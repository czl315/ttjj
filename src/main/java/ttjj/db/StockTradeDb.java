package ttjj.db;

import java.math.BigDecimal;

/**
 * 交易-股票
 */
public class StockTradeDb {
    /**
     * FD_CODE 证券编码
     */
    private String FD_CODE;
    private String FD_INFO;
    private String TRADE_TIME;
    private String ORDER_STATUS;
    private Double CONFIRM_NET;
    private Double ORDER_AMT;
    private String STATUS;
    private String ORDER_CODE;
    private Double CONFIRM_AMT;
    private String CONFIRM_NET_DATA;
    private Double SERVER_CHARGE;
    private String SOURCE;
    private String CREATE_TIME;
    private String UPDATE_TIME;
    private String BIZ_TP;
    private Double RK_ST_LOSS;
    private Double RK_ST_PROFIT;
    /**
     * conception 概念-要点内容
     */
    private String conception;

    /**
     * CONFIRM_SHARE 确认份额-买入份额
     */
    private Double CONFIRM_SHARE;

    private String TYPE;
    private Double NET_REDEM;
    private Double REDEM_SHARE;
    private String REDEM_TIME;
    private Double REDEM_AMT;
    private BigDecimal SERVER_CHARGE_REDEM;
    private String REDEM_STATUS;
    private BigDecimal EARN_AMT;

    private Double LAST_NET;
    /**
     * NET_MIN_1 净值-最低-1天
     */
    private Double NET_MIN_1;
    private Double NET_MIN_7;
    private Double NET_MIN_14;
    private Double NET_MIN_30;
    private Double NET_MIN_60;
    private Double NET_MIN_90;
    private Double NET_MIN_180;
    private Double NET_MIN_360;
    private Double NET_MAX_1;
    private Double NET_MAX_7;
    private Double NET_MAX_14;
    private Double NET_MAX_30;
    private Double NET_MAX_60;
    private Double NET_MAX_90;
    private Double NET_MAX_180;
    private Double NET_MAX_360;
    private Double NET_MIN_CLOS_1;
    private Double NET_MIN_CLOS_7;
    private Double NET_MIN_CLOS_14;
    private Double NET_MIN_CLOS_30;
    private Double NET_MIN_CLOS_60;
    private Double NET_MIN_CLOS_90;
    private Double NET_MIN_CLOS_180;
    private Double NET_MIN_CLOS_360;
    private Double NET_MAX_CLOS_1;
    private Double NET_MAX_CLOS_7;
    private Double NET_MAX_CLOS_14;
    private Double NET_MAX_CLOS_30;
    private Double NET_MAX_CLOS_60;
    private Double NET_MAX_CLOS_90;
    private Double NET_MAX_CLOS_180;
    private Double NET_MAX_CLOS_360;

    public String getFD_INFO() {
        return FD_INFO;
    }

    public void setFD_INFO(String FD_INFO) {
        this.FD_INFO = FD_INFO;
    }

    public String getTRADE_TIME() {
        return TRADE_TIME;
    }

    public void setTRADE_TIME(String TRADE_TIME) {
        this.TRADE_TIME = TRADE_TIME;
    }

    public String getORDER_STATUS() {
        return ORDER_STATUS;
    }

    public void setORDER_STATUS(String ORDER_STATUS) {
        this.ORDER_STATUS = ORDER_STATUS;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getORDER_CODE() {
        return ORDER_CODE;
    }

    public void setORDER_CODE(String ORDER_CODE) {
        this.ORDER_CODE = ORDER_CODE;
    }

    public String getCONFIRM_NET_DATA() {
        return CONFIRM_NET_DATA;
    }

    public void setCONFIRM_NET_DATA(String CONFIRM_NET_DATA) {
        this.CONFIRM_NET_DATA = CONFIRM_NET_DATA;
    }

    public String getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(String CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public String getUPDATE_TIME() {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(String UPDATE_TIME) {
        this.UPDATE_TIME = UPDATE_TIME;
    }

    public String getBIZ_TP() {
        return BIZ_TP;
    }

    public void setBIZ_TP(String BIZ_TP) {
        this.BIZ_TP = BIZ_TP;
    }

    public Double getRK_ST_LOSS() {
        return RK_ST_LOSS;
    }

    public void setRK_ST_LOSS(Double RK_ST_LOSS) {
        this.RK_ST_LOSS = RK_ST_LOSS;
    }

    public Double getRK_ST_PROFIT() {
        return RK_ST_PROFIT;
    }

    public void setRK_ST_PROFIT(Double RK_ST_PROFIT) {
        this.RK_ST_PROFIT = RK_ST_PROFIT;
    }

    public Double getCONFIRM_SHARE() {
        return CONFIRM_SHARE;
    }

    public void setCONFIRM_SHARE(Double CONFIRM_SHARE) {
        this.CONFIRM_SHARE = CONFIRM_SHARE;
    }

    public String getFD_CODE() {
        return FD_CODE;
    }

    public void setFD_CODE(String FD_CODE) {
        this.FD_CODE = FD_CODE;
    }

    public Double getNET_MIN_1() {
        return NET_MIN_1;
    }

    public void setNET_MIN_1(Double NET_MIN_1) {
        this.NET_MIN_1 = NET_MIN_1;
    }

    public Double getNET_MIN_7() {
        return NET_MIN_7;
    }

    public void setNET_MIN_7(Double NET_MIN_7) {
        this.NET_MIN_7 = NET_MIN_7;
    }

    public Double getNET_MIN_14() {
        return NET_MIN_14;
    }

    public void setNET_MIN_14(Double NET_MIN_14) {
        this.NET_MIN_14 = NET_MIN_14;
    }

    public Double getNET_MIN_30() {
        return NET_MIN_30;
    }

    public void setNET_MIN_30(Double NET_MIN_30) {
        this.NET_MIN_30 = NET_MIN_30;
    }

    public Double getNET_MIN_60() {
        return NET_MIN_60;
    }

    public void setNET_MIN_60(Double NET_MIN_60) {
        this.NET_MIN_60 = NET_MIN_60;
    }

    public Double getNET_MIN_90() {
        return NET_MIN_90;
    }

    public void setNET_MIN_90(Double NET_MIN_90) {
        this.NET_MIN_90 = NET_MIN_90;
    }

    public Double getNET_MIN_180() {
        return NET_MIN_180;
    }

    public void setNET_MIN_180(Double NET_MIN_180) {
        this.NET_MIN_180 = NET_MIN_180;
    }

    public Double getNET_MIN_360() {
        return NET_MIN_360;
    }

    public void setNET_MIN_360(Double NET_MIN_360) {
        this.NET_MIN_360 = NET_MIN_360;
    }

    public Double getNET_MAX_1() {
        return NET_MAX_1;
    }

    public void setNET_MAX_1(Double NET_MAX_1) {
        this.NET_MAX_1 = NET_MAX_1;
    }

    public Double getNET_MAX_7() {
        return NET_MAX_7;
    }

    public void setNET_MAX_7(Double NET_MAX_7) {
        this.NET_MAX_7 = NET_MAX_7;
    }

    public Double getNET_MAX_14() {
        return NET_MAX_14;
    }

    public void setNET_MAX_14(Double NET_MAX_14) {
        this.NET_MAX_14 = NET_MAX_14;
    }

    public Double getNET_MAX_30() {
        return NET_MAX_30;
    }

    public void setNET_MAX_30(Double NET_MAX_30) {
        this.NET_MAX_30 = NET_MAX_30;
    }

    public Double getNET_MAX_60() {
        return NET_MAX_60;
    }

    public void setNET_MAX_60(Double NET_MAX_60) {
        this.NET_MAX_60 = NET_MAX_60;
    }

    public Double getNET_MAX_90() {
        return NET_MAX_90;
    }

    public void setNET_MAX_90(Double NET_MAX_90) {
        this.NET_MAX_90 = NET_MAX_90;
    }

    public Double getNET_MAX_180() {
        return NET_MAX_180;
    }

    public void setNET_MAX_180(Double NET_MAX_180) {
        this.NET_MAX_180 = NET_MAX_180;
    }

    public Double getNET_MAX_360() {
        return NET_MAX_360;
    }

    public void setNET_MAX_360(Double NET_MAX_360) {
        this.NET_MAX_360 = NET_MAX_360;
    }

    public Double getNET_MIN_CLOS_1() {
        return NET_MIN_CLOS_1;
    }

    public void setNET_MIN_CLOS_1(Double NET_MIN_CLOS_1) {
        this.NET_MIN_CLOS_1 = NET_MIN_CLOS_1;
    }

    public Double getNET_MIN_CLOS_7() {
        return NET_MIN_CLOS_7;
    }

    public void setNET_MIN_CLOS_7(Double NET_MIN_CLOS_7) {
        this.NET_MIN_CLOS_7 = NET_MIN_CLOS_7;
    }

    public Double getNET_MIN_CLOS_14() {
        return NET_MIN_CLOS_14;
    }

    public void setNET_MIN_CLOS_14(Double NET_MIN_CLOS_14) {
        this.NET_MIN_CLOS_14 = NET_MIN_CLOS_14;
    }

    public Double getNET_MIN_CLOS_30() {
        return NET_MIN_CLOS_30;
    }

    public void setNET_MIN_CLOS_30(Double NET_MIN_CLOS_30) {
        this.NET_MIN_CLOS_30 = NET_MIN_CLOS_30;
    }

    public Double getNET_MIN_CLOS_60() {
        return NET_MIN_CLOS_60;
    }

    public void setNET_MIN_CLOS_60(Double NET_MIN_CLOS_60) {
        this.NET_MIN_CLOS_60 = NET_MIN_CLOS_60;
    }

    public Double getNET_MIN_CLOS_90() {
        return NET_MIN_CLOS_90;
    }

    public void setNET_MIN_CLOS_90(Double NET_MIN_CLOS_90) {
        this.NET_MIN_CLOS_90 = NET_MIN_CLOS_90;
    }

    public Double getNET_MIN_CLOS_180() {
        return NET_MIN_CLOS_180;
    }

    public void setNET_MIN_CLOS_180(Double NET_MIN_CLOS_180) {
        this.NET_MIN_CLOS_180 = NET_MIN_CLOS_180;
    }

    public Double getNET_MIN_CLOS_360() {
        return NET_MIN_CLOS_360;
    }

    public void setNET_MIN_CLOS_360(Double NET_MIN_CLOS_360) {
        this.NET_MIN_CLOS_360 = NET_MIN_CLOS_360;
    }

    public Double getNET_MAX_CLOS_1() {
        return NET_MAX_CLOS_1;
    }

    public void setNET_MAX_CLOS_1(Double NET_MAX_CLOS_1) {
        this.NET_MAX_CLOS_1 = NET_MAX_CLOS_1;
    }

    public Double getNET_MAX_CLOS_7() {
        return NET_MAX_CLOS_7;
    }

    public void setNET_MAX_CLOS_7(Double NET_MAX_CLOS_7) {
        this.NET_MAX_CLOS_7 = NET_MAX_CLOS_7;
    }

    public Double getNET_MAX_CLOS_14() {
        return NET_MAX_CLOS_14;
    }

    public void setNET_MAX_CLOS_14(Double NET_MAX_CLOS_14) {
        this.NET_MAX_CLOS_14 = NET_MAX_CLOS_14;
    }

    public Double getNET_MAX_CLOS_30() {
        return NET_MAX_CLOS_30;
    }

    public void setNET_MAX_CLOS_30(Double NET_MAX_CLOS_30) {
        this.NET_MAX_CLOS_30 = NET_MAX_CLOS_30;
    }

    public Double getNET_MAX_CLOS_60() {
        return NET_MAX_CLOS_60;
    }

    public void setNET_MAX_CLOS_60(Double NET_MAX_CLOS_60) {
        this.NET_MAX_CLOS_60 = NET_MAX_CLOS_60;
    }

    public Double getNET_MAX_CLOS_90() {
        return NET_MAX_CLOS_90;
    }

    public void setNET_MAX_CLOS_90(Double NET_MAX_CLOS_90) {
        this.NET_MAX_CLOS_90 = NET_MAX_CLOS_90;
    }

    public Double getNET_MAX_CLOS_180() {
        return NET_MAX_CLOS_180;
    }

    public void setNET_MAX_CLOS_180(Double NET_MAX_CLOS_180) {
        this.NET_MAX_CLOS_180 = NET_MAX_CLOS_180;
    }

    public Double getNET_MAX_CLOS_360() {
        return NET_MAX_CLOS_360;
    }

    public void setNET_MAX_CLOS_360(Double NET_MAX_CLOS_360) {
        this.NET_MAX_CLOS_360 = NET_MAX_CLOS_360;
    }

    public Double getLAST_NET() {
        return LAST_NET;
    }

    public void setLAST_NET(Double LAST_NET) {
        this.LAST_NET = LAST_NET;
    }

    public String getConception() {
        return conception;
    }

    public void setConception(String conception) {
        this.conception = conception;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public Double getNET_REDEM() {
        return NET_REDEM;
    }

    public void setNET_REDEM(Double NET_REDEM) {
        this.NET_REDEM = NET_REDEM;
    }

    public Double getREDEM_SHARE() {
        return REDEM_SHARE;
    }

    public void setREDEM_SHARE(Double REDEM_SHARE) {
        this.REDEM_SHARE = REDEM_SHARE;
    }

    public String getREDEM_TIME() {
        return REDEM_TIME;
    }

    public void setREDEM_TIME(String REDEM_TIME) {
        this.REDEM_TIME = REDEM_TIME;
    }

    public Double getREDEM_AMT() {
        return REDEM_AMT;
    }

    public void setREDEM_AMT(Double REDEM_AMT) {
        this.REDEM_AMT = REDEM_AMT;
    }

    public BigDecimal getSERVER_CHARGE_REDEM() {
        return SERVER_CHARGE_REDEM;
    }

    public void setSERVER_CHARGE_REDEM(BigDecimal SERVER_CHARGE_REDEM) {
        this.SERVER_CHARGE_REDEM = SERVER_CHARGE_REDEM;
    }

    public String getREDEM_STATUS() {
        return REDEM_STATUS;
    }

    public void setREDEM_STATUS(String REDEM_STATUS) {
        this.REDEM_STATUS = REDEM_STATUS;
    }

    public BigDecimal getEARN_AMT() {
        return EARN_AMT;
    }

    public void setEARN_AMT(BigDecimal EARN_AMT) {
        this.EARN_AMT = EARN_AMT;
    }

    public Double getCONFIRM_NET() {
        return CONFIRM_NET;
    }

    public void setCONFIRM_NET(Double CONFIRM_NET) {
        this.CONFIRM_NET = CONFIRM_NET;
    }

    public Double getORDER_AMT() {
        return ORDER_AMT;
    }

    public void setORDER_AMT(Double ORDER_AMT) {
        this.ORDER_AMT = ORDER_AMT;
    }

    public Double getCONFIRM_AMT() {
        return CONFIRM_AMT;
    }

    public void setCONFIRM_AMT(Double CONFIRM_AMT) {
        this.CONFIRM_AMT = CONFIRM_AMT;
    }

    public Double getSERVER_CHARGE() {
        return SERVER_CHARGE;
    }

    public void setSERVER_CHARGE(Double SERVER_CHARGE) {
        this.SERVER_CHARGE = SERVER_CHARGE;
    }

    public String getSOURCE() {
        return SOURCE;
    }

    public void setSOURCE(String SOURCE) {
        this.SOURCE = SOURCE;
    }
}
