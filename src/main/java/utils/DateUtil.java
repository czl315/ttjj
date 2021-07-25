package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author chenzhilong
 * @date 2021/7/1
 */
public class DateUtil {
    public static void main(String[] args) {
//        //  取得一年的第几周    27
//        int weekOfYear = getWeekOfYear(new Date());
//        System.out.println(weekOfYear);
//
//        //时间戳   1625069737271
//        long timeInMillis = getTimeInMillis(new Date());
//        System.out.println(timeInMillis);

        String dateStr ="2021-07-23";
        String week = getWeekByYyyyMmDd(dateStr);
        System.out.println(week);
    }


    /**
     * 取得一年的第几周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 取得指定日期的时间戳
     *
     * @param date
     * @return
     */
    public static long getTimeInMillis(Date date) {
        Calendar c = Calendar.getInstance();
        return c.getTimeInMillis();
    }

    /**
     * 根据日期取得星期几
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        String[] weeks = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

    /**
     * 根据日期取得星期几
     * @return
     */
    public static String getWeekByYyyyMmDd(String dateStr) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] weeks = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }
}
