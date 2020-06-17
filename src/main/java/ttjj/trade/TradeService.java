package ttjj.trade;

import dto.FundTrade;

import java.util.List;

/**
 * 交易服务
 */
public interface TradeService {
    List<FundTrade> findFundTrade(String fundCode);
}
