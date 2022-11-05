package utils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author chenzhilong
 * @date
 */
public class Content {
    /**
     * ZHISHU_CODE_000001
     */
    public static String INDEX_SHANG_HAI = "000001";
    public static String INDEX_SHEN_ZHEN = "399001";//深证成指
    public static String INDEX_CYB = "399006";//创业板指
    /**
     * ZHISHU_NAME_000001
     */
    public static String ZHISHU_NAME_000001 = "上证指数";
    public static String ZHISHU_NAME_399673 = "创业板50";
    /**
     * 常用指数
     * 000001	上证指数
     */
    public static Map<String, String> zhishuMap = new HashMap<>();

    static {
        zhishuMap.put(INDEX_SHANG_HAI, ZHISHU_NAME_000001);
        zhishuMap.put("000300", "沪深300");
        zhishuMap.put("000016", "上证50");
        zhishuMap.put("000903", "中证100");
        zhishuMap.put("000905", "中证500");
        zhishuMap.put("000852", "中证1000");
        zhishuMap.put("000688", "科创50");
        zhishuMap.put("399001", "深证成指");
        zhishuMap.put("399006", "创业板指");
        zhishuMap.put("399673", "创业板50");
//        zhishuMap.put("931643", "科创创业50");
//        zhishuMap.put("399005","中小100");
//        zhishuMap.put("000010", "上证180");
//        zhishuMap.put("000009","上证380");
    }


    /**
     * 常用指数
     *
     * @return
     */
    public static Map<String, String> getZhishuMap() {
        return zhishuMap;
    }

    public static String jjName = "jjName";
    public static String jjCode = "jjCode";
    public static String canShare = "canShare";
    public static String BUY_COST = "BUY_COST";
    public static String FIRST_NET_DATA = "FIRST_NET_DATA";
    public static String TRADE_ID = "TRADE_ID";
    public static String FD_ID = "FD_ID";
    public static String SOURCE = "SOURCE";
    public static String startDate = "startDate";
    public static String endDate = "endDate";
    public static String lsjzUrl = "lsjzUrl";
    public static String SXF = "sxf";
    /**
     * 基金类型
     */
    public static String FD_TYPE = "FD_TYPE";

    /**
     * ZQDM_ETF_CYB50_159949 证券代码-创业板50
     */
    public static String ZQDM_ETF_CYB50_159949 = "159949";
    /**
     * klt_1 klt=1:1分钟;5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
     */
    public static String KLT_1 = "1";
    /**
     * klt_1 klt=1:1分钟;5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
     */
    public static String KLT_5 = "5";
    /**
     * klt_1 klt=1:1分钟;5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
     */
    public static String KLT_15 = "15";
    /**
     * klt_1 klt=1:1分钟;5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
     */
    public static String KLT_30 = "30";
    /**
     * klt_1 klt=1:1分钟;5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
     */
    public static String KLT_60 = "60";
    /**
     * klt_1 klt=1:1分钟;5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
     */
    public static String KLT_120 = "120";
    /**
     * klt_1 klt=101:日;
     */
    public static String KLT_101 = "101";
    /**
     * klt_1 klt=1:1分钟;5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
     */
    public static String KLT_102 = "102";
    /**
     * klt_1 klt=1:1分钟;5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
     */
    public static String KLT_103 = "103";
    /**
     * klt_1 klt=1:1分钟;5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
     */
    public static String KLT_104 = "104";
    /**
     * klt_1 klt=1:1分钟;5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
     */
    public static String KLT_105 = "105";
    /**
     * klt_1 klt=1:1分钟;5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
     */
    public static String KLT_106 = "106";

    /**
     * DAYS_1 天数-1
     */
    public static String DAYS_1 = "1";
    /**
     * DAYS_1 天数-7
     */
    public static String DAYS_7 = "7";
    /**
     * DAYS_1 天数-30
     */
    public static String DAYS_30 = "30";
    /**
     * DAYS_365 年
     */
    public static String DAYS_365 = "365";

    /**
     * st_plate_f139_AG f139 2-A股主板(00XXXX/60XXXX);
     */
    public static String ST_PLATE_F139_AG = "2";
    /**
     * ST_PLATE_F139_BG 3-B股(200XXX/900XXX);
     */
    public static String ST_PLATE_F139_BG = "3";
    /**
     * ST_PLATE_F139_CYB 5-创业板(30XXXX);
     */
    public static String ST_PLATE_F139_CYB = "5";
    /**
     * ST_PLATE_F139_KCB 32-科创板(688XXX);
     */
    public static String ST_PLATE_F139_KCB = "32";
    /**
     * ST_PLATE_F139_CTPZ 33-存托凭证(689XXX,689是科创板专门为CDR存托凭证公司)
     */
    public static String ST_PLATE_F139_CTPZ = "33";

    /**
     * ST_BIZ_TYPE_ZAOZHIYINSHUA 造纸印刷-BK0470
     */
    public static String ST_BIZ_TYPE_ZAOZHIYINSHUA = "造纸印刷";
    /**
     * 酿酒行业-BK0477
     */
    public static String ST_BIZ_TYPE_NIANG_JIU_HANG_YE = "酿酒行业";
    /**
     * 酿酒行业-BK0477
     */
    public static String ST_BIZ_TYPE_CODE_NIANG_JIU_HANG_YE = "BK0477";
    /**
     * ST_BIZ_TYPE_YOU_SE_JIN_SHU 有色金属-BK0478
     */
    public static String ST_BIZ_TYPE_YOU_SE_JIN_SHU = "有色金属";
    /**
     * ST_BIZ_TYPE_SHI_YOU 石油行业-BK0464
     */
    public static String ST_BIZ_TYPE_SHI_YOU = "石油行业";

    /**
     * DB_RANK_BIZ_TYPE_BAN_KUAI 数据库字段-排行榜-业务-类型-板块
     */
    public static String DB_RANK_BIZ_TYPE_BAN_KUAI = "bk";
    /**
     * DB_RANK_BIZ_TYPE_GAI_NIAN 排行榜-类型-概念
     */
    public static String DB_RANK_BIZ_TYPE_GAI_NIAN = "gn";
    /**
     * DB_RANK_BIZ_TYPE_ETF
     */
    public static String DB_RANK_BIZ_TYPE_ETF = "etf";
    /**
     * DB_RANK_BIZ_TYPE_LOF
     */
    public static String DB_RANK_BIZ_TYPE_LOF = "LOF";
    /**
     * DB_RANK_BIZ_TYPE_ZS 指数
     */
    public static String DB_RANK_BIZ_TYPE_ZS = "zs";

    /**
     * 沪市
     */
    public static long F13_SHANGHAI = 1;
    /**
     * 深市
     */
    public static long F13_SHENZHEN = 0;
    /**
     * DB_RANK_BIZ_F12_BAN_KUAI f13类型-板块
     */
    public static long DB_RANK_BIZ_F12_BAN_KUAI = 90;
    /**
     * DB_RANK_BIZ_F19_BAN_KUAI f19类型-板块
     */
    public static long DB_RANK_BIZ_F19_BK_MAIN = 2;
    /**
     * DB_RANK_BIZ_F139_BK_MAIN f19类型-板块:主板板块
     * 2-A股主板(00XXXX/60XXXX);     2022-08-18：3149
     */
    public static long DB_RANK_BIZ_F139_BK_MAIN = 2;
    /**
     * 5-创业板(30XXXX);             2022-08-18：1184
     */
    public static Long F139_BK_CYB = 5L;
    /**
     * 32-科创板(688XXX);            2022-08-18：459
     */
    public static Long F139_BK_KCB = 32L;
    /**
     * 3-B股(20XXXX/900XXX);         2022-08-18：100;
     */
    public static Long F139_BK_B = 3L;//
    /**
     * 80-北交所(43XXXX/83XXXX/87XXXX);    2022-08-18：109
     */
    public static Long F139_BK_BJS = 80L;//;

    /**
     * DB_STOCK_F107_STOCK_STATUS_STOP F107:5 停牌
     */
    public static long F107_STOCK_STATUS_STOP = 5;
    /**
     * DB_RANK_BIZ_F148_STOCK_STATUS_DELISTED f148 状态：2-退市或停牌;
     */
    public static long DB_RANK_BIZ_F148_STOCK_STATUS_DELISTED = 2;
    /**
     * DB_RANK_BIZ_F148_STOCK_STATUS_DELISTING 退市整理
     */
    public static long DB_RANK_BIZ_F148_STOCK_STATUS_DELISTING = 2048;
    /**
     * DB_RANK_BIZ_F148_STOCK_STATUS_ST f148 状态：4-ST股；
     */
    public static long DB_RANK_BIZ_F148_STOCK_STATUS_ST = 4;
    /**
     * DB_RANK_BIZ_F148_STOCK_STATUS_UNLISTED 16-未上市;
     */
    public static long DB_RANK_BIZ_F148_STOCK_STATUS_UNLISTED = 16;
    /**
     * DB_RANK_BIZ_F148_STOCK_STATUS_SUSPENSION 32-暂停上市；
     */
    public static long DB_RANK_BIZ_F148_STOCK_STATUS_SUSPENSION = 32;

    /**
     * HTTP_KLINE_SECID_PREFIX_BANKUAI 请求前缀-板块
     */
    public static String HTTP_KLINE_SECID_PREFIX_BANKUAI = "90.";
    /**
     * HTTP_KLINE_TYPE_BK_REFIX k线类型-板块开头
     */
    public static String HTTP_KLINE_TYPE_BK_REFIX = "BK";


    /**
     * 交易天数：n
     */
    public static int TRADE_DAYS_1 = 1;
    /**
     * 交易天数：n
     */
    public static int TRADE_DAYS_2 = 2;
    /**
     * 交易天数：n
     */
    public static int TRADE_DAYS_3 = 3;
    /**
     * TRADE_DAYS_5 交易天数：n
     */
    public static int TRADE_DAYS_5 = 5;
    /**
     * TRADE_DAYS_10 交易天数：n
     */
    public static int TRADE_DAYS_10 = 10;
    /**
     * TRADE_DAYS_15 交易天数：n
     */
    public static int TRADE_DAYS_15 = 15;
    /**
     * TRADE_DAYS_20 交易天数：n
     */
    public static int TRADE_DAYS_20 = 20;
    /**
     * TRADE_DAYS_40 交易天数：n
     */
    public static int TRADE_DAYS_40 = 40;
    /**
     * TRADE_DAYS_60 交易天数：n
     */
    public static int TRADE_DAYS_60 = 60;
    /**
     * TRADE_DAYS_120 交易天数：n
     */
    public static int TRADE_DAYS_120 = 120;
    /**
     * TRADE_DAYS_250 交易天数：250，一年
     */
    public static int TRADE_DAYS_250 = 250;
    /**
     * MINUTE_1 周期-分钟：n
     */
    public static int MINUTE_1 = 1;
    /**
     * MINUTE_2
     */
    public static int MINUTE_2 = 2;
    /**
     * MINUTE_3
     */
    public static int MINUTE_3 = 3;
    /**
     * 周期-分钟：n
     */
    public static int MINUTE_5 = 5;
    /**
     * MINUTE_15
     */
    public static int MINUTE_15 = 15;
    /**
     * MINUTE_30
     */
    public static int MINUTE_30 = 30;
    /**
     * MINUTE_60
     */
    public static int MINUTE_60 = 60;
    /**
     * MINUTE_120
     */
    public static int MINUTE_120 = 120;
    /**
     * KLT_DAY_1
     */
    public static int KLT_DAY_1 = 101;
    /**
     * MA_5 均线
     */
    public static int MA_5 = 5;
    /**
     * MA_10
     */
    public static int MA_10 = 10;
    /**
     * MA_15
     */
    public static int MA_15 = 15;
    /**
     * MA_20
     */
    public static int MA_20 = 20;
    /**
     * MA_30
     */
    public static int MA_30 = 30;
    /**
     * MA_40
     */
    public static int MA_40 = 40;
    /**
     * MA_60
     */
    public static int MA_60 = 60;
    /**
     * MA_120
     */
    public static int MA_120 = 120;
    /**
     * MA_250
     */
    public static int MA_250 = 250;

    /**
     * 渠道：京东
     */
    public static String SOURCE_JD = "1";
    /**
     * 渠道：zfb
     */
    public static String SOURCE_ZFB = "2";
    /**
     * 渠道：TTJJ
     */
    public static String SOURCE_TTJJ = "3";

    /**
     * 份额合计
     */
    public static String SHARE_SUM = "shareSum";
    /**
     * 成本金额合计
     */
    public static String AMT_SUM = "amtSum";
    /**
     * 服务费
     */
    public static String SERVER_CHARGE = "serverCharge";
    /**
     * 名称
     */
    public static String FUND_NAME = "fundName";
    /**
     * 编码
     */
    public static String FUND_CODE = "fundCode";

    /**
     * keyRsMin
     */
    public static String keyRsMin = "netMin";
    /**
     * keyRsMax
     */
    public static String keyRsMax = "netMax";
    /**
     * keyRsNetCloseMin
     */
    public static String keyRsNetCloseMin = "netCloseMin";
    /**
     * keyRsNetCloseMax
     */
    public static String keyRsNetCloseMax = "netCloseMax";
    /**
     * keyRsNetCloseAvg 收盘均值
     */
    public static String keyRsNetCloseAvg = "netCloseAvg";
    /**
     * keyRsNetClose 收盘价
     */
    public static String keyRsNetClose = "netClose";
    /**
     * keyRsJyeAvg 交易额均量
     */
    public static String keyRsCjeAvg = "cjeAvg";
    /**
     * keyRsKlineCount k线个数
     */
    public static String keyRsKlineCount = "klineCount";
    /**
     * keyRsKlineZqmc 证券名称
     */
    public static String keyRsKlineZqmc = "klineZqmc";
    /**
     * KLINE_TYPE_STOCK k线类型-股票
     */
    public static String KLINE_TYPE_STOCK = "stock";
    /**
     * KLINE_TYPE_INDEX k线类型-指数
     */
    public static String KLINE_TYPE_INDEX = "index";
    /**
     * KLINE_TYPE_BAN_KUAI k线类型-板块
     */
    public static String KLINE_TYPE_BAN_KUAI = "banKuai";
    /**
     * KLINE_TYPE_ETF k线类型-etf
     */
    public static String KLINE_TYPE_ETF = "etf";

    /**
     * NUM_MAX_999 数量上限-99
     */
    public static int NUM_MAX_999 = 999;
    /**
     * NUM_MAX_9999
     */
    public static int NUM_MAX_9999 = 9999;
    /**
     * NUM_MAX_99 数量上限-99
     */
    public static int NUM_MAX_99 = 99;

    /**
     * NUM_YI_10000
     */
    public static BigDecimal NUM_YI_10000 = new BigDecimal("100000000").multiply(new BigDecimal("10000"));
    /**
     * NUM_YI_1000
     */
    public static BigDecimal NUM_YI_1000 = new BigDecimal("100000000").multiply(new BigDecimal("1000"));
    /**
     * NUM_YI_500
     */
    public static BigDecimal NUM_YI_500 = new BigDecimal("100000000").multiply(new BigDecimal("500"));

    /**
     * 200亿
     */
    public static BigDecimal NUM_YI_200 = new BigDecimal("100000000").multiply(new BigDecimal("200"));
    /**
     * NUM_YI_100
     */
    public static BigDecimal NUM_YI_100 = new BigDecimal("100000000").multiply(new BigDecimal("100"));
    /**
     * NUM_YI_50 数字-亿
     */
    public static BigDecimal NUM_YI_50 = new BigDecimal("100000000").multiply(new BigDecimal("50"));
    /**
     * NUM_YI_40 数字-亿
     */
    public static BigDecimal NUM_YI_40 = new BigDecimal("100000000").multiply(new BigDecimal("40"));
    /**
     * NUM_YI_30 数字-亿
     */
    public static BigDecimal NUM_YI_30 = new BigDecimal("100000000").multiply(new BigDecimal("30"));
    /**
     * 20亿
     */
    public static BigDecimal NUM_YI_20 = new BigDecimal("100000000").multiply(new BigDecimal("20"));
    /**
     * NUM_YI_15
     */
    public static BigDecimal NUM_YI_15 = new BigDecimal("100000000").multiply(new BigDecimal("15"));
    /**
     * 10亿
     */
    public static BigDecimal NUM_YI_10 = new BigDecimal("100000000").multiply(new BigDecimal("10"));
    /**
     * NUM_YI_5
     */
    public static BigDecimal NUM_YI_5 = new BigDecimal("100000000").multiply(new BigDecimal("5"));
    /**
     * 2亿
     */
    public static BigDecimal NUM_YI_2 = new BigDecimal("100000000").multiply(new BigDecimal("2"));
    /**
     * 1亿
     */
    public static BigDecimal NUM_YI_1 = new BigDecimal("100000000").multiply(new BigDecimal("1"));
    /**
     * NUM_YI_0
     */
    public static BigDecimal NUM_YI_0 = new BigDecimal("100000000").multiply(new BigDecimal("0"));


    /**
     * DATE_WEEK_5
     */
    public static String DATE_WEEK_5 = "五";

    /**
     * MA_UPDATE_FLAG_MINUTE_15_30 均线更新标志-分钟-周期
     */
    public static String MA_UPDATE_FLAG_MINUTE_1_5 = "maMinute1Type5";
    public static String MA_UPDATE_FLAG_MINUTE_1_10 = "maMinute1Type10";
    public static String MA_UPDATE_FLAG_MINUTE_1_20 = "maMinute1Type20";
    public static String MA_UPDATE_FLAG_MINUTE_1_30 = "maMinute1Type30";
    public static String MA_UPDATE_FLAG_MINUTE_1_60 = "maMinute1Type60";
    public static String MA_UPDATE_FLAG_MINUTE_1_120 = "maMinute1Type120";
    public static String MA_UPDATE_FLAG_MINUTE_1_250 = "maMinute1Type250";
    public static String MA_UPDATE_FLAG_MINUTE_5_5 = "maMinute5Type5";
    public static String MA_UPDATE_FLAG_MINUTE_5_10 = "maMinute5Type10";
    public static String MA_UPDATE_FLAG_MINUTE_5_20 = "maMinute5Type20";
    public static String MA_UPDATE_FLAG_MINUTE_5_30 = "maMinute5Type30";
    public static String MA_UPDATE_FLAG_MINUTE_5_60 = "maMinute5Type60";
    public static String MA_UPDATE_FLAG_MINUTE_5_120 = "maMinute5Type120";
    public static String MA_UPDATE_FLAG_MINUTE_5_250 = "maMinute5Type250";
    public static String MA_UPDATE_FLAG_MINUTE_15_5 = "maMinute15Type5";
    public static String MA_UPDATE_FLAG_MINUTE_15_10 = "maMinute15Type10";
    public static String MA_UPDATE_FLAG_MINUTE_15_20 = "maMinute15Type20";
    public static String MA_UPDATE_FLAG_MINUTE_15_30 = "maMinute15Type30";
    public static String MA_UPDATE_FLAG_MINUTE_15_60 = "maMinute15Type60";
    public static String MA_UPDATE_FLAG_MINUTE_15_120 = "maMinute15Type120";
    public static String MA_UPDATE_FLAG_MINUTE_15_250 = "maMinute15Type250";
    public static String MA_UPDATE_FLAG_MINUTE_30_5 = "maMinute30Type5";
    public static String MA_UPDATE_FLAG_MINUTE_30_10 = "maMinute30Type10";
    public static String MA_UPDATE_FLAG_MINUTE_30_20 = "maMinute30Type20";
    public static String MA_UPDATE_FLAG_MINUTE_30_30 = "maMinute30Type30";
    public static String MA_UPDATE_FLAG_MINUTE_30_60 = "maMinute30Type60";
    public static String MA_UPDATE_FLAG_MINUTE_30_120 = "maMinute30Type120";
    public static String MA_UPDATE_FLAG_MINUTE_30_250 = "maMinute30Type250";
    public static String MA_UPDATE_FLAG_MINUTE_60_5 = "maMinute60Type5";
    public static String MA_UPDATE_FLAG_MINUTE_60_10 = "maMinute60Type10";
    public static String MA_UPDATE_FLAG_MINUTE_60_20 = "maMinute60Type20";
    public static String MA_UPDATE_FLAG_MINUTE_60_30 = "maMinute60Type30";
    public static String MA_UPDATE_FLAG_MINUTE_60_60 = "maMinute60Type60";
    public static String MA_UPDATE_FLAG_MINUTE_60_120 = "maMinute60Type120";
    public static String MA_UPDATE_FLAG_MINUTE_60_250 = "maMinute60Type250";
    public static String MA_UPDATE_FLAG_MINUTE_120_5 = "maMinute120Type5";
    public static String MA_UPDATE_FLAG_MINUTE_120_10 = "maMinute120Type10";
    public static String MA_UPDATE_FLAG_MINUTE_120_20 = "maMinute120Type20";
    public static String MA_UPDATE_FLAG_MINUTE_120_30 = "maMinute120Type30";
    public static String MA_UPDATE_FLAG_MINUTE_120_60 = "maMinute120Type60";
    public static String MA_UPDATE_FLAG_MINUTE_120_120 = "maMinute120Type120";
    public static String MA_UPDATE_FLAG_MINUTE_120_250 = "maMinute120Type250";
    public static String MA_UPDATE_FLAG_DAY_1_5 = "maDay1Type5";
    public static String MA_UPDATE_FLAG_DAY_1_10 = "maDay1Type10";
    public static String MA_UPDATE_FLAG_DAY_1_20 = "maDay1Type20";
    public static String MA_UPDATE_FLAG_DAY_1_30 = "maDay1Type30";
    public static String MA_UPDATE_FLAG_DAY_1_60 = "maDay1Type60";
    public static String MA_UPDATE_FLAG_DAY_1_120 = "maDay1Type120";
    public static String MA_UPDATE_FLAG_DAY_1_250 = "maDay1Type250";
    public static String MA_UPDATE_FLAG_WEEK_1_5 = "maWeek1Type5";
    public static String MA_UPDATE_FLAG_WEEK_1_10 = "maWeek1Type10";
    public static String MA_UPDATE_FLAG_WEEK_1_20 = "maWeek1Type20";
    public static String MA_UPDATE_FLAG_WEEK_1_30 = "maWeek1Type30";
    public static String MA_UPDATE_FLAG_WEEK_1_60 = "maWeek1Type60";
    public static String MA_UPDATE_FLAG_WEEK_1_120 = "maWeek1Type120";
    public static String MA_UPDATE_FLAG_WEEK_1_250 = "maWeek1Type250";
    public static String MA_UPDATE_FLAG_MONTH_1_5 = "maMonth1Type5";
    public static String MA_UPDATE_FLAG_MONTH_1_10 = "maMonth1Type10";
    public static String MA_UPDATE_FLAG_MONTH_1_20 = "maMonth1Type20";
    public static String MA_UPDATE_FLAG_MONTH_1_30 = "maMonth1Type30";
    public static String MA_UPDATE_FLAG_MONTH_1_60 = "maMonth1Type60";
    public static String MA_UPDATE_FLAG_MONTH_1_120 = "maMonth1Type120";
    public static String MA_UPDATE_FLAG_MONTH_1_250 = "maMonth1Type250";
    public static String MA_TYPE_MINUTE1 = "MA_TYPE_MINUTE1";
    public static String MA_TYPE_MINUTE5 = "MA_TYPE_MINUTE5";
    public static String MA_TYPE_MINUTE15 = "MA_TYPE_MINUTE15";
    public static String MA_TYPE_MINUTE30 = "MA_TYPE_MINUTE30";
    public static String MA_TYPE_MINUTE60 = "MA_TYPE_MINUTE60";
    public static String MA_TYPE_MINUTE120 = "MA_TYPE_MINUTE120";
    public static String MA_TYPE_MINUTE250 = "MA_TYPE_MINUTE250";
    public static String MA_TYPE_DAY = "MA_TYPE_DAY";
    public static String MA_TYPE_WEEK = "MA_TYPE_WEEK";
    public static String MA_TYPE_MONTH = "MA_TYPE_MONTH";

    public static String SHANG_HAI = "1.000001";
    public static String SHEN_ZHEN = "0.399001";
    public static String CYB = "0.399006";
    public static String HS_300_000300 = "1.000300";
    public static String CYB_50_399673 = "0.399673";
    public static String ZZ_500_000905 = "1.000905";
    public static String SH_50_000016 = "1.000016";
    public static String KCB_50 = "1.000688";
    public static String BIZ_QUANSHANG = "0.399975";

    /**
     * MA_DAY_5 均线-日-5
     */
    public static String MA_DAY_5 = "MA_DAY_5";
    /**
     * MA_DAY_10 均线-日-
     */
    public static String MA_DAY_10 = "MA_DAY_10";
    /**
     * MA_DAY_20 均线-日-
     */
    public static String MA_DAY_20 = "MA_DAY_20";
    /**
     * MA_DAY_40 均线-日-
     */
    public static String MA_DAY_40 = "MA_DAY_40";
    /**
     * MA_DAY_60 均线-日-
     */
    public static String MA_DAY_60 = "MA_DAY_60";
    /**
     * MA_DAY_120 均线-日-
     */
    public static String MA_DAY_120 = "MA_DAY_120";
    /**
     * MA_DAY_250 均线-日-
     */
    public static String MA_DAY_250 = "MA_DAY_250";

    /**
     * ORDER_FIELD_NET_AREA_DAY_5 排序字段
     */
    public static String ORDER_FIELD_NET_AREA_DAY_5 = "NET_AREA_DAY_5";
    /**
     * ORDER_FIELD_NET_AREA_DAY_10
     */
    public static String ORDER_FIELD_NET_AREA_DAY_10 = "NET_AREA_DAY_10";
    /**
     * ORDER_FIELD_NET_AREA_DAY_20
     */
    public static String ORDER_FIELD_NET_AREA_DAY_20 = "NET_AREA_DAY_20";
    /**
     * ORDER_FIELD_NET_AREA_DAY_40
     */
    public static String ORDER_FIELD_NET_AREA_DAY_40 = "NET_AREA_DAY_40";
    /**
     * ORDER_FIELD_NET_AREA_DAY_60
     */
    public static String ORDER_FIELD_NET_AREA_DAY_60 = "NET_AREA_DAY_60";
    /**
     * ORDER_FIELD_ADR_UP_SUM_1_60
     */
    public static String ORDER_FIELD_ADR_UP_SUM_1_60 = "ADR_UP_SUM_1_60";
    public static String ORDER_FIELD_ADR_UP_SUM_1_40 = "ADR_UP_SUM_1_40";
    public static String ORDER_FIELD_ADR_UP_SUM_1_20 = "ADR_UP_SUM_1_20";
    /**
     * ORDER_FIELD_F3
     */
    public static String ORDER_FIELD_F3 = "F3";
    /**
     * ORDER_FIELD_MAXDOWN
     */
    public static String ORDER_FIELD_MAXDOWN = "maxDown";
    /**
     * ORDER_FIELD_MINRISE
     */
    public static String ORDER_FIELD_MINRISE = "minRise";
    /**
     * 排序字段-我的仓位-最新市值
     */
    public static String ORDER_FIELD_MY_ZXSZ = "myPositionZxsz";

    /**
     * 报表数据-业绩快报
     */
    public static String REPORT_NAME_RPT_FCI_PERFORMANCEE = "RPT_FCI_PERFORMANCEE";
    /**
     * 报表数据-业绩报表
     */
    public static String REPORT_NAME_RPT_LICO_FN_CPD = "RPT_LICO_FN_CPD";
    /**
     * 报表数据-业绩预告
     */
    public static String REPORT_NAME_RPT_PUBLIC_OP_NEWPREDICT = "RPT_PUBLIC_OP_NEWPREDICT";


    /**
     * DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_1
     */
    public static String DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_1 = "ADR_UP_SUM_1_1";
    /**
     * DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_2
     */
    public static String DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_2 = "ADR_UP_SUM_1_2";
    /**
     * DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_3
     */
    public static String DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_3 = "ADR_UP_SUM_1_3";
    /**
     * DB_STOCK_ADR_COUNT_ADR_UP_COUNT_1_5
     */
    public static String DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_5 = "ADR_UP_SUM_1_5";
    /**
     * DB_STOCK_ADR_COUNT_ADR_UP_COUNT_1_10
     */
    public static String DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_10 = "ADR_UP_SUM_1_10";
    /**
     * DB_STOCK_ADR_COUNT_ADR_UP_COUNT_20
     */
    public static String DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_20 = "ADR_UP_SUM_1_20";
    /**
     * DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_40
     */
    public static String DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_40 = "ADR_UP_SUM_1_40";
    /**
     * 数据库字段-股票涨幅次数
     */
    public static String DB_STOCK_ADR_COUNT_ADR_UP_SUM_20_40 = "ADR_UP_SUM_20_40";
    /**
     * DB_STOCK_ADR_COUNT_ADR_UP_SUM_40_60
     */
    public static String DB_STOCK_ADR_COUNT_ADR_UP_SUM_40_60 = "ADR_UP_SUM_40_60";
    /**
     * DB_STOCK_ADR_COUNT_ADR_UP_SUM_40_60
     */
    public static String DB_STOCK_ADR_COUNT_ADR_UP_SUM_1_60 = "ADR_UP_SUM_1_60";

    /**
     * 排序字段
     */
    public static String ORDER_BY_F3 = " F3 DESC ";

    /**
     * 每天时间
     */
    public static String TIME_09_35 = "09:35:00";
    public static String TIME_09_40 = "09:40:00";
    public static String TIME_09_45 = "09:45:00";
    public static String TIME_09_50 = "09:50:00";
    public static String TIME_09_55 = "09:55:00";
    public static String TIME_10_00 = "10:00:00";
    public static String TIME_10_05 = "10:05:00";
    public static String TIME_10_10 = "10:10:00";
    public static String TIME_10_15 = "10:15:00";
    public static String TIME_10_20 = "10:20:00";
    public static String TIME_10_25 = "10:25:00";
    public static String TIME_10_30 = "10:30:00";
    public static String TIME_10_35 = "10:35:00";
    public static String TIME_10_40 = "10:40:00";
    public static String TIME_10_45 = "10:45:00";
    public static String TIME_10_50 = "10:50:00";
    public static String TIME_10_55 = "10:55:00";
    public static String TIME_11_00 = "11:00:00";
    public static String TIME_11_05 = "11:05:00";
    public static String TIME_11_10 = "11:10:00";
    public static String TIME_11_15 = "11:15:00";
    public static String TIME_11_20 = "11:20:00";
    public static String TIME_11_25 = "11:25:00";
    public static String TIME_11_30 = "11:30:00";
    public static String TIME_13_00 = "13:00:00";
    public static String TIME_13_05 = "13:05:00";
    public static String TIME_13_10 = "13:10:00";
    public static String TIME_13_15 = "13:15:00";
    public static String TIME_13_20 = "13:20:00";
    public static String TIME_13_25 = "13:25:00";
    public static String TIME_13_30 = "13:30:00";
    public static String TIME_13_35 = "13:35:00";
    public static String TIME_13_40 = "13:40:00";
    public static String TIME_13_45 = "13:45:00";
    public static String TIME_13_50 = "13:50:00";
    public static String TIME_13_55 = "13:55:00";
    public static String TIME_14_00 = "14:00:00";
    public static String TIME_14_05 = "14:05:00";
    public static String TIME_14_10 = "14:10:00";
    public static String TIME_14_15 = "14:15:00";
    public static String TIME_14_20 = "14:20:00";
    public static String TIME_14_25 = "14:25:00";
    public static String TIME_14_30 = "14:30:00";
    public static String TIME_14_35 = "14:35:00";
    public static String TIME_14_40 = "14:40:00";
    public static String TIME_14_45 = "14:45:00";
    public static String TIME_14_50 = "14:50:00";
    public static String TIME_14_55 = "14:55:00";
    public static String TIME_15_00 = "15:00:00";


    public static List<String> TIME_TYPE_5_0935_TO_1000 = Arrays.asList(TIME_09_35, TIME_09_40, TIME_09_45, TIME_09_50, TIME_09_55, TIME_10_00);
    public static List<String> TIME_TYPE_5_0935_TO_1030 = Arrays.asList(TIME_09_35, TIME_09_40, TIME_09_45, TIME_09_50, TIME_09_55, TIME_10_00, TIME_10_05, TIME_10_10, TIME_10_15, TIME_10_20, TIME_10_25, TIME_10_30);
    public static List<String> TIME_TYPE5_1005_TO_1030 = Arrays.asList(TIME_10_05, TIME_10_10, TIME_10_15, TIME_10_20, TIME_10_25, TIME_10_30);
    public static List<String> TIME_TYPE5_1035_TO_1100 = Arrays.asList(TIME_10_35, TIME_10_40, TIME_10_45, TIME_10_50, TIME_10_55, TIME_11_00);
    public static List<String> TIME_TYPE5_1105_TO_1130 = Arrays.asList(TIME_11_05, TIME_11_10, TIME_11_15, TIME_11_20, TIME_11_25, TIME_11_30);
    public static List<String> TIME_TYPE_5_1305_TO_1330 = Arrays.asList(TIME_13_05, TIME_13_10, TIME_13_15, TIME_13_20, TIME_13_25, TIME_13_30);
    public static List<String> TIME_TYPE_5_1335_TO_1400 = Arrays.asList(TIME_13_35, TIME_13_40, TIME_13_45, TIME_13_50, TIME_13_55, TIME_14_00);
    public static List<String> TIME_TYPE_5_1405_TO_1430 = Arrays.asList(TIME_14_05, TIME_14_10, TIME_14_15, TIME_14_20, TIME_14_25, TIME_14_30);
    public static List<String> TIME_TYPE_5_1435_TO_1500 = Arrays.asList(TIME_14_35, TIME_14_40, TIME_14_45, TIME_14_50, TIME_14_55, TIME_15_00);
    public static List<String> TIME_TYPE_5_0935_TO_1500 = Arrays.asList(TIME_09_35, TIME_09_40, TIME_09_45, TIME_09_50, TIME_09_55, TIME_10_00, TIME_10_05, TIME_10_10, TIME_10_15, TIME_10_20, TIME_10_25, TIME_10_30, TIME_10_35, TIME_10_40, TIME_10_45, TIME_10_50, TIME_10_55, TIME_11_00, TIME_11_05, TIME_11_10, TIME_11_15, TIME_11_20, TIME_11_25, TIME_11_30
            , TIME_13_05, TIME_13_10, TIME_13_15, TIME_13_20, TIME_13_25, TIME_13_30, TIME_13_35, TIME_13_40, TIME_14_05, TIME_14_10, TIME_14_15, TIME_14_20, TIME_14_25, TIME_14_30, TIME_13_45, TIME_13_50, TIME_13_55, TIME_14_00, TIME_14_35, TIME_14_40, TIME_14_45, TIME_14_50, TIME_14_55, TIME_15_00);

    public static List<String> TIME_TYPE_15_0945_TO_1130 = Arrays.asList(TIME_09_45, TIME_10_00, TIME_10_15, TIME_10_30, TIME_10_45, TIME_11_00, TIME_11_15, TIME_11_30);
    public static List<String> TIME_TYPE_15_1315_TO_1500 = Arrays.asList(TIME_13_15, TIME_13_30, TIME_13_45, TIME_14_00, TIME_14_15, TIME_14_30, TIME_14_45, TIME_15_00);
    public static List<String> TIME_TYPE_15_0945_TO_1500 = Arrays.asList(TIME_09_45, TIME_10_00, TIME_10_15, TIME_10_30, TIME_10_45, TIME_11_00, TIME_11_15, TIME_11_30, TIME_13_15, TIME_13_30, TIME_13_45, TIME_14_00, TIME_14_15, TIME_14_30, TIME_14_45, TIME_15_00);

    /**
     * 每天时间段列表-n分钟-开始时间-至-结束时间
     */
    public static List<String> TIME_TYPE_30_1000_TO_1500 = Arrays.asList(TIME_10_00, TIME_10_30, TIME_11_00, TIME_11_30, TIME_13_30, TIME_14_00, TIME_14_30, TIME_15_00);
    public static List<String> TIME_TYPE_30_1500_TO_1000 = Arrays.asList(TIME_15_00, TIME_14_30, TIME_14_00, TIME_13_30, TIME_11_30, TIME_11_00, TIME_10_30, TIME_10_00);
    /**
     * 每天时间段列表-n分钟-开始时间-至-结束时间
     */
    public static List<String> TIME_TYPE_60_1030_TO_1500 = Arrays.asList(TIME_10_30, TIME_11_30, TIME_14_00, TIME_15_00);

    /**
     * 查询模式-http查询
     */
    public static String FIND_MODEL_HTTP = "HTTP";
    /**
     * 查询模式-数据库查询
     */
    public static String FIND_MODEL_DB = "DB";
    /**
     * 查询模式-缓存
     */
    public static String FIND_MODEL_CACHE = "CACHE";

    public static List<String> CACHE_DATE_LIST = new ArrayList<>();//缓存日期列表

}
