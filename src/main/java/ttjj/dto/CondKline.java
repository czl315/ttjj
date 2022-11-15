package ttjj.dto;

import java.math.BigDecimal;
import java.util.List;


/**
 * 查询条件-
 *
 * @author Administrator
 * @date 2022-08-04 11:22
 */
public class CondKline extends Kline {
    /**
     * mvMin 最低市值
     */
    private BigDecimal mvMin;
    /**
     * mvMax
     */
    private BigDecimal mvMax;
    /**
     * 涨跌率-最低
     */
    private BigDecimal adrMin;
    /**
     * begDate 开始日期
     */
    private String begDate;
    /**
     * endDate 结束日期
     */
    private String endDate;
    /**
     * stCodeList 限定股票代码列表
     */
    private List<String> stCodeList;
    /**
     * 净值-开始日期
     */
    private BigDecimal begDateF18;
    /**
     * 净值-结束日期
     */
    private BigDecimal endDateF2;
    /**
     * 区间涨幅
     */
    private BigDecimal areaF3;
    /**
     * 流市比：主力净流入与市值的百分比
     */
    private BigDecimal flowInMainPct;
    /**
     * orderBy 排序
     */
    private String orderBy;

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

    public List<String> getStCodeList() {
        return stCodeList;
    }

    public void setStCodeList(List<String> stCodeList) {
        this.stCodeList = stCodeList;
    }

    public BigDecimal getAdrMin() {
        return adrMin;
    }

    public void setAdrMin(BigDecimal adrMin) {
        this.adrMin = adrMin;
    }

    public BigDecimal getMvMin() {
        return mvMin;
    }

    public void setMvMin(BigDecimal mvMin) {
        this.mvMin = mvMin;
    }

    public BigDecimal getMvMax() {
        return mvMax;
    }

    public void setMvMax(BigDecimal mvMax) {
        this.mvMax = mvMax;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public BigDecimal getBegDateF18() {
        return begDateF18;
    }

    public void setBegDateF18(BigDecimal begDateF18) {
        this.begDateF18 = begDateF18;
    }

    public BigDecimal getEndDateF2() {
        return endDateF2;
    }

    public void setEndDateF2(BigDecimal endDateF2) {
        this.endDateF2 = endDateF2;
    }

    public BigDecimal getAreaF3() {
        return areaF3;
    }

    public void setAreaF3(BigDecimal areaF3) {
        this.areaF3 = areaF3;
    }

    public BigDecimal getFlowInMainPct() {
        return flowInMainPct;
    }

    public void setFlowInMainPct(BigDecimal flowInMainPct) {
        this.flowInMainPct = flowInMainPct;
    }
}
