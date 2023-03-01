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
            Integer countNotNullLimit = 5000;//数据已存在阈值限定5000，5222(2023.02.27)
            Integer countConceptionNotNullLimit = 4500;//概念非空数据已存在阈值限定4000，4741(2023.02.27)
            System.out.println();
            long begTime = System.currentTimeMillis();
            System.out.println("定时任务-股票-更新-beg:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));

            int startNum = 0;//开始位置，默认0
            Map<String, Boolean> maUpdateMap = new HashMap<>();
            StockControl.setMaMapType(MA_TYPE_DAY, maUpdateMap);
            StockControl.setMaMapType(MA_TYPE_MINUTE15, maUpdateMap);
            StockControl.setMaMapType(MA_TYPE_WEEK, maUpdateMap);

            if (countThread % 10 == 0) {
                //查询指定日期的个数,如果低于阈值：1000，添加。否则，数据已存在，无需新增：5222
                CondStock conditionCountNotNull = new CondStock();
                conditionCountNotNull.setDate(date);
                Integer countNotNull = StockService.findCountByCondition(conditionCountNotNull);
                if (countNotNull == null || countNotNull < countNotNullLimit) {
                    System.out.print("查询指定日期的个数低于阈值" + countNotNullLimit + "，已存在：" + countNotNull);
                    Integer rs = StockControl.addTodayStCom(date, startNum);//  添加或更新股票-根据日期
                    System.out.println("，保存成功个数：" + rs);
                } else {
                    System.out.println("查询指定日期的个数,数据已存在，无需新增：" + countNotNull);
                }

                //查询概念非空的个数,如果低于阈值：1000，更新概念。否则，数据已存在，无需更新概念：4741
                CondStock conditionConceptionNotNull = new CondStock();
                conditionConceptionNotNull.setDate(date);
                conditionConceptionNotNull.setConceptionNotNull(true);
                Integer countConceptionNotNull = StockService.findCountByCondition(conditionConceptionNotNull);
                if (countConceptionNotNull == null || countConceptionNotNull < countConceptionNotNullLimit) {
                    System.out.print("概念非空个数低于阈值" + countConceptionNotNullLimit + "，已存在：" + countConceptionNotNull);
                    int rsUpdate = StockControl.updateConception(date, startNum);//更新题材概念
                    System.out.println("更新概念个数:" + rsUpdate);
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
