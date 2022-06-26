package utils;

import ttjj.dto.StockAdrCountVo;
import ttjj.service.KlineService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Content.*;

/**
 * ContMapStZiYuan-业务板块
 *
 * @author Administrator
 * @date 2022-03-18 10:09
 */
public class ContMapStBoardAdrCount {
    /**
     * 保险
     **/
    public static Map<String, String> baoXian = new HashMap<>();

    static {
        baoXian.put("000627", "天茂集团");//保险        	160		46		39		29		24		22		9.94	1	 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:100.00	超均线2022-06-21:15              102
        baoXian.put("601628", "中国人寿");//保险        	119		39		28		19		17		16		5.82	2	 5日:81.86 	10日:86.37 	20日:87.84 	30日:87.84 	60日:87.84 	超均线2022-06-21:                102
    }

    /**
     * 游戏
     **/
    public static Map<String, String> youXi = new HashMap<>();

    static {
        youXi.put("002624", "完美世界");//游戏        	179		75		58		22		13		11		-0.28	1	 5日:62.71 	10日:65.98 	20日:71.05 	30日:73.91 	60日:85.40 	超均线2022-06-21:
        youXi.put("600633", "浙数文化");//游戏        	132		53		40		29		6		4		7.98	2	 5日:75.51 	10日:80.33 	20日:85.19 	30日:88.29 	60日:63.84 	超均线2022-06-21:15              102
    }

    /**
     * 电机
     **/
    public static Map<String, String> dianJi = new HashMap<>();

    static {
        dianJi.put("003021", "兆威机电");//电机        	255		78		71		58		27		21		-2.62	1	 5日:55.91 	10日:66.60 	20日:77.05 	30日:78.83 	60日:81.57 	超均线2022-06-21:
        dianJi.put("002176", "江特电机");//电机        	189		75		62		28		13		11		-2.59	2	 5日:65.55 	10日:68.38 	20日:79.33 	30日:83.10 	60日:88.53 	超均线2022-06-21:
    }

    /**
     * 文化传媒
     **/
    public static Map<String, String> wenHuaChuanMei = new HashMap<>();

    static {
        wenHuaChuanMei.put("000665", "湖北广电");//文化传媒    	252		140		71		29		6		6		4.62	1	 5日:68.49 	10日:72.04 	20日:36.15 	30日:36.15 	60日:50.55 	超均线2022-06-21:15  30      101
        wenHuaChuanMei.put("002292", "奥飞娱乐");//文化传媒    	225		95		86		30		9		5		2.2 	2	 5日:73.58 	10日:80.00 	20日:30.11 	30日:45.06 	60日:48.13 	超均线2022-06-21:15      60  101
    }

    /**
     * 房地产开发
     **/
    public static Map<String, String> fangDiChanKaiFa = new HashMap<>();

    static {
        fangDiChanKaiFa.put("000736", "中交地产");//房地产开发  	371		228		94		29		15		5		4.55	1	 5日:64.29 	10日:65.73 	20日:25.63 	30日:9.98  	60日:39.59 	超均线2022-06-21:15  30  60
        fangDiChanKaiFa.put("600603", "广汇物流");//房地产开发  	329		155		90		61		21		2		0.35	2	 5日:12.87 	10日:14.18 	20日:53.63 	30日:60.88 	60日:65.77 	超均线2022-06-21:
    }

    /**
     * 计算机设备
     **/
    public static Map<String, String> jiSuanJiSheBei = new HashMap<>();

    static {
        jiSuanJiSheBei.put("002180", "纳思达  ");//计算机设备  	137		61		42		17		11		6		-2.11	1	 5日:60.00 	10日:66.67 	20日:82.46 	30日:86.09 	60日:90.18 	超均线2022-06-21:
        jiSuanJiSheBei.put("600764", "中国海防");//计算机设备  	127		52		39		27		7		2		-1.77	2	 5日:10.34 	10日:15.38 	20日:56.27 	30日:58.73 	60日:67.90 	超均线2022-06-21:
    }

    /**
     * 医药商业
     **/
    public static Map<String, String> yiYaoShangYe = new HashMap<>();

    static {
        yiYaoShangYe.put("600056", "中国医药");//医药商业    	188		97		50		19		16		6		-0.05	1	 5日:57.23 	10日:33.50 	20日:34.29 	30日:14.79 	60日:7.15  	超均线2022-06-21:
        yiYaoShangYe.put("002589", "瑞康医药");//医药商业    	168		89		43		30		6		null		0.51	2	 5日:75.00 	10日:47.50 	20日:31.25 	30日:47.62 	60日:18.69 	超均线2022-06-21:    30
    }

    /**
     * 装修装饰
     **/
    public static Map<String, String> zhuangXiuZhuangShi = new HashMap<>();

    static {
        zhuangXiuZhuangShi.put("601886", "江河集团");//装修装饰    	252		90		77		45		28		12		5.94	1	 5日:83.93 	10日:85.33 	20日:90.36 	30日:91.32 	60日:92.68 	超均线2022-06-21:
        zhuangXiuZhuangShi.put("001212", "中旗新材");//装修装饰    	170		36		36		36		36		26		-10 	2	 5日:6.64  	10日:55.36 	20日:70.45 	30日:75.76 	60日:76.56 	超均线2022-06-21:
    }

    /**
     * 水泥建材
     **/
    public static Map<String, String> shuiNiJianCai = new HashMap<>();

    static {
        shuiNiJianCai.put("003037", "三和管桩");//水泥建材    	222		121		60		19		13		9		-1.54	1	 5日:49.06 	10日:58.88 	20日:58.88 	30日:29.52 	60日:47.95 	超均线2022-06-21:
        shuiNiJianCai.put("000546", "金圆股份");//水泥建材    	207		84		63		38		16		6		2.41	2	 5日:87.89 	10日:91.53 	20日:94.80 	30日:94.95 	60日:95.73 	超均线2022-06-21:15
    }

    /**
     * 通信服务
     **/
    public static Map<String, String> tongXinFuWu = new HashMap<>();

    static {
        tongXinFuWu.put("002544", "普天科技");//通信服务    	162		80		55		20		4		3		-0.04	1	 5日:22.56 	10日:28.72 	20日:38.96 	30日:55.65 	60日:73.79 	超均线2022-06-21:
        tongXinFuWu.put("002467", "二六三  ");//通信服务    	125		36		30		26		22		11		-2.28	2	 5日:23.29 	10日:47.66 	20日:48.15 	30日:53.33 	60日:64.67 	超均线2022-06-21:
    }

    /**
     * 互联网服务
     **/
    public static Map<String, String> huLianWangFuWu = new HashMap<>();

    static {
        huLianWangFuWu.put("600536", "中国软件");//互联网服务  	264		92		79		54		32		7		-5.41	1	 5日:19.68 	10日:31.66 	20日:72.23 	30日:72.75 	60日:79.76 	超均线2022-06-21:
        huLianWangFuWu.put("002453", "华软科技");//互联网服务  	208		84		71		32		16		5		-1.69	2	 5日:46.51 	10日:63.49 	20日:71.13 	30日:87.15 	60日:64.99 	超均线2022-06-21:
    }

    /**
     * 房地产服务
     **/
    public static Map<String, String> fangDiChanFuWu = new HashMap<>();

    static {
        fangDiChanFuWu.put("001914", "招商积余");//房地产服务  	163		64		49		19		16		15		1.21	1	 5日:61.80 	10日:65.50 	20日:58.48 	30日:43.56 	60日:42.48 	超均线2022-06-21:
        fangDiChanFuWu.put("600604", "市北高新");//房地产服务  	158		81		47		20		5		5		1.35	2	 5日:76.79 	10日:81.43 	20日:57.58 	30日:61.11 	60日:65.81 	超均线2022-06-21:
    }

    /**
     * 专用设备
     **/
    public static Map<String, String> zhuanYongSheBei = new HashMap<>();

    static {
        zhuanYongSheBei.put("000821", "京山轻机");//专用设备    	274		81		74		51		42		26		9.98	1	 5日:94.71 	10日:95.28 	20日:95.87 	30日:96.69 	60日:96.84 	超均线2022-06-21:
        zhuanYongSheBei.put("002006", "精功科技");//专用设备    	217		87		75		31		16		8		-1   	2	 5日:79.14 	10日:85.91 	20日:89.22 	30日:93.22 	60日:95.10 	超均线2022-06-21:
    }

    /**
     * 银行
     **/
    public static Map<String, String> yinHang = new HashMap<>();

    static {
        yinHang.put("001227", "兰州银行");//银行        	123		40		29		21		19		14		-0.2	1	 5日:6.82  	10日:28.07 	20日:35.94 	30日:35.94 	60日:23.59 	超均线2022-06-21:
        yinHang.put("002807", "江阴银行");//银行        	107		49		29		16		7		6		-0.23	2	 5日:5.66  	10日:13.79 	20日:41.18 	30日:46.24 	60日:59.02 	超均线2022-06-21:
    }

    /**
     * 家用轻工
     **/
    public static Map<String, String> jiaYongQingGong = new HashMap<>();

    static {
        jiaYongQingGong.put("603398", "沐邦高科");//家用轻工    	213		96		66		28		14		9		-0.19	1	 5日:76.49 	10日:76.49 	20日:58.50 	30日:60.60 	60日:80.45 	超均线2022-06-21:
        jiaYongQingGong.put("002489", "浙江永强");//家用轻工    	195		89		77		20		8		1		-0.24	2	 5日:30.30 	10日:52.83 	20日:63.77 	30日:53.66 	60日:76.54 	超均线2022-06-21:
    }

    /**
     * 多元金融
     **/
    public static Map<String, String> duoYuanJinRong = new HashMap<>();

    static {
        duoYuanJinRong.put("000567", "海德股份");//多元金融    	163		78		53		21		9		2		-2.33	1	 5日:21.24 	10日:29.92 	20日:72.10 	30日:80.31 	60日:87.03 	超均线2022-06-21:
        duoYuanJinRong.put("000987", "越秀金控");//多元金融    	162		56		38		26		22		20		1.58	2	 5日:46.25 	10日:67.18 	20日:70.95 	30日:74.33 	60日:76.18 	超均线2022-06-21:
    }

    /**
     * 装修建材
     **/
    public static Map<String, String> zhuangXiuJianCai = new HashMap<>();

    static {
        zhuangXiuJianCai.put("603801", "志邦家居");//装修建材    	222		78		52		42		26		24		1.82	1	 5日:80.56 	10日:82.10 	20日:83.09 	30日:84.95 	60日:84.95 	超均线2022-06-21:
        zhuangXiuJianCai.put("002791", "坚朗五金");//装修建材    	216		86		59		35		19		17		3.32	2	 5日:79.82 	10日:81.95 	20日:84.34 	30日:84.34 	60日:79.95 	超均线2022-06-21:
    }

    /**
     * 通用设备
     **/
    public static Map<String, String> tongYongSheBei = new HashMap<>();

    static {
        tongYongSheBei.put("603985", "恒润股份");//通用设备    	327		117		101		60		28		21		1.34	1	 5日:82.77 	10日:83.70 	20日:86.27 	30日:90.78 	60日:91.19 	超均线2022-06-21:
        tongYongSheBei.put("002132", "恒星科技");//通用设备    	256		91		81		39		30		15		-2.14	2	 5日:65.22 	10日:84.91 	20日:85.52 	30日:88.69 	60日:90.88 	超均线2022-06-21:
    }

    /**
     * 石油行业
     **/
    public static Map<String, String> shiYouHangYe = new HashMap<>();

    static {
        shiYouHangYe.put("600777", "新潮能源");//石油行业    	283		97		89		53		38		6		-0.39	1	 5日:4.60  	10日:6.74  	20日:34.65 	30日:37.12 	60日:52.02 	超均线2022-06-21:
        shiYouHangYe.put("600938", "中国海油");//石油行业    	227		88		88		36		11		4		1.11	2	 5日:13.58 	10日:12.74 	20日:30.60 	30日:42.50 	60日:57.24 	超均线2022-06-21:                102
    }

    /**
     * 仪器仪表
     **/
    public static Map<String, String> yiQiYiBiao = new HashMap<>();

    static {
        yiQiYiBiao.put("002819", "东方中科");//仪器仪表    	151		58		47		36		7		3		-1.83	1	 5日:39.61 	10日:20.41 	20日:44.50 	30日:44.50 	60日:30.81 	超均线2022-06-21:
        yiQiYiBiao.put("603100", "川仪股份");//仪器仪表    	128		53		45		21		9		null		4.65	2	 5日:72.32 	10日:84.80 	20日:90.03 	30日:92.19 	60日:94.81 	超均线2022-06-21:15  30          102
    }

    /**
     * 珠宝首饰
     **/
    public static Map<String, String> zhuBaoShouShi = new HashMap<>();

    static {
        zhuBaoShouShi.put("600916", "中国黄金");//珠宝首饰    	117		50		29		17		12		9		-1.86	1	 5日:42.66 	10日:51.76 	20日:51.76 	30日:53.93 	60日:40.27 	超均线2022-06-21:
        zhuBaoShouShi.put("002867", "周大生  ");//珠宝首饰    	93		36		32		15		5		5		-0.61	3	 5日:71.54 	10日:77.98 	20日:83.91 	30日:83.91 	60日:91.00 	超均线2022-06-21:
    }

    /**
     * 风电设备
     **/
    public static Map<String, String> fengDianSheBei = new HashMap<>();

    static {
        fengDianSheBei.put("002487", "大金重工");//风电设备    	317		116		100		49		27		25		0.66	1	 5日:88.94 	10日:88.94 	20日:89.34 	30日:94.04 	60日:94.35 	超均线2022-06-21:
        fengDianSheBei.put("002531", "天顺风能");//风电设备    	261		93		87		43		21		17		0.3 	2	 5日:75.85 	10日:77.94 	20日:82.74 	30日:88.97 	60日:89.36 	超均线2022-06-21:
    }

    /**
     * 煤炭行业
     **/
    public static Map<String, String> meiTanHangYe = new HashMap<>();

    static {
        meiTanHangYe.put("600121", "郑州煤电");//煤炭行业    	344		108		84		74		56		22		0.43	1	 5日:30.67 	10日:69.50 	20日:72.27 	30日:75.47 	60日:76.94 	超均线2022-06-21:
        meiTanHangYe.put("600508", "上海能源");//煤炭行业    	320		150		109		41		18		2		-0.84	2	 5日:6.71  	10日:6.20  	20日:28.71 	30日:36.39 	60日:60.06 	超均线2022-06-21:
    }

    /**
     * 工程咨询服务
     **/
    public static Map<String, String> gongChengZiXunFuWu = new HashMap<>();

    static {
        gongChengZiXunFuWu.put("603357", "设计总院");//工程咨询服务	160		64		61		17		10		8		-0.81	1	 5日:60.23 	10日:68.18 	20日:47.47 	30日:18.75 	60日:40.80 	超均线2022-06-21:
        gongChengZiXunFuWu.put("600629", "华建集团");//工程咨询服务	159		81		52		18		6		2		-0.54	2	 5日:45.65 	10日:28.38 	20日:23.13 	30日:15.98 	60日:29.31 	超均线2022-06-21:
    }

    /**
     * 旅游酒店
     **/
    public static Map<String, String> lvYouJiuDian = new HashMap<>();

    static {
        lvYouJiuDian.put("000524", "岭南控股");//旅游酒店    	198		97		56		26		12		7		-2.04	1	 5日:63.86 	10日:78.26 	20日:57.59 	30日:70.11 	60日:34.34 	超均线2022-06-21:
        lvYouJiuDian.put("601888", "中国中免");//旅游酒店    	160		72		44		23		13		8		0.46	2	 5日:86.13 	10日:87.03 	20日:91.57 	30日:92.64 	60日:92.89 	超均线2022-06-21:
    }

    /**
     * 电力行业
     **/
    public static Map<String, String> dianLiHangYe = new HashMap<>();

    static {
        dianLiHangYe.put("000722", "湖南发展");//电力行业    	304		132		132		30		6		4		1.99	1	 5日:44.33 	10日:43.43 	20日:11.96 	30日:7.57  	60日:41.73 	超均线2022-06-21:
        dianLiHangYe.put("001896", "豫能控股");//电力行业    	228		76		65		41		33		13		-3.48	2	 5日:33.33 	10日:54.43 	20日:69.00 	30日:71.94 	60日:77.24 	超均线2022-06-21:
    }

    /**
     * 消费电子
     **/
    public static Map<String, String> xiaoFeiDianZi = new HashMap<>();

    static {
        xiaoFeiDianZi.put("002881", "美格智能");//消费电子    	229		89		80		37		16		7		-3.02	1	 5日:44.17 	10日:63.41 	20日:77.26 	30日:86.55 	60日:88.44 	超均线2022-06-21:
        xiaoFeiDianZi.put("603595", "东尼电子");//消费电子    	212		90		74		27		13		8		-0.57	2	 5日:31.99 	10日:59.46 	20日:71.85 	30日:71.85 	60日:83.86 	超均线2022-06-21:
    }

    /**
     * 软件开发
     **/
    public static Map<String, String> ruanJianKaiFa = new HashMap<>();

    static {
        ruanJianKaiFa.put("601519", "大智慧  ");//软件开发    	212		63		54		43		32		20		-1.69	1	 5日:10.61 	10日:41.29 	20日:50.83 	30日:52.03 	60日:58.45 	超均线2022-06-21:
        ruanJianKaiFa.put("002268", "卫 士 通");//软件开发    	198		83		60		27		18		10		-4.53	2	 5日:26.04 	10日:65.56 	20日:74.35 	30日:79.15 	60日:51.92 	超均线2022-06-21:
    }

    /**
     * 造纸印刷
     **/
    public static Map<String, String> zaoZhiYinShua = new HashMap<>();

    static {
        zaoZhiYinShua.put("605007", "五洲特纸");//造纸印刷    	200		68		64		30		20		18		3.96	1	 5日:93.13 	10日:93.56 	20日:94.57 	30日:94.57 	60日:96.23 	超均线2022-06-21:
        zaoZhiYinShua.put("600963", "岳阳林纸");//造纸印刷    	193		68		66		33		14		12		-0.98	2	 5日:59.52 	10日:66.00 	20日:77.48 	30日:77.78 	60日:86.18 	超均线2022-06-21:
    }

    /**
     * 家电行业
     **/
    public static Map<String, String> jiaDianHangYe = new HashMap<>();

    static {
        jiaDianHangYe.put("605117", "德业股份");//家电行业    	260		102		82		38		20		18		-0.13	1	 5日:91.83 	10日:92.14 	20日:94.01 	30日:95.53 	60日:96.84 	超均线2022-06-21:
        jiaDianHangYe.put("002050", "三花智控");//家电行业    	231		78		67		40		29		18		1.76	2	 5日:58.83 	10日:69.73 	20日:76.11 	30日:76.31 	60日:81.20 	超均线2022-06-21:
    }

    /**
     * 航运港口
     **/
    public static Map<String, String> hangYunGangKou = new HashMap<>();

    static {
        hangYunGangKou.put("601975", "招商南油");//航运港口    	298		121		110		47		19		1		1.12	1	 5日:13.10 	10日:13.10 	20日:49.66 	30日:63.86 	60日:72.14 	超均线2022-06-21:
        hangYunGangKou.put("600026", "中远海能");//航运港口    	251		125		81		37		8		null		5.56	2	 5日:43.36 	10日:21.99 	20日:40.05 	30日:55.65 	60日:66.57 	超均线2022-06-21:15
    }

    /**
     * 钢铁行业
     **/
    public static Map<String, String> gangTieHangYe = new HashMap<>();

    static {
        gangTieHangYe.put("600399", "抚顺特钢");//钢铁行业    	176		80		60		20		8		8		-4.68	1	 5日:53.92 	10日:55.56 	20日:59.18 	30日:63.90 	60日:85.82 	超均线2022-06-21:
        gangTieHangYe.put("002756", "永兴材料");//钢铁行业    	174		77		62		24		9		2		-4.52	2	 5日:13.75 	10日:30.33 	20日:68.24 	30日:79.04 	60日:83.04 	超均线2022-06-21:
    }

    /**
     * 证券
     **/
    public static Map<String, String> zhengQuan = new HashMap<>();

    static {
        zhengQuan.put("601788", "光大证券");//证券        	274		77		67		58		54		18		0.6 	1	 5日:6.28  	10日:49.80 	20日:53.27 	30日:53.66 	60日:55.33 	超均线2022-06-21:
        zhengQuan.put("601236", "红塔证券");//证券        	227		80		50		38		32		27		-3.54	2	 5日:41.82 	10日:60.49 	20日:63.84 	30日:64.07 	60日:59.28 	超均线2022-06-21:
    }

    /**
     * 光学光电子
     **/
    public static Map<String, String> guangXueGuangDianZi = new HashMap<>();

    static {
        guangXueGuangDianZi.put("002876", "三利谱  ");//光学光电子  	229		63		53		50		42		21		-2.8	1	 5日:57.83 	10日:80.43 	20日:86.54 	30日:87.73 	60日:88.16 	超均线2022-06-21:
        guangXueGuangDianZi.put("603297", "永新光学");//光学光电子  	225		97		75		36		12		5		-0.69	2	 5日:55.54 	10日:68.55 	20日:74.98 	30日:81.59 	60日:62.86 	超均线2022-06-21:
    }

    /**
     * 纺织服装
     **/
    public static Map<String, String> fangZhiFuZhuang = new HashMap<>();

    static {
        fangZhiFuZhuang.put("603908", "牧高笛  ");//纺织服装    	240		80		80		40		27		13		-5.03	1	 5日:52.87 	10日:55.16 	20日:73.09 	30日:75.67 	60日:88.66 	超均线2022-06-21:
        fangZhiFuZhuang.put("605138", "盛泰集团");//纺织服装    	230		95		81		25		16		13		-2.64	2	 5日:40.78 	10日:65.14 	20日:69.50 	30日:41.74 	60日:31.34 	超均线2022-06-21:
    }

    /**
     * 工程建设
     **/
    public static Map<String, String> gongChengJianShe = new HashMap<>();

    static {
        gongChengJianShe.put("002761", "浙江建投");//工程建设    	350		158		138		46		6		2		5.4 	1	 5日:58.06 	10日:51.58 	20日:29.57 	30日:21.23 	60日:47.84 	超均线2022-06-21:15  30  60  101
        gongChengJianShe.put("002060", "粤 水 电");//工程建设    	325		166		87		49		12		11		-2.65	2	 5日:52.51 	10日:55.26 	20日:73.27 	30日:74.32 	60日:80.23 	超均线2022-06-21:
    }

    /**
     * 生物制品
     **/
    public static Map<String, String> shengWuZhiPin = new HashMap<>();

    static {
        shengWuZhiPin.put("000661", "长春高新");//生物制品    	205		68		59		35		24		19		0.73	1	 5日:75.81 	10日:79.10 	20日:84.05 	30日:84.80 	60日:87.04 	超均线2022-06-21:
        shengWuZhiPin.put("002007", "华兰生物");//生物制品    	198		60		56		41		23		18		-3.89	2	 5日:59.72 	10日:68.48 	20日:74.47 	30日:79.43 	60日:82.30 	超均线2022-06-21:
    }

    /**
     * 电子元件
     **/
    public static Map<String, String> dianZiYuanJian = new HashMap<>();

    static {
        dianZiYuanJian.put("600478", "科力远  ");//电子元件    	216		87		61		31		21		16		-1.3	1	 5日:81.56 	10日:86.73 	20日:88.84 	30日:90.68 	60日:92.74 	超均线2022-06-21:
        dianZiYuanJian.put("002384", "东山精密");//电子元件    	202		69		55		37		22		19		5.59	2	 5日:83.58 	10日:84.43 	20日:88.63 	30日:89.09 	60日:90.79 	超均线2022-06-21:
    }

    /**
     * 电网设备
     **/
    public static Map<String, String> dianWangSheBei = new HashMap<>();

    static {
        dianWangSheBei.put("002121", "科陆电子");//电网设备    	310		107		94		83		22		4		-4.88	1	 5日:18.69 	10日:10.36 	20日:53.24 	30日:61.81 	60日:63.81 	超均线2022-06-21:
        dianWangSheBei.put("603070", "XD万控智");//电网设备    	306		144		74		37		33		18		-2.33	2	 5日:84.95 	10日:89.11 	20日:90.04 	30日:90.04 	60日:26.80 	超均线2022-06-21:
    }

    /**
     * 玻璃玻纤
     **/
    public static Map<String, String> boLiBoQian = new HashMap<>();

    static {
        boLiBoQian.put("002201", "正威新材");//玻璃玻纤    	289		95		91		66		28		9		-1.1	1	 5日:72.54 	10日:68.40 	20日:86.81 	30日:88.62 	60日:89.68 	超均线2022-06-21:
        boLiBoQian.put("002163", "海南发展");//玻璃玻纤    	236		118		80		26		9		3		2.94	3	 5日:62.90 	10日:56.88 	20日:34.47 	30日:55.98 	60日:70.33 	超均线2022-06-21:    30  60
    }

    /**
     * 商业百货
     **/
    public static Map<String, String> shangYeBaiHuo = new HashMap<>();

    static {
        shangYeBaiHuo.put("002251", "步 步 高");//商业百货    	225		128		60		17		10		10		-0.14	1	 5日:10.00 	10日:28.95 	20日:19.08 	30日:8.35  	60日:15.39 	超均线2022-06-21:
        shangYeBaiHuo.put("002187", "广百股份");//商业百货    	209		88		65		27		18		11		-2.61	2	 5日:38.10 	10日:50.76 	20日:63.48 	30日:59.79 	60日:34.35 	超均线2022-06-21:
    }

    /**
     * 专业服务
     **/
    public static Map<String, String> zhuanYeFuWu = new HashMap<>();

    static {
        zhuanYeFuWu.put("002967", "广电计量");//专业服务    	125		55		40		21		5		4		2.11	1	 5日:87.50 	10日:90.82 	20日:95.05 	30日:95.05 	60日:67.16 	超均线2022-06-21:
        zhuanYeFuWu.put("003035", "南网能源");//专业服务    	101		38		32		19		8		4		-0.45	2	 5日:76.60 	10日:79.63 	20日:89.22 	30日:89.22 	60日:63.43 	超均线2022-06-21:
    }

    /**
     * 半导体
     **/
    public static Map<String, String> banDaoTi = new HashMap<>();

    static {
        banDaoTi.put("001270", "铖昌科技");//半导体      	330		70		70		70		70		50		10   	1	 5日:100.00	10日:100.00	20日:100.00	30日:100.00	60日:100.00	超均线2022-06-21:
        banDaoTi.put("600171", "XD上海贝");//半导体      	216		74		59		34		25		24		-1.02	2	 5日:72.02 	10日:75.00 	20日:76.32 	30日:80.60 	60日:83.58 	超均线2022-06-21:
    }

    /**
     * 铁路公路
     **/
    public static Map<String, String> tieLuGongLu = new HashMap<>();

    static {
        tieLuGongLu.put("603069", "海汽集团");//铁路公路    	415		105		105		105		75		25		-2.91	1	 5日:77.89 	10日:90.41 	20日:94.52 	30日:94.57 	60日:94.57 	超均线2022-06-21:
        tieLuGongLu.put("603032", "德新交运");//铁路公路    	277		119		92		38		19		9		-2.76	2	 5日:3.07  	10日:3.62  	20日:49.54 	30日:71.75 	60日:73.92 	超均线2022-06-21:
    }

    /**
     * 工程机械
     **/
    public static Map<String, String> gongChengJiXie = new HashMap<>();

    static {
        gongChengJiXie.put("605305", "中际联合");//工程机械    	221		80		70		38		19		14		0.06	1	 5日:71.00 	10日:72.52 	20日:76.52 	30日:86.98 	60日:87.93 	超均线2022-06-21:
        gongChengJiXie.put("000680", "山推股份");//工程机械    	190		87		69		22		9		3		-1.8	2	 5日:12.90 	10日:23.94 	20日:35.92 	30日:41.01 	60日:63.72 	超均线2022-06-21:
    }

    /**
     * 汽车整车
     **/
    public static Map<String, String> qiCheZhengChe = new HashMap<>();

    static {
        qiCheZhengChe.put("000957", "中通客车");//汽车整车    	377		106		106		106		30		29		0.34	1	 5日:97.85 	10日:98.89 	20日:99.45 	30日:99.49 	60日:99.49 	超均线2022-06-21:
        qiCheZhengChe.put("601127", "小康股份");//汽车整车    	327		134		100		63		20		10		-2.15	2	 5日:40.14 	10日:51.28 	20日:79.82 	30日:83.98 	60日:84.74 	超均线2022-06-21:
    }

    /**
     * 电子化学品
     **/
    public static Map<String, String> dianZiHuaXuePin = new HashMap<>();

    static {
        dianZiHuaXuePin.put("603078", "江化微  ");//电子化学品  	180		77		52		22		18		11		0.89	1	 5日:82.73 	10日:85.98 	20日:88.27 	30日:88.27 	60日:91.99 	超均线2022-06-21:
        dianZiHuaXuePin.put("002643", "万润股份");//电子化学品  	131		55		45		18		10		3		0.83	2	 5日:61.59 	10日:73.86 	20日:79.94 	30日:83.02 	60日:91.16 	超均线2022-06-21:
    }

    /**
     * 环保行业
     **/
    public static Map<String, String> huanBaoHangYe = new HashMap<>();

    static {
        huanBaoHangYe.put("000035", "中国天楹");//环保行业    	153		57		47		33		8		8		-3.6	2	 5日:47.69 	10日:56.41 	20日:74.68 	30日:78.26 	60日:79.59 	超均线2022-06-21:
        huanBaoHangYe.put("002645", "华宏科技");//环保行业    	136		64		45		14		11		2		-2.98	3	 5日:20.99 	10日:43.00 	20日:51.14 	30日:62.58 	60日:80.09 	超均线2022-06-21:
    }

    /**
     * 航空机场
     **/
    public static Map<String, String> lvYou_hangKongJiChang = new HashMap<>();

    static {
        lvYou_hangKongJiChang.put("603885", "吉祥航空");//航空机场    	134		62		44		18		7		3		0.65	1	 5日:70.08 	10日:74.32 	20日:84.80 	30日:88.45 	60日:69.43 	超均线2022-06-21:
        lvYou_hangKongJiChang.put("601111", "中国国航");//航空机场    	127		57		32		19		11		8		-0.88	2	 5日:81.90 	10日:84.03 	20日:87.16 	30日:91.36 	60日:81.38 	超均线2022-06-21:
    }

    /**
     * 包装材料
     **/
    public static Map<String, String> baoZhuangCaiLiao = new HashMap<>();

    static {
        baoZhuangCaiLiao.put("600210", "紫江企业");//包装材料    	184		65		61		41		14		3		-1.95	1	 5日:30.23 	10日:28.38 	20日:57.60 	30日:72.96 	60日:77.64 	超均线2022-06-21:
        baoZhuangCaiLiao.put("601968", "宝钢包装");//包装材料    	177		67		59		24		14		13		-0.96	2	 5日:67.03 	10日:73.68 	20日:73.68 	30日:54.34 	60日:65.40 	超均线2022-06-21:
    }

    /**
     * 医疗服务
     **/
    public static Map<String, String> yiLiaoFuWu = new HashMap<>();

    static {
        yiLiaoFuWu.put("600763", "通策医疗");//医疗服务    	193		82		53		29		16		13		0.13	1	 5日:89.89 	10日:90.38 	20日:93.85 	30日:94.06 	60日:95.05 	超均线2022-06-21:
        yiLiaoFuWu.put("000516", "国际医学");//医疗服务    	190		82		58		28		12		10		-1.55	2	 5日:63.33 	10日:68.39 	20日:81.10 	30日:81.10 	60日:86.49 	超均线2022-06-21:
    }

    /**
     * 物流行业
     **/
    public static Map<String, String> wuLiuHangYe = new HashMap<>();

    static {
        wuLiuHangYe.put("002183", "怡 亚 通");//物流行业    	279		128		103		39		7		2		-3.13	1	 5日:16.67 	10日:6.25  	20日:4.42  	30日:33.70 	60日:53.06 	超均线2022-06-21:
        wuLiuHangYe.put("600233", "圆通速递");//物流行业    	190		81		63		29		14		3		-2.41	2	 5日:15.74 	10日:14.91 	20日:42.26 	30日:56.01 	60日:73.75 	超均线2022-06-21:
    }

    /**
     * 通信设备
     **/
    public static Map<String, String> tongXinSheBei = new HashMap<>();

    static {
        tongXinSheBei.put("002897", "意华股份");//通信设备    	215		78		71		42		20		4		-2.78	1	 5日:28.18 	10日:25.00 	20日:65.01 	30日:75.47 	60日:80.05 	超均线2022-06-21:
        tongXinSheBei.put("600776", "东方通信");//通信设备    	204		81		55		44		12		12		-3.3	2	 5日:44.74 	10日:53.54 	20日:72.00 	30日:72.22 	60日:76.08 	超均线2022-06-21:
    }

    /**
     * 酿酒行业
     **/
    public static Map<String, String> niangJiuHangYe = new HashMap<>();

    static {
        niangJiuHangYe.put("601579", "会稽山  ");//酿酒行业    	252		115		102		19		10		6		-2   	1	 5日:42.20 	10日:54.01 	20日:28.79 	30日:8.76  	60日:33.65 	超均线2022-06-21:
        niangJiuHangYe.put("600199", "金种子酒");//酿酒行业    	189		92		54		18		14		11		0.17	2	 5日:72.79 	10日:72.79 	20日:73.04 	30日:69.54 	60日:73.38 	超均线2022-06-21:
    }

    /**
     * 医疗器械
     **/
    public static Map<String, String> yiLiaoQiXie = new HashMap<>();

    static {
        yiLiaoQiXie.put("002432", "九安医疗");//医疗器械    	202		126		46		17		12		1		-2.41	1	 5日:14.41 	10日:10.90 	20日:28.83 	30日:16.68 	60日:7.70  	超均线2022-06-21:
        yiLiaoQiXie.put("600587", "新华医疗");//医疗器械    	192		76		64		22		19		11		-1.85	2	 5日:65.78 	10日:79.31 	20日:80.09 	30日:80.09 	60日:50.59 	超均线2022-06-21:
    }

    /**
     * 化学制药
     **/
    public static Map<String, String> huaXueZhiYao = new HashMap<>();

    static {
        huaXueZhiYao.put("000756", "新华制药");//化学制药    	538		232		205		65		23		13		4.95	1	 5日:69.33 	10日:68.48 	20日:27.87 	30日:43.18 	60日:64.14 	超均线2022-06-21:
        huaXueZhiYao.put("600062", "华润双鹤");//化学制药    	371		192		106		38		21		14		-1.84	2	 5日:61.30 	10日:62.84 	20日:17.96 	30日:14.70 	60日:41.02 	超均线2022-06-21:
    }

    /**
     * 化肥行业
     **/
    public static Map<String, String> huaFeiHangYe = new HashMap<>();

    static {
        huaFeiHangYe.put("603938", "三孚股份");//化肥行业    	296		120		92		49		31		4		0.21	1	 5日:22.59 	10日:37.41 	20日:51.04 	30日:60.02 	60日:69.16 	超均线2022-06-21:
        huaFeiHangYe.put("002895", "川恒股份");//化肥行业    	255		116		83		41		11		4		-1.01	2	 5日:16.69 	10日:12.26 	20日:30.14 	30日:45.33 	60日:61.91 	超均线2022-06-21:
    }

    /**
     * 贸易行业
     **/
    public static Map<String, String> maoYiHangYe = new HashMap<>();

    static {
        maoYiHangYe.put("600058", "五矿发展");//贸易行业    	266		92		82		67		20		5		-10.03	1	 5日:0.00  	10日:0.00  	20日:47.16 	30日:50.23 	60日:54.66 	超均线2022-06-21:
        maoYiHangYe.put("000829", "天音控股");//贸易行业    	182		81		57		29		11		4		4.39	2	 5日:84.13 	10日:89.30 	20日:91.27 	30日:91.27 	60日:52.50 	超均线2022-06-21:15
    }

    /**
     * 塑料制品
     **/
    public static Map<String, String> suLiaoZhiPin = new HashMap<>();

    static {
        suLiaoZhiPin.put("002324", "普利特  ");//塑料制品    	227		77		69		36		26		19		-0.88	1	 5日:86.31 	10日:94.06 	20日:94.06 	30日:95.71 	60日:96.09 	超均线2022-06-21:
        suLiaoZhiPin.put("603051", "鹿山新材");//塑料制品    	185		73		33		33		33		13		4.31	2	 5日:81.24 	10日:77.37 	20日:89.94 	30日:90.29 	60日:76.82 	超均线2022-06-21:15  30
    }

    /**
     * 燃气
     **/
    public static Map<String, String> ranQi = new HashMap<>();

    static {
        ranQi.put("000968", "蓝焰控股");//燃气        	271		130		80		34		22		5		0   	1	 5日:6.22  	10日:5.51  	20日:18.93 	30日:17.34 	60日:54.25 	超均线2022-06-21:
        ranQi.put("600903", "贵州燃气");//燃气        	209		60		54		45		40		10		-2.5	2	 5日:4.15  	10日:33.76 	20日:41.08 	30日:41.57 	60日:48.64 	超均线2022-06-21:
    }

    /**
     * 公用事业
     **/
    public static Map<String, String> gongYongShiYe = new HashMap<>();

    static {
        gongYongShiYe.put("600283", "钱江水利");//公用事业    	239		69		69		69		19		13		-5   	1	 5日:4.65  	10日:12.21 	20日:43.82 	30日:45.66 	60日:50.19 	超均线2022-06-21:
        gongYongShiYe.put("003039", "顺控发展");//公用事业    	233		83		82		38		17		13		-3.59	2	 5日:38.03 	10日:52.16 	20日:52.23 	30日:68.03 	60日:73.23 	超均线2022-06-21:
    }

    /**
     * 综合行业
     **/
    public static Map<String, String> zongHeHangYe = new HashMap<>();

    static {
        zongHeHangYe.put("000833", "粤桂股份");//综合行业    	260		109		72		50		28		1		0.34	1	 5日:3.15  	10日:2.66  	20日:28.32 	30日:35.49 	60日:49.38 	超均线2022-06-21:
        zongHeHangYe.put("000009", "中国宝安");//综合行业    	216		84		74		33		15		10		-4.67	2	 5日:49.00 	10日:53.64 	20日:76.17 	30日:84.38 	60日:86.54 	超均线2022-06-21:
    }

    /**
     * 汽车零部件
     **/
    public static Map<String, String> qiCheLingBuJian = new HashMap<>();

    static {
        qiCheLingBuJian.put("603997", "继峰股份");//汽车零部件  	336		114		92		58		44		28		-2.13	1	 5日:56.56 	10日:72.25 	20日:73.39 	30日:76.93 	60日:80.08 	超均线2022-06-21:
        qiCheLingBuJian.put("002865", "钧达股份");//汽车零部件  	331		127		99		55		36		14		-3.28	2	 5日:58.79 	10日:77.03 	20日:83.93 	30日:85.35 	60日:89.04 	超均线2022-06-21:
    }

    /**
     * 中药
     **/
    public static Map<String, String> zhongYao = new HashMap<>();

    static {
        zhongYao.put("002349", "精华制药");//中药        	280		139		83		28		20		10		0.43	1	 5日:58.80 	10日:47.60 	20日:55.07 	30日:28.43 	60日:23.53 	超均线2022-06-21:
        zhongYao.put("002317", "众生药业");//中药        	229		122		73		21		12		1		0   	2	 5日:35.87 	10日:25.19 	20日:14.16 	30日:8.26  	60日:28.53 	超均线2022-06-21:
    }

    /**
     * 采掘行业
     **/
    public static Map<String, String> caiJueHangYe = new HashMap<>();

    static {
        caiJueHangYe.put("603619", "中曼石油");//采掘行业    	268		116		86		39		17		10		-0.05	2	 5日:7.46  	10日:7.46  	20日:39.02 	30日:46.10 	60日:51.86 	超均线2022-06-21:
        caiJueHangYe.put("000762", "西藏矿业");//采掘行业    	268		92		82		48		29		17		-3.25	1	 5日:47.28 	10日:70.08 	20日:75.16 	30日:82.12 	60日:85.97 	超均线2022-06-21:
    }

    /**
     * 非金属材料
     **/
    public static Map<String, String> feiJinShuCaiLiao = new HashMap<>();

    static {
        feiJinShuCaiLiao.put("603612", "索通发展");//非金属材料  	340		127		97		55		41		23		-4.15	1	 5日:65.22 	10日:77.50 	20日:81.96 	30日:86.98 	60日:86.98 	超均线2022-06-21:
        feiJinShuCaiLiao.put("603688", "石英股份");//非金属材料  	333		127		103		57		35		14		-7.54	2	 5日:59.10 	10日:68.64 	20日:82.01 	30日:84.88 	60日:88.56 	超均线2022-06-21:
    }

    /**
     * 化学原料
     **/
    public static Map<String, String> huaXueYuanLiao = new HashMap<>();

    static {
        huaXueYuanLiao.put("002125", "湘潭电化");//化学原料    	272		100		75		48		35		18		-2.94	1	 5日:32.90 	10日:62.68 	20日:70.99 	30日:74.15 	60日:77.46 	超均线2022-06-21:
        huaXueYuanLiao.put("603299", "苏盐井神");//化学原料    	241		90		93		37		17		4		-4.48	2	 5日:19.61 	10日:39.51 	20日:51.32 	30日:54.91 	60日:74.35 	超均线2022-06-21:
    }

    /**
     * 食品饮料
     **/
    public static Map<String, String> shiPinYinLiao = new HashMap<>();

    static {
        shiPinYinLiao.put("001318", "阳光乳业");//食品饮料    	203		60		67		67		7		2		1.26	1	 5日:79.43 	10日:31.02 	20日:21.32 	30日:40.77 	60日:40.77 	超均线2022-06-21:15  30
        shiPinYinLiao.put("002840", "华统股份");//食品饮料    	195		59		48		37		34		17		0.54	2	 5日:65.35 	10日:82.43 	20日:82.96 	30日:82.96 	60日:82.96 	超均线2022-06-21:
    }

    /**
     * 汽车服务
     **/
    public static Map<String, String> qiCheFuWu = new HashMap<>();

    static {
        qiCheFuWu.put("000025", "特  力Ａ");//汽车服务    	510		157		140		129		57		27		-9.32	1	 5日:24.85 	10日:53.80 	20日:72.27 	30日:73.37 	60日:74.51 	超均线2022-06-21:
        qiCheFuWu.put("600335", "国机汽车");//汽车服务    	386		154		106		63		47		16		9.98	2	 5日:86.07 	10日:86.34 	20日:86.92 	30日:89.71 	60日:90.56 	超均线2022-06-21:    30
    }

    /**
     * 美容护理
     **/
    public static Map<String, String> meiRongHuLi = new HashMap<>();

    static {
        meiRongHuLi.put("000523", "广州浪奇");//美容护理    	203		83		68		45		7		null		-0.76	1	 5日:4.35  	10日:3.53  	20日:32.12 	30日:45.63 	60日:47.91 	超均线2022-06-21:
        meiRongHuLi.put("603983", "丸美股份");//美容护理    	199		65		54		31		25		24		-3.38	2	 5日:71.33 	10日:76.59 	20日:76.59 	30日:81.43 	60日:85.19 	超均线2022-06-21:
    }

    /**
     * 化纤行业
     **/
    public static Map<String, String> huaQianHangYe = new HashMap<>();

    static {
        huaQianHangYe.put("002206", "海 利 得");//化纤行业    	203		75		68		40		16		4		-3.22	1	 5日:23.21 	10日:57.43 	20日:72.78 	30日:79.33 	60日:84.86 	超均线2022-06-21:
        huaQianHangYe.put("000301", "东方盛虹");//化纤行业    	191		74		61		33		21		2		-3.18	2	 5日:10.85 	10日:38.13 	20日:43.43 	30日:62.12 	60日:68.57 	超均线2022-06-21:
    }

    /**
     * 农药兽药
     **/
    public static Map<String, String> nongYaoShouYao = new HashMap<>();

    static {
        nongYaoShouYao.put("600389", "江山股份");//农药兽药    	207		101		59		27		16		4		-3.59	1	 5日:14.92 	10日:11.55 	20日:29.61 	30日:32.94 	60日:66.62 	超均线2022-06-21:
        nongYaoShouYao.put("000553", "安道麦A");//农药兽药    	188		83		67		25		11		2		-2.69	2	 5日:13.11 	10日:13.01 	20日:52.65 	30日:67.08 	60日:81.03 	超均线2022-06-21:
    }

    /**
     * 交运设备
     **/
    public static Map<String, String> jiaoYunSheBei = new HashMap<>();

    static {
        jiaoYunSheBei.put("000913", "钱江摩托");//交运设备    	252		78		70		56		31		17		0.19	1	 5日:63.41 	10日:74.80 	20日:82.94 	30日:86.25 	60日:87.29 	超均线2022-06-21:
        jiaoYunSheBei.put("603529", "爱玛科技");//交运设备    	206		95		62		26		15		8		-3.7	2	 5日:54.43 	10日:62.34 	20日:75.37 	30日:81.30 	60日:87.11 	超均线2022-06-21:
    }

    /**
     * 光伏设备
     **/
    public static Map<String, String> guangFuSheBei = new HashMap<>();

    static {
        guangFuSheBei.put("600732", "爱旭股份");//光伏设备    	356		144		130		50		20		12		-2.05	1	 5日:61.95 	10日:66.03 	20日:83.15 	30日:85.59 	60日:90.89 	超均线2022-06-21:
        guangFuSheBei.put("603396", "金辰股份");//光伏设备    	300		102		89		56		39		14		1.57	2	 5日:69.80 	10日:71.45 	20日:85.47 	30日:87.39 	60日:89.22 	超均线2022-06-21:
    }

    /**
     * 农牧饲渔
     **/
    public static Map<String, String> nongMuSiYu = new HashMap<>();

    static {
        nongMuSiYu.put("600313", "农发种业");//农牧饲渔    	379		215		121		38		5		null		-4.91	1	 5日:6.25  	10日:4.10  	20日:3.29  	30日:48.76 	60日:65.89 	超均线2022-06-21:
        nongMuSiYu.put("603668", "天马科技");//农牧饲渔    	299		107		78		47		39		28		-1.88	2	 5日:59.46 	10日:86.05 	20日:87.53 	30日:89.31 	60日:89.92 	超均线2022-06-21:
    }

    /**
     * 电池
     **/
    public static Map<String, String> dianChi = new HashMap<>();

    static {
        dianChi.put("002192", "融捷股份");//电池        	232		106		80		32		12		2		-2.78	1	 5日:19.54 	10日:14.60 	20日:63.32 	30日:75.89 	60日:83.11 	超均线2022-06-21:
        dianChi.put("002245", "蔚蓝锂芯");//电池        	224		97		72		35		12		8		-0.6	2	 5日:59.82 	10日:64.42 	20日:83.33 	30日:84.66 	60日:86.72 	超均线2022-06-21:
    }

    /**
     * 化学制品
     **/
    public static Map<String, String> huaXueZhiPin = new HashMap<>();

    static {
        huaXueZhiPin.put("605366", "宏柏新材");//化学制品    	380		129		116		90		33		12		-3.29	1	 5日:3.94  	10日:26.95 	20日:61.21 	30日:64.77 	60日:71.19 	超均线2022-06-21:
        huaXueZhiPin.put("003022", "联泓新科");//化学制品    	262		93		90		48		31		9		-5.24	2	 5日:13.22 	10日:52.39 	20日:55.52 	30日:70.54 	60日:73.97 	超均线2022-06-21:
    }

    /**
     * 橡胶制品
     **/
    public static Map<String, String> xiangJiaoZhiPin = new HashMap<>();

    static {
        xiangJiaoZhiPin.put("002068", "黑猫股份");//橡胶制品    	328		124		104		58		29		13		-3.88	1	 5日:8.81  	10日:52.69 	20日:72.59 	30日:77.72 	60日:79.86 	超均线2022-06-21:
        xiangJiaoZhiPin.put("000887", "中鼎股份");//橡胶制品    	266		99		80		48		28		11		-2.45	2	 5日:25.00 	10日:67.68 	20日:77.71 	30日:83.13 	60日:85.27 	超均线2022-06-21:
    }

    /**
     * 贵金属
     **/
    public static Map<String, String> guiJinShu = new HashMap<>();

    static {
        guiJinShu.put("000975", "银泰黄金");//贵金属      	185		86		56		29		11		3		-3.34	1	 5日:11.58 	10日:53.33 	20日:57.58 	30日:70.42 	60日:73.67 	超均线2022-06-21:
        guiJinShu.put("002155", "湖南黄金");//贵金属      	179		58		45		32		26		18		-1.08	2	 5日:72.83 	10日:77.74 	20日:79.62 	30日:80.77 	60日:82.92 	超均线2022-06-21:
    }

    /**
     * 船舶制造
     **/
    public static Map<String, String> chuanBoZhiZao = new HashMap<>();

    static {
        chuanBoZhiZao.put("601890", "亚星锚链");//船舶制造    	125		58		40		23		12		12		-3.93	1	 5日:36.08 	10日:46.55 	20日:46.55 	30日:66.84 	60日:70.48 	超均线2022-06-21:
        chuanBoZhiZao.put("600150", "中国船舶");//船舶制造    	104		44		35		19		5		1		-1.45	2	 5日:9.41  	10日:15.69 	20日:47.62 	30日:58.75 	60日:66.76 	超均线2022-06-21:
    }

    /**
     * 有色金属
     **/
    public static Map<String, String> youSeJinShu = new HashMap<>();

    static {
        youSeJinShu.put("002824", "和胜股份");//有色金属    	340		113		102		67		35		23		-4.11	1	 5日:63.13 	10日:71.15 	20日:78.26 	30日:85.05 	60日:85.76 	超均线2022-06-21:
        youSeJinShu.put("601702", "华峰铝业");//有色金属    	272		89		80		51		32		20		-2.33	2	 5日:83.53 	10日:86.23 	20日:90.58 	30日:92.80 	60日:93.59 	超均线2022-06-21:
    }

    /**
     * 航天航空
     **/
    public static Map<String, String> hangTianHangKong = new HashMap<>();

    static {
        hangTianHangKong.put("003009", "中天火箭");//航天航空    	209		79		68		47		11		7		-2.51	1	 5日:17.23 	10日:23.72 	20日:47.24 	30日:47.24 	60日:59.15 	超均线2022-06-21:
        hangTianHangKong.put("600862", "中航高科");//航天航空    	196		78		62		29		17		10		-1.69	2	 5日:67.30 	10日:72.99 	20日:77.19 	30日:80.08 	60日:90.22 	超均线2022-06-21:
    }

    /**
     * 电源设备
     **/
    public static Map<String, String> dianYuanSheBei = new HashMap<>();

    static {
        dianYuanSheBei.put("002518", "科士达  ");//电源设备    	278		97		90		44		31		16		-1.35	1	 5日:74.80 	10日:85.29 	20日:86.00 	30日:91.87 	60日:92.47 	超均线2022-06-21:
        dianYuanSheBei.put("002851", "麦格米特");//电源设备    	183		55		51		37		26		14		0.69	2	 5日:58.15 	10日:80.43 	20日:83.37 	30日:83.50 	60日:87.18 	超均线2022-06-21:
    }

    /**
     * 能源金属
     **/
    public static Map<String, String> nengYuanJinShu = new HashMap<>();

    static {
        nengYuanJinShu.put("002466", "天齐锂业");//能源金属    	226		91		80		36		15		4		-2.65	1	 5日:18.72 	10日:52.68 	20日:75.01 	30日:83.41 	60日:88.35 	超均线2022-06-21:
        nengYuanJinShu.put("603799", "华友钴业");//能源金属    	217		82		70		35		19		11		-2.56	2	 5日:66.79 	10日:73.30 	20日:83.91 	30日:87.71 	60日:89.28 	超均线2022-06-21:
    }

    /**
     * 教育
     **/
    public static Map<String, String> jiaoYu = new HashMap<>();

    static {
        jiaoYu.put("600880", "博瑞传播");//教育        	299		113		77		56		40		16		2.37	1	 5日:80.87 	10日:91.06 	20日:91.88 	30日:92.31 	60日:91.50 	超均线2022-06-21:
        jiaoYu.put("002607", "中公教育");//教育        	252		90		76		40		33		13		-4.54	2	 5日:17.33 	10日:52.31 	20日:65.56 	30日:73.04 	60日:76.25 	超均线2022-06-21:
    }

    /**
     * 小金属
     **/
    public static Map<String, String> xiaoJinShu = new HashMap<>();

    static {
        xiaoJinShu.put("603399", "吉翔股份");//小金属      	286		147		75		34		24		6		-0.04	1	 5日:58.94 	10日:79.55 	20日:84.24 	30日:86.11 	60日:70.57 	超均线2022-06-21:
        xiaoJinShu.put("002056", "横店东磁");//小金属      	243		85		95		34		19		10		-6.03	2	 5日:1.30  	10日:30.47 	20日:31.10 	30日:66.52 	60日:74.12 	超均线2022-06-21:
    }

    /**
     * 资源
     */
    public static Map<String, String> ziYuan = new HashMap<>();

    static {
        ziYuan.putAll(meiTanHangYe);
        ziYuan.putAll(hangYunGangKou);
        ziYuan.putAll(huaFeiHangYe);
        ziYuan.putAll(nongYaoShouYao);
        ziYuan.putAll(gangTieHangYe);
        ziYuan.putAll(huaXueYuanLiao);
        ziYuan.putAll(caiJueHangYe);
        ziYuan.putAll(ranQi);
        ziYuan.putAll(nongMuSiYu);//农牧饲渔
        ziYuan.putAll(feiJinShuCaiLiao);
        ziYuan.putAll(zaoZhiYinShua);
        ziYuan.putAll(shiYouHangYe);
        ziYuan.putAll(huaQianHangYe);
        ziYuan.putAll(youSeJinShu);
        ziYuan.putAll(huaXueZhiPin);
        ziYuan.putAll(boLiBoQian);
        ziYuan.putAll(baoZhuangCaiLiao);
        ziYuan.putAll(suLiaoZhiPin);
        ziYuan.putAll(xiangJiaoZhiPin);
        ziYuan.putAll(maoYiHangYe);//贸易行业
    }

    /**
     * 金融
     */
    public static Map<String, String> jinRong = new HashMap<>();

    static {
        jinRong.putAll(yinHang);//银行
        jinRong.putAll(fangDiChanKaiFa);
        jinRong.putAll(gongChengJianShe);
        jinRong.putAll(shuiNiJianCai);
        jinRong.putAll(guiJinShu);
        jinRong.putAll(tieLuGongLu);//铁路公路
        jinRong.putAll(gongChengZiXunFuWu);
        jinRong.putAll(fangDiChanFuWu);
        jinRong.putAll(gongYongShiYe);
        jinRong.putAll(xiaoJinShu);
        jinRong.putAll(baoXian);
        jinRong.putAll(zhuBaoShouShi);
        jinRong.putAll(zongHeHangYe);
        jinRong.putAll(zhuangXiuJianCai);
        jinRong.putAll(zhengQuan);
        jinRong.putAll(duoYuanJinRong);
        jinRong.putAll(gongChengJiXie);
        jinRong.putAll(zhuangXiuZhuangShi);//装修装饰
    }

    /**
     * 消费
     */
    public static Map<String, String> xiaoFei = new HashMap<>();

    static {
        xiaoFei.putAll(shangYeBaiHuo);//商业百货
        xiaoFei.putAll(lvYouJiuDian);
        xiaoFei.putAll(lvYou_hangKongJiChang);
        xiaoFei.putAll(wuLiuHangYe);
        xiaoFei.putAll(jiaDianHangYe);
        xiaoFei.putAll(shiPinYinLiao);
        xiaoFei.putAll(niangJiuHangYe);
        xiaoFei.putAll(fangZhiFuZhuang);
        xiaoFei.putAll(jiaYongQingGong);
        xiaoFei.putAll(qiCheFuWu);
        xiaoFei.putAll(meiRongHuLi);
        xiaoFei.putAll(wenHuaChuanMei);
        xiaoFei.putAll(youXi);
    }

    /**
     * keJi 科技
     */
    public static Map<String, String> keJi = new HashMap<>();

    static {
        keJi.putAll(dianLiHangYe);//电力行业
        keJi.putAll(jiaoYunSheBei);
        keJi.putAll(dianZiHuaXuePin);
        keJi.putAll(tongXinFuWu);
        keJi.putAll(jiaoYu);
        keJi.putAll(zhuanYeFuWu);
        keJi.putAll(huanBaoHangYe);
        keJi.putAll(tongYongSheBei);
        keJi.putAll(nengYuanJinShu);
        keJi.putAll(huLianWangFuWu);
        keJi.putAll(tongXinSheBei);
        keJi.putAll(qiCheLingBuJian);
        keJi.putAll(qiCheZhengChe);
        keJi.putAll(zhuanYongSheBei);
        keJi.putAll(chuanBoZhiZao);
        keJi.putAll(dianWangSheBei);
        keJi.putAll(yiQiYiBiao);
        keJi.putAll(jiSuanJiSheBei);
        keJi.putAll(ruanJianKaiFa);
        keJi.putAll(guangFuSheBei);
        keJi.putAll(dianZiYuanJian);
        keJi.putAll(dianJi);
        keJi.putAll(dianYuanSheBei);
        keJi.putAll(banDaoTi);
        keJi.putAll(hangTianHangKong);
        keJi.putAll(guangXueGuangDianZi);
        keJi.putAll(xiaoFeiDianZi);
        keJi.putAll(dianChi);
        keJi.putAll(fengDianSheBei);
    }

    /**
     * yiLiao 医疗
     */
    public static Map<String, String> yiLiao = new HashMap<>();

    static {
        yiLiao.putAll(yiYaoShangYe);
        yiLiao.putAll(huaXueZhiYao);
        yiLiao.putAll(zhongYao);
        yiLiao.putAll(yiLiaoQiXie);
        yiLiao.putAll(shengWuZhiPin);
        yiLiao.putAll(yiLiaoFuWu);
    }

    /**
     * all
     */
    public static Map<String, String> all = new HashMap<>();

    static {
        all.putAll(xiaoFei);//消费
        all.putAll(jinRong);//金融
        all.putAll(ziYuan);//资源
        all.putAll(keJi);//科技
        all.putAll(yiLiao);//医疗
    }

    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-06-24";
        String spDate = null;
//        spDate = "2022-05-09";//特定日期：下一交易日
        boolean isShowPriceArea = true;//是否显示价格区间
//        boolean isShowPriceArea = false;//是否显示价格区间
        List<String> kltList = Arrays.asList(KLT_5, KLT_15, KLT_30, KLT_60, KLT_101);//价格区间周期列表
//        kltList.add(KLT_5);
//        kltList.add(KLT_102);
        boolean isShowUpMa = true;//是否显示-超过均线
//        boolean isShowUpMa = false;//是否显示-超过均线
        boolean isShowFlowIn = true;//是否显示-主力净流入市值比
//        boolean isShowFlowIn = false;//是否显示-主力净流入市值比
        boolean isFindKline = true;//是否查询最新k线
        List<StockAdrCountVo> rs = null;
        rs = KlineService.checkMaDemo(lvYou_hangKongJiChang, date, isShowPriceArea, isShowUpMa, isFindKline, kltList);
        String orderField = ORDER_FIELD_NET_AREA_DAY_5;
        KlineService.showStockMa(rs, orderField, false, isShowPriceArea, isShowUpMa,isShowFlowIn, kltList, spDate);
//        KlineService.checkMaDemo(lvYouJiuDian, date, spDate);// all   huaGong   huaGong_linHuaGong    yinHang
    }
}
