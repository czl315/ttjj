package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.time.DateUtils;
import ttjj.dto.RankBizDataDiff;
import utils.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 主题排行
 */
public class BizRankDemo {


    /**
     * ttjj
     *
     * @param args args
     */
    public static void main(String[] args) {
//        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String date = "20210416";
//        List<RankBizDataDiff> rankBizDataDiffListBiz = listBiz(100);//查询主题排名by时间类型、显示个数
//        //显示业务排行-插入sql
//        showBizSql(rankBizDataDiffListBiz,"hy");//hy-行业
//
//        List<RankBizDataDiff> rankBizDataDiffListConcept = listConcept(500);//查询主题排名by时间类型、显示个数
//        //显示业务排行-插入sql
//        showBizSql(rankBizDataDiffListConcept,"gn");

        List<RankBizDataDiff> rankEtf = listEtf(1000);//2021-04-16:425;
        showBizSql(date, rankEtf, "etf");//etf-etf指数基金场内

    }

    private static List<RankBizDataDiff> listEtf(int pageSize) {
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
                "f190,f191,f192,f193,f194,f195,f196,f197,f198,f199"+ "," +
                "f200,f201,f202,f203,f204,f205,f206,f207,f208,f209" + "," +
                "f210,f211,f212,f213,f214,f215,f216,f217,f218,f219" + "," +
                "f220,f221,f222,f223,f224,f225,f226,f227,f228,f229" +
//                "f230,f231,f232,f233,f234,f235,f236,f237,f238,f239" +

//                "f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222" +

                "&fields=" +
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
//            row.setRs(rs);
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
                        "`rs`" +
                        ",`date`" +
                        ",`type`" +
                        ",`order_num`" +
                        ",`f1`,`f2`,`f3`,`f4`" +
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
                        " '" + (entity.getRs() == null ? "" : entity.getRs()) + "'" +
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
     * @param endCount
     */
    private static List<RankBizDataDiff> listBiz(int endCount) {
        //http://28.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112408110589206747254_1616379873172&pn=1&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:90+t:2+f:!50&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222&_=1616379873199
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
                "&invt=2" +//显示格式：-；0.0
                "&fid=f3" +//排序字段
                "&fs=m:90+t:2+f:!50" +
                "&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f30,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222" +
                "&_=1616379873199");
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
            row.setRs(rs);
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
     * 查询排名-概念板块
     *
     * @param endCount
     */
    private static List<RankBizDataDiff> listConcept(int endCount) {
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
                "&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f30,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222" +
                "&_=1616379873199");
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
            row.setRs(rs);
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
}
