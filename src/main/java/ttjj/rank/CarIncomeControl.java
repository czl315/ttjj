package ttjj.rank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StopWatch;
import ttjj.dao.KlineDao;
import ttjj.db.CarIncome;
import ttjj.dto.FundFlow;
import ttjj.dto.Kline;
import ttjj.dto.RankBizDataDiff;
import ttjj.service.BizService;
import ttjj.service.CarIncomeService;
import ttjj.service.FundFlowService;
import ttjj.service.KlineService;
import utils.Content;
import utils.ContentEtf;
import utils.DateUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static utils.Content.*;


/**
 * 汽车收入
 */
public class CarIncomeControl {
    public static void main(String[] args) throws Exception {
//        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);
        String date = "2023-05-26";

//        savePatch(16, date);

//        findListByCondition(date);

        //更新用时分钟
        updateMinutes(date);

//        test();
    }

    /**
     * 更新用时分钟
     */
    private static void updateMinutes(String date) {
        int updateCount = 0;
        CarIncome condition = new CarIncome();
        condition.setDate(date);
        List<CarIncome> rs = CarIncomeService.findListByCondition(condition);
        if (rs != null) {
            for (CarIncome carIncome : rs) {
//                System.out.println(JSON.toJSONString(carIncome));
                //计算用时分钟
                carIncome.setMins(handlerMinutes(carIncome));
                updateCount += CarIncomeService.update(carIncome);
            }
        }
        System.out.println("更新个数：" + updateCount);
    }

    /**
     * 计算用时分钟
     *
     * @param carIncome
     */
    private static BigDecimal handlerMinutes(CarIncome carIncome) {
        BigDecimal rs = null;
        if (carIncome == null) {
            return null;
        }
        String begTime = carIncome.getStart_time();
        String endTime = carIncome.getEnd_time();
        String fgf = ".";
        if (begTime.contains(fgf) && endTime.contains(fgf)) {
            fgf = "\\.";//特殊字符分隔符

            String[] array = begTime.split(fgf);
            String hourBegTime = array[0];
            String minuteBegTime = array[1];
            int hourBegTimeInt = Integer.parseInt(hourBegTime);
            int minuteBegTimeInt = Integer.parseInt(minuteBegTime);
//            System.out.println("hourBegTimeInt：" + hourBegTimeInt + ";" + "minuteBegTimeInt：" + minuteBegTimeInt);
            int timeMinutesBegTime = hourBegTimeInt * 60 + minuteBegTimeInt;

            String[] arrayEndTime = endTime.split(fgf);
            String hourEndTime = arrayEndTime[0];
            String minuteEndTime = arrayEndTime[1];
            int hourEndTimeInt = Integer.parseInt(hourEndTime);
            int minuteEndTimeInt = Integer.parseInt(minuteEndTime);
//            System.out.println("hourEndTimeInt：" + hourEndTimeInt + ";" + "minuteEndTimeInt：" + minuteEndTimeInt);
            int timeMinutesEndTime = hourEndTimeInt * 60 + minuteEndTimeInt;

            int minutes = timeMinutesEndTime - timeMinutesBegTime;
            rs = new BigDecimal("" + minutes);
            System.out.println("minutes：" + minutes );
        }
        return rs;
    }

    /**
     * 批量保存
     */
    private static int savePatch(int count, String date) {
        int rs = 0;
        String plat = "快车";
        for (int i = 0; i < count; i++) {
            CarIncome entity = new CarIncome();
            entity.setDate(date);
            entity.setPlat(plat);
            entity.setFare_bounty(new BigDecimal("0"));
            entity.setFare_cost(new BigDecimal("0"));
            CarIncomeService.insert(entity);
        }
        return rs;
    }


    /**
     * 查询
     *
     * @return
     */
    private static List<CarIncome> findListByCondition(String date) {
        List<CarIncome> rs = null;
        CarIncome condition = new CarIncome();
        condition.setDate(date);
        rs = CarIncomeService.findListByCondition(condition);
        if (rs != null) {
            for (CarIncome carIncome : rs) {
                System.out.println(JSON.toJSONString(carIncome));
            }
        }

        return rs;
    }
}
