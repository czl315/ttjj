/****/
SELECT
	ol_fund_earn.FD_NAME,
ROUND(ROUND((ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET-ol_fund_earn.BUY_COST)/ol_fund_earn.BUY_COST,4)*100/DATEDIFF(ol_fund_earn.LASTEST_NET_DATA,ol_fund_earn.FIRST_NET_DATA),4)AS dayRate,
-- ROUND(ROUND((ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET-ol_fund_earn.BUY_COST)/ol_fund_earn.BUY_COST,4)*100/DATEDIFF(ol_fund_earn.LASTEST_NET_DATA,ol_fund_earn.FIRST_NET_DATA),4)*30 AS '月率',
ROUND(ROUND((ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET-ol_fund_earn.BUY_COST)/ol_fund_earn.BUY_COST,4)*100/DATEDIFF(ol_fund_earn.LASTEST_NET_DATA,ol_fund_earn.FIRST_NET_DATA),4)*365 AS '年率',
ROUND((ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET-ol_fund_earn.BUY_COST)/ol_fund_earn.BUY_COST,4)*100 AS '持有收益率',
ol_fund_earn.LASTEST_NET_DATA AS 日期,
ol_fund_earn.LASTEST_NET AS 净值,
ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2) AS '持有金额',
ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET-ol_fund_earn.BUY_COST,2) AS '收益金额',
DATEDIFF(ol_fund_earn.LASTEST_NET_DATA,ol_fund_earn.FIRST_NET_DATA) AS '持有天数',ol_fund_earn.remark,
ol_fund_earn.TODAY_EARN_AMT 今日收益,
ROUND(ol_fund_earn.TODAY_EARN_AMT/ol_fund_earn.BUY_COST*100,4) 今日收益率,
ol_fund_earn.HOLD_EARN_RATE,ol_fund_earn.HOLD_EARN_AMT,ol_fund_earn.HOLD_AMT,
	ol_fund_earn.*
FROM
	`ol_fund_earn`
WHERE 1=1
AND ol_fund_earn.SOURCE = 2
-- AND ol_fund_earn.LASTEST_NET_DATA ='2020-02-28'
-- AND ol_fund_earn.FD_NAME = '广发全指工业ETF联接'
-- AND ol_fund_earn.FD_NAME in ('民生加银鑫享债券C' ,'招商招旭纯债C(003860)' )
-- AND ol_fund_earn.FD_NAME in ('招商招旭纯债C(003860)' )
-- AND ol_fund_earn.LASTEST_NET_DATA  BETWEEN '2020-03-01' AND '2020-03-31'
-- AND ol_fund_earn.BUY_COST<10000
 	AND ol_fund_earn.FD_NAME LIKE '%003547%'
--  	AND ol_fund_earn.FD_NAME = '交银中证海外中国互联网指数(164906)'
-- 	华润元大双鑫债券A(003680)  工银国债(7-10年)指数C(004086) 国富全球科技互联混合人民币(006373) 嘉实稳宏债券A(003458) 博时转债增强债券A(050019)
/**
--  	AND ol_fund_earn.FD_NAME in ('民生加银转债优选A(000067)' )
--  	AND ol_fund_earn.FD_NAME = '招商招旭纯债C(003860)'
	鹏华港美互联股票（LOF）
	前海开源裕源(FOF)(005809)
	易方达中概ETF联接人民币A
--  	AND ol_fund_earn.FD_NAME in ('前海开源中航军工' )
	AND ol_fund_earn.FD_NAME = '广发全指工业ETF联接A(004593)'

AND ol_fund_earn.FD_NAME = '' **/
-- ORDER BY dayRate DESC
-- ORDER BY 	ol_fund_earn.LASTEST_NET_DATA DESC
-- ORDER BY 	今日收益率 DESC
ORDER BY 	ol_fund_earn.LASTEST_NET_DATA DESC ,dayRate DESC
-- ORDER BY 	今日收益率 DESC
LIMIT 0,100;




/**	昨日收益
SELECT
	t1.LASTEST_NET_DATA,
t1.LASTEST_NET,
	t1.TODAY_EARN_AMT,
(SELECT
ol_fund_earn.LASTEST_NET
FROM
	`ol_fund_earn`
WHERE 1=1
	AND ol_fund_earn.FD_NAME = t1.FD_NAME
	AND ol_fund_earn.LASTEST_NET_DATA < t1.LASTEST_NET_DATA
ORDER BY ol_fund_earn.LASTEST_NET_DATA DESC
LIMIT 1) AS yestodoyAmt,
ROUND(t1.LASTEST_NET - (SELECT ol_fund_earn.LASTEST_NET FROM `ol_fund_earn`
WHERE 1=1 AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.LASTEST_NET_DATA < t1.LASTEST_NET_DATA ORDER BY ol_fund_earn.LASTEST_NET_DATA DESC
LIMIT 1),4),
ROUND(ROUND(t1.LASTEST_NET - (SELECT ol_fund_earn.LASTEST_NET FROM `ol_fund_earn`
WHERE 1=1 AND ol_fund_earn.FD_NAME = t1.FD_NAME AND ol_fund_earn.LASTEST_NET_DATA < t1.LASTEST_NET_DATA ORDER BY ol_fund_earn.LASTEST_NET_DATA DESC
LIMIT 1),4)*t1.CAN_SHARE,2) AS '今日收益金额',
t1.TODAY_EARN_AMT
FROM
	`ol_fund_earn` t1
WHERE 1=1
	AND t1.FD_NAME = '前海开源中航军工'
ORDER BY t1.LASTEST_NET_DATA DESC
LIMIT 0,1000;
**/


/**更新-持有收益金额
**/
/**更新-持有收益率
**/
/**更新-持有天数
**/
/**更新-收益率日月年
UPDATE ol_fund_earn SET ol_fund_earn.HOLD_AMT=ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET,2);
UPDATE ol_fund_earn SET ol_fund_earn.HOLD_EARN_AMT=ROUND(ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET-ol_fund_earn.BUY_COST,2);
UPDATE ol_fund_earn SET ol_fund_earn.HOLD_EARN_RATE=ROUND(ROUND((ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET-ol_fund_earn.BUY_COST)/ol_fund_earn.BUY_COST,4)*100,2) WHERE ol_fund_earn.HOLD_EARN_RATE is NULL OR ol_fund_earn.HOLD_EARN_RATE =0;
UPDATE ol_fund_earn SET ol_fund_earn.HOLD_DAYS=DATEDIFF(ol_fund_earn.LASTEST_NET_DATA,ol_fund_earn.FIRST_NET_DATA)
	WHERE ol_fund_earn.HOLD_DAYS is NULL or ol_fund_earn.HOLD_DAYS =0;
UPDATE ol_fund_earn
SET ol_fund_earn.HOLD_EARN_RATE_DAY=
		ROUND(ROUND((ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET-ol_fund_earn.BUY_COST)/ol_fund_earn.BUY_COST,4)*100
			/DATEDIFF(ol_fund_earn.LASTEST_NET_DATA,ol_fund_earn.FIRST_NET_DATA),2)
WHERE 1=1
;
UPDATE ol_fund_earn
SET ol_fund_earn.HOLD_EARN_RATE_MONTH=ROUND(ROUND((ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET-ol_fund_earn.BUY_COST)/ol_fund_earn.BUY_COST,4)*100/DATEDIFF(ol_fund_earn.LASTEST_NET_DATA,ol_fund_earn.FIRST_NET_DATA)*30,2)
;
UPDATE ol_fund_earn
SET ol_fund_earn.HOLD_EARN_RATE_YEAR=ROUND(ROUND((ol_fund_earn.CAN_SHARE*ol_fund_earn.LASTEST_NET-ol_fund_earn.BUY_COST)/ol_fund_earn.BUY_COST,4)*100/DATEDIFF(ol_fund_earn.LASTEST_NET_DATA,ol_fund_earn.FIRST_NET_DATA)*360,2)
;
**/

/**
AND ol_fund_earn.FD_CODE = '003383'
DATEDIFF(CURDATE(),'2019-09-09') AS '持有天数',

前海开源中航军工
SELECT
	ol_fund_earn.FD_NAME AS '基金名称',
	ROUND(SUM(ol_fund_earn.TODAY_EARN_AMT),2) AS '累计收益'
FROM
	`ol_fund_earn`
WHERE 1=1
	AND ol_fund_earn.FD_ID=13
LIMIT 0,1000;
**/

/**
民生加银鑫享债券C
INSERT INTO `bank19`.`ol_fund_earn` ( `TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`, `LASTEST_NET`, `LASTEST_NET_DATA`, `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362', '1.1362', '2019-07-23', '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1365', '2019-07-24','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1367', '2019-07-25','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1368', '2019-07-26','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1371', '2019-07-29','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1371', '2019-07-30','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1374', '2019-07-31','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1377', '2019-08-01','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1381', '2019-08-02','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1386', '2019-08-05','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());

INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1389', '2019-08-06','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1392', '2019-08-07','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1393', '2019-08-08','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1394', '2019-08-09','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());

INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1397', '2019-08-12','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1399', '2019-08-13','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1401', '2019-08-14','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1403', '2019-08-15','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1403', '2019-08-16','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());

INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1405', '2019-08-19','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1406', '2019-08-20','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1407', '2019-08-21','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1409', '2019-08-22','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1400', '2019-08-23','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());

INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1404', '2019-08-26','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1403', '2019-08-27','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1404', '2019-08-28','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1404', '2019-08-29','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1405', '2019-08-30','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());

INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1409', '2019-09-02','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1409', '2019-09-03','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1409', '2019-09-04','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1411', '2019-09-05','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1414', '2019-09-06','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());

INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1417', '2019-09-09','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1417', '2019-09-10','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1418', '2019-09-11','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1420', '2019-09-12','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());



INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1420', '2019-09-16','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1421', '2019-09-17','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1419', '2019-09-18','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `LASTEST_NET`, `LASTEST_NET_DATA`,`TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `HOLD_AMT`, `TODAY_EARN_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `WAIT_CONFIRM_AMT`, `CAN_SHARE`, `POSITION_COST_AMT`,  `BUY_COST`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ( '1.1420', '2019-09-19','11', '12', '民生加银鑫享债券C', '003383', NULL, NULL, NULL, NULL, '0', '880.13', '1.1362',  '1000', '0', NOW(), NOW());
**/

/**	插入-前海开源中航军工
INSERT INTO `bank19`.`ol_fund_earn` ( `TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `CAN_SHARE`, `BUY_COST`, `LASTEST_NET_DATA`, `LASTEST_NET`, `HOLD_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `TODAY_EARN_AMT`, `HOLD_DAYS`, `HOLD_EARN_RATE_DAY`, `HOLD_EARN_RATE_MONTH`, `HOLD_EARN_RATE_YEAR`, `WAIT_CONFIRM_AMT`, `POSITION_COST_AMT`, `FIRST_NET_DATA`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ('12', '13', '前海开源中航军工', '164402	', '880', '1000', '2020-02-10', '0.9770', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, '2019-09-09', '0', NOW(), NOW());

INSERT INTO `bank19`.`ol_fund_earn` ( `TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `CAN_SHARE`, `BUY_COST`, `LASTEST_NET_DATA`, `LASTEST_NET`, `HOLD_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `TODAY_EARN_AMT`, `HOLD_DAYS`, `HOLD_EARN_RATE_DAY`, `HOLD_EARN_RATE_MONTH`, `HOLD_EARN_RATE_YEAR`, `WAIT_CONFIRM_AMT`, `POSITION_COST_AMT`, `FIRST_NET_DATA`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ('12', '13', '前海开源中航军工', '164402	', '880', '1000', '2019-09-16', '1.111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, '2019-09-09', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `CAN_SHARE`, `BUY_COST`, `LASTEST_NET_DATA`, `LASTEST_NET`, `HOLD_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `TODAY_EARN_AMT`, `HOLD_DAYS`, `HOLD_EARN_RATE_DAY`, `HOLD_EARN_RATE_MONTH`, `HOLD_EARN_RATE_YEAR`, `WAIT_CONFIRM_AMT`, `POSITION_COST_AMT`, `FIRST_NET_DATA`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ('12', '13', '前海开源中航军工', '164402	', '880', '1000', '2019-09-17', '1.076', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, '2019-09-09', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `CAN_SHARE`, `BUY_COST`, `LASTEST_NET_DATA`, `LASTEST_NET`, `HOLD_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `TODAY_EARN_AMT`, `HOLD_DAYS`, `HOLD_EARN_RATE_DAY`, `HOLD_EARN_RATE_MONTH`, `HOLD_EARN_RATE_YEAR`, `WAIT_CONFIRM_AMT`, `POSITION_COST_AMT`, `FIRST_NET_DATA`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ('12', '13', '前海开源中航军工', '164402	', '880', '1000', '2019-09-18', '1.068', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, '2019-09-09', '0', NOW(), NOW());
INSERT INTO `bank19`.`ol_fund_earn` ( `TRADE_ID`, `FD_ID`, `FD_NAME`, `FD_CODE`, `CAN_SHARE`, `BUY_COST`, `LASTEST_NET_DATA`, `LASTEST_NET`, `HOLD_AMT`, `HOLD_EARN_AMT`, `HOLD_EARN_RATE`, `TODAY_EARN_AMT`, `HOLD_DAYS`, `HOLD_EARN_RATE_DAY`, `HOLD_EARN_RATE_MONTH`, `HOLD_EARN_RATE_YEAR`, `WAIT_CONFIRM_AMT`, `POSITION_COST_AMT`, `FIRST_NET_DATA`, `SERVER_CHARGE_AMT`, `CREATE_TIME`, `UPDATE_TIME`)
	VALUES ('12', '13', '前海开源中航军工', '164402	', '880', '1000', '2019-09-19', '1.084', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, '2019-09-09', '0', NOW(), NOW());
**/

/** 更新-基金交易-基金信息名称
UPDATE `bank19`.ol_fund_trade SET ol_fund_trade.FD_INFO =(SELECT ol_fund.`NAME` FROM ol_fund WHERE ol_fund.ID=ol_fund_trade.FD_ID);

UPDATE `bank19`.ol_fund_earn SET ol_fund_earn.FD_TYPE =(SELECT ol_fund.TYPE FROM ol_fund WHERE ol_fund.ID=ol_fund_earn.FD_ID);
**/
