package ttjj.dto;

import java.math.BigDecimal;
import java.util.List;


/**
 * 指数
 *
 * @author Administrator
 * @date 2022-11-04 19:46
 */
public class IndexDto extends Kline{
    /**
     * 前一个k线时间
     */
    private String preDayKtime;
    /**
     * 前一个-成交量
     */
    private String preDayCjl;
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

    public String getPreDayKtime() {
        return preDayKtime;
    }

    public void setPreDayKtime(String preDayKtime) {
        this.preDayKtime = preDayKtime;
    }

    public String getPreDayCjl() {
        return preDayCjl;
    }

    public void setPreDayCjl(String preDayCjl) {
        this.preDayCjl = preDayCjl;
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
}
