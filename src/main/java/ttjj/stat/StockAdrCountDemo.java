package ttjj.stat;

import org.apache.commons.lang3.StringUtils;
import ttjj.dao.RankStockCommpanyDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.db.StockAdrCount;
import ttjj.db.StockAdrCountVo;
import ttjj.dto.DateCond;
import ttjj.rank.StockDemo;
import ttjj.service.KlineService;
import ttjj.service.StockAdrCountService;
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
        statStockAdrCount(-1, -1);//统计股票涨跌次数:0,0为当天
//        statStockAdrCountBatch(10);//统计股票涨跌次数:0,0为当天

    }

    /**
     * 统计股票涨跌次数:批量
     *
     * @param days 天数
     */
    private static void statStockAdrCountBatch(int days) {
        for (int i = 0; i <= days; i++) {
            statStockAdrCount((i + 1), i);
        }
    }

    /**
     * 统计股票涨跌次数
     */
    private static void statStockAdrCount(int maDateInt, int spDateInt) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-05-10";
        boolean isShowPriceArea = true;//是否显示价格区间
//        boolean isShowPriceArea = false;//是否显示价格区间
//        List<BigDecimal> orderNumList = Arrays.asList(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3"));
        List<BigDecimal> orderNumList = null;
//        List<Boolean> upMaList = Arrays.asList(false,true,true);//判断是否超过均线列表：15,30,60
        List<Boolean> upMaList = null;//判断是否超过均线列表：15,30,60
        String biz = "银行";
        String orderBy = " ADR_UP_COUNT_5 DESC ";//排序
        String maDate = date;
        String spDate = date;
        if (maDateInt >= 0 && spDateInt >= 0) {
            List<String> dateListBefore = RankStockCommpanyDao.findListDateBefore(new DateCond(date, 20));
            maDate = dateListBefore.get(maDateInt);
            spDate = dateListBefore.get(spDateInt);
        }

        int overCount = 0;//第二天上涨个数
        int downCount = 0;//第二天下跌个数

//        List<BigDecimal> orderNumList = Arrays.asList(new BigDecimal("1"), new BigDecimal("2"));
//        List<BigDecimal> orderNumList = Arrays.asList(new BigDecimal("1"));
        BigDecimal adrUpCountSum60Limit = new BigDecimal("0");
        StockAdrCountVo condition = new StockAdrCountVo();
        condition.setDate(date);
        condition.setOrderNumList(orderNumList);
        condition.setADR_UP_COUNT_SUM_60(adrUpCountSum60Limit);
        condition.setOrderBy(orderBy);
        if (StringUtils.isNotBlank(biz)) {
            condition.setType_name(biz);
        }
        List<StockAdrCount> stockAdrCountList = StockAdrCountService.findListByCondition(condition);
        for (StockAdrCount stockAdrCount : stockAdrCountList) {
            StringBuffer sbStockAdrCount = new StringBuffer();
            sbStockAdrCount.append(StockUtil.handlerStName(stockAdrCount.getF14())).append("\t");
            sbStockAdrCount.append(StockUtil.formatBizName(stockAdrCount.getType_name())).append("\t");
            sbStockAdrCount.append(stockAdrCount.getADR_UP_COUNT_SUM_60()).append("\t\t");
            sbStockAdrCount.append(stockAdrCount.getADR_UP_COUNT_60()).append("\t\t");
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
                StockDemo.handlerNetMa(stock, maUpdateMap, date, sbPriceArea);//处理均线净值
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
            if (isHasMa) {//只显示超过均线的
                StringBuffer sbMa15 = new StringBuffer("----");
                StringBuffer sbMa30 = new StringBuffer("----");
                StringBuffer sbMa60 = new StringBuffer("----");
                StringBuffer sbMa101 = new StringBuffer("----");
                StringBuffer sbMa102 = new StringBuffer("----");
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
                boolean isUpMa = false;
                if (upMaList != null && upMaList.size() > 0) {
                    isUpMa = isMa30 && isMa60;
                }
//                if(isMa60){
                if (isUpMa) {
                    System.out.print(sbStockAdrCount);//显示信息-涨幅次数
                    System.out.print(sbPriceArea.toString());//显示信息-价格区间
                    StringBuffer sbMa = new StringBuffer(sbMa15).append(sbMa30).append(sbMa60).append(sbMa101).append(sbMa102);
                    System.out.print("超均线" + maDate + ":" + sbMa);
                    BigDecimal maDateF3 = KlineService.showDateF3(maDate, stock);
                    BigDecimal spDateF3 = KlineService.showDateF3(spDate, stock);
                    System.out.print("[" + maDate + "]：" + maDateF3 + "\t");
                    System.out.print("[" + spDate + "]：" + spDateF3);
                    System.out.println();
                    if (spDateF3.compareTo(new BigDecimal("0")) > 0) {
                        overCount++;
                    } else {
                        downCount++;
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
                    System.out.println();
                }
            }
        }
        System.out.println("第二天上涨-下跌比:" + overCount + ":" + downCount);
        System.out.println();

//        StockStatDemo.checkMaDemo(zqMap, date);

    }


}
