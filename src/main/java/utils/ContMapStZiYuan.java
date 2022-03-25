package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ContMapStZiYuan简介-股票分类数据：资源
 *
 * @author Administrator
 * @date 2022-03-18 10:09
 */
public class ContMapStZiYuan {

    /**
     * ["可燃冰"]：8;
     */
    public static Map<String, String> keRanBing = new HashMap<>();

    static {
        keRanBing.put("002353", "杰瑞股份");//268	专用设备	2.37	422.61	2.72	价格区间: 5日:52.18 	10日:51.24 	20日:51.24 	30日:51.24 	60日:55.05 	120日:43.37 	250日:60.53 		,当前价：44.12	,当前：2022-03-18 10:18:33
        keRanBing.put("600098", "广州发展");//211	电力行业	0.46	230.36	1.81	价格区间: 5日:61.04 	10日:42.73 	20日:38.84 	30日:26.55 	60日:15.56 	120日:13.43 	250日:23.29 		,当前价：6.5	,当前：2022-03-18 10:18:34
        keRanBing.put("601808", "中海油服");//202	采掘行业	2.56	650.85	4.1 	价格区间: 5日:53.01 	10日:24.91 	20日:22.72 	30日:22.72 	60日:22.72 	120日:22.72 	250日:28.16 		,当前价：13.64	,当前：2022-03-18 10:18:35
    }

    /**
     * ["油价相关"]：10;
     */
    public static Map<String, String> youJiaXiangGuan = new HashMap<>();
    static {
        youJiaXiangGuan.put("000822", "山东海化");//268	化学原料	0.61	59.43	2.89	价格区间: 5日:65.38 	10日:37.78 	20日:32.28 	30日:28.81 	60日:28.81 	120日:5.44  	250日:19.52 		,当前价：6.64	,当前：2022-03-18 10:22:47
        youJiaXiangGuan.put("600777", "新潮能源");//251	石油行业	3.03	138.73	5.36	价格区间: 5日:87.10 	10日:45.00 	20日:44.26 	30日:44.26 	60日:41.54 	120日:20.45 	250日:35.58 		,当前价：2.04	,当前：2022-03-18 10:22:49
        youJiaXiangGuan.put("601111", "中国国航");//207	航空机场	0.74	1391.48	2.86	价格区间: 5日:82.35 	10日:65.97 	20日:56.25 	30日:41.31 	60日:42.99 	120日:58.28 	250日:65.24 		,当前价：9.58	,当前：2022-03-18 10:22:51
        youJiaXiangGuan.put("601808", "中海油服");//202	采掘行业	2.56	650.85	4.1 	价格区间: 5日:50.60 	10日:23.77 	20日:21.69 	30日:21.69 	60日:21.69 	120日:21.69 	250日:27.20 		,当前价：13.64	,当前：2022-03-18 10:22:52
        youJiaXiangGuan.put("600115", "中国东航");//193	航空机场	3.33	1053.19	2.74	价格区间: 5日:95.24 	10日:81.63 	20日:64.52 	30日:57.14 	60日:60.00 	120日:63.19 	250日:69.54 		,当前价：5.58	,当前：2022-03-18 10:22:53
        youJiaXiangGuan.put("600688", "上海石化");//190	石油行业	1.42	386.41	3.83	价格区间: 5日:65.12 	10日:33.73 	20日:31.11 	30日:30.77 	60日:23.53 	120日:15.30 	250日:20.10 		,当前价：3.57	,当前：2022-03-18 10:22:54
        youJiaXiangGuan.put("601857", "中国石油");//189	石油行业	2.34	9608.60	6.15	价格区间: 5日:48.05 	10日:31.62 	20日:30.33 	30日:30.33 	60日:31.45 	120日:31.89 	250日:51.35 		,当前价：5.25	,当前：2022-03-18 10:22:55
    }

    /**
     * allZiYuan
     */
    public static Map<String, String> allZiYuan = new HashMap<>();

    static {
        allZiYuan.putAll(keRanBing);//可燃冰
        allZiYuan.putAll(youJiaXiangGuan);//油价相关
    }
}
