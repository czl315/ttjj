package ttjj.dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ttjj.db.RankStockCommpanyDb;
import ttjj.db.StockConception;
import ttjj.dto.*;

import java.util.List;

/**
 * 股票概念
 *
 * @author Administrator
 * @date 2023-03-05 11:17
 */
public interface StockConceptionMapper {

    @Insert({"<script>",
            "INSERT INTO `bank19`.`st_conception`(",
            "`code`,`name`,`date`,`conception`,`type_name`",
            ",`f3`",
            ") VALUES (",
            "#{code},#{name},#{date},#{conception},#{type_name}",
            ",#{f3}",
            ");",
            "</script>"})
    void insert(StockConception entity);

}
