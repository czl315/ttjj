package work;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import utils.HttpUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * 守护明星
 */
public class StarDoTask extends TimerTask {
//    /**
//     * logger
//     */
//    private final static Logger logger = Logger.getLogger(NewSeason.class);

    /**
     * cookie
     */
    private final static String cookie = "shshshfpa=00c2369e-1ebc-a87a-c0d6-f23f02eb461f-1525437685; shshshfpb=268190f132b494db48c188c56f94acb765aec54f6338d03f664da35f97; pinId=xcWTturruQA; __jdu=15143778252431524477047; __guid=211148491.959919709830732800.1596249332740.9607; areaId=1; ipLoc-djd=1-72-0-0; pin=czl315; unick=czl315; _tp=EOpsxLedIh%2Bha%2FKPkDpqCg%3D%3D; _pst=czl315; __jdv=137720036|direct|-|none|-|1603471133013; TrackID=1DhC7y9vh8--XK9qq3eYIWJL_yLHEPl99vhLGMVHy8QrHdEOXK3Zg_9JvevcObWn8AqUQXtp5sn-avZDP9vdBLA; thor=A718E266D9B2378CAFBC9387160E8751C139A29BC4AA2B6352E12DB10D30B7B4A70E6FE70799D8F285DA7AECF1F1D6EB37DBEF034FB1889820FC9A35562681904830B7EDC21F33FBC0EBC05F2E70489B1022AAED1EE9D4252B3978D11A66558E9D9FB6224E3627F00032D1B5776B4F2953035CC8ABE43FC020507E9AA09F7A80; ceshi3.com=201; shshshfp=5cde40273325e7c97f146bb943194707; shshshsID=c03debc5c7c7091616dc036749fb6fe7_2_1604073303251; __jda=122270672.15143778252431524477047.1514377825.1603730399.1604073289.591; __jdc=122270672; 3AB9D23F7A4B3C9B=3NPW7L6RMBADGBRW4PE5K4UBZTHIUSJ64WTYYACB2QADAC6WQJKSB6FDQTR6LEM53W3RXEJ7ARX72YUJX27HCCQXME; monitor_count=2; __jdb=122270672.6.15143778252431524477047|591.1604073289";

    /**
     * 新品季
     *
     * @param args args
     */
    public static void main(String[] args) {
        String urlPre = "https://urvsaggpt.m.jd.com/guardianstar/getHomePage";
//        String param = "starId=aokesilingengxin";
//        String param = "starId=skgwangyibo";
        String param = "starId=bolangwutiaoren";
        doBizTask(urlPre, param);
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
     *
     * @param url
     */
    private static void doBizTask(String url, String urlParam) {
//        System.out.println("查询开始：");

//        String urlPre = "https://urvsaggpt.m.jd.com/guardianstar/getHomePage?starId=";
//        String star = "aokesilingengxin";
//        String url = String.join("", urlPre, star);

        System.out.println("查询-url:" + url);
        String rs = HttpUtil.sendGet(url, urlParam, cookie);
//        System.out.println("查询-rs:" + rs);

        JSONObject rsJson = JSONObject.parseObject(rs);
        JSONArray dateJsonList = rsJson.getJSONArray("data");
        if (dateJsonList == null) {
            return;
        }
        JSONObject dateJson = dateJsonList.getJSONObject(0);
        String starId = dateJson.getString("starId");
        //任务-店铺
        for (int i = 0; i < dateJsonList.size(); i++) {
            JSONArray objList = dateJson.getJSONArray("shopList");
            doTaskList(objList, starId, "shop","shopId");
        }

//        //任务-venue
        for (int i = 0; i < dateJsonList.size(); i++) {
            JSONArray objList = dateJson.getJSONArray("venueList");
            doTaskList(objList, starId, "venue","venueId");
        }
        //任务-productList
        for (int i = 0; i < dateJsonList.size(); i++) {
            JSONArray objList = dateJson.getJSONArray("productList");
            doTaskList(objList, starId, "product","productId");
        }


//        browseType = "listMeeting";//会场
//        listJsonObj = listGoods(cookie, browseType);//查询
//        browseGoods(listJsonObj, cookie, browseType);//浏览
//        getGoodsPrize(cookie, browseType, listJsonObj);//领奖
//
//        browseType = "shopInfo";//店铺
//        listJsonObj = listGoods(cookie, browseType);//查询
//        browseGoods(listJsonObj, cookie, browseType);//浏览
//        getGoodsPrize(cookie, browseType, listJsonObj);//领奖
    }

    private static void doTaskList(JSONArray shopList, String starId, String type, String keyObjId) {
        for (int i = 0; i < shopList.size(); i++) {
            JSONObject shop = shopList.getJSONObject(i);
            String shopId = shop.getString(keyObjId);
            String url = "https://urvsaggpt.m.jd.com/guardianstar/doTask";
            //第一步：浏览任务
            String status1 = "1";
            String urlParam = String.join("", "starId=", starId, "&", "type=", type, "&", "id=", shopId, "&", "status=", status1);
            System.out.println(url+","+urlParam);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String rs = HttpUtil.sendPost(url, urlParam, cookie);
            System.out.println("做任务-立即领取-结果：" + rs);



            //第2步：立即领取
            String status = "2";
            String urlParam2 = String.join("", "starId=", starId, "&", "type=", type, "&", "id=", shopId, "&", "status=", status);
            System.out.println(url+","+urlParam2);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String rs2 = HttpUtil.sendPost(url, urlParam2, cookie);
            System.out.println("做任务-立即领取-结果：" + rs2);
        }
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
        String urlPre = "https://urvsaggpt.m.jd.com/guardianstar/getHomePage";
        String param = "starId=aokesilingengxin";
        doBizTask(urlPre, param);
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
