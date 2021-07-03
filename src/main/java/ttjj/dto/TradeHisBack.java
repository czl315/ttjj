package ttjj.dto;

import java.math.BigDecimal;

/**
 * 交易历史回测
 * @author Administrator
 * @date 2021-06-28 23:25
 */
public class TradeHisBack {
    /**
     * 限定金额
     */
    private BigDecimal lmtAmt;
    /**
     * eachNum 每份数量
     */
    private BigDecimal eachNum;
    /**
     * 现金
     */
    private BigDecimal cash;
    /**
     * 持有数量
     */
    private BigDecimal holdNum;
    /**
     * 持有-成本价
     */
    private BigDecimal holdAmt;
    /**
     * 当前价格
     */
    private BigDecimal nowPrice;
    /**
     * 日期时间
     */
    private String ktime;

    /**
     * minPrice 最低价
     */
    private BigDecimal minPrice;
    /**
     * maxPrice 最高价
     */
    private BigDecimal maxPrice;
    /**
     * upFactor 上涨因子
     */
    private BigDecimal upFactor;
    /**
     * downFactor 下跌因子
     */
    private BigDecimal downFactor;

    public BigDecimal getHoldNum() {
        return holdNum;
    }

    public void setHoldNum(BigDecimal holdNum) {
        this.holdNum = holdNum;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getHoldAmt() {
        return holdAmt;
    }

    public void setHoldAmt(BigDecimal holdAmt) {
        this.holdAmt = holdAmt;
    }

    public BigDecimal getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(BigDecimal nowPrice) {
        this.nowPrice = nowPrice;
    }

    public String getKtime() {
        return ktime;
    }

    public void setKtime(String ktime) {
        this.ktime = ktime;
    }

    public BigDecimal getLmtAmt() {
        return lmtAmt;
    }

    public void setLmtAmt(BigDecimal lmtAmt) {
        this.lmtAmt = lmtAmt;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getUpFactor() {
        return upFactor;
    }

    public void setUpFactor(BigDecimal upFactor) {
        this.upFactor = upFactor;
    }

    public BigDecimal getDownFactor() {
        return downFactor;
    }

    public void setDownFactor(BigDecimal downFactor) {
        this.downFactor = downFactor;
    }

    public BigDecimal getEachNum() {
        return eachNum;
    }

    public void setEachNum(BigDecimal eachNum) {
        this.eachNum = eachNum;
    }
}
