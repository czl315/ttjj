package zfb;

import utils.Content;
import utils.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zfb_00_0613_0619 {
    /**
     *
     */
    public static String SOURCE_ZFB = "2";

    public static void main(String[] args) {
        List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
//        String endDateToday = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String startDate = "2020-06-12";
        String endDate = "2020-06-19";

//        {
//            paramList.add(handlertTtjjHasSxf(startDate, endDate, "003547", "003547|鹏华丰禄债券",
//                    "23003.67", "24500", "2020-03-11", "51", "27", "0"));
//
//            paramList.add(handlertTtjjHasSxf(startDate, endDate, "004127", "004127|鹏华丰康债券",
//                    "1815.9", "2000", "2020-03-10", "57", "29", "0"));
//
//            paramList.add(handlertTtjjHasSxf(startDate, endDate, "206018", "206018|鹏华产业债债券",
//                    "856.21", "1000", "2020-03-10", "56", "28", "0"));
//
//            paramList.add(handlertTtjjHasSxf(startDate, endDate, "161716", "161716|招商双债增强债券(LOF)C",
//                    "749.03", "1000", "2020-03-10", "00", "00", "0"));
//
//            paramList.add(handlertTtjjHasSxf(startDate, endDate, "164402", "164402|前海开源中航军工",
//                    "94.58", "100", "2020-03-02", "38", "13", "0"));
//
//
//        }

//        {
//            /**
//             * 已清仓
//             */
//            paramList.add(handlertTtjjHasSxf(startDate, "2020-06-08", "003383", "003383|民生加银鑫享债券C",
//                    "8571.92", "10000", "2020-03-03", "39", "12", "0"));
//            paramList.add(handlertTtjjHasSxf(startDate, "2020-06-08", "004086", "004086|工银国债(7-10年)指数C",
//                    "902.45", "1000", "2020-03-02", "36", "23", "0"));
//        }

        {

            String startDateQdii = "2020-06-04";
            String endDateQdii = "2020-06-20";
            paramList.add(handlertTtjjHasSxf(startDateQdii, endDateQdii, "164906", "164906|交银中证海外中国互联网指数",
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
