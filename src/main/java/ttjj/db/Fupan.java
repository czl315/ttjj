package ttjj.db;

/**
 * 复盘
 * @author chenzhilong
 * @date 2021/4/7
 */
public class Fupan {
    int ID;
    String code;
    String period;
    String type;
    /**
     * rt_hs300 沪深300增长率
     */
    String rtHs300;
    /**
     * pt_hs300 指数沪深300
     */
    String ptHs300;
    /**
     * cjeHs300 成交额-沪深300
     */
    String cjeHs300;

    public String getPtHs300() {
        return ptHs300;
    }

    public void setPtHs300(String ptHs300) {
        this.ptHs300 = ptHs300;
    }

    public String getCjeHs300() {
        return cjeHs300;
    }

    public void setCjeHs300(String cjeHs300) {
        this.cjeHs300 = cjeHs300;
    }

    public String getRtHs300() {
        return rtHs300;
    }

    public void setRtHs300(String rtHs300) {
        this.rtHs300 = rtHs300;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
