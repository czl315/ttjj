package ttjj.trade;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import ttjj.Dao.TradeDao;
import ttjj.Dao.TradeStockDao;
import ttjj.Dao.impl.TradeDaoImpl;
import ttjj.Dao.impl.TradeStockDaoImpl;
import ttjj.dto.FundTrade;
import ttjj.dto.LsjzDataLsjz;
import ttjj.dto.StockTrade;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author chenzhilong
 * @date 2020/8/4
 */
public class StockTraceDemo {
    public static void main(String[] args) {
        String cookie = "__guid=260925462.4161440383634452500.1615302736826.6602; Yybdm=5408; Uid=fNUE23lwQOlyHFRjGcQYdA%3d%3d; Khmc=%e9%99%88%e5%bf%97%e9%be%99; eastmoney_txzq_zjzh=NTQwODIwMTc0NTY5fA%3D%3D; mobileimei=f1db2a73-5d30-4835-b6e7-629a26c15834; Uuid=cd7e46d7039547128b334b958798c98d; monitor_count=33";
        String validatekey = "c2c1e931-cb40-44d5-9106-4c66362fe9ef";
        int showType = 1;

        String startDate = "2020-12-09";
        String endDate = "2021-12-31";

        if (showType == 1) {
//        //显示插入数据库语句
            String bizTypeBuy = "1";//0-全部;1-申购;2-卖出;
            String insertStartDate = "2021-01-01";//查询新增交易的开始时间
            String insertEndDate = "2021-03-11";//查询新增交易的结束时间
            showInsertDb(cookie, validatekey, insertStartDate, insertEndDate, bizTypeBuy);
        }

        if (showType == 2) {
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
    private static void showInsertDb(String cookie, String validatekey, String startDate, String endDate, String busType) {
        TradeStockDao tradeService = new TradeStockDaoImpl();
        String fundCode = "";
        List<StockTrade> rs = tradeService.findMyStockTrade(cookie, fundCode, startDate, endDate, busType, "1", validatekey);
//        System.out.println("findMyTrade:" + JSON.toJSON(rs));
        for (StockTrade fundTrade : rs) {
            //"ywsm": "证券买入",
            String type = "证券买入";
            if (fundTrade.getYwsm().equals(type)) {
                //处理业务类型
                handlerBizTp(fundTrade);

                Date tradeTimeDate = null;
                try {
                    tradeTimeDate = new SimpleDateFormat("yyyyMMdd hhmmss").parse(fundTrade.getFsrq() + " " + fundTrade.getFssj());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String tradeTimeDateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(tradeTimeDate);

                //显示插入数据库语句
                System.out.println("INSERT INTO `bank19`.`ol_stock_trade`(" +
                        " `FD_CODE`" +
                        ",`FD_INFO`" +
                        ", `TYPE`" +
                        ", `TRADE_TIME`" +
                        ", `ORDER_STATUS`, " +
                        " `CONFIRM_SHARE`" +
                        ", `CONFIRM_NET`" +
                        ", `ORDER_AMT`" +
                        ", `STATUS`, " +
                        " `ORDER_CODE`" +
                        ", `CONFIRM_AMT`" +
                        ", `REDEM_AMT`," +
                        " `EARN_AMT`" +
                        ", `CONFIRM_NET_DATA`" +
                        ", `SERVER_CHARGE`, " +
                        " `REDEM_STATUS`" +
                        ", `REDEM_SHARE`" +
                        ", `REDEM_TIME`" +
                        ",`SOURCE`" +
                        ",  `CREATE_TIME`" +
                        ", `UPDATE_TIME`" +
                        " ,`BIZ_TP`" +
                        " ,`RK_ST_LOSS`" +
                        ",`RK_ST_PROFIT`" +
                        ") VALUES (" +
                        " '" + fundTrade.getZqdm() + "'" +
                        ", '" + fundTrade.getZqmc() + "'" +
                        ", '" + type + "', '" + tradeTimeDateStr + "', '" + type + "'" +
                        ", " + fundTrade.getCjsl() + ", " + fundTrade.getCjjg() + ", " + fundTrade.getCjje() + ", " + "'确认成功', " +
                        "  '', " + fundTrade.getFsje() + ", 0, " +
                        " 0, '" + tradeTimeDateStr + "', " + fundTrade.getSxf() + ", " +
                        " '0', 0,  '3000-01-01 00:00:00',  " +
                        " '4', now(), now()" +
                        " ,'" + (fundTrade.getBizTy() == null ? "" : fundTrade.getBizTy()) + "'" +
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
    private static void handlerBizTp(StockTrade fundTrade) {
        double baseRiskStLoss = 0.95;
        double baseRiskStProfit = 1.15;

        //传媒
        List<String> typeListChuanMei = new ArrayList<>();
        typeListChuanMei.add("002027");//分众传媒
        if (typeListChuanMei.contains(fundTrade.getZqdm())) {
            fundTrade.setBizTy("传媒");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        //工程建设
        List<String> typeListGongCheng = new ArrayList<>();
        typeListGongCheng.add("601668");//中国建筑
        typeListGongCheng.add("002271");//东方雨虹-水泥建材
        if (typeListGongCheng.contains(fundTrade.getZqdm())) {
            fundTrade.setBizTy("工程建设");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        //旅游酒店
        List<String> typeListLvYouJiuDian = new ArrayList<>();
        typeListLvYouJiuDian.add("600138");//中青旅
        typeListLvYouJiuDian.add("600258");//首旅酒店
        typeListLvYouJiuDian.add("600115");//东方航空-民航机场
        if (typeListLvYouJiuDian.contains(fundTrade.getZqdm())) {
            fundTrade.setBizTy("旅游酒店");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        //科技
        List<String> typeListKeJi = new ArrayList<>();
        typeListKeJi.add("515050");//5GETF
        typeListKeJi.add("159995");//芯片ETF
        typeListKeJi.add("002202");//金风科技
        typeListKeJi.add("600517");//国网英大
        typeListKeJi.add("159813");//芯片
        typeListKeJi.add("600703");//三安光电
        if (typeListKeJi.contains(fundTrade.getZqdm())) {
            fundTrade.setBizTy("科技");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        //环保
        List<String> typeListHuanBao = new ArrayList<>();
        typeListHuanBao.add("600217");//中再资环
        typeListHuanBao.add("002340");//格林美
        if (typeListHuanBao.contains(fundTrade.getZqdm())) {
            fundTrade.setBizTy("环保");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        //指数
        List<String> typeListZhiShu = new ArrayList<>();
        typeListZhiShu.add("510050");//50ETF
        typeListZhiShu.add("159915");//创业板
        typeListZhiShu.add("510310");//HS300ETF
        typeListZhiShu.add("159982");//中证500
        typeListZhiShu.add("588090");//科创板
        if (typeListZhiShu.contains(fundTrade.getZqdm())) {
            fundTrade.setBizTy("指数");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }


        List<String> typeListJunGong = new ArrayList<>();
        typeListJunGong.add("512560");//中证军工
        if (typeListJunGong.contains(fundTrade.getZqdm())) {
            fundTrade.setBizTy("军工");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        List<String> typeListQiHuo = new ArrayList<>();
        typeListQiHuo.add("159985");//豆粕ETF
        if (typeListQiHuo.contains(fundTrade.getZqdm())) {
            fundTrade.setBizTy("期货");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        List<String> typeListJinRong = new ArrayList<>();
        typeListJinRong.add("601555");//东吴证券
        if (typeListJinRong.contains(fundTrade.getZqdm())) {
            fundTrade.setBizTy("金融");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

        List<String> typeListYiLiao = new ArrayList<>();
        typeListYiLiao.add("159929");//医药ETF
        if (typeListYiLiao.contains(fundTrade.getZqdm())) {
            fundTrade.setBizTy("医药");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

//        List<String> typeListXiaoFei = new ArrayList<>();
//        typeListXiaoFei.add("005621|中欧品质消费股票C");
//        typeListXiaoFei.add("161810|银华内需精选混合(LOF)");
//        typeListXiaoFei.add("110022|易方达消费行业股票");
//        typeListXiaoFei.add("161725|招商中证白酒指数(LOF)");
//        typeListXiaoFei.add("006308|汇添富全球消费混合人民币A");
//        if (typeListXiaoFei.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("消费");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }


//        List<String> typeListYouSe = new ArrayList<>();
//        typeListYouSe.add("165520|信诚中证800有色指数(LOF)");
//        typeListYouSe.add("002207|前海开源金银珠宝混合C");
//        if (typeListYouSe.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("有色");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }


//        List<String> typeListNongYe = new ArrayList<>();
//        typeListNongYe.add("001027|前海开源中证大农业指数增强");
//        if (typeListNongYe.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("农业");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }

//        //混合
//        List<String> typeListHunHe = new ArrayList<>();
//        typeListHunHe.add("519212|万家宏观择时多策略混合");
//        if (typeListHunHe.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("混合");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }


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
        TradeStockDao tradeService = new TradeStockDaoImpl();
        String fundCode = "";
        String validatekey = "c2c1e931-cb40-44d5-9106-4c66362fe9ef";
        List<StockTrade> rs = new ArrayList<StockTrade>();
        for (int i = 1; i < 40; i++) {
            List<StockTrade> tempRs = tradeService.findMyStockTrade(cookie, fundCode, startDate, endDate, busType, i + "", validatekey);
            if (tempRs.size() == 0) {
                break;
            }
            rs.addAll(tempRs);
        }
//        System.out.println("findMyTrade-rs.size():" + JSON.toJSON(rs.size()));
//        System.out.println("findMyTrade:"+JSON.toJSON(rs));
        rs = rs.stream().collect(
                collectingAndThen(toCollection(() ->
                        new TreeSet<>(Comparator.comparing(StockTrade::getZqmc))), ArrayList::new
                )
        );
        for (StockTrade fundTrade : rs) {
            //查询 -限定时间段的最大最小净值
            LsjzUtil.findJzMaxMin(fundTrade.getZqdm(), days);
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
