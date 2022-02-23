package ttjj.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.*;

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
    static boolean showTime = false;//显示花费时间;

    /**
     * 查询股票-从数据库中-最新的一条
     *
     * @param condition
     * @return
     */
    public static RankStockCommpanyDb findStockLast(RankStockCommpanyDb condition) {
        SqlSession session = sqlSessionFactory.openSession();
        RankStockCommpanyDb rs = null;
        try {
            rs = session.selectOne("ttjj.dao.mapper.RankStockCommpanyMapper.findStockLast", condition);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * 查询-统计数据-股票分组
     *
     * @param condition
     * @return
     */
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
     *
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

    /**
     * 超跌反弹
     *
     * @param condition
     * @return
     */
    public static List<SuperDropBounceRs> findListSuperDropBounce(SuperDropBounceCond condition) {
        SqlSession session = sqlSessionFactory.openSession();
        List<SuperDropBounceRs> rs = null;
        try {
//                System.out.println(JSON.toJSONString(entity));
            rs = session.selectList("ttjj.dao.mapper.RankStockCommpanyMapper.findListSuperDropBounce", condition);
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
     * 查询指定日期之前交易日期列表
     *
     * @param dateCond
     * @return
     */
    public static List<String> findListDateBefore(DateCond dateCond) {
        SqlSession session = sqlSessionFactory.openSession();
        List<String> rs = null;
        try {
            rs = session.selectList("ttjj.dao.mapper.RankStockCommpanyMapper.findListDateBefore", dateCond);
            session.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * 查询指定日期之后交易日期列表
     *
     * @param dateCond
     * @return
     */
    public static List<String> findListDateAfter(DateCond dateCond) {
        SqlSession session = sqlSessionFactory.openSession();
        List<String> rs = null;
        try {
            rs = session.selectList("ttjj.dao.mapper.RankStockCommpanyMapper.findListDateAfter", dateCond);
            session.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * 更新业务板块-根据code
     *
     * @param entity
     * @return
     */
    public static int updateBizByCode(RankStockCommpanyDb entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.update("ttjj.dao.mapper.RankStockCommpanyMapper.updateBizByCode", entity);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * 删除数据-根据日期
     *
     * @param date
     * @return
     */
    public static int deleteStComByDate(String date) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.delete("ttjj.dao.mapper.RankStockCommpanyMapper.deleteStComByDate", date);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * 查询-股票涨跌次数
     *
     * @param condition
     * @return
     */
    public static List<StatRsStAdrCount> findListStatStAdrCount(StatCondStAdrCount condition) {
        long timeBeg = 0;
        if (showTime) {
            timeBeg = System.currentTimeMillis();
        }
        SqlSession session = sqlSessionFactory.openSession();
        List<StatRsStAdrCount> rs = null;
        try {
//                System.out.println(JSON.toJSONString(condition));
            rs = session.selectList("ttjj.dao.mapper.RankStockCommpanyMapper.findListStatStAdrCount", condition);
            session.commit();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        if (showTime) {
            long timeEnd = System.currentTimeMillis();
            System.out.println("time-findListStatStAdrCount():" + (timeEnd - timeBeg));
        }
        return rs;
    }

    /**
     * 查询列表-模糊匹配-概念
     *
     * @param condition
     * @return
     */
    public static List<RankStockCommpanyDb> findListLikeConception(CondStLikeConception condition) {
        long timeBeg = 0;
        if (showTime) {
            timeBeg = System.currentTimeMillis();
        }
        SqlSession session = sqlSessionFactory.openSession();
        List<RankStockCommpanyDb> rs = null;
        try {

//                System.out.println(JSON.toJSONString(condition));
            rs = session.selectList("ttjj.dao.mapper.RankStockCommpanyMapper.findListLikeConception", condition);
            session.commit();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        if (showTime) {
            long timeEnd = System.currentTimeMillis();
            System.out.println("time-findListLikeConception():" + (timeEnd - timeBeg));
        }
        return rs;
    }
}
