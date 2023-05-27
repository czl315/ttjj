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
        int count = 16;
        String plat = "快车";
        for (int i = 0; i < count; i++) {
            CarIncome entity = new CarIncome();
            entity.setDate(date);
            entity.setPlat(plat);
            CarIncomeService.insert(entity);
        }
    }
}
