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
     * allZiYuan
     */
    public static Map<String, String> all = new HashMap<>();

    static {
        all.putAll(lvSeDianLi);
    }
}
