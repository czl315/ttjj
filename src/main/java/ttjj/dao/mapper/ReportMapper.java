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
                " `ASSIGNDSCRPT`,`PAYYEAR`, `PUBLISHNAME`, `ZXGXL`, `NOTICE_DATE`, ",
                " `ORG_CODE`,`TRADE_MARKET_ZJG`, `ISNEW`, `QDATE`, `DATATYPE`, ",
                " `DATAYEAR`,`DATEMMDD`, `EITIME`, `SECUCODE` ",
            ") VALUES (",
                " #{SECURITY_CODE},#{SECURITY_NAME_ABBR}, #{TRADE_MARKET_CODE}, #{TRADE_MARKET}, #{SECURITY_TYPE_CODE}, ",
                " #{SECURITY_TYPE},#{UPDATE_DATE}, #{REPORT_DATE}, #{BASIC_EPS}, #{DEDUCT_BASIC_EPS}, ",
                " #{TOTAL_OPERATE_INCOME},#{PARENT_NETPROFIT}, #{WEIGHTAVG_ROE}, #{YSTZ}, #{JLRTBZCL}, ",
                " #{PARENT_BVPS},#{MGJYXJJE}, #{XSMLL}, #{DJDYSHZ}, #{DJDJLHZ}, ",
                " #{ASSIGNDSCRPT},#{PAYYEAR}, #{PUBLISHNAME}, #{ZXGXL}, #{NOTICE_DATE}, ",
                " #{ORG_CODE},#{TRADE_MARKET_ZJG}, #{ISNEW}, #{QDATE}, #{DATATYPE}, ",
                " #{DATAYEAR},#{DATEMMDD}, #{EITIME}, #{SECUCODE} ",
            ");",
            "</script>"})
    void insert(Report entity);

    /**
     *
     * @param condition
     * @return
     */
    @Select("select * from report where SECURITY_CODE=#{SECURITY_CODE} AND QDATE=#{QDATE}")
    Report findByCondition(Report condition);
}
