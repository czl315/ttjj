package ttjj.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.db.Fupan;

import java.io.IOException;

/**
 * @author chenzhilong
 * @date 2021/4/7
 */
public class TestMybatis {

    /**
     * sqlSessionFactory mybatis
     */
    static SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();

    public static void main(String[] args) throws IOException {
        Fupan fupan = new Fupan();
        //set value
        fupan.setRtHs300("5.0");
        //where
        fupan.setCode("2021-04-27");
        fupan.setPeriod("1");
        fupan.setType("1");
        int rs = updateFupan(fupan);
        System.out.println("rs:" + rs);
    }

    private static int updateFupan(Fupan fupan) {
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

    private static void getUserByID(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
//            List<Fupan> fupanList =  session.selectList("ttjj.dao.mapper.FupanMapper.getAllRupan");
//            for (Fupan fupan : fupanList) {
//                int id = fupan.getID();
//                String code = fupan.getCode();
//                System.out.println(id+",code:"+code);
//            }

            Fupan fupan = session.selectOne("ttjj.dao.mapper.FupanMapper.getUserByID", 1);
            String code = fupan.getCode();
            System.out.println(id + ",code:" + code);

        } finally {
            session.close();
        }
    }

}
