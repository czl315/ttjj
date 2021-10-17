package ttjj.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.db.AssetPositionDb;
import ttjj.dto.DateCond;

import java.util.List;

/**
 * @author chenzhilong
 * @date 2021/7/26
 */
public class FupanPositionDao {
    /**
     * sqlSessionFactory mybatis
     */
    static SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();
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
     * 更新复盘仓位
     * @param entity
     * @return
     */
    public static int updateDbFupan(AssetPositionDb entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.insert("ttjj.dao.mapper.FupanPositionMapper.updateEntity", entity);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * 列表查询-我的持仓
     * @param date
     * @return
     */
    public static List<AssetPositionDb> listMyPositionByDate(String date) {
        SqlSession session = sqlSessionFactory.openSession();
        List<AssetPositionDb> rs = null;
        try {
            rs = session.selectList("ttjj.dao.mapper.FupanPositionMapper.listMyPositionByDate", date);
            session.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return rs;
    }
}
