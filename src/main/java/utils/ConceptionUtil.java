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
        /**
         * 指数
         */
        {
            stConceptionMap.put("上证50_", "shangZheng_50");
        }

        /**
         * 业务板块
         */
        {
            stConceptionMap.put("煤炭行业", "meiTanHangYe");
            stConceptionMap.put("房地产开发", "fangDiChanKaiFa");
            stConceptionMap.put("工程建设", "gongChengJianShe");
            stConceptionMap.put("银行", "yinHang");
            stConceptionMap.put("商业百货", "shangYeBaiHuo");
            stConceptionMap.put("旅游酒店", "lvYouJiuDian");
            stConceptionMap.put("航运港口", "hangYunGangKou");
            stConceptionMap.put("水泥建材", "shuiNiJianCai");
            stConceptionMap.put("贵金属", "guiJinShu");
            stConceptionMap.put("化肥行业", "huaFeiHangYe");

            stConceptionMap.put("铁路公路", "tieLuGongLu");
            stConceptionMap.put("农药兽药", "nongYaoShouYao");
            stConceptionMap.put("工程咨询服务", "gongChengZiXunFuWu");
            stConceptionMap.put("航空机场", "hangKongJiChang");
            stConceptionMap.put("物流行业", "wuLiuHangYe");
            stConceptionMap.put("钢铁行业", "gangTieHangYe");
            stConceptionMap.put("农牧饲渔", "nongMuSiYu");
            stConceptionMap.put("房地产服务", "fangDiChanFuWu");
            stConceptionMap.put("综合行业", "zongHeHangYe");
            stConceptionMap.put("公用事业", "gongYongShiYe");

            stConceptionMap.put("保险", "baoXian");
            stConceptionMap.put("珠宝首饰", "zhuBaoShouShi");
            stConceptionMap.put("医药商业", "yiYaoShangYe");
            stConceptionMap.put("贸易行业", "maoYiHangYe");
            stConceptionMap.put("酿酒行业", "niangJiuHangYe");
            stConceptionMap.put("化学制药", "huaXueZhiYao");
            stConceptionMap.put("石油行业", "shiYouHangYe");
            stConceptionMap.put("化纤行业", "huaQianHangYe");
            stConceptionMap.put("纺织服装", "fangZhiFuZhuang");
            stConceptionMap.put("中药", "zhongYao");

            stConceptionMap.put("化学原料", "huaXueYuanLiao");
            stConceptionMap.put("采掘行业", "caiJueHangYe");
            stConceptionMap.put("燃气", "ranQi");
            stConceptionMap.put("电力行业", "dianLiHangYe");
            stConceptionMap.put("非金属材料", "feiJinShuCaiLiao");
            stConceptionMap.put("造纸印刷", "zaoZhiYinShua");
            stConceptionMap.put("家电行业", "jiaDianHangYe");
            stConceptionMap.put("食品饮料", "shiPinYinLiao");
            stConceptionMap.put("交运设备", "jiaoYunSheBei");
            stConceptionMap.put("小金属", "xiaoJinShu");

            stConceptionMap.put("家用轻工", "jiaYongQingGong");
            stConceptionMap.put("电子化学品", "dianZiHuaXuePin");
            stConceptionMap.put("有色金属", "youSeJinShu");
            stConceptionMap.put("化学制品", "huaXueZhiPin");
            stConceptionMap.put("汽车服务", "qiCheFuWu");
            stConceptionMap.put("装修建材", "zhuangXiuJianCai");
            stConceptionMap.put("通信服务", "tongXinFuWu");
            stConceptionMap.put("装修装饰", "zhuangXiuZhuangShi");
            stConceptionMap.put("玻璃玻纤", "boLiBoQian");
            stConceptionMap.put("证券", "zhengQuan");

            stConceptionMap.put("教育", "jiaoYu");
            stConceptionMap.put("美容护理", "meiRongHuLi");
            stConceptionMap.put("文化传媒", "wenHuaChuanMei");
            stConceptionMap.put("多元金融", "duoYuanJinRong");
            stConceptionMap.put("医疗器械", "yiLiaoQiXie");
            stConceptionMap.put("生物制品", "shengWuZhiPin");
            stConceptionMap.put("专业服务", "zhuanYeFuWu");
            stConceptionMap.put("环保行业", "huanBaoHangYe");
            stConceptionMap.put("医疗服务", "yiLiaoFuWu");
            stConceptionMap.put("通用设备", "tongYongSheBei");

            stConceptionMap.put("包装材料", "baoZhuangCaiLiao");
            stConceptionMap.put("能源金属", "nengYuanJinShu");
            stConceptionMap.put("互联网服务", "huLianWangFuWu");
            stConceptionMap.put("通信设备", "tongXinSheBei");
            stConceptionMap.put("汽车零部件", "qiCheLingBuJian");
            stConceptionMap.put("汽车整车", "qiCheZhengChe");
            stConceptionMap.put("专用设备", "zhuanYongSheBei");
            stConceptionMap.put("工程机械", "gongChengJiXie");
            stConceptionMap.put("塑料制品", "suLiaoZhiPin");
            stConceptionMap.put("船舶制造", "chuanBoZhiZao");

            stConceptionMap.put("橡胶制品", "xiangJiaoZhiPin");
            stConceptionMap.put("电网设备", "dianWangSheBei");
            stConceptionMap.put("仪器仪表", "yiQiYiBiao");
            stConceptionMap.put("计算机设备", "jiSuanJiSheBei");
            stConceptionMap.put("软件开发", "ruanJianKaiFa");
            stConceptionMap.put("光伏设备", "guangFuSheBei");
            stConceptionMap.put("游戏", "youXi");
            stConceptionMap.put("电子元件", "dianZiYuanJian");
            stConceptionMap.put("电机", "dianJi");
            stConceptionMap.put("电源设备", "dianYuanSheBei");

            stConceptionMap.put("半导体", "banDaoTi");
            stConceptionMap.put("航天航空", "hangTianHangKong");
            stConceptionMap.put("光学光电子", "guangXueGuangDianZi");
            stConceptionMap.put("消费电子", "xiaoFeiDianZi");
            stConceptionMap.put("电池", "dianChi");
            stConceptionMap.put("风电设备", "fengDianSheBei");
        }

        /**
         * 科技
         */
        {
            stConceptionMap.put("固态电池", "xinNengYuan_guTaiDianChi");
            stConceptionMap.put("刀片电池", "xinNengYuan_daoPianDianChi");
            //数字经济
            stConceptionMap.put("电子身份证", "shuZi_dianZiShenFenZheng");
            stConceptionMap.put("数据安全", "shuZi_shuJuAnQuan");
            stConceptionMap.put("智慧政务", "shuZi_zhiHuiZhengWu");
            stConceptionMap.put("电子车牌", "shuZi_dianZiChePai");
            stConceptionMap.put("数字货币", "shuZi_shuZiHuoBi");
            stConceptionMap.put("国资云概念", "shuZi_guoZiYun");
            stConceptionMap.put("eSIM", "shuZi_esim");
            stConceptionMap.put("华为昇腾", "shuZi_huaWeiShengTeng");
            stConceptionMap.put("数字经济", "shuZi_shuZiJingJi");
            stConceptionMap.put("ETC", "shuZi_etc");
            stConceptionMap.put("东数西算", "shuZi_dongShuXiSuan");

            stConceptionMap.put("航母概念", "junGong_hangMu");
            stConceptionMap.put("海工装备", "junGong_haiGongZhuangBei");
            //科技-汽车
            stConceptionMap.put("激光雷达", "qiChe_jiGuangLeiDa");
            stConceptionMap.put("胎压监测", "qiChe_taiYaJianCe");
            stConceptionMap.put("华为汽车", "qiChe_huaWeiQiChe");
            stConceptionMap.put("特斯拉", "qiChe_teSiLa");
            stConceptionMap.put("磁悬浮概念", "qiChe_ciXuanFu");
            //
            stConceptionMap.put("HIT电池", "guangFu_hit");

            stConceptionMap.put("盐湖提锂", "xinNengYuan_yanHuTiLi");
            stConceptionMap.put("NFT概念", "chuanMei_nft");
            stConceptionMap.put("广电", "chuanMei_guangDian");
            stConceptionMap.put("职业教育", "chuanMei_jiaoYu");
            stConceptionMap.put("虚拟数字人", "chuanMei_xunNiShuZiRen");
            //游戏
            stConceptionMap.put("云游戏", "youXi_yunYouXi");
            stConceptionMap.put("电子竞技", "youXi_dianZiJingJi");
            stConceptionMap.put("手游概念", "youXi_shouYou");
            stConceptionMap.put("网络游戏", "youXi_wangLuoYouXi");
            //科技-工业
            stConceptionMap.put("工业母机", "gongYe_gongYeMuJi");
            stConceptionMap.put("工业4.0", "gongYe_gongYe40");
            stConceptionMap.put("人脑工程", "zhiNeng_renNaoGongCheng");

            stConceptionMap.put("绿色电力", "dianLi_lvSeDianLi");
            stConceptionMap.put("虚拟电厂", "dianLi_xuNiDianChang");
            stConceptionMap.put("发电机概念", "dianLi_faDianJi");
            stConceptionMap.put("抽水蓄能", "dianLi_chouShuiXuNeng");

            stConceptionMap.put("光刻胶", "xinPian_guangKeJiao");
            stConceptionMap.put("中芯概念", "xinPian_zhongXin");
            stConceptionMap.put("汽车芯片", "xinPian_qiCheXinPian");
            stConceptionMap.put("PVDF概念", "xinPian_pvdf");
            stConceptionMap.put("IGBT概念", "xinPian_igbt");

            stConceptionMap.put("氟化工", "huaGong_foHuaGong");
            stConceptionMap.put("钛白粉", "huaGong_taiBaiFen");
            stConceptionMap.put("有机硅", "huaGong_youJiGui");
        }

        /**
         * 消费
         */
        {
            stConceptionMap.put("C2M概念", "lingShou_c2m");
            stConceptionMap.put("退税商店", "lingShou_tuiShuiShangDian");
            stConceptionMap.put("新零售", "lingShou_xinLingShou");

            stConceptionMap.put("在线旅游", "lvYou_zaiXianLvYou");
            stConceptionMap.put("免税概念", "lvYou_mianShui");
            stConceptionMap.put("航空机场", "lvYou_hangKongJiChang");


            stConceptionMap.put("快递概念", "wuLiu_kuaiDi");
            stConceptionMap.put("统一大市场", "wuLiu_tongYiDaShiChang");
            stConceptionMap.put("RCEP概念", "wuLiu_rcep");
            stConceptionMap.put("海洋经济", "wuLiu_haiYangJingJi");
            stConceptionMap.put("中俄贸易概念", "wuLiu_zhongEMaoYi");
            stConceptionMap.put("进口博览", "wuLiu_jinKouBoLan");

            stConceptionMap.put("调味品概念", "shiPin_tiaoWeiPin");
            stConceptionMap.put("代糖概念", "shiPin_daiTang");
            stConceptionMap.put("超级品牌", "shiPin_chaoJiPinPai");
            stConceptionMap.put("预制菜概念", "shiPin_yuZhiCai");
            stConceptionMap.put("社区团购", "shiPin_sheQuTuanGou");
            stConceptionMap.put("乳业", "shiPin_ruYe");
            stConceptionMap.put("人造肉", "shiPin_renZaoRou");

            stConceptionMap.put("白酒", "jiuShui_baiJiu");
            stConceptionMap.put("啤酒概念", "jiuShui_piJiu");

            stConceptionMap.put("草甘膦", "nongYe_caoGanLin");
            stConceptionMap.put("磷化工", "huaGong_linHuaGong");
            stConceptionMap.put("化工原料", "huaGong_huaGongYuanLiao");
            stConceptionMap.put("猪肉概念", "nongYe_zhuRou");
            stConceptionMap.put("鸡肉概念", "shiPin_jiRou");
            stConceptionMap.put("水产养殖", "nongYe_shuiChanYangZhi");
            stConceptionMap.put("转基因", "nongYe_zhuanJiYin");
            stConceptionMap.put("生态农业", "nongYe_shengTaiNongYe");

            stConceptionMap.put("蝗虫防治", "nongYe_huangChongFangZhi");
            stConceptionMap.put("农业种植", "nongYe_nongYeZhongZhi");
        }


        /**
         * 医疗
         */
        {
            stConceptionMap.put("独家药品", "zhongYao_duJiaYaoPin");
            stConceptionMap.put("长寿药", "zhongYao_changShouYao");
            stConceptionMap.put("流感", "zhongYao_liuGan");
            stConceptionMap.put("幽门螺杆菌概念", "zhongYao_youMenLuoXuanGanJun");
            //新冠
            stConceptionMap.put("新冠药物", "xinGuan_xinGuanYaoWu");
            stConceptionMap.put("新冠检测", "xinGuan_xinGuanJianCe");
            stConceptionMap.put("体外诊断", "xinGuan_tiWaiZhenDuan");

            stConceptionMap.put("CAR-T细胞疗法", "chuangXinYao_cart");
            stConceptionMap.put("青蒿素", "chuangXinYao_qingGaoSu");
            //制药
            stConceptionMap.put("肝素概念", "zhiYao_ganSuGaiNian");
            stConceptionMap.put("肝炎概念", "zhiYao_ganYan");
            stConceptionMap.put("地塞米松", "zhiYao_diSaiMiSong");
            stConceptionMap.put("阿兹海默", "zhiYao_aZiHaiMo");
            stConceptionMap.put("基因测序", "zhiYao_jiYinCeXu");
            stConceptionMap.put("维生素", "zhiYao_weiShengSu");
            //创新药
            stConceptionMap.put("CRO ", "chuangXinYao_cro");

            stConceptionMap.put("疫苗冷链", "zhongYao_yiMiaoLengLian");
            stConceptionMap.put("毛发医疗", "yiMei_maoFaYiLiao");
        }


        stConceptionMap.put("黄金概念", "guiJinShu_huangJin");
        stConceptionMap.put("培育钻石", "guiJinShu_peiYuZuanShi");
        stConceptionMap.put("券商概念", "quanShangGaiNian");

        stConceptionMap.put("租售同权", "fangDiChan_zuShouTongQuan");
        stConceptionMap.put("海绵城市", "fangDiChan_haiMianChengShi");
        stConceptionMap.put("装配建筑", "fangDiChan_zhuangPeiJianZhu");
        stConceptionMap.put("REITs概念", "fangDiChan_reits");
        stConceptionMap.put("地下管网", "fangDiChan_diXiaGuanWang");
        stConceptionMap.put("工程机械概念", "fangDiChan_gongChengJiXie");
        stConceptionMap.put("民爆概念", "fangDiChan_minBao");
        stConceptionMap.put("银行 ", "yinHang");

        stConceptionMap.put("基本金属", "jinShu_jiBenJinShu");
        stConceptionMap.put("氢能源", "qiTi_qingNengYuan");
        stConceptionMap.put("氦气概念", "qiTi_haiQi");
        stConceptionMap.put("低碳冶金", "gangTie_diTanYeJin");
        stConceptionMap.put("煤化工", "meiTan_meiHuaGong");
        stConceptionMap.put("稀缺资源", "meiTan_xiQueZiYuan");
    }
}
