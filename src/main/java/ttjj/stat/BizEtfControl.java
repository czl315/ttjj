package ttjj.stat;

import ttjj.service.KlineService;
import utils.ContentEtf;
import utils.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static utils.Content.*;

/**
 * 主题排行
 */
public class BizEtfControl {
    public static void main(String[] args) {
        checkMaDemo();
    }

    /**
     * 检查均线
     */
    private static void checkMaDemo() {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);//                        String date = "2022-02-15";

        boolean isUp = true;//检查上涨
//        boolean isUp = false;

        List<Integer> maList = new ArrayList<>();
//        maList.add(MA_30);
        maList.add(MA_60);

        Map<String, String> etfBizMap = ContentEtf.mapEtfAll;//mapEtfBiz mapEtfIndex    mapEtfAll

//            KlineService.checkMa(etfBizMap, KLT_5, maList, date, isUp);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
        KlineService.checkMa(etfBizMap, KLT_15, maList, date, isUp);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
        KlineService.checkMa(etfBizMap, KLT_30, maList, date, isUp);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
        KlineService.checkMa(etfBizMap, KLT_60, maList, date, isUp);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
        KlineService.checkMa(etfBizMap, KLT_101, maList, date, isUp);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
        KlineService.checkMa(etfBizMap, KLT_102, maList, date, isUp);// //    检查均线:买入信号   KLT_15 KLT_30  KLT_60 KLT_101
    }

}
