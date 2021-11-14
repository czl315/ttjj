package ttjj.dto;

import java.math.BigDecimal;

/**
 * 统计信息-etf-每日涨跌
 *
 * @author chenzhiLong
 * @date
 */
public class StatEtfUpDown {
    /**
     * code 代码
     */
    private String code;
    /**
     * name 名称
     */
    private String name;
    /**
     * countCurContinueUp 当前连续次数合计-上涨
     */
    private int countCurContinueUp;
    /**
     * countCurContinueDown 当前连续次数合计-下跌
     */
    private int countCurContinueDown;
    /**
     * countTotalUp 累计次数-上涨
     */
    private int countTotalUp;
    /**
     * countTotalDown 累计次数-下跌
     */
    private int countTotalDown;

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

    public int getCountCurContinueUp() {
        return countCurContinueUp;
    }

    public void setCountCurContinueUp(int countCurContinueUp) {
        this.countCurContinueUp = countCurContinueUp;
    }

    public int getCountCurContinueDown() {
        return countCurContinueDown;
    }

    public void setCountCurContinueDown(int countCurContinueDown) {
        this.countCurContinueDown = countCurContinueDown;
    }

    public int getCountTotalUp() {
        return countTotalUp;
    }

    public void setCountTotalUp(int countTotalUp) {
        this.countTotalUp = countTotalUp;
    }

    public int getCountTotalDown() {
        return countTotalDown;
    }

    public void setCountTotalDown(int countTotalDown) {
        this.countTotalDown = countTotalDown;
    }
}
