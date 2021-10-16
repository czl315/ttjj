package ttjj.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.db.AssetPositionDb;
import ttjj.db.FundRank;

/**
 * FundRankDao简介
 *
 * @author Administrator
 * @date 2021-10-16 22:04
 */
public class FundRankDao {
    /**
     * sqlSessionFactory mybatis
     */
    static SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();
    /**
     * db-插入
     *
     * @param entity
     */
    public static int insertDb(FundRank entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
//                System.out.println(JSON.toJSONString(entity));
            rs = session.insert("ttjj.dao.mapper.RankFundMapper.insert", entity);
            session.commit();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return rs;
    }
}
