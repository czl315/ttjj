/*
Navicat MySQL Data Transfer

Source Server         : czl315
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : bank19

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-06-07 23:32:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ol_fund_fupan
-- ----------------------------
DROP TABLE IF EXISTS `ol_fund_fupan`;
CREATE TABLE `ol_fund_fupan` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(100) DEFAULT NULL COMMENT '代码',
  `amt` varchar(255) DEFAULT NULL,
  `NAME` varchar(200) DEFAULT NULL COMMENT '概述',
  `TYPE` varchar(10) DEFAULT NULL COMMENT '类型',
  `fupan_good` varchar(255) DEFAULT NULL,
  `fupan_kong` varchar(500) DEFAULT NULL COMMENT '复盘',
  `ex` varchar(255) DEFAULT NULL,
  `rate_sh` varchar(255) DEFAULT NULL COMMENT '上证增长率',
  `rate_sz` varchar(255) DEFAULT NULL COMMENT '深证增长率',
  `rate_cyb` varchar(255) DEFAULT NULL COMMENT '创业板增长率',
  `rate_hs300` varchar(255) DEFAULT NULL COMMENT '沪深300增长率',
  `rate_ty_gp` varchar(255) DEFAULT NULL COMMENT '类型-股票',
  `rate_ty_hh` varchar(255) DEFAULT NULL COMMENT '类型-混合',
  `rate_ty_zq` varchar(255) DEFAULT NULL COMMENT '类型-债券',
  `rate_ty_qdii` varchar(255) DEFAULT NULL COMMENT '类型-国际',
  `rate_zz500` varchar(255) DEFAULT NULL COMMENT '中证500增长率',
  `ny` varchar(255) DEFAULT NULL COMMENT '能源',
  `ycl` varchar(255) DEFAULT NULL COMMENT '原材料',
  `gy` varchar(255) DEFAULT NULL COMMENT '工业',
  `kxxf` varchar(255) DEFAULT NULL COMMENT '可选消费',
  `zyxf` varchar(255) DEFAULT NULL COMMENT '主要消费',
  `yyws` varchar(255) DEFAULT NULL COMMENT '医药卫生',
  `jrdc` varchar(255) DEFAULT NULL COMMENT '金融地产',
  `xxjs` varchar(255) DEFAULT NULL COMMENT '信息技术',
  `dxyw` varchar(255) DEFAULT NULL COMMENT '电信业务',
  `ggsy` varchar(255) DEFAULT NULL COMMENT '公共事业',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='基金复盘';

-- ----------------------------
-- Records of ol_fund_fupan
-- ----------------------------
INSERT INTO `ol_fund_fupan` VALUES ('1', '2020wk17', '-475.81', null, null, '债券：稳定增长；消费：上涨；', '科技下跌，一季报不好；原油大跌；国际：新冠疫情；', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2020-04-25 22:33:31', '2020-04-27 19:15:56');
INSERT INTO `ol_fund_fupan` VALUES ('2', '2020wk18-yuqi', null, null, null, '消费：上涨1.0%；科技:上涨1.0%', '科技：一季报不好；', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2020-04-25 22:49:08', '2020-04-27 19:17:10');
INSERT INTO `ol_fund_fupan` VALUES ('3', '2020wk18', '1716.72', '', '', '消费：上涨0.5%；科技:上涨5.5%，超跌反弹；两会确定', '科技：一季报不好(消费：顺鑫农业)；', '月底超跌反弹；科技超跌反弹', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2020-04-25 22:49:08', '2020-05-10 21:01:46');
INSERT INTO `ol_fund_fupan` VALUES ('4', '2020wk19-yuqi', '', '', '', '消费：上涨0.5%；科技：下跌2.0%；两会行情+10；国内：复产复工+5；', '科技：回调；国际：新冠疫情-3；外围：A50下跌，港股下跌；', '小幅上涨0.5%', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2020-04-25 22:49:08', '2020-05-10 21:02:13');
INSERT INTO `ol_fund_fupan` VALUES ('5', '2020wk19', '1346.72', null, null, '上涨1.23%(yes)；两会行情+8；国内：复产复工+5；科技复苏；', '', '两会行情', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2020-05-10 20:56:46', '2020-05-10 21:22:11');
INSERT INTO `ol_fund_fupan` VALUES ('6', '2020wk20-yq', null, null, null, '上涨0.8%；两会行情+8；国内：复产复工+5；横盘；军工上涨；券商上涨；', '信息技术-回调；美股；', null, null, null, null, null, null, null, null, null, null, '+0.5', '+0.1', '+0.5', '+0.3', '+0.2', '+0.2', '+0.3', '-0.5', '+0.2', '0', '2020-05-10 21:22:19', '2020-05-17 12:23:22');
INSERT INTO `ol_fund_fupan` VALUES ('7', '2020wk20-sj', '161.66', '', '', '上涨0.5%；两会行情+3；国内：复产复工+3；横盘；军工横盘；券商下跌；', '信息技术-上涨；美股-横盘；', '科技利好消息', null, null, null, null, null, null, null, null, null, '-5', '-2', '-2', '-0.5', '+1.5', '+4.3', '-1.8', '+4.0', '+0.7', '0', '2020-05-10 21:22:19', '2020-05-17 12:23:24');
INSERT INTO `ol_fund_fupan` VALUES ('8', '2020wk21-yq', '', '', '', '上涨1.0%；两会行情+9；国内：复产复工+3；上涨；军工上涨；券商上涨；', '信息技术-回调；美股；', '', null, null, null, null, null, null, null, null, null, '+1.0', '+0.5', '+0.5', '+0.8', '+0.2', '+0.5', '+0.3', '-0.5', '+0.2', '0', '2020-05-10 21:22:19', '2020-05-17 12:26:30');
INSERT INTO `ol_fund_fupan` VALUES ('9', '20200518', '-509.18', '', '', '', '信息技术：美国限制华为半导体', '', null, null, null, null, null, null, null, null, null, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-24 11:09:34');
INSERT INTO `ol_fund_fupan` VALUES ('10', '20200519', '704.27', '', '', '', '', '', null, null, null, null, null, null, null, null, null, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-24 11:10:57');
INSERT INTO `ol_fund_fupan` VALUES ('11', '20200520', '-380.69', '', '', '', '全部：港股大跌', '', null, null, null, null, null, null, null, null, null, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-25 23:13:14');
INSERT INTO `ol_fund_fupan` VALUES ('12', '20200521', '-642.63', '', '', '', '全部：财务造假', '', null, null, null, null, null, null, null, null, null, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-24 11:12:20');
INSERT INTO `ol_fund_fupan` VALUES ('13', '20200524', '-1005.79', '', '', '', '全部：两会无GDP预期', '', null, null, null, null, null, null, null, null, null, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-24 11:11:36');
INSERT INTO `ol_fund_fupan` VALUES ('14', '20200525', '', '', '', '消费：两会内需', '全部：中美贸易摩擦', '', null, null, null, null, null, null, null, null, null, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-25 23:14:02');
INSERT INTO `ol_fund_fupan` VALUES ('15', '2020wk23-yq', '', '', '', '上涨1.0%；两会刺激+3；国内：复产复工+1；军工上涨；券商上涨；', '信息技术：中美贸易摩擦', '', null, null, null, null, null, null, null, null, null, '+1.0', '+0.5', '+0.2', '+0.3', '+0.5', '+0.2', '+0.3', '-0.2', '0', '0', '2020-05-10 21:22:19', '2020-05-31 10:53:22');
INSERT INTO `ol_fund_fupan` VALUES ('16', '2020wk22', '', '', '', '', '信息技术：中美贸易摩擦', '', '1.37', '1.33', '1.96', '1.12', '1.46', '1.45', '-0.14', '1.26', null, '+1.0', '+0.5', '+0.2', '+0.3', '+0.5', '+0.2', '+0.3', '-0.2', '0', '0', '2020-05-10 21:22:19', '2020-06-07 23:20:55');
INSERT INTO `ol_fund_fupan` VALUES ('17', '2020wk24-yq', '', '', '0', '{\"行业\":\"消费、新能源、黄金\"}上涨0.5%；两会刺激+3；国内：复产复工+1；券商上涨；', '国际：美股下跌', '', '', '', '', '', null, null, null, null, '', '+0.5', '+0.5', '+0.2', '+0.5', '+0.3', '+0.2', '+0.5', '-0.5', '0', '0', '2020-06-07 22:56:28', '2020-06-07 23:16:59');
INSERT INTO `ol_fund_fupan` VALUES ('18', '2020wk23', '1985.01', '', '1', '上涨1.0%；两会刺激+3；国内：复产复工+1；{\"行业\":\"通讯、电子元件、国际原油\"}', '信息技术：中美贸易摩擦{\"行业\":\"贵金属\"}', '利空出尽反弹；股市上涨，黄金下跌', '2.75', '4.04', '3.82', '3.47', '3.1', '1.88', '-0.47', '2.7', '', '+1.0', '+0.5', '+0.2', '+0.3', '+0.5', '+0.2', '+0.3', '-0.2', '0', '0', '2020-06-07 23:13:22', '2020-06-07 23:16:04');
