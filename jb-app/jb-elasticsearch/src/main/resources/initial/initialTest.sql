/*
Navicat MySQL Data Transfer

Source Server         : localhost_3307
Source Server Version : 50637
Source Host           : 127.0.0.1:3307
Source Database       : elastic

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-07-23 16:31:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '顾客ID',
  `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `nick_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `wallet` double(8,2) DEFAULT '0.00' COMMENT '钱包',
  `gender` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of customer
-- ----------------------------

-- ----------------------------
-- Table structure for customer_address
-- ----------------------------
DROP TABLE IF EXISTS `customer_address`;
CREATE TABLE `customer_address` (
  `id` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '顾客地址ID',
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `phone` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `province` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '省份(如:河南省)',
  `city` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '省市',
  `area` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '区域(如：金水区)',
  `area_code` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '该区域code',
  `address` varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `status` int(1) DEFAULT NULL COMMENT '是否是默认1默认0不是',
  `customer_id` int(8) DEFAULT NULL COMMENT '顾客ID',
  `type` int(1) DEFAULT NULL COMMENT '0顾客地址  1商家地址',
  `address_str` varchar(255) CHARACTER SET utf16 DEFAULT NULL COMMENT '地址拼接',
  `is_deleted` int(1) DEFAULT '0' COMMENT '顾客的地址是否删除0没有删除   1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of customer_address
-- ----------------------------
