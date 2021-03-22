package ttjj.topic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ttjj.dto.RankBizDataDiff;
import utils.HttpUtil;

import java.util.List;

/**
 * 主题排行
 */
public class BizRank {


    /**
     * ttjj
     * @param args args
     */
    public static void main(String[] args) {
        int endCount = 100;//显示数量

//        System.out.println("查询主题排名:");
        findTtjjTopicByYesterday(endCount);//查询主题排名by时间类型、显示个数
    }

    /**
     * 查询昨日主题排名
     * @param endCount
     */
    private static void findTtjjTopicByYesterday(int endCount) {
        //http://28.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112408110589206747254_1616379873172&pn=1&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:90+t:2+f:!50&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222&_=1616379873199
        StringBuffer urlParam = new StringBuffer();
//        String url = "http://api.fund.eastmoney.com/ztjj/GetZTJJList";
//        urlParam.append("callback=jQuery311015658602123786958_1591838943711&callbackname=fundData");
//        urlParam.append("&st="+findDateType);//查询类型
//        urlParam.append("&_=1614523183291");

        String url = "http://28.push2.eastmoney.com/api/qt/clist/get";
        urlParam.append("cb=jQuery112408110589206747254_1616379873172" +
                "&pn=1" +
                "&pz=200" +
                "&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3" +
                "&fs=m:90+t:2+f:!50" +
                "&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222" +
                "&_=1616379873199");
        String rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);
        if(rs == null){
            return;
        }
        if(rs.startsWith("jQuery")){
            rs = rs.substring(rs.indexOf("{"));
        }
        if(rs.endsWith(");")){
            rs = rs.substring(0,rs.length()-2);
        }
//        System.out.println(rs);//返回结果
        int tempInt = 0;
        JSONArray jsonArrayBiz = new JSONArray();
        JSONObject rsTop = new JSONObject();//顶层结果
        JSONObject rsJsonObj = JSONObject.parseObject(rs);
        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
        List<RankBizDataDiff> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankBizDataDiff.class);
        for (RankBizDataDiff row : rankBizDataDiffList) {
            tempInt++;
            if(tempInt>endCount ){
                break;
            }
//            System.out.println(row);//每个行业一行数据
            JSONObject jsonObjectBiz = new JSONObject();
            jsonObjectBiz.put("name",row.getF14());
            jsonObjectBiz.put("rate",row.getF3());
            jsonArrayBiz.add(jsonObjectBiz);
        }

//        System.out.println(jsonArrayBiz);//行业排行数组
        for (Object obj : jsonArrayBiz) {
            JSONObject jsonObjectBiz =(JSONObject)obj;
            System.out.println(jsonObjectBiz);
        }
    }
}
