package ttjj.db;

import java.math.BigDecimal;

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
    private Double f2;
    /**
     * f3 涨跌幅
     */
    private BigDecimal f3;
    /**
     * f4 涨跌数值
     */
    private Double f4;
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
    private Double f7;
    /**
     * f8 换手
     */
    private Double f8;
    /**
     * f9 市盈(动)
     */
    private Double f9;
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
    private Double f15;
    /**
     * f16 最低
     */
    private Double f16;
    /**
     * f17 今日开盘
     */
    private Double f17;
    /**
     * f18 昨日收盘
     */
    private Double f18;
    /**
     * f20 总市值
     */
    private BigDecimal f20;
    /**
     * f21 流通市值
     */
    private Long f21;

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

    public Double getF2() {
        return f2;
    }

    public void setF2(Double f2) {
        this.f2 = f2;
    }

    public BigDecimal getF3() {
        return f3;
    }

    public void setF3(BigDecimal f3) {
        this.f3 = f3;
    }

    public Double getF4() {
        return f4;
    }

    public void setF4(Double f4) {
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

    public Double getF7() {
        return f7;
    }

    public void setF7(Double f7) {
        this.f7 = f7;
    }

    public Double getF8() {
        return f8;
    }

    public void setF8(Double f8) {
        this.f8 = f8;
    }

    public Double getF9() {
        return f9;
    }

    public void setF9(Double f9) {
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

    public Double getF15() {
        return f15;
    }

    public void setF15(Double f15) {
        this.f15 = f15;
    }

    public Double getF16() {
        return f16;
    }

    public void setF16(Double f16) {
        this.f16 = f16;
    }

    public Double getF17() {
        return f17;
    }

    public void setF17(Double f17) {
        this.f17 = f17;
    }

    public Double getF18() {
        return f18;
    }

    public void setF18(Double f18) {
        this.f18 = f18;
    }

    public BigDecimal getF20() {
        return f20;
    }

    public void setF20(BigDecimal f20) {
        this.f20 = f20;
    }

    public Long getF21() {
        return f21;
    }

    public void setF21(Long f21) {
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
}
