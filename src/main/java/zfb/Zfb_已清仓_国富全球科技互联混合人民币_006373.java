package zfb;

import utils.TtjjFoundRs;

public class Zfb_已清仓_国富全球科技互联混合人民币_006373 {

    public static void main(String[] args) {
        String name = "国富全球科技互联混合人民币(006373)";
        String canShare = "651.55";
        String BUY_COST = "1000";
        String FIRST_NET_DATA = "2020-02-18";
        String TRADE_ID = "35";
        String FD_ID = "19";
        String SOURCE = "2";

        String backString = "";
        {
            backString = "";
        }

        String[] str = backString.split("</td>");
        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
