package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ContMapStZiYuan简介-股票分类数据：消费
 *
 * @author Administrator
 * @date 2022-03-18 10:09
 */
public class ContMapStXiaoFei {
    /**
     * zhuanJiYin ["转基因"]：8;
     */
    public static Map<String, String> zhuanJiYin = new HashMap<>();
    static {
        zhuanJiYin.put("600596", "新安股份");//328	农药兽药	0.74	224.16	5.59	价格区间: 5日:85.71 	10日:85.83 	20日:60.23 	30日:69.50 	60日:73.67 	120日:22.34 	250日:43.53 		,当前价：27.39	,当前：2022-03-21 11:05:54
        zhuanJiYin.put("002100", "天康生物");//286	农牧饲渔	2.37	163.68	3.11	价格区间: 5日:95.43 	10日:95.43 	20日:92.16 	30日:71.48 	60日:80.37 	120日:85.44 	250日:87.24 		,当前价：12.09	,当前：2022-03-21 11:05:55
        zhuanJiYin.put("002385", "大北农  ");//249	农牧饲渔	2.73	327.58	4.8 	价格区间: 5日:88.89 	10日:55.56 	20日:39.80 	30日:28.67 	60日:20.05 	120日:19.32 	250日:31.28 		,当前价：7.91	,当前：2022-03-21 11:05:57
    }

    /**
     * ["在线旅游"]：8;
     */
    public static Map<String, String> zaiXianLvYou = new HashMap<>();

    static {
        zaiXianLvYou.put("000524", "岭南控股");//328	旅游酒店	10.04	86.66	77.48	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:100.00	120日:100.00	250日:100.00		,当前价：12.93	,当前：2022-03-18 10:29:08
        zaiXianLvYou.put("000796", "凯撒旅业");//313	旅游酒店	2.63	84.48	13.48	价格区间: 5日:74.00 	10日:78.15 	20日:80.23 	30日:67.03 	60日:74.48 	120日:75.50 	250日:76.35 		,当前价：10.52	,当前：2022-03-18 10:29:10
        zaiXianLvYou.put("600258", "首旅酒店");//256	旅游酒店	0.86	277.77	2.2 	价格区间: 5日:95.47 	10日:64.52 	20日:56.98 	30日:50.51 	60日:50.51 	120日:50.51 	250日:59.71 		,当前价：24.77	,当前：2022-03-18 10:29:11
        zaiXianLvYou.put("002707", "众信旅游");//244	旅游酒店	0.7 	64.80	16.07	价格区间: 5日:81.61 	10日:80.46 	20日:80.46 	30日:47.95 	60日:58.70 	120日:64.07 	250日:67.31 		,当前价：7.15	,当前：2022-03-18 10:29:12
        zaiXianLvYou.put("000069", "华侨城Ａ");//188	房地产开发	-0.31	519.17	2.68	价格区间: 5日:65.05 	10日:45.27 	20日:39.18 	30日:27.35 	60日:23.51 	120日:23.51 	250日:14.38 		,当前价：6.33	,当前：2022-03-18 10:29:17
    }

    /**
     * yuZhiCaiGaiNian ["预制菜概念"]：16;
     */
    public static Map<String, String> yuZhiCaiGaiNian = new HashMap<>();

    static {
        yuZhiCaiGaiNian.put("000524", "岭南控股");//356	旅游酒店	9.98	95.30	47.18	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:100.00	120日:100.00	250日:100.00		,当前价：14.22	,当前：2022-03-21 10:40:58
        yuZhiCaiGaiNian.put("605089", "味知香  ");//326	食品饮料	6.34	61.68	1.18	价格区间: 5日:85.38 	10日:72.40 	20日:56.38 	30日:44.72 	60日:16.25 	120日:16.25 	250日:20.80 		,当前价：61.68	,当前：2022-03-21 10:40:59
        yuZhiCaiGaiNian.put("600965", "福成股份");//240	农牧饲渔	5.05	56.24	2.4 	价格区间: 5日:90.91 	10日:90.91 	20日:90.91 	30日:90.91 	60日:24.69 	120日:25.43 	250日:37.11 		,当前价：6.87	,当前：2022-03-21 10:41:01
        yuZhiCaiGaiNian.put("000876", "新希望  ");//236	农牧饲渔	2.5 	701.46	1.98	价格区间: 5日:98.17 	10日:58.27 	20日:55.99 	30日:44.51 	60日:42.24 	120日:57.76 	250日:42.92 		,当前价：15.57	,当前：2022-03-21 10:41:02
        yuZhiCaiGaiNian.put("603345", "安井食品");//235	食品饮料	3.12	339.89	3.46	价格区间: 5日:86.65 	10日:86.65 	20日:44.35 	30日:34.64 	60日:15.72 	120日:13.03 	250日:8.52  		,当前价：115.88	,当前：2022-03-21 10:41:03
    }

    /**
     * allZiYuan
     */
    public static Map<String, String> all = new HashMap<>();

    static {
        all.putAll(zaiXianLvYou);
        all.putAll(yuZhiCaiGaiNian);
    }
}
