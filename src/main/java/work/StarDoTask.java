package work;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import utils.HttpUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private final static String cookie = "shshshfpa=7d458cc5-ef69-8cca-04f9-04e8eefb8031-1577076121; __jdu=15770761202431945958964; pinId=xcWTturruQA; shshshfpb=yjlcj3AiuwWhpqP6phZkXFA%3D%3D; pin=czl315; unick=czl315; _tp=EOpsxLedIh%2Bha%2FKPkDpqCg%3D%3D; _pst=czl315; ceshi3.com=201; areaId=1; ipLoc-djd=1-2809-0-0; user-key=eea11b8b-1c6b-4189-ad68-d4af9c92847c; cn=70; __jdv=50564013|ssa.jd.com|-|referral|-|1604764856203; sso.jd.com=BJ.61a203343cdb49acb67de1bbdd31a6f6; TrackID=1gjOP5cZA1IvDS2hYXj_-mPtkmoTisTZoYHhYo5Oz8jvGZjwojpvkXed3X7vSs7zKfLcWhwHR5DlWBwpsZ9rsncYTw6vfWrf7SdC3y7t4Bj4; thor=A718E266D9B2378CAFBC9387160E87511F32BF3A9B87B8CBB0DE77D2839AF424FDCB5D2AE0C9C287AD759BD85B12FD065CA4EC72E4FCA4371FD8C5754C186BF3DE377EE24AB7B6A74C866EEA2C1EE15CBC8143F8A5282070A370DF558967C607D29044415ED34EF167F174C2B2CD9D6BA66F5FCC13C29AFB80744673F278FDDD; shshshfp=4154bf8474fadcecaae419f9c9058aff; shshshsID=e745c0904814b2b64fab38891714fb93_2_1605023343615; __jda=122270672.15770761202431945958964.1577076120.1605013624.1605018977.460; __jdc=122270672; 3AB9D23F7A4B3C9B=PMRCFLCUO2QEAKTO3OLBOKLRFTIBQFWR423GYIVNGBMDU3YZKT53RRFSK4TL72V5IF5FRF24EEQKY5ANIUW7RDPGPU; __jdb=122270672.31.15770761202431945958964|460.1605018977";

    /**
     * #海尔毛晓彤
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=haiermaoxiaotong
     *
     * #创维毛不易
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=chuangweimaobuyi
     *
     * #欧乐B杨紫
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=oulebyangzi
     *
     * #美的佟丽娅
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=meiditongliya
     *
     * #雀巢朱一龙
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=quechaozhuyilong
     *
     * #海尔空调陈晓
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=haierchenxiao
     *
     * #飞利浦李现
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=feilipulixian
     *
     * #飞利浦任嘉伦
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=feilipurenjialun
     *
     * #海信成果
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=haixinchengguo
     *
     * #九阳邓伦
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=jiuyangdenglun
     *
     * #老板宋威龙
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=laobansongweilong
     *
     * #方太
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=fangtai
     *
     * #奥克斯林更新
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=aokesilingengxin
     *
     * #LG杨子姗
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=lgyangzishan
     *
     * #长虹宋轶
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=changhongsongyi
     *
     * #飞利浦王子异
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=feilipuwangziyi
     *
     * #博朗五条人
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=bolangwutiaoren
     *
     * #三星宁静-暂未对外
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=sanxingningjing
     *
     * #_heidianliyitong_20-黑电李艺彤(第二波明星-品类)
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=heidianliyitong
     * #_kongtiaozhangjike_21-空调张继科(第二波明星-品类)
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=kongtiaozhangjike
     * #_xiaojiadianxiongziqi_22-小家电熊梓淇(第二波明星-品类)
     * https://urvsaggpt.m.jd.com/static/index.html#/?starId=xiaojiadianxiongziqi
     *
     * @param args args
     */
    public static void main(String[] args) {
        String urlPre = "https://urvsaggpt.m.jd.com/guardianstar/getHomePage";
//        String param = "starId=aokesilingengxin";
//        String param = "starId=haiermaoxiaotong";
//        String param = "starId=chuangweimaobuyi";
//        String param = "starId=oulebyangzi";
        String param = "starId=heidianliyitong";
//        String param = "starId=kongtiaozhangjike";
//        String param = "starId=xiaojiadianxiongziqi";

        //设置线程
//        ExecutorService executorService= Executors.newFixedThreadPool(5);
//        executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                doBizTask(urlPre, "starId=kongtiaozhangjike");
//            }
//        });

        //        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                doBizTask(urlPre, "starId=xiaojiadianxiongziqi");
//            }
//        }).start();

        //        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                doBizTask(urlPre, "starId=kongtiaozhangjike");
//            }
//        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                doBizTask(urlPre, "starId=heidianliyitong");
//            }
//        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                doBizTask(urlPre, "starId=feilipurenjialun");
//            }
//        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                doBizTask(urlPre, "starId=haierchenxiao");
//            }
//        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                doBizTask(urlPre, "starId=feilipulixian");
//            }
//        }).start();



//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                doBizTask(urlPre, "starId=haixinchengguo");
//            }
//        }).start();
//
        new Thread(new Runnable() {
            @Override
            public void run() {
                doBizTask(urlPre, "starId=jiuyangdenglun");
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                doBizTask(urlPre, "starId=fangtai");
//            }
//        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                doBizTask(urlPre, "starId=aokesilingengxin");
//            }
//        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                doBizTask(urlPre, "starId=lgyangzishan");
//            }
//        }).start();





//        doBizTask(urlPre, param);
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
        if (dateJsonList == null ||dateJsonList.size()==0) {
            System.out.println(dateJsonList+"==0");
            return;
        }
        JSONObject dateJson = dateJsonList.getJSONObject(0);
        String starId = dateJson.getString("starId");
//        任务-店铺
        for (int i = 0; i < dateJsonList.size(); i++) {
            JSONArray objList = dateJson.getJSONArray("shopList");
            doTaskList(objList, starId, "shop","shopId");
        }

        //任务-venue
        for (int i = 0; i < dateJsonList.size(); i++) {
            JSONArray objList = dateJson.getJSONArray("venueList");
            doTaskList(objList, starId, "venue","venueId");
        }
        //任务-productList
        for (int i = 0; i < dateJsonList.size(); i++) {
            JSONArray objList = dateJson.getJSONArray("productList");
            doTaskList(objList, starId, "product","productId");
        }
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
//            System.out.println("做任务-浏览任务"+",type="+type+"-结果：" + rs);

            try {
                Thread.sleep(10000);
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
