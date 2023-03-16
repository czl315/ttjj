package ttjj.rank.stat.schedule;

import ttjj.dto.CondMa;
import ttjj.rank.stat.BizEtfStat;
import utils.DateUtil;

import java.util.Arrays;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static utils.Content.*;
import static utils.Content.KLT_101;

/**
 * 定时任务-etf
 */
public class EtfJob {
    public static void main(String[] args) {
        statShowEtfUpMaSchedule();//定时任务-etf-检查均线
    }

    /**
     * 定时任务-etf-检查均线
     */
    private static void statShowEtfUpMaSchedule() {
        new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(() -> {
            System.out.println();
            long begTime = System.currentTimeMillis();
            System.out.println("定时任务-etf-检查均线-beg:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0));
            String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-10-26";
            try {
                String spDate = null;//
//        String spDate = DateUtil.getAddDays(YYYY_MM_DD, date, 1);//是否显示特定日期涨跌   "2022-05-18"

                CondMa condMa = new CondMa();
                condMa.setOrderField(ORDER_FIELD_NET_AREA_DAY_10);//ORDER_FIELD_NET_AREA_DAY_5 ORDER_FIELD_F3 ORDER_FIELD_MAXDOWN    ORDER_FIELD_MINRISE
                condMa.setOrderDesc(true);//是否倒序
                condMa.setDate(date);
                condMa.setDays(3);
                condMa.setSpDate(spDate);
                condMa.setShowPriceArea(true);//是否显示价格区间
//                condMa.setKltList(Arrays.asList(KLT_5, KLT_15, KLT_30, KLT_60, KLT_101));//价格区间周期列表
                condMa.setKltList(Arrays.asList(KLT_15, KLT_30, KLT_60, KLT_101, KLT_102));//价格区间周期列表
                condMa.setShowUpMa(true);//是否显示-超过均线
                condMa.setShowDownMa(true);//是否显示-跌落均线
                condMa.setShowBreakDownMaMax(true);//是否显示-跌落均线-最高
                condMa.setShowBreakUpMaMin(true);//是否显示-涨上均线-最低
                condMa.setFindKline(true);//是否查询最新k线
                condMa.setShowFlowIn(false);//是否显示资金流入
                condMa.setShowDateMinMax(false);//是否显示日最低点、最高点
                condMa.setShowMaxMin(true);//是否显最低、最高

//                condMa.setShowPct(false);//是否显示均线百分比
                condMa.setShowPct(true);//是否显示均线百分比
                condMa.setShowPctKltList(Arrays.asList(KLT_60));

                BizEtfStat.showEtfUpMa(date, condMa);//etf-超过均线
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("定时任务-etf-检查均线-end:" + DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD_HH_MM_SS, 0) + "，用时：" + (System.currentTimeMillis() - begTime) / 1000);
        }, 0, 5, TimeUnit.MINUTES);
    }

}
