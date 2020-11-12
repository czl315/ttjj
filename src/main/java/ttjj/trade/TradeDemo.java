package ttjj.trade;

import com.alibaba.fastjson.JSON;
import ttjj.Dao.TradeDao;
import ttjj.Dao.impl.TradeDaoImpl;
import ttjj.dto.FundTrade;
import ttjj.dto.LsjzDataLsjz;
import ttjj.trade.impl.TradeServiceImpl;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author chenzhilong
 * @date 2020/8/4
 */
public class TradeDemo {
    public static void main(String[] args) {
//        String cookie = "FundTradeLoginCard=0; FundTradeLoginTab=0; __guid=168568481.3275105768631237000.1587299075419.1528; st_si=16529934604496; st_asi=delete; b_p_log_key=YqYlmCtMJVbt2nCdsyCq6TbxbtD+GRXUJrxBZEDZITxHIfCgUw8tCrxUk731QiB0nvUsM8TkmchTyB/oeM7g5nSq+YBBzSV3o1LtTI5IkSzzNbvkXT0=; st_pvi=34528644972697; st_sp=2020-03-21%2009%3A52%3A13; st_inirUrl=https%3A%2F%2Flogin.1234567.com.cn%2Flogin; st_sn=2; st_psi=20200626125212882-119085303933-7379745562; cp_token=2ca40dd1c09e4f6c99ad548acef6e639; FundTradeLoginUser=WryU2RGKLMQddvOrt9ie5DH681dVJ7Rs98U+3fkkCPoMMYm+TogQg18eMgwrDAYRCBbS6blW; fund_trade_cn=WOQWkk599pK/DrM33DcU8UfIp1hdiIqkOBDKtTcnjgVcLCFELaR5ciCT33q3rNqfMHS/tnYaLdfbHWm6cxq48Uvcrj2Xgx//HDR5H1pWcbYwG3sOur8=; fund_trade_name=WQ+qNJQRkM2gf0ve7giilJV4jbTVicEdkpYh3wCIEEQaZYpiX5NWK08mI5edsToRSpyAZ+3a; fund_trade_visitor=WZGFRsGAsMwx6GI0b4iGrpXig4GVnUDJkDgn3Wcq5hDS7YYCBDJ7f88D0Q7jDR0Rmf6wuKvi; fund_trade_risk=WGB21Yg1xMyCP00D20iUUjQveZdV0FAjhnlZ38Sw4BK48YjyjP+Qq98bpqDDQTeRLcjQLeub; fund_trade_gps=2; VipLevel=0; TradeLoginToken=0cdd00b4660749efbbb2b1ad5a00a1a2; UTOKEN=WOQWkk599pK/DrM33DcU8UfIp1hdiIqkOBDKtTcnjgVcLCFELaR5ciCT33q3rNqfMHS/tuY+U8CMvNFZKtf18kYRYJD80Zr4yERG9mq9X6yRf4eEJPk=; LToken=919b1cc8f4244369b65efa898ff7ae58; fund_trade_trackid=gSRgeG/HOvMXtvvf2k5YRsWme3vrnWmciDUr/Y5kDpfBbZD9+RhA8YnjkfRSoYT1EaJ8OL/0ktaZzbgM46qQ3g==; monitor_count=14";
        String cookie = "FundTradeLoginCard=0; FundTradeLoginTab=0; __guid=26890232.4298960297175944000.1584177952348.2258; st_si=85551223730194; st_asi=delete; ASP.NET_SessionId=quqkus5yxv33zosijxpyoubv; st_pvi=34528644972697; st_sp=2020-03-21%2009%3A52%3A13; st_inirUrl=https%3A%2F%2Flogin.1234567.com.cn%2Flogin; st_sn=7; st_psi=20201111225631898-119085303933-3578202004; cp_token=6020f7b837b342c29bc036f50ff9dad9; FundTradeLoginUser=cAtjMaAtlPPmxeQe5+vUOTbiTXd9bUNt/sT35Gf4sB6mvGUb27qVv5F1/MuGoYuRmnW4FB7h; fund_trade_cn=cmBPYgBndVrn78PUQQ3QBy2DLRipv+cFmzEaZlQyBZ9n0s9ZOrK6PY2O5bOE8/xeelV8G1G0mnImh2fFsU6/F+/0u8MMniNM49RLLb3GQpdWhguThzk=; fund_trade_name=cdbR0WrIXPoQ9ACzd7v9wRwXQuV9qz+13uQB5SdqjzeqpGT6ue1i+VFs4bJ4zAQR3pvJzBUd; fund_trade_visitor=c1OwUzosvP7iW11RmxvBK+FaUij9G393wKDT54fErGPYlGQFq0PSR+F+T/fvUuQRhAEQlw/M; fund_trade_risk=c9M3hCwgdPKJ54SiiGvX6OgCbes9mXy9GSjc5s2T0JUVkGPSs8wrWAFF9p3hVerRaev7IwJF; fund_trade_gps=2; VipLevel=0; TradeLoginToken=81465d3c62474317b0ecf0ae05109887; UTOKEN=cmBPYgBndVrn78PUQQ3QBy2DLRipv+cFmzEaZlQyBZ9n0s9ZOrK6PY2O5bOE8/xeelV8GqGJ1fGdEboYg41+Fu1CSfN9Z3KVXlRNXhI55vUl/Uzovrc=; LToken=63ee6851e0ee43c09eb9320c1ec355c6; fund_trade_trackid=Hqx7xgoi3bZj2SZrO36M/i3WcOJ2HSs+0XamtOzG7kD4mBDZF4peARksgR2musv+aGtxxl7U1Sbzd32sqnYQIw==; monitor_count=7";

//        int showType =1;
        int showType =2;

        if(showType==1){
            //        String busType = "0";//0-全部;1-申购;2-卖出;
//        //显示插入数据库语句
        showInsertDb(cookie, "2020-11-11", "2020-12-31", "1");
        //显示更新数据库语句
        showUpdateDb(cookie, "2020-06-29", "2020-12-31", "1");
//        赎回
        showDbRedem(cookie, "2020-06-29", "2020-12-31", "2");
        }

        if(showType==2){
            //////         更新最新净值-限定时间段的最大最小净值
            showUpdateDbMaxMinNetByDays(cookie, "2020-06-29", "2020-12-31", "1",30);
            // 更新最新净值-限定时间段的最大最小净值
            showUpdateDbMaxMinNetByDays(cookie, "2020-06-29", "2020-12-31", "1",60);
            // 更新最新净值-限定时间段的最大最小净值
            showUpdateDbMaxMinNetByDays(cookie, "2020-06-29", "2020-12-31", "1",90);
////        // 更新最新净值-限定时间段的最大最小净值
            showUpdateDbMaxMinNetByDays(cookie, "2020-06-29", "2020-12-31", "1",180);
            // 更新最新净值-限定时间段的最大最小净值
            showUpdateDbMaxMinNetByDays(cookie, "2020-06-29", "2020-12-31", "1",360);

            //        // 更新最新净值-限定时间段的最大最小净值
//        showUpdateDbMaxMinNetByDays(cookie, "2020-04-01", "2020-12-31", "1",1);
        }



    }

    /**
     * 显示插入数据库语句
     *
     * @param
     */
    private static void showInsertDb(String cookie, String startDate, String endDate, String busType) {
        String fundCode = "";
        TradeDao tradeService = new TradeDaoImpl();
        List<FundTrade> rs = tradeService.findMyTrade(cookie, fundCode, startDate, endDate, busType, "1");
//        System.out.println("findMyTrade:"+JSON.toJSON(rs));
        for (FundTrade fundTrade : rs) {
            if (fundTrade.getOrderStatus() != null && (fundTrade.getOrderStatus().contains("申购"))) {
                //处理业务类型
                handlerBizTp(fundTrade);
                //显示插入数据库语句
                System.out.println("INSERT INTO `bank19`.`ol_fund_trade`(" +
                        " `FD_ID`, `FD_INFO`, `TYPE`, `TRADE_TIME`, `ORDER_STATUS`, " +
                        " `CONFIRM_SHARE`, `CONFIRM_NET`, `ORDER_AMT`, `STATUS`, " +
                        " `ORDER_CODE`, `CONFIRM_AMT`, `REDEM_AMT`," +
                        " `EARN_AMT`, `CONFIRM_NET_DATA`, `SERVER_CHARGE`, " +
                        " `REDEM_STATUS`, `REDEM_SHARE`, `REDEM_TIME`, " +
                        " `SOURCE`, `FD_CODE`, `CREATE_TIME`, `UPDATE_TIME`" +
                        " ,`BIZ_TP`" +
                        " ,`RK_ST_LOSS`" + " ,`RK_ST_PROFIT`" +
                        ") VALUES (" +
                        " '', '" + fundTrade.getFundInfo() + "', '" + fundTrade.getOrderStatus() + "', '" + fundTrade.getTradeTime() + "', '" + fundTrade.getOrderStatus() + "'" +
                        ", " + fundTrade.getConfirmShare() + ", " + fundTrade.getConfirmNet() + ", " + fundTrade.getOrderAmt() + ", " + "'确认成功', " +
                        "  '', " + fundTrade.getOrderAmt() + ", 0, " +
                        " 0, '" + fundTrade.getConfirmNetData() + "', " + fundTrade.getServerCharge() + ", " +
                        " '0', 0,  '3000-01-01 00:00:00',  " +
                        " '3', '', now(), now()" +
                        " ,'" + fundTrade.getBizTy() + "'" +
                        " ," + fundTrade.getRiskStLoss() + "" +
                        " ," + fundTrade.getRiskStProfit() + "" +
//                        " ,0.92,1.1" +
                        ");");
            }
        }
    }

    /**
     * 处理业务类型
     *
     * @param fundTrade
     */
    private static void handlerBizTp(FundTrade fundTrade) {
        if ("001875|前海开源沪港深优势精选混合".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("指数");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("004746|易方达上证50指数C".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("指数");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("110003|易方达上证50指数A".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("指数");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("002671|万家沪深300指数增强C".equals(fundTrade.getFundInfo()) || "004789|富荣沪深300指数增强C".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("指数");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("160420|华安创业板50指数分级".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("指数");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("009300|西部利得中证500指数增强C".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("指数");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("110011|易方达中小盘混合".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("指数");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("008086|华夏中证5G通信主题ETF联接A".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("科技");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("001986|前海开源人工智能主题混合".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("科技");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("320007|诺安成长混合".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("科技");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("519005|海富通股票混合".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("科技");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("000977|长城环保主题混合".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("科技");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("519674|银河创新成长混合".equals(fundTrade.getFundInfo()) || "009314|广发双擎升级混合C".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("科技");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }

        if ("160633|鹏华证券分级".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("金融");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("161028|富国中证新能源汽车指数分级".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("科技");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("004857|广发中证全指建筑材料指数C".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("建材");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.05);//建材涨幅小
        }
        if ("002207|前海开源金银珠宝混合C".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("有色金属");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("005621|中欧品质消费股票C".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("消费");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("161810|银华内需精选混合(LOF)".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("消费");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("110022|易方达消费行业股票".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("消费");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("161725|招商中证白酒指数分级".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("消费");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("006308|汇添富全球消费混合人民币A".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("消费");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("164402|前海开源中航军工".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("军工");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("003096|中欧医疗健康混合C".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("医疗");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
        }
        if ("005967|鹏华创新驱动混合".equals(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("医疗");
            fundTrade.setRiskStLoss(0.92);
            fundTrade.setRiskStProfit(1.1);
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
        List<FundTrade> rs = new ArrayList<FundTrade>();
        for (int i = 1; i < 20; i++) {
            List<FundTrade> tempRs = tradeService.findMyTrade(cookie, fundCode, startDate, endDate, busType, i + "");
            if (tempRs.size() == 0) {
                break;
            }
            rs.addAll(tempRs);
        }
//        System.out.println("findMyTrade-rs.size():" + JSON.toJSON(rs.size()));
//        System.out.println("findMyTrade:"+JSON.toJSON(rs));
        for (FundTrade fundTrade : rs) {
            String lsjzUrl = "fundCode=" + fundTrade.getFundCode() + "&pageIndex=1&pageSize=100&startDate=" + "" + "&endDate=" + "" + "&_=1558194929451";
            byte[] bytes = "".getBytes();
            LsjzDataLsjz lsjzDataLsjz = HttpUtil.sendPostTtjjLsjzLastOne(lsjzUrl, bytes, new HashMap<String, String>());
//        System.out.println("lsjzDataLsjz:"+JSON.toJSON(lsjzDataLsjz));
            //打印-
            if (lsjzDataLsjz == null) {
                continue;
            }
            System.out.println("UPDATE `ol_fund_trade` " +
                    "SET " +
                    " `LAST_NET`=" + lsjzDataLsjz.getDWJZ() + " " +
                    ",`NET_MAX_1`=" + lsjzDataLsjz.getLJJZ() + " " +
                    ",`NET_MIN_1`=" + lsjzDataLsjz.getLJJZ() + " " +
                    ",`LAST_DATE`='" + lsjzDataLsjz.getFSRQ() + "' " +
                    "WHERE  `FD_INFO` = '" + fundTrade.getFundInfo() + "' " +
//                        "AND (`TYPE` = '申购' OR `TYPE` = '申购(赎回中)')"+
                    " ;");
        }
    }

    /**
     * 更新最新净值-限定时间段的最大最小净值
     *  @param cookie
     * @param startDate
     * @param endDate
     * @param busType
     * @param days
     */
    private static void showUpdateDbMaxMinNetByDays(String cookie, String startDate, String endDate, String busType, int days) {
        String fundCode = "";
        TradeDao tradeService = new TradeDaoImpl();
        List<FundTrade> rs = new ArrayList<FundTrade>();
        for (int i = 1; i < 40; i++) {
            List<FundTrade> tempRs = tradeService.findMyTrade(cookie, fundCode, startDate, endDate, busType, i + "");
            if (tempRs.size() == 0) {
                break;
            }
            rs.addAll(tempRs);
        }
//        System.out.println("findMyTrade-rs.size():" + JSON.toJSON(rs.size()));
//        System.out.println("findMyTrade:"+JSON.toJSON(rs));
        for (FundTrade fundTrade : rs) {
            //查询 -限定时间段的最大最小净值
            LsjzUtil.findJzMaxMin(fundTrade.getFundInfo(),days);
        }
    }

    /**
     * 更新最新净值-我自己-手工添加
     * @param cookie
     * @param startDate
     * @param endDate
     * @param busType
     */
    private static void showUpdateDbMy(String cookie, String startDate, String endDate, String busType) {
        List<FundTrade> rs = new ArrayList<FundTrade>();
        FundTrade fundTrade1 = new FundTrade();
        fundTrade1.setFundInfo("160633|鹏华证券分级");
        rs.add(fundTrade1);
//        String fundCode = "";
//        TradeDao tradeService = new TradeDaoImpl();
//        for (int i = 1; i < 20; i++) {
//            List<FundTrade> tempRs = tradeService.findMyTrade(cookie, fundCode, startDate, endDate, busType, i + "");
//            if (tempRs.size() == 0) {
//                break;
//            }
//            rs.addAll(tempRs);
//        }
//        System.out.println("findMyTrade-rs.size():" + JSON.toJSON(rs.size()));
//        System.out.println("findMyTrade:"+JSON.toJSON(rs));
        for (FundTrade fundTrade : rs) {
            String lsjzUrl = "fundCode=" + fundTrade.getFundCode() + "&pageIndex=1&pageSize=100&startDate=" + "" + "&endDate=" + "" + "&_=1558194929451";
            byte[] bytes = "".getBytes();
            LsjzDataLsjz lsjzDataLsjz = HttpUtil.sendPostTtjjLsjzLastOne(lsjzUrl, bytes, new HashMap<String, String>());
//        System.out.println("lsjzDataLsjz:"+JSON.toJSON(lsjzDataLsjz));
            //打印-
            if (lsjzDataLsjz == null) {
                continue;
            }
            System.out.println("UPDATE `ol_fund_trade` " +
                    "SET " +
                    " `LAST_NET`=" + lsjzDataLsjz.getDWJZ() + " " +
                    ",`LAST_DATE`='" + lsjzDataLsjz.getFSRQ() + "' " +
                    "WHERE  `FD_INFO` = '" + fundTrade.getFundInfo() + "' " +
//                        "AND (`TYPE` = '申购' OR `TYPE` = '申购(赎回中)')"+
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
        List<FundTrade> rs = new ArrayList<FundTrade>();
        for (int i = 1; i < 20; i++) {
            List<FundTrade> tempRs = tradeService.findMyTrade(cookie, fundCode, startDate, endDate, busType, i + "");
            if (tempRs.size() == 0) {
                break;
            }
            rs.addAll(tempRs);
        }
//        System.out.println("findMyTrade:"+ JSON.toJSONString(rs));
        for (FundTrade fundTrade : rs) {
            if (fundTrade.getOrderStatus() != null && (fundTrade.getOrderStatus().contains("赎回")) && fundTrade.getOrderAmt() != null) {
                //打印-赎回-update
                BigDecimal enrnAmtSubServerCharge = fundTrade.getOrderAmt().subtract(fundTrade.getServerCharge());
                StringBuffer sb = new StringBuffer();
                sb.append("UPDATE `ol_fund_trade` " +
//                System.out.println("UPDATE `ol_fund_trade` " +
                        "SET `TYPE`='申购(赎回)'");
                if (fundTrade.getConfirmNet() != null) {
                    sb.append(",`NET_REDEM`=" + fundTrade.getConfirmNet() + " ");
                }
                if (fundTrade.getConfirmShare() != null) {
                    sb.append(",`REDEM_SHARE`=" + fundTrade.getConfirmShare() + " ");
                }
//                        ",`ORDER_AMT`=" + fundTrade.getOrderAmt() + " " +
                sb.append(",`REDEM_TIME`='" + fundTrade.getConfirmNetData() + "' " +
                        ",`REDEM_AMT`=" + fundTrade.getOrderAmt() + " " +
//                        ",`SERVER_CHARGE`=ROUND((" + fundTrade.getServerCharge() + "+`SERVER_CHARGE`) ,2)" +
                        ",`SERVER_CHARGE_REDEM`=" + fundTrade.getServerCharge() + "" +
                        ",`REDEM_STATUS`=1" +
                        ",`EARN_AMT`=ROUND((" + enrnAmtSubServerCharge + "-`CONFIRM_AMT`-`SERVER_CHARGE`) ,2)" +
                        "WHERE  `FD_INFO` = '" + fundTrade.getFundInfo() + "' "
                        + "AND (`TYPE` = '申购' OR `TYPE` = '申购(赎回中)')"
                        + "AND `CONFIRM_SHARE` = '" + fundTrade.getConfirmShare() + "' " +
                        " LIMIT 1; ");
                System.out.println(sb.toString());
            }
        }
    }


}
