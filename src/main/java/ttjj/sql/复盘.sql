/** 复盘	**/
SELECT
	ol_fund_fupan.`CODE`
	,ol_fund_fupan.period
	,ROUND((ol_fund_fupan.pt_sh - ol_fund_fupan.pt_sh_last) / ol_fund_fupan.pt_sh_last * 100,2) AS 上证
	,ROUND((ol_fund_fupan.pt_sh50 - ol_fund_fupan.pt_sh50_last) / ol_fund_fupan.pt_sh50_last * 100,2) AS 上证50
	,ROUND((ol_fund_fupan.pt_sz - ol_fund_fupan.pt_sz_last) / ol_fund_fupan.pt_sz_last * 100,2) AS 深证
	,ROUND((ol_fund_fupan.pt_cyb - ol_fund_fupan.pt_cyb_last) / ol_fund_fupan.pt_cyb_last * 100,2) AS 创业板
	,ROUND((ol_fund_fupan.pt_cyb50 - ol_fund_fupan.pt_cyb50_last) / ol_fund_fupan.pt_cyb50_last * 100,2) AS 创业板50
	,ROUND((ol_fund_fupan.pt_hs300 - ol_fund_fupan.pt_hs300_last) / ol_fund_fupan.pt_hs300_last * 100,2) AS 沪深300
	,ROUND((ol_fund_fupan.pt_zz500 - ol_fund_fupan.pt_zz500_last) / ol_fund_fupan.pt_zz500_last * 100,2) AS 中证500
,ol_fund_fupan.fupan_good
,ol_fund_fupan.fupan_kong
,ol_fund_fupan.amt
,ol_fund_fupan.earn
,ol_fund_fupan.topic_good
,ol_fund_fupan.topic_kong
,ol_fund_fupan.ex
	,ol_fund_fupan.pt_sh
	,ol_fund_fupan.pt_sh_last
	,ol_fund_fupan.pt_sz
	,ol_fund_fupan.pt_sz_last
	,ol_fund_fupan.pt_cyb
	,ol_fund_fupan.pt_cyb_last
	,ol_fund_fupan.pt_sh50
	,ol_fund_fupan.pt_sh50_last
	,ol_fund_fupan.pt_hs300
	,ol_fund_fupan.pt_hs300_last
	,ol_fund_fupan.pt_cyb50
	,ol_fund_fupan.pt_cyb50_last
	,ol_fund_fupan.pt_zz500
	,ol_fund_fupan.pt_zz500_last

,ol_fund_fupan.*
FROM
	`ol_fund_fupan`
WHERE 1=1
-- 	AND `CODE` LIKE '%2020-11-23%'
ORDER BY ol_fund_fupan.`ID` DESC
LIMIT 0,
 1000;