package work;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import utils.HttpUtil;

/**
 * 新品季
 */
public class NewSeason {
    private final static Logger logger = Logger.getLogger(NewSeason.class);


    /**
     * 新品季
     *
     * @param args args
     */
    public static void main(String[] args) {
//        System.out.println("查询开始：");
        String cookie = "shshshfpa=7d458cc5-ef69-8cca-04f9-04e8eefb8031-1577076121; __jdu=15770761202431945958964; pinId=xcWTturruQA; shshshfpb=yjlcj3AiuwWhpqP6phZkXFA%3D%3D; __jdv=76161171|direct|-|none|-|1591945299888; areaId=1; ipLoc-djd=1-2809-0-0; shshshfp=4154bf8474fadcecaae419f9c9058aff; pin=czl315; unick=czl315; _tp=EOpsxLedIh%2Bha%2FKPkDpqCg%3D%3D; _pst=czl315; user-key=b33f3bbe-c314-4321-9cca-3b828ff5dc79; cn=49; TrackID=1j5aNi9FXnnxPIiOVZWH-bHo1BeIWq5lqynywK5V7NnJfmlwsJY8Pm81L6Twh3TFljY2Fp7XvNp3mo1ny1IWq2m1uef0T3v41awFzSZ6DSxI; thor=A718E266D9B2378CAFBC9387160E875110CE55110EA8365AD1E3F4FE2A9A392D6A6D90F36223F77C58A511CB434DF650A8896DFE2B28A43496006C991F0348884AD312738877666672FF9EA3B51C98FE8F2FA141799EEA8954AA9EFBDCE4394D9110DD945FF7D1FD0DA40C7326F24AE2C8472C8903A552E75468F6828D47682E; ceshi3.com=201; shshshsID=69438b643a25a11b9c107a1702852c34_1_1592184039515; __jda=122270672.15770761202431945958964.1577076120.1591945300.1592184030.6; __jdc=122270672; 3AB9D23F7A4B3C9B=PMRCFLCUO2QEAKTO3OLBOKLRFTIBQFWR423GYIVNGBMDU3YZKT53RRFSK4TL72V5IF5FRF24EEQKY5ANIUW7RDPGPU; __jdb=122270672.7.15770761202431945958964|6.1592184030";


//        String browseType = "listGoods";
//        String browseType = "listMeeting";
        String browseType = "shopInfo";
        String listJsonObj = listGoods(cookie, browseType);//查询商品列表
//        browseGoods(listJsonObj, cookie,browseType);//浏览商品
        getGoodsPrize(cookie,browseType,listJsonObj);
    }

    /**
     * 查询商品列表
     * @param cookie
     * @param browseType
     */
    private static String listGoods(String cookie, String browseType) {
        String url = "https://rdcseason.m.jd.com/api/task/"+browseType;
        StringBuffer urlParam = new StringBuffer();
        String rs = HttpUtil.sendGet(url, urlParam.toString(), cookie);
//        System.out.println(rs);
        return rs;
    }
    /**
     * 浏览商品
     *  @param goodsListJsonObj
     * @param cookie
     * @param browseType
     */
    private static void browseGoods(String goodsListJsonObj, String cookie, String browseType) {
        String listType="";
        String skuId="";

        if("listGoods".equals(browseType)){
            browseType = "browseGoods";
            listType = "goodsList";
            skuId = "skuId";
        }
        if("listMeeting".equals(browseType)){
            browseType = "browseMeeting";
            listType = "meetingList";
            skuId = "meetingId";
        }
        if("shopInfo".equals(browseType)){
            browseType = "browseShop";
            listType = "goodsList";
            skuId = "shopId";
        }
        String url = "https://rdcseason.m.jd.com/api/task/"+browseType;
        //解析商品列表
        JSONObject jsonObject = JSONObject.parseObject(goodsListJsonObj);
        if("browseGoods".equals(browseType) || "browseMeeting".equals(browseType)){
            JSONObject jsonObjectData = JSONObject.parseObject(jsonObject.getString("data"));
            JSONArray jsonArrayGoodsList = JSONArray.parseArray(jsonObjectData.getString(listType));
            for (Object object : jsonArrayGoodsList) {
                StringBuffer urlParam = new StringBuffer();
                JSONObject goods = (JSONObject)object;
                String entityId = goods.getString("id");
                urlParam.append(skuId+"=" + entityId);
                System.out.println(("浏览商品,skuId="+skuId));
                String rs = HttpUtil.sendGet(url, urlParam.toString(), cookie);
                System.out.println(rs);
            }
        }

        if("browseShop".equals(browseType)){
            JSONArray jsonArrayGoodsList = JSONArray.parseArray(jsonObject.getString("data"));
            for (Object object : jsonArrayGoodsList) {
                StringBuffer urlParam = new StringBuffer();
                JSONObject goods = (JSONObject)object;
                String entityId = goods.getString("shopId");
                urlParam.append(skuId+"=" + entityId);
                System.out.println(("浏览店铺,Id="+skuId));
                String rs = HttpUtil.sendPost(url, urlParam.toString(), cookie);
                System.out.println(rs);
            }
        }

    }

    /**
     * 获取奖励
     * @param cookie
     * @param browseType
     * @param goodsListJsonObj
     * @return
     */
    private static String getGoodsPrize(String cookie, String browseType,String goodsListJsonObj) {
        String url = "https://rdcseason.m.jd.com/api/task/";
        String urlGetPrizeType = "";
        String listType="";
        String skuId="";
        if("listGoods".equals(browseType)){
            url = url + "getGoodsPrize";
            listType = "goodsList";
            skuId = "skuId";
        }
        if("listMeeting".equals(browseType)){
            url = url + "getMeetingPrize";
            listType = "meetingList";
            skuId = "meetingId";
        }
        if("shopInfo".equals(browseType)){
            url = url + "getShopPrize";
//            listType = "goodsList";
            skuId = "shopId";
        }

        //解析商品列表
        JSONObject jsonObject = JSONObject.parseObject(goodsListJsonObj);
        if("listGoods".equals(browseType) || "listMeeting".equals(browseType)){
            JSONObject jsonObjectData = JSONObject.parseObject(jsonObject.getString("data"));
            JSONArray jsonArrayGoodsList = JSONArray.parseArray(jsonObjectData.getString(listType));
            for (Object object : jsonArrayGoodsList) {
                StringBuffer urlParam = new StringBuffer();
                JSONObject goods = (JSONObject)object;
                String entityId = goods.getString("id");
                urlParam.append(skuId+"=" + entityId);
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

        if("shopInfo".equals(browseType)){
            JSONArray jsonArrayGoodsList = JSONArray.parseArray(jsonObject.getString("data"));
            for (Object object : jsonArrayGoodsList) {
                StringBuffer urlParam = new StringBuffer();
                JSONObject goods = (JSONObject)object;
                String entityId = goods.getString("shopId");
                urlParam.append(skuId+"=" + entityId);
                System.out.println(("浏览店铺,Id="+skuId));
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
}
