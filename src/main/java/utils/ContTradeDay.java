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
        mapTradeDay.put(0,"2023-03-24");
        mapTradeDay.put(1,"2023-03-23");
        mapTradeDay.put(2,"2023-03-22");
        mapTradeDay.put(3,"2023-03-21");
        mapTradeDay.put(4,"2023-03-20");
        mapTradeDay.put(5,"2023-03-17");
        mapTradeDay.put(6,"2023-03-16");
        mapTradeDay.put(7,"2023-03-15");
        mapTradeDay.put(8,"2023-03-14");
        mapTradeDay.put(9,"2023-03-13");
        mapTradeDay.put(10,"2023-03-10");
        mapTradeDay.put(11,"2023-03-09");
        mapTradeDay.put(12,"2023-03-08");
        mapTradeDay.put(13,"2023-03-07");
        mapTradeDay.put(14,"2023-03-06");
        mapTradeDay.put(15,"2023-03-03");
        mapTradeDay.put(16,"2023-03-02");
        mapTradeDay.put(17,"2023-03-01");
        mapTradeDay.put(18,"2023-02-28");
        mapTradeDay.put(19,"2023-02-27");
        mapTradeDay.put(20,"2023-02-24");
        mapTradeDay.put(21,"2023-02-23");
        mapTradeDay.put(22,"2023-02-22");
        mapTradeDay.put(23,"2023-02-21");
        mapTradeDay.put(24,"2023-02-20");
        mapTradeDay.put(25,"2023-02-17");
        mapTradeDay.put(26,"2023-02-16");
        mapTradeDay.put(27,"2023-02-15");
        mapTradeDay.put(28,"2023-02-14");
        mapTradeDay.put(29,"2023-02-13");
        mapTradeDay.put(30,"2023-02-10");
        mapTradeDay.put(31,"2023-02-09");
        mapTradeDay.put(32,"2023-02-08");
        mapTradeDay.put(33,"2023-02-07");
        mapTradeDay.put(34,"2023-02-06");
        mapTradeDay.put(35,"2023-02-03");
        mapTradeDay.put(36,"2023-02-02");
        mapTradeDay.put(37,"2023-02-01");
        mapTradeDay.put(38,"2023-01-31");
        mapTradeDay.put(39,"2023-01-30");
        mapTradeDay.put(40,"2023-01-20");
        mapTradeDay.put(41,"2023-01-19");
        mapTradeDay.put(42,"2023-01-18");
        mapTradeDay.put(43,"2023-01-17");
        mapTradeDay.put(44,"2023-01-16");
        mapTradeDay.put(45,"2023-01-13");
        mapTradeDay.put(46,"2023-01-12");
        mapTradeDay.put(47,"2023-01-11");
        mapTradeDay.put(48,"2023-01-10");
        mapTradeDay.put(49,"2023-01-09");
        mapTradeDay.put(50,"2023-01-06");
        mapTradeDay.put(51,"2023-01-05");
        mapTradeDay.put(52,"2023-01-04");
        mapTradeDay.put(53,"2023-01-03");
        mapTradeDay.put(54,"2022-12-30");
        mapTradeDay.put(55,"2022-12-29");
        mapTradeDay.put(56,"2022-12-28");
        mapTradeDay.put(57,"2022-12-27");
        mapTradeDay.put(58,"2022-12-26");
        mapTradeDay.put(59,"2022-12-23");
        mapTradeDay.put(60,"2022-12-22");
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
