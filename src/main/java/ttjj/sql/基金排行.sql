/**基金排行-今年	**/
SELECT
	ol_fund_rank.growthCurYear-ol_fund_rank.growth7 '今年+近一周'
	,ol_fund_rank.growthCurYear
	,ol_fund_rank.*
FROM
	`ol_fund_rank`
WHERE 
	ol_fund_rank.periodTy = 'jn'
LIMIT 0,
 100;