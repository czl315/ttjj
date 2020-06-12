/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : bank19

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 12/06/2020 18:26:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ol_fund_fupan
-- ----------------------------
DROP TABLE IF EXISTS `ol_fund_fupan`;
CREATE TABLE `ol_fund_fupan`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代码',
  `amt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '概述',
  `TYPE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `fupan_good` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fupan_kong` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '复盘',
  `ex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `topic` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题排行',
  `rate_sh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上证增长率',
  `rate_sz` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '深证增长率',
  `rate_cyb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创业板增长率',
  `rate_hs300` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '沪深300增长率',
  `rate_ty_gp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型-股票',
  `rate_ty_hh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型-混合',
  `rate_ty_zq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型-债券',
  `rate_ty_qdii` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型-国际',
  `rate_zz500` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中证500增长率',
  `ny` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '能源',
  `ycl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原材料',
  `gy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工业',
  `kxxf` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '可选消费',
  `zyxf` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要消费',
  `yyws` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医药卫生',
  `jrdc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '金融地产',
  `xxjs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '信息技术',
  `dxyw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电信业务',
  `ggsy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公共事业',
  `CREATE_TIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `UPDATE_TIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '基金复盘' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ol_fund_fupan
-- ----------------------------
INSERT INTO `ol_fund_fupan` VALUES (1, '2020wk17', '-475.81', NULL, NULL, '债券：稳定增长；消费：上涨；', '科技下跌，一季报不好；原油大跌；国际：新冠疫情；', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-04-25 22:33:31', '2020-04-27 19:15:56');
INSERT INTO `ol_fund_fupan` VALUES (2, '2020wk18-yuqi', NULL, NULL, NULL, '消费：上涨1.0%；科技:上涨1.0%', '科技：一季报不好；', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-04-25 22:49:08', '2020-04-27 19:17:10');
INSERT INTO `ol_fund_fupan` VALUES (3, '2020wk18', '1716.72', '', '', '消费：上涨0.5%；科技:上涨5.5%，超跌反弹；两会确定', '科技：一季报不好(消费：顺鑫农业)；', '月底超跌反弹；科技超跌反弹', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-04-25 22:49:08', '2020-05-10 21:01:46');
INSERT INTO `ol_fund_fupan` VALUES (4, '2020wk19-yuqi', '', '', '', '消费：上涨0.5%；科技：下跌2.0%；两会行情+10；国内：复产复工+5；', '科技：回调；国际：新冠疫情-3；外围：A50下跌，港股下跌；', '小幅上涨0.5%', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-04-25 22:49:08', '2020-05-10 21:02:13');
INSERT INTO `ol_fund_fupan` VALUES (5, '2020wk19', '1346.72', NULL, NULL, '上涨1.23%(yes)；两会行情+8；国内：复产复工+5；科技复苏；', '', '两会行情', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-05-10 20:56:46', '2020-05-10 21:22:11');
INSERT INTO `ol_fund_fupan` VALUES (6, '2020wk20-yq', NULL, NULL, NULL, '上涨0.8%；两会行情+8；国内：复产复工+5；横盘；军工上涨；券商上涨；', '信息技术-回调；美股；', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '+0.5', '+0.1', '+0.5', '+0.3', '+0.2', '+0.2', '+0.3', '-0.5', '+0.2', '0', '2020-05-10 21:22:19', '2020-05-17 12:23:22');
INSERT INTO `ol_fund_fupan` VALUES (7, '2020wk20-sj', '161.66', '', '', '上涨0.5%；两会行情+3；国内：复产复工+3；横盘；军工横盘；券商下跌；', '信息技术-上涨；美股-横盘；', '科技利好消息', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '-5', '-2', '-2', '-0.5', '+1.5', '+4.3', '-1.8', '+4.0', '+0.7', '0', '2020-05-10 21:22:19', '2020-05-17 12:23:24');
INSERT INTO `ol_fund_fupan` VALUES (8, '2020wk21-yq', '', '', '', '上涨1.0%；两会行情+9；国内：复产复工+3；上涨；军工上涨；券商上涨；', '信息技术-回调；美股；', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '+1.0', '+0.5', '+0.5', '+0.8', '+0.2', '+0.5', '+0.3', '-0.5', '+0.2', '0', '2020-05-10 21:22:19', '2020-05-17 12:26:30');
INSERT INTO `ol_fund_fupan` VALUES (9, '20200518', '-509.18', '', '', '', '信息技术：美国限制华为半导体', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-24 11:09:34');
INSERT INTO `ol_fund_fupan` VALUES (10, '20200519', '704.27', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-24 11:10:57');
INSERT INTO `ol_fund_fupan` VALUES (11, '20200520', '-380.69', '', '', '', '全部：港股大跌', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-25 23:13:14');
INSERT INTO `ol_fund_fupan` VALUES (12, '20200521', '-642.63', '', '', '', '全部：财务造假', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-24 11:12:20');
INSERT INTO `ol_fund_fupan` VALUES (13, '20200524', '-1005.79', '', '', '', '全部：两会无GDP预期', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-24 11:11:36');
INSERT INTO `ol_fund_fupan` VALUES (14, '20200525', '', '', '', '消费：两会内需', '全部：中美贸易摩擦', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-25 23:14:02');
INSERT INTO `ol_fund_fupan` VALUES (15, '2020wk23-yq', '', '', '', '上涨1.0%；两会刺激+3；国内：复产复工+1；军工上涨；券商上涨；', '信息技术：中美贸易摩擦', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '+1.0', '+0.5', '+0.2', '+0.3', '+0.5', '+0.2', '+0.3', '-0.2', '0', '0', '2020-05-10 21:22:19', '2020-05-31 10:53:22');
INSERT INTO `ol_fund_fupan` VALUES (16, '2020wk22', '', '', '', '', '信息技术：中美贸易摩擦', '', NULL, '1.37', '1.33', '1.96', '1.12', '1.46', '1.45', '-0.14', '1.26', NULL, '+1.0', '+0.5', '+0.2', '+0.3', '+0.5', '+0.2', '+0.3', '-0.2', '0', '0', '2020-05-10 21:22:19', '2020-06-07 23:20:55');
INSERT INTO `ol_fund_fupan` VALUES (17, '2020wk24-yq', '', '', '0', '{\"行业\":\"消费、新能源、黄金\"}上涨0.5%；两会刺激+3；国内：复产复工+1；券商上涨；', '国际：美股下跌', '', NULL, '', '', '', '', NULL, NULL, NULL, NULL, '', '+0.5', '+0.5', '+0.2', '+0.5', '+0.3', '+0.2', '+0.5', '-0.5', '0', '0', '2020-06-07 22:56:28', '2020-06-07 23:16:59');
INSERT INTO `ol_fund_fupan` VALUES (18, '2020wk23', '1985.01', '', '1', '上涨1.0%；两会刺激+3；国内：复产复工+1；{\"行业\":\"通讯、电子元件、国际原油\"}', '信息技术：中美贸易摩擦{\"行业\":\"贵金属\"}', '利空出尽反弹；股市上涨，黄金下跌', NULL, '2.75', '4.04', '3.82', '3.47', '3.1', '1.88', '-0.47', '2.7', '', '+1.0', '+0.5', '+0.2', '+0.3', '+0.5', '+0.2', '+0.3', '-0.2', '0', '0', '2020-06-07 23:13:22', '2020-06-07 23:16:04');
INSERT INTO `ol_fund_fupan` VALUES (19, '20200608', '', '', '1', '{\"行业\":[{\"能源\":\"石油大涨5%\"}]}', '', '石油', NULL, '', '', '', '', '', '', '', '', '', '石油+5%', '', '', '', '', '', '', '', '', '', '2020-06-09 13:40:02', '2020-06-11 14:21:44');
INSERT INTO `ol_fund_fupan` VALUES (20, '20200609', '', '', '1', '[{\"行业\":[{\"金融\":\"T+0券商\"}]},{\"国际\":[{\"美股\":\"科技新高\"}]}]', '', '券商', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-06-11 14:13:21', '2020-06-11 14:21:48');
INSERT INTO `ol_fund_fupan` VALUES (21, '20200610', '', '', '1', '', '石油超涨下跌', '医疗', '[{\"rate\":2.1186,\"name\":\"水泥建材\"},{\"rate\":1.9833,\"name\":\"社保重仓\"},{\"rate\":1.9587,\"name\":\"医药行业\"},{\"rate\":1.8589,\"name\":\"生物疫苗\"},{\"rate\":1.8042,\"name\":\"医疗行业\"},{\"rate\":1.7381,\"name\":\"单抗概念\"},{\"rate\":1.6572,\"name\":\"病毒防治\"},{\"rate\":1.6442,\"name\":\"医疗器械\"},{\"rate\":1.5906,\"name\":\"医药制造\"},{\"rate\":1.5774,\"name\":\"二胎概念\"}]', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-06-11 14:13:21', '2020-06-12 09:09:11');
INSERT INTO `ol_fund_fupan` VALUES (22, '20200611', '', '', '1', '[{\"行业\":[{\"信息通讯\":\"苹果\"}]},{\"国际\":[{\"美股\":\"降息保持0%\"}]}]', '', '', '[{\"rate\":1.4882,\"name\":\"阿里概念\"},{\"rate\":1.4665,\"name\":\"优选QDII\"},{\"rate\":1.3435,\"name\":\"车联网\"},{\"rate\":1.1333,\"name\":\"生物识别\"},{\"rate\":1.0621,\"name\":\"手游概念\"},{\"rate\":0.9824,\"name\":\"小金属\"},{\"rate\":0.8885,\"name\":\"移动支付\"},{\"rate\":0.7671,\"name\":\"贬值受益\"},{\"rate\":0.7305,\"name\":\"通讯行业\"},{\"rate\":0.7137,\"name\":\"国产软件\"}]', '-0.02', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-06-11 14:13:21', '2020-06-12 13:42:31');
INSERT INTO `ol_fund_fupan` VALUES (23, '20200612', '', '', '1', '[{\"行业\":[{\"能源\":\"黄金\"}]},{\"国际\":[]}]', '[{\"行业\":[{\"能源\":\"石油大幅下跌\"}]},{\"国际\":[{\"美股\":\"暴跌\"}]}]', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-06-11 14:13:21', '2020-06-12 09:06:58');

SET FOREIGN_KEY_CHECKS = 1;
