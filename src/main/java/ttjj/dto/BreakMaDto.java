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
    private boolean isUpMa;
    /**
     * 均线净值
     */
    private BigDecimal maNet;

    public BigDecimal getMaNet() {
        return maNet;
    }

    public void setMaNet(BigDecimal maNet) {
        this.maNet = maNet;
    }

    public boolean isUpMa() {
        return isUpMa;
    }

    public void setUpMa(boolean upMa) {
        isUpMa = upMa;
    }
}
