package histroy;

import utils.TtjjFoundRs;

public class Zfb_01_鹏华丰禄债券_003547 {

    public static void main(String[] args) {
        String name = "鹏华丰禄债券(003547)";
        String canShare = "11130.51";
        String BUY_COST = "12000";
        String FIRST_NET_DATA = "2020-03-11";
        String TRADE_ID = "51";
        String FD_ID = "27";
        String SOURCE = "2";

        String backString = "";
        {
            backString = "";
        }
        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
