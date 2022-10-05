package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.BizRankDao;
import ttjj.dto.Kline;
import ttjj.dto.RankBizDataDiff;
import ttjj.dto.StatEtfUpDown;
import ttjj.service.BizService;
import ttjj.service.FundFlowService;
import ttjj.service.KlineService;
import utils.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static utils.Content.*;

/**
 * 主题排行
 */
public class BizControl {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-05-24";
//        boolean isOnlyGn = true;
        boolean isOnlyGn = false;
        boolean isDelAndAddBiz = true;//是否删除后插入
//        boolean isDelAndAddBiz = false;//是否删除后插入
//        boolean isUpdateDayMa = true;//是否更新日均线
        boolean isUpdateDayMa = false;//是否更新日均线
//        boolean isUpdateDay15MinuteNet = true;//是否更新当日15分钟净值
        boolean isUpdateDay15MinuteNet = false;//是否更新当日15分钟净值

        String bizType = "";
        List<RankBizDataDiff> bizList = null;

        bizType = DB_RANK_BIZ_TYPE_BAN_KUAI;
        bizList = BizService.listBiz(date, bizType, NUM_MAX_999);//查询板块行业列表
        saveBizAndKline(date, bizType, bizList, isDelAndAddBiz, isUpdateDayMa, isUpdateDay15MinuteNet);//保存业务和k线

        //etf
        bizType = DB_RANK_BIZ_TYPE_ETF;
        bizList = BizService.listBiz(date, bizType, NUM_MAX_999);//查询板块行业列表
        if (DB_RANK_BIZ_TYPE_ETF.equals(bizType)) {
            //过滤etf
            bizList = EtfUtil.handlerEtfList(bizList, bizType);
        }
        saveBizAndKline(date, bizType, bizList, isDelAndAddBiz, isUpdateDayMa, isUpdateDay15MinuteNet);//保存业务和k线

        if (!isOnlyGn) {
            bizType = DB_RANK_BIZ_TYPE_GAI_NIAN;
            bizList = BizService.listBiz(date, bizType, NUM_MAX_999);//查询板块行业列表
            saveBizAndKline(date, bizType, bizList, isDelAndAddBiz, isUpdateDayMa, isUpdateDay15MinuteNet);//保存业务和k线

            //插入当日所有etf
            bizType = DB_RANK_BIZ_TYPE_ETF;
            deleteTodayBiz(date, bizType);//删除数据-今日
            bizList = BizService.listBiz(date, bizType, NUM_MAX_999);//查询列表
            BizService.insertTodayRank(bizType, bizList);

//            updateFundFlow(date,DB_RANK_BIZ_TYPE_BAN_KUAI);//更新当日资金流信息-板块
//            updateFundFlow(date,DB_RANK_BIZ_TYPE_GAI_NIAN);//更新当日资金流信息-概念
//            updateFundFlow(date,DB_RANK_BIZ_TYPE_ETF);////更新当日资金流信息-etf
        }


//        /**
//         * 更新均值
//         */
//        List<RankBizDataDiff> bizList = listBiz(date, DB_RANK_BIZ_TYPE_BAN_KUAI, NUM_MAX_999);//查询主题排名by时间类型、显示个数
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
     *
     * @param date                   日期
     * @param bizType                业务
     * @param bizList                对象列表
     * @param isDelAndAddBiz         是否删除后插入
     * @param isUpdateDayMa          是否更新日均线
     * @param isUpdateDay15MinuteNet 是否更新当日15分钟净值
     */
    private static void saveBizAndKline(String date, String bizType, List<RankBizDataDiff> bizList, boolean isDelAndAddBiz, boolean isUpdateDayMa, boolean isUpdateDay15MinuteNet) {
        if (isDelAndAddBiz) {
            deleteTodayBiz(date, bizType);//删除数据-今日
            BizService.insertTodayRank(bizType, bizList);
        }
        if (isUpdateDayMa) {
            updateDbTodayEtfMa(date, bizType, bizList);
        }
        if (isUpdateDay15MinuteNet) {
            updateDbTodayNetCloseByKlt(date, KLT_15, bizType, bizList);
        }
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
     * 更新当日资金流信息-板块
     *
     * @param date
     */
    private static void updateFundFlow(String date, String type) {
        List<RankBizDataDiff> rankList = new ArrayList<>();
        if (DB_RANK_BIZ_TYPE_BAN_KUAI.equals(type)) {
            rankList = BizService.listBiz(date, DB_RANK_BIZ_TYPE_BAN_KUAI, NUM_MAX_999);//查询板块行业列表
        }
        if (DB_RANK_BIZ_TYPE_BAN_KUAI.equals(type)) {
            rankList = BizService.listBiz(date, DB_RANK_BIZ_TYPE_ETF, NUM_MAX_999);//查询板块行业列表
        }
        for (RankBizDataDiff etf : rankList) {
            String stCode = etf.getF12();
            String rsFundFlow = FundFlowService.httpFundFlowRs(stCode, null, KLT_1);

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
            String rsFundFlow = FundFlowService.httpFundFlowRs(stCode, null, KLT_1);

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
            Map<String, BigDecimal> netMap = KlineService.findNetMinMaxAvg(zqdm, ma5, KLT_101, false, "", date, DB_RANK_BIZ_TYPE_BAN_KUAI);
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
        List<RankBizDataDiff> hangYeList = BizService.listBiz(endDate, DB_RANK_BIZ_TYPE_BAN_KUAI, NUM_MAX_999);//查询所有行业列表
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
//            List<RankBizDataDiff> rankBizDataDiffListBiz = listBiz(date, DB_RANK_BIZ_TYPE_BAN_KUAI, NUM_MAX_999);//查询板块行业列表
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
        List<Kline> klines = KlineService.kline(zqdm, lmt, klt, true, begDate, endDate, DB_RANK_BIZ_TYPE_ETF);
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
            rankBizDataDiff.setF2(kline.getCloseAmt());
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
            rankBizDataDiff.setF15(kline.getMaxAmt());
            rankBizDataDiff.setF16(kline.getMinAmt());
            rankBizDataDiff.setF17(kline.getOpenAmt());
            rankBizDataDiff.setF18(kline.getCloseAmt().subtract(kline.getZhangDieE()));//计算昨日收盘价：今日收盘价-今日涨跌额

            rankBizDataDiffList.add(rankBizDataDiff);
        }
//        System.out.println("rankBizDataDiffList:"+JSON.toJSONString(rankBizDataDiffList));

        BizRankDao.insertDbBiz(rankBizDataDiffList);//业务排行-插入
    }

    private static void insertHisDbBanKuai(String zqdm, String begDate, String endDate) {
        String klt = KLT_101;//klt=5:5分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
        int lmt = 1000000;
        List<RankBizDataDiff> rankBizDataDiffList = new ArrayList<>();
        List<Kline> klines = KlineService.kline(zqdm, lmt, klt, true, begDate, endDate, DB_RANK_BIZ_TYPE_BAN_KUAI);
        System.out.println(",开始日期:" + begDate + "，结束日期:" + endDate + "，周期:" + klt + "，klines.size():" + klines.size() + ",zqdm:" + zqdm);
//        System.out.println("klines:"+JSON.toJSONString(klines));
        for (Kline kline : klines) {
            RankBizDataDiff rankBizDataDiff = new RankBizDataDiff();
            rankBizDataDiff.setDate(kline.getKtime());
            rankBizDataDiff.setMonth(DateUtil.getYearMonth(kline.getKtime(), DateUtil.YYYY_MM_DD));
            rankBizDataDiff.setWeekYear(DateUtil.getYearWeek(kline.getKtime(), DateUtil.YYYY_MM_DD));
            rankBizDataDiff.setWeek(DateUtil.getWeekByYyyyMmDd(kline.getKtime(), DateUtil.YYYY_MM_DD));
            rankBizDataDiff.setType(DB_RANK_BIZ_TYPE_BAN_KUAI);
            rankBizDataDiff.setF1(2L);
            rankBizDataDiff.setF2(kline.getCloseAmt());
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
            rankBizDataDiff.setF15(kline.getMaxAmt());
            rankBizDataDiff.setF16(kline.getMinAmt());
            rankBizDataDiff.setF17(kline.getOpenAmt());
            rankBizDataDiff.setF18(kline.getCloseAmt().subtract(kline.getZhangDieE()));//计算昨日收盘价：今日收盘价-今日涨跌额
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
            BizService.kline(entity, date, entity.getF12(), table, days, klt, dbFieldLastNetMin, dbFieldLastNetMax, dbFieldLastNetMinClose, dbFieldLastNetMaxClose);//沪深300
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
