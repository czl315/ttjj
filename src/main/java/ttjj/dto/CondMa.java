package ttjj.dto;

import java.util.List;
import java.util.Map;

/**
 * 查询均线条件
 *
 * @author Administrator
 * @date 2022-07-07 13:41
 */
public class CondMa {
    /**
     * mapStock 证券编码列表
     */
    private Map<String, String> mapStock;

    /**
     * date 查询日期
     */
    private String date;
    /**
     * spDate 特定日期
     */
    private String spDate;
    /**
     * orderField 排序字段
     */
    private String orderField;
    /**
     * isOrderDesc 是否倒序
     */
    private Boolean isOrderDesc;
    /**
     * isShowPriceArea 是否查询价格区间
     */
    private Boolean isShowPriceArea;
    /**
     * isShowUpMa 是否查询向上突破均线
     */
    private Boolean isShowUpMa;
    /**
     * isShowDownMa 是否查询向下跌破均线
     */
    private Boolean isShowDownMa;
    /**
     * isShowFlowIn 是否查询资金流入
     */
    private Boolean isShowFlowIn;
    /**
     * isFindKline 是否查询今日k线
     */
    private Boolean isFindKline;
    /**
     * kltList 周期列表
     */
    private List<String> kltList;

    public Map<String, String> getMapStock() {
        return mapStock;
    }

    public void setMapStock(Map<String, String> mapStock) {
        this.mapStock = mapStock;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSpDate() {
        return spDate;
    }

    public void setSpDate(String spDate) {
        this.spDate = spDate;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public Boolean getOrderDesc() {
        return isOrderDesc;
    }

    public void setOrderDesc(Boolean orderDesc) {
        isOrderDesc = orderDesc;
    }

    public Boolean getShowPriceArea() {
        return isShowPriceArea;
    }

    public void setShowPriceArea(Boolean showPriceArea) {
        isShowPriceArea = showPriceArea;
    }

    public Boolean getShowUpMa() {
        return isShowUpMa;
    }

    public void setShowUpMa(Boolean showUpMa) {
        isShowUpMa = showUpMa;
    }

    public Boolean getShowDownMa() {
        return isShowDownMa;
    }

    public void setShowDownMa(Boolean showDownMa) {
        isShowDownMa = showDownMa;
    }

    public Boolean getShowFlowIn() {
        return isShowFlowIn;
    }

    public void setShowFlowIn(Boolean showFlowIn) {
        isShowFlowIn = showFlowIn;
    }

    public Boolean getFindKline() {
        return isFindKline;
    }

    public void setFindKline(Boolean findKline) {
        isFindKline = findKline;
    }

    public List<String> getKltList() {
        return kltList;
    }

    public void setKltList(List<String> kltList) {
        this.kltList = kltList;
    }
}
