package ttjj.trade;

import ttjj.Dao.TradeDao;
import ttjj.Dao.impl.TradeDaoImpl;
import ttjj.dto.FundTrade;
import ttjj.dto.LsjzDataLsjz;
import ttjj.trade.impl.TradeServiceImpl;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author chenzhilong
 * @date 2020/8/4
 */
public class TradeDemo {
    public static void main(String[] args) {
//        String cookie = "FundTradeLoginCard=0; FundTradeLoginTab=0; __guid=168568481.3275105768631237000.1587299075419.1528; st_si=16529934604496; st_asi=delete; b_p_log_key=YqYlmCtMJVbt2nCdsyCq6TbxbtD+GRXUJrxBZEDZITxHIfCgUw8tCrxUk731QiB0nvUsM8TkmchTyB/oeM7g5nSq+YBBzSV3o1LtTI5IkSzzNbvkXT0=; st_pvi=34528644972697; st_sp=2020-03-21%2009%3A52%3A13; st_inirUrl=https%3A%2F%2Flogin.1234567.com.cn%2Flogin; st_sn=2; st_psi=20200626125212882-119085303933-7379745562; cp_token=2ca40dd1c09e4f6c99ad548acef6e639; FundTradeLoginUser=WryU2RGKLMQddvOrt9ie5DH681dVJ7Rs98U+3fkkCPoMMYm+TogQg18eMgwrDAYRCBbS6blW; fund_trade_cn=WOQWkk599pK/DrM33DcU8UfIp1hdiIqkOBDKtTcnjgVcLCFELaR5ciCT33q3rNqfMHS/tnYaLdfbHWm6cxq48Uvcrj2Xgx//HDR5H1pWcbYwG3sOur8=; fund_trade_name=WQ+qNJQRkM2gf0ve7giilJV4jbTVicEdkpYh3wCIEEQaZYpiX5NWK08mI5edsToRSpyAZ+3a; fund_trade_visitor=WZGFRsGAsMwx6GI0b4iGrpXig4GVnUDJkDgn3Wcq5hDS7YYCBDJ7f88D0Q7jDR0Rmf6wuKvi; fund_trade_risk=WGB21Yg1xMyCP00D20iUUjQveZdV0FAjhnlZ38Sw4BK48YjyjP+Qq98bpqDDQTeRLcjQLeub; fund_trade_gps=2; VipLevel=0; TradeLoginToken=0cdd00b4660749efbbb2b1ad5a00a1a2; UTOKEN=WOQWkk599pK/DrM33DcU8UfIp1hdiIqkOBDKtTcnjgVcLCFELaR5ciCT33q3rNqfMHS/tuY+U8CMvNFZKtf18kYRYJD80Zr4yERG9mq9X6yRf4eEJPk=; LToken=919b1cc8f4244369b65efa898ff7ae58; fund_trade_trackid=gSRgeG/HOvMXtvvf2k5YRsWme3vrnWmciDUr/Y5kDpfBbZD9+RhA8YnjkfRSoYT1EaJ8OL/0ktaZzbgM46qQ3g==; monitor_count=14";
        String cookie = "FundTradeLoginCard=0; FundTradeLoginTab=0; __guid=26890232.4298960297175944000.1584177952348.2258; st_si=24610614265727; st_asi=delete; cp_token=b4d7af7e4b7944c0a5230ad2e0ee509d; FundTradeLoginUser=ZYLq5K4JVzfmVrcNubpWSmMmA01q7H+ykR44qB7ktmYsLlRjNpt0BLaGIAjCtnnNYwBB4Qap; fund_trade_cn=ZFiOEtZtUXFig6zPNxmLdKR3s5bepxsXiKjKnUZ6Vxq9icgWaPWhvtPbq5AXGVHvu6S4mTlN8RZqmnN8ddp1ajlrCPYoP4B0aGNKorHBrkxQ1svS98E=; fund_trade_name=ZkOuU2xPdzVh58lVRNpZBoAk2k5qpz1J+Kxqq1E0yfOkDlb2BEi/qKakfcIYG1tN3RllEIhK; fund_trade_visitor=ZNIddSa1IzPwQ6+9OJpoO4Gody9qJxMo2pEsq5buneIwvlxQ3UCIK2axNw4e7OxNPqFjC9Ml; fund_trade_risk=ZXOf9D1dazBYdzeStKpKCR67pfOqElxBWAHRq5r6i1jfglhHCO+ojHaUAmwIpxlNaYnLaXXv; fund_trade_gps=2; VipLevel=0; TradeLoginToken=99c8e7ee8bda47f4a8358014cf1eb5c9; UTOKEN=ZFiOEtZtUXFig6zPNxmLdKR3s5bepxsXiKjKnUZ6Vxq9icgWaPWhvtPbq5AXGVHvu6S4mDlixZjcJp0ACKHIafcl8Am7sDEoprNew68x5GDAuNJ7aFs=; LToken=634e27a7e3fb4118a1d1068a0695eee7; fund_trade_trackid=CIpww812J0HH1TejttWVxNapH5sH123BJM4LbU3GPvGw/YBRZWNP8Ak3iiCBUml1HN9d6sivvrYVJpR3Nb8sMw==; ASP.NET_SessionId=5dul4rhumintltnbueptjwed; st_pvi=34528644972697; st_sp=2020-03-21%2009%3A52%3A13; st_inirUrl=https%3A%2F%2Flogin.1234567.com.cn%2Flogin; st_sn=2; st_psi=20200810233501795-0-4643086257; monitor_count=4";

//        String startDate = "2020-02-01";
//        String endDate = "2020-12-31";
//        String busType = "0";//0-全部;1-申购;2-卖出;

        //显示插入数据库语句
//        showInsertDb(cookie, "2020-08-07", "2020-12-31", "1");
        //显示更新数据库语句
        showUpdateDb(cookie, "2020-02-01", "2020-12-31", "1");
        //赎回
        showDbRedem(cookie, "2020-02-01", "2020-12-31", "2");

    }

    /**
     * 显示插入数据库语句
     *
     * @param
     */
    private static void showInsertDb(String cookie, String startDate, String endDate, String busType) {
        String fundCode = "";
        TradeDao tradeService = new TradeDaoImpl();
        List<FundTrade> rs = tradeService.findMyTrade(cookie, fundCode, startDate, endDate, busType);
//        System.out.println("findMyTrade:"+JSON.toJSON(rs));
        for (FundTrade fundTrade : rs) {
            if (fundTrade.getOrderStatus() != null && (fundTrade.getOrderStatus().contains("申购"))) {
                //显示插入数据库语句
                System.out.println("INSERT INTO `bank19`.`ol_fund_trade`(" +
                        " `FD_ID`, `FD_INFO`, `TYPE`, `TRADE_TIME`, `ORDER_STATUS`, " +
                        " `CONFIRM_SHARE`, `CONFIRM_NET`, `ORDER_AMT`, `STATUS`, " +
                        " `ORDER_CODE`, `CONFIRM_AMT`, `REDEM_AMT`," +
                        " `EARN_AMT`, `CONFIRM_NET_DATA`, `SERVER_CHARGE`, " +
                        " `REDEM_STATUS`, `REDEM_SHARE`, `REDEM_TIME`, `REDEM_ACCT_TIME`," +
                        " `SOURCE`, `FD_CODE`, `CREATE_TIME`, `UPDATE_TIME`" +
                        ") VALUES (" +
                        " '', '" + fundTrade.getFundInfo() + "', '" + fundTrade.getOrderStatus() + "', '" + fundTrade.getTradeTime() + "', '" + fundTrade.getOrderStatus() + "'" +
                        ", " + fundTrade.getConfirmShare() + ", " + fundTrade.getConfirmNet() + ", " + fundTrade.getOrderAmt() + ", " + "'确认成功', " +
                        "  '', " + fundTrade.getOrderAmt() + ", 0, " +
                        " 0, '" + fundTrade.getConfirmNetData() + "', " + fundTrade.getServerCharge() + ", " +
                        " '0', 0,  '3000-01-01 00:00:00', '3000-01-01 00:00:00', " +
                        " '3', '', now(), now()" +
                        ");");
            }
        }
    }

    /**
     * 更新最新净值
     *
     * @param cookie
     * @param startDate
     * @param endDate
     * @param busType
     */
    private static void showUpdateDb(String cookie, String startDate, String endDate, String busType) {
        String fundCode = "";
        TradeDao tradeService = new TradeDaoImpl();
        List<FundTrade> rs = tradeService.findMyTrade(cookie, fundCode, startDate, endDate, busType);
//        System.out.println("findMyTrade:"+JSON.toJSON(rs));
        for (FundTrade fundTrade : rs) {
                String lsjzUrl = "fundCode=" + fundTrade.getFundCode() + "&pageIndex=1&pageSize=100&startDate=" + "" + "&endDate=" + "" + "&_=1558194929451";
                byte[] bytes ="".getBytes();
                LsjzDataLsjz lsjzDataLsjz = HttpUtil.sendPostTtjjLsjzLastOne(lsjzUrl, bytes, new HashMap<String, String>());
//        System.out.println("lsjzDataLsjz:"+JSON.toJSON(lsjzDataLsjz));
                //打印-
                if(lsjzDataLsjz==null){
                    return;
                }
                System.out.println("UPDATE `ol_fund_trade` " +
                        "SET " +
                        " `LAST_NET`=" + lsjzDataLsjz.getDWJZ() + " " +
                        ",`LAST_DATE`='" + lsjzDataLsjz.getFSRQ() + "' " +
                        "WHERE  `FD_INFO` = '" + fundTrade.getFundInfo() + "' "+
                        "AND `TYPE` = '申购' "+
                        " ;");
        }
    }

    /**
     * 赎回
     *
     * @param cookie
     * @param startDate
     * @param endDate
     * @param busType
     */
    private static void showDbRedem(String cookie, String startDate, String endDate, String busType) {
        String fundCode = "";
        TradeDao tradeService = new TradeDaoImpl();
        List<FundTrade> rs = tradeService.findMyTrade(cookie, fundCode, startDate, endDate, busType);
//        System.out.println("findMyTrade:"+JSON.toJSON(rs));
        for (FundTrade fundTrade : rs) {
            if (fundTrade.getOrderStatus() != null && (fundTrade.getOrderStatus().contains("赎回")) && fundTrade.getOrderAmt()!=null) {
                //打印-赎回-update
                BigDecimal enrnAmtSubServerCharge = fundTrade.getOrderAmt().subtract(fundTrade.getServerCharge());
                System.out.println("UPDATE `ol_fund_trade` " +
                        "SET `TYPE`='申购(赎回)'" +
                        ",`ORDER_AMT`=" + fundTrade.getOrderAmt() + " " +
                        ",`REDEM_TIME`='" + fundTrade.getConfirmNetData() + "' " +
                        ",`REDEM_AMT`=" + fundTrade.getOrderAmt() + " " +
                        ",`SERVER_CHARGE`=" + fundTrade.getServerCharge() + " " +
                        ",`EARN_AMT`=ROUND((" + enrnAmtSubServerCharge + "-`CONFIRM_AMT`) ,2)" +
                        "WHERE  `FD_INFO` = '" + fundTrade.getFundInfo() + "' "
                        + "AND `TYPE` = '申购' "
                        + "AND `CONFIRM_SHARE` = '" + fundTrade.getConfirmShare() + "' " +
                        " LIMIT 1; ");
            }
        }
    }


}
