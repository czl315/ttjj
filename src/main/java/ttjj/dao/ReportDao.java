package ttjj.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.dto.Report;

/**
 * ReportDao简介
 *
 * @author Administrator
 * @date 2021-10-30 17:38
 */
public class ReportDao {
    /**
     * sqlSessionFactory mybatis
     */
    static SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();

    /**
     * 业务排行-插入
     *
     * @param entity
     */
    public static int insert(Report entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.insert("ttjj.dao.mapper.ReportMapper.insert", entity);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * 查询业绩报表是否存在
     * @param condition
     * @return
     */
    public static Report findByCondition(Report condition) {
        SqlSession session = sqlSessionFactory.openSession();
        Report rs = null;
        try {
            rs = session.selectOne("ttjj.dao.mapper.ReportMapper.findByCondition", condition);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }
}
