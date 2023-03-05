package ttjj.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import ttjj.db.StockConception;

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
