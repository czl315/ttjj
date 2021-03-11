package ttjj.Dao;

import ttjj.dto.FundTrade;
import ttjj.dto.StockTrade;

import java.util.List;

/**
 * 交易服务-股票
 */
public interface TradeStockDao {
    List<StockTrade> findMyStockTrade(String cookie, String fundCode, String startDate, String endDate, String busType, String pageIndex);
}
