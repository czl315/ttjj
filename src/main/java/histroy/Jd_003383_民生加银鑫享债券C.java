package histroy;

import utils.TtjjFoundRs;

public class Jd_003383_民生加银鑫享债券C {

    public static void main(String[] args) {
        String name = "民生加银鑫享债券C(003383)";
        String canShare = "857.41";
        String BUY_COST = "2000";
        String FIRST_NET_DATA = "2019-07-23";
        String TRADE_ID = "11";
        String FD_ID = "12";
        String SOURCE = "1";

        String backString = "";
        {
            backString = "";
        }

        String[] str = backString.split("</td>");
        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
