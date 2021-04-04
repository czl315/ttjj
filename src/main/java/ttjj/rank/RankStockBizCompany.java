package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ttjj.dto.RankBizDataDiff;
import utils.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 排行-行业股票-公司-每日明细
 */
public class RankStockBizCompany {


    /**
     * @param args
     */
    public static void main(String[] args) {
//        System.out.println("查询主题排名:");
        List<RankBizDataDiff> rankBizDataDiffListBiz = listRankStockByBiz(1000, "BK0459");//查询主题排名by时间类型、显示个数
        //显示业务排行-插入sql
        showBizSql(rankBizDataDiffListBiz, "BK0459");

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
                System.out.println("INSERT INTO `bank19`.`rank_st_biz_com`(" +
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
     */
    private static List<RankBizDataDiff> listRankStockByBiz(int pageSize, String biz) {
        //http://push2.eastmoney.com/api/qt/clist/get?cb=jQuery112307730222083783287_1617467610779&fid=f62&po=1&pz=50&pn=1&np=1&fltt=2&invt=2&ut=b2884a393a59ad64002292a3e90d46a5&fs=b%3ABK0891&fields=f12%2Cf14%2Cf2%2Cf3%2Cf62%2Cf184%2Cf66%2Cf69%2Cf72%2Cf75%2Cf78%2Cf81%2Cf84%2Cf87%2Cf204%2Cf205%2Cf124
        StringBuffer urlParam = new StringBuffer();
        String url = "http://push2.eastmoney.com/api/qt/clist/get?";
        urlParam.append("cb=jQuery112307730222083783287_1617467610779" +
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
                "&fields=f50,f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f30,f11,f62,f72,f75,f78,f81,f84,f87,f204,f205,f124,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222" +
                "");
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

        JSONObject rsJsonObj = JSONObject.parseObject(rs);
//        System.out.println(rs);//返回结果
        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
        List<RankBizDataDiff> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankBizDataDiff.class);
//        for (RankBizDataDiff row : rankBizDataDiffList) {
//            System.out.println(JSON.toJSON(row));//每个行业一行数据
//        }
        return rankBizDataDiffList;
    }

}
