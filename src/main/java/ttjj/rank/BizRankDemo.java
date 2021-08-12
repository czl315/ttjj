package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.dao.BizRankDao;
import ttjj.dao.MyBatisUtils;
import ttjj.dto.RankBizDataDiff;
import ttjj.dto.StockTrade;
import utils.Content;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 主题排行
 */
public class BizRankDemo {
    /**
     * sqlSessionFactory mybatis
     */
    static SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();

    /**
     * ttjj
     *
     * @param args args
     */
    public static void main(String[] args) {
//        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        String date = "20210604";

        boolean insertDbTodayBiz = true;
//        boolean insertDbTodayBiz = false;
        boolean insertDbTodayConcept = true;
//        boolean insertDbTodayConcept = false;
        boolean insertDbTodayEtf = true;
//        boolean insertDbTodayEtf = false;
        boolean updateDbTodayEtfNetMaxMinTimeFlag = true;
//        boolean updateDbTodayEtfNetMaxMinTimeFlag = false;

        int updateDbEtfNetDays = 365;
//        int updateDbEtfNetDays = 1;
//        int updateDbEtfNetDays = 0;

        if (insertDbTodayBiz) {
            List<RankBizDataDiff> rankBizDataDiffListBiz = listBiz(date, "hy", 999);//查询主题排名by时间类型、显示个数
            //db-插入
            BizRankDao.insertDbBiz(rankBizDataDiffListBiz);//hy-行业
//            showBizSql(date, rankBizDataDiffListBiz, "hy");//显示sql-业务排行-插入
        }

        if (insertDbTodayConcept) {
            List<RankBizDataDiff> rankBizDataDiffListConcept = listConcept(date, "gn", 999);//查询主题排名by时间类型、显示个数
            //db-插入
            BizRankDao.insertDbBiz(rankBizDataDiffListConcept);//hy-行业
//            showBizSql(date, rankBizDataDiffListConcept, "gn");//显示业务排行-插入sql
        }

        List<RankBizDataDiff> rankEtf = listEtf(date, "etf", 999);//2021-04-16:425;
        if (insertDbTodayEtf) {
            BizRankDao.insertDbBiz(
                    rankEtf);//hy-行业
//            showBizSql(date, rankEtf, "etf");//新增插入-etf指数基金场内
        }

        if (updateDbEtfNetDays == 1) {
            // 更新最新净值-限定时间段的最大最小净值
            String table = "rank_st_biz";
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 1, "LAST_NET", "LAST_NET", "LAST_NET", "LAST_NET");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 1, "NET_MIN_1", "NET_MAX_1", "NET_MIN_CLOS_1", "NET_MAX_CLOS_1");

        }
        if (updateDbEtfNetDays == 365) {
            // 更新最新净值-限定时间段的最大最小净值
            String table = "rank_st_biz";
//            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 1, "LAST_NET", "LAST_NET", "LAST_NET", "LAST_NET");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 1, "NET_MIN_1", "NET_MAX_1", "NET_MIN_CLOS_1", "NET_MAX_CLOS_1");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 7, "NET_MIN_7", "NET_MAX_7", "NET_MIN_CLOS_7", "NET_MAX_CLOS_7");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 14, "NET_MIN_14", "NET_MAX_14", "NET_MIN_CLOS_14", "NET_MAX_CLOS_14");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 30, "NET_MIN_30", "NET_MAX_30", "NET_MIN_CLOS_30", "NET_MAX_CLOS_30");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 60, "NET_MIN_60", "NET_MAX_60", "NET_MIN_CLOS_60", "NET_MAX_CLOS_60");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 90, "NET_MIN_90", "NET_MAX_90", "NET_MIN_CLOS_90", "NET_MAX_CLOS_90");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 180, "NET_MIN_180", "NET_MAX_180", "NET_MIN_CLOS_180", "NET_MAX_CLOS_180");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 365, "NET_MIN_360", "NET_MAX_360", "NET_MIN_CLOS_360", "NET_MAX_CLOS_360");
            for (RankBizDataDiff rankBizDataDiff : rankEtf) {
                BizRankDao.updateEtfNet(rankBizDataDiff);
            }
        }

        if (updateDbTodayEtfNetMaxMinTimeFlag) {
            String curdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//            String curdate = "2021-06-04";
            updateDbTodayEtfNetMaxMinTime(curdate, rankEtf);
        }

    }

    private static void updateDbTodayEtfNetMaxMinTime(String date, List<RankBizDataDiff> rankEtf) {
        for (RankBizDataDiff rankBizDataDiff : rankEtf) {
            rankBizDataDiff.setDate(date);
            findTrends(rankBizDataDiff, "", "fupan", rankBizDataDiff.getF12(), 1, date, "pt_sh_time_min", "pt_sh_time_max");//查询指定指数的最大值时间、最小值时间
            BizRankDao.updateDbEtfNetMaxMinTimeByDate(rankBizDataDiff);
        }
    }


    /**
     * 更新最新净值-限定时间段的最大最小净值
     *
     * @param date
     * @param rankEtf
     * @param table
     * @param days
     * @param dbFieldLastNetMin
     */
    private static void showUpdateDbMaxMinNetByDays(String date, List<RankBizDataDiff> rankEtf, String table, int days, String dbFieldLastNetMin, String dbFieldLastNetMax, String dbFieldLastNetMinClose, String dbFieldLastNetMaxClose) {
        for (RankBizDataDiff entity : rankEtf) {
            //查询 -限定时间段的最大最小净值
//            LsjzUtil.findJzMaxMin(fundTrade.getZqdm(), days);
            //k线
            String klt = "101";//klt=101:日;102:周;103:月;104:3月;105:6月;106:12月
            kline(entity, date, entity.getF12(), table, days, klt, dbFieldLastNetMin, dbFieldLastNetMax, dbFieldLastNetMinClose, dbFieldLastNetMaxClose);//沪深300
        }
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
        if (days == 1) {
            rankEtf.setLAST_NET(netCloseMin);
            rankEtf.setNET_MIN_1(minJz);
            rankEtf.setNET_MIN_CLOS_1(netCloseMin);
            rankEtf.setNET_MAX_1(maxJz);
            rankEtf.setNET_MAX_CLOS_1(netCloseMax);
        }
        if (days == 7) {
            rankEtf.setNET_MIN_7(minJz);
            rankEtf.setNET_MIN_CLOS_7(netCloseMin);
            rankEtf.setNET_MAX_7(maxJz);
            rankEtf.setNET_MAX_CLOS_7(netCloseMax);
        }
        if (days == 14) {
            rankEtf.setNET_MIN_14(minJz);
            rankEtf.setNET_MIN_CLOS_14(netCloseMin);
            rankEtf.setNET_MAX_14(maxJz);
            rankEtf.setNET_MAX_CLOS_14(netCloseMax);
        }
        if (days == 30) {
            rankEtf.setNET_MIN_30(minJz);
            rankEtf.setNET_MIN_CLOS_30(netCloseMin);
            rankEtf.setNET_MAX_30(maxJz);
            rankEtf.setNET_MAX_CLOS_30(netCloseMax);
        }
        if (days == 60) {
            rankEtf.setNET_MIN_60(minJz);
            rankEtf.setNET_MIN_CLOS_60(netCloseMin);
            rankEtf.setNET_MAX_60(maxJz);
            rankEtf.setNET_MAX_CLOS_60(netCloseMax);
        }
        if (days == 90) {
            rankEtf.setNET_MIN_90(minJz);
            rankEtf.setNET_MIN_CLOS_90(netCloseMin);
            rankEtf.setNET_MAX_90(maxJz);
            rankEtf.setNET_MAX_CLOS_90(netCloseMax);
        }
        if (days == 180) {
            rankEtf.setNET_MIN_180(minJz);
            rankEtf.setNET_MIN_CLOS_180(netCloseMin);
            rankEtf.setNET_MAX_180(maxJz);
            rankEtf.setNET_MAX_CLOS_180(netCloseMax);
        }
        if (days == 365) {
            rankEtf.setNET_MIN_360(minJz);
            rankEtf.setNET_MIN_CLOS_360(netCloseMin);
            rankEtf.setNET_MAX_360(maxJz);
            rankEtf.setNET_MAX_CLOS_360(netCloseMax);
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


    private static List<RankBizDataDiff> listEtf(String date, String type, int pageSize) {
//          http://32.push2.eastmoney.com/api/qt/clist/get?cb=jQuery11240476946102335426_1618637035810&pn=1&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=b:MK0021,b:MK0022,b:MK0023,b:MK0024&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f128,f136,f115,f152&_=1618637035811
        String url = "http://32.push2.eastmoney.com/api/qt/clist/get";
        StringBuffer urlParam = new StringBuffer();
        long curTime = System.currentTimeMillis();
        urlParam.append("cb=jQuery11240476946102335426_" + curTime +
                "&pn=1" +//页数
                "&pz=" + pageSize +//每页数量
                "&po=1" +//pageorder:页面排序：0-正序；1-倒序
                "&np=1" +//是否分页：1-分页
                "&ut=bd1d9ddb04089700cf9c27f6f7426281" +
                "&fltt=2" +//浮点数精度
                "&invt=3" +//显示格式：2-"-"；3-"0.0"
                "&fid=f3" +//排序字段
                "&fs=b:MK0021,b:MK0022,b:MK0023,b:MK0024" +
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
//                "f230,f231,f232,f233,f234,f235,f236,f237,f238,f239" +

//                "f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222" +

                "&_=" + curTime);
        String rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);
        if (rs == null) {
            return null;
        }
        if (rs.startsWith("jQuery")) {
            rs = rs.substring(rs.indexOf("{"));
        }
        if (rs.endsWith(");")) {
            rs = rs.substring(0, rs.length() - 2);
        }
//        System.out.println(rs);//返回结果
        JSONObject rsJsonObj = JSONObject.parseObject(rs);
        if (!rsJsonObj.containsKey("data")) {
            System.out.println("---------------data---error!!!!!");
        }
        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
        List<RankBizDataDiff> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankBizDataDiff.class);
        for (RankBizDataDiff row : rankBizDataDiffList) {
//            row.setRs(rs);
            row.setDate(date);
            row.setType(type);
//            System.out.println(JSON.toJSON(row));//每个行业一行数据
        }

        return rankBizDataDiffList;
    }

    /**
     * 显示业务排行-插入sql
     *
     * @param today
     * @param rankBizDataDiffList
     * @param queryType
     */
    private static void showBizSql(String today, List<RankBizDataDiff> rankBizDataDiffList, String queryType) {
        int orderNum = 0;//序号
        for (RankBizDataDiff entity : rankBizDataDiffList) {
            orderNum++;
            //显示插入数据库语句
            {
                System.out.println("INSERT INTO `bank19`.`rank_st_biz`(" +
                        "`rs`,`date`,`type`,`order_num`," +
                        "`f1`,`f2`,`f3`,`f4`" +
                        ",`f5`,`f6`,`f7`,`f8`,`f9`" +
                        ",`f10`,`f11`,`f12`,`f13`,`f14`" +
                        ",`f15`,`f16`,`f17`,`f18`,`f19`" +
                        ",`f20`,`f21`,`f22`,`f23`,`f24`" +
                        ",`f25`,`f26`,`f27`,`f28`,`f29`" +
                        ",`f30`,`f31`,`f32`,`f33`,`f34`" +
                        ",`f35`,`f36`,`f37`,`f38`,`f39`" +
                        ",`f60`,`f61`,`f62`,`f63`,`f64`" +
                        ",`f65`,`f66`,`f67`,`f68`,`f69`" +
                        ",`f70`,`f71`,`f72`,`f73`,`f74`" +
                        ",`f75`,`f76`,`f77`,`f78`,`f79`" +
                        ",`f80`,`f81`,`f82`,`f83`,`f84`" +
                        ",`f85`,`f86`,`f87`,`f88`,`f89`" +
                        ",`f90`,`f91`,`f92`,`f93`,`f94`" +
                        ",`f95`,`f96`,`f97`,`f98`,`f99`" +
                        ",`f104`" +
                        ",`f105`,`f107`,`f109`" +
                        ",`f110`,`f111`" +
                        ",`f115`" +
                        ",`f124`" +
                        ",`f127`,`f128`" +
                        ",`f136`,`f139`" +
                        ",`f140`,`f141`,`f142`,`f143`,`f144`" +
                        ",`f145`,`f148`,`f149`" +
                        ",`f152`" +
                        ",`f207`" +
                        ",`f208`" +
                        ",`f209`" +
                        ",`f222`,`f223`" +
                        ") VALUES (" +
                        " ''" +
                        " ,'" + today + "'" +
                        " ,'" + queryType + "'" +
                        " ," + orderNum + "" +
                        " ," + entity.getF1() + "," + entity.getF2() + "," + entity.getF3() + "," + entity.getF4() + "," + entity.getF5() + "" +
                        " ," + entity.getF6() + "," + entity.getF7() + "," + entity.getF8() + "," + entity.getF9() +
                        "," + entity.getF10() + "," + entity.getF11() + ",'" + entity.getF12() + "'," + entity.getF13() + ",'" + entity.getF14() + "'" +
                        " ," + entity.getF15() + "," + entity.getF16() + "," + entity.getF17() + "," + entity.getF18() + "," + entity.getF19() +
                        " ," + entity.getF20() + "," + entity.getF21() + "," + entity.getF22() + ",'" + (entity.getF23() == null ? "" : entity.getF23()) + "'," + entity.getF24() + "" +
                        " ," + entity.getF25() + ",'" + (entity.getF26() == null ? "" : entity.getF26()) + "'," + entity.getF27() + "," + entity.getF28() + "," + entity.getF29() + "" +
                        " ," + entity.getF30() + "," + entity.getF31() + "," + entity.getF32() + "," + entity.getF33() + "," + entity.getF34() + "" +
                        " ," + entity.getF35() + "," + entity.getF36() + "," + entity.getF37() + "," + entity.getF38() + "," + entity.getF39() + "" +
                        " ," + entity.getF60() + "," + entity.getF61() + "," + entity.getF62() + "," + entity.getF63() + "," + entity.getF64() + "" +
                        " ," + entity.getF65() + "," + entity.getF66() + "," + entity.getF67() + "," + entity.getF68() + "," + entity.getF69() + "" +
                        " ," + entity.getF70() + "," + entity.getF71() + "," + entity.getF72() + "," + entity.getF73() + "," + entity.getF74() + "" +
                        " ," + entity.getF75() + "," + entity.getF76() + "," + entity.getF77() + "," + entity.getF78() + "," + entity.getF79() + "" +
                        " ," + entity.getF80() + "," + entity.getF81() + "," + entity.getF82() + "," + entity.getF83() + "," + entity.getF84() + "" +
                        " ," + entity.getF85() + "," + entity.getF86() + "," + entity.getF87() + "," + entity.getF88() + "," + entity.getF89() + "" +
                        " ," + entity.getF90() + "," + entity.getF91() + "," + entity.getF92() + "," + entity.getF93() + "," + entity.getF94() + "" +
                        " ," + entity.getF95() + "," + entity.getF96() + "," + entity.getF97() + "," + entity.getF98() + "," + entity.getF99() + "" +
                        " ," + entity.getF104() + "" +
                        " ," + entity.getF105() + "," + entity.getF107() + "" + "," + entity.getF109() + "" +
                        " ," + entity.getF110() + "," + entity.getF111() +
                        " ,'" + (entity.getF115() == null ? "" : entity.getF115()) + "'" +
                        " ," + entity.getF124() + "" +
                        " ," + entity.getF127() + "," + "'" + (entity.getF128() == null ? "" : entity.getF128()) + "'" +
                        " ," + entity.getF136() + "," + entity.getF139() + "" +
                        " ,'" + (entity.getF140() == null ? "" : entity.getF140()) + "'" + "," + entity.getF141() + "," + entity.getF142() + "," + entity.getF143() + "," + entity.getF144() + "" +
                        " ," + entity.getF145() + "" + " ," + entity.getF148() + "" + " ," + entity.getF149() + "" +
                        " ," + entity.getF152() + "" +
                        " ,'" + (entity.getF207() == null ? "" : entity.getF207()) + "'" +
                        " ,'" + (entity.getF208() == null ? "" : entity.getF208()) + "'" +
                        " ," + entity.getF209() + "" +
                        " ," + entity.getF222() + "" + " ," + entity.getF223() +
                        ");");
            }

        }
    }

    /**
     * 查询昨日主题排名
     *
     * @param date
     * @param type
     * @param endCount
     */
    private static List<RankBizDataDiff> listBiz(String date, String type, int endCount) {
        //http://28.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112408110589206747254_1616379873172&pn=1&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:90+t:2+f:!50&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222&_=1616379873199
        long curTime = System.currentTimeMillis();
        StringBuffer urlParam = new StringBuffer();
//        String url = "http://api.fund.eastmoney.com/ztjj/GetZTJJList";
//        urlParam.append("callback=jQuery311015658602123786958_1591838943711&callbackname=fundData");
//        urlParam.append("&st="+findDateType);//查询类型
//        urlParam.append("&_=1614523183291");
        String url = "http://28.push2.eastmoney.com/api/qt/clist/get";
        urlParam.append("cb=jQuery112408110589206747254_1616379873172" +
                "&pn=1" +//页数
                "&pz=" + endCount +//每页数量
                "&po=1" +//pageorder:页面排序：0-正序；1-倒序
                "&np=1" +
                "&ut=bd1d9ddb04089700cf9c27f6f7426281" +
                "&fltt=2" +//浮点数精度
                "&invt=3" +//显示格式：-；0.0
                "&fid=f3" +//排序字段
                "&fs=m:90+t:2+f:!50" +
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
        String rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);
        if (rs == null) {
            return null;
        }
        if (rs.startsWith("jQuery")) {
            rs = rs.substring(rs.indexOf("{"));
        }
        if (rs.endsWith(");")) {
            rs = rs.substring(0, rs.length() - 2);
        }
//        System.out.println(rs);//返回结果
        JSONObject rsJsonObj = JSONObject.parseObject(rs);
        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
        List<RankBizDataDiff> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankBizDataDiff.class);
        for (RankBizDataDiff row : rankBizDataDiffList) {
//            System.out.println(JSON.toJSON(row));//每个行业一行数据
            row.setDate(date);
            row.setType(type);
        }
        return rankBizDataDiffList;
    }

    /**
     * 查询排名-概念板块
     *
     * @param date
     * @param type
     * @param endCount
     */
    private static List<RankBizDataDiff> listConcept(String date, String type, int endCount) {
        long curTime = System.currentTimeMillis();
        //http://28.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112408110589206747254_1616379873172&pn=1&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:90+t:2+f:!50&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222&_=1616379873199
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
        String rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);
        if (rs == null) {
            return null;
        }
        if (rs.startsWith("jQuery")) {
            rs = rs.substring(rs.indexOf("{"));
        }
        if (rs.endsWith(");")) {
            rs = rs.substring(0, rs.length() - 2);
        }
//        System.out.println(rs);//返回结果
        JSONArray jsonArrayBiz = new JSONArray();
        JSONObject rsJsonObj = JSONObject.parseObject(rs);
        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
        List<RankBizDataDiff> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankBizDataDiff.class);
        for (RankBizDataDiff row : rankBizDataDiffList) {
//            row.setRs(rs);
            row.setDate(date);
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

    /**
     * 查询指定指数的最大值时间、最小值时间
     *
     * @param rankBizDataDiff
     * @param cookie
     * @param zhiShu
     * @param days
     * @param curDate
     * @param field1
     * @param field2
     */
    private static void findTrends(RankBizDataDiff rankBizDataDiff, String cookie, String table, String zhiShu, int days, String curDate, String field1, String field2) {
        long curTime = System.currentTimeMillis();
        String code = "";
        String url = "http://push2his.eastmoney.com/api/qt/stock/trends2/get?cb=jQuery112408829480231577647_" + curTime;
        if (zhiShu.startsWith("5") || zhiShu.startsWith("6")) {
            code = "&secid=" + "1." + zhiShu;
        } else {
            code = "&secid=" + "0." + zhiShu;
        }
        url = url + code +
                "&ut=fa5fd1943c7b386f172d6893dbfba10b" +
                "&fields1=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11" +
                "&fields2=f51,f53,f56,f58" +
                "&iscr=0" +
                "&ndays=" + days +
                "&_=" + curTime +
                "";
//        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("&StartDate=").append(startDate);

//        System.out.println("请求url:"+url);
        String rs = HttpUtil.sendGet(url, "", cookie);
        /**
         * 如果返回异常，n次重试
         */
        for (int i = 0; i < 10; i++) {
            if (StringUtils.isBlank(rs)) {
                rs = HttpUtil.sendGet(url, "", cookie);
            } else {
                break;
            }
        }
        if (!rs.contains("{")) {
            System.out.println("返回数据异常：" + JSON.toJSONString(rs));
            return;
        }
        String rsJson = rs.substring(rs.indexOf("{"));
        rsJson = rsJson.replace(");", "");
//        System.out.println("szKline:"+rsJson);

        List<String> timePointList = new ArrayList<String>();
        JSONObject szzzMonthJson = JSON.parseObject(rsJson);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
        JSONArray trends = JSON.parseArray(szzzMonthDataJson.getString("trends"));
//        System.out.println(trends);
        for (Object timePoint : trends) {
            String timePointStr = (String) timePoint;
            timePointList.add(timePointStr);
        }

        BigDecimal dayPointMax = new BigDecimal(0);
        BigDecimal dayPointMin = new BigDecimal(0);
        String dayPointTimeMax = "";
        String dayPointTimeMin = "";
        boolean init = false;
        for (int i = 0; i <= timePointList.size() - 1; i++) {
            //"2021-04-23 09:30,2857.23,60147,2857.226"
            String timePoint = timePointList.get(i);
            String[] timePointArray = timePoint.split(",");
            String dateTime = timePointArray[0];
            String date = dateTime.substring(0, 10);
            //指定具体日期
            if (!curDate.equals(date)) {
                continue;
            }
            String point = timePointArray[1];
//            String count = timePointArray[2];
//            String point2 = timePointArray[3];
            //初始化
            if (!init) {
                init = true;
                dayPointMax = new BigDecimal(point);
                dayPointTimeMax = dateTime.substring(dateTime.indexOf(" "));
                dayPointMin = new BigDecimal(point);
                dayPointTimeMin = dateTime.substring(dateTime.indexOf(" "));
            }

            //比较最大最小
            BigDecimal curPoint = new BigDecimal(point);
            if (curPoint.compareTo(dayPointMin) <= 0) {
                dayPointMin = new BigDecimal(point);
                dayPointTimeMin = dateTime.substring(dateTime.indexOf(" ") + 1);
                rankBizDataDiff.setPt_time_min(dayPointTimeMin);
            }
            if (curPoint.compareTo(dayPointMax) >= 0) {
                dayPointMax = new BigDecimal(point);
                dayPointTimeMax = dateTime.substring(dateTime.indexOf(" ") + 1);
                rankBizDataDiff.setPt_time_max(dayPointTimeMax);
            }
        }
//        System.out.println("最大值：" + dayPointTimeMax + "---" + dayPointMax);
//        System.out.println("最小值：" + dayPointTimeMin + "---" + dayPointMin);

        //sql
//        System.out.println("UPDATE `" + table + "` " +
//                "SET " +
//                "`" + field1 + "`='" + dayPointTimeMin + "'" + "," +
//                "`" + field2 + "`='" + dayPointTimeMax + "'" +
//                " WHERE (`CODE`='" + curDate + "') AND fupan.period='1'" + " AND fupan.TYPE=1;");
    }

}
