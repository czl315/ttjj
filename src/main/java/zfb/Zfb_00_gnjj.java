package zfb;

import utils.Content;
import utils.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.*;

public class Zfb_00_gnjj {

    public static void main(String[] args) {
        List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();

        paramList.add(handler003547("2020-04-15"));
        paramList.add(handler206018("2020-04-15"));
        paramList.add(handler004127("2020-04-15"));
        paramList.add(handler003383("2020-04-15"));
        paramList.add(handler164402("2020-04-15"));
        paramList.add(handler004086("2020-04-15"));

        paramList.add(handler164906("2020-04-14"));

        //ttjj-insert
        for (Map<String, String> stringStringMap : paramList) {
            byte[] bytes = "".getBytes();
            HttpUtil.sendPostTtjj(stringStringMap.get(Content.lsjzUrl), bytes, stringStringMap);
            System.out.println();
        }

    }

    public static Map<String, String> handler003547(String startDate){
        Map<String, String> rs = new HashMap<String, String>(16);
        String name = "鹏华丰禄债券(003547)";
        String canShare = "18503.26";
        String BUY_COST = "20000";
        String FIRST_NET_DATA = "2020-03-11";
        String TRADE_ID = "51";
        String FD_ID = "27";
        String SOURCE = "2";
        String jjCode = "003547";
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=1558194929451";
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
        rs.put(Content.SOURCE, SOURCE);
        return rs;
    }
    public static Map<String, String> handler206018(String startDate){
        Map<String, String> rs = new HashMap<String, String>(16);
        String name = "鹏华产业债债券(206018)";
        String canShare = "856.21";
        String BUY_COST = "1000";
        String FIRST_NET_DATA = "2020-03-10";
        String TRADE_ID = "56";
        String FD_ID = "28";
        String SOURCE = "2";
        String jjCode = "206018";
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=1558194929451";
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
        rs.put(Content.SOURCE, SOURCE);
        return rs;
    }
    public static Map<String, String> handler004127(String startDate){
        Map<String, String> rs = new HashMap<String, String>(16);
        String name = "鹏华丰康债券(004127)";
        String canShare = "901.97";
        String BUY_COST = "1000";
        String FIRST_NET_DATA = "2020-03-10";
        String TRADE_ID = "57";
        String FD_ID = "29";
        String SOURCE = "2";
        String jjCode = "004127";
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=1558194929451";
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
        rs.put(Content.SOURCE, SOURCE);
        return rs;
    }
    public static Map<String, String> handler003383(String startDate){
        Map<String, String> rs = new HashMap<String, String>(16);
        String name = "民生加银鑫享债券C(003383)";
        String canShare = "8571.92";
        String BUY_COST = "10000";
        String FIRST_NET_DATA = "2020-03-03";
        String TRADE_ID = "39";
        String FD_ID = "12";
        String SOURCE = "2";
        String jjCode = "003383";
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=1558194929451";
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
        rs.put(Content.SOURCE, SOURCE);
        return rs;
    }
    public static Map<String, String> handler164402(String startDate){
        Map<String, String> rs = new HashMap<String, String>(16);
        String name = "前海开源中航军工(164402)";
        String canShare = "94.58";
        String BUY_COST = "100";
        String FIRST_NET_DATA = "2020-03-02";
        String TRADE_ID = "38";
        String FD_ID = "13";
        String SOURCE = "2";
        String jjCode = "164402";
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=1558194929451";
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
        rs.put(Content.SOURCE, SOURCE);
        return rs;
    }
    public static Map<String, String> handler004086(String startDate){
        Map<String, String> rs = new HashMap<String, String>(16);
        String name = "工银国债(7-10年)指数C(004086)";
        String canShare = "902.45";
        String BUY_COST = "1000";
        String FIRST_NET_DATA = "2020-03-02";
        String TRADE_ID = "36";
        String FD_ID = "23";
        String SOURCE = "2";
        String jjCode = "004086";
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=1558194929451";
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
        rs.put(Content.SOURCE, SOURCE);
        return rs;
    }
    public static Map<String, String> handler164906(String startDate){
        Map<String, String> rs = new HashMap<String, String>(16);
        String name = "交银中证海外中国互联网指数(164906)";
        String canShare = "72.59";
        String BUY_COST = "100";
        String FIRST_NET_DATA = "2020-03-03";
        String TRADE_ID = "40";
        String FD_ID = "1";
        String SOURCE = "2";
        String jjCode = "164906";
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=1558194929451";
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
        rs.put(Content.SOURCE, SOURCE);
        return rs;
    }
}
