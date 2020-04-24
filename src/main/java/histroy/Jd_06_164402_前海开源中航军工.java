package histroy;

import utils.TtjjFoundRs;

public class Jd_06_164402_前海开源中航军工 {

    public static void main(String[] args) {
        String name = "前海开源中航军工(164402)";//http://fundf10.eastmoney.com/jjjz_164402.html
        String canShare = "408.76";
        String BUY_COST = "444.79";
        String FIRST_NET_DATA = "2019-09-09";
        String TRADE_ID = "12";
        String FD_ID = "13";
        String SOURCE = "1";

        String backString = "";
        {
            backString = "";
        }

        String[] str = backString.split("</td>");
        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
