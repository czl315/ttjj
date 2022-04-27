package ttjj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import ttjj.dto.Kline;
import ttjj.dto.Report;
import utils.Content;
import utils.ContentUrl;
import utils.DateUtil;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static utils.Content.KLINE_TYPE_STOCK;

/**
 * @author chenzhilong
 * @date 2021/7/26
 */
public class ReportService {
    public static void main(String[] args) {
        // 查询业绩报表
        String stCode = "688699";
        List<Report> rs = listHttpReportByStCode(stCode);
        String date = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, 0);// String today = "2021-09-17";
        String qDate = "2021Q4";//报表周期
        for (Report report : rs) {
            //是否有2021三季报
            if (report.getQDATE().equals(qDate)) {
                String startDate = "20211001";
                System.out.println(JSON.toJSONString(report));
                System.out.print(report.getSECURITY_NAME_ABBR() + "；营业总收入-环比增长:" + report.getDJDYSHZ() + "；营业总收入-同比增长:" + report.getYSTZ() + ":净利润-环比增长:" + report.getDJDJLHZ()+ ":净利润-同比增长:" + report.getJLRTBZCL());
//                //查询区间价格涨跌-最近4个月
                BigDecimal totalAdr = new BigDecimal("0");
                List<Kline> klines = KlineService.kline(stCode, 4, Content.KLT_103, true, startDate, date, KLINE_TYPE_STOCK);
                for (Kline kline : klines) {
                    totalAdr = totalAdr.add(kline.getZhangDieFu());
                }
                System.out.print(",季度涨跌幅:" + totalAdr);
                System.out.println();
                break;
            }
//                            System.out.println(qDate + "没有查询到！");
//            break;//只查询第一个
        }
    }

    /**
     * 查询业绩报表-根据证券编码
     *
     * @param stCode
     */
    public static List<Report> listHttpReportByStCode(String stCode) {
        List<Report> rsList = new ArrayList<>();
        long curTime = System.currentTimeMillis();
        //http://datacenter-web.eastmoney.com/api/data/get?callback=jQuery112306952726494784089_1635304353564&st=REPORTDATE&sr=-1&ps=50&p=1&sty=ALL&filter=(SECURITY_CODE%3D%22603099%22)&token=894050c76af8597a853f5b408b759f5d&type=RPT_LICO_FN_CPD
        // http://datacenter-web.eastmoney.com/api/data/get?    callback=jQuery112309508918124001358_1635304406473 &st=REPORTDATE&sr=-1&ps=50&p=1&sty=ALL&filter=(SECURITY_CODE%3D%22603099%22)&token=894050c76af8597a853f5b408b759f5d&type=RPT_LICO_FN_CPD
        //https://datacenter-web.eastmoney.com/api/data/v1/get? callback=jQuery112308306859364351287_1641909539914&sortColumns=UPDATE_DATE%2CSECURITY_CODE&sortTypes=-1%2C-1&pageSize=50&pageNumber=1&reportName=RPT_FCI_PERFORMANCEE&columns=ALL&filter=(SECURITY_TYPE_CODE+in+(%22058001001%22%2C%22058001008%22))(TRADE_MARKET_CODE!%3D%22069001017%22)(REPORT_DATE%3D%272021-12-31%27)
        StringBuffer url = new StringBuffer();
//        url.append(jqueryHttpHead);
//        url.append("jQuery112309508918124001358_");
        url.append(ContentUrl.URL_DATA_CENTER);
        StringBuffer urlParam = new StringBuffer();
        urlParam.append("callback=jQuery11230830685936435" + RandomUtils.nextInt(1000, 9999) + "_");
        urlParam.append(curTime);
        urlParam.append("&sortColumns=UPDATE_DATE%2CSECURITY_CODE");//&sortColumns=UPDATE_DATE%2CSECURITY_CODE 排序字段
        urlParam.append("&sortTypes=-1%2C-1");//&sortTypes=-1%2C-1
        urlParam.append("&pageSize=50");//&pageSize=50
        urlParam.append("&pageNumber=1");//&pageNumber=1
        urlParam.append("&reportName=RPT_LICO_FN_CPD");//&reportName=RPT_FCI_PERFORMANCEE   RPT_LICO_FN_CPD:业绩报表
        urlParam.append("&columns=ALL");//&columns=ALL
        //&filter=(SECURITY_TYPE_CODE+in+(%22058001001%22%2C%22058001008%22))(TRADE_MARKET_CODE!%3D%22069001017%22)(REPORT_DATE%3D%272021-12-31%27)
        //&filter: (SECURITY_TYPE_CODE in ("058001001","058001008"))(TRADE_MARKET_CODE!="069001017")(REPORT_DATE='2021-12-31')  沪深A股
        urlParam.append("&filter=");
//        urlParam.append("%28").append("SECURITY_TYPE_CODE+in+%28%22058001001%22%2C%22058001008%22%3D%3D%28TRADE_MARKET_CODE!%3D%22069001017%22%3D");
        //(REPORT_DATE%3D%272021-12-31%27)    (REPORT_DATE='2021-12-31')
//        urlParam.append("%28REPORT_DATE=%27"+"2021-12-31"+"%27%3D");
//        urlParam.append("%28").append("REPORT_DATE="+"'2021-12-31'").append("%3D");
        //(SECURITY_CODE%3D%22603866%22)    (SECURITY_CODE="603866")
        urlParam.append("%28").append("SECURITY_CODE").append("%3D").append(stCode).append("%29");
//        urlParam.append("%28SECURITY_CODE%3D%22"+stCode+"%22%3D");
//        //(PUBLISHNAME="专用设备")  行业
//        urlParam.append("(PUBLISHNAME%3D%22%E4%B8%93%E7%94%A8%E8%AE%BE%E5%A4%87%22)");
//        urlParam.append("&st=REPORTDATE");//排序列:REPORTDATE;UPDATE_DATE:最新公告日期;SECURITY_CODE:证券编码
//        urlParam.append("&sr=-1");//倒序：-1；正序：0
//        urlParam.append("&ps=500");//pagesize：每页大小
//        urlParam.append("&p=1");//page：页
//        urlParam.append("&sty=ALL");//&sty=ALL：全部
////        urlParam.append("&filter=(SECURITY_CODE=\"" + stCode + "\")");//股票代码
//        urlParam.append("&filter=%28SECURITY_CODE%3D" + stCode + "%29");//股票代码
//        urlParam.append("&token=894050c76af8597a853f5b408b759f5d");//token
//        urlParam.append("&type=RPT_LICO_FN_CPD");//报表配置
//        System.out.println("请求url:");
        System.out.println(url +"?"+ urlParam.toString());
        String rs = "";
        try {
            rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
        } catch (Exception e) {
            System.out.println("/** http重试 **/");
            rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
        }

        /**
         * 如果返回异常，n次重试
         */
        for (int i = 0; i < 10; i++) {
            if (StringUtils.isBlank(rs)) {
                rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
            } else {
                break;
            }
        }
        System.out.println("rs:" + rs);
        rs = rs.substring(rs.indexOf("({"));
        rs = rs.replace("({", "{");
        rs = rs.replace("});", "}");
//        System.out.println("rs:" + rs);
//        if (rs.contains("安记食品")) {
//            System.out.println("111");
//        }
        JSONObject jsonObject = JSON.parseObject(rs);
        if (jsonObject == null || !jsonObject.containsKey("result") || jsonObject.getJSONObject("result") == null || !jsonObject.getJSONObject("result").containsKey("data")) {
            System.out.println("查询报表信息异常：" + JSON.toJSONString(rs));
            return rsList;
        }
        String data = jsonObject.getJSONObject("result").getString("data");
        rsList = JSON.parseArray(data, Report.class);

        return rsList;
    }


}
