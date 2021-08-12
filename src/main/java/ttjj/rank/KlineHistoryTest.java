package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.BizRankDao;
import ttjj.dto.Kline;
import ttjj.dto.RankBizDataDiff;
import ttjj.dto.TradeHisBack;
import utils.HttpUtil;

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
        String zqdm = "515220";
        String begDate = "20210101";//查询新增交易的开始时间
        String endDate = "20210415";
        String klt = "101";//klt=5:5分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
        int lmt = 100;
        List<RankBizDataDiff> rankBizDataDiffList = new ArrayList<>();
        List<Kline> klines = kline(zqdm, lmt, klt, begDate, endDate);
        System.out.println(JSON.toJSONString(klines));
        for (Kline kline : klines) {
            RankBizDataDiff rankBizDataDiff = new RankBizDataDiff();
            rankBizDataDiff.setDate(kline.getKtime());
            rankBizDataDiff.setType("etf");
            rankBizDataDiff.setF1(3L);
            rankBizDataDiff.setF2(kline.getCloseAmt().doubleValue());
            rankBizDataDiff.setF3(kline.getZhangDieFu().doubleValue());
            rankBizDataDiff.setF4(kline.getZhangDieE().doubleValue());
            rankBizDataDiff.setF5(kline.getCjl().longValue());
            rankBizDataDiff.setF6(kline.getCje().longValue());
            rankBizDataDiff.setF7(kline.getZhenFu().doubleValue());
            rankBizDataDiff.setF8(kline.getHuanShouLv().doubleValue());
//            rankBizDataDiff.setF9(kline.getsh);
//            rankBizDataDiff.getF10(kline.get)
//            rankBizDataDiff.getF11()
            rankBizDataDiff.setF12(kline.getZqdm());
            rankBizDataDiff.setF13(0L);
            rankBizDataDiff.setF14(kline.getZqmc());
            rankBizDataDiff.setF15(kline.getMaxAmt().doubleValue());
            rankBizDataDiff.setF16(kline.getMinAmt().doubleValue());
            rankBizDataDiff.setF17(kline.getOpenAmt().doubleValue());
            rankBizDataDiff.setF18(kline.getCloseAmt().subtract(kline.getZhangDieE()).doubleValue());//计算昨日收盘价：今日收盘价-今日涨跌额

            rankBizDataDiffList.add(rankBizDataDiff);
        }
        System.out.println(JSON.toJSONString(rankBizDataDiffList));

        BizRankDao.insertDbBiz(rankBizDataDiffList);//hy-行业


//        // 上涨或下跌因子
//        addOrSubFactor(klines);

        //交易回测
//        TradeHisBack tradeHisBack = tradeHistoryTest(klines);
//        System.out.println(JSON.toJSONString(tradeHisBack));

    }

    /**
     * 交易回测
     *
     * @param klines
     */
    private static TradeHisBack tradeHistoryTest(List<Kline> klines) {
        if (klines == null || klines.size() == 0) {
            System.out.println("k线数据不能为空！");
        }
        System.out.println("klines.size():" + klines.size());
        //初始化交易历史回测数据
        TradeHisBack tradeHisBack = new TradeHisBack();
        tradeHisBack.setLmtAmt(new BigDecimal(100000));
        tradeHisBack.setEachNum(new BigDecimal(5000));
        tradeHisBack.setCash(new BigDecimal(100000));
        tradeHisBack.setDownFactor(new BigDecimal("-0.01"));
        tradeHisBack.setUpFactor(new BigDecimal("0.01"));
        tradeHisBack.setHoldAmt(new BigDecimal(0));
        tradeHisBack.setHoldNum(new BigDecimal(0));
        tradeHisBack.setKtime("");
        tradeHisBack.setMaxPrice(new BigDecimal(0));
        tradeHisBack.setMinPrice(new BigDecimal(0));
        tradeHisBack.setNowPrice(new BigDecimal(0));
        for (Kline kline : klines) {
            System.out.println("kline:" + JSON.toJSONString(kline));
            tradeHisBack.setKtime(kline.getKtime());

            //当前价
            tradeHisBack.setNowPrice(kline.getOpenAmt());
            if (kline.getCloseLastAmt().compareTo(BigDecimal.ZERO) > 0) {
                tradeHisBack.setNowPrice(kline.getCloseLastAmt());
            }
            BigDecimal nowPrice = tradeHisBack.getNowPrice();
            BigDecimal minAmt = kline.getMinAmt();
            BigDecimal maxAmt = kline.getMaxAmt();
            //比较当前价与最低价，如果最低价低于下跌因子价格，卖出一定份额
            if (nowPrice.compareTo(minAmt) > 0) {
                BigDecimal spread = nowPrice.subtract(minAmt);
                BigDecimal downPct = spread.divide(nowPrice, 2, BigDecimal.ROUND_HALF_DOWN);//下跌百分比
                BigDecimal sellMultiple = downPct.divide(tradeHisBack.getDownFactor(), 1, BigDecimal.ROUND_HALF_DOWN);//卖出系数
                int sellMultipleInt = sellMultiple.intValue();
                for (int i = 0; i < sellMultipleInt; i++) {
                    //计算n份，循环卖出,如果持有份额足够才能卖出
                    BigDecimal remainNum = tradeHisBack.getHoldNum().subtract(tradeHisBack.getEachNum());
                    if (remainNum.compareTo(BigDecimal.ZERO) > 0) {
                        tradeHisBack.setHoldNum(remainNum);
                    } else {
                        break;
                    }
                }
            }

            //比较当前价与最高价，如果最高价高于上涨因子价格，买出一定份额
            if (nowPrice.compareTo(maxAmt) < 0) {
                BigDecimal spread = maxAmt.subtract(nowPrice);
                BigDecimal upPct = spread.divide(nowPrice, 2, BigDecimal.ROUND_HALF_DOWN);//下跌百分比
                BigDecimal buyMultiple = upPct.divide(tradeHisBack.getUpFactor(), 1, BigDecimal.ROUND_HALF_DOWN);//买入系数
                int buyMultipleInt = buyMultiple.intValue();
                for (int i = 0; i < buyMultipleInt; i++) {
                    //计算n份，循环买入,如果现金足够才能买入
                    BigDecimal buyAmt = nowPrice.multiply(tradeHisBack.getUpFactor().add(new BigDecimal(1)).multiply(tradeHisBack.getEachNum()));
                    BigDecimal remainCashAmt = tradeHisBack.getCash().subtract(buyAmt);
                    if (remainCashAmt.compareTo(BigDecimal.ZERO) > 0) {
                        tradeHisBack.setCash(remainCashAmt);
                        tradeHisBack.setHoldNum(tradeHisBack.getHoldNum().add(tradeHisBack.getEachNum()));
                    } else {
                        System.out.println("现金不足！");
                        break;
                    }
                }
            }
        }
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

    /**
     * 查询-ETF-指数
     *
     * @param zhiShu  指数
     * @param lmt     数量
     * @param klt     K线周期类型
     * @param begDate
     * @param endDate
     */
    public static List<Kline> kline(String zhiShu, int lmt, String klt, String begDate, String endDate) {
        //http://76.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery33106379847099350968_1624766355746&secid=0.399673&ut=fa5fd1943c7b386f172d6893dbfba10b&fields1=f1,f2,f3,f4,f5,f6&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61&klt=101&fqt=1&beg=0&end=20500101&smplmt=1390.59&lmt=1000000&_=1624766355750
        StringBuffer url = new StringBuffer();
        url.append("http://96.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery331093188916841208381602168987937");
        if (zhiShu.startsWith("5") || zhiShu.startsWith("6")) {
            url.append("&secid=" + "1." + zhiShu);
        } else {
            url.append("&secid=" + "0." + zhiShu);
        }

        url.append("&ut=fa5fd1943c7b386f172d6893dbfba10b");
//        url.append("&fields1=f1,f2,f3,f4,f5,f6");
        url.append("&fields1=");
        url.append("f1,f2,f3,f4,f5,f6,f7,f8,f9,");
        url.append("f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,");
        url.append("f20,f21,f22,f23,f24,f25,f26,f27,f28,f29,");
        url.append("f30,f31,f32,f33,f34,f35,f36,f37,f38,f39,");
        url.append("f40,f41,f42,f43,f44,f45,f46,f47,f48,f49,");
        url.append("f50,f51,f52,f53,f54,f55,f56,f57,f58,f59,");
        url.append("f60,f61,f62,f63,f64,f65,f66,f67,f68,f69,");
        url.append("f70,f71,f72,f73,f74,f75,f76,f77,f78,f79,");
        url.append("f80,f81,f82,f83,f84,f85,f86,f87,f88,f89,");
        url.append("f90,f91,f92,f93,f94,f95,f96,f97,f98,f99");

//        url.append("&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61");/**     f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61  日期，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率 **/
        url.append("&fields2=");
        url.append("f1,f2,f3,f4,f5,f6,f7,f8,f9,");
        url.append("f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,");
        url.append("f20,f21,f22,f23,f24,f25,f26,f27,f28,f29,");
        url.append("f30,f31,f32,f33,f34,f35,f36,f37,f38,f39,");
        url.append("f40,f41,f42,f43,f44,f45,f46,f47,f48,f49,");
        url.append("f50,f51,f52,f53,f54,f55,f56,f57,f58,f59,");
        url.append("f60,f61,f62,f63,f64,f65,f66,f67,f68,f69,");
        url.append("f70,f71,f72,f73,f74,f75,f76,f77,f78,f79,");
        url.append("f80,f81,f82,f83,f84,f85,f86,f87,f88,f89,");
        url.append("f90,f91,f92,f93,f94,f95,f96,f97,f98,f99");

        url.append("&klt=" + klt);
        url.append("&fqt=1");
        url.append("&beg=" + begDate);
        url.append("&end=" + endDate);
        url.append("&lmt=" + lmt);
        url.append("&_=" + System.currentTimeMillis());

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("&StartDate=").append(startDate);

//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
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

        String rsJson = rs.substring(rs.indexOf("{"));
        rsJson = rsJson.replace(");", "");
        System.out.println("szKline:" + rsJson);

        List<String> klineList = new ArrayList<String>();
        JSONObject szzzMonthJson = JSON.parseObject(rsJson);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
//        String name = szzzMonthDataJson.getString("name");
//        System.out.println("指数名称："+name);
        List<Kline> klineRs = new ArrayList<>();
        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        if (klines != null) {
            BigDecimal lastCloseAmt = new BigDecimal("0");//上一期收盘价
            for (Object klineObj : klines) {
                String klineString = (String) klineObj;
                klineList.add(klineString);
                String[] klineArray = klineString.split(",");
                //  日期，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率
                //"2020-09-30,3389.74,3218.05,3425.63,3202.34,4906229054,6193724911616.00,6.58,-5.23,-177.63,13.40"
                Kline kline = new Kline();
                String dateTime = klineArray[0];
                if (klineArray[0].contains(":") && klineArray[0].length() == 16) {
                    dateTime = klineArray[0] + ":00";
                }
                BigDecimal closeAmt = new BigDecimal(klineArray[2]);
                kline.setKtime(dateTime);
                kline.setOpenAmt(new BigDecimal(klineArray[1]));
                kline.setCloseAmt(closeAmt);
                kline.setMaxAmt(new BigDecimal(klineArray[3]));
                kline.setMinAmt(new BigDecimal(klineArray[4]));
                kline.setCjl(new BigDecimal(klineArray[5]));
                kline.setCje(new BigDecimal(klineArray[6]));
                kline.setZhenFu(new BigDecimal(klineArray[7]));
                kline.setZhangDieFu(new BigDecimal(klineArray[8]));
                kline.setZhangDieE(new BigDecimal(klineArray[9]));
                kline.setHuanShouLv(new BigDecimal(klineArray[10]));
                kline.setCloseLastAmt(lastCloseAmt);
                lastCloseAmt = closeAmt;
                kline.setZqdm(szzzMonthDataJson.getString("code"));
                kline.setZqmc(szzzMonthDataJson.getString("name"));

                klineRs.add(kline);
            }
        }
        return klineRs;
    }

}
