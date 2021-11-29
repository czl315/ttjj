package ttjj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.BizRankDao;
import ttjj.dao.RankStockCommpanyDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.Kline;
import ttjj.dto.RankBizDataDiff;
import utils.DateUtil;
import utils.HttpUtil;
import utils.ToBuyMap;

import java.math.BigDecimal;
import java.util.*;

import static utils.Content.*;

/**
 * FundFlowService简介 资金流向
 *
 * @author Administrator
 * @date 2021-10-28 01:00
 */
public class FundFlowService {
    public static void main(String[] args) {
        String limitStartTime = null;
        String stCode = "601877";//万科Ａ:000002  中航沈飞:600760  广发证券-000776 片仔癀：600436  分众传媒:002027  招商银行:600036 通威股份-600438
        //上证50ETF:510050    券商ETF：512000 159995:芯片
//        String limitStartTime = "2021-11-12 10:00";
//        String limitStartTime = "2021-11-12 10:50";
        fundFlowHandler(stCode, limitStartTime);//查询资金流向，判断买卖信号

//        Set<String> toBuySet = ToBuyMap.stockMap.keySet();
//        Set<String> toBuySet = ToBuyMap.banks.keySet();
//        Set<String> toBuySet = ToBuyMap.wenHuaChuanMei.keySet();
//        for (String code : toBuySet) {
//            fundFlowHandler(code, limitStartTime);//查询资金流向，判断买卖信号
//        }
    }

    /**
     * 查询资金流向，判断买卖信号
     *
     * @param zqdm
     */
    public static String fundFlowHandler(String zqdm, String limitStartTime) {
        String rs = httpFundFlowRs(zqdm);
//        System.out.println("zqdm:" + zqdm + ",rs:" + rs);
        JSONObject szzzMonthJson = JSON.parseObject(rs);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));

        if (szzzMonthDataJson == null || !szzzMonthDataJson.containsKey("klines")) {
            System.out.println("数据异常[资金流向]：" + rs + ",zqdm:" + zqdm);
            return null;
        }
        String name = "";
        if (szzzMonthDataJson.containsKey("name")) {
            name = szzzMonthDataJson.getString("name").replace(" ", "");
        }
//        System.out.println("指数名称："+name);

        //  查询证券的市值-从数据库中(股票或etf)-最新的
        BigDecimal marketValue = null;
        RankStockCommpanyDb stock = findMarketValue(zqdm);
        if (stock != null) {
            marketValue = stock.getF20();
        }else{
            return null;
        }

        //查询今日分钟级别K线
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
        List<Kline> klineTodayList = KlineService.kline(zqdm, NUM_MAX_999, KLT_1, true, date, date, KLINE_TYPE_STOCK);
        Map<String, Kline> klineTodayMap = new HashMap<>();
        for (Kline kline : klineTodayList) {
            klineTodayMap.put(kline.getKtime(), kline);
        }

        BigDecimal yesterdayCloseAmt = null;//昨日收盘价
        List<Kline> klineDayList = KlineService.kline(zqdm, 2, KLT_101, false, "", date, KLINE_TYPE_STOCK);
        if (klineDayList != null && klineDayList.size() >= 2) {
            yesterdayCloseAmt = klineDayList.get(0).getCloseAmt();
//            System.out.println("昨日收盘价：" + yesterdayCloseAmt);
        }

        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        boolean isLimitStartTime = true;
        if (klines != null) {
//            BigDecimal flowMoneyTotal = new BigDecimal("0");//主力净流入-合计
            BigDecimal flowMoneyLast = null;//流入金额-上一个
            BigDecimal flowMoneyCe = new BigDecimal("0");//流入金额-两个时段的差额
            BigDecimal flowMoneyTotal = new BigDecimal("0");//累计流入金额
            for (Object klineObj : klines) {
                String klineString = (String) klineObj;
                String[] klineArray = klineString.split(",");
                //  日期时间，主力净流入,小单净流入,中单净流入,大单净流入,超大单净流入
                //"2021-10-27 09:31,-3737368.0,3689243.0,48125.0,-3680116.0,-57252.0",
                String dateTime = klineArray[0];

                //  开始时间判断
                if (isLimitStartTime) {
                    if (StringUtils.isNotBlank(limitStartTime)) {
                        if (!dateTime.equals(limitStartTime)) {
//                            System.out.println("未到开始时间");
                            continue;
                        } else {
                            isLimitStartTime = false;
                        }
                    } else {
                        isLimitStartTime = false;
                        continue;
                    }
                }


                if (klineArray[0].contains(":") && klineArray[0].length() == 16) {
                    dateTime = klineArray[0] + ":00";
                }
                System.out.print(name + ":" + zqdm);
                System.out.print("," + dateTime.substring(5, 16));
                //今日分钟级别K线-当前时间涨跌
                if (klineTodayMap.containsKey(dateTime)) {
                    Kline kline = klineTodayMap.get(dateTime);

                    System.out.print("，\t分涨：" + kline.getZhangDieFu());
                    System.out.print("，\t累涨：" + kline.getCloseAmt().subtract(yesterdayCloseAmt).divide(yesterdayCloseAmt, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100").setScale(-2, BigDecimal.ROUND_HALF_UP)) + "\t");
                }

                BigDecimal flowMoneyCur = new BigDecimal(klineArray[1]);//流入金额-当前
                flowMoneyTotal = flowMoneyCur;//累计流入金额
                if (flowMoneyLast != null) {
                    flowMoneyCe = flowMoneyCur.subtract(flowMoneyLast);
                }
                flowMoneyLast = flowMoneyCur;

                System.out.print(",主力净流入:" + flowMoneyCur.divide(new BigDecimal("10000"), 0, BigDecimal.ROUND_HALF_UP));//万
                //如果净流入超过阈值，报警
                boolean buyFlag = false;//买入信号
                boolean sellFlag = false;//卖出信号
                for (int i = 1; i <= 100; i++) {
                    //净流入超过阈值正向
                    if (flowMoneyCur.compareTo(new BigDecimal(i * 10000000)) > 0) {//每千万为单位
//                        System.out.print(",请注意,主力净流入超过");
                        System.out.print("," + i);
                        buyFlag = true;
                    }
                    //净流入超过阈值-负向卖出信号
                    if (flowMoneyCur.compareTo(new BigDecimal(-i * 10000000)) < 0) {//每千万为单位
//                        System.out.print(",请注意,主力净流出超过");
                        System.out.print("," + -i);
                        sellFlag = true;
                    }
                }
//                if (buyFlag) {
//                    System.out.print(",买入信号!");
//                }
//                if (sellFlag) {
//                    System.out.print(",负向卖出信号!!!");
//                }

                //判断两个时段的差额
                if (marketValue != null) {
                    //市值比-百万分比
                    BigDecimal szb = flowMoneyCe.divide(marketValue, 8, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("1000000")).setScale(2, BigDecimal.ROUND_HALF_UP);
//                    System.out.print(",市值比:" + szb);
                    if (szb.compareTo(new BigDecimal("50")) > 0 || flowMoneyCe.compareTo(new BigDecimal("5000000")) > 0) {
                        System.out.print(",大额买入：" + flowMoneyCe.divide(new BigDecimal("10000"), 0, BigDecimal.ROUND_HALF_UP) + ",市值比:" + szb);//万);
                    }
                    if (szb.compareTo(new BigDecimal("-50")) < 0 || flowMoneyCe.compareTo(new BigDecimal("-5000000")) < 0) {
                        System.out.print(",大额卖出：" + flowMoneyCe.divide(new BigDecimal("10000"), 0, BigDecimal.ROUND_HALF_UP) + ",市值比:" + szb);//万);
                    }
                } else {
                    if (flowMoneyCe.compareTo(new BigDecimal("1000000")) > 0) {
                        System.out.print(",大额买入：" + flowMoneyCe.divide(new BigDecimal("10000"), 0, BigDecimal.ROUND_HALF_UP));//万);
                    }
                    if (flowMoneyCe.compareTo(new BigDecimal("-1000000")) < 0) {
                        System.out.print(",大额卖出：" + flowMoneyCe.divide(new BigDecimal("10000"), 0, BigDecimal.ROUND_HALF_UP));//万);
                    }
                }

                System.out.println();
            }
            BigDecimal flowMarketValueRate = flowMoneyTotal.divide(marketValue, 6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("10000")).setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println("--------------------------------------------------------市值：" + marketValue.divide(new BigDecimal("10000"), 4, BigDecimal.ROUND_HALF_UP) + ",主力净流入total:" + flowMoneyTotal.divide(new BigDecimal("10000"), 0, BigDecimal.ROUND_HALF_UP) + ",万分比例:" + flowMarketValueRate);
//            System.out.print(",主力净流入-合计:" + flowMoneyTotal);
        }
        return null;
    }

    /**
     * 查询证券的市值-从数据库中(股票或etf)-最新的
     *
     * @param zqdm
     */
    private static RankStockCommpanyDb findMarketValue(String zqdm) {
        //判断证券代码前缀
        RankStockCommpanyDb stockCondition = new RankStockCommpanyDb();
        stockCondition.setF12(zqdm);
        RankStockCommpanyDb stock = RankStockCommpanyDao.findStockLast(stockCondition);
//        System.out.println("首先查询股票，:" + zqdm + ",rs：" + JSON.toJSONString(stock));

        //如果非股票，查询etf
        if (stock == null) {
            RankBizDataDiff condition = new RankBizDataDiff();
            condition.setF12(zqdm);
            RankBizDataDiff rs = BizRankDao.findEtfLast(condition);
            if (rs != null) {
                stock = new RankStockCommpanyDb();
                stock.setF12(rs.getF12());
                stock.setF14(rs.getF14());
                stock.setF20(rs.getF20());
                System.out.println("如果非股票，查询etf:" + zqdm + ",rs：" + JSON.toJSONString(stock));
            } else {
                System.out.println("非股票；非etf；zqdm：" + zqdm);
            }
        }

        return stock;
    }

    /**
     * 查询资金流向，返回结果字符串json格式
     *
     * @param zqdm
     * @return
     */
    public static String httpFundFlowRs(String zqdm) {
        long curTime = System.currentTimeMillis();
        //http://push2.eastmoney.com/api/qt/stock/fflow/kline/get?cb=jQuery112301410828211613766_1635351266119&lmt=0&klt=1&fields1=f1%2Cf2%2Cf3%2Cf7&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58%2Cf59%2Cf60%2Cf61%2Cf62%2Cf63%2Cf64%2Cf65&ut=b2884a393a59ad64002292a3e90d46a5&secid=0.002027&_=1635351266120
        StringBuffer url = new StringBuffer();
//        url.append(jqueryHttpHead);
//        url.append("jQuery112309508918124001358_");
        url.append("http://push2.eastmoney.com/api/qt/stock/fflow/kline/get");
        StringBuffer urlParam = new StringBuffer();
        urlParam.append("cb=jQuery11230141082821161" + RandomUtils.nextInt(1000, 9999) + "_");
        urlParam.append(curTime);
        urlParam.append("&lmt=0");//
        urlParam.append("&klt=1");//
        urlParam.append("&fields1=f1,f2,f3,f7");//
        urlParam.append("&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61,f62,f63,f64,f65");//
        urlParam.append("&ut=b2884a393a59ad64002292a3e90d46a5");//
//        urlParam.append("&secid=" + zqdm + "");//股票代码
        urlParam.append("&secid=");

        if (zqdm.startsWith("00") || zqdm.startsWith("20") || zqdm.startsWith("30") || zqdm.startsWith("159")) {
            urlParam.append("0." + zqdm);
        } else if (zqdm.startsWith("93")) {
            //2.931643
            urlParam.append("2." + zqdm);
        } else {
            //zhiShu.startsWith("5") || zhiShu.startsWith("6") || zhiShu.startsWith("000")|| zhiShu.startsWith("11")|| zhiShu.startsWith("12")
            urlParam.append("1." + zqdm);
        }
        urlParam.append("&_=" + (curTime + 1));//
//        System.out.println("请求url:");
//        System.out.println(url + "?" + urlParam);
        String rs = "";
        try {
            rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
        } catch (Exception e) {
            System.out.println("/** http重试 **/");
            rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
        }

        /**
         * 如果返回异常，n次重试
         */
        for (int i = 0; i < 10; i++) {
            if (StringUtils.isBlank(rs)) {
                rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
            } else {
                break;
            }
        }
//        System.out.println("rs:" + rs);
        rs = rs.substring(rs.indexOf("({"));
        rs = rs.replace("({", "{");
        rs = rs.replace("});", "}");
//        System.out.println("rs:" + rs);

        return rs;
    }
}