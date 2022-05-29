package utils;

import ttjj.dto.RankBizDataDiff;
import ttjj.service.KlineService;
import ttjj.service.StockService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Content.NUM_MAX_99;

/**
 * 业务-板块
 *
 * @author Administrator
 * @date 2022-03-18 10:09
 */
public class ContMapBizBaord {
    /**
     * meiTanHangYe
     */
    public static Map<String, String> BOARD = new HashMap<>();

    static {
        BOARD.put("采掘行业", "BK1017");//1
        BOARD.put("石油行业", "BK0464");//2
        BOARD.put("煤炭行业", "BK0437");//3
        BOARD.put("化学原料", "BK1019");//4
        BOARD.put("汽车服务", "BK1016");//5
        BOARD.put("医疗服务", "BK0727");//6
        BOARD.put("化肥行业", "BK0731");//7
        BOARD.put("工程建设", "BK0425");//8
        BOARD.put("汽车整车", "BK1029");//9
        BOARD.put("教育", "BK0740");//10
        BOARD.put("工程咨询服务", "BK0726");//11
        BOARD.put("交运设备", "BK0429");//12
        BOARD.put("物流行业", "BK0422");//13
        BOARD.put("化学制品", "BK0538");//14
        BOARD.put("旅游酒店", "BK0485");//15
        BOARD.put("电池", "BK1033");//16
        BOARD.put("农药兽药", "BK0730");//17
        BOARD.put("化纤行业", "BK0471");//18
        BOARD.put("珠宝首饰", "BK0734");//19
        BOARD.put("航运港口", "BK0450");//20
        BOARD.put("钢铁行业", "BK0479");//21
        BOARD.put("美容护理", "BK1035");//22
        BOARD.put("专业服务", "BK1043");//23
        BOARD.put("电机", "BK1030");//24
        BOARD.put("装修装饰", "BK0725");//25
        BOARD.put("非金属材料", "BK1020");//26
        BOARD.put("化学制药", "BK0465");//27
        BOARD.put("航空机场", "BK0420");//28
        BOARD.put("橡胶制品", "BK1018");//29
        BOARD.put("水泥建材", "BK0424");//30
        BOARD.put("铁路公路", "BK0421");//31
        BOARD.put("仪器仪表", "BK0458");//32
        BOARD.put("综合行业", "BK0539");//33
        BOARD.put("公用事业", "BK0427");//34
        BOARD.put("家电行业", "BK0456");//35
        BOARD.put("消费电子", "BK1037");//36
        BOARD.put("通用设备", "BK0545");//37
        BOARD.put("生物制品", "BK1044");//38
        BOARD.put("农牧饲渔", "BK0433");//39
        BOARD.put("贵金属", "BK0732");//40
        BOARD.put("玻璃玻纤", "BK0546");//41
        BOARD.put("专用设备", "BK0910");//42
        BOARD.put("塑料制品", "BK0454");//43
        BOARD.put("汽车零部件", "BK0481");//44
        BOARD.put("小金属", "BK1027");//45
        BOARD.put("保险", "BK0474");//46
        BOARD.put("中药", "BK1040");//47
        BOARD.put("证券", "BK0473");//48
        BOARD.put("包装材料", "BK0733");//49
        BOARD.put("贸易行业", "BK0484");//50
        BOARD.put("电子化学品", "BK1039");//51
        BOARD.put("环保行业", "BK0728");//52
        BOARD.put("能源金属", "BK1015");//53
        BOARD.put("银行", "BK0475");//54
        BOARD.put("医疗器械", "BK1041");//55
        BOARD.put("通信服务", "BK0736");//56
        BOARD.put("酿酒行业", "BK0477");//57
        BOARD.put("造纸印刷", "BK0470");//58
        BOARD.put("电子元件", "BK0459");//59
        BOARD.put("装修建材", "BK0476");//60
        BOARD.put("商业百货", "BK0482");//61
        BOARD.put("食品饮料", "BK0438");//62
        BOARD.put("有色金属", "BK0478");//63
        BOARD.put("房地产服务", "BK1045");//64
        BOARD.put("电力行业", "BK0428");//65
        BOARD.put("电网设备", "BK0457");//66
        BOARD.put("光伏设备", "BK1031");//67
        BOARD.put("通信设备", "BK0448");//68
        BOARD.put("燃气", "BK1028");//69
        BOARD.put("房地产开发", "BK0451");//70
        BOARD.put("家用轻工", "BK0440");//71
        BOARD.put("工程机械", "BK0739");//72
        BOARD.put("光学光电子", "BK1038");//73
        BOARD.put("互联网服务", "BK0447");//74
        BOARD.put("半导体", "BK1036");//75
        BOARD.put("航天航空", "BK0480");//76
        BOARD.put("计算机设备", "BK0735");//77
        BOARD.put("纺织服装", "BK0436");//78
        BOARD.put("软件开发", "BK0737");//79
        BOARD.put("文化传媒", "BK0486");//80
        BOARD.put("多元金融", "BK0738");//81
        BOARD.put("船舶制造", "BK0729");//82
        BOARD.put("医药商业", "BK1042");//83
        BOARD.put("电源设备", "BK1034");//84
        BOARD.put("游戏", "BK1046");//85
        BOARD.put("风电设备", "BK1032");//86
    }


    public static void main(String[] args) {
        List<RankBizDataDiff> bizList = StockService.listBiz(NUM_MAX_99);//查询业务列表
        int count = 0;
        for (RankBizDataDiff biz : bizList) {
//            System.out.println("BOARD.put(\"" + biz.getF12() + "\", \"" + biz.getF14() + "\");//" + (++count));
            System.out.println("BOARD.put(\"" + biz.getF14() + "\", \"" + biz.getF12() + "\");//" + (++count));
        }

//        System.out.println();
//        System.out.println("截止2022-05-27共有86个板块");
//        if (count != 86) {
//            System.out.println("业务板块可能发生变更，请确认");
//        }
    }
}
