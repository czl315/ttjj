package ttjj.histroy;

import utils.Content;
import utils.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.*;

public class Jd_00_gnjj {

    public static void main(String[] args) {
        List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
        String endDateToday = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        String startDate = "2020-04-17";
        paramList.add(handlertTtjj(startDate, endDateToday, "003860", "招商招旭纯债C(003860)",
                "872.29", "1000", "2020-02-21", "25", "16"));
        paramList.add(handlertTtjj(startDate, endDateToday, "164402", "前海开源中航军工(164402)",
                "408.76", "1000", "2019-09-09", "12", "13"));
        paramList.add(handlertTtjj(startDate, endDateToday, "004593", "广发全指工业ETF联接A(004593)",
                "99.93", "100", "2017-05-21", "4", "5"));


//        String qdiiStartDate = "2020-04-16";
//        //        //2020-04-21 全部赎回
//        paramList.add(handlertTtjj(qdiiStartDate, "2020-04-21", "164906", "交银中证海外中国互联网指数(164906)",
//                "1430.53", "1750.253455", "2019-09-10", "13", "1"));
//        //2020-04-21 全部赎回
//        paramList.add(handlertTtjj(qdiiStartDate, "2020-04-21", "005809", "前海开源裕源(FOF)(005809)",
//                "310.22", "436.78976", "2020-02-12", "23", "10"));
//        //2020-04-17 全部赎回
//        paramList.add(handlertTtjj(qdiiStartDate, "2020-04-17", "006327", "易方达中证海外联接人民币A(006327)",
//                "100", "117.9", "2020-02-10", "17", "14"));
//        //2020-04-10 全部赎回
//        paramList.add(handlertTtjj(startDate, endDateToday, "160644", "鹏华港美互联股票人民币(160644)",
//                "100", "104.2", "2020-04-09", "15", "16"));

        //ttjj-insert
        for (Map<String, String> stringStringMap : paramList) {
            byte[] bytes = "".getBytes();
            HttpUtil.sendPostTtjj(stringStringMap.get(Content.lsjzUrl), bytes, stringStringMap);
            System.out.println();
        }

    }

    /**
     * 封装参数
     * @param startDate
     * @param endDate
     * @param jjCode
     * @param name
     * @param canShare
     * @param BUY_COST
     * @param FIRST_NET_DATA
     * @param TRADE_ID
     * @param FD_ID
     * @return
     */
    public static Map<String, String> handlertTtjj(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, Content.SOURCE_JD);
        return rs;
    }

}
