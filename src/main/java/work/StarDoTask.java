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
    private final static String cookie = "shshshfpa=7d458cc5-ef69-8cca-04f9-04e8eefb8031-1577076121; __jdu=15770761202431945958964; pinId=xcWTturruQA; shshshfpb=yjlcj3AiuwWhpqP6phZkXFA%3D%3D; pin=czl315; unick=czl315; _tp=EOpsxLedIh%2Bha%2FKPkDpqCg%3D%3D; _pst=czl315; user-key=ca3009ba-f2ec-4e53-b8a1-ce2bc354f73f; __jdv=137720036|direct|-|none|-|1602131826715; ceshi3.com=201; cn=46; ipLoc-djd=1-2809-0-0; areaId=1; TrackID=1vXii0AmNa7-HkSc47cFQqDy0w9IRV0jCKB12SZHDULqNLPo7756033PV--MrQOhJ4Jq8gyc60q83Y0eYO6QT5IR10UIIFu3aGotbI6XPd8k; jd.erp.lang=zh_CN; erp1.jd.com=7674AEEEDE87FAD500DFD91F29B55E0942D12E1BF2A66AF21B660315F9F413F1302F7A66613F32F801C719FD593E74BB48CBD92CBDB854F8B840D0FB4E6010BD5AD3A7A27E6CE3975AEC8A30154349718C9E51251F67EA7C1405F9118408B847; sso.jd.com=BJ.b69ac0d6afbc437f9051f7e9e3dc80a4; mba_muid=15770761202431945958964; shshshfp=87af3de7878d7588aca9a89af95a1d15; shshshsID=cbf2d7a1cd5c181639fea8c29ad8201a_1_1603417606753; TrackerID=c-uj_o3L2gyCbprQRVNIW8ksleaznsuGYkMT4GSa7MMsjY_b2FVofbYnibAYDwzFaxuzQV-V-YKKCsbaJ6xNDvmlqSjxpz1ixbuOUlUgAbGqSps2MFZGR-qiRcdBQNue; pt_key=AAJfkjYPADBc9wj48xLnoI2kA1-9J40a5kKOj2LKQPGLKosskrQtFXAg_T1CNAf_fJ4luaZhDMY; pt_pin=czl315; pt_token=x3nkomjx; pwdt_id=czl315; sfstoken=tk01mb9b61c1ba8sMSsxKzN4RlB4IDSPcjTYiirn2aNDnTk4WmTHRew4Lnd4CbIHyl9D8aEt4vRcQXex3E9G0kJtNBJk; __jda=122270672.15770761202431945958964.1577076120.1603362594.1603415953.357; __jdc=122270672; 3AB9D23F7A4B3C9B=PMRCFLCUO2QEAKTO3OLBOKLRFTIBQFWR423GYIVNGBMDU3YZKT53RRFSK4TL72V5IF5FRF24EEQKY5ANIUW7RDPGPU; mba_sid=16034168544427363555979529712.7; __jd_ref_cls=Mnpm_ComponentApplied; thor=A718E266D9B2378CAFBC9387160E8751722FD49DF04EB58CAB38C0C68F509655CBB4E3828E62F3827E01738DB3C7AD208CAD5CBAB4CCF4BB5CEED698CD7FAF4EC93B1C56A3C12C5BA059F93A26C949C8763D2C615BCD6784E0B9E709121344C0092C91E006AFE2711DD5CC7F99496824FCBB4707333B6D604793E56A1AFF9EB6; __jdb=122270672.12.15770761202431945958964|357.1603415953";

    /**
     * 新品季
     *
     * @param args args
     */
    public static void main(String[] args) {
        String urlPre = "https://urvsaggpt.m.jd.com/guardianstar/getHomePage";
        String param = "starId=aokesilingengxin";
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
//        for (int i = 0; i < dateJsonList.size(); i++) {
//            JSONArray objList = dateJson.getJSONArray("shopList");
//            doTaskList(objList, starId, "shop","shopId");
//        }

//        //任务-venue
//        for (int i = 0; i < dateJsonList.size(); i++) {
//            JSONArray objList = dateJson.getJSONArray("venueList");
//            doTaskList(objList, starId, "venue","venueId");
//        }
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
            String rs = HttpUtil.sendPost(url, urlParam, cookie);
            System.out.println("做任务-立即领取-结果：" + rs);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //第2步：立即领取
            String status = "2";
            String urlParam2 = String.join("", "starId=", starId, "&", "type=", type, "&", "id=", shopId, "&", "status=", status);
            System.out.println(url+","+urlParam2);
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
