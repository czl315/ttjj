package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import ttjj.dto.FundRank;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基金排行
 *
 * @author chenzhilong9
 * @date 2021-02-26 10:08
 */
public class FundRankDemo {
    public static void main(String[] args) {
        /**
         * 列表查询-排行榜
         */
//        listRank("", 50, "pg","ln");//成立
//        listRank("", 100, "pg", "jn");
//        //股票-今年
//        listRank("", 100, "gp", "jn");
//        //混合-今年
//        listRank("", 100, "hh", "jn");
        //指数-今年
        listRank("", 100, "zs", "jn");
        //qdii-今年
        listRank("", 100, "qdii", "jn");
    }

    /**
     * 查询
     *
     * @param cookie cookie
     * @param ft     类型：pg-偏股；
     * @param sc     周期：r-日；z-周；y-月；3y-3月；6y-6月；1n-1年；jn-今年；ln-成立；
     */
    public static void listRank(String cookie, int count, String ft, String sc) {
        String url = "https://fundapi.eastmoney.com/fundtradenew.aspx?";
        if (StringUtils.isNotBlank(ft)) {
            url = url + "ft=" + ft;
        }
        if (StringUtils.isNotBlank(sc)) {
            url = url + "&sc=" + sc;
        }
        url = url + "&st=desc&pi=1&pn=100&cp=&ct=&cd=&ms=&fr=&plevel=&fst=&ftype=&fr1=&fl=0&isab=1";
        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("");
//        System.out.println("请求url:" + url);
        String rs = HttpUtil.sendGet(url, urlParam.toString(), cookie);
//        System.out.println("http-rs:"+rs);
        //返回数据转换为json格式
        String rsJson = rs.substring(rs.indexOf("[\""), rs.indexOf("\"]") + 2);
//        System.out.println("http-rs-json:"+rsJson);

        List<String> klineList = new ArrayList<String>();
        JSONArray rankArray = JSON.parseArray(rsJson);
        for (Object kline : rankArray) {
            String klineStr = (String) kline;
            klineList.add(klineStr);
        }

        //
        List<FundRank> fundRankList = new ArrayList<>();
        int curNum = 1;
        for (int i = 0; i < klineList.size(); i++) {
            String objStr = klineList.get(i);
//            System.out.println("objStr:" + objStr);
            //161721|招商沪深300地产等权重指数|股票型|2021-02-25|0.8975|6.23|5.85|6.91|-1.98|-7.38|6.80|18.84|7.81|4.51|45.33|3|1|1|0.10|0||050,051,054|1|1|100元|1.00%|0.10%|0.10%|1
            String[] klineArray = objStr.split("\\|");
            FundRank fundRank = new FundRank();
            fundRank.setNum(curNum);
            fundRank.setPeriodTy(sc);
            fundRank.setFundCode(klineArray[0]);
            fundRank.setFundInfo(klineArray[1]);
            fundRank.setBizTy(ft);
            fundRank.setPeriodDate(klineArray[3]);
            fundRank.setNet(klineArray[4]);
            fundRank.setGrowth1(StringUtils.isNotBlank(klineArray[5]) ? new BigDecimal(klineArray[5]) : null);
            fundRank.setGrowth7(StringUtils.isNotBlank(klineArray[6]) ? new BigDecimal(klineArray[6]) : null);
            fundRank.setGrowth30(StringUtils.isNotBlank(klineArray[7]) ? new BigDecimal(klineArray[7]) : null);
            fundRank.setGrowth90(StringUtils.isNotBlank(klineArray[8]) ? new BigDecimal(klineArray[8]) : null);
            fundRank.setGrowth180(StringUtils.isNotBlank(klineArray[9]) ? new BigDecimal(klineArray[9]) : null);
            fundRank.setGrowth360(StringUtils.isNotBlank(klineArray[10]) ? new BigDecimal(klineArray[10]) : null);
            fundRank.setGrowth720(StringUtils.isNotBlank(klineArray[11]) ? new BigDecimal(klineArray[11]) : null);
            fundRank.setGrowth1080(StringUtils.isNotBlank(klineArray[12]) ? new BigDecimal(klineArray[12]) : null);
            fundRank.setGrowthCurYear(StringUtils.isNotBlank(klineArray[13]) ? new BigDecimal(klineArray[13]) : null);
            fundRank.setGrowthEstablish(StringUtils.isNotBlank(klineArray[14]) ? new BigDecimal(klineArray[14].replace(",","")) : null);
//            if(curNum == 90){
//                System.out.println("");
//            }

            fundRankList.add(fundRank);

//            System.out.print("" + fundRank.getNum() + ",\t");
//            System.out.print("日增:" + fundRank.getGrowth1() + ",\t");
//            System.out.print("近一周:" + fundRank.getGrowth7() + ",\t");
//            System.out.print("近一月:" + fundRank.getGrowth30() + ",\t");
//            System.out.print("近三月:" + fundRank.getGrowth90() + ",");
//            System.out.print("近六月:" + fundRank.getGrowth180() + ",");
//            System.out.print("近一年:" + fundRank.getGrowth360() + ",");
////            System.out.print("近两年:" + fundRank.getGrowth720() + ",");
////            System.out.print("近三年:" + fundRank.getGrowth1080() + ",");
//            System.out.print("今年:" + fundRank.getGrowthCurYear() + ",");
//            System.out.print("成立来:" + fundRank.getGrowthEstablish() + ",");
//            System.out.print("代码:" + fundRank.getFundCode() + ",");
//            System.out.print("名称:" + fundRank.getFundInfo() + ",\t");
//            System.out.print("类型:" + fundRank.getBizTy() + ",");
////            System.out.print("日期:" + fundRank.getPeriodDate() + ",");
//            System.out.print("净值:" + fundRank.getNet() + ",");
////            System.out.print("周期类型:" + fundRank.getPeriodTy() + ",");
//            System.out.println();

            //显示数量限定
            if (curNum == count) {
                break;
            }
            curNum++;
        }

        List<FundRank> filterSortedRs = fundRankList.stream().filter(e -> e != null).sorted(Comparator.comparing(FundRank::getNum)).collect(Collectors.toList());
        filterSortedRs.forEach(fundRank -> {
//            System.out.print("" + fundRank.getNum() + ",\t");
//            System.out.print("日增:" + fundRank.getGrowth1() + ",\t");
//            System.out.print("近一周:" + fundRank.getGrowth7() + ",\t");
//            System.out.print("近一月:" + fundRank.getGrowth30() + ",\t");
//            System.out.print("近三月:" + fundRank.getGrowth90() + ",");
//            System.out.print("近六月:" + fundRank.getGrowth180() + ",");
//            System.out.print("近一年:" + fundRank.getGrowth360() + ",");
////            System.out.print("近两年:" + fundRank.getGrowth720() + ",");
////            System.out.print("近三年:" + fundRank.getGrowth1080() + ",");
//            System.out.print("今年:" + fundRank.getGrowthCurYear() + ",");
//            System.out.print("成立来:" + fundRank.getGrowthEstablish() + ",");
//            System.out.print("代码:" + fundRank.getFundCode() + ",");
//            System.out.print("名称:" + fundRank.getFundInfo() + ",\t");
//            System.out.print("类型:" + fundRank.getBizTy() + ",");
////            System.out.print("日期:" + fundRank.getPeriodDate() + ",");
//            System.out.print("净值:" + fundRank.getNet() + ",");
////            System.out.print("周期类型:" + fundRank.getPeriodTy() + ",");
//            System.out.println();

            showInsertDb(fundRank);
        });
    }

    private static void showInsertDb(FundRank fundRank) {
        //显示插入数据库语句
        System.out.println("INSERT INTO `ol_fund_rank` (`num`, `fundCode`, `fundInfo`, `bizTy`, `periodDate`, `periodTy`," +
                "`net`, " +
                "`growth1`, `growth7`, `growth30`, `growth90`, `growth180`, " +
                "`growth360`, `growth720`, `growth1080`, `growthCurYear`, `growthEstablish`) " +
                "VALUES (" + fundRank.getNum() + ", '" + fundRank.getFundCode() + "', '" + fundRank.getFundInfo() + "', '" + fundRank.getBizTy() + "', '" + fundRank.getPeriodDate() + "', '" + fundRank.getPeriodTy() + "', " +
                "'" + fundRank.getNet() + "'" +
                "," + (fundRank.getGrowth1() != null ? "'" + fundRank.getGrowth1() + "'" : null) +
                "," + (fundRank.getGrowth7() != null ? "'" + fundRank.getGrowth7() + "'" : null) +
                "," + (fundRank.getGrowth30() != null ? "'" + fundRank.getGrowth30() + "'" : null) +
                "," + (fundRank.getGrowth90() != null ? "'" + fundRank.getGrowth90() + "'" : null) +
                "," + (fundRank.getGrowth180() != null ? "'" + fundRank.getGrowth180() + "'" : null) +
                "," + (fundRank.getGrowth360() != null ? "'" + fundRank.getGrowth360() + "'" : null) +
                "," + (fundRank.getGrowth720() != null ? "'" + fundRank.getGrowth720() + "'" : null) +
                "," + (fundRank.getGrowth1080() != null ? "'" + fundRank.getGrowth1080() + "'" : null) +
                "," + (fundRank.getGrowthCurYear() != null ? "'" + fundRank.getGrowthCurYear() + "'" : null) +
                "," + (fundRank.getGrowthEstablish() != null ? "'" + fundRank.getGrowthEstablish() + "'" : null) +
                ");");
    }


}
