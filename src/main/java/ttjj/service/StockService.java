package ttjj.service;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import ttjj.db.RankStockCommpanyDb;

import java.math.BigDecimal;

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
        if (entity.getF139() != DB_RANK_BIZ_F139_BAN_KUAI) {
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

}
