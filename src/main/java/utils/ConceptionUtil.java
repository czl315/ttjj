package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ConceptionUtil简介-概念工具
 *
 * @author Administrator
 * @date 2022-03-30 13:58
 */
public class ConceptionUtil {

    /**
     * StConceptionMap 概念map
     */
    public static Map<String, String> stConceptionMap = new HashMap<>();

    static {
        stConceptionMap.put("电子身份证", "shuZi_dianZiShenFenZheng");
        stConceptionMap.put("数据安全", "shuZi_shuJuAnQuan");

        stConceptionMap.put("调味品概念", "shiPin_tiaoWeiPin");
        stConceptionMap.put("水产养殖", "nongYe_shuiChanYangZhi");

        stConceptionMap.put("NFT概念", "chuanMei_nft");
    }
}
