package ttjj.trade;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import utils.HttpUtil;

/**
 * 新品季
 */
public class MyTrade {
    private final static Logger logger = Logger.getLogger(MyTrade.class);

    /**
     * @param args args
     */
    public static void main(String[] args) {
//        System.out.println("查询开始：");
        String cookie = "cp_token=817ebcada6714fc1a2666e094afd00d0; st_si=36333761260301; st_pvi=97169704610474; st_sp=2020-06-02%2010%3A08%3A40; st_inirUrl=https%3A%2F%2Ffund.eastmoney.com%2F; st_sn=1; st_psi=20200615135130273-119085303933-1761304033; st_asi=delete; fund_login_qrid=c50a4350bfb0417d93e0196e9b04f319; fund_trade_cn=CTNXleP1p+Q+TZtLKzQQeSwLJnJoPaugZlG/AvFFhrScozGr5n3nUoHrixmkwMDFZVHN5EfxN+CyzCu4RLorfJ6NgiX/TPyNbLSkXJnOX0xwxErGeCI=; fund_trade_name=CnaUdX+nrtfd+OqEjXP1wWoHYSvSAiCAkFesilDwNJXa9f4sDcRCt5fOln9L14KSNFFDNnsd; fund_trade_visitor=CW+appJCOt6J8t7cKrPCEAzVHm7SKBjPW+ZFi/Sh02EfMfD1WTFHy/f92+TslpISuC8+t7C2; fund_trade_risk=CiFuiI+aet35QenP8lPnTMqYVH5S96rBt/9MiXfaccMDgfarrAQ5bNfiCKGTvZASfuJdNvnW; fund_trade_gps=2; VipLevel=0; TradeLoginToken=45c76a5b56db4611b11826eac4765857; UTOKEN=CTNXleP1p+Q+TZtLKzQQeSwLJnJoPaugZlG/AvFFhrScozGr5n3nUoHrixmkwMDFZVHN51f17mSgBMLFHHxWfCoZBNSp/yZi6LSxGGjf025whKrNUrg=; LToken=69b58efd90e849dbb9d95d4957a6a04d; fund_trade_trackid=gArU8ysfxMIAcnD/1Qt5v7AtPmssbUpWWsZPLqqjcoEBCHTn9CV6nR6HhoGT0Cmf5qRN4FeCVqz6xVgXTtcxnw==";
        String rs = findMyTrade(cookie);
//        System.out.println("rs:"+rs);

        formatTradeShow(rs,cookie);
    }

    /**
     * 格式化显示
     *
     * @param rs
     * @param cookie
     */
    private static void formatTradeShow(String rs, String cookie) {
        String date = "";
        String time = "";
        String fundName = "";
        String fundCode = "";
        String amt = "";
        String shareCount = "";
        String detailUrl = "";
        String[] rsTrs = rs.split("<tr class=\"\" data-count=\"94\">");
        System.out.println("每行：");
//            System.out.println("每行：<tr "+rsTr);
        String[] rsTds = rs.split("<td ");
        for (String rsTd : rsTds) {
//            System.out.println("每列：<td " + rsTd);
            // 日期
            if (rsTd.contains("<span>20")) {
//                    System.out.println("每列：<td "+rsTd);
                date = rsTd.substring(rsTd.indexOf("<span>2020") + 6, rsTd.indexOf("<span>2020") + 16);
                time = rsTd.substring(rsTd.indexOf("<span style=\"color:#939290\">") + 28, rsTd.indexOf("<span style=\"color:#939290\">") + 36);
                System.out.println("日期:" + date + " " + time);
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
                    System.out.println(fundName);
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
                            System.out.println("amt:" + amt);
                        }
                    }
                }
            }
            //<td class="text-right"><span class='red fw-bold mr5'>213.42</span>份</td>
            if (rsTd.contains("份")) {
//                System.out.println("确认数:"+rsTd);
                shareCount = rsTd.substring(rsTd.indexOf("<span class='red fw-bold mr5'>") + 30, rsTd.indexOf("</span>份</td> "));
                System.out.println("shareCount:" + shareCount);
            }
            //href="/Query/Detail?id=dc84aad8f91c434496a9da31269e2849&businType=22&traceNo=dc84aad8f91c434496a9da31269e2849" class="lk">详情</a></td>
            if (rsTd.contains("详情")) {
//                System.out.println("确认数:"+rsTd);
                detailUrl = rsTd.substring(rsTd.indexOf("href=\"") + 6, rsTd.indexOf("\" class=\"lk\">详情</a></td>"));
                //detailUrl:/Query/Detail?id=dc84aad8f91c434496a9da31269e2849&businType=22&traceNo=dc84aad8f91c434496a9da31269e2849
                detailUrl = "https://query.1234567.com.cn/"+detailUrl;
                System.out.println("detailUrl:" + detailUrl);
                if(!detailUrl.endsWith("=")){//=结尾代表货币基金不查询
                    String rsDetail = HttpUtil.sendGet(detailUrl, "".toString(), cookie);
//                    System.out.println("rsDetail:" + rsDetail);
                    if(rsDetail.contains("<h3>确认信息</h3>")){
                        String tradeConfirmInfo = rsDetail.substring(rsDetail.indexOf("<h3>确认信息</h3>"),rsDetail.indexOf("交易说明"));
                        System.out.println("tradeConfirmInfo:" + tradeConfirmInfo);
                        String tradeConfirmInfoTbody = tradeConfirmInfo.substring(tradeConfirmInfo.indexOf("<tbody><tr>"),tradeConfirmInfo.indexOf("</tbody>"));
                        System.out.println("tradeConfirmInfoTbody:" + tradeConfirmInfoTbody);
                        String[] array = tradeConfirmInfoTbody.split("<td");
                        for (String confirmField : array) {
//                            System.out.println("confirmField:"+confirmField);
                            // 日期
                            if (confirmField.contains("2020")) {
                                String confirmDate = confirmField.substring(1);
                                confirmDate = confirmDate.replace("</td>","" );
                                System.out.println("confirmDate:"+confirmDate);
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
                        tradeConfirmInfoTbody = tradeConfirmInfoTbody.substring(tradeConfirmInfoTbody.indexOf("<td class=\"success\">成功</td>")+27);
                        String confirmNet = tradeConfirmInfoTbody.substring(tradeConfirmInfoTbody.indexOf("<td>")+4,tradeConfirmInfoTbody.indexOf("</td>"));
                        System.out.println("confirmNet："+confirmNet);

                        tradeConfirmInfoTbody = tradeConfirmInfoTbody.substring(tradeConfirmInfoTbody.indexOf("</td>")+5);
//                        System.out.println("tradeConfirmInfoTbodyTemp:"+tradeConfirmInfoTbody);
                        String confirmAmt = tradeConfirmInfoTbody.substring(tradeConfirmInfoTbody.indexOf("<td>")+4,tradeConfirmInfoTbody.indexOf("</td>",2));
                        System.out.println("confirmAmt："+confirmAmt);
                        tradeConfirmInfoTbody = tradeConfirmInfoTbody.substring(tradeConfirmInfoTbody.indexOf("</td>")+5);
                        String confirmShare = tradeConfirmInfoTbody.substring(tradeConfirmInfoTbody.indexOf("<td>")+4,tradeConfirmInfoTbody.indexOf("</td>",2));
                        System.out.println("confirmShare："+confirmShare);
                        tradeConfirmInfoTbody = tradeConfirmInfoTbody.substring(tradeConfirmInfoTbody.indexOf("</td>")+5);
                        String confirmSxf = tradeConfirmInfoTbody.substring(tradeConfirmInfoTbody.indexOf("<td>")+4,tradeConfirmInfoTbody.indexOf("</td>",2));
                        System.out.println("confirmSxf："+confirmSxf);
                    }
                    System.out.println();
                }

            }
//            System.out.println();
        }
    }

    /**
     * 查询
     *
     * @param cookie
     */
    private static String findMyTrade(String cookie) {
        String url = "https://query.1234567.com.cn/Query/DelegateList";
        StringBuffer urlParam = new StringBuffer();
        urlParam.append("DataType=1&StartDate=2020-05-15&EndDate=2020-06-15&BusType=0&Statu=0&Account=&FundType=0&PageSize=20&PageIndex=1&Container=tb_delegate&FundCode=&IsHistory=false&callback=undefined");

        String rs = HttpUtil.sendGet(url, urlParam.toString(), cookie);
//        System.out.println(rs);
        return rs;
    }
}
