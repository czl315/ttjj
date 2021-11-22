package utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 计划买入
 *
 * @author chenzhilong
 * @date
 */
public class ToBuyMap {
    /**
     * zhishuMap
     */
    public static Map<String, String> stockMap = new HashMap<>();

    static {
        stockMap.put("603517", "绝味食品");
        stockMap.put("000066", "中国长城");
        stockMap.put("002600", "领益智造");
        stockMap.put("002938", "鹏鼎控股");

    }

    public static Map<String, String> banks = new LinkedHashMap<>();

    static {
        banks.put("600036", "招商银行");
        banks.put("002142", "宁波银行");
        banks.put("000776", "广发证券");
        banks.put("000002", "万科A");
        banks.put("000537", "广宇发展");
        banks.put("600048", "保利发展");
    }

    /**
     * wenHuaChuanMei 文化传媒
     */
    public static Map<String, String> wenHuaChuanMei = new HashMap<>();

    static {
        wenHuaChuanMei.put("600556", "天下秀");
        wenHuaChuanMei.put("002624", "完美世界");
        wenHuaChuanMei.put("002027", "分众传媒");
    }

    public static Map<String, String> meiTanHangYe = new HashMap<>();

    static {
        meiTanHangYe.put("600188", "兖州煤业");
    }

    public static Map<String, String> nongMuSuYv = new HashMap<>();

    static {
        nongMuSuYv.put("002714", "牧原股份");
        nongMuSuYv.put("000998", "隆平高科");
    }

    public static Map<String, String> caiLiaoHangYe = new HashMap<>();

    static {
        nongMuSuYv.put("002129", "中环股份");
        nongMuSuYv.put("600516", "方大炭素");
        nongMuSuYv.put("002074", "国轩高科");
        nongMuSuYv.put("601012", "隆基股份");
    }
}
