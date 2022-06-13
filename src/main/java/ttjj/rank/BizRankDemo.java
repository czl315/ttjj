package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.BizRankDao;
import ttjj.dto.FundFlow;
import ttjj.dto.Kline;
import ttjj.dto.RankBizDataDiff;
import ttjj.dto.StatEtfUpDown;
import ttjj.service.BizService;
import ttjj.service.FundFlowService;
import ttjj.service.KlineService;
import utils.Content;
import utils.DateUtil;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static utils.Content.*;
import static utils.DateUtil.HH_MM_SS;

/**
 * 主题排行
 */
public class BizRankDemo {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-05-24";
        boolean isOnlyGn = true;
//        boolean isOnlyGn = false;
        boolean isDelAndAddBiz = true;//是否删除后插入
//        boolean isDelAndAddBiz = false;//是否删除后插入
//        boolean isUpdateDayMa = true;//是否更新日均线
        boolean isUpdateDayMa = false;//是否更新日均线
//        boolean isUpdateDay15MinuteNet = true;//是否更新当日15分钟净值
        boolean isUpdateDay15MinuteNet = false;//是否更新当日15分钟净值

        String bizType = "";
        List<RankBizDataDiff> bizList = new ArrayList<>();

        bizType = DB_RANK_BIZ_TYPE_HANG_YE;
        bizList = BizService.listBiz(date, bizType, NUM_MAX_999);//查询板块行业列表
        saveBizAndKline(date, bizType, bizList, isDelAndAddBiz,isUpdateDayMa,isUpdateDay15MinuteNet);//保存业务和k线

        if (!isOnlyGn) {
            bizType = DB_RANK_BIZ_TYPE_GAI_NIAN;
            bizList = BizService.listBiz(date, bizType, NUM_MAX_999);//查询板块行业列表
            saveBizAndKline(date, bizType, bizList, isDelAndAddBiz, isUpdateDayMa, isUpdateDay15MinuteNet);//保存业务和k线
            bizType = DB_RANK_BIZ_TYPE_ETF;
            bizList = BizService.listBiz(date, bizType, NUM_MAX_999);//查询板块行业列表
            saveBizAndKline(date, bizType, bizList, isDelAndAddBiz, isUpdateDayMa, isUpdateDay15MinuteNet);//保存业务和k线

//            updateFundFlowBk(date);//更新当日资金流信息-板块
//            updateFundFlowGn(date);//更新当日资金流信息-概念
//            updateFundFlowEtf(date);////更新当日资金流信息-etf
        }


//        /**
//         * 更新均值
//         */
//        List<RankBizDataDiff> bizList = listBiz(date, DB_RANK_BIZ_TYPE_HANG_YE, NUM_MAX_999);//查询主题排名by时间类型、显示个数
//        for (int i = 0; i < 365; i++) {
//            date = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -i);// String today = "2021-09-17";
//            updateNetMa(date, Content.MA_5, bizList);
//            updateNetMa(date, Content.MA_10, bizList);
////            updateNetMa(date, Content.MA_20, bizList);
////            updateNetMa(date, Content.MA_30, bizList);
////            updateNetMa(date, Content.MA_60, bizList);
////            updateNetMa(date, Content.MA_120, bizList);
////            updateNetMa(date, Content.MA_250, bizList);
//        }

//        //新增历史数据
//        String begDate = "2018-01-01";//查询新增交易的开始时间
//        String endDate = "2018-12-31";
//        insertHisDbBanKuai(begDate, endDate);//新增历史数据
    }

    /**
     * 保存业务和k线
     *  @param date           日期
     * @param bizType        业务
     * @param bizList        对象列表
     * @param isDelAndAddBiz 是否删除后插入
     * @param isUpdateDayMa 是否更新日均线
     * @param isUpdateDay15MinuteNet 是否更新当日15分钟净值
     */
    private static void saveBizAndKline(String date, String bizType, List<RankBizDataDiff> bizList, boolean isDelAndAddBiz, boolean isUpdateDayMa, boolean isUpdateDay15MinuteNet) {
        if (isDelAndAddBiz) {
            deleteTodayBiz(date, bizType);//删除数据-今日
            insertTodayRank(bizType, bizList);
        }
        if (isUpdateDayMa) {
            updateDbTodayEtfMa(date, bizType, bizList);
        }
        if (isUpdateDay15MinuteNet) {
            updateDbTodayNetCloseByKlt(date, KLT_15, bizType, bizList);
        }

        //遍历板块，插入K线
        saveKlineByType(bizList, date, KLT_5, bizType, true);
        saveKlineByType(bizList, date, KLT_15, bizType, true);
        saveKlineByType(bizList, date, KLT_30, bizType, true);
        saveKlineByType(bizList, date, KLT_60, bizType, true);
        saveKlineByType(bizList, date, KLT_101, bizType, true);

        // 更新-k线-资金流向
        updateFundFlow(bizList, date, KLT_5, bizType);
        updateFundFlow(bizList, date, KLT_15, bizType);
        updateFundFlow(bizList, date, KLT_30, bizType);
        updateFundFlow(bizList, date, KLT_60, bizType);
        updateFundFlow(bizList, date, KLT_101, bizType);

        //更新-k线-净值-只更新日k线-价格区间
        updateNet(bizList, date, KLT_101, bizType);
    }

    /**
     * 更新-k线-净值
     *
     * @param date    日期
     * @param bizList 更新列表
     */
    private static void updateNet(List<RankBizDataDiff> bizList, String date, String klt, String type) {
        int updateRs = 0;//更新成功个数
        if (bizList == null) {
            System.out.println("更新-净值区间:bizList==null");
            return;
        }
        for (RankBizDataDiff biz : bizList) {
            Kline entity = new Kline();
            String zqdm = biz.getF12();
            entity.setZqdm(zqdm);
            entity.setDate(date);
            entity.setKlt(klt);
            entity.setType(type);

            //处理价格区间
            //计算净值
            Map<String, BigDecimal> netMap5 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, klt, false, "", date, null);
            entity.setNET_MA_5(netMap5.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_5(netMap5.get(Content.keyRsMin));
            entity.setNET_MAX_5(netMap5.get(Content.keyRsMax));
            entity.setNET_MIN_CLOS_5(netMap5.get(Content.keyRsNetCloseMin));
            entity.setNET_MAX_CLOS_5(netMap5.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap10 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, klt, false, "", date, null);
            entity.setNET_MA_10(netMap10.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_10(netMap10.get(Content.keyRsMin));
            entity.setNET_MAX_10(netMap10.get(Content.keyRsMax));
            entity.setNET_MIN_CLOS_10(netMap10.get(Content.keyRsNetCloseMin));
            entity.setNET_MAX_CLOS_10(netMap10.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap20 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, klt, false, "", date, null);
            entity.setNET_MA_20(netMap20.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_20(netMap20.get(Content.keyRsMin));
            entity.setNET_MAX_20(netMap20.get(Content.keyRsMax));
            entity.setNET_MIN_CLOS_20(netMap20.get(Content.keyRsNetCloseMin));
            entity.setNET_MAX_CLOS_20(netMap20.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap30 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, klt, false, "", date, null);
            entity.setNET_MA_30(netMap30.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_30(netMap30.get(Content.keyRsMin));
            entity.setNET_MAX_30(netMap30.get(Content.keyRsMax));
            entity.setNET_MIN_CLOS_30(netMap30.get(Content.keyRsNetCloseMin));
            entity.setNET_MAX_CLOS_30(netMap30.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap60 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, klt, false, "", date, null);
            entity.setNET_MA_60(netMap60.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_60(netMap60.get(Content.keyRsMin));
            entity.setNET_MAX_60(netMap60.get(Content.keyRsMax));
            entity.setNET_MIN_CLOS_60(netMap60.get(Content.keyRsNetCloseMin));
            entity.setNET_MAX_CLOS_60(netMap60.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap120 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, klt, false, "", date, null);
            entity.setNET_MA_120(netMap120.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_120(netMap120.get(Content.keyRsMin));
            entity.setNET_MAX_120(netMap120.get(Content.keyRsMax));
            entity.setNET_MIN_CLOS_120(netMap120.get(Content.keyRsNetCloseMin));
            entity.setNET_MAX_CLOS_120(netMap120.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap250 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, klt, false, "", date, null);
            entity.setNET_MA_250(netMap250.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_250(netMap250.get(Content.keyRsMin));
            entity.setNET_MAX_250(netMap250.get(Content.keyRsMax));
            entity.setNET_MIN_CLOS_250(netMap250.get(Content.keyRsNetCloseMin));
            entity.setNET_MAX_CLOS_250(netMap250.get(Content.keyRsNetCloseMax));


            //更新
            updateRs += KlineService.update(entity);
        }
        System.out.println("更新-净值-个数:" + bizList.size() + ",更新成功：" + updateRs);
    }

    /**
     * 更新资金流向
     *
     * @param bizList 类型列表
     * @param date    日期
     * @param klt     周期
     * @param type    业务类型
     */
    private static void updateFundFlow(List<RankBizDataDiff> bizList, String date, String klt, String type) {

        for (RankBizDataDiff rankBizDataDiff : bizList) {
            String zqdm = rankBizDataDiff.getF12();
            String zqmc = rankBizDataDiff.getF14();

            int rs = 0;
            List<FundFlow> rsList = FundFlowService.parse(FundFlowService.httpFundFlowRs(zqdm));//获取-资金流向的对象结果
            rsList = FundFlowService.handlerFundFlowByMinute(rsList, Integer.valueOf(klt));//计算分钟级别资金流向
            for (FundFlow fundFlow : rsList) {
                Kline kline = new Kline();
                String ktime = fundFlow.getKtime();
                if (klt == KLT_5 || klt == KLT_15 || klt == KLT_30 || klt == KLT_60) {
                    kline.setKtime(DateUtil.getForMatTime(HH_MM_SS, ktime));//只设置当天具体时间，去掉日期
                }
                kline.setZqdm(zqdm);
                kline.setDate(date);
                kline.setType(type);
                kline.setKlt(klt);

                kline.setFlowInMain(fundFlow.getFlowInMain());
                kline.setFlowInSuperBig(fundFlow.getFlowInSuperBig());
                kline.setFlowInBig(fundFlow.getFlowInBig());
                kline.setFlowInMid(fundFlow.getFlowInMid());
                kline.setFlowInSmall(fundFlow.getFlowInSmall());
                int updateRs = KlineService.update(kline);
                if (updateRs != 1) {
                    System.out.println("K线-资金流入-更新失败：" + JSON.toJSONString(kline));
                } else {
                    rs = rs + updateRs;
                }
            }
            System.out.println("K线-资金流入-更新个数：" + rs + ",zqmc:" + zqmc);
        }
    }

    /**
     * 遍历板块，插入K线
     *
     * @param bizList  行业列表
     * @param date     日期
     * @param klt      周期类型
     * @param isDelete 是否先删除
     */
    private static void saveKlineByType(List<RankBizDataDiff> bizList, String date, String klt, String type, boolean isDelete) {
        for (RankBizDataDiff rankBizDataDiff : bizList) {
            String zqdm = rankBizDataDiff.getF12();
            String zqmc = rankBizDataDiff.getF14();
            List<Kline> klines = KlineService.kline(zqdm, 1000, klt, true, date, date, null);
            System.out.println(",开始日期:" + date + "，结束日期:" + date + "，周期:" + klt + "，klines.size():" + klines.size() + ",zqmc:" + zqmc);
            //        System.out.println("klines:"+JSON.toJSONString(klines));
            //是否先删除
            if (isDelete) {
                Kline condition = new Kline();
                condition.setDate(date);
                condition.setZqdm(zqdm);
                condition.setType(type);
                condition.setKlt(klt);
                int rs = KlineService.deleteByCondition(condition);
//                System.out.println(zqdm + "," + date + ",删除结果：" + rs);
            }

            int saveRs = 0;
            for (Kline kline : klines) {
                if (klt == KLT_5 || klt == KLT_15 || klt == KLT_30 || klt == KLT_60) {
                    kline.setKtime(kline.getKtime().substring(11));//只设置当天具体时间，去掉日期
                }
                kline.setDate(date);
                kline.setType(type);
                kline.setRs(null);
                /**
                 * 插入数据库-K线
                 */
                saveRs += KlineService.insert(kline);
            }
//            System.out.println(zqdm + "," + date + ",插入K线：" + saveRs);
        }
    }

    /**
     * 更新今日15分钟净值价-根据周期
     *
     * @param date
     * @param klt
     * @param bizList
     */
    private static void updateDbTodayNetCloseByKlt(String date, String klt, String type, List<RankBizDataDiff> bizList) {
        for (RankBizDataDiff rankBizDataDiff : bizList) {
            List<Kline> klines = KlineService.kline(rankBizDataDiff.getF12(), NUM_MAX_99, klt, true, date, date, null);
            if (klines == null || klines.size() == 0) {
                System.out.println("k线为空：" + JSON.toJSONString(rankBizDataDiff));
                continue;
            }
            handlerTodayNetCloseByKlt(rankBizDataDiff, klines, klt);
            int rs = BizRankDao.updateEtfNet(rankBizDataDiff);
            System.out.println("更新今日净值价-分时-" + klt + "：" + rs);
        }
    }

    /**
     * 处理今日净值
     *
     * @param rankBizDataDiff
     * @param klines
     * @param klt
     */
    private static void handlerTodayNetCloseByKlt(RankBizDataDiff rankBizDataDiff, List<Kline> klines, String klt) {
        if (klines == null) {
            return;
        }
        for (Kline kline : klines) {
            String ktime = kline.getKtime();
            if (klt.equals(KLT_15)) {
                if (ktime.contains("09:45:00")) {
                    BigDecimal closeAmt = kline.getCloseAmt();
                    rankBizDataDiff.setNET_TODAY_0945(closeAmt);
                }
                if (ktime.contains("10:00:00")) {
                    BigDecimal closeAmt = kline.getCloseAmt();
                    rankBizDataDiff.setNET_TODAY_1000(closeAmt);
                }
                if (ktime.contains("10:15:00")) {
                    BigDecimal closeAmt = kline.getCloseAmt();
                    rankBizDataDiff.setNET_TODAY_1015(closeAmt);
                }
                if (ktime.contains("10:30:00")) {
                    BigDecimal closeAmt = kline.getCloseAmt();
                    rankBizDataDiff.setNET_TODAY_1030(closeAmt);
                }
                if (ktime.contains("10:45:00")) {
                    BigDecimal closeAmt = kline.getCloseAmt();
                    rankBizDataDiff.setNET_TODAY_1045(closeAmt);
                }
                if (ktime.contains("11:00:00")) {
                    BigDecimal closeAmt = kline.getCloseAmt();
                    rankBizDataDiff.setNET_TODAY_1100(closeAmt);
                }
                if (ktime.contains("11:15:00")) {
                    BigDecimal closeAmt = kline.getCloseAmt();
                    rankBizDataDiff.setNET_TODAY_1115(closeAmt);
                }
                if (ktime.contains("11:30:00")) {
                    BigDecimal closeAmt = kline.getCloseAmt();
                    rankBizDataDiff.setNET_TODAY_1130(closeAmt);
                }
                if (ktime.contains("13:15:00")) {
                    BigDecimal closeAmt = kline.getCloseAmt();
                    rankBizDataDiff.setNET_TODAY_1315(closeAmt);
                }
                if (ktime.contains("13:30:00")) {
                    BigDecimal closeAmt = kline.getCloseAmt();
                    rankBizDataDiff.setNET_TODAY_1330(closeAmt);
                }
                if (ktime.contains("13:45:00")) {
                    BigDecimal closeAmt = kline.getCloseAmt();
                    rankBizDataDiff.setNET_TODAY_1345(closeAmt);
                }
                if (ktime.contains("14:00:00")) {
                    BigDecimal closeAmt = kline.getCloseAmt();
                    rankBizDataDiff.setNET_TODAY_1400(closeAmt);
                }
                if (ktime.contains("14:15:00")) {
                    BigDecimal closeAmt = kline.getCloseAmt();
                    rankBizDataDiff.setNET_TODAY_1415(closeAmt);
                }
                if (ktime.contains("14:30:00")) {
                    BigDecimal closeAmt = kline.getCloseAmt();
                    rankBizDataDiff.setNET_TODAY_1430(closeAmt);
                }
                if (ktime.contains("14:45:00")) {
                    BigDecimal closeAmt = kline.getCloseAmt();
                    rankBizDataDiff.setNET_TODAY_1445(closeAmt);
                }
                if (ktime.contains("15:00:00")) {
                    BigDecimal closeAmt = kline.getCloseAmt();
                    rankBizDataDiff.setNET_TODAY_1500(closeAmt);
                }
            }

        }
    }

    /**
     * 删除数据-今日
     *
     * @param date
     * @param bizType
     */
    private static void deleteTodayBiz(String date, String bizType) {
        String today = DateUtil.getToday(DateUtil.YYYY_MM_DD);
        if (!today.equals(date)) {
            System.out.println("不是删除今日数据，请注意！");
            return;
        }
        RankBizDataDiff entity = new RankBizDataDiff();
        entity.setDate(date);
        entity.setType(bizType);
        int rs = BizRankDao.deleteByDate(entity);
        System.out.println("日期：" + date + "，删除结果：" + rs);
    }

    /**
     * @param date
     * @param fundType
     * @param bizList
     */
    private static void updateDbTodayEtfMa(String date, String fundType, List<RankBizDataDiff> bizList) {
        for (RankBizDataDiff rankBizDataDiff : bizList) {
            String klt = KLT_101;
            RankBizDataDiff entity = new RankBizDataDiff();
            String zqdm = rankBizDataDiff.getF12();
            entity.setF12(zqdm);
            entity.setDate(date);
            Map<String, BigDecimal> netMap5 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, klt, false, "", date, null);
            entity.setNET_MA_5(netMap5.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_7(netMap5.get(Content.keyRsMin));
            entity.setNET_MAX_7(netMap5.get(Content.keyRsMax));
            entity.setNET_MIN_CLOS_7(netMap5.get(Content.keyRsNetCloseMin));
            entity.setNET_MAX_CLOS_7(netMap5.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap10 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, klt, false, "", date, null);
            entity.setNET_MA_10(netMap10.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_14(netMap10.get(Content.keyRsMin));
            entity.setNET_MAX_14(netMap10.get(Content.keyRsMax));
            entity.setNET_MIN_CLOS_14(netMap10.get(Content.keyRsNetCloseMin));
            entity.setNET_MAX_CLOS_14(netMap10.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap20 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, klt, false, "", date, null);
            entity.setNET_MA_20(netMap20.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_30(netMap20.get(Content.keyRsMin));
            entity.setNET_MAX_30(netMap20.get(Content.keyRsMax));
            entity.setNET_MIN_CLOS_30(netMap20.get(Content.keyRsNetCloseMin));
            entity.setNET_MAX_CLOS_30(netMap20.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap30 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, klt, false, "", date, null);
            entity.setNET_MA_30(netMap30.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_60(netMap30.get(Content.keyRsMin));
            entity.setNET_MAX_60(netMap30.get(Content.keyRsMax));
            entity.setNET_MIN_CLOS_60(netMap30.get(Content.keyRsNetCloseMin));
            entity.setNET_MAX_CLOS_60(netMap30.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap60 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, klt, false, "", date, null);
            entity.setNET_MA_60(netMap60.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_90(netMap60.get(Content.keyRsMin));
            entity.setNET_MAX_90(netMap60.get(Content.keyRsMax));
            entity.setNET_MIN_CLOS_90(netMap60.get(Content.keyRsNetCloseMin));
            entity.setNET_MAX_CLOS_90(netMap60.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap120 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, klt, false, "", date, null);
            entity.setNET_MA_120(netMap120.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_180(netMap120.get(Content.keyRsMin));
            entity.setNET_MAX_180(netMap120.get(Content.keyRsMax));
            entity.setNET_MIN_CLOS_180(netMap120.get(Content.keyRsNetCloseMin));
            entity.setNET_MAX_CLOS_180(netMap120.get(Content.keyRsNetCloseMax));
            Map<String, BigDecimal> netMap250 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, klt, false, "", date, null);
            entity.setNET_MA_250(netMap250.get(Content.keyRsNetCloseAvg));
            entity.setNET_MIN_360(netMap250.get(Content.keyRsMin));
            entity.setNET_MAX_360(netMap250.get(Content.keyRsMax));
            entity.setNET_MIN_CLOS_360(netMap250.get(Content.keyRsNetCloseMin));
            entity.setNET_MAX_CLOS_360(netMap250.get(Content.keyRsNetCloseMax));
            BizRankDao.updateEtfNet(entity);
            System.out.println("更新-均线：" + rankBizDataDiff.getF3() + ":" + "\t" + rankBizDataDiff.getF223() + ":" + rankBizDataDiff.getF12() + ":" + rankBizDataDiff.getF14());
        }
    }

    /**
     * 保存-板块、概念、etf
     *
     * @param type
     * @param bizList
     */
    private static void insertTodayRank(String type, List<RankBizDataDiff> bizList) {
        //db-插入
        BizRankDao.insertDbBiz(bizList);//bk-板块
        System.out.println(type + "保存完成：" + bizList.size());
//            showBizSql(date, rankBizDataDiffListBiz, "bk");//显示sql-业务排行-插入
    }


    /**
     * 列表查询-行业etf-排序：涨跌幅
     *
     * @param etfBizSet etf集合
     * @param days
     * @param showUp    是否显示上涨
     * @param showDown  是否显示下跌
     * @return
     */
    private static Map<String, StatEtfUpDown> listEtfBizDb(Set<String> etfBizSet, int days, boolean showUp, boolean showDown) {
        Map<String, StatEtfUpDown> statRs = new HashMap<>();
        List<StatEtfUpDown> statEtfUpDownList = new ArrayList<>();
        //按照日期，倒序查询
        for (int i = 0; i <= days; i++) {
            String date = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -i);
            Map<String, Object> condition = new HashMap<>();
            condition.put("list", etfBizSet);
            condition.put("date", date);
            List<RankBizDataDiff> rankListUp = BizRankDao.listEtfBiz(condition);
            if (rankListUp == null) {
                continue;
            }
            List<RankBizDataDiff> rankListDown = rankListUp.stream().filter(e -> e != null).sorted(Comparator.comparing(RankBizDataDiff::getF3, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());//倒序
            String curWeekNo = DateUtil.getWeekByYyyyMmDd(date, DateUtil.YYYY_MM_DD);
            if (showUp) {
                if (curWeekNo.equals(DATE_WEEK_5)) {
                }
                System.out.println(date + "上涨:");
                String upListStr = handlerUpOrDownList(rankListUp, 100, true);//处理上涨
                System.out.println(upListStr);//显示
            }
            if (showDown) {
                if (curWeekNo.equals(DATE_WEEK_5)) {
                }
                System.out.println(date + "下跌:");
                String downListStr = handlerUpOrDownList(rankListDown, 100, false);
                System.out.println(downListStr);//显示
            }

//            for (RankBizDataDiff biz : rankListUp) {
//                if (rankListUp == null) {
//                    return null;
//                }
//                String code = biz.getF12();
//                StatEtfUpDown statEtfUpDown = new StatEtfUpDown();
//                if (statRs.containsKey(code)) {
//                    statEtfUpDown = statRs.get(code);
//                }
//                statEtfUpDown.setCode(biz.getF12());
//                statEtfUpDown.setName(handlerEtfName(biz.getF14()));
//                int oldCountCurContinueUp = statEtfUpDown.getCountCurContinueUp();
//                int oldCountCurContinueDown = statEtfUpDown.getCountCurContinueDown();
//                int oldCountTotalUp = statEtfUpDown.getCountTotalUp();
//                int oldCountTotalDown = statEtfUpDown.getCountTotalDown();
//                //  当前连续次数合计-上涨:如果上涨，次数加，否则次数重置为0；下跌次数反之
//                if (biz.getF3().compareTo(new BigDecimal("0")) > 0) {
//                    statEtfUpDown.setCountCurContinueUp(oldCountCurContinueUp + 1);
//                    statEtfUpDown.setCountCurContinueDown(0);
//                    statEtfUpDown.setCountTotalUp(oldCountTotalUp + 1);
//                } else {
//                    statEtfUpDown.setCountCurContinueDown(oldCountCurContinueDown + 1);
//                    statEtfUpDown.setCountCurContinueUp(0);
//                    statEtfUpDown.setCountTotalDown(oldCountTotalDown + 1);
//                }
//                statRs.put(code, statEtfUpDown);
//            }
        }

        statEtfUpDownList.addAll(statRs.values());
        //排序
        statEtfUpDownList = statEtfUpDownList.stream().filter(e -> e != null).sorted(Comparator.comparing(StatEtfUpDown::getCountTotalUp, Comparator.nullsFirst(Integer::compareTo)).reversed()).collect(Collectors.toList());
        System.out.println();
        for (StatEtfUpDown dto : statEtfUpDownList) {
            String name = dto.getName();
            System.out.print(dto.getCode());
            System.out.print("\t累计-涨跌比:" + dto.getCountTotalUp() + ":" + dto.getCountTotalDown());
            System.out.print(" \t当前连续次数合计-涨跌比:" + dto.getCountCurContinueUp() + ":" + dto.getCountCurContinueDown());
            System.out.print("\t");
            if (name.length() < 4) {
                System.out.print(dto.getName());
            } else {
                System.out.print(dto.getName());
            }
            System.out.println();
        }
        return statRs;
    }

    /**
     * 统计涨跌次数-按照天的维度
     *
     * @param etfBizSet
     * @param days
     * @return
     */
    private static Map<String, StatEtfUpDown> statEtfAdrDb(Set<String> etfBizSet, int year, int month, int day, int days) {
        Map<String, StatEtfUpDown> statRs = new HashMap<>();
        List<StatEtfUpDown> statEtfUpDownList = new ArrayList<>();
        //按照日期，倒序查询
        for (int i = 0; i <= days; i++) {
            String date = DateUtil.getDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, year, month, day, i);//查询新增交易的开始时间
            Map<String, Object> condition = new HashMap<>();
            condition.put("list", etfBizSet);
            condition.put("date", date);
            List<RankBizDataDiff> rankListUp = BizRankDao.listEtfBiz(condition);
            if (rankListUp == null) {
                continue;
            }
            for (RankBizDataDiff biz : rankListUp) {
                if (rankListUp == null) {
                    return null;
                }
                String code = biz.getF12();
                StatEtfUpDown statEtfUpDown = new StatEtfUpDown();
                if (statRs.containsKey(code)) {
                    statEtfUpDown = statRs.get(code);
                }
                statEtfUpDown.setCode(biz.getF12());
                statEtfUpDown.setName(handlerEtfName(biz.getF14()));
                int oldCountCurContinueUp = statEtfUpDown.getCountCurContinueUp();
                int oldCountCurContinueDown = statEtfUpDown.getCountCurContinueDown();
                int oldCountTotalUp = statEtfUpDown.getCountTotalUp();
                int oldCountTotalDown = statEtfUpDown.getCountTotalDown();
                //  当前连续次数合计-上涨:如果上涨，次数加，否则次数重置为0；下跌次数反之
                if (biz.getF3().compareTo(new BigDecimal("0")) > 0) {
                    statEtfUpDown.setCountCurContinueUp(oldCountCurContinueUp + 1);
                    statEtfUpDown.setCountCurContinueDown(0);
                    statEtfUpDown.setCountTotalUp(oldCountTotalUp + 1);
                } else {
                    statEtfUpDown.setCountCurContinueDown(oldCountCurContinueDown + 1);
                    statEtfUpDown.setCountCurContinueUp(0);
                    statEtfUpDown.setCountTotalDown(oldCountTotalDown + 1);
                }
                statRs.put(code, statEtfUpDown);
            }
        }

        statEtfUpDownList.addAll(statRs.values());
        //排序
        statEtfUpDownList = statEtfUpDownList.stream().filter(e -> e != null).sorted(Comparator.comparing(StatEtfUpDown::getCountTotalUp, Comparator.nullsFirst(Integer::compareTo)).reversed()).collect(Collectors.toList());
        System.out.println();
        for (StatEtfUpDown dto : statEtfUpDownList) {
            String name = dto.getName();
            System.out.print(dto.getCode());
            System.out.print("\t累计-涨跌比:" + dto.getCountTotalUp() + ":" + dto.getCountTotalDown());
            System.out.print(" \t当前连续次数合计-涨跌比:" + dto.getCountCurContinueUp() + ":" + dto.getCountCurContinueDown());
            System.out.print("\t");
            if (name.length() < 4) {
                System.out.print(dto.getName());
            } else {
                System.out.print(dto.getName());
            }
            System.out.println();
        }
        return statRs;
    }

    /**
     * 处理上涨列表
     *
     * @param upRankList
     * @return
     */
    private static String handlerUpOrDownList(List<RankBizDataDiff> upRankList, int limit, boolean upDownFlag) {
        StringBuffer sb = new StringBuffer();
        if (upRankList == null) {
            return null;
        }
        int temp = 0;
        for (RankBizDataDiff r : upRankList) {
            temp++;
            if (temp > limit) {
                break;
            }
            String name = handlerEtfName(r.getF14());
            //如果上涨标志，涨幅小于0，中断
            if (upDownFlag && r.getF3().compareTo(new BigDecimal("0")) <= 0) {
                break;
            }
            //如果下跌标志，涨幅小于0，中断
            if (!upDownFlag && r.getF3().compareTo(new BigDecimal("0")) >= 0) {
                break;
            }
            sb.append("," + name);
//            sb.append("," + name + "：" + r.getF3());

        }
        String rs = "";
        if (sb.length() > 0) {
            rs = sb.substring(1);
        }
        return rs;
    }

    /**
     * 处理etf名称
     *
     * @param name
     */
    private static String handlerEtfName(String name) {
        name = name.replace("ETF", "");
        name = name.replace("基金", "");
        name = name.replace("有色金属", "有色");
        name = name.replace("基建50", "基建");
        name = name.replace("能源化工", "能源");
        name = name.replace("中概互联网", "中概");
        return name;
    }

    /**
     * 检查资金流向-etf
     *
     * @param date
     */
    private static void checkFundFlowByEtf(String date) {
        List<RankBizDataDiff> etfList = BizService.listBiz(date, KLINE_TYPE_ETF, NUM_MAX_999);//2021-04-16:425;
        for (RankBizDataDiff etf : etfList) {
            //限定总市值10亿
            if (etf.getF20().compareTo(new BigDecimal("1000000000")) > 0) {
                FundFlowService.fundFlowHandler(etf.getF12(), null);
            }
        }
    }

    /**
     * 更新当日资金流信息-板块
     *
     * @param date
     */
    private static void updateFundFlowBk(String date) {
        List<RankBizDataDiff> rankList = BizService.listBiz(date, DB_RANK_BIZ_TYPE_HANG_YE, NUM_MAX_999);//查询板块行业列表
        for (RankBizDataDiff etf : rankList) {
            String stCode = etf.getF12();
            String rsFundFlow = FundFlowService.httpFundFlowRs(stCode);

            RankBizDataDiff entityDb = new RankBizDataDiff();
            entityDb.setF12(stCode);
            entityDb.setDate(date);

            entityDb.setFundFlow(rsFundFlow);
            int updateRs = BizRankDao.updateEtfNet(entityDb);
            System.out.println("更新资金流向-板块-结果：" + updateRs + "," + etf.getF14());
        }
    }

    /**
     * 更新当日资金流信息-etf
     *
     * @param date
     */
    private static void updateFundFlowEtf(String date) {
        List<RankBizDataDiff> rankEtf = BizService.listBiz(date, "etf", 999);//2021-04-16:425;
        for (RankBizDataDiff etf : rankEtf) {
            String stCode = etf.getF12();
            String rsFundFlow = FundFlowService.httpFundFlowRs(stCode);

            RankBizDataDiff entityDb = new RankBizDataDiff();
            entityDb.setF12(stCode);
            entityDb.setDate(date);

            entityDb.setFundFlow(rsFundFlow);
            int updateRs = BizRankDao.updateEtfNet(entityDb);
            System.out.println("更新资金流向-etf-结果：" + updateRs + "," + etf.getF14());
        }
    }

    private static void updateNetMa(String date, int ma5, List<RankBizDataDiff> bizList) {
        for (RankBizDataDiff rankBizDataDiff : bizList) {
            RankBizDataDiff entity = new RankBizDataDiff();
            String zqdm = rankBizDataDiff.getF12();
            entity.setF12(zqdm);
            entity.setDate(date);
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, ma5, KLT_101, false, "", date, KLINE_TYPE_BAN_KUAI);
            if (ma5 == MA_5) {
                entity.setNET_MA_5(netMap.get(Content.keyRsNetCloseAvg));
            }
            if (ma5 == MA_10) {
                entity.setNET_MA_10(netMap.get(Content.keyRsNetCloseAvg));
            }
            if (ma5 == MA_20) {
                entity.setNET_MA_20(netMap.get(Content.keyRsNetCloseAvg));
            }
            if (ma5 == MA_30) {
                entity.setNET_MA_30(netMap.get(Content.keyRsNetCloseAvg));
            }
            if (ma5 == MA_60) {
                entity.setNET_MA_60(netMap.get(Content.keyRsNetCloseAvg));
            }
            if (ma5 == MA_120) {
                entity.setNET_MA_120(netMap.get(Content.keyRsNetCloseAvg));
            }
            if (ma5 == MA_250) {
                entity.setNET_MA_250(netMap.get(Content.keyRsNetCloseAvg));
            }
            int rs = BizRankDao.updateEtfNet(entity);
            if (rs == 1) {
                System.out.println("更新净值成功：" + JSON.toJSONString(entity));
            } else {
                System.out.println("更新净值失败！！！！！！：" + JSON.toJSONString(entity));
            }
        }
    }

    /**
     * 新增历史数据-板块
     *
     * @param begDate
     * @param endDate
     */
    private static void insertHisDbBanKuai(String begDate, String endDate) {
        List<RankBizDataDiff> hangYeList = BizService.listBiz(endDate, DB_RANK_BIZ_TYPE_HANG_YE, NUM_MAX_999);//查询所有行业列表
        //遍历所有行业，根据行业查询历史k线，插入行业的数据
        for (RankBizDataDiff hangYe : hangYeList) {
            String banKuaiZqdm = HTTP_KLINE_SECID_PREFIX_BANKUAI + hangYe.getF12();
            insertHisDbBanKuai(banKuaiZqdm, begDate, endDate);
        }
    }

    /**
     * 新增今日数据
     *
     * @param date
     */
    private static void insertTodayBizDb(String date) {
//        boolean insertDbTodayBiz = true;
//        boolean insertDbTodayBiz = false;
//        boolean insertDbTodayConcept = true;
//        boolean insertDbTodayConcept = false;
//        boolean insertDbTodayEtf = true;
//        boolean insertDbTodayEtf = false;

        boolean updateDbTodayEtfMa = true;//更新均线
//        boolean updateDbTodayEtfMa = false;//更新均线

        boolean updateDbFundFlow = true;//更新资金流向

//        boolean updateDbTodayEtfNetMaxMinTimeFlag = true;
        boolean updateDbTodayEtfNetMaxMinTimeFlag = false;
//        boolean updateDateBizFlag = true;
        boolean updateDateBizFlag = false;

        int updateDbEtfNetDays = 365;
//        int updateDbEtfNetDays = 1;
//        int updateDbEtfNetDays = 0;

//        if (insertDbTodayBiz) {
//            List<RankBizDataDiff> rankBizDataDiffListBiz = listBiz(date, DB_RANK_BIZ_TYPE_HANG_YE, NUM_MAX_999);//查询板块行业列表
//            //db-插入
//            BizRankDao.insertDbBiz(rankBizDataDiffListBiz);//bk-板块
//            System.out.println("bk-板块-保存完成：" + rankBizDataDiffListBiz.size());
////            showBizSql(date, rankBizDataDiffListBiz, "bk");//显示sql-业务排行-插入
//        }

//        if (insertDbTodayConcept) {
//            List<RankBizDataDiff> rankBizDataDiffListConcept = listConcept(date, DB_RANK_BIZ_TYPE_GAI_NIAN, NUM_MAX_999);//查询主题排名by时间类型、显示个数
//            //db-插入
//            BizRankDao.insertDbBiz(rankBizDataDiffListConcept);
//            System.out.println("rank-概念-保存完成：" + rankBizDataDiffListConcept.size());
////            showBizSql(date, rankBizDataDiffListConcept, "gn");//显示业务排行-插入sql
//        }

//        List<RankBizDataDiff> rankEtf = listEtf(date, "etf", 999);//2021-04-16:425;
//        if (insertDbTodayEtf) {
//            BizRankDao.insertDbBiz(rankEtf);
//            System.out.println("etf-保存完成：" + rankEtf.size());
////            showBizSql(date, rankEtf, "etf");//新增插入-etf指数基金场内
//        }
        List<RankBizDataDiff> rankEtf = BizService.listBiz(date, "etf", 999);//2021-04-16:425;
        if (updateDbTodayEtfMa) {
            for (RankBizDataDiff rankBizDataDiff : rankEtf) {
                String klt = KLT_101;
                RankBizDataDiff entity = new RankBizDataDiff();
                String zqdm = rankBizDataDiff.getF12();
                entity.setF12(zqdm);
                entity.setDate(date);
                Map<String, BigDecimal> netMap5 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_5, klt, false, "", date, null);
                entity.setNET_MA_5(netMap5.get(Content.keyRsNetCloseAvg));
//                entity.setNET_MIN_7(netMap5.get(keyRsMin));
                Map<String, BigDecimal> netMap10 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_10, klt, false, "", date, null);
                entity.setNET_MA_10(netMap10.get(Content.keyRsNetCloseAvg));
                Map<String, BigDecimal> netMap20 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_20, klt, false, "", date, null);
                entity.setNET_MA_20(netMap20.get(Content.keyRsNetCloseAvg));
                Map<String, BigDecimal> netMap30 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_30, klt, false, "", date, null);
                entity.setNET_MA_30(netMap30.get(Content.keyRsNetCloseAvg));
                Map<String, BigDecimal> netMap60 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_60, klt, false, "", date, null);
                entity.setNET_MA_60(netMap60.get(Content.keyRsNetCloseAvg));
                Map<String, BigDecimal> netMap120 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_120, klt, false, "", date, null);
                entity.setNET_MA_120(netMap120.get(Content.keyRsNetCloseAvg));
                Map<String, BigDecimal> netMap250 = KlineService.findNetMinMaxAvg(zqdm, Content.MA_250, klt, false, "", date, null);
                entity.setNET_MA_250(netMap250.get(Content.keyRsNetCloseAvg));
                BizRankDao.updateEtfNet(entity);
                System.out.println("更新-etf净值：" + JSON.toJSONString(entity));
            }
        }

        if (updateDbEtfNetDays == 1) {
            // 更新最新净值-限定时间段的最大最小净值
            String table = "rank_st_biz";
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 1, "LAST_NET", "LAST_NET", "LAST_NET", "LAST_NET");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 1, "NET_MIN_1", "NET_MAX_1", "NET_MIN_CLOS_1", "NET_MAX_CLOS_1");

        }
        if (updateDbEtfNetDays == 365) {
            // 更新最新净值-限定时间段的最大最小净值
            String table = "rank_st_biz";
//            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 1, "LAST_NET", "LAST_NET", "LAST_NET", "LAST_NET");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 1, "NET_MIN_1", "NET_MAX_1", "NET_MIN_CLOS_1", "NET_MAX_CLOS_1");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 7, "NET_MIN_7", "NET_MAX_7", "NET_MIN_CLOS_7", "NET_MAX_CLOS_7");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 14, "NET_MIN_14", "NET_MAX_14", "NET_MIN_CLOS_14", "NET_MAX_CLOS_14");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 30, "NET_MIN_30", "NET_MAX_30", "NET_MIN_CLOS_30", "NET_MAX_CLOS_30");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 60, "NET_MIN_60", "NET_MAX_60", "NET_MIN_CLOS_60", "NET_MAX_CLOS_60");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 90, "NET_MIN_90", "NET_MAX_90", "NET_MIN_CLOS_90", "NET_MAX_CLOS_90");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 180, "NET_MIN_180", "NET_MAX_180", "NET_MIN_CLOS_180", "NET_MAX_CLOS_180");
            showUpdateDbMaxMinNetByDays(date, rankEtf, table, 365, "NET_MIN_360", "NET_MAX_360", "NET_MIN_CLOS_360", "NET_MAX_CLOS_360");
            for (RankBizDataDiff rankBizDataDiff : rankEtf) {
                BizRankDao.updateEtfNet(rankBizDataDiff);
            }
        }

        if (updateDbTodayEtfNetMaxMinTimeFlag) {
            String curdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//            String curdate = "2021-06-04";
            updateDbTodayEtfNetMaxMinTime(curdate, rankEtf);
        }

        if (updateDateBizFlag) {
            updateDateBiz();
        }

        if (updateDbFundFlow) {
            for (RankBizDataDiff rankBizDataDiff : rankEtf) {
                RankBizDataDiff entity = new RankBizDataDiff();
                entity.setF12(rankBizDataDiff.getF12());
                entity.setDate(date);
                BizRankDao.updateEtfNet(rankBizDataDiff);
            }
        }

    }

//    public static void main(String[] args) {
//        String date = "2021-09-03";
//        List<RankBizDataDiff> rankEtf = listEtf(date, "etf", 999);//2021-04-16:425;
//        for (RankBizDataDiff rankBizDataDiff : rankEtf) {
//            insertBiz(rankBizDataDiff.getF12());
//        }
//    }

    /**
     * 新增-业务
     */
    private static void insertBiz(String zqdm) {

        /**    创业板50-159949 HS300ETF-510310 50ETF-510050	新汽车-515030	芯片ETF-159995	酒ETF-512690	医疗ETF-512170 	光伏ETF-515790	稀土ETF-516780	有色50-159880	煤炭ETF-515220 军工ETF-512660		**/
//        String zqdm = "159949";
        String begDate = "20210101";//查询新增交易的开始时间
        String endDate = "20210415";
        String klt = "101";//klt=5:5分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
        int lmt = 1000000;
        List<RankBizDataDiff> rankBizDataDiffList = new ArrayList<>();
        List<Kline> klines = KlineService.kline(zqdm, lmt, klt, true, begDate, endDate, KLINE_TYPE_ETF);
        System.out.println("开始日期:" + begDate + "，结束日期:" + endDate + "，周期:" + klt + "，klines.size():" + klines.size());
//        System.out.println("klines:"+JSON.toJSONString(klines));
        for (Kline kline : klines) {
            RankBizDataDiff rankBizDataDiff = new RankBizDataDiff();
            rankBizDataDiff.setDate(kline.getKtime());
            rankBizDataDiff.setMonth(DateUtil.getYearMonth(kline.getKtime(), DateUtil.YYYY_MM_DD));
            rankBizDataDiff.setWeekYear(DateUtil.getYearWeek(kline.getKtime(), DateUtil.YYYY_MM_DD));
            rankBizDataDiff.setWeek(DateUtil.getWeekByYyyyMmDd(kline.getKtime(), DateUtil.YYYY_MM_DD));
            rankBizDataDiff.setType("etf");
            rankBizDataDiff.setF1(3L);
            rankBizDataDiff.setF2(kline.getCloseAmt().doubleValue());
            rankBizDataDiff.setF3(kline.getZhangDieFu());
            rankBizDataDiff.setF4(kline.getZhangDieE().doubleValue());
            rankBizDataDiff.setF5(kline.getCjl().longValue());
            rankBizDataDiff.setF6(kline.getCje().longValue());
            rankBizDataDiff.setF7(kline.getZhenFu().doubleValue());
            rankBizDataDiff.setF8(kline.getHuanShouLv().doubleValue());
//            rankBizDataDiff.setF9(kline.getsh);
//            rankBizDataDiff.getF10(kline.get)
//            rankBizDataDiff.getF11()
            rankBizDataDiff.setF12(kline.getZqdm());
            rankBizDataDiff.setF13(1L);
            rankBizDataDiff.setF14(kline.getZqmc());
            rankBizDataDiff.setF15(kline.getMaxAmt().doubleValue());
            rankBizDataDiff.setF16(kline.getMinAmt().doubleValue());
            rankBizDataDiff.setF17(kline.getOpenAmt().doubleValue());
            rankBizDataDiff.setF18(kline.getCloseAmt().subtract(kline.getZhangDieE()).doubleValue());//计算昨日收盘价：今日收盘价-今日涨跌额

            rankBizDataDiffList.add(rankBizDataDiff);
        }
//        System.out.println("rankBizDataDiffList:"+JSON.toJSONString(rankBizDataDiffList));

        BizRankDao.insertDbBiz(rankBizDataDiffList);//业务排行-插入
    }

    private static void insertHisDbBanKuai(String zqdm, String begDate, String endDate) {
        String klt = KLT_101;//klt=5:5分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
        int lmt = 1000000;
        List<RankBizDataDiff> rankBizDataDiffList = new ArrayList<>();
        List<Kline> klines = KlineService.kline(zqdm, lmt, klt, true, begDate, endDate, KLINE_TYPE_BAN_KUAI);
        System.out.println(",开始日期:" + begDate + "，结束日期:" + endDate + "，周期:" + klt + "，klines.size():" + klines.size() + ",zqdm:" + zqdm);
//        System.out.println("klines:"+JSON.toJSONString(klines));
        for (Kline kline : klines) {
            RankBizDataDiff rankBizDataDiff = new RankBizDataDiff();
            rankBizDataDiff.setDate(kline.getKtime());
            rankBizDataDiff.setMonth(DateUtil.getYearMonth(kline.getKtime(), DateUtil.YYYY_MM_DD));
            rankBizDataDiff.setWeekYear(DateUtil.getYearWeek(kline.getKtime(), DateUtil.YYYY_MM_DD));
            rankBizDataDiff.setWeek(DateUtil.getWeekByYyyyMmDd(kline.getKtime(), DateUtil.YYYY_MM_DD));
            rankBizDataDiff.setType(DB_RANK_BIZ_TYPE_HANG_YE);
            rankBizDataDiff.setF1(2L);
            rankBizDataDiff.setF2(kline.getCloseAmt().doubleValue());
            rankBizDataDiff.setF3(kline.getZhangDieFu());
            rankBizDataDiff.setF4(kline.getZhangDieE().doubleValue());
            rankBizDataDiff.setF5(kline.getCjl().longValue());
            rankBizDataDiff.setF6(kline.getCje().longValue());
            rankBizDataDiff.setF7(kline.getZhenFu().doubleValue());
            rankBizDataDiff.setF8(kline.getHuanShouLv().doubleValue());
//            rankBizDataDiff.setF9(kline.getsh);
//            rankBizDataDiff.getF10(kline.get)
//            rankBizDataDiff.getF11()
            rankBizDataDiff.setF12(kline.getZqdm());
            rankBizDataDiff.setF13(DB_RANK_BIZ_F12_BAN_KUAI);
            rankBizDataDiff.setF14(kline.getZqmc());
            rankBizDataDiff.setF15(kline.getMaxAmt().doubleValue());
            rankBizDataDiff.setF16(kline.getMinAmt().doubleValue());
            rankBizDataDiff.setF17(kline.getOpenAmt().doubleValue());
            rankBizDataDiff.setF18(kline.getCloseAmt().subtract(kline.getZhangDieE()).doubleValue());//计算昨日收盘价：今日收盘价-今日涨跌额
            rankBizDataDiff.setF19(DB_RANK_BIZ_F19_BK_MAIN);
            rankBizDataDiff.setF27(DB_RANK_BIZ_F12_BAN_KUAI);
            rankBizDataDiff.setF29(DB_RANK_BIZ_F139_BK_MAIN);
            rankBizDataDiff.setF33(0.0);
            rankBizDataDiff.setF107(5L);
            rankBizDataDiff.setF111(0L);
            rankBizDataDiff.setF139(DB_RANK_BIZ_F139_BK_MAIN);
            rankBizDataDiff.setF152(DB_RANK_BIZ_F139_BK_MAIN);

            rankBizDataDiffList.add(rankBizDataDiff);
        }
//        System.out.println("rankBizDataDiffList:"+JSON.toJSONString(rankBizDataDiffList));

        BizRankDao.insertDbBiz(rankBizDataDiffList);//业务排行-插入
    }

    private static void updateDbTodayEtfNetMaxMinTime(String date, List<RankBizDataDiff> rankEtf) {
        for (RankBizDataDiff rankBizDataDiff : rankEtf) {
            rankBizDataDiff.setDate(date);
            rankBizDataDiff.setMonth(DateUtil.getYearMonth(date, DateUtil.YYYY_MM_DD));
            rankBizDataDiff.setWeekYear(DateUtil.getYearWeek(date, DateUtil.YYYY_MM_DD));
            rankBizDataDiff.setWeek(DateUtil.getWeekByYyyyMmDd(date, DateUtil.YYYY_MM_DD));
            findTrends(rankBizDataDiff, "", "fupan", rankBizDataDiff.getF12(), 1, date, "pt_sh_time_min", "pt_sh_time_max");//查询指定指数的最大值时间、最小值时间
            BizRankDao.updateDbEtfNetMaxMinTimeByDate(rankBizDataDiff);
        }
    }


    /**
     * 更新最新净值-限定时间段的最大最小净值
     *
     * @param date
     * @param rankEtf
     * @param table
     * @param days
     * @param dbFieldLastNetMin
     */
    private static void showUpdateDbMaxMinNetByDays(String date, List<RankBizDataDiff> rankEtf, String table, int days, String dbFieldLastNetMin, String dbFieldLastNetMax, String dbFieldLastNetMinClose, String dbFieldLastNetMaxClose) {
        for (RankBizDataDiff entity : rankEtf) {
            //查询 -限定时间段的最大最小净值
//            LsjzUtil.findJzMaxMin(fundTrade.getZqdm(), days);
            //k线
            String klt = "101";//klt=101:日;102:周;103:月;104:3月;105:6月;106:12月
            kline(entity, date, entity.getF12(), table, days, klt, dbFieldLastNetMin, dbFieldLastNetMax, dbFieldLastNetMinClose, dbFieldLastNetMaxClose);//沪深300
        }
    }


    /**
     * 更新日期
     */
    private static void updateDateBiz() {
        for (int i = 0; i < 1; i++) {
            String date = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, -i);
            RankBizDataDiff entity = new RankBizDataDiff();
            entity.setDate(date);
            entity.setMonth(DateUtil.getYearMonth(date, DateUtil.YYYY_MM_DD));
            entity.setWeekYear(DateUtil.getYearWeek(date, DateUtil.YYYY_MM_DD));
            entity.setWeek(DateUtil.getWeekByYyyyMmDd(date, DateUtil.YYYY_MM_DD));
            BizRankDao.updateDate(entity);
        }
    }

    /**
     * 查询-ETF-指数
     *
     * @param rankEtf
     * @param date
     * @param zhiShu            指数
     * @param table
     * @param days              数量
     * @param klt               K线周期类型
     * @param dbFieldLastNetMin
     * @param dbFieldLastNetMax
     */
    public static void kline(RankBizDataDiff rankEtf, String date, String zhiShu, String table, int days, String klt, String dbFieldLastNetMin, String dbFieldLastNetMax, String dbFieldLastNetMinClose, String dbFieldLastNetMaxClose) {
        long curTime = System.currentTimeMillis();
        StringBuffer url = new StringBuffer();
        url.append("http://96.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery331093188916841208381602168987937");
        if (zhiShu.startsWith("5") || zhiShu.startsWith("6")) {
            url.append("&secid=" + "1." + zhiShu);
        } else {
            url.append("&secid=" + "0." + zhiShu);
        }

        url.append("&ut=fa5fd1943c7b386f172d6893dbfba10b");
        url.append("&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5%2Cf6");
        url.append("&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58%2Cf59%2Cf60%2Cf61");
        url.append("&klt=" + klt);
        url.append("&fqt=1");
        url.append("&end=20500101");
        url.append("&lmt=" + days);
        url.append("&_=" + curTime);

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("&StartDate=").append(startDate);

//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
        String rs = "";
        try {
            rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
        } catch (Exception e) {
            System.out.println("/** http重试 **/");
            rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
        }

        /**
         * 如果返回异常，n次重试
         */
        for (int i = 0; i < 10; i++) {
            if (StringUtils.isBlank(rs)) {
                rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
            } else {
                break;
            }
        }

        String rsJson = rs.substring(rs.indexOf("{"));
        rsJson = rsJson.replace(");", "");
//        System.out.println("szKline:" + rsJson);

        List<String> klineList = new ArrayList<String>();
        JSONObject szzzMonthJson = JSON.parseObject(rsJson);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
//        String name = szzzMonthDataJson.getString("name");
//        System.out.println("指数名称："+name);
        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        if (klines != null) {
            for (Object kline : klines) {
                String klineStr = (String) kline;
                klineList.add(klineStr);
            }
        }

        Map<String, Double> netRs = handlerMaxJz(klineList);
        Double minJz = netRs.get(Content.keyRsMin);
        Double maxJz = netRs.get(Content.keyRsMax);
        Double netCloseMin = netRs.get(Content.keyRsNetCloseMin);
        Double netCloseMax = netRs.get(Content.keyRsNetCloseMax);

//        StringBuffer sb = new StringBuffer();
//        sb.append("UPDATE `" + table + "` ");
//        sb.append("SET ");
//        sb.append(" `" + dbFieldLastNetMin + "`=" + minJz + ", ");
//        sb.append(" `" + dbFieldLastNetMinClose + "`=" + netCloseMin + ", ");
//        sb.append(" `" + dbFieldLastNetMax + "`=" + maxJz + ", ");
//        sb.append(" `" + dbFieldLastNetMaxClose + "`=" + netCloseMax + " ");
////        sb.append(" WHERE `FD_CODE`='" + zhiShu + "'" + " AND TYPE = '证券买入'" + ";");
//        sb.append(" WHERE `f12`='" + zhiShu + "'" + "");
//        sb.append(" AND `date`='" + date + "'" + "");
//        sb.append(";");
//        sb.append("/**" + szzzMonthDataJson.getString("name") + "**/");
//        System.out.println(sb);

        //insertDb
        rankEtf.setDate(date);
        rankEtf.setMonth(DateUtil.getYearMonth(date, DateUtil.YYYY_MM_DD));
        rankEtf.setWeekYear(DateUtil.getYearWeek(date, DateUtil.YYYY_MM_DD));
        rankEtf.setWeek(DateUtil.getWeekByYyyyMmDd(date, DateUtil.YYYY_MM_DD));
        if (days == 1) {
            rankEtf.setLAST_NET(new BigDecimal(netCloseMin));
            rankEtf.setNET_MIN_1(new BigDecimal(minJz));
            rankEtf.setNET_MIN_CLOS_1(new BigDecimal(netCloseMin));
            rankEtf.setNET_MAX_1(new BigDecimal(maxJz));
            rankEtf.setNET_MAX_CLOS_1(new BigDecimal(netCloseMax));
        }
        if (days == 7) {
            rankEtf.setNET_MIN_7(new BigDecimal(minJz));
            rankEtf.setNET_MIN_CLOS_7(new BigDecimal(netCloseMin));
            rankEtf.setNET_MAX_7(new BigDecimal(maxJz));
            rankEtf.setNET_MAX_CLOS_7(new BigDecimal(netCloseMax));
        }
        if (days == 14) {
            rankEtf.setNET_MIN_14(new BigDecimal(minJz));
            rankEtf.setNET_MIN_CLOS_14(new BigDecimal(netCloseMin));
            rankEtf.setNET_MAX_14(new BigDecimal(maxJz));
            rankEtf.setNET_MAX_CLOS_14(new BigDecimal(netCloseMax));
        }
        if (days == 30) {
            rankEtf.setNET_MIN_30(new BigDecimal(minJz));
            rankEtf.setNET_MIN_CLOS_30(new BigDecimal(netCloseMin));
            rankEtf.setNET_MAX_30(new BigDecimal(maxJz));
            rankEtf.setNET_MAX_CLOS_30(new BigDecimal(netCloseMax));
        }
        if (days == 60) {
            rankEtf.setNET_MIN_60(new BigDecimal(minJz));
            rankEtf.setNET_MIN_CLOS_60(new BigDecimal(netCloseMin));
            rankEtf.setNET_MAX_60(new BigDecimal(maxJz));
            rankEtf.setNET_MAX_CLOS_60(new BigDecimal(netCloseMax));
        }
        if (days == 90) {
            rankEtf.setNET_MIN_90(new BigDecimal(minJz));
            rankEtf.setNET_MIN_CLOS_90(new BigDecimal(netCloseMin));
            rankEtf.setNET_MAX_90(new BigDecimal(maxJz));
            rankEtf.setNET_MAX_CLOS_90(new BigDecimal(netCloseMax));
        }
        if (days == 180) {
            rankEtf.setNET_MIN_180(new BigDecimal(minJz));
            rankEtf.setNET_MIN_CLOS_180(new BigDecimal(netCloseMin));
            rankEtf.setNET_MAX_180(new BigDecimal(maxJz));
            rankEtf.setNET_MAX_CLOS_180(new BigDecimal(netCloseMax));
        }
        if (days == 365) {
            rankEtf.setNET_MIN_360(new BigDecimal(minJz));
            rankEtf.setNET_MIN_CLOS_360(new BigDecimal(netCloseMin));
            rankEtf.setNET_MAX_360(new BigDecimal(maxJz));
            rankEtf.setNET_MAX_CLOS_360(new BigDecimal(netCloseMax));
        }


    }

    /**
     * 计算最大净值、最小净值
     *
     * @return
     */
    private static Map<String, Double> handlerMaxJz(List<String> klineList) {
        Map<String, Double> rs = new HashMap<String, Double>();
        Double rsMax = 0.0;
        Double rsMin = 0.0;
        Double lastDwjz = 0.0;
        Double rsNetCloseMin = 0.0;
        Double rsNetCloseMax = 0.0;
        int curTempInt = 0;
        for (String klineStr : klineList) {
            //  日期，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率
            //"2020-09-30,3389.74,3218.05,3425.63,3202.34,4906229054,6193724911616.00,6.58,-5.23,-177.63,13.40"
            String[] klineArray = klineStr.split(",");
            String shouPan = klineArray[2];
            String netMax = klineArray[3];
            String netMin = klineArray[4];
            String zhangDie = klineArray[8];
            String chengJiaoE = klineArray[6];
            String curDate = klineArray[0];
//            System.out.print("日期:" + curDate + ",");
//            System.out.print("收盘:" + shouPan + ",");
//            System.out.print("涨跌幅:" + zhangDie + ",\t");
//            System.out.print("开盘:" + klineArray[1] + ",\t");
//            System.out.print("最高:" + klineArray[3] + ",");
//            System.out.print("最低:" + klineArray[4] + ",");
//            System.out.print("成交量:\t" + klineArray[5] + ",\t\t");
//            System.out.print("成交额:\t" + klineArray[6] + ",\t\t");
//            System.out.print("振幅:" + klineArray[7] + ",");
//            System.out.print("涨跌额:" + klineArray[9] + ",");
//            System.out.print("换手率:" + klineArray[10] + ",");
//            System.out.println();

//                    System.out.println(JSON.toJSONString(lsjzDataLsjz));
//            String dwJz = lsjzDataLsjz.getDWJZ();//当晚净值
            String dwJz = shouPan;//累计净值

            if (StringUtils.isBlank(dwJz)) {
                dwJz = "0";
            }
            String fsrq = curDate;
//            System.out.println("fsrq:" + fsrq + ",dwjzLong:" + dwJz);

            Double netMaxDou = Double.valueOf(netMax);
            if (netMaxDou > rsMax) {
                rsMax = netMaxDou;
            }
            Double netMinDou = Double.valueOf(netMin);
            if (netMinDou < rsMin || rsMin == 0.0) {
                rsMin = netMinDou;
            }

            //
            Double dwjzLong = Double.valueOf(dwJz);
            if (dwjzLong > rsNetCloseMax) {
                rsNetCloseMax = dwjzLong;
            }
            if (dwjzLong < rsNetCloseMin || rsNetCloseMin == 0.0) {
                rsNetCloseMin = dwjzLong;
            }
        }
        rs.put(Content.keyRsMax, rsMax);
        rs.put(Content.keyRsMin, rsMin);
        rs.put(Content.keyRsNetCloseMin, rsNetCloseMin);
        rs.put(Content.keyRsNetCloseMax, rsNetCloseMax);
        return rs;
    }


    /**
     * 显示业务排行-插入sql
     *
     * @param today
     * @param rankBizDataDiffList
     * @param queryType
     */
    private static void showBizSql(String today, List<RankBizDataDiff> rankBizDataDiffList, String queryType) {
        int orderNum = 0;//序号
        for (RankBizDataDiff entity : rankBizDataDiffList) {
            orderNum++;
            //显示插入数据库语句
            {
                System.out.println("INSERT INTO `bank19`.`rank_st_biz`(" +
                        "`rs`,`date`,`type`,`order_num`," +
                        "`f1`,`f2`,`f3`,`f4`" +
                        ",`f5`,`f6`,`f7`,`f8`,`f9`" +
                        ",`f10`,`f11`,`f12`,`f13`,`f14`" +
                        ",`f15`,`f16`,`f17`,`f18`,`f19`" +
                        ",`f20`,`f21`,`f22`,`f23`,`f24`" +
                        ",`f25`,`f26`,`f27`,`f28`,`f29`" +
                        ",`f30`,`f31`,`f32`,`f33`,`f34`" +
                        ",`f35`,`f36`,`f37`,`f38`,`f39`" +
                        ",`f60`,`f61`,`f62`,`f63`,`f64`" +
                        ",`f65`,`f66`,`f67`,`f68`,`f69`" +
                        ",`f70`,`f71`,`f72`,`f73`,`f74`" +
                        ",`f75`,`f76`,`f77`,`f78`,`f79`" +
                        ",`f80`,`f81`,`f82`,`f83`,`f84`" +
                        ",`f85`,`f86`,`f87`,`f88`,`f89`" +
                        ",`f90`,`f91`,`f92`,`f93`,`f94`" +
                        ",`f95`,`f96`,`f97`,`f98`,`f99`" +
                        ",`f104`" +
                        ",`f105`,`f107`,`f109`" +
                        ",`f110`,`f111`" +
                        ",`f115`" +
                        ",`f124`" +
                        ",`f127`,`f128`" +
                        ",`f136`,`f139`" +
                        ",`f140`,`f141`,`f142`,`f143`,`f144`" +
                        ",`f145`,`f148`,`f149`" +
                        ",`f152`" +
                        ",`f207`" +
                        ",`f208`" +
                        ",`f209`" +
                        ",`f222`,`f223`" +
                        ") VALUES (" +
                        " ''" +
                        " ,'" + today + "'" +
                        " ,'" + queryType + "'" +
                        " ," + orderNum + "" +
                        " ," + entity.getF1() + "," + entity.getF2() + "," + entity.getF3() + "," + entity.getF4() + "," + entity.getF5() + "" +
                        " ," + entity.getF6() + "," + entity.getF7() + "," + entity.getF8() + "," + entity.getF9() +
                        "," + entity.getF10() + "," + entity.getF11() + ",'" + entity.getF12() + "'," + entity.getF13() + ",'" + entity.getF14() + "'" +
                        " ," + entity.getF15() + "," + entity.getF16() + "," + entity.getF17() + "," + entity.getF18() + "," + entity.getF19() +
                        " ," + entity.getF20() + "," + entity.getF21() + "," + entity.getF22() + ",'" + (entity.getF23() == null ? "" : entity.getF23()) + "'," + entity.getF24() + "" +
                        " ," + entity.getF25() + ",'" + (entity.getF26() == null ? "" : entity.getF26()) + "'," + entity.getF27() + "," + entity.getF28() + "," + entity.getF29() + "" +
                        " ," + entity.getF30() + "," + entity.getF31() + "," + entity.getF32() + "," + entity.getF33() + "," + entity.getF34() + "" +
                        " ," + entity.getF35() + "," + entity.getF36() + "," + entity.getF37() + "," + entity.getF38() + "," + entity.getF39() + "" +
                        " ," + entity.getF60() + "," + entity.getF61() + "," + entity.getF62() + "," + entity.getF63() + "," + entity.getF64() + "" +
                        " ," + entity.getF65() + "," + entity.getF66() + "," + entity.getF67() + "," + entity.getF68() + "," + entity.getF69() + "" +
                        " ," + entity.getF70() + "," + entity.getF71() + "," + entity.getF72() + "," + entity.getF73() + "," + entity.getF74() + "" +
                        " ," + entity.getF75() + "," + entity.getF76() + "," + entity.getF77() + "," + entity.getF78() + "," + entity.getF79() + "" +
                        " ," + entity.getF80() + "," + entity.getF81() + "," + entity.getF82() + "," + entity.getF83() + "," + entity.getF84() + "" +
                        " ," + entity.getF85() + "," + entity.getF86() + "," + entity.getF87() + "," + entity.getF88() + "," + entity.getF89() + "" +
                        " ," + entity.getF90() + "," + entity.getF91() + "," + entity.getF92() + "," + entity.getF93() + "," + entity.getF94() + "" +
                        " ," + entity.getF95() + "," + entity.getF96() + "," + entity.getF97() + "," + entity.getF98() + "," + entity.getF99() + "" +
                        " ," + entity.getF104() + "" +
                        " ," + entity.getF105() + "," + entity.getF107() + "" + "," + entity.getF109() + "" +
                        " ," + entity.getF110() + "," + entity.getF111() +
                        " ,'" + (entity.getF115() == null ? "" : entity.getF115()) + "'" +
                        " ," + entity.getF124() + "" +
                        " ," + entity.getF127() + "," + "'" + (entity.getF128() == null ? "" : entity.getF128()) + "'" +
                        " ," + entity.getF136() + "," + entity.getF139() + "" +
                        " ,'" + (entity.getF140() == null ? "" : entity.getF140()) + "'" + "," + entity.getF141() + "," + entity.getF142() + "," + entity.getF143() + "," + entity.getF144() + "" +
                        " ," + entity.getF145() + "" + " ," + entity.getF148() + "" + " ," + entity.getF149() + "" +
                        " ," + entity.getF152() + "" +
                        " ,'" + (entity.getF207() == null ? "" : entity.getF207()) + "'" +
                        " ,'" + (entity.getF208() == null ? "" : entity.getF208()) + "'" +
                        " ," + entity.getF209() + "" +
                        " ," + entity.getF222() + "" + " ," + entity.getF223() +
                        ");");
            }

        }
    }

    /**
     * 查询排名-概念板块
     *
     * @param date
     * @param type
     * @param endCount
     */
    public static List<RankBizDataDiff> listConcept(String date, String type, int endCount) {
        long curTime = System.currentTimeMillis();
        //http://28.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112408110589206747254_1616379873172&pn=1&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:90+t:2+f:!50&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222&_=1616379873199
//        http://70.push2.eastmoney.com/api/qt/clist/get?cb=jQuery1124026081630094811414_1617261240739&pn=1&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:90+t:3+f:!50&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222&_=1617261240740
        StringBuffer urlParam = new StringBuffer();
//        String url = "http://api.fund.eastmoney.com/ztjj/GetZTJJList";
//        urlParam.append("callback=jQuery311015658602123786958_1591838943711&callbackname=fundData");
//        urlParam.append("&st="+findDateType);//查询类型
//        urlParam.append("&_=1614523183291");

        String url = "http://70.push2.eastmoney.com/api/qt/clist/get";
        urlParam.append("cb=jQuery1124026081630094811414_1617261240739" +
                "&pn=1" +//页数
                "&pz=" + endCount +//每页数量
                "&po=1" +//pageorder:页面排序：0-正序；1-倒序
                "&np=1" +
                "&ut=bd1d9ddb04089700cf9c27f6f7426281" +
                "&fltt=2" +//浮点数精度
                "&invt=3" +//显示格式：-；0.0
                "&fid=f3" +//排序字段
//                "&fs=m:90+t:2+f:!50" +
                "&fs=m:90+t:3+f:!50" +
                "&fields=" +
                "f1,f2,f3,f4,f5,f6,f7,f8,f9," +
                "f10,f11,f12,f13,f14,f15,f16,f17,f18,f19," +
                "f20,f21,f22,f23,f24,f25,f26,f27,f28,f29," +
                "f30,f31,f32,f33,f34,f35,f36,f37,f38,f39," +
                "f40,f41,f42,f43,f44,f45,f46,f47,f48,f49," +
                "f50,f51,f52,f53,f54,f55,f56,f57,f58,f59," +
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
        String rs = "";
        JSONObject rsJsonObj = null;
        JSONArray jsonArrayBiz = new JSONArray();
        for (int i = 0; i < 10; i++) {
            rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);//返回结果
            if (rs.startsWith("jQuery")) {
                rs = rs.substring(rs.indexOf("{"));
            }
            if (rs.endsWith(");")) {
                rs = rs.substring(0, rs.length() - 2);
            }
            rsJsonObj = JSONObject.parseObject(rs);
            if (rs == null || rsJsonObj == null || !rsJsonObj.containsKey("data")) {
                System.out.println("查询数据异常，重新查询：" + rs);
                continue;
            } else {
                break;
            }
        }

        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
        List<RankBizDataDiff> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankBizDataDiff.class);
        for (RankBizDataDiff row : rankBizDataDiffList) {
//            row.setRs(rs);
            row.setDate(date);
            row.setMonth(DateUtil.getYearMonth(date, DateUtil.YYYY_MM_DD));
            row.setWeekYear(DateUtil.getYearWeek(date, DateUtil.YYYY_MM_DD));
            row.setWeek(DateUtil.getWeekByYyyyMmDd(date, DateUtil.YYYY_MM_DD));
            row.setType(type);
//            System.out.println(JSON.toJSON(row));//每个行业一行数据
            JSONObject jsonObjectBiz = new JSONObject();
            jsonObjectBiz.put("name", row.getF14());
            jsonObjectBiz.put("rate", row.getF3());
            jsonArrayBiz.add(jsonObjectBiz);
        }
//        System.out.println(jsonArrayBiz);//行业排行数组

        for (Object obj : jsonArrayBiz) {
            JSONObject jsonObjectBiz = (JSONObject) obj;
//            System.out.println(jsonObjectBiz);
        }

        return rankBizDataDiffList;
    }

    /**
     * 查询指定指数的最大值时间、最小值时间
     *
     * @param rankBizDataDiff
     * @param cookie
     * @param zhiShu
     * @param days
     * @param curDate
     * @param field1
     * @param field2
     */
    private static void findTrends(RankBizDataDiff rankBizDataDiff, String cookie, String table, String zhiShu, int days, String curDate, String field1, String field2) {
        long curTime = System.currentTimeMillis();
        String code = "";
        String url = "http://push2his.eastmoney.com/api/qt/stock/trends2/get?cb=jQuery112408829480231577647_" + curTime;
        if (zhiShu.startsWith("5") || zhiShu.startsWith("6")) {
            code = "&secid=" + "1." + zhiShu;
        } else {
            code = "&secid=" + "0." + zhiShu;
        }
        url = url + code +
                "&ut=fa5fd1943c7b386f172d6893dbfba10b" +
                "&fields1=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11" +
                "&fields2=f51,f53,f56,f58" +
                "&iscr=0" +
                "&ndays=" + days +
                "&_=" + curTime +
                "";
//        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("&StartDate=").append(startDate);

//        System.out.println("请求url:"+url);
        String rs = HttpUtil.sendGet(url, "", cookie);
        /**
         * 如果返回异常，n次重试
         */
        for (int i = 0; i < 10; i++) {
            if (StringUtils.isBlank(rs)) {
                rs = HttpUtil.sendGet(url, "", cookie);
            } else {
                break;
            }
        }
        if (!rs.contains("{")) {
            System.out.println("返回数据异常：" + JSON.toJSONString(rs));
            return;
        }
        String rsJson = rs.substring(rs.indexOf("{"));
        rsJson = rsJson.replace(");", "");
//        System.out.println("szKline:"+rsJson);

        List<String> timePointList = new ArrayList<String>();
        JSONObject szzzMonthJson = JSON.parseObject(rsJson);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
        JSONArray trends = JSON.parseArray(szzzMonthDataJson.getString("trends"));
//        System.out.println(trends);
        for (Object timePoint : trends) {
            String timePointStr = (String) timePoint;
            timePointList.add(timePointStr);
        }

        BigDecimal dayPointMax = new BigDecimal(0);
        BigDecimal dayPointMin = new BigDecimal(0);
        String dayPointTimeMax = "";
        String dayPointTimeMin = "";
        boolean init = false;
        for (int i = 0; i <= timePointList.size() - 1; i++) {
            //"2021-04-23 09:30,2857.23,60147,2857.226"
            String timePoint = timePointList.get(i);
            String[] timePointArray = timePoint.split(",");
            String dateTime = timePointArray[0];
            String date = dateTime.substring(0, 10);
            //指定具体日期
            if (!curDate.equals(date)) {
                continue;
            }
            String point = timePointArray[1];
//            String count = timePointArray[2];
//            String point2 = timePointArray[3];
            //初始化
            if (!init) {
                init = true;
                dayPointMax = new BigDecimal(point);
                dayPointTimeMax = dateTime.substring(dateTime.indexOf(" "));
                dayPointMin = new BigDecimal(point);
                dayPointTimeMin = dateTime.substring(dateTime.indexOf(" "));
            }

            //比较最大最小
            BigDecimal curPoint = new BigDecimal(point);
            if (curPoint.compareTo(dayPointMin) <= 0) {
                dayPointMin = new BigDecimal(point);
                dayPointTimeMin = dateTime.substring(dateTime.indexOf(" ") + 1);
                rankBizDataDiff.setPt_time_min(dayPointTimeMin);
            }
            if (curPoint.compareTo(dayPointMax) >= 0) {
                dayPointMax = new BigDecimal(point);
                dayPointTimeMax = dateTime.substring(dateTime.indexOf(" ") + 1);
                rankBizDataDiff.setPt_time_max(dayPointTimeMax);
            }
        }
//        System.out.println("最大值：" + dayPointTimeMax + "---" + dayPointMax);
//        System.out.println("最小值：" + dayPointTimeMin + "---" + dayPointMin);

        //sql
//        System.out.println("UPDATE `" + table + "` " +
//                "SET " +
//                "`" + field1 + "`='" + dayPointTimeMin + "'" + "," +
//                "`" + field2 + "`='" + dayPointTimeMax + "'" +
//                " WHERE (`CODE`='" + curDate + "') AND fupan.period='1'" + " AND fupan.TYPE=1;");
    }

}
