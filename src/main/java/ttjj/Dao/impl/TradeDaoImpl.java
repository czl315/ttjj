package ttjj.Dao.impl;

import org.apache.commons.lang3.StringUtils;
import ttjj.Dao.TradeDao;
import ttjj.dto.FundTrade;
import ttjj.dto.LsjzDataLsjz;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 交易服务
 */
public class TradeDaoImpl implements TradeDao {
    //    private final static Logger logger = Logger.getLogger(TradeDaoImpl.class);

    public List<FundTrade> findFundTrade(String fundCode, String cookie) {
        List<FundTrade> fundTradeList = new ArrayList<FundTrade>();//硬编码方式
//        int fundCodeInt = Integer.valueOf("1" + fundCode);
//        switch (fundCodeInt) {
//            case 1000656:
//                fundTradeList = handler000656();
//                break;
//        }

        //ttjj接口查询方式
        String startDate = "2020-01-01";
        String endDate = "2020-12-31";
        String busType = "";
        fundTradeList = findMyTrade(cookie, fundCode, startDate, endDate, busType);

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
//    private List<FundTrade> findMyTrade(String cookie, String fundCode) {

    public List<FundTrade> findMyTrade(String cookie, String fundCode, String startDate, String endDate, String busType) {
        String url = "https://query.1234567.com.cn/Query/DelegateList";
        StringBuffer urlParam = new StringBuffer();
        urlParam.append("DataType=1");
        urlParam.append("&StartDate=").append(startDate);
        urlParam.append("&EndDate=").append(endDate);
        urlParam.append("&BusType=").append(busType);
        urlParam.append("&Statu=0&Account=&FundType=0");
        urlParam.append("&PageSize=1000");
        urlParam.append("&PageIndex=1");
        urlParam.append("&Container=tb_delegate");
        urlParam.append("&FundCode=" + fundCode);
//        urlParam.append("&IsHistory=true");
        urlParam.append("&IsHistory=false");
        urlParam.append("&callback=undefined");

        //        System.out.println(rs);
//        System.out.println("请求url:"+url+JSON.toJSONString(urlParam));
        String rs = HttpUtil.sendGet(url, urlParam.toString(), cookie);
//        System.out.println("myTradeRs:"+rs);
        List<FundTrade> fundTradeList = formatTradeShow(rs, cookie);
//        System.out.println("fundTradeList:" + JSON.toJSONString(fundTradeList));
        return fundTradeList;
    }

    /**
     * 查询 all
     *
     * @param cookie
     * @param fundCode
     */
    private static List<FundTrade> findMyTradeAll(String cookie, String fundCode) {
        String url = "https://query.1234567.com.cn/Query/DelegateList";
        StringBuffer urlParam = new StringBuffer();
        urlParam.append("DataType=1&StartDate=2020-01-01&EndDate=2020-12-31");
//        urlParam.append("DataType=1&StartDate=2020-06-01&EndDate=2020-06-20");
//        urlParam.append("DataType=1&StartDate=2020-05-01&EndDate=2020-05-31");
//        urlParam.append("DataType=1&StartDate=2020-04-01&EndDate=2020-04-30");
//        urlParam.append("DataType=1&StartDate=2020-03-01&EndDate=2020-03-31");
//        urlParam.append("DataType=1&StartDate=2020-02-01&EndDate=2020-02-29");
//        urlParam.append("DataType=1&StartDate=2020-01-01&EndDate=2020-01-31");
        urlParam.append("&BusType=0");
        urlParam.append("&Statu=0&Account=&FundType=0");
        urlParam.append("&PageSize=1000");
        urlParam.append("&PageIndex=1");
        urlParam.append("&Container=tb_delegate");
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
                    if (fundTrade.getTradeTime() == null) {
                        fundTrade.setTradeTime(dateTime);
                    }
                }
                //名称  编码
                if (rsTd.contains("target=\"_blank\">")) {
//                    System.out.println("每列："+rsTd);
                    String[] array = rsTd.split("<span>");
                    //<td class="text-left"><span><a class="lk" href="http://fund.eastmoney.com/003003.html" target="_blank">华夏现金增利货币A/E</a></span><br /><span>003003</span></td>                        <td>红利再投资</td>                            <!--申请数-->
                    if (array.length > 2) {
                        //                        System.out.println("array[2]:"+array[2]);
                        fundCode = array[2].substring(0, 6);
                        fundTrade.setFundCode(fundCode);
//                    System.out.print("fundCode:" + fundCode + "|");
//                        System.out.println("array[1]:"+array[1]);
                        fundName = fundCode + "|" + array[1].substring(array[1].indexOf(">") + 1, array[1].indexOf("</a>"));
                        fundTrade.setFundInfo(fundName);
//                    System.out.println(fundName);
                    }
                }

                //金额
                // <td class="text-right"><span class='red fw-bold mr5'>200.00</span>元</td>                            <!--确认数-->
                //<td class="text-right"><span class='red fw-bold mr5'>121.67</span>元</td>
                // class="text-right">--</td>                            <!--确认数-->
                if (rsTd.contains("元")) {
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
//                    System.out.println("份数:" + rsTd);
                    shareCount = rsTd.substring(rsTd.indexOf("<span class='red fw-bold mr5'>") + 30, rsTd.indexOf("</span>份</td> "));
//                System.out.println("shareCount:" + shareCount);
                    fundTrade.setConfirmShare(new BigDecimal(shareCount));
                }
                //href="/Query/Detail?id=dc84aad8f91c434496a9da31269e2849&businType=22&traceNo=dc84aad8f91c434496a9da31269e2849" class="lk">详情</a></td>
                if (rsTd.contains("详情</a></td>")) {
//                    System.out.println("详情:" + rsTd);
                    detailUrl = rsTd.substring(rsTd.indexOf("href=\"") + 6, rsTd.indexOf("\" class=\"lk\">详情</a></td>"));
                    //detailUrl:/Query/Detail?id=dc84aad8f91c434496a9da31269e2849&businType=22&traceNo=dc84aad8f91c434496a9da31269e2849
                    detailUrl = "https://query.1234567.com.cn/" + detailUrl;
//                    System.out.println("detailUrl:" + detailUrl);
                    if (!detailUrl.endsWith("=")) {//=结尾代表货币基金不查询
                    }
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
                                //如果已经被设置为赎回，不再更新。（次情况是卖出回活期宝）
                                if (fundTrade.getOrderStatus() == null) {
                                    fundTrade.setOrderStatus("申购");
                                }
                            }
                            if (confirmField.contains("赎回确认")) {
                                fundTrade.setOrderStatus("赎回");
                            }
                            if (confirmField.contains("强行调增")) {
                                fundTrade.setOrderStatus("强行调增");
                            }
                            if (confirmField.contains("红利发放<br>(现金分红)")) {
                                fundTrade.setOrderStatus("现金分红");
                            }
//                            //
//                            if (confirmField.contains("http://fund.eastmoney.com/")) {
//                                String confirmDate = confirmField.substring(confirmField.indexOf("target=\"_blank\">")+16);
//                                confirmDate = confirmDate.replace("</td>","" );
//                                System.out.println("confirmDate:"+confirmDate);
//                            }
                            // 确认净值(元)  净值规则：长度6位，第二位是小数点
//                            if (confirmField.length()==6 && confirmField.contains(".")) {
//                                String confirmNet = confirmField.substring(1);
//                                confirmNet = confirmNet.replace("</td>","");
////                                System.out.println("confirmNet:"+confirmNet);
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

                        //INSERT INTO `bank19`.`ol_fund_trade`(`ID`, `FD_ID`, `FD_INFO`, `TYPE`, `TRADE_TIME`, `ORDER_STATUS`, `CONFIRM_SHARE`, `CONFIRM_NET`, `ORDER_AMT`, `STATUS`, `TRADE_CODE`, `ORDER_CODE`, `CONFIRM_AMT`, `REDEM_AMT`, `EARN_AMT`, `CONFIRM_NET_DATA`, `SERVER_CHARGE`, `REDEM_STATUS`, `REDEM_SHARE`, `REDEM_TIME`, `REDEM_ACCT_TIME`, `SOURCE`, `FD_CODE`, `CREATE_TIME`, `UPDATE_TIME`) VALUES (105, '32', '鹏华证券分级(160633)', '申购', '2020-05-13 12:43:30', '支付成功', 209.61, 0.953, 200, '确认成功', '', '', '0', '', 100, 0, 0, '2020-03-11 13:29:18', 0.24, '2020-03-11 13:29:18', '2020-03-11 13:29:18', '天天基金', '0', 0, '3000-01-01 00:00:00', '3000-01-01 00:00:00', '天天基金', '', '2020-03-14 17:40:26', '2020-06-02 15:45:27');
                        if (fundTrade.getFundInfo() != null && fundTrade.getFundInfo().contains("货币")) {
                            //货币基金不打印
                            continue;
                        }
                        if (fundTrade.getOrderStatus() != null && fundTrade.getOrderStatus().contains("强行调增")) {
                            //不打印-强行调增
                            continue;
                        }
                        if (fundTrade.getOrderStatus() != null && fundTrade.getOrderStatus().contains("现金分红")) {
                            //不打印-现金分红
                            continue;
                        }
                        if (fundTrade.getOrderStatus() != null && (fundTrade.getOrderStatus().contains("申购") || fundTrade.getOrderStatus().contains("赎回"))) {
//                                //打印-申购、赎回
//                                System.out.println("INSERT INTO `bank19`.`ol_fund_trade`(" +
//                                        " `FD_ID`, `FD_INFO`, `TYPE`, `TRADE_TIME`, `ORDER_STATUS`, `CONFIRM_SHARE`, `CONFIRM_NET`, `ORDER_AMT`, `STATUS`, `ORDER_CODE`, `CONFIRM_AMT`, `REDEM_AMT`, `EARN_AMT`, `CONFIRM_NET_DATA`, `SERVER_CHARGE`, `REDEM_STATUS`, `REDEM_SHARE`, `REDEM_TIME`, `REDEM_ACCT_TIME`, `SOURCE`, `FD_CODE`, `CREATE_TIME`, `UPDATE_TIME`" +
//                                        ") VALUES (" +
//                                        " '', '" + fundTrade.getFundInfo() + "', '"+fundTrade.getOrderStatus()+"', '" + dateTime + "', '"+fundTrade.getOrderStatus()+"', " + confirmShare + ", " + confirmNet + ", " + confirmAmt + ", " +
//                                        "'确认成功', '', '', '0', '', " + confirmAmt + ", 0, 0, '" + confirmDate + "', " + confirmSxf + ", '3000-01-01 00:00:00', '3000-01-01 00:00:00', '天天基金', '0', 0, '3000-01-01 00:00:00', '3000-01-01 00:00:00', '3', '', now(), now()" +
//                                        ");");
                        }


                    }
//                    System.out.println();

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
//        String cookie = "FundTradeLoginCard=0; FundTradeLoginTab=0; __guid=168568481.3275105768631237000.1587299075419.1528; st_si=16529934604496; st_asi=delete; b_p_log_key=YqYlmCtMJVbt2nCdsyCq6TbxbtD+GRXUJrxBZEDZITxHIfCgUw8tCrxUk731QiB0nvUsM8TkmchTyB/oeM7g5nSq+YBBzSV3o1LtTI5IkSzzNbvkXT0=; st_pvi=34528644972697; st_sp=2020-03-21%2009%3A52%3A13; st_inirUrl=https%3A%2F%2Flogin.1234567.com.cn%2Flogin; st_sn=2; st_psi=20200626125212882-119085303933-7379745562; cp_token=2ca40dd1c09e4f6c99ad548acef6e639; FundTradeLoginUser=WryU2RGKLMQddvOrt9ie5DH681dVJ7Rs98U+3fkkCPoMMYm+TogQg18eMgwrDAYRCBbS6blW; fund_trade_cn=WOQWkk599pK/DrM33DcU8UfIp1hdiIqkOBDKtTcnjgVcLCFELaR5ciCT33q3rNqfMHS/tnYaLdfbHWm6cxq48Uvcrj2Xgx//HDR5H1pWcbYwG3sOur8=; fund_trade_name=WQ+qNJQRkM2gf0ve7giilJV4jbTVicEdkpYh3wCIEEQaZYpiX5NWK08mI5edsToRSpyAZ+3a; fund_trade_visitor=WZGFRsGAsMwx6GI0b4iGrpXig4GVnUDJkDgn3Wcq5hDS7YYCBDJ7f88D0Q7jDR0Rmf6wuKvi; fund_trade_risk=WGB21Yg1xMyCP00D20iUUjQveZdV0FAjhnlZ38Sw4BK48YjyjP+Qq98bpqDDQTeRLcjQLeub; fund_trade_gps=2; VipLevel=0; TradeLoginToken=0cdd00b4660749efbbb2b1ad5a00a1a2; UTOKEN=WOQWkk599pK/DrM33DcU8UfIp1hdiIqkOBDKtTcnjgVcLCFELaR5ciCT33q3rNqfMHS/tuY+U8CMvNFZKtf18kYRYJD80Zr4yERG9mq9X6yRf4eEJPk=; LToken=919b1cc8f4244369b65efa898ff7ae58; fund_trade_trackid=gSRgeG/HOvMXtvvf2k5YRsWme3vrnWmciDUr/Y5kDpfBbZD9+RhA8YnjkfRSoYT1EaJ8OL/0ktaZzbgM46qQ3g==; monitor_count=14";
        String cookie = "FundTradeLoginCard=0; FundTradeLoginTab=0; __guid=26890232.4298960297175944000.1584177952348.2258; st_si=35064838476839; st_asi=delete; cp_token=fe61c5eb6f3f43e5b035c5b4a628c32c; FundTradeLoginUser=U2CO9sByNlODzXeIbMgSwW/b1Gs895NLtoNfgUFlhWuqSu14/b/ZOb07AhZT2H9N/ZEyPTuq; fund_trade_cn=UFtAHlpn90nA5YlN/VWQYvdG1gJ1gf7zVb9xejK+Qa8Vyzj2WWK42oZlgHyYmG6HFlRYOpuG8zkj+su2A11v0kIqFxq5BdmSGAN9bnJ0GFuqGv0e2Z8=; fund_trade_name=UxqmDYUotlxnXYaLeqgUyYM+lNL8NDA/7YZzgA4FxtI/GuXM3NHn2Q0uEXb71ihNY1Gwg4i5; fund_trade_visitor=UZTBZwxlFl8JmYYX1tg8Xs+qpZn8pz+sEStSgY3xVIjOxusIrLZ0290HX/CyGviNjSFL/ybl; fund_trade_risk=UmYrjk0Opl6xKtsgI8gMVSRKhP+8Q2iDGpNxgqqhh4WN5ub6oHmVh004sHRzdnSNbEIkaI/g; fund_trade_gps=2; VipLevel=0; TradeLoginToken=47f210116f774826abd60c5e1c13a75e; UTOKEN=UFtAHlpn90nA5YlN/VWQYvdG1gJ1gf7zVb9xejK+Qa8Vyzj2WWK42oZlgHyYmG6HFlRYOWuUQXB9AzFD7Zux0XNgecHhXE4tkxNb6sEG1NnFTEKV0ws=; LToken=c78a67f989074af98d28f9ae8a754365; fund_trade_trackid=BU1i3MQO0fA1cBEYweOrDPofj9ha3nq6+KePw+8TxLDtLXHb4mg+QeF3YxHr94zEMRQnhEuFkRXeGLVZRyeOaw==; ASP.NET_SessionId=ywqrj5wu24tms2hxhnr2tmpu; st_pvi=34528644972697; st_sp=2020-03-21%2009%3A52%3A13; st_inirUrl=https%3A%2F%2Flogin.1234567.com.cn%2Flogin; st_sn=2; st_psi=20200803233348155-0-1249590593; monitor_count=13";

//        System.out.println("查询开始：");=
        String fundCode = "";
//        String fundCode = "002207";
        String startDate = "2020-02-31";
        String endDate = "2020-12-31";
//        String busType = "0";//0-全部;1-申购;2-卖出;
        String busType = "1";//0-全部;1-申购;2-卖出;
//        String busType = "2";//0-全部;1-申购;2-赎回;
        List<FundTrade> rs = new TradeDaoImpl().findMyTrade(cookie, fundCode, startDate, endDate, busType);
//        System.out.println("findMyTrade:"+JSON.toJSON(rs));
        for (FundTrade fundTrade : rs) {
            if (fundTrade.getOrderStatus() != null && (fundTrade.getOrderStatus().contains("申购"))) {
                //显示插入数据库语句
                showInsertDb(fundTrade);
                //显示-更新数据库语句
//                showUpdateDb(fundTrade);

            }
            //赎回
            if (fundTrade.getOrderStatus() != null && (fundTrade.getOrderStatus().contains("赎回")) && fundTrade.getOrderAmt()!=null) {
                //打印-赎回-update
                BigDecimal enrnAmtSubServerCharge = fundTrade.getOrderAmt().subtract(fundTrade.getServerCharge());
                System.out.println("UPDATE `ol_fund_trade` " +
                        "SET `TYPE`='申购(赎回)'" +
                        ",`ORDER_AMT`=" + fundTrade.getOrderAmt() + " " +
                        ",`REDEM_TIME`='" + fundTrade.getConfirmNetData() + "' " +
                        ",`REDEM_AMT`=" + fundTrade.getOrderAmt() + " " +
                        ",`SERVER_CHARGE`=" + fundTrade.getServerCharge() + " " +
                        ",`EARN_AMT`=ROUND((" + enrnAmtSubServerCharge + "-`CONFIRM_AMT`) ,2)" +
                        "WHERE  `FD_INFO` = '" + fundTrade.getFundInfo() + "' "
                        + "AND `TYPE` = '申购' "
                        + "AND `CONFIRM_SHARE` = '" + fundTrade.getConfirmShare() + "' " +
                        " LIMIT 1; ");
            }
        }
//        String rs = findMyTrade(cookie, "");
//        System.out.println("rs:" + JSON.toJSONString(rs));

//        formatTradeShow(rs, cookie);
    }

    /**
     * 显示-更新数据库语句-更新-我的交易-最新净值和日期
     * @param fundTrade
     */
    private static void showUpdateDb(FundTrade fundTrade) {
        String lsjzUrl = "fundCode=" + fundTrade.getFundCode() + "&pageIndex=1&pageSize=100&startDate=" + "" + "&endDate=" + "" + "&_=1558194929451";
        byte[] bytes ="".getBytes();
        LsjzDataLsjz lsjzDataLsjz = HttpUtil.sendPostTtjjLsjzLastOne(lsjzUrl, bytes, new HashMap<String, String>());
//        System.out.println("lsjzDataLsjz:"+JSON.toJSON(lsjzDataLsjz));
        //打印-
        if(lsjzDataLsjz==null){
            return;
        }
        System.out.println("UPDATE `ol_fund_trade` " +
                "SET " +
                " `LAST_NET`=" + lsjzDataLsjz.getDWJZ() + " " +
                ",`LAST_DATE`='" + lsjzDataLsjz.getFSRQ() + "' " +
                "WHERE  `FD_INFO` = '" + fundTrade.getFundInfo() + "' "+
                "AND `TYPE` = '申购' "+
                " ;");
    }

    /**
     * 显示插入数据库语句
     * @param fundTrade
     */
    private static void showInsertDb(FundTrade fundTrade) {
        //打印-
        System.out.println("INSERT INTO `bank19`.`ol_fund_trade`(" +
                " `FD_ID`, `FD_INFO`, `TYPE`, `TRADE_TIME`, `ORDER_STATUS`, " +
                " `CONFIRM_SHARE`, `CONFIRM_NET`, `ORDER_AMT`, `STATUS`, " +
                " `ORDER_CODE`, `CONFIRM_AMT`, `REDEM_AMT`," +
                " `EARN_AMT`, `CONFIRM_NET_DATA`, `SERVER_CHARGE`, " +
                " `REDEM_STATUS`, `REDEM_SHARE`, `REDEM_TIME`, `REDEM_ACCT_TIME`," +
                " `SOURCE`, `FD_CODE`, `CREATE_TIME`, `UPDATE_TIME`" +
                ") VALUES (" +
                " '', '" + fundTrade.getFundInfo() + "', '" + fundTrade.getOrderStatus() + "', '" + fundTrade.getTradeTime() + "', '" + fundTrade.getOrderStatus() + "'" +
                ", " + fundTrade.getConfirmShare() + ", " + fundTrade.getConfirmNet() + ", " + fundTrade.getOrderAmt() + ", " + "'确认成功', " +
                "  '', " + fundTrade.getOrderAmt() + ", 0, " +
                " 0, '" + fundTrade.getConfirmNetData() + "', " + fundTrade.getServerCharge() + ", " +
                " '0', 0,  '3000-01-01 00:00:00', '3000-01-01 00:00:00', " +
                " '3', '', now(), now()" +
                ");");
    }
}
