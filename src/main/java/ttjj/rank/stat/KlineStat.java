package ttjj.rank.stat;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import ttjj.dao.KlineDao;
import ttjj.dto.*;
import ttjj.service.KlineService;
import ttjj.service.StockService;
import utils.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static utils.Content.*;

/**
 * k线
 */
public class KlineStat {
    public static void main(String[] args) {
//        // 统计涨跌次数-根据每月中的日期
//        String zqmc = ZHISHU_NAME_399673;//ZHISHU_NAME_399673 ZHISHU_NAME_000001
//        statAdrCountByDay(zqmc);

        //区间涨幅
//        statListAdrArea(DB_RANK_BIZ_TYPE_ETF);
//        statListAdrArea(DB_RANK_BIZ_TYPE_BAN_KUAI);
//        statListAdrArea(DB_RANK_BIZ_TYPE_GAI_NIAN);

        statAdrByTime();//      统计涨幅-分时

//        statAdrCjlCnA();//统计中国A股全市场
//        statAdrCjl(ContIndex.SHANG_HAI);
//        statAdrCjl(ContIndex.SHEN_ZHEN);
//        statAdrCjl(ContIndex.CYB);
//        statAdrCjl(ContIndex.ZZ_1000);
//        findKline();


    }

    /**
     * 统计中国A股全市场
     */
    private static void statAdrCjlCnA() {
        int days = 1;
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-11-04";
        List<String> dateList = new ArrayList<>();
        if (CACHE_DATE_LIST.size() >= days) {
            dateList = CACHE_DATE_LIST.subList(0, 20);
        } else {
            dateList = StockService.findListDateBefore(date, days);
            CACHE_DATE_LIST = dateList;
        }
        for (String curDate : dateList) {
            statAdrCjlCnA(curDate);
            System.out.println();
        }
    }

    /**
     * 统计中国A股全市场-根据日期
     *
     * @param date
     */
    private static void statAdrCjlCnA(String date) {
        String klt = Content.KLT_15;//KLT_120  KLT_120  KLT_101
        boolean isShowAdr = true;
        String preTradeDay = StockService.findBegDate(date, 1);//上一交易日
        KlineDto klineDtoShangHai = handlerKlinePreDay(ContIndex.SHANG_HAI, preTradeDay, date, klt);//获得k线信息：前一交易日信息
        KlineDto klineDtoShenZhen = handlerKlinePreDay(ContIndex.SHEN_ZHEN, preTradeDay, date, klt);//获得k线信息：前一交易日信息

        List<KlineDto> klineListPreDayShangHai = klineDtoShangHai.getPreDayKlineList();
        List<KlineDto> klineListPreDayShenZhen = klineDtoShenZhen.getPreDayKlineList();
        for (KlineDto klineShenZhen : klineListPreDayShenZhen) {
            for (KlineDto klineShangHai : klineListPreDayShangHai) {
                if (klineShangHai.getKtime().equals(klineShenZhen.getKtime())) {
                    klineShangHai.setCje(klineShangHai.getCje().add(klineShenZhen.getCje()));
                    klineShangHai.setCjl(klineShangHai.getCjl().add(klineShenZhen.getCjl()));
                }
            }
        }

        List<Kline> klineListShangHai = klineDtoShangHai.getKlineList();
        List<Kline> klineListShenZhen = klineDtoShenZhen.getKlineList();
        for (Kline klineShenZhen : klineListShenZhen) {
            for (Kline klineShangHai : klineListShangHai) {
                if (klineShangHai.getKtime().equals(klineShenZhen.getKtime())) {
                    klineShangHai.setCje(klineShangHai.getCje().add(klineShenZhen.getCje()));
                    klineShangHai.setCjl(klineShangHai.getCjl().add(klineShenZhen.getCjl()));


                }
            }
        }
        klineDtoShangHai.setZqmc("A股");

        //涨/平/跌
        BigDecimal zero = new BigDecimal("0");
        int countUp = StockService.count(new CondStock(date, null, null, Arrays.asList(F139_BK_B, F139_BK_BJS), null, zero, null));
        int countFlat = StockService.count(new CondStock(date, null, null, Arrays.asList(F139_BK_B, F139_BK_BJS), zero, null, null));
        int countDown = StockService.count(new CondStock(date, null, null, Arrays.asList(F139_BK_B, F139_BK_BJS), null, null, zero));
        klineDtoShangHai.setCountUp(countUp);
        klineDtoShangHai.setCountFlat(countFlat);
        klineDtoShangHai.setCountDown(countDown);

        showInfoKlineCompPreDay(klineDtoShangHai, isShowAdr);//显示k线与前一日比较数据
    }

    /**
     * 区间涨幅
     */
    private static void statListAdrArea(String bizType) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-12-08";
        boolean isAllList = true;//是否显示全列表
//        boolean isAllList = false;//是否显示全列表
        int areaDays = 0;//4:近一周;20:近一月
        int days = 0;
        List<String> dateList = StockService.findListDateBefore(date, days);
        for (String curDate : dateList) {
            statListAdrArea(NUM_MAX_999, curDate, areaDays, bizType, isAllList);//K线：统计区间涨幅,etf NUM_MAX_999
        }
    }

    /**
     * K线：统计区间涨幅,
     * etf,限定时间段(结束时间)
     * 排序：区间涨幅、市值、主力净流入、流市比
     * 查询结束日期的后一日的k线涨幅、最高涨幅、最低涨幅
     * 查询主力净流入之和、主力净流入之和与市值比
     * 查询当日分时端主流、涨幅
     *
     * @param endDate
     */
    private static void statListAdrArea(int limit, String endDate, int areaDays, String bizType, boolean isAllList) {
        boolean isFindSpDate = true;//查询结束日期的后一日的k线涨幅
//        boolean isFindSpDate = false;//查询结束日期的后一日的k线涨幅
        //每周一放量板块是否本周继续上涨
//        String endDate = StockService.findBegDate(date, 0);
        String begDate = StockService.findBegDate(endDate, areaDays);
        String spDate = endDate;
//        String spDate = "2022-12-09";

        //查询交易日天数
        DateCond dateCond = new DateCond();
        dateCond.setBegDate(begDate);
        dateCond.setEndDate(endDate);
        List<String> dateList = StockService.findListDateByBegToEnd(dateCond);

        Map<String, String> etfMap = ContMapEtf.INDEX_MORE;//ETF_MORE   INDEX_MORE
//        Map<String, String> etfMap = ContMapEtf.INDEX_ALL;//ETF_MORE   INDEX_MORE
        String orderField = ORDER_FIELD_AREA_ADR;//ORDER_FIELD_AREA_ADR ORDER_FIELD_FLOW_IN_MAIN_PCT    ORDER_FIELD_FLOW_IN_MAIN    ORDER_FIELD_FLOW_IN_MAIN_SUM    ORDER_FIELD_FLOW_IN_MAIN_PCT_SUM
        if (DB_RANK_BIZ_TYPE_ETF.equals(bizType) && ContMapEtf.INDEX_MORE.equals(etfMap)) {
            orderField = ORDER_FIELD_AREA_ADR;
        }
        String klt = KLT_101;//KLT_60
        String ktime = endDate;//时间段(结束时间)
//        String klt = KLT_30;//KLT_60
//        String ktime = "14:00:00";//时间段(结束时间)
        boolean isShowCode = true;//是否显示编码
        boolean isShowMoreYes = true;
        List<CondKline> rsList = statListAdrArea(bizType, etfMap, limit, begDate, endDate, klt, ktime);

        //区间涨幅
        if (isAllList) {
            System.out.println(begDate + "至" + endDate);// + "上涨：");
            KlineService.showKlineAllList(rsList, begDate, endDate, dateList, limit, isShowMoreYes, isShowCode, klt, ktime, true, orderField, isFindSpDate, spDate, bizType);
//            System.out.println(begDate + "至" + endDate + "下跌：");
//            KlineService.showKlineAllList(rsList, begDate, endDate, limit, isShowMoreYes, isShowCode, klt, ktime, false, orderField);
        }


        System.out.println();
        System.out.println(begDate + "至" + endDate + "上涨：");
        KlineService.showInfoEtfSimple(rsList, 10, true);
        System.out.println(begDate + "至" + endDate + "下跌：");
        KlineService.showInfoEtfSimple(rsList, 10, false);
        System.out.println();

        //更新复权：前复权，检查当日K线与数据库的数据是否相符，如果不符，进行复权更新

    }


    /**
     * @param type
     * @param etfMap
     * @param limit
     * @param begDate
     * @param endDate
     * @param klt
     * @param ktime
     * @return
     */
    private static List<CondKline> statListAdrArea(String type, Map<String, String> etfMap, int limit, String begDate, String endDate, String klt, String ktime) {
//        String type = DB_RANK_BIZ_TYPE_ETF;//DB_RANK_BIZ_TYPE_BAN_KUAI  DB_RANK_BIZ_TYPE_ETF   DB_RANK_BIZ_TYPE_GAI_NIAN
//        Map<String, String> etfMap = KEJI_MORE;//INDEX_ALL     INDEX_MORE   XIAOFEI_ALL_TO_MORE    KEJI_MORE
        boolean isCheckFuQuan = false;//是否检查更新复权
        boolean isOrMianEtf = false;//是否必须查询我的主要etf
//        boolean isCheckMianEtf = true;//是否必须查询我的主要etf
        boolean isCheckMianEtf = false;//是否必须查询我的主要etf
        boolean isShowEtfInfo = false;//是否显示etf信息
        boolean isFindFlowInMainSum = true;//是否查询主力净流入合计
        BigDecimal mvMin = null;
        BigDecimal mvMax = null;
        Map<String, CondKline> rsMap = new HashMap<>();
        CondKline condEndDate = new CondKline();
        condEndDate.setDate(endDate);
        condEndDate.setType(type);
        condEndDate.setKlt(klt);
        condEndDate.setKtime(ktime);//时间类型
        if (type.equals(DB_RANK_BIZ_TYPE_ETF)) {//检查是否是主要etf
            condEndDate.setStCodeList(EtfUtil.getMainEtf(etfMap));
        }
        //查询k线，db
        List<Kline> klineListEndDate = KlineService.listKine(condEndDate);

        for (Kline kline : klineListEndDate) {
            CondKline rsOne = new CondKline();
            rsOne.setZqmc(kline.getZqmc());
            rsOne.setZqdm(kline.getZqdm());
            rsOne.setZhangDieFu(kline.getZhangDieFu());
            BigDecimal marketValue = null;
            if (kline.getF20() != null) {
                marketValue = kline.getF20().divide(NUM_YI_1, 2, BigDecimal.ROUND_HALF_UP);
            }

            rsOne.setFlowInMain(kline.getFlowInMain());
            rsOne.setF20(marketValue);
            rsOne.setBegDate(begDate);
            rsOne.setEndDate(endDate);
            rsOne.setEndDateF2(kline.getCloseAmt());
            rsMap.put(kline.getZqdm(), rsOne);
        }

        CondKline condBegDate = new CondKline();
        condBegDate.setDate(begDate);
        condBegDate.setType(type);
        condBegDate.setKlt(klt);
        if (KLT_101.equals(klt)) {
            condBegDate.setKtime(begDate);//时间类型
        } else {
            condBegDate.setKtime(ktime);//时间类型
        }
        if (isCheckMianEtf && type.equals(DB_RANK_BIZ_TYPE_ETF)) {//检查是否是主要etf
            condBegDate.setStCodeList(EtfUtil.getMainEtf(etfMap));
        }
        List<Kline> klineListBegDate = KlineService.listKine(condBegDate);

        for (Kline etfBegDate : klineListBegDate) {
            String code = etfBegDate.getZqdm();
            BigDecimal yestodayNet = etfBegDate.getCloseLastAmt();
            CondKline rsOne = rsMap.get(code);
            if (rsOne == null) {
//                System.out.println("市值已减小：" + JSON.toJSONString(rankStockCommpanyDb));
                continue;
            }
            if (yestodayNet == null) {
//                System.out.println("昨日净值为空：" + JSON.toJSONString(rankStockCommpanyDb));
                continue;
            }
            rsOne.setBegDateF18(yestodayNet);
//            rsOne.setF18(yestodayNet);

            rsMap.put(code, rsOne);
        }

        List<CondKline> rsList = new ArrayList<>();
        //计算区间涨幅
        for (CondKline dto : rsMap.values()) {
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

            //流市比
            BigDecimal flowInMainSum = null;
            if (isFindFlowInMainSum) {
                //查询主力净流入
                CondKline condFlowInMain = new CondKline();
                condFlowInMain.setBegDate(begDate);
                condFlowInMain.setEndDate(endDate);
                condFlowInMain.setType(type);
                condFlowInMain.setKlt(klt);
                condFlowInMain.setZqdm(dto.getZqdm());
                List<Kline> klineListFlowInMain = KlineService.listKine(condFlowInMain);
                if (klineListFlowInMain != null) {
                    for (Kline kline : klineListFlowInMain) {
                        if (flowInMainSum == null) {
                            flowInMainSum = new BigDecimal("0");
                        }
                        if (kline.getFlowInMain() != null) {
                            flowInMainSum = flowInMainSum.add(kline.getFlowInMain());
                        }
                    }
                }
            }
            BigDecimal flowInMain = dto.getFlowInMain();
            BigDecimal flowRate = null;
            if (flowInMain != null) {
                flowRate = flowInMain.divide(dto.getF20(), 6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100").setScale(4, BigDecimal.ROUND_HALF_UP)).setScale(4, BigDecimal.ROUND_HALF_UP);
            }
            BigDecimal flowRateSum = null;
            if (flowInMainSum != null && dto.getF20() != null) {
                flowRateSum = flowInMainSum.divide(dto.getF20(), 6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100").setScale(4, BigDecimal.ROUND_HALF_UP)).setScale(4, BigDecimal.ROUND_HALF_UP);
            }
            dto.setFlowInMainPct(flowRate);
            dto.setFlowInMainSum(flowInMainSum);
            dto.setFlowInMainPctSum(flowRateSum);

            rsList.add(dto);
        }

        return rsList;
        //更新复权：前复权，检查当日K线与数据库的数据是否相符，如果不符，进行复权更新
    }

    private static void statAdrCjl(String zqdm) {
        int days = 1;
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-11-04";
        List<String> dateList = null;
        if (CACHE_DATE_LIST.size() >= days) {
            dateList = CACHE_DATE_LIST.subList(0, days);
        } else {
            dateList = StockService.findListDateBefore(date, days);
            CACHE_DATE_LIST = dateList;
        }
        for (String curDate : dateList) {
            String klt = Content.KLT_30;//KLT_120  KLT_120  KLT_101
            statAdrCjl(curDate, zqdm, klt);
            System.out.println();
        }
    }

    /**
     * K线:指数，统计成交量，增减，放量缩量，上涨下跌。
     */
    private static void statAdrCjl(String date, String zqdm, String klt) {
        String preTradeDay = StockService.findBegDate(date, 1);//上一交易日
        KlineDto klineDto = handlerKlinePreDay(zqdm, preTradeDay, date, klt);//获得k线信息：前一交易日信息
        showInfoKlineCompPreDay(klineDto, true);//显示k线与前一日比较数据
    }

    /**
     * 显示k线与前一日比较数据
     *
     * @param klineDto
     */
    private static void showInfoKlineCompPreDay(KlineDto klineDto, boolean isShowAdr) {
        Map<String, BigDecimal> cjeMap = new HashMap<>();
        List<KlineDto> klineTradeDaySub1List = klineDto.getPreDayKlineList();
        for (KlineDto kline : klineTradeDaySub1List) {
            BigDecimal cje = kline.getCje().divide(NUM_YI_1, 0, BigDecimal.ROUND_HALF_UP);
            String timeCur = kline.getKtime().substring(10);
            cjeMap.put(timeCur, cje);
//            System.out.println("上一交易日" + "(" + kline.getKtime() + ")" + StockUtil.formatStName("成交额(亿):" + cje, 20));
        }

        List<Kline> klineCurDay = klineDto.getKlineList();
        for (Kline kline : klineCurDay) {
            StringBuffer sb = new StringBuffer();
            BigDecimal cjeToday = kline.getCje().divide(NUM_YI_1, 0, BigDecimal.ROUND_HALF_UP);
            String timeCur = kline.getKtime().substring(10);
            BigDecimal cjeTradeDaySub1 = cjeMap.get(timeCur);
            BigDecimal cjeCompRs = cjeToday.subtract(cjeTradeDaySub1);
            BigDecimal cjeCompRsPt = cjeCompRs.divide(cjeTradeDaySub1, 2, RoundingMode.HALF_UP);//前一日量比
            sb.append(klineDto.getZqmc() + ":");
            sb.append("时间:" + kline.getKtime());
            sb.append(";成交额(亿):" + StockUtil.formatDouble(cjeToday, 5) + "-" + StockUtil.formatDouble(cjeTradeDaySub1, 5) + "=");
            sb.append("(" + StockUtil.formatDouble(cjeCompRs, 5) + ")亿,");

            long klineTime = Calendar.getInstance().getTimeInMillis();
            if (kline.getKtime().length() > 10) {
                klineTime = DateUtil.getTimeInMillisByDateStr(DateUtil.YYYY_MM_DD_HH_MM_SS, kline.getKtime());
            }

            long curTime = Calendar.getInstance().getTimeInMillis();
            if (cjeCompRs.compareTo(new BigDecimal("0")) > 0) {
                //如果k线时间大于当前时间，不比较
                if (klineTime <= curTime) {
                    sb.append("放量");
                    sb.append(StockUtil.formatDouble(cjeCompRsPt, 5) + "%");
                } else {
                    sb.append("    ");
                    sb.append(StockUtil.formatDouble(cjeCompRsPt, 5) + "%");
                }
            } else {
                if (klineTime <= curTime) {
                    sb.append("缩量");
                    sb.append(StockUtil.formatDouble(cjeCompRsPt, 5) + "%");
                } else {
                    sb.append("    ");
                    sb.append(StockUtil.formatDouble(cjeCompRsPt, 5) + "%");
                }
            }
            if (isShowAdr) {
                BigDecimal adr = kline.getZhangDieFu();
                if (adr.compareTo(new BigDecimal("0")) > 0) {
                    sb.append(",上涨：");
                } else {
                    sb.append(",下跌：");
                }
                sb.append(StockUtil.formatDouble(adr, 6));
            }

            //涨跌个数
            sb.append(";");
            sb.append("涨/平/跌:").append(klineDto.getCountUp() + "/" + klineDto.getCountFlat() + "/" + klineDto.getCountDown());

            System.out.println(sb);
        }
    }

    /**
     * //获得k线信息：当前日信息与前一交易日信息差异
     *
     * @param zqdm
     * @param date
     * @param klt
     * @param klineDto
     */
    private static void handlerKlineCurDay(String zqdm, String date, String klt, KlineDto klineDto) {

    }

    /**
     * 获得k线信息：前一交易日信息
     *
     * @param zqdm
     * @param preTradeDay
     * @param klt
     */
    private static KlineDto handlerKlinePreDay(String zqdm, String preTradeDay, String date, String klt) {
        KlineDto klineDto = new KlineDto();
        String zqmc = Content.zhishuMap.get(zqdm);
        klineDto.setZqdm(zqdm);
        klineDto.setZqmc(zqmc);

        List<KlineDto> preDayKlineList = new ArrayList<>();
        List<Kline> klineTradeDaySub1 = KlineService.kline(zqdm, NUM_MAX_999, klt, true, preTradeDay, preTradeDay, DB_RANK_BIZ_TYPE_ZS);
        for (Kline kline : klineTradeDaySub1) {
            KlineDto preDayKline = new KlineDto();
            BeanUtils.copyProperties(kline, preDayKline);
            preDayKlineList.add(preDayKline);
        }
        klineDto.setPreDayKlineList(preDayKlineList);

        //查询K线-今日
        List<Kline> klineCurDay = KlineService.kline(zqdm, NUM_MAX_999, klt, true, date, date, DB_RANK_BIZ_TYPE_ZS);
        klineDto.setKlineList(klineCurDay);

        //查询涨跌家数
        handlerAdrCount(klineDto, zqdm, date);


        return klineDto;
    }

    /**
     * 涨跌家数
     *
     * @param klineDto
     * @param zqdm
     */
    private static void handlerAdrCount(KlineDto klineDto, String zqdm, String date) {
        BigDecimal zero = new BigDecimal("0");
        if (ContIndex.SHANG_HAI.equals(zqdm)) {
            klineDto.setCountUp(StockService.count(new CondStock(date, F13_SHANGHAI, null, Arrays.asList(F139_BK_B, F139_BK_BJS), null, zero, null)));
            klineDto.setCountFlat(StockService.count(new CondStock(date, F13_SHANGHAI, null, Arrays.asList(F139_BK_B, F139_BK_BJS), zero, null, null)));
            klineDto.setCountDown(StockService.count(new CondStock(date, F13_SHANGHAI, null, Arrays.asList(F139_BK_B, F139_BK_BJS), null, null, zero)));
        }
        if (ContIndex.SHEN_ZHEN.equals(zqdm)) {
            klineDto.setCountUp(StockService.count(new CondStock(date, F13_SHENZHEN, null, Arrays.asList(F139_BK_B, F139_BK_BJS), null, zero, null)));
            klineDto.setCountFlat(StockService.count(new CondStock(date, F13_SHENZHEN, null, Arrays.asList(F139_BK_B, F139_BK_BJS), zero, null, null)));
            klineDto.setCountDown(StockService.count(new CondStock(date, F13_SHENZHEN, null, Arrays.asList(F139_BK_B, F139_BK_BJS), null, null, zero)));
        }
    }

    /**
     * 统计区间涨幅：分时统计。排序：按照指定字段排序：主力净流入百分比
     */
    private static void statAdrByTime() {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-12-14";

//        String type = DB_RANK_BIZ_TYPE_ETF;
        String type = DB_RANK_BIZ_TYPE_BAN_KUAI;
//        String type = DB_RANK_BIZ_TYPE_GAI_NIAN;

//        String klt = KLT_101;
//        String klt = KLT_60;
        String klt = KLT_30;
//        String klt = KLT_15;
//        String klt = KLT_5;

        //        boolean isMainEtf = true;
        boolean isMainEtf = false;
        boolean isShowOnlyUp = true;
//        boolean isShowOnlyUp = false;

//        boolean isShowOnlyDown = true;
        boolean isShowOnlyDown = false;

//        String orderTime = TIME_11_30;//TIME_10_30 TIME_11_30  TIME_14_00   TIME_15_00 TIME_09_45, TIME_10_00, TIME_10_15, TIME_10_30, TIME_10_45, TIME_11_00, TIME_11_15, TIME_11_30, TIME_13_15, TIME_13_30, TIME_13_45, TIME_14_00, TIME_14_15, TIME_14_30, TIME_14_45, TIME_15_00
        List<String> orderTimeList = new ArrayList<>();
        if (klt.equals(KLT_5)) {
//            orderTimeList = TIME_TYPE_5_1305_TO_1330;
            orderTimeList = TIME_TYPE_5_0935_TO_1500;
        } else if (klt.equals(KLT_15)) {
            orderTimeList = TIME_TYPE_15_0945_TO_1500;
//            orderTimeList = TIME_TYPE_15_1315_TO_1500;
        } else if (klt.equals(KLT_30)) {
            orderTimeList = TIME_TYPE_30_1000_TO_1500;
//            orderTimeList = TIME_TYPE_30_1500_TO_1000;
        } else if (klt.equals(KLT_60)) {
            orderTimeList = TIME_TYPE_60_1030_TO_1500;
        } else if (klt.equals(KLT_101)) {
            orderTimeList.add(date);
        }
        for (String time : orderTimeList) {
            statAdrByTime(date, time, klt, false, type, isMainEtf, isShowOnlyUp, isShowOnlyDown);
        }
    }

    /**
     * 统计涨幅-根据时间、类型等:如果未到当前时间，不处理
     */
    private static void statAdrByTime(String date, String time, String klt, boolean isShowSimpleUpOrDown, String type, boolean isMainEtf, boolean isShowOnlyUp, boolean isShowOnlyDown) {
        //如果未到当前时间，不处理
        String datTime = date + " " + time;
        if (datTime.length() == 19) {
            long timeLong = DateUtil.getTimeInMillisByDateStr(DateUtil.YYYY_MM_DD_HH_MM_SS, datTime);
            long curTime = Calendar.getInstance().getTimeInMillis();
            if (timeLong > curTime) {
//                System.out.println("如果未到当前时间，不处理:" + datTime);
                return;
            }
        }

        //首先查询k线类别
        //按时间列表查询k线涨幅
        if (isShowSimpleUpOrDown) {
            showUpOrDownInfo(date, type, klt, "0", "0", 100);//显示-A股当前时段上涨和下跌
        }
        showKline(date, type, klt, time, klt, isMainEtf, false, isShowOnlyUp, new BigDecimal("0"), isShowOnlyDown, new BigDecimal("0"));
    }

    private static void showUpOrDownInfo(String date, String type, String klt, String adrUp, String adrDown, int limit) {
        int sizeName = 14;
        int sizeAdr = 6;
        boolean isCheckMianEtf = true;//是否必须查询我的主要etf
        Map<String, String> etfMap = ContMapEtf.ETF_MORE;

        System.out.println("A股主要ETF涨幅榜");
        int i = 0;//计数器
        List<String> timeList = getTimeList(date, klt);
        for (String time : timeList) {
            StringBuffer sbHead = new StringBuffer();
            sbHead.append("A股主要");
            if (type.equals(DB_RANK_BIZ_TYPE_ETF)) {
                sbHead.append("(ETF)");
            }
            sbHead.append("(上涨)");
            sbHead.append("(" + date + ")");
            if (!klt.equals(KLT_101)) {
                sbHead.append("(第" + (++i) + "个)");
            } else {
                sbHead.append("(全天):");
            }
            if (klt.equals(KLT_60)) {
                sbHead.append("(小时):");
            }
            if (klt.equals(KLT_30)) {
                sbHead.append("(半小时):");
            }
            if (klt.equals(KLT_15)) {
                sbHead.append("(刻钟):");
            }
            if (klt.equals(KLT_5)) {
                sbHead.append("(5分钟):");
            }

            CondKline conditionKlineList = new CondKline();
            conditionKlineList.setDate(date);
            conditionKlineList.setType(type);
            conditionKlineList.setKlt(klt);
            if (klt.equals(KLT_101)) {
                conditionKlineList.setKtime(date);
            } else {
                conditionKlineList.setKtime(time);//排序时间点
            }
            if (isCheckMianEtf && type.equals(DB_RANK_BIZ_TYPE_ETF)) {//检查是否是主要etf
                conditionKlineList.setStCodeList(EtfUtil.getMainEtf(etfMap));
            }
            List<Kline> klineList = KlineService.listKine(conditionKlineList);
            if (klineList == null || klineList.size() == 0) {
//                System.out.println("klineList==null");
//                break;
            }

            //上涨
            System.out.println(sbHead);
            int curNum = limit;
            for (Kline dto : klineList) {
                //检查是否是主要etf
                if (!EtfUtil.isMainEtf(dto.getZqdm())) {
                    continue;
                }

                BigDecimal adr = dto.getZhangDieFu();
                if (adr.compareTo(new BigDecimal(adrUp)) >= 0) {
                    if (--curNum < 0) {
                        break;//限定个数
                    }
                    StringBuffer sb = new StringBuffer();
//                    sb.append(StockUtil.formatEtfName(dto.getZqmc(), 0));
                    sb.append(StockUtil.formatStName(dto.getZqmc(), sizeName));
                    sb.append(":");
                    sb.append(StockUtil.formatStr(adr + "%", sizeAdr));
                    sb.append(";");
                    System.out.println(sb);
                }
            }
            System.out.println();

            //下跌
            System.out.println(sbHead.toString().replace("(上涨)", "(下跌)"));
            klineList = klineList.stream().filter(e -> e != null).sorted(Comparator.comparing(Kline::getZhangDieFu, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            curNum = limit;
            for (Kline dto : klineList) {
                //检查是否是主要etf
                if (!EtfUtil.isMainEtf(dto.getZqdm())) {
                    continue;
                }
                BigDecimal adr = dto.getZhangDieFu();
                if (adr.compareTo(new BigDecimal(adrDown)) < 0) {
                    if (--curNum < 0) {
                        break;//限定个数
                    }
                    StringBuffer sb = new StringBuffer();
//                    sb.append(StockUtil.formatEtfName(dto.getZqmc(), 0));
                    sb.append(StockUtil.formatStName(dto.getZqmc(), sizeName));
                    sb.append(":");
                    sb.append(StockUtil.formatStr(adr + "%", sizeAdr));
                    sb.append(";");
                    System.out.println(sb);
                }
            }
            System.out.println();
        }

    }


    /**
     * 显示k线数据
     *
     * @param date
     * @param type
     * @param klt
     * @param orderTime
     * @param orderKlt
     * @param isMainEtf
     * @param isShowAll 是否显示所有
     */
    private static void showKline(String date, String type, String klt, String orderTime, String orderKlt, boolean isMainEtf, boolean isShowAll, boolean isOnlyUp, BigDecimal up, boolean isOnlyDown, BigDecimal down) {
        String bizName = null;
//        String bizName = "半导体";
        boolean isShowCode = false;
        //排序列表
        CondKline cond = new CondKline();
        cond.setDate(date);
        cond.setType(type);
        cond.setKlt(orderKlt);
        cond.setKtime(orderTime);//排序时间点
        List<CondKline> orderDtoList = new ArrayList<>();
        List<Kline> orderList = KlineService.listKine(cond);
        if (orderList == null) {
            System.out.println("klineList==null");
            return;
        }
        for (Kline kline : orderList) {
            CondKline condKline = new CondKline();
            BeanUtils.copyProperties(kline, condKline);
            BigDecimal flowInMain = kline.getFlowInMain();
            BigDecimal mv = kline.getF20();
            if (flowInMain != null && mv != null) {
                BigDecimal flowRate = flowInMain.divide(mv, 12, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100000").setScale(4, BigDecimal.ROUND_HALF_UP)).setScale(1, BigDecimal.ROUND_HALF_UP);
                condKline.setFlowInMainPct(flowRate);
            }
            orderDtoList.add(condKline);
        }

        List<String> timeList = getTimeList(date, klt);//获取时段列表，根据时间类型

        Map<String, Integer> sizeMap = new HashMap<>();


        //查询当日涨幅
        CondKline condCurDay = new CondKline();
        condCurDay.setDate(date);
        condCurDay.setType(type);
        condCurDay.setKlt(KLT_101);
        List<Kline> listKlineCurDay = KlineService.listKine(condCurDay);
        Map<String, Kline> mapKlineListCurDay = new HashMap<>();
        for (Kline kline : listKlineCurDay) {
            mapKlineListCurDay.put(kline.getZqdm(), kline);
        }

        //排序：按照指定字段排序。
        String orderField = ORDER_FIELD_FLOW_IN_MAIN;//ORDER_FIELD_AREA_ADR ORDER_FIELD_FLOW_IN_MAIN_PCT
        boolean isDesc = true;
        orderDtoList = KlineService.handlerOrderKline(orderDtoList, orderField, isDesc);//列表-排序：根据字段

        List<StringBuffer> sbList = null;
        if (isShowAll) {
            sbList = handlerInfo(orderDtoList, mapKlineListCurDay, date, type, klt, timeList, isMainEtf, false, null, false, null, sizeMap, bizName);

            for (StringBuffer sb : sbList) {
                System.out.println(sb);
            }
        } else {
            //显示上涨
            if (isOnlyUp) {
                System.out.println();
                System.out.println(new StringBuffer("当前时间：").append(date + " " + orderTime).append(",上涨列表"));
                sbList = handlerInfo(orderDtoList, mapKlineListCurDay, date, type, klt, timeList, isMainEtf, true, up, false, down, sizeMap, bizName);
                for (StringBuffer sb : sbList) {
                    System.out.println(sb);
                }
            }

            //显示下跌
            if (isOnlyDown) {
                System.out.println();
                System.out.println(new StringBuffer("当前时间：").append(orderTime).append(",下跌列表"));
                sbList = handlerInfo(orderDtoList, mapKlineListCurDay, date, type, klt, timeList, isMainEtf, false, up, true, down, sizeMap, bizName);
                for (StringBuffer sb : sbList) {
                    System.out.println(sb);
                }
            }
//            showUpOrDownInfoByList(date, type, orderKlt, orderTime, orderList, true, up, false, down, 100);//显示-A股当前时段上涨和下跌
//            showUpOrDownInfoByList(date, type, orderKlt, orderTime, orderList, false, up, true, down, 100);//显示-A股当前时段上涨和下跌
        }
    }

    /**
     * 显示简约的信息
     *
     * @param date
     * @param type
     * @param klt
     * @param orderTime
     * @param orderList
     * @param isOnlyUp
     * @param adrUp
     * @param isOnlyDown
     * @param adrDown
     */
    private static void showUpOrDownInfoByList(String date, String type, String klt, String orderTime, List<Kline> orderList, boolean isOnlyUp, BigDecimal adrUp, boolean isOnlyDown, BigDecimal adrDown, int limit) {
        int sizeName = 14;
        int sizeAdr = 6;
        if (orderList == null) {
            System.out.println("orderList==null");
        }
        List<String> timeList = getTimeList(date, klt);//获取时段列表，根据时间类型
        int i = 0;//计数器
        for (String time : timeList) {
            StringBuffer sbHead = new StringBuffer();
            sbHead.append("A股主要");
            if (type.equals(DB_RANK_BIZ_TYPE_ETF)) {
                sbHead.append("(ETF)");
            }
            if (isOnlyUp) {
                sbHead.append("(上涨)");
            }
            if (isOnlyDown) {
                sbHead.append("(下跌)");
            }
            sbHead.append("(" + date + ")");
            if (!klt.equals(KLT_101)) {
                sbHead.append("(第" + (++i) + "个)");
            } else {
                sbHead.append("(全天):");
            }
            if (klt.equals(KLT_60)) {
                sbHead.append("(小时):");
            }
            if (klt.equals(KLT_30)) {
                sbHead.append("(半小时):");
            }
            if (klt.equals(KLT_15)) {
                sbHead.append("(刻钟):");
            }
            if (klt.equals(KLT_5)) {
                sbHead.append("(5分钟):");
            }
            if (orderTime.equals(time)) {
                System.out.println(sbHead);
                break;
            } else {
                sbHead = new StringBuffer();
            }
        }

        //上涨
        int curNum = limit;
        if (isOnlyDown) {
            orderList = orderList.stream().filter(e -> e != null).sorted(Comparator.comparing(Kline::getZhangDieFu, Comparator.nullsLast(BigDecimal::compareTo))).collect(Collectors.toList());
        }
        for (Kline dto : orderList) {
            //检查是否是主要etf
            if (!EtfUtil.isMainEtf(dto.getZqdm())) {
                continue;
            }

            if (isOnlyUp) {
                BigDecimal adr = dto.getZhangDieFu();
                if (adr.compareTo(adrUp) > 0) {
                    if (--curNum < 0) {
                        break;//限定个数
                    }
                    StringBuffer sb = new StringBuffer();
//                    sb.append(StockUtil.formatEtfName(dto.getZqmc(), 0));
                    sb.append(StockUtil.formatStName(dto.getZqmc(), sizeName));
                    sb.append(":");
                    sb.append(StockUtil.formatStr(adr + "%", sizeAdr));
                    sb.append(";");
                    System.out.println(sb);
                }
            }

            if (isOnlyDown) {
                BigDecimal adr = dto.getZhangDieFu();
                if (adr.compareTo(adrDown) <= 0) {
                    if (--curNum < 0) {
                        break;//限定个数
                    }
                    StringBuffer sb = new StringBuffer();
//                    sb.append(StockUtil.formatEtfName(dto.getZqmc(), 0));
                    sb.append(StockUtil.formatStName(dto.getZqmc(), sizeName));
                    sb.append(":");
                    sb.append(StockUtil.formatStr(adr + "%", sizeAdr));
                    sb.append(";");
                    System.out.println(sb);
                }
            }

        }
        System.out.println();
    }

    /**
     * 处理信息
     *
     * @param orderList
     * @param mapKlineListCurDay
     * @param date
     * @param type
     * @param klt
     * @param timeList
     * @param isMainEtf
     * @param isOnlyUp           只处理上涨
     * @param sizeMap
     * @param bizName
     * @return
     */
    private static List<StringBuffer> handlerInfo(List<CondKline> orderList, Map<String, Kline> mapKlineListCurDay, String date, String type, String klt, List<String> timeList, boolean isMainEtf, boolean isOnlyUp, BigDecimal up, boolean isOnlyDown, BigDecimal down, Map<String, Integer> sizeMap, String bizName) {
        int sizeName = 16;
        int sizeAdr = 10;
        int sizeFlowInCurDay = 6;
        String flowInMainName = "流率";
        int sizeFlowInMainName = 4;
        sizeMap.put(flowInMainName, sizeFlowInMainName);
        String keyFlowInMain = "流入";
        int keySizeFlowInMain = 6;
        sizeMap.put(keyFlowInMain, keySizeFlowInMain);
        String timePried = "时段";
        int sizeTime = 6;
        sizeMap.put(timePried, sizeTime);
        StringBuffer sbHead = new StringBuffer();//首行标题信息
        boolean isShowCode = false;
        if (isShowCode) {
            sbHead.append(StockUtil.formatStName("编码", 10));
        }
//        sbHead.append("[");
        sbHead.append(StockUtil.formatStName("名称", sizeName));
//        sbHead.append("]");
        sbHead.append(StockUtil.formatStName("类型", 10));
//        sbHead.append(StockUtil.formatStName("排序时间", 14));
//        sbHead.append(StockUtil.formatStName("时间涨幅", sizeAdr));
        sbHead.append(StockUtil.formatStName("日期", 14));
        if (!klt.equals(KLT_101)) {
            sbHead.append(StockUtil.formatStName("日涨幅", sizeAdr));
            sbHead.append(StockUtil.formatStName("日主流", sizeFlowInCurDay));
        }
        for (String time : timeList) {
            sbHead.append(StockUtil.formatStName(time.substring(0, 6), 6));
//            sbHead.append(StockUtil.formatStName(flowInMainName, sizeMap.get(flowInMainName)));
            sbHead.append(StockUtil.formatStName(":" + keyFlowInMain, sizeMap.get(keyFlowInMain)));
        }
        System.out.println(sbHead);//首行标题信息);

        List<StringBuffer> sbList = new ArrayList<>();
//        if (isOnlyDown) {
//            orderList = orderList.stream().filter(e -> e != null).sorted(Comparator.comparing(CondKline::getZhangDieFu, Comparator.nullsLast(BigDecimal::compareTo))).collect(Collectors.toList());
////            orderList = orderList.stream().filter(e -> e != null).sorted(Comparator.comparing(CondKline::getFlowInMainPct, Comparator.nullsLast(BigDecimal::compareTo))).collect(Collectors.toList());
//        }
//        if (isOnlyUp) {
//            orderList = orderList.stream().filter(e -> e != null).sorted(Comparator.comparing(Kline::getZhangDieFu, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
////            orderList = orderList.stream().filter(e -> e != null).sorted(Comparator.comparing(CondKline::getFlowInMainPct, Comparator.nullsLast(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
//        }
        for (CondKline dto : orderList) {
            //检查是否是主要etf
            if (isMainEtf && !EtfUtil.isMainEtf(dto.getZqdm())) {
                continue;
            }
            if (bizName != null && !bizName.equals(dto.getZqmc())) {
                continue;
            }
//            if (isOnlyUp && dto.getZhangDieFu().compareTo(up) <= 0) {
//                break;
//            }
//            if (isOnlyDown && dto.getZhangDieFu().compareTo(down) >= 0) {
//                break;
//            }
//            System.out.println(JSON.toJSONString(stockAdrCount));
            StringBuffer sb = new StringBuffer();
            String code = StockUtil.formatStName(dto.getZqdm(), 6);
            String ktime = dto.getKtime();
            if (isShowCode) {
                sb.append(code).append("  ");
            }
//            sb.append("[");
            sb.append(StockUtil.formatStName(dto.getZqmc(), sizeName));
//            sb.append("]");
            sb.append(StockUtil.formatStName(dto.getType(), 10));
//            sb.append(StockUtil.formatStName(ktime, 14));
//            sb.append(StockUtil.formatDouble(dto.getZhangDieFu(), sizeAdr));
            //显示当日涨幅
            if (!klt.equals(KLT_101)) {
                sb.append(StockUtil.formatStName(date, 14));
                sb.append(StockUtil.formatDouble(mapKlineListCurDay.get(dto.getZqdm()).getZhangDieFu(), sizeAdr));
                if (mapKlineListCurDay.get(dto.getZqdm()) != null && mapKlineListCurDay.get(dto.getZqdm()).getFlowInMain() != null) {
                    BigDecimal flowInMain = mapKlineListCurDay.get(dto.getZqdm()).getFlowInMain().divide(NUM_YI_1, 1, BigDecimal.ROUND_HALF_UP);
                    sb.append(StockUtil.formatDouble(flowInMain, sizeFlowInCurDay));
                }
            }

            CondKline condKlineTime = new CondKline();
            condKlineTime.setDate(date);
            condKlineTime.setType(type);
            condKlineTime.setKlt(klt);
            condKlineTime.setZqdm(code);
            List<Kline> klineTimeList = KlineService.listKine(condKlineTime);
            for (String time : timeList) {
                if (klineTimeList == null || klineTimeList.size() == 0) {
                    break;
                }
                for (Kline klineTime : klineTimeList) {
                    if (!code.equals(klineTime.getZqdm())) {
                        continue;
                    }
                    if (time.equals(klineTime.getKtime())) {
//                        sb.append("[");
                        sb.append(StockUtil.formatDouble(klineTime.getZhangDieFu(), sizeMap.get(timePried)));
                        if (sizeMap.containsKey(flowInMainName)) {
                            if (klineTime.getFlowInMain() != null) {
                                BigDecimal flowInMain = klineTime.getFlowInMain();
                                BigDecimal flowInMainYi = klineTime.getFlowInMain().divide(NUM_YI_1, 1, BigDecimal.ROUND_HALF_UP);
//                                BigDecimal flowRate = flowInMain.divide(klineTime.getF20(), 12, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100000").setScale(4, BigDecimal.ROUND_HALF_UP)).setScale(0, BigDecimal.ROUND_HALF_UP);
//                                sb.append(StockUtil.formatStr(":" + flowRate, sizeMap.get(flowInMainName)));
                                sb.append(StockUtil.formatStr(":" + flowInMainYi, sizeMap.get(keyFlowInMain)));
                            } else {
                                sb.append(StockUtil.formatStr(":", sizeMap.get(flowInMainName)));
                            }
                        }
//                        sb.append("]");
                        break;
                    }
                }
            }
            sbList.add(sb);
        }
        return sbList;
    }

    /**
     * 首行标题信息
     *
     * @param timeList
     * @param klt
     * @param sizeMap
     * @return
     */
    private static StringBuffer handlerHeadInfo(List<String> timeList, String klt, Map<String, Integer> sizeMap) {
        int sizeName = 16;
        int sizeAdr = 10;
        int sizeFlowInCurDay = 6;
        String flowInMainName = "流率";
        String timePried = "时段";
        int sizeFlowInMainName = 8;
        int sizeTime = 6;
        sizeMap.put(flowInMainName, sizeFlowInMainName);
        sizeMap.put(timePried, sizeTime);
        StringBuffer sbHead = new StringBuffer();//首行标题信息
        boolean isShowCode = false;
        if (isShowCode) {
            sbHead.append(StockUtil.formatStName("编码", 10));
        }
//        sbHead.append("[");
        sbHead.append(StockUtil.formatStName("名称", sizeName));
//        sbHead.append("]");
        sbHead.append(StockUtil.formatStName("类型", 10));
//        sbHead.append(StockUtil.formatStName("排序时间", 14));
//        sbHead.append(StockUtil.formatStName("时间涨幅", sizeAdr));
        sbHead.append(StockUtil.formatStName("日期", 14));
        if (!klt.equals(KLT_101)) {
            sbHead.append(StockUtil.formatStName("日涨幅", sizeAdr));
            sbHead.append(StockUtil.formatStName("日主流", sizeFlowInCurDay));
        }
        for (String time : timeList) {
            sbHead.append(StockUtil.formatStName(time.substring(0, 6), 6));
            sbHead.append(StockUtil.formatStName(flowInMainName, sizeMap.get(flowInMainName)));
        }
        return sbHead;
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
