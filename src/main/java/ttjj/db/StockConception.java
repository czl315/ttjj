package ttjj.db;

import java.math.BigDecimal;


/**
 * 股票概念:增加股票概念表，字段：id、代码、名称、日期、概念、当日涨幅、业务类别。(ct:2023-03-05)
 * <p>
 * CREATE TABLE `st_conception` (
 * `id` int(11) NOT NULL AUTO_INCREMENT,
 * `code` varchar(255) DEFAULT NULL,
 * `name` varchar(255) DEFAULT NULL,
 * `date` varchar(20) DEFAULT NULL,
 * `conception` varchar(255) DEFAULT NULL,
 * `f3` double DEFAULT NULL,
 * `type_name` varchar(10) DEFAULT NULL,
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 *
 * @author Administrator
 * @date 2023-03-05 10:36
 */
public class StockConception {
    /**
     * id
     */
    private int id;
    /**
     * date 日期
     */
    private String date;
    /**
     * 代码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 概念
     */
    private String conception;
    /**
     * 当日涨幅
     */
    private BigDecimal f3;
    /**
     * 类型-板块名称
     */
    private String type_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConception() {
        return conception;
    }

    public void setConception(String conception) {
        this.conception = conception;
    }

    public BigDecimal getF3() {
        return f3;
    }

    public void setF3(BigDecimal f3) {
        this.f3 = f3;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
