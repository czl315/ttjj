package utils;


/**
 * EtfUtil简介
 *
 * @author Administrator
 * @date 2022-03-07 10:15
 */
public class EtfUtil {
    /**
     * @param name
     */
    public static String handlerEtfName(String name) {
        if (name.contains("ETF")) {
            name = name.replace("ETF","");
        }
        if (name.length() <= 2) {
            name = name + "    ";
        }
        if (name.contains("50")) {
            name =  name + "  ";
        }
//        if (name.length() > 2 && name.length() < 4) {
//            name = name + "\t";
//        }
//        if (name.length() >= 4) {
//            name = name + "\t";
//        }
        return name;
    }
}
