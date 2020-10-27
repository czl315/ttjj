/**周期净值比率-n天**/
SELECT
	ol_fund_trade.FD_INFO AS 回撤
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30) * 100,2) AS 净值比率最大30
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_60) * 100,2) AS 净值比率最大60
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_90) * 100,2) AS 净值比率最大90
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_180) * 100,2) AS 净值比率最大180
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360) * 100,2) AS 净值比率最大360
	,ROUND(ol_fund_trade.LAST_NET * ol_fund_trade.CONFIRM_SHARE ,2) AS '最新金额'
	,ol_fund_trade.CONFIRM_NET AS confirmNet
	,ol_fund_trade.LAST_NET lastNet
,ol_fund_trade.NET_MAX_30 NET_MAX_30
,ol_fund_trade.NET_MAX_60 NET_MAX_60
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
FROM
	`ol_fund_trade` ol_fund_trade
-- LEFT JOIN `ol_fund_earn` ON ol_fund_trade.FD_ID = ol_fund_earn.FD_ID
WHERE
	1 = 1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
	AND ol_fund_trade.SOURCE=3
-- 	AND (ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30)=(ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360)
GROUP BY ol_fund_trade.FD_INFO
ORDER BY 净值比率最大30 DESC
-- ORDER BY 净值比率最大60 ASC
-- ORDER BY 净值比率最大90 ASC
-- ORDER BY 净值比率最大180 ASC
-- ORDER BY 净值比率最大360 ASC
-- 每日收益率 DESC;
;

/**周期净值比率-n天**/
SELECT
	ol_fund_trade.FD_INFO AS 回撤
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30) * 100,2) AS 净值比率最大30
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_60) * 100,2) AS 净值比率最大60
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_90) * 100,2) AS 净值比率最大90
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_180) * 100,2) AS 净值比率最大180
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360) * 100,2) AS 净值比率最大360
	,ROUND(ol_fund_trade.LAST_NET * ol_fund_trade.CONFIRM_SHARE ,2) AS '最新金额'
	,ol_fund_trade.CONFIRM_NET AS confirmNet
	,ol_fund_trade.LAST_NET lastNet
,ol_fund_trade.NET_MAX_30 NET_MAX_30
,ol_fund_trade.NET_MAX_60 NET_MAX_60
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
FROM
	`ol_fund_trade` ol_fund_trade
-- LEFT JOIN `ol_fund_earn` ON ol_fund_trade.FD_ID = ol_fund_earn.FD_ID
WHERE
	1 = 1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
	AND ol_fund_trade.SOURCE=3
-- 	AND (ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30)=(ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360)
GROUP BY ol_fund_trade.FD_INFO
ORDER BY 净值比率最大60 DESC
-- ORDER BY 净值比率最大60 ASC
-- ORDER BY 净值比率最大90 ASC
-- ORDER BY 净值比率最大180 ASC
-- ORDER BY 净值比率最大360 ASC
-- 每日收益率 DESC;
;

/**周期净值比率-n天**/
SELECT
	ol_fund_trade.FD_INFO AS 回撤
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30) * 100,2) AS 净值比率最大30
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_60) * 100,2) AS 净值比率最大60
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_90) * 100,2) AS 净值比率最大90
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_180) * 100,2) AS 净值比率最大180
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360) * 100,2) AS 净值比率最大360
	,ROUND(ol_fund_trade.LAST_NET * ol_fund_trade.CONFIRM_SHARE ,2) AS '最新金额'
	,ol_fund_trade.CONFIRM_NET AS confirmNet
	,ol_fund_trade.LAST_NET lastNet
,ol_fund_trade.NET_MAX_30 NET_MAX_30
,ol_fund_trade.NET_MAX_60 NET_MAX_60
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
FROM
	`ol_fund_trade` ol_fund_trade
-- LEFT JOIN `ol_fund_earn` ON ol_fund_trade.FD_ID = ol_fund_earn.FD_ID
WHERE
	1 = 1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
	AND ol_fund_trade.SOURCE=3
-- 	AND (ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30)=(ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360)
GROUP BY ol_fund_trade.FD_INFO
ORDER BY 净值比率最大90 DESC
-- ORDER BY 净值比率最大60 ASC
-- ORDER BY 净值比率最大90 ASC
-- ORDER BY 净值比率最大180 ASC
-- ORDER BY 净值比率最大360 ASC
-- 每日收益率 DESC;
;

/**周期净值比率-n天**/
SELECT
	ol_fund_trade.FD_INFO AS 回撤
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30) * 100,2) AS 净值比率最大30
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_60) * 100,2) AS 净值比率最大60
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_90) * 100,2) AS 净值比率最大90
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_180) * 100,2) AS 净值比率最大180
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360) * 100,2) AS 净值比率最大360
	,ROUND(ol_fund_trade.LAST_NET * ol_fund_trade.CONFIRM_SHARE ,2) AS '最新金额'
	,ol_fund_trade.CONFIRM_NET AS confirmNet
	,ol_fund_trade.LAST_NET lastNet
,ol_fund_trade.NET_MAX_30 NET_MAX_30
,ol_fund_trade.NET_MAX_60 NET_MAX_60
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
FROM
	`ol_fund_trade` ol_fund_trade
-- LEFT JOIN `ol_fund_earn` ON ol_fund_trade.FD_ID = ol_fund_earn.FD_ID
WHERE
	1 = 1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
	AND ol_fund_trade.SOURCE=3
-- 	AND (ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30)=(ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360)
GROUP BY ol_fund_trade.FD_INFO
ORDER BY 净值比率最大180 DESC
-- ORDER BY 净值比率最大60 ASC
-- ORDER BY 净值比率最大90 ASC
-- ORDER BY 净值比率最大180 ASC
-- ORDER BY 净值比率最大360 ASC
-- 每日收益率 DESC;
;

/**周期净值比率-n天**/
SELECT
	ol_fund_trade.FD_INFO AS 回撤
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30) * 100,2) AS 净值比率最大30
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_60) * 100,2) AS 净值比率最大60
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_90) * 100,2) AS 净值比率最大90
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_180) * 100,2) AS 净值比率最大180
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360) * 100,2) AS 净值比率最大360
	,ROUND(ol_fund_trade.LAST_NET * ol_fund_trade.CONFIRM_SHARE ,2) AS '最新金额'
	,ol_fund_trade.CONFIRM_NET AS confirmNet
	,ol_fund_trade.LAST_NET lastNet
,ol_fund_trade.NET_MAX_30 NET_MAX_30
,ol_fund_trade.NET_MAX_60 NET_MAX_60
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
FROM
	`ol_fund_trade` ol_fund_trade
-- LEFT JOIN `ol_fund_earn` ON ol_fund_trade.FD_ID = ol_fund_earn.FD_ID
WHERE
	1 = 1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
	AND ol_fund_trade.SOURCE=3
-- 	AND (ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30)=(ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360)
GROUP BY ol_fund_trade.FD_INFO
ORDER BY 净值比率最大360 DESC
-- ORDER BY 净值比率最大60 ASC
-- ORDER BY 净值比率最大90 ASC
-- ORDER BY 净值比率最大180 ASC
-- ORDER BY 净值比率最大360 ASC
-- 每日收益率 DESC;
;