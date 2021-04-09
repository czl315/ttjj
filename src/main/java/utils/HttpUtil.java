package utils;

import com.alibaba.fastjson.JSON;
import ttjj.dto.LsjzDataLsjz;
import ttjj.dto.LsjzPt;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class HttpUtil {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param, String cookie) {
        String result = "";
        BufferedReader in = null;
        String urlNameString = url + "?" + param;
        try {
//            System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");
            connection.setRequestProperty("Cookie", cookie);
            connection.setConnectTimeout(100);
            // 建立实际的连接
            connection.connect();

            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                // System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("/**发送GET请求出现异常！" + urlNameString + e+"**/");
//            throw new RuntimeException();
//            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, byte[] param) {
        OutputStream out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = conn.getOutputStream();
            //out = new PrintWriter(outputStream);
            // 发送请求参数
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param, String cookie) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Cookie", cookie);
            // 建立实际的连接
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 历史净值
     *
     * @param url
     * @param param
     * @return
     */
    public static LsjzPt sendPostTtjjLsjj(String url, byte[] param, Map<String, String> paramMap) {
        LsjzPt lsjzPt = new LsjzPt();
        String headerReferer = "referer";
        String headerRefererValue = "http://fundf10.eastmoney.com/";
        OutputStream out = null;
        BufferedReader in = null;
        String result = "";
        try {
            String lsjzUrl = "http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery183010470500509039282_1603296503156&";
            URL realUrl = new URL(lsjzUrl + url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty(headerReferer, headerRefererValue);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = conn.getOutputStream();
            //out = new PrintWriter(outputStream);
            // 发送请求参数
            out.write(param);
//            System.out.println(out.toString());
//            System.out.println(conn.getURL());
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

            result = result.replace("jQuery183010470500509039282_1603296503156(", "");
            result = result.replace(")", "");
//            System.out.println(result);
            lsjzPt = JSON.parseObject(result, LsjzPt.class);
            List<LsjzDataLsjz> lsjzDataLsjzs = lsjzPt.getData().getLSJZList();
            if (lsjzDataLsjzs != null && lsjzDataLsjzs.size() > 0) {
                Collections.sort(lsjzDataLsjzs, new Comparator<LsjzDataLsjz>() {
                    public int compare(LsjzDataLsjz o1, LsjzDataLsjz o2) {
                        return o1.getFSRQ().compareTo(o2.getFSRQ());
                    }
                });
            }
            return lsjzPt;

        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return lsjzPt;
    }

    /**
     * @param url
     * @param param
     * @return
     */
    public static String sendPostTtjj(String url, byte[] param, Map<String, String> paramMap) {
        String headerReferer = "referer";
        String headerRefererValue = "http://fundf10.eastmoney.com/";
        OutputStream out = null;
        BufferedReader in = null;
        String result = "";
        try {
            String lsjzUrl = "http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18301820868923083503_1594476154019&";
            URL realUrl = new URL(lsjzUrl + url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty(headerReferer, headerRefererValue);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = conn.getOutputStream();
            //out = new PrintWriter(outputStream);
            // 发送请求参数
            out.write(param);
//            System.out.println(out.toString());
//            System.out.println(conn.getURL());
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

            result = result.replace("jQuery18301820868923083503_1594476154019(", "");
            result = result.replace(")", "");
            System.out.println(result);
            LsjzPt lsjzPt = JSON.parseObject(result, LsjzPt.class);
            List<LsjzDataLsjz> lsjzDataLsjzs = lsjzPt.getData().getLSJZList();
            if (lsjzDataLsjzs != null && lsjzDataLsjzs.size() > 0) {
                Collections.sort(lsjzDataLsjzs, new Comparator<LsjzDataLsjz>() {
                    public int compare(LsjzDataLsjz o1, LsjzDataLsjz o2) {
                        return o1.getFSRQ().compareTo(o2.getFSRQ());
                    }
                });
                int firstDay = 0;//第一天标志
                String lastDayNet = "0";//上一日净值
                for (LsjzDataLsjz lsjzDataLsjz : lsjzDataLsjzs) {
                    if (++firstDay == 1) {
                        //第一天只记录上一日净值
                        lastDayNet = lsjzDataLsjz.getDWJZ();
                        continue;
                    }
//                    System.out.println(JSON.toJSONString(lsjzDataLsjz));
                    System.out.print("INSERT INTO `bank19`.`ol_fund_earn` ( `FD_NAME`, `LASTEST_NET_DATA`, `LASTEST_NET`, `LASTEST_ADD_NET`, `DAY_ADD_RATE`, `TODAY_EARN_AMT`, `HOLD_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `HOLD_EARN_RATE_DAY`, `HOLD_EARN_RATE_MONTH`, `HOLD_EARN_RATE_YEAR`, `remark`, `remark_in`, `remark_out`, `CAN_SHARE`, `BUY_COST`, `UP_MSG`, `DOWN_MSG`, `UP_DOWN_DAYS`, `HOLD_DAYS`, `FIRST_NET_DATA`, `TRADE_ID`, `FD_ID`,`FD_TYPE`, `DATE_FLAG`, `SOURCE`, `CREATE_TIME`, `UPDATE_TIME`) ");
                    System.out.print("VALUES ('" + paramMap.get(Content.jjName));
                    System.out.print("','" + lsjzDataLsjz.getFSRQ() + "',");
                    System.out.print("'" + lsjzDataLsjz.getDWJZ() + "',");
                    if (null != lsjzDataLsjz.getLJJZ() && !"".equals(lsjzDataLsjz.getLJJZ())) {
                        System.out.print("'" + lsjzDataLsjz.getLJJZ() + "',");
                    } else {
                        System.out.print("'" + "0" + "',");
                    }
                    //日增长率
                    if (null != lsjzDataLsjz.getJZZZL() && !"".equals(lsjzDataLsjz.getJZZZL())) {
                        System.out.print(lsjzDataLsjz.getJZZZL());
                    } else {
                        System.out.print("0");
                    }
//                    BigDecimal todayEarnAmt = new BigDecimal(lsjzDataLsjz.getDWJZ()).multiply(new BigDecimal(paramMap.get("canShare"))).multiply(new BigDecimal(lsjzDataLsjz.getJZZZL()))
//                            .divide(new BigDecimal(100));
                    BigDecimal todayEarnAmt = new BigDecimal(lsjzDataLsjz.getDWJZ()).subtract(new BigDecimal(lastDayNet)).multiply(new BigDecimal(paramMap.get(Content.canShare) != null ? paramMap.get(Content.canShare) : "0"));
//                    System.out.println(todayEarnAmt.setScale(4, BigDecimal.ROUND_HALF_DOWN));//每日收益金额
                    //手续费
                    String sxf = paramMap.get(Content.SXF);
                    if (sxf != null && !"".equals(sxf)) {
                        todayEarnAmt = todayEarnAmt.subtract(new BigDecimal(sxf));
                    }
                    System.out.print(",'" + todayEarnAmt.setScale(4, BigDecimal.ROUND_HALF_DOWN) + "',");//每日收益金额
                    System.out.print(" '0', '0', '0', '0', '0', '0', '', '', '', '" + paramMap.get(Content.canShare) + "', '" + paramMap.get(Content.BUY_COST) + "', '', '', '', '0', '" + paramMap.get(Content.FIRST_NET_DATA) + "', '" + paramMap.get(Content.TRADE_ID) +
                            "', '" + paramMap.get(Content.FD_ID));
                    System.out.print("', '" + (paramMap.containsKey(Content.FD_TYPE) ? paramMap.get(Content.FD_TYPE) : "") + "', ");
                    System.out.print("'', '"
                            + paramMap.get(Content.SOURCE) + "', NOW(), NOW());");
                    System.out.println();
                    lastDayNet = lsjzDataLsjz.getDWJZ();//记录上一日净值
                }
            }

        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 查询-ttjj-历史净值-最新一条
     *
     * @param url
     * @param param
     * @return
     */
    public static LsjzDataLsjz sendPostTtjjLsjzLastOne(String url, byte[] param, Map<String, String> paramMap) {
        LsjzDataLsjz rs = null;
        String headerReferer = "referer";
        String headerRefererValue = "http://fundf10.eastmoney.com/";
        OutputStream out = null;
        BufferedReader in = null;
        String result = "";
        try {
            String lsjzUrl = "http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18301820868923083503_1594476154019&";
            URL realUrl = new URL(lsjzUrl + url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty(headerReferer, headerRefererValue);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = conn.getOutputStream();
            //out = new PrintWriter(outputStream);
            // 发送请求参数
            out.write(param);
//            System.out.println(out.toString());
//            System.out.println(conn.getURL());
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

            result = result.replace("jQuery18301820868923083503_1594476154019(", "");
            result = result.replace(")", "");
//            System.out.println(result);
            LsjzPt lsjzPt = JSON.parseObject(result, LsjzPt.class);
            if (lsjzPt.getData() == null) {
                return rs;
            }
            List<LsjzDataLsjz> lsjzDataLsjzs = lsjzPt.getData().getLSJZList();
            if (lsjzDataLsjzs != null && lsjzDataLsjzs.size() > 0) {
                rs = lsjzDataLsjzs.get(0);
            }

        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return rs;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGetTtjj(String url, String param, String cookie) {
        String headerReferer = "referer";
        String headerRefererValue = "http://fundf10.eastmoney.com/";
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty(headerReferer, headerRefererValue);//TTJJ
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Cookie", cookie);
            // 建立实际的连接
            connection.connect();

            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                // System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


}