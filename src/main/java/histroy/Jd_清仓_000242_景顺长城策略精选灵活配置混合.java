package histroy;

import utils.TtjjFoundRs;

public class Jd_清仓_000242_景顺长城策略精选灵活配置混合 {

    public static void main(String[] args) {
        String name = "景顺长城策略精选灵活配置混合(000242)";
        String canShare = "638.43";
        String BUY_COST = "1000";
        String FIRST_NET_DATA = "2020-02-26";
        String TRADE_ID = "27";
        String FD_ID = "21";
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
