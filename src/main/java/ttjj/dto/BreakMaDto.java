package ttjj.dto;

import java.math.BigDecimal;

/**
 * 突破均线
 *
 * @author Administrator
 * @date 2022-09-28 00:41
 */
public class BreakMaDto {
    /**
     * 是否突破均线-向上
     */
    private boolean isMaBreakUp;
    /**
     * 突破均线-向下
     */
    private boolean isMaBreakDown;
    /**
     * 均线净值
     */
    private BigDecimal maNet;
    /**
     * 突破均线次数-向上
     */
    private int breakCountUp;
    /**
     * 突破均线次数-向下
     */
    private int breakCountDown;
    /**
     * 突破百分比-向上
     */
    private BigDecimal breakPctUp;
    /**
     * 突破百分比-向下
     */
    private BigDecimal breakPctDown;

    public BigDecimal getMaNet() {
        return maNet;
    }

    public void setMaNet(BigDecimal maNet) {
        this.maNet = maNet;
    }

    public boolean isMaBreakDown() {
        return isMaBreakDown;
    }

    public void setMaBreakDown(boolean maBreakDown) {
        isMaBreakDown = maBreakDown;
    }

    public boolean isMaBreakUp() {
        return isMaBreakUp;
    }

    public void setMaBreakUp(boolean maBreakUp) {
        isMaBreakUp = maBreakUp;
    }

    public int getBreakCountUp() {
        return breakCountUp;
    }

    public void setBreakCountUp(int breakCountUp) {
        this.breakCountUp = breakCountUp;
    }

    public int getBreakCountDown() {
        return breakCountDown;
    }

    public void setBreakCountDown(int breakCountDown) {
        this.breakCountDown = breakCountDown;
    }

    public BigDecimal getBreakPctUp() {
        return breakPctUp;
    }

    public void setBreakPctUp(BigDecimal breakPctUp) {
        this.breakPctUp = breakPctUp;
    }

    public BigDecimal getBreakPctDown() {
        return breakPctDown;
    }

    public void setBreakPctDown(BigDecimal breakPctDown) {
        this.breakPctDown = breakPctDown;
    }
}
