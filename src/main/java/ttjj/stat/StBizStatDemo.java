package ttjj.stat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.BizRankDao;
import ttjj.dao.RankStockCommpanyDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.db.StockAdrCount;
import ttjj.dto.*;
import ttjj.rank.BizRankDemo;
import ttjj.rank.StockDemo;
import ttjj.service.FundFlowService;
import ttjj.service.KlineService;
import ttjj.service.ReportService;
import ttjj.service.StockService;
import utils.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static utils.Content.*;

/**
 * 业务概念
 *
 * @author Administrator
 * @date 2022-02-25 10:41
 */
public class StBizStatDemo {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-05-11";
//        showGianNian(date);//显示概念涨幅排行榜

        List<BigDecimal> adrMinList = Arrays.asList(new BigDecimal("0"), new BigDecimal("1"), new BigDecimal("3"), new BigDecimal("5"), new BigDecimal("7"), new BigDecimal("9"));
        List<Integer> daysList = Arrays.asList(MA_60, MA_40, MA_20, MA_10, MA_5);
        String reportQuete = "";//业绩报表季度  2022Q1
        boolean isShowPriceArea = false;//是否显示价格区间
        long board = DB_RANK_BIZ_F19_BK_MAIN;
        BigDecimal mvLimit = NUM_YI_50;

        {
            //        String conceptions = "体外诊断";//医疗-新冠：新冠检测,新冠药物,体外诊断
            String conceptions = "鸿蒙概念";//科技-软件：华为欧拉,鸿蒙概念
//                String conceptions = "国资云概念";//科技-数字经济:数字货币,智慧政务,国资云概念,电子车牌,数据安全,eSIM,电子身份证,东数西算,ETC,VPN,数据中心,云计算,边缘计算,网络安全,华为昇腾,数字经济,跨境支付,移动支付,区块链,京东金融,       ["eSIM"]：5;
//                String conceptions = "华为欧拉";//科技-传媒：华为欧拉,广电,NFT概念,虚拟数字人,快手概念,手游概念,元宇宙概念,盲盒经济

//        String conceptions = "预制菜概念";//消费-食品: 转基因,代糖概念,社区团购,预制菜概念,超级品牌,调味品概念,水产养殖,鸡肉概念,猪肉概念,乳业,人造肉
//        String conceptions = "统一大市场";//消费-物流:统一大市场,快递概念,海洋经济,中俄贸易概念,RCEP概念,进口博览
//        String conceptions = "草甘膦";//资源-农业: 草甘膦,蝗虫防治,生态农业,农业种植,乡村振兴,宠物经济
//        String conceptions = "肝素概念";//医疗-中药：肝素概念,肝炎概念,长寿药,流感,独家药品,中药概念,超级真菌,阿兹海默,幽门螺杆菌概念
//        String conceptions = "青蒿素";//医疗-创新药：CRO ,青蒿素,CAR-T细胞疗法
//        String conceptions = "土地流转";//金融-房地产：地下管网,建筑节能,民爆概念,REITs概念,海绵城市,租售同权,赛马概念,装配建筑,工程机械概念,水利建设,京津冀,中超概念,
//        String conceptions = "动力电池回收";//科技-新能车：刀片电池,盐湖提锂,固态电池,动力电池回收,钠离子电池,锂电池,氟化工   ["盐湖提锂"];股票个数：18;["氟化工"]：21;
//        String conceptions = "磷化工";//资源-化工: 磷化工,钛白粉,氟化工,有机硅,化工原料,
//        String conceptions = "在线旅游";//消费-旅游:在线旅游,航空机场,盲盒经济,影视概念
//        String conceptions = "滨海新区";//地区板块:沪企改革,上海自贸,滨海新区

//        String conceptions = "上证50_";//指数:上证50_,HS300_,茅指数
        }
//        List<RankStockCommpanyDb> stList = StockService.listlikeConception(date, conceptions, board, mvLimit);//查询股票列表-根据概念
//        showAdrCount(date, stList, board, mvLimit, adrMinList, daysList, conceptions, reportQuete);//统计涨跌次数
//
//        Map<String, String> zqMap = new HashMap<>();
//        for (RankStockCommpanyDb stock : stList) {
//            zqMap.put(stock.getF12(), stock.getF14());
//        }
//        StockStatDemo.checkMaDemo(zqMap, date);

//        按板块查询
        List<RankBizDataDiff> bizList = StockService.listBiz(NUM_MAX_99);//查询主题排名by时间类型、显示个数
//        int limit = NUM_MAX_99;//限定个数
        int limit = 1;//限定个数
        int stBizCountTemp = 0;
        for (RankBizDataDiff rankBizDataDiff : bizList) {
            if (--limit < 0) {
                break;
            }
            String biz = "银行";//银行  航空机场    证券
//            String biz = rankBizDataDiff.getF14();
            System.out.println("-------------------------当前stBizCountTemp：" + (++stBizCountTemp) + "---" + biz);
            List<RankStockCommpanyDb> stList = StockService.findListByCondition(biz, date, board, mvLimit);//查询股票列表-根据板块：
            List<StockAdrCount> stockAdrCountList = showAdrCount(date, stList, board, mvLimit, adrMinList, daysList, biz, reportQuete, isShowPriceArea);//统计涨跌次数
//            System.out.println("插入成功-涨幅次数统计：" + StockAdrCountService.insertListOrUpdate(stockAdrCountList));

            Map<String, String> zqMap = new HashMap<>();
            for (RankStockCommpanyDb stock : stList) {
                zqMap.put(stock.getF12(), stock.getF14());
            }
            StockStatDemo.checkMaDemo(zqMap, date);
        }


//        int year = DateUtil.getCurYear();//DateUtil.getCurYear() 2021
//        int month = DateUtil.getCurMonth();//DateUtil.getCurMonth()   12
//        int day = 1;//DateUtil.getCurDay()   27
//        statEtfAdrDb(etfBizSet, year, month, day, 18);//统计涨跌次数-按照天的维度

        //检查资金流向-etf
//        checkFundFlowByEtf(date);

//        //        // 统计涨跌次数-根据每月中的日期
//        String zqmc = "159949";//512800 510050:上证50ETF  512000:券商ETF
//        String begDate = "2020-01-01";//开始时间
//        String endDate = date;//DateUtil.getToday(DateUtil.YYYY_MM_DD)
//        statAdrCountByDay(zqmc, begDate, endDate);

    }


    /**
     * 检查均线
     *
     * @param date
     * @param isUp
     */
    private static void maCheck(String date, boolean isUp) {
        Map<String, String> etfBizMap = new HashMap<>();
        List<Integer> maList = new ArrayList<>();
//        maList.add(MA_30);
        maList.add(MA_60);
        etfBizMap = ContentEtf.mapEtfAll;//mapEtfBiz mapEtfIndex    mapEtfAll
        if (isUp) {
//        checkMaDemo(etfBizMap, date, true, maList, KLT_5);//    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
            checkMaDemo(etfBizMap, date, true, maList, KLT_15);//    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
            checkMaDemo(etfBizMap, date, true, maList, KLT_30);//    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
            checkMaDemo(etfBizMap, date, true, maList, KLT_60);//    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
            checkMaDemo(etfBizMap, date, true, maList, KLT_101);//    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
        } else {
            checkMaDemo(etfBizMap, date, false, maList, KLT_15);//    检查均线:卖出信号
            checkMaDemo(etfBizMap, date, false, maList, KLT_30);//    检查均线:卖出信号
            checkMaDemo(etfBizMap, date, false, maList, KLT_60);//    检查均线:卖出信号
            checkMaDemo(etfBizMap, date, false, maList, KLT_101);//    检查均线:卖出信号
        }


    }

    /**
     * 统计涨跌次数-根据每月中的日期
     */
    private static void statAdrCountByDay(String zqdm, String begDate, String endDate) {
        String klt = KLT_101;
        List<String> stCodeList = new ArrayList<>();
        stCodeList.add(zqdm);
        BigDecimal adrMin = new BigDecimal("0");

        StatCondStAdrCountBiz condition = new StatCondStAdrCountBiz();//查询条件
        condition.setKlt(klt);
        condition.setType(DB_RANK_BIZ_TYPE_ETF);
        condition.setBegDate(begDate);
        condition.setEndDate(endDate);
        condition.setStCodeList(stCodeList);

        String zqmc = "";
        List<StatRsStAdrCountBiz> rsAll = BizRankDao.findListStatStAdrCount(condition); //  查询-涨跌次数-所有
        for (StatRsStAdrCountBiz statRsStAdrCountBiz : rsAll) {
            zqmc = statRsStAdrCountBiz.getName();
//            System.out.println(JSON.toJSONString(r));
        }
        condition.setAdrMin(adrMin);
        List<StatRsStAdrCountBiz> rsGt0 = BizRankDao.findListStatStAdrCount(condition); //  查询-涨跌次数-涨跌大于n
        for (StatRsStAdrCountBiz r : rsGt0) {
//            System.out.println(JSON.toJSONString(r));
        }

        System.out.println(zqdm + ":" + zqmc);

        List<String> days = new ArrayList<>();

        days = Arrays.asList("01,02,03,04,05".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("06,07,08,09,10".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("06".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("07".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("08".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("09".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("10".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("11".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("12".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("13".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("14".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("15".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("11,12,13,14,15".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("16,17,18,19,20".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("21,22,23,24,25".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("26,27,28,29,30".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("31".split(","));
        staticsPct(rsGt0, rsAll, days);

    }

    /**
     * 统计百分比-涨跌次数
     *
     * @param rsGt0
     * @param rsAll
     * @param days
     */
    private static BigDecimal staticsPct(List<StatRsStAdrCountBiz> rsGt0, List<StatRsStAdrCountBiz> rsAll, List<String> days) {
        Map<String, BigDecimal> mapRsGt0 = new HashMap();
        Map<String, BigDecimal> mapRsAll = new HashMap();
        for (StatRsStAdrCountBiz rsGt0Temp : rsGt0) {
            mapRsGt0.put(rsGt0Temp.getRsDate(), rsGt0Temp.getCount());//大于n的次数s
        }
        for (StatRsStAdrCountBiz rsTemp : rsAll) {
            mapRsAll.put(rsTemp.getRsDate(), rsTemp.getCount());//总次数
        }

        List<StatRsStAdrCountKline> rs = new ArrayList<>();
        for (String day : days) {
            StatRsStAdrCountKline statRsStAdrCountKline = new StatRsStAdrCountKline();
            BigDecimal countGt = mapRsGt0.get(day);
            BigDecimal countAll = (mapRsAll.get(day));
            statRsStAdrCountKline.setCountGt(countGt);
            statRsStAdrCountKline.setCountAll(countAll);
            BigDecimal pct = countGt.divide(countAll, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            statRsStAdrCountKline.setPctCount(pct);
            statRsStAdrCountKline.setRsDate(day);
            rs.add(statRsStAdrCountKline);
        }
        BigDecimal conutGtSum = new BigDecimal("0");
        BigDecimal conutAllSum = new BigDecimal("0");
        for (StatRsStAdrCountKline r : rs) {
//            System.out.println(JSON.toJSONString(r));
            conutGtSum = conutGtSum.add(r.getCountGt());
            conutAllSum = conutAllSum.add(r.getCountAll());
        }
        BigDecimal pct = conutGtSum.divide(conutAllSum, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP);
        System.out.println("日期：" + JSON.toJSONString(days) + "，上涨概率:" + pct);

        return pct;
    }

    /**
     * 统计涨跌次数
     *
     * @param date
     * @param stListLikeConception
     * @param daysList
     * @param conpetions
     * @param isShowPriceArea
     */
    public static List<StockAdrCount> showAdrCount(String date, List<RankStockCommpanyDb> stListLikeConception, Long board, BigDecimal mvMin, List<BigDecimal> adrMinList, List<Integer> daysList, String conpetions, String reportQuete, boolean isShowPriceArea) {
        List<StockAdrCount> stockAdrCountList = new ArrayList<>();
        Map<String, StockAdrCount> statRsStAdrCountMap = new HashMap<>();
        ExecutorService service = Executors.newCachedThreadPool();// 创建一个的线程池
        for (BigDecimal adrMinTemp : adrMinList) {
            //涨幅超过
            for (Integer days : daysList) {
                service.execute(() -> {
                    statStAdrCount(stListLikeConception, date, days, adrMinTemp, board, mvMin, statRsStAdrCountMap);//统计次数：90
                });
            }
        }

        // 等待子线程结束，再继续执行下面的代码
        service.shutdown();
        while (true) {
            if (service.isTerminated()) {
                System.out.println("service-thread-ok!" + "线程池状态：" + service);
                break;
            }
            try {
//                System.out.println("线程池状态：" + service);
//                service.awaitTermination(20, TimeUnit.SECONDS);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<StockAdrCount> statRsStAdrCountList = new ArrayList<>();
        for (String code : statRsStAdrCountMap.keySet()) {
            StockAdrCount statRsStAdrCount = statRsStAdrCountMap.get(code);
            statRsStAdrCountList.add(statRsStAdrCount);
        }
        statRsStAdrCountList = statRsStAdrCountList.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCount::getADR_UP_COUNT_SUM_60, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());

        Map<String, RankStockCommpanyDb> stDbMap = new HashMap<>();
        for (RankStockCommpanyDb rankStockCommpanyDb : stListLikeConception) {
            stDbMap.put(rankStockCommpanyDb.getF12(), rankStockCommpanyDb);
//                System.out.println(rankStockCommpanyDb.getF12()+":"+rankStockCommpanyDb.getF14()+":"+rankStockCommpanyDb.getF3());
        }
////            checkMaDemo(stMap, date, true, maList, KLT_15);//    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
////            checkMaDemo(stMap, date, true, maList, KLT_30);//    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
////            checkMaDemo(stMap, date, true, maList, KLT_60);//    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
////            checkMaDemo(stMap, date, true, maList, KLT_101);//    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101

        System.out.println("[" + JSON.toJSONString(conpetions) + "]：" + stListLikeConception.size() + ";");
        BigDecimal orderNum = new BigDecimal("0");
        for (StockAdrCount statRsStAdrCount : statRsStAdrCountList) {
            StringBuffer sb = new StringBuffer();
//                System.out.println(JSON.toJSONString(statRsStAdrCount));
            String zqdm = statRsStAdrCount.getF12();
            BigDecimal stAdrCount = statRsStAdrCount.getADR_UP_COUNT_SUM_60();

            RankStockCommpanyDb rankStockCommpanyDb = stDbMap.get(zqdm);
            String biz = StockUtil.formatBizName(rankStockCommpanyDb.getType_name());
            String adr = StockUtil.formatDouble(rankStockCommpanyDb.getF3());
            String liangBi = StockUtil.formatDouble(rankStockCommpanyDb.getF10());
            String stName = StockUtil.handlerStName(rankStockCommpanyDb.getF14());
            Double curPrice = rankStockCommpanyDb.getF2();
            BigDecimal marketValue = null;
            if (rankStockCommpanyDb.getF20() != null) {
                marketValue = rankStockCommpanyDb.getF20().divide(new BigDecimal("100000000"), 2, BigDecimal.ROUND_HALF_UP);
            }

//            map.put("002432", "");//002432	九安医疗	医疗器械
            String concepPinYin = "mapGn";
            if (ConceptionUtil.stConceptionMap.get(conpetions) != null) {
                concepPinYin = ConceptionUtil.stConceptionMap.get(conpetions);
            }
            System.out.print(concepPinYin + ".put(\"" + zqdm + "\", \"" + stName + "\");//");//map  map.put("002432", "");//002432	九安医疗	医疗器械
//            sb.append(stCode).append("\t");
//            sb.append(stName).append("\t");
            sb.append(stAdrCount).append("\t");
            sb.append(biz).append(" ");
            sb.append(adr).append("\t");
            sb.append(marketValue).append("\t");
            sb.append(liangBi).append("\t");
            System.out.print(sb);
//                System.out.println("mapTemp.put(\"" + stCode + "\", \"" + stName.replace(" ","") + "\");//" + stCode + " " + stName + " " + stAdrCount + " " + biz + " ");

            RankStockCommpanyDb stock = new RankStockCommpanyDb();
            stock.setF12(statRsStAdrCount.getF12());
            StringBuffer maSb = new StringBuffer();
            if (isShowPriceArea) {
                Map<String, Boolean> maUpdateMap = new HashMap<>();
                StockDemo.setMaMapType(MA_TYPE_DAY, maUpdateMap);
                StringBuffer sbPriceArea = new StringBuffer();
                StockDemo.handlerNetMa(stock, maUpdateMap, date, sbPriceArea, new StockAdrCountVo());//处理均线净值
//            maSb.append("\t").append(",当前价：").append(curPrice);
//            maSb.append("\t").append(",当前：").append(DateUtil.getToday(DateUtil.YYYY_MM_DD_HH_MM_SS));
                System.out.println("价格区间:" + sbPriceArea.toString());
            }
            if (StringUtils.isNotBlank(reportQuete)) {
                handlerReport(maSb, stock, reportQuete);
            }
            System.out.println();

            statRsStAdrCount.setType_name(rankStockCommpanyDb.getType_name());
            statRsStAdrCount.setConception(rankStockCommpanyDb.getConception());
            statRsStAdrCount.setDate(date);
            if (rankStockCommpanyDb.getF2() != null) {
                statRsStAdrCount.setF2(new BigDecimal(rankStockCommpanyDb.getF2()));
            }
            statRsStAdrCount.setF3(rankStockCommpanyDb.getF3());
            if (rankStockCommpanyDb.getF4() != null) {
                statRsStAdrCount.setF4(new BigDecimal(rankStockCommpanyDb.getF4()));
            }
            statRsStAdrCount.setF5(rankStockCommpanyDb.getF5());
            statRsStAdrCount.setF6(rankStockCommpanyDb.getF6());
            if (rankStockCommpanyDb.getF7() != null) {
                statRsStAdrCount.setF7(new BigDecimal(rankStockCommpanyDb.getF7()));
            }
            if (rankStockCommpanyDb.getF8() != null) {
                statRsStAdrCount.setF8(new BigDecimal(rankStockCommpanyDb.getF8()));
            }
            if (rankStockCommpanyDb.getF9() != null) {
                statRsStAdrCount.setF9(new BigDecimal(rankStockCommpanyDb.getF9()));
            }
            statRsStAdrCount.setF10(rankStockCommpanyDb.getF10());
            statRsStAdrCount.setF12(rankStockCommpanyDb.getF12());
            statRsStAdrCount.setF14(rankStockCommpanyDb.getF14());
            if (rankStockCommpanyDb.getF15() != null) {
                statRsStAdrCount.setF15(new BigDecimal(rankStockCommpanyDb.getF15().toString()));
            }
            if (rankStockCommpanyDb.getF16() != null) {
                statRsStAdrCount.setF16(new BigDecimal(rankStockCommpanyDb.getF16().toString()));
            }
            if (rankStockCommpanyDb.getF17() != null) {
                statRsStAdrCount.setF17(new BigDecimal(rankStockCommpanyDb.getF17().toString()));
            }
            if (rankStockCommpanyDb.getF18() != null) {
                statRsStAdrCount.setF18(new BigDecimal(rankStockCommpanyDb.getF18().toString()));
            }
            statRsStAdrCount.setF20(rankStockCommpanyDb.getF20());
            statRsStAdrCount.setF21(rankStockCommpanyDb.getF21());

//            //处理价格区间
//            statRsStAdrCount.setNET_AREA_DAY_5(KlineService.handlerPriceAreaRate(zqdm, MA_5, KLT_101, false, "", date, KLINE_TYPE_STOCK));
//            statRsStAdrCount.setNET_AREA_DAY_10(KlineService.handlerPriceAreaRate(zqdm, MA_10, KLT_101, false, "", date, KLINE_TYPE_STOCK));
//            statRsStAdrCount.setNET_AREA_DAY_20(KlineService.handlerPriceAreaRate(zqdm, MA_20, KLT_101, false, "", date, KLINE_TYPE_STOCK));
//            statRsStAdrCount.setNET_AREA_DAY_40(KlineService.handlerPriceAreaRate(zqdm, MA_40, KLT_101, false, "", date, KLINE_TYPE_STOCK));
//            statRsStAdrCount.setNET_AREA_DAY_60(KlineService.handlerPriceAreaRate(zqdm, MA_60, KLT_101, false, "", date, KLINE_TYPE_STOCK));
//            statRsStAdrCount.setNET_AREA_DAY_120(KlineService.handlerPriceAreaRate(zqdm, MA_120, KLT_101, false, "", date, KLINE_TYPE_STOCK));
//            statRsStAdrCount.setNET_AREA_DAY_250(KlineService.handlerPriceAreaRate(zqdm, MA_250, KLT_101, false, "", date, KLINE_TYPE_STOCK));

            orderNum = orderNum.add(new BigDecimal("1"));
            statRsStAdrCount.setOrder_num(orderNum);

            stockAdrCountList.add(statRsStAdrCount);
        }
        return stockAdrCountList;
    }

    /**
     * 处理业绩报表
     *
     * @param maSb
     * @param stock
     * @param reportQuete
     */
    private static void handlerReport(StringBuffer maSb, RankStockCommpanyDb stock, String reportQuete) {
        Report condition = new Report();
        condition.setSECURITY_CODE(stock.getF12());
        condition.setQDATE(reportQuete);
        Report report = ReportService.findByCondition(condition);
        if (report != null) {
            BigDecimal PARENT_NETPROFIT = report.getPARENT_NETPROFIT() != null ? report.getPARENT_NETPROFIT().divide(new BigDecimal("100000000")).setScale(2, RoundingMode.HALF_UP) : null;//净利亿
            BigDecimal SJLHZ = report.getSJLHZ() != null ? report.getSJLHZ().setScale(2, RoundingMode.HALF_UP) : null;//净利环增
            BigDecimal SJLTZ = report.getSJLTZ() != null ? report.getSJLTZ().setScale(2, RoundingMode.HALF_UP) : null;//净利同增
            BigDecimal YSHZ = report.getYSHZ() != null ? report.getYSHZ().setScale(2, RoundingMode.HALF_UP) : null;//营收环增
            BigDecimal YSTZ = report.getYSTZ() != null ? report.getYSTZ().setScale(2, RoundingMode.HALF_UP) : null;//营收同增
            maSb.append("净利环:").append(SJLHZ).append(";净利同:").append(SJLTZ).append(";营收环:").append(YSHZ).append(";营收同:").append(YSTZ).append(";").append("净利:").append(PARENT_NETPROFIT).append(";");
        } else {
            maSb.append("业绩报表为空");
        }
    }


    /**
     * 统计涨跌次数
     */
    private static void statStAdrCount(List<RankStockCommpanyDb> stListLikeConception, String endDate, Integer days, BigDecimal adrMin, Long bk, BigDecimal mvMin, Map<String, StockAdrCount> statRsStAdrCountMap) {
        List<String> stCodeList = new ArrayList<>();
        if (stListLikeConception == null || stListLikeConception.size() <= 0) {
            System.out.println(JSON.toJSONString(stListLikeConception) + ":查询股票列表为空！");
//            return statRsStAdrCountMap;
        }
        for (RankStockCommpanyDb rankStockCommpanyDb : stListLikeConception) {
            stCodeList.add(rankStockCommpanyDb.getF12());
        }

        //查询n个交易日之前的日期,如果不存在，使用减去自然日
        String begDate = findBegDate(endDate, days);

        StatCondStAdrCount condition = new StatCondStAdrCount();
        condition.setF139(bk);
        condition.setMarketValueMin(mvMin);//市值
        condition.setAdrMin(adrMin);
        condition.setBegDate(begDate);
        condition.setEndDate(endDate);

        condition.setStCodeList(stCodeList);
        List<StatRsStAdrCount> rs = RankStockCommpanyDao.findListStatStAdrCount(condition); //  查询-股票涨跌次数
        for (StatRsStAdrCount stAdrCount : rs) {
            String code = stAdrCount.getCode();

//            BigDecimal score = adrMin.multiply(stAdrCount.getCount());//涨幅得分=上涨幅度*次数
            BigDecimal score = stAdrCount.getCount();//涨幅得分=上涨次数
//            System.out.println("days:" + days + ",adrMin:" + adrMin + "=" + JSON.toJSONString(stAdrCount));
//            if ("中国神华".equals(stAdrCount.getName())) {
//                System.out.println(stAdrCount.getCode() + ":" + stAdrCount.getName() + ":" + ",天数：" + days + ",上涨次数：" + stAdrCount.getCount() + ",涨幅标准：" + adrMin + ",上涨得分：" + score);
//            }
            if (statRsStAdrCountMap.containsKey(code)) {
                StockAdrCount stMapDtoOld = statRsStAdrCountMap.get(code);
                BigDecimal countOld = stMapDtoOld.getADR_UP_COUNT_SUM_60();
                stMapDtoOld.setADR_UP_COUNT_SUM_60(countOld.add(score));

                if (days == MA_5) {
                    BigDecimal countOldTemp = stMapDtoOld.getADR_UP_COUNT_5();
                    if (countOldTemp != null) {
                        stMapDtoOld.setADR_UP_COUNT_5(countOldTemp.add(score));
                    } else {
                        stMapDtoOld.setADR_UP_COUNT_5(score != null ? score : new BigDecimal("0"));
                    }
                }
                if (days == MA_10) {
                    BigDecimal countOldTemp = stMapDtoOld.getADR_UP_COUNT_10();
                    if (countOldTemp != null) {
                        stMapDtoOld.setADR_UP_COUNT_10(countOldTemp.add(score));
                    } else {
                        stMapDtoOld.setADR_UP_COUNT_10(score != null ? score : new BigDecimal("0"));
                    }
                }
                if (days == MA_20) {
                    BigDecimal countOldTemp = stMapDtoOld.getADR_UP_COUNT_20();
                    if (countOldTemp != null) {
                        stMapDtoOld.setADR_UP_COUNT_20(countOldTemp.add(score));
                    } else {
                        stMapDtoOld.setADR_UP_COUNT_20(score != null ? score : new BigDecimal("0"));
                    }
                }
                if (days == MA_40) {
                    BigDecimal countOldTemp = stMapDtoOld.getADR_UP_COUNT_40();
                    if (countOldTemp != null) {
                        stMapDtoOld.setADR_UP_COUNT_40(countOldTemp.add(score));
                    } else {
                        stMapDtoOld.setADR_UP_COUNT_40(score != null ? score : new BigDecimal("0"));
                    }
                }
                if (days == MA_60) {
                    BigDecimal countOldTemp = stMapDtoOld.getADR_UP_COUNT_60();
                    if (countOldTemp != null) {
                        stMapDtoOld.setADR_UP_COUNT_60(countOldTemp.add(score));
                    } else {
                        stMapDtoOld.setADR_UP_COUNT_60(score != null ? score : new BigDecimal("0"));
                    }
                }
                statRsStAdrCountMap.put(code, stMapDtoOld);
            } else {
                StockAdrCount stockAdrCount = new StockAdrCount();
                stockAdrCount.setF12(code);
                stockAdrCount.setADR_UP_COUNT_SUM_60(score);
                //-120, -90,-60, -30, -14, -7
                if (days == MA_5) {
                    stockAdrCount.setADR_UP_COUNT_5(score);
                }
                if (days == MA_10) {
                    stockAdrCount.setADR_UP_COUNT_10(score);
                }
                if (days == MA_20) {
                    stockAdrCount.setADR_UP_COUNT_20(score);
                }
                if (days == MA_40) {
                    stockAdrCount.setADR_UP_COUNT_40(score);
                }
                if (days == MA_60) {
                    stockAdrCount.setADR_UP_COUNT_60(score);
                }
                statRsStAdrCountMap.put(code, stockAdrCount);
            }
        }
//        return statRsStAdrCountMap;
    }

    /**
     * 查询n个交易日之前的日期,日过不存在，使用指定日期减去自然日的日期
     *
     * @param endDate 结束日期
     * @param days    n天之前
     * @return 计算结果日期
     */
    private static String findBegDate(String endDate, Integer days) {
        List<String> dateList = StockService.findListDateBefore(endDate, days);//查询n个交易日之前的日期
        if (dateList != null) {
//            System.out.println("findBegDate.：" + dateList.get(days - 1));
            return dateList.get(days - 1);
        } else {
            System.out.println("查询日期错误.：使用指定日期减去自然日的日期" + JSON.toJSONString(dateList));
            return DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, days);
        }
    }

    /**
     * 显示概念涨幅排行榜
     */
    private static void showGianNian(String date) {
        List<RankBizDataDiff> rankList = BizRankDemo.listConcept(date, DB_RANK_BIZ_TYPE_GAI_NIAN, NUM_MAX_999);//查询主题排名by时间类型、显示个数
        System.out.println("排行榜-概念：");
        for (RankBizDataDiff etf : rankList) {
            System.out.print(etf.getF14() + ",");
//            System.out.print(etf.getF14() + ":" + etf.getF3());
        }
        System.out.println();
    }

    /**
     * 检查均线
     *
     * @param etfBizMap
     * @param date
     * @param isUp
     * @param maList
     * @param kltType
     */
    public static void checkMaDemo(Map<String, String> etfBizMap, String date, boolean isUp, List<Integer> maList, String kltType) {
//        Map<String, String> etfBizMap = ContentEtf.mapEtfAll;//mapEtfBiz mapEtfIndex    mapEtfAll
//        List<RankBizDataDiff> rankEtf = listEtf(date, DB_RANK_BIZ_TYPE_ETF, NUM_MAX_999);//
//        for (RankBizDataDiff etf : rankEtf) {
//            etfBizMap.put(etf.getF12(), etf.getF14());
//        }

        KlineService.checkMa(etfBizMap, kltType, maList, date, isUp, null, true);// 检查均线
    }


    /**
     * 统计涨跌次数-按照天的维度
     *
     * @param etfBizSet
     * @param days
     * @return
     */
    private static Map<String, StatEtfUpDown> statEtfAdrDb(Set<String> etfBizSet, int year, int month, int day, int days) {
        Map<String, StatEtfUpDown> statRs = new HashMap<>();
        List<StatEtfUpDown> statEtfUpDownList = new ArrayList<>();
        //按照日期，倒序查询
        for (int i = 0; i <= days; i++) {
            String date = DateUtil.getDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, year, month, day, i);//查询新增交易的开始时间
            Map<String, Object> condition = new HashMap<>();
            condition.put("list", etfBizSet);
            condition.put("date", date);
            List<RankBizDataDiff> rankListUp = BizRankDao.listEtfBiz(condition);
            if (rankListUp == null) {
                continue;
            }
            for (RankBizDataDiff biz : rankListUp) {
                if (rankListUp == null) {
                    return null;
                }
                String code = biz.getF12();
                StatEtfUpDown statEtfUpDown = new StatEtfUpDown();
                if (statRs.containsKey(code)) {
                    statEtfUpDown = statRs.get(code);
                }
                statEtfUpDown.setCode(biz.getF12());
                statEtfUpDown.setName(BizEtfControl.handlerEtfName(biz.getF14()));
                int oldCountCurContinueUp = statEtfUpDown.getCountCurContinueUp();
                int oldCountCurContinueDown = statEtfUpDown.getCountCurContinueDown();
                int oldCountTotalUp = statEtfUpDown.getCountTotalUp();
                int oldCountTotalDown = statEtfUpDown.getCountTotalDown();
                //  当前连续次数合计-上涨:如果上涨，次数加，否则次数重置为0；下跌次数反之
                if (biz.getF3().compareTo(new BigDecimal("0")) > 0) {
                    statEtfUpDown.setCountCurContinueUp(oldCountCurContinueUp + 1);
                    statEtfUpDown.setCountCurContinueDown(0);
                    statEtfUpDown.setCountTotalUp(oldCountTotalUp + 1);
                } else {
                    statEtfUpDown.setCountCurContinueDown(oldCountCurContinueDown + 1);
                    statEtfUpDown.setCountCurContinueUp(0);
                    statEtfUpDown.setCountTotalDown(oldCountTotalDown + 1);
                }
                statRs.put(code, statEtfUpDown);
            }
        }

        statEtfUpDownList.addAll(statRs.values());
        //排序
        statEtfUpDownList = statEtfUpDownList.stream().filter(e -> e != null).sorted(Comparator.comparing(StatEtfUpDown::getCountTotalUp, Comparator.nullsFirst(Integer::compareTo)).reversed()).collect(Collectors.toList());
        System.out.println();
        for (StatEtfUpDown dto : statEtfUpDownList) {
            String name = dto.getName();
            System.out.print(dto.getCode());
            System.out.print("\t累计-涨跌比:" + dto.getCountTotalUp() + ":" + dto.getCountTotalDown());
            System.out.print(" \t当前连续次数合计-涨跌比:" + dto.getCountCurContinueUp() + ":" + dto.getCountCurContinueDown());
            System.out.print("\t");
            if (name.length() < 4) {
                System.out.print(dto.getName());
            } else {
                System.out.print(dto.getName());
            }
            System.out.println();
        }
        return statRs;
    }


    /**
     * 检查资金流向-etf
     *
     * @param date
     */
    private static void checkFundFlowByEtf(String date) {
        List<RankBizDataDiff> etfList = StBizStatDemo.listEtf(date, KLINE_TYPE_ETF, NUM_MAX_999);//2021-04-16:425;
        for (RankBizDataDiff etf : etfList) {
            //限定总市值10亿
            if (etf.getF20().compareTo(new BigDecimal("1000000000")) > 0) {
                FundFlowService.fundFlowHandler(etf.getF12(), null);
            }
        }
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
        Double rsNetCloseMin = 0.0;
        Double rsNetCloseMax = 0.0;
        int curTempInt = 0;
        for (String klineStr : klineList) {
            //  日期，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率
            //"2020-09-30,3389.74,3218.05,3425.63,3202.34,4906229054,6193724911616.00,6.58,-5.23,-177.63,13.40"
            String[] klineArray = klineStr.split(",");
            String shouPan = klineArray[2];
            String netMax = klineArray[3];
            String netMin = klineArray[4];
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

            Double netMaxDou = Double.valueOf(netMax);
            if (netMaxDou > rsMax) {
                rsMax = netMaxDou;
            }
            Double netMinDou = Double.valueOf(netMin);
            if (netMinDou < rsMin || rsMin == 0.0) {
                rsMin = netMinDou;
            }

            //
            Double dwjzLong = Double.valueOf(dwJz);
            if (dwjzLong > rsNetCloseMax) {
                rsNetCloseMax = dwjzLong;
            }
            if (dwjzLong < rsNetCloseMin || rsNetCloseMin == 0.0) {
                rsNetCloseMin = dwjzLong;
            }
        }
        rs.put(Content.keyRsMax, rsMax);
        rs.put(Content.keyRsMin, rsMin);
        rs.put(Content.keyRsNetCloseMin, rsNetCloseMin);
        rs.put(Content.keyRsNetCloseMax, rsNetCloseMax);
        return rs;
    }


    public static List<RankBizDataDiff> listEtf(String date, String type, int pageSize) {
//          http://32.push2.eastmoney.com/api/qt/clist/get?cb=jQuery11240476946102335426_1618637035810&pn=1&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=b:MK0021,b:MK0022,b:MK0023,b:MK0024&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f128,f136,f115,f152&_=1618637035811
        String url = "http://32.push2.eastmoney.com/api/qt/clist/get";
        StringBuffer urlParam = new StringBuffer();
        long curTime = System.currentTimeMillis();
        urlParam.append("cb=jQuery11240476946102335426_" + curTime +
                "&pn=1" +//页数
                "&pz=" + pageSize +//每页数量
                "&po=1" +//pageorder:页面排序：0-正序；1-倒序
                "&np=1" +//是否分页：1-分页
                "&ut=bd1d9ddb04089700cf9c27f6f7426281" +
                "&fltt=2" +//浮点数精度
                "&invt=3" +//显示格式：2-"-"；3-"0.0"
                "&fid=f3" +//排序字段
                "&fs=b:MK0021,b:MK0022,b:MK0023,b:MK0024" +
                "&fields=" +
                "f1,f2,f3,f4,f5,f6,f7,f8,f9," +
                "f10,f11,f12,f13,f14,f15,f16,f17,f18,f19," +
                "f20,f21,f22,f23,f24,f25,f26,f27,f28,f29," +
                "f30,f31,f32,f33,f34,f35,f36,f37,f38,f39," +
                "f40,f41,f42,f43,f44,f45,f46,f47,f48,f49," +
                "f50,f51,f52,f53,f54,f55,f56,f57,f58,f59," +
                "f60,f61,f62,f63,f64,f65,f66,f67,f68,f69," +
                "f70,f71,f72,f73,f74,f75,f76,f77,f78,f79," +
                "f80,f81,f82,f83,f84,f85,f86,f87,f88,f89," +
                "f90,f91,f92,f93,f94,f95,f96,f97,f98,f99," +
                "f100,f101,f102,f103,f104,f105,f106,f107,f108,f109" + "," +
                "f110,f111,f112,f113,f114,f115,f116,f117,f118,f119" + "," +
                "f120,f121,f122,f123,f124,f125,f126,f127,f128,f129" + "," +
                "f130,f131,f132,f133,f134,f135,f136,f137,f138,f139" + "," +
                "f140,f141,f142,f143,f144,f145,f146,f147,f148,f149" + "," +
                "f150,f151,f152,f153,f154,f155,f156,f157,f158,f159" + "," +
                "f160,f161,f162,f163,f164,f165,f166,f167,f168,f169" + "," +
                "f170,f171,f172,f173,f174,f175,f176,f177,f178,f179" + "," +
                "f180,f181,f182,f183,f184,f185,f186,f187,f188,f189" + "," +
                "f190,f191,f192,f193,f194,f195,f196,f197,f198,f199" + "," +
                "f200,f201,f202,f203,f204,f205,f206,f207,f208,f209" + "," +
                "f210,f211,f212,f213,f214,f215,f216,f217,f218,f219" + "," +
                "f220,f221,f222,f223,f224,f225,f226,f227,f228,f229" +
//                "f230,f231,f232,f233,f234,f235,f236,f237,f238,f239" +

//                "f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222" +

                "&_=" + curTime);
        String rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);
        if (rs == null) {
            return null;
        }
        if (rs.startsWith("jQuery")) {
            rs = rs.substring(rs.indexOf("{"));
        }
        if (rs.endsWith(");")) {
            rs = rs.substring(0, rs.length() - 2);
        }
//        System.out.println(rs);//返回结果
        JSONObject rsJsonObj = JSONObject.parseObject(rs);
        if (rsJsonObj == null || !rsJsonObj.containsKey("data")) {
            System.out.println("---------------data---error!!!!!");
            return null;
        }
        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
        List<RankBizDataDiff> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankBizDataDiff.class);
        for (RankBizDataDiff row : rankBizDataDiffList) {
//            row.setRs(rs);
            row.setDate(date);
            row.setMonth(DateUtil.getYearMonth(date, DateUtil.YYYY_MM_DD));
            row.setWeekYear(DateUtil.getYearWeek(date, DateUtil.YYYY_MM_DD));
            row.setWeek(DateUtil.getWeekByYyyyMmDd(date, DateUtil.YYYY_MM_DD));
            row.setType(type);
//            System.out.println(JSON.toJSON(row));//每个行业一行数据
        }

        return rankBizDataDiffList;
    }

}
