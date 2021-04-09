package ttjj.Dao.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import ttjj.Dao.TradeDao;
import ttjj.Dao.impl.TradeDaoImpl;
import ttjj.dto.Fupan;

import java.util.List;

/**
 * @author chenzhilong
 * @date 2021/4/7
 */
public class TestMybatis {
    @Autowired
    TestMapper testMapper;

//    public static void main(String[] args) {
//        listFupan();
//    }

//    public static List<Fupan> listFupan(){
//        List<Fupan> fupanList =  testMapper.getAllRupan();
//        for (Fupan fupan : fupanList) {
//            int id = fupan.getID();
//            System.out.println(id);
//        }
//        return  fupanList;
//    }


}
