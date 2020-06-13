SELECT t1.FD_NAME AS 合计天天基金
,ROUND(SUM(t1.TODAY_EARN_AMT),2) AS 每只基金收益累计
,max(t1.LASTEST_NET_DATA) as maxDate
-- ,min(t1.LASTEST_NET_DATA) as minDate
,DATEDIFF(max(t1.LASTEST_NET_DATA),min(t1.LASTEST_NET_DATA)) as days
,(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=3 LIMIT 1) AS 最新日期投资金额
,ROUND(SUM(t1.TODAY_EARN_AMT)/
	(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=3 LIMIT 1)*100,2)
AS 持有收益率
-- ,t2.*
FROM
	`ol_fund_earn` t1
WHERE 1=1
	AND t1.SOURCE=3 /**ttjj	**/
GROUP BY t1.FD_NAME
-- ORDER BY t1.FD_NAME
ORDER BY maxDate desc ,t1.FD_NAME
-- ORDER BY 每只基金收益累计 DESC
-- ORDER BY maxDate desc ,持有收益率 DESC
-- ORDER BY 持有收益率 DESC
;

SELECT t1.FD_NAME AS 支付宝合计
,ROUND(SUM(t1.TODAY_EARN_AMT),2) AS 每只基金收益累计
,max(t1.LASTEST_NET_DATA) as maxDate
,(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE 1=1 AND ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=2 LIMIT 1)
AS 最新日期投资金额
,ROUND(SUM(t1.TODAY_EARN_AMT)/
	(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=2 LIMIT 1)*100,2)
AS 持有收益率
-- ,t2.*
FROM
	`ol_fund_earn` t1
WHERE 1=1
	AND t1.SOURCE=2 /**zfb	**/
GROUP BY t1.FD_NAME
-- ORDER BY ol_fund_earn.FD_NAME
-- ORDER BY 每只基金收益累计 DESC
ORDER BY maxDate desc ,持有收益率 DESC
;

SELECT t1.FD_NAME AS 京东合计
	,ROUND(SUM(t1.TODAY_EARN_AMT),2) AS 每只基金收益累计
,max(t1.LASTEST_NET_DATA) AS maxDate
,(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=1 LIMIT 1) AS 最新日期投资金额
,ROUND(SUM(t1.TODAY_EARN_AMT)/
	(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=1 LIMIT 1)*100,2)
AS 持有收益率
-- ,t2.*
FROM
	`ol_fund_earn` t1
WHERE 1=1
 	AND t1.SOURCE=1/**京东	**/
GROUP BY t1.FD_NAME
-- ORDER BY ol_fund_earn.FD_NAME
-- ORDER BY 每只基金收益累计 DESC
ORDER BY maxDate desc ,持有收益率 DESC
;

SELECT t1.FD_NAME AS 天天基金每周
,ROUND(SUM(t1.TODAY_EARN_AMT),2) AS 每只基金收益累计
,max(t1.LASTEST_NET_DATA)
,(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=3 LIMIT 1) AS 最新日期投资金额
,ROUND(SUM(t1.TODAY_EARN_AMT)/
	(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=3 LIMIT 1)*100,2)
AS 持有收益率
-- ,t2.*
FROM
	`ol_fund_earn` t1
WHERE 1=1
--  	AND t1.SOURCE=1/**京东	**/
-- 	AND t1.SOURCE=2 /**zfb	**/
	AND t1.SOURCE=3 /**ttjj	**/
-- 	AND t1.LASTEST_NET_DATA BETWEEN '2020-05-01' AND '2020-05-31' /**每月	**/
-- 	AND t1.LASTEST_NET_DATA BETWEEN '2020-05-01' AND '2020-05-10' /**每周	**/
	AND t1.LASTEST_NET_DATA BETWEEN '2020-05-10' AND '2020-05-17' /**每周	**/
GROUP BY t1.FD_NAME
-- ORDER BY t1.FD_NAME
-- ORDER BY 每只基金收益累计 DESC
ORDER BY 持有收益率 DESC
;

SELECT t1.FD_NAME,ROUND(SUM(t1.TODAY_EARN_AMT),2) AS 每只基金收益累计
,max(t1.LASTEST_NET_DATA)
,(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE 1=1 AND ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=2 LIMIT 1)
AS 最新日期投资金额
,ROUND(SUM(t1.TODAY_EARN_AMT)/
	(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=2 LIMIT 1)*100,2)
AS 持有收益率
-- ,t2.*
FROM
	`ol_fund_earn` t1
WHERE 1=1
	AND t1.SOURCE=2 /**zfb	**/
-- 	AND t1.LASTEST_NET_DATA BETWEEN '2020-05-01' AND '2020-05-31' /**每月	**/
-- 	AND t1.LASTEST_NET_DATA BETWEEN '2020-05-10' AND '2020-12-31' /**每周	**/
GROUP BY t1.FD_NAME
-- ORDER BY ol_fund_earn.FD_NAME
-- ORDER BY 每只基金收益累计 DESC
ORDER BY 持有收益率 DESC
;

SELECT t1.FD_NAME AS 京东基金
	,ROUND(SUM(t1.TODAY_EARN_AMT),2) AS 每只基金收益累计
,max(t1.LASTEST_NET_DATA)
,(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=1 LIMIT 1) AS 最新日期投资金额
,ROUND(SUM(t1.TODAY_EARN_AMT)/
	(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=1 LIMIT 1)*100,2)
AS 持有收益率
-- ,t2.*
FROM
	`ol_fund_earn` t1
WHERE 1=1
 	AND t1.SOURCE=1/**京东	**/
-- 	AND t1.LASTEST_NET_DATA BETWEEN '2020-05-01' AND '2020-05-31' /**每月	**/
-- 	AND t1.LASTEST_NET_DATA BETWEEN '2020-05-10' AND '2020-12-31' /**每周	**/
GROUP BY t1.FD_NAME
-- ORDER BY ol_fund_earn.FD_NAME
-- ORDER BY 每只基金收益累计 DESC
ORDER BY 持有收益率 DESC
;
--
-- SELECT t1.FD_NAME,ROUND(SUM(t1.TODAY_EARN_AMT),2) AS 每只基金收益累计
-- ,max(t1.LASTEST_NET_DATA)
-- ,(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=3 LIMIT 1) AS 最新日期投资金额
-- ,ROUND(SUM(t1.TODAY_EARN_AMT)/
-- 	(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=3 LIMIT 1)*100,2)
-- AS 持有收益率
-- -- ,t2.*
-- FROM
-- 	`ol_fund_earn` t1
-- WHERE 1=1
-- --  	AND t1.SOURCE=1/**京东	**/
-- -- 	AND t1.SOURCE=2 /**zfb	**/
-- 	AND t1.SOURCE=3 /**ttjj	**/
-- -- 	AND t1.LASTEST_NET_DATA BETWEEN '2020-05-01' AND '2020-05-31' /**每月	**/
-- 	AND t1.LASTEST_NET_DATA BETWEEN '2020-05-10' AND '2020-12-31' /**每周	**/
-- GROUP BY t1.FD_NAME
-- -- ORDER BY t1.FD_NAME
-- -- ORDER BY 每只基金收益累计 DESC
-- ORDER BY 持有收益率 DESC
-- ;
--
-- SELECT t1.FD_NAME,ROUND(SUM(t1.TODAY_EARN_AMT),2) AS 每只基金收益累计
-- ,max(t1.LASTEST_NET_DATA)
-- ,(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE 1=1 AND ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=2 LIMIT 1)
-- AS 最新日期投资金额
-- ,ROUND(SUM(t1.TODAY_EARN_AMT)/
-- 	(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=2 LIMIT 1)*100,2)
-- AS 持有收益率
-- -- ,t2.*
-- FROM
-- 	`ol_fund_earn` t1
-- WHERE 1=1
-- 	AND t1.SOURCE=2 /**zfb	**/
-- -- 	AND t1.LASTEST_NET_DATA BETWEEN '2020-05-01' AND '2020-05-31' /**每月	**/
-- -- 	AND t1.LASTEST_NET_DATA BETWEEN '2020-05-10' AND '2020-12-31' /**每周	**/
-- GROUP BY t1.FD_NAME
-- -- ORDER BY ol_fund_earn.FD_NAME
-- -- ORDER BY 每只基金收益累计 DESC
-- ORDER BY 持有收益率 DESC
-- ;
--
-- SELECT t1.FD_NAME AS 京东基金
-- 	,ROUND(SUM(t1.TODAY_EARN_AMT),2) AS 每只基金收益累计
-- ,max(t1.LASTEST_NET_DATA)
-- ,(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=1 LIMIT 1) AS 最新日期投资金额
-- ,ROUND(SUM(t1.TODAY_EARN_AMT)/
-- 	(SELECT ol_fund_earn.BUY_COST FROM ol_fund_earn WHERE ol_fund_earn.LASTEST_NET_DATA=max(t1.LASTEST_NET_DATA) AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.SOURCE=1 LIMIT 1)*100,2)
-- AS 持有收益率
-- -- ,t2.*
-- FROM
-- 	`ol_fund_earn` t1
-- WHERE 1=1
--  	AND t1.SOURCE=1/**京东	**/
-- -- 	AND t1.LASTEST_NET_DATA BETWEEN '2020-05-01' AND '2020-05-31' /**每月	**/
-- -- 	AND t1.LASTEST_NET_DATA BETWEEN '2020-05-10' AND '2020-12-31' /**每周	**/
-- GROUP BY t1.FD_NAME
-- -- ORDER BY ol_fund_earn.FD_NAME
-- -- ORDER BY 每只基金收益累计 DESC
-- ORDER BY 持有收益率 DESC
-- ;