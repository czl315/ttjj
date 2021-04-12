set @curDay= '20210412';

SELECT
/**业务板块排行榜-每日-行业	**/
	rank_st_biz.date,
	rank_st_biz.order_num,
	rank_st_biz.f3,
	rank_st_biz.f14,
	rank_st_biz.f2,
	rank_st_biz.f12,
	rank_st_biz.f128,
	rank_st_biz.f140,
	rank_st_biz.f136,
  rank_st_biz.ex,
	ROUND(rank_st_biz.f20/100000000,2) 总市值亿,
	ROUND(rank_st_biz.f62/100000000,2) 净流入亿,
	rank_st_biz.*
FROM
	`rank_st_biz`
WHERE 1=1
	AND rank_st_biz.date = @curDay
	AND rank_st_biz.type = 'hy' -- 行业
	AND ROUND(rank_st_biz.f20/100000000,2)>=2500 /**总市值大于1000亿**/
-- 	AND rank_st_biz.f14 in('公用事业')
-- 	AND rank_st_biz.f14 in('酿酒行业')
-- 	AND rank_st_biz.f14 in('园林工程')
-- 	AND rank_st_biz.f14 in('环保工程')
-- 	AND rank_st_biz.f14 in('券商信托')
-- 	AND rank_st_biz.f14 in('软件服务')
-- 	AND rank_st_biz.f14 in('电力行业')
-- 	AND rank_st_biz.f14 in('电子信息')
-- 	AND rank_st_biz.f14 in('水泥建材')
-- 	AND rank_st_biz.f14 in('食品饮料')
-- 	AND rank_st_biz.f14 in('保险')
-- 	AND rank_st_biz.f14 in('文化传媒')
ORDER BY
	rank_st_biz.order_num
-- 	rank_st_biz.date
LIMIT 0,
 1000;

/**业务板块排行榜-每日-概念	**/
SELECT
	rank_st_biz.date,
	rank_st_biz.order_num,
	rank_st_biz.f3,
	rank_st_biz.f14,
	rank_st_biz.f2,
	rank_st_biz.f128,
	rank_st_biz.f140,
	rank_st_biz.f136,
  rank_st_biz.ex,
	ROUND(rank_st_biz.f20/100000000,2) 总市值亿,
	ROUND(rank_st_biz.f62/100000000,2) 净流入亿,
	rank_st_biz.*
FROM
	`rank_st_biz`
WHERE 1=1
	AND rank_st_biz.date = @curDay
	AND rank_st_biz.type = 'gn' -- 概念
-- 	AND ROUND(rank_st_biz.f20/100000000,2)>3000 /**总市值大于1000亿**/
ORDER BY rank_st_biz.order_num
LIMIT 0,
 1000;

-- DELETE FROM rank_st_biz WHERE  rank_st_biz.date = '20210401';

/**业务板块排行榜-每日	**/
SELECT
	rank_st_biz.date,
	rank_st_biz.order_num,
	rank_st_biz.f3,
	rank_st_biz.f14,
	rank_st_biz.f2,
	rank_st_biz.f128,
	rank_st_biz.f140,
	rank_st_biz.f136,
	rank_st_biz.*
FROM
	`rank_st_biz`
WHERE 1=1
-- 	AND rank_st_biz.date = '20210324'
	AND rank_st_biz.f14 = '酿酒行业'
ORDER BY
	rank_st_biz.order_num
LIMIT 0,200;