package ttjj.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import ttjj.db.CarIncome;

/**
 * CarIncomeMapper简介
 *
 * @author Administrator
 * @date 2023-05-28 00:24
 */
public interface CarIncomeMapper {
    @Insert({"<script>",
            "INSERT INTO `bank19`.`car_income` (" +
                    "`plat`, `fast_type`, `date`, `start_time`, " +
                    "`end_time`, `mins`, `mins_share`, `fare`, `fare_share`, " +
                    "`fare_pay`, `fare_pay_tatol`, `fare_bounty`, `fare_cost`, `fare_tip`, " +
                    "`num_pass`, `num_order`, `phone`, `phone_share`, `distance`, " +
                    "`seat`, `seat_rs`, `start_addr`, `end_addr`, `order_time`, " +
                    "`arrive_ori_time`, `on_car_time`, `arrive_des_time`, `trip_mins`, `wait_mins`, " +
                    "`go_sta_mins`, `end_empty_mins`, `find_mins`, `rs_high`, `rs_low`, " +
                    "`remark`, `start_county`, `start_village`, `end_county`, `end_village`, " +
                    "`start_city`, `end_city`) " +
            "VALUES (" +
                    "#{plat}, #{fast_type}, #{date}, #{start_time}, " +
                    "#{end_time}, #{mins}, #{mins_share}, #{fare}, #{fare_share}, " +
                    "#{fare_pay}, #{fare_pay_tatol}, #{fare_bounty}, #{fare_cost}, #{fare_tip}, " +
                    "#{num_pass}, #{num_order}, #{phone}, #{phone_share}, #{distance}, " +
                    "#{seat}, #{seat_rs}, #{start_addr}, #{end_addr}, #{order_time}, " +
                    "#{arrive_ori_time}, #{on_car_time}, #{arrive_des_time}, #{trip_mins}, #{wait_mins}, " +
                    "#{go_sta_mins}, #{end_empty_mins}, #{find_mins}, #{rs_high}, #{rs_low}, " +
                    "#{remark}, #{start_county}, #{start_village}, #{end_county}, #{end_village}, " +
                    "#{start_city}, #{end_city}" +
                    ") ;" +
            "</script>"})
    void insert(CarIncome entity);
}
