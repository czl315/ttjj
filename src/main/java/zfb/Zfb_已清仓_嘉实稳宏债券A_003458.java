package zfb;

import utils.TtjjFoundRs;

public class Zfb_已清仓_嘉实稳宏债券A_003458 {

    public static void main(String[] args) {
        String name = "嘉实稳宏债券A(003458)";
        String canShare = "799.55";
        String BUY_COST = "1000";
        String FIRST_NET_DATA = "2020-02-18";
        String TRADE_ID = "34";
        String FD_ID = "22";
        String SOURCE = "2";

        String backString = "";
        {
            backString = "";
        }

        String[] str = backString.split("</td>");
        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
