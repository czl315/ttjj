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
     * zuShouTongQuan ["租售同权"]：14;
     */
    public static Map<String, String> zuShouTongQuan = new HashMap<>();

    static {
        zuShouTongQuan.put("000732", "泰禾集团");//307	房地产开发	-1.1	67.20	18.11	价格区间: 5日:54.90 	10日:54.90 	20日:30.43 	30日:31.67 	60日:45.33 	120日:53.93 	250日:53.93 		,当前价：2.7	,当前：2022-03-18 14:44:34
        zuShouTongQuan.put("000056", "皇庭国际");//305	房地产服务	-0.85	82.10	3.49	价格区间: 5日:75.15 	10日:54.15 	20日:34.44 	30日:34.44 	60日:55.13 	120日:63.69 	250日:64.88 		,当前价：6.99	,当前：2022-03-18 14:44:36
        zuShouTongQuan.put("600048", "保利发展");//247	房地产开发	6.67	1971.51	1.7 	价格区间: 5日:88.15 	10日:76.44 	20日:76.44 	30日:76.44 	60日:76.44 	120日:83.15 	250日:89.04 		,当前价：16.47	,当前：2022-03-18 14:44:38
    }

    /**
     * allZiYuan
     */
    public static Map<String, String> all = new HashMap<>();

    static {
        all.putAll(zuShouTongQuan);
    }
}
