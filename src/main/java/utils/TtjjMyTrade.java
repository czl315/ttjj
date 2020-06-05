package utils;

import java.math.BigDecimal;

public class TtjjMyTrade {

    public static void main(String[] args) {
        String str = "";
        {
            str = "<tbody>\n" +
                    "\n" +
                    "\n" +
                    "                    <tr class=\"\" data-count=\"5\">\n" +
                    "                        <td class=\"text-left relative\">\n" +
                    "                            <span>2020-05-13</span><br><span style=\"color:#939290\">12:43:30</span>\n" +
                    "                            \n" +
                    "                        </td>\n" +
                    "                        <td class=\"text-left\"><span><a class=\"lk\" href=\"http://fund.eastmoney.com/160633.html\" target=\"_blank\">鹏华证券分级</a></span><br><span>160633</span></td>\n" +
                    "                        <td>活期宝转入</td>\n" +
                    "                            <!--申请数-->\n" +
                    "                            <td class=\"text-right\"><span class=\"red fw-bold mr5\">200.00</span>元</td>\n" +
                    "                            <!--确认数-->\n" +
                    "                            <td class=\"text-right\"><span class=\"red fw-bold mr5\">209.61</span>份</td>\n" +
                    "\n" +
                    "                        <td class=\"checkna text-left\"><span>工商银行 | 5364</span></td>\n" +
                    "                        <td class=\"\">成功</td>\n" +
                    "\n" +
                    "                            <td><a target=\"_blank\" href=\"/Query/Detail?id=387d8c05c0854b4dae6f384c9ff03fcd&amp;businType=22&amp;traceNo=387d8c05c0854b4dae6f384c9ff03fcd\" class=\"lk\">详情</a></td>\n" +
                    "\n" +
                    "                    </tr>\n" +
                    "                    <tr class=\"even\">\n" +
                    "                        <td class=\"text-left relative\">\n" +
                    "                            <span>2020-04-20</span><br><span style=\"color:#939290\">13:41:07</span>\n" +
                    "                            \n" +
                    "                        </td>\n" +
                    "                        <td class=\"text-left\"><span><a class=\"lk\" href=\"http://fund.eastmoney.com/160633.html\" target=\"_blank\">鹏华证券分级</a></span><br><span>160633</span></td>\n" +
                    "                        <td>活期宝转入</td>\n" +
                    "                            <!--申请数-->\n" +
                    "                            <td class=\"text-right\"><span class=\"red fw-bold mr5\">500.00</span>元</td>\n" +
                    "                            <!--确认数-->\n" +
                    "                            <td class=\"text-right\"><span class=\"red fw-bold mr5\">524.03</span>份</td>\n" +
                    "                        <td class=\"text-left\"><span>工商银行 | 5364</span></td>\n" +
                    "                        <td class=\"\">成功</td>\n" +
                    "                                <td><a target=\"_blank\" href=\"/Query/Detail?id=13705ae35e304771b796120bc2e2ae05&amp;businType=22&amp;traceNo=13705ae35e304771b796120bc2e2ae05\" class=\"lk\">详情</a></td>\n" +
                    "\n" +
                    "                    </tr>\n" +
                    "                    <tr class=\"\">\n" +
                    "                        <td class=\"text-left relative\">\n" +
                    "                            <span>2020-04-17</span><br><span style=\"color:#939290\">14:41:48</span>\n" +
                    "                            \n" +
                    "                        </td>\n" +
                    "                        <td class=\"text-left\"><span><a class=\"lk\" href=\"http://fund.eastmoney.com/160633.html\" target=\"_blank\">鹏华证券分级</a></span><br><span>160633</span></td>\n" +
                    "                        <td>活期宝转入</td>\n" +
                    "                            <!--申请数-->\n" +
                    "                            <td class=\"text-right\"><span class=\"red fw-bold mr5\">500.00</span>元</td>\n" +
                    "                            <!--确认数-->\n" +
                    "                            <td class=\"text-right\"><span class=\"red fw-bold mr5\">526.79</span>份</td>\n" +
                    "                        <td class=\"text-left\"><span>工商银行 | 5364</span></td>\n" +
                    "                        <td class=\"\">成功</td>\n" +
                    "                                <td><a target=\"_blank\" href=\"/Query/Detail?id=3980375722dc48b89f8d7b40537b87c2&amp;businType=22&amp;traceNo=3980375722dc48b89f8d7b40537b87c2\" class=\"lk\">详情</a></td>\n" +
                    "\n" +
                    "                    </tr>\n" +
                    "                    <tr class=\"even\">\n" +
                    "                        <td class=\"text-left relative\">\n" +
                    "                            <span>2020-04-15</span><br><span style=\"color:#939290\">14:22:10</span>\n" +
                    "                            \n" +
                    "                        </td>\n" +
                    "                        <td class=\"text-left\"><span><a class=\"lk\" href=\"http://fund.eastmoney.com/160633.html\" target=\"_blank\">鹏华证券分级</a></span><br><span>160633</span></td>\n" +
                    "                        <td>活期宝转入</td>\n" +
                    "                            <!--申请数-->\n" +
                    "                            <td class=\"text-right\"><span class=\"red fw-bold mr5\">1000.00</span>元</td>\n" +
                    "                            <!--确认数-->\n" +
                    "                            <td class=\"text-right\"><span class=\"red fw-bold mr5\">1061.42</span>份</td>\n" +
                    "                        <td class=\"text-left\"><span>工商银行 | 5364</span></td>\n" +
                    "                        <td class=\"\">成功</td>\n" +
                    "                                <td><a target=\"_blank\" href=\"/Query/Detail?id=a1de015bc8074be8b92363d5114cf108&amp;businType=22&amp;traceNo=a1de015bc8074be8b92363d5114cf108\" class=\"lk\">详情</a></td>\n" +
                    "\n" +
                    "                    </tr>\n" +
                    "                    <tr class=\"\">\n" +
                    "                        <td class=\"text-left relative\">\n" +
                    "                            <span>2020-03-09</span><br><span style=\"color:#939290\">12:01:48</span>\n" +
                    "                            \n" +
                    "                        </td>\n" +
                    "                        <td class=\"text-left\"><span><a class=\"lk\" href=\"http://fund.eastmoney.com/160633.html\" target=\"_blank\">鹏华证券分级</a></span><br><span>160633</span></td>\n" +
                    "                        <td>银行卡支付买入</td>\n" +
                    "                            <!--申请数-->\n" +
                    "                            <td class=\"text-right\"><span class=\"red fw-bold mr5\">100.00</span>元</td>\n" +
                    "                            <!--确认数-->\n" +
                    "                            <td class=\"text-right\"><span class=\"red fw-bold mr5\">97.73</span>份</td>\n" +
                    "                        <td class=\"text-left\"><span>工商银行 | 5364</span></td>\n" +
                    "                        <td class=\"\">成功</td>\n" +
                    "                                <td><a target=\"_blank\" href=\"/Query/Detail?id=9d44c2e29fd547928795f5d7f4168b70&amp;businType=22&amp;traceNo=\" class=\"lk\">详情</a></td>\n" +
                    "\n" +
                    "                    </tr>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "<!--加载分页-->\n" +
                    "</tbody>";

        }

        String[] myTrades = str.split("\n");
//        String showType = "tradeDate";//日期 时间
//        String showType="tradeAmt";//交易金额
//        String showType="tradeShare";//交易份额
//        String showType="serviceAmt";//交易份额
        Double serviceRate=0.12;//服务费
        String showType="net";//净值

        BigDecimal tradeAmt = new BigDecimal(0);
        BigDecimal serviceAmt = new BigDecimal(0);
        BigDecimal tradeShare = new BigDecimal(0);

        for (String myTradeRow : myTrades) {
            //日期 时间

            if ("tradeDate".equals(showType)) {
                if (myTradeRow.contains("<span>2020")) {
//                System.out.println(myTradeRow);
                    String tradeDate = myTradeRow.substring(myTradeRow.indexOf("<span>") + 6, myTradeRow.indexOf("<span>") + 16);
                    System.out.print(tradeDate);
                    String tradeTime = myTradeRow.substring(myTradeRow.indexOf("color:#939290\">") + 15, myTradeRow.indexOf("color:#939290\">") + 23);
                    System.out.println(" " + tradeTime);
                }
            }

            if ("tradeAmt".equals(showType)) {
                //交易金额
                if (myTradeRow.contains("red fw-bold mr5\">") && myTradeRow.contains("元")) {
                    String tradeAmtStr = myTradeRow.substring(myTradeRow.indexOf("red fw-bold mr5\">") + 17, myTradeRow.indexOf("</span>元"));
                    System.out.println(tradeAmtStr);
                }
            }

            if ("tradeShare".equals(showType)) {
                //交易份额
                if (myTradeRow.contains("red fw-bold mr5\">") && myTradeRow.contains("份")) {
                    String tradeShareStr = myTradeRow.substring(myTradeRow.indexOf("red fw-bold mr5\">") + 17, myTradeRow.indexOf("</span>份"));
                    System.out.println(tradeShareStr);
                }
            }

            if ("serviceAmt".equals(showType)) {
                //服务费
                if (myTradeRow.contains("red fw-bold mr5\">") && myTradeRow.contains("元")) {
                    String tradeAmtStr = myTradeRow.substring(myTradeRow.indexOf("red fw-bold mr5\">") + 17, myTradeRow.indexOf("</span>元"));
//                    System.out.println(tradeAmtStr);
                    BigDecimal serviceAmtStr = new BigDecimal(tradeAmtStr).multiply(new BigDecimal(serviceRate)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    System.out.println(serviceAmtStr);
                }
            }

            if ("net".equals(showType)) {

                //交易金额
                if (myTradeRow.contains("red fw-bold mr5\">") && myTradeRow.contains("元")) {
                    String tradeAmtStr = myTradeRow.substring(myTradeRow.indexOf("red fw-bold mr5\">") + 17, myTradeRow.indexOf("</span>元"));
//                    System.out.println(tradeAmt);
                    tradeAmt = new BigDecimal(tradeAmtStr);
                    serviceAmt = new BigDecimal(tradeAmtStr).multiply(new BigDecimal(serviceRate)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
//                    System.out.println(serviceAmt);
                }
                //交易份额
                if (myTradeRow.contains("red fw-bold mr5\">") && myTradeRow.contains("份")) {
                    String tradeShareStr = myTradeRow.substring(myTradeRow.indexOf("red fw-bold mr5\">") + 17, myTradeRow.indexOf("</span>份"));
//                    System.out.println(tradeShareStr);
                    tradeShare = new BigDecimal(tradeShareStr);

//                    System.out.println(tradeAmt);
//                    System.out.println(serviceAmt);
//                    System.out.println(tradeShare);
                    BigDecimal net = (tradeAmt.subtract(serviceAmt)).divide(tradeShare,4,BigDecimal.ROUND_HALF_UP);
                    System.out.println(net);
                }

            }

        }
    }
}
