package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dto.LsjzDataLsjz;
import dto.LsjzPt;

import java.util.*;

/**
 * @author chenzhilong
 * @date
 */
public class LsjzUrl {
    public static void main(String[] args) {
//        byte[] bytes = "callback=jQuery183018519977574130597_1558194911277&fundCode=164906&pageIndex=1&pageSize=20&startDate=2020-04-01&endDate=2020-04-31&_=1558194929451".getBytes();
        byte[] bytes ="".getBytes();
        Map<String, String> pramaMap = new HashMap<String, String>();
        pramaMap.put(Content.jjCode, "000656");
        pramaMap.put(Content.startDate, "2020-04-01");
        pramaMap.put(Content.endDate, "2020-04-05");
        pramaMap.put(Content.jjName, "前海开源沪深300指数 (000656)");
        pramaMap.put(Content.canShare, "388.34");
        pramaMap.put(Content.BUY_COST, "500");
        pramaMap.put(Content.FIRST_NET_DATA, "2020-03-13");
        pramaMap.put(Content.TRADE_ID, "50");
        pramaMap.put(Content.FD_ID, "30");
        pramaMap.put(Content.SOURCE, "3");

        String lsjzUrl = "fundCode=" + pramaMap.get(Content.jjCode) + "&pageIndex=1&pageSize=100&startDate=" + pramaMap.get(Content.startDate) + "&endDate=" + pramaMap.get(Content.endDate) + "&_=1558194929451";
        HttpUtil.sendPostTtjj(lsjzUrl, bytes, pramaMap);

    }
}
