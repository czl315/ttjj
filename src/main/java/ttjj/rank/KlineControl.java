package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.KlineDao;
import ttjj.dto.FundFlow;
import ttjj.dto.Kline;
import ttjj.dto.RankBizDataDiff;
import ttjj.service.BizService;
import ttjj.service.FundFlowService;
import ttjj.service.KlineService;
import utils.ContMapEtf;
import utils.Content;
import utils.ContentEtf;
import utils.DateUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static utils.Content.*;


/**
 * k线
 */
public class KlineControl {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-07-22";

        saveKlineEtfMianSchedule(date);
//        saveKlineAll();

        //  插入常用指数k线
//        addZs(KLT_102);

//        updateHisDateKlineFundFlow(DB_RANK_BIZ_TYPE_BAN_KUAI);//更新历史日期-资金流向

////        // 查询k线
//        findKline();

    }

    private static void saveKlineEtfMianSchedule(String date) {
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            System.out.println("定时任务-保存常用etf-beg:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));
            saveKlineAndMv(date, DB_RANK_BIZ_TYPE_ETF, Arrays.asList(KLT_5, KLT_15, KLT_30, KLT_60, KLT_101), ContMapEtf.ETF_All);//保存常用etf
            System.out.println("定时任务-保存常用etf-end:");
        }, 0, 5, TimeUnit.MINUTES);
    }

    private static void saveKlineAll() {

        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-07-22";
        List<String> kltList_101_15 = Arrays.asList(KLT_101, KLT_60, KLT_30, KLT_15);
        List<String> kltList_101_5 = Arrays.asList(KLT_5, KLT_15, KLT_30, KLT_60, KLT_101);
        List<String> kltList_101 = Arrays.asList(KLT_101);

        System.out.println("定时任务-保存-板块、概念、指数、全部etf:");
        saveKlineAndMv(date, DB_RANK_BIZ_TYPE_ZS, kltList_101, handlerZqMap(date, DB_RANK_BIZ_TYPE_ZS));//
        saveKlineAndMv(date, DB_RANK_BIZ_TYPE_ETF, kltList_101_5, ContMapEtf.ETF_All);//保存常用etf
        saveKlineAndMv(date, DB_RANK_BIZ_TYPE_BAN_KUAI, kltList_101_15, handlerZqMap(date, DB_RANK_BIZ_TYPE_BAN_KUAI));//
        saveKlineAndMv(date, DB_RANK_BIZ_TYPE_GAI_NIAN, Arrays.asList(KLT_101, KLT_60), handlerZqMap(date, DB_RANK_BIZ_TYPE_GAI_NIAN));//
        saveKlineAndMv(date, DB_RANK_BIZ_TYPE_ETF, kltList_101, handlerZqMap(date, DB_RANK_BIZ_TYPE_ETF));//全部etf

        updateFundFlow(date, DB_RANK_BIZ_TYPE_ZS, Arrays.asList(KLT_101, KLT_60, KLT_30, KLT_15));
        updateFundFlow(date, DB_RANK_BIZ_TYPE_BAN_KUAI, Arrays.asList(KLT_101, KLT_60, KLT_30, KLT_15));
        updateFundFlow(date, DB_RANK_BIZ_TYPE_GAI_NIAN, Arrays.asList(KLT_101, KLT_60, KLT_30, KLT_15));

//        updateFundFlow(date, DB_RANK_BIZ_TYPE_ETF, Arrays.asList(KLT_101, KLT_60, KLT_30, KLT_15));//etf资金流向不重要
    }

    /**
     * 更新资金流向
     *
     * @param date
     * @param bizType
     * @param kltList
     */
    private static void updateFundFlow(String date, String bizType, List<String> kltList) {
        Map<String, String> zhishuMap = handlerZqMap(date, bizType);

        for (String klt : kltList) {
            // 更新-k线-资金流向
            KlineService.updateFundFlow(zhishuMap, date, klt, bizType);
        }
    }

    /**
     * 保存k线数据
     *
     * @param date
     * @param bizType
     * @param kltList
     */
    private static void saveKlineByType(String date, String bizType, List<String> kltList) {
        Map<String, String> zhishuMap = handlerZqMap(date, bizType);

        for (String klt : kltList) {
            // 保存指数k线：5分钟-天, date, KLT_60, bizType);
            KlineService.saveKlineByType(zhishuMap, date, klt, bizType, true);
//            // 更新-k线-资金流向
//            KlineService.updateFundFlow(zhishuMap, date, klt, bizType);
        }

        //更新均线价格-天
        for (String zqdm : zhishuMap.keySet()) {
            updateNetByDate(zqdm, KLT_101, true, date, date, bizType);
        }

        //更新-市值
        updateMv(date, bizType, zhishuMap);

        //etf特殊集合插入5分钟
        if (bizType.equals(DB_RANK_BIZ_TYPE_ETF)) {
            zhishuMap = ContentEtf.mapEtfAll;//mapEtfBiz mapEtfIndex    mapEtfAll
            KlineService.saveKlineByType(zhishuMap, date, KLT_5, bizType, true);
        }
    }

    /**
     * 保存K线，更新市值,更新均线价格-天
     *
     * @param date
     * @param bizType
     * @param kltList
     * @param mapZq
     */
    private static void saveKlineAndMv(String date, String bizType, List<String> kltList, Map<String, String> mapZq) {
        long timeBeg = System.currentTimeMillis();
        System.out.println("保存K线，更新市值,更新均线价格" + date + "," + bizType + "-beg");
        for (String klt : kltList) {
            // 保存指数k线：5分钟-天, date, KLT_60, bizType);
            KlineService.saveKlineByType(mapZq, date, klt, bizType, true);
        }

        //更新-市值
        updateMv(date, bizType, mapZq);

        //更新均线价格-天
        for (String zqdm : mapZq.keySet()) {
            updateNetByDate(zqdm, KLT_101, true, date, date, bizType);
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("保存K线，更新市值,更新均线价格" + date + "," + bizType + "-end:" + (timeEnd - timeBeg) / 1000);
    }

    /**
     * 更新-市值
     *
     * @param date      日期
     * @param bizType   业务
     * @param zhishuMap 代码列表
     */
    private static void updateMv(String date, String bizType, Map<String, String> zhishuMap) {
        boolean isShowLog = true;
        Map<String, BigDecimal> mapMv = new HashMap<>();
        //查询市值
//        if (bizType.equals(DB_RANK_BIZ_TYPE_ZS)) {
//            zhishuMap = Content.getZhishuMap();//        Map<String, String>  zhishuMap = new HashMap<>();zhishuMap.put("000001","上证指数");//特定测试
//        } else
        if (bizType.equals(DB_RANK_BIZ_TYPE_ETF) || bizType.equals(DB_RANK_BIZ_TYPE_BAN_KUAI) || bizType.equals(DB_RANK_BIZ_TYPE_GAI_NIAN)) {
            List<RankBizDataDiff> bizList = BizService.listBiz(date, bizType, NUM_MAX_999);//查询板块行业列表
            for (RankBizDataDiff rankBizDataDiff : bizList) {
                mapMv.put(rankBizDataDiff.getF12(), rankBizDataDiff.getF20());
            }
        }

        Integer rs = 0;
        for (String zqdm : zhishuMap.keySet()) {
            BigDecimal mv = mapMv.get(zqdm);
            if (mv == null) {
                System.out.println("市值为空：" + zqdm);
                continue;
            }

            Kline kline = new Kline();
            kline.setZqdm(zqdm);
            kline.setDate(date);
            kline.setF20(mv);
            rs += KlineDao.updateNet(kline);

            if (isShowLog && rs < 1) {
                System.out.println("更新-市值-失败：" + JSON.toJSONString(kline));
            }
        }
        if (isShowLog && rs >= 1) {
            System.out.println("更新-市值-成功：" + rs);
        }
    }

    /**
     * 获取证券列表
     *
     * @param date    日期
     * @param bizType 业务
     * @return 证券列表
     */
    private static Map<String, String> handlerZqMap(String date, String bizType) {
        Map<String, String> zhishuMap = new HashMap<>();
        if (bizType.equals(DB_RANK_BIZ_TYPE_ZS)) {
            zhishuMap = Content.getZhishuMap();//        Map<String, String>  zhishuMap = new HashMap<>();zhishuMap.put("000001","上证指数");//特定测试
        } else if (bizType.equals(DB_RANK_BIZ_TYPE_ETF) || bizType.equals(DB_RANK_BIZ_TYPE_BAN_KUAI) || bizType.equals(DB_RANK_BIZ_TYPE_GAI_NIAN)) {
            List<RankBizDataDiff> bizList = BizService.listBiz(date, bizType, NUM_MAX_999);//查询板块行业列表
            for (RankBizDataDiff rankBizDataDiff : bizList) {
                zhishuMap.put(rankBizDataDiff.getF12(), rankBizDataDiff.getF14());
            }
        }
        return zhishuMap;
    }

    private static void addZs(String klt) {
        //        boolean isAddMinuteKline = true;//是否添加分钟级别K线
        int lmt = 1;
        int addDaysMax = 0;//最多增加的天数
        int year = DateUtil.getCurYear();//2021
        int month = DateUtil.getCurMonth();//DateUtil.getCurMonth()
        int day = DateUtil.getCurDay();//DateUtil.getCurDay()
        String klineType = DB_RANK_BIZ_TYPE_ZS;
        addZhishuKline(false, klt, lmt, addDaysMax, year, month, day, klineType);
    }

    /**
     * 更新历史日期-资金流向
     */
    private static void updateHisDateKlineFundFlow(String bizType) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
        List<RankBizDataDiff> bizList = BizService.listBiz(date, bizType, NUM_MAX_999);//查询板块行业列表
        for (RankBizDataDiff rankBizDataDiff : bizList) {
            String zqdm = rankBizDataDiff.getF12();
            String zqmc = rankBizDataDiff.getF14();
            String klt = KLT_101;
            List<FundFlow> fundFlowList = FundFlowService.httpFundFlowHisDay(zqdm, klt);
            fundFlowList = fundFlowList.stream().filter(e -> e != null).sorted(Comparator.comparing(FundFlow::getKtime, Comparator.nullsFirst(String::compareTo)).reversed()).collect(Collectors.toList());
            int rs = 0;
            int limitCount = 10;
            for (FundFlow fundFlow : fundFlowList) {
                if (--limitCount <= 0) {//限定个数
                    break;
                }
                Kline kline = new Kline();
                kline.setKtime(fundFlow.getKtime());//只设置当天具体时间，去掉日期
                kline.setDate(date);//只设置当天具体时间，去掉日期
                kline.setZqdm(rankBizDataDiff.getF12());
                kline.setDate(fundFlow.getKtime());
                kline.setType(bizType);
                kline.setKlt(klt);

                kline.setFlowInMain(fundFlow.getFlowInMain());
                kline.setFlowInSuperBig(fundFlow.getFlowInSuperBig());
                kline.setFlowInBig(fundFlow.getFlowInBig());
                kline.setFlowInMid(fundFlow.getFlowInMid());
                kline.setFlowInSmall(fundFlow.getFlowInSmall());
                int updateRs = KlineService.update(kline);
                if (updateRs != 1) {
                    System.out.println("K线-资金流入历史日期-更新失败：" + JSON.toJSONString(kline));
                } else {
                    rs = rs + updateRs;
                }
            }
            System.out.println("K线-资金流入历史日期-更新个数：" + rs + ",zqmc:" + zqmc);
        }
    }

    private static void findKline() {
        String zqdm = "000001";
        String klt = Content.KLT_5;//klt=5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
        int lmt = 1000000;
        int addDaysMax = -100;//最多增加的天数
        int year = DateUtil.getCurYear();//2021
        int month = DateUtil.getCurMonth();//DateUtil.getCurMonth()
        int day = DateUtil.getCurDay();//DateUtil.getCurDay()
        String begDate = DateUtil.getDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, year, month, day, addDaysMax);//查询新增交易的开始时间
        String endDate = DateUtil.getToday(DateUtil.YYYY_MM_DD);
        //查询K线-昨天到今天
        List<Kline> klines = KlineService.kline(zqdm, lmt, klt, true, begDate, endDate, KLINE_TYPE_STOCK);
        klines = klines.stream().filter(e -> e != null).sorted(Comparator.comparing(Kline::getKtime, Comparator.nullsFirst(String::compareTo)).reversed()).collect(Collectors.toList());
        int fromIndex = 0;//需要计算开始位置
        int cjeCountAdd = 0;//成交额增量次数
        int cjeCountSub = 0;//成交额减量次数
        for (Kline kline : klines) {
            fromIndex++;
            //遍历k线
            //如果k线时间大于当前时间，不比较
            long klineTime = DateUtil.getTimeInMillisByDateStr(DateUtil.YYYY_MM_DD_HH_MM_SS, kline.getKtime());
            long curTime = Calendar.getInstance().getTimeInMillis();
            if (klineTime > curTime) {
                System.out.println("如果k线时间大于当前时间，不比较.klineRs:" + JSON.toJSONString(kline));
                continue;
            }
            //如果非今天，退出比较
            long todayTime = DateUtil.getTimeInMillisByDateStr(DateUtil.YYYY_MM_DD_HH_MM_SS, DateUtil.getToday(DateUtil.YYYY_MM_DD_HH_MM_SS).substring(0, 10) + " 00:00:00");
            if (klineTime < todayTime) {
                System.out.println("如果k线时间小于今日时间，退出比较.");
                break;
            }
//            System.out.println("klineRs:" + JSON.toJSONString(kline));

            //查询最小净值、最大净值、均值,k线时间必须小于比较时间
            List<Kline> maKlines = klines.subList(fromIndex, klines.size());
            Map<String, BigDecimal> netMap5 = KlineService.handlerNetMinMaxAvg(Content.MA_15, maKlines);

            //比较均量
            BigDecimal cjeAvg = netMap5.get(Content.keyRsCjeAvg);
            BigDecimal cjeCur = kline.getCje();
            if (cjeCur.compareTo(cjeAvg) > 0) {
                cjeCountAdd++;
                System.out.print("放量(" + cjeCountAdd + "),");
//                System.out.print("当前成交额：" + cjeCur + ",");
//                System.out.print("平均成交额：" + cjeAvg + "。");
            }
            if (cjeCur.compareTo(cjeAvg) == 0) {
                System.out.print("平量,");
//                System.out.print("当前成交额：" + cjeCur + ",");
//                System.out.println("平均成交额：" + cjeAvg + "。");
            }
            if (cjeCur.compareTo(cjeAvg) < 0) {
                cjeCountSub++;
                System.out.print("缩量(" + cjeCountSub + "),");
//                System.out.print("当前成交额：" + cjeCur + ",");
//                System.out.print("平均成交额：" + cjeAvg + "。");
            }

            //比较均价
            BigDecimal netAvg = netMap5.get(Content.keyRsNetCloseAvg);
            BigDecimal netCur = kline.getCloseAmt();
            if (netCur.compareTo(netAvg) > 0) {
                System.out.print("上涨,");
                System.out.print("当前价格：" + netCur + ",");
                System.out.print("平均价格：" + netAvg + "。");
            }
            if (netCur.compareTo(netAvg) == 0) {
                System.out.print("平价,");
                System.out.print("当前价格：" + netCur + ",");
                System.out.print("平均价格：" + netAvg + "。");
            }
            if (netCur.compareTo(netAvg) < 0) {
                System.out.print("下跌,");
                System.out.print("当前价格：" + netCur + ",");
                System.out.print("平均价格：" + netAvg + "。");
            }
            System.out.println(kline.getKtime());

        }
    }

    /**
     * 插入常用指数k线-大周期：日、周、月、年
     */
    private static void addZhishuKline(boolean isAddMinuteKline, String klt, int lmt, int addDaysMax, int year, int month, int day, String klineType) {
        //插入常用指数k线-大周期：日、周、月、年
        Map<String, String> zhishuMap = Content.getZhishuMap();//        Map<String, String>  zhishuMap = new HashMap<>();zhishuMap.put("000001","上证指数");//特定测试
        Set<String> zhishuList = zhishuMap.keySet();
        for (String zqdm : zhishuList) {
            for (int i = 0; i <= addDaysMax; i++) {
                String begDate = DateUtil.getDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, year, month, day, i);//查询新增交易的开始时间
                String endDate = begDate;
//            //  增加大周期k线
                addKlineDaZhouQi(zqdm, lmt, klt, begDate, endDate, klineType);

                if (isAddMinuteKline) {
                    //按照日期的分钟线（1分钟；5分钟；15分钟；30分钟；60分钟；120分钟），插入k线
                    addKlineMinute(zqdm, lmt, Content.KLT_1, begDate, endDate, klineType);
                    addKlineMinute(zqdm, lmt, Content.KLT_5, begDate, endDate, klineType);
                    addKlineMinute(zqdm, lmt, Content.KLT_15, begDate, endDate, klineType);
                    addKlineMinute(zqdm, lmt, Content.KLT_30, begDate, endDate, klineType);
                    addKlineMinute(zqdm, lmt, Content.KLT_60, begDate, endDate, klineType);
                    addKlineMinute(zqdm, lmt, Content.KLT_120, begDate, endDate, klineType);
                }

                //计算净值
                updateNet(zqdm, lmt, klt, begDate, endDate, klineType);
            }
        }
    }

    /**
     * 按照日期的分钟线（5分钟；15分钟；30分钟；60分钟；120分钟），插入k线
     *
     * @param zqdm
     * @param lmt
     * @param klt
     * @param begDate
     * @param endDate
     * @param klineType
     */
    public static void addKlineMinute(String zqdm, int lmt, String klt, String begDate, String endDate, String klineType) {
        /**    创业板50-159949 HS300ETF-510310 50ETF-510050	新汽车-515030	芯片ETF-159995	酒ETF-512690	医疗ETF-512170 	光伏ETF-515790	稀土ETF-516780	有色50-159880	煤炭ETF-515220 军工ETF-512660		**/
        String klineRs = KlineService.klineRsStrHttpDfcf(zqdm, lmt, klt, true, begDate, endDate, klineType);
//        System.out.println("开始日期:" + date + "，结束日期:" + date + "，周期:" + klt + "，klineRs:" + klineRs);
//        System.out.println("klines:"+JSON.toJSONString(klines));

        if (StringUtils.isBlank(klineRs)) {
            System.out.println("k线查询结果为空！");
            return;
        }

        JSONObject szzzMonthJson = JSON.parseObject(klineRs);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
        String zqmc = szzzMonthDataJson.getString("name");
//        System.out.println("证券名称：" + zqmc);
        if (szzzMonthDataJson == null || !szzzMonthDataJson.containsKey("klines")) {
            System.out.println("klines数据异常：" + JSON.toJSONString(szzzMonthDataJson));
            return;
        }

        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        if (klines == null || klines.size() <= 0) {
            System.out.println("k线数据为空！");
            return;
        }

        Kline kline = new Kline();
        kline.setType(DB_RANK_BIZ_TYPE_ZS);
        kline.setKlt(klt);
//        kline.setRs(klineRs);
        kline.setZqmc(zqmc);
        kline.setZqdm(zqdm);
        kline.setKtime(endDate);
        /**
         * 插入数据库-K线
         */
        KlineDao.insert(kline);
    }

    public static void updateNet(String zqdm, int lmt, String klt, String begDate, String endDate, String klineType) {
        Kline kline = new Kline();
        kline.setKlt(klt);
        kline.setZqdm(zqdm);
        kline.setKtime(endDate);

        //计算净值
        Map<String, BigDecimal> netMap5 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, klt, false, "", endDate, klineType);
        kline.setNET_MA_5(netMap5.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_5(netMap5.get(Content.keyRsMin));
        kline.setNET_MAX_5(netMap5.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_5(netMap5.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_5(netMap5.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap10 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, klt, false, "", endDate, klineType);
        kline.setNET_MA_10(netMap10.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_10(netMap10.get(Content.keyRsMin));
        kline.setNET_MAX_10(netMap10.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_10(netMap10.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_10(netMap10.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap20 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, klt, false, "", endDate, klineType);
        kline.setNET_MA_20(netMap20.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_20(netMap20.get(Content.keyRsMin));
        kline.setNET_MAX_20(netMap20.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_20(netMap20.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_20(netMap20.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap30 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, klt, false, "", endDate, klineType);
        kline.setNET_MA_30(netMap30.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_30(netMap30.get(Content.keyRsMin));
        kline.setNET_MAX_30(netMap30.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_30(netMap30.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_30(netMap30.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap60 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, klt, false, "", endDate, klineType);
        kline.setNET_MA_60(netMap60.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_60(netMap60.get(Content.keyRsMin));
        kline.setNET_MAX_60(netMap60.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_60(netMap60.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_60(netMap60.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap120 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, klt, false, "", endDate, klineType);
        kline.setNET_MA_120(netMap120.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_120(netMap120.get(Content.keyRsMin));
        kline.setNET_MAX_120(netMap120.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_120(netMap120.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_120(netMap120.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap250 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, klt, false, "", endDate, klineType);
        kline.setNET_MA_250(netMap250.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_250(netMap250.get(Content.keyRsMin));
        kline.setNET_MAX_250(netMap250.get(Content.keyRsMax));
        BigDecimal NET_MAX_CLOS_250 = netMap250.get(Content.keyRsNetCloseMax);
        if (NET_MAX_CLOS_250 == null) {
            System.out.println("均值异常！！！！！！！！！！！！！！！！！！！！");
            kline.setNET_MIN_CLOS_250(new BigDecimal("0"));
        } else {
            kline.setNET_MIN_CLOS_250(netMap250.get(Content.keyRsNetCloseMin));
        }
        KlineDao.updateNet(kline);
    }

    /**
     * 增加大周期k线
     *
     * @param zqdm
     * @param lmt
     * @param klt
     * @param begDate
     * @param endDate
     * @param klineType
     */
    private static void addKlineDaZhouQi(String zqdm, int lmt, String klt, String begDate, String endDate, String klineType) {
        //  插入数据库-K线-大周期
        List<Kline> klines = KlineService.kline(zqdm, lmt, klt, true, begDate, endDate, klineType);
        if (klines == null || klines.size() == 0) {
            return;
        }
        for (Kline kline : klines) {
            kline.setDate(endDate);
            kline.setType(DB_RANK_BIZ_TYPE_ZS);
            if (KLT_101.equals(klt)) {
                kline.setMonth(DateUtil.getYearMonth(endDate, DateUtil.YYYY_MM_DD));
                kline.setWeekYear(DateUtil.getYearWeek(endDate, DateUtil.YYYY_MM_DD));
                kline.setWeek(DateUtil.getWeekByYyyyMmDd(endDate, DateUtil.YYYY_MM_DD));
            }
            if (KLT_102.equals(klt)) {
                kline.setMonth(DateUtil.getYearMonth(endDate, DateUtil.YYYY_MM_DD));
                kline.setWeekYear(DateUtil.getYearWeek(endDate, DateUtil.YYYY_MM_DD));
            }
            if (KLT_103.equals(klt)) {
                kline.setMonth(DateUtil.getYearMonth(endDate, DateUtil.YYYY_MM_DD));
            }

            /**
             * 插入数据库-K线
             */
            int rs = KlineDao.insert(kline);
            System.out.println(rs + "-" + kline.getZqmc());

            //计算净值
            Map<String, BigDecimal> netMap5 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, klt, false, begDate, endDate, klineType);
            kline.setNET_MA_5(netMap5.get(Content.keyRsNetCloseAvg));
            kline.setNET_MIN_5(netMap5.get(Content.keyRsMin));
            kline.setNET_MAX_5(netMap5.get(Content.keyRsMax));
            kline.setNET_MIN_CLOS_5(netMap5.get(Content.keyRsNetCloseMin));
            kline.setNET_MAX_CLOS_5(netMap5.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap10 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, klt, false, begDate, endDate, klineType);
            kline.setNET_MA_10(netMap10.get(Content.keyRsNetCloseAvg));
            kline.setNET_MIN_10(netMap10.get(Content.keyRsMin));
            kline.setNET_MAX_10(netMap10.get(Content.keyRsMax));
            kline.setNET_MIN_CLOS_10(netMap10.get(Content.keyRsNetCloseMin));
            kline.setNET_MAX_CLOS_10(netMap10.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap20 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, klt, false, begDate, endDate, klineType);
            kline.setNET_MA_20(netMap20.get(Content.keyRsNetCloseAvg));
            kline.setNET_MIN_20(netMap20.get(Content.keyRsMin));
            kline.setNET_MAX_20(netMap20.get(Content.keyRsMax));
            kline.setNET_MIN_CLOS_20(netMap20.get(Content.keyRsNetCloseMin));
            kline.setNET_MAX_CLOS_20(netMap20.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap30 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, klt, false, begDate, endDate, klineType);
            kline.setNET_MA_30(netMap30.get(Content.keyRsNetCloseAvg));
            kline.setNET_MIN_30(netMap30.get(Content.keyRsMin));
            kline.setNET_MAX_30(netMap30.get(Content.keyRsMax));
            kline.setNET_MIN_CLOS_30(netMap30.get(Content.keyRsNetCloseMin));
            kline.setNET_MAX_CLOS_30(netMap30.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap60 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, klt, false, begDate, endDate, klineType);
            kline.setNET_MA_60(netMap60.get(Content.keyRsNetCloseAvg));
            kline.setNET_MIN_60(netMap60.get(Content.keyRsMin));
            kline.setNET_MAX_60(netMap60.get(Content.keyRsMax));
            kline.setNET_MIN_CLOS_60(netMap60.get(Content.keyRsNetCloseMin));
            kline.setNET_MAX_CLOS_60(netMap60.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap120 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, klt, false, begDate, endDate, klineType);
            kline.setNET_MA_120(netMap120.get(Content.keyRsNetCloseAvg));
            kline.setNET_MIN_120(netMap120.get(Content.keyRsMin));
            kline.setNET_MAX_120(netMap120.get(Content.keyRsMax));
            kline.setNET_MIN_CLOS_120(netMap120.get(Content.keyRsNetCloseMin));
            kline.setNET_MAX_CLOS_120(netMap120.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap250 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, klt, false, begDate, endDate, klineType);
            kline.setNET_MA_250(netMap250.get(Content.keyRsNetCloseAvg));
            kline.setNET_MIN_250(netMap250.get(Content.keyRsMin));
            kline.setNET_MAX_250(netMap250.get(Content.keyRsMax));
            kline.setNET_MIN_CLOS_250(netMap250.get(Content.keyRsNetCloseMin));
            kline.setNET_MAX_CLOS_250(netMap250.get(Content.keyRsNetCloseMax));
            KlineDao.updateNet(kline);
        }
    }

    /**
     * 更新均线价格
     *
     * @param zqdm      证券代码
     * @param klt       周期
     * @param isBegDate 是否有开始时间
     * @param begDate   开始时间
     * @param endDate   结束时间
     * @param klineType k线类型
     */
    private static Integer updateNetByDate(String zqdm, String klt, boolean isBegDate, String begDate, String endDate, String klineType) {
        Integer rs = 1;
        boolean isShowLog = false;
        Kline kline = new Kline();
        kline.setZqdm(zqdm);
        kline.setDate(endDate);
        kline.setType(klineType);
        kline.setKlt(klt);

        //计算净值
        Map<String, BigDecimal> netMap5 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, klt, false, begDate, endDate, klineType);
        if (netMap5.get(Content.keyRsNetCloseAvg) == null) {
            System.out.println("均线净值异常：" + JSON.toJSONString(netMap5));
            return null;
        }
        kline.setNET_MA_5(netMap5.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_5(netMap5.get(Content.keyRsMin));
        kline.setNET_MAX_5(netMap5.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_5(netMap5.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_5(netMap5.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap10 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, klt, false, begDate, endDate, klineType);
        kline.setNET_MA_10(netMap10.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_10(netMap10.get(Content.keyRsMin));
        kline.setNET_MAX_10(netMap10.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_10(netMap10.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_10(netMap10.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap20 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, klt, false, begDate, endDate, klineType);
        kline.setNET_MA_20(netMap20.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_20(netMap20.get(Content.keyRsMin));
        kline.setNET_MAX_20(netMap20.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_20(netMap20.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_20(netMap20.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap30 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, klt, false, begDate, endDate, klineType);
        kline.setNET_MA_30(netMap30.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_30(netMap30.get(Content.keyRsMin));
        kline.setNET_MAX_30(netMap30.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_30(netMap30.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_30(netMap30.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap60 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, klt, false, begDate, endDate, klineType);
        kline.setNET_MA_60(netMap60.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_60(netMap60.get(Content.keyRsMin));
        kline.setNET_MAX_60(netMap60.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_60(netMap60.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_60(netMap60.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap120 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, klt, false, begDate, endDate, klineType);
        kline.setNET_MA_120(netMap120.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_120(netMap120.get(Content.keyRsMin));
        kline.setNET_MAX_120(netMap120.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_120(netMap120.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_120(netMap120.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap250 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, klt, false, begDate, endDate, klineType);
        kline.setNET_MA_250(netMap250.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_250(netMap250.get(Content.keyRsMin));
        kline.setNET_MAX_250(netMap250.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_250(netMap250.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_250(netMap250.get(Content.keyRsNetCloseMax));
        KlineDao.updateNet(kline);
        if (isShowLog) {
            System.out.println("均线净值成功：" + JSON.toJSONString(kline));
        }
        return rs;
    }

}
