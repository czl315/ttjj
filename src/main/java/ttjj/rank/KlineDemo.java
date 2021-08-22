package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.dao.BizRankDao;
import ttjj.dao.KlineDao;
import ttjj.dao.MyBatisUtils;
import ttjj.dto.Kline;
import ttjj.dto.RankBizDataDiff;
import ttjj.dto.TradeHisBack;
import ttjj.service.KlineService;
import utils.Content;
import utils.DateUtil;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * k线
 */
public class KlineDemo {
    public static void main(String[] args) {
        //  插入常用指数k线
        addZhishuKline();
    }

    /**
     * 插入常用指数k线
     */
    private static void addZhishuKline() {
        String klt = "1";//klt=5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
        int lmt = 1000000;
        int addDaysMax = 66;//最多增加的天数
        int year = 2021;
        int month = 7;
        int day = 1;
        Map<String, String> zhishuMap = Content.getZhishuMap();
        Set<String> zhishuList = zhishuMap.keySet();
        for (String zhishu : zhishuList) {
//            //按照日期的分钟线（5分钟；15分钟；30分钟；60分钟；120分钟），插入k线
//            addKlineByDay(zhishu, klt);

//            //  增加大周期k线
            addKlineDaZhouQi(zhishu, klt);

        }


    }

    /**
     * 按照日期的分钟线（5分钟；15分钟；30分钟；60分钟；120分钟），插入k线
     *
     * @param zhishu
     * @param klt
     */
    private static void addKlineByDay(String zhishu, String klt) {
        int lmt = 1000000;
        int addDaysMax = 66;//最多增加的天数
        int year = 2021;
        int month = 7;
        int day = 1;
        for (int i = 0; i < addDaysMax; i++) {
            String begDate = "";//查询新增交易的开始时间
            String endDate = "";
            begDate = DateUtil.getDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, year, month, day, i);
            endDate = begDate;
            /**    创业板50-159949 HS300ETF-510310 50ETF-510050	新汽车-515030	芯片ETF-159995	酒ETF-512690	医疗ETF-512170 	光伏ETF-515790	稀土ETF-516780	有色50-159880	煤炭ETF-515220 军工ETF-512660		**/
            String zqdm = zhishu;
            String klineRs = KlineService.klineRsStrHttpDfcf(zqdm, lmt, klt, begDate, endDate);
            System.out.println("开始日期:" + begDate + "，结束日期:" + endDate + "，周期:" + klt + "，klines.size():" + klineRs);
//        System.out.println("klines:"+JSON.toJSONString(klines));

            if (StringUtils.isBlank(klineRs)) {
                System.out.println("k线查询结果为空！");
            }

            JSONObject szzzMonthJson = JSON.parseObject(klineRs);
            JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
            String zqmc = szzzMonthDataJson.getString("name");
            System.out.println("证券名称：" + zqmc);
            if (szzzMonthDataJson == null || !szzzMonthDataJson.containsKey("klines")) {
                System.out.println("klines数据异常：" + JSON.toJSONString(szzzMonthDataJson));
            }

            JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
            if (klines == null || klines.size() <= 0) {
                System.out.println("k线数据为空！");
                continue;
            }

            Kline kline = new Kline();
            kline.setKlt(klt);
            kline.setRs(klineRs);
            kline.setZqmc(zqmc);
            kline.setZqdm(zqdm);
            kline.setKtime(begDate);
            /**
             * 插入数据库-K线
             */
            KlineDao.insert(kline);
        }
    }

    /**
     * 增加大周期k线
     *
     * @param zhishu
     * @param klt
     */
    private static void addKlineDaZhouQi(String zhishu, String klt) {
        int lmt = 1000000;
        String begDate = "";//查询新增交易的开始时间
        String endDate = "";
        begDate = "0";
        endDate = "20500101";
        String zqdm = zhishu;
        String klineRs = KlineService.klineRsStrHttpDfcf(zqdm, lmt, klt, begDate, endDate);
        System.out.println("开始日期:" + begDate + "，结束日期:" + endDate + "，周期:" + klt + "，klines.size():" + klineRs);
//        System.out.println("klines:"+JSON.toJSONString(klines));

        if (StringUtils.isBlank(klineRs)) {
            System.out.println("k线查询结果为空！");
        }

        JSONObject szzzMonthJson = JSON.parseObject(klineRs);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
        String zqmc = szzzMonthDataJson.getString("name");
        System.out.println("证券名称：" + zqmc);
        if (szzzMonthDataJson == null || !szzzMonthDataJson.containsKey("klines")) {
            System.out.println("klines数据异常：" + JSON.toJSONString(szzzMonthDataJson));
        }

        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        if (klines == null || klines.size() <= 0) {
            System.out.println("k线数据为空！");
        }

        Kline kline = new Kline();
        kline.setKlt(klt);
        kline.setRs(klineRs);
        kline.setZqmc(zqmc);
        kline.setZqdm(zqdm);
        kline.setKtime(begDate);
        /**
         * 插入数据库-K线
         */
        KlineDao.insert(kline);
    }

    /**
     * 交易回测
     *
     * @param klines
     * @param tradeHisBack
     */
    private static TradeHisBack tradeHistoryTest(List<Kline> klines, TradeHisBack tradeHisBack) {
        if (klines == null || klines.size() == 0) {
            System.out.println("k线数据不能为空！");
        }
//        System.out.println("klines.size():" + klines.size());

        boolean initOldPrice = true;//初始化原有价格标志
        for (Kline kline : klines) {
            if (initOldPrice) {
                tradeHisBack.setOldPrice(kline.getCloseLastAmt());
                initOldPrice = false;
            }
//            System.out.println("kline:" + JSON.toJSONString(kline));
            tradeHisBack.setKtime(kline.getKtime());

//            if (kline.getCloseLastAmt().compareTo(BigDecimal.ZERO) > 0) {
//                tradeHisBack.setNowPrice(kline.getCloseLastAmt());
//            }
            //当前价=收盘价
            BigDecimal nowPrice = kline.getCloseAmt();
            BigDecimal stPrice = tradeHisBack.getStPrice();//标准价
            if (stPrice.compareTo(BigDecimal.ZERO) <= 0) {
                stPrice = kline.getCloseLastAmt();
            }
            //比较当前价与最低价，如果最低价低于下跌因子价格，卖出一定份额
            if (nowPrice.compareTo(stPrice) < 0) {
//                System.out.println("收盘价[" + nowPrice + "]低于上一次最低价[" + stPrice + "]");
                BigDecimal spread = nowPrice.subtract(stPrice);
                BigDecimal downPct = spread.divide(stPrice, 4, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal("100"));//百分比
//                System.out.println("下跌百分比:" + downPct + "%");
                //因子价格
                BigDecimal downLimitPrice = stPrice.multiply(tradeHisBack.getDownFactor().divide(new BigDecimal("100"), 4, BigDecimal.ROUND_HALF_DOWN)).add(stPrice);//限定价格
//                System.out.println("nowPrice:"+nowPrice+",downLimitPrice:"+downLimitPrice);
                if (nowPrice.compareTo(downLimitPrice) < 0) {
//                    System.out.println("下跌百分比低于限定下跌因子价格，执行卖出操作。");
                    //如果持仓数量足够才卖出
                    if (tradeHisBack.getHoldNum().compareTo(BigDecimal.ZERO) > 0) {
                        BigDecimal remainCashAmt = tradeHisBack.getCash().add(nowPrice.multiply(tradeHisBack.getEachNum()));
                        tradeHisBack.setStPrice(nowPrice);//执行卖出后，才更新限价
                        tradeHisBack.setNowPrice(nowPrice);
                        tradeHisBack.setCash(remainCashAmt);
                        tradeHisBack.setHoldNum(tradeHisBack.getHoldNum().subtract(tradeHisBack.getEachNum()));
                        tradeHisBack.setSellCount(tradeHisBack.getSellCount().add(new BigDecimal("1")));
                    } else {
//                        System.out.println("持仓数量不足！");
                    }
//                    System.out.println("tradeHisBack:" + JSON.toJSONString(tradeHisBack));
                } else {
//                    System.out.println("下跌百分比不低于限定下跌因子价格，暂无操作。");
                }
            }

            //比较当前价与最高价，如果高于上涨因子价格，买入一定份额
            if (nowPrice.compareTo(stPrice) > 0) {
//                System.out.println("收盘价[" + nowPrice + "]高于上一次最高价[" + stPrice + "]");
                BigDecimal spread = nowPrice.subtract(stPrice);
                BigDecimal upPct = spread.divide(stPrice, 4, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal("100"));//上涨百分比
//                System.out.println("上涨百分比:" + upPct + "%");
                //上涨因子价格
                BigDecimal upLimitPrice = stPrice.multiply(tradeHisBack.getUpFactor().divide(new BigDecimal("100"), 4, BigDecimal.ROUND_HALF_DOWN)).add(stPrice);//上涨限定价格
                if (nowPrice.compareTo(upLimitPrice) >= 0) {
//                    System.out.println("上涨百分比高于限定上涨因子价格，执行买入操作。");
                    //如果现金足够才能买入
                    BigDecimal buyAmt = nowPrice;
                    BigDecimal remainCashAmt = tradeHisBack.getCash().subtract(buyAmt.multiply(tradeHisBack.getEachNum()));
                    if (remainCashAmt.compareTo(BigDecimal.ZERO) > 0) {
                        tradeHisBack.setStPrice(nowPrice);//执行买入后，才更新最高限价
                        tradeHisBack.setNowPrice(nowPrice);
                        tradeHisBack.setCash(remainCashAmt);
                        tradeHisBack.setHoldNum(tradeHisBack.getHoldNum().add(tradeHisBack.getEachNum()));
                        tradeHisBack.setBuyCount(tradeHisBack.getBuyCount().add(new BigDecimal("1")));
                    } else {
//                        System.out.println("现金不足！");
                    }
//                    System.out.println("tradeHisBack:" + JSON.toJSONString(tradeHisBack));
                } else {
//                    System.out.println("上涨百分比不高于限定上涨因子价格，暂无操作。");
                }
            }

            tradeHisBack.setHoldAmt(tradeHisBack.getHoldNum().multiply(kline.getCloseAmt()));
            tradeHisBack.setNowPrice(kline.getCloseAmt());
        }
        BigDecimal totalAmtOld = tradeHisBack.getTotalAmtOld();
        BigDecimal totalAmtNew = tradeHisBack.getHoldAmt().add(tradeHisBack.getCash());
        BigDecimal zdf = totalAmtNew.subtract(totalAmtOld).divide(totalAmtOld, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
        BigDecimal zdfPoint = tradeHisBack.getNowPrice().subtract(tradeHisBack.getOldPrice()).divide(tradeHisBack.getOldPrice(), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
        tradeHisBack.setTotalAmt(totalAmtNew);
        tradeHisBack.setZdf(zdf.setScale(2, BigDecimal.ROUND_HALF_UP));
        tradeHisBack.setZdfPoint(zdfPoint.setScale(2, BigDecimal.ROUND_HALF_UP));

        return tradeHisBack;
    }

    private static void addOrSubFactor(List<Kline> klines) {
        String addFactor = "0.4";//因子-上涨加速
        int closeZdfComAddFactorCountYes = 0;//因子-上涨加速-收盘涨跌幅-成功个数
        int closeZdfComAddFactorCountNo = 0;//因子-上涨加速-收盘涨跌幅-失败个数
        String subFactor = "0.7";//下跌加速因子
        int closeZdfComSubFactorCountYes = 0;//因子-下跌加速-收盘涨跌幅-成功个数
        int closeZdfComSubFactorCountNo = 0;//因子-下跌加速-收盘涨跌幅-失败个数

        if (klines != null) {
            System.out.println("klines.size():" + klines.size());
            klines = klines.stream().filter(e -> e != null).sorted(Comparator.comparing(Kline::getZhangDieFu).reversed()).collect(Collectors.toList());//排序-涨跌幅
            for (Kline kline : klines) {
//                System.out.println(JSON.toJSONString(kline));
                System.out.print("日期:" + kline.getKtime() + ",");
                System.out.print("上一期收盘:" + kline.getCloseLastAmt() + ",");
                System.out.print("开盘:" + kline.getOpenAmt() + ",");
                BigDecimal openZdf = new BigDecimal(0);//开盘涨跌幅
                if (kline.getCloseLastAmt().compareTo(new BigDecimal(0)) == 0) {
                    System.out.print("开盘涨跌:未查询到昨日收盘价");
                } else {
                    openZdf = kline.getOpenAmt().subtract(kline.getCloseLastAmt()).divide(kline.getCloseLastAmt(), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
                    if (openZdf.compareTo(new BigDecimal(addFactor)) > 0) {//加速上涨-超过开盘涨幅因子
                        BigDecimal closeZdfComAddFactor = kline.getZhangDieFu().subtract(new BigDecimal(addFactor));
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.print("开盘涨跌:" + openZdf + ",");
                        System.out.print("收盘涨跌幅-上涨加速因子:" + closeZdfComAddFactor + ",");
                        if (closeZdfComAddFactor.compareTo(new BigDecimal(0)) >= 0) {
                            closeZdfComAddFactorCountYes++;
                        } else {
                            closeZdfComAddFactorCountNo++;
                        }
                    } else {
                        System.out.print("开盘涨跌:" + openZdf + ",");
                    }

                    //下跌因子比较
                    if (openZdf.compareTo(new BigDecimal(subFactor)) <= 0) {//加速上涨-超过开盘涨幅因子
                        BigDecimal closeZdfComSubFactor = kline.getZhangDieFu().subtract(new BigDecimal(subFactor));
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.print("开盘涨跌:" + openZdf + ",");
                        System.out.print("收盘涨跌幅-下跌加速因子:" + closeZdfComSubFactor + ",");
                        if (closeZdfComSubFactor.compareTo(new BigDecimal(0)) > 0) {
                            closeZdfComSubFactorCountYes++;
                        } else {
                            closeZdfComSubFactorCountNo++;
                        }
                    } else {
                        System.out.print("开盘涨跌:" + openZdf + ",");
                    }
                }
//                System.out.print("收盘:" + kline.getCloseAmt() + ",");
//            System.out.print("成交量:\t" + klineArray[5] + ",\t\t");
//            System.out.print("成交额:\t" + klineArray[6] + ",\t\t");
//                System.out.print("振幅:" + kline.getZhenFu() + ",");
                System.out.print("涨跌:" + kline.getZhangDieFu() + ",");
//                System.out.print("最低:" + kline.getMinAmt() + ",");
//                System.out.print("最高:" + kline.getMaxAmt() + ",");
//            System.out.print("换手率:" + klineArray[10] + ",");
                System.out.println();
            }
            System.out.println();
            System.out.println("上涨加速-收盘涨跌幅-成功个数:" + closeZdfComAddFactorCountYes + ",成功率：" + new BigDecimal("" + closeZdfComAddFactorCountYes).divide(new BigDecimal(closeZdfComAddFactorCountYes + closeZdfComAddFactorCountNo), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            System.out.println("上涨加速-收盘涨跌幅-失败个数:" + closeZdfComAddFactorCountNo + ",成功率：" + new BigDecimal("" + closeZdfComAddFactorCountNo).divide(new BigDecimal(closeZdfComAddFactorCountYes + closeZdfComAddFactorCountNo), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            System.out.println();
            System.out.println("下跌-收盘涨跌幅-成功个数:" + closeZdfComSubFactorCountYes + ",成功率：" + new BigDecimal("" + closeZdfComSubFactorCountYes).divide(new BigDecimal(closeZdfComSubFactorCountYes + closeZdfComSubFactorCountNo), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            System.out.println("下跌-收盘涨跌幅-失败个数:" + closeZdfComSubFactorCountNo + ",成功率：" + new BigDecimal("" + closeZdfComSubFactorCountNo).divide(new BigDecimal(closeZdfComSubFactorCountYes + closeZdfComSubFactorCountNo), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));

        }
    }

}
