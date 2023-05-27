package ttjj.service;

import ttjj.dao.CarIncomeDao;
import ttjj.dao.KlineDao;
import ttjj.db.CarIncome;

/**
 * CarIncomeService简介
 *
 * @author Administrator
 * @date 2023-05-28 00:49
 */
public class CarIncomeService {

    /**
     * 插入
     *
     * @param entity
     * @return
     */
    public static Integer insert(CarIncome entity) {
        Integer rs = 0;
        if (entity == null) {
            return rs;
        }
        /**
         * 插入数据库-K线
         */
        return CarIncomeDao.insert(entity);
    }
}
