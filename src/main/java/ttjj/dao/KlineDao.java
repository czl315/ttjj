package ttjj.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.dto.Kline;

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
}
