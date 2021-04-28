package ttjj.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ttjj.db.Fupan;
import ttjj.db.StockTradeDb;
import ttjj.dto.RankBizDataDiff;

/**
 * @author chenzhilong
 * @date 2021/4/7
 */
public interface StockTradeMapper {

    @Insert({"<script>",
            "INSERT INTO `bank19`.`stock_trade`(",
            " `FD_CODE`,`FD_INFO`, `TYPE`, `TRADE_TIME`, `ORDER_STATUS`, ",
            " `CONFIRM_SHARE`, `CONFIRM_NET`, `ORDER_AMT`, `STATUS`, `ORDER_CODE`,",
            " `CONFIRM_AMT`, `REDEM_AMT`, `EARN_AMT`, `CONFIRM_NET_DATA`, `SERVER_CHARGE`, ",
            " `REDEM_STATUS`, `REDEM_SHARE`, `REDEM_TIME`,`SOURCE`,  `CREATE_TIME`",
            ", `UPDATE_TIME` ,`BIZ_TP` ,`RK_ST_LOSS`,`RK_ST_PROFIT`",
            ") VALUES (",
            "   #{FD_CODE},#{FD_INFO},#{TYPE},#{TRADE_TIME},#{ORDER_STATUS},",
            "   #{CONFIRM_SHARE}, #{CONFIRM_NET}, #{ORDER_AMT}, #{STATUS}, #{ORDER_CODE},",
            "   #{CONFIRM_AMT}, #{REDEM_AMT}, #{EARN_AMT}, #{CONFIRM_NET_DATA}, #{SERVER_CHARGE},",
            "   #{REDEM_STATUS}, #{REDEM_SHARE}, #{REDEM_TIME},#{SOURCE},  #{CREATE_TIME},",
            "   #{UPDATE_TIME},#{BIZ_TP},#{RK_ST_LOSS},#{RK_ST_PROFIT}",
            ");",
            "</script>"})
    void insert(StockTradeDb entity);

    @Update({"<script>",
            "update stock_trade",
            "  <set>",
            "    <if test='conception != null'>conception=#{conception},</if>",
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
            "where FD_CODE=#{FD_CODE} ",
            "</script>"})
    void updateNet(StockTradeDb entity);

    /**
     * 更新-卖出
     *
     * @param entity
     */
    @Update({"<script>",
            "update stock_trade",
            "  <set>",
            "    <if test='TYPE != null'>TYPE='证券买入(卖出)',</if>",
            "    <if test='NET_REDEM != null'>NET_REDEM=#{NET_REDEM},</if>",
            "    <if test='REDEM_SHARE != null'>REDEM_SHARE=#{REDEM_SHARE},</if>",
            "    <if test='REDEM_TIME != null'>REDEM_TIME=#{REDEM_TIME},</if>",
            "    <if test='REDEM_AMT != null'>REDEM_AMT=#{REDEM_AMT},</if>",
            "    <if test='SERVER_CHARGE_REDEM != null'>SERVER_CHARGE_REDEM=#{SERVER_CHARGE_REDEM},</if>",
            "    <if test='REDEM_STATUS != null'>REDEM_STATUS=#{REDEM_STATUS},</if>",
            "    <if test='EARN_AMT != null'>EARN_AMT=`EARN_AMT`=ROUND((#{EARN_AMT}-`CONFIRM_AMT`-`SERVER_CHARGE`) ,2),</if>",
            "  </set>",
            "where FD_CODE=#{FD_CODE} ",
            "   AND (`TYPE` = '证券买入' OR `TYPE` = '证券买入(卖出中)') ",
            "   AND `CONFIRM_SHARE` = #{CONFIRM_SHARE} ",
            "LIMIT 1; ",
            "</script>"})
    void updateSellOut(StockTradeDb entity);

}
