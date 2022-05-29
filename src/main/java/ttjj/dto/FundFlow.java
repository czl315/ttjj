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
     * mainIn 主力净流入
     */
    private BigDecimal mainNetIn;
    /**
     * smallNetIn 小单净流入
     */
    private BigDecimal smallNetIn;
    /**
     * midNetIn 中单净流入
     */
    private BigDecimal midNetIn;
    /**
     * bigNetIn 大单净流入
     */
    private BigDecimal bigNetIn;
    /**
     * superBigNetIn 超大单净流入
     */
    private BigDecimal superBigNetIn;

    public String getKtime() {
        return ktime;
    }

    public void setKtime(String ktime) {
        this.ktime = ktime;
    }

    public BigDecimal getMainNetIn() {
        return mainNetIn;
    }

    public void setMainNetIn(BigDecimal mainNetIn) {
        this.mainNetIn = mainNetIn;
    }

    public BigDecimal getSmallNetIn() {
        return smallNetIn;
    }

    public void setSmallNetIn(BigDecimal smallNetIn) {
        this.smallNetIn = smallNetIn;
    }

    public BigDecimal getMidNetIn() {
        return midNetIn;
    }

    public void setMidNetIn(BigDecimal midNetIn) {
        this.midNetIn = midNetIn;
    }

    public BigDecimal getBigNetIn() {
        return bigNetIn;
    }

    public void setBigNetIn(BigDecimal bigNetIn) {
        this.bigNetIn = bigNetIn;
    }

    public BigDecimal getSuperBigNetIn() {
        return superBigNetIn;
    }

    public void setSuperBigNetIn(BigDecimal superBigNetIn) {
        this.superBigNetIn = superBigNetIn;
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
}
