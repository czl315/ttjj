package ttjj.trade;

import java.util.Map;

/**
 * 交易服务
 */
public interface TradeService {
    Map<String, String> findFundTrade(String fundCode,String cookie );
}
