package utils;

import ttjj.service.KlineService;
import ttjj.stat.StBizStatDemo;
import ttjj.stat.StockStatDemo;

import java.util.HashMap;
import java.util.Map;

/**
 * ContMapStZiYuan简介-股票分类数据：金融
 *
 * @author Administrator
 * @date 2022-03-18 10:09
 */
public class ContMapStJinRong {

    /**
     * guiJinShu_peiYuZuanShi ["培育钻石"]：4;
     */
    public static Map<String, String> guiJinShu_peiYuZuanShi = new HashMap<>();

    static {
        guiJinShu_peiYuZuanShi.put("600172", "黄河旋风");//386	非金属材料	1.76	133.26	4.64	价格区间: 5日:97.95 	10日:97.95 	20日:98.20 	30日:64.57 	60日:73.21 	120日:44.97 	250日:68.90 		,当前价：9.24	,当前：2022-04-01 10:18:30
        guiJinShu_peiYuZuanShi.put("000519", "中兵红箭");//385	专用设备	3.91	325.58	8.19	价格区间: 5日:89.29 	10日:89.60 	20日:93.67 	30日:93.38 	60日:62.92 	120日:42.56 	250日:63.69 		,当前价：23.38	,当前：2022-04-01 10:18:31
    }

    /**
     * guiJinShu_huangJin ["黄金概念"]：36;
     */
    public static Map<String, String> guiJinShu_huangJin = new HashMap<>();

    static {
        guiJinShu_huangJin.put("000546", "金圆股份");//384	水泥建材     -2.33	121.33	2.43	价格区间: 5日:56.32 	10日:64.07 	20日:56.25 	30日:37.57 	60日:51.24 	120日:66.70 	250日:75.45 		,当前价：15.54	,当前：2022-04-08 11:52:48
        guiJinShu_huangJin.put("600596", "新安股份");//333	农药兽药     4.99	232.34	2.3 	价格区间: 5日:99.23 	10日:76.95 	20日:85.16 	30日:85.09 	60日:90.13 	120日:54.56 	250日:47.13 		,当前价：28.39	,当前：2022-04-08 11:52:49
        guiJinShu_huangJin.put("000688", "国城矿业");//317	有色金属     0.13	177.42	1.14	价格区间: 5日:17.57 	10日:13.83 	20日:39.30 	30日:28.26 	60日:46.44 	120日:59.98 	250日:63.61 		,当前价：15.6	,当前：2022-04-08 11:52:51
        guiJinShu_huangJin.put("000009", "中国宝安");//290	综合行业     -2.14	271.59	1.29	价格区间: 5日:12.67 	10日:10.61 	20日:9.36  	30日:4.51  	60日:4.08  	120日:1.61  	250日:9.17  		,当前价：10.53	,当前：2022-04-08 11:52:52
        guiJinShu_huangJin.put("601020", "ST华钰  ");//289	有色金属     -0.5	100.38	0.92	价格区间: 5日:81.20 	10日:86.94 	20日:89.93 	30日:89.93 	60日:94.07 	120日:94.07 	250日:96.46 		,当前价：17.94	,当前：2022-04-08 11:52:53
        guiJinShu_huangJin.put("000630", "铜陵有色");//272	有色金属     -0.52	403.17	1.25	价格区间: 5日:88.89 	10日:80.00 	20日:88.41 	30日:51.69 	60日:51.69 	120日:45.77 	250日:40.38 		,当前价：3.83	,当前：2022-04-08 11:52:54
        guiJinShu_huangJin.put("603979", "金诚信  ");//264	采掘行业     4.15	128.30	2.58	价格区间: 5日:88.57 	10日:71.70 	20日:79.70 	30日:48.83 	60日:47.01 	120日:52.86 	250日:50.49 		,当前价：21.6	,当前：2022-04-08 11:52:55
        guiJinShu_huangJin.put("600988", "赤峰黄金");//264	贵金属       9.95	347.42	3.74	价格区间: 5日:95.15 	10日:95.15 	20日:97.34 	30日:97.41 	60日:97.97 	120日:97.97 	250日:97.97 		,当前价：20.88	,当前：2022-04-08 11:52:56
        guiJinShu_huangJin.put("600490", "鹏欣资源");//263	有色金属     1.19	94.27	1.29	价格区间: 5日:51.61 	10日:20.51 	20日:10.06 	30日:7.58  	60日:7.58  	120日:6.72  	250日:11.07 		,当前价：4.26	,当前：2022-04-08 11:52:57
        guiJinShu_huangJin.put("002155", "湖南黄金");//255	贵金属       4.96	134.87	3.49	价格区间: 5日:85.06 	10日:66.67 	20日:57.39 	30日:40.62 	60日:45.33 	120日:45.33 	250日:37.22 		,当前价：11.22	,当前：2022-04-08 11:52:57
        guiJinShu_huangJin.put("600089", "特变电工");//251	电网设备     -1.9	744.76	1.64	价格区间: 5日:17.91 	10日:20.86 	20日:13.62 	30日:42.14 	60日:48.17 	120日:18.06 	250日:47.00 		,当前价：19.65	,当前：2022-04-08 11:52:58
        guiJinShu_huangJin.put("600711", "盛屯矿业");//245	能源金属     -1.8	231.66	1.49	价格区间: 5日:26.19 	10日:14.10 	20日:10.38 	30日:5.99  	60日:5.99  	120日:5.30  	250日:22.33 		,当前价：8.2	,当前：2022-04-08 11:52:59
    }

    /**
     * quanShangGaiNian 概念：["券商概念"];股票个数：51;
     */
    public static Map<String, String> quanShangGaiNian = new HashMap<>();

    static {
        quanShangGaiNian.put("002945", "华林证券");//270	证券    	-0.55	389.61	1.34	价格区间: 5日:21.01 	10日:7.31  	20日:7.07  	30日:11.80 	60日:32.97 	120日:46.77 	250日:46.77 		,当前价：14.43	,当前：2022-04-01 13:41:07
        quanShangGaiNian.put("600906", "财达证券");//245	证券    	1.19	358.57	1.32	价格区间: 5日:64.62 	10日:32.56 	20日:28.04 	30日:28.04 	60日:22.81 	120日:17.75 	250日:46.01 		,当前价：11.05	,当前：2022-04-01 13:41:09
        quanShangGaiNian.put("600958", "东方证券");//215	证券    	1.55	779.79	1.39	价格区间: 5日:85.90 	10日:85.90 	20日:62.16 	30日:37.40 	60日:18.59 	120日:13.63 	250日:24.93 		,当前价：11.15	,当前：2022-04-01 13:41:10
        quanShangGaiNian.put("000776", "广发证券");//212	证券    	2.05	1367.22	2.61	价格区间: 5日:68.64 	10日:68.64 	20日:79.22 	30日:60.84 	60日:21.72 	120日:17.78 	250日:29.08 		,当前价：17.94	,当前：2022-04-01 13:41:11
        quanShangGaiNian.put("002939", "长城证券");//196	证券    	1.16	298.86	1.46	价格区间: 5日:86.96 	10日:57.14 	20日:28.78 	30日:19.14 	60日:9.83  	120日:7.52  	250日:10.05 		,当前价：9.63	,当前：2022-04-01 13:41:13
        quanShangGaiNian.put("601456", "国联证券");//195	证券    	0.08	374.08	1.26	价格区间: 5日:40.00 	10日:40.87 	20日:27.32 	30日:16.21 	60日:12.77 	120日:26.27 	250日:21.54 		,当前价：13.21	,当前：2022-04-01 13:41:14
        quanShangGaiNian.put("601696", "中银证券");//190	证券    	5.67	393.36	3.55	价格区间: 5日:85.89 	10日:85.89 	20日:80.65 	30日:80.65 	60日:80.65 	120日:76.81 	250日:32.15 		,当前价：14.16	,当前：2022-04-01 13:41:15
        quanShangGaiNian.put("601995", "中金公司");//186	证券    	1.85	2045.79	2.37	价格区间: 5日:89.32 	10日:89.32 	20日:69.63 	30日:69.63 	60日:45.73 	120日:28.91 	250日:21.39 		,当前价：42.38	,当前：2022-04-01 13:41:16
    }

    /**
     * jianZhuJieNeng ["建筑节能"]：6;
     */
    public static Map<String, String> fangDiChan_jianZhuJieNeng = new HashMap<>();

    static {
        fangDiChan_jianZhuJieNeng.put("002761", "浙江建投");//465	工程建设	-4.58	409.61	10.56	价格区间: 5日:67.55 	10日:83.76 	20日:86.13 	30日:89.10 	60日:89.23 	120日:89.28 	250日:89.32 		,当前价：37.88	,当前：2022-03-22 11:00:58
        fangDiChan_jianZhuJieNeng.put("002810", "山东赫达");//263	化学制品	-2.39	145.29	1.42	价格区间: 5日:24.54 	10日:15.54 	20日:7.84  	30日:7.84  	60日:5.61  	120日:19.41 	250日:35.45 		,当前价：42.41	,当前：2022-03-22 11:00:59
        fangDiChan_jianZhuJieNeng.put("000517", "荣安地产");//237	房地产开发	7.4 	106.34	66.03	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:100.00	120日:100.00	250日:100.00		,当前价：3.34	,当前：2022-03-22 11:01:00
        fangDiChan_jianZhuJieNeng.put("600586", "金晶科技");//235	玻璃玻纤	-0.95	118.59	3.11	价格区间: 5日:82.14 	10日:82.14 	20日:62.84 	30日:52.04 	60日:51.80 	120日:20.43 	250日:28.61 		,当前价：8.3	,当前：2022-03-22 11:01:01
    }

    /**
     * fangDiChan_zuShouTongQuan ["租售同权"]：14;
     */
    public static Map<String, String> fangDiChan_zuShouTongQuan = new HashMap<>();

    static {
        fangDiChan_zuShouTongQuan.put("000732", "泰禾集团");//348	房地产开发   -9.94	78.90	3.65	价格区间: 5日:15.52 	10日:50.00 	20日:59.50 	30日:59.50 	60日:68.18 	120日:74.07 	250日:74.07 		,当前价：3.17	,当前：2022-04-07 13:39:50
        fangDiChan_zuShouTongQuan.put("000671", "阳光城  ");//343	房地产开发   2.51	152.37	3.22	价格区间: 5日:36.79 	10日:38.53 	20日:72.20 	30日:72.20 	60日:72.20 	120日:72.20 	250日:42.44 		,当前价：3.68	,当前：2022-04-07 13:39:51
        fangDiChan_zuShouTongQuan.put("000056", "皇庭国际");//316	房地产服务   -3.41	93.14	2.35	价格区间: 5日:3.28  	10日:47.56 	20日:65.29 	30日:61.67 	60日:61.67 	120日:77.96 	250日:79.46 		,当前价：7.93	,当前：2022-04-07 13:39:52
        fangDiChan_zuShouTongQuan.put("002285", "世联行  ");//310	房地产服务   -10 	82.36	2.85	价格区间: 5日:5.32  	10日:23.93 	20日:58.02 	30日:58.02 	60日:58.02 	120日:58.02 	250日:21.69 		,当前价：4.05	,当前：2022-04-07 13:39:53
        fangDiChan_zuShouTongQuan.put("600048", "保利发展");//253	房地产开发   -1.97	2147.47	1.77	价格区间: 5日:40.28 	10日:45.57 	20日:75.48 	30日:75.48 	60日:75.48 	120日:81.17 	250日:86.90 		,当前价：17.94	,当前：2022-04-07 13:39:55
        fangDiChan_zuShouTongQuan.put("002244", "滨江集团");//252	房地产开发   -2.53	228.07	2.87	价格区间: 5日:54.47 	10日:57.89 	20日:76.57 	30日:76.57 	60日:82.82 	120日:85.93 	250日:86.27 		,当前价：7.33	,当前：2022-04-07 13:39:56
        fangDiChan_zuShouTongQuan.put("000011", "深物业A");//240	房地产开发   -10.01	79.80	5.89	价格区间: 5日:44.59 	10日:55.76 	20日:65.98 	30日:65.98 	60日:65.98 	120日:65.98 	250日:65.98 		,当前价：13.39	,当前：2022-04-07 13:39:57
        fangDiChan_zuShouTongQuan.put("001979", "招商蛇口");//238	房地产开发   -1.47	1270.89	2.33	价格区间: 5日:54.15 	10日:65.44 	20日:79.52 	30日:79.52 	60日:79.52 	120日:87.00 	250日:87.57 		,当前价：16.04	,当前：2022-04-07 13:39:58
        fangDiChan_zuShouTongQuan.put("000002", "万科Ａ  ");//230	房地产开发   -0.66	2450.63	2.8 	价格区间: 5日:74.49 	10日:76.89 	20日:84.87 	30日:84.87 	60日:73.21 	120日:73.21 	250日:39.60 		,当前价：21.08	,当前：2022-04-07 13:39:58
    }

    /**
     * fangDiChan_haiMianChengShi 海绵城市
     */
    public static Map<String, String> fangDiChan_haiMianChengShi = new HashMap<>();

    static {
        fangDiChan_haiMianChengShi.put("002271", "东方雨虹");//265	装修建材     0.15	1141.90	null	价格区间: 5日:33.51 	10日:72.08 	20日:74.75 	30日:73.91 	60日:49.00 	120日:52.68 	250日:31.85 		,当前价：45.32	,当前：2022-04-08 10:01:51
        fangDiChan_haiMianChengShi.put("600502", "安徽建工");//259	工程建设     9.92	97.25	null	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:100.00	120日:100.00	250日:100.00		,当前价：5.65	,当前：2022-04-08 10:01:53
        fangDiChan_haiMianChengShi.put("600512", "腾达建设");//245	工程建设     9.9 	69.23	null	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:100.00	120日:100.00	250日:100.00		,当前价：4.33	,当前：2022-04-08 10:01:54
        fangDiChan_haiMianChengShi.put("002062", "宏润建设");//223	工程建设     5.82	62.18	null	价格区间: 5日:77.05 	10日:81.58 	20日:84.95 	30日:82.29 	60日:55.68 	120日:59.67 	250日:59.67 		,当前价：5.64	,当前：2022-04-08 10:01:55
        fangDiChan_haiMianChengShi.put("002372", "伟星新材");//219	装修建材     2.82	342.62	null	价格区间: 5日:72.30 	10日:83.00 	20日:85.75 	30日:65.86 	60日:46.28 	120日:55.98 	250日:51.27 		,当前价：21.52	,当前：2022-04-08 10:01:56
    }

    /**
     * reits ["REITs概念"]：16;
     */
    public static Map<String, String> fangDiChan_reits = new HashMap<>();

    static {

    }

    /**
     * jianCai_diXiaGuanWang ["地下管网"]：9;
     */
    public static Map<String, String> fangDiChan_diXiaGuanWang = new HashMap<>();

    static {
        fangDiChan_diXiaGuanWang.put("003037", "三和管桩");//281	水泥建材     9.97	64.99	null	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:72.90 	120日:73.81 	250日:27.41 		,当前价：12.9	,当前：2022-04-08 10:06:12
        fangDiChan_diXiaGuanWang.put("002108", "沧州明珠");//267	塑料制品     -1.81	76.85	null	价格区间: 5日:2.70  	10日:1.79  	20日:1.19  	30日:0.54  	60日:0.36  	120日:0.18  	250日:20.58 		,当前价：5.42	,当前：2022-04-08 10:06:14
        fangDiChan_diXiaGuanWang.put("000778", "新兴铸管");//229	钢铁行业     0.95	211.92	null	价格区间: 5日:70.00 	10日:83.52 	20日:86.61 	30日:86.61 	60日:70.80 	120日:80.58 	250日:80.58 		,当前价：5.31	,当前：2022-04-08 10:06:15
        fangDiChan_diXiaGuanWang.put("000708", "中信特钢");//221	钢铁行业     -1.6	1026.59	null	价格区间: 5日:45.28 	10日:56.28 	20日:64.05 	30日:47.33 	60日:36.99 	120日:36.99 	250日:28.44 		,当前价：20.34	,当前：2022-04-08 10:06:16
        fangDiChan_diXiaGuanWang.put("002372", "伟星新材");//219	装修建材     2.82	342.62	null	价格区间: 5日:77.93 	10日:86.46 	20日:88.65 	30日:68.09 	60日:47.85 	120日:57.48 	250日:52.64 		,当前价：21.52	,当前：2022-04-08 10:06:18
        fangDiChan_diXiaGuanWang.put("002062", "宏润建设");//217	工程建设     5.82	62.18	null	价格区间: 5日:73.77 	10日:78.95 	20日:82.80 	30日:80.21 	60日:54.95 	120日:59.00 	250日:59.00 		,当前价：5.64	,当前：2022-04-08 10:06:19
    }

    /**
     * fangDiChan_zhuangPeiJianZhu 装配建筑
     */
    public static Map<String, String> fangDiChan_zhuangPeiJianZhu = new HashMap<>();

    static {
        fangDiChan_zhuangPeiJianZhu.put("002761", "浙江建投");//431	工程建设     2.57	302.23	null	价格区间: 5日:6.02  	10日:5.72  	20日:32.05 	30日:43.27 	60日:58.87 	120日:59.26 	250日:59.39 		,当前价：27.95	,当前：2022-04-08 10:11:10
        fangDiChan_zhuangPeiJianZhu.put("600510", "黑牡丹  ");//360	房地产开发   3.49	120.94	null	价格区间: 5日:50.00 	10日:26.20 	20日:7.78  	30日:10.75 	60日:44.30 	120日:44.30 	250日:48.36 		,当前价：11.55	,当前：2022-04-08 10:11:11
        fangDiChan_zhuangPeiJianZhu.put("601155", "新城控股");//321	房地产开发   -0.56	727.06	null	价格区间: 5日:34.22 	10日:58.42 	20日:75.67 	30日:75.67 	60日:68.28 	120日:57.34 	250日:40.93 		,当前价：32.12	,当前：2022-04-08 10:11:12
        fangDiChan_zhuangPeiJianZhu.put("000498", "山东路桥");//319	工程建设     9.82	148.23	null	价格区间: 5日:77.97 	10日:84.15 	20日:88.13 	30日:88.13 	60日:77.46 	120日:81.13 	250日:83.92 		,当前价：9.51	,当前：2022-04-08 10:11:14
        fangDiChan_zhuangPeiJianZhu.put("002135", "东南网架");//309	工程建设     3.1 	139.52	null	价格区间: 5日:29.29 	10日:25.98 	20日:58.59 	30日:59.66 	60日:65.62 	120日:73.67 	250日:73.26 		,当前价：11.97	,当前：2022-04-08 10:11:15
    }

    /**
     * fangDiChan_gongChengJiXie ["工程机械概念"]：20;
     */
    public static Map<String, String> fangDiChan_gongChengJiXie = new HashMap<>();

    static {
        fangDiChan_gongChengJiXie.put("605305", "中际联合");//313	工程机械     0.04	62.61	null	价格区间: 5日:13.96 	10日:8.94  	20日:5.25  	30日:2.84  	60日:1.82  	120日:1.07  	250日:20.19 		,当前价：56.92	,当前：2022-04-08 11:10:04
        fangDiChan_gongChengJiXie.put("600375", "汉马科技");//236	工程机械     -0.79	57.32	null	价格区间: 5日:8.55  	10日:5.95  	20日:18.18 	30日:18.18 	60日:14.87 	120日:15.58 	250日:30.41 		,当前价：8.76	,当前：2022-04-08 11:10:05
    }

    /**
     * fangDiChan_minBao 民爆概念
     */
    public static Map<String, String> fangDiChan_minBao = new HashMap<>();

    static {
        fangDiChan_minBao.put("002497", "雅化集团");//284	化学制品     -0.98	337.24	1.59	价格区间: 5日:39.55 	10日:27.02 	20日:19.29 	30日:9.16  	60日:43.37 	120日:43.37 	250日:46.35 		,当前价：29.26	,当前：2022-04-08 11:42:45
        fangDiChan_minBao.put("002037", "保利联合");//245	化学制品     6.92	69.29	3.04	价格区间: 5日:86.00 	10日:88.10 	20日:89.86 	30日:75.24 	60日:57.14 	120日:62.22 	250日:62.61 		,当前价：14.21	,当前：2022-04-08 11:42:47
    }

    /**
     * fangDiChan 房地产:租售同权,建筑节能,海绵城市
     */
    public static Map<String, String> fangDiChan = new HashMap<>();

    static {
        fangDiChan.putAll(fangDiChan_zuShouTongQuan);//租售同权
        fangDiChan.putAll(fangDiChan_haiMianChengShi);//海绵城市
        fangDiChan.putAll(fangDiChan_reits);
        fangDiChan.putAll(fangDiChan_jianZhuJieNeng);//建筑节能
        fangDiChan.putAll(fangDiChan_diXiaGuanWang);//地下管网
        fangDiChan.putAll(fangDiChan_zhuangPeiJianZhu);//装配建筑
        fangDiChan.putAll(fangDiChan_gongChengJiXie);//工程机械概念
        fangDiChan.putAll(fangDiChan_minBao);//民爆概念
    }

    /**
     * guiJinShu 贵金属
     */
    public static Map<String, String> guiJinShu = new HashMap<>();

    static {
        guiJinShu.putAll(guiJinShu_peiYuZuanShi);
    }

    /**
     * allZiYuan
     */
    public static Map<String, String> all = new HashMap<>();

    static {
        all.putAll(fangDiChan);//房地产
        all.putAll(quanShangGaiNian);//券商概念
        all.putAll(guiJinShu); //贵金属
    }

    public static void main(String[] args) {
        String spDate = null;
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);//        String date = "2022-03-25";
//        String date = "2022-04-07";
//        spDate = "2022-04-08";//特定日期：下一交易日
        KlineService.checkMaDemo(all, date, spDate);// all   huaGong   nongYe_linHuaGong
    }
}
