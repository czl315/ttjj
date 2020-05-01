package histroy;

import utils.TtjjFoundRs;

public class Zfb_02_鹏华产业债债券_206018 {

    public static void main(String[] args) {
        String name = "鹏华产业债债券(206018)";
        String canShare = "856.21";
        String BUY_COST = "1000";
        String FIRST_NET_DATA = "2020-03-10";
        String TRADE_ID = "56";
        String FD_ID = "28";
        String SOURCE = "2";

        String backString = "";
        {
            backString = "";
        }

        String[] str = backString.split("</td>");
        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
