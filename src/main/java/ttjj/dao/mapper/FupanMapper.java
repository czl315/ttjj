package ttjj.dao.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ttjj.db.Fupan;

import java.util.List;

/**
 * @author chenzhilong
 * @date 2021/4/7
 */
public interface FupanMapper {
    @Select("select * from fupan")
    List<Fupan> getAllRupan();

    @Select("select * from fupan where id= #{id}")
    public Fupan getUserByID(int id);

    @Update({"<script>",
            "update fupan",
            "  <set>",
            "    <if test='rt_hs300 != null'>rt_hs300=#{rt_hs300},</if>",
            "    <if test='rt_cyb50 != null'>rt_cyb50=#{rt_cyb50},</if>",
            "    <if test='rt_sh50 != null'>rt_sh50=#{rt_sh50},</if>",
            "    <if test='rt_sz != null'>rt_sz=#{rt_sz},</if>",
            "    <if test='rt_cyb != null'>rt_cyb=#{rt_cyb},</if>",
            "    <if test='rt_sh != null'>rt_sh=#{rt_sh},</if>",
            "    <if test='rt_zz500 != null'>rt_zz500=#{rt_zz500},</if>",
            "    <if test='rt_biz_qs != null'>rt_biz_qs=#{rt_biz_qs},</if>",

            "    <if test='cje_hs300 != null'>cje_hs300=#{cje_hs300},</if>",
            "    <if test='cje_sh != null'>cje_sh=#{cje_sh},</if>",
            "    <if test='cje_sz != null'>cje_sz=#{cje_sz},</if>",
            "    <if test='cje_cyb != null'>cje_cyb=#{cje_cyb},</if>",
            "    <if test='cje_cyb50 != null'>cje_cyb50=#{cje_cyb50},</if>",
            "    <if test='cje_sh50 != null'>cje_sh50=#{cje_sh50},</if>",
            "    <if test='cje_zz500 != null'>cje_zz500=#{cje_zz500},</if>",
            "    <if test='cje_biz_qs != null'>cje_biz_qs=#{cje_biz_qs},</if>",

            "    <if test='pt_hs300 != null'>pt_hs300=#{pt_hs300},</if>",
            "    <if test='pt_cyb50 != null'>pt_cyb50=#{pt_cyb50},</if>",
            "    <if test='pt_cyb != null'>pt_cyb=#{pt_cyb},</if>",
            "    <if test='pt_sh50 != null'>pt_sh50=#{pt_sh50},</if>",
            "    <if test='pt_sh != null'>pt_sh=#{pt_sh},</if>",
            "    <if test='pt_sz != null'>pt_sz=#{pt_sz},</if>",
            "    <if test='pt_zz500 != null'>pt_zz500=#{pt_zz500},</if>",
            "    <if test='pt_biz_qs != null'>pt_biz_qs=#{pt_biz_qs},</if>",

            "    <if test='amt_dfcf != null'>amt_dfcf=#{amt_dfcf},</if>",
            "    <if test='hold_st != null'>hold_st=#{hold_st},</if>",
            "    <if test='earn_st != null'>earn_st=#{earn_st},</if>",
            "    <if test='rt_st != null'>rt_st=#{rt_st},</if>",
            "    <if test='amt != null'>amt=#{amt},</if>",
            "    <if test='amt_fund != null'>amt_fund=#{amt_fund},</if>",
            "    <if test='amt_fund_last != null'>amt_fund_last=#{amt_fund_last},</if>",
            "    <if test='earn_fund != null'>earn_fund=#{earn_fund},</if>",
            "    <if test='rt_zh != null'>rt_zh=#{rt_zh},</if>",
            "  </set>",
            "where CODE=#{code} AND fupan.period=#{period} AND fupan.TYPE=#{type}",
            "</script>"})
    void updateFupan(Fupan fupan);

}
