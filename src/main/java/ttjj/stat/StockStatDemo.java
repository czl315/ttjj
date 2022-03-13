package ttjj.stat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.RankStockCommpanyDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.*;
import ttjj.service.KlineService;
import utils.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static utils.Content.*;

/**
 * 排行-行业股票-公司-每日明细
 */
public class StockStatDemo {
    public static void main(String[] args) {

//        Map<String, String> zqMap = ContStConception.mapYiLiaoXinGuanAll;// 医疗：  mapYiLiaoXinGuanAll
//        Map<String, String> zqMap = ContStConception.mapJinRongQuanShang;// 金融：   mapJinRongQuanShang
//        Map<String, String> zqMap = ContStConception.mapKeJiShuZiHuoBi;// 科技：   mapKeJiShuZiHuoBi
        Map<String, String> zqMap = ContStConception.mapXiaoFeiZaiXianLvYou;// 消费：   mapXiaoFeiZaiXianLvYou
        checkMaDemo(zqMap);

        /**
         * 添加或更新股票-根据日期
         */
//        for (int i = 0; i < 1; i++) {
//            String date = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -i);
////            String date = "2021-12-06";
//            String specialDate = "2022-01-07";
//
//            BigDecimal curPriceAreaMaxRate = new BigDecimal("20");
////            String biz = "BK0486";//ST_BIZ_TYPE_CODE_NIANG_JIU_HANG_YE  文化传媒,BK0486
//            String biz = "";//
//            BigDecimal limitMarketValue = new BigDecimal("10000000000");//限定市值
//            String maType = KLT_60;//  KLT_15  KLT_30  KLT_60  KLT_120 KLT_5   KLT_101
//            List<Integer> maList = new ArrayList<>();
//            maList.add(MA_30);
//            maList.add(MA_60);
//            checkMaByBk(date,specialDate, biz, curPriceAreaMaxRate, limitMarketValue,maType,maList);// 检查均线,确认买点
//
//
////            //查询业绩报表
////            String begDate = "20210701";
////            String endDate = DateUtil.getToday(DateUtil.YYYY_MM_DD);
////            List<RankBizDataDiff> bkList = listBiz(NUM_MAX_999);//查询主题-排名by时间类型、显示个数
////            listReport(bkList, "2021Q3", begDate, endDate);
//
////            updateBkList(date, startNum);//更新业务板块
//        }


        //突破均线
//        {
//            String curDate = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -7);
//            List<String> dateListBefore = RankStockCommpanyDao.findListDateBefore(new DateCond(curDate, 120));
////        System.out.println(JSON.toJSONString(dateListBefore));
//            int countUp1 = 0, countUp2 = 0, countUp3 = 0;//上涨个数
//            int countDown1 = 0;//下跌个数
//            int countDown2 = 0;//下跌个数
//            int countDown3 = 0;//下跌个数
//            String weekFiter = "一";
////            String weekFiter = "二";
////            String weekFiter = "三";
////            String weekFiter = "四";
////            String weekFiter = "五";
//            for (String date : dateListBefore) {
//                // 均线突破
//                MaBreakUpRs rs = maBreakUp(date, weekFiter);
//                if (rs == null) {
//                    continue;
//                }
//                countUp1 = countUp1 + rs.getCurDayAdd1UpCount();
//                countUp2 = countUp2 + rs.getCurDayAdd2UpCount();
//                countUp3 = countUp3 + rs.getCurDayAdd3UpCount();
//                countDown1 = countDown1 + rs.getCurDayAdd1DownCount();
//                countDown2 = countDown2 + rs.getCurDayAdd1DownCount();
//                countDown3 = countDown3 + rs.getCurDayAdd1DownCount();
//            }
//            System.out.println("后1日合计涨跌比:" + countUp1 + ":" + countDown1 + ",上涨率：" + new BigDecimal(countUp1).divide(new BigDecimal(countUp1 + countDown1), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")));
//            System.out.println(countUp1 + "\t" + countDown1 + "\t" + new BigDecimal(countUp1).divide(new BigDecimal(countUp1 + countDown1), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")));
//
//        }


//        String begDate = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -365);
//        String endDate = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, 0);
//        String typeName = ST_BIZ_TYPE_YOU_SE_JIN_SHU;//业务板块
//        findListTongJj(typeName,begDate,endDate);//查询-统计数据
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
     * 检查均线
     * @param zqMap
     */
    public static void checkMaDemo(Map<String, String> zqMap) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);//                        String date = "2022-02-15";
        boolean isUp = true;//检查上涨
//        boolean isUp = false;

        List<Integer> maList = new ArrayList<>();
//        maList.add(MA_30);
        maList.add(MA_60);

//        KlineService.checkMa(zqMap, KLT_5, maList, date, isUp);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
        KlineService.checkMa(zqMap, KLT_15, maList, date, isUp);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
        KlineService.checkMa(zqMap, KLT_30, maList, date, isUp);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
        KlineService.checkMa(zqMap, KLT_60, maList, date, isUp);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
        KlineService.checkMa(zqMap, KLT_101, maList, date, isUp);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
        KlineService.checkMa(zqMap, KLT_102, maList, date, isUp);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
    }

    /**
     * 均线突破
     *
     * @param curDate   日期
     * @param weekFiter 星期几过滤
     */
    private static MaBreakUpRs maBreakUp(String curDate, String weekFiter) {
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
//        condition.setGoodRateCurDayLimitUp(new BigDecimal("0.03"));//当日涨幅上限
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
     * 检查均线
     *
     * @param date
     * @param bk
     * @param curPriceAreaMax
     * @param limitMarketValue
     * @param checkMaType
     * @param maList
     */
    private static void checkMaByBk(String date, String specialDate, String bk, BigDecimal curPriceAreaMax, BigDecimal limitMarketValue, String checkMaType, List<Integer> maList) {
        List<RankBizDataDiff> bkList = listBiz(NUM_MAX_999);//查询主题排名by时间类型、显示个数
        for (RankBizDataDiff banKuai : bkList) {
            String banKuaiCode = banKuai.getF12();
            String banKuaiName = banKuai.getF14();
            if (StringUtils.isNotBlank(bk) && !banKuaiCode.equals(bk)) {
                continue;//特定板块非空过滤
            }

            System.out.println("特定板块:" + banKuaiName + "," + banKuaiCode);
            List<RankStockCommpanyDb> stockList = listRankStockByBiz(NUM_MAX_999, banKuaiCode);//查询股票列表-根据板块

//            BigDecimal flowInBk = banKuai.getF62().divide(new BigDecimal("100000000"), 2, BigDecimal.ROUND_HALF_UP);
//            BigDecimal marketValueBk = banKuai.getF20().divide(new BigDecimal("100000000"), 2, BigDecimal.ROUND_HALF_UP);
//            BigDecimal flowRateBk = flowInBk.divide(marketValueBk, 6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100").setScale(4, BigDecimal.ROUND_HALF_UP));

//            StringBuffer sb = new StringBuffer();
//            System.out.println("-----------" + stBizCountTemp + ",当前板块：" + banKuaiName + "涨跌：[" + banKuai.getF3() + "]" + ",个数：[" + stockList.size() + "]，板块主力净流入:[" + flowInBk + "]" + "," + "流入市值比：[" + flowRateBk + "]");
//            System.out.println(sb.toString());

            // 最新周期价格
            for (RankStockCommpanyDb entity : stockList) {
                entity.setDate(date);
                String zqdm = entity.getF12();
                String zqmc = entity.getF14();

                //检查股票:状态、是否主板股票、市值限定
                if (!checkIsMainStockLimit(entity, limitMarketValue)) {
                    continue;
                }

                BigDecimal curPriceArea = handlerAvgLine(KlineService.findNetMinMaxAvg(zqdm, MA_20, KLT_101, false, "", date, KLINE_TYPE_STOCK));
                if (curPriceArea != null && curPriceArea.compareTo(curPriceAreaMax) <= 0) {
//                        StringBuffer maSb = new StringBuffer("20日:").append(curPriceArea);
                    Map<String, String> stockMap = new HashMap<>();//
                    stockMap.put(zqdm, zqmc);
                    KlineService.checkMa(stockMap, checkMaType, maList, date, true);// 检查均线
                }
            }
        }
    }

    /**
     * 检查股票:状态、是否主板股票、市值限定
     *
     * @param entity
     * @param limitMarketValue
     * @return
     */
    private static boolean checkIsMainStockLimit(RankStockCommpanyDb entity, BigDecimal limitMarketValue) {
        String zqdm = entity.getF12();
        String zqmc = entity.getF14();
        if (entity == null) {
            System.out.println("实体信息为null，不更新db：");
            return false;
        }
        if (StringUtils.isBlank(zqdm)) {
            System.out.println("实体信息异常，不更新db：" + JSON.toJSONString(entity));
            return false;
        }

        // 股票状态
        if (DB_RANK_BIZ_F148_STOCK_STATUS_DELISTED == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（退市）！" + JSON.toJSONString(entity));
            return false;
        }
        if (DB_RANK_BIZ_F148_STOCK_STATUS_UNLISTED == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（未上市）！" + JSON.toJSONString(entity));
            return false;
        }
        if (DB_RANK_BIZ_F148_STOCK_STATUS_SUSPENSION == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（暂停上市）！" + JSON.toJSONString(entity));
            return false;
        }
        if (DB_RANK_BIZ_F148_STOCK_STATUS_ST == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（ST股）！" + JSON.toJSONString(entity));
            return false;
        }
        //只更新主板板块的价格
        if (entity.getF139() != DB_RANK_BIZ_F139_BK_MAIN) {
//                    System.out.println("均线价格暂不更新（非主板）！" + JSON.toJSONString(entity));
            return false;
        }
        //  市值限定,100亿以下不更新
        if (entity.getF20() != null && entity.getF20().compareTo(new BigDecimal("10000000000")) < 0) {
//                    System.out.println("均线价格暂不更新（100亿以下）！" + JSON.toJSONString(entity));
            return false;
        }
        return true;
    }


    /**
     * 更新业务板块
     *
     * @param date
     * @param bkStartNum
     */
    private static void updateBkList(String date, int bkStartNum) {
        List<RankBizDataDiff> bkList = listBiz(NUM_MAX_999);//查询主题排名by时间类型、显示个数
        int bizCountLimit = NUM_MAX_999;
        int bizCountTemp = 0;
        int stBizCountTemp = bkStartNum;
        for (RankBizDataDiff banKuai : bkList) {
            bizCountTemp++;
            if (bizCountTemp < bkStartNum) {
                System.out.println("已完成:" + banKuai.getF14() + "," + banKuai.getF12());
                continue;//已完成
            }
            if (bizCountTemp > bizCountLimit) {
                System.out.println("已中断:" + banKuai.getF14() + "," + banKuai.getF12());
                break;//限定个数中断
            }

            String banKuaiCode = banKuai.getF12();
            String banKuaiName = banKuai.getF14();
            stBizCountTemp++;
            List<RankStockCommpanyDb> stockList = listRankStockByBiz(NUM_MAX_999, banKuaiCode);//查询股票列表-根据板块
            System.out.println();
            System.out.println("-------------------------当前板块：" + stBizCountTemp + "---" + banKuaiName + "---[" + banKuai.getF3() + "]---" + stockList.size());
            System.out.println();

            // 最新周期价格
            for (RankStockCommpanyDb entitySource : stockList) {
                if (entitySource == null) {
                    System.out.println("实体信息为null，不更新db：");
                    continue;
                }
                RankStockCommpanyDb entity = new RankStockCommpanyDb();
                entity.setF12(entitySource.getF12());
                entity.setF14(entitySource.getF14().replace(" ", ""));
                entity.setType(banKuaiCode);
                entity.setType_name(banKuaiName);
                int rs = RankStockCommpanyDao.updateBizByCode(entity);
                System.out.println("更新业务板块---------------------" + ",rs:" + rs + JSON.toJSONString(entity));
            }
        }
    }


    /**
     * 查询昨日主题排名
     *
     * @param endCount
     */
    public static List<RankBizDataDiff> listBiz(int endCount) {
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

    /**
     * 显示业务排行-插入sql
     *
     * @param rankBizDataDiffList
     * @param queryType
     * @param today
     */
    private static int showBizSql(List<RankStockCommpanyDb> rankBizDataDiffList, String queryType, String typeName, String today) {
        int rs = 0;
        int orderNum = 0;//序号

        for (RankStockCommpanyDb entity : rankBizDataDiffList) {
            orderNum++;
            //显示插入数据库语句
//            {
//                System.out.println("INSERT INTO `bank19`.`rank_st_biz_com`(" +
//                        "`rs`" +
//                        ",`date`" +
//                        ",`type`" +
//                        ",`type_name`" +
//                        ",`order_num`" +
//                        ",`f2`" +
//                        ",`f3`" +
//                        ",`f4`" +
//                        ",`f5`" +
//                        ",`f6`" +
//                        ",`f7`" +
//                        ",`f8`" +
//                        ",`f9`" +
//                        ",`f10`" +
//                        ",`f11`" +
//                        ",`f12`" +
//                        ",`f13`" +
//                        ",`f14`" +
//                        ",`f15`" +
//                        ",`f16`" +
//                        ",`f17`" +
//                        ",`f18`" +
//                        ",`f20`" +
//                        ",`f21`" +
//                        ",`f22`" +
//                        ",`f23`" +
//                        ",`f24`" +
//                        ",`f25`" +
//                        ",`f26`" +
//                        ",`f33`" +
//                        ",`f62`" +
//                        ",`f104`" +
//                        ",`f105`" +
//                        ",`f107`" +
//                        ",`f115`" +
//                        ",`f124`" +
//                        ",`f128`" +
//                        ",`f140`" +
//                        ",`f141`" +
//                        ",`f136`" +
//                        ",`f152`" +
//                        ",`f207`" +
//                        ",`f208`" +
//                        ",`f209`" +
//                        ",`f222`" +
//                        ") VALUES (" +
//                        " '" + JSON.toJSONString(rankBizDataDiff) + "'" +
//                        " ,'" + today + "'" +
//                        " ,'" + queryType + "'" +
//                        " ,'" + typeName + "'" +
//                        " ," + orderNum + "" +
//                        " ," + rankBizDataDiff.getF2() + "" +
//                        " ," + rankBizDataDiff.getF3() + "" +
//                        " ," + rankBizDataDiff.getF4() + "" +
//                        " ," + rankBizDataDiff.getF5() + "" +
//                        " ," + rankBizDataDiff.getF6() + "" +
//                        " ," + rankBizDataDiff.getF7() + "" +
//                        " ," + rankBizDataDiff.getF8() + "" +
//                        " ," + rankBizDataDiff.getF9() + "" +
//                        " ," + rankBizDataDiff.getF10() + "" +
//                        " ," + rankBizDataDiff.getF11() + "" +
//                        " ,'" + rankBizDataDiff.getF12() + "'" +
//                        " ," + rankBizDataDiff.getF13() + "" +
//                        " ,'" + rankBizDataDiff.getF14() + "'" +
//                        " ," + rankBizDataDiff.getF15() + "" +
//                        " ," + rankBizDataDiff.getF16() + "" +
//                        " ," + rankBizDataDiff.getF17() + "" +
//                        " ," + rankBizDataDiff.getF18() + "" +
//                        " ," + rankBizDataDiff.getF20() + "" +
//                        " ," + rankBizDataDiff.getF21() + "" +
//                        " ," + rankBizDataDiff.getF22() + "" +
//                        " ,'" + rankBizDataDiff.getF23() + "'" +
//                        " ," + rankBizDataDiff.getF24() + "" +
//                        " ," + rankBizDataDiff.getF25() + "" +
//                        " ,'" + rankBizDataDiff.getF26() + "'" +
//                        " ," + rankBizDataDiff.getF33() + "" +
//                        " ," + rankBizDataDiff.getF62() + "" +
//                        " ," + rankBizDataDiff.getF104() + "" +
//                        " ," + rankBizDataDiff.getF105() + "" +
//                        " ," + rankBizDataDiff.getF107() + "" +
//                        " ,'" + rankBizDataDiff.getF115() + "'" +
//                        " ," + rankBizDataDiff.getF124() + "" +
//                        " ,'" + rankBizDataDiff.getF128() + "'" +
//                        " ,'" + rankBizDataDiff.getF140() + "'" +
//                        " ," + rankBizDataDiff.getF141() + "" +
//                        " ," + rankBizDataDiff.getF136() + "" +
//                        " ," + rankBizDataDiff.getF152() + "" +
//                        " ,'" + rankBizDataDiff.getF207() + "'" +
//                        " ,'" + rankBizDataDiff.getF208() + "'" +
//                        " ," + rankBizDataDiff.getF209() + "" +
//                        " ," + rankBizDataDiff.getF222() + "" +
//                        ");");
//            }

            {
                Date curDate = new Date();
                entity.setDate(today);
                entity.setMonth(DateUtil.getYearMonth(today, DateUtil.YYYY_MM_DD));
                entity.setWeekYear(DateUtil.getYearWeek(today, DateUtil.YYYY_MM_DD));
                entity.setWeek(DateUtil.getWeekByYyyyMmDd(today, DateUtil.YYYY_MM_DD));
                entity.setType(queryType);
                entity.setType_name(typeName);
                entity.setOrder_num(Long.valueOf(orderNum));
                entity.setCREATE_TIME(curDate);
                entity.setUPDATE_TIME(curDate);
                entity.setF14(entity.getF14().replace(" ", ""));

                //db-更新要点内容
                rs = RankStockCommpanyDao.insertDb(entity);//

//                RankStockCommpanyDb entity = new RankStockCommpanyDb();
//                entity.setRs("");
//                entity.setDate(today);
//                entity.setType(queryType);
//                entity.setType_name(typeName);
//                entity.setOrder_num(Long.valueOf(orderNum));
//                entity.setF2(rankBizDataDiff.getF2());
//                entity.setF3(rankBizDataDiff.getF3());
//                entity.setF4(rankBizDataDiff.getF4());
//                entity.setF5(rankBizDataDiff.getF5());
//                entity.setF6(rankBizDataDiff.getF6());
//                entity.setF7(rankBizDataDiff.getF7());
//                entity.setF8(rankBizDataDiff.getF8());
//                entity.setF9(rankBizDataDiff.getF9());
//                entity.setF10(rankBizDataDiff.getF10());
//                entity.setF11(rankBizDataDiff.getF11());
//                entity.setF12(rankBizDataDiff.getF12());
//                entity.setF13(rankBizDataDiff.getF13());
//                entity.setF14(rankBizDataDiff.getF14());
//                entity.setF15(rankBizDataDiff.getF15());
//                entity.setF16(rankBizDataDiff.getF16());
//                entity.setF17(rankBizDataDiff.getF17());
//                entity.setF18(rankBizDataDiff.getF18());
//                entity.setF20(rankBizDataDiff.getF20());
//                entity.setF21(rankBizDataDiff.getF21());
//                entity.setF22(rankBizDataDiff.getF22());
//                entity.setF23(rankBizDataDiff.getF23());
//                entity.setF24(rankBizDataDiff.getF24());
//                entity.setF25(rankBizDataDiff.getF25());
//                entity.setF26(rankBizDataDiff.getF26());
//                entity.setF33(rankBizDataDiff.getF33());
//                entity.setF62(rankBizDataDiff.getF62());
//                entity.setF104(rankBizDataDiff.getF104());
//                entity.setF105(rankBizDataDiff.getF105());
//                entity.setF107(rankBizDataDiff.getF107());
//                entity.setF115(rankBizDataDiff.getF115());
//                entity.setF124(rankBizDataDiff.getF124());
//                entity.setF128(rankBizDataDiff.getF128());
//                entity.setF140(rankBizDataDiff.getF140());
//                entity.setF141(rankBizDataDiff.getF141());
//                entity.setF136(rankBizDataDiff.getF136());
//                entity.setF152(rankBizDataDiff.getF152());
//                entity.setF207(rankBizDataDiff.getF207());
//                entity.setF208(rankBizDataDiff.getF208());
//                entity.setF209(rankBizDataDiff.getF209());
//                entity.setF222(rankBizDataDiff.getF222());

            }

        }
        return rs;
    }


    /**
     * 查询昨日主题排名
     */
    public static List<RankStockCommpanyDb> listRankStockByBiz(int pageSize, String biz) {
        //http://push2.eastmoney.com/api/qt/clist/get?cb=jQuery112307730222083783287_1617467610779&fid=f62&po=1&pz=50&pn=1&np=1&fltt=2&invt=2&ut=b2884a393a59ad64002292a3e90d46a5&fs=b%3ABK0891&fields=f12%2Cf14%2Cf2%2Cf3%2Cf62%2Cf184%2Cf66%2Cf69%2Cf72%2Cf75%2Cf78%2Cf81%2Cf84%2Cf87%2Cf204%2Cf205%2Cf124
        StringBuffer urlParam = new StringBuffer();
        long curTime = System.currentTimeMillis();
        String url = "http://push2.eastmoney.com/api/qt/clist/get?";
        urlParam.append("cb=jQuery112307730222083783287_" + curTime +
                "&pn=1" +//页数
                "&pz=" + pageSize +//每页数量
                "&po=1" +//pageorder:页面排序：0-正序；1-倒序
                "&np=1" +
                "&ut=b2884a393a59ad64002292a3e90d46a5" +
                "&fltt=2" +//浮点数精度
                "&invt=3" +//显示格式：-；0.0
//                "&fid=f20" +//排序字段：f20:总市值
                "&fid=f3" +//排序字段：f3:涨跌幅
                "&fs=b:" + biz +
                //fields: f12,f14,f2,f3,f62,f184,f66,f69,f72,f75,f78,f81,f84,f87,f204,f205,f124
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
                "");
        String rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);
        /**
         * 如果返回异常，n次重试
         */
        for (int i = 0; i < 10; i++) {
            if (StringUtils.isBlank(rs)) {
                rs = HttpUtil.sendGet(url, urlParam.toString(), "");
            } else {
                break;
            }
        }
        if (rs == null) {
            return null;
        }
        if (rs.startsWith("jQuery")) {
            rs = rs.substring(rs.indexOf("{"));
        }
        if (rs.endsWith(");")) {
            rs = rs.substring(0, rs.length() - 2);
        }

        JSONObject rsJsonObj = JSONObject.parseObject(rs);
//        System.out.println(rs);//返回结果
        if (!rsJsonObj.containsKey("data")) {
            System.out.println("data返回结果异常");//返回结果
            return null;
        }
        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
        List<RankStockCommpanyDb> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankStockCommpanyDb.class);
//        for (RankBizDataDiff row : rankBizDataDiffList) {
//            System.out.println(JSON.toJSON(row));//每个行业一行数据
//        }
        return rankBizDataDiffList;
    }

    /**
     * 获取均线数据
     *
     * @param netMap
     * @return
     */
    private static BigDecimal handlerAvgLine(Map<String, BigDecimal> netMap) {
        StringBuffer sb = new StringBuffer();
        BigDecimal curPriceArea = null;
        BigDecimal curPrice = netMap.get(keyRsNetClose);
        BigDecimal minPrice = netMap.get(keyRsMin);
        BigDecimal maxPrice = netMap.get(keyRsMax);
        if (curPrice != null && minPrice != null && maxPrice != null) {
            if (maxPrice.subtract(minPrice).compareTo(new BigDecimal("0")) == 0) {
                sb.append("\t").append("：").append("\t").append(netMap.get(keyRsNetCloseAvg));
                sb.append("\t").append(",最低：").append("\t").append(minPrice);
                sb.append("\t").append(",最高：").append("\t").append(maxPrice);
                sb.append("\t").append(",当前价：").append(curPrice);
            } else {
                curPriceArea = curPrice.subtract(minPrice).divide(maxPrice.subtract(minPrice), 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);

            }
//            sb.append(strHead).append("区间：").append("\t").append(curPriceArea).append("%").append(",");
//            sb.append(strHead).append(curPriceArea).append("%").append(",");
//            sb.append(strHead).append(curPriceArea).append("\t\t");
        }
//        sb.append("\t").append(strHead).append("：").append("\t").append(netMap.get(keyRsNetCloseAvg));
//        sb.append("\t").append(",最低：").append("\t").append(minPrice);
//        sb.append("\t").append(",最高：").append("\t").append(maxPrice);
//        sb.append("\t").append(",当前价：").append(curPrice);

        return curPriceArea;
    }

}
