package ttjj.dto;

import java.math.BigDecimal;

/**
 * 均线突破-向上-查询条件
 *
 * @author chenzhiLong
 * @date
 */
public class MaBreakUpCond {
    /**
     * curDay 当前日
     */
    private String curDay;
    /**
     * curDayAdd1 当前日加1
     */
    private String curDayAdd1;
    /**
     * curDayAdd2 当前日加2
     */
    private String curDayAdd2;
    /**
     * curDayAdd3 当前日加3
     */
    private String curDayAdd3;
    /**
     * curDaySub1 当前日减1
     */
    private String curDaySub1;
    /**
     * type 类型：bk-板块；gn-概念；etf；
     */
    private String type;
    /**
     * type_name 类型-板块名称
     */
    private String type_name;
    /**
     * f20 市值限定
     */
    private Long f20;
    /**
     * marketValueMin 市值限定-最低
     */
    private BigDecimal marketValueMin;
    /**
     * marketValueMax 市值限定-最高
     */
    private BigDecimal marketValueMax;
    /**
     * begDate 开始日期
     */
    private String begDate;
    /**
     * endDate 结束日期
     */
    private String endDate;
    /**
     * plate 板块 f139 2-A股主板(00XXXX/60XXXX);3-B股(200XXX/900XXX);5-创业板(30XXXX);32-科创板(688XXX);33-存托凭证(689XXX,689是科创板专门为CDR存托凭证公司)
     */
    private long f139;
    /**
     * goodRateCurDayLimitDown 乖离率-当前日-下限
     */
    private BigDecimal goodRateCurDayLimitDown;
    /**
     * goodRateCurDayLimitUp 乖离率-当前日-上限
     */
    private BigDecimal goodRateCurDayLimitUp;
    /**
     * goodRateDaySub1 乖离率-当前日减1
     */
    private BigDecimal goodRateDaySub1;
    /**
     * goodRateDaySub2 乖离率-当前日减2
     */
    private BigDecimal goodRateDaySub2;
    /**
     * goodRateDaySub3 乖离率-当前日减3
     */
    private BigDecimal goodRateDaySub3;

    /**
     * orderBy 排序
     */
    private String orderBy;


    public String getCurDay() {
        return curDay;
    }

    public void setCurDay(String curDay) {
        this.curDay = curDay;
    }

    public String getCurDayAdd1() {
        return curDayAdd1;
    }

    public void setCurDayAdd1(String curDayAdd1) {
        this.curDayAdd1 = curDayAdd1;
    }

    public String getCurDayAdd2() {
        return curDayAdd2;
    }

    public void setCurDayAdd2(String curDayAdd2) {
        this.curDayAdd2 = curDayAdd2;
    }

    public String getCurDayAdd3() {
        return curDayAdd3;
    }

    public void setCurDayAdd3(String curDayAdd3) {
        this.curDayAdd3 = curDayAdd3;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Long getF20() {
        return f20;
    }

    public void setF20(Long f20) {
        this.f20 = f20;
    }

    public BigDecimal getMarketValueMin() {
        return marketValueMin;
    }

    public void setMarketValueMin(BigDecimal marketValueMin) {
        this.marketValueMin = marketValueMin;
    }

    public BigDecimal getMarketValueMax() {
        return marketValueMax;
    }

    public void setMarketValueMax(BigDecimal marketValueMax) {
        this.marketValueMax = marketValueMax;
    }

    public String getBegDate() {
        return begDate;
    }

    public void setBegDate(String begDate) {
        this.begDate = begDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getF139() {
        return f139;
    }

    public void setF139(long f139) {
        this.f139 = f139;
    }

    public BigDecimal getGoodRateDaySub1() {
        return goodRateDaySub1;
    }

    public void setGoodRateDaySub1(BigDecimal goodRateDaySub1) {
        this.goodRateDaySub1 = goodRateDaySub1;
    }

    public BigDecimal getGoodRateDaySub2() {
        return goodRateDaySub2;
    }

    public void setGoodRateDaySub2(BigDecimal goodRateDaySub2) {
        this.goodRateDaySub2 = goodRateDaySub2;
    }

    public BigDecimal getGoodRateDaySub3() {
        return goodRateDaySub3;
    }

    public void setGoodRateDaySub3(BigDecimal goodRateDaySub3) {
        this.goodRateDaySub3 = goodRateDaySub3;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getCurDaySub1() {
        return curDaySub1;
    }

    public void setCurDaySub1(String curDaySub1) {
        this.curDaySub1 = curDaySub1;
    }

    public BigDecimal getGoodRateCurDayLimitDown() {
        return goodRateCurDayLimitDown;
    }

    public void setGoodRateCurDayLimitDown(BigDecimal goodRateCurDayLimitDown) {
        this.goodRateCurDayLimitDown = goodRateCurDayLimitDown;
    }

    public BigDecimal getGoodRateCurDayLimitUp() {
        return goodRateCurDayLimitUp;
    }

    public void setGoodRateCurDayLimitUp(BigDecimal goodRateCurDayLimitUp) {
        this.goodRateCurDayLimitUp = goodRateCurDayLimitUp;
    }
}
