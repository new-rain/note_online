/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.31-log : Database - note
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`note` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `note`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `password` varchar(80) NOT NULL,
  `name` varchar(12) NOT NULL,
  `mobile` varchar(15) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `ir_num` int(5) DEFAULT '0',
  `ap_num` int(5) DEFAULT '0',
  `achievement` int(5) DEFAULT '0',
  `headUrl` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90000017 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`password`,`name`,`mobile`,`email`,`ir_num`,`ap_num`,`achievement`,`headUrl`) values (1,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','superadmin','18317819891','1551903505@qq.com',0,0,0,'admin_default.jpg'),(90000001,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','admin','15937055666','2504574888@qq.com',1,2,66,'zip-c83c4339-f8a1-4cc6-a730-d6092a1b08be.jpg'),(90000002,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','管理员','111','11@qq.com',2,1,56,'admin_default.jpg'),(90000003,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','管','111','11@qq.com',3,3,30,'admin_default.jpg'),(90000005,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','manager','123','123@qq.com',2,5,88,'admin_default.jpg'),(90000006,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','管','123','123@qq.com',0,0,0,'admin_default.jpg'),(90000007,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','管','123','123@qq.com',0,0,0,'admin_default.jpg'),(90000008,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','管','123','123@qq.com',0,0,0,'admin_default.jpg'),(90000009,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','管','123','123@qq.com',0,0,0,'admin_default.jpg'),(90000010,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','管','123','123@qq.com',0,0,0,'admin_default.jpg'),(90000011,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','管','123','123@qq.com',0,0,0,'admin_default.jpg'),(90000012,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','管','123','123@qq.com',0,0,0,'admin_default.jpg'),(90000013,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','管','123','123@qq.com',0,0,0,'admin_default.jpg'),(90000014,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','管','123','123@qq.com',0,0,0,'admin_default.jpg'),(90000015,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','管','123','123@qq.com',0,0,0,'admin_default.jpg'),(90000016,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','管理员一号','123','123@qq.com',0,0,0,'admin_default.jpg');

/*Table structure for table `appeal` */

DROP TABLE IF EXISTS `appeal`;

CREATE TABLE `appeal` (
  `a_no` int(8) NOT NULL AUTO_INCREMENT,
  `au_no` int(8) DEFAULT NULL,
  `an_no` int(8) NOT NULL,
  `a_body` varchar(200) DEFAULT NULL,
  `a_reason` varchar(10) NOT NULL,
  `a_type` bit(1) DEFAULT NULL,
  `ad_type` int(1) NOT NULL,
  `a_time` varchar(20) NOT NULL,
  `aa_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `a_state` bit(1) DEFAULT b'0',
  `a_id` int(8) DEFAULT NULL,
  PRIMARY KEY (`a_no`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `appeal` */

insert  into `appeal`(`a_no`,`au_no`,`an_no`,`a_body`,`a_reason`,`a_type`,`ad_type`,`a_time`,`aa_time`,`a_state`,`a_id`) values (1,10000001,1,'请求取消处罚','其他','',1,'2021-03-03 18:40:30','2021-04-15 09:20:33','',90000001),(2,10000001,1,'','其他','',1,'2021-03-14 10:37:26','2021-04-15 09:15:17','',90000002),(3,10000001,8,'申诉','政治相关','\0',1,'2021-03-19 14:16:30','2021-04-15 09:15:18','',90000003),(4,10000001,1,'申诉','政治相关','\0',1,'2021-04-14 16:15:25','2021-04-15 09:15:17','',90000001),(5,10000001,1,'申诉','含有敏感词','\0',1,'2021-04-14 16:15:36','2021-04-15 09:44:29','',90000001),(6,10000001,1,'申诉','含有敏感词','\0',1,'2021-04-15 09:27:48','2021-04-15 09:29:27','',90000001),(7,10000001,1,'申诉','含有敏感词',NULL,1,'2021-04-15 09:27:56','2021-04-15 09:27:56','\0',NULL),(8,10000001,1,'申诉','含有敏感词','',1,'2021-04-15 09:28:06','2021-04-15 09:29:31','',90000001),(9,10000001,1,'申诉测试','政治相关',NULL,1,'2021-04-15 09:28:15','2021-04-15 09:28:15','\0',NULL),(10,10000001,1,'申诉测试','政治相关',NULL,1,'2021-04-15 09:28:28','2021-04-15 09:28:28','\0',NULL),(11,10000001,31,'123132','3','',3,'2021-04-15 19:42:48','2021-04-15 20:14:35','',90000001),(12,10000001,31,'123132','3','',3,'2021-04-15 20:15:41','2021-04-15 20:15:54','',90000001),(13,10000001,29,'12123','undefined','',3,'2021-04-15 20:18:44','2021-04-15 20:19:03','',90000001),(14,10000001,29,'','undefined','',3,'2021-04-15 20:20:47','2021-04-15 20:22:13','',90000001),(15,10000001,29,'123','政治相关','',3,'2021-04-15 20:21:34','2021-04-15 20:22:10','',90000001);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `c_no` int(8) NOT NULL AUTO_INCREMENT,
  `c_content` varchar(200) NOT NULL,
  `c_to_nno` int(8) DEFAULT NULL,
  `u_no` int(8) NOT NULL,
  `u_name` varchar(15) NOT NULL,
  `to_no` int(8) DEFAULT NULL,
  `to_name` varchar(15) DEFAULT NULL,
  `c_type` int(1) NOT NULL,
  `c_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`c_no`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`c_no`,`c_content`,`c_to_nno`,`u_no`,`u_name`,`to_no`,`to_name`,`c_type`,`c_time`) values (1,'111',7,10000001,'旧雨',10000001,'旧雨',1,'2021-03-03 09:33:25'),(2,'评论测试',19,10000001,'旧雨',10000004,'测试账号注册',1,'2021-03-03 14:05:04'),(3,'评论笔记',1,10000001,'旧雨',10000001,'旧雨',1,'2021-03-03 16:51:17'),(4,'评论测试',1,10000001,'旧雨',10000001,'旧雨',1,'2021-03-03 16:56:50'),(5,'再次评论',4,10000001,'旧雨',10000001,'旧雨',2,'2021-03-03 16:56:59'),(6,'测试',8,10000004,'测试账号注册',10000001,'旧雨~~',1,'2021-03-03 17:29:32'),(7,'测试',4,10000004,'测试账号注册',10000001,'旧雨~~',1,'2021-03-03 17:29:56'),(8,'测试',2,10000004,'测试账号注册',10000001,'旧雨~~',1,'2021-03-03 17:30:11'),(9,'评论显示',1,10000001,'旧雨~~',10000001,'旧雨~~',1,'2021-03-19 13:53:48'),(10,'评论',1,10000001,'旧雨~~',10000001,'旧雨~~',1,'2021-03-19 14:03:12'),(11,'这是评论',1,10000001,'旧雨~~',10000001,'旧雨~~',1,'2021-03-19 14:09:56'),(12,'评论',32,10000001,'旧雨~~',10000001,'旧雨~~',1,'2021-03-19 14:11:53');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `f_no` int(8) NOT NULL AUTO_INCREMENT,
  `f_type` int(1) DEFAULT NULL,
  `f_body` varchar(200) NOT NULL,
  `fu_no` int(8) DEFAULT NULL,
  `f_time` varchar(20) DEFAULT NULL,
  `f_state` bit(1) DEFAULT b'0',
  PRIMARY KEY (`f_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `feedback` */

insert  into `feedback`(`f_no`,`f_type`,`f_body`,`fu_no`,`f_time`,`f_state`) values (1,2,'测试',10000004,'2021-03-03 18:46:07',''),(2,3,'测试',10000004,'2021-03-03 18:46:48',''),(3,2,'发聩',10000001,'2021-03-19 14:17:05','');

/*Table structure for table `irregularity` */

DROP TABLE IF EXISTS `irregularity`;

CREATE TABLE `irregularity` (
  `i_no` int(8) NOT NULL AUTO_INCREMENT,
  `i_type` int(1) NOT NULL,
  `i_body` varchar(200) DEFAULT NULL,
  `ir_no` int(8) NOT NULL,
  `is_no` int(8) NOT NULL,
  `in_no` int(8) NOT NULL,
  `in_name` varchar(15) NOT NULL,
  `i_state` bit(1) NOT NULL DEFAULT b'0',
  `i_time` varchar(20) DEFAULT NULL,
  `i_result` int(1) DEFAULT NULL,
  `ia_time` varchar(20) DEFAULT NULL,
  `a_id` int(8) DEFAULT NULL,
  PRIMARY KEY (`i_no`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `irregularity` */

insert  into `irregularity`(`i_no`,`i_type`,`i_body`,`ir_no`,`is_no`,`in_no`,`in_name`,`i_state`,`i_time`,`i_result`,`ia_time`,`a_id`) values (1,7,'举报测试',10000001,10000001,1,'1.java入门','','2021-03-03 16:52:13',1,'2021-03-03 18:39:03',90000001),(2,3,'测试',10000004,10000001,8,'1.Linux目录结构','','2021-03-03 17:21:57',1,'2021-03-03 18:38:53',90000001),(3,1,'',10000001,10000001,1,'1.java入门','','2021-03-14 10:35:45',1,'2021-04-14 16:12:35',90000001),(4,3,'举报演示',10000001,10000001,31,'演示新建','','2021-03-19 13:56:41',3,'2021-04-15 15:48:18',90000001),(5,4,'举报',10000001,10000001,1,'1.java入门','','2021-03-19 14:03:26',3,'2021-04-15 14:36:50',90000001),(6,3,'举报',10000001,10000001,1,'1.java入门','','2021-03-19 14:10:06',1,'2021-03-19 14:15:52',90000001),(7,3,'2222',10000001,10000001,29,'10.枚举','','2021-04-15 15:49:03',3,'2021-04-15 20:18:32',NULL);

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `m_no` int(8) NOT NULL AUTO_INCREMENT,
  `m_body` varchar(300) DEFAULT NULL,
  `m_to_who_no` int(8) NOT NULL,
  `m_who_to_no` int(8) DEFAULT NULL,
  `m_type` int(1) NOT NULL,
  `m_state` bit(1) DEFAULT b'0',
  `m_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `note_no` int(8) DEFAULT NULL,
  PRIMARY KEY (`m_no`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`m_no`,`m_body`,`m_to_who_no`,`m_who_to_no`,`m_type`,`m_state`,`m_time`,`note_no`) values (3,'',10000004,10000001,3,'','2021-03-03 16:39:49',NULL),(4,'',10000003,10000004,3,'\0','2021-03-03 10:52:12',NULL),(7,'',10000002,10000001,3,'\0','2021-03-03 13:40:59',NULL),(8,'',10000004,10000004,2,'','2021-03-03 16:39:49',NULL),(9,'',10000004,10000001,2,'','2021-03-03 16:39:49',NULL),(10,'评论测试',10000004,10000001,1,'','2021-03-03 16:39:48',NULL),(13,'给好友留言',10000004,10000001,0,'','2021-03-03 16:40:31',NULL),(35,'感谢您的举报，经审核，我们已对文章《1.Linux目录结构》做出责令修改的决定',10000004,NULL,4,'\0','2021-03-03 18:38:53',NULL),(36,'经审核，您的文章《8->1.Linux目录结构》由于政治相关，请您限时修改',10000001,NULL,5,'','2021-03-03 18:39:29',NULL),(37,'感谢您的举报，经审核，我们已对文章《1.java入门》做出责令修改的决定',10000001,NULL,4,'','2021-03-03 18:39:28',NULL),(38,'经审核，您的文章《1->1.java入门》由于其他，请您限时修改',10000001,NULL,5,'','2021-03-03 18:39:30',NULL),(39,'您申诉的关于《1.java入门》其他，经审核，申诉成功，原处理以取消',10000001,NULL,4,'','2021-03-03 18:41:17',NULL),(40,'发布信息测试',1,1,4,'\0','2021-03-03 18:41:56',NULL),(41,'发布信息测试',10016,1,4,'\0','2021-03-03 18:41:56',NULL),(42,'发布信息测试',10017,1,4,'\0','2021-03-03 18:41:56',NULL),(43,'发布信息测试',10018,1,4,'\0','2021-03-03 18:41:56',NULL),(44,'发布信息测试',10019,1,4,'\0','2021-03-03 18:41:56',NULL),(45,'发布信息测试',10020,1,4,'\0','2021-03-03 18:41:56',NULL),(46,'发布信息测试',10021,1,4,'\0','2021-03-03 18:41:56',NULL),(47,'发布信息测试',10022,1,4,'\0','2021-03-03 18:41:56',NULL),(48,'发布信息测试',10023,1,4,'\0','2021-03-03 18:41:56',NULL),(49,'发布信息测试',10024,1,4,'\0','2021-03-03 18:41:56',NULL),(50,'发布信息测试',10025,1,4,'\0','2021-03-03 18:41:56',NULL),(51,'发布信息测试',10026,1,4,'\0','2021-03-03 18:41:56',NULL),(52,'发布信息测试',10000001,1,4,'','2021-03-03 18:42:03',NULL),(53,'发布信息测试',10000002,1,4,'\0','2021-03-03 18:41:56',NULL),(54,'发布信息测试',10000003,1,4,'\0','2021-03-03 18:41:56',NULL),(55,'发布信息测试',10000004,1,4,'\0','2021-03-03 18:41:56',NULL),(56,'发布信息测试',10000005,1,4,'\0','2021-03-03 18:41:56',NULL),(57,'评论显示',10000001,10000001,1,'','2021-03-19 14:05:28',NULL),(58,'',10000001,10000001,2,'','2021-03-19 14:05:17',NULL),(59,'',10000001,10000001,2,'','2021-03-19 14:05:17',NULL),(60,'评论',10000001,10000001,1,'','2021-03-19 14:05:25',NULL),(61,'留言',10000004,10000001,0,'\0','2021-03-19 14:05:04',NULL),(62,'',10000001,10000001,2,'','2021-03-19 14:13:52',NULL),(63,'这是评论',10000001,10000001,1,'','2021-03-19 14:13:51',NULL),(64,'',10000001,10000001,2,'','2021-03-19 14:13:52',NULL),(65,'评论',10000001,10000001,1,'','2021-03-19 14:13:49',NULL),(66,'',10000005,10000001,3,'\0','2021-03-19 14:12:51',NULL),(67,'',10000005,10000001,3,'\0','2021-03-19 14:12:56',NULL),(68,'留言',10000004,10000001,0,'\0','2021-03-19 14:13:22',NULL),(69,'感谢您的举报，经审核，我们已对文章《1.java入门》做出责令修改的决定',10000001,NULL,4,'','2021-03-19 14:16:22',NULL),(70,'经审核，您的文章《1->1.java入门》由于政治相关，请您限时修改',10000001,NULL,5,'','2021-03-19 14:16:33',NULL),(71,'您申诉的关于《1.java入门》其他，经审核，申诉成功，原处理以取消',10000001,NULL,4,'','2021-04-14 16:15:31',NULL),(72,'感谢您的举报，经审核，我们已对文章《1.java入门》做出责令修改的决定',10000001,NULL,4,'','2021-04-14 16:15:28',NULL),(73,'经审核，您的文章《1->1.java入门》由于含有敏感词，请您限时修改',10000001,NULL,5,'','2021-04-14 16:15:30',NULL),(74,'您申诉的关于《1.java入门》政治相关，经审核，申诉成功，原处理以取消',10000001,NULL,4,'','2021-04-15 09:27:40',NULL),(75,'您申诉的关于《1.java入门》政治相关，经审核，申诉成功，原处理以取消',10000001,NULL,4,'','2021-04-15 09:27:39',NULL),(76,'您申诉的关于《1.java入门》含有敏感词，经审核，申诉成功，原处理以取消',10000001,NULL,4,'\0','2021-04-15 09:29:31',NULL),(77,'感谢您的举报，经审核，我们已对文章《1.java入门》做出封禁帐号的决定',10000001,NULL,4,'\0','2021-04-15 14:35:25',NULL),(78,'感谢您的举报，经审核，我们已对文章《1.java入门》做出封禁帐号的决定',10000001,NULL,4,'\0','2021-04-15 14:36:50',NULL),(79,'感谢您的举报，经审核，我们已对文章《演示新建》做出封禁帐号的决定',10000001,NULL,4,'\0','2021-04-15 15:35:40',NULL),(80,'感谢您的举报，经审核，我们已对文章《演示新建》做出封禁帐号的决定',10000001,NULL,4,'\0','2021-04-15 15:41:38',NULL),(81,'感谢您的举报，经审核，我们已对文章《演示新建》做出封禁帐号的决定',10000001,NULL,4,'\0','2021-04-15 15:42:04',NULL),(82,'感谢您的举报，经审核，我们已对文章《演示新建》做出封禁帐号的决定',10000001,NULL,4,'\0','2021-04-15 15:46:08',NULL),(83,'感谢您的举报，经审核，我们已对文章《演示新建》做出封禁帐号的决定',10000001,NULL,4,'\0','2021-04-15 15:48:18',NULL),(84,'您申诉的关于《演示新建》3，经审核，申诉成功，原处理以取消',10000001,NULL,4,'\0','2021-04-15 20:14:35',NULL),(85,'您申诉的关于《演示新建》3，经审核，申诉成功，原处理以取消',10000001,NULL,4,'\0','2021-04-15 20:15:54',NULL),(86,'感谢您的举报，经审核，我们已对文章《10.枚举》做出封禁帐号的决定',10000001,NULL,4,'\0','2021-04-15 20:18:32',NULL),(87,'您申诉的关于《10.枚举》undefined，经审核，申诉成功，原处理以取消',10000001,NULL,4,'\0','2021-04-15 20:19:03',NULL),(88,'您申诉的关于《10.枚举》政治相关，经审核，申诉成功，原处理以取消',10000001,NULL,4,'\0','2021-04-15 20:22:10',NULL),(89,'您申诉的关于《10.枚举》undefined，经审核，申诉成功，原处理以取消',10000001,NULL,4,'\0','2021-04-15 20:22:13',NULL);

/*Table structure for table `note` */

DROP TABLE IF EXISTS `note`;

CREATE TABLE `note` (
  `n_no` int(8) NOT NULL AUTO_INCREMENT,
  `n_name` varchar(15) NOT NULL,
  `n_body_url` varchar(60) DEFAULT 'src/main/resources/static/articles/default_arthicle.md',
  `n_read_num` int(8) DEFAULT '0',
  `n_good_num` int(7) DEFAULT '0',
  `n_comment_num` int(6) DEFAULT '0',
  `nb_no` int(8) NOT NULL,
  `u_no` int(8) NOT NULL,
  `note_state` bit(1) DEFAULT b'1',
  `n_collection_num` int(7) DEFAULT '0',
  `create_time` varchar(10) NOT NULL,
  PRIMARY KEY (`n_no`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `note` */

insert  into `note`(`n_no`,`n_name`,`n_body_url`,`n_read_num`,`n_good_num`,`n_comment_num`,`nb_no`,`u_no`,`note_state`,`n_collection_num`,`create_time`) values (1,'1.java入门','/1.md',108,1,5,3,10000001,'',1,'2021-03-01'),(2,'2.JAVA语法','/2.md',29,1,1,3,10000001,'',0,'2021-03-01'),(3,'3.流程控制','/3.md',6,1,0,3,10000001,'',0,'2021-03-01'),(4,'4.方法','/4.md',7,1,2,3,10000001,'',0,'2021-03-01'),(5,'5.数组','/5.md',1,0,0,3,10000001,'\0',0,'2021-03-01'),(6,'5.数组','/6.md',2,0,0,3,10000001,'',0,'2021-03-03'),(7,'6.面向对象（修改）','/7.md',4,1,1,3,10000001,'',1,'2021-03-03'),(8,'1.Linux目录结构','/8.md',12,1,1,5,10000001,'',1,'2021-03-03'),(9,'2.Linux指令','/9.md',4,0,0,5,10000001,'',0,'2021-03-03'),(10,'3.远程登录Linux 说明','/10.md',14,0,0,5,10000001,'',0,'2021-03-03'),(11,'1.数据结构分类','/11.md',1,0,0,2,10000001,'',0,'2021-03-03'),(12,'2.稀疏数组','/12.md',1,0,0,2,10000001,'',0,'2021-03-03'),(13,'3.队列','/13.md',1,0,0,2,10000001,'',0,'2021-03-03'),(14,'1.回溯算法','/14.md',3,0,0,8,10000004,'',0,'2021-03-03'),(15,'1.冒泡排序','/15.md',1,0,0,8,10000004,'',0,'2021-03-03'),(16,'2.快速排序','/16.md',1,0,0,8,10000004,'',0,'2021-03-03'),(17,'1.简单选择排序','/17.md',1,0,0,8,10000004,'',0,'2021-03-03'),(18,'2.堆排序','/18.md',2,0,0,8,10000004,'',0,'2021-03-03'),(19,'1.变量','/19.md',10,2,1,9,10000004,'',1,'2021-03-03'),(20,'1.快捷键','/20.md',1,0,0,10,10000004,'',0,'2021-03-03'),(21,'1.Docker入门','/21.md',1,0,0,11,10000004,'',0,'2021-03-03'),(22,'6.Hello World','/22.md',5,0,0,3,10000001,'\0',0,'2021-03-03'),(23,'7.新建测试','/23.md',1,0,0,3,10000001,'\0',0,'2021-03-03'),(24,'测试','/24.md',1,0,0,13,10000004,'',0,'2021-03-03'),(25,'测试','/25.md',1,0,0,13,10000004,'',0,'2021-03-03'),(26,'敏感词屏蔽','/26.md',1,0,0,13,10000004,'',0,'2021-03-03'),(27,'敏感词屏蔽','/27.md',1,0,0,13,10000004,'',0,'2021-03-03'),(28,'测试','/28.md',6,0,0,3,10000001,'\0',0,'2021-03-04'),(29,'10.枚举','/29.md',28,0,0,3,10000001,'',0,'2021-03-04'),(30,'测试','/30---测试.md',1,0,0,3,10000001,'\0',0,'2021-03-06'),(31,'演示新建','/31---演示新建.md',18,1,0,3,10000001,'',1,'2021-03-19'),(32,'新建','/32---新建.md',2,1,1,16,10000001,'',1,'2021-03-19'),(33,'新建','/33---新建.md',1,0,0,16,10000001,'',0,'2021-03-19'),(34,'新建','/34---新建.md',1,0,0,16,10000001,'',0,'2021-03-19'),(35,'测试配置','/35---测试配置.md',6,0,0,3,10000001,'\0',0,'2021-04-08'),(36,'测试配置2','/36---测试配置2.md',1,0,0,3,10000001,'\0',0,'2021-04-08'),(37,'test','/37---test.md',1,0,0,3,10000001,'\0',0,'2021-04-08'),(38,'x','/38---x.md',1,0,0,3,10000001,'\0',0,'2021-04-08'),(39,'g','/39---g.md',1,0,0,3,10000001,'\0',0,'2021-04-08'),(40,'df','\\\\40---df.md ',1,0,0,3,10000001,'\0',0,'2021-04-08'),(41,'hashMap','\\\\41---11.md ',4,0,0,3,10000001,'',0,'2021-04-08');

/*Table structure for table `notebook` */

DROP TABLE IF EXISTS `notebook`;

CREATE TABLE `notebook` (
  `b_no` int(8) NOT NULL AUTO_INCREMENT,
  `b_name` varchar(20) NOT NULL,
  `b_describe` varchar(300) DEFAULT '这个小懒货还没有写描述',
  `b_type` varchar(100) DEFAULT NULL,
  `b_power` int(1) DEFAULT '1',
  `b_create_time` varchar(20) NOT NULL,
  `b_update_time` varchar(20) NOT NULL,
  `u_no` int(8) NOT NULL,
  `notebook_state` bit(1) DEFAULT b'1',
  `cover_url` varchar(80) DEFAULT 'http://localhost:8888/img/notebook-img/default-notebook-img.png',
  PRIMARY KEY (`b_no`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `notebook` */

insert  into `notebook`(`b_no`,`b_name`,`b_describe`,`b_type`,`b_power`,`b_create_time`,`b_update_time`,`u_no`,`notebook_state`,`cover_url`) values (1,'测试','这个小懒货还没有写描述',NULL,1,'2020-05-01 01:17:35','2021-03-01 01:17:35',10000001,'','http://localhost:8888/img/notebook-img/default-notebook-img.png'),(2,'数据结构','数据结构学习笔记','-数据结构-',1,'2021-02-01 02:17:35','2021-03-01 02:17:35',10000001,'','http://localhost:8888/img/notebook-img/default-notebook-img.png'),(3,'JAVA学习笔记','JAVA学习笔记，这是学习笔记','-java--java学习笔记-',1,'2019-03-02 03:17:35','2021-03-01 03:17:35',10000001,'','http://localhost:8888/img/notebook-img/notebook-img-2.png'),(4,'C语言','C语言学习笔记','-C-',1,'2021-02-05 04:17:35','2021-03-01 04:17:35',10000001,'','http://localhost:8888/img/notebook-img/notebook-img-2.png'),(5,'Linux','Linux学习笔记','-linux--学习笔记--服务器-',1,'2021-03-01 05:17:35','2021-03-01 05:17:35',10000001,'','http://localhost:8888/img/notebook-img/notebook-img-1.png'),(7,'VUE','VUE学习笔记','-vue--js--前端-',1,'2021-03-01 07:17:35','2021-03-01 06:17:35',10000001,'','http://localhost:8888/img/notebook-img/default-notebook-img.png'),(8,'算法','算法学习笔记','-算法-',1,'2021-03-03 10:03:27','2021-03-03 10:03:27',10000004,'','http://localhost:8888/img/notebook-img/default-notebook-img.png'),(9,'Python','Python学习笔记','-Python--人工智能-',1,'2021-03-03 13:54:23','2021-03-03 13:54:23',10000004,'','http://localhost:8888/img/notebook-img/notebook-img-1.png'),(10,'Typora','Typora学习笔记','-MarkDown--Typora-',1,'2021-03-03 14:07:02','2021-03-03 14:07:02',10000004,'','http://localhost:8888/img/notebook-img/notebook-img-4.png'),(11,'Docker','Docker学习笔记','-Docker--容器-',1,'2021-03-03 14:09:09','2021-03-03 14:09:09',10000004,'','http://localhost:8888/img/notebook-img/notebook-img-2.png'),(12,'测试','测试','-测试-',1,'2021-03-03 18:23:44','2021-03-03 18:23:44',10000004,'','http://localhost:8888/img/notebook-img/default-notebook-img.png'),(13,'软件测试','软件测试','-软件测试-',1,'2021-03-03 18:24:29','2021-03-03 18:24:29',10000004,'','http://localhost:8888/img/notebook-img/default-notebook-img.png'),(14,'演示修改','新建演示','-演示--测试-',1,'2021-03-19 13:57:45','2021-03-19 13:57:45',10000001,'','http://localhost:8888/img/notebook-img/notebook-img-3.png'),(16,'新建笔记本','演示','-java--演示-',1,'2021-03-19 14:10:55','2021-03-19 14:10:55',10000001,'','http://localhost:8888/img/notebook-img/notebook-img-2.png');

/*Table structure for table `registernum` */

DROP TABLE IF EXISTS `registernum`;

CREATE TABLE `registernum` (
  `rnum` varchar(50) NOT NULL,
  `admin` int(8) DEFAULT NULL,
  PRIMARY KEY (`rnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `registernum` */

insert  into `registernum`(`rnum`,`admin`) values ('0J89w1EtpOcNx6GaQSR3v8BDBnyr59L7f9E95BfRbWB6ycqE0B',90000016),('0sA6yL5I94nT66z5xyjXKt5UdkgFQ4r307f4OMYLx3vzve0040',-1),('14aq3gLFRI91fZApEb06yDz7L1ObGxBxKZ2JC95dEUQ738lxZ9',-1),('24psm99wfMaP3Mv7mT1HX2DrElrMaA857S51ekrA8Rdoby0e13',90000001),('299EpaIQr4p6V3ZvM1opPA4HAL40Mv436o1DkY3qg3D044X5oL',90000002),('3dim9T0lj46Zmm9JHI39QQ33pUs2qJ1a4aWpjrA4ZK36ZPDakW',-1),('3Kb8P4jtrCAhLx85QxMl37fln2W26uR79lymKCk4fN9ONpqHgq',90000003),('4BeFFmGuhOxzxLCZJiFXw18xBEBotqPs6sD1ShvRgRNKJT2Dl6',-1),('4CZliXMr39tYK3yWeryNv8RH7yCOZiJDJLnSigOk562JU24LY3',90000004),('4Nc0baBJf16r6K0OtjW489wUgM35vxW0LGhlqH2EPt732gNrCJ',-1),('4OP2MD2yJjPLSuTWkHFTbQXkUY9ZrUqU9MzcTNw4wUOqAmXE6P',90000005),('4uLBZcVnNxgKHYH0sCNUxfSALEiN56vgPe69Jh05iIi0Maaq90',-1),('66IHmmsba4KqLELDV0xC9TVV6xjLZJFeCvxP8iVzyZYo98x7ty',-1),('691t3ZsQmd9ca9gpynd5ock33O9y2939wV62LT84VKQkZ9Dko6',-1),('6hbEccK08k2lZiSSDL1dJ6J7453fHWu3Ak5N1z8yWb98sWTexh',-1),('6KAzNP099BrcD9Nt9BH0ja4wf0w8Il6ZrVUjgv5548InUZlwCo',-1),('7FFzDxxnAF60urxVdQ63ZqsKP52lTGO72o3RnC5N7OGC7udpGU',-1),('7n4qvNFxP9mp8l0r2ceeoiJ8pWuF51a70Qd4k031x9RxGX5pZ9',-1),('7Nd2ILYwM92Q5Xc3tSjV89gsLLq7zIDPgFM4zoDY7uwbqkzuEh',-1),('87v3bIigx0zCdz492w49aBb4U8Qz3vF5CA1guOGlsj2albJib5',-1),('8WDWfnovozobHV4ncfahujEqYgHh32ave2V4x17cO4M4l7TVa3',-1),('a1wwUj94iU2SY3K9954jI8Yhj55nqST28g54DJ7f4HdN36GJKH',-1),('Afivrn9yMfMERp4AdQLbT1STlkn3uj9wvRWFmB1MZDQVIQkWAu',-1),('B5JIa2fBg6T0955MfF13KPR5sKxDg893kZ85kcU9x4dG2e24R0',-1),('B5pWGLd3Nb2KliQy001X1vv1d89693jK3hYG4E56YybxNHdj1F',-1),('BinT0EZ39iAzvEWpK41Z5h4y440ZOhvTe6FQJy9iNNubTs7I5S',-1),('Bl6wYiXV8lXDLQtVsq93EzSka2JEA9LISah3GNY1MD02jf35uB',-1),('C2teRNNSx6LemEjltuvpltqahn5uLjvnM18rgWNSbkb6cbd9zQ',-1),('C3jBMz4rQXqSL1n1X09LPheQ6K4FTslS78JxmvT2HnHw3XUtbU',-1),('C9J37P8gE1G98y1GKK4Y1JEWGF0JY12Dn27206ZX2y9oAp37y1',-1),('cgmi6ZNfRrv1dFnIu1Hqm0EOgpGY41w0As1I6XNPkDFa7Rpv4J',-1),('cRby6AmAxiFq9TXVnJ9CQ5DaUTdPrfcarGFduJjX9VjGM3rygP',-1),('D1vfoYAESOqZqSzrr93ruUiT5L9na3p8S8JvZkvfjBXmc2t3vK',-1),('drAZ3x7odc1uf26Rf5603fBh45YCuM9FhhoiWAHINk2wiRh39I',-1),('erhp9NPdhyQB5Cwl2pAppLXyLD4aoDd9NMcZodgD0DVSh6MEcN',-1),('FftX2CW7744199BX650M1TGJ3gex28QNH2Fc93wtk18N9w2oKs',-1),('FypLitNeiQ1KIp1PFAy28FBz82NVpvQk1z7F1kcsJR57dXUbIF',-1),('GvNyGoeJifoVO5qEAdP6KIh1CwvjmKc49ZgLMQhUZQXxBwz490',-1),('hU4ZqF9Jj49cT4euY5wf3an7e76X4CqUGjsAn9YR4Zv9zuj5fH',-1),('iiW4xNLzr7f9dBrJrTGs53k5sg7s9EfVHW4zZbnyOaQxSYqJHT',-1),('ILdnVzv853W2Fv69atff4dcdz7H0K35XVHlnpXP50w0N3mk8S1',-1),('jCntCKM38Nx9JtZYkVfJG1Kq53Wd7fWW03NCj86Ao1zOAfEqY4',-1),('JrON5VU6GTrSDbRv8MT7g6F9OJhyHxBr3VN14DEfLewNi1yy1O',-1),('KEO4y1RcPlQCfadA7xn3tt230zp4T512toXWEDAqA8Tzt8ZfC8',-1),('KO8hc1gzDRQ09jCynznduqGbpZ90K416QaPzgZgK568XB9HTQr',-1),('kuMD28okroDlp40YbAmS8VgAOtnkc2T2iaYOuY2mjvAdcHLQpF',-1),('LG8gqaC4HzDOU29kVgWwK23ewgZkb3YxXcf71Dk2FzR7dilP5n',-1),('lgKV38vwj5vvAVkMAh6BZuqtQn08fbR9rro2c13L6FYbKWfxkB',-1),('LkLd3VFuitB81z8Ud9c9FtEv3O4cNlxS3xi3mOdI4Lnz8ho80n',-1),('LPZg0E5Ols7eXBkemX2LtXhDb7Sp4w434ELttmd2NA50VJsBJL',-1),('LtGAS6Ektxa0dQu673lmr94BKrJiaF234r1HrgAtCD7TvmG2uO',-1),('Map2c8wQ845kFDc5yEcDtR57X8Yjs9l6lLETHBRxqof0k9PxC6',-1),('MLDy0DO2Tk1Sd7v4ZUM32A57bzT9r8chUWWQTdWZ7cpcgiIOML',-1),('MtABG337zJeqHAzUW51WV77st6sue7tQcW3u5aJW2e0Rj4fur5',-1),('N0WuerN4n9w2UVcd0g4x1Q938H67Vi68Xv5WvW3QZEOM6eIHxv',-1),('N2BuD596XvY0NL13wcEdXY9XtwL9DIJVXXK26vQx4Xb5Qyf3HF',-1),('N2K1T3kO59pN5LDVkXhjv3y9367zZhMynrnUc1IPA8hN8eW8zG',-1),('n3aRPcIp2TwtFxtRlWfEt8HGpGojj5se0NABQ6PS399xoqPQXU',-1),('o683Spd6ukSjHg70Fzv5s0Af7iXA3Q1Nsbry7ngy472R94eQl6',-1),('O7owoWBr1fnfR4Pot3pbDOkKczQZ270qAHa9A9p70C51HicHmT',-1),('oO823JP0mBYF5zS81G5PtLuxpyx0RrbZTYvwZV5x0FsG9BXr7X',-1),('oq56k86VlBjIRcYV0Xdqr3w3SsGpK1MJ9wf6hSu46dL8M5V95g',-1),('orHS3QkqI9fQiMew9Ir2dWH6S8HG5GVY3Yi6dD0kd327Kac9IN',-1),('oU4fs3CI0s87k5mKljwICQ3Igb5hQTqk2FxX8EEi8hwm96Vcyg',-1),('p8hOJnzn50eq4U97XPlmPT5DNy5uJ2qANtyoWsaaWNllIIoGGu',-1),('pNz2rptnDDiC6010fcpQQPcTevs1MxEtWx8JB5t0ZG74eEw2fM',-1),('PQTog84Rt6ov5Uh43KkqcCz057Zp6jO2fM8qAMFzFgI0xL8klr',-1),('PZoO23RHfkGa9rpvzfhaUb3jlhbh1SwsJ24y5C8q5iaXG3eL0f',-1),('q5Og1ZLBjoEufXum5il8hWxcrjrF6I0u2n2OL30D9qtK9Znmry',-1),('q9dRVDNyC0M7b5VGnHwANqj98ULFChDv1Hw37XhMCSK9VD9Tsl',-1),('QLwXqEpp90FchyvFYZkkVwX0Yumk52zu4V4Ag2h5f5L8DAwppn',-1),('RIijTnStYJbE48588X8Zf5wDo7z0jMHdR4LFBeZ8PzEbzv5vqk',-1),('RQ14wpgw9j5P1JiYJd85mls58V28hbOeVkm3S9ccTkkQOb97ZX',-1),('rrRZOl63OEZAWM0DPw9C3xP0cs1fiCnHG1l1HHpIWWiyr8Vl6j',-1),('sAPu6t53UXn7kzCBg70114rPU2IbJi2BfgV3977SH91me5u9IS',-1),('sfg3VpZ52tPbKSiXC52z01Af8uFqNpjEw2iA3aOm3elHSfeai1',-1),('smjf4q9ZQY2aC0Kbw12xkhPEar54rss1nX5xmA5kt08VbmLV1b',-1),('SrRKAgc19ix6Bq78glMsU6TUHKM5uP4iJZUf4EpdRj5WWAAu0Y',-1),('SW6lCFB41bz50Lb7q3YI2AiKMVO7uf0ASoTx4IfW2C5MJoK1xT',-1),('t7Q2Dqvn0kw5U80Aiw3wqMg44Lci6nayCq46NVSh6S87Nc7uoB',-1),('Ta8AfHz83A8R93mgxLN40J6Jlr2colVGIl53030e6W4It8g29P',-1),('TduMtCwC4A2uUoZpyw3DSlSIDz0kCA70Q5AUW7Hj56baJvtj3r',-1),('tE5sN1zv0ax43zDB9WltX0L5LSmepwYyQl5vj9Pw4S6PBdRp2z',-1),('uCH0a339bVL2S42Mmy2ywTAUCi3m6c8f6wIPA1Ns47ZgRRLgDp',-1),('ugzkngVcfY4m5AIvrb8OE6PcLUXQuRvbfFatHKg07u0gJ709iR',-1),('V5Jq9S63UQ2QPRrgmFElowDamIW0oMHAaa2H8P6T0wLzHGQ2AZ',-1),('vdDODX6L9U1LTn311ELVDQUA7QcRMqJkdRNq1Bbk4QGkwa2xpw',-1),('Vk2gxIrewNK83F88zQ7Yb8vGVsd16dxpTMUEbl6MfVmOstxU1l',-1),('W1gdrJa81CT1QvvRTe4KA53wlv4b49g2BPnAlsxGMb1hwq85zl',-1),('WA7C0s5f7yuh5ALE7g2MYao5KRBvi4nO7M27hpcwzZMvpgpo5g',-1),('wQ25Gt9ztbCOa2uDRDyq403tVSvvaXSGxpsqnU07cviM7a40qf',-1),('WqRG3ZL7F861r9n17ZAIrizr7avZB82gI74Au0y75qFG76Li3X',-1),('xMLfsW328FGM1FvZofM89ITgUgw698lONIq21fi1G9MpTqnTWZ',-1),('xn1hO1cKZx1DnWOPLOuscD8zbT9xAEfjXwJWkFvtylHEhOi44S',-1),('XVo8oW0aVQVob0LPMU3cbnoq4K8ZOWITmkhh9Kvc4i8cqPjL5P',-1),('YEkSb2oHfe4Sitk7ETvN2oW4JSgNasBizcyiY87woL5VhxgkjM',-1),('yp4gFGc3S3Zk99KQ5ok3JaMXLKFJ2tl8Yn92YbE6EOM4HmL2tm',-1),('YR48u8kz0oye772t1bZ4eIE1iSVdKUFa0YVnEaNp2xW552aeIr',-1),('Yrsj2T20YiVOGfeAZmRy1YVCrxspX5x0trCa27EgFAwR96GvLG',-1),('zpVk0l9MFq03u649KUovEp4RbFtEPpMEH0h7s7D5bcI4K9DMOW',-1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `no` int(8) NOT NULL AUTO_INCREMENT,
  `password` varchar(80) DEFAULT NULL,
  `nickname` varchar(15) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `brief` varchar(300) DEFAULT '这家伙太懒了，还没写简介！',
  `create_time` varchar(20) DEFAULT NULL,
  `name` varchar(15) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gender` varchar(1) DEFAULT '1',
  `job` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `now_address` varchar(50) DEFAULT NULL,
  `headUrl` varchar(60) DEFAULT 'http://localhost:8888/img/user_head/default_head_img.jpg',
  `state` bit(1) DEFAULT b'1',
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=10000008 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`no`,`password`,`nickname`,`email`,`brief`,`create_time`,`name`,`birthday`,`gender`,`job`,`address`,`now_address`,`headUrl`,`state`) values (1,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','系统',NULL,'这家伙太懒了，还没写简介！','2021-02-23 20:08:48','测试',NULL,'1',NULL,'河南省',NULL,'zip-10000001-S00614-20234375(1).png',''),(10016,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','测试账号','1234567891@qq.com','测试账号','2021-02-24 14:58:31','测试',NULL,'1',NULL,'河北省',NULL,'zip-10000001-S00614-20234375(1).png',''),(10017,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','测试账号','1598746225@qq.com','测试账号','2021-02-25 14:58:31','测试',NULL,'1',NULL,'湖南省',NULL,'zip-10000001-S00614-20234375(1).png',''),(10018,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','测试账号','1257546596@qq.com','测试账号','2021-02-25 14:58:31','测试',NULL,'1',NULL,'浙江省',NULL,'zip-10000001-S00614-20234375(1).png',''),(10019,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','测试账号','5465465465@qq.com','测试账号','2021-02-26 14:58:31','测试',NULL,'1',NULL,'江苏省',NULL,'zip-10000001-S00614-20234375(1).png',''),(10020,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','测试账号','7897654613@qq.com','测试账号','2021-02-26 14:58:31','测试',NULL,'1',NULL,'北京',NULL,'zip-10000001-S00614-20234375(1).png',''),(10021,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','测试账号','48462515625@qq.com','测试账号','2021-02-26 14:58:31','测试',NULL,'1',NULL,'北京',NULL,'zip-10000001-S00614-20234375(1).png',''),(10022,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','测试账号','13245678945@qq.com','测试账号','2021-02-27 14:58:31','测试',NULL,'1',NULL,'上海',NULL,'zip-10000001-S00614-20234375(1).png',''),(10023,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','测试账号','77445588845@qq.com','测试账号','2021-02-28 14:58:31','测试',NULL,'1',NULL,'上海',NULL,'zip-10000001-S00614-20234375(1).png',''),(10024,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','测试账号','32265656565@qq.com','测试账号','2021-02-28 14:58:31','测试',NULL,'1',NULL,'广东省',NULL,'zip-10000001-S00614-20234375(1).png',''),(10025,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','新城','45454485151@qq.com','这家伙太懒了，还没写简介！','2021-03-01 14:58:31','测试',NULL,'1',NULL,'广东省',NULL,'zip-10000001-S00614-20234375(1).png',''),(10026,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','九城','5995656565@qq.com','这家伙太懒了，还没写简介！','2021-03-01 14:58:31','测试',NULL,'1',NULL,'河南省',NULL,'zip-10000001-S00614-20234375(1).png',''),(10000001,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','旧雨~~','1551903506@qq.com','一个努力学习的小渣渣，小渣渣','2021-03-01 14:55:21','靳新宇','1998-08-29','1','学生','河南省开封市','河南省开封市','zip-10000001-logo.png',''),(10000002,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','宇','2504574877@qq.com','一个努力学习的小渣渣','2021-03-01 14:55:21','靳','1998-08-29','1','学生','河南省开封市','河南省商丘市','zip-10000003.png',''),(10000003,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','新雨','3418135781@qq.com','一个努力学习的小渣渣','2021-03-01 14:55:21','靳','1998-08-29','1','学生','湖南省','河南省商丘市','zip-10000001-S00614-20234375(1).png',''),(10000004,'5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5','测试账号注册','2504574878@qq.com','这家伙太懒了，还没写简介！','2021-03-01 14:55:21',NULL,NULL,'1',NULL,NULL,NULL,'zip-10000001-s.png',''),(10000005,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','测试','1551903505@qq.com','这家伙太懒了，还没写简介！','2021-03-03 18:36:58',NULL,NULL,'1',NULL,NULL,NULL,'zip-10000002-a.png',''),(10000006,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','zs','3418135782@qq.com','这家伙太懒了，还没写简介！','2021-04-07 16:43:27',NULL,NULL,'1',NULL,NULL,NULL,'zip-10000001-S00614-20234375(1).png',''),(10000007,'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','123','3418135783@qq.com','这家伙太懒了，还没写简介！','2021-04-07 16:49:54',NULL,NULL,'1',NULL,NULL,NULL,'zip-10000001-S00614-20234375(1).png','');

/*Table structure for table `user_collection` */

DROP TABLE IF EXISTS `user_collection`;

CREATE TABLE `user_collection` (
  `uc_no` int(8) NOT NULL AUTO_INCREMENT,
  `uc_user_no` int(8) NOT NULL,
  `uc_n_no` int(8) NOT NULL,
  `uc_type` bit(1) DEFAULT NULL,
  PRIMARY KEY (`uc_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_collection` */

/*Table structure for table `user_note` */

DROP TABLE IF EXISTS `user_note`;

CREATE TABLE `user_note` (
  `un_no` int(8) NOT NULL AUTO_INCREMENT,
  `user_no` int(8) NOT NULL,
  `note_no` int(8) NOT NULL,
  `is_good` bit(1) DEFAULT b'0',
  `is_collection` bit(1) DEFAULT b'0',
  `un_type` bit(1) NOT NULL,
  PRIMARY KEY (`un_no`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `user_note` */

insert  into `user_note`(`un_no`,`user_no`,`note_no`,`is_good`,`is_collection`,`un_type`) values (1,10000001,7,'','','\0'),(2,10000001,8,'\0','',''),(3,10000004,19,'','\0','\0'),(4,10000001,19,'','','\0'),(5,10000004,7,'\0','',''),(6,10000004,4,'','',''),(7,10000004,2,'','',''),(8,10000004,3,'','',''),(9,10000004,5,'\0','',''),(10,10000004,8,'','','\0'),(11,10000001,9,'\0','',''),(12,10000001,1,'','','\0'),(13,10000001,31,'','','\0'),(14,10000001,32,'','','\0');

/*Table structure for table `user_relationship` */

DROP TABLE IF EXISTS `user_relationship`;

CREATE TABLE `user_relationship` (
  `r_no` int(8) NOT NULL AUTO_INCREMENT,
  `a_no` int(8) NOT NULL,
  `b_no` int(8) NOT NULL,
  `r_relationship` int(1) NOT NULL,
  PRIMARY KEY (`r_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `user_relationship` */

insert  into `user_relationship`(`r_no`,`a_no`,`b_no`,`r_relationship`) values (2,10000004,10000001,1),(3,10000001,10000002,2),(5,10000003,10000001,2),(6,10000002,10000004,1),(8,10000001,10000005,2);

/*Table structure for table `visitnum` */

DROP TABLE IF EXISTS `visitnum`;

CREATE TABLE `visitnum` (
  `v_date` varchar(12) NOT NULL,
  `v_num` int(8) DEFAULT NULL,
  PRIMARY KEY (`v_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `visitnum` */

insert  into `visitnum`(`v_date`,`v_num`) values ('2021-02-02',0),('2021-02-03',5),('2021-02-04',2),('2021-02-05',1),('2021-02-06',7),('2021-02-07',5),('2021-02-08',3),('2021-02-09',2),('2021-02-10',18),('2021-02-14',1),('2021-02-21',3),('2021-02-22',67),('2021-02-23',10),('2021-02-24',2),('2021-02-25',1),('2021-02-26',5),('2021-02-28',6),('2021-03-01',61),('2021-03-03',69),('2021-03-04',16),('2021-03-06',1),('2021-03-07',1),('2021-03-13',13),('2021-03-14',5),('2021-03-19',16),('2021-03-24',1),('2021-03-26',2),('2021-03-28',3),('2021-03-29',4),('2021-03-30',27),('2021-03-31',1),('2021-04-07',9),('2021-04-08',22),('2021-04-09',2),('2021-04-13',14),('2021-04-14',9),('2021-04-15',90);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
