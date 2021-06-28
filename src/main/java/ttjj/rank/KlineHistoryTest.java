package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dto.Kline;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenzhilong
 * @date 2021/6/27
 */
public class KlineHistoryTest {

    public static void main(String[] args) {
        String zqdm = "159949";
        String begDate = "20200101";//查询新增交易的开始时间
        String endDate = "20210625";
        String klt = "101";//klt=5:5分钟;101:日;102:周;103:月;104:3月;105:6月;106:12月
        int lmt = 100;
        List<Kline> klines = kline(zqdm, lmt, klt, begDate, endDate);

        String addFactor = "0.4";//因子-上涨加速
        int closeZdfComAddFactorCountYes = 0;//因子-上涨加速-收盘涨跌幅-成功个数
        int closeZdfComAddFactorCountNo = 0;//因子-上涨加速-收盘涨跌幅-失败个数
        String subFactor = "0.7";//下跌加速因子
        int closeZdfComSubFactorCountYes = 0;//因子-下跌加速-收盘涨跌幅-成功个数
        int closeZdfComSubFactorCountNo = 0;//因子-下跌加速-收盘涨跌幅-失败个数

        if (klines != null) {
            System.out.println("klines.size():" + klines.size());
            klines = klines.stream().filter(e -> e != null).sorted(Comparator.comparing(Kline::getZhangDieFu).reversed()).collect(Collectors.toList());//排序-涨跌幅
            for (Kline kline : klines) {
//                System.out.println(JSON.toJSONString(kline));
                System.out.print("日期:" + kline.getKtime() + ",");
                System.out.print("上一期收盘:" + kline.getCloseLastAmt() + ",");
                System.out.print("开盘:" + kline.getOpenAmt() + ",");
                BigDecimal openZdf = new BigDecimal(0);//开盘涨跌幅
                if (kline.getCloseLastAmt().compareTo(new BigDecimal(0)) == 0) {
                    System.out.print("开盘涨跌:未查询到昨日收盘价");
                } else {
                    openZdf = kline.getOpenAmt().subtract(kline.getCloseLastAmt()).divide(kline.getCloseLastAmt(), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
                    if (openZdf.compareTo(new BigDecimal(addFactor)) > 0) {//加速上涨-超过开盘涨幅因子
                        BigDecimal closeZdfComAddFactor = kline.getZhangDieFu().subtract(new BigDecimal(addFactor));
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.print("开盘涨跌:" + openZdf + ",");
                        System.out.print("收盘涨跌幅-上涨加速因子:" + closeZdfComAddFactor + ",");
                        if(closeZdfComAddFactor.compareTo(new BigDecimal(0)) >= 0){
                            closeZdfComAddFactorCountYes++;
                        }else {
                            closeZdfComAddFactorCountNo++;
                        }
                    } else {
                        System.out.print("开盘涨跌:" + openZdf + ",");
                    }

                    //下跌因子比较
                    if (openZdf.compareTo(new BigDecimal(subFactor)) <= 0) {//加速上涨-超过开盘涨幅因子
                        BigDecimal closeZdfComSubFactor = kline.getZhangDieFu().subtract(new BigDecimal(subFactor));
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.print("开盘涨跌:" + openZdf + ",");
                        System.out.print("收盘涨跌幅-下跌加速因子:" + closeZdfComSubFactor + ",");
                        if(closeZdfComSubFactor.compareTo(new BigDecimal(0)) > 0){
                            closeZdfComSubFactorCountYes++;
                        }else {
                            closeZdfComSubFactorCountNo++;
                        }
                    } else {
                        System.out.print("开盘涨跌:" + openZdf + ",");
                    }
                }
//                System.out.print("收盘:" + kline.getCloseAmt() + ",");
//            System.out.print("成交量:\t" + klineArray[5] + ",\t\t");
//            System.out.print("成交额:\t" + klineArray[6] + ",\t\t");
//                System.out.print("振幅:" + kline.getZhenFu() + ",");
                System.out.print("涨跌:" + kline.getZhangDieFu() + ",");
//                System.out.print("最低:" + kline.getMinAmt() + ",");
//                System.out.print("最高:" + kline.getMaxAmt() + ",");
//            System.out.print("换手率:" + klineArray[10] + ",");
                System.out.println();
            }
            System.out.println();
            System.out.println("上涨加速-收盘涨跌幅-成功个数:"+closeZdfComAddFactorCountYes+",成功率："+new BigDecimal(""+closeZdfComAddFactorCountYes).divide(new BigDecimal(closeZdfComAddFactorCountYes+closeZdfComAddFactorCountNo),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            System.out.println("上涨加速-收盘涨跌幅-失败个数:"+closeZdfComAddFactorCountNo+",成功率："+new BigDecimal(""+closeZdfComAddFactorCountNo).divide(new BigDecimal(closeZdfComAddFactorCountYes+closeZdfComAddFactorCountNo),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            System.out.println();
            System.out.println("下跌-收盘涨跌幅-成功个数:"+closeZdfComSubFactorCountYes+",成功率："+new BigDecimal(""+closeZdfComSubFactorCountYes).divide(new BigDecimal(closeZdfComSubFactorCountYes+closeZdfComSubFactorCountNo),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            System.out.println("下跌-收盘涨跌幅-失败个数:"+closeZdfComSubFactorCountNo+",成功率："+new BigDecimal(""+closeZdfComSubFactorCountNo).divide(new BigDecimal(closeZdfComSubFactorCountYes+closeZdfComSubFactorCountNo),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));

        }


    }

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
