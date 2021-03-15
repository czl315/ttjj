package ttjj.trade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import ttjj.Dao.TradeDao;
import ttjj.Dao.TradeStockDao;
import ttjj.Dao.impl.TradeDaoImpl;
import ttjj.Dao.impl.TradeStockDaoImpl;
import ttjj.dto.FundTrade;
import ttjj.dto.LsjzDataLsjz;
import ttjj.dto.StockTrade;
import ttjj.dto.StockTradeToday;
import ttjj.point.SzzsData;
import utils.HttpUtil;
import work.NhjXjkTyjSend200;

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
    static String keyRsMax = "rsMax";
    static String keyRsMin = "rsMin";

    public static void main(String[] args) {
        String cookie = "__guid=260925462.4161440383634452500.1615302736826.6602; eastmoney_txzq_zjzh=NTQwODIwMTc0NTY5fA%3D%3D; Yybdm=5408; Uid=fNUE23lwQOlyHFRjGcQYdA%3d%3d; Khmc=%e9%99%88%e5%bf%97%e9%be%99; mobileimei=22854f22-d447-4da4-b3d4-6a4863129413; Uuid=36fe1d1b00d04d7c8cadf1dc0f60b08b; monitor_count=4";
        String validatekey = "16c027d0-b34b-4b52-9d17-9cd1b2601677";

        int showType = 1;
//        int showType = 2;

        if (showType == 1) {
//        //显示插入数据库语句
            String bizTypeBuy = "1";//0-全部;1-申购;2-卖出;
            String insertStartDate = "2021-01-01";//查询新增交易的开始时间
            String insertEndDate = "2021-03-15";//查询新增交易的结束时间
            showInsertDb(cookie, validatekey, insertStartDate, insertEndDate, bizTypeBuy);

//            //  赎回
//            String bizTypeSell = "2";//0-全部;1-申购;2-卖出;
//            String sellStartDate = "2021-01-01";
//            String sellEndDate = "2021-12-31";
//            showDbRedem(cookie,validatekey, sellStartDate, sellEndDate, bizTypeSell);
        }


        if (showType == 2) {
            //        // 更新最新净值-限定时间段的最大最小净值
            showUpdateDbMaxMinNetByDays(cookie, 1,  "LAST_NET", "LAST_NET");
            showUpdateDbMaxMinNetByDays(cookie, 1, "NET_MIN_1", "NET_MAX_1");
            showUpdateDbMaxMinNetByDays(cookie, 7,  "NET_MIN_7", "NET_MAX_7");
            showUpdateDbMaxMinNetByDays(cookie, 14,  "NET_MIN_14", "NET_MAX_14");
            showUpdateDbMaxMinNetByDays(cookie, 30,  "NET_MIN_30", "NET_MAX_30");
            showUpdateDbMaxMinNetByDays(cookie, 60,  "NET_MIN_60", "NET_MAX_60");
            showUpdateDbMaxMinNetByDays(cookie, 90,  "NET_MIN_90", "NET_MAX_90");
            showUpdateDbMaxMinNetByDays(cookie, 180,  "NET_MIN_180", "NET_MAX_180");
            showUpdateDbMaxMinNetByDays(cookie, 365,  "NET_MIN_360", "NET_MAX_360");

        }


    }

    /**
     * 显示插入数据库语句
     *
     * @param
     */
    private static void showInsertDb(String cookie, String validatekey, String startDate, String endDate, String busType) {
        TradeStockDao tradeService = new TradeStockDaoImpl();
        List<StockTrade> rs = tradeService.findMyStockTrade(cookie, startDate, endDate, validatekey);
        System.out.println("findMyTrade:" + JSON.toJSON(rs));
        for (StockTrade trade : rs) {
            //"ywsm": "证券买入",
            String type = "证券买入";
            if (type.equals(trade.getYwsm())) {
                //处理业务类型
                handlerBizTp(trade);

                Date tradeTimeDate = null;
                try {
                    tradeTimeDate = new SimpleDateFormat("yyyyMMdd hhmmss").parse(trade.getFsrq() + " " + trade.getFssj());
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
                        " '" + trade.getZqdm() + "'" +
                        ", '" + trade.getZqmc() + "'" +
                        ", '" + type
                        + "', '" + tradeTimeDateStr
                        + "', '" + type + "'" +
                        ", " + trade.getCjsl() + "" +
                        ", " + trade.getCjjg()
                        + ", " + trade.getCjje()
                        + ", " + "'确认成功', "
//                        +"  '', " + fundTrade.getFsje()
                        + "  '', " + trade.getCjje()
                        + ", 0, " +
                        " 0, '" + tradeTimeDateStr + "', " + trade.getSxf() + ", " +
                        " '0', 0,  '3000-01-01 00:00:00',  " +
                        " '4', now(), now()" +
                        " ,'" + (trade.getBizTy() == null ? "" : trade.getBizTy()) + "'" +
                        " ," + trade.getRiskStLoss() + "" +
                        " ," + trade.getRiskStProfit() + "" +
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

        //指数
        List<String> typeListZhiShu = new ArrayList<>();
        typeListZhiShu.add("510050");//50ETF
        typeListZhiShu.add("159915");//创业板
        typeListZhiShu.add("510310");//HS300ETF
        typeListZhiShu.add("159982");//中证500
        typeListZhiShu.add("588090");//科创板
        typeListZhiShu.add("588080");//科创板50
        if (typeListZhiShu.contains(fundTrade.getZqdm())) {
            fundTrade.setBizTy("指数");
            fundTrade.setRiskStLoss(baseRiskStLoss);
            fundTrade.setRiskStProfit(baseRiskStProfit);
            return;
        }

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
     * 查询我的股票列表
     */
    private static List<StockTrade> listMyStock() {
        List<StockTrade> rs = new ArrayList<>();
        StockTrade stockTradeTemp = new StockTrade();

        //传媒
        List<StockTrade> typeListChuanMei = new ArrayList<>();
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("002027");//分众传媒
        typeListChuanMei.add(stockTradeTemp);

        //工程建设
        List<StockTrade> typeListGongCheng = new ArrayList<>();
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("601668");//中国建筑
        typeListGongCheng.add(stockTradeTemp);
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("002271");//东方雨虹-水泥建材
        typeListGongCheng.add(stockTradeTemp);

        //旅游酒店
        List<StockTrade> typeListLvYouJiuDian = new ArrayList<>();
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("600138");//中青旅
        typeListLvYouJiuDian.add(stockTradeTemp);
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("600258");//首旅酒店
        typeListLvYouJiuDian.add(stockTradeTemp);
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("600115");//东方航空-民航机场
        typeListLvYouJiuDian.add(stockTradeTemp);

        //科技
        List<StockTrade> typeListKeJi = new ArrayList<>();
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("515050");//5GETF
        typeListKeJi.add(stockTradeTemp);
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("159995");//芯片ETF
        typeListKeJi.add(stockTradeTemp);
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("002202");//金风科技
        typeListKeJi.add(stockTradeTemp);
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("600517");//国网英大
        typeListKeJi.add(stockTradeTemp);
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("159813");//芯片
        typeListKeJi.add(stockTradeTemp);
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("600703");//三安光电
        typeListKeJi.add(stockTradeTemp);

        //环保
        List<StockTrade> typeListHuanBao = new ArrayList<>();
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("600217");//中再资环
        typeListHuanBao.add(stockTradeTemp);
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("002340");//格林美
        typeListHuanBao.add(stockTradeTemp);

        //指数
        List<StockTrade> typeListZhiShu = new ArrayList<>();
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("510050");//50ETF
        typeListZhiShu.add(stockTradeTemp);
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("159915");//创业板
        typeListZhiShu.add(stockTradeTemp);
        stockTradeTemp.setZqdm("510310");//HS300ETF
        typeListZhiShu.add(stockTradeTemp);
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("159982");//中证500
        typeListZhiShu.add(stockTradeTemp);
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("588090");//科创板
        typeListZhiShu.add(stockTradeTemp);
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("588080");//科创板50
        typeListZhiShu.add(stockTradeTemp);


        List<StockTrade> typeListJunGong = new ArrayList<>();
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("512560");//中证军工
        typeListJunGong.add(stockTradeTemp);

        List<StockTrade> typeListQiHuo = new ArrayList<>();
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("159985");//豆粕ETF
        typeListQiHuo.add(stockTradeTemp);

        List<StockTrade> typeListJinRong = new ArrayList<>();
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("601555");//东吴证券
        typeListJinRong.add(stockTradeTemp);

        List<StockTrade> typeListYiLiao = new ArrayList<>();
        stockTradeTemp = new StockTrade();
        stockTradeTemp.setZqdm("159929");//医药ETF
        typeListYiLiao.add(stockTradeTemp);

        rs.addAll(typeListChuanMei);
        rs.addAll(typeListGongCheng);
        rs.addAll(typeListHuanBao);
        rs.addAll(typeListJinRong);
        rs.addAll(typeListJunGong);
        rs.addAll(typeListKeJi);
        rs.addAll(typeListLvYouJiuDian);
        rs.addAll(typeListQiHuo);
        rs.addAll(typeListYiLiao);
        rs.addAll(typeListZhiShu);
        return rs;
    }

    /**
     * 更新最新净值
     *
     * @param startDate
     * @param endDate
     */
    private static void showUpdateDb(String startDate, String endDate, String tableName) {
        List<StockTrade> stockTradeList = listMyStock();
        for (StockTrade fundTrade : stockTradeList) {
            String lsjzUrl = "fundCode=" + fundTrade.getZqdm() + "&pageIndex=1&pageSize=100&startDate=" + startDate + "" + "&endDate=" + endDate + "" + "&_=1558194929451";
            byte[] bytes = "".getBytes();
            LsjzDataLsjz lsjzDataLsjz = HttpUtil.sendPostTtjjLsjzLastOne(lsjzUrl, bytes, new HashMap<String, String>());
//        System.out.println("lsjzDataLsjz:"+JSON.toJSON(lsjzDataLsjz));
            //打印-
            if (lsjzDataLsjz == null) {
                continue;
            }
            System.out.println("UPDATE `" + tableName + "` " +
                    "SET " +
                    " `LAST_NET`=" + lsjzDataLsjz.getDWJZ() + " " +
                    ",`NET_MAX_1`=" + lsjzDataLsjz.getLJJZ() + " " +
                    ",`NET_MIN_1`=" + lsjzDataLsjz.getLJJZ() + " " +
//                    ",`NET_MAX_1`=" + lsjzDataLsjz.getDWJZ() + " " +
//                    ",`NET_MIN_1`=" + lsjzDataLsjz.getDWJZ() + " " +
                    ",`LAST_DATE`='" + lsjzDataLsjz.getFSRQ() + "' " +
                    "WHERE  `FD_CODE` = '" + fundTrade.getZqdm() + "' " +
//                        "AND (`TYPE` = '申购' OR `TYPE` = '申购(赎回中)')"+
                    " ;");
        }
    }

    /**
     * 更新最新净值-限定时间段的最大最小净值
     *
     * @param cookie
     * @param days
     * @param dbFieldLastNetMin
     */
    private static void showUpdateDbMaxMinNetByDays(String cookie, int days, String dbFieldLastNetMin, String dbFieldLastNetMax) {
        List<StockTrade> stockTradeList = listMyStock();//查询我的列表
        for (StockTrade fundTrade : stockTradeList) {
            //查询 -限定时间段的最大最小净值
//            LsjzUtil.findJzMaxMin(fundTrade.getZqdm(), days);
            //k线
            String klt = "101";//klt=101:日;102:周;103:月;104:3月;105:6月;106:12月
            String dateType = "1";//1：一天;7:周;30:月;
            kline(cookie, fundTrade.getZqdm(), days, klt, dbFieldLastNetMin, dbFieldLastNetMax);//沪深300
        }
    }

    /**
     * 查询-ETF-指数
     *
     * @param cookie            cookie
     * @param zhiShu            指数
     * @param count             数量
     * @param klt               K线周期类型
     * @param dbFieldLastNetMin
     * @param dbFieldLastNetMax
     */
    public static void kline(String cookie, String zhiShu, int count, String klt, String dbFieldLastNetMin, String dbFieldLastNetMax) {
        StringBuffer url = new StringBuffer();
        url.append("http://96.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery331093188916841208381602168987937");
        if (zhiShu.startsWith("5") || zhiShu.startsWith("6")) {
            url.append("&secid=" + "1." + zhiShu);
        } else {
            url.append("&secid=" + "0." + zhiShu);
        }

        url.append("&ut=fa5fd1943c7b386f172d6893dbfba10b");
        url.append("&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5%2Cf6");
        url.append("&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58%2Cf59%2Cf60%2Cf61");
        url.append("&klt=" + klt);
        url.append("&fqt=1");
        url.append("&end=20500101");
        url.append("&lmt=" + count);
        url.append("&_=1602168987942");

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("&StartDate=").append(startDate);

//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
        String rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), cookie);
        String rsJson = rs.substring(rs.indexOf("{"));
        rsJson = rsJson.replace(");", "");
//        System.out.println("szKline:" + rsJson);

        List<String> klineList = new ArrayList<String>();
        JSONObject szzzMonthJson = JSON.parseObject(rsJson);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
//        String name = szzzMonthDataJson.getString("name");
//        System.out.println("指数名称："+name);
        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        if (klines != null) {
            for (Object kline : klines) {
                String klineStr = (String) kline;
                klineList.add(klineStr);
            }
        }

        Map<String, Double> netRs = handlerMaxJz(klineList);
        Double minJz = netRs.get(keyRsMin);
        Double maxJz = netRs.get(keyRsMax);

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE `ol_stock_trade` ");
        sb.append("SET ");
        sb.append(" `" + dbFieldLastNetMin + "`=" + minJz + " ");
        sb.append(" ,`" + dbFieldLastNetMax + "`=" + maxJz + " ");
        sb.append(" WHERE `FD_CODE`='" + zhiShu + "'" + " AND TYPE = '证券买入'" + ";");
        System.out.println(sb);
    }

    /**
     * 计算最大净值、最小净值
     *
     * @return
     */
    private static Map<String, Double> handlerMaxJz(List<String> klineList) {
        Map<String, Double> rs = new HashMap<String, Double>();
        Double rsMax = 0.0;
        Double rsMin = 0.0;
        Double lastDwjz = 0.0;
        Double rsNewestDayNet = 0.0;
        Double rsOldestDayNet = 0.0;
        int curTempInt = 0;
        for (String klineStr : klineList) {
            //  日期，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率
            //"2020-09-30,3389.74,3218.05,3425.63,3202.34,4906229054,6193724911616.00,6.58,-5.23,-177.63,13.40"
            String[] klineArray = klineStr.split(",");
            String shouPan = klineArray[2];
            String zhangDie = klineArray[8];
            String chengJiaoE = klineArray[6];
            String curDate = klineArray[0];
//            System.out.print("日期:" + curDate + ",");
//            System.out.print("收盘:" + shouPan + ",");
//            System.out.print("涨跌幅:" + zhangDie + ",\t");
//            System.out.print("开盘:" + klineArray[1] + ",\t");
//            System.out.print("最高:" + klineArray[3] + ",");
//            System.out.print("最低:" + klineArray[4] + ",");
//            System.out.print("成交量:\t" + klineArray[5] + ",\t\t");
//            System.out.print("成交额:\t" + klineArray[6] + ",\t\t");
//            System.out.print("振幅:" + klineArray[7] + ",");
//            System.out.print("涨跌额:" + klineArray[9] + ",");
//            System.out.print("换手率:" + klineArray[10] + ",");
//            System.out.println();

//                    System.out.println(JSON.toJSONString(lsjzDataLsjz));
//            String dwJz = lsjzDataLsjz.getDWJZ();//当晚净值
            String dwJz = shouPan;//累计净值

            if (StringUtils.isBlank(dwJz)) {
                dwJz = "0";
            }
            String fsrq = curDate;
//            System.out.println("fsrq:" + fsrq + ",dwjzLong:" + dwJz);
            Double dwjzLong = Double.valueOf(dwJz);
            if (curTempInt == 0) {//最新一天的净值
                rsNewestDayNet = dwjzLong;
            }
            curTempInt++;
            if (dwjzLong > rsMax) {
                rsMax = dwjzLong;
            }
            if (dwjzLong < rsMin || rsMin == 0.0) {
                rsMin = dwjzLong;
            }
        }
        rs.put(keyRsMax, rsMax);
        rs.put(keyRsMin, rsMin);
        return rs;
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
    private static void showDbRedem(String cookie,String validatekey, String startDate, String endDate, String busType) {
        TradeStockDao tradeService = new TradeStockDaoImpl();
        String fundCode = "";
        List<StockTrade> stockTradeList = tradeService.findMyStockTrade(cookie, startDate, endDate, validatekey);
        for (StockTrade stockTrade : stockTradeList) {
            String type = "证券卖出";
            if (stockTrade.getYwsm().equals(type)) {
                BigDecimal serverChargeRedem = new BigDecimal(stockTrade.getSxf());//赎回手续费=佣金+交易规费
                BigDecimal enrnAmtSubServerCharge = new BigDecimal(stockTrade.getCjje()).subtract(serverChargeRedem);

                //发生日期时间格式化
                Date tradeTimeDate = null;
                try {
                    tradeTimeDate = new SimpleDateFormat("yyyyMMdd hhmmss").parse(stockTrade.getFsrq() + " " + stockTrade.getFssj());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String tradeTimeDateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(tradeTimeDate);

                StringBuffer sb = new StringBuffer();
                sb.append("UPDATE `ol_stock_trade` " +
//                System.out.println("UPDATE `ol_fund_trade` " +
                        "SET `TYPE`='证券买入(卖出)'");
                if (stockTrade.getCjjg() != null) {
                    sb.append(",`NET_REDEM`=" + stockTrade.getCjjg() + " ");
                }
                if (stockTrade.getCjsl() != null) {
                    sb.append(",`REDEM_SHARE`=" + stockTrade.getCjsl() + " ");
                }
                sb.append(",`REDEM_TIME`='" + tradeTimeDateStr + "' " +
                        ",`REDEM_AMT`=" + stockTrade.getCjje() + " " +
                        ",`SERVER_CHARGE_REDEM`=" + serverChargeRedem + "" +
                        ",`REDEM_STATUS`=1" +
                        ",`EARN_AMT`=ROUND((" + enrnAmtSubServerCharge + "-`CONFIRM_AMT`-`SERVER_CHARGE`) ,2)" +
                        "WHERE  `FD_CODE` = '" + stockTrade.getZqdm() + "' "
                        + "AND (`TYPE` = '证券买入' OR `TYPE` = '证券买入(卖出中)')"
                        + "AND `CONFIRM_SHARE` = '" + stockTrade.getCjsl() + "' " +
                        " LIMIT 1; ");
                System.out.println(sb.toString());
            }
        }
    }


}
