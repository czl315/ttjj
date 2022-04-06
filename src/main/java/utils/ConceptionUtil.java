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
        stConceptionMap.put("固态电池", "xinNengYuan_guTaiDianChi");
        stConceptionMap.put("刀片电池", "xinNengYuan_daoPianDianChi");
        stConceptionMap.put("电子身份证", "shuZi_dianZiShenFenZheng");
        stConceptionMap.put("数据安全", "shuZi_shuJuAnQuan");
        stConceptionMap.put("数字货币", "shuZi_shuZiHuoBi");

        stConceptionMap.put("在线旅游", "lvYou_zaiXianLvYou");
        stConceptionMap.put("免税概念", "lvYou_mianShui");
        stConceptionMap.put("航空机场", "lvYou_hangKongJiChang");
        stConceptionMap.put("草甘膦", "nongYe_caoGanLin");
        stConceptionMap.put("磷化工", "nongYe_linHuaGong");
        stConceptionMap.put("猪肉概念", "nongYe_zhuRou");
        stConceptionMap.put("水产养殖", "nongYe_shuiChanYangZhi");
        stConceptionMap.put("转基因", "nongYe_zhuanJiYin");
        stConceptionMap.put("培育钻石", "guiJinShu_peiYuZuanShi");
        stConceptionMap.put("调味品概念", "shiPin_tiaoWeiPin");
        stConceptionMap.put("白酒", "jiuShui_baiJiu");

        stConceptionMap.put("NFT概念", "chuanMei_nft");
        stConceptionMap.put("广电", "chuanMei_guangDian");

        stConceptionMap.put("新冠药物", "xinGuan_xinGuanYaoWu");

        stConceptionMap.put("券商概念", "quanShangGaiNian");
    }
}
