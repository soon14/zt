/*
Navicat MySQL Data Transfer

Source Server         : 118.25.209.50
Source Server Version : 50724
Source Host           : 118.25.209.50:3306
Source Database       : zt_schedule

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-04-02 16:54:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for quartz_config
-- ----------------------------
DROP TABLE IF EXISTS `quartz_config`;
CREATE TABLE `quartz_config` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '调度规则编号',
  `quartz_group` varchar(255) DEFAULT NULL COMMENT '调度任务分组',
  `quartz_name` varchar(255) DEFAULT NULL COMMENT '调度任务名称',
  `cron` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `quartz_status` int(11) DEFAULT NULL COMMENT '状态 0为正常 1为停止',
  `msg` varchar(255) DEFAULT NULL COMMENT '消息',
  `quartz_class` varchar(255) DEFAULT NULL COMMENT '任务执行类',
  `start_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '生效时间',
  `end_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '失效时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=uft8;

-- ----------------------------
-- Records of quartz_config
-- ----------------------------
INSERT INTO `quartz_config` VALUES ('2', 'test1', 'test1', '0/1 * * * * ?', '0', null, 'com.chinaunicom.js.schedule.task.MyTestTask', '2019-04-01 12:43:12', '2019-04-05 12:43:12', 'system', 'system', '2019-04-02 13:19:29', '2019-04-02 16:39:42');
INSERT INTO `quartz_config` VALUES ('3', 'test1', 'test1', '0/5 * * * * ?', '1', null, 'com.chinaunicom.js.schedule.task.MyTestTask', '2019-04-01 12:43:12', '2019-04-05 12:43:12', 'system', null, '2019-04-02 13:23:30', null);
