package ttjj.dto;

import java.math.BigDecimal;

/**
 * K线
 */
public class KlineDto extends Kline {
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
}
