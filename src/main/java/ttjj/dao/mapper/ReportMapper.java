package ttjj.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import ttjj.dto.Report;

/**
 * 复盘-我的持仓明细
 */
public interface ReportMapper {
    /**
     * @param entity
     */
    @Insert({"<script>",
            "INSERT INTO `bank19`.`report`(",
                " `SECURITY_CODE`,`SECURITY_NAME_ABBR`, `TRADE_MARKET_CODE`, `TRADE_MARKET`, `SECURITY_TYPE_CODE`, ",
                " `SECURITY_TYPE`,`UPDATE_DATE`, `REPORT_DATE`, `BASIC_EPS`, `DEDUCT_BASIC_EPS`, ",
                " `TOTAL_OPERATE_INCOME`,`PARENT_NETPROFIT`, `WEIGHTAVG_ROE`, `YSTZ`, `JLRTBZCL`, ",
                " `PARENT_BVPS`,`MGJYXJJE`, `XSMLL`, `DJDYSHZ`, `DJDJLHZ`, ",
                " `YSHZ`,`SJLTZ`,`SJLHZ`, ",
                " `ASSIGNDSCRPT`,`PAYYEAR`, `PUBLISHNAME`, `ZXGXL`, `NOTICE_DATE`, ",
                " `ORG_CODE`,`TRADE_MARKET_ZJG`, `ISNEW`, `QDATE`, `DATATYPE`, ",
                " `DATAYEAR`,`DATEMMDD`, `EITIME`, `SECUCODE` ",
                " ,`REPORT_TYPE`,`PREDICT_FINANCE_CODE`,`PREDICT_FINANCE`,`PREDICT_AMT_LOWER`,`PREDICT_AMT_UPPER` ",
                " ,`FORECAST_JZ`,`ADD_AMP_LOWER`,`ADD_AMP_UPPER`,`INCREASE_JZ`,`PREDICT_CONTENT` ",
                " ,`CHANGE_REASON_EXPLAIN`,`PREDICT_TYPE`,`FORECAST_STATE`,`IS_LATEST`,`PREYEAR_SAME_PERIOD` ",
            ") VALUES (",
                " #{SECURITY_CODE},#{SECURITY_NAME_ABBR}, #{TRADE_MARKET_CODE}, #{TRADE_MARKET}, #{SECURITY_TYPE_CODE}, ",
                " #{SECURITY_TYPE},#{UPDATE_DATE}, #{REPORT_DATE}, #{BASIC_EPS}, #{DEDUCT_BASIC_EPS}, ",
                " #{TOTAL_OPERATE_INCOME},#{PARENT_NETPROFIT}, #{WEIGHTAVG_ROE}, #{YSTZ}, #{JLRTBZCL}, ",
                " #{PARENT_BVPS},#{MGJYXJJE}, #{XSMLL}, #{DJDYSHZ}, #{DJDJLHZ}, ",
                " #{YSHZ},#{SJLTZ},#{SJLHZ}, ",
                " #{ASSIGNDSCRPT},#{PAYYEAR}, #{PUBLISHNAME}, #{ZXGXL}, #{NOTICE_DATE}, ",
                " #{ORG_CODE},#{TRADE_MARKET_ZJG}, #{ISNEW}, #{QDATE}, #{DATATYPE}, ",
                " #{DATAYEAR},#{DATEMMDD}, #{EITIME}, #{SECUCODE} ",
                " ,#{REPORT_TYPE},#{PREDICT_FINANCE_CODE},#{PREDICT_FINANCE},#{PREDICT_AMT_LOWER},#{PREDICT_AMT_UPPER} ",
                " ,#{FORECAST_JZ},#{ADD_AMP_LOWER},#{ADD_AMP_UPPER},#{INCREASE_JZ},#{PREDICT_CONTENT} ",
                " ,#{CHANGE_REASON_EXPLAIN},#{PREDICT_TYPE},#{FORECAST_STATE},#{IS_LATEST},#{PREYEAR_SAME_PERIOD} ",
            ");",
            "</script>"})
    void insert(Report entity);

    /**
     *
     * @param condition
     * @return
     */
    @Select({"<script>",
            "select * from report ",
            "   WHERE 1=1  ",
            "   AND SECURITY_CODE=#{SECURITY_CODE} ",
            "   <if test='QDATE != null'> ",
            "   AND QDATE=#{QDATE}",
            "   </if> ",
            "   <if test='REPORT_DATE != null'> ",
            "   AND REPORT_DATE=#{REPORT_DATE}",
            "   </if> ",
            "   <if test='PREDICT_FINANCE_CODE != null'> ",
            "   AND PREDICT_FINANCE_CODE=#{PREDICT_FINANCE_CODE}",
            "   </if> ",
            "   LIMIT 1 ",
            "</script>"})
    Report findByCondition(Report condition);
}
