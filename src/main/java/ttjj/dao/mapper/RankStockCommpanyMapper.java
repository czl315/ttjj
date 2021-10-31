package ttjj.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.*;

import java.util.List;

/**
 * @author chenzhilong
 * @date 2021/4/7
 */
public interface RankStockCommpanyMapper {
    /**
     * 查询-统计数据-股票分组
     *
     * @param condition
     * @return
     */
    @Select({"<script>",
            "   SELECT ",
            "       rank_st_biz_com.f12    code ",
            "       ,rank_st_biz_com.f14     name ",
            "       ,ROUND(SUM(rank_st_biz_com.f3),2) zhangfuSum   ",
            "       ,COUNT(1)   count ",
            "       ,(SELECT ROUND(t2.f20/100000000,2) FROM rank_st_biz_com t2 WHERE t2.f12=rank_st_biz_com.f12 ORDER BY t2.date desc LIMIT 1) lastMarketValue ",
            "       ,(SELECT t2.f9 FROM rank_st_biz_com t2 WHERE t2.f12=rank_st_biz_com.f12 ORDER BY t2.date desc LIMIT 1) lastPe ",
            "       ,(SELECT t2.f37 FROM rank_st_biz_com t2 WHERE t2.f12=rank_st_biz_com.f12 ORDER BY t2.date desc LIMIT 1) lastRoe ",
            "       <if test='begDate != null'> ",
            "       ,#{begDate} begDate ",
            "       </if> ",
            "       <if test='endDate != null'> ",
            "       ,#{endDate} endDate ",
            "       </if> ",
            "   FROM rank_st_biz_com ",
            "   WHERE 1=1  ",
            "       <if test='type_name != null'> ",
            "       AND rank_st_biz_com.type_name=#{type_name} ",
            "       </if> ",
            "       <if test='begDate != null'> ",
            "       <![CDATA[ AND rank_st_biz_com.date >= #{begDate} ]]> ",
            "       </if> ",
            "       <if test='endDate != null'> ",
            "       <![CDATA[ AND rank_st_biz_com.date <= #{endDate} ]]> ",
            "       </if> ",
            "       <if test='marketValueMin != null'> ",
            "       <![CDATA[ AND (SELECT ROUND(t2.f20/100000000,2) FROM rank_st_biz_com t2 WHERE t2.f12=rank_st_biz_com.f12 ORDER BY t2.date desc LIMIT 1) >= #{marketValueMin} ]]> ",
            "       </if> ",
            "       <if test='marketValueMax != null'> ",
            "       <![CDATA[ AND (SELECT ROUND(t2.f20/100000000,2) FROM rank_st_biz_com t2 WHERE t2.f12=rank_st_biz_com.f12 ORDER BY t2.date desc LIMIT 1) <= #{marketValueMax} ]]> ",
            "       </if> ",
            "       <if test='plate != null'> ",
            "       AND rank_st_biz_com.f139=#{plate} ",
            "       </if> ",
//            "       AND rank_st_biz_com.f148 NOT IN('2') ",//状态限定：f148 ?状态：2-退市或停牌;16-未上市;
            "   GROUP BY rank_st_biz_com.f12 ",
//            "       <if test='orderBy != null'> ",
//            "        ORDER BY  #{orderBy}  ",
//            "       </if> ",
            "   ORDER BY SUM(rank_st_biz_com.f3) DESC ",
//            "   ORDER BY code  ",
//            "   ORDER BY lastRoe DESC ",
            "</script>"})
    List<RankStComTjRs> findListTongjiGroup(RankStComTjCond condition);

    /**
     * 查询-均线突破
     *
     * @param condition
     * @return
     */
    @Select({"<script>",
            /**突破5日均线：前一日5日均线为负、当前日5日均线为正**/
            "SELECT",
            "   rank_st_biz_com.f12 code ",
            "   ,rank_st_biz_com.f14 name ",
            "   ,(SELECT t.f3 FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date=#{curDayAdd1}) adrCurDayAdd1 ",
            "   <![CDATA[ ,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=#{curDayAdd1} AND t.date<=#{curDayAdd1}),2) adrDaySum1 ]]>",
            "   <![CDATA[ ,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=#{curDayAdd1} AND t.date<=#{curDayAdd2}),2) adrDaySum2 ]]>",
            "   <![CDATA[ ,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=#{curDayAdd1} AND t.date<=#{curDayAdd3}),2) adrDaySum3 ]]>",
            "   ,rank_st_biz_com.f3 adrCurDay",
//            "   ,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_5)/rank_st_biz_com.NET_MA_5*100,2) goodRate5 ",
            "   ,(SELECT ROUND((t.f2-t.NET_MA_5)/t.NET_MA_5*100,2) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = #{curDaySub1}) goodRate5DaySub1 ",
////            ",ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_10)/rank_st_biz_com.NET_MA_10*100,2) 乖离10,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_20)/rank_st_biz_com.NET_MA_20*100,2) 乖离20,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_30)/rank_st_biz_com.NET_MA_30*100,2) 乖离30,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_60)/rank_st_biz_com.NET_MA_60*100,2) 乖离60,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_120)/rank_st_biz_com.NET_MA_120*100,2) 乖离120,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_250)/rank_st_biz_com.NET_MA_250*100,2) 乖离250",
////            ",rank_st_biz_com.date",
////            ",ROUND(rank_st_biz_com.f20/100000000,2) 总市值亿",
////            ",rank_st_biz_com.type_name 板块",
////            "-- ROUND(rank_st_biz_com.f62/100000000,2) 主力净流入亿,ROUND(rank_st_biz_com.f6/100000000,2) 成交额亿,rank_st_biz_com.f9 市盈率",
////            ",rank_st_biz_com.f2 现价",
////            ",rank_st_biz_com.*",
            " FROM ",
            "   `rank_st_biz_com` rank_st_biz_com ",
            " WHERE 1=1 ",
            "   AND rank_st_biz_com.date = #{curDay} ",
            "   <if test='type_name != null'> ",
            "   AND rank_st_biz_com.type_name in (#{type_name}) ",//-- 业务板块
            "   </if> ",
            "   AND rank_st_biz_com.f139=#{f139} ",// -- A股主板
//            "   AND rank_st_biz_com.f20 >#{f20} ",//-- 市值
            "   <if test='goodRateCurDayLimitDown != null'> ",
            "   <![CDATA[ AND (rank_st_biz_com.f2-rank_st_biz_com.NET_MA_5)/rank_st_biz_com.NET_MA_5 >= #{goodRateCurDayLimitDown} ]]>",//-- 乖离率
            "   </if> ",
            "   <if test='goodRateCurDayLimitUp != null'> ",
            "   <![CDATA[ AND (rank_st_biz_com.f2-rank_st_biz_com.NET_MA_5)/rank_st_biz_com.NET_MA_5 <= #{goodRateCurDayLimitUp} ]]>",//-- 乖离率
            "   </if> ",
            "   <if test='goodRateDaySub1 != null'> ",
            "   <![CDATA[ AND (SELECT ROUND((t.f2-t.NET_MA_5)/t.NET_MA_5*100,2) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = #{curDaySub1}) <= #{goodRateDaySub1} ]]>",//-- 昨日乖离率
            "   </if> ",
//            " ORDER BY ",
//            "   (SELECT ROUND((t.f2-t.NET_MA_5)/t.NET_MA_5*100,2) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = #{curDaySub1})",/**昨日乖离率**/
//            "-- (SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDayAdd1 AND t.date<=@curDayAdd3) ",
////            "-- 日加1涨幅",
////            "-- (SELECT ROUND((t.f2-t.NET_MA_5)/t.NET_MA_5*100,2) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = @curDaySub1)/**rank_st_biz_com.date desc, f3  DESCROUND(rank_st_biz_com.f20,0) desc 乖离5**/",
////            ";",
            "</script>"})
    List<MaBreakUpRs> findListMaBreakUpCond(MaBreakUpCond condition);

    /**
     * 查询-超跌反弹
     *
     * @param condition
     * @return
     */
    @Select({"<script>",
            " SELECT",
            "   rank_st_biz_com.f12 code ",
            "   ,rank_st_biz_com.f14 name ",
            "   ,rank_st_biz_com.type_name type_name ",
            "   ,rank_st_biz_com.f3 adrSum ",
            "   ,rank_st_biz_com.date curDay ",
            "   <if test='curDayAdd1 != null'> ",
            "   <![CDATA[ ,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=#{curDayAdd1} AND t.date<=#{curDayAdd1}),2) adrSum1  ]]>",
            "   </if> ",
            "   <if test='curDayAdd2 != null'> ",
            "   <![CDATA[ ,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=#{curDayAdd1} AND t.date<=#{curDayAdd2}),2) adrSum2 ]]>",
            "   </if> ",
            "   <if test='curDayAdd3 != null'> ",
            "   <![CDATA[ ,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=#{curDayAdd1} AND t.date<=#{curDayAdd3}),2) adrSum3 ]]>",
            "   </if> ",
            "   <if test='curDayAdd5 != null'> ",
            "   <![CDATA[ ,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=#{curDayAdd1} AND t.date<=#{curDayAdd5}),2) adrSum5 ]]>",
            "   </if> ",
            "   <if test='curDayAdd10 != null'> ",
            "   <![CDATA[ ,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=#{curDayAdd1} AND t.date<=#{curDayAdd10}),2) adrSum10 ]]>",
            "   </if> ",
            "   <if test='curDayAdd20 != null'> ",
            "   <![CDATA[ ,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=#{curDayAdd1} AND t.date<=#{curDayAdd20}),2) adrSum20 ]]>",
            "   </if> ",
            "   <if test='curDayAdd30 != null'> ",
            "   <![CDATA[ ,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=#{curDayAdd1} AND t.date<=#{curDayAdd30}),2) adrSum30 ]]>",
            "   </if> ",
            "   <if test='curDayAdd60 != null'> ",
            "   <![CDATA[ ,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=#{curDayAdd1} AND t.date<=#{curDayAdd60}),2) adrSum60 ]]>",
            "   </if> ",
            "   <if test='curDaySub1 != null'> ",
            "   <![CDATA[ ,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = #{curDaySub1}),2) adrDaySub1 ]]>",
            "   </if> ",
            "   <if test='curDaySub2 != null'> ",
            "   <![CDATA[ ,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = #{curDaySub2}),2) adrDaySub2 ]]>",
            "   </if> ",
            "   <if test='curDaySub3 != null'> ",
            "   <![CDATA[ ,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = #{curDaySub3}),2) adrDaySub3 ]]>",
            "   </if> ",
            "   <if test='curDaySub4 != null'> ",
            "   <![CDATA[ ,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = #{curDaySub4}),2) adrDaySub4 ]]>",
            "   </if> ",
            "   <if test='curDaySub5 != null'> ",
            "   <![CDATA[ ,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = #{curDaySub5}),2) adrDaySub5 ]]>",
            "   </if> ",
//            "   ,rank_st_biz_com.f3 adrCurDay",
//            "   ,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_5)/rank_st_biz_com.NET_MA_5*100,2) goodRate5 ",
//            "   ,(SELECT ROUND((t.f2-t.NET_MA_5)/t.NET_MA_5*100,2) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = #{curDaySub1}) goodRate5DaySub1 ",
////            ",ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_10)/rank_st_biz_com.NET_MA_10*100,2) 乖离10,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_20)/rank_st_biz_com.NET_MA_20*100,2) 乖离20,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_30)/rank_st_biz_com.NET_MA_30*100,2) 乖离30,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_60)/rank_st_biz_com.NET_MA_60*100,2) 乖离60,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_120)/rank_st_biz_com.NET_MA_120*100,2) 乖离120,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_250)/rank_st_biz_com.NET_MA_250*100,2) 乖离250",
////            ",rank_st_biz_com.date",
////            ",ROUND(rank_st_biz_com.f20/100000000,2) 总市值亿",
////            ",rank_st_biz_com.type_name 板块",
////            "-- ROUND(rank_st_biz_com.f62/100000000,2) 主力净流入亿,ROUND(rank_st_biz_com.f6/100000000,2) 成交额亿,rank_st_biz_com.f9 市盈率",
////            ",rank_st_biz_com.f2 现价",
////            ",rank_st_biz_com.*",
            " FROM ",
            "   `rank_st_biz_com` rank_st_biz_com ",
            " WHERE 1=1 ",
            "   AND rank_st_biz_com.date = #{curDay} ",
            "   <if test='type_name != null'> ",
            "   AND rank_st_biz_com.type_name in (#{type_name}) ",//-- 业务板块
            "   </if> ",
            "   AND rank_st_biz_com.f139=#{f139} ",// -- A股主板
            "   AND rank_st_biz_com.f20 >#{marketValueMin} ",//-- 市值
            "   <if test='qrrMaxDaySub1 != null and curDaySub1 != null '> ",
            "   <![CDATA[ AND IFNULL((SELECT t2.f10 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = #{curDaySub1}),0)<=#{qrrMaxDaySub1} ]]>",//-- 量比
            "   </if> ",
            "   <if test='qrrMaxDaySub2 != null and curDaySub2 != null'> ",
            "   <![CDATA[ AND IFNULL((SELECT t2.f10 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = #{curDaySub2}),0)<=#{qrrMaxDaySub2} ]]>",//-- 量比
            "   </if> ",
            "   <if test='qrrMaxDaySub3 != null and curDaySub3 != null'> ",
            "   <![CDATA[ AND IFNULL((SELECT t2.f10 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = #{curDaySub3}),0)<=#{qrrMaxDaySub3} ]]>",//-- 量比
            "   </if> ",
            "   <if test='qrrMaxDaySub4 != null and curDaySub4 != null'> ",
            "   <![CDATA[ AND IFNULL((SELECT t2.f10 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = #{curDaySub4}),0)<=#{qrrMaxDaySub4} ]]>",//-- 量比
            "   </if> ",
            "   <if test='qrrMaxDaySub5 != null and curDaySub5 != null'> ",
            "   <![CDATA[ AND IFNULL((SELECT t2.f10 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = #{curDaySub5}),0)<=#{qrrMaxDaySub5} ]]>",//-- 量比
            "   </if> ",
            "   <if test='adrMinDay0 != null'> ",
            "   <![CDATA[ AND rank_st_biz_com.f3 >= #{adrMinDay0} ]]>",//涨跌限定-最低
            "   </if> ",
            "   <if test='adrMaxDaySub1 != null'> ",
            "   <![CDATA[ AND IFNULL((SELECT t2.f3 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = #{curDaySub1}),0) <= #{adrMaxDaySub1} ]]>",
            "   </if> ",
            "   <if test='adrMaxDaySub2 != null'> ",
            "   <![CDATA[ AND IFNULL((SELECT t2.f3 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = #{curDaySub2}),0) <= #{adrMaxDaySub2} ]]>",
            "   </if> ",
            "   <if test='adrMaxDaySub3 != null'> ",
            "   <![CDATA[ AND IFNULL((SELECT t2.f3 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = #{curDaySub3}),0) <= #{adrMaxDaySub3} ]]>",
            "   </if> ",
            "   <if test='adrMaxDaySub4 != null'> ",
            "   <![CDATA[ AND IFNULL((SELECT t2.f3 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = #{curDaySub4}),0) <= #{adrMaxDaySub4} ]]>",
            "   </if> ",
            "   <if test='adrMaxDaySub5 != null'> ",
            "   <![CDATA[ AND IFNULL((SELECT t2.f3 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = #{curDaySub5}),0) <= #{adrMaxDaySub5} ]]>",
            "   </if> ",
            " ORDER BY ",
            "   f3  DESC",
//            "   (SELECT ROUND((t.f2-t.NET_MA_5)/t.NET_MA_5*100,2) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = #{curDaySub1})",/**昨日乖离率**/
//            "-- (SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDayAdd1 AND t.date<=@curDayAdd3) ",
////            "-- (SELECT ROUND((t.f2-t.NET_MA_5)/t.NET_MA_5*100,2) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = @curDaySub1)/**rank_st_biz_com.date desc, f3  DESCROUND(rank_st_biz_com.f20,0) desc 乖离5**/",
////            ";",
            "</script>"})
    List<SuperDropBounceRs> findListSuperDropBounce(SuperDropBounceCond condition);

    /**
     * 查询某日之前的交易日期列表
     *
     * @param condition
     * @return
     */
    @Select({"<script>",
            " <![CDATA[  SELECT t.date FROM rank_st_biz_com t WHERE t.date < #{curDate} GROUP BY t.date ORDER BY t.date desc limit #{count} ]]> ",
            "</script>"})
    List<String> findListDateBefore(DateCond condition);

    /**
     * 查询某日之后的交易日期列表
     *
     * @param condition
     * @return
     */
    @Select({"<script>",
            " <![CDATA[  SELECT t.date FROM rank_st_biz_com t WHERE t.date > #{curDate} GROUP BY t.date ORDER BY t.date ASC limit #{count} ]]> ",
            "</script>"})
    List<String> findListDateAfter(DateCond condition);

    @Insert({"<script>",
            "INSERT INTO `bank19`.`rank_st_biz_com`(",
            "`rs`,`date`,`type`,`type_name`,`order_num`",
            ",`month`,`weekYear`,`week`",
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
            ", `CREATE_TIME`, `UPDATE_TIME`",
            ") VALUES (",
            "#{rs},#{date},#{type},#{type_name},#{order_num}",
            ",#{month},#{weekYear},#{week}",
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
            ",#{CREATE_TIME},#{UPDATE_TIME}",
            ");",
            "</script>"})
    void insert(RankStockCommpanyDb entity);

    @Update({"<script>",
            "update rank_st_biz_com",
            "  <set>",
            "    <if test='conception != null'>conception=#{conception},</if>",
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
            "    <if test='fundFlow != null'>fundFlow=#{fundFlow},</if>",
            "  </set>",
            "where date=#{date} AND f12=#{f12}",
            "</script>"})
    void update(RankStockCommpanyDb entity);

    @Update({"<script>",
            "update rank_st_biz_com",
            "  <set>",
            "    <if test='month != null'>month=#{month},</if>",
            "    <if test='weekYear != null'>weekYear=#{weekYear},</if>",
            "    <if test='week != null'>week=#{week},</if>",
            "  </set>",
            "where date=#{date} ",
            "</script>"})
    void updateDate(RankBizDataDiff entity);

}
