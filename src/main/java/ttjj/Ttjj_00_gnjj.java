package ttjj;

import utils.Content;
import utils.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.*;

public class Ttjj_00_gnjj {
    /**
     * TTJJ
     */
    static String SOURCE_TTJJ = "3";

    public static void main(String[] args) {
        List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
        String endDateToday = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        paramList.add(handlertTtjj("2020-03-12", "2020-04-19", "000656", "000656|前海开源沪深300指数",
                "388.34", "500", "2020-03-13", "50", "30"));

//        paramList.add(handler001027("2020-03-30", "2020-04-19", "001027", "001027|前海开源中证大农业指数增强",
//                "96.88", "500", "2020-03-31", "00", "00"));

//        paramList.add(handler001279("2020-03-20", "2020-04-19", "001279", "001279|中海积极增利混合",
//                "794.35", "1000", "2020-03-23", "00", "00"));

//        paramList.add(handler001579("2020-03-30", "2020-04-19", "001579", "001579|国泰大农业股票",
//                "55.44", "100", "2020-03-31", "00", "00"));

//        paramList.add(handler001986("2020-03-25", "2020-04-19", "001986", "001986|前海开源人工智能主题混合",
//                "79.44", "100", "2020-03-26", "00", "00"));
//
//        paramList.add(handler002560("2020-03-27", "2020-04-19", "002560", "002560|诺安和鑫灵活配置混合",
//                "89.62", "100", "2020-03-30", "00", "00"));
//
//        paramList.add(handler003095("2020-04-10", "2020-04-19", "003095", "003095|中欧医疗健康混合A",
//                "235.38", "500", "2020-04-13", "00", "00"));
//
//        paramList.add(handler003096("2020-04-13", "2020-04-19", "003096", "003096|中欧医疗健康混合C",
//                "234.74", "500", "2020-04-14", "00", "00"));
//
//        paramList.add(handler003547("2020-03-07", "2020-04-19", "003547", "003547|鹏华丰禄债券",
//                "925.79", "1000", "2020-03-10", "51", "27"));

//        paramList.add(handler003986("2020-03-10", "2020-03-16", "003986", "003986|申万菱信中证500指数优选增强A",
//                "74.63", "100", "2020-03-11","00","00"));
//        paramList.add(handler003986("2020-03-16", "2020-04-19", "003986", "003986|申万菱信中证500指数优选增强A",
//                "235.78", "300", "2020-03-11","00","00"));

//        paramList.add(handler004857("2020-04-15", "2020-04-19", "004857", "004857|广发中证全指建筑材料指数C",
//                "422.65", "500", "2020-04-16", "00", "00"));

//        paramList.add(handler005224("2020-03-26", "2020-04-15", "005224", "005224|广发中证基建工程指数C",
//                "707.11", "500", "2020-03-27", "00", "00"));
//        paramList.add(handler005224("2020-04-15", "2020-04-16", "005224", "005224|广发中证基建工程指数C",
//                "2095.23", "1500", "2020-03-27", "00", "00"));
//        paramList.add(handler005224("2020-04-16", "2020-04-19", "005224", "005224|广发中证基建工程指数C",
//                "3484.7", "2500", "2020-03-27", "00", "00"));
        
//        paramList.add(handler005620("2020-04-03", "2020-04-19", "005620", "005620|中欧品质消费股票A",
//                "62.48", "100", "2020-04-07", "00", "00"));

//        paramList.add(handler005911("2020-03-27", "2020-04-19", "005911", "005911|广发双擎升级混合",
//                "43.23", "100", "2020-03-30", "00", "00"));

//        paramList.add(handler005967("2020-02-28", "2020-04-19", "005967", "005967|鹏华创新驱动混合",
//                "769.5", "1000", "2020-03-02", "00", "00"));

//        paramList.add(handler006021("2020-03-16", "2020-04-19", "006021", "006021|广发沪深300指数增强C",
//                "168.52", "200", "2020-03-17", "00", "00"));

//        paramList.add(handler007136("2020-03-10", "2020-04-19", "007136", "007136|广发中证100ETF联接C",
//                "95.38", "100", "2020-03-11", "00", "00"));

//        paramList.add(handler007380("2020-03-10", "2020-04-19", "007380", "007380|易方达上证50ETF联接基金C",
//                "96.09", "100", "2020-03-11", "00", "00"));

//        paramList.add(handler007405("2020-03-12", "2020-04-19", "007405", "007405|华宝中证100指数C",
//                "67.29", "100", "2020-03-13", "00", "00"));

//        paramList.add(handler008086("2020-03-23", "2020-03-26", "008086", "008086|华夏中证5G通信主题ETF联接A",
//                "98.72", "100", "2020-03-24", "00", "00"));
//        paramList.add(handler008086("2020-03-26", "2020-04-03", "008086", "008086|华夏中证5G通信主题ETF联接A",
//                "576.94", "600", "2020-03-24", "00", "00"));
//        paramList.add(handler008086("2020-04-03", "2020-04-07", "008086", "008086|华夏中证5G通信主题ETF联接A",
//                "960.91", "1000", "2020-03-24", "00", "00"));
//        paramList.add(handler008086("2020-04-07", "2020-04-09", "008086", "008086|华夏中证5G通信主题ETF联接A",
//                "1895.68", "2000", "2020-03-24", "00", "00"));
//        paramList.add(handler008086("2020-04-09", "2020-04-10", "008086", "008086|华夏中证5G通信主题ETF联接A",
//                "2361.71", "2500", "2020-03-24", "00", "00"));
//        paramList.add(handler008086("2020-04-10", "2020-04-13", "008086", "008086|华夏中证5G通信主题ETF联接A",
//                "4297.74", "4500", "2020-03-24", "00", "00"));
//        paramList.add(handler008086("2020-04-13", "2020-04-19", "008086", "008086|华夏中证5G通信主题ETF联接A",
//                "5289.6", "5500", "2020-03-24", "00", "00"));

//        paramList.add(handler110022("2020-03-30", "2020-04-08", "110022", "110022|易方达消费行业股票",
//                "36.3", "100", "2020-03-31", "00", "00"));
//        paramList.add(handler110022("2020-04-08", "2020-04-15", "110022", "110022|易方达消费行业股票",
//                "388.01", "1100", "2020-03-31", "00", "00"));
//        paramList.add(handler110022("2020-04-15", "2020-04-19", "110022", "110022|易方达消费行业股票",
//                "729.84", "2100", "2020-03-31", "00", "00"));

//        paramList.add(handler160225("2020-03-27", "2020-04-03", "160225", "160225|国泰国证新能源汽车指数",
//                "116.05", "100", "2020-03-30", "00", "00"));
//        paramList.add(handler160225("2020-04-03", "2020-04-07", "160225", "160225|国泰国证新能源汽车指数",
//                "231.61", "200", "2020-03-30", "00", "00"));
//        paramList.add(handler160225("2020-04-07", "2020-04-19", "160225", "160225|国泰国证新能源汽车指数",
//                "1355.75", "1200", "2020-03-30", "00", "00"));

//        paramList.add(handler160416("2020-03-17", "2020-04-19", "160416", "160416|华安标普全球石油指数",
//                "166.19", "100", "2020-03-19", "00", "00"));

//        paramList.add(handler160633("2020-03-09", "2020-04-15", "160633", "160633|鹏华证券分级",
//                "97.73", "100", "2020-03-10", "00", "00"));
//        paramList.add(handler160633("2020-04-15", "2020-04-19", "160633", "160633|鹏华证券分级",
//                "1159.15", "1100", "2020-03-10", "00", "00"));

//        paramList.add(handler160637("2020-03-27", "2020-04-10", "160637", "160637|鹏华创业板分级",
//                "80.42", "100", "2020-03-30", "00", "00"));
//        paramList.add(handler160637("2020-04-10", "2020-04-19", "160637", "160637|鹏华创业板分级",
//                "867.5", "1100", "2020-03-30", "00", "00"));

//        paramList.add(handler161028("2020-03-24", "2020-04-08", "161028", "161028|富国中证新能源汽车指数分级",
//                "133.89", "100", "2020-03-24", "00", "00"));
//        paramList.add(handler161028("2020-04-08", "2020-04-10", "161028", "161028|富国中证新能源汽车指数分级",
//                "1368.5", "1100", "2020-03-24", "00", "00"));
//        paramList.add(handler161028("2020-04-10", "2020-04-19", "161028", "161028|富国中证新能源汽车指数分级",
//                "2642.48", "2100", "2020-03-24", "00", "00"));

//        paramList.add(handler161121("2020-02-27", "2020-04-19", "161121", "161121|易方达银行分级",
//                "100.71", "100", "2020-02-28", "00", "00"));

//        paramList.add(handler161725("2020-04-03", "2020-04-07", "161725", "161725|招商中证白酒指数分级",
//                "112.26", "100", "2020-04-07", "00", "00"));
//        paramList.add(handler161725("2020-04-07", "2020-04-13", "161725", "161725|招商中证白酒指数分级",
//                "1206.34", "1100", "2020-04-07", "00", "00"));
//        paramList.add(handler161725("2020-04-13", "2020-04-19", "161725", "161725|招商中证白酒指数分级",
//                "1744.71", "1600", "2020-04-07", "00", "00"));

//        paramList.add(handler162201("2020-03-27", "2020-04-19", "162201", "162201|泰达宏利成长混合",
//                "62.71", "100", "2020-03-30", "00", "00"));

//        paramList.add(handler163208("2020-03-13", "2020-03-18", "163208", "163208|诺安油气能源",
//                "230.6", "100", "2020-03-16", "00", "00"));
//        paramList.add(handler163208("2020-03-18", "2020-04-19", "163208", "163208|诺安油气能源",
//                "708.35", "300", "2020-03-16", "00", "00"));

//        paramList.add(handler165525("2020-04-03", "2020-04-19", "165525", "165525|信诚中证基建工程指数LOF",
//                "294.26", "200", "2020-04-07", "00", "00"));

//        paramList.add(handler257020("2020-03-27", "2020-04-19", "257020", "257020|国联安精选混合",
//                "112.82", "100", "2020-03-30", "00", "00"));

//        paramList.add(handler320007("2020-03-05", "2020-04-10", "320007", "320007|诺安成长混合",
//                "65.82", "100", "2020-03-09", "00", "00"));
//        paramList.add(handler320007("2020-04-10", "2020-04-19", "320007", "320007|诺安成长混合",
//                "838.06", "1100", "2020-03-09", "00", "00"));
//
        paramList.add(handler502010("2020-03-09", "2020-04-19", "502010", "502010|易方达证券公司分级",
                "89.39", "100", "2020-03-10", "00", "00"));

//        paramList.add(handler519674("2020-03-20", "2020-04-19", "519674", "519674|银河创新成长混合",
//                "20.01", "100", "2020-03-23", "00", "00"));

        //ttjj-insert
        for (Map<String, String> stringStringMap : paramList) {
            byte[] bytes = "".getBytes();
            HttpUtil.sendPostTtjj(stringStringMap.get(Content.lsjzUrl), bytes, stringStringMap);
            System.out.println();
        }

    }

    public static Map<String, String> handlertTtjj(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }


    public static Map<String, String> handler000656(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler001027(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler001279(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);


        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler001579(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);

        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler001986(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);


        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler002560(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);


        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler003095(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);


        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler003096(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);


        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler003547(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);


        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler003986(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }
    public static Map<String, String> handler004857(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }
    public static Map<String, String> handler005224(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler005620(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler005911(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);


        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler005967(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);

        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler006021(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);


        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler007136(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler007380(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);


        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler007405(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);


        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler008086(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);


        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler110022(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);


        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler160225(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler160416(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler160633(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler160637(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler161028(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler161121(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler161725(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler162201(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler163208(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler165525(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler257020(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler320007(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler502010(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }

    public static Map<String, String> handler519674(String startDate, String endDate, String jjCode, String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID) {
        Map<String, String> rs = new HashMap<String, String>(16);
        String lsjzUrl = "fundCode=" + jjCode + "&pageIndex=1&pageSize=100&startDate=" + startDate + "&endDate=" + endDate + "&_=" + System.currentTimeMillis();
        rs.put(Content.lsjzUrl, lsjzUrl);
        rs.put(Content.startDate, startDate);
        rs.put(Content.endDate, endDate);
        rs.put(Content.jjCode, jjCode);
        rs.put(Content.jjName, name);
        rs.put(Content.FIRST_NET_DATA, FIRST_NET_DATA);
        rs.put(Content.canShare, canShare);
        rs.put(Content.BUY_COST, BUY_COST);
        rs.put(Content.TRADE_ID, TRADE_ID);
        rs.put(Content.FD_ID, FD_ID);
        rs.put(Content.SOURCE, SOURCE_TTJJ);
        return rs;
    }
}
