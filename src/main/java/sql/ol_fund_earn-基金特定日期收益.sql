/** 按照日期分组统计 **/
SELECT ol_fund_earn.LASTEST_NET_DATA,ROUND(SUM(ol_fund_earn.TODAY_EARN_AMT),2) as 三个渠道总收益,
	ROUND(SUM(ol_fund_earn.BUY_COST),2) as 成本,
	SUM(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2)) AS 持有金额
FROM
	`ol_fund_earn`
WHERE 1=1
--  	AND ol_fund_earn.SOURCE=1/**京东	**/
-- 	AND ol_fund_earn.SOURCE=2 /**zfb	**/
-- AND ol_fund_earn.SOURCE=3 /**ttjj	**/
--   AND ol_fund_earn.LASTEST_NET_DATA BETWEEN '2020-01-01' AND '2020-12-31'
-- 	AND ol_fund_earn.LASTEST_NET_DATA  ='2020-02-03'
GROUP BY ol_fund_earn.LASTEST_NET_DATA
ORDER BY ol_fund_earn.LASTEST_NET_DATA DESC
-- ORDER BY 收益 DESC
;

/** 按照日期分组统计 **/
SELECT ol_fund_earn.LASTEST_NET_DATA,ROUND(SUM(ol_fund_earn.TODAY_EARN_AMT),2) as 京东收益,
	ROUND(SUM(ol_fund_earn.BUY_COST),2) as 成本,
	SUM(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2)) AS 持有金额
FROM
	`ol_fund_earn`
WHERE 1=1
 	AND ol_fund_earn.SOURCE=1/**京东	**/
-- 	AND ol_fund_earn.SOURCE=2 /**zfb	**/
-- AND ol_fund_earn.SOURCE=3 /**ttjj	**/
  AND ol_fund_earn.LASTEST_NET_DATA BETWEEN '2020-01-01' AND '2020-12-31'
-- 	AND ol_fund_earn.LASTEST_NET_DATA  ='2020-02-03'
GROUP BY ol_fund_earn.LASTEST_NET_DATA
ORDER BY ol_fund_earn.LASTEST_NET_DATA DESC
-- ORDER BY 收益 DESC
;
/** 按照日期分组统计 **/
SELECT ol_fund_earn.LASTEST_NET_DATA,ROUND(SUM(ol_fund_earn.TODAY_EARN_AMT),2) as zfb收益,
	ROUND(SUM(ol_fund_earn.BUY_COST),2) as 成本,
	SUM(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2)) AS 持有金额
FROM
	`ol_fund_earn`
WHERE 1=1
	AND ol_fund_earn.SOURCE=2 /**zfb	**/
  AND ol_fund_earn.LASTEST_NET_DATA BETWEEN '2020-01-01' AND '2020-12-31'
-- 	AND ol_fund_earn.LASTEST_NET_DATA  ='2020-02-03'
GROUP BY ol_fund_earn.LASTEST_NET_DATA
ORDER BY ol_fund_earn.LASTEST_NET_DATA DESC
-- ORDER BY 收益 DESC
;

/** 按照日期分组统计 **/
SELECT ol_fund_earn.LASTEST_NET_DATA,ROUND(SUM(ol_fund_earn.TODAY_EARN_AMT),2) as ttjj收益,
	ROUND(SUM(ol_fund_earn.BUY_COST),2) as 成本,
	SUM(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2)) AS 持有金额
FROM
	`ol_fund_earn`
WHERE 1=1
	AND ol_fund_earn.SOURCE=3 /**ttjj	**/
  AND ol_fund_earn.LASTEST_NET_DATA BETWEEN '2020-01-01' AND '2020-12-31'
-- 	AND ol_fund_earn.LASTEST_NET_DATA  ='2020-02-03'
GROUP BY ol_fund_earn.LASTEST_NET_DATA
ORDER BY ol_fund_earn.LASTEST_NET_DATA DESC
-- ORDER BY 收益 DESC
;

SELECT  ROUND(SUM(ol_fund_earn.TODAY_EARN_AMT),2) AS 2020收益累计
FROM
	`ol_fund_earn`
WHERE 1=1
--  	AND ol_fund_earn.SOURCE=1/**京东	**/
-- 		AND ol_fund_earn.SOURCE=2 /**zfb	**/
AND ol_fund_earn.SOURCE=3 /**ttjj	**/
-- 	AND ol_fund_earn.LASTEST_NET_DATA BETWEEN '2020-01-01' AND '2020-12-31'
-- 	AND ol_fund_earn.LASTEST_NET_DATA BETWEEN '2020-02-01' AND '2020-02-31'
-- 	AND ol_fund_earn.LASTEST_NET_DATA BETWEEN '2020-03-01' AND '2020-03-31'
	AND ol_fund_earn.LASTEST_NET_DATA BETWEEN '2020-01-01' AND '2020-12-31'
;

SELECT DATE_FORMAT(ol_fund_earn.LASTEST_NET_DATA,'%Y-%m') AS 月份, ROUND(SUM(ol_fund_earn.TODAY_EARN_AMT),2) AS 每月收益累计
FROM
	`ol_fund_earn`
WHERE 1=1
--  	AND ol_fund_earn.SOURCE=1/**京东	**/
-- 		AND ol_fund_earn.SOURCE=2 /**zfb	**/
-- AND ol_fund_earn.SOURCE=3 /**ttjj	**/
	AND ol_fund_earn.LASTEST_NET_DATA BETWEEN '2020-01-01' AND '2020-12-31'
GROUP BY DATE_FORMAT(ol_fund_earn.LASTEST_NET_DATA,'%Y-%m')
;

SELECT WEEK(ol_fund_earn.LASTEST_NET_DATA)+1 AS 周,
	ROUND(SUM(ol_fund_earn.TODAY_EARN_AMT),2) AS 三类渠道每周收益累计
FROM
	`ol_fund_earn`
WHERE 1=1
--  	AND ol_fund_earn.SOURCE=1/**京东	**/
-- 		AND ol_fund_earn.SOURCE=2 /**zfb	**/
-- AND ol_fund_earn.SOURCE=3 /**ttjj	**/
	AND ol_fund_earn.LASTEST_NET_DATA BETWEEN '2020-01-01' AND '2020-12-31'
GROUP BY WEEK(ol_fund_earn.LASTEST_NET_DATA)
ORDER BY WEEK(ol_fund_earn.LASTEST_NET_DATA) DESC
;
SELECT WEEK(ol_fund_earn.LASTEST_NET_DATA)+1 AS 周,
	ROUND(SUM(ol_fund_earn.TODAY_EARN_AMT),2) AS 每周收益累计京东
FROM
	`ol_fund_earn`
WHERE 1=1
 	AND ol_fund_earn.SOURCE=1/**京东	**/
	AND ol_fund_earn.LASTEST_NET_DATA BETWEEN '2020-01-01' AND '2020-12-31'
GROUP BY WEEK(ol_fund_earn.LASTEST_NET_DATA)
ORDER BY WEEK(ol_fund_earn.LASTEST_NET_DATA) DESC
;
SELECT WEEK(ol_fund_earn.LASTEST_NET_DATA)+1 AS 周,
	ROUND(SUM(ol_fund_earn.TODAY_EARN_AMT),2) AS 每周收益累计zfb
FROM
	`ol_fund_earn`
WHERE 1=1
		AND ol_fund_earn.SOURCE=2 /**zfb	**/
	AND ol_fund_earn.LASTEST_NET_DATA BETWEEN '2020-01-01' AND '2020-12-31'
GROUP BY WEEK(ol_fund_earn.LASTEST_NET_DATA)
ORDER BY WEEK(ol_fund_earn.LASTEST_NET_DATA) DESC
;
SELECT WEEK(ol_fund_earn.LASTEST_NET_DATA)+1 AS 周,
	ROUND(SUM(ol_fund_earn.TODAY_EARN_AMT),2) AS 每周收益累计ttjj
FROM
	`ol_fund_earn`
WHERE 1=1
	AND ol_fund_earn.SOURCE=3 /**ttjj	**/
	AND ol_fund_earn.LASTEST_NET_DATA BETWEEN '2020-01-01' AND '2020-12-31'
GROUP BY WEEK(ol_fund_earn.LASTEST_NET_DATA)
ORDER BY WEEK(ol_fund_earn.LASTEST_NET_DATA) DESC
;

SELECT DATE_FORMAT(ol_fund_earn.LASTEST_NET_DATA,'%Y-%m-%d') AS 日期, ROUND(SUM(ol_fund_earn.TODAY_EARN_AMT),2) AS 每日收益累计
FROM
	`ol_fund_earn`
WHERE 1=1
--  	AND ol_fund_earn.SOURCE=1/**京东	**/
-- 		AND ol_fund_earn.SOURCE=2 /**zfb	**/
AND ol_fund_earn.SOURCE=3 /**ttjj	**/
	AND ol_fund_earn.LASTEST_NET_DATA BETWEEN '2020-01-01' AND '2020-12-31'
GROUP BY DATE_FORMAT(ol_fund_earn.LASTEST_NET_DATA,'%Y-%m-%d')
;

/** 基金特定日期收益 **/
SELECT ol_fund_earn.FD_NAME,ol_fund_earn.FD_TYPE,
	'' as 日期,
	ol_fund_earn.TODAY_EARN_AMT as 今日收益,
	ROUND(ol_fund_earn.BUY_COST,2) as 成本,
	ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2) AS 持有金额,
	ROUND(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2)-ol_fund_earn.BUY_COST,2) as 持有收益金额
FROM
	`ol_fund_earn`
WHERE 1=1
-- 	AND ol_fund_earn.SOURCE=1 /**京东	**/
-- 	AND ol_fund_earn.SOURCE=2 /**zfb	**/
AND ol_fund_earn.SOURCE=3 /**ttjj	**/
-- 	AND ol_fund_earn.LASTEST_NET_DATA ='2020-02-03'/** 新冠病毒A股跌至2746 **/
-- 	AND ol_fund_earn.LASTEST_NET_DATA ='2020-03-06'
-- 	AND ol_fund_earn.LASTEST_NET_DATA ='2020-03-09'
AND ol_fund_earn.LASTEST_NET_DATA ='2020-04-22'
UNION
SELECT '999999基金类-持仓-合计收益' AS FD_NAME,'',
	ol_fund_earn.LASTEST_NET_DATA,
	ROUND(SUM(ol_fund_earn.TODAY_EARN_AMT),2) as 收益,
	ROUND(SUM(ol_fund_earn.BUY_COST),2) as 成本,
	SUM(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2)) AS 持有金额,
	ROUND(SUM(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2))-SUM(ol_fund_earn.BUY_COST),2) AS 收益累计
FROM
	`ol_fund_earn`
WHERE 1=1
 	AND ol_fund_earn.SOURCE=1/**京东	**/
-- 	AND ol_fund_earn.SOURCE=2 /**zfb	**/
-- AND ol_fund_earn.SOURCE=3 /**ttjj	**/
-- 	AND ol_fund_earn.LASTEST_NET_DATA ='2020-03-09'
AND ol_fund_earn.LASTEST_NET_DATA ='2020-04-22'
-- ORDER BY FD_TYPE DESC
-- ORDER BY 今日收益 DESC
-- ORDER BY 持有收益金额 DESC
ORDER BY FD_NAME ASC
;







SELECT ol_fund_earn.FD_NAME,ROUND(SUM(ol_fund_earn.TODAY_EARN_AMT),2) AS 每只基金收益累计
FROM
	`ol_fund_earn`
WHERE 1=1
--  	AND ol_fund_earn.SOURCE=1/**京东	**/
-- 	AND ol_fund_earn.SOURCE=2 /**zfb	**/
	AND ol_fund_earn.SOURCE=3 /**ttjj	**/
	AND ol_fund_earn.LASTEST_NET_DATA BETWEEN '2000-01-01' AND '2020-12-31'
-- 	AND ol_fund_earn.FD_NAME = '000656|前海开源沪深300指数'
GROUP BY ol_fund_earn.FD_NAME
-- ORDER BY ol_fund_earn.FD_NAME
ORDER BY 每只基金收益累计 DESC
;









/** 2019每月收益累计

SELECT '2020-03合计收益' AS 月底合计收益,ol_fund_earn.LASTEST_NET_DATA,
	ROUND(SUM(ol_fund_earn.BUY_COST),2) as 成本,
	SUM(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2)) AS 持有金额,
	ROUND(SUM(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2))-SUM(ol_fund_earn.BUY_COST),2) AS 收益累计
FROM
	`ol_fund_earn`
WHERE 1=1 AND ol_fund_earn.LASTEST_NET_DATA in ('2020-02-12')
UNION
SELECT '2020-02合计收益' AS 月底合计收益,ol_fund_earn.LASTEST_NET_DATA,
	ROUND(SUM(ol_fund_earn.BUY_COST),2) as 成本,
	SUM(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2)) AS 持有金额,
	ROUND(SUM(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2))-SUM(ol_fund_earn.BUY_COST),2) AS 收益累计
FROM
	`ol_fund_earn`
WHERE 1=1 AND ol_fund_earn.LASTEST_NET_DATA in ('2020-02-28')
UNION
SELECT '2020-01合计收益',ol_fund_earn.LASTEST_NET_DATA,
	ROUND(SUM(ol_fund_earn.BUY_COST),2) as 成本,
	SUM(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2)) AS 持有金额,
	ROUND(SUM(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2))-SUM(ol_fund_earn.BUY_COST),2) AS 收益累计
FROM
	`ol_fund_earn`
WHERE 1=1 AND ol_fund_earn.LASTEST_NET_DATA in ('2020-01-23')
UNION
SELECT '2019-12合计收益',ol_fund_earn.LASTEST_NET_DATA,
	ROUND(SUM(ol_fund_earn.BUY_COST),2) as 成本,
	SUM(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2)) AS 持有金额,
	ROUND(SUM(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2))-SUM(ol_fund_earn.BUY_COST),2) AS 收益累计
FROM
	`ol_fund_earn`
WHERE 1=1 AND ol_fund_earn.LASTEST_NET_DATA in ('2019-12-31')

UNION
SELECT '2019-11合计收益',ol_fund_earn.LASTEST_NET_DATA,
	ROUND(SUM(ol_fund_earn.BUY_COST),2) as 成本,
	SUM(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2)) AS 持有金额,
	ROUND(SUM(ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2))-SUM(ol_fund_earn.BUY_COST),2) AS 收益累计
FROM
	`ol_fund_earn`
WHERE 1=1 AND ol_fund_earn.LASTEST_NET_DATA in ('2019-11-29')
ORDER BY LASTEST_NET_DATA DESC
;
**/
-- SELECT tp.endAmt,tp.startAmt
-- FROM
-- (SELECT
-- 	(SELECT ol_fund_earn.HOLD_EARN_AMT FROM `ol_fund_earn`
-- 		WHERE ol_fund_earn.LASTEST_NET_DATA in ('2020-02-03') AND ol_fund_earn.FD_NAME = t.FD_NAME
--
-- 	) as startAmt,
--   (SELECT ol_fund_earn.HOLD_EARN_AMT FROM `ol_fund_earn`
-- 		WHERE ol_fund_earn.LASTEST_NET_DATA in ('2020-02-28') AND ol_fund_earn.FD_NAME = t.FD_NAME
--
-- 	) as endAmt
-- -- 	,endAmt-startAmt as betweenAmt
-- FROM
-- 	`ol_fund_earn` t
-- WHERE 1=1
-- 			AND t.SOURCE=1 /**京东	**/
-- 			AND t.FD_NAME in ('民生加银鑫享债券C'  )
-- LIMIT 1
-- ) tp;


