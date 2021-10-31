package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 计划买入
 * @author chenzhilong
 * @date
 */
public class ToBuyMap {
    /**
     * zhishuMap
     */
    private static Map<String, String> stockMap = new HashMap<>();
    static {
        stockMap.put("603517","绝味食品");
        stockMap.put("000066","中国长城");
        stockMap.put("002600","领益智造");
        stockMap.put("002938","鹏鼎控股");

    }
}
