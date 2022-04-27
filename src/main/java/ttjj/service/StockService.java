package ttjj.service;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.RankStockCommpanyDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.CondStLikeConception;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

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

        // 股票状态
        if (DB_RANK_BIZ_F148_STOCK_STATUS_DELISTED == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（退市）！" + JSON.toJSONString(entity));
            return false;
        }
        if (DB_RANK_BIZ_F148_STOCK_STATUS_UNLISTED == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（未上市）！" + JSON.toJSONString(entity));
            return false;
        }
        if (DB_RANK_BIZ_F148_STOCK_STATUS_SUSPENSION == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（暂停上市）！" + JSON.toJSONString(entity));
            return false;
        }
        if (DB_RANK_BIZ_F148_STOCK_STATUS_ST == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（ST股）！" + JSON.toJSONString(entity));
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


        //        String conceptions = "青蒿素";//医疗-创新药：CRO ,青蒿素,CAR-T细胞疗法
//        String conceptions = "化工原料";//资源-化工: 磷化工,钛白粉,氟化工,有机硅,化工原料,
//        String conceptions = "稀缺资源";//资源-煤炭：煤化工,稀缺资源,
//        String conceptions = "低碳冶金";//资源-钢铁：低碳冶金,基本金属,稀土永磁,钛白粉
//        String conceptions = "氦气概念";//资源-气体：氢能源,氦气概念,工业气体
//        String conceptions = "航母概念";//科技-军工: 航母概念,海工装备,军民融合,大飞机,通用航空,天基互联,航天概念,空间站概念,北斗导航,
//        String conceptions = "云游戏";//科技-传媒：云游戏,手游概念,电子竞技,网络游戏,
//        String conceptions = "地热能";//科技-电力:抽水蓄能,虚拟电厂,特高压,绿色电力,风能,
//        String conceptions = "磁悬浮概念";//科技-汽车: 激光雷达,胎压监测,华为汽车,特斯拉
//        String conceptions = "工业母机";//科技-工业: 工业母机,工业4.0
//        String conceptions = "培育钻石";//消费-贵金属: 培育钻石,黄金概念,
//        String conceptions = "HIT电池";//科技-光伏: HIT电池,光伏建筑一体化      ,太阳能        ["太阳能"];股票个数：168;
//        String conceptions = "人脑工程";//科技-智能: 人脑工程
//        String conceptions = "银行 ";//金融-银行:银行,互联金融
//        String conceptions = "体育产业";//消费-体育：中超概念,体育产业,
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

}
