package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.KlineDao;
import ttjj.dto.Kline;
import ttjj.service.KlineService;
import utils.Content;
import utils.DateUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static utils.Content.*;


/**
 * k线
 */
public class KlineDemo {
    public static void main(String[] args) {
        //  插入常用指数k线
        boolean isAddMinuteKline = true;//是否添加分钟级别K线
        String klt = Content.KLT_101;//klt=5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
        int lmt = 1;
        int addDaysMax = 0;//最多增加的天数
        int year = DateUtil.getCurYear();//2021
        int month = DateUtil.getCurMonth();//DateUtil.getCurMonth()
        int day = DateUtil.getCurDay();//DateUtil.getCurDay()
        String klineType = KLINE_TYPE_INDEX;
        addZhishuKline(isAddMinuteKline, klt, lmt, addDaysMax, year, month, day, klineType);

////        // 查询k线
//        findKline();

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

    /**
     * 插入常用指数k线-大周期：日、周、月、年
     */
    private static void addZhishuKline(boolean isAddMinuteKline, String klt, int lmt, int addDaysMax, int year, int month, int day, String klineType) {
        //插入常用指数k线-大周期：日、周、月、年
        Map<String, String> zhishuMap = Content.getZhishuMap();//        Map<String, String>  zhishuMap = new HashMap<>();zhishuMap.put("000001","上证指数");//特定测试
        Set<String> zhishuList = zhishuMap.keySet();
        for (String zqdm : zhishuList) {
            for (int i = 0; i <= addDaysMax; i++) {
                String begDate = DateUtil.getDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, year, month, day, i);//查询新增交易的开始时间
                String endDate = begDate;
//            //  增加大周期k线
                addKlineDaZhouQi(zqdm, lmt, klt, begDate, endDate);

                if (isAddMinuteKline) {
                    //按照日期的分钟线（1分钟；5分钟；15分钟；30分钟；60分钟；120分钟），插入k线
                    addKlineMinute(zqdm, lmt, Content.KLT_1, begDate, endDate, klineType);
                    addKlineMinute(zqdm, lmt, Content.KLT_5, begDate, endDate, klineType);
                    addKlineMinute(zqdm, lmt, Content.KLT_15, begDate, endDate, klineType);
                    addKlineMinute(zqdm, lmt, Content.KLT_30, begDate, endDate, klineType);
                    addKlineMinute(zqdm, lmt, Content.KLT_60, begDate, endDate, klineType);
                    addKlineMinute(zqdm, lmt, Content.KLT_120, begDate, endDate, klineType);
                }

                //计算净值
                updateNet(zqdm, lmt, klt, begDate, endDate, klineType);
            }
        }
    }

    /**
     * 按照日期的分钟线（5分钟；15分钟；30分钟；60分钟；120分钟），插入k线
     *
     * @param zqdm
     * @param lmt
     * @param klt
     * @param begDate
     * @param endDate
     * @param klineType
     */
    public static void addKlineMinute(String zqdm, int lmt, String klt, String begDate, String endDate, String klineType) {
        /**    创业板50-159949 HS300ETF-510310 50ETF-510050	新汽车-515030	芯片ETF-159995	酒ETF-512690	医疗ETF-512170 	光伏ETF-515790	稀土ETF-516780	有色50-159880	煤炭ETF-515220 军工ETF-512660		**/
        String klineRs = KlineService.klineRsStrHttpDfcf(zqdm, lmt, klt, true, begDate, endDate, klineType);
//        System.out.println("开始日期:" + date + "，结束日期:" + date + "，周期:" + klt + "，klineRs:" + klineRs);
//        System.out.println("klines:"+JSON.toJSONString(klines));

        if (StringUtils.isBlank(klineRs)) {
            System.out.println("k线查询结果为空！");
            return;
        }

        JSONObject szzzMonthJson = JSON.parseObject(klineRs);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
        String zqmc = szzzMonthDataJson.getString("name");
//        System.out.println("证券名称：" + zqmc);
        if (szzzMonthDataJson == null || !szzzMonthDataJson.containsKey("klines")) {
            System.out.println("klines数据异常：" + JSON.toJSONString(szzzMonthDataJson));
            return;
        }

        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        if (klines == null || klines.size() <= 0) {
            System.out.println("k线数据为空！");
            return;
        }

        Kline kline = new Kline();
        kline.setKlt(klt);
        kline.setRs(klineRs);
        kline.setZqmc(zqmc);
        kline.setZqdm(zqdm);
        kline.setKtime(endDate);
        /**
         * 插入数据库-K线
         */
        KlineDao.insert(kline);
    }

    public static void updateNet(String zqdm, int lmt, String klt, String begDate, String endDate, String klineType) {
        Kline kline = new Kline();
        kline.setKlt(klt);
        kline.setZqdm(zqdm);
        kline.setKtime(endDate);

        //计算净值
        Map<String, BigDecimal> netMap5 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, klt, false, "", endDate, klineType);
        kline.setNET_MA_5(netMap5.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_5(netMap5.get(Content.keyRsMin));
        kline.setNET_MAX_5(netMap5.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_5(netMap5.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_5(netMap5.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap10 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, klt, false, "", endDate, klineType);
        kline.setNET_MA_10(netMap10.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_10(netMap10.get(Content.keyRsMin));
        kline.setNET_MAX_10(netMap10.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_10(netMap10.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_10(netMap10.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap20 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, klt, false, "", endDate, klineType);
        kline.setNET_MA_20(netMap20.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_20(netMap20.get(Content.keyRsMin));
        kline.setNET_MAX_20(netMap20.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_20(netMap20.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_20(netMap20.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap30 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, klt, false, "", endDate, klineType);
        kline.setNET_MA_30(netMap30.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_30(netMap30.get(Content.keyRsMin));
        kline.setNET_MAX_30(netMap30.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_30(netMap30.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_30(netMap30.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap60 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, klt, false, "", endDate, klineType);
        kline.setNET_MA_60(netMap60.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_60(netMap60.get(Content.keyRsMin));
        kline.setNET_MAX_60(netMap60.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_60(netMap60.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_60(netMap60.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap120 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, klt, false, "", endDate, klineType);
        kline.setNET_MA_120(netMap120.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_120(netMap120.get(Content.keyRsMin));
        kline.setNET_MAX_120(netMap120.get(Content.keyRsMax));
        kline.setNET_MIN_CLOS_120(netMap120.get(Content.keyRsNetCloseMin));
        kline.setNET_MAX_CLOS_120(netMap120.get(Content.keyRsNetCloseMax));
        Map<String, BigDecimal> netMap250 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, klt, false, "", endDate, klineType);
        kline.setNET_MA_250(netMap250.get(Content.keyRsNetCloseAvg));
        kline.setNET_MIN_250(netMap250.get(Content.keyRsMin));
        kline.setNET_MAX_250(netMap250.get(Content.keyRsMax));
        BigDecimal NET_MAX_CLOS_250 = netMap250.get(Content.keyRsNetCloseMax);
        if (NET_MAX_CLOS_250 == null) {
            System.out.println("均值异常！！！！！！！！！！！！！！！！！！！！");
            kline.setNET_MIN_CLOS_250(new BigDecimal("0"));
        } else {
            kline.setNET_MIN_CLOS_250(netMap250.get(Content.keyRsNetCloseMin));
        }
        KlineDao.updateNet(kline);
    }

    /**
     * 增加大周期k线
     *
     * @param zqdm
     * @param lmt
     * @param klt
     * @param begDate
     * @param endDate
     */
    private static void addKlineDaZhouQi(String zqdm, int lmt, String klt, String begDate, String endDate) {
        //  插入数据库-K线-大周期
        List<Kline> klines = KlineService.kline(zqdm, lmt, klt, true, begDate, endDate, KLINE_TYPE_INDEX);
        if (klines == null || klines.size() == 0) {
            return;
        }
        for (Kline kline : klines) {
            if (KLT_101.equals(klt)) {
                kline.setMonth(DateUtil.getYearMonth(endDate, DateUtil.YYYY_MM_DD));
                kline.setWeekYear(DateUtil.getYearWeek(endDate, DateUtil.YYYY_MM_DD));
                kline.setWeek(DateUtil.getWeekByYyyyMmDd(endDate, DateUtil.YYYY_MM_DD));
            }
            if (KLT_102.equals(klt)) {
                kline.setMonth(DateUtil.getYearMonth(endDate, DateUtil.YYYY_MM_DD));
                kline.setWeekYear(DateUtil.getYearWeek(endDate, DateUtil.YYYY_MM_DD));
            }
            if (KLT_103.equals(klt)) {
                kline.setMonth(DateUtil.getYearMonth(endDate, DateUtil.YYYY_MM_DD));
            }

            /**
             * 插入数据库-K线
             */
            System.out.println(JSON.toJSONString(kline));
            KlineDao.insert(kline);

            //计算净值
            Map<String, BigDecimal> netMap5 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, klt, false, begDate, endDate, KLINE_TYPE_INDEX);
            kline.setNET_MA_5(netMap5.get(Content.keyRsNetCloseAvg));
            kline.setNET_MIN_5(netMap5.get(Content.keyRsMin));
            kline.setNET_MAX_5(netMap5.get(Content.keyRsMax));
            kline.setNET_MIN_CLOS_5(netMap5.get(Content.keyRsNetCloseMin));
            kline.setNET_MAX_CLOS_5(netMap5.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap10 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, klt, false, begDate, endDate, KLINE_TYPE_INDEX);
            kline.setNET_MA_10(netMap10.get(Content.keyRsNetCloseAvg));
            kline.setNET_MIN_10(netMap10.get(Content.keyRsMin));
            kline.setNET_MAX_10(netMap10.get(Content.keyRsMax));
            kline.setNET_MIN_CLOS_10(netMap10.get(Content.keyRsNetCloseMin));
            kline.setNET_MAX_CLOS_10(netMap10.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap20 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, klt, false, begDate, endDate, KLINE_TYPE_INDEX);
            kline.setNET_MA_20(netMap20.get(Content.keyRsNetCloseAvg));
            kline.setNET_MIN_20(netMap20.get(Content.keyRsMin));
            kline.setNET_MAX_20(netMap20.get(Content.keyRsMax));
            kline.setNET_MIN_CLOS_20(netMap20.get(Content.keyRsNetCloseMin));
            kline.setNET_MAX_CLOS_20(netMap20.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap30 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, klt, false, begDate, endDate, KLINE_TYPE_INDEX);
            kline.setNET_MA_30(netMap30.get(Content.keyRsNetCloseAvg));
            kline.setNET_MIN_30(netMap30.get(Content.keyRsMin));
            kline.setNET_MAX_30(netMap30.get(Content.keyRsMax));
            kline.setNET_MIN_CLOS_30(netMap30.get(Content.keyRsNetCloseMin));
            kline.setNET_MAX_CLOS_30(netMap30.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap60 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, klt, false, begDate, endDate, KLINE_TYPE_INDEX);
            kline.setNET_MA_60(netMap60.get(Content.keyRsNetCloseAvg));
            kline.setNET_MIN_60(netMap60.get(Content.keyRsMin));
            kline.setNET_MAX_60(netMap60.get(Content.keyRsMax));
            kline.setNET_MIN_CLOS_60(netMap60.get(Content.keyRsNetCloseMin));
            kline.setNET_MAX_CLOS_60(netMap60.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap120 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, klt, false, begDate, endDate, KLINE_TYPE_INDEX);
            kline.setNET_MA_120(netMap120.get(Content.keyRsNetCloseAvg));
            kline.setNET_MIN_120(netMap120.get(Content.keyRsMin));
            kline.setNET_MAX_120(netMap120.get(Content.keyRsMax));
            kline.setNET_MIN_CLOS_120(netMap120.get(Content.keyRsNetCloseMin));
            kline.setNET_MAX_CLOS_120(netMap120.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap250 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, klt, false, begDate, endDate, KLINE_TYPE_INDEX);
            kline.setNET_MA_250(netMap250.get(Content.keyRsNetCloseAvg));
            kline.setNET_MIN_250(netMap250.get(Content.keyRsMin));
            kline.setNET_MAX_250(netMap250.get(Content.keyRsMax));
            kline.setNET_MIN_CLOS_250(netMap250.get(Content.keyRsNetCloseMin));
            kline.setNET_MAX_CLOS_250(netMap250.get(Content.keyRsNetCloseMax));
            KlineDao.updateNet(kline);
        }
    }

    /**
     * 增加大周期k线-只插入http返回结果
     *
     * @param zhishu
     * @param klt
     */
    private static void addKlineDaZhouQiOnlyHttpRs(String zhishu, String klt) {
        int lmt = 1000000;
        String begDate = "";//查询新增交易的开始时间
        String endDate = "";
        begDate = "0";
        endDate = "20500101";
        String zqdm = zhishu;
//  只插入http返回结果
        String klineRs = KlineService.klineRsStrHttpDfcf(zqdm, lmt, klt, true, begDate, endDate, KLINE_TYPE_STOCK);
        System.out.println("开始日期:" + begDate + "，结束日期:" + endDate + "，周期:" + klt + "，klines.size():" + klineRs);
//        System.out.println("klines:"+JSON.toJSONString(klines));

        if (StringUtils.isBlank(klineRs)) {
            System.out.println("k线查询结果为空！");
        }

        JSONObject szzzMonthJson = JSON.parseObject(klineRs);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
        String zqmc = szzzMonthDataJson.getString("name");
        System.out.println("证券名称：" + zqmc);
        if (szzzMonthDataJson == null || !szzzMonthDataJson.containsKey("klines")) {
            System.out.println("klines数据异常：" + JSON.toJSONString(szzzMonthDataJson));
        }

        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        if (klines == null || klines.size() <= 0) {
            System.out.println("k线数据为空！");
        }

        Kline kline = new Kline();
        kline.setKlt(klt);
        kline.setRs(klineRs);
        kline.setZqmc(zqmc);
        kline.setZqdm(zqdm);
        kline.setKtime(begDate);
        /**
         * 插入数据库-K线
         */
        KlineDao.insert(kline);


    }
}
