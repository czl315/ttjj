package ttjj.rank.stat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.RankStockCommpanyDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.*;
import ttjj.service.KlineService;
import ttjj.service.StockService;
import utils.Content;
import utils.DateUtil;
import utils.HttpUtil;
import utils.StockUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static utils.Content.*;

/**
 * 股票-统计
 */
public class StockStat {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-08-04";

        statListAdrArea();//计算区间涨幅

//        statStockUpDownCount(date);//股票涨跌个数

//        statSuperDropBounce();//  超跌反弹
//        statMaBreakUp();//突破均线
//        statFindListTongJj();//查询-统计数据-股票分组

    }

    /**
     * 股票涨跌个数:A股、上证指数沪市、深证成指深市、主板、创业板、科创板、B股、北交所
     *
     * @param date
     */
    private static void statStockUpDownCount(String date) {
        BigDecimal zero = new BigDecimal("0");
        boolean isShowAllCount = false;
        //查询个数

        if (isShowAllCount) {
            System.out.println("全市场个数-全部：" + StockService.count(new CondStock(date, null, null, null, null)));
        }
//        System.out.println("A股涨跌个数统计" + "(" + date + ")" + "(上午)");
        System.out.println("A股涨跌个数统计" + "(" + date + ")");

        int countUp = StockService.count(new CondStock(date, null, null, Arrays.asList(F139_BK_B), null, zero, null));
        int countFlat = StockService.count(new CondStock(date, null, null, Arrays.asList(F139_BK_B), zero, null, null));
        int countDown = StockService.count(new CondStock(date, null, null, Arrays.asList(F139_BK_B), null, null, zero));
        System.out.println(StockUtil.formatStName("A股(涨/平/跌):", 30) + StockUtil.formatStName(countUp + ":" + countFlat + ":" + countDown, 20));

        int countShUp = StockService.count(new CondStock(date, F13_SHANGHAI, null, Arrays.asList(F139_BK_B), null, zero, null));
        int countShFlat = StockService.count(new CondStock(date, F13_SHANGHAI, null, Arrays.asList(F139_BK_B), zero, null, null));
        int countShDown = StockService.count(new CondStock(date, F13_SHANGHAI, null, Arrays.asList(F139_BK_B), null, null, zero));
        System.out.print(StockUtil.formatStName("上证指数沪市(涨/平/跌):", 30) + StockUtil.formatStName(countShUp + ":" + countShFlat + ":" + countShDown, 20));
        CondKline conditionKlineList = new CondKline();
        conditionKlineList.setZqdm(ZHISHU_CODE_000001);
        conditionKlineList.setDate(date);
        conditionKlineList.setType(DB_RANK_BIZ_TYPE_ZS);
        conditionKlineList.setKlt(KLT_101);
        conditionKlineList.setKtime(date);
        List<Kline> klineList = KlineService.listKine(conditionKlineList);
        if (klineList != null && klineList.size() > 0) {
            Kline kline = klineList.get(0);
            System.out.print(StockUtil.formatStName("涨幅:" + kline.getZhangDieFu(), 20));
            BigDecimal cje = kline.getCje().divide(NUM_YI_1, 0, BigDecimal.ROUND_HALF_UP);
            System.out.print(StockUtil.formatStName("成交额(亿):" + cje, 20));
        }
        System.out.println();

        int countSzczUp = StockService.count(new CondStock(date, F13_SHENZHEN, null, Arrays.asList(F139_BK_BJS), null, zero, null));
        int countSzczFlat = StockService.count(new CondStock(date, F13_SHENZHEN, null, Arrays.asList(F139_BK_BJS), zero, null, null));
        int countSzczDown = StockService.count(new CondStock(date, F13_SHENZHEN, null, Arrays.asList(F139_BK_BJS), null, null, zero));
        System.out.print(StockUtil.formatStName("深证成指深市(涨/平/跌):", 30) + StockUtil.formatStName(countSzczUp + ":" + countSzczFlat + ":" + countSzczDown, 20));
        CondKline condSzcz = new CondKline();
        condSzcz.setZqdm(ZHISHU_CODE_399001);
        condSzcz.setDate(date);
        condSzcz.setType(DB_RANK_BIZ_TYPE_ZS);
        condSzcz.setKlt(KLT_101);
        condSzcz.setKtime(date);
        List<Kline> klineListSzcz = KlineService.listKine(condSzcz);
        if (klineListSzcz != null && klineListSzcz.size() > 0) {
            Kline kline = klineListSzcz.get(0);
            System.out.print(StockUtil.formatStName("涨幅:" + kline.getZhangDieFu(), 20));
            BigDecimal cje = kline.getCje().divide(NUM_YI_1, 0, BigDecimal.ROUND_HALF_UP);
            System.out.print(StockUtil.formatStName("成交额(亿):" + cje, 20));
        }
        System.out.println();

        int countUpZb = StockService.count(new CondStock(date, null, DB_RANK_BIZ_F139_BK_MAIN, null, null, zero, null));
        int countSzczFlatZb = StockService.count(new CondStock(date, null, DB_RANK_BIZ_F139_BK_MAIN, null, zero, null, null));
        int countSzczDownZb = StockService.count(new CondStock(date, null, DB_RANK_BIZ_F139_BK_MAIN, null, null, null, zero));
        System.out.println(StockUtil.formatStName("主板(涨/平/跌):", 30) + StockUtil.formatStName(countUpZb + ":" + countSzczFlatZb + ":" + countSzczDownZb, 20));

        int countUpCyb = StockService.count(new CondStock(date, null, F139_BK_CYB, null, null, zero, null));
        int countFlatCyb = StockService.count(new CondStock(date, null, F139_BK_CYB, null, zero, null, null));
        int countDownCyb = StockService.count(new CondStock(date, null, F139_BK_CYB, null, null, null, zero));
        System.out.println(StockUtil.formatStName("创业板(涨/平/跌):", 30) + StockUtil.formatStName(countUpCyb + ":" + countFlatCyb + ":" + countDownCyb, 20));

        int countUpKcb = StockService.count(new CondStock(date, null, F139_BK_KCB, null, null, zero, null));
        int countFlatKcb = StockService.count(new CondStock(date, null, F139_BK_KCB, null, zero, null, null));
        int countDownKcb = StockService.count(new CondStock(date, null, F139_BK_KCB, null, null, null, zero));
        System.out.println(StockUtil.formatStName("科创板(涨/平/跌):", 30) + StockUtil.formatStName(countUpKcb + ":" + countFlatKcb + ":" + countDownKcb, 20));

        int countUpBg = StockService.count(new CondStock(date, null, F139_BK_B, null, null, zero, null));
        int countFlatBg = StockService.count(new CondStock(date, null, F139_BK_B, null, zero, null, null));
        int countDownBg = StockService.count(new CondStock(date, null, F139_BK_B, null, null, null, zero));
        System.out.println(StockUtil.formatStName("B股(涨/平/跌):", 30) + StockUtil.formatStName(countUpBg + ":" + countFlatBg + ":" + countDownBg, 20));

        int countUpBjs = StockService.count(new CondStock(date, null, F139_BK_BJS, null, null, zero, null));
        int countFlatBjs = StockService.count(new CondStock(date, null, F139_BK_BJS, null, zero, null, null));
        int countDownBjs = StockService.count(new CondStock(date, null, F139_BK_BJS, null, null, null, zero));
        System.out.println(StockUtil.formatStName("北交所(涨/平/跌):", 30) + StockUtil.formatStName(countUpBjs + ":" + countFlatBjs + ":" + countDownBjs, 20));
    }

    /**
     * 股票：计算区间涨幅,涨幅榜、跌幅榜.
     * 计算区间涨幅：查询两个日期间的股票列表，计算净值之差，得出涨幅。
     */
    private static void statListAdrArea() {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-08-26";


        int areaDays = 4;//4:近一周;20:近一月
//        Long board = null;
        Long board = DB_RANK_BIZ_F19_BK_MAIN;
//        BigDecimal mvMin = null;//
        BigDecimal mvMin = NUM_YI_200;//NUM_YI_1000
        BigDecimal mvMax = null;
        int limit = 20;

        boolean isDesc = true;
//        boolean isDesc = false;

        boolean isNew = false;//是否统计新股

        String conception = null;
//        String conception = "上证50";//上证50  HS300

        statListAdrArea(date, areaDays, board, isDesc, mvMin, mvMax, limit, conception, isNew);

//        statListAdrArea(date, areaDays, board, isDesc, NUM_YI_500, null,limit,conception);
//        statListAdrArea(date, areaDays, board, isDesc, null, null,limit);
//        statListAdrArea(date, areaDays, board, isDesc, NUM_YI_200, NUM_YI_500,limit);
//        statListAdrArea(date, areaDays, board, isDesc, NUM_YI_50, NUM_YI_200,limit);
//        statListAdrArea(date, areaDays, board, isDesc, null, NUM_YI_50,limit);


    }

    /**
     * 统计区间涨幅
     * 20221016:股票:统计-统计区间涨幅-是否统计新股:根据标志，是否统计上市时间在30天以内新股
     *
     * @param date
     * @param areaDays
     * @param board
     * @param isDesc
     * @param mvMin      市值最低
     * @param mvMax      市值最高
     * @param limit
     * @param conception 概念
     * @param isNew      是否统计新股
     */
    private static void statListAdrArea(String date, int areaDays, Long board, boolean isDesc, BigDecimal mvMin, BigDecimal mvMax, int limit, String conception, boolean isNew) {
        boolean isShowCode = true;//是否显示编码
        boolean isCheckFuQuan = false;//是否检查更新复权

        String endDate = StockService.findBegDate(date, 0);
        String begDate = StockService.findBegDate(date, areaDays);

        Map<String, CondStock> rsMap = new HashMap<>();

        CondStock condEndDate = new CondStock();
        condEndDate.setDate(endDate);
        condEndDate.setF139(board);
        condEndDate.setMvMin(mvMin);
        condEndDate.setMvMax(mvMax);
        if (!isNew) {
            String maxDate = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYYMMDD, -30);
            condEndDate.setMaxF26(maxDate);
        }
        List<RankStockCommpanyDb> stListEndDate = StockService.findListByCondition(condEndDate);//查询股票列表

        CondStock condBegDate = new CondStock();
        condBegDate.setDate(begDate);
        List<String> stCodeList = new ArrayList<>();
        for (RankStockCommpanyDb rankStockCommpanyDb : stListEndDate) {
            stCodeList.add(rankStockCommpanyDb.getF12());
        }
        condBegDate.setStCodeList(stCodeList);
        condBegDate.setConception(conception);
        List<RankStockCommpanyDb> stListBegDate = StockService.findListByCondition(condBegDate);//查询股票列表

        for (RankStockCommpanyDb rankStockCommpanyDb : stListEndDate) {
            CondStock rsOne = new CondStock();
            rsOne.setF14(rankStockCommpanyDb.getF14());
            rsOne.setF12(rankStockCommpanyDb.getF12());
            rsOne.setF3(rankStockCommpanyDb.getF3());
            rsOne.setF139(rankStockCommpanyDb.getF139());
            rsOne.setType_name(rankStockCommpanyDb.getType_name());
            BigDecimal marketValue = null;
            if (rankStockCommpanyDb.getF20() != null) {
                marketValue = rankStockCommpanyDb.getF20().divide(NUM_YI_1, 2, BigDecimal.ROUND_HALF_UP);
            }
            rsOne.setF20(marketValue);
            rsOne.setBegDate(begDate);
            rsOne.setEndDate(endDate);
            rsOne.setEndDateF2(rankStockCommpanyDb.getF2());
            rsMap.put(rankStockCommpanyDb.getF12(), rsOne);
        }

        for (RankStockCommpanyDb rankStockCommpanyDb : stListBegDate) {
            String code = rankStockCommpanyDb.getF12();
            BigDecimal yestodayNet = rankStockCommpanyDb.getF18();
            CondStock rsOne = rsMap.get(code);
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
            rsOne.setDate(rankStockCommpanyDb.getDate());
            rsOne.setF2(rankStockCommpanyDb.getF2());
            rsMap.put(code, rsOne);
        }

        List<CondStock> rsList = new ArrayList<>();
        //计算区间涨幅
        for (CondStock dto : rsMap.values()) {
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
            rsList.add(dto);
        }

        boolean isShowMoreYes = true;
        boolean isShowMoreNo = false;
        String rankName = "";
        if (isDesc) {
            //排序
            rsList = rsList.stream().filter(e -> e != null).sorted(Comparator.comparing(CondStock::getAreaF3, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());
            rankName = "涨幅榜";
        } else {
            rsList = rsList.stream().filter(e -> e != null).sorted(Comparator.comparing(CondStock::getAreaF3, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            rankName = "跌幅榜";
        }
        if (isCheckFuQuan) {
            updateFuQuan(rsList, limit);//更新复权：前复权，检查当日K线与数据库的数据是否相符，如果不符，进行复权更新
        }
        //区间涨幅
//            System.out.println();
//            showHeadAdrRank(board, areaDays, begDate, endDate, rankName, mvMin, mvMax);
//            showInfo(rsListAsc, board, begDate, endDate, limit, isShowMoreNo, isShowCode);
        showHeadAdrRank(board, areaDays, begDate, endDate, rankName, mvMin, mvMax, isNew);
        Map<String, Integer> sizeMap = StockUtil.showInfoHead(isShowMoreYes, isShowCode, true, conception);
        StockUtil.showInfo(rsList, board, begDate, endDate, limit, isShowMoreYes, isShowCode, sizeMap, conception);
        System.out.println();
    }

    /**
     * 更新复权：前复权，检查当日K线与数据库的数据是否相符，如果不符，进行复权更新
     *
     * @param rsListAsc 股票列表
     * @param limit     限定个数
     */
    private static void updateFuQuan(List<CondStock> rsListAsc, int limit) {
        int checkCount = limit;
        for (CondStock stock : rsListAsc) {
            if (--checkCount <= 0) {
                break;
            }
            String zqdm = stock.getF12();
            String zqmc = stock.getF14();
//            if(zqmc.contains("康龙化成")){
//                System.out.println("特殊股票处理："+zqmc);
//            }
//            if(zqdm.contains("300759")){
//                System.out.println("特殊股票代码处理："+zqmc);
//            }
            String dateCheck = stock.getDate();
            BigDecimal closeAmtDb = stock.getF2();
            // 查询今日价格
            List<Kline> klines = KlineService.kline(zqdm, 1, KLT_101, true, dateCheck, dateCheck, "");
            if (klines == null || klines.size() == 0) {
                StringBuffer sbError = new StringBuffer();
                sbError.append(zqdm).append("，").append(":k线异常！");
                System.out.println(sbError);
                continue;
            }
            Kline todayKline = klines.get(0);
            BigDecimal closeAmt = todayKline.getCloseAmt();
            if (closeAmtDb.compareTo(closeAmt) != 0) {
                System.out.println("k线收盘价与数据库收盘价不符：" + zqmc + "," + closeAmt + ":" + closeAmtDb);
                RankStockCommpanyDb stockInfo = new RankStockCommpanyDb();
                stockInfo.setF12(zqdm);
                stockInfo.setDate(dateCheck);
                stockInfo.setF2(closeAmt);
                stockInfo.setF17(todayKline.getOpenAmt());
                stockInfo.setF18(todayKline.getCloseLastAmt());
                stockInfo.setF15(todayKline.getMaxAmt());
                stockInfo.setF16(todayKline.getMinAmt());
                int rsUpdate = RankStockCommpanyDao.updateByCode(stockInfo);
                System.out.println("复权更新：" + rsUpdate);
            }
        }
    }

    /**
     * 显示排行榜头信息
     *
     * @param board       板块
     * @param areaDayType 区间日期类型
     * @param begDate     开始日期
     * @param endDate     结束日期
     * @param rankName    排行榜名称
     * @param mvMin       最小市值
     * @param mvMax       最大市值
     * @param isNew       是否统计新股
     */
    private static void showHeadAdrRank(Long board, int areaDayType, String begDate, String endDate, String rankName, BigDecimal mvMin, BigDecimal mvMax, boolean isNew) {
        StringBuffer sb = new StringBuffer();
        String mvLimitInfo = "";
        if (mvMin == null || mvMin.compareTo(new BigDecimal("0")) == 0) {
            mvLimitInfo = "(0亿";
        } else if (mvMin.equals(NUM_YI_50)) {
            mvLimitInfo = "(50亿";
        } else if (mvMin.equals(NUM_YI_100)) {
            mvLimitInfo = "(100亿";
        } else if (mvMin.equals(NUM_YI_200)) {
            mvLimitInfo = "(200亿";
        } else if (mvMin.equals(NUM_YI_500)) {
            mvLimitInfo = "(500亿";
        } else if (mvMin.equals(NUM_YI_1000)) {
            mvLimitInfo = "(1000亿";
        }
        if (mvMax == null) {
            if (mvMin == null || mvMin.compareTo(new BigDecimal("0")) == 0) {
                mvLimitInfo = "";
            } else {
                mvLimitInfo = mvLimitInfo + "以上)";
            }
        } else if (mvMax.equals(NUM_YI_50)) {
            mvLimitInfo = mvLimitInfo + "至50亿)";
        } else if (mvMax.equals(NUM_YI_100)) {
            mvLimitInfo = mvLimitInfo + "至100亿)";
        } else if (mvMax.equals(NUM_YI_200)) {
            mvLimitInfo = mvLimitInfo + "至200亿)";
        } else if (mvMax.equals(NUM_YI_500)) {
            mvLimitInfo = mvLimitInfo + "至500亿)";
        } else if (mvMax.equals(NUM_YI_1000)) {
            mvLimitInfo = mvLimitInfo + "至1000亿)";
        }

        String boardName = StockUtil.handlerBoardName(board);

        String areaDayTypeName = "";
        if (areaDayType == 4) {
            areaDayTypeName = "近一周";
        } else if (areaDayType == 20) {
            areaDayTypeName = "近一月";
        }

        sb.append("A股");
        sb.append(areaDayTypeName);
        sb.append(rankName);
        sb.append("(");
        sb.append(begDate);
        sb.append("至");
        sb.append(endDate);
        sb.append(")");

        sb.append(mvLimitInfo);

        sb.append("(");
        sb.append(boardName);
        sb.append(")");
        if (!isNew) {
            sb.append("(剔除近30日内新股)");
        }

        System.out.println(sb);
    }


    /**
     * 突破均线
     */
    private static void statMaBreakUp() {
        BigDecimal goodRateCurDayLimitUp = new BigDecimal("0.01");
        String curDate = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -8);
        List<String> dateListBefore = RankStockCommpanyDao.findListDateBefore(new DateCond(curDate, 120));
//        System.out.println(JSON.toJSONString(dateListBefore));
        int countUp1 = 0, countUp2 = 0, countUp3 = 0;//上涨个数
        int countDown1 = 0, countDown2 = 0, countDown3 = 0;//下跌个数
//            String weekFiter = "一";
//            String weekFiter = "二";
//            String weekFiter = "三";
//            String weekFiter = "四";
        String weekFiter = "五";
        for (String date : dateListBefore) {
            // 均线突破
            MaBreakUpRs rs = maBreakUp(date, weekFiter, goodRateCurDayLimitUp);
            if (rs == null) {
                continue;
            }
            countUp1 = countUp1 + rs.getCurDayAdd1UpCount();
            countUp2 = countUp2 + rs.getCurDayAdd2UpCount();
            countUp3 = countUp3 + rs.getCurDayAdd3UpCount();
            countDown1 = countDown1 + rs.getCurDayAdd1DownCount();
            countDown2 = countDown2 + rs.getCurDayAdd1DownCount();
            countDown3 = countDown3 + rs.getCurDayAdd1DownCount();
        }
        System.out.println("后1日合计涨跌比:" + countUp1 + ":" + countDown1 + ",上涨率：" + new BigDecimal(countUp1).divide(new BigDecimal(countUp1 + countDown1), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")));
        System.out.println(countUp1 + "\t" + countDown1 + "\t" + new BigDecimal(countUp1).divide(new BigDecimal(countUp1 + countDown1), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")));

    }

    /**
     * 查询-统计数据-股票分组
     */
    private static void statFindListTongJj() {
        String begDate = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -365);
        String endDate = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, 0);
        String typeName = ST_BIZ_TYPE_YOU_SE_JIN_SHU;//业务板块
        findListTongJj(typeName, begDate, endDate);//查询-统计数据
//        findListTongJj("2021-01-01","2021-01-31");//查询-统计数据
//        findListTongJj("2021-02-01","2021-02-29");//查询-统计数据
//        findListTongJj("2021-03-01","2021-03-31");//查询-统计数据
//        findListTongJj("2021-04-01","2021-04-31");//查询-统计数据
//        findListTongJj("2021-05-01","2021-05-31");//查询-统计数据
//        findListTongJj("2021-06-01","2021-06-31");//查询-统计数据
//        findListTongJj("2021-07-01","2021-07-31");//查询-统计数据
//        findListTongJj("2021-08-01","2021-08-31");//查询-统计数据
//        findListTongJj("2021-09-01","2021-09-31");//查询-统计数据
    }

    /**
     * 超跌反弹
     */
    private static void statSuperDropBounce() {
        SuperDropBounceStat statistics = new SuperDropBounceStat();
        for (int i = 15; i <= 60; i++) {
            String curDate = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -i);
            //  超跌反弹
            superDropBounce(curDate, statistics);
        }
        System.out.println("统计:" + JSON.toJSONString(statistics));
        System.out.print("统计日加1 :" + "涨：" + statistics.getAdrUpCountDay1() + "，跌：" + statistics.getAdrDownCountDay1() + "，共：" + statistics.getRsCountDay1());
        System.out.println(",统计日加1（累计） :" + "涨：" + statistics.getAdrUpCountTotalDay1() + "，跌：" + statistics.getAdrDownCountTotalDay1());
        System.out.print("统计日加2 :" + "涨：" + statistics.getAdrUpCountDay2() + "，跌：" + statistics.getAdrDownCountDay2() + "，共：" + statistics.getRsCountDay2());
        System.out.println(",统计日加2（累计） :" + "涨：" + statistics.getAdrUpCountTotalDay2() + "，跌：" + statistics.getAdrDownCountTotalDay2());
        System.out.print("统计日加3 :" + "涨：" + statistics.getAdrUpCountDay3() + "，跌：" + statistics.getAdrDownCountDay3() + "，共：" + statistics.getRsCountDay3());
        System.out.println(",统计日加3（累计） :" + "涨：" + statistics.getAdrUpCountTotalDay3() + "，跌：" + statistics.getAdrDownCountTotalDay3());
        System.out.print("统计日加5 :" + "涨：" + statistics.getAdrUpCountDay5() + "，跌：" + statistics.getAdrDownCountDay5() + "，共：" + statistics.getRsCountDay5());
        System.out.println(",统计日加5（累计） :" + "涨：" + statistics.getAdrUpCountTotalDay5() + "，跌：" + statistics.getAdrDownCountTotalDay5());
        System.out.print("统计日加10:" + "涨：" + statistics.getAdrUpCountDay10() + "，跌：" + statistics.getAdrDownCountDay10() + "，共：" + statistics.getRsCountDay10());
        System.out.println(",统计日10（累计） :" + "涨：" + statistics.getAdrUpCountTotalDay10() + "，跌：" + statistics.getAdrDownCountTotalDay10());
        System.out.print("统计日加20:" + "涨：" + statistics.getAdrUpCountDay20() + "，跌：" + statistics.getAdrDownCountDay20() + "，共：" + statistics.getRsCountDay20());
        System.out.println(",统计日20（累计） :" + "涨：" + statistics.getAdrUpCountTotalDay20() + "，跌：" + statistics.getAdrDownCountTotalDay20());
        System.out.print("统计日加30:" + "涨：" + statistics.getAdrUpCountDay30() + "，跌：" + statistics.getAdrDownCountDay30() + "，共：" + statistics.getRsCountDay30());
        System.out.println(",统计日30（累计） :" + "涨：" + statistics.getAdrUpCountTotalDay30() + "，跌：" + statistics.getAdrDownCountTotalDay30());
        System.out.print("统计日加60:" + "涨：" + statistics.getAdrUpCountDay60() + "，跌：" + statistics.getAdrDownCountDay60() + "，共：" + statistics.getRsCountDay60());
        System.out.println(",统计日60（累计） :" + "涨：" + statistics.getAdrUpCountTotalDay60() + "，跌：" + statistics.getAdrDownCountTotalDay60());
    }

    /**
     * 超跌反弹
     */
    private static List<SuperDropBounceRs> superDropBounce(String curDate, SuperDropBounceStat stat) {
        SuperDropBounceCond condition = new SuperDropBounceCond();
        List<String> dateListBefore = RankStockCommpanyDao.findListDateBefore(new DateCond(curDate, 60));
//        System.out.println(JSON.toJSONString(dateListBefore));
        List<String> dateListAfter = RankStockCommpanyDao.findListDateAfter(new DateCond(curDate, 60));
//        System.out.println(JSON.toJSONString(dateListAfter));
        String curDayAdd1 = dateListAfter.get(0);
        String curDayAdd2 = dateListAfter.get(1);
        condition.setCurDay(curDate);
        condition.setCurDayAdd1(curDayAdd1);
        condition.setCurDayAdd2(curDayAdd2);
        if (dateListAfter.size() >= 3) {
            condition.setCurDayAdd3(dateListAfter.get(2));
        }
        if (dateListAfter.size() >= 5) {
            condition.setCurDayAdd5(dateListAfter.get(4));
        }
        if (dateListAfter.size() >= 10) {
            condition.setCurDayAdd10(dateListAfter.get(9));
        }
        if (dateListAfter.size() >= 20) {
            condition.setCurDayAdd20(dateListAfter.get(19));
        }
        if (dateListAfter.size() >= 30) {
            condition.setCurDayAdd30(dateListAfter.get(29));
        }
        if (dateListAfter.size() >= 60) {
            condition.setCurDayAdd60(dateListAfter.get(59));
        }
        if (dateListBefore.size() >= 1) {
            condition.setCurDaySub1(dateListBefore.get(0));
        }
        if (dateListBefore.size() >= 2) {
            condition.setCurDaySub2(dateListBefore.get(1));
        }
        if (dateListBefore.size() >= 3) {
            condition.setCurDaySub3(dateListBefore.get(2));
        }
        if (dateListBefore.size() >= 4) {
            condition.setCurDaySub4(dateListBefore.get(3));
        }
        if (dateListBefore.size() >= 5) {
            condition.setCurDaySub5(dateListBefore.get(4));
        }
        if (dateListBefore.size() >= 6) {
            condition.setCurDaySub6(dateListBefore.get(5));
        }
        condition.setF139(DB_RANK_BIZ_F139_BK_MAIN);
//        condition.setType_name(ST_BIZ_TYPE_YOU_SE_JIN_SHU);
        condition.setMarketValueMin(new BigDecimal("20000000000"));//市值
        //量比最高
        condition.setQrrMaxDaySub1(new BigDecimal("0.8"));
        condition.setQrrMaxDaySub2(new BigDecimal("0.8"));
        condition.setQrrMaxDaySub3(new BigDecimal("0.8"));
        condition.setQrrMaxDaySub4(new BigDecimal("0.8"));
//        condition.setQrrMaxDaySub5(new BigDecimal("0"));
        condition.setAdrMinDay0(new BigDecimal("0"));//涨跌限定-最低-日加n
        condition.setAdrMaxDaySub1(new BigDecimal("0"));//涨跌限定-最高-日减1
        condition.setAdrMaxDaySub2(new BigDecimal("0"));//涨跌限定-最高-日减2
        condition.setAdrMaxDaySub3(new BigDecimal("0"));//涨跌限定-最高-日减3
        condition.setAdrMaxDaySub4(new BigDecimal("0"));//涨跌限定-最高-日减4
//        condition.setAdrMaxDaySub5(new BigDecimal("0"));
//        System.out.println("查询条件：" + JSON.toJSONString(condition));
        List<SuperDropBounceRs> rs = RankStockCommpanyDao.findListSuperDropBounce(condition);
        if (rs == null || rs.size() == 0) {
            System.out.println("返回结果为空！");
            return null;
        }

        //是否超过当前价格
        for (SuperDropBounceRs superDropBounceRs : rs) {
            boolean isOverCurPriceDay1 = false, isOverCurPriceDay2 = false, isOverCurPriceDay3 = false, isOverCurPriceDay5 = false, isOverCurPriceDay10 = false, isOverCurPriceDay20 = false, isOverCurPriceDay30 = false, isOverCurPriceDay60 = false;
//            System.out.println("返回结果:" + JSON.toJSONString(superDropBounceRs));
            if (superDropBounceRs.getAdrSum1() != null) {
                stat.setRsCountDay1(stat.getRsCountDay1().add(new BigDecimal("1")));
                if (superDropBounceRs.getAdrSum1().compareTo(new BigDecimal("0")) > 0) {
                    stat.setAdrUpCountDay1(stat.getAdrUpCountDay1().add(new BigDecimal("1")));
                    isOverCurPriceDay1 = true;
                } else {
                    stat.setAdrDownCountDay1(stat.getAdrDownCountDay1().add(new BigDecimal("1")));
                }
            }
            if (superDropBounceRs.getAdrSum2() != null) {
                stat.setRsCountDay2(stat.getRsCountDay2().add(new BigDecimal("1")));
                if (superDropBounceRs.getAdrSum2().compareTo(new BigDecimal("0")) > 0) {
                    stat.setAdrUpCountDay2(stat.getAdrUpCountDay2().add(new BigDecimal("1")));
                    isOverCurPriceDay2 = true;
                } else {
                    stat.setAdrDownCountDay2(stat.getAdrDownCountDay2().add(new BigDecimal("1")));
                }
            }
            if (superDropBounceRs.getAdrSum3() != null) {
                stat.setRsCountDay3(stat.getRsCountDay3().add(new BigDecimal("1")));
                if (superDropBounceRs.getAdrSum3().compareTo(new BigDecimal("0")) > 0) {
                    stat.setAdrUpCountDay3(stat.getAdrUpCountDay3().add(new BigDecimal("1")));
                    isOverCurPriceDay3 = true;
                } else {
                    stat.setAdrDownCountDay3(stat.getAdrDownCountDay3().add(new BigDecimal("1")));
                }
            }
            if (superDropBounceRs.getAdrSum5() != null) {
                stat.setRsCountDay5(stat.getRsCountDay5().add(new BigDecimal("1")));
                if (superDropBounceRs.getAdrSum5().compareTo(new BigDecimal("0")) > 0) {
                    stat.setAdrUpCountDay5(stat.getAdrUpCountDay5().add(new BigDecimal("1")));
                    isOverCurPriceDay5 = true;
                } else {
                    stat.setAdrDownCountDay5(stat.getAdrDownCountDay5().add(new BigDecimal("1")));
                }
            }
            if (superDropBounceRs.getAdrSum10() != null) {
                stat.setRsCountDay10(stat.getRsCountDay10().add(new BigDecimal("1")));
                if (superDropBounceRs.getAdrSum10().compareTo(new BigDecimal("0")) > 0) {
                    stat.setAdrUpCountDay10(stat.getAdrUpCountDay10().add(new BigDecimal("1")));
                    isOverCurPriceDay10 = true;
                } else {
                    stat.setAdrDownCountDay10(stat.getAdrDownCountDay10().add(new BigDecimal("1")));
                }
            }
            if (superDropBounceRs.getAdrSum20() != null) {
                stat.setRsCountDay20(stat.getRsCountDay20().add(new BigDecimal("1")));
                if (superDropBounceRs.getAdrSum20().compareTo(new BigDecimal("0")) > 0) {
                    stat.setAdrUpCountDay20(stat.getAdrUpCountDay20().add(new BigDecimal("1")));
                    isOverCurPriceDay20 = true;
                } else {
                    stat.setAdrDownCountDay20(stat.getAdrDownCountDay20().add(new BigDecimal("1")));
                }
            }
            if (superDropBounceRs.getAdrSum30() != null) {
                stat.setRsCountDay30(stat.getRsCountDay30().add(new BigDecimal("1")));
                if (superDropBounceRs.getAdrSum30().compareTo(new BigDecimal("0")) > 0) {
                    stat.setAdrUpCountDay30(stat.getAdrUpCountDay30().add(new BigDecimal("1")));
                    isOverCurPriceDay30 = true;
                } else {
                    stat.setAdrDownCountDay30(stat.getAdrDownCountDay30().add(new BigDecimal("1")));
                }
            }
            if (superDropBounceRs.getAdrSum60() != null) {
                stat.setRsCountDay60(stat.getRsCountDay60().add(new BigDecimal("1")));
                if (superDropBounceRs.getAdrSum60().compareTo(new BigDecimal("0")) > 0) {
                    stat.setAdrUpCountDay60(stat.getAdrUpCountDay60().add(new BigDecimal("1")));
                    isOverCurPriceDay60 = true;
                } else {
                    stat.setAdrDownCountDay60(stat.getAdrDownCountDay60().add(new BigDecimal("1")));
                }
            }
            if (!isOverCurPriceDay1 && !isOverCurPriceDay2 && !isOverCurPriceDay3 && !isOverCurPriceDay5 && !isOverCurPriceDay10 && !isOverCurPriceDay20 && !isOverCurPriceDay30 && !isOverCurPriceDay60) {
                stat.setAdrDownCountTotalDay60(stat.getAdrDownCountTotalDay60().add(new BigDecimal("1")));
                System.out.println("返回结果(60都下跌):" + JSON.toJSONString(superDropBounceRs));
            }
            if (isOverCurPriceDay1) {
                stat.setAdrUpCountTotalDay1(stat.getAdrUpCountTotalDay1().add(new BigDecimal("1")));
            }
            if (!isOverCurPriceDay2 && !isOverCurPriceDay3 && !isOverCurPriceDay5 && !isOverCurPriceDay10 && !isOverCurPriceDay20 && !isOverCurPriceDay30 && !isOverCurPriceDay60) {
                stat.setAdrDownCountTotalDay2(stat.getAdrDownCountTotalDay2().add(new BigDecimal("1")));
            }
            if (isOverCurPriceDay1 || isOverCurPriceDay2) {
                stat.setAdrUpCountTotalDay2(stat.getAdrUpCountTotalDay2().add(new BigDecimal("1")));
            }
            if (!isOverCurPriceDay3 && !isOverCurPriceDay5 && !isOverCurPriceDay10 && !isOverCurPriceDay20 && !isOverCurPriceDay30 && !isOverCurPriceDay60) {
                stat.setAdrDownCountTotalDay3(stat.getAdrDownCountTotalDay3().add(new BigDecimal("1")));
            }
            if (isOverCurPriceDay1 || isOverCurPriceDay2 || isOverCurPriceDay3) {
                stat.setAdrUpCountTotalDay3(stat.getAdrUpCountTotalDay3().add(new BigDecimal("1")));
            }
            if (!isOverCurPriceDay5 && !isOverCurPriceDay10 && !isOverCurPriceDay20 && !isOverCurPriceDay30 && !isOverCurPriceDay60) {
                stat.setAdrDownCountTotalDay5(stat.getAdrDownCountTotalDay5().add(new BigDecimal("1")));
            }
            if (isOverCurPriceDay1 || isOverCurPriceDay2 || isOverCurPriceDay3 || isOverCurPriceDay5) {
                stat.setAdrUpCountTotalDay5(stat.getAdrUpCountTotalDay5().add(new BigDecimal("1")));
            }
            if (!isOverCurPriceDay10 && !isOverCurPriceDay20 && !isOverCurPriceDay30 && !isOverCurPriceDay60) {
                stat.setAdrDownCountTotalDay10(stat.getAdrDownCountTotalDay10().add(new BigDecimal("1")));
            }
            if (isOverCurPriceDay1 || isOverCurPriceDay2 || isOverCurPriceDay3 || isOverCurPriceDay5 || isOverCurPriceDay10) {
                stat.setAdrUpCountTotalDay10(stat.getAdrUpCountTotalDay10().add(new BigDecimal("1")));
            }
            if (!isOverCurPriceDay20 && !isOverCurPriceDay30 && !isOverCurPriceDay60) {
                stat.setAdrDownCountTotalDay20(stat.getAdrDownCountTotalDay20().add(new BigDecimal("1")));
            }
            if (isOverCurPriceDay1 || isOverCurPriceDay2 || isOverCurPriceDay3 || isOverCurPriceDay5 || isOverCurPriceDay10 || isOverCurPriceDay20) {
                stat.setAdrUpCountTotalDay20(stat.getAdrUpCountTotalDay20().add(new BigDecimal("1")));
            }
            if (isOverCurPriceDay1 || isOverCurPriceDay2 || isOverCurPriceDay3 || isOverCurPriceDay5 || isOverCurPriceDay10 || isOverCurPriceDay20 || isOverCurPriceDay30) {
                stat.setAdrUpCountTotalDay30(stat.getAdrUpCountTotalDay30().add(new BigDecimal("1")));
            }
            if (!isOverCurPriceDay30 && !isOverCurPriceDay60) {
                stat.setAdrDownCountTotalDay30(stat.getAdrDownCountTotalDay30().add(new BigDecimal("1")));
            }
            if (isOverCurPriceDay1 || isOverCurPriceDay2 || isOverCurPriceDay3 || isOverCurPriceDay5 || isOverCurPriceDay10 || isOverCurPriceDay20 || isOverCurPriceDay30 || isOverCurPriceDay60) {
                stat.setAdrUpCountTotalDay60(stat.getAdrUpCountTotalDay60().add(new BigDecimal("1")));
            }
        }


        return rs;
    }

    /**
     * 均线突破
     *
     * @param curDate               日期
     * @param weekFiter             星期几过滤
     * @param goodRateCurDayLimitUp
     */
    private static MaBreakUpRs maBreakUp(String curDate, String weekFiter, BigDecimal goodRateCurDayLimitUp) {
        //过滤器：星期n
        String curDateWeek = DateUtil.getWeekByYyyyMmDd(curDate, DateUtil.YYYY_MM_DD);
        if (StringUtils.isNotBlank(weekFiter) && !curDateWeek.equals(weekFiter)) {
            System.out.println("过滤器：星期[" + curDateWeek + "]不查询");
            return null;
        }
        MaBreakUpCond condition = new MaBreakUpCond();
        List<String> dateListBefore = RankStockCommpanyDao.findListDateBefore(new DateCond(curDate, 10));
//        System.out.println(JSON.toJSONString(dateListBefore));
        List<String> dateListAfter = RankStockCommpanyDao.findListDateAfter(new DateCond(curDate, 10));
//        System.out.println(JSON.toJSONString(dateListAfter));
        String curDaySub1 = dateListBefore.get(0);
        String curDayAdd1 = dateListAfter.get(0);
        String curDayAdd2 = dateListAfter.get(1);
        String curDayAdd3 = dateListAfter.get(2);
        condition.setCurDaySub1(curDaySub1);
        condition.setCurDay(curDate);
        condition.setCurDayAdd1(curDayAdd1);
        condition.setCurDayAdd2(curDayAdd2);
        condition.setCurDayAdd3(curDayAdd3);
        condition.setF139(DB_RANK_BIZ_F139_BK_MAIN);
//        condition.setType_name(ST_BIZ_TYPE_YOU_SE_JIN_SHU);
        condition.setF20(200 * 00000000l);//市值
        condition.setGoodRateCurDayLimitDown(new BigDecimal("0"));//当日涨幅下限
        condition.setGoodRateCurDayLimitUp(goodRateCurDayLimitUp);//当日涨幅上限
        condition.setGoodRateDaySub1(new BigDecimal("0"));
//        System.out.println("查询条件：" + JSON.toJSONString(condition));
        List<MaBreakUpRs> rs = RankStockCommpanyDao.findListMaBreakUpCond(condition);
        if (rs == null || rs.size() == 0) {
            System.out.println("返回结果为空！" + JSON.toJSONString(condition));
            return null;
        }
        int countUp1 = 0;//上涨个数
        int countUp2 = 0;//上涨个数
        int countUp3 = 0;//上涨个数
        int countDown1 = 0;//下跌个数
        int countDown2 = 0;//下跌个数
        int countDown3 = 0;//下跌个数
        int countUpDayAdd1Week5 = 0;//上涨个数-周n
        int countUpDayAdd1Week4 = 0;//上涨个数-周n
        int countUpDayAdd1Week3 = 0;//上涨个数-周n
        int countUpDayAdd1Week2 = 0;//上涨个数-周n
        int countUpDayAdd1Week1 = 0;//上涨个数-周n
        int countUpDayAdd2Week5 = 0;//上涨个数-周n
        int countUpDayAdd2Week4 = 0;//上涨个数-周n
        int countUpDayAdd2Week3 = 0;//上涨个数-周n
        int countUpDayAdd2Week2 = 0;//上涨个数-周n
        int countUpDayAdd2Week1 = 0;//上涨个数-周n
        int countUpDayAdd3Week5 = 0;//上涨个数-周n
        int countUpDayAdd3Week4 = 0;//上涨个数-周n
        int countUpDayAdd3Week3 = 0;//上涨个数-周n
        int countUpDayAdd3Week2 = 0;//上涨个数-周n
        int countUpDayAdd3Week1 = 0;//上涨个数-周n
        int countDownDayAdd1Week5 = 0;//下跌个数-周n
        int countDownDayAdd1Week4 = 0;//下跌个数-周n
        int countDownDayAdd1Week3 = 0;//下跌个数-周n
        int countDownDayAdd1Week2 = 0;//下跌个数-周n
        int countDownDayAdd1Week1 = 0;//下跌个数-周n
        int countDownDayAdd2Week5 = 0;//下跌个数-周n
        int countDownDayAdd2Week4 = 0;//下跌个数-周n
        int countDownDayAdd2Week3 = 0;//下跌个数-周n
        int countDownDayAdd2Week2 = 0;//下跌个数-周n
        int countDownDayAdd2Week1 = 0;//下跌个数-周n
        int countDownDayAdd3Week5 = 0;//下跌个数-周n
        int countDownDayAdd3Week4 = 0;//下跌个数-周n
        int countDownDayAdd3Week3 = 0;//下跌个数-周n
        int countDownDayAdd3Week2 = 0;//下跌个数-周n
        int countDownDayAdd3Week1 = 0;//下跌个数-周n
        for (MaBreakUpRs maBreakUp : rs) {
//            System.out.println(JSON.toJSONString(maBreakUp));
            //统计个数：当前日期的后n天涨跌
            if (maBreakUp.getAdrDaySum1() == null) {
                continue;
            }
            if (maBreakUp.getAdrDaySum1().compareTo(new BigDecimal("0")) > 0) {
                countUp1++;
                if (curDateWeek.equals("五")) {
                    countUpDayAdd1Week5++;
                }
                if (curDateWeek.equals("四")) {
                    countUpDayAdd1Week4++;
                }
                if (curDateWeek.equals("三")) {
                    countUpDayAdd1Week3++;
                }
                if (curDateWeek.equals("二")) {
                    countUpDayAdd1Week2++;
                }
                if (curDateWeek.equals("一")) {
                    countUpDayAdd1Week1++;
                }
            } else {
                countDown1++;
                if (curDateWeek.equals("五")) {
                    countDownDayAdd1Week5++;
                }
                if (curDateWeek.equals("四")) {
                    countDownDayAdd1Week4++;
                }
                if (curDateWeek.equals("三")) {
                    countDownDayAdd1Week3++;
                }
                if (curDateWeek.equals("二")) {
                    countDownDayAdd1Week2++;
                }
                if (curDateWeek.equals("一")) {
                    countDownDayAdd1Week1++;
                }
            }
            if (maBreakUp.getAdrDaySum2().compareTo(new BigDecimal("0")) > 0) {
                countUp2++;
                if (curDateWeek.equals("五")) {
                    countUpDayAdd2Week5++;
                }
                if (curDateWeek.equals("四")) {
                    countUpDayAdd2Week4++;
                }
                if (curDateWeek.equals("三")) {
                    countUpDayAdd2Week3++;
                }
                if (curDateWeek.equals("二")) {
                    countUpDayAdd2Week2++;
                }
                if (curDateWeek.equals("一")) {
                    countUpDayAdd2Week1++;
                }
            } else {
                countDown2++;
                if (curDateWeek.equals("五")) {
                    countDownDayAdd2Week5++;
                }
                if (curDateWeek.equals("四")) {
                    countDownDayAdd2Week4++;
                }
                if (curDateWeek.equals("三")) {
                    countDownDayAdd2Week3++;
                }
                if (curDateWeek.equals("二")) {
                    countDownDayAdd2Week2++;
                }
                if (curDateWeek.equals("一")) {
                    countDownDayAdd2Week1++;
                }
            }
            if (maBreakUp.getAdrDaySum3().compareTo(new BigDecimal("0")) > 0) {
                countUp3++;
                if (curDateWeek.equals("五")) {
                    countUpDayAdd3Week5++;
                }
                if (curDateWeek.equals("四")) {
                    countUpDayAdd3Week4++;
                }
                if (curDateWeek.equals("三")) {
                    countUpDayAdd3Week3++;
                }
                if (curDateWeek.equals("二")) {
                    countUpDayAdd3Week2++;
                }
                if (curDateWeek.equals("一")) {
                    countUpDayAdd3Week1++;
                }
            } else {
                countDown3++;
                if (curDateWeek.equals("五")) {
                    countDownDayAdd3Week5++;
                }
                if (curDateWeek.equals("四")) {
                    countDownDayAdd3Week4++;
                }
                if (curDateWeek.equals("三")) {
                    countDownDayAdd3Week3++;
                }
                if (curDateWeek.equals("二")) {
                    countDownDayAdd3Week2++;
                }
                if (curDateWeek.equals("一")) {
                    countDownDayAdd3Week1++;
                }
            }
        }
        System.out.println("当前日期:" + curDate + ":" + DateUtil.getWeekByYyyyMmDd(curDate, DateUtil.YYYY_MM_DD));
        System.out.println("后1日" + curDayAdd1 + DateUtil.getWeekByYyyyMmDd(curDayAdd1, DateUtil.YYYY_MM_DD) + "涨跌比:" + countUp1 + ":" + countDown1 + ",上涨率：" + new BigDecimal(countUp1).divide(new BigDecimal(rs.size()), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")));
        System.out.println("后2日" + curDayAdd2 + DateUtil.getWeekByYyyyMmDd(curDayAdd2, DateUtil.YYYY_MM_DD) + "涨跌比:" + countUp2 + ":" + countDown2 + ",上涨率：" + new BigDecimal(countUp2).divide(new BigDecimal(rs.size()), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")));
        System.out.println("后3日" + curDayAdd3 + DateUtil.getWeekByYyyyMmDd(curDayAdd3, DateUtil.YYYY_MM_DD) + "涨跌比:" + countUp3 + ":" + countDown3 + ",上涨率：" + new BigDecimal(countUp3).divide(new BigDecimal(rs.size()), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")));
        MaBreakUpRs maBreakUpRs = new MaBreakUpRs();
        maBreakUpRs.setCurDayAdd1UpCount(countUp1);
        maBreakUpRs.setCurDayAdd2UpCount(countUp2);
        maBreakUpRs.setCurDayAdd3UpCount(countUp3);
        maBreakUpRs.setCurDayAdd1DownCount(countDown1);
        maBreakUpRs.setCurDayAdd2DownCount(countDown2);
        maBreakUpRs.setCurDayAdd3DownCount(countDown3);
        return maBreakUpRs;
    }

    /**
     * 查询-统计数据-股票分组
     *
     * @param typeName
     * @param begDate
     * @param endDate
     */
    private static void findListTongJj(String typeName, String begDate, String endDate) {
        BigDecimal limitPe = new BigDecimal("50");//提醒信息：限定市盈率
        boolean isShowPeHighInfo = true;//是否显示市盈率高的记录

        RankStComTjCond condition = new RankStComTjCond();
        condition.setBegDate(begDate);//n天
        condition.setEndDate(endDate);
        condition.setPlate(Content.ST_PLATE_F139_AG);//只查询主板的
//        condition.setMarketValueMin(new BigDecimal("200"));//市值限定
//        condition.setMarketValueMax(new BigDecimal("9999"));//市值限定
        condition.setType_name(typeName);/**    业务板块：	电力行业-BK0428	券商信托-BK0473	银行-BK0475	医疗行业-BK0727	医药制造-BK0465	化工行业-BK0538	酿酒行业-BK0477	化肥行业-BK0731	民航机场-BK0420	环保工程	**/
        condition.setOrderBy(" code ");
        System.out.println("查询条件：" + JSON.toJSONString(condition));
        List<RankStComTjRs> rs = RankStockCommpanyDao.findListTongjiGroup(condition);
        if (rs == null) {
            System.out.println("返回结果为空！");
            return;
        }
        int orderNum = 0;
        for (RankStComTjRs rankStComTjRs : rs) {
            if (!isShowPeHighInfo) {
                if (rankStComTjRs.getLastPe() != null && rankStComTjRs.getLastPe().compareTo(limitPe) >= 0) {
//                    System.out.println("!!!市盈率过高不显示:" + rankStComTjRs.getLastPe());
                    continue;
                }
            }
            orderNum++;
            System.out.print(orderNum + ",");
            System.out.print(JSON.toJSONString(rankStComTjRs));
//            System.out.print(rankStComTjRs.getCode());
//            System.out.print("\t");
//            System.out.print(rankStComTjRs.getName());
//            System.out.print("\t");
//            System.out.print(rankStComTjRs.getZhangfuSum());
//            System.out.print(rankStComTjRs.getLastPe());
//            System.out.print(rankStComTjRs.getLastMarketValue());
            //市盈率过高提醒
            if (rankStComTjRs.getLastPe() != null && rankStComTjRs.getLastPe().compareTo(limitPe) >= 0) {
                System.out.print("!!!市盈率过高:" + rankStComTjRs.getLastPe());
            }
            System.out.println();
        }
    }

    /**
     * 查询昨日主题排名
     *
     * @param endCount
     */
    private static List<RankBizDataDiff> listBiz(int endCount) {
        //http://28.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112408110589206747254_1616379873172&pn=1&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:90+t:2+f:!50&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222&_=1616379873199
        long curTime = System.currentTimeMillis();
        StringBuffer urlParam = new StringBuffer();
//        String url = "http://api.fund.eastmoney.com/ztjj/GetZTJJList";
//        urlParam.append("callback=jQuery311015658602123786958_1591838943711&callbackname=fundData");
//        urlParam.append("&st="+findDateType);//查询类型
//        urlParam.append("&_=1614523183291");
        String url = "http://28.push2.eastmoney.com/api/qt/clist/get";
        urlParam.append("cb=jQuery112408110589206747254_" + curTime +
                "&pn=1" +//页数
                "&pz=" + endCount +//每页数量
                "&po=1" +//pageorder:页面排序：0-正序；1-倒序
                "&np=1" +
                "&ut=bd1d9ddb04089700cf9c27f6f7426281" +
                "&fltt=2" +//浮点数精度
                "&invt=3" +//显示格式：-；0.0
                "&fid=f3" +//排序字段
                "&fs=m:90+t:2+f:!50" +
                "&fields=" +
                "f1,f2,f3,f4,f5,f6,f7,f8,f9," +
                "f10,f11,f12,f13,f14,f15,f16,f17,f18,f19," +
                "f20,f21,f22,f23,f24,f25,f26,f27,f28,f29," +
                "f30,f31,f32,f33,f34,f35,f36,f37,f38,f39," +
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
        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
        List<RankBizDataDiff> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankBizDataDiff.class);
//        for (RankBizDataDiff row : rankBizDataDiffList) {
//            System.out.println(JSON.toJSON(row));//每个行业一行数据
//        }
        return rankBizDataDiffList;
    }

}
