/*
Navicat MySQL Data Transfer

Source Server         : 10.28.175.91
Source Server Version : 50161
Source Host           : 10.28.175.91:3306
Source Database       : gallery

Target Server Type    : MYSQL
Target Server Version : 50161
File Encoding         : 65001

Date: 2015-12-27 20:44:01 author ttx
*/
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `wishmvc` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `wishmvc` ;

-- ------------------------------------------------
-- Table structure for `user_demo`， add by ttx 2015-6-1
-- ------------------------------------------------
CREATE  TABLE IF NOT EXISTS `wishmvc`.`user_demo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted_time` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '是否删除字段(1表示删除)',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '用户表';


-- ------------------------------------------------
-- 初始化 user_demo 用户数据
-- ------------------------------------------------
INSERT 
INTO user_demo(`name`, password, created_time, updated_time, deleted_time, deleted) 
VALUES('zhangsan', '123456', '2015-12-27 20:54:12', '2015-12-27 20:54:25', NULL, 0);

INSERT 
INTO user_demo(`name`, password, created_time, updated_time, deleted_time, deleted) 
VALUES('lisi', '123456', '2015-12-27 21:54:12', '2015-12-27 22:54:25', NULL, 0);

INSERT 
INTO user_demo(`name`, password, created_time, updated_time, deleted_time, deleted) 
VALUES('wangwu', '123456', '2015-12-27 20:54:12', '2015-12-27 20:54:25', '2015-12-27 20:54:25', 1);

