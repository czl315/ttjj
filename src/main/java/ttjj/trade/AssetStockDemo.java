package ttjj.trade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.Dao.TradeStockDao;
import ttjj.Dao.impl.TradeStockDaoImpl;
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
        String cookie = "__guid=260925462.4161440383634452500.1615302736826.6602; eastmoney_txzq_zjzh=NTQwODIwMTc0NTY5fA%3D%3D; Yybdm=5408; Uid=fNUE23lwQOlyHFRjGcQYdA%3d%3d; Khmc=%e9%99%88%e5%bf%97%e9%be%99; mobileimei=d217008d-d490-4195-92c7-1e824e512880; Uuid=7bf8007afc0f460da36a6c1146cfb905; monitor_count=67";
        String validatekey = "5f090882-d116-4f2e-a1b1-9b40afd82512";

//        //显示插入数据库语句
        String bizTypeBuy = "1";//0-全部;1-申购;2-卖出;
        String insertStartDate = "2021-03-17";//查询新增交易的开始时间
        String insertEndDate = "2021-03-31";//查询新增交易的结束时间
        queryAssetByDfcf(cookie, validatekey);
    }

    /**
     * 显示插入数据库语句
     *
     * @param
     */
    private static void queryAssetByDfcf(String cookie, String validatekey) {
        String url = "https://jywg.18.cn/Com/queryAssetAndPositionV1?validatekey=" + validatekey;
        StringBuffer urlParam = new StringBuffer();
        urlParam.append("moneyType=").append("RMB");

//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
        String rs = HttpUtil.sendPost(url, urlParam.toString(), cookie);
        System.out.println("queryAssetByDfcf:" + rs);

        JSONObject rsJson = JSON.parseObject(rs);
        String rsDate = rsJson.getString("Data");
//        System.out.println(rsDate);
    }


}
