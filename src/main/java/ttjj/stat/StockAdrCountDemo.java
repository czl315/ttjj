package ttjj.stat;

import ttjj.db.RankStockCommpanyDb;
import ttjj.db.StockAdrCount;
import ttjj.db.StockAdrCountVo;
import ttjj.rank.StockDemo;
import ttjj.service.KlineService;
import ttjj.service.StockAdrCountService;
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
//        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
        String date = "2022-05-10";
        boolean isShowPriceArea = true;//是否显示价格区间
        String biz = "银行";

        List<BigDecimal> orderNumList = Arrays.asList(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3"));
//        List<BigDecimal> orderNumList = Arrays.asList(new BigDecimal("4"));
        BigDecimal adrUpCountSum60Limit = new BigDecimal("0");
        StockAdrCountVo condition = new StockAdrCountVo();
        condition.setDate(date);
        condition.setOrderNumList(orderNumList);
        condition.setADR_UP_COUNT_SUM_60(adrUpCountSum60Limit);
//        condition.setType_name(biz);
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
            boolean isUp = true;//检查上涨
            List<Integer> maList = new ArrayList<>();
//        maList.add(MA_30);
            maList.add(MA_60);

//            KlineService.checkMa(zqMap, KLT_15, maList, date, isUp, null);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
//            KlineService.checkMa(zqMap, KLT_30, maList, date, isUp, null);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
//            KlineService.checkMa(zqMap, KLT_60, maList, date, isUp, null);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
//            KlineService.checkMa(zqMap, KLT_101, maList, date, isUp, null);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
//            KlineService.checkMa(zqMap, KLT_102, maList, date, isUp, null);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101

//            String maDate = date;
            String maDate = "2022-05-09";
            StringBuffer sbMa15 = KlineService.showUpMa(stock, KLT_15, maList, maDate, isUp);//显示信息-上涨均线
            StringBuffer sbMa30 = KlineService.showUpMa(stock, KLT_30, maList, maDate, isUp);//显示信息-上涨均线
            StringBuffer sbMa60 = KlineService.showUpMa(stock, KLT_60, maList, maDate, isUp);//显示信息-上涨均线
            StringBuffer sbMa101 = KlineService.showUpMa(stock, KLT_101, maList, maDate, isUp);//显示信息-上涨均线
            StringBuffer sbMa102 = KlineService.showUpMa(stock, KLT_102, maList, maDate, isUp);//显示信息-上涨均线
            StringBuffer sbMa = new StringBuffer(sbMa15).append(sbMa30).append(sbMa60).append(sbMa101).append(sbMa102);

//            System.out.print(sbStockAdrCount);//显示信息-涨幅次数
//            System.out.print("价格区间:" + sbPriceArea.toString());//显示信息-价格区间
//            System.out.print(sbMa);
//            System.out.print(KlineService.showDateF3(date, stock));
//            String spDate = "2022-05-11";
//            System.out.print(KlineService.showDateF3(spDate, stock));
//            System.out.println();
            if (sbMa.length() > 0) {//只显示超过均线的
                System.out.print(sbStockAdrCount);//显示信息-涨幅次数
                System.out.print(sbPriceArea.toString());//显示信息-价格区间
                if(sbMa15.length() == 0){
                    sbMa15 = new StringBuffer("---");
                }
                if(sbMa30.length() == 0){
                    sbMa30 = new StringBuffer("---");
                }
                if(sbMa60.length() == 0){
                    sbMa60 = new StringBuffer("---");
                }
                if(sbMa101.length() == 0){
                    sbMa101 = new StringBuffer("---");
                }
                if(sbMa102.length() == 0){
                    sbMa102 = new StringBuffer("---");
                }
                sbMa = new StringBuffer(sbMa15).append(sbMa30).append(sbMa60).append(sbMa101).append(sbMa102);
                System.out.print("超均线"+maDate+":" +sbMa);
                System.out.print(KlineService.showDateF3(maDate, stock));
                String spDate = "2022-05-10";
                System.out.print(KlineService.showDateF3(spDate, stock));
                System.out.println();
            }


        }

//        StockStatDemo.checkMaDemo(zqMap, date);

    }


}
