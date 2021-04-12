set @value = '2021-04-12';

/**基金排行-近一天	**/
SELECT
	rank_fund.growth1
	,rank_fund.growth7
	,rank_fund.growth30
	,rank_fund.growthCurYear
	,rank_fund.*
FROM
	`rank_fund`
WHERE 1=1
-- 	AND rank_fund.periodTy = 'z'
	AND rank_fund.periodDate = @value
-- 	AND rank_fund.fundInfo LIKE '%兴全%'
GROUP BY rank_fund.fundCode,rank_fund.periodDate
ORDER BY ROUND(rank_fund.growth1,2) DESC
LIMIT 0,1000;

/**基金排行-近一周	**/
SELECT
	rank_fund.growth7
	,rank_fund.growth1
	,rank_fund.growth30
	,rank_fund.growthCurYear
	,rank_fund.*
FROM
	`rank_fund`
WHERE 1=1
-- 	AND rank_fund.periodTy = 'z'
	AND rank_fund.periodDate = @value
-- 	AND rank_fund.fundInfo LIKE '%兴全%'
GROUP BY rank_fund.fundCode,rank_fund.periodDate
ORDER BY ROUND(rank_fund.growth7,2) DESC
LIMIT 0,1000;

/**基金排行-近一月	**/
SELECT
	rank_fund.growth30
	,rank_fund.growth7
	,rank_fund.growth1
	,rank_fund.growthCurYear
	,rank_fund.*
FROM
	`rank_fund`
WHERE 1=1
-- 	AND rank_fund.periodTy = 'z'
	AND rank_fund.periodDate = @value
-- 	AND rank_fund.fundInfo LIKE '%诺安%'
GROUP BY rank_fund.fundCode,rank_fund.periodDate
ORDER BY rank_fund.growth30 DESC
LIMIT 0,1000;

/**基金排行-近一月	**/
SELECT
	ROUND(rank_fund.growthCurYear,2) 今年
	,rank_fund.growth30
	,rank_fund.growth7
	,rank_fund.growth1
	,rank_fund.*
FROM
	`rank_fund`
WHERE 1=1
-- 	AND rank_fund.periodTy = 'z'
	AND rank_fund.periodDate = @value
-- 	AND rank_fund.fundInfo LIKE '%诺安%'
GROUP BY rank_fund.fundCode,rank_fund.periodDate
ORDER BY 今年 DESC
LIMIT 0,1000;

/**基金排行-近一周	**/
SELECT
	ROUND(rank_fund.growth7+rank_fund.growth1,2) '近一周+近一天'
	,rank_fund.growth1
	,rank_fund.growth7
	,rank_fund.growth30
	,rank_fund.growthCurYear
	,rank_fund.*
FROM
	`rank_fund`
WHERE 1=1
-- 	AND rank_fund.periodTy = 'z'
	AND rank_fund.periodDate = @value
GROUP BY rank_fund.fundCode,rank_fund.periodDate
ORDER BY ROUND(rank_fund.growth7+rank_fund.growth1,2) DESC
LIMIT 0,1000;

/**基金排行-近一月+近一天	**/
SELECT
	ROUND(rank_fund.growth30+rank_fund.growth1,2) '近一月+近一天'
	,rank_fund.growth1
	,rank_fund.growthCurYear
	,rank_fund.growth7
	,rank_fund.*
FROM
	`rank_fund`
WHERE 1=1
-- 	AND rank_fund.periodTy = 'jn'
	AND rank_fund.periodDate = @value
GROUP BY rank_fund.fundCode,rank_fund.periodDate
ORDER BY ROUND(rank_fund.growth30+rank_fund.growth1,2) DESC
LIMIT 0,100;

/**基金排行-今年+近一周	**/
SELECT
	ROUND(rank_fund.growthCurYear+rank_fund.growth7,2) '今年+近一周'
	,rank_fund.growth1
	,rank_fund.growthCurYear
	,rank_fund.growth7
	,rank_fund.*
FROM
	`rank_fund`
WHERE 1=1
-- 	AND rank_fund.periodTy = 'jn'
	AND rank_fund.periodDate = @value
GROUP BY rank_fund.fundCode,rank_fund.periodDate
ORDER BY ROUND(rank_fund.growthCurYear+rank_fund.growth7,2) DESC
LIMIT 0,100;