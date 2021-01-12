
/**	分组-行业	*/
SELECT 	ol_fund_trade.FD_INFO AS 科技基金
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
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
	FROM ol_fund_trade
	WHERE 1=1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
	AND ol_fund_trade.BIZ_TP = '科技'
ORDER BY 最新收益率 DESC
;

/**	分组-行业	*/
SELECT 	ol_fund_trade.FD_INFO AS 指数
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
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
	FROM ol_fund_trade
	WHERE 1=1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
	AND ol_fund_trade.BIZ_TP = '指数'
ORDER BY 最新收益率 DESC
;

/**	分组-行业	*/
SELECT 	ol_fund_trade.FD_INFO AS 金融
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
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
	FROM ol_fund_trade
	WHERE 1=1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
	AND ol_fund_trade.BIZ_TP = '金融'
ORDER BY 最新收益率 DESC
;

/**	分组-医药	*/
SELECT 	ol_fund_trade.FD_INFO AS 医药
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
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
	FROM ol_fund_trade
	WHERE 1=1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
	AND ol_fund_trade.BIZ_TP = '医药'
ORDER BY 最新收益率 DESC
;


/**	分组-行业	*/
SELECT 	ol_fund_trade.FD_INFO AS 消费
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
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
	FROM ol_fund_trade
	WHERE 1=1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
	AND ol_fund_trade.BIZ_TP = '消费'
ORDER BY 最新收益率 DESC
;

/**	分组-行业	*/
SELECT 	ol_fund_trade.FD_INFO AS 建材农业有色传媒
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
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
	FROM ol_fund_trade
	WHERE 1=1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
	AND ol_fund_trade.BIZ_TP in ('建材','农业','有色','传媒')
ORDER BY 最新收益率 DESC
;

/**	分组-行业	*/
SELECT ol_fund_trade.BIZ_TP
	FROM ol_fund_trade
	WHERE 1=1
	AND ol_fund_trade.BIZ_TP NOT IN ('债券','货币')
	GROUP BY ol_fund_trade.BIZ_TP
;

/**	分组-行业	*/
SELECT 	ol_fund_trade.FD_INFO AS 计划卖出基金
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
	,ol_fund_trade.TYPE
	,ol_fund_trade.BIZ_TP
	FROM ol_fund_trade
	WHERE 1=1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
	AND ol_fund_trade.FD_INFO in( '161903|万家行业优选混合(LOF)','005967|鹏华创新驱动混合','005621|中欧品质消费股票C')
ORDER BY 最新收益率 DESC
;