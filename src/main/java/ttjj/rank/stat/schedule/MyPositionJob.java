package ttjj.rank.stat.schedule;

import ttjj.rank.FupanControl;
import utils.DateUtil;

/**
 * k线
 */
public class MyPositionJob {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-07-22";

        FupanControl.checkMaByMyPositionSchedule(date);//保存常用etf
    }
}
