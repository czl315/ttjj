package jd;

import utils.TtjjFoundRs;

public class Jd_07_004593_广发全指工业ETF联接A {

    public static void main(String[] args) {
        String name = "广发全指工业ETF联接A(004593)";//http://fundf10.eastmoney.com/jjjz_004593.html
        String canShare = "99.93";
        String BUY_COST = "100";
        String FIRST_NET_DATA = "2017-05-21";
        String TRADE_ID = "4";
        String FD_ID = "5";
        String SOURCE = "1";

        String backString = "";
        {
            backString = "<tbody>" +
                    "                 " +
                    "                  <tr class=\"\">" +
                    "                      <td>2020-04-10</td>" +
                    "                      <td class=\"tor bold\">0.7674" +
                    "                            " +
                    "                      </td>" +
                    "" +
                    "                      " +
                    "                          <td class=\"tor bold\">0.7674</td>" +
                    "                      <td class=\"tor bold grn\">-1.46%</td>" +
                    "                      " +
                    "                          " +
                    "                        <td>暂停申购</td>" +
                    "                      <td>开放赎回</td>" +
                    "                      <td class=\"red unbold\"></td>" +
                    "                  </tr>" +
                    "                 " +
                    "                  <tr class=\"\">" +
                    "                      <td>2020-04-09</td>" +
                    "                      <td class=\"tor bold\">0.7788" +
                    "                            " +
                    "                      </td>" +
                    "" +
                    "                      " +
                    "                          <td class=\"tor bold\">0.7788</td>" +
                    "                      <td class=\"tor bold red\">0.19%</td>" +
                    "                      " +
                    "                          " +
                    "                        <td>暂停申购</td>" +
                    "                      <td>开放赎回</td>" +
                    "                      <td class=\"red unbold\"></td>" +
                    "                  </tr>" +
                    "                 " +
                    "                  <tr class=\"\">" +
                    "                      <td>2020-04-08</td>" +
                    "                      <td class=\"tor bold\">0.7773" +
                    "                            " +
                    "                      </td>" +
                    "" +
                    "                      " +
                    "                          <td class=\"tor bold\">0.7773</td>" +
                    "                      <td class=\"tor bold red\">0.05%</td>" +
                    "                      " +
                    "                          " +
                    "                        <td>暂停申购</td>" +
                    "                      <td>开放赎回</td>" +
                    "                      <td class=\"red unbold\"></td>" +
                    "                  </tr>" +
                    "                " +
                    "            </tbody>";
        }
        String insertRs = TtjjFoundRs.insertRs(name,canShare,BUY_COST,FIRST_NET_DATA,TRADE_ID,FD_ID,SOURCE,backString);
    }

}
