/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : meite_pay

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2019-03-23 22:12:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for payment_channel
-- ----------------------------
DROP TABLE IF EXISTS `payment_channel`;
CREATE TABLE `payment_channel` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `CHANNEL_NAME` varchar(32) NOT NULL COMMENT '渠道名称',
  `CHANNEL_ID` varchar(32) NOT NULL COMMENT '渠道ID',
  `MERCHANT_ID` varchar(32) NOT NULL COMMENT '商户id',
  `SYNC_URL` text NOT NULL COMMENT '同步回调URL',
  `ASYN_URL` text NOT NULL COMMENT '异步回调URL',
  `PUBLIC_KEY` text COMMENT '公钥',
  `PRIVATE_KEY` text COMMENT '私钥',
  `CHANNEL_STATE` int(11) DEFAULT '0' COMMENT '渠道状态 0开启1关闭',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CLASS_ADDRES` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`,`CHANNEL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='支付渠道 ';

-- ----------------------------
-- Records of payment_channel
-- ----------------------------
INSERT INTO `payment_channel` VALUES ('1', '银联支付', 'yinlian_pay', '777290058110048', 'http://localhost:8080/ACPSample_B2C/frontRcvResponse', 'http://222.222.222.222:8080/ACPSample_B2C/backRcvResponse', null, null, '0', null, null, null, null, null, null);
INSERT INTO `payment_channel` VALUES ('2', '支付宝', 'ali_pay', '777666655522521', 'test', 'test', null, null, '0', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for payment_transaction
-- ----------------------------
DROP TABLE IF EXISTS `payment_transaction`;
CREATE TABLE `payment_transaction` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `PAY_AMOUNT` int(11) NOT NULL COMMENT '支付金额',
  `PAYMENT_STATUS` int(11) NOT NULL DEFAULT '0' COMMENT '支付状态 0待支付1已经支付2支付超时3支付失败',
  `USER_ID` int(11) NOT NULL COMMENT '用户ID',
  `ORDER_ID` varchar(32) NOT NULL COMMENT '订单号码',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `PARTYPAY_ID` varchar(32) DEFAULT NULL,
  `PAYMENT_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='支付交易 ';

-- ----------------------------
-- Records of payment_transaction
-- ----------------------------
INSERT INTO `payment_transaction` VALUES ('1', '1000', '0', '1', '20180302101452', null, null, null, null, null, null, null);
INSERT INTO `payment_transaction` VALUES ('2', '1000', '0', '1', '20180302101452', null, null, '2019-03-21 19:06:15', null, '2019-03-21 19:06:15', null, null);
INSERT INTO `payment_transaction` VALUES ('3', '1000', '0', '1', '20180302101452', null, null, '2019-03-21 19:31:42', null, '2019-03-21 19:31:42', null, null);
INSERT INTO `payment_transaction` VALUES ('4', '1', '0', '644064', '20180302101452', null, null, '2019-03-23 15:09:05', null, '2019-03-23 15:09:05', null, null);
INSERT INTO `payment_transaction` VALUES ('5', '1', '0', '644064', '20180302101452', null, null, '2019-03-23 15:13:08', null, '2019-03-23 15:13:08', null, null);
INSERT INTO `payment_transaction` VALUES ('6', '1', '0', '644064', '20180302101452', null, null, '2019-03-23 15:17:23', null, '2019-03-23 15:17:23', null, null);
INSERT INTO `payment_transaction` VALUES ('7', '1', '0', '644064', '20180302101452', null, null, '2019-03-23 15:59:37', null, '2019-03-23 15:59:37', '269342963258232832', null);
INSERT INTO `payment_transaction` VALUES ('8', '1', '0', '644064', '20180302101452', null, null, '2019-03-23 16:06:34', null, '2019-03-23 16:06:34', null, '269344715239985152');
INSERT INTO `payment_transaction` VALUES ('9', '1', '0', '644064', '20180302101452', null, null, '2019-03-23 16:12:41', null, '2019-03-23 16:12:41', null, '269346254797017088');
INSERT INTO `payment_transaction` VALUES ('10', '10020', '0', '644064', '2019010203501501', null, null, '2019-03-23 16:15:22', null, '2019-03-23 16:15:22', null, '269346928683257856');
INSERT INTO `payment_transaction` VALUES ('11', '10020', '0', '644064', '2019010203501501', null, null, '2019-03-23 17:40:15', null, '2019-03-23 17:40:15', null, '269368292148383744');
INSERT INTO `payment_transaction` VALUES ('12', '10020', '0', '644064', '2019010203501501', null, null, '2019-03-23 17:40:25', null, '2019-03-23 17:40:25', null, '269368331461595136');
INSERT INTO `payment_transaction` VALUES ('13', '10020', '0', '644064', '2019010203501501', null, null, '2019-03-23 20:14:45', null, '2019-03-23 20:14:45', null, '269407170557251584');
INSERT INTO `payment_transaction` VALUES ('14', '10020', '0', '644064', '2019010203501501', null, null, '2019-03-23 20:28:30', null, '2019-03-23 20:28:30', null, '269410634171551744');
INSERT INTO `payment_transaction` VALUES ('15', '20020', '0', '644064', '2019010203501501', null, null, '2019-03-23 20:28:43', null, '2019-03-23 20:28:43', null, '269410688940773376');
INSERT INTO `payment_transaction` VALUES ('16', '20020', '0', '644064', '2019010203501501', null, null, '2019-03-23 20:35:52', null, '2019-03-23 20:35:52', null, '269412486262951936');
INSERT INTO `payment_transaction` VALUES ('17', '30020', '0', '644064', '2019010203501501', null, null, '2019-03-23 20:36:39', null, '2019-03-23 20:36:39', null, '269412683504291840');
INSERT INTO `payment_transaction` VALUES ('18', '30020', '0', '644064', '2019010203501501', null, null, '2019-03-23 20:58:41', null, '2019-03-23 20:58:41', null, '269418222355353600');
INSERT INTO `payment_transaction` VALUES ('19', '30020', '0', '644064', '2019010203501501', null, null, '2019-03-23 21:13:19', null, '2019-03-23 21:13:19', null, '269421909492502528');

-- ----------------------------
-- Table structure for payment_transaction_log
-- ----------------------------
DROP TABLE IF EXISTS `payment_transaction_log`;
CREATE TABLE `payment_transaction_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `SYNCH_LOG` text COMMENT '同步回调日志',
  `ASYNC_LOG` text COMMENT '异步回调日志',
  `CHANNEL_ID` int(11) DEFAULT NULL COMMENT '支付渠道ID',
  `TRANSACTION_ID` int(11) DEFAULT NULL COMMENT '支付交易ID',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `untitled` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付交易日志表 ';

-- ----------------------------
-- Records of payment_transaction_log
-- ----------------------------
