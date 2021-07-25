package ttjj.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import ttjj.db.AssetPositionDb;

/**
 * 复盘-我的持仓明细
 */
public interface FupanPositionMapper {
    @Insert({"<script>",
            "INSERT INTO `bank19`.`fupan_position`(",
            " `date`,`period`, `assetPosition`, `fupan`, `Bz`, ",
            " `Cbjg`, `Cbjgex`, `Ckcb`, `Ckcbj`, `Ckyk`,",
            " `Cwbl`, `Djsl`, `Dqcb`, `Dryk`, `Drykbl`, ",
            " `Gddm`, `Gfmcdj`, `Gfmrjd`,`Gfssmmce`,  `Gfye`,",
            " `Jgbm`, `Khdm`, `Ksssl`,`Kysl`,  `Ljyk`,",
            " `Market`, `Mrssc`, `Sssl`,`Szjsbs`,  `Ykbl`,",
            " `Zjzh`, `Zqdm`, `Zqlx`,`Zqlxmc`,  `Zqmc`,",
            " `Zqsl`, `Ztmc`, `Ztmr`,`Zxjg`,  `Zxsz`,",
            " `CREATE_TIME`, `UPDATE_TIME`",
            ") VALUES (",
            " #{date},#{period}, #{assetPosition}, #{fupan}, #{Bz}, ",
            " #{Cbjg}, #{Cbjgex}, #{Ckcb}, #{Ckcbj}, #{Ckyk},",
            " #{Cwbl}, #{Djsl}, #{Dqcb}, #{Dryk}, #{Drykbl}, ",
            " #{Gddm}, #{Gfmcdj}, #{Gfmrjd},#{Gfssmmce},  #{Gfye},",
            " #{Jgbm}, #{Khdm}, #{Ksssl},#{Kysl},  #{Ljyk},",
            " #{Market}, #{Mrssc}, #{Sssl},#{Szjsbs},  #{Ykbl},",
            " #{Zjzh}, #{Zqdm}, #{Zqlx},#{Zqlxmc},  #{Zqmc},",
            " #{Zqsl}, #{Ztmc}, #{Ztmr},#{Zxjg},  #{Zxsz},",
            " #{CREATE_TIME}, #{UPDATE_TIME}",
            ");",
            "</script>"})
    void insert(AssetPositionDb entity);

    /**
     * @param condition
     * @return
     */
    @Select("select * from fupan where fupan.date=#{date} AND fupan.period=#{period}")
    public AssetPositionDb findDbByDate(AssetPositionDb condition);

}
