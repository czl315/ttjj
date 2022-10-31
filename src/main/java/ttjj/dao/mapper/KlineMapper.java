package ttjj.dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ttjj.dto.CondKline;
import ttjj.dto.Kline;
import ttjj.dto.StatCondStAdrCountKline;
import ttjj.dto.StatRsStAdrCountKline;

import java.util.List;

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
            " `month`,`weekYear`,`week`",
            ",`date`,`type`",
            ",`huanShouLv` ",
            ") VALUES (",
            " #{ktime},#{zqdm}, #{zqmc}, #{klt}, #{rs}, ",
            " #{openAmt},#{closeAmt}, #{closeLastAmt}, #{maxAmt}, #{minAmt}, ",
            " #{cjl},#{cje}, #{zhenFu}, #{zhangDieFu}, #{zhangDieE}, ",
            " #{NET_MIN_5},#{NET_MIN_10}, #{NET_MIN_20}, #{NET_MIN_30}, #{NET_MIN_60},#{NET_MIN_120},#{NET_MIN_250}, ",
            " #{NET_MAX_5},#{NET_MAX_10}, #{NET_MAX_20}, #{NET_MAX_30}, #{NET_MAX_60},#{NET_MAX_120},#{NET_MAX_250}, ",
            " #{NET_MIN_CLOS_5},#{NET_MIN_CLOS_10}, #{NET_MIN_CLOS_20}, #{NET_MIN_CLOS_30}, #{NET_MIN_CLOS_60},#{NET_MIN_CLOS_120},#{NET_MIN_CLOS_250}, ",
            " #{NET_MAX_CLOS_5},#{NET_MAX_CLOS_10}, #{NET_MAX_CLOS_20}, #{NET_MAX_CLOS_30}, #{NET_MAX_CLOS_60},#{NET_MAX_CLOS_120},#{NET_MAX_CLOS_250}, ",
            " #{NET_MA_5},#{NET_MA_10}, #{NET_MA_20}, #{NET_MA_30}, #{NET_MA_60},#{NET_MA_120},#{NET_MA_250}, ",
            " #{month},#{weekYear},#{week}",
            ",#{date},#{type}",
            ",#{huanShouLv} ",
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
            "    <if test='NET_MA_250 != null'>NET_MA_250=#{NET_MA_250},</if>",
            "    <if test='flowInMain != null'>flowInMain=#{flowInMain},</if>",
            "    <if test='flowInSmall != null'>flowInSmall=#{flowInSmall},</if>",
            "    <if test='flowInMid != null'>flowInMid=#{flowInMid},</if>",
            "    <if test='flowInBig != null'>flowInBig=#{flowInBig},</if>",
            "    <if test='flowInSuperBig != null'>flowInSuperBig=#{flowInSuperBig},</if>",
            "    <if test='f20 != null'>f20=#{f20},</if>",
            "  </set>",
            "where 1=1  ",
            "   AND date = #{date}  ",
            "   AND zqdm=#{zqdm} ",
            "   <if test='klt != null'> ",
            "       AND klt=#{klt} ",
            "   </if> ",
            "   <if test='type != null'> AND type=#{type}</if> ",
            "   <if test='ktime != null'> AND ktime=#{ktime}</if> ",
            "</script>"})
    void updateNet(Kline entity);

    @Delete({"<script>",
            "DELETE FROM `kline` WHERE 1=1 ",
            "   AND date = #{date}  ",
            "   <if test='type != null'> AND type=#{type}</if> ",
            "   <if test='zqdm != null'> AND zqdm=#{zqdm}</if> ",
            "   <if test='klt != null'> AND klt=#{klt}</if> ",
            " LIMIT 100 ",
            "</script>"})
    int deleteByCondition(Kline condition);

    /**
     * 查询-涨跌次数
     *
     * @param condition
     * @return
     */
    @Select({"<script>",
            " SELECT",
            "   kline.zqdm code ",
            "   ,kline.zqmc name ",
            "   ,SUBSTR(kline.ktime FROM 9 FOR 2) rsDate ",
            "   ,COUNT(1) count ",
            "   ,ROUND(SUM(kline.zhangDieFu),2) adrSum",
            " FROM ",
            "   `kline` kline ",
            " WHERE 1=1 ",
            "   <if test='klt != null'> ",
            "   AND kline.klt = #{klt} ",
            "   </if> ",
            "   <if test='adrMin != null'> ",
            "   AND kline.zhangDieFu > #{adrMin} ",
            "   </if> ",
            "       <if test='begDate != null'> ",
            "       <![CDATA[ AND kline.ktime >= #{begDate} ]]> ",
            "       </if> ",
            "       <if test='endDate != null'> ",
            "       <![CDATA[ AND kline.ktime <= #{endDate} ]]> ",
            "       </if> ",
            "   AND kline.zqmc IN  ",
            "   <foreach collection='stCodeList' item='item' open='(' separator=',' close=')'>  ",
            "       #{item} ",
            "   </foreach> ",
            " GROUP BY SUBSTR(kline.ktime FROM 9 FOR 2) ",
            " ORDER BY ",
            "   SUBSTR(kline.ktime FROM 9 FOR 2) ",
            "</script>"})
    List<StatRsStAdrCountKline> findListStatStAdrCount(StatCondStAdrCountKline condition);

    @Select({"<script>",
            " SELECT",
            "   * ",
            " FROM ",
            "   `kline` kline ",
            " WHERE 1=1 ",
            "   <if test='zqdm != null'> ",
            "       AND kline.zqdm = #{zqdm} ",
            "   </if> ",
            "   <if test='klt != null'> ",
            "       AND kline.klt = #{klt} ",
            "   </if> ",
            "   <if test='type != null'> ",
            "       AND kline.type = #{type} ",
            "   </if> ",
            "   <if test='date != null'> ",
            "       <![CDATA[ AND kline.date = #{date} ]]> ",
            "   </if> ",
            "   <if test='ktime != null'> ",
            "       <![CDATA[ AND kline.ktime = #{ktime} ]]> ",
            "   </if> ",
            "   <if test='stCodeList != null'> ",
            "   AND kline.zqdm IN  ",
            "   <foreach collection='stCodeList' item='item' open='(' separator=',' close=')'>  ",
            "       #{item} ",
            "   </foreach> ",
            "   </if> ",
            " ORDER BY ",
            "   kline.zhangDieFu DESC  ",
            "</script>"})
    List<Kline> listKline(CondKline condition);


}
