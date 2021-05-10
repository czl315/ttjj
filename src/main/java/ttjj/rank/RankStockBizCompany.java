package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ttjj.dao.MyBatisUtils;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.RankBizDataDiff;
import utils.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 排行-行业股票-公司-每日明细
 */
public class RankStockBizCompany {
    /**
     * sqlSessionFactory mybatis
     */
    static SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();
    static String keyRsMin = "rsMin";
    static String keyRsMax = "rsMax";
    static String keyRsNetCloseMin = "keyRsNetCloseMin";
    static String keyRsNetCloseMax = "keyRsNetCloseMax";

    /**
     * @param args
     */
    public static void main(String[] args) {
//        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String today = "20210507";

        Map<String, String> bizMap = new HashMap();

        List<RankBizDataDiff> bizList = listBiz(100);//查询主题排名by时间类型、显示个数

//        boolean flagInsertDb = true;//标识：是否插入数据库
        boolean flagInsertDb = false;//标识：是否插入数据库
        boolean flagUpdateConception = true;//标识：是否更新概念题材
//        boolean flagUpdateConception = false;//标识：是否更新概念题材
        boolean flagUpdateNet = true;//标识：是否更新净值
//        boolean flagUpdateNet = false;//标识：是否更新净值

        int startNum = 2;
        int bizCountLimit = 999;
        int bizCountTemp = 0;
        for (RankBizDataDiff biz : bizList) {
            bizCountTemp++;
            if (bizCountTemp < startNum) {
                System.out.println("已完成:" + biz.getF14() + "," + biz.getF12());
                continue;//已完成
            }
            if (bizCountTemp > bizCountLimit) {
                break;//限定个数中断
            }
//            System.out.println("/**" + JSON.toJSONString(biz) + "**/");
            bizMap.put(biz.getF12(), biz.getF14());
        }
        int stBizCountTemp = 0;
        for (String biz : bizMap.keySet()) {
            stBizCountTemp++;
            System.out.println("-------------------------当前stBizCountTemp：" + stBizCountTemp + "---" + JSON.toJSONString(bizMap));
            List<RankStockCommpanyDb> rankBizDataDiffListBiz = listRankStockByBiz(500, biz);

            if (flagInsertDb) {
                int insertRs = showBizSql(rankBizDataDiffListBiz, biz, bizMap.get(biz), today);//显示业务排行-插入sql
            }

            if (flagUpdateConception) {
                //更新题材概念
                updateConception(today, biz, rankBizDataDiffListBiz);
            }

            if (flagUpdateNet) {
                // 最新周期价格
                showUpdateDbMaxMinNetByDays(today, rankBizDataDiffListBiz, 1, "NET_MIN_1", "NET_MAX_1", "NET_MIN_CLOS_1", "NET_MAX_CLOS_1");
                showUpdateDbMaxMinNetByDays(today, rankBizDataDiffListBiz, 7, "NET_MIN_7", "NET_MAX_7", "NET_MIN_CLOS_7", "NET_MAX_CLOS_7");
                showUpdateDbMaxMinNetByDays(today, rankBizDataDiffListBiz, 14, "NET_MIN_14", "NET_MAX_14", "NET_MIN_CLOS_14", "NET_MAX_CLOS_14");
                showUpdateDbMaxMinNetByDays(today, rankBizDataDiffListBiz, 30, "NET_MIN_30", "NET_MAX_30", "NET_MIN_CLOS_30", "NET_MAX_CLOS_30");
                showUpdateDbMaxMinNetByDays(today, rankBizDataDiffListBiz, 60, "NET_MIN_60", "NET_MAX_60", "NET_MIN_CLOS_60", "NET_MAX_CLOS_60");
//            showUpdateDbMaxMinNetByDays(today, rankBizDataDiffListBiz, 90, "NET_MIN_90", "NET_MAX_90", "NET_MIN_CLOS_90", "NET_MAX_CLOS_90");
//            showUpdateDbMaxMinNetByDays(today, rankBizDataDiffListBiz, 180, "NET_MIN_180", "NET_MAX_180", "NET_MIN_CLOS_180", "NET_MAX_CLOS_180");
                showUpdateDbMaxMinNetByDays(today, rankBizDataDiffListBiz, 365, "NET_MIN_360", "NET_MAX_360", "NET_MIN_CLOS_360", "NET_MAX_CLOS_360");
                for (RankStockCommpanyDb entity : rankBizDataDiffListBiz) {
                    if (entity == null || StringUtils.isBlank(entity.getF12()) || StringUtils.isBlank(entity.getDate())) {
                        System.out.println("实体信息异常，不更新db：" + JSON.toJSONString(entity));
                    } else {
                        updateByCode(entity);
                    }
                }
            }




        }
    }

    /**
     * 查询昨日主题排名
     *
     * @param endCount
     */
    private static List<RankBizDataDiff> listBiz(int endCount) {
        //http://28.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112408110589206747254_1616379873172&pn=1&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:90+t:2+f:!50&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f208,f209,f222&_=1616379873199
        long curTime = System.currentTimeMillis();
        StringBuffer urlParam = new StringBuffer();
//        String url = "http://api.fund.eastmoney.com/ztjj/GetZTJJList";
//        urlParam.append("callback=jQuery311015658602123786958_1591838943711&callbackname=fundData");
//        urlParam.append("&st="+findDateType);//查询类型
//        urlParam.append("&_=1614523183291");
        String url = "http://28.push2.eastmoney.com/api/qt/clist/get";
        urlParam.append("cb=jQuery112408110589206747254_" + curTime +
                "&pn=1" +//页数
                "&pz=" + endCount +//每页数量
                "&po=1" +//pageorder:页面排序：0-正序；1-倒序
                "&np=1" +
                "&ut=bd1d9ddb04089700cf9c27f6f7426281" +
                "&fltt=2" +//浮点数精度
                "&invt=3" +//显示格式：-；0.0
                "&fid=f3" +//排序字段
                "&fs=m:90+t:2+f:!50" +
                "&fields=" +
                "f1,f2,f3,f4,f5,f6,f7,f8,f9," +
                "f10,f11,f12,f13,f14,f15,f16,f17,f18,f19," +
                "f20,f21,f22,f23,f24,f25,f26,f27,f28,f29," +
                "f30,f31,f32,f33,f34,f35,f36,f37,f38,f39," +
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
                "&_=" + curTime);
        String rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);
        if (rs == null) {
            return null;
        }
        if (rs.startsWith("jQuery")) {
            rs = rs.substring(rs.indexOf("{"));
        }
        if (rs.endsWith(");")) {
            rs = rs.substring(0, rs.length() - 2);
        }
//        System.out.println(rs);//返回结果
        JSONObject rsJsonObj = JSONObject.parseObject(rs);
        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
        List<RankBizDataDiff> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankBizDataDiff.class);
//        for (RankBizDataDiff row : rankBizDataDiffList) {
//            System.out.println(JSON.toJSON(row));//每个行业一行数据
//        }
        return rankBizDataDiffList;
    }

    /**
     * 更新题材概念
     *
     * @param today
     * @param biz
     * @param rankBizDataDiffListBiz
     */
    private static void updateConception(String today, String biz, List<RankStockCommpanyDb> rankBizDataDiffListBiz) {
        for (RankStockCommpanyDb stockInfo : rankBizDataDiffListBiz) {
            String stCode = stockInfo.getF12();
            StringBuffer url = new StringBuffer();
            url.append("http://f10.eastmoney.com/CoreConception/CoreConceptionAjax");

            StringBuffer urlParam = new StringBuffer();
            if (stCode.startsWith("5") || stCode.startsWith("6") || stCode.startsWith("9")) {
                urlParam.append("code=SH").append(stCode);
            } else {
                urlParam.append("code=SZ").append(stCode);
            }

//            System.out.println("请求url:" + url + JSON.toJSONString(urlParam));
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

//            System.out.println("rs:" + rs);
            if (rs == null || rs.contains("不合法")) {
                System.out.println("rs:" + rs);
                continue;
            }

            //{"hxtc":[{"zqnm":"--","zqdm":"600733.SH","zqjc":"--","jyscbm":"--","gjc":"所属板块","yd":"1","ydnr":"MSCI中国 北京板块 标准普尔 成渝特区 富时罗素 固态电池 华为概念 机构重仓 汽车芯片 汽车行业 融资融券 无人驾驶 新能源车 预亏预减 中证500"},{"zqnm":"--","zqdm":"600733.SH","zqjc":"--","jyscbm":"--","gjc":"经营范围","yd":"2","ydnr":"设计、研发、销售汽车(含重型货车、大中型客车、轻型越野汽车、轻、微型客货汽车、多功能车、专用车、轿车、电动汽车、混合动力汽车)、汽车配件、机械设备、电器设备、零部件加工设备;汽车装饰;货物进出口、代理进出口、技术进出口;软件开发;技术开发、技术服务、技术咨询、技术转让;设计、制作、代理、发布国内外广告;经济贸易咨询;物业管理。"},{"zqnm":"--","zqdm":"600733.SH","zqjc":"--","jyscbm":"--","gjc":"新能源汽车","yd":"3","ydnr":"作为国家战略新兴产业之一的新能源汽车产业开拓者,北汽蓝谷子公司北京新能源汽车股份有限公司创立于2009年,是我国首家独立运营、首个获得新能源汽车生产资质的企业。北汽新能源从节能环保到电动化、智能化、网联化、共享化为目标的发展过程中实现了三年打基础、三年上水平、三年上规模的各阶段发展目标,自2013年以来连续七年保持国内新能源纯电动乘用车的销量第一。"},{"zqnm":"--","zqdm":"600733.SH","zqjc":"--","jyscbm":"--","gjc":"新能源汽车行业","yd":"4","ydnr":"2019年新能源汽车行业面临三重压力,一是汽车市场持续下行的压力,二是新能源汽车补贴急剧大幅退坡导致市场信心不足产生较大市场波动的压力,三是外资品牌、新势力、国内传统汽车企业纷纷进入新能源汽车行业的竞争压力。同时新能源汽车企业内部面临电动化及智能化技术创新、新一代产品研发、市场占有的扩张压力,新能源企业经营面临巨大挑战,但这是新兴行业螺旋式向上发展的必经阶段。在创新、协调、绿色、开放、共享的新时代发展理念指引下,新能源汽车作为国家战略型新兴产业,在政策保障、技术进步、市场认知度迅速提升、绿色环保等多因素的推动下,新能源汽车保持了快速发展的态势。根据中国汽车工业协会数据,2019年国内新能源汽车销量为120.6万辆,占世界新能源汽车销量的一半以上。世界范围内新能源汽车2019年销量为220万辆,同比增长了近10%,特别是欧洲主要国家随着碳排放限制趋严和新能源补贴增加,新能源汽车渗透率显著提升。国内新能源汽车的发展由探索阶段的政策驱动到现阶段的政策驱动和市场驱动的统筹推进,促使新能源汽车从“有没有”转向“好不好”的高质量发展。"},{"zqnm":"--","zqdm":"600733.SH","zqjc":"--","jyscbm":"--","gjc":"技术第一生产力,体系全面对抗","yd":"5","ydnr":"科学技术是第一生产力,这一论断在新兴行业表现尤为突出。新能源智能汽车首先作为汽车,其技术进步呈现为长期式的、渐进式的、积累式的特点,而电动化加智能化,其技术进步呈现为跃进式的、迭代式的、颠覆式的特点,新能源汽车技术的进步和创新是整体的、协调的、融合的,符合这一特点和要求的新能源汽车技术提升和创新是公司的核心竞争力,是公司发展的第一动力,处于公司的核心位置。公司高度重视产品的智能网联化转型。新能源汽车电动化的本质使汽车从机械能时代进入电能时代,新能源汽车具有交通工具和能源储备装置的双重属性,特别是为可再生能源(风、水、电、太阳能发电)的深度利用广度延伸奠定了基础;新能源汽车的数字化使汽车从电力时代进入信息时代,人们在汽车上拥有了庞大的应用生态;新能源汽车的智能化使汽车从信息时代进入AI时代,新能源汽车发展的技术方向要求新能源汽车企业具有强大完善的研发体系和能力。新能源汽车已从市场保护进入到市场竞争阶段,竞争已从单一产品的简单对抗发展成为研发体系和创新能力的全面对抗。北汽新能源经十年发展目前已建立了新一代新能源整车产品平台开发技术、三电技术和人工智能技术相互依托的协调研发体系和创新能力。"},{"zqnm":"--","zqdm":"600733.SH","zqjc":"--","jyscbm":"--","gjc":"系统化研发能力,完整研发生态","yd":"6","ydnr":"1、四级研发生态,要素整合共享:北汽新能源将研发能力作为企业的核心竞争力,一方面在全球范围内吸纳中外优秀专业人才,另一方面加大研发投入,构建了完整的面向未来的新一代新能源整车研发体系,提升了电池电机电控、智能网联、智能驾驶、新材料、换电储能等领域核心竞争能力。2、平台开发能力,引领高端制造:伴随着新能源汽车三电及智能技术不断积累提升,北汽新能源产品开发经历了单品“油改电”阶段、单品设计开发阶段,现已进入高性能整车模块化平台开发阶段。已建立满足新产品研发特点的整车开发管理流程,保证了新车开发进度、质量、成本、目标的要求,有效控制风险。目前已构建“大、中、小”三大类全新平台搭建,涵盖A00级到B级,轿车、SUV、CROSSOVER等多级别跨车型全面产品类型,平台底盘架构化设计,衍生多款底盘拓展方案,凸显平台车型研发周期短、开发费用相对低、通用化率高等优势。"},{"zqnm":"--","zqdm":"600733.SH","zqjc":"--","jyscbm":"--","gjc":"动力电池研发,突破提升性能","yd":"7","ydnr":"北汽新能源围绕提升电池使用性能布局研发工作,取得了多个领域关键技术的突破,完成了多个新技术在量产产品中的应用,产品竞争力显著提升。一是完成了全球首款乘用车CTP电池应用系统的开发,对于进一步提升新能源汽车的续航里程、安全与成本控制具有明显效果,助力EU5车型竞争力显著提升。二是开发了第三代IBTC智能仿生温控系统并实现批量应用,产品温控速率提升明显。三是开发了FPC集成采集技术并实现批量应用,产品稳定性显著提升。四是完成了第三代智能管理电池系统的开发并实现批量应用,集成度提升,成本降低,稳定性提高。五是完成了长寿命电池系统的开发。六是电池系统集成能力进一步提升,磷酸铁锂电池系统能量密度和新开发的三元电池系统能量密度领先于国内外同电量等级电池产品。七是建立起完善的自主开发能力,在电池系统集成技术、电池性能集成技术、电池安全技术、电池仿真分析技术、电池管理控制技术、电池测试验证技术等领域保持行业领先水平。"}]}
            JSONObject rsJo = JSON.parseObject(rs);
            JSONArray hxtcArray = JSON.parseArray(rsJo.getString("hxtc"));
            if (hxtcArray == null) {
//            System.out.println("klines未查询到："+zhiShu);
                return;
            }
            String ydnr = "";//核心题材-要点板块
            for (int i = 0; i < hxtcArray.size(); i++) {
                JSONObject ssbk = JSON.parseObject(hxtcArray.getString(i));
                String gjc = ssbk.getString("gjc");
                if ("所属板块".equals(gjc)) {
                    ydnr = ssbk.getString("ydnr");
                    break;
                }
            }

            StringBuffer sb = new StringBuffer();
            sb.append("UPDATE `rank_st_biz_com` ");
            sb.append("SET ");
            sb.append(" `conception`='" + ydnr + "' ");
            sb.append(" WHERE `f12`='" + stockInfo.getF12() + "'");
            sb.append(" AND `date`='" + today + "'");
            sb.append(";");
            sb.append("/**" + stockInfo.getF14() + "**/");

            System.out.println(sb);

            //db-更新要点内容
            RankStockCommpanyDb entity = new RankStockCommpanyDb();
            entity.setF12(stockInfo.getF12());
            entity.setDate(today);
            entity.setConception(ydnr);//要点内容
            updateByCode(entity);
        }
    }

    /**
     * 更新最新净值-限定时间段的最大最小净值
     *
     * @param today
     * @param rankBizDataDiffListBiz
     * @param days
     * @param dbFieldLastNetMin
     * @param dbFieldLastNetMinClose
     * @param dbFieldLastNetMaxClose
     */
    private static void showUpdateDbMaxMinNetByDays(String today, List<RankStockCommpanyDb> rankBizDataDiffListBiz, int days, String dbFieldLastNetMin, String dbFieldLastNetMax, String dbFieldLastNetMinClose, String dbFieldLastNetMaxClose) {
        for (RankStockCommpanyDb stockInfo : rankBizDataDiffListBiz) {
            //查询 -限定时间段的最大最小净值
//            LsjzUtil.findJzMaxMin(fundTrade.getZqdm(), days);
            //k线
            String klt = "101";//klt=101:日;102:周;103:月;104:3月;105:6月;106:12月
            RankStockCommpanyDb entity = kline(stockInfo, today, stockInfo.getF12(), days, klt, dbFieldLastNetMin, dbFieldLastNetMax, dbFieldLastNetMinClose, dbFieldLastNetMaxClose);

        }
    }

    /**
     * 查询-ETF-指数
     *
     * @param stockInfo
     * @param today
     * @param zhiShu                 指数
     * @param days                   数量
     * @param klt                    K线周期类型
     * @param dbFieldLastNetMin
     * @param dbFieldLastNetMax
     * @param dbFieldLastNetMinClose
     * @param dbFieldLastNetMaxClose
     */
    public static RankStockCommpanyDb kline(RankStockCommpanyDb stockInfo, String today, String zhiShu, int days, String klt, String dbFieldLastNetMin, String dbFieldLastNetMax, String dbFieldLastNetMinClose, String dbFieldLastNetMaxClose) {
        StringBuffer url = new StringBuffer();
        url.append("http://96.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery331093188916841208381602168987937");
        if (zhiShu.startsWith("5") || zhiShu.startsWith("6")) {
            url.append("&secid=" + "1." + zhiShu);
        } else {
            url.append("&secid=" + "0." + zhiShu);
        }

        url.append("&ut=fa5fd1943c7b386f172d6893dbfba10b");
        url.append("&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5%2Cf6");
        url.append("&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58%2Cf59%2Cf60%2Cf61");
        url.append("&klt=" + klt);
        url.append("&fqt=1");
        url.append("&end=20500101");
        url.append("&lmt=" + days);
        url.append("&_=1602168987942");

        StringBuffer urlParam = new StringBuffer();
//        urlParam.append("&StartDate=").append(startDate);

//        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
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

        String rsJson = rs.substring(rs.indexOf("{"));
        rsJson = rsJson.replace(");", "");
//        System.out.println("szKline:" + rsJson);

        List<String> klineList = new ArrayList<String>();
        JSONObject szzzMonthJson = JSON.parseObject(rsJson);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
//        String name = szzzMonthDataJson.getString("name");
//        System.out.println("指数名称："+name);
        if (szzzMonthDataJson == null || !szzzMonthDataJson.containsKey("klines")) {
//            System.out.println("klines未查询到："+zhiShu);
            return null;
        }
        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        if (klines != null) {
            for (Object kline : klines) {
                String klineStr = (String) kline;
                klineList.add(klineStr);
            }
        }

        Map<String, Double> netRs = handlerMaxJz(klineList);
        Double minJz = netRs.get(keyRsMin);
        Double maxJz = netRs.get(keyRsMax);
        Double netCloseMin = netRs.get(keyRsNetCloseMin);
        Double netCloseMax = netRs.get(keyRsNetCloseMax);

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE `rank_st_biz_com` ");
        sb.append("SET ");
        sb.append(" `" + dbFieldLastNetMin + "`=" + minJz + ", ");
        sb.append(" `" + dbFieldLastNetMinClose + "`=" + netCloseMin + ", ");
        sb.append(" `" + dbFieldLastNetMax + "`=" + maxJz + ", ");
        sb.append(" `" + dbFieldLastNetMaxClose + "`=" + netCloseMax + " ");
//        sb.append(" WHERE `FD_CODE`='" + zhiShu + "'" + " AND TYPE = '证券买入'" + ";");
        sb.append(" WHERE `f12`='" + zhiShu + "'");
        sb.append(" AND `date`='" + today + "'");
        sb.append(";");
        sb.append("/**" + stockInfo.getF14() + "**/");

        System.out.println(sb);

        stockInfo.setDate(today);
        if (days == 1) {
            stockInfo.setNET_MIN_1(minJz);
            stockInfo.setNET_MIN_CLOS_1(netCloseMin);
            stockInfo.setNET_MAX_1(maxJz);
            stockInfo.setNET_MAX_CLOS_1(netCloseMax);
        }
        if (days == 7) {
            stockInfo.setNET_MIN_7(minJz);
            stockInfo.setNET_MIN_CLOS_7(netCloseMin);
            stockInfo.setNET_MAX_7(maxJz);
            stockInfo.setNET_MAX_CLOS_7(netCloseMax);
        }
        if (days == 14) {
            stockInfo.setNET_MIN_14(minJz);
            stockInfo.setNET_MIN_CLOS_14(netCloseMin);
            stockInfo.setNET_MAX_14(maxJz);
            stockInfo.setNET_MAX_CLOS_14(netCloseMax);
        }
        if (days == 30) {
            stockInfo.setNET_MIN_30(minJz);
            stockInfo.setNET_MIN_CLOS_30(netCloseMin);
            stockInfo.setNET_MAX_30(maxJz);
            stockInfo.setNET_MAX_CLOS_30(netCloseMax);
        }
        if (days == 60) {
            stockInfo.setNET_MIN_60(minJz);
            stockInfo.setNET_MIN_CLOS_60(netCloseMin);
            stockInfo.setNET_MAX_60(maxJz);
            stockInfo.setNET_MAX_CLOS_60(netCloseMax);
        }
        if (days == 90) {
            stockInfo.setNET_MIN_90(minJz);
            stockInfo.setNET_MIN_CLOS_90(netCloseMin);
            stockInfo.setNET_MAX_90(maxJz);
            stockInfo.setNET_MAX_CLOS_90(netCloseMax);
        }
        if (days == 180) {
            stockInfo.setNET_MIN_180(minJz);
            stockInfo.setNET_MIN_CLOS_180(netCloseMin);
            stockInfo.setNET_MAX_180(maxJz);
            stockInfo.setNET_MAX_CLOS_180(netCloseMax);
        }
        if (days == 365) {
            stockInfo.setNET_MIN_360(minJz);
            stockInfo.setNET_MIN_CLOS_360(netCloseMin);
            stockInfo.setNET_MAX_360(maxJz);
            stockInfo.setNET_MAX_CLOS_360(netCloseMax);
        }
        return stockInfo;
    }

    /**
     * 计算最大净值、最小净值
     *
     * @return
     */
    private static Map<String, Double> handlerMaxJz(List<String> klineList) {
        Map<String, Double> rs = new HashMap<String, Double>();
        Double rsMax = 0.0;
        Double rsMin = 0.0;
        Double lastDwjz = 0.0;
        Double rsNetCloseMin = 0.0;
        Double rsNetCloseMax = 0.0;
        int curTempInt = 0;
        for (String klineStr : klineList) {
            //  日期，开盘，收盘,最高，最低，成交量，成交额，振幅，涨跌幅，涨跌额，换手率
            //"2020-09-30,3389.74,3218.05,3425.63,3202.34,4906229054,6193724911616.00,6.58,-5.23,-177.63,13.40"
            String[] klineArray = klineStr.split(",");
            String shouPan = klineArray[2];
            String netMax = klineArray[3];
            String netMin = klineArray[4];
            String zhangDie = klineArray[8];
            String chengJiaoE = klineArray[6];
            String curDate = klineArray[0];
//            System.out.print("日期:" + curDate + ",");
//            System.out.print("收盘:" + shouPan + ",");
//            System.out.print("涨跌幅:" + zhangDie + ",\t");
//            System.out.print("开盘:" + klineArray[1] + ",\t");
//            System.out.print("最高:" + klineArray[3] + ",");
//            System.out.print("最低:" + klineArray[4] + ",");
//            System.out.print("成交量:\t" + klineArray[5] + ",\t\t");
//            System.out.print("成交额:\t" + klineArray[6] + ",\t\t");
//            System.out.print("振幅:" + klineArray[7] + ",");
//            System.out.print("涨跌额:" + klineArray[9] + ",");
//            System.out.print("换手率:" + klineArray[10] + ",");
//            System.out.println();

//                    System.out.println(JSON.toJSONString(lsjzDataLsjz));
//            String dwJz = lsjzDataLsjz.getDWJZ();//当晚净值
            String dwJz = shouPan;//累计净值

            if (StringUtils.isBlank(dwJz)) {
                dwJz = "0";
            }
            String fsrq = curDate;
//            System.out.println("fsrq:" + fsrq + ",dwjzLong:" + dwJz);

            Double netMaxDou = Double.valueOf(netMax);
            if (netMaxDou > rsMax) {
                rsMax = netMaxDou;
            }
            Double netMinDou = Double.valueOf(netMin);
            if (netMinDou < rsMin || rsMin == 0.0) {
                rsMin = netMinDou;
            }

            //
            Double dwjzLong = Double.valueOf(dwJz);
            if (dwjzLong > rsNetCloseMax) {
                rsNetCloseMax = dwjzLong;
            }
            if (dwjzLong < rsNetCloseMin || rsNetCloseMin == 0.0) {
                rsNetCloseMin = dwjzLong;
            }
        }
        rs.put(keyRsMax, rsMax);
        rs.put(keyRsMin, rsMin);
        rs.put(keyRsNetCloseMin, rsNetCloseMin);
        rs.put(keyRsNetCloseMax, rsNetCloseMax);
        return rs;
    }

    /**
     * 显示业务排行-插入sql
     *
     * @param rankBizDataDiffList
     * @param queryType
     * @param today
     */
    private static int showBizSql(List<RankStockCommpanyDb> rankBizDataDiffList, String queryType, String typeName, String today) {
        int rs = 0;
        int orderNum = 0;//序号

        for (RankStockCommpanyDb rankBizDataDiff : rankBizDataDiffList) {
            orderNum++;
            //显示插入数据库语句
            {
                System.out.println("INSERT INTO `bank19`.`rank_st_biz_com`(" +
                        "`rs`" +
                        ",`date`" +
                        ",`type`" +
                        ",`type_name`" +
                        ",`order_num`" +
                        ",`f2`" +
                        ",`f3`" +
                        ",`f4`" +
                        ",`f5`" +
                        ",`f6`" +
                        ",`f7`" +
                        ",`f8`" +
                        ",`f9`" +
                        ",`f10`" +
                        ",`f11`" +
                        ",`f12`" +
                        ",`f13`" +
                        ",`f14`" +
                        ",`f15`" +
                        ",`f16`" +
                        ",`f17`" +
                        ",`f18`" +
                        ",`f20`" +
                        ",`f21`" +
                        ",`f22`" +
                        ",`f23`" +
                        ",`f24`" +
                        ",`f25`" +
                        ",`f26`" +
                        ",`f33`" +
                        ",`f62`" +
                        ",`f104`" +
                        ",`f105`" +
                        ",`f107`" +
                        ",`f115`" +
                        ",`f124`" +
                        ",`f128`" +
                        ",`f140`" +
                        ",`f141`" +
                        ",`f136`" +
                        ",`f152`" +
                        ",`f207`" +
                        ",`f208`" +
                        ",`f209`" +
                        ",`f222`" +
                        ") VALUES (" +
                        " '" + JSON.toJSONString(rankBizDataDiff) + "'" +
                        " ,'" + today + "'" +
                        " ,'" + queryType + "'" +
                        " ,'" + typeName + "'" +
                        " ," + orderNum + "" +
                        " ," + rankBizDataDiff.getF2() + "" +
                        " ," + rankBizDataDiff.getF3() + "" +
                        " ," + rankBizDataDiff.getF4() + "" +
                        " ," + rankBizDataDiff.getF5() + "" +
                        " ," + rankBizDataDiff.getF6() + "" +
                        " ," + rankBizDataDiff.getF7() + "" +
                        " ," + rankBizDataDiff.getF8() + "" +
                        " ," + rankBizDataDiff.getF9() + "" +
                        " ," + rankBizDataDiff.getF10() + "" +
                        " ," + rankBizDataDiff.getF11() + "" +
                        " ,'" + rankBizDataDiff.getF12() + "'" +
                        " ," + rankBizDataDiff.getF13() + "" +
                        " ,'" + rankBizDataDiff.getF14() + "'" +
                        " ," + rankBizDataDiff.getF15() + "" +
                        " ," + rankBizDataDiff.getF16() + "" +
                        " ," + rankBizDataDiff.getF17() + "" +
                        " ," + rankBizDataDiff.getF18() + "" +
                        " ," + rankBizDataDiff.getF20() + "" +
                        " ," + rankBizDataDiff.getF21() + "" +
                        " ," + rankBizDataDiff.getF22() + "" +
                        " ,'" + rankBizDataDiff.getF23() + "'" +
                        " ," + rankBizDataDiff.getF24() + "" +
                        " ," + rankBizDataDiff.getF25() + "" +
                        " ,'" + rankBizDataDiff.getF26() + "'" +
                        " ," + rankBizDataDiff.getF33() + "" +
                        " ," + rankBizDataDiff.getF62() + "" +
                        " ," + rankBizDataDiff.getF104() + "" +
                        " ," + rankBizDataDiff.getF105() + "" +
                        " ," + rankBizDataDiff.getF107() + "" +
                        " ,'" + rankBizDataDiff.getF115() + "'" +
                        " ," + rankBizDataDiff.getF124() + "" +
                        " ,'" + rankBizDataDiff.getF128() + "'" +
                        " ,'" + rankBizDataDiff.getF140() + "'" +
                        " ," + rankBizDataDiff.getF141() + "" +
                        " ," + rankBizDataDiff.getF136() + "" +
                        " ," + rankBizDataDiff.getF152() + "" +
                        " ,'" + rankBizDataDiff.getF207() + "'" +
                        " ,'" + rankBizDataDiff.getF208() + "'" +
                        " ," + rankBizDataDiff.getF209() + "" +
                        " ," + rankBizDataDiff.getF222() + "" +
                        ");");
            }

            {
                rankBizDataDiff.setDate(today);
                rankBizDataDiff.setType(queryType);
                rankBizDataDiff.setType_name(typeName);
                rankBizDataDiff.setOrder_num(Long.valueOf(orderNum));

                //db-更新要点内容
                rs = insertDb(rankBizDataDiff);

//                RankStockCommpanyDb entity = new RankStockCommpanyDb();
//                entity.setRs("");
//                entity.setDate(today);
//                entity.setType(queryType);
//                entity.setType_name(typeName);
//                entity.setOrder_num(Long.valueOf(orderNum));
//                entity.setF2(rankBizDataDiff.getF2());
//                entity.setF3(rankBizDataDiff.getF3());
//                entity.setF4(rankBizDataDiff.getF4());
//                entity.setF5(rankBizDataDiff.getF5());
//                entity.setF6(rankBizDataDiff.getF6());
//                entity.setF7(rankBizDataDiff.getF7());
//                entity.setF8(rankBizDataDiff.getF8());
//                entity.setF9(rankBizDataDiff.getF9());
//                entity.setF10(rankBizDataDiff.getF10());
//                entity.setF11(rankBizDataDiff.getF11());
//                entity.setF12(rankBizDataDiff.getF12());
//                entity.setF13(rankBizDataDiff.getF13());
//                entity.setF14(rankBizDataDiff.getF14());
//                entity.setF15(rankBizDataDiff.getF15());
//                entity.setF16(rankBizDataDiff.getF16());
//                entity.setF17(rankBizDataDiff.getF17());
//                entity.setF18(rankBizDataDiff.getF18());
//                entity.setF20(rankBizDataDiff.getF20());
//                entity.setF21(rankBizDataDiff.getF21());
//                entity.setF22(rankBizDataDiff.getF22());
//                entity.setF23(rankBizDataDiff.getF23());
//                entity.setF24(rankBizDataDiff.getF24());
//                entity.setF25(rankBizDataDiff.getF25());
//                entity.setF26(rankBizDataDiff.getF26());
//                entity.setF33(rankBizDataDiff.getF33());
//                entity.setF62(rankBizDataDiff.getF62());
//                entity.setF104(rankBizDataDiff.getF104());
//                entity.setF105(rankBizDataDiff.getF105());
//                entity.setF107(rankBizDataDiff.getF107());
//                entity.setF115(rankBizDataDiff.getF115());
//                entity.setF124(rankBizDataDiff.getF124());
//                entity.setF128(rankBizDataDiff.getF128());
//                entity.setF140(rankBizDataDiff.getF140());
//                entity.setF141(rankBizDataDiff.getF141());
//                entity.setF136(rankBizDataDiff.getF136());
//                entity.setF152(rankBizDataDiff.getF152());
//                entity.setF207(rankBizDataDiff.getF207());
//                entity.setF208(rankBizDataDiff.getF208());
//                entity.setF209(rankBizDataDiff.getF209());
//                entity.setF222(rankBizDataDiff.getF222());

            }

        }
        return rs;
    }

    /**
     * db-插入
     *
     * @param entity
     */
    private static int insertDb(RankStockCommpanyDb entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
//                System.out.println(JSON.toJSONString(entity));
            rs = session.insert("ttjj.dao.mapper.RankStockCommpanyMapper.insert", entity);
            session.commit();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * @param entity
     * @return
     */
    private static int updateByCode(RankStockCommpanyDb entity) {
        SqlSession session = sqlSessionFactory.openSession();
        int rs = 0;
        try {
            rs = session.update("ttjj.dao.mapper.RankStockCommpanyMapper.update", entity);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rs;
    }

    /**
     * 查询昨日主题排名
     */
    private static List<RankStockCommpanyDb> listRankStockByBiz(int pageSize, String biz) {
        //http://push2.eastmoney.com/api/qt/clist/get?cb=jQuery112307730222083783287_1617467610779&fid=f62&po=1&pz=50&pn=1&np=1&fltt=2&invt=2&ut=b2884a393a59ad64002292a3e90d46a5&fs=b%3ABK0891&fields=f12%2Cf14%2Cf2%2Cf3%2Cf62%2Cf184%2Cf66%2Cf69%2Cf72%2Cf75%2Cf78%2Cf81%2Cf84%2Cf87%2Cf204%2Cf205%2Cf124
        StringBuffer urlParam = new StringBuffer();
        long curTime = System.currentTimeMillis();
        String url = "http://push2.eastmoney.com/api/qt/clist/get?";
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
        String rs = HttpUtil.sendGet(url, urlParam.toString(), "");
//        System.out.println(rs);
        if (rs == null) {
            return null;
        }
        if (rs.startsWith("jQuery")) {
            rs = rs.substring(rs.indexOf("{"));
        }
        if (rs.endsWith(");")) {
            rs = rs.substring(0, rs.length() - 2);
        }

        JSONObject rsJsonObj = JSONObject.parseObject(rs);
//        System.out.println(rs);//返回结果
        JSONObject rsJsonData = rsJsonObj.getJSONObject("data");
        JSONArray rsJsonDataDiff = rsJsonData.getJSONArray("diff");
        List<RankStockCommpanyDb> rankBizDataDiffList = JSON.parseArray(JSON.toJSONString(rsJsonDataDiff), RankStockCommpanyDb.class);
//        for (RankBizDataDiff row : rankBizDataDiffList) {
//            System.out.println(JSON.toJSON(row));//每个行业一行数据
//        }
        return rankBizDataDiffList;
    }

}
