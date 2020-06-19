package topic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import utils.HttpUtil;

/**
 * 主题排行
 */
public class TopicRank {


    /**
     * ttjj
     * @param args args
     */
    public static void main(String[] args) {
        int endCount = 1000;//显示数量

//        String findDateType ="SYL_D";//查询类型 SYL_D:日；SYL_Z：周；SYL_Y：月；SYL_3Y：季；SYL_6Y：半年；SYL_1N：1年；SYL_JN：今年；
//        String findDateType ="SYL_Z";//查询类型 SYL_D:日；SYL_Z：周；SYL_Y：月；SYL_3Y：季；SYL_6Y：半年；SYL_1N：1年；SYL_JN：今年；
        String findDateType ="SYL_D";//查询类型 SYL_D:日；SYL_Z：周；SYL_Y：月；SYL_3Y：季；SYL_6Y：半年；SYL_1N：1年；SYL_JN：今年；
        System.out.println("查询主题排名-日:");
        findTtjjTopicByYesterday(findDateType,endCount);//查询主题排名by时间类型、显示个数
//        System.out.println("查询主题排名-周:");
//        findTtjjTopicByYesterday("SYL_Z",endCount);//查询主题排名by时间类型、显示个数
//        System.out.println("查询主题排名-月:");
//        findTtjjTopicByYesterday("SYL_Y",endCount);//查询主题排名by时间类型、显示个数
//        System.out.println("查询主题排名-季:");
//        findTtjjTopicByYesterday("SYL_3Y",endCount);//查询主题排名by时间类型、显示个数
//        System.out.println("查询主题排名-半年:");
//        findTtjjTopicByYesterday("SYL_6Y",endCount);//查询主题排名by时间类型、显示个数
//        System.out.println("查询主题排名-1年:");
//        findTtjjTopicByYesterday("SYL_1N",endCount);//查询主题排名by时间类型、显示个数
//        System.out.println("查询主题排名-今年:");
//        findTtjjTopicByYesterday("SYL_JN",endCount);//查询主题排名by时间类型、显示个数

    }

    /**
     * 查询昨日主题排名
     * @param findDateType
     * @param endCount
     */
    private static void findTtjjTopicByYesterday(String findDateType, int endCount) {
        //查询日  ：https://fundztapi.eastmoney.com/fundspecialapi/fundspecialfundtopiclist.ashx?callback=jQuery31107334621007101361_1591854447815&callbackname=fundData&sort=SYL_D&sorttype=desc&pageindex=1&pagesize=500&dt=11&tt=0&rs=WRANK&_=1591854447818
        //查询周  ：https://fundztapi.eastmoney.com/fundspecialapi/fundspecialfundtopiclist.ashx?callback=jQuery3110927108387611935_1591854756830 &callbackname=fundData&sort=SYL_Z&sorttype=desc&pageindex=1&pagesize=500&dt=11&tt=0&rs=WRANK&_=1591854756832
        String url = "https://fundztapi.eastmoney.com/fundspecialapi/fundspecialfundtopiclist.ashx";
        StringBuffer urlParam = new StringBuffer();
        urlParam.append("callback=jQuery311015658602123786958_1591838943711&callbackname=fundData");
        urlParam.append("&sort="+findDateType);//查询类型
        urlParam.append("&sorttype=desc&pageindex=1&pagesize=500&dt=123&tt=0&rs=WRANK&_=1591838943713");
        String rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);
        if(rs == null){
            return;
        }
        if(rs.startsWith("jQuery")){
            rs = rs.substring(rs.indexOf("{"));
        }
        if(rs.endsWith(")")){
            rs = rs.substring(0,rs.length()-1);
        }
//        System.out.println(rs);//ttjj版块原始返回结果
        JSONObject rsTop = new JSONObject();//顶层结果
        JSONObject rsJsonObj = JSONObject.parseObject(rs);
        JSONArray jsonArray = rsJsonObj.getJSONArray("Datas");
        int tempInt = 0;
        JSONArray jsonArrayBiz = new JSONArray();
        for (Object rowObj : jsonArray) {
            tempInt++;
            if(tempInt>endCount ){
                break;
            }
            String row = (String)rowObj;
//            System.out.println(row);//每个行业一行数据
            JSONObject jsonObjectBiz = new JSONObject();
            if(row.contains(",")){
                String[] rowField =  row.split(",");
                jsonObjectBiz.put("name",rowField[1]);
                jsonObjectBiz.put("rate",Double.valueOf(rowField[2]));
                jsonArrayBiz.add(jsonObjectBiz);
            }
        }

        System.out.println(jsonArrayBiz);//行业排行数组
        rsTop.put("Topic10",jsonArrayBiz);
        System.out.println(rsTop);
        for (Object obj : jsonArrayBiz) {
            JSONObject jsonObjectBiz =(JSONObject)obj;
            System.out.println(jsonObjectBiz);
        }
    }
}
