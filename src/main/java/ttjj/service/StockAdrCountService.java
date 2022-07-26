package ttjj.service;

import ttjj.dao.StockAdrCountDao;
import ttjj.db.StockAdrCount;
import ttjj.dto.CondStockAdrCount;

import java.util.List;

/**
 * 股票涨跌次数
 *
 * @author Administrator
 * @date 2022-05-10 22:48
 */
public class StockAdrCountService {
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
            rs += StockAdrCountDao.insert(stockAdrCount);
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
                System.out.println("数据已存在，先删除:" + deleRs + ";" );//+ JSON.toJSONString(entity)
            }
            /**
             * 插入数据库-K线
             */
            rs += StockAdrCountDao.insert(stockAdrCount);
        }
//        System.out.println("批量插入:" + rs);
        return rs;
    }

    /**
     * 查询列表-根据条件
     * @param condition
     * @return
     */
    public static List<StockAdrCount> findListByCondition(CondStockAdrCount condition) {
        return StockAdrCountDao.findListByCondition(condition);
    }

    /**
     * 更新
     * @param stockAdrCount 更新内容和条件
     * @return 结果
     */
    public static Integer update(StockAdrCount stockAdrCount) {
        return StockAdrCountDao.update(stockAdrCount);
    }
}
