package ttjj.dao;

import ttjj.dto.FundTrade;
import ttjj.dto.StockTrade;
import ttjj.dto.StockTradeToday;

import java.util.List;

/**
 * 交易服务-股票
 */
public interface TradeStockDao {
    /**
     *
     * @param cookie
     * @param startDate
     * @param endDate
     * @param validatekey
     * @return
     */
    List<StockTrade> findMyStockTrade(String cookie, String startDate, String endDate, String validatekey);
}
