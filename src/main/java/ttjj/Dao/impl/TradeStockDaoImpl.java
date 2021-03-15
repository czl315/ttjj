package ttjj.Dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ttjj.Dao.TradeDao;
import ttjj.Dao.TradeStockDao;
import ttjj.dto.FundTrade;
import ttjj.dto.LsjzDataLsjz;
import ttjj.dto.StockTrade;
import ttjj.dto.StockTradeToday;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 交易服务
 * @author Administrator
 * @date 2021-03-15 21:39
 */
public class TradeStockDaoImpl implements TradeStockDao {
    //    private final static Logger logger = Logger.getLogger(TradeDaoImpl.class);

    /**
     * @param args args
     */
    public static void main(String[] args) {
        String cookie = "__guid=260925462.4161440383634452500.1615302736826.6602; Yybdm=5408; Uid=fNUE23lwQOlyHFRjGcQYdA%3d%3d; Khmc=%e9%99%88%e5%bf%97%e9%be%99; eastmoney_txzq_zjzh=NTQwODIwMTc0NTY5fA%3D%3D; mobileimei=a1acfe85-1eca-4664-81fa-1f8959c9d8ce; Uuid=dc064ff54ccf48989c8d092a31c024c8; monitor_count=25";
        String validatekey = "c2c1e931-cb40-44d5-9106-4c66362fe9ef";
//        System.out.println("查询开始：");=
        String fundCode = "";
//        String fundCode = "002207";
        String startDate = "20210101";
        String endDate = "20210228";
//        String busType = "0";//0-全部;1-申购;2-卖出;
        String busType = "1";//0-全部;1-申购;2-卖出;
//        String busType = "2";//0-全部;1-申购;2-赎回;
        List<StockTrade> rs = new TradeStockDaoImpl().findMyStockTrade(cookie, startDate, endDate,validatekey);
//        System.out.println("findMyStockTrade:"+JSON.toJSON(rs));


    }


    /**
     * 查询
     *
     * @param cookie
     */
    public List<StockTrade> findMyStockTrade(String cookie, String startDate, String endDate, String validatekey) {
        String url = "https://jywg.18.cn/Search/GetHisDealData?validatekey="+validatekey;
        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("st=2021-03-04&et=2021-03-11&qqhs=20&dwc=");
        urlParam.append("st=").append(startDate);
        urlParam.append("&et=").append(endDate);
        urlParam.append("&qqhs=").append("1000");
        urlParam.append("&dwc=" + "");

        //        System.out.println(rs);
//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
        String rs = HttpUtil.sendPost(url, urlParam.toString(), cookie);
//        System.out.println("findMyStockTrade:"+rs);

        JSONObject findMyStockTradeJo = JSON.parseObject(rs);
        String rsDate = findMyStockTradeJo.getString("Data");
//        System.out.println(rsDate);
        List<StockTrade> array = JSONObject.parseArray(rsDate,StockTrade.class);

//        System.out.println("tradeList:" + JSON.toJSONString(array));
        return array;
    }

}
