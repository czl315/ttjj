package ttjj.dto;

import java.math.BigDecimal;


/**
 * StatRsStAdrCount简介
 *  统计数据-查询结果-涨跌次数-指数k线
 * @author Administrator
 * @date 2022-01-21 10:39
 */
public class StatRsStAdrCountKline extends StatCondStAdrCountKline {
    /**
     * adrSum 区间涨幅
     */
    private BigDecimal adrSum;
    /**
     * count 涨跌次数
     */
    private BigDecimal count;
    /**
     * countGt 涨跌大于n次数
     */
    private BigDecimal countGt;
    /**
     * countAll 总次数
     */
    private BigDecimal countAll;
    /**
     * pctCount 涨跌大于n百分比：涨跌大于n次数/总次数*100
     */
    private BigDecimal pctCount;

    /**
     * rsDate 结果日期
     */
    private String rsDate;

    public String getRsDate() {
        return rsDate;
    }

    public void setRsDate(String rsDate) {
        this.rsDate = rsDate;
    }

    public BigDecimal getAdrSum() {
        return adrSum;
    }

    public void setAdrSum(BigDecimal adrSum) {
        this.adrSum = adrSum;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getCountGt() {
        return countGt;
    }

    public void setCountGt(BigDecimal countGt) {
        this.countGt = countGt;
    }

    public BigDecimal getCountAll() {
        return countAll;
    }

    public void setCountAll(BigDecimal countAll) {
        this.countAll = countAll;
    }

    public BigDecimal getPctCount() {
        return pctCount;
    }

    public void setPctCount(BigDecimal pctCount) {
        this.pctCount = pctCount;
    }
}
