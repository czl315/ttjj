package ttjj.rank.stat.schedule;

import ttjj.dto.CondStock;
import ttjj.rank.StockControl;
import ttjj.service.StockService;
import utils.DateUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static utils.Content.*;
import static utils.Content.MA_TYPE_WEEK;

/**
 * 定时任务
 */
public class StockJob {
    private static volatile int countThread = 0;//线程次数

    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);

        //查询指定日期的个数,如果低于阈值，添加。否则，数据已存在，无需新增：5222
        Integer countNotNullLimit = 5000;//数据已存在阈值限定5000，5222(2023.02.27)
        StockControl.addTodayStComByExistCount(date, countNotNullLimit);//数据已存在阈值限定5000，5222(2023.02.27)

        scheduleStock(date);//定时任务
    }

    /**
     * 定时任务-股票-更新
     *
     * @param date 日期
     */
    private static void scheduleStock(String date) {
        int period = 5;

        //新增保存今日股票
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            Integer countNotNullLimit = 5000;//数据已存在阈值限定5000，5222(2023.02.27)
            //查询指定日期的个数,如果低于阈值，添加。否则，数据已存在，无需新增：5222
            StockControl.addTodayStComByExistCount(date, countNotNullLimit);//数据已存在阈值限定5000，5222(2023.02.27)
        }, 0, 30, TimeUnit.MINUTES);


        //更新概念
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            Integer countConceptionNotNullLimit = 4500;//概念非空数据已存在阈值限定4000，4741(2023.02.27)
            StockControl.updateConceptionByExistCount(date, countConceptionNotNullLimit);//数据已存在阈值限定5000，5222(2023.02.27)
        }, 0, 30, TimeUnit.MINUTES);

        //更新股票
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            System.out.println();
            long begTime = System.currentTimeMillis();
            System.out.println("定时任务-股票-更新-beg:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));

            int startNum = 0;//开始位置，默认0
            StockControl.updateTodayStCom(date, startNum);//更新股票

            System.out.println("运行次数" + (++countThread));
            System.out.println("定时任务-股票-更新-end:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0) + "，用时：" + (System.currentTimeMillis() - begTime) / 1000);

        }, 0, 5, TimeUnit.MINUTES);

        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            int startNum = 0;//开始位置，默认0
            Map<String, Boolean> maUpdateMap = new HashMap<>();
            StockControl.setMaMapType(MA_TYPE_DAY, maUpdateMap);
            StockControl.setMaMapType(MA_TYPE_MINUTE15, maUpdateMap);
            StockControl.setMaMapType(MA_TYPE_WEEK, maUpdateMap);

//            if (countThread % 5 == 1) {
                StockControl.updateNetToday(date, startNum, maUpdateMap, false, NUM_YI_40);//  更新净值
//            }
        }, 2, 60, TimeUnit.MINUTES);
    }

}
