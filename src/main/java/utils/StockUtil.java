package utils;

import java.math.BigDecimal;

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
     * 格式化-字符串，根据长度
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
}
