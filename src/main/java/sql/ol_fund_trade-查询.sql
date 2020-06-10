/**基金交易的最新净值-收益率**/
SELECT
	ol_fund_trade.FD_INFO,
	(SELECT t1.LASTEST_NET_DATA FROM `ol_fund_earn` t1 WHERE t1.`FD_NAME` = ol_fund_trade.FD_INFO ORDER BY t1.`LASTEST_NET_DATA` DESC LIMIT 1) lastDate,
	(SELECT t2.LASTEST_NET_DATA FROM `ol_fund_earn` t2 WHERE t2.`FD_NAME` = ol_fund_trade.FD_INFO AND t2.`LASTEST_NET` =
		(SELECT MAX(t1.LASTEST_NET) FROM `ol_fund_earn` t1 WHERE t1.`FD_NAME` = ol_fund_trade.FD_INFO LIMIT 1) LIMIT 1) maxNetDate,
	ol_fund_trade.CONFIRM_NET AS confirmNet,
	(SELECT t1.LASTEST_NET FROM `ol_fund_earn` t1 WHERE t1.`FD_NAME` = ol_fund_trade.FD_INFO ORDER BY t1.`LASTEST_NET_DATA` DESC LIMIT 1) AS lastNet,
	(SELECT MAX(t1.LASTEST_NET) FROM `ol_fund_earn` t1 WHERE t1.`FD_NAME` = ol_fund_trade.FD_INFO LIMIT 1) AS maxNet,
	ROUND(
		((SELECT Min(t1.LASTEST_NET) FROM `ol_fund_earn` t1 WHERE t1.`FD_NAME` = ol_fund_trade.FD_INFO) - ol_fund_trade.CONFIRM_NET) / ol_fund_trade.CONFIRM_NET *100
	,4) AS 最小收益率,
	ROUND(
		((SELECT MAX(t1.LASTEST_NET) FROM `ol_fund_earn` t1 WHERE t1.`FD_NAME` = ol_fund_trade.FD_INFO) - ol_fund_trade.CONFIRM_NET) / ol_fund_trade.CONFIRM_NET *100
	,4) AS 最大收益率,
-- 	(SELECT t1.LASTEST_NET FROM `ol_fund_earn` t1 WHERE t1.`FD_NAME` = ol_fund_trade.FD_INFO ORDER BY t1.`LASTEST_NET_DATA` DESC LIMIT 1) - ol_fund_trade.CONFIRM_NET AS syAmt,
	ROUND(((SELECT t1.LASTEST_NET FROM `ol_fund_earn` t1 WHERE t1.`FD_NAME` = ol_fund_trade.FD_INFO ORDER BY t1.`LASTEST_NET_DATA` DESC LIMIT 1) - ol_fund_trade.CONFIRM_NET) * ol_fund_trade.CONFIRM_SHARE
		/ ol_fund_trade.ORDER_AMT *100,4
	) AS 最新日期收益率,
		ol_fund_trade.CONFIRM_NET_DATA,
	ol_fund_trade.ORDER_AMT
FROM
	`ol_fund_trade` ol_fund_trade
-- INNER JOIN `ol_fund_earn` ON ol_fund_trade.FD_ID = ol_fund_earn.FD_ID
WHERE 1=1
	AND ol_fund_trade.ORDER_STATUS = '买入成功'
-- 	ol_fund_trade.FD_INFO LIKE '%160416|华安标普全球石油指数%'
-- ORDER BY lastDate DESC
-- ORDER BY 最大收益率 DESC
ORDER BY 最新日期收益率 DESC
;

SELECT
ol_fund.`NAME` AS '基金名称',
	ol_fund_trade.*
FROM
	`ol_fund_trade` LEFT JOIN ol_fund ON ol_fund_trade.FD_ID = ol_fund.ID
ORDER BY ol_fund_trade.TRADE_TIME DESC
LIMIT 0,1000;

SELECT
	DATE_FORMAT(ol_fund_trade.trade_time,'%Y-%m'),ROUND(SUM(ol_fund_trade.EARN_AMT),2)
FROM
	`ol_fund_trade`
GROUP BY DATE_FORMAT(ol_fund_trade.trade_time,'%Y-%m')
ORDER BY ol_fund_trade.TRADE_TIME DESC
LIMIT 0,1000;

