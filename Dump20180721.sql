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
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'yosoro','monstar10',1),(2,'yosoro1','monstar10',1),(5,'yosoro233','monstar10',1),(6,'yosoro2334','monstar10',1);
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
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'yosoro','female','12345678910','1011206880@qq.com','nothing to show','2018-07-18 00:00:00',NULL,'2018-07-20 09:05:30'),(2,'yohane','female','1234567891','101120680@qq.com','nothing to show','2018-07-18 00:00:00',NULL,'2018-07-20 09:05:30'),(5,'yosoro555','male','12332112332','1011@qq.com','this guy is lazy.there is nothing to show','2018-07-18 21:12:02','1988-10-07 00:00:00','2018-07-20 09:05:30'),(6,'渡边曜','female','123321123324','10112@qq.com','this guy is lazy.there is nothing to show','2018-07-18 21:13:54','2004-10-07 00:00:00','2018-07-20 19:05:30');
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
  `relation_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_relationship`
--

LOCK TABLES `user_relationship` WRITE;
/*!40000 ALTER TABLE `user_relationship` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote_history`
--

LOCK TABLES `vote_history` WRITE;
/*!40000 ALTER TABLE `vote_history` DISABLE KEYS */;
INSERT INTO `vote_history` VALUES (1,5,2,15,'2018-07-21 14:02:56'),(2,5,2,18,'2018-07-21 14:02:56'),(3,2,2,4,'2018-07-21 14:19:46'),(4,2,2,5,'2018-07-21 14:19:49'),(5,2,2,6,'2018-07-21 14:19:52'),(6,2,2,5,'2018-07-21 14:24:25'),(7,2,2,4,'2018-07-21 14:25:03'),(8,2,2,4,'2018-07-21 14:26:00'),(9,2,2,6,'2018-07-21 14:26:17'),(10,2,2,6,'2018-07-21 14:26:30'),(11,2,2,6,'2018-07-21 14:27:04'),(12,2,2,6,'2018-07-21 14:27:58'),(13,2,2,4,'2018-07-21 14:28:16'),(14,2,2,6,'2018-07-21 14:28:19'),(15,2,2,5,'2018-07-21 14:28:47'),(16,2,2,4,'2018-07-21 14:47:16'),(17,2,2,6,'2018-07-21 14:47:20'),(18,2,2,6,'2018-07-21 14:47:23'),(19,2,2,4,'2018-07-21 14:47:26'),(20,1,2,1,'2018-07-21 16:59:47');
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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote_item`
--

LOCK TABLES `vote_item` WRITE;
/*!40000 ALTER TABLE `vote_item` DISABLE KEYS */;
INSERT INTO `vote_item` VALUES (1,1,'新垣结衣',3),(2,1,'武田玲奈',2),(3,1,'深田恭子',3),(4,2,'AMD!',6),(5,2,'INTEL',3),(6,2,'2个都是',8),(15,5,'游泳',13),(16,5,'爬山',13),(17,5,'听歌',15),(18,5,'打球',17),(19,6,'后街女孩',0),(20,6,'骨头王',0),(21,6,'高达',0),(22,7,'千歌',0),(23,7,'逢田姐',0),(24,7,'曜酱',0),(25,7,'堕天使',0),(26,7,'花丸',0),(27,7,'露比',0),(28,7,'加拿大流氓',0),(29,7,'南哥',0),(30,7,'黛娜',0),(31,8,'水树奈奈',0),(32,8,'小唯仓',0),(33,8,'东山奈央',0),(34,8,'平野绫',0),(35,9,'opt1',0),(36,9,'opt2',0),(37,9,'opt3',0),(38,9,'opt4',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote_message`
--

LOCK TABLES `vote_message` WRITE;
/*!40000 ALTER TABLE `vote_message` DISABLE KEYS */;
INSERT INTO `vote_message` VALUES (1,'测试数据1',5,'2018-07-20 19:49:44',1,1),(2,'测试数据2',5,'2018-07-20 19:49:44',1,1),(3,'测试数据3',5,'2018-07-20 19:49:44',1,1),(4,'测试数据4',5,'2018-07-20 19:49:44',1,1),(5,'测试数据5',5,'2018-07-20 19:49:44',1,1),(6,'this is test yosoro~',5,'2018-07-20 21:36:24',1,6),(10,'test',2,'2018-07-21 15:21:04',1,1),(12,'留言测试',2,'2018-07-21 15:23:52',1,2),(13,'测试数据+1',5,'2018-07-21 15:24:08',1,2),(14,'test',2,'2018-07-21 16:12:22',1,2),(15,'this is for the test',2,'2018-07-21 16:26:58',1,2),(16,'this is for the test',2,'2018-07-21 16:27:08',1,2),(17,'this is for the test',2,'2018-07-21 16:27:49',1,2),(18,'this is the test',2,'2018-07-21 16:30:23',1,2),(19,'this is the test',2,'2018-07-21 16:32:24',1,2),(20,'this is the test',2,'2018-07-21 16:32:47',1,2),(21,'this is the test',2,'2018-07-21 16:33:19',1,2),(22,'测试+1',5,'2018-07-21 16:40:53',1,2),(23,'测试+2',5,'2018-07-21 16:40:59',1,2),(24,'测试+3',5,'2018-07-21 16:41:07',1,2),(25,'测试+4',5,'2018-07-21 16:41:13',1,2),(26,'test for ctrl + enter',5,'2018-07-21 16:53:32',1,2),(27,'那当然是投Gakki啦',1,'2018-07-21 16:59:56',1,2);
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
  PRIMARY KEY (`vote_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote_topic`
--

LOCK TABLES `vote_topic` WRITE;
/*!40000 ALTER TABLE `vote_topic` DISABLE KEYS */;
INSERT INTO `vote_topic` VALUES (1,'谁是你老婆?','你们觉得谁是你们心目中的老婆？','2018-07-19 00:00:00','2018-07-28 00:00:00',0,1,2,0),(2,'你们觉得AMD还是Intel良心','RT','2018-07-19 00:00:00','2018-07-31 00:00:00',0,1,2,0),(5,'爱好投票','你们喜欢什么爱好','2018-07-20 00:00:00','2018-07-31 00:00:00',1,1,1,2),(6,'7月霸权','请问7月霸权是啥','2018-07-21 00:00:00','2018-07-31 00:00:00',0,1,1,0),(7,'Love live Sunshine角色应援','水团你们厨谁？','2018-07-21 00:00:00','2018-07-31 00:00:00',0,1,1,0),(8,'声优投票！','请选择你们最喜欢的声优','2018-07-21 00:00:00','2018-07-31 00:00:00',0,1,1,0),(9,'多选测试','this is for the test','2018-07-22 00:00:00','2018-07-30 00:00:00',1,1,1,3);
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
	insert into vote_topic(vote_topic,vote_content,start_time,end_time,is_multi,usable,user_id,total_vote_count) 
    values(topic,content,startTime,endTime,isMulti,true,userId,maxCount);
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
	select count(*) into total from vote_message where vote_id = voteId;
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

-- Dump completed on 2018-07-21 17:04:10
