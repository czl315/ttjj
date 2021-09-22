package ttjj.dto;

import java.util.Date;

/**
 * 排行-股票-查询条件
 *
 * @author chenzhiLong
 * @date
 */
public class RankStComTjCond {
    /**
     * date 日期
     */
    private String date;
    /**
     * type 类型：hy-行业；gn-概念；etf；
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
