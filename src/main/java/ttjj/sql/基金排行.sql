-- /**基金排行-今年+近一周	**/
-- SELECT
-- 	ROUND(ol_fund_rank.growthCurYear+ol_fund_rank.growth7,2) '今年+近一周'
-- 	,ol_fund_rank.growthCurYear
-- 	,ol_fund_rank.growth7
-- 	,ol_fund_rank.*
-- FROM
-- 	`ol_fund_rank`
-- WHERE 
-- 	ol_fund_rank.periodTy = 'z'
-- ORDER BY ROUND(ol_fund_rank.growthCurYear+ol_fund_rank.growth7,2) DESC
-- LIMIT 0,
--  100;

/**基金排行-今年+近一周	**/
SELECT
	ROUND(ol_fund_rank.growthCurYear+ol_fund_rank.growth7,2) '今年+近一周'
	,ol_fund_rank.growthCurYear
	,ol_fund_rank.growth7
	,ol_fund_rank.*
FROM
	`ol_fund_rank`
WHERE 
	ol_fund_rank.periodTy = 'jn'
GROUP BY ol_fund_rank.fundCode,ol_fund_rank.periodDate
ORDER BY ROUND(ol_fund_rank.growthCurYear+ol_fund_rank.growth7,2) DESC
LIMIT 0,
 100;