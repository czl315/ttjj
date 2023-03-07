package ttjj.rank.stat.schedule;

import ttjj.dto.CondStockAdrCount;
import ttjj.dto.RankBizDataDiff;
import ttjj.rank.StockAdrCountControl;
import ttjj.service.StockService;
import utils.DateUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static utils.Content.*;

/**
 * 定时任务-etf
 */
public class StAdrJob {
    private static volatile int countThread = 0;//线程次数

    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
        updateStAdrSchedule(date);//定时任务
    }

    /**
     * 定时任务
     *
     * @param date 日期
     */
    private static void updateStAdrSchedule(String date) {

        //更新股票
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            String jobName = "更新股票涨幅统计";
            System.out.println();
            System.out.println("定时任务-" + jobName + "-beg:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));
            Long board = DB_RANK_BIZ_F139_BK_MAIN;//
            String spBizName = null;//特定业务：半导体 "半导体"
            int begBiz = 0;//map的开始，中断后使用，默认可设置为0
            BigDecimal mvMin = NUM_YI_40;//NUM_YI_1000  NUM_YI_50  NUM_YI_100
            BigDecimal mvMax = null;
            List<String> maKltList = Arrays.asList(KLT_15, KLT_30, KLT_60, KLT_101, KLT_102);//价格区间周期列表
//        List<String> maKltList = Arrays.asList(KLT_102, KLT_101, KLT_60);//价格区间周期列表
//        List<String> maKltList = Arrays.asList(KLT_102);//价格区间周期列表

            List<RankBizDataDiff> bizList = StockService.listBiz(NUM_MAX_99);//查询业务列表

            CondStockAdrCount stockAdrCountCond = new CondStockAdrCount();
            stockAdrCountCond.setMvMin(mvMin);
            stockAdrCountCond.setMvMax(mvMax);
            stockAdrCountCond.setF139(board);
            stockAdrCountCond.setMaKltList(maKltList);
            stockAdrCountCond.setUpdateNet(true);//用时：16

            StockAdrCountControl.updateListByBizAll(date, bizList, begBiz, spBizName, stockAdrCountCond);

            System.out.println("定时任务-" + jobName + "-end:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));
        }, 0, 5, TimeUnit.MINUTES);

        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            String jobName = "更新股票涨幅统计";
            System.out.println();
            System.out.println("定时任务-" + jobName + "-beg:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));
            Long board = DB_RANK_BIZ_F139_BK_MAIN;//
            String spBizName = null;//特定业务：半导体 "半导体"
            int begBiz = 0;//map的开始，中断后使用，默认可设置为0
            BigDecimal mvMin = NUM_YI_40;//NUM_YI_1000  NUM_YI_50  NUM_YI_100
            BigDecimal mvMax = null;
            List<String> maKltList = Arrays.asList(KLT_15, KLT_30, KLT_60, KLT_101, KLT_102);//价格区间周期列表
//        List<String> maKltList = Arrays.asList(KLT_102, KLT_101, KLT_60);//价格区间周期列表
//        List<String> maKltList = Arrays.asList(KLT_102);//价格区间周期列表

            List<RankBizDataDiff> bizList = StockService.listBiz(NUM_MAX_99);//查询业务列表

            CondStockAdrCount stockAdrCountCond = new CondStockAdrCount();
            stockAdrCountCond.setMvMin(mvMin);
            stockAdrCountCond.setMvMax(mvMax);
            stockAdrCountCond.setF139(board);
            stockAdrCountCond.setMaKltList(maKltList);
//            stockAdrCountCond.setUpdateNet(true);//用时：16

            if (countThread % 10 == 0) {
                StockAdrCountControl.save(date, bizList, false, spBizName, stockAdrCountCond);
                stockAdrCountCond.setUpdateSum(true);//总花费时间：1116
                stockAdrCountCond.setUpdateOrder(true);
            }
            if (countThread % 5 == 1 ) {
                stockAdrCountCond.setUpdateUpMa(true);//总花费时间：(40亿)用时：1454  (50亿)1225
            }
            if (countThread % 5 == 2 ) {
                stockAdrCountCond.setUpdateNetArea(true);//总花费时间：613
            }

            StockAdrCountControl.updateListByBizAll(date, bizList, begBiz, spBizName, stockAdrCountCond);

            System.out.println("运行次数" + (++countThread));
            System.out.println("定时任务-" + jobName + "-end:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));
        }, 0, 5, TimeUnit.MINUTES);
    }

}
