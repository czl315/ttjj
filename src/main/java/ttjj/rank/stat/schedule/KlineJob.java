package ttjj.rank.stat.schedule;

import ttjj.rank.KlineControl;
import utils.ContMapEtf;
import utils.DateUtil;

import java.util.Arrays;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static utils.Content.*;

/**
 * k线
 */
public class KlineJob {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-07-22";

        saveKlineEtfMianSchedule(date);
    }

    private static void saveKlineEtfMianSchedule(String date) {
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            System.out.println("定时任务-保存常用etf-beg:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));
            KlineControl.saveKlineAndMv(date, DB_RANK_BIZ_TYPE_ETF, Arrays.asList(KLT_5, KLT_15, KLT_30, KLT_60, KLT_101), KlineControl.handlerZqMap(date, DB_RANK_BIZ_TYPE_ETF));
//            KlineControl.saveKlineAndMv(date, DB_RANK_BIZ_TYPE_ETF, Arrays.asList(KLT_5, KLT_15, KLT_30, KLT_60, KLT_101), ContMapEtf.ETF_All);//保存常用etf
            System.out.println("定时任务-保存常用etf-end:");
        }, 0, 5, TimeUnit.MINUTES);
    }

}
