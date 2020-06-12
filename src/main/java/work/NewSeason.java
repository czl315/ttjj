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
        String cookie = "shshshfpa=7d458cc5-ef69-8cca-04f9-04e8eefb8031-1577076121; __jdu=15770761202431945958964; pinId=xcWTturruQA; shshshfpb=yjlcj3AiuwWhpqP6phZkXFA%3D%3D; __jdv=76161171|direct|-|none|-|1591945299888; areaId=1; ipLoc-djd=1-2809-0-0; shshshfp=4154bf8474fadcecaae419f9c9058aff; TrackID=1swBJtDMYMExRKY66kBsNOdmdJJahsW9Dli62pvU8B0Op9WpzB32uuJ4ns2ZDhEH5UwuwK48fwYmgm3GqLpSNkC2vKzyfEcNormYZqf6jbgQ; thor=A718E266D9B2378CAFBC9387160E8751DB914878966BFFE01DDC55E5CC43E6B3507119BA4547A24F8D18EB4B0564A536065A5E2614A2739A05840FAF2A2A32A614BB3136C2E18916F8B9FA26653B0254EA68F015104D196DBD777F77E760070B4365F91BE1A5217B4DB363BD1DA5F93EB0727482FD9FA6658C86A8BF03F3A5D5; pin=czl315; unick=czl315; ceshi3.com=201; _tp=EOpsxLedIh%2Bha%2FKPkDpqCg%3D%3D; _pst=czl315; user-key=b33f3bbe-c314-4321-9cca-3b828ff5dc79; cn=49; shshshsID=f2a55511bd9b6daba55bbd4c2d7d0bc4_2_1591945340306; __jda=122270672.15770761202431945958964.1577076120.1589455637.1591945300.5; __jdc=122270672; 3AB9D23F7A4B3C9B=PMRCFLCUO2QEAKTO3OLBOKLRFTIBQFWR423GYIVNGBMDU3YZKT53RRFSK4TL72V5IF5FRF24EEQKY5ANIUW7RDPGPU; __jdb=122270672.6.15770761202431945958964|5.1591945300";


//        String browseType = "listGoods";
//        String browseType = "listMeeting";
        String browseType = "shopInfo";
        String listJsonObj = listGoods(cookie, browseType);//查询商品列表
        browseGoods(listJsonObj, cookie,browseType);//浏览商品
//        browseMeeting(listJsonObj, cookie);//浏览商品
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
            skuId = "id";
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
        if("listGoods".equals(browseType) || "listMeeting".equals(browseType)){
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
                String rs = HttpUtil.sendGet(url, urlParam.toString(), cookie);
                System.out.println(rs);
            }
        }

    }
}
