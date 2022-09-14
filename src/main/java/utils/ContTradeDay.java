package utils;

import ttjj.service.StockService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易日-缓存常量
 *
 * @author Administrator
 * @date 2022-09-13 11:05
 */
public class ContTradeDay {
    /**
     * mapEtfKeJi etf-科技
     */
    public static Map<Integer, String> mapTradeDay = new HashMap<>();

    static {
        mapTradeDay.put(0,"2022-09-14");
        mapTradeDay.put(1,"2022-09-13");
        mapTradeDay.put(2,"2022-09-09");
        mapTradeDay.put(3,"2022-09-08");
        mapTradeDay.put(4,"2022-09-07");
        mapTradeDay.put(5,"2022-09-06");
        mapTradeDay.put(6,"2022-09-05");
        mapTradeDay.put(7,"2022-09-02");
        mapTradeDay.put(8,"2022-09-01");
        mapTradeDay.put(9,"2022-08-31");
        mapTradeDay.put(10,"2022-08-30");
        mapTradeDay.put(11,"2022-08-29");
        mapTradeDay.put(12,"2022-08-26");
        mapTradeDay.put(13,"2022-08-25");
        mapTradeDay.put(14,"2022-08-24");
        mapTradeDay.put(15,"2022-08-23");
        mapTradeDay.put(16,"2022-08-22");
        mapTradeDay.put(17,"2022-08-19");
        mapTradeDay.put(18,"2022-08-18");
        mapTradeDay.put(19,"2022-08-17");
        mapTradeDay.put(20,"2022-08-16");
        mapTradeDay.put(21,"2022-08-15");
        mapTradeDay.put(22,"2022-08-12");
        mapTradeDay.put(23,"2022-08-11");
        mapTradeDay.put(24,"2022-08-10");
        mapTradeDay.put(25,"2022-08-09");
        mapTradeDay.put(26,"2022-08-08");
        mapTradeDay.put(27,"2022-08-05");
        mapTradeDay.put(28,"2022-08-04");
        mapTradeDay.put(29,"2022-08-03");
        mapTradeDay.put(30,"2022-08-02");
        mapTradeDay.put(31,"2022-08-01");
        mapTradeDay.put(32,"2022-07-29");
        mapTradeDay.put(33,"2022-07-28");
        mapTradeDay.put(34,"2022-07-27");
        mapTradeDay.put(35,"2022-07-26");
        mapTradeDay.put(36,"2022-07-25");
        mapTradeDay.put(37,"2022-07-22");
        mapTradeDay.put(38,"2022-07-21");
        mapTradeDay.put(39,"2022-07-20");
        mapTradeDay.put(40,"2022-07-19");
        mapTradeDay.put(41,"2022-07-18");
        mapTradeDay.put(42,"2022-07-15");
        mapTradeDay.put(43,"2022-07-14");
        mapTradeDay.put(44,"2022-07-13");
        mapTradeDay.put(45,"2022-07-12");
        mapTradeDay.put(46,"2022-07-11");
        mapTradeDay.put(47,"2022-07-08");
        mapTradeDay.put(48,"2022-07-07");
        mapTradeDay.put(49,"2022-07-06");
        mapTradeDay.put(50,"2022-07-05");
        mapTradeDay.put(51,"2022-07-04");
        mapTradeDay.put(52,"2022-07-01");
        mapTradeDay.put(53,"2022-06-30");
        mapTradeDay.put(54,"2022-06-29");
        mapTradeDay.put(55,"2022-06-28");
        mapTradeDay.put(56,"2022-06-27");
        mapTradeDay.put(57,"2022-06-24");
        mapTradeDay.put(58,"2022-06-23");
        mapTradeDay.put(59,"2022-06-22");
        mapTradeDay.put(60,"2022-06-21");
    }


    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
//        String date = "2022-09-09";
        List<String> dateList = StockService.findListDateBefore(date, 60);//查询n个交易日之前的日期
        int temp = 0;
        for (String dateStr : dateList) {
            System.out.println("mapTradeDay.put(" + (temp++) + ",\"" + dateStr + "\");");

        }
    }
}
