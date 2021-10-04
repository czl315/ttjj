package ttjj.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.MaBreakUpCond;
import ttjj.dto.MaBreakUpRs;
import ttjj.dto.RankStComTjCond;
import ttjj.dto.RankStComTjRs;

import java.util.List;

/**
 * @author chenzhilong
 * @date 2021/7/26
 */
public class RankStockCommpanyDao {
    /**
     * sqlSessionFactory mybatis
     */
    static SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();

    public static List<RankStComTjRs> findListTongjiGroup(RankStComTjCond condition) {
        SqlSession session = sqlSessionFactory.openSession();
        List<RankStComTjRs> rs = null;
        try {
//                System.out.println(JSON.toJSONString(entity));
            rs = session.selectList("ttjj.dao.mapper.RankStockCommpanyMapper.findListTongjiGroup", condition);
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

    public static int updateDate(RankStockCommpanyDb entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.update("ttjj.dao.mapper.RankStockCommpanyMapper.updateDate", entity);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * 均线突破
     * @param condition
     * @return
     */
    public static List<MaBreakUpRs> findListMaBreakUpCond(MaBreakUpCond condition) {
        SqlSession session = sqlSessionFactory.openSession();
        List<MaBreakUpRs> rs = null;
        try {
//                System.out.println(JSON.toJSONString(entity));
            rs = session.selectList("ttjj.dao.mapper.RankStockCommpanyMapper.findListMaBreakUpCond", condition);
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
