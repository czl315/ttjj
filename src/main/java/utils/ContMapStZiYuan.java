package utils;

import ttjj.service.KlineService;
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
//        youJiaXiangGuan.put("601111", "中国国航");//207	航空机场	0.74	1391.48	2.86	价格区间: 5日:82.35 	10日:65.97 	20日:56.25 	30日:41.31 	60日:42.99 	120日:58.28 	250日:65.24 		,当前价：9.58	,当前：2022-03-18 10:22:51
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
        gangTie_diTanYeJin.put("600581", "八一钢铁");//345	钢铁行业     3.08	118.03	null	价格区间: 5日:67.11 	10日:81.82 	20日:83.50 	30日:85.07 	60日:85.07 	120日:85.07 	250日:52.69 		,当前价：7.7	,当前：2022-04-19 11:38:07
        gangTie_diTanYeJin.put("000959", "首钢股份");//282	钢铁行业     -2.2	448.08	null	价格区间: 5日:66.67 	10日:77.27 	20日:83.33 	30日:74.84 	60日:56.59 	120日:44.96 	250日:30.62 		,当前价：5.77	,当前：2022-04-19 11:38:08
        gangTie_diTanYeJin.put("000928", "中钢国际");//255	工程咨询服务 1.46	80.06	null	价格区间: 5日:32.81 	10日:15.67 	20日:15.67 	30日:12.65 	60日:8.75  	120日:4.93  	250日:2.69  		,当前价：6.25	,当前：2022-04-19 11:38:10
        gangTie_diTanYeJin.put("600307", "酒钢宏兴");//232	钢铁行业     1.65	154.70	null	价格区间: 5日:88.24 	10日:91.30 	20日:92.86 	30日:94.12 	60日:94.12 	120日:74.42 	250日:41.18 		,当前价：2.47	,当前：2022-04-19 11:38:11
        gangTie_diTanYeJin.put("002002", "鸿达兴业");//210	化学原料     0.95	132.37	null	价格区间: 5日:26.00 	10日:18.06 	20日:11.93 	30日:9.63  	60日:7.56  	120日:5.53  	250日:19.82 		,当前价：4.24	,当前：2022-04-19 11:38:12
        gangTie_diTanYeJin.put("601618", "中国中冶");//206	工程建设     0.54	764.70	null	价格区间: 5日:30.00 	10日:18.18 	20日:25.00 	30日:15.15 	60日:15.15 	120日:15.15 	250日:24.71 		,当前价：3.69	,当前：2022-04-19 11:38:13
    }

    /**
     * jinShu_jiBenJinShu ["基本金属"]：37;
     */
    public static Map<String, String> jinShu_jiBenJinShu = new HashMap<>();

    static {
        jinShu_jiBenJinShu.put("002738", "中矿资源");//420	小金属       0.94	255.43	1.61	价格区间: 5日:27.66 	10日:16.96 	20日:12.47 	30日:9.69  	60日:43.36 	120日:46.04 	250日:64.61 		,当前价：78.43	,当前：2022-04-13 11:29:26
        jinShu_jiBenJinShu.put("600338", "西藏珠峰");//371	有色金属     -0.21	219.04	2.13	价格区间: 5日:33.44 	10日:20.24 	20日:13.92 	30日:9.25  	60日:7.94  	120日:6.05  	250日:34.55 		,当前价：23.96	,当前：2022-04-13 11:29:27
        jinShu_jiBenJinShu.put("000807", "云铝股份");//351	有色金属     5.47	448.06	1.85	价格区间: 5日:82.28 	10日:41.67 	20日:45.02 	30日:28.38 	60日:39.06 	120日:42.77 	250日:24.15 		,当前价：12.92	,当前：2022-04-13 11:29:29
        jinShu_jiBenJinShu.put("600367", "红星发展");//343	化学原料     -5   	66.27	3.19	价格区间: 5日:36.91 	10日:47.58 	20日:56.48 	30日:42.17 	60日:63.17 	120日:65.52 	250日:76.67 		,当前价：22.6	,当前：2022-04-13 11:29:30
        jinShu_jiBenJinShu.put("002182", "云海金属");//341	小金属       6.74	127.02	2.29	价格区间: 5日:66.38 	10日:55.27 	20日:36.54 	30日:21.05 	60日:18.70 	120日:34.36 	250日:47.89 		,当前价：19.65	,当前：2022-04-13 11:29:31
        jinShu_jiBenJinShu.put("603799", "华友钴业");//322	能源金属     0.67	1022.43	3.79	价格区间: 5日:19.64 	10日:15.19 	20日:11.87 	30日:7.08  	60日:6.75  	120日:4.85  	250日:21.40 		,当前价：83.71	,当前：2022-04-13 11:29:32
    }

    /**
     * meiTan_meiHuaGong ["煤化工"]：57;
     */
    public static Map<String, String> meiTan_meiHuaGong = new HashMap<>();

    static {
        meiTan_meiHuaGong.put("000422", "湖北宜化");//482	化学原料     -0.88	213.60	2.93	价格区间: 5日:60.92 	10日:75.10 	20日:82.41 	30日:84.81 	60日:85.50 	120日:40.14 	250日:63.86 		,当前价：23.79	,当前：2022-04-15 09:56:18
        meiTan_meiHuaGong.put("600096", "云天化  ");//456	化肥行业     -1.05	534.24	3.75	价格区间: 5日:74.45 	10日:77.45 	20日:83.95 	30日:85.09 	60日:91.15 	120日:83.86 	250日:72.17 		,当前价：29.1	,当前：2022-04-15 09:56:20
        meiTan_meiHuaGong.put("600123", "兰花科创");//428	煤炭行业     1.73	181.76	6.87	价格区间: 5日:92.06 	10日:95.05 	20日:96.22 	30日:96.52 	60日:96.99 	120日:97.02 	250日:97.95 		,当前价：15.91	,当前：2022-04-15 09:56:21
        meiTan_meiHuaGong.put("601101", "昊华能源");//427	煤炭行业     5.39	143.04	18.46	价格区间: 5日:91.67 	10日:91.67 	20日:94.97 	30日:95.11 	60日:96.42 	120日:90.65 	250日:80.51 		,当前价：11.92	,当前：2022-04-15 09:56:23
        meiTan_meiHuaGong.put("600188", "兖矿能源");//425	煤炭行业     0.7 	1988.39	4.89	价格区间: 5日:84.65 	10日:86.51 	20日:92.36 	30日:93.02 	60日:95.84 	120日:95.98 	250日:97.37 		,当前价：40.18	,当前：2022-04-15 09:56:23
        meiTan_meiHuaGong.put("000933", "神火股份");//400	有色金属     0.98	349.58	9.75	价格区间: 5日:81.43 	10日:81.43 	20日:85.80 	30日:87.48 	60日:88.85 	120日:91.33 	250日:74.67 		,当前价：15.53	,当前：2022-04-15 09:56:25
        meiTan_meiHuaGong.put("600277", "亿利洁能");//380	化学原料     -1.52	185.15	2.22	价格区间: 5日:12.50 	10日:7.55  	20日:3.39  	30日:2.55  	60日:20.93 	120日:28.34 	250日:48.83 		,当前价：5.2	,当前：2022-04-15 09:56:25
    }

    /**
     * meiTan_xiQueZiYuan ["稀缺资源"]：66;
     */
    public static Map<String, String> meiTan_xiQueZiYuan = new HashMap<>();

    static {
        meiTan_xiQueZiYuan.put("601666", "平煤股份");//479	煤炭行业     1.49	378.77	null	价格区间: 5日:45.32 	10日:48.46 	20日:58.44 	30日:67.65 	60日:75.22 	120日:78.49 	250日:82.02 		,当前价：16.36	,当前：2022-04-19 10:54:58
        meiTan_xiQueZiYuan.put("002192", "融捷股份");//447	电池         0.35	257.06	null	价格区间: 5日:45.44 	10日:19.14 	20日:12.39 	30日:12.39 	60日:24.57 	120日:18.64 	250日:37.22 		,当前价：99.0	,当前：2022-04-19 10:54:59
        meiTan_xiQueZiYuan.put("000983", "山西焦煤");//433	煤炭行业     1.43	608.75	null	价格区间: 5日:89.55 	10日:91.07 	20日:91.61 	30日:93.76 	60日:95.39 	120日:95.39 	250日:84.12 		,当前价：14.86	,当前：2022-04-19 10:55:00
        meiTan_xiQueZiYuan.put("002176", "江特电机");//420	电机         -0.51	335.12	null	价格区间: 5日:57.14 	10日:45.03 	20日:22.11 	30日:19.80 	60日:41.57 	120日:32.91 	250日:50.72 		,当前价：19.64	,当前：2022-04-19 10:55:01
        meiTan_xiQueZiYuan.put("002738", "中矿资源");//415	小金属       -0.67	264.58	null	价格区间: 5日:61.31 	10日:33.93 	20日:17.16 	30日:15.51 	60日:45.70 	120日:48.26 	250日:66.07 		,当前价：81.24	,当前：2022-04-19 10:55:02
        meiTan_xiQueZiYuan.put("600188", "兖矿能源");//411	煤炭行业     2.79	1749.86	null	价格区间: 5日:35.91 	10日:35.91 	20日:42.11 	30日:57.65 	60日:74.79 	120日:75.59 	250日:84.03 		,当前价：35.36	,当前：2022-04-19 10:55:04
    }

    /**
     * meiTan 煤炭：煤化工,稀缺资源,
     */
    public static Map<String, String> meiTan = new HashMap<>();

    static {
        meiTan.putAll(meiTan_meiHuaGong);
        meiTan.putAll(meiTan_xiQueZiYuan);
    }

    /**
     * qingNengYuan ["氢能源"]：91;
     */
    public static Map<String, String> qiTi_qingNengYuan = new HashMap<>();

    static {
        qiTi_qingNengYuan.put("002537", "海联金汇");//437	汽车零部件	1.29	138.42	2.39	价格区间: 5日:33.79 	10日:32.95 	20日:65.75 	30日:69.51 	60日:72.57 	120日:78.66 	250日:78.89 		,当前价：11.79	,当前：2022-04-06 14:34:18
        qiTi_qingNengYuan.put("600277", "亿利洁能");//402	化学原料	2.51	218.27	3.73	价格区间: 5日:58.33 	10日:19.21 	20日:11.93 	30日:28.43 	60日:44.70 	120日:57.20 	250日:64.27 		,当前价：6.13	,当前：2022-04-06 14:34:20
        qiTi_qingNengYuan.put("000980", "*ST众泰 ");//392	汽车整车	1.28	200.74	2.38	价格区间: 5日:61.76 	10日:69.23 	20日:62.96 	30日:38.06 	60日:14.33 	120日:7.85  	250日:11.91 		,当前价：3.96	,当前：2022-04-06 14:34:20
        qiTi_qingNengYuan.put("600860", "京城股份");//378	通用设备	2.67	97.05	2.55	价格区间: 5日:100.00	10日:44.51 	20日:60.97 	30日:61.94 	60日:59.88 	120日:67.92 	250日:69.51 		,当前价：20.01	,当前：2022-04-06 14:34:22
        qiTi_qingNengYuan.put("603938", "三孚股份");//364	化肥行业	-0.9	96.69	1.81	价格区间: 5日:10.24 	10日:45.90 	20日:63.07 	30日:68.71 	60日:75.89 	120日:68.80 	250日:45.12 		,当前价：49.53	,当前：2022-04-06 14:34:23
        qiTi_qingNengYuan.put("002060", "粤水电  ");//356	工程建设	4.29	114.09	3.68	价格区间: 5日:82.23 	10日:86.77 	20日:91.42 	30日:91.70 	60日:92.18 	120日:92.28 	250日:93.78 		,当前价：9.49	,当前：2022-04-06 14:34:24
        qiTi_qingNengYuan.put("600141", "兴发集团");//347	化肥行业	-0.09	371.98	1.61	价格区间: 5日:11.59 	10日:14.81 	20日:9.00  	30日:6.33  	60日:29.81 	120日:14.80 	250日:44.68 		,当前价：33.46	,当前：2022-04-06 14:34:25
        qiTi_qingNengYuan.put("600032", "浙江新能");//341	电力行业	-1   	289.54	1.63	价格区间: 5日:28.31 	10日:34.68 	20日:56.01 	30日:56.01 	60日:56.01 	120日:36.38 	250日:62.56 		,当前价：13.92	,当前：2022-04-06 14:34:26
        qiTi_qingNengYuan.put("002639", "雪人股份");//332	通用设备	2.99	87.92	2   	价格区间: 5日:91.96 	10日:26.75 	20日:50.09 	30日:50.09 	60日:30.59 	120日:43.98 	250日:48.84 		,当前价：11.38	,当前：2022-04-06 14:34:27
    }
    /**
     * qiTi_haiQi ["氦气概念"]：3;
     */
    public static Map<String, String> qiTi_haiQi = new HashMap<>();

    static {
        qiTi_haiQi.put("002639", "雪人股份");//380	通用设备     5.71	94.33	null	价格区间: 5日:47.14 	10日:48.25 	20日:39.48 	30日:58.76 	60日:54.52 	120日:47.71 	250日:50.25 		,当前价：12.21	,当前：2022-04-19 11:12:48
        qiTi_haiQi.put("002549", "凯美特气");//265	化学制品     0.87	86.82	null	价格区间: 5日:33.33 	10日:19.87 	20日:14.18 	30日:14.18 	60日:12.27 	120日:32.84 	250日:58.10 		,当前价：13.92	,当前：2022-04-19 11:12:49
        qiTi_haiQi.put("002430", "杭氧股份");//215	专用设备     -0.63	265.05	null	价格区间: 5日:83.98 	10日:58.02 	20日:38.72 	30日:40.79 	60日:30.18 	120日:41.15 	250日:21.68 		,当前价：26.95	,当前：2022-04-19 11:12:50
    }

    /**
     * huaGong_huaGongYuanLiao ["化工原料"]：44;
     */
    public static Map<String, String> huaGong_huaGongYuanLiao = new HashMap<>();

    static {
        huaGong_huaGongYuanLiao.put("000422", "湖北宜化");//466	化学原料     0.66	204.62	null	价格区间: 5日:42.31 	10日:45.24 	20日:66.91 	30日:72.49 	60日:73.74 	120日:34.62 	250日:60.53 		,当前价：22.79	,当前：2022-04-19 10:31:00
        huaGong_huaGongYuanLiao.put("600096", "云天化  ");//452	化肥行业     0.99	522.50	null	价格区间: 5日:52.65 	10日:57.24 	20日:73.66 	30日:76.53 	60日:86.08 	120日:79.19 	250日:69.82 		,当前价：28.46	,当前：2022-04-19 10:31:01
        huaGong_huaGongYuanLiao.put("600389", "江山股份");//416	农药兽药     9.99	169.97	null	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:100.00	120日:100.00	250日:81.87 		,当前价：57.23	,当前：2022-04-19 10:31:03
        huaGong_huaGongYuanLiao.put("000683", "远兴能源");//411	化学原料     2.82	374.69	null	价格区间: 5日:63.78 	10日:63.78 	20日:71.26 	30日:75.69 	60日:75.69 	120日:84.34 	250日:69.20 		,当前价：10.2	,当前：2022-04-19 10:31:04
        huaGong_huaGongYuanLiao.put("600141", "兴发集团");//376	化肥行业     1.36	413.12	null	价格区间: 5日:38.24 	10日:66.20 	20日:67.02 	30日:64.67 	60日:62.57 	120日:31.06 	250日:53.19 		,当前价：37.16	,当前：2022-04-19 10:31:06
        huaGong_huaGongYuanLiao.put("600596", "新安股份");//338	农药兽药     5.85	238.48	null	价格区间: 5日:91.42 	10日:91.88 	20日:93.16 	30日:95.42 	60日:96.89 	120日:64.31 	250日:49.82 		,当前价：29.14	,当前：2022-04-19 10:31:07
    }

    /**
     * huaGong_taiBaiFen ["钛白粉"]：8;
     */
    public static Map<String, String> huaGong_taiBaiFen = new HashMap<>();

    static {
        huaGong_taiBaiFen.put("002386", "天原股份");//258	化学原料     3.28	78.63	null	价格区间: 5日:78.89 	10日:58.68 	20日:44.94 	30日:31.70 	60日:20.64 	120日:14.26 	250日:31.19 		,当前价：10.07	,当前：2022-04-19 10:15:48
        huaGong_taiBaiFen.put("603067", "振华股份");//245	化学原料     4.11	57.96	null	价格区间: 5日:75.26 	10日:78.38 	20日:44.16 	30日:32.71 	60日:25.62 	120日:31.24 	250日:60.72 		,当前价：11.4	,当前：2022-04-19 10:15:49
        huaGong_taiBaiFen.put("603826", "坤彩科技");//232	非金属材料   0.69	309.35	null	价格区间: 5日:53.96 	10日:41.06 	20日:64.16 	30日:66.73 	60日:76.45 	120日:76.76 	250日:78.67 		,当前价：66.1	,当前：2022-04-19 10:15:50
        huaGong_taiBaiFen.put("600727", "鲁北化工");//232	化学原料     3.69	55.03	null	价格区间: 5日:66.67 	10日:75.42 	20日:83.52 	30日:85.50 	60日:85.50 	120日:39.58 	250日:38.40 		,当前价：10.41	,当前：2022-04-19 10:15:52
        huaGong_taiBaiFen.put("000629", "攀钢钒钛");//218	钢铁行业     1.08	322.61	null	价格区间: 5日:31.03 	10日:39.39 	20日:34.21 	30日:32.86 	60日:27.06 	120日:35.34 	250日:53.72 		,当前价：3.75	,当前：2022-04-19 10:15:52
    }

    /**
     * huaGong_youJiGui ["有机硅"]：11;
     */
    public static Map<String, String> huaGong_youJiGui = new HashMap<>();

    static {
        huaGong_youJiGui.put("603938", "三孚股份");//377	化肥行业     1.5 	93.57	null	价格区间: 5日:80.75 	10日:36.87 	20日:36.57 	30日:53.52 	60日:69.66 	120日:68.40 	250日:41.67 		,当前价：47.93	,当前：2022-04-19 10:25:20
        huaGong_youJiGui.put("600141", "兴发集团");//376	化肥行业     1.36	413.12	null	价格区间: 5日:40.79 	10日:67.60 	20日:68.38 	30日:65.99 	60日:63.34 	120日:31.44 	250日:53.38 		,当前价：37.16	,当前：2022-04-19 10:25:21
        huaGong_youJiGui.put("600596", "新安股份");//338	农药兽药     5.85	238.48	null	价格区间: 5日:88.12 	10日:88.75 	20日:90.53 	30日:93.66 	60日:95.69 	120日:63.51 	250日:49.54 		,当前价：29.14	,当前：2022-04-19 10:25:22
    }

    /**
     * huaGong_foHuaGong ["氟化工"]：21;
     */
    public static Map<String, String> huaGong_foHuaGong = new HashMap<>();

    static {
        huaGong_foHuaGong.put("600096", "云天化  ");//464	化肥行业     6.08	518.64	6.68	价格区间: 5日:89.25 	10日:92.71 	20日:93.84 	30日:93.84 	60日:96.69 	120日:78.05 	250日:70.11 		,当前价：28.25	,当前：2022-04-07 11:36:33
        huaGong_foHuaGong.put("002326", "永太科技");//455	化学制品     0.16	323.98	1.48	价格区间: 5日:15.81 	10日:33.07 	20日:64.07 	30日:64.07 	60日:44.16 	120日:19.22 	250日:38.76 		,当前价：36.96	,当前：2022-04-07 11:36:35
        huaGong_foHuaGong.put("002738", "中矿资源");//424	小金属       -0.32	285.03	1.61	价格区间: 5日:11.83 	10日:7.91  	20日:7.08  	30日:4.92  	60日:56.27 	120日:59.16 	250日:72.67 		,当前价：87.52	,当前：2022-04-07 11:36:37
        huaGong_foHuaGong.put("600141", "兴发集团");//353	化肥行业     2.68	379.65	2.22	价格区间: 5日:44.58 	10日:33.60 	20日:27.85 	30日:14.35 	60日:35.82 	120日:17.78 	250日:46.24 		,当前价：34.15	,当前：2022-04-07 11:36:39
        huaGong_foHuaGong.put("002759", "天际股份");//350	家电行业     0.3 	106.89	2.61	价格区间: 5日:8.81  	10日:6.42  	20日:42.52 	30日:42.52 	60日:42.52 	120日:19.52 	250日:34.74 		,当前价：26.58	,当前：2022-04-07 11:36:41
    }

    /**
     * huaGong_linHuaGong ["磷化工"]：26;
     */
    public static Map<String, String> huaGong_linHuaGong = new HashMap<>();

    static {
        huaGong_linHuaGong.put("000422", "湖北宜化");//466	化学原料     0.66	204.62	null	价格区间: 5日:43.36 	10日:45.95 	20日:67.34 	30日:72.85 	60日:74.09 	120日:34.78 	250日:60.63 		,当前价：22.79	,当前：2022-04-19 10:13:30
        huaGong_linHuaGong.put("600096", "云天化  ");//452	化肥行业     0.99	522.50	null	价格区间: 5日:57.63 	10日:60.80 	20日:75.86 	30日:78.48 	60日:87.24 	120日:80.25 	250日:70.35 		,当前价：28.46	,当前：2022-04-19 10:13:31
        huaGong_linHuaGong.put("002895", "川恒股份");//388	化肥行业     5.86	126.26	null	价格区间: 5日:79.70 	10日:90.09 	20日:90.52 	30日:91.91 	60日:80.23 	120日:25.26 	250日:38.75 		,当前价：25.85	,当前：2022-04-19 10:13:33
        huaGong_linHuaGong.put("600141", "兴发集团");//376	化肥行业     1.36	413.12	null	价格区间: 5日:41.36 	10日:67.91 	20日:68.68 	30日:66.28 	60日:63.51 	120日:31.53 	250日:53.43 		,当前价：37.16	,当前：2022-04-19 10:13:34
        huaGong_linHuaGong.put("600596", "新安股份");//338	农药兽药     5.85	238.48	null	价格区间: 5日:83.83 	10日:84.69 	20日:87.11 	30日:91.37 	60日:94.13 	120日:62.48 	250日:49.18 		,当前价：29.14	,当前：2022-04-19 10:13:35
        huaGong_linHuaGong.put("600691", "阳煤化工");//326	化肥行业     2.1 	115.71	null	价格区间: 5日:32.47 	10日:40.91 	20日:58.40 	30日:67.90 	60日:67.90 	120日:48.89 	250日:39.02 		,当前价：4.87	,当前：2022-04-19 10:13:36
    }

    /**
     * huaGong 化工
     */
    public static Map<String, String> huaGong = new HashMap<>();

    static {
        huaGong.putAll(huaGong_linHuaGong);//磷化工
        huaGong.putAll(huaGong_taiBaiFen);//钛白粉
        huaGong.putAll(huaGong_youJiGui);//有机硅
        huaGong.putAll(huaGong_huaGongYuanLiao);//化工原料
        huaGong.putAll(huaGong_foHuaGong);//氟化工
    }

    /**
     * nongYe_shuiChanYangZhi ["水产养殖"]：6;
     */
    public static Map<String, String> nongYe_shuiChanYangZhi = new HashMap<>();

    static {
        nongYe_shuiChanYangZhi.put("001313", "粤海饲料");//345	农牧饲渔     8.47	116.55	18.11	价格区间: 5日:84.80 	10日:84.80 	20日:47.71 	30日:47.24 	60日:69.37 	120日:69.37 	250日:69.37 		,当前价：16.65	,当前：2022-04-20 11:09:26
        nongYe_shuiChanYangZhi.put("002385", "大北农  ");//268	农牧饲渔     0.11	379.34	2.45	价格区间: 5日:76.30 	10日:78.52 	20日:80.25 	30日:85.52 	60日:67.74 	120日:45.65 	250日:53.70 		,当前价：9.16	,当前：2022-04-20 11:09:27
        nongYe_shuiChanYangZhi.put("600438", "通威股份");//249	光伏设备     -2.08	1761.01	1.79	价格区间: 5日:37.35 	10日:25.81 	20日:12.85 	30日:11.82 	60日:28.90 	120日:13.82 	250日:29.09 		,当前价：39.12	,当前：2022-04-20 11:09:28
    }
    /**
     * 农业-转基因 ["转基因"]：8;
     */
    public static Map<String, String> nongYe_zhuanJiYin = new HashMap<>();
    static {
        nongYe_zhuanJiYin.put("600313", "农发种业");//414	农牧饲渔     8.35	115.15	9.97	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:100.00	120日:100.00	250日:100.00		,当前价：10.64	,当前：2022-04-20 10:02:37
        nongYe_zhuanJiYin.put("600596", "新安股份");//337	农药兽药     -0.58	237.91	5.42	价格区间: 5日:87.25 	10日:87.25 	20日:89.82 	30日:93.17 	60日:95.35 	120日:67.88 	250日:49.54 		,当前价：29.07	,当前：2022-04-20 10:02:38
        nongYe_zhuanJiYin.put("002041", "登海种业");//284	农牧饲渔     -1.33	221.14	2.91	价格区间: 5日:86.45 	10日:89.39 	20日:90.30 	30日:94.03 	60日:94.03 	120日:71.36 	250日:80.15 		,当前价：25.13	,当前：2022-04-20 10:02:40
        nongYe_zhuanJiYin.put("002385", "大北农  ");//268	农牧饲渔     -1.75	372.30	2.45	价格区间: 5日:91.06 	10日:91.97 	20日:92.67 	30日:94.74 	60日:70.97 	120日:47.83 	250日:55.56 		,当前价：8.99	,当前：2022-04-20 10:02:41
        nongYe_zhuanJiYin.put("002100", "天康生物");//267	农牧饲渔     0.68	160.19	10.19	价格区间: 5日:38.17 	10日:38.67 	20日:29.00 	30日:52.67 	60日:54.92 	120日:70.35 	250日:77.28 		,当前价：11.83	,当前：2022-04-20 10:02:42
    }

    /**
     * nongYe_caoGanLin ["草甘膦"]：10;
     */
    public static Map<String, String> nongYe_caoGanLin = new HashMap<>();

    static {
        nongYe_caoGanLin.put("600389", "江山股份");//416	农药兽药     9.99	169.97	null	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:100.00	120日:100.00	250日:81.87 		,当前价：57.23	,当前：2022-04-19 09:51:12
        nongYe_caoGanLin.put("600141", "兴发集团");//370	化肥行业     -2.43	397.66	null	价格区间: 5日:41.08 	10日:67.75 	20日:68.53 	30日:66.13 	60日:63.42 	120日:31.53 	250日:53.43 		,当前价：35.77	,当前：2022-04-19 09:51:13
        nongYe_caoGanLin.put("600596", "新安股份");//326	农药兽药     2.62	231.20	null	价格区间: 5日:98.68 	10日:98.75 	20日:98.95 	30日:99.30 	60日:99.52 	120日:66.06 	250日:50.43 		,当前价：28.25	,当前：2022-04-19 09:51:15
        nongYe_caoGanLin.put("603077", "和邦生物");//263	化学原料     1.37	327.64	null	价格区间: 5日:54.55 	10日:75.61 	20日:78.26 	30日:70.00 	60日:49.49 	120日:55.36 	250日:61.45 		,当前价：3.71	,当前：2022-04-19 09:51:16
    }

    /**
     * nongYe_zhuRou ["猪肉概念"]：26;
     */
    public static Map<String, String> nongYe_zhuRou = new HashMap<>();

    static {
        nongYe_zhuRou.put("603363", "傲农生物");//402	农牧饲渔     3.15	189.86	null	价格区间: 5日:97.50 	10日:97.50 	20日:98.00 	30日:98.62 	60日:98.83 	120日:99.14 	250日:99.23 		,当前价：27.49	,当前：2022-04-19 10:42:32
        nongYe_zhuRou.put("603477", "XD巨星农");//380	农牧饲渔     1.89	133.81	null	价格区间: 5日:89.78 	10日:89.78 	20日:67.70 	30日:79.67 	60日:84.61 	120日:88.73 	250日:90.11 		,当前价：26.44	,当前：2022-04-19 10:42:34
        nongYe_zhuRou.put("002124", "天邦股份");//273	农牧饲渔     0.64	172.88	null	价格区间: 5日:88.48 	10日:89.08 	20日:93.58 	30日:94.92 	60日:94.93 	120日:94.93 	250日:72.57 		,当前价：9.4	,当前：2022-04-19 10:42:35
        nongYe_zhuRou.put("002100", "天康生物");//268	农牧饲渔     0.58	163.71	null	价格区间: 5日:83.21 	10日:73.58 	20日:58.50 	30日:72.33 	60日:73.65 	120日:82.67 	250日:86.72 		,当前价：12.09	,当前：2022-04-19 10:42:36
        nongYe_zhuRou.put("002385", "大北农  ");//262	农牧饲渔     1.82	369.82	null	价格区间: 5日:93.52 	10日:94.26 	20日:95.21 	30日:96.39 	60日:67.38 	120日:45.41 	250日:53.50 		,当前价：8.93	,当前：2022-04-19 10:42:37
    }


    /**
     * nongYe_shengTaiNongYe ["生态农业"]：21;
     */
    public static Map<String, String> nongYe_shengTaiNongYe = new HashMap<>();

    static {
        nongYe_shengTaiNongYe.put("000893", "亚钾国际");//468	化肥行业     2.71	303.97	null	价格区间: 5日:34.52 	10日:56.66 	20日:74.64 	30日:79.97 	60日:83.85 	120日:85.91 	250日:88.42 		,当前价：40.16	,当前：2022-04-19 10:45:26
        nongYe_shengTaiNongYe.put("002539", "云图控股");//315	化肥行业     2.73	155.66	null	价格区间: 5日:72.54 	10日:75.19 	20日:81.07 	30日:84.24 	60日:84.24 	120日:58.54 	250日:46.81 		,当前价：15.41	,当前：2022-04-19 10:45:28
        nongYe_shengTaiNongYe.put("002470", "ST金正  ");//313	化肥行业     5.1 	101.54	null	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:100.00	120日:84.40 	250日:82.86 		,当前价：3.09	,当前：2022-04-19 10:45:29
        nongYe_shengTaiNongYe.put("000505", "京粮控股");//308	农牧饲渔     0.26	83.09	null	价格区间: 5日:72.31 	10日:75.00 	20日:62.30 	30日:67.19 	60日:69.00 	120日:69.13 	250日:69.40 		,当前价：11.43	,当前：2022-04-19 10:45:31
        nongYe_shengTaiNongYe.put("002746", "仙坛股份");//276	农牧饲渔     8.58	86.05	null	价格区间: 5日:100.00	10日:96.39 	20日:96.39 	30日:96.67 	60日:55.16 	120日:68.33 	250日:68.98 		,当前价：10.0	,当前：2022-04-19 10:45:32
    }

    /**
     * nongYe_huangChongFangZhi ["蝗虫防治"]：10;
     */
    public static Map<String, String> nongYe_huangChongFangZhi = new HashMap<>();

    static {
        nongYe_huangChongFangZhi.put("600389", "江山股份");//416	农药兽药     9.99	169.97	null	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:100.00	120日:100.00	250日:81.87 		,当前价：57.23	,当前：2022-04-19 09:54:22
        nongYe_huangChongFangZhi.put("000009", "中国宝安");//276	综合行业     0.87	267.98	null	价格区间: 5日:79.17 	10日:36.08 	20日:22.35 	30日:16.86 	60日:10.94 	120日:4.59  	250日:4.96  		,当前价：10.39	,当前：2022-04-19 09:54:24
        nongYe_huangChongFangZhi.put("603639", "海利尔  ");//224	农药兽药     -0.22	76.99	null	价格区间: 5日:93.53 	10日:95.92 	20日:97.07 	30日:97.65 	60日:65.79 	120日:57.85 	250日:63.53 		,当前价：22.59	,当前：2022-04-19 09:54:25
        nongYe_huangChongFangZhi.put("000713", "丰乐种业");//211	农牧饲渔     -0.21	58.76	null	价格区间: 5日:52.87 	10日:35.38 	20日:54.10 	30日:67.44 	60日:67.44 	120日:67.44 	250日:59.79 		,当前价：9.57	,当前：2022-04-19 09:54:26
    }

    /**
     * nongYe_nongYeZhongZhi ["农业种植"]：30;
     */
    public static Map<String, String> nongYe_nongYeZhongZhi = new HashMap<>();

    static {
        nongYe_nongYeZhongZhi.put("002326", "永太科技");//428	化学制品     -0.29	272.26	null	价格区间: 5日:44.77 	10日:15.70 	20日:11.52 	30日:28.09 	60日:21.89 	120日:8.42  	250日:30.58 		,当前价：31.06	,当前：2022-04-19 10:46:53
        nongYe_nongYeZhongZhi.put("600313", "农发种业");//368	农牧饲渔     2.13	98.70	null	价格区间: 5日:87.95 	10日:90.25 	20日:93.65 	30日:94.49 	60日:94.52 	120日:94.67 	250日:95.11 		,当前价：9.12	,当前：2022-04-19 10:46:54
        nongYe_nongYeZhongZhi.put("000937", "冀中能源");//353	煤炭行业     5.21	271.38	null	价格区间: 5日:39.57 	10日:41.26 	20日:56.02 	30日:67.32 	60日:75.72 	120日:75.72 	250日:59.81 		,当前价：7.68	,当前：2022-04-19 10:46:55
    }

    /**
     * nongYe 农业:转基因,草甘膦,水产养殖,磷化工,鸡肉概念,猪肉概念,生态农业
     */
    public static Map<String, String> nongYe = new HashMap<>();

    static {
        nongYe.putAll(nongYe_zhuanJiYin);//转基因
        nongYe.putAll(nongYe_caoGanLin);//草甘膦
        nongYe.putAll(nongYe_shuiChanYangZhi);//水产养殖
        nongYe.putAll(nongYe_zhuRou);//猪肉概念
        nongYe.putAll(nongYe_shengTaiNongYe);//生态农业
        nongYe.putAll(nongYe_huangChongFangZhi);//蝗虫防治
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
        all.putAll(qiTi_qingNengYuan);//氢能源
        all.putAll(qiTi_haiQi);//氦气概念
        all.putAll(meiTan);//煤
        all.putAll(huaGong);//化工
        all.putAll(nongYe); //农业:转基因,草甘膦,水产养殖,磷化工
    }

    public static void main(String[] args) {
        String spDate = null;
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//                String date = "2022-04-18";
//                 spDate = "2022-04-19";//特定日期：下一交易日
        KlineService.checkMaDemo(all, date,spDate);// all   huaGong   huaGong_linHuaGong   nongYe_caoGanLin
    }
}
