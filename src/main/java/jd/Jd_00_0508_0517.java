package jd;

import utils.Content;
import utils.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jd_00_0508_0517 {
    /**
     *
     */
    static String SOURCE_JD = "1";

    public static void main(String[] args) {
        List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
//        String endDateToday = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String startDate = "2020-05-08";
        String endDate = "2020-05-17";

        {
        paramList.add(handlertTtjjHasSxf(startDate, endDate, "003860", "招商招旭纯债C(003860)",
                "872.29", "1000", "2020-02-21", "25", "16", "0"));
        paramList.add(handlertTtjjHasSxf(startDate, endDate, "164402", "前海开源中航军工(164402)",
                "408.76", "444.79", "2019-09-09", "12", "13", "0"));
    }

//        {
//            //清仓
//            paramList.add(handlertTtjjHasSxf(startDate, "2020-04-29", "004593", "广发全指工业ETF联接A(004593)",
//                    "99.93", "100", "2017-05-21", "4", "5", "0"));
//        }

//        {
//
//            String startDateQdii = "2020-04-23";
//            String endDateQdii = "2020-04-30";
//            paramList.add(handlertTtjjHasSxf(startDateQdii, endDateQdii, "164906", "交银中证海外中国互联网指数(164906)",
//                    "72.59", "100", "2020-03-03", "40", "1", "0"));
//        }

        //ttjj-insert
        for (Map<String, String> stringStringMap : paramList) {
            byte[] bytes = "".getBytes();
            HttpUtil.sendPostTtjj(stringStringMap.get(Content.lsjzUrl), bytes, stringStringMap);
            System.out.println();
        }

    }

    /**
     * 含手续费-基金买入
     *
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
    private static Map<String, String> handlertTtjjHasSxf(String startDate, String endDate, String jjCode, String name,
                                                          String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID, String sxf) {
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
        rs.put(Content.SOURCE, SOURCE_JD);
        rs.put(Content.SXF, sxf);
        return rs;
    }


}
