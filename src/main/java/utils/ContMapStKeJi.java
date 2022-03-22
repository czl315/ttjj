package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ContMapStZiYuan简介-股票分类数据：科技
 *
 * @author Administrator
 * @date 2022-03-18 10:09
 */
public class ContMapStKeJi {

    /**
     * ["绿色电力"]：67;
     */
    public static Map<String, String> lvSeDianLi = new HashMap<>();

    static {
        lvSeDianLi.put("002053", "云南能投");//401	化学原料	9.99	135.68	7.01	价格区间: 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:100.00	120日:100.00	250日:100.00		,当前价：17.83	,当前：2022-03-18 10:41:56
        lvSeDianLi.put("600821", "金开新能");//397	电力行业	1.01	122.60	1.14	价格区间: 5日:39.85 	10日:28.96 	20日:28.65 	30日:25.73 	60日:9.76  	120日:23.01 	250日:41.02 		,当前价：7.98	,当前：2022-03-18 10:41:57
        lvSeDianLi.put("600021", "上海电力");//357	电力行业	2.8 	278.20	1.83	价格区间: 5日:54.27 	10日:33.58 	20日:33.58 	30日:22.14 	60日:12.66 	120日:25.18 	250日:38.75 		,当前价：10.63	,当前：2022-03-18 10:41:59
        lvSeDianLi.put("002256", "兆新股份");//356	电网设备	1.43	66.64	1.78	价格区间: 5日:65.00 	10日:49.06 	20日:32.91 	30日:32.91 	60日:14.73 	120日:13.52 	250日:41.65 		,当前价：3.54	,当前：2022-03-18 10:42:00
    }

    /**
     * foHuaGong ["氟化工"]：21;
     */
    public static Map<String, String> foHuaGong = new HashMap<>();

    static {
        foHuaGong.put("002738", "中矿资源");//446	小金属  	2.77	331.28	6.52	价格区间: 5日:98.12 	10日:90.60 	20日:61.23 	30日:83.49 	60日:83.81 	120日:84.88 	250日:89.88 		,当前价：101.85	,当前：2022-03-21 10:31:07
        foHuaGong.put("002326", "永太科技");//425	化学制品	10   	298.03	7.78	价格区间: 5日:100.00	10日:100.00	20日:59.75 	30日:59.75 	60日:25.31 	120日:14.07 	250日:35.04 		,当前价：34.0	,当前：2022-03-21 10:31:08
        foHuaGong.put("600096", "云天化  ");//421	化肥行业	2.83	440.25	2.13	价格区间: 5日:40.12 	10日:40.08 	20日:54.85 	30日:70.57 	60日:72.56 	120日:37.74 	250日:56.42 		,当前价：23.98	,当前：2022-03-21 10:31:09
        foHuaGong.put("002759", "天际股份");//382	家电行业	5.02	127.00	18.77	价格区间: 5日:87.83 	10日:91.62 	20日:91.62 	30日:91.62 	60日:91.62 	120日:28.66 	250日:45.56 		,当前价：31.58	,当前：2022-03-21 10:31:10
        foHuaGong.put("002411", "延安必康");//371	医药商业	1.68	157.21	5.37	价格区间: 5日:91.18 	10日:92.15 	20日:80.37 	30日:80.37 	60日:44.00 	120日:24.54 	250日:43.91 		,当前价：10.26	,当前：2022-03-21 10:31:11
        foHuaGong.put("600141", "兴发集团");//350	化肥行业	0.14	395.22	2.86	价格区间: 5日:83.23 	10日:45.20 	20日:31.66 	30日:47.28 	60日:49.20 	120日:20.24 	250日:49.71 		,当前价：35.55	,当前：2022-03-21 10:31:12
        foHuaGong.put("603026", "石大胜华");//327	化学制品	2.11	317.19	3.33	价格区间: 5日:90.31 	10日:93.73 	20日:43.00 	30日:43.00 	60日:32.75 	120日:11.19 	250日:34.45 		,当前价：156.5	,当前：2022-03-21 10:31:13
        foHuaGong.put("002709", "天赐材料");//317	化学制品	2.68	1013.96	2.06	价格区间: 5日:78.84 	10日:87.98 	20日:87.98 	30日:87.98 	60日:57.56 	120日:25.59 	250日:49.69 		,当前价：105.64	,当前：2022-03-21 10:31:14
        foHuaGong.put("600884", "杉杉股份");//314	电子元件	2.83	607.52	2.06	价格区间: 5日:80.97 	10日:84.67 	20日:65.78 	30日:65.78 	60日:38.67 	120日:17.90 	250日:49.61 		,当前价：28.35	,当前：2022-03-21 10:31:15
        foHuaGong.put("002407", "多氟多  ");//312	化学制品	3.06	337.77	2.25	价格区间: 5日:88.80 	10日:95.45 	20日:77.52 	30日:77.52 	60日:77.52 	120日:29.62 	250日:54.32 		,当前价：44.09	,当前：2022-03-21 10:31:17
    }

    /**
     * allZiYuan
     */
    public static Map<String, String> all = new HashMap<>();

    static {
        all.putAll(lvSeDianLi);
        all.putAll(foHuaGong);
    }
}
