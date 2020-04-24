package histroy;

import utils.TtjjFoundRs;

public class Jd_04_005809_前海开源裕源 {

    public static void main(String[] args) {
        String name = "前海开源裕源(FOF)(005809)";
        String canShare = "310.22";
        String BUY_COST = "436.78976";
        String FIRST_NET_DATA = "2020-02-12";
        String TRADE_ID = "23";
        String FD_ID = "10";
        String SOURCE = "1";

        String backString = "";
//        backString = "";
        {
            backString = "";
        }

        String[] str = backString.split("</td>");
        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
