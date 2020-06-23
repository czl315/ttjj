package ttjj.histroy;

import utils.TtjjFoundRs;

public class Zfb_04_民生加银鑫享债券C_003383 {

    public static void main(String[] args) {
        String name = "民生加银鑫享债券C(003383)";
        String canShare = "8571.92";
        String BUY_COST = "10000";
        String FIRST_NET_DATA = "2020-03-03";
        String TRADE_ID = "39";
        String FD_ID = "12";
        String SOURCE = "2";

        String backString = "";
        {
            backString = "";
        }

        String[] str = backString.split("</td>");
        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
