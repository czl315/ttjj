/**周期净值比率-n天**/
SELECT
	ol_fund_trade.FD_INFO AS 回撤
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30) * 100,2) AS 回撤30
	,ROUND(((ol_fund_trade.NET_MAX_30-ol_fund_trade.NET_MIN_30) / ol_fund_trade.NET_MIN_30) * 100,2) AS 涨幅30
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_60) * 100,2) AS 回撤60
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_90) * 100,2) AS 回撤90
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_180) * 100,2) AS 回撤180
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360) * 100,2) AS 回撤360
	,ROUND(((ol_fund_trade.NET_MAX_60-ol_fund_trade.NET_MIN_60) / ol_fund_trade.NET_MIN_60) * 100,2) AS 涨幅60
	,ROUND(((ol_fund_trade.NET_MAX_90-ol_fund_trade.NET_MIN_90) / ol_fund_trade.NET_MIN_90) * 100,2) AS 涨幅90
	,ROUND(((ol_fund_trade.NET_MAX_180-ol_fund_trade.NET_MIN_180) / ol_fund_trade.NET_MIN_180) * 100,2) AS 涨幅180
	,ROUND(((ol_fund_trade.NET_MAX_360-ol_fund_trade.NET_MIN_360) / ol_fund_trade.NET_MIN_360) * 100,2) AS 涨幅360
	,ol_fund_trade.LAST_NET lastNet
,ol_fund_trade.NET_MAX_30 NET_MAX_30
,ol_fund_trade.NET_MAX_60 NET_MAX_60
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
FROM `ol_fund_trade` ol_fund_trade
WHERE 	1 = 1 AND ol_fund_trade.SOURCE=3 AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
GROUP BY ol_fund_trade.FD_INFO
ORDER BY 回撤30 DESC,回撤360 DESC,涨幅30 DESC
-- ORDER BY 涨幅30 DESC
;

/**周期净值比率-n天**/
SELECT
	ol_fund_trade.FD_INFO AS 涨幅30基金
	,ROUND(((ol_fund_trade.NET_MAX_30-ol_fund_trade.NET_MIN_30) / ol_fund_trade.NET_MIN_30) * 100,2) AS 涨幅30
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30) * 100,2) AS 回撤30
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_60) * 100,2) AS 回撤60
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_90) * 100,2) AS 回撤90
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_180) * 100,2) AS 回撤180
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360) * 100,2) AS 回撤360
	,ROUND(((ol_fund_trade.NET_MAX_60-ol_fund_trade.NET_MIN_60) / ol_fund_trade.NET_MIN_60) * 100,2) AS 涨幅60
	,ROUND(((ol_fund_trade.NET_MAX_90-ol_fund_trade.NET_MIN_90) / ol_fund_trade.NET_MIN_90) * 100,2) AS 涨幅90
	,ROUND(((ol_fund_trade.NET_MAX_180-ol_fund_trade.NET_MIN_180) / ol_fund_trade.NET_MIN_180) * 100,2) AS 涨幅180
	,ROUND(((ol_fund_trade.NET_MAX_360-ol_fund_trade.NET_MIN_360) / ol_fund_trade.NET_MIN_360) * 100,2) AS 涨幅360
	,ol_fund_trade.LAST_NET lastNet
,ol_fund_trade.NET_MAX_30 NET_MAX_30
,ol_fund_trade.NET_MAX_60 NET_MAX_60
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
FROM `ol_fund_trade` ol_fund_trade
WHERE 	1 = 1 AND ol_fund_trade.SOURCE=3 AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
GROUP BY ol_fund_trade.FD_INFO
-- ORDER BY 回撤30 DESC
ORDER BY 涨幅30 DESC
;

/**周期净值比率-n天**/
SELECT
	ol_fund_trade.FD_INFO AS 份额
	,ol_fund_trade.CONFIRM_SHARE AS 份额
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30) * 100,2) AS 回撤30
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_60) * 100,2) AS 回撤60
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_90) * 100,2) AS 回撤90
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_180) * 100,2) AS 回撤180
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360) * 100,2) AS 回撤360
	,ROUND(((ol_fund_trade.NET_MAX_30-ol_fund_trade.NET_MIN_30) / ol_fund_trade.NET_MIN_30) * 100,2) AS 涨幅30
	,ROUND(((ol_fund_trade.NET_MAX_60-ol_fund_trade.NET_MIN_60) / ol_fund_trade.NET_MIN_60) * 100,2) AS 涨幅60
	,ROUND(((ol_fund_trade.NET_MAX_90-ol_fund_trade.NET_MIN_90) / ol_fund_trade.NET_MIN_90) * 100,2) AS 涨幅90
	,ROUND(((ol_fund_trade.NET_MAX_180-ol_fund_trade.NET_MIN_180) / ol_fund_trade.NET_MIN_180) * 100,2) AS 涨幅180
	,ROUND(((ol_fund_trade.NET_MAX_360-ol_fund_trade.NET_MIN_360) / ol_fund_trade.NET_MIN_360) * 100,2) AS 涨幅360
	,ol_fund_trade.LAST_NET lastNet
,ol_fund_trade.NET_MAX_30 NET_MAX_30
,ol_fund_trade.NET_MAX_60 NET_MAX_60
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
FROM `ol_fund_trade` ol_fund_trade
WHERE 	1 = 1 AND ol_fund_trade.SOURCE=3 AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
-- GROUP BY ol_fund_trade.FD_INFO
ORDER BY 回撤30 DESC ,ol_fund_trade.FD_INFO DESC
-- ORDER BY 涨幅30 DESC
;

/**周期净值比率-n天**/
SELECT
	ol_fund_trade.FD_INFO AS 回撤60基金
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_60) * 100,2) AS 回撤60
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30) * 100,2) AS 回撤30
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_90) * 100,2) AS 回撤90
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_180) * 100,2) AS 回撤180
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360) * 100,2) AS 回撤360
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
ORDER BY 回撤60 DESC
-- ORDER BY 回撤60 ASC
-- ORDER BY 回撤90 ASC
-- ORDER BY 回撤180 ASC
-- ORDER BY 回撤360 ASC
-- 每日收益率 DESC;
;

/**周期净值比率-n天**/
SELECT
	ol_fund_trade.FD_INFO AS 回撤90基金
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30) * 100,2) AS 回撤30
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_60) * 100,2) AS 回撤60
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_90) * 100,2) AS 回撤90
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_180) * 100,2) AS 回撤180
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360) * 100,2) AS 回撤360
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
ORDER BY 回撤90 DESC
-- ORDER BY 回撤60 ASC
-- ORDER BY 回撤90 ASC
-- ORDER BY 回撤180 ASC
-- ORDER BY 回撤360 ASC
-- 每日收益率 DESC;
;

/**周期净值比率-n天**/
SELECT
	ol_fund_trade.FD_INFO AS 回撤180基金
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30) * 100,2) AS 回撤30
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_60) * 100,2) AS 回撤60
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_90) * 100,2) AS 回撤90
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_180) * 100,2) AS 回撤180
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360) * 100,2) AS 回撤360
	,ROUND(ol_fund_trade.LAST_NET * ol_fund_trade.CONFIRM_SHARE ,2) AS '最新金额'
	,ol_fund_trade.CONFIRM_NET AS confirmNet
	,ol_fund_trade.LAST_NET lastNet
,ol_fund_trade.NET_MAX_30 NET_MAX_30
,ol_fund_trade.NET_MAX_60 NET_MAX_60
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
FROM
	`ol_fund_trade` ol_fund_trade
WHERE
	1 = 1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
	AND ol_fund_trade.SOURCE=3
-- 	AND (ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30)=(ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360)
GROUP BY ol_fund_trade.FD_INFO
ORDER BY 回撤180 DESC
;

/**周期净值比率-n天**/
SELECT
	ol_fund_trade.FD_INFO AS 回撤360基金
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360) * 100,2) AS 回撤360
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30) * 100,2) AS 回撤30
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_60) * 100,2) AS 回撤60
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_90) * 100,2) AS 回撤90
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_180) * 100,2) AS 回撤180
	,ROUND(((ol_fund_trade.NET_MAX_30-ol_fund_trade.NET_MIN_30) / ol_fund_trade.NET_MIN_30) * 100,2) AS 涨幅30
	,ROUND(((ol_fund_trade.NET_MAX_60-ol_fund_trade.NET_MIN_60) / ol_fund_trade.NET_MIN_60) * 100,2) AS 涨幅60
	,ROUND(((ol_fund_trade.NET_MAX_90-ol_fund_trade.NET_MIN_90) / ol_fund_trade.NET_MIN_90) * 100,2) AS 涨幅90
	,ROUND(((ol_fund_trade.NET_MAX_180-ol_fund_trade.NET_MIN_180) / ol_fund_trade.NET_MIN_180) * 100,2) AS 涨幅180
	,ROUND(((ol_fund_trade.NET_MAX_360-ol_fund_trade.NET_MIN_360) / ol_fund_trade.NET_MIN_360) * 100,2) AS 涨幅360
	,ROUND(ol_fund_trade.LAST_NET * ol_fund_trade.CONFIRM_SHARE ,2) AS '最新金额'
	,ol_fund_trade.CONFIRM_NET AS confirmNet
	,ol_fund_trade.LAST_NET lastNet
,ol_fund_trade.NET_MAX_30 NET_MAX_30
,ol_fund_trade.NET_MAX_60 NET_MAX_60
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
FROM
	`ol_fund_trade` ol_fund_trade
WHERE
	1 = 1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
	AND ol_fund_trade.SOURCE=3
-- 	AND (ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30)=(ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360)
GROUP BY ol_fund_trade.FD_INFO
ORDER BY 回撤360 DESC
;

/****/
SELECT
	ol_fund_trade.FD_INFO AS 涨幅360基金
	,ROUND(((ol_fund_trade.NET_MAX_360-ol_fund_trade.NET_MIN_360) / ol_fund_trade.NET_MIN_360) * 100,2) AS 涨跌360
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_360) * 100,2) AS 回撤360
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_30) * 100,2) AS 回撤30
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_60) * 100,2) AS 回撤60
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_90) * 100,2) AS 回撤90
	,ROUND((ol_fund_trade.NET_MAX_1 / ol_fund_trade.NET_MAX_180) * 100,2) AS 回撤180
	,ROUND(((ol_fund_trade.NET_MAX_30-ol_fund_trade.NET_MIN_30) / ol_fund_trade.NET_MIN_30) * 100,2) AS 涨幅30
	,ROUND(((ol_fund_trade.NET_MAX_60-ol_fund_trade.NET_MIN_60) / ol_fund_trade.NET_MIN_60) * 100,2) AS 涨幅60
	,ROUND(((ol_fund_trade.NET_MAX_90-ol_fund_trade.NET_MIN_90) / ol_fund_trade.NET_MIN_90) * 100,2) AS 涨幅90
	,ROUND(((ol_fund_trade.NET_MAX_180-ol_fund_trade.NET_MIN_180) / ol_fund_trade.NET_MIN_180) * 100,2) AS 涨幅180

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
ORDER BY 涨跌360 DESC
;