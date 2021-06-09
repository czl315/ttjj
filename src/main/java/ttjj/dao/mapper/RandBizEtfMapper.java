package ttjj.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ttjj.db.Fupan;
import ttjj.dto.RankBizDataDiff;

import java.util.List;

/**
 * @author chenzhilong
 * @date 2021/4/7
 */
public interface RandBizEtfMapper {
    @Select("select * from fupan where id= #{id}")
    public Fupan getUserByID(int id);

    @Insert({"<script>",
            "INSERT INTO `bank19`.`rank_st_biz`(",
            "`rs`,`date`,`type`,`order_num`",
            ",`f1`,`f2`,`f3`,`f4`",
            ",`f5`,`f6`,`f7`,`f8`,`f9`",
            ",`f10`,`f11`,`f12`,`f13`,`f14`",
            ",`f15`,`f16`,`f17`,`f18`,`f19`",
            ",`f20`,`f21`,`f22`,`f23`,`f24`",
            ",`f25`,`f26`,`f27`,`f28`,`f29`",
            ",`f30`,`f31`,`f32`,`f33`,`f34`,`f35`,`f36`,`f37`,`f38`,`f39`",
            ",`f40`,`f41`,`f42`,`f43`,`f44`,`f45`,`f46`,`f47`,`f48`,`f49`",
            ",`f50`,`f51`,`f52`,`f53`,`f54`,`f55`,`f56`,`f57`,`f58`,`f59`",
            ",`f60`,`f61`,`f62`,`f63`,`f64`",
            ",`f65`,`f66`,`f67`,`f68`,`f69`",
            ",`f70`,`f71`,`f72`,`f73`,`f74`",
            ",`f75`,`f76`,`f77`,`f78`,`f79`",
            ",`f80`,`f81`,`f82`,`f83`,`f84`",
            ",`f85`,`f86`,`f87`,`f88`,`f89`",
            ",`f90`,`f91`,`f92`,`f93`,`f94`",
            ",`f95`,`f96`,`f97`,`f98`,`f99`",
            ",`f104`",
            ",`f105`,`f107`,`f109`",
            ",`f110`,`f111`",
            ",`f115`",
            ",`f124`",
            ",`f127`,`f128`",
            ",`f136`,`f139`",
            ",`f140`,`f141`,`f142`,`f143`,`f144`",
            ",`f145`,`f148`,`f149`",
            ",`f152`",
            ",`f207`",
            ",`f208`",
            ",`f209`",
            ",`f222`,`f223`",
            ") VALUES (",
            "#{rs},#{date},#{type},#{orderNum}",
            ",#{f1},#{f2},#{f3},#{f4}",
            ",#{f5},#{f6},#{f7},#{f8},#{f9}",
            ",#{f10},#{f11},#{f12},#{f13},#{f14}",
            ",#{f15},#{f16},#{f17},#{f18},#{f19}",
            ",#{f20},#{f21},#{f22},#{f23},#{f24}",
            ",#{f25},#{f26},#{f27},#{f28},#{f29}",
            ",#{f30},#{f31},#{f32},#{f33},#{f34},#{f35},#{f36},#{f37},#{f38},#{f39}",
            ",#{f40},#{f41},#{f42},#{f43},#{f44},#{f45},#{f46},#{f47},#{f48},#{f49}",
            ",#{f50},#{f51},#{f52},#{f53},#{f54},#{f55},#{f56},#{f57},#{f58},#{f59}",
            ",#{f60},#{f61},#{f62},#{f63},#{f64}",
            ",#{f65},#{f66},#{f67},#{f68},#{f69}",
            ",#{f70},#{f71},#{f72},#{f73},#{f74}",
            ",#{f75},#{f76},#{f77},#{f78},#{f79}",
            ",#{f80},#{f81},#{f82},#{f83},#{f84}",
            ",#{f85},#{f86},#{f87},#{f88},#{f89}",
            ",#{f90},#{f91},#{f92},#{f93},#{f94}",
            ",#{f95},#{f96},#{f97},#{f98},#{f99}",
            ",#{f104}",
            ",#{f105},#{f107},#{f109}",
            ",#{f110},#{f111}",
            ",#{f115}",
            ",#{f124}",
            ",#{f127},#{f128}",
            ",#{f136},#{f139}",
            ",#{f140},#{f141},#{f142},#{f143},#{f144}",
            ",#{f145},#{f148},#{f149}",
            ",#{f152}",
            ",#{f207}",
            ",#{f208}",
            ",#{f209}",
            ",#{f222},#{f223}",
            ");",
            "</script>"})
    void insertRandBizEtf(RankBizDataDiff entity);

    @Update({"<script>",
            "update rank_st_biz",
            "  <set>",
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
            "  </set>",
            "where date=#{date} AND f12=#{f12}",
            "</script>"})
    void updateEtfNet(RankBizDataDiff entity);

    @Update({"<script>",
            "update rank_st_biz",
            "  <set>",
            "    <if test='pt_time_min != null'>pt_time_min=#{pt_time_min},</if>",
            "    <if test='pt_time_max != null'>pt_time_max=#{pt_time_max},</if>",
            "  </set>",
            "where date=#{date} AND f12=#{f12}",
            "</script>"})
    void updateDbEtfNetMaxMinTimeByDate(RankBizDataDiff entity);

}
