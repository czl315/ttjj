package ttjj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.RankStockCommpanyDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.CondStLikeConception;
import ttjj.dto.DateCond;
import ttjj.dto.RankBizDataDiff;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static utils.Content.*;

/**
 * @author chenzhilong
 * @date 2022/1/10
 */
public class StockService {

    /**
     * 检查股票:状态、是否主板股票、市值限定
     *
     * @param entity
     * @param limitMarketValue
     * @return
     */
    public static boolean checkIsMainStockLimit(RankStockCommpanyDb entity, BigDecimal limitMarketValue) {
        String zqdm = entity.getF12();
        String zqmc = entity.getF14();
        if (entity == null) {
            System.out.println("实体信息为null，不更新db：");
            return false;
        }
        if (StringUtils.isBlank(zqdm)) {
            System.out.println("实体信息异常，不更新db：" + JSON.toJSONString(entity));
            return false;
        }

        //只更新主板板块的价格
        if (entity.getF139() != DB_RANK_BIZ_F139_BK_MAIN) {
//                    System.out.println("均线价格暂不更新（非主板）！" + JSON.toJSONString(entity));
            return false;
        }
        //  市值限定,100亿以下不更新
        if (entity.getF20() != null && entity.getF20().compareTo(limitMarketValue) < 0) {
//                    System.out.println("均线价格暂不更新（100亿以下）！" + JSON.toJSONString(entity));
            return false;
        }

        // 股票状态过滤：退市、退市整理、未上市、st
        Long stStatus = entity.getF148();
        if (DB_RANK_BIZ_F148_STOCK_STATUS_DELISTED == stStatus || DB_RANK_BIZ_F148_STOCK_STATUS_DELISTING == stStatus) {
//                    System.out.println("均线价格暂不更新（退市、退市整理）！" + JSON.toJSONString(entity));
            return false;
        }
        if (DB_RANK_BIZ_F148_STOCK_STATUS_UNLISTED == stStatus) {
//                    System.out.println("均线价格暂不更新（未上市）！" + JSON.toJSONString(entity));
            return false;
        }
        if (DB_RANK_BIZ_F148_STOCK_STATUS_SUSPENSION == stStatus) {
//                    System.out.println("均线价格暂不更新（暂停上市）！" + JSON.toJSONString(entity));
            return false;
        }
        if (DB_RANK_BIZ_F148_STOCK_STATUS_ST == stStatus) {
//                    System.out.println("均线价格暂不更新（ST股）！" + JSON.toJSONString(entity));
            return false;
        }

        return true;
    }

    /**
     * 查询股票列表-根据概念
     *
     * @param date
     * @param conceptions
     * @param board
     * @param mvMin
     * @return
     */
    public static List<RankStockCommpanyDb> listlikeConception(String date, String conceptions, Long board, BigDecimal mvMin) {
        //        String conceptions = "注射器概念";//科技-芯片：汽车芯片,IGBT概念,中芯概念,第三代半导体,PVDF概念,光刻胶,半导体概念,
//        String conceptions = "毛发医疗";//医疗-医美：毛发医疗,医疗美容,


//        String conceptions = "HIT电池";//科技-光伏: HIT电池,光伏建筑一体化      ,太阳能        ["太阳能"];股票个数：168;
//        String conceptions = "煤化工";//资源-煤炭：煤化工,稀缺资源,
//        String conceptions = "油价相关";//资源-石油：可燃冰,页岩气,油气设服,天然气,油价相关
//        String conceptions = "退税商店";//消费-零售:免税概念,退税商店,新零售,C2M概念,抖音小店
//        String conceptions = "白酒";//消费-酒:白酒,啤酒概念

//        String conceptions = "券商概念";//金融-券商:券商概念,互联金融,参股期货

//        String conceptions = "医废处理";//医疗-制药：维生素,地塞米松,疫苗冷链,阿兹海默,基因测序

//        String conceptions = "低碳冶金";//资源-钢铁：低碳冶金,基本金属,稀土永磁,钛白粉
//        String conceptions = "氦气概念";//资源-气体：氢能源,氦气概念,工业气体
//        String conceptions = "航母概念";//科技-军工: 航母概念,海工装备,军民融合,大飞机,通用航空,天基互联,航天概念,空间站概念,北斗导航,
//        String conceptions = "云游戏";//科技-传媒：云游戏,手游概念,电子竞技,网络游戏,
//        String conceptions = "地热能";//科技-电力:抽水蓄能,虚拟电厂,特高压,绿色电力,风能,
//        String conceptions = "磁悬浮概念";//科技-汽车: 激光雷达,胎压监测,华为汽车,特斯拉
//        String conceptions = "工业母机";//科技-工业: 工业母机,工业4.0
//        String conceptions = "培育钻石";//消费-贵金属: 培育钻石,黄金概念,
//        String conceptions = "人脑工程";//科技-智能: 人脑工程
//        String conceptions = "银行 ";//金融-银行:银行,互联金融
//        String conceptions = "体育产业";//消费-体育：体育产业,
//        String conceptions = "职业教育";//科技-教育:职业教育
//        String conceptions = "上海自贸";//：上海自贸
//        String conceptions = "辅助生殖";//辅助生殖,婴童概念,托育服务
//        String conceptions = "杭州亚运会";//最新概念：土壤修复,智慧灯杆,净水概念,杭州亚运会
        CondStLikeConception conditionLikeConception = new CondStLikeConception();
        conditionLikeConception.setDate(date);
        String[] conceptionStrs = conceptions.split(",");
        List<String> conpetionList = Arrays.asList(conceptionStrs);
        conditionLikeConception.setConpetionList(conpetionList);
        conditionLikeConception.setF139(board);
        conditionLikeConception.setF20(mvMin);
        List<RankStockCommpanyDb> stListLikeConception = RankStockCommpanyDao.findListLikeConception(conditionLikeConception);
//        System.out.println("概念：" + JSON.toJSONString(conpetionList) + ";" + "股票个数：" + stListLikeConception.size() + ";");
        return stListLikeConception;
    }

    /**
     * 查询列表-根据板块
     *
     * @param boardName
     * @param date
     * @param board
     * @param mvMin
     * @return
     */
    public static List<RankStockCommpanyDb> findListByCondition(String boardName, String date, Long board, BigDecimal mvMin) {
        RankStockCommpanyDb condition = new RankStockCommpanyDb();
        condition.setDate(date);
        condition.setF139(board);
        condition.setF20(mvMin);
        condition.setType_name(boardName);
        return RankStockCommpanyDao.findListByCondition(condition);
    }

    /**
     * 查询昨日主题排名
     *
     * @param endCount
     */
    public static List<RankBizDataDiff> listBiz(int endCount) {
        //http://28.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112408110589206747254_1616379873172&pn=1&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:90+t:2+f:!50&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222&_=1616379873199
        long curTime = System.currentTimeMillis();
        StringBuffer urlParam = new StringBuffer();
//        String url = "http://api.fund.eastmoney.com/ztjj/GetZTJJList";
//        urlParam.append("callback=jQuery311015658602123786958_1591838943711&callbackname=fundData");
//        urlParam.append("&st="+findDateType);//查询类型
//        urlParam.append("&_=1614523183291");
        String url = "http://28.push2.eastmoney.com/api/qt/clist/get";
        urlParam.append("cb=jQuery112408110589206747254_" + curTime +
                "&pn=1" +//页数
                "&pz=" + endCount +//每页数量
                "&po=1" +//pageorder:页面排序：0-正序；1-倒序
                "&np=1" +
                "&ut=bd1d9ddb04089700cf9c27f6f7426281" +
                "&fltt=2" +//浮点数精度
                "&invt=3" +//显示格式：-；0.0
                "&fid=f3" +//排序字段
                "&fs=m:90+t:2+f:!50" +
                "&fields=" +
                "f1,f2,f3,f4,f5,f6,f7,f8,f9," +
                "f10,f11,f12,f13,f14,f15,f16,f17,f18,f19," +
                "f20,f21,f22,f23,f24,f25,f26,f27,f28,f29," +
                "f30,f31,f32,f33,f34,f35,f36,f37,f38,f39," +
                "f60,f61,f62,f63,f64,f65,f66,f67,f68,f69," +
                "f70,f71,f72,f73,f74,f75,f76,f77,f78,f79," +
                "f80,f81,f82,f83,f84,f85,f86,f87,f88,f89," +
                "f90,f91,f92,f93,f94,f95,f96,f97,f98,f99," +
                "f100,f101,f102,f103,f104,f105,f106,f107,f108,f109" + "," +
                "f110,f111,f112,f113,f114,f115,f116,f117,f118,f119" + "," +
                "f120,f121,f122,f123,f124,f125,f126,f127,f128,f129" + "," +
                "f130,f131,f132,f133,f134,f135,f136,f137,f138,f139" + "," +
                "f140,f141,f142,f143,f144,f145,f146,f147,f148,f149" + "," +
                "f150,f151,f152,f153,f154,f155,f156,f157,f158,f159" + "," +
                "f160,f161,f162,f163,f164,f165,f166,f167,f168,f169" + "," +
                "f170,f171,f172,f173,f174,f175,f176,f177,f178,f179" + "," +
                "f180,f181,f182,f183,f184,f185,f186,f187,f188,f189" + "," +
                "f190,f191,f192,f193,f194,f195,f196,f197,f198,f199" + "," +
                "f200,f201,f202,f203,f204,f205,f206,f207,f208,f209" + "," +
                "f210,f211,f212,f213,f214,f215,f216,f217,f218,f219" + "," +
                "f220,f221,f222,f223,f224,f225,f226,f227,f228,f229" +
                "&_=" + curTime);
        String rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);
        /**
         * 如果返回异常，n次重试
         */
        for (int i = 0; i < 10; i++) {
            if (StringUtils.isBlank(rs)) {
                rs = HttpUtil.sendGet(url, urlParam.toString(), "");
            } else {
                break;
            }
        }

        if (rs.startsWith("jQuery")) {
            rs = rs.substring(rs.indexOf("{"));
        }
        if (rs.endsWith(");")) {
            rs = rs.substring(0, rs.length() - 2);
        }
//        System.out.println(rs);//返回结果
        JSONObject rsJsonObj = JSONObject.parseObject(rs);
        for (int i = 0; i < 10; i++) {
            if (rsJsonObj == null || !rsJsonObj.containsKey("data")) {
                System.out.println("查询数据异常，重新查询" + JSON.toJSONString(rsJsonObj));
                rs = HttpUtil.sendGet(url, urlParam.toString(), "");
                rsJsonObj = JSONObject.parseObject(rs);
            } else {
                break;
            }
        }

        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
        List<RankBizDataDiff> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankBizDataDiff.class);
//        for (RankBizDataDiff row : rankBizDataDiffList) {
//            System.out.println(JSON.toJSON(row));//每个行业一行数据
//        }
        return rankBizDataDiffList;
    }

    /**
     * 获取均线区间
     *
     * @param netMap
     * @return
     */
    public static BigDecimal handlerMaArea(Map<String, BigDecimal> netMap) {
        BigDecimal curPriceArea = null;
        BigDecimal curPrice = netMap.get(keyRsNetClose);
        BigDecimal minPrice = netMap.get(keyRsMin);
        BigDecimal maxPrice = netMap.get(keyRsMax);
        if (curPrice != null && minPrice != null && maxPrice != null) {
            curPriceArea = curPrice.subtract(minPrice).divide(maxPrice.subtract(minPrice), 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return curPriceArea;
    }

    /**
     * 查询指定日期之后交易日期列表
     *
     * @param date 指定日期
     * @param days 限定返回数量
     * @return 日期列表
     */
    public static List<String> findListDateAfter(String date, int days) {
        return RankStockCommpanyDao.findListDateAfter(new DateCond(date, days));
    }

    /**
     * 查询指定日期之前交易日期列表
     * @param date 指定日期
     * @param days 限定返回数量
     * @return 日期列表
     */
    public static List<String> findListDateBefore(String date, int days) {
        return RankStockCommpanyDao.findListDateBefore(new DateCond(date, days));
    }


}
