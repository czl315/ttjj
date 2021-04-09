package ttjj.Dao.mapper;

import org.apache.ibatis.annotations.Select;
import ttjj.dto.Fupan;

import java.util.List;

/**
 * @author chenzhilong
 * @date 2021/4/7
 */
public interface TestMapper {
    @Select("select * from fupan")
    List<Fupan> getAllRupan();

}
