package ttjj.dto;

import java.math.BigDecimal;

/**
 * 超跌反弹-查询结果-统计
 *
 * @author chenzhiLong
 * @date
 */
public class SuperDropBounceStat {
    /**
     * rsCount 查询结果个数
     */
    private BigDecimal rsCountDay1 = new BigDecimal("0");
    /**
     * adrUpCount 上涨个数
     */
    private BigDecimal adrUpCountDay1 = new BigDecimal("0");
    /**
     * adrDownCount 下跌个数
     */
    private BigDecimal adrDownCountDay1 = new BigDecimal("0");
    /**
     * rsCount 查询结果个数
     */
    private BigDecimal rsCountDay2 = new BigDecimal("0");
    /**
     * adrUpCount 上涨个数
     */
    private BigDecimal adrUpCountDay2 = new BigDecimal("0");
    /**
     * adrDownCount 下跌个数
     */
    private BigDecimal adrDownCountDay2 = new BigDecimal("0");
    /**
     * rsCount 查询结果个数
     */
    private BigDecimal rsCountDay3 = new BigDecimal("0");
    /**
     * adrUpCount 上涨个数
     */
    private BigDecimal adrUpCountDay3 = new BigDecimal("0");
    /**
     * adrDownCount 下跌个数
     */
    private BigDecimal adrDownCountDay3 = new BigDecimal("0");
    /**
     * rsCount 查询结果个数
     */
    private BigDecimal rsCountDay5 = new BigDecimal("0");
    /**
     * adrUpCount 上涨个数
     */
    private BigDecimal adrUpCountDay5 = new BigDecimal("0");
    /**
     * adrDownCount 下跌个数
     */
    private BigDecimal adrDownCountDay5 = new BigDecimal("0");
    /**
     * rsCount 查询结果个数
     */
    private BigDecimal rsCountDay10 = new BigDecimal("0");
    /**
     * adrUpCount 上涨个数
     */
    private BigDecimal adrUpCountDay10 = new BigDecimal("0");
    /**
     * adrDownCount 下跌个数
     */
    private BigDecimal adrDownCountDay10 = new BigDecimal("0");
    /**
     * rsCount 查询结果个数
     */
    private BigDecimal rsCountDay20 = new BigDecimal("0");
    /**
     * adrUpCount 上涨个数
     */
    private BigDecimal adrUpCountDay20 = new BigDecimal("0");
    /**
     * adrDownCount 下跌个数
     */
    private BigDecimal adrDownCountDay20 = new BigDecimal("0");
    /**
     * rsCount 查询结果个数
     */
    private BigDecimal rsCountDay30 = new BigDecimal("0");
    /**
     * adrUpCount 上涨个数
     */
    private BigDecimal adrUpCountDay30 = new BigDecimal("0");
    /**
     * adrDownCount 下跌个数
     */
    private BigDecimal adrDownCountDay30 = new BigDecimal("0");
    /**
     * rsCount 查询结果个数
     */
    private BigDecimal rsCountDay60 = new BigDecimal("0");
    /**
     * adrUpCount 上涨个数
     */
    private BigDecimal adrUpCountDay60 = new BigDecimal("0");
    /**
     * adrDownCount 下跌个数
     */
    private BigDecimal adrDownCountDay60 = new BigDecimal("0");

    /**
     * adrUpCount 上涨个数-合计(只要有阶段价格超过就合格)
     */
    private BigDecimal adrUpCountTotalDay1 = new BigDecimal("0");
    private BigDecimal adrUpCountTotalDay2 = new BigDecimal("0");
    private BigDecimal adrUpCountTotalDay3 = new BigDecimal("0");
    private BigDecimal adrUpCountTotalDay5 = new BigDecimal("0");
    private BigDecimal adrUpCountTotalDay10 = new BigDecimal("0");
    private BigDecimal adrUpCountTotalDay20 = new BigDecimal("0");
    private BigDecimal adrUpCountTotalDay30 = new BigDecimal("0");
    private BigDecimal adrUpCountTotalDay60 = new BigDecimal("0");
    /**
     * adrCount 下跌个数-合计(只要有阶段价格超过就合格)
     */
    private BigDecimal adrDownCountTotalDay1 = new BigDecimal("0");
    private BigDecimal adrDownCountTotalDay2 = new BigDecimal("0");
    private BigDecimal adrDownCountTotalDay3 = new BigDecimal("0");
    private BigDecimal adrDownCountTotalDay5 = new BigDecimal("0");
    private BigDecimal adrDownCountTotalDay10 = new BigDecimal("0");
    private BigDecimal adrDownCountTotalDay20 = new BigDecimal("0");
    private BigDecimal adrDownCountTotalDay30 = new BigDecimal("0");
    private BigDecimal adrDownCountTotalDay60 = new BigDecimal("0");

    public BigDecimal getAdrDownCountTotalDay1() {
        return adrDownCountTotalDay1;
    }

    public void setAdrDownCountTotalDay1(BigDecimal adrDownCountTotalDay1) {
        this.adrDownCountTotalDay1 = adrDownCountTotalDay1;
    }

    public BigDecimal getAdrDownCountTotalDay2() {
        return adrDownCountTotalDay2;
    }

    public void setAdrDownCountTotalDay2(BigDecimal adrDownCountTotalDay2) {
        this.adrDownCountTotalDay2 = adrDownCountTotalDay2;
    }

    public BigDecimal getAdrDownCountTotalDay3() {
        return adrDownCountTotalDay3;
    }

    public void setAdrDownCountTotalDay3(BigDecimal adrDownCountTotalDay3) {
        this.adrDownCountTotalDay3 = adrDownCountTotalDay3;
    }

    public BigDecimal getAdrDownCountTotalDay5() {
        return adrDownCountTotalDay5;
    }

    public void setAdrDownCountTotalDay5(BigDecimal adrDownCountTotalDay5) {
        this.adrDownCountTotalDay5 = adrDownCountTotalDay5;
    }

    public BigDecimal getAdrDownCountTotalDay10() {
        return adrDownCountTotalDay10;
    }

    public void setAdrDownCountTotalDay10(BigDecimal adrDownCountTotalDay10) {
        this.adrDownCountTotalDay10 = adrDownCountTotalDay10;
    }

    public BigDecimal getAdrDownCountTotalDay20() {
        return adrDownCountTotalDay20;
    }

    public void setAdrDownCountTotalDay20(BigDecimal adrDownCountTotalDay20) {
        this.adrDownCountTotalDay20 = adrDownCountTotalDay20;
    }

    public BigDecimal getAdrDownCountTotalDay30() {
        return adrDownCountTotalDay30;
    }

    public void setAdrDownCountTotalDay30(BigDecimal adrDownCountTotalDay30) {
        this.adrDownCountTotalDay30 = adrDownCountTotalDay30;
    }

    public BigDecimal getAdrDownCountTotalDay60() {
        return adrDownCountTotalDay60;
    }

    public void setAdrDownCountTotalDay60(BigDecimal adrDownCountTotalDay60) {
        this.adrDownCountTotalDay60 = adrDownCountTotalDay60;
    }

    public BigDecimal getAdrUpCountTotalDay1() {
        return adrUpCountTotalDay1;
    }

    public void setAdrUpCountTotalDay1(BigDecimal adrUpCountTotalDay1) {
        this.adrUpCountTotalDay1 = adrUpCountTotalDay1;
    }

    public BigDecimal getAdrUpCountTotalDay2() {
        return adrUpCountTotalDay2;
    }

    public void setAdrUpCountTotalDay2(BigDecimal adrUpCountTotalDay2) {
        this.adrUpCountTotalDay2 = adrUpCountTotalDay2;
    }

    public BigDecimal getAdrUpCountTotalDay3() {
        return adrUpCountTotalDay3;
    }

    public void setAdrUpCountTotalDay3(BigDecimal adrUpCountTotalDay3) {
        this.adrUpCountTotalDay3 = adrUpCountTotalDay3;
    }

    public BigDecimal getAdrUpCountTotalDay5() {
        return adrUpCountTotalDay5;
    }

    public void setAdrUpCountTotalDay5(BigDecimal adrUpCountTotalDay5) {
        this.adrUpCountTotalDay5 = adrUpCountTotalDay5;
    }

    public BigDecimal getAdrUpCountTotalDay10() {
        return adrUpCountTotalDay10;
    }

    public void setAdrUpCountTotalDay10(BigDecimal adrUpCountTotalDay10) {
        this.adrUpCountTotalDay10 = adrUpCountTotalDay10;
    }

    public BigDecimal getAdrUpCountTotalDay20() {
        return adrUpCountTotalDay20;
    }

    public void setAdrUpCountTotalDay20(BigDecimal adrUpCountTotalDay20) {
        this.adrUpCountTotalDay20 = adrUpCountTotalDay20;
    }

    public BigDecimal getAdrUpCountTotalDay30() {
        return adrUpCountTotalDay30;
    }

    public void setAdrUpCountTotalDay30(BigDecimal adrUpCountTotalDay30) {
        this.adrUpCountTotalDay30 = adrUpCountTotalDay30;
    }

    public BigDecimal getAdrUpCountTotalDay60() {
        return adrUpCountTotalDay60;
    }

    public void setAdrUpCountTotalDay60(BigDecimal adrUpCountTotalDay60) {
        this.adrUpCountTotalDay60 = adrUpCountTotalDay60;
    }

    public BigDecimal getRsCountDay1() {
        return rsCountDay1;
    }

    public void setRsCountDay1(BigDecimal rsCountDay1) {
        this.rsCountDay1 = rsCountDay1;
    }

    public BigDecimal getAdrUpCountDay1() {
        return adrUpCountDay1;
    }

    public void setAdrUpCountDay1(BigDecimal adrUpCountDay1) {
        this.adrUpCountDay1 = adrUpCountDay1;
    }

    public BigDecimal getAdrDownCountDay1() {
        return adrDownCountDay1;
    }

    public void setAdrDownCountDay1(BigDecimal adrDownCountDay1) {
        this.adrDownCountDay1 = adrDownCountDay1;
    }

    public BigDecimal getRsCountDay2() {
        return rsCountDay2;
    }

    public void setRsCountDay2(BigDecimal rsCountDay2) {
        this.rsCountDay2 = rsCountDay2;
    }

    public BigDecimal getAdrUpCountDay2() {
        return adrUpCountDay2;
    }

    public void setAdrUpCountDay2(BigDecimal adrUpCountDay2) {
        this.adrUpCountDay2 = adrUpCountDay2;
    }

    public BigDecimal getAdrDownCountDay2() {
        return adrDownCountDay2;
    }

    public void setAdrDownCountDay2(BigDecimal adrDownCountDay2) {
        this.adrDownCountDay2 = adrDownCountDay2;
    }

    public BigDecimal getRsCountDay3() {
        return rsCountDay3;
    }

    public void setRsCountDay3(BigDecimal rsCountDay3) {
        this.rsCountDay3 = rsCountDay3;
    }

    public BigDecimal getAdrUpCountDay3() {
        return adrUpCountDay3;
    }

    public void setAdrUpCountDay3(BigDecimal adrUpCountDay3) {
        this.adrUpCountDay3 = adrUpCountDay3;
    }

    public BigDecimal getAdrDownCountDay3() {
        return adrDownCountDay3;
    }

    public void setAdrDownCountDay3(BigDecimal adrDownCountDay3) {
        this.adrDownCountDay3 = adrDownCountDay3;
    }

    public BigDecimal getRsCountDay5() {
        return rsCountDay5;
    }

    public void setRsCountDay5(BigDecimal rsCountDay5) {
        this.rsCountDay5 = rsCountDay5;
    }

    public BigDecimal getAdrUpCountDay5() {
        return adrUpCountDay5;
    }

    public void setAdrUpCountDay5(BigDecimal adrUpCountDay5) {
        this.adrUpCountDay5 = adrUpCountDay5;
    }

    public BigDecimal getAdrDownCountDay5() {
        return adrDownCountDay5;
    }

    public void setAdrDownCountDay5(BigDecimal adrDownCountDay5) {
        this.adrDownCountDay5 = adrDownCountDay5;
    }

    public BigDecimal getRsCountDay10() {
        return rsCountDay10;
    }

    public void setRsCountDay10(BigDecimal rsCountDay10) {
        this.rsCountDay10 = rsCountDay10;
    }

    public BigDecimal getAdrUpCountDay10() {
        return adrUpCountDay10;
    }

    public void setAdrUpCountDay10(BigDecimal adrUpCountDay10) {
        this.adrUpCountDay10 = adrUpCountDay10;
    }

    public BigDecimal getAdrDownCountDay10() {
        return adrDownCountDay10;
    }

    public void setAdrDownCountDay10(BigDecimal adrDownCountDay10) {
        this.adrDownCountDay10 = adrDownCountDay10;
    }

    public BigDecimal getRsCountDay20() {
        return rsCountDay20;
    }

    public void setRsCountDay20(BigDecimal rsCountDay20) {
        this.rsCountDay20 = rsCountDay20;
    }

    public BigDecimal getAdrUpCountDay20() {
        return adrUpCountDay20;
    }

    public void setAdrUpCountDay20(BigDecimal adrUpCountDay20) {
        this.adrUpCountDay20 = adrUpCountDay20;
    }

    public BigDecimal getAdrDownCountDay20() {
        return adrDownCountDay20;
    }

    public void setAdrDownCountDay20(BigDecimal adrDownCountDay20) {
        this.adrDownCountDay20 = adrDownCountDay20;
    }

    public BigDecimal getRsCountDay30() {
        return rsCountDay30;
    }

    public void setRsCountDay30(BigDecimal rsCountDay30) {
        this.rsCountDay30 = rsCountDay30;
    }

    public BigDecimal getAdrUpCountDay30() {
        return adrUpCountDay30;
    }

    public void setAdrUpCountDay30(BigDecimal adrUpCountDay30) {
        this.adrUpCountDay30 = adrUpCountDay30;
    }

    public BigDecimal getAdrDownCountDay30() {
        return adrDownCountDay30;
    }

    public void setAdrDownCountDay30(BigDecimal adrDownCountDay30) {
        this.adrDownCountDay30 = adrDownCountDay30;
    }

    public BigDecimal getRsCountDay60() {
        return rsCountDay60;
    }

    public void setRsCountDay60(BigDecimal rsCountDay60) {
        this.rsCountDay60 = rsCountDay60;
    }

    public BigDecimal getAdrUpCountDay60() {
        return adrUpCountDay60;
    }

    public void setAdrUpCountDay60(BigDecimal adrUpCountDay60) {
        this.adrUpCountDay60 = adrUpCountDay60;
    }

    public BigDecimal getAdrDownCountDay60() {
        return adrDownCountDay60;
    }

    public void setAdrDownCountDay60(BigDecimal adrDownCountDay60) {
        this.adrDownCountDay60 = adrDownCountDay60;
    }
}
