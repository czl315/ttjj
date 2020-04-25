package ttjj;

import utils.Content;
import utils.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.*;

public class Ttjj_00_20200424 {
    /**
     * TTJJ
     */
    static String SOURCE_TTJJ = "3";

    public static void main(String[] args) {
        List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
//        String endDateToday = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String startDate = "2020-04-17";
        String endDate = "2020-04-24";

        paramList.add(handlertTtjjHasSxf(startDate, endDate, "000656", "000656|前海开源沪深300指数",
                "388.34", "500", "2020-03-13", "50", "30","0"));
        paramList.add(handlertTtjjHasSxf(startDate, endDate, "001027", "001027|前海开源中证大农业指数增强",
                "96.88", "500", "2020-03-31", "00", "00","0"));
        paramList.add(handlertTtjjHasSxf(startDate, endDate, "001279", "001279|中海积极增利混合",
                "794.35", "1000", "2020-03-23", "00", "00","0"));
        paramList.add(handlertTtjjHasSxf(startDate, endDate, "001579", "001579|国泰大农业股票",
                "55.44", "100", "2020-03-31", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf("2020-04-21", "2020-04-22", "001875", "001875|前海开源沪港深优势精选混合",
                "279.85", "500", "2020-04-22", "00", "00","0.75"));
        paramList.add(handlertTtjjHasSxf("2020-04-22", endDate, "001875", "001875|前海开源沪港深优势精选混合",
                "279.85", "500", "2020-04-22", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, "2020-04-20", "001986", "001986|前海开源人工智能主题混合",
                "79.44", "100", "2020-03-26", "00", "00","0"));
        paramList.add(handlertTtjjHasSxf("2020-04-20", "2020-04-21", "001986", "001986|前海开源人工智能主题混合",
                "840.49", "1100", "2020-03-26", "00", "00","1.5"));
        paramList.add(handlertTtjjHasSxf("2020-04-21", "2020-04-22", "001986", "001986|前海开源人工智能主题混合",
                "1224.23", "1600", "2020-03-26", "00", "00","0.75"));
        paramList.add(handlertTtjjHasSxf("2020-04-22", "2020-04-24", "001986", "001986|前海开源人工智能主题混合",
                "1224.23", "1600", "2020-03-26", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf("2020-04-17", "2020-04-20", "002207", "002207|前海开源金银珠宝混合C",
                "96.75", "100", "2020-04-20", "00", "00","0.15"));
        paramList.add(handlertTtjjHasSxf("2020-04-20", "2020-04-21", "002207", "002207|前海开源金银珠宝混合C",
                "96.75", "100", "2020-04-20", "00", "00","0"));
        paramList.add(handlertTtjjHasSxf("2020-04-21", "2020-04-22", "002207", "002207|前海开源金银珠宝混合C",
                "587.17", "600", "2020-04-20", "00", "00","0.75"));
        paramList.add(handlertTtjjHasSxf("2020-04-22", "2020-04-23", "002207", "002207|前海开源金银珠宝混合C",
                "1079.53", "1100", "2020-04-20", "00", "00","0.75"));
        paramList.add(handlertTtjjHasSxf("2020-04-23", "2020-04-24", "002207", "002207|前海开源金银珠宝混合C",
                "1079.53", "1100", "2020-04-20", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf("2020-04-17", "2020-04-24", "002560", "002560|诺安和鑫灵活配置混合",
                "89.62", "100", "2020-03-30", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf("2020-04-17", endDate, "003095", "003095|中欧医疗健康混合A",
                "235.38", "500", "2020-04-13", "00", "00","0"));
        paramList.add(handlertTtjjHasSxf("2020-04-17", endDate, "003096", "003096|中欧医疗健康混合C",
                "234.74", "500", "2020-04-14", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, endDate, "003547", "003547|鹏华丰禄债券",
                "925.79", "1000", "2020-03-10", "51", "27","0"));

        paramList.add(handlertTtjjHasSxf(startDate, endDate, "003986", "003986|申万菱信中证500指数优选增强A",
                "235.78", "300", "2020-03-11","00","00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, endDate, "004857", "004857|广发中证全指建筑材料指数C",
                "422.65", "500", "2020-04-16", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, "2020-04-24", "005224", "005224|广发中证基建工程指数C",
                "3484.7", "2500", "2020-03-27", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, endDate, "005620", "005620|中欧品质消费股票A",
                "62.48", "100", "2020-04-07", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, "2020-04-21", "005911", "005911|广发双擎升级混合",
                "43.23", "100", "2020-03-30", "00", "00","0"));
        paramList.add(handlertTtjjHasSxf("2020-04-21", "2020-04-22", "005911", "005911|广发双擎升级混合",
                "248.95", "600", "2020-03-30", "00", "00","0.75"));
        paramList.add(handlertTtjjHasSxf("2020-04-22", "2020-04-24", "005911", "005911|广发双擎升级混合",
                "248.95", "600", "2020-03-30", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, endDate, "005967", "005967|鹏华创新驱动混合",
                "769.5", "1000", "2020-03-02", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, endDate, "006021", "006021|广发沪深300指数增强C",
                "168.52", "200", "2020-03-17", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, endDate, "007136", "007136|广发中证100ETF联接C",
                "95.38", "100", "2020-03-11", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, endDate, "007380", "007380|易方达上证50ETF联接基金C",
                "96.09", "100", "2020-03-11", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, endDate, "007405", "007405|华宝中证100指数C",
                "67.29", "100", "2020-03-13", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf("2020-04-17", "2020-04-20", "008086", "008086|华夏中证5G通信主题ETF联接A",
                "5289.6", "5500", "2020-03-24", "00", "00","0"));
        paramList.add(handlertTtjjHasSxf("2020-04-20", "2020-04-21", "008086", "008086|华夏中证5G通信主题ETF联接A",
                "6215.87", "6500", "2020-03-24", "00", "00","1.2"));
        paramList.add(handlertTtjjHasSxf("2020-04-21", "2020-04-22", "008086", "008086|华夏中证5G通信主题ETF联接A",
                "7152.39", "7500", "2020-03-24", "00", "00","1.2"));
        paramList.add(handlertTtjjHasSxf("2020-04-22", "2020-04-23", "008086", "008086|华夏中证5G通信主题ETF联接A",
                "7619.69", "8000", "2020-03-24", "00", "00","0.6"));
        paramList.add(handlertTtjjHasSxf("2020-04-23", "2020-04-24", "008086", "008086|华夏中证5G通信主题ETF联接A",
                "8095.4", "8500", "2020-03-24", "00", "00","0.6"));

        paramList.add(handlertTtjjHasSxf(startDate, "2020-04-21", "110022", "110022|易方达消费行业股票",
                "729.84", "2100", "2020-03-31", "00", "00","0"));
        paramList.add(handlertTtjjHasSxf("2020-04-21", "2020-04-22", "110022", "110022|易方达消费行业股票",
                "902.41", "2600", "2020-03-31", "00", "00","0.75"));
        paramList.add(handlertTtjjHasSxf("2020-04-22", "2020-04-24", "110022", "110022|易方达消费行业股票",
                "902.41", "2600", "2020-03-31", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, "2020-04-24", "160225", "160225|国泰国证新能源汽车指数",
                "1355.75", "1200", "2020-03-30", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, endDate, "160416", "160416|华安标普全球石油指数",
                "166.19", "100", "2020-03-19", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf("2020-04-17", "2020-04-20", "160633", "160633|鹏华证券分级",
                "1685.94", "1600", "2020-03-10", "00", "00","0.6"));
        paramList.add(handlertTtjjHasSxf("2020-04-20", "2020-04-21", "160633", "160633|鹏华证券分级",
                "2209.97", "2100", "2020-03-10", "00", "00","0.6"));
        paramList.add(handlertTtjjHasSxf("2020-04-21", "2020-04-24", "160633", "160633|鹏华证券分级",
                "2209.97", "2100", "2020-03-10", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, "2020-04-24", "160637", "160637|鹏华创业板分级",
                "867.5", "1100", "2020-03-30", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, "2020-04-21", "161028", "161028|富国中证新能源汽车指数分级",
                "2642.48", "2100", "2020-03-24", "00", "00","0"));
        paramList.add(handlertTtjjHasSxf("2020-04-21", "2020-04-22", "161028", "161028|富国中证新能源汽车指数分级",
                "3261.32", "2600", "2020-03-24", "00", "00","0.6"));
        paramList.add(handlertTtjjHasSxf("2020-04-22", "2020-04-24", "161028", "161028|富国中证新能源汽车指数分级",
                "3261.32", "2600", "2020-03-24", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, endDate, "161121", "161121|易方达银行分级",
                "100.71", "100", "2020-02-28", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, "2020-04-24", "161725", "161725|招商中证白酒指数分级",
                "1744.71", "1600", "2020-04-07", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, "2020-04-23", "162201", "162201|泰达宏利成长混合",
                "62.71", "100", "2020-03-30", "00", "00","0"));
        paramList.add(handlertTtjjHasSxf("2020-04-23", "2020-04-24", "162201", "162201|泰达宏利成长混合",
                "367.56", "600", "2020-03-30", "00", "00","0.75"));

        paramList.add(handlertTtjjHasSxf(startDate, endDate, "163208", "163208|诺安油气能源",
                "708.35", "300", "2020-03-16", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, endDate, "165525", "165525|信诚中证基建工程指数LOF",
                "294.26", "200", "2020-04-07", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, "2020-04-21", "257020", "257020|国联安精选混合",
                "112.82", "100", "2020-03-30", "00", "00","0"));
        paramList.add(handlertTtjjHasSxf("2020-04-21", "2020-04-22", "257020", "257020|国联安精选混合",
                "646.78", "600", "2020-03-30", "00", "00","0.75"));
        paramList.add(handlertTtjjHasSxf("2020-04-22", "2020-04-24", "257020", "257020|国联安精选混合",
                "646.78", "600", "2020-03-30", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, "2020-04-21", "320007", "320007|诺安成长混合",
                "838.06", "1100", "2020-03-09", "00", "00","0"));
        paramList.add(handlertTtjjHasSxf("2020-04-21", "2020-04-22", "320007", "320007|诺安成长混合",
                "1208.97", "1600", "2020-03-09", "00", "00","0.75"));
        paramList.add(handlertTtjjHasSxf("2020-04-22", "2020-04-23", "320007", "320007|诺安成长混合",
                "1577.69", "2100", "2020-03-09", "00", "00","0.75"));
        paramList.add(handlertTtjjHasSxf("2020-04-23", "2020-04-24", "320007", "320007|诺安成长混合",
                "1953.63", "2600", "2020-03-09", "00", "00","0.75"));

        paramList.add(handlertTtjjHasSxf(startDate, endDate, "502010", "502010|易方达证券公司分级",
                "89.39", "100", "2020-03-10", "00", "00","0"));

        paramList.add(handlertTtjjHasSxf(startDate, endDate, "519674", "519674|银河创新成长混合",
                "20.01", "100", "2020-03-23", "00", "00","0"));

        //ttjj-insert
        for (Map<String, String> stringStringMap : paramList) {
            byte[] bytes = "".getBytes();
            HttpUtil.sendPostTtjj(stringStringMap.get(Content.lsjzUrl), bytes, stringStringMap);
            System.out.println();
        }

    }

    /**
     * 含手续费-基金买入
     * @param startDate
     * @param endDate
     * @param jjCode
     * @param name
     * @param canShare
     * @param BUY_COST
     * @param FIRST_NET_DATA
     * @param TRADE_ID
     * @param FD_ID
     * @return
     */
    private static Map<String, String> handlertTtjjHasSxf(String startDate, String endDate, String jjCode, String name,
                                                          String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID, String sxf) {
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
        rs.put(Content.SXF, sxf);
        return rs;
    }

}
