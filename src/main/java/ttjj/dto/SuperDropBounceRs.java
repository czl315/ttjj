package ttjj.dto;

import java.math.BigDecimal;

/**
 * 超跌反弹-查询结果
 *
 * @author chenzhiLong
 * @date
 */
public class SuperDropBounceRs extends  SuperDropBounceCond{
    /**
     * adrSum 日加0涨跌合计
     */
    private BigDecimal adrSum;
    /**
     * adrSum1日加1涨跌合计
     */
    private BigDecimal adrSum1;
    /**
     * adrSum2 日加2涨跌合计
     */
    private BigDecimal adrSum2;
    /**
     * adrSum3 日加3涨跌合计
     */
    private BigDecimal adrSum3;
    /**
     * adrSum5
     */
    private BigDecimal adrSum5;
    /**
     * adrSum10
     */
    private BigDecimal adrSum10;
    /**
     * adrSum20
     */
    private BigDecimal adrSum20;
    /**
     * adrSum30
     */
    private BigDecimal adrSum30;
    /**
     * adrSum60
     */
    private BigDecimal adrSum60;
    /**
     * adrMax0 当日加0涨幅最高
     */
    private BigDecimal adrMax0;
    /**
     * adrMin0 当日加0跌幅最高
     */
    private BigDecimal adrMin0;
    /**
     * adrDaySub1 日减n涨跌
     */
    private BigDecimal adrDaySub1;
    /**
     * adrDaySub2
     */
    private BigDecimal adrDaySub2;
    /**
     * adrDaySub3
     */
    private BigDecimal adrDaySub3;
    /**
     * adrDaySub4
     */
    private BigDecimal adrDaySub4;
    /**
     * adrDaySub5
     */
    private BigDecimal adrDaySub5;
    /**
     * adrDaySub6
     */
    private BigDecimal adrDaySub6;
    /**
     * pe 市盈率
     */
    private BigDecimal pe;
    /**
     * roe 净资产收益率
     */
    private BigDecimal roe;
    /**
     * marketValue 市值
     */
    private BigDecimal marketValue;
    /**
     * qrr 量比-日加0
     */
    private BigDecimal qrr0;
    /**
     * qrrSub0 量比-日减0
     */
    private BigDecimal qrrSub1;
    /**
     * qrrSub2
     */
    private BigDecimal qrrSub2;
    /**
     * qrrSub3
     */
    private BigDecimal qrrSub3;
    /**
     * qrrSub4
     */
    private BigDecimal qrrSub4;
    /**
     * qrrSub5
     */
    private BigDecimal qrrSub5;
    /**
     * qrrSub6
     */
    private BigDecimal qrrSub6;

    public BigDecimal getAdrSum() {
        return adrSum;
    }

    public void setAdrSum(BigDecimal adrSum) {
        this.adrSum = adrSum;
    }

    public BigDecimal getAdrSum1() {
        return adrSum1;
    }

    public void setAdrSum1(BigDecimal adrSum1) {
        this.adrSum1 = adrSum1;
    }

    public BigDecimal getAdrSum2() {
        return adrSum2;
    }

    public void setAdrSum2(BigDecimal adrSum2) {
        this.adrSum2 = adrSum2;
    }

    public BigDecimal getAdrSum3() {
        return adrSum3;
    }

    public void setAdrSum3(BigDecimal adrSum3) {
        this.adrSum3 = adrSum3;
    }

    public BigDecimal getAdrSum5() {
        return adrSum5;
    }

    public void setAdrSum5(BigDecimal adrSum5) {
        this.adrSum5 = adrSum5;
    }

    public BigDecimal getAdrSum10() {
        return adrSum10;
    }

    public void setAdrSum10(BigDecimal adrSum10) {
        this.adrSum10 = adrSum10;
    }

    public BigDecimal getAdrSum20() {
        return adrSum20;
    }

    public void setAdrSum20(BigDecimal adrSum20) {
        this.adrSum20 = adrSum20;
    }

    public BigDecimal getAdrSum30() {
        return adrSum30;
    }

    public void setAdrSum30(BigDecimal adrSum30) {
        this.adrSum30 = adrSum30;
    }

    public BigDecimal getAdrSum60() {
        return adrSum60;
    }

    public void setAdrSum60(BigDecimal adrSum60) {
        this.adrSum60 = adrSum60;
    }

    public BigDecimal getAdrMax0() {
        return adrMax0;
    }

    public void setAdrMax0(BigDecimal adrMax0) {
        this.adrMax0 = adrMax0;
    }

    public BigDecimal getAdrMin0() {
        return adrMin0;
    }

    public void setAdrMin0(BigDecimal adrMin0) {
        this.adrMin0 = adrMin0;
    }

    public BigDecimal getAdrDaySub1() {
        return adrDaySub1;
    }

    public void setAdrDaySub1(BigDecimal adrDaySub1) {
        this.adrDaySub1 = adrDaySub1;
    }

    public BigDecimal getAdrDaySub2() {
        return adrDaySub2;
    }

    public void setAdrDaySub2(BigDecimal adrDaySub2) {
        this.adrDaySub2 = adrDaySub2;
    }

    public BigDecimal getAdrDaySub3() {
        return adrDaySub3;
    }

    public void setAdrDaySub3(BigDecimal adrDaySub3) {
        this.adrDaySub3 = adrDaySub3;
    }

    public BigDecimal getAdrDaySub4() {
        return adrDaySub4;
    }

    public void setAdrDaySub4(BigDecimal adrDaySub4) {
        this.adrDaySub4 = adrDaySub4;
    }

    public BigDecimal getAdrDaySub5() {
        return adrDaySub5;
    }

    public void setAdrDaySub5(BigDecimal adrDaySub5) {
        this.adrDaySub5 = adrDaySub5;
    }

    public BigDecimal getAdrDaySub6() {
        return adrDaySub6;
    }

    public void setAdrDaySub6(BigDecimal adrDaySub6) {
        this.adrDaySub6 = adrDaySub6;
    }

    public BigDecimal getPe() {
        return pe;
    }

    public void setPe(BigDecimal pe) {
        this.pe = pe;
    }

    public BigDecimal getRoe() {
        return roe;
    }

    public void setRoe(BigDecimal roe) {
        this.roe = roe;
    }

    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    public BigDecimal getQrr0() {
        return qrr0;
    }

    public void setQrr0(BigDecimal qrr0) {
        this.qrr0 = qrr0;
    }

    public BigDecimal getQrrSub1() {
        return qrrSub1;
    }

    public void setQrrSub1(BigDecimal qrrSub1) {
        this.qrrSub1 = qrrSub1;
    }

    public BigDecimal getQrrSub2() {
        return qrrSub2;
    }

    public void setQrrSub2(BigDecimal qrrSub2) {
        this.qrrSub2 = qrrSub2;
    }

    public BigDecimal getQrrSub3() {
        return qrrSub3;
    }

    public void setQrrSub3(BigDecimal qrrSub3) {
        this.qrrSub3 = qrrSub3;
    }

    public BigDecimal getQrrSub4() {
        return qrrSub4;
    }

    public void setQrrSub4(BigDecimal qrrSub4) {
        this.qrrSub4 = qrrSub4;
    }

    public BigDecimal getQrrSub5() {
        return qrrSub5;
    }

    public void setQrrSub5(BigDecimal qrrSub5) {
        this.qrrSub5 = qrrSub5;
    }

    public BigDecimal getQrrSub6() {
        return qrrSub6;
    }

    public void setQrrSub6(BigDecimal qrrSub6) {
        this.qrrSub6 = qrrSub6;
    }
}
