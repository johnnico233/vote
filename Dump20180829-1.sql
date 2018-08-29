-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: vote
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dustbin`
--

DROP TABLE IF EXISTS `dustbin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dustbin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ban_id` int(11) DEFAULT NULL,
  `ban_type` int(11) DEFAULT NULL,
  `ban_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8 COMMENT='ban_type : 1 - user  ,  2 - topic , 3 - message';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dustbin`
--

LOCK TABLES `dustbin` WRITE;
/*!40000 ALTER TABLE `dustbin` DISABLE KEYS */;
INSERT INTO `dustbin` VALUES (72,25,3,'2018-08-01 14:49:46'),(73,1,2,'2018-08-02 11:22:05'),(74,7,2,'2018-08-02 11:22:39'),(75,8,2,'2018-08-02 11:22:42');
/*!40000 ALTER TABLE `dustbin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(16) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  `user_type_id` int(11) DEFAULT NULL,
  `loginable` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'yosoro','monstar10',1,1),(2,'yosoro1','monstar10',1,1),(5,'yosoro233','monstar10',2,1),(6,'yosoro2334','monstar10',2,1),(7,'yosoki','monstar10',2,1),(8,'yosoki123','monstar10',2,1),(9,'yosoki555','monstar10',2,1),(10,'yosoki12','monstar10',2,1),(11,'nicopoi3','monstar10',2,1),(12,'nicopoi','monstar10',2,1),(13,'TestAccount','monstar10',2,1),(14,'Test11','monstar10',2,1),(15,'IamTest','12345678',2,1),(16,'IanNew','monstar10',2,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(16) DEFAULT NULL,
  `user_sex` varchar(6) DEFAULT NULL,
  `user_phone` varchar(13) DEFAULT NULL,
  `user_email` varchar(32) DEFAULT NULL,
  `user_whatsup` varchar(64) DEFAULT 'this guy is lazy.there is nothing to show',
  `create_time` datetime DEFAULT NULL,
  `birth` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `user_avatar` varchar(45) DEFAULT 'default.jpg',
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'Zzzaa','male','12345678910','1011206880@qq.com','nothing to show','2018-07-18 00:00:00','2018-07-11 00:00:00','2018-08-07 17:11:56','15330380069061.jpg'),(2,'yohane','female','1234567891','101120680@qq.com','nothing to show','2018-07-18 00:00:00','2000-01-01 00:00:00','2018-07-20 09:05:30','15326642584572.jpg'),(5,'Little Mark','male','12345678923','10112113@qq.com','there is nothing shows','2018-07-18 21:12:02','1997-07-11 00:00:00','2018-07-31 12:00:33','15327818383305.png'),(6,'渡边曜','female','123321123324','10112@qq.com','this guy is lazy.there is nothing to show','2018-07-18 21:13:54','2000-01-01 00:00:00','2018-07-20 19:05:30','default.jpg'),(7,'测试账户1','female','12332112311','john@outlook.com','this guy is lazy.there is nothing to show','2018-07-25 20:41:39','1969-01-31 00:00:00','2018-08-02 15:00:16','default.jpg'),(8,'测试账户2','female','12332112312','john1@outlook.com','this guy is lazy.there is nothing to show','2018-07-25 20:41:49','1969-01-31 00:00:00','2018-07-30 16:30:06','default.jpg'),(9,'测试昵称','female','12332112313','john4@outlook.com','this guy is lazy.there is nothing to show','2018-07-25 20:42:06','1986-01-31 00:00:00','2018-07-30 16:24:39','default.jpg'),(10,'测试账户4','male','12332112314','john5@outlook.com','this guy is lazy.there is nothing to show','2018-07-25 20:42:15','1986-01-31 00:00:00','2018-07-31 19:27:31','153303466933510.png'),(11,'肥仔水爱好者','female','18676054680','johnnico233@outlook.com','this guy is lazy.there is nothing to show','2018-07-30 21:17:24','2000-01-01 00:00:00',NULL,'default.jpg'),(12,'Poi_Yudachi','male','18691332112','test@163.com','this guy is lazy.there is nothing to show','2018-07-30 21:20:12','2000-01-01 00:00:00','2018-07-30 21:21:12','153295710801812.jpg'),(13,'Tester','male','12345678900','test@qq.com','this guy is lazy.there is nothing to show','2018-07-30 21:20:59','2000-01-01 00:00:00',NULL,'default.jpg'),(14,'tset','female','85701036','tester@163.com','this guy is lazy.there is nothing to show','2018-07-30 21:48:52','1980-12-24 00:00:00',NULL,'default.jpg'),(15,'Tester123','male','13425546913','IamTest@outlook.com','this guy is lazy.there is nothing to show','2018-08-01 10:13:55','1984-08-18 00:00:00','2018-08-01 10:14:03','153308978644215.jpg'),(16,'哈哈哈哈$，','male','12345678912','1234@outlook.com','this guy is lazy.there is nothing to show','2018-08-02 14:24:34','1980-03-12 00:00:00','2018-08-02 14:24:51','153319155670616.jpg');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_relationship`
--

DROP TABLE IF EXISTS `user_relationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_relationship` (
  `user_id` int(11) DEFAULT NULL,
  `follow_user_id` int(11) DEFAULT NULL,
  `relation_id` int(11) NOT NULL AUTO_INCREMENT,
  `follow_time` datetime DEFAULT NULL,
  PRIMARY KEY (`relation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_relationship`
--

LOCK TABLES `user_relationship` WRITE;
/*!40000 ALTER TABLE `user_relationship` DISABLE KEYS */;
INSERT INTO `user_relationship` VALUES (5,2,5,'2018-07-16 16:21:37'),(1,8,7,'2018-07-25 20:43:20'),(1,10,10,'2018-07-30 20:59:02'),(1,7,12,'2018-07-30 21:10:27'),(12,2,13,'2018-07-30 21:26:04'),(1,5,14,'2018-07-30 21:34:31'),(1,6,15,'2018-07-30 21:34:35'),(1,12,16,'2018-07-30 21:35:02'),(10,2,17,'2018-07-31 18:58:10'),(15,2,18,'2018-08-01 10:16:49'),(15,1,19,'2018-08-01 10:17:04'),(16,2,20,'2018-08-02 14:31:07'),(7,2,21,'2018-08-02 15:00:27');
/*!40000 ALTER TABLE `user_relationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type` (
  `type_id` int(11) NOT NULL,
  `type_name` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (1,'管理员'),(2,'普通用户');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote_history`
--

DROP TABLE IF EXISTS `vote_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vote_history` (
  `history_id` int(11) NOT NULL AUTO_INCREMENT,
  `vote_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  PRIMARY KEY (`history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote_history`
--

LOCK TABLES `vote_history` WRITE;
/*!40000 ALTER TABLE `vote_history` DISABLE KEYS */;
INSERT INTO `vote_history` VALUES (1,5,2,15,'2018-07-30 10:24:49'),(2,5,2,18,'2018-07-30 10:24:49'),(3,5,6,15,'2018-07-30 10:30:32'),(4,5,6,18,'2018-07-30 10:30:32'),(5,5,5,15,'2018-07-30 10:33:36'),(6,5,5,16,'2018-07-30 10:33:36'),(7,5,7,15,'2018-07-30 10:48:18'),(8,5,7,18,'2018-07-30 10:48:18'),(9,5,1,15,'2018-07-30 11:15:25'),(10,5,1,18,'2018-07-30 11:15:25'),(11,6,1,19,'2018-07-30 12:05:25'),(12,2,8,5,'2018-07-30 14:27:35'),(13,15,7,90,'2018-07-30 15:24:21'),(14,2,7,6,'2018-07-30 15:44:48'),(15,8,7,33,'2018-07-30 15:50:45'),(16,2,12,4,'2018-07-30 21:21:31'),(17,19,5,105,'2018-07-30 22:03:20'),(18,19,5,107,'2018-07-30 22:03:20'),(19,13,1,87,'2018-07-31 11:59:15'),(20,19,10,105,'2018-07-31 18:57:22'),(21,19,10,107,'2018-07-31 18:57:22'),(22,13,10,87,'2018-07-31 19:02:38'),(23,19,7,105,'2018-07-31 19:14:51'),(24,19,7,107,'2018-07-31 19:14:51'),(25,16,7,93,'2018-07-31 19:16:00'),(26,17,7,104,'2018-07-31 19:16:07'),(27,19,1,105,'2018-07-31 19:31:43'),(28,19,1,106,'2018-07-31 19:31:43'),(29,17,1,103,'2018-07-31 19:34:35'),(30,16,1,92,'2018-07-31 19:34:39'),(31,16,15,93,'2018-08-01 10:14:16'),(32,19,15,105,'2018-08-01 10:14:37'),(33,19,15,106,'2018-08-01 10:14:37'),(34,20,15,109,'2018-08-01 10:15:48'),(35,20,16,108,'2018-08-02 14:27:24'),(36,20,16,109,'2018-08-02 14:27:24'),(37,21,16,113,'2018-08-02 14:30:03'),(38,21,16,115,'2018-08-02 14:30:03'),(39,21,16,116,'2018-08-02 14:30:03'),(40,22,1,124,'2018-08-02 14:38:52'),(41,22,1,125,'2018-08-02 14:38:52');
/*!40000 ALTER TABLE `vote_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote_item`
--

DROP TABLE IF EXISTS `vote_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vote_item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `vote_id` int(11) DEFAULT NULL,
  `option_name` varchar(32) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote_item`
--

LOCK TABLES `vote_item` WRITE;
/*!40000 ALTER TABLE `vote_item` DISABLE KEYS */;
INSERT INTO `vote_item` VALUES (1,1,'新垣结衣',4),(2,1,'武田玲奈',2),(3,1,'深田恭子',4),(4,2,'AMD!',7),(5,2,'INTEL',5),(6,2,'2个都是',11),(15,5,'游泳',20),(16,5,'爬山',14),(17,5,'听歌',18),(18,5,'打球',22),(19,6,'后街女孩',1),(20,6,'骨头王',0),(21,6,'高达',1),(22,7,'千歌',0),(23,7,'逢田姐',0),(24,7,'曜酱',0),(25,7,'堕天使',0),(26,7,'花丸',0),(27,7,'露比',0),(28,7,'加拿大流氓',0),(29,7,'南哥',0),(30,7,'黛娜',0),(31,8,'水树奈奈',1),(32,8,'小唯仓',0),(33,8,'东山奈央',1),(34,8,'平野绫',0),(35,9,'opt1',1),(36,9,'opt2',1),(37,9,'opt3',0),(38,9,'opt4',1),(74,10,'option1 for test',0),(75,10,'option2',0),(76,10,'option3',0),(77,10,'option4',0),(78,10,'option5 for fadd',0),(79,11,'1~~',0),(80,11,'2~~',1),(81,11,'3~~',1),(86,13,'1',0),(87,13,'2',2),(90,15,'3',1),(91,15,'4',0),(92,16,'1',1),(93,16,'2',2),(96,12,'opt1',0),(97,12,'opt2',0),(98,12,'opt3',0),(99,12,'opt4',0),(100,18,'option1',0),(101,18,'option2',0),(102,17,'1',0),(103,17,'2',1),(104,17,'3',1),(105,19,'option1',5),(106,19,'option2',2),(107,19,'option3',3),(108,20,'option 1 ',1),(109,20,'option 2',2),(110,20,'option 3',0),(111,14,'3',0),(112,14,'4',0),(113,21,'兴中道广场',1),(114,21,'假日广场',0),(115,21,'利和广场',1),(116,21,'岐江河',1),(117,21,'最后一个我不知道了',0),(123,22,'兴中道广场',0),(124,22,'假日广场',1),(125,22,'fdsafa',1);
/*!40000 ALTER TABLE `vote_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote_message`
--

DROP TABLE IF EXISTS `vote_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vote_message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `message_content` varchar(256) DEFAULT NULL,
  `vote_id` int(11) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `available` tinyint(1) DEFAULT '1',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote_message`
--

LOCK TABLES `vote_message` WRITE;
/*!40000 ALTER TABLE `vote_message` DISABLE KEYS */;
INSERT INTO `vote_message` VALUES (1,'测试数据123',5,'2018-07-20 19:49:44',0,1),(12,'更新留言测试',2,'2018-07-21 15:23:52',0,2),(13,'测试数据+1',5,'2018-07-21 15:24:08',0,2),(14,'test',2,'2018-07-21 16:12:22',0,2),(20,'this is the test',2,'2018-07-21 16:32:47',0,2),(25,'测试+4',5,'2018-07-21 16:41:13',0,2),(26,'test for ctrl + enter',5,'2018-07-21 16:53:32',1,2),(27,'那当然是投Gakki啦',1,'2018-07-21 16:59:56',1,2),(28,'啊哈哈哈哈',2,'2018-07-23 16:31:48',1,2),(29,'留言测试hahaha',2,'2018-07-28 09:09:24',1,2),(30,'我只是来试下投票行不行~~~',2,'2018-07-28 10:25:07',1,2),(32,'gakki!,不接受任何反驳',1,'2018-07-28 11:06:36',1,2),(34,'哈哈哈是',2,'2018-07-28 17:22:24',1,2),(36,'我来试下测试',5,'2018-07-28 20:44:25',1,5),(38,'死宅真恶心',1,'2018-07-28 21:23:54',1,5),(39,'死宅真恶心~,呵呵哒',1,'2018-07-30 21:21:24',1,12),(43,'我只是想试试回复行不行',19,'2018-07-30 22:03:32',1,5),(44,'test~',13,'2018-07-31 11:59:21',1,1),(45,'jksdafjksadfhjka',9,'2018-07-31 18:56:49',1,5),(46,'哈哈刻录机很快就会',19,'2018-07-31 18:57:32',1,10),(47,'I want to send somthing',16,'2018-08-01 10:14:25',1,15),(48,'我是来测试一下的',2,'2018-08-02 14:25:04',1,16),(49,'老刘嘤嘤嘤',11,'2018-08-07 17:12:33',1,1);
/*!40000 ALTER TABLE `vote_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote_topic`
--

DROP TABLE IF EXISTS `vote_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vote_topic` (
  `vote_id` int(11) NOT NULL AUTO_INCREMENT,
  `vote_topic` varchar(32) DEFAULT NULL,
  `vote_content` varchar(256) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `is_multi` tinyint(1) DEFAULT NULL,
  `usable` tinyint(1) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `total_vote_count` int(11) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`vote_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote_topic`
--

LOCK TABLES `vote_topic` WRITE;
/*!40000 ALTER TABLE `vote_topic` DISABLE KEYS */;
INSERT INTO `vote_topic` VALUES (1,'谁是你老婆?','你们觉得谁是你们心目中的老婆？','2018-07-19 00:00:00','2018-07-27 09:00:00',0,0,2,0,'2018-07-18 09:24:09'),(2,'你们觉得AMD还是Intel良心','RT','2018-07-19 00:00:00','2018-07-31 00:00:00',0,1,2,0,'2018-07-19 19:24:09'),(5,'爱好投票','你们喜欢什么爱好','2018-07-20 00:00:00','2018-07-31 00:00:00',1,1,1,2,'2018-07-20 09:24:09'),(6,'7月霸权','请问7月霸权是啥','2018-07-21 00:00:00','2018-07-31 00:00:00',0,1,1,0,'2018-07-18 05:24:09'),(7,'Love live Sunshine角色应援','水团你们厨谁？','2018-07-21 00:00:00','2018-07-31 00:00:00',0,0,1,0,'2018-07-18 09:24:09'),(8,'声优投票！','请选择你们最喜欢的声优','2018-07-21 00:00:00','2018-07-31 00:00:00',0,0,1,0,'2018-07-18 09:50:09'),(9,'多选测试','this is for the test','2018-07-22 00:00:00','2018-07-30 00:00:00',1,1,1,3,'2018-07-18 09:24:09'),(10,'test','test for content','2018-07-28 19:24:44','2018-07-31 19:24:44',0,1,2,2,'2018-07-27 09:29:46'),(11,'投票测试','this is for the voting test','2018-07-29 21:02:00','2018-07-31 12:03:00',1,1,2,2,'2018-07-28 18:10:23'),(12,'这是测试1','这是测试123','2018-07-31 12:33:00','2018-08-15 23:33:00',1,1,1,3,'2018-07-30 15:00:58'),(13,'test测试','测试11','2018-07-30 20:02:00','2018-08-30 03:02:00',0,1,7,2,'2018-07-30 15:19:00'),(14,'test123','test','2018-08-30 11:11:00','2018-09-13 12:33:00',1,1,1,2,'2018-07-30 15:20:27'),(15,'test1234','test','2018-08-30 11:11:00','2018-09-13 12:33:00',0,1,7,2,'2018-07-30 15:21:06'),(16,'test45','test','2018-07-16 04:04:00','2018-08-30 04:04:00',0,1,7,2,'2018-07-30 15:25:09'),(17,'test455','test','2018-07-16 04:04:00','2018-08-30 04:04:00',0,1,1,2,'2018-07-30 15:25:19'),(18,'voting test','this is test','2018-07-30 20:30:00','2018-08-22 04:00:00',0,1,7,2,'2018-07-30 20:12:04'),(19,'我只是想试试投票行不行','没有内容','2018-07-30 10:02:00','2018-08-16 10:23:00',1,1,5,2,'2018-07-30 22:03:13'),(20,'this is the test','nothing to show','2018-08-01 01:01:00','2018-08-30 10:10:00',1,1,15,2,'2018-08-01 10:15:44'),(21,'中山比较好玩的地方','你们觉得中山那个地方比较好玩','2018-08-02 10:30:00','2018-08-24 10:30:00',1,1,16,4,'2018-08-02 14:29:56'),(22,'中山比较好玩的地方','你们觉得中山那个地方比较好玩','2018-08-02 10:30:00','2018-08-24 10:30:00',1,1,1,2,'2018-08-02 14:30:21');
/*!40000 ALTER TABLE `vote_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'vote'
--
/*!50003 DROP PROCEDURE IF EXISTS `addNewTopic` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewTopic`(in topic varchar(32),in content varchar(250),in startTime dateTime,in endTime dateTime,
							 in isMulti bool,in userID int,in maxCount int,out topicId int)
BEGIN
	declare userable bool;
	insert into vote_topic(vote_topic,vote_content,start_time,end_time,is_multi,usable,user_id,total_vote_count,create_time) 
    values(topic,content,startTime,endTime,isMulti,true,userId,maxCount,now());
    select vote_id into topicId from vote_topic where user_id = userID order by vote_id desc limit 0,1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `addNewVoteMessage` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewVoteMessage`(in content varchar(256),in voteId int,in userId int,out total int)
BEGIN
	insert into vote_message(message_content,vote_id,user_id,send_time)
        values(content,voteId,userId,now());
	select count(*) into total from vote_message where vote_id = voteId and available;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `alterUserRelationShip` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `alterUserRelationShip`(in userId int,in followId int,out result int)
BEGIN
	declare pre_rel int;
    declare after_rel int;
    declare relId int;
    select count(*) into pre_rel from user_relationship where user_id = userId and follow_user_id = followId;
    if pre_rel = 0 then
		insert into user_relationship(user_id,follow_user_id,follow_time) values(userId,followId,now());
	else
		select relation_id into relId from user_relationship where user_id = userId and follow_user_id = followId;
        delete from user_relationship where relation_id = relId;
    end if;
    select count(*) =1 into after_rel from user_relationship where user_id = userId and follow_user_id = followId;
    if pre_rel <>after_rel then 
		if pre_rel = 0 then set result = 1;
		else set result =2;
		end if;
	else set result = 0 ;
    end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `banUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `banUser`(in operateUserId int,
in uaccount varchar(16),out result bool)
BEGIN
	declare uid int;
    declare banned bool;
    declare isRecord bool;
    declare isOperate bool;
    select user_type_id = 1 into isOperate from user where user_id = operateUserId;
	if isOperate then
		update user set loginable = 0 where account  = uaccount;
		select user_id into uid from user where account = uaccount;
		insert into dustbin(ban_id,ban_type,ban_time) values(uid,1,now());
		select count(*) into banned from user where user_id = uid and loginable = 0;
		select count(*) into isRecord from dustbin where ban_id = uid and ban_type = 1;
		select banned=1&&isRecord=1 into result;
	else
		select 0 into result;
	END if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `deleteVoteMessage` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteVoteMessage`(in messageId int,out result int)
BEGIN
	declare updateResult bool;
    declare insertResult bool;
	update vote_message set available = false where message_id = messageId;
    insert into dustbin(ban_id,ban_type,ban_time) values(messageId,3,now());
    select count(*) = 1 into updateResult from vote_message where message_id =
    messageId and available = false;
    select COUNT(*) = 1 into insertResult from dustbin where ban_type=3 and 
    ban_id = messageId;
    select updateResult&insertResult into result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delVoteMessage` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delVoteMessage`(in banId int,in messageId int,out result int)
BEGIN
	declare ddr int;
    declare vdr int;
    start transaction;
    delete from vote_message where message_id = messageId;
    delete from dustbin where id = banId and ban_type =3;
    commit;
    select count(*) =0 into ddr from dustbin where id = banId;
    select count(*) =0 into vdr from vote_message where message_id = messageId;
    select ddr&vdr into result;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `recoverMessage` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `recoverMessage`(in banId int,in messageId int,out result int)
BEGIN
	declare ur int;
    declare dr int;
    update vote_message set available = true where message_id = messageId;
    delete from dustbin where id = banId;
    select count(*)=0 into ur from vote_message where message_id = messageId and available = false;
    select count(*)=0 into dr from dustbin where id = banId;
    select ur&dr into result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `recoverTopic` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `recoverTopic`(in banId int,in topicId int,out result int)
BEGIN
	declare deleteResult int;
    declare updateResult int;
    delete from dustbin where ban_id = banId;
    update vote_topic set usable = 1 where vote_id = topicId;
    select COUNT(*) = 0 into deleteResult from dustbin where ban_id = banId;
    select COUNT(*) = 1 into updateResult from vote_topic where vote_id = topicId and usable = 1;
	select deleteResult&updateResult into result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `recoverUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `recoverUser`(in userId int,out result bool)
BEGIN
	declare delRecord bool;
    declare recoverRecord bool;
	delete from dustbin where ban_id = userId and ban_type =1;
    update user set loginable = 1 where user_id = userId;
    select COUNT(*)=0 into delRecord from dustbin where ban_id = userId and ban_type = 1;
    select COUNT(*)=1 into recoverRecord from user where user_id = userId and loginable = 1;
    select delRecord&recoverRecord into result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateTopic` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateTopic`(in topic varchar(32),in content varchar(250),in startTime dateTime,in endTime dateTime,
							 in isMulti bool,in userID int,in maxCount int,in topicId int,out result int)
BEGIN
	declare done int default 0;
    declare cur_item int;
	declare cur_item_id CURSOR for select item_id from vote_item where vote_id = topicId;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
    update vote_topic set vote_topic = topic,vote_content= content,start_time=startTime,end_time = endTime,is_multi =isMulti, usable = 1,
    user_id =userID,total_vote_count = maxCount where vote_id = topicId;
    
    open cur_item_id;
    fetch cur_item_id into cur_item;
    while done <>1 do 
		delete from vote_item where item_id = cur_item;
        fetch cur_item_id into cur_item;
	end while;
    close cur_item_id;
    select count(*) = 0 into result from vote_item where vote_id = topicId;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `uploadVoteItem` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `uploadVoteItem`(in userId int,in itemId int,in topicId int)
BEGIN
	insert into vote_history(vote_id,user_id,item_id,send_time) values(topicId,userId,itemId,now());
    update vote_item set count=count+1 where item_id = itemId;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-29 20:39:31
