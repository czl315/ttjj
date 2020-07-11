/**基金交易的最新净值-收益率
	(
		SELECT
			t1.LASTEST_NET
		FROM
			`ol_fund_earn` t1
		WHERE
			t1.`FD_NAME` = ol_fund_trade.FD_INFO
		ORDER BY
			t1.`LASTEST_NET_DATA` DESC
		LIMIT 1
	) AS lastNet,
	ROUND(
		(
			(
				SELECT
					t1.LASTEST_NET
				FROM
					`ol_fund_earn` t1
				WHERE
					t1.`FD_NAME` = ol_fund_trade.FD_INFO
				ORDER BY
					t1.`LASTEST_NET_DATA` DESC
				LIMIT 1
			) - ol_fund_trade.CONFIRM_NET
		) * ol_fund_trade.CONFIRM_SHARE / ol_fund_trade.ORDER_AMT * 100,
		4
	) AS 最新日期收益率
**/
SELECT
	ol_fund_trade.FD_INFO,
	ol_fund_trade.TRADE_TIME,
	ol_fund_trade.ORDER_AMT,
	ol_fund_trade.CONFIRM_NET AS confirmNet,
ol_fund_trade.LAST_NET lastNet,
-- 	(
-- 		SELECT
-- 			MAX(t1.LASTEST_NET)
-- 		FROM
-- 			`ol_fund_earn` t1
-- 		WHERE
-- 			t1.`FD_NAME` = ol_fund_trade.FD_INFO
-- 		LIMIT 1
-- 	) AS maxNet,
-- 	ROUND(
-- 		(
-- 			(
-- 				SELECT
-- 					MAX(t1.LASTEST_NET)
-- 				FROM
-- 					`ol_fund_earn` t1
-- 				WHERE
-- 					t1.`FD_NAME` = ol_fund_trade.FD_INFO
-- 			) - ol_fund_trade.CONFIRM_NET
-- 		) / ol_fund_trade.CONFIRM_NET * 100,
-- 		4
-- 	) AS 最大收益率,
	-- 	(SELECT t1.LASTEST_NET FROM `ol_fund_earn` t1 WHERE t1.`FD_NAME` = ol_fund_trade.FD_INFO ORDER BY t1.`LASTEST_NET_DATA` DESC LIMIT 1) - ol_fund_trade.CONFIRM_NET AS syAmt,
	ROUND(
		(
			ol_fund_trade.LAST_NET - ol_fund_trade.CONFIRM_NET
		) * ol_fund_trade.CONFIRM_SHARE / ol_fund_trade.ORDER_AMT * 100,
		4
	) AS 最新日期收益率
,DATEDIFF(NOW() ,ol_fund_trade.TRADE_TIME) AS 现今持有天数
,DATEDIFF(ol_fund_trade.REDEM_TIME ,ol_fund_trade.TRADE_TIME) AS 赎回天数
,ol_fund_trade.CONFIRM_AMT
,ol_fund_trade.REDEM_AMT
,ol_fund_trade.TYPE
FROM
	`ol_fund_trade` ol_fund_trade
-- LEFT JOIN `ol_fund_earn` ON ol_fund_trade.FD_ID = ol_fund_earn.FD_ID
WHERE
	1 = 1
	AND ol_fund_trade.TRADE_TIME>='2020-01-01 00:00:00'
	AND ol_fund_trade.TYPE = '申购'
-- 	AND ol_fund_trade.TYPE in( '申购(赎回)' )
-- 	AND ol_fund_trade.TYPE = '赎回'
-- AND ol_fund_trade.FD_INFO LIKE '%160633|鹏华证券分级%'
-- ORDER BY lastDate DESC
-- ORDER BY 最大收益率 DESC
ORDER BY
	最新日期收益率 DESC;

-- 	(
-- 		SELECT
-- 			t2.LASTEST_NET_DATA
-- 		FROM
-- 			`ol_fund_earn` t2
-- 		WHERE
-- 			t2.`FD_NAME` = ol_fund_trade.FD_INFO
-- 		AND t2.`LASTEST_NET` = (
-- 			SELECT
-- 				MAX(t1.LASTEST_NET)
-- 			FROM
-- 				`ol_fund_earn` t1
-- 			WHERE
-- 				t1.`FD_NAME` = ol_fund_trade.FD_INFO
-- 			LIMIT 1
-- 		)
-- 		LIMIT 1
-- 	) maxNetDate,

