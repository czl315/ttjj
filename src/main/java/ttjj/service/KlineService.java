package ttjj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import ttjj.dto.Kline;
import utils.Content;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Content.*;

/**
 * @author chenzhilong
 * @date 2021/7/26
 */
public class KlineService {

    public static void main(String[] args) {
        // 查询最小净值、最大净值、均值
        findNetMinMaxAvgDemo();
    }

    /**
     * 测试样例：查询最小净值、最大净值、均值
     */
    private static void findNetMinMaxAvgDemo() {
        String zqdm = Content.ZQDM_ETF_CYB50_159949;
        String begDate = "";//查询新增交易的开始时间
        String endDate = "20500101";
        String klt = Content.KLT_101;//klt=5:5分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
        int lmt = Content.MA_250;
        Map<String, BigDecimal> minMaxJzMap = KlineService.findNetMinMaxAvg(zqdm, lmt, klt, false, begDate, endDate, KLINE_TYPE_STOCK);
        System.out.println("key:" + Content.keyRsMin + ",value:" + minMaxJzMap.get(Content.keyRsMin));
        System.out.println("key:" + Content.keyRsMax + ",value:" + minMaxJzMap.get(Content.keyRsMax));
        System.out.println("key:" + Content.keyRsNetCloseMin + ",value:" + minMaxJzMap.get(Content.keyRsNetCloseMin));
        System.out.println("key:" + Content.keyRsNetCloseMax + ",value:" + minMaxJzMap.get(Content.keyRsNetCloseMax));
        System.out.println("key:" + Content.keyRsNetCloseAvg + ",value:" + minMaxJzMap.get(Content.keyRsNetCloseAvg));
        System.out.println("key:" + Content.keyRsKlineCount + ",value:" + minMaxJzMap.get(Content.keyRsKlineCount));
//        for (String key : minMaxJzMap.keySet()) {
//            System.out.println("key:" + key + ",value:" + minMaxJzMap.get(key));
//        }
    }

    /**
     * 查询-ETF-指数
     *
     * @param zhiShu       指数
     * @param lmt          数量
     * @param klt          K线周期类型
     * @param isHasBegDate
     * @param begDate
     * @param endDate
     * @param klineType
     * @return
     */
    public static List<Kline> kline(String zhiShu, int lmt, String klt, Boolean isHasBegDate, String begDate, String endDate, String klineType) {
        String rs = klineRsStrHttpDfcf(zhiShu, lmt, klt, isHasBegDate, begDate, endDate, klineType);//k线结果
//        System.out.println(rs);
        JSONObject szzzMonthJson = JSON.parseObject(rs);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
//        String name = szzzMonthDataJson.getString("name");
//        System.out.println("指数名称："+name);
        if (szzzMonthDataJson == null || !szzzMonthDataJson.containsKey("klines")) {
            System.out.println("klines数据异常：" + JSON.toJSONString(szzzMonthDataJson));
            return null;
        }

        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        List<Kline> klineRs = new ArrayList<>();
        if (klines != null) {
            BigDecimal lastCloseAmt = new BigDecimal("0");//上一期收盘价
            for (Object klineObj : klines) {
                Kline kline = new Kline();
                String klineString = (String) klineObj;
                kline.setRs(klineString);
                kline.setKlt(klt);
                String[] klineArray = klineString.split(",");
                //  日期，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率
                //"2020-09-30,3389.74,3218.05,3425.63,3202.34,4906229054,6193724911616.00,6.58,-5.23,-177.63,13.40"
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
                lastCloseAmt = kline.getCloseAmt().subtract(kline.getZhangDieE());
                kline.setCloseLastAmt(lastCloseAmt);
                kline.setZqdm(szzzMonthDataJson.getString("code"));
                kline.setZqmc(szzzMonthDataJson.getString("name"));
                kline.setRs(rs);

                klineRs.add(kline);
            }
        }
        return klineRs;
    }

    /**
     * k线结果字符串类型
     *
     * @param zqdm
     * @param lmt
     * @param klt
     * @param isHasBegDate
     * @param begDate
     * @param endDate
     * @return
     */
    public static String klineRsStrHttpDfcf(String zqdm, int lmt, String klt, Boolean isHasBegDate, String begDate, String endDate, String klineType) {
        begDate = begDate.replace("-", "");
        endDate = endDate.replace("-", "");
        long curTime = System.currentTimeMillis();
        int random = RandomUtils.nextInt(40, 99);
        //http://76.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery33106379847099350968_1624766355746&secid=0.399673&ut=fa5fd1943c7b386f172d6893dbfba10b&fields1=f1,f2,f3,f4,f5,f6&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61&klt=101&fqt=1&beg=0&end=20500101&smplmt=1390.59&lmt=1000000&_=1624766355750
        StringBuffer url = new StringBuffer();
//        url.append("http://96.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery331093188916841208381602168987937");
        url.append("http://" + random + ".push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery33103254362175743777_" + curTime);
        url.append("&secid=");

        if (klineType.equals(KLINE_TYPE_STOCK)) {
            if (zqdm.startsWith("00") || zqdm.startsWith("20") || zqdm.startsWith("30") ||zqdm.startsWith("159")) {
                url.append("0." + zqdm);

                //指数 zqdm.startsWith("159") || zqdm.startsWith("399") ||
//                Map<String, String> zhishuMap = Content.getZhishuMap();
//                if (zhishuMap.keySet().contains(zqdm) && !zqdm.startsWith("399")) {
//                    url.append("1." + zqdm);
//                } else {
//                    url.append("0." + zqdm);
//                }
                //159开头
                //  110、120开头是可转债
                //  上海证券交易所决定为交易型货币市场基金、债券ETF等上市交易的固定收益类基金产品分配511000-511999专用证券代码段。
                //沪市A股票代码是以60开头 沪市主板股票代码:600、601、603、605。
                //深市A股票代码是以00开头 深市主板股票代码:000开头。深市中小板股票代码:002开头。
                //  创业板股票代码:300开头。科创板股票代码:688开头。
                //    B股代码是以900开头
                //    新股申购的代码是以730开头
                //    配股代码以700开头
                //    B股代码是以200开头
                //    新股申购的代码是以00开头
                //    配股代码以080开头
                //S开头表示未进行股改，
                //ST开头表示连续两年股东收益为负等原因，
                //*ST开头表示有退市风险
                //XD表示分红等
            } else if (zqdm.startsWith("93")) {
                //2.931643
                url.append("2." + zqdm);
            } else {
                //zhiShu.startsWith("5") || zhiShu.startsWith("6") || zhiShu.startsWith("000")|| zhiShu.startsWith("11")|| zhiShu.startsWith("12")
                url.append("1." + zqdm);
            }
        } else if (klineType.equals(KLINE_TYPE_INDEX)) {
            if (zqdm.startsWith("000") || zqdm.startsWith("5") || zqdm.startsWith("6") || zqdm.startsWith("11") || zqdm.startsWith("12")) {
                //zhiShu.startsWith("5") || zhiShu.startsWith("6") || zhiShu.startsWith("000")|| zhiShu.startsWith("11")|| zhiShu.startsWith("12")
                url.append("1." + zqdm);

                //指数 zqdm.startsWith("159") || zqdm.startsWith("399") ||
//                Map<String, String> zhishuMap = Content.getZhishuMap();
//                if (zhishuMap.keySet().contains(zqdm) && !zqdm.startsWith("399")) {
//                    url.append("1." + zqdm);
//                } else {
//                    url.append("0." + zqdm);
//                }
                //159开头
                //  110、120开头是可转债
                //  上海证券交易所决定为交易型货币市场基金、债券ETF等上市交易的固定收益类基金产品分配511000-511999专用证券代码段。
                //沪市A股票代码是以60开头 沪市主板股票代码:600、601、603、605。
                //深市A股票代码是以00开头 深市主板股票代码:000开头。深市中小板股票代码:002开头。
                //  创业板股票代码:300开头。科创板股票代码:688开头。
                //    B股代码是以900开头
                //    新股申购的代码是以730开头
                //    配股代码以700开头
                //    B股代码是以200开头
                //    新股申购的代码是以00开头
                //    配股代码以080开头
                //S开头表示未进行股改，
                //ST开头表示连续两年股东收益为负等原因，
                //*ST开头表示有退市风险
                //XD表示分红等
            } else if (zqdm.startsWith("39") || zqdm.startsWith("47") || zqdm.startsWith("48") || zqdm.startsWith("97") || zqdm.startsWith("98")) {
                //  39开头、47开头、48开头、97开头、98开头
                url.append("0." + zqdm);
            } else if (zqdm.startsWith("93")) {
                //2.931643
                url.append("2." + zqdm);
            } else {
                url.append("0." + zqdm);
            }
        } else if (klineType.equals(KLINE_TYPE_BAN_KUAI)) {
            if (zqdm.startsWith(HTTP_KLINE_SECID_PREFIX_BANKUAI)) {//板块
                url.append(zqdm);//secid: 90.BK0438
            } else {
                url.append(HTTP_KLINE_SECID_PREFIX_BANKUAI + zqdm);
            }
        } else if (klineType.equals(KLINE_TYPE_ETF)) {
            //指数 zqdm.startsWith("159")
            if (zqdm.startsWith("159")) {
                url.append("0." + zqdm);
            } else {
                //5开头
                url.append("1." + zqdm);
            }
        } else {
            url.append(zqdm);
        }


        url.append("&ut=fa5fd1943c7b386f172d6893dbfba10b");
        url.append("&fields1=f1,f2,f3,f4,f5,f6");
//        url.append("&fields1=");
//        url.append("f1,f2,f3,f4,f5,f6,f7,f8,f9,");
//        url.append("f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,");
//        url.append("f20,f21,f22,f23,f24,f25,f26,f27,f28,f29,");
//        url.append("f30,f31,f32,f33,f34,f35,f36,f37,f38,f39,");
//        url.append("f40,f41,f42,f43,f44,f45,f46,f47,f48,f49,");
//        url.append("f50,f51,f52,f53,f54,f55,f56,f57,f58,f59,");
//        url.append("f60,f61,f62,f63,f64,f65,f66,f67,f68,f69,");
//        url.append("f70,f71,f72,f73,f74,f75,f76,f77,f78,f79,");
//        url.append("f80,f81,f82,f83,f84,f85,f86,f87,f88,f89,");
//        url.append("f90,f91,f92,f93,f94,f95,f96,f97,f98,f99");

        url.append("&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61");/**     f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61  日期，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率 **/
//        url.append("&fields2=");
//        url.append("f1,f2,f3,f4,f5,f6,f7,f8,f9,");
//        url.append("f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,");
//        url.append("f20,f21,f22,f23,f24,f25,f26,f27,f28,f29,");
//        url.append("f30,f31,f32,f33,f34,f35,f36,f37,f38,f39,");
//        url.append("f40,f41,f42,f43,f44,f45,f46,f47,f48,f49,");
//        url.append("f50,f51,f52,f53,f54,f55,f56,f57,f58,f59,");
//        url.append("f60,f61,f62,f63,f64,f65,f66,f67,f68,f69,");
//        url.append("f70,f71,f72,f73,f74,f75,f76,f77,f78,f79,");
//        url.append("f80,f81,f82,f83,f84,f85,f86,f87,f88,f89,");
//        url.append("f90,f91,f92,f93,f94,f95,f96,f97,f98,f99,");
//        url.append("f100,f101,f102,f103,f104,f105,f106,f107,f108,f109,");
//        url.append("f110,f111,f112,f113,f114,f115,f116,f117,f118,f119,");
//        url.append("f120,f121,f122,f123,f124,f125,f126,f127,f128,f129,");
//        url.append("f130,f131,f132,f133,f134,f135,f136,f137,f138,f139,");
//        url.append("f140,f141,f142,f143,f144,f145,f146,f147,f148,f149,");
//        url.append("f150,f151,f152,f153,f154,f155,f156,f157,f158,f159,");
//        url.append("f160,f161,f162,f163,f164,f165,f166,f167,f168,f169,");
//        url.append("f170,f171,f172,f173,f174,f175,f176,f177,f178,f179,");
//        url.append("f180,f181,f182,f183,f184,f185,f186,f187,f188,f189,");
//        url.append("f190,f191,f192,f193,f194,f195,f196,f197,f198,f199,");
//        url.append("f200,f201,f202,f203,f204,f205,f206,f207,f208,f209,");
//        url.append("f210,f211,f212,f213,f214,f215,f216,f217,f218,f219,");
//        url.append("f220,f221,f222,f223,f224,f225,f226,f227,f228,f229");
//        url.append("f230,f231,f232,f233,f234,f235,f236,f237,f238,f239");

        url.append("&klt=" + klt);
        url.append("&fqt=1");
        if (isHasBegDate) {
            //是否有开始时间
            url.append("&beg=" + begDate);

        }
        url.append("&end=" + endDate);
        url.append("&lmt=" + lmt);
        url.append("&_=" + curTime);

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("&StartDate=").append(startDate);

//        System.out.println("请求url:" + url + JSON.toJSONString(urlParam));
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

        //jQuery33103254362175743777_1629614861227({"rc":0,"rt":17,"svr":181669733,"lt":1,"full":0,"data":{"code":"000300","market":1,"name":"沪深300","decimal":2,"dktotal":4044,"preKPrice":0.0,"klines":[]}});
        rs = rs.substring(rs.indexOf("({"));
        rs = rs.replace("({", "{");
        rs = rs.replace("});", "}");
//        System.out.println("szKline:" + rs);

        return rs;
    }

    /**
     * 查询最小净值、最大净值
     *
     * @return
     */
    private static Map<String, BigDecimal> findNetMinMax(String zqdm, int lmt, String klt, Boolean isHasBegDate, String begDate, String endDate) {
        List<Kline> klines = KlineService.kline(zqdm, lmt, klt, isHasBegDate, begDate, endDate, KLINE_TYPE_STOCK);
        Map<String, BigDecimal> rs = new HashMap<>();
        BigDecimal rsMax = new BigDecimal("0.0");
        BigDecimal rsMin = new BigDecimal("0.0");
        BigDecimal rsNetCloseMin = new BigDecimal("0.0");
        BigDecimal rsNetCloseMax = new BigDecimal("0.0");
        for (Kline kline : klines) {
            BigDecimal dwjzLong = kline.getCloseAmt();
            BigDecimal netMinDou = kline.getMinAmt();
            BigDecimal netMaxDou = kline.getMaxAmt();
            if (netMaxDou.compareTo(rsMax) > 0) {
                rsMax = netMaxDou;
            }
            if (netMinDou.compareTo(rsMin) <= 0 || rsMin.compareTo(new BigDecimal("0.0")) == 0) {
                rsMin = netMinDou;
            }

            //
            if (dwjzLong.compareTo(rsNetCloseMax) > 0) {
                rsNetCloseMax = dwjzLong;
            }
            if (dwjzLong.compareTo(rsNetCloseMin) <= 0 || rsNetCloseMin.compareTo(new BigDecimal("0.0")) == 0) {
                rsNetCloseMin = dwjzLong;
            }
        }
        rs.put(Content.keyRsMin, rsMin);
        rs.put(Content.keyRsMax, rsMax);
        rs.put(Content.keyRsNetCloseMin, rsNetCloseMin);
        rs.put(Content.keyRsNetCloseMax, rsNetCloseMax);
        return rs;
    }

    /**
     * 查询最小净值、最大净值、均值
     *
     * @param zqdm
     * @param lmt
     * @param klt
     * @param isHasBegDate
     * @param begDate
     * @param endDate
     * @return
     */
    public static Map<String, BigDecimal> findNetMinMaxAvg(String zqdm, int lmt, String klt, Boolean isHasBegDate, String begDate, String endDate, String klineType) {
        List<Kline> klines = KlineService.kline(zqdm, lmt, klt, isHasBegDate, begDate, endDate, klineType);
        Map<String, BigDecimal> rs = new HashMap<>();
        BigDecimal rsMax = new BigDecimal("0.0");
        BigDecimal rsMin = new BigDecimal("0.0");
        BigDecimal rsNetCloseMin = new BigDecimal("0.0");
        BigDecimal rsNetCloseMax = new BigDecimal("0.0");
        BigDecimal rsNetCloseAvg = new BigDecimal("0");//均值
        BigDecimal rsNetCloseSum = new BigDecimal("0");//和值

        for (Kline kline : klines) {
            BigDecimal dwjzLong = kline.getCloseAmt();
            BigDecimal netMinDou = kline.getMinAmt();
            BigDecimal netMaxDou = kline.getMaxAmt();
            rsNetCloseSum = rsNetCloseSum.add(dwjzLong);
            if (netMaxDou.compareTo(rsMax) > 0) {
                rsMax = netMaxDou;
            }
            if (netMinDou.compareTo(rsMin) <= 0 || rsMin.compareTo(new BigDecimal("0.0")) == 0) {
                rsMin = netMinDou;
            }

            //
            if (dwjzLong.compareTo(rsNetCloseMax) > 0) {
                rsNetCloseMax = dwjzLong;
            }
            if (dwjzLong.compareTo(rsNetCloseMin) <= 0 || rsNetCloseMin.compareTo(new BigDecimal("0.0")) == 0) {
                rsNetCloseMin = dwjzLong;
            }
        }

        //计算均值
        BigDecimal count = new BigDecimal(klines.size());//个数
        if (count.compareTo(new BigDecimal("0")) <= 0) {
            rsNetCloseAvg = new BigDecimal("0");
        } else {
            rsNetCloseAvg = rsNetCloseSum.divide(count, 3, BigDecimal.ROUND_HALF_UP);
        }

        rs.put(Content.keyRsMin, rsMin);
        rs.put(Content.keyRsMax, rsMax);
        rs.put(Content.keyRsNetCloseMin, rsNetCloseMin);
        rs.put(Content.keyRsNetCloseMax, rsNetCloseMax);
        rs.put(Content.keyRsNetCloseAvg, rsNetCloseAvg);
        rs.put(Content.keyRsKlineCount, count);
        return rs;
    }

    /**
     * 计算最小净值、最大净值、均值、均量
     *
     * @param maType 均线类型
     * @return
     */
    public static Map<String, BigDecimal> handlerNetMinMaxAvg(int maType, List<Kline> klines) {
        Map<String, BigDecimal> rs = new HashMap<>();
        BigDecimal rsMax = new BigDecimal("0.0");
        BigDecimal rsMin = new BigDecimal("0.0");
        BigDecimal rsNetCloseMin = new BigDecimal("0.0");
        BigDecimal rsNetCloseMax = new BigDecimal("0.0");
        BigDecimal rsNetCloseAvg = new BigDecimal("0");//均值
        BigDecimal rsNetCloseSum = new BigDecimal("0");//和值
        BigDecimal rsCjeSum = new BigDecimal("0");//和值-交易额
        BigDecimal rsCjegAvg = new BigDecimal("0");//均量-交易额
        int count = maType;
        for (Kline kline : klines) {
            count--;
            if (count < 0) {
                break;//限定计算
            }
            BigDecimal dwjzLong = kline.getCloseAmt();
            BigDecimal netMinDou = kline.getMinAmt();
            BigDecimal netMaxDou = kline.getMaxAmt();
            BigDecimal cje = kline.getCje();
            rsNetCloseSum = rsNetCloseSum.add(dwjzLong);
            rsCjeSum = rsCjeSum.add(cje);
            if (netMaxDou.compareTo(rsMax) > 0) {
                rsMax = netMaxDou;
            }
            if (netMinDou.compareTo(rsMin) <= 0 || rsMin.compareTo(new BigDecimal("0.0")) == 0) {
                rsMin = netMinDou;
            }

            //
            if (dwjzLong.compareTo(rsNetCloseMax) > 0) {
                rsNetCloseMax = dwjzLong;
            }
            if (dwjzLong.compareTo(rsNetCloseMin) <= 0 || rsNetCloseMin.compareTo(new BigDecimal("0.0")) == 0) {
                rsNetCloseMin = dwjzLong;
            }
        }

        //计算均值
        BigDecimal klineCount = new BigDecimal(klines.size());//个数
        rsNetCloseAvg = rsNetCloseSum.divide(new BigDecimal(maType), 2, BigDecimal.ROUND_HALF_UP);
        rsCjegAvg = rsCjeSum.divide(new BigDecimal(maType), 2, BigDecimal.ROUND_HALF_UP);

        rs.put(Content.keyRsMin, rsMin);
        rs.put(Content.keyRsMax, rsMax);
        rs.put(Content.keyRsNetCloseMin, rsNetCloseMin);
        rs.put(Content.keyRsNetCloseMax, rsNetCloseMax);
        rs.put(Content.keyRsNetCloseAvg, rsNetCloseAvg);
        rs.put(Content.keyRsCjeAvg, rsCjegAvg);
        rs.put(Content.keyRsKlineCount, new BigDecimal(maType));
        return rs;
    }

}
