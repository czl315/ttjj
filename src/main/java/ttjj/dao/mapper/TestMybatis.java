package ttjj.dao.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ttjj.dto.Fupan;

import javax.activation.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author chenzhilong
 * @date 2021/4/7
 */
public class TestMybatis {
    @Autowired
    static TestMapper testMapper;

    public static void main(String[] args) throws IOException {
//        listFupan();
//        ApplicationContext context=new ClassPathXmlApplicationContext("spring-config-imp.xml");
//        BeanFactory factory = (BeanFactory) context;
//        TestMapper oneMapper=factory.getBean(TestMapper.class);
//        List<Fupan> fupanList =  oneMapper.getAllRupan();
//        for (Fupan fupan : fupanList) {
//            int id = fupan.getID();
//            System.out.println(id);
//        }

        String resource = "spring-config-imp.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            System.out.println("start...");
//            session.selectOne("select * from fupan where id =210444",210444);
//            session.insert("INSERT INTO `fupan` (`CODE`) VALUES ('test');");
//            Fupan entity = (Fupan) session.selectOne("ttjj.dao.mapper.TestMapper.getAllRupan", 210444);
//            System.out.println(entity.getID());
//            Integer count = session.selectOne("ttjj.dao.mapper.FupanMapper.selectCount");
//            System.out.println(count);


            List<Fupan> fupanList =  session.selectList("ttjj.dao.mapper.TestMapper.getAllRupan");
            for (Fupan fupan : fupanList) {
                int id = fupan.getID();
                String code = fupan.getCode();
                System.out.println(id+",code:"+code);
            }

        }
    }

    public static List<Fupan> listFupan(){
        List<Fupan> fupanList =  testMapper.getAllRupan();
        for (Fupan fupan : fupanList) {
            int id = fupan.getID();
            System.out.println(id);
        }
        return  fupanList;
    }


}
