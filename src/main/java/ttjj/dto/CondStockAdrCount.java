package ttjj.dto;

import ttjj.db.StockAdrCount;

import java.math.BigDecimal;
import java.util.List;

/**
 * 股票涨跌次数
 *
 * @author Administrator
 * @date 2022-05-09 23:47
 */
public class CondStockAdrCount extends StockAdrCount {
    /**
     * order_num 查询条件：排序号列表
     */
    private List<BigDecimal> orderNumList;

    /**
     * orderBy 排序字段
     */
    private String orderBy;

    /**
     * limitCount 限定查询个数
     */
    private Integer limitCount;
    /**
     * 是否更新-净值
     */
    private boolean updateNet;
    /**
     * 是否更新-上涨累计
     */
    private boolean updateSum;
    /**
     * 是否更新-上涨累计排序
     */
    private boolean updateOrder;
    /**
     * 是否查询-列表
     */
    private boolean findList;
    /**
     * 是否更新-超过均线
     */
    private boolean updateUpMa;
    /**
     * 是否更新-净值区间
     */
    private boolean updateNetArea;

    /**
     * mvMin 市值-最低
     */
    private BigDecimal mvMin;
    /**
     * mvMax 市值-最高
     */
    private BigDecimal mvMax;
    /**
     * 最低-上涨累计排序-1至60日
     */
    private BigDecimal adrUpSumOrder1to60Min;
    /**
     * 最高-上涨累计排序-1至60日
     */
    private BigDecimal adrUpSumOrder1to60Max;

    /**
     * 均线周期列表
     */
    private List<String> maKltList;
    /**
     * 超过均线列表-条件-或
     */
    private List<String> upMaKltOrList;

    /**
     * 业务列表
     */
    private List<String> bizList;

    /**
     * 均线之上-60-周线
     */
    private BigDecimal minMa60Up102;
    /**
     * minNetAreaDay5
     */
    private BigDecimal minNetAreaDay5;
    /**
     * maxNetAreaDay5
     */
    private BigDecimal maxNetAreaDay5;

    public BigDecimal getAdrUpSumOrder1to60Min() {
        return adrUpSumOrder1to60Min;
    }

    public void setAdrUpSumOrder1to60Min(BigDecimal adrUpSumOrder1to60Min) {
        this.adrUpSumOrder1to60Min = adrUpSumOrder1to60Min;
    }

    public BigDecimal getAdrUpSumOrder1to60Max() {
        return adrUpSumOrder1to60Max;
    }

    public void setAdrUpSumOrder1to60Max(BigDecimal adrUpSumOrder1to60Max) {
        this.adrUpSumOrder1to60Max = adrUpSumOrder1to60Max;
    }

    public Integer getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
    }


    public List<BigDecimal> getOrderNumList() {
        return orderNumList;
    }

    public void setOrderNumList(List<BigDecimal> orderNumList) {
        this.orderNumList = orderNumList;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isUpdateNet() {
        return updateNet;
    }

    public void setUpdateNet(boolean updateNet) {
        this.updateNet = updateNet;
    }

    public boolean isUpdateSum() {
        return updateSum;
    }

    public void setUpdateSum(boolean updateSum) {
        this.updateSum = updateSum;
    }

    public boolean isUpdateOrder() {
        return updateOrder;
    }

    public void setUpdateOrder(boolean updateOrder) {
        this.updateOrder = updateOrder;
    }

    public boolean isFindList() {
        return findList;
    }

    public void setFindList(boolean findList) {
        this.findList = findList;
    }

    public boolean isUpdateUpMa() {
        return updateUpMa;
    }

    public void setUpdateUpMa(boolean updateUpMa) {
        this.updateUpMa = updateUpMa;
    }

    public boolean isUpdateNetArea() {
        return updateNetArea;
    }

    public void setUpdateNetArea(boolean updateNetArea) {
        this.updateNetArea = updateNetArea;
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

    public List<String> getMaKltList() {
        return maKltList;
    }

    public void setMaKltList(List<String> maKltList) {
        this.maKltList = maKltList;
    }

    public List<String> getBizList() {
        return bizList;
    }

    public void setBizList(List<String> bizList) {
        this.bizList = bizList;
    }

    public List<String> getUpMaKltOrList() {
        return upMaKltOrList;
    }

    public void setUpMaKltOrList(List<String> upMaKltOrList) {
        this.upMaKltOrList = upMaKltOrList;
    }

    public BigDecimal getMinMa60Up102() {
        return minMa60Up102;
    }

    public void setMinMa60Up102(BigDecimal minMa60Up102) {
        this.minMa60Up102 = minMa60Up102;
    }

    public BigDecimal getMinNetAreaDay5() {
        return minNetAreaDay5;
    }

    public void setMinNetAreaDay5(BigDecimal minNetAreaDay5) {
        this.minNetAreaDay5 = minNetAreaDay5;
    }

    public BigDecimal getMaxNetAreaDay5() {
        return maxNetAreaDay5;
    }

    public void setMaxNetAreaDay5(BigDecimal maxNetAreaDay5) {
        this.maxNetAreaDay5 = maxNetAreaDay5;
    }
}
