/*
Navicat MySQL Data Transfer

Source Server         : czl315
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : bank19

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-09-29 00:11:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ol_fund_fupan
-- ----------------------------
DROP TABLE IF EXISTS `ol_fund_fupan`;
CREATE TABLE `ol_fund_fupan` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `period` varchar(255) DEFAULT NULL COMMENT '周期',
  `CODE` varchar(100) DEFAULT NULL COMMENT '代码',
  `amt` varchar(255) DEFAULT NULL,
  `NAME` varchar(200) DEFAULT NULL COMMENT '概述',
  `TYPE` varchar(10) DEFAULT NULL COMMENT '类型',
  `fupan_good` varchar(255) DEFAULT NULL,
  `fupan_kong` varchar(500) DEFAULT NULL COMMENT '复盘',
  `ex` varchar(255) DEFAULT NULL,
  `topic` varchar(1000) DEFAULT NULL COMMENT '主题排行',
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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='基金复盘';

-- ----------------------------
-- Records of ol_fund_fupan
-- ----------------------------
INSERT INTO `ol_fund_fupan` VALUES ('1', null, '2020wk17', '-475.81', null, null, '债券：稳定增长；消费：上涨；', '科技下跌，一季报不好；原油大跌；国际：新冠疫情；', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2020-04-25 22:33:31', '2020-04-27 19:15:56');
INSERT INTO `ol_fund_fupan` VALUES ('2', null, '2020wk18-yuqi', null, null, null, '消费：上涨1.0%；科技:上涨1.0%', '科技：一季报不好；', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2020-04-25 22:49:08', '2020-04-27 19:17:10');
INSERT INTO `ol_fund_fupan` VALUES ('3', null, '2020wk18', '1716.72', '', '', '消费：上涨0.5%；科技:上涨5.5%，超跌反弹；两会确定', '科技：一季报不好(消费：顺鑫农业)；', '月底超跌反弹；科技超跌反弹', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2020-04-25 22:49:08', '2020-05-10 21:01:46');
INSERT INTO `ol_fund_fupan` VALUES ('4', null, '2020wk19-yuqi', '', '', '', '消费：上涨0.5%；科技：下跌2.0%；两会行情+10；国内：复产复工+5；', '科技：回调；国际：新冠疫情-3；外围：A50下跌，港股下跌；', '小幅上涨0.5%', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2020-04-25 22:49:08', '2020-05-10 21:02:13');
INSERT INTO `ol_fund_fupan` VALUES ('5', null, '2020wk19', '1346.72', null, null, '上涨1.23%(yes)；两会行情+8；国内：复产复工+5；科技复苏；', '', '两会行情', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2020-05-10 20:56:46', '2020-05-10 21:22:11');
INSERT INTO `ol_fund_fupan` VALUES ('6', null, '2020wk20-yq', null, null, null, '上涨0.8%；两会行情+8；国内：复产复工+5；横盘；军工上涨；券商上涨；', '信息技术-回调；美股；', null, null, null, null, null, null, null, null, null, null, null, '+0.5', '+0.1', '+0.5', '+0.3', '+0.2', '+0.2', '+0.3', '-0.5', '+0.2', '0', '2020-05-10 21:22:19', '2020-05-17 12:23:22');
INSERT INTO `ol_fund_fupan` VALUES ('7', null, '2020wk20-sj', '161.66', '', '', '上涨0.5%；两会行情+3；国内：复产复工+3；横盘；军工横盘；券商下跌；', '信息技术-上涨；美股-横盘；', '科技利好消息', null, null, null, null, null, null, null, null, null, null, '-5', '-2', '-2', '-0.5', '+1.5', '+4.3', '-1.8', '+4.0', '+0.7', '0', '2020-05-10 21:22:19', '2020-05-17 12:23:24');
INSERT INTO `ol_fund_fupan` VALUES ('8', null, '2020wk21-yq', '', '', '', '上涨1.0%；两会行情+9；国内：复产复工+3；上涨；军工上涨；券商上涨；', '信息技术-回调；美股；', '', null, null, null, null, null, null, null, null, null, null, '+1.0', '+0.5', '+0.5', '+0.8', '+0.2', '+0.5', '+0.3', '-0.5', '+0.2', '0', '2020-05-10 21:22:19', '2020-05-17 12:26:30');
INSERT INTO `ol_fund_fupan` VALUES ('9', null, '20200518', '-509.18', '', '', '', '信息技术：美国限制华为半导体', '', null, null, null, null, null, null, null, null, null, null, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-24 11:09:34');
INSERT INTO `ol_fund_fupan` VALUES ('10', null, '20200519', '704.27', '', '', '', '', '', null, null, null, null, null, null, null, null, null, null, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-24 11:10:57');
INSERT INTO `ol_fund_fupan` VALUES ('11', null, '20200520', '-380.69', '', '', '', '全部：港股大跌', '', null, null, null, null, null, null, null, null, null, null, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-25 23:13:14');
INSERT INTO `ol_fund_fupan` VALUES ('12', null, '20200521', '-642.63', '', '', '', '全部：财务造假', '', null, null, null, null, null, null, null, null, null, null, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-24 11:12:20');
INSERT INTO `ol_fund_fupan` VALUES ('13', null, '20200524', '-1005.79', '', '', '', '全部：两会无GDP预期', '', null, null, null, null, null, null, null, null, null, null, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-24 11:11:36');
INSERT INTO `ol_fund_fupan` VALUES ('14', null, '20200525', '', '', '', '消费：两会内需', '全部：中美贸易摩擦', '', null, null, null, null, null, null, null, null, null, null, '', '', '', '', '', '', '', '', '', '', '2020-05-10 21:22:19', '2020-05-25 23:14:02');
INSERT INTO `ol_fund_fupan` VALUES ('15', null, '2020wk23-yq', '', '', '', '上涨1.0%；两会刺激+3；国内：复产复工+1；军工上涨；券商上涨；', '信息技术：中美贸易摩擦', '', null, null, null, null, null, null, null, null, null, null, '+1.0', '+0.5', '+0.2', '+0.3', '+0.5', '+0.2', '+0.3', '-0.2', '0', '0', '2020-05-10 21:22:19', '2020-05-31 10:53:22');
INSERT INTO `ol_fund_fupan` VALUES ('16', null, '2020wk22', '', '', '', '', '信息技术：中美贸易摩擦', '', null, '1.37', '1.33', '1.96', '1.12', '1.46', '1.45', '-0.14', '1.26', null, '+1.0', '+0.5', '+0.2', '+0.3', '+0.5', '+0.2', '+0.3', '-0.2', '0', '0', '2020-05-10 21:22:19', '2020-06-07 23:20:55');
INSERT INTO `ol_fund_fupan` VALUES ('17', null, '2020wk24-yq', '', '', '0', '{\"行业\":\"消费、新能源、黄金\"}上涨0.5%；两会刺激+3；国内：复产复工+1；券商上涨；', '国际：美股下跌', '', null, '', '', '', '', null, null, null, null, '', '+0.5', '+0.5', '+0.2', '+0.5', '+0.3', '+0.2', '+0.5', '-0.5', '0', '0', '2020-06-07 22:56:28', '2020-06-07 23:16:59');
INSERT INTO `ol_fund_fupan` VALUES ('18', null, '2020wk23', '2028.96', '', '1', '上涨1.0%；两会刺激+3；国内：复产复工+1；{\"行业\":\"通讯、电子元件、国际原油\"}', '信息技术：中美贸易摩擦{\"行业\":\"贵金属\"}', '利空出尽反弹；股市上涨，黄金下跌', null, '2.75', '4.04', '3.82', '3.47', '3.1', '1.88', '-0.47', '2.7', '', '+1.0', '+0.5', '+0.2', '+0.3', '+0.5', '+0.2', '+0.3', '-0.2', '0', '0', '2020-06-07 23:13:22', '2020-06-13 16:42:23');
INSERT INTO `ol_fund_fupan` VALUES ('19', null, '20200608', '33.30', '', '1', '{\"行业\":[{\"能源\":\"石油大涨5%\"}]}', '', '石油', null, '', '', '', '', '', '', '', '', '', '石油+5%', '', '', '', '', '', '', '', '', '', '2020-06-09 13:40:02', '2020-06-13 16:40:24');
INSERT INTO `ol_fund_fupan` VALUES ('20', null, '20200609', '366.22', '', '1', '[{\"行业\":[{\"金融\":\"T+0券商\"}]},{\"国际\":[{\"美股\":\"科技新高\"}]}]', '', '券商', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-06-11 14:13:21', '2020-06-13 16:40:51');
INSERT INTO `ol_fund_fupan` VALUES ('21', null, '20200610', '321.14', '', '1', '[{\"行业\":[{\"消费\":\"新能源车\"}]},{\"国际\":[]}]', '石油超涨下跌', '医疗', '[{\"rate\":2.1186,\"name\":\"水泥建材\"},{\"rate\":1.9833,\"name\":\"社保重仓\"},{\"rate\":1.9587,\"name\":\"医药行业\"},{\"rate\":1.8589,\"name\":\"生物疫苗\"},{\"rate\":1.8042,\"name\":\"医疗行业\"},{\"rate\":1.7381,\"name\":\"单抗概念\"},{\"rate\":1.6572,\"name\":\"病毒防治\"},{\"rate\":1.6442,\"name\":\"医疗器械\"},{\"rate\":1.5906,\"name\":\"医药制造\"},{\"rate\":1.5774,\"name\":\"二胎概念\"}]', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-06-11 14:13:21', '2020-06-13 16:41:31');
INSERT INTO `ol_fund_fupan` VALUES ('22', null, '20200611', '-210.13', '', '1', '[{\"行业\":[{\"信息通讯\":\"苹果\"}]},{\"国际\":[{\"美股\":\"降息保持0%\"}]}]', '', '', '[{\"rate\":1.4882,\"name\":\"阿里概念\"},{\"rate\":1.4665,\"name\":\"优选QDII\"},{\"rate\":1.3435,\"name\":\"车联网\"},{\"rate\":1.1333,\"name\":\"生物识别\"},{\"rate\":1.0621,\"name\":\"手游概念\"},{\"rate\":0.9824,\"name\":\"小金属\"},{\"rate\":0.8885,\"name\":\"移动支付\"},{\"rate\":0.7671,\"name\":\"贬值受益\"},{\"rate\":0.7305,\"name\":\"通讯行业\"},{\"rate\":0.7137,\"name\":\"国产软件\"}]', '-0.02', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-06-11 14:13:21', '2020-06-13 16:40:45');
INSERT INTO `ol_fund_fupan` VALUES ('23', null, '20200612', '-180.79', '', '1', '[{\"行业\":[]},{\"国际\":[]}]', '[{\"行业\":[{\"能源\":\"石油下跌\"}]},{\"国际\":[{\"美股\":\"暴跌\"}]}]', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-06-11 14:13:21', '2020-06-13 16:43:03');
INSERT INTO `ol_fund_fupan` VALUES ('24', null, '2020wk24', '329.73', '', '1', '[{\"行业\":[]},{\"国内\":[{\"创业板\":\"新规政策\"}]}]', '[{\"行业\":[{\"能源\":\"石油下跌\"}]},{\"国际\":[{\"美股\":\"暴跌\"}]}]', '', '', '-0.38', '0.64', '1.86', '0.05', '0.54', '0.84', '0.14', '-2.32', '', '', '', '', '', '', '', '', '', '', '', '2020-06-13 16:44:33', '2020-06-13 16:46:59');
INSERT INTO `ol_fund_fupan` VALUES ('25', null, '20200615', '', '', '1', '[{\"行业\":[]},{\"国内\":[{\"医药\":\"北京疫情\"}]}{\"国际\":[{\"能源\":\"石油上涨\"}]}]', '[{\"行业\":[]},{\"国际\":[]}]', '', '[{\"rate\":1.8595,\"name\":\"国际原油\"},{\"rate\":1.4204,\"name\":\"生物疫苗\"},{\"rate\":1.3071,\"name\":\"优选QDII\"},{\"rate\":1.1188,\"name\":\"医药行业\"},{\"rate\":1.0997,\"name\":\"医疗器械\"},{\"rate\":0.8931,\"name\":\"医疗行业\"},{\"rate\":0.882,\"name\":\"病毒防治\"},{\"rate\":0.8426,\"name\":\"单抗概念\"},{\"rate\":0.8273,\"name\":\"中药\"},{\"rate\":0.6488,\"name\":\"医药制造\"}]', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-06-16 09:43:44', '2020-06-16 09:51:17');
INSERT INTO `ol_fund_fupan` VALUES ('26', null, '2020wk25-yq', '', '', '0', '[{\"行业\":[{\"创业板\":\"新规政策\"}]},{\"国内\":[]}]', '[{\"行业\":[]},{\"国内\":[{\"医药\":\"北京疫情\"}],{\"国际\":[{\"政治\":\"中美关系\"}]}]', '', '', '-1.0', '-1.0', '0.5', '-1.0', '', '', '', '', '', '石油上涨', '', '', '下跌-1.0', '', '上涨0.5', '', '下跌-1.5', '下跌-1.5', '', '2020-06-13 16:44:33', '2020-06-16 10:10:11');
INSERT INTO `ol_fund_fupan` VALUES ('27', null, '20200616', '', '', '1', '[{\"行业\":[]},{\"国内\":[]}{\"国际\":[]}]', '[{\"行业\":[]},{\"国内\":[]}{\"国际\":[]}]', '', '[{\"rate\":2.7645,\"name\":\"钢铁行业\"},{\"rate\":2.685,\"name\":\"基本金属\"},{\"rate\":2.5754,\"name\":\"通讯行业\"},{\"rate\":2.4678,\"name\":\"电子元件\"},{\"rate\":2.4516,\"name\":\" 沪港深\"},{\"rate\":2.3664,\"name\":\"区块链\"},{\"rate\":2.3521,\"name\":\"优选股基\"},{\"rate\":2.313,\"name\":\"在线教育\"},{\"rate\":2.3096,\"name\":\"海工装备\"},{\"rate\":2.2626,\"name\":\"生物疫苗\"}]', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-06-17 09:39:32', '2020-06-17 09:39:57');
INSERT INTO `ol_fund_fupan` VALUES ('28', null, '20200617', '', '', '1', '[{\"行业\":[]},{\"国内\":[{\"医药\":\"北京疫情\"},{\"军工\":\"航空航天\"]}{\"国际\":[]}]', '[{\"行业\":[]},{\"国内\":[]}{\"国际\":[]}]', '', '[{\"rate\":2.7179,\"name\":\"国际原油\"},{\"rate\":2.6203,\"name\":\"优选QDII\"},{\"rate\":2.1606,\"name\":\"生物疫苗\"},{\"rate\":2.0403,\"name\":\"单抗概念\"},{\"rate\":1.8925,\"name\":\"病毒防治\"},{\"rate\":1.738,\"name\":\"医疗器械\"},{\"rate\":1.6788,\"name\":\"中药\"},{\"rate\":1.6639,\"name\":\"航天航空\"},{\"rate\":1.6347,\"name\":\"通用航空\"},{\"rate\":1.5854,\"name\":\"社保重仓\"}]', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-06-18 09:19:43', '2020-06-18 09:36:27');
INSERT INTO `ol_fund_fupan` VALUES ('29', null, '20200618', '', '', '1', '[{\"行业\":[{\"通讯\":\"华为缓和\"}]},{\"国内\":[{\"会议\":\"降准\"},{\"会议\":\"中美经贸会议\"]}}{\"国际\":[]}]', '[{\"行业\":[{\"医药\":\"回调\"}]},{\"国内\":[]}{\"国际\":[]}]', '', '[{\"rate\":3.4334,\"name\":\"通讯行业\"},{\"rate\":2.7136,\"name\":\"电子元件\"},{\"rate\":2.5835,\"name\":\"智能穿戴\"},{\"rate\":2.303,\"name\":\"华为概念\"},{\"rate\":2.0429,\"name\":\"量子通信\"},{\"rate\":1.9698,\"name\":\"生物识别\"},{\"rate\":1.9227,\"name\":\"苹果概念\"},{\"rate\":1.8488,\"name\":\"煤炭采选\"},{\"rate\":1.7843,\"name\":\"5G概念\"},{\"rate\":1.7647,\"name\":\"中超概念\"}]', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-06-18 09:19:43', '2020-06-19 09:23:23');
INSERT INTO `ol_fund_fupan` VALUES ('30', null, '2020wk25', '', '', '1', '', '', '', '[{\"rate\":6.4843,\"name\":\"生物疫苗\"},{\"rate\":5.9671,\"name\":\"病毒防治\"},{\"rate\":5.579,\"name\":\"材料行业\"},{\"rate\":5.5071,\"name\":\"中药\"},{\"rate\":5.3428,\"name\":\"社保重仓\"},{\"rate\":5.3254,\"name\":\"单抗概念\"},{\"rate\":5.2543,\"name\":\"特斯拉\"},{\"rate\":5.2166,\"name\":\"医疗行业\"},{\"rate\":5.1691,\"name\":\"二胎概念\"},{\"rate\":5.0697,\"name\":\"医疗器械\"}]\r\n', '1.64', '3.7', '5.11', '2.39', '2.89', '2.07', '0.07', '1.85', '', '', '', '', '', '', '', '', '', '', '', '2020-06-21 21:33:27', '2020-06-21 21:42:27');
INSERT INTO `ol_fund_fupan` VALUES ('31', null, '2020wk26-yq', '', '', '0', '[{\"行业\":[{\"创业板\":\"新规政策\"}]},{\"国内\":[]}]', '', '券商；创业板50；中报', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-06-21 21:33:27', '2020-06-21 21:37:10');
INSERT INTO `ol_fund_fupan` VALUES ('32', null, '20200924', null, null, '1', null, '美股大跌-0.5；节前行情-0.5；北向流出-0.8', null, null, '-1.72', '-2.24', '-2.46', '-1.92', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2020-09-24 23:36:27', '2020-09-24 23:46:14');
INSERT INTO `ol_fund_fupan` VALUES ('33', null, '20200925', '', '', '0', '超跌反弹+1.1；美股涨+0.3；', '节前行情-0.3；', '50；300', '', '+1.1', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-09-24 23:36:27', '2020-09-24 23:49:58');
INSERT INTO `ol_fund_fupan` VALUES ('34', '7', '20200927', '', '', '1', '超跌反弹+0.5；美股涨+0.3；', '节前行情-1.3；利空-1.0;外盘大跌-1.2;下降趋势-0.5;国外疫情-0.5', '50；300;券商', '', '-3.56', '-3.25', '-3.53', '-3.53', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-09-24 23:36:27', '2020-09-27 23:07:13');
INSERT INTO `ol_fund_fupan` VALUES ('35', '7', '20200927', '', '', '0', '超跌反弹+1.5;外盘涨+0.5;', '节前行情-0.2;利空-0.2;外盘大跌-0.2;下降趋势-0.2', '50；300;券商', '', '+1.2', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-09-24 23:36:27', '2020-09-27 23:11:05');
INSERT INTO `ol_fund_fupan` VALUES ('36', '1', '20200928', '', '', '0', '超跌反弹+0.3;外盘涨+0.3;', '节前行情-0.3;利空-0.2;外盘大跌-0.0;下降趋势-0.0', '50；300;券商', '', '+0.1', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-09-24 23:36:27', '2020-09-27 23:14:15');
INSERT INTO `ol_fund_fupan` VALUES ('37', '1', '20200928', '', '', '1', '超跌反弹+0.3;外盘涨+0.1;', '节前行情-0.4;利空-0.2;外盘大跌-0.0;下降趋势-0.2;北向-0.1', '50;300;', '', '-0.06', '-0.42', '-0.76', '0.26', '-0.4', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-09-24 23:36:27', '2020-09-29 00:05:05');
INSERT INTO `ol_fund_fupan` VALUES ('38', '1', '20200929', '', '', '0', '超跌反弹+0.6;外盘涨+0.2;', '节前行情-0.2;利空-0.1;外盘-0.0;趋势-0.0;北向-0.0', '50;300;', '', '+0.6', '0.5', '0.4', '+0.75', '+0.5', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2020-09-24 23:36:27', '2020-09-29 00:09:10');
