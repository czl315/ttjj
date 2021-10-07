package ttjj.dto;

import java.math.BigDecimal;

/**
 * 均线突破-向上-查询结果
 *
 * @author chenzhiLong
 * @date
 */
public class MaBreakUpRs extends  MaBreakUpCond{
    /**
     * code
     */
    private String code;
    /**
     * name
     */
    private String name;
    /**
     * adrDaySum3 日加3涨幅合计
     */
    private BigDecimal adrDaySum3;
    /**
     * adrDaySum2
     */
    private BigDecimal adrDaySum2;
    /**
     * adrDaySum1
     */
    private BigDecimal adrDaySum1;
    /**
     * curDayAdd1 当前日加1
     */
    private String curDayAdd1;
    /**
     * curDayAdd2 当前日加2
     */
    private String curDayAdd2;
    /**
     * curDayAdd3 当前日加3
     */
    private String curDayAdd3;
    /**
     * goodRate5DaySub1 乖离率-当前日减1
     */
    private BigDecimal goodRate5DaySub1;
    /**
     * adrCurDay 涨跌率-当前日
     */
    private BigDecimal adrCurDay;
    /**
     * adrCurDayAdd1 涨跌率-当前日加1
     */
    private BigDecimal adrCurDayAdd1;
    /**
     * adrCurDayAdd2
     */
    private BigDecimal adrCurDayAdd2;
    /**
     * adrCurDayAdd3
     */
    private BigDecimal adrCurDayAdd3;

    /**
     * curDayAdd1UpCount (统计数据使用)后一日上涨次数
     */
    private int curDayAdd1UpCount;
    /**
     * curDayAdd2UpCount
     */
    private int curDayAdd2UpCount;
    /**
     * curDayAdd3UpCount
     */
    private int curDayAdd3UpCount;
    /**
     * curDayAdd1DownCount (统计数据使用)后一日下跌次数
     */
    private int curDayAdd1DownCount;
    /**
     * curDayAdd2DownCount
     */
    private int curDayAdd2DownCount;
    /**
     * curDayAdd3DownCount
     */
    private int curDayAdd3DownCount;

    public int getCurDayAdd1UpCount() {
        return curDayAdd1UpCount;
    }

    public void setCurDayAdd1UpCount(int curDayAdd1UpCount) {
        this.curDayAdd1UpCount = curDayAdd1UpCount;
    }

    public int getCurDayAdd2UpCount() {
        return curDayAdd2UpCount;
    }

    public void setCurDayAdd2UpCount(int curDayAdd2UpCount) {
        this.curDayAdd2UpCount = curDayAdd2UpCount;
    }

    public int getCurDayAdd3UpCount() {
        return curDayAdd3UpCount;
    }

    public void setCurDayAdd3UpCount(int curDayAdd3UpCount) {
        this.curDayAdd3UpCount = curDayAdd3UpCount;
    }

    public int getCurDayAdd1DownCount() {
        return curDayAdd1DownCount;
    }

    public void setCurDayAdd1DownCount(int curDayAdd1DownCount) {
        this.curDayAdd1DownCount = curDayAdd1DownCount;
    }

    public int getCurDayAdd2DownCount() {
        return curDayAdd2DownCount;
    }

    public void setCurDayAdd2DownCount(int curDayAdd2DownCount) {
        this.curDayAdd2DownCount = curDayAdd2DownCount;
    }

    public int getCurDayAdd3DownCount() {
        return curDayAdd3DownCount;
    }

    public void setCurDayAdd3DownCount(int curDayAdd3DownCount) {
        this.curDayAdd3DownCount = curDayAdd3DownCount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAdrDaySum3() {
        return adrDaySum3;
    }

    public void setAdrDaySum3(BigDecimal adrDaySum3) {
        this.adrDaySum3 = adrDaySum3;
    }

    @Override
    public String getCurDayAdd1() {
        return curDayAdd1;
    }

    @Override
    public void setCurDayAdd1(String curDayAdd1) {
        this.curDayAdd1 = curDayAdd1;
    }

    @Override
    public String getCurDayAdd2() {
        return curDayAdd2;
    }

    @Override
    public void setCurDayAdd2(String curDayAdd2) {
        this.curDayAdd2 = curDayAdd2;
    }

    @Override
    public String getCurDayAdd3() {
        return curDayAdd3;
    }

    @Override
    public void setCurDayAdd3(String curDayAdd3) {
        this.curDayAdd3 = curDayAdd3;
    }

    public BigDecimal getGoodRate5DaySub1() {
        return goodRate5DaySub1;
    }

    public void setGoodRate5DaySub1(BigDecimal goodRate5DaySub1) {
        this.goodRate5DaySub1 = goodRate5DaySub1;
    }

    public BigDecimal getAdrCurDay() {
        return adrCurDay;
    }

    public void setAdrCurDay(BigDecimal adrCurDay) {
        this.adrCurDay = adrCurDay;
    }

    public BigDecimal getAdrCurDayAdd1() {
        return adrCurDayAdd1;
    }

    public void setAdrCurDayAdd1(BigDecimal adrCurDayAdd1) {
        this.adrCurDayAdd1 = adrCurDayAdd1;
    }

    public BigDecimal getAdrCurDayAdd2() {
        return adrCurDayAdd2;
    }

    public void setAdrCurDayAdd2(BigDecimal adrCurDayAdd2) {
        this.adrCurDayAdd2 = adrCurDayAdd2;
    }

    public BigDecimal getAdrCurDayAdd3() {
        return adrCurDayAdd3;
    }

    public void setAdrCurDayAdd3(BigDecimal adrCurDayAdd3) {
        this.adrCurDayAdd3 = adrCurDayAdd3;
    }

    public BigDecimal getAdrDaySum2() {
        return adrDaySum2;
    }

    public void setAdrDaySum2(BigDecimal adrDaySum2) {
        this.adrDaySum2 = adrDaySum2;
    }

    public BigDecimal getAdrDaySum1() {
        return adrDaySum1;
    }

    public void setAdrDaySum1(BigDecimal adrDaySum1) {
        this.adrDaySum1 = adrDaySum1;
    }
}
