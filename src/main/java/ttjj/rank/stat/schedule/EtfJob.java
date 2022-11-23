package ttjj.rank.stat.schedule;

import ttjj.rank.stat.BizEtfStat;
import utils.DateUtil;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务-etf
 */
public class EtfJob {
    public static void main(String[] args) {
        statShowEtfUpMaSchedule();//定时任务-etf-检查均线
    }

    /**
     * 定时任务-etf-检查均线
     *
     */
    private static void statShowEtfUpMaSchedule() {
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            System.out.println();
            long begTime = System.currentTimeMillis();
            System.out.println("定时任务-etf-检查均线-beg:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));
            String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-10-26";
            try {
                BizEtfStat.showEtfUpMa(date);//etf-超过均线
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("定时任务-etf-检查均线-end:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0) + "，用时：" + (System.currentTimeMillis() - begTime) / 1000);
        }, 0, 5, TimeUnit.MINUTES);
    }

}
