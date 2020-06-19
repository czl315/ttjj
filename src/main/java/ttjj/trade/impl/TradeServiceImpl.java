package ttjj.trade.impl;

import dto.FundTrade;
import ttjj.Dao.impl.TradeDaoImpl;
import ttjj.trade.TradeService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Content.*;

/**
 * 交易服务
 */
public class TradeServiceImpl implements TradeService {
    /**
     * 查询指定日期之前的累计份额、成本金额、手续费
     *
     * @param fundCode
     * @return
     */
    public Map<String, String> findFundTrade(String fundCode) {
        Map<String, String> rs = new HashMap<String, String>();
        BigDecimal shareSum = new BigDecimal(0);
        BigDecimal amtSum = new BigDecimal(0);
        BigDecimal serverCharge = new BigDecimal(0);
        String fundName = "";
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
            serverCharge = fundTrade.getServerCharge();
            fundName = fundTrade.getFundInfo();
        }
        rs.put(FUND_NAME, fundName);
        rs.put(FUND_CODE, fundCode);
        rs.put(SHARE_SUM, shareSum.toString());
        rs.put(AMT_SUM, amtSum.toString());
        rs.put(SERVER_CHARGE, serverCharge.toString());
        return rs;
    }
}
