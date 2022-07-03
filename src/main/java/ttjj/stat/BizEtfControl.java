package ttjj.stat;

import ttjj.dao.BizRankDao;
import ttjj.dto.RankBizDataDiff;
import ttjj.dto.StatEtfUpDown;
import ttjj.dto.StockAdrCountVo;
import ttjj.service.KlineService;
import utils.ContentEtf;
import utils.DateUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static utils.Content.*;
import static utils.DateUtil.YYYY_MM_DD;

/**
 * 主题排行
 */
public class BizEtfControl {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-06-01";
//        spDate = DateUtil.getAddDays(YYYY_MM_DD, date, 1);//是否显示特定日期涨跌   "2022-05-18"
        String spDate = "";//
//        List<String> dateList = StockService.findListDateAfter(date, 2);
//        if (dateList != null && dateList.size() > 1) {
//            spDate = dateList.get(1);//是否显示特定日期涨跌   "2022-05-18"
//        }
        boolean isShowPriceArea = true;//是否显示价格区间
//        boolean isShowPriceArea = false;//是否显示价格区间
        List<String> kltList = Arrays.asList(KLT_5, KLT_15, KLT_30, KLT_60, KLT_101);//价格区间周期列表
//        kltList.add(KLT_5);
//        kltList.add(KLT_102);

        boolean isShowUpMa = true;//是否显示-超过均线
//        boolean isShowUpMa = false;//是否显示-超过均线
        boolean isShowDownMa = true;//是否显示-跌落均线
        boolean isFindKline = true;//是否查询最新k线
        boolean isShowFlowIn = true;//是否显示资金流入

//        Map<String, String> etfBizMap = ContentEtf.mapEtfAll;//mapEtfBiz mapEtfIndex    mapEtfAll
//        Map<String, String> etfBizMap = ContentEtf.mapEtfIndex;//mapEtfBiz mapEtfIndex    mapEtfAll
//        Map<String, String> etfBizMap = ContentEtf.mapEtfBiz;//mapEtfBiz mapEtfIndex    mapEtfAll
//        List<StockAdrCountVo> rs = checkMaDemo(etfBizMap, date, isShowPriceArea, isShowUpMa, isFindKline, kltList);
//        showStockMa(rs, ORDER_FIELD_NET_AREA_DAY_5, false, isShowPriceArea, isShowUpMa, kltList, spDate);

        String orderField = ORDER_FIELD_NET_AREA_DAY_5;//ORDER_FIELD_NET_AREA_DAY_5 ORDER_FIELD_F3
        boolean isOrderDesc = false;//是否倒序
        List<StockAdrCountVo> rs = null;
        System.out.println("科技：");
        rs = KlineService.checkMaDemo(ContentEtf.mapEtfKeJi, date, isShowPriceArea, isShowPriceArea, isShowUpMa, isFindKline, kltList);
        KlineService.showStockMa(rs, orderField, isOrderDesc, isShowPriceArea, isShowUpMa,isShowDownMa, isShowFlowIn,  kltList, spDate);
        System.out.println("消费：");
        rs = KlineService.checkMaDemo(ContentEtf.mapEtfXiaoFei, date, isShowPriceArea, isShowPriceArea, isShowUpMa, isFindKline, kltList);
        KlineService.showStockMa(rs, orderField, isOrderDesc, isShowPriceArea, isShowUpMa,isShowDownMa, isShowFlowIn,  kltList, spDate);
        System.out.println("资源：");
        rs = KlineService.checkMaDemo(ContentEtf.mapEtfZiYuan, date, isShowPriceArea, isShowPriceArea, isShowUpMa, isFindKline, kltList);
        KlineService.showStockMa(rs, orderField, isOrderDesc, isShowPriceArea, isShowUpMa,isShowDownMa, isShowFlowIn,  kltList, spDate);
        System.out.println("医疗：");
        rs = KlineService.checkMaDemo(ContentEtf.mapEtfYiLiao, date, isShowPriceArea, isShowPriceArea, isShowUpMa, isFindKline, kltList);
        KlineService.showStockMa(rs, orderField, isOrderDesc, isShowPriceArea, isShowUpMa,isShowDownMa, isShowFlowIn,  kltList, spDate);
        System.out.println("金融：");
        rs = KlineService.checkMaDemo(ContentEtf.mapEtfJinRong, date, isShowPriceArea, isShowUpMa,isShowDownMa, isFindKline, kltList);
        KlineService.showStockMa(rs, orderField, isOrderDesc, isShowPriceArea, isShowUpMa,isShowDownMa, isShowFlowIn, kltList, spDate);
        System.out.println("指数：");
        rs = KlineService.checkMaDemo(ContentEtf.mapEtfIndex, date, isShowPriceArea, isShowPriceArea, isShowUpMa, isFindKline, kltList);
        KlineService.showStockMa(rs, orderField, isOrderDesc, isShowPriceArea, isShowUpMa,isShowDownMa, isShowFlowIn,  kltList, spDate);

//        showStockMa(rs, ORDER_FIELD_F3, true, isShowPriceArea, isShowUpMa, kltList, spDate);

//        listEtfBizDb(ContentEtf.mapEtfAll.keySet(), 0, true, true);//列表查询-行业etf-排序：涨跌幅
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
            String date = DateUtil.getCurDateStrAddDaysByFormat(YYYY_MM_DD, -i);
            Map<String, Object> condition = new HashMap<>();
            condition.put("list", etfBizSet);
            condition.put("date", date);
            List<RankBizDataDiff> rankListUp = BizRankDao.listEtfBiz(condition);
            if (rankListUp == null) {
                continue;
            }
            List<RankBizDataDiff> rankListDown = rankListUp.stream().filter(e -> e != null).sorted(Comparator.comparing(RankBizDataDiff::getF3, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());//倒序
            String curWeekNo = DateUtil.getWeekByYyyyMmDd(date, YYYY_MM_DD);
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
    public static String handlerEtfName(String name) {
        name = name.replace("ETF", "");
        name = name.replace("基金", "");
        name = name.replace("有色金属", "有色");
        name = name.replace("基建50", "基建");
        name = name.replace("能源化工", "能源化工");
        name = name.replace("中概互联网", "中概");
        return name;
    }




}
