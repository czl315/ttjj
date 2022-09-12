package ttjj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomUtils;
import ttjj.dao.BizRankDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.RankBizDataDiff;
import utils.DateUtil;
import utils.HttpUtil;

import java.util.*;

import static utils.ContMapBizBaord.BOARD_NAME_CODE;
import static utils.Content.*;

/**
 * BizService简介
 *
 * @author Administrator
 * @date 2022-05-14 21:37
 */
public class BizService {
    /**
     * 列表查询业务-根据类型
     * //2021-04-16:425;2021-12-06:584;
     *
     * @param date
     * @param type
     * @param pageSize
     * @return
     */
    public static List<RankBizDataDiff> listBiz(String date, String type, int pageSize) {
        //http://87.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112407216560422201541_1652863152766&pn=1&pz=20&po=0&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&wbp2u=6342375825382124|0|1|0|web&fid=f3&fs=m:90+t:2+f:!50&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222&_=1652863152768
        String url = "http://32.push2.eastmoney.com/api/qt/clist/get";
        StringBuffer urlParam = new StringBuffer();
        long curTime = System.currentTimeMillis();
        urlParam.append("cb=jQuery11240323187262602" + RandomUtils.nextInt(1000, 9999) + "_");
        urlParam.append(curTime);
        urlParam.append("&pn=1" +//页数
                "&pz=" + pageSize +//每页数量
                "&po=1" +//pageorder:页面排序：0-正序；1-倒序
                "&np=1" +
                "&ut=bd1d9ddb04089700cf9c27f6f7426281" +
                "&fltt=2" +//浮点数精度
                "&invt=3" +//显示格式：-；0.0
                "&fid=f3");//排序字段
        if (type.equals(DB_RANK_BIZ_TYPE_BAN_KUAI)) {
            urlParam.append("&fs=m:90+t:2+f:!50");
        }
        if (type.equals(DB_RANK_BIZ_TYPE_GAI_NIAN)) {
            urlParam.append("&fs=m:90+t:3+f:!50");
        }
        if (type.equals(DB_RANK_BIZ_TYPE_ETF)) {
            urlParam.append("&fs=b:MK0021,b:MK0022,b:MK0023,b:MK0024");
        }
        if (type.equals(DB_RANK_BIZ_TYPE_LOF)) {
            urlParam.append("&fs=b:MK0404,b:MK0405,b:MK0406,b:MK0407");
        }
        urlParam.append("&fields=" +
                "f1,f2,f3,f4,f5,f6,f7,f8,f9," +
                "f10,f11,f12,f13,f14,f15,f16,f17,f18,f19," +
                "f20,f21,f22,f23,f24,f25,f26,f27,f28,f29," +
                "f30,f31,f32,f33,f34,f35,f36,f37,f38,f39," +
                "f40,f41,f42,f43,f44,f45,f46,f47,f48,f49," +
                "f50,f51,f52,f53,f54,f55,f56,f57,f58,f59," +
                "f60,f61,f62,f63,f64,f65,f66,f67,f68,f69," +
                "f70,f71,f72,f73,f74,f75,f76,f77,f78,f79," +
                "f80,f81,f82,f83,f84,f85,f86,f87,f88,f89," +
                "f90,f91,f92,f93,f94,f95,f96,f97,f98,f99," +
                "f100,f101,f102,f103,f104,f105,f106,f107,f108,f109" + "," +
                "f110,f111,f112,f113,f114,f115,f116,f117,f118,f119" + "," +
                "f120,f121,f122,f123,f124,f125,f126,f127,f128,f129" + "," +
                "f130,f131,f132,f133,f134,f135,f136,f137,f138,f139" + "," +
                "f140,f141,f142,f143,f144,f145,f146,f147,f148,f149" + "," +
                "f150,f151,f152,f153,f154,f155,f156,f157,f158,f159" + "," +
                "f160,f161,f162,f163,f164,f165,f166,f167,f168,f169" + "," +
                "f170,f171,f172,f173,f174,f175,f176,f177,f178,f179" + "," +
                "f180,f181,f182,f183,f184,f185,f186,f187,f188,f189" + "," +
                "f190,f191,f192,f193,f194,f195,f196,f197,f198,f199" + "," +
                "f200,f201,f202,f203,f204,f205,f206,f207,f208,f209" + "," +
                "f210,f211,f212,f213,f214,f215,f216,f217,f218,f219" + "," +
                "f220,f221,f222,f223,f224,f225,f226,f227,f228,f229" +
//                "f230,f231,f232,f233,f234,f235,f236,f237,f238,f239" +
//                "f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222" +
                "&_=" + curTime);
//        System.out.println(url + "?" + urlParam.toString());
        String rs = "";
        JSONObject rsJsonObj = null;
        for (int i = 0; i < 10; i++) {
            rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);//返回结果
            if (rs.startsWith("jQuery")) {
                rs = rs.substring(rs.indexOf("{"));
            }
            if (rs.endsWith(");")) {
                rs = rs.substring(0, rs.length() - 2);
            }
            rsJsonObj = JSONObject.parseObject(rs);
            if (rs == null || rsJsonObj == null || !rsJsonObj.containsKey("data")) {
                System.out.println("查询数据异常，重新查询：" + rs);
                continue;
            } else {
                break;
            }
        }
//        System.out.println(rs);//返回结果

        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        try {
            JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
            List<RankBizDataDiff> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankBizDataDiff.class);
            for (RankBizDataDiff row : rankBizDataDiffList) {
//            row.setRs(rs);
                row.setDate(date);
                row.setMonth(DateUtil.getYearMonth(date, DateUtil.YYYY_MM_DD));
                row.setWeekYear(DateUtil.getYearWeek(date, DateUtil.YYYY_MM_DD));
                row.setWeek(DateUtil.getWeekByYyyyMmDd(date, DateUtil.YYYY_MM_DD));
                row.setType(type);
//            System.out.println(JSON.toJSON(row));//每个行业一行数据
            }
            return rankBizDataDiffList;
        } catch (Exception e) {
            System.out.println("Exception:" + rs);
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 查询业务-根据条件：编码、日期、业务类型
     * @param zqdm 编码
     * @param date 日期
     * @param type 类型
     * @return 查询结果
     */
    public static RankBizDataDiff findBiz(String zqdm, String date, String type) {
        RankBizDataDiff condition = new RankBizDataDiff();
        condition.setF12(zqdm);
        condition.setDate(date);
        return BizRankDao.findBiz(condition);
    }

    /**
     * 查询昨日主题排名
     */
    public static List<RankStockCommpanyDb> listRankStockByBiz(int pageSize, String biz) {
        //http://push2.eastmoney.com/api/qt/clist/get?cb=jQuery112307730222083783287_1617467610779&fid=f62&po=1&pz=50&pn=1&np=1&fltt=2&invt=2&ut=b2884a393a59ad64002292a3e90d46a5&fs=b%3ABK0891&fields=f12%2Cf14%2Cf2%2Cf3%2Cf62%2Cf184%2Cf66%2Cf69%2Cf72%2Cf75%2Cf78%2Cf81%2Cf84%2Cf87%2Cf204%2Cf205%2Cf124
        StringBuffer urlParam = new StringBuffer();
        long curTime = System.currentTimeMillis();
        String url = "http://push2.eastmoney.com/api/qt/clist/get";
        urlParam.append("cb=jQuery112307730222083783287_" + curTime +
                "&pn=1" +//页数
                "&pz=" + pageSize +//每页数量
                "&po=1" +//pageorder:页面排序：0-正序；1-倒序
                "&np=1" +
                "&ut=b2884a393a59ad64002292a3e90d46a5" +
                "&fltt=2" +//浮点数精度
                "&invt=3" +//显示格式：-；0.0
//                "&fid=f20" +//排序字段：f20:总市值
                "&fid=f3" +//排序字段：f3:涨跌幅
                "&fs=b:" + biz +
                //fields: f12,f14,f2,f3,f62,f184,f66,f69,f72,f75,f78,f81,f84,f87,f204,f205,f124
                "&fields=" +
                "f1,f2,f3,f4,f5,f6,f7,f8,f9," +
                "f10,f11,f12,f13,f14,f15,f16,f17,f18,f19," +
                "f20,f21,f22,f23,f24,f25,f26,f27,f28,f29," +
                "f30,f31,f32,f33,f34,f35,f36,f37,f38,f39," +
                "f40,f41,f42,f43,f44,f45,f46,f47,f48,f49," +
                "f50,f51,f52,f53,f54,f55,f56,f57,f58,f59," +
                "f60,f61,f62,f63,f64,f65,f66,f67,f68,f69," +
                "f70,f71,f72,f73,f74,f75,f76,f77,f78,f79," +
                "f80,f81,f82,f83,f84,f85,f86,f87,f88,f89," +
                "f90,f91,f92,f93,f94,f95,f96,f97,f98,f99," +
                "f100,f101,f102,f103,f104,f105,f106,f107,f108,f109" + "," +
                "f110,f111,f112,f113,f114,f115,f116,f117,f118,f119" + "," +
                "f120,f121,f122,f123,f124,f125,f126,f127,f128,f129" + "," +
                "f130,f131,f132,f133,f134,f135,f136,f137,f138,f139" + "," +
                "f140,f141,f142,f143,f144,f145,f146,f147,f148,f149" + "," +
                "f150,f151,f152,f153,f154,f155,f156,f157,f158,f159" + "," +
                "f160,f161,f162,f163,f164,f165,f166,f167,f168,f169" + "," +
                "f170,f171,f172,f173,f174,f175,f176,f177,f178,f179" + "," +
                "f180,f181,f182,f183,f184,f185,f186,f187,f188,f189" + "," +
                "f190,f191,f192,f193,f194,f195,f196,f197,f198,f199" + "," +
                "f200,f201,f202,f203,f204,f205,f206,f207,f208,f209" + "," +
                "f210,f211,f212,f213,f214,f215,f216,f217,f218,f219" + "," +
                "f220,f221,f222,f223,f224,f225,f226,f227,f228,f229" +
                "");
//        System.out.println(url + "?" + urlParam.toString());
        String rs = "";
        JSONObject rsJsonObj = null;
        for (int i = 0; i < 10; i++) {
            rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);//返回结果
            if (rs.startsWith("jQuery")) {
                rs = rs.substring(rs.indexOf("{"));
            }
            if (rs.endsWith(");")) {
                rs = rs.substring(0, rs.length() - 2);
            }
            rsJsonObj = JSONObject.parseObject(rs);
            if (rs == null || rsJsonObj == null || !rsJsonObj.containsKey("data")) {
                System.out.println("查询数据异常，重新查询：" + rs);
                continue;
            } else {
                break;
            }
        }
//        System.out.println(rs);//返回结果

        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        try {
            JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
            List<RankStockCommpanyDb> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankStockCommpanyDb.class);
            return rankBizDataDiffList;
        } catch (Exception e) {
            System.out.println("Exception:" + rs);
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取业务列表-全部板块
     * @return 获取业务列表-全部板块
     */
    public static Map<String, List<String>> getBizListAll() {
        Map<String, List<String>> bkMap = new HashMap<>();
        List<String> bizList = null;//
        //        //全部板块
//        List<RankBizDataDiff> bkList = StockService.listBiz(NUM_MAX_99);
        Set<String> bkList = BOARD_NAME_CODE.keySet();
        for (String bk : bkList) {
            bizList = new ArrayList<>();
            bizList.add(bk);
            bkMap.put(bk, bizList);
        }
        return bkMap;
    }
}
