package utils;

import org.apache.commons.lang3.StringUtils;
import ttjj.db.AssetPositionDb;
import ttjj.db.Fupan;
import ttjj.dto.BizDto;
import ttjj.dto.CondStock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Content.*;

/**
 * @author chenzhilong
 * @date 2022/5/11
 */
public class StockUtil {
    /**
     * @param name
     */
    public static String handlerStName(String name) {
        if (name.length() <= 2) {
            name = name + "    ";
        }
        if (name.length() > 2 && name.length() < 4) {
            name = name + "  ";
        }
        if (name.startsWith("*ST")) {
            name = name + " ";
        }
        if (name.startsWith("ST")) {
            name = name + " ";
        }
        if (name.startsWith("A")) {
            name = name + "  ";
        }
        return name;
    }

    /**
     * 格式化名称
     *
     * @param name
     * @return
     */
    public static String formatName(String name) {
        if (name.length() <= 2) {
            name = name + "    ";
        }
        if (name.length() > 2 && name.length() < 4) {
            name = name + "  ";
        }
        if (name.startsWith("*ST")) {
            name = name + " ";
        }
        if (name.startsWith("ST")) {
            name = name + " ";
        }
        if (name.startsWith("A")) {
            name = name + "  ";
        }
        return name;
    }

    /**
     * 格式化-涨跌幅
     *
     * @param f3
     * @return
     */
    public static String formatDouble(BigDecimal f3) {
        if (f3 == null) {
            return null;
        }
        String f3Str = f3.toString();
        if (f3Str.length() == 3) {
            return f3Str + " ";
        }
        if (f3Str.length() <= 2) {
            return f3Str + "   ";
        }
        return f3Str;
    }

    /**
     * 格式化-数字，根据长度
     *
     * @param number 输入值
     * @param length 长度
     * @return 格式化结果
     */
    public static String formatDouble(BigDecimal number, int length) {
        StringBuffer rs = new StringBuffer();
        if (number == null) {
            for (int i = 0; i < length; i++) {
                rs.append(" ");
            }
            return rs.toString();
        }
        String numberStr = number.toString();
        int kong = length - numberStr.length();
        rs.append(numberStr);
        for (int i = 0; i < kong; i++) {
            rs.append(" ");
        }
        return rs.toString();
    }

    /**
     * 格式化-数字，根据长度，带前缀，后缀
     *
     * @param number 输入值
     * @param length 长度
     * @return 格式化结果
     */
    public static String formatDouble(BigDecimal number, int length, String prefix, String suffix) {
        StringBuffer rs = new StringBuffer();
        if (number == null) {
            for (int i = 0; i < length; i++) {
                rs.append(" ");
            }
            return rs.toString();
        }
        //格式化
        number = number.setScale(2, RoundingMode.HALF_UP);

        String numberStr = number.toString();
        int kong = length - numberStr.length();
        if (prefix != null) {
            kong = kong - prefix.length();
            rs.append(prefix);
        }
        rs.append(numberStr);
        if (suffix != null) {
            kong = kong - suffix.length();
            rs.append(suffix);
        }
        for (int i = 0; i < kong; i++) {
            rs.append(" ");
        }
        return rs.toString();
    }

    /**
     * 格式化-字符串，根据长度
     *
     * @param number
     * @param length
     * @return
     */
    public static String formatStr(String number, int length) {
        StringBuffer rs = new StringBuffer();
        if (number == null) {
            for (int i = 0; i < length; i++) {
                rs.append(" ");
            }
            return rs.toString();
        }
        String numberStr = number;
        int kong = length - numberStr.length();
        rs.append(numberStr);
        for (int i = 0; i < kong; i++) {
            rs.append(" ");
        }
        return rs.toString();
    }

    /**
     * 格式化名称-业务
     *
     * @param name
     * @return
     */
    public static String formatBizName(String name) {
        if (name.length() == 2) {
            name = name + "        ";
        }
        if (name.length() == 3) {
            name = name + "      ";
        }
        if (name.length() == 4) {
            name = name + "    ";
        }
        if (name.length() == 5) {
            name = name + "  ";
        }
        return name;
    }

    /**
     * 格式化名称
     *
     * @param name
     * @param length
     * @return rs
     */
    public static String formatStName(String name, int length) {
        StringBuffer rs = new StringBuffer();
        if (name == null || name.length() == 0) {
            for (int i = 0; i < length; i++) {
                rs.append(" ");
            }
            return rs.toString();
        }
        char[] chars = name.toCharArray();
        for (char aChar : chars) {
            if (isChineseChar(aChar)) {
                length = length - 2;
            } else if (isCharQuanJiao(aChar)) {
                length = length - 2;
            } else {
//                System.out.println("名称非中文或全角：["+aChar+"]");
                length = length - 1;
            }
        }
        rs.append(name);
        for (int i = 0; i < length; i++) {
            rs.append(" ");
        }
        return rs.toString();
    }

    /**
     * 格式化名称-etf
     *
     * @param name
     * @param length
     * @return
     */
    public static String formatEtfName(String name, int length) {
        name = name.replace("ETF", "");
        name = name.replace("易方达", "");
        return formatStName(name, length);
    }

    /**
     * 判断一个字符是否是汉字
     * PS：中文汉字的编码范围：[\u4e00-\u9fa5]
     *
     * @param c 需要判断的字符
     * @return 是汉字(true), 不是汉字(false)
     */
    public static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

    /**
     * 判断是全角字符
     *
     * @param c 字符
     * @return 是否全角字符
     */
    public static boolean isCharQuanJiao(char c) {
        return String.valueOf(c).matches("[^\\x00-\\xff]");
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
     * @param rsList     列表
     * @param board      板块
     * @param begDate    开始时间
     * @param endDate    结束时间
     * @param limit
     * @param showMore   显示更多字段
     * @param sizeMap
     * @param conception
     */
    public static void showInfo(List<CondStock> rsList, Long board, String begDate, String endDate, int limit, boolean showMore, boolean isShowCode, Map<String, Integer> sizeMap, String conception) {
        if (rsList == null) {
            return;
        }
        int size = 10;
        int sizeBiz = 14;
        int sizeDate14 = 14;
        String boardName = handlerBoardName(board);
        int number = 0;
        for (CondStock dto : rsList) {
            if (limit-- <= 0) {
                break;
            }
            StringBuffer sb = new StringBuffer();
            sb.append(StockUtil.formatStName(String.valueOf(++number), sizeMap.get("序号")));
            if (isShowCode) {
                sb.append(StockUtil.formatStName(dto.getF12(), sizeMap.get("代码")));
            }
            sb.append(StockUtil.formatStName(dto.getF14(), sizeMap.get("名称")));
            if (showMore) {
                sb.append(StockUtil.formatStName(dto.getType_name(), sizeBiz));
            }
            if (StringUtils.isNotBlank(conception)) {
                sb.append(StockUtil.formatStName(conception, sizeMap.get("概念")));
            }
            sb.append(StockUtil.formatDouble(dto.getAreaF3(), size, null, "%"));
            if (showMore) {
                sb.append(StockUtil.formatDouble(dto.getF3(), size, null, "%"));
                sb.append(StockUtil.formatStName(boardName, sizeBiz));
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
     * 获取市场板块
     *
     * @param board 板块
     * @return 市场板块名称
     */
    public static String handlerBoardName(Long board) {
        String boardName = "";
        if (board == null) {
            boardName = "全市场";
        } else if (board == DB_RANK_BIZ_F19_BK_MAIN) {
            boardName = "沪深主板";
        }
        return boardName;
    }

    /**
     * 检查是否：XX发债，7XXXXX开头的
     *
     * @param assetPosition
     */
    public static boolean checkIsNewBond(AssetPositionDb assetPosition) {
        if (assetPosition == null) {
            return false;
        }
        String zqdm = assetPosition.getZqdm();
        String zqmc = assetPosition.getZqmc();
        if (zqdm.startsWith("7")) {
            return true;
        }
        if (zqmc.contains("发债")) {
            return true;
        }
        return false;
    }

    /**
     * 获取均线数据
     *
     * @param strHead
     * @param netMap
     * @return
     */
    public static String handlerAvgLine(String strHead, Map<String, BigDecimal> netMap) {
        BigDecimal curPrice = netMap.get(keyRsNetClose);
        BigDecimal minPrice = netMap.get(keyRsMin);
        BigDecimal maxPrice = netMap.get(keyRsMax);
        StringBuffer sb = new StringBuffer();
        if (curPrice != null && minPrice != null && maxPrice != null && maxPrice.compareTo(new BigDecimal("0")) != 0) {
            BigDecimal curPriceArea = curPrice.subtract(minPrice).divide(maxPrice.subtract(minPrice), 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
            sb.append(strHead).append("区间：").append("\t").append(curPriceArea).append("%").append(",");
        }
        sb.append("\t").append(strHead).append("：").append("\t").append(netMap.get(keyRsNetCloseAvg));
        sb.append("\t").append(",最低：").append("\t").append(minPrice);
        sb.append("\t").append(",最高：").append("\t").append(maxPrice);
        sb.append("\t").append(",当前价：").append(curPrice);

        return sb.toString();
    }

    /**
     * 计算我的天天基金收益
     *
     * @param amt
     * @param amt_fund
     * @param amt_fund_last
     * @param date
     * @param dateType
     */
    public static Fupan handlerFundByTtjj(String amt, String amt_fund, String amt_fund_last, String earn_fund, String date, String dateType) {
        String dayProfitRt = new BigDecimal(earn_fund).divide(new BigDecimal(amt_fund_last), 6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).divide(new BigDecimal("1"), 4, BigDecimal.ROUND_HALF_UP).toString();//当日盈亏收益率
        System.out.println("UPDATE `fupan` SET `amt`='" + amt + "', `amt_fund`='" + amt_fund + "', `amt_fund_last`='" + amt_fund_last
                + "', `earn_fund`='" + earn_fund
                + "', `rt_zh`='" + dayProfitRt
                + "' WHERE (`CODE`='" + date + "') AND fupan.period='1'" + " AND fupan.TYPE=1;");

        Fupan fupanRs = new Fupan();
        //where
        fupanRs.setCode(date);
        fupanRs.setPeriod(dateType);
        fupanRs.setType("1");//实际
        //setValue
        fupanRs.setAmt(amt);
        fupanRs.setAmt_fund(amt_fund);
        fupanRs.setAmt_fund_last(amt_fund_last);
        fupanRs.setEarn_fund(earn_fund);
        fupanRs.setRt_zh(dayProfitRt);
        return fupanRs;
    }

}
