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
     * @param entity
     */
    @Insert({"<script>",
            "INSERT INTO `bank19`.`kline`(",
            " `ktime`,`zqdm`, `zqmc`, `klt`, `rs`, ",
            " `openAmt`,`closeAmt`, `closeLastAmt`, `maxAmt`, `minAmt`, ",
            " `cjl`,`cje`, `zhenFu`, `zhangDieFu`, `zhangDieE`, ",
            " `NET_MIN_5`,`NET_MIN_10`, `NET_MIN_20`, `NET_MIN_30`, `NET_MIN_60`,`NET_MIN_120`,`NET_MIN_250`, ",
            " `NET_MAX_5`,`NET_MAX_10`, `NET_MAX_20`, `NET_MAX_30`, `NET_MAX_60`,`NET_MAX_120`,`NET_MAX_250`, ",
            " `NET_MIN_CLOS_5`,`NET_MIN_CLOS_10`, `NET_MIN_CLOS_20`, `NET_MIN_CLOS_30`, `NET_MIN_CLOS_60`,`NET_MIN_CLOS_120`,`NET_MIN_CLOS_250`, ",
            " `NET_MAX_CLOS_5`,`NET_MAX_CLOS_10`, `NET_MAX_CLOS_20`, `NET_MAX_CLOS_30`, `NET_MAX_CLOS_60`,`NET_MAX_CLOS_120`,`NET_MAX_CLOS_250`, ",
            " `NET_MA_5`,`NET_MA_10`, `NET_MA_20`, `NET_MA_30`, `NET_MA_60`,`NET_MA_120`,`NET_MA_250`, ",
            " `month`,`weekYear`,`week`,",
            " `huanShouLv` ",
            ") VALUES (",
            " #{ktime},#{zqdm}, #{zqmc}, #{klt}, #{rs}, ",
            " #{openAmt},#{closeAmt}, #{closeLastAmt}, #{maxAmt}, #{minAmt}, ",
            " #{cjl},#{cje}, #{zhenFu}, #{zhangDieFu}, #{zhangDieE}, ",
            " #{NET_MIN_5},#{NET_MIN_10}, #{NET_MIN_20}, #{NET_MIN_30}, #{NET_MIN_60},#{NET_MIN_120},#{NET_MIN_250}, ",
            " #{NET_MAX_5},#{NET_MAX_10}, #{NET_MAX_20}, #{NET_MAX_30}, #{NET_MAX_60},#{NET_MAX_120},#{NET_MAX_250}, ",
            " #{NET_MIN_CLOS_5},#{NET_MIN_CLOS_10}, #{NET_MIN_CLOS_20}, #{NET_MIN_CLOS_30}, #{NET_MIN_CLOS_60},#{NET_MIN_CLOS_120},#{NET_MIN_CLOS_250}, ",
            " #{NET_MAX_CLOS_5},#{NET_MAX_CLOS_10}, #{NET_MAX_CLOS_20}, #{NET_MAX_CLOS_30}, #{NET_MAX_CLOS_60},#{NET_MAX_CLOS_120},#{NET_MAX_CLOS_250}, ",
            " #{NET_MA_5},#{NET_MA_10}, #{NET_MA_20}, #{NET_MA_30}, #{NET_MA_60},#{NET_MA_120},#{NET_MA_250}, ",
            " #{month},#{weekYear},#{week},",
            " #{huanShouLv} ",
            ");",
            "</script>"})
    void insert(Kline entity);

    @Update({"<script>",
            "update kline",
            "  <set>",
            "    <if test='NET_MIN_5 != null'>NET_MIN_5=#{NET_MIN_5},</if>",
            "    <if test='NET_MIN_CLOS_5 != null'>NET_MIN_CLOS_5=#{NET_MIN_CLOS_5},</if>",
            "    <if test='NET_MAX_5 != null'>NET_MAX_5=#{NET_MAX_5},</if>",
            "    <if test='NET_MAX_CLOS_5 != null'>NET_MAX_CLOS_5=#{NET_MAX_CLOS_5},</if>",
            "    <if test='NET_MIN_10 != null'>NET_MIN_10=#{NET_MIN_10},</if>",
            "    <if test='NET_MIN_CLOS_10 != null'>NET_MIN_CLOS_10=#{NET_MIN_CLOS_10},</if>",
            "    <if test='NET_MAX_10 != null'>NET_MAX_10=#{NET_MAX_10},</if>",
            "    <if test='NET_MAX_CLOS_10 != null'>NET_MAX_CLOS_10=#{NET_MAX_CLOS_10},</if>",
            "    <if test='NET_MIN_20 != null'>NET_MIN_20=#{NET_MIN_20},</if>",
            "    <if test='NET_MIN_CLOS_20 != null'>NET_MIN_CLOS_20=#{NET_MIN_CLOS_20},</if>",
            "    <if test='NET_MAX_20 != null'>NET_MAX_20=#{NET_MAX_20},</if>",
            "    <if test='NET_MAX_CLOS_20 != null'>NET_MAX_CLOS_20=#{NET_MAX_CLOS_20},</if>",
            "    <if test='NET_MIN_30 != null'>NET_MIN_30=#{NET_MIN_30},</if>",
            "    <if test='NET_MIN_CLOS_30 != null'>NET_MIN_CLOS_30=#{NET_MIN_CLOS_30},</if>",
            "    <if test='NET_MAX_30 != null'>NET_MAX_30=#{NET_MAX_30},</if>",
            "    <if test='NET_MAX_CLOS_30 != null'>NET_MAX_CLOS_30=#{NET_MAX_CLOS_30},</if>",
            "    <if test='NET_MIN_60 != null'>NET_MIN_60=#{NET_MIN_60},</if>",
            "    <if test='NET_MIN_CLOS_60 != null'>NET_MIN_CLOS_60=#{NET_MIN_CLOS_60},</if>",
            "    <if test='NET_MAX_60 != null'>NET_MAX_60=#{NET_MAX_60},</if>",
            "    <if test='NET_MAX_CLOS_60 != null'>NET_MAX_CLOS_60=#{NET_MAX_CLOS_60},</if>",
            "    <if test='NET_MIN_120 != null'>NET_MIN_120=#{NET_MIN_120},</if>",
            "    <if test='NET_MIN_CLOS_120 != null'>NET_MIN_CLOS_120=#{NET_MIN_CLOS_120},</if>",
            "    <if test='NET_MAX_120 != null'>NET_MAX_120=#{NET_MAX_120},</if>",
            "    <if test='NET_MAX_CLOS_120 != null'>NET_MAX_CLOS_120=#{NET_MAX_CLOS_120},</if>",
            "    <if test='NET_MIN_250 != null'>NET_MIN_250=#{NET_MIN_250},</if>",
            "    <if test='NET_MIN_CLOS_250 != null'>NET_MIN_CLOS_250=#{NET_MIN_CLOS_250},</if>",
            "    <if test='NET_MAX_250 != null'>NET_MAX_250=#{NET_MAX_250},</if>",
            "    <if test='NET_MAX_CLOS_250 != null'>NET_MAX_CLOS_250=#{NET_MAX_CLOS_250},</if>",
            "    <if test='NET_MA_5 != null'>NET_MA_5=#{NET_MA_5},</if>",
            "    <if test='NET_MA_10 != null'>NET_MA_10=#{NET_MA_10},</if>",
            "    <if test='NET_MA_20 != null'>NET_MA_20=#{NET_MA_20},</if>",
            "    <if test='NET_MA_30 != null'>NET_MA_30=#{NET_MA_30},</if>",
            "    <if test='NET_MA_60 != null'>NET_MA_60=#{NET_MA_60},</if>",
            "    <if test='NET_MA_120 != null'>NET_MA_120=#{NET_MA_120},</if>",
            "    <if test='NET_MA_250 != null'>NET_MA_250=#{NET_MA_250},</if>",
            "  </set>",
            "where ktime=#{ktime} AND zqdm=#{zqdm} AND klt=#{klt}",
            "</script>"})
    void updateNet(Kline entity);

}
