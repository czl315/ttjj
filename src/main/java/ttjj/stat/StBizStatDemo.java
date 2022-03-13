package ttjj.stat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.BizRankDao;
import ttjj.dao.RankStockCommpanyDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.*;
import ttjj.rank.BizRankDemo;
import ttjj.rank.StockDemo;
import ttjj.service.FundFlowService;
import ttjj.service.KlineService;
import utils.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.*;
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
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);//                        String date = "2022-02-23";
        showGianNian(date);//显示概念涨幅排行榜

        List<BigDecimal> adrMinList = Arrays.asList(new BigDecimal("1"), new BigDecimal("3"), new BigDecimal("5"), new BigDecimal("7"), new BigDecimal("9"));
//        String conceptions = "券商概念";//券商:
//        String conceptions = "在线旅游";//消费-旅游:在线旅游,免税概念,盲盒经济,退税商店,影视概念
//        String conceptions = "转基因,鸡肉概念,水产养殖,猪肉概念,预制菜概念,农业种植";//农业养殖：转基因,鸡肉概念,水产养殖,猪肉概念,预制菜概念,农业种植
//        String conceptions = "新冠检测";//新冠检测,新冠药物,体外诊断     重组蛋白,基因测序,超级真菌
        String conceptions = "动力电池回收";//新能源-锂电池：刀片电池,固态电池,钠离子电池,盐湖提锂,动力电池回收,锂电池,氟化工
//        String conceptions = "数字货币";//科技-数字经济:数字货币,跨境支付,数据安全,数字经济,区块链        互联金融,智慧政务,网络安全,
//        String conceptions = "辅助生殖,婴童概念,托育服务";//辅助生殖,婴童概念,托育服务
//        String conceptions = "海绵城市";//地下管网,水利建设,海绵城市,装配建筑
//        String conceptions = "可燃冰,页岩气,油气设服";//石油：可燃冰,页岩气,油气设服,天然气,油价相关       ,氦气概念
//        String conceptions = "太阳能";//科技-光伏: HIT电池,光伏建筑一体化      ,太阳能        ["太阳能"];股票个数：168;
//        String conceptions = "抽水蓄能";//绿色电力,抽水蓄能,风能
//        String conceptions = "CRO ,CAR-T细胞疗法,地塞米松";//CRO ,CAR-T细胞疗法,地塞米松,超级真菌
//        String conceptions = "东数西算";//东数西算,国资云概念,数据中心,华为昇腾,云计算,边缘计算,华为欧拉,智慧政务,网络安全,VPN
//        String conceptions = "虚拟数字人,NFT概念";//传媒：虚拟数字人,NFT概念,元宇宙概念,云游戏,盲盒经济,快手概念,广电,电子竞技,手游概念,网络游戏
//        String conceptions = "氟化工";//化学：氟化工
//        String conceptions = "中俄贸易概念";//中俄贸易概念
//        String conceptions = "IGBT概念";//光刻胶,中芯概念,IGBT概念,汽车芯片,第三代半导体,半导体概念
//        String conceptions = "白酒";//白酒
//        String conceptions = "商汤概念";//5日内：商汤概念,HIT电池,地塞米松,

        List<RankStockCommpanyDb> stListLikeConception = listlikeConception(date, conceptions, DB_RANK_BIZ_F139_BK_MAIN, NUM_YI_50, adrMinList);//查询股票列表-根据概念、涨幅列表
        showAdrCount(date, stListLikeConception, DB_RANK_BIZ_F139_BK_MAIN, NUM_YI_50, adrMinList, conceptions);//统计涨跌次数

        Map<String, String> zqMap = new HashMap<>();
        for (RankStockCommpanyDb stock : stListLikeConception) {
            zqMap.put(stock.getF12(), stock.getF14());
        }
//        StockStatDemo.checkMaDemo(zqMap);

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
     * 查询股票列表-根据概念、涨幅列表
     *
     * @param date
     * @param conceptions
     * @param board
     * @param mvMin
     * @param adrMinList
     * @return
     */
    private static List<RankStockCommpanyDb> listlikeConception(String date, String conceptions, long board, BigDecimal mvMin, List<BigDecimal> adrMinList) {
//        String conceptions = "贵金属";//贵金属: 黄金概念,培育钻石    贵金属
//        String conceptions = "基本金属";//基本金属
//        String conceptions = "水产养殖";//食品: 预制菜概念,水产养殖,鸡肉概念,猪肉概念     海洋经济        社区团购
//        String conceptions = "宠物经济";//养老概念,宠物经济
//        String conceptions = "鸡肉概念";//低碳冶金
//        String conceptions = "中药概念";//中药概念:114;
//                String conceptions = "EDR概念";//稀缺资源   稀土永磁
//        String conceptions = "航母概念,海工装备,军民融合,大飞机,通用航空,天基互联,航天概念,空间站概念,北斗导航";//军工: 航母概念,海工装备,军民融合,大飞机,通用航空,天基互联,航天概念,空间站概念,北斗导航,
//                        String conceptions = "工业母机";//
//        String conceptions = "杭州亚运会";//最新概念：土壤修复,智慧灯杆,净水概念,杭州亚运会
//        String conceptions = "全息技术,3D摄像头";//全息技术,3D摄像头
//        String conceptions = "磷化工";//磷化工
//                String conceptions = "民爆概念";//民爆概念
//                String conceptions = "在线教育";//在线教育
//        String conceptions = "REITs概念,民爆概念";//
        CondStLikeConception conditionLikeConception = new CondStLikeConception();
        conditionLikeConception.setDate(date);
        String[] conceptionStrs = conceptions.split(",");
        List<String> conpetionList = Arrays.asList(conceptionStrs);
        conditionLikeConception.setConpetionList(conpetionList);
        conditionLikeConception.setF139(board);
        conditionLikeConception.setF20(mvMin);
        List<RankStockCommpanyDb> stListLikeConception = RankStockCommpanyDao.findListLikeConception(conditionLikeConception);
//        System.out.println("概念：" + JSON.toJSONString(conpetionList) + ";" + "股票个数：" + stListLikeConception.size() + ";");
        return stListLikeConception;
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
     * @param conpetions
     */
    private static void showAdrCount(String date, List<RankStockCommpanyDb> stListLikeConception, Long board, BigDecimal mvMin, List<BigDecimal> adrMinList, String conpetions) {
        Map<String, StatRsStAdrCount> statRsStAdrCountMap = new HashMap<>();
        ExecutorService service = Executors.newCachedThreadPool();// 创建一个的线程池
        for (BigDecimal adrMinTemp : adrMinList) {
            //涨幅超过
            service.execute(() -> {
                statStAdrCount(stListLikeConception, DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -365), date, adrMinTemp, board, mvMin, statRsStAdrCountMap);//统计次数：365
            });
            service.execute(() -> {
                statStAdrCount(stListLikeConception, DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -180), date, adrMinTemp, board, mvMin, statRsStAdrCountMap);//统计次数：365
            });
            service.execute(() -> {
                statStAdrCount(stListLikeConception, DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -90), date, adrMinTemp, board, mvMin, statRsStAdrCountMap);//统计次数：90
            });
            service.execute(() -> {
                statStAdrCount(stListLikeConception, DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -30), date, adrMinTemp, board, mvMin, statRsStAdrCountMap);//统计次数：90
            });
            service.execute(() -> {
                statStAdrCount(stListLikeConception, DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -14), date, adrMinTemp, board, mvMin, statRsStAdrCountMap);//统计次数：90
            });
            service.execute(() -> {
                statStAdrCount(stListLikeConception, DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -7), date, adrMinTemp, board, mvMin, statRsStAdrCountMap);//统计次数：90
            });
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

        List<StatRsStAdrCount> statRsStAdrCountList = new ArrayList<>();
        for (String code : statRsStAdrCountMap.keySet()) {
            StatRsStAdrCount statRsStAdrCount = statRsStAdrCountMap.get(code);
            statRsStAdrCountList.add(statRsStAdrCount);
        }
        statRsStAdrCountList = statRsStAdrCountList.stream().filter(e -> e != null).sorted(Comparator.comparing(StatRsStAdrCount::getCount, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());

        Map<String, RankStockCommpanyDb> stDbMap = new HashMap<>();
        for (RankStockCommpanyDb rankStockCommpanyDb : stListLikeConception) {
            stDbMap.put(rankStockCommpanyDb.getF12(), rankStockCommpanyDb);
//                System.out.println(rankStockCommpanyDb.getF12()+":"+rankStockCommpanyDb.getF14()+":"+rankStockCommpanyDb.getF3());
        }
////            checkMaDemo(stMap, date, true, maList, KLT_15);//    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
////            checkMaDemo(stMap, date, true, maList, KLT_30);//    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
////            checkMaDemo(stMap, date, true, maList, KLT_60);//    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
////            checkMaDemo(stMap, date, true, maList, KLT_101);//    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101

        System.out.println("概念：[" + JSON.toJSONString(conpetions) + "];" + "股票个数：" + stListLikeConception.size() + ";");
        for (StatRsStAdrCount statRsStAdrCount : statRsStAdrCountList) {
            StringBuffer sb = new StringBuffer();
//                System.out.println(JSON.toJSONString(statRsStAdrCount));
            String stCode = statRsStAdrCount.getCode();
            BigDecimal stAdrCount = statRsStAdrCount.getCount();
            String biz = handlerStName(statRsStAdrCount.getType_name());

            RankStockCommpanyDb rankStockCommpanyDb = stDbMap.get(stCode);
            String adr = formatDouble(rankStockCommpanyDb.getF3());
            String liangBi = formatDouble(rankStockCommpanyDb.getF10());
            String stName = handlerStName(rankStockCommpanyDb.getF14());
            Double curPrice = rankStockCommpanyDb.getF2();
            BigDecimal marketValue = null;
            if (rankStockCommpanyDb.getF20() != null) {
                marketValue = rankStockCommpanyDb.getF20().divide(new BigDecimal("100000000"), 2, BigDecimal.ROUND_HALF_UP);
            }

//            map.put("002432", "");//002432	九安医疗	医疗器械
            System.out.print("map.put(\"" + stCode + "\", \"" + stName + "\");//");//map  map.put("002432", "");//002432	九安医疗	医疗器械
//            sb.append(stCode).append("\t");
//            sb.append(stName).append("\t");
            sb.append(stAdrCount).append("\t");
            sb.append(biz).append("\t");
            sb.append(adr).append("\t");
            sb.append(marketValue).append("\t");
            sb.append(liangBi).append("\t");
            System.out.print(sb);
//                System.out.println("mapTemp.put(\"" + stCode + "\", \"" + stName.replace(" ","") + "\");//" + stCode + " " + stName + " " + stAdrCount + " " + biz + " ");

            StringBuffer maSb = new StringBuffer();
            RankStockCommpanyDb stock = new RankStockCommpanyDb();
            stock.setF12(statRsStAdrCount.getCode());
            Map<String, Boolean> maUpdateMap = new HashMap<>();
            StockDemo.setMaMapType(MA_TYPE_DAY, maUpdateMap);
            StockDemo.handlerNetMa(stock, maUpdateMap, date, maSb);//处理均线净值
            maSb.append("\t").append(",当前价：").append(curPrice);
            maSb.append("\t").append(",当前：").append(DateUtil.getToday(DateUtil.YYYY_MM_DD_HH_MM_SS));
            System.out.println("价格区间:" + maSb.toString());
        }

//            System.out.println("}");

    }

    private static String formatDouble(BigDecimal f3) {
        if (f3 == null) {
            return null;
        }
        String f3Str = f3.toString();
        if (f3Str.length() == 3) {
            return f3Str + " ";
        }
        if (f3Str.length() <= 2) {
            return f3Str + "   ";
        }
        return f3Str;
    }

    /**
     * @param name
     */
    private static String handlerStName(String name) {
        if (name.length() <= 2) {
            name = name + "    ";
        }
        if (name.length() > 2 && name.length() < 4) {
            name = name + "  ";
        }
        if (name.startsWith("*ST")) {
            name = name + " ";
        }
        if (name.startsWith("ST")) {
            name = name + "  ";
        }
        return name;
    }

    /**
     * 统计涨跌次数
     */
    private static Map<String, StatRsStAdrCount> statStAdrCount(List<RankStockCommpanyDb> stListLikeConception, String begDate, String endDate, BigDecimal adrMin, Long bk, BigDecimal mvMin, Map<String, StatRsStAdrCount> statRsStAdrCountMap) {
        List<String> stCodeList = new ArrayList<>();
        if (stListLikeConception == null || stListLikeConception.size() <= 0) {
            System.out.println(JSON.toJSONString(stListLikeConception) + ":查询股票列表为空！");
            return statRsStAdrCountMap;
        }
        for (RankStockCommpanyDb rankStockCommpanyDb : stListLikeConception) {
            stCodeList.add(rankStockCommpanyDb.getF12());
        }

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
//            System.out.println(JSON.toJSONString(stAdrCount));
//            System.out.println(stAdrCount.getCode()+":"+stAdrCount.getName()+":"+stAdrCount.getCount());
            if (statRsStAdrCountMap.containsKey(code)) {
                StatRsStAdrCount stMapDtoOld = statRsStAdrCountMap.get(code);
                BigDecimal countOld = stMapDtoOld.getCount();
                stMapDtoOld.setCount(countOld.add(stAdrCount.getCount()));
                statRsStAdrCountMap.put(code, stMapDtoOld);
            } else {
                statRsStAdrCountMap.put(code, stAdrCount);
            }
        }
        return statRsStAdrCountMap;
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
    private static void checkMaDemo(Map<String, String> etfBizMap, String date, boolean isUp, List<Integer> maList, String kltType) {
//        Map<String, String> etfBizMap = ContentEtf.mapEtfAll;//mapEtfBiz mapEtfIndex    mapEtfAll
//        List<RankBizDataDiff> rankEtf = listEtf(date, DB_RANK_BIZ_TYPE_ETF, NUM_MAX_999);//
//        for (RankBizDataDiff etf : rankEtf) {
//            etfBizMap.put(etf.getF12(), etf.getF14());
//        }

        KlineService.checkMa(etfBizMap, kltType, maList, date, isUp);// 检查均线
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
