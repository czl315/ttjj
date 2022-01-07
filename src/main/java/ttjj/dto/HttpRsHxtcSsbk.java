package ttjj.dto;

/**
 * http调用返回结果：核心题材-所属板块
 * @author chenzhilong
 * @date 2022/1/8
 */
public class HttpRsHxtcSsbk {
    private String SECUCODE;
    private String SECURITY_CODE;
    private String SECURITY_NAME_ABBR;
    private String BOARD_CODE;
    private String BOARD_NAME;
    private String IS_PRECISE;
    private int BOARD_RANK;

    public String getSECUCODE() {
        return SECUCODE;
    }

    public void setSECUCODE(String SECUCODE) {
        this.SECUCODE = SECUCODE;
    }

    public String getSECURITY_CODE() {
        return SECURITY_CODE;
    }

    public void setSECURITY_CODE(String SECURITY_CODE) {
        this.SECURITY_CODE = SECURITY_CODE;
    }

    public String getSECURITY_NAME_ABBR() {
        return SECURITY_NAME_ABBR;
    }

    public void setSECURITY_NAME_ABBR(String SECURITY_NAME_ABBR) {
        this.SECURITY_NAME_ABBR = SECURITY_NAME_ABBR;
    }

    public String getBOARD_CODE() {
        return BOARD_CODE;
    }

    public void setBOARD_CODE(String BOARD_CODE) {
        this.BOARD_CODE = BOARD_CODE;
    }

    public String getBOARD_NAME() {
        return BOARD_NAME;
    }

    public void setBOARD_NAME(String BOARD_NAME) {
        this.BOARD_NAME = BOARD_NAME;
    }

    public String getIS_PRECISE() {
        return IS_PRECISE;
    }

    public void setIS_PRECISE(String IS_PRECISE) {
        this.IS_PRECISE = IS_PRECISE;
    }

    public int getBOARD_RANK() {
        return BOARD_RANK;
    }

    public void setBOARD_RANK(int BOARD_RANK) {
        this.BOARD_RANK = BOARD_RANK;
    }
}
