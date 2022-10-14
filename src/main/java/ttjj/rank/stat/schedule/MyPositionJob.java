package ttjj.rank.stat.schedule;

import ttjj.rank.FupanControl;
import utils.ContentCookie;
import utils.DateUtil;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static utils.Content.DAYS_1;
import static utils.Content.KLT_101;

/**
 * k线
 */
public class MyPositionJob {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-07-22";

        checkMaByMyPositionSchedule(date);//保存常用etf
    }

    /**
     * 定时任务-检查我的持仓
     *
     * @param date 日期
     */
    public static void checkMaByMyPositionSchedule(String date) {
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            System.out.println();
            System.out.println();
            System.out.println("定时任务-检查我的持仓-beg:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));
//            FupanControl.insertOrUpdate(date, KLT_101, DAYS_1, ContentCookie.COOKIE_DFCF);
            FupanControl.checkMaByMyPosition(date);//检查我的持仓
            System.out.println("定时任务-检查我的持仓-end:");
        }, 0, 15, TimeUnit.MINUTES);
    }
}
