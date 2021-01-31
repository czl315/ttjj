/** 涨跌-每周	**/
SELECT
	ol_fund_fupan.`CODE` 阶段涨幅
	,ol_fund_fupan.period
	,ol_fund_fupan.rt_zh
	,ol_fund_fupan.rt_hs300 AS 沪深300
	,ol_fund_fupan.rt_sh50 AS 上证50
	,ol_fund_fupan.rt_cyb50 AS 创业板50
	,ol_fund_fupan.rt_sz AS 深证
	,ol_fund_fupan.rt_cyb AS 创业板
	,ol_fund_fupan.rt_sh AS 上证
	,ol_fund_fupan.rt_zz500 AS 中证500
,ol_fund_fupan.amt
,ol_fund_fupan.amt_fund
,ol_fund_fupan.amt_hqb
,ol_fund_fupan.earn
,ol_fund_fupan.earn_fund
,ol_fund_fupan.fupan_good
,ol_fund_fupan.fupan_kong
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
	AND ol_fund_fupan.period = 30 
-- 	AND ol_fund_fupan.TYPE=1
ORDER BY ol_fund_fupan.`CODE` DESC ,ol_fund_fupan.ID DESC
LIMIT 0,1000;

/** 复盘	**/
SELECT
	ol_fund_fupan.`CODE`
	,ol_fund_fupan.period
	,ol_fund_fupan.rt_zh
	,ROUND((ol_fund_fupan.pt_hs300 - ol_fund_fupan.pt_hs300_last) / ol_fund_fupan.pt_hs300_last * 100,2) AS 沪深300
	,ROUND((ol_fund_fupan.pt_sh50 - ol_fund_fupan.pt_sh50_last) / ol_fund_fupan.pt_sh50_last * 100,2) AS 上证50
	,ROUND((ol_fund_fupan.pt_cyb50 - ol_fund_fupan.pt_cyb50_last) / ol_fund_fupan.pt_cyb50_last * 100,2) AS 创业板50
	,ROUND((ol_fund_fupan.pt_sz - ol_fund_fupan.pt_sz_last) / ol_fund_fupan.pt_sz_last * 100,2) AS 深证
	,ROUND((ol_fund_fupan.pt_cyb - ol_fund_fupan.pt_cyb_last) / ol_fund_fupan.pt_cyb_last * 100,2) AS 创业板
	,ROUND((ol_fund_fupan.pt_sh - ol_fund_fupan.pt_sh_last) / ol_fund_fupan.pt_sh_last * 100,2) AS 上证
	,ROUND((ol_fund_fupan.pt_zz500 - ol_fund_fupan.pt_zz500_last) / ol_fund_fupan.pt_zz500_last * 100,2) AS 中证500
,ol_fund_fupan.amt
,ol_fund_fupan.amt_fund
,ol_fund_fupan.amt_hqb
,ol_fund_fupan.earn
,ol_fund_fupan.earn_fund
,ol_fund_fupan.fupan_good
,ol_fund_fupan.fupan_kong
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
-- 	AND ol_fund_fupan.period = 7 AND ol_fund_fupan.TYPE=1
ORDER BY ol_fund_fupan.`CODE` DESC ,ol_fund_fupan.ID DESC
LIMIT 0,1000;



/** 成交额-行业	**/
SELECT
	ol_fund_fupan.`CODE` '成交额-行业'
-- 	,ol_fund_fupan.cje_biz_qs
	,ROUND(ol_fund_fupan.cje_biz_qs/100000000,0) '券商'
	,ol_fund_fupan.rt_biz_qs
	,ol_fund_fupan.pt_biz_qs
,ol_fund_fupan.*
FROM
	`ol_fund_fupan`
WHERE 1=1 
	AND ol_fund_fupan.period = 1 AND ol_fund_fupan.TYPE=1
ORDER BY ol_fund_fupan.`CODE` DESC ,ol_fund_fupan.ID DESC
LIMIT 0,1000;

/** 货币政策	**/
SELECT
	ol_fund_fupan.`CODE`
	,ol_fund_fupan.period
	,ol_fund_fupan.money_nhg
	,ol_fund_fupan.money_mlf
	,ol_fund_fupan.money_mlf-ol_fund_fupan.money_nhg 逆回购差值
	,ol_fund_fupan.rt_hs300
FROM
	`ol_fund_fupan`
WHERE 1=1 
	 AND ol_fund_fupan.TYPE=1
ORDER BY ol_fund_fupan.`CODE` DESC ,ol_fund_fupan.ID DESC
LIMIT 0,1000;