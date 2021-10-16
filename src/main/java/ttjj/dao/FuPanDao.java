package ttjj.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.db.AssetPositionDb;
import ttjj.db.Fupan;

/**
 * FuPanDao简介
 *
 * @author Administrator
 * @date 2021-10-16 22:07
 */
public class FuPanDao {
    /**
     * sqlSessionFactory mybatis
     */
    static SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();
    /**
     * 更新-我的股票-资产持仓
     *
     * @param fupan
     */
    public static int updateMyStockAssetPosition(Fupan fupan) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.update("ttjj.dao.mapper.FupanMapper.updateMyStockAssetPosition", fupan);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * @param condition
     * @return
     */
    public static Fupan findDbByDate(Fupan condition) {
        SqlSession session = sqlSessionFactory.openSession();
        Fupan fupan = null;
        try {
            fupan = session.selectOne("ttjj.dao.mapper.FupanMapper.findDbByDate", condition);
            session.commit();
        } finally {
            session.close();
        }
        return fupan;
    }

    /**
     * 插入-复盘我的持仓明细
     *
     * @param entity
     * @return
     */
    public static int insertDbFupanPosition(AssetPositionDb entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.insert("ttjj.dao.mapper.FupanPositionMapper.insert", entity);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rs;
    }
    /**
     * 更新数据库
     *
     * @param fupan
     */
    public static int updateDb(Fupan fupan) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.update("ttjj.dao.mapper.FupanMapper.updateFupan", fupan);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }
}
