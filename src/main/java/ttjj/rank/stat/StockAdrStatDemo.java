package ttjj.rank.stat;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import ttjj.db.RankStockCommpanyDb;
import ttjj.db.StockAdrCount;
import ttjj.dto.*;
import ttjj.rank.StockControl;
import ttjj.service.BizService;
import ttjj.service.KlineService;
import ttjj.service.StockAdrCountService;
import ttjj.service.StockService;
import utils.ConceptionUtil;
import utils.DateUtil;
import utils.StockUtil;

import java.math.BigDecimal;
import java.util.*;

import static utils.ContMapBizBaord.BOARD_NAME_CODE;
import static utils.Content.*;

/**
 * 涨幅次数统计
 *
 * @author Administrator
 * @date 2022-05-11 10:28
 */
public class StockAdrStatDemo {
    public static void main(String[] args) {
        findListDemo();

//        statStockAdrCountBatch(0);//统计股票涨跌次数:0,0为当天

    }

    /**
     * 统计：涨幅次数列表
     *
     */
    public static List<StockAdrCountVo> findListDemo() {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-09-26";
        String spDateBeg = null;//"2022-09-05"
        String spDateEnd = null;//"2022-09-09"
//        String spDateBeg = "2022-09-20";//
//        String spDateEnd = "2022-09-22";//

        int limitCount = 3;

        List<StockAdrCountVo> stockAdrCountListBkAll = new ArrayList<>();
        Map<String, List<String>> bkMap = new HashMap<>();
        bkMap = getBizListSp();//获取业务列表-特定
//        bkMap = BizService.getBizListAll();//获取业务列表-全部板块

        String orderBy = "";//排序   ADR_UP_COUNT_5 DESC    ADR_UP_COUNT_SUM_60    ADR_UP_SUM_1_60
        String orderField = ORDER_FIELD_ADR_UP_SUM_1_60;
        if (orderField.equals(ORDER_FIELD_ADR_UP_SUM_1_60)) {
            orderBy = " ADR_UP_SUM_1_60  DESC ";//排序
        }
        if (orderField.equals(ORDER_FIELD_ADR_UP_SUM_1_40)) {
            orderBy = " ADR_UP_SUM_1_40  DESC ";//排序
        }

        BigDecimal mvMinBig = NUM_YI_500;//NUM_YI_1000  NUM_YI_50  NUM_YI_200
        BigDecimal mvMinSmall = NUM_YI_50;//


        BigDecimal adrSum1To60 = null;
        BigDecimal adrSum1To40 = null;
        BigDecimal adrSum40To60 = null;
        BigDecimal adrSum20To40 = null;

//         adrSum1To60 = new BigDecimal("60");
//         adrSum1To40 = new BigDecimal("30");
//        adrSum40To60 = new BigDecimal("15");
////        BigDecimal adrSum20To40 = new BigDecimal("20");

//        adrSum1To60 = new BigDecimal("90");
//        adrSum1To40 = new BigDecimal("45");
//        adrSum40To60 = new BigDecimal("10");
//        BigDecimal adrSum20To40 = new BigDecimal("20");

        CondStockAdrCount condFind = new CondStockAdrCount();
        condFind.setADR_UP_SUM_1_60(adrSum1To60);
        condFind.setADR_UP_SUM_1_40(adrSum1To40);
        condFind.setADR_UP_SUM_40_60(adrSum40To60);//new BigDecimal("10")
        condFind.setADR_UP_SUM_20_40(adrSum20To40);

//        condFind.setUP_MA_30("30(60)");
//        condFind.setUP_MA_60("60(60)");
//        condFind.setUP_MA_101("101(60)");
//        condFind.setUP_MA_102("102(60)");

        for (List<String> bks : bkMap.values()) {
            List<StockAdrCountVo> stockAdrCountList = listByBizList(date, bks, orderBy, limitCount, mvMinBig, mvMinSmall, condFind);
            stockAdrCountListBkAll.addAll(stockAdrCountList);
        }

        stockAdrCountListBkAll = KlineService.handlerOrder(stockAdrCountListBkAll, orderField, true);//列表-排序：根据字段

        showStockAdrCountList(stockAdrCountListBkAll, spDateBeg, spDateEnd);//显示-涨幅列表

        return stockAdrCountListBkAll;

    }


    /**
     * 获取特定业务列表
     *
     * @return
     */
    private static Map<String, List<String>> getBizListSp() {
        Map<String, List<String>> bkMap = new HashMap<>();
        List<String> bizList = null;//
//        bizList = Arrays.asList("光伏设备","电网设备","电源设备","电池","电力行业","电机","风电设备");//科技:电力
//        bizList = Arrays.asList("钢铁行业","包装材料","有色金属","化肥行业","贵金属","橡胶制品","化学原料","化纤行业","非金属材料","玻璃玻纤","能源金属","煤炭行业","农牧饲渔","采掘行业","造纸印刷","农药兽药","小金属","石油行业","化学制品","塑料制品","燃气");//板块-分类-科技:电力
//        bizList = Arrays.asList("化肥行业","农牧饲渔","农药兽药");//资源-农业:
//        bizList = Arrays.asList("燃气");//资源:大宗商品:("煤炭行业", "采掘行业", "石油行业", "燃气")
//        bizList = Arrays.asList("船舶制造");//资源:交运:("船舶制造")
        bizList = Arrays.asList("医疗服务");//医疗:("医疗服务")
//        bizList = Arrays.asList("家电行业");//消费:("家电行业")
//        bizList = Arrays.asList("多元金融", "银行", "证券", "保险");//金融-机构:("多元金融","银行","证券","保险");
//        bizList = Arrays.asList("证券");
        //金融-房地产、基建:"水泥建材","房地产服务","工程机械","房地产开发","铁路公路","装修建材","装修装饰","工程建设","公用事业","工程咨询服务");//
//        bizList = Arrays.asList("水泥建材","房地产服务","工程机械","房地产开发","铁路公路","装修建材","装修装饰","工程建设","公用事业","工程咨询服务");//

        bkMap.put(bizList.get(0), bizList);//特定板块
        return bkMap;
    }

    /**
     * 查询列表-根据业务等条件
     *
     * @param date       日期
     * @param bizList    业务列表
     * @param orderBy    排序
     * @param limitCount 个数
     * @param condFind
     * @return 查询列表
     */
    public static List<StockAdrCountVo> listByBizList(String date, List<String> bizList, String orderBy, int limitCount, BigDecimal mvMinBig, BigDecimal mvMinSmall, CondStockAdrCount condFind) {
//        CondStockAdrCount condFind = new CondStockAdrCount();
        condFind.setDate(date);
        condFind.setF139(DB_RANK_BIZ_F139_BK_MAIN);
        condFind.setOrderBy(orderBy);
//        condFind.setADR_UP_SUM_1_60(adrSum1To60);
//        condFind.setADR_UP_SUM_1_40(adrSum1To40);
//        condFind.setADR_UP_SUM_40_60(adrSum40To60);
//        condFind.setADR_UP_SUM_20_40(adrSum20To40);
//        condFind.setAdrUpSumOrder1to60Min(new BigDecimal("1"));
//        condFind.setAdrUpSumOrder1to60Max(new BigDecimal("5"));
//        condFind.setUP_MA_60("60(60)");
//        condFind.setUP_MA_101("101(60)");
//        condFind.setUP_MA_102("102(60)");
//        condFind.setADR_UP_COUNT_SUM_60(adrUpCountSum60Limit);
        condFind.setLimitCount(limitCount);
//        condFind.setType_name("半导体");//特定业务：半导体 "半导体" null
//        condFind.setBizList(Arrays.asList("半导体","电子化学品","光学光电子","电子元件"));
//        condFind.setBizList(Arrays.asList("银行"));
//        condFind.setBizList(Arrays.asList("煤炭行业"));
        condFind.setBizList(bizList);
//        condFind.setMvMin(mvMin);
//        condFind.setMvMax(null);
        List<StockAdrCountVo> stockAdrCountList = new ArrayList<>();
        Map<String, StockAdrCountVo> stockAdrCountMap = new HashMap<>();
        //查询大票
        condFind.setMvMin(mvMinBig);
        List<StockAdrCountVo> stockAdrCountList500 = findListByBizList(date, bizList, orderBy, mvMinBig, limitCount, condFind);//大票
        for (StockAdrCountVo stockAdrCount : stockAdrCountList500) {
            stockAdrCountMap.put(stockAdrCount.getF12(), stockAdrCount);
        }
        //查询中票，去重
        condFind.setMvMin(mvMinSmall);
        List<StockAdrCountVo> stockAdrCountList100 = findListByBizList(date, bizList, orderBy, mvMinSmall, limitCount, condFind);//中票
        for (StockAdrCountVo stockAdrCount : stockAdrCountList100) {
            String zqdm = stockAdrCount.getF12();
            String zqmc = stockAdrCount.getF14();
            if (stockAdrCountMap.containsKey(zqdm)) {
                System.out.println("检查中票：大票也存在：" + zqmc);
            } else {
                stockAdrCountMap.put(zqdm, stockAdrCount);
            }
        }
        for (StockAdrCountVo stockAdrCount : stockAdrCountMap.values()) {
            stockAdrCountList.add(stockAdrCount);
        }

        return stockAdrCountList;
    }

    /**
     * 查询
     *
     * @param date       日期
     * @param bizList    板块列表
     * @param orderBy
     * @param mvMin
     * @param limitCount
     */
    private static List<StockAdrCountVo> findListByBizList(String date, List<String> bizList, String orderBy, BigDecimal mvMin, int limitCount, CondStockAdrCount condFind) {
//        List<StockAdrCount> stockAdrCountList = findListByBiz(date, bizList,spBizName,startMapNum, null, mvMin, 2,orderBy);
        return findListByCondition(condFind);
    }

    private static List<StockAdrCountVo> findListByBiz(String date, List<RankBizDataDiff> bizList, String spBizName, int startMapNum, Object o, BigDecimal mvMin, int limitCount, String orderBy) {
        List<StockAdrCountVo> rs = new ArrayList<>();
        BigDecimal adrUpCountSum60Limit = null;//涨幅次数限定，过滤杂毛

        //插入且更新价格区间、更新
        int stBizCountTemp = 0;
        for (RankBizDataDiff rankBizDataDiff : bizList) {
            String bizCode = rankBizDataDiff.getF12();
            String bizName = rankBizDataDiff.getF14();
            stBizCountTemp++;
            if (stBizCountTemp < startMapNum) {
                System.out.println("已完成," + (stBizCountTemp) + ":" + bizName);
                continue;//已完成
            }

            //特定业务处理
            if (spBizName != null && !bizName.equals(spBizName)) {
                continue;
            }

            CondStockAdrCount condition = new CondStockAdrCount();
            condition.setDate(date);
//        condition.setOrderNumList(orderNumList);
            condition.setADR_UP_COUNT_SUM_60(adrUpCountSum60Limit);
            condition.setOrderBy(orderBy);
            condition.setLimitCount(limitCount);
            if (StringUtils.isNotBlank(bizName)) {
                condition.setType_name(bizName);
            }
            rs.addAll(StockAdrCountService.findListByCondition(condition));
        }

        return rs;
    }

    /**
     * 显示-涨幅列表
     *
     * @param stockAdrCountList
     */
    public static void showStockAdrCountList(List<StockAdrCountVo> stockAdrCountList, String spDateBeg, String spDateEnd) {

        if (stockAdrCountList == null) {
            System.out.println("stockAdrCountList==null");
            return;
        }

        boolean isShowCode = true;
//        boolean isShowCode = false;
        StringBuffer sbHead = new StringBuffer();
        if (isShowCode) {
            sbHead.append(StockUtil.formatStName("编码", 10));
        }
//        sbHead.append("[");
        sbHead.append(StockUtil.formatEtfName("名称", 10));
//        sbHead.append("]");
        sbHead.append(StockUtil.formatStName("行业", 14));
        sbHead.append(StockUtil.formatStName("全3月排", 8));
        sbHead.append(StockUtil.formatStName("全3排和", 8));
        sbHead.append(StockUtil.formatStName("3月排", 6));
        sbHead.append(StockUtil.formatStName("2月排", 6));
        sbHead.append(StockUtil.formatStName("1月排", 6));
        sbHead.append(StockUtil.formatStName("10天排", 6));
        sbHead.append(StockUtil.formatStName("5天排", 6));
        sbHead.append(StockUtil.formatStName("3月涨和", 8));
        sbHead.append(StockUtil.formatStName("2月涨和", 8));
        sbHead.append(StockUtil.formatStName("3月涨", 8));
        sbHead.append(StockUtil.formatStName("2月涨", 8));
        sbHead.append(StockUtil.formatStName("1月涨", 8));
        sbHead.append(StockUtil.formatStName("10天涨", 6));
        sbHead.append(StockUtil.formatStName("5天涨", 6));
        sbHead.append(StockUtil.formatStName("3天涨", 10));
        sbHead.append(StockUtil.formatStName("2天涨", 6));
        sbHead.append(StockUtil.formatStName("1天涨", 6));
        sbHead.append(StockUtil.formatStName("今涨", 8));
        if (StringUtils.isNotBlank(spDateBeg)) {
//            sbHead.append(StockUtil.formatStName(spDateBeg.substring(5), 5));
            sbHead.append(StockUtil.formatStName("区间涨幅", 5));
        }
//        if (StringUtils.isNotBlank(spDateEnd)) {
//            sbHead.append("至");
//            sbHead.append(StockUtil.formatStName(spDateEnd.substring(5), 7));
//        }
        sbHead.append(StockUtil.formatStName("市值", 8));
        sbHead.append(StockUtil.formatStName("区间5", 6));
        sbHead.append(StockUtil.formatStName("区间10", 6));
        sbHead.append(StockUtil.formatStName("区间20", 6));
        sbHead.append(StockUtil.formatStName("区间40", 6));
        sbHead.append(StockUtil.formatStName("区间60", 6));
        sbHead.append(StockUtil.formatStName("超周", 6));
        sbHead.append(StockUtil.formatStName("超日", 6));
        sbHead.append(StockUtil.formatStName("超60", 6));
        sbHead.append(StockUtil.formatStName("超30", 6));
        sbHead.append(StockUtil.formatStName("超15", 6));
        System.out.println(sbHead);

        //处理-近2月涨幅和
//        for (StockAdrCount stockAdrCount : stockAdrCountList) {
//            if (stockAdrCount.getADR_UP_SUM_1_40() != null) {
//                break;//如果非空，不需要处理
//            }
//            BigDecimal adrUpSum1to20 = stockAdrCount.getADR_UP_SUM_1_20() != null ? stockAdrCount.getADR_UP_SUM_1_20() : new BigDecimal("0");
//            BigDecimal adrUpSum20to40 = stockAdrCount.getADR_UP_SUM_20_40() != null ? stockAdrCount.getADR_UP_SUM_20_40() : new BigDecimal("0");
//            BigDecimal adrUpSum1to40 = adrUpSum20to40.add(adrUpSum1to20);
//            stockAdrCount.setADR_UP_SUM_1_40(adrUpSum1to40);
//        }

//        //排序-近2月涨幅和
//        stockAdrCountList = stockAdrCountList.stream().filter(e -> e != null).sorted(Comparator.comparing(StockAdrCount::getADR_UP_SUM_1_40, Comparator.nullsFirst(BigDecimal::compareTo)).reversed()).collect(Collectors.toList());

        for (StockAdrCountVo stockAdrCount : stockAdrCountList) {
//            System.out.println(JSON.toJSONString(stockAdrCount));
            StringBuffer sb = new StringBuffer();
            String code = stockAdrCount.getF12();

            String bizName = StockUtil.formatBizName(stockAdrCount.getType_name());
            BigDecimal order1to20 = stockAdrCount.getADR_UP_SUM_ORDER_1_20() != null ? stockAdrCount.getADR_UP_SUM_ORDER_1_20() : new BigDecimal("0");
            BigDecimal order20to40 = stockAdrCount.getADR_UP_SUM_ORDER_20_40() != null ? stockAdrCount.getADR_UP_SUM_ORDER_20_40() : new BigDecimal("0");
            BigDecimal order40to60 = stockAdrCount.getADR_UP_SUM_ORDER_40_60() != null ? stockAdrCount.getADR_UP_SUM_ORDER_40_60() : new BigDecimal("0");

            BigDecimal marketValue = stockAdrCount.getF20().divide(new BigDecimal("100000000"), 2, BigDecimal.ROUND_HALF_UP);
            BigDecimal order_1_60 = order1to20.add(order20to40).add(order40to60);
            if (isShowCode) {
                sb.append(StockUtil.formatStName(code, 10));
            }
//            sb.append("[");
            sb.append(StockUtil.formatStName(stockAdrCount.getF14(), 10));
//            sb.append("]");
            sb.append(StockUtil.formatStName(bizName, 14));
            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_ORDER_1_60(), 8));
            sb.append(StockUtil.formatDouble(order_1_60, 8));
            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_ORDER_40_60(), 6));
            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_ORDER_20_40(), 6));
            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_ORDER_1_20(), 6));
            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_ORDER_1_10(), 6));
            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_ORDER_1_5(), 6));
            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_1_60(), 8));
            //处理-近2月涨幅和
            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_1_40(), 8));
//            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_20_40().add(stockAdrCount.getADR_UP_SUM_1_20()),8));

            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_40_60(), 8));
            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_20_40(), 8));
            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_1_20(), 8));
            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_1_10(), 6));
            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_1_5(), 6));
            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_1_3(), 10));
            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_1_2(), 6));
            sb.append(StockUtil.formatDouble(stockAdrCount.getADR_UP_SUM_1_1(), 6));
            sb.append(StockUtil.formatDouble(stockAdrCount.getF3(), 8));
            //特定日期区间涨幅
            if (StringUtils.isNotBlank(spDateBeg)) {
//                RankStockCommpanyDb stock = new RankStockCommpanyDb();
//                stock.setF12(stockAdrCount.getF12());
//                Kline kline = KlineService.findLast(stock, spDateBeg, KLT_101);
//                if (kline != null) {
//                    sb.append(StockUtil.formatDouble(kline.getZhangDieFu(), 8));
//                }

                BigDecimal areaAdr = KlineService.findAreaAdr(code, spDateBeg, spDateEnd, KLT_101);
                sb.append(StockUtil.formatDouble(areaAdr, 8));
            }
            sb.append(StockUtil.formatDouble(marketValue, 8));
            sb.append(StockUtil.formatDouble(stockAdrCount.getNET_AREA_DAY_5(), 6));
            sb.append(StockUtil.formatDouble(stockAdrCount.getNET_AREA_DAY_10(), 6));
            sb.append(StockUtil.formatDouble(stockAdrCount.getNET_AREA_DAY_20(), 6));
            sb.append(StockUtil.formatDouble(stockAdrCount.getNET_AREA_DAY_40(), 6));
            sb.append(StockUtil.formatDouble(stockAdrCount.getNET_AREA_DAY_60(), 6));
            if (stockAdrCount.getUP_MA_102() != null) {
                sb.append(StockUtil.formatStName(stockAdrCount.getUP_MA_102().replace("(60)", ""), 6));
            } else {
                sb.append(StockUtil.formatStName("", 6));
            }
            if (stockAdrCount.getUP_MA_101() != null) {
                sb.append(StockUtil.formatStName(stockAdrCount.getUP_MA_101().replace("(60)", ""), 6));
            } else {
                sb.append(StockUtil.formatStName("", 6));
            }
            if (stockAdrCount.getUP_MA_60() != null) {
                sb.append(StockUtil.formatStName(stockAdrCount.getUP_MA_60().replace("(60)", ""), 6));
            } else {
                sb.append(StockUtil.formatStName("", 6));
            }
            if (stockAdrCount.getUP_MA_30() != null) {
                sb.append(StockUtil.formatStName(stockAdrCount.getUP_MA_30().replace("(60)", ""), 6));
            } else {
                sb.append(StockUtil.formatStName("", 6));
            }
            if (stockAdrCount.getUP_MA_15() != null) {
                sb.append(StockUtil.formatStName(stockAdrCount.getUP_MA_15().replace("(60)", ""), 6));
            } else {
                sb.append(StockUtil.formatStName("", 6));
            }
            System.out.println(sb);
        }
    }

    /**
     * 更新涨跌次数-根据业务
     *
     * @param date
     * @param board
     * @param mvMin
     * @param mvMax
     * @param bizName
     * @return
     */
    private static int updateAdrSumByBiz(String date, Long board, BigDecimal mvMin, BigDecimal mvMax, String bizName, String dbField) {
        int rs = 0;
        List<StockAdrCount> stockAdrCountList = new ArrayList<>();
        //查询股票列表-根据板块
        CondStock condition = new CondStock();
        condition.setDate(date);
        condition.setF139(board);
        condition.setMvMin(mvMin);
        condition.setMvMax(mvMax);
        condition.setType_name(bizName);
        List<RankStockCommpanyDb> stList = StockService.findListByCondition(condition);

        String endDate = handlerEndDateByDbField(date, dbField);
        String begDate = handlerBegDateByDbField(date, dbField);

        //查询每只股票的涨幅次数
        for (RankStockCommpanyDb stock : stList) {
            //检查股票:状态
            if (!StockService.checkStockStatus(stock)) {
                continue;
            }

            BigDecimal adrSum = null;//涨幅合计
            CondStock conditionStock = new CondStock();//查询条件
            conditionStock.setF12(stock.getF12());
            conditionStock.setBegDate(begDate);
            conditionStock.setEndDate(endDate);
            List<RankStockCommpanyDb> stockDateList = StockService.findListByCondition(conditionStock);
            for (RankStockCommpanyDb stockOneDate : stockDateList) {
                BigDecimal adr = stockOneDate.getF3();
                //只计算正增长的
                if (adr != null && adr.compareTo(new BigDecimal("0")) > 0) {
                    if (adrSum == null) {
                        adrSum = new BigDecimal("0");
                    }
                    adrSum = adrSum.add(adr);
                }
            }
            StockAdrCount entity = new StockAdrCount();
            entity.setF12(stock.getF12());
            entity.setF14(stock.getF14());
            entity.setDate(date);
            if (adrSum == null) {
//                System.out.println("今日未涨：" + JSON.toJSONString(entity));
                continue;
            }
            if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_1.equals(dbField)) {
                entity.setADR_UP_SUM_1_1(adrSum);
            }
            if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_2.equals(dbField)) {
                entity.setADR_UP_SUM_1_2(adrSum);
            }
            if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_3.equals(dbField)) {
                entity.setADR_UP_SUM_1_3(adrSum);
            }
            if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_5.equals(dbField)) {
                entity.setADR_UP_SUM_1_5(adrSum);
            }
            if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_10.equals(dbField)) {
                entity.setADR_UP_SUM_1_10(adrSum);
            }
            if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_20.equals(dbField)) {
                entity.setADR_UP_SUM_1_20(adrSum);
            }
            if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_40.equals(dbField)) {
                entity.setADR_UP_SUM_1_40(adrSum);
            }
            if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_20_40.equals(dbField)) {
                entity.setADR_UP_SUM_20_40(adrSum);
            }
            if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_40_60.equals(dbField)) {
                entity.setADR_UP_SUM_40_60(adrSum);
            }
            stockAdrCountList.add(entity);
        }
        //排序
        //更新涨幅次数
        for (StockAdrCount stockAdrCount : stockAdrCountList) {
            int updateRs = StockAdrCountService.update(stockAdrCount);
            if (updateRs != 1) {
                System.out.println(bizName + ",更新-上涨之和-失败：" + rs + "" + JSON.toJSONString(stockAdrCount));
            } else {
                rs++;
            }
        }
        System.out.println(bizName + ",更新-上涨之和-成功：" + rs);
        return rs;
    }

    /**
     * 计算开始日期-根据字段
     *
     * @param date    基础日期
     * @param dbField 字段
     * @return 结束日期
     */
    private static String handlerBegDateByDbField(String date, String dbField) {
        String rs = null;
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_1.equals(dbField)) {
            return StockService.findBegDate(date, 1);
        }
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_2.equals(dbField)) {
            return StockService.findBegDate(date, 2);
        }
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_3.equals(dbField)) {
            return StockService.findBegDate(date, 3);
        }
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_5.equals(dbField)) {
            return StockService.findBegDate(date, 5);
        }
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_10.equals(dbField)) {
            return StockService.findBegDate(date, 10);
        }
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_20.equals(dbField)) {
            return StockService.findBegDate(date, 20);
        }
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_40.equals(dbField)) {
            return StockService.findBegDate(date, 40);
        }
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_20_40.equals(dbField)) {
            return StockService.findBegDate(date, 40);
        }
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_40_60.equals(dbField)) {
            return StockService.findBegDate(date, 60);
        }
        return rs;
    }

    /**
     * 计算结束日期-根据字段
     *
     * @param date    基础日期
     * @param dbField 字段
     * @return 结束日期
     */
    private static String handlerEndDateByDbField(String date, String dbField) {
        String rs = null;
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_1.equals(dbField)) {
            return StockService.findBegDate(date, 1);
        }
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_2.equals(dbField)) {
            return StockService.findBegDate(date, 1);
        }
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_3.equals(dbField)) {
            return StockService.findBegDate(date, 1);
        }
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_5.equals(dbField)) {
            return StockService.findBegDate(date, 1);
        }
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_10.equals(dbField)) {
            return StockService.findBegDate(date, 1);
        }
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_20.equals(dbField)) {
            return StockService.findBegDate(date, 1);
        }
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_40.equals(dbField)) {
            return StockService.findBegDate(date, 1);
        }
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_20_40.equals(dbField)) {
            return StockService.findBegDate(date, 20);
        }
        if (DB_STOCK_ADR_COUNT_ADR_UP_SUM_40_60.equals(dbField)) {
            return StockService.findBegDate(date, 40);
        }

        return rs;
    }

    /**
     * 检查-当前业务是否完成
     *
     * @param curBizNum 当前业务号
     * @param begBiz    特定业务开始号
     * @return 是否检查通过
     */
    private static boolean checkBizBegNum(int curBizNum, int begBiz) {
        if (curBizNum < begBiz) {
            return false;
        }
        return true;
    }

    /**
     * 检查：涨幅次数限定
     *
     * @param adrUpCountSum60Limit 涨幅次数限定
     * @param stockAdrCount        涨幅次数
     * @return
     */
    private static boolean checkAdrUpCount(BigDecimal adrUpCountSum60Limit, StockAdrCount stockAdrCount) {
        if (adrUpCountSum60Limit == null) {
            return true;
        }
        BigDecimal adrUpCountSum60 = stockAdrCount.getADR_UP_COUNT_SUM_60();
        if (adrUpCountSum60.compareTo(adrUpCountSum60Limit) < 0) {
//            System.out.println(stockAdrCount.getF14() + ":涨幅次数[" + adrUpCountSum60 + "]低于限定[" + adrUpCountSum60Limit + "]，不处理");
            return false;
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append(stockAdrCount.getF14() + ":涨幅次数[" + adrUpCountSum60 + "]高于限定[" + adrUpCountSum60Limit + "]").append("\t");
            sb.append(stockAdrCount.getADR_UP_COUNT_SUM_60()).append("\t");
            sb.append(stockAdrCount.getType_name()).append(" ");
            sb.append(stockAdrCount.getF3()).append("\t");
            System.out.println(sb);
            return true;
        }
    }

    /**
     * 查询列表-根据条件
     *
     * @param condFind 条件
     * @return 结果
     */
    private static List<StockAdrCountVo> findListByCondition(CondStockAdrCount condFind) {
        return StockAdrCountService.findListByCondition(condFind);
    }

    /**
     * 统计股票涨跌次数
     */
    private static void statStockAdrCount(String spDate, String maDate, List<StockAdrCount> stockAdrCountList, String biz) {
        boolean isShowPriceArea = true;//是否显示价格区间
//        boolean isShowPriceArea = false;//是否显示价格区间

        int overCount = 0;//第二天上涨个数
        int downCount = 0;//第二天下跌个数

//        List<StockAdrCount> stockAdrCountList = findListByCondition(date, bizName, adrUpCountSum60Limit, mvLimit, 2);

        String concepPinYin = "mapGn";
        if (ConceptionUtil.stConceptionMap.get(biz) != null) {
            concepPinYin = ConceptionUtil.stConceptionMap.get(biz);
        }
        StringBuffer bizInfoBeg = new StringBuffer();
        bizInfoBeg.append("/**" + biz + "**/");
        bizInfoBeg.append("\n");
        bizInfoBeg.append("public static Map<String, String> " + concepPinYin + " = new HashMap<>();");
        bizInfoBeg.append("\n");
        bizInfoBeg.append("static {");
        System.out.println(bizInfoBeg);

        for (StockAdrCount stockAdrCount : stockAdrCountList) {
            StringBuffer sbStockAdrCount = new StringBuffer();
//            sbStockAdrCount.append(StockUtil.handlerStName(stockAdrCount.getF14())).append("\t");
            String stName = StockUtil.handlerStName(stockAdrCount.getF14());
            //            map.put("002432", "");//002432	九安医疗	医疗器械

            sbStockAdrCount.append("\t");
            sbStockAdrCount.append((concepPinYin + ".put(\"" + stockAdrCount.getF12() + "\", \"" + stName + "\");//"));//map  map.put("002432", "");//002432	九安医疗	医疗器械
            sbStockAdrCount.append(StockUtil.formatBizName(stockAdrCount.getType_name())).append("\t");
            sbStockAdrCount.append(stockAdrCount.getADR_UP_COUNT_SUM_60()).append("\t\t");
            sbStockAdrCount.append(stockAdrCount.getADR_UP_COUNT_60()).append("\t\t");
            sbStockAdrCount.append(stockAdrCount.getADR_UP_COUNT_40()).append("\t\t");
            sbStockAdrCount.append(stockAdrCount.getADR_UP_COUNT_20()).append("\t\t");
            sbStockAdrCount.append(stockAdrCount.getADR_UP_COUNT_10()).append("\t\t");
            sbStockAdrCount.append(stockAdrCount.getADR_UP_COUNT_5()).append("\t\t");
            sbStockAdrCount.append(StockUtil.formatDouble(stockAdrCount.getF3())).append("\t");
            sbStockAdrCount.append(stockAdrCount.getOrder_num()).append("\t");

            StringBuffer sbPriceArea = new StringBuffer();
            String zqdm = stockAdrCount.getF12();
            RankStockCommpanyDb stock = new RankStockCommpanyDb();
            stock.setF12(zqdm);
            if (isShowPriceArea) {
                Map<String, Boolean> maUpdateMap = new HashMap<>();
                StockControl.setMaMapType(MA_TYPE_DAY, maUpdateMap);
                StockControl.handlerNetMa(stock, maUpdateMap, maDate, sbPriceArea, new StockAdrCountVo());//处理均线净值
            }

            Map<String, String> zqMap = new HashMap<>();
            zqMap.put(stockAdrCount.getF12(), stockAdrCount.getF14());
//            zqMap.put("002594", "比亚迪");
            boolean isUp = true;//检查上涨
            List<Integer> maList = new ArrayList<>();
//        maList.add(MA_30);
            maList.add(MA_60);
//            String maDate = date;

            boolean isMa15 = KlineService.showUpMa(stock, KLT_15, maList, maDate, isUp);//显示信息-上涨均线
//            String upMa15 = KlineService.checkMa(stock, KLT_15, maList, date, isUp, null, false);
            boolean isMa30 = KlineService.showUpMa(stock, KLT_30, maList, maDate, isUp);//显示信息-上涨均线
            boolean isMa60 = KlineService.showUpMa(stock, KLT_60, maList, maDate, isUp);//显示信息-上涨均线
            boolean isMa101 = KlineService.showUpMa(stock, KLT_101, maList, maDate, isUp);//显示信息-上涨均线
            boolean isMa102 = KlineService.showUpMa(stock, KLT_102, maList, maDate, isUp);//显示信息-上涨均线
            boolean isHasMa = isMa15 || isMa30 || isMa60 || isMa101 || isMa102;

//            System.out.print(sbStockAdrCount);//显示信息-涨幅次数
//            System.out.print("价格区间:" + sbPriceArea.toString());//显示信息-价格区间
//            System.out.print(sbMa);
//            System.out.print(KlineService.showDateF3(date, stock));
//            String spDate = "2022-05-11";
//            System.out.print(KlineService.showDateF3(spDate, stock));
//            System.out.println();
            isHasMa = true;
            if (isHasMa) {//只显示超过均线的
                StringBuffer sbMa15 = new StringBuffer("    ");
                StringBuffer sbMa30 = new StringBuffer("    ");
                StringBuffer sbMa60 = new StringBuffer("    ");
                StringBuffer sbMa101 = new StringBuffer("    ");
                StringBuffer sbMa102 = new StringBuffer("    ");
                if (isMa15) {
                    sbMa15 = new StringBuffer("15  ");
                }
                if (isMa30) {
                    sbMa30 = new StringBuffer("30  ");
                }
                if (isMa60) {
                    sbMa60 = new StringBuffer("60  ");
                }
                if (isMa101) {
                    sbMa101 = new StringBuffer("101 ");
                }
                if (isMa102) {
                    sbMa102 = new StringBuffer("102 ");
                }

                //判断是否超过均线列表：15,30,60
                boolean isUpMa = true;
//                boolean isUpMa = false;
//                if (upMaList != null && upMaList.size() > 0) {
//                    isUpMa = isMa30 && isMa60;
//                }
//                if(isMa60){
                if (isUpMa) {
                    System.out.print(sbStockAdrCount);//显示信息-涨幅次数
                    System.out.print(sbPriceArea.toString());//显示信息-价格区间
                    StringBuffer sbMa = new StringBuffer(sbMa15).append(sbMa30).append(sbMa60).append(sbMa101).append(sbMa102);
                    System.out.print("超均线" + maDate + ":" + sbMa);
                    if (StringUtils.isNotBlank(spDate)) {
                        BigDecimal maDateF3 = KlineService.showDateF3(maDate, stock);
                        System.out.print("[" + maDate + "]：" + maDateF3 + "\t");
                        BigDecimal spDateF3 = KlineService.showDateF3(spDate, stock);
                        System.out.print("[" + spDate + "]：" + spDateF3);
                        System.out.println();
                        if (spDateF3.compareTo(new BigDecimal("0")) > 0) {
                            overCount++;
                        } else {
                            downCount++;
                        }
                    }
                } else {
                    System.out.print(sbStockAdrCount);//显示信息-涨幅次数
                    System.out.print(sbPriceArea.toString());//显示信息-价格区间
                    StringBuffer sbMa = new StringBuffer(sbMa15).append(sbMa30).append(sbMa60).append(sbMa101).append(sbMa102);
                    System.out.print("超均线" + maDate + ":" + sbMa);
                    BigDecimal maDateF3 = KlineService.showDateF3(maDate, stock);
                    BigDecimal spDateF3 = KlineService.showDateF3(spDate, stock);
                    System.out.print("[" + maDate + "]：" + maDateF3 + "\t");
                    System.out.print("[" + spDate + "]：" + spDateF3);
                }
                System.out.println();
            }
        }
//        System.out.println("第二天上涨-下跌比:" + overCount + ":" + downCount);

        StringBuffer bizInfoEnd = new StringBuffer();
        bizInfoEnd.append("}");
        System.out.println(bizInfoEnd);

//        StockStatDemo.checkMaDemo(zqMap, date);

    }

}
