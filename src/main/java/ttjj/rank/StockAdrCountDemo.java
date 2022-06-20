package ttjj.rank;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.StockAdrCountDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.db.StockAdrCount;
import ttjj.dto.Kline;
import ttjj.dto.RankBizDataDiff;
import ttjj.dto.StockAdrCountCond;
import ttjj.dto.StockAdrCountVo;
import ttjj.service.BizService;
import ttjj.service.KlineService;
import ttjj.service.StockAdrCountService;
import ttjj.service.StockService;
import ttjj.stat.StBizStatDemo;
import utils.ConceptionUtil;
import utils.ContMapBizBaord;
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
//        String date = "2022-06-17";
        String spDate = "";//        String spDate = "2022-05-18";//是否显示特定日期涨跌
        List<BigDecimal> adrMinList = Arrays.asList(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("4"), new BigDecimal("5"), new BigDecimal("6"), new BigDecimal("7"), new BigDecimal("8"), new BigDecimal("9"), new BigDecimal("9.9"));
        List<Integer> daysList = Arrays.asList(TRADE_DAYS_60, TRADE_DAYS_40, TRADE_DAYS_20, TRADE_DAYS_10, TRADE_DAYS_5, TRADE_DAYS_3, TRADE_DAYS_2, TRADE_DAYS_1);
//        List<Integer> daysList = Arrays.asList(TRADE_DAYS_3,TRADE_DAYS_2,TRADE_DAYS_1);
//        BigDecimal adrUpCountSum60Limit = new BigDecimal("200");//涨幅次数限定，过滤杂毛
        BigDecimal adrUpCountSum60Limit = null;//涨幅次数限定，过滤杂毛
        BigDecimal mvLimit = NUM_YI_50;//NUM_YI_1000

        //插入且更新价格区间、更新
        String spBizName = null;//业务类别为空时，插入全部类别
//        String spBizName = "中药";//科技： 光伏设备  能源金属  风电设备    半导体  电池    非金属材料   汽车整车
//        String spBizName = "贸易行业";//消费： 酿酒行业  贸易行业 物流行业
//        String spBizName = "";//资源：能源金属 煤炭行业   化肥行业 农牧饲渔 航天航空    采掘行业  医疗服务 医疗器械
//        String spBizName = "医疗服务";//医疗： 医疗服务 医疗器械 中药 生物制品
//        String spBizName = "工程建设";//金融： 银行  工程咨询服务 证券 房地产开发

        List<RankBizDataDiff> bizList = StockService.listBiz(NUM_MAX_99);//查询业务列表
        int stBizCountTemp = 0;
        int startMapNum = 0;//map的开始，中断后使用，默认可设置为0
        for (RankBizDataDiff rankBizDataDiff : bizList) {
            String bizCode = rankBizDataDiff.getF12();
            //保存-特定业务处理
            if (saveBizSp(date, spBizName, adrMinList, daysList, adrUpCountSum60Limit, mvLimit)) {
                break;
            }

            String bizName = rankBizDataDiff.getF14();
            stBizCountTemp++;
            if (stBizCountTemp < startMapNum) {
                System.out.println("已完成," + (stBizCountTemp) + ":" + bizName);
                continue;//已完成
            }
            System.out.println("-------------------------当前stBizCountTemp：" + (stBizCountTemp) + "---" + bizName);
//            insertListStatStock(date, bizName, adrMinList,daysList);//批量插入-从股票表中统计数据-按照业务类别
//            deleteTodayStAdrCount(date, bizName);//删除
//            insertListByBiz(date, bizCode, bizName);
            updateListByBiz(date, bizCode, bizName);
            updateAdrCount(date, bizName, adrMinList, daysList, adrUpCountSum60Limit);
            updateNetAreaAndMa(date, bizName, adrUpCountSum60Limit,mvLimit);//更新-最新价格、价格区间、均线

//            List<StockAdrCount> stockAdrCountList = findListByCondition(date, bizName, adrUpCountSum60Limit, mvLimit);
//            statStockAdrCount(spDate,date,stockAdrCountList, bizName);//统计股票涨跌次数:0,0为当天
        }

//        List<StockAdrCount> stockAdrCountList = findListByCondition(date, biz);




//        statStockAdrCountBatch(0);//统计股票涨跌次数:0,0为当天


    }

    /**
     * 保存-特定业务处理
     *
     * @param date                 日期
     * @param spBizName            特定业务
     * @param adrMinList           涨幅列表
     * @param daysList             天数列表
     * @param adrUpCountSum60Limit
     * @param mvLimit
     */
    private static boolean saveBizSp(String date, String spBizName, List<BigDecimal> adrMinList, List<Integer> daysList, BigDecimal adrUpCountSum60Limit, BigDecimal mvLimit) {
        if (StringUtils.isBlank(spBizName)) {
//            System.out.println("特定业务处理-业务不能为空");
            return false;
        }
        String bizCode = "";
        System.out.println("特定业务处理：" + spBizName);

        bizCode = ContMapBizBaord.BOARD.get(spBizName);//根据业务名称，查询业务编码

        //                insertListByBiz(date, bizCode);
        updateListByBiz(date, bizCode, spBizName);
        updateAdrCount(date, spBizName, adrMinList, daysList, adrUpCountSum60Limit);
        updateNetAreaAndMa(date, spBizName, adrUpCountSum60Limit, mvLimit);//更新-最新价格、价格区间、均线
        return true;
    }

    /**
     * 插入-根据业务，查询列表
     * 股票状态过滤：退市、退市整理、未上市、st
     *
     * @param date
     * @param biz
     * @param bizName
     * @return
     */
    private static List<StockAdrCount> insertListByBiz(String date, String biz, String bizName) {
        List<StockAdrCount> stockAdrCountList = new ArrayList<>();
//        按板块查询
//        System.out.println("-------------------------当前biz：" + biz);
        List<RankStockCommpanyDb> stList = BizService.listRankStockByBiz(NUM_MAX_999, biz);
        for (RankStockCommpanyDb rankStockCommpanyDb : stList) {
            //只更新主板板块
            if (rankStockCommpanyDb.getF139() != DB_RANK_BIZ_F139_BK_MAIN) {
//                    System.out.println("暂不更新（非主板）！" + JSON.toJSONString(entity));
                continue;
            }
            // 股票状态过滤：退市、退市整理、未上市、st
            Long stStatus = rankStockCommpanyDb.getF148();
            if (DB_RANK_BIZ_F148_STOCK_STATUS_DELISTED == stStatus || DB_RANK_BIZ_F148_STOCK_STATUS_DELISTING == stStatus) {
//                    System.out.println("均线价格暂不更新（退市）！" + JSON.toJSONString(entity));
                continue;
            }
            if (DB_RANK_BIZ_F148_STOCK_STATUS_UNLISTED == stStatus) {
//                    System.out.println("均线价格暂不更新（未上市）！" + JSON.toJSONString(entity));
                continue;
            }
            if (DB_RANK_BIZ_F148_STOCK_STATUS_SUSPENSION == stStatus) {
//                    System.out.println("均线价格暂不更新（暂停上市）！" + JSON.toJSONString(entity));
                continue;
            }
            if (DB_RANK_BIZ_F148_STOCK_STATUS_ST == stStatus) {
//                    System.out.println("均线价格暂不更新（ST股）！" + JSON.toJSONString(entity));
                continue;
            }

            StockAdrCount entity = new StockAdrCount();
            entity.setDate(date);
            entity.setType_name(bizName);
            entity.setF139(rankStockCommpanyDb.getF139());
            entity.setConception(rankStockCommpanyDb.getConception());
            if (rankStockCommpanyDb.getF2() != null) {
                entity.setF2(new BigDecimal(rankStockCommpanyDb.getF2()));
            }
            entity.setF3(rankStockCommpanyDb.getF3());
            if (rankStockCommpanyDb.getF4() != null) {
                entity.setF4(new BigDecimal(rankStockCommpanyDb.getF4()));
            }
            entity.setF5(rankStockCommpanyDb.getF5());
            entity.setF6(rankStockCommpanyDb.getF6());
            if (rankStockCommpanyDb.getF7() != null) {
                entity.setF7(new BigDecimal(rankStockCommpanyDb.getF7()));
            }
            if (rankStockCommpanyDb.getF8() != null) {
                entity.setF8(new BigDecimal(rankStockCommpanyDb.getF8()));
            }
            if (rankStockCommpanyDb.getF9() != null) {
                entity.setF9(new BigDecimal(rankStockCommpanyDb.getF9()));
            }
            entity.setF10(rankStockCommpanyDb.getF10());
            entity.setF12(rankStockCommpanyDb.getF12());
            entity.setF14(rankStockCommpanyDb.getF14());
            if (rankStockCommpanyDb.getF15() != null) {
                entity.setF15(new BigDecimal(rankStockCommpanyDb.getF15().toString()));
            }
            if (rankStockCommpanyDb.getF16() != null) {
                entity.setF16(new BigDecimal(rankStockCommpanyDb.getF16().toString()));
            }
            if (rankStockCommpanyDb.getF17() != null) {
                entity.setF17(new BigDecimal(rankStockCommpanyDb.getF17().toString()));
            }
            if (rankStockCommpanyDb.getF18() != null) {
                entity.setF18(new BigDecimal(rankStockCommpanyDb.getF18().toString()));
            }
            entity.setF20(rankStockCommpanyDb.getF20());
            entity.setF21(rankStockCommpanyDb.getF21());
            entity.setF62(rankStockCommpanyDb.getF62());

            stockAdrCountList.add(entity);
        }

        System.out.println("插入成功-涨幅次数统计：" + StockAdrCountService.insertList(stockAdrCountList));
        return stockAdrCountList;
    }

    /**
     * 更新-根据业务，批量更新基础信息
     *
     * @param date
     * @param biz
     * @param bizName
     * @return
     */
    private static List<StockAdrCount> updateListByBiz(String date, String biz, String bizName) {
        List<StockAdrCount> stockAdrCountList = new ArrayList<>();
//        按板块查询
//        System.out.println("-------------------------当前biz：" + biz);
        List<RankStockCommpanyDb> stList = BizService.listRankStockByBiz(NUM_MAX_999, biz);
        for (RankStockCommpanyDb rankStockCommpanyDb : stList) {
            //只更新主板板块
            if (rankStockCommpanyDb.getF139() != DB_RANK_BIZ_F139_BK_MAIN) {
//                    System.out.println("暂不更新（非主板）！" + JSON.toJSONString(entity));
                continue;
            }
            StockAdrCount entity = new StockAdrCount();
            entity.setDate(date);
            entity.setType_name(bizName);
            entity.setF139(rankStockCommpanyDb.getF139());
            entity.setF12(rankStockCommpanyDb.getF12());
            entity.setConception(rankStockCommpanyDb.getConception());
            if (rankStockCommpanyDb.getF2() != null) {
                entity.setF2(new BigDecimal(rankStockCommpanyDb.getF2()));
            }
            entity.setF3(rankStockCommpanyDb.getF3());
            if (rankStockCommpanyDb.getF4() != null) {
                entity.setF4(new BigDecimal(rankStockCommpanyDb.getF4()));
            }
            entity.setF5(rankStockCommpanyDb.getF5());
            entity.setF6(rankStockCommpanyDb.getF6());
            if (rankStockCommpanyDb.getF7() != null) {
                entity.setF7(new BigDecimal(rankStockCommpanyDb.getF7()));
            }
            if (rankStockCommpanyDb.getF8() != null) {
                entity.setF8(new BigDecimal(rankStockCommpanyDb.getF8()));
            }
            if (rankStockCommpanyDb.getF9() != null) {
                entity.setF9(new BigDecimal(rankStockCommpanyDb.getF9()));
            }
            entity.setF10(rankStockCommpanyDb.getF10());
            entity.setF14(rankStockCommpanyDb.getF14());
            if (rankStockCommpanyDb.getF15() != null) {
                entity.setF15(new BigDecimal(rankStockCommpanyDb.getF15().toString()));
            }
            if (rankStockCommpanyDb.getF16() != null) {
                entity.setF16(new BigDecimal(rankStockCommpanyDb.getF16().toString()));
            }
            if (rankStockCommpanyDb.getF17() != null) {
                entity.setF17(new BigDecimal(rankStockCommpanyDb.getF17().toString()));
            }
            if (rankStockCommpanyDb.getF18() != null) {
                entity.setF18(new BigDecimal(rankStockCommpanyDb.getF18().toString()));
            }
            entity.setF20(rankStockCommpanyDb.getF20());
            entity.setF62(rankStockCommpanyDb.getF62());
            entity.setF21(rankStockCommpanyDb.getF21());

            if (entity.getF3() == null) {
                System.out.println("rankStockCommpanyDb异常：" + JSON.toJSONString(rankStockCommpanyDb));
            }
            stockAdrCountList.add(entity);
        }

        int rs = 0;
        for (StockAdrCount stockAdrCount : stockAdrCountList) {
            int updateRs = StockAdrCountService.update(stockAdrCount);
            if (updateRs != 1) {
                System.out.println("更新-失败：" + rs + "" + JSON.toJSONString(stockAdrCount));
            } else {
                rs++;
            }
        }
        System.out.println("当前biz：" + bizName + ",根据业务，批量更新基础信息成功-涨幅次数统计：" + rs);

        return stockAdrCountList;
    }

    /**
     * 更新涨幅次数
     *
     * @param date
     * @param biz
     * @param adrMinList
     * @param daysList             +
     * @param adrUpCountSum60Limit 涨幅次数限定，过滤杂毛
     */
    private static void updateAdrCount(String date, String biz, List<BigDecimal> adrMinList, List<Integer> daysList, BigDecimal adrUpCountSum60Limit) {
        List<StockAdrCount> stockAdrCountList = null;
        boolean isShowPriceArea = false;//是否显示价格区间
        long board = DB_RANK_BIZ_F19_BK_MAIN;
        BigDecimal mvLimit = NUM_YI_50;
        int rs = 0;

//        按板块查询
//        System.out.println("-------------------------当前biz：" + biz);
        List<RankStockCommpanyDb> stList = StockService.findListByCondition(biz, date, board, mvLimit);//查询股票列表-根据板块：
        String endDate = StBizStatDemo.findBegDate(date, 2);//只统计今天之前的数据
        stockAdrCountList = StBizStatDemo.showAdrCount(endDate, stList, board, mvLimit, adrMinList, daysList, biz, "", isShowPriceArea);//统计涨跌次数
        for (StockAdrCount stockAdrCount : stockAdrCountList) {
            BigDecimal adrUpCountSum60 = stockAdrCount.getADR_UP_COUNT_SUM_60();
            //涨幅次数限定，过滤杂毛
            if (!checkAdrUpCount(adrUpCountSum60Limit, stockAdrCount)) {
                continue;
            }
            StockAdrCount entity = new StockAdrCount();
            entity.setDate(date);
            entity.setF12(stockAdrCount.getF12());
            entity.setADR_UP_COUNT_SUM_60(adrUpCountSum60);
            entity.setADR_UP_COUNT_1(stockAdrCount.getADR_UP_COUNT_1());
            entity.setADR_UP_COUNT_2(stockAdrCount.getADR_UP_COUNT_2());
            entity.setADR_UP_COUNT_3(stockAdrCount.getADR_UP_COUNT_3());
            entity.setADR_UP_COUNT_5(stockAdrCount.getADR_UP_COUNT_5());
            entity.setADR_UP_COUNT_10(stockAdrCount.getADR_UP_COUNT_10());
            entity.setADR_UP_COUNT_20(stockAdrCount.getADR_UP_COUNT_20());
            entity.setADR_UP_COUNT_40(stockAdrCount.getADR_UP_COUNT_40());
            entity.setADR_UP_COUNT_60(stockAdrCount.getADR_UP_COUNT_60());
//            entity.setADR_UP_COUNT_120(stockAdrCount.getADR_UP_COUNT_120());
//            entity.setADR_UP_COUNT_250(stockAdrCount.getADR_UP_COUNT_250());
            entity.setOrder_num(stockAdrCount.getOrder_num());
            int updateRs = StockAdrCountService.update(entity);
            if (updateRs != 1) {
                System.out.println("更新-涨幅次数统计-失败：" + rs + "" + JSON.toJSONString(entity));
            } else {
                rs++;
            }
        }
        System.out.println("更新-涨幅次数统计-成功：" + rs);
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
     * 插入且更新价格区间、更新
     *
     * @param date
     */
    private static void insertListStockAdrCountAndUpdateNetAreaMa(String date, List<BigDecimal> adrMinList, BigDecimal adrUpCountSum60Limit) {
        List<StockAdrCount> stockAdrCountList = null;
        List<Integer> daysList = Arrays.asList(MA_60, MA_40, MA_20, MA_10, MA_5);
        boolean isShowPriceArea = false;//是否显示价格区间
        long board = DB_RANK_BIZ_F19_BK_MAIN;
        BigDecimal mvLimit = NUM_YI_50;

        //批量插入-从股票表中统计数据-按照业务类别
//        insertListStatStock(date, biz);
        List<RankBizDataDiff> bizList = StockService.listBiz(NUM_MAX_99);//查询业务列表
        int stBizCountTemp = 0;
        for (RankBizDataDiff rankBizDataDiff : bizList) {
            String biz = rankBizDataDiff.getF14();
            System.out.println("-------------------------当前stBizCountTemp：" + (++stBizCountTemp) + "---" + biz);

            //先删除，后插入
            List<RankStockCommpanyDb> stList = StockService.findListByCondition(biz, date, board, mvLimit);//查询股票列表-根据板块：
            stockAdrCountList = StBizStatDemo.showAdrCount(date, stList, board, mvLimit, adrMinList, daysList, biz, "", isShowPriceArea);//统计涨跌次数
            deleteTodayStAdrCount(date, biz);//先删除，后插入
            System.out.println("插入成功-涨幅次数统计：" + StockAdrCountService.insertList(stockAdrCountList));

            stockAdrCountList = findListByCondition(date, biz, adrUpCountSum60Limit, mvLimit);
            //更新-价格区间
            updateNetArea(date, stockAdrCountList);
            //更新-超过均线信息
            updateUpMa(date, stockAdrCountList);
        }
    }

    /**
     * 更新-最新价格、价格区间、均线
     *
     * @param date                 日期
     * @param adrUpCountSum60Limit 涨幅次数限定
     * @param mvLimit
     */
    private static void updateNetAreaAndMa(String date, String biz, BigDecimal adrUpCountSum60Limit, BigDecimal mvLimit) {
        List<StockAdrCount> stockAdrCountList = findListByCondition(date, biz, adrUpCountSum60Limit, mvLimit);//查询列表-根据条件
        updateNetArea(date, stockAdrCountList);//更新-价格区间
        updateUpMa(date, stockAdrCountList);//更新-超过均线信息
    }

    /**
     * 批量插入-从股票表中统计数据-按照业务类别
     *
     * @param date       日期
     * @param biz        限定业务
     * @param adrMinList 最低涨幅列表
     * @param daysList
     * @return 插入数据集
     */
    private static List<StockAdrCount> insertListStatStock(String date, String biz, List<BigDecimal> adrMinList, List<Integer> daysList) {
        List<StockAdrCount> stockAdrCountList = null;
        boolean isShowPriceArea = false;//是否显示价格区间
        long board = DB_RANK_BIZ_F19_BK_MAIN;
        BigDecimal mvLimit = NUM_YI_50;

//        按板块查询
//        System.out.println("-------------------------当前biz：" + biz);
        List<RankStockCommpanyDb> stList = StockService.findListByCondition(biz, date, board, mvLimit);//查询股票列表-根据板块：
        stockAdrCountList = StBizStatDemo.showAdrCount(date, stList, board, mvLimit, adrMinList, daysList, biz, "", isShowPriceArea);//统计涨跌次数
        deleteTodayStAdrCount(date, biz);//先删除，后插入
        System.out.println("插入成功-涨幅次数统计：" + StockAdrCountService.insertList(stockAdrCountList));
        return stockAdrCountList;
    }

    /**
     * 更新-价格区间
     *
     * @param date              日期
     * @param stockAdrCountList 更新列表
     */
    private static void updateNetArea(String date, List<StockAdrCount> stockAdrCountList) {
        int updateRs = 0;//更新成功个数
        if (stockAdrCountList == null) {
            System.out.println("更新-净值区间:stockAdrCountList==null");
            return;
        }
        for (StockAdrCount stockAdrCount : stockAdrCountList) {
            StockAdrCount entity = new StockAdrCount();
            String zqdm = stockAdrCount.getF12();
            entity.setF12(zqdm);
            entity.setDate(stockAdrCount.getDate());
            entity.setUPDATE_TIME(new Date());

            //处理价格区间
            entity.setNET_AREA_DAY_5(KlineService.handlerPriceAreaRate(zqdm, MA_5, KLT_101, false, "", date, KLINE_TYPE_STOCK));
            entity.setNET_AREA_DAY_10(KlineService.handlerPriceAreaRate(zqdm, MA_10, KLT_101, false, "", date, KLINE_TYPE_STOCK));
            entity.setNET_AREA_DAY_20(KlineService.handlerPriceAreaRate(zqdm, MA_20, KLT_101, false, "", date, KLINE_TYPE_STOCK));
            entity.setNET_AREA_DAY_40(KlineService.handlerPriceAreaRate(zqdm, MA_40, KLT_101, false, "", date, KLINE_TYPE_STOCK));
            entity.setNET_AREA_DAY_60(KlineService.handlerPriceAreaRate(zqdm, MA_60, KLT_101, false, "", date, KLINE_TYPE_STOCK));
//            stockAdrCount.setNET_AREA_DAY_120(KlineService.handlerPriceAreaRate(zqdm, MA_120, KLT_101, false, "", date, KLINE_TYPE_STOCK));
//            stockAdrCount.setNET_AREA_DAY_250(KlineService.handlerPriceAreaRate(zqdm, MA_250, KLT_101, false, "", date, KLINE_TYPE_STOCK));
            //更新
            updateRs += StockAdrCountService.update(entity);
//            System.out.println("更新-净值区间:" + stockAdrCount.getF14() + StockAdrCountService.update(entity));
        }
        System.out.println("更新-净值区间-个数:" + stockAdrCountList.size() + ",更新成功：" + updateRs);
    }

    /**
     * 更新-最新价格
     *
     * @param date
     * @param stockAdrCountList
     */
    private static void updateCurPrice(String date, List<StockAdrCount> stockAdrCountList) {
        int updateRs = 0;//更新成功个数
        if (stockAdrCountList == null) {
            System.out.println("更新-净值区间:stockAdrCountList==null");
        }
        for (StockAdrCount stockAdrCount : stockAdrCountList) {
            StockAdrCount entity = new StockAdrCount();
            String zqdm = stockAdrCount.getF12();
            entity.setF12(zqdm);
            entity.setDate(stockAdrCount.getDate());

            //处理
            RankStockCommpanyDb stock = new RankStockCommpanyDb();
            stock.setF12(zqdm);
            Kline kline = KlineService.findLast(stock, date, KLT_101);
            if (kline != null) {
                entity.setF3(kline.getZhangDieFu());
                entity.setF2(kline.getCloseAmt());
                entity.setF15(kline.getMaxAmt());
                entity.setF16(kline.getMinAmt());
            }
            //更新
            updateRs += StockAdrCountService.update(entity);
//            System.out.println("更新-最新价格:" + stockAdrCount.getF14() + StockAdrCountService.update(entity));
        }
        System.out.println("更新-最新价格-个数:" + stockAdrCountList.size() + ",更新成功：" + updateRs);
    }

    /**
     * 查询列表-根据条件
     *
     * @param maDate               日期
     * @param biz                  业务
     * @param adrUpCountSum60Limit 涨幅次数限定
     * @param mvLimit              市值限定
     */
    private static List<StockAdrCount> findListByCondition(String maDate, String biz, BigDecimal adrUpCountSum60Limit, BigDecimal mvLimit) {
        //        List<BigDecimal> orderNumList = Arrays.asList(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3"));
        List<BigDecimal> orderNumList = null;
//        List<Boolean> upMaList = Arrays.asList(false,true,true);//判断是否超过均线列表：15,30,60
        List<Boolean> upMaList = null;//判断是否超过均线列表：15,30,60

        String orderBy = " ADR_UP_COUNT_60 DESC ";//排序   ADR_UP_COUNT_5 DESC    ADR_UP_COUNT_SUM_60
//        if (maDateInt >= 0 ) {
//            List<String> dateListBefore = RankStockCommpanyDao.findListDateBefore(new DateCond(date, 20));
//            maDate = dateListBefore.get(maDateInt);
//        }


//        List<BigDecimal> orderNumList = Arrays.asList(new BigDecimal("1"), new BigDecimal("2"));
//        List<BigDecimal> orderNumList = Arrays.asList(new BigDecimal("1"));
        StockAdrCountCond condition = new StockAdrCountCond();
        condition.setDate(maDate);
        condition.setOrderNumList(orderNumList);
        condition.setADR_UP_COUNT_SUM_60(adrUpCountSum60Limit);
        condition.setOrderBy(orderBy);
        if (StringUtils.isNotBlank(biz)) {
            condition.setType_name(biz);
        }
        return StockAdrCountService.findListByCondition(condition);
    }

    /**
     * 更新-超过均线信息
     *
     * @param maDate
     * @param stockAdrCountList
     */
    private static void updateUpMa(String maDate, List<StockAdrCount> stockAdrCountList) {
        int updateRs = 0;//更新成功个数
        if (stockAdrCountList == null) {
            System.out.println("更新-超过均线信息:stockAdrCountList==null");
        }
        for (StockAdrCount stockAdrCount : stockAdrCountList) {
            StockAdrCount entity = new StockAdrCount();
            entity.setF12(stockAdrCount.getF12());
            entity.setDate(stockAdrCount.getDate());

            boolean isUp = true;//检查上涨
            List<Integer> maList = new ArrayList<>();
            maList.add(MA_60);

            //判断是否超过均线列表：15,30,60
            RankStockCommpanyDb stock = new RankStockCommpanyDb();
            stock.setF12(stockAdrCount.getF12());
            //显示信息-上涨均线
            boolean isMa15 = KlineService.showUpMa(stock, KLT_15, maList, maDate, isUp);//显示信息-上涨均线
            if (isMa15) {
                entity.setUP_MA_15(KLT_15 + "(" + MA_60 + ")");
            } else {
                entity.setUP_MA_15("");
            }
            boolean isMa30 = KlineService.showUpMa(stock, KLT_30, maList, maDate, isUp);//显示信息-上涨均线
            if (isMa30) {
                entity.setUP_MA_30(KLT_30 + "(" + MA_60 + ")");
            } else {
                entity.setUP_MA_30("");
            }
            boolean isMa60 = KlineService.showUpMa(stock, KLT_60, maList, maDate, isUp);//显示信息-上涨均线
            if (isMa60) {
                entity.setUP_MA_60(KLT_60 + "(" + MA_60 + ")");
            } else {
                entity.setUP_MA_60("");
            }
            boolean isMa101 = KlineService.showUpMa(stock, KLT_101, maList, maDate, isUp);//显示信息-上涨均线
            if (isMa101) {
                entity.setUP_MA_101(KLT_101 + "(" + MA_60 + ")");
            } else {
                entity.setUP_MA_101("");
            }
            boolean isMa102 = KlineService.showUpMa(stock, KLT_102, maList, maDate, isUp);//显示信息-上涨均线
            if (isMa102) {
                entity.setUP_MA_102(KLT_102 + "(" + MA_60 + ")");
            } else {
                entity.setUP_MA_102("");
            }

            boolean isHasMa = isMa15 || isMa30 || isMa60 || isMa101 || isMa102;
            if (isHasMa) {
                //更新
                int rs = StockAdrCountService.update(entity);
                updateRs += rs;
                System.out.println("更新-超过均线信息:" + stockAdrCount.getF14() + ",是否成功：" + rs);
            } else {
//                System.out.println("更新-超过均线信息:" + stockAdrCount.getF14() + "未超过任何均线，不做处理");
            }
        }
        System.out.println("更新-超过均线信息-个数:" + stockAdrCountList.size() + ",更新成功：" + updateRs);
    }

    /**
     * @param date
     */
    private static void insertStockAdrCount(String date) {
        List<BigDecimal> adrMinList = Arrays.asList(new BigDecimal("0"), new BigDecimal("1"), new BigDecimal("3"), new BigDecimal("5"), new BigDecimal("7"), new BigDecimal("9"));
        List<Integer> daysList = Arrays.asList(MA_60, MA_40, MA_20, MA_10, MA_5);
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
            System.out.println("-------------------------当前stBizCountTemp：" + (++stBizCountTemp) + "---" + biz);
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
    }

    /**
     * 统计股票涨跌次数:批量
     *
     * @param days 天数
     */
    private static void statStockAdrCountBatch(int days, String date, List<StockAdrCount> stockAdrCountList, String biz) {
        for (int i = 0; i <= days; i++) {
            statStockAdrCount(date, date, stockAdrCountList, biz);
        }
    }

    /**
     * 统计股票涨跌次数
     */
    private static void statStockAdrCount(String spDate, String maDate, List<StockAdrCount> stockAdrCountList, String biz) {
        boolean isShowPriceArea = true;//是否显示价格区间
//        boolean isShowPriceArea = false;//是否显示价格区间

        int overCount = 0;//第二天上涨个数
        int downCount = 0;//第二天下跌个数

        for (StockAdrCount stockAdrCount : stockAdrCountList) {
            StringBuffer sbStockAdrCount = new StringBuffer();
//            sbStockAdrCount.append(StockUtil.handlerStName(stockAdrCount.getF14())).append("\t");
            String stName = StockUtil.handlerStName(stockAdrCount.getF14());
            //            map.put("002432", "");//002432	九安医疗	医疗器械
            String concepPinYin = "mapGn";
            if (ConceptionUtil.stConceptionMap.get(biz) != null) {
                concepPinYin = ConceptionUtil.stConceptionMap.get(biz);
            }
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
                StockDemo.handlerNetMa(stock, maUpdateMap, maDate, sbPriceArea, new StockAdrCountVo());//处理均线净值
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
        System.out.println("第二天上涨-下跌比:" + overCount + ":" + downCount);
        System.out.println();

//        StockStatDemo.checkMaDemo(zqMap, date);

    }

    /**
     * 删除数据-今日
     *
     * @param biz
     */
    private static void deleteTodayStAdrCount(String date, String biz) {
        String today = DateUtil.getToday(DateUtil.YYYY_MM_DD);
        if (!today.equals(date)) {
            System.out.println("不是删除今日数据，请注意！");
//            return;
        }
        StockAdrCount condition = new StockAdrCount();
        condition.setDate(date);
        condition.setType_name(biz);
        int rs = StockAdrCountDao.deleteByCondition(condition);
        System.out.println("日期：" + date + "，删除结果：" + rs);
    }
}
