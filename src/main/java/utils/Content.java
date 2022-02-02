package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhilong
 * @date
 */
public class Content {
    /**常用指数
     * 000001	上证指数
     */
    private static Map<String, String> zhishuMap = new HashMap<>();
    static {
        zhishuMap.put("000001","上证指数");
        zhishuMap.put("000300","沪深300");
        zhishuMap.put("000016","上证50");
        zhishuMap.put("000903","中证100");
        zhishuMap.put("000905","中证500");
        zhishuMap.put("000852","中证1000");
        zhishuMap.put("000688","科创50");
        zhishuMap.put("399001","深证成指");
        zhishuMap.put("399006","创业板指");
        zhishuMap.put("399673","创业板50");
        zhishuMap.put("931643","科创创业50");
//        zhishuMap.put("399005","中小100");
        zhishuMap.put("000010","上证180");
//        zhishuMap.put("000009","上证380");
    }

    /**
     * 常用指数
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
     * klt_1 klt=1:1分钟;5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
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
     *  酿酒行业-BK0477
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
     * DB_RANK_BIZ_TYPE_HANG_YE 数据库字段-排行榜-业务-类型-板块
     */
    public static String DB_RANK_BIZ_TYPE_HANG_YE = "bk";
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
     * DB_RANK_BIZ_F12_BAN_KUAI f13类型-板块
     */
    public static long DB_RANK_BIZ_F12_BAN_KUAI = 90;
    /**
     * DB_RANK_BIZ_F19_BAN_KUAI f19类型-板块
     */
    public static long DB_RANK_BIZ_F19_BK_MAIN = 2;
    /**
     * DB_RANK_BIZ_F139_BK_MAIN f19类型-板块:主板板块
     */
    public static long DB_RANK_BIZ_F139_BK_MAIN = 2;

    /**
     * DB_RANK_BIZ_F148_STOCK_STATUS_DELISTED f148 状态：2-退市或停牌;
     */
    public static long DB_RANK_BIZ_F148_STOCK_STATUS_DELISTED = 2;
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
     * NUM_MAX_99 数量上限-99
     */
    public static int NUM_MAX_99 = 99;
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

    public static  String SHANG_HAI = "1.000001";
    public static  String SHEN_ZHEN = "0.399001";
    public static  String CYB = "0.399006";
    public static  String HS_300_000300 = "1.000300";
    public static  String CYB_50_399673 = "0.399673";
    public static  String ZZ_500_000905 = "1.000905";
    public static  String SH_50_000016 = "1.000016";
    public static  String KCB_50 = "1.000688";
    public static  String BIZ_QUANSHANG = "0.399975";
}
