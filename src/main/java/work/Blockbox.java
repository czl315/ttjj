package work;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import utils.HttpUtil;
import work.dto.TaskVo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * 盲盒
 */
public class Blockbox extends TimerTask {
//    /**
//     * logger
//     */
//    private final static Logger logger = Logger.getLogger(Blockbox.class);

    /**
     * cookie
     */
    private final static String cookie = "shshshfpa=7d458cc5-ef69-8cca-04f9-04e8eefb8031-1577076121; __jdu=15770761202431945958964; pinId=xcWTturruQA; shshshfpb=yjlcj3AiuwWhpqP6phZkXFA%3D%3D; pin=czl315; unick=czl315; _tp=EOpsxLedIh%2Bha%2FKPkDpqCg%3D%3D; _pst=czl315; user-key=1775a64e-111a-4b21-8480-cb8f9b143bdb; ipLocation=%u5317%u4eac; __jdv=76161171|direct|-|none|-|1596619327388; ipLoc-djd=1-2809-0-0; areaId=1; PCSYCityID=CN_110000_110100_110112; sso.jd.com=BJ.e470756c36614e06b73a9d2922bf2464; TrackID=155sXVZPw_k90RzdsAuJy3y9VlSNrswd6Py5sdONODbDMrPtETIU3Pd6dOEoe8A7gQidoomXmt_lbsBER69fxFoBL5zp5UUwFzEocj35ZN-I; thor=A718E266D9B2378CAFBC9387160E8751610BA81EF537B4E0723EC2C58ACAA34B02B885729991A25557E74BC4E3F6AC301CD94FF8701296CD9A9F0A5A4FB6A0D965E90942763810790606334D0C2B29BEA06A4BE834955E01E662BACBB14CFF474903586F29721DD73824C2AE07D92A56EA81B94D0F2081E326A6A6AD31C8D074; ceshi3.com=201; __jda=76161171.15770761202431945958964.1577076120.1597304419.1597306894.92; __jdc=76161171; cn=8; mba_muid=15770761202431945958964; shshshfp=9b70e2fbaeffa2bed396601b41f523f7; shshshsID=0779ae365f49835b9865945f6759ec37_4_1597306950258; 3AB9D23F7A4B3C9B=VOWID4DTU2HHVPCPK7E5QYV37B7YKZTJPACOO3A6MKHBE6SQK7MVOB3MOGFTS7EMJJ7BYZTAVFRA4TXOU7ZAHNOC24; TrackerID=Jh2fRUCObmt1jG6QStC7z7GPWt4u4C5uoedHJz4WETbN9zVk-Hsya2qIocWH1jCclsH55Lq2N4tTpm6Lkurn6KswtSQ9cfKUnMnk0LSqKRQF2hjkdIoPGA0PAdCmMdKA; pt_key=AAJfNPhZADCDZuxregA4yeJm6NGLOIL-PJjq4fgeZXElmX9Cj1qJayzR2d-grtnKoquY5lDyA88; pt_pin=czl315; pt_token=6kletqdf; pwdt_id=czl315; __jdb=76161171.9.15770761202431945958964|92.1597306894; mba_sid=15973069171611611636355508512.3; __jd_ref_cls=txmh_1577090969334%7C2";

    /**
     * 新品季
     *
     * @param args args
     *
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
        // https://blindbox.jd.com/active/taskList?t=1597306986531

        String listJsonObj = "";

        String taskListUrl = "https://blindbox.jd.com/active/taskList";
        JSONObject taskListJsonObj = JSONObject.parseObject(taskList(cookie, taskListUrl));
        String taskListData = taskListJsonObj.getString("data");
        JSONObject taskListDataJo = JSONObject.parseObject(taskListData);

        String task1Str = taskListDataJo.getString("task1");//
//        System.out.println("查询任务列表-task1Str："+task1Str);
        TaskVo taskVo1 = JSON.parseObject(task1Str, TaskVo.class);
//        System.out.println("查询任务列表-taskVo1："+JSON.toJSONString(taskVo1));

//        String shopRs = doShopTask();//店铺任务
//
        //
        String rsDoSkuTask = doSkuTask();//商品任务


        String task2Str = taskListDataJo.getString("task2");//
        String task3Str = taskListDataJo.getString("task3");//
        String task5Str = taskListDataJo.getString("task5");//
//        browseGoods(listJsonObj, cookie, browseType);//浏览
//        getGoodsPrize(cookie, browseType, listJsonObj);//领奖

    }

    /**
     * 商品任务
     * @return
     */
    private static String doSkuTask() {
        String taskListUrl = "https://blindbox.jd.com/active/taskList";

        for (int i = 0; i < 16; i++) {
            JSONObject taskListJsonObj = JSONObject.parseObject(taskList(cookie, taskListUrl));
            String taskListData = taskListJsonObj.getString("data");
            JSONObject taskListDataJo = JSONObject.parseObject(taskListData);
            String task4Str = taskListDataJo.getString("task4");//
            System.out.println("查询任务列表-task4Str："+task4Str);
            TaskVo taskVo4 = JSON.parseObject(task4Str, TaskVo.class);
            String skuId = taskVo4.getSkuId();
            String url = "https://blindbox.jd.com/active/browseProduct";
            System.out.println("浏览任务-url:" + url);
            StringBuffer urlParam = new StringBuffer();
            urlParam.append("skuId"+skuId);
            String rs = HttpUtil.sendGet(url, urlParam.toString(), cookie);
            System.out.println("浏览任务-rs:" + rs);
//        System.out.println("查询任务列表-taskVo1："+JSON.toJSONString(taskVo1));

            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //领取奖励-
            String shopPrizeRsStr = taskCoin("https://blindbox.jd.com/active/taskCoin","type=4");
        }



        return "";
    }

    /**
     * 会场任务
     * @return
     */
    private static String doShopTask() {
        for (int i = 0; i < 10; i++) {
            //浏览会场
            String browseShopRsStr = browseShop("https://blindbox.jd.com/active/strollActive","activeId");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //领取奖励-会场:https://blindbox.jd.com/active/taskCoin?t=1597309560842&type=1
            String shopPrizeRsStr = taskCoin("https://blindbox.jd.com/active/taskCoin","type=1");
        }
        return "doShopTask";
    }

    /**
     * 领取奖励-会场
     * @param url
     * @param type
     * @return
     */
    private static String taskCoin(String url, String type) {
        url = url;
        System.out.println("领取奖励-会场-url:" + url);
        StringBuffer urlParam = new StringBuffer();
        urlParam.append(type);
        String rs = HttpUtil.sendGet(url, urlParam.toString(), cookie);
        System.out.println("领取奖励-会场-rs:" + rs);
        return rs;
    }

    /**
     * 浏览会场
     * @param url
     * @param activeId
     */
    private static String browseShop(String url, String activeId) {
        System.out.println("浏览任务-url:" + url);
        StringBuffer urlParam = new StringBuffer();
        String rs = HttpUtil.sendGet(url, urlParam.toString(), cookie);
        System.out.println("浏览任务-rs:" + rs);
        return rs;
    }

    /**
     * 查询任务列表
     *
     * @param cookie
     * @param taskListUrl
     */
    private static String taskList(String cookie, String taskListUrl) {
        String url = taskListUrl;
        System.out.println("查询任务列表" + ",-url:" + url);
        StringBuffer urlParam = new StringBuffer();
        String rs = HttpUtil.sendGet(url, urlParam.toString(), cookie);
        System.out.println("查询任务列表" + ",-rs:" + rs);
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
