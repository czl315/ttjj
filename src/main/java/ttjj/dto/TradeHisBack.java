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
     * 持有金额
     */
    private BigDecimal holdAmt;
    /**
     * totalAmt 总金额=持仓金额+现金
     */
    private BigDecimal totalAmt;
    /**
     * totalAmtOld 原有总资产
     */
    private BigDecimal totalAmtOld;
    /**
     * zdf 涨跌幅
     */
    private BigDecimal zdf;
    /**
     * zdfPoint 指数涨跌幅
     */
    private BigDecimal zdfPoint;

    /**
     * 当前价格
     */
    private BigDecimal nowPrice;
    /**
     * oldPrice 原有价格
     */
    private BigDecimal oldPrice;
    /**
     * 日期时间
     */
    private String ktime;
    /**
     * stPrice 标准价
     */
    private BigDecimal stPrice;
    /**
     * upFactor 上涨因子
     */
    private BigDecimal upFactor;
    /**
     * downFactor 下跌因子
     */
    private BigDecimal downFactor;
    /**
     * buyCount 买入次数
     */
    private BigDecimal buyCount;
    /**
     * sellCount 卖出次数
     */
    private BigDecimal sellCount;


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

    public BigDecimal getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(BigDecimal buyCount) {
        this.buyCount = buyCount;
    }

    public BigDecimal getSellCount() {
        return sellCount;
    }

    public void setSellCount(BigDecimal sellCount) {
        this.sellCount = sellCount;
    }

    public BigDecimal getStPrice() {
        return stPrice;
    }

    public void setStPrice(BigDecimal stPrice) {
        this.stPrice = stPrice;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public BigDecimal getTotalAmtOld() {
        return totalAmtOld;
    }

    public void setTotalAmtOld(BigDecimal totalAmtOld) {
        this.totalAmtOld = totalAmtOld;
    }

    public BigDecimal getZdf() {
        return zdf;
    }

    public void setZdf(BigDecimal zdf) {
        this.zdf = zdf;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public BigDecimal getZdfPoint() {
        return zdfPoint;
    }

    public void setZdfPoint(BigDecimal zdfPoint) {
        this.zdfPoint = zdfPoint;
    }
}
