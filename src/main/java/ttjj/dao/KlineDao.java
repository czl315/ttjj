package ttjj.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.dto.*;

import java.util.List;

/**
 * @author chenzhilong
 * @date 2021/7/26
 */
public class KlineDao {
    /**
     * sqlSessionFactory mybatis
     */
    static SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();

    /**
     * 业务排行-插入
     *
     * @param entity
     */
    public static int insert(Kline entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.insert("ttjj.dao.mapper.KlineMapper.insert", entity);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * 更新净值
     * @param entity
     * @return
     */
    public static int updateNet(Kline entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.insert("ttjj.dao.mapper.KlineMapper.updateNet", entity);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * 查询-涨跌次数
     *
     * @param condition
     * @return
     */
    public static List<StatRsStAdrCountKline> findListStatStAdrCount(StatCondStAdrCountKline condition) {
        SqlSession session = sqlSessionFactory.openSession();
        List<StatRsStAdrCountKline> rs = null;
        try {
//                System.out.println(JSON.toJSONString(condition));
            rs = session.selectList("ttjj.dao.mapper.KlineMapper.findListStatStAdrCount", condition);
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
