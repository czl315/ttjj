package ttjj.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * K线
 */
public class KlineDto extends Kline {
    /**
     * k线列表
     */
    private List<Kline> klineList;
    /**
     * k线个数
     */
    private Integer count;
    /**
     * 净值-均值
     */
    private BigDecimal netMa;
    /**
     * 净值-当前最新
     */
    private BigDecimal netCur;

    /**
     * 得分
     */
    private BigDecimal score;

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public List<Kline> getKlineList() {
        return klineList;
    }

    public void setKlineList(List<Kline> klineList) {
        this.klineList = klineList;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getNetMa() {
        return netMa;
    }

    public void setNetMa(BigDecimal netMa) {
        this.netMa = netMa;
    }

    public BigDecimal getNetCur() {
        return netCur;
    }

    public void setNetCur(BigDecimal netCur) {
        this.netCur = netCur;
    }
}
