package ttjj.trade.impl;

import com.alibaba.fastjson.JSON;

import java.util.Map;

public class TradeTest {
    public static void main(String[] args) {
        Map<String,String> tradeRs=  new TradeServiceImpl().findFundTrade("000656");
//        for (String trade : tradeRs.keySet()) {
//            System.out.println(tradeRs.get(trade));
//        }
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
