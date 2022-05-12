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
     * 格式化-涨跌幅
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
