package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import ttjj.dto.FundRank;
import utils.HttpUtil;

import java.util.ArrayList;
import java.util.List;

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
        listRank("", 20, "pg","r");
    }

    /**
     * 查询
     *  @param cookie cookie
     * @param ft     类型：pg-偏股；
     * @param sc 周期：r-日；z-周；
     */
    public static void listRank(String cookie, int count, String ft, String sc) {
        String url = "https://fundapi.eastmoney.com/fundtradenew.aspx?";
        if (StringUtils.isNotBlank(ft)) {
            url = url + "ft=" + ft;
        }
        if (StringUtils.isNotBlank(sc)) {
            url = url + "sc=" + sc;
        }
        url = url + "&sc=r&st=desc&pi=1&pn=100&cp=&ct=&cd=&ms=&fr=&plevel=&fst=&ftype=&fr1=&fl=0&isab=1";
        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("");
        System.out.println("请求url:" + url);
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
        int curNum = 1;
        for (int i = 0; i < klineList.size(); i++) {
            String objStr = klineList.get(i);
//            System.out.println("objStr:" + objStr);
            //161721|招商沪深300地产等权重指数|股票型|2021-02-25|0.8975|6.23|5.85|6.91|-1.98|-7.38|6.80|18.84|7.81|4.51|45.33|3|1|1|0.10|0||050,051,054|1|1|100元|1.00%|0.10%|0.10%|1
            String[] klineArray = objStr.split("\\|");
            FundRank fundRank = new FundRank();
            fundRank.setFundCode(klineArray[0]);
            fundRank.setFundInfo(klineArray[1]);
            fundRank.setBizTy(klineArray[2]);
            fundRank.setPeriodDate(klineArray[3]);
            fundRank.setNet(klineArray[4]);
            fundRank.setGrowth1(klineArray[5]);
            fundRank.setGrowth7(klineArray[6]);
            fundRank.setGrowth30(klineArray[7]);
            fundRank.setGrowth90(klineArray[8]);
            fundRank.setGrowth180(klineArray[9]);
            fundRank.setGrowth360(klineArray[10]);
            fundRank.setGrowth720(klineArray[11]);
            fundRank.setGrowth1080(klineArray[12]);
            fundRank.setGrowthCurYear(klineArray[13]);
            fundRank.setGrowthEstablish(klineArray[14]);
            System.out.print("" + curNum + ",");
            System.out.print("日增长率:" + fundRank.getGrowth1() + ",");
            System.out.print("近一周:" + fundRank.getGrowth7() + ",");
            System.out.print("近一月:" + fundRank.getGrowth30() + ",");
            System.out.print("近三月:" + fundRank.getGrowth90() + ",");
            System.out.print("近六月:" + fundRank.getGrowth180() + ",");
            System.out.print("近一年:" + fundRank.getGrowth360() + ",");
            System.out.print("近两年:" + fundRank.getGrowth720() + ",");
            System.out.print("近三年:" + fundRank.getGrowth1080() + ",");
            System.out.print("今年:" + fundRank.getGrowthCurYear() + ",");
            System.out.print("成立来:" + fundRank.getGrowthEstablish() + ",");
            System.out.print("代码:" + fundRank.getFundCode() + ",");
            System.out.print("名称:" + fundRank.getFundInfo() + ",\t");
            System.out.print("类型:" + fundRank.getBizTy() + ",");
            System.out.print("日期:" + fundRank.getPeriodDate() + ",");
            System.out.print("净值:" + fundRank.getNet() + ",");
            System.out.println();
            if (curNum == count) {
                break;
            }
            curNum++;
        }
    }


}
