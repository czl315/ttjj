package ttjj.dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ttjj.db.AssetPositionDb;

import java.util.List;

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
            " `week`,",
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
            " #{week},",
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

    @Update({"<script>",
            "update fupan_position",
            "  <set>",
            "    <if test='openAmt != null'>openAmt=#{openAmt},</if>",
            "    <if test='closeAmt != null'>closeAmt=#{closeAmt},</if>",
            "    <if test='closeLastAmt != null'>closeLastAmt=#{closeLastAmt},</if>",
            "    <if test='maxAmt != null'>maxAmt=#{maxAmt},</if>",
            "    <if test='minAmt != null'>minAmt=#{minAmt},</if>",
            "    <if test='cjl != null'>cjl=#{cjl},</if>",
            "    <if test='cje != null'>cje=#{cje},</if>",
            "    <if test='zhenFu != null'>zhenFu=#{zhenFu},</if>",
            "    <if test='zhangDieFu != null'>zhangDieFu=#{zhangDieFu},</if>",
            "    <if test='zhangDieE != null'>zhangDieE=#{zhangDieE},</if>",
            "    <if test='huanShouLv != null'>huanShouLv=#{huanShouLv},</if>",
            "    <if test='LAST_NET != null'>LAST_NET=#{LAST_NET},</if>",
            "    <if test='NET_MIN_1 != null'>NET_MIN_1=#{NET_MIN_1},</if>",
            "    <if test='NET_MIN_CLOS_1 != null'>NET_MIN_CLOS_1=#{NET_MIN_CLOS_1},</if>",
            "    <if test='NET_MAX_1 != null'>NET_MAX_1=#{NET_MAX_1},</if>",
            "    <if test='NET_MAX_CLOS_1 != null'>NET_MAX_CLOS_1=#{NET_MAX_CLOS_1},</if>",
            "    <if test='NET_MIN_7 != null'>NET_MIN_7=#{NET_MIN_7},</if>",
            "    <if test='NET_MIN_CLOS_7 != null'>NET_MIN_CLOS_7=#{NET_MIN_CLOS_7},</if>",
            "    <if test='NET_MAX_7 != null'>NET_MAX_7=#{NET_MAX_7},</if>",
            "    <if test='NET_MAX_CLOS_7 != null'>NET_MAX_CLOS_7=#{NET_MAX_CLOS_7},</if>",
            "    <if test='NET_MIN_14 != null'>NET_MIN_14=#{NET_MIN_14},</if>",
            "    <if test='NET_MIN_CLOS_14 != null'>NET_MIN_CLOS_14=#{NET_MIN_CLOS_14},</if>",
            "    <if test='NET_MAX_14 != null'>NET_MAX_14=#{NET_MAX_14},</if>",
            "    <if test='NET_MAX_CLOS_14 != null'>NET_MAX_CLOS_14=#{NET_MAX_CLOS_14},</if>",
            "    <if test='NET_MIN_30 != null'>NET_MIN_30=#{NET_MIN_30},</if>",
            "    <if test='NET_MIN_CLOS_30 != null'>NET_MIN_CLOS_30=#{NET_MIN_CLOS_30},</if>",
            "    <if test='NET_MAX_30 != null'>NET_MAX_30=#{NET_MAX_30},</if>",
            "    <if test='NET_MAX_CLOS_30 != null'>NET_MAX_CLOS_30=#{NET_MAX_CLOS_30},</if>",
            "    <if test='NET_MIN_60 != null'>NET_MIN_60=#{NET_MIN_60},</if>",
            "    <if test='NET_MIN_CLOS_60 != null'>NET_MIN_CLOS_60=#{NET_MIN_CLOS_60},</if>",
            "    <if test='NET_MAX_60 != null'>NET_MAX_60=#{NET_MAX_60},</if>",
            "    <if test='NET_MAX_CLOS_60 != null'>NET_MAX_CLOS_60=#{NET_MAX_CLOS_60},</if>",
            "    <if test='NET_MIN_90 != null'>NET_MIN_90=#{NET_MIN_90},</if>",
            "    <if test='NET_MIN_CLOS_90 != null'>NET_MIN_CLOS_90=#{NET_MIN_CLOS_90},</if>",
            "    <if test='NET_MAX_90 != null'>NET_MAX_90=#{NET_MAX_90},</if>",
            "    <if test='NET_MAX_CLOS_90 != null'>NET_MAX_CLOS_90=#{NET_MAX_CLOS_90},</if>",
            "    <if test='NET_MIN_180 != null'>NET_MIN_180=#{NET_MIN_180},</if>",
            "    <if test='NET_MIN_CLOS_180 != null'>NET_MIN_CLOS_180=#{NET_MIN_CLOS_180},</if>",
            "    <if test='NET_MAX_180 != null'>NET_MAX_180=#{NET_MAX_180},</if>",
            "    <if test='NET_MAX_CLOS_180 != null'>NET_MAX_CLOS_180=#{NET_MAX_CLOS_180},</if>",
            "    <if test='NET_MIN_360 != null'>NET_MIN_360=#{NET_MIN_360},</if>",
            "    <if test='NET_MIN_CLOS_360 != null'>NET_MIN_CLOS_360=#{NET_MIN_CLOS_360},</if>",
            "    <if test='NET_MAX_360 != null'>NET_MAX_360=#{NET_MAX_360},</if>",
            "    <if test='NET_MAX_CLOS_360 != null'>NET_MAX_CLOS_360=#{NET_MAX_CLOS_360},</if>",
            "    <if test='NET_MA_5 != null'>NET_MA_5=#{NET_MA_5},</if>",
            "    <if test='NET_MA_10 != null'>NET_MA_10=#{NET_MA_10},</if>",
            "    <if test='NET_MA_20 != null'>NET_MA_20=#{NET_MA_20},</if>",
            "    <if test='NET_MA_30 != null'>NET_MA_30=#{NET_MA_30},</if>",
            "    <if test='NET_MA_60 != null'>NET_MA_60=#{NET_MA_60},</if>",
            "    <if test='NET_MA_120 != null'>NET_MA_120=#{NET_MA_120},</if>",
            "    <if test='NET_MA_250 != null'>NET_MA_250=#{NET_MA_250},</if>",
            "  </set>",
            "where date=#{date} AND Zqdm=#{Zqdm}",
//            "where date=#{date} AND Zqdm=#{Zqdm} limit 1",
            "</script>"})
    void updateEntity(AssetPositionDb entity);

    @Select({"<script>",
            "   SELECT * ",
            "   FROM fupan_position ",
            "   WHERE 1=1  ",
            "       AND fupan_position.date=#{date} ",
            "</script>"})
    List<AssetPositionDb> listMyPositionByDate(String date);

    @Delete({"<script>",
            "DELETE FROM `fupan_position` WHERE 1=1 ",
            "   AND date = #{date}  ",
            "   <if test='zqdm != null'> AND zqdm=#{zqdm}</if> ",
            " LIMIT 1 ",
            "</script>"})
    int delete(AssetPositionDb condition);
}
