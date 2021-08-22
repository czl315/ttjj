package ttjj.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ttjj.db.AssetPositionDb;
import ttjj.dto.Kline;

/**
 * 复盘-我的持仓明细
 */
public interface KlineMapper {
    /**
     *
     * @param entity
     */
    @Insert({"<script>",
            "INSERT INTO `bank19`.`kline`(",
            " `ktime`,`zqdm`, `zqmc`, `klt`, `rs`, ",
            " `openAmt`,`closeAmt`, `closeLastAmt`, `maxAmt`, `minAmt`, ",
            " `cjl`,`cje`, `zhenFu`, `zhangDieFu`, `zhangDieE`, ",
            " `huanShouLv` ",
            ") VALUES (",
            " #{ktime},#{zqdm}, #{zqmc}, #{klt}, #{rs}, ",
            " #{openAmt},#{closeAmt}, #{closeLastAmt}, #{maxAmt}, #{minAmt}, ",
            " #{cjl},#{cje}, #{zhenFu}, #{zhangDieFu}, #{zhangDieE}, ",
            " #{huanShouLv} ",
            ");",
            "</script>"})
    void insert(Kline entity);

}
