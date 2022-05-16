package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import ttjj.dao.FuPanDao;
import ttjj.dao.FupanPositionDao;
import ttjj.db.AssetPositionDb;
import ttjj.db.Fupan;
import ttjj.db.StockTradeDb;
import ttjj.dto.Asset;
import ttjj.dto.AssetPosition;
import ttjj.dto.Kline;
import ttjj.rank.history.KlineDemo;
import ttjj.rank.history.StockTradeDemo;
import ttjj.service.FundFlowService;
import ttjj.service.KlineService;
import utils.Content;
import utils.ContentCookie;
import utils.DateUtil;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static ttjj.dao.FupanPositionDao.insertDbFupanPosition;
import static ttjj.dao.FupanPositionDao.listMyPositionByDate;
import static utils.Content.*;

/**
 * 上证指数
 *
 * @author chenzhilong
 * @date 2020/10/7
 */
public class FupanDemo {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);//        String date = "2021-11-01";

        String klt = KLT_101;//klt=101:日;102:周;103:月;104:3月;105:6月;106:12月
        String dateType = Content.DAYS_1;//1：一天;7:周;30:月;
        insertOrUpdate(date, klt, dateType);//保存复盘和仓位

        KlineDemo.main(null);

//        checkFundFlowByMyPosition(date);//检查资金流向-我的仓位

//        listMyPosition(date, KLT_101);//查询我的仓位 KLT_102;//检查周期类型

    }

    /**
     * 查询资金流向，判断买卖信号
     *
     * @param date
     */
    private static void checkFundFlowByMyPosition(String date) {
        List<AssetPositionDb> rs = listMyPositionByDate(date);
        for (AssetPositionDb myPosition : rs) {
            String zqdm = myPosition.getZqdm();
//            if (zqdm.equals("754212")) {
//                System.out.println("特定zqdm：" + zqdm);
//            }
            FundFlowService.fundFlowHandler(zqdm, null);
            //净值
            System.out.println(handlerAvgLine("5日价格", KlineService.findNetMinMaxAvg(zqdm, MA_5, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
            System.out.println(handlerAvgLine("10日价格", KlineService.findNetMinMaxAvg(zqdm, MA_10, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
            System.out.println(handlerAvgLine("20日价格", KlineService.findNetMinMaxAvg(zqdm, MA_20, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
            System.out.println(handlerAvgLine("60日价格", KlineService.findNetMinMaxAvg(zqdm, MA_60, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
            System.out.println();
        }
    }

    /**
     * 获取均线数据
     *
     * @param strHead
     * @param netMap
     * @return
     */
    public static String handlerAvgLine(String strHead, Map<String, BigDecimal> netMap) {
        BigDecimal curPrice = netMap.get(keyRsNetClose);
        BigDecimal minPrice = netMap.get(keyRsMin);
        BigDecimal maxPrice = netMap.get(keyRsMax);
        StringBuffer sb = new StringBuffer();
        if (curPrice != null && minPrice != null && maxPrice != null && maxPrice.compareTo(new BigDecimal("0")) != 0) {
            BigDecimal curPriceArea = curPrice.subtract(minPrice).divide(maxPrice.subtract(minPrice), 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
            sb.append(strHead).append("区间：").append("\t").append(curPriceArea).append("%").append(",");
        }
        sb.append("\t").append(strHead).append("：").append("\t").append(netMap.get(keyRsNetCloseAvg));
        sb.append("\t").append(",最低：").append("\t").append(minPrice);
        sb.append("\t").append(",最高：").append("\t").append(maxPrice);
        sb.append("\t").append(",当前价：").append(curPrice);

        return sb.toString();
    }

    /**
     * 查询我的仓位
     *
     * @param date
     */
    private static void listMyPosition(String date, String klt) {
        List<AssetPositionDb> rs = listMyPositionByDate(date);
        for (AssetPositionDb myPosition : rs) {
//            System.out.println(JSON.toJSONString(myPosition));
            BigDecimal zxsz = myPosition.getZxsz();
            System.out.println(myPosition.getZqmc() + ",数量：" + myPosition.getZqsl() + ",最新市值：" + zxsz);
            //如果最新市值为0，不检查
            if (zxsz.compareTo(new BigDecimal("0")) == 0) {
                continue;
            }
            String zqdm = myPosition.getZqdm();
            BigDecimal openPrice = new BigDecimal("0");
            List<Kline> klines = KlineService.kline(zqdm, 1, Content.KLT_101, true, date, date, KLINE_TYPE_STOCK);//查询今日价格k线
            if (klines != null && klines.size() > 0) {
                Kline kline = klines.get(0);
                openPrice = kline.getOpenAmt();
            }
            BigDecimal zxjg = myPosition.getZxjg();
            switch (klt) {
                case "5":
                    System.out.println("\t检查类型：5分钟；");
                    break;
                case "15":
                    System.out.println("\t检查类型：15分钟；");
                    break;
                case "30":
                    System.out.println("\t检查类型：30分钟；");
                    break;
                case "60":
                    System.out.println("\t检查类型：60分钟；");
                    break;
                case "101":
                    System.out.println("\t检查类型：日线；");
                    break;
                case "102":
                    System.out.println("\t检查类型：周线；");
                    break;
                default:
                    System.out.println("未知均线类型");
            }
            Map<String, BigDecimal> netMap5 = KlineService.findNetMinMaxAvg(zqdm, MA_5, klt, false, "", date, KLINE_TYPE_STOCK);
            BigDecimal maPrice5 = netMap5.get(keyRsNetCloseAvg);
            System.out.print("\t检查周期：5周期均线：" + maPrice5);
            if (zxjg.compareTo(maPrice5) >= 0) {
                System.out.print(",最新价格在周期均线价格之上：" + zxjg + ">=" + maPrice5);
                if (openPrice.compareTo(maPrice5) < 0) {//开盘价检查
                    System.out.print(",开盘价：" + openPrice + ",突破均线，建议买入");
                }
            } else {
//                System.out.print(",最新价格低于周期均线价格：" + zxjg + "<" + maPrice5);
            }
            System.out.println();
            Map<String, BigDecimal> netMap10 = KlineService.findNetMinMaxAvg(zqdm, MA_10, klt, false, "", date, KLINE_TYPE_STOCK);
            BigDecimal maPrice10 = netMap10.get(keyRsNetCloseAvg);
            System.out.print("\t检查周期：10周期均线：" + maPrice10);
            if (zxjg.compareTo(maPrice10) >= 0) {
                System.out.print(",最新价格在周期均线价格之上：" + zxjg + ">=" + maPrice10);
                if (openPrice.compareTo(maPrice10) < 0) {//开盘价检查
                    System.out.print(",开盘价：" + openPrice + ",突破均线，建议买入");
                }
            } else {
//                System.out.print(",最新价格低于周期均线价格：" + zxjg + "<" + maPrice10);
            }
            System.out.println();
            Map<String, BigDecimal> netMap20 = KlineService.findNetMinMaxAvg(zqdm, MA_20, klt, false, "", date, KLINE_TYPE_STOCK);
            BigDecimal maPrice20 = netMap20.get(keyRsNetCloseAvg);
            System.out.print("\t检查周期：20周期均线：" + maPrice20);
            if (zxjg.compareTo(maPrice20) >= 0) {
                System.out.print(",最新价格在周期均线价格之上：" + zxjg + ">=" + maPrice20);
                if (openPrice.compareTo(maPrice20) < 0) {//开盘价检查
                    System.out.print(",开盘价：" + openPrice + ",突破均线，建议买入");
                }
            } else {
//                System.out.print(",最新价格低于周期均线价格：" + zxjg + "<" + maPrice20);
            }
            System.out.println();
            Map<String, BigDecimal> netMap30 = KlineService.findNetMinMaxAvg(zqdm, MA_30, klt, false, "", date, KLINE_TYPE_STOCK);
            BigDecimal maPrice30 = netMap30.get(keyRsNetCloseAvg);
            System.out.print("\t检查周期：30周期均线：" + maPrice30);
            if (zxjg.compareTo(maPrice30) >= 0) {
                System.out.print(",最新价格在周期均线价格之上：" + zxjg + ">=" + maPrice30);
                if (openPrice.compareTo(maPrice30) < 0) {//开盘价检查
                    System.out.print(",开盘价：" + openPrice + ",突破均线，建议买入");
                }
            } else {
//                System.out.print(",最新价格低于周期均线价格：" + zxjg + "<" + maPrice30);
            }
            System.out.println();
            Map<String, BigDecimal> netMap60 = KlineService.findNetMinMaxAvg(zqdm, MA_60, klt, false, "", date, KLINE_TYPE_STOCK);
            BigDecimal maPrice60 = netMap60.get(keyRsNetCloseAvg);
            System.out.print("\t检查周期：60周期均线：" + maPrice60);
            if (zxjg.compareTo(maPrice60) >= 0) {
                System.out.print(",最新价格在周期均线价格之上：" + zxjg + ">=" + maPrice60);
                if (openPrice.compareTo(maPrice60) < 0) {//开盘价检查
                    System.out.print(",开盘价：" + openPrice + ",突破均线，建议买入");
                }
            } else {
//                System.out.print(",最新价格低于周期均线价格：" + zxjg + "<" + maPrice60);
            }
            System.out.println();
            Map<String, BigDecimal> netMap120 = KlineService.findNetMinMaxAvg(zqdm, MA_120, klt, false, "", date, KLINE_TYPE_STOCK);
            BigDecimal maPrice120 = netMap120.get(keyRsNetCloseAvg);
            System.out.print("\t检查周期：120周期均线：" + maPrice120);
            if (zxjg.compareTo(maPrice120) >= 0) {
                System.out.print(",最新价格在周期均线价格之上：" + zxjg + ">=" + maPrice120);
                if (openPrice.compareTo(maPrice120) < 0) {//开盘价检查
                    System.out.print(",开盘价：" + openPrice + ",突破均线，建议买入");
                }
            } else {
//                System.out.print(",最新价格低于周期均线价格：" + zxjg + "<" + maPrice120);
            }
            System.out.println();
            Map<String, BigDecimal> netMap250 = KlineService.findNetMinMaxAvg(zqdm, MA_250, klt, false, "", date, KLINE_TYPE_STOCK);
            BigDecimal maPrice250 = netMap250.get(keyRsNetCloseAvg);
            System.out.print("\t检查周期：250周期均线：" + maPrice250);
            if (zxjg.compareTo(maPrice250) >= 0) {
                System.out.print(",最新价格在周期均线价格之上：" + zxjg + ">=" + maPrice250);
                if (openPrice.compareTo(maPrice250) < 0) {//开盘价检查
                    System.out.print(",开盘价：" + openPrice + ",突破均线，建议买入");
                }
            } else {
//                System.out.print(",最新价格低于周期均线价格：" + zxjg + "<" + maPrice250);
            }
            System.out.println();
            System.out.println();
        }
    }

    /**
     * @param date
     * @param klt
     * @param dateType
     */
    private static void insertOrUpdate(String date, String klt, String dateType) {
        boolean updateDaPanKline = true;//显示-大盘指数
//        boolean updateDaPanKline = false;//不显示-大盘指数
        boolean updateMyStock = true;//显示-我的股票
//        boolean updateMyStock = false;//不显示-我的股票
//        boolean updateMyStockAssetPosition = true;//更新-我的股票-资产持仓 2022-02-16 字段太长，不再插入
        boolean updateMyStockAssetPosition = false;//不更新-我的股票-资产持仓
        boolean findDbMyPositionByDate = true;//从数据库中根据日期查询我的持仓盈亏
//        boolean findDbMyPositionByDate = false;//从数据库中根据日期查询我的持仓盈亏
        boolean updateDbFupanPositionByDate = true;//更新我的持仓盈亏明细
//        boolean updateDbFupanPositionByDate = false;//更新我的持仓盈亏明细
//
//        boolean updateMyTtjj = true;//显示-我的基金
        boolean updateMyTtjj = false;//不显示-我的基金

        if (updateDaPanKline) {
            String cookie = "";
            //k线
            int count = 1;

            FuPanDao.updateDb(findFupanPointByKline(cookie, HS_300_000300, count, klt, dateType, date));//沪深300
            FuPanDao.updateDb(findFupanPointByKline(cookie, CYB_50_399673, count, klt, dateType, date));//创业板50
            FuPanDao.updateDb(findFupanPointByKline(cookie, ZZ_500_000905, count, klt, dateType, date));//中证500
            FuPanDao.updateDb(findFupanPointByKline(cookie, SH_50_000016, count, klt, dateType, date));//上证50
            FuPanDao.updateDb(findFupanPointByKline(cookie, SHANG_HAI, count, klt, dateType, date));//上证
            FuPanDao.updateDb(findFupanPointByKline(cookie, SHEN_ZHEN, count, klt, dateType, date));//深证成指
            FuPanDao.updateDb(findFupanPointByKline(cookie, CYB, count, klt, dateType, date));//创业板
            FuPanDao.updateDb(findFupanPointByKline(cookie, KCB_50, count, klt, dateType, date));
            System.out.println();

//            for (int i = 0; i < 100; i++) {
//                date = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -i);
//                updateDb(findFupanPointByKline(cookie, KCB_50, count, klt, dateType, date));
//            }

            //k线-日线-行业指数
//            updateDb(findFupanPointByKline(cookie, BIZ_QUANSHANG, count, klt, dateType, date));

        }

        if (updateMyStock) {
            //显示股票每日收益
            Fupan FupanMyStock = queryAssetByDfcfStock(ContentCookie.COOKIE_DFCF, dateType);
            FuPanDao.updateDb(FupanMyStock);
        }

        if (updateMyStockAssetPosition) {
            //更新-我的股票-资产持仓
            Fupan fupanMyStockAssetPosition = queryMyStockAssetPosition(ContentCookie.COOKIE_DFCF, dateType, date);

            FuPanDao.updateMyStockAssetPosition(fupanMyStockAssetPosition);
        }

        List<AssetPositionDb> assetPositionList = new ArrayList<>();
        //  从数据库中根据日期查询我的持仓盈亏
        if (findDbMyPositionByDate) {
            String findDate = date;//查询日期
            String period = "1";
            assetPositionList = findDbMyPositionByDate(findDate, period, dateType);
        }

        //  更新我的持仓盈亏明细
        if (updateDbFupanPositionByDate) {
            for (AssetPositionDb assetPosition : assetPositionList) {
                //插入-我的持仓明细
                //  不插入XX发债，7XXXXX开头的
                if (checkIsNewBond(assetPosition)) {
                    System.out.println("新发债券不插入我的持仓：" + assetPosition.getZqmc());
                    continue;
                }
                insertDbFupanPosition(assetPosition);//插入-我的持仓明细

                //更新当日k线参数
                List<Kline> klineList = KlineService.kline(assetPosition.getZqdm(), 1, klt, true, date, date, KLINE_TYPE_STOCK);
                if (klineList != null && klineList.size() > 0) {
                    AssetPositionDb entity = new AssetPositionDb();
                    entity.setZqdm(assetPosition.getZqdm());
                    entity.setDate(assetPosition.getDate());

                    Kline kline = klineList.get(0);
                    entity.setOpenAmt(kline.getOpenAmt());
                    entity.setCloseAmt(kline.getCloseAmt());
                    entity.setMaxAmt(kline.getMaxAmt());
                    entity.setMinAmt(kline.getMinAmt());
                    entity.setCjl(kline.getCjl());
                    entity.setCje(kline.getCje());
                    entity.setZhenFu(kline.getZhenFu());
                    entity.setZhangDieFu(kline.getZhangDieFu());
                    entity.setZhangDieE(kline.getZhangDieE());
                    entity.setHuanShouLv(kline.getHuanShouLv());

                    //计算昨日收盘价：今日收盘价-今日涨跌额
                    BigDecimal closeLastAmt = entity.getCloseAmt().subtract(entity.getZhangDieE());
                    entity.setCloseLastAmt(closeLastAmt);//上一个交易时期的收盘价
//                    System.out.println(assetPosition.getZqmc() + "-昨日收盘价：" + entity.getCloseLastAmt());

                    String zqdm = entity.getZqdm();
                    //查询净值
                    StockTradeDb entityStockTradeDbLastNet = StockTradeDemo.kline(zqdm, 1, klt, "LAST_NET", "LAST_NET", "LAST_NET", "LAST_NET");
                    entity.setLAST_NET(entityStockTradeDbLastNet.getLAST_NET());
                    entityStockTradeDbLastNet = StockTradeDemo.kline(zqdm, 1, klt, "NET_MIN_1", "NET_MAX_1", "NET_MIN_CLOS_1", "NET_MAX_CLOS_1");
                    entity.setNET_MIN_1(entityStockTradeDbLastNet.getNET_MIN_1());
                    entity.setNET_MIN_CLOS_1(entityStockTradeDbLastNet.getNET_MIN_CLOS_1());
                    entity.setNET_MAX_1(entityStockTradeDbLastNet.getNET_MAX_1());
                    entity.setNET_MAX_CLOS_1(entityStockTradeDbLastNet.getNET_MAX_CLOS_1());
                    entityStockTradeDbLastNet = StockTradeDemo.kline(zqdm, 5, klt, "NET_MIN_7", "NET_MAX_7", "NET_MIN_CLOS_7", "NET_MAX_CLOS_7");
                    entity.setNET_MIN_7(entityStockTradeDbLastNet.getNET_MIN_7());
                    entity.setNET_MIN_CLOS_7(entityStockTradeDbLastNet.getNET_MIN_CLOS_7());
                    entity.setNET_MAX_7(entityStockTradeDbLastNet.getNET_MAX_7());
                    entity.setNET_MAX_CLOS_7(entityStockTradeDbLastNet.getNET_MAX_CLOS_7());
                    entityStockTradeDbLastNet = StockTradeDemo.kline(zqdm, 10, klt, "NET_MIN_14", "NET_MAX_14", "NET_MIN_CLOS_14", "NET_MAX_CLOS_14");
                    entity.setNET_MIN_14(entityStockTradeDbLastNet.getNET_MIN_14());
                    entity.setNET_MIN_CLOS_14(entityStockTradeDbLastNet.getNET_MIN_CLOS_14());
                    entity.setNET_MAX_14(entityStockTradeDbLastNet.getNET_MAX_14());
                    entity.setNET_MAX_CLOS_14(entityStockTradeDbLastNet.getNET_MAX_CLOS_14());
                    entityStockTradeDbLastNet = StockTradeDemo.kline(zqdm, 20, klt, "NET_MIN_30", "NET_MAX_30", "NET_MIN_CLOS_30", "NET_MAX_CLOS_30");
                    entity.setNET_MIN_30(entityStockTradeDbLastNet.getNET_MIN_30());
                    entity.setNET_MIN_CLOS_30(entityStockTradeDbLastNet.getNET_MIN_CLOS_30());
                    entity.setNET_MAX_30(entityStockTradeDbLastNet.getNET_MAX_30());
                    entity.setNET_MAX_CLOS_30(entityStockTradeDbLastNet.getNET_MAX_CLOS_30());
                    entityStockTradeDbLastNet = StockTradeDemo.kline(zqdm, 60, klt, "NET_MIN_60", "NET_MAX_60", "NET_MIN_CLOS_60", "NET_MAX_CLOS_60");
                    entity.setNET_MIN_60(entityStockTradeDbLastNet.getNET_MIN_60());
                    entity.setNET_MIN_CLOS_60(entityStockTradeDbLastNet.getNET_MIN_CLOS_60());
                    entity.setNET_MAX_60(entityStockTradeDbLastNet.getNET_MAX_60());
                    entity.setNET_MAX_CLOS_60(entityStockTradeDbLastNet.getNET_MAX_CLOS_60());
                    entityStockTradeDbLastNet = StockTradeDemo.kline(zqdm, 90, klt, "NET_MIN_90", "NET_MAX_90", "NET_MIN_CLOS_90", "NET_MAX_CLOS_90");
                    entity.setNET_MIN_90(entityStockTradeDbLastNet.getNET_MIN_90());
                    entity.setNET_MIN_CLOS_90(entityStockTradeDbLastNet.getNET_MIN_CLOS_90());
                    entity.setNET_MAX_90(entityStockTradeDbLastNet.getNET_MAX_90());
                    entity.setNET_MAX_CLOS_90(entityStockTradeDbLastNet.getNET_MAX_CLOS_90());
                    entityStockTradeDbLastNet = StockTradeDemo.kline(zqdm, 180, klt, "NET_MIN_180", "NET_MAX_180", "NET_MIN_CLOS_180", "NET_MAX_CLOS_180");
                    entity.setNET_MIN_180(entityStockTradeDbLastNet.getNET_MIN_180());
                    entity.setNET_MIN_CLOS_180(entityStockTradeDbLastNet.getNET_MIN_CLOS_180());
                    entity.setNET_MAX_180(entityStockTradeDbLastNet.getNET_MAX_180());
                    entity.setNET_MAX_CLOS_180(entityStockTradeDbLastNet.getNET_MAX_CLOS_180());
                    entityStockTradeDbLastNet = StockTradeDemo.kline(zqdm, 365, klt, "NET_MIN_360", "NET_MAX_360", "NET_MIN_CLOS_360", "NET_MAX_CLOS_360");
                    entity.setNET_MIN_360(entityStockTradeDbLastNet.getNET_MIN_360());
                    entity.setNET_MIN_CLOS_360(entityStockTradeDbLastNet.getNET_MIN_CLOS_360());
                    entity.setNET_MAX_360(entityStockTradeDbLastNet.getNET_MAX_360());
                    entity.setNET_MAX_CLOS_360(entityStockTradeDbLastNet.getNET_MAX_CLOS_360());


                    Map<String, BigDecimal> netMap5 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, klt, false, "", date, KLINE_TYPE_STOCK);
                    entity.setNET_MA_5(netMap5.get(Content.keyRsNetCloseAvg));
                    Map<String, BigDecimal> netMap10 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, klt, false, "", date, KLINE_TYPE_STOCK);
                    entity.setNET_MA_10(netMap10.get(Content.keyRsNetCloseAvg));
                    Map<String, BigDecimal> netMap20 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, klt, false, "", date, KLINE_TYPE_STOCK);
                    entity.setNET_MA_20(netMap20.get(Content.keyRsNetCloseAvg));
                    Map<String, BigDecimal> netMap30 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, klt, false, "", date, KLINE_TYPE_STOCK);
                    entity.setNET_MA_30(netMap30.get(Content.keyRsNetCloseAvg));
                    Map<String, BigDecimal> netMap60 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, klt, false, "", date, KLINE_TYPE_STOCK);
                    entity.setNET_MA_60(netMap60.get(Content.keyRsNetCloseAvg));
                    Map<String, BigDecimal> netMap120 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, klt, false, "", date, KLINE_TYPE_STOCK);
                    entity.setNET_MA_120(netMap120.get(Content.keyRsNetCloseAvg));
                    Map<String, BigDecimal> netMap250 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, klt, false, "", date, KLINE_TYPE_STOCK);
                    entity.setNET_MA_250(netMap250.get(Content.keyRsNetCloseAvg));

                    FupanPositionDao.updateDbFupan(entity);
                }
            }
        }

//        if (showMyTtjj) {
//            //显示每日收益-天天基金
//            String cookieTtjj = "https://trade.1234567.com.cn/MyAssets/bankcardtb-banktbb=0; FundTradeLoginCard=0; FundTradeLoginTab=0; __guid=234620763.2234479903904567600.1595670543743.7263; st_si=75897173483057; st_asi=delete; FundTradeLoginUser=eSyBdwCDNI88w5CvIMRXefDBulaL560isdMdYBPJYPfeNkG97pJ1xeXBCIeV1ePMzYOoSkxz; fund_trade_cn=eYF4gJ3qrhs+PKIutFPCD4UKx77lRkQOrpJwYL50KMLTPYp14Z6DGcxkYFRmNqkLI5+3HlkbFa38aDi7kq+GXwizujwcKp7vd8MdBUuES/lP0vjwI5w=; fund_trade_name=eblm8ZTqFI07SdoAThR8onnxmD3Lq2pc/ykvY4m/JLdBYkaly3XIwfXfSsP6Xn9MVYWN/0uI; fund_trade_visitor=eNObSKvJ7IwWDFY0qeRnFpcVhjFLtsDLDkGlYwPhhyS5LkCkyOshjMXtlqX4J7LMXsxNzC6G; fund_trade_risk=ejg1/dcN6IxiQyIGYlR4e9OqTU2LKo9elEtmYeudX8j2MkNYSJX10xX1C+rJyldMnIsF6FRg; fund_trade_gps=2; VipLevel=0; TradeLoginToken=7617397110c94aaf9729d08f0ee68f70; UTOKEN=eYF4gJ3qrhs+PKIutFPCD4UKx77lRkQOrpJwYL50KMLTPYp14Z6DGcxkYFRmNqkLI5+3HpkPC6z4IzgzW81sXFuHmEmRpSYrHGMHOBNRTUZGNXNCtrQ=; LToken=9ecb06d8d1cd4d1db8157ea5794c5f48; fund_trade_trackid=qGufvhAXV1DDvDlRSiPVNEYjzjEIZUt69z6kHBc9je5OqdQCNjuVyIblV0B+zF5RUyXwUB0Sj3a7xwmf/4hgIQ==; b_p_log_key=LN4iAOH8if7VQBi4jiFlkfxvm2tLFX7g4WlP7SJxVwdatcYMQnsJkIci6HlT9k8dR9KbvRcmjBAYOrsSqxQqSGeAtEqb1+tnd1pl6U0ysdsd7bS98Qo=; ASP.NET_SessionId=pje40u0wqsmcip1dvc3a2dal; b_pl_bq=77bcd74f069146b390f0f9e7d47c7e46; monitor_count=14; st_pvi=34528644972697; st_sp=2020-03-21%2009%3A52%3A13; st_inirUrl=https%3A%2F%2Flogin.1234567.com.cn%2Flogin; st_sn=28; st_psi=20210331234529129-1190151312948-9502758665";
//            //天天基金-我的资产
//            queryAssetByTtjj(cookieTtjj);
//        }

        if (updateMyTtjj) {
            String amt = "";
            String amt_fund = "0";
            String amt_fund_last = "0";
            String earn_fund = "0";
            Fupan FupanMyFund = handlerFundByTtjj(amt, amt_fund, amt_fund_last, earn_fund, date, dateType);
            FuPanDao.updateDb(FupanMyFund);
        }

    }

    /**
     * 检查是否：XX发债，7XXXXX开头的
     *
     * @param assetPosition
     */
    private static boolean checkIsNewBond(AssetPositionDb assetPosition) {
        if (assetPosition == null) {
            return false;
        }
        String zqdm = assetPosition.getZqdm();
        String zqmc = assetPosition.getZqmc();
        if (zqdm.startsWith("7")) {
            return true;
        }
        if (zqmc.contains("发债")) {
            return true;
        }
        return false;
    }


    /**
     * 计算我的天天基金收益
     *
     * @param amt
     * @param amt_fund
     * @param amt_fund_last
     * @param date
     * @param dateType
     */
    private static Fupan handlerFundByTtjj(String amt, String amt_fund, String amt_fund_last, String earn_fund, String date, String dateType) {
        String dayProfitRt = new BigDecimal(earn_fund).divide(new BigDecimal(amt_fund_last), 6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).divide(new BigDecimal("1"), 4, BigDecimal.ROUND_HALF_UP).toString();//当日盈亏收益率
        System.out.println("UPDATE `fupan` SET `amt`='" + amt + "', `amt_fund`='" + amt_fund + "', `amt_fund_last`='" + amt_fund_last
                + "', `earn_fund`='" + earn_fund
                + "', `rt_zh`='" + dayProfitRt
                + "' WHERE (`CODE`='" + date + "') AND fupan.period='1'" + " AND fupan.TYPE=1;");

        Fupan fupanRs = new Fupan();
        //where
        fupanRs.setCode(date);
        fupanRs.setPeriod(dateType);
        fupanRs.setType("1");//实际
        //setValue
        fupanRs.setAmt(amt);
        fupanRs.setAmt_fund(amt_fund);
        fupanRs.setAmt_fund_last(amt_fund_last);
        fupanRs.setEarn_fund(earn_fund);
        fupanRs.setRt_zh(dayProfitRt);
        return fupanRs;
    }

    /**
     * 查询ttjj资产
     *
     * @param
     */
    private static void queryAssetByTtjj(String cookie) {
        String url = "https://trade.1234567.com.cn/MyAssets/do.aspx/GetMyAssertInfoNew?1617205529735";

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("moneyType=").append("RMB");

//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
        String rs = HttpUtil.sendPost(url, urlParam.toString(), cookie);
        System.out.println("queryAssetByTtjj:" + rs);

        String fundMktVal = "";//证券市值
        String fundAvl = "";//可用资金
        String totalAmt = "";//合计资产金额
        String dayProfit = "";//当日盈亏
        String dayProfitRt = "";//当日盈亏收益率

        JSONObject rsJson = JSON.parseObject(rs);
        JSONArray rsArray = rsJson.getJSONArray("ResultObj");
        for (int i = 0; i < rsArray.size(); i++) {
            JSONObject myStock = (JSONObject) (rsArray.get(i));
//            System.out.println("ResultObj:"+JSON.toJSONString(myStock));
            fundMktVal = myStock.getString("FundMktVal");//证券市值
            fundAvl = myStock.getString("FundAvl");//可用资金
            BigDecimal totalAmtBig = new BigDecimal(fundMktVal).add(new BigDecimal(fundAvl));
            totalAmt = totalAmtBig.toString();
            dayProfit = myStock.getString("DayProfit");
            dayProfitRt = new BigDecimal(dayProfit).divide(new BigDecimal(fundMktVal), 6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).divide(new BigDecimal("1"), 4, BigDecimal.ROUND_HALF_UP).toString();//当日盈亏收益率
        }

        String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println("UPDATE `fupan` SET `amt_dfcf`='" + totalAmt + "', `hold_st`='" + fundMktVal + "', `earn_st`='" + dayProfit + "', `rt_st`='" + dayProfitRt + "' WHERE (`CODE`='" + curDate + "') AND fupan.period='1'" + " AND fupan.TYPE=1;");


//        String rsDate = rsArray.getString("Data");
//        System.out.println(rsDate);
    }

    /**
     * 查询东方财富股票账户-显示插入数据库语句
     *
     * @param
     * @param dateType
     */
    private static Fupan queryAssetByDfcfStock(String cookie, String dateType) {
//        String url = "https://jywg.18.cn/Com/queryAssetAndPositionV1?validatekey=" + validatekey;
        long curTime = System.currentTimeMillis();
        String url = "https://jywg.18.cn/AccountAnalyze/Asset/GetHold?v=" + curTime;

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("moneyType=").append("RMB");

//        System.out.println("请求url:" + url + JSON.toJSONString(urlParam));
        String rs = HttpUtil.sendPost(url, urlParam.toString(), cookie);
//        System.out.println("queryAssetByDfcfStock:" + rs);


        //      "FundBal": "6597.17",   可取资金        6597.17
        //      "FundFrz": "25506.39",  冻结资金        25506.39
        //      "MoneyType": "0",
        //      "MaxDraw": null,

        String fundMktVal = "";//证券市值
        String fundAvl = "";//可用资金
        String totalAmt = "";//合计资产金额
        String dayProfit = "";//当日盈亏
        String dayProfitRt = "";//当日盈亏收益率
        JSONObject rsJson = JSON.parseObject(rs);

        JSONArray rsArray = rsJson.getJSONArray("ResultObj");
        for (int i = 0; i < rsArray.size(); i++) {
            JSONObject myStock = (JSONObject) (rsArray.get(i));
//            System.out.println("ResultObj:"+JSON.toJSONString(myStock));
            fundMktVal = myStock.getString("FundMktVal");//总市值 "FundMktVal": "302225.10",
            fundAvl = myStock.getString("FundAvl");//可用资金   "FundAvl": "45506.39",
            BigDecimal fundAll = new BigDecimal(myStock.getString("FundAll"));//总资产   "FundAll": "398732.090",    BigDecimal totalAmtBig = new BigDecimal(fundMktVal).add(new BigDecimal(fundAvl));
            BigDecimal otcAsset = new BigDecimal(myStock.getString("OtcAsset"));//      "OtcAsset": "0.00", 场外资产
            totalAmt = fundAll.add(otcAsset).toString();//总资产 = 场内资产+场外资产
            dayProfit = myStock.getString("DayProfit");//      "DayProfit": "2037.00", 当日参考盈亏    2037.00
            if (fundMktVal == null || new BigDecimal(fundMktVal).compareTo(new BigDecimal("0")) == 0 || dayProfit == null) {
                dayProfitRt = "0";
            } else {
                dayProfitRt = new BigDecimal(dayProfit).divide(new BigDecimal(fundMktVal), 6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).divide(new BigDecimal("1"), 4, BigDecimal.ROUND_HALF_UP).toString();//当日盈亏收益率
            }
        }

        String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println("UPDATE `fupan` SET `amt_dfcf`='" + totalAmt + "', `hold_st`='" + fundMktVal + "', `earn_st`='" + dayProfit + "', `rt_st`='" + dayProfitRt + "' WHERE (`CODE`='" + curDate + "') AND fupan.period='1'" + " AND fupan.TYPE=1;");

        Fupan fupanRs = new Fupan();
        //where
        fupanRs.setCode(curDate);
        fupanRs.setPeriod(dateType);
        fupanRs.setType("1");//实际
        //setValue
        fupanRs.setAmt_dfcf(totalAmt);
        fupanRs.setHold_st(fundMktVal);
        fupanRs.setEarn_st(dayProfit);
        fupanRs.setRt_st(dayProfitRt);
        return fupanRs;

    }

    /**
     * 查询-我的股票-资产持仓
     *
     * @param cookie
     * @param dateType
     * @param date
     * @return
     */
    private static Fupan queryMyStockAssetPosition(String cookie, String dateType, String date) {
        String url = "https://jywg.18.cn/Com/queryAssetAndPositionV1?validatekey=734d22f9-364d-4460-bac6-e6df18953822&moneyType=RMB";

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("moneyType=").append("RMB");

//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
        String assetPositionRs = HttpUtil.sendPost(url, urlParam.toString(), cookie);
        System.out.println("queryAssetPositionRs:" + assetPositionRs);

        JSONObject assetPositionRsJsonObject = JSON.parseObject(assetPositionRs);
        JSONArray assetPositionDataArray = JSON.parseArray(assetPositionRsJsonObject.getString("Data"));
        for (int i = 0; i < assetPositionDataArray.size(); i++) {
            Asset asset = JSON.parseObject(assetPositionDataArray.getString(i), Asset.class);
            List<AssetPosition> assetPositionList = asset.getPositions();
            List<AssetPosition> assetPositionListSortLjyk = assetPositionList.stream().filter(e -> e != null).sorted(Comparator.comparing(AssetPosition::getLjyk, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            List<AssetPosition> assetPositionListSortDryk = assetPositionList.stream().filter(e -> e != null).sorted(Comparator.comparing(AssetPosition::getDryk, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            List<AssetPosition> assetPositionListSortDrykbl = assetPositionList.stream().filter(e -> e != null).sorted(Comparator.comparing(AssetPosition::getDrykbl, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            System.out.println("排序-当日盈亏比例：");
            for (AssetPosition assetPosition : assetPositionListSortDrykbl) {
                BigDecimal drykbl = assetPosition.getDrykbl();
                BigDecimal dryk = assetPosition.getDryk();
                if (drykbl == null) {
                    drykbl = new BigDecimal(0);
                }
                if (dryk == null) {
                    dryk = new BigDecimal(0);
                }
                System.out.println(assetPosition.getZqmc() + "\t，当日盈亏:" + assetPosition.getDryk() + "\t，当日盈亏比例:" + drykbl.multiply(new BigDecimal("100")) + "%"
                        + "\t，累计盈亏:" + assetPosition.getLjyk() + "\t，累计盈亏比例:" + dryk.multiply(new BigDecimal("100")) + "%" + "\t，持款成本:" + assetPosition.getCkcb());
            }
            System.out.println("排序-当日盈亏：");
            for (AssetPosition assetPosition : assetPositionListSortDryk) {
                BigDecimal drykbl = assetPosition.getDrykbl();
                BigDecimal dryk = assetPosition.getDryk();
                if (drykbl == null) {
                    drykbl = new BigDecimal(0);
                }
                if (dryk == null) {
                    dryk = new BigDecimal(0);
                }
                System.out.println(assetPosition.getZqmc() + "\t，当日盈亏:" + assetPosition.getDryk() + "\t，当日盈亏比例:" + drykbl.multiply(new BigDecimal("100")) + "%"
                        + "\t，累计盈亏:" + assetPosition.getLjyk() + "\t，累计盈亏比例:" + dryk.multiply(new BigDecimal("100")) + "%" + "\t，持款成本:" + assetPosition.getCkcb());
            }
            System.out.println("排序-累计盈亏：");
            for (AssetPosition assetPosition : assetPositionListSortLjyk) {
                BigDecimal drykbl = assetPosition.getDrykbl();
                BigDecimal dryk = assetPosition.getDryk();
                if (drykbl == null) {
                    drykbl = new BigDecimal(0);
                }
                if (dryk == null) {
                    dryk = new BigDecimal(0);
                }
                System.out.println(assetPosition.getZqmc() + "\t，当日盈亏:" + assetPosition.getDryk() + "\t，当日盈亏比例:" + drykbl.multiply(new BigDecimal("100")) + "%"
                        + "\t，累计盈亏:" + assetPosition.getLjyk() + "\t，累计盈亏比例:" + dryk.multiply(new BigDecimal("100")) + "%" + "\t，持款成本:" + assetPosition.getCkcb());
            }
        }

        Fupan fupanRs = new Fupan();
        //where
        fupanRs.setCode(date);
        fupanRs.setPeriod(dateType);
        fupanRs.setType("1");//实际
        //setValue
        fupanRs.setAssetPosition(assetPositionRs);
        return fupanRs;

    }

    private static String queryMyStockAssetPositionByDfcf(String cookie, String dateType, String date) {
        String url = "https://jywg.18.cn/Com/queryAssetAndPositionV1?validatekey=734d22f9-364d-4460-bac6-e6df18953822&moneyType=RMB";

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("moneyType=").append("RMB");

//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
        String assetPositionRs = HttpUtil.sendPost(url, urlParam.toString(), cookie);
        System.out.println("queryAssetPositionRs:" + assetPositionRs);

        return assetPositionRs;

    }

    /**
     * 从数据库中根据日期查询我的持仓盈亏
     *
     * @param date
     * @param period
     * @param dateType
     * @return
     */
    private static List<AssetPositionDb> findDbMyPositionByDate(String date, String period, String dateType) {
//        Fupan condition = new Fupan();
//        condition.setCode(date);
//        condition.setPeriod(period);
//        Fupan fupanDb = FuPanDao.findDbByDate(condition);
        String assetPositionRs = queryMyStockAssetPositionByDfcf(ContentCookie.COOKIE_DFCF, dateType, date);
        System.out.println("findDbMyPositionByDate:" + assetPositionRs);

        JSONObject assetPositionRsJsonObject = JSON.parseObject(assetPositionRs);
        JSONArray assetPositionDataArray = JSON.parseArray(assetPositionRsJsonObject.getString("Data"));
        List<AssetPosition> assetPositionListSortDrykbl = new ArrayList<>();
        List<AssetPosition> assetPositionListSortLjyk = new ArrayList<>();
        List<AssetPosition> assetPositionListSortDryk = new ArrayList<>();
        for (int i = 0; i < assetPositionDataArray.size(); i++) {
            Asset asset = JSON.parseObject(assetPositionDataArray.getString(i), Asset.class);
            List<AssetPosition> assetPositionList = asset.getPositions();
            assetPositionListSortLjyk = assetPositionList.stream().filter(e -> e != null).sorted(Comparator.comparing(AssetPosition::getLjyk, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            assetPositionListSortDryk = assetPositionList.stream().filter(e -> e != null).sorted(Comparator.comparing(AssetPosition::getDryk, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            assetPositionListSortDrykbl = assetPositionList.stream().filter(e -> e != null).sorted(Comparator.comparing(AssetPosition::getDrykbl, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            List<AssetPosition> assetPositionListSortDrykblDesc = assetPositionList.stream().filter(e -> e != null).sorted(Comparator.comparing(AssetPosition::getDrykbl, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());

            System.out.println("排序-当日盈亏比例：");
            for (AssetPosition assetPosition : assetPositionListSortDrykbl) {
                BigDecimal drykbl = assetPosition.getDrykbl();
                BigDecimal dryk = assetPosition.getDryk();
                BigDecimal ykbl = assetPosition.getYkbl();
                if (drykbl == null) {
                    drykbl = new BigDecimal(0);
                }
                if (dryk == null) {
                    dryk = new BigDecimal(0);
                }
                if (ykbl == null) {
                    ykbl = new BigDecimal(0);
                }
                System.out.print(assetPosition.getZqmc() + ":" + drykbl.multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP) + "%;");
            }
            System.out.println();
            for (AssetPosition assetPosition : assetPositionListSortDrykblDesc) {
                BigDecimal drykbl = assetPosition.getDrykbl();
                BigDecimal dryk = assetPosition.getDryk();
                BigDecimal ykbl = assetPosition.getYkbl();
                if (drykbl == null) {
                    drykbl = new BigDecimal(0);
                }
                if (dryk == null) {
                    dryk = new BigDecimal(0);
                }
                if (ykbl == null) {
                    ykbl = new BigDecimal(0);
                }
                System.out.print(assetPosition.getZqmc() + ":" + drykbl.multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP) + "%;");
            }
            System.out.println();
            for (AssetPosition assetPosition : assetPositionListSortDrykbl) {
                BigDecimal drykbl = assetPosition.getDrykbl();
                BigDecimal dryk = assetPosition.getDryk();
                BigDecimal ykbl = assetPosition.getYkbl();
                if (drykbl == null) {
                    drykbl = new BigDecimal(0);
                }
                if (dryk == null) {
                    dryk = new BigDecimal(0);
                }
                if (ykbl == null) {
                    ykbl = new BigDecimal(0);
                }
//                System.out.println(assetPosition.getZqmc() + "\t，当日盈亏:" + dryk + "\t，当日盈亏比例:" + drykbl.multiply(new BigDecimal("100")) + "%"
//                        + "\t，累计盈亏:" + assetPosition.getLjyk() + "\t，累计盈亏比例:" + ykbl.multiply(new BigDecimal("100")) + "%" + "\t，持款成本:" + assetPosition.getCkcb());
            }
            System.out.println("排序-当日盈亏：");
            for (AssetPosition assetPosition : assetPositionListSortDryk) {
                BigDecimal drykbl = assetPosition.getDrykbl();
                BigDecimal dryk = assetPosition.getDryk();
                BigDecimal ykbl = assetPosition.getYkbl();
                if (drykbl == null) {
                    drykbl = new BigDecimal(0);
                }
                if (dryk == null) {
                    dryk = new BigDecimal(0);
                }
                if (ykbl == null) {
                    ykbl = new BigDecimal(0);
                }
//                System.out.println(assetPosition.getZqmc() + "\t，当日盈亏:" + dryk + "\t，当日盈亏比例:" + drykbl.multiply(new BigDecimal("100")) + "%"
//                        + "\t，累计盈亏:" + assetPosition.getLjyk() + "\t，累计盈亏比例:" + ykbl.multiply(new BigDecimal("100")) + "%" + "\t，持款成本:" + assetPosition.getCkcb());
            }
            System.out.println("排序-累计盈亏：");
            for (AssetPosition assetPosition : assetPositionListSortLjyk) {
                BigDecimal drykbl = assetPosition.getDrykbl();
                BigDecimal dryk = assetPosition.getDryk();
                if (drykbl == null) {
                    drykbl = new BigDecimal(0);
                }
                if (dryk == null) {
                    dryk = new BigDecimal(0);
                }
                BigDecimal ykbl = assetPosition.getYkbl();
                if (ykbl == null) {
                    ykbl = new BigDecimal(0);
                }
//                System.out.println(assetPosition.getZqmc() + "\t，当日盈亏:" + dryk + "\t，当日盈亏比例:" + drykbl.multiply(new BigDecimal("100")) + "%"
//                        + "\t，累计盈亏:" + assetPosition.getLjyk() + "\t，累计盈亏比例:" + ykbl.multiply(new BigDecimal("100")) + "%" + "\t，持款成本:" + assetPosition.getCkcb());
            }
        }

        List<AssetPositionDb> assetPositionDbListSortDrykbl = new ArrayList<>();
        for (AssetPosition assetPosition : assetPositionListSortDrykbl) {
            AssetPositionDb assetPositionDb = new AssetPositionDb();
            BeanUtils.copyProperties(assetPosition, assetPositionDb);
            assetPositionDb.setAssetPosition(JSON.toJSONString(assetPosition));
            assetPositionDb.setDate(date);
            assetPositionDb.setWeek(DateUtil.getWeekByYyyyMmDd(date, DateUtil.YYYY_MM_DD));
            assetPositionDb.setPeriod(period);
            assetPositionDbListSortDrykbl.add(assetPositionDb);
        }
        return assetPositionDbListSortDrykbl;

    }

    /**
     * @param count 最新n个
     */
    private static void szzsKlineMonth(int count) {
        String rsJson = "{\"rc\":0,\"rt\":17,\"svr\":2887135893,\"lt\":1,\"full\":0,\"data\":{\"code\":\"000001\",\"market\":1,\"name\":\"上证指数\",\"decimal\":2,\"dktotal\":7283,\"preKPrice\":0,\"klines\":[\"1990-12-31,96.05,127.61,127.61,95.79,1884,936000.00,0.00,0.00,0.00,0.00\",\"1991-01-31,127.61,129.97,135.19,127.61,67197,36371000.00,5.94,1.85,2.36,0.00\",\"1991-02-28,129.50,133.01,134.87,128.06,50982,30273000.00,5.24,2.34,3.04,0.00\",\"1991-03-29,132.53,120.19,132.53,120.11,24528,17253000.00,9.34,-9.64,-12.82,0.00\",\"1991-04-30,120.69,113.94,122.20,113.89,70671,36473000.00,6.91,-5.20,-6.25,0.00\",\"1991-05-31,113.04,114.83,114.83,104.96,76775,39471000.00,8.66,0.78,0.89,0.00\",\"1991-06-28,115.90,137.56,137.56,114.89,77828,46392000.00,19.74,19.79,22.73,0.00\",\"1991-07-31,136.64,143.80,143.80,131.87,160132,85962000.00,8.67,4.54,6.24,0.00\",\"1991-08-30,145.12,178.43,178.43,143.80,80814,44303000.00,24.08,24.08,34.63,0.00\",\"1991-09-30,180.08,180.92,191.18,178.43,286133,194104000.00,7.15,1.40,2.49,0.00\",\"1991-10-31,181.55,218.60,218.60,179.80,223443,149536000.00,21.45,20.83,37.68,0.00\",\"1991-11-29,220.71,259.60,259.85,218.60,96270,80215000.00,18.87,18.76,41.00,0.00\",\"1991-12-31,261.78,292.75,292.75,259.67,43666,46668000.00,12.74,12.77,33.15,0.00\",\"1992-01-31,293.74,313.24,313.24,292.76,16905,22691000.00,7.00,7.00,20.49,0.00\",\"1992-02-28,314.18,364.66,364.67,313.24,108287,165688000.00,16.42,16.42,51.42,0.00\",\"1992-03-31,365.15,381.24,382.06,364.66,161441,346806000.00,4.77,4.55,16.58,0.00\",\"1992-04-30,381.55,445.38,448.94,381.01,510611,804940000.00,17.82,16.82,64.14,0.00\",\"1992-05-29,454.86,1234.71,1429.01,439.96,1073313,2085232968.00,222.07,177.23,789.33,0.00\",\"1992-06-30,1175.69,1191.19,1249.58,1033.47,794087,2006645000.00,17.50,-3.52,-43.52,0.00\",\"1992-07-31,1199.54,1052.07,1215.24,961.61,1388425,2781203992.00,21.29,-11.68,-139.12,0.00\",\"1992-08-31,1034.70,823.27,1060.36,627.36,2040679,2862567000.00,41.16,-21.75,-228.80,0.01\",\"1992-09-30,826.96,702.32,832.84,595.42,2089985,2734136008.00,28.84,-14.69,-120.95,0.01\",\"1992-10-30,676.47,507.25,713.77,447.93,2046371,2284817984.00,37.85,-27.78,-195.07,0.01\",\"1992-11-30,471.18,724.60,752.32,386.85,3251862,2828500024.00,72.05,42.85,217.35,0.01\",\"1992-12-31,718.99,780.39,841.02,592.78,4761674,6216520992.00,34.26,7.70,55.79,0.01\",\"1993-01-29,784.13,1198.48,1198.48,777.16,7341654,11527160064.00,53.99,53.57,418.09,0.02\",\"1993-02-26,1242.35,1339.88,1558.95,1196.47,12275779,25997162176.00,30.24,11.80,141.40,0.03\",\"1993-03-31,1328.22,925.91,1339.10,913.74,7621896,14609000896.00,31.75,-30.90,-413.97,0.02\",\"1993-04-30,921.59,1358.78,1392.62,915.59,12998694,26098213904.00,51.52,46.75,432.87,0.04\",\"1993-05-31,1365.15,935.48,1380.50,930.64,6612356,12124998992.00,33.11,-31.15,-423.30,0.02\",\"1993-06-30,935.90,1007.05,1177.91,859.48,9065218,15475152216.00,34.04,7.65,71.57,0.02\",\"1993-07-30,1000.80,881.07,1009.89,777.73,6560435,8074706984.00,23.05,-12.51,-125.98,0.02\",\"1993-08-31,885.31,895.68,1042.47,823.00,14976937,18153104064.00,24.91,1.66,14.61,0.04\",\"1993-09-30,898.01,890.27,947.53,864.38,6194908,6923986080.00,9.28,-0.60,-5.41,0.02\",\"1993-10-29,895.69,814.82,934.40,774.54,15145361,23641632160.00,17.96,-8.47,-75.45,0.04\",\"1993-11-30,801.86,984.93,1011.76,793.32,26927669,37317982656.00,26.81,20.88,170.11,0.07\",\"1993-12-31,993.67,833.80,1044.85,750.46,28186649,37710016256.00,29.89,-15.34,-151.13,0.08\",\"1994-01-31,837.70,770.25,907.09,760.78,24245439,23596675040.00,17.55,-7.62,-63.55,0.07\",\"1994-02-28,770.25,770.98,818.57,749.63,15068988,15483997920.00,8.95,0.09,0.73,0.04\",\"1994-03-31,771.52,704.46,807.52,694.03,53540892,58077193984.00,14.72,-8.63,-66.52,0.15\",\"1994-04-29,704.93,592.56,705.41,536.34,39314387,31774157248.00,24.00,-15.88,-111.90,0.11\",\"1994-05-31,593.94,556.26,620.94,537.78,41944620,23194520064.00,14.03,-6.13,-36.30,0.11\",\"1994-06-30,554.08,469.29,556.90,458.74,21192207,10519165040.00,17.65,-15.63,-86.97,0.06\",\"1994-07-29,469.27,333.92,469.43,325.89,15428914,7637914976.00,30.59,-28.85,-135.37,0.04\",\"1994-08-31,394.87,785.33,787.22,377.97,207322046,148498494208.00,122.56,135.19,451.41,0.57\",\"1994-09-30,785.61,791.15,1052.94,785.56,148753124,151567028480.00,34.05,0.74,5.82,0.41\",\"1994-10-31,770.90,654.98,794.85,546.79,65577130,55882321088.00,31.35,-17.21,-136.17,0.18\",\"1994-11-30,667.66,683.59,743.22,648.68,32386735,29585950968.00,14.43,4.37,28.61,0.09\",\"1994-12-30,684.02,647.87,695.22,620.47,21634041,20175947185.00,10.93,-5.23,-35.72,0.06\",\"1995-01-27,637.72,562.59,657.45,547.38,7730020,5026333024.00,16.99,-13.16,-85.28,0.02\",\"1995-02-28,559.78,549.26,608.58,524.43,10386637,6308962040.00,14.96,-2.37,-13.33,0.03\",\"1995-03-31,548.91,646.92,648.04,545.72,29372607,23360125088.00,18.63,17.78,97.66,0.08\",\"1995-04-28,651.04,579.93,681.15,547.21,30383922,20810696096.00,20.70,-10.36,-66.99,0.08\",\"1995-05-31,576.69,700.51,926.41,564.86,61327317,52655821968.00,62.34,20.79,120.58,0.17\",\"1995-06-30,703.72,630.58,722.30,625.70,26658567,15829079072.00,13.79,-9.98,-69.93,0.07\",\"1995-07-31,623.14,695.55,723.68,610.33,66999478,31923493952.00,17.98,10.30,64.97,0.18\",\"1995-08-31,697.22,723.87,788.16,688.65,100044077,52840375552.00,14.31,4.07,28.32,0.27\",\"1995-09-29,726.05,722.43,792.54,699.10,59926284,31720007232.00,12.91,-0.20,-1.44,0.16\",\"1995-10-31,724.04,717.32,765.48,693.86,71208460,41171704960.00,9.91,-0.71,-5.11,0.19\",\"1995-11-30,716.46,641.14,736.91,632.03,33017496,19113567904.00,14.62,-10.62,-76.18,0.09\",\"1995-12-29,631.55,555.29,643.36,552.84,17671843,9978510976.00,14.12,-13.39,-85.85,0.05\",\"1996-01-31,550.26,537.35,561.36,512.83,25288649,10682254960.00,8.74,-3.23,-17.94,0.07\",\"1996-02-16,536.24,552.94,553.83,518.18,9370149,4637379968.00,6.63,2.90,15.59,0.03\",\"1996-03-29,583.38,556.39,606.51,549.71,26976358,14298638144.00,10.27,0.62,3.45,0.07\",\"1996-04-30,566.21,681.15,739.10,556.23,74838678,47108727712.00,32.87,22.42,124.76,0.20\",\"1996-05-31,715.98,643.65,724.97,630.52,80464094,47661894016.00,13.87,-5.51,-37.50,0.22\",\"1996-06-28,643.95,804.25,819.68,636.49,121945445,92866997248.00,28.46,24.95,160.60,0.33\",\"1996-07-31,800.90,822.47,894.72,753.75,140098866,115824302336.00,17.53,2.27,18.22,0.38\",\"1996-08-30,823.17,809.93,894.85,766.70,81200057,65660085888.00,15.58,-1.52,-12.54,0.22\",\"1996-09-27,812.94,875.52,875.52,752.68,88503305,78203350912.00,15.17,8.10,65.59,0.24\",\"1996-10-31,887.85,976.71,1038.81,852.83,184693255,148472227328.00,21.24,11.56,101.19,0.50\",\"1996-11-29,935.17,1032.94,1047.39,896.87,136847403,115159287552.00,15.41,5.76,56.23,0.37\",\"1996-12-31,1056.67,917.01,1258.68,855.84,217022018,208659213952.00,39.00,-11.22,-115.93,0.59\",\"1997-01-31,914.06,964.74,967.89,871.78,68919074,60120881408.00,10.48,5.20,47.73,0.19\",\"1997-02-28,969.53,1040.02,1040.17,870.18,77341550,68511630080.00,17.62,7.80,75.28,0.21\",\"1997-03-31,1044.61,1234.61,1234.95,1043.92,186113193,198599799808.00,18.37,18.71,194.59,0.51\",\"1997-04-30,1243.87,1393.74,1396.48,1222.55,211733187,253385569280.00,14.09,12.89,159.13,0.58\",\"1997-05-30,1398.14,1285.18,1510.17,1213.24,156260822,211894489088.00,21.30,-7.79,-108.56,0.43\",\"1997-06-27,1302.82,1250.27,1369.51,1154.97,97375851,133886091776.00,16.69,-2.72,-34.91,0.27\",\"1997-07-31,1255.90,1189.76,1261.57,1066.04,90841298,104874978048.00,15.64,-4.84,-60.51,0.25\",\"1997-08-29,1191.65,1221.06,1223.07,1116.14,92515438,85519833344.00,8.99,2.63,31.30,0.25\",\"1997-09-30,1224.17,1097.38,1264.48,1025.13,92347451,80037856256.00,19.60,-10.13,-123.68,0.25\",\"1997-10-31,1100.70,1180.39,1241.83,1087.01,70430576,69498271488.00,14.11,7.56,83.01,0.19\",\"1997-11-28,1181.16,1139.62,1213.22,1114.13,63470464,61244923520.00,8.39,-3.45,-40.77,0.17\",\"1997-12-31,1137.58,1194.10,1198.97,1119.20,66937679,71338314112.00,7.00,4.78,54.48,0.18\",\"1998-01-23,1200.94,1222.91,1250.00,1110.08,70820513,78995927040.00,11.72,2.41,28.81,0.19\",\"1998-02-27,1257.99,1206.53,1262.62,1189.51,60270625,60762337536.00,5.98,-1.34,-16.38,0.16\",\"1998-03-31,1196.74,1243.01,1252.24,1175.55,85973130,92073068544.00,6.36,3.02,36.48,0.23\",\"1998-04-30,1243.37,1343.44,1343.46,1240.05,223980843,190071163904.00,8.32,8.08,100.43,0.61\",\"1998-05-29,1353.05,1411.20,1411.34,1324.72,189109876,167860520960.00,6.45,5.04,67.76,0.52\",\"1998-06-30,1418.35,1339.20,1422.97,1323.52,142215364,140481351424.00,7.05,-5.10,-72.00,0.39\",\"1998-07-31,1363.26,1316.91,1367.14,1278.30,107207496,92758388992.00,6.63,-1.66,-22.29,0.29\",\"1998-08-31,1315.13,1150.22,1315.47,1043.02,115474144,80271511552.00,20.69,-12.66,-166.69,0.32\",\"1998-09-30,1142.50,1242.89,1264.39,1123.63,155017943,120391215104.00,12.24,8.06,92.67,0.42\",\"1998-10-30,1245.73,1217.31,1263.71,1191.55,117162909,103396637440.00,5.81,-2.06,-25.58,0.32\",\"1998-11-30,1217.33,1247.41,1300.15,1210.84,122414156,116245068032.00,7.34,2.47,30.10,0.33\",\"1998-12-31,1249.00,1146.70,1251.86,1125.55,72323148,56303432064.00,10.13,-8.07,-100.71,0.20\",\"1999-01-29,1144.88,1134.67,1174.42,1104.50,93117074,62575736320.00,6.10,-1.05,-12.03,0.25\",\"1999-02-09,1131.65,1090.08,1131.66,1064.17,27636005,17512153088.00,5.95,-3.93,-44.59,0.08\",\"1999-03-31,1092.14,1158.05,1179.65,1084.15,136720954,95195664000.00,8.76,6.24,67.97,0.37\",\"1999-04-30,1158.68,1120.92,1210.10,1087.79,162136649,109919927296.00,10.56,-3.21,-37.13,0.44\",\"1999-05-31,1120.90,1279.32,1303.48,1047.83,228524549,149832969984.00,22.81,14.13,158.40,0.62\",\"1999-06-30,1283.30,1689.42,1756.18,1259.00,640449531,514741625856.00,38.86,32.06,410.10,1.75\",\"1999-07-30,1678.83,1601.45,1678.83,1471.50,367116316,296444475392.00,12.27,-5.21,-87.97,1.00\",\"1999-08-31,1607.06,1627.11,1658.69,1546.83,237121827,208186233344.00,6.98,1.60,25.66,0.65\",\"1999-09-30,1627.82,1570.70,1695.64,1556.74,163297753,149847198976.00,8.54,-3.47,-56.41,0.45\",\"1999-10-29,1568.23,1504.56,1568.37,1452.70,101967457,66638261760.00,7.36,-4.21,-66.14,0.28\",\"1999-11-30,1501.73,1434.97,1506.99,1427.35,129447277,80928994816.00,5.29,-4.63,-69.59,0.35\",\"1999-12-30,1434.97,1366.58,1461.89,1341.05,110045787,77631267328.00,8.42,-4.77,-68.39,0.30\",\"2000-01-28,1368.69,1534.99,1547.70,1361.21,290763951,235174845440.00,13.65,12.32,168.41,0.79\",\"2000-02-29,1591.44,1714.57,1770.88,1583.58,372839124,352376625152.00,12.20,11.70,179.58,1.02\",\"2000-03-31,1720.59,1800.22,1811.06,1596.07,468987773,451189257216.00,12.54,5.00,85.65,1.28\",\"2000-04-28,1799.57,1836.32,1858.26,1746.30,299069742,298000380928.00,6.22,2.01,36.10,0.82\",\"2000-05-31,1848.17,1894.55,1908.30,1695.53,210112056,216096141312.00,11.59,3.17,58.23,0.57\",\"2000-06-30,1900.53,1928.10,1953.33,1884.61,311865144,309953387520.00,3.63,1.77,33.55,0.85\",\"2000-07-31,1917.44,2023.53,2024.99,1888.24,322061107,280412246016.00,7.09,4.95,95.43,0.88\",\"2000-08-31,2027.01,2021.19,2114.52,1980.34,376217601,348080470016.00,6.63,-0.12,-2.34,1.03\",\"2000-09-29,2009.15,1910.16,2026.35,1874.21,154312995,151215416320.00,7.53,-5.49,-111.03,0.42\",\"2000-10-31,1912.07,1961.28,1980.62,1896.94,129495216,135405416448.00,4.38,2.68,51.12,0.35\",\"2000-11-30,1961.08,2070.61,2125.72,1959.72,251043936,266251673088.00,8.46,5.57,109.33,0.69\",\"2000-12-29,2073.08,2073.47,2097.28,2024.28,253660385,225910386688.00,3.53,0.14,2.86,0.69\",\"2001-01-19,2077.07,2065.60,2131.98,2007.70,182685811,181074398720.00,5.99,-0.38,-7.87,0.50\",\"2001-02-28,2069.85,1959.18,2072.54,1893.78,125081965,116878325760.00,8.65,-5.15,-106.42,0.34\",\"2001-03-30,1959.00,2112.77,2112.87,1953.72,351475539,281703756288.00,8.12,7.84,153.59,0.96\",\"2001-04-30,2117.45,2119.18,2179.74,2081.64,446043952,338655051776.00,4.64,0.30,6.41,1.22\",\"2001-05-31,2119.89,2214.25,2222.65,2119.89,332318660,283418659840.00,4.85,4.49,95.07,0.91\",\"2001-06-29,2216.56,2218.02,2245.43,2157.12,321901740,309387153408.00,3.99,0.17,3.77,0.88\",\"2001-07-31,2220.41,1920.31,2223.20,1909.29,230077797,196849746432.00,14.15,-13.42,-297.71,0.63\",\"2001-08-31,1925.23,1834.13,1990.69,1795.56,214882552,167636372992.00,10.16,-4.49,-86.18,0.59\",\"2001-09-28,1831.70,1764.86,1888.90,1750.76,182422250,117382985216.00,7.53,-3.78,-69.27,0.50\",\"2001-10-31,1766.13,1689.17,1773.79,1514.86,205154895,133580983040.00,14.67,-4.29,-75.69,0.56\",\"2001-11-30,1692.35,1747.99,1748.00,1550.46,207398182,143742665472.00,11.69,3.48,58.82,0.57\",\"2001-12-31,1751.05,1645.97,1776.02,1593.03,172031990,135036541952.00,10.47,-5.84,-102.02,0.47\",\"2002-01-31,1643.48,1491.67,1643.50,1339.20,202129630,132420550656.00,18.49,-9.37,-154.30,0.55\",\"2002-02-28,1494.58,1524.70,1544.92,1476.13,119103202,82617579008.00,4.61,2.21,33.03,0.33\",\"2002-03-29,1521.53,1603.91,1693.87,1494.83,380968843,289090573824.00,13.05,5.20,79.21,1.04\",\"2002-04-30,1598.03,1667.75,1680.30,1575.97,225832079,184107545600.00,6.50,3.98,63.84,0.62\",\"2002-05-31,1669.87,1515.73,1670.92,1506.54,128014277,109687863552.00,9.86,-9.12,-152.02,0.35\",\"2002-06-28,1510.25,1732.76,1748.89,1455.31,348879822,246000538880.00,19.37,14.32,217.03,0.95\",\"2002-07-31,1736.29,1651.59,1741.92,1647.27,241571308,202496331264.00,5.46,-4.68,-81.17,0.66\",\"2002-08-30,1650.05,1666.62,1696.28,1624.05,136558744,114071739648.00,4.37,0.91,15.03,0.37\",\"2002-09-27,1666.34,1581.62,1680.28,1577.53,110946756,86372007680.00,6.17,-5.10,-85.00,0.30\",\"2002-10-31,1577.05,1507.50,1577.05,1488.68,123155933,75239638784.00,5.59,-4.69,-74.12,0.34\",\"2002-11-29,1507.38,1434.18,1573.10,1353.14,180644811,120814268672.00,14.59,-4.86,-73.32,0.49\",\"2002-12-31,1432.03,1357.65,1439.85,1348.71,157413083,108861060864.00,6.35,-5.34,-76.53,0.43\",\"2003-01-29,1347.43,1499.81,1508.59,1311.68,318844027,195592855808.00,14.50,10.47,142.16,0.87\",\"2003-02-28,1499.17,1511.93,1524.75,1474.47,147642450,103491807744.00,3.35,0.81,12.12,0.40\",\"2003-03-31,1511.58,1510.58,1529.75,1447.01,177241955,132802575872.00,5.47,-0.09,-1.35,0.48\",\"2003-04-30,1513.00,1521.44,1649.60,1473.27,542571187,375857076736.00,11.67,0.72,10.86,1.48\",\"2003-05-30,1522.98,1576.26,1581.61,1477.25,265931638,194373554176.00,6.86,3.60,54.82,0.73\",\"2003-06-30,1577.99,1486.02,1582.41,1483.69,240738131,159559037440.00,6.26,-5.72,-90.24,0.66\",\"2003-07-31,1485.52,1476.74,1540.19,1464.87,210929924,151572146688.00,5.07,-0.62,-9.28,0.58\",\"2003-08-29,1476.47,1421.98,1497.10,1406.68,129856479,95330717696.00,6.12,-3.71,-54.76,0.35\",\"2003-09-30,1423.35,1367.16,1456.55,1348.22,155248011,114426169856.00,7.62,-3.86,-54.82,0.42\",\"2003-10-31,1366.20,1348.30,1411.88,1334.78,164454838,106685031168.00,5.64,-1.38,-18.86,0.45\",\"2003-11-28,1346.48,1397.22,1423.58,1307.40,309617488,188745052672.00,8.62,3.63,48.92,0.85\",\"2003-12-31,1398.71,1497.04,1525.30,1398.71,469573057,298406538240.00,9.06,7.14,99.82,1.28\",\"2004-01-30,1492.72,1590.73,1634.41,1491.69,356958013,251050334208.00,9.53,6.26,93.69,0.97\",\"2004-02-27,1630.65,1675.07,1730.28,1585.96,595819499,452388325376.00,9.07,5.30,84.34,1.63\",\"2004-03-31,1676.22,1741.62,1760.08,1634.71,468341440,356722574336.00,7.48,3.97,66.55,1.28\",\"2004-04-30,1744.71,1595.59,1783.01,1572.23,404251968,325762390016.00,12.10,-8.38,-146.03,1.10\",\"2004-05-31,1600.63,1555.91,1606.07,1508.65,162512648,116288178688.00,6.11,-2.49,-39.68,0.44\",\"2004-06-30,1555.56,1399.16,1580.57,1376.22,237253776,169958002176.00,13.13,-10.07,-156.75,0.65\",\"2004-07-30,1398.20,1386.20,1466.78,1366.50,240506631,155127734272.00,7.17,-0.93,-12.96,0.66\",\"2004-08-31,1381.88,1342.06,1424.55,1310.02,190773844,114801929216.00,8.26,-3.18,-44.14,0.52\",\"2004-09-30,1340.75,1396.70,1496.21,1259.43,415522042,243466627840.00,17.64,4.07,54.64,1.13\",\"2004-10-29,1395.67,1320.54,1437.57,1289.87,274668950,173103607296.00,10.57,-5.45,-76.16,0.75\",\"2004-11-30,1317.66,1340.77,1387.34,1290.99,320007322,184495946752.00,7.30,1.53,20.23,0.87\",\"2004-12-31,1340.92,1266.50,1352.45,1264.15,237372264,127921336576.00,6.59,-5.54,-74.27,0.65\",\"2005-01-31,1260.78,1191.82,1268.86,1189.21,206209307,107140113152.00,6.29,-5.90,-74.68,0.56\",\"2005-02-28,1189.50,1306.00,1328.53,1187.26,257579282,127513349632.00,11.85,9.58,114.18,0.70\",\"2005-03-31,1305.25,1181.24,1326.08,1162.03,375598427,186625315328.00,12.56,-9.55,-124.76,1.03\",\"2005-04-29,1180.37,1159.15,1254.32,1135.73,388548976,181112172544.00,10.04,-1.87,-22.09,1.06\",\"2005-05-31,1160.62,1060.74,1165.39,1043.28,223922447,90810936064.00,10.53,-8.49,-98.41,0.61\",\"2005-06-30,1059.61,1080.94,1146.42,998.23,486472121,190919582208.00,13.97,1.90,20.20,1.33\",\"2005-07-29,1077.31,1083.03,1097.63,1004.08,377405093,136870946816.00,8.65,0.19,2.09,1.03\",\"2005-08-31,1082.80,1162.80,1201.76,1081.12,768863677,291683451392.00,11.14,7.37,79.77,2.10\",\"2005-09-30,1163.70,1155.61,1223.56,1129.05,597120702,255132204032.00,8.13,-0.62,-7.19,1.63\",\"2005-10-31,1153.67,1092.82,1165.67,1067.41,306126236,130836277248.00,8.50,-5.43,-62.79,0.84\",\"2005-11-30,1091.68,1099.26,1122.88,1074.30,396489206,140527693312.00,4.45,0.59,6.44,1.08\",\"2005-12-30,1098.78,1161.06,1173.06,1074.01,379101363,141506364416.00,9.01,5.62,61.80,1.04\",\"2006-01-25,1163.88,1258.05,1262.09,1161.91,518491447,226870039552.00,8.63,8.35,96.99,1.42\",\"2006-02-28,1263.00,1299.03,1304.61,1256.81,543788947,235681081344.00,3.80,3.26,40.98,1.48\",\"2006-03-31,1299.15,1298.30,1313.13,1238.16,567038827,254484399104.00,5.77,-0.06,-0.73,1.55\",\"2006-04-28,1298.68,1440.22,1444.71,1298.68,914328670,442730631168.00,11.25,10.93,141.92,2.50\",\"2006-05-31,1446.99,1641.30,1678.60,1446.99,1261933293,678456328192.00,16.08,13.96,201.08,3.45\",\"2006-06-30,1639.67,1672.21,1695.58,1512.52,1075434758,566733221888.00,11.15,1.88,30.91,2.94\",\"2006-07-31,1677.31,1612.73,1757.47,1611.16,911272021,506546521088.00,8.75,-3.56,-59.48,2.49\",\"2006-08-31,1619.64,1658.64,1667.53,1541.41,678223755,346699783168.00,7.82,2.85,45.91,1.85\",\"2006-09-29,1658.66,1752.42,1755.69,1634.03,837051251,432338803712.00,7.33,5.65,93.78,2.29\",\"2006-10-31,1768.14,1837.99,1842.73,1753.49,877300435,439776518144.00,5.09,4.88,85.57,2.40\",\"2006-11-30,1838.68,2099.29,2102.06,1833.20,1323923297,702754164736.00,14.63,14.22,261.30,3.61\",\"2006-12-29,2106.29,2675.47,2698.90,2084.27,1816592095,1050175860736.00,29.28,27.45,576.18,4.96\",\"2007-01-31,2728.19,2786.33,2994.28,2617.02,2508365650,1751354437632.00,14.10,4.14,110.86,6.85\",\"2007-02-28,2744.81,2881.07,3049.77,2541.52,1591253783,1185048641536.00,18.24,3.40,94.74,4.34\",\"2007-03-30,2877.20,3183.98,3273.73,2723.06,2584220763,2134087614464.00,19.11,10.51,302.91,7.06\",\"2007-04-30,3196.59,3841.27,3851.35,3196.59,3114852093,3236545773568.00,20.56,20.64,657.29,8.50\",\"2007-05-31,3937.94,4109.65,4335.96,3845.23,3150358952,3974512115712.00,12.78,6.99,268.38,8.60\",\"2007-06-29,4120.63,3820.70,4312.00,3404.15,2979268183,3605374312448.00,22.09,-7.03,-288.95,8.13\",\"2007-07-31,3800.23,4471.03,4476.63,3563.54,1847649135,2191543627776.00,23.90,17.02,650.33,5.04\",\"2007-08-31,4488.77,5218.83,5235.16,4284.87,2570673797,3601380614144.00,21.25,16.73,747.80,7.02\",\"2007-09-28,5257.78,5552.30,5560.42,5025.34,2030233085,3076618084352.00,10.25,6.39,333.47,5.54\",\"2007-10-31,5683.31,5954.77,6124.04,5462.01,1464080754,2444001075200.00,11.92,7.25,402.47,4.00\",\"2007-11-30,5978.94,4871.78,6005.13,4778.73,1111361148,1767704969216.00,20.60,-18.19,-1082.99,3.03\",\"2007-12-28,4838.56,5261.56,5336.50,4798.01,1352188081,2002408501248.00,11.05,8.00,389.78,3.69\",\"2008-01-31,5265.00,4383.39,5522.78,4330.70,1887177730,3110350381056.00,22.66,-16.69,-878.17,5.15\",\"2008-02-29,4388.25,4348.54,4695.80,4123.31,918487774,1439819816960.00,13.06,-0.80,-34.85,2.51\",\"2008-03-31,4323.70,3472.71,4472.15,3357.23,1398548659,1970483560448.00,25.64,-20.14,-875.83,3.82\",\"2008-04-30,3461.08,3693.11,3705.09,2990.79,1637669672,1921220706304.00,20.57,6.35,220.40,4.47\",\"2008-05-30,3739.80,3433.35,3786.02,3333.95,1581657625,2013760495616.00,12.24,-7.03,-259.76,4.32\",\"2008-06-30,3426.20,2736.10,3483.61,2693.40,1155888145,1155162058752.00,23.02,-20.31,-697.25,3.16\",\"2008-07-31,2743.16,2775.72,2952.04,2566.53,1619298062,1561811681280.00,14.09,1.45,39.62,4.42\",\"2008-08-29,2751.02,2397.37,2830.76,2284.58,1036471943,808666611712.00,19.68,-13.63,-378.35,2.83\",\"2008-09-26,2380.47,2293.78,2380.47,1802.33,1230857039,836296081408.00,24.12,-4.32,-103.59,3.36\",\"2008-10-31,2267.39,1728.79,2267.39,1664.93,1198120149,755986202624.00,26.26,-24.63,-564.99,3.27\",\"2008-11-28,1713.76,1871.16,2050.88,1678.96,2066536427,1214618560512.00,21.51,8.24,142.37,5.64\",\"2008-12-31,1865.74,1820.81,2100.81,1814.75,2551395553,1597041635328.00,15.29,-2.69,-50.35,6.97\",\"2009-01-23,1849.02,1990.66,2018.51,1844.09,1497598483,992466333696.00,9.58,9.33,169.85,4.09\",\"2009-02-27,2008.13,2082.85,2402.81,1987.13,3564243510,2679023800320.00,20.88,4.63,92.19,9.73\",\"2009-03-31,2066.23,2373.21,2392.77,2037.02,3163165712,2546460618752.00,17.08,13.94,290.36,8.64\",\"2009-04-30,2380.98,2477.57,2579.22,2331.88,3480883824,3055866208256.00,10.42,4.40,104.36,9.50\",\"2009-05-27,2486.69,2632.93,2688.11,2486.23,2699600574,2456206819328.00,8.15,6.27,155.36,7.37\",\"2009-06-30,2668.40,2959.36,2997.27,2668.40,3269336524,3134113734656.00,12.49,12.40,326.43,8.93\",\"2009-07-31,2950.17,3412.06,3454.02,2947.69,4540701670,4800856719360.00,17.11,15.30,452.70,12.40\",\"2009-08-31,3429.69,2667.75,3478.01,2663.00,3376616496,3425830035456.00,23.89,-21.81,-744.31,9.22\",\"2009-09-30,2649.17,2779.43,3068.03,2639.76,2948162471,2897459273728.00,16.05,4.19,111.68,8.05\",\"2009-10-30,2840.13,2995.85,3123.46,2834.62,2006591167,2042654015488.00,10.39,7.79,216.42,5.48\",\"2009-11-30,2933.82,3195.30,3361.39,2923.52,3754254961,3972485201920.00,14.62,6.66,199.45,10.25\",\"2009-12-31,3191.06,3277.14,3334.01,3039.86,3026727178,3259664703488.00,9.21,2.56,81.84,8.26\",\"2010-01-29,3289.75,2989.29,3306.75,2963.89,2674281856,2970097647616.00,10.46,-8.78,-287.85,7.30\",\"2010-02-26,2981.37,3051.94,3067.52,2890.02,1359493770,1452718817280.00,5.94,2.10,62.65,3.71\",\"2010-03-31,3057.01,3109.10,3132.58,2963.44,2352346962,2560712572928.00,5.54,1.87,57.16,6.42\",\"2010-04-30,3111.94,2870.61,3181.66,2820.95,2581867658,2918268108800.00,11.60,-7.67,-238.49,7.05\",\"2010-05-31,2821.35,2592.15,2862.55,2481.97,2049862694,1910392102912.00,13.26,-9.70,-278.46,5.60\",\"2010-06-30,2577.76,2398.37,2598.90,2382.36,1478459183,1323574845440.00,8.35,-7.48,-193.78,4.04\",\"2010-07-30,2393.95,2637.50,2656.41,2319.74,2195783137,1780801945600.00,14.04,9.97,239.13,6.00\",\"2010-08-31,2635.81,2638.80,2701.93,2564.84,2712434101,2507933089792.00,5.20,0.05,1.30,7.41\",\"2010-09-30,2641.05,2655.66,2704.93,2573.63,2250726352,2372378619904.00,4.98,0.64,16.86,6.15\",\"2010-10-29,2681.25,2978.83,3073.38,2677.99,3378396902,3739733491712.00,14.89,12.17,323.17,9.22\",\"2010-11-30,2986.89,2820.18,3186.72,2758.92,4042972788,4641575288832.00,14.36,-5.33,-158.65,11.04\",\"2010-12-31,2810.54,2808.08,2939.05,2721.48,2412057136,2682173276160.00,7.71,-0.43,-12.10,6.59\",\"2011-01-31,2825.33,2790.69,2868.00,2661.45,1936920404,2049064198144.00,7.36,-0.62,-17.39,5.29\",\"2011-02-28,2795.07,2905.05,2944.41,2760.18,1919974660,2186076577792.00,6.60,4.10,114.36,5.24\",\"2011-03-31,2906.28,2928.11,3012.04,2850.95,3202941770,3735172505600.00,5.55,0.79,23.06,8.75\",\"2011-04-29,2932.48,2911.51,3067.46,2871.01,2521846283,2757674934272.00,6.71,-0.57,-16.60,6.89\",\"2011-05-31,2911.51,2743.47,2933.46,2689.21,1957067045,2060381806592.00,8.39,-5.77,-168.04,5.34\",\"2011-06-30,2737.06,2762.08,2774.36,2610.99,1782801326,1869747294208.00,5.95,0.68,18.61,4.87\",\"2011-07-29,2767.83,2701.73,2826.96,2677.12,2082899664,2314552033280.00,5.42,-2.18,-60.35,5.69\",\"2011-08-31,2697.57,2567.34,2712.89,2437.68,1801978595,1913013506048.00,10.19,-4.97,-134.39,4.92\",\"2011-09-30,2569.80,2359.22,2584.80,2348.22,1190285001,1210646315008.00,9.21,-8.11,-208.12,3.25\",\"2011-10-31,2363.08,2468.25,2483.76,2307.15,1201777310,1115670050048.00,7.49,4.62,109.03,3.28\",\"2011-11-30,2450.33,2333.41,2536.78,2319.44,1641719638,1623328178688.00,8.81,-5.46,-134.84,4.48\",\"2011-12-30,2392.48,2199.42,2423.56,2134.02,1154517877,1022673261312.00,12.41,-5.74,-133.99,3.15\",\"2012-01-31,2212.00,2292.61,2324.49,2132.63,1045580518,905508241664.00,8.72,4.24,93.19,2.85\",\"2012-02-29,2288.07,2428.49,2478.38,2263.34,1952283807,1771725445376.00,9.38,5.93,135.88,5.33\",\"2012-03-30,2418.79,2262.79,2476.22,2242.34,2089452092,2022506908672.00,9.63,-6.82,-165.70,5.70\",\"2012-04-27,2258.03,2396.32,2415.75,2251.39,1633715388,1468779275264.00,7.26,5.90,133.53,4.46\",\"2012-05-31,2421.08,2372.23,2453.73,2309.07,1991234823,1936082270720.00,6.04,-1.01,-24.09,5.44\",\"2012-06-29,2373.22,2225.43,2388.09,2188.72,1276093548,1213271062528.00,8.40,-6.19,-146.80,3.48\",\"2012-07-31,2234.32,2103.63,2244.83,2100.25,1349082449,1218154718976.00,6.50,-5.47,-121.80,3.68\",\"2012-08-31,2101.72,2047.52,2176.79,2032.54,1364021861,1145769779456.00,6.86,-2.67,-56.11,3.72\",\"2012-09-28,2044.82,2086.17,2145.00,1999.48,1432787813,1154914581504.00,7.11,1.89,38.65,3.91\",\"2012-10-31,2084.85,2068.88,2138.03,2053.09,1231251578,920182187008.00,4.07,-0.83,-17.29,3.36\",\"2012-11-30,2070.02,1980.12,2123.33,1959.33,1245203384,916117925888.00,7.93,-4.29,-88.76,3.40\",\"2012-12-31,1977.25,2269.13,2269.51,1949.46,2245206471,1716522227968.00,16.16,14.60,289.01,6.13\",\"2013-01-31,2289.51,2385.42,2391.82,2234.95,2671926055,2258990497792.00,6.91,5.12,116.29,7.30\",\"2013-02-28,2377.41,2365.59,2444.80,2289.89,1826534695,1627227246592.00,6.49,-0.83,-19.83,4.99\",\"2013-03-29,2364.54,2236.62,2369.65,2228.81,2325954880,2039708848128.00,5.95,-5.45,-128.97,6.35\",\"2013-04-26,2229.46,2177.91,2253.42,2165.78,1497143669,1284320397312.00,3.92,-2.62,-58.71,4.09\",\"2013-05-31,2170.78,2300.59,2334.33,2161.14,2294899562,2085294382848.00,7.95,5.63,122.68,6.27\",\"2013-06-28,2300.21,1979.21,2313.43,1849.65,1552384045,1328937492736.00,20.16,-13.97,-321.38,4.24\",\"2013-07-31,1965.99,1993.80,2092.87,1946.37,2164964324,1819243548416.00,7.40,0.74,14.59,5.91\",\"2013-08-30,2000.82,2098.38,2198.85,1997.06,2489486052,2068466159616.00,10.12,5.25,104.58,6.80\",\"2013-09-30,2104.09,2174.67,2270.27,2078.46,2999507730,2536614277120.00,9.14,3.64,76.29,8.19\",\"2013-10-31,2171.90,2141.61,2242.98,2093.20,2377395749,2097596624896.00,6.89,-1.52,-33.06,6.49\",\"2013-11-29,2139.88,2220.50,2234.39,2078.99,2187871868,1921697377280.00,7.26,3.68,78.89,5.97\",\"2013-12-31,2203.12,2115.98,2260.87,2068.54,2071263957,1797708357888.00,8.66,-4.71,-104.52,5.66\",\"2014-01-30,2112.13,2033.08,2113.11,1984.82,1563580332,1359830631424.00,6.06,-3.92,-82.90,4.27\",\"2014-02-28,2022.32,2056.30,2177.98,2014.38,2051218335,1792376137728.00,8.05,1.14,23.22,5.60\",\"2014-03-31,2052.07,2033.31,2079.55,1974.38,2312976689,1863529390080.00,5.11,-1.12,-22.99,6.32\",\"2014-04-30,2031.01,2026.36,2146.67,1997.64,2067447856,1676592255488.00,7.33,-0.34,-6.95,5.64\",\"2014-05-30,2022.18,2039.21,2061.06,1991.06,1512460515,1200023033856.00,3.45,0.63,12.85,4.13\",\"2014-06-30,2039.20,2048.33,2087.32,2010.53,1591231478,1311746404352.00,3.77,0.45,9.12,4.34\",\"2014-07-31,2051.23,2201.56,2202.13,2033.00,2980954633,2389624782848.00,8.26,7.48,153.23,8.14\",\"2014-08-29,2194.17,2217.20,2248.94,2180.60,3343780116,2789714087936.00,3.10,0.71,15.64,9.13\",\"2014-09-30,2220.13,2363.87,2365.49,2217.69,4191031068,3552143753216.00,6.67,6.62,146.67,11.44\",\"2014-10-31,2368.58,2420.18,2423.60,2279.84,3697286924,3128628473856.00,6.08,2.38,56.31,10.09\",\"2014-11-28,2425.23,2682.83,2683.18,2401.75,5691334597,4881325359104.00,11.63,10.85,262.65,15.54\",\"2014-12-31,2691.73,3234.68,3239.36,2665.69,11383522762,11481330065408.00,21.38,20.57,551.85,31.08\",\"2015-01-30,3258.63,3210.36,3406.79,3095.07,7037433825,7834158653440.00,9.64,-0.75,-24.32,19.21\",\"2015-02-27,3148.14,3310.30,3324.55,3049.11,3615594951,4118308360192.00,8.58,3.11,99.94,9.87\",\"2015-03-31,3332.72,3747.90,3835.57,3198.37,9442484970,11265694613504.00,19.25,13.22,437.60,25.78\",\"2015-04-30,3748.34,4441.65,4572.39,3742.21,12677372727,17358039247975.00,22.15,18.51,693.75,34.61\",\"2015-05-29,4441.34,4611.74,4986.50,4099.04,10664884612,16332876034048.00,19.98,3.83,170.09,29.12\",\"2015-06-30,4633.10,4277.22,5178.19,3847.88,12870645767,19999625441280.00,28.85,-7.25,-334.52,35.14\",\"2015-07-31,4214.15,3663.73,4317.05,3373.54,13193595661,16111942029312.00,22.06,-14.34,-613.49,36.02\",\"2015-08-31,3614.99,3205.99,4006.34,2850.71,8719258733,10774444736512.00,31.54,-12.49,-457.74,23.81\",\"2015-09-30,3157.83,3052.78,3256.74,2983.53,5362512602,5626371821568.00,8.52,-4.78,-153.21,14.64\",\"2015-10-30,3156.07,3382.56,3457.52,3133.13,5452579125,6420219047936.00,10.63,10.80,329.78,14.89\",\"2015-11-30,3337.58,3445.41,3678.27,3302.18,7065814378,9220410224640.00,11.12,1.86,62.85,19.29\",\"2015-12-31,3442.44,3539.18,3684.57,3399.28,5368182332,7207430279168.00,8.28,2.72,93.77,14.66\",\"2016-01-29,3536.59,2737.60,3538.69,2638.30,4007771704,4338224422912.00,25.44,-22.65,-801.58,10.94\",\"2016-02-29,2730.98,2687.98,2933.96,2638.96,3013290916,3077309755392.00,10.78,-1.81,-49.62,8.23\",\"2016-03-31,2688.38,3003.92,3028.32,2668.76,5084600134,5342528307200.00,13.38,11.75,315.94,13.88\",\"2016-04-29,2997.09,2938.32,3097.17,2905.05,3786963354,4214743420928.00,6.40,-2.18,-65.60,10.34\",\"2016-05-31,2940.39,2916.62,3004.42,2780.76,2853848274,3155930787840.00,7.61,-0.74,-21.70,7.79\",\"2016-06-30,2917.15,2929.61,2945.94,2807.60,3175938685,3770003271680.00,4.74,0.45,12.99,8.67\",\"2016-07-29,2931.80,2979.34,3069.05,2922.52,4011659245,4703322476544.00,5.00,1.70,49.73,10.95\",\"2016-08-31,2971.95,3085.49,3140.44,2931.96,3956218228,4449908187136.00,7.00,3.56,106.15,10.80\",\"2016-09-30,3083.96,3004.70,3105.68,2969.13,2795832183,3140900614144.00,4.43,-2.62,-80.79,7.63\",\"2016-10-31,3020.46,3100.49,3137.03,3014.62,2783130104,3063402254336.00,4.07,3.19,95.79,7.60\",\"2016-11-30,3101.66,3250.03,3301.21,3094.10,5200799407,5853881372672.00,6.68,4.82,149.54,14.20\",\"2016-12-30,3257.03,3103.64,3279.71,3068.42,3965931464,4406217834496.00,6.50,-4.50,-146.39,10.83\",\"2017-01-26,3105.31,3159.17,3174.58,3044.29,2759490669,3052771803136.00,4.20,1.79,55.53,7.53\",\"2017-02-28,3160.08,3241.73,3264.08,3132.03,3398041799,3777257340928.00,4.18,2.61,82.56,9.28\",\"2017-03-31,3240.07,3222.51,3283.24,3193.16,4117634423,5088074592256.00,2.78,-0.59,-19.22,11.24\",\"2017-04-28,3235.66,3154.66,3295.19,3097.33,3789459398,4224360730624.00,6.14,-2.11,-67.85,10.35\",\"2017-05-31,3147.23,3117.18,3154.78,3016.53,3228021204,3606045044736.00,4.38,-1.19,-37.48,8.81\",\"2017-06-30,3108.42,3192.43,3193.46,3078.79,3204652292,3658665504768.00,3.68,2.41,75.25,8.75\",\"2017-07-31,3192.00,3273.03,3276.95,3139.50,4187623654,4506669383680.00,4.31,2.52,80.60,11.43\",\"2017-08-31,3274.37,3360.81,3376.65,3200.75,5059863052,5543980208128.00,5.37,2.68,87.78,13.82\",\"2017-09-29,3365.99,3348.94,3391.64,3332.60,4232609701,4940508467200.00,1.76,-0.35,-11.87,11.56\",\"2017-10-31,3403.25,3393.34,3421.10,3357.28,2706739541,3406039277568.00,1.91,1.33,44.40,7.39\",\"2017-11-30,3393.97,3317.19,3450.49,3300.78,3982400471,5136182591488.00,4.41,-2.24,-76.15,10.87\",\"2017-12-29,3315.10,3307.17,3324.52,3254.18,2941226861,3628130553856.00,2.12,-0.30,-10.02,8.03\",\"2018-01-31,3314.03,3480.83,3587.03,3314.03,4797771235,5772056289280.00,8.25,5.25,173.66,13.10\",\"2018-02-28,3478.67,3259.41,3495.09,3062.74,2888959452,3262254743552.00,12.42,-6.36,-221.42,7.89\",\"2018-03-30,3235.09,3168.90,3333.88,3091.46,3704084125,4396936880128.00,7.44,-2.78,-90.51,10.11\",\"2018-04-27,3169.78,3082.23,3220.84,3041.62,2698985451,3372116049920.00,5.66,-2.74,-86.67,7.37\",\"2018-05-31,3087.41,3095.47,3219.74,3041.00,2979289094,3810440503296.00,5.80,0.43,13.24,8.13\",\"2018-06-29,3084.75,2847.42,3128.72,2782.38,2623979375,3100449951744.00,11.19,-8.01,-248.05,7.16\",\"2018-07-31,2841.58,2876.40,2915.30,2691.02,3049095133,3259242876928.00,7.88,1.02,28.98,8.33\",\"2018-08-31,2882.51,2725.25,2897.40,2653.11,2821389015,2872972697600.00,8.49,-5.25,-151.15,7.70\",\"2018-09-28,2716.40,2821.35,2827.34,2644.30,2148358946,2152910417920.00,6.72,3.53,96.10,5.87\",\"2018-10-31,2768.21,2602.78,2771.94,2449.20,2740428851,2449203240960.00,11.44,-7.75,-218.57,7.48\",\"2018-11-30,2617.03,2588.19,2703.51,2555.32,4052823298,3352501661696.00,5.69,-0.56,-14.59,11.07\",\"2018-12-28,2647.13,2493.90,2666.08,2462.84,2575119403,2236020850688.00,7.85,-3.64,-94.29,7.03\",\"2019-01-31,2497.88,2584.57,2630.32,2440.91,3385640712,2804232437760.00,7.59,3.64,90.67,9.24\",\"2019-02-28,2597.78,2940.95,2997.49,2590.55,4421808305,3816144642048.00,15.74,13.79,356.38,12.07\",\"2019-03-29,2954.40,3090.76,3129.94,2930.83,8691925434,8300906455040.00,6.77,5.09,149.81,23.73\",\"2019-04-30,3111.66,3078.34,3288.45,3050.03,7487133087,7562326147072.00,7.71,-0.40,-12.42,20.44\",\"2019-05-31,2984.73,2898.70,2986.54,2833.04,4465126595,4317925220352.00,4.99,-5.84,-179.64,12.19\",\"2019-06-28,2901.74,2978.88,3012.83,2822.19,3974677501,3846260588544.00,6.58,2.77,80.18,10.85\",\"2019-07-31,3024.62,2932.51,3048.48,2879.69,3718515285,3992261566464.00,5.67,-1.56,-46.37,10.15\",\"2019-08-30,2920.85,2886.24,2927.34,2733.92,3694481764,4262760017920.00,6.60,-1.58,-46.27,10.09\",\"2019-09-30,2886.94,2905.19,3042.93,2883.68,3946915550,4558441168896.00,5.52,0.66,18.95,10.78\",\"2019-10-31,2905.76,2929.06,3026.38,2891.54,2635181115,2973472292864.00,4.64,0.82,23.87,7.20\",\"2019-11-29,2924.34,2871.98,3008.31,2858.58,3044961697,3357767282688.00,5.11,-1.95,-57.08,8.31\",\"2019-12-31,2874.45,3050.12,3051.68,2857.32,4127694911,4416950018048.00,6.77,6.20,178.14,11.27\",\"2020-01-23,3066.34,2976.53,3127.17,2955.35,3873404242,4461753876480.00,5.63,-2.41,-73.59,10.58\",\"2020-02-28,2716.70,2880.30,3058.90,2685.27,6537017087,7556709675008.00,12.55,-3.23,-96.23,17.85\",\"2020-03-31,2899.31,2750.30,3074.26,2646.80,6989474744,7504342409216.00,14.84,-4.51,-130.00,19.08\",\"2020-04-30,2743.54,2860.08,2865.59,2719.90,4710379191,5066189262848.00,5.30,3.99,109.78,12.86\",\"2020-05-29,2831.63,2852.35,2914.28,2802.47,3703707261,4488403505152.00,3.91,-0.27,-7.73,10.11\",\"2020-06-30,2871.96,2984.67,2990.83,2871.96,4699819694,5733482606592.00,4.17,4.64,132.32,12.83\",\"2020-07-31,2991.18,3310.01,3458.79,2984.98,10244995253,13240885506048.00,15.87,10.90,325.34,27.97\",\"2020-08-31,3332.18,3395.68,3456.72,3263.27,7359125699,9621174857728.00,5.84,2.59,85.67,20.09\",\"2020-09-30,3389.74,3218.05,3425.63,3202.34,4906229054,6193724911616.00,6.58,-5.23,-177.63,13.40\"]}}";
        List<String> klineList = new ArrayList<String>();
        JSONObject szzzMonthJson = JSON.parseObject(rsJson);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
        String name = szzzMonthDataJson.getString("name");
        System.out.println(name);
        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        for (Object kline : klines) {
            String klineStr = (String) kline;
            klineList.add(klineStr);
        }

        //倒序
        for (int i = klineList.size(); i >= 0; i--) {
            if (count-- <= 0) {
                break;
            }

            String klineStr = klineList.get(i - 1);
            //  日期，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率
            //"2020-09-30,3389.74,3218.05,3425.63,3202.34,4906229054,6193724911616.00,6.58,-5.23,-177.63,13.40"
            String[] klineArray = klineStr.split(",");
            System.out.print("月份:" + klineArray[0] + ",");
            System.out.print("收盘:" + klineArray[2] + ",");
            System.out.print("涨跌幅:" + klineArray[8] + ",\t");
            System.out.print("开盘:" + klineArray[1] + ",\t");
            System.out.print("最高:" + klineArray[3] + ",");
            System.out.print("最低:" + klineArray[4] + ",");
            System.out.print("成交量:" + klineArray[5] + ",");
            System.out.print("成交额:" + klineArray[6] + ",");
            System.out.print("振幅:" + klineArray[7] + ",");
            System.out.print("涨跌额:" + klineArray[9] + ",");
            System.out.print("换手率:" + klineArray[10] + ",");
            System.out.println();
        }
    }


    /**
     * 查询-ETF-指数
     *
     * @param cookie  cookie
     * @param zhiShu  指数
     * @param count   数量
     * @param klt     K线周期类型
     * @param dayTpye 日期类型
     * @param date
     */
    public static Fupan findFupanPointByKline(String cookie, String zhiShu, int count, String klt, String dayTpye, String date) {
        Fupan fupanRs = new Fupan();
        String closePointLast = "";//上一时段-收盘点位
        //where
        fupanRs.setCode(date);
        fupanRs.setPeriod(dayTpye);
        fupanRs.setType("1");//实际
        String dbFieldRt = "";
        String dbFieldNet = "";
        String dbFieldCje = "";

        StringBuffer url = new StringBuffer();
        if (HS_300_000300.equals(zhiShu) || SH_50_000016.equals(zhiShu) || SHANG_HAI.equals(zhiShu) || SHEN_ZHEN.equals(zhiShu) || CYB.equals(zhiShu) || BIZ_QUANSHANG.equals(zhiShu) || ZZ_500_000905.equals(zhiShu) || KCB_50.equals(zhiShu)) {
            url.append("http://96.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery331093188916841208381602168987937");
        } else if (CYB_50_399673.equals(zhiShu)) {
            url.append("http://50.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery33107544000725313278_1602691226567");
        } else if ("".equals(zhiShu)) {
            System.out.println("指数不能为空！");
        }
        url.append("&secid=" + zhiShu);
        url.append("&ut=fa5fd1943c7b386f172d6893dbfba10b");
        url.append("&fields1=f1");/** f1,f2,f3,f4,f5,f6**/
        url.append("&fields2=" +
//                "f1,f2,f3,f4,f5,f6,f7,f8,f9," +
//                "f10,f11,f12,f13,f14,f15,f16,f17,f18,f19," +
//                "f20,f21,f22,f23,f24,f25,f26,f27,f28,f29," +
//                "f30,f31,f32,f33,f34,f35,f36,f37,f38,f39," +
//                "f40,f41,f42,f43,f44,f45,f46,f47,f48,f49," +
                "f50,f51,f52,f53,f54,f55,f56,f57,f58,f59," +
                "f60,f61,f62,f63,f64,f65,f66,f67,f68,f69" +
//                "f70,f71,f72,f73,f74,f75,f76,f77,f78,f79," +
                "");/**     f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61  日期，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率 **/
        url.append("&klt=" + klt);
        url.append("&fqt=1");
        url.append("&beg=" + date.replace("-", ""));
        url.append("&end=20500101");
        url.append("&lmt=" + count);
        url.append("&_=" + System.currentTimeMillis());
//            System.out.println(url.toString());

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("&StartDate=").append(startDate);

//        System.out.println("请求url:" + url + JSON.toJSONString(urlParam));
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
        for (Object kline : klines) {
            String klineStr = (String) kline;
            klineList.add(klineStr);
        }

        //倒序
        for (int i = 0; i <= klineList.size() - 1; i++) {
            String klineStr = klineList.get(i);
            //  日期，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率
            //"2020-09-30,3389.74,3218.05,3425.63,3202.34,4906229054,6193724911616.00,6.58,-5.23,-177.63,13.40"
            String[] klineArray = klineStr.split(",");
            String shouPan = klineArray[2];
            String max = klineArray[3];
            String min = klineArray[4];
            String zhangDie = klineArray[8];
            String chengJiaoE = klineArray[6];
            String curDate = klineArray[0];

            if (HS_300_000300.equals(zhiShu)) {
                fupanRs.setRt_hs300(zhangDie);
                fupanRs.setPt_hs300(shouPan);
                fupanRs.setCje_hs300(chengJiaoE);
                fupanRs.setPt_hs300_last(closePointLast);
                fupanRs.setPt_hs300_max(max);
                fupanRs.setPt_hs300_min(min);
                dbFieldRt = "rt_hs300";
                dbFieldNet = "pt_hs300";
                dbFieldCje = "cje_hs300";
            }
            if (SHANG_HAI.equals(zhiShu)) {
                fupanRs.setRt_sh(zhangDie);
                fupanRs.setPt_sh(shouPan);
                fupanRs.setCje_sh(chengJiaoE);
                fupanRs.setPt_sh_last(closePointLast);
                fupanRs.setPt_sh_max(max);
                fupanRs.setPt_sh_min(min);
                dbFieldRt = "rt_sh";
                dbFieldNet = "pt_sh";
                dbFieldCje = "cje_sh";
            }
            if (SHEN_ZHEN.equals(zhiShu)) {
                fupanRs.setRt_sz(zhangDie);
                fupanRs.setPt_sz(shouPan);
                fupanRs.setCje_sz(chengJiaoE);
                fupanRs.setPt_sz_last(closePointLast);
                fupanRs.setPt_sz_max(max);
                fupanRs.setPt_sz_min(min);
                dbFieldRt = "rt_sz";
                dbFieldNet = "pt_sz";
                dbFieldCje = "cje_sz";
            }
            if (CYB.equals(zhiShu)) {
                fupanRs.setRt_cyb(zhangDie);
                fupanRs.setPt_cyb(shouPan);
                fupanRs.setCje_cyb(chengJiaoE);
                fupanRs.setPt_cyb_last(closePointLast);
                fupanRs.setPt_cyb_max(max);
                fupanRs.setPt_cyb_min(min);
                dbFieldRt = "rt_cyb";
                dbFieldNet = "pt_cyb";
                dbFieldCje = "cje_cyb";
            }
            if (CYB_50_399673.equals(zhiShu)) {
                fupanRs.setRt_cyb50(zhangDie);
                fupanRs.setPt_cyb50(shouPan);
                fupanRs.setCje_cyb50(chengJiaoE);
                fupanRs.setPt_cyb50_last(closePointLast);
                fupanRs.setPt_cyb50_max(max);
                fupanRs.setPt_cyb50_min(min);
                dbFieldRt = "rt_cyb50";
                dbFieldNet = "pt_cyb50";
                dbFieldCje = "cje_cyb50";
            }
            if (ZZ_500_000905.equals(zhiShu)) {
                fupanRs.setRt_zz500(zhangDie);
                fupanRs.setPt_zz500(shouPan);
                fupanRs.setCje_zz500(chengJiaoE);
                fupanRs.setPt_zz500_last(closePointLast);
                fupanRs.setPt_zz500_max(max);
                fupanRs.setPt_zz500_min(min);
                dbFieldRt = "rt_zz500";
                dbFieldNet = "pt_zz500";
                dbFieldCje = "cje_zz500";
            }
            if (SH_50_000016.equals(zhiShu)) {
                fupanRs.setRt_sh50(zhangDie);
                fupanRs.setPt_sh50(shouPan);
                fupanRs.setCje_sh50(chengJiaoE);
                fupanRs.setPt_sh50_last(closePointLast);
                fupanRs.setPt_sh50_max(max);
                fupanRs.setPt_sh50_min(min);
                dbFieldRt = "rt_sh50";
                dbFieldNet = "pt_sh50";
                dbFieldCje = "cje_sh50";
            }
            if (KCB_50.equals(zhiShu)) {
                fupanRs.setRt_kcb50(zhangDie);
                fupanRs.setPt_kcb50(shouPan);
                fupanRs.setCje_kcb50(chengJiaoE);
                fupanRs.setPt_kcb50_last(closePointLast);
                fupanRs.setPt_kcb50_max(max);
                fupanRs.setPt_kcb50_min(min);
                dbFieldRt = "rt_kcb50";
                dbFieldNet = "pt_kcb50";
                dbFieldCje = "cje_kcb50";
            }
            if (BIZ_QUANSHANG.equals(zhiShu)) {
                fupanRs.setRt_biz_qs(zhangDie);
                fupanRs.setPt_biz_qs(shouPan);
                fupanRs.setCje_biz_qs(chengJiaoE);
                fupanRs.setPt_biz_qs_last(closePointLast);
                dbFieldRt = "rt_biz_qs";
                dbFieldNet = "pt_biz_qs";
                dbFieldCje = "cje_biz_qs";
            }

//            System.out.println(klineStr+",上一时段-收盘点位:"+closePointLast);
            closePointLast = klineArray[2];//上一时段-收盘点位

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
//            System.out.println("UPDATE `fupan` SET `" + dbFieldRt + "`='" + zhangDie + "', `" + dbFieldNet + "`='" + shouPan + "', `" + dbFieldCje + "`='" + chengJiaoE + "' WHERE (`CODE`='" + curDate + "') AND fupan.period='" + dayTpye + "'" + " AND fupan.TYPE=1;");
            if (StringUtils.isNotBlank(date) && curDate.equals(date)) {//指定日期
                System.out.println("UPDATE `fupan` SET `" + dbFieldRt + "`='" + zhangDie + "', `" + dbFieldNet + "`='" + shouPan + "', `" + dbFieldCje + "`='" + chengJiaoE + "' WHERE (`CODE`='" + curDate + "') AND fupan.period='" + dayTpye + "'" + " AND fupan.TYPE=1;");
                return fupanRs;
            } else {
                System.out.println("/** 未指定日期 **/UPDATE `fupan` SET `" + dbFieldRt + "`='" + zhangDie + "', `" + dbFieldNet + "`='" + shouPan + "', `" + dbFieldCje + "`='" + chengJiaoE + "' WHERE (`CODE`='" + curDate + "') AND fupan.period='" + dayTpye + "'" + " AND fupan.TYPE=1;");
            }
        }
        return null;
    }

}
