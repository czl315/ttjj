package ttjj.rank.stat.schedule;

import org.springframework.util.StopWatch;
import ttjj.rank.KlineControl;
import utils.ContMapEtf;
import utils.DateUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

//        saveKlineIndexJob();
//        saveKlineEtfJob();
        saveKlineBroadAndZsAndEtfJob();
//        saveKlineConceptionSchedule(date);
    }

    /**
     * 定时保存
     */
    private static void saveKlineBroadAndZsAndEtfJob() {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-07-22";
        int period = 5;
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            Boolean isUpdateMv = false;//是否更新市值
            String funcName = "保存K线(指数、板块、etf)";
            List<String> kltList_101_15 = Arrays.asList(KLT_15, KLT_30, KLT_60, KLT_101);
            StopWatch sw = new StopWatch(funcName);

            sw.start("保存K线-指数");
            KlineControl.saveKlineAndMv(date, DB_RANK_BIZ_TYPE_ZS, Arrays.asList(KLT_5, KLT_15, KLT_30, KLT_60, KLT_101), KlineControl.handlerZqMap(date, DB_RANK_BIZ_TYPE_ZS), isUpdateMv, funcName);//
            sw.stop();

            sw.start("保存K线-板块");
            KlineControl.saveKlineAndMv(date, DB_RANK_BIZ_TYPE_BAN_KUAI, kltList_101_15, KlineControl.handlerZqMap(date, DB_RANK_BIZ_TYPE_BAN_KUAI), true, funcName);//
            KlineControl.updateFundFlow(date, DB_RANK_BIZ_TYPE_BAN_KUAI, Arrays.asList(KLT_101, KLT_60, KLT_30, KLT_15));
            sw.stop();

            sw.start("保存K线-ETF");
            Map<String, String> mapEtf = ContMapEtf.ETF_MORE;//K线-ETF-主要
            KlineControl.saveKlineAndMv(date, DB_RANK_BIZ_TYPE_ETF, kltList_101_15, mapEtf, true, funcName);
            sw.stop();

            sw.start("保存K线-概念");
            KlineControl.saveKlineAndMv(date, DB_RANK_BIZ_TYPE_GAI_NIAN, Arrays.asList(KLT_30, KLT_60, KLT_101), KlineControl.handlerZqMap(date, DB_RANK_BIZ_TYPE_GAI_NIAN), isUpdateMv, funcName);//
            sw.stop();

            System.out.println(sw.prettyPrint());
            System.out.println(sw.shortSummary());
            System.out.println(sw.getTotalTimeMillis());
        }, 0, period, TimeUnit.MINUTES);
    }

    private static void saveKlineConceptionSchedule(String date) {
        int period = 10;
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            Boolean isUpdateMv = false;//是否更新市值
            String funcName = "保存K线";
            //            Map<String, String> mapEtf = KlineControl.handlerZqMap(date, DB_RANK_BIZ_TYPE_ETF);//K线-ETF-所有
            Map<String, String> mapEtf = ContMapEtf.ETF_MORE;//K线-ETF-主要
            long begTime = System.currentTimeMillis();
            System.out.println("定时任务-保存常用etf-beg:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));
            List<String> kltList_101_15 = Arrays.asList(KLT_15, KLT_30, KLT_60, KLT_101);
            StopWatch sw = new StopWatch("保存K线");
            sw.start("保存K线-概念");
            KlineControl.saveKlineAndMv(date, DB_RANK_BIZ_TYPE_GAI_NIAN, kltList_101_15, KlineControl.handlerZqMap(date, DB_RANK_BIZ_TYPE_GAI_NIAN), isUpdateMv, funcName);//
            sw.stop();
//            KlineControl.saveKlineAndMv(date, DB_RANK_BIZ_TYPE_ETF, Arrays.asList(KLT_5, KLT_15, KLT_30, KLT_60, KLT_101), ContMapEtf.ETF_All);//保存常用etf
            System.out.println(sw.prettyPrint());
            System.out.println(sw.shortSummary());
            System.out.println(sw.getTotalTimeMillis());
            System.out.println("定时任务-保存K线-end:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0) + "，用时：" + (System.currentTimeMillis() - begTime) / 1000);
        }, 1, period, TimeUnit.MINUTES);
    }

}
