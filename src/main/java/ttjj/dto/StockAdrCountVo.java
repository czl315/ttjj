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
    private String upMaDay5;
    /**
     * upMaDay10
     */
    private String upMaDay10;
    /**
     * upMaDay20
     */
    private String upMaDay20;
    /**
     * upMaDay40
     */
    private String upMaDay40;
    /**
     * upMaDay60
     */
    private String upMaDay60;

    public String getUpMaDay5() {
        return upMaDay5;
    }

    public void setUpMaDay5(String upMaDay5) {
        this.upMaDay5 = upMaDay5;
    }

    public String getUpMaDay10() {
        return upMaDay10;
    }

    public void setUpMaDay10(String upMaDay10) {
        this.upMaDay10 = upMaDay10;
    }

    public String getUpMaDay20() {
        return upMaDay20;
    }

    public void setUpMaDay20(String upMaDay20) {
        this.upMaDay20 = upMaDay20;
    }

    public String getUpMaDay40() {
        return upMaDay40;
    }

    public void setUpMaDay40(String upMaDay40) {
        this.upMaDay40 = upMaDay40;
    }

    public String getUpMaDay60() {
        return upMaDay60;
    }

    public void setUpMaDay60(String upMaDay60) {
        this.upMaDay60 = upMaDay60;
    }
}
