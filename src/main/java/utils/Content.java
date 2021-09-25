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
        zhishuMap.put("399005","中小100");
        zhishuMap.put("000010","上证180");
        zhishuMap.put("000009","上证380");
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
     * ST_BIZ_TYPE_NIANGJIUHANGYE 酿酒行业-BK0477
     */
    public static String ST_BIZ_TYPE_NIANGJIUHANGYE = "酿酒行业";

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
     * keyRsJyeAvg 交易额均量
     */
    public static String keyRsCjeAvg = "cjeAvg";
    /**
     * keyRsKlineCount k线个数
     */
    public static String keyRsKlineCount = "klineCount";
}
