package ttjj.Dao.impl;

import dto.FundTrade;
import ttjj.Dao.TradeDao;
import ttjj.trade.TradeService;

import java.util.ArrayList;
import java.util.List;

/**
 * 交易服务
 */
public class TradeDaoImpl implements TradeDao {
    public List<FundTrade> findFundTrade(String fundCode) {
        List<FundTrade> fundTradeList = new ArrayList<FundTrade>();
        int fundCodeInt = Integer.valueOf("1" + fundCode);
        switch (fundCodeInt) {
            case 1000656:
                fundTradeList = handler000656();
                break;
        }
        return fundTradeList;
    }

    List<FundTrade> handler000656() {
        List<FundTrade> fundTradeList = new ArrayList<FundTrade>();
        FundTrade fundTrade = new FundTrade();
        fundTrade = new FundTrade(1, "000656|前海开源沪深300指数", "2020-03-12 13:47:28",
                "388.34","1.286","500","0.6","买入成功");
        fundTradeList.add(fundTrade);
        fundTrade = new FundTrade(2, "000656|前海开源沪深300指数", "2020-03-12 13:47:28",
                "160.06","1.2480","200.00","0.24","买入成功");
        fundTradeList.add(fundTrade);
        return fundTradeList;
    }

    /**
     * 从ttjj查询我的交易
     * @param fundCode
     * @return
     */
    List<FundTrade> invokeTtjjMyTrade(String fundCode) {
        List<FundTrade> fundTradeList = new ArrayList<FundTrade>();
        FundTrade fundTrade = new FundTrade();
        fundTrade = new FundTrade(1, "000656|前海开源沪深300指数", "2020-03-12 13:47:28",
                "388.34","1.286","500","0.6","买入成功");
        fundTradeList.add(fundTrade);
        fundTrade = new FundTrade(2, "000656|前海开源沪深300指数", "2020-03-12 13:47:28",
                "160.06","1.2480","200.00","0.24","买入成功");
        fundTradeList.add(fundTrade);
        return fundTradeList;
    }
}
