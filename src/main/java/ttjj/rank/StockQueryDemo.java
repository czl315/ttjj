package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.RankStockCommpanyDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.*;
import ttjj.service.KlineService;
import utils.Content;
import utils.DateUtil;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.*;

import static utils.Content.*;

/**
 * 排行-行业股票-公司-每日明细
 */
public class StockQueryDemo {
    /**
     * @param args
     */
    public static void main(String[] args) {
        //  超跌反弹
        {

            SuperDropBounceStat statistics = new SuperDropBounceStat(), statisticsDay2 = new SuperDropBounceStat(), statisticsDay3 = new SuperDropBounceStat(), statisticsDay5 = new SuperDropBounceStat();
            SuperDropBounceStat statisticsDay10 = new SuperDropBounceStat(), statisticsDay20 = new SuperDropBounceStat(), statisticsDay30 = new SuperDropBounceStat(), statisticsDay60 = new SuperDropBounceStat();
            for (int i = 15; i <= 60; i++) {
                String curDate = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -i);
                //  超跌反弹
                superDropBounce(curDate, statistics);
            }
            System.out.println("统计:" + JSON.toJSONString(statistics));
            System.out.println("统计日加1 :" +"涨："+ statistics.getAdrUpCountDay1()+"，跌："+ statistics.getAdrDownCountDay1()+"，共："+ statistics.getRsCountDay1());
            System.out.println("统计日加2 :" +"涨："+ statistics.getAdrUpCountDay2()+"，跌："+ statistics.getAdrDownCountDay2()+"，共："+ statistics.getRsCountDay2());
            System.out.println("统计日加3 :" +"涨："+ statistics.getAdrUpCountDay3()+"，跌："+ statistics.getAdrDownCountDay3()+"，共："+ statistics.getRsCountDay3());
            System.out.println("统计日加5 :" +"涨："+ statistics.getAdrUpCountDay5()+"，跌："+ statistics.getAdrDownCountDay5()+"，共："+ statistics.getRsCountDay5());
            System.out.println("统计日加10:" +"涨："+ statistics.getAdrUpCountDay10()+"，跌："+ statistics.getAdrDownCountDay10()+"，共："+ statistics.getRsCountDay10());
            System.out.println("统计日加20:" +"涨："+ statistics.getAdrUpCountDay20()+"，跌："+ statistics.getAdrDownCountDay20()+"，共："+ statistics.getRsCountDay20());
            System.out.println("统计日加30:" +"涨："+ statistics.getAdrUpCountDay30()+"，跌："+ statistics.getAdrDownCountDay30()+"，共："+ statistics.getRsCountDay30());
            System.out.println("统计日加60:" +"涨："+ statistics.getAdrUpCountDay60()+"，跌："+ statistics.getAdrDownCountDay60()+"，共："+ statistics.getRsCountDay60());
//            System.out.println("日加2:" + JSON.toJSONString(statisticsDay2));
//            System.out.println("日加3:" + JSON.toJSONString(statisticsDay3));
//            System.out.println("日加5:" + JSON.toJSONString(statisticsDay5));
//            System.out.println("日加10:" + JSON.toJSONString(statisticsDay10));
//            System.out.println("日加20:" + JSON.toJSONString(statisticsDay20));
//            System.out.println("日加30:" + JSON.toJSONString(statisticsDay30));
//            System.out.println("日加60:" + JSON.toJSONString(statisticsDay60));

        }

        //突破均线
//        {
//            BigDecimal goodRateCurDayLimitUp = new BigDecimal("0.01");
//            String curDate = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -8);
//            List<String> dateListBefore = RankStockCommpanyDao.findListDateBefore(new DateCond(curDate, 120));
////        System.out.println(JSON.toJSONString(dateListBefore));
//            int countUp1 = 0, countUp2 = 0, countUp3 = 0;//上涨个数
//            int countDown1 = 0, countDown2 = 0, countDown3 = 0;//下跌个数
////            String weekFiter = "一";
////            String weekFiter = "二";
////            String weekFiter = "三";
////            String weekFiter = "四";
//            String weekFiter = "五";
//            for (String date : dateListBefore) {
//                // 均线突破
//                MaBreakUpRs rs = maBreakUp(date, weekFiter, goodRateCurDayLimitUp);
//                if (rs == null) {
//                    continue;
//                }
//                countUp1 = countUp1 + rs.getCurDayAdd1UpCount();
//                countUp2 = countUp2 + rs.getCurDayAdd2UpCount();
//                countUp3 = countUp3 + rs.getCurDayAdd3UpCount();
//                countDown1 = countDown1 + rs.getCurDayAdd1DownCount();
//                countDown2 = countDown2 + rs.getCurDayAdd1DownCount();
//                countDown3 = countDown3 + rs.getCurDayAdd1DownCount();
//            }
//            System.out.println("后1日合计涨跌比:" + countUp1 + ":" + countDown1 + ",上涨率：" + new BigDecimal(countUp1).divide(new BigDecimal(countUp1 + countDown1), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")));
//            System.out.println(countUp1 + "\t" + countDown1 + "\t" + new BigDecimal(countUp1).divide(new BigDecimal(countUp1 + countDown1), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")));
//
//        }

        /**查询-统计数据-股票分组**/
//        String begDate = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -365);
//        String endDate = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, 0);
//        String typeName = ST_BIZ_TYPE_YOU_SE_JIN_SHU;//业务板块
//        findListTongJj(typeName,begDate,endDate);//查询-统计数据
//        findListTongJj("2021-01-01","2021-01-31");//查询-统计数据
//        findListTongJj("2021-02-01","2021-02-29");//查询-统计数据
//        findListTongJj("2021-03-01","2021-03-31");//查询-统计数据
//        findListTongJj("2021-04-01","2021-04-31");//查询-统计数据
//        findListTongJj("2021-05-01","2021-05-31");//查询-统计数据
//        findListTongJj("2021-06-01","2021-06-31");//查询-统计数据
//        findListTongJj("2021-07-01","2021-07-31");//查询-统计数据
//        findListTongJj("2021-08-01","2021-08-31");//查询-统计数据
//        findListTongJj("2021-09-01","2021-09-31");//查询-统计数据
    }

    /**
     * 超跌反弹
     */
    private static List<SuperDropBounceRs> superDropBounce(String curDate, SuperDropBounceStat statisticsDay1) {
        SuperDropBounceCond condition = new SuperDropBounceCond();
        List<String> dateListBefore = RankStockCommpanyDao.findListDateBefore(new DateCond(curDate, 60));
//        System.out.println(JSON.toJSONString(dateListBefore));
        List<String> dateListAfter = RankStockCommpanyDao.findListDateAfter(new DateCond(curDate, 60));
//        System.out.println(JSON.toJSONString(dateListAfter));
        String curDayAdd1 = dateListAfter.get(0);
        String curDayAdd2 = dateListAfter.get(1);
        condition.setCurDay(curDate);
        condition.setCurDayAdd1(curDayAdd1);
        condition.setCurDayAdd2(curDayAdd2);
        if (dateListAfter.size() >= 3) {
            condition.setCurDayAdd3(dateListAfter.get(2));
        }
        if (dateListAfter.size() >= 5) {
            condition.setCurDayAdd5(dateListAfter.get(4));
        }
        if (dateListAfter.size() >= 10) {
            condition.setCurDayAdd10(dateListAfter.get(9));
        }
        if (dateListAfter.size() >= 20) {
            condition.setCurDayAdd20(dateListAfter.get(19));
        }
        if (dateListAfter.size() >= 30) {
            condition.setCurDayAdd30(dateListAfter.get(29));
        }
        if (dateListAfter.size() >= 60) {
            condition.setCurDayAdd60(dateListAfter.get(59));
        }
        if (dateListBefore.size() >= 1) {
            condition.setCurDaySub1(dateListBefore.get(0));
        }
        if (dateListBefore.size() >= 2) {
            condition.setCurDaySub2(dateListBefore.get(1));
        }
        if (dateListBefore.size() >= 3) {
            condition.setCurDaySub3(dateListBefore.get(2));
        }
        if (dateListBefore.size() >= 4) {
            condition.setCurDaySub4(dateListBefore.get(3));
        }
        if (dateListBefore.size() >= 5) {
            condition.setCurDaySub5(dateListBefore.get(4));
        }
        if (dateListBefore.size() >= 6) {
            condition.setCurDaySub6(dateListBefore.get(5));
        }
        condition.setF139(DB_RANK_BIZ_F139_BAN_KUAI);
//        condition.setType_name(ST_BIZ_TYPE_YOU_SE_JIN_SHU);
        condition.setMarketValueMin(new BigDecimal("20000000000"));//市值
        //量比最高
        condition.setQrrMaxDaySub1(new BigDecimal("0.8"));
        condition.setQrrMaxDaySub2(new BigDecimal("0.8"));
        condition.setQrrMaxDaySub3(new BigDecimal("0.8"));
        condition.setQrrMaxDaySub4(new BigDecimal("0.8"));
//        condition.setQrrMaxDaySub5(new BigDecimal("0"));
        condition.setAdrMinDay0(new BigDecimal("0"));//涨跌限定-最低-日加n
        condition.setAdrMaxDaySub1(new BigDecimal("0"));//涨跌限定-最高-日减1
        condition.setAdrMaxDaySub2(new BigDecimal("0"));//涨跌限定-最高-日减2
        condition.setAdrMaxDaySub3(new BigDecimal("0"));//涨跌限定-最高-日减3
        condition.setAdrMaxDaySub4(new BigDecimal("0"));//涨跌限定-最高-日减4
//        condition.setAdrMaxDaySub5(new BigDecimal("0"));
        System.out.println("查询条件：" + JSON.toJSONString(condition));
        List<SuperDropBounceRs> rs = RankStockCommpanyDao.findListSuperDropBounce(condition);
        if (rs == null || rs.size() == 0) {
            System.out.println("返回结果为空！");
            return null;
        }

        if (statisticsDay1.getRsCountDay1() == null) {
            statisticsDay1.setRsCountDay1(new BigDecimal("0"));
        }
        if (statisticsDay1.getAdrUpCountDay1() == null) {
            statisticsDay1.setAdrUpCountDay1(new BigDecimal("0"));
        }
        if (statisticsDay1.getAdrDownCountDay1() == null) {
            statisticsDay1.setAdrDownCountDay1(new BigDecimal("0"));
        }
        if (statisticsDay1.getRsCountDay2() == null) {
            statisticsDay1.setRsCountDay2(new BigDecimal("0"));
        }
        if (statisticsDay1.getAdrUpCountDay2() == null) {
            statisticsDay1.setAdrUpCountDay2(new BigDecimal("0"));
        }
        if (statisticsDay1.getAdrDownCountDay2() == null) {
            statisticsDay1.setAdrDownCountDay2(new BigDecimal("0"));
        }
        if (statisticsDay1.getRsCountDay3() == null) {
            statisticsDay1.setRsCountDay3(new BigDecimal("0"));
        }
        if (statisticsDay1.getAdrUpCountDay3() == null) {
            statisticsDay1.setAdrUpCountDay3(new BigDecimal("0"));
        }
        if (statisticsDay1.getAdrDownCountDay3() == null) {
            statisticsDay1.setAdrDownCountDay3(new BigDecimal("0"));
        }
        if (statisticsDay1.getRsCountDay5() == null) {
            statisticsDay1.setRsCountDay5(new BigDecimal("0"));
        }
        if (statisticsDay1.getAdrUpCountDay5() == null) {
            statisticsDay1.setAdrUpCountDay5(new BigDecimal("0"));
        }
        if (statisticsDay1.getAdrDownCountDay5() == null) {
            statisticsDay1.setAdrDownCountDay5(new BigDecimal("0"));
        }
        if (statisticsDay1.getRsCountDay10() == null) {
            statisticsDay1.setRsCountDay10(new BigDecimal("0"));
        }
        if (statisticsDay1.getAdrUpCountDay10() == null) {
            statisticsDay1.setAdrUpCountDay10(new BigDecimal("0"));
        }
        if (statisticsDay1.getAdrDownCountDay10() == null) {
            statisticsDay1.setAdrDownCountDay10(new BigDecimal("0"));
        }
        if (statisticsDay1.getRsCountDay20() == null) {
            statisticsDay1.setRsCountDay20(new BigDecimal("0"));
        }
        if (statisticsDay1.getAdrUpCountDay20() == null) {
            statisticsDay1.setAdrUpCountDay20(new BigDecimal("0"));
        }
        if (statisticsDay1.getAdrDownCountDay20() == null) {
            statisticsDay1.setAdrDownCountDay20(new BigDecimal("0"));
        }
        if (statisticsDay1.getRsCountDay30() == null) {
            statisticsDay1.setRsCountDay30(new BigDecimal("0"));
        }
        if (statisticsDay1.getAdrUpCountDay30() == null) {
            statisticsDay1.setAdrUpCountDay30(new BigDecimal("0"));
        }
        if (statisticsDay1.getAdrDownCountDay30() == null) {
            statisticsDay1.setAdrDownCountDay30(new BigDecimal("0"));
        }
        if (statisticsDay1.getRsCountDay60() == null) {
            statisticsDay1.setRsCountDay60(new BigDecimal("0"));
        }
        if (statisticsDay1.getAdrUpCountDay60() == null) {
            statisticsDay1.setAdrUpCountDay60(new BigDecimal("0"));
        }
        if (statisticsDay1.getAdrDownCountDay60() == null) {
            statisticsDay1.setAdrDownCountDay60(new BigDecimal("0"));
        }


        //是否超过当前价格
        boolean isOverCurPriceDay1 = false,isOverCurPriceDay2 = false,isOverCurPriceDay3 = false,isOverCurPriceDay5 = false;
        boolean isOverCurPriceDay10 = false,isOverCurPriceDay20 = false,isOverCurPriceDay30 = false,isOverCurPriceDay60 = false;
        for (SuperDropBounceRs superDropBounceRs : rs) {
            System.out.println("返回结果:" + JSON.toJSONString(superDropBounceRs));
            if (superDropBounceRs.getAdrSum1() != null) {
                statisticsDay1.setRsCountDay1(statisticsDay1.getRsCountDay1().add(new BigDecimal("1")));
                if (superDropBounceRs.getAdrSum1().compareTo(new BigDecimal("0")) > 0) {
                    statisticsDay1.setAdrUpCountDay1(statisticsDay1.getAdrUpCountDay1().add(new BigDecimal("1")));
                    isOverCurPriceDay1 = true;
                } else {
                    statisticsDay1.setAdrDownCountDay1(statisticsDay1.getAdrDownCountDay1().add(new BigDecimal("1")));
                }
            }
            if (superDropBounceRs.getAdrSum2() != null) {
                statisticsDay1.setRsCountDay2(statisticsDay1.getRsCountDay2().add(new BigDecimal("1")));
                if (superDropBounceRs.getAdrSum2().compareTo(new BigDecimal("0")) > 0) {
                    statisticsDay1.setAdrUpCountDay2(statisticsDay1.getAdrUpCountDay2().add(new BigDecimal("1")));
                    isOverCurPriceDay2 = true;
                } else {
                    statisticsDay1.setAdrDownCountDay2(statisticsDay1.getAdrDownCountDay2().add(new BigDecimal("1")));
                }
            }
            if (superDropBounceRs.getAdrSum3() != null) {
                statisticsDay1.setRsCountDay3(statisticsDay1.getRsCountDay3().add(new BigDecimal("1")));
                if (superDropBounceRs.getAdrSum3().compareTo(new BigDecimal("0")) > 0) {
                    statisticsDay1.setAdrUpCountDay3(statisticsDay1.getAdrUpCountDay3().add(new BigDecimal("1")));
                    isOverCurPriceDay3 = true;
                } else {
                    statisticsDay1.setAdrDownCountDay3(statisticsDay1.getAdrDownCountDay3().add(new BigDecimal("1")));
                }
            }
            if (superDropBounceRs.getAdrSum5() != null) {
                statisticsDay1.setRsCountDay5(statisticsDay1.getRsCountDay5().add(new BigDecimal("1")));
                if (superDropBounceRs.getAdrSum5().compareTo(new BigDecimal("0")) > 0) {
                    statisticsDay1.setAdrUpCountDay5(statisticsDay1.getAdrUpCountDay5().add(new BigDecimal("1")));
                    isOverCurPriceDay5 = true;
                } else {
                    statisticsDay1.setAdrDownCountDay5(statisticsDay1.getAdrDownCountDay5().add(new BigDecimal("1")));
                }
            }
            if (superDropBounceRs.getAdrSum10() != null) {
                statisticsDay1.setRsCountDay10(statisticsDay1.getRsCountDay10().add(new BigDecimal("1")));
                if (superDropBounceRs.getAdrSum10().compareTo(new BigDecimal("0")) > 0) {
                    statisticsDay1.setAdrUpCountDay10(statisticsDay1.getAdrUpCountDay10().add(new BigDecimal("1")));
                    isOverCurPriceDay10 = true;
                } else {
                    statisticsDay1.setAdrDownCountDay10(statisticsDay1.getAdrDownCountDay10().add(new BigDecimal("1")));
                }
            }
            if (superDropBounceRs.getAdrSum20() != null) {
                statisticsDay1.setRsCountDay20(statisticsDay1.getRsCountDay20().add(new BigDecimal("1")));
                if (superDropBounceRs.getAdrSum20().compareTo(new BigDecimal("0")) > 0) {
                    statisticsDay1.setAdrUpCountDay20(statisticsDay1.getAdrUpCountDay20().add(new BigDecimal("1")));
                    isOverCurPriceDay20 = true;
                } else {
                    statisticsDay1.setAdrDownCountDay20(statisticsDay1.getAdrDownCountDay20().add(new BigDecimal("1")));
                }
            }
            if (superDropBounceRs.getAdrSum30() != null) {
                statisticsDay1.setRsCountDay30(statisticsDay1.getRsCountDay30().add(new BigDecimal("1")));
                if (superDropBounceRs.getAdrSum30().compareTo(new BigDecimal("0")) > 0) {
                    statisticsDay1.setAdrUpCountDay30(statisticsDay1.getAdrUpCountDay30().add(new BigDecimal("1")));
                    isOverCurPriceDay30 = true;
                } else {
                    statisticsDay1.setAdrDownCountDay30(statisticsDay1.getAdrDownCountDay30().add(new BigDecimal("1")));
                }
            }
            if (superDropBounceRs.getAdrSum60() != null) {
                statisticsDay1.setRsCountDay60(statisticsDay1.getRsCountDay60().add(new BigDecimal("1")));
                if (superDropBounceRs.getAdrSum60().compareTo(new BigDecimal("0")) > 0) {
                    statisticsDay1.setAdrUpCountDay60(statisticsDay1.getAdrUpCountDay60().add(new BigDecimal("1")));
                    isOverCurPriceDay60 = true;
                } else {
                    statisticsDay1.setAdrDownCountDay60(statisticsDay1.getAdrDownCountDay60().add(new BigDecimal("1")));
                }
            }
        }
        if(isOverCurPriceDay1){
            
        }

        return rs;
    }

    /**
     * 均线突破
     *
     * @param curDate               日期
     * @param weekFiter             星期几过滤
     * @param goodRateCurDayLimitUp
     */
    private static MaBreakUpRs maBreakUp(String curDate, String weekFiter, BigDecimal goodRateCurDayLimitUp) {
        //过滤器：星期n
        String curDateWeek = DateUtil.getWeekByYyyyMmDd(curDate, DateUtil.YYYY_MM_DD);
        if (StringUtils.isNotBlank(weekFiter) && !curDateWeek.equals(weekFiter)) {
            System.out.println("过滤器：星期[" + curDateWeek + "]不查询");
            return null;
        }
        MaBreakUpCond condition = new MaBreakUpCond();
        List<String> dateListBefore = RankStockCommpanyDao.findListDateBefore(new DateCond(curDate, 10));
//        System.out.println(JSON.toJSONString(dateListBefore));
        List<String> dateListAfter = RankStockCommpanyDao.findListDateAfter(new DateCond(curDate, 10));
//        System.out.println(JSON.toJSONString(dateListAfter));
        String curDaySub1 = dateListBefore.get(0);
        String curDayAdd1 = dateListAfter.get(0);
        String curDayAdd2 = dateListAfter.get(1);
        String curDayAdd3 = dateListAfter.get(2);
        condition.setCurDaySub1(curDaySub1);
        condition.setCurDay(curDate);
        condition.setCurDayAdd1(curDayAdd1);
        condition.setCurDayAdd2(curDayAdd2);
        condition.setCurDayAdd3(curDayAdd3);
        condition.setF139(DB_RANK_BIZ_F139_BAN_KUAI);
//        condition.setType_name(ST_BIZ_TYPE_YOU_SE_JIN_SHU);
        condition.setF20(200 * 00000000l);//市值
        condition.setGoodRateCurDayLimitDown(new BigDecimal("0"));//当日涨幅下限
        condition.setGoodRateCurDayLimitUp(goodRateCurDayLimitUp);//当日涨幅上限
        condition.setGoodRateDaySub1(new BigDecimal("0"));
//        System.out.println("查询条件：" + JSON.toJSONString(condition));
        List<MaBreakUpRs> rs = RankStockCommpanyDao.findListMaBreakUpCond(condition);
        if (rs == null || rs.size() == 0) {
            System.out.println("返回结果为空！" + JSON.toJSONString(condition));
            return null;
        }
        int countUp1 = 0;//上涨个数
        int countUp2 = 0;//上涨个数
        int countUp3 = 0;//上涨个数
        int countDown1 = 0;//下跌个数
        int countDown2 = 0;//下跌个数
        int countDown3 = 0;//下跌个数
        int countUpDayAdd1Week5 = 0;//上涨个数-周n
        int countUpDayAdd1Week4 = 0;//上涨个数-周n
        int countUpDayAdd1Week3 = 0;//上涨个数-周n
        int countUpDayAdd1Week2 = 0;//上涨个数-周n
        int countUpDayAdd1Week1 = 0;//上涨个数-周n
        int countUpDayAdd2Week5 = 0;//上涨个数-周n
        int countUpDayAdd2Week4 = 0;//上涨个数-周n
        int countUpDayAdd2Week3 = 0;//上涨个数-周n
        int countUpDayAdd2Week2 = 0;//上涨个数-周n
        int countUpDayAdd2Week1 = 0;//上涨个数-周n
        int countUpDayAdd3Week5 = 0;//上涨个数-周n
        int countUpDayAdd3Week4 = 0;//上涨个数-周n
        int countUpDayAdd3Week3 = 0;//上涨个数-周n
        int countUpDayAdd3Week2 = 0;//上涨个数-周n
        int countUpDayAdd3Week1 = 0;//上涨个数-周n
        int countDownDayAdd1Week5 = 0;//下跌个数-周n
        int countDownDayAdd1Week4 = 0;//下跌个数-周n
        int countDownDayAdd1Week3 = 0;//下跌个数-周n
        int countDownDayAdd1Week2 = 0;//下跌个数-周n
        int countDownDayAdd1Week1 = 0;//下跌个数-周n
        int countDownDayAdd2Week5 = 0;//下跌个数-周n
        int countDownDayAdd2Week4 = 0;//下跌个数-周n
        int countDownDayAdd2Week3 = 0;//下跌个数-周n
        int countDownDayAdd2Week2 = 0;//下跌个数-周n
        int countDownDayAdd2Week1 = 0;//下跌个数-周n
        int countDownDayAdd3Week5 = 0;//下跌个数-周n
        int countDownDayAdd3Week4 = 0;//下跌个数-周n
        int countDownDayAdd3Week3 = 0;//下跌个数-周n
        int countDownDayAdd3Week2 = 0;//下跌个数-周n
        int countDownDayAdd3Week1 = 0;//下跌个数-周n
        for (MaBreakUpRs maBreakUp : rs) {
//            System.out.println(JSON.toJSONString(maBreakUp));
            //统计个数：当前日期的后n天涨跌
            if (maBreakUp.getAdrDaySum1() == null) {
                continue;
            }
            if (maBreakUp.getAdrDaySum1().compareTo(new BigDecimal("0")) > 0) {
                countUp1++;
                if (curDateWeek.equals("五")) {
                    countUpDayAdd1Week5++;
                }
                if (curDateWeek.equals("四")) {
                    countUpDayAdd1Week4++;
                }
                if (curDateWeek.equals("三")) {
                    countUpDayAdd1Week3++;
                }
                if (curDateWeek.equals("二")) {
                    countUpDayAdd1Week2++;
                }
                if (curDateWeek.equals("一")) {
                    countUpDayAdd1Week1++;
                }
            } else {
                countDown1++;
                if (curDateWeek.equals("五")) {
                    countDownDayAdd1Week5++;
                }
                if (curDateWeek.equals("四")) {
                    countDownDayAdd1Week4++;
                }
                if (curDateWeek.equals("三")) {
                    countDownDayAdd1Week3++;
                }
                if (curDateWeek.equals("二")) {
                    countDownDayAdd1Week2++;
                }
                if (curDateWeek.equals("一")) {
                    countDownDayAdd1Week1++;
                }
            }
            if (maBreakUp.getAdrDaySum2().compareTo(new BigDecimal("0")) > 0) {
                countUp2++;
                if (curDateWeek.equals("五")) {
                    countUpDayAdd2Week5++;
                }
                if (curDateWeek.equals("四")) {
                    countUpDayAdd2Week4++;
                }
                if (curDateWeek.equals("三")) {
                    countUpDayAdd2Week3++;
                }
                if (curDateWeek.equals("二")) {
                    countUpDayAdd2Week2++;
                }
                if (curDateWeek.equals("一")) {
                    countUpDayAdd2Week1++;
                }
            } else {
                countDown2++;
                if (curDateWeek.equals("五")) {
                    countDownDayAdd2Week5++;
                }
                if (curDateWeek.equals("四")) {
                    countDownDayAdd2Week4++;
                }
                if (curDateWeek.equals("三")) {
                    countDownDayAdd2Week3++;
                }
                if (curDateWeek.equals("二")) {
                    countDownDayAdd2Week2++;
                }
                if (curDateWeek.equals("一")) {
                    countDownDayAdd2Week1++;
                }
            }
            if (maBreakUp.getAdrDaySum3().compareTo(new BigDecimal("0")) > 0) {
                countUp3++;
                if (curDateWeek.equals("五")) {
                    countUpDayAdd3Week5++;
                }
                if (curDateWeek.equals("四")) {
                    countUpDayAdd3Week4++;
                }
                if (curDateWeek.equals("三")) {
                    countUpDayAdd3Week3++;
                }
                if (curDateWeek.equals("二")) {
                    countUpDayAdd3Week2++;
                }
                if (curDateWeek.equals("一")) {
                    countUpDayAdd3Week1++;
                }
            } else {
                countDown3++;
                if (curDateWeek.equals("五")) {
                    countDownDayAdd3Week5++;
                }
                if (curDateWeek.equals("四")) {
                    countDownDayAdd3Week4++;
                }
                if (curDateWeek.equals("三")) {
                    countDownDayAdd3Week3++;
                }
                if (curDateWeek.equals("二")) {
                    countDownDayAdd3Week2++;
                }
                if (curDateWeek.equals("一")) {
                    countDownDayAdd3Week1++;
                }
            }
        }
        System.out.println("当前日期:" + curDate + ":" + DateUtil.getWeekByYyyyMmDd(curDate, DateUtil.YYYY_MM_DD));
        System.out.println("后1日" + curDayAdd1 + DateUtil.getWeekByYyyyMmDd(curDayAdd1, DateUtil.YYYY_MM_DD) + "涨跌比:" + countUp1 + ":" + countDown1 + ",上涨率：" + new BigDecimal(countUp1).divide(new BigDecimal(rs.size()), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")));
        System.out.println("后2日" + curDayAdd2 + DateUtil.getWeekByYyyyMmDd(curDayAdd2, DateUtil.YYYY_MM_DD) + "涨跌比:" + countUp2 + ":" + countDown2 + ",上涨率：" + new BigDecimal(countUp2).divide(new BigDecimal(rs.size()), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")));
        System.out.println("后3日" + curDayAdd3 + DateUtil.getWeekByYyyyMmDd(curDayAdd3, DateUtil.YYYY_MM_DD) + "涨跌比:" + countUp3 + ":" + countDown3 + ",上涨率：" + new BigDecimal(countUp3).divide(new BigDecimal(rs.size()), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")));
        MaBreakUpRs maBreakUpRs = new MaBreakUpRs();
        maBreakUpRs.setCurDayAdd1UpCount(countUp1);
        maBreakUpRs.setCurDayAdd2UpCount(countUp2);
        maBreakUpRs.setCurDayAdd3UpCount(countUp3);
        maBreakUpRs.setCurDayAdd1DownCount(countDown1);
        maBreakUpRs.setCurDayAdd2DownCount(countDown2);
        maBreakUpRs.setCurDayAdd3DownCount(countDown3);
        return maBreakUpRs;
    }

    /**
     * 查询-统计数据-股票分组
     *
     * @param typeName
     * @param begDate
     * @param endDate
     */
    private static void findListTongJj(String typeName, String begDate, String endDate) {
        BigDecimal limitPe = new BigDecimal("50");//提醒信息：限定市盈率
        boolean isShowPeHighInfo = true;//是否显示市盈率高的记录

        RankStComTjCond condition = new RankStComTjCond();
        condition.setBegDate(begDate);//n天
        condition.setEndDate(endDate);
        condition.setPlate(Content.ST_PLATE_F139_AG);//只查询主板的
//        condition.setMarketValueMin(new BigDecimal("200"));//市值限定
//        condition.setMarketValueMax(new BigDecimal("9999"));//市值限定
        condition.setType_name(typeName);/**    业务板块：	电力行业-BK0428	券商信托-BK0473	银行-BK0475	医疗行业-BK0727	医药制造-BK0465	化工行业-BK0538	酿酒行业-BK0477	化肥行业-BK0731	民航机场-BK0420	环保工程	**/
        condition.setOrderBy(" code ");
        System.out.println("查询条件：" + JSON.toJSONString(condition));
        List<RankStComTjRs> rs = RankStockCommpanyDao.findListTongjiGroup(condition);
        if (rs == null) {
            System.out.println("返回结果为空！");
            return;
        }
        int orderNum = 0;
        for (RankStComTjRs rankStComTjRs : rs) {
            if (!isShowPeHighInfo) {
                if (rankStComTjRs.getLastPe() != null && rankStComTjRs.getLastPe().compareTo(limitPe) >= 0) {
//                    System.out.println("!!!市盈率过高不显示:" + rankStComTjRs.getLastPe());
                    continue;
                }
            }
            orderNum++;
            System.out.print(orderNum + ",");
            System.out.print(JSON.toJSONString(rankStComTjRs));
//            System.out.print(rankStComTjRs.getCode());
//            System.out.print("\t");
//            System.out.print(rankStComTjRs.getName());
//            System.out.print("\t");
//            System.out.print(rankStComTjRs.getZhangfuSum());
//            System.out.print(rankStComTjRs.getLastPe());
//            System.out.print(rankStComTjRs.getLastMarketValue());
            //市盈率过高提醒
            if (rankStComTjRs.getLastPe() != null && rankStComTjRs.getLastPe().compareTo(limitPe) >= 0) {
                System.out.print("!!!市盈率过高:" + rankStComTjRs.getLastPe());
            }
            System.out.println();
        }
    }

    /**
     * 查询昨日主题排名
     *
     * @param endCount
     */
    private static List<RankBizDataDiff> listBiz(int endCount) {
        //http://28.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112408110589206747254_1616379873172&pn=1&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:90+t:2+f:!50&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222&_=1616379873199
        long curTime = System.currentTimeMillis();
        StringBuffer urlParam = new StringBuffer();
//        String url = "http://api.fund.eastmoney.com/ztjj/GetZTJJList";
//        urlParam.append("callback=jQuery311015658602123786958_1591838943711&callbackname=fundData");
//        urlParam.append("&st="+findDateType);//查询类型
//        urlParam.append("&_=1614523183291");
        String url = "http://28.push2.eastmoney.com/api/qt/clist/get";
        urlParam.append("cb=jQuery112408110589206747254_" + curTime +
                "&pn=1" +//页数
                "&pz=" + endCount +//每页数量
                "&po=1" +//pageorder:页面排序：0-正序；1-倒序
                "&np=1" +
                "&ut=bd1d9ddb04089700cf9c27f6f7426281" +
                "&fltt=2" +//浮点数精度
                "&invt=3" +//显示格式：-；0.0
                "&fid=f3" +//排序字段
                "&fs=m:90+t:2+f:!50" +
                "&fields=" +
                "f1,f2,f3,f4,f5,f6,f7,f8,f9," +
                "f10,f11,f12,f13,f14,f15,f16,f17,f18,f19," +
                "f20,f21,f22,f23,f24,f25,f26,f27,f28,f29," +
                "f30,f31,f32,f33,f34,f35,f36,f37,f38,f39," +
                "f60,f61,f62,f63,f64,f65,f66,f67,f68,f69," +
                "f70,f71,f72,f73,f74,f75,f76,f77,f78,f79," +
                "f80,f81,f82,f83,f84,f85,f86,f87,f88,f89," +
                "f90,f91,f92,f93,f94,f95,f96,f97,f98,f99," +
                "f100,f101,f102,f103,f104,f105,f106,f107,f108,f109" + "," +
                "f110,f111,f112,f113,f114,f115,f116,f117,f118,f119" + "," +
                "f120,f121,f122,f123,f124,f125,f126,f127,f128,f129" + "," +
                "f130,f131,f132,f133,f134,f135,f136,f137,f138,f139" + "," +
                "f140,f141,f142,f143,f144,f145,f146,f147,f148,f149" + "," +
                "f150,f151,f152,f153,f154,f155,f156,f157,f158,f159" + "," +
                "f160,f161,f162,f163,f164,f165,f166,f167,f168,f169" + "," +
                "f170,f171,f172,f173,f174,f175,f176,f177,f178,f179" + "," +
                "f180,f181,f182,f183,f184,f185,f186,f187,f188,f189" + "," +
                "f190,f191,f192,f193,f194,f195,f196,f197,f198,f199" + "," +
                "f200,f201,f202,f203,f204,f205,f206,f207,f208,f209" + "," +
                "f210,f211,f212,f213,f214,f215,f216,f217,f218,f219" + "," +
                "f220,f221,f222,f223,f224,f225,f226,f227,f228,f229" +
                "&_=" + curTime);
        String rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);
        if (rs == null) {
            return null;
        }
        if (rs.startsWith("jQuery")) {
            rs = rs.substring(rs.indexOf("{"));
        }
        if (rs.endsWith(");")) {
            rs = rs.substring(0, rs.length() - 2);
        }
//        System.out.println(rs);//返回结果
        JSONObject rsJsonObj = JSONObject.parseObject(rs);
        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
        List<RankBizDataDiff> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankBizDataDiff.class);
//        for (RankBizDataDiff row : rankBizDataDiffList) {
//            System.out.println(JSON.toJSON(row));//每个行业一行数据
//        }
        return rankBizDataDiffList;
    }

}
