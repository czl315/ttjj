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

import java.util.Map;
import java.util.Set;


/**
 * k线
 */
public class KlineDemo {
    public static void main(String[] args) {
//        //  插入常用指数k线
//        addZhishuKline();

        // 查询k线
        String zqdm = "110081";
        String klt = "101";//klt=5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
        String begDate = "20210820";//查询新增交易的开始时间
        String endDate = "20500101";
        findKlineByZqdm(zqdm, klt, begDate, endDate);
    }

    /**
     * 查询k线
     *  @param zqdm
     * @param klt
     * @param begDate
     * @param endDate
     */
    private static void findKlineByZqdm(String zqdm, String klt, String begDate, String endDate) {
        int lmt = 1000000;
        String klineRs = KlineService.klineRsStrHttpDfcf(zqdm, lmt, klt, begDate, endDate);
        System.out.println("开始日期:" + begDate + "，结束日期:" + endDate + "，周期:" + klt + "，klines.size():" + klineRs);
        System.out.println("klines:" + klineRs);
    }

    /**
     * 插入常用指数k线
     */
    private static void addZhishuKline() {
        String klt = "1";//klt=5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
        int lmt = 1000000;
        int addDaysMax = 66;//最多增加的天数
        int year = 2021;
        int month = 7;
        int day = 1;
        Map<String, String> zhishuMap = Content.getZhishuMap();
        Set<String> zhishuList = zhishuMap.keySet();
        for (String zhishu : zhishuList) {
//            //按照日期的分钟线（5分钟；15分钟；30分钟；60分钟；120分钟），插入k线
//            addKlineByDay(zhishu, klt);

//            //  增加大周期k线
            addKlineDaZhouQi(zhishu, klt);

        }


    }

    /**
     * 按照日期的分钟线（5分钟；15分钟；30分钟；60分钟；120分钟），插入k线
     *
     * @param zhishu
     * @param klt
     */
    private static void addKlineByDay(String zhishu, String klt) {
        int lmt = 1000000;
        int addDaysMax = 66;//最多增加的天数
        int year = 2021;
        int month = 7;
        int day = 1;
        for (int i = 0; i < addDaysMax; i++) {
            String begDate = "";//查询新增交易的开始时间
            String endDate = "";
            begDate = DateUtil.getDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, year, month, day, i);
            endDate = begDate;
            /**    创业板50-159949 HS300ETF-510310 50ETF-510050	新汽车-515030	芯片ETF-159995	酒ETF-512690	医疗ETF-512170 	光伏ETF-515790	稀土ETF-516780	有色50-159880	煤炭ETF-515220 军工ETF-512660		**/
            String zqdm = zhishu;
            String klineRs = KlineService.klineRsStrHttpDfcf(zqdm, lmt, klt, begDate, endDate);
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
                continue;
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

    /**
     * 增加大周期k线
     *
     * @param zhishu
     * @param klt
     */
    private static void addKlineDaZhouQi(String zhishu, String klt) {
        int lmt = 1000000;
        String begDate = "";//查询新增交易的开始时间
        String endDate = "";
        begDate = "0";
        endDate = "20500101";
        String zqdm = zhishu;
        String klineRs = KlineService.klineRsStrHttpDfcf(zqdm, lmt, klt, begDate, endDate);
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
