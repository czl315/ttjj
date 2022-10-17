package ttjj.rank.stat.schedule;

import ttjj.rank.stat.BizEtfDemo;
import utils.DateUtil;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务-etf
 */
public class EtfJob {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
        statShowEtfUpMaSchedule(date);//定时任务-etf-检查均线
    }

    /**
     * 定时任务-etf-检查均线
     *
     * @param date 日期
     */
    private static void statShowEtfUpMaSchedule(String date) {
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            System.out.println();
            System.out.println("定时任务-etf-检查均线-beg:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));
            BizEtfDemo.showEtfUpMa(date);//etf-超过均线
            System.out.println("定时任务-etf-检查均线-end:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));
        }, 0, 5, TimeUnit.MINUTES);
    }

}
