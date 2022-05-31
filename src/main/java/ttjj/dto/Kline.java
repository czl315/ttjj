package ttjj.dto;

import java.math.BigDecimal;

/**
 * K线返回结果
 * "2021-06-23 09:35,1.438,1.443,1.446,1.437,490887,70765420.000,0.63,0.49,0.007,0.64"
 * //  日期时间，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率
 */
public class Kline {
    /**
     * 日期时间
     */
    private String ktime;
    /**
     * date 日期
     */
    private String date;
    /**
     * month
     */
    private String month;
    /**
     * weekYear
     */
    private String weekYear;
    /**
     * week
     */
    private String week;
    /**
     * type 类型：bk-板块；gn-概念；etf；
     */
    private String type;
    /**
     * zqdm 证券代码
     */
    private String zqdm;
    /**
     * zqmc 证券名称
     */
    private String zqmc;
    /**
     * klt 周期
     */
    private String klt;
    /**
     * rs 返回结果
     */
    private String rs;
    /**
     * 开盘价
     */
    private BigDecimal openAmt;
    /**
     * 收盘价
     */
    private BigDecimal closeAmt;
    /**
     * 收盘价-上一期
     */
    private BigDecimal closeLastAmt;
    /**
     * 最高
     */
    private BigDecimal maxAmt;
    /**
     * 最低
     */
    private BigDecimal minAmt;
    /**
     * 成交量
     */
    private BigDecimal cjl;
    /**
     * 成交额
     */
    private BigDecimal cje;
    /**
     * 振幅
     */
    private BigDecimal zhenFu;
    /**
     * 涨跌幅
     */
    private BigDecimal zhangDieFu;
    /**
     * 涨跌额
     */
    private BigDecimal zhangDieE;
    /**
     * 换手率
     */
    private BigDecimal huanShouLv;

    /**
     * NET_MIN_5 净值-最小-n个
     */
    private BigDecimal NET_MIN_5;
    private BigDecimal NET_MIN_10;
    private BigDecimal NET_MIN_20;
    private BigDecimal NET_MIN_30;
    private BigDecimal NET_MIN_60;
    private BigDecimal NET_MIN_120;
    private BigDecimal NET_MIN_250;
    /**
     * NET_MAX_5 净值-最大-n个
     */
    private BigDecimal NET_MAX_5;
    private BigDecimal NET_MAX_10;
    private BigDecimal NET_MAX_20;
    private BigDecimal NET_MAX_30;
    private BigDecimal NET_MAX_60;
    private BigDecimal NET_MAX_120;
    private BigDecimal NET_MAX_250;
    /**
     * NET_MIN_CLOS_5 净值-最小收盘-n个
     */
    private BigDecimal NET_MIN_CLOS_5;
    private BigDecimal NET_MIN_CLOS_10;
    private BigDecimal NET_MIN_CLOS_20;
    private BigDecimal NET_MIN_CLOS_30;
    private BigDecimal NET_MIN_CLOS_60;
    private BigDecimal NET_MIN_CLOS_120;
    private BigDecimal NET_MIN_CLOS_250;
    /**
     * NET_MAX_CLOS_5 NET_MAX_5 净值-最大收盘-n个
     */
    private BigDecimal NET_MAX_CLOS_5;
    private BigDecimal NET_MAX_CLOS_10;
    private BigDecimal NET_MAX_CLOS_20;
    private BigDecimal NET_MAX_CLOS_30;
    private BigDecimal NET_MAX_CLOS_60;
    private BigDecimal NET_MAX_CLOS_120;
    private BigDecimal NET_MAX_CLOS_250;

    /**
     * NET_MA_5 净值-均线-n个
     */
    private BigDecimal NET_MA_5;
    private BigDecimal NET_MA_10;
    private BigDecimal NET_MA_20;
    private BigDecimal NET_MA_30;
    private BigDecimal NET_MA_60;
    private BigDecimal NET_MA_120;
    private BigDecimal NET_MA_250;

    /**
     *  主力净流入
     */
    private BigDecimal flowInMain;
    /**
     *  小单净流入
     */
    private BigDecimal flowInSmall;
    /**
     *  中单净流入
     */
    private BigDecimal flowInMid;
    /**
     *  大单净流入
     */
    private BigDecimal flowInBig;
    /**
     *  超大单净流入
     */
    private BigDecimal flowInSuperBig;

    public String getKtime() {
        return ktime;
    }

    public void setKtime(String ktime) {
        this.ktime = ktime;
    }

    public BigDecimal getOpenAmt() {
        return openAmt;
    }

    public void setOpenAmt(BigDecimal openAmt) {
        this.openAmt = openAmt;
    }

    public BigDecimal getCloseAmt() {
        return closeAmt;
    }

    public void setCloseAmt(BigDecimal closeAmt) {
        this.closeAmt = closeAmt;
    }

    public BigDecimal getCloseLastAmt() {
        return closeLastAmt;
    }

    public void setCloseLastAmt(BigDecimal closeLastAmt) {
        this.closeLastAmt = closeLastAmt;
    }

    public BigDecimal getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(BigDecimal maxAmt) {
        this.maxAmt = maxAmt;
    }

    public BigDecimal getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(BigDecimal minAmt) {
        this.minAmt = minAmt;
    }

    public BigDecimal getCjl() {
        return cjl;
    }

    public void setCjl(BigDecimal cjl) {
        this.cjl = cjl;
    }

    public BigDecimal getCje() {
        return cje;
    }

    public void setCje(BigDecimal cje) {
        this.cje = cje;
    }

    public BigDecimal getZhenFu() {
        return zhenFu;
    }

    public void setZhenFu(BigDecimal zhenFu) {
        this.zhenFu = zhenFu;
    }

    public BigDecimal getZhangDieFu() {
        return zhangDieFu;
    }

    public void setZhangDieFu(BigDecimal zhangDieFu) {
        this.zhangDieFu = zhangDieFu;
    }

    public BigDecimal getZhangDieE() {
        return zhangDieE;
    }

    public void setZhangDieE(BigDecimal zhangDieE) {
        this.zhangDieE = zhangDieE;
    }

    public BigDecimal getHuanShouLv() {
        return huanShouLv;
    }

    public void setHuanShouLv(BigDecimal huanShouLv) {
        this.huanShouLv = huanShouLv;
    }

    public String getZqdm() {
        return zqdm;
    }

    public void setZqdm(String zqdm) {
        this.zqdm = zqdm;
    }

    public String getZqmc() {
        return zqmc;
    }

    public void setZqmc(String zqmc) {
        this.zqmc = zqmc;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getKlt() {
        return klt;
    }

    public void setKlt(String klt) {
        this.klt = klt;
    }

    public BigDecimal getNET_MIN_5() {
        return NET_MIN_5;
    }

    public void setNET_MIN_5(BigDecimal NET_MIN_5) {
        this.NET_MIN_5 = NET_MIN_5;
    }

    public BigDecimal getNET_MIN_10() {
        return NET_MIN_10;
    }

    public void setNET_MIN_10(BigDecimal NET_MIN_10) {
        this.NET_MIN_10 = NET_MIN_10;
    }

    public BigDecimal getNET_MIN_20() {
        return NET_MIN_20;
    }

    public void setNET_MIN_20(BigDecimal NET_MIN_20) {
        this.NET_MIN_20 = NET_MIN_20;
    }

    public BigDecimal getNET_MIN_30() {
        return NET_MIN_30;
    }

    public void setNET_MIN_30(BigDecimal NET_MIN_30) {
        this.NET_MIN_30 = NET_MIN_30;
    }

    public BigDecimal getNET_MIN_60() {
        return NET_MIN_60;
    }

    public void setNET_MIN_60(BigDecimal NET_MIN_60) {
        this.NET_MIN_60 = NET_MIN_60;
    }

    public BigDecimal getNET_MIN_120() {
        return NET_MIN_120;
    }

    public void setNET_MIN_120(BigDecimal NET_MIN_120) {
        this.NET_MIN_120 = NET_MIN_120;
    }

    public BigDecimal getNET_MIN_250() {
        return NET_MIN_250;
    }

    public void setNET_MIN_250(BigDecimal NET_MIN_250) {
        this.NET_MIN_250 = NET_MIN_250;
    }

    public BigDecimal getNET_MAX_5() {
        return NET_MAX_5;
    }

    public void setNET_MAX_5(BigDecimal NET_MAX_5) {
        this.NET_MAX_5 = NET_MAX_5;
    }

    public BigDecimal getNET_MAX_10() {
        return NET_MAX_10;
    }

    public void setNET_MAX_10(BigDecimal NET_MAX_10) {
        this.NET_MAX_10 = NET_MAX_10;
    }

    public BigDecimal getNET_MAX_20() {
        return NET_MAX_20;
    }

    public void setNET_MAX_20(BigDecimal NET_MAX_20) {
        this.NET_MAX_20 = NET_MAX_20;
    }

    public BigDecimal getNET_MAX_30() {
        return NET_MAX_30;
    }

    public void setNET_MAX_30(BigDecimal NET_MAX_30) {
        this.NET_MAX_30 = NET_MAX_30;
    }

    public BigDecimal getNET_MAX_60() {
        return NET_MAX_60;
    }

    public void setNET_MAX_60(BigDecimal NET_MAX_60) {
        this.NET_MAX_60 = NET_MAX_60;
    }

    public BigDecimal getNET_MAX_120() {
        return NET_MAX_120;
    }

    public void setNET_MAX_120(BigDecimal NET_MAX_120) {
        this.NET_MAX_120 = NET_MAX_120;
    }

    public BigDecimal getNET_MAX_250() {
        return NET_MAX_250;
    }

    public void setNET_MAX_250(BigDecimal NET_MAX_250) {
        this.NET_MAX_250 = NET_MAX_250;
    }

    public BigDecimal getNET_MIN_CLOS_5() {
        return NET_MIN_CLOS_5;
    }

    public void setNET_MIN_CLOS_5(BigDecimal NET_MIN_CLOS_5) {
        this.NET_MIN_CLOS_5 = NET_MIN_CLOS_5;
    }

    public BigDecimal getNET_MIN_CLOS_10() {
        return NET_MIN_CLOS_10;
    }

    public void setNET_MIN_CLOS_10(BigDecimal NET_MIN_CLOS_10) {
        this.NET_MIN_CLOS_10 = NET_MIN_CLOS_10;
    }

    public BigDecimal getNET_MIN_CLOS_20() {
        return NET_MIN_CLOS_20;
    }

    public void setNET_MIN_CLOS_20(BigDecimal NET_MIN_CLOS_20) {
        this.NET_MIN_CLOS_20 = NET_MIN_CLOS_20;
    }

    public BigDecimal getNET_MIN_CLOS_30() {
        return NET_MIN_CLOS_30;
    }

    public void setNET_MIN_CLOS_30(BigDecimal NET_MIN_CLOS_30) {
        this.NET_MIN_CLOS_30 = NET_MIN_CLOS_30;
    }

    public BigDecimal getNET_MIN_CLOS_60() {
        return NET_MIN_CLOS_60;
    }

    public void setNET_MIN_CLOS_60(BigDecimal NET_MIN_CLOS_60) {
        this.NET_MIN_CLOS_60 = NET_MIN_CLOS_60;
    }

    public BigDecimal getNET_MIN_CLOS_120() {
        return NET_MIN_CLOS_120;
    }

    public void setNET_MIN_CLOS_120(BigDecimal NET_MIN_CLOS_120) {
        this.NET_MIN_CLOS_120 = NET_MIN_CLOS_120;
    }

    public BigDecimal getNET_MIN_CLOS_250() {
        return NET_MIN_CLOS_250;
    }

    public void setNET_MIN_CLOS_250(BigDecimal NET_MIN_CLOS_250) {
        this.NET_MIN_CLOS_250 = NET_MIN_CLOS_250;
    }

    public BigDecimal getNET_MAX_CLOS_5() {
        return NET_MAX_CLOS_5;
    }

    public void setNET_MAX_CLOS_5(BigDecimal NET_MAX_CLOS_5) {
        this.NET_MAX_CLOS_5 = NET_MAX_CLOS_5;
    }

    public BigDecimal getNET_MAX_CLOS_10() {
        return NET_MAX_CLOS_10;
    }

    public void setNET_MAX_CLOS_10(BigDecimal NET_MAX_CLOS_10) {
        this.NET_MAX_CLOS_10 = NET_MAX_CLOS_10;
    }

    public BigDecimal getNET_MAX_CLOS_20() {
        return NET_MAX_CLOS_20;
    }

    public void setNET_MAX_CLOS_20(BigDecimal NET_MAX_CLOS_20) {
        this.NET_MAX_CLOS_20 = NET_MAX_CLOS_20;
    }

    public BigDecimal getNET_MAX_CLOS_30() {
        return NET_MAX_CLOS_30;
    }

    public void setNET_MAX_CLOS_30(BigDecimal NET_MAX_CLOS_30) {
        this.NET_MAX_CLOS_30 = NET_MAX_CLOS_30;
    }

    public BigDecimal getNET_MAX_CLOS_60() {
        return NET_MAX_CLOS_60;
    }

    public void setNET_MAX_CLOS_60(BigDecimal NET_MAX_CLOS_60) {
        this.NET_MAX_CLOS_60 = NET_MAX_CLOS_60;
    }

    public BigDecimal getNET_MAX_CLOS_120() {
        return NET_MAX_CLOS_120;
    }

    public void setNET_MAX_CLOS_120(BigDecimal NET_MAX_CLOS_120) {
        this.NET_MAX_CLOS_120 = NET_MAX_CLOS_120;
    }

    public BigDecimal getNET_MAX_CLOS_250() {
        return NET_MAX_CLOS_250;
    }

    public void setNET_MAX_CLOS_250(BigDecimal NET_MAX_CLOS_250) {
        this.NET_MAX_CLOS_250 = NET_MAX_CLOS_250;
    }

    public BigDecimal getNET_MA_5() {
        return NET_MA_5;
    }

    public void setNET_MA_5(BigDecimal NET_MA_5) {
        this.NET_MA_5 = NET_MA_5;
    }

    public BigDecimal getNET_MA_10() {
        return NET_MA_10;
    }

    public void setNET_MA_10(BigDecimal NET_MA_10) {
        this.NET_MA_10 = NET_MA_10;
    }

    public BigDecimal getNET_MA_20() {
        return NET_MA_20;
    }

    public void setNET_MA_20(BigDecimal NET_MA_20) {
        this.NET_MA_20 = NET_MA_20;
    }

    public BigDecimal getNET_MA_30() {
        return NET_MA_30;
    }

    public void setNET_MA_30(BigDecimal NET_MA_30) {
        this.NET_MA_30 = NET_MA_30;
    }

    public BigDecimal getNET_MA_60() {
        return NET_MA_60;
    }

    public void setNET_MA_60(BigDecimal NET_MA_60) {
        this.NET_MA_60 = NET_MA_60;
    }

    public BigDecimal getNET_MA_120() {
        return NET_MA_120;
    }

    public void setNET_MA_120(BigDecimal NET_MA_120) {
        this.NET_MA_120 = NET_MA_120;
    }

    public BigDecimal getNET_MA_250() {
        return NET_MA_250;
    }

    public void setNET_MA_250(BigDecimal NET_MA_250) {
        this.NET_MA_250 = NET_MA_250;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeekYear() {
        return weekYear;
    }

    public void setWeekYear(String weekYear) {
        this.weekYear = weekYear;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getFlowInMain() {
        return flowInMain;
    }

    public void setFlowInMain(BigDecimal flowInMain) {
        this.flowInMain = flowInMain;
    }

    public BigDecimal getFlowInSmall() {
        return flowInSmall;
    }

    public void setFlowInSmall(BigDecimal flowInSmall) {
        this.flowInSmall = flowInSmall;
    }

    public BigDecimal getFlowInMid() {
        return flowInMid;
    }

    public void setFlowInMid(BigDecimal flowInMid) {
        this.flowInMid = flowInMid;
    }

    public BigDecimal getFlowInBig() {
        return flowInBig;
    }

    public void setFlowInBig(BigDecimal flowInBig) {
        this.flowInBig = flowInBig;
    }

    public BigDecimal getFlowInSuperBig() {
        return flowInSuperBig;
    }

    public void setFlowInSuperBig(BigDecimal flowInSuperBig) {
        this.flowInSuperBig = flowInSuperBig;
    }
}
