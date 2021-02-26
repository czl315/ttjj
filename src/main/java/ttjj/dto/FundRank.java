package ttjj.dto;

import java.math.BigDecimal;

/**
 * 基金涨跌排行
 * https://fundapi.eastmoney.com/fundtradenew.aspx?ft=gp&sc=r&st=desc&pi=1&pn=100&cp=&ct=&cd=&ms=&fr=&plevel=&fst=&ftype=&fr1=&fl=0&isab=1
 * 005968|创金合信工业周期股票A|股票型|2021-02-25|3.2318|-0.64|-12.40|-5.99|37.35|65.78|132.91|227.24||14.89|223.18|3|1|1|0.15|0||080|1|1|100元|1.50%|0.15%|0.15%|1
 */
public class FundRank {
    /**
     * id
     */
    private int id;
    /**
     * num 排名号
     */
    private int num;
    /**
     * 基金编码
     */
    private String fundCode;
    /**
     * 基金信息
     */
    private String fundInfo;
    /**
     * 类型
     */
    private String bizTy;
    /**
     * periodDate 时期
     */
    private String periodDate;
    /**
     * periodDate 周期类型
     */
    private String periodTy;
    /**
     * net 确认净值
     */
    private String net;
    /**
     * growthDay 增长率-日
     */
    private BigDecimal growth1;
    /**
     * growthDay 增长率-周
     */
    private BigDecimal growth7;
    /**
     * growthDay 增长率-1个月
     */
    private BigDecimal growth30;
    /**
     * growthDay 增长率
     */
    private BigDecimal growth90;
    /**
     * growthDay 增长率
     */
    private BigDecimal growth180;
    /**
     * growthDay 增长率
     */
    private BigDecimal growth360;
    /**
     * growthDay 增长率
     */
    private BigDecimal growth720;
    /**
     * growthDay 增长率
     */
    private BigDecimal growth1080;
    /**
     * growthDay 增长率-今年
     */
    private BigDecimal growthCurYear;
    /**
     * growthDay 增长率-成立
     */
    private BigDecimal growthEstablish;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundInfo() {
        return fundInfo;
    }

    public void setFundInfo(String fundInfo) {
        this.fundInfo = fundInfo;
    }

    public String getBizTy() {
        return bizTy;
    }

    public void setBizTy(String bizTy) {
        this.bizTy = bizTy;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }

    public String getPeriodTy() {
        return periodTy;
    }

    public void setPeriodTy(String periodTy) {
        this.periodTy = periodTy;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public BigDecimal getGrowth1() {
        return growth1;
    }

    public void setGrowth1(BigDecimal growth1) {
        this.growth1 = growth1;
    }

    public BigDecimal getGrowth7() {
        return growth7;
    }

    public void setGrowth7(BigDecimal growth7) {
        this.growth7 = growth7;
    }

    public BigDecimal getGrowth30() {
        return growth30;
    }

    public void setGrowth30(BigDecimal growth30) {
        this.growth30 = growth30;
    }

    public BigDecimal getGrowth90() {
        return growth90;
    }

    public void setGrowth90(BigDecimal growth90) {
        this.growth90 = growth90;
    }

    public BigDecimal getGrowth180() {
        return growth180;
    }

    public void setGrowth180(BigDecimal growth180) {
        this.growth180 = growth180;
    }

    public BigDecimal getGrowth360() {
        return growth360;
    }

    public void setGrowth360(BigDecimal growth360) {
        this.growth360 = growth360;
    }

    public BigDecimal getGrowth720() {
        return growth720;
    }

    public void setGrowth720(BigDecimal growth720) {
        this.growth720 = growth720;
    }

    public BigDecimal getGrowth1080() {
        return growth1080;
    }

    public void setGrowth1080(BigDecimal growth1080) {
        this.growth1080 = growth1080;
    }

    public BigDecimal getGrowthCurYear() {
        return growthCurYear;
    }

    public void setGrowthCurYear(BigDecimal growthCurYear) {
        this.growthCurYear = growthCurYear;
    }

    public BigDecimal getGrowthEstablish() {
        return growthEstablish;
    }

    public void setGrowthEstablish(BigDecimal growthEstablish) {
        this.growthEstablish = growthEstablish;
    }
}
