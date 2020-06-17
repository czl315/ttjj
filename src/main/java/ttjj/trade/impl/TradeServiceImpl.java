package ttjj.trade.impl;

import dto.FundTrade;
import ttjj.Dao.impl.TradeDaoImpl;
import ttjj.trade.TradeService;

import java.math.BigDecimal;
import java.util.List;

/**
 * 交易服务
 */
public class TradeServiceImpl implements TradeService {
    public List<FundTrade> findFundTrade(String fundCode) {
        BigDecimal shareSum = new BigDecimal(0);
        BigDecimal amtSum = new BigDecimal(0);
        List<FundTrade> fundTradeList = new TradeDaoImpl().findFundTrade(fundCode);
        for (FundTrade fundTrade : fundTradeList) {
            if ("买入成功".equals(fundTrade.getOrderStatus())) {
                shareSum = shareSum.add(fundTrade.getConfirmShare());
                amtSum = amtSum.add(fundTrade.getOrderAmt());
            }
            if ("赎回完成".equals(fundTrade.getOrderStatus())) {
                shareSum = shareSum.subtract(fundTrade.getConfirmShare());
                amtSum = amtSum.subtract(fundTrade.getOrderAmt());
            }
        }
        return fundTradeList;
//        Map rs = new HashMap<String,String>();
//        rs.put("shareSum",shareSum);
//        rs.put("amtSum",amtSum);
    }
}
