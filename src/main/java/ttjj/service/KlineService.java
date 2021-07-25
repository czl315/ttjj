package ttjj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dto.Kline;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhilong
 * @date 2021/7/26
 */
public class KlineService {
    /**
     * 查询-ETF-指数
     *
     * @param zhiShu  指数
     * @param lmt     数量
     * @param klt     K线周期类型
     * @param begDate
     * @param endDate
     */
    public static List<Kline> kline(String zhiShu, int lmt, String klt, String begDate, String endDate) {
        //http://76.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery33106379847099350968_1624766355746&secid=0.399673&ut=fa5fd1943c7b386f172d6893dbfba10b&fields1=f1,f2,f3,f4,f5,f6&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61&klt=101&fqt=1&beg=0&end=20500101&smplmt=1390.59&lmt=1000000&_=1624766355750
        StringBuffer url = new StringBuffer();
        url.append("http://96.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery331093188916841208381602168987937");
        if (zhiShu.startsWith("5") || zhiShu.startsWith("6")) {
            url.append("&secid=" + "1." + zhiShu);
        } else {
            url.append("&secid=" + "0." + zhiShu);
        }

        url.append("&ut=fa5fd1943c7b386f172d6893dbfba10b");
        url.append("&fields1=f1,f2,f3,f4,f5,f6");
        url.append("&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61");
        url.append("&klt=" + klt);
        url.append("&fqt=1");
        url.append("&beg=" + begDate);
        url.append("&end=" + endDate);
        url.append("&lmt=" + lmt);
        url.append("&_=" + System.currentTimeMillis());

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("&StartDate=").append(startDate);

//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
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

        String rsJson = rs.substring(rs.indexOf("{"));
        rsJson = rsJson.replace(");", "");
//        System.out.println("szKline:" + rsJson);

        List<String> klineList = new ArrayList<String>();
        JSONObject szzzMonthJson = JSON.parseObject(rsJson);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
//        String name = szzzMonthDataJson.getString("name");
//        System.out.println("指数名称："+name);
        List<Kline> klineRs = new ArrayList<>();
        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        if (klines != null) {
            BigDecimal lastCloseAmt = new BigDecimal("0");//上一期收盘价
            for (Object klineObj : klines) {
                String klineString = (String) klineObj;
                klineList.add(klineString);
                String[] klineArray = klineString.split(",");
                //  日期，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率
                //"2020-09-30,3389.74,3218.05,3425.63,3202.34,4906229054,6193724911616.00,6.58,-5.23,-177.63,13.40"
                Kline kline = new Kline();
                String dateTime = klineArray[0];
                if (klineArray[0].contains(":") && klineArray[0].length() == 16) {
                    dateTime = klineArray[0] + ":00";
                }
                BigDecimal closeAmt = new BigDecimal(klineArray[2]);
                kline.setKtime(dateTime);
                kline.setOpenAmt(new BigDecimal(klineArray[1]));
                kline.setCloseAmt(closeAmt);
                kline.setMaxAmt(new BigDecimal(klineArray[3]));
                kline.setMinAmt(new BigDecimal(klineArray[4]));
                kline.setCjl(new BigDecimal(klineArray[5]));
                kline.setCje(new BigDecimal(klineArray[6]));
                kline.setZhenFu(new BigDecimal(klineArray[7]));
                kline.setZhangDieFu(new BigDecimal(klineArray[8]));
                kline.setZhangDieE(new BigDecimal(klineArray[9]));
                kline.setHuanShouLv(new BigDecimal(klineArray[10]));
                kline.setCloseLastAmt(lastCloseAmt);
                lastCloseAmt = closeAmt;
                klineRs.add(kline);
            }
        }
        return klineRs;
    }
}
