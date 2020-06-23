package ttjj.histroy;

import utils.TtjjFoundRs;

public class Zfb_04_交银中证海外中国互联网指数_164906 {

    public static void main(String[] args) {
        String name = "交银中证海外中国互联网指数(164906)";
        String canShare = "72.59";
        String BUY_COST = "100";
        String FIRST_NET_DATA = "2020-03-03";
        String TRADE_ID = "40";
        String FD_ID = "1";
        String SOURCE = "2";

        String backString = "";
        {
            backString = "";
        }

        String[] str = backString.split("</td>");
        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
