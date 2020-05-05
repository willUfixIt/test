/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.17-log : Database - hotel
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hotel` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hotel`;

/*Table structure for table `in_room_info` */

DROP TABLE IF EXISTS `in_room_info`;

CREATE TABLE `in_room_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_name` varchar(40) DEFAULT NULL COMMENT '客人姓名',
  `gender` varchar(2) DEFAULT '1' COMMENT '性别(1男 0女)',
  `is_vip` varchar(2) DEFAULT '0' COMMENT '0普通，1vip',
  `idcard` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `money` float(10,2) DEFAULT NULL COMMENT '押金',
  `create_date` datetime DEFAULT NULL COMMENT '入住时间',
  `room_id` bigint(20) DEFAULT NULL COMMENT '房间表主键',
  `status` varchar(2) DEFAULT '1' COMMENT '显示状态：1显示，0隐藏',
  `out_room_status` varchar(2) DEFAULT '0' COMMENT '退房状态：0未退房 1已经退房',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `in_room_info` */

insert  into `in_room_info`(`id`,`customer_name`,`gender`,`is_vip`,`idcard`,`phone`,`money`,`create_date`,`room_id`,`status`,`out_room_status`) values (1,'赵子龙','1','0','411311199001015599','13012345678',200.00,'2018-09-06 20:19:03',1,'0','0'),(2,'张飞','1','0','411311199001015588','13812345678',249.00,'2018-09-05 20:20:01',2,'1','1'),(3,'刘备','1','0','411311199001015577','15912345612',200.00,'2018-09-04 20:20:32',3,'1','0'),(4,'诸葛亮','1','0','411311199001015522','15912345611',150.00,'2018-09-06 20:20:59',4,'1','0'),(5,'曹操','1','0','411311199001015566','13912345622',200.00,'2018-09-03 20:21:28',5,'1','1'),(7,'丁鹏','1','1','123456789123456780','15912345678',200.00,'2018-10-23 00:00:00',6,'1','0'),(8,'王二麻子','0','1','123456789123456781','15912345678',200.00,'2018-10-23 00:00:00',7,'1','1');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_num` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `order_money` float(10,2) DEFAULT NULL COMMENT '订单总价',
  `order_status` varchar(2) DEFAULT '0' COMMENT '0未结算，1已结算',
  `room_id` bigint(20) DEFAULT NULL COMMENT '房间主键',
  `create_date` datetime DEFAULT NULL COMMENT '下单时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`id`,`order_num`,`order_money`,`order_status`,`room_id`,`create_date`) values (2,'f79ca7db-3383-4b4f-8faa-da59e769bf2a',200.00,'1',7,'2018-10-24 11:08:42'),(3,'7d0a3f0a-f576-4b2c-b5b7-17084fae9916',3.50,'0',7,'2018-10-24 11:11:21'),(4,'6ec659c6-c8ee-4cbd-b353-5be759650147',3.50,'1',3,'2018-10-24 14:06:37'),(5,'7b20ca8d-9f03-42ee-9aaf-512538eab704',30.50,'1',7,'2018-10-24 14:09:43'),(6,'42060571-9d86-4b18-aec7-58baf0d4c14b',200.00,'1',7,'2018-10-24 14:10:08'),(7,'d41cea47-cae1-424c-b492-d7699ce3158d',3.50,'0',7,'2018-10-24 15:42:21'),(8,'680ce894-a546-414b-9acb-b29c9123faaf',3.50,'1',7,'2018-10-24 15:42:57'),(9,'d2db4b74-fdb9-45b5-a475-65ac3b0dbdbc',1000.00,'1',7,'2018-10-24 15:43:10');

/*Table structure for table `room_type` */

DROP TABLE IF EXISTS `room_type`;

CREATE TABLE `room_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `room_type_name` varchar(20) DEFAULT NULL COMMENT '房间类型名',
  `room_price` float(10,2) DEFAULT NULL COMMENT '房间的单价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `room_type` */

insert  into `room_type`(`id`,`room_type_name`,`room_price`) values (1,'单人间',140.00),(2,'双人间',180.00),(3,'豪华间',280.00),(5,'总统套房',500.00),(6,'钟点房',100.00),(7,'情侣套房',599.00),(8,'单人间带窗户',200.00),(9,'双刃剑带窗户',249.00),(10,'总统房带窗户',999.00),(11,'闺蜜房',99.00);

/*Table structure for table `rooms` */

DROP TABLE IF EXISTS `rooms`;

CREATE TABLE `rooms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `room_num` varchar(10) DEFAULT NULL COMMENT '房间编号',
  `room_status` varchar(2) DEFAULT '0' COMMENT '房间的状态(0空闲，1已入住，2打扫)',
  `room_type_id` bigint(20) DEFAULT NULL COMMENT '房间类型主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `rooms` */

insert  into `rooms`(`id`,`room_num`,`room_status`,`room_type_id`) values (1,'8201','1',1),(2,'8202','2',1),(3,'8203','1',1),(4,'8204','1',2),(5,'82044','2',2),(6,'8205','1',3),(7,'8206','2',3),(8,'8207','0',2),(11,'8208','0',5),(12,'8209','2',3),(13,'8210','2',5);

/*Table structure for table `system_authority` */

DROP TABLE IF EXISTS `system_authority`;

CREATE TABLE `system_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `authority_name` varchar(20) DEFAULT NULL COMMENT '权限名',
  `authority_url` varchar(200) DEFAULT '#' COMMENT '权限跳转地址',
  `parent` bigint(20) DEFAULT '0' COMMENT '记住上级的主键，0为一级节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `system_authority` */

insert  into `system_authority`(`id`,`authority_name`,`authority_url`,`parent`) values (1,'入住管理','#',0),(2,'订单管理','#',0),(3,'会员管理','#',0),(4,'客房管理','#',0),(5,'用户管理','#',0),(6,'客人意见','#',0),(7,'入住信息查询','/getInRoomInfo.do',1),(8,'入住信息添加','/checkIn.do',1),(9,'消费记录','#',1),(10,'结账退房','/jieZhang.do',1),(11,'订单信息','/getAllOrders.do',2),(12,'订单添加','/toAddOrder.do',2),(13,'会员信息查询','/getAllVip.do',3),(14,'会员信息管理','#',3),(15,'添加会员','/pages/admin/vip/addvip.jsp',3),(16,'客房信息查询','/getHouseInfo.do',4),(17,'客房信息管理','/getHouseManage.do',4),(18,'添加客房','/pages/admin/room/addRoom.jsp',4),(19,'用户信息查询','/getUserInfo.do',5),(20,'用户信息管理','#',5),(21,'添加用户','/toAddUser.do',5),(22,'客人意见','#',6);

/*Table structure for table `system_user` */

DROP TABLE IF EXISTS `system_user`;

CREATE TABLE `system_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(40) DEFAULT NULL COMMENT '账号',
  `pwd` varchar(40) DEFAULT NULL COMMENT '密码',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `use_status` varchar(2) DEFAULT '1' COMMENT '启用状态：1启用，0禁用',
  `is_admin` varchar(2) DEFAULT '0' COMMENT '1超级管理员，0普通管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `system_user` */

insert  into `system_user`(`id`,`username`,`pwd`,`create_date`,`use_status`,`is_admin`) values (1,'admin','d5d23b080c52f070e3dc61821f325c7d','2018-09-20 14:20:19','1','1'),(2,'bigbird','d5d23b080c52f070e3dc61821f325c7d','2018-10-12 14:20:27','1','0'),(4,'zhangsan','d5d23b080c52f070e3dc61821f325c7d','2018-11-01 15:30:02','1','0'),(5,'lisi','d5d23b080c52f070e3dc61821f325c7d','2018-11-01 15:30:47','1','0');

/*Table structure for table `user_authority` */

DROP TABLE IF EXISTS `user_authority`;

CREATE TABLE `user_authority` (
  `user_id` bigint(20) DEFAULT NULL COMMENT '系统用户主键',
  `authority_id` bigint(20) DEFAULT NULL COMMENT '权限主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_authority` */

insert  into `user_authority`(`user_id`,`authority_id`) values (1,1),(1,7),(1,8),(1,9),(1,10),(1,2),(1,11),(1,12),(1,3),(1,13),(1,14),(1,15),(1,4),(1,16),(1,17),(1,18),(1,5),(1,19),(1,20),(1,21),(4,1),(4,7),(4,8),(4,9),(4,10),(5,1),(5,2),(5,3),(5,4),(5,7),(5,11),(5,13),(5,16);

/*Table structure for table `vip` */

DROP TABLE IF EXISTS `vip`;

CREATE TABLE `vip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vip_num` varchar(50) DEFAULT NULL COMMENT '会员卡编号',
  `customer_name` varchar(40) DEFAULT NULL COMMENT '会员姓名',
  `vip_rate` float(2,1) DEFAULT '0.9' COMMENT '1~9折',
  `idcard` varchar(20) DEFAULT NULL COMMENT '会员身份证',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `create_date` datetime DEFAULT NULL COMMENT '会员办理日期',
  `gender` varchar(2) DEFAULT '1' COMMENT '性别：1男 0女',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `vip` */

insert  into `vip`(`id`,`vip_num`,`customer_name`,`vip_rate`,`idcard`,`phone`,`create_date`,`gender`) values (1,'880888','王二麻子',0.9,'123456789123456781','15912345678','2018-10-25 10:23:47','1'),(2,'99999','丁鹏-bigbird',0.8,'123456789123456780','15912345111','2018-10-25 10:25:37','1'),(3,'39cb8619-0cb7-40eb-87cd-7bc4d03831ef','李四',1.0,'123456789123456799','15912345677','2018-10-26 14:32:31','1'),(4,'3a5cf0aa-756d-48cc-afa7-427c6fe52127','赵六',1.0,'123456789123456722','15912345677','2018-10-26 14:38:07','1'),(5,'8a676207-5be4-43a5-8a2f-81babf82413a','李二麻子',1.0,'123456789123456733','15912345677','2018-10-26 14:38:28','0'),(6,'4ac7b16b-1cff-4364-b4c2-41c2d1c4e992','刘备',1.0,'123456789123456766','15912345678','2018-10-26 14:38:42','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
