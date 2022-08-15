package ttjj.rank.stat;

import com.alibaba.fastjson.JSON;
import ttjj.dao.KlineDao;
import ttjj.dto.CondKline;
import ttjj.dto.Kline;
import ttjj.dto.StatCondStAdrCountKline;
import ttjj.dto.StatRsStAdrCountKline;
import ttjj.service.KlineService;
import utils.Content;
import utils.DateUtil;
import utils.StockUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static utils.Content.*;


/**
 * k线
 */
public class StatKlineDemo {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-08-09";

//        // 统计涨跌次数-根据每月中的日期
//        String zqmc = ZHISHU_NAME_399673;//ZHISHU_NAME_399673 ZHISHU_NAME_000001
//        statAdrCountByDay(zqmc);

        statAdrByTime(date);

    }

    /**
     * 统计涨幅-根据时间、类型等
     */
    private static void statAdrByTime(String date) {
        //首先查询k线类别
        //按时间列表查询k线涨幅
        List<String> timeList = new ArrayList<>();
        Map<String, List<Kline>> codeKline = new HashMap<>();
        String orderTime = TIME_10_30;//TIME_10_30 TIME_11_30  TIME_14_00   TIME_15_00 TIME_09_45, TIME_10_00, TIME_10_15, TIME_10_30, TIME_10_45, TIME_11_00, TIME_11_15, TIME_11_30, TIME_13_15, TIME_13_30, TIME_13_45, TIME_14_00, TIME_14_15, TIME_14_30, TIME_14_45, TIME_15_00
        String klt = KLT_60;
//        String klt = KLT_30;
//        String klt = KLT_15;
//        String klt = KLT_5;
        if (klt.equals(KLT_15)) {
            timeList.addAll(TIME_TYPE_15_0945_TO_1500);
        }
        if (klt.equals(KLT_30)) {
            timeList.addAll(TIME_TYPE_30_1000_TO_1500);//TIME_TYPE_30_1000_TO_1500  TIME_TYPE_15_0945_TO_1500
        }
        if (klt.equals(KLT_60)) {
            timeList.addAll(TIME_TYPE_60_1030_TO_1500);
        }
        if (klt.equals(KLT_5)) {
//            timeList.addAll(TIME_TYPE_5_0935_TO_1000);//TIME_TYPE_5_0935_TO_1000
            timeList.addAll(TIME_TYPE_5_1305_TO_1330);//TIME_TYPE_5_0935_TO_1000
        }

        String type = DB_RANK_BIZ_TYPE_ETF;
//        TIME_TYPE5_0935_TO_1000.addAll(TIME_TYPE5_1005_TO_1030);

        CondKline conditionKlineList = new CondKline();
        conditionKlineList.setDate(date);
        conditionKlineList.setType(type);
        conditionKlineList.setKlt(klt);
        conditionKlineList.setKtime(orderTime);//排序时间点
        List<Kline> klineList = KlineService.listKine(conditionKlineList);

        for (Kline kline : klineList) {
            String code = kline.getZqdm();
            CondKline condKlineTime = new CondKline();
            condKlineTime.setDate(date);
            condKlineTime.setType(type);
            condKlineTime.setKlt(klt);
            condKlineTime.setZqdm(code);
            List<Kline> klineTimeList = KlineService.listKine(condKlineTime);
            codeKline.put(code, klineTimeList);
        }

        showKline(date, type, klineList, timeList, codeKline);

        //显示-A股当前时段上涨和下跌
        showUpOrDownInfo(date, type, klt, TIME_10_30, timeList,"0.3", "-0.3", true, false, 10);
        showUpOrDownInfo(date, type, klt, TIME_10_30, timeList, "0.3", "-0.3", false, true, 10);

        showUpOrDownInfo(date, type, klt, TIME_11_30, timeList, "0.3", "-0.3", true, false, 10);
        showUpOrDownInfo(date, type, klt, TIME_11_30, timeList, "0.3", "-0.3", false, true, 10);

        showUpOrDownInfo(date, type, klt, TIME_14_00, timeList, "0.3", "-0.3", true, false, 10);
        showUpOrDownInfo(date, type, klt, TIME_14_00, timeList, "0.3", "-0.3", false, true, 10);

//        showUpOrDownInfo(date, type, klt, TIME_14_00, "0.5", "-0.5", true, false, 10);
//        showUpOrDownInfo(date, type, klt, TIME_15_00, "0.5", "-0.5", true, false, 10);
    }

    private static void showUpOrDownInfo(String date, String type, String klt, String orderTime, List<String> timeList, String adrUp, String adrDown, boolean isUp, boolean isDown, int limit) {
        boolean isShowCode = false;
        int sizeName = 14;
        int sizeAdr = 6;
        CondKline conditionKlineList = new CondKline();
        conditionKlineList.setDate(date);
        conditionKlineList.setType(type);
        conditionKlineList.setKlt(klt);
        conditionKlineList.setKtime(orderTime);//排序时间点
        List<Kline> klineList = KlineService.listKine(conditionKlineList);

        if (klineList == null) {
            System.out.println("klineList==null");
            return;
        }

        StringBuffer sbHead = new StringBuffer();
        sbHead.append("A股主要");
        if (isUp) {
            sbHead.append("上涨");
        } else {
            sbHead.append("下跌");
        }
        if (type.equals(DB_RANK_BIZ_TYPE_ETF)) {
            sbHead.append("(ETF)");
        }
        sbHead.append("(" + date + ")");
        List<String> kltList = null;
        if (klt.equals(KLT_60)) {
            kltList = TIME_TYPE_60_1030_TO_1500;
            int i = 0;
            for (String kltCur : kltList) {
                i++;
                if (orderTime.equals(kltCur)) {
                    sbHead.append("(第" + i + "个");
                }
            }
            sbHead.append("小时):");
        }
        if (klt.equals(KLT_30)) {
            kltList = TIME_TYPE_30_1000_TO_1500;
            int i = 0;
            for (String kltCur : kltList) {
                i++;
                if (orderTime.equals(kltCur)) {
                    sbHead.append("(第" + i + "个");
                }
            }
            sbHead.append("半小时):");
        }
        if (klt.equals(KLT_15)) {
            kltList = TIME_TYPE_15_0945_TO_1500;
            int i = 0;
            for (String kltCur : kltList) {
                i++;
                if (orderTime.equals(kltCur)) {
                    sbHead.append("(第" + i + "个");
                }
            }
            sbHead.append("刻钟):");
        }
        if (klt.equals(KLT_5)) {
            kltList = TIME_TYPE_5_0935_TO_1000;
            int i = 0;
            for (String kltCur : kltList) {
                i++;
                if (orderTime.equals(kltCur)) {
                    sbHead.append("(第" + i + "个");
                }
            }
            sbHead.append("5分钟):");
        }
        System.out.println(sbHead);

        //上涨
        if (isUp) {
            for (Kline dto : klineList) {
                BigDecimal adr = dto.getZhangDieFu();
                if (adr.compareTo(new BigDecimal(adrUp)) >= 0) {
                    if (--limit < 0) {
                        break;//限定个数
                    }
                    StringBuffer sb = new StringBuffer();
//                    sb.append(StockUtil.formatEtfName(dto.getZqmc(), 0));
                    sb.append(StockUtil.formatStName(dto.getZqmc(), sizeName));
                    sb.append(":");
                    sb.append(StockUtil.formatStr(adr+"%", sizeAdr));
                    sb.append(";");
                    System.out.println(sb);
                }
            }
            System.out.println();
        }

        if (isDown) {
            klineList = klineList.stream().filter(e -> e != null).sorted(Comparator.comparing(Kline::getZhangDieFu, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            for (Kline dto : klineList) {
                BigDecimal adr = dto.getZhangDieFu();
                if (adr.compareTo(new BigDecimal(adrDown)) < 0) {
                    if (--limit < 0) {
                        break;//限定个数
                    }
                    StringBuffer sb = new StringBuffer();
//                    sb.append(StockUtil.formatEtfName(dto.getZqmc(), 0));
                    sb.append(StockUtil.formatStName(dto.getZqmc(), sizeName));
                    sb.append(":");
                    sb.append(StockUtil.formatStr(adr+"%", sizeAdr));
                    sb.append(";");
                    System.out.println(sb);
                }
            }
            System.out.println();
        }


//        StringBuffer sbHeadDown = new StringBuffer();
//        sbHeadDown.append("A股ETF下跌");
//        if(orderTime.equals(TIME_10_30)){
//            sbHeadDown.append("(第1小时):");
//        }
//        if(orderTime.equals(TIME_11_30)){
//            sbHeadDown.append("(第2小时):");
//        }
//        if(orderTime.equals(TIME_14_00)){
//            sbHeadDown.append("(第3小时):");
//        }
//        if(orderTime.equals(TIME_15_00)){
//            sbHeadDown.append("(第4小时):");
//        }
//        System.out.println(sbHeadDown);
//        //  下跌
//        for (Kline dto : klineList) {
//            BigDecimal adr = dto.getZhangDieFu();
//            if(adr.compareTo(new BigDecimal(adrDown))<=0){
//                StringBuffer sb = new StringBuffer();
//                sb.append(StockUtil.formatEtfName(dto.getZqmc(), 0));
//                sb.append(":");
//                sb.append(StockUtil.formatDouble(adr, 4));
//                sb.append("; ");
//                System.out.print(sb);
//            }
//        }
    }

    private static void showKline(String date, String type, List<Kline> klineList, List<String> timeList, Map<String, List<Kline>> codeKlineMap) {
        boolean isShowCode = false;
        if (klineList == null) {
            System.out.println("klineList==null");
            return;
        }

        StringBuffer sbHead = new StringBuffer();
        if (isShowCode) {
            sbHead.append(StockUtil.formatStName("编码", 10));
        }
//        sbHead.append("[");
        sbHead.append(StockUtil.formatEtfName("名称", 12));
//        sbHead.append("]");
//        sbHead.append("[");
        sbHead.append(StockUtil.formatStName("类型", 10));
//        sbHead.append("]");
//        sbHead.append("[");
        sbHead.append(StockUtil.formatStName("时间", 10));
//        sbHead.append("]");
//        sbHead.append("[");
        sbHead.append(StockUtil.formatStName("时间涨幅", 10));
//        sbHead.append("]");
//        sbHead.append("[");
        sbHead.append(StockUtil.formatStName("日期", 14));
//        sbHead.append("]");
//        sbHead.append("[");
        sbHead.append(StockUtil.formatStName("日涨幅", 10));
//        sbHead.append("]");
        for (String time : timeList) {
//            sbHead.append("[");
            sbHead.append(StockUtil.formatStName(time, 10));
//            sbHead.append("]");
        }
        System.out.println(sbHead);

        //查询当日涨幅
        CondKline conditionKlineList = new CondKline();
        conditionKlineList.setDate(date);
        conditionKlineList.setType(type);
        conditionKlineList.setKlt(KLT_101);
        List<Kline> listKlineCurDay = KlineService.listKine(conditionKlineList);
        Map<String, Kline> mapKlineListCurDay = new HashMap<>();
        for (Kline kline : listKlineCurDay) {
            mapKlineListCurDay.put(kline.getZqdm(), kline);
        }

        for (Kline dto : klineList) {
//            System.out.println(JSON.toJSONString(stockAdrCount));
            StringBuffer sb = new StringBuffer();
            String stName = StockUtil.formatEtfName(dto.getZqmc(), 12);
            String bizName = StockUtil.formatStName(dto.getType(), 2);
            String code = StockUtil.formatStName(dto.getZqdm(), 6);
            String ktime = dto.getKtime();
            if (isShowCode) {
                sb.append(code).append("  ");
            }
//            sb.append("[");
            sb.append(stName);
//            sb.append("]");
//            sb.append("[");
            sb.append(StockUtil.formatStName(bizName, 10));
//            sb.append("]");
//            sb.append("[");
            sb.append(StockUtil.formatStName(ktime, 10));
//            sb.append("]");
//            sb.append("[");
            sb.append(StockUtil.formatDouble(dto.getZhangDieFu(), 10));
//            sb.append("]");

            //显示当日涨幅
//            sb.append("[");
            sb.append(StockUtil.formatStName(date, 14));
//            sb.append("]");
//            sb.append("[");
            sb.append(StockUtil.formatDouble(mapKlineListCurDay.get(dto.getZqdm()).getZhangDieFu(), 10));
//            sb.append("]");

            for (String time : timeList) {
                List<Kline> codeKlineList = codeKlineMap.get(code);

                for (Kline kline : codeKlineList) {
                    if (!code.equals(kline.getZqdm())) {
                        continue;
                    }
                    if (time.equals(kline.getKtime())) {
//                        sb.append("[");
                        sb.append(StockUtil.formatDouble(kline.getZhangDieFu(), 10));
//                        sb.append("]");
                        break;
                    }
                }
            }


            System.out.println(sb);
        }
    }

    /**
     * 统计涨跌次数-根据每月中的日期
     */
    private static void statAdrCountByDay(String zqmc) {
        String begDate = "2020-01-01";//开始时间
        String endDate = "2021-12-31";//DateUtil.getToday(DateUtil.YYYY_MM_DD)
        String klt = KLT_101;
        List<String> stCodeList = new ArrayList<>();
        stCodeList.add(zqmc);
        BigDecimal adrMin = new BigDecimal("0");

        StatCondStAdrCountKline condition = new StatCondStAdrCountKline();//查询条件
        condition.setKlt(klt);
        condition.setBegDate(begDate);
        condition.setEndDate(endDate);
        condition.setStCodeList(stCodeList);


        List<StatRsStAdrCountKline> rsAll = KlineDao.findListStatStAdrCount(condition); //  查询-涨跌次数-所有
        for (StatRsStAdrCountKline r : rsAll) {
//            System.out.println(JSON.toJSONString(r));
        }
        condition.setAdrMin(adrMin);
        List<StatRsStAdrCountKline> rsGt0 = KlineDao.findListStatStAdrCount(condition); //  查询-涨跌次数-涨跌大于n
        for (StatRsStAdrCountKline r : rsGt0) {
//            System.out.println(JSON.toJSONString(r));
        }

        System.out.println(zqmc);

        List<String> days = new ArrayList<>();

        days = Arrays.asList("01,02,03,04,05".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("06,07,08,09,10".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("07".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("08".split(","));
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
    private static BigDecimal staticsPct(List<StatRsStAdrCountKline> rsGt0, List<StatRsStAdrCountKline> rsAll, List<String> days) {
        Map<String, BigDecimal> mapRsGt0 = new HashMap();
        Map<String, BigDecimal> mapRsAll = new HashMap();
        for (StatRsStAdrCountKline rsGt0Temp : rsGt0) {
            mapRsGt0.put(rsGt0Temp.getRsDate(), rsGt0Temp.getCount());//大于n的次数s
        }
        for (StatRsStAdrCountKline rsTemp : rsAll) {
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
}
