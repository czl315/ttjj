package ttjj.Dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ttjj.Dao.TradeDao;
import ttjj.Dao.TradeStockDao;
import ttjj.dto.FundTrade;
import ttjj.dto.LsjzDataLsjz;
import ttjj.dto.StockTrade;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 交易服务
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
        List<StockTrade> rs = new TradeStockDaoImpl().findMyStockTrade(cookie, fundCode, startDate, endDate, busType, "1",validatekey);
//        System.out.println("findMyStockTrade:"+JSON.toJSON(rs));


    }


    /**
     * 查询
     *
     * @param cookie
     */
    public List<StockTrade> findMyStockTrade(String cookie, String fundCode, String startDate, String endDate, String busType, String pageIndex, String validatekey) {
        String url = "https://jywg.18.cn/Search/GetFundsFlow?validatekey="+validatekey;
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

    /**
     * 显示插入数据库语句
     *
     * @param fundTrade
     */
    private static void showInsertDb(FundTrade fundTrade) {
        //打印-
        System.out.println("INSERT INTO `bank19`.`ol_stock_trade`(" +
                " `FD_ID`, `FD_INFO`, `TYPE`, `TRADE_TIME`, `ORDER_STATUS`, " +
                " `CONFIRM_SHARE`, `CONFIRM_NET`, `ORDER_AMT`, `STATUS`, " +
                " `ORDER_CODE`, `CONFIRM_AMT`, `REDEM_AMT`," +
                " `EARN_AMT`, `CONFIRM_NET_DATA`, `SERVER_CHARGE`, " +
                " `REDEM_STATUS`, `REDEM_SHARE`, `REDEM_TIME`, " +
                " `SOURCE`, `FD_CODE`, `CREATE_TIME`, `UPDATE_TIME`" +
                ") VALUES (" +
                " '', '" + fundTrade.getFundInfo() + "', '" + fundTrade.getOrderStatus() + "', '" + fundTrade.getTradeTime() + "', '" + fundTrade.getOrderStatus() + "'" +
                ", " + fundTrade.getConfirmShare() + ", " + fundTrade.getConfirmNet() + ", " + fundTrade.getOrderAmt() + ", " + "'确认成功', " +
                "  '', " + fundTrade.getOrderAmt() + ", 0, " +
                " 0, '" + fundTrade.getConfirmNetData() + "', " + fundTrade.getServerCharge() + ", " +
                " '0', 0,  '3000-01-01 00:00:00', " +
                " '3', '', now(), now()" +
                ");");
    }
}
