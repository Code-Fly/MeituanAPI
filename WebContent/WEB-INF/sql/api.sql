
CREATE USER 'wuzhong'@'%' IDENTIFIED BY 'Free10031204';
GRANT SELECT, INSERT,UPDATE ON test.* TO 'wuzhong'@'%';
grant SELECT, INSERT,UPDATE  on test.* to 'wuzhong'@'localhost' identified by 'Free10031204';

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
CREATE TABLE `meituan_order` (
	`order_id` INT(11) NOT NULL COMMENT '订单ID ',
	`wm_order_id_view` BIGINT(20) NULL DEFAULT NULL COMMENT '订单展示ID ',
	`app_poi_code` VARCHAR(128) NOT NULL COMMENT 'APP方商家ID ',
	`wm_poi_name` VARCHAR(128) NULL DEFAULT NULL COMMENT '美团商家名称 ',
	`wm_poi_address` VARCHAR(512) NULL DEFAULT NULL COMMENT '美团商家地址',
	`wm_poi_phone` VARCHAR(128) NULL DEFAULT NULL COMMENT '美团商家电话 ',
	`recipient_address` VARCHAR(512) NULL DEFAULT NULL COMMENT '收件人地址 ',
	`recipient_phone` VARCHAR(128) NULL DEFAULT NULL COMMENT '收件人电话',
	`recipient_name` VARCHAR(128) NULL DEFAULT NULL COMMENT '收件人姓名 ',
	`shipping_fee` FLOAT(8,2) NULL DEFAULT NULL COMMENT '门店配送费 ',
	`total` FLOAT(8,2) NULL DEFAULT NULL COMMENT ' 	总价 ',
	`original_price` DECIMAL(8,2) NULL DEFAULT NULL COMMENT '原价 ',
	`caution` VARCHAR(1024) NULL DEFAULT NULL COMMENT '忌口或备注 ',
	`shipper_phone` VARCHAR(32) NULL DEFAULT NULL COMMENT '送餐员电话',
	`status` INT(1) NULL DEFAULT NULL COMMENT ' 	订单状态 ',
	`city_id` INT(11) NULL DEFAULT NULL COMMENT '城市ID（目前暂时用不到此信息） ',
	`has_invoiced` INT(4) NULL DEFAULT NULL COMMENT '是否开发票 ',
	`invoice_title` VARCHAR(256) NULL DEFAULT NULL COMMENT '发票抬头',
	`ctime` INT(11) NULL DEFAULT NULL COMMENT '创建时间 ',
	`utime` INT(11) NULL DEFAULT NULL COMMENT '更新时间 ',
	`delivery_time` INT(11) NULL DEFAULT NULL COMMENT '用户预计送达时间，“立即送达”时为0 ',
	`is_third_shipping` INT(1) NULL DEFAULT NULL COMMENT '是否为美团商家、APP方配送（0：否；1：是） ',
	`pay_type` INT(1) NULL DEFAULT NULL COMMENT '支付类型（1：货到付款；2：在线支付）',
	`latitude` DECIMAL(11,7) NULL DEFAULT NULL COMMENT '实际送餐地址纬度',
	`longitude` DECIMAL(11,7) NULL DEFAULT NULL COMMENT '实际送餐地址经度 ',
	`detail` VARCHAR(6144) NULL DEFAULT NULL,
	`extra` VARCHAR(4096) NULL DEFAULT NULL,
	`app_status` INT(2) NOT NULL DEFAULT '0' COMMENT '门店是否已下载该订单（0：否，1：是）',
	PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `refund` (
	`order_id` INT(11) NOT NULL COMMENT '订单ID ',
	`app_poi_code` VARCHAR(128) NOT NULL,
	`notify_type` VARCHAR(36) NOT NULL COMMENT '通知类型，apply：发起退款',
	`reason` VARCHAR(1024) NULL DEFAULT NULL COMMENT '原因 ',
	PRIMARY KEY (`order_id`)
)
COMMENT='退款'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;



CREATE TABLE `app_poi` (
	`app_poi_code` VARCHAR(128) NOT NULL COMMENT 'APP方商家ID ',
	`appid` VARCHAR(32) NOT NULL,
	`wm_poi_name` VARCHAR(128) NULL DEFAULT NULL COMMENT '美团商家名称 ',
	`wm_poi_address` VARCHAR(512) NULL DEFAULT NULL COMMENT '美团商家地址',
	`wm_poi_phone` VARCHAR(128) NULL DEFAULT NULL COMMENT '美团商家电话 ',
	`amount` FLOAT(8,2) NULL DEFAULT NULL COMMENT '累计金额',
	`price` FLOAT(8,2) NULL DEFAULT NULL COMMENT '单价',
	`desc` VARCHAR(32) NULL DEFAULT NULL COMMENT '门店描述',
	`expiredate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '门店有效期',
	PRIMARY KEY (`app_poi_code`, `appid`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

;


