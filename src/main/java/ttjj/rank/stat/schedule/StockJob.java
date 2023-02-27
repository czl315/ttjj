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
        scheduleStock(date);//定时任务
    }

    /**
     * 定时任务-股票-更新
     *
     * @param date 日期
     */
    private static void scheduleStock(String date) {
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            System.out.println();
            long begTime = System.currentTimeMillis();
            System.out.println("定时任务-股票-更新-beg:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));

            int startNum = 0;//开始位置，默认0
            Map<String, Boolean> maUpdateMap = new HashMap<>();
            StockControl.setMaMapType(MA_TYPE_DAY, maUpdateMap);
            StockControl.setMaMapType(MA_TYPE_MINUTE15, maUpdateMap);
            StockControl.setMaMapType(MA_TYPE_WEEK, maUpdateMap);

            if (countThread % 10 == 0) {
                //查询指定日期的个数,如果低于阈值：1000，添加
                CondStock conditionCountNotNull = new CondStock();
                conditionCountNotNull.setDate(date);
                Integer countNotNull = StockService.findCountByCondition(conditionCountNotNull);
                if (countNotNull == null || countNotNull < 1000) {
                    System.out.println("查询指定日期的个数,如果低于1000，添加：" + countNotNull);
                    StockControl.addTodayStCom(date, startNum);//  添加或更新股票-根据日期
                } else {
                    System.out.println("查询指定日期的个数,数据已存在，无需新增：" + countNotNull);
                }

                //查询概念非空的个数,如果低于4000，更新概念
                CondStock conditionConceptionNotNull = new CondStock();
                conditionConceptionNotNull.setDate(date);
                conditionConceptionNotNull.setConpetionNotNull("yes");
                Integer countConceptionNotNull = StockService.findCountByCondition(conditionConceptionNotNull);
                if (countNotNull == null || countNotNull < 1000) {
                    System.out.println("概念非空个数：" + countConceptionNotNull);
                    StockControl.updateConception(date, startNum);//更新题材概念
                } else {
                    System.out.println("概念非空个数,数据已存在，无需更新概念：" + countConceptionNotNull);
                }
            }
            StockControl.updateTodayStCom(date, startNum);//更新股票
            if (countThread % 5 == 1) {
                StockControl.updateNetToday(date, startNum, maUpdateMap, false, NUM_YI_40);//  更新净值
            }
            System.out.println("运行次数" + (++countThread));
            System.out.println("定时任务-股票-更新-end:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0) + "，用时：" + (System.currentTimeMillis() - begTime) / 1000);
        }, 0, 5, TimeUnit.MINUTES);
    }

}
