package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ContentEtf简介
 * 常量-etf
 *
 * @author Administrator
 * @date 2021-11-05 23:08
 */
public class ContentEtf {

    /**
     * 常用-etf-行业
     */
    public static Map<String, String> mapEtfBiz = new HashMap<>();
    static {
        mapEtfBiz.put("159869", "游戏ETF");//159869	游戏ETF	4.21
        mapEtfBiz.put("512980", "传媒ETF");//512980	传媒ETF	52.66
        mapEtfBiz.put("518880", "黄金ETF");//518880	黄金ETF	113.78
        mapEtfBiz.put("513100", "纳指ETF");//513100	纳指ETF	29.78
        mapEtfBiz.put("513500", "标普500ETF");//513500	标普500ETF	45.90
        mapEtfBiz.put("159995", "芯片ETF");//159995	芯片ETF	183.65 512760	芯片ETF	133.52   512480	半导体ETF	101.98
        mapEtfBiz.put("515030", "新能源车ETF");//515030	新能源车ETF	96.78       515700	新能车ETF	57.64
        mapEtfBiz.put("515000", "科技ETF");//515000	科技ETF	49.63
        mapEtfBiz.put("515790", "光伏ETF");//515790	光伏ETF	142.07
//        mapEtfBiz.put("159819", "人工智能ETF");//159819	人工智能ETF	21.60
        mapEtfBiz.put("515050", "5GETF");//159994	5GETF	32.40   515050	5GETF	143.85
        mapEtfBiz.put("515880", "通信ETF");//515880	通信ETF	27.50
        mapEtfBiz.put("512690", "酒ETF");//512690	酒ETF	73.21
        mapEtfBiz.put("515170", "食品饮料ETF");//515170	食品饮料ETF	35.21
        mapEtfBiz.put("512170", "医药ETF");//512170	医疗ETF	112.27      512010	医药ETF	49.59
        mapEtfBiz.put("159992", "创新药ETF");//创新药ETF	159992	34.94
        mapEtfBiz.put("159928", "消费ETF");//159928	消费ETF	103.70
        mapEtfBiz.put("159996", "家电ETF");//家电ETF	159996	10.03
        mapEtfBiz.put("512880", "证券ETF");//512880	证券ETF	353.10      512000	券商ETF	237.31      512900	证券ETF基金	88.41
        mapEtfBiz.put("512800", "银行ETF");//512800	银行ETF	91.44   515290	银行ETF天弘	81.57
        mapEtfBiz.put("159940", "金融ETF基金");//159940	金融ETF基金	20.80
        mapEtfBiz.put("512200", "房地产ETF");//512200	房地产ETF	17.53
        mapEtfBiz.put("513050", "中概互联网ETF");//513050	中概互联网ETF	315.74
        mapEtfBiz.put("159920", "恒生ETF");//159920	恒生ETF	96.60
//        mapEtfBiz.put("513330", "恒生互联网ETF");//513330	恒生互联网ETF	137.99      513180	恒生科技指数ETF	34.31
        mapEtfBiz.put("512400", "有色金属ETF");//512400	有色金属ETF	40.66
        mapEtfBiz.put("516150", "稀土ETF基金");//516150	稀土ETF基金	22.37
        mapEtfBiz.put("512660", "军工ETF");//512660	军工ETF	151.61      512680	中证军工ETF	40.29
//        mapEtfBiz.put("515150", "一带一路ETF");//515150	一带一路ETF	12.84
        mapEtfBiz.put("512960", "央调ETF");//512960	央调ETF	71.73   512950	央企改革ETF	68.96   159959	央企ETF	24.79   515600	央企创新ETF	21.75
        mapEtfBiz.put("510880", "红利ETF");//510880	红利ETF	161.92
        mapEtfBiz.put("159825", "农业ETF");//159825	农业ETF	17.81   159827	农业50ETF	2.24    516550	农业ETF	0.81
        mapEtfBiz.put("516760", "养殖ETF");//516760	养殖ETF	1.64
        mapEtfBiz.put("516970", "基建50ETF");//516970	基建50ETF	50.27
        mapEtfBiz.put("515210", "钢铁ETF");//515210	钢铁ETF	18.11
        mapEtfBiz.put("159870", "化工ETF");//159870	化工ETF	7.33
        mapEtfBiz.put("159981", "能源化工ETF");//159981	能源化工ETF	2.96    159930	能源ETF	2.06
        mapEtfBiz.put("515220", "煤炭ETF");//515220	煤炭ETF	31.86
        mapEtfBiz.put("516110", "汽车ETF");//汽车ETF	516110	2.23
        mapEtfBiz.put("159766", "旅游ETF");//旅游ETF	159766	2.29
    }

    /**
     * 常用-etf-指数
     */
    public static Map<String, String> mapEtfIndex = new HashMap<>();
    static {
        mapEtfIndex.put("510050", "上证50ETF");//510050	上证50ETF	549.38
        mapEtfIndex.put("512910", "中证100ETF");// 512910	中证100ETF	5.41
        mapEtfIndex.put("510180", "上证180ETF");//510180	上证180ETF	225.14
        mapEtfIndex.put("510300", "沪深300ETF");//510300	沪深300ETF	405.92      510330	300ETF基金	248.51      159919	沪深300ETF	183.06      510310	沪深300ETF易方达	91.71
        mapEtfIndex.put("510500", "中证500ETF");//  510500	中证500ETF	445.48      510510	中证500ETF基金	28.15
        mapEtfIndex.put("512100", "中证1000ETF");//512100	中证1000ETF	12.77
        mapEtfIndex.put("159949", "创业板50ETF");//159949	创业板50ETF	97.37
        mapEtfIndex.put("159967", "创成长ETF");//创成长ETF	159967	33.83
        mapEtfIndex.put("588000", "科创板50ETF");//588000	科创50ETF	226.26      588080	科创板50ETF	112.41      588090	科创板ETF	31.32
        mapEtfIndex.put("159781", "双创50ETF");// 159781	双创50ETF	81.55 588400	双创50ETF	22.27
    }

    /**
     * mapEtfAll -包含行业、指数
     */
    public static Map<String, String> mapEtfAll = new HashMap<>();
    static {
        mapEtfAll.putAll(mapEtfBiz);
        mapEtfAll.putAll(mapEtfIndex);
    }

    public static void main(String[] args) {
        Set<String> bizSet = mapEtfBiz.keySet();
        for (String bizCode : bizSet) {
            System.out.print(",'"+bizCode+"'");
        }
        System.out.println();
    }
}