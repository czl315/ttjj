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
public class BizRank {


    /**
     * ttjj
     *
     * @param args args
     */
    public static void main(String[] args) {
//        System.out.println("查询主题排名:");
        List<RankBizDataDiff> rankBizDataDiffListBiz = listBiz(100);//查询主题排名by时间类型、显示个数
        //显示业务排行-插入sql
        showBizSql(rankBizDataDiffListBiz,"hy");

        List<RankBizDataDiff> rankBizDataDiffListConcept = listConcept(500);//查询主题排名by时间类型、显示个数
        //显示业务排行-插入sql
        showBizSql(rankBizDataDiffListConcept,"gn");
    }

    /**
     * 显示业务排行-插入sql
     *
     * @param rankBizDataDiffList
     * @param queryType
     */
    private static void showBizSql(List<RankBizDataDiff> rankBizDataDiffList, String queryType) {
        int orderNum = 0;//序号
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        for (RankBizDataDiff entity : rankBizDataDiffList) {
            orderNum++;
            //显示插入数据库语句
            {
                System.out.println("INSERT INTO `bank19`.`rank_st_biz`(" +
                        "`rs`" +
                        ",`date`" +
                        ",`type`" +
                        ",`order_num`" +
                        ",`f2`" +
                        ",`f3`" +
                        ",`f4`" +
                        ",`f5`" +
                        ",`f6`" +
                        ",`f7`" +
                        ",`f8`" +
                        ",`f9`" +
                        ",`f10`" +
                        ",`f11`" +
                        ",`f12`" +
                        ",`f13`" +
                        ",`f14`" +
                        ",`f15`" +
                        ",`f16`" +
                        ",`f17`" +
                        ",`f18`" +
                        ",`f20`" +
                        ",`f21`" +
                        ",`f22`" +
                        ",`f23`" +
                        ",`f24`" +
                        ",`f25`" +
                        ",`f26`" +
                        ",`f33`" +
                        ",`f62`" +
                        ",`f104`" +
                        ",`f105`" +
                        ",`f107`" +
                        ",`f115`" +
                        ",`f124`" +
                        ",`f128`" +
                        ",`f140`" +
                        ",`f141`" +
                        ",`f136`" +
                        ",`f152`" +
                        ",`f207`" +
                        ",`f208`" +
                        ",`f209`" +
                        ",`f222`" +
                        ") VALUES (" +
                        " '" + JSON.toJSONString(entity) + "'" +
                        " ,'" + today + "'" +
                        " ,'" + queryType + "'" +
                        " ," + orderNum + "" +
                        " ," + entity.getF2() + "" +
                        " ," + entity.getF3() + "" +
                        " ," + entity.getF4() + "" +
                        " ," + entity.getF5() + "" +
                        " ," + entity.getF6() + "" +
                        " ," + entity.getF7() + "" +
                        " ," + entity.getF8() + "" +
                        " ," + entity.getF9() + "" +
                        " ," + entity.getF10() + "" +
                        " ," + entity.getF11() + "" +
                        " ,'" + entity.getF12() + "'" +
                        " ," + entity.getF13() + "" +
                        " ,'" + entity.getF14() + "'" +
                        " ," + entity.getF15() + "" +
                        " ," + entity.getF16() + "" +
                        " ," + entity.getF17() + "" +
                        " ," + entity.getF18() + "" +
                        " ," + entity.getF20() + "" +
                        " ," + entity.getF21() + "" +
                        " ," + entity.getF22() + "" +
                        " ,'" + entity.getF23() + "'" +
                        " ," + entity.getF24() + "" +
                        " ," + entity.getF25() + "" +
                        " ,'" + entity.getF26() + "'" +
                        " ," + entity.getF33() + "" +
                        " ," + entity.getF62() + "" +
                        " ," + entity.getF104() + "" +
                        " ," + entity.getF105() + "" +
                        " ," + entity.getF107() + "" +
                        " ,'" + entity.getF115() + "'" +
                        " ," + entity.getF124() + "" +
                        " ,'" + entity.getF128() + "'" +
                        " ,'" + entity.getF140() + "'" +
                        " ," + entity.getF141() + "" +
                        " ," + entity.getF136() + "" +
                        " ," + entity.getF152() + "" +
                        " ,'" + entity.getF207() + "'" +
                        " ,'" + entity.getF208() + "'" +
                        " ," + entity.getF209() + "" +
                        " ," + entity.getF222() + "" +
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
