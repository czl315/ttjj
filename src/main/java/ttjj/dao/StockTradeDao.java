package ttjj.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.db.RankStockCommpanyDb;
import ttjj.db.StockTradeDb;

/**
 * @author chenzhilong
 * @date 2021/7/26
 */
public class StockTradeDao {
    /**
     * sqlSessionFactory mybatis
     */
    static SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();
    /**
     * db-插入
     *
     * @param entity
     */
    public static int insertDb(StockTradeDb entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
//                System.out.println(JSON.toJSONString(entity));
            rs = session.insert("ttjj.dao.mapper.StockTradeMapper.insert", entity);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * @param entity
     * @return
     */
    public static int updateByCode(StockTradeDb entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.update("ttjj.dao.mapper.StockTradeMapper.updateNet", entity);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * 更新-卖出
     *
     * @param entity
     * @return
     */
    public static int updateSellOut(StockTradeDb entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.update("ttjj.dao.mapper.StockTradeMapper.updateSellOut", entity);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }
}
