package jd;

import utils.TtjjFoundRs;

public class Jd_03_160644_鹏华港美互联股票人民币 {

    public static void main(String[] args) {
        String name = "鹏华港美互联股票人民币(160644)";
        String canShare = "100";
        String BUY_COST = "104.2";
        String FIRST_NET_DATA = "2020-02-14";
        String TRADE_ID = "24";
        String FD_ID = "15";
        String SOURCE = "1";

        String backString = "";
        {
            backString = "";
        }

        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
