package ttjj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomUtils;
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
        String rs = klineRsStrHttpDfcf(zhiShu, lmt, klt, begDate, endDate);

        JSONObject szzzMonthJson = JSON.parseObject(rs);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
//        String name = szzzMonthDataJson.getString("name");
//        System.out.println("指数名称："+name);
        if (szzzMonthDataJson == null || !szzzMonthDataJson.containsKey("klines")) {
            System.out.println("klines数据异常：" + JSON.toJSONString(szzzMonthDataJson));
            return null;
        }

        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        List<Kline> klineRs = new ArrayList<>();
        if (klines != null) {
            BigDecimal lastCloseAmt = new BigDecimal("0");//上一期收盘价
            for (Object klineObj : klines) {
                Kline kline = new Kline();
                String klineString = (String) klineObj;
                kline.setRs(klineString);
                kline.setKlt(klt);
                String[] klineArray = klineString.split(",");
                //  日期，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率
                //"2020-09-30,3389.74,3218.05,3425.63,3202.34,4906229054,6193724911616.00,6.58,-5.23,-177.63,13.40"
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
                lastCloseAmt = kline.getCloseAmt().subtract(kline.getZhangDieE());
                kline.setCloseLastAmt(lastCloseAmt);
                kline.setZqdm(szzzMonthDataJson.getString("code"));
                kline.setZqmc(szzzMonthDataJson.getString("name"));

                klineRs.add(kline);
            }
        }
        return klineRs;
    }

    /**
     * k线结果字符串类型
     *
     * @param zhiShu
     * @param lmt
     * @param klt
     * @param begDate
     * @param endDate
     * @return
     */
    public static String klineRsStrHttpDfcf(String zhiShu, int lmt, String klt, String begDate, String endDate) {
        begDate = begDate.replace("-", "");
        endDate = endDate.replace("-", "");
        long curTime = System.currentTimeMillis();
        int random = RandomUtils.nextInt(40, 99);
        //http://76.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery33106379847099350968_1624766355746&secid=0.399673&ut=fa5fd1943c7b386f172d6893dbfba10b&fields1=f1,f2,f3,f4,f5,f6&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61&klt=101&fqt=1&beg=0&end=20500101&smplmt=1390.59&lmt=1000000&_=1624766355750
        StringBuffer url = new StringBuffer();
//        url.append("http://96.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery331093188916841208381602168987937");
        url.append("http://" + random + ".push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery33103254362175743777_" + curTime);
        if (zhiShu.startsWith("5") || zhiShu.startsWith("6") || zhiShu.startsWith("000")) {
            url.append("&secid=" + "1." + zhiShu);
        } else {
            url.append("&secid=" + "0." + zhiShu);
        }

        url.append("&ut=fa5fd1943c7b386f172d6893dbfba10b");
        url.append("&fields1=f1,f2,f3,f4,f5,f6");
//        url.append("&fields1=");
//        url.append("f1,f2,f3,f4,f5,f6,f7,f8,f9,");
//        url.append("f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,");
//        url.append("f20,f21,f22,f23,f24,f25,f26,f27,f28,f29,");
//        url.append("f30,f31,f32,f33,f34,f35,f36,f37,f38,f39,");
//        url.append("f40,f41,f42,f43,f44,f45,f46,f47,f48,f49,");
//        url.append("f50,f51,f52,f53,f54,f55,f56,f57,f58,f59,");
//        url.append("f60,f61,f62,f63,f64,f65,f66,f67,f68,f69,");
//        url.append("f70,f71,f72,f73,f74,f75,f76,f77,f78,f79,");
//        url.append("f80,f81,f82,f83,f84,f85,f86,f87,f88,f89,");
//        url.append("f90,f91,f92,f93,f94,f95,f96,f97,f98,f99");

        url.append("&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61");/**     f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61  日期，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率 **/
//        url.append("&fields2=");
//        url.append("f1,f2,f3,f4,f5,f6,f7,f8,f9,");
//        url.append("f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,");
//        url.append("f20,f21,f22,f23,f24,f25,f26,f27,f28,f29,");
//        url.append("f30,f31,f32,f33,f34,f35,f36,f37,f38,f39,");
//        url.append("f40,f41,f42,f43,f44,f45,f46,f47,f48,f49,");
//        url.append("f50,f51,f52,f53,f54,f55,f56,f57,f58,f59,");
//        url.append("f60,f61,f62,f63,f64,f65,f66,f67,f68,f69,");
//        url.append("f70,f71,f72,f73,f74,f75,f76,f77,f78,f79,");
//        url.append("f80,f81,f82,f83,f84,f85,f86,f87,f88,f89,");
//        url.append("f90,f91,f92,f93,f94,f95,f96,f97,f98,f99");

        url.append("&klt=" + klt);
        url.append("&fqt=1");
        url.append("&beg=" + begDate);
        url.append("&end=" + endDate);
        url.append("&lmt=" + lmt);
        url.append("&_=" + curTime);

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("&StartDate=").append(startDate);

        System.out.println("请求url:" + url + JSON.toJSONString(urlParam));
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

        //jQuery33103254362175743777_1629614861227({"rc":0,"rt":17,"svr":181669733,"lt":1,"full":0,"data":{"code":"000300","market":1,"name":"沪深300","decimal":2,"dktotal":4044,"preKPrice":0.0,"klines":[]}});
        rs = rs.substring(rs.indexOf("({"));
        rs = rs.replace("({", "{");
        rs = rs.replace("});", "}");
        System.out.println("szKline:" + rs);

        return rs;
    }

}
