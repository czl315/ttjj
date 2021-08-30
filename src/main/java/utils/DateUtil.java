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
    public static String YYYY_MM_DD = "yyyy-MM-dd";
    public static String YYYYMMDD = "yyyyMMdd";

    public static void main(String[] args) {

//        //获取年月日
//        System.out.println(getCurYear());
//        System.out.println(getCurMonth());
//        System.out.println(getCurDay());
//        System.out.println(getCurYearMonth());
//        System.out.println(getCurYearWeek());
//        System.out.println(getWeek(new Date()));
//        System.out.println(getWeekByYyyyMmDd("2021-08-29",DateUtil.YYYY_MM_DD));
        System.out.println(getYearWeek("2021-08-29", DateUtil.YYYY_MM_DD));


//        //获取格式化日期
//        String date = getDateStrAddDaysByFormat(YYYY_MM_DD, 2021, 2, 28, -1);
//        System.out.println(date);

//        //  取得一年的第几周    27
//        int weekOfYear = getWeekOfYear(new Date());
//        System.out.println(weekOfYear);
//
//        //时间戳   1625069737271
//        long timeInMillis = getTimeInMillis(new Date());
//        System.out.println(timeInMillis);

        //  根据日期取得星期几
//        String dateStr ="2021-07-23";
//        String week = getWeekByYyyyMmDd(dateStr);
//        System.out.println(week);

//        //  获取当前日期增加或减少天数的日期格式
//        String dayStr = getCurDateStrAddDaysByFormat(YYYY_MM_DD, -10);
//        System.out.println(dayStr);


    }

    /**
     * @return
     */
    public static String getToday(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 获取年
     *
     * @return
     */
    public static int getCurYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * @return
     */
    public static int getCurMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * @return
     */
    public static String getCurYearMonth() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        String yearMonth = year + "" + month;
        if (month < 10) {
            yearMonth = year + "0" + month;
        }
        return yearMonth;
    }


    /**
     * @return
     */
    public static String getYearWeek(String dateStr, String format) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        String yearMonth = year + "" + week;
        if (week < 10) {
            yearMonth = year + "0" + week;
        }
        return yearMonth;
    }

    /**
     * @param dateStr
     * @param format
     * @return
     */
    public static String getYearMonth(String dateStr, String format) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        String yearMonth = year + "" + month;
        if (month < 10) {
            yearMonth = year + "0" + month;
        }
        return yearMonth;
    }

    /**
     * @return
     */
    public static int getCurDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前日期增加或减少天数的日期格式
     *
     * @param format
     * @param days
     * @return
     */
    public static String getCurDateStrAddDaysByFormat(String format, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    /**
     * 获取格式化日期
     *
     * @param format
     * @param year
     * @param month
     * @param day
     * @param days
     * @return
     */
    public static String getDateStrAddDaysByFormat(String format, int year, int month, int day, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, (month - 1));
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return new SimpleDateFormat(format).format(calendar.getTime());
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
     *
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
     *
     * @return
     */
    public static String getWeekByYyyyMmDd(String dateStr, String format) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat(format).parse(dateStr);
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
