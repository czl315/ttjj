package ttjj.dto;

import java.math.BigDecimal;

/**
 * K线返回结果
 * "2021-06-23 09:35,1.438,1.443,1.446,1.437,490887,70765420.000,0.63,0.49,0.007,0.64"
 * //  日期时间，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率
 */
public class Kline {
    /**
     * 日期时间
     */
    private String ktime;
    /**
     * zqdm 证券代码
     */
    private String zqdm;
    /**
     * zqmc 证券名称
     */
    private String zqmc;
    /**
     * 开盘价
     */
    private BigDecimal openAmt;
    /**
     * 收盘价
     */
    private BigDecimal closeAmt;
    /**
     * 收盘价-上一期
     */
    private BigDecimal closeLastAmt;
    /**
     * 最高
     */
    private BigDecimal maxAmt;
    /**
     * 最低
     */
    private BigDecimal minAmt;
    /**
     * 成交量
     */
    private BigDecimal cjl;
    /**
     * 成交额
     */
    private BigDecimal cje;
    /**
     * 振幅
     */
    private BigDecimal zhenFu;
    /**
     * 涨跌幅
     */
    private BigDecimal zhangDieFu;
    /**
     * 涨跌额
     */
    private BigDecimal zhangDieE;
    /**
     * 换手率
     */
    private BigDecimal huanShouLv;

    public String getKtime() {
        return ktime;
    }

    public void setKtime(String ktime) {
        this.ktime = ktime;
    }

    public BigDecimal getOpenAmt() {
        return openAmt;
    }

    public void setOpenAmt(BigDecimal openAmt) {
        this.openAmt = openAmt;
    }

    public BigDecimal getCloseAmt() {
        return closeAmt;
    }

    public void setCloseAmt(BigDecimal closeAmt) {
        this.closeAmt = closeAmt;
    }

    public BigDecimal getCloseLastAmt() {
        return closeLastAmt;
    }

    public void setCloseLastAmt(BigDecimal closeLastAmt) {
        this.closeLastAmt = closeLastAmt;
    }

    public BigDecimal getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(BigDecimal maxAmt) {
        this.maxAmt = maxAmt;
    }

    public BigDecimal getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(BigDecimal minAmt) {
        this.minAmt = minAmt;
    }

    public BigDecimal getCjl() {
        return cjl;
    }

    public void setCjl(BigDecimal cjl) {
        this.cjl = cjl;
    }

    public BigDecimal getCje() {
        return cje;
    }

    public void setCje(BigDecimal cje) {
        this.cje = cje;
    }

    public BigDecimal getZhenFu() {
        return zhenFu;
    }

    public void setZhenFu(BigDecimal zhenFu) {
        this.zhenFu = zhenFu;
    }

    public BigDecimal getZhangDieFu() {
        return zhangDieFu;
    }

    public void setZhangDieFu(BigDecimal zhangDieFu) {
        this.zhangDieFu = zhangDieFu;
    }

    public BigDecimal getZhangDieE() {
        return zhangDieE;
    }

    public void setZhangDieE(BigDecimal zhangDieE) {
        this.zhangDieE = zhangDieE;
    }

    public BigDecimal getHuanShouLv() {
        return huanShouLv;
    }

    public void setHuanShouLv(BigDecimal huanShouLv) {
        this.huanShouLv = huanShouLv;
    }

    public String getZqdm() {
        return zqdm;
    }

    public void setZqdm(String zqdm) {
        this.zqdm = zqdm;
    }

    public String getZqmc() {
        return zqmc;
    }

    public void setZqmc(String zqmc) {
        this.zqmc = zqmc;
    }
}
