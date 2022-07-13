package ttjj.dto;

import ttjj.db.RankStockCommpanyDb;

import java.math.BigDecimal;
import java.util.List;


/**
 * 查询条件-模糊匹配概念
 *
 * @author Administrator
 * @date 2022-02-07 14:10
 */
public class CondStock extends RankStockCommpanyDb {
    /**
     * mvMin 最低市值
     */
    private BigDecimal mvMin;
    /**
     * mvMax
     */
    private BigDecimal mvMax;

    List<String> conpetionList;

    public List<String> getConpetionList() {
        return conpetionList;
    }

    public void setConpetionList(List<String> conpetionList) {
        this.conpetionList = conpetionList;
    }

    public BigDecimal getMvMin() {
        return mvMin;
    }

    public void setMvMin(BigDecimal mvMin) {
        this.mvMin = mvMin;
    }

    public BigDecimal getMvMax() {
        return mvMax;
    }

    public void setMvMax(BigDecimal mvMax) {
        this.mvMax = mvMax;
    }
}
