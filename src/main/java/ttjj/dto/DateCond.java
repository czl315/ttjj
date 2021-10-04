package ttjj.dto;

/**
 * 日期-查询条件
 *
 * @author chenzhiLong
 * @date
 */
public class DateCond {
    /**
     * curDate 当前日
     */
    private String curDate;
    /**
     * count 个数
     */
    private int count;

    public DateCond(String curDate, int count) {
        this.curDate = curDate;
        this.count = count;
    }

    public DateCond() {
    }

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
