/**基金交易的最新净值-收益率**/
SELECT
	ol_fund_trade.FD_INFO AS 最新收益倒序

	,ol_fund_trade.CONFIRM_SHARE AS 买入份额
	,ROUND(
		(ol_fund_trade.LAST_NET - ol_fund_trade.CONFIRM_NET) * ol_fund_trade.CONFIRM_SHARE / ol_fund_trade.ORDER_AMT * 100,
		4
	) AS 最新收益率
,ROUND((ol_fund_trade.LAST_NET - ol_fund_trade.CONFIRM_NET) * ol_fund_trade.CONFIRM_SHARE / ol_fund_trade.ORDER_AMT * 100/DATEDIFF(NOW() ,ol_fund_trade.TRADE_TIME) ,4) 
		AS 每日收益率
	,ol_fund_trade.CONFIRM_AMT AS 交易金额
,DATEDIFF(NOW() ,ol_fund_trade.TRADE_TIME) AS 持有天数
	,ol_fund_trade.TRADE_TIME
	,ROUND(ol_fund_trade.LAST_NET * ol_fund_trade.CONFIRM_SHARE ,2) AS '最新金额'
	,ol_fund_trade.CONFIRM_NET AS confirmNet
	,ol_fund_trade.LAST_NET lastNet
,ol_fund_trade.NET_MAX_30 NET_MAX_30
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
FROM
	`ol_fund_trade` ol_fund_trade
WHERE
	1 = 1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购' 
-- AND ol_fund_trade.FD_INFO LIKE '%160633|鹏华证券分级%' 
-- ORDER BY ol_fund_trade.TRADE_TIME ASC
ORDER BY 最新收益率 DESC;
-- 每日收益率 DESC;
;

/**基金交易的最新净值-收益率**/
SELECT
	ol_fund_trade.FD_INFO AS 最老收益倒序
	,ol_fund_trade.CONFIRM_SHARE AS 买入份额
	,ROUND(
		(ol_fund_trade.LAST_NET - ol_fund_trade.CONFIRM_NET) * ol_fund_trade.CONFIRM_SHARE / ol_fund_trade.ORDER_AMT * 100,
		4
	) AS 最新收益率
,ROUND((ol_fund_trade.LAST_NET - ol_fund_trade.CONFIRM_NET) * ol_fund_trade.CONFIRM_SHARE / ol_fund_trade.ORDER_AMT * 100/DATEDIFF(NOW() ,ol_fund_trade.TRADE_TIME) ,4) 
		AS 每日收益率
	,ol_fund_trade.CONFIRM_AMT AS 交易金额
,DATEDIFF(NOW() ,ol_fund_trade.TRADE_TIME) AS 持有天数
	,ol_fund_trade.TRADE_TIME
,ROUND((ol_fund_trade.LAST_NET / ol_fund_trade.NET_MAX_1) * 100,2) AS 当晚净值比最大率
	,ROUND(ol_fund_trade.LAST_NET * ol_fund_trade.CONFIRM_SHARE ,2) AS '最新金额'
	,ol_fund_trade.CONFIRM_NET AS confirmNet
	,ol_fund_trade.LAST_NET lastNet
,ol_fund_trade.NET_MAX_30 NET_MAX_30
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
FROM
	`ol_fund_trade` ol_fund_trade
WHERE
	1 = 1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE in( '申购','申购(赎回中)') 
-- AND ol_fund_trade.FD_INFO LIKE '%160633|鹏华证券分级%' 
ORDER BY ol_fund_trade.TRADE_TIME ASC
;

/**基金交易的最新净值-收益率**/
SELECT
	ol_fund_trade.FD_INFO AS 每日收益率排序
,DATEDIFF(NOW() ,ol_fund_trade.TRADE_TIME) AS 持有天数
	,ol_fund_trade.TRADE_TIME
	,ROUND(
		(ol_fund_trade.LAST_NET - ol_fund_trade.CONFIRM_NET) * ol_fund_trade.CONFIRM_SHARE / ol_fund_trade.ORDER_AMT * 100,
		4
	) AS 最新日期收益率
,ROUND((ol_fund_trade.LAST_NET - ol_fund_trade.CONFIRM_NET) * ol_fund_trade.CONFIRM_SHARE / ol_fund_trade.CONFIRM_AMT * 100/DATEDIFF(NOW() ,ol_fund_trade.TRADE_TIME) ,4) 
		AS 每日收益率
	,ol_fund_trade.CONFIRM_SHARE
	,ol_fund_trade.CONFIRM_AMT
	,ROUND(ol_fund_trade.LAST_NET * ol_fund_trade.CONFIRM_SHARE ,2) AS '最新金额'
	,ol_fund_trade.CONFIRM_NET AS confirmNet
	,ol_fund_trade.LAST_NET lastNet
,ol_fund_trade.TYPE
FROM
	`ol_fund_trade` ol_fund_trade
-- LEFT JOIN `ol_fund_earn` ON ol_fund_trade.FD_ID = ol_fund_earn.FD_ID
WHERE
	1 = 1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
-- 	AND DATEDIFF(NOW() ,ol_fund_trade.TRADE_TIME)>15
-- 	AND ol_fund_trade.TYPE = '申购' 
-- 	AND ol_fund_trade.CONFIRM_AMT <1000
-- 	AND ol_fund_trade.TYPE in( '申购(赎回)' )
-- 	AND ol_fund_trade.TYPE = '赎回' 
-- AND ol_fund_trade.FD_INFO LIKE '%160633|鹏华证券分级%'
-- ORDER BY lastDate DESC
ORDER BY TRADE_TIME DESC;
-- ORDER BY 每日收益率 DESC;

-- /**赎回**/
SELECT
	ol_fund_trade.FD_INFO AS 赎回信息,
	ol_fund_trade.ORDER_AMT,
	ol_fund_trade.REDEM_SHARE,
	ol_fund_trade.CONFIRM_NET AS confirmNet,
	ol_fund_trade.LAST_NET lastNet
	,ROUND(
			(ol_fund_trade.REDEM_AMT - ol_fund_trade.CONFIRM_AMT)/ ol_fund_trade.CONFIRM_AMT * 100,
		4
	) AS 赎回收益率
,DATEDIFF(ol_fund_trade.REDEM_TIME ,ol_fund_trade.TRADE_TIME) AS 赎回天数
	,ROUND((ol_fund_trade.REDEM_AMT - ol_fund_trade.CONFIRM_AMT)/ ol_fund_trade.CONFIRM_AMT * 100/DATEDIFF(ol_fund_trade.REDEM_TIME ,ol_fund_trade.TRADE_TIME) ,4) 
		AS 每日收益率
,ol_fund_trade.CONFIRM_AMT
,ol_fund_trade.REDEM_AMT
,ol_fund_trade.TYPE
	,ol_fund_trade.TRADE_TIME
	,ol_fund_trade.REDEM_TIME
FROM
	`ol_fund_trade` ol_fund_trade
WHERE
	1 = 1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
-- 	AND ol_fund_trade.TYPE in('申购(赎回)')
-- AND ol_fund_trade.FD_INFO LIKE '%160633|鹏华证券分级%'
-- ORDER BY lastDate DESC
-- ORDER BY 最大收益率 DESC
ORDER BY
	每日收益率 DESC
-- ol_fund_trade.REDEM_TIME  DESC
;

SELECT ol_fund_trade.FD_INFO AS 基金分组
	,SUM(ol_fund_trade.CONFIRM_AMT)  AS sumamt 
	,COUNT(ol_fund_trade.CONFIRM_AMT)  
	FROM ol_fund_trade 
WHERE 1=1
	AND ol_fund_trade.SOURCE =3 
	AND ol_fund_trade.TYPE in( '申购' ) 
	GROUP BY ol_fund_trade.FD_INFO
	ORDER by sumamt  DESC
;

/**超过止损线**/
SELECT
	ol_fund_trade.FD_INFO AS 超过止损线

	,ol_fund_trade.CONFIRM_SHARE AS 买入份额
	,ROUND(
		(ol_fund_trade.LAST_NET - ol_fund_trade.CONFIRM_NET) * ol_fund_trade.CONFIRM_SHARE / ol_fund_trade.ORDER_AMT * 100,
		4
	) AS 最新收益率
,ROUND((ol_fund_trade.LAST_NET - ol_fund_trade.CONFIRM_NET) * ol_fund_trade.CONFIRM_SHARE / ol_fund_trade.ORDER_AMT * 100/DATEDIFF(NOW() ,ol_fund_trade.TRADE_TIME) ,4) 
		AS 每日收益率
	,ol_fund_trade.CONFIRM_AMT AS 交易金额
,DATEDIFF(NOW() ,ol_fund_trade.TRADE_TIME) AS 持有天数
	,ol_fund_trade.TRADE_TIME
	,ROUND(ol_fund_trade.LAST_NET * ol_fund_trade.CONFIRM_SHARE ,2) AS '最新金额'
	,ROUND(ol_fund_trade.CONFIRM_NET*ol_fund_trade.RK_ST_LOSS * ol_fund_trade.CONFIRM_SHARE ,2) AS '止损金额'
	,ol_fund_trade.CONFIRM_NET AS confirmNet
	,ol_fund_trade.LAST_NET lastNet
	,ol_fund_trade.TYPE
FROM
	`ol_fund_trade` ol_fund_trade
-- LEFT JOIN `ol_fund_earn` ON ol_fund_trade.FD_ID = ol_fund_earn.FD_ID
WHERE
	1 = 1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购' 
	AND ROUND(ol_fund_trade.CONFIRM_NET*ol_fund_trade.RK_ST_LOSS * ol_fund_trade.CONFIRM_SHARE ,2) > ROUND(ol_fund_trade.LAST_NET * ol_fund_trade.CONFIRM_SHARE ,2)
ORDER BY TRADE_TIME ;
-- 每日收益率 DESC;
;

/**超过止盈线**/
SELECT
	ol_fund_trade.FD_INFO AS 超过止盈线

	,ol_fund_trade.CONFIRM_SHARE AS 买入份额
	,ROUND(
		(ol_fund_trade.LAST_NET - ol_fund_trade.CONFIRM_NET) * ol_fund_trade.CONFIRM_SHARE / ol_fund_trade.ORDER_AMT * 100,
		4
	) AS 最新收益率
,ROUND((ol_fund_trade.LAST_NET - ol_fund_trade.CONFIRM_NET) * ol_fund_trade.CONFIRM_SHARE / ol_fund_trade.ORDER_AMT * 100/DATEDIFF(NOW() ,ol_fund_trade.TRADE_TIME) ,4) 
		AS 每日收益率
	,ol_fund_trade.CONFIRM_AMT AS 交易金额
,DATEDIFF(NOW() ,ol_fund_trade.TRADE_TIME) AS 持有天数
	,ol_fund_trade.TRADE_TIME
	,ROUND(ol_fund_trade.LAST_NET * ol_fund_trade.CONFIRM_SHARE ,2) AS '最新金额'
	,ROUND(ol_fund_trade.CONFIRM_NET*ol_fund_trade.RK_ST_PROFIT * ol_fund_trade.CONFIRM_SHARE ,2) AS '止盈金额'
	,ol_fund_trade.CONFIRM_NET AS confirmNet
	,ol_fund_trade.LAST_NET lastNet
	,ol_fund_trade.TYPE
FROM
	`ol_fund_trade` ol_fund_trade
-- LEFT JOIN `ol_fund_earn` ON ol_fund_trade.FD_ID = ol_fund_earn.FD_ID
WHERE
	1 = 1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购' 
	AND ROUND(ol_fund_trade.CONFIRM_NET*ol_fund_trade.RK_ST_PROFIT * ol_fund_trade.CONFIRM_SHARE ,2) < ROUND(ol_fund_trade.LAST_NET * ol_fund_trade.CONFIRM_SHARE ,2)
ORDER BY TRADE_TIME ;
-- ORDER BY FD_INFO ;
-- 每日收益率 DESC;
;

