package ttjj.service;

import ttjj.dao.StockConceptionDao;
import ttjj.db.StockConception;

/**
 * StockConceptionService简介
 *
 * @author Administrator
 * @date 2023-03-05 11:26
 */
public class StockConceptionService {

    /**
     * 插入
     *
     * @param entity
     * @return
     */
    public static Integer insert(StockConception entity) {
        Integer rs = 0;
        if (entity == null) {
            return rs;
        }
        /**
         * 插入数据库-K线
         */
        return StockConceptionDao.insert(entity);
    }
}
