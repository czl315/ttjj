package ttjj.db;

import java.math.BigDecimal;
import java.util.List;

/**
 * 股票涨跌次数
 *
 * @author Administrator
 * @date 2022-05-09 23:47
 */
public class StockAdrCountVo extends StockAdrCount {
    /**
     * order_num 查询条件：排序号列表
     */
    private List<BigDecimal> orderNumList;

    public List<BigDecimal> getOrderNumList() {
        return orderNumList;
    }

    public void setOrderNumList(List<BigDecimal> orderNumList) {
        this.orderNumList = orderNumList;
    }
}
