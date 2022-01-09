package ttjj.db;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 行业排行
 *
 * @author chenzhiLong
 * @date
 */
public class RankStockCommpanyDb {
    /**
     * rs 原始结果
     */
    private String rs;
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
     * type_name 类型-板块名称
     */
    private String type_name;

    /**
     * order_num 排序号
     */
    private Long order_num;
    /**
     * conception 概念-要点内容
     */
    private String conception;
    /**
     * f1 类别：2-一般股票；3-B股/etf；
     */
    private Long f1;
    /**
     * f2 指数点（当前价格）
     */
    private Double f2;
    /**
     * f3 涨跌幅
     */
    private Double f3;
    /**
     * f4 涨跌数值
     */
    private Double f4;
    /**
     * f5 成交量
     */
    private Long f5;
    /**
     * f6 成交额
     */
    private Long f6;
    /**
     * f7 振幅
     */
    private Double f7;
    /**
     * f8 换手
     */
    private Double f8;
    /**
     * f9 市盈(动)
     */
    private Double f9;
    /**
     * f10 量比
     */
    private Double f10;
    private Double f11;
    /**
     * f12 股票代码
     */
    private String f12;
    /**
     * f13 ？1-沪市；0-深市
     */
    private Long f13;
    /**
     * f14 证券名称
     */
    private String f14;
    /**
     * f15 最高
     */
    private Double f15;
    /**
     * f16 最低
     */
    private Double f16;
    /**
     * f17 今日开盘
     */
    private Double f17;
    /**
     * f18 昨日收盘
     */
    private Double f18;

    /**
     * f19 ？2-上证(600XXX)；3-B股(900XXX)；6-深证(00XXXX)；7-B股(200XXX)；10-etf(159XXX)；23-科创板(688XXX)；80-创业板(300XXX)；
     */
    private Long f19;
    /**
     * f20 总市值
     */
    private BigDecimal f20;
    /**
     * f21 流通市值
     */
    private Long f21;
    /**
     * f22 -1；0；1；2
     */
    private Long f22;
    /**
     * f23 市净率
     */
    private String f23;
    private Double f24;
    private Double f25;
    /**
     * f26 上市时间
     */
    private String f26;
    private Long f27;
    private Double f28;
    private Long f29;
    private Long f30;
    private Double f31;
    private Double f32;
    /**
     * f33 委比
     */
    private Double f33;
    /**
     * f34 外盘
     */
    private Long f34;
    /**
     * f35 内盘
     */
    private Long f35;
    private Double f36;
    /**
     * f37 ROE 利润资产比
     */
    private Double f37;
    /**
     * f38 总股本
     */
    private Double f38;
    /**
     * f39 流通股
     */
    private Double f39;
    /**
     * f40 总营收
     */
    private String f40;
    private String f41;
    private String f42;
    private String f43;
    private String f44;
    /**
     * f45 净利润
     */
    private String f45;
    /**
     * f46 公司核心数据-同比
     */
    private String f46;
    private String f47;
    /**
     * f48 每股未分配利润
     */
    private String f48;
    /**
     * f49 毛利率
     */
    private String f49;
    private Long f50;
    private Double f51;
    private Double f52;
    private Double f53;
    private String f54;
    private String f55;
    private String f56;
    /**
     * f57 负债率
     */
    private String f57;
    private String f58;
    private String f59;
    private Double f60;
    private Double f61;
    /**
     * f62 主力-净流入
     */
    private BigDecimal f62;
    private Double f63;
    /**
     * f64 超大单-流入
     */
    private Double f64;
    /**
     * f65 超大单-流出
     */
    private Double f65;
    /**
     * f66 超大单-净流入
     */
    private Double f66;
    private Double f67;
    private Double f68;
    /**
     * f69 超大单-净占比
     */
    private Double f69;
    /**
     * f70 大单-流入
     */
    private Double f70;
    /**
     * f71 大单-流出
     */
    private Double f71;
    /**
     * f72 大单-净流入
     */
    private Double f72;
    private Double f73;
    private Double f74;
    /**
     * f75 大单-净占比
     */
    private Double f75;
    /**
     * f76 中单-流入
     */
    private Double f76;
    /**
     * f77 中单-流出
     */
    private Double f77;
    /**
     * f78 中单-净流入
     */
    private Double f78;
    private Double f79;
    private Double f80;
    /**
     * f81 中单-净占比
     */
    private Double f81;
    /**
     * f82 小单-流入
     */
    private Long f82;
    /**
     * f83 小单-流出
     */
    private Long f83;
    /**
     * f84 小单-净流入
     */
    private Long f84;
    private Double f85;
    private Double f86;
    /**
     * f87 小单净比
     */
    private Double f87;
    private Double f88;
    private Double f89;
    private Double f90;
    private Double f91;
    private Long f92;
    private Long f93;
    private Double f94;
    private Double f95;
    private Long f96;
    private Long f97;
    private Long f98;
    private Long f99;
    /**
     * f104 上涨家数
     */
    private Long f104;
    /**
     * f105 下跌家数
     */
    private Long f105;
    /**
     * f107 5-闭市
     */
    private Long f107;
    /**
     * f109 5日涨跌
     */
    private Double f109;
    private Double f110;
    private Long f111;
    private String f115;
    private Long f124;
    /**
     * f127 三日涨跌
     */
    private Double f127;
    /**
     * f128 领涨股票-名称
     */
    private String f128;
    /**
     * f136 领涨股票-涨跌幅
     */
    private Double f136;
    /**
     * f139 2-A股主板(00XXXX/60XXXX);3-B股(200XXX/900XXX);5-创业板(30XXXX);32-科创板(688XXX);33-存托凭证(689XXX,689是科创板专门为CDR存托凭证公司)
     */
    private Long f139;
    /**
     * f140 领涨股票-编码
     */
    private String f140;
    /**
     * f141 领涨股票：1-沪市；0-深市
     */
    private Long f141;
    private Double f142;
    private Double f143;
    /**
     * f144 收盘价
     */
    private Double f144;
    private Double f145;
    /**
     * f148 特性：
     *  2-退市或停牌;4-ST股；8-次新股；16-未上市;17-换股吸收合并？；32-暂停上市；
     *  65-融资融券；72-注册制次新股；
     *  513-沪股通；520-沪股通 次新股；    577-沪股通 融资融券；584-沪股通 次新股 融资融券；1025-深股通；1032-深股通 次新股；1089-深股通 融资融券；1096-深股通 次新股 融资融券；33345-GDR HS300_ MSCI中国 标准普尔 富时罗素
     */
    private Long f148;
    /**
     * f149 6日涨跌
     */
    private Double f149;
    private Long f152;
    private Long f153;
    private Long f154;
    private Double f160;
    private Long f164;
    private Double f165;
    private Long f166;
    private Double f167;
    private Long f168;
    private Double f169;
    private Long f170;
    private Double f171;
    private Long f172;
    private Double f173;
    private Long f174;
    private Double f175;
    private Long f176;
    private Double f177;
    private Long f178;
    private Double f179;
    private Long f180;
    private Double f181;
    private Long f182;
    private Double f183;
    private Double f184;
    /**
     * f207 领跌股票-名称
     */
    private String f207;
    /**
     * f208 领跌股票-编码
     */
    private String f208;
    /**
     * f209 领跌股票-1-沪市；0-深市
     */
    private Long f209;
    /**
     * f222 领跌股票-涨跌值
     */
    private Double f222;
    /**
     * f223 排序号
     */
    private Long f223;

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

    private BigDecimal NET_MA_MI_1_5;
    private BigDecimal NET_MA_MI_1_10;
    private BigDecimal NET_MA_MI_1_20;
    private BigDecimal NET_MA_MI_1_30;
    private BigDecimal NET_MA_MI_1_60;
    private BigDecimal NET_MA_MI_1_120;
    private BigDecimal NET_MA_MI_1_250;

    private BigDecimal NET_MA_MI_5_5;
    private BigDecimal NET_MA_MI_5_10;
    private BigDecimal NET_MA_MI_5_20;
    private BigDecimal NET_MA_MI_5_30;
    private BigDecimal NET_MA_MI_5_60;
    private BigDecimal NET_MA_MI_5_120;
    private BigDecimal NET_MA_MI_5_250;

    private BigDecimal NET_MA_MI_15_5;
    private BigDecimal NET_MA_MI_15_10;
    private BigDecimal NET_MA_MI_15_20;
    private BigDecimal NET_MA_MI_15_30;
    private BigDecimal NET_MA_MI_15_60;
    private BigDecimal NET_MA_MI_15_120;
    private BigDecimal NET_MA_MI_15_250;

    private BigDecimal NET_MA_MI_30_5;
    private BigDecimal NET_MA_MI_30_10;
    private BigDecimal NET_MA_MI_30_20;
    private BigDecimal NET_MA_MI_30_30;
    private BigDecimal NET_MA_MI_30_60;
    private BigDecimal NET_MA_MI_30_120;
    private BigDecimal NET_MA_MI_30_250;

    private BigDecimal NET_MA_MI_60_5;
    private BigDecimal NET_MA_MI_60_10;
    private BigDecimal NET_MA_MI_60_20;
    private BigDecimal NET_MA_MI_60_30;
    private BigDecimal NET_MA_MI_60_60;
    private BigDecimal NET_MA_MI_60_120;
    private BigDecimal NET_MA_MI_60_250;

    private BigDecimal NET_MA_MI_120_5;
    private BigDecimal NET_MA_MI_120_10;
    private BigDecimal NET_MA_MI_120_20;
    private BigDecimal NET_MA_MI_120_30;
    private BigDecimal NET_MA_MI_120_60;
    private BigDecimal NET_MA_MI_120_120;
    private BigDecimal NET_MA_MI_120_250;

    private BigDecimal NET_MA_WE_1_5;
    private BigDecimal NET_MA_WE_1_10;
    private BigDecimal NET_MA_WE_1_20;
    private BigDecimal NET_MA_WE_1_30;
    private BigDecimal NET_MA_WE_1_60;
    private BigDecimal NET_MA_WE_1_120;
    private BigDecimal NET_MA_WE_1_250;

    private BigDecimal NET_MA_MO_1_5;
    private BigDecimal NET_MA_MO_1_10;
    private BigDecimal NET_MA_MO_1_20;
    private BigDecimal NET_MA_MO_1_30;
    private BigDecimal NET_MA_MO_1_60;
    private BigDecimal NET_MA_MO_1_120;
    private BigDecimal NET_MA_MO_1_250;

    public BigDecimal getNET_MA_MI_1_5() {
        return NET_MA_MI_1_5;
    }

    public void setNET_MA_MI_1_5(BigDecimal NET_MA_MI_1_5) {
        this.NET_MA_MI_1_5 = NET_MA_MI_1_5;
    }

    public BigDecimal getNET_MA_MI_1_10() {
        return NET_MA_MI_1_10;
    }

    public void setNET_MA_MI_1_10(BigDecimal NET_MA_MI_1_10) {
        this.NET_MA_MI_1_10 = NET_MA_MI_1_10;
    }

    public BigDecimal getNET_MA_MI_1_20() {
        return NET_MA_MI_1_20;
    }

    public void setNET_MA_MI_1_20(BigDecimal NET_MA_MI_1_20) {
        this.NET_MA_MI_1_20 = NET_MA_MI_1_20;
    }

    public BigDecimal getNET_MA_MI_1_30() {
        return NET_MA_MI_1_30;
    }

    public void setNET_MA_MI_1_30(BigDecimal NET_MA_MI_1_30) {
        this.NET_MA_MI_1_30 = NET_MA_MI_1_30;
    }

    public BigDecimal getNET_MA_MI_1_60() {
        return NET_MA_MI_1_60;
    }

    public void setNET_MA_MI_1_60(BigDecimal NET_MA_MI_1_60) {
        this.NET_MA_MI_1_60 = NET_MA_MI_1_60;
    }

    public BigDecimal getNET_MA_MI_1_120() {
        return NET_MA_MI_1_120;
    }

    public void setNET_MA_MI_1_120(BigDecimal NET_MA_MI_1_120) {
        this.NET_MA_MI_1_120 = NET_MA_MI_1_120;
    }

    public BigDecimal getNET_MA_MI_1_250() {
        return NET_MA_MI_1_250;
    }

    public void setNET_MA_MI_1_250(BigDecimal NET_MA_MI_1_250) {
        this.NET_MA_MI_1_250 = NET_MA_MI_1_250;
    }

    public BigDecimal getNET_MA_MI_5_5() {
        return NET_MA_MI_5_5;
    }

    public void setNET_MA_MI_5_5(BigDecimal NET_MA_MI_5_5) {
        this.NET_MA_MI_5_5 = NET_MA_MI_5_5;
    }

    public BigDecimal getNET_MA_MI_5_10() {
        return NET_MA_MI_5_10;
    }

    public void setNET_MA_MI_5_10(BigDecimal NET_MA_MI_5_10) {
        this.NET_MA_MI_5_10 = NET_MA_MI_5_10;
    }

    public BigDecimal getNET_MA_MI_5_20() {
        return NET_MA_MI_5_20;
    }

    public void setNET_MA_MI_5_20(BigDecimal NET_MA_MI_5_20) {
        this.NET_MA_MI_5_20 = NET_MA_MI_5_20;
    }

    public BigDecimal getNET_MA_MI_5_30() {
        return NET_MA_MI_5_30;
    }

    public void setNET_MA_MI_5_30(BigDecimal NET_MA_MI_5_30) {
        this.NET_MA_MI_5_30 = NET_MA_MI_5_30;
    }

    public BigDecimal getNET_MA_MI_5_60() {
        return NET_MA_MI_5_60;
    }

    public void setNET_MA_MI_5_60(BigDecimal NET_MA_MI_5_60) {
        this.NET_MA_MI_5_60 = NET_MA_MI_5_60;
    }

    public BigDecimal getNET_MA_MI_5_120() {
        return NET_MA_MI_5_120;
    }

    public void setNET_MA_MI_5_120(BigDecimal NET_MA_MI_5_120) {
        this.NET_MA_MI_5_120 = NET_MA_MI_5_120;
    }

    public BigDecimal getNET_MA_MI_5_250() {
        return NET_MA_MI_5_250;
    }

    public void setNET_MA_MI_5_250(BigDecimal NET_MA_MI_5_250) {
        this.NET_MA_MI_5_250 = NET_MA_MI_5_250;
    }

    public BigDecimal getNET_MA_MI_15_5() {
        return NET_MA_MI_15_5;
    }

    public void setNET_MA_MI_15_5(BigDecimal NET_MA_MI_15_5) {
        this.NET_MA_MI_15_5 = NET_MA_MI_15_5;
    }

    public BigDecimal getNET_MA_MI_15_10() {
        return NET_MA_MI_15_10;
    }

    public void setNET_MA_MI_15_10(BigDecimal NET_MA_MI_15_10) {
        this.NET_MA_MI_15_10 = NET_MA_MI_15_10;
    }

    public BigDecimal getNET_MA_MI_15_20() {
        return NET_MA_MI_15_20;
    }

    public void setNET_MA_MI_15_20(BigDecimal NET_MA_MI_15_20) {
        this.NET_MA_MI_15_20 = NET_MA_MI_15_20;
    }

    public BigDecimal getNET_MA_MI_15_30() {
        return NET_MA_MI_15_30;
    }

    public void setNET_MA_MI_15_30(BigDecimal NET_MA_MI_15_30) {
        this.NET_MA_MI_15_30 = NET_MA_MI_15_30;
    }

    public BigDecimal getNET_MA_MI_15_60() {
        return NET_MA_MI_15_60;
    }

    public void setNET_MA_MI_15_60(BigDecimal NET_MA_MI_15_60) {
        this.NET_MA_MI_15_60 = NET_MA_MI_15_60;
    }

    public BigDecimal getNET_MA_MI_15_120() {
        return NET_MA_MI_15_120;
    }

    public void setNET_MA_MI_15_120(BigDecimal NET_MA_MI_15_120) {
        this.NET_MA_MI_15_120 = NET_MA_MI_15_120;
    }

    public BigDecimal getNET_MA_MI_15_250() {
        return NET_MA_MI_15_250;
    }

    public void setNET_MA_MI_15_250(BigDecimal NET_MA_MI_15_250) {
        this.NET_MA_MI_15_250 = NET_MA_MI_15_250;
    }

    public BigDecimal getNET_MA_MI_30_5() {
        return NET_MA_MI_30_5;
    }

    public void setNET_MA_MI_30_5(BigDecimal NET_MA_MI_30_5) {
        this.NET_MA_MI_30_5 = NET_MA_MI_30_5;
    }

    public BigDecimal getNET_MA_MI_30_10() {
        return NET_MA_MI_30_10;
    }

    public void setNET_MA_MI_30_10(BigDecimal NET_MA_MI_30_10) {
        this.NET_MA_MI_30_10 = NET_MA_MI_30_10;
    }

    public BigDecimal getNET_MA_MI_30_20() {
        return NET_MA_MI_30_20;
    }

    public void setNET_MA_MI_30_20(BigDecimal NET_MA_MI_30_20) {
        this.NET_MA_MI_30_20 = NET_MA_MI_30_20;
    }

    public BigDecimal getNET_MA_MI_30_30() {
        return NET_MA_MI_30_30;
    }

    public void setNET_MA_MI_30_30(BigDecimal NET_MA_MI_30_30) {
        this.NET_MA_MI_30_30 = NET_MA_MI_30_30;
    }

    public BigDecimal getNET_MA_MI_30_60() {
        return NET_MA_MI_30_60;
    }

    public void setNET_MA_MI_30_60(BigDecimal NET_MA_MI_30_60) {
        this.NET_MA_MI_30_60 = NET_MA_MI_30_60;
    }

    public BigDecimal getNET_MA_MI_30_120() {
        return NET_MA_MI_30_120;
    }

    public void setNET_MA_MI_30_120(BigDecimal NET_MA_MI_30_120) {
        this.NET_MA_MI_30_120 = NET_MA_MI_30_120;
    }

    public BigDecimal getNET_MA_MI_30_250() {
        return NET_MA_MI_30_250;
    }

    public void setNET_MA_MI_30_250(BigDecimal NET_MA_MI_30_250) {
        this.NET_MA_MI_30_250 = NET_MA_MI_30_250;
    }

    public BigDecimal getNET_MA_MI_60_5() {
        return NET_MA_MI_60_5;
    }

    public void setNET_MA_MI_60_5(BigDecimal NET_MA_MI_60_5) {
        this.NET_MA_MI_60_5 = NET_MA_MI_60_5;
    }

    public BigDecimal getNET_MA_MI_60_10() {
        return NET_MA_MI_60_10;
    }

    public void setNET_MA_MI_60_10(BigDecimal NET_MA_MI_60_10) {
        this.NET_MA_MI_60_10 = NET_MA_MI_60_10;
    }

    public BigDecimal getNET_MA_MI_60_20() {
        return NET_MA_MI_60_20;
    }

    public void setNET_MA_MI_60_20(BigDecimal NET_MA_MI_60_20) {
        this.NET_MA_MI_60_20 = NET_MA_MI_60_20;
    }

    public BigDecimal getNET_MA_MI_60_30() {
        return NET_MA_MI_60_30;
    }

    public void setNET_MA_MI_60_30(BigDecimal NET_MA_MI_60_30) {
        this.NET_MA_MI_60_30 = NET_MA_MI_60_30;
    }

    public BigDecimal getNET_MA_MI_60_60() {
        return NET_MA_MI_60_60;
    }

    public void setNET_MA_MI_60_60(BigDecimal NET_MA_MI_60_60) {
        this.NET_MA_MI_60_60 = NET_MA_MI_60_60;
    }

    public BigDecimal getNET_MA_MI_60_120() {
        return NET_MA_MI_60_120;
    }

    public void setNET_MA_MI_60_120(BigDecimal NET_MA_MI_60_120) {
        this.NET_MA_MI_60_120 = NET_MA_MI_60_120;
    }

    public BigDecimal getNET_MA_MI_60_250() {
        return NET_MA_MI_60_250;
    }

    public void setNET_MA_MI_60_250(BigDecimal NET_MA_MI_60_250) {
        this.NET_MA_MI_60_250 = NET_MA_MI_60_250;
    }

    public BigDecimal getNET_MA_MI_120_5() {
        return NET_MA_MI_120_5;
    }

    public void setNET_MA_MI_120_5(BigDecimal NET_MA_MI_120_5) {
        this.NET_MA_MI_120_5 = NET_MA_MI_120_5;
    }

    public BigDecimal getNET_MA_MI_120_10() {
        return NET_MA_MI_120_10;
    }

    public void setNET_MA_MI_120_10(BigDecimal NET_MA_MI_120_10) {
        this.NET_MA_MI_120_10 = NET_MA_MI_120_10;
    }

    public BigDecimal getNET_MA_MI_120_20() {
        return NET_MA_MI_120_20;
    }

    public void setNET_MA_MI_120_20(BigDecimal NET_MA_MI_120_20) {
        this.NET_MA_MI_120_20 = NET_MA_MI_120_20;
    }

    public BigDecimal getNET_MA_MI_120_30() {
        return NET_MA_MI_120_30;
    }

    public void setNET_MA_MI_120_30(BigDecimal NET_MA_MI_120_30) {
        this.NET_MA_MI_120_30 = NET_MA_MI_120_30;
    }

    public BigDecimal getNET_MA_MI_120_60() {
        return NET_MA_MI_120_60;
    }

    public void setNET_MA_MI_120_60(BigDecimal NET_MA_MI_120_60) {
        this.NET_MA_MI_120_60 = NET_MA_MI_120_60;
    }

    public BigDecimal getNET_MA_MI_120_120() {
        return NET_MA_MI_120_120;
    }

    public void setNET_MA_MI_120_120(BigDecimal NET_MA_MI_120_120) {
        this.NET_MA_MI_120_120 = NET_MA_MI_120_120;
    }

    public BigDecimal getNET_MA_MI_120_250() {
        return NET_MA_MI_120_250;
    }

    public void setNET_MA_MI_120_250(BigDecimal NET_MA_MI_120_250) {
        this.NET_MA_MI_120_250 = NET_MA_MI_120_250;
    }

    public BigDecimal getNET_MA_WE_1_5() {
        return NET_MA_WE_1_5;
    }

    public void setNET_MA_WE_1_5(BigDecimal NET_MA_WE_1_5) {
        this.NET_MA_WE_1_5 = NET_MA_WE_1_5;
    }

    public BigDecimal getNET_MA_WE_1_10() {
        return NET_MA_WE_1_10;
    }

    public void setNET_MA_WE_1_10(BigDecimal NET_MA_WE_1_10) {
        this.NET_MA_WE_1_10 = NET_MA_WE_1_10;
    }

    public BigDecimal getNET_MA_WE_1_20() {
        return NET_MA_WE_1_20;
    }

    public void setNET_MA_WE_1_20(BigDecimal NET_MA_WE_1_20) {
        this.NET_MA_WE_1_20 = NET_MA_WE_1_20;
    }

    public BigDecimal getNET_MA_WE_1_30() {
        return NET_MA_WE_1_30;
    }

    public void setNET_MA_WE_1_30(BigDecimal NET_MA_WE_1_30) {
        this.NET_MA_WE_1_30 = NET_MA_WE_1_30;
    }

    public BigDecimal getNET_MA_WE_1_60() {
        return NET_MA_WE_1_60;
    }

    public void setNET_MA_WE_1_60(BigDecimal NET_MA_WE_1_60) {
        this.NET_MA_WE_1_60 = NET_MA_WE_1_60;
    }

    public BigDecimal getNET_MA_WE_1_120() {
        return NET_MA_WE_1_120;
    }

    public void setNET_MA_WE_1_120(BigDecimal NET_MA_WE_1_120) {
        this.NET_MA_WE_1_120 = NET_MA_WE_1_120;
    }

    public BigDecimal getNET_MA_WE_1_250() {
        return NET_MA_WE_1_250;
    }

    public void setNET_MA_WE_1_250(BigDecimal NET_MA_WE_1_250) {
        this.NET_MA_WE_1_250 = NET_MA_WE_1_250;
    }

    public BigDecimal getNET_MA_MO_1_5() {
        return NET_MA_MO_1_5;
    }

    public void setNET_MA_MO_1_5(BigDecimal NET_MA_MO_1_5) {
        this.NET_MA_MO_1_5 = NET_MA_MO_1_5;
    }

    public BigDecimal getNET_MA_MO_1_10() {
        return NET_MA_MO_1_10;
    }

    public void setNET_MA_MO_1_10(BigDecimal NET_MA_MO_1_10) {
        this.NET_MA_MO_1_10 = NET_MA_MO_1_10;
    }

    public BigDecimal getNET_MA_MO_1_20() {
        return NET_MA_MO_1_20;
    }

    public void setNET_MA_MO_1_20(BigDecimal NET_MA_MO_1_20) {
        this.NET_MA_MO_1_20 = NET_MA_MO_1_20;
    }

    public BigDecimal getNET_MA_MO_1_30() {
        return NET_MA_MO_1_30;
    }

    public void setNET_MA_MO_1_30(BigDecimal NET_MA_MO_1_30) {
        this.NET_MA_MO_1_30 = NET_MA_MO_1_30;
    }

    public BigDecimal getNET_MA_MO_1_60() {
        return NET_MA_MO_1_60;
    }

    public void setNET_MA_MO_1_60(BigDecimal NET_MA_MO_1_60) {
        this.NET_MA_MO_1_60 = NET_MA_MO_1_60;
    }

    public BigDecimal getNET_MA_MO_1_120() {
        return NET_MA_MO_1_120;
    }

    public void setNET_MA_MO_1_120(BigDecimal NET_MA_MO_1_120) {
        this.NET_MA_MO_1_120 = NET_MA_MO_1_120;
    }

    public BigDecimal getNET_MA_MO_1_250() {
        return NET_MA_MO_1_250;
    }

    public void setNET_MA_MO_1_250(BigDecimal NET_MA_MO_1_250) {
        this.NET_MA_MO_1_250 = NET_MA_MO_1_250;
    }

    private Date CREATE_TIME;
    private Date UPDATE_TIME;

    /**
     * fundFlow 资金流向-当日每分钟
     */
    private String fundFlow;

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

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public Double getF2() {
        return f2;
    }

    public void setF2(Double f2) {
        this.f2 = f2;
    }

    public Double getF3() {
        return f3;
    }

    public void setF3(Double f3) {
        this.f3 = f3;
    }

    public Double getF4() {
        return f4;
    }

    public void setF4(Double f4) {
        this.f4 = f4;
    }

    public Long getF5() {
        return f5;
    }

    public void setF5(Long f5) {
        this.f5 = f5;
    }

    public Long getF6() {
        return f6;
    }

    public void setF6(Long f6) {
        this.f6 = f6;
    }

    public Double getF7() {
        return f7;
    }

    public void setF7(Double f7) {
        this.f7 = f7;
    }

    public Double getF8() {
        return f8;
    }

    public void setF8(Double f8) {
        this.f8 = f8;
    }

    public Double getF9() {
        return f9;
    }

    public void setF9(Double f9) {
        this.f9 = f9;
    }

    public Double getF10() {
        return f10;
    }

    public void setF10(Double f10) {
        this.f10 = f10;
    }

    public Double getF11() {
        return f11;
    }

    public void setF11(Double f11) {
        this.f11 = f11;
    }

    public String getF12() {
        return f12;
    }

    public void setF12(String f12) {
        this.f12 = f12;
    }

    public Long getF13() {
        return f13;
    }

    public void setF13(Long f13) {
        this.f13 = f13;
    }

    public String getF14() {
        return f14;
    }

    public void setF14(String f14) {
        this.f14 = f14;
    }

    public Double getF15() {
        return f15;
    }

    public void setF15(Double f15) {
        this.f15 = f15;
    }

    public Double getF16() {
        return f16;
    }

    public void setF16(Double f16) {
        this.f16 = f16;
    }

    public Double getF17() {
        return f17;
    }

    public void setF17(Double f17) {
        this.f17 = f17;
    }

    public Double getF18() {
        return f18;
    }

    public void setF18(Double f18) {
        this.f18 = f18;
    }

    public Long getF19() {
        return f19;
    }

    public void setF19(Long f19) {
        this.f19 = f19;
    }

    public BigDecimal getF20() {
        return f20;
    }

    public void setF20(BigDecimal f20) {
        this.f20 = f20;
    }

    public Long getF21() {
        return f21;
    }

    public void setF21(Long f21) {
        this.f21 = f21;
    }

    public Long getF22() {
        return f22;
    }

    public void setF22(Long f22) {
        this.f22 = f22;
    }

    public String getF23() {
        return f23;
    }

    public void setF23(String f23) {
        this.f23 = f23;
    }

    public Double getF24() {
        return f24;
    }

    public void setF24(Double f24) {
        this.f24 = f24;
    }

    public Double getF25() {
        return f25;
    }

    public void setF25(Double f25) {
        this.f25 = f25;
    }

    public String getF26() {
        return f26;
    }

    public void setF26(String f26) {
        this.f26 = f26;
    }

    public Long getF27() {
        return f27;
    }

    public void setF27(Long f27) {
        this.f27 = f27;
    }

    public Double getF28() {
        return f28;
    }

    public void setF28(Double f28) {
        this.f28 = f28;
    }

    public Long getF29() {
        return f29;
    }

    public void setF29(Long f29) {
        this.f29 = f29;
    }

    public Long getF30() {
        return f30;
    }

    public Double getF31() {
        return f31;
    }

    public void setF31(Double f31) {
        this.f31 = f31;
    }

    public void setF30(Long f30) {
        this.f30 = f30;
    }

    public Double getF32() {
        return f32;
    }

    public void setF32(Double f32) {
        this.f32 = f32;
    }

    public Double getF33() {
        return f33;
    }

    public void setF33(Double f33) {
        this.f33 = f33;
    }

    public Long getF34() {
        return f34;
    }

    public void setF34(Long f34) {
        this.f34 = f34;
    }

    public Long getF35() {
        return f35;
    }

    public void setF35(Long f35) {
        this.f35 = f35;
    }

    public Double getF36() {
        return f36;
    }

    public void setF36(Double f36) {
        this.f36 = f36;
    }

    public Double getF37() {
        return f37;
    }

    public void setF37(Double f37) {
        this.f37 = f37;
    }

    public Double getF38() {
        return f38;
    }

    public void setF38(Double f38) {
        this.f38 = f38;
    }

    public Double getF39() {
        return f39;
    }

    public void setF39(Double f39) {
        this.f39 = f39;
    }

    public Double getF60() {
        return f60;
    }

    public void setF60(Double f60) {
        this.f60 = f60;
    }

    public Double getF61() {
        return f61;
    }

    public void setF61(Double f61) {
        this.f61 = f61;
    }

    public BigDecimal getF62() {
        return f62;
    }

    public void setF62(BigDecimal f62) {
        this.f62 = f62;
    }

    public Double getF63() {
        return f63;
    }

    public void setF63(Double f63) {
        this.f63 = f63;
    }

    public Double getF64() {
        return f64;
    }

    public void setF64(Double f64) {
        this.f64 = f64;
    }

    public Double getF65() {
        return f65;
    }

    public void setF65(Double f65) {
        this.f65 = f65;
    }

    public Double getF66() {
        return f66;
    }

    public void setF66(Double f66) {
        this.f66 = f66;
    }

    public Double getF67() {
        return f67;
    }

    public void setF67(Double f67) {
        this.f67 = f67;
    }

    public Double getF68() {
        return f68;
    }

    public void setF68(Double f68) {
        this.f68 = f68;
    }

    public Double getF69() {
        return f69;
    }

    public void setF69(Double f69) {
        this.f69 = f69;
    }

    public Double getF70() {
        return f70;
    }

    public void setF70(Double f70) {
        this.f70 = f70;
    }

    public Double getF71() {
        return f71;
    }

    public void setF71(Double f71) {
        this.f71 = f71;
    }

    public Double getF72() {
        return f72;
    }

    public void setF72(Double f72) {
        this.f72 = f72;
    }

    public Double getF73() {
        return f73;
    }

    public void setF73(Double f73) {
        this.f73 = f73;
    }

    public Double getF74() {
        return f74;
    }

    public void setF74(Double f74) {
        this.f74 = f74;
    }

    public Double getF75() {
        return f75;
    }

    public void setF75(Double f75) {
        this.f75 = f75;
    }

    public Double getF76() {
        return f76;
    }

    public void setF76(Double f76) {
        this.f76 = f76;
    }

    public Double getF77() {
        return f77;
    }

    public void setF77(Double f77) {
        this.f77 = f77;
    }

    public Double getF78() {
        return f78;
    }

    public void setF78(Double f78) {
        this.f78 = f78;
    }

    public Double getF79() {
        return f79;
    }

    public void setF79(Double f79) {
        this.f79 = f79;
    }

    public Double getF80() {
        return f80;
    }

    public void setF80(Double f80) {
        this.f80 = f80;
    }

    public Double getF81() {
        return f81;
    }

    public void setF81(Double f81) {
        this.f81 = f81;
    }

    public Long getF82() {
        return f82;
    }

    public void setF82(Long f82) {
        this.f82 = f82;
    }

    public Long getF83() {
        return f83;
    }

    public void setF83(Long f83) {
        this.f83 = f83;
    }

    public Long getF84() {
        return f84;
    }

    public void setF84(Long f84) {
        this.f84 = f84;
    }

    public Double getF85() {
        return f85;
    }

    public void setF85(Double f85) {
        this.f85 = f85;
    }

    public Double getF86() {
        return f86;
    }

    public void setF86(Double f86) {
        this.f86 = f86;
    }

    public Double getF87() {
        return f87;
    }

    public void setF87(Double f87) {
        this.f87 = f87;
    }

    public Double getF88() {
        return f88;
    }

    public void setF88(Double f88) {
        this.f88 = f88;
    }

    public Double getF89() {
        return f89;
    }

    public void setF89(Double f89) {
        this.f89 = f89;
    }

    public Double getF90() {
        return f90;
    }

    public void setF90(Double f90) {
        this.f90 = f90;
    }

    public Double getF91() {
        return f91;
    }

    public void setF91(Double f91) {
        this.f91 = f91;
    }

    public Long getF92() {
        return f92;
    }

    public void setF92(Long f92) {
        this.f92 = f92;
    }

    public Double getF94() {
        return f94;
    }

    public void setF94(Double f94) {
        this.f94 = f94;
    }

    public Double getF95() {
        return f95;
    }

    public void setF95(Double f95) {
        this.f95 = f95;
    }

    public Long getF97() {
        return f97;
    }

    public void setF97(Long f97) {
        this.f97 = f97;
    }

    public Long getF98() {
        return f98;
    }

    public void setF98(Long f98) {
        this.f98 = f98;
    }

    public Long getF99() {
        return f99;
    }

    public void setF99(Long f99) {
        this.f99 = f99;
    }

    public Long getF104() {
        return f104;
    }

    public void setF104(Long f104) {
        this.f104 = f104;
    }

    public Long getF105() {
        return f105;
    }

    public void setF105(Long f105) {
        this.f105 = f105;
    }

    public Long getF107() {
        return f107;
    }

    public void setF107(Long f107) {
        this.f107 = f107;
    }

    public Double getF109() {
        return f109;
    }

    public void setF109(Double f109) {
        this.f109 = f109;
    }

    public Double getF110() {
        return f110;
    }

    public void setF110(Double f110) {
        this.f110 = f110;
    }

    public String getF115() {
        return f115;
    }

    public void setF115(String f115) {
        this.f115 = f115;
    }

    public Long getF124() {
        return f124;
    }

    public void setF124(Long f124) {
        this.f124 = f124;
    }

    public Double getF127() {
        return f127;
    }

    public void setF127(Double f127) {
        this.f127 = f127;
    }

    public String getF128() {
        return f128;
    }

    public void setF128(String f128) {
        this.f128 = f128;
    }

    public String getF140() {
        return f140;
    }

    public void setF140(String f140) {
        this.f140 = f140;
    }

    public Long getF141() {
        return f141;
    }

    public void setF141(Long f141) {
        this.f141 = f141;
    }

    public Double getF136() {
        return f136;
    }

    public void setF136(Double f136) {
        this.f136 = f136;
    }

    public Long getF139() {
        return f139;
    }

    public void setF139(Long f139) {
        this.f139 = f139;
    }

    public Double getF142() {
        return f142;
    }

    public void setF142(Double f142) {
        this.f142 = f142;
    }

    public Double getF143() {
        return f143;
    }

    public void setF143(Double f143) {
        this.f143 = f143;
    }

    public Double getF144() {
        return f144;
    }

    public void setF144(Double f144) {
        this.f144 = f144;
    }

    public Double getF145() {
        return f145;
    }

    public void setF145(Double f145) {
        this.f145 = f145;
    }

    public Long getF148() {
        return f148;
    }

    public void setF148(Long f148) {
        this.f148 = f148;
    }

    public Double getF149() {
        return f149;
    }

    public void setF149(Double f149) {
        this.f149 = f149;
    }

    public Long getF152() {
        return f152;
    }

    public void setF152(Long f152) {
        this.f152 = f152;
    }

    public Long getF153() {
        return f153;
    }

    public void setF153(Long f153) {
        this.f153 = f153;
    }

    public Long getF154() {
        return f154;
    }

    public void setF154(Long f154) {
        this.f154 = f154;
    }

    public Double getF160() {
        return f160;
    }

    public void setF160(Double f160) {
        this.f160 = f160;
    }

    public Long getF164() {
        return f164;
    }

    public void setF164(Long f164) {
        this.f164 = f164;
    }

    public Double getF165() {
        return f165;
    }

    public void setF165(Double f165) {
        this.f165 = f165;
    }

    public Long getF166() {
        return f166;
    }

    public void setF166(Long f166) {
        this.f166 = f166;
    }

    public Double getF167() {
        return f167;
    }

    public void setF167(Double f167) {
        this.f167 = f167;
    }

    public Long getF168() {
        return f168;
    }

    public void setF168(Long f168) {
        this.f168 = f168;
    }

    public Double getF169() {
        return f169;
    }

    public void setF169(Double f169) {
        this.f169 = f169;
    }

    public Long getF170() {
        return f170;
    }

    public void setF170(Long f170) {
        this.f170 = f170;
    }

    public Double getF171() {
        return f171;
    }

    public void setF171(Double f171) {
        this.f171 = f171;
    }

    public Long getF172() {
        return f172;
    }

    public void setF172(Long f172) {
        this.f172 = f172;
    }

    public Double getF173() {
        return f173;
    }

    public void setF173(Double f173) {
        this.f173 = f173;
    }

    public Long getF174() {
        return f174;
    }

    public void setF174(Long f174) {
        this.f174 = f174;
    }

    public Double getF175() {
        return f175;
    }

    public void setF175(Double f175) {
        this.f175 = f175;
    }

    public Long getF176() {
        return f176;
    }

    public void setF176(Long f176) {
        this.f176 = f176;
    }

    public Double getF177() {
        return f177;
    }

    public void setF177(Double f177) {
        this.f177 = f177;
    }

    public Long getF178() {
        return f178;
    }

    public void setF178(Long f178) {
        this.f178 = f178;
    }

    public Double getF179() {
        return f179;
    }

    public void setF179(Double f179) {
        this.f179 = f179;
    }

    public Long getF180() {
        return f180;
    }

    public void setF180(Long f180) {
        this.f180 = f180;
    }

    public Double getF181() {
        return f181;
    }

    public void setF181(Double f181) {
        this.f181 = f181;
    }

    public Long getF182() {
        return f182;
    }

    public void setF182(Long f182) {
        this.f182 = f182;
    }

    public Double getF183() {
        return f183;
    }

    public void setF183(Double f183) {
        this.f183 = f183;
    }

    public Double getF184() {
        return f184;
    }

    public void setF184(Double f184) {
        this.f184 = f184;
    }

    public String getF207() {
        return f207;
    }

    public void setF207(String f207) {
        this.f207 = f207;
    }

    public String getF208() {
        return f208;
    }

    public void setF208(String f208) {
        this.f208 = f208;
    }

    public Long getF209() {
        return f209;
    }

    public void setF209(Long f209) {
        this.f209 = f209;
    }

    public Double getF222() {
        return f222;
    }

    public void setF222(Double f222) {
        this.f222 = f222;
    }

    public Long getF93() {
        return f93;
    }

    public void setF93(Long f93) {
        this.f93 = f93;
    }

    public Long getF96() {
        return f96;
    }

    public void setF96(Long f96) {
        this.f96 = f96;
    }

    public Long getF111() {
        return f111;
    }

    public void setF111(Long f111) {
        this.f111 = f111;
    }

    public Long getF223() {
        return f223;
    }

    public void setF223(Long f223) {
        this.f223 = f223;
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

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Long getOrder_num() {
        return order_num;
    }

    public void setOrder_num(Long order_num) {
        this.order_num = order_num;
    }

    public Long getF1() {
        return f1;
    }

    public void setF1(Long f1) {
        this.f1 = f1;
    }

    public String getConception() {
        return conception;
    }

    public void setConception(String conception) {
        this.conception = conception;
    }

    public String getF40() {
        return f40;
    }

    public void setF40(String f40) {
        this.f40 = f40;
    }

    public String getF41() {
        return f41;
    }

    public void setF41(String f41) {
        this.f41 = f41;
    }

    public String getF42() {
        return f42;
    }

    public void setF42(String f42) {
        this.f42 = f42;
    }

    public String getF43() {
        return f43;
    }

    public void setF43(String f43) {
        this.f43 = f43;
    }

    public String getF44() {
        return f44;
    }

    public void setF44(String f44) {
        this.f44 = f44;
    }

    public String getF45() {
        return f45;
    }

    public void setF45(String f45) {
        this.f45 = f45;
    }

    public String getF46() {
        return f46;
    }

    public void setF46(String f46) {
        this.f46 = f46;
    }

    public String getF47() {
        return f47;
    }

    public void setF47(String f47) {
        this.f47 = f47;
    }

    public String getF48() {
        return f48;
    }

    public void setF48(String f48) {
        this.f48 = f48;
    }

    public String getF49() {
        return f49;
    }

    public void setF49(String f49) {
        this.f49 = f49;
    }

    public Long getF50() {
        return f50;
    }

    public void setF50(Long f50) {
        this.f50 = f50;
    }

    public Double getF51() {
        return f51;
    }

    public void setF51(Double f51) {
        this.f51 = f51;
    }

    public Double getF52() {
        return f52;
    }

    public void setF52(Double f52) {
        this.f52 = f52;
    }

    public Double getF53() {
        return f53;
    }

    public void setF53(Double f53) {
        this.f53 = f53;
    }

    public String getF54() {
        return f54;
    }

    public void setF54(String f54) {
        this.f54 = f54;
    }

    public String getF55() {
        return f55;
    }

    public void setF55(String f55) {
        this.f55 = f55;
    }

    public String getF56() {
        return f56;
    }

    public void setF56(String f56) {
        this.f56 = f56;
    }

    public String getF57() {
        return f57;
    }

    public void setF57(String f57) {
        this.f57 = f57;
    }

    public String getF58() {
        return f58;
    }

    public void setF58(String f58) {
        this.f58 = f58;
    }

    public String getF59() {
        return f59;
    }

    public void setF59(String f59) {
        this.f59 = f59;
    }

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

    public String getFundFlow() {
        return fundFlow;
    }

    public void setFundFlow(String fundFlow) {
        this.fundFlow = fundFlow;
    }
}
