package utils;


import org.apache.commons.lang3.StringUtils;
import ttjj.dto.BizDto;
import ttjj.dto.RankBizDataDiff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Content.DB_RANK_BIZ_TYPE_ETF;

/**
 * EtfUtil简介
 *
 * @author Administrator
 * @date 2022-03-07 10:15
 */
public class EtfUtil {
    /**
     * 过滤etf
     *
     * @param bizList 原始数据
     * @param bizType 类型
     * @return 过滤后数据
     */
    public static List<RankBizDataDiff> handlerEtfList(List<RankBizDataDiff> bizList, String bizType) {
        List<RankBizDataDiff> rs = new ArrayList<>();
        if (DB_RANK_BIZ_TYPE_ETF.equals(bizType)) {
            //过滤etf
            for (RankBizDataDiff biz : bizList) {
                String code = biz.getF12();
                if (ContentEtf.mapEtfAll.keySet().contains(code)) {
                    rs.add(biz);
                }
            }
            return rs;
        } else {
            return bizList;
        }
    }


    /**
     * 处理etf名称
     *
     * @param name
     */
    public static String handlerEtfNameSimple(String name) {
        name = name.replace("ETF", "");
        name = name.replace("基金", "");
        name = name.replace("有色金属", "有色");
        name = name.replace("基建50", "基建");
        name = name.replace("能源化工", "能源");
        name = name.replace("中概互联网", "中概");
        return name;
    }

    /**
     * @param name
     */
    public static String handlerEtfName(String name) {
        StringBuffer sb = new StringBuffer();
//        if(name.contains("1000")){
//            System.out.println("特定");
//        }
        name = name.replace("ETF", "");
        sb.append(name);
        if (name.contains("上证50") || (name.contains("基建50") || (name.contains("双创50")))) {
            sb.append("    ");
            return sb.toString();
        }
        if (name.equals("5G") || name.equals("酒")) {
            sb.append("        ");
            return sb.toString();
        }
        if (name.equals("创成长") || name.equals("创成长") || name.equals("1000")) {
            sb.append("    ");
            return sb.toString();
        }
        if (name.equals("标普500") || name.equals("中证500") || name.equals("中证100") || name.equals("上证180") || name.equals("沪深300") || name.equals("TCL中环")) {
            sb.append("   ");
            return sb.toString();
        }
        if (name.equals("创业板50") || name.equals("科创板50")) {
            sb.append("  ");
            return sb.toString();
        }
        if (name.length() == 2) {
            sb.append("      ");
            return sb.toString();
        }
        if (name.length() == 3) {
            sb.append("    ");
            return sb.toString();
        }
        if (name.length() == 4) {
            sb.append("  ");
            return sb.toString();
        }

//        if(name.equals("能源")){//测：
//            System.out.println(name.length());
//        }
        return sb.toString();
    }

    /**
     * 检查是否是主要etf
     *
     * @param zqdm 编码
     * @return 是否是主要etf
     */
    public static boolean isMainEtf(String zqdm) {
        if (ContMapEtf.ETF_All.keySet().contains(zqdm)) {
            return true;
        }
        return false;
    }

    /**
     * 显示头信息
     */
    public static Map<String, Integer> showInfoHead(boolean showMore, boolean isShowCode, boolean isShowBoard, String conception) {
        Map<String, Integer> sizeMap = new HashMap<>();
        String orderNo = "序号";
        sizeMap.put("序号", 5);
        sizeMap.put("名称", 16);
        sizeMap.put("概念", 16);
        sizeMap.put("代码", 8);
        int size = 10;
        int sizeBiz = 14;
        int sizeDate14 = 14;
        StringBuffer sb = new StringBuffer();
        sb.append(StockUtil.formatStName(orderNo, sizeMap.get(orderNo)));
        if (isShowCode) {
            sb.append(StockUtil.formatStName("代码", sizeMap.get("代码")));
        }
        sb.append(StockUtil.formatStName("名称", sizeMap.get("名称")));
        if (isShowBoard) {
            sb.append(StockUtil.formatStName("业务板块", sizeBiz));
        }
        if (StringUtils.isNotBlank(conception)) {
            sb.append(StockUtil.formatStName("概念", sizeMap.get("概念")));
        }
        sb.append(StockUtil.formatStName("区间涨幅", size));
        if (showMore) {
            sb.append(StockUtil.formatStName("最新涨幅", size));
            if (isShowBoard) {
                sb.append(StockUtil.formatStName("市场板块", sizeBiz));
            }
            sb.append(StockUtil.formatStName("最新市值(亿)", sizeDate14));
            sb.append(StockUtil.formatStName("开始日期", sizeDate14));
            sb.append(StockUtil.formatStName("结束日期", sizeDate14));
        }

        System.out.println(sb);
        return sizeMap;
    }


    /**
     * 显示集合
     *
     * @param rsList   列表
     * @param begDate  开始时间
     * @param endDate  结束时间
     * @param limit
     * @param showMore 显示更多字段
     * @param sizeMap
     */
    public static void showInfoEtf(List<BizDto> rsList, String begDate, String endDate, int limit, boolean showMore, boolean isShowCode, Map<String, Integer> sizeMap) {
        if (rsList == null) {
            return;
        }
        int size = 10;
        int sizeDate14 = 14;
        int number = 0;
        for (BizDto dto : rsList) {
            if (limit-- <= 0) {
                break;
            }
            String name = dto.getF14();
            String code = dto.getF12();
//            if (!name.contains("色")) {
//                continue;
//            }
            StringBuffer sb = new StringBuffer();
            sb.append(StockUtil.formatStName(String.valueOf(++number), sizeMap.get("序号")));
            if (isShowCode) {
                sb.append(StockUtil.formatStName(dto.getF12(), sizeMap.get("代码")));
            }
            sb.append(StockUtil.formatStName(name, 16));
            sb.append(StockUtil.formatDouble(dto.getAreaF3(), size, null, "%"));
            if (showMore) {
                sb.append(StockUtil.formatDouble(dto.getF3(), size, null, "%"));
                sb.append(StockUtil.formatDouble(dto.getF20(), sizeDate14));
                sb.append(StockUtil.formatStName(begDate, sizeDate14));
                sb.append(StockUtil.formatStName(endDate, sizeDate14));
//                sb.append(StockUtil.formatDouble(dto.getBegDateF18(), size));
//                sb.append(StockUtil.formatDouble(dto.getEndDateF2(), size));
            }
            System.out.println(sb);
        }
    }

    /**
     * 显示-业务类型
     *
     * @param rsList   列表
     * @param begDate  开始时间
     * @param endDate  结束时间
     * @param limit
     * @param showMore 显示更多字段
     * @param sizeMap
     */
    public static void showInfoEtfType(List<BizDto> rsList, String begDate, String endDate, int limit, boolean showMore, boolean isShowCode, Map<String, Integer> sizeMap) {
        if (rsList == null) {
            return;
        }
        int size = 10;
        int sizeDate14 = 14;
        int number = 0;
        for (BizDto dto : rsList) {
            if (limit-- <= 0) {
                break;
            }
            String name = dto.getF14();
            String code = dto.getF12();

//            if (!name.contains("稀") ) { continue; }
//            if (!name.contains("新能源") && !name.contains("电池")) { continue; }

//            if (ContMapEtfType.ETF_TYPE_ALL.keySet().contains(code)) {

            if (!ContMapEtfType.ZHISHU_MORE.keySet().contains(code)) {
                continue;
            }
            StringBuffer sb = new StringBuffer();
            sb.append(StockUtil.formatStName(String.valueOf(++number), sizeMap.get("序号")));
            if (isShowCode) {
                sb.append(StockUtil.formatStName(dto.getF12(), sizeMap.get("代码")));
            }
            sb.append(StockUtil.formatStName(name, 16));
            sb.append(StockUtil.formatDouble(dto.getAreaF3(), size, null, "%"));
            if (showMore) {
//                sb.append(StockUtil.formatDouble(dto.getF3(), size, null, "%"));
                sb.append(StockUtil.formatDouble(dto.getF20(), sizeDate14));
                sb.append(StockUtil.formatStName(begDate, sizeDate14));
                sb.append(StockUtil.formatStName(endDate, sizeDate14));
//                sb.append(StockUtil.formatDouble(dto.getBegDateF18(), size));
//                sb.append(StockUtil.formatDouble(dto.getEndDateF2(), size));
            }
//            System.out.println("YILIAO_MORE.put(\"" + code + "\", \"" + StockUtil.formatStName(name, 16) + "\");//" + sb);
//            System.out.println("XIAOFEI_MORE.put(\"" + code + "\", \"" + StockUtil.formatStName(name, 16) + "\");//" + sb);
//            System.out.println("KEJI_MORE.put(\"" + code + "\", \"" + StockUtil.formatStName(name, 16) + "\");//" + sb);
//            System.out.println("ZIYUAN_MORE.put(\"" + code + "\", \"" + StockUtil.formatStName(name, 16) + "\");//" + sb);
//            System.out.println("JINRONG_MORE.put(\"" + code + "\", \"" + StockUtil.formatStName(name, 16) + "\");//" + sb);
            System.out.println("ZHISHU_MORE.put(\"" + code + "\", \"" + StockUtil.formatStName(name, 16) + "\");//" + sb);
//            System.out.println(sb);
        }
    }

}
