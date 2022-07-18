package ttjj.db;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 股票涨跌次数
 *
 * @author Administrator
 * @date 2022-05-09 23:47
 *  * 2022-05-10:增加最近3个月的涨幅次数统计：5日、10日、20日、40日、60日、近3月涨跌和
 */
public class StockAdrCount {
    /**
     * date 日期
     */
    private String date;
    /**
     * type_name 类型-板块名称
     */
    private String type_name;
    /**
     * order_num 排序号
     */
    private BigDecimal order_num;
    /**
     * conception 概念-要点内容
     */
    private String conception;
    /**
     * f2 指数点（当前价格）
     */
    private BigDecimal f2;
    /**
     * f3 涨跌幅
     */
    private BigDecimal f3;
    /**
     * f4 涨跌数值
     */
    private BigDecimal f4;
    /**
     * f5 成交量
     */
    private Long f5;
    /**
     * f6 成交额
     */
    private Long f6;
    /**
     * f7 振幅
     */
    private BigDecimal f7;
    /**
     * f8 换手
     */
    private BigDecimal f8;
    /**
     * f9 市盈(动)
     */
    private BigDecimal f9;
    /**
     * f10 量比
     */
    private BigDecimal f10;
    /**
     * f12 股票代码
     */
    private String f12;
    /**
     * f14 证券名称
     */
    private String f14;
    /**
     * f15 最高
     */
    private BigDecimal f15;
    /**
     * f16 最低
     */
    private BigDecimal f16;
    /**
     * f17 今日开盘
     */
    private BigDecimal f17;
    /**
     * f18 昨日收盘
     */
    private BigDecimal f18;
    /**
     * f20 总市值
     */
    private BigDecimal f20;
    /**
     * f21 流通市值
     */
    private BigDecimal f21;
    /**
     * f62 主力净流入
     */
    private BigDecimal f62;
    /**
     * f139 2-A股主板(00XXXX/60XXXX);3-B股(200XXX/900XXX);5-创业板(30XXXX);32-科创板(688XXX);33-存托凭证(689XXX,689是科创板专门为CDR存托凭证公司)
     */
    private Long f139;

    /**
     * ADR_UP_COUNT_1 涨幅次数之和：1日
     */
    private BigDecimal ADR_UP_COUNT_1;
    /**
     * ADR_UP_COUNT_2 涨幅次数之和：2日
     */
    private BigDecimal ADR_UP_COUNT_2;
    /**
     * ADR_UP_COUNT_3 涨幅次数之和：3日
     */
    private BigDecimal ADR_UP_COUNT_3;
    /**
     * ADR_UP_COUNT_7 涨幅次数：5日、10日、20日、40日、60日、近3月涨跌和
     */
    private BigDecimal ADR_UP_COUNT_5;
    /**
     * ADR_UP_COUNT_10 涨幅次数：5日、10日、20日、40日、60日、近3月涨跌和
     */
    private BigDecimal ADR_UP_COUNT_10;
    /**
     * ADR_UP_COUNT_20 涨幅次数：5日、10日、20日、40日、60日、近3月涨跌和
     */
    private BigDecimal ADR_UP_COUNT_20;
    /**
     * ADR_UP_COUNT_40 涨幅次数：5日、10日、20日、40日、60日、近3月涨跌和
     */
    private BigDecimal ADR_UP_COUNT_40;
    /**
     * ADR_UP_COUNT_60 涨幅次数：5日、10日、20日、40日、60日、近3月涨跌和
     */
    private BigDecimal ADR_UP_COUNT_60;
    /**
     * ADR_UP_COUNT_stat 涨幅次数统计近3月：5日、10日、20日、40日、60日之和
     */
    private BigDecimal ADR_UP_COUNT_SUM_60;
    /**
     * ADR_UP_SUM_1_1
     */
    private BigDecimal ADR_UP_SUM_1_1;
    /**
     * ADR_UP_SUM_1_2
     */
    private BigDecimal ADR_UP_SUM_1_2;
    /**
     * ADR_UP_SUM_1_3
     */
    private BigDecimal ADR_UP_SUM_1_3;
    /**
     * ADR_UP_SUM_1_5 上涨之和
     */
    private BigDecimal ADR_UP_SUM_1_5;
    /**
     * ADR_UP_SUM_1_10
     */
    private BigDecimal ADR_UP_SUM_1_10;
    /**
     * ADR_UP_SUM_1_20
     */
    private BigDecimal ADR_UP_SUM_1_20;
    /**
     * ADR_UP_COUNT_SUM_20_40 涨幅次数统计
     */
    private BigDecimal ADR_UP_SUM_20_40;
    /**
     * ADR_UP_COUNT_SUM_40_60 涨幅次数统计
     */
    private BigDecimal ADR_UP_SUM_40_60;
    /**
     * ADR_UP_SUM_1_60
     */
    private BigDecimal ADR_UP_SUM_1_60;
    /**
     * ADR_UP_SUM_ORDER_1_5 上涨之和排序
     */
    private BigDecimal ADR_UP_SUM_ORDER_1_5;
    /**
     * ADR_UP_SUM_ORDER_1_10
     */
    private BigDecimal ADR_UP_SUM_ORDER_1_10;
    /**
     * ADR_UP_SUM_ORDER_1_20
     */
    private BigDecimal ADR_UP_SUM_ORDER_1_20;
    /**
     * ADR_UP_SUM_ORDER_20_40
     */
    private BigDecimal ADR_UP_SUM_ORDER_20_40;
    /**
     * ADR_UP_SUM_ORDER_40_60
     */
    private BigDecimal ADR_UP_SUM_ORDER_40_60;
    /**
     * ADR_UP_SUM_ORDER_1_60
     */
    private BigDecimal ADR_UP_SUM_ORDER_1_60;
    /**
     * 排序号
     */
    private BigDecimal ADR_UP_COUNT_ORDER_NUM_1_10;
    /**
     * 排序号
     */
    private BigDecimal ADR_UP_COUNT_ORDER_NUM_1_20;
    /**
     * 排序号
     */
    private BigDecimal ADR_UP_COUNT_ORDER_NUM_20_40;
    /**
     * 排序号
     */
    private BigDecimal ADR_UP_COUNT_ORDER_NUM_40_60;
    /**
     * 涨幅次数统计：1-60交易日之和
     */
    private BigDecimal ADR_UP_COUNT_SUM_1_60;

    /**
     * NET_AREA_DAY_5 净值区间
     */
    private BigDecimal NET_AREA_DAY_5;
    /**
     * NET_AREA_DAY_10
     */
    private BigDecimal NET_AREA_DAY_10;
    /**
     * NET_AREA_DAY_20
     */
    private BigDecimal NET_AREA_DAY_20;
    /**
     * NET_AREA_DAY_40
     */
    private BigDecimal NET_AREA_DAY_40;
    /**
     * NET_AREA_DAY_60
     */
    private BigDecimal NET_AREA_DAY_60;
    /**
     * NET_AREA_DAY_120
     */
    private BigDecimal NET_AREA_DAY_120;
    /**
     * NET_AREA_DAY_250
     */
    private BigDecimal NET_AREA_DAY_250;
    /**
     * UP_MA_ 超过均线
     */
    private String UP_MA_5;
    /**
     * UP_MA_15
     */
    private String UP_MA_15;
    /**
     * UP_MA_30
     */
    private String UP_MA_30;
    /**
     * UP_MA_60
     */
    private String UP_MA_60;
    /**
     * UP_MA_101
     */
    private String UP_MA_101;
    /**
     * UP_MA_102
     */
    private String UP_MA_102;

    /**
     * UPDATE_TIME
     */
    private Date UPDATE_TIME;

    public Date getUPDATE_TIME() {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(Date UPDATE_TIME) {
        this.UPDATE_TIME = UPDATE_TIME;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public BigDecimal getOrder_num() {
        return order_num;
    }

    public void setOrder_num(BigDecimal order_num) {
        this.order_num = order_num;
    }

    public String getConception() {
        return conception;
    }

    public void setConception(String conception) {
        this.conception = conception;
    }

    public BigDecimal getF2() {
        return f2;
    }

    public void setF2(BigDecimal f2) {
        this.f2 = f2;
    }

    public BigDecimal getF3() {
        return f3;
    }

    public void setF3(BigDecimal f3) {
        this.f3 = f3;
    }

    public BigDecimal getF4() {
        return f4;
    }

    public void setF4(BigDecimal f4) {
        this.f4 = f4;
    }

    public Long getF5() {
        return f5;
    }

    public void setF5(Long f5) {
        this.f5 = f5;
    }

    public Long getF6() {
        return f6;
    }

    public void setF6(Long f6) {
        this.f6 = f6;
    }

    public BigDecimal getF7() {
        return f7;
    }

    public void setF7(BigDecimal f7) {
        this.f7 = f7;
    }

    public BigDecimal getF8() {
        return f8;
    }

    public void setF8(BigDecimal f8) {
        this.f8 = f8;
    }

    public BigDecimal getF9() {
        return f9;
    }

    public void setF9(BigDecimal f9) {
        this.f9 = f9;
    }

    public BigDecimal getF10() {
        return f10;
    }

    public void setF10(BigDecimal f10) {
        this.f10 = f10;
    }

    public String getF12() {
        return f12;
    }

    public void setF12(String f12) {
        this.f12 = f12;
    }

    public String getF14() {
        return f14;
    }

    public void setF14(String f14) {
        this.f14 = f14;
    }

    public BigDecimal getF15() {
        return f15;
    }

    public void setF15(BigDecimal f15) {
        this.f15 = f15;
    }

    public BigDecimal getF16() {
        return f16;
    }

    public void setF16(BigDecimal f16) {
        this.f16 = f16;
    }

    public BigDecimal getF17() {
        return f17;
    }

    public void setF17(BigDecimal f17) {
        this.f17 = f17;
    }

    public BigDecimal getF18() {
        return f18;
    }

    public void setF18(BigDecimal f18) {
        this.f18 = f18;
    }

    public BigDecimal getF20() {
        return f20;
    }

    public void setF20(BigDecimal f20) {
        this.f20 = f20;
    }

    public BigDecimal getF21() {
        return f21;
    }

    public void setF21(BigDecimal f21) {
        this.f21 = f21;
    }

    public BigDecimal getADR_UP_COUNT_5() {
        return ADR_UP_COUNT_5;
    }

    public void setADR_UP_COUNT_5(BigDecimal ADR_UP_COUNT_5) {
        this.ADR_UP_COUNT_5 = ADR_UP_COUNT_5;
    }

    public BigDecimal getADR_UP_COUNT_10() {
        return ADR_UP_COUNT_10;
    }

    public void setADR_UP_COUNT_10(BigDecimal ADR_UP_COUNT_10) {
        this.ADR_UP_COUNT_10 = ADR_UP_COUNT_10;
    }

    public BigDecimal getADR_UP_COUNT_20() {
        return ADR_UP_COUNT_20;
    }

    public void setADR_UP_COUNT_20(BigDecimal ADR_UP_COUNT_20) {
        this.ADR_UP_COUNT_20 = ADR_UP_COUNT_20;
    }

    public BigDecimal getADR_UP_COUNT_40() {
        return ADR_UP_COUNT_40;
    }

    public void setADR_UP_COUNT_40(BigDecimal ADR_UP_COUNT_40) {
        this.ADR_UP_COUNT_40 = ADR_UP_COUNT_40;
    }

    public BigDecimal getADR_UP_COUNT_60() {
        return ADR_UP_COUNT_60;
    }

    public void setADR_UP_COUNT_60(BigDecimal ADR_UP_COUNT_60) {
        this.ADR_UP_COUNT_60 = ADR_UP_COUNT_60;
    }

    public BigDecimal getADR_UP_COUNT_SUM_60() {
        return ADR_UP_COUNT_SUM_60;
    }

    public void setADR_UP_COUNT_SUM_60(BigDecimal ADR_UP_COUNT_SUM_60) {
        this.ADR_UP_COUNT_SUM_60 = ADR_UP_COUNT_SUM_60;
    }

    public BigDecimal getNET_AREA_DAY_5() {
        return NET_AREA_DAY_5;
    }

    public void setNET_AREA_DAY_5(BigDecimal NET_AREA_DAY_5) {
        this.NET_AREA_DAY_5 = NET_AREA_DAY_5;
    }

    public BigDecimal getNET_AREA_DAY_10() {
        return NET_AREA_DAY_10;
    }

    public void setNET_AREA_DAY_10(BigDecimal NET_AREA_DAY_10) {
        this.NET_AREA_DAY_10 = NET_AREA_DAY_10;
    }

    public BigDecimal getNET_AREA_DAY_20() {
        return NET_AREA_DAY_20;
    }

    public void setNET_AREA_DAY_20(BigDecimal NET_AREA_DAY_20) {
        this.NET_AREA_DAY_20 = NET_AREA_DAY_20;
    }

    public BigDecimal getNET_AREA_DAY_40() {
        return NET_AREA_DAY_40;
    }

    public void setNET_AREA_DAY_40(BigDecimal NET_AREA_DAY_40) {
        this.NET_AREA_DAY_40 = NET_AREA_DAY_40;
    }

    public BigDecimal getNET_AREA_DAY_60() {
        return NET_AREA_DAY_60;
    }

    public void setNET_AREA_DAY_60(BigDecimal NET_AREA_DAY_60) {
        this.NET_AREA_DAY_60 = NET_AREA_DAY_60;
    }

    public BigDecimal getNET_AREA_DAY_120() {
        return NET_AREA_DAY_120;
    }

    public void setNET_AREA_DAY_120(BigDecimal NET_AREA_DAY_120) {
        this.NET_AREA_DAY_120 = NET_AREA_DAY_120;
    }

    public BigDecimal getNET_AREA_DAY_250() {
        return NET_AREA_DAY_250;
    }

    public void setNET_AREA_DAY_250(BigDecimal NET_AREA_DAY_250) {
        this.NET_AREA_DAY_250 = NET_AREA_DAY_250;
    }

    public String getUP_MA_5() {
        return UP_MA_5;
    }

    public void setUP_MA_5(String UP_MA_5) {
        this.UP_MA_5 = UP_MA_5;
    }

    public String getUP_MA_15() {
        return UP_MA_15;
    }

    public void setUP_MA_15(String UP_MA_15) {
        this.UP_MA_15 = UP_MA_15;
    }

    public String getUP_MA_30() {
        return UP_MA_30;
    }

    public void setUP_MA_30(String UP_MA_30) {
        this.UP_MA_30 = UP_MA_30;
    }

    public String getUP_MA_60() {
        return UP_MA_60;
    }

    public void setUP_MA_60(String UP_MA_60) {
        this.UP_MA_60 = UP_MA_60;
    }

    public String getUP_MA_101() {
        return UP_MA_101;
    }

    public void setUP_MA_101(String UP_MA_101) {
        this.UP_MA_101 = UP_MA_101;
    }

    public String getUP_MA_102() {
        return UP_MA_102;
    }

    public void setUP_MA_102(String UP_MA_102) {
        this.UP_MA_102 = UP_MA_102;
    }

    public BigDecimal getADR_UP_COUNT_1() {
        return ADR_UP_COUNT_1;
    }

    public void setADR_UP_COUNT_1(BigDecimal ADR_UP_COUNT_1) {
        this.ADR_UP_COUNT_1 = ADR_UP_COUNT_1;
    }

    public BigDecimal getADR_UP_COUNT_2() {
        return ADR_UP_COUNT_2;
    }

    public void setADR_UP_COUNT_2(BigDecimal ADR_UP_COUNT_2) {
        this.ADR_UP_COUNT_2 = ADR_UP_COUNT_2;
    }

    public BigDecimal getADR_UP_COUNT_3() {
        return ADR_UP_COUNT_3;
    }

    public void setADR_UP_COUNT_3(BigDecimal ADR_UP_COUNT_3) {
        this.ADR_UP_COUNT_3 = ADR_UP_COUNT_3;
    }

    public Long getF139() {
        return f139;
    }

    public void setF139(Long f139) {
        this.f139 = f139;
    }

    public BigDecimal getF62() {
        return f62;
    }

    public void setF62(BigDecimal f62) {
        this.f62 = f62;
    }

    public BigDecimal getADR_UP_SUM_20_40() {
        return ADR_UP_SUM_20_40;
    }

    public void setADR_UP_SUM_20_40(BigDecimal ADR_UP_SUM_20_40) {
        this.ADR_UP_SUM_20_40 = ADR_UP_SUM_20_40;
    }

    public BigDecimal getADR_UP_SUM_40_60() {
        return ADR_UP_SUM_40_60;
    }

    public void setADR_UP_SUM_40_60(BigDecimal ADR_UP_SUM_40_60) {
        this.ADR_UP_SUM_40_60 = ADR_UP_SUM_40_60;
    }

    public BigDecimal getADR_UP_COUNT_ORDER_NUM_1_10() {
        return ADR_UP_COUNT_ORDER_NUM_1_10;
    }

    public void setADR_UP_COUNT_ORDER_NUM_1_10(BigDecimal ADR_UP_COUNT_ORDER_NUM_1_10) {
        this.ADR_UP_COUNT_ORDER_NUM_1_10 = ADR_UP_COUNT_ORDER_NUM_1_10;
    }

    public BigDecimal getADR_UP_COUNT_ORDER_NUM_1_20() {
        return ADR_UP_COUNT_ORDER_NUM_1_20;
    }

    public void setADR_UP_COUNT_ORDER_NUM_1_20(BigDecimal ADR_UP_COUNT_ORDER_NUM_1_20) {
        this.ADR_UP_COUNT_ORDER_NUM_1_20 = ADR_UP_COUNT_ORDER_NUM_1_20;
    }

    public BigDecimal getADR_UP_COUNT_ORDER_NUM_20_40() {
        return ADR_UP_COUNT_ORDER_NUM_20_40;
    }

    public void setADR_UP_COUNT_ORDER_NUM_20_40(BigDecimal ADR_UP_COUNT_ORDER_NUM_20_40) {
        this.ADR_UP_COUNT_ORDER_NUM_20_40 = ADR_UP_COUNT_ORDER_NUM_20_40;
    }

    public BigDecimal getADR_UP_COUNT_ORDER_NUM_40_60() {
        return ADR_UP_COUNT_ORDER_NUM_40_60;
    }

    public void setADR_UP_COUNT_ORDER_NUM_40_60(BigDecimal ADR_UP_COUNT_ORDER_NUM_40_60) {
        this.ADR_UP_COUNT_ORDER_NUM_40_60 = ADR_UP_COUNT_ORDER_NUM_40_60;
    }

    public BigDecimal getADR_UP_COUNT_SUM_1_60() {
        return ADR_UP_COUNT_SUM_1_60;
    }

    public void setADR_UP_COUNT_SUM_1_60(BigDecimal ADR_UP_COUNT_SUM_1_60) {
        this.ADR_UP_COUNT_SUM_1_60 = ADR_UP_COUNT_SUM_1_60;
    }

    public BigDecimal getADR_UP_SUM_1_5() {
        return ADR_UP_SUM_1_5;
    }

    public void setADR_UP_SUM_1_5(BigDecimal ADR_UP_SUM_1_5) {
        this.ADR_UP_SUM_1_5 = ADR_UP_SUM_1_5;
    }

    public BigDecimal getADR_UP_SUM_1_10() {
        return ADR_UP_SUM_1_10;
    }

    public void setADR_UP_SUM_1_10(BigDecimal ADR_UP_SUM_1_10) {
        this.ADR_UP_SUM_1_10 = ADR_UP_SUM_1_10;
    }

    public BigDecimal getADR_UP_SUM_1_20() {
        return ADR_UP_SUM_1_20;
    }

    public void setADR_UP_SUM_1_20(BigDecimal ADR_UP_SUM_1_20) {
        this.ADR_UP_SUM_1_20 = ADR_UP_SUM_1_20;
    }

    public BigDecimal getADR_UP_SUM_ORDER_1_5() {
        return ADR_UP_SUM_ORDER_1_5;
    }

    public void setADR_UP_SUM_ORDER_1_5(BigDecimal ADR_UP_SUM_ORDER_1_5) {
        this.ADR_UP_SUM_ORDER_1_5 = ADR_UP_SUM_ORDER_1_5;
    }

    public BigDecimal getADR_UP_SUM_ORDER_1_10() {
        return ADR_UP_SUM_ORDER_1_10;
    }

    public void setADR_UP_SUM_ORDER_1_10(BigDecimal ADR_UP_SUM_ORDER_1_10) {
        this.ADR_UP_SUM_ORDER_1_10 = ADR_UP_SUM_ORDER_1_10;
    }

    public BigDecimal getADR_UP_SUM_ORDER_1_20() {
        return ADR_UP_SUM_ORDER_1_20;
    }

    public void setADR_UP_SUM_ORDER_1_20(BigDecimal ADR_UP_SUM_ORDER_1_20) {
        this.ADR_UP_SUM_ORDER_1_20 = ADR_UP_SUM_ORDER_1_20;
    }

    public BigDecimal getADR_UP_SUM_ORDER_20_40() {
        return ADR_UP_SUM_ORDER_20_40;
    }

    public void setADR_UP_SUM_ORDER_20_40(BigDecimal ADR_UP_SUM_ORDER_20_40) {
        this.ADR_UP_SUM_ORDER_20_40 = ADR_UP_SUM_ORDER_20_40;
    }

    public BigDecimal getADR_UP_SUM_ORDER_40_60() {
        return ADR_UP_SUM_ORDER_40_60;
    }

    public void setADR_UP_SUM_ORDER_40_60(BigDecimal ADR_UP_SUM_ORDER_40_60) {
        this.ADR_UP_SUM_ORDER_40_60 = ADR_UP_SUM_ORDER_40_60;
    }

    public BigDecimal getADR_UP_SUM_1_60() {
        return ADR_UP_SUM_1_60;
    }

    public void setADR_UP_SUM_1_60(BigDecimal ADR_UP_SUM_1_60) {
        this.ADR_UP_SUM_1_60 = ADR_UP_SUM_1_60;
    }

    public BigDecimal getADR_UP_SUM_ORDER_1_60() {
        return ADR_UP_SUM_ORDER_1_60;
    }

    public void setADR_UP_SUM_ORDER_1_60(BigDecimal ADR_UP_SUM_ORDER_1_60) {
        this.ADR_UP_SUM_ORDER_1_60 = ADR_UP_SUM_ORDER_1_60;
    }

    public BigDecimal getADR_UP_SUM_1_1() {
        return ADR_UP_SUM_1_1;
    }

    public void setADR_UP_SUM_1_1(BigDecimal ADR_UP_SUM_1_1) {
        this.ADR_UP_SUM_1_1 = ADR_UP_SUM_1_1;
    }

    public BigDecimal getADR_UP_SUM_1_2() {
        return ADR_UP_SUM_1_2;
    }

    public void setADR_UP_SUM_1_2(BigDecimal ADR_UP_SUM_1_2) {
        this.ADR_UP_SUM_1_2 = ADR_UP_SUM_1_2;
    }

    public BigDecimal getADR_UP_SUM_1_3() {
        return ADR_UP_SUM_1_3;
    }

    public void setADR_UP_SUM_1_3(BigDecimal ADR_UP_SUM_1_3) {
        this.ADR_UP_SUM_1_3 = ADR_UP_SUM_1_3;
    }
}
