package ttjj.histroy;

import utils.Content;
import utils.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.*;

public class Zfb_00_gnjj {

    public static void main(String[] args) {
        List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
        String endDateToday = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        String startDate = "2020-04-17";
        paramList.add(handlertTtjj("2020-04-17", "2020-04-18", "003547", "鹏华丰禄债券(003547)",
                "19423.09", "21000", "2020-03-11", "51", "27"));
        paramList.add(handlertTtjj("2020-04-18", "2020-04-19", "003547", "鹏华丰禄债券(003547)",
                "20342.66", "22000", "2020-03-11", "51", "27"));
        paramList.add(handlertTtjj("2020-04-20", "2020-04-21", "003547", "鹏华丰禄债券(003547)",
                "20434.59", "22100", "2020-03-11", "51", "27"));
        paramList.add(handlertTtjj("2020-04-21", "2020-04-22", "003547", "鹏华丰禄债券(003547)",
                "20526.50", "22200", "2020-03-11", "51", "27"));
        paramList.add(handlertTtjj("2020-04-22", endDateToday, "003547", "鹏华丰禄债券(003547)",
                "20526.50", "22200", "2020-03-11", "51", "27"));



//        paramList.add(handlertTtjj(startDate, endDateToday, "206018", "鹏华产业债债券(206018)",
//                "856.21", "1000", "2020-03-10", "56", "28"));
//        paramList.add(handlertTtjj(startDate, endDateToday, "004127", "鹏华丰康债券(004127)",
//                "901.97", "1000", "2020-03-10", "57", "29"));
//        paramList.add(handlertTtjj(startDate, endDateToday, "003383", "民生加银鑫享债券C(003383)",
//                "8571.92", "10000", "2020-03-03", "39", "12"));
//        paramList.add(handlertTtjj(startDate, endDateToday, "164402", "前海开源中航军工(164402)",
//                "94.58", "100", "2020-03-02", "38", "13"));
//        paramList.add(handlertTtjj(startDate, endDateToday, "004086", "工银国债(7-10年)指数C(004086)",
//                "902.45", "1000", "2020-03-02", "36", "23"));

//        String qdiiStartDate = "2020-04-16";
//        paramList.add(handlertTtjj(qdiiStartDate, endDateToday, "164906", "交银中证海外中国互联网指数(164906)",
//                "72.59", "100", "2020-03-03", "40", "1"));

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
        rs.put(Content.SOURCE, Content.SOURCE_ZFB);
        return rs;
    }

}
