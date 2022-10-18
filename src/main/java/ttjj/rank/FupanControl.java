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
import ttjj.dto.*;
import ttjj.rank.history.StockTradeDemo;
import ttjj.service.FundFlowService;
import ttjj.service.KlineService;
import ttjj.service.MyPositionService;
import ttjj.service.StockService;
import utils.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static utils.Content.*;

/**
 * 复盘和仓位
 *
 * @author Administrator
 * @date 2022-07-12 10:14
 */
public class FupanControl {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//                String date = "2022-08-19";

        insertOrUpdate(date, KLT_101, DAYS_1,ContentCookie.COOKIE_DFCF);//保存复盘和仓位
//        checkMaByMyPosition(date);//检查我的持仓：超过均线、价格区间、今日涨跌

//        System.out.println( getCookieHttp());//获取cookie

//        checkFundFlowByMyPosition(date);//检查资金流向-我的仓位

//        listMyPosition(date, KLT_101);//查询我的仓位 KLT_102;//检查周期类型

    }

    /**
     * 更新股票涨跌个数
     * @param date
     */
    private static void updateStUpDownCount(String date) {
        //查询个数
        CondStock condition = new CondStock();
        condition.setDate(date);
        Integer countAll = StockService.count(condition);
        System.out.println("全市场个数-全部："+countAll);

        CondStock conditionUp = new CondStock();
        conditionUp.setDate(date);
        conditionUp.setMinF3(new BigDecimal("0"));
        Integer countAllUp = StockService.count(conditionUp);
        System.out.println("全市场个数-上涨："+countAllUp);
        CondStock conditionFlat = new CondStock();
        conditionFlat.setDate(date);
        conditionFlat.setF3(new BigDecimal("0"));
        Integer countAllFlat = StockService.count(conditionFlat);
        System.out.println("全市场个数-平盘："+countAllFlat);
        CondStock conditionDown = new CondStock();
        conditionDown.setDate(date);
        conditionDown.setMaxF3(new BigDecimal("0"));
        Integer countAllDown = StockService.count(conditionDown);
        System.out.println("全市场个数-下跌："+countAllDown);

        CondStock conditionUpMianBord = new CondStock();
        conditionUpMianBord.setDate(date);
        conditionUpMianBord.setMinF3(new BigDecimal("0"));
        conditionUpMianBord.setF139(2L);
        Integer countAllUpMianBord = StockService.count(conditionUpMianBord);
        System.out.println("主板个数-上涨："+countAllUpMianBord);
        CondStock conditionMianBordFlat = new CondStock();
        conditionMianBordFlat.setDate(date);
        conditionMianBordFlat.setF3(new BigDecimal("0"));
        conditionMianBordFlat.setF139(2L);
        Integer countAllMianBordFlat = StockService.count(conditionMianBordFlat);
        System.out.println("主板个数-平盘："+countAllMianBordFlat);
        CondStock conditionMianBordDown = new CondStock();
        conditionMianBordDown.setDate(date);
        conditionMianBordDown.setMaxF3(new BigDecimal("0"));
        conditionMianBordDown.setF139(2L);
        Integer countAllMianBordDown = StockService.count(conditionMianBordDown);
        System.out.println("主板个数-下跌："+countAllMianBordDown);

        Fupan fupanRs = new Fupan();
        //where
        fupanRs.setCode(date);
        fupanRs.setPeriod("1");
        //setValue
        fupanRs.setCOUNT_ST_ALL(countAll);
        fupanRs.setCOUNT_ST_ALL_UP(countAllUp);
        fupanRs.setCOUNT_ST_ALL_DOWN(countAllDown);
        fupanRs.setCOUNT_ST_ALL_FLAT(countAllFlat);
        fupanRs.setCOUNT_ST_ZB_UP(countAllUpMianBord);
        fupanRs.setCOUNT_ST_ZB_DOWN(countAllMianBordDown);
        fupanRs.setCOUNT_ST_ZB_FLAT(countAllMianBordFlat);
        FuPanDao.updateDb(fupanRs);
    }



    /**
     * 检查我的持仓：超过均线、价格区间、今日涨跌
     *
     * @param date
     */
    public static void checkMaByMyPosition(String date) {
        int days = 3;
        List<AssetPositionDb> rs = FupanPositionDao.listMyPositionByDate(date);//我的持仓
        Map<String, AssetPositionDb> mapMyPosition = new HashMap<>();
        Map<String, String> mapStock = new HashMap<>();
        for (AssetPositionDb assetPositionDb : rs) {
            mapMyPosition.put(assetPositionDb.getZqdm(),assetPositionDb);
            mapStock.put(assetPositionDb.getZqdm(),assetPositionDb.getZqmc());
        }

        CondMa condMa = new CondMa();
        condMa.setDays(days);
        condMa.setDate(date);
        condMa.setSpDate(null);
        condMa.setShowPriceArea(true);//是否显示价格区间
        condMa.setKltList(Arrays.asList(KLT_5, KLT_15, KLT_30, KLT_60, KLT_101));//价格区间周期列表
        condMa.setShowUpMa(true);//是否显示-超过均线
        condMa.setShowDownMa(true);//是否显示-跌落均线
        condMa.setFindKline(true);//是否查询最新k线
        condMa.setShowFlowIn(false);//是否显示资金流入
        condMa.setOrderField(ORDER_FIELD_MY_ZXSZ);//ORDER_FIELD_NET_AREA_DAY_5 ORDER_FIELD_F3   ORDER_FIELD_MAXDOWN ORDER_FIELD_MINRISE`ORDER_FIELD_MY_ZXSZ
        condMa.setOrderDesc(false);//是否倒序
        condMa.setShowMyPosition(true);
        condMa.setMapStock(mapStock);

        System.out.println("我的持仓：");
        condMa.setMapMyPosition(mapMyPosition);
        KlineService.showStockMa(condMa);
    }

    /**
     * 查询资金流向，查询价格区间
     *
     * @param date
     */
    private static void checkFundFlowByMyPosition(String date) {
        List<AssetPositionDb> rs = FupanPositionDao.listMyPositionByDate(date);//我的持仓
        for (AssetPositionDb myPosition : rs) {
            String zqdm = myPosition.getZqdm();
//            if (zqdm.equals("754212")) {
//                System.out.println("特定zqdm：" + zqdm);
//            }
            FundFlowService.fundFlowHandler(zqdm, null);
            //净值
            System.out.println(StockUtil.handlerAvgLine("5日价格", KlineService.findNetMinMaxAvg(zqdm, MA_5, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
            System.out.println(StockUtil.handlerAvgLine("10日价格", KlineService.findNetMinMaxAvg(zqdm, MA_10, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
            System.out.println(StockUtil.handlerAvgLine("20日价格", KlineService.findNetMinMaxAvg(zqdm, MA_20, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
            System.out.println(StockUtil.handlerAvgLine("60日价格", KlineService.findNetMinMaxAvg(zqdm, MA_60, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
            System.out.println();
        }
    }



    /**
     * 查询我的仓位
     *
     * @param date
     */
    private static void listMyPosition(String date, String klt) {
        List<AssetPositionDb> rs = FupanPositionDao.listMyPositionByDate(date);
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
     * 保存或更新我的复盘和持仓
     * @param date 日期
     * @param klt 周期
     * @param dateType 日期类型
     * @param cookie 登录key
     */
    public static void insertOrUpdate(String date, String klt, String dateType, String cookie) {
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

//        String cookieHttp = getCookieHttp();//获取cookie

        if (updateDaPanKline) {
            //k线
            int count = 1;

//            FuPanDao.updateDb(findFupanPointByKline(cookie, HS_300_000300, count, klt, dateType, date));//沪深300
//            FuPanDao.updateDb(findFupanPointByKline(cookie, CYB_50_399673, count, klt, dateType, date));//创业板50
//            FuPanDao.updateDb(findFupanPointByKline(cookie, ZZ_500_000905, count, klt, dateType, date));//中证500
//            FuPanDao.updateDb(findFupanPointByKline(cookie, SH_50_000016, count, klt, dateType, date));//上证50
//            FuPanDao.updateDb(findFupanPointByKline(cookie, SHANG_HAI, count, klt, dateType, date));//上证
//            FuPanDao.updateDb(findFupanPointByKline(cookie, SHEN_ZHEN, count, klt, dateType, date));//深证成指
//            FuPanDao.updateDb(findFupanPointByKline(cookie, CYB, count, klt, dateType, date));//创业板
//            FuPanDao.updateDb(findFupanPointByKline(cookie, KCB_50, count, klt, dateType, date));
//            System.out.println();

//            for (int i = 0; i < 100; i++) {
//                date = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -i);
//                updateDb(findFupanPointByKline(cookie, KCB_50, count, klt, dateType, date));
//            }

            //k线-日线-行业指数
//            updateDb(findFupanPointByKline(cookie, BIZ_QUANSHANG, count, klt, dateType, date));

        }

        if (updateMyStock) {
            //显示股票每日收益
            Fupan FupanMyStock = MyPositionService.queryAssetByDfcfStock(cookie, dateType);
            FuPanDao.updateDb(FupanMyStock);
        }

        if (updateMyStockAssetPosition) {
            //更新-我的股票-资产持仓
            Fupan fupanMyStockAssetPosition = queryMyStockAssetPosition(cookie, dateType, date);

            FuPanDao.updateMyStockAssetPosition(fupanMyStockAssetPosition);
        }

        List<AssetPositionDb> assetPositionList = new ArrayList<>();
        //  从数据库中根据日期查询我的持仓盈亏
        if (findDbMyPositionByDate) {
            String findDate = date;//查询日期
            String period = "1";
            assetPositionList = findDbMyPositionByDate(findDate, period, dateType,cookie);
        }

        //  更新我的持仓盈亏明细
        if (updateDbFupanPositionByDate) {
            for (AssetPositionDb assetPosition : assetPositionList) {
                //插入-我的持仓明细
                //  不插入XX发债，7XXXXX开头的
                if (StockUtil.checkIsNewBond(assetPosition)) {
                    System.out.println("新发债券不插入我的持仓：" + assetPosition.getZqmc());
                    continue;
                }
                FupanPositionDao.deletePosition(assetPosition);
                FupanPositionDao.insertDbFupanPosition(assetPosition);//插入-我的持仓明细

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
            Fupan FupanMyFund = StockUtil.handlerFundByTtjj(amt, amt_fund, amt_fund_last, earn_fund, date, dateType);
            FuPanDao.updateDb(FupanMyFund);
        }

        updateStUpDownCount(date);//更新股票涨跌个数

    }

    /**
     * 获取cookie
     * @return
     */
    private static String getCookieHttp() {
        String cookie = "";
        String url = "https://jywg.18.cn/Search/GetFundsFlow?validatekey=ed8500c4-5784-4d16-a2d8-14641c8d59c3";

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("moneyType=").append("RMB");

//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
        String rs = HttpUtil.sendPost(url, urlParam.toString(), cookie);
        System.out.println("GetFundsFlow:" + rs);
        return cookie;
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
     * 查询-我的股票-资产持仓:从东财http
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

    /**
     * 查询我的持仓盈亏：从数据库中根据日期
     *
     * @param date
     * @param period
     * @param dateType
     * @param cookie
     * @return
     */
    private static List<AssetPositionDb> findDbMyPositionByDate(String date, String period, String dateType, String cookie) {
//        Fupan condition = new Fupan();
//        condition.setCode(date);
//        condition.setPeriod(period);
//        Fupan fupanDb = FuPanDao.findDbByDate(condition);

        String url = "https://jywg.18.cn/Com/queryAssetAndPositionV1?validatekey=734d22f9-364d-4460-bac6-e6df18953822&moneyType=RMB";
//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
        String assetPositionRs = HttpUtil.sendPost(url, new StringBuffer().toString(), cookie);
        System.out.println("queryAssetPositionRs:" + assetPositionRs);

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("moneyType=").append("RMB");

//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
         assetPositionRs = HttpUtil.sendPost(url, urlParam.toString(), cookie);
        System.out.println("queryAssetPositionRs:" + assetPositionRs);


        System.out.println("查询我的持仓:" + assetPositionRs);

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
     * 查询-主要大盘指数
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
