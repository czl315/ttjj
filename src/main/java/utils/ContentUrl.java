package utils;

/**
 * ContentUrl简介
 *
 * @author Administrator
 * @date 2022-01-07 23:54
 */
public class ContentUrl {
    /**
     * http地址：核心题材
     * https://emweb.securities.eastmoney.com/PC_HSF10/CoreConception/PageAjax?code=SH601318
     * 历史地址(已废弃)：http://f10.eastmoney.com/CoreConception/CoreConceptionAjax
     */
    public static String URL_CORE_CONCEPTION = "https://emweb.securities.eastmoney.com/PC_HSF10/CoreConception/PageAjax";
    /**
     * URL_DATA_CENTER:报表数据
     * 报表数据：https://datacenter-web.eastmoney.com/api/data/v1/get?callback=jQuery112308306859364351287_1641909539914&sortColumns=UPDATE_DATE%2CSECURITY_CODE&sortTypes=-1%2C-1&pageSize=50&pageNumber=1&reportName=RPT_FCI_PERFORMANCEE&columns=ALL&filter=(SECURITY_TYPE_CODE+in+(%22058001001%22%2C%22058001008%22))(TRADE_MARKET_CODE!%3D%22069001017%22)(REPORT_DATE%3D%272021-12-31%27)
     * 报表数据(旧)：http://datacenter-web.eastmoney.com/api/data/get
     */
    public static String URL_DATA_CENTER = "https://datacenter-web.eastmoney.com/api/data/v1/get";

    /**
     * KLINE_HTTP_HEAD K线历史-天
     */
    public static String KLINE_HTTP_HEAD = ".push2his.eastmoney.com/api/qt/stock/kline/get?cb=";
    /**
     * 查询(http)-资金流向-历史日期
     * https://push2his.eastmoney.com/api/qt/stock/fflow/daykline/get?cb=jQuery112305250574768837943_1654268615719&lmt=0&klt=101&fields1=f1%2Cf2%2Cf3%2Cf7&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58%2Cf59%2Cf60%2Cf61%2Cf62%2Cf63%2Cf64%2Cf65&ut=b2884a393a59ad64002292a3e90d46a5&secid=90.BK0451&_=1654268615720
     */
    public static String HTTP_HEAD_FFLOW_DAY = "https://push2his.eastmoney.com/api/qt/stock/fflow/daykline/get?cb=";


}
