package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ContentStockConception简介
 * 常量：股票分类，概念
 *
 * @author Administrator
 * @date 2022-02-25 11:34
 */
public class ContStConception {

    /**
     * mapKeJiXinPian 科技-芯片：["光刻胶,中芯概念,IGBT概念"]：44;
     */
    public static Map<String, String> mapKeJiXinPian = new HashMap<>();

    static {
        mapKeJiXinPian.put("605111", "新洁能  ");//361	半导体  	1.25	280.79	3.86	价格区间: 5日:86.10 	10日:89.79 	20日:92.87 	30日:94.38 	60日:94.38 	120日:62.51 	250日:63.93 		,当前价：196.6	,当前：2022-03-17 09:59:26
        mapKeJiXinPian.put("603290", "斯达半导");//353	半导体  	3.5 	715.69	3.94	价格区间: 5日:80.24 	10日:83.89 	20日:88.29 	30日:89.89 	60日:89.89 	120日:57.39 	250日:72.80 		,当前价：419.5	,当前：2022-03-17 09:59:27
        mapKeJiXinPian.put("002371", "北方华创");//346	半导体  	2.79	1729.58	5.02	价格区间: 5日:82.97 	10日:85.93 	20日:89.05 	30日:91.04 	60日:63.57 	120日:40.68 	250日:60.13 		,当前价：328.93	,当前：2022-03-17 09:59:28
        mapKeJiXinPian.put("600141", "兴发集团");//340	化肥行业	-1.5	380.43	3.1 	价格区间: 5日:34.30 	10日:20.73 	20日:17.54 	30日:38.78 	60日:32.44 	120日:15.96 	250日:47.21 		,当前价：34.22	,当前：2022-03-17 09:59:29
        mapKeJiXinPian.put("600460", "士兰微  ");//316	半导体  	3.39	867.91	6.34	价格区间: 5日:80.82 	10日:83.13 	20日:89.47 	30日:90.09 	60日:90.73 	120日:56.50 	250日:72.55 		,当前价：61.29	,当前：2022-03-17 09:59:30
        mapKeJiXinPian.put("003031", "中瓷电子");//313	通信设备	3.15	150.57	3.67	价格区间: 5日:89.33 	10日:75.83 	20日:48.99 	30日:66.67 	60日:67.22 	120日:77.35 	250日:82.74 		,当前价：100.83	,当前：2022-03-17 09:59:31
        mapKeJiXinPian.put("603650", "彤程新材");//305	橡胶制品	10.01	245.55	15.21	价格区间: 5日:100.00	10日:85.41 	20日:85.10 	30日:87.03 	60日:33.37 	120日:20.90 	250日:23.93 		,当前价：41.12	,当前：2022-03-17 09:59:32
        mapKeJiXinPian.put("002584", "西陇科学");//304	电子化学品	1.37	64.84	2.8 	价格区间: 5日:50.00 	10日:70.10 	20日:75.54 	30日:75.54 	60日:58.26 	120日:66.30 	250日:68.40 		,当前价：11.08	,当前：2022-03-17 09:59:33
        mapKeJiXinPian.put("002049", "紫光国微");//270	半导体  	1.7 	1295.96	3.27	价格区间: 5日:90.33 	10日:59.39 	20日:61.79 	30日:67.99 	60日:52.04 	120日:55.65 	250日:77.01 		,当前价：213.56	,当前：2022-03-17 09:59:40
        mapKeJiXinPian.put("002129", "中环股份");//220	光伏设备	1.12	1487.89	2.76	价格区间: 5日:56.40 	10日:46.86 	20日:42.54 	30日:52.65 	60日:67.60 	120日:51.57 	250日:67.97 		,当前价：46.04	,当前：2022-03-17 09:59:51
        mapKeJiXinPian.put("002594", "比亚迪  ");//284	汽车整车	4.28	6964.91	6.58	价格区间: 5日:87.12 	10日:84.49 	20日:58.08 	30日:58.08 	60日:32.60 	120日:22.11 	250日:49.87 		,当前价：239.25	,当前：2022-03-17 09:59:36
    }

    /**
     * mapKeJiDongYuanYvZhou ["NFT概念,虚拟数字人,云游戏,元宇宙概念"]：44;
     */
    public static Map<String, String> mapKeJiDongYuanYvZhou = new HashMap<>();
    static {
        mapKeJiDongYuanYvZhou.put("002354", "天娱数科");//343	游戏    	-1.1	74.64	3.09	价格区间: 5日:11.11 	10日:8.33  	20日:20.39 	30日:20.39 	60日:10.54 	120日:41.29 	250日:44.51 		,当前价：4.49	,当前：2022-03-16 11:26:37
        mapKeJiDongYuanYvZhou.put("603466", "风语筑");  //325	文化传媒	0.05	83.04	2.62	价格区间: 5日:15.25 	10日:6.56  	20日:6.34  	30日:2.48  	60日:2.19  	120日:24.95 	250日:31.24 		,当前价：19.68	,当前：2022-03-16 11:26:39
        mapKeJiDongYuanYvZhou.put("000810", "创维数字");//323	家电行业	-4.11	129.08	3.34	价格区间: 5日:22.47 	10日:24.87 	20日:37.46 	30日:41.62 	60日:48.80 	120日:61.66 	250日:61.66 		,当前价：12.14	,当前：2022-03-16 11:26:40
        mapKeJiDongYuanYvZhou.put("000681", "视觉中国");//305	文化传媒	-0.51	94.79	3.22	价格区间: 5日:20.86 	10日:14.02 	20日:10.64 	30日:7.94  	60日:3.13  	120日:11.27 	250日:17.91 		,当前价：13.53	,当前：2022-03-16 11:26:41
        mapKeJiDongYuanYvZhou.put("002036", "联创电子");//299	光学光电子	0.21	153.90	9.36	价格区间: 5日:12.05 	10日:8.24  	20日:5.80  	30日:4.12  	60日:2.64  	120日:2.64  	250日:31.48 		,当前价：14.48	,当前：2022-03-16 11:26:43
        mapKeJiDongYuanYvZhou.put("000665", "湖北广电");//298	文化传媒	0.67	61.17	2.4 	价格区间: 5日:20.29 	10日:8.54  	20日:5.07  	30日:3.48  	60日:16.36 	120日:29.81 	250日:29.81 		,当前价：6.03	,当前：2022-03-16 11:26:44
        mapKeJiDongYuanYvZhou.put("002059", "云南旅游");//294	旅游酒店	5   	63.78	10.45	价格区间: 5日:44.58 	10日:50.00 	20日:36.22 	30日:18.70 	60日:41.35 	120日:45.75 	250日:47.96 		,当前价：6.3	,当前：2022-03-16 11:26:45
        mapKeJiDongYuanYvZhou.put("600804", "鹏博士");  //294	通信服务	5.11	119.34	5.82	价格区间: 5日:83.91 	10日:89.71 	20日:94.02 	30日:94.59 	60日:94.59 	120日:94.59 	250日:95.83 		,当前价：7.2	,当前：2022-03-16 11:26:46
    }

    /**
     * mapKeJiDongShuXiSuan ["东数西算"]：21;
     */
    public static Map<String, String> mapKeJiDongShuXiSuan = new HashMap<>();

    static {
        mapKeJiDongShuXiSuan.put("600510", "黑牡丹");//403	房地产开发	2.5 	158.84	5.47	价格区间: 5日:51.16 	10日:70.08 	20日:79.01 	30日:81.78 	60日:81.78 	120日:81.78 	250日:83.11 		,当前价：15.17	,当前：2022-03-16 11:04:18
        mapKeJiDongShuXiSuan.put("002837", "英维克");//380	专用设备	6.8 	111.86	7.84	价格区间: 5日:77.05 	10日:54.63 	20日:31.28 	30日:39.91 	60日:24.79 	120日:28.12 	250日:52.22 		,当前价：33.46	,当前：2022-03-16 11:04:20
        mapKeJiDongShuXiSuan.put("600996", "贵广网络");//354	文化传媒	2.36	82.14	5.19	价格区间: 5日:10.74 	10日:11.54 	20日:18.67 	30日:25.31 	60日:45.70 	120日:49.73 	250日:49.73 		,当前价：7.81	,当前：2022-03-16 11:04:21
        mapKeJiDongShuXiSuan.put("000815", "美利云");//350	造纸印刷	9.99	133.21	10.92	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:100.00	120日:100.00	250日:100.00		,当前价：19.16	,当前：2022-03-16 11:04:22
    }

    /**
     * mapXiaoFeiZaiXianLvYou
     */
    public static Map<String, String> mapXiaoFeiZaiXianLvYou = new HashMap<>();

    static {
        mapXiaoFeiZaiXianLvYou.put("000796", "凯撒旅业");//307	旅游酒店	8.05	81.91	1.02	价格区间: 5日:76.88 	10日:80.00 	20日:44.44 	30日:56.16 	60日:61.30 	120日:62.85 	250日:64.23 		,当前价：10.2	,当前：2022-03-11 14:27:26
        mapXiaoFeiZaiXianLvYou.put("000524", "岭南控股");//232	旅游酒店	-2.53	64.68	1.15	价格区间: 5日:42.29 	10日:52.46 	20日:52.46 	30日:67.88 	60日:70.41 	120日:74.11 	250日:74.11 		,当前价：9.65	,当前：2022-03-11 14:27:29
        mapXiaoFeiZaiXianLvYou.put("600258", "首旅酒店");//212	旅游酒店	1.62	260.95	1.25	价格区间: 5日:30.21 	10日:26.18 	20日:23.47 	30日:22.84 	60日:22.84 	120日:31.05 	250日:47.31 		,当前价：23.27	,当前：2022-03-11 14:27:30
        mapXiaoFeiZaiXianLvYou.put("002707", "众信旅游");//196	旅游酒店	0   	58.46	1.47	价格区间: 5日:68.13 	10日:51.67 	20日:23.31 	30日:22.22 	60日:41.03 	120日:48.70 	250日:53.33 		,当前价：6.45	,当前：2022-03-11 14:27:33
        mapXiaoFeiZaiXianLvYou.put("000069", "华侨城Ａ");//171	房地产开发	-0.46	532.30	0.91	价格区间: 5日:37.50 	10日:37.50 	20日:25.49 	30日:17.26 	60日:16.18 	120日:23.77 	250日:14.13 		,当前价：6.49	,当前：2022-03-11 14:27:34
    }

    /**
     * mapJinRongQuanShang 概念：["券商概念"];股票个数：51;
     */
    public static Map<String, String> mapJinRongQuanShang = new HashMap<>();

    static {
        mapJinRongQuanShang.put("002945", "华林证券");//243	证券    	2.63	410.40	1.1 	价格区间: 5日:74.45 	10日:77.25 	20日:74.65 	30日:76.92 	60日:66.73 	120日:75.13 	250日:75.13 		,当前价：15.2	,当前：2022-03-11 14:05:46
        mapJinRongQuanShang.put("600906", "财达证券");//211	证券    	-1.12	344.62	0.96	价格区间: 5日:58.70 	10日:58.70 	20日:32.14 	30日:20.53 	60日:15.98 	120日:10.63 	250日:45.59 		,当前价：10.62	,当前：2022-03-11 14:05:48
        mapJinRongQuanShang.put("601456", "国联证券");//182	证券    	-3.44	365.87	1.3 	价格区间: 5日:34.02 	10日:27.97 	20日:20.18 	30日:15.90 	60日:15.90 	120日:28.92 	250日:23.71 		,当前价：12.92	,当前：2022-03-11 14:05:50
        mapJinRongQuanShang.put("002939", "长城证券");//181	证券    	-0.31	301.03	0.83	价格区间: 5日:48.18 	10日:37.50 	20日:27.05 	30日:19.41 	60日:12.45 	120日:11.85 	250日:14.90 		,当前价：9.7	,当前：2022-03-11 14:05:51
        mapJinRongQuanShang.put("600958", "东方证券");//177	证券    	0.38	742.03	0.96	价格区间: 5日:34.46 	10日:22.77 	20日:19.32 	30日:14.96 	60日:7.97  	120日:5.39  	250日:21.33 		,当前价：10.61	,当前：2022-03-11 14:05:52
        mapJinRongQuanShang.put("000776", "广发证券");//171	证券    	-1.54	1266.62	1.08	价格区间: 5日:38.74 	10日:27.48 	20日:18.42 	30日:13.01 	60日:8.47  	120日:7.63  	250日:21.37 		,当前价：16.62	,当前：2022-03-11 14:05:53
        mapJinRongQuanShang.put("601375", "中原证券");//124	证券    	-0.44	210.32	0.86	价格区间: 5日:69.70 	10日:53.49 	20日:26.44 	30日:21.10 	60日:20.54 	120日:19.01 	250日:24.62 		,当前价：4.53	,当前：2022-03-11 14:05:59
    }

    /**
     * mapJinRongFangDiChan 金融-房地产：["租售同权"]：13;
     */
    public static Map<String, String> mapJinRongFangDiChan = new HashMap<>();
    static {
        mapJinRongFangDiChan.put("000732", "泰禾集团");//320	房地产开发	3.99	71.43	25.49	价格区间: 5日:70.59 	10日:70.59 	20日:39.13 	30日:48.23 	60日:51.66 	120日:58.99 	250日:58.99 		,当前价：2.87	,当前：2022-03-17 09:50:57
        mapJinRongFangDiChan.put("000056", "皇庭国际");//299	房地产服务	2.23	80.93	5.28	价格区间: 5日:75.14 	10日:49.62 	20日:36.11 	30日:36.11 	60日:56.27 	120日:64.62 	250日:65.77 		,当前价：6.89	,当前：2022-03-17 09:50:58
        mapJinRongFangDiChan.put("600048", "保利发展");//237	房地产开发	4.14	1868.56	4.53	价格区间: 5日:57.80 	10日:50.13 	20日:50.13 	30日:50.13 	60日:50.13 	120日:64.34 	250日:76.81 		,当前价：15.61	,当前：2022-03-17 09:51:01
    }

    /**
     * mapXnyGuangFuHit 新能源：光伏
     * 概念：["HIT电池"];股票个数：16;
     * ["光伏建筑一体化"]：26;
     */
    public static Map<String, String> mapXnyGuangFuHit = new HashMap<>();

    static {
        mapXnyGuangFuHit.put("002459", "晶澳科技");//002459	晶澳科技	376	光伏设备	5.88	1506.06	2.23	价格区间: 5日:91.75 	10日:94.18 	20日:83.78 	30日:83.78 	60日:83.49 	120日:81.53 	250日:88.72 		,当前价：94.18	,当前：2022-02-28 14:07:52
        mapXnyGuangFuHit.put("603396", "金辰股份");//603396	金辰股份	361	光伏设备	2.7 	96.15	2.09	价格区间: 5日:93.89 	10日:95.02 	20日:83.29 	30日:60.78 	60日:15.92 	120日:11.73 	250日:31.91 		,当前价：82.68	,当前：2022-02-28 14:07:54
        mapXnyGuangFuHit.put("600546", "山煤国际");//600546	山煤国际	339	煤炭行业	3.26	219.85	3.03	价格区间: 5日:42.67 	10日:53.26 	20日:70.55 	30日:77.43 	60日:80.05 	120日:45.82 	250日:56.08 		,当前价：11.09	,当前：2022-02-28 14:07:55
        mapXnyGuangFuHit.put("002610", "爱康科技");//002610	爱康科技	280	光伏设备	-0.24	182.76	1.95	价格区间: 5日:58.62 	10日:64.18 	20日:71.08 	30日:71.08 	60日:26.22 	120日:25.72 	250日:49.00 		,当前价：4.08	,当前：2022-02-28 14:07:58
        mapXnyGuangFuHit.put("600438", "通威股份");//600438	通威股份	256	光伏设备	1.92	1931.16	1.73	价格区间: 5日:77.41 	10日:83.33 	20日:85.45 	30日:85.45 	60日:51.89 	120日:24.53 	250日:40.96 		,当前价：42.9	,当前：2022-02-28 14:08:01
        mapXnyGuangFuHit.put("600151", "航天机电");//600151	航天机电	221	光伏设备	2.39	166.23	2.42	价格区间: 5日:82.52 	10日:83.49 	20日:65.71 	30日:35.94 	60日:27.06 	120日:43.51 	250日:65.41 		,当前价：11.59	,当前：2022-02-28 14:08:02
        mapXnyGuangFuHit.put("601012", "隆基股份");//601012	隆基股份	221	光伏设备	4.28	4208.57	2.74	价格区间: 5日:99.73 	10日:99.76 	20日:99.79 	30日:75.57 	60日:55.51 	120日:35.87 	250日:48.90 		,当前价：77.75	,当前：2022-02-28 14:08:03
        mapXnyGuangFuHit.put("002129", "中环股份");//002129	中环股份	214	光伏设备	0.88	1548.32	1.64	价格区间: 5日:94.79 	10日:96.75 	20日:97.32 	30日:98.22 	60日:98.24 	120日:56.40 	250日:75.19 		,当前价：47.91	,当前：2022-02-28 14:08:05
        //["光伏建筑一体化"]：26;
        mapXnyGuangFuHit.put("002135", "东南网架");//330	工程建设	4.35	145.34	2.88	价格区间: 5日:79.02 	10日:79.20 	20日:81.50 	30日:84.28 	60日:80.54 	120日:84.54 	250日:79.05 		,当前价：12.47	,当前：2022-03-15 10:50:36
        mapXnyGuangFuHit.put("603098", "森特股份");//317	工程建设	0.92	260.73	1.19	价格区间: 5日:79.01 	10日:83.41 	20日:85.65 	30日:89.98 	60日:89.83 	120日:67.10 	250日:85.33 		,当前价：48.39	,当前：2022-03-15 10:50:38

    }

    /**
     * 盐湖提锂
     */
    public static Map<String, String> mapYanHuTiLi = new HashMap<>();

    static {
        mapYanHuTiLi.put("600338", "西藏珠峰");//600338 西藏珠峰 295 有色金属
        mapYanHuTiLi.put("600773", "西藏城投");//600773 西藏城投 291 房地产开发
        mapYanHuTiLi.put("000762", "西藏矿业");//000762 西藏矿业 284 采掘行业
        mapYanHuTiLi.put("002240", "盛新锂能");//002240 盛新锂能 269 能源金属
        mapYanHuTiLi.put("600499", "科达制造");//600499 科达制造 263 专用设备
        mapYanHuTiLi.put("002256", "兆新股份");//002256 兆新股份 259 电网设备
        mapYanHuTiLi.put("002466", "天齐锂业");//002466 天齐锂业 251 能源金属
        mapYanHuTiLi.put("000408", "藏格矿业");//000408 藏格矿业 250 化肥行业
        mapYanHuTiLi.put("000546", "金圆股份");//000546 金圆股份 245 水泥建材
        mapYanHuTiLi.put("002140", "东华科技");//002140 东华科技 240 工程建设
        mapYanHuTiLi.put("601068", "中铝国际");//601068 中铝国际 235 工程建设
        mapYanHuTiLi.put("002460", "赣锋锂业");//002460 赣锋锂业 229 能源金属
        mapYanHuTiLi.put("603799", "华友钴业");//603799 华友钴业 229 能源金属
        mapYanHuTiLi.put("601168", "西部矿业");//601168 西部矿业 219 有色金属
        mapYanHuTiLi.put("002594", "比亚迪");  //002594 比亚迪   212 汽车整车
        mapYanHuTiLi.put("601899", "紫金矿业");//601899 紫金矿业 203 贵金属
        mapYanHuTiLi.put("000792", "盐湖股份");//000792 盐湖股份 194 化肥行业
        mapYanHuTiLi.put("002239", "奥特佳");  //002239 奥特佳   175 汽车零部件
        mapYanHuTiLi.put("603867", "新化股份");//603867 新化股份 47 化学制品
        mapYanHuTiLi.put("000920", "沃顿科技");//000920 沃顿科技 6 环保行业
    }

    /**
     * mapYiLiaoXinGuanYaoWu 概念：["新冠药物"];股票个数：3;
     */
    public static Map<String, String> mapYiLiaoXinGuanYaoWu = new HashMap<>();

    static {
        mapYiLiaoXinGuanYaoWu.put("002349", "精华制药");//436	中药    	3.96	118.51	1.59	价格区间: 5日:76.14 	10日:56.28 	20日:50.11 	30日:41.90 	60日:50.92 	120日:52.14 	250日:54.51 		,当前价：14.18	,当前：2022-03-11 11:34:58
        mapYiLiaoXinGuanYaoWu.put("600668", "尖峰集团");//319	水泥建材	5.79	84.89	2.53	价格区间: 5日:91.55 	10日:93.12 	20日:93.16 	30日:93.22 	60日:95.32 	120日:95.81 	250日:95.96 		,当前价：24.67	,当前：2022-03-11 11:34:59
        mapYiLiaoXinGuanYaoWu.put("002603", "以岭药业");//296	中药    	1.02	411.83	1.5 	价格区间: 5日:54.97 	10日:50.87 	20日:54.86 	30日:61.39 	60日:71.71 	120日:74.08 	250日:76.35 		,当前价：24.65	,当前：2022-03-11 11:35:00
        mapYiLiaoXinGuanYaoWu.put("002584", "西陇科学");//291	电子化学品	3.2 	62.21	2.48	价格区间: 5日:74.79 	10日:74.79 	20日:80.33 	30日:80.33 	60日:53.21 	120日:62.22 	250日:64.58 		,当前价：10.63	,当前：2022-03-11 11:35:02
        mapYiLiaoXinGuanYaoWu.put("600196", "复星医药");//221	化学制药	-0.27	1029.00	1.15	价格区间: 5日:37.91 	10日:34.50 	20日:34.50 	30日:23.51 	60日:17.60 	120日:10.25 	250日:6.87  		,当前价：40.15	,当前：2022-03-11 11:35:03
    }

    /**
     * mapYiLiaoXinGuanJianCe 概念：["新冠检测"];股票个数：15;
     */
    public static Map<String, String> mapYiLiaoXinGuanJianCe = new HashMap<>();

    static {
        mapYiLiaoXinGuanJianCe.put("002432", "九安医疗");//575	医疗器械	10   	284.19	1.66	价格区间: 5日:100.00	10日:69.31 	20日:51.38 	30日:51.38 	60日:58.16 	120日:64.49 	250日:64.49 		,当前价：59.38	,当前：2022-03-11 13:51:13
        mapYiLiaoXinGuanJianCe.put("002932", "明德生物");//377	医疗器械	10   	99.88	5.46	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:46.32 	120日:55.00 	250日:65.00 		,当前价：95.23	,当前：2022-03-11 13:51:15
        mapYiLiaoXinGuanJianCe.put("002584", "西陇科学");//297	电子化学品	6.8 	64.37	2.48	价格区间: 5日:87.82 	10日:87.82 	20日:90.49 	30日:90.49 	60日:57.95 	120日:66.05 	250日:68.17 		,当前价：11.0	,当前：2022-03-11 13:51:16
        mapYiLiaoXinGuanJianCe.put("603392", "万泰生物");//296	医疗器械	1.96	1532.17	1.7 	价格区间: 5日:82.19 	10日:73.06 	20日:91.33 	30日:91.33 	60日:92.18 	120日:88.09 	250日:69.31 		,当前价：252.4	,当前：2022-03-11 13:51:17
    }


    /**
     * mapCro
     */
    public static Map<String, String> mapYiLiaoCro = new HashMap<>();

    static {
        mapYiLiaoCro.put("603456", "九洲药业");//603456	九洲药业	301	化学制药	1.07	401.47	5.81	价格区间: 5日:69.90 	10日:76.56 	20日:76.78 	30日:62.55 	60日:35.23 	120日:37.10 	250日:54.15 		,当前价：48.22	,当前：2022-02-28 11:10:56
        mapYiLiaoCro.put("603127", "昭衍新药");//603127	昭衍新药	296	医疗服务	-1.61	439.92	3.52	价格区间: 5日:79.07 	10日:85.06 	20日:86.45 	30日:86.45 	60日:46.65 	120日:29.34 	250日:35.31 		,当前价：115.39	,当前：2022-02-28 11:10:57
        mapYiLiaoCro.put("002821", "凯莱英  ");//002821	凯莱英  	283	医疗服务	-0.79	902.15	2.08	价格区间: 5日:67.59 	10日:81.05 	20日:82.39 	30日:60.24 	60日:28.70 	120日:28.70 	250日:36.49 		,当前价：341.28	,当前：2022-02-28 11:10:59
        mapYiLiaoCro.put("603259", "药明康德");//603259	药明康德	225	医疗服务	-0.1	3053.29	2.52	价格区间: 5日:69.11 	10日:83.82 	20日:67.59 	30日:54.24 	60日:31.29 	120日:27.20 	250日:23.12 		,当前价：103.3	,当前：2022-02-28 11:11:00
    }

    /**
     * mapShiYou 概念：["可燃冰","油气设服","页岩气","氦气概念","天然气","油价相关"];股票个数：97;
     */
    public static Map<String, String> mapShiYou = new HashMap<>();

    static {
        mapShiYou.put("600021", "上海电力");//600021	上海电力	341	电力行业	1.92	305.68	7.1 	价格区间: 5日:84.39 	10日:64.04 	20日:42.07 	30日:29.08 	60日:35.72 	120日:47.98 	250日:50.49 		,当前价：11.68	,当前：2022-02-28 09:47:54
        mapShiYou.put("600348", "华阳股份");//600348	华阳股份	335	煤炭行业	1.36	287.16	3.03	价格区间: 5日:34.23 	10日:42.06 	20日:70.20 	30日:70.20 	60日:53.58 	120日:34.09 	250日:63.31 		,当前价：11.94	,当前：2022-02-28 09:47:55
        mapShiYou.put("601969", "海南矿业");//601969	海南矿业	307	钢铁行业	-0.99	223.40	2.2 	价格区间: 5日:27.71 	10日:56.20 	20日:70.59 	30日:65.16 	60日:21.62 	120日:9.05  	250日:29.64 		,当前价：11.05	,当前：2022-02-28 09:47:56
        mapShiYou.put("603619", "中曼石油");//603619	中曼石油	307	采掘行业	3.34	68.04	4.63	价格区间: 5日:59.54 	10日:66.26 	20日:78.52 	30日:78.52 	60日:83.15 	120日:80.80 	250日:83.27 		,当前价：17.01	,当前：2022-02-28 09:47:57
        mapShiYou.put("600759", "洲际油气");//600759	洲际油气	306	石油行业	4.56	72.66	6.21	价格区间: 5日:80.85 	10日:85.00 	20日:89.53 	30日:89.53 	60日:91.09 	120日:46.94 	250日:57.55 		,当前价：3.21	,当前：2022-02-28 09:47:58
        mapShiYou.put("000723", "美锦能源");//000723	美锦能源	304	煤炭行业	-3.09	601.68	5.17	价格区间: 5日:30.37 	10日:30.37 	20日:56.68 	30日:36.83 	60日:17.85 	120日:41.95 	250日:56.79 		,当前价：14.09	,当前：2022-02-28 09:48:00
        mapShiYou.put("600256", "广汇能源");//600256	广汇能源	298	石油行业	1.56	468.79	3.98	价格区间: 5日:64.47 	10日:75.45 	20日:83.83 	30日:83.83 	60日:80.00 	120日:45.23 	250日:67.16 		,当前价：7.14	,当前：2022-02-28 09:48:01
    }

    /**
     * mapKeJiShuZiHuoBi 概念：["数字货币"];股票个数：24;
     */
    public static Map<String, String> mapKeJiShuZiHuoBi = new HashMap<>();

    static {
        mapKeJiShuZiHuoBi.put("603123", "翠微股份");//407	商业百货	5.06	144.25	1.06	价格区间: 5日:71.71 	10日:36.00 	20日:16.51 	30日:12.68 	60日:47.01 	120日:49.16 	250日:49.16 		,当前价：18.06	,当前：2022-03-11 14:17:44
        mapKeJiShuZiHuoBi.put("002537", "海联金汇");//396	汽车零部件	6.3 	120.81	2.27	价格区间: 5日:86.50 	10日:64.87 	20日:70.71 	30日:73.04 	60日:76.84 	120日:82.40 	250日:82.65 		,当前价：10.29	,当前：2022-03-11 14:17:45
        mapKeJiShuZiHuoBi.put("003040", "楚天龙");//334	通信设备	3.66	105.69	1.12	价格区间: 5日:51.52 	10日:39.96 	20日:23.97 	30日:21.18 	60日:13.77 	120日:13.77 	250日:42.89 		,当前价：22.92	,当前：2022-03-11 14:17:46
        mapKeJiShuZiHuoBi.put("002268", "卫士通");//315	软件开发	0.09	361.54	1.59	价格区间: 5日:75.09 	10日:50.60 	20日:60.25 	30日:58.44 	60日:29.44 	120日:23.34 	250日:55.55 		,当前价：42.72	,当前：2022-03-11 14:17:47
        mapKeJiShuZiHuoBi.put("002987", "京北方");//297	软件开发	1.15	79.20	1.06	价格区间: 5日:85.36 	10日:47.49 	20日:32.26 	30日:24.03 	60日:34.20 	120日:42.68 	250日:46.15 		,当前价：35.21	,当前：2022-03-11 14:17:49
        mapKeJiShuZiHuoBi.put("000676", "智度股份");//277	文化传媒	-0.42	90.38	0.74	价格区间: 5日:68.42 	10日:49.52 	20日:27.23 	30日:29.80 	60日:22.10 	120日:41.08 	250日:49.51 		,当前价：7.08	,当前：2022-03-11 14:17:49
        mapKeJiShuZiHuoBi.put("002197", "证通电子");//270	计算机设备	3.6 	77.78	1.24	价格区间: 5日:89.07 	10日:48.51 	20日:27.72 	30日:34.51 	60日:55.22 	120日:58.82 	250日:58.76 		,当前价：12.66	,当前：2022-03-11 14:17:50
        mapKeJiShuZiHuoBi.put("002049", "紫光国微");//254	半导体  	0.34	1265.38	0.92	价格区间: 5日:41.16 	10日:36.81 	20日:58.14 	30日:50.34 	60日:44.50 	120日:48.74 	250日:74.45 		,当前价：208.52	,当前：2022-03-11 14:17:52
        mapKeJiShuZiHuoBi.put("002104", "恒宝股份");//253	通信设备	10.01	80.42	1.08	价格区间: 5日:79.80 	10日:45.63 	20日:20.10 	30日:39.98 	60日:45.56 	120日:48.77 	250日:50.88 		,当前价：11.54	,当前：2022-03-11 14:17:53
        mapKeJiShuZiHuoBi.put("002152", "广电运通");//168	计算机设备	0.37	268.45	0.94	价格区间: 5日:50.98 	10日:36.45 	20日:25.32 	30日:22.35 	60日:22.35 	120日:29.79 	250日:40.32 		,当前价：10.81	,当前：2022-03-11 14:18:02
        mapKeJiShuZiHuoBi.put("000997", "新大陆");//206	互联网服务	3.11	174.52	0.76	价格区间: 5日:73.52 	10日:49.39 	20日:49.39 	30日:46.94 	60日:43.16 	120日:53.81 	250日:65.19 		,当前价：16.91	,当前：2022-03-11 14:17:57
    }

    /**
     * mapXnyGuangFuHit 概念：科技-"虚拟数字人";股票个数：13;
     */
    public static Map<String, String> mapChuanMeiYuanYuZhou = new HashMap<>();

    static {
        mapChuanMeiYuanYuZhou.put("002354", "天娱数科");//002354	天娱数科	377	游戏    	6.44	87.93	1.35	价格区间: 5日:72.37 	10日:72.03 	20日:72.03 	30日:52.55 	60日:49.60 	120日:57.37 	250日:59.70 		,当前价：5.29	,当前：2022-03-04 13:54:57
        mapChuanMeiYuanYuZhou.put("603466", "风语筑  ");//603466	风语筑  	352	文化传媒	7.12	101.57	1.19	价格区间: 5日:83.08 	10日:85.33 	20日:34.59 	30日:19.18 	60日:36.00 	120日:47.16 	250日:51.15 		,当前价：24.07	,当前：2022-03-04 13:54:58
        mapChuanMeiYuanYuZhou.put("000810", "创维数字");//000810	创维数字	336	家电行业	-0.52	142.69	0.8 	价格区间: 5日:68.20 	10日:74.14 	20日:76.01 	30日:54.12 	60日:69.66 	120日:75.00 	250日:75.00 		,当前价：13.42	,当前：2022-03-04 13:55:00
        mapChuanMeiYuanYuZhou.put("000681", "视觉中国");//000681	视觉中国	322	文化传媒	2.53	113.35	1.47	价格区间: 5日:62.12 	10日:43.48 	20日:30.72 	30日:7.73  	60日:7.20  	120日:26.11 	250日:31.64 		,当前价：16.18	,当前：2022-03-04 13:55:01
        mapChuanMeiYuanYuZhou.put("002241", "歌尔股份");//002241	歌尔股份	235	消费电子	-2.65	1354.57	1.55	价格区间: 5日:4.49  	10日:3.70  	20日:3.05  	30日:1.92  	60日:1.31  	120日:2.33  	250日:43.52 		,当前价：39.65	,当前：2022-03-04 13:55:16
        mapChuanMeiYuanYuZhou.put("002230", "科大讯飞");//002230	科大讯飞	154	软件开发	0.18	1141.01	1.14	价格区间: 5日:28.09 	10日:20.69 	20日:68.02 	30日:68.84 	60日:47.21 	120日:34.27 	250日:22.24 		,当前价：49.09	,当前：2022-03-04 13:56:10
    }

    /**
     * mapYiLiao 医疗
     */
    public static Map<String, String> mapYiLiao = new HashMap<>();

    static {
        mapYiLiao.putAll(mapYiLiaoCro);
        mapYiLiao.putAll(mapYiLiaoXinGuanYaoWu);
        mapYiLiao.putAll(mapYiLiaoXinGuanJianCe);
    }

    /**
     * mapYiLiaoXinGuanAll 医疗-新冠-汇总:概念：["新冠药物","新冠检测","体外诊断"];股票个数：29;
     */
    public static Map<String, String> mapYiLiaoXinGuanAll = new HashMap<>();

    static {
        mapYiLiaoXinGuanAll.putAll(mapYiLiaoXinGuanYaoWu);
        mapYiLiaoXinGuanAll.putAll(mapYiLiaoXinGuanJianCe);
    }
}
