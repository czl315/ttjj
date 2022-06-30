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
     * upMaDay15
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

    /**
     * maDownDay5 跌落均线
     */
    private String maDownDay5;
    /**
     * maDownDay15
     */
    private String maDownDay15;
    /**
     * maDownDay10
     */
    private String maDownDay30;
    /**
     * maDownDay20
     */
    private String maDownDay60;
    /**
     * maDownDay40
     */
    private String maDownDay101;
    /**
     * maDownDay60
     */
    private String maDownDay102;

    public String getUpMaDay5() {
        return upMaDay5;
    }

    public void setUpMaDay5(String upMaDay5) {
        this.upMaDay5 = upMaDay5;
    }

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

    public String getMaDownDay5() {
        return maDownDay5;
    }

    public void setMaDownDay5(String maDownDay5) {
        this.maDownDay5 = maDownDay5;
    }

    public String getMaDownDay15() {
        return maDownDay15;
    }

    public void setMaDownDay15(String maDownDay15) {
        this.maDownDay15 = maDownDay15;
    }

    public String getMaDownDay30() {
        return maDownDay30;
    }

    public void setMaDownDay30(String maDownDay30) {
        this.maDownDay30 = maDownDay30;
    }

    public String getMaDownDay60() {
        return maDownDay60;
    }

    public void setMaDownDay60(String maDownDay60) {
        this.maDownDay60 = maDownDay60;
    }

    public String getMaDownDay101() {
        return maDownDay101;
    }

    public void setMaDownDay101(String maDownDay101) {
        this.maDownDay101 = maDownDay101;
    }

    public String getMaDownDay102() {
        return maDownDay102;
    }

    public void setMaDownDay102(String maDownDay102) {
        this.maDownDay102 = maDownDay102;
    }
}
