package utils;

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
     * quanShangGaiNian 概念：["券商概念"];股票个数：51;
     */
    public static Map<String, String> quanShangGaiNian = new HashMap<>();

    static {
        quanShangGaiNian.put("002945", "华林证券");//275	证券    	-1.93	452.79	3.12	价格区间: 5日:39.84 	10日:27.86 	20日:55.35 	30日:60.26 	60日:66.07 	120日:73.06 	250日:73.06 		,当前价：16.77	,当前：2022-03-25 10:54:37
        quanShangGaiNian.put("600906", "财达证券");//237	证券    	0.98	369.61	1.55	价格区间: 5日:37.18 	10日:31.36 	20日:45.79 	30日:45.79 	60日:37.26 	120日:28.99 	250日:48.67 		,当前价：11.39	,当前：2022-03-25 10:54:39
        quanShangGaiNian.put("600958", "东方证券");//207	证券    	1.11	766.50	1.45	价格区间: 5日:70.00 	10日:63.55 	20日:34.82 	30日:29.55 	60日:15.76 	120日:10.13 	250日:23.71 		,当前价：10.96	,当前：2022-03-25 10:54:40
        quanShangGaiNian.put("000776", "广发证券");//198	证券    	0   	1317.69	1.93	价格区间: 5日:25.00 	10日:65.93 	20日:45.43 	30日:30.91 	60日:16.02 	120日:13.05 	250日:25.00 		,当前价：17.29	,当前：2022-03-25 10:54:41
        quanShangGaiNian.put("601456", "国联证券");//191	证券    	2.26	372.38	3.42	价格区间: 5日:40.74 	10日:30.67 	20日:22.88 	30日:16.51 	60日:13.01 	120日:26.48 	250日:21.70 		,当前价：13.15	,当前：2022-03-25 10:54:43
        quanShangGaiNian.put("002939", "长城证券");//186	证券    	0.84	297.31	1.34	价格区间: 5日:25.58 	10日:40.23 	20日:20.45 	30日:14.75 	60日:8.89  	120日:6.46  	250日:9.71  		,当前价：9.58	,当前：2022-03-25 10:54:44
        quanShangGaiNian.put("600095", "湘财股份");//182	证券    	0.48	240.96	1.99	价格区间: 5日:36.84 	10日:37.50 	20日:36.92 	30日:36.64 	60日:18.60 	120日:14.77 	250日:13.63 		,当前价：8.44	,当前：2022-03-25 10:54:45
    }

    /**
     * jianZhuJieNeng ["建筑节能"]：6;
     */
    public static Map<String, String> jianZhuJieNeng = new HashMap<>();

    static {
        jianZhuJieNeng.put("002761", "浙江建投");//465	工程建设	-4.58	409.61	10.56	价格区间: 5日:67.55 	10日:83.76 	20日:86.13 	30日:89.10 	60日:89.23 	120日:89.28 	250日:89.32 		,当前价：37.88	,当前：2022-03-22 11:00:58
        jianZhuJieNeng.put("002810", "山东赫达");//263	化学制品	-2.39	145.29	1.42	价格区间: 5日:24.54 	10日:15.54 	20日:7.84  	30日:7.84  	60日:5.61  	120日:19.41 	250日:35.45 		,当前价：42.41	,当前：2022-03-22 11:00:59
        jianZhuJieNeng.put("000517", "荣安地产");//237	房地产开发	7.4 	106.34	66.03	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:100.00	120日:100.00	250日:100.00		,当前价：3.34	,当前：2022-03-22 11:01:00
        jianZhuJieNeng.put("600586", "金晶科技");//235	玻璃玻纤	-0.95	118.59	3.11	价格区间: 5日:82.14 	10日:82.14 	20日:62.84 	30日:52.04 	60日:51.80 	120日:20.43 	250日:28.61 		,当前价：8.3	,当前：2022-03-22 11:01:01
    }

    /**
     * zuShouTongQuan ["租售同权"]：14;
     */
    public static Map<String, String> zuShouTongQuan = new HashMap<>();

    static {
        zuShouTongQuan.put("000732", "泰禾集团");//304	房地产开发	0.36	69.94	3.38	价格区间: 5日:76.47 	10日:76.47 	20日:50.00 	30日:35.45 	60日:51.03 	120日:60.11 	250日:60.11 		,当前价：2.81	,当前：2022-03-22 10:42:34
        zuShouTongQuan.put("000056", "皇庭国际");//300	房地产服务	-2.34	78.58	1.45	价格区间: 5日:69.80 	10日:49.52 	20日:28.89 	30日:28.89 	60日:51.33 	120日:60.62 	250日:61.90 		,当前价：6.69	,当前：2022-03-22 10:42:35
        zuShouTongQuan.put("000671", "阳光城  ");//283	房地产开发	10.1	130.84	25.36	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:61.17 	120日:47.52 	250日:27.85 		,当前价：3.16	,当前：2022-03-22 10:42:36
        zuShouTongQuan.put("600048", "保利发展");//253	房地产开发	1.62	2027.77	2.09	价格区间: 5日:97.51 	10日:97.51 	20日:88.47 	30日:88.47 	60日:88.47 	120日:91.76 	250日:94.64 		,当前价：16.94	,当前：2022-03-22 10:42:38
        zuShouTongQuan.put("000002", "万科Ａ  ");//196	房地产开发	1.07	1973.99	1.92	价格区间: 5日:91.03 	10日:83.82 	20日:45.53 	30日:39.31 	60日:32.91 	120日:32.91 	250日:16.06 		,当前价：16.98	,当前：2022-03-22 10:42:42
    }

    /**
     * reits ["REITs概念"]：16;
     */
    public static Map<String, String> reits = new HashMap<>();

    static {
        reits.put("002059", "云南旅游");//318	旅游酒店	-2.59	68.64	5.07	价格区间: 5日:65.88 	10日:74.78 	20日:74.78 	30日:34.96 	60日:50.97 	120日:55.75 	250日:57.55 		,当前价：6.78	,当前：2022-03-22 10:56:13
        reits.put("600048", "保利发展");//259	房地产开发	3.18	2058.89	2.09	价格区间: 5日:95.42 	10日:95.42 	20日:88.72 	30日:88.72 	60日:88.72 	120日:91.94 	250日:94.76 		,当前价：17.2	,当前：2022-03-22 10:56:15
        reits.put("600466", "蓝光发展");//248	房地产开发	1.96	63.13	6.29	价格区间: 5日:96.08 	10日:96.08 	20日:96.08 	30日:90.74 	60日:32.67 	120日:33.11 	250日:17.30 		,当前价：2.08	,当前：2022-03-22 10:56:16
        reits.put("002285", "世联行  ");//245	房地产服务	4.91	73.82	11.16	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:59.12 	120日:59.12 	250日:16.58 		,当前价：3.63	,当前：2022-03-22 10:56:17
        reits.put("002314", "南山控股");//237	房地产开发	0.53	102.62	4.09	价格区间: 5日:100.00	10日:100.00	20日:93.94 	30日:31.79 	60日:38.71 	120日:46.80 	250日:46.80 		,当前价：3.79	,当前：2022-03-22 10:56:18
        reits.put("001979", "招商蛇口");//219	房地产开发	4   	1113.22	1.26	价格区间: 5日:93.45 	10日:71.10 	20日:69.03 	30日:69.03 	60日:69.03 	120日:83.28 	250日:84.18 		,当前价：14.05	,当前：2022-03-22 10:56:19
        reits.put("000002", "万科Ａ  ");//196	房地产开发	2.98	2011.19	1.92	价格区间: 5日:99.69 	10日:94.41 	20日:51.28 	30日:44.28 	60日:37.07 	120日:37.07 	250日:18.03 		,当前价：17.3	,当前：2022-03-22 10:56:20
    }

    /**
     * allZiYuan
     */
    public static Map<String, String> all = new HashMap<>();

    static {
        all.putAll(zuShouTongQuan);
        all.putAll(reits);
        all.putAll(jianZhuJieNeng);
        all.putAll(jianZhuJieNeng);
    }

    /**
     * fangDiChan 房地产
     */
    public static Map<String, String> fangDiChan = new HashMap<>();

    static {
        fangDiChan.putAll(zuShouTongQuan);
        fangDiChan.putAll(reits);
        fangDiChan.putAll(jianZhuJieNeng);
        fangDiChan.putAll(quanShangGaiNian);//券商概念
    }
}
