/**周期净值比率-n天**/
SELECT
	stock_trade.FD_INFO AS 7日最高股票,
	stock_trade.FD_CODE,
	ROUND((stock_trade.LAST_NET - stock_trade.CONFIRM_NET) * stock_trade.CONFIRM_SHARE / stock_trade.ORDER_AMT * 100,4) AS 最新收益率,
	ROUND(ROUND(stock_trade.LAST_NET * stock_trade.CONFIRM_SHARE ,2)-stock_trade.CONFIRM_AMT ,2) AS 收益金额,
	stock_trade.CONFIRM_SHARE AS 买入份额,
	stock_trade.CONFIRM_NET 买入净值,
	stock_trade.LAST_NET,
	stock_trade.NET_MIN_7 低7,
	stock_trade.NET_MAX_7 高7,
	NET_MIN_CLOS_7 收低7,
	NET_MAX_CLOS_7 收高7,
	ROUND((stock_trade.LAST_NET-stock_trade.NET_MIN_7)/stock_trade.NET_MIN_7*100,2) 最低率7,
	ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_7) * 100,2) AS 回撤7,
	ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_14) * 100,2) AS 回撤14,
	ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_30) * 100,2) AS 回撤30,
	ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_60) * 100,2) AS 回撤60,
	ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_90) * 100,2) AS 回撤90,
	ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_180) * 100,2) AS 回撤180,
	ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_360) * 100,2) AS 回撤360,

	ROUND(((stock_trade.NET_MIN_1-stock_trade.NET_MIN_7) / stock_trade.NET_MIN_7) * 100,2) AS 涨幅7,
	ROUND(((stock_trade.NET_MAX_7-stock_trade.NET_MIN_7) / stock_trade.NET_MIN_7) * 100,2) AS 振幅7,
	ROUND(((stock_trade.NET_MAX_7+stock_trade.NET_MIN_7) / 2),2) AS 最近7日净值中位数,
	ROUND(((stock_trade.NET_MIN_1-stock_trade.NET_MIN_14) / stock_trade.NET_MIN_14) * 100,2) AS 涨幅14,
	ROUND(((stock_trade.NET_MAX_14-stock_trade.NET_MIN_14) / stock_trade.NET_MIN_14) * 100,2) AS 振幅14,
	stock_trade.NET_MIN_14,
	stock_trade.NET_MAX_14,
	ROUND(((stock_trade.NET_MAX_14+stock_trade.NET_MIN_14) / 2),2) AS 最近14日净值中位数,
	ROUND(((stock_trade.NET_MIN_1-stock_trade.NET_MIN_30) / stock_trade.NET_MIN_30) * 100,2) AS 涨幅30,
	ROUND(((stock_trade.NET_MAX_14-stock_trade.NET_MIN_30) / stock_trade.NET_MIN_30) * 100,2) AS 振幅30,
	stock_trade.NET_MIN_30,
	stock_trade.NET_MAX_30,
	ROUND(((stock_trade.NET_MAX_14+stock_trade.NET_MIN_30) / 2),2) AS 最近30日净值中位数,

	ROUND(((stock_trade.NET_MAX_30-stock_trade.NET_MIN_30) / stock_trade.NET_MIN_30) * 100,2) AS 振幅30,
	ROUND(((stock_trade.NET_MAX_360-stock_trade.NET_MIN_360) / stock_trade.NET_MIN_360) * 100,2) AS 涨幅360,
	ROUND(((stock_trade.NET_MAX_60-stock_trade.NET_MIN_60) / stock_trade.NET_MIN_60) * 100,2) AS 涨幅60,
	ROUND(((stock_trade.NET_MAX_90-stock_trade.NET_MIN_90) / stock_trade.NET_MIN_90) * 100,2) AS 涨幅90,
	ROUND(((stock_trade.NET_MAX_180-stock_trade.NET_MIN_180) / stock_trade.NET_MIN_180) * 100,2) AS 涨幅180,

	stock_trade.LAST_NET lastNet
,stock_trade.NET_MAX_360 NET_MAX_360
,stock_trade.NET_MIN_360 NET_MIN_360
	,stock_trade.BIZ_TP
FROM
	`stock_trade` stock_trade
WHERE 1 = 1
	AND stock_trade.TYPE = '证券买入'
-- 	AND stock_trade.FD_INFO in( '芯片ETF','三安光电')
-- 	AND (stock_trade.NET_MAX_1 / stock_trade.NET_MAX_30)=(stock_trade.NET_MAX_1 / stock_trade.NET_MAX_360)
-- GROUP BY stock_trade.FD_INFO
-- ORDER BY 回撤7 DESC,回撤14 DESC,回撤30 DESC,ROUND((stock_trade.LAST_NET - stock_trade.CONFIRM_NET) * stock_trade.CONFIRM_SHARE / stock_trade.ORDER_AMT * 100,4) DESC
ORDER BY 最低率7
;

/**周期净值比率-n天**/
SELECT
	stock_trade.FD_INFO AS 涨幅7股票
	,ROUND(((stock_trade.NET_MIN_1-stock_trade.NET_MIN_7) / stock_trade.NET_MIN_7) * 100,2) AS 涨幅7
	,ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_7) * 100,2) AS 回撤7
	,ROUND(((stock_trade.NET_MAX_7-stock_trade.NET_MIN_7) / stock_trade.NET_MIN_7) * 100,2) AS 振幅7
	,ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_14) * 100,2) AS 回撤14
	,ROUND(((stock_trade.NET_MIN_1-stock_trade.NET_MIN_14) / stock_trade.NET_MIN_14) * 100,2) AS 涨幅14
	,ROUND(((stock_trade.NET_MAX_14-stock_trade.NET_MIN_14) / stock_trade.NET_MIN_14) * 100,2) AS 振幅14
	,ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_30) * 100,2) AS 回撤30
	,ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_60) * 100,2) AS 回撤60
	,ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_90) * 100,2) AS 回撤90
	,ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_180) * 100,2) AS 回撤180
	,ROUND(((stock_trade.NET_MAX_30-stock_trade.NET_MIN_30) / stock_trade.NET_MIN_30) * 100,2) AS 振幅30
	,ROUND(((stock_trade.NET_MAX_360-stock_trade.NET_MIN_360) / stock_trade.NET_MIN_360) * 100,2) AS 涨幅360
	,ROUND(((stock_trade.NET_MAX_60-stock_trade.NET_MIN_60) / stock_trade.NET_MIN_60) * 100,2) AS 涨幅60
	,ROUND(((stock_trade.NET_MAX_90-stock_trade.NET_MIN_90) / stock_trade.NET_MIN_90) * 100,2) AS 涨幅90
	,ROUND(((stock_trade.NET_MAX_180-stock_trade.NET_MIN_180) / stock_trade.NET_MIN_180) * 100,2) AS 涨幅180
	,ROUND((stock_trade.NET_MAX_1 / stock_trade.NET_MAX_360) * 100,2) AS 回撤360
	,stock_trade.LAST_NET lastNet
,stock_trade.NET_MAX_360 NET_MAX_360
,stock_trade.NET_MIN_360 NET_MIN_360
	,stock_trade.BIZ_TP
FROM
	`stock_trade` stock_trade
WHERE 1 = 1
	AND stock_trade.TYPE = '证券买入'
-- 	AND (stock_trade.NET_MAX_1 / stock_trade.NET_MAX_30)=(stock_trade.NET_MAX_1 / stock_trade.NET_MAX_360)
GROUP BY stock_trade.FD_INFO
ORDER BY 涨幅7
;