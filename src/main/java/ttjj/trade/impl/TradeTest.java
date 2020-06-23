package ttjj.trade.impl;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TradeTest {
    public static void main(String[] args) {
        List<Map<String,String>> myHoldFundList = new ArrayList<Map<String,String>>();

        List<String> fundCodeList = new ArrayList<String>();
        fundCodeList.add("000656");
        fundCodeList.add("001027");
        fundCodeList.add("001279");
        fundCodeList.add("001875");
        fundCodeList.add("001915");


        for (String fundCode : fundCodeList) {
            Map<String,String> tradeRs=  new TradeServiceImpl().findFundTrade(fundCode);
            myHoldFundList.add(tradeRs);
        }
        for (Map<String, String> tradeRs : myHoldFundList) {
            if(tradeRs!=null){
                System.out.println(JSON.toJSONString(tradeRs));
//            System.out.println(tradeRs.get(SHARE_SUM));
//            System.out.println(tradeRs.get(AMT_SUM));
//            System.out.println(tradeRs.get(SERVER_CHARGE));
//            System.out.println(tradeRs.get(FUND_NAME));
//            System.out.println(tradeRs.get(FUND_CODE));
            }
        }




    }
}
