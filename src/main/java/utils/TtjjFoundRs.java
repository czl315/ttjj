package utils;

import java.math.BigDecimal;

public class TtjjFoundRs {
    public static String insertRs(String name, String canShare, String BUY_COST, String FIRST_NET_DATA, String TRADE_ID, String FD_ID, String SOURCE, String backString) {
        String rs = "";
        String[] str = backString.split("</td>");
        String lastestNet = "0";
        String dayAddRate = "";//每日增长率
        BigDecimal yesterdayNet= new BigDecimal(0);
        for (int i = 0; i < str.length; i++) {
//			 System.out.print(str[i]);
            String tempRowAllStr = str[i];
//					 System.out.print(tempRowAllStr);

            if (tempRowAllStr.contains("<td>2020-")) {
//					System.out.print(tempRowAllStr);
                String lastestNetData = tempRowAllStr.replace("                  </tr>                                   <tr class=\"\">                      <td>", "");
                lastestNetData = lastestNetData.replace("                                   <tr class=\"\">                      <td>", "");
                lastestNetData = lastestNetData.replace("<tbody>", "");
                lastestNetData = lastestNetData.replace(" ", "");
                System.out.print("INSERT INTO `bank19`.`ol_fund_earn` ( `FD_NAME`, `LASTEST_NET_DATA`, `LASTEST_NET`, `LASTEST_ADD_NET`, `DAY_ADD_RATE`, `TODAY_EARN_AMT`, `HOLD_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `HOLD_EARN_RATE_DAY`, `HOLD_EARN_RATE_MONTH`, `HOLD_EARN_RATE_YEAR`, `remark`, `remark_in`, `remark_out`, `CAN_SHARE`, `BUY_COST`, `UP_MSG`, `DOWN_MSG`, `UP_DOWN_DAYS`, `HOLD_DAYS`, `FIRST_NET_DATA`, `TRADE_ID`, `FD_ID`, `DATE_FLAG`, `SOURCE`, `CREATE_TIME`, `UPDATE_TIME`) ");
                System.out.print("VALUES ('" + name + "','" + lastestNetData + "',");
            }
            if (tempRowAllStr.contains("<td class=\"tor bold\">")) {
//					System.out.print(tempRowAllStr);
                lastestNet = tempRowAllStr.replace("<td class=\"tor bold\">", "");
                lastestNet = lastestNet.replace(" ", "");
                System.out.print("'" + lastestNet + "',");
            }
            if (tempRowAllStr.contains("<td class=\"tor bold grn\">") || tempRowAllStr.contains("<td class=\"tor bold red\">") || tempRowAllStr.contains("<td class=\"tor bold bck\">")) {
                dayAddRate = tempRowAllStr.replace("<td class=\"tor bold grn\">", "");
                dayAddRate = dayAddRate.replace("<td class=\"tor bold red\">", "");
                dayAddRate = dayAddRate.replace("<td class=\"tor bold bck\">", "");
                dayAddRate = dayAddRate.replace(" ", "");
                dayAddRate = dayAddRate.replace("%", "");
                System.out.print(dayAddRate);//日增长率
                BigDecimal todayEarnAmt = new BigDecimal(lastestNet).multiply(new BigDecimal(canShare)).multiply(new BigDecimal(dayAddRate))
                        .divide(new BigDecimal(100));
                System.out.print(",'" + todayEarnAmt.setScale(2, BigDecimal.ROUND_HALF_UP) + "',");//每日收益金额
                System.out.println(" '0', '0', '0', '0', '0', '0', '', '', '', '" + canShare + "', '" + BUY_COST + "', '', '', '', '0', '" + FIRST_NET_DATA + "', '" + TRADE_ID + "', '" + FD_ID + "', '', '" + SOURCE + "', NOW(), NOW());");

//                System.out.println("todayEarnAmt1:"+todayEarnAmt);
//                //记录昨日净值、日增长率、计算今日收益
//                BigDecimal todayEarnAmtComp = yesterdayNet.multiply(new BigDecimal(dayAddRate)).multiply(new BigDecimal(canShare))
//                        .divide(new BigDecimal(100));
//                System.out.println("todayEarnAmt2:"+todayEarnAmtComp);
//                yesterdayNet = new BigDecimal(lastestNet);
            }


//				System.out.print(rs);
        }
        return rs;
    }

}
