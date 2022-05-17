package ttjj.stat;

import org.apache.commons.lang3.StringUtils;
import ttjj.dao.BizRankDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.RankBizDataDiff;
import ttjj.dto.StatEtfUpDown;
import ttjj.dto.StockAdrCountVo;
import ttjj.rank.StockDemo;
import ttjj.service.KlineService;
import utils.ContentEtf;
import utils.DateUtil;
import utils.EtfUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static utils.Content.*;

/**
 * 主题排行
 */
public class BizEtfControl {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-03-28";
        boolean isShowPriceArea = true;//是否显示价格区间
//        boolean isShowPriceArea = false;//是否显示价格区间
        boolean isShowUpMa = true;//是否显示-超过均线
//        boolean isShowUpMa = false;//是否显示-超过均线

        List<StockAdrCountVo> rs = checkMaDemo(date, isShowPriceArea, isShowUpMa);
        showStockMa(rs,MA_DAY_5,isShowPriceArea, isShowUpMa);

//        listEtfBizDb(ContentEtf.mapEtfAll.keySet(), 0, true, true);//列表查询-行业etf-排序：涨跌幅
    }


    /**
     * 检查均线
     *
     * @param date
     * @param isShowPriceArea 是否显示价格区间
     * @param isShowUpMa
     */
    private static List<StockAdrCountVo> checkMaDemo(String date, boolean isShowPriceArea, boolean isShowUpMa) {
        List<StockAdrCountVo> rs = new ArrayList<>();
        boolean isUp = true;//检查上涨
//        boolean isUp = false;

        List<Integer> maList = new ArrayList<>();
//        maList.add(MA_30);
        maList.add(MA_60);

        Map<String, String> etfBizMap = ContentEtf.mapEtfAll;//mapEtfBiz mapEtfIndex    mapEtfAll

        for (String zqdm : etfBizMap.keySet()) {
            StockAdrCountVo stockAdrCountVo = new StockAdrCountVo();
            String zqmc = etfBizMap.get(zqdm);
            Map<String, String> etfBizMapSub = new HashMap<>();
            etfBizMapSub.put(zqdm, zqmc);
            stockAdrCountVo.setF12(zqdm);
            stockAdrCountVo.setF14(zqmc);

            //净值
            if (isShowPriceArea) {
                RankStockCommpanyDb stock = new RankStockCommpanyDb();
                stock.setF12(zqdm);
                StringBuffer sbPriceArea = new StringBuffer();
                Map<String, Boolean> maUpdateMap = new HashMap<>();
                StockDemo.setMaMapType(MA_TYPE_DAY, maUpdateMap);
                StockDemo.handlerNetMa(stock, maUpdateMap, date, sbPriceArea, stockAdrCountVo);//处理均线净值
            }

            if (isShowUpMa) {
                //            KlineService.checkMa(etfBizMap, KLT_5, maList, date, isUp,null);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
                String upMa15 = KlineService.checkMa(etfBizMapSub, KLT_15, maList, date, isUp, null, false);
                stockAdrCountVo.setUpMaDay15(upMa15);
                String upMa30 = KlineService.checkMa(etfBizMapSub, KLT_30, maList, date, isUp, null, false);
                stockAdrCountVo.setUpMaDay30(upMa30);
                String upMa60 = KlineService.checkMa(etfBizMapSub, KLT_60, maList, date, isUp, null, false);
                stockAdrCountVo.setUpMaDay60(upMa60);
                String upMa101 = KlineService.checkMa(etfBizMapSub, KLT_101, maList, date, isUp, null, false);
                stockAdrCountVo.setUpMaDay101(upMa101);
                String upMa102 = KlineService.checkMa(etfBizMapSub, KLT_102, maList, date, isUp, null, false);
                stockAdrCountVo.setUpMaDay102(upMa102);
            }
            rs.add(stockAdrCountVo);
        }
        return rs;

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

    /**
     * 显示均线信息
     * @param rs 统计信息
     * @param orderField
     * @param isShowPriceArea 是否显示价格区间
     * @param isShowUpMa 是否显示-超过均线
     */
    private static void showStockMa(List<StockAdrCountVo> rs, String orderField, boolean isShowPriceArea, boolean isShowUpMa) {

        if(rs ==null || rs.size()==0){
            return;
        }
        if(StringUtils.isNotBlank(orderField)){
            if(MA_DAY_5.equals(orderField)){
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getNET_AREA_DAY_5, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            }
            if(MA_DAY_10.equals(orderField)){
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getNET_AREA_DAY_10, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            }
            if(MA_DAY_20.equals(orderField)){
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getNET_AREA_DAY_20, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            }
            if(MA_DAY_40.equals(orderField)){
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getNET_AREA_DAY_40, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            }
            if(MA_DAY_60.equals(orderField)){
                rs = rs.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCountVo::getNET_AREA_DAY_60, Comparator.nullsFirst(BigDecimal::compareTo))).collect(Collectors.toList());
            }
        }
        for (StockAdrCountVo stockAdrCountVo : rs) {
            System.out.print(stockAdrCountVo.getF12());
            System.out.print("\t");
            System.out.print(EtfUtil.handlerEtfName(stockAdrCountVo.getF14()));
            System.out.print("\t");
            if (isShowPriceArea) {
                System.out.print("5日:" + stockAdrCountVo.getNET_AREA_DAY_5() + "\t");//显示信息-价格区间
                System.out.print("10日:" + stockAdrCountVo.getNET_AREA_DAY_10() + "\t");//显示信息-价格区间
                System.out.print("20日:" + stockAdrCountVo.getNET_AREA_DAY_20() + "\t");//显示信息-价格区间
                System.out.print("40日:" + stockAdrCountVo.getNET_AREA_DAY_40() + "\t");//显示信息-价格区间
                System.out.print("60日:" + stockAdrCountVo.getNET_AREA_DAY_60() + "\t");//显示信息-价格区间
//                System.out.print("120日:"+stockAdrCountVo.getNET_AREA_DAY_120() + "\t");//显示信息-价格区间
//                System.out.print("250日:"+stockAdrCountVo.getNET_AREA_DAY_250() + "\t");//显示信息-价格区间
            }
            if (isShowUpMa) {
                System.out.print("超过均线：");//显示信息-价格区间
                String upMa15 = stockAdrCountVo.getUpMaDay15();
                String upMa30 = stockAdrCountVo.getUpMaDay30();
                String upMa60 = stockAdrCountVo.getUpMaDay60();
                String upMa101 = stockAdrCountVo.getUpMaDay101();
                String upMa102 = stockAdrCountVo.getUpMaDay102();

                System.out.print(StringUtils.isNotBlank(upMa15) ? upMa15 : "-------");
                System.out.print(StringUtils.isNotBlank(upMa30) ? upMa30 : "-------");
                System.out.print(StringUtils.isNotBlank(upMa60) ? upMa60 : "-------");
                System.out.print(StringUtils.isNotBlank(upMa101) ? upMa101 : "-------");
                System.out.print(StringUtils.isNotBlank(upMa102) ? upMa102 : "-------");
            }
            System.out.println();
        }
    }

}
