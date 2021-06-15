package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.TradeStockDao;
import ttjj.dao.impl.TradeStockDaoImpl;
import ttjj.dto.FundTrade;
import ttjj.dto.LsjzDataLsjz;
import ttjj.dto.StockTrade;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 我的资产-股票-东财
 *
 * @author Administrator
 * @date 2021-03-18 19:05
 */
public class AssetStockDemo {

    public static void main(String[] args) {
        String cookie = "__guid=260925462.4161440383634452500.1615302736826.6602; eastmoney_txzq_zjzh=NTQwODIwMTc0NTY5fA%3D%3D; Yybdm=5408; Uid=fNUE23lwQOlyHFRjGcQYdA%3d%3d; Khmc=%e9%99%88%e5%bf%97%e9%be%99; mobileimei=5dfd1623-04dd-44d7-b4d8-e156e5265178; Uuid=52c74f76dfa84ff68296d0696de5f57f; monitor_count=46";
        String validatekey = "5f090882-d116-4f2e-a1b1-9b40afd82512";

//        //显示插入数据库语句
        queryAssetByDfcfStock(cookie, validatekey);
    }

    /**
     * 查询东方财富股票账户-显示插入数据库语句
     *
     * @param
     */
    private static void queryAssetByDfcfStock(String cookie, String validatekey) {
//        String url = "https://jywg.18.cn/Com/queryAssetAndPositionV1?validatekey=" + validatekey;
        String url = "https://jywg.18.cn/AccountAnalyze/Asset/GetHold?v=1617202981877";

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("moneyType=").append("RMB");

//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
        String rs = HttpUtil.sendPost(url, urlParam.toString(), cookie);
//        System.out.println("queryAssetByDfcfStock:" + rs);

        String fundMktVal = "";//证券市值
        String fundAvl = "";//可用资金
        String totalAmt = "";//合计资产金额
        String dayProfit = "";//当日盈亏
        String dayProfitRt = "";//当日盈亏收益率
        JSONObject rsJson = JSON.parseObject(rs);
        JSONArray rsArray = rsJson.getJSONArray("ResultObj");
        for (int i = 0; i < rsArray.size(); i++) {
            JSONObject myStock = (JSONObject) (rsArray.get(i));
//            System.out.println("ResultObj:"+JSON.toJSONString(myStock));
            fundMktVal = myStock.getString("FundMktVal");//证券市值
            fundAvl = myStock.getString("FundAvl");//可用资金
            BigDecimal totalAmtBig = new BigDecimal(fundMktVal).add(new BigDecimal(fundAvl));
            totalAmt = totalAmtBig.toString();
            dayProfit =myStock.getString("DayProfit");
            dayProfitRt = new BigDecimal(dayProfit).divide(new BigDecimal(fundMktVal), 6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).divide(new BigDecimal("1"),4,BigDecimal.ROUND_HALF_UP).toString();//当日盈亏收益率
        }

        String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println("UPDATE `fupan` SET `amt_dfcf`='" + totalAmt + "', `hold_st`='" + fundMktVal + "', `earn_st`='" + dayProfit + "', `rt_st`='" + dayProfitRt + "' WHERE (`CODE`='" + curDate + "') AND fupan.period='1'" + " AND fupan.TYPE=1;");


//        String rsDate = rsArray.getString("Data");
//        System.out.println(rsDate);
    }


}
