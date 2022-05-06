package ttjj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.BizRankDao;
import ttjj.dao.RankStockCommpanyDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.Kline;
import ttjj.dto.RankBizDataDiff;
import ttjj.dto.StockNotice;
import ttjj.rank.StockDemo;
import utils.DateUtil;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Content.*;

/**
 * 股票公告
 *
 * @author Administrator
 * @date 2021-11-15 21:23
 */
public class NoticeService {
    public static void main(String[] args) {
        String date = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD, 0);
        List<String> filterList = new ArrayList<>();
//        filterList.add("提价");
//        filterList.add("产品价格调整");
        filterList.add("发行股份");
//        filterList.add("价格调整");
//        findStockNotice("002507", filterStr);

        listStockNotice(date, filterList);
    }

    /**
     * 遍历股票
     *
     * @param date
     */
    private static void listStockNotice(String date, List<String> filterList) {
        List<RankBizDataDiff> bkList = StockService.listBiz(NUM_MAX_99);//查询主题排名by时间类型、显示个数
        List<RankStockCommpanyDb> stockList = new ArrayList<>();
        int stBizCountTemp = 0;
        for (RankBizDataDiff banKuai : bkList) {
            String banKuaiCode = banKuai.getF12();
            String banKuaiName = banKuai.getF14();
            stBizCountTemp++;
            stockList.addAll(StockDemo.listRankStockByBiz(NUM_MAX_999, banKuaiCode));
//            System.out.println("-------------------------当前stBizCountTemp：" + stBizCountTemp + "---" + banKuaiName + "---[" + banKuai.getF3() + "]---" + stockList.size());
        }

        for (RankStockCommpanyDb entity : stockList) {
            String stCode = entity.getF12();
            if (entity == null) {
                System.out.println("实体信息为null，不更新db：");
                continue;
            }
            entity.setDate(date);
            if (StringUtils.isBlank(stCode)) {
                System.out.println("实体信息异常，不更新db：" + JSON.toJSONString(entity));
                continue;
            }

            // 股票状态
            if (DB_RANK_BIZ_F148_STOCK_STATUS_DELISTED == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（退市）！" + JSON.toJSONString(entity));
                continue;
            }
            if (DB_RANK_BIZ_F148_STOCK_STATUS_UNLISTED == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（未上市）！" + JSON.toJSONString(entity));
                continue;
            }
            if (DB_RANK_BIZ_F148_STOCK_STATUS_SUSPENSION == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（暂停上市）！" + JSON.toJSONString(entity));
                continue;
            }
            if (DB_RANK_BIZ_F148_STOCK_STATUS_ST == entity.getF148()) {
//                    System.out.println("均线价格暂不更新（ST股）！" + JSON.toJSONString(entity));
                continue;
            }
            //只更新主板板块的价格
            if (entity.getF139() != DB_RANK_BIZ_F139_BK_MAIN) {
//                    System.out.println("均线价格暂不更新（非主板）！" + JSON.toJSONString(entity));
                continue;
            }
            //  市值限定,100亿以下不更新
            if (entity.getF20() != null && entity.getF20().compareTo(new BigDecimal("10000000000")) < 0) {
//                    System.out.println("均线价格暂不更新（100亿以下）！" + JSON.toJSONString(entity));
                continue;
            }

            if (entity.getF139() == DB_RANK_BIZ_F139_BK_MAIN) {
                findStockNotice(stCode, filterList);
            } else {
                System.out.println("非主板不更新！");
            }
        }
    }

    /**
     * 查询股票通知，过滤
     *
     * @param zqdm
     */
    public static String findStockNotice(String zqdm, List<String> filterList) {
        String rs = httpRsStockNotice(zqdm);
//        System.out.println("zqdm:" + zqdm + ",rs:" + rs);
        JSONObject jsonObject = JSON.parseObject(rs);
        JSONObject jsonObjectData = JSON.parseObject(jsonObject.getString("data"));
        String jsonObjectList = jsonObjectData.getString("list");

        if (jsonObjectList == null) {
            System.out.println("通知为空：" + rs);
            return null;
        }

        JSONArray jsonArray = JSON.parseArray(jsonObjectList);
        List<StockNotice> stockNoticeList = new ArrayList<>();
        for (Object object : jsonArray) {
            JSONObject stockNoticeJo = (JSONObject) object;
            StockNotice stockNotice = new StockNotice();
            stockNotice.setArt_code(stockNoticeJo.getString("art_code"));
            stockNotice.setCodes(stockNoticeJo.getString("codes"));
            stockNotice.setColumns(stockNoticeJo.getString("columns"));
            stockNotice.setColumn_code(stockNoticeJo.getString("column_code"));
            stockNotice.setColumn_name(stockNoticeJo.getString("column_name"));
            stockNotice.setDisplay_time(stockNoticeJo.getString("display_time"));
            stockNotice.setEiTime(stockNoticeJo.getString("eiTime"));
            stockNotice.setLanguage(stockNoticeJo.getString("language"));
            stockNotice.setNotice_date(stockNoticeJo.getString("notice_date"));
            stockNotice.setTitle(stockNoticeJo.getString("title"));
            stockNotice.setTitle_ch(stockNoticeJo.getString("title_ch"));
            stockNotice.setTitle_en(stockNoticeJo.getString("title_en"));
            stockNoticeList.add(stockNotice);
        }

//        List<StockNotice> stockNoticeList = JSON.parseArray(jsonObjectList, StockNotice.class);
        if (stockNoticeList == null || stockNoticeList.size() == 0) {
            System.out.println("通知返回列表为空：" + rs);
            return null;
        }

        //遍历列表，过滤
        for (StockNotice stockNotice : stockNoticeList) {
            //过滤指定字符
            String titleCh = stockNotice.getTitle_ch();
//            System.out.println(titleCh);
            for (String filterKey : filterList) {
                if (titleCh.contains(filterKey)) {
                    System.out.print("包含关键字：" + filterKey);
                    System.out.print(",通知日期：" + stockNotice.getNotice_date());
                    System.out.print(",标题：" + titleCh);
                    System.out.print(",显示时间：" + stockNotice.getDisplay_time());
                    System.out.print(",EiTime：" + stockNotice.getEiTime());
                    System.out.print(",通知返回：" + rs);
                    System.out.println();
                }
            }
        }

        return null;
    }

    /**
     * 查询，返回结果字符串json格式
     *
     * @param zqdm
     * @return
     */
    public static String httpRsStockNotice(String zqdm) {
        long curTime = System.currentTimeMillis();
        //http://np-anotice-stock.eastmoney.com/api/security/ann?cb=jQuery112301928918159223474_1636981415638&sr=-1&page_size=50&page_index=1&ann_type=A&client_source=web&stock_list=002507&f_node=0&s_node=0
        StringBuffer url = new StringBuffer();
//        url.append(jqueryHttpHead);
//        url.append("jQuery112309508918124001358_");
        url.append("http://np-anotice-stock.eastmoney.com/api/security/ann");
        StringBuffer urlParam = new StringBuffer();
        urlParam.append("jQuery11230192891815922").append(RandomUtils.nextInt(1000, 9999));
        urlParam.append("_");
        urlParam.append(curTime);
        urlParam.append("&sr=-1");//
        urlParam.append("&page_size=50");//
        urlParam.append("&page_index=1");//
        urlParam.append("&ann_type=A");//
        urlParam.append("&client_source=web");//
        urlParam.append("&stock_list=").append(zqdm);//
        urlParam.append("&f_node=0");//
        urlParam.append("&s_node=0");//

//        System.out.println("请求url:");
//        System.out.println(url + "?" + urlParam);
        String rs = "";
        try {
            rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
        } catch (Exception e) {
            System.out.println("/** http重试 **/");
            rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
        }

        /**
         * 如果返回异常，n次重试
         */
        for (int i = 0; i < 10; i++) {
            if (StringUtils.isBlank(rs)) {
                rs = HttpUtil.sendGet(url.toString(), urlParam.toString(), "");
            } else {
                break;
            }
        }
//        System.out.println("rs:" + rs);
        if (rs.contains("({")) {
            rs = rs.substring(rs.indexOf("({"));
            rs = rs.replace("({", "{");
            rs = rs.replace("});", "}");
        }
//        System.out.println("rs:" + rs);

        return rs;
    }
}