package ttjj.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.db.AssetPositionDb;
import ttjj.dto.RankBizDataDiff;

import java.util.List;

/**
 * @author chenzhilong
 * @date 2021/7/26
 */
public class BizRankDao {
    /**
     * sqlSessionFactory mybatis
     */
    static SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();
    /**
     * 业务排行-插入
     *
     * @param rankBizDataDiffListBiz
     */
    public static int insertDbBiz(List<RankBizDataDiff> rankBizDataDiffListBiz) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        long temp = 0;
        try {
            for (RankBizDataDiff rankBizDataDiff : rankBizDataDiffListBiz) {
//                System.out.println(JSON.toJSONString(rankBizDataDiff));
//                rankBizDataDiff.setRs("");
                rs = session.insert("ttjj.dao.mapper.RandBizEtfMapper.insertRandBizEtf", rankBizDataDiff);
                session.commit();
            }
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * @param rankBizDataDiffListBiz
     * @return
     */
    public static int updateEtfNet(RankBizDataDiff rankBizDataDiffListBiz) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.update("ttjj.dao.mapper.RandBizEtfMapper.updateEtfNet", rankBizDataDiffListBiz);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }

    public static int updateDbEtfNetMaxMinTimeByDate(RankBizDataDiff rankBizDataDiffListBiz) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.update("ttjj.dao.mapper.RandBizEtfMapper.updateDbEtfNetMaxMinTimeByDate", rankBizDataDiffListBiz);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }

    public static int updateDate(RankBizDataDiff entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.update("ttjj.dao.mapper.RandBizEtfMapper.updateDate", entity);
            session.commit();
        } finally {
            session.close();
        }
        return rs;
    }
}
