package ttjj.trade;

import ttjj.Dao.TradeDao;
import ttjj.Dao.impl.TradeDaoImpl;
import ttjj.dto.FundTrade;
import ttjj.dto.LsjzDataLsjz;
import ttjj.dto.LsjzPt;
import utils.Content;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * @author chenzhilong
 * @date 2020/8/4
 */
public class LsjjDemo {
    static String keyRsMax = "rsMax";
    static String keyRsMin = "rsMin";
    static String keyLastDwjz = "lastDwjz";

    public static void main(String[] args) {
        showJz("110011|易方达中小盘混合",60);

    }

    /**
     * 显示净值
     * @param codeStr
     * @param days
     */
    private static void showJz(String codeStr, int days) {
        //        String cookie = "FundTradeLoginCard=0; FundTradeLoginTab=0; __guid=234620763.2234479903904567600.1595670543743.7263; st_si=20949751584722; st_asi=delete; Fund_GiveLike=3d5694cbb8204c85aa021ee657963024; ASP.NET_SessionId=punsfbafnqwjwbkjsxjapjis; st_pvi=34528644972697; st_sp=2020-03-21%2009%3A52%3A13; st_inirUrl=https%3A%2F%2Flogin.1234567.com.cn%2Flogin; st_sn=8; st_psi=20201022000104610-119085303933-0701114944; cp_token=77ba1ae84a784497a8abb19e9f450cc0; FundTradeLoginUser=utbyatwV2Mbp3gbH4w9YJGlPDxV1IAFbIDKHHXn35v7s/AgkfCGY9zgpUIo5PacxEzQlGU+n; fund_trade_cn=u5lcTnbaitj6P9MMhE1bWAKst4Xz9dYUrw5QvipOc11fMqQ27qU+OpLOHy1EkB42drgVsNAN3AZFA1yXEGdfgRNFsLJ7QHBXDixOOxjQdw4M3RjMBGM=; fund_trade_name=uH/CPWbj0MxcuzPDQG9ZpscwUi+1JnKtsORuHJQDCNawPARjQU3IrQgDYi05PrdxrJ6YoaJQ; fund_trade_visitor=uFoHZF3YNMorTXDTRh9tGEQZMpf1737jTlUFH/vkn04eFA9t1UdBiXg84605IvWxm9CDKeqf; fund_trade_risk=uUl+d5w7QMPWa5HwNK9ousq0Jb01nQ/6PY4CH7hTf/rVzACWL/ic2HgNTgJY/vgx3oS6Zaei; fund_trade_gps=2; VipLevel=0; TradeLoginToken=79694dfaffa14e9bb39bfa0f81c6287a; UTOKEN=u5lcTnbaitj6P9MMhE1bWAKst4Xz9dYUrw5QvipOc11fMqQ27qU+OpLOHy1EkB42drgVsVAlaDv3FNTEfo0mg8EOUjm4cFeXiPx3x/+2jnvtzOvVbYM=; LToken=83b0722a5c7e43eab8360946d9ab4dd4; fund_trade_trackid=LGwq2OdJjSnc8WnN1j/kXO+FBqfNso8Wpmsm1YG1uOOu7nX4Cp7IZ76LgGTu+UXpiRnfVUhyDA6p6LXb04ssGw==; monitor_count=2";

        Map<String, String> pramaMap = new HashMap<String, String>();
        LocalDate lastMonth1 = LocalDate.now().minusDays(days);
        System.out.println(lastMonth1);
        System.out.println(lastMonth1.toString());

        pramaMap.put(Content.jjCode, codeStr.substring(0,codeStr.indexOf("|")));
        pramaMap.put(Content.startDate, lastMonth1.toString());
//        pramaMap.put(Content.endDate, "2020-04-13");
        pramaMap.put(Content.endDate, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));


        String lsjzUrl = "fundCode=" + pramaMap.get(Content.jjCode) + "&pageIndex=1&pageSize=100&startDate=" + pramaMap.get(Content.startDate) + "&endDate=" + pramaMap.get(Content.endDate) + "&_=1558194929451";
        byte[] bytes = "".getBytes();
        LsjzPt lsjzPt = HttpUtil.sendPostTtjjLsjj(lsjzUrl, bytes, pramaMap);
        List<LsjzDataLsjz> lsjzDataLsjzList = lsjzPt.getData().getLSJZList();
        if (lsjzDataLsjzList != null && lsjzDataLsjzList.size() > 0) {
            Collections.sort(lsjzDataLsjzList, new Comparator<LsjzDataLsjz>() {
                public int compare(LsjzDataLsjz o1, LsjzDataLsjz o2) {
                    return o1.getFSRQ().compareTo(o2.getFSRQ());
                }
            });
            int firstDay = 0;//第一天标志
            String lastDayNet = "0";//上一日净值
            Map<String, Double> rs = handlerMaxJz(lsjzDataLsjzList);
            Double maxJz = rs.get(keyRsMax);
            Double minJz = rs.get(keyRsMin);
            Double lastDwjz = rs.get(keyLastDwjz);
            System.out.println("最大净值：" + maxJz);
            System.out.println("最小净值：" + minJz);
            System.out.println("最新净值：" + lastDwjz);
            Double lvLastDwjzMax = lastDwjz / maxJz * 100;
            Double lvLastDwjzMin = (lastDwjz / minJz-1) * 100;
            System.out.println("当晚净值比最大率：" + lvLastDwjzMax);
            System.out.println("当晚净值比最小率：" + lvLastDwjzMin);
        }
    }

    /**
     * 计算最大净值
     *
     * @param lsjzDataLsjzList
     * @return
     */
    private static Map<String, Double> handlerMaxJz(List<LsjzDataLsjz> lsjzDataLsjzList) {
        Map<String, Double> rs = new HashMap<String, Double>();
        Double rsMax = 0.0;
        Double rsMin = 0.0;
        Double lastDwjz = 0.0;
        for (LsjzDataLsjz lsjzDataLsjz : lsjzDataLsjzList) {
//                    System.out.println(JSON.toJSONString(lsjzDataLsjz));
            String dwJz = lsjzDataLsjz.getDWJZ();
            String fsrq = lsjzDataLsjz.getFSRQ();
//            System.out.println("fsrq:" + fsrq + ",dwjzLong:" + dwJz);
            Double dwjzLong = Double.valueOf(dwJz);
            if (dwjzLong > rsMax) {
                rsMax = dwjzLong;
            }
            if (dwjzLong < rsMin || rsMin == 0.0) {
                rsMin = dwjzLong;
            }
            lastDwjz = dwjzLong;
        }
        rs.put(keyRsMax, rsMax);
        rs.put(keyRsMin, rsMin);
        rs.put(keyLastDwjz, lastDwjz);
        return rs;
    }


}
