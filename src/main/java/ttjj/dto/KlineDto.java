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

    /**
     * 前一日k线列表
     */
    private List<KlineDto> preDayKlineList;
    /**
     * 量比(与前一个)
     */
    private BigDecimal preDayLiangBi;
    /**
     * 个数-上涨
     */
    private Integer countUp;
    /**
     * 个数-下跌
     */
    private Integer countDown;
    /**
     * 个数-平盘
     */
    private Integer countFlat;

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

    public BigDecimal getPreDayLiangBi() {
        return preDayLiangBi;
    }

    public void setPreDayLiangBi(BigDecimal preDayLiangBi) {
        this.preDayLiangBi = preDayLiangBi;
    }

    public Integer getCountUp() {
        return countUp;
    }

    public void setCountUp(Integer countUp) {
        this.countUp = countUp;
    }

    public Integer getCountDown() {
        return countDown;
    }

    public void setCountDown(Integer countDown) {
        this.countDown = countDown;
    }

    public Integer getCountFlat() {
        return countFlat;
    }

    public void setCountFlat(Integer countFlat) {
        this.countFlat = countFlat;
    }

    public List<KlineDto> getPreDayKlineList() {
        return preDayKlineList;
    }

    public void setPreDayKlineList(List<KlineDto> preDayKlineList) {
        this.preDayKlineList = preDayKlineList;
    }
}
