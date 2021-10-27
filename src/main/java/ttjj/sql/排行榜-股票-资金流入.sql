SET @curDay=(SELECT t.date FROM rank_st_biz_com t GROUP BY date ORDER BY date DESC limit 0,1);SET @staDay=DATE_SUB(@curDay,INTERVAL 6 DAY);SET @endDay=DATE_SUB(@curDay,INTERVAL 0 DAY);-- SET @daysAdd=0;SET @curDay=DATE_SUB(curdate(),INTERVAL @daysAdd DAY);
SET @curDayAdd1=(SELECT t.date FROM rank_st_biz_com t WHERE t.date>@curDay GROUP BY date ORDER BY t.date limit 0,1);SET @curDayAdd2=(SELECT t.date FROM rank_st_biz_com t WHERE t.date>@curDay GROUP BY date ORDER BY t.date limit 1,1);SET @curDayAdd3=(SELECT t.date FROM rank_st_biz_com t WHERE t.date>@curDay GROUP BY date ORDER BY t.date limit 2,1);SET @curDayAdd4=(SELECT t.date FROM rank_st_biz_com t WHERE t.date>@curDay GROUP BY date ORDER BY t.date limit 3,1);SET @curDayAdd5=(SELECT t.date FROM rank_st_biz_com t WHERE t.date>@curDay GROUP BY date ORDER BY t.date limit 4,1);SET @curDayAdd3=(SELECT t.date FROM rank_st_biz_com t WHERE t.date>@curDay GROUP BY date ORDER BY t.date limit 2,1);SET @curDayAdd4=(SELECT t.date FROM rank_st_biz_com t WHERE t.date>@curDay GROUP BY date ORDER BY t.date limit 3,1);SET @curDayAdd10=(SELECT t.date FROM rank_st_biz_com t WHERE t.date>@curDay GROUP BY date ORDER BY t.date limit 9,1);
SET @curDaySub1=(SELECT t.date FROM rank_st_biz_com t WHERE t.date<@curDay GROUP BY date ORDER BY t.date desc limit 0,1);SET @curDaySub2=(SELECT t.date FROM rank_st_biz_com t WHERE t.date<@curDay GROUP BY date ORDER BY t.date desc limit 1,1);SET @curDaySub3=(SELECT t.date FROM rank_st_biz_com t WHERE t.date<@curDay GROUP BY date ORDER BY t.date desc limit 2,1);SET @curDaySub4=(SELECT t.date FROM rank_st_biz_com t WHERE t.date<@curDay GROUP BY date ORDER BY t.date desc limit 3,1);SET @curDaySub5=(SELECT t.date FROM rank_st_biz_com t WHERE t.date<@curDay GROUP BY date ORDER BY t.date desc limit 4,1);
SET @bizType1='%%';SET @bizType2='';/**	环保工程	**/
SET @zszMin=200*100000000;SET @zszMax=30000*100000000;/**最大流通市值 1000000 **/
SET @bizType1='酿酒行业';SET @bizType2='';/**	业务板块：酿酒行业 医疗行业 医药制造	电力行业-BK0428	石油行业	券商信托-BK0473	银行-BK0475		化工行业	有色金属-BK0478	造纸印刷-BK0470	化肥行业-BK0731	民航机场-BK0420	环保工程	**/
SET @name1='昂立教育';
/**	乖离率	**/
SELECT
	rank_st_biz_com.f14	,rank_st_biz_com.type_name 板块 -- rank_st_biz_com.f12,
-- 	,(SELECT t.f3 FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = @curDayAdd3) adr3
-- ,ROUND((SELECT t2.f62 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDayAdd3)/100000000,2) 主流日Ad3
-- 	,(SELECT t.f3 FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = @curDayAdd2) adr2
-- ,ROUND((SELECT t2.f62 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDayAdd2)/100000000,2) 主流日Ad2
	,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDayAdd1 AND t.date<=@curDayAdd5),2) adrSum5
	,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDaySub4 AND t.date<=@curDay),2) adr近5
	,(SELECT t.f3 FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = @curDayAdd1) adr1
	,ROUND((SELECT t2.f62 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDayAdd1)/100000000,2) 流加1
	,rank_st_biz_com.f3 涨幅
	,ROUND((SELECT SUM(t.f62)/100000000 FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDay AND t.date<=@curDay),2) 流近1
	,ROUND((SELECT SUM(t.f62) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDay AND t.date<=@curDay)/rank_st_biz_com.f20*100,2) 流市比近1
	,ROUND((SELECT SUM(t.f62)/100000000 FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDaySub1 AND t.date<=@curDay),2) 流近2
	,ROUND((SELECT SUM(t.f62) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDaySub1 AND t.date<=@curDay)/rank_st_biz_com.f20*100,2) 流市比近2
	,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MIN_7)/(rank_st_biz_com.NET_MAX_7-rank_st_biz_com.NET_MIN_7)*100,2) 价7,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MIN_14)/(rank_st_biz_com.NET_MAX_14-rank_st_biz_com.NET_MIN_14)*100,2) 价14,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MIN_30)/(rank_st_biz_com.NET_MAX_30-rank_st_biz_com.NET_MIN_30)*100,2) 价30,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MIN_360)/(rank_st_biz_com.NET_MAX_360-rank_st_biz_com.NET_MIN_360)*100,2) 价360
-- 	,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDayAdd1 AND t.date<=@curDayAdd3),2) adrSum3
-- 	,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDayAdd1 AND t.date<=@curDayAdd10),2) adrSum10
	,ROUND(rank_st_biz_com.f62/100000000,2) 主流今日
-- 	,(SELECT t2.f3 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDaySub1) adrDaySub1
	,ROUND((SELECT t2.f62 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDaySub1)/100000000,2) 流减1
-- 	,(SELECT t2.f3 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDaySub2) adrDaySub2
	,ROUND((SELECT t2.f62 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDaySub2)/100000000,2) 流减2
-- 	,(SELECT t2.f3 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDaySub3) adrDaySub3
	,ROUND((SELECT t2.f62 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDaySub3)/100000000,2) 流减3
	,ROUND((SELECT t2.f62 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDaySub4)/100000000,2) 流减4
-- 	,ROUND(rank_st_biz_com.f62/100000000,2)+ROUND((SELECT t2.f62 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDaySub1)/100000000,2) 主流近2日
	,ROUND((SELECT SUM(t.f62)/100000000 FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDaySub4 AND t.date<=@curDay),2) 流近5

-- 	,ROUND((SELECT SUM(t.f62) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDaySub4 AND t.date<=@curDay)/rank_st_biz_com.f20*100,2) 流市比近5
-- 	,ROUND((SELECT SUM(t.f62)/100000000 FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDaySub2 AND t.date<=@curDay),2) 流近3
-- 	,ROUND((SELECT SUM(t.f62) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDaySub2 AND t.date<=@curDay)/rank_st_biz_com.f20*100,2) 流市比近3

	,rank_st_biz_com.f9 市盈率	,rank_st_biz_com.f37 roe ,rank_st_biz_com.f8 换手
	,ROUND(rank_st_biz_com.f20/100000000,2) 市值
	,rank_st_biz_com.f10 当日量比
	,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_5)/rank_st_biz_com.NET_MA_5*100,2) 乖离5
	,(SELECT ROUND((t.f2-t.NET_MA_5)/t.NET_MA_5*100,2) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = @curDaySub1) 日减1乖离5
-- 	,ROUND(rank_st_biz_com.f69,2) 超大单比,ROUND(rank_st_biz_com.f75,2) 大单比,ROUND(rank_st_biz_com.f81,2) 中单比,ROUND(rank_st_biz_com.f87,2) 小单比	,ROUND(rank_st_biz_com.f62/100000000,2)+ROUND((SELECT t2.f62 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDaySub1)/100000000,2) 主流和近2日
-- 	,ROUND((SELECT SUM(t.f3) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDaySub3 AND t.date<=@curDaySub1),2) adrSumSub3
-- 	,ROUND((SELECT t2.f62 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDaySub1)/100000000,2)+ROUND((SELECT t2.f62 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDaySub2)/100000000,2)+ROUND((SELECT t2.f62 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDaySub3)/100000000,2) 流减3和
-- 	,(SELECT t2.f3 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDaySub4) adrDaySub4
-- 	,ROUND((SELECT t2.f62 FROM rank_st_biz_com t2 WHERE t2.f12 = rank_st_biz_com.f12 AND t2.date = @curDaySub4)/100000000,2) 流减4
-- 	,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_10)/rank_st_biz_com.NET_MA_10*100,2) 乖离10,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_20)/rank_st_biz_com.NET_MA_20*100,2) 乖离20,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_30)/rank_st_biz_com.NET_MA_30*100,2) 乖离30
-- 	,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_60)/rank_st_biz_com.NET_MA_60*100,2) 乖离60,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_120)/rank_st_biz_com.NET_MA_120*100,2) 乖离120,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MA_250)/rank_st_biz_com.NET_MA_250*100,2) 乖离250
-- 	ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MIN_7)/rank_st_biz_com.NET_MIN_7*100,2) 最低率7,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MAX_7)/rank_st_biz_com.NET_MAX_7*100,2) 最高率7,ROUND((rank_st_biz_com.NET_MAX_7-rank_st_biz_com.NET_MIN_7)/rank_st_biz_com.NET_MIN_7*100,2) 最振幅率7,	ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MIN_30)/rank_st_biz_com.NET_MIN_30*100,2) 最低率30,ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MAX_30)/rank_st_biz_com.NET_MAX_30*100,2) 最高率30,ROUND((rank_st_biz_com.NET_MAX_30-rank_st_biz_com.NET_MIN_30)/rank_st_biz_com.NET_MIN_30*100,2) 最振幅率30,
	,rank_st_biz_com.*
FROM
	`rank_st_biz_com` rank_st_biz_com
WHERE 1=1 AND rank_st_biz_com.f139=2 -- AND rank_st_biz_com.f148!=4 -- AND rank_st_biz_com.f107!=5 -- A股主板 非退市
	AND rank_st_biz_com.date = @curDay
	AND rank_st_biz_com.f20 BETWEEN @zszMin AND @zszMax -- 市值
-- 	AND ROUND((rank_st_biz_com.f2-rank_st_biz_com.NET_MIN_360)/(rank_st_biz_com.NET_MAX_360-rank_st_biz_com.NET_MIN_360)*100,2) > 30  -- 价格区间限定
-- 	AND rank_st_biz_com.type_name in (@bizType1,@bizType2) -- 业务板块
-- 	AND rank_st_biz_com.f14 =@name1
-- 	AND rank_st_biz_com.f12 in( SELECT  fupan_position.Zqdm  FROM  `fupan_position`  WHERE   fupan_position.DATE BETWEEN @staDay AND @endDay GROUP BY fupan_position.Zqdm)
-- 	AND rank_st_biz_com.f3<1
-- 	AND rank_st_biz_com.f8>5 -- 换手太高的小心筹码交换
-- 	AND rank_st_biz_com.f37>10
-- 	AND ROUND((SELECT SUM(t.f62) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDaySub1 AND t.date<=@curDay)/rank_st_biz_com.f20*100,2)>0
	AND ROUND((SELECT SUM(t.f62) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date>=@curDay AND t.date<=@curDay)/rank_st_biz_com.f20*100,2)>0
-- 市盈率不能太高 roe
-- 	AND (rank_st_biz_com.f2-rank_st_biz_com.NET_MA_5)/rank_st_biz_com.NET_MA_5>0 -- 乖离率
-- 	AND (SELECT ROUND((t.f2-t.NET_MA_5)/t.NET_MA_5*100,2) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = @curDaySub1)<0 -- 昨日乖离率
ORDER BY -- date DESC ,
	 流市比近2  DESC	/** DESC	f3	f10	f8	f62 主流今日 流减3和	主流近2日	市值	换手	市盈率	roe	**/
-- 	(SELECT ROUND((t.f2-t.NET_MA_5)/t.NET_MA_5*100,2) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = @curDaySub1)	/**	昨日乖离率**/
-- 	乖离5 -- 乖离5	日减1乖离5
-- 	(SELECT ROUND((t.f2-t.NET_MA_5)/t.NET_MA_5*100,2) FROM rank_st_biz_com t WHERE t.f12 = rank_st_biz_com.f12 AND t.date = @curDaySub1)	/**	rank_st_biz_com.date desc, f3  DESC	ROUND(rank_st_biz_com.f20,0) desc 乖离5**/
	LIMIT 0,300
;

/**周期净值比率-n天**/
-- SELECT
-- 	rank_st_biz_com.f14 AS 7日最高股票,
-- 	rank_st_biz_com.f12,
-- 	rank_st_biz_com.f3 涨幅,
-- 	ROUND(rank_st_biz_com.f62/100000000,2) 主力净流入亿,
-- 	ROUND(rank_st_biz_com.f6/100000000,2) 成交额亿,
-- 	ROUND(rank_st_biz_com.f21/100000000,2) 流通市值亿, -- 	ROUND(rank_st_biz_com.f20/100000000,2) 总市值亿,
-- 	rank_st_biz_com.f9 市盈率,
-- 	rank_st_biz_com.f2 现价,
-- 	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_7) * 100,2) AS 回撤7,
-- 	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_14) * 100,2) AS 回撤14,
-- 	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_30) * 100,2) AS 回撤30,
-- 	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_60) * 100,2) AS 回撤60,
-- 	ROUND((rank_st_biz_com.NET_MAX_1 / rank_st_biz_com.NET_MAX_360) * 100,2) AS 回撤360,
-- 	ROUND(((rank_st_biz_com.NET_MAX_7+rank_st_biz_com.NET_MIN_7) / 2),2) AS 最近7日净值中位数,
-- 	ROUND(((rank_st_biz_com.NET_MAX_14+rank_st_biz_com.NET_MIN_14) / 2),2) AS 最近14日净值中位数,
-- 	ROUND(((rank_st_biz_com.NET_MIN_1-rank_st_biz_com.NET_MIN_7) / rank_st_biz_com.NET_MIN_7) * 100,2) AS 涨幅7,
-- 	ROUND(((rank_st_biz_com.NET_MAX_7-rank_st_biz_com.NET_MIN_7) / rank_st_biz_com.NET_MIN_7) * 100,2) AS 振幅7,
-- 	ROUND(((rank_st_biz_com.NET_MIN_1-rank_st_biz_com.NET_MIN_14) / rank_st_biz_com.NET_MIN_14) * 100,2) AS 涨幅14,
-- 	ROUND(((rank_st_biz_com.NET_MAX_14-rank_st_biz_com.NET_MIN_14) / rank_st_biz_com.NET_MIN_14) * 100,2) AS 振幅14,
-- 	rank_st_biz_com.NET_MIN_14,
-- 	rank_st_biz_com.NET_MAX_14,
-- 	ROUND(((rank_st_biz_com.NET_MIN_1-rank_st_biz_com.NET_MIN_30) / rank_st_biz_com.NET_MIN_30) * 100,2) AS 涨幅30,
-- 	ROUND(((rank_st_biz_com.NET_MAX_14-rank_st_biz_com.NET_MIN_30) / rank_st_biz_com.NET_MIN_30) * 100,2) AS 振幅30,
-- 	rank_st_biz_com.NET_MIN_30,
-- 	rank_st_biz_com.NET_MAX_30,
-- 	ROUND(((rank_st_biz_com.NET_MAX_14+rank_st_biz_com.NET_MIN_30) / 2),2) AS 最近30日净值中位数,
-- 	ROUND(((rank_st_biz_com.NET_MAX_30-rank_st_biz_com.NET_MIN_30) / rank_st_biz_com.NET_MIN_30) * 100,2) AS 振幅30,
-- 	ROUND(((rank_st_biz_com.NET_MAX_360-rank_st_biz_com.NET_MIN_360) / rank_st_biz_com.NET_MIN_360) * 100,2) AS 涨幅360,
-- 	ROUND(((rank_st_biz_com.NET_MAX_60-rank_st_biz_com.NET_MIN_60) / rank_st_biz_com.NET_MIN_60) * 100,2) AS 涨幅60,
-- 	ROUND(((rank_st_biz_com.NET_MAX_90-rank_st_biz_com.NET_MIN_90) / rank_st_biz_com.NET_MIN_90) * 100,2) AS 涨幅90,
-- 	ROUND(((rank_st_biz_com.NET_MAX_180-rank_st_biz_com.NET_MIN_180) / rank_st_biz_com.NET_MIN_180) * 100,2) AS 涨幅180,
-- 	rank_st_biz_com.f2 lastNet
-- ,rank_st_biz_com.NET_MAX_360 NET_MAX_360
-- ,rank_st_biz_com.NET_MIN_360 NET_MIN_360
-- FROM
-- 	`rank_st_biz_com` rank_st_biz_com
-- WHERE 1 = 1
-- 	AND rank_st_biz_com.date = @curDay
-- 	AND rank_st_biz_com.type LIKE (@bizType1)
-- 	AND (rank_st_biz_com.f20 BETWEEN @zszMin AND @zszMax OR rank_st_biz_com.f14 IN (@ltszOr1,@ltszOr2,@ltszOr3,@ltszOr4,@ltszOr5,@ltszOr6,@ltszOr7,@ltszOr8,@ltszOr9,@ltszOr10)) /**-- 流通市值亿 **/
-- -- 	AND (rank_st_biz_com.f21 BETWEEN @zszMin AND @zszMax OR rank_st_biz_com.conception like (@area1)) /**-- 流通市值亿 **/
-- 	AND rank_st_biz_com.f14 NOT LIKE '%ST%' -- 非st
-- 	AND (rank_st_biz_com.f12 NOT LIKE '300%' AND rank_st_biz_com.f12 NOT LIKE '688%' AND rank_st_biz_com.f12 NOT LIKE '900%' AND rank_st_biz_com.f12 NOT LIKE '200%') -- 非创业板 -- 非科创板 --非B股
-- 	AND rank_st_biz_com.f20 >1000*100000000 -- 市盈率
-- -- 	AND rank_st_biz_com.conception LIKE '%海南%' -- 地域
-- -- 	AND rank_st_biz_com.conception LIKE '%疫苗%' /**-- 概念	数字货币	**/
-- -- 	AND LENGTH(rank_st_biz_com.conception)>350
-- -- 	AND rank_st_biz_com.f9 >100 -- 市盈率
-- -- GROUP BY rank_st_biz_com.FD_INFO
-- ORDER BY 回撤7 DESC,回撤14 DESC,回撤30 DESC,回撤60 DESC,回撤360 DESC
-- ;
--
--
-- /**
-- DELETE FROM rank_st_biz_com WHERE rank_st_biz_com.date = @curDay;
--  **/

 /**	排行榜-每日股票涨跌幅-概念	**/
-- SELECT
-- 	rank_st_biz_com.f12,rank_st_biz_com.f14,
-- 	rank_st_biz_com.date,
-- 	rank_st_biz_com.f3 涨幅,
-- rank_st_biz_com.type_name,
-- 	ROUND(rank_st_biz_com.f20/100000000,2) 总市值亿,
-- 	rank_st_biz_com.f2 现价,
-- 	rank_st_biz_com.conception,
-- 	rank_st_biz_com.*
-- FROM
-- 	`rank_st_biz_com` rank_st_biz_com
-- WHERE 1=1
-- 	AND rank_st_biz_com.date = @curDay
-- 	AND (rank_st_biz_com.conception LIKE '%老%' AND rank_st_biz_com.conception LIKE '%二%') /**-- 概念	数字货币	**/
-- ORDER BY f3 DESC /**涨幅 **/
-- LIMIT 0,1000;


/**
SELECT count(1) FROM `rank_st_biz_com` rank_st_biz_com WHERE 1=1 AND rank_st_biz_com.date = @curDay;
**/
/**更正日期
UPDATE `rank_st_biz_com`
SET `date` = CONCAT(
	SUBSTRING(rank_st_biz_com.date, 1, 4),
	'-',
	SUBSTRING(rank_st_biz_com.date, 5, 2),
	'-',
	SUBSTRING(rank_st_biz_com.date, 7, 2)
)
WHERE
	LENGTH(rank_st_biz_com.date) = 8
;
**/

/**
DELETE FROM `rank_st_biz_com` WHERE `date` = curdate() LIMIT 5000 ;
**/