package ttjj.service;

import com.alibaba.fastjson.JSON;
import ttjj.dao.StockAdrCountDao;
import ttjj.db.StockAdrCount;
import ttjj.dto.CondStockAdrCount;
import ttjj.dto.Kline;
import ttjj.dto.StockAdrCountVo;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import static utils.Content.*;

/**
 * 股票涨跌次数
 *
 * @author Administrator
 * @date 2022-05-10 22:48
 */
public class StockAdrCountService {
    private final static Logger logger = Logger.getLogger(StockAdrCountService.class.getName());

    /**
     * 批量插入
     *
     * @param stockAdrCount
     * @return
     */
    public static Integer insert(StockAdrCount stockAdrCount) {
        return StockAdrCountDao.insert(stockAdrCount);
    }

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    public static Integer insertList(List<StockAdrCount> list) {
        Integer rs = 0;
        if (list == null) {
            return rs;
        }
        for (StockAdrCount stockAdrCount : list) {
            /**
             * 插入数据库-K线
             */
            int rsSave = StockAdrCountDao.insert(stockAdrCount);
            //打印保存成功的记录
            if (rsSave > 0) {
                logger.info("保存成功的记录：" + JSON.toJSONString(stockAdrCount));
                rs += rsSave;
            }
        }
        return rs;
    }

    /**
     * 批量插入-先查询
     *
     * @param list
     * @return
     */
    public static Integer insertListBeforeFind(List<StockAdrCount> list) {
        Integer rs = 0;
        if (list == null) {
            return rs;
        }
        for (StockAdrCount stockAdrCount : list) {
            StockAdrCount entity = StockAdrCountDao.findByCondition(stockAdrCount);
            if (entity != null) {
//                logger.info("记录已存在：" + stockAdrCount.getF14());
//                System.out.println("记录已存在：" + stockAdrCount.getF14());
                continue;
            }
            /**
             * 插入数据库-K线
             */
            int rsSave = StockAdrCountDao.insert(stockAdrCount);
            //打印保存成功的记录
            if (rsSave > 0) {
//                logger.info("保存成功的记录：" + JSON.toJSONString(stockAdrCount));
//                System.out.println("保存成功的记录：" + stockAdrCount.getF14() + JSON.toJSONString(stockAdrCount));
                rs += rsSave;
            }
        }
        return rs;
    }


    /**
     * 批量插入：插入之前先查询是否存在(日期和code)，如果存在则先删除再插入
     *
     * @param list
     * @return
     */
    public static Integer insertListOrUpdate(List<StockAdrCount> list) {
        Integer rs = 0;
        if (list == null) {
            return rs;
        }
        for (StockAdrCount stockAdrCount : list) {
            StockAdrCount entity = StockAdrCountDao.findByCondition(stockAdrCount);
            if (entity != null) {
                int deleRs = StockAdrCountDao.deleteByCondition(entity);
                System.out.println("数据已存在，先删除:" + deleRs + ";");//+ JSON.toJSONString(entity)
            }
            //插入数据库-K线
            rs += StockAdrCountDao.insert(stockAdrCount);
        }
//        System.out.println("批量插入:" + rs);
        return rs;
    }

    /**
     * 查询列表-根据条件
     *
     * @param condition condition
     * @return rs
     */
    public static List<StockAdrCountVo> listStAdrCount(CondStockAdrCount condition) {
        return StockAdrCountDao.listStAdrCount(condition);
    }

    /**
     * 更新
     *
     * @param stockAdrCount 更新内容和条件
     * @return 结果
     */
    public static Integer update(StockAdrCount stockAdrCount) {
        return StockAdrCountDao.update(stockAdrCount);
    }

    /**
     * 查询日净值数据:查询日k线
     *
     * @param stockAdrCountList 涨幅列表
     * @param date              日期
     * @param days           日类型
     * @param compMaType        比较均线类型
     * @param condFind
     */
    public static void handlerNetDay(List<StockAdrCountVo> stockAdrCountList, String date, int days, String compMaType, CondStockAdrCount condFind) {
        if (stockAdrCountList == null) {
            return;
        }
        if (KLT_102.equals(compMaType) && !condFind.isShowMaWeekCountUpDown()) {
            return;
        }
        if (KLT_101.equals(compMaType) && !condFind.isShowMaDayCountUpDown()) {
            return;
        }
        String endDate = date;
        String begDate = StockService.findBegDate(date, days);
        for (StockAdrCountVo stockAdrCountVo : stockAdrCountList) {
            List<Kline> klines = KlineService.kline(stockAdrCountVo.getF12(), days, KLT_101, true, begDate, endDate, null);
            if (klines == null) {
                continue;
            }
            stockAdrCountVo.setNetDayLast20(klines);

            //比较净值，计算超过次数、低于次数
            int countUpNet = 0;
            int countDownNet = 0;
            BigDecimal compMaNet = null;
            if (KLT_102.equals(compMaType)) {
                compMaNet = stockAdrCountVo.getMA_NET_60_102();
            } else if (KLT_101.equals(compMaType)) {
                compMaNet = stockAdrCountVo.getMA_NET_60_101();
            }
            for (Kline kline : klines) {
                if (kline.getCloseAmt() == null || compMaNet==null) {
                    continue;
                }
                if (kline.getCloseAmt().compareTo(compMaNet) >= 0) {
                    countUpNet++;
                } else {
                    countDownNet++;
                }
            }
            if (KLT_102.equals(compMaType)) {
                if (DAY_20 == days) {
                    stockAdrCountVo.setCountUpMa102Type60LastDay20(countUpNet);
                    stockAdrCountVo.setCountDownMa102Type60LastDay20(countDownNet);
                }
                if (DAY_10 == days) {
                    stockAdrCountVo.setCountUpMa102Type60LastDay10(countUpNet);
                    stockAdrCountVo.setCountDownMa102Type60LastDay10(countDownNet);
                }
            }
            if (KLT_101.equals(compMaType)) {
                if (DAY_20 == days) {
                    stockAdrCountVo.setCountUpMa101Type60LastDay20(countUpNet);
                    stockAdrCountVo.setCountDownMa101Type60LastDay20(countDownNet);
                }
                if (DAY_10 == days) {
                    stockAdrCountVo.setCountUpMa101Type60LastDay10(countUpNet);
                    stockAdrCountVo.setCountDownMa101Type60LastDay10(countDownNet);
                }
            }
        }
    }
}
