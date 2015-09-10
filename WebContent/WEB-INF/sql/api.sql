-- --------------------------------------------------------
-- 主机:                           120.26.103.47
-- 服务器版本:                        5.6.21-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      linux-glibc2.5
-- HeidiSQL 版本:                  9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 test.app 结构
CREATE TABLE IF NOT EXISTS `app` (
  `appid` varchar(32) NOT NULL,
  `secret` varchar(64) NOT NULL,
  `desc` varchar(32) DEFAULT NULL COMMENT 'app描述',
  `expiredate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`appid`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

-- 数据导出被取消选择。


-- 导出  表 test.meituan_order 结构
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
  `ctime` varchar(36) DEFAULT NULL COMMENT '创建时间 ',
  `utime` varchar(36) DEFAULT NULL COMMENT '更新时间 ',
  `delivery_time` varchar(36) DEFAULT NULL COMMENT '用户预计送达时间，“立即送达”时为0 ',
  `is_third_shipping` int(1) DEFAULT NULL COMMENT '是否为美团商家、APP方配送（0：否；1：是） ',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付类型（1：货到付款；2：在线支付）',
  `latitude` varchar(24) DEFAULT NULL COMMENT '实际送餐地址纬度',
  `longitude` varchar(24) DEFAULT NULL COMMENT '实际送餐地址经度 ',
  `detail` varchar(2048) DEFAULT NULL,
  `extra` varchar(2048) DEFAULT NULL,
  `app_status` int(2) NOT NULL DEFAULT '0' COMMENT '门店是否已下载该订单（0：否，1：是）'
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 test.meituan_order_detail 结构
CREATE TABLE IF NOT EXISTS `meituan_order_detail` (
  `detail_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(36) DEFAULT NULL,
  `app_food_code` int(2) DEFAULT NULL,
  `food_name` varchar(80) DEFAULT NULL,
  `sku_id` varchar(8) DEFAULT NULL,
  `quantity` int(4) DEFAULT NULL,
  `price` decimal(5,1) DEFAULT NULL,
  `box_num` int(4) DEFAULT NULL,
  `box_price` decimal(5,1) DEFAULT NULL,
  `unit` varchar(8) DEFAULT NULL,
  `food_discount` decimal(3,2) DEFAULT NULL,
  PRIMARY KEY (`detail_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 test.meituan_order_extras 结构
CREATE TABLE IF NOT EXISTS `meituan_order_extras` (
  `act_detail_id` int(10) NOT NULL,
  `order_id` varchar(36) NOT NULL,
  `reduce_fee` decimal(5,1) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  `avg_send_time` int(5) DEFAULT NULL,
  PRIMARY KEY (`act_detail_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
