package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
}
