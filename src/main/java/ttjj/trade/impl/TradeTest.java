package ttjj.trade.impl;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TradeTest {
    public static void main(String[] args) {
        String cookie = "cp_token=7f8f0f3224b24194a70d453b65f6338f; st_si=21393218770879; st_pvi=97169704610474; st_sp=2020-06-02%2010%3A08%3A40; st_inirUrl=https%3A%2F%2Ffund.eastmoney.com%2F; st_sn=1; st_psi=20200624102608139-119085303933-2162940118; st_asi=delete; fund_trade_cn=Yc77PDg/+Wj5iTXCqVAA6bxp4OMd9jjngcRyW/+F8i0zV9OOuhjeqbjRblMfwOtJqTWURKkvVfQwtZQrAcFblP0Pn2jNxqPztf1WgiztdNFUZXlCcfg=; fund_trade_name=YgcrYk+jAXjrUklMAm9Ff+xrg6o0g72mSG2Dbf5VfgPFykcfzedN8rlb/M0IYR518y54T2vu; fund_trade_visitor=YFcgjlp0dXH+3UcNZq9Pg39qEuI0Mw/G0/76bNepPfBLykIDDfITzgl7fx90upJ1kXFLlrSp; fund_trade_risk=YoM9MwKvdXlp7EYt7e91M9qcfW60tYQw+FHxbltK8hCCUkRgD1R2p5l6mLEYSuJ1LTTd1wMx; fund_trade_gps=2; VipLevel=0; TradeLoginToken=00f403747d9847ef9017e344a947d6ec; UTOKEN=Yc77PDg/+Wj5iTXCqVAA6bxp4OMd9jjngcRyW/+F8i0zV9OOuhjeqbjRblMfwOtJqTWURUkRGp2JtiCSwQlllLA5W2+ROreNxb1XjvLpcHc2z4/eBGg=; LToken=d7f8eb0ea7864795879a3d666fec23ab; fund_trade_trackid=gXoYRgS4Y2W/A9QxqdSXCXc6ZguMOE0x0pGC2ZrZhW6NDXcn18gz+y0p+QpC6G9BZ1MHZAVwRAPu+khVKNw+eg==";

        List<Map<String,String>> myHoldFundList = new ArrayList<Map<String,String>>();

        List<String> fundCodeList = new ArrayList<String>();
        fundCodeList.add("000656");
//        fundCodeList.add("001027");
//        fundCodeList.add("001279");
//        fundCodeList.add("001875");
//        fundCodeList.add("001915");


        for (String fundCode : fundCodeList) {
            Map<String,String> tradeRs=  new TradeServiceImpl().findFundTrade(fundCode, cookie );
            myHoldFundList.add(tradeRs);
        }
        for (Map<String, String> tradeRs : myHoldFundList) {
            if(tradeRs!=null){
                System.out.println("tradeRs:"+JSON.toJSONString(tradeRs));
//            System.out.println(tradeRs.get(SHARE_SUM));
//            System.out.println(tradeRs.get(AMT_SUM));
//            System.out.println(tradeRs.get(SERVER_CHARGE));
//            System.out.println(tradeRs.get(FUND_NAME));
//            System.out.println(tradeRs.get(FUND_CODE));
            }
        }




    }
}
