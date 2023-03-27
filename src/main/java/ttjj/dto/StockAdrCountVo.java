package ttjj.dto;

import ttjj.db.StockAdrCount;

import java.math.BigDecimal;
import java.util.List;

/**
 * 股票涨跌次数
 *
 * @author Administrator
 * @date 2022-05-09 23:47
 * 2023-03-26 增加属性：日净值数组-最近n个
 */
public class StockAdrCountVo extends StockAdrCount {
    /**
     * maxDown 最高回撤
     */
    private BigDecimal maxDown;
    /**
     * minRise 最低上涨
     */
    private BigDecimal minRise;
    /**
     * upMa 超过均线-
     */
    private String upMaDay5;
    /**
     * upMaDay15
     */
    private String upMaDay15;
    /**
     * upMaDay10
     */
    private String upMaDay30;
    /**
     * upMaDay20
     */
    private String upMaDay60;
    /**
     * upMaDay40
     */
    private String upMaDay101;
    /**
     * upMaDay60
     */
    private String upMaDay102;

    /**
     * maDownDay5 跌落均线
     */
    private String maDownDay5;
    /**
     * 突破均线-向下-5-最高净值
     */
    private String maBreakDownMax5;
    /**
     * maBreakDownMax15
     */
    private String maBreakDownMax15;
    /**
     * maBreakDownMax30
     */
    private String maBreakDownMax30;
    /**
     * maBreakDownMax60
     */
    private String maBreakDownMax60;
    /**
     * maBreakDownMax101
     */
    private String maBreakDownMax101;
    /**
     * maBreakDownMax102
     */
    private String maBreakDownMax102;
    /**
     * 突破均线-向上-最低净值-5
     */
    private String maBreakUpMin5;
    /**
     * maBreakUpMin15
     */
    private String maBreakUpMin15;
    /**
     * maBreakUpMin30
     */
    private String maBreakUpMin30;
    /**
     * maBreakUpMin60
     */
    private String maBreakUpMin60;
    /**
     * maBreakUpMin101
     */
    private String maBreakUpMin101;
    /**
     * maBreakUpMin102
     */
    private String maBreakUpMin102;
    /**
     * maDownDay15
     */
    private String maDownDay15;
    /**
     * maDownDay10
     */
    private String maDownDay30;
    /**
     * maDownDay20
     */
    private String maDownDay60;
    /**
     * maDownDay40
     */
    private String maDownDay101;
    /**
     * maDownDay60
     */
    private String maDownDay102;
    /**
     * count 涨跌次数
     */
    private BigDecimal count;

    /**
     * Zxjg 最新价格
     */
    private BigDecimal myPositionZxjg;
    /**
     * Zxsz 最新市值
     */
    private BigDecimal myPositionZxsz;

    /**
     * 突破均线次数-向上
     */
    private int breakCountUp5;
    /**
     * 突破均线次数-向上
     */
    private int breakCountUp15;
    /**
     * 突破均线次数-向上
     */
    private int breakCountUp30;
    /**
     * 突破均线次数-向上
     */
    private int breakCountUp60;
    /**
     * 突破均线次数-向上
     */
    private int breakCountUp101;
    /**
     * 突破均线次数-向上
     */
    private int breakCountUp102;
    /**
     * 突破均线次数-向下
     */
    private int breakCountDown5;
    /**
     * 突破均线次数-向下-最高净值
     */
    private int breakCountDownMax5;
    /**
     * 突破均线次数-向下-最高净值
     */
    private int breakCountDownMax15;
    /**
     * 突破均线次数-向下-最高净值
     */
    private int breakCountDownMax30;
    /**
     * 突破均线次数-向下-最高净值
     */
    private int breakCountDownMax60;
    /**
     * 突破均线次数-向下-最高净值
     */
    private int breakCountDownMax101;
    /**
     * 突破均线次数-向下-最高净值
     */
    private int breakCountDownMax102;
    /**
     * 突破均线次数-向上-最低净值
     */
    private int breakCountUpMin5;
    private int breakCountUpMin15;
    private int breakCountUpMin30;
    private int breakCountUpMin60;
    private int breakCountUpMin101;
    private int breakCountUpMin102;
    /**
     * 突破均线次数-向下
     */
    private int breakCountDown15;
    /**
     * 突破均线次数-向下
     */
    private int breakCountDown30;
    /**
     * 突破均线次数-向下
     */
    private int breakCountDown60;
    /**
     * 突破均线次数-向下
     */
    private int breakCountDown101;
    /**
     * 突破均线次数-向下
     */
    private int breakCountDown102;
    /**
     * 突破百分比-向上
     */
    private BigDecimal breakPctUp5;
    /**
     * 突破百分比-向上
     */
    private BigDecimal breakPctUp15;
    /**
     * 突破百分比-向上
     */
    private BigDecimal breakPctUp30;
    /**
     * 突破百分比-向上
     */
    private BigDecimal breakPctUp60;
    /**
     * 突破百分比-向上
     */
    private BigDecimal breakPctUp101;
    /**
     * 突破百分比-向上
     */
    private BigDecimal breakPctUp102;
    /**
     * 突破百分比-向下
     */
    private BigDecimal breakPctDown5;
    /**
     * 突破百分比-向下
     */
    private BigDecimal breakPctDown15;
    /**
     * 突破百分比-向下
     */
    private BigDecimal breakPctDown30;
    /**
     * 突破百分比-向下
     */
    private BigDecimal breakPctDown60;
    /**
     * 突破百分比-向下
     */
    private BigDecimal breakPctDown101;
    /**
     * 突破百分比-向下
     */
    private BigDecimal breakPctDown102;

    /**
     * 日净值数组-最近20
     */
    private List<Kline> netDayLast20;
    /**
     * 日净值数组-最近10
     */
    private List<Kline> netDayLast10;
    /**
     * 日净值数组-最近5
     */
    private List<Kline> netDayLast5;
    /**
     *  超过60周线次数-近20日
     */
    private Integer countUpMa102Type60LastDay20;
    /**
     * 超过60周线次数-近n日
     */
    private Integer countUpMa102Type60LastDay10;
    /**
     * 超过60周线次数-近n日
     */
    private Integer countUpMa102Type60LastDay5;

    /**
     *  低于60周线次数-近n日
     */
    private Integer countDownMa102Type60LastDay20;
    /**
     * 低于60周线次数-近n日
     */
    private Integer countDownMa102Type60LastDay10;
    /**
     * 低于60周线次数-近n日
     */
    private Integer countDownMa102Type60LastDay5;

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public String getUpMaDay5() {
        return upMaDay5;
    }

    public void setUpMaDay5(String upMaDay5) {
        this.upMaDay5 = upMaDay5;
    }

    public String getUpMaDay15() {
        return upMaDay15;
    }

    public void setUpMaDay15(String upMaDay15) {
        this.upMaDay15 = upMaDay15;
    }

    public String getUpMaDay30() {
        return upMaDay30;
    }

    public void setUpMaDay30(String upMaDay30) {
        this.upMaDay30 = upMaDay30;
    }

    public String getUpMaDay60() {
        return upMaDay60;
    }

    public void setUpMaDay60(String upMaDay60) {
        this.upMaDay60 = upMaDay60;
    }

    public String getUpMaDay101() {
        return upMaDay101;
    }

    public void setUpMaDay101(String upMaDay101) {
        this.upMaDay101 = upMaDay101;
    }

    public String getUpMaDay102() {
        return upMaDay102;
    }

    public void setUpMaDay102(String upMaDay102) {
        this.upMaDay102 = upMaDay102;
    }

    public String getMaDownDay5() {
        return maDownDay5;
    }

    public void setMaDownDay5(String maDownDay5) {
        this.maDownDay5 = maDownDay5;
    }

    public String getMaDownDay15() {
        return maDownDay15;
    }

    public void setMaDownDay15(String maDownDay15) {
        this.maDownDay15 = maDownDay15;
    }

    public String getMaDownDay30() {
        return maDownDay30;
    }

    public void setMaDownDay30(String maDownDay30) {
        this.maDownDay30 = maDownDay30;
    }

    public String getMaDownDay60() {
        return maDownDay60;
    }

    public void setMaDownDay60(String maDownDay60) {
        this.maDownDay60 = maDownDay60;
    }

    public String getMaDownDay101() {
        return maDownDay101;
    }

    public void setMaDownDay101(String maDownDay101) {
        this.maDownDay101 = maDownDay101;
    }

    public String getMaDownDay102() {
        return maDownDay102;
    }

    public void setMaDownDay102(String maDownDay102) {
        this.maDownDay102 = maDownDay102;
    }

    public BigDecimal getMaxDown() {
        return maxDown;
    }

    public void setMaxDown(BigDecimal maxDown) {
        this.maxDown = maxDown;
    }

    public BigDecimal getMinRise() {
        return minRise;
    }

    public void setMinRise(BigDecimal minRise) {
        this.minRise = minRise;
    }

    public BigDecimal getMyPositionZxjg() {
        return myPositionZxjg;
    }

    public void setMyPositionZxjg(BigDecimal myPositionZxjg) {
        this.myPositionZxjg = myPositionZxjg;
    }

    public BigDecimal getMyPositionZxsz() {
        return myPositionZxsz;
    }

    public void setMyPositionZxsz(BigDecimal myPositionZxsz) {
        this.myPositionZxsz = myPositionZxsz;
    }

    public int getBreakCountUp5() {
        return breakCountUp5;
    }

    public void setBreakCountUp5(int breakCountUp5) {
        this.breakCountUp5 = breakCountUp5;
    }

    public int getBreakCountUp15() {
        return breakCountUp15;
    }

    public void setBreakCountUp15(int breakCountUp15) {
        this.breakCountUp15 = breakCountUp15;
    }

    public int getBreakCountUp30() {
        return breakCountUp30;
    }

    public void setBreakCountUp30(int breakCountUp30) {
        this.breakCountUp30 = breakCountUp30;
    }

    public int getBreakCountUp60() {
        return breakCountUp60;
    }

    public void setBreakCountUp60(int breakCountUp60) {
        this.breakCountUp60 = breakCountUp60;
    }

    public int getBreakCountUp101() {
        return breakCountUp101;
    }

    public void setBreakCountUp101(int breakCountUp101) {
        this.breakCountUp101 = breakCountUp101;
    }

    public int getBreakCountUp102() {
        return breakCountUp102;
    }

    public void setBreakCountUp102(int breakCountUp102) {
        this.breakCountUp102 = breakCountUp102;
    }

    public int getBreakCountDown5() {
        return breakCountDown5;
    }

    public void setBreakCountDown5(int breakCountDown5) {
        this.breakCountDown5 = breakCountDown5;
    }

    public int getBreakCountDown15() {
        return breakCountDown15;
    }

    public void setBreakCountDown15(int breakCountDown15) {
        this.breakCountDown15 = breakCountDown15;
    }

    public int getBreakCountDown30() {
        return breakCountDown30;
    }

    public void setBreakCountDown30(int breakCountDown30) {
        this.breakCountDown30 = breakCountDown30;
    }

    public int getBreakCountDown60() {
        return breakCountDown60;
    }

    public void setBreakCountDown60(int breakCountDown60) {
        this.breakCountDown60 = breakCountDown60;
    }

    public int getBreakCountDown101() {
        return breakCountDown101;
    }

    public void setBreakCountDown101(int breakCountDown101) {
        this.breakCountDown101 = breakCountDown101;
    }

    public int getBreakCountDown102() {
        return breakCountDown102;
    }

    public void setBreakCountDown102(int breakCountDown102) {
        this.breakCountDown102 = breakCountDown102;
    }

    public BigDecimal getBreakPctUp5() {
        return breakPctUp5;
    }

    public void setBreakPctUp5(BigDecimal breakPctUp5) {
        this.breakPctUp5 = breakPctUp5;
    }

    public BigDecimal getBreakPctUp15() {
        return breakPctUp15;
    }

    public void setBreakPctUp15(BigDecimal breakPctUp15) {
        this.breakPctUp15 = breakPctUp15;
    }

    public BigDecimal getBreakPctUp30() {
        return breakPctUp30;
    }

    public void setBreakPctUp30(BigDecimal breakPctUp30) {
        this.breakPctUp30 = breakPctUp30;
    }

    public BigDecimal getBreakPctUp60() {
        return breakPctUp60;
    }

    public void setBreakPctUp60(BigDecimal breakPctUp60) {
        this.breakPctUp60 = breakPctUp60;
    }

    public BigDecimal getBreakPctUp101() {
        return breakPctUp101;
    }

    public void setBreakPctUp101(BigDecimal breakPctUp101) {
        this.breakPctUp101 = breakPctUp101;
    }

    public BigDecimal getBreakPctUp102() {
        return breakPctUp102;
    }

    public void setBreakPctUp102(BigDecimal breakPctUp102) {
        this.breakPctUp102 = breakPctUp102;
    }

    public BigDecimal getBreakPctDown5() {
        return breakPctDown5;
    }

    public void setBreakPctDown5(BigDecimal breakPctDown5) {
        this.breakPctDown5 = breakPctDown5;
    }

    public BigDecimal getBreakPctDown15() {
        return breakPctDown15;
    }

    public void setBreakPctDown15(BigDecimal breakPctDown15) {
        this.breakPctDown15 = breakPctDown15;
    }

    public BigDecimal getBreakPctDown30() {
        return breakPctDown30;
    }

    public void setBreakPctDown30(BigDecimal breakPctDown30) {
        this.breakPctDown30 = breakPctDown30;
    }

    public BigDecimal getBreakPctDown60() {
        return breakPctDown60;
    }

    public void setBreakPctDown60(BigDecimal breakPctDown60) {
        this.breakPctDown60 = breakPctDown60;
    }

    public BigDecimal getBreakPctDown101() {
        return breakPctDown101;
    }

    public void setBreakPctDown101(BigDecimal breakPctDown101) {
        this.breakPctDown101 = breakPctDown101;
    }

    public BigDecimal getBreakPctDown102() {
        return breakPctDown102;
    }

    public void setBreakPctDown102(BigDecimal breakPctDown102) {
        this.breakPctDown102 = breakPctDown102;
    }

    public String getMaBreakDownMax5() {
        return maBreakDownMax5;
    }

    public void setMaBreakDownMax5(String maBreakDownMax5) {
        this.maBreakDownMax5 = maBreakDownMax5;
    }

    public String getMaBreakDownMax15() {
        return maBreakDownMax15;
    }

    public void setMaBreakDownMax15(String maBreakDownMax15) {
        this.maBreakDownMax15 = maBreakDownMax15;
    }

    public String getMaBreakDownMax30() {
        return maBreakDownMax30;
    }

    public void setMaBreakDownMax30(String maBreakDownMax30) {
        this.maBreakDownMax30 = maBreakDownMax30;
    }

    public String getMaBreakDownMax60() {
        return maBreakDownMax60;
    }

    public void setMaBreakDownMax60(String maBreakDownMax60) {
        this.maBreakDownMax60 = maBreakDownMax60;
    }

    public String getMaBreakDownMax101() {
        return maBreakDownMax101;
    }

    public void setMaBreakDownMax101(String maBreakDownMax101) {
        this.maBreakDownMax101 = maBreakDownMax101;
    }

    public String getMaBreakDownMax102() {
        return maBreakDownMax102;
    }

    public void setMaBreakDownMax102(String maBreakDownMax102) {
        this.maBreakDownMax102 = maBreakDownMax102;
    }

    public int getBreakCountDownMax5() {
        return breakCountDownMax5;
    }

    public void setBreakCountDownMax5(int breakCountDownMax5) {
        this.breakCountDownMax5 = breakCountDownMax5;
    }

    public int getBreakCountDownMax15() {
        return breakCountDownMax15;
    }

    public void setBreakCountDownMax15(int breakCountDownMax15) {
        this.breakCountDownMax15 = breakCountDownMax15;
    }

    public int getBreakCountDownMax30() {
        return breakCountDownMax30;
    }

    public void setBreakCountDownMax30(int breakCountDownMax30) {
        this.breakCountDownMax30 = breakCountDownMax30;
    }

    public int getBreakCountDownMax60() {
        return breakCountDownMax60;
    }

    public void setBreakCountDownMax60(int breakCountDownMax60) {
        this.breakCountDownMax60 = breakCountDownMax60;
    }

    public int getBreakCountDownMax101() {
        return breakCountDownMax101;
    }

    public void setBreakCountDownMax101(int breakCountDownMax101) {
        this.breakCountDownMax101 = breakCountDownMax101;
    }

    public int getBreakCountDownMax102() {
        return breakCountDownMax102;
    }

    public void setBreakCountDownMax102(int breakCountDownMax102) {
        this.breakCountDownMax102 = breakCountDownMax102;
    }

    public String getMaBreakUpMin5() {
        return maBreakUpMin5;
    }

    public void setMaBreakUpMin5(String maBreakUpMin5) {
        this.maBreakUpMin5 = maBreakUpMin5;
    }

    public String getMaBreakUpMin15() {
        return maBreakUpMin15;
    }

    public void setMaBreakUpMin15(String maBreakUpMin15) {
        this.maBreakUpMin15 = maBreakUpMin15;
    }

    public String getMaBreakUpMin30() {
        return maBreakUpMin30;
    }

    public void setMaBreakUpMin30(String maBreakUpMin30) {
        this.maBreakUpMin30 = maBreakUpMin30;
    }

    public String getMaBreakUpMin60() {
        return maBreakUpMin60;
    }

    public void setMaBreakUpMin60(String maBreakUpMin60) {
        this.maBreakUpMin60 = maBreakUpMin60;
    }

    public String getMaBreakUpMin101() {
        return maBreakUpMin101;
    }

    public void setMaBreakUpMin101(String maBreakUpMin101) {
        this.maBreakUpMin101 = maBreakUpMin101;
    }

    public String getMaBreakUpMin102() {
        return maBreakUpMin102;
    }

    public void setMaBreakUpMin102(String maBreakUpMin102) {
        this.maBreakUpMin102 = maBreakUpMin102;
    }

    public int getBreakCountUpMin5() {
        return breakCountUpMin5;
    }

    public void setBreakCountUpMin5(int breakCountUpMin5) {
        this.breakCountUpMin5 = breakCountUpMin5;
    }

    public int getBreakCountUpMin15() {
        return breakCountUpMin15;
    }

    public void setBreakCountUpMin15(int breakCountUpMin15) {
        this.breakCountUpMin15 = breakCountUpMin15;
    }

    public int getBreakCountUpMin30() {
        return breakCountUpMin30;
    }

    public void setBreakCountUpMin30(int breakCountUpMin30) {
        this.breakCountUpMin30 = breakCountUpMin30;
    }

    public int getBreakCountUpMin60() {
        return breakCountUpMin60;
    }

    public void setBreakCountUpMin60(int breakCountUpMin60) {
        this.breakCountUpMin60 = breakCountUpMin60;
    }

    public int getBreakCountUpMin101() {
        return breakCountUpMin101;
    }

    public void setBreakCountUpMin101(int breakCountUpMin101) {
        this.breakCountUpMin101 = breakCountUpMin101;
    }

    public int getBreakCountUpMin102() {
        return breakCountUpMin102;
    }

    public void setBreakCountUpMin102(int breakCountUpMin102) {
        this.breakCountUpMin102 = breakCountUpMin102;
    }

    public List<Kline> getNetDayLast20() {
        return netDayLast20;
    }

    public void setNetDayLast20(List<Kline> netDayLast20) {
        this.netDayLast20 = netDayLast20;
    }

    public List<Kline> getNetDayLast10() {
        return netDayLast10;
    }

    public void setNetDayLast10(List<Kline> netDayLast10) {
        this.netDayLast10 = netDayLast10;
    }

    public List<Kline> getNetDayLast5() {
        return netDayLast5;
    }

    public void setNetDayLast5(List<Kline> netDayLast5) {
        this.netDayLast5 = netDayLast5;
    }

    public Integer getCountUpMa102Type60LastDay20() {
        return countUpMa102Type60LastDay20;
    }

    public void setCountUpMa102Type60LastDay20(Integer countUpMa102Type60LastDay20) {
        this.countUpMa102Type60LastDay20 = countUpMa102Type60LastDay20;
    }

    public Integer getCountUpMa102Type60LastDay10() {
        return countUpMa102Type60LastDay10;
    }

    public void setCountUpMa102Type60LastDay10(Integer countUpMa102Type60LastDay10) {
        this.countUpMa102Type60LastDay10 = countUpMa102Type60LastDay10;
    }

    public Integer getCountUpMa102Type60LastDay5() {
        return countUpMa102Type60LastDay5;
    }

    public void setCountUpMa102Type60LastDay5(Integer countUpMa102Type60LastDay5) {
        this.countUpMa102Type60LastDay5 = countUpMa102Type60LastDay5;
    }

    public Integer getCountDownMa102Type60LastDay20() {
        return countDownMa102Type60LastDay20;
    }

    public void setCountDownMa102Type60LastDay20(Integer countDownMa102Type60LastDay20) {
        this.countDownMa102Type60LastDay20 = countDownMa102Type60LastDay20;
    }

    public Integer getCountDownMa102Type60LastDay10() {
        return countDownMa102Type60LastDay10;
    }

    public void setCountDownMa102Type60LastDay10(Integer countDownMa102Type60LastDay10) {
        this.countDownMa102Type60LastDay10 = countDownMa102Type60LastDay10;
    }

    public Integer getCountDownMa102Type60LastDay5() {
        return countDownMa102Type60LastDay5;
    }

    public void setCountDownMa102Type60LastDay5(Integer countDownMa102Type60LastDay5) {
        this.countDownMa102Type60LastDay5 = countDownMa102Type60LastDay5;
    }
}
