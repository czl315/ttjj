package ttjj.histroy;

public class StringUtil_007136 {

    public static void main(String[] args) {
        String name = "广发中证100ETF联接C(007136)";
        String canShare = "95.38";
        String BUY_COST = "100";
        String FIRST_NET_DATA = "2020-03-11";
        String TRADE_ID = "43";
        String FD_ID = "39";
        String SOURCE = "3";

        String backString = "<tbody>" +
                "                 " +
                "                  <tr class=\"\">" +
                "                      <td>2020-03-13</td>" +
                "                      <td class=\"tor bold\">1.0052" +
                "                            " +
                "                      </td>" +
                "" +
                "                      " +
                "                          <td class=\"tor bold\">1.0052</td>" +
                "                      <td class=\"tor bold grn\">-1.42%</td>" +
                "                      " +
                "                          " +
                "                        <td>开放申购</td>" +
                "                      <td>开放赎回</td>" +
                "                      <td class=\"red unbold\"></td>" +
                "                  </tr>" +
                "                 " +
                "                  <tr class=\"\">" +
                "                      <td>2020-03-12</td>" +
                "                      <td class=\"tor bold\">1.0197" +
                "                            " +
                "                      </td>" +
                "" +
                "                      " +
                "                          <td class=\"tor bold\">1.0197</td>" +
                "                      <td class=\"tor bold grn\">-1.57%</td>" +
                "                      " +
                "                          " +
                "                        <td>开放申购</td>" +
                "                      <td>开放赎回</td>" +
                "                      <td class=\"red unbold\"></td>" +
                "                  </tr>" +
                "                 " +
                "                  <tr class=\"\">" +
                "                      <td>2020-03-11</td>" +
                "                      <td class=\"tor bold\">1.0360" +
                "                            " +
                "                      </td>" +
                "" +
                "                      " +
                "                          <td class=\"tor bold\">1.0360</td>" +
                "                      <td class=\"tor bold grn\">-1.18%</td>" +
                "                      " +
                "                          " +
                "                        <td>开放申购</td>" +
                "                      <td>开放赎回</td>" +
                "                      <td class=\"red unbold\"></td>" +
                "                  </tr>" +
                "                " +
                "            </tbody>";

        String[] str = backString.split("</td>");
        for (int i = 0; i < str.length; i++) {
//			 System.out.println(str[i]);
            String tempRowAllStr = str[i];
//					 System.out.println(tempRowAllStr);

            if (tempRowAllStr.contains("<td>2020-")) {
//					System.out.println(tempRowAllStr);
                String date = tempRowAllStr.replace("                  </tr>                                   <tr class=\"\">                      <td>", "");
                date = date.replace("                                   <tr class=\"\">                      <td>", "");
                date = date.replace("<tbody>", "");
                date = date.replace(" ", "");
                System.out.print(" ");
                System.out.print("INSERT INTO `bank19`.`ol_fund_earn` ( `FD_NAME`, `LASTEST_NET_DATA`, `LASTEST_NET`, `LASTEST_ADD_NET`, `DAY_ADD_RATE`, `TODAY_EARN_AMT`, `HOLD_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `HOLD_EARN_RATE_DAY`, `HOLD_EARN_RATE_MONTH`, `HOLD_EARN_RATE_YEAR`, `remark`, `remark_in`, `remark_out`, `CAN_SHARE`, `BUY_COST`, `UP_MSG`, `DOWN_MSG`, `UP_DOWN_DAYS`, `HOLD_DAYS`, `FIRST_NET_DATA`, `TRADE_ID`, `FD_ID`, `DATE_FLAG`, `SOURCE`, `CREATE_TIME`, `UPDATE_TIME`) ");
                System.out.print("VALUES (");
                System.out.print("'" + name + "',");
                System.out.print("'" + date + "',");
            }
            if (tempRowAllStr.contains("<td class=\"tor bold\">")) {
//					System.out.println(tempRowAllStr);
                String date = tempRowAllStr.replace("<td class=\"tor bold\">", "");
                date = date.replace(" ", "");
                System.out.print(" ");
                System.out.print("'" + date + "',");
            }
            if (tempRowAllStr.contains("<td class=\"tor bold grn\">") || tempRowAllStr.contains("<td class=\"tor bold red\">") || tempRowAllStr.contains("<td class=\"tor bold bck\">")) {
                String date = "";
                date = tempRowAllStr.replace("<td class=\"tor bold grn\">", "");
                date = date.replace("<td class=\"tor bold red\">", "");
                date = date.replace("<td class=\"tor bold bck\">", "");
                date = date.replace(" ", "");
                date = date.replace("%", "");
                System.out.print(" ");
                System.out.print(date);
                System.out.println(",'0', '0', '0', '0', '0', '0', '0', '', '', '', '" + canShare + "', '" + BUY_COST + "', '', '', '', '0', '" + FIRST_NET_DATA + "', '" + TRADE_ID + "', '" + FD_ID + "', '', '" + SOURCE + "', NOW(), NOW());");
            }


//				System.out.println(tempRowBlue);
        }
    }

}