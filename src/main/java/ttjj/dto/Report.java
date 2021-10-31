package ttjj.dto;

import java.math.BigDecimal;

/**
 * Report简介-业绩报表返回数据
 * http://datacenter-web.eastmoney.com/api/data/get?callback=jQuery112309508918124001358_1635304406473&st=REPORTDATE&sr=-1&ps=50&p=1&sty=ALL&filter=(SECURITY_CODE=%22002624%22)&token=894050c76af8597a853f5b408b759f5d&type=RPT_LICO_FN_CPD
 {
 "SECURITY_CODE": "002624",
 "SECURITY_NAME_ABBR": "完美世界",
 "TRADE_MARKET_CODE": "069001002001",
 "TRADE_MARKET": "深交所主板",
 "SECURITY_TYPE_CODE": "058001001",
 "SECURITY_TYPE": "A股",
 "UPDATE_DATE": "2021-10-27 00:00:00",
 "REPORTDATE": "2021-09-30 00:00:00",
 "BASIC_EPS": 0.41,
 "DEDUCT_BASIC_EPS": null,
 "TOTAL_OPERATE_INCOME": 6739494487.57,
 "PARENT_NETPROFIT": 799916206.12,
 "WEIGHTAVG_ROE": 7.37,
 "YSTZ": -16.4047817808,
 "SJLTZ": -55.72,
 "BPS": 5.585338538859,
 "MGJYXJJE": 0.261280616914,
 "XSMLL": 59.4427228492,
 "YSHZ": 28.2079,
 "SJLHZ": 362.4657,
 "ASSIGNDSCRPT": null,
 "PAYYEAR": null,
 "PUBLISHNAME": "文化传媒",
 "ZXGXL": null,
 "NOTICE_DATE": "2021-10-27 00:00:00",
 "ORG_CODE": "10175652",
 "TRADE_MARKET_ZJG": "0201",
 "ISNEW": "1",
 "QDATE": "2021Q3",
 "DATATYPE": "2021年 三季报",
 "DATAYEAR": "2021",
 "DATEMMDD": "三季报",
 "EITIME": "2021-10-26 17:36:23",
 "SECUCODE": "002624.SZ"
 }
 * @author Administrator
 * @date 2021-10-27 11:56
 */
public class Report {
    /**
     * SECURITY_CODE 证券编码
     *  *         "SECURITY_CODE": "002624",
     */
    private String SECURITY_CODE;
    /**
     * SECURITY_NAME_ABBR 证券名称
     *  *         "SECURITY_NAME_ABBR": "完美世界",
     */
    private String SECURITY_NAME_ABBR;
    /**
     * TRADE_MARKET_CODE 所属市场编码
     *  *         "TRADE_MARKET_CODE": "069001002001",
     */
    private String TRADE_MARKET_CODE;
    /**
     * TRADE_MARKET 所属市场
     *  *         "TRADE_MARKET": "深交所主板",
     */
    private String TRADE_MARKET;
    /**
     * SECURITY_TYPE_CODE 证券类型编码
     *  *         "SECURITY_TYPE_CODE": "058001001",
     */
    private String SECURITY_TYPE_CODE;
    /**
     * SECURITY_TYPE 证券类型
     *  *         "SECURITY_TYPE": "A股",
     */
    private String SECURITY_TYPE;
    /**
     * UPDATE_DATE 最新公告日期
     *  *         "UPDATE_DATE": "2021-10-27 00:00:00",
     */
    private String UPDATE_DATE;
    /**
     * REPORTDATE 报告日期
     *  *         "REPORTDATE": "2021-09-30 00:00:00",
     */
    private String REPORTDATE;
    /**
     * BASIC_EPS 每股收益(元)
     *  *         "BASIC_EPS": 0.41,
     */
    private BigDecimal BASIC_EPS;
    /**
     * DEDUCT_BASIC_EPS 每股收益(扣除)(元)
     *  *         "DEDUCT_BASIC_EPS": null,
     */
    private String DEDUCT_BASIC_EPS;
    /**
     * TOTAL_OPERATE_INCOME 营业总收入-营业总收入(元)
     *  *         "TOTAL_OPERATE_INCOME": 6739494487.57,
     */
    private BigDecimal TOTAL_OPERATE_INCOME;
    /**
     * YSTZ 营业总收入-同比增长(%)
     *  *         "YSTZ": -16.4047817808,
     */
    private BigDecimal YSTZ;
    /**
     * YSHZ 营业总收入-季度环比增长(%)
     *  *         "YSHZ": 28.2079,
     */
    private BigDecimal YSHZ;

    /**
     * PARENT_NETPROFIT 净利润-净利润(元)
     *  *         "PARENT_NETPROFIT": 799916206.12,
     */
    private BigDecimal PARENT_NETPROFIT;
    /**
     * WEIGHTAVG_ROE 净资产收益率(%)
     *  *         "WEIGHTAVG_ROE": 7.37,
     */
    private BigDecimal WEIGHTAVG_ROE;
    /**
     * SJLTZ 净利润-同比增长(%)
     *  *         "SJLTZ": -55.72,
     */
    private BigDecimal SJLTZ;
    /**
     * BPS 每股净资产(元)
     *         "BPS": 5.585338538859,
     */
    private BigDecimal BPS;
    /**
     * MGJYXJJE 每股经营现金流量(元)
     *  *         "MGJYXJJE": 0.261280616914,
     */
    private BigDecimal MGJYXJJE;
    /**
     * XSMLL 销售毛利率(%)
     *  *         "XSMLL": 59.4427228492,
     */
    private BigDecimal XSMLL;

    /**
     * SJLHZ 净利润-季度环比增长(%)
     *  *         "SJLHZ": 362.4657,
     */
    private BigDecimal SJLHZ;
    /**
     * ASSIGNDSCRPT 分配转增描述
     * 不分配不转增
     */
    private String ASSIGNDSCRPT;
    /**
     * PAYYEAR
     *  *         "PAYYEAR": null,
     */
    private String PAYYEAR;
    /**
     * PUBLISHNAME 行业
     *  *         "PUBLISHNAME": "文化传媒",
     */
    private String PUBLISHNAME;
    /**
     * ZXGXL
     *  *         "ZXGXL": null,
     */
    private String ZXGXL;
    /**
     * NOTICE_DATE 首次公告日期
     *  *         "NOTICE_DATE": "2021-10-27 00:00:00",
     */
    private String NOTICE_DATE;
    /**
     * ORG_CODE
     *  *         "ORG_CODE": "10175652",
     */
    private String ORG_CODE;
    /**
     * TRADE_MARKET_ZJG
     *        "TRADE_MARKET_ZJG": "0201",
     */
    private String TRADE_MARKET_ZJG;
    /**
     * ISNEW 是否最新
     *  *         "ISNEW": "1",
     */
    private String ISNEW;
    /**
     * QDATE 季度类型
     *  *         "QDATE": "2021Q3",
     */
    private String QDATE;
    /**
     * DATATYPE 报告期类型
     *  *         "DATATYPE": "2021年 三季报",
     */
    private String DATATYPE;
    /**
     * DATAYEAR 数据年份
     *  *         "DATAYEAR": "2021",
     */
    private String DATAYEAR;
    /**
     * DATEMMDD 数据季度
     *  *         "DATEMMDD": "三季报",
     */
    private String DATEMMDD;
    /**
     * EITIME
     *  *         "EITIME": "2021-10-26 17:36:23",
     */
    private String EITIME;
    /**
     * SECUCODE
     *  *         "SECUCODE": "002624.SZ"
     */
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
