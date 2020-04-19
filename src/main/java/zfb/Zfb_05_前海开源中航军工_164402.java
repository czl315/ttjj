package zfb;

import utils.TtjjFoundRs;

public class Zfb_05_前海开源中航军工_164402 {

    public static void main(String[] args) {
        String name = "前海开源中航军工(164402)";
        String canShare = "94.58";
        String BUY_COST = "100";
        String FIRST_NET_DATA = "2020-03-02";
        String TRADE_ID = "38";
        String FD_ID = "13";
        String SOURCE = "2";

        String backString = "";
        {
            backString = "";
        }

        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
