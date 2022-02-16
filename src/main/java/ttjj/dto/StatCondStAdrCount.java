package ttjj.dto;

import java.math.BigDecimal;
import java.util.List;


/**
 * StatCondStAdrCount简介
 *  统计数据-查询条件-股票涨跌次数
 * @author Administrator
 * @date 2022-01-21 10:31
 */
public class StatCondStAdrCount {
    /**
     * code
     */
    private String code;
    /**
     * name
     */
    private String name;
    /**
     * klt
     */
    private String klt;

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
    private Long f139;
    /**
     * 涨跌率-最低
     */
    private BigDecimal adrMin;

    /**
     * stCodeList 限定股票代码列表
     */
    private List<String> stCodeList;

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

    public Long getF139() {
        return f139;
    }

    public void setF139(Long f139) {
        this.f139 = f139;
    }

    public BigDecimal getAdrMin() {
        return adrMin;
    }

    public void setAdrMin(BigDecimal adrMin) {
        this.adrMin = adrMin;
    }

    public List<String> getStCodeList() {
        return stCodeList;
    }

    public void setStCodeList(List<String> stCodeList) {
        this.stCodeList = stCodeList;
    }

    public String getKlt() {
        return klt;
    }

    public void setKlt(String klt) {
        this.klt = klt;
    }
}
