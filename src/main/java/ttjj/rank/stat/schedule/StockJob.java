package ttjj.rank.stat.schedule;

import ttjj.rank.StockControl;
import utils.DateUtil;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务
 */
public class StockJob {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
        scheduleStock(date);//定时任务
    }

    /**
     * 定时任务-股票-更新
     *
     * @param date 日期
     */
    private static void scheduleStock(String date) {
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            System.out.println();
            long begTime = System.currentTimeMillis();
            System.out.println("定时任务-股票-更新-beg:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));
            StockControl.updateTodayStCom(date, 0);//更新股票
            System.out.println("定时任务-股票-更新-end:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0) + "，用时：" + (System.currentTimeMillis() - begTime) / 1000);
        }, 0, 5, TimeUnit.MINUTES);
    }

}
