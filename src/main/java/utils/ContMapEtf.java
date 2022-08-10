package utils;

import ttjj.rank.stat.BizEtfControl;

import java.util.HashMap;
import java.util.Map;

/**
 * ETF
 *
 * @author Administrator
 * @date 2022-08-07 00:01
 */
public class ContMapEtf {
    /**
     * etf-市值
     */
    public static Map<String, String> ETF_MV = new HashMap<>();

    /**
     * etf-市值-指数-A50
     */
    public static Map<String, String> ETF_MV_ZS_A50 = new HashMap<>();
    public static Map<String, String> ETF_MV_ZS_ZZ100= new HashMap<>();
    public static Map<String, String> ETF_MV_ZS_HS300 = new HashMap<>();
    public static Map<String, String> ETF_MV_ZS_ZZ500 = new HashMap<>();
    public static Map<String, String> ETF_MV_ZS_KCB = new HashMap<>();
    public static Map<String, String> ETF_MV_ZS_CYB = new HashMap<>();
    public static Map<String, String> ETF_MV_ZS_ZS180 = new HashMap<>();
    public static Map<String, String> ETF_MV_ZS_HG = new HashMap<>();
    public static Map<String, String> ETF_MV_ZS_MG = new HashMap<>();
    public static Map<String, String> ETF_MV_ZS_ZS1000 = new HashMap<>();
    public static Map<String, String> ETF_MV_ZS_SZ100 = new HashMap<>();
    public static Map<String, String> ETF_MV_ZS_SC = new HashMap<>();

    static {
        ETF_MV_ZS_A50.put("510050", "500.21");//上证50ETF
    }
    static {
        ETF_MV_ZS_ZZ100.put("562000", "5.11");//华宝中证100ETF
    }
    static {
        ETF_MV_ZS_ZS180.put("510180", "200.33");//上证180ETF
    }
    static {
        ETF_MV_ZS_HS300.put("510300", "439.81");//沪深300ETF
//        ETF_MV_ZS_HS300.put("510330", "209.41");//300ETF基金
//        ETF_MV_ZS_HS300.put("159919", "181.46");//沪深300ETF
//        ETF_MV_ZS_HS300.put("510310", "107.57");//沪深300ETF易方达
//        ETF_MV_ZS_HS300.put("515330", "63.93");//300ETF天弘
//        ETF_MV_ZS_HS300.put("515380", "37.87");//泰康沪深300ETF
//        ETF_MV_ZS_HS300.put("510350", "28.41");//工银沪深300ETF
//        ETF_MV_ZS_HS300.put("517300", "22.03");//沪港深300ETF
//        ETF_MV_ZS_HS300.put("159925", "16.93");//沪深300ETF南方
//        ETF_MV_ZS_HS300.put("510360", "15.56");//沪深300ETF基金
//        ETF_MV_ZS_HS300.put("561300", "13.28");//300增强ETF
//        ETF_MV_ZS_HS300.put("510380", "12.34");//国寿300ETF
    }

    static {
        ETF_MV_ZS_ZZ500.put("510500", "375.59");//中证500ETF
//        ETF_MV_ZS_ZZ500.put("512500", "38.78");//500ETF基金
//        ETF_MV_ZS_ZZ500.put("159922", "30.08");//中证500ETF嘉实
//        ETF_MV_ZS_ZZ500.put("510510", "26.55");//中证500ETF基金
//        ETF_MV_ZS_ZZ500.put("159820", "23.91");//中证500ETF天弘
//        ETF_MV_ZS_ZZ500.put("510580", "20.17");//中证500ETF易方达
    }

    static {
        ETF_MV_ZS_ZS1000.put("512100", "100.94");//中证1000ETF
//        ETF_MV_ZS_ZS1000.put("560010", "87.90");//中证1000ETF指数
//        ETF_MV_ZS_ZS1000.put("159629", "80.96");//1000ETF
//        ETF_MV_ZS_ZS1000.put("159633", "79.32");//中证1000指数ETF
//        ETF_MV_ZS_ZS1000.put("159845", "52.41");//中证1000ETF
//        ETF_MV_ZS_ZS1000.put("515800", "19.78");//800ETF
    }

    static {
        ETF_MV_ZS_KCB.put("588000", "298.53");//科创50ETF
//        ETF_MV_ZS_KCB.put("588080", "134.19");//科创板50ETF
//        ETF_MV_ZS_KCB.put("588050", "47.76");//科创ETF
//        ETF_MV_ZS_KCB.put("588090", "32.56");//科创板ETF
//        ETF_MV_ZS_KCB.put("588180", "16.46");//科创50ETF基金
    }

    static {
        ETF_MV_ZS_CYB.put("159949", "89.48");//创业板50ETF
//        ETF_MV_ZS_CYB.put("159915", "149.28");//创业板ETF易方达
//        ETF_MV_ZS_CYB.put("159977", "46.14");//创业板ETF天弘
//        ETF_MV_ZS_CYB.put("159948", "22.07");//创业板ETF南方
//        ETF_MV_ZS_CYB.put("588380", "19.12");//创50ETF
//        ETF_MV.put("159952", "18.22");//创业板ETF广发
    }
    static {
        ETF_MV_ZS_SC.put("159781", "63.93");//双创50ETF
//        ETF_MV_ZS_SC.put("159783", "45.56");//双创基金ETF
//        ETF_MV_ZS_SC.put("159780", "32.18");//双创ETF
//        ETF_MV_ZS_SC.put("588400", "18.82");//双创50ETF
//        ETF_MV_ZS_SC.put("588300", "15.63");//双创ETF
    }



    static {
        ETF_MV_ZS_HG.put("159920", "139.03");//恒生ETF
//        ETF_MV_ZS_HG.put("510900", "89.48");//H股ETF
    }

    static {
        ETF_MV_ZS_MG.put("159941", "120.15");//纳指ETF
        ETF_MV_ZS_MG.put("513500", "74.38");//标普500ETF
//        ETF_MV_ZS_MG.put("513100", "52.02");//纳指ETF
//        ETF_MV_ZS_MG.put("513300", "12.09");//纳斯达克ETF
    }


    static {
        ETF_MV_ZS_SZ100.put("159901", "65.04");//深证100ETF易方达
    }



    /**
     * etf-市值-指数
     */
    public static Map<String, String> ETF_ZS = new HashMap<>();

    static {
        ETF_ZS.put("512100", "中证1000ETF");		//103.53	0.19
        ETF_ZS.put("510500", "中证500ETF");		//375.91	0.17
        ETF_ZS.put("159901", "深证100ETF易方达");		//65.12	-0.12
        ETF_ZS.put("510300", "沪深300ETF");		//434.68	-0.24
        ETF_ZS.put("513500", "标普500ETF");		//74.86	-0.38
        ETF_ZS.put("510050", "上证50ETF");		//492.63	-0.5
        ETF_ZS.put("159949", "创业板50ETF");		//89.61	-0.51
        ETF_ZS.put("159920", "恒生ETF");		//136.84	-0.54
        ETF_ZS.put("159941", "纳指ETF");		//120.13	-0.72
        ETF_ZS.put("159781", "双创50ETF");		//63.51	-0.74
        ETF_ZS.put("588000", "科创50ETF");		//305.66	-1.23
//        ETF_ZS.put("562000", "华宝中证100ETF");		//5.05	-0.41
//        ETF_ZS.put("510180", "上证180ETF");		//199.61	-0.36

//        ETF_ZS.putAll(ETF_MV_ZS_A50);
//        ETF_ZS.putAll(ETF_MV_ZS_ZZ100);
//        ETF_ZS.putAll(ETF_MV_ZS_HS300);
//        ETF_ZS.putAll(ETF_MV_ZS_ZZ500);
//        ETF_ZS.putAll(ETF_MV_ZS_KCB);
//        ETF_ZS.putAll(ETF_MV_ZS_CYB);
//        ETF_ZS.putAll(ETF_MV_ZS_ZS180);
//        ETF_ZS.putAll(ETF_MV_ZS_HG);
//        ETF_ZS.putAll(ETF_MV_ZS_MG);
//        ETF_ZS.putAll(ETF_MV_ZS_ZS1000);
//        ETF_ZS.putAll(ETF_MV_ZS_SZ100);
//        ETF_ZS.putAll(ETF_MV_ZS_SC);
    }


    /**
     * mapEtfZiYuan etf-资源
     */
    public static Map<String, String> KEJI = new HashMap<>();
    public static Map<String, String> YILIAO = new HashMap<>();
    public static Map<String, String> JINRONG = new HashMap<>();
    public static Map<String, String> XIAOFEI = new HashMap<>();
    public static Map<String, String> ZIYUAN = new HashMap<>();

    static {
        KEJI.put("512660", "军工ETF       ");//139.20  	1.74
        KEJI.put("159611", "电力ETF       ");//12.86   	0.68
        KEJI.put("159967", "创成长ETF     ");//29.98   	0.15
        KEJI.put("515790", "光伏ETF       ");//147.52  	-0.18
        KEJI.put("515030", "新能源车ETF   ");//110.75  	-0.23
        KEJI.put("515050", "5GETF         ");//99.94   	-0.42
        KEJI.put("516110", "汽车ETF       ");//9.23    	-0.81
        KEJI.put("159995", "芯片ETF       ");//205.39  	-0.94
        KEJI.put("513050", "中概互联网ETF ");//367.01  	-1.43
        KEJI.put("513330", "恒生互联网ETF ");//246.99  	-1.67

//        KEJI.put("513180", "118.13");//恒生科技指数ETF
//        KEJI.put("512760", "135.48");//芯片ETF
//        KEJI.put("512480", "124.78");//半导体ETF

//        KEJI.put("515700", "66.24");//新能车ETF
//        KEJI.put("513130", "65.51");//恒生科技ETF
//        KEJI.put("512710", "49.38");//军工龙头ETF
//        KEJI.put("512680", "37.29");//军工ETF基金
//        KEJI.put("159605", "36.09");//中概互联ETF
//        KEJI.put("515000", "35.76");//科技ETF
//        KEJI.put("562990", "33.72");//碳中和100ETF
//        KEJI.put("159790", "32.40");//碳中和ETF
//        KEJI.put("513010", "29.62");//恒生科技30ETF
//        KEJI.put("513550", "29.04");//港股通50ETF
//        KEJI.put("512670", "28.01");//国防ETF
//        KEJI.put("516160", "24.50");//新能源ETF
//        KEJI.put("159639", "23.64");//碳中和ETF南方
//        KEJI.put("159939", "23.07");//信息技术ETF
//        KEJI.put("512580", "22.92");//碳中和龙头ETF
//        KEJI.put("561190", "21.79");//双碳ETF
//        KEJI.put("159994", "21.75");//5GETF
//        KEJI.put("513660", "20.86");//恒生ETF
//        KEJI.put("159801", "20.52");//芯片龙头ETF
//        KEJI.put("159755", "20.51");//电池ETF
//        KEJI.put("159792", "20.10");//港股通互联网ETF
//        KEJI.put("517160", "18.81");//长江保护主题ETF
//        KEJI.put("159998", "18.65");//计算机ETF
//        KEJI.put("517330", "18.39");//长江保护ETF
//        KEJI.put("159813", "17.84");//半导体ETF
//        KEJI.put("159806", "17.15");//新能源车ETF
//        KEJI.put("513980", "16.39");//港股科技50ETF
//        KEJI.put("159819", "15.01");//人工智能ETF
//        KEJI.put("560550", "14.98");//碳中和ETF环交所
//        KEJI.put("515880", "13.86");//通信ETF
//        KEJI.put("159997", "12.91");//电子ETF
//        KEJI.put("159857", "12.49");//光伏ETF
//        KEJI.put("588330", "11.38");//双创龙头ETF
//        KEJI.put("159740", "10.19");//恒生科技ETF
    }

    //金融
    static {
        JINRONG.put("510880", "红利ETF       ");//166.99  	0.6
        JINRONG.put("512200", "房地产ETF     ");//52.10   	-0.14
        JINRONG.put("512880", "证券ETF       ");//316.81  	-0.22
        JINRONG.put("516970", "基建50ETF     ");//98.25   	-0.27
        JINRONG.put("512800", "银行ETF       ");//97.45   	-0.47
        JINRONG.put("518880", "黄金ETF       ");//112.54  	-0.55

        //        JINRONG.put("511990", "1532.55");//华宝添益ETF
//        JINRONG.put("511880", "1355.05");//银华日利ETF
//        JINRONG.put("511660", "228.88");//货币ETF建信添益
//        JINRONG.put("512000", "228.39");//券商ETF
//        JINRONG.put("511360", "174.73");//短融ETF
//        JINRONG.put("511850", "92.60");//财富宝ETF
//        JINRONG.put("512900", "82.71");//证券ETF基金
//        JINRONG.put("159937", "74.00");//黄金ETF基金
//        JINRONG.put("515290", "71.88");//银行ETF天弘
//        JINRONG.put("510810", "65.61");//上海国企ETF
//        JINRONG.put("512960", "54.75");//央调ETF
//        JINRONG.put("511030", "52.74");//公司债ETF
//        JINRONG.put("512950", "50.97");//央企改革ETF
//        JINRONG.put("159934", "45.00");//黄金ETF
//        JINRONG.put("159841", "42.78");//证券ETF
//        JINRONG.put("515900", "39.23");//央创ETF
//        JINRONG.put("159972", "39.05");//5年地债ETF
//        JINRONG.put("510230", "33.40");//金融ETF
//        JINRONG.put("512070", "32.99");//证券保险ETF
//        JINRONG.put("159905", "28.74");//深红利ETF
//        JINRONG.put("511810", "25.85");//理财金货币ETF
//        JINRONG.put("512700", "25.62");//银行ETF基金
//        JINRONG.put("159001", "23.99");//货币ETF
//        JINRONG.put("159959", "23.80");//央企ETF
//        JINRONG.put("159940", "19.83");//金融地产ETF
//        JINRONG.put("515180", "18.13");//红利ETF易方达
//        JINRONG.put("159816", "16.92");//0-4地债ETF
//        JINRONG.put("511690", "16.09");//交易货币ETF
//        JINRONG.put("515600", "15.83");//央企创新ETF
//        JINRONG.put("511900", "15.80");//富国货币ETF
//        JINRONG.put("515680", "15.66");//嘉实央企创新ETF
//        JINRONG.put("511700", "15.57");//场内货币ETF
//        JINRONG.put("516950", "14.75");//基建ETF
//        JINRONG.put("159993", "14.65");//龙头券商ETF
//        JINRONG.put("511220", "13.29");//城投债ETF
//        JINRONG.put("511020", "11.94");//活跃国债ETF
//        JINRONG.put("513090", "11.86");//香港证券ETF
    }

    //医疗
    static {
        YILIAO.put("513060", "恒生医疗ETF   ");//55.08   	-0.18
        YILIAO.put("512170", "医疗ETF       ");//148.20  	-0.37
        YILIAO.put("159992", "创新药ETF     ");//43.44   	-0.89

//        YILIAO.put("512010", "101.39");//医药ETF
//        YILIAO.put("512290", "40.51");//生物医药ETF
//        YILIAO.put("159938", "29.27");//医药卫生ETF
//        YILIAO.put("159837", "16.75");//生物科技ETF
//        YILIAO.put("515120", "15.19");//创新药ETF
//        YILIAO.put("159828", "14.97");//医疗ETF
//        YILIAO.put("159859", "12.95");//生物医药ETF
//        YILIAO.put("159929", "10.15");//医药ETF
    }

    //消费
    static {
        XIAOFEI.put("159996", "家电ETF       ");//27.23   	0.65
        XIAOFEI.put("515170", "食品饮料ETF   ");//39.01   	-0.26
        XIAOFEI.put("159928", "消费ETF       ");//107.11  	-0.37
        XIAOFEI.put("512690", "酒ETF         ");//92.62   	-0.47
        XIAOFEI.put("512980", "传媒ETF       ");//47.02   	-0.67
        XIAOFEI.put("159766", "旅游ETF       ");//19.72   	-1.86

//        XIAOFEI.put("515650", "17.31");//消费50ETF
//        XIAOFEI.put("510150", "11.36");//消费ETF

//        XIAOFEI.put("159736", "71.57");//饮食ETF
//        XIAOFEI.put("515710", "11.25");//食品ETF

//        XIAOFEI.put("560880", "11.53");//家电ETF基金
    }

    //资源
    static {
        ZIYUAN.put("515220", "煤炭ETF       ");//51.65   	1.62
        ZIYUAN.put("159930", "能源ETF       ");//2.67    	1.41
        ZIYUAN.put("515210", "钢铁ETF       ");//17.02   	1.1
        ZIYUAN.put("159865", "养殖ETF       ");//41.80   	0.59
        ZIYUAN.put("159825", "农业ETF       ");//17.63   	0.41
        ZIYUAN.put("516150", "稀土ETF基金   ");//24.14   	0.36
        ZIYUAN.put("159870", "化工ETF       ");//7.80    	0.34
        ZIYUAN.put("512400", "有色金属ETF   ");//30.77   	-0.08
    }

    /**
     * etf-市值-行业板块
     */
    public static Map<String, String> ETF_BK = new HashMap<>();

    static {
        ETF_BK.putAll(KEJI);
        ETF_BK.putAll(YILIAO);
        ETF_BK.putAll(JINRONG);
        ETF_BK.putAll(XIAOFEI);
        ETF_BK.putAll(ZIYUAN);
    }

    /**
     * ETF_All 全部：指数、板块
     */
    public static Map<String, String> ETF_All = new HashMap<>();

    static {
        ETF_All.putAll(ETF_ZS);
        ETF_All.putAll(ETF_BK);
    }

    static {
        //市值低于10亿
//        ETF_MV.put("516880", "9.98");//光伏50ETF
//        ETF_MV.put("159636", "9.71");//港股通科技30ETF
//        ETF_MV.put("516780", "9.53");//稀土ETF
//        ETF_MV.put("561550", "9.50");//500增强ETF
//        ETF_MV.put("515250", "9.37");//智能汽车ETF
//        ETF_MV.put("515010", "9.27");//券商ETF基金
//        ETF_MV.put("516110", "9.19");//汽车ETF
//        ETF_MV.put("159883", "9.00");//医疗器械ETF
//        ETF_MV.put("159782", "8.85");//双创50ETF基金
//        ETF_MV.put("515080", "8.61");//中证红利ETF
//        ETF_MV.put("159908", "8.58");//创业板ETF博时
//        ETF_MV.put("512720", "8.52");//计算机ETF
//        ETF_MV.put("159641", "8.33");//双碳ETF
//        ETF_MV.put("511380", "8.21");//可转债ETF
//        ETF_MV.put("510100", "8.09");//上证50ETF易方达
//        ETF_MV.put("516820", "8.06");//医疗创新ETF
//        ETF_MV.put("159796", "7.66");//电池50ETF
//        ETF_MV.put("510210", "7.60");//上证指数ETF
//        ETF_MV.put("516640", "7.58");//芯片龙头ETF
//        ETF_MV.put("512560", "7.56");//军工ETF易方达
//        ETF_MV.put("159968", "7.51");//中证500ETF博时
//        ETF_MV.put("561160", "7.49");//锂电池ETF
//        ETF_MV.put("518800", "7.47");//黄金基金ETF
//        ETF_MV.put("515070", "7.41");//人工智能AIETF
//        ETF_MV.put("159610", "7.36");//500ETF增强
//        ETF_MV.put("159647", "7.32");//中药ETF
//        ETF_MV.put("560060", "7.12");//碳中和ETF
//        ETF_MV.put("515150", "7.12");//一带一路ETF
//        ETF_MV.put("511270", "7.12");//10年地方债ETF
//        ETF_MV.put("513030", "7.03");//德国ETF
//        ETF_MV.put("159607", "6.88");//中概互联网ETF
//        ETF_MV.put("159640", "6.72");//碳中和龙头ETF
//        ETF_MV.put("511010", "6.71");//国债ETF
//        ETF_MV.put("159867", "6.67");//畜牧ETF
//        ETF_MV.put("159745", "6.61");//建材ETF
//        ETF_MV.put("515020", "6.57");//银行ETF华夏
//        ETF_MV.put("512600", "6.57");//必选消费ETF
//        ETF_MV.put("159840", "6.56");//锂电池ETF
//        ETF_MV.put("159606", "6.54");//中证500成长ETF
//        ETF_MV.put("159869", "6.36");//游戏ETF
//        ETF_MV.put("159742", "6.25");//恒生科技指数ETF
//        ETF_MV.put("515750", "6.25");//科技50ETF
//        ETF_MV.put("562800", "6.20");//稀有金属ETF
//        ETF_MV.put("513580", "6.15");//恒生科技ETF基金
//        ETF_MV.put("515660", "6.10");//国联安沪深300ETF
//        ETF_MV.put("511260", "6.00");//十年国债ETF
//        ETF_MV.put("517050", "5.89");//互联网50ETF
//        ETF_MV.put("159973", "5.81");//民企领先100ETF
//        ETF_MV.put("159885", "5.78");//碳中和ETF基金
//        ETF_MV.put("516050", "5.72");//科技龙头ETF
//        ETF_MV.put("159961", "5.72");//深100ETF方正富邦
//        ETF_MV.put("515580", "5.71");//科技100ETF
//        ETF_MV.put("159807", "5.71");//科技ETF
//        ETF_MV.put("513360", "5.71");//教育ETF
//        ETF_MV.put("510590", "5.68");//平安中证500ETF
//        ETF_MV.put("511060", "5.63");//5年地方债ETF
//        ETF_MV.put("517000", "5.60");//沪港深500ETF
//        ETF_MV.put("560800", "5.58");//数字经济ETF
//        ETF_MV.put("510390", "5.55");//平安沪深300ETF
//        ETF_MV.put("588360", "5.55");//科创创业ETF
//        ETF_MV.put("517080", "5.42");//沪港深500ETF基金
//        ETF_MV.put("513600", "5.42");//恒生指数ETF
//        ETF_MV.put("159966", "5.38");//创蓝筹ETF
//        ETF_MV.put("159842", "5.36");//券商ETF
//        ETF_MV.put("515260", "5.35");//电子ETF
//        ETF_MV.put("510710", "5.34");//上证50ETF博时
//        ETF_MV.put("510800", "5.31");//50ETF基金
//        ETF_MV.put("512510", "5.28");//中证500指数ETF
//        ETF_MV.put("159974", "5.20");//央企创新ETF
//        ETF_MV.put("159902", "5.18");//中小100ETF
//        ETF_MV.put("512810", "5.14");//国防军工ETF
//        ETF_MV.put("159850", "5.12");//恒生国企ETF
//        ETF_MV.put("562000", "5.11");//华宝中证100ETF
//        ETF_MV.put("512330", "4.98");//信息科技ETF
//        ETF_MV.put("159980", "4.93");//有色ETF
//        ETF_MV.put("561990", "4.83");//沪深300增强ETF
//        ETF_MV.put("512650", "4.82");//长三角ETF
//        ETF_MV.put("561510", "4.80");//中医药ETF
//        ETF_MV.put("515950", "4.76");//医药龙头ETF
//        ETF_MV.put("513860", "4.74");//港股通科技ETF
//        ETF_MV.put("515060", "4.63");//房地产ETF基金
//        ETF_MV.put("515110", "4.63");//一带一路国企ETF
//        ETF_MV.put("512820", "4.62");//银行业ETF
//        ETF_MV.put("159856", "4.60");//互联网龙头ETF
//        ETF_MV.put("516660", "4.54");//新能汽车ETF
//        ETF_MV.put("510410", "4.49");//资源ETF
//        ETF_MV.put("516010", "4.48");//游戏ETF
//        ETF_MV.put("159757", "4.48");//电池30ETF
//        ETF_MV.put("159864", "4.42");//光伏50ETF
//        ETF_MV.put("562510", "4.30");//旅游ETF
//        ETF_MV.put("515560", "4.27");//证券ETF建信
//        ETF_MV.put("159875", "4.27");//新能源ETF
//        ETF_MV.put("159981", "4.12");//能源化工ETF
//        ETF_MV.put("516670", "4.12");//畜牧养殖ETF
//        ETF_MV.put("516070", "4.10");//碳中和50ETF
//        ETF_MV.put("513770", "4.08");//港股互联网ETF
//        ETF_MV.put("516920", "4.05");//芯片ETF基金
//        ETF_MV.put("159861", "3.97");//碳中和50ETF
//        ETF_MV.put("159839", "3.94");//生物药ETF
//        ETF_MV.put("159960", "3.93");//H股ETF港股通
//        ETF_MV.put("159635", "3.91");//基建100ETF
//        ETF_MV.put("513700", "3.86");//香港医药ETF
//        ETF_MV.put("159887", "3.85");//银行ETF
//        ETF_MV.put("516090", "3.84");//新能源ETF易方达
//        ETF_MV.put("510630", "3.84");//消费30ETF
//        ETF_MV.put("517100", "3.83");//AH500ETF
//        ETF_MV.put("159916", "3.80");//深F60ETF
//        ETF_MV.put("512910", "3.79");//中证100ETF
//        ETF_MV.put("159617", "3.75");//500价值ETF
//        ETF_MV.put("159910", "3.72");//基本面120ETF
//        ETF_MV.put("159615", "3.68");//生物科技ETF港股
//        ETF_MV.put("515390", "3.66");//华安沪深300ETF
//        ETF_MV.put("588100", "3.65");//科创信息技术ETF
//        ETF_MV.put("159982", "3.61");//中证500ETF鹏华
//        ETF_MV.put("516390", "3.54");//新能源汽车ETF
//        ETF_MV.put("159003", "3.51");//招商快线ETF
//        ETF_MV.put("159964", "3.45");//平安创业板ETF
//        ETF_MV.put("159741", "3.37");//恒生科技ETF基金
//        ETF_MV.put("512770", "3.33");//战略新兴ETF
//        ETF_MV.put("517120", "3.33");//沪港深创新药ETF
//        ETF_MV.put("159957", "3.30");//创业板ETF华夏
//        ETF_MV.put("588060", "3.28");//科创50ETF龙头
//        ETF_MV.put("515980", "3.26");//人工智能ETF
//        ETF_MV.put("516020", "3.21");//化工ETF
//        ETF_MV.put("588390", "3.18");//科创创业50ETF
//        ETF_MV.put("159747", "3.18");//香港科技ETF
//        ETF_MV.put("159642", "3.10");//碳中和100ETF
//        ETF_MV.put("515960", "3.08");//医药健康ETF
//        ETF_MV.put("159715", "3.02");//稀土ETF易方达
//        ETF_MV.put("159619", "3.00");//基建ETF
//        ETF_MV.put("513690", "2.99");//恒生高股息ETF
//        ETF_MV.put("562390", "2.98");//中药50ETF
//        ETF_MV.put("159847", "2.97");//医疗50ETF
//        ETF_MV.put("159743", "2.94");//湖北ETF
//        ETF_MV.put("512040", "2.92");//价值100ETF
//        ETF_MV.put("159824", "2.89");//新能车ETF
//        ETF_MV.put("159625", "2.87");//绿色电力ETF
//        ETF_MV.put("512220", "2.78");//科技150ETF
//        ETF_MV.put("159768", "2.74");//房地产ETF
//        ETF_MV.put("510530", "2.73");//工银中证500ETF
//        ETF_MV.put("561700", "2.73");//电力ETF基金
//        ETF_MV.put("517850", "2.72");//张江ETF
//        ETF_MV.put("159852", "2.72");//软件ETF
//        ETF_MV.put("512550", "2.70");//A50ETF基金
//        ETF_MV.put("516830", "2.66");//300ESGETF
//        ETF_MV.put("515890", "2.65");//红利ETF博时
//        ETF_MV.put("159761", "2.62");//新材料50ETF
//        ETF_MV.put("516080", "2.62");//创新药ETF易方达
//        ETF_MV.put("159930", "2.61");//能源ETF
//        ETF_MV.put("159863", "2.60");//光伏ETF基金
//        ETF_MV.put("515320", "2.59");//电子50ETF
//        ETF_MV.put("159620", "2.59");//500成长ETF
//        ETF_MV.put("516800", "2.58");//智能制造ETF
//        ETF_MV.put("159843", "2.57");//食品饮料ETF
//        ETF_MV.put("561910", "2.57");//电池ETF
//        ETF_MV.put("159903", "2.55");//深成ETF
//        ETF_MV.put("159632", "2.54");//纳斯达克ETF
//        ETF_MV.put("516290", "2.54");//光伏ETF基金
//        ETF_MV.put("159713", "2.54");//稀土ETF
//        ETF_MV.put("561130", "2.51");//国货ETF
//        ETF_MV.put("159811", "2.50");//5G50ETF
//        ETF_MV.put("515230", "2.49");//软件ETF
//        ETF_MV.put("511800", "2.49");//易方达货币ETF
//        ETF_MV.put("515920", "2.47");//智能消费ETF
//        ETF_MV.put("512120", "2.47");//医药50ETF
//        ETF_MV.put("510160", "2.46");//产业升级ETF
//        ETF_MV.put("510560", "2.41");//国寿500ETF
//        ETF_MV.put("510850", "2.40");//工银上证50ETF
//        ETF_MV.put("512890", "2.39");//红利低波ETF
//        ETF_MV.put("159985", "2.38");//豆粕ETF
//        ETF_MV.put("159954", "2.32");//H股ETF
//        ETF_MV.put("510760", "2.32");//上证综指ETF
//        ETF_MV.put("516120", "2.31");//化工50ETF
//        ETF_MV.put("516300", "2.30");//中证1000指数ETF
//        ETF_MV.put("159822", "2.30");//新经济ETF
//        ETF_MV.put("560900", "2.28");//创新药企ETF
//        ETF_MV.put("515200", "2.28");//创新100ETF
//        ETF_MV.put("512930", "2.22");//AIETF
//        ETF_MV.put("515400", "2.22");//大数据ETF
//        ETF_MV.put("510130", "2.22");//中盘ETF
//        ETF_MV.put("159851", "2.21");//金融科技ETF
//        ETF_MV.put("515860", "2.20");//科技ETF基金
//        ETF_MV.put("515760", "2.18");//浙江国资ETF
//        ETF_MV.put("510010", "2.18");//180治理ETF
//        ETF_MV.put("513020", "2.17");//港股科技ETF
//        ETF_MV.put("159814", "2.17");//创业大盘ETF
//        ETF_MV.put("159786", "2.13");//VRETF
//        ETF_MV.put("517380", "2.09");//创新药沪港深ETF
//        ETF_MV.put("159748", "2.04");//沪港深创新药ETF
//        ETF_MV.put("516510", "2.00");//云计算ETF
//        ETF_MV.put("510170", "1.99");//大宗商品ETF
//        ETF_MV.put("159628", "1.99");//国证2000ETF
//        ETF_MV.put("512570", "1.98");//证券ETF易方达
//        ETF_MV.put("159936", "1.98");//可选消费ETF
//        ETF_MV.put("513890", "1.97");//恒生科技HKETF
//        ETF_MV.put("562880", "1.97");//电池ETF基金
//        ETF_MV.put("513280", "1.96");//港股生物科技ETF
//        ETF_MV.put("159609", "1.95");//光伏龙头ETF
//        ETF_MV.put("159708", "1.93");//红利ETF
//        ETF_MV.put("159958", "1.83");//创业板ETF工银
//        ETF_MV.put("516520", "1.83");//智能驾驶ETF
//        ETF_MV.put("159752", "1.83");//新能源龙头ETF
//        ETF_MV.put("517960", "1.81");//科技AHETF
//        ETF_MV.put("516760", "1.80");//养殖ETF
//        ETF_MV.put("516220", "1.80");//化工龙头ETF
//        ETF_MV.put("512730", "1.78");//中证银行ETF
//        ETF_MV.put("513900", "1.77");//港股通100ETF
//        ETF_MV.put("159805", "1.76");//传媒ETF
//        ETF_MV.put("588260", "1.73");//科创信息ETF
//        ETF_MV.put("561100", "1.72");//消费电子龙头ETF
//        ETF_MV.put("561350", "1.72");//国泰中证500ETF
//        ETF_MV.put("516310", "1.70");//银行ETF易方达
//        ETF_MV.put("159892", "1.70");//恒生医药ETF
//        ETF_MV.put("510290", "1.70");//上证380ETF
//        ETF_MV.put("517110", "1.69");//创新药沪深港ETF
//        ETF_MV.put("515850", "1.67");//证券龙头ETF
//        ETF_MV.put("159707", "1.67");//地产ETF
//        ETF_MV.put("511820", "1.67");//鹏华添利ETF
//        ETF_MV.put("159618", "1.66");//光伏ETF华安
//        ETF_MV.put("516350", "1.66");//芯片50ETF
//        ETF_MV.put("517200", "1.65");//互联网ETF
//        ETF_MV.put("516810", "1.64");//农业50ETF
//        ETF_MV.put("159758", "1.64");//红利50ETF
//        ETF_MV.put("516590", "1.63");//智能汽车50ETF
//        ETF_MV.put("588280", "1.63");//科创50ETF华安
//        ETF_MV.put("159909", "1.63");//TMT50ETF
//        ETF_MV.put("516380", "1.62");//智能电动车ETF
//        ETF_MV.put("159804", "1.60");//创中盘88ETF
//        ETF_MV.put("560100", "1.57");//中证500增强ETF
//        ETF_MV.put("159838", "1.56");//医药ETF基金
//        ETF_MV.put("159760", "1.56");//公共卫生健康ETF
//        ETF_MV.put("518680", "1.56");//金ETF
//        ETF_MV.put("513150", "1.55");//港股通科技50ETF
//        ETF_MV.put("159933", "1.54");//国投金融地产ETF
//        ETF_MV.put("518850", "1.53");//黄金ETF9999
//        ETF_MV.put("516790", "1.52");//医疗保健ETF
//        ETF_MV.put("159716", "1.49");//深创100ETF
//        ETF_MV.put("515100", "1.47");//红利100ETF
//        ETF_MV.put("515090", "1.47");//可持续发展ETF
//        ETF_MV.put("560660", "1.46");//云50ETF
//        ETF_MV.put("516360", "1.45");//新材料ETF
//        ETF_MV.put("516270", "1.44");//新能源50ETF
//        ETF_MV.put("515350", "1.42");//民生加银300ETF
//        ETF_MV.put("513220", "1.42");//全球互联ETF
//        ETF_MV.put("562500", "1.42");//机器人ETF
//        ETF_MV.put("159889", "1.42");//智能汽车ETF
//        ETF_MV.put("562300", "1.41");//碳中和ETF基金
//        ETF_MV.put("159873", "1.39");//医疗设备ETF
//        ETF_MV.put("159608", "1.38");//稀有金属ETF
//        ETF_MV.put("512970", "1.38");//大湾区ETF
//        ETF_MV.put("513320", "1.37");//恒生新经济ETF
//        ETF_MV.put("518600", "1.36");//上海金ETF
//        ETF_MV.put("516710", "1.35");//新材料50ETF
//        ETF_MV.put("159898", "1.35");//器械ETF
//        ETF_MV.put("159729", "1.35");//互联网ETF
//        ETF_MV.put("560560", "1.35");//泰康碳中和ETF
//        ETF_MV.put("159613", "1.34");//信息安全ETF
//        ETF_MV.put("511650", "1.32");//华夏快线ETF
//        ETF_MV.put("561120", "1.31");//家电ETF
//        ETF_MV.put("159891", "1.30");//医疗ETF基金
//        ETF_MV.put("159881", "1.29");//有色60ETF
//        ETF_MV.put("515360", "1.29");//方正沪深300ETF
//        ETF_MV.put("159709", "1.24");//物联网ETF工银
//        ETF_MV.put("516260", "1.24");//物联网50ETF
//        ETF_MV.put("512750", "1.23");//基本面50ETF
//        ETF_MV.put("159770", "1.23");//机器人ETF
//        ETF_MV.put("516180", "1.22");//光伏ETF平安
//        ETF_MV.put("517350", "1.22");//中概科技ETF
//        ETF_MV.put("159899", "1.22");//软件基金ETF
//        ETF_MV.put("159827", "1.21");//农业50ETF
//        ETF_MV.put("511180", "1.19");//上证可转债ETF
//        ETF_MV.put("516580", "1.18");//新能源主题ETF
//        ETF_MV.put("516600", "1.17");//消费服务ETF
//        ETF_MV.put("561310", "1.17");//消电ETF
//        ETF_MV.put("512260", "1.16");//中证500低波ETF
//        ETF_MV.put("159976", "1.16");//湾创ETF
//        ETF_MV.put("516770", "1.16");//游戏动漫ETF
//        ETF_MV.put("159763", "1.15");//新材料ETF基金
//        ETF_MV.put("515630", "1.15");//保险证券ETF
//        ETF_MV.put("159767", "1.14");//电池龙头ETF
//        ETF_MV.put("561500", "1.13");//漂亮50ETF
//        ETF_MV.put("510030", "1.12");//价值ETF
//        ETF_MV.put("159906", "1.11");//深成长龙头ETF
//        ETF_MV.put("159732", "1.11");//消费电子ETF
//        ETF_MV.put("159877", "1.11");//医疗产业ETF
//        ETF_MV.put("159975", "1.11");//深100ETF招商
//        ETF_MV.put("518660", "1.09");//黄金ETF基金
//        ETF_MV.put("588150", "1.09");//科创龙头ETF
//        ETF_MV.put("513120", "1.08");//港股创新药ETF
//        ETF_MV.put("560500", "1.07");//500质量成长ETF
//        ETF_MV.put("159848", "1.06");//证券ETF基金
//        ETF_MV.put("515810", "1.05");//中证800ETF
//        ETF_MV.put("159907", "1.05");//中小300ETF
//        ETF_MV.put("516060", "1.05");//创新药产业ETF
//        ETF_MV.put("159849", "1.05");//生物科技ETF招商
//        ETF_MV.put("510020", "1.04");//超大盘ETF
//        ETF_MV.put("515670", "1.04");//中银中证100ETF
//        ETF_MV.put("515990", "1.03");//国企一带一路ETF
//        ETF_MV.put("510660", "1.03");//医药卫生ETF
//        ETF_MV.put("515820", "1.01");//A800ETF
//        ETF_MV.put("159965", "0.99");//央视50ETF
//        ETF_MV.put("159855", "0.98");//影视ETF
//        ETF_MV.put("516130", "0.97");//消费龙头ETF
//        ETF_MV.put("513200", "0.97");//港股通医药ETF
//        ETF_MV.put("159888", "0.96");//智能车ETF
//        ETF_MV.put("516550", "0.96");//农业ETF
//        ETF_MV.put("561560", "0.94");//电力ETF
//        ETF_MV.put("516530", "0.93");//物流快递ETF
//        ETF_MV.put("159751", "0.92");//港股科技ETF
//        ETF_MV.put("515300", "0.91");//红利低波ETF基金
//        ETF_MV.put("159706", "0.91");//深证100ETF华安
//        ETF_MV.put("159720", "0.91");//智能电动车ETF
//        ETF_MV.put("159735", "0.90");//港股消费ETF
//        ETF_MV.put("159795", "0.90");//智能车ETF基金
//        ETF_MV.put("516500", "0.89");//生物科技ETF
//        ETF_MV.put("159943", "0.89");//深证成指ETF
//        ETF_MV.put("159990", "0.89");//小盘价值ETF
//        ETF_MV.put("562360", "0.88");//机器人50ETF
//        ETF_MV.put("159738", "0.88");//沪港深云计算ETF
//        ETF_MV.put("561800", "0.87");//稀有金属ETF基金
//        ETF_MV.put("159912", "0.87");//深300ETF
//        ETF_MV.put("516960", "0.86");//机械ETF
//        ETF_MV.put("159896", "0.86");//物联网龙头ETF
//        ETF_MV.put("562950", "0.85");//消费电子50ETF
//        ETF_MV.put("562350", "0.82");//电力指数ETF
//        ETF_MV.put("159726", "0.82");//恒生红利ETF
//        ETF_MV.put("560990", "0.82");//中金科技先锋ETF
//        ETF_MV.put("159991", "0.81");//创大盘ETF
//        ETF_MV.put("159721", "0.81");//深创龙头ETF
//        ETF_MV.put("516610", "0.79");//医疗服务ETF
//        ETF_MV.put("159612", "0.79");//标普500ETF
//        ETF_MV.put("159779", "0.78");//消费电子ETF基金
//        ETF_MV.put("510060", "0.78");//央企ETF
//        ETF_MV.put("159739", "0.78");//大数据ETF
//        ETF_MV.put("516630", "0.77");//云计算50ETF
//        ETF_MV.put("516750", "0.76");//建材ETF
//        ETF_MV.put("510200", "0.76");//上证券商ETF
//        ETF_MV.put("159808", "0.76");//创业板ETF融通
//        ETF_MV.put("159712", "0.75");//港股通50ETF
//        ETF_MV.put("516560", "0.75");//养老ETF
//        ETF_MV.put("159866", "0.74");//日经ETF
//        ETF_MV.put("516730", "0.73");//证券公司ETF
//        ETF_MV.put("516690", "0.73");//化工产业ETF
//        ETF_MV.put("515280", "0.73");//银行龙头ETF
//        ETF_MV.put("159858", "0.72");//创新药指ETF
//        ETF_MV.put("510090", "0.72");//ESGETF基金
//        ETF_MV.put("512640", "0.71");//金融地产ETF基金
//        ETF_MV.put("159956", "0.71");//创业板ETF建信
//        ETF_MV.put("516850", "0.70");//新能源80ETF
//        ETF_MV.put("515930", "0.68");//永赢沪深300ETF
//        ETF_MV.put("512150", "0.68");//A50ETF
//        ETF_MV.put("518890", "0.68");//中银上海金ETF
//        ETF_MV.put("516620", "0.67");//影视ETF
//        ETF_MV.put("513520", "0.67");//日经ETF
//        ETF_MV.put("516910", "0.67");//物流ETF
//        ETF_MV.put("588310", "0.67");//双创ETF基金
//        ETF_MV.put("515190", "0.67");//中银证券500ETF
//        ETF_MV.put("561900", "0.67");//沪深300ESGETF
//        ETF_MV.put("159983", "0.66");//粤港澳大湾区ETF
//        ETF_MV.put("562900", "0.66");//现代农业ETF
//        ETF_MV.put("517360", "0.65");//沪港深科技ETF
//        ETF_MV.put("516930", "0.64");//生物科技ETF基金
//        ETF_MV.put("159725", "0.64");//线上消费ETF
//        ETF_MV.put("159987", "0.64");//科技创新ETF
//        ETF_MV.put("159718", "0.64");//港股医药ETF
//        ETF_MV.put("159711", "0.64");//港股通ETF
//        ETF_MV.put("159798", "0.64");//消费50ETF
//        ETF_MV.put("513000", "0.63");//日经225ETF易方达
//        ETF_MV.put("159773", "0.63");//创科技ETF
//        ETF_MV.put("159918", "0.63");//中创400ETF
//        ETF_MV.put("159710", "0.63");//智能电车ETF
//        ETF_MV.put("516330", "0.63");//物联网ETF
//        ETF_MV.put("516320", "0.63");//高端装备ETF
//        ETF_MV.put("159931", "0.63");//金融ETF
//        ETF_MV.put("159862", "0.63");//食品ETF
//        ETF_MV.put("515130", "0.62");//沪深300ETF博时
//        ETF_MV.put("159776", "0.62");//港股通医药ETF
//        ETF_MV.put("159789", "0.62");//饮料ETF
//        ETF_MV.put("516100", "0.61");//互联网金融ETF
//        ETF_MV.put("517390", "0.60");//云计算沪港深ETF
//        ETF_MV.put("516720", "0.60");//ESGETF
//        ETF_MV.put("513080", "0.60");//法国CAC40ETF
//        ETF_MV.put("159836", "0.59");//创300ETF
//        ETF_MV.put("513990", "0.59");//港股通ETF
//        ETF_MV.put("159717", "0.59");//ESGETF
//        ETF_MV.put("159835", "0.58");//创新药50ETF
//        ETF_MV.put("513880", "0.58");//日经225ETF
//        ETF_MV.put("159778", "0.58");//工业互联ETF
//        ETF_MV.put("513530", "0.58");//港股通红利ETF
//        ETF_MV.put("513960", "0.57");//港股通消费ETF
//        ETF_MV.put("159935", "0.57");//景顺中证500ETF
//        ETF_MV.put("561600", "0.57");//消费电子ETF
//        ETF_MV.put("513230", "0.57");//港股消费ETF
//        ETF_MV.put("516000", "0.57");//大数据50ETF
//        ETF_MV.put("510650", "0.56");//金融地产ETF
//        ETF_MV.put("510990", "0.56");//180ESGETF
//        ETF_MV.put("159886", "0.56");//机械ETF
//        ETF_MV.put("159793", "0.55");//线上消费ETF平安
//        ETF_MV.put("159984", "0.55");//湾区100ETF
//        ETF_MV.put("513070", "0.54");//港股消费50ETF
//        ETF_MV.put("562910", "0.54");//高端制造ETF
//        ETF_MV.put("513160", "0.54");//港股科技30ETF
//        ETF_MV.put("516860", "0.54");//金融科技ETF
//        ETF_MV.put("513800", "0.53");//东京证券指数ETF
//        ETF_MV.put("159730", "0.53");//龙头家电ETF
//        ETF_MV.put("159769", "0.53");//消费电子龙头ETF
//        ETF_MV.put("159703", "0.53");//新材料ETF
//        ETF_MV.put("512870", "0.52");//杭州湾区ETF
//        ETF_MV.put("510190", "0.52");//上证龙头ETF
//        ETF_MV.put("560650", "0.52");//核心50ETF
//        ETF_MV.put("159871", "0.52");//有色金属ETF
//        ETF_MV.put("515570", "0.51");//山证红利ETF
//        ETF_MV.put("159723", "0.51");//科技龙头ETF
//        ETF_MV.put("518860", "0.51");//黄金ETFAU
//        ETF_MV.put("512190", "0.50");//浙商之江凤凰ETF
//        ETF_MV.put("517880", "0.49");//品牌消费ETF
//        ETF_MV.put("511860", "0.48");//保证金货币ETF
//        ETF_MV.put("510600", "0.48");//申万上证50ETF
//        ETF_MV.put("516890", "0.48");//新材料ETF平安
//        ETF_MV.put("159823", "0.47");//H股ETF基金
//        ETF_MV.put("517800", "0.47");//人工智能50ETF
//        ETF_MV.put("512530", "0.47");//沪深300红利ETF
//        ETF_MV.put("159005", "0.46");//汇添富快钱ETF
//        ETF_MV.put("159890", "0.46");//云计算ETF
//        ETF_MV.put("159788", "0.45");//港股通100ETF
//        ETF_MV.put("159999", "0.45");//永赢中证500ETF
//        ETF_MV.put("159962", "0.45");//四川ETF
//        ETF_MV.put("159775", "0.44");//新能源车电池ETF
//        ETF_MV.put("515590", "0.44");//500等权ETF
//        ETF_MV.put("159945", "0.43");//能源ETF基金
//        ETF_MV.put("159913", "0.42");//深价值ETF
//        ETF_MV.put("510440", "0.41");//500沪市ETF
//        ETF_MV.put("159895", "0.41");//物联网50ETF
//        ETF_MV.put("517010", "0.41");//沪港深500ETF指数
//        ETF_MV.put("515870", "0.41");//先进制造ETF
//        ETF_MV.put("159986", "0.41");//消费100ETF
//        ETF_MV.put("159728", "0.39");//在线消费ETF
//        ETF_MV.put("159750", "0.39");//香港科技50ETF
//        ETF_MV.put("517280", "0.39");//网购ETF
//        ETF_MV.put("516210", "0.38");//银行股ETF
//        ETF_MV.put("517660", "0.38");//物联网沪港深ETF
//        ETF_MV.put("159872", "0.38");//车联网ETF
//        ETF_MV.put("562520", "0.38");//1000成长ETF
//        ETF_MV.put("515530", "0.37");//泰康中证500ETF
//        ETF_MV.put("512590", "0.37");//高股息ETF
//        ETF_MV.put("159701", "0.37");//物联网ETF招商
//        ETF_MV.put("517030", "0.37");//沪港深300ETF基金
//        ETF_MV.put("513590", "0.37");//香港消费ETF
//        ETF_MV.put("159733", "0.36");//消费电子50ETF
//        ETF_MV.put("159932", "0.36");//中证500深ETF
//        ETF_MV.put("159603", "0.35");//双创龙头ETF
//        ETF_MV.put("516900", "0.35");//食品50ETF
//        ETF_MV.put("517770", "0.33");//游戏传媒ETF
//        ETF_MV.put("515500", "0.33");//中证长三角领先
//        ETF_MV.put("516570", "0.33");//化工行业ETF
//        ETF_MV.put("159876", "0.32");//有色龙头ETF
//        ETF_MV.put("159810", "0.31");//浦银创业板ETF
//        ETF_MV.put("516980", "0.31");//证券ETF先锋
//        ETF_MV.put("517270", "0.31");//沪港深科技TOPETF
//        ETF_MV.put("159969", "0.31");//深100ETF银华
//        ETF_MV.put("517900", "0.31");//银行ETF优选
//        ETF_MV.put("159978", "0.31");//大湾区ETF
//        ETF_MV.put("513380", "0.30");//恒生科技基金ETF
//        ETF_MV.put("159815", "0.30");//浙江100ETF
//        ETF_MV.put("511980", "0.30");//现金添富ETF
//        ETF_MV.put("159853", "0.30");//科技100ETF
//        ETF_MV.put("511310", "0.30");//10年国债ETF
//        ETF_MV.put("159880", "0.30");//有色ETF基金
//        ETF_MV.put("517170", "0.29");//沪港深ETF
//        ETF_MV.put("516680", "0.28");//有色ETF基金
//        ETF_MV.put("516700", "0.27");//大数据产业ETF
//        ETF_MV.put("510220", "0.26");//中小盘ETF
//        ETF_MV.put("510770", "0.26");//G60创新ETF
//        ETF_MV.put("159970", "0.26");//深100ETF工银
//        ETF_MV.put("516650", "0.26");//有色50ETF
//        ETF_MV.put("159821", "0.26");//BOCI创业板ETF
//        ETF_MV.put("517780", "0.25");//红300ETF
//        ETF_MV.put("517550", "0.24");//消费ETF沪港深
//        ETF_MV.put("516480", "0.24");//新材料ETF基金
//        ETF_MV.put("515310", "0.24");//沪深300添富ETF
//        ETF_MV.put("510680", "0.24");//上证50ETF基金
//        ETF_MV.put("511600", "0.23");//货币ETF
//        ETF_MV.put("515550", "0.23");//中融中证500ETF
//        ETF_MV.put("510550", "0.21");//方正中证500ETF
//        ETF_MV.put("159923", "0.21");//中证100ETF
//        ETF_MV.put("516200", "0.21");//证券行业ETF
//        ETF_MV.put("513680", "0.20");//恒生国企ETF
//        ETF_MV.put("159702", "0.19");//AIETF
//        ETF_MV.put("510110", "0.19");//周期ETF
//        ETF_MV.put("159787", "0.19");//建材ETF易方达
//        ETF_MV.put("159791", "0.18");//300ESGETF
//        ETF_MV.put("159797", "0.17");//医疗器械ETF基金
//        ETF_MV.put("159731", "0.17");//石化ETF
//        ETF_MV.put("511620", "0.16");//货币基金ETF
//        ETF_MV.put("159944", "0.16");//材料ETF
//        ETF_MV.put("517500", "0.16");//游戏沪港深ETF
//        ETF_MV.put("511830", "0.16");//华泰货币ETF
//        ETF_MV.put("159955", "0.15");//创业板ETF嘉实
//        ETF_MV.put("510120", "0.15");//非周期ETF
//        ETF_MV.put("159777", "0.14");//创科技ETF基金
//        ETF_MV.put("560000", "0.14");//智慧电车ETF
//        ETF_MV.put("515510", "0.14");//500成长ETF
//        ETF_MV.put("560600", "0.13");//医药创新ETF
//        ETF_MV.put("511950", "0.12");//添利货币ETF
//        ETF_MV.put("512610", "0.12");//医药卫生ETF基金
//        ETF_MV.put("512780", "0.11");//京津冀ETF
//        ETF_MV.put("510270", "0.11");//国企ETF
//        ETF_MV.put("510370", "0.11");//兴业沪深300ETF
//        ETF_MV.put("516190", "0.11");//文娱传媒ETF
//        ETF_MV.put("511960", "0.10");//嘉实快线ETF
//        ETF_MV.put("510570", "0.10");//兴业中证500ETF
//        ETF_MV.put("517760", "0.10");//消费TOPETF
//        ETF_MV.put("517990", "0.10");//医药ETF沪港深
//        ETF_MV.put("159979", "0.09");//湾创100ETF
//        ETF_MV.put("511670", "0.09");//华泰天天金ETF
//        ETF_MV.put("511920", "0.08");//广发货币ETF
//        ETF_MV.put("159971", "0.07");//创业板ETF富国
//        ETF_MV.put("511970", "0.07");//国寿货币ETF
//        ETF_MV.put("159951", "0.06");//中关村ETF
//        ETF_MV.put("511910", "0.03");//融通货币ETF
//        ETF_MV.put("511770", "0.03");//金鹰增益货币ETF
//        ETF_MV.put("511930", "0.02");//中融日盈货币ETF
    }

    public static void main(String[] args) {
        BizEtfControl.showEtfMv(DateUtil.getToday(DateUtil.YYYY_MM_DD));//显示etf市值
    }
}
