package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import ttjj.dao.StockTradeDao;
import ttjj.dao.TradeStockDao;
import ttjj.dao.impl.TradeStockDaoImpl;
import ttjj.db.StockTradeDb;
import ttjj.dto.FundTrade;
import ttjj.dto.LsjzDataLsjz;
import ttjj.dto.StockTrade;
import utils.Content;
import utils.DateUtil;
import utils.HttpUtil;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chenzhilong
 * @date 2020/8/4
 */
public class StockTradeDemo {
//    static String type_selling = "证券买入(卖出中)";
    public static String COOKIE_DFCF = "__guid=260925462.4161440383634452500.1615302736826.6602; eastmoney_txzq_zjzh=NTQwODIwMTc0NTY5fA%3D%3D; st_si=14344864504935; st_pvi=68959131305862; st_sp=2021-04-02%2023%3A27%3A59; st_inirUrl=https%3A%2F%2Fjywg.18.cn%2FSearch%2FFundsFlow; st_sn=1; st_psi=20210928155050916-11923323313501-9442771456; st_asi=delete; Yybdm=5408; Uid=fNUE23lwQOlyHFRjGcQYdA%3d%3d; Khmc=%e9%99%88%e5%bf%97%e9%be%99; mobileimei=724a50b9-c445-4e72-9caf-00c0c9e85f39; Uuid=7e0480ad6a7d4484925ee0d51a3924ff; monitor_count=3";

    public static void main(String[] args) {
//        boolean tradIng = true;//"盘中交易中"
        boolean tradIng = false;//"盘后交易结束"

        boolean showBuyOrSell = false;//新增赎回
        boolean sellDbFlag = false;//赎回
        int showTypeNet = 1;//最新一天
        if (!tradIng) {
            showBuyOrSell = true;//新增赎回
            sellDbFlag = true;//赎回
            showTypeNet = 365;//最新一年内
        }

            String startDate = "2021-09-24";//查询新增交易的开始时间
//        String startDate = DateUtil.getToday(DateUtil.YYYY_MM_DD);
        String endDate = DateUtil.getCurDateStrAddDaysByFormat(DateUtil.YYYY_MM_DD,10);
//            String endDate = "2021-03-19";
        if (showBuyOrSell) {
            //显示插入数据库语句
            String bizTypeBuy = "1";//0-全部;1-申购;2-卖出;
            String insertStartDate = startDate;//查询新增交易的开始时间
            String insertEndDate = endDate;//查询新增交易的结束时间
            showInsertDb(COOKIE_DFCF, "", insertStartDate, insertEndDate, bizTypeBuy);
        }

        if (sellDbFlag) {
            //  赎回
            String sellStartDate = startDate;
            String sellEndDate = endDate;
            showDbRedem(COOKIE_DFCF, "", sellStartDate, sellEndDate);
        }

        //更新题材概念
        if (showBuyOrSell) {
//            List<StockTrade> stockTradeList = listMyStock();//查询我的列表
//            updateConception(stockTradeList);
        }

        List<StockTrade> myStockTradeList = listMyStock();//查询我的列表
        if (showTypeNet == 1) {
            // 更新最新净值-限定时间段的最大最小净值
            showUpdateDbMaxMinNetByDays(myStockTradeList, 1, "LAST_NET", "LAST_NET", "LAST_NET", "LAST_NET");
            showUpdateDbMaxMinNetByDays(myStockTradeList, 1, "NET_MIN_1", "NET_MAX_1", "NET_MIN_CLOS_1", "NET_MAX_CLOS_1");
        }

        if (showTypeNet == 365) {
            // 更新最新净值-限定时间段的最大最小净值
            showUpdateDbMaxMinNetByDays(myStockTradeList, 1, "LAST_NET", "LAST_NET", "LAST_NET", "LAST_NET");
            showUpdateDbMaxMinNetByDays(myStockTradeList, 1, "NET_MIN_1", "NET_MAX_1", "NET_MIN_CLOS_1", "NET_MAX_CLOS_1");
            showUpdateDbMaxMinNetByDays(myStockTradeList, 7, "NET_MIN_7", "NET_MAX_7", "NET_MIN_CLOS_7", "NET_MAX_CLOS_7");
            showUpdateDbMaxMinNetByDays(myStockTradeList, 14, "NET_MIN_14", "NET_MAX_14", "NET_MIN_CLOS_14", "NET_MAX_CLOS_14");
            showUpdateDbMaxMinNetByDays(myStockTradeList, 30, "NET_MIN_30", "NET_MAX_30", "NET_MIN_CLOS_30", "NET_MAX_CLOS_30");
            showUpdateDbMaxMinNetByDays(myStockTradeList, 60, "NET_MIN_60", "NET_MAX_60", "NET_MIN_CLOS_60", "NET_MAX_CLOS_60");
            showUpdateDbMaxMinNetByDays(myStockTradeList, 90, "NET_MIN_90", "NET_MAX_90", "NET_MIN_CLOS_90", "NET_MAX_CLOS_90");
            showUpdateDbMaxMinNetByDays(myStockTradeList, 180, "NET_MIN_180", "NET_MAX_180", "NET_MIN_CLOS_180", "NET_MAX_CLOS_180");
            showUpdateDbMaxMinNetByDays(myStockTradeList, 365, "NET_MIN_360", "NET_MAX_360", "NET_MIN_CLOS_360", "NET_MAX_CLOS_360");
        }
    }

    /**
     * 更新题材概念
     *
     * @param stockTradeList
     */
    private static void updateConception(List<StockTrade> stockTradeList) {
        for (StockTrade stockInfo : stockTradeList) {
            String stCode = stockInfo.getZqdm();
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
                System.out.println("/**rs:" + rs + "**/");
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
            sb.append("UPDATE `stock_trade` ");
            sb.append("SET ");
            sb.append(" `conception`='" + ydnr + "' ");
            sb.append(" WHERE `FD_CODE`='" + stCode + "'");
            sb.append(";");
//            sb.append("/**" + rsJo.getString("hxtc") + "**/");

            System.out.println(sb);

            //db-更新要点内容
            StockTradeDb entity = new StockTradeDb();
            entity.setFD_CODE(stCode);
            entity.setConception(ydnr);//要点内容
            StockTradeDao.updateByCode(entity);
        }
    }

    /**
     * 显示插入数据库语句
     *
     * @param
     */
    private static void showInsertDb(String cookie, String validatekey, String startDate, String endDate, String busType) {
        TradeStockDao tradeService = new TradeStockDaoImpl();
        List<StockTrade> rs = tradeService.findMyStockTrade(cookie, startDate, endDate, validatekey);
//        System.out.println("findMyTrade:" + JSON.toJSON(rs));
        for (StockTrade trade : rs) {
            //"ywsm": "证券买入",
            String type = "证券买入";
            if (type.equals(trade.getMmsm())) {
                //处理业务类型
                handlerBizTp(trade);

                Date tradeTimeDate = null;
                try {
                    tradeTimeDate = new SimpleDateFormat("yyyyMMdd HHmmss").parse(trade.getCjrq() + " " + trade.getCjsj());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String tradeTimeDateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tradeTimeDate);

                //显示插入数据库语句
                System.out.println("INSERT INTO `bank19`.`stock_trade`(" +
                        " `FD_CODE`,`FD_INFO`, `TYPE`, `TRADE_TIME`, `ORDER_STATUS`, " +
                        " `CONFIRM_SHARE`, `CONFIRM_NET`, `ORDER_AMT`, `STATUS`, `ORDER_CODE`," +
                        " `CONFIRM_AMT`, `REDEM_AMT`, `EARN_AMT`, `CONFIRM_NET_DATA`, `SERVER_CHARGE`, " +
                        " `REDEM_STATUS`, `REDEM_SHARE`, `REDEM_TIME`,`SOURCE`,  `CREATE_TIME`," +
                        " `UPDATE_TIME` ,`BIZ_TP` ,`RK_ST_LOSS`,`RK_ST_PROFIT`" +
                        ") VALUES (" +
                        " '" + trade.getZqdm() + "'" +
                        ", '" + trade.getZqmc() + "'" +
                        ", '" + type
                        + "', '" + tradeTimeDateStr
                        + "', '" + type + "'" +
                        ", " + trade.getCjsl() + "" +
                        ", " + trade.getCjjg()
                        + ", " + trade.getCjje()
                        + ", " + "'确认成功', "
//                        +"  '', " + fundTrade.getFsje()
                        + "  '', " + trade.getCjje()
                        + ", 0, " +
                        " 0, '" + tradeTimeDateStr + "', " + trade.getSxf() + ", " +
                        " '0', 0,  '3000-01-01 00:00:00',  " +
                        " '4', now(), now()" +
                        " ,'" + (trade.getBizTy() == null ? "" : trade.getBizTy()) + "'" +
                        " ," + trade.getRiskStLoss() + "" +
                        " ," + trade.getRiskStProfit() + "" +
//                        " ,0.92,1.1" +
                        ");");

//                        " `UPDATE_TIME` ,`BIZ_TP` ,`RK_ST_LOSS`,`RK_ST_PROFIT`" +
                //db-更新要点内容
                StockTradeDb entity = new StockTradeDb();
                entity.setFD_CODE(trade.getZqdm());
                entity.setFD_INFO(trade.getZqmc());
                entity.setTYPE(type);
                entity.setTRADE_TIME(tradeTimeDateStr);
                entity.setORDER_STATUS(type);
                entity.setCONFIRM_SHARE(Double.valueOf(trade.getCjsl()));
                entity.setCONFIRM_NET(Double.valueOf(trade.getCjjg()));
                entity.setORDER_AMT(Double.valueOf(trade.getCjje()));
                entity.setSTATUS("确认成功");
                entity.setORDER_CODE("");
                entity.setCONFIRM_AMT(Double.valueOf(trade.getCjje()));
                entity.setREDEM_AMT(null);
                entity.setEARN_AMT(null);
                entity.setCONFIRM_NET_DATA(tradeTimeDateStr);
                entity.setSERVER_CHARGE(Double.valueOf(trade.getSxf()));
                entity.setREDEM_STATUS("0");//未赎回
                entity.setREDEM_SHARE(null);
                entity.setREDEM_TIME(null);
                entity.setSOURCE("4");//来源：东财
                entity.setCREATE_TIME(tradeTimeDateStr);
                entity.setUPDATE_TIME(tradeTimeDateStr);
                entity.setBIZ_TP(trade.getBizTy());
                entity.setRK_ST_LOSS(trade.getRiskStLoss());
                entity.setRK_ST_PROFIT(trade.getRiskStProfit());
                StockTradeDao.insertDb(entity);
//                System.out.println("未执行插入数据库操作！！！");
            }
        }
    }

    /**
     * 处理业务类型
     *
     * @param fundTrade
     */
    private static void handlerBizTp(StockTrade fundTrade) {
        double baseRiskStLoss = 0.95;
        double baseRiskStProfit = 1.15;

        List<StockTrade> rs = listMyStock();
        for (StockTrade stockTrade : rs) {
            if (stockTrade.getZqdm().equals(fundTrade.getZqdm())) {
                fundTrade.setBizTy(stockTrade.getBizTy());
                fundTrade.setRiskStLoss(baseRiskStLoss);
                fundTrade.setRiskStProfit(baseRiskStProfit);
                return;
            }
        }
//
//        //传媒
//        List<String> typeListChuanMei = new ArrayList<>();
//        typeListChuanMei.add("002027");//分众传媒
//        if (typeListChuanMei.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("传媒");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }
//
//        //玻璃陶瓷
//        List<String> typeListBltc = new ArrayList<>();
//        typeListBltc.add("600176");//中国巨石
//        if (typeListBltc.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("玻璃陶瓷");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }
//
//        //工程建设
//        List<String> typeListGongCheng = new ArrayList<>();
//        typeListGongCheng.add("601668");//中国建筑
//        typeListGongCheng.add("002271");//东方雨虹-水泥建材
//        if (typeListGongCheng.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("工程建设");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }
//
//        //旅游酒店
//        List<String> typeListLvYouJiuDian = new ArrayList<>();
//        typeListLvYouJiuDian.add("600138");//中青旅
//        typeListLvYouJiuDian.add("600258");//首旅酒店
//        typeListLvYouJiuDian.add("600115");//东方航空-民航机场
//        if (typeListLvYouJiuDian.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("旅游酒店");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }
//
//        //科技
//        List<String> typeListKeJi = new ArrayList<>();
//        typeListKeJi.add("515050");//5GETF
//        typeListKeJi.add("159995");//芯片ETF
//        typeListKeJi.add("002202");//金风科技
//        typeListKeJi.add("600517");//国网英大
//        typeListKeJi.add("159813");//芯片
//        typeListKeJi.add("600703");//三安光电
//        typeListKeJi.add("513330");//恒生互联
//        if (typeListKeJi.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("科技");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }
//
//        //环保
//        List<String> typeListHuanBao = new ArrayList<>();
//        typeListHuanBao.add("600217");//中再资环
//        typeListHuanBao.add("002340");//格林美
//        if (typeListHuanBao.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("环保");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }
//
//
//
//        List<String> typeListJunGong = new ArrayList<>();
//        typeListJunGong.add("512560");//中证军工
//        if (typeListJunGong.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("军工");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }
//
//        List<String> typeListQiHuo = new ArrayList<>();
//        typeListQiHuo.add("159985");//豆粕ETF
//        if (typeListQiHuo.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("期货");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }
//
//        List<String> typeListJinRong = new ArrayList<>();
//        typeListJinRong.add("601555");//东吴证券
//        typeListJinRong.add("501025");//香港银行
//        if (typeListJinRong.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("金融");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }
//
//        List<String> typeListYiLiao = new ArrayList<>();
//        typeListYiLiao.add("159929");//医药ETF
//        if (typeListYiLiao.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("医药");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }

//        List<String> typeListXiaoFei = new ArrayList<>();
//        typeListXiaoFei.add("005621|中欧品质消费股票C");
//        typeListXiaoFei.add("161810|银华内需精选混合(LOF)");
//        typeListXiaoFei.add("110022|易方达消费行业股票");
//        typeListXiaoFei.add("161725|招商中证白酒指数(LOF)");
//        typeListXiaoFei.add("006308|汇添富全球消费混合人民币A");
//        if (typeListXiaoFei.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("消费");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }


//        List<String> typeListYouSe = new ArrayList<>();
//        typeListYouSe.add("165520|信诚中证800有色指数(LOF)");
//        typeListYouSe.add("002207|前海开源金银珠宝混合C");
//        if (typeListYouSe.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("有色");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }


//        List<String> typeListNongYe = new ArrayList<>();
//        typeListNongYe.add("001027|前海开源中证大农业指数增强");
//        if (typeListNongYe.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("农业");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }

//        //混合
//        List<String> typeListHunHe = new ArrayList<>();
//        typeListHunHe.add("519212|万家宏观择时多策略混合");
//        if (typeListHunHe.contains(fundTrade.getZqdm())) {
//            fundTrade.setBizTy("混合");
//            fundTrade.setRiskStLoss(baseRiskStLoss);
//            fundTrade.setRiskStProfit(baseRiskStProfit);
//            return;
//        }
    }

    /**
     * 查询我的股票列表
     */
    private static List<StockTrade> listMyStock() {
        List<StockTrade> rs = new ArrayList<>();
        double baseRiskStLoss = 0.95;
        double baseRiskStProfit = 1.15;

        //指数
        List<String> typeListZhiShu = new ArrayList<>();
        typeListZhiShu.add("510050");//50ETF
        typeListZhiShu.add("510310");//HS300ETF
        typeListZhiShu.add("159982");//中证500
        typeListZhiShu.add("588080");//科创板50
        typeListZhiShu.add("159949");//创业板50
        typeListZhiShu.add("159929");//医药ETF
        typeListZhiShu.add("512660");//军工ETF
        typeListZhiShu.add("512690");//酒ETF
        typeListZhiShu.add("510500");//500ETF
        typeListZhiShu.add("159880");//有色50
        typeListZhiShu.add("512000");//券商ETF
        typeListZhiShu.add("512170");//医疗ETF
        typeListZhiShu.add("515030");//新汽车
        typeListZhiShu.add("515790");//光伏ETF
        typeListZhiShu.add("515220");//煤炭ETF
        typeListZhiShu.add("159782");//双创50
        typeListZhiShu.add("516780");//稀土ETF
        typeListZhiShu.add("515290");//银行
        typeListZhiShu.add("159852");//软件ETF
        typeListZhiShu.add("516110");//汽车ETF
//        typeListZhiShu.add("510880");//红利ETF
//        typeListZhiShu.add("159825");//农业ETF
//        typeListZhiShu.add("515250");//智能汽车
//        typeListZhiShu.add("513050");//中概互联
//        typeListZhiShu.add("513550");//港股通50
//        typeListZhiShu.add("160416");//石油基金
//        typeListZhiShu.add("159915");//创业板
//        typeListZhiShu.add("588090");//科创板
        for (String zqdm : typeListZhiShu) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("指数");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }


        //食品饮料
        List<String> typeListSpyl = new ArrayList<>();
//        typeListSpyl.add("000895");//双汇发展
        for (String zqdm : typeListSpyl) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("食品饮料");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //材料
        List<String> typeListClhy = new ArrayList<>();
        typeListClhy.add("601012");//隆基股份
        typeListClhy.add("002129");//中环股份
        for (String zqdm : typeListClhy) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("材料行业");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //科技
        List<String> typeListKeJi = new ArrayList<>();
//        typeListKeJi.add("515050");//5GETF
        typeListKeJi.add("159995");//芯片ETF
        typeListKeJi.add("159813");//芯片
        typeListKeJi.add("513330");//恒生互联
        for (String zqdm : typeListKeJi) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("科技");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        List<String> typeListDzyj = new ArrayList<>();
//        typeListDzyj.add("000725");//京东方Ａ
        typeListDzyj.add("600703");//三安光电
//        typeListDzyj.add("002475");//立讯精密
        for (String zqdm : typeListDzyj) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("电子元件");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        List<String> typeListTxhy = new ArrayList<>();
//        typeListTxhy.add("600745");//闻泰科技
        for (String zqdm : typeListTxhy) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("通讯行业");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //电子信息
        List<String> typeListDzxx = new ArrayList<>();
//        typeListDzxx.add("003040");//楚天龙
//        typeListDzxx.add("000997");//新 大 陆
//        typeListDzxx.add("002177");//御银股份
//        typeListDzxx.add("002152");//广电运通
        for (String zqdm : typeListDzxx) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("电子信息");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //输配电气
        List<String> typeListSpdq = new ArrayList<>();
//        typeListSpdq.add("002202");//金风科技
//        typeListSpdq.add("600517");//国网英大
        for (String zqdm : typeListSpdq) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("输配电气");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //软件服务
        List<String> typeListRuanJianFuWu = new ArrayList<>();
//        typeListRuanJianFuWu.add("002230");//科大讯飞
//        typeListRuanJianFuWu.add("002987");//京北方
        for (String zqdm : typeListRuanJianFuWu) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("软件服务");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //家电行业
        List<String> typeListJdhy = new ArrayList<>();
//        typeListJdhy.add("000651");//格力电器
//        typeListJdhy.add("600690");//海尔智家
        for (String zqdm : typeListJdhy) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("家电行业");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }


        List<String> typeListYiLiao = new ArrayList<>();
//        typeListYiLiao.add("600085");//同仁堂
//        typeListYiLiao.add("002773");//康弘药业
//        typeListYiLiao.add("000538");//云南白药
        for (String zqdm : typeListYiLiao) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("医药制造");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //传媒
        List<String> typeListChuanMei = new ArrayList<>();
        typeListChuanMei.add("002027");//分众传媒
        for (String zqdm : typeListChuanMei) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("文化传媒");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //玻璃陶瓷
        List<String> typeListBltc = new ArrayList<>();
//        typeListBltc.add("600176");//中国巨石
//        typeListBltc.add("002162");//悦心健康
        for (String zqdm : typeListBltc) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("玻璃陶瓷");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //贵金属
        List<String> typeListGjs = new ArrayList<>();
//        typeListGjs.add("601899");//紫金矿业
        for (String zqdm : typeListGjs) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("贵金属");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //工程建设
        List<String> typeListGongCheng = new ArrayList<>();
//        typeListGongCheng.add("601668");//中国建筑
//        typeListGongCheng.add("002271");//东方雨虹-水泥建材
        for (String zqdm : typeListGongCheng) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("工程建设");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //水泥建材
        List<String> typeListSnjc = new ArrayList<>();
//        typeListSnjc.add("600585");//水泥建材
        for (String zqdm : typeListSnjc) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("水泥建材");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //旅游酒店
        List<String> typeListLvYouJiuDian = new ArrayList<>();
//        typeListLvYouJiuDian.add("600138");//中青旅
//        typeListLvYouJiuDian.add("600258");//首旅酒店
        for (String zqdm : typeListLvYouJiuDian) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("旅游酒店");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //民航机场
        List<String> typeListMhjc = new ArrayList<>();
//        typeListMhjc.add("600009");//上海机场
//        typeListMhjc.add("600115");//东方航空
//        typeListMhjc.add("601021");//春秋航空
        for (String zqdm : typeListMhjc) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("民航机场");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //港口水运
        List<String> typeListGksy = new ArrayList<>();
//        typeListGksy.add("600018");//上港集团
        for (String zqdm : typeListGksy) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("港口水运");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //化工行业
        List<String> typeListHghy = new ArrayList<>();
//        typeListHghy.add("601216");//君正集团
        for (String zqdm : typeListHghy) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("化工行业");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //环保
        List<String> typeListHuanBao = new ArrayList<>();
//        typeListHuanBao.add("600217");//中再资环
//        typeListHuanBao.add("002340");//格林美
        for (String zqdm : typeListHuanBao) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("环保");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        //环保工程
        List<String> typeListHbgc = new ArrayList<>();
        typeListHbgc.add("003035");//南网能源
        for (String zqdm : typeListHbgc) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("环保工程");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        List<String> typeListJinRong = new ArrayList<>();
//        typeListJinRong.add("601555");//东吴证券
//        typeListJinRong.add("501025");//香港银行
        typeListJinRong.add("000776");//广发证券
        for (String zqdm : typeListJinRong) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("金融");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        List<String> typeListYinHang = new ArrayList<>();
//        typeListYinHang.add("601398");//工商银行
        typeListYinHang.add("600036");//招商银行
        for (String zqdm : typeListYinHang) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("银行");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        List<String> typeListGyzp = new ArrayList<>();
//        typeListGyzp.add("603398");//邦宝益智
        for (String zqdm : typeListGyzp) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("工艺商品");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }


        //酿酒行业
        List<String> typeListNiangJiu = new ArrayList<>();
//        typeListNiangJiu.add("600600");//青岛啤酒
//        typeListNiangJiu.add("000729");//燕京啤酒
        for (String zqdm : typeListNiangJiu) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("酿酒行业");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }

        List<String> typeListJunGong = new ArrayList<>();
        typeListJunGong.add("512560");//中证军工
        for (String zqdm : typeListJunGong) {
            StockTrade stockTradeTemp = new StockTrade();
            stockTradeTemp.setBizTy("军工");
            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
            stockTradeTemp.setZqdm(zqdm);
            rs.add(stockTradeTemp);
        }


//        //汽车行业
//        List<String> typeListQchy = new ArrayList<>();
//        typeListQchy.add("600066");//宇通客车
//        for (String zqdm : typeListQchy) {
//            StockTrade stockTradeTemp = new StockTrade();
//            stockTradeTemp.setBizTy("汽车行业");
//            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
//            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
//            stockTradeTemp.setZqdm(zqdm);
//            rs.add(stockTradeTemp);
//        }

//        //机械行业
//        List<String> typeListJxhy = new ArrayList<>();
//        typeListJxhy.add("605259");//绿田机械
//        for (String zqdm : typeListJxhy) {
//            StockTrade stockTradeTemp = new StockTrade();
//            stockTradeTemp.setBizTy("机械行业");
//            stockTradeTemp.setRiskStLoss(baseRiskStLoss);
//            stockTradeTemp.setRiskStProfit(baseRiskStProfit);
//            stockTradeTemp.setZqdm(zqdm);
//            rs.add(stockTradeTemp);
//        }

        return rs;
    }

    /**
     * 更新最新净值-限定时间段的最大最小净值
     *
     * @param stockTradeList
     * @param days
     * @param dbFieldLastNetMin
     */
    private static void showUpdateDbMaxMinNetByDays(List<StockTrade> stockTradeList, int days, String dbFieldLastNetMin, String dbFieldLastNetMax, String dbFieldLastNetMinClose, String dbFieldLastNetMaxClose) {
        for (StockTrade stockTradeTemp : stockTradeList) {
            //查询 -限定时间段的最大最小净值
//            LsjzUtil.findJzMaxMin(fundTrade.getZqdm(), days);
            //k线
            String klt = "101";//klt=101:日;102:周;103:月;104:3月;105:6月;106:12月
            StockTradeDb entity = kline(stockTradeTemp.getZqdm(), days, klt, dbFieldLastNetMin, dbFieldLastNetMax, dbFieldLastNetMinClose, dbFieldLastNetMaxClose);//沪深300
            StockTradeDao.updateByCode(entity);
        }
    }



    /**
     * 查询-ETF-指数
     *
     * @param zhiShu            指数
     * @param days              数量
     * @param klt               K线周期类型
     * @param dbFieldLastNetMin
     * @param dbFieldLastNetMax
     */
    public static StockTradeDb kline(String zhiShu, int days, String klt, String dbFieldLastNetMin, String dbFieldLastNetMax, String dbFieldLastNetMinClose, String dbFieldLastNetMaxClose) {
        StringBuffer url = new StringBuffer();
        url.append("http://96.push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery331093188916841208381602168987937");
        if (zhiShu.startsWith("5") || zhiShu.startsWith("6") || zhiShu.startsWith("11")|| zhiShu.startsWith("12")) {
            //|| zhiShu.startsWith("000")
            //  110、120开头是可转债
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

        System.out.println("请求url:"+url+ JSON.toJSONString(urlParam));
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
        System.out.println("szKline:" + rsJson);

        List<String> klineList = new ArrayList<String>();
        JSONObject szzzMonthJson = JSON.parseObject(rsJson);
        JSONObject szzzMonthDataJson = JSON.parseObject(szzzMonthJson.getString("data"));
        String name = szzzMonthDataJson.getString("name");
        System.out.println("指数名称："+name);
        JSONArray klines = JSON.parseArray(szzzMonthDataJson.getString("klines"));
        if (klines != null) {
            for (Object kline : klines) {
                String klineStr = (String) kline;
                klineList.add(klineStr);
            }
        }

        Map<String, Double> netRs = handlerMaxJz(klineList);
        Double minJz = netRs.get(Content.keyRsMin);
        Double maxJz = netRs.get(Content.keyRsMax);
        Double netCloseMin = netRs.get(Content.keyRsNetCloseMin);
        Double netCloseMax = netRs.get(Content.keyRsNetCloseMax);

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE `stock_trade` ");
        sb.append("SET ");
        sb.append(" `" + dbFieldLastNetMin + "`=" + minJz + ", ");
        sb.append(" `" + dbFieldLastNetMinClose + "`=" + netCloseMin + ", ");
        sb.append(" `" + dbFieldLastNetMax + "`=" + maxJz + ", ");
        sb.append(" `" + dbFieldLastNetMaxClose + "`=" + netCloseMax + " ");
//        sb.append(" WHERE `FD_CODE`='" + zhiShu + "'" + " AND TYPE = '证券买入'" + ";");
        sb.append(" WHERE `FD_CODE`='" + zhiShu + "'" + "");
        sb.append(";");
//        System.out.println(sb);

        StockTradeDb stockTradeDb = new StockTradeDb();
        stockTradeDb.setFD_CODE(zhiShu);
        if (days == 1) {
            stockTradeDb.setLAST_NET(netCloseMin);
            stockTradeDb.setNET_MIN_1(minJz);
            stockTradeDb.setNET_MIN_CLOS_1(netCloseMin);
            stockTradeDb.setNET_MAX_1(maxJz);
            stockTradeDb.setNET_MAX_CLOS_1(netCloseMax);
        }
        if (days == 7 ||days == 5 ) {
            stockTradeDb.setNET_MIN_7(minJz);
            stockTradeDb.setNET_MIN_CLOS_7(netCloseMin);
            stockTradeDb.setNET_MAX_7(maxJz);
            stockTradeDb.setNET_MAX_CLOS_7(netCloseMax);
        }
        if (days == 14||days == 10 ) {
            stockTradeDb.setNET_MIN_14(minJz);
            stockTradeDb.setNET_MIN_CLOS_14(netCloseMin);
            stockTradeDb.setNET_MAX_14(maxJz);
            stockTradeDb.setNET_MAX_CLOS_14(netCloseMax);
        }
        if (days == 30||days == 20 ) {
            stockTradeDb.setNET_MIN_30(minJz);
            stockTradeDb.setNET_MIN_CLOS_30(netCloseMin);
            stockTradeDb.setNET_MAX_30(maxJz);
            stockTradeDb.setNET_MAX_CLOS_30(netCloseMax);
        }
        if (days == 60) {
            stockTradeDb.setNET_MIN_60(minJz);
            stockTradeDb.setNET_MIN_CLOS_60(netCloseMin);
            stockTradeDb.setNET_MAX_60(maxJz);
            stockTradeDb.setNET_MAX_CLOS_60(netCloseMax);
        }
        if (days == 90) {
            stockTradeDb.setNET_MIN_90(minJz);
            stockTradeDb.setNET_MIN_CLOS_90(netCloseMin);
            stockTradeDb.setNET_MAX_90(maxJz);
            stockTradeDb.setNET_MAX_CLOS_90(netCloseMax);
        }
        if (days == 180) {
            stockTradeDb.setNET_MIN_180(minJz);
            stockTradeDb.setNET_MIN_CLOS_180(netCloseMin);
            stockTradeDb.setNET_MAX_180(maxJz);
            stockTradeDb.setNET_MAX_CLOS_180(netCloseMax);
        }
        if (days == 365) {
            stockTradeDb.setNET_MIN_360(minJz);
            stockTradeDb.setNET_MIN_CLOS_360(netCloseMin);
            stockTradeDb.setNET_MAX_360(maxJz);
            stockTradeDb.setNET_MAX_CLOS_360(netCloseMax);
        }
        return stockTradeDb;
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
        rs.put(Content.keyRsMax, rsMax);
        rs.put(Content.keyRsMin, rsMin);
        rs.put(Content.keyRsNetCloseMin, rsNetCloseMin);
        rs.put(Content.keyRsNetCloseMax, rsNetCloseMax);
        return rs;
    }

    /**
     * 更新最新净值-我自己-手工添加
     *
     * @param cookie
     * @param startDate
     * @param endDate
     * @param busType
     */
    private static void showUpdateDbMy(String cookie, String startDate, String endDate, String busType) {
        List<FundTrade> rs = new ArrayList<FundTrade>();
        FundTrade fundTrade1 = new FundTrade();
        fundTrade1.setFundInfo("160633|鹏华证券分级");
        rs.add(fundTrade1);
//        String fundCode = "";
//        TradeDao tradeService = new TradeDaoImpl();
//        for (int i = 1; i < 20; i++) {
//            List<FundTrade> tempRs = tradeService.findMyTrade(cookie, fundCode, startDate, endDate, busType, i + "");
//            if (tempRs.size() == 0) {
//                break;
//            }
//            rs.addAll(tempRs);
//        }
//        System.out.println("findMyTrade-rs.size():" + JSON.toJSON(rs.size()));
//        System.out.println("findMyTrade:"+JSON.toJSON(rs));
        for (FundTrade fundTrade : rs) {
            String lsjzUrl = "fundCode=" + fundTrade.getFundCode() + "&pageIndex=1&pageSize=100&startDate=" + "" + "&endDate=" + "" + "&_=1558194929451";
            byte[] bytes = "".getBytes();
            LsjzDataLsjz lsjzDataLsjz = HttpUtil.sendPostTtjjLsjzLastOne(lsjzUrl, bytes, new HashMap<String, String>());
//        System.out.println("lsjzDataLsjz:"+JSON.toJSON(lsjzDataLsjz));
            //打印-
            if (lsjzDataLsjz == null) {
                continue;
            }
            System.out.println("UPDATE `ol_fund_trade` " +
                    "SET " +
                    " `LAST_NET`=" + lsjzDataLsjz.getDWJZ() + " " +
                    ",`LAST_DATE`='" + lsjzDataLsjz.getFSRQ() + "' " +
                    "WHERE  `FD_INFO` = '" + fundTrade.getFundInfo() + "' " +
//                        "AND (`TYPE` = '申购' OR `TYPE` = '申购(赎回中)')"+
                    " ;");
        }
    }

    /**
     * 赎回
     *
     * @param cookie
     * @param startDate
     * @param endDate
     */
    private static void showDbRedem(String cookie, String validatekey, String startDate, String endDate) {
        TradeStockDao tradeService = new TradeStockDaoImpl();
        List<StockTrade> stockTradeList = tradeService.findMyStockTrade(cookie, startDate, endDate, validatekey);
        for (StockTrade stockTrade : stockTradeList) {
            String type = "证券卖出";
            if (stockTrade.getMmsm().equals(type)) {
                BigDecimal serverChargeRedem = new BigDecimal(stockTrade.getSxf());//赎回手续费=佣金+交易规费
                BigDecimal enrnAmtSubServerCharge = new BigDecimal(stockTrade.getCjje()).subtract(serverChargeRedem);

                //发生日期时间格式化
                Date tradeTimeDate = null;
                try {
                    tradeTimeDate = new SimpleDateFormat("yyyyMMdd HHmmss").parse(stockTrade.getCjrq() + " " + stockTrade.getCjsj());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String tradeTimeDateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tradeTimeDate);

                StringBuffer sb = new StringBuffer();
                sb.append("UPDATE `stock_trade` " +
//                System.out.println("UPDATE `ol_fund_trade` " +
                        "SET `TYPE`='证券买入(卖出)'");
                if (stockTrade.getCjjg() != null) {
                    sb.append(",`NET_REDEM`=" + stockTrade.getCjjg() + " ");
                }
                if (stockTrade.getCjsl() != null) {
                    sb.append(",`REDEM_SHARE`=" + stockTrade.getCjsl() + " ");
                }
                sb.append(",`REDEM_TIME`='" + tradeTimeDateStr + "' " +
                        ",`REDEM_AMT`=" + stockTrade.getCjje() + " " +
                        ",`SERVER_CHARGE_REDEM`=" + serverChargeRedem + "" +
                        ",`REDEM_STATUS`=1" +
                        ",`EARN_AMT`=ROUND((" + enrnAmtSubServerCharge + "-`CONFIRM_AMT`-`SERVER_CHARGE`) ,2)" +
                        "WHERE  `FD_CODE` = '" + stockTrade.getZqdm() + "' "
                        + "AND (`TYPE` = '证券买入' OR `TYPE` = '证券买入(卖出中)')"
                        + "AND `CONFIRM_SHARE` = '" + stockTrade.getCjsl() + "' " +
                        " LIMIT 1; ");
                System.out.println(sb.toString());

                //db-更新-卖出
                StockTradeDb entity = new StockTradeDb();
                entity.setNET_REDEM(Double.valueOf(stockTrade.getCjjg()));
                entity.setREDEM_SHARE(Double.valueOf(stockTrade.getCjsl()));
                entity.setREDEM_TIME(tradeTimeDateStr);
                entity.setREDEM_AMT(Double.valueOf(stockTrade.getCjje()));
                entity.setSERVER_CHARGE_REDEM(serverChargeRedem);
                entity.setREDEM_STATUS("1");//卖出
                entity.setFD_CODE(stockTrade.getZqdm());
                entity.setEARN_AMT(enrnAmtSubServerCharge);
                entity.setCONFIRM_SHARE(Double.valueOf(stockTrade.getCjsl()));
                StockTradeDao.updateSellOut(entity);//更新-卖出
            }
        }
    }


}
