package ttjj.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 排行-股票-结果
 *
 * @author chenzhiLong
 * @date
 */
public class RankStComTjRs {
    /**
     * code 代码
     */
    private String code;
    /**
     * name 名称
     */
    private String name;
    /**
     * date 日期
     */
    private String date;
    /**
     * type 类型：bk-板块；gn-概念；etf；
     */
    private String type;
    /**
     * type_name 类型-板块名称
     */
    private String type_name;
    /**
     * orderNum 排序号
     */
    private Long orderNum;
    /**
     * begDate 开始日期
     */
    private String begDate;
    /**
     * endDate 结束日期
     */
    private String endDate;
    /**
     * zhangfuSum 涨跌幅合计
     */
    private BigDecimal zhangfuSum;
    /**
     * count 个数
     */
    private BigDecimal count;
    /**
     * lastMarketValue 最新市值
     */
    private BigDecimal lastMarketValue;
    /**
     * lastPe 最新pe市盈率
     */
    private BigDecimal lastPe;
    /**
     * lastRoe 最新Roe
     */
    private BigDecimal lastRoe;

    public BigDecimal getLastRoe() {
        return lastRoe;
    }

    public void setLastRoe(BigDecimal lastRoe) {
        this.lastRoe = lastRoe;
    }

    public BigDecimal getLastMarketValue() {
        return lastMarketValue;
    }

    public void setLastMarketValue(BigDecimal lastMarketValue) {
        this.lastMarketValue = lastMarketValue;
    }

    public BigDecimal getLastPe() {
        return lastPe;
    }

    public void setLastPe(BigDecimal lastPe) {
        this.lastPe = lastPe;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
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

    public BigDecimal getZhangfuSum() {
        return zhangfuSum;
    }

    public void setZhangfuSum(BigDecimal zhangfuSum) {
        this.zhangfuSum = zhangfuSum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public String getBegDate() {
        return begDate;
    }

    public void setBegDate(String begDate) {
        this.begDate = begDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
