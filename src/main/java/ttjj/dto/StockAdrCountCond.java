package ttjj.dto;

import ttjj.db.StockAdrCount;

import java.math.BigDecimal;
import java.util.List;

/**
 * 股票涨跌次数
 *
 * @author Administrator
 * @date 2022-05-09 23:47
 */
public class StockAdrCountCond extends StockAdrCount {
    /**
     * order_num 查询条件：排序号列表
     */
    private List<BigDecimal> orderNumList;

    /**
     * orderBy 排序字段
     */
    private String orderBy;

    public List<BigDecimal> getOrderNumList() {
        return orderNumList;
    }

    public void setOrderNumList(List<BigDecimal> orderNumList) {
        this.orderNumList = orderNumList;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
