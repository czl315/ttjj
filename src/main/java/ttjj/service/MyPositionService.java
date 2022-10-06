package ttjj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ttjj.dao.FupanPositionDao;
import ttjj.db.AssetPositionDb;
import ttjj.db.Fupan;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我的仓位
 *
 * @author Administrator
 * @date 2022-09-05 00:02
 */
public class MyPositionService {
    /**
     * 我的仓位
     * @param date 日期
     * @return 我的持仓
     */
    public static Map<String, String> listMyPositionByDate(String date) {
        List<AssetPositionDb> rs = FupanPositionDao.listMyPositionByDate(date);//我的持仓
        Map<String, String> mapMyPostion = new HashMap<>();
        for (AssetPositionDb assetPositionDb : rs) {
            mapMyPostion.put(assetPositionDb.getZqdm(), assetPositionDb.getZqmc());
        }
        return mapMyPostion;
    }

    /**
     * 查询我的仓位：从东方财富股票账户
     *
     * @param
     * @param dateType
     */
    public static Fupan queryAssetByDfcfStock(String cookie, String dateType) {
//        String url = "https://jywg.18.cn/Com/queryAssetAndPositionV1?validatekey=" + validatekey;
        long curTime = System.currentTimeMillis();
        String url = "https://jywg.18.cn/AccountAnalyze/Asset/GetHold?v=" + curTime;

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("moneyType=").append("RMB");

//        System.out.println("请求url:" + url + JSON.toJSONString(urlParam));
        String rs = HttpUtil.sendPost(url, urlParam.toString(), cookie);
//        System.out.println("queryAssetByDfcfStock:" + rs);


        //      "FundBal": "6597.17",   可取资金        6597.17
        //      "FundFrz": "25506.39",  冻结资金        25506.39
        //      "MoneyType": "0",
        //      "MaxDraw": null,

        String fundMktVal = "";//证券市值
        String fundAvl = "";//可用资金
        String totalAmt = "";//合计资产金额
        String dayProfit = "";//当日盈亏
        String dayProfitRt = "";//当日盈亏收益率
        JSONObject rsJson = null;
        try{
            rsJson = JSON.parseObject(rs);
        }catch (Exception e){
            e.printStackTrace();
        }

        JSONArray rsArray = rsJson.getJSONArray("ResultObj");
        for (int i = 0; i < rsArray.size(); i++) {
            JSONObject myStock = (JSONObject) (rsArray.get(i));
//            System.out.println("ResultObj:"+JSON.toJSONString(myStock));
            fundMktVal = myStock.getString("FundMktVal");//总市值 "FundMktVal": "302225.10",
            fundAvl = myStock.getString("FundAvl");//可用资金   "FundAvl": "45506.39",
            BigDecimal fundAll = new BigDecimal(myStock.getString("FundAll"));//总资产   "FundAll": "398732.090",    BigDecimal totalAmtBig = new BigDecimal(fundMktVal).add(new BigDecimal(fundAvl));
            BigDecimal otcAsset = new BigDecimal(myStock.getString("OtcAsset"));//      "OtcAsset": "0.00", 场外资产
            totalAmt = fundAll.add(otcAsset).toString();//总资产 = 场内资产+场外资产
            dayProfit = myStock.getString("DayProfit");//      "DayProfit": "2037.00", 当日参考盈亏    2037.00
            if (fundMktVal == null || new BigDecimal(fundMktVal).compareTo(new BigDecimal("0")) == 0 || dayProfit == null) {
                dayProfitRt = "0";
            } else {
                dayProfitRt = new BigDecimal(dayProfit).divide(new BigDecimal(fundMktVal), 6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).divide(new BigDecimal("1"), 4, BigDecimal.ROUND_HALF_UP).toString();//当日盈亏收益率
            }
        }

        String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println("UPDATE `fupan` SET `amt_dfcf`='" + totalAmt + "', `hold_st`='" + fundMktVal + "', `earn_st`='" + dayProfit + "', `rt_st`='" + dayProfitRt + "' WHERE (`CODE`='" + curDate + "') AND fupan.period='1'" + " AND fupan.TYPE=1;");

        Fupan fupanRs = new Fupan();
        //where
        fupanRs.setCode(curDate);
        fupanRs.setPeriod(dateType);
        fupanRs.setType("1");//实际
        //setValue
        fupanRs.setAmt_dfcf(totalAmt);
        fupanRs.setHold_st(fundMktVal);
        fupanRs.setEarn_st(dayProfit);
        fupanRs.setRt_st(dayProfitRt);
        return fupanRs;

    }
}
