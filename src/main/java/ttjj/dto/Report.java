package ttjj.dto;

import java.math.BigDecimal;

/**
 * Report简介-业绩报表返回数据
 * {
 * "SECURITY_CODE": "603099",
 * "SECURITY_NAME_ABBR": "长白山",
 * "TRADE_MARKET_CODE": "069001001001",
 * "TRADE_MARKET": "上交所主板",
 * "SECURITY_TYPE_CODE": "058001001",
 * "SECURITY_TYPE": "A股",
 * "UPDATE_DATE": "2021-10-27 00:00:00",
 * "REPORTDATE": "2021-09-30 00:00:00",
 * "BASIC_EPS": -0.1,
 * "DEDUCT_BASIC_EPS": null,
 * "TOTAL_OPERATE_INCOME": 158307299.38,
 * "PARENT_NETPROFIT": -26709715.48,
 * "WEIGHTAVG_ROE": -2.66,
 * "YSTZ": 60.7175416514,
 * "SJLTZ": 32.7231883326,
 * "BPS": 3.715953885814,
 * "MGJYXJJE": 0.067942447932,
 * "XSMLL": 5.3449198004,
 * "YSHZ": 183.9646,
 * "SJLHZ": 242.2486,
 * "ASSIGNDSCRPT": null,
 * "PAYYEAR": null,
 * "PUBLISHNAME": "旅游酒店",
 * "ZXGXL": null,
 * "NOTICE_DATE": "2021-10-27 00:00:00",
 * "ORG_CODE": "10181860",
 * "TRADE_MARKET_ZJG": "0101",
 * "ISNEW": "1",
 * "QDATE": "2021Q3",
 * "DATATYPE": "2021年 三季报",
 * "DATAYEAR": "2021",
 * "DATEMMDD": "三季报",
 * "EITIME": "2021-10-26 15:41:04",
 * "SECUCODE": "603099.SH"
 * }
 *
 * @author Administrator
 * @date 2021-10-27 11:56
 */
public class Report {
    private String SECURITY_CODE;
    private String SECURITY_NAME_ABBR;
    private String TRADE_MARKET_CODE;
    private String TRADE_MARKET;
    private String SECURITY_TYPE_CODE;
    private String SECURITY_TYPE;
    private String UPDATE_DATE;
    private String REPORTDATE;
    /**
     * BASIC_EPS 每股收益
     */
    private BigDecimal BASIC_EPS;
    private String DEDUCT_BASIC_EPS;
    /**
     * TOTAL_OPERATE_INCOME 营业总收入-金额
     */
    private BigDecimal TOTAL_OPERATE_INCOME;
    /**
     * YSTZ 营业总收入-同比增长
     */
    private BigDecimal YSTZ;
    /**
     * YSHZ 营业总收入-环比增长
     */
    private BigDecimal YSHZ;

    /**
     * PARENT_NETPROFIT 净利润-金额
     */
    private BigDecimal PARENT_NETPROFIT;
    private BigDecimal WEIGHTAVG_ROE;
    /**
     * SJLTZ 净利润-同比增长
     */
    private BigDecimal SJLTZ;
    /**
     * BPS 每股净资产(元)
     */
    private BigDecimal BPS;
    private BigDecimal MGJYXJJE;
    private BigDecimal XSMLL;

    /**
     * SJLHZ SJLTZ 净利润-环比增长
     */
    private BigDecimal SJLHZ;
    private String ASSIGNDSCRPT;
    private String PAYYEAR;
    private String PUBLISHNAME;
    private String ZXGXL;
    private String NOTICE_DATE;
    private String ORG_CODE;
    private String TRADE_MARKET_ZJG;
    private String ISNEW;
    private String QDATE;
    private String DATATYPE;
    private String DATAYEAR;
    private String DATEMMDD;
    private String EITIME;
    private String SECUCODE;

    public void setSECURITY_CODE(String SECURITY_CODE) {
        this.SECURITY_CODE = SECURITY_CODE;
    }

    public String getSECURITY_CODE() {
        return SECURITY_CODE;
    }

    public void setSECURITY_NAME_ABBR(String SECURITY_NAME_ABBR) {
        this.SECURITY_NAME_ABBR = SECURITY_NAME_ABBR;
    }

    public String getSECURITY_NAME_ABBR() {
        return SECURITY_NAME_ABBR;
    }

    public void setTRADE_MARKET_CODE(String TRADE_MARKET_CODE) {
        this.TRADE_MARKET_CODE = TRADE_MARKET_CODE;
    }

    public String getTRADE_MARKET_CODE() {
        return TRADE_MARKET_CODE;
    }

    public void setTRADE_MARKET(String TRADE_MARKET) {
        this.TRADE_MARKET = TRADE_MARKET;
    }

    public String getTRADE_MARKET() {
        return TRADE_MARKET;
    }

    public void setSECURITY_TYPE_CODE(String SECURITY_TYPE_CODE) {
        this.SECURITY_TYPE_CODE = SECURITY_TYPE_CODE;
    }

    public String getSECURITY_TYPE_CODE() {
        return SECURITY_TYPE_CODE;
    }

    public void setSECURITY_TYPE(String SECURITY_TYPE) {
        this.SECURITY_TYPE = SECURITY_TYPE;
    }

    public String getSECURITY_TYPE() {
        return SECURITY_TYPE;
    }

    public void setUPDATE_DATE(String UPDATE_DATE) {
        this.UPDATE_DATE = UPDATE_DATE;
    }

    public String getUPDATE_DATE() {
        return UPDATE_DATE;
    }

    public void setREPORTDATE(String REPORTDATE) {
        this.REPORTDATE = REPORTDATE;
    }

    public String getREPORTDATE() {
        return REPORTDATE;
    }

    public void setBASIC_EPS(BigDecimal BASIC_EPS) {
        this.BASIC_EPS = BASIC_EPS;
    }

    public BigDecimal getBASIC_EPS() {
        return BASIC_EPS;
    }

    public void setDEDUCT_BASIC_EPS(String DEDUCT_BASIC_EPS) {
        this.DEDUCT_BASIC_EPS = DEDUCT_BASIC_EPS;
    }

    public String getDEDUCT_BASIC_EPS() {
        return DEDUCT_BASIC_EPS;
    }

    public void setTOTAL_OPERATE_INCOME(BigDecimal TOTAL_OPERATE_INCOME) {
        this.TOTAL_OPERATE_INCOME = TOTAL_OPERATE_INCOME;
    }

    public BigDecimal getTOTAL_OPERATE_INCOME() {
        return TOTAL_OPERATE_INCOME;
    }

    public void setPARENT_NETPROFIT(BigDecimal PARENT_NETPROFIT) {
        this.PARENT_NETPROFIT = PARENT_NETPROFIT;
    }

    public BigDecimal getPARENT_NETPROFIT() {
        return PARENT_NETPROFIT;
    }

    public void setWEIGHTAVG_ROE(BigDecimal WEIGHTAVG_ROE) {
        this.WEIGHTAVG_ROE = WEIGHTAVG_ROE;
    }

    public BigDecimal getWEIGHTAVG_ROE() {
        return WEIGHTAVG_ROE;
    }

    public void setYSTZ(BigDecimal YSTZ) {
        this.YSTZ = YSTZ;
    }

    public BigDecimal getYSTZ() {
        return YSTZ;
    }

    public void setSJLTZ(BigDecimal SJLTZ) {
        this.SJLTZ = SJLTZ;
    }

    public BigDecimal getSJLTZ() {
        return SJLTZ;
    }

    public void setBPS(BigDecimal BPS) {
        this.BPS = BPS;
    }

    public BigDecimal getBPS() {
        return BPS;
    }

    public void setMGJYXJJE(BigDecimal MGJYXJJE) {
        this.MGJYXJJE = MGJYXJJE;
    }

    public BigDecimal getMGJYXJJE() {
        return MGJYXJJE;
    }

    public void setXSMLL(BigDecimal XSMLL) {
        this.XSMLL = XSMLL;
    }

    public BigDecimal getXSMLL() {
        return XSMLL;
    }

    public void setYSHZ(BigDecimal YSHZ) {
        this.YSHZ = YSHZ;
    }

    public BigDecimal getYSHZ() {
        return YSHZ;
    }

    public void setSJLHZ(BigDecimal SJLHZ) {
        this.SJLHZ = SJLHZ;
    }

    public BigDecimal getSJLHZ() {
        return SJLHZ;
    }

    public void setASSIGNDSCRPT(String ASSIGNDSCRPT) {
        this.ASSIGNDSCRPT = ASSIGNDSCRPT;
    }

    public String getASSIGNDSCRPT() {
        return ASSIGNDSCRPT;
    }

    public void setPAYYEAR(String PAYYEAR) {
        this.PAYYEAR = PAYYEAR;
    }

    public String getPAYYEAR() {
        return PAYYEAR;
    }

    public void setPUBLISHNAME(String PUBLISHNAME) {
        this.PUBLISHNAME = PUBLISHNAME;
    }

    public String getPUBLISHNAME() {
        return PUBLISHNAME;
    }

    public void setZXGXL(String ZXGXL) {
        this.ZXGXL = ZXGXL;
    }

    public String getZXGXL() {
        return ZXGXL;
    }

    public void setNOTICE_DATE(String NOTICE_DATE) {
        this.NOTICE_DATE = NOTICE_DATE;
    }

    public String getNOTICE_DATE() {
        return NOTICE_DATE;
    }

    public void setORG_CODE(String ORG_CODE) {
        this.ORG_CODE = ORG_CODE;
    }

    public String getORG_CODE() {
        return ORG_CODE;
    }

    public void setTRADE_MARKET_ZJG(String TRADE_MARKET_ZJG) {
        this.TRADE_MARKET_ZJG = TRADE_MARKET_ZJG;
    }

    public String getTRADE_MARKET_ZJG() {
        return TRADE_MARKET_ZJG;
    }

    public void setISNEW(String ISNEW) {
        this.ISNEW = ISNEW;
    }

    public String getISNEW() {
        return ISNEW;
    }

    public void setQDATE(String QDATE) {
        this.QDATE = QDATE;
    }

    public String getQDATE() {
        return QDATE;
    }

    public void setDATATYPE(String DATATYPE) {
        this.DATATYPE = DATATYPE;
    }

    public String getDATATYPE() {
        return DATATYPE;
    }

    public void setDATAYEAR(String DATAYEAR) {
        this.DATAYEAR = DATAYEAR;
    }

    public String getDATAYEAR() {
        return DATAYEAR;
    }

    public void setDATEMMDD(String DATEMMDD) {
        this.DATEMMDD = DATEMMDD;
    }

    public String getDATEMMDD() {
        return DATEMMDD;
    }

    public void setEITIME(String EITIME) {
        this.EITIME = EITIME;
    }

    public String getEITIME() {
        return EITIME;
    }

    public void setSECUCODE(String SECUCODE) {
        this.SECUCODE = SECUCODE;
    }

    public String getSECUCODE() {
        return SECUCODE;
    }


}
