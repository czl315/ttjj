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
     * REPORT_NAME 报告类型
     */
    private String REPORT_TYPE;

    /** 预报特殊字段-开始 **/
    /**
     * PREDICT_FINANCE_CODE 预测指标-编码
     * "PREDICT_FINANCE_CODE": "006",
     */
    private String PREDICT_FINANCE_CODE;
    /**
     * PREDICT_FINANCE 预测指标-名称
     * "PREDICT_FINANCE": "营业收入","PREDICT_FINANCE": "扣除非经常性损益后的净利润",
     */
    private String PREDICT_FINANCE;
    /**
     * 预测数值(元)-下限
     * "PREDICT_AMT_LOWER": 290000000,
     */
    private BigDecimal PREDICT_AMT_LOWER;
    /**
     * 预测数值(元)-上限
     * "PREDICT_AMT_UPPER": 290000000,
     */
    private BigDecimal PREDICT_AMT_UPPER;
    /**
     * "FORECAST_JZ": 290000000,
     */
    private BigDecimal FORECAST_JZ;
    /**
     * 业绩变动幅度-下限
     * "ADD_AMP_LOWER": 75.1,
     */
    private BigDecimal ADD_AMP_LOWER;
    /**
     * 业绩变动幅度-上限
     * "ADD_AMP_UPPER": 75.1,
     */
    private BigDecimal ADD_AMP_UPPER;
    /**
     * 预告-增长-？
     * "INCREASE_JZ": 75.1,
     */
    private BigDecimal INCREASE_JZ;

    /**
     * 预报-业绩变动-概述
     */
    private String PREDICT_CONTENT;
    /**
     * 预报-业绩变动-原因(字段较多，不建议保存)
     */
    private String CHANGE_REASON_EXPLAIN;
    /**
     * 预告-类型
     * "PREDICT_TYPE": "预增",
     */
    private String PREDICT_TYPE;
    /**
     * "FORECAST_STATE": "increase",
     */
    private String FORECAST_STATE;
    /**
     * 是否最新
     * "IS_LATEST": "T"
     */
    private String IS_LATEST;
    /**
     * "PREYEAR_SAME_PERIOD": 165619400,
     */
    private BigDecimal PREYEAR_SAME_PERIOD;

/** 预报特殊字段-结束 **/





    /**
     * REPORT_DATE 报表日期
     */
    private String REPORT_DATE;
    private BigDecimal TOTAL_OPERATE_INCOME_SQ;
        private BigDecimal PARENT_NETPROFIT_SQ;


    /**
     * MARKET "MARKET": "0102",
     */
    private String MARKET;
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
     * DJDYSHZ
     */
    private BigDecimal DJDYSHZ;

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
     * 净利润-同比增长(%)
     * SJLTZ (历史)
     *  *         "SJLTZ": -55.72,
     */
    private BigDecimal SJLTZ;
    private BigDecimal JLRTBZCL;
    /**
     * BPS 每股净资产(元)
     *         "BPS": 5.585338538859,
     */
//    private BigDecimal BPS;
    private BigDecimal PARENT_BVPS;
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
     * DJDJLHZ "DJDYSHZ": -60.296897018451,季度环比增长
     */
    private BigDecimal DJDJLHZ;
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

    public String getREPORT_DATE() {
        return REPORT_DATE;
    }

    public void setREPORT_DATE(String REPORT_DATE) {
        this.REPORT_DATE = REPORT_DATE;
    }

    public BigDecimal getTOTAL_OPERATE_INCOME_SQ() {
        return TOTAL_OPERATE_INCOME_SQ;
    }

    public void setTOTAL_OPERATE_INCOME_SQ(BigDecimal TOTAL_OPERATE_INCOME_SQ) {
        this.TOTAL_OPERATE_INCOME_SQ = TOTAL_OPERATE_INCOME_SQ;
    }

    public BigDecimal getPARENT_NETPROFIT_SQ() {
        return PARENT_NETPROFIT_SQ;
    }

    public void setPARENT_NETPROFIT_SQ(BigDecimal PARENT_NETPROFIT_SQ) {
        this.PARENT_NETPROFIT_SQ = PARENT_NETPROFIT_SQ;
    }

    public BigDecimal getPARENT_BVPS() {
        return PARENT_BVPS;
    }

    public void setPARENT_BVPS(BigDecimal PARENT_BVPS) {
        this.PARENT_BVPS = PARENT_BVPS;
    }

    public BigDecimal getJLRTBZCL() {
        return JLRTBZCL;
    }

    public void setJLRTBZCL(BigDecimal JLRTBZCL) {
        this.JLRTBZCL = JLRTBZCL;
    }

    public BigDecimal getDJDYSHZ() {
        return DJDYSHZ;
    }

    public void setDJDYSHZ(BigDecimal DJDYSHZ) {
        this.DJDYSHZ = DJDYSHZ;
    }

    public BigDecimal getDJDJLHZ() {
        return DJDJLHZ;
    }

    public void setDJDJLHZ(BigDecimal DJDJLHZ) {
        this.DJDJLHZ = DJDJLHZ;
    }

    public String getMARKET() {
        return MARKET;
    }

    public void setMARKET(String MARKET) {
        this.MARKET = MARKET;
    }

    public BigDecimal getYSHZ() {
        return YSHZ;
    }

    public void setYSHZ(BigDecimal YSHZ) {
        this.YSHZ = YSHZ;
    }

    public BigDecimal getSJLTZ() {
        return SJLTZ;
    }

    public void setSJLTZ(BigDecimal SJLTZ) {
        this.SJLTZ = SJLTZ;
    }

    public BigDecimal getSJLHZ() {
        return SJLHZ;
    }

    public void setSJLHZ(BigDecimal SJLHZ) {
        this.SJLHZ = SJLHZ;
    }

    public String getPREDICT_FINANCE_CODE() {
        return PREDICT_FINANCE_CODE;
    }

    public void setPREDICT_FINANCE_CODE(String PREDICT_FINANCE_CODE) {
        this.PREDICT_FINANCE_CODE = PREDICT_FINANCE_CODE;
    }

    public String getPREDICT_FINANCE() {
        return PREDICT_FINANCE;
    }

    public void setPREDICT_FINANCE(String PREDICT_FINANCE) {
        this.PREDICT_FINANCE = PREDICT_FINANCE;
    }

    public BigDecimal getPREDICT_AMT_LOWER() {
        return PREDICT_AMT_LOWER;
    }

    public void setPREDICT_AMT_LOWER(BigDecimal PREDICT_AMT_LOWER) {
        this.PREDICT_AMT_LOWER = PREDICT_AMT_LOWER;
    }

    public BigDecimal getPREDICT_AMT_UPPER() {
        return PREDICT_AMT_UPPER;
    }

    public void setPREDICT_AMT_UPPER(BigDecimal PREDICT_AMT_UPPER) {
        this.PREDICT_AMT_UPPER = PREDICT_AMT_UPPER;
    }

    public BigDecimal getFORECAST_JZ() {
        return FORECAST_JZ;
    }

    public void setFORECAST_JZ(BigDecimal FORECAST_JZ) {
        this.FORECAST_JZ = FORECAST_JZ;
    }

    public BigDecimal getADD_AMP_LOWER() {
        return ADD_AMP_LOWER;
    }

    public void setADD_AMP_LOWER(BigDecimal ADD_AMP_LOWER) {
        this.ADD_AMP_LOWER = ADD_AMP_LOWER;
    }

    public BigDecimal getADD_AMP_UPPER() {
        return ADD_AMP_UPPER;
    }

    public void setADD_AMP_UPPER(BigDecimal ADD_AMP_UPPER) {
        this.ADD_AMP_UPPER = ADD_AMP_UPPER;
    }

    public BigDecimal getINCREASE_JZ() {
        return INCREASE_JZ;
    }

    public void setINCREASE_JZ(BigDecimal INCREASE_JZ) {
        this.INCREASE_JZ = INCREASE_JZ;
    }

    public String getPREDICT_CONTENT() {
        return PREDICT_CONTENT;
    }

    public void setPREDICT_CONTENT(String PREDICT_CONTENT) {
        this.PREDICT_CONTENT = PREDICT_CONTENT;
    }

    public String getCHANGE_REASON_EXPLAIN() {
        return CHANGE_REASON_EXPLAIN;
    }

    public void setCHANGE_REASON_EXPLAIN(String CHANGE_REASON_EXPLAIN) {
        this.CHANGE_REASON_EXPLAIN = CHANGE_REASON_EXPLAIN;
    }

    public String getPREDICT_TYPE() {
        return PREDICT_TYPE;
    }

    public void setPREDICT_TYPE(String PREDICT_TYPE) {
        this.PREDICT_TYPE = PREDICT_TYPE;
    }

    public String getFORECAST_STATE() {
        return FORECAST_STATE;
    }

    public void setFORECAST_STATE(String FORECAST_STATE) {
        this.FORECAST_STATE = FORECAST_STATE;
    }

    public String getIS_LATEST() {
        return IS_LATEST;
    }

    public void setIS_LATEST(String IS_LATEST) {
        this.IS_LATEST = IS_LATEST;
    }

    public BigDecimal getPREYEAR_SAME_PERIOD() {
        return PREYEAR_SAME_PERIOD;
    }

    public void setPREYEAR_SAME_PERIOD(BigDecimal PREYEAR_SAME_PERIOD) {
        this.PREYEAR_SAME_PERIOD = PREYEAR_SAME_PERIOD;
    }

    public String getREPORT_TYPE() {
        return REPORT_TYPE;
    }

    public void setREPORT_TYPE(String REPORT_TYPE) {
        this.REPORT_TYPE = REPORT_TYPE;
    }
}
