package histroy;

import utils.TtjjFoundRs;

public class Zfb_03_鹏华丰康债券_004127 {

    public static void main(String[] args) {
        String name = "鹏华丰康债券(004127)";
        String canShare = "901.97";
        String BUY_COST = "1000";
        String FIRST_NET_DATA = "2020-03-10";
        String TRADE_ID = "57";
        String FD_ID = "29";
        String SOURCE = "2";

        String backString = "";
        {
            backString = "";
        }

        String[] str = backString.split("</td>");
        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
