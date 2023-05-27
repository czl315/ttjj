package ttjj.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.db.CarIncome;
import ttjj.dto.Kline;
import ttjj.dto.StatCondStAdrCountKline;
import ttjj.dto.StatRsStAdrCountKline;

import java.util.List;

public class CarIncomeDao {
    /**
     * sqlSessionFactory mybatis
     */
    static SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();

    /**
     * 业务排行-插入
     *
     * @param entity
     */
    public static int insert(CarIncome entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.insert("ttjj.dao.mapper.CarIncomeMapper.insert", entity);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }

}
