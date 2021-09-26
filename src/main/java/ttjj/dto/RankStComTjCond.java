package ttjj.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 排行-股票-查询条件
 *
 * @author chenzhiLong
 * @date
 */
public class RankStComTjCond {
    /**
     * date 日期
     */
    private String date;
    /**
     * type 类型：bk-板块；gn-概念；etf；
     */
    private String type;
    /**
     * type_name 类型-板块名称
     */
    private String type_name;
    /**
     * orderNum 排序号
     */
    private Long orderNum;
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
    private String plate;

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
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
}
