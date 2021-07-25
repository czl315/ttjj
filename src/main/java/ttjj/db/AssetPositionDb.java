package ttjj.db;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资产持仓-持仓
 * @author Administrator
 * @date 2021-07-02 00:30
 *
 * "positions": [
 *         {
 *           "Bz": "RMB",
 *           "Cbjg": "59.230",
 *           "Cbjgex": "59.230",
 *           "Ckcb": "5923.00",
 *           "Ckcbj": "59.230",
 *           "Ckyk": "-737.00",
 *           "Cwbl": "0.01293",
 *           "Djsl": "0",
 *           "Dqcb": "5923.00",
 *           "Dryk": "",
 *           "Drykbl": "",
 *           "Gddm": "0291691999",
 *           "Gfmcdj": "0",
 *           "Gfmrjd": "0",
 *           "Gfssmmce": "0",
 *           "Gfye": "100",
 *           "Jgbm": "5408",
 *           "Khdm": "540820174569",
 *           "Ksssl": "100",
 *           "Kysl": "100",
 *           "Ljyk": "-737.00",
 *           "Market": "SA",
 *           "Mrssc": "0",
 *           "Sssl": "0",
 *           "Szjsbs": "1",
 *           "Ykbl": "-0.124430",
 *           "Zjzh": "540820174569",
 *           "Zqdm": "000651",
 *           "Zqlx": "0",
 *           "Zqlxmc": "0",
 *           "Zqmc": "格力电器",
 *           "Zqsl": "100",
 *           "Ztmc": "0",
 *           "Ztmr": "0",
 *           "Zxjg": "51.860",
 *           "Zxsz": "5186.00"
 *         },
 */
public class AssetPositionDb {
    int ID;
    /**
     * date
     */
    String date;
    /**
     * period
     */
    String period;
    /**
     * week
     */
    String week;
    /**
     * assetPosition 资产持仓
     */
    String assetPosition;
    /**
     * ex 经验
     */
    String fupan;

    /**
     * Bz 币种
     */
    private String Bz;
    /**
     * Cbjg 持币价格
     */
    private BigDecimal Cbjg;
    /**
     * Cbjgex 持币价格ex
     */
    private String Cbjgex;
    /**
     * Ckcb 持有成本
     */
    private BigDecimal Ckcb;
    /**
     * Ckcbj 持有成本价
     */
    private BigDecimal Ckcbj;
    /**
     * Ckyk 持有盈亏
     */
    private BigDecimal Ckyk;
    /**
     * Cwbl 持有比例
     */
    private BigDecimal Cwbl;
    private String Djsl;
    private String Dqcb;
    /**
     * Dryk 当日盈亏
     */
    private BigDecimal Dryk;
    /**
     * Drykbl 当日盈亏比例
     */
    private BigDecimal Drykbl;
    private String Gddm;
    private String Gfmcdj;
    private String Gfmrjd;
    private String Gfssmmce;
    /**
     * Gfye 股份余额
     */
    private String Gfye;
    /**
     * Jgbm 机构编码
     */
    private String Jgbm;
    /**
     * Khdm 客户代码
     */
    private String Khdm;
    private String Ksssl;
    /**
     * Kysl 可用数量
     */
    private BigDecimal Kysl;
    /**
     * Ljyk 累计盈亏
     */
    private BigDecimal Ljyk;
    /**
     * Market 市场
     */
    private String Market;
    /**
     * Mrssc 买入
     */
    private String Mrssc;
    private String Sssl;
    private String Szjsbs;
    /**
     * Ykbl 盈亏比例
     */
    private BigDecimal Ykbl;
    /**
     * Zjzh 资金账户
     */
    private String Zjzh;
    /**
     * Zqdm 证券代码
     */
    private String Zqdm;
    /**
     * Zqlx 证券类型：0-股票；8-可转债；E-etf；
     */
    private String Zqlx;
    /**
     * Zqlxmc 证券类型名称：0-股票；8-可转债；E-etf；
     */
    private String Zqlxmc;
    /**
     * Zqmc 证券名称
     */
    private String Zqmc;
    /**
     * Zqsl 证券数量
     */
    private BigDecimal Zqsl;
    private String Ztmc;
    private String Ztmr;
    /**
     * Zxjg 最新价格
     */
    private BigDecimal Zxjg;
    /**
     * Zxsz 最新数字
     */
    private BigDecimal Zxsz;
    private Date CREATE_TIME;
    private Date UPDATE_TIME;

    public Date getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Date CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public Date getUPDATE_TIME() {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(Date UPDATE_TIME) {
        this.UPDATE_TIME = UPDATE_TIME;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getAssetPosition() {
        return assetPosition;
    }

    public void setAssetPosition(String assetPosition) {
        this.assetPosition = assetPosition;
    }

    public String getBz() {
        return Bz;
    }

    public void setBz(String bz) {
        Bz = bz;
    }

    public BigDecimal getCbjg() {
        return Cbjg;
    }

    public void setCbjg(BigDecimal cbjg) {
        Cbjg = cbjg;
    }

    public String getCbjgex() {
        return Cbjgex;
    }

    public void setCbjgex(String cbjgex) {
        Cbjgex = cbjgex;
    }

    public BigDecimal getCkcb() {
        return Ckcb;
    }

    public void setCkcb(BigDecimal ckcb) {
        Ckcb = ckcb;
    }

    public BigDecimal getCkcbj() {
        return Ckcbj;
    }

    public void setCkcbj(BigDecimal ckcbj) {
        Ckcbj = ckcbj;
    }

    public BigDecimal getCkyk() {
        return Ckyk;
    }

    public void setCkyk(BigDecimal ckyk) {
        Ckyk = ckyk;
    }

    public BigDecimal getCwbl() {
        return Cwbl;
    }

    public void setCwbl(BigDecimal cwbl) {
        Cwbl = cwbl;
    }

    public String getDjsl() {
        return Djsl;
    }

    public void setDjsl(String djsl) {
        Djsl = djsl;
    }

    public String getDqcb() {
        return Dqcb;
    }

    public void setDqcb(String dqcb) {
        Dqcb = dqcb;
    }

    public BigDecimal getDryk() {
        return Dryk;
    }

    public void setDryk(BigDecimal dryk) {
        Dryk = dryk;
    }

    public BigDecimal getDrykbl() {
        return Drykbl;
    }

    public void setDrykbl(BigDecimal drykbl) {
        Drykbl = drykbl;
    }

    public String getGddm() {
        return Gddm;
    }

    public void setGddm(String gddm) {
        Gddm = gddm;
    }

    public String getGfmcdj() {
        return Gfmcdj;
    }

    public void setGfmcdj(String gfmcdj) {
        Gfmcdj = gfmcdj;
    }

    public String getGfmrjd() {
        return Gfmrjd;
    }

    public void setGfmrjd(String gfmrjd) {
        Gfmrjd = gfmrjd;
    }

    public String getGfssmmce() {
        return Gfssmmce;
    }

    public void setGfssmmce(String gfssmmce) {
        Gfssmmce = gfssmmce;
    }

    public String getGfye() {
        return Gfye;
    }

    public void setGfye(String gfye) {
        Gfye = gfye;
    }

    public String getJgbm() {
        return Jgbm;
    }

    public void setJgbm(String jgbm) {
        Jgbm = jgbm;
    }

    public String getKhdm() {
        return Khdm;
    }

    public void setKhdm(String khdm) {
        Khdm = khdm;
    }

    public String getKsssl() {
        return Ksssl;
    }

    public void setKsssl(String ksssl) {
        Ksssl = ksssl;
    }

    public BigDecimal getKysl() {
        return Kysl;
    }

    public void setKysl(BigDecimal kysl) {
        Kysl = kysl;
    }

    public BigDecimal getLjyk() {
        return Ljyk;
    }

    public void setLjyk(BigDecimal ljyk) {
        Ljyk = ljyk;
    }

    public String getMarket() {
        return Market;
    }

    public void setMarket(String market) {
        Market = market;
    }

    public String getMrssc() {
        return Mrssc;
    }

    public void setMrssc(String mrssc) {
        Mrssc = mrssc;
    }

    public String getSssl() {
        return Sssl;
    }

    public void setSssl(String sssl) {
        Sssl = sssl;
    }

    public String getSzjsbs() {
        return Szjsbs;
    }

    public void setSzjsbs(String szjsbs) {
        Szjsbs = szjsbs;
    }

    public BigDecimal getYkbl() {
        return Ykbl;
    }

    public void setYkbl(BigDecimal ykbl) {
        Ykbl = ykbl;
    }

    public String getZjzh() {
        return Zjzh;
    }

    public void setZjzh(String zjzh) {
        Zjzh = zjzh;
    }

    public String getZqdm() {
        return Zqdm;
    }

    public void setZqdm(String zqdm) {
        Zqdm = zqdm;
    }

    public String getZqlx() {
        return Zqlx;
    }

    public void setZqlx(String zqlx) {
        Zqlx = zqlx;
    }

    public String getZqlxmc() {
        return Zqlxmc;
    }

    public void setZqlxmc(String zqlxmc) {
        Zqlxmc = zqlxmc;
    }

    public String getZqmc() {
        return Zqmc;
    }

    public void setZqmc(String zqmc) {
        Zqmc = zqmc;
    }

    public BigDecimal getZqsl() {
        return Zqsl;
    }

    public void setZqsl(BigDecimal zqsl) {
        Zqsl = zqsl;
    }

    public String getZtmc() {
        return Ztmc;
    }

    public void setZtmc(String ztmc) {
        Ztmc = ztmc;
    }

    public String getZtmr() {
        return Ztmr;
    }

    public void setZtmr(String ztmr) {
        Ztmr = ztmr;
    }

    public BigDecimal getZxjg() {
        return Zxjg;
    }

    public void setZxjg(BigDecimal zxjg) {
        Zxjg = zxjg;
    }

    public BigDecimal getZxsz() {
        return Zxsz;
    }

    public void setZxsz(BigDecimal zxsz) {
        Zxsz = zxsz;
    }

    public String getFupan() {
        return fupan;
    }

    public void setFupan(String fupan) {
        this.fupan = fupan;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
