package utils;


import ttjj.dto.RankBizDataDiff;

import java.util.ArrayList;
import java.util.List;

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
        if (name.equals("标普500") || name.equals("中证500") || name.equals("中证100") || name.equals("上证180") || name.equals("沪深300")|| name.equals("TCL中环")) {
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
}
