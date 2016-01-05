
CREATE USER 'wuzhong'@'%' IDENTIFIED BY 'Free10031204';
GRANT SELECT, INSERT,UPDATE ON test.* TO 'wuzhong'@'%';
grant SELECT, INSERT,UPDATE  on test.* to 'wuzhong'@'localhost' identified by 'Free10031204';

-- --------------------------------------------------------
-- 主机:                           120.26.103.47
-- 服务器版本:                        5.6.21-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      linux-glibc2.5
-- HeidiSQL 版本:                  8.1.0.4545
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 develop 的数据库结构
CREATE DATABASE IF NOT EXISTS `develop` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `develop`;


-- 导出  表 develop.app 结构
CREATE TABLE IF NOT EXISTS `app` (
  `appid` varchar(32) NOT NULL,
  `secret` varchar(64) NOT NULL,
  `userid` int(11) NOT NULL,
  `price` float(8,2) DEFAULT NULL COMMENT '年费',
  `descption` varchar(256) DEFAULT NULL COMMENT 'app描述',
  `expiredate` datetime DEFAULT NULL COMMENT '暂时没用到',
  PRIMARY KEY (`appid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 develop.app_poi 结构
CREATE TABLE IF NOT EXISTS `app_poi` (
  `poi_id` int(11) NOT NULL AUTO_INCREMENT,
  `app_poi_code` varchar(128) NOT NULL COMMENT 'APP方商家ID ',
  `appid` varchar(32) NOT NULL,
  `bd_poi_code` varchar(128) DEFAULT NULL,
  `bd_appid` varchar(32) DEFAULT NULL,
  `eleme_poi_code` varchar(128) DEFAULT NULL,
  `eleme_appid` varchar(32) DEFAULT NULL,
  `kb_poi_code` varchar(128) DEFAULT NULL,
  `kb_appid` varchar(32) DEFAULT NULL,
  `userid` int(11) NOT NULL COMMENT 'userid',
  `wm_poi_name` varchar(128) DEFAULT NULL COMMENT '美团商家名称 ',
  `wm_poi_address` varchar(512) DEFAULT NULL COMMENT '美团商家地址',
  `wm_poi_phone` varchar(128) DEFAULT NULL COMMENT '美团商家电话 ',
  `amount` float(8,2) DEFAULT NULL COMMENT '累计金额（暂时没用到）',
  `price` float(8,2) DEFAULT NULL COMMENT '单价',
  `descption` varchar(256) DEFAULT NULL COMMENT '门店描述',
  `expiredate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '门店有效期',
  PRIMARY KEY (`poi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 develop.charge_record 结构
CREATE TABLE `charge_record` (
	`record_id` INT(11) NOT NULL AUTO_INCREMENT,
	`poi_id` INT(11) NOT NULL DEFAULT '0',
	`poi_name` VARCHAR(128) NULL DEFAULT NULL,
	`czsj` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`czns` INT(11) NOT NULL,
	`czje` FLOAT(8,2) NOT NULL,
	`czfs` VARCHAR(64) NOT NULL,
	`jyh` VARCHAR(64) NULL DEFAULT NULL COMMENT '交易号',
	`out_trade_no` VARCHAR(64) NULL DEFAULT NULL COMMENT '流水号',
	PRIMARY KEY (`record_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=10000;


-- 数据导出被取消选择。


-- 导出  表 develop.login_users 结构
CREATE TABLE IF NOT EXISTS `login_users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` varchar(64) NOT NULL,
  `login_pass` varchar(64) NOT NULL DEFAULT '666',
  `nfdj` float DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '0,停用,1，启用',
  `descption` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 develop.meituan_order 结构
CREATE TABLE IF NOT EXISTS `meituan_order` (
  `order_id` int(11) NOT NULL COMMENT '订单ID ',
  `wm_order_id_view` bigint(20) DEFAULT NULL COMMENT '订单展示ID ',
  `app_poi_code` varchar(128) NOT NULL COMMENT 'APP方商家ID ',
  `wm_poi_name` varchar(128) DEFAULT NULL COMMENT '美团商家名称 ',
  `wm_poi_address` varchar(512) DEFAULT NULL COMMENT '美团商家地址',
  `wm_poi_phone` varchar(128) DEFAULT NULL COMMENT '美团商家电话 ',
  `recipient_address` varchar(512) DEFAULT NULL COMMENT '收件人地址 ',
  `recipient_phone` varchar(128) DEFAULT NULL COMMENT '收件人电话',
  `recipient_name` varchar(128) DEFAULT NULL COMMENT '收件人姓名 ',
  `shipping_fee` float(8,2) DEFAULT NULL COMMENT '门店配送费 ',
  `total` float(8,2) DEFAULT NULL COMMENT ' 	总价 ',
  `original_price` decimal(8,2) DEFAULT NULL COMMENT '原价 ',
  `caution` varchar(1024) DEFAULT NULL COMMENT '忌口或备注 ',
  `shipper_phone` varchar(32) DEFAULT NULL COMMENT '送餐员电话',
  `status` int(1) DEFAULT NULL COMMENT ' 	订单状态 ',
  `city_id` int(11) DEFAULT NULL COMMENT '城市ID（目前暂时用不到此信息） ',
  `has_invoiced` int(4) DEFAULT NULL COMMENT '是否开发票 ',
  `invoice_title` varchar(256) DEFAULT NULL COMMENT '发票抬头',
  `ctime` int(11) DEFAULT NULL COMMENT '创建时间 ',
  `utime` int(11) DEFAULT NULL COMMENT '更新时间 ',
  `delivery_time` int(11) DEFAULT NULL COMMENT '用户预计送达时间，“立即送达”时为0 ',
  `is_third_shipping` int(1) DEFAULT NULL COMMENT '是否为美团商家、APP方配送（0：否；1：是） ',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付类型（1：货到付款；2：在线支付）',
  `latitude` decimal(11,7) DEFAULT NULL COMMENT '实际送餐地址纬度',
  `longitude` decimal(11,7) DEFAULT NULL COMMENT '实际送餐地址经度 ',
  `detail` varchar(6144) DEFAULT NULL,
  `extra` varchar(4096) DEFAULT NULL,
  `app_status` int(2) NOT NULL DEFAULT '0' COMMENT '门店是否已下载该订单（0：否，1：是）',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 develop.refund 结构
CREATE TABLE IF NOT EXISTS `refund` (
  `order_id` int(11) NOT NULL COMMENT '订单ID ',
  `app_poi_code` varchar(128) NOT NULL,
  `notify_type` varchar(36) NOT NULL COMMENT '通知类型，apply：发起退款',
  `reason` varchar(1024) DEFAULT NULL COMMENT '原因 ',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退款';

-- 数据导出被取消选择。


-- 导出  事件 develop.clean_order_data 结构
DELIMITER //
CREATE DEFINER=`root`@`%` EVENT `clean_order_data` ON SCHEDULE EVERY 1 DAY STARTS '2015-12-04 00:00:00' ON COMPLETION PRESERVE ENABLE COMMENT '每天定期清理meituan_order数据，保留一个月的数据' DO BEGIN
delete from  meituan_order  where  datediff(DATE_FORMAT(CURDATE(),'%Y%m%d'),DATE_FORMAT(FROM_UNIXTIME(utime),'%Y%m%d')) > 2;
END//
DELIMITER ;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;


