package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 常量-股票-概念
 *
 * @author Administrator
 * @date 2022-02-06 22:18
 */
public class ContStConc {
    /**
     * 在线旅游 
     */
    public static Map<String, String> mapZaiXianLvYou = new HashMap<>();
    static {
        mapZaiXianLvYou.put("000796", "凯撒旅业");//000796 凯撒旅业 211 旅游酒店 
        mapZaiXianLvYou.put("600640", "新国脉");//600640 新国脉   205 文化传媒
//        mapZaiXianLvYou.put("002244", "滨江集团");//002244 滨江集团 200 房地产开发
//        mapZaiXianLvYou.put("600258", "首旅酒店");//600258 首旅酒店 197 旅游酒店
//        mapZaiXianLvYou.put("600138", "中青旅  ");//600138 中青旅   193 旅游酒店
        mapZaiXianLvYou.put("000069", "华侨城Ａ");//000069 华侨城Ａ 187 房地产开发 
//        mapZaiXianLvYou.put("002707", "众信旅游");//002707 众信旅游 124 旅游酒店
//        mapZaiXianLvYou.put("000524", "岭南控股");//000524 岭南控股 73 旅游酒店
//        mapZaiXianLvYou.put("600088", "中视传媒");//600088 中视传媒 13 文化传媒
    }

    /**
     * 盐湖提锂 
     */
    public static Map<String, String> mapYanHuTiLi = new HashMap<>();
    static {
        mapYanHuTiLi.put("600338", "西藏珠峰");//600338 西藏珠峰 295 有色金属 
        mapYanHuTiLi.put("600773", "西藏城投");//600773 西藏城投 291 房地产开发 
        mapYanHuTiLi.put("000762", "西藏矿业");//000762 西藏矿业 284 采掘行业 
        mapYanHuTiLi.put("002240", "盛新锂能");//002240 盛新锂能 269 能源金属 
        mapYanHuTiLi.put("600499", "科达制造");//600499 科达制造 263 专用设备 
        mapYanHuTiLi.put("002256", "兆新股份");//002256 兆新股份 259 电网设备 
        mapYanHuTiLi.put("002466", "天齐锂业");//002466 天齐锂业 251 能源金属 
        mapYanHuTiLi.put("000408", "藏格矿业");//000408 藏格矿业 250 化肥行业 
        mapYanHuTiLi.put("000546", "金圆股份");//000546 金圆股份 245 水泥建材 
        mapYanHuTiLi.put("002140", "东华科技");//002140 东华科技 240 工程建设 
        mapYanHuTiLi.put("601068", "中铝国际");//601068 中铝国际 235 工程建设 
        mapYanHuTiLi.put("002460", "赣锋锂业");//002460 赣锋锂业 229 能源金属 
        mapYanHuTiLi.put("603799", "华友钴业");//603799 华友钴业 229 能源金属 
        mapYanHuTiLi.put("601168", "西部矿业");//601168 西部矿业 219 有色金属 
        mapYanHuTiLi.put("002594", "比亚迪");  //002594 比亚迪   212 汽车整车
        mapYanHuTiLi.put("601899", "紫金矿业");//601899 紫金矿业 203 贵金属 
        mapYanHuTiLi.put("000792", "盐湖股份");//000792 盐湖股份 194 化肥行业 
        mapYanHuTiLi.put("002239", "奥特佳");  //002239 奥特佳   175 汽车零部件
        mapYanHuTiLi.put("603867", "新化股份");//603867 新化股份 47 化学制品 
        mapYanHuTiLi.put("000920", "沃顿科技");//000920 沃顿科技 6 环保行业 
    }

    /**
     * mapAll
     */
    public static Map<String, String> mapAll = new HashMap<>();
    static {
        mapAll.putAll(mapZaiXianLvYou);
        mapAll.putAll(mapYanHuTiLi);
    }
}
