package ttjj;

import utils.Content;
import utils.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ttjj_00_0517_0524 {
    /**
     * TTJJ
     */
    static String SOURCE_TTJJ = "3";

    public static void main(String[] args) {
        List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
//        String endDateToday = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String startDate = "2020-05-15";
        String endDate = "2020-05-24";


        {
            paramList.add(handlertTtjjHasSxf(startDate, endDate, "000656", "000656|前海开源沪深300指数",
                    "548.4", "700", "2020-03-13", "50", "30", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "001027", "001027|前海开源中证大农业指数增强",
                    "96.88", "500", "2020-03-31", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "001279", "001279|中海积极增利混合",
                    "794.35", "1000", "2020-03-23", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, "2020-05-19", "001579", "001579|国泰大农业股票",
                    "55.44", "100", "2020-03-31", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "001875", "001875|前海开源沪港深优势精选混合",
                    "390.18", "700", "2020-04-22", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf("2020-05-15", "2020-05-21", "001986", "001986|前海开源人工智能主题混合",
                    "1747.25", "2300", "2020-03-26", "00", "00", "0"));
        paramList.add(handlertTtjjHasSxf("2020-05-21", "2020-05-22", "001986", "001986|前海开源人工智能主题混合",
                "1969.96", "2600", "2020-03-26", "00", "00", "0.45"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "002207", "002207|前海开源金银珠宝混合C",
                    "1579.51", "1600", "2020-04-20", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "003095", "003095|中欧医疗健康混合A",
            "235.38", "500", "2020-04-14", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "003096", "003096|中欧医疗健康混合C",
                    "234.74", "500", "2020-04-14", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "003547", "003547|鹏华丰禄债券",
                    "925.79", "1000", "2020-03-10", "51", "27", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "003986", "003986|申万菱信中证500指数优选增强A",
                    "235.78", "300", "2020-03-11", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf("2020-05-15", "2020-05-18", "004857", "004857|广发中证全指建筑材料指数C",
                    "1158.62", "1400", "2020-04-16", "00", "00", "0"));
            paramList.add(handlertTtjjHasSxf("2020-05-18", endDate, "004857", "004857|广发中证全指建筑材料指数C",
                    "1158.62", "1400", "2020-04-16", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "005224", "005224|广发中证基建工程指数C",
                    "3896.45", "2800", "2020-03-27", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "005620", "005620|中欧品质消费股票A",
                    "62.48", "100", "2020-04-07", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "005911", "005911|广发双擎升级混合",
                    "248.95", "600", "2020-03-30", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "005967", "005967|鹏华创新驱动混合",
                    "769.5", "1000", "2020-03-02", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "006021", "006021|广发沪深300指数增强C",
                    "168.52", "200", "2020-03-17", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "007136", "007136|广发中证100ETF联接C",
                    "95.38", "100", "2020-03-11", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "007300", "007300|国联安中证半导体ETF联接A",
                    "122.22", "200", "2020-04-28", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "007380", "007380|易方达上证50ETF联接基金C",
                    "96.09", "100", "2020-03-11", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "007405", "007405|华宝中证100指数C",
                    "67.29", "100", "2020-03-13", "00", "00", "0"));

                    paramList.add(handlertTtjjHasSxf("2020-05-21", endDate, "007795", "007795|申万菱信中证500指数增强C",
                    "229.45", "300", "2020-05-21", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf("2020-05-15", "2020-05-19", "008086", "008086|华夏中证5G通信主题ETF联接A",
                    "8385.92", "8800", "2020-03-24", "00", "00", "0"));
        paramList.add(handlertTtjjHasSxf("2020-05-19", "2020-05-20", "008086", "008086|华夏中证5G通信主题ETF联接A",
                "8824.61", "9300", "2020-03-24", "00", "00", "0.6"));
        paramList.add(handlertTtjjHasSxf("2020-05-20", "2020-05-21", "008086", "008086|华夏中证5G通信主题ETF联接A",
                "8824.61", "9300", "2020-03-24", "00", "00", "0"));
        paramList.add(handlertTtjjHasSxf("2020-05-21", "2020-05-22", "008086", "008086|华夏中证5G通信主题ETF联接A",
                "9277.91", "9800", "2020-03-24", "00", "00", "0.6"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "110003", "110003|易方达上证50指数A",
                    "280.49", "500", "2020-05-07", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "110011", "110011|易方达中小盘混合",
                    "38.93", "200", "2020-05-07", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "110022", "110022|易方达消费行业股票",
                    "902.41", "2600", "2020-03-31", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "160225", "160225|国泰国证新能源汽车指数",
                    "1355.75", "1200", "2020-03-30", "00", "00", "0"));


            paramList.add(handlertTtjjHasSxf(startDate, endDate, "160633", "160633|鹏华证券分级",
                    "2419.58", "2300", "2020-03-10", "00", "00", ""));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "160637", "160637|鹏华创业板分级",
                    "1097.64", "1400", "2020-03-30", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "161028", "161028|富国中证新能源汽车指数分级",
                    "3510.09", "2800", "2020-03-24", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "161121", "161121|易方达银行分级",
                    "100.71", "100", "2020-02-28", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "161725", "161725|招商中证白酒指数分级",
                    "1744.71", "1600", "2020-04-07", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "162201", "162201|泰达宏利成长混合",
                    "367.56", "600", "2020-03-30", "00", "00", "0"));


            paramList.add(handlertTtjjHasSxf(startDate, endDate, "165525", "165525|信诚中证基建工程指数LOF",
                    "294.26", "200", "2020-04-07", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "257020", "257020|国联安精选混合",
                    "864.08", "800", "2020-03-30", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "320007", "320007|诺安成长混合",
                    "2182.99", "2900", "2020-03-09", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "502010", "502010|易方达证券公司分级",
                    "89.39", "100", "2020-03-10", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf("2020-05-15", "2020-05-21",  "519005", "519005|海富通股票混合",
                    "381.02", "400", "2020-03-10", "00", "00", ""));
        paramList.add(handlertTtjjHasSxf("2020-05-21", "2020-05-22", "519005", "519005|海富通股票混合",
                "661.76", "700", "2020-03-10", "00", "00", "0.45"));
        paramList.add(handlertTtjjHasSxf("2020-05-22", endDate, "519005", "519005|海富通股票混合",
                "661.76", "700", "2020-03-10", "00", "00", "0"));

            paramList.add(handlertTtjjHasSxf(startDate, endDate, "519674", "519674|银河创新成长混合",
                    "20.01", "100", "2020-03-23", "00", "00", "0"));
        }

        /*{

            String startDateQdii = "2020-05-14";
            String endDateQdii = "2020-05-24";
            paramList.add(handlertTtjjHasSxfType(startDateQdii, endDateQdii, "160416", "160416|华安标普全球石油指数",
                    "166.19", "100", "2020-03-19", "00", "00", "0", "QDII"));

            paramList.add(handlertTtjjHasSxfType(startDateQdii, endDateQdii, "163208", "163208|诺安油气能源",
                    "708.35", "300", "2020-03-16", "00", "00", "0", "QDII"));

            paramList.add(handlertTtjjHasSxfType(startDateQdii, endDateQdii, "378546", "378546|上投摩根全球天然资源混合",
                    "846.1", "500", "2020-05-12", "00", "00", "0", "QDII"));
        }*/

        {
            /**
             * 已清仓
             */
//            paramList.add(handlertTtjjHasSxf(startDate, "2020-05-14", "002560", "002560|诺安和鑫灵活配置混合",
//                    "89.62", "100", "2020-03-30", "00", "00", "0"));
        }


        //ttjj-insert
        for (Map<String, String> stringStringMap : paramList) {
            byte[] bytes = "".getBytes();
            HttpUtil.sendPostTtjj(stringStringMap.get(Content.lsjzUrl), bytes, stringStringMap);
            System.out.println();
        }

    }

    /**
     * 含手续费-基金买入
     *
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

    /**
     * 含手续费-基金买入
     *
     * @param startDate
     * @param endDate
     * @param jjCode
     * @param name
     * @param canShare
     * @param BUY_COST
     * @param FIRST_NET_DATA
     * @param TRADE_ID
     * @param FD_ID
     * @param sxf
     * @param fdType         基金类型
     * @return
     */
    private static Map<String, String> handlertTtjjHasSxfType(String startDate, String endDate, String jjCode, String name,
                                                              String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID, String sxf, String fdType) {
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
        rs.put(Content.FD_TYPE, fdType);
        return rs;
    }

}
