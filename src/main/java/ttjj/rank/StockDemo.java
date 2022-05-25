package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.RankStockCommpanyDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.*;
import ttjj.service.*;
import utils.Content;
import utils.ContentUrl;
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
        for (int i = 0; i < 1; i++) {
            String date = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -i);
//            String date = "2022-05-05";
            boolean isReport = false;//是否查询业绩报表
            int startNum = 0;//开始位置，默认0
            Map<String, Boolean> maUpdateMap = new HashMap<>();
            setMaMapType(MA_TYPE_DAY, maUpdateMap);
            setMaMapType(MA_TYPE_MINUTE15, maUpdateMap);

            deleteTodayStCom();//删除数据-今日
            addTodayStCom(date, startNum);//  添加或更新股票-根据日期
            updateConception(date, startNum);//更新题材概念
//            updateTodayStCom(date, startNum);//更新股票
            updateNetToday(date, startNum, maUpdateMap, isReport, NUM_YI_50);//  更新净值

//            setMaMapType(MA_TYPE_MINUTE5, maUpdateMap);
//            setMaMapType(MA_TYPE_MINUTE30, maUpdateMap);
//            setMaMapType(MA_TYPE_MINUTE60, maUpdateMap);
//            setMaMapType(MA_TYPE_MINUTE120, maUpdateMap);
//            setMaMapType(MA_TYPE_MINUTE250, maUpdateMap);
//            setMaMapType(MA_TYPE_WEEK, maUpdateMap);
//            setMaMapType(MA_TYPE_MONTH, maUpdateMap);

//            updateFundFlow(date, startNum);//更新当日资金流信息

//            //查询业绩报表
//            String begDate = "20210701";
//            String endDate = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//            List<RankBizDataDiff> bkList = StockService.listBiz(NUM_MAX_999);//查询主题-排名by时间类型、显示个数
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
     * 更新股票：涨幅等
     *
     * @param date
     * @param startNum
     */
    private static void updateTodayStCom(String date, int startNum) {
        List<RankBizDataDiff> bkList = StockService.listBiz(NUM_MAX_99);//查询主题排名by时间类型、显示个数
        List<RankBizDataDiff> bkListMust = new ArrayList<>();//查询主题排名by时间类型、显示个数
        int bizCountLimit = NUM_MAX_999;
        int bizCountTemp = 0;
        for (RankBizDataDiff biz : bkList) {
            bizCountTemp++;
            if (bizCountTemp < startNum) {
                System.out.println("已完成:" + biz.getF14() + "," + biz.getF12());
                continue;//已完成
            }
            bkListMust.add(biz);
            if (bizCountTemp > bizCountLimit) {
                break;//限定个数中断
            }
        }
        int stBizCountTemp = startNum;
        for (RankBizDataDiff bk : bkListMust) {
            String banKuaiCode = bk.getF12();
            String banKuaiName = bk.getF14();
            stBizCountTemp++;
            List<RankStockCommpanyDb> stockList = BizService.listRankStockByBiz(NUM_MAX_999, banKuaiCode);
//            System.out.println();
            System.out.println("-------------------------当前stBizCountTemp：" + stBizCountTemp + "---" + banKuaiName + "---[" + bk.getF3() + "]---" + stockList.size());
//            System.out.println();

            for (RankStockCommpanyDb stockInfo : stockList) {
                if (stockInfo.getF3() == null) {
//                    System.out.println("数据异常:" + JSON.toJSONString(stockInfo));
                    continue;
                }
                stockInfo.setDate(date);

                int rsUpdate = RankStockCommpanyDao.updateByCode(stockInfo);

//                System.out.println("rsUpdate:" + rsUpdate);
            }
        }


    }

    /**
     * 设值-均线类型
     *
     * @param maTypeDay
     * @param maUpdateMap
     */
    public static void setMaMapType(String maTypeDay, Map<String, Boolean> maUpdateMap) {
        if (maTypeDay.equals(MA_TYPE_DAY)) {
            maUpdateMap.put(MA_UPDATE_FLAG_DAY_1_5, true);
            maUpdateMap.put(MA_UPDATE_FLAG_DAY_1_10, true);
            maUpdateMap.put(MA_UPDATE_FLAG_DAY_1_20, true);
            maUpdateMap.put(MA_UPDATE_FLAG_DAY_1_30, true);
            maUpdateMap.put(MA_UPDATE_FLAG_DAY_1_60, true);
//            maUpdateMap.put(MA_UPDATE_FLAG_DAY_1_120, true);
//            maUpdateMap.put(MA_UPDATE_FLAG_DAY_1_250, true);
        }

        if (maTypeDay.equals(MA_TYPE_MINUTE1)) {
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_1_5, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_1_10, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_1_20, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_1_30, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_1_60, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_1_120, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_1_250, true);
        }
        if (maTypeDay.equals(MA_TYPE_MINUTE5)) {
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_5_5, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_5_10, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_5_20, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_5_30, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_5_60, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_5_120, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_5_250, true);
        }
        if (maTypeDay.equals(MA_TYPE_MINUTE15)) {
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_15_5, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_15_10, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_15_20, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_15_30, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_15_60, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_15_120, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_15_250, true);
        }
        if (maTypeDay.equals(MA_TYPE_MINUTE30)) {
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_30_5, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_30_10, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_30_20, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_30_30, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_30_60, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_30_120, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_30_250, true);
        }

        if (maTypeDay.equals(MA_TYPE_MINUTE60)) {
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_60_5, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_60_10, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_60_20, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_60_30, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_60_60, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_60_120, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_60_250, true);
        }
        if (maTypeDay.equals(MA_TYPE_MINUTE120)) {
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_120_5, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_120_10, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_120_20, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_120_30, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_120_60, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_120_120, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MINUTE_120_250, true);
        }
        if (maTypeDay.equals(MA_TYPE_WEEK)) {
            maUpdateMap.put(MA_UPDATE_FLAG_WEEK_1_5, true);
            maUpdateMap.put(MA_UPDATE_FLAG_WEEK_1_10, true);
            maUpdateMap.put(MA_UPDATE_FLAG_WEEK_1_20, true);
            maUpdateMap.put(MA_UPDATE_FLAG_WEEK_1_30, true);
            maUpdateMap.put(MA_UPDATE_FLAG_WEEK_1_60, true);
            maUpdateMap.put(MA_UPDATE_FLAG_WEEK_1_120, true);
            maUpdateMap.put(MA_UPDATE_FLAG_WEEK_1_250, true);
        }
        if (maTypeDay.equals(MA_TYPE_MONTH)) {
            maUpdateMap.put(MA_UPDATE_FLAG_MONTH_1_5, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MONTH_1_10, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MONTH_1_20, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MONTH_1_30, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MONTH_1_60, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MONTH_1_120, true);
            maUpdateMap.put(MA_UPDATE_FLAG_MONTH_1_250, true);
        }
    }

    /**
     * 删除数据-今日
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
        List<RankBizDataDiff> bkList = StockService.listBiz(NUM_MAX_99);//查询主题排名by时间类型、显示个数
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
            stockList.addAll(BizService.listRankStockByBiz(NUM_MAX_999, banKuaiCode));
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
            if (entity.getF139() != DB_RANK_BIZ_F139_BK_MAIN) {
//                    System.out.println("均线价格暂不更新（非主板）！" + JSON.toJSONString(entity));
                continue;
            }
            //  市值限定,100亿以下不更新
            if (entity.getF20() != null && entity.getF20().compareTo(new BigDecimal("10000000000")) < 0) {
//                    System.out.println("均线价格暂不更新（100亿以下）！" + JSON.toJSONString(entity));
                continue;
            }

            if (entity.getF139() == DB_RANK_BIZ_F139_BK_MAIN) {
                String rsFundFlow = FundFlowService.httpFundFlowRs(stCode);

                RankStockCommpanyDb entityDb = new RankStockCommpanyDb();
                entityDb.setF12(stCode);
                entityDb.setDate(date);

//                entityDb.setFundFlow(rsFundFlow);
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
            List<RankStockCommpanyDb> stockList = BizService.listRankStockByBiz(NUM_MAX_999, banKuaiCode);
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
                if (entity.getF139() != DB_RANK_BIZ_F139_BK_MAIN) {
//                    System.out.println("均线价格暂不更新（非主板）！" + JSON.toJSONString(entity));
                    continue;
                }
                //  市值限定,100亿以下不更新
                if (entity.getF20() != null && entity.getF20().compareTo(new BigDecimal("10000000000")) < 0) {
//                    System.out.println("均线价格暂不更新（100亿以下）！" + JSON.toJSONString(entity));
                    continue;
                }
                if (entity.getF139() == DB_RANK_BIZ_F139_BK_MAIN) {
//                    System.out.println("股票---------------------" + entity.getF14() + ":"+ entity.getF3() + JSON.toJSONString(entity));
                    List<Report> rsReport = ReportService.listHttpReportByStCode(stCode);
                    for (Report report : rsReport) {
                        //是否有2021三季报
                        if (report.getQDATE().equals(quarter)) {
                            BigDecimal tbzzYyzsr = report.getYSTZ();
                            BigDecimal tbzzJlr = report.getJLRTBZCL();
                            BigDecimal hbzzYyzsr = report.getDJDYSHZ();
                            BigDecimal hbzzJlr = report.getDJDJLHZ();
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
        List<RankBizDataDiff> bizList = StockService.listBiz(NUM_MAX_99);//查询主题排名by时间类型、显示个数
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
            List<RankStockCommpanyDb> rankBizDataDiffListBiz = BizService.listRankStockByBiz(500, bizCode);

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
                rankStockCommpanyDb.setF3(kline.getZhangDieFu());
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
        List<RankBizDataDiff> bkList = StockService.listBiz(NUM_MAX_99);//查询主题排名by时间类型、显示个数
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
            List<RankStockCommpanyDb> stockList = BizService.listRankStockByBiz(NUM_MAX_999, banKuaiCode);
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
    private static void updateNetToday(String date, int startNum, Map<String, Boolean> maUpdateMap, boolean isReport, BigDecimal limitMarketValue) {
        List<RankBizDataDiff> bkList = StockService.listBiz(NUM_MAX_999);//查询主题排名by时间类型、显示个数
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
            List<RankStockCommpanyDb> stockList = BizService.listRankStockByBiz(NUM_MAX_999, banKuaiCode);//查询股票列表-根据板块
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
                entity.setDate(date);
                String stCode = entity.getF12();
                //检查股票:状态、是否主板股票、市值限定
                if (!StockService.checkIsMainStockLimit(entity, limitMarketValue)) {
                    continue;
                }

                if (entity.getF139() == DB_RANK_BIZ_F139_BK_MAIN) {
                    // 周期价格:均线、最低、最高、收盘最低、收盘最高
                    String zqdm = entity.getF12();

                    StringBuffer maSb = new StringBuffer();
                    handlerNetMa(entity, maUpdateMap, date, maSb, new StockAdrCountVo());//处理均线净值

//                    System.out.println();
                    int rs = RankStockCommpanyDao.updateByCode(entity);
                    BigDecimal flowIn = entity.getF62() != null ? entity.getF62().divide(new BigDecimal("100000000"), 2, BigDecimal.ROUND_HALF_UP) : entity.getF62();
                    BigDecimal marketValue = null;
                    if (entity.getF20() != null) {
                        marketValue = entity.getF20().divide(new BigDecimal("100000000"), 2, BigDecimal.ROUND_HALF_UP);
                    } else {
                        System.out.println("市值异常：" + entity.getF12() + ":" + entity.getF14() + "：" + entity.getF20());
                    }
                    BigDecimal flowRate = new BigDecimal("0");
                    if (flowIn != null && marketValue != null && marketValue.compareTo(new BigDecimal("0")) != 0) {
                        flowRate = flowIn.divide(marketValue, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
                    }

                    System.out.println("rs:" + rs
                                    + "\t" + entity.getF12() + ":" + entity.getF14() + "\t" + ":" + entity.getF3() + ""
                                    + "\t" + "价格区间:" + maSb.toString()
                                    + "\t" + "主力-净流入:" + flowIn
                                    + "\t" + "流入市值比：:" + flowRate
                                    + "\t" + "市值：:" + marketValue
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
                                BigDecimal hbzzYyzsr = report.getDJDYSHZ();
                                BigDecimal hbzzJlr = report.getDJDJLHZ();
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
     * 处理均线净值
     *
     * @param entity
     * @param maUpdateMap
     * @param date
     * @param maSb
     * @param stockAdrCountVo
     */
    public static void handlerNetMa(RankStockCommpanyDb entity, Map<String, Boolean> maUpdateMap, String date, StringBuffer maSb, StockAdrCountVo stockAdrCountVo) {
        String zqdm = entity.getF12();
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_1_5) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_1_5)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, KLT_1, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_1_5(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_1_10) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_1_10)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, KLT_1, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_1_10(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_1_20) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_1_20)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, KLT_1, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_1_20(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_1_30) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_1_30)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, KLT_1, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_1_30(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_1_60) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_1_60)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, KLT_1, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_1_60(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_1_120) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_1_120)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, KLT_1, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_1_120(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_1_250) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_1_250)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, KLT_1, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_1_250(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_5_5) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_5_5)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, KLT_5, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_5_5(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_5_10) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_5_10)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, KLT_5, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_5_10(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_5_20) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_5_20)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, KLT_5, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_5_20(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_5_30) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_5_30)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, KLT_5, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_5_30(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_5_60) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_5_60)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, KLT_5, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_5_60(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_5_120) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_5_120)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, KLT_5, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_5_120(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_5_250) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_5_250)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, KLT_5, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_5_250(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_15_5) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_15_5)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, KLT_15, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_15_5(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_15_10) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_15_10)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, KLT_15, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_15_10(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_15_20) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_15_20)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, KLT_15, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_15_20(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_15_30) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_15_30)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, KLT_15, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_15_30(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_15_60) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_15_60)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, KLT_15, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_15_60(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_15_120) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_15_120)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, KLT_15, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_15_120(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_15_250) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_15_250)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, KLT_15, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_15_250(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_30_5) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_30_5)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, KLT_30, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_30_5(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_30_10) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_30_10)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, KLT_30, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_30_10(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_30_20) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_30_20)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, KLT_30, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_30_20(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_30_30) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_30_30)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, KLT_30, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_30_30(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_30_60) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_30_60)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, KLT_30, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_30_60(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_30_120) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_30_120)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, KLT_30, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_30_120(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_30_250) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_30_250)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, KLT_30, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_30_250(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_60_5) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_60_5)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, KLT_60, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_60_5(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_60_10) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_60_10)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, KLT_60, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_60_10(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_60_20) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_60_20)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, KLT_60, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_60_20(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_60_30) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_60_30)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, KLT_60, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_60_30(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_60_60) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_60_60)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, KLT_60, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_60_60(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_60_120) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_60_120)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, KLT_60, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_60_120(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_60_250) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_60_250)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, KLT_60, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_60_250(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_120_5) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_120_5)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, KLT_120, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_120_5(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_120_10) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_120_10)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, KLT_120, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_120_10(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_120_20) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_120_20)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, KLT_120, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_120_20(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_120_30) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_120_30)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, KLT_120, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_120_30(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_120_60) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_120_60)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, KLT_120, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_120_60(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_120_120) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_120_120)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, KLT_120, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_120_120(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MINUTE_120_250) && maUpdateMap.get(MA_UPDATE_FLAG_MINUTE_120_250)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, KLT_120, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MI_120_250(netMap.get(Content.keyRsNetCloseAvg));
        }

        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_DAY_1_5) && maUpdateMap.get(MA_UPDATE_FLAG_DAY_1_5)) {
            int ma = Content.MA_5;
            String klt = KLT_101;
            Map<String, BigDecimal> netMap5 = KlineService.findNetMinMaxAvg(zqdm, ma, klt, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_5(netMap5.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_7(netMap5.get(Content.keyRsMin).doubleValue());
            entity.setNET_MAX_7(netMap5.get(Content.keyRsMax).doubleValue());
            entity.setNET_MIN_CLOS_7(netMap5.get(Content.keyRsNetCloseMin).doubleValue());
            entity.setNET_MAX_CLOS_7(netMap5.get(Content.keyRsNetCloseMax).doubleValue());
            if (stockAdrCountVo != null && stockAdrCountVo.getF12() != null) {
                stockAdrCountVo.setNET_AREA_DAY_5(StockService.handlerMaArea(KlineService.findNetMinMaxAvg(zqdm, ma, klt, false, "", date, KLINE_TYPE_STOCK)));
            }
            maSb.append(handlerAvgLine(" 5日:", netMap5));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_DAY_1_10) && maUpdateMap.get(MA_UPDATE_FLAG_DAY_1_10)) {
            int ma = MA_10;
            String klt = KLT_101;
            Map<String, BigDecimal> netMap10 = KlineService.findNetMinMaxAvg(zqdm, ma, klt, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_10(netMap10.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_14(netMap10.get(Content.keyRsMin).doubleValue());
            entity.setNET_MAX_14(netMap10.get(Content.keyRsMax).doubleValue());
            entity.setNET_MIN_CLOS_14(netMap10.get(Content.keyRsNetCloseMin).doubleValue());
            entity.setNET_MAX_CLOS_14(netMap10.get(Content.keyRsNetCloseMax).doubleValue());
            if (stockAdrCountVo != null && stockAdrCountVo.getF12() != null) {
                stockAdrCountVo.setNET_AREA_DAY_10(StockService.handlerMaArea(KlineService.findNetMinMaxAvg(zqdm, ma, klt, false, "", date, KLINE_TYPE_STOCK)));
            }
            maSb.append(handlerAvgLine("10日:", KlineService.findNetMinMaxAvg(zqdm, MA_10, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_DAY_1_20) && maUpdateMap.get(MA_UPDATE_FLAG_DAY_1_20)) {
            int ma = MA_20;
            String klt = KLT_101;
            Map<String, BigDecimal> netMap20 = KlineService.findNetMinMaxAvg(zqdm, ma, klt, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_20(netMap20.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_30(netMap20.get(Content.keyRsMin).doubleValue());
            entity.setNET_MAX_30(netMap20.get(Content.keyRsMax).doubleValue());
            entity.setNET_MIN_CLOS_30(netMap20.get(Content.keyRsNetCloseMin).doubleValue());
            entity.setNET_MAX_CLOS_30(netMap20.get(Content.keyRsNetCloseMax).doubleValue());
            if (stockAdrCountVo != null && stockAdrCountVo.getF12() != null) {
                stockAdrCountVo.setNET_AREA_DAY_20(StockService.handlerMaArea(KlineService.findNetMinMaxAvg(zqdm, ma, klt, false, "", date, KLINE_TYPE_STOCK)));
            }
            maSb.append(handlerAvgLine("20日:", KlineService.findNetMinMaxAvg(zqdm, MA_20, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_DAY_1_30) && maUpdateMap.get(MA_UPDATE_FLAG_DAY_1_30)) {
            int ma = MA_30;
            String klt = KLT_101;
            Map<String, BigDecimal> netMap30 = KlineService.findNetMinMaxAvg(zqdm, ma, klt, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_30(netMap30.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_60(netMap30.get(Content.keyRsMin).doubleValue());
            entity.setNET_MAX_60(netMap30.get(Content.keyRsMax).doubleValue());
            entity.setNET_MIN_CLOS_60(netMap30.get(Content.keyRsNetCloseMin).doubleValue());
            entity.setNET_MAX_CLOS_60(netMap30.get(Content.keyRsNetCloseMax).doubleValue());
            if (stockAdrCountVo != null && stockAdrCountVo.getF12() != null) {
                stockAdrCountVo.setNET_AREA_DAY_40(StockService.handlerMaArea(KlineService.findNetMinMaxAvg(zqdm, ma, klt, false, "", date, KLINE_TYPE_STOCK)));
            }
            maSb.append(handlerAvgLine("30日:", KlineService.findNetMinMaxAvg(zqdm, MA_30, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_DAY_1_60) && maUpdateMap.get(MA_UPDATE_FLAG_DAY_1_60)) {
            int ma = MA_60;
            String klt = KLT_101;
            Map<String, BigDecimal> netMap60 = KlineService.findNetMinMaxAvg(zqdm, ma, klt, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_60(netMap60.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_90(netMap60.get(Content.keyRsMin).doubleValue());
            entity.setNET_MAX_90(netMap60.get(Content.keyRsMax).doubleValue());
            entity.setNET_MIN_CLOS_90(netMap60.get(Content.keyRsNetCloseMin).doubleValue());
            entity.setNET_MAX_CLOS_90(netMap60.get(Content.keyRsNetCloseMax).doubleValue());
            if (stockAdrCountVo != null && stockAdrCountVo.getF12() != null) {
                stockAdrCountVo.setNET_AREA_DAY_60(StockService.handlerMaArea(KlineService.findNetMinMaxAvg(zqdm, ma, klt, false, "", date, KLINE_TYPE_STOCK)));
            }
            maSb.append(handlerAvgLine("60日:", KlineService.findNetMinMaxAvg(zqdm, MA_60, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_DAY_1_120) && maUpdateMap.get(MA_UPDATE_FLAG_DAY_1_120)) {
            int ma = MA_120;
            String klt = KLT_101;
            Map<String, BigDecimal> netMap120 = KlineService.findNetMinMaxAvg(zqdm, ma, klt, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_120(netMap120.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_180(netMap120.get(Content.keyRsMin).doubleValue());
            entity.setNET_MAX_180(netMap120.get(Content.keyRsMax).doubleValue());
            entity.setNET_MIN_CLOS_180(netMap120.get(Content.keyRsNetCloseMin).doubleValue());
            entity.setNET_MAX_CLOS_180(netMap120.get(Content.keyRsNetCloseMax).doubleValue());
            if (stockAdrCountVo != null && stockAdrCountVo.getF12() != null) {
                stockAdrCountVo.setNET_AREA_DAY_120(StockService.handlerMaArea(KlineService.findNetMinMaxAvg(zqdm, ma, klt, false, "", date, KLINE_TYPE_STOCK)));
            }
            maSb.append(handlerAvgLine("120日:", KlineService.findNetMinMaxAvg(zqdm, MA_120, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_DAY_1_250) && maUpdateMap.get(MA_UPDATE_FLAG_DAY_1_250)) {
            int ma = MA_250;
            String klt = KLT_101;
            Map<String, BigDecimal> netMap250 = KlineService.findNetMinMaxAvg(zqdm, ma, klt, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_250(netMap250.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_360(netMap250.get(Content.keyRsMin).doubleValue());
            entity.setNET_MAX_360(netMap250.get(Content.keyRsMax).doubleValue());
            entity.setNET_MIN_CLOS_360(netMap250.get(Content.keyRsNetCloseMin).doubleValue());
            entity.setNET_MAX_CLOS_360(netMap250.get(Content.keyRsNetCloseMax).doubleValue());
            if (stockAdrCountVo != null && stockAdrCountVo.getF12() != null) {
                stockAdrCountVo.setNET_AREA_DAY_250(StockService.handlerMaArea(KlineService.findNetMinMaxAvg(zqdm, ma, klt, false, "", date, KLINE_TYPE_STOCK)));
            }
            maSb.append(handlerAvgLine("250日:", KlineService.findNetMinMaxAvg(zqdm, MA_250, KLT_101, false, "", date, KLINE_TYPE_STOCK)));
        }

        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_WEEK_1_5) && maUpdateMap.get(MA_UPDATE_FLAG_WEEK_1_5)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, KLT_102, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_WE_1_5(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_WEEK_1_10) && maUpdateMap.get(MA_UPDATE_FLAG_WEEK_1_10)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, KLT_102, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_WE_1_10(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_WEEK_1_20) && maUpdateMap.get(MA_UPDATE_FLAG_WEEK_1_20)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, KLT_102, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_WE_1_20(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_WEEK_1_30) && maUpdateMap.get(MA_UPDATE_FLAG_WEEK_1_30)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, KLT_102, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_WE_1_30(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_WEEK_1_60) && maUpdateMap.get(MA_UPDATE_FLAG_WEEK_1_60)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, KLT_102, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_WE_1_60(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_WEEK_1_120) && maUpdateMap.get(MA_UPDATE_FLAG_WEEK_1_120)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, KLT_102, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_WE_1_120(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_WEEK_1_250) && maUpdateMap.get(MA_UPDATE_FLAG_WEEK_1_250)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, KLT_102, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_WE_1_250(netMap.get(Content.keyRsNetCloseAvg));
        }

        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MONTH_1_5) && maUpdateMap.get(MA_UPDATE_FLAG_MONTH_1_5)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, KLT_103, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MO_1_5(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MONTH_1_10) && maUpdateMap.get(MA_UPDATE_FLAG_MONTH_1_10)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, KLT_103, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MO_1_10(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MONTH_1_20) && maUpdateMap.get(MA_UPDATE_FLAG_MONTH_1_20)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, KLT_103, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MO_1_20(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MONTH_1_30) && maUpdateMap.get(MA_UPDATE_FLAG_MONTH_1_30)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, KLT_103, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MO_1_30(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MONTH_1_60) && maUpdateMap.get(MA_UPDATE_FLAG_MONTH_1_60)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, KLT_103, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MO_1_60(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MONTH_1_120) && maUpdateMap.get(MA_UPDATE_FLAG_MONTH_1_120)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, KLT_103, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MO_1_120(netMap.get(Content.keyRsNetCloseAvg));
        }
        if (maUpdateMap.containsKey(MA_UPDATE_FLAG_MONTH_1_250) && maUpdateMap.get(MA_UPDATE_FLAG_MONTH_1_250)) {
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, KLT_103, false, "", date, KLINE_TYPE_STOCK);
            entity.setNET_MA_MO_1_250(netMap.get(Content.keyRsNetCloseAvg));
        }
    }


    /**
     * 更新业务板块
     *
     * @param date
     * @param bkStartNum
     */
    private static void updateBkList(String date, int bkStartNum) {
        List<RankBizDataDiff> bkList = StockService.listBiz(NUM_MAX_999);//查询主题排名by时间类型、显示个数
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
            List<RankStockCommpanyDb> stockList = BizService.listRankStockByBiz(NUM_MAX_999, banKuaiCode);//查询股票列表-根据板块
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
     * 更新题材概念
     *
     * @param date
     * @param startNum
     */
    private static void updateConception(String date, int startNum) {
        List<RankBizDataDiff> bkList = StockService.listBiz(NUM_MAX_99);//查询主题排名by时间类型、显示个数
        List<RankBizDataDiff> bkListMust = new ArrayList<>();//查询主题排名by时间类型、显示个数
        int bizCountLimit = NUM_MAX_999;
        int bizCountTemp = 0;
        for (RankBizDataDiff biz : bkList) {
            bizCountTemp++;
            if (bizCountTemp < startNum) {
                System.out.println("已完成:" + biz.getF14() + "," + biz.getF12());
                continue;//已完成
            }
            bkListMust.add(biz);
            if (bizCountTemp > bizCountLimit) {
                break;//限定个数中断
            }
        }
        int stBizCountTemp = startNum;
        for (RankBizDataDiff bk : bkListMust) {
            String banKuaiCode = bk.getF12();
            String banKuaiName = bk.getF14();
            stBizCountTemp++;
            List<RankStockCommpanyDb> stockList = BizService.listRankStockByBiz(NUM_MAX_999, banKuaiCode);
//            System.out.println();
            System.out.println("-------------------------当前stBizCountTemp：" + stBizCountTemp + "---" + banKuaiName + "---[" + bk.getF3() + "]---" + stockList.size());
//            System.out.println();

            for (RankStockCommpanyDb stockInfo : stockList) {
                String stCode = stockInfo.getF12();
                StringBuffer url = new StringBuffer();
                url.append(ContentUrl.URL_CORE_CONCEPTION);

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

                //{"ssbk":[{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"474","BOARD_NAME":"保险","IS_PRECISE":"0","BOARD_RANK":1},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"153","BOARD_NAME":"广东板块","IS_PRECISE":"0","BOARD_RANK":2},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"879","BOARD_NAME":"标准普尔","IS_PRECISE":"0","BOARD_RANK":3},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"867","BOARD_NAME":"富时罗素","IS_PRECISE":"0","BOARD_RANK":4},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"821","BOARD_NAME":"MSCI中国","IS_PRECISE":"0","BOARD_RANK":5},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"718","BOARD_NAME":"证金持股","IS_PRECISE":"0","BOARD_RANK":6},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"707","BOARD_NAME":"沪股通","IS_PRECISE":"0","BOARD_RANK":7},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"612","BOARD_NAME":"上证180_","IS_PRECISE":"0","BOARD_RANK":8},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"611","BOARD_NAME":"","IS_PRECISE":"0","BOARD_RANK":9},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"610","BOARD_NAME":"央视50_","IS_PRECISE":"0","BOARD_RANK":10},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"596","BOARD_NAME":"融资融券","IS_PRECISE":"0","BOARD_RANK":11},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"505","BOARD_NAME":"中字头","IS_PRECISE":"0","BOARD_RANK":12},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"500","BOARD_NAME":"HS300_","IS_PRECISE":"0","BOARD_RANK":13},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"499","BOARD_NAME":"AH股","IS_PRECISE":"0","BOARD_RANK":14},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"837","BOARD_NAME":"互联医疗","IS_PRECISE":"1","BOARD_RANK":15},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"835","BOARD_NAME":"独角兽","IS_PRECISE":"1","BOARD_RANK":16},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"830","BOARD_NAME":"区块链","IS_PRECISE":"1","BOARD_RANK":17},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"697","BOARD_NAME":"IPO受益","IS_PRECISE":"0","BOARD_RANK":18},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"653","BOARD_NAME":"养老概念","IS_PRECISE":"1","BOARD_RANK":19},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"637","BOARD_NAME":"互联金融","IS_PRECISE":"1","BOARD_RANK":20},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"549","BOARD_NAME":"深圳特区","IS_PRECISE":"1","BOARD_RANK":21},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"525","BOARD_NAME":"参股银行","IS_PRECISE":"0","BOARD_RANK":22},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","BOARD_CODE":"514","BOARD_NAME":"参股券商","IS_PRECISE":"0","BOARD_RANK":23}],"hxtc":[{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","KEYWORD":"经营范围","MAINPOINT":2,"MAINPOINT_CONTENT":"投资保险企业;监督管理控股投资企业的各种国内、国际业务;开展保险资金运用业务;经批准开展国内、国际保险业务;经中国保险监督管理委员会及国家有关部门批准的其他业务。","KEY_CLASSIF":"经营范围","KEY_CLASSIF_CODE":"002","IS_POINT":"0"},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","KEYWORD":"多元金融业务","MAINPOINT":3,"MAINPOINT_CONTENT":"公司通过多渠道分销网络以统一的品牌,借助旗下平 安寿险、平安产险、平安养老险、平安健康险、平安银 行、平安信托、平安证券、平安资产管理及平安资产管 理(香港)等公司经营保险、银行、资产管理三大核心金 融业务,借助陆金所、平安付与万里通、平安好房、平 安健康互联网、平安金融科技等公司经营互联网金融业 务,向客户提供多种金融产品和服务。","KEY_CLASSIF":"主营业务","KEY_CLASSIF_CODE":"003","IS_POINT":"0"},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","KEYWORD":"核心金融","MAINPOINT":5,"MAINPOINT_CONTENT":"\"核心金融业务方面,寿险业务实现规模保费2,998.14亿元,代理人数量近87万,新业务价值持续提升 ;产险业务实现保费收入1,639.55亿元,综合成本率95.6%,业务品质保持优良 ;平安养老险年金等养老资产管理业务行业领先 ;保险资金净投资收益率稳步提升,总投资收 益率创近年新高 ;平安银行持续推动结构调整和经营模 式创新,逐步形成“专业化、集约化、综合金融、互联 网金融”的经营特色,平安银行“不一样”的品牌形象正 逐步深化 ;平安信托坚持优化业务结构,继续严控项目 风险,业务保持稳健增长 ;平安证券经营业绩创历史新 高,战略推进成效显著 ;平安资产管理的投资业绩表现 优秀,第三方资产管理业务快速发展\"。","KEY_CLASSIF":"核心竞争力","KEY_CLASSIF_CODE":"005","IS_POINT":"1"},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","KEYWORD":"互联网金融","MAINPOINT":6,"MAINPOINT_CONTENT":"继续贯彻“科技引领金融”理念, 一方面利用互联网升级综合金融模式,将线下的金融客 户迁徙到线上,扩大服务范围,提升服务效率和体验 ; 同时,围绕用户的“医、食、住、行、玩”需求,不断完 善线上平台,提供多种服务和产品,将金融嵌入线上生 活服务,逐步横向迁徙,实现“一个客户、一个账户、 多项服务、多个产品”,让平安成为客户的“财富管家、 健康顾问、生活助手”。截至2015年12月31日,平安互 联网用户规模约2.42亿,较年初增长75.9%,继续保持 高速增长。","KEY_CLASSIF":"核心竞争力","KEY_CLASSIF_CODE":"005","IS_POINT":"1"},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","KEYWORD":"互联网金融业务","MAINPOINT":13,"MAINPOINT_CONTENT":"截至2016年12月31日,陆金所平台累计注册用户数2,838万,较上年末增长55.0%,活跃投资用户数740万,较上年末增长103.9%,2016年新增投资用户数445万,同比增长33.3%。通过陆金所平台交易的资产规模保持高速增长,2016年零售端交易量15,351.63亿元,同比增长137.5%,期末零售端资产管理规模达4,383.79亿元。截至2016年底,平安好医生自建医学团队近1,000人,提供7X24小时在线咨询服务;外部签约医生6万余人,提供复诊随诊服务;可提供挂号服务的合作医院近2,300家;合作体检机构超过700家,覆盖全国150余座城市;提供B2C全国供药和13座一线城市1小时O2O送药服务,涵盖近3万种日常用药及保健品。平安好医生累计为1.3亿用户提供健康管理服务,月活跃用户数峰值2,625万,日咨询量峰值44万。","KEY_CLASSIF":"核心竞争力","KEY_CLASSIF_CODE":"005","IS_POINT":"1"},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","KEYWORD":"平安证券拟改制并赴港上市","MAINPOINT":14,"MAINPOINT_CONTENT":"2016年6月16日公告称,公司召开第十届董事会第七次会议,审议并通过了“关于平安证券境外上市方案”在内的十一项议案。该会议同意公司控股子公司平安证券有限责任公司整体改制为股份有限公司,改制后名称拟变更为“平安证券股份有限公司”,并同意平安证券完成改制后首次公开发行H股并在香港联合交易所有限公司主板上市。平安证券股份公司拟向境外投资者及合资格境内投资者募集并在香港联交所主板上市的境外上市外资股(H股),发行方式为香港公开发行及国际配售,每股面值为人民币1.00元,发行数量不超过发行后总股本的25%(超额配售权行使前),同时根据市场情况授予承销商不超过本次基础发行的H股股数15%的超额配售权。平安证券股份公司此次境外公开发行H股所募集资金在扣除发行费用后拟用于增加其资本金,补充其营运资金,推动各项证券相关业务发展。具体募集资金用途及投向计划以届时平安证券股份公司H股招股说明书的披露为准。","KEY_CLASSIF":"核心竞争力","KEY_CLASSIF_CODE":"005","IS_POINT":"1"},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","KEYWORD":"将回购不超10%股份","MAINPOINT":17,"MAINPOINT_CONTENT":"2018年10月29日公告,前十大股东榜中,证金公司持股由二季度末的4.9%,三季度减持至2.99%。公司同日公告,公司董事会通过了公司回购议案。公司将酌情及适时回购公司公开发行的境内、境外股份,回购总额不超过公司发行总股本的10%。回购资金包括自有资金及符合监管政策法规要求的资金。","KEY_CLASSIF":"股票回购","KEY_CLASSIF_CODE":"007002","IS_POINT":"1"},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","KEYWORD":"回购具体方案仍有待确定","MAINPOINT":18,"MAINPOINT_CONTENT":"2018年10月30日早间发布关于回购公司股份及相关授权方案的提示性公告。公告称,为积极响应国家政府和监管部门的政策导向,稳定资本市场,促进股东价值最大化,公司制定股份回购计划。目前该方案还需要公司股东大会审议,并将由股东大会授权相关机构和人员在不超过公司发行总股本的10%的授权额度内,制定回购具体方案,后续具体回购股份的价格、种类、批次、数量及执行时间仍有待确定及具有不确定性。公司将严格按照有关法律法规及上市规则的要求执行后续股份回购计划,及时履行信息披露义务。","KEY_CLASSIF":"股票回购","KEY_CLASSIF_CODE":"007002","IS_POINT":"1"},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","KEYWORD":"拟50亿-100亿元回购股份","MAINPOINT":20,"MAINPOINT_CONTENT":"2019年3月12日公告,公司拟使用不低于人民币50亿元且不超过人民币100亿元的自有资金,以不超过人民币101.24元/股的回购价格回购本公司A股股份。本次回购股份将全部用于公司员工持股计划,包括但不限于公司股东大会已审议通过的长期服务计划。本次回购期限为自本次回购方案经公司年度股东大会及类别股东大会审议通过之日起不超过12个月。","KEY_CLASSIF":"股票回购","KEY_CLASSIF_CODE":"007002","IS_POINT":"1"},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","KEYWORD":"耗资2.8亿元实施首次回购","MAINPOINT":21,"MAINPOINT_CONTENT":"2019年6月18日公告,公司于2019年6月18日以集中竞价交易方式实施了首次回购A股股份,首次回购共购得3,503,689股公司A股股份,占本公司总股本的比例为0.01917%,已支付的资金总额合计人民币281,825,094.66元(不含交易费用),最低成交价格为人民币79.85元/股,最高成交价格为人民币80.93元/股。","KEY_CLASSIF":"股票回购","KEY_CLASSIF_CODE":"007002","IS_POINT":"1"},{"SECUCODE":"601318.SH","SECURITY_CODE":"601318","SECURITY_NAME_ABBR":"中国平安","KEYWORD":"增设三名联席CEO","MAINPOINT":19,"MAINPOINT_CONTENT":"2018年12月14日公告,根据《公司章程》及公司执行委员会工作细则有关规定,借鉴全球公司治理最佳实践,公司在执行委员会现行执行官负责制基础上,增设三个联席首席执行官岗位,由李源祥先生、谢永林先生、陈心颖女士担任,并由李源祥先生分管个人客户综合金融业务、由谢永林先生分管公司客户综合金融业务、由陈心颖女士分管科技业务。在公司董事长/首席执行官的领导下,三位联席首席执行官分别对三大业务主线实施统一领导、专业分工。公司董事会认为,在公司执行委员会现行执行官负责制基础上设立联席首席执行官集体决策机制,使之成为公司一项重要的制度化组织体系,将有助于深入贯彻、持续践行集体决策、分工负责、矩阵式管理的经营决策机制,更高效地整合内部资源、提升协同效率、强化风险管控,健全人才培养及梯队建设体系。上述安排符合本公司适应以客户为导向战略转型、适应新业务模式发展管理、强化本公司风险管控和人才梯队培养的需要,是对本公司现有战略及业务模式和决策机制的延续与深化,有利于加快推动本公司科技转型、生态转型战略向更深、更广层次演进,提升本公司对经营、管理风险的驾驭能力,实现长期可持续健康发展。同日,公司2018年第二次临时股东大会审议及批准了《关于审议回购公司股份及相关授权的方案》。","KEY_CLASSIF":"其他项目","KEY_CLASSIF_CODE":"010","IS_POINT":"1"}]}
                HttpRsHxtc httpRsHxtc = JSON.parseObject(rs, HttpRsHxtc.class);
                if (httpRsHxtc == null) {
//            System.out.println("klines未查询到："+zhiShu);
                    return;
                }
                StringBuffer ssbk = new StringBuffer();//核心题材-所属板块
                List<HttpRsHxtcSsbk> ssbkList = httpRsHxtc.getSsbk();
                for (HttpRsHxtcSsbk httpRsHxtcSsbk : ssbkList) {
                    ssbk.append(httpRsHxtcSsbk.getBOARD_NAME()).append(" ");
                }

                StringBuffer sb = new StringBuffer();
                sb.append("UPDATE `rank_st_biz_com` ");
                sb.append("SET ");
                sb.append(" `conception`='" + ssbk.toString() + "' ");
                sb.append(" WHERE `f12`='" + stockInfo.getF12() + "'");
                sb.append(" AND `date`='" + date + "'");
                sb.append(";");
                sb.append("/**" + stockInfo.getF14() + "**/");


                //db-更新要点内容
                RankStockCommpanyDb entity = new RankStockCommpanyDb();
                entity.setF12(stockInfo.getF12());
                entity.setDate(date);
                entity.setConception(ssbk.toString());//所属板块
                int rsUpdate = RankStockCommpanyDao.updateByCode(entity);

                System.out.println(sb + " rsUpdate:" + rsUpdate);
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
        if (curPrice != null && minPrice != null && maxPrice != null && maxPrice.compareTo(new BigDecimal("0")) != 0) {
            BigDecimal curPriceArea = curPrice.subtract(minPrice).divide(maxPrice.subtract(minPrice), 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
//            sb.append(strHead).append("区间：").append("\t").append(curPriceArea).append("%").append(",");
//            sb.append(strHead).append(curPriceArea).append("%").append(",");
            String curPriceAreaStr = "";
            int priceSize = curPriceArea.toString().length();
            if (curPriceArea != null) {
                curPriceAreaStr = curPriceArea.toString();
            }
            if (priceSize <= 4) {
                curPriceAreaStr = curPriceArea.toString() + "  ";
            }
            if (priceSize > 4 && priceSize < 6) {
                curPriceAreaStr = curPriceArea.toString() + " ";
            }
            sb.append(strHead).append(curPriceAreaStr).append("\t");
        }
//        sb.append("\t").append(strHead).append("：").append("\t").append(netMap.get(keyRsNetCloseAvg));
//        sb.append("\t").append(",最低：").append("\t").append(minPrice);
//        sb.append("\t").append(",最高：").append("\t").append(maxPrice);
//        sb.append("\t").append(",当前价：").append(curPrice);
//        sb.append("\t").append(",当前：").append( DateUtil.getToday(DateUtil.YYYY_MM_DD_HH_MM_SS));

        return sb.toString();
    }

}
