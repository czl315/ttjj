/**股票-交易的最新净值-收益率**/
SELECT
	stock_trade.FD_CODE 最新收益
	,stock_trade.FD_INFO
	,stock_trade.CONFIRM_SHARE AS 买入份额
	,ROUND((stock_trade.LAST_NET - stock_trade.CONFIRM_NET) * stock_trade.CONFIRM_SHARE / stock_trade.ORDER_AMT * 100,4) AS 最新收益率
,ROUND((stock_trade.LAST_NET - stock_trade.CONFIRM_NET) * stock_trade.CONFIRM_SHARE / stock_trade.ORDER_AMT * 100/(DATEDIFF(NOW() ,stock_trade.TRADE_TIME)+1) ,4)
		AS 每日收益率
	,stock_trade.CONFIRM_AMT 交易金额
	,ROUND(stock_trade.LAST_NET * stock_trade.CONFIRM_SHARE ,2) 最新金额
	,ROUND(ROUND(stock_trade.LAST_NET * stock_trade.CONFIRM_SHARE ,2)-stock_trade.CONFIRM_AMT ,2) AS 收益金额
	,DATEDIFF(NOW() ,stock_trade.TRADE_TIME) AS 天数
	,stock_trade.CONFIRM_NET AS confirmNet,
	stock_trade.LAST_NET lastNet,
	stock_trade.NET_MIN_7 低7,
	stock_trade.NET_MAX_7 高7,
	NET_MIN_CLOS_7 收低7,
	NET_MAX_CLOS_7 收高7,
	ROUND((stock_trade.LAST_NET-stock_trade.NET_MIN_7)/stock_trade.NET_MIN_7*100,2) 最低率7,
	ROUND((stock_trade.NET_MAX_7-stock_trade.NET_MIN_7)/stock_trade.NET_MIN_7*100,2) 最振幅率7,
-- 	,stock_trade.NET_REDEM
	ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_7) * 100,2) AS 回撤7
	,ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_14) * 100,2) AS 回撤14
	,ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_30) * 100,2) AS 回撤30
	,ROUND(((stock_trade.NET_MIN_1-stock_trade.NET_MIN_7) / stock_trade.NET_MIN_7) * 100,2) AS 涨幅7
	,ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_360) * 100,2) AS 回撤360
	,ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_60) * 100,2) AS 回撤60
	,ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_90) * 100,2) AS 回撤90
	,ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_180) * 100,2) AS 回撤180
,stock_trade.NET_MAX_7
,stock_trade.NET_MAX_30
	,stock_trade.TYPE
FROM
	`stock_trade`
WHERE
	1 = 1
-- 	AND stock_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND stock_trade.TYPE = '证券买入'
-- 	AND stock_trade.conception LIKE ('%海南%')
-- 	AND stock_trade.FD_INFO in ('广发证券')
-- 	AND stock_trade.FD_INFO in ('HS300ETF')
-- ORDER BY stock_trade.TRADE_TIME ASC
ORDER BY 最新收益率 DESC
-- ORDER BY 最低率7 ;
-- ORDER BY每日收益率 DESC;
;

/**基金交易的最新净值-收益率**/
SELECT
	stock_trade.FD_INFO AS 最老收益倒序
	,stock_trade.CONFIRM_SHARE AS 买入份额
	,ROUND(
		(stock_trade.LAST_NET - stock_trade.CONFIRM_NET) * stock_trade.CONFIRM_SHARE / stock_trade.ORDER_AMT * 100,
		4
	) AS 最新收益率
,ROUND((stock_trade.LAST_NET - stock_trade.CONFIRM_NET) * stock_trade.CONFIRM_SHARE / stock_trade.ORDER_AMT * 100/DATEDIFF(NOW() ,stock_trade.TRADE_TIME) ,4)
		AS 每日收益率
	,stock_trade.CONFIRM_AMT AS 交易金额
,DATEDIFF(NOW() ,stock_trade.TRADE_TIME) AS 持有天数
	,stock_trade.TRADE_TIME
,ROUND((stock_trade.LAST_NET / stock_trade.NET_MAX_1) * 100,2) AS 当晚净值比最大率
	,ROUND(stock_trade.LAST_NET * stock_trade.CONFIRM_SHARE ,2) AS '最新金额'
	,stock_trade.CONFIRM_NET AS confirmNet
	,stock_trade.LAST_NET lastNet
,stock_trade.NET_MAX_30 NET_MAX_30
	,stock_trade.TYPE
	,stock_trade.BIZ_TP
FROM
	`stock_trade` stock_trade
WHERE
	1 = 1
	AND stock_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND stock_trade.TYPE = '证券买入'
-- AND stock_trade.FD_INFO LIKE '%001875|前海开源沪港深优势精选混合%'
ORDER BY stock_trade.TRADE_TIME ASC
;

/**基金交易的最新净值-收益率**/
SELECT
	stock_trade.FD_INFO AS 每日收益率排序
,DATEDIFF(NOW() ,stock_trade.TRADE_TIME) AS 持有天数
	,stock_trade.TRADE_TIME
	,ROUND(
		(stock_trade.LAST_NET - stock_trade.CONFIRM_NET) * stock_trade.CONFIRM_SHARE / stock_trade.ORDER_AMT * 100,
		4
	) AS 最新日期收益率
,ROUND((stock_trade.LAST_NET - stock_trade.CONFIRM_NET) * stock_trade.CONFIRM_SHARE / stock_trade.CONFIRM_AMT * 100/DATEDIFF(NOW() ,stock_trade.TRADE_TIME) ,4)
		AS 每日收益率
	,stock_trade.CONFIRM_SHARE
	,stock_trade.CONFIRM_AMT
	,ROUND(stock_trade.LAST_NET * stock_trade.CONFIRM_SHARE ,2) AS '最新金额'
	,stock_trade.CONFIRM_NET AS confirmNet
	,stock_trade.LAST_NET lastNet
,stock_trade.TYPE
FROM
	`stock_trade` stock_trade
-- LEFT JOIN `ol_fund_earn` ON stock_trade.FD_ID = ol_fund_earn.FD_ID
WHERE
	1 = 1
	AND stock_trade.TRADE_TIME>='2020-01-01 00:00:00'
-- 	AND DATEDIFF(NOW() ,stock_trade.TRADE_TIME)>15
-- 	AND stock_trade.TYPE = '证券买入'
-- 	AND stock_trade.CONFIRM_AMT <1000
-- 	AND stock_trade.TYPE in( '申购(赎回)' )
-- 	AND stock_trade.TYPE = '赎回'
-- AND stock_trade.FD_INFO LIKE '%160633|鹏华证券分级%'
-- ORDER BY lastDate DESC
ORDER BY TRADE_TIME DESC;
-- ORDER BY 每日收益率 DESC;

-- /**赎回**/
SELECT
	stock_trade.FD_INFO AS 赎回信息,
	stock_trade.ORDER_AMT,
	stock_trade.REDEM_SHARE,
	stock_trade.CONFIRM_NET AS confirmNet
	,stock_trade.NET_REDEM
	,stock_trade.LAST_NET lastNet
	,ROUND((stock_trade.REDEM_AMT - stock_trade.CONFIRM_AMT)/ stock_trade.CONFIRM_AMT * 100,4) AS 赎回收益率
	,ROUND((stock_trade.REDEM_AMT - stock_trade.CONFIRM_AMT)/ stock_trade.CONFIRM_AMT * 100/DATEDIFF(stock_trade.REDEM_TIME ,stock_trade.TRADE_TIME) ,4) AS 每日收益率
	,DATEDIFF(stock_trade.REDEM_TIME ,stock_trade.TRADE_TIME) AS 赎回天数
,stock_trade.CONFIRM_AMT
,stock_trade.REDEM_AMT
,stock_trade.TYPE
	,stock_trade.TRADE_TIME
	,stock_trade.REDEM_TIME
FROM
	`stock_trade` stock_trade
WHERE
	1 = 1
	AND stock_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND stock_trade.TYPE in('证券买入(卖出)')
-- AND stock_trade.FD_INFO LIKE '%160633|鹏华证券分级%'
-- ORDER BY lastDate DESC
ORDER BY 赎回收益率 DESC
-- ORDER BY 每日收益率 DESC
-- stock_trade.REDEM_TIME  DESC
;

SELECT stock_trade.FD_INFO AS 基金分组
	,SUM(stock_trade.CONFIRM_AMT)  AS sumamt
	,COUNT(stock_trade.CONFIRM_AMT)
	FROM stock_trade
WHERE 1=1
	AND stock_trade.TYPE in( '证券买入' )
	GROUP BY stock_trade.FD_INFO
	ORDER by sumamt  DESC
;

/**超过止损线**/
SELECT
	stock_trade.FD_INFO AS 超过止损线

	,stock_trade.CONFIRM_SHARE AS 买入份额
	,ROUND(
		(stock_trade.LAST_NET - stock_trade.CONFIRM_NET) * stock_trade.CONFIRM_SHARE / stock_trade.ORDER_AMT * 100,
		4
	) AS 最新收益率
,ROUND((stock_trade.LAST_NET - stock_trade.CONFIRM_NET) * stock_trade.CONFIRM_SHARE / stock_trade.ORDER_AMT * 100/DATEDIFF(NOW() ,stock_trade.TRADE_TIME) ,4)
		AS 每日收益率
	,stock_trade.CONFIRM_AMT AS 交易金额
,DATEDIFF(NOW() ,stock_trade.TRADE_TIME) AS 持有天数
	,stock_trade.TRADE_TIME
	,ROUND(stock_trade.LAST_NET * stock_trade.CONFIRM_SHARE ,2) AS '最新金额'
	,ROUND(stock_trade.CONFIRM_NET*stock_trade.RK_ST_LOSS * stock_trade.CONFIRM_SHARE ,2) AS '止损金额'
	,stock_trade.CONFIRM_NET AS confirmNet
	,stock_trade.LAST_NET lastNet
	,stock_trade.TYPE
FROM
	`stock_trade` stock_trade
-- LEFT JOIN `ol_fund_earn` ON stock_trade.FD_ID = ol_fund_earn.FD_ID
WHERE
	1 = 1
	AND stock_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND stock_trade.TYPE = '证券买入'
	AND ROUND(stock_trade.CONFIRM_NET*stock_trade.RK_ST_LOSS * stock_trade.CONFIRM_SHARE ,2) > ROUND(stock_trade.LAST_NET * stock_trade.CONFIRM_SHARE ,2)
-- 	AND stock_trade.CONFIRM_AMT > 100
-- ORDER BY TRADE_TIME ;
ORDER BY 最新收益率 DESC;

/**超过止盈线**/
SELECT
	stock_trade.FD_INFO AS 超过止盈线

	,stock_trade.CONFIRM_SHARE AS 买入份额
	,ROUND(
		(stock_trade.LAST_NET - stock_trade.CONFIRM_NET) * stock_trade.CONFIRM_SHARE / stock_trade.ORDER_AMT * 100,
		4
	) AS 最新收益率
,ROUND((stock_trade.LAST_NET - stock_trade.CONFIRM_NET) * stock_trade.CONFIRM_SHARE / stock_trade.ORDER_AMT * 100/DATEDIFF(NOW() ,stock_trade.TRADE_TIME) ,4)
		AS 每日收益率
	,stock_trade.CONFIRM_AMT AS 交易金额
,DATEDIFF(NOW() ,stock_trade.TRADE_TIME) AS 持有天数
	,stock_trade.TRADE_TIME
	,ROUND(stock_trade.LAST_NET * stock_trade.CONFIRM_SHARE ,2) AS '最新金额'
	,ROUND(stock_trade.CONFIRM_NET*stock_trade.RK_ST_PROFIT * stock_trade.CONFIRM_SHARE ,2) AS '止盈金额'
	,stock_trade.CONFIRM_NET AS confirmNet
	,stock_trade.LAST_NET lastNet
	,stock_trade.TYPE
FROM
	`stock_trade` stock_trade
-- LEFT JOIN `ol_fund_earn` ON stock_trade.FD_ID = ol_fund_earn.FD_ID
WHERE
	1 = 1
	AND stock_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND stock_trade.TYPE = '证券买入'
	AND ROUND(stock_trade.CONFIRM_NET*stock_trade.RK_ST_PROFIT * stock_trade.CONFIRM_SHARE ,2) < ROUND(stock_trade.LAST_NET * stock_trade.CONFIRM_SHARE ,2)
ORDER BY TRADE_TIME ;
-- ORDER BY FD_INFO ;
-- 每日收益率 DESC;
;

/**买卖经验**/
SELECT
	stock_trade.reason_buy,
	stock_trade.reason_sell,
	stock_trade.reason_ex,
	ROUND((stock_trade.LAST_NET - stock_trade.CONFIRM_NET) * stock_trade.CONFIRM_SHARE / stock_trade.ORDER_AMT * 100,4) AS 最新收益率,
	CASE stock_trade.TYPE
		WHEN stock_trade.TYPE != '证券买入'	THEN ''
		ELSE ROUND((stock_trade.REDEM_AMT - stock_trade.CONFIRM_AMT) / stock_trade.CONFIRM_AMT*100,2)
	END 卖出收益率,
	stock_trade.*
FROM
	`stock_trade` stock_trade
WHERE 1 = 1
-- 	AND stock_trade.TYPE = '证券买入'
-- AND stock_trade.FD_INFO LIKE '%160633|鹏华证券分级%'
ORDER BY stock_trade.TRADE_TIME
;