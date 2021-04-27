package ttjj.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import ttjj.dto.Fupan;

import java.util.List;

/**
 * @author chenzhilong
 * @date 2021/4/7
 */
@Mapper
@Component
public interface TestMapper {
    @Select("select * from fupan")
    List<Fupan> getAllRupan();


}
