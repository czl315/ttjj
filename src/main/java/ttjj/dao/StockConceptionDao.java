package ttjj.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.db.StockConception;
import ttjj.dto.Kline;
import ttjj.dto.StatCondStAdrCountKline;
import ttjj.dto.StatRsStAdrCountKline;

import java.util.List;

/**
 * StockConceptionDao简介
 *
 * @author Administrator
 * @date 2023-03-05 11:24
 */
public class StockConceptionDao {
    /**
     * sqlSessionFactory mybatis
     */
    static SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();

    /**
     * 业务排行-插入
     *
     * @param entity
     */
    public static int insert(StockConception entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.insert("ttjj.dao.mapper.StockConceptionMapper.insert", entity);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }
}
