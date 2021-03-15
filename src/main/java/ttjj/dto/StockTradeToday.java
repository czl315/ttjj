package ttjj.dto;

/**
 * 交易-股票
 * {
 *       "Wtsj": "134141",
 *       "Zqdm": "515050",
 *       "Zqmc": "5GETF",
 *       "Mmsm": "证券卖出",
 *       "Mmlb": "S",
 *       "Wtsl": "1500",
 *       "Wtzt": "已成",
 *       "Wtjg": "1.000",
 *       "Cjsl": "1500",
 *       "Cjje": "1500.00",
 *       "Cjjg": "1.000000",
 *       "Market": "HA",
 *       "Wtbh": "755880",
 *       "Gddm": "A799426307",
 *       "Dwc": "",
 *       "Qqhs": null,
 *       "Wtrq": "20210315",
 *       "Wtph": "755880",
 *       "Khdm": "540820174569",
 *       "Khxm": "张三",
 *       "Zjzh": "540820174569",
 *       "Jgbm": "5408",
 *       "Bpsj": "134141",
 *       "Cpbm": "",
 *       "Cpmc": "",
 *       "Djje": ".00",
 *       "Cdsl": "0",
 *       "Jyxw": "43761",
 *       "Cdbs": "F",
 *       "Czrq": "20210315",
 *       "Wtqd": "9",
 *       "Bzxx": "",
 *       "Sbhtxh": "1057060484",
 *       "Mmlb_ex": "S",
 *       "Mmlb_bs": "S"
 *     },
 */
public class StockTradeToday {
    /**
     * Wtsj 委托时间
     */
    private String Wtsj;
    /**
     * Zqdm 证券代码
     */
    private String Zqdm;
    /**
     * Zqmc 证券名称
     */
    private String Zqmc;
    /**
     * Mmsm 买卖sm
     */
    private String Mmsm;
    /**
     * Mmlb 买卖类别
     */
    private String Mmlb;
    /**
     * Wtsl 委托数量
     */
    private String Wtsl;
    /**
     * Wtzt 委托状态
     */
    private String Wtzt;
    /**
     * Wtjg 委托价格
     */
    private String Wtjg;
    /**
     * Cjsl 成交数量
     */
    private String Cjsl;
    /**
     * Cjje 成交金额
     */
    private String Cjje;
    /**
     * Cjjg 成交价格
     */
    private String Cjjg;
    /**
     * Market 市场
     */
    private String Market;
    /**
     * Wtbh 委托编号
     */
    private String Wtbh;
    /**
     * Gddm 股东代码
     */
    private String Gddm;
    /**
     * Dwc ？
     */
    private String Dwc;
    private String Qqhs;
    /**
     * Wtrq 委托日期
     */
    private String Wtrq;
    /**
     * Wtph 委托？
     */
    private String Wtph;
    /**
     * Khdm 开户代码
     */
    private String Khdm;
    /**
     * Khxm 开户姓名
     */
    private String Khxm;
    /**
     * Zjzh zj账户
     */
    private String Zjzh;
    /**
     * Jgbm
     */
    private String Jgbm;
    /**
     * Bpsj bp时间
     */
    private String Bpsj;
    private String Cpbm;
    private String Cpmc;
    /**
     * Djje dj金额
     */
    private String Djje;
    private String Cdsl;
    private String Jyxw;
    private String Cdbs;
    /**
     * Czrq cz日期
     */
    private String Czrq;
    /**
     * Wtqd 委托qd
     */
    private String Wtqd;
    private String Bzxx;
    private String Sbhtxh;
    private String Mmlb_ex;
    private String Mmlb_bs;

    /**
     * 业务类型
     */
    private String bizTy;
    /**
     * 风控-系数-止损
     */
    private Double riskStLoss;
    /**
     * 风控-系数-止盈
     */
    private Double riskStProfit;

    public String getWtsj() {
        return Wtsj;
    }

    public void setWtsj(String wtsj) {
        Wtsj = wtsj;
    }

    public String getZqdm() {
        return Zqdm;
    }

    public void setZqdm(String zqdm) {
        Zqdm = zqdm;
    }

    public String getZqmc() {
        return Zqmc;
    }

    public void setZqmc(String zqmc) {
        Zqmc = zqmc;
    }

    public String getMmsm() {
        return Mmsm;
    }

    public void setMmsm(String mmsm) {
        Mmsm = mmsm;
    }

    public String getMmlb() {
        return Mmlb;
    }

    public void setMmlb(String mmlb) {
        Mmlb = mmlb;
    }

    public String getWtsl() {
        return Wtsl;
    }

    public void setWtsl(String wtsl) {
        Wtsl = wtsl;
    }

    public String getWtzt() {
        return Wtzt;
    }

    public void setWtzt(String wtzt) {
        Wtzt = wtzt;
    }

    public String getWtjg() {
        return Wtjg;
    }

    public void setWtjg(String wtjg) {
        Wtjg = wtjg;
    }

    public String getCjsl() {
        return Cjsl;
    }

    public void setCjsl(String cjsl) {
        Cjsl = cjsl;
    }

    public String getCjje() {
        return Cjje;
    }

    public void setCjje(String cjje) {
        Cjje = cjje;
    }

    public String getCjjg() {
        return Cjjg;
    }

    public void setCjjg(String cjjg) {
        Cjjg = cjjg;
    }

    public String getMarket() {
        return Market;
    }

    public void setMarket(String market) {
        Market = market;
    }

    public String getWtbh() {
        return Wtbh;
    }

    public void setWtbh(String wtbh) {
        Wtbh = wtbh;
    }

    public String getGddm() {
        return Gddm;
    }

    public void setGddm(String gddm) {
        Gddm = gddm;
    }

    public String getDwc() {
        return Dwc;
    }

    public void setDwc(String dwc) {
        Dwc = dwc;
    }

    public String getQqhs() {
        return Qqhs;
    }

    public void setQqhs(String qqhs) {
        Qqhs = qqhs;
    }

    public String getWtrq() {
        return Wtrq;
    }

    public void setWtrq(String wtrq) {
        Wtrq = wtrq;
    }

    public String getWtph() {
        return Wtph;
    }

    public void setWtph(String wtph) {
        Wtph = wtph;
    }

    public String getKhdm() {
        return Khdm;
    }

    public void setKhdm(String khdm) {
        Khdm = khdm;
    }

    public String getKhxm() {
        return Khxm;
    }

    public void setKhxm(String khxm) {
        Khxm = khxm;
    }

    public String getZjzh() {
        return Zjzh;
    }

    public void setZjzh(String zjzh) {
        Zjzh = zjzh;
    }

    public String getJgbm() {
        return Jgbm;
    }

    public void setJgbm(String jgbm) {
        Jgbm = jgbm;
    }

    public String getBpsj() {
        return Bpsj;
    }

    public void setBpsj(String bpsj) {
        Bpsj = bpsj;
    }

    public String getCpbm() {
        return Cpbm;
    }

    public void setCpbm(String cpbm) {
        Cpbm = cpbm;
    }

    public String getCpmc() {
        return Cpmc;
    }

    public void setCpmc(String cpmc) {
        Cpmc = cpmc;
    }

    public String getDjje() {
        return Djje;
    }

    public void setDjje(String djje) {
        Djje = djje;
    }

    public String getCdsl() {
        return Cdsl;
    }

    public void setCdsl(String cdsl) {
        Cdsl = cdsl;
    }

    public String getJyxw() {
        return Jyxw;
    }

    public void setJyxw(String jyxw) {
        Jyxw = jyxw;
    }

    public String getCdbs() {
        return Cdbs;
    }

    public void setCdbs(String cdbs) {
        Cdbs = cdbs;
    }

    public String getCzrq() {
        return Czrq;
    }

    public void setCzrq(String czrq) {
        Czrq = czrq;
    }

    public String getWtqd() {
        return Wtqd;
    }

    public void setWtqd(String wtqd) {
        Wtqd = wtqd;
    }

    public String getBzxx() {
        return Bzxx;
    }

    public void setBzxx(String bzxx) {
        Bzxx = bzxx;
    }

    public String getSbhtxh() {
        return Sbhtxh;
    }

    public void setSbhtxh(String sbhtxh) {
        Sbhtxh = sbhtxh;
    }

    public String getMmlb_ex() {
        return Mmlb_ex;
    }

    public void setMmlb_ex(String mmlb_ex) {
        Mmlb_ex = mmlb_ex;
    }

    public String getMmlb_bs() {
        return Mmlb_bs;
    }

    public void setMmlb_bs(String mmlb_bs) {
        Mmlb_bs = mmlb_bs;
    }

    public String getBizTy() {
        return bizTy;
    }

    public void setBizTy(String bizTy) {
        this.bizTy = bizTy;
    }

    public Double getRiskStLoss() {
        return riskStLoss;
    }

    public void setRiskStLoss(Double riskStLoss) {
        this.riskStLoss = riskStLoss;
    }

    public Double getRiskStProfit() {
        return riskStProfit;
    }

    public void setRiskStProfit(Double riskStProfit) {
        this.riskStProfit = riskStProfit;
    }
}
