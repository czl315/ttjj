package ttjj.dto;

import ttjj.db.StockAdrCount;

/**
 * 股票涨跌次数
 *
 * @author Administrator
 * @date 2022-05-09 23:47
 */
public class StockAdrCountVo extends StockAdrCount {
    /**
     * upMa 超过均线-
     */
    private String upMaDay15;
    /**
     * upMaDay10
     */
    private String upMaDay30;
    /**
     * upMaDay20
     */
    private String upMaDay60;
    /**
     * upMaDay40
     */
    private String upMaDay101;
    /**
     * upMaDay60
     */
    private String upMaDay102;

    public String getUpMaDay15() {
        return upMaDay15;
    }

    public void setUpMaDay15(String upMaDay15) {
        this.upMaDay15 = upMaDay15;
    }

    public String getUpMaDay30() {
        return upMaDay30;
    }

    public void setUpMaDay30(String upMaDay30) {
        this.upMaDay30 = upMaDay30;
    }

    public String getUpMaDay60() {
        return upMaDay60;
    }

    public void setUpMaDay60(String upMaDay60) {
        this.upMaDay60 = upMaDay60;
    }

    public String getUpMaDay101() {
        return upMaDay101;
    }

    public void setUpMaDay101(String upMaDay101) {
        this.upMaDay101 = upMaDay101;
    }

    public String getUpMaDay102() {
        return upMaDay102;
    }

    public void setUpMaDay102(String upMaDay102) {
        this.upMaDay102 = upMaDay102;
    }
}
