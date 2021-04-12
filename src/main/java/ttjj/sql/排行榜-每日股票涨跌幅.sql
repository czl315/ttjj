set @curDay= '20210412';
SET @liuTongShiZhiEnd=1000000*100000000;/**最大流通市值 1000000 **/
-- SET @bizType1="BK0486"; SET @liuTongShiZhiStart=200*100000000; /**文化传媒**/
-- SET @bizType1="BK0479"; SET @liuTongShiZhiStart=400*100000000; /**钢铁行业**/
-- SET @bizType1="BK0450"; SET @liuTongShiZhiStart=500*100000000; /**港口水运**/
-- SET @bizType1="BK0485"; SET @liuTongShiZhiStart=80*100000000; SET @area1='%海南%';/**旅游酒店**/
/**环保工程**/
	SET @bizType1="BK0728"; SET @liuTongShiZhiStart=100*100000000;
	SET @ltszOr1 ='中材节能';SET @ltszOr2 ='南网能源';SET @ltszOr3 ='远达环保';SET @ltszOr4 ='盛剑环境';SET @ltszOr5 ='中再资环';
	SET @ltszOr6 ='';SET @ltszOr7 ='东旭蓝天';SET @ltszOr8 ='太和水';SET @ltszOr9 ='清新环境';SET @ltszOr10='岭南股份';
-- SET @bizType1="BK0473"; SET @bizType2="";/**券商信托**/
-- SET @bizType1="BK0425"; SET @bizType2="";/**工程建设**/
-- SET @bizType1="BK0459"; /**电子元件 **/
-- SET @bizType1="BK0477"; /**酿酒行业 **/
-- SET @bizType1="BK0737"; /**软件服务**/
-- SET @bizType1="BK0727"; SET @bizType2="BK0465"; SET @liuTongShiZhiStart=200*100000000; /**医疗行业**/
-- SET @ltszOr1 ='葫芦娃';SET @ltszOr2 ='紫鑫药业';SET @ltszOr3 ='哈三联';SET @ltszOr4 ='九 芝 堂';SET @ltszOr5 ='双鹭药业';
-- 桂林三金 哈药股份 海王生物 仁和药业 万泰生物 中新药业 康恩贝 华润双鹤 吉林敖东

-- SET @bizType1="BK0729"; /**船舶制造**/
/**	排行榜-每日股票涨跌幅	**/
SELECT
	rank_st_biz_com.f12,rank_st_biz_com.f14,
	rank_st_biz_com.f3 涨幅,
	ROUND(rank_st_biz_com.f62/100000000,2) 主力净流入亿,
	ROUND(rank_st_biz_com.f6/100000000,2) 成交额亿,
	ROUND(rank_st_biz_com.f21/100000000,2) 流通市值亿, -- 	ROUND(rank_st_biz_com.f20/100000000,2) 总市值亿,
	rank_st_biz_com.conception,
	rank_st_biz_com.f9 市盈率,
	rank_st_biz_com.f2 现价,
	rank_st_biz_com.NET_MIN_7 低7,
	rank_st_biz_com.NET_MAX_7 高7,
	rank_st_biz_com.NET_MIN_CLOS_7 收低7,
	rank_st_biz_com.NET_MAX_CLOS_7 收高7,
	ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MIN_7)/rank_st_biz_com.NET_MIN_7*100,2) 最低率7,
	ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MAX_7)/rank_st_biz_com.NET_MAX_7*100,2) 最高率7,
	ROUND((rank_st_biz_com.NET_MAX_7-rank_st_biz_com.NET_MIN_7)/rank_st_biz_com.NET_MIN_7*100,2) 最振幅率7,

	rank_st_biz_com.*
FROM
	`rank_st_biz_com` rank_st_biz_com
WHERE 1=1
	AND rank_st_biz_com.date = @curDay
	AND rank_st_biz_com.type IN (@bizType1,@bizType2)
	AND rank_st_biz_com.f21 BETWEEN @liuTongShiZhiStart AND @liuTongShiZhiEnd OR rank_st_biz_com.f14 IN (@ltszOr1,@ltszOr2,@ltszOr3,@ltszOr4,@ltszOr5,@ltszOr6,@ltszOr7,@ltszOr8,@ltszOr9,@ltszOr10) /**-- 流通市值亿 **/
-- 	AND (rank_st_biz_com.f21 BETWEEN @liuTongShiZhiStart AND @liuTongShiZhiEnd OR rank_st_biz_com.conception like (@area1)) /**-- 流通市值亿 **/
	AND rank_st_biz_com.f14 NOT LIKE '%ST%' -- 非st
	AND (rank_st_biz_com.f12 NOT LIKE '300%' AND rank_st_biz_com.f12 NOT LIKE '688%' AND rank_st_biz_com.f12 NOT LIKE '900%') -- 非创业板 -- 非科创板 --非B股
-- 	AND rank_st_biz_com.conception LIKE '%海南%' -- 概念
-- 	AND rank_st_biz_com.f9 >100 -- 市盈率
-- ORDER BY f3 DESC /**涨幅 **/
ORDER BY 主力净流入亿 DESC
-- ORDER BY f12,date DESC
-- ORDER BY 流通市值亿 DESC
LIMIT 0,
 1000;

/**周期净值比率-n天**/
SELECT
	rank_st_biz_com.f14 AS 7日最高股票,
	rank_st_biz_com.f12,
	rank_st_biz_com.f3 涨幅,
	ROUND(rank_st_biz_com.f62/100000000,2) 主力净流入亿,
	ROUND(rank_st_biz_com.f6/100000000,2) 成交额亿,
	ROUND(rank_st_biz_com.f21/100000000,2) 流通市值亿, -- 	ROUND(rank_st_biz_com.f20/100000000,2) 总市值亿,
	rank_st_biz_com.f9 市盈率,
	rank_st_biz_com.f2 现价,
	rank_st_biz_com.NET_MIN_7,
	rank_st_biz_com.NET_MAX_7,
	rank_st_biz_com.NET_MIN_CLOS_7,
	rank_st_biz_com.NET_MAX_CLOS_7,
	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_7) * 100,2) AS 回撤7,
	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_14) * 100,2) AS 回撤14,
	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_30) * 100,2) AS 回撤30,
	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_60) * 100,2) AS 回撤60,
	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_90) * 100,2) AS 回撤90,
	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_180) * 100,2) AS 回撤180,
	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_360) * 100,2) AS 回撤360,
	ROUND(((rank_st_biz_com.NET_MAX_7+rank_st_biz_com.NET_MIN_7) / 2),2) AS 最近7日净值中位数,
	ROUND(((rank_st_biz_com.NET_MAX_14+rank_st_biz_com.NET_MIN_14) / 2),2) AS 最近14日净值中位数,
	ROUND(((rank_st_biz_com.NET_MIN_1-rank_st_biz_com.NET_MIN_7) / rank_st_biz_com.NET_MIN_7) * 100,2) AS 涨幅7,
	ROUND(((rank_st_biz_com.NET_MAX_7-rank_st_biz_com.NET_MIN_7) / rank_st_biz_com.NET_MIN_7) * 100,2) AS 振幅7,
	ROUND(((rank_st_biz_com.NET_MIN_1-rank_st_biz_com.NET_MIN_14) / rank_st_biz_com.NET_MIN_14) * 100,2) AS 涨幅14,
	ROUND(((rank_st_biz_com.NET_MAX_14-rank_st_biz_com.NET_MIN_14) / rank_st_biz_com.NET_MIN_14) * 100,2) AS 振幅14,
	rank_st_biz_com.NET_MIN_14,
	rank_st_biz_com.NET_MAX_14,
	ROUND(((rank_st_biz_com.NET_MIN_1-rank_st_biz_com.NET_MIN_30) / rank_st_biz_com.NET_MIN_30) * 100,2) AS 涨幅30,
	ROUND(((rank_st_biz_com.NET_MAX_14-rank_st_biz_com.NET_MIN_30) / rank_st_biz_com.NET_MIN_30) * 100,2) AS 振幅30,
	rank_st_biz_com.NET_MIN_30,
	rank_st_biz_com.NET_MAX_30,
	ROUND(((rank_st_biz_com.NET_MAX_14+rank_st_biz_com.NET_MIN_30) / 2),2) AS 最近30日净值中位数,
	ROUND(((rank_st_biz_com.NET_MAX_30-rank_st_biz_com.NET_MIN_30) / rank_st_biz_com.NET_MIN_30) * 100,2) AS 振幅30,
	ROUND(((rank_st_biz_com.NET_MAX_360-rank_st_biz_com.NET_MIN_360) / rank_st_biz_com.NET_MIN_360) * 100,2) AS 涨幅360,
	ROUND(((rank_st_biz_com.NET_MAX_60-rank_st_biz_com.NET_MIN_60) / rank_st_biz_com.NET_MIN_60) * 100,2) AS 涨幅60,
	ROUND(((rank_st_biz_com.NET_MAX_90-rank_st_biz_com.NET_MIN_90) / rank_st_biz_com.NET_MIN_90) * 100,2) AS 涨幅90,
	ROUND(((rank_st_biz_com.NET_MAX_180-rank_st_biz_com.NET_MIN_180) / rank_st_biz_com.NET_MIN_180) * 100,2) AS 涨幅180,
	rank_st_biz_com.f2 lastNet
,rank_st_biz_com.NET_MAX_360 NET_MAX_360
,rank_st_biz_com.NET_MIN_360 NET_MIN_360
FROM
	`rank_st_biz_com` rank_st_biz_com
WHERE 1 = 1
	AND rank_st_biz_com.date = @curDay
	AND rank_st_biz_com.type IN (@bizType1,@bizType2)
-- 	AND (rank_st_biz_com.f21 BETWEEN @liuTongShiZhiStart AND @liuTongShiZhiEnd ) /**-- 流通市值亿 **/
	AND rank_st_biz_com.f14 NOT LIKE '%ST%' -- 非st
	AND (rank_st_biz_com.f12 NOT LIKE '300%' AND rank_st_biz_com.f12 NOT LIKE '688%' AND rank_st_biz_com.f12 NOT LIKE '900%') -- 非创业板 -- 非科创板 --非B股
-- 	AND rank_st_biz_com.conception LIKE '%医疗美容%' -- 概念
-- 	AND rank_st_biz_com.f9 <75 -- 市盈率
-- 	AND rank_st_biz_com.TYPE = '证券买入'
-- 	AND (rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_30)=(rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_360)
-- GROUP BY rank_st_biz_com.FD_INFO
ORDER BY 回撤7 DESC,回撤14 DESC,回撤30 DESC,回撤60 DESC,回撤90 DESC,回撤180 DESC,回撤360 DESC
;

/**可以低吸**/
SELECT
	rank_st_biz_com.f14 AS 可以低吸,
	rank_st_biz_com.f12,
	rank_st_biz_com.f3 涨幅,
	ROUND(rank_st_biz_com.f62/100000000,2) 主力净流入亿,
	ROUND(rank_st_biz_com.f6/100000000,2) 成交额亿,
	ROUND(rank_st_biz_com.f21/100000000,2) 流通市值亿, -- 	ROUND(rank_st_biz_com.f20/100000000,2) 总市值亿,
	rank_st_biz_com.f9 市盈率,
	rank_st_biz_com.f2 现价,
rank_st_biz_com.date,
	rank_st_biz_com.NET_MIN_7 低7,
	rank_st_biz_com.NET_MAX_7 高7,
	rank_st_biz_com.NET_MIN_CLOS_7 收低7,
	rank_st_biz_com.NET_MAX_CLOS_7 收高7,
	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_7) * 100,2) AS 回撤7,
	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_14) * 100,2) AS 回撤14,
	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_30) * 100,2) AS 回撤30,
	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_60) * 100,2) AS 回撤60,
	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_90) * 100,2) AS 回撤90,
	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_180) * 100,2) AS 回撤180,
	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_360) * 100,2) AS 回撤360,
	ROUND(((rank_st_biz_com.NET_MAX_7+rank_st_biz_com.NET_MIN_7) / 2),2) AS 最近7日净值中位数,
	ROUND(((rank_st_biz_com.NET_MAX_14+rank_st_biz_com.NET_MIN_14) / 2),2) AS 最近14日净值中位数,
	ROUND(((rank_st_biz_com.NET_MIN_1-rank_st_biz_com.NET_MIN_7) / rank_st_biz_com.NET_MIN_7) * 100,2) AS 涨幅7,
	ROUND(((rank_st_biz_com.NET_MAX_7-rank_st_biz_com.NET_MIN_7) / rank_st_biz_com.NET_MIN_7) * 100,2) AS 振幅7,
	ROUND(((rank_st_biz_com.NET_MIN_1-rank_st_biz_com.NET_MIN_14) / rank_st_biz_com.NET_MIN_14) * 100,2) AS 涨幅14,
	ROUND(((rank_st_biz_com.NET_MAX_14-rank_st_biz_com.NET_MIN_14) / rank_st_biz_com.NET_MIN_14) * 100,2) AS 振幅14,
	rank_st_biz_com.NET_MIN_14,
	rank_st_biz_com.NET_MAX_14,
	ROUND(((rank_st_biz_com.NET_MIN_1-rank_st_biz_com.NET_MIN_30) / rank_st_biz_com.NET_MIN_30) * 100,2) AS 涨幅30,
	ROUND(((rank_st_biz_com.NET_MAX_14-rank_st_biz_com.NET_MIN_30) / rank_st_biz_com.NET_MIN_30) * 100,2) AS 振幅30,
	rank_st_biz_com.NET_MIN_30,
	rank_st_biz_com.NET_MAX_30,
	ROUND(((rank_st_biz_com.NET_MAX_14+rank_st_biz_com.NET_MIN_30) / 2),2) AS 最近30日净值中位数,
	ROUND(((rank_st_biz_com.NET_MAX_30-rank_st_biz_com.NET_MIN_30) / rank_st_biz_com.NET_MIN_30) * 100,2) AS 振幅30,
	ROUND(((rank_st_biz_com.NET_MAX_360-rank_st_biz_com.NET_MIN_360) / rank_st_biz_com.NET_MIN_360) * 100,2) AS 涨幅360,
	ROUND(((rank_st_biz_com.NET_MAX_60-rank_st_biz_com.NET_MIN_60) / rank_st_biz_com.NET_MIN_60) * 100,2) AS 涨幅60,
	ROUND(((rank_st_biz_com.NET_MAX_90-rank_st_biz_com.NET_MIN_90) / rank_st_biz_com.NET_MIN_90) * 100,2) AS 涨幅90,
	ROUND(((rank_st_biz_com.NET_MAX_180-rank_st_biz_com.NET_MIN_180) / rank_st_biz_com.NET_MIN_180) * 100,2) AS 涨幅180,
	rank_st_biz_com.f2 lastNet
,rank_st_biz_com.NET_MAX_360 NET_MAX_360
,rank_st_biz_com.NET_MIN_360 NET_MIN_360
FROM
	`rank_st_biz_com` rank_st_biz_com
WHERE 1 = 1
	AND rank_st_biz_com.f14 in (
		'分众传媒','中青旅'
-- 		,'立讯精密','春秋航空','科大讯飞','宇通客车','上港集团','中国建筑','广发证券','燕京啤酒',
-- 		'中国巨石','三安光电','紫金矿业','君正集团'
	)
-- 	AND rank_st_biz_com.f9 <75 -- 市盈率
-- 	AND rank_st_biz_com.TYPE = '证券买入'
-- 	AND (rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_30)=(rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_360)
-- GROUP BY rank_st_biz_com.FD_INFO
ORDER BY f14 ,回撤7 DESC,回撤14 DESC,回撤30 DESC,回撤60 DESC,回撤90 DESC,回撤180 DESC,回撤360 DESC
;

/**
DELETE FROM rank_st_biz_com WHERE rank_st_biz_com.date = @curDay;
 **/