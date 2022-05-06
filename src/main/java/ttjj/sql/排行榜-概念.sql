SET @curDay = (SELECT date FROM rank_st_biz GROUP BY date ORDER BY date DESC limit 0,1);/**'20210618'	DATE_FORMAT(DATE_SUB(curdate(),INTERVAL 0 DAY),'%Y-%m-%d')	**/
SET @curDayAdd1=(SELECT date FROM rank_st_biz WHERE date>@curDay GROUP BY date ORDER BY date limit 0,1);/**	SELECT @curDayAdd1; **/
SET @zszyMin = 10*100000000;SET @zszyMax = 900*100000000;	
SET @zszyOr1 = '159851';SET @zszyOr2 = '159996';SET @zszyOr3 = '159869';SET @zszyOr4 = '516010';SET @zszyOr5 = '515150';SET @zszyOr6 = '159870';SET @zszyOr7 = '159732';SET @zszyOr8 = '159865';SET @zszyOr9 = '159981';SET @zszyOr10 = '513360';	SET @zszyOr11 = '515250';	
SET @in1 ='515700';SET @in2= '';SET @in3= '';SET @in4= '';

/**	涨幅	**/
SELECT
	rank_st_biz.f14 AS 涨幅etf,rank_st_biz.f12,rank_st_biz.f3 涨幅 -- 	,ROUND((rank_st_biz.f15-(rank_st_biz.f18))/(rank_st_biz.f18)*100,2) 最高比	,ROUND(ROUND((rank_st_biz.f15-(rank_st_biz.f18))/(rank_st_biz.f18)*100,3)-rank_st_biz.f3,2)	最高回落
	,ROUND((NET_TODAY_1500-NET_TODAY_1445)/NET_TODAY_1445*100,2) 涨刻1500,ROUND((NET_TODAY_1445-NET_TODAY_1430)/NET_TODAY_1430*100,2) 涨刻1445 ,ROUND((NET_TODAY_1430-NET_TODAY_1415)/NET_TODAY_1415*100,2) 涨刻1430 ,ROUND((NET_TODAY_1415-NET_TODAY_1400)/NET_TODAY_1400*100,2) 涨刻1415 ,ROUND((NET_TODAY_1400-NET_TODAY_1345)/NET_TODAY_1345*100,2) 涨刻1400,ROUND((NET_TODAY_1345-NET_TODAY_1330)/NET_TODAY_1330*100,2) 涨刻1345 ,ROUND((NET_TODAY_1330-NET_TODAY_1315)/NET_TODAY_1315*100,2) 涨刻1330 ,ROUND((NET_TODAY_1315-NET_TODAY_1130)/NET_TODAY_1130*100,2) 涨刻1315 
-- 	,ROUND((NET_TODAY_1130-NET_TODAY_1115)/NET_TODAY_1115*100,2) 涨刻1130 	,ROUND((NET_TODAY_1115-NET_TODAY_1100)/NET_TODAY_1100*100,2) 涨刻1115 ,ROUND((NET_TODAY_1100-NET_TODAY_1045)/NET_TODAY_1045*100,2) 涨刻1100 ,ROUND((NET_TODAY_1045-NET_TODAY_1030)/NET_TODAY_1030*100,2) 涨刻1045 ,ROUND((NET_TODAY_1030-NET_TODAY_1015)/NET_TODAY_1015*100,2) 涨刻1030 ,ROUND((NET_TODAY_1015-NET_TODAY_1000)/NET_TODAY_1000*100,2) 涨刻1015 ,ROUND((NET_TODAY_1000-NET_TODAY_0945)/NET_TODAY_0945*100,2) 涨刻1000 ,ROUND((NET_TODAY_0945-rank_st_biz.f18)/rank_st_biz.f18*100,2) 涨刻0945 
	,ROUND((NET_TODAY_1500-NET_TODAY_1400)/NET_TODAY_1400*100,2) 涨时1500 ,ROUND((NET_TODAY_1400-NET_TODAY_1130)/NET_TODAY_1130*100,2) 涨时1400 ,ROUND((NET_TODAY_1130-NET_TODAY_1030)/NET_TODAY_1030*100,2) 涨时1130 ,ROUND((NET_TODAY_1030-rank_st_biz.f18)/rank_st_biz.f18*100,2) 涨时1030 
	,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_7)/(rank_st_biz.NET_MAX_7-rank_st_biz.NET_MIN_7)*100,2) 现价区间7,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_14)/(rank_st_biz.NET_MAX_14-rank_st_biz.NET_MIN_14)*100,2) 现价区间14,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_30)/(rank_st_biz.NET_MAX_30-rank_st_biz.NET_MIN_30)*100,2) 现价区间30,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_360)/(rank_st_biz.NET_MAX_360-rank_st_biz.NET_MIN_360)*100,2) 现价区间360
	,rank_st_biz.*
FROM `rank_st_biz` rank_st_biz
WHERE 1 = 1 AND rank_st_biz.type IN ('gn') -- 'etf','LOF'
	AND rank_st_biz.date = @curDay 
ORDER BY 涨刻1430 DESC	-- 	涨刻0945	涨刻1000	涨刻1015	涨刻1030	涨刻1115	涨刻1130	涨半时1130
;

/**	半时	**/
SELECT
	rank_st_biz.f14 AS 涨半时,rank_st_biz.f12,rank_st_biz.f3 涨幅
	,ROUND((NET_TODAY_1500-NET_TODAY_1430)/NET_TODAY_1430*100,2) 涨半时1500,ROUND((NET_TODAY_1430-NET_TODAY_1400)/NET_TODAY_1400*100,2) 涨半时1430 ,ROUND((NET_TODAY_1400-NET_TODAY_1130)/NET_TODAY_1130*100,2) 涨半时1400 ,ROUND((NET_TODAY_1330-NET_TODAY_1130)/NET_TODAY_1130*100,2) 涨半时1330 ,ROUND((NET_TODAY_1130-NET_TODAY_1030)/NET_TODAY_1030*100,2) 涨半时1130 	,ROUND((NET_TODAY_1100-NET_TODAY_1030)/NET_TODAY_1030*100,2) 涨半时1100 	,ROUND((NET_TODAY_1030-NET_TODAY_1000)/NET_TODAY_1000*100,2) 涨半时1030,ROUND((NET_TODAY_1000-f18)/f18*100,2) 涨半时1000 
	,rank_st_biz.*
FROM `rank_st_biz` rank_st_biz WHERE 1 = 1 AND rank_st_biz.date = @curDay 	AND rank_st_biz.type IN ('gn') -- 'etf','LOF'
ORDER BY 涨半时1430 DESC	-- 涨半时1500	涨半时1430	涨半时1400	涨半时1330	涨半时1130	涨半时1100	·涨半时1030	涨半时1000
;

/**	涨时	**/
SELECT
	rank_st_biz.f14 AS 涨时 ,rank_st_biz.f3 今日涨幅
	,ROUND((NET_TODAY_1500-NET_TODAY_1400)/NET_TODAY_1400*100,2) 涨时1500 ,ROUND((NET_TODAY_1400-NET_TODAY_1130)/NET_TODAY_1130*100,2) 涨时1400 ,ROUND((NET_TODAY_1130-NET_TODAY_1030)/NET_TODAY_1030*100,2) 涨时1130 ,ROUND((NET_TODAY_1030-rank_st_biz.f18)/rank_st_biz.f18*100,2) 涨时1030 
-- 	,rank_st_biz.*
FROM
	`rank_st_biz` rank_st_biz
WHERE 1 = 1
	AND rank_st_biz.date = @curDay 
	AND rank_st_biz.type IN ('gn') -- 'etf','LOF','bk''gn'
ORDER BY /**rank_st_biz.f3 DESC	现价区间30 	DESC	振幅 f7	最高比	最低比	5日乖离率	rank_st_biz.date DESC **/
	rank_st_biz.f3 DESC	
-- 	涨时1030 DESC	-- 涨时1500	涨半时1330	涨时1030
;


/**	刻	**/
-- SELECT
-- 	rank_st_biz.f14 AS 涨刻,rank_st_biz.f12,rank_st_biz.f3 涨幅
-- 	,ROUND((rank_st_biz.NET_TODAY_1500-rank_st_biz.NET_TODAY_1445)/rank_st_biz.NET_TODAY_1445*100,2) 涨刻1500,ROUND((rank_st_biz.NET_TODAY_1445-rank_st_biz.NET_TODAY_1430)/rank_st_biz.NET_TODAY_1430*100,2) 涨刻1445 ,ROUND((rank_st_biz.NET_TODAY_1430-rank_st_biz.NET_TODAY_1415)/rank_st_biz.NET_TODAY_1415*100,2) 涨刻1430 ,ROUND((rank_st_biz.NET_TODAY_1415-rank_st_biz.NET_TODAY_1400)/rank_st_biz.NET_TODAY_1400*100,2) 涨刻1415 ,ROUND((rank_st_biz.NET_TODAY_1400-rank_st_biz.NET_TODAY_1345)/rank_st_biz.NET_TODAY_1345*100,2) 涨刻1400,ROUND((rank_st_biz.NET_TODAY_1345-rank_st_biz.NET_TODAY_1330)/rank_st_biz.NET_TODAY_1330*100,2) 涨刻1345 ,ROUND((rank_st_biz.NET_TODAY_1330-rank_st_biz.NET_TODAY_1315)/rank_st_biz.NET_TODAY_1315*100,2) 涨刻1330 ,ROUND((rank_st_biz.NET_TODAY_1315-rank_st_biz.NET_TODAY_1130)/rank_st_biz.NET_TODAY_1130*100,2) 涨刻1315 ,ROUND((rank_st_biz.NET_TODAY_1130-rank_st_biz.NET_TODAY_1115)/rank_st_biz.NET_TODAY_1115*100,2) 涨刻1130 	,ROUND((rank_st_biz.NET_TODAY_1115-rank_st_biz.NET_TODAY_1100)/rank_st_biz.NET_TODAY_1100*100,2) 涨刻1115 ,ROUND((rank_st_biz.NET_TODAY_1100-rank_st_biz.NET_TODAY_1045)/rank_st_biz.NET_TODAY_1045*100,2) 涨刻1100 ,ROUND((rank_st_biz.NET_TODAY_1045-rank_st_biz.NET_TODAY_1030)/rank_st_biz.NET_TODAY_1030*100,2) 涨刻1045 ,ROUND((rank_st_biz.NET_TODAY_1030-rank_st_biz.NET_TODAY_1015)/rank_st_biz.NET_TODAY_1015*100,2) 涨刻1030 ,ROUND((rank_st_biz.NET_TODAY_1015-rank_st_biz.NET_TODAY_1000)/rank_st_biz.NET_TODAY_1000*100,2) 涨刻1015 ,ROUND((rank_st_biz.NET_TODAY_1000-rank_st_biz.NET_TODAY_0945)/rank_st_biz.NET_TODAY_0945*100,2) 涨刻1000 ,ROUND((rank_st_biz.NET_TODAY_0945-rank_st_biz.f18)/rank_st_biz.f18*100,2) 涨刻0945 
-- 	,rank_st_biz.*
-- FROM `rank_st_biz` rank_st_biz
-- WHERE 1 = 1 AND rank_st_biz.date = @curDay AND rank_st_biz.type IN ('gn') -- 'etf','LOF'
-- ORDER BY 
-- 	涨刻1315 DESC	-- 涨刻1500	涨刻1445	涨刻1430	涨刻1415	涨刻1400	涨刻1345
-- ;


/**	乖离率	**/
-- SELECT
-- 	rank_st_biz.f14 AS 乖离率,rank_st_biz.f12
-- 	,ROUND(rank_st_biz.f20/100000000,2) 总市值亿
-- 	,rank_st_biz.f3 当前日涨幅
-- 	,(SELECT t.f3 FROM rank_st_biz t WHERE t.date = @curDayAdd1 AND t.f12=rank_st_biz.f12) 加1日涨幅
-- 	,ROUND((rank_st_biz.f2-rank_st_biz.NET_MA_5)/rank_st_biz.NET_MA_5*100,2) 5日乖离率	-- ,ROUND(fupan_position.Zxjg-fupan_position.NET_MA_5,2) 5日线差额
-- 	,ROUND((rank_st_biz.f2-rank_st_biz.NET_MA_10)/rank_st_biz.NET_MA_10*100,2) 10日乖离率,ROUND((rank_st_biz.f2-rank_st_biz.NET_MA_20)/rank_st_biz.NET_MA_20*100,2) 20日乖离率,ROUND((rank_st_biz.f2-rank_st_biz.NET_MA_30)/rank_st_biz.NET_MA_30*100,2) 30日乖离率,ROUND((rank_st_biz.f2-rank_st_biz.NET_MA_60)/rank_st_biz.NET_MA_60*100,2) 60日乖离率,ROUND((rank_st_biz.f2-rank_st_biz.NET_MA_120)/rank_st_biz.NET_MA_120*100,2) 120日乖离率,ROUND((rank_st_biz.f2-rank_st_biz.NET_MA_250)/rank_st_biz.NET_MA_250*100,2) 250日乖离率
-- -- 	,ROUND(((rank_st_biz.f2-rank_st_biz.NET_MA_5)/rank_st_biz.NET_MA_5*100+(rank_st_biz.f2-rank_st_biz.NET_MA_250)/rank_st_biz.NET_MA_250*100),2) 5加250日乖离率
-- ,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_7)/(rank_st_biz.NET_MAX_7-rank_st_biz.NET_MIN_7)*100,2) 现价区间7,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_14)/(rank_st_biz.NET_MAX_14-rank_st_biz.NET_MIN_14)*100,2) 现价区间14,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_30)/(rank_st_biz.NET_MAX_30-rank_st_biz.NET_MIN_30)*100,2) 现价区间30,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_360)/(rank_st_biz.NET_MAX_360-rank_st_biz.NET_MIN_360)*100,2) 现价区间360
-- 	,rank_st_biz.*
-- FROM
-- 	`rank_st_biz` rank_st_biz
-- WHERE 1 = 1
-- 	AND rank_st_biz.date = @curDay
-- 	AND rank_st_biz.f20 >= @zszyMin -- AND	@zszyMax-- OR rank_st_biz.f12 IN (@zszyOr1,@zszyOr2,@zszyOr3,@zszyOr4,@zszyOr5,@zszyOr6,@zszyOr7,@zszyOr8,@zszyOr9,@zszyOr10))
-- 	AND rank_st_biz.type = 'gn'
-- -- 	AND rank_st_biz.f12 in(@in1)
-- -- 	AND rank_st_biz.f14 LIKE @bkLike /**板块  创业 500	有色	**/
-- -- 	AND ((rank_st_biz.f14 NOT LIKE @nameNoLike1 ) AND (rank_st_biz.f14 NOT LIKE @nameNoLike2) AND (rank_st_biz.f14 NOT LIKE @nameNoLike3) AND (rank_st_biz.f14 NOT LIKE @nameNoLike4))
-- -- 	AND	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_7)/(rank_st_biz.NET_MAX_7-rank_st_biz.NET_MIN_7)*100,2)<=50 /**现价区间**/
-- -- 	AND	ROUND((rank_st_biz.f15-(rank_st_biz.f18))/(rank_st_biz.f18)*100,3)<=0.3 -- 最高比
-- ORDER BY 5日乖离率
-- 	 /**rank_st_biz.f3 DESC 	DESC	振幅 f7	最高比	最低比	5日乖离率	5加250日乖离率 **/
-- -- 	rank_st_biz.date DESC
-- ;

/**	周期-星期**/
-- SELECT
-- 	rank_st_biz.f14 AS 周期星期,rank_st_biz.f12,rank_st_biz.`week`
-- 	,rank_st_biz.f223
-- 	,rank_st_biz.f3 涨幅
-- 	,(SELECT t.f3 FROM rank_st_biz t WHERE t.date = @curDayWeekSub1 AND t.f12=rank_st_biz.f12) 上1周涨跌
-- 	,(SELECT t.f3 FROM rank_st_biz t WHERE t.date = @curDayWeekSub2 AND t.f12=rank_st_biz.f12) 上2周涨跌
-- 	,(SELECT t.f3 FROM rank_st_biz t WHERE t.date = @curDayWeekSub3 AND t.f12=rank_st_biz.f12) 上3周涨跌
-- 	,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_7)/(rank_st_biz.NET_MAX_7-rank_st_biz.NET_MIN_7)*100,2) 现价区间7,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_14)/(rank_st_biz.NET_MAX_14-rank_st_biz.NET_MIN_14)*100,2) 现价区间14,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_30)/(rank_st_biz.NET_MAX_30-rank_st_biz.NET_MIN_30)*100,2) 现价区间30,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_360)/(rank_st_biz.NET_MAX_360-rank_st_biz.NET_MIN_360)*100,2) 现价区间360
-- 	,rank_st_biz.date
-- 	,ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_7) * 100,2) AS 回撤7,ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_14) * 100,2) AS 回撤14,ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_30) * 100,2) AS 回撤30,ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_60) * 100,2) AS 回撤60,ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_90) * 100,2) AS 回撤90,ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_180) * 100,2) AS 回撤180,ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_360) * 100,2) AS 回撤360
-- 	,rank_st_biz.*
-- FROM
-- 	`rank_st_biz` rank_st_biz
-- WHERE 1 = 1
-- 	AND rank_st_biz.date = @curDay
-- -- 	AND rank_st_biz.`week` = @curDayWeek
-- 	AND (rank_st_biz.f20 BETWEEN @zszyMin AND	@zszyMax  OR rank_st_biz.f12 IN (@zszyOr1,@zszyOr2,@zszyOr3,@zszyOr4,@zszyOr5,@zszyOr6,@zszyOr7,@zszyOr8,@zszyOr9,@zszyOr10))
-- 	AND rank_st_biz.type IN ('etf','LOF') -- 'etf','LOF'
-- -- 	AND rank_st_biz.f12 in(@in1)
-- -- 	AND rank_st_biz.f12 in('513100','159995','516970','513500','159819','515000','515880','515220','512690','512170','516150','512800','159981','512880','512660','512980','159920','513330','159940','512400','515210','159869','159825','512200','510880','512960','515030','518880','515790','159928','516760','515170','515050','513050','515150','159870')
-- -- 	AND rank_st_biz.f14 LIKE @bkLike 
-- -- 	AND ((rank_st_biz.f14 NOT LIKE @nameNoLike1 ) AND (rank_st_biz.f14 NOT LIKE @nameNoLike2) AND (rank_st_biz.f14 NOT LIKE @nameNoLike3) AND (rank_st_biz.f14 NOT LIKE @nameNoLike4))
-- -- 	AND	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_7)/(rank_st_biz.NET_MAX_7-rank_st_biz.NET_MIN_7)*100,2)<=50 /**现价区间**/
-- -- 	AND	ROUND((rank_st_biz.f15-(rank_st_biz.f18))/(rank_st_biz.f18)*100,3)<=0.3 -- 最高比
-- ORDER BY /**rank_st_biz.f3 DESC	现价区间30 	DESC	振幅 f7	最高比	最低比	5日乖离率	rank_st_biz.date DESC **/
-- 	rank_st_biz.date DESC,rank_st_biz.f3 DESC	
-- -- 	现价区间30
-- -- 	5日乖离率
-- ;

/**	涨幅	
-- 	,ROUND(rank_st_biz.f20/100000000,2) 总市值亿
-- 	rank_st_biz.pt_time_min,rank_st_biz.pt_time_max,
**/
-- SELECT
-- 	rank_st_biz.f14 AS 最高低比
-- 	,rank_st_biz.f12
-- 	,ROUND((rank_st_biz.f15-rank_st_biz.f18)/rank_st_biz.f18*100,3) 最高比
-- 	,rank_st_biz.f3 涨幅
-- 	,ROUND((rank_st_biz.f16-rank_st_biz.f18)/rank_st_biz.f18*100,3) 最低比
-- 	,rank_st_biz.f7 振幅
-- 	,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_7)/(rank_st_biz.NET_MAX_7-rank_st_biz.NET_MIN_7)*100,2) 现价区间7
-- 	,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_14)/(rank_st_biz.NET_MAX_14-rank_st_biz.NET_MIN_14)*100,2) 现价区间14
-- 	,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_30)/(rank_st_biz.NET_MAX_30-rank_st_biz.NET_MIN_30)*100,2) 现价区间30
-- 	,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_360)/(rank_st_biz.NET_MAX_360-rank_st_biz.NET_MIN_360)*100,2) 现价区间360
-- 	,rank_st_biz.NET_MIN_7 低7
-- 	,rank_st_biz.NET_MAX_7 高7
-- 	,rank_st_biz.f2 净值
-- 	,rank_st_biz.date
-- 	,ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_7)/rank_st_biz.NET_MIN_7*100,2) 最低率7
-- 	,ROUND((rank_st_biz.f2-rank_st_biz.NET_MAX_7)/rank_st_biz.NET_MAX_7*100,2) 最高率7
-- 	,ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_7) * 100,2) AS 回撤7,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_14) * 100,2) AS 回撤14,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_30) * 100,2) AS 回撤30,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_60) * 100,2) AS 回撤60,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_90) * 100,2) AS 回撤90,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_180) * 100,2) AS 回撤180,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_360) * 100,2) AS 回撤360,
-- 	ROUND(((rank_st_biz.NET_MIN_1-rank_st_biz.NET_MIN_7) / rank_st_biz.NET_MIN_7) * 100,2) AS 涨幅7,
-- 	ROUND(((rank_st_biz.NET_MAX_7-rank_st_biz.NET_MIN_7) / rank_st_biz.NET_MIN_7) * 100,2) AS 振幅7,
-- 	ROUND(((rank_st_biz.NET_MIN_1-rank_st_biz.NET_MIN_14) / rank_st_biz.NET_MIN_14) * 100,2) AS 涨幅14,
-- 	ROUND(((rank_st_biz.NET_MAX_14-rank_st_biz.NET_MIN_14) / rank_st_biz.NET_MIN_14) * 100,2) AS 振幅14,
-- 	rank_st_biz.NET_MIN_14,
-- 	rank_st_biz.NET_MAX_14,
-- 	ROUND(((rank_st_biz.NET_MIN_1-rank_st_biz.NET_MIN_30) / rank_st_biz.NET_MIN_30) * 100,2) AS 涨幅30,
-- 	ROUND(((rank_st_biz.NET_MAX_14-rank_st_biz.NET_MIN_30) / rank_st_biz.NET_MIN_30) * 100,2) AS 振幅30,
-- 	rank_st_biz.NET_MIN_30,
-- 	rank_st_biz.NET_MAX_30,
-- 	ROUND(((rank_st_biz.NET_MAX_14+rank_st_biz.NET_MIN_30) / 2),2) AS 最近30日净值中位数,
-- 	ROUND(((rank_st_biz.NET_MAX_30-rank_st_biz.NET_MIN_30) / rank_st_biz.NET_MIN_30) * 100,2) AS 振幅30,
-- 	ROUND(((rank_st_biz.NET_MAX_360-rank_st_biz.NET_MIN_360) / rank_st_biz.NET_MIN_360) * 100,2) AS 涨幅360,
-- 	ROUND(((rank_st_biz.NET_MAX_60-rank_st_biz.NET_MIN_60) / rank_st_biz.NET_MIN_60) * 100,2) AS 涨幅60,
-- 	ROUND(((rank_st_biz.NET_MAX_90-rank_st_biz.NET_MIN_90) / rank_st_biz.NET_MIN_90) * 100,2) AS 涨幅90,
-- 	ROUND(((rank_st_biz.NET_MAX_180-rank_st_biz.NET_MIN_180) / rank_st_biz.NET_MIN_180) * 100,2) AS 涨幅180,
-- 	rank_st_biz.f2 lastNet
-- ,rank_st_biz.*
-- FROM
-- 	`rank_st_biz` rank_st_biz
-- WHERE 1 = 1
-- 	AND rank_st_biz.type = 'etf' -- 行业
-- 	AND rank_st_biz.date = @curDay
-- -- 	AND rank_st_biz.f14 in(@in1)
-- -- 	AND rank_st_biz.f14 LIKE @bkLike /**板块  创业 500	有色	**/
-- -- 	AND (ROUND(rank_st_biz.f20/100000000,2) BETWEEN @zszyMin AND	@zszyMax 
-- -- 			OR rank_st_biz.f3>1.5 OR rank_st_biz.f3<-2
-- -- 			OR rank_st_biz.f14 IN (@zszyOr1,@zszyOr2,@zszyOr3,@zszyOr4,@zszyOr5,@zszyOr6,@zszyOr7,@zszyOr8,@zszyOr9,@zszyOr10)
-- -- 	) /**总市值亿	**/
-- -- 	AND rank_st_biz.f14 NOT IN(@notIn1,@notIn2,@notIn3,@notIn4,@notIn5,@notIn6,@notIn7,@notIn8,@notIn9,@notIn10)
-- -- 	AND ((rank_st_biz.f14 NOT LIKE @nameNoLike1 ) AND (rank_st_biz.f14 NOT LIKE @nameNoLike2) AND (rank_st_biz.f14 NOT LIKE @nameNoLike3) AND (rank_st_biz.f14 NOT LIKE @nameNoLike4))
-- -- 	AND	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_7)/(rank_st_biz.NET_MAX_7-rank_st_biz.NET_MIN_7)*100,2)<=50 /**现价区间**/
-- -- 	AND	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_30)/(rank_st_biz.NET_MAX_30-rank_st_biz.NET_MIN_30)*100,2) <=50
-- ORDER BY 
-- 	最低比  /**		最高比 DESC	振幅 f7	最高比	最低比	rank_st_biz.f3 **/
-- ;
-- 
-- /**	涨幅	
-- -- 	,ROUND(rank_st_biz.f20/100000000,2) 总市值亿
-- **/
-- 
-- /**	我的持仓	**/
-- SELECT
-- 	rank_st_biz.f14 AS 我的持仓,	rank_st_biz.f12,
-- 	ROUND(rank_st_biz.f20/100000000,2) 总市值亿,	ROUND(rank_st_biz.f62/10000,2) 净流入万,
-- 	rank_st_biz.f2 净值,
-- 	rank_st_biz.date,
-- 	rank_st_biz.f3 涨幅,
-- 	rank_st_biz.f7 振幅,
-- 	ROUND((rank_st_biz.NET_MIN_1-rank_st_biz.f18)/rank_st_biz.f18*100,2) 最低一日,
-- 	ROUND((rank_st_biz.NET_MAX_1-rank_st_biz.f18)/rank_st_biz.f18*100,2) 最高一日,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_7)/(rank_st_biz.NET_MAX_7-rank_st_biz.NET_MIN_7)*100,2) 现价区间7,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_14)/(rank_st_biz.NET_MAX_14-rank_st_biz.NET_MIN_14)*100,2) 现价区间14,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_30)/(rank_st_biz.NET_MAX_30-rank_st_biz.NET_MIN_30)*100,2) 现价区间30,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_360)/(rank_st_biz.NET_MAX_360-rank_st_biz.NET_MIN_360)*100,2) 现价区间360,
-- 	rank_st_biz.NET_MIN_7 低7,
-- 	rank_st_biz.NET_MAX_7 高7,
-- 	rank_st_biz.pt_time_min,
-- 	rank_st_biz.pt_time_max,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_7)/rank_st_biz.NET_MIN_7*100,2) 最低率7,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MAX_7)/rank_st_biz.NET_MAX_7*100,2) 最高率7,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_7) * 100,2) AS 回撤7,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_14) * 100,2) AS 回撤14,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_30) * 100,2) AS 回撤30,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_60) * 100,2) AS 回撤60,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_90) * 100,2) AS 回撤90,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_180) * 100,2) AS 回撤180,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_360) * 100,2) AS 回撤360,
-- 	ROUND(((rank_st_biz.NET_MIN_1-rank_st_biz.NET_MIN_7) / rank_st_biz.NET_MIN_7) * 100,2) AS 涨幅7,
-- 	ROUND(((rank_st_biz.NET_MAX_7-rank_st_biz.NET_MIN_7) / rank_st_biz.NET_MIN_7) * 100,2) AS 振幅7,
-- 	ROUND(((rank_st_biz.NET_MIN_1-rank_st_biz.NET_MIN_14) / rank_st_biz.NET_MIN_14) * 100,2) AS 涨幅14,
-- 	ROUND(((rank_st_biz.NET_MAX_14-rank_st_biz.NET_MIN_14) / rank_st_biz.NET_MIN_14) * 100,2) AS 振幅14,
-- 	rank_st_biz.NET_MIN_14,
-- 	rank_st_biz.NET_MAX_14,
-- 	ROUND(((rank_st_biz.NET_MIN_1-rank_st_biz.NET_MIN_30) / rank_st_biz.NET_MIN_30) * 100,2) AS 涨幅30,
-- 	ROUND(((rank_st_biz.NET_MAX_14-rank_st_biz.NET_MIN_30) / rank_st_biz.NET_MIN_30) * 100,2) AS 振幅30,
-- 	rank_st_biz.NET_MIN_30,
-- 	rank_st_biz.NET_MAX_30,
-- 	ROUND(((rank_st_biz.NET_MAX_14+rank_st_biz.NET_MIN_30) / 2),2) AS 最近30日净值中位数,
-- 	ROUND(((rank_st_biz.NET_MAX_30-rank_st_biz.NET_MIN_30) / rank_st_biz.NET_MIN_30) * 100,2) AS 振幅30,
-- 	ROUND(((rank_st_biz.NET_MAX_360-rank_st_biz.NET_MIN_360) / rank_st_biz.NET_MIN_360) * 100,2) AS 涨幅360,
-- 	ROUND(((rank_st_biz.NET_MAX_60-rank_st_biz.NET_MIN_60) / rank_st_biz.NET_MIN_60) * 100,2) AS 涨幅60,
-- 	ROUND(((rank_st_biz.NET_MAX_90-rank_st_biz.NET_MIN_90) / rank_st_biz.NET_MIN_90) * 100,2) AS 涨幅90,
-- 	ROUND(((rank_st_biz.NET_MAX_180-rank_st_biz.NET_MIN_180) / rank_st_biz.NET_MIN_180) * 100,2) AS 涨幅180,
-- 	rank_st_biz.f2 lastNet
-- ,rank_st_biz.*
-- FROM
-- 	`rank_st_biz` rank_st_biz
-- WHERE 1 = 1
-- 	AND rank_st_biz.date = @curDay
-- 	AND rank_st_biz.type = 'etf' -- 行业
-- 	AND rank_st_biz.f14 IN(@my1,@my2,@my3,@my4,@my5,@my6,@my7,@my8,@my9,@my10)
-- ORDER BY 
-- 	rank_st_biz.f3 DESC
-- -- 	rank_st_biz.f7 DESC /**	振幅**/
-- ;
-- 
-- /**周期净值比率-n天**/
-- SELECT
-- 	rank_st_biz.f14 AS 现价区间,
-- 	rank_st_biz.f12,
-- 	ROUND(rank_st_biz.f20/100000000,2) 总市值亿,
-- 	ROUND(rank_st_biz.f62/10000,2) 净流入万,
-- 	rank_st_biz.date,
-- 	rank_st_biz.f2,
-- 	rank_st_biz.f3 涨幅,
-- 	ROUND((rank_st_biz.NET_MIN_1-rank_st_biz.f18)/rank_st_biz.f18*100,2) 最低一日,
-- 	ROUND((rank_st_biz.NET_MAX_1-rank_st_biz.f18)/rank_st_biz.f18*100,2) 最高一日,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_7)/(rank_st_biz.NET_MAX_7-rank_st_biz.NET_MIN_7)*100,2) 现价区间7,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_14)/(rank_st_biz.NET_MAX_14-rank_st_biz.NET_MIN_14)*100,2) 现价区间14,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_30)/(rank_st_biz.NET_MAX_30-rank_st_biz.NET_MIN_30)*100,2) 现价区间30,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_360)/(rank_st_biz.NET_MAX_360-rank_st_biz.NET_MIN_360)*100,2) 现价区间360,
-- 	rank_st_biz.NET_MIN_7 低7,
-- 	rank_st_biz.NET_MAX_7 高7,
-- 	NET_MIN_CLOS_7 收低7,
-- 	NET_MAX_CLOS_7 收高7,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_7)/rank_st_biz.NET_MIN_7*100,2) 最低率7,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MAX_7)/rank_st_biz.NET_MAX_7*100,2) 最高率7,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_7) * 100,2) AS 回撤7,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_14) * 100,2) AS 回撤14,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_30) * 100,2) AS 回撤30,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_60) * 100,2) AS 回撤60,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_90) * 100,2) AS 回撤90,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_180) * 100,2) AS 回撤180,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_360) * 100,2) AS 回撤360,
-- 	ROUND(((rank_st_biz.NET_MIN_1-rank_st_biz.NET_MIN_7) / rank_st_biz.NET_MIN_7) * 100,2) AS 涨幅7,
-- 	ROUND(((rank_st_biz.NET_MAX_7-rank_st_biz.NET_MIN_7) / rank_st_biz.NET_MIN_7) * 100,2) AS 振幅7,
-- 	ROUND(((rank_st_biz.NET_MIN_1-rank_st_biz.NET_MIN_14) / rank_st_biz.NET_MIN_14) * 100,2) AS 涨幅14,
-- 	ROUND(((rank_st_biz.NET_MAX_14-rank_st_biz.NET_MIN_14) / rank_st_biz.NET_MIN_14) * 100,2) AS 振幅14,
-- 	rank_st_biz.NET_MIN_14,
-- 	rank_st_biz.NET_MAX_14,
-- 	ROUND(((rank_st_biz.NET_MIN_1-rank_st_biz.NET_MIN_30) / rank_st_biz.NET_MIN_30) * 100,2) AS 涨幅30,
-- 	ROUND(((rank_st_biz.NET_MAX_14-rank_st_biz.NET_MIN_30) / rank_st_biz.NET_MIN_30) * 100,2) AS 振幅30,
-- 	rank_st_biz.NET_MIN_30,
-- 	rank_st_biz.NET_MAX_30,
-- 	ROUND(((rank_st_biz.NET_MAX_14+rank_st_biz.NET_MIN_30) / 2),2) AS 最近30日净值中位数,
-- 	ROUND(((rank_st_biz.NET_MAX_30-rank_st_biz.NET_MIN_30) / rank_st_biz.NET_MIN_30) * 100,2) AS 振幅30,
-- 	ROUND(((rank_st_biz.NET_MAX_360-rank_st_biz.NET_MIN_360) / rank_st_biz.NET_MIN_360) * 100,2) AS 涨幅360,
-- 	ROUND(((rank_st_biz.NET_MAX_60-rank_st_biz.NET_MIN_60) / rank_st_biz.NET_MIN_60) * 100,2) AS 涨幅60,
-- 	ROUND(((rank_st_biz.NET_MAX_90-rank_st_biz.NET_MIN_90) / rank_st_biz.NET_MIN_90) * 100,2) AS 涨幅90,
-- 	ROUND(((rank_st_biz.NET_MAX_180-rank_st_biz.NET_MIN_180) / rank_st_biz.NET_MIN_180) * 100,2) AS 涨幅180,
-- 	rank_st_biz.f2 lastNet
-- ,rank_st_biz.*
-- FROM
-- 	`rank_st_biz` rank_st_biz
-- WHERE 1 = 1
-- 	AND rank_st_biz.date = @curDay
-- 	AND rank_st_biz.type = 'etf' -- 行业
-- 	AND rank_st_biz.f14 LIKE @bkLike /**板块  创业 500	有色	**/
-- 	AND ROUND(rank_st_biz.f20/100000000,2) BETWEEN @zszyMin AND	@zszyMax /**总市值亿	**/
-- 	AND rank_st_biz.f14 NOT IN('货币ETF建信添益')
-- -- 	AND ((rank_st_biz.f14 NOT LIKE @nameNoLike1 ) AND (rank_st_biz.f14 NOT LIKE @nameNoLike2) AND (rank_st_biz.f14 NOT LIKE @nameNoLike3) AND (rank_st_biz.f14 NOT LIKE @nameNoLike4))
-- -- 	AND rank_st_biz.f14 in('上证50ETF')
-- -- 	AND	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_7)/(rank_st_biz.NET_MAX_7-rank_st_biz.NET_MIN_7)*100,2)<=50 /**现价区间**/
-- -- 	AND	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_30)/(rank_st_biz.NET_MAX_30-rank_st_biz.NET_MIN_30)*100,2) <=50
-- ORDER BY 
-- 	现价区间30
-- ;
-- 
-- /**	特定etf	**/
-- SELECT
-- 	rank_st_biz.f14 AS 特定etf,	rank_st_biz.f12,rank_st_biz.date
-- 	,rank_st_biz.f7 振幅
-- 	,rank_st_biz.f2 净值,
-- 	rank_st_biz.date,
-- 	rank_st_biz.f3 涨幅,
-- 	ROUND((rank_st_biz.NET_MIN_1-rank_st_biz.f18)/rank_st_biz.f18*100,2) 最低一日,
-- 	ROUND((rank_st_biz.NET_MAX_1-rank_st_biz.f18)/rank_st_biz.f18*100,2) 最高一日,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_7)/(rank_st_biz.NET_MAX_7-rank_st_biz.NET_MIN_7)*100,2) 现价区间7,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_14)/(rank_st_biz.NET_MAX_14-rank_st_biz.NET_MIN_14)*100,2) 现价区间14,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_30)/(rank_st_biz.NET_MAX_30-rank_st_biz.NET_MIN_30)*100,2) 现价区间30,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_360)/(rank_st_biz.NET_MAX_360-rank_st_biz.NET_MIN_360)*100,2) 现价区间360,
-- 	rank_st_biz.NET_MIN_7 低7,
-- 	rank_st_biz.NET_MAX_7 高7,
-- 	NET_MIN_CLOS_7 收低7,
-- 	NET_MAX_CLOS_7 收高7,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MIN_7)/rank_st_biz.NET_MIN_7*100,2) 最低率7,
-- 	ROUND((rank_st_biz.f2-rank_st_biz.NET_MAX_7)/rank_st_biz.NET_MAX_7*100,2) 最高率7,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_7) * 100,2) AS 回撤7,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_14) * 100,2) AS 回撤14,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_30) * 100,2) AS 回撤30,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_60) * 100,2) AS 回撤60,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_90) * 100,2) AS 回撤90,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_180) * 100,2) AS 回撤180,
-- 	ROUND((rank_st_biz.f2 / rank_st_biz.NET_MAX_360) * 100,2) AS 回撤360,
-- 	ROUND(((rank_st_biz.NET_MIN_1-rank_st_biz.NET_MIN_7) / rank_st_biz.NET_MIN_7) * 100,2) AS 涨幅7,
-- 	ROUND(((rank_st_biz.NET_MAX_7-rank_st_biz.NET_MIN_7) / rank_st_biz.NET_MIN_7) * 100,2) AS 振幅7,
-- 	ROUND(((rank_st_biz.NET_MIN_1-rank_st_biz.NET_MIN_14) / rank_st_biz.NET_MIN_14) * 100,2) AS 涨幅14,
-- 	ROUND(((rank_st_biz.NET_MAX_14-rank_st_biz.NET_MIN_14) / rank_st_biz.NET_MIN_14) * 100,2) AS 振幅14,
-- 	rank_st_biz.NET_MIN_14,
-- 	rank_st_biz.NET_MAX_14,
-- 	ROUND(((rank_st_biz.NET_MIN_1-rank_st_biz.NET_MIN_30) / rank_st_biz.NET_MIN_30) * 100,2) AS 涨幅30,
-- 	ROUND(((rank_st_biz.NET_MAX_14-rank_st_biz.NET_MIN_30) / rank_st_biz.NET_MIN_30) * 100,2) AS 振幅30,
-- 	rank_st_biz.NET_MIN_30,
-- 	rank_st_biz.NET_MAX_30,
-- 	ROUND(((rank_st_biz.NET_MAX_14+rank_st_biz.NET_MIN_30) / 2),2) AS 最近30日净值中位数,
-- 	ROUND(((rank_st_biz.NET_MAX_30-rank_st_biz.NET_MIN_30) / rank_st_biz.NET_MIN_30) * 100,2) AS 振幅30,
-- 	ROUND(((rank_st_biz.NET_MAX_360-rank_st_biz.NET_MIN_360) / rank_st_biz.NET_MIN_360) * 100,2) AS 涨幅360,
-- 	ROUND(((rank_st_biz.NET_MAX_60-rank_st_biz.NET_MIN_60) / rank_st_biz.NET_MIN_60) * 100,2) AS 涨幅60,
-- 	ROUND(((rank_st_biz.NET_MAX_90-rank_st_biz.NET_MIN_90) / rank_st_biz.NET_MIN_90) * 100,2) AS 涨幅90,
-- 	ROUND(((rank_st_biz.NET_MAX_180-rank_st_biz.NET_MIN_180) / rank_st_biz.NET_MIN_180) * 100,2) AS 涨幅180,
-- 	rank_st_biz.f2 lastNet
-- ,rank_st_biz.*
-- FROM
-- 	`rank_st_biz` rank_st_biz
-- WHERE 1 = 1
-- -- 	AND rank_st_biz.date = @curDay
-- 	AND rank_st_biz.type = 'etf' -- 行业
-- -- 	AND rank_st_biz.f14 LIKE @bkLike /**板块  创业 500	有色	**/
-- -- 	AND ((rank_st_biz.f14 NOT LIKE @nameNoLike1 ) AND (rank_st_biz.f14 NOT LIKE @nameNoLike2) AND (rank_st_biz.f14 NOT LIKE @nameNoLike3) AND (rank_st_biz.f14 NOT LIKE @nameNoLike4))
-- 	AND rank_st_biz.f14 in(@in1)
-- ORDER BY 
-- -- 	rank_st_biz.date DESC,
-- 	rank_st_biz.f7 DESC
-- ;
-- SET @curDayWeek = '五';SET @curDayWeekSub1 = (SELECT date FROM rank_st_biz WHERE `week`=@curDayWeek GROUP BY date ORDER BY date DESC limit 1,1);SET @curDayWeekSub2 = (SELECT date FROM rank_st_biz WHERE `week`=@curDayWeek GROUP BY date ORDER BY date DESC limit 2,1);SET @curDayWeekSub3 = (SELECT date FROM rank_st_biz WHERE `week`=@curDayWeek GROUP BY date ORDER BY date DESC limit 3,1);
SET @bkLike = '%旅%';/**板块	有色	芯片	酒 医	军	创业	**/
SET @nameNoLike1 = '%债%';SET @nameNoLike2 = '%货币%';SET @nameNoLike3 = '%5G%';SET @nameNoLike4 = '%快线%';SET @nameNoLike5 = '%快钱%';/**基金名称 不查询	**/
