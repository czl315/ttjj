package ttjj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.BizRankDao;
import ttjj.dao.RankStockCommpanyDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.FundFlow;
import ttjj.dto.Kline;
import ttjj.dto.RankBizDataDiff;
import utils.DateUtil;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.ContHttpRs.*;
import static utils.Content.*;
import static utils.ContentUrl.HTTP_HEAD_FFLOW_DAY;

/**
 * FundFlowService简介 资金流向
 *
 * @author Administrator
 * @date 2021-10-28 01:00
 */
public class FundFlowService {
    public static void main(String[] args) {
//        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
        String date = "2022-05-27";
        BigDecimal unit = new BigDecimal("100000000");
        String limitStartTime = null;
        String zqdm = "BK0433";//万科Ａ:000002  中航沈飞:600760  广发证券-000776 片仔癀：600436  分众传媒:002027  招商银行:600036 通威股份-600438
        //石油行业:90.BK0464
        //上证50ETF:510050    券商ETF：512000 159995:芯片
//        String limitStartTime = "2021-11-12 10:00";
//        String limitStartTime = "2021-11-12 10:50";
//        fundFlowHandler(zqdm, limitStartTime);//查询资金流向，判断买卖信号

        List<FundFlow> rsList = parse(httpFundFlowRs(zqdm, null, MINUTE_1 + ""));//获取-资金流向的对象结果
        rsList = handlerFundFlowByMinute(rsList, MINUTE_15);//计算分钟级别资金流向
        RankBizDataDiff biz = BizService.findBiz(zqdm, date, null);

        BigDecimal marketValueBk = biz.getF20();

        BigDecimal mainNetIn = new BigDecimal("0");
        BigDecimal smallNetIn = new BigDecimal("0");
        BigDecimal midNetIn = new BigDecimal("0");
        BigDecimal bigNetIn = new BigDecimal("0");
        BigDecimal superBigNetIn = new BigDecimal("0");
        for (FundFlow fundFlow : rsList) {
//            System.out.println(JSON.toJSONString(fundFlow));
//            System.out.println("时间:" + fundFlow.getKtime() + ",主力净流入:" + fundFlow.getMainNetIn());
            //万分比
            BigDecimal flowRateBk = fundFlow.getFlowInMain().divide(marketValueBk, 8, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("10000")).setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println("时间:" + fundFlow.getKtime() + ",主力净流入:" + fundFlow.getFlowInMain().divide(unit, 2, BigDecimal.ROUND_HALF_UP) + "," + "流入市值比：[" + flowRateBk + "]");
            mainNetIn = mainNetIn.add(fundFlow.getFlowInMain());
            smallNetIn = smallNetIn.add(fundFlow.getFlowInSmall());
            midNetIn = midNetIn.add(fundFlow.getFlowInMid());
            bigNetIn = bigNetIn.add(fundFlow.getFlowInBig());
            superBigNetIn = superBigNetIn.add(fundFlow.getFlowInSuperBig());

        }
        System.out.println("mainNetIn:" + mainNetIn.divide(unit, 2, BigDecimal.ROUND_HALF_UP));
        System.out.println("superBigNetIn:" + superBigNetIn.divide(unit, 2, BigDecimal.ROUND_HALF_UP));
        System.out.println("bigNetIn:" + bigNetIn.divide(unit, 2, BigDecimal.ROUND_HALF_UP));
        System.out.println("midNetIn:" + midNetIn.divide(unit, 2, BigDecimal.ROUND_HALF_UP));
        System.out.println("smallNetIn:" + smallNetIn.divide(unit, 2, BigDecimal.ROUND_HALF_UP));

        //净值
//        System.out.println(KlineService.handlerAvgLine("5日价格", KlineService.findNetMinMaxAvg(zqdm, MA_5, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
//        System.out.println(KlineService.handlerAvgLine("10日价格", KlineService.findNetMinMaxAvg(zqdm, MA_10, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
//        System.out.println(KlineService.handlerAvgLine("20日价格", KlineService.findNetMinMaxAvg(zqdm, MA_20, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
//        System.out.println(KlineService.handlerAvgLine("60日价格", KlineService.findNetMinMaxAvg(zqdm, MA_60, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
//        System.out.println();

//        Set<String> toBuySet = ToBuyMap.stockMap.keySet();
//        Set<String> toBuySet = ToBuyMap.banks.keySet();
//        Set<String> toBuySet = ToBuyMap.wenHuaChuanMei.keySet();
//        for (String code : toBuySet) {
//            fundFlowHandler(code, limitStartTime);//查询资金流向，判断买卖信号
//        }
    }

    /**
     * 计算分钟级别资金流向-根据类型
     *
     * @param rsList     资金流向结果列表，每分钟
     * @param minuteType 周期类型
     * @return 金流向结果列表
     */
    public static List<FundFlow> handlerFundFlowByMinute(List<FundFlow> rsList, int minuteType) {
        List<FundFlow> rs = new ArrayList<>();
        if (rsList == null) {
            return null;
        }

        BigDecimal mainNetIn = new BigDecimal("0");
        BigDecimal smallNetIn = new BigDecimal("0");
        BigDecimal midNetIn = new BigDecimal("0");
        BigDecimal bigNetIn = new BigDecimal("0");
        BigDecimal superBigNetIn = new BigDecimal("0");
        for (FundFlow fundFlow : rsList) {
//            System.out.println(JSON.toJSONString(fundFlow));
            //根据周期类型，对时间最后两位进行取余，如果取余为0，则将累计的结果重新设值,然后重置
            String ktime = fundFlow.getKtime();

            if (minuteType == KLT_DAY_1) {
                //天的处理
                FundFlow newRs = new FundFlow();
//                System.out.println(ktimeMinuteInt);

                //累计的结果 = 新值-旧值
                mainNetIn = fundFlow.getFlowInMain().subtract(mainNetIn);
                smallNetIn = fundFlow.getFlowInSmall().subtract(smallNetIn);
                midNetIn = fundFlow.getFlowInMid().subtract(midNetIn);
                bigNetIn = fundFlow.getFlowInBig().subtract(bigNetIn);
                superBigNetIn = fundFlow.getFlowInSuperBig().subtract(superBigNetIn);

                newRs.setKtime(fundFlow.getKtime());
                newRs.setCode(fundFlow.getCode());
                newRs.setName(fundFlow.getName());
                newRs.setFlowInMain(mainNetIn);
                newRs.setFlowInSmall(smallNetIn);
                newRs.setFlowInMid(midNetIn);
                newRs.setFlowInBig(bigNetIn);
                newRs.setFlowInSuperBig(superBigNetIn);

                rs.add(newRs);

                //将此时的值设定为起始值
                mainNetIn = fundFlow.getFlowInMain();
                smallNetIn = fundFlow.getFlowInSmall();
                midNetIn = fundFlow.getFlowInMid();
                bigNetIn = fundFlow.getFlowInBig();
                superBigNetIn = fundFlow.getFlowInSuperBig();
            } else if (minuteType == MINUTE_60) {
                //一般情况取余处理即可，60分钟需要特殊处理
                if (ktime.endsWith("10:30") || ktime.endsWith("11:30") || ktime.endsWith("14:00") || ktime.endsWith("15:00")) {
                    FundFlow newRs = new FundFlow();
//                System.out.println(ktimeMinuteInt);

                    //累计的结果 = 新值-旧值
                    mainNetIn = fundFlow.getFlowInMain().subtract(mainNetIn);
                    smallNetIn = fundFlow.getFlowInSmall().subtract(smallNetIn);
                    midNetIn = fundFlow.getFlowInMid().subtract(midNetIn);
                    bigNetIn = fundFlow.getFlowInBig().subtract(bigNetIn);
                    superBigNetIn = fundFlow.getFlowInSuperBig().subtract(superBigNetIn);

                    newRs.setKtime(fundFlow.getKtime());
                    newRs.setCode(fundFlow.getCode());
                    newRs.setName(fundFlow.getName());
                    newRs.setFlowInMain(mainNetIn);
                    newRs.setFlowInSmall(smallNetIn);
                    newRs.setFlowInMid(midNetIn);
                    newRs.setFlowInBig(bigNetIn);
                    newRs.setFlowInSuperBig(superBigNetIn);

                    rs.add(newRs);

                    //将此时的值设定为起始值
                    mainNetIn = fundFlow.getFlowInMain();
                    smallNetIn = fundFlow.getFlowInSmall();
                    midNetIn = fundFlow.getFlowInMid();
                    bigNetIn = fundFlow.getFlowInBig();
                    superBigNetIn = fundFlow.getFlowInSuperBig();
                }
            } else {
                String ktimeMinute = ktime.substring(fundFlow.getKtime().length() - 2);
                int ktimeMinuteInt = Integer.valueOf(ktimeMinute);
                if (ktimeMinuteInt % minuteType == 0) {
                    FundFlow newRs = new FundFlow();
//                System.out.println(ktimeMinuteInt);

                    //累计的结果 = 新值-旧值
                    mainNetIn = fundFlow.getFlowInMain().subtract(mainNetIn);
                    smallNetIn = fundFlow.getFlowInSmall().subtract(smallNetIn);
                    midNetIn = fundFlow.getFlowInMid().subtract(midNetIn);
                    bigNetIn = fundFlow.getFlowInBig().subtract(bigNetIn);
                    superBigNetIn = fundFlow.getFlowInSuperBig().subtract(superBigNetIn);

                    newRs.setKtime(fundFlow.getKtime());
                    newRs.setCode(fundFlow.getCode());
                    newRs.setName(fundFlow.getName());
                    newRs.setFlowInMain(mainNetIn);
                    newRs.setFlowInSmall(smallNetIn);
                    newRs.setFlowInMid(midNetIn);
                    newRs.setFlowInBig(bigNetIn);
                    newRs.setFlowInSuperBig(superBigNetIn);

                    rs.add(newRs);

                    //将此时的值设定为起始值
                    mainNetIn = fundFlow.getFlowInMain();
                    smallNetIn = fundFlow.getFlowInSmall();
                    midNetIn = fundFlow.getFlowInMid();
                    bigNetIn = fundFlow.getFlowInBig();
                    superBigNetIn = fundFlow.getFlowInSuperBig();

                }
            }

        }
        return rs;
    }

    /**
     * 查询资金流向，判断买卖信号
     *
     * @param zqdm
     */
    public static String fundFlowHandler(String zqdm, String limitStartTime) {
        String rs = httpFundFlowRs(zqdm, null, MINUTE_1 + "");
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
        } else {
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
     * @param zqdm zqdm
     * @param type 类型
     * @param klt
     * @return rs
     */
    public static String httpFundFlowRs(String zqdm, String type, String klt) {
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
        if (klt.equals(KLT_101)) {
            urlParam.append("&klt=" + KLT_101);//
        } else {
            urlParam.append("&klt=1");//
        }
        urlParam.append("&fields1=f1,f2,f3,f7");//
        urlParam.append("&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61,f62,f63,f64,f65");//
        urlParam.append("&ut=b2884a393a59ad64002292a3e90d46a5");//
        urlParam.append("&secid=").append(KlineService.getSecid(zqdm, type));

        urlParam.append("&_=" + (curTime + 1));//
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

    /**
     * 获取-资金流向的对象结果，根据资金流向转换
     *
     * @param fundFlowRs 资金流向http查询结果
     * @return 资金流向对象结果
     */
    public static List<FundFlow> parse(String fundFlowRs) {
        List<FundFlow> rs = new ArrayList<>();

        //{"rc":0,"rt":21,"svr":182482236,"lt":1,"full":0,"dlmkts":"","data":{"code":"BK0464","market":90,"name":"石油行业","tradePeriods":{"pre":null,"after":null,"periods":[{"b":202205270930,"e":202205271130},{"b":202205271300,"e":202205271500}]},
        //  "klines":["2022-05-27 09:31,9706708.0,-4195368.0,-5511340.0,6747405.0,2959303.0","2022-05-27 09:32,13862440.0,-4179670.0,-9682770.0,3219155.0,10643285.0"]}}
        JSONObject jsonRs = JSON.parseObject(fundFlowRs);
        JSONObject jsonRsData = JSON.parseObject(jsonRs.getString(RS_KEY_DATA));
        String code = "";
        if (jsonRsData.containsKey(RS_KEY_CODE)) {
            code = jsonRsData.getString(RS_KEY_CODE);
        }
        String name = null;
        if (jsonRsData.containsKey(RS_KEY_NAME)) {
            name = jsonRsData.getString(RS_KEY_NAME);
        }

        if (jsonRsData == null || !jsonRsData.containsKey(RS_KEY_KLINES)) {
            System.out.println("数据异常[资金流向]：" + rs + ",zqmc:" + name);
            return null;
        }

        JSONArray klines = JSON.parseArray(jsonRsData.getString(RS_KEY_KLINES));
        if (klines != null) {
            for (Object klineObj : klines) {
                FundFlow fundFlow = new FundFlow();
                String klineString = (String) klineObj;
                String[] klineArray = klineString.split(",");
                //  日期时间，主力净流入,小单净流入,中单净流入,大单净流入,超大单净流入
                //"2021-10-27 09:31,-3737368.0,3689243.0,48125.0,-3680116.0,-57252.0",
                String dateTime = klineArray[0];
//                if (klineArray[0].contains(":") && klineArray[0].length() == 16) {
//                    dateTime = klineArray[0] + ":00";
//                }
                BigDecimal mainNetIn = new BigDecimal(klineArray[1]);
                BigDecimal smallNetIn = new BigDecimal(klineArray[2]);
                BigDecimal midNetIn = new BigDecimal(klineArray[3]);
                BigDecimal bigNetIn = new BigDecimal(klineArray[4]);
                BigDecimal superBigNetIn = new BigDecimal(klineArray[5]);

                fundFlow.setCode(code);
                fundFlow.setCode(name);
                fundFlow.setKtime(dateTime);
                fundFlow.setFlowInMain(mainNetIn);
                fundFlow.setFlowInSmall(smallNetIn);
                fundFlow.setFlowInMid(midNetIn);
                fundFlow.setFlowInBig(bigNetIn);
                fundFlow.setFlowInSuperBig(superBigNetIn);
                rs.add(fundFlow);
            }
        }
        return rs;
    }


    /**
     * 查询(http)-资金流向-历史日期
     *
     * @param zqdm 证券代码
     * @param klt  周期
     * @return 历史资金流向列表
     */
    public static List<FundFlow> httpFundFlowHisDay(String zqdm, String klt) {
        List<FundFlow> fundFlowRs = new ArrayList<>();

        String rs = httpFundFlowHisDayRs(zqdm, klt);

        //{"rc":0,"rt":21,"svr":182482236,"lt":1,"full":0,"dlmkts":"","data":{"code":"BK0464","market":90,"name":"石油行业","tradePeriods":{"pre":null,"after":null,"periods":[{"b":202205270930,"e":202205271130},{"b":202205271300,"e":202205271500}]},
        //  "klines":["2022-05-27 09:31,9706708.0,-4195368.0,-5511340.0,6747405.0,2959303.0","2022-05-27 09:32,13862440.0,-4179670.0,-9682770.0,3219155.0,10643285.0"]}}
        JSONObject jsonRs = JSON.parseObject(rs);
        if (jsonRs == null || !jsonRs.containsKey(RS_KEY_DATA)) {
            System.out.println("查询信息异常：" + JSON.toJSONString(rs));
            return fundFlowRs;
        }
        JSONObject jsonRsData = JSON.parseObject(jsonRs.getString(RS_KEY_DATA));
        if (jsonRsData == null || !jsonRsData.containsKey(RS_KEY_CODE)) {
            System.out.println("查询信息异常：" + JSON.toJSONString(rs));
            return fundFlowRs;
        }
        String code = "";
        if (jsonRsData.containsKey(RS_KEY_CODE)) {
            code = jsonRsData.getString(RS_KEY_CODE);
        }
        String name = null;
        if (jsonRsData.containsKey(RS_KEY_NAME)) {
            name = jsonRsData.getString(RS_KEY_NAME);
        }

        if (jsonRsData == null || !jsonRsData.containsKey(RS_KEY_KLINES)) {
            System.out.println("数据异常[资金流向历史日期]：" + rs + ",zqmc:" + name);
            return null;
        }

        JSONArray klines = JSON.parseArray(jsonRsData.getString(RS_KEY_KLINES));

        if (klines != null) {
            for (Object klineObj : klines) {
                FundFlow fundFlow = new FundFlow();
                String klineString = (String) klineObj;
                String[] klineArray = klineString.split(",");
                //  日期时间，主力净流入,小单净流入,中单净流入,大单净流入,超大单净流入
                //"2021-10-27 09:31,-3737368.0,3689243.0,48125.0,-3680116.0,-57252.0",
                String dateTime = klineArray[0];
//                if (klineArray[0].contains(":") && klineArray[0].length() == 16) {
//                    dateTime = klineArray[0] + ":00";
//                }
                BigDecimal mainNetIn = new BigDecimal(klineArray[1]);
                BigDecimal smallNetIn = new BigDecimal(klineArray[2]);
                BigDecimal midNetIn = new BigDecimal(klineArray[3]);
                BigDecimal bigNetIn = new BigDecimal(klineArray[4]);
                BigDecimal superBigNetIn = new BigDecimal(klineArray[5]);

                fundFlow.setCode(code);
                fundFlow.setCode(name);
                fundFlow.setKtime(dateTime);
                fundFlow.setDate(dateTime);
                fundFlow.setFlowInMain(mainNetIn);
                fundFlow.setFlowInSmall(smallNetIn);
                fundFlow.setFlowInMid(midNetIn);
                fundFlow.setFlowInBig(bigNetIn);
                fundFlow.setFlowInSuperBig(superBigNetIn);
                fundFlowRs.add(fundFlow);
            }
        }

        return fundFlowRs;
    }

    /**
     * 查询(http)-资金流向-历史日期
     *
     * @param zqdm 证券代码
     * @param klt  周期
     * @param date 日期
     * @return 历史当天资金流向
     */
    public static FundFlow findFundFlowHisDay(String zqdm, String klt, String date) {
        String rs = httpFundFlowHisDayRs(zqdm, klt);
        JSONObject jsonRs = JSON.parseObject(rs);
        JSONObject jsonRsData = JSON.parseObject(jsonRs.getString(RS_KEY_DATA));
        String code = "";
        if (jsonRsData.containsKey(RS_KEY_CODE)) {
            code = jsonRsData.getString(RS_KEY_CODE);
        }
        String name = null;
        if (jsonRsData.containsKey(RS_KEY_NAME)) {
            name = jsonRsData.getString(RS_KEY_NAME);
        }

        if (jsonRsData == null || !jsonRsData.containsKey(RS_KEY_KLINES)) {
            System.out.println("数据异常[资金流向历史日期]：" + rs + ",zqmc:" + name);
            return null;
        }

        JSONArray klines = JSON.parseArray(jsonRsData.getString(RS_KEY_KLINES));
        FundFlow fundFlow = null;
        if (klines != null) {
            for (Object klineObj : klines) {
                String klineString = (String) klineObj;
                String[] klineArray = klineString.split(",");
                //  日期时间，主力净流入,小单净流入,中单净流入,大单净流入,超大单净流入
                //"2021-10-27 09:31,-3737368.0,3689243.0,48125.0,-3680116.0,-57252.0",
                String dateTime = klineArray[0];
                if (date.equals(dateTime)) {//只查询指定日期的
                    BigDecimal mainNetIn = new BigDecimal(klineArray[1]);
                    BigDecimal smallNetIn = new BigDecimal(klineArray[2]);
                    BigDecimal midNetIn = new BigDecimal(klineArray[3]);
                    BigDecimal bigNetIn = new BigDecimal(klineArray[4]);
                    BigDecimal superBigNetIn = new BigDecimal(klineArray[5]);

                    fundFlow.setCode(code);
                    fundFlow.setCode(name);
                    fundFlow.setKtime(dateTime);
                    fundFlow.setDate(dateTime);
                    fundFlow.setFlowInMain(mainNetIn);
                    fundFlow.setFlowInSmall(smallNetIn);
                    fundFlow.setFlowInMid(midNetIn);
                    fundFlow.setFlowInBig(bigNetIn);
                    fundFlow.setFlowInSuperBig(superBigNetIn);
                }
            }
        }

        return fundFlow;
    }

    /**
     * 查询(http)-资金流向-历史日期
     *
     * @param zqdm 证券代码
     * @param klt  周期
     * @return 历史资金流向列表
     */
    public static String httpFundFlowHisDayRs(String zqdm, String klt) {
        long curTime = System.currentTimeMillis();
        StringBuffer url = new StringBuffer();
//        url.append(jqueryHttpHead);
        String httpHead = HTTP_HEAD_FFLOW_DAY;
        //https://push2his.eastmoney.com/api/qt/stock/fflow/daykline/get?cb=jQuery112305250574768837943_1654268615719&lmt=0&klt=101&fields1=f1%2Cf2%2Cf3%2Cf7&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58%2Cf59%2Cf60%2Cf61%2Cf62%2Cf63%2Cf64%2Cf65&ut=b2884a393a59ad64002292a3e90d46a5&secid=90.BK0451&_=1654268615720
        StringBuffer urlParam = new StringBuffer();
        urlParam.append("&lmt=0");//
        urlParam.append("&klt=").append(klt);//
        urlParam.append("&fields1=f1,f2,f3,f7");//
        urlParam.append("&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61,f62,f63,f64,f65");//
        urlParam.append("&ut=b2884a393a59ad64002292a3e90d46a5");//
        urlParam.append("&secid=").append(KlineService.getSecid(zqdm, null));

        urlParam.append("&_=" + (curTime + 1));//

//        System.out.println("请求url:" + HttpUtil.randomHttpHead(httpHead) + url.toString());
        String rs = "";
        try {
            rs = HttpUtil.sendGet(HttpUtil.randomHttpHead(httpHead) + url.toString(), urlParam.toString(), "");
        } catch (Exception e) {
            System.out.println("/** http重试 **/" + e);
            rs = HttpUtil.sendGet(HttpUtil.randomHttpHead(httpHead) + url.toString(), urlParam.toString(), "");
        }
        /**
         * 如果返回异常，n次重试
         */
        for (int i = 0; i < 10; i++) {
            if (StringUtils.isBlank(rs)) {
                rs = HttpUtil.sendGet(HttpUtil.randomHttpHead(httpHead) + url.toString(), urlParam.toString(), "");
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