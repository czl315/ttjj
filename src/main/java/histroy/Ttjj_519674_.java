package histroy;

import utils.Content;
import utils.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Ttjj_519674_ {

    public static void main(String[] args) {
        String name = "银河创新成长混合(519674)";
        String canShare = "20.01";
        String BUY_COST = "100";
        String FIRST_NET_DATA = "2020-03-23";
        String TRADE_ID = "00";
        String FD_ID = "00";
        String SOURCE = "3";

        Map<String, String> pramaMap = new HashMap<String, String>();
        pramaMap.put(Content.jjCode, "519674");
        pramaMap.put(Content.startDate, "2020-03-20");
//        pramaMap.put(Content.endDate, "2020-04-10");
        pramaMap.put(Content.endDate, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        pramaMap.put(Content.jjName, name);
        pramaMap.put(Content.canShare, canShare);
        pramaMap.put(Content.BUY_COST, BUY_COST);
        pramaMap.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        pramaMap.put(Content.TRADE_ID, TRADE_ID);
        pramaMap.put(Content.FD_ID, FD_ID);
        pramaMap.put(Content.SOURCE, SOURCE);

        String lsjzUrl = "fundCode=" + pramaMap.get(Content.jjCode) + "&pageIndex=1&pageSize=100&startDate=" + pramaMap.get(Content.startDate) + "&endDate=" + pramaMap.get(Content.endDate) + "&_=1558194929451";
        byte[] bytes ="".getBytes();
        HttpUtil.sendPostTtjj(lsjzUrl, bytes, pramaMap);
    }

}
