package utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author chenzhilong
 * @date 2021/7/1
 */
public class DateUtil {
    public static void main(String[] args) {
        //  取得一年的第几周    27
        int weekOfYear = getWeekOfYear(new Date());
        System.out.println(weekOfYear);

        //时间戳   1625069737271
        long timeInMillis = getTimeInMillis(new Date());
        System.out.println(timeInMillis);
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
}
