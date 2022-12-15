package utils;

import ttjj.dto.RankBizDataDiff;
import ttjj.service.KlineService;
import ttjj.service.StockService;

import java.util.*;

import static utils.Content.NUM_MAX_99;

/**
 * 业务-板块
 *
 * @author Administrator
 * @date 2022-03-18 10:09
 */
public class ContMapBizBaord {
    public static Map<String, String> BOARD_NAME_CODE = new HashMap<>();
    public static Map<String, String> BOARD_CODE_NAME = new TreeMap<>();
    /**
     * 板块-分类-资源
     */
    public static Map<String, String> BOARD_TYPE_ZI_YUAN = new HashMap<>();

    static {
        BOARD_TYPE_ZI_YUAN.put("煤炭行业", "BK0437");//11
        BOARD_TYPE_ZI_YUAN.put("石油行业", "BK0464");//23
        BOARD_TYPE_ZI_YUAN.put("采掘行业", "BK1017");//63
        BOARD_TYPE_ZI_YUAN.put("燃气", "BK1028");//68
        BOARD_TYPE_ZI_YUAN.put("能源金属", "BK1015");//61
        BOARD_TYPE_ZI_YUAN.put("有色金属", "BK0478");//32
        BOARD_TYPE_ZI_YUAN.put("小金属", "BK1027");//67
        BOARD_TYPE_ZI_YUAN.put("贵金属", "BK0732");//51
        BOARD_TYPE_ZI_YUAN.put("非金属材料", "BK1020");//66
        BOARD_TYPE_ZI_YUAN.put("钢铁行业", "BK0479");//33
        BOARD_TYPE_ZI_YUAN.put("化学原料", "BK1019");//65
        BOARD_TYPE_ZI_YUAN.put("化学制品", "BK0538");//40
        BOARD_TYPE_ZI_YUAN.put("化纤行业", "BK0471");//26
        BOARD_TYPE_ZI_YUAN.put("玻璃玻纤", "BK0546");//43
        BOARD_TYPE_ZI_YUAN.put("橡胶制品", "BK1018");//64
        BOARD_TYPE_ZI_YUAN.put("塑料制品", "BK0454");//18
        BOARD_TYPE_ZI_YUAN.put("包装材料", "BK0733");//52
        BOARD_TYPE_ZI_YUAN.put("造纸印刷", "BK0470");//25
        BOARD_TYPE_ZI_YUAN.put("农牧饲渔", "BK0433");//9
        BOARD_TYPE_ZI_YUAN.put("农药兽药", "BK0730");//49
        BOARD_TYPE_ZI_YUAN.put("化肥行业", "BK0731");//50
    }

    /**
     * 板块-分类-金融-基建
     */
    public static Map<String, String> BOARD_TYPE_JIN_RONG_JI_JIAN = new HashMap<>();

    static {
        BOARD_TYPE_JIN_RONG_JI_JIAN.put("工程建设", "BK0425");//5
        BOARD_TYPE_JIN_RONG_JI_JIAN.put("铁路公路", "BK0421");//2
        BOARD_TYPE_JIN_RONG_JI_JIAN.put("水泥建材", "BK0424");//4
        BOARD_TYPE_JIN_RONG_JI_JIAN.put("公用事业", "BK0427");//6
        BOARD_TYPE_JIN_RONG_JI_JIAN.put("房地产开发", "BK0451");//17
        BOARD_TYPE_JIN_RONG_JI_JIAN.put("装修建材", "BK0476");//30
        BOARD_TYPE_JIN_RONG_JI_JIAN.put("装修装饰", "BK0725");//44
        BOARD_TYPE_JIN_RONG_JI_JIAN.put("工程咨询服务", "BK0726");//45
        BOARD_TYPE_JIN_RONG_JI_JIAN.put("房地产服务", "BK1045");//85
        BOARD_TYPE_JIN_RONG_JI_JIAN.put("工程机械", "BK0739");//58
    }

    /**
     * 板块-分类-金融-机构
     */
    public static Map<String, String> BOARD_TYPE_JIN_RONG_JI_GOU = new HashMap<>();

    static {
        BOARD_TYPE_JIN_RONG_JI_GOU.put("证券", "BK0473");//27
        BOARD_TYPE_JIN_RONG_JI_GOU.put("保险", "BK0474");//28
        BOARD_TYPE_JIN_RONG_JI_GOU.put("银行", "BK0475");//29
        BOARD_TYPE_JIN_RONG_JI_GOU.put("多元金融", "BK0738");//57
    }

    /**
     * 板块-分类-金融：基建，机构
     */
    public static Map<String, String> BOARD_TYPE_JIN_RONG = new HashMap<>();

    static {
        BOARD_TYPE_JIN_RONG.putAll(BOARD_TYPE_JIN_RONG_JI_JIAN);
        BOARD_TYPE_JIN_RONG.putAll(BOARD_TYPE_JIN_RONG_JI_GOU);
    }

    /**
     * 板块-分类-科技-芯片
     */
    public static Map<String, String> BOARD_TYPE_KE_JI_XIN_PIAN = new HashMap<>();


    static {
        BOARD_TYPE_KE_JI_XIN_PIAN.put("半导体", "BK1036");//76
        BOARD_TYPE_KE_JI_XIN_PIAN.put("消费电子", "BK1037");//77
        BOARD_TYPE_KE_JI_XIN_PIAN.put("光学光电子", "BK1038");//78
        BOARD_TYPE_KE_JI_XIN_PIAN.put("电子化学品", "BK1039");//79
    }

    /**
     * 板块-分类-科技:电力：风电设备、光伏设备
     */
    public static Map<String, String> BOARD_TYPE_KE_JI_DIAN = new HashMap<>();

    static {
        BOARD_TYPE_KE_JI_DIAN.put("风电设备", "BK1032");//72
        BOARD_TYPE_KE_JI_DIAN.put("光伏设备", "BK1031");//71
        BOARD_TYPE_KE_JI_DIAN.put("电力行业", "BK0428");//7
        BOARD_TYPE_KE_JI_DIAN.put("电网设备", "BK0457");//20
        BOARD_TYPE_KE_JI_DIAN.put("电机", "BK1030");//70
        BOARD_TYPE_KE_JI_DIAN.put("电池", "BK1033");//73
        BOARD_TYPE_KE_JI_DIAN.put("电源设备", "BK1034");//74
    }

    /**
     * 板块-分类-科技:芯片
     */
    public static Map<String, String> BOARD_TYPE_KE_JI = new HashMap<>();

    static {
        BOARD_TYPE_KE_JI.putAll(BOARD_TYPE_KE_JI_XIN_PIAN);
        BOARD_TYPE_KE_JI.putAll(BOARD_TYPE_KE_JI_DIAN);

        BOARD_NAME_CODE.put("交运设备", "BK0429");//8
        BOARD_NAME_CODE.put("家用轻工", "BK0440");//13
        BOARD_NAME_CODE.put("互联网服务", "BK0447");//14
        BOARD_NAME_CODE.put("通信设备", "BK0448");//15
        BOARD_NAME_CODE.put("仪器仪表", "BK0458");//21
        BOARD_NAME_CODE.put("航天航空", "BK0480");//34
        BOARD_NAME_CODE.put("汽车零部件", "BK0481");//35
        BOARD_NAME_CODE.put("通用设备", "BK0545");//42
        BOARD_NAME_CODE.put("环保行业", "BK0728");//47
        BOARD_NAME_CODE.put("船舶制造", "BK0729");//48
        BOARD_NAME_CODE.put("包装材料", "BK0733");//52
        BOARD_NAME_CODE.put("计算机设备", "BK0735");//54
        BOARD_NAME_CODE.put("通信服务", "BK0736");//55
        BOARD_NAME_CODE.put("软件开发", "BK0737");//56
        BOARD_NAME_CODE.put("教育", "BK0740");//59
        BOARD_NAME_CODE.put("专用设备", "BK0910");//60
        BOARD_NAME_CODE.put("能源金属", "BK1015");//61
        BOARD_NAME_CODE.put("汽车服务", "BK1016");//62
        BOARD_NAME_CODE.put("汽车整车", "BK1029");//69
        BOARD_NAME_CODE.put("专业服务", "BK1043");//83
        BOARD_NAME_CODE.put("游戏", "BK1046");//86
        BOARD_NAME_CODE.put("综合行业", "BK0539");//41
    }

    /**
     * 板块-分类-医疗
     */
    public static Map<String, String> BOARD_TYPE_YI_LIAO = new HashMap<>();
    static {
        //医疗
        BOARD_TYPE_YI_LIAO.put("化学制药", "BK0465");//24
        BOARD_TYPE_YI_LIAO.put("医疗服务", "BK0727");//46
        BOARD_TYPE_YI_LIAO.put("中药", "BK1040");//80
        BOARD_TYPE_YI_LIAO.put("医疗器械", "BK1041");//81
        BOARD_TYPE_YI_LIAO.put("医药商业", "BK1042");//82
        BOARD_TYPE_YI_LIAO.put("生物制品", "BK1044");//84
    }

    /**
     * 板块-分类-医疗
     */
    public static Map<String, String> BOARD_TYPE_XIAO_FEI = new HashMap<>();
    static {
        // 消费
        BOARD_TYPE_XIAO_FEI.put("航空机场", "BK0420");//1
        BOARD_TYPE_XIAO_FEI.put("物流行业", "BK0422");//3
        BOARD_TYPE_XIAO_FEI.put("纺织服装", "BK0436");//10
        BOARD_TYPE_XIAO_FEI.put("食品饮料", "BK0438");//12
        BOARD_TYPE_XIAO_FEI.put("家电行业", "BK0456");//19
        BOARD_TYPE_XIAO_FEI.put("酿酒行业", "BK0477");//31
        BOARD_TYPE_XIAO_FEI.put("商业百货", "BK0482");//36
        BOARD_TYPE_XIAO_FEI.put("贸易行业", "BK0484");//37
        BOARD_TYPE_XIAO_FEI.put("旅游酒店", "BK0485");//38
        BOARD_TYPE_XIAO_FEI.put("文化传媒", "BK0486");//39
        BOARD_TYPE_XIAO_FEI.put("美容护理", "BK1035");//75
        BOARD_TYPE_XIAO_FEI.put("游戏", "BK1046");//86
    }

    //分类
    static {
        //金融
        BOARD_NAME_CODE.put("工程建设", "BK0425");//5
        BOARD_NAME_CODE.put("铁路公路", "BK0421");//2
        BOARD_NAME_CODE.put("水泥建材", "BK0424");//4
        BOARD_NAME_CODE.put("公用事业", "BK0427");//6
        BOARD_NAME_CODE.put("房地产开发", "BK0451");//17
        BOARD_NAME_CODE.put("证券", "BK0473");//27
        BOARD_NAME_CODE.put("保险", "BK0474");//28
        BOARD_NAME_CODE.put("银行", "BK0475");//29
        BOARD_NAME_CODE.put("装修建材", "BK0476");//30
        BOARD_NAME_CODE.put("综合行业", "BK0539");//41
        BOARD_NAME_CODE.put("装修装饰", "BK0725");//44
        BOARD_NAME_CODE.put("工程咨询服务", "BK0726");//45
        BOARD_NAME_CODE.put("多元金融", "BK0738");//57
        BOARD_NAME_CODE.put("工程机械", "BK0739");//58
        BOARD_NAME_CODE.put("房地产服务", "BK1045");//85

        // 消费
        BOARD_NAME_CODE.put("航空机场", "BK0420");//1
        BOARD_NAME_CODE.put("物流行业", "BK0422");//3
        BOARD_NAME_CODE.put("纺织服装", "BK0436");//10
        BOARD_NAME_CODE.put("食品饮料", "BK0438");//12
        BOARD_NAME_CODE.put("家电行业", "BK0456");//19
        BOARD_NAME_CODE.put("酿酒行业", "BK0477");//31
        BOARD_NAME_CODE.put("商业百货", "BK0482");//36
        BOARD_NAME_CODE.put("贸易行业", "BK0484");//37
        BOARD_NAME_CODE.put("旅游酒店", "BK0485");//38
        BOARD_NAME_CODE.put("文化传媒", "BK0486");//39
        BOARD_NAME_CODE.put("美容护理", "BK1035");//75
        BOARD_NAME_CODE.put("游戏", "BK1046");//86

        //科技
        BOARD_NAME_CODE.put("电力行业", "BK0428");//7
        BOARD_NAME_CODE.put("交运设备", "BK0429");//8
        BOARD_NAME_CODE.put("家用轻工", "BK0440");//13
        BOARD_NAME_CODE.put("互联网服务", "BK0447");//14
        BOARD_NAME_CODE.put("通信设备", "BK0448");//15
        BOARD_NAME_CODE.put("电网设备", "BK0457");//20
        BOARD_NAME_CODE.put("仪器仪表", "BK0458");//21
        BOARD_NAME_CODE.put("航天航空", "BK0480");//34
        BOARD_NAME_CODE.put("汽车零部件", "BK0481");//35
        BOARD_NAME_CODE.put("通用设备", "BK0545");//42
        BOARD_NAME_CODE.put("环保行业", "BK0728");//47
        BOARD_NAME_CODE.put("船舶制造", "BK0729");//48
        BOARD_NAME_CODE.put("包装材料", "BK0733");//52
        BOARD_NAME_CODE.put("计算机设备", "BK0735");//54
        BOARD_NAME_CODE.put("通信服务", "BK0736");//55
        BOARD_NAME_CODE.put("软件开发", "BK0737");//56
        BOARD_NAME_CODE.put("教育", "BK0740");//59
        BOARD_NAME_CODE.put("专用设备", "BK0910");//60
        BOARD_NAME_CODE.put("能源金属", "BK1015");//61
        BOARD_NAME_CODE.put("汽车服务", "BK1016");//62
        BOARD_NAME_CODE.put("汽车整车", "BK1029");//69
        BOARD_NAME_CODE.put("电机", "BK1030");//70
        BOARD_NAME_CODE.put("光伏设备", "BK1031");//71
        BOARD_NAME_CODE.put("风电设备", "BK1032");//72
        BOARD_NAME_CODE.put("电池", "BK1033");//73
        BOARD_NAME_CODE.put("电源设备", "BK1034");//74
        BOARD_NAME_CODE.put("半导体", "BK1036");//76
        BOARD_NAME_CODE.put("消费电子", "BK1037");//77
        BOARD_NAME_CODE.put("光学光电子", "BK1038");//78
        BOARD_NAME_CODE.put("电子化学品", "BK1039");//79
        BOARD_NAME_CODE.put("专业服务", "BK1043");//83

        BOARD_NAME_CODE.put("综合行业", "BK0539");//41

        //资源
        BOARD_NAME_CODE.put("农牧饲渔", "BK0433");//9
        BOARD_NAME_CODE.put("煤炭行业", "BK0437");//11
        BOARD_NAME_CODE.put("航运港口", "BK0450");//16
        BOARD_NAME_CODE.put("塑料制品", "BK0454");//18
        BOARD_NAME_CODE.put("电子元件", "BK0459");//22
        BOARD_NAME_CODE.put("石油行业", "BK0464");//23
        BOARD_NAME_CODE.put("造纸印刷", "BK0470");//25
        BOARD_NAME_CODE.put("化纤行业", "BK0471");//26
        BOARD_NAME_CODE.put("有色金属", "BK0478");//32
        BOARD_NAME_CODE.put("钢铁行业", "BK0479");//33
        BOARD_NAME_CODE.put("化学制品", "BK0538");//40
        BOARD_NAME_CODE.put("玻璃玻纤", "BK0546");//43
        BOARD_NAME_CODE.put("农药兽药", "BK0730");//49
        BOARD_NAME_CODE.put("化肥行业", "BK0731");//50
        BOARD_NAME_CODE.put("贵金属", "BK0732");//51
        BOARD_NAME_CODE.put("珠宝首饰", "BK0734");//53
        BOARD_NAME_CODE.put("采掘行业", "BK1017");//63
        BOARD_NAME_CODE.put("橡胶制品", "BK1018");//64
        BOARD_NAME_CODE.put("化学原料", "BK1019");//65
        BOARD_NAME_CODE.put("非金属材料", "BK1020");//66
        BOARD_NAME_CODE.put("小金属", "BK1027");//67
        BOARD_NAME_CODE.put("燃气", "BK1028");//68

        //医疗
        BOARD_NAME_CODE.put("化学制药", "BK0465");//24
        BOARD_NAME_CODE.put("医疗服务", "BK0727");//46
        BOARD_NAME_CODE.put("中药", "BK1040");//80
        BOARD_NAME_CODE.put("医疗器械", "BK1041");//81
        BOARD_NAME_CODE.put("医药商业", "BK1042");//82
        BOARD_NAME_CODE.put("生物制品", "BK1044");//84

    }

    static {
        BOARD_NAME_CODE.put("航空机场", "BK0420");//1
        BOARD_NAME_CODE.put("铁路公路", "BK0421");//2
        BOARD_NAME_CODE.put("物流行业", "BK0422");//3
        BOARD_NAME_CODE.put("水泥建材", "BK0424");//4
        BOARD_NAME_CODE.put("工程建设", "BK0425");//5
        BOARD_NAME_CODE.put("公用事业", "BK0427");//6
        BOARD_NAME_CODE.put("电力行业", "BK0428");//7
        BOARD_NAME_CODE.put("交运设备", "BK0429");//8
        BOARD_NAME_CODE.put("农牧饲渔", "BK0433");//9
        BOARD_NAME_CODE.put("纺织服装", "BK0436");//10
        BOARD_NAME_CODE.put("煤炭行业", "BK0437");//11
        BOARD_NAME_CODE.put("食品饮料", "BK0438");//12
        BOARD_NAME_CODE.put("家用轻工", "BK0440");//13
        BOARD_NAME_CODE.put("互联网服务", "BK0447");//14
        BOARD_NAME_CODE.put("通信设备", "BK0448");//15
        BOARD_NAME_CODE.put("航运港口", "BK0450");//16
        BOARD_NAME_CODE.put("房地产开发", "BK0451");//17
        BOARD_NAME_CODE.put("塑料制品", "BK0454");//18
        BOARD_NAME_CODE.put("家电行业", "BK0456");//19
        BOARD_NAME_CODE.put("电网设备", "BK0457");//20
        BOARD_NAME_CODE.put("仪器仪表", "BK0458");//21
        BOARD_NAME_CODE.put("电子元件", "BK0459");//22
        BOARD_NAME_CODE.put("石油行业", "BK0464");//23
        BOARD_NAME_CODE.put("化学制药", "BK0465");//24
        BOARD_NAME_CODE.put("造纸印刷", "BK0470");//25
        BOARD_NAME_CODE.put("化纤行业", "BK0471");//26
        BOARD_NAME_CODE.put("证券", "BK0473");//27
        BOARD_NAME_CODE.put("保险", "BK0474");//28
        BOARD_NAME_CODE.put("银行", "BK0475");//29
        BOARD_NAME_CODE.put("装修建材", "BK0476");//30
        BOARD_NAME_CODE.put("酿酒行业", "BK0477");//31
        BOARD_NAME_CODE.put("有色金属", "BK0478");//32
        BOARD_NAME_CODE.put("钢铁行业", "BK0479");//33
        BOARD_NAME_CODE.put("航天航空", "BK0480");//34
        BOARD_NAME_CODE.put("汽车零部件", "BK0481");//35
        BOARD_NAME_CODE.put("商业百货", "BK0482");//36
        BOARD_NAME_CODE.put("贸易行业", "BK0484");//37
        BOARD_NAME_CODE.put("旅游酒店", "BK0485");//38
        BOARD_NAME_CODE.put("文化传媒", "BK0486");//39
        BOARD_NAME_CODE.put("化学制品", "BK0538");//40
        BOARD_NAME_CODE.put("综合行业", "BK0539");//41
        BOARD_NAME_CODE.put("通用设备", "BK0545");//42
        BOARD_NAME_CODE.put("玻璃玻纤", "BK0546");//43
        BOARD_NAME_CODE.put("装修装饰", "BK0725");//44
        BOARD_NAME_CODE.put("工程咨询服务", "BK0726");//45
        BOARD_NAME_CODE.put("医疗服务", "BK0727");//46
        BOARD_NAME_CODE.put("环保行业", "BK0728");//47
        BOARD_NAME_CODE.put("船舶制造", "BK0729");//48
        BOARD_NAME_CODE.put("农药兽药", "BK0730");//49
        BOARD_NAME_CODE.put("化肥行业", "BK0731");//50
        BOARD_NAME_CODE.put("贵金属", "BK0732");//51
        BOARD_NAME_CODE.put("包装材料", "BK0733");//52
        BOARD_NAME_CODE.put("珠宝首饰", "BK0734");//53
        BOARD_NAME_CODE.put("计算机设备", "BK0735");//54
        BOARD_NAME_CODE.put("通信服务", "BK0736");//55
        BOARD_NAME_CODE.put("软件开发", "BK0737");//56
        BOARD_NAME_CODE.put("多元金融", "BK0738");//57
        BOARD_NAME_CODE.put("工程机械", "BK0739");//58
        BOARD_NAME_CODE.put("教育", "BK0740");//59
        BOARD_NAME_CODE.put("专用设备", "BK0910");//60
        BOARD_NAME_CODE.put("能源金属", "BK1015");//61
        BOARD_NAME_CODE.put("汽车服务", "BK1016");//62
        BOARD_NAME_CODE.put("采掘行业", "BK1017");//63
        BOARD_NAME_CODE.put("橡胶制品", "BK1018");//64
        BOARD_NAME_CODE.put("化学原料", "BK1019");//65
        BOARD_NAME_CODE.put("非金属材料", "BK1020");//66
        BOARD_NAME_CODE.put("小金属", "BK1027");//67
        BOARD_NAME_CODE.put("燃气", "BK1028");//68
        BOARD_NAME_CODE.put("汽车整车", "BK1029");//69
        BOARD_NAME_CODE.put("电机", "BK1030");//70
        BOARD_NAME_CODE.put("光伏设备", "BK1031");//71
        BOARD_NAME_CODE.put("风电设备", "BK1032");//72
        BOARD_NAME_CODE.put("电池", "BK1033");//73
        BOARD_NAME_CODE.put("电源设备", "BK1034");//74
        BOARD_NAME_CODE.put("美容护理", "BK1035");//75
        BOARD_NAME_CODE.put("半导体", "BK1036");//76
        BOARD_NAME_CODE.put("消费电子", "BK1037");//77
        BOARD_NAME_CODE.put("光学光电子", "BK1038");//78
        BOARD_NAME_CODE.put("电子化学品", "BK1039");//79
        BOARD_NAME_CODE.put("中药", "BK1040");//80
        BOARD_NAME_CODE.put("医疗器械", "BK1041");//81
        BOARD_NAME_CODE.put("医药商业", "BK1042");//82
        BOARD_NAME_CODE.put("专业服务", "BK1043");//83
        BOARD_NAME_CODE.put("生物制品", "BK1044");//84
        BOARD_NAME_CODE.put("房地产服务", "BK1045");//85
        BOARD_NAME_CODE.put("游戏", "BK1046");//86
    }

    static {
        BOARD_CODE_NAME.put("BK0420", "航空机场");//1
        BOARD_CODE_NAME.put("BK0421", "铁路公路");//2
        BOARD_CODE_NAME.put("BK0422", "物流行业");//3
        BOARD_CODE_NAME.put("BK0424", "水泥建材");//4
        BOARD_CODE_NAME.put("BK0425", "工程建设");//5
        BOARD_CODE_NAME.put("BK0427", "公用事业");//6
        BOARD_CODE_NAME.put("BK0428", "电力行业");//7
        BOARD_CODE_NAME.put("BK0429", "交运设备");//8
        BOARD_CODE_NAME.put("BK0433", "农牧饲渔");//9
        BOARD_CODE_NAME.put("BK0436", "纺织服装");//10
        BOARD_CODE_NAME.put("BK0437", "煤炭行业");//11
        BOARD_CODE_NAME.put("BK0438", "食品饮料");//12
        BOARD_CODE_NAME.put("BK0440", "家用轻工");//13
        BOARD_CODE_NAME.put("BK0447", "互联网服务");//14
        BOARD_CODE_NAME.put("BK0448", "通信设备");//15
        BOARD_CODE_NAME.put("BK0450", "航运港口");//16
        BOARD_CODE_NAME.put("BK0451", "房地产开发");//17
        BOARD_CODE_NAME.put("BK0454", "塑料制品");//18
        BOARD_CODE_NAME.put("BK0456", "家电行业");//19
        BOARD_CODE_NAME.put("BK0457", "电网设备");//20
        BOARD_CODE_NAME.put("BK0458", "仪器仪表");//21
        BOARD_CODE_NAME.put("BK0459", "电子元件");//22
        BOARD_CODE_NAME.put("BK0464", "石油行业");//23
        BOARD_CODE_NAME.put("BK0465", "化学制药");//24
        BOARD_CODE_NAME.put("BK0470", "造纸印刷");//25
        BOARD_CODE_NAME.put("BK0471", "化纤行业");//26
        BOARD_CODE_NAME.put("BK0473", "证券");//27
        BOARD_CODE_NAME.put("BK0474", "保险");//28
        BOARD_CODE_NAME.put("BK0475", "银行");//29
        BOARD_CODE_NAME.put("BK0476", "装修建材");//30
        BOARD_CODE_NAME.put("BK0477", "酿酒行业");//31
        BOARD_CODE_NAME.put("BK0478", "有色金属");//32
        BOARD_CODE_NAME.put("BK0479", "钢铁行业");//33
        BOARD_CODE_NAME.put("BK0480", "航天航空");//34
        BOARD_CODE_NAME.put("BK0481", "汽车零部件");//35
        BOARD_CODE_NAME.put("BK0482", "商业百货");//36
        BOARD_CODE_NAME.put("BK0484", "贸易行业");//37
        BOARD_CODE_NAME.put("BK0485", "旅游酒店");//38
        BOARD_CODE_NAME.put("BK0486", "文化传媒");//39
        BOARD_CODE_NAME.put("BK0538", "化学制品");//40
        BOARD_CODE_NAME.put("BK0539", "综合行业");//41
        BOARD_CODE_NAME.put("BK0545", "通用设备");//42
        BOARD_CODE_NAME.put("BK0546", "玻璃玻纤");//43
        BOARD_CODE_NAME.put("BK0725", "装修装饰");//44
        BOARD_CODE_NAME.put("BK0726", "工程咨询服务");//45
        BOARD_CODE_NAME.put("BK0727", "医疗服务");//46
        BOARD_CODE_NAME.put("BK0728", "环保行业");//47
        BOARD_CODE_NAME.put("BK0729", "船舶制造");//48
        BOARD_CODE_NAME.put("BK0730", "农药兽药");//49
        BOARD_CODE_NAME.put("BK0731", "化肥行业");//50
        BOARD_CODE_NAME.put("BK0732", "贵金属");//51
        BOARD_CODE_NAME.put("BK0733", "包装材料");//52
        BOARD_CODE_NAME.put("BK0734", "珠宝首饰");//53
        BOARD_CODE_NAME.put("BK0735", "计算机设备");//54
        BOARD_CODE_NAME.put("BK0736", "通信服务");//55
        BOARD_CODE_NAME.put("BK0737", "软件开发");//56
        BOARD_CODE_NAME.put("BK0738", "多元金融");//57
        BOARD_CODE_NAME.put("BK0739", "工程机械");//58
        BOARD_CODE_NAME.put("BK0740", "教育");//59
        BOARD_CODE_NAME.put("BK0910", "专用设备");//60
        BOARD_CODE_NAME.put("BK1015", "能源金属");//61
        BOARD_CODE_NAME.put("BK1016", "汽车服务");//62
        BOARD_CODE_NAME.put("BK1017", "采掘行业");//63
        BOARD_CODE_NAME.put("BK1018", "橡胶制品");//64
        BOARD_CODE_NAME.put("BK1019", "化学原料");//65
        BOARD_CODE_NAME.put("BK1020", "非金属材料");//66
        BOARD_CODE_NAME.put("BK1027", "小金属");//67
        BOARD_CODE_NAME.put("BK1028", "燃气");//68
        BOARD_CODE_NAME.put("BK1029", "汽车整车");//69
        BOARD_CODE_NAME.put("BK1030", "电机");//70
        BOARD_CODE_NAME.put("BK1031", "光伏设备");//71
        BOARD_CODE_NAME.put("BK1032", "风电设备");//72
        BOARD_CODE_NAME.put("BK1033", "电池");//73
        BOARD_CODE_NAME.put("BK1034", "电源设备");//74
        BOARD_CODE_NAME.put("BK1035", "美容护理");//75
        BOARD_CODE_NAME.put("BK1036", "半导体");//76
        BOARD_CODE_NAME.put("BK1037", "消费电子");//77
        BOARD_CODE_NAME.put("BK1038", "光学光电子");//78
        BOARD_CODE_NAME.put("BK1039", "电子化学品");//79
        BOARD_CODE_NAME.put("BK1040", "中药");//80
        BOARD_CODE_NAME.put("BK1041", "医疗器械");//81
        BOARD_CODE_NAME.put("BK1042", "医药商业");//82
        BOARD_CODE_NAME.put("BK1043", "专业服务");//83
        BOARD_CODE_NAME.put("BK1044", "生物制品");//84
        BOARD_CODE_NAME.put("BK1045", "房地产服务");//85
        BOARD_CODE_NAME.put("BK1046", "游戏");//86
    }

    public static void main(String[] args) {
//        showBoard();//显示所有板块
        showBoardList();//显示板块列表

    }

    /**
     * 显示板块列表
     */
    private static void showBoardList() {
        Map<String, String> mapBiz = new HashMap<>();//业务
//        mapBiz = ContMapBizBaord.BOARD_TYPE_JIN_RONG_JI_GOU;//金融：BOARD_TYPE_JIN_RONG   BOARD_TYPE_JIN_RONG_JI_GOU   BOARD_TYPE_ZI_YUAN
//        mapBiz = ContMapBizBaord.BOARD_TYPE_KE_JI_DIAN;//板块-分类-科技:电力：BOARD_TYPE_KE_JI_XIN_PIAN BOARD_TYPE_KE_JI_FENG_DIAN BOARD_TYPE_KE_JI_GUANG_FU
//        mapBiz = ContMapBizBaord.BOARD_TYPE_JIN_RONG_JI_JIAN;//板块-分类-金融-基建
//        mapBiz = ContMapBizBaord.BOARD_TYPE_ZI_YUAN;//资源
//        mapBiz = ContMapBizBaord.BOARD_TYPE_YI_LIAO;//医疗
//        mapBiz = ContMapBizBaord.BOARD_TYPE_XIAO_FEI;//医疗
        mapBiz = ContMapBizBaord.BOARD_TYPE_KE_JI_XIN_PIAN;//医疗
        boolean isFirst = true;
        System.out.print("Arrays.asList(");
        for (String bk : mapBiz.keySet()) {
            if (!isFirst) {
                System.out.print(",");
            }
            isFirst = false;
            System.out.print("\"" + bk + "\"");
        }
        System.out.println(");");
    }

    /**
     * 显示所有板块
     */
    private static void showBoard() {
        List<RankBizDataDiff> bizList = StockService.listBiz(NUM_MAX_99);//查询业务列表
        int count = 0;
        for (RankBizDataDiff biz : bizList) {
            String code = biz.getF12();
            String name = biz.getF14();
//            System.out.println("BOARD.put(\"" + biz.getF12() + "\", \"" + biz.getF14() + "\");//" + (++count));
//            System.out.println("BOARD.put(\"" + biz.getF14() + "\", \"" + biz.getF12() + "\");//" + (++count));
            BOARD_CODE_NAME.put(code, name);
        }

        for (String code : BOARD_CODE_NAME.keySet()) {
//            System.out.println("BOARD_CODE_NAME.put(\"" + code + "\", \"" + BOARD_CODE_NAME.get(code) + "\");//" + (++count));
            System.out.println("BOARD_NAME_CODE.put(\"" + BOARD_CODE_NAME.get(code) + "\", \"" + code + "\");//" + (++count));
        }

//        System.out.println();
//        System.out.println("截止2022-05-27共有86个板块");
//        if (count != 86) {
//            System.out.println("业务板块可能发生变更，请确认");
//        }
    }
}
