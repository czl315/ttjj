package ttjj.dto;

import java.math.BigDecimal;

/**
 * K线-资金流向
 * "klines":["2022-05-27 09:31,9706708.0,-4195368.0,-5511340.0,6747405.0,2959303.0","2022-05-27 09:32,13862440.0,-4179670.0,-9682770.0,3219155.0,10643285.0"]
 * //  日期时间，
 */
public class FundFlow {
    /**
     * 日期时间
     */
    private String ktime;
    /**
     * code
     */
    private String code;
    /**
     * name
     */
    private String name;
    /**
     *  主力净流入
     */
    private BigDecimal flowInMain;
    /**
     *  小单净流入
     */
    private BigDecimal flowInSmall;
    /**
     *  中单净流入
     */
    private BigDecimal flowInMid;
    /**
     *  大单净流入
     */
    private BigDecimal flowInBig;
    /**
     *  超大单净流入
     */
    private BigDecimal flowInSuperBig;

    public String getKtime() {
        return ktime;
    }

    public void setKtime(String ktime) {
        this.ktime = ktime;
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

    public BigDecimal getFlowInMain() {
        return flowInMain;
    }

    public void setFlowInMain(BigDecimal flowInMain) {
        this.flowInMain = flowInMain;
    }

    public BigDecimal getFlowInSmall() {
        return flowInSmall;
    }

    public void setFlowInSmall(BigDecimal flowInSmall) {
        this.flowInSmall = flowInSmall;
    }

    public BigDecimal getFlowInMid() {
        return flowInMid;
    }

    public void setFlowInMid(BigDecimal flowInMid) {
        this.flowInMid = flowInMid;
    }

    public BigDecimal getFlowInBig() {
        return flowInBig;
    }

    public void setFlowInBig(BigDecimal flowInBig) {
        this.flowInBig = flowInBig;
    }

    public BigDecimal getFlowInSuperBig() {
        return flowInSuperBig;
    }

    public void setFlowInSuperBig(BigDecimal flowInSuperBig) {
        this.flowInSuperBig = flowInSuperBig;
    }
}
