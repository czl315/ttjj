package ttjj.dto;

import ttjj.db.AssetPositionDb;

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
     * 我的仓位
     */
    private Map<String, AssetPositionDb> mapMyPosition;

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
     * 是否查询向下跌破均线-最高净值
     */
    private Boolean isShowBreakDownMaMax;
    /**
     * 是否查询向上涨破均线-最低净值
     */
    private Boolean isShowBreakUpMaMin;
    /**
     * isShowFlowIn 是否查询资金流入
     */
    private Boolean isShowFlowIn;
    /**
     * isFindKline 是否查询今日k线
     */
    private Boolean isFindKline;
    /**
     * 是否显示日最低点、最高点
     */
    private Boolean showDateMinMax;
    /**
     * 是否显示我的仓位
     */
    private Boolean isShowMyPosition;
    /**
     * 是否显示-高点回撤、低点上涨、最高涨幅、最低跌幅
     */
    private Boolean isShowMaxMin;
    /**
     * kltList 周期列表
     */
    private List<String> kltList;
    /**
     * K线天数
     */
    private Integer days;

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

    public Map<String, AssetPositionDb> getMapMyPosition() {
        return mapMyPosition;
    }

    public void setMapMyPosition(Map<String, AssetPositionDb> mapMyAsset) {
        this.mapMyPosition = mapMyAsset;
    }

    public Boolean getShowMyPosition() {
        return isShowMyPosition;
    }

    public void setShowMyPosition(Boolean showMyPosition) {
        isShowMyPosition = showMyPosition;
    }

    public Map<String, String> getMapStock() {
        return mapStock;
    }

    public void setMapStock(Map<String, String> mapStock) {
        this.mapStock = mapStock;
    }

    public Boolean getShowDateMinMax() {
        return showDateMinMax;
    }

    public void setShowDateMinMax(Boolean showDateMinMax) {
        this.showDateMinMax = showDateMinMax;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Boolean getShowMaxMin() {
        return isShowMaxMin;
    }

    public void setShowMaxMin(Boolean showMaxMin) {
        isShowMaxMin = showMaxMin;
    }

    public Boolean getShowBreakDownMaMax() {
        return isShowBreakDownMaMax;
    }

    public void setShowBreakDownMaMax(Boolean showBreakDownMaMax) {
        isShowBreakDownMaMax = showBreakDownMaMax;
    }

    public Boolean getShowBreakUpMaMin() {
        return isShowBreakUpMaMin;
    }

    public void setShowBreakUpMaMin(Boolean showBreakUpMaMin) {
        isShowBreakUpMaMin = showBreakUpMaMin;
    }
}
