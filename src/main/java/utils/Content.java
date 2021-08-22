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
