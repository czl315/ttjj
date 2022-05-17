package ttjj.rank;

import org.apache.commons.lang3.StringUtils;
import ttjj.dao.RankStockCommpanyDao;
import ttjj.dao.StockAdrCountDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.db.StockAdrCount;
import ttjj.dto.RankBizDataDiff;
import ttjj.dto.StockAdrCountCond;
import ttjj.dto.DateCond;
import ttjj.dto.StockAdrCountVo;
import ttjj.service.KlineService;
import ttjj.service.StockAdrCountService;
import ttjj.service.StockService;
import ttjj.stat.StBizStatDemo;
import utils.ConceptionUtil;
import utils.DateUtil;
import utils.StockUtil;

import java.math.BigDecimal;
import java.util.*;

import static utils.Content.*;

/**
 * 涨幅次数统计
 *
 * @author Administrator
 * @date 2022-05-11 10:28
 */
public class StockAdrCountDemo {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-05-16";

        statStockAdrCount(-1, date,date);//统计股票涨跌次数:0,0为当天
//        statStockAdrCountBatch(10);//统计股票涨跌次数:0,0为当天

//        //先删除，后插入
//        deleteTodayStAdrCount();
//        insertStockAdrCount(date);

    }

    /**
     *
     *
     * @param date
     */
    private static void insertStockAdrCount(String date) {
        List<BigDecimal> adrMinList = Arrays.asList(new BigDecimal("0"), new BigDecimal("1"), new BigDecimal("3"), new BigDecimal("5"), new BigDecimal("7"), new BigDecimal("9"));
        List<Integer> daysList = Arrays.asList(-90, -60, -30, -14, -7);
        String reportQuete = "";//业绩报表季度  2022Q1
        boolean isShowPriceArea = false;//是否显示价格区间
        long board = DB_RANK_BIZ_F19_BK_MAIN;
        BigDecimal mvLimit = NUM_YI_50;

//        按板块查询
        List<RankBizDataDiff> bizList = StockService.listBiz(NUM_MAX_99);//查询主题排名by时间类型、显示个数
        int limit = NUM_MAX_99;//限定个数
        int stBizCountTemp = 0;
        for (RankBizDataDiff rankBizDataDiff : bizList) {
            if (--limit < 0) {
                break;
            }
//            String biz = "医疗服务";//银行  航空机场    证券
            String biz = rankBizDataDiff.getF14();
            System.out.println("-------------------------当前stBizCountTemp：" + (++stBizCountTemp) + "---" + biz );
            List<RankStockCommpanyDb> stList = StockService.findListByCondition(biz, date, board, mvLimit);//查询股票列表-根据板块：
            List<StockAdrCount> stockAdrCountList = StBizStatDemo.showAdrCount(date, stList, board, mvLimit, adrMinList, daysList, biz, reportQuete, isShowPriceArea);//统计涨跌次数
//            //插入或更新
//            System.out.println("插入成功-涨幅次数统计：" + StockAdrCountService.insertListOrUpdate(stockAdrCountList));

            System.out.println("插入成功-涨幅次数统计：" + StockAdrCountService.insertList(stockAdrCountList));

//            Map<String, String> zqMap = new HashMap<>();
//            for (RankStockCommpanyDb stock : stList) {
//                zqMap.put(stock.getF12(), stock.getF14());
//            }
//            StockStatDemo.checkMaDemo(zqMap, date);
        }


//        int year = DateUtil.getCurYear();//DateUtil.getCurYear() 2021
//        int month = DateUtil.getCurMonth();//DateUtil.getCurMonth()   12
//        int day = 1;//DateUtil.getCurDay()   27
//        statEtfAdrDb(etfBizSet, year, month, day, 18);//统计涨跌次数-按照天的维度

        //检查资金流向-etf
//        checkFundFlowByEtf(date);

//        //        // 统计涨跌次数-根据每月中的日期
//        String zqmc = "159949";//512800 510050:上证50ETF  512000:券商ETF
//        String begDate = "2020-01-01";//开始时间
//        String endDate = date;//DateUtil.getToday(DateUtil.YYYY_MM_DD)
//        statAdrCountByDay(zqmc, begDate, endDate);

    }

    /**
     * 统计股票涨跌次数:批量
     *
     * @param days 天数
     */
    private static void statStockAdrCountBatch(int days,String date) {
        for (int i = 0; i <= days; i++) {
            statStockAdrCount((i + 1), date, date);
        }
    }

    /**
     * 统计股票涨跌次数
     */
    private static void statStockAdrCount(int maDateInt, String spDate, String date) {
        boolean isShowPriceArea = true;//是否显示价格区间
//        boolean isShowPriceArea = false;//是否显示价格区间
//        List<BigDecimal> orderNumList = Arrays.asList(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3"));
        List<BigDecimal> orderNumList = null;
//        List<Boolean> upMaList = Arrays.asList(false,true,true);//判断是否超过均线列表：15,30,60
        List<Boolean> upMaList = null;//判断是否超过均线列表：15,30,60
//        String biz = "医疗服务";//化肥行业 农牧饲渔 航天航空    证券  医疗服务 医疗器械
//        String biz = "生物制品";//医疗： 医疗服务 医疗器械 中药 生物制品
//        String biz = "非金属材料";//科技： 光伏设备  能源金属  风电设备  电池    非金属材料
        String biz = "物流行业";//金融： 银行  工程咨询服务
//        String biz = "酿酒行业";//消费： 酿酒行业
        String orderBy = " ADR_UP_COUNT_60 DESC ";//排序   ADR_UP_COUNT_5 DESC    ADR_UP_COUNT_SUM_60
        String maDate = date;
        if (maDateInt >= 0 ) {
            List<String> dateListBefore = RankStockCommpanyDao.findListDateBefore(new DateCond(date, 20));
            maDate = dateListBefore.get(maDateInt);
        }

        int overCount = 0;//第二天上涨个数
        int downCount = 0;//第二天下跌个数

//        List<BigDecimal> orderNumList = Arrays.asList(new BigDecimal("1"), new BigDecimal("2"));
//        List<BigDecimal> orderNumList = Arrays.asList(new BigDecimal("1"));
        BigDecimal adrUpCountSum60Limit = new BigDecimal("0");
        StockAdrCountCond condition = new StockAdrCountCond();
        condition.setDate(date);
        condition.setOrderNumList(orderNumList);
        condition.setADR_UP_COUNT_SUM_60(adrUpCountSum60Limit);
        condition.setOrderBy(orderBy);
        if (StringUtils.isNotBlank(biz)) {
            condition.setType_name(biz);
        }
        //            map.put("002432", "");//002432	九安医疗	医疗器械
        String concepPinYin = "mapGn";
        if (ConceptionUtil.stConceptionMap.get(biz) != null) {
            concepPinYin = ConceptionUtil.stConceptionMap.get(biz);
        }

        List<StockAdrCount> stockAdrCountList = StockAdrCountService.findListByCondition(condition);
        for (StockAdrCount stockAdrCount : stockAdrCountList) {
            StringBuffer sbStockAdrCount = new StringBuffer();
//            sbStockAdrCount.append(StockUtil.handlerStName(stockAdrCount.getF14())).append("\t");
            String stName = StockUtil.handlerStName(stockAdrCount.getF14());
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
                StockDemo.setMaMapType(MA_TYPE_DAY, maUpdateMap);
                StockDemo.handlerNetMa(stock, maUpdateMap, date, sbPriceArea, new StockAdrCountVo());//处理均线净值
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
                    BigDecimal maDateF3 = KlineService.showDateF3(maDate, stock);
                    System.out.print("[" + maDate + "]：" + maDateF3 + "\t");
//                    BigDecimal spDateF3 = KlineService.showDateF3(spDate, stock);
//                    System.out.print("[" + spDate + "]：" + spDateF3);
                    System.out.println();
//                    if (spDateF3.compareTo(new BigDecimal("0")) > 0) {
//                        overCount++;
//                    } else {
//                        downCount++;
//                    }
                } else {
                    System.out.print(sbStockAdrCount);//显示信息-涨幅次数
                    System.out.print(sbPriceArea.toString());//显示信息-价格区间
                    StringBuffer sbMa = new StringBuffer(sbMa15).append(sbMa30).append(sbMa60).append(sbMa101).append(sbMa102);
                    System.out.print("超均线" + maDate + ":" + sbMa);
                    BigDecimal maDateF3 = KlineService.showDateF3(maDate, stock);
                    BigDecimal spDateF3 = KlineService.showDateF3(spDate, stock);
                    System.out.print("[" + maDate + "]：" + maDateF3 + "\t");
                    System.out.print("[" + spDate + "]：" + spDateF3);
                    System.out.println();
                }
            }
        }
        System.out.println("第二天上涨-下跌比:" + overCount + ":" + downCount);
        System.out.println();

//        StockStatDemo.checkMaDemo(zqMap, date);

    }

    /**
     * 删除数据-今日
     */
    private static void deleteTodayStAdrCount() {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
        int rs = StockAdrCountDao.deleteByDate(date);
        System.out.println("日期：" + date + "，删除结果：" + rs);
    }
}
