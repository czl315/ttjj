package jd;

import utils.TtjjFoundRs;

public class Jd_01_164906_交银中证海外中国互联网指数 {

    public static void main(String[] args) {
        String name = "交银中证海外中国互联网指数(164906)";
        String canShare = "1430.53";//份额
//        String canShare = "600.96";//份额
        String BUY_COST = "1750.253455";
        String FIRST_NET_DATA = "2019-09-10";
        String TRADE_ID = "13";
        String FD_ID = "1";
        String SOURCE = "1";

        String backString = "";
        {
            backString = "";
        }

        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
