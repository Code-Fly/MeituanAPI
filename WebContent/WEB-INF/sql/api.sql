-- --------------------------------------------------------
-- 主机:                           120.26.103.47
-- 服务器版本:                        5.6.21-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      linux-glibc2.5
-- --------------------------------------------------------

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





