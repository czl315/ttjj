package ttjj.Dao.impl;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import ttjj.dto.FundTrade;
import ttjj.Dao.TradeDao;
import utils.HttpUtil;
import work.NewSeasonJingTie;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 交易服务
 */
public class TradeDaoImpl implements TradeDao {
    //    private final static Logger logger = Logger.getLogger(TradeDaoImpl.class);

    public List<FundTrade> findFundTrade(String fundCode,String cookie) {
        List<FundTrade> fundTradeList = new ArrayList<FundTrade>();

        //硬编码方式
//        int fundCodeInt = Integer.valueOf("1" + fundCode);
//        switch (fundCodeInt) {
//            case 1000656:
//                fundTradeList = handler000656();
//                break;
//        }

        //ttjj接口查询方式
        fundTradeList = findMyTrade(cookie, fundCode);

        return fundTradeList;
    }

    List<FundTrade> handler000656() {
        List<FundTrade> fundTradeList = new ArrayList<FundTrade>();
        FundTrade fundTrade = new FundTrade();
        fundTrade = new FundTrade(1, "000656|前海开源沪深300指数", "2020-03-12 13:47:28",
                "388.34", "1.286", "500", "0.6", "买入成功");
        fundTradeList.add(fundTrade);
        fundTrade = new FundTrade(2, "000656|前海开源沪深300指数", "2020-03-12 13:47:28",
                "160.06", "1.2480", "200.00", "0.24", "买入成功");
        fundTradeList.add(fundTrade);
        return fundTradeList;
    }

    /**
     * 从ttjj查询我的交易
     *
     * @param fundCode
     * @return
     */
    List<FundTrade> invokeTtjjMyTrade(String fundCode) {
        List<FundTrade> fundTradeList = new ArrayList<FundTrade>();
        FundTrade fundTrade = new FundTrade();
        fundTrade = new FundTrade(1, "000656|前海开源沪深300指数", "2020-03-12 13:47:28",
                "388.34", "1.286", "500", "0.6", "买入成功");
        fundTradeList.add(fundTrade);
        fundTrade = new FundTrade(2, "000656|前海开源沪深300指数", "2020-03-12 13:47:28",
                "160.06", "1.2480", "200.00", "0.24", "买入成功");
        fundTradeList.add(fundTrade);
        return fundTradeList;
    }

    /**
     * 查询
     *
     * @param cookie
     */
    private static List<FundTrade> findMyTrade(String cookie, String fundCode) {
        String url = "https://query.1234567.com.cn/Query/DelegateList";
        StringBuffer urlParam = new StringBuffer();
        urlParam.append("DataType=1&StartDate=2020-01-01&EndDate=2020-12-31");
//        urlParam.append("DataType=1&StartDate=2020-06-01&EndDate=2020-06-20");
//        urlParam.append("DataType=1&StartDate=2020-05-01&EndDate=2020-05-31");
//        urlParam.append("DataType=1&StartDate=2020-04-01&EndDate=2020-04-30");
//        urlParam.append("DataType=1&StartDate=2020-03-01&EndDate=2020-03-31");
//        urlParam.append("DataType=1&StartDate=2020-02-01&EndDate=2020-02-29");
//        urlParam.append("DataType=1&StartDate=2020-01-01&EndDate=2020-01-31");
        urlParam.append("&BusType=0&Statu=0&Account=&FundType=0");
        urlParam.append("&PageSize=100");
        urlParam.append("&PageIndex=1&Container=tb_delegate");
        urlParam.append("&FundCode=" + fundCode);
        urlParam.append("&IsHistory=false&callback=undefined");

        //        System.out.println(rs);
//        System.out.println("请求url:"+url+JSON.toJSONString(urlParam));
        String rs = HttpUtil.sendGet(url, urlParam.toString(), cookie);
//        System.out.println("myTradeRs:"+rs);
        List<FundTrade> fundTradeList = formatTradeShow(rs, cookie);
//        System.out.println("fundTradeList:" + JSON.toJSONString(fundTradeList));
        return fundTradeList;
    }

    /**
     * 格式化显示
     *
     * @param rs
     * @param cookie
     */
    private static List<FundTrade> formatTradeShow(String rs, String cookie) {
        List<FundTrade> fundTradeList = new ArrayList<FundTrade>();
        String dateTime = "";
        String time = "";
        String fundName = "";
        String fundCode = "";
        String amt = "";
        String shareCount = "";
        String detailUrl = "";
        String confirmDate = "";
        String[] rsTrs = rs.split("<tr");
        for (String rsTr : rsTrs) {
            FundTrade fundTrade = new FundTrade();
//            System.out.println("每行：<tr " + rsTr);
            String[] rsTds = rsTr.split("<td");
            for (String rsTd : rsTds) {
//            System.out.println("每列：<td " + rsTd);
                // 日期
                if (rsTd.contains("<span>20")) {
//                    System.out.println("每列：<td "+rsTd);
                    dateTime = rsTd.substring(rsTd.indexOf("<span>2020") + 6, rsTd.indexOf("<span>2020") + 16);
                    time = rsTd.substring(rsTd.indexOf("<span style=\"color:#939290\">") + 28, rsTd.indexOf("<span style=\"color:#939290\">") + 36);
                    dateTime = dateTime + " " + time;
//                    System.out.println("交易发起时间:" + dateTime);
                    fundTrade.setTradeTime(dateTime);
                }
                //名称  编码
                if (rsTd.contains("target=\"_blank\">")) {
//                    System.out.println("每列："+rsTd);
                    String[] array = rsTd.split("<span>");
                    //<td class="text-left"><span><a class="lk" href="http://fund.eastmoney.com/003003.html" target="_blank">华夏现金增利货币A/E</a></span><br /><span>003003</span></td>                        <td>红利再投资</td>                            <!--申请数-->
                    if (array.length > 2) {
                        //                        System.out.println("array[2]:"+array[2]);
                        fundCode = array[2].substring(0, 6);
//                    System.out.print("fundCode:" + fundCode + "|");
//                        System.out.println("array[1]:"+array[1]);
                        fundName = fundCode + "|" + array[1].substring(array[1].indexOf(">") + 1, array[1].indexOf("</a>"));
                        fundTrade.setFundInfo(fundName);
//                    System.out.println(fundName);
                    }
                }

                //金额
                // <td class="text-right"><span class='red fw-bold mr5'>200.00</span>元</td>                            <!--确认数-->
                // class="text-right">--</td>                            <!--确认数-->
                if (rsTd.contains("确认数")) {
//                System.out.println("确认数:"+rsTd);
                    String[] array = rsTd.split("class=\"text-right\">");
                    if (array.length > 0) {
                        for (String str : array) {
                            if (str.contains("元")) {
                                amt = str.substring(str.indexOf("<span class='red fw-bold mr5'>") + 30, str.indexOf("</span>元</td>"));
//                            System.out.println("amt:" + amt);
                                fundTrade.setOrderAmt(new BigDecimal(amt));
                            }
                        }
                    }
                }
                //<td class="text-right"><span class='red fw-bold mr5'>213.42</span>份</td>
                if (rsTd.contains("</span>份</td>")) {
                    System.out.println("份数:" + rsTd);
                    shareCount = rsTd.substring(rsTd.indexOf("<span class='red fw-bold mr5'>") + 30, rsTd.indexOf("</span>份</td> "));
//                System.out.println("shareCount:" + shareCount);
                    fundTrade.setConfirmShare(new BigDecimal(shareCount));
                }
                //href="/Query/Detail?id=dc84aad8f91c434496a9da31269e2849&businType=22&traceNo=dc84aad8f91c434496a9da31269e2849" class="lk">详情</a></td>
                if (rsTd.contains("详情</a></td>")) {
                    System.out.println("详情:" + rsTd);
                    detailUrl = rsTd.substring(rsTd.indexOf("href=\"") + 6, rsTd.indexOf("\" class=\"lk\">详情</a></td>"));
                    //detailUrl:/Query/Detail?id=dc84aad8f91c434496a9da31269e2849&businType=22&traceNo=dc84aad8f91c434496a9da31269e2849
                    detailUrl = "https://query.1234567.com.cn/" + detailUrl;
//                    System.out.println("detailUrl:" + detailUrl);
                    if (!detailUrl.endsWith("=")) {//=结尾代表货币基金不查询
                        String rsDetail = HttpUtil.sendGet(detailUrl, "".toString(), cookie);
//                    System.out.println("rsDetail:" + rsDetail);
                        if (rsDetail.contains("<h3>确认信息</h3>")) {
                            String tradeConfirmInfo = rsDetail.substring(rsDetail.indexOf("<h3>确认信息</h3>"), rsDetail.indexOf("交易说明"));
//                        System.out.println("tradeConfirmInfo:" + tradeConfirmInfo);
                            String tradeConfirmInfoTbody = tradeConfirmInfo.substring(tradeConfirmInfo.indexOf("<tbody><tr>"), tradeConfirmInfo.indexOf("</tbody>"));
//                        System.out.println("tradeConfirmInfoTbody:" + tradeConfirmInfoTbody);
                            String[] array = tradeConfirmInfoTbody.split("<td");
                            //确认信息
                            for (String confirmField : array) {
//                            System.out.println("confirmField:"+confirmField);
                                // 日期
                                if (confirmField.contains("2020")) {
                                    confirmDate = confirmField.substring(1);
                                    confirmDate = confirmDate.replace("</td>", "");
//                                System.out.println("confirmDate:"+confirmDate);
                                    fundTrade.setConfirmNetData(confirmDate);
                                }
                                // 状态
                                if (confirmField.contains("申购确认")) {
                                    fundTrade.setOrderStatus("买入成功");
                                }
//                            //
//                            if (confirmField.contains("http://fund.eastmoney.com/")) {
//                                String confirmDate = confirmField.substring(confirmField.indexOf("target=\"_blank\">")+16);
//                                confirmDate = confirmDate.replace("</td>","" );
//                                System.out.println("confirmDate:"+confirmDate);
//                            }
//                            // 确认净值(元)
//                            if (confirmField.contains(".")) {
//                                String confirmNet = confirmField.substring(1);
//                                confirmNet = confirmNet.replace("</td>","");
//                                System.out.println("confirmNet:"+confirmNet);
//                            }
                            }
//                        String[] arrayConfirm = tradeConfirmInfoTbody.split("<td>");
//                        for (String str : arrayConfirm) {
//                            System.out.println();
//                        }
                            tradeConfirmInfoTbody = tradeConfirmInfoTbody.substring(tradeConfirmInfoTbody.indexOf("<td class=\"success\">成功</td>") + 27);
                            String confirmNet = tradeConfirmInfoTbody.substring(tradeConfirmInfoTbody.indexOf("<td>") + 4, tradeConfirmInfoTbody.indexOf("</td>"));
//                        System.out.println("confirmNet："+confirmNet);
                            fundTrade.setConfirmNet(new BigDecimal(confirmNet));

                            tradeConfirmInfoTbody = tradeConfirmInfoTbody.substring(tradeConfirmInfoTbody.indexOf("</td>") + 5);
//                        System.out.println("tradeConfirmInfoTbodyTemp:"+tradeConfirmInfoTbody);
                            String confirmAmt = tradeConfirmInfoTbody.substring(tradeConfirmInfoTbody.indexOf("<td>") + 4, tradeConfirmInfoTbody.indexOf("</td>", 2));
//                        System.out.println("confirmAmt："+confirmAmt);
//                            fundTrade.setC
                            tradeConfirmInfoTbody = tradeConfirmInfoTbody.substring(tradeConfirmInfoTbody.indexOf("</td>") + 5);
                            String confirmShare = tradeConfirmInfoTbody.substring(tradeConfirmInfoTbody.indexOf("<td>") + 4, tradeConfirmInfoTbody.indexOf("</td>", 2));
//                        System.out.println("confirmShare："+confirmShare);
                            fundTrade.setConfirmShare(new BigDecimal(confirmShare));
                            tradeConfirmInfoTbody = tradeConfirmInfoTbody.substring(tradeConfirmInfoTbody.indexOf("</td>") + 5);
                            String confirmSxf = tradeConfirmInfoTbody.substring(tradeConfirmInfoTbody.indexOf("<td>") + 4, tradeConfirmInfoTbody.indexOf("</td>", 2));
//                        System.out.println("confirmSxf："+confirmSxf);
                            fundTrade.setServerCharge(new BigDecimal(confirmSxf));

                            //INSERT INTO `bank19`.`ol_fund_trade`(`ID`, `FD_ID`, `FD_INFO`, `TYPE`, `TRADE_TIME`, `ORDER_STATUS`, `CONFIRM_SHARE`, `CONFIRM_NET`, `ORDER_AMT`, `STATUS`, `TRADE_CODE`, `TRADE_CODE_BUY`, `TRADE_CODE_REDEM`, `ORDER_CODE`, `CONFIRM_AMT`, `REDEM_AMT`, `EARN_AMT`, `CONFIRM_NET_DATA`, `SERVER_CHARGE`, `PROCESS_TIME`, `EARN_TIME`, `TRADE_ACCT`, `REDEM_STATUS`, `REDEM_SHARE`, `REDEM_TIME`, `REDEM_ACCT_TIME`, `SOURCE`, `FD_CODE`, `CREATE_TIME`, `UPDATE_TIME`) VALUES (105, '32', '鹏华证券分级(160633)', '申购', '2020-05-13 12:43:30', '支付成功', 209.61, 0.953, 200, '确认成功', '', '', '0', '', 100, 0, 0, '2020-03-11 13:29:18', 0.24, '2020-03-11 13:29:18', '2020-03-11 13:29:18', '天天基金', '0', 0, '3000-01-01 00:00:00', '3000-01-01 00:00:00', '天天基金', '', '2020-03-14 17:40:26', '2020-06-02 15:45:27');
                            System.out.println("INSERT INTO `bank19`.`ol_fund_trade`(" +
                                    " `FD_ID`, `FD_INFO`, `TYPE`, `TRADE_TIME`, `ORDER_STATUS`, `CONFIRM_SHARE`, `CONFIRM_NET`, `ORDER_AMT`, `STATUS`, `TRADE_CODE`, `TRADE_CODE_BUY`, `TRADE_CODE_REDEM`, `ORDER_CODE`, `CONFIRM_AMT`, `REDEM_AMT`, `EARN_AMT`, `CONFIRM_NET_DATA`, `SERVER_CHARGE`, `PROCESS_TIME`, `EARN_TIME`, `TRADE_ACCT`, `REDEM_STATUS`, `REDEM_SHARE`, `REDEM_TIME`, `REDEM_ACCT_TIME`, `SOURCE`, `FD_CODE`, `CREATE_TIME`, `UPDATE_TIME`" +
                                    ") VALUES (" +
                                    " '', '" + fundName + "', '申购', '" + dateTime + "', '买入成功', " + confirmShare + ", " + confirmNet + ", " + confirmAmt + ", " +
                                    "'确认成功', '', '', '0', '', " + confirmAmt + ", 0, 0, '" + confirmDate + "', " + confirmSxf + ", '3000-01-01 00:00:00', '3000-01-01 00:00:00', '天天基金', '0', 0, '3000-01-01 00:00:00', '3000-01-01 00:00:00', '3', '', now(), now()" +
                                    ");");
                        }
//                    System.out.println();
                    }
                    fundTradeList.add(fundTrade);
                }
//            System.out.println();
//            System.out.println("fundTrade:" + JSON.toJSONString(fundTrade));
            }
        }
        return fundTradeList;
    }

    /**
     * @param args args
     */
    public static void main(String[] args) {
        String cookie = "cp_token=7f8f0f3224b24194a70d453b65f6338f; st_si=21393218770879; st_pvi=97169704610474; st_sp=2020-06-02%2010%3A08%3A40; st_inirUrl=https%3A%2F%2Ffund.eastmoney.com%2F; st_sn=1; st_psi=20200624102608139-119085303933-2162940118; st_asi=delete; fund_trade_cn=Yc77PDg/+Wj5iTXCqVAA6bxp4OMd9jjngcRyW/+F8i0zV9OOuhjeqbjRblMfwOtJqTWURKkvVfQwtZQrAcFblP0Pn2jNxqPztf1WgiztdNFUZXlCcfg=; fund_trade_name=YgcrYk+jAXjrUklMAm9Ff+xrg6o0g72mSG2Dbf5VfgPFykcfzedN8rlb/M0IYR518y54T2vu; fund_trade_visitor=YFcgjlp0dXH+3UcNZq9Pg39qEuI0Mw/G0/76bNepPfBLykIDDfITzgl7fx90upJ1kXFLlrSp; fund_trade_risk=YoM9MwKvdXlp7EYt7e91M9qcfW60tYQw+FHxbltK8hCCUkRgD1R2p5l6mLEYSuJ1LTTd1wMx; fund_trade_gps=2; VipLevel=0; TradeLoginToken=00f403747d9847ef9017e344a947d6ec; UTOKEN=Yc77PDg/+Wj5iTXCqVAA6bxp4OMd9jjngcRyW/+F8i0zV9OOuhjeqbjRblMfwOtJqTWURUkRGp2JtiCSwQlllLA5W2+ROreNxb1XjvLpcHc2z4/eBGg=; LToken=d7f8eb0ea7864795879a3d666fec23ab; fund_trade_trackid=gXoYRgS4Y2W/A9QxqdSXCXc6ZguMOE0x0pGC2ZrZhW6NDXcn18gz+y0p+QpC6G9BZ1MHZAVwRAPu+khVKNw+eg==";
//        System.out.println("查询开始：");
        List<FundTrade> rs = findMyTrade(cookie, "000656");
//        String rs = findMyTrade(cookie, "");
        System.out.println("rs:" + JSON.toJSONString(rs));

//        formatTradeShow(rs, cookie);
    }
}
