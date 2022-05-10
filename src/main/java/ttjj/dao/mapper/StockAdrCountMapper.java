package ttjj.dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ttjj.db.StockAdrCount;

import java.util.List;

/**
 * StockAdrCountMapper简介
 *
 * @author Administrator
 * @date 2022-05-10 22:11
 */
public interface StockAdrCountMapper {

    @Insert({"<script>",
            "INSERT INTO `stock_adr_count` (",
            "`date`, `type_name`, `f2`, `f3`, `f4`",
            ",`f5`, `f6`, `f7`, `f8`, `f9` ",
            ",`f10`, `f12`, `f14`, `f15`, `f16` ",
            ",`f17`, `f18`, `f20`, `f21`, `order_num` ",
            ",`conception`, `ADR_UP_COUNT_5`, `ADR_UP_COUNT_10`, `ADR_UP_COUNT_20`, `ADR_UP_COUNT_40` ",
            ",`ADR_UP_COUNT_60`, `ADR_UP_COUNT_SUM_60`",
            ") VALUES (",
            "#{date},#{type_name},#{f2},#{f3},#{f4}",
            ",#{f5},#{f6},#{f7},#{f8},#{f9}",
            ",#{f10},#{f12},#{f14},#{f15},#{f16}",
            ",#{f17},#{f18},#{f20},#{f21},#{order_num}",
            ",#{conception},#{ADR_UP_COUNT_5},#{ADR_UP_COUNT_10},#{ADR_UP_COUNT_20},#{ADR_UP_COUNT_40}",
            ",#{ADR_UP_COUNT_60}, #{ADR_UP_COUNT_SUM_60}",
            ");",
            "</script>"})
    int insert(StockAdrCount entity);

    @Update({"<script>",
            "update stock_adr_count",
            "  <set>",
            "    <if test='f2 != null'>f2=#{f2},</if>",
            "    <if test='f3 != null'>f3=#{f3},</if>",
            "    <if test='f4 != null'>f4=#{f4},</if>",
            "    <if test='f5 != null'>f5=#{f5},</if>",
            "    <if test='f6 != null'>f6=#{f6},</if>",
            "    <if test='f7 != null'>f7=#{f7},</if>",
            "    <if test='f8 != null'>f8=#{f8},</if>",
            "    <if test='f9 != null'>f9=#{f9},</if>",
            "    <if test='f10 != null'>f10=#{f10},</if>",
            "    <if test='f15 != null'>f15=#{f15},</if>",
            "    <if test='f16 != null'>f16=#{f16},</if>",
            "    <if test='f17 != null'>f17=#{f17},</if>",
            "    <if test='f18 != null'>f18=#{f18},</if>",
            "    <if test='f20 != null'>f20=#{f20},</if>",
            "    <if test='f21 != null'>f21=#{f21},</if>",
            "    <if test='order_num != null'>order_num=#{order_num},</if>",
            "    <if test='conception != null'>conception=#{conception},</if>",
            "    <if test='ADR_UP_COUNT_5 != null'>ADR_UP_COUNT_5=#{ADR_UP_COUNT_5},</if>",
            "    <if test='ADR_UP_COUNT_10 != null'>ADR_UP_COUNT_10=#{ADR_UP_COUNT_10},</if>",
            "    <if test='ADR_UP_COUNT_20 != null'>ADR_UP_COUNT_20=#{ADR_UP_COUNT_20},</if>",
            "    <if test='ADR_UP_COUNT_40 != null'>ADR_UP_COUNT_40=#{ADR_UP_COUNT_40},</if>",
            "    <if test='ADR_UP_COUNT_60 != null'>ADR_UP_COUNT_60=#{ADR_UP_COUNT_60},</if>",
            "    <if test='ADR_UP_COUNT_SUM_60 != null'>ADR_UP_COUNT_SUM_60=#{ADR_UP_COUNT_SUM_60},</if>",
            "  </set>",
            "where date=#{date} AND f12=#{f12}",
            "</script>"})
    void update(StockAdrCount entity);

    @Select({"<script>",
            "   SELECT ",
            "       * ",
            "   FROM stock_adr_count ",
            "   WHERE 1=1  ",
            "       AND date = #{date}  ",
//            "       AND conception LIKE CONCAT('%',#{conception},'%')",
            "       <if test='f20 != null'> ",
            "       AND f20 >= #{f20} ",
            "       </if> ",
            "       <if test='type_name != null'> ",
            "       AND type_name = #{type_name} ",
            "       </if> ",
            "   ORDER BY f3 DESC ",
            "</script>"})
    List<StockAdrCount> findListByCondition(StockAdrCount condition);

    /**
     *
     * @param condition
     * @return
     */
    @Select("select * from stock_adr_count where f12=#{f12} AND date = #{date}")
    StockAdrCount findByCondition(StockAdrCount condition);

    @Delete({"<script>",
            "DELETE FROM `stock_adr_count` WHERE f12=#{f12} AND date = #{date} LIMIT 1 ",
            "</script>"})
    int deleteByCondition(String date);

}