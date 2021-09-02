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
     * klt_1 klt=1:1分钟;5:5分钟;15:15分钟;30:30分钟;60:60分钟;120:120分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
     */
    public static String KLT_1 = "1";
    public static String KLT_5 = "5";
    public static String KLT_15 = "15";
    public static String KLT_30 = "30";
    public static String KLT_60 = "60";
    public static String KLT_120 = "120";
    public static String KLT_101 = "101";
    public static String KLT_102 = "102";
    public static String KLT_103 = "103";
    public static String KLT_104 = "104";
    public static String KLT_105 = "105";
    public static String KLT_106 = "106";

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

    public static String keyRsMin = "rsMin";
    public static String keyRsMax = "rsMax";
    public static String keyRsNetCloseMin = "keyRsNetCloseMin";
    public static String keyRsNetCloseMax = "keyRsNetCloseMax";
}
