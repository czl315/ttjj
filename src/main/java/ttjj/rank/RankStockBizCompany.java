package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dto.RankBizDataDiff;
import ttjj.dto.StockTrade;
import utils.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 排行-行业股票-公司-每日明细
 */
public class RankStockBizCompany {
    static String keyRsMax = "rsMax";
    static String keyRsMin = "rsMin";

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<String> bizList = new ArrayList<>();
//        bizList.add("BK0459");
//        bizList.add("BK0477");
//        bizList.add("BK0485");//旅游酒店
//        bizList.add("BK0737");//软件服务
//        bizList.add("BK0727");//医疗行业
        bizList.add("BK0473");//券商信托


        for (String biz : bizList) {
            List<RankBizDataDiff> rankBizDataDiffListBiz = listRankStockByBiz(500, biz);
            //显示业务排行-插入sql
            showBizSql(rankBizDataDiffListBiz, biz);

            // 最新周期价格
            showUpdateDbMaxMinNetByDays(rankBizDataDiffListBiz, 1, "NET_MIN_1", "NET_MAX_1");
            showUpdateDbMaxMinNetByDays(rankBizDataDiffListBiz, 7, "NET_MIN_7", "NET_MAX_7");
            showUpdateDbMaxMinNetByDays(rankBizDataDiffListBiz, 14, "NET_MIN_14", "NET_MAX_14");
            showUpdateDbMaxMinNetByDays(rankBizDataDiffListBiz, 30, "NET_MIN_30", "NET_MAX_30");
            showUpdateDbMaxMinNetByDays(rankBizDataDiffListBiz, 60, "NET_MIN_60", "NET_MAX_60");
            showUpdateDbMaxMinNetByDays(rankBizDataDiffListBiz, 90, "NET_MIN_90", "NET_MAX_90");
            showUpdateDbMaxMinNetByDays(rankBizDataDiffListBiz, 180, "NET_MIN_180", "NET_MAX_180");
            showUpdateDbMaxMinNetByDays(rankBizDataDiffListBiz, 365, "NET_MIN_360", "NET_MAX_360");
        }



    }

    /**
     * 更新最新净值-限定时间段的最大最小净值
     *
     * @param rankBizDataDiffListBiz
     * @param days
     * @param dbFieldLastNetMin
     */
    private static void showUpdateDbMaxMinNetByDays(List<RankBizDataDiff> rankBizDataDiffListBiz, int days, String dbFieldLastNetMin, String dbFieldLastNetMax) {
        for (RankBizDataDiff stockInfo : rankBizDataDiffListBiz) {
            //查询 -限定时间段的最大最小净值
//            LsjzUtil.findJzMaxMin(fundTrade.getZqdm(), days);
            //k线
            String klt = "101";//klt=101:日;102:周;103:月;104:3月;105:6月;106:12月
            kline(stockInfo.getF12(), days, klt, dbFieldLastNetMin, dbFieldLastNetMax);
        }
    }

    /**
     * 查询-ETF-指数
     *
     * @param zhiShu            指数
     * @param count             数量
     * @param klt               K线周期类型
     * @param dbFieldLastNetMin
     * @param dbFieldLastNetMax
     */
    public static void kline(String zhiShu, int count, String klt, String dbFieldLastNetMin, String dbFieldLastNetMax) {
        StringBuffer url = new StringBuffer();
        url.append("http://96.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery331093188916841208381602168987937");
        if (zhiShu.startsWith("5") || zhiShu.startsWith("6")) {
            url.append("&secid=" + "1." + zhiShu);
        } else {
            url.append("&secid=" + "0." + zhiShu);
        }

        url.append("&ut=fa5fd1943c7b386f172d6893dbfba10b");
        url.append("&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5%2Cf6");
        url.append("&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58%2Cf59%2Cf60%2Cf61");
        url.append("&klt=" + klt);
        url.append("&fqt=1");
        url.append("&end=20500101");
        url.append("&lmt=" + count);
        url.append("&_=1602168987942");

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("&StartDate=").append(startDate);

//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
        String rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
        String rsJson = rs.substring(rs.indexOf("{"));
        rsJson = rsJson.replace(");", "");
//        System.out.println("szKline:" + rsJson);

        List<String> klineList = new ArrayList<String>();
        JSONObject szzzMonthJson = JSON.parseObject(rsJson);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
//        String name = szzzMonthDataJson.getString("name");
//        System.out.println("指数名称："+name);
        if(szzzMonthDataJson==null || !szzzMonthDataJson.containsKey("klines")){
//            System.out.println("klines未查询到："+zhiShu);
            return;
        }
        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        if (klines != null) {
            for (Object kline : klines) {
                String klineStr = (String) kline;
                klineList.add(klineStr);
            }
        }

        Map<String, Double> netRs = handlerMaxJz(klineList);
        Double minJz = netRs.get(keyRsMin);
        Double maxJz = netRs.get(keyRsMax);

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE `rank_st_biz_com` ");
        sb.append("SET ");
        sb.append(" `" + dbFieldLastNetMin + "`=" + minJz + " ");
        sb.append(" ,`" + dbFieldLastNetMax + "`=" + maxJz + " ");
//        sb.append(" WHERE `FD_CODE`='" + zhiShu + "'" + " AND TYPE = '证券买入'" + ";");
        sb.append(" WHERE `f12`='" + zhiShu + "'" + "" + ";");
        System.out.println(sb);
    }

    /**
     * 计算最大净值、最小净值
     *
     * @return
     */
    private static Map<String, Double> handlerMaxJz(List<String> klineList) {
        Map<String, Double> rs = new HashMap<String, Double>();
        Double rsMax = 0.0;
        Double rsMin = 0.0;
        Double lastDwjz = 0.0;
        Double rsNewestDayNet = 0.0;
        Double rsOldestDayNet = 0.0;
        int curTempInt = 0;
        for (String klineStr : klineList) {
            //  日期，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率
            //"2020-09-30,3389.74,3218.05,3425.63,3202.34,4906229054,6193724911616.00,6.58,-5.23,-177.63,13.40"
            String[] klineArray = klineStr.split(",");
            String shouPan = klineArray[2];
            String zhangDie = klineArray[8];
            String chengJiaoE = klineArray[6];
            String curDate = klineArray[0];
//            System.out.print("日期:" + curDate + ",");
//            System.out.print("收盘:" + shouPan + ",");
//            System.out.print("涨跌幅:" + zhangDie + ",\t");
//            System.out.print("开盘:" + klineArray[1] + ",\t");
//            System.out.print("最高:" + klineArray[3] + ",");
//            System.out.print("最低:" + klineArray[4] + ",");
//            System.out.print("成交量:\t" + klineArray[5] + ",\t\t");
//            System.out.print("成交额:\t" + klineArray[6] + ",\t\t");
//            System.out.print("振幅:" + klineArray[7] + ",");
//            System.out.print("涨跌额:" + klineArray[9] + ",");
//            System.out.print("换手率:" + klineArray[10] + ",");
//            System.out.println();

//                    System.out.println(JSON.toJSONString(lsjzDataLsjz));
//            String dwJz = lsjzDataLsjz.getDWJZ();//当晚净值
            String dwJz = shouPan;//累计净值

            if (StringUtils.isBlank(dwJz)) {
                dwJz = "0";
            }
            String fsrq = curDate;
//            System.out.println("fsrq:" + fsrq + ",dwjzLong:" + dwJz);
            Double dwjzLong = Double.valueOf(dwJz);
            if (curTempInt == 0) {//最新一天的净值
                rsNewestDayNet = dwjzLong;
            }
            curTempInt++;
            if (dwjzLong > rsMax) {
                rsMax = dwjzLong;
            }
            if (dwjzLong < rsMin || rsMin == 0.0) {
                rsMin = dwjzLong;
            }
        }
        rs.put(keyRsMax, rsMax);
        rs.put(keyRsMin, rsMin);
        return rs;
    }

    /**
     * 显示业务排行-插入sql
     *
     * @param rankBizDataDiffList
     * @param queryType
     */
    private static void showBizSql(List<RankBizDataDiff> rankBizDataDiffList, String queryType) {
        int orderNum = 0;//序号
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
//        String today = "20210402";
        for (RankBizDataDiff entity : rankBizDataDiffList) {
            orderNum++;
            //显示插入数据库语句
            {
                System.out.println("INSERT INTO `bank19`.`rank_st_biz_com`(" +
                        "`rs`" +
                        ",`date`" +
                        ",`type`" +
                        ",`order_num`" +
                        ",`f2`" +
                        ",`f3`" +
                        ",`f4`" +
                        ",`f5`" +
                        ",`f6`" +
                        ",`f7`" +
                        ",`f8`" +
                        ",`f9`" +
                        ",`f10`" +
                        ",`f11`" +
                        ",`f12`" +
                        ",`f13`" +
                        ",`f14`" +
                        ",`f15`" +
                        ",`f16`" +
                        ",`f17`" +
                        ",`f18`" +
                        ",`f20`" +
                        ",`f21`" +
                        ",`f22`" +
                        ",`f23`" +
                        ",`f24`" +
                        ",`f25`" +
                        ",`f26`" +
                        ",`f33`" +
                        ",`f62`" +
                        ",`f104`" +
                        ",`f105`" +
                        ",`f107`" +
                        ",`f115`" +
                        ",`f124`" +
                        ",`f128`" +
                        ",`f140`" +
                        ",`f141`" +
                        ",`f136`" +
                        ",`f152`" +
                        ",`f207`" +
                        ",`f208`" +
                        ",`f209`" +
                        ",`f222`" +
                        ") VALUES (" +
                        " '" + JSON.toJSONString(entity) + "'" +
                        " ,'" + today + "'" +
                        " ,'" + queryType + "'" +
                        " ," + orderNum + "" +
                        " ," + entity.getF2() + "" +
                        " ," + entity.getF3() + "" +
                        " ," + entity.getF4() + "" +
                        " ," + entity.getF5() + "" +
                        " ," + entity.getF6() + "" +
                        " ," + entity.getF7() + "" +
                        " ," + entity.getF8() + "" +
                        " ," + entity.getF9() + "" +
                        " ," + entity.getF10() + "" +
                        " ," + entity.getF11() + "" +
                        " ,'" + entity.getF12() + "'" +
                        " ," + entity.getF13() + "" +
                        " ,'" + entity.getF14() + "'" +
                        " ," + entity.getF15() + "" +
                        " ," + entity.getF16() + "" +
                        " ," + entity.getF17() + "" +
                        " ," + entity.getF18() + "" +
                        " ," + entity.getF20() + "" +
                        " ," + entity.getF21() + "" +
                        " ," + entity.getF22() + "" +
                        " ,'" + entity.getF23() + "'" +
                        " ," + entity.getF24() + "" +
                        " ," + entity.getF25() + "" +
                        " ,'" + entity.getF26() + "'" +
                        " ," + entity.getF33() + "" +
                        " ," + entity.getF62() + "" +
                        " ," + entity.getF104() + "" +
                        " ," + entity.getF105() + "" +
                        " ," + entity.getF107() + "" +
                        " ,'" + entity.getF115() + "'" +
                        " ," + entity.getF124() + "" +
                        " ,'" + entity.getF128() + "'" +
                        " ,'" + entity.getF140() + "'" +
                        " ," + entity.getF141() + "" +
                        " ," + entity.getF136() + "" +
                        " ," + entity.getF152() + "" +
                        " ,'" + entity.getF207() + "'" +
                        " ,'" + entity.getF208() + "'" +
                        " ," + entity.getF209() + "" +
                        " ," + entity.getF222() + "" +
                        ");");
            }

        }
    }

    /**
     * 查询昨日主题排名
     */
    private static List<RankBizDataDiff> listRankStockByBiz(int pageSize, String biz) {
        //http://push2.eastmoney.com/api/qt/clist/get?cb=jQuery112307730222083783287_1617467610779&fid=f62&po=1&pz=50&pn=1&np=1&fltt=2&invt=2&ut=b2884a393a59ad64002292a3e90d46a5&fs=b%3ABK0891&fields=f12%2Cf14%2Cf2%2Cf3%2Cf62%2Cf184%2Cf66%2Cf69%2Cf72%2Cf75%2Cf78%2Cf81%2Cf84%2Cf87%2Cf204%2Cf205%2Cf124
        StringBuffer urlParam = new StringBuffer();
        String url = "http://push2.eastmoney.com/api/qt/clist/get?";
        urlParam.append("cb=jQuery112307730222083783287_1617467610779" +
                "&pn=1" +//页数
                "&pz=" + pageSize +//每页数量
                "&po=1" +//pageorder:页面排序：0-正序；1-倒序
                "&np=1" +
                "&ut=b2884a393a59ad64002292a3e90d46a5" +
                "&fltt=2" +//浮点数精度
                "&invt=3" +//显示格式：-；0.0
//                "&fid=f20" +//排序字段：f20:总市值
                "&fid=f3" +//排序字段：f3:涨跌幅
                "&fs=b:" + biz +
                //fields: f12,f14,f2,f3,f62,f184,f66,f69,f72,f75,f78,f81,f84,f87,f204,f205,f124
                "&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f30,f11,f62,f72,f75,f78,f81,f84,f87,f204,f205,f124,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222" +
                "");
        String rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);
        if (rs == null) {
            return null;
        }
        if (rs.startsWith("jQuery")) {
            rs = rs.substring(rs.indexOf("{"));
        }
        if (rs.endsWith(");")) {
            rs = rs.substring(0, rs.length() - 2);
        }

        JSONObject rsJsonObj = JSONObject.parseObject(rs);
//        System.out.println(rs);//返回结果
        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
        List<RankBizDataDiff> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankBizDataDiff.class);
//        for (RankBizDataDiff row : rankBizDataDiffList) {
//            System.out.println(JSON.toJSON(row));//每个行业一行数据
//        }
        return rankBizDataDiffList;
    }

}
