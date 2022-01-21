package ttjj.dto;

import java.math.BigDecimal;


/**
 * StatRsStAdrCount简介
 *  统计数据-查询结果-股票涨跌次数
 * @author Administrator
 * @date 2022-01-21 10:39
 */
public class StatRsStAdrCount extends StatCondStAdrCount {
    /**
     * type 类型：bk-板块；gn-概念；etf；
     */
    private String type;
    /**
     * type_name 类型-板块名称
     */
    private String type_name;
    /**
     * adrSum 区间涨幅
     */
    private BigDecimal adrSum;
    /**
     * count 涨跌次数
     */
    private BigDecimal count;

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

    public BigDecimal getAdrSum() {
        return adrSum;
    }

    public void setAdrSum(BigDecimal adrSum) {
        this.adrSum = adrSum;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }
}
