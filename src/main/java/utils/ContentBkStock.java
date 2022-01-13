package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ContentBkStock简介
 *  板块股票
 * @author Administrator
 * @date 2021-12-31 10:02
 */
public class ContentBkStock {
    /**
     * 新能源-能源金属
     */
    public static Map<String, String> mapXnyNengYuanJinShu = new HashMap<>();
    static {
        mapXnyNengYuanJinShu.put("002466", "");//002466	天齐锂业	能源金属
        mapXnyNengYuanJinShu.put("600711", "");//600711	盛屯矿业	能源金属
        mapXnyNengYuanJinShu.put("002240", "");//002240	盛新锂能	能源金属
        mapXnyNengYuanJinShu.put("603799", "");//603799	华友钴业	能源金属
    }
    /**
     * 新能源-电池
     */
    public static Map<String, String> mapXnyDianChi = new HashMap<>();
    static {
        mapXnyDianChi.put("002245", "");//002245	蔚蓝锂芯	电池
        mapXnyDianChi.put("002812", "");//002812	恩捷股份	电池
        mapXnyDianChi.put("002192", "");//002192	融捷股份	电池
        mapXnyDianChi.put("000049", "");//000049	德赛电池	电池
    }

    /**
     * 能源化工-采掘行业
     */
    public static Map<String, String> mapCaiJueHangYe = new HashMap<>();
    static {
        mapCaiJueHangYe.put("000762", "");//000762	西藏矿业	采掘行业
        mapCaiJueHangYe.put("603979", "");//603979	金诚信	采掘行业
        mapCaiJueHangYe.put("603505", "");//603505	金石资源	采掘行业
    }

    /**
     * 金融-银行
     */
    public static Map<String, String> mapYinHang = new HashMap<>();
    static {
        mapYinHang.put("600036", "招商银行");//600036	招商银行	银行
        mapYinHang.put("601838", "成都银行");//601838	成都银行	银行
        mapYinHang.put("002142", "宁波银行");//002142	宁波银行	银行
        mapYinHang.put("601528", "瑞丰银行");//601528	瑞丰银行	银行
        mapYinHang.put("601166", "兴业银行");//601166	兴业银行	银行
    }
    /**
     * 金融-房地产服务
     */
    public static Map<String, String> mapFangDiChanFuWu = new HashMap<>();
    static {
        mapFangDiChanFuWu.put("001914", "招商积余");
    }

    /**
     * 消费-旅游酒店
     */
    public static Map<String, String> mapXiaoFeiLvYouJiuDian = new HashMap<>();
    static {
        mapXiaoFeiLvYouJiuDian.put("600754", "");//600754	锦江酒店	旅游酒店
        mapXiaoFeiLvYouJiuDian.put("600258", "");//600258	首旅酒店	旅游酒店
        mapXiaoFeiLvYouJiuDian.put("601888", "");//601888	中国中免	旅游酒店
    }
    /**
     * 消费-酿酒行业
     */
    public static Map<String, String> mapNiangJiuHangYe = new HashMap<>();
    static {
        mapNiangJiuHangYe.put("600702", "");//600702	舍得酒业	酿酒行业
        mapNiangJiuHangYe.put("600132", "");//600132	重庆啤酒	酿酒行业
        mapNiangJiuHangYe.put("603198", "");//603198	迎驾贡酒	酿酒行业
        mapNiangJiuHangYe.put("603589", "");//603589	口子窖	酿酒行业
    }

    /**
     * 医药-中药
     */
    public static Map<String, String> mapZhongYao = new HashMap<>();
    static {
        mapZhongYao.put("600771", "广誉远");//广誉远	600771
        mapZhongYao.put("600566", "济川药业");
        mapZhongYao.put("600993", "马应龙");
        mapZhongYao.put("600329", "中新药业");
        mapZhongYao.put("000650", "仁和药业");
        mapZhongYao.put("000423", "东阿阿胶");
        mapZhongYao.put("600085", "同仁堂");
    }

    /**
     * 电力-光伏设备
     */
    public static Map<String, String> mapGuangFu = new HashMap<>();
    static {
        mapGuangFu.put("002459", "晶澳科技");
        mapGuangFu.put("603806", "福斯特");
        mapGuangFu.put("002617", "露笑科技");
    }
    /**
     * 电力-风电设备
     */
    public static Map<String, String> mapFengDianSheBei = new HashMap<>();
    static {
        mapFengDianSheBei.put("002531", "天顺风能");
        mapFengDianSheBei.put("601615", "明阳智能");
        mapFengDianSheBei.put("603218", "日月股份");
    }
    /**
     * 电力-电力行业
     */
    public static Map<String, String> mapDianLiHangYe = new HashMap<>();
    static {
        mapDianLiHangYe.put("000155", "川能动力");
        mapDianLiHangYe.put("600032", "浙江新能");
        mapDianLiHangYe.put("600483", "福能股份");
    }
    /**
     * 电力-电网设备
     */
    public static Map<String, String> mapDianWangSheBei = new HashMap<>();
    static {
        mapDianWangSheBei.put("603606", "");//603606	东方电缆	电网设备
        mapDianWangSheBei.put("000400", "");//000400	许继电气	电网设备
        mapDianWangSheBei.put("601877", "");//601877	正泰电器	电网设备
        mapDianWangSheBei.put("601567", "");//601567	三星医疗	电网设备
        mapDianWangSheBei.put("600885", "");//600885	宏发股份	电网设备
        mapDianWangSheBei.put("601126", "");//601126	四方股份	电网设备
        mapDianWangSheBei.put("605222", "");//605222	起帆电缆	电网设备
        mapDianWangSheBei.put("600517", "");//600517	国网英大	电网设备
        mapDianWangSheBei.put("600312", "");//600312	平高电气	电网设备
        mapDianWangSheBei.put("600550", "");//600550	保变电气	电网设备
    }

    /**
     * 医药商业
     */
    public static Map<String, String> mapYiYaoShangYe = new HashMap<>();
    static {
        mapYiYaoShangYe.put("002411", "延安必康");
        mapYiYaoShangYe.put("603939", "益丰药房");
        mapYiYaoShangYe.put("002727", "一心堂");
    }
    /**
     * 生物制品
     */
    public static Map<String, String> mapShengWuZhiPin = new HashMap<>();
    static {
        mapShengWuZhiPin.put("002581", "未名医药");//002581	未名医药	生物制品
        mapShengWuZhiPin.put("000403", "派林生物");//000403	派林生物	生物制品
        mapShengWuZhiPin.put("000661", "长春高新");//000661	长春高新	生物制品
    }

    /**
     * 工程建设
     */
    public static Map<String, String> mapGongChengJianShe = new HashMap<>();
    static {
        mapGongChengJianShe.put("603098", "森特股份");//603098	森特股份	工程建设
        mapGongChengJianShe.put("601117", "中国化学");//601117	中国化学	工程建设
        mapGongChengJianShe.put("601669", "中国电建");//601669	中国电建	工程建设
    }

    /**
     * 非金属材料
     */
    public static Map<String, String> mapFeiJinShuCaiLiao = new HashMap<>();
    static {
        mapFeiJinShuCaiLiao.put("603260", "合盛硅业");//603260	合盛硅业	非金属材料
        mapFeiJinShuCaiLiao.put("603688", "石英股份");//603688	石英股份	非金属材料
        mapFeiJinShuCaiLiao.put("600516", "方大炭素");//600516	方大炭素	非金属材料
    }

    /**
     * 非金属材料
     */
    public static Map<String, String> mapNongMuSiYu = new HashMap<>();
    static {
        mapNongMuSiYu.put("002041", "登海种业");//002041	登海种业	农牧饲渔
        mapNongMuSiYu.put("000998", "隆平高科");//000998	隆平高科	农牧饲渔
        mapNongMuSiYu.put("002385", "大北农");//002385	大北农	农牧饲渔
//        mapNongMuSiYu.put("", "");//
    }

    /**
     * mapEtfAll -包含行业、指数
     */
    public static Map<String, String> mapBkStockAll = new HashMap<>();
    static {
        mapBkStockAll.putAll(mapZhongYao);
    }

    public static void main(String[] args) {
        Set<String> bizSet = mapBkStockAll.keySet();
        for (String bizCode : bizSet) {
            System.out.print(",'"+bizCode+"'");
        }
        System.out.println();
    }
}
