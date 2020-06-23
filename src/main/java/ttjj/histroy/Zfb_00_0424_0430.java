package ttjj.histroy;

import utils.Content;
import utils.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zfb_00_0424_0430 {
    /**
     *
     */
    static String SOURCE_ZFB = "2";

    public static void main(String[] args) {
        List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
//        String endDateToday = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String startDate = "2020-04-24";
        String endDate = "2020-04-30";

//        {
//            paramList.add(handlertTtjjHasSxf("2020-04-24", "2020-04-27", "003547", "鹏华丰禄债券(003547)",
//                    "20526.50", "22200", "2020-03-11", "51", "27", "0"));
//            paramList.add(handlertTtjjHasSxf("2020-04-27", "2020-04-28", "003547", "鹏华丰禄债券(003547)",
//                    "20618.25", "22300", "2020-03-11", "51", "27", "0.08"));
//            paramList.add(handlertTtjjHasSxf("2020-04-28", "2020-04-29", "003547", "鹏华丰禄债券(003547)",
//                    "20709.98", "22400", "2020-03-11", "51", "27", "0.08"));
//            paramList.add(handlertTtjjHasSxf("2020-04-29", "2020-04-30", "003547", "鹏华丰禄债券(003547)",
//                    "20801.67", "22500", "2020-03-11", "51", "27", "0.08"));
//
//            paramList.add(handlertTtjjHasSxf("2020-04-24", "2020-04-27", "004127", "鹏华丰康债券(004127)",
//                    "1815.9", "2000", "2020-03-10", "57", "29", "0.8"));
//            paramList.add(handlertTtjjHasSxf("2020-04-27", "2020-04-30", "004127", "鹏华丰康债券(004127)",
//                    "1815.9", "2000", "2020-03-10", "57", "29", "0"));
//
//            paramList.add(handlertTtjjHasSxf(startDate, endDate, "206018", "鹏华产业债债券(206018)",
//                    "856.21", "1000", "2020-03-10", "56", "28", "0"));
//
//            paramList.add(handlertTtjjHasSxf("2020-04-24", "2020-04-27", "161716", "161716|招商双债增强债券(LOF)C",
//                    "749.03", "1000", "2020-03-10", "00", "00", "0.8"));
//            paramList.add(handlertTtjjHasSxf("2020-04-27", "2020-04-30", "161716", "161716|招商双债增强债券(LOF)C",
//                    "749.03", "1000", "2020-03-10", "00", "00", "0"));
//
//            paramList.add(handlertTtjjHasSxf(startDate, endDate, "003383", "民生加银鑫享债券C(003383)",
//                    "8571.92", "10000", "2020-03-03", "39", "12", "0"));
//
//            paramList.add(handlertTtjjHasSxf(startDate, endDate, "164402", "前海开源中航军工(164402)",
//                    "94.58", "100", "2020-03-02", "38", "13", "0"));
//
//            paramList.add(handlertTtjjHasSxf(startDate, endDate, "004086", "工银国债(7-10年)指数C(004086)",
//                    "902.45", "1000", "2020-03-02", "36", "23", "0"));
//        }

        {

            String startDateQdii = "2020-04-23";
            String endDateQdii = "2020-04-30";
        paramList.add(handlertTtjjHasSxf(startDateQdii, endDateQdii, "164906", "交银中证海外中国互联网指数(164906)",
                "72.59", "100", "2020-03-03", "40", "1", "0"));
        }

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
        rs.put(Content.SOURCE, SOURCE_ZFB);
        rs.put(Content.SXF, sxf);
        return rs;
    }


}
