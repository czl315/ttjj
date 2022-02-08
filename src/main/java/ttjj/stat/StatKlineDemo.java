package ttjj.stat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.KlineDao;
import ttjj.dao.RankStockCommpanyDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.*;
import ttjj.service.KlineService;
import utils.Content;
import utils.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static utils.Content.*;


/**
 * k线
 */
public class StatKlineDemo {
    public static void main(String[] args) {

//        // 统计涨跌次数-根据每月中的日期
        String zqmc = ZHISHU_NAME_399673;//ZHISHU_NAME_399673 ZHISHU_NAME_000001
        statAdrCountByDay(zqmc);

    }

    /**
     * 统计涨跌次数-根据每月中的日期
     */
    private static void statAdrCountByDay(String zqmc) {
        String begDate = "2020-01-01";//开始时间
        String endDate = "2021-12-31";//DateUtil.getToday(DateUtil.YYYY_MM_DD)
        String klt = KLT_101;
        List<String> stCodeList = new ArrayList<>();
        stCodeList.add(zqmc);
        BigDecimal adrMin = new BigDecimal("0");

        StatCondStAdrCountKline condition = new StatCondStAdrCountKline();//查询条件
        condition.setKlt(klt);
        condition.setBegDate(begDate);
        condition.setEndDate(endDate);
        condition.setStCodeList(stCodeList);


        List<StatRsStAdrCountKline> rsAll = KlineDao.findListStatStAdrCount(condition); //  查询-涨跌次数-所有
        for (StatRsStAdrCountKline r : rsAll) {
//            System.out.println(JSON.toJSONString(r));
        }
        condition.setAdrMin(adrMin);
        List<StatRsStAdrCountKline> rsGt0 = KlineDao.findListStatStAdrCount(condition); //  查询-涨跌次数-涨跌大于n
        for (StatRsStAdrCountKline r : rsGt0) {
//            System.out.println(JSON.toJSONString(r));
        }

        System.out.println(zqmc);

        List<String> days = new ArrayList<>();

        days = Arrays.asList("01,02,03,04,05".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("06,07,08,09,10".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("07".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("08".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("11,12,13,14,15".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("16,17,18,19,20".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("21,22,23,24,25".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("26,27,28,29,30".split(","));
        staticsPct(rsGt0, rsAll, days);
        days = Arrays.asList("31".split(","));
        staticsPct(rsGt0, rsAll, days);

    }

    /**
     * 统计百分比-涨跌次数
     *
     * @param rsGt0
     * @param rsAll
     * @param days
     */
    private static BigDecimal staticsPct(List<StatRsStAdrCountKline> rsGt0, List<StatRsStAdrCountKline> rsAll, List<String> days) {
        Map<String, BigDecimal> mapRsGt0 = new HashMap();
        Map<String, BigDecimal> mapRsAll = new HashMap();
        for (StatRsStAdrCountKline rsGt0Temp : rsGt0) {
            mapRsGt0.put(rsGt0Temp.getRsDate(), rsGt0Temp.getCount());//大于n的次数s
        }
        for (StatRsStAdrCountKline rsTemp : rsAll) {
            mapRsAll.put(rsTemp.getRsDate(), rsTemp.getCount());//总次数
        }

        List<StatRsStAdrCountKline> rs = new ArrayList<>();
        for (String day : days) {
            StatRsStAdrCountKline statRsStAdrCountKline = new StatRsStAdrCountKline();
            BigDecimal countGt = mapRsGt0.get(day);
            BigDecimal countAll = (mapRsAll.get(day));
            statRsStAdrCountKline.setCountGt(countGt);
            statRsStAdrCountKline.setCountAll(countAll);
            BigDecimal pct = countGt.divide(countAll, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            statRsStAdrCountKline.setPctCount(pct);
            statRsStAdrCountKline.setRsDate(day);
            rs.add(statRsStAdrCountKline);
        }
        BigDecimal conutGtSum = new BigDecimal("0");
        BigDecimal conutAllSum = new BigDecimal("0");
        for (StatRsStAdrCountKline r : rs) {
//            System.out.println(JSON.toJSONString(r));
            conutGtSum = conutGtSum.add(r.getCountGt());
            conutAllSum = conutAllSum.add(r.getCountAll());
        }
        BigDecimal pct = conutGtSum.divide(conutAllSum, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP);
        System.out.println("日期：" + JSON.toJSONString(days) + "，上涨概率:" + pct);

        return pct;
    }

    private static void findKline() {
        String zqdm = "000001";
        String klt = Content.KLT_5;//klt=5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
        int lmt = 1000000;
        int addDaysMax = -100;//最多增加的天数
        int year = DateUtil.getCurYear();//2021
        int month = DateUtil.getCurMonth();//DateUtil.getCurMonth()
        int day = DateUtil.getCurDay();//DateUtil.getCurDay()
        String begDate = DateUtil.getDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, year, month, day, addDaysMax);//查询新增交易的开始时间
        String endDate = DateUtil.getToday(DateUtil.YYYY_MM_DD);
        //查询K线-昨天到今天
        List<Kline> klines = KlineService.kline(zqdm, lmt, klt, true, begDate, endDate, KLINE_TYPE_STOCK);
        klines = klines.stream().filter(e -> e != null).sorted(Comparator.comparing(Kline::getKtime, Comparator.nullsFirst(String::compareTo)).reversed()).collect(Collectors.toList());
        int fromIndex = 0;//需要计算开始位置
        int cjeCountAdd = 0;//成交额增量次数
        int cjeCountSub = 0;//成交额减量次数
        for (Kline kline : klines) {
            fromIndex++;
            //遍历k线
            //如果k线时间大于当前时间，不比较
            long klineTime = DateUtil.getTimeInMillisByDateStr(DateUtil.YYYY_MM_DD_HH_MM_SS, kline.getKtime());
            long curTime = Calendar.getInstance().getTimeInMillis();
            if (klineTime > curTime) {
                System.out.println("如果k线时间大于当前时间，不比较.klineRs:" + JSON.toJSONString(kline));
                continue;
            }
            //如果非今天，退出比较
            long todayTime = DateUtil.getTimeInMillisByDateStr(DateUtil.YYYY_MM_DD_HH_MM_SS, DateUtil.getToday(DateUtil.YYYY_MM_DD_HH_MM_SS).substring(0, 10) + " 00:00:00");
            if (klineTime < todayTime) {
                System.out.println("如果k线时间小于今日时间，退出比较.");
                break;
            }
//            System.out.println("klineRs:" + JSON.toJSONString(kline));

            //查询最小净值、最大净值、均值,k线时间必须小于比较时间
            List<Kline> maKlines = klines.subList(fromIndex, klines.size());
            Map<String, BigDecimal> netMap5 = KlineService.handlerNetMinMaxAvg(Content.MA_15, maKlines);

            //比较均量
            BigDecimal cjeAvg = netMap5.get(Content.keyRsCjeAvg);
            BigDecimal cjeCur = kline.getCje();
            if (cjeCur.compareTo(cjeAvg) > 0) {
                cjeCountAdd++;
                System.out.print("放量(" + cjeCountAdd + "),");
//                System.out.print("当前成交额：" + cjeCur + ",");
//                System.out.print("平均成交额：" + cjeAvg + "。");
            }
            if (cjeCur.compareTo(cjeAvg) == 0) {
                System.out.print("平量,");
//                System.out.print("当前成交额：" + cjeCur + ",");
//                System.out.println("平均成交额：" + cjeAvg + "。");
            }
            if (cjeCur.compareTo(cjeAvg) < 0) {
                cjeCountSub++;
                System.out.print("缩量(" + cjeCountSub + "),");
//                System.out.print("当前成交额：" + cjeCur + ",");
//                System.out.print("平均成交额：" + cjeAvg + "。");
            }

            //比较均价
            BigDecimal netAvg = netMap5.get(Content.keyRsNetCloseAvg);
            BigDecimal netCur = kline.getCloseAmt();
            if (netCur.compareTo(netAvg) > 0) {
                System.out.print("上涨,");
                System.out.print("当前价格：" + netCur + ",");
                System.out.print("平均价格：" + netAvg + "。");
            }
            if (netCur.compareTo(netAvg) == 0) {
                System.out.print("平价,");
                System.out.print("当前价格：" + netCur + ",");
                System.out.print("平均价格：" + netAvg + "。");
            }
            if (netCur.compareTo(netAvg) < 0) {
                System.out.print("下跌,");
                System.out.print("当前价格：" + netCur + ",");
                System.out.print("平均价格：" + netAvg + "。");
            }
            System.out.println(kline.getKtime());

        }
    }
}
