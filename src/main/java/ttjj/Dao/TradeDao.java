package ttjj.Dao;

import dto.FundTrade;

import java.util.List;

/**
 * 交易服务
 */
public interface TradeDao {
    List<FundTrade> findFundTrade(String fundCode);
}
