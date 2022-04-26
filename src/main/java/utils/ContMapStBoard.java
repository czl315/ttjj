package utils;

import ttjj.service.KlineService;

import java.util.HashMap;
import java.util.Map;

/**
 * ContMapStZiYuan简介-股票分类数据：金融
 *
 * @author Administrator
 * @date 2022-03-18 10:09
 */
public class ContMapStBoard {

    /**
     * yinHang ["银行 "]：42;
     */
    public static Map<String, String> yinHang = new HashMap<>();

    static {
        yinHang.put("001227", "兰州银行");//216	银行         1.11	312.12	1.54	价格区间: 5日:77.50 	10日:46.27 	20日:23.31 	30日:23.31 	60日:21.24 	120日:37.04 	250日:37.04 		,当前价：5.48	,当前：2022-04-26 00:05:40
        yinHang.put("601665", "齐鲁银行");//197	银行         2.7 	278.51	1.52	价格区间: 5日:60.61 	10日:45.30 	20日:57.92 	30日:59.72 	60日:59.72 	120日:59.72 	250日:16.96 		,当前价：6.08	,当前：2022-04-26 00:05:41
        yinHang.put("601838", "成都银行");//186	银行         -2.58	573.63	1.26	价格区间: 5日:37.50 	10日:42.03 	20日:68.99 	30日:78.61 	60日:78.61 	120日:85.77 	250日:88.27 		,当前价：15.88	,当前：2022-04-26 00:05:43
        yinHang.put("601128", "常熟银行");//180	银行         -3.19	216.53	1.41	价格区间: 5日:9.62  	10日:6.85  	20日:46.46 	30日:63.83 	60日:63.83 	120日:66.99 	250日:76.55 		,当前价：7.9	,当前：2022-04-26 00:05:44
        yinHang.put("601009", "南京银行");//177	银行         -2.54	1190.83	1.1 	价格区间: 5日:37.50 	10日:58.33 	20日:75.98 	30日:83.02 	60日:83.02 	120日:84.93 	250日:86.42 		,当前价：11.89	,当前：2022-04-26 00:05:45
        yinHang.put("603323", "苏农银行");//174	银行         -3.19	98.63	1.42	价格区间: 5日:21.21 	10日:16.28 	20日:53.27 	30日:62.12 	60日:62.12 	120日:62.69 	250日:70.41 		,当前价：5.47	,当前：2022-04-26 00:05:46
        yinHang.put("600919", "江苏银行");//173	银行         -5.07	1050.12	1.42	价格区间: 5日:2.38  	10日:2.13  	20日:56.19 	30日:71.60 	60日:71.60 	120日:75.53 	250日:73.66 		,当前价：7.11	,当前：2022-04-26 00:05:46
    }

    /**
     * hangKongJiChang 航空机场
     */
    public static Map<String, String> lvYou_hangKongJiChang = new HashMap<>();

    static {
        lvYou_hangKongJiChang.put("601111", "中国国航");//187	航空机场     -4.19	1360.98	0.96	价格区间: 5日:4.42  	10日:4.07  	20日:28.48 	30日:46.85 	60日:34.10 	120日:45.97 	250日:60.97 		,当前价：9.37	,当前：2022-04-26 00:08:44
        lvYou_hangKongJiChang.put("600009", "上海机场");//182	航空机场     -5.31	935.15	1.1 	价格区间: 5日:0.00  	10日:9.42  	20日:20.89 	30日:37.66 	60日:22.25 	120日:34.06 	250日:51.25 		,当前价：48.53	,当前：2022-04-26 00:08:45
        lvYou_hangKongJiChang.put("600004", "白云机场");//175	航空机场     -5.22	279.51	1.31	价格区间: 5日:6.86  	10日:6.06  	20日:6.06  	30日:26.48 	60日:17.36 	120日:26.33 	250日:52.53 		,当前价：11.81	,当前：2022-04-26 00:08:46
        lvYou_hangKongJiChang.put("600029", "南方航空");//174	航空机场     -4.55	1101.65	1.15	价格区间: 5日:0.00  	10日:6.06  	20日:39.81 	30日:49.18 	60日:28.17 	120日:28.17 	250日:47.96 		,当前价：6.5	,当前：2022-04-26 00:08:48
        lvYou_hangKongJiChang.put("600115", "中国东航");//157	航空机场     -3.35	872.00	1.47	价格区间: 5日:0.00  	10日:24.00 	20日:18.46 	30日:9.23  	60日:7.06  	120日:7.06  	250日:19.80 		,当前价：4.62	,当前：2022-04-26 00:08:49
        lvYou_hangKongJiChang.put("002928", "华夏航空");//154	航空机场     -2.4	102.88	1.24	价格区间: 5日:11.48 	10日:21.17 	20日:23.94 	30日:25.68 	60日:8.60  	120日:8.60  	250日:14.34 		,当前价：10.15	,当前：2022-04-26 00:08:49
        lvYou_hangKongJiChang.put("603885", "吉祥航空");//151	航空机场     -10.01	233.38	1.74	价格区间: 5日:0.00  	10日:0.00  	20日:0.00  	30日:0.00  	60日:0.00  	120日:0.00  	250日:6.07  		,当前价：11.87	,当前：2022-04-26 00:08:50

    }

    /**
     * zhengQuan ["证券"]：48;
     */
    public static Map<String, String> zhengQuan = new HashMap<>();

    static {
        zhengQuan.put("002945", "华林证券");//216	证券         0.45	420.12	1.58	价格区间: 5日:70.65 	10日:49.24 	20日:53.40 	30日:31.98 	60日:43.73 	120日:59.69 	250日:59.69 		,当前价：15.56	,当前：2022-04-26 00:14:51
        zhengQuan.put("601696", "中银证券");//194	证券         2.96	396.70	1.53	价格区间: 5日:68.69 	10日:43.87 	20日:57.25 	30日:68.54 	60日:69.53 	120日:69.53 	250日:34.40 		,当前价：14.28	,当前：2022-04-26 00:14:52
        zhengQuan.put("002670", "国盛金控");//181	证券         -0.83	185.19	1.64	价格区间: 5日:53.77 	10日:31.49 	20日:45.37 	30日:56.79 	60日:57.68 	120日:57.68 	250日:35.65 		,当前价：9.57	,当前：2022-04-26 00:14:54
        zhengQuan.put("601236", "红塔证券");//165	证券         -0.76	491.96	1.42	价格区间: 5日:31.62 	10日:35.91 	20日:53.41 	30日:59.30 	60日:58.93 	120日:45.74 	250日:32.14 		,当前价：10.43	,当前：2022-04-26 00:14:55
        zhengQuan.put("600155", "华创阳安");//157	证券         -6.97	148.56	1.06	价格区间: 5日:4.00  	10日:3.75  	20日:3.75  	30日:8.02  	60日:8.02  	120日:7.98  	250日:3.65  		,当前价：8.54	,当前：2022-04-26 00:14:56
        zhengQuan.put("601990", "南京证券");//145	证券         -1.11	328.45	1.2 	价格区间: 5日:26.73 	10日:56.98 	20日:56.98 	30日:56.98 	60日:55.56 	120日:42.34 	250日:26.12 		,当前价：8.91	,当前：2022-04-26 00:14:57
        zhengQuan.put("000712", "锦龙股份");//144	证券         -5.92	119.71	1.08	价格区间: 5日:1.23  	10日:0.70  	20日:0.70  	30日:0.70  	60日:10.53 	120日:10.53 	250日:8.91  		,当前价：13.36	,当前：2022-04-26 00:14:57
        zhengQuan.put("600909", "华安证券");//139	证券         10.04	241.93	12.12	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:80.25 	120日:65.66 	250日:38.46 		,当前价：5.15	,当前：2022-04-26 00:14:58
        zhengQuan.put("000776", "广发证券");//137	证券         -5.32	1192.70	1.21	价格区间: 5日:6.13  	10日:3.72  	20日:3.57  	30日:3.57  	60日:1.34  	120日:0.86  	250日:12.61 		,当前价：15.65	,当前：2022-04-26 00:14:59
        zhengQuan.put("601456", "国联证券");//137	证券         -5.38	264.20	0.94	价格区间: 5日:3.65  	10日:1.48  	20日:1.09  	30日:0.99  	60日:0.67  	120日:0.67  	250日:0.67  		,当前价：9.33	,当前：2022-04-26 00:15:00
        zhengQuan.put("000728", "国元证券");//136	证券         -3.62	301.97	1.28	价格区间: 5日:0.00  	10日:0.00  	20日:22.95 	30日:32.37 	60日:33.33 	120日:33.33 	250日:22.17 		,当前价：6.92	,当前：2022-04-26 00:15:00
        zhengQuan.put("600621", "华鑫股份");//133	证券         -4.11	113.94	1.45	价格区间: 5日:3.33  	10日:1.34  	20日:1.26  	30日:1.05  	60日:1.05  	120日:0.79  	250日:0.42  		,当前价：10.74	,当前：2022-04-26 00:15:01
        zhengQuan.put("002939", "长城证券");//127	证券         -6.03	270.93	0.92	价格区间: 5日:2.78  	10日:1.52  	20日:1.52  	30日:1.52  	60日:0.72  	120日:0.51  	250日:0.49  		,当前价：8.73	,当前：2022-04-26 00:15:02
        zhengQuan.put("601066", "中信建投");//127	证券         -0.39	1801.88	2.05	价格区间: 5日:43.75 	10日:33.82 	20日:33.82 	30日:34.96 	60日:17.79 	120日:11.37 	250日:8.11  		,当前价：23.23	,当前：2022-04-26 00:15:02
        zhengQuan.put("600864", "哈投股份");//122	证券         -6.74	103.61	1.38	价格区间: 5日:1.64  	10日:1.16  	20日:1.11  	30日:1.02  	60日:0.84  	120日:0.63  	250日:0.43  		,当前价：4.98	,当前：2022-04-26 00:15:03
        zhengQuan.put("600958", "东方证券");//119	证券         null	644.82	null	价格区间: 5日:1.90  	10日:1.90  	20日:1.90  	30日:1.79  	60日:0.82  	120日:0.52  	250日:8.34  		,当前价：null	,当前：2022-04-26 00:15:04
    }


    /**
     * allZiYuan
     */
    public static Map<String, String> all = new HashMap<>();

    static {
        all.putAll(yinHang);//银行
        all.putAll(lvYou_hangKongJiChang);
        all.putAll(zhengQuan);
    }

    public static void main(String[] args) {
        String spDate = null;
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);//        String date = "2022-03-25";
//        String date = "2022-04-08";
//        spDate = "2022-04-08";//特定日期：下一交易日
        KlineService.checkMaDemo(all, date, spDate);// all   huaGong   huaGong_linHuaGong    yinHang
    }
}
