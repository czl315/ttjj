package ttjj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.KlineDao;
import ttjj.dao.RankStockCommpanyDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.*;
import ttjj.rank.StockDemo;
import utils.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static utils.Content.*;
import static utils.ContentUrl.KLINE_HTTP_HEAD;
import static utils.DateUtil.HH_MM_SS;

/**
 * @author chenzhilong
 * @date 2021/7/26
 */
public class KlineService {


    public static void main(String[] args) {

        //比较两个指数：上证50，三倍做空FTSE中国ETF
        a50CompareYang();


//        findNetMinMaxAvgDemo();// 查询最小净值、最大净值、均值
    }

    /**
     * 插入k线
     *
     * @param entity
     * @return
     */
    public static Integer insert(Kline entity) {
        Integer rs = 0;
        if (entity == null) {
            return rs;
        }
        /**
         * 插入数据库-K线
         */
        return KlineDao.insert(entity);
    }

    /**
     * 删除-根据条件
     *
     * @param condition 条件
     * @return 删除个数
     */
    public static Integer deleteByCondition(Kline condition) {
        return KlineDao.deleteByCondition(condition);
    }

    /**
     * 比较两个指数：上证50，三倍做空FTSE中国ETF
     */
    private static void a50CompareYang() {
        String code1 = "107.YANG";
        String code2 = "000001";//000016 上证50;  000001:上证指数
        String date = "2022-05-06";
        String date2 = "";
        List<String> dateListBefore = RankStockCommpanyDao.findListDateAfter(new DateCond(date, 1));
        if (dateListBefore != null && dateListBefore.size() > 0) {
            date2 = dateListBefore.get(0);
        }
        List<Kline> klines = KlineService.kline(code1, 1, KLT_101, true, date, date, "");
        for (Kline kline : klines) {
//            System.out.println(JSON.toJSONString(kline));
            System.out.println(code1 + "[" + date + "]:" + kline.getZhangDieFu().divide(new BigDecimal("-3"), 2, BigDecimal.ROUND_HALF_UP));
            if (StringUtils.isNotBlank(date2)) {
                List<Kline> klines2 = KlineService.kline(code2, 1, KLT_101, true, date2, date2, "");
                if (klines2 != null) {
                    System.out.println(code2 + "[" + date2 + "]:" + klines2.get(0).getZhangDieFu());
                }
            }

        }
    }

    /**
     * 测试样例：查询最小净值、最大净值、均值
     */
    private static void findNetMinMaxAvgDemo() {
        String zqdm = "113630";//Content.ZQDM_ETF_CYB50_159949
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
//                kline.setRs(klineString);
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
//                kline.setRs(rs);

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
        //http://76.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery33106379847099350968_1624766355746&secid=0.399673&ut=fa5fd1943c7b386f172d6893dbfba10b&fields1=f1,f2,f3,f4,f5,f6&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61&klt=101&fqt=1&beg=0&end=20500101&smplmt=1390.59&lmt=1000000&_=1624766355750

        StringBuffer url = new StringBuffer();
//        url.append(jqueryHttpHead);
        url.append("&secid=");

        if (klineType == null || klineType.equals(KLINE_TYPE_STOCK) || StringUtils.isBlank(klineType)) {
            if (zqdm.startsWith("00") || zqdm.startsWith("12") || zqdm.startsWith("13") || zqdm.startsWith("16") || zqdm.startsWith("20") || zqdm.startsWith("30") || zqdm.startsWith("159")) {
                url.append("0." + zqdm);
//                16XXXX：深交所LOF基金：16打头(前两位均用“16”标识，中间两位为中国证监会信息中心统一规定的基金管理公司代码gg，后两位为该公司发行全部开放式基金的顺序号xx。具体表示为“16ggxx”)
                //指数 zqdm.startsWith("159") || zqdm.startsWith("399") ||
                //159开头
                //  11XXXX：债券；12XXXX：可转换债券；13XXXX：国债回购
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
            } else if (zqdm.startsWith("107.")) {
                url.append(zqdm);//美股
            } else if (zqdm.startsWith(HTTP_KLINE_SECID_PREFIX_BANKUAI) || zqdm.startsWith(HTTP_KLINE_TYPE_BK_REFIX)) {//板块
                if (zqdm.startsWith(HTTP_KLINE_SECID_PREFIX_BANKUAI)) {//板块
                    url.append(zqdm);//secid: 90.BK0438
                } else {
                    url.append(HTTP_KLINE_SECID_PREFIX_BANKUAI + zqdm);
                }
            } else if (zqdm.startsWith("159")) {//ETF
                url.append("0." + zqdm);
            } else {
//                || zqdm.startsWith("11")
                //zhiShu.startsWith("5") || zhiShu.startsWith("6") || zhiShu.startsWith("000")|| zhiShu.startsWith("11")|| zhiShu.startsWith("12")
                url.append("1." + zqdm);
            }
        } else if (klineType.equals(KLINE_TYPE_INDEX)) {
            if (zqdm.startsWith("000") || zqdm.startsWith("5") || zqdm.startsWith("6") || zqdm.startsWith("11") || zqdm.startsWith("12")) {
                //zhiShu.startsWith("5") || zhiShu.startsWith("6") || zhiShu.startsWith("000")|| zhiShu.startsWith("11")|| zhiShu.startsWith("12")
                url.append("1." + zqdm);
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
        } else if (klineType.equals(KLINE_TYPE_ETF) || zqdm.startsWith("16")) {
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
        String httpHead = KLINE_HTTP_HEAD;
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
        BigDecimal rsNetClose = new BigDecimal("0");//收盘价
        if (klines == null) {
            System.out.println("k线为空！");
            return rs;
        }

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
            rsNetClose = dwjzLong;
        }

        //计算均值
        BigDecimal count = new BigDecimal(klines.size());//个数
        if (count.compareTo(new BigDecimal("0")) <= 0) {
            rsNetCloseAvg = new BigDecimal("0");
        } else {
            rsNetCloseAvg = rsNetCloseSum.divide(count, 3, BigDecimal.ROUND_HALF_UP);
        }

//        //获取证券名称
//        String zqmc = "";
//        if (klines.size() > 0) {
//             zqmc = klines.get(0).getZqmc();
//        }

        rs.put(Content.keyRsMin, rsMin);
        rs.put(Content.keyRsMax, rsMax);
        rs.put(Content.keyRsNetCloseMin, rsNetCloseMin);
        rs.put(Content.keyRsNetCloseMax, rsNetCloseMax);
        rs.put(Content.keyRsNetCloseAvg, rsNetCloseAvg);
        rs.put(Content.keyRsNetClose, rsNetClose);
        rs.put(Content.keyRsKlineCount, count);
//        rs.put(Content.keyRsKlineZqmc, zqmc);
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

    /**
     * 查询价格区间百分比
     */
    public static BigDecimal handlerPriceAreaRate(String zqdm, int lmt, String klt, Boolean isHasBegDate, String begDate, String endDate, String klineType) {
        BigDecimal curPriceArea = null;
        Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, lmt, klt, isHasBegDate, begDate, endDate, klineType);
        BigDecimal curPrice = netMap.get(keyRsNetClose);
        BigDecimal minPrice = netMap.get(keyRsMin);
        BigDecimal maxPrice = netMap.get(keyRsMax);
        if (curPrice != null && minPrice != null && maxPrice != null && maxPrice.compareTo(new BigDecimal("0")) != 0) {
            curPriceArea = curPrice.subtract(minPrice).divide(maxPrice.subtract(minPrice), 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return curPriceArea;
    }


    /**
     * 获取均线数据
     *
     * @param strHead
     * @param netMap
     * @return
     */
    public static String handlerAvgLine(String strHead, Map<String, BigDecimal> netMap) {
        BigDecimal curPrice = netMap.get(keyRsNetClose);
        BigDecimal minPrice = netMap.get(keyRsMin);
        BigDecimal maxPrice = netMap.get(keyRsMax);
        StringBuffer sb = new StringBuffer();
        if (curPrice != null && minPrice != null && maxPrice != null && maxPrice.compareTo(new BigDecimal("0")) != 0) {
            BigDecimal curPriceArea = curPrice.subtract(minPrice).divide(maxPrice.subtract(minPrice), 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
//            sb.append(strHead).append("区间：").append("\t").append(curPriceArea).append("%").append(",");
            sb.append(strHead).append(curPriceArea).append("\t\t");
        }
//        sb.append("\t").append(strHead).append("：").append("\t").append(netMap.get(keyRsNetCloseAvg));
//        sb.append("\t").append(",最低：").append("\t").append(minPrice);
//        sb.append("\t").append(",最高：").append("\t").append(maxPrice);
//        sb.append("\t").append(",当前价：").append(curPrice);

        return sb.toString();
    }

    /**
     * 检查均线
     *
     * @param zqMap
     * @param spDate
     */
    public static void checkMaDemo(Map<String, String> zqMap, String date, String spDate) {
//        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);//                        String date = "2022-02-15";
        boolean isUp = true;//检查上涨
        boolean isDown = true;//检查下跌
//        boolean isUp = false;

        List<Integer> maList = new ArrayList<>();
//        maList.add(MA_30);
        maList.add(MA_60);

//        KlineService.checkMa(zqMap, KLT_5, maList, date, isUp);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
        KlineService.checkMa(zqMap, KLT_15, maList, date, isUp, isDown, spDate, false);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
        KlineService.checkMa(zqMap, KLT_30, maList, date, isUp, isDown, spDate, false);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
        KlineService.checkMa(zqMap, KLT_60, maList, date, isUp, isDown, spDate, false);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
        KlineService.checkMa(zqMap, KLT_101, maList, date, isUp, isDown, spDate, false);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
        KlineService.checkMa(zqMap, KLT_102, maList, date, isUp, isDown, spDate, false);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
    }

    /**
     * 检查均线
     *
     * @param etfBizMap   etf列表
     * @param klt         均线类型
     * @param maList      均线列表
     * @param date
     * @param isUp
     * @param isCheckDown 是否检查下跌
     * @param spDate
     * @param isShow      是否显示结果
     */
    public static String checkMa(Map<String, String> etfBizMap, String klt, List<Integer> maList, String date, boolean isUp, boolean isCheckDown, String spDate, boolean isShow) {
        StringBuffer sbMa = new StringBuffer();
        for (String zqdm : etfBizMap.keySet()) {
            String zqmc = etfBizMap.get(zqdm);
            // 查询今日价格
            List<Kline> klines = KlineService.kline(zqdm, 1, KLT_101, true, date, date, "");
            if (klines == null || klines.size() == 0) {
                StringBuffer sbError = new StringBuffer();
                sbError.append(zqdm).append("，").append(zqmc).append(":k线异常！");
                System.out.println(sbError);
                continue;
            }
            Kline todayKline = klines.get(0);
            BigDecimal curAmt = todayKline.getCloseAmt();
            BigDecimal yesterdayCloseAmt = todayKline.getCloseLastAmt();
//            BigDecimal openAmt = todayKline.getOpenAmt();
//            BigDecimal zhangDieFu = todayKline.getZhangDieFu();

            StringBuffer sbDay = new StringBuffer();
//            sbDay.append("\t").append(zqdm).append("，");
//            sbDay.append("\t").append(EtfUtil.handlerEtfName(zqmc));
//            sbDay.append("，[" + date + "]");
//            sbDay.append("涨幅：").append(zhangDieFu).append("%").append("\t");
            //查询特定日期涨跌幅
            if (spDate != null) {
                List<Kline> klinesSpDate = KlineService.kline(zqdm, 1, KLT_101, true, spDate, spDate, "");
                if (klinesSpDate != null && klinesSpDate.size() != 0) {
                    Kline todayKlineSpDate = klinesSpDate.get(0);
                    sbDay.append("，特定日期[" + spDate + "]涨幅：").append(todayKlineSpDate.getZhangDieFu()).append("%").append("\t");
                }
            }
//            sbDay.append("，日期：").append(date);
//            sbDay.append("，昨日收盘价：").append(yesterdayCloseAmt);
//            sbDay.append("，开盘价：").append(openAmt);
//            sbDay.append("，当前价：").append(curAmt);
//            System.out.println(sbDay);
            for (Integer maType : maList) {
                Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, maType, klt, false, "", date, "");
                BigDecimal curMaAmt = netMap.get(Content.keyRsNetCloseAvg);
//                sbMa.append("均线周期：");
//                sbMa.append(klt + "(" + maType + ")");
//                sbMa.append("\t");
//                sbMa.append(",均线价格：" + curMaAmt);
//                if (openAmt.compareTo(curMaAmt) >= 0) {
//                    sbMa.append("，开盘价高于均线");
//                } else {
//                    sbMa.append("，开盘价低于均线");
//                }
//                if (curAmt.compareTo(curMaAmt) >= 0) {
//                    sbMa.append("，当前价高于均线");
//                } else {
//                    sbMa.append("，当前价低于均线");
//                }
                //跌破均线，卖出信号
//                if (yesterdayCloseAmt.compareTo(curMaAmt) >= 0 && curAmt.compareTo(curMaAmt) < 0) {
//                    sbMa.append("，开盘价高于均线但是当前价跌破均线，卖出信号！！！");
//                }
                //涨破均线，买出信号
                if (isUp && yesterdayCloseAmt.compareTo(curMaAmt) < 0 && curAmt.compareTo(curMaAmt) >= 0) {
//                    sbMa.append("，昨日价低于均线但是当前价涨破均线，买入信号！！！！！！");
//                    sbMa.append(klt).append("(").append(maType).append(")");
                    sbMa.append(klt);
                }
                if (isCheckDown && yesterdayCloseAmt.compareTo(curMaAmt) >= 0 && curAmt.compareTo(curMaAmt) < 0) {
//                    sbMa.append("，昨日价高于均线但是当前价跌破均线，卖出信号！！！");
//                    sbMa.append("-");
//                    sbMa.append(klt).append("(").append(maType).append(")");
                    sbMa.append(klt);
                }
            }
        }
        return sbMa.toString();
    }

    /**
     * 显示涨幅-根据日期
     *
     * @param date  查询日期
     * @param stock stock
     */
    public static BigDecimal showDateF3(String date, RankStockCommpanyDb stock) {
        BigDecimal rs = null;
        String zqdm = stock.getF12();

        // 查询今日价格
        List<Kline> klines = KlineService.kline(zqdm, 1, KLT_101, true, date, date, "");
        if (klines == null || klines.size() == 0) {
            StringBuffer sbError = new StringBuffer();
            sbError.append(zqdm).append("，").append(zqdm).append(":k线异常！");
            System.out.println(sbError);
            return rs;
        }
        Kline todayKline = klines.get(0);
        rs = todayKline.getZhangDieFu();

//            sbDay.append("\t").append(zqdm).append("，");
//            sbDay.append("\t").append(EtfUtil.handlerEtfName(zqmc));
//        rs.append("[" + date + "]");
//        rs.append("涨幅：").append(zhangDieFu).append("\t");
        return rs;
    }

    /**
     * 查询k线
     *
     * @param stock
     * @param date
     * @param klt
     * @return
     */
    public static Kline findLast(RankStockCommpanyDb stock, String date, String klt) {
        String zqdm = stock.getF12();
        // 查询今日
        List<Kline> klines = KlineService.kline(zqdm, 1, klt, false, "", date, "");
        if (klines == null || klines.size() == 0) {
            System.out.println(new StringBuffer().append(zqdm).append("，").append(zqdm).append(":k线异常！"));
            return null;
        }
        return klines.get(0);
    }

    /**
     * @param stock
     * @param klt
     * @param maList
     * @param date
     * @param isUp
     * @return
     */
    public static boolean showUpMa(RankStockCommpanyDb stock, String klt, List<Integer> maList, String date, boolean isUp) {
        boolean isUpMa = false;
        String zqmc = stock.getF14();
        String zqdm = stock.getF12();
        ;
        // 查询今日价格
        List<Kline> klines = KlineService.kline(zqdm, 1, KLT_101, true, date, date, "");
        if (klines == null || klines.size() == 0) {
//            System.out.println(new StringBuffer().append(zqdm).append("，").append(zqmc).append(":k线null！"));
            return isUpMa;
        }
        Kline todayKline = klines.get(0);
        BigDecimal curAmt = todayKline.getCloseAmt();
        BigDecimal yesterdayCloseAmt = todayKline.getCloseLastAmt();

        for (Integer maType : maList) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, maType, klt, false, "", date, "");
            BigDecimal curMaAmt = netMap.get(Content.keyRsNetCloseAvg);
//                sbMa.append("均线周期：");

//                sbMa.append(",均线价格：" + curMaAmt);
//                if (openAmt.compareTo(curMaAmt) >= 0) {
//                    sbMa.append("，开盘价高于均线");
//                } else {
//                    sbMa.append("，开盘价低于均线");
//                }
//                if (curAmt.compareTo(curMaAmt) >= 0) {
//                    sbMa.append("，当前价高于均线");
//                } else {
//                    sbMa.append("，当前价低于均线");
//                }
            //跌破均线，卖出信号
//                if (yesterdayCloseAmt.compareTo(curMaAmt) >= 0 && curAmt.compareTo(curMaAmt) < 0) {
//                    sbMa.append("，开盘价高于均线但是当前价跌破均线，卖出信号！！！");
//                }
            //涨破均线，买出信号
            if (isUp && yesterdayCloseAmt.compareTo(curMaAmt) < 0 && curAmt.compareTo(curMaAmt) >= 0) {
                isUpMa = true;
//                sbMa.append("(" + maType + ")");
//                sbMa.append("\t");
//                    sbMa.append("，昨日价低于均线但是当前价涨破均线，买入信号！！！！！！");
//                    System.out.print(KlineService.handlerAvgLine("5日:", KlineService.findNetMinMaxAvg(zqdm, MA_5, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
//                    System.out.print(KlineService.handlerAvgLine("10日:", KlineService.findNetMinMaxAvg(zqdm, MA_10, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
//                    System.out.print(KlineService.handlerAvgLine("20日:", KlineService.findNetMinMaxAvg(zqdm, MA_20, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
//                    System.out.print(KlineService.handlerAvgLine("60日:", KlineService.findNetMinMaxAvg(zqdm, MA_60, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
//                System.out.print(sbMa);
//                    System.out.println(KlineService.handlerAvgLine("120日价格", KlineService.findNetMinMaxAvg(zqdm, MA_120, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
//                    System.out.println(KlineService.handlerAvgLine("250日价格", KlineService.findNetMinMaxAvg(zqdm, MA_250, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
//                    System.out.println();
            }
            if (!isUp && yesterdayCloseAmt.compareTo(curMaAmt) >= 0 && curAmt.compareTo(curMaAmt) < 0) {
//                sbMa.append("，昨日价高于均线但是当前价跌破均线，卖出信号！！！");
                System.out.println(isUpMa);
                System.out.print(KlineService.handlerAvgLine("5日", KlineService.findNetMinMaxAvg(zqdm, MA_5, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
                System.out.print(KlineService.handlerAvgLine("10日", KlineService.findNetMinMaxAvg(zqdm, MA_10, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
                System.out.print(KlineService.handlerAvgLine("20日", KlineService.findNetMinMaxAvg(zqdm, MA_20, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
                System.out.print(KlineService.handlerAvgLine("60日", KlineService.findNetMinMaxAvg(zqdm, MA_60, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
                System.out.println();
            }
        }

        return isUpMa;
    }

    /**
     * 获取-secid
     *
     * @return
     */
    public static String getSecid(String zqdm, String type) {
        StringBuffer sb = new StringBuffer();
        if (DB_RANK_BIZ_TYPE_ZS.equals(type)) {
            // 指数
            if (zqdm.startsWith("399")) {
                sb.append("0." + zqdm);
            } else {
                sb.append("1." + zqdm);
            }
            return sb.toString();
        }
        if (zqdm.startsWith("00") || zqdm.startsWith("12") || zqdm.startsWith("13") || zqdm.startsWith("16") || zqdm.startsWith("20") || zqdm.startsWith("30") || zqdm.startsWith("159")) {
            sb.append("0." + zqdm);
        } else if (zqdm.startsWith(HTTP_KLINE_SECID_PREFIX_BANKUAI) || zqdm.startsWith(HTTP_KLINE_TYPE_BK_REFIX)) {//板块
            //secid: 90.BK0438
            if (zqdm.startsWith(HTTP_KLINE_SECID_PREFIX_BANKUAI)) {//板块
                sb.append(zqdm);
            } else {
                sb.append(HTTP_KLINE_SECID_PREFIX_BANKUAI + zqdm);
            }
        } else if (zqdm.startsWith("107.")) {
            sb.append(zqdm);//美股
        } else {
            sb.append("1." + zqdm);
        }
        return sb.toString();
    }

    /**
     * 更新
     *
     * @param entity 更新内容和条件
     * @return 结果
     */
    public static Integer update(Kline entity) {
        return KlineDao.updateNet(entity);
    }

    /**
     * 检查均线
     *
     * @param etfBizMap       证券列表
     * @param date            日期
     * @param isShowPriceArea 是否显示价格区间
     * @param isShowUpMa      是否显示超过均线
     * @param isShowDownMa    是否显示跌落均线
     * @param isFindKline     查询当日k线
     * @param kltList         周期列表
     */
    public static List<StockAdrCountVo> checkMaDemo(Map<String, String> etfBizMap, String date, boolean isShowPriceArea, boolean isShowUpMa, boolean isShowDownMa, boolean isFindKline, List<String> kltList) {
        List<StockAdrCountVo> rs = new ArrayList<>();
        boolean isUp = true;//检查上涨
//        boolean isUp = false;

        List<Integer> maList = new ArrayList<>();
//        maList.add(MA_30);
        maList.add(MA_60);

        for (String zqdm : etfBizMap.keySet()) {
            StockAdrCountVo stockAdrCountVo = new StockAdrCountVo();
            String zqmc = etfBizMap.get(zqdm);
            Map<String, String> etfBizMapSub = new HashMap<>();
            etfBizMapSub.put(zqdm, zqmc);
            stockAdrCountVo.setF12(zqdm);
            stockAdrCountVo.setF14(zqmc);


            RankStockCommpanyDb stock = new RankStockCommpanyDb();
            stock.setF12(zqdm);
            //净值
            if (isShowPriceArea) {
                StringBuffer sbPriceArea = new StringBuffer();
                Map<String, Boolean> maUpdateMap = new HashMap<>();
                StockDemo.setMaMapType(MA_TYPE_DAY, maUpdateMap);
                StockDemo.handlerNetMa(stock, maUpdateMap, date, sbPriceArea, stockAdrCountVo);//处理均线净值
            }

            if (isShowUpMa) {
                boolean isCheckMaDown = false;
                for (String klt : kltList) {
                    if (KLT_5.equals(klt)) {
                        String upMa5 = KlineService.checkMa(etfBizMapSub, KLT_5, maList, date, isUp, isCheckMaDown, null, false);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
                        stockAdrCountVo.setUpMaDay5(upMa5);
                    }
                    if (KLT_15.equals(klt)) {
                        String upMa15 = KlineService.checkMa(etfBizMapSub, KLT_15, maList, date, isUp, isCheckMaDown, null, false);
                        stockAdrCountVo.setUpMaDay15(upMa15);
                    }
                    if (KLT_30.equals(klt)) {
                        String upMa30 = KlineService.checkMa(etfBizMapSub, KLT_30, maList, date, isUp, isCheckMaDown, null, false);
                        stockAdrCountVo.setUpMaDay30(upMa30);
                    }
                    if (KLT_60.equals(klt)) {
                        String upMa60 = KlineService.checkMa(etfBizMapSub, KLT_60, maList, date, isUp, isCheckMaDown, null, false);
                        stockAdrCountVo.setUpMaDay60(upMa60);
                    }
                    if (KLT_101.equals(klt)) {
                        String upMa101 = KlineService.checkMa(etfBizMapSub, KLT_101, maList, date, isUp, isCheckMaDown, null, false);
                        stockAdrCountVo.setUpMaDay101(upMa101);
                    }
                    if (KLT_102.equals(klt)) {
                        String upMa102 = KlineService.checkMa(etfBizMapSub, KLT_102, maList, date, isUp, isCheckMaDown, null, false);
                        stockAdrCountVo.setUpMaDay102(upMa102);
                    }
                }


            }

            //是否显示跌落均线
            if (isShowDownMa) {
                boolean isCheckMaUp = false;
                for (String klt : kltList) {
                    if (KLT_5.equals(klt)) {
                        String upMa5 = KlineService.checkMa(etfBizMapSub, KLT_5, maList, date, isCheckMaUp, isShowDownMa, null, false);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
                        stockAdrCountVo.setMaDownDay5(upMa5);
                    }
                    if (KLT_15.equals(klt)) {
                        String upMa15 = KlineService.checkMa(etfBizMapSub, KLT_15, maList, date, isCheckMaUp, isShowDownMa, null, false);
                        stockAdrCountVo.setMaDownDay15(upMa15);
                    }
                    if (KLT_30.equals(klt)) {
                        String upMa30 = KlineService.checkMa(etfBizMapSub, KLT_30, maList, date, isCheckMaUp, isShowDownMa, null, false);
                        stockAdrCountVo.setMaDownDay30(upMa30);
                    }
                    if (KLT_60.equals(klt)) {
                        String upMa60 = KlineService.checkMa(etfBizMapSub, KLT_60, maList, date, isCheckMaUp, isShowDownMa, null, false);
                        stockAdrCountVo.setMaDownDay60(upMa60);
                    }
                    if (KLT_101.equals(klt)) {
                        String upMa101 = KlineService.checkMa(etfBizMapSub, KLT_101, maList, date, isCheckMaUp, isShowDownMa, null, false);
                        stockAdrCountVo.setMaDownDay101(upMa101);
                    }
                    if (KLT_102.equals(klt)) {
                        String upMa102 = KlineService.checkMa(etfBizMapSub, KLT_102, maList, date, isCheckMaUp, isShowDownMa, null, false);
                        stockAdrCountVo.setMaDownDay102(upMa102);
                    }
                }


            }

            if (isFindKline) {
                //实时查询，http
                Kline kline = KlineService.findLast(stock, date, KLT_101);
                if (kline != null) {
                    BigDecimal curAmt = kline.getCloseAmt();
                    BigDecimal maxAmt = kline.getMaxAmt();
                    BigDecimal minAmt = kline.getMinAmt();
                    stockAdrCountVo.setDate(date);
                    stockAdrCountVo.setF3(kline.getZhangDieFu());
                    stockAdrCountVo.setF2(curAmt);
                    stockAdrCountVo.setF15(maxAmt);
                    stockAdrCountVo.setF16(minAmt);
                    if (curAmt != null && curAmt.compareTo(new BigDecimal("0")) > 0) {
                        BigDecimal maxDown = maxAmt.subtract(curAmt).divide(curAmt, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
                        BigDecimal minRise = curAmt.subtract(minAmt).divide(minAmt, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
                        stockAdrCountVo.setMaxDown(maxDown);
                        stockAdrCountVo.setMinRise(minRise);
                    }
                }

                //从数据库查询：市值
                RankStockCommpanyDb stockCondition = new RankStockCommpanyDb();
                stockCondition.setDate(date);
                stockCondition.setF12(zqdm);
                RankStockCommpanyDb stockToday = RankStockCommpanyDao.find(stockCondition);
                if (stockToday != null) {
                    stockAdrCountVo.setF20(stockToday.getF20());
                    stockAdrCountVo.setF21(stockToday.getF21());
                    stockAdrCountVo.setF62(stockToday.getF62());
                } else {
                    RankBizDataDiff biz = BizService.findBiz(zqdm, date, null);
                    if (biz != null) {
                        stockAdrCountVo.setF20(biz.getF20());
                        stockAdrCountVo.setF21(biz.getF21());
                        stockAdrCountVo.setF62(biz.getF62());
                    }
                }

                //查询主力净流入(实时查询)
//                FundFlow fundFlow = FundFlowService.findFundFlowHisDay(zqdm,KLT_101,date);
//                stockAdrCountVo.setF62(fundFlow.getFlowInMain());
//                BigDecimal flowRateBk = flowInMain.divide(marketValue, 6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100").setScale(4, BigDecimal.ROUND_HALF_UP));
            }

            rs.add(stockAdrCountVo);
        }
        return rs;

    }

    /**
     * 显示均线信息
     *
     * @param rs              统计信息
     * @param orderField
     * @param isShowPriceArea 是否显示价格区间
     * @param isShowUpMa      是否显示-超过均线
     * @param isShowFlowIn    是否显示-主力净流入市值比
     * @param isShowDownMa
     * @param kltList
     * @param spDate
     */
    public static void showStockMa(List<StockAdrCountVo> rs, String orderField, boolean isOrderDesc, boolean isShowPriceArea, boolean isShowUpMa, boolean isShowDownMa, boolean isShowFlowIn, List<String> kltList, String spDate) {
        if (rs == null || rs.size() == 0) {
            return;
        }
        if (StringUtils.isNotBlank(orderField)) {
            rs = handlerOrder(rs, orderField, isOrderDesc);//列表-排序：根据字段
        }
        for (StockAdrCountVo stockAdrCountVo : rs) {
            System.out.print(stockAdrCountVo.getF12());
            System.out.print("\t");
            System.out.print(EtfUtil.handlerEtfName(stockAdrCountVo.getF14()));
            System.out.print("\t");
            if (isShowPriceArea) {
                System.out.print("5日:" + stockAdrCountVo.getNET_AREA_DAY_5() + "\t");//显示信息-价格区间
                System.out.print("10日:" + stockAdrCountVo.getNET_AREA_DAY_10() + "\t");//显示信息-价格区间
                System.out.print("20日:" + stockAdrCountVo.getNET_AREA_DAY_20() + "\t");//显示信息-价格区间
                System.out.print("40日:" + stockAdrCountVo.getNET_AREA_DAY_40() + "\t");//显示信息-价格区间
                System.out.print("60日:" + stockAdrCountVo.getNET_AREA_DAY_60() + "\t");//显示信息-价格区间
//                System.out.print("120日:"+stockAdrCountVo.getNET_AREA_DAY_120() + "\t");//显示信息-价格区间
//                System.out.print("250日:"+stockAdrCountVo.getNET_AREA_DAY_250() + "\t");//显示信息-价格区间
            }
            if (isShowUpMa) {
                System.out.print("超均线：");//显示信息-价格区间
                if (kltList.contains(KLT_5)) {
                    String upMa5 = stockAdrCountVo.getUpMaDay5();
                    System.out.print(StringUtils.isNotBlank(upMa5) ? upMa5 + "   " : "        ");
//                    System.out.print(StringUtils.isNotBlank(upMa5) ? "[" + upMa5 + "   " + "]" : "[        ]");
                }
                if (kltList.contains(KLT_15)) {
                    String upMa15 = stockAdrCountVo.getUpMaDay15();
                    System.out.print(StringUtils.isNotBlank(upMa15) ? upMa15 + " " : "       ");
//                    System.out.print(StringUtils.isNotBlank(upMa15) ? "[" + upMa15 + " " + "]" : "[       ]");
                }
                if (kltList.contains(KLT_30)) {
                    String upMa30 = stockAdrCountVo.getUpMaDay30();
                    System.out.print(StringUtils.isNotBlank(upMa30) ? upMa30 + " " : "       ");
//                    System.out.print(StringUtils.isNotBlank(upMa30) ? "[" + upMa30 + " " + "]" : "[       ]");
                }
                if (kltList.contains(KLT_60)) {
                    String upMa60 = stockAdrCountVo.getUpMaDay60();
                    System.out.print(StringUtils.isNotBlank(upMa60) ? upMa60 + " " : "       ");
//                    System.out.print(StringUtils.isNotBlank(upMa60) ? "[" + upMa60 + " " + "]" : "[       ]");
                }
                if (kltList.contains(KLT_101)) {
                    String upMa101 = stockAdrCountVo.getUpMaDay101();
                    System.out.print(StringUtils.isNotBlank(upMa101) ? upMa101 : "       ");
//                    System.out.print(StringUtils.isNotBlank(upMa101) ? "[" + upMa101 + "]" : "[       ]");
                }
                if (kltList.contains(KLT_102)) {
                    String upMa102 = stockAdrCountVo.getUpMaDay102();
                    System.out.print(StringUtils.isNotBlank(upMa102) ? upMa102 : "       ");
//                    System.out.print(StringUtils.isNotBlank(upMa102) ? "[" + upMa102 + "]" : "[       ]");
                }
            }

            if (isShowDownMa) {
                System.out.print("跌落均线：");//显示信息-价格区间
                if (kltList.contains(KLT_5)) {
                    String upMa5 = stockAdrCountVo.getMaDownDay5();
                    System.out.print(StringUtils.isNotBlank(upMa5) ? upMa5 + "    " : "         ");
//                    System.out.print(StringUtils.isNotBlank(upMa5) ? "[" + upMa5 + "   " + "]" : "[        ]");
                }
                if (kltList.contains(KLT_15)) {
                    String upMa15 = stockAdrCountVo.getMaDownDay15();
                    System.out.print(StringUtils.isNotBlank(upMa15) ? upMa15 + "  " : "        ");
//                    System.out.print(StringUtils.isNotBlank(upMa15) ? "[" + upMa15 + " " + "]" : "[       ]");
                }
                if (kltList.contains(KLT_30)) {
                    String upMa30 = stockAdrCountVo.getMaDownDay30();
                    System.out.print(StringUtils.isNotBlank(upMa30) ? upMa30 + "  " : "        ");
//                    System.out.print(StringUtils.isNotBlank(upMa30) ? "[" + upMa30 + " " + "]" : "[       ]");
                }
                if (kltList.contains(KLT_60)) {
                    String upMa60 = stockAdrCountVo.getMaDownDay60();
                    System.out.print(StringUtils.isNotBlank(upMa60) ? upMa60 + "  " : "        ");
//                    System.out.print(StringUtils.isNotBlank(upMa60) ? "[" + upMa60 + " " + "]" : "[       ]");
                }
                if (kltList.contains(KLT_101)) {
                    String upMa101 = stockAdrCountVo.getMaDownDay101();
                    System.out.print(StringUtils.isNotBlank(upMa101) ? upMa101 : "        ");
//                    System.out.print(StringUtils.isNotBlank(upMa101) ? "[" + upMa101 + "]" : "[       ]");
                }
                if (kltList.contains(KLT_102)) {
                    String upMa102 = stockAdrCountVo.getMaDownDay102();
                    System.out.print(StringUtils.isNotBlank(upMa102) ? upMa102 : "        ");
//                    System.out.print(StringUtils.isNotBlank(upMa102) ? "[" + upMa102 + "]" : "[       ]");
                }
            }

            //证券信息：涨幅，助力净流入，流市比
            StringBuffer sbStockInfo = new StringBuffer();
            sbStockInfo.append("[").append(stockAdrCountVo.getDate().substring(5)).append("]");
            sbStockInfo.append("涨跌：").append(stockAdrCountVo.getF3());
            sbStockInfo.append("\t");
            System.out.print(sbStockInfo);

            if (isShowFlowIn) {
                BigDecimal flowInMian = stockAdrCountVo.getF62();
                BigDecimal marketValue = stockAdrCountVo.getF21();
                BigDecimal flowRate = null;
                if (flowInMian != null) {
                    StringBuffer sbFlowMian = new StringBuffer();
                    flowRate = flowInMian.divide(marketValue, 6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100").setScale(4, BigDecimal.ROUND_HALF_UP)).setScale(4, BigDecimal.ROUND_HALF_UP);
                    sbFlowMian.append("主力净流入：").append("[").append(StockUtil.formatDouble(flowInMian.divide(NUM_YI_1, 2, BigDecimal.ROUND_HALF_UP), 5)).append("]").append(",");
                    sbFlowMian.append("\t");
                    sbFlowMian.append("流入市值比：").append("[").append(flowRate).append("]").append(",");
                    sbFlowMian.append("\t");
                    System.out.print(sbFlowMian);
                }
            }

            //特定日期涨跌
            if (StringUtils.isNotBlank(spDate)) {
                RankStockCommpanyDb stock = new RankStockCommpanyDb();
                stock.setF12(stockAdrCountVo.getF12());
                Kline kline = KlineService.findLast(stock, spDate, KLT_101);
                if (kline != null) {
                    System.out.print("\t[" + spDate.substring(5) + "]：" + kline.getZhangDieFu());
                }
            }

            System.out.println();
        }
    }

    /**
     * 处理-列表-排序：根据字段
     *
     * @param rs          原始数据handlerOrder
     * @param orderField  排序字段
     * @param isOrderDesc 是否倒序
     * @return 排序结果
     */
    private static List<StockAdrCountVo> handlerOrder(List<StockAdrCountVo> rs, String orderField, boolean isOrderDesc) {
        if (rs == null) {
            return null;
        }
        if (ORDER_FIELD_NET_AREA_DAY_5.equals(orderField)) {
            if (isOrderDesc) {
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getNET_AREA_DAY_5, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
            } else {
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getNET_AREA_DAY_5, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            }
        }
        if (ORDER_FIELD_NET_AREA_DAY_10.equals(orderField)) {
            if (isOrderDesc) {
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getNET_AREA_DAY_10, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
            } else {
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getNET_AREA_DAY_10, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            }
        }
        if (ORDER_FIELD_NET_AREA_DAY_20.equals(orderField)) {
            if (isOrderDesc) {
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getNET_AREA_DAY_20, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
            } else {
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getNET_AREA_DAY_20, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            }
        }
        if (ORDER_FIELD_NET_AREA_DAY_40.equals(orderField)) {
            if (isOrderDesc) {
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getNET_AREA_DAY_40, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
            } else {
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getNET_AREA_DAY_40, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            }
        }
        if (ORDER_FIELD_NET_AREA_DAY_60.equals(orderField)) {
            if (isOrderDesc) {
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getNET_AREA_DAY_60, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
            } else {
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getNET_AREA_DAY_60, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            }
        }
        if (ORDER_FIELD_F3.equals(orderField)) {
            if (isOrderDesc) {
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getF3, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
            } else {
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getF3, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            }
        }
        if (ORDER_FIELD_MAXDOWN.equals(orderField)) {
            if (isOrderDesc) {
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getMaxDown, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
            } else {
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getMaxDown, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            }
        }
        if (ORDER_FIELD_MINRISE.equals(orderField)) {
            if (isOrderDesc) {
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getMinRise, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
            } else {
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getMinRise, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            }
        }
        return rs;
    }

    /**
     * 遍历板块，插入K线
     *
     * @param bizList  行业列表
     * @param date     日期
     * @param klt      周期类型
     * @param type     类型
     * @param isDelete 是否先删除
     */
    public static void saveKlineByType(List<RankBizDataDiff> bizList, String date, String klt, String type, boolean isDelete) {
        Map<String, String> mapZq = new HashMap<>();
        for (RankBizDataDiff rankBizDataDiff : bizList) {
            mapZq.put(rankBizDataDiff.getF12(), rankBizDataDiff.getF14());
        }
        saveKlineByType(mapZq, date, klt, type, isDelete);
    }

    public static void saveKlineByType(Map<String, String> mapZq, String date, String klt, String type, boolean isDelete) {
        for (String zqdm : mapZq.keySet()) {
            String zqmc = mapZq.get(zqdm);
            List<Kline> klines = null;
            if (DB_RANK_BIZ_TYPE_ZS.equals(type)) {
                klines = KlineService.kline(zqdm, NUM_MAX_999, klt, true, date, date, KLINE_TYPE_INDEX);
            } else {
                klines = KlineService.kline(zqdm, NUM_MAX_999, klt, true, date, date, null);
            }
            System.out.println(",开始日期:" + date + "，结束日期:" + date + "，周期:" + klt + "，klines.size():" + klines.size() + ",zqmc:" + zqmc);
            //        System.out.println("klines:"+JSON.toJSONString(klines));
            //是否先删除
            if (isDelete) {
                Kline condition = new Kline();
                condition.setDate(date);
                condition.setZqdm(zqdm);
                condition.setType(type);
                condition.setKlt(klt);
                int rs = KlineService.deleteByCondition(condition);
//                System.out.println(zqdm + "," + date + ",删除结果：" + rs);
            }

            int saveRs = 0;
            for (Kline kline : klines) {
                if (klt == KLT_5 || klt == KLT_15 || klt == KLT_30 || klt == KLT_60) {
                    kline.setKtime(kline.getKtime().substring(11));//只设置当天具体时间，去掉日期
                }
                kline.setDate(date);
                kline.setType(type);
                kline.setRs(null);
                /**
                 * 插入数据库-K线
                 */
                saveRs += KlineService.insert(kline);
            }
//            System.out.println(zqdm + "," + date + ",插入K线：" + saveRs);
        }
    }

    /**
     * 更新资金流向
     *
     * @param bizList 类型列表
     * @param date    日期
     * @param klt     周期
     * @param type    业务类型
     */
    public static void updateFundFlow(List<RankBizDataDiff> bizList, String date, String klt, String type) {
        for (RankBizDataDiff rankBizDataDiff : bizList) {
            String zqdm = rankBizDataDiff.getF12();
            String zqmc = rankBizDataDiff.getF14();

            int rs = 0;
            List<FundFlow> rsList = FundFlowService.parse(FundFlowService.httpFundFlowRs(zqdm, null));//获取-资金流向的对象结果
            rsList = FundFlowService.handlerFundFlowByMinute(rsList, Integer.valueOf(klt));//计算分钟级别资金流向
            for (FundFlow fundFlow : rsList) {
                Kline kline = new Kline();
                String ktime = fundFlow.getKtime();
                if (klt == KLT_5 || klt == KLT_15 || klt == KLT_30 || klt == KLT_60) {
                    kline.setKtime(DateUtil.getForMatTime(HH_MM_SS, ktime));//只设置当天具体时间，去掉日期
                }
                kline.setZqdm(zqdm);
                kline.setDate(date);
                kline.setType(type);
                kline.setKlt(klt);

                kline.setFlowInMain(fundFlow.getFlowInMain());
                kline.setFlowInSuperBig(fundFlow.getFlowInSuperBig());
                kline.setFlowInBig(fundFlow.getFlowInBig());
                kline.setFlowInMid(fundFlow.getFlowInMid());
                kline.setFlowInSmall(fundFlow.getFlowInSmall());
                int updateRs = KlineService.update(kline);
                if (updateRs != 1) {
                    System.out.println("K线-资金流入-更新失败：" + JSON.toJSONString(kline));
                } else {
                    rs = rs + updateRs;
                }
            }
            System.out.println("K线-资金流入-更新个数：" + rs + ",zqmc:" + zqmc);
        }
    }

    /**
     * 更新资金流向
     *
     * @param mapZq 证券
     * @param date  日期
     * @param klt   周期
     * @param type  业务类型
     */
    public static void updateFundFlow(Map<String, String> mapZq, String date, String klt, String type) {
        for (String zqdm : mapZq.keySet()) {
            String zqmc = mapZq.get(zqdm);

            int rs = 0;
            List<FundFlow> rsList = FundFlowService.parse(FundFlowService.httpFundFlowRs(zqdm, type));//获取-资金流向的对象结果
            rsList = FundFlowService.handlerFundFlowByMinute(rsList, Integer.valueOf(klt));//计算分钟级别资金流向
            for (FundFlow fundFlow : rsList) {
                Kline kline = new Kline();
                String ktime = fundFlow.getKtime();
                if (klt == KLT_5 || klt == KLT_15 || klt == KLT_30 || klt == KLT_60) {
                    kline.setKtime(DateUtil.getForMatTime(HH_MM_SS, ktime));//只设置当天具体时间，去掉日期
                }
                kline.setZqdm(zqdm);
                kline.setDate(date);
                kline.setType(type);
                kline.setKlt(klt);

                kline.setFlowInMain(fundFlow.getFlowInMain());
                kline.setFlowInSuperBig(fundFlow.getFlowInSuperBig());
                kline.setFlowInBig(fundFlow.getFlowInBig());
                kline.setFlowInMid(fundFlow.getFlowInMid());
                kline.setFlowInSmall(fundFlow.getFlowInSmall());
                int updateRs = KlineService.update(kline);
                if (updateRs != 1) {
                    System.out.println("K线-资金流入-更新失败：" + JSON.toJSONString(kline));
                } else {
                    rs = rs + updateRs;
                }
            }
            System.out.println("K线-资金流入-更新个数：" + rs + ",zqmc:" + zqmc);
        }
    }

    /**
     * 查询并显示突破均线信息
     *
     * @param condMa 均线条件
     */
    public static void showStockMa(CondMa condMa) {

        String date = condMa.getDate();
        String spDate = condMa.getSpDate();
        boolean isShowPriceArea = condMa.getShowPriceArea();
        boolean isShowUpMa = condMa.getShowUpMa();
        boolean isFindKline = condMa.getFindKline();
        List<String> kltList = condMa.getKltList();
        String orderField = condMa.getOrderField();
        boolean isOrderDesc = condMa.getOrderDesc();
        boolean isShowDownMa = condMa.getShowDownMa();
        boolean isShowFlowIn = condMa.getShowFlowIn();
        Map<String, String> mapSt = condMa.getMapStock();

        List<StockAdrCountVo> rs = KlineService.checkMaDemo(mapSt, date, isShowPriceArea, isShowPriceArea, isShowUpMa, isFindKline, kltList);

        if (rs == null || rs.size() == 0) {
            return;
        }
        if (StringUtils.isNotBlank(orderField)) {
            rs = handlerOrder(rs, orderField, isOrderDesc);//列表-排序：根据字段
        }
        for (StockAdrCountVo stockAdrCountVo : rs) {
            System.out.print(stockAdrCountVo.getF12());
            System.out.print("\t");
            System.out.print(EtfUtil.handlerEtfName(stockAdrCountVo.getF14()));
            System.out.print("\t");
            if (isShowPriceArea) {
                System.out.print("5日:" + stockAdrCountVo.getNET_AREA_DAY_5() + "\t");//显示信息-价格区间
                System.out.print("10日:" + stockAdrCountVo.getNET_AREA_DAY_10() + "\t");//显示信息-价格区间
                System.out.print("20日:" + stockAdrCountVo.getNET_AREA_DAY_20() + "\t");//显示信息-价格区间
                System.out.print("40日:" + stockAdrCountVo.getNET_AREA_DAY_40() + "\t");//显示信息-价格区间
                System.out.print("60日:" + stockAdrCountVo.getNET_AREA_DAY_60() + "\t");//显示信息-价格区间
//                System.out.print("120日:"+stockAdrCountVo.getNET_AREA_DAY_120() + "\t");//显示信息-价格区间
//                System.out.print("250日:"+stockAdrCountVo.getNET_AREA_DAY_250() + "\t");//显示信息-价格区间
            }
            if (isShowUpMa) {
                System.out.print("超均线：");//显示信息-价格区间
                if (kltList.contains(KLT_5)) {
                    String upMa5 = stockAdrCountVo.getUpMaDay5();
                    System.out.print(StringUtils.isNotBlank(upMa5) ? upMa5 + "   " : "        ");
//                    System.out.print(StringUtils.isNotBlank(upMa5) ? "[" + upMa5 + "   " + "]" : "[        ]");
                }
                if (kltList.contains(KLT_15)) {
                    String upMa15 = stockAdrCountVo.getUpMaDay15();
                    System.out.print(StringUtils.isNotBlank(upMa15) ? upMa15 + " " : "       ");
//                    System.out.print(StringUtils.isNotBlank(upMa15) ? "[" + upMa15 + " " + "]" : "[       ]");
                }
                if (kltList.contains(KLT_30)) {
                    String upMa30 = stockAdrCountVo.getUpMaDay30();
                    System.out.print(StringUtils.isNotBlank(upMa30) ? upMa30 + " " : "       ");
//                    System.out.print(StringUtils.isNotBlank(upMa30) ? "[" + upMa30 + " " + "]" : "[       ]");
                }
                if (kltList.contains(KLT_60)) {
                    String upMa60 = stockAdrCountVo.getUpMaDay60();
                    System.out.print(StringUtils.isNotBlank(upMa60) ? upMa60 + " " : "       ");
//                    System.out.print(StringUtils.isNotBlank(upMa60) ? "[" + upMa60 + " " + "]" : "[       ]");
                }
                if (kltList.contains(KLT_101)) {
                    String upMa101 = stockAdrCountVo.getUpMaDay101();
                    System.out.print(StringUtils.isNotBlank(upMa101) ? upMa101 : "       ");
//                    System.out.print(StringUtils.isNotBlank(upMa101) ? "[" + upMa101 + "]" : "[       ]");
                }
                if (kltList.contains(KLT_102)) {
                    String upMa102 = stockAdrCountVo.getUpMaDay102();
                    System.out.print(StringUtils.isNotBlank(upMa102) ? upMa102 : "       ");
//                    System.out.print(StringUtils.isNotBlank(upMa102) ? "[" + upMa102 + "]" : "[       ]");
                }
            }

            if (isShowDownMa) {
                System.out.print("跌落均线：");//显示信息-价格区间
                if (kltList.contains(KLT_5)) {
                    String upMa = stockAdrCountVo.getMaDownDay5();
                    System.out.print(StockUtil.formatStr(upMa,4));
//                    System.out.print(StringUtils.isNotBlank(upMa5) ? "[" + upMa5 + "   " + "]" : "[        ]");
                }
                if (kltList.contains(KLT_15)) {
                    String upMa = stockAdrCountVo.getMaDownDay15();
                    System.out.print(StockUtil.formatStr(upMa,4));
//                    System.out.print(StringUtils.isNotBlank(upMa15) ? "[" + upMa15 + " " + "]" : "[       ]");
                }
                if (kltList.contains(KLT_30)) {
                    String upMa = stockAdrCountVo.getMaDownDay30();
                    System.out.print(StockUtil.formatStr(upMa,4));
//                    System.out.print(StringUtils.isNotBlank(upMa30) ? "[" + upMa30 + " " + "]" : "[       ]");
                }
                if (kltList.contains(KLT_60)) {
                    String upMa = stockAdrCountVo.getMaDownDay60();
                    System.out.print(StockUtil.formatStr(upMa,4));
//                    System.out.print(StringUtils.isNotBlank(upMa60) ? "[" + upMa60 + " " + "]" : "[       ]");
                }
                if (kltList.contains(KLT_101)) {
                    String upMa = stockAdrCountVo.getMaDownDay101();
                    System.out.print(StockUtil.formatStr(upMa,4));
//                    System.out.print(StringUtils.isNotBlank(upMa101) ? "[" + upMa101 + "]" : "[       ]");
                }
                if (kltList.contains(KLT_102)) {
                    String upMa = stockAdrCountVo.getMaDownDay102();
                    System.out.print(StockUtil.formatStr(upMa,4));
//                    System.out.print(StringUtils.isNotBlank(upMa102) ? "[" + upMa102 + "]" : "[       ]");
                }
            }

            //证券信息：涨幅，助力净流入，流市比
            StringBuffer sbStockInfo = new StringBuffer();
            sbStockInfo.append("[").append(stockAdrCountVo.getDate().substring(5)).append("]");
            sbStockInfo.append("涨跌：").append(StockUtil.formatDouble(stockAdrCountVo.getF3(), 5)).append(" ");
            sbStockInfo.append("最高回撤：").append(stockAdrCountVo.getMaxDown()).append(" ");
            sbStockInfo.append("最低上涨：").append(stockAdrCountVo.getMinRise()).append(" ");

            sbStockInfo.append("\t");
            System.out.print(sbStockInfo);

            if (isShowFlowIn) {
                BigDecimal flowInMian = stockAdrCountVo.getF62();
                BigDecimal marketValue = stockAdrCountVo.getF21();
                BigDecimal flowRate = null;
                if (flowInMian != null) {
                    StringBuffer sbFlowMian = new StringBuffer();
                    flowRate = flowInMian.divide(marketValue, 6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100").setScale(4, BigDecimal.ROUND_HALF_UP)).setScale(4, BigDecimal.ROUND_HALF_UP);
                    sbFlowMian.append("主力净流入：").append("[").append(StockUtil.formatDouble(flowInMian.divide(NUM_YI_1, 2, BigDecimal.ROUND_HALF_UP), 5)).append("]").append(",");
                    sbFlowMian.append("\t");
                    sbFlowMian.append("流入市值比：").append("[").append(flowRate).append("]").append(",");
                    sbFlowMian.append("\t");
                    System.out.print(sbFlowMian);
                }
            }

            //特定日期涨跌
            if (StringUtils.isNotBlank(spDate)) {
                RankStockCommpanyDb stock = new RankStockCommpanyDb();
                stock.setF12(stockAdrCountVo.getF12());
                Kline kline = KlineService.findLast(stock, spDate, KLT_101);
                if (kline != null) {
                    System.out.print("\t[" + spDate.substring(5) + "]：" + kline.getZhangDieFu());
                }
            }

            System.out.println();
        }
    }
}
