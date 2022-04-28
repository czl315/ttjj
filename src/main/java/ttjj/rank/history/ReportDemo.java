package ttjj.rank.history;

import com.alibaba.fastjson.JSON;
import ttjj.dao.ReportDao;
import ttjj.db.RankStockCommpanyDb;
import ttjj.dto.RankBizDataDiff;
import ttjj.dto.Report;
import ttjj.rank.BizRankDemo;
import ttjj.rank.StockDemo;
import ttjj.service.ReportService;
import utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import static utils.Content.DB_RANK_BIZ_TYPE_HANG_YE;
import static utils.Content.NUM_MAX_999;


/**
 * 业绩报表
 *
 * @author Administrator
 * @date 2021-10-30 17:00
 */
public class ReportDemo {
    public static void main(String[] args) {
        String date = DateUtil.getToday(DateUtil.YYYY_MM_DD);// String date = "2021-09-24";
        int indexBeg = 0;//行业序号：从0开始
        int indexEnd = NUM_MAX_999;//行业序号-结束

        //查询所有行业列表
        List<RankBizDataDiff> bizList = BizRankDemo.listBiz(date, DB_RANK_BIZ_TYPE_HANG_YE, NUM_MAX_999);//查询所有行业列表
//        List<RankBizDataDiff> bizList = new ArrayList<>();//查询所有行业列表
//        RankBizDataDiff bizOne = new RankBizDataDiff();//指定一个板块行业
//        bizOne.setF12("BK0438");//食品饮料:BK0438
//        bizList.add(bizOne);

        //遍历所有行业中的股票
        List<RankStockCommpanyDb> stockList = new ArrayList<>();
        for (int i = 0; i < bizList.size(); i++) {
            RankBizDataDiff biz = bizList.get(i);
            if (i < indexBeg) {
                //行业序号判断
                System.out.println("行业序号判断-未到开始序号-跳过:" + biz.getF14() + "," + biz.getF12());
                continue;
            }
            if (i >= indexEnd) {
                //行业序号判断
                System.out.println("行业序号判断-已结束");
                break;
            }

            System.out.println("行业-------------------------------：" + biz.getF14());
            //特定行业
            if(biz.getF14().equals("证券")){
                stockList.addAll(StockDemo.listRankStockByBiz(NUM_MAX_999, biz.getF12()));
            }
//            stockList.addAll(StockDemo.listRankStockByBiz(NUM_MAX_999, biz.getF12()));
        }

        //保存业绩报表
        for (RankStockCommpanyDb rankStockCommpanyDb : stockList) {
            insertOrUpdateReport(rankStockCommpanyDb.getF12());
        }
    }

    /**
     * 保存业绩报告
     */
    private static void insertReport(String stCode) {
        List<Report> rs = ReportService.listHttpReportByStCode(stCode);//查询业绩报表-根据证券编码
        for (Report entity : rs) {
            int insertYes = ReportDao.insert(entity);//插入数据库
            System.out.println("保存成功标志：" + insertYes + "," + entity.getSECURITY_NAME_ABBR());
        }
    }

    /**
     * 保存或更新业绩报告
     *
     * @param stCode
     */
    private static void insertOrUpdateReport(String stCode) {
        List<Report> rs = ReportService.listHttpReportByStCode(stCode);//查询业绩报表-根据证券编码
        for (Report entity : rs) {
//            if(entity.getSECURITY_CODE().equals("002610")){
//                System.out.println("");
//            }
            //  查询业绩报表是否存在
            Report existEntity = ReportDao.findByCondition(entity);
            if (existEntity == null) {
                System.out.println("记录不存在，查询条件" + JSON.toJSONString(entity));
                int insertYes = ReportDao.insert(entity);//插入数据库
                System.out.println("插入成功标志：" + insertYes + "," + entity.getSECURITY_NAME_ABBR());
                continue;
            }

            // 如果记录已存在
            System.out.println("记录已存在.");//+ JSON.toJSONString(existEntity)

        }
    }
}
