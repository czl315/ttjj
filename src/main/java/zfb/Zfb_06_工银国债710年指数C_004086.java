package zfb;

import utils.TtjjFoundRs;

public class Zfb_06_工银国债710年指数C_004086 {

    public static void main(String[] args) {
        String name = "工银国债(7-10年)指数C(004086)";
        String canShare = "902.45";
        String BUY_COST = "1000";
        String FIRST_NET_DATA = "2020-03-02";
        String TRADE_ID = "36";
        String FD_ID = "23";
        String SOURCE = "2";

        String backString = "";
        {
            backString = "";
        }

        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
