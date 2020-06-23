package ttjj.histroy;

import utils.TtjjFoundRs;

public class Zfb_已清仓_华润元大双鑫债券A_003680 {

    public static void main(String[] args) {
        String name = "华润元大双鑫债券A(003680)";
        String canShare = "909.94";
        String BUY_COST = "1000";
        String FIRST_NET_DATA = "2020-03-02";
        String TRADE_ID = "37";
        String FD_ID = "24";
        String SOURCE = "2";

        String backString = "";
        {
            backString = "";
        }

        String[] str = backString.split("</td>");
        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
