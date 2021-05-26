package ttjj.db;

/**
 * 复盘
 *
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
    String rt_hs300;
    String rt_cyb50;
    String rt_sh50;
    String rt_sz;
    String rt_cyb;
    String rt_sh;
    String rt_zz500;
    String rt_biz_qs;

    /**
     * pt_hs300 指数沪深300
     */
    String pt_hs300;
    String pt_cyb50;
    String pt_cyb;
    String pt_sh50;
    String pt_sh;
    String pt_sz;
    String pt_zz500;
    String pt_biz_qs;

    /**
     * 指数-上一时段
     */
    String pt_hs300_last;
    String pt_cyb50_last;
    String pt_cyb_last;
    String pt_sh50_last;
    String pt_sh_last;
    String pt_sz_last;
    String pt_zz500_last;
    String pt_biz_qs_last;

    /**
     * 指数-最大最小值
     */
    String pt_sh_min;
    String pt_sh_max;
    String pt_sh50_min;
    String pt_sh50_max;
    String pt_hs300_min;
    String pt_hs300_max;
    String pt_zz500_min;
    String pt_zz500_max;
    String pt_sz_min;
    String pt_sz_max;
    String pt_cyb_min;
    String pt_cyb_max;
    String pt_cyb50_min;
    String pt_cyb50_max;

    /**
     * cjeHs300 成交额-沪深300
     */
    String cje_hs300;
    String cje_sh;
    String cje_sz;
    String cje_cyb;
    String cje_cyb50;
    String cje_sh50;
    String cje_zz500;
    String cje_biz_qs;

    String amt_dfcf;
    String hold_st;
    String earn_st;
    String rt_st;

    String amt;
    String amt_fund;
    String amt_fund_last;
    String earn_fund;
    String rt_zh;

    public String getRt_biz_qs() {
        return rt_biz_qs;
    }

    public void setRt_biz_qs(String rt_biz_qs) {
        this.rt_biz_qs = rt_biz_qs;
    }

    public String getPt_biz_qs() {
        return pt_biz_qs;
    }

    public void setPt_biz_qs(String pt_biz_qs) {
        this.pt_biz_qs = pt_biz_qs;
    }

    public String getCje_biz_qs() {
        return cje_biz_qs;
    }

    public void setCje_biz_qs(String cje_biz_qs) {
        this.cje_biz_qs = cje_biz_qs;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getAmt_fund() {
        return amt_fund;
    }

    public void setAmt_fund(String amt_fund) {
        this.amt_fund = amt_fund;
    }

    public String getAmt_fund_last() {
        return amt_fund_last;
    }

    public void setAmt_fund_last(String amt_fund_last) {
        this.amt_fund_last = amt_fund_last;
    }

    public String getEarn_fund() {
        return earn_fund;
    }

    public void setEarn_fund(String earn_fund) {
        this.earn_fund = earn_fund;
    }

    public String getRt_zh() {
        return rt_zh;
    }

    public void setRt_zh(String rt_zh) {
        this.rt_zh = rt_zh;
    }

    public String getAmt_dfcf() {
        return amt_dfcf;
    }

    public void setAmt_dfcf(String amt_dfcf) {
        this.amt_dfcf = amt_dfcf;
    }

    public String getHold_st() {
        return hold_st;
    }

    public void setHold_st(String hold_st) {
        this.hold_st = hold_st;
    }

    public String getEarn_st() {
        return earn_st;
    }

    public void setEarn_st(String earn_st) {
        this.earn_st = earn_st;
    }

    public String getRt_st() {
        return rt_st;
    }

    public void setRt_st(String rt_st) {
        this.rt_st = rt_st;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getRt_hs300() {
        return rt_hs300;
    }

    public void setRt_hs300(String rt_hs300) {
        this.rt_hs300 = rt_hs300;
    }

    public String getRt_cyb50() {
        return rt_cyb50;
    }

    public void setRt_cyb50(String rt_cyb50) {
        this.rt_cyb50 = rt_cyb50;
    }

    public String getRt_sh50() {
        return rt_sh50;
    }

    public void setRt_sh50(String rt_sh50) {
        this.rt_sh50 = rt_sh50;
    }

    public String getRt_sz() {
        return rt_sz;
    }

    public void setRt_sz(String rt_sz) {
        this.rt_sz = rt_sz;
    }

    public String getRt_cyb() {
        return rt_cyb;
    }

    public void setRt_cyb(String rt_cyb) {
        this.rt_cyb = rt_cyb;
    }

    public String getRt_sh() {
        return rt_sh;
    }

    public void setRt_sh(String rt_sh) {
        this.rt_sh = rt_sh;
    }

    public String getRt_zz500() {
        return rt_zz500;
    }

    public void setRt_zz500(String rt_zz500) {
        this.rt_zz500 = rt_zz500;
    }

    public String getPt_hs300() {
        return pt_hs300;
    }

    public void setPt_hs300(String pt_hs300) {
        this.pt_hs300 = pt_hs300;
    }

    public String getPt_cyb50() {
        return pt_cyb50;
    }

    public void setPt_cyb50(String pt_cyb50) {
        this.pt_cyb50 = pt_cyb50;
    }

    public String getPt_cyb() {
        return pt_cyb;
    }

    public void setPt_cyb(String pt_cyb) {
        this.pt_cyb = pt_cyb;
    }

    public String getPt_sh50() {
        return pt_sh50;
    }

    public void setPt_sh50(String pt_sh50) {
        this.pt_sh50 = pt_sh50;
    }

    public String getPt_sh() {
        return pt_sh;
    }

    public void setPt_sh(String pt_sh) {
        this.pt_sh = pt_sh;
    }

    public String getPt_sz() {
        return pt_sz;
    }

    public void setPt_sz(String pt_sz) {
        this.pt_sz = pt_sz;
    }

    public String getPt_zz500() {
        return pt_zz500;
    }

    public void setPt_zz500(String pt_zz500) {
        this.pt_zz500 = pt_zz500;
    }

    public String getCje_hs300() {
        return cje_hs300;
    }

    public void setCje_hs300(String cje_hs300) {
        this.cje_hs300 = cje_hs300;
    }

    public String getCje_sh() {
        return cje_sh;
    }

    public void setCje_sh(String cje_sh) {
        this.cje_sh = cje_sh;
    }

    public String getCje_sz() {
        return cje_sz;
    }

    public void setCje_sz(String cje_sz) {
        this.cje_sz = cje_sz;
    }

    public String getCje_cyb() {
        return cje_cyb;
    }

    public void setCje_cyb(String cje_cyb) {
        this.cje_cyb = cje_cyb;
    }

    public String getCje_cyb50() {
        return cje_cyb50;
    }

    public void setCje_cyb50(String cje_cyb50) {
        this.cje_cyb50 = cje_cyb50;
    }

    public String getCje_sh50() {
        return cje_sh50;
    }

    public void setCje_sh50(String cje_sh50) {
        this.cje_sh50 = cje_sh50;
    }

    public String getCje_zz500() {
        return cje_zz500;
    }

    public void setCje_zz500(String cje_zz500) {
        this.cje_zz500 = cje_zz500;
    }

    public String getPt_hs300_last() {
        return pt_hs300_last;
    }

    public void setPt_hs300_last(String pt_hs300_last) {
        this.pt_hs300_last = pt_hs300_last;
    }

    public String getPt_cyb50_last() {
        return pt_cyb50_last;
    }

    public void setPt_cyb50_last(String pt_cyb50_last) {
        this.pt_cyb50_last = pt_cyb50_last;
    }

    public String getPt_cyb_last() {
        return pt_cyb_last;
    }

    public void setPt_cyb_last(String pt_cyb_last) {
        this.pt_cyb_last = pt_cyb_last;
    }

    public String getPt_sh50_last() {
        return pt_sh50_last;
    }

    public void setPt_sh50_last(String pt_sh50_last) {
        this.pt_sh50_last = pt_sh50_last;
    }

    public String getPt_sh_last() {
        return pt_sh_last;
    }

    public void setPt_sh_last(String pt_sh_last) {
        this.pt_sh_last = pt_sh_last;
    }

    public String getPt_sz_last() {
        return pt_sz_last;
    }

    public void setPt_sz_last(String pt_sz_last) {
        this.pt_sz_last = pt_sz_last;
    }

    public String getPt_zz500_last() {
        return pt_zz500_last;
    }

    public void setPt_zz500_last(String pt_zz500_last) {
        this.pt_zz500_last = pt_zz500_last;
    }

    public String getPt_biz_qs_last() {
        return pt_biz_qs_last;
    }

    public void setPt_biz_qs_last(String pt_biz_qs_last) {
        this.pt_biz_qs_last = pt_biz_qs_last;
    }

    public String getPt_sh_min() {
        return pt_sh_min;
    }

    public void setPt_sh_min(String pt_sh_min) {
        this.pt_sh_min = pt_sh_min;
    }

    public String getPt_sh_max() {
        return pt_sh_max;
    }

    public void setPt_sh_max(String pt_sh_max) {
        this.pt_sh_max = pt_sh_max;
    }

    public String getPt_sh50_min() {
        return pt_sh50_min;
    }

    public void setPt_sh50_min(String pt_sh50_min) {
        this.pt_sh50_min = pt_sh50_min;
    }

    public String getPt_sh50_max() {
        return pt_sh50_max;
    }

    public void setPt_sh50_max(String pt_sh50_max) {
        this.pt_sh50_max = pt_sh50_max;
    }

    public String getPt_hs300_min() {
        return pt_hs300_min;
    }

    public void setPt_hs300_min(String pt_hs300_min) {
        this.pt_hs300_min = pt_hs300_min;
    }

    public String getPt_hs300_max() {
        return pt_hs300_max;
    }

    public void setPt_hs300_max(String pt_hs300_max) {
        this.pt_hs300_max = pt_hs300_max;
    }

    public String getPt_zz500_min() {
        return pt_zz500_min;
    }

    public void setPt_zz500_min(String pt_zz500_min) {
        this.pt_zz500_min = pt_zz500_min;
    }

    public String getPt_zz500_max() {
        return pt_zz500_max;
    }

    public void setPt_zz500_max(String pt_zz500_max) {
        this.pt_zz500_max = pt_zz500_max;
    }

    public String getPt_sz_min() {
        return pt_sz_min;
    }

    public void setPt_sz_min(String pt_sz_min) {
        this.pt_sz_min = pt_sz_min;
    }

    public String getPt_sz_max() {
        return pt_sz_max;
    }

    public void setPt_sz_max(String pt_sz_max) {
        this.pt_sz_max = pt_sz_max;
    }

    public String getPt_cyb_min() {
        return pt_cyb_min;
    }

    public void setPt_cyb_min(String pt_cyb_min) {
        this.pt_cyb_min = pt_cyb_min;
    }

    public String getPt_cyb_max() {
        return pt_cyb_max;
    }

    public void setPt_cyb_max(String pt_cyb_max) {
        this.pt_cyb_max = pt_cyb_max;
    }

    public String getPt_cyb50_min() {
        return pt_cyb50_min;
    }

    public void setPt_cyb50_min(String pt_cyb50_min) {
        this.pt_cyb50_min = pt_cyb50_min;
    }

    public String getPt_cyb50_max() {
        return pt_cyb50_max;
    }

    public void setPt_cyb50_max(String pt_cyb50_max) {
        this.pt_cyb50_max = pt_cyb50_max;
    }
}
