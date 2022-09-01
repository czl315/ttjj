package ttjj.rank.stat;

import ttjj.dao.BizRankDao;
import ttjj.dto.*;
import ttjj.service.BizService;
import ttjj.service.KlineService;
import utils.ContMapEtf;
import utils.ContentEtf;
import utils.DateUtil;
import utils.StockUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static utils.Content.*;
import static utils.DateUtil.YYYY_MM_DD;

/**
 * etf
 */
public class BizEtfDemo {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-08-04";
//        List<String> dateList = StockService.findListDateAfter(date, 2);
//        if (dateList != null && dateList.size() > 1) {
//            spDate = dateList.get(1);//是否显示特定日期涨跌   "2022-05-18"
//        }


        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            System.out.println();
            System.out.println();
            System.out.println("定时任务-etf-检查均线-beg:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));
            showEtfUpMa(date);//etf-超过均线
            System.out.println("定时任务-etf-检查均线-end:");
        }, 0, 5, TimeUnit.MINUTES);

//        showEtfUpMa(date);//etf-超过均线
//        showEtfMv(date);//显示etf市值

        //k线：每日最高点、最低点
        statDayMinMaxTime();

//        listEtfBizDb(ContentEtf.mapEtfAll.keySet(), 0, true, true);//列表查询-行业etf-排序：涨跌幅
    }

    /**
     * 每日最高点、最低点
     */
    private static void statDayMinMaxTime() {
        Map<String, String> mapZq = ContMapEtf.ETF_ZS;
        for (String zqdm : mapZq.keySet()) {
            String zqmc = mapZq.get(zqdm);
            statDayMinMaxTime(zqdm, zqmc);
        }
    }

    private static void statDayMinMaxTime(String zqdm, String zqmc) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
        int days = 20;
        String klt = KLT_5;
        Map<String, KlineDto> mapTimeScore = new HashMap<>();
        //获取最新n个交易日
        List<Kline> klines = KlineService.kline(zqdm, days, KLT_101, false, null, date, DB_RANK_BIZ_TYPE_ETF);
        for (Kline kline : klines) {
            String curDate = kline.getKtime();
//                System.out.println(curDate);

            //获取每个交易日的所有5分钟k线
            List<Kline> klineCurDate5MinuteList = KlineService.kline(zqdm, 0, klt, true, curDate, curDate, DB_RANK_BIZ_TYPE_ETF);
            klineCurDate5MinuteList = klineCurDate5MinuteList.stream().filter(e -> e != null).sorted(Comparator.comparing(Kline::getCloseAmt, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
            BigDecimal initScore = new BigDecimal(klineCurDate5MinuteList.size());
            for (Kline klineCurDate5Minute : klineCurDate5MinuteList) {
                String curTime = klineCurDate5Minute.getKtime();
                String keyCurTime = "";
                if (curTime.length() >= 19) {
                    keyCurTime = curTime.substring(11);
                }
//                    BigDecimal curCloseAmt = klineCurDate5Minute.getCloseAmt();
//                    System.out.println(keyCurTime + ":" + curCloseAmt);

                KlineDto klineDto = new KlineDto();
                klineDto.setZqdm(zqdm);
                klineDto.setZqmc(zqmc);
                klineDto.setKtime(keyCurTime);
                if (mapTimeScore.containsKey(keyCurTime)) {
                    BigDecimal oldScore = mapTimeScore.get(keyCurTime).getScore();
                    BigDecimal newScore = oldScore.add(initScore);
                    klineDto.setScore(newScore);
                } else {
                    klineDto.setScore(initScore);
                }
                mapTimeScore.put(keyCurTime, klineDto);
                initScore = initScore.subtract(new BigDecimal("1"));
            }
        }


        List<KlineDto> list = new ArrayList<>();
        for (String s : mapTimeScore.keySet()) {
            list.add(mapTimeScore.get(s));
        }
        list = list.stream().filter(e -> e != null).sorted(Comparator.comparing(KlineDto::getScore, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
        int i = 0;
        for (KlineDto klineDto : list) {
            System.out.println(++i + ":" + klineDto.getZqmc() + ":" + klineDto.getKtime() + ":" + klineDto.getScore());
        }
        System.out.println();
    }

    /**
     * 显示-etf-市值
     *
     * @param date
     */
    public static void showEtfMv(String date) {
        List<RankBizDataDiff> bizList = BizService.listBiz(date, DB_RANK_BIZ_TYPE_ETF, NUM_MAX_999);//查询板块行业列表
//        bizList = bizList.stream().filter(e -> e != null).sorted(Comparator.comparing(RankBizDataDiff::getF20, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
        bizList = bizList.stream().filter(e -> e != null).sorted(Comparator.comparing(RankBizDataDiff::getF3, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
        for (RankBizDataDiff biz : bizList) {
            StringBuffer sb = new StringBuffer();
//            mapMv.put(rankBizDataDiff.getF12(), rankBizDataDiff.getF20());
            String code = biz.getF12();
            String name = biz.getF14();
            String nameFormat = StockUtil.formatStName(name, 14);
            BigDecimal marketValue = biz.getF20().divide(NUM_YI_1, 2, BigDecimal.ROUND_HALF_UP);
            String mvFormat = StockUtil.formatDouble(marketValue, 8);
            BigDecimal adr = biz.getF3();
//            //  名称过滤
//            if (!name.contains("300")) {
//                continue;
//            }
            //  特定类型
//            Map<String, String> mapZq = ContMapEtf.ZIYUAN;
//            if (!mapZq.keySet().contains(code)) {
//                continue;
//            }

            sb.append(StockUtil.formatStName(code, 6)).append(" ");
            sb.append(nameFormat).append(" ");
            sb.append(marketValue).append(" ");
//            System.out.println(sb);
            System.out.println("etf.put(\"" + code + "\", \"" + nameFormat + "\");//" + mvFormat + "\t" + adr);
        }
    }

    /**
     * etf-超过均线
     *
     * @param date
     */
    private static void showEtfUpMa(String date) {
        //        spDate = DateUtil.getAddDays(YYYY_MM_DD, date, 1);//是否显示特定日期涨跌   "2022-05-18"
        String spDate = "";//

        CondMa condMa = new CondMa();
        condMa.setDate(date);
        condMa.setSpDate(spDate);
        condMa.setShowPriceArea(true);//是否显示价格区间
        condMa.setKltList(Arrays.asList(KLT_5, KLT_15, KLT_30, KLT_60, KLT_101));//价格区间周期列表
        condMa.setShowUpMa(true);//是否显示-超过均线
        condMa.setShowDownMa(true);//是否显示-跌落均线
        condMa.setFindKline(true);//是否查询最新k线
        condMa.setShowFlowIn(false);//是否显示资金流入
        condMa.setOrderField(ORDER_FIELD_NET_AREA_DAY_5);//ORDER_FIELD_NET_AREA_DAY_5 ORDER_FIELD_F3 ORDER_FIELD_MAXDOWN    ORDER_FIELD_MINRISE
        condMa.setOrderDesc(false);//是否倒序
        List<StockAdrCountVo> rs = null;
        System.out.println("科技：");
        condMa.setMapStock(ContMapEtf.KEJI);
        KlineService.showStockMa(condMa);
        System.out.println("消费：");
        condMa.setMapStock(ContMapEtf.XIAOFEI);
        KlineService.showStockMa(condMa);
        System.out.println("资源：");
        condMa.setMapStock(ContMapEtf.ZIYUAN);
        KlineService.showStockMa(condMa);
        System.out.println("医疗：");
        condMa.setMapStock(ContMapEtf.YILIAO);
        KlineService.showStockMa(condMa);
        System.out.println("金融：");
        condMa.setMapStock(ContMapEtf.JINRONG);
        KlineService.showStockMa(condMa);
        System.out.println("指数：");
        condMa.setMapStock(ContMapEtf.ETF_ZS);
        KlineService.showStockMa(condMa);
    }


    /**
     * 列表查询-行业etf-排序：涨跌幅
     *
     * @param etfBizSet etf集合
     * @param days
     * @param showUp    是否显示上涨
     * @param showDown  是否显示下跌
     * @return
     */
    private static Map<String, StatEtfUpDown> listEtfBizDb(Set<String> etfBizSet, int days, boolean showUp, boolean showDown) {
        Map<String, StatEtfUpDown> statRs = new HashMap<>();
        List<StatEtfUpDown> statEtfUpDownList = new ArrayList<>();
        //按照日期，倒序查询
        for (int i = 0; i <= days; i++) {
            String date = DateUtil.getCurDateStrAddDaysByFormat(YYYY_MM_DD, -i);
            Map<String, Object> condition = new HashMap<>();
            condition.put("list", etfBizSet);
            condition.put("date", date);
            List<RankBizDataDiff> rankListUp = BizRankDao.listEtfBiz(condition);
            if (rankListUp == null) {
                continue;
            }
            List<RankBizDataDiff> rankListDown = rankListUp.stream().filter(e -> e != null).sorted(Comparator.comparing(RankBizDataDiff::getF3, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());//倒序
            String curWeekNo = DateUtil.getWeekByYyyyMmDd(date, YYYY_MM_DD);
            if (showUp) {
                if (curWeekNo.equals(DATE_WEEK_5)) {
                }
                System.out.println(date + "上涨:");
                String upListStr = handlerUpOrDownList(rankListUp, 100, true);//处理上涨
                System.out.println(upListStr);//显示
            }
            if (showDown) {
                if (curWeekNo.equals(DATE_WEEK_5)) {
                }
                System.out.println(date + "下跌:");
                String downListStr = handlerUpOrDownList(rankListDown, 100, false);
                System.out.println(downListStr);//显示
            }

//            for (RankBizDataDiff biz : rankListUp) {
//                if (rankListUp == null) {
//                    return null;
//                }
//                String code = biz.getF12();
//                StatEtfUpDown statEtfUpDown = new StatEtfUpDown();
//                if (statRs.containsKey(code)) {
//                    statEtfUpDown = statRs.get(code);
//                }
//                statEtfUpDown.setCode(biz.getF12());
//                statEtfUpDown.setName(handlerEtfName(biz.getF14()));
//                int oldCountCurContinueUp = statEtfUpDown.getCountCurContinueUp();
//                int oldCountCurContinueDown = statEtfUpDown.getCountCurContinueDown();
//                int oldCountTotalUp = statEtfUpDown.getCountTotalUp();
//                int oldCountTotalDown = statEtfUpDown.getCountTotalDown();
//                //  当前连续次数合计-上涨:如果上涨，次数加，否则次数重置为0；下跌次数反之
//                if (biz.getF3().compareTo(new BigDecimal("0")) > 0) {
//                    statEtfUpDown.setCountCurContinueUp(oldCountCurContinueUp + 1);
//                    statEtfUpDown.setCountCurContinueDown(0);
//                    statEtfUpDown.setCountTotalUp(oldCountTotalUp + 1);
//                } else {
//                    statEtfUpDown.setCountCurContinueDown(oldCountCurContinueDown + 1);
//                    statEtfUpDown.setCountCurContinueUp(0);
//                    statEtfUpDown.setCountTotalDown(oldCountTotalDown + 1);
//                }
//                statRs.put(code, statEtfUpDown);
//            }
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
     * 处理上涨列表
     *
     * @param upRankList
     * @return
     */
    private static String handlerUpOrDownList(List<RankBizDataDiff> upRankList, int limit, boolean upDownFlag) {
        StringBuffer sb = new StringBuffer();
        if (upRankList == null) {
            return null;
        }
        int temp = 0;
        for (RankBizDataDiff r : upRankList) {
            temp++;
            if (temp > limit) {
                break;
            }
            String name = handlerEtfName(r.getF14());
            //如果上涨标志，涨幅小于0，中断
            if (upDownFlag && r.getF3().compareTo(new BigDecimal("0")) <= 0) {
                break;
            }
            //如果下跌标志，涨幅小于0，中断
            if (!upDownFlag && r.getF3().compareTo(new BigDecimal("0")) >= 0) {
                break;
            }
            sb.append("," + name);
//            sb.append("," + name + "：" + r.getF3());

        }
        String rs = "";
        if (sb.length() > 0) {
            rs = sb.substring(1);
        }
        return rs;
    }

    /**
     * 处理etf名称
     *
     * @param name
     */
    public static String handlerEtfName(String name) {
        name = name.replace("ETF", "");
        name = name.replace("基金", "");
        name = name.replace("有色金属", "有色");
        name = name.replace("基建50", "基建");
        name = name.replace("能源化工", "能源化工");
        name = name.replace("中概互联网", "中概");
        return name;
    }


}
