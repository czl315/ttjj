package ttjj.rank.stat;

import ttjj.dao.BizRankDao;
import ttjj.dto.*;
import ttjj.service.BizService;
import ttjj.service.KlineService;
import ttjj.service.StockService;
import utils.ContMapEtf;
import utils.DateUtil;
import utils.EtfUtil;
import utils.StockUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static utils.Content.*;
import static utils.DateUtil.YYYY_MM_DD;

/**
 * etf
 */
public class BizEtfDemo {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-09-27";
//        List<String> dateList = StockService.findListDateAfter(date, 2);
//        if (dateList != null && dateList.size() > 1) {
//            spDate = dateList.get(1);//是否显示特定日期涨跌   "2022-05-18"
//        }

        showEtfUpMa(date);//etf-超过均线
//        showEtfMv(date);//显示etf市值
//        statDayMinMaxTime(date);//k线：每日最高点、最低点

//        listEtfBizDb(ContentEtf.mapEtfAll.keySet(), 0, true, true);//列表查询-行业etf-排序：涨跌幅

//        statListEtfAdrArea();//计算区间涨幅
    }

    /**
     * ETF：计算区间涨幅
     */
    private static void statListEtfAdrArea() {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-08-26";


        int areaDays = 4;//4:近一周;20:近一月
        int limit = 20;

        boolean isDesc = true;
//        boolean isDesc = false;

//        statListEtfAdrArea(date, areaDays, isDesc, null, null, limit, true, DB_RANK_BIZ_TYPE_ETF);
        statListEtfAdrArea(date, areaDays, isDesc, null, null, limit, false,DB_RANK_BIZ_TYPE_BAN_KUAI);

    }

    /**
     * 统计区间涨幅
     *
     * @param date
     * @param areaDays
     * @param isDesc
     * @param mvMin
     * @param mvMax
     */
    private static void statListEtfAdrArea(String date, int areaDays, boolean isDesc, BigDecimal mvMin, BigDecimal mvMax, int limit, boolean isCheckMianEtf, String type) {
        boolean isShowCode = true;//是否显示编码
        boolean isCheckFuQuan = true;//是否检查更新复权

        String endDate = StockService.findBegDate(date, 0);
        String begDate = StockService.findBegDate(date, areaDays);

        Map<String, BizDto> rsMap = new HashMap<>();

        BizDto condition = new BizDto();
        condition.setDate(endDate);
        condition.setType(type);
        condition.setMvMin(mvMin);
        condition.setMvMax(mvMax);
        List<BizDto> etfListEndDate = BizService.findListDbBiz(condition);

        for (BizDto etf : etfListEndDate) {
            BizDto rsOne = new BizDto();
            rsOne.setF14(etf.getF14());
            rsOne.setF12(etf.getF12());
            rsOne.setF3(etf.getF3());
            BigDecimal marketValue = null;
            if (etf.getF20() != null) {
                marketValue = etf.getF20().divide(NUM_YI_1, 2, BigDecimal.ROUND_HALF_UP);
            }
            rsOne.setF20(marketValue);
            rsOne.setBegDate(begDate);
            rsOne.setEndDate(endDate);
            rsOne.setEndDateF2(etf.getF2());
            rsMap.put(etf.getF12(), rsOne);
        }

        BizDto condBegDate = new BizDto();
        condBegDate.setDate(begDate);
        condBegDate.setType(type);
        condBegDate.setMvMin(mvMin);
        condBegDate.setMvMax(mvMax);
        List<BizDto> etfListBegDate = BizService.findListDbBiz(condBegDate);

        for (BizDto etfBegDate : etfListBegDate) {
            String code = etfBegDate.getF12();
            BigDecimal yestodayNet = etfBegDate.getF18();
            BizDto rsOne = rsMap.get(code);
            if (rsOne == null) {
//                System.out.println("市值已减小：" + JSON.toJSONString(rankStockCommpanyDb));
                continue;
            }
            if (yestodayNet == null) {
//                System.out.println("昨日净值为空：" + JSON.toJSONString(rankStockCommpanyDb));
                continue;
            }
            rsOne.setBegDateF18(yestodayNet);
            rsOne.setF18(yestodayNet);

            //更新复权使用
            rsOne.setF2(etfBegDate.getF2());

            rsMap.put(code, rsOne);
        }


        List<BizDto> rsList = new ArrayList<>();
        //计算区间涨幅
        for (BizDto dto : rsMap.values()) {
            BigDecimal endDateF2 = dto.getEndDateF2();
            BigDecimal begDateF18 = dto.getBegDateF18();
            if (endDateF2 == null) {
//                System.out.println("结束净值为空：" + JSON.toJSONString(dto));
                continue;
            }
            if (begDateF18 == null) {
//                System.out.println("开始净值为空：" + JSON.toJSONString(dto));
                continue;
            }
            BigDecimal adrArea = (endDateF2.subtract(begDateF18)).multiply(new BigDecimal("100")).divide(begDateF18, 2, RoundingMode.HALF_UP);
            dto.setAreaF3(adrArea);

            if (isCheckMianEtf && !EtfUtil.isMainEtf(dto.getF12())) {//检查是否是主要etf
                continue;
            }
            rsList.add(dto);
        }

        boolean isShowMoreYes = true;
        boolean isShowMoreNo = false;
        if (isDesc) {
            //排序
            rsList = rsList.stream().filter(e -> e != null).sorted(Comparator.comparing(BizDto::getAreaF3, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
        } else {
            rsList = rsList.stream().filter(e -> e != null).sorted(Comparator.comparing(BizDto::getAreaF3, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
        }
        //区间涨幅
        StockUtil.showInfoHead(isShowMoreYes, isShowCode, false);
        StockUtil.showInfoEtf(rsList, begDate, endDate, limit, isShowMoreYes, isShowCode);
        System.out.println();

        if (isCheckFuQuan) {
            boolean isUpdate = BizService.updateFuQuanBiz(rsList, limit, begDate);//更新复权：前复权，检查当日K线与数据库的数据是否相符，如果不符，进行复权更新
            if (isUpdate) {
                statListEtfAdrArea(date, areaDays, isDesc, mvMin, mvMax, limit, isCheckMianEtf, type);
            }
        }
    }


    /**
     * 每日最高点、最低点
     *
     * @param date
     */
    private static void statDayMinMaxTime(String date) {
        Map<String, String> mapZq = new HashMap<>();
//        mapZq = ContMapEtf.ETF_MV_ZS_CYB;
        mapZq.put("515790", "光伏ETF");//KEJI.put("515790", "光伏ETF       ");//147.52  	-0.18
//        mapZq = MyPositionService.listMyPositionByDate(date);//我的持仓
//        mapZq.put("002027","分众传媒");
        String klineType = null;
//        String klineType = DB_RANK_BIZ_TYPE_ETF;
        for (String zqdm : mapZq.keySet()) {
            String zqmc = mapZq.get(zqdm);
            statDayMinMaxTime(date, zqdm, zqmc, klineType);
        }
    }

    /**
     * 每日最高点、最低点:
     *
     * @param zqdm      编码
     * @param zqmc      名称
     * @param klineType k线类型
     */
    private static void statDayMinMaxTime(String date, String zqdm, String zqmc, String klineType) {
        int days = 60;
        String klt = KLT_15;
//        BigDecimal adrMin = null;
        BigDecimal adrMin = new BigDecimal("1");
        BigDecimal adrMax = null;
//        BigDecimal adrMax = new BigDecimal("0");
        String limitWeek = null;
//        String limitWeek = "一";
        Map<String, KlineDto> mapTimeScore = new HashMap<>();
        //获取最新n个交易日
        System.out.println();
        System.out.print("统计" + zqmc + "最近" + days + "个交易日的数据");
        if (limitWeek != null) {
            System.out.print(",限定周" + limitWeek);
        }
        System.out.println();
        List<Kline> klines = KlineService.kline(zqdm, days, KLT_101, false, null, date, klineType);
        if (klines == null) {
            date = StockService.findBegDate(date, 1);
            klines = KlineService.kline(zqdm, days, KLT_101, false, null, date, klineType);
        }
        if (klines == null) {
            return;
        }
        for (Kline kline : klines) {
            String curDate = kline.getKtime();
//                System.out.println(curDate);
            BigDecimal adr = kline.getZhangDieFu();
            //限定涨跌
            if (adrMin != null && adr.compareTo(adrMin) < 0) {
//                System.out.println(curDate + "(ok)日涨跌低于限定最低涨幅:" + adr + ":" + adrMin);
                continue;
            } else {
//                System.out.println(curDate + "(ok)日涨跌高于限定最低涨幅:" + adr + ":" + adrMin);
            }
            if (adrMax != null && adr.compareTo(adrMax) > 0) {
//                System.out.println(curDate + "(ok)日涨跌高于限定最高涨幅:" + adr + ":" + adrMax);
                continue;
            } else {
//                System.out.println(curDate + "(ok)日涨跌低于限定最高涨幅:" + adr + ":" + adrMax);
            }

            //限定星期几
            if (limitWeek != null) {
                String curDateWeek = DateUtil.getWeekByYyyyMmDd(curDate, DateUtil.YYYY_MM_DD);
                if (curDateWeek != limitWeek) {
                    continue;
                } else {
                    System.out.println(curDate + "限定周一");
                }
            }


            //获取每个交易日的所有5分钟k线
            List<Kline> klineCurDate5MinuteList = KlineService.kline(zqdm, 0, klt, true, curDate, curDate, klineType);
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
                klineDto.setCloseAmt(klineCurDate5Minute.getCloseAmt());
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
//            System.out.println(StockUtil.formatStName(++i+"",4) + StockUtil.formatStName(klineDto.getZqmc(),10) + StockUtil.formatStName(klineDto.getKtime().substring(0,5),10) + StockUtil.formatDouble(klineDto.getCloseAmt(),10));
            System.out.println(StockUtil.formatStName(++i + "", 4) + StockUtil.formatStName(klineDto.getZqmc(), 16) + StockUtil.formatStName(klineDto.getKtime().substring(0, 5), 10) + StockUtil.formatDouble(klineDto.getScore(), 10));
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
     * etf-超过均线:
     * 显示最近3个K线交易日的涨跌
     *
     * @param date
     */
    public static void showEtfUpMa(String date) {
        String spDate = DateUtil.getAddDays(YYYY_MM_DD, date, 1);//是否显示特定日期涨跌   "2022-05-18"
//        String spDate = "2022-09-27";//

        CondMa condMa = new CondMa();
        condMa.setOrderField(ORDER_FIELD_NET_AREA_DAY_5);//ORDER_FIELD_NET_AREA_DAY_5 ORDER_FIELD_F3 ORDER_FIELD_MAXDOWN    ORDER_FIELD_MINRISE
        condMa.setDate(date);
        condMa.setDays(3);
        condMa.setSpDate(spDate);
        condMa.setShowPriceArea(true);//是否显示价格区间
        condMa.setKltList(Arrays.asList(KLT_5, KLT_15, KLT_30, KLT_60, KLT_101));//价格区间周期列表
        condMa.setShowUpMa(true);//是否显示-超过均线
        condMa.setShowDownMa(true);//是否显示-跌落均线
        condMa.setFindKline(true);//是否查询最新k线
        condMa.setShowFlowIn(false);//是否显示资金流入
        condMa.setOrderDesc(false);//是否倒序
        condMa.setShowDateMinMax(true);//是否显示日最低点、最高点
        condMa.setShowMaxMin(true);//是否显最低、最高

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
            String name = StockUtil.formatEtfName(r.getF14(), 4);
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
}
