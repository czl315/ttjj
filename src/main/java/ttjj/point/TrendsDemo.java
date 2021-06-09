package ttjj.point;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.trade.StockTradeDemo;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 上证指数
 *
 * @author chenzhilong
 * @date 2020/10/7
 */
public class TrendsDemo {
    static final String SHANG_HAI = "1.000001";
    static final String SHEN_ZHEN = "0.399001";
    static final String CYB = "0.399006";
    static final String HS_300_000300 = "1.000300";
    static final String CYB_50_399673 = "0.399673";
    static final String ZZ_500_000905 = "1.000905";
    static final String SH_50_000016 = "1.000016";
    static final String BIZ_QUANSHANG = "0.399975";
    static final String BIZ_BANDAOTI_XINPIAN_990001 = "0.007300";

    public static void main(String[] args) {
        String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        String curDate = "2021-06-04";
        findTrends("", "fupan", SHANG_HAI, 5, curDate, "pt_sh_time_min", "pt_sh_time_max");//查询指定指数的最大值时间、最小值时间
        findTrends("", "fupan", SH_50_000016, 5, curDate, "pt_sh50_time_min", "pt_sh50_time_max");//查询指定指数的最大值时间、最小值时间
        findTrends("", "fupan", HS_300_000300, 5, curDate, "pt_hs300_time_min", "pt_hs300_time_max");//查询指定指数的最大值时间、最小值时间
        findTrends("", "fupan", ZZ_500_000905, 5, curDate, "pt_zz500_time_min", "pt_zz500_time_max");//查询指定指数的最大值时间、最小值时间
        findTrends("", "fupan", SHEN_ZHEN, 5, curDate, "pt_sz_time_min", "pt_sz_time_max");//查询指定指数的最大值时间、最小值时间
        findTrends("", "fupan", CYB, 5, curDate, "pt_cyb_time_min", "pt_cyb_time_max");//查询指定指数的最大值时间、最小值时间
        findTrends("", "fupan", CYB_50_399673, 5, curDate, "pt_cyb50_time_min", "pt_cyb50_time_max");//查询指定指数的最大值时间、最小值时间
    }

    /**
     * 查询指定指数的最大值时间、最小值时间
     *
     * @param cookie
     * @param zhiShu
     * @param days
     * @param curDate
     * @param field1
     * @param field2
     */
    private static void findTrends(String cookie, String table, String zhiShu, int days, String curDate, String field1, String field2) {
        String url = "http://push2his.eastmoney.com/api/qt/stock/trends2/get?cb=jQuery112408829480231577647_1619343238054" +
                "&secid=" + zhiShu +
                "&ut=fa5fd1943c7b386f172d6893dbfba10b" +
                "&fields1=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11" +
                "&fields2=f51,f53,f56,f58" +
                "&iscr=0" +
                "&ndays=" + days +
                "&_=1619343238066" +
                "";
//        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("&StartDate=").append(startDate);

//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
        String rs = HttpUtil.sendGet(url, "", cookie);
        String rsJson = rs.substring(rs.indexOf("{"));
        rsJson = rsJson.replace(");", "");
//        System.out.println("szKline:"+rsJson);

        List<String> timePointList = new ArrayList<String>();
        JSONObject szzzMonthJson = JSON.parseObject(rsJson);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
        JSONArray trends = JSON.parseArray(szzzMonthDataJson.getString("trends"));
//        System.out.println(trends);
        for (Object timePoint : trends) {
            String timePointStr = (String) timePoint;
            timePointList.add(timePointStr);
        }

        BigDecimal dayPointMax = new BigDecimal(0);
        BigDecimal dayPointMin = new BigDecimal(0);
        String dayPointTimeMax = "";
        String dayPointTimeMin = "";
        boolean init = false;
        for (int i = 0; i <= timePointList.size() - 1; i++) {
            //"2021-04-23 09:30,2857.23,60147,2857.226"
            String timePoint = timePointList.get(i);
            String[] timePointArray = timePoint.split(",");
            String dateTime = timePointArray[0];
            String date = dateTime.substring(0, 10);
            //指定具体日期
            if (!curDate.equals(date)) {
                continue;
            }
            String point = timePointArray[1];
//            String count = timePointArray[2];
//            String point2 = timePointArray[3];
            //初始化
            if (!init) {
                init = true;
                dayPointMax = new BigDecimal(point);
                dayPointTimeMax = dateTime.substring(dateTime.indexOf(" "));
                dayPointMin = new BigDecimal(point);
                dayPointTimeMin = dateTime.substring(dateTime.indexOf(" "));
            }

            //比较最大最小
            BigDecimal curPoint = new BigDecimal(point);
            if (curPoint.compareTo(dayPointMin) <= 0) {
                dayPointMin = new BigDecimal(point);
                dayPointTimeMin = dateTime.substring(dateTime.indexOf(" ") + 1);
            }
            if (curPoint.compareTo(dayPointMax) >= 0) {
                dayPointMax = new BigDecimal(point);
                dayPointTimeMax = dateTime.substring(dateTime.indexOf(" ") + 1);
            }
        }
//        System.out.println("最大值：" + dayPointTimeMax + "---" + dayPointMax);
//        System.out.println("最小值：" + dayPointTimeMin + "---" + dayPointMin);

        //sql
        System.out.println("UPDATE `" + table + "` " +
                "SET " +
                "`" + field1 + "`='" + dayPointTimeMin + "'" + "," +
                "`" + field2 + "`='" + dayPointTimeMax + "'" +
                " WHERE (`CODE`='" + curDate + "') AND fupan.period='1'" + " AND fupan.TYPE=1;");
    }


}
