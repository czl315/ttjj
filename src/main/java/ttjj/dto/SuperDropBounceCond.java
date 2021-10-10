package ttjj.dto;

import java.math.BigDecimal;

/**
 * 查询条件-超跌反弹
 *
 * @author chenzhiLong
 * @date
 */
public class SuperDropBounceCond {
    /**
     * code
     */
    private String code;
    /**
     * name
     */
    private String name;
    /**
     * type 类型：bk-板块；gn-概念；etf；
     */
    private String type;
    /**
     * type_name 类型-板块名称
     */
    private String type_name;
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
     * curDayAdd5
     */
    private String curDayAdd5;
    /**
     * curDayAdd10
     */
    private String curDayAdd10;
    /**
     * curDayAdd20
     */
    private String curDayAdd20;
    /**
     * curDayAdd30
     */
    private String curDayAdd30;
    /**
     * curDayAdd60
     */
    private String curDayAdd60;
    /**
     * curDaySub1 当前日减1
     */
    private String curDaySub1;
    private String curDaySub2;
    private String curDaySub3;
    private String curDaySub4;
    private String curDaySub5;
    private String curDaySub6;

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
     * orderBy 排序
     */
    private String orderBy;
    /**
     * qrrDaySub1 量比最低-日减n
     */
    private BigDecimal qrrMaxDaySub1;
    private BigDecimal qrrMaxDaySub2;
    private BigDecimal qrrMaxDaySub3;
    private BigDecimal qrrMaxDaySub4;
    private BigDecimal qrrMaxDaySub5;
    /**
     * adrDay0 涨跌限定-最低-日加n
     */
    private BigDecimal adrMinDay0;
    /**
     * adrMaxDaySub1 涨跌限定-最高-日减n
     */
    private BigDecimal adrMaxDaySub1;
    private BigDecimal adrMaxDaySub2;
    private BigDecimal adrMaxDaySub3;
    private BigDecimal adrMaxDaySub4;

    public BigDecimal getAdrMaxDaySub2() {
        return adrMaxDaySub2;
    }

    public void setAdrMaxDaySub2(BigDecimal adrMaxDaySub2) {
        this.adrMaxDaySub2 = adrMaxDaySub2;
    }

    public BigDecimal getAdrMaxDaySub3() {
        return adrMaxDaySub3;
    }

    public void setAdrMaxDaySub3(BigDecimal adrMaxDaySub3) {
        this.adrMaxDaySub3 = adrMaxDaySub3;
    }

    public BigDecimal getAdrMaxDaySub4() {
        return adrMaxDaySub4;
    }

    public void setAdrMaxDaySub4(BigDecimal adrMaxDaySub4) {
        this.adrMaxDaySub4 = adrMaxDaySub4;
    }

    public BigDecimal getAdrMaxDaySub5() {
        return adrMaxDaySub5;
    }

    public void setAdrMaxDaySub5(BigDecimal adrMaxDaySub5) {
        this.adrMaxDaySub5 = adrMaxDaySub5;
    }

    private BigDecimal adrMaxDaySub5;

    public BigDecimal getQrrMaxDaySub2() {
        return qrrMaxDaySub2;
    }

    public void setQrrMaxDaySub2(BigDecimal qrrMaxDaySub2) {
        this.qrrMaxDaySub2 = qrrMaxDaySub2;
    }

    public BigDecimal getQrrMaxDaySub3() {
        return qrrMaxDaySub3;
    }

    public void setQrrMaxDaySub3(BigDecimal qrrMaxDaySub3) {
        this.qrrMaxDaySub3 = qrrMaxDaySub3;
    }

    public BigDecimal getQrrMaxDaySub4() {
        return qrrMaxDaySub4;
    }

    public void setQrrMaxDaySub4(BigDecimal qrrMaxDaySub4) {
        this.qrrMaxDaySub4 = qrrMaxDaySub4;
    }

    public BigDecimal getQrrMaxDaySub5() {
        return qrrMaxDaySub5;
    }

    public void setQrrMaxDaySub5(BigDecimal qrrMaxDaySub5) {
        this.qrrMaxDaySub5 = qrrMaxDaySub5;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCurDayAdd5() {
        return curDayAdd5;
    }

    public void setCurDayAdd5(String curDayAdd5) {
        this.curDayAdd5 = curDayAdd5;
    }

    public String getCurDayAdd10() {
        return curDayAdd10;
    }

    public void setCurDayAdd10(String curDayAdd10) {
        this.curDayAdd10 = curDayAdd10;
    }

    public String getCurDayAdd20() {
        return curDayAdd20;
    }

    public void setCurDayAdd20(String curDayAdd20) {
        this.curDayAdd20 = curDayAdd20;
    }

    public String getCurDayAdd30() {
        return curDayAdd30;
    }

    public void setCurDayAdd30(String curDayAdd30) {
        this.curDayAdd30 = curDayAdd30;
    }

    public String getCurDayAdd60() {
        return curDayAdd60;
    }

    public void setCurDayAdd60(String curDayAdd60) {
        this.curDayAdd60 = curDayAdd60;
    }

    public String getCurDaySub1() {
        return curDaySub1;
    }

    public void setCurDaySub1(String curDaySub1) {
        this.curDaySub1 = curDaySub1;
    }

    public String getCurDaySub2() {
        return curDaySub2;
    }

    public void setCurDaySub2(String curDaySub2) {
        this.curDaySub2 = curDaySub2;
    }

    public String getCurDaySub3() {
        return curDaySub3;
    }

    public void setCurDaySub3(String curDaySub3) {
        this.curDaySub3 = curDaySub3;
    }

    public String getCurDaySub4() {
        return curDaySub4;
    }

    public void setCurDaySub4(String curDaySub4) {
        this.curDaySub4 = curDaySub4;
    }

    public String getCurDaySub5() {
        return curDaySub5;
    }

    public void setCurDaySub5(String curDaySub5) {
        this.curDaySub5 = curDaySub5;
    }

    public String getCurDaySub6() {
        return curDaySub6;
    }

    public void setCurDaySub6(String curDaySub6) {
        this.curDaySub6 = curDaySub6;
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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public BigDecimal getQrrMaxDaySub1() {
        return qrrMaxDaySub1;
    }

    public void setQrrMaxDaySub1(BigDecimal qrrMaxDaySub1) {
        this.qrrMaxDaySub1 = qrrMaxDaySub1;
    }

    public BigDecimal getAdrMinDay0() {
        return adrMinDay0;
    }

    public void setAdrMinDay0(BigDecimal adrMinDay0) {
        this.adrMinDay0 = adrMinDay0;
    }

    public BigDecimal getAdrMaxDaySub1() {
        return adrMaxDaySub1;
    }

    public void setAdrMaxDaySub1(BigDecimal adrMaxDaySub1) {
        this.adrMaxDaySub1 = adrMaxDaySub1;
    }
}
