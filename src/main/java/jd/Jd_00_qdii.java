package jd;

import utils.Content;
import utils.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.*;

public class Jd_00_qdii {

    public static void main(String[] args) {
        List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
        String lsjzUrl = "";

        Map<String, String> pramaMap164906 = new HashMap<String, String>();
        pramaMap164906.put(Content.jjCode, "164906");
        pramaMap164906.put(Content.startDate, "2020-04-09");
        pramaMap164906.put(Content.jjName, "交银中证海外中国互联网指数(164906)");
//        pramaMap164906.put(Content.endDate, "2020-04-10");
        pramaMap164906.put(Content.endDate, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        pramaMap164906.put(Content.FIRST_NET_DATA, "2019-09-10");
        pramaMap164906.put(Content.canShare, "1430.53");
        pramaMap164906.put(Content.BUY_COST, "1750.253455");
        pramaMap164906.put(Content.TRADE_ID, "13");
        pramaMap164906.put(Content.FD_ID, "1");
        pramaMap164906.put(Content.SOURCE, "1");
        lsjzUrl = "fundCode=" + pramaMap164906.get(Content.jjCode) + "&pageIndex=1&pageSize=100&startDate=" + pramaMap164906.get(Content.startDate) + "&endDate=" + pramaMap164906.get(Content.endDate) + "&_=1558194929451";
        pramaMap164906.put(Content.lsjzUrl, lsjzUrl);
        paramList.add(pramaMap164906);

        Map<String, String> pramaMap = new HashMap<String, String>();
        pramaMap.put(Content.startDate, "2020-04-09");
        pramaMap.put(Content.jjCode, "160644");
        pramaMap.put(Content.jjName, "鹏华港美互联股票人民币(160644)");
        pramaMap.put(Content.canShare, "100");
        pramaMap.put(Content.FIRST_NET_DATA, "2020-04-09");
        pramaMap.put(Content.BUY_COST, "104.2");
        pramaMap.put(Content.SOURCE, "1");
        pramaMap.put(Content.endDate, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        pramaMap.put(Content.TRADE_ID, "15");
        pramaMap.put(Content.FD_ID, "16");
        lsjzUrl = "fundCode=" + pramaMap.get(Content.jjCode) + "&pageIndex=1&pageSize=100&startDate=" + pramaMap.get(Content.startDate) + "&endDate=" + pramaMap.get(Content.endDate) + "&_=1558194929451";
        pramaMap.put(Content.lsjzUrl, lsjzUrl);
        paramList.add(pramaMap);

        pramaMap = new HashMap<String, String>();
        pramaMap.put(Content.startDate, "2020-04-09");
        pramaMap.put(Content.jjCode, "005809");
        pramaMap.put(Content.jjName, "前海开源裕源(FOF)(005809)");
        pramaMap.put(Content.canShare, "310.22");
        pramaMap.put(Content.BUY_COST, "436.78976");
        pramaMap.put(Content.FIRST_NET_DATA, "2020-02-12");
        pramaMap.put(Content.TRADE_ID, "23");
        pramaMap.put(Content.FD_ID, "10");
        pramaMap.put(Content.SOURCE, "1");
        pramaMap.put(Content.endDate, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        lsjzUrl = "fundCode=" + pramaMap.get(Content.jjCode) + "&pageIndex=1&pageSize=100&startDate=" + pramaMap.get(Content.startDate) + "&endDate=" + pramaMap.get(Content.endDate) + "&_=1558194929451";
        pramaMap.put(Content.lsjzUrl, lsjzUrl);
        paramList.add(pramaMap);

        pramaMap = new HashMap<String, String>(16);
        pramaMap.put(Content.startDate, "2020-04-09");
        pramaMap.put(Content.jjCode, "006327");
        String name = "易方达中证海外联接人民币A(006327)";//http://fundf10.eastmoney.com/jjjz_006327.html
        String canShare = "100";
        String BUY_COST = "117.9";
        String FIRST_NET_DATA = "2020-02-10";
        String TRADE_ID = "17";
        String FD_ID = "14";
        String SOURCE = "1";
        pramaMap.put(Content.jjName, name);
        pramaMap.put(Content.canShare, canShare);
        pramaMap.put(Content.BUY_COST, BUY_COST);
        pramaMap.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        pramaMap.put(Content.TRADE_ID, TRADE_ID);
        pramaMap.put(Content.FD_ID, FD_ID);
        pramaMap.put(Content.SOURCE, SOURCE);
        pramaMap.put(Content.endDate, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        lsjzUrl = "fundCode=" + pramaMap.get(Content.jjCode) + "&pageIndex=1&pageSize=100&startDate=" + pramaMap.get(Content.startDate) + "&endDate=" + pramaMap.get(Content.endDate) + "&_=1558194929451";
        pramaMap.put(Content.lsjzUrl, lsjzUrl);
        paramList.add(pramaMap);

        for (Map<String, String> stringStringMap : paramList) {
            byte[] bytes = "".getBytes();
            HttpUtil.sendPostTtjj(stringStringMap.get(Content.lsjzUrl), bytes, stringStringMap);
            System.out.println();
        }

    }

}
