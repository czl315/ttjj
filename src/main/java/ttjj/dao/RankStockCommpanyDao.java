package ttjj.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.db.AssetPositionDb;
import ttjj.db.RankStockCommpanyDb;

/**
 * @author chenzhilong
 * @date 2021/7/26
 */
public class RankStockCommpanyDao {
    /**
     * sqlSessionFactory mybatis
     */
    static SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();
    /**
     * db-插入
     *
     * @param entity
     */
    public static int insertDb(RankStockCommpanyDb entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
//                System.out.println(JSON.toJSONString(entity));
            rs = session.insert("ttjj.dao.mapper.RankStockCommpanyMapper.insert", entity);
            session.commit();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * @param entity
     * @return
     */
    public static int updateByCode(RankStockCommpanyDb entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.update("ttjj.dao.mapper.RankStockCommpanyMapper.update", entity);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rs;
    }
}
