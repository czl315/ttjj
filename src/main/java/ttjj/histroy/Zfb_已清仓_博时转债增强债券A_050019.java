package ttjj.histroy;

import utils.TtjjFoundRs;

public class Zfb_已清仓_博时转债增强债券A_050019 {

    public static void main(String[] args) {
        String name = "博时转债增强债券A(050019)";
        String canShare = "601.2";
        String BUY_COST = "1000";
        String FIRST_NET_DATA = "2020-02-18";
        String TRADE_ID = "33";
        String FD_ID = "17";
        String SOURCE = "2";

        String backString = "";
        {
            backString = "";
        }

        String[] str = backString.split("</td>");
        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
