package utils;

import ttjj.stat.StockStatDemo;

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
     * gangTie_diTanYeJin ["低碳冶金"]：9;
     */
    public static Map<String, String> gangTie_diTanYeJin = new HashMap<>();

    static {
        gangTie_diTanYeJin.put("000959", "首钢股份");//275	钢铁行业	3.65	363.84	2.88	价格区间: 5日:94.59 	10日:96.23 	20日:48.55 	30日:32.68 	60日:32.68 	120日:21.07 	250日:23.34 		,当前价：5.39	,当前：2022-04-06 11:48:26
        gangTie_diTanYeJin.put("600581", "八一钢铁");//272	钢铁行业	2.42	90.75	3.03	价格区间: 5日:97.50 	10日:98.04 	20日:56.55 	30日:48.24 	60日:41.21 	120日:26.03 	250日:29.00 		,当前价：5.92	,当前：2022-04-06 11:48:27
        gangTie_diTanYeJin.put("000928", "中钢国际");//238	工程咨询服务	-0.46	83.78	1.41	价格区间: 5日:12.00 	10日:11.76 	20日:17.86 	30日:14.04 	60日:7.42  	120日:6.25  	250日:11.29 		,当前价：6.54	,当前：2022-04-06 11:48:28
        gangTie_diTanYeJin.put("002002", "鸿达兴业");//217	化学原料	-2.26	148.29	6.16	价格区间: 5日:29.41 	10日:17.86 	20日:35.96 	30日:26.89 	60日:15.69 	120日:15.69 	250日:36.86 		,当前价：4.75	,当前：2022-04-06 11:48:29
        gangTie_diTanYeJin.put("601618", "中国中冶");//205	工程建设	0.27	775.06	2.21	价格区间: 5日:36.84 	10日:46.43 	20日:18.18 	30日:18.18 	60日:18.18 	120日:18.18 	250日:25.87 		,当前价：3.74	,当前：2022-04-06 11:48:31
    }

    /**
     * jinShu_jiBenJinShu ["基本金属"]：37;
     */
    public static Map<String, String> jinShu_jiBenJinShu = new HashMap<>();

    static {
        jinShu_jiBenJinShu.put("002738", "中矿资源");//427	小金属  	-3.18	284.38	3.21	价格区间: 5日:7.38  	10日:4.87  	20日:4.29  	30日:3.01  	60日:56.05 	120日:58.96 	250日:72.54 		,当前价：87.32	,当前：2022-04-06 14:01:30
        jinShu_jiBenJinShu.put("600338", "西藏珠峰");//371	有色金属	-2.34	237.05	2.26	价格区间: 5日:9.02  	10日:4.73  	20日:4.09  	30日:2.18  	60日:1.86  	120日:1.57  	250日:38.95 		,当前价：25.93	,当前：2022-04-06 14:01:31
        jinShu_jiBenJinShu.put("600367", "红星发展");//340	化学原料	-5.58	69.50	3.75	价格区间: 5日:56.34 	10日:60.69 	20日:64.25 	30日:44.93 	60日:64.93 	120日:67.17 	250日:77.79 		,当前价：23.7	,当前：2022-04-06 14:01:32
        jinShu_jiBenJinShu.put("000807", "云铝股份");//340	有色金属	-1.78	460.20	3.5 	价格区间: 5日:30.29 	10日:30.29 	20日:40.97 	30日:35.43 	60日:45.06 	120日:48.40 	250日:29.26 		,当前价：13.27	,当前：2022-04-06 14:01:41
        jinShu_jiBenJinShu.put("603799", "华友钴业");//339	能源金属	-1.95	1169.12	2.1 	价格区间: 5日:21.34 	10日:19.28 	20日:31.76 	30日:25.35 	60日:25.35 	120日:17.71 	250日:38.17 		,当前价：95.72	,当前：2022-04-06 14:01:42
        jinShu_jiBenJinShu.put("601899", "紫金矿业");//204	贵金属  	1.14	3048.93	2.52	价格区间: 5日:58.62 	10日:68.82 	20日:88.45 	30日:77.89 	60日:78.05 	120日:78.05 	250日:66.67 		,当前价：11.58	,当前：2022-04-06 14:01:59

    }


    /**
     * qingNengYuan ["氢能源"]：91;
     */
    public static Map<String, String> qingNengYuan = new HashMap<>();

    static {
        qingNengYuan.put("002537", "海联金汇");//437	汽车零部件	1.29	138.42	2.39	价格区间: 5日:33.79 	10日:32.95 	20日:65.75 	30日:69.51 	60日:72.57 	120日:78.66 	250日:78.89 		,当前价：11.79	,当前：2022-04-06 14:34:18
        qingNengYuan.put("600277", "亿利洁能");//402	化学原料	2.51	218.27	3.73	价格区间: 5日:58.33 	10日:19.21 	20日:11.93 	30日:28.43 	60日:44.70 	120日:57.20 	250日:64.27 		,当前价：6.13	,当前：2022-04-06 14:34:20
        qingNengYuan.put("000980", "*ST众泰 ");//392	汽车整车	1.28	200.74	2.38	价格区间: 5日:61.76 	10日:69.23 	20日:62.96 	30日:38.06 	60日:14.33 	120日:7.85  	250日:11.91 		,当前价：3.96	,当前：2022-04-06 14:34:20
        qingNengYuan.put("600860", "京城股份");//378	通用设备	2.67	97.05	2.55	价格区间: 5日:100.00	10日:44.51 	20日:60.97 	30日:61.94 	60日:59.88 	120日:67.92 	250日:69.51 		,当前价：20.01	,当前：2022-04-06 14:34:22
        qingNengYuan.put("603938", "三孚股份");//364	化肥行业	-0.9	96.69	1.81	价格区间: 5日:10.24 	10日:45.90 	20日:63.07 	30日:68.71 	60日:75.89 	120日:68.80 	250日:45.12 		,当前价：49.53	,当前：2022-04-06 14:34:23
        qingNengYuan.put("002060", "粤水电  ");//356	工程建设	4.29	114.09	3.68	价格区间: 5日:82.23 	10日:86.77 	20日:91.42 	30日:91.70 	60日:92.18 	120日:92.28 	250日:93.78 		,当前价：9.49	,当前：2022-04-06 14:34:24
        qingNengYuan.put("600141", "兴发集团");//347	化肥行业	-0.09	371.98	1.61	价格区间: 5日:11.59 	10日:14.81 	20日:9.00  	30日:6.33  	60日:29.81 	120日:14.80 	250日:44.68 		,当前价：33.46	,当前：2022-04-06 14:34:25
        qingNengYuan.put("600032", "浙江新能");//341	电力行业	-1   	289.54	1.63	价格区间: 5日:28.31 	10日:34.68 	20日:56.01 	30日:56.01 	60日:56.01 	120日:36.38 	250日:62.56 		,当前价：13.92	,当前：2022-04-06 14:34:26
        qingNengYuan.put("002639", "雪人股份");//332	通用设备	2.99	87.92	2   	价格区间: 5日:91.96 	10日:26.75 	20日:50.09 	30日:50.09 	60日:30.59 	120日:43.98 	250日:48.84 		,当前价：11.38	,当前：2022-04-06 14:34:27
    }

    /**
     * allZiYuan
     */
    public static Map<String, String> all = new HashMap<>();

    static {
        all.putAll(keRanBing);//可燃冰
        all.putAll(youJiaXiangGuan);//油价相关
        all.putAll(gangTie_diTanYeJin);//低碳冶金
        all.putAll(jinShu_jiBenJinShu);//基本金属
        all.putAll(qingNengYuan);//氢能源
    }

    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);//        String date = "2022-03-25";
//        StockStatDemo.checkMaDemo(chuanMei, date);//
        StockStatDemo.checkMaDemo(all, date);//   shuZi
    }
}
