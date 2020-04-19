package jd;

import utils.Content;
import utils.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.*;

public class Jd_00_gnjj {

    public static void main(String[] args) {
        List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
        paramList.add(handler004593("2020-04-15"));
        paramList.add(handler164402("2020-04-15"));
        paramList.add(handler003860("2020-04-15"));

        paramList.add(handler164906("2020-04-14"));
        paramList.add(handler005809("2020-04-14"));
        paramList.add(handler006327("2020-04-14"));

//        paramList.add(handler160644("2020-04-14"));//2020-04-10 全部赎回

        //ttjj-insert
        for (Map<String, String> stringStringMap : paramList) {
            byte[] bytes = "".getBytes();
            HttpUtil.sendPostTtjj(stringStringMap.get(Content.lsjzUrl), bytes, stringStringMap);
            System.out.println();
        }

    }

    public static Map<String, String> handler003860(String startDate){
        Map<String, String> rs = new HashMap<String, String>(16);
        String name = "招商招旭纯债C(003860)";
        String canShare = "872.29";
        String BUY_COST = "1000";
        String FIRST_NET_DATA = "2020-02-21";
        String TRADE_ID = "25";
        String FD_ID = "16";
        String SOURCE = "1";
        String jjCode = "003860";
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=1558194929451";
        rs.put(Content.lsjzUrl, lsjzUrl);
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
        String jjCode = "164402";
        String canShare = "444.79";
        String BUY_COST = "1000";
        String FIRST_NET_DATA = "2019-09-09";
        String TRADE_ID = "12";
        String FD_ID = "13";
        String SOURCE = "1";
        String name = "前海开源中航军工(164402)";//http://fundf10.eastmoney.com/jjjz_164402.html

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
    public static Map<String, String> handler004593(String startDate){
        Map<String, String> rs = new HashMap<String, String>(16);
        String name = "广发全指工业ETF联接A(004593)";//http://fundf10.eastmoney.com/jjjz_004593.html
        String jjCode = "004593";
        String canShare = "99.93";
        String BUY_COST = "100";
        String FIRST_NET_DATA = "2017-05-21";
        String TRADE_ID = "4";
        String FD_ID = "5";
        String SOURCE = "1";
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
        String jjCode = "164906";
        String canShare = "1430.53";
        String BUY_COST = "1750.253455";
        String FIRST_NET_DATA = "2019-09-10";
        String TRADE_ID = "13";
        String FD_ID = "1";
        String SOURCE = "1";
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
    public static Map<String, String> handler160644(String startDate){
        Map<String, String> rs = new HashMap<String, String>(16);
        String name = "鹏华港美互联股票人民币(160644)";
        String jjCode = "160644";
        String canShare = "100";
        String BUY_COST = "104.2";
        String FIRST_NET_DATA = "2020-04-09";
        String TRADE_ID = "15";
        String FD_ID = "16";
        String SOURCE = "1";
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
    public static Map<String, String> handler005809(String startDate){
        Map<String, String> rs = new HashMap<String, String>(16);
        String name = "前海开源裕源(FOF)(005809)";
        String jjCode = "005809";
        String canShare = "310.22";
        String BUY_COST = "436.78976";
        String FIRST_NET_DATA = "2020-02-12";
        String TRADE_ID = "23";
        String FD_ID = "10";
        String SOURCE = "1";
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
    public static Map<String, String> handler006327(String startDate){
        Map<String, String> rs = new HashMap<String, String>(16);
        String jjCode = "006327";
        String name = "易方达中证海外联接人民币A(006327)";//http://fundf10.eastmoney.com/jjjz_006327.html
        String canShare = "100";
        String BUY_COST = "117.9";
        String FIRST_NET_DATA = "2020-02-10";
        String TRADE_ID = "17";
        String FD_ID = "14";
        String SOURCE = "1";
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
