package work;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import utils.HttpUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 新品季
 */
public class NewSeason extends TimerTask {
//    /**
//     * logger
//     */
//    private final static Logger logger = Logger.getLogger(NewSeason.class);

    /**
     * cookie
     */
    private final static String cookie = "shshshfpa=00c2369e-1ebc-a87a-c0d6-f23f02eb461f-1525437685; shshshfpb=268190f132b494db48c188c56f94acb765aec54f6338d03f664da35f97; pinId=xcWTturruQA; __jdu=15143778252431524477047; unpl=V2_ZzNtbRBVEUB2C0dcK0xUAGJUR1tKVxYTfQhEU3IQDwFgBkENclRCFnQUR1JnG1wUZAAZXUZcQRRFCEdkeBBVAWMDE1VGZxpFK0oYEDpBA04%2bR0ICLFYTHHMME1N7S1hSMwYUCEsEERB8D0BcextfDWUAR15AZ3MWdThGVUsZWAViARRbS1RHFnAKRlB4HFkDYQIXW3JnRCVFOE5VeRpbDFcCIlxyFRdJdwtBVnkaEQVjAxdfRFFKFnELQ1Z7HV8AYgUUXEdRcxRFCw%3d%3d; areaId=1; ipLoc-djd=1-72-0-0; PCSYCityID=CN_110000_110100_110105; pin=czl315; unick=czl315; _tp=EOpsxLedIh%2Bha%2FKPkDpqCg%3D%3D; _pst=czl315; __jdv=76161171|haosou-pinzhuan|t_288551095_haosoupinzhuan|cpc|0a875d61c5fe47d8bc48679132932d23_0_c3cd2209ad944fd791d7913688b564ba|1596204971247; __guid=211148491.959919709830732800.1596249332740.9607; shshshfp=7b91ed39142f53c5b120c12e311be4a6; TrackID=1ItxsW2PS6a28lFCMWq3UmZImm_geKolsZS75a83-fvvxD2hOn3e9V2sw8NtTDRYY8h5dadp-Ip7MBoKbDorkKg; thor=A718E266D9B2378CAFBC9387160E87517643674DD869B0A14DBB59A408D92CCF56E2D9BEA03F8451533FBCB3246518F5388A4A845B8C30C79BD1C1525DD2F6CC6BB0823974F8A83732089E195AA47189A39AD064AF484001E900A3EE08B7F2A8DA50BB1538EB379EE22B33019A490B203813A99EDC5D4052A5AFD3B6DF55D600; ceshi3.com=201; shshshsID=2b7eeaa16dd9d7f43c61c57c4c277917_2_1596360995204; __jda=122270672.15143778252431524477047.1514377825.1596249262.1596360964.572; __jdc=122270672; 3AB9D23F7A4B3C9B=3NPW7L6RMBADGBRW4PE5K4UBZTHIUSJ64WTYYACB2QADAC6WQJKSB6FDQTR6LEM53W3RXEJ7ARX72YUJX27HCCQXME; monitor_count=1; __jdb=122270672.7.15143778252431524477047|572.1596360964";

    /**
     * 新品季
     *
     * @param args args
     */
    public static void main(String[] args) {
        doBizTask();
//        long initDelay = getTimeMillis("18:00:00") - System.currentTimeMillis();
//        ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(1);
//        pool.scheduleAtFixedRate(new Runnable() {
//            public void run() {
//                // task to run goes here
//                System.out.println("新品季-定时做任务");
//                doBizTask();
//            }
//        }, 0, 60 * 60 * 4, TimeUnit.SECONDS);
    }


    /**
     * 获取指定时间对应的毫秒数
     *
     * @param time "HH:mm:ss"
     * @return
     */

    private static long getTimeMillis(String time) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
            Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
            return curDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 做任务
     */
    private static void doBizTask() {
//        System.out.println("查询开始：");


        String browseType = "";
        String listJsonObj = "";

        browseType = "listGoods";//商品
        listJsonObj = listGoods(cookie, browseType);//查询商
        browseGoods(listJsonObj, cookie, browseType);//浏览
        getGoodsPrize(cookie, browseType, listJsonObj);//领奖

        browseType = "listMeeting";//会场
        listJsonObj = listGoods(cookie, browseType);//查询
        browseGoods(listJsonObj, cookie, browseType);//浏览
        getGoodsPrize(cookie, browseType, listJsonObj);//领奖

        browseType = "shopInfo";//店铺
        listJsonObj = listGoods(cookie, browseType);//查询
        browseGoods(listJsonObj, cookie, browseType);//浏览
        getGoodsPrize(cookie, browseType, listJsonObj);//领奖
    }

    /**
     * 查询商品列表
     *
     * @param cookie
     * @param browseType
     */
    private static String listGoods(String cookie, String browseType) {
        String url = "https://rdcseason.m.jd.com/api/task/" + browseType;
        System.out.println("查询列表-browseType" + browseType + ",-url:" + url);
        StringBuffer urlParam = new StringBuffer();
        String rs = HttpUtil.sendGet(url, urlParam.toString(), cookie);
        System.out.println("查询列表-browseType" + browseType + ",-rs:" + rs);
        return rs;
    }

    /**
     * 浏览商品
     *
     * @param goodsListJsonObj
     * @param cookie
     * @param browseType
     */
    private static void browseGoods(String goodsListJsonObj, String cookie, String browseType) {
        String listType = "";
        String skuId = "";

        if ("listGoods".equals(browseType)) {
            browseType = "browseGoods";
            listType = "goodsList";
            skuId = "skuId";
        }
        if ("listMeeting".equals(browseType)) {
            browseType = "browseMeeting";
            listType = "meetingList";
            skuId = "meetingId";
        }
        if ("shopInfo".equals(browseType)) {
            browseType = "browseShop";
            listType = "goodsList";
            skuId = "shopId";
        }
        String url = "https://rdcseason.m.jd.com/api/task/" + browseType;
        //解析商品列表
        JSONObject jsonObject = JSONObject.parseObject(goodsListJsonObj);
        if ("browseGoods".equals(browseType) || "browseMeeting".equals(browseType)) {
            JSONObject jsonObjectData = JSONObject.parseObject(jsonObject.getString("data"));
            JSONArray jsonArrayGoodsList = JSONArray.parseArray(jsonObjectData.getString(listType));
            for (Object object : jsonArrayGoodsList) {
                StringBuffer urlParam = new StringBuffer();
                JSONObject goods = (JSONObject) object;
                String entityId = goods.getString("id");
                urlParam.append(skuId + "=" + entityId);
                String rs = HttpUtil.sendGet(url, urlParam.toString(), cookie);
                System.out.println(rs);
            }
        }

        if ("browseShop".equals(browseType)) {
            JSONArray jsonArrayGoodsList = JSONArray.parseArray(jsonObject.getString("data"));
            for (Object object : jsonArrayGoodsList) {
                StringBuffer urlParam = new StringBuffer();
                JSONObject goods = (JSONObject) object;
                String entityId = goods.getString("shopId");
                urlParam.append(skuId + "=" + entityId);
                String rs = HttpUtil.sendPost(url, urlParam.toString(), cookie);
                System.out.println(rs);
            }
        }

    }

    /**
     * 获取奖励
     *
     * @param cookie
     * @param browseType
     * @param goodsListJsonObj
     * @return
     */
    private static String getGoodsPrize(String cookie, String browseType, String goodsListJsonObj) {
        try {
            Thread.sleep(5001);//间隔10秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String url = "https://rdcseason.m.jd.com/api/task/";
        String urlGetPrizeType = "";
        String listType = "";
        String skuId = "";
        if ("listGoods".equals(browseType)) {
            url = url + "getGoodsPrize";
            listType = "goodsList";
            skuId = "skuId";
        }
        if ("listMeeting".equals(browseType)) {
            url = url + "getMeetingPrize";
            listType = "meetingList";
            skuId = "meetingId";
        }
        if ("shopInfo".equals(browseType)) {
            url = url + "getShopPrize";
//            listType = "goodsList";
            skuId = "shopId";
        }

        //解析商品列表
        JSONObject jsonObject = JSONObject.parseObject(goodsListJsonObj);
        if ("listGoods".equals(browseType) || "listMeeting".equals(browseType)) {
            JSONObject jsonObjectData = JSONObject.parseObject(jsonObject.getString("data"));
            JSONArray jsonArrayGoodsList = JSONArray.parseArray(jsonObjectData.getString(listType));
            for (Object object : jsonArrayGoodsList) {
                StringBuffer urlParam = new StringBuffer();
                JSONObject goods = (JSONObject) object;
                String entityId = goods.getString("id");
                urlParam.append(skuId + "=" + entityId);
                StringBuffer urlRequest = new StringBuffer().append(url);
//                System.out.println(("获取奖励,url="+urlRequest));
                try {
                    Thread.sleep(1001);//间隔1秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String rs = HttpUtil.sendGet(urlRequest.toString(), urlParam.toString(), cookie);
                System.out.println(rs);
            }
        }

        if ("shopInfo".equals(browseType)) {
            JSONArray jsonArrayGoodsList = JSONArray.parseArray(jsonObject.getString("data"));
            for (Object object : jsonArrayGoodsList) {
                StringBuffer urlParam = new StringBuffer();
                JSONObject goods = (JSONObject) object;
                String entityId = goods.getString("shopId");
                urlParam.append(skuId + "=" + entityId);
//                System.out.println(("浏览店铺,Id=" + skuId));
                try {
                    Thread.sleep(1001);//间隔1秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String rs = HttpUtil.sendPost(url, urlParam.toString(), cookie);
                System.out.println(rs);
            }
        }

        return "";
    }

    public void run() {
        System.out.println("定时任务timer");
        doBizTask();
    }

    /**
     * 京贴兑换优惠券
     *
     * @param cookie
     */
    private static String jingTieExchange(String cookie) {
        String url = "https://rdcseason.m.jd.com/api/task/jingTieExchange";
        StringBuffer urlParam = new StringBuffer();
        String rs = HttpUtil.sendPost(url, urlParam.toString(), cookie);
//        System.out.println(rs);
        return rs;
    }
}
