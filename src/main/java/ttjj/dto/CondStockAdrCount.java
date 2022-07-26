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

    List<String> maKltList ;//均线周期列表

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
}
