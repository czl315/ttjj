package ttjj.trade;

import com.alibaba.fastjson.JSON;
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
        String cookie = "FundTradeLoginCard=0; FundTradeLoginTab=0; __guid=26890232.4298960297175944000.1584177952348.2258; st_si=81449020881936; st_pvi=34528644972697; st_sp=2020-03-21%2009%3A52%3A13; st_inirUrl=https%3A%2F%2Flogin.1234567.com.cn%2Flogin; st_sn=1; st_asi=delete; st_psi=20200812225001296-119085303933-7866552014; cp_token=88171758d58648f996a0d6ca1814bf97; FundTradeLoginUser=OxIUA2QylslYffZIi0ljJccnKeaoyVQfrE7nfpwBoTP2lePMiYbp9MoPgPoLvgH4+Q/EquAU; fund_trade_cn=OBC10MGUUaRIlasR0bSGtMIAc4bZlTWMDQEjiNRe3GoDFetIFse27o6yfcpIa59hqWNUhqeqOYa9+CcpQEQeoBaDz0+Y0AiRgx4i9hk8OvIz3rtjAEc=; fund_trade_name=OvbOd099AsPvTuEH1qldScltkVPoECBuGYdtfJV9VbF+oetEFy9x5JomqhoM3Xw48rJcLffT; fund_trade_visitor=OwcUBmHYSs+HFD+A2ol8pDO51w/om80lJg1hfmQ0/9J43exF9/gXokogipbAchY4qPYiTiJP; fund_trade_risk=OHgXN5ovlsYNjrckzJlyqLRumqpoVtTNya0nfle4r9IxOe9Ai4HEHgoAT5+Fnnz400EBCE3n; fund_trade_gps=2; VipLevel=0; TradeLoginToken=538d689731cc4d578a4e0bdf0fe12831; UTOKEN=OBC10MGUUaRIlasR0bSGtMIAc4bZlTWMDQEjiNRe3GoDFetIFse27o6yfcpIa59hqWNUh3eqXqOr6cu/zr6xofA6wzYEtNR+md45eLKblrwTpMBBv3A=; LToken=02068eef6be14f91945a9ff5ef45c6b8; fund_trade_trackid=DCV5vgjf6b/95KU4zj3yooJTKqnSgMrNdzZvPkYauwpR2EgYKUGrnFWYVqv6SzQvwm6/LuinwovFNwxKGm6SXQ==; ASP.NET_SessionId=txzyqutxkbi0btqlmt4e0ha0; monitor_count=2";

//        String startDate = "2020-02-01";
//        String endDate = "2020-12-31";
//        String busType = "0";//0-全部;1-申购;2-卖出;

        //显示插入数据库语句
//        showInsertDb(cookie, "2020-08-11", "2020-12-31", "1");
        //显示更新数据库语句
//        showUpdateDb(cookie, "2020-02-01", "2020-12-31", "1");
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
//        System.out.println("findMyTrade:"+ JSON.toJSONString(rs));
        for (FundTrade fundTrade : rs) {
            if (fundTrade.getOrderStatus() != null && (fundTrade.getOrderStatus().contains("赎回")) && fundTrade.getOrderAmt()!=null) {
                //打印-赎回-update
                BigDecimal enrnAmtSubServerCharge = fundTrade.getOrderAmt().subtract(fundTrade.getServerCharge());
                System.out.println("UPDATE `ol_fund_trade` " +
                        "SET `TYPE`='申购(赎回)'" +
                        ",`ORDER_AMT`=" + fundTrade.getOrderAmt() + " " +
//                        ",`NET_REDEM`=" + fundTrade.get + " " +
                        ",`REDEM_TIME`='" + fundTrade.getConfirmNetData() + "' " +
                        ",`REDEM_AMT`=" + fundTrade.getOrderAmt() + " " +
                        ",`REDEM_AMT`=" + fundTrade.getOrderAmt() + " " +
                        ",`SERVER_CHARGE`=ROUND((" + fundTrade.getServerCharge() + "+`SERVER_CHARGE`) ,2)" +
                        ",`EARN_AMT`=ROUND((" + enrnAmtSubServerCharge + "-`CONFIRM_AMT`) ,2)" +
                        "WHERE  `FD_INFO` = '" + fundTrade.getFundInfo() + "' "
                        + "AND `TYPE` = '申购' "
                        + "AND `CONFIRM_SHARE` = '" + fundTrade.getConfirmShare() + "' " +
                        " LIMIT 1; ");
            }
        }
    }


}
