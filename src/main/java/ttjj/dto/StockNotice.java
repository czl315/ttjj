package ttjj.dto;

import java.math.BigDecimal;


/**
 * StockNotice简介
 *
 *  {
 *   "data": {
 *     "list": [
 *       {
 *         "art_code": "AN202111151529234515",
 *         "codes": [
 *           {
 *             "ann_type": "A,SZA",
 *             "inner_code": "32126709707798",
 *             "market_code": "0",
 *             "short_name": "涪陵榨菜",
 *             "stock_code": "002507"
 *           }
 *         ],
 *         "columns": [
 *           {
 *             "column_code": "001003002001",
 *             "column_name": "保荐/核查意见"
 *           }
 *         ],
 *         "display_time": "2021-11-15 18:54:16:409",
 *         "eiTime": "2021-11-15 18:55:28:000",
 *         "language": "0",
 *         "notice_date": "2021-11-16 00:00:00",
 *         "title": "涪陵榨菜:西南证券股份有限公司关于重庆市涪陵榨菜集团股份有限公司非公开发行限售股份上市流通的核查意见",
 *         "title_ch": "涪陵榨菜:西南证券股份有限公司关于重庆市涪陵榨菜集团股份有限公司非公开发行限售股份上市流通的核查意见",
 *         "title_en": ""
 *       },
 *       {
 *         "art_code": "AN202111151529234521",
 *         "codes": [
 *           {
 *             "ann_type": "A,SZA",
 *             "inner_code": "32126709707798",
 *             "market_code": "0",
 *             "short_name": "涪陵榨菜",
 *             "stock_code": "002507"
 *           }
 *         ],
 *         "columns": [
 *           {
 *             "column_code": "001002002003",
 *             "column_name": "限售股份上市流通"
 *           }
 *         ],
 *         "display_time": "2021-11-15 18:54:16:309",
 *         "eiTime": "2021-11-15 18:55:28:000",
 *         "language": "0",
 *         "notice_date": "2021-11-16 00:00:00",
 *         "title": "涪陵榨菜:关于非公开发行限售股份上市流通的提示性公告",
 *         "title_ch": "涪陵榨菜:关于非公开发行限售股份上市流通的提示性公告",
 *         "title_en": ""
 *       }
 *     ],
 *     "page_index": 1,
 *     "page_size": 2,
 *     "total_hits": 1296
 *   },
 *   "error": "",
 *   "success": 1
 * }
 * @author Administrator
 * @date 2021-11-15 21:55
 */
public class StockNotice {
    private String art_code;
    private String codes;
    private String columns;
    private String column_code;
    private String column_name;
    private String display_time;
    private String eiTime;
    private String language;
    private String notice_date;
    private String title;
    private String title_ch;
    private String title_en;

    public String getArt_code() {
        return art_code;
    }

    public void setArt_code(String art_code) {
        this.art_code = art_code;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getColumn_code() {
        return column_code;
    }

    public void setColumn_code(String column_code) {
        this.column_code = column_code;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getDisplay_time() {
        return display_time;
    }

    public void setDisplay_time(String display_time) {
        this.display_time = display_time;
    }

    public String getEiTime() {
        return eiTime;
    }

    public void setEiTime(String eiTime) {
        this.eiTime = eiTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getNotice_date() {
        return notice_date;
    }

    public void setNotice_date(String notice_date) {
        this.notice_date = notice_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_ch() {
        return title_ch;
    }

    public void setTitle_ch(String title_ch) {
        this.title_ch = title_ch;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }
}
