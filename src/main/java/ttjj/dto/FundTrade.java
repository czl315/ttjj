package ttjj.dto;

import java.math.BigDecimal;

/**
 * 交易
 */
public class FundTrade {
    private int id;
    private String fundInfo;//基金信息
    private String confirmNetData;//确认净值日期
    private BigDecimal confirmShare;//确认份额
    private BigDecimal confirmNet;//确认净值
    private BigDecimal orderAmt;//订单金额
    private BigDecimal serverCharge;//手续费
    private String orderStatus;//订单状态
    private String tradeTime;//交易发起时间
    /**
     * 基金编码
     */
    private String fundCode;

    public FundTrade() {
    }

    public FundTrade(int id, String fundInfo, String confirmNetData, String confirmShare, String confirmNet, String orderAmt,
                     String serverCharge, String orderStatus) {
        this.id = id;
        this.fundInfo = fundInfo;
        this.confirmNetData = confirmNetData;
        this.confirmShare = new BigDecimal(confirmShare);
        this.confirmNet = new BigDecimal(confirmNet);
        this.orderAmt = new BigDecimal(orderAmt);
        this.serverCharge = new BigDecimal(serverCharge);
        this.orderStatus = orderStatus;
    }
//  `FD_ID` varchar(100) DEFAULT NULL COMMENT '基金ID',
//            `TYPE` varchar(50) DEFAULT NULL COMMENT '交易类型:1-申购；0-赎回；',
//            `TRADE_TIME` datetime DEFAULT NULL COMMENT '交易时间',
//            `ORDER_STATUS` varchar(10) DEFAULT NULL COMMENT '订单状态',
//            `STATUS` varchar(50) DEFAULT NULL COMMENT '交易状态：1-确认成功',
//            `TRADE_CODE` varchar(50) DEFAULT NULL COMMENT '交易/赎回单号',
//            `TRADE_CODE_BUY` varchar(50) DEFAULT NULL COMMENT '交易单号申购',
//            `TRADE_CODE_REDEM` varchar(50) DEFAULT NULL COMMENT '交易单号赎回',
//            `ORDER_CODE` varchar(50) DEFAULT NULL COMMENT '订单编号',
//            `CONFIRM_AMT` double DEFAULT NULL COMMENT '确认金额(购买)',
//            `REDEM_AMT` double DEFAULT NULL COMMENT '赎回金额',
//            `EARN_AMT` double DEFAULT NULL COMMENT '收益金额',
//            `CONFIRM_NET_DATA` varchar(20) DEFAULT NULL COMMENT '确认净值日期',
//            `SERVER_CHARGE` double DEFAULT NULL COMMENT '手续费',
//            `PROCESS_TIME` datetime DEFAULT NULL COMMENT '受理时间',
//            `EARN_TIME` datetime DEFAULT NULL COMMENT '收益开始时间',
//            `TRADE_ACCT` varchar(20) DEFAULT NULL COMMENT '交易账户',
//            `REDEM_STATUS` varchar(20) DEFAULT NULL COMMENT '赎回状态',
//            `REDEM_SHARE` double DEFAULT NULL COMMENT '赎回份数',
//            `REDEM_TIME` datetime DEFAULT NULL COMMENT '赎回时间',
//            `REDEM_ACCT_TIME` datetime DEFAULT NULL COMMENT '赎回到账时间',
//            `SOURCE` varchar(50) DEFAULT NULL COMMENT '交易渠道',
//            `FD_CODE` varchar(100) DEFAULT NULL COMMENT '基金代码',
//            `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
//            `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
//    PRIMARY KEY (`ID`) USING BTREE
//) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='基金交易记录';


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFundInfo() {
        return fundInfo;
    }

    public void setFundInfo(String fundInfo) {
        this.fundInfo = fundInfo;
    }

    public String getConfirmNetData() {
        return confirmNetData;
    }

    public void setConfirmNetData(String confirmNetData) {
        this.confirmNetData = confirmNetData;
    }

    public BigDecimal getConfirmShare() {
        return confirmShare;
    }

    public void setConfirmShare(BigDecimal confirmShare) {
        this.confirmShare = confirmShare;
    }

    public BigDecimal getConfirmNet() {
        return confirmNet;
    }

    public void setConfirmNet(BigDecimal confirmNet) {
        this.confirmNet = confirmNet;
    }

    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    public BigDecimal getServerCharge() {
        return serverCharge;
    }

    public void setServerCharge(BigDecimal serverCharge) {
        this.serverCharge = serverCharge;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }
}
