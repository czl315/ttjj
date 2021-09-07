package ttjj.rank;

import ttjj.dto.Kline;
import ttjj.dto.RankBizDataDiff;
import ttjj.dto.TradeHisBack;
import ttjj.service.KlineService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenzhilong
 * @date 2021/6/27
 */
public class KlineHistoryTest {
    public static void main(String[] args) {
        /**	创业板50-159949	50ETF-510050(低跌1.5：7 13	86	15.1163)	HS300ETF-510310	500ETF-510500
         新汽车-515030	芯片ETF-159995	酒ETF-512690	医疗ETF-512170 	光伏ETF-515790	稀土ETF-516780	有色50-159880	煤炭ETF-515220 军工ETF-512660		券商ETF	512000	中概互联-513050 基建50ETF-516970
         招商银行	600036	**/
        //        String zqdm = "159949";
        String zqdm = "159949";
        String begDate = "20210823";//查询新增交易的开始时间
        String endDate = "20210903";
        String klt = "5";//klt=5:5分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
        int lmt = 1000000;
        List<RankBizDataDiff> rankBizDataDiffList = new ArrayList<>();
        List<Kline> klines = KlineService.kline(zqdm, lmt, klt, true, begDate, endDate);
        System.out.println("开始日期:" + begDate + "，结束日期:" + endDate + "，周期:" + klt + "，klines.size():" + klines.size());
//        System.out.println("klines:"+JSON.toJSONString(klines));

//        // 上涨或下跌因子
//        addOrSubFactor(klines);

        //   计算买卖
        handlerBuySell(klines);


    }

    private static void handlerBuySell(List<Kline> klines) {
        for (int i = 1; i <= 20; i++) {
//            boolean downFactorFlag = true;//固定因子
            boolean downFactorFlag = false;
//            boolean upFactorFlag = true;
            boolean upFactorFlag = false;

            BigDecimal downFactorTemp = new BigDecimal("-0.9");
            BigDecimal upFactorTemp = new BigDecimal("0.3");

            BigDecimal downFactorTempStep = new BigDecimal("0.1");
//            BigDecimal downFactorTemp = downFactorTempStep.multiply(new BigDecimal(i)).multiply(new BigDecimal("-1"));
//            BigDecimal downFactorTemp = new BigDecimal("-0.25");
            BigDecimal upFactorFlagStep = new BigDecimal("0.1");
//            BigDecimal upFactorTemp = upFactorFlagStep.multiply(new BigDecimal(i)).multiply(new BigDecimal("1"));
//            BigDecimal upFactorTemp = new BigDecimal("0.35");

            List<TradeHisBack> rs = new ArrayList<>();//回测结果列表
            for (int j = 1; j <= 10; j++) {
                BigDecimal downFactor = new BigDecimal("0");
                if (downFactorFlag) {
                    downFactor = downFactorTemp;
                } else {
                    BigDecimal step = new BigDecimal("0.1");
                    downFactor = step.multiply(new BigDecimal(i)).multiply(new BigDecimal("-1"));
                }
//            BigDecimal downFactor = new BigDecimal("-0.5");

                BigDecimal upFactor = new BigDecimal("0");
                if (upFactorFlag) {
                    upFactor = upFactorTemp;
                } else {
                    BigDecimal step = new BigDecimal("0.1");
                    upFactor = step.multiply(new BigDecimal(j)).multiply(new BigDecimal("1"));
                }
//                BigDecimal upFactor = new BigDecimal("1.0");//上涨[0.1-1.0]:0.5；

                //下跌数据统计：15m,20210701-20210813
                //      下跌因子[-0.4,-0.5,-0.6,],上涨因子：[2.0,2.0,2.0,],涨幅：[2.26,1.80,1.67,]

                //交易回测
                //初始化交易历史回测数据
                TradeHisBack tradeHisBack = new TradeHisBack();
                tradeHisBack.setLmtAmt(new BigDecimal("100000"));
                tradeHisBack.setEachNum(new BigDecimal("5000"));
                tradeHisBack.setCash(new BigDecimal("65525"));
//                tradeHisBack.setCash(new BigDecimal("100000"));
                tradeHisBack.setHoldAmt(new BigDecimal("34475.00"));
//                tradeHisBack.setHoldAmt(new BigDecimal("0"));
                tradeHisBack.setHoldNum(new BigDecimal("25000"));
//                tradeHisBack.setHoldNum(new BigDecimal("0"));
                tradeHisBack.setTotalAmt(new BigDecimal("100000"));
                tradeHisBack.setTotalAmtOld(new BigDecimal("100000"));
                tradeHisBack.setDownFactor(downFactor);
                tradeHisBack.setUpFactor(upFactor);
                tradeHisBack.setKtime("");
                tradeHisBack.setStPrice(new BigDecimal("0"));
                tradeHisBack.setNowPrice(new BigDecimal("0"));
                tradeHisBack.setBuyCount(new BigDecimal("0"));
                tradeHisBack.setSellCount(new BigDecimal("0"));

                tradeHisBack = tradeHistoryTest(klines, tradeHisBack);//历史回测

                rs.add(tradeHisBack);

//            System.out.println(JSON.toJSONString(tradeHisBack));
//            System.out.print("涨跌幅：" + tradeHisBack.getZdf() + "%");
//            System.out.print("，上涨因子：" + tradeHisBack.getUpFactor());
//            System.out.print("，下跌因子：" + tradeHisBack.getDownFactor());
//            System.out.print("，买入次数：" + tradeHisBack.getBuyCount());
//            System.out.print("，卖出次数：" + tradeHisBack.getSellCount());
//            System.out.print("，现金：" + tradeHisBack.getCash());
//            System.out.print("，持仓：" + tradeHisBack.getHoldAmt());
//            System.out.print("，总资产：" + tradeHisBack.getTotalAmt());
//            System.out.println();
            }

            //统计前三的下跌因子和上涨因子数据
            String tjjgDownFactor = "";
            String tjjgUpFactor = "";
            String tjjgZdf = "";
            int count = 3;
            rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(TradeHisBack::getZdf, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
            for (TradeHisBack tradeHisBack : rs) {
                BigDecimal upFactor = tradeHisBack.getUpFactor();
                BigDecimal downFactor = tradeHisBack.getDownFactor();
                List<BigDecimal> showUpFactors = new ArrayList<>();
                showUpFactors.add(new BigDecimal("0.1"));
                showUpFactors.add(new BigDecimal("0.2"));
                showUpFactors.add(new BigDecimal("0.3"));
                showUpFactors.add(new BigDecimal("0.4"));
                showUpFactors.add(new BigDecimal("0.5"));
                showUpFactors.add(new BigDecimal("0.6"));
                showUpFactors.add(new BigDecimal("0.7"));
                showUpFactors.add(new BigDecimal("0.8"));
                showUpFactors.add(new BigDecimal("0.9"));
                showUpFactors.add(new BigDecimal("1.0"));
                if(showUpFactors.contains(upFactor)){
//                if(downFactor.compareTo(new BigDecimal("-0.8"))==0){
                    System.out.print("涨跌幅：" + tradeHisBack.getZdf() + "%");
                    System.out.print("，上涨因子：" + upFactor);
                    System.out.print("，下跌因子：" + downFactor);
                    System.out.print("，买入次数：" + tradeHisBack.getBuyCount());
                    System.out.print("，卖出次数：" + tradeHisBack.getSellCount());
                    System.out.print("，现金：" + tradeHisBack.getCash());
                    System.out.print("，持仓：" + tradeHisBack.getHoldAmt());
                    System.out.print("，总资产：" + tradeHisBack.getTotalAmt());
                    System.out.print("，原有价格：" + tradeHisBack.getOldPrice());
                    System.out.print("，最新价格：" + tradeHisBack.getNowPrice());
                    System.out.print("，指数涨跌：" + tradeHisBack.getZdfPoint() + "%");
                    System.out.println();
                }

                if (count > 0) {
                    //统计前三的下跌因子和上涨因子数据
                    tjjgDownFactor = tjjgDownFactor + tradeHisBack.getDownFactor() + ",";
                    tjjgUpFactor = tjjgUpFactor + tradeHisBack.getUpFactor() + ",";
                    tjjgZdf = tjjgZdf + tradeHisBack.getZdf() + ",";
//            下跌因子[-0.1]:上涨因子：0.9(0.93%),1.0,1.2；
                }
                count--;
            }

            System.out.println("下跌因子[" + tjjgDownFactor + "],上涨因子：[" + tjjgUpFactor + "]" + ",涨幅：[" + tjjgZdf + "]");
        }
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
            if(initOldPrice){
                tradeHisBack.setOldPrice(kline.getCloseLastAmt());
                initOldPrice=false;
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
