/** 涨跌-每天	**/
SELECT
	fupan.`CODE` 阶段涨幅
	,fupan.period
	,fupan.rt_zh
	,fupan.rt_st
	,fupan.rt_hs300 AS 沪深300
	,fupan.rt_sh50 AS 上证50
	,fupan.rt_cyb50 AS 创业板50
	,fupan.rt_sz AS 深证
	,fupan.rt_cyb AS 创业板
	,fupan.rt_sh AS 上证
	,fupan.rt_zz500 AS 中证500
,fupan.amt
,fupan.amt_fund
,ROUND(fupan.earn_fund+fupan.earn_st,2) 收益合计
,fupan.earn_fund
,fupan.earn_st
,fupan.fupan_good
,fupan.fupan_kong
,fupan.topic_good
,fupan.topic_kong
,fupan.ex
	,fupan.pt_sh
	,fupan.pt_sh_last
	,fupan.pt_sz
	,fupan.pt_sz_last
	,fupan.pt_cyb
	,fupan.pt_cyb_last
	,fupan.pt_sh50
	,fupan.pt_sh50_last
	,fupan.pt_hs300
	,fupan.pt_hs300_last
	,fupan.pt_cyb50
	,fupan.pt_cyb50_last
	,fupan.pt_zz500
	,fupan.pt_zz500_last
,fupan.*
FROM
	`fupan`
WHERE 1=1
	AND fupan.period = 1 /**1：每天；7：每周 **/
	AND fupan.TYPE=1
ORDER BY fupan.`CODE` DESC ,fupan.ID DESC
LIMIT 0,1000;

-- /** 涨跌-每周	**/
-- SELECT
-- 	fupan.`CODE` 每周涨跌
-- 	,fupan.period
-- 	,fupan.rt_zh
-- 	,fupan.rt_hs300 AS 沪深300
-- 	,fupan.rt_sh50 AS 上证50
-- 	,fupan.rt_cyb50 AS 创业板50
-- 	,fupan.rt_sz AS 深证
-- 	,fupan.rt_cyb AS 创业板
-- 	,fupan.rt_sh AS 上证
-- 	,fupan.rt_zz500 AS 中证500
-- ,fupan.amt
-- ,fupan.amt_fund
-- ,fupan.amt_hqb
-- ,fupan.earn
-- ,fupan.earn_fund
-- ,fupan.fupan_good
-- ,fupan.fupan_kong
-- ,fupan.topic_good
-- ,fupan.topic_kong
-- ,fupan.ex
-- 	,fupan.pt_sh
-- 	,fupan.pt_sh_last
-- 	,fupan.pt_sz
-- 	,fupan.pt_sz_last
-- 	,fupan.pt_cyb
-- 	,fupan.pt_cyb_last
-- 	,fupan.pt_sh50
-- 	,fupan.pt_sh50_last
-- 	,fupan.pt_hs300
-- 	,fupan.pt_hs300_last
-- 	,fupan.pt_cyb50
-- 	,fupan.pt_cyb50_last
-- 	,fupan.pt_zz500
-- 	,fupan.pt_zz500_last
-- ,fupan.*
-- FROM
-- 	`fupan`
-- WHERE 1=1
-- 	AND fupan.period = 7
-- 	AND fupan.TYPE=1
-- ORDER BY fupan.`CODE` DESC ,fupan.ID DESC
-- LIMIT 0,1000;

/** 成交量	**/
SELECT
	fupan.`CODE` 成交量
	,ROUND(fupan.cje_hs300/100000000,0) AS 沪深300
	,fupan.rt_hs300 AS 沪深300涨跌
	,fupan.pt_hs300 AS 沪深300指数
	,ROUND(fupan.cje_sh50/100000000,0) AS 上证50
	,fupan.rt_sh50 AS 上证50涨跌
	,fupan.pt_sh50 AS 上证50指数
	,ROUND(fupan.cje_cyb50/100000000,0) AS 创业板50
	,fupan.rt_cyb50 AS 创业板50涨跌
	,fupan.pt_cyb50 AS 创业板50指数
	,ROUND(fupan.cje_cyb/100000000,0) AS 创业板
	,fupan.rt_cyb AS 创业板涨跌
	,fupan.pt_cyb AS 创业板指数
	,ROUND(fupan.cje_sz/100000000,0) AS 深证
	,fupan.rt_sz AS 深证涨跌
	,fupan.pt_sz AS 深证指数
	,ROUND(fupan.cje_sh/100000000,0) AS 上证
	,fupan.rt_sh AS 上证涨跌
	,fupan.pt_sh AS 上证指数
	,ROUND(fupan.cje_zz500/100000000,0) AS 中证500
	,fupan.rt_zz500 AS 中证500涨跌
	,fupan.pt_zz500 AS 中证500指数
,fupan.*
FROM
	`fupan`
WHERE 1=1
-- 	AND fupan.`CODE`<='2021-03-05'
	AND fupan.period = 1 AND fupan.TYPE=1
ORDER BY fupan.`CODE` DESC ,fupan.ID DESC
LIMIT 0,1000;



/** 券商	**/
SELECT
	fupan.`CODE` '券商'
-- 	,fupan.cje_biz_qs
	,ROUND(fupan.cje_biz_qs/100000000,0) '成交额'
	,fupan.rt_biz_qs
	,fupan.pt_biz_qs
,fupan.*
FROM
	`fupan`
WHERE 1=1
	AND fupan.period = 1 AND fupan.TYPE=1
ORDER BY fupan.`CODE` DESC ,fupan.ID DESC
-- ORDER BY fupan.pt_biz_qs
-- ORDER BY fupan.cje_biz_qs DESC
LIMIT 0,1000;

/** 货币政策	**/
SELECT
	fupan.`CODE` 逆回购
	,fupan.period
	,fupan.money_nhg
	,fupan.money_mlf
	,fupan.money_mlf-fupan.money_nhg 逆回购差值
	,fupan.rt_hs300
,fupan.*
FROM
	`fupan`
WHERE 1=1
	 AND fupan.TYPE=1
ORDER BY fupan.`CODE` DESC ,fupan.ID DESC
LIMIT 0,1000;

/** 行业板块排名	**/
SELECT
	fupan.`CODE` 行业板块排名
	,fupan.topic_good
	,fupan.topic_kong
	,fupan.ex
,fupan.*
FROM
	`fupan`
WHERE 1=1
	 AND fupan.TYPE=1
ORDER BY fupan.`CODE` DESC ,fupan.ID DESC
LIMIT 0,1000;