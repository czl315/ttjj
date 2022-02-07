package ttjj.dto;

import ttjj.db.RankStockCommpanyDb;

import java.util.List;


/**
 * 查询条件-模糊匹配概念
 *
 * @author Administrator
 * @date 2022-02-07 14:10
 */
public class CondStLikeConception extends RankStockCommpanyDb {
    List<String> conpetionList;

    public List<String> getConpetionList() {
        return conpetionList;
    }

    public void setConpetionList(List<String> conpetionList) {
        this.conpetionList = conpetionList;
    }
}
