package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.RankStockCommpanyDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.*;
import ttjj.service.FundFlowService;
import ttjj.service.KlineService;
import ttjj.service.ReportService;
import utils.Content;
import utils.DateUtil;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static utils.Content.*;

/**
 * 排行-行业股票-公司-每日明细
 */
public class StockDemo {
    public static void main(String[] args) {
        boolean isReport = false;//是否查询业绩报表
        /**
         * 添加或更新股票-根据日期
         */
        for (int i = 0; i < 1; i++) {
            String date = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -i);
//            String date = "2021-12-03";

            int startNum = 0;//开始位置，默认0

//            deleteTodayStCom();//删除数据-今日

            //  添加或更新股票-根据日期
            addTodayStCom(date, startNum);
//            updateNetToday(date, startNum, true, false, false, false, false, false, false, isReport);//  更新净值
//            updateNetToday(date, startNum, true, true, true, false, true, false, true, isReport);//  更新净值
            updateNetToday(date, startNum, true, true, true, true, true, true, true, isReport);//  更新净值
//            updateFundFlow(date, startNum);//更新当日资金流信息
//            updateConception(date,0);//更新题材概念


//            //查询业绩报表
//            String begDate = "20210701";
//            String endDate = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//            List<RankBizDataDiff> bkList = listBiz(NUM_MAX_999);//查询主题-排名by时间类型、显示个数
//            listReport(bkList, "2021Q3", begDate, endDate);

//            updateBkList(date, startNum);//更新业务板块
        }


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
     * 删除数据-今日
     *
     */
    private static void deleteTodayStCom() {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
        int rs = RankStockCommpanyDao.deleteStComByDate(date);
        System.out.println("日期：" + date + "，删除结果：" + rs);
    }

    /**
     * 更新当日资金流信息
     *
     * @param date
     */
    private static void updateFundFlow(String date, int startNum) {
        List<RankBizDataDiff> bkList = listBiz(NUM_MAX_99);//查询主题排名by时间类型、显示个数
        int bizCountLimit = NUM_MAX_999;
        int bizCountTemp = 0;
        for (RankBizDataDiff biz : bkList) {
            bizCountTemp++;
            if (bizCountTemp < startNum) {
                System.out.println("已完成:" + biz.getF14() + "," + biz.getF12());
                continue;//已完成
            }
            if (bizCountTemp > bizCountLimit) {
                break;//限定个数中断
            }
        }
        int stBizCountTemp = startNum;
        List<RankStockCommpanyDb> stockList = new ArrayList<>();
        for (RankBizDataDiff banKuai : bkList) {
            String banKuaiCode = banKuai.getF12();
            String banKuaiName = banKuai.getF14();
            stBizCountTemp++;
            stockList.addAll(listRankStockByBiz(NUM_MAX_999, banKuaiCode));
//            System.out.println();
            System.out.println("-------------------------当前stBizCountTemp：" + stBizCountTemp + "---" + banKuaiName + "---[" + banKuai.getF3() + "]---" + stockList.size());
//            System.out.println();
        }

        for (RankStockCommpanyDb entity : stockList) {
            String stCode = entity.getF12();
            if (entity == null) {
                System.out.println("实体信息为null，不更新db：");
                continue;
            }
            entity.setDate(date);
            if (StringUtils.isBlank(stCode)) {
                System.out.println("实体信息异常，不更新db：" + JSON.toJSONString(entity));
                continue;
            }

            // 股票状态
            if (DB_RANK_BIZ_F148_STOCK_STATUS_DELISTED == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（退市）！" + JSON.toJSONString(entity));
                continue;
            }
            if (DB_RANK_BIZ_F148_STOCK_STATUS_UNLISTED == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（未上市）！" + JSON.toJSONString(entity));
                continue;
            }
            if (DB_RANK_BIZ_F148_STOCK_STATUS_SUSPENSION == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（暂停上市）！" + JSON.toJSONString(entity));
                continue;
            }
            if (DB_RANK_BIZ_F148_STOCK_STATUS_ST == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（ST股）！" + JSON.toJSONString(entity));
                continue;
            }
            //只更新主板板块的价格
            if (entity.getF139() != DB_RANK_BIZ_F139_BAN_KUAI) {
//                    System.out.println("均线价格暂不更新（非主板）！" + JSON.toJSONString(entity));
                continue;
            }
            //  市值限定,100亿以下不更新
            if (entity.getF20() != null && entity.getF20().compareTo(new BigDecimal("10000000000")) < 0) {
//                    System.out.println("均线价格暂不更新（100亿以下）！" + JSON.toJSONString(entity));
                continue;
            }

            if (entity.getF139() == DB_RANK_BIZ_F139_BAN_KUAI) {
                String rsFundFlow = FundFlowService.httpFundFlowRs(stCode);

                RankStockCommpanyDb entityDb = new RankStockCommpanyDb();
                entityDb.setF12(stCode);
                entityDb.setDate(date);

                entityDb.setFundFlow(rsFundFlow);
                int updateRs = RankStockCommpanyDao.updateByCode(entityDb);
                System.out.println("更新结果：" + updateRs + "," + entity.getF14());
            } else {
                System.out.println("非主板不更新！");
            }

        }
    }

    /**
     * 查询业绩报表
     *
     * @param bkList  板块列表
     * @param quarter 季度
     * @param begDate
     * @param endDate
     */
    private static void listReport(List<RankBizDataDiff> bkList, String quarter, String begDate, String endDate) {
        for (RankBizDataDiff banKuai : bkList) {
            String banKuaiCode = banKuai.getF12();
            String banKuaiName = banKuai.getF14();
            List<RankStockCommpanyDb> stockList = listRankStockByBiz(NUM_MAX_999, banKuaiCode);
            System.out.println();
            System.out.println("-------------------------当前板块：" + "---" + banKuaiName + "---[" + banKuai.getF3() + "]---" + stockList.size());
            System.out.println();

            // 最新周期价格
            for (RankStockCommpanyDb entity : stockList) {
                String stCode = entity.getF12();
                if (entity == null) {
                    System.out.println("实体信息为null，不更新db：");
                    continue;
                }
                if (StringUtils.isBlank(stCode)) {
                    System.out.println("实体信息异常，不更新db：" + JSON.toJSONString(entity));
                    continue;
                }

                // 股票状态
                if (DB_RANK_BIZ_F148_STOCK_STATUS_DELISTED == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（退市）！" + JSON.toJSONString(entity));
                    continue;
                }
                if (DB_RANK_BIZ_F148_STOCK_STATUS_UNLISTED == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（未上市）！" + JSON.toJSONString(entity));
                    continue;
                }
                if (DB_RANK_BIZ_F148_STOCK_STATUS_SUSPENSION == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（暂停上市）！" + JSON.toJSONString(entity));
                    continue;
                }
                if (DB_RANK_BIZ_F148_STOCK_STATUS_ST == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（ST股）！" + JSON.toJSONString(entity));
                    continue;
                }
                //只更新主板板块的价格
                if (entity.getF139() != DB_RANK_BIZ_F139_BAN_KUAI) {
//                    System.out.println("均线价格暂不更新（非主板）！" + JSON.toJSONString(entity));
                    continue;
                }
                //  市值限定,100亿以下不更新
                if (entity.getF20() != null && entity.getF20().compareTo(new BigDecimal("10000000000")) < 0) {
//                    System.out.println("均线价格暂不更新（100亿以下）！" + JSON.toJSONString(entity));
                    continue;
                }
                if (entity.getF139() == DB_RANK_BIZ_F139_BAN_KUAI) {
//                    System.out.println("股票---------------------" + entity.getF14() + ":"+ entity.getF3() + JSON.toJSONString(entity));
                    List<Report> rsReport = ReportService.listHttpReportByStCode(stCode);
                    for (Report report : rsReport) {
                        //是否有2021三季报
                        if (report.getQDATE().equals(quarter)) {
                            BigDecimal tbzzYyzsr = report.getYSTZ();
                            BigDecimal tbzzJlr = report.getSJLTZ();
                            BigDecimal hbzzYyzsr = report.getYSHZ();
                            BigDecimal hbzzJlr = report.getSJLHZ();
//                                System.out.println(JSON.toJSONString(report));
                            String stName = report.getSECURITY_NAME_ABBR();
//                            if (stName.equals("维远股份")) {
//                                System.out.println();
//                            }

                            //查询区间价格涨跌-最近4个月
                            BigDecimal totalAdr = new BigDecimal("0");
                            List<Kline> klines = KlineService.kline(stCode, 4, Content.KLT_103, true, begDate, endDate, KLINE_TYPE_STOCK);
                            for (Kline kline : klines) {
                                totalAdr = totalAdr.add(kline.getZhangDieFu()).setScale(2, BigDecimal.ROUND_HALF_UP);
                            }

                            boolean tbzzYyzsrFlag = false;
                            boolean tbzzJlrFlag = false;
                            boolean hbzzYyzsrFlag = false;
                            boolean hbzzJlrFlag = false;
                            int yeAddCount = 0;//业绩增长个数，统计4项
                            if (tbzzYyzsr != null && tbzzYyzsr.compareTo(totalAdr) > 0 && tbzzYyzsr.compareTo(new BigDecimal("0")) > 0 && report.getTOTAL_OPERATE_INCOME().compareTo(new BigDecimal("0")) > 0) {
                                tbzzYyzsrFlag = true;
                            }
                            if (tbzzJlr != null && tbzzJlr.compareTo(totalAdr) > 0 && tbzzJlr.compareTo(new BigDecimal("0")) > 0 && report.getPARENT_NETPROFIT().compareTo(new BigDecimal("0")) > 0) {
                                tbzzJlrFlag = true;
                            }
                            if (hbzzYyzsr != null && hbzzYyzsr.compareTo(totalAdr) > 0 && hbzzYyzsr.compareTo(new BigDecimal("0")) > 0 && report.getTOTAL_OPERATE_INCOME().compareTo(new BigDecimal("0")) > 0) {
                                hbzzYyzsrFlag = true;
                            }
                            if (hbzzJlr != null && hbzzJlr.compareTo(totalAdr) > 0 && hbzzJlr.compareTo(new BigDecimal("0")) > 0 && report.getPARENT_NETPROFIT().compareTo(new BigDecimal("0")) > 0) {
                                hbzzJlrFlag = true;
                            }
                            if (tbzzYyzsrFlag) {
                                yeAddCount++;
                            }
                            if (tbzzJlrFlag) {
                                yeAddCount++;
                            }
                            if (hbzzYyzsrFlag) {
                                yeAddCount++;
                            }
                            if (hbzzJlrFlag) {
                                yeAddCount++;
                            }

                            if (yeAddCount == 4) {
                                System.out.print(stName);
                                System.out.print("；季度涨跌幅:" + totalAdr);
                                System.out.print("；今日涨跌幅:" + entity.getF3());
                                System.out.print("；PE:" + entity.getF9());
                                System.out.print("；ROE:" + entity.getF37());
                                System.out.print("；主力-净流入:" + entity.getF62().divide(new BigDecimal("100000000"), 2, BigDecimal.ROUND_HALF_UP));
                                if (tbzzYyzsr != null) {
                                    System.out.print("；营业总收入-同比增长:" + tbzzYyzsr.setScale(2, BigDecimal.ROUND_HALF_UP));
                                }
                                if (tbzzJlr != null) {
                                    System.out.print("；净利润-同比增长:" + tbzzJlr.setScale(2, BigDecimal.ROUND_HALF_UP));
                                }
                                if (hbzzYyzsr != null) {
                                    System.out.print("；营业总收入-环比增长:" + hbzzYyzsr.setScale(2, BigDecimal.ROUND_HALF_UP));
                                }
                                if (hbzzJlr != null) {
                                    System.out.print("；净利润-环比增长:" + hbzzJlr.setScale(2, BigDecimal.ROUND_HALF_UP));
                                }
                                if (tbzzYyzsrFlag) {
                                    System.out.print(",营收同比增长！");
                                }
                                if (tbzzJlrFlag) {
                                    System.out.print(",净利润同比增长！");
                                }
                                if (hbzzYyzsrFlag) {
                                    System.out.print(",营收环比增长！");
                                }
                                if (hbzzJlrFlag) {
                                    System.out.print(",净利润环比增长！");
                                }
                                System.out.println();
                            }
                            break;
                        }
//                            System.out.println(qDate + "没有查询到！");
                        break;//只查询第一个
                    }
                }
            }
        }
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
        condition.setF139(DB_RANK_BIZ_F139_BAN_KUAI);
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
     * 添加股票-历史日期
     */
    private static void addHistroyStCom() {
        int addDaysMax = 0;//开始日期增加天数
        int year = 2021;//2021
        int month = 12;//DateUtil.getCurMonth()
        int day = 3;//DateUtil.getCurDay()
        String begDate = DateUtil.getDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, year, month, day, addDaysMax);//查询新增交易的开始时间
        int days = 0;//最多增加的天数
        String endDate = DateUtil.getDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, year, month, day, days);//查询新增交易的开始时间
        int startMapNum = 0;//map的开始，中断后使用，默认可设置为0

        // 查询所有行业map
        Map<String, String> bizMap = new LinkedHashMap<>();
        List<RankBizDataDiff> bizList = listBiz(100);//查询主题排名by时间类型、显示个数
        for (RankBizDataDiff biz : bizList) {
//            System.out.println("/**" + JSON.toJSONString(biz) + "**/");
            bizMap.put(biz.getF12(), biz.getF14());
        }

        // 遍历行业map，插入行业内的股票

        int stBizCountTemp = 0;
        for (String bizCode : bizMap.keySet()) {
            stBizCountTemp++;
            System.out.println();
            System.out.println("-------------------------当前stBizCountTemp：" + stBizCountTemp + "---" + JSON.toJSONString(bizMap));
            if (stBizCountTemp < startMapNum) {
                System.out.println("已完成:" + bizMap.get(bizCode));
                continue;//已完成
            }
            List<RankStockCommpanyDb> rankBizDataDiffListBiz = listRankStockByBiz(500, bizCode);

            addRankStockCommpanyDbByList(rankBizDataDiffListBiz, begDate, endDate, days, bizCode, bizMap.get(bizCode));//插入行业内的股票,根据开始日期，截止天数
        }
    }

    /**
     * 插入行业内的股票,根据开始日期，截止天数
     *
     * @param rankBizDataDiffListBiz
     * @param startDate
     * @param endDate
     * @param count
     * @param bizCode
     * @param bizName
     */
    private static void addRankStockCommpanyDbByList(List<RankStockCommpanyDb> rankBizDataDiffListBiz, String startDate, String endDate, int count, String bizCode, String bizName) {
        for (RankStockCommpanyDb rankStockCommpanyDbBiz : rankBizDataDiffListBiz) {
            String zqdm = rankStockCommpanyDbBiz.getF12();
            List<Kline> klines = KlineService.kline(zqdm, count, Content.KLT_101, true, startDate, endDate, KLINE_TYPE_STOCK);
            if (klines == null || klines.size() == 0) {
                System.out.println("查询k线为空，证券代码：" + zqdm);
                continue;
            }
            for (Kline kline : klines) {
                RankStockCommpanyDb rankStockCommpanyDb = new RankStockCommpanyDb();
                rankStockCommpanyDb.setDate(kline.getKtime());
                rankStockCommpanyDb.setType(bizCode);
                rankStockCommpanyDb.setType_name(bizName);
                rankStockCommpanyDb.setF1(2L);
                //f139 2-A股主板(00XXXX/60XXXX);3-B股(200XXX/900XXX);5-创业板(30XXXX);32-科创板(688XXX)
                if (zqdm.startsWith("900")) {//B股
                    rankStockCommpanyDb.setF1(3L);
                }
                rankStockCommpanyDb.setF2(kline.getCloseAmt().doubleValue());
                rankStockCommpanyDb.setF3(kline.getZhangDieFu().doubleValue());
                rankStockCommpanyDb.setF4(kline.getZhangDieE().doubleValue());
                rankStockCommpanyDb.setF5(kline.getCjl().longValue());
                rankStockCommpanyDb.setF6(kline.getCje().longValue());
                rankStockCommpanyDb.setF7(kline.getZhenFu().doubleValue());
                rankStockCommpanyDb.setF8(kline.getHuanShouLv().doubleValue());
                rankStockCommpanyDb.setF12(kline.getZqdm());
                rankStockCommpanyDb.setF14(kline.getZqmc());
                rankStockCommpanyDb.setF15(kline.getMaxAmt().doubleValue());
                rankStockCommpanyDb.setF16(kline.getMinAmt().doubleValue());
                rankStockCommpanyDb.setF17(kline.getOpenAmt().doubleValue());
                rankStockCommpanyDb.setF18(kline.getCloseAmt().subtract(kline.getZhangDieE()).doubleValue());

                List<RankStockCommpanyDb> rankBizDataDiffListDb = new ArrayList<>();
                rankBizDataDiffListDb.add(rankStockCommpanyDb);
                showBizSql(rankBizDataDiffListDb, bizCode, bizName, kline.getKtime());//显示业务排行-插入sql
            }
        }
    }

    /**
     * 添加或更新股票-根据日期
     *
     * @param date
     */
    private static void addTodayStCom(String date, int startNum) {
        List<RankBizDataDiff> bkList = listBiz(NUM_MAX_99);//查询主题排名by时间类型、显示个数
        int bizCountLimit = NUM_MAX_999;
        int bizCountTemp = 0;
        for (RankBizDataDiff biz : bkList) {
            bizCountTemp++;
            if (bizCountTemp < startNum) {
                System.out.println("已完成:" + biz.getF14() + "," + biz.getF12());
                continue;//已完成
            }
            if (bizCountTemp > bizCountLimit) {
                break;//限定个数中断
            }
        }
        int stBizCountTemp = startNum;
        for (RankBizDataDiff banKuai : bkList) {
            String banKuaiCode = banKuai.getF12();
            String banKuaiName = banKuai.getF14();
            stBizCountTemp++;
            List<RankStockCommpanyDb> stockList = listRankStockByBiz(NUM_MAX_999, banKuaiCode);
//            System.out.println();
//            System.out.println();

            showBizSql(stockList, banKuaiCode, banKuaiName, date);//显示业务排行-插入sql
            System.out.println("-------------------------当前stBizCountTemp：" + stBizCountTemp + "---" + banKuaiName + "---[" + banKuai.getF3() + "]---" + stockList.size());
        }
    }

    /**
     * 更新净值
     *
     * @param date
     * @param startNum
     * @param isReport
     */
    private static void updateNetToday(String date, int startNum, boolean isMa5, boolean isMa10, boolean isMa20, boolean isMa30, boolean isMa60, boolean isMa120, boolean isMa250, boolean isReport) {
        List<RankBizDataDiff> bkList = listBiz(NUM_MAX_999);//查询主题排名by时间类型、显示个数
        int bizCountLimit = NUM_MAX_999;
        int bizCountTemp = 0;
        int stBizCountTemp = startNum;
        for (RankBizDataDiff banKuai : bkList) {
            bizCountTemp++;
            if (bizCountTemp < startNum) {
                System.out.println("已完成:" + banKuai.getF14() + "," + banKuai.getF12());
                continue;//已完成
            }
            if (bizCountTemp > bizCountLimit) {
                break;//限定个数中断
            }

            String banKuaiCode = banKuai.getF12();
            String banKuaiName = banKuai.getF14();
            BigDecimal flowInBk = banKuai.getF62().divide(new BigDecimal("100000000"), 2, BigDecimal.ROUND_HALF_UP);
            BigDecimal marketValueBk = banKuai.getF20().divide(new BigDecimal("100000000"), 2, BigDecimal.ROUND_HALF_UP);
            BigDecimal flowRateBk = flowInBk.divide(marketValueBk, 6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100").setScale(4, BigDecimal.ROUND_HALF_UP));
            stBizCountTemp++;
            List<RankStockCommpanyDb> stockList = listRankStockByBiz(NUM_MAX_999, banKuaiCode);//查询股票列表-根据板块
            System.out.println();
            StringBuffer sb = new StringBuffer();
            sb.append("-----------").append(stBizCountTemp).append(",");
            sb.append("当前板块：").append("[").append(banKuaiName).append("]").append(",");
            sb.append("涨跌：").append("[").append(banKuai.getF3()).append("]").append(",");
            sb.append("个数：").append("[").append(stockList.size()).append("]").append(",");
            sb.append("板块主力净流入：").append("[").append(flowInBk).append("]").append(",");
            sb.append("流入市值比：").append("[").append(flowRateBk).append("]").append(",");
//            System.out.println("-----------" + stBizCountTemp + ",当前板块：" + banKuaiName + "涨跌：[" + banKuai.getF3() + "]" + ",个数：[" + stockList.size() + "]，板块主力净流入:[" + flowInBk + "]" + "," + "流入市值比：[" + flowRateBk + "]");
            System.out.println(sb.toString());

            // 最新周期价格
            for (RankStockCommpanyDb entity : stockList) {
                String stCode = entity.getF12();
                if (entity == null) {
                    System.out.println("实体信息为null，不更新db：");
                    continue;
                }
                entity.setDate(date);
                if (StringUtils.isBlank(stCode)) {
                    System.out.println("实体信息异常，不更新db：" + JSON.toJSONString(entity));
                    continue;
                }

                // 股票状态
                if (DB_RANK_BIZ_F148_STOCK_STATUS_DELISTED == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（退市）！" + JSON.toJSONString(entity));
                    continue;
                }
                if (DB_RANK_BIZ_F148_STOCK_STATUS_UNLISTED == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（未上市）！" + JSON.toJSONString(entity));
                    continue;
                }
                if (DB_RANK_BIZ_F148_STOCK_STATUS_SUSPENSION == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（暂停上市）！" + JSON.toJSONString(entity));
                    continue;
                }
                if (DB_RANK_BIZ_F148_STOCK_STATUS_ST == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（ST股）！" + JSON.toJSONString(entity));
                    continue;
                }
                //只更新主板板块的价格
                if (entity.getF139() != DB_RANK_BIZ_F139_BAN_KUAI) {
//                    System.out.println("均线价格暂不更新（非主板）！" + JSON.toJSONString(entity));
                    continue;
                }
                //  市值限定,100亿以下不更新
                if (entity.getF20() != null && entity.getF20().compareTo(new BigDecimal("10000000000")) < 0) {
//                    System.out.println("均线价格暂不更新（100亿以下）！" + JSON.toJSONString(entity));
                    continue;
                }
                if (entity.getF139() == DB_RANK_BIZ_F139_BAN_KUAI) {
                    // 周期价格:均线、最低、最高、收盘最低、收盘最高
                    String zqdm = entity.getF12();

                    StringBuffer maSb = new StringBuffer();
                    if (isMa5) {
                        Map<String, BigDecimal> netMap5 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, KLT_101, false, "", date, KLINE_TYPE_STOCK);
                        entity.setNET_MA_5(netMap5.get(Content.keyRsNetCloseAvg));
                        entity.setNET_MIN_7(netMap5.get(Content.keyRsMin).doubleValue());
                        entity.setNET_MAX_7(netMap5.get(Content.keyRsMax).doubleValue());
                        entity.setNET_MIN_CLOS_7(netMap5.get(Content.keyRsNetCloseMin).doubleValue());
                        entity.setNET_MAX_CLOS_7(netMap5.get(Content.keyRsNetCloseMax).doubleValue());
                        maSb.append(handlerAvgLine(" 5日:", KlineService.findNetMinMaxAvg(zqdm, MA_5, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
                    }
                    if (isMa10) {
                        Map<String, BigDecimal> netMap10 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, KLT_101, false, "", date, KLINE_TYPE_STOCK);
                        entity.setNET_MA_10(netMap10.get(Content.keyRsNetCloseAvg));
                        entity.setNET_MIN_14(netMap10.get(Content.keyRsMin).doubleValue());
                        entity.setNET_MAX_14(netMap10.get(Content.keyRsMax).doubleValue());
                        entity.setNET_MIN_CLOS_14(netMap10.get(Content.keyRsNetCloseMin).doubleValue());
                        entity.setNET_MAX_CLOS_14(netMap10.get(Content.keyRsNetCloseMax).doubleValue());
                        maSb.append(handlerAvgLine("10日:", KlineService.findNetMinMaxAvg(zqdm, MA_10, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
                    }
                    if (isMa20) {
                        Map<String, BigDecimal> netMap20 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, KLT_101, false, "", date, KLINE_TYPE_STOCK);
                        entity.setNET_MA_20(netMap20.get(Content.keyRsNetCloseAvg));
                        entity.setNET_MIN_30(netMap20.get(Content.keyRsMin).doubleValue());
                        entity.setNET_MAX_30(netMap20.get(Content.keyRsMax).doubleValue());
                        entity.setNET_MIN_CLOS_30(netMap20.get(Content.keyRsNetCloseMin).doubleValue());
                        entity.setNET_MAX_CLOS_30(netMap20.get(Content.keyRsNetCloseMax).doubleValue());
                        maSb.append(handlerAvgLine("20日:", KlineService.findNetMinMaxAvg(zqdm, MA_20, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
                    }
                    if (isMa30) {
                        Map<String, BigDecimal> netMap30 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, KLT_101, false, "", date, KLINE_TYPE_STOCK);
                        entity.setNET_MA_30(netMap30.get(Content.keyRsNetCloseAvg));
                        entity.setNET_MIN_60(netMap30.get(Content.keyRsMin).doubleValue());
                        entity.setNET_MAX_60(netMap30.get(Content.keyRsMax).doubleValue());
                        entity.setNET_MIN_CLOS_60(netMap30.get(Content.keyRsNetCloseMin).doubleValue());
                        entity.setNET_MAX_CLOS_60(netMap30.get(Content.keyRsNetCloseMax).doubleValue());
                        maSb.append(handlerAvgLine("30日:", KlineService.findNetMinMaxAvg(zqdm, MA_30, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
                    }
                    if (isMa60) {
                        Map<String, BigDecimal> netMap60 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, KLT_101, false, "", date, KLINE_TYPE_STOCK);
                        entity.setNET_MA_60(netMap60.get(Content.keyRsNetCloseAvg));
                        entity.setNET_MIN_90(netMap60.get(Content.keyRsMin).doubleValue());
                        entity.setNET_MAX_90(netMap60.get(Content.keyRsMax).doubleValue());
                        entity.setNET_MIN_CLOS_90(netMap60.get(Content.keyRsNetCloseMin).doubleValue());
                        entity.setNET_MAX_CLOS_90(netMap60.get(Content.keyRsNetCloseMax).doubleValue());
                        maSb.append(handlerAvgLine("60日:", KlineService.findNetMinMaxAvg(zqdm, MA_60, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
                    }
                    if (isMa120) {
                        Map<String, BigDecimal> netMap120 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, KLT_101, false, "", date, KLINE_TYPE_STOCK);
                        entity.setNET_MA_120(netMap120.get(Content.keyRsNetCloseAvg));
                        entity.setNET_MIN_180(netMap120.get(Content.keyRsMin).doubleValue());
                        entity.setNET_MAX_180(netMap120.get(Content.keyRsMax).doubleValue());
                        entity.setNET_MIN_CLOS_180(netMap120.get(Content.keyRsNetCloseMin).doubleValue());
                        entity.setNET_MAX_CLOS_180(netMap120.get(Content.keyRsNetCloseMax).doubleValue());
                        maSb.append(handlerAvgLine("120日:", KlineService.findNetMinMaxAvg(zqdm, MA_120, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
                    }
                    if (isMa250) {
                        Map<String, BigDecimal> netMap250 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, KLT_101, false, "", date, KLINE_TYPE_STOCK);
                        entity.setNET_MA_250(netMap250.get(Content.keyRsNetCloseAvg));
                        entity.setNET_MIN_360(netMap250.get(Content.keyRsMin).doubleValue());
                        entity.setNET_MAX_360(netMap250.get(Content.keyRsMax).doubleValue());
                        entity.setNET_MIN_CLOS_360(netMap250.get(Content.keyRsNetCloseMin).doubleValue());
                        entity.setNET_MAX_CLOS_360(netMap250.get(Content.keyRsNetCloseMax).doubleValue());
                        maSb.append(handlerAvgLine("250日:", KlineService.findNetMinMaxAvg(zqdm, MA_250, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
                    }
//                    System.out.println();
                    int rs = RankStockCommpanyDao.updateByCode(entity);
                    BigDecimal flowIn = entity.getF62() != null ? entity.getF62().divide(new BigDecimal("100000000"), 2, BigDecimal.ROUND_HALF_UP) : entity.getF62();
                    BigDecimal marketValue = entity.getF20().divide(new BigDecimal("100000000"), 2, BigDecimal.ROUND_HALF_UP);
                    BigDecimal flowRate = new BigDecimal("0");
                    if (flowIn != null && marketValue != null && marketValue.compareTo(new BigDecimal("0")) != 0) {
                        flowRate = flowIn.divide(marketValue, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
                    }

                    System.out.println("主板价格更新---------------------"
                                    + "\t" + "rs:" + rs
                                    + "\t" + entity.getF12() + ":" + entity.getF14() + ":" + entity.getF3() + "；"
                                    + "\t" + "价格区间:" + maSb.toString()
                                    + "\t" + "主力-净流入:" + flowIn
                                    + "\t" + "流入市值比：:" + flowRate
//                            + ",rs:" + rs + JSON.toJSONString(entity)
                    );

                    //  季报
                    if (isReport) {
                        List<Report> rsReport = ReportService.listHttpReportByStCode(stCode);
                        String startDate = "20210701";
                        String qDate = "2021Q3";
                        for (Report report : rsReport) {
                            //是否有2021三季报
                            if (report.getQDATE().equals(qDate)) {
                                BigDecimal hbzzYyzsr = report.getYSHZ();
                                BigDecimal hbzzJlr = report.getSJLHZ();
//                                System.out.println(JSON.toJSONString(report));
                                System.out.print(report.getSECURITY_NAME_ABBR());
//                                System.out.print("；营业总收入-同比增长:" + report.getYSTZ());
                                System.out.print("；营收-环比增长:" + hbzzYyzsr);
                                System.out.print("；净利润-环比增长:" + hbzzJlr);
                                //查询区间价格涨跌-最近4个月
                                BigDecimal totalAdr = new BigDecimal("0");
                                List<Kline> klines = KlineService.kline(stCode, 4, Content.KLT_103, true, startDate, date, KLINE_TYPE_STOCK);
                                for (Kline kline : klines) {
                                    totalAdr = totalAdr.add(kline.getZhangDieFu());
                                }
                                System.out.print("；季度涨跌幅:" + totalAdr);
                                System.out.print("；PE:" + entity.getF9());
                                System.out.print("；ROE:" + entity.getF37());
                                System.out.print("；主力-净流入:" + entity.getF62().divide(new BigDecimal("100000000"), 2, BigDecimal.ROUND_HALF_UP));
                                if (totalAdr.compareTo(hbzzJlr) < 0 && hbzzJlr.compareTo(new BigDecimal("0")) > 0) {
                                    System.out.print(",季度涨跌小于净利润环比增长！！！");
                                }
                                if (totalAdr.compareTo(hbzzYyzsr) < 0 && hbzzYyzsr.compareTo(new BigDecimal("0")) > 0) {
                                    System.out.print(",季度涨跌小于营业总收入环比增长！！！");
                                }
                                System.out.println();
                                break;
                            }
//                            System.out.println(qDate + "没有查询到！");
                            break;//只查询第一个
                        }
                    }
                }
            }
        }
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
     * 更新题材概念
     *
     * @param date
     * @param startNum
     */
    private static void updateConception(String date, int startNum) {
        List<RankBizDataDiff> bkList = listBiz(NUM_MAX_99);//查询主题排名by时间类型、显示个数
        int bizCountLimit = NUM_MAX_999;
        int bizCountTemp = 0;
        for (RankBizDataDiff biz : bkList) {
            bizCountTemp++;
            if (bizCountTemp < startNum) {
                System.out.println("已完成:" + biz.getF14() + "," + biz.getF12());
                continue;//已完成
            }
            if (bizCountTemp > bizCountLimit) {
                break;//限定个数中断
            }
        }
        int stBizCountTemp = startNum;
        for (RankBizDataDiff banKuai : bkList) {
            String banKuaiCode = banKuai.getF12();
            String banKuaiName = banKuai.getF14();
            stBizCountTemp++;
            List<RankStockCommpanyDb> stockList = listRankStockByBiz(NUM_MAX_999, banKuaiCode);
//            System.out.println();
            System.out.println("-------------------------当前stBizCountTemp：" + stBizCountTemp + "---" + banKuaiName + "---[" + banKuai.getF3() + "]---" + stockList.size());
//            System.out.println();

            for (RankStockCommpanyDb stockInfo : stockList) {
                String stCode = stockInfo.getF12();
                StringBuffer url = new StringBuffer();
                url.append("http://f10.eastmoney.com/CoreConception/CoreConceptionAjax");

                StringBuffer urlParam = new StringBuffer();
                if (stCode.startsWith("5") || stCode.startsWith("6") || stCode.startsWith("9")) {
                    urlParam.append("code=SH").append(stCode);
                } else {
                    urlParam.append("code=SZ").append(stCode);
                }

//            System.out.println("请求url:" + url + JSON.toJSONString(urlParam));
                String rs = "";
                try {
                    rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
                } catch (Exception e) {
                    System.out.println("/** http重试 **/");
                    rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
                }

                /**
                 * 如果返回异常，n次重试
                 */
                for (int i = 0; i < 10; i++) {
                    if (StringUtils.isBlank(rs)) {
                        rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
                    } else {
                        break;
                    }
                }

//            System.out.println("rs:" + rs);
                if (rs == null || rs.contains("不合法")) {
                    System.out.println("rs:" + rs);
                    continue;
                }

                //{"hxtc":[{"zqnm":"--","zqdm":"600733.SH","zqjc":"--","jyscbm":"--","gjc":"所属板块","yd":"1","ydnr":"MSCI中国 北京板块 标准普尔 成渝特区 富时罗素 固态电池 华为概念 机构重仓 汽车芯片 汽车行业 融资融券 无人驾驶 新能源车 预亏预减 中证500"},{"zqnm":"--","zqdm":"600733.SH","zqjc":"--","jyscbm":"--","gjc":"经营范围","yd":"2","ydnr":"设计、研发、销售汽车(含重型货车、大中型客车、轻型越野汽车、轻、微型客货汽车、多功能车、专用车、轿车、电动汽车、混合动力汽车)、汽车配件、机械设备、电器设备、零部件加工设备;汽车装饰;货物进出口、代理进出口、技术进出口;软件开发;技术开发、技术服务、技术咨询、技术转让;设计、制作、代理、发布国内外广告;经济贸易咨询;物业管理。"},{"zqnm":"--","zqdm":"600733.SH","zqjc":"--","jyscbm":"--","gjc":"新能源汽车","yd":"3","ydnr":"作为国家战略新兴产业之一的新能源汽车产业开拓者,北汽蓝谷子公司北京新能源汽车股份有限公司创立于2009年,是我国首家独立运营、首个获得新能源汽车生产资质的企业。北汽新能源从节能环保到电动化、智能化、网联化、共享化为目标的发展过程中实现了三年打基础、三年上水平、三年上规模的各阶段发展目标,自2013年以来连续七年保持国内新能源纯电动乘用车的销量第一。"},{"zqnm":"--","zqdm":"600733.SH","zqjc":"--","jyscbm":"--","gjc":"新能源汽车行业","yd":"4","ydnr":"2019年新能源汽车行业面临三重压力,一是汽车市场持续下行的压力,二是新能源汽车补贴急剧大幅退坡导致市场信心不足产生较大市场波动的压力,三是外资品牌、新势力、国内传统汽车企业纷纷进入新能源汽车行业的竞争压力。同时新能源汽车企业内部面临电动化及智能化技术创新、新一代产品研发、市场占有的扩张压力,新能源企业经营面临巨大挑战,但这是新兴行业螺旋式向上发展的必经阶段。在创新、协调、绿色、开放、共享的新时代发展理念指引下,新能源汽车作为国家战略型新兴产业,在政策保障、技术进步、市场认知度迅速提升、绿色环保等多因素的推动下,新能源汽车保持了快速发展的态势。根据中国汽车工业协会数据,2019年国内新能源汽车销量为120.6万辆,占世界新能源汽车销量的一半以上。世界范围内新能源汽车2019年销量为220万辆,同比增长了近10%,特别是欧洲主要国家随着碳排放限制趋严和新能源补贴增加,新能源汽车渗透率显著提升。国内新能源汽车的发展由探索阶段的政策驱动到现阶段的政策驱动和市场驱动的统筹推进,促使新能源汽车从“有没有”转向“好不好”的高质量发展。"},{"zqnm":"--","zqdm":"600733.SH","zqjc":"--","jyscbm":"--","gjc":"技术第一生产力,体系全面对抗","yd":"5","ydnr":"科学技术是第一生产力,这一论断在新兴行业表现尤为突出。新能源智能汽车首先作为汽车,其技术进步呈现为长期式的、渐进式的、积累式的特点,而电动化加智能化,其技术进步呈现为跃进式的、迭代式的、颠覆式的特点,新能源汽车技术的进步和创新是整体的、协调的、融合的,符合这一特点和要求的新能源汽车技术提升和创新是公司的核心竞争力,是公司发展的第一动力,处于公司的核心位置。公司高度重视产品的智能网联化转型。新能源汽车电动化的本质使汽车从机械能时代进入电能时代,新能源汽车具有交通工具和能源储备装置的双重属性,特别是为可再生能源(风、水、电、太阳能发电)的深度利用广度延伸奠定了基础;新能源汽车的数字化使汽车从电力时代进入信息时代,人们在汽车上拥有了庞大的应用生态;新能源汽车的智能化使汽车从信息时代进入AI时代,新能源汽车发展的技术方向要求新能源汽车企业具有强大完善的研发体系和能力。新能源汽车已从市场保护进入到市场竞争阶段,竞争已从单一产品的简单对抗发展成为研发体系和创新能力的全面对抗。北汽新能源经十年发展目前已建立了新一代新能源整车产品平台开发技术、三电技术和人工智能技术相互依托的协调研发体系和创新能力。"},{"zqnm":"--","zqdm":"600733.SH","zqjc":"--","jyscbm":"--","gjc":"系统化研发能力,完整研发生态","yd":"6","ydnr":"1、四级研发生态,要素整合共享:北汽新能源将研发能力作为企业的核心竞争力,一方面在全球范围内吸纳中外优秀专业人才,另一方面加大研发投入,构建了完整的面向未来的新一代新能源整车研发体系,提升了电池电机电控、智能网联、智能驾驶、新材料、换电储能等领域核心竞争能力。2、平台开发能力,引领高端制造:伴随着新能源汽车三电及智能技术不断积累提升,北汽新能源产品开发经历了单品“油改电”阶段、单品设计开发阶段,现已进入高性能整车模块化平台开发阶段。已建立满足新产品研发特点的整车开发管理流程,保证了新车开发进度、质量、成本、目标的要求,有效控制风险。目前已构建“大、中、小”三大类全新平台搭建,涵盖A00级到B级,轿车、SUV、CROSSOVER等多级别跨车型全面产品类型,平台底盘架构化设计,衍生多款底盘拓展方案,凸显平台车型研发周期短、开发费用相对低、通用化率高等优势。"},{"zqnm":"--","zqdm":"600733.SH","zqjc":"--","jyscbm":"--","gjc":"动力电池研发,突破提升性能","yd":"7","ydnr":"北汽新能源围绕提升电池使用性能布局研发工作,取得了多个领域关键技术的突破,完成了多个新技术在量产产品中的应用,产品竞争力显著提升。一是完成了全球首款乘用车CTP电池应用系统的开发,对于进一步提升新能源汽车的续航里程、安全与成本控制具有明显效果,助力EU5车型竞争力显著提升。二是开发了第三代IBTC智能仿生温控系统并实现批量应用,产品温控速率提升明显。三是开发了FPC集成采集技术并实现批量应用,产品稳定性显著提升。四是完成了第三代智能管理电池系统的开发并实现批量应用,集成度提升,成本降低,稳定性提高。五是完成了长寿命电池系统的开发。六是电池系统集成能力进一步提升,磷酸铁锂电池系统能量密度和新开发的三元电池系统能量密度领先于国内外同电量等级电池产品。七是建立起完善的自主开发能力,在电池系统集成技术、电池性能集成技术、电池安全技术、电池仿真分析技术、电池管理控制技术、电池测试验证技术等领域保持行业领先水平。"}]}
                JSONObject rsJo = JSON.parseObject(rs);
                JSONArray hxtcArray = JSON.parseArray(rsJo.getString("hxtc"));
                if (hxtcArray == null) {
//            System.out.println("klines未查询到："+zhiShu);
                    return;
                }
                String ydnr = "";//核心题材-要点板块
                for (int i = 0; i < hxtcArray.size(); i++) {
                    JSONObject ssbk = JSON.parseObject(hxtcArray.getString(i));
                    String gjc = ssbk.getString("gjc");
                    if ("所属板块".equals(gjc)) {
                        ydnr = ssbk.getString("ydnr");
                        break;
                    }
                }

                StringBuffer sb = new StringBuffer();
                sb.append("UPDATE `rank_st_biz_com` ");
                sb.append("SET ");
                sb.append(" `conception`='" + ydnr + "' ");
                sb.append(" WHERE `f12`='" + stockInfo.getF12() + "'");
                sb.append(" AND `date`='" + date + "'");
                sb.append(";");
                sb.append("/**" + stockInfo.getF14() + "**/");

                System.out.println(sb);

                //db-更新要点内容
                RankStockCommpanyDb entity = new RankStockCommpanyDb();
                entity.setF12(stockInfo.getF12());
                entity.setDate(date);
                entity.setConception(ydnr);//要点内容
                RankStockCommpanyDao.updateByCode(entity);
            }
        }


    }

    /**
     * 更新最新净值-限定时间段的最大最小净值
     *
     * @param today
     * @param rankBizDataDiffListBiz
     * @param days
     * @param dbFieldLastNetMin
     * @param dbFieldLastNetMinClose
     * @param dbFieldLastNetMaxClose
     */
    private static void showUpdateDbMaxMinNetByDays(String today, List<RankStockCommpanyDb> rankBizDataDiffListBiz, int days, String dbFieldLastNetMin, String dbFieldLastNetMax, String dbFieldLastNetMinClose, String dbFieldLastNetMaxClose) {
        for (RankStockCommpanyDb stockInfo : rankBizDataDiffListBiz) {
            //查询 -限定时间段的最大最小净值
//            LsjzUtil.findJzMaxMin(fundTrade.getZqdm(), days);
            //k线
            String klt = KLT_101;//klt=101:日;102:周;103:月;104:3月;105:6月;106:12月
            RankStockCommpanyDb entity = kline(stockInfo, today, stockInfo.getF12(), days, klt, dbFieldLastNetMin, dbFieldLastNetMax, dbFieldLastNetMinClose, dbFieldLastNetMaxClose);

        }
    }

    /**
     * 查询-ETF-指数
     *
     * @param stockInfo
     * @param today
     * @param zhiShu                 指数
     * @param days                   数量
     * @param klt                    K线周期类型
     * @param dbFieldLastNetMin
     * @param dbFieldLastNetMax
     * @param dbFieldLastNetMinClose
     * @param dbFieldLastNetMaxClose
     */
    public static RankStockCommpanyDb kline(RankStockCommpanyDb stockInfo, String today, String zhiShu, int days, String klt, String dbFieldLastNetMin, String dbFieldLastNetMax, String dbFieldLastNetMinClose, String dbFieldLastNetMaxClose) {
        StringBuffer url = new StringBuffer();
        url.append("http://96.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery331093188916841208381602168987937");
        if (zhiShu.startsWith("5") || zhiShu.startsWith("6")) {
            url.append("&secid=" + "1." + zhiShu);
        } else {
            url.append("&secid=" + "0." + zhiShu);
        }

        url.append("&ut=fa5fd1943c7b386f172d6893dbfba10b");
        url.append("&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5%2Cf6");
        url.append("&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58%2Cf59%2Cf60%2Cf61");
        url.append("&klt=" + klt);
        url.append("&fqt=1");
        url.append("&end=20500101");
        url.append("&lmt=" + days);
        url.append("&_=1602168987942");

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("&StartDate=").append(startDate);

//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
        String rs = "";
        try {
            rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
        } catch (Exception e) {
            System.out.println("/** http重试 **/");
            rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
        }

        /**
         * 如果返回异常，n次重试
         */
        for (int i = 0; i < 10; i++) {
            if (StringUtils.isBlank(rs)) {
                rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
            } else {
                break;
            }
        }

        String rsJson = rs.substring(rs.indexOf("{"));
        rsJson = rsJson.replace(");", "");
//        System.out.println("szKline:" + rsJson);

        List<String> klineList = new ArrayList<String>();
        JSONObject szzzMonthJson = JSON.parseObject(rsJson);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
//        String name = szzzMonthDataJson.getString("name");
//        System.out.println("指数名称："+name);
        if (szzzMonthDataJson == null || !szzzMonthDataJson.containsKey("klines")) {
//            System.out.println("klines未查询到："+zhiShu);
            return null;
        }
        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        if (klines != null) {
            for (Object kline : klines) {
                String klineStr = (String) kline;
                klineList.add(klineStr);
            }
        }

        Map<String, Double> netRs = handlerMaxJz(klineList);
        Double minJz = netRs.get(Content.keyRsMin);
        Double maxJz = netRs.get(Content.keyRsMax);
        Double netCloseMin = netRs.get(Content.keyRsNetCloseMin);
        Double netCloseMax = netRs.get(Content.keyRsNetCloseMax);

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE `rank_st_biz_com` ");
        sb.append("SET ");
        sb.append(" `" + dbFieldLastNetMin + "`=" + minJz + ", ");
        sb.append(" `" + dbFieldLastNetMinClose + "`=" + netCloseMin + ", ");
        sb.append(" `" + dbFieldLastNetMax + "`=" + maxJz + ", ");
        sb.append(" `" + dbFieldLastNetMaxClose + "`=" + netCloseMax + " ");
//        sb.append(" WHERE `FD_CODE`='" + zhiShu + "'" + " AND TYPE = '证券买入'" + ";");
        sb.append(" WHERE `f12`='" + zhiShu + "'");
        sb.append(" AND `date`='" + today + "'");
        sb.append(";");
        sb.append("/**" + stockInfo.getF14() + "**/");

        System.out.println(sb);

        stockInfo.setDate(today);
        if (days == 1) {
            stockInfo.setNET_MIN_1(minJz);
            stockInfo.setNET_MIN_CLOS_1(netCloseMin);
            stockInfo.setNET_MAX_1(maxJz);
            stockInfo.setNET_MAX_CLOS_1(netCloseMax);
        }
        if (days == 7) {
            stockInfo.setNET_MIN_7(minJz);
            stockInfo.setNET_MIN_CLOS_7(netCloseMin);
            stockInfo.setNET_MAX_7(maxJz);
            stockInfo.setNET_MAX_CLOS_7(netCloseMax);
        }
        if (days == 14) {
            stockInfo.setNET_MIN_14(minJz);
            stockInfo.setNET_MIN_CLOS_14(netCloseMin);
            stockInfo.setNET_MAX_14(maxJz);
            stockInfo.setNET_MAX_CLOS_14(netCloseMax);
        }
        if (days == 30) {
            stockInfo.setNET_MIN_30(minJz);
            stockInfo.setNET_MIN_CLOS_30(netCloseMin);
            stockInfo.setNET_MAX_30(maxJz);
            stockInfo.setNET_MAX_CLOS_30(netCloseMax);
        }
        if (days == 60) {
            stockInfo.setNET_MIN_60(minJz);
            stockInfo.setNET_MIN_CLOS_60(netCloseMin);
            stockInfo.setNET_MAX_60(maxJz);
            stockInfo.setNET_MAX_CLOS_60(netCloseMax);
        }
        if (days == 90) {
            stockInfo.setNET_MIN_90(minJz);
            stockInfo.setNET_MIN_CLOS_90(netCloseMin);
            stockInfo.setNET_MAX_90(maxJz);
            stockInfo.setNET_MAX_CLOS_90(netCloseMax);
        }
        if (days == 180) {
            stockInfo.setNET_MIN_180(minJz);
            stockInfo.setNET_MIN_CLOS_180(netCloseMin);
            stockInfo.setNET_MAX_180(maxJz);
            stockInfo.setNET_MAX_CLOS_180(netCloseMax);
        }
        if (days == 365) {
            stockInfo.setNET_MIN_360(minJz);
            stockInfo.setNET_MIN_CLOS_360(netCloseMin);
            stockInfo.setNET_MAX_360(maxJz);
            stockInfo.setNET_MAX_CLOS_360(netCloseMax);
        }
        return stockInfo;
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
     * @param strHead
     * @param netMap
     * @return
     */
    private static String handlerAvgLine(String strHead, Map<String, BigDecimal> netMap) {
        StringBuffer sb = new StringBuffer();

        BigDecimal curPrice = netMap.get(keyRsNetClose);
        BigDecimal minPrice = netMap.get(keyRsMin);
        BigDecimal maxPrice = netMap.get(keyRsMax);
        if (curPrice != null && minPrice != null && maxPrice != null) {
            BigDecimal curPriceArea = curPrice.subtract(minPrice).divide(maxPrice.subtract(minPrice), 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
//            sb.append(strHead).append("区间：").append("\t").append(curPriceArea).append("%").append(",");
            sb.append(strHead).append(curPriceArea).append("%").append(",");
        }
//        sb.append("\t").append(strHead).append("：").append("\t").append(netMap.get(keyRsNetCloseAvg));
//        sb.append("\t").append(",最低：").append("\t").append(minPrice);
//        sb.append("\t").append(",最高：").append("\t").append(maxPrice);
//        sb.append("\t").append(",当前价：").append(curPrice);

        return sb.toString();
    }

}
