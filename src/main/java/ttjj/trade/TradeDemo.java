package ttjj.trade;

import ttjj.Dao.TradeDao;
import ttjj.Dao.impl.TradeDaoImpl;
import ttjj.dto.FundTrade;
import ttjj.dto.LsjzDataLsjz;
import utils.HttpUtil;
import utils.LsjzUtil;

import java.math.BigDecimal;
import java.util.*;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author chenzhilong
 * @date 2020/8/4
 */
public class TradeDemo {
    public static void main(String[] args) {
        String cookie = "FundTradeLoginCard=0; FundTradeLoginTab=0; __guid=26890232.840238135491018600.1610158880294.6384; st_si=43345134256896; st_asi=delete; cp_token=cd0e74d0bb3a4d4a916f544503a92afa; FundTradeLoginUser=DAqpBMSzdv7vTH2C8w20ONUeP2G7hPGAsiAF4GXNubPyuDP7HDrMW1Rwiwwb3f1bBivMgEkb; fund_trade_cn=D+xKtV7vpFDJ9bvq3MwbA1+7EG4l2/d5jwJ8qnuZJ07SCZ1TDqLlU57h4+Q1V7u0yFRRv4D3eQ7VbCy8q1eoRtgHrgPdJIj4B9bNPTSLM9GYfUa2pdQ=; fund_trade_name=Db5hcX9xFvYw8mt0oO2QeD30uIT7VxjwviJC4pacRCrJIDn3klgRkrRBb1LM7EmbUgNiPXmb; fund_trade_visitor=Dw/McDBFzv5xBFC6ZA2zBB2egNT7zeGjcgK64Egg2TUBrDVFb/lOJzRe6m66fCDbLXqA2vdO; fund_trade_risk=DrfQL4unOvnh6BjUnB2TGA2I85g7KRLfhUut41uRvvRzkDdGY4/P/SRRwOaKaSubhWk+BpuB; fund_trade_gps=2; VipLevel=0; TradeLoginToken=fdd0101f707e45b9bbb19739b5ff8ed2; UTOKEN=D+xKtV7vpFDJ9bvq3MwbA1+7EG4l2/d5jwJ8qnuZJ07SCZ1TDqLlU57h4+Q1V7u0yFRRvMDEp0sFqHsvHQSeRU5Vhvu1ShcQn4bhgbnb+ufMt0dFcHc=; LToken=cd8ae87658624ca593abd6dc4055cfb0; fund_trade_trackid=VtrteNqrHR9zYsh83G4EXsJ0BmMBIETs2WJAYIvnxC67lwxNUIlOd2IFqplUfHGVlzhD7hZmFyOppXBgOCXgWg==; ASP.NET_SessionId=0ijpojocyed0hntlmvfcvvjn; monitor_count=2; st_pvi=34528644972697; st_sp=2020-03-21%2009%3A52%3A13; st_inirUrl=https%3A%2F%2Flogin.1234567.com.cn%2Flogin; st_sn=3; st_psi=20210419225221954-1190151312948-1606117717";

        boolean showTypeBuyOrSell = true;//新增或卖出交易
        boolean showTypeNet = true;//更新每日净值

        String startDate = "2020-12-16";
        String endDate = "2021-12-31";

        if (showTypeBuyOrSell) {
//        //显示插入数据库语句
            String bizTypeBuy = "1";//0-全部;1-申购;2-卖出;
            String bizTypeRedem = "2";//0-全部;1-申购;2-卖出;
            String insertStartDate = "2021-03-19";//查询新增交易的开始时间
            showInsertDb(cookie, insertStartDate, endDate, bizTypeBuy);
            //显示更新数据库语句
            showUpdateDb(cookie, startDate, endDate, bizTypeBuy);
//        赎回
            showDbRedem(cookie, startDate, endDate, bizTypeRedem);
        }

        if (showTypeNet) {
            String bizType = "1";
            //        // 更新最新净值-限定时间段的最大最小净值
            showUpdateDbMaxMinNetByDays(cookie, startDate, endDate, bizType, 0);//0是今天，1是昨天
            showUpdateDbMaxMinNetByDays(cookie, startDate, endDate, bizType, 7);
            showUpdateDbMaxMinNetByDays(cookie, startDate, endDate, bizType, 14);
            showUpdateDbMaxMinNetByDays(cookie, startDate, endDate, bizType, 30);
            showUpdateDbMaxMinNetByDays(cookie, startDate, endDate, bizType, 60);
            showUpdateDbMaxMinNetByDays(cookie, startDate, endDate, bizType, 90);
            showUpdateDbMaxMinNetByDays(cookie, startDate, endDate, bizType, 180);
            showUpdateDbMaxMinNetByDays(cookie, startDate, endDate, bizType, 365);

//            showUpdateDbMaxMinNetByDays(cookie, startDate, endDate, "1",7);

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
        double baseRiskStLoss = 0.95;
        double baseRiskStProfit = 1.15;

        //指数
        List<String> typeListZhiShu = new ArrayList<>();
        typeListZhiShu.add("004746|易方达上证50增强C");
        typeListZhiShu.add("004789|富荣沪深300指数增强C");
        typeListZhiShu.add("005827|易方达蓝筹精选混合");
        typeListZhiShu.add("001875|前海开源沪港深优势精选混合");
        typeListZhiShu.add("160420|华安创业板50指数");
        typeListZhiShu.add("110011|易方达中小盘混合");
        typeListZhiShu.add("481010|工银中小盘混合");
        typeListZhiShu.add("004408|招商深证100指数C");
        typeListZhiShu.add("009300|西部利得中证500指数增强C");
        typeListZhiShu.add("160637|鹏华创业板分级");
        typeListZhiShu.add("002671|万家沪深300指数增强C");
        typeListZhiShu.add("110003|易方达上证50增强A");
        if (typeListZhiShu.contains(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("指数");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        //科技
        List<String> typeListKeJi = new ArrayList<>();
        typeListKeJi.add("002190|农银新能源主题");
        typeListKeJi.add("004997|广发高端制造股票A");
        typeListKeJi.add("519674|银河创新成长混合");
        typeListKeJi.add("009314|广发双擎升级混合C");
        typeListKeJi.add("320007|诺安成长混合");
        typeListKeJi.add("001986|前海开源人工智能主题混合");
        typeListKeJi.add("161028|富国中证新能源汽车指数(LOF)");
        typeListKeJi.add("001606|农银工业4.0混合");
        typeListKeJi.add("008086|华夏中证5G通信主题ETF联接A");
        typeListKeJi.add("519005|海富通股票混合");
        typeListKeJi.add("000977|长城环保主题混合");
        typeListKeJi.add("163402|兴全趋势投资混合(LOF)");
        typeListKeJi.add("161903|万家行业优选混合(LOF)");
        typeListKeJi.add("005969|创金合信工业周期股票C");
        typeListKeJi.add("000594|大摩进取优选股票");
        if (typeListKeJi.contains(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("科技");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        List<String> typeListXiaoFei = new ArrayList<>();
        typeListXiaoFei.add("005621|中欧品质消费股票C");
        typeListXiaoFei.add("161810|银华内需精选混合(LOF)");
        typeListXiaoFei.add("110022|易方达消费行业股票");
        typeListXiaoFei.add("161725|招商中证白酒指数(LOF)");
        typeListXiaoFei.add("006308|汇添富全球消费混合人民币A");
        if (typeListXiaoFei.contains(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("消费");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        List<String> typeListYiLiao = new ArrayList<>();
        typeListYiLiao.add("003096|中欧医疗健康混合C");
        typeListYiLiao.add("005967|鹏华创新驱动混合");
        if (typeListYiLiao.contains(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("医药");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        List<String> typeListJinRong = new ArrayList<>();
        typeListJinRong.add("167301|方正富邦保险主题指数");
        typeListJinRong.add("160633|鹏华中证全指证券公司指数(LOF)");
        typeListJinRong.add("161121|易方达中证银行指数(LOF)A");
        typeListJinRong.add("160628|鹏华中证800地产指数(LOF)");
        if (typeListJinRong.contains(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("金融");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        List<String> typeListYouSe = new ArrayList<>();
        typeListYouSe.add("165520|信诚中证800有色指数(LOF)");
        typeListYouSe.add("002207|前海开源金银珠宝混合C");
        if (typeListYouSe.contains(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("有色");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        List<String> typeListChuanMei = new ArrayList<>();
//        typeListChuanMei.add("160629|鹏华传媒分级");
        typeListChuanMei.add("160629|鹏华中证传媒指数(LOF)");
        if (typeListChuanMei.contains(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("传媒");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        List<String> typeListJianCai = new ArrayList<>();
        typeListJianCai.add("004857|广发中证全指建筑材料指数C");
        if (typeListJianCai.contains(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("建材");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        List<String> typeListJunGong = new ArrayList<>();
        typeListJunGong.add("164402|前海开源中航军工指数");
        if (typeListJunGong.contains(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("军工");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }


        List<String> typeListNongYe = new ArrayList<>();
        typeListNongYe.add("001027|前海开源中证大农业指数增强");
        if (typeListNongYe.contains(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("农业");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        //混合
        List<String> typeListHunHe = new ArrayList<>();
        typeListHunHe.add("519212|万家宏观择时多策略混合");
        if (typeListHunHe.contains(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("混合");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        //环保
        List<String> typeListHuanBao = new ArrayList<>();
        typeListHuanBao.add("501031|汇添富中证环境治理指数C");
        if (typeListHuanBao.contains(fundTrade.getFundInfo())) {
            fundTrade.setBizTy("环保");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
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
        rs = rs.stream().collect(
                collectingAndThen(toCollection(() ->
                        new TreeSet<>(Comparator.comparing(FundTrade::getFundInfo))), ArrayList::new
                )
        );
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
//                    ",`NET_MAX_1`=" + lsjzDataLsjz.getDWJZ() + " " +
//                    ",`NET_MIN_1`=" + lsjzDataLsjz.getDWJZ() + " " +
                    ",`LAST_DATE`='" + lsjzDataLsjz.getFSRQ() + "' " +
                    "WHERE  `FD_INFO` = '" + fundTrade.getFundInfo() + "' " +
//                        "AND (`TYPE` = '申购' OR `TYPE` = '申购(赎回中)')"+
                    " ;");
        }
    }

    /**
     * 更新最新净值-限定时间段的最大最小净值
     *
     * @param cookie
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
        rs = rs.stream().collect(
                collectingAndThen(toCollection(() ->
                        new TreeSet<>(Comparator.comparing(FundTrade::getFundInfo))), ArrayList::new
                )
        );
        for (FundTrade fundTrade : rs) {
            //查询 -限定时间段的最大最小净值
            LsjzUtil.findJzMaxMin(fundTrade.getFundInfo(), days);
        }
    }

    /**
     * 更新最新净值-我自己-手工添加
     *
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
//        rs = rs.stream().collect(
//                collectingAndThen(toCollection(() ->
//                        new TreeSet<>(Comparator.comparing(FundTrade::getFundInfo))), ArrayList::new
//                )
//        );
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
