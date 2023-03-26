package ttjj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.BizRankDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.BizDto;
import ttjj.dto.Kline;
import ttjj.dto.RankBizDataDiff;
import utils.Content;
import utils.DateUtil;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.*;

import static utils.ContMapBizBaord.BOARD_NAME_CODE;
import static utils.Content.*;

/**
 * BizService简介
 *
 * @author Administrator
 * @date 2022-05-14 21:37
 */
public class BizService {
    /**
     * 保存-板块、概念、etf
     *
     * @param type
     * @param bizList
     */
    public static void insertTodayRank(String type, List<RankBizDataDiff> bizList) {
        //db-插入
        BizRankDao.insertDbBiz(bizList);//bk-板块
        System.out.println(type + "保存完成：" + bizList.size());
//            showBizSql(date, rankBizDataDiffListBiz, "bk");//显示sql-业务排行-插入
    }

    /**
     * 列表查询业务-根据类型
     * //2021-04-16:425;2021-12-06:584;
     *
     * @param date
     * @param type
     * @param pageSize
     * @return
     */
    public static List<RankBizDataDiff> listBiz(String date, String type, int pageSize) {
        //http://87.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112407216560422201541_1652863152766&pn=1&pz=20&po=0&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&wbp2u=6342375825382124|0|1|0|web&fid=f3&fs=m:90+t:2+f:!50&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222&_=1652863152768
        String url = "http://32.push2.eastmoney.com/api/qt/clist/get";
        StringBuffer urlParam = new StringBuffer();
        long curTime = System.currentTimeMillis();
        urlParam.append("cb=jQuery11240323187262602" + RandomUtils.nextInt(1000, 9999) + "_");
        urlParam.append(curTime);
        urlParam.append("&pn=1" +//页数
                "&pz=" + pageSize +//每页数量
                "&po=1" +//pageorder:页面排序：0-正序；1-倒序
                "&np=1" +
                "&ut=bd1d9ddb04089700cf9c27f6f7426281" +
                "&fltt=2" +//浮点数精度
                "&invt=3" +//显示格式：-；0.0
                "&fid=f3");//排序字段
        if (type.equals(DB_RANK_BIZ_TYPE_BAN_KUAI)) {
            urlParam.append("&fs=m:90+t:2+f:!50");
        }
        if (type.equals(DB_RANK_BIZ_TYPE_GAI_NIAN)) {
            urlParam.append("&fs=m:90+t:3+f:!50");
        }
        if (type.equals(DB_RANK_BIZ_TYPE_ETF)) {
            urlParam.append("&fs=b:MK0021,b:MK0022,b:MK0023,b:MK0024");
        }
        if (type.equals(DB_RANK_BIZ_TYPE_LOF)) {
            urlParam.append("&fs=b:MK0404,b:MK0405,b:MK0406,b:MK0407");
        }
        urlParam.append("&fields=" +
                "f1,f2,f3,f4,f5,f6,f7,f8,f9," +
                "f10,f11,f12,f13,f14,f15,f16,f17,f18,f19," +
                "f20,f21,f22,f23,f24,f25,f26,f27,f28,f29," +
                "f30,f31,f32,f33,f34,f35,f36,f37,f38,f39," +
                "f40,f41,f42,f43,f44,f45,f46,f47,f48,f49," +
                "f50,f51,f52,f53,f54,f55,f56,f57,f58,f59," +
                "f60,f61,f62,f63,f64,f65,f66,f67,f68,f69," +
                "f70,f71,f72,f73,f74,f75,f76,f77,f78,f79," +
                "f80,f81,f82,f83,f84,f85,f86,f87,f88,f89," +
                "f90,f91,f92,f93,f94,f95,f96,f97,f98,f99," +
                "f100,f101,f102,f103,f104,f105,f106,f107,f108,f109" + "," +
                "f110,f111,f112,f113,f114,f115,f116,f117,f118,f119" + "," +
                "f120,f121,f122,f123,f124,f125,f126,f127,f128,f129" + "," +
                "f130,f131,f132,f133,f134,f135,f136,f137,f138,f139" + "," +
                "f140,f141,f142,f143,f144,f145,f146,f147,f148,f149" + "," +
                "f150,f151,f152,f153,f154,f155,f156,f157,f158,f159" + "," +
                "f160,f161,f162,f163,f164,f165,f166,f167,f168,f169" + "," +
                "f170,f171,f172,f173,f174,f175,f176,f177,f178,f179" + "," +
                "f180,f181,f182,f183,f184,f185,f186,f187,f188,f189" + "," +
                "f190,f191,f192,f193,f194,f195,f196,f197,f198,f199" + "," +
                "f200,f201,f202,f203,f204,f205,f206,f207,f208,f209" + "," +
                "f210,f211,f212,f213,f214,f215,f216,f217,f218,f219" + "," +
                "f220,f221,f222,f223,f224,f225,f226,f227,f228,f229" +
//                "f230,f231,f232,f233,f234,f235,f236,f237,f238,f239" +
//                "f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222" +
                "&_=" + curTime);
//        System.out.println(url + "?" + urlParam.toString());
        String rs = "";
        JSONObject rsJsonObj = null;
        for (int i = 0; i < 10; i++) {
            rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);//返回结果
            if (rs.startsWith("jQuery")) {
                rs = rs.substring(rs.indexOf("{"));
            }
            if (rs.endsWith(");")) {
                rs = rs.substring(0, rs.length() - 2);
            }
            rsJsonObj = JSONObject.parseObject(rs);
            if (rs == null || rsJsonObj == null || !rsJsonObj.containsKey("data")) {
                System.out.println("查询数据异常，重新查询：" + rs);
                continue;
            } else {
                break;
            }
        }
//        System.out.println(rs);//返回结果

        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        try {
            JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
            List<RankBizDataDiff> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankBizDataDiff.class);
            for (RankBizDataDiff row : rankBizDataDiffList) {
//            row.setRs(rs);
                row.setDate(date);
                row.setMonth(DateUtil.getYearMonth(date, DateUtil.YYYY_MM_DD));
                row.setWeekYear(DateUtil.getYearWeek(date, DateUtil.YYYY_MM_DD));
                row.setWeek(DateUtil.getWeekByYyyyMmDd(date, DateUtil.YYYY_MM_DD));
                row.setType(type);
//            System.out.println(JSON.toJSON(row));//每个行业一行数据
            }
            return rankBizDataDiffList;
        } catch (Exception e) {
            System.out.println("Exception:" + rs);
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 查询业务-根据条件：编码、日期、业务类型
     *
     * @param zqdm 编码
     * @param date 日期
     * @param type 类型
     * @return 查询结果
     */
    public static RankBizDataDiff findBiz(String zqdm, String date, String type) {
        RankBizDataDiff condition = new RankBizDataDiff();
        condition.setF12(zqdm);
        condition.setDate(date);
        return BizRankDao.findBiz(condition);
    }

    /**
     * 查询列表-根据
     *
     * @param condition 条件
     * @return 结果
     */
    public static List<BizDto> findListDbBiz(BizDto condition) {
        return BizRankDao.findListDbBiz(condition);
    }

    /**
     * 查询昨日主题排名
     */
    public static List<RankStockCommpanyDb> listRankStockByBiz(int pageSize, String biz) {
        //http://push2.eastmoney.com/api/qt/clist/get?cb=jQuery112307730222083783287_1617467610779&fid=f62&po=1&pz=50&pn=1&np=1&fltt=2&invt=2&ut=b2884a393a59ad64002292a3e90d46a5&fs=b%3ABK0891&fields=f12%2Cf14%2Cf2%2Cf3%2Cf62%2Cf184%2Cf66%2Cf69%2Cf72%2Cf75%2Cf78%2Cf81%2Cf84%2Cf87%2Cf204%2Cf205%2Cf124
        StringBuffer urlParam = new StringBuffer();
        long curTime = System.currentTimeMillis();
        String url = "http://push2.eastmoney.com/api/qt/clist/get";
        urlParam.append("cb=jQuery112307730222083783287_" + curTime +
                "&pn=1" +//页数
                "&pz=" + pageSize +//每页数量
                "&po=1" +//pageorder:页面排序：0-正序；1-倒序
                "&np=1" +
                "&ut=b2884a393a59ad64002292a3e90d46a5" +
                "&fltt=2" +//浮点数精度
                "&invt=3" +//显示格式：-；0.0
//                "&fid=f20" +//排序字段：f20:总市值
                "&fid=f3" +//排序字段：f3:涨跌幅
                "&fs=b:" + biz +
                //fields: f12,f14,f2,f3,f62,f184,f66,f69,f72,f75,f78,f81,f84,f87,f204,f205,f124
                "&fields=" +
                "f1,f2,f3,f4,f5,f6,f7,f8,f9," +
                "f10,f11,f12,f13,f14,f15,f16,f17,f18,f19," +
                "f20,f21,f22,f23,f24,f25,f26,f27,f28,f29," +
                "f30,f31,f32,f33,f34,f35,f36,f37,f38,f39," +
                "f40,f41,f42,f43,f44,f45,f46,f47,f48,f49," +
                "f50,f51,f52,f53,f54,f55,f56,f57,f58,f59," +
                "f60,f61,f62,f63,f64,f65,f66,f67,f68,f69," +
                "f70,f71,f72,f73,f74,f75,f76,f77,f78,f79," +
                "f80,f81,f82,f83,f84,f85,f86,f87,f88,f89," +
                "f90,f91,f92,f93,f94,f95,f96,f97,f98,f99," +
                "f100,f101,f102,f103,f104,f105,f106,f107,f108,f109" + "," +
                "f110,f111,f112,f113,f114,f115,f116,f117,f118,f119" + "," +
                "f120,f121,f122,f123,f124,f125,f126,f127,f128,f129" + "," +
                "f130,f131,f132,f133,f134,f135,f136,f137,f138,f139" + "," +
                "f140,f141,f142,f143,f144,f145,f146,f147,f148,f149" + "," +
                "f150,f151,f152,f153,f154,f155,f156,f157,f158,f159" + "," +
                "f160,f161,f162,f163,f164,f165,f166,f167,f168,f169" + "," +
                "f170,f171,f172,f173,f174,f175,f176,f177,f178,f179" + "," +
                "f180,f181,f182,f183,f184,f185,f186,f187,f188,f189" + "," +
                "f190,f191,f192,f193,f194,f195,f196,f197,f198,f199" + "," +
                "f200,f201,f202,f203,f204,f205,f206,f207,f208,f209" + "," +
                "f210,f211,f212,f213,f214,f215,f216,f217,f218,f219" + "," +
                "f220,f221,f222,f223,f224,f225,f226,f227,f228,f229" +
                "");
//        System.out.println(url + "?" + urlParam.toString());
        String rs = "";
        JSONObject rsJsonObj = null;
        for (int i = 0; i < NUM_MAX_99; i++) {
            rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);//返回结果
            if (rs.startsWith("jQuery")) {
                rs = rs.substring(rs.indexOf("{"));
            }
            if (rs.endsWith(");")) {
                rs = rs.substring(0, rs.length() - 2);
            }
            rsJsonObj = JSONObject.parseObject(rs);
            if (rs == null || rsJsonObj == null || !rsJsonObj.containsKey("data")) {
                System.out.println("查询数据异常，重新查询：" + rs);
                continue;
            } else {
                break;
            }
        }
//        System.out.println(rs);//返回结果

        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        try {
            JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
            List<RankStockCommpanyDb> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankStockCommpanyDb.class);
            return rankBizDataDiffList;
        } catch (Exception e) {
            System.out.println("Exception:" + rs);
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取业务列表-全部板块
     *
     * @return 获取业务列表-全部板块
     */
    public static Map<String, List<String>> getBizListAll(String findModel) {
        Map<String, List<String>> bkMap = new HashMap<>();
        List<String> bizList = null;//
        //        //全部板块
        Set<String> bkList = new HashSet<>();
        if (FIND_MODEL_CACHE.equals(findModel)) {
            bkList = BOARD_NAME_CODE.keySet();
        }
        if (FIND_MODEL_HTTP.equals(findModel)) {
            List<RankBizDataDiff> bkListHttp = StockService.listBiz(NUM_MAX_99);
            for (RankBizDataDiff bk : bkListHttp) {
                bkList.add(bk.getF14());
            }
        }
        for (String bk : bkList) {
            bizList = new ArrayList<>();
            bizList.add(bk);
            bkMap.put(bk, bizList);
        }
        return bkMap;
    }

    /**
     * 更新复权：前复权，检查当日K线与数据库的数据是否相符，如果不符，进行复权更新
     *
     * @param rsList    列表
     * @param limit     限定个数
     * @param dateCheck
     */
    public static boolean updateFuQuanBiz(List<BizDto> rsList, int limit, String dateCheck) {
        int checkCount = limit;
        boolean isUpdate = false;
        for (BizDto bizDto : rsList) {
            if (--checkCount <= 0) {
                break;
            }
            String zqdm = bizDto.getF12();
            String zqmc = bizDto.getF14();
//            if(zqmc.contains("康龙化成")){
//                System.out.println("特殊股票处理："+zqmc);
//            }
//            if(zqdm.contains("300759")){
//                System.out.println("特殊股票代码处理："+zqmc);
//            }
            BigDecimal closeAmtDb = bizDto.getF2();
            // 查询今日价格
            List<Kline> klines = KlineService.kline(zqdm, 1, KLT_101, true, dateCheck, dateCheck, "");
            if (klines == null || klines.size() == 0) {
                StringBuffer sbError = new StringBuffer();
                sbError.append(zqdm).append("，").append(":k线异常！");
                System.out.println(sbError);
                continue;
            }
            Kline todayKline = klines.get(0);
            BigDecimal closeAmt = todayKline.getCloseAmt();
            if (closeAmtDb.compareTo(closeAmt) != 0) {
                System.out.println("k线收盘价与数据库收盘价不符：" + zqmc + ":" + zqdm + "," + closeAmt + ":" + closeAmtDb + ":" + dateCheck);
                RankBizDataDiff entity = new RankBizDataDiff();
                entity.setF12(zqdm);
                entity.setDate(dateCheck);
                entity.setF2(closeAmt);
                entity.setF15(todayKline.getMaxAmt());
                entity.setF16(todayKline.getMinAmt());
                entity.setF17(todayKline.getOpenAmt());
                entity.setF18(todayKline.getCloseLastAmt());
                int rsUpdate = BizRankDao.updateEtfNet(entity);
                System.out.println("复权更新：" + rsUpdate);
                isUpdate = true;
            }
        }
        return isUpdate;
    }

    /**
     * 查询-ETF-指数
     *
     * @param rankEtf
     * @param date
     * @param zhiShu            指数
     * @param table
     * @param days              数量
     * @param klt               K线周期类型
     * @param dbFieldLastNetMin
     * @param dbFieldLastNetMax
     */
    public static void kline(RankBizDataDiff rankEtf, String date, String zhiShu, String table, int days, String klt, String dbFieldLastNetMin, String dbFieldLastNetMax, String dbFieldLastNetMinClose, String dbFieldLastNetMaxClose) {
        long curTime = System.currentTimeMillis();
        StringBuffer url = new StringBuffer();
        url.append("http://96.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery331093188916841208381602168987937");
        if (zhiShu.startsWith("5") || zhiShu.startsWith("6")) {
            url.append("&secid=" + "1." + zhiShu);
        } else {
            url.append("&secid=" + "0." + zhiShu);
        }

        url.append("&ut=fa5fd1943c7b386f172d6893dbfba10b");
        url.append("&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5%2Cf6");
        url.append("&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58%2Cf59%2Cf60%2Cf61");
        url.append("&klt=" + klt);
        url.append("&fqt=1");
        url.append("&end=20500101");
        url.append("&lmt=" + days);
        url.append("&_=" + curTime);

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
//        System.out.println("szKline:" + rsJson);

        List<String> klineList = new ArrayList<String>();
        JSONObject szzzMonthJson = JSON.parseObject(rsJson);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
//        String name = szzzMonthDataJson.getString("name");
//        System.out.println("指数名称："+name);
        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        if (klines != null) {
            for (Object kline : klines) {
                String klineStr = (String) kline;
                klineList.add(klineStr);
            }
        }

        Map<String, Double> netRs = handlerMaxJz(klineList);
        Double minJz = netRs.get(Content.keyRsMin);
        Double maxJz = netRs.get(Content.keyRsMax);
        Double netCloseMin = netRs.get(Content.keyRsNetCloseMin);
        Double netCloseMax = netRs.get(Content.keyRsNetCloseMax);

//        StringBuffer sb = new StringBuffer();
//        sb.append("UPDATE `" + table + "` ");
//        sb.append("SET ");
//        sb.append(" `" + dbFieldLastNetMin + "`=" + minJz + ", ");
//        sb.append(" `" + dbFieldLastNetMinClose + "`=" + netCloseMin + ", ");
//        sb.append(" `" + dbFieldLastNetMax + "`=" + maxJz + ", ");
//        sb.append(" `" + dbFieldLastNetMaxClose + "`=" + netCloseMax + " ");
////        sb.append(" WHERE `FD_CODE`='" + zhiShu + "'" + " AND TYPE = '证券买入'" + ";");
//        sb.append(" WHERE `f12`='" + zhiShu + "'" + "");
//        sb.append(" AND `date`='" + date + "'" + "");
//        sb.append(";");
//        sb.append("/**" + szzzMonthDataJson.getString("name") + "**/");
//        System.out.println(sb);

        //insertDb
        rankEtf.setDate(date);
        rankEtf.setMonth(DateUtil.getYearMonth(date, DateUtil.YYYY_MM_DD));
        rankEtf.setWeekYear(DateUtil.getYearWeek(date, DateUtil.YYYY_MM_DD));
        rankEtf.setWeek(DateUtil.getWeekByYyyyMmDd(date, DateUtil.YYYY_MM_DD));
        if (days == 1) {
            rankEtf.setLAST_NET(new BigDecimal(netCloseMin));
            rankEtf.setNET_MIN_1(new BigDecimal(minJz));
            rankEtf.setNET_MIN_CLOS_1(new BigDecimal(netCloseMin));
            rankEtf.setNET_MAX_1(new BigDecimal(maxJz));
            rankEtf.setNET_MAX_CLOS_1(new BigDecimal(netCloseMax));
        }
        if (days == 7) {
            rankEtf.setNET_MIN_7(new BigDecimal(minJz));
            rankEtf.setNET_MIN_CLOS_7(new BigDecimal(netCloseMin));
            rankEtf.setNET_MAX_7(new BigDecimal(maxJz));
            rankEtf.setNET_MAX_CLOS_7(new BigDecimal(netCloseMax));
        }
        if (days == 14) {
            rankEtf.setNET_MIN_14(new BigDecimal(minJz));
            rankEtf.setNET_MIN_CLOS_14(new BigDecimal(netCloseMin));
            rankEtf.setNET_MAX_14(new BigDecimal(maxJz));
            rankEtf.setNET_MAX_CLOS_14(new BigDecimal(netCloseMax));
        }
        if (days == 30) {
            rankEtf.setNET_MIN_30(new BigDecimal(minJz));
            rankEtf.setNET_MIN_CLOS_30(new BigDecimal(netCloseMin));
            rankEtf.setNET_MAX_30(new BigDecimal(maxJz));
            rankEtf.setNET_MAX_CLOS_30(new BigDecimal(netCloseMax));
        }
        if (days == 60) {
            rankEtf.setNET_MIN_60(new BigDecimal(minJz));
            rankEtf.setNET_MIN_CLOS_60(new BigDecimal(netCloseMin));
            rankEtf.setNET_MAX_60(new BigDecimal(maxJz));
            rankEtf.setNET_MAX_CLOS_60(new BigDecimal(netCloseMax));
        }
        if (days == 90) {
            rankEtf.setNET_MIN_90(new BigDecimal(minJz));
            rankEtf.setNET_MIN_CLOS_90(new BigDecimal(netCloseMin));
            rankEtf.setNET_MAX_90(new BigDecimal(maxJz));
            rankEtf.setNET_MAX_CLOS_90(new BigDecimal(netCloseMax));
        }
        if (days == 180) {
            rankEtf.setNET_MIN_180(new BigDecimal(minJz));
            rankEtf.setNET_MIN_CLOS_180(new BigDecimal(netCloseMin));
            rankEtf.setNET_MAX_180(new BigDecimal(maxJz));
            rankEtf.setNET_MAX_CLOS_180(new BigDecimal(netCloseMax));
        }
        if (days == 365) {
            rankEtf.setNET_MIN_360(new BigDecimal(minJz));
            rankEtf.setNET_MIN_CLOS_360(new BigDecimal(netCloseMin));
            rankEtf.setNET_MAX_360(new BigDecimal(maxJz));
            rankEtf.setNET_MAX_CLOS_360(new BigDecimal(netCloseMax));
        }


    }

    /**
     * 计算最大净值、最小净值
     *
     * @return
     */
    private static Map<String, Double> handlerMaxJz(List<String> klineList) {
        Map<String, Double> rs = new HashMap<String, Double>();
        Double rsMax = 0.0;
        Double rsMin = 0.0;
        Double lastDwjz = 0.0;
        Double rsNetCloseMin = 0.0;
        Double rsNetCloseMax = 0.0;
        int curTempInt = 0;
        for (String klineStr : klineList) {
            //  日期，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率
            //"2020-09-30,3389.74,3218.05,3425.63,3202.34,4906229054,6193724911616.00,6.58,-5.23,-177.63,13.40"
            String[] klineArray = klineStr.split(",");
            String shouPan = klineArray[2];
            String netMax = klineArray[3];
            String netMin = klineArray[4];
            String zhangDie = klineArray[8];
            String chengJiaoE = klineArray[6];
            String curDate = klineArray[0];
//            System.out.print("日期:" + curDate + ",");
//            System.out.print("收盘:" + shouPan + ",");
//            System.out.print("涨跌幅:" + zhangDie + ",\t");
//            System.out.print("开盘:" + klineArray[1] + ",\t");
//            System.out.print("最高:" + klineArray[3] + ",");
//            System.out.print("最低:" + klineArray[4] + ",");
//            System.out.print("成交量:\t" + klineArray[5] + ",\t\t");
//            System.out.print("成交额:\t" + klineArray[6] + ",\t\t");
//            System.out.print("振幅:" + klineArray[7] + ",");
//            System.out.print("涨跌额:" + klineArray[9] + ",");
//            System.out.print("换手率:" + klineArray[10] + ",");
//            System.out.println();

//                    System.out.println(JSON.toJSONString(lsjzDataLsjz));
//            String dwJz = lsjzDataLsjz.getDWJZ();//当晚净值
            String dwJz = shouPan;//累计净值

            if (StringUtils.isBlank(dwJz)) {
                dwJz = "0";
            }
            String fsrq = curDate;
//            System.out.println("fsrq:" + fsrq + ",dwjzLong:" + dwJz);

            Double netMaxDou = Double.valueOf(netMax);
            if (netMaxDou > rsMax) {
                rsMax = netMaxDou;
            }
            Double netMinDou = Double.valueOf(netMin);
            if (netMinDou < rsMin || rsMin == 0.0) {
                rsMin = netMinDou;
            }

            //
            Double dwjzLong = Double.valueOf(dwJz);
            if (dwjzLong > rsNetCloseMax) {
                rsNetCloseMax = dwjzLong;
            }
            if (dwjzLong < rsNetCloseMin || rsNetCloseMin == 0.0) {
                rsNetCloseMin = dwjzLong;
            }
        }
        rs.put(Content.keyRsMax, rsMax);
        rs.put(Content.keyRsMin, rsMin);
        rs.put(Content.keyRsNetCloseMin, rsNetCloseMin);
        rs.put(Content.keyRsNetCloseMax, rsNetCloseMax);
        return rs;
    }

    /**
     * 查询排名-概念板块
     *
     * @param date
     * @param type
     * @param endCount
     */
    public static List<RankBizDataDiff> listConcept(String date, String type, int endCount) {
        long curTime = System.currentTimeMillis();
//        http://70.push2.eastmoney.com/api/qt/clist/get?cb=jQuery1124026081630094811414_1617261240739&pn=1&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:90+t:3+f:!50&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222&_=1617261240740
        StringBuffer urlParam = new StringBuffer();
//        String url = "http://api.fund.eastmoney.com/ztjj/GetZTJJList";
//        urlParam.append("callback=jQuery311015658602123786958_1591838943711&callbackname=fundData");
//        urlParam.append("&st="+findDateType);//查询类型
//        urlParam.append("&_=1614523183291");

        String url = "http://70.push2.eastmoney.com/api/qt/clist/get";
        urlParam.append("cb=jQuery1124026081630094811414_1617261240739" +
                "&pn=1" +//页数
                "&pz=" + endCount +//每页数量
                "&po=1" +//pageorder:页面排序：0-正序；1-倒序
                "&np=1" +
                "&ut=bd1d9ddb04089700cf9c27f6f7426281" +
                "&fltt=2" +//浮点数精度
                "&invt=3" +//显示格式：-；0.0
                "&fid=f3" +//排序字段
//                "&fs=m:90+t:2+f:!50" +
                "&fs=m:90+t:3+f:!50" +
                "&fields=" +
                "f1,f2,f3,f4,f5,f6,f7,f8,f9," +
                "f10,f11,f12,f13,f14,f15,f16,f17,f18,f19," +
                "f20,f21,f22,f23,f24,f25,f26,f27,f28,f29," +
                "f30,f31,f32,f33,f34,f35,f36,f37,f38,f39," +
                "f40,f41,f42,f43,f44,f45,f46,f47,f48,f49," +
                "f50,f51,f52,f53,f54,f55,f56,f57,f58,f59," +
                "f60,f61,f62,f63,f64,f65,f66,f67,f68,f69," +
                "f70,f71,f72,f73,f74,f75,f76,f77,f78,f79," +
                "f80,f81,f82,f83,f84,f85,f86,f87,f88,f89," +
                "f90,f91,f92,f93,f94,f95,f96,f97,f98,f99," +
                "f100,f101,f102,f103,f104,f105,f106,f107,f108,f109" + "," +
                "f110,f111,f112,f113,f114,f115,f116,f117,f118,f119" + "," +
                "f120,f121,f122,f123,f124,f125,f126,f127,f128,f129" + "," +
                "f130,f131,f132,f133,f134,f135,f136,f137,f138,f139" + "," +
                "f140,f141,f142,f143,f144,f145,f146,f147,f148,f149" + "," +
                "f150,f151,f152,f153,f154,f155,f156,f157,f158,f159" + "," +
                "f160,f161,f162,f163,f164,f165,f166,f167,f168,f169" + "," +
                "f170,f171,f172,f173,f174,f175,f176,f177,f178,f179" + "," +
                "f180,f181,f182,f183,f184,f185,f186,f187,f188,f189" + "," +
                "f190,f191,f192,f193,f194,f195,f196,f197,f198,f199" + "," +
                "f200,f201,f202,f203,f204,f205,f206,f207,f208,f209" + "," +
                "f210,f211,f212,f213,f214,f215,f216,f217,f218,f219" + "," +
                "f220,f221,f222,f223,f224,f225,f226,f227,f228,f229" +
                "&_=" + curTime);
        String rs = "";
        JSONObject rsJsonObj = null;
        JSONArray jsonArrayBiz = new JSONArray();
        for (int i = 0; i < 10; i++) {
            rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);//返回结果
            if (rs.startsWith("jQuery")) {
                rs = rs.substring(rs.indexOf("{"));
            }
            if (rs.endsWith(");")) {
                rs = rs.substring(0, rs.length() - 2);
            }
            rsJsonObj = JSONObject.parseObject(rs);
            if (rs == null || rsJsonObj == null || !rsJsonObj.containsKey("data")) {
                System.out.println("查询数据异常，重新查询：" + rs);
                continue;
            } else {
                break;
            }
        }

        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
        List<RankBizDataDiff> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankBizDataDiff.class);
        for (RankBizDataDiff row : rankBizDataDiffList) {
//            row.setRs(rs);
            row.setDate(date);
            row.setMonth(DateUtil.getYearMonth(date, DateUtil.YYYY_MM_DD));
            row.setWeekYear(DateUtil.getYearWeek(date, DateUtil.YYYY_MM_DD));
            row.setWeek(DateUtil.getWeekByYyyyMmDd(date, DateUtil.YYYY_MM_DD));
            row.setType(type);
//            System.out.println(JSON.toJSON(row));//每个行业一行数据
            JSONObject jsonObjectBiz = new JSONObject();
            jsonObjectBiz.put("name", row.getF14());
            jsonObjectBiz.put("rate", row.getF3());
            jsonArrayBiz.add(jsonObjectBiz);
        }
//        System.out.println(jsonArrayBiz);//行业排行数组

        for (Object obj : jsonArrayBiz) {
            JSONObject jsonObjectBiz = (JSONObject) obj;
//            System.out.println(jsonObjectBiz);
        }

        return rankBizDataDiffList;
    }

    public static void main(String[] args) {
        updateFuQuanBizByZqdm();
    }

    /**
     * 复权更新-特定编码-特定日期
     */
    private static void updateFuQuanBizByZqdm() {
        List<BizDto> rsList = new ArrayList<>();
        BizDto dto = new BizDto();
        dto.setF12("512100");
        dto.setF14("XXX");
        dto.setF2(new BigDecimal("1"));
        rsList.add(dto);
        String begDate = "2022-09-05";
        int year = DateUtil.getCurYear();//2021
        int month = 7;//DateUtil.getCurMonth()
        int day = 1;//DateUtil.getCurDay()
        for (int i = 0; i < 365; i++) {
            begDate = DateUtil.getDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, year, month, day, -i);//查询新增交易的开始时间
            BizService.updateFuQuanBiz(rsList, 2, begDate);//更新复权：前复权，检查当日K线与数据库的数据是否相符，如果不符，进行复权更新

        }

    }

}
