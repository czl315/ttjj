package ttjj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import ttjj.dto.Kline;
import ttjj.dto.Report;
import utils.Content;
import utils.DateUtil;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.List;

import static utils.Content.KLINE_TYPE_STOCK;

/**
 * @author chenzhilong
 * @date 2021/7/26
 */
public class ReportService {
    public static void main(String[] args) {
        // 查询业绩报表
        String stCode = "002624";
        List<Report> rs = httpReport(stCode);
        String date = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, 0);// String today = "2021-09-17";
        String qDate = "2021Q3";
        for (Report report : rs) {
            //是否有2021三季报
            if (report.getQDATE().equals(qDate)) {
                String startDate = "20210701";
                System.out.println(JSON.toJSONString(report));
                System.out.print(report.getSECURITY_NAME_ABBR() + "；营业总收入-环比增长:" + report.getYSHZ() + "；营业总收入-同比增长:" + report.getYSTZ() + ":净利润-环比增长:" + report.getSJLHZ());
                //查询区间价格涨跌-最近4个月
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
            break;//只查询第一个
        }
    }

    /**
     * 查询业绩报表
     *
     * @param stCode
     */
    public static List<Report> httpReport(String stCode) {
        long curTime = System.currentTimeMillis();
        //http://datacenter-web.eastmoney.com/api/data/get?callback=jQuery112306952726494784089_1635304353564&st=REPORTDATE&sr=-1&ps=50&p=1&sty=ALL&filter=(SECURITY_CODE%3D%22603099%22)&token=894050c76af8597a853f5b408b759f5d&type=RPT_LICO_FN_CPD
        //http://datacenter-web.eastmoney.com/api/data/get?callback=jQuery112309508918124001358_1635304406473&st=REPORTDATE&sr=-1&ps=50&p=1&sty=ALL&filter=(SECURITY_CODE%3D%22603099%22)&token=894050c76af8597a853f5b408b759f5d&type=RPT_LICO_FN_CPD
        StringBuffer url = new StringBuffer();
//        url.append(jqueryHttpHead);
//        url.append("jQuery112309508918124001358_");
        url.append("http://datacenter-web.eastmoney.com/api/data/get");
        StringBuffer urlParam = new StringBuffer();
        urlParam.append("callback=jQuery11230950891812400" + RandomUtils.nextInt(1000, 9999) + "_");
        urlParam.append(curTime);
        urlParam.append("&st=REPORTDATE");//排序列
        urlParam.append("&sr=-1");//倒序：-1；正序：0
        urlParam.append("&ps=50");//pagesize：每页大小
        urlParam.append("&p=1");//page：页
        urlParam.append("&sty=ALL");//&sty=ALL：全部
//        urlParam.append("&filter=(SECURITY_CODE=\"" + stCode + "\")");//股票代码
        urlParam.append("&filter=%28SECURITY_CODE%3D" + stCode + "%29");//股票代码
        urlParam.append("&token=894050c76af8597a853f5b408b759f5d");//token
        urlParam.append("&type=RPT_LICO_FN_CPD");//报表配置
//        System.out.println("请求url:");
//        System.out.println(url +"?"+ JSON.toJSONString(urlParam));
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
//        System.out.println("rs:" + rs);
        rs = rs.substring(rs.indexOf("({"));
        rs = rs.replace("({", "{");
        rs = rs.replace("});", "}");
//        System.out.println("rs:" + rs);
        JSONObject jsonObject = JSON.parseObject(rs);
        String data = jsonObject.getJSONObject("result").getString("data");
        List<Report> rsList = JSON.parseArray(data, Report.class);

        return rsList;
    }


}
