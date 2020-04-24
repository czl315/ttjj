package histroy;

import utils.TtjjFoundRs;

public class Jd_05_006327_易方达中证海外联接人民币A {

    public static void main(String[] args) {
        String name = "易方达中证海外联接人民币A(006327)";//http://fundf10.eastmoney.com/jjjz_006327.html
        String canShare = "100";
        String BUY_COST = "117.9";
        String FIRST_NET_DATA = "2020-02-10";
        String TRADE_ID = "17";
        String FD_ID = "14";
        String SOURCE = "1";

        String backString = "";
        {
            backString = "";
        }

        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
