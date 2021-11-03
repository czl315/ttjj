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
    public static Map<String, String> stockMap = new HashMap<>();
    static {
        stockMap.put("603517","绝味食品");
        stockMap.put("000066","中国长城");
        stockMap.put("002600","领益智造");
        stockMap.put("002938","鹏鼎控股");

    }

    public static Map<String, String> banks = new HashMap<>();
    static {
        banks.put("600036","招商银行");
        banks.put("000776","广发证券");
        banks.put("000002","万科A");
    }
}
