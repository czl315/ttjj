package ttjj.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import ttjj.db.FundRank;
import ttjj.db.RankStockCommpanyDb;

/**
 * @author chenzhilong
 * @date 2021/4/7
 */
public interface RankFundMapper {
    @Insert({"<script>",
            "INSERT INTO `rank_fund` (`num`, `fundCode`, `fundInfo`, `bizTy`, `periodDate`, ",
            "`periodTy`,`net`, ",
            "`growth1`, `growth7`, `growth30`, `growth90`, `growth180`, ",
            "`growth360`, `growth720`, `growth1080`, `growthCurYear`, `growthEstablish`",
            ")VALUES (",
            "#{num},#{fundCode},#{fundInfo},#{bizTy},#{periodDate},",
            "#{periodTy},#{net},",
            "#{growth1}, #{growth7}, #{growth30}, #{growth90}, #{growth180},",
            "#{growth360}, #{growth720}, #{growth1080}, #{growthCurYear}, #{growthEstablish}",
            ");",
            "</script>"})
    void insert(FundRank entity);

}
