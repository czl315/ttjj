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
        StringBuffer sb = new StringBuffer();
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
        if (name.equals("创成长")|| name.equals("创成长")) {
            sb.append("    ");
            return sb.toString();
        }
        if (name.equals("标普500")||name.equals("中证500")||name.equals("中证100")||name.equals("上证180")||name.equals("沪深300")) {
            sb.append("   ");
            return sb.toString();
        }
        if (name.equals("中证1000")) {
            sb.append("  ");
            return sb.toString();
        }
        if (name.equals("中证1000")||name.equals("创业板50")||name.equals("科创板50")) {
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
}
