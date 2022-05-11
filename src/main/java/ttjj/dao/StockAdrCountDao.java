package ttjj.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.db.StockAdrCount;
import ttjj.db.StockAdrCountVo;

import java.util.List;

public class StockAdrCountDao {
    /**
     * sqlSessionFactory mybatis
     */
    static SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();

    /**
     * db-插入
     *
     * @param entity
     */
    public static int insert(StockAdrCount entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.insert("ttjj.dao.mapper.StockAdrCountMapper.insert", entity);
            session.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     *
     * @param condition
     * @return
     */
    public static StockAdrCount findByCondition(StockAdrCount condition) {
        SqlSession session = sqlSessionFactory.openSession();
        StockAdrCount rs = null;
        try {
            rs = session.selectOne("ttjj.dao.mapper.StockAdrCountMapper.findByCondition", condition);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * 查询列表-根据条件
     * @param condition
     * @return
     */
    public static List<StockAdrCount> findListByCondition(StockAdrCountVo condition) {
        SqlSession session = sqlSessionFactory.openSession();
        List<StockAdrCount> rs = null;
        try {
            rs = session.selectList("ttjj.dao.mapper.StockAdrCountMapper.findListByCondition", condition);
            session.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * 删除-根据
     * @param condition
     * @return
     */
    public static int deleteByDateAndCode(StockAdrCount condition) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.delete("ttjj.dao.mapper.StockAdrCountMapper.deleteByCondition", condition);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rs;
    }

}
