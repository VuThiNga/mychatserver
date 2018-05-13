-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: mychat
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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `feed_id` int(11) NOT NULL,
  `creator_id` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `attachment_thumb_url` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `is_like` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `creator_id_comment_users_idx` (`creator_id`),
  KEY `feed_id_comment_feed_idx` (`feed_id`),
  CONSTRAINT `creator_id_comment_users` FOREIGN KEY (`creator_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `feed_id_comment_feed` FOREIGN KEY (`feed_id`) REFERENCES `feed` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,1,3,'Cố lên',NULL,'2018-05-02 17:57:56',NULL,NULL,NULL),(2,25,1,'Hinh to vai vay','sticker_07.gif','2018-05-11 01:49:59',NULL,NULL,NULL);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation`
--

DROP TABLE IF EXISTS `conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conversation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `creator_id` int(11) NOT NULL,
  `channel_id` varchar(45) DEFAULT 'message',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `deleted_date` datetime DEFAULT NULL,
  `type` enum('single','group') DEFAULT 'single',
  PRIMARY KEY (`id`),
  KEY `creator_id_conversation_users_idx` (`creator_id`),
  CONSTRAINT `creator_id_conversation_users` FOREIGN KEY (`creator_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
INSERT INTO `conversation` VALUES (1,'HelloSu',1,'message','2018-04-22 14:52:53',NULL,'single'),(2,'Hello',2,'message','2018-04-22 14:52:53',NULL,'single'),(3,'Hello hah',3,'message','2018-04-22 14:52:53',NULL,'single'),(4,'3 dua nhoc',1,'message','2018-04-24 00:35:00',NULL,'group'),(5,'Hen hoc tieng',3,'message','2018-04-24 17:06:25',NULL,'single'),(8,'Test ',1,'message','2018-04-28 01:37:27',NULL,'group'),(9,'H',1,'message','2018-05-08 16:24:45',NULL,'group');
/*!40000 ALTER TABLE `conversation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed`
--

DROP TABLE IF EXISTS `feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator_id` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `attachment_url` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `deleted_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `creator_id_feed_users_idx` (`creator_id`),
  CONSTRAINT `creator_id_feed_users` FOREIGN KEY (`creator_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed`
--

LOCK TABLES `feed` WRITE;
/*!40000 ALTER TABLE `feed` DISABLE KEYS */;
INSERT INTO `feed` VALUES (1,1,'Haiz. Hôm nay mệt quá','yoichi_hiruma_eyeshield_21_by_hansih2-d5wha05.png','2018-05-02 17:56:54',NULL),(2,1,'I wish I efforted so mụch',NULL,'2018-05-02 17:56:54',NULL),(3,2,'Đẹp quá','16174458_1523666110996550_7717224724625203566_n.jpg','2018-05-02 17:56:54',NULL),(4,1,'one two three ...','toc-ngan-dep-an-tuong-6.jpg','2018-05-02 18:51:15',NULL),(6,1,'Có những khi chỉ muốn để tâm hồn nó trôi dạt đâu đó mà chẳng nghĩ ngợi gì','20171127_215300.jpg','2018-05-09 17:35:14',NULL),(7,1,'Tôi nhớ tôi của ngày xưa?',NULL,'2018-05-09 17:35:14',NULL),(8,2,'Em có hẹn với tháng 5. Tháng của anh. ','15741308_732277136925643_8843417271534870565_n.jpg','2018-05-09 17:35:14',NULL),(9,2,'Today, I start learning English in Water University, to I can meet my idol in my future','16174458_1523666110996550_7717224724625203566_n.jpg','2018-05-09 17:35:14',NULL),(10,3,'Mịa. toàn bọn dở hơi',NULL,'2018-05-09 17:35:14',NULL),(11,3,'OK. I\'m fine',NULL,'2018-05-09 17:35:14',NULL),(12,4,'Trà đá hồ Văn Quán đi! Ae ê','20171127_215300.jpg','2018-05-09 17:35:14',NULL),(13,4,'Haha. Mãi mà không có lương',NULL,'2018-05-09 17:35:14',NULL),(14,5,'Hè đến rồi.',NULL,'2018-05-09 17:35:14',NULL),(15,6,'Ai biết. Mọi chuyện rồi có ổn không? ',NULL,'2018-05-09 17:35:14',NULL),(16,7,'Chẳng may trên đường đời t vấp ngã, nâng t lên các mày nhé!','HEAB1746.JPG','2018-05-09 17:35:14',NULL),(17,8,'Kỉ với chẳng yếu. éo biết bao giờ mới ra trường. Có họ với đức phúc :v','HEAB1418.JPG','2018-05-09 17:35:14',NULL),(18,10,'Em chúc anh thành công. thật thành công anh nhé','16113968_10154070366463414_7615047365140684872_n.png','2018-05-09 17:35:14',NULL),(19,12,'Sắp bảo vệ mà chưa được gì',NULL,'2018-05-09 17:35:14',NULL),(20,12,'Tao mệt. ',NULL,'2018-05-09 17:35:14',NULL),(21,13,'Khôn hết phần thiên hạ. :3 gửi em. tính toán vừa thôi. để cho trời còn tính nữa',NULL,'2018-05-09 17:35:14',NULL),(22,14,'Đám cưới anh cò','18471616_1002915936510720_740409433_n.jpg','2018-05-09 17:35:14',NULL),(23,14,'Chúng mình cùng xinh','HEAB1947.JPG','2018-05-09 17:35:14',NULL),(24,1,'Nga ham. Su Gi ham. ',NULL,'2018-05-09 21:22:00',NULL),(25,1,'Mai khong sua duoc la the meo nao','anh1.png','2018-05-09 21:40:41',NULL);
/*!40000 ALTER TABLE `feed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend`
--

DROP TABLE IF EXISTS `friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_one` int(11) NOT NULL,
  `person_two` int(11) NOT NULL,
  `is_friend` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `person_one_friend_users_idx` (`person_one`),
  KEY `person_two_friend_users_idx` (`person_two`),
  CONSTRAINT `person_one_friend_users` FOREIGN KEY (`person_one`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `person_two_friend_users` FOREIGN KEY (`person_two`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` VALUES (1,1,2,1),(4,1,3,1),(6,2,3,0),(7,2,4,1),(22,1,9,0),(23,1,11,0),(25,5,1,0),(27,17,1,1),(28,18,1,0),(29,19,1,0),(31,1,10,0),(33,1,9,0),(34,1,5,0),(37,6,1,0),(38,8,1,0),(39,14,1,0),(40,7,1,1);
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like`
--

DROP TABLE IF EXISTS `like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `feed_id` int(11) NOT NULL,
  `creator_id` int(11) NOT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_like` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `creator_id_like_users_idx` (`creator_id`),
  KEY `feed_id_like_feed_idx` (`feed_id`),
  CONSTRAINT `creator_id_like_users` FOREIGN KEY (`creator_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `feed_id_like_feed` FOREIGN KEY (`feed_id`) REFERENCES `feed` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like`
--

LOCK TABLES `like` WRITE;
/*!40000 ALTER TABLE `like` DISABLE KEYS */;
INSERT INTO `like` VALUES (1,1,2,'2018-05-02 17:58:34',1),(2,1,3,'2018-05-02 17:58:34',1),(3,2,1,'2018-05-02 17:58:34',1),(4,25,1,'2018-05-11 01:50:08',1),(5,25,1,'2018-05-11 01:50:13',1);
/*!40000 ALTER TABLE `like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conversation_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `message_type` enum('text','image','audio','video') DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `attachment_url` varchar(255) DEFAULT NULL,
  `attachment_thumb_url` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `deleted_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `conversation_id_messages_conversation_idx` (`conversation_id`),
  KEY `sender_id_messages_users_idx` (`sender_id`),
  CONSTRAINT `conversation_id_messages_conversation` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sender_id_messages_users` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,1,1,'text','Hello. How are you',NULL,NULL,'2018-04-22 15:00:47',NULL),(2,1,4,'text','I\'m fine. Thanks',NULL,NULL,'2018-04-22 15:00:47',NULL),(3,1,1,'text','hihi',NULL,NULL,'2018-04-22 15:00:47',NULL),(4,1,1,'text','Where are you?',NULL,NULL,'2018-04-22 15:00:47',NULL),(5,1,4,'text','Here!',NULL,NULL,'2018-04-22 15:00:47',NULL),(6,2,2,'text','Steven!',NULL,NULL,'2018-04-22 15:00:47',NULL),(7,2,2,'text','Help me',NULL,NULL,'2018-04-22 15:00:47',NULL),(8,2,3,'text','What \'s matter?',NULL,NULL,'2018-04-22 15:00:47',NULL),(9,3,3,'text','Hi',NULL,NULL,'2018-04-22 15:00:47',NULL),(10,3,1,'text','hello',NULL,NULL,'2018-04-22 15:12:46',NULL),(11,4,1,'text','gi the may dua?',NULL,NULL,'2018-04-24 00:36:22',NULL),(12,4,2,'text','hi chi?',NULL,NULL,'2018-04-24 00:36:22',NULL),(13,4,1,'text','Sao e?',NULL,NULL,'2018-04-24 00:36:22',NULL),(14,4,3,'text','Nhan choi the thoi1!',NULL,NULL,'2018-04-24 00:36:22',NULL);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participants`
--

DROP TABLE IF EXISTS `participants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participants` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conversation_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `conversation_id_paticipants_conversation_idx` (`conversation_id`),
  KEY `user_id_paticipants_users_idx` (`user_id`),
  CONSTRAINT `conversation_id_paticipants_conversation` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id_paticipants_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participants`
--

LOCK TABLES `participants` WRITE;
/*!40000 ALTER TABLE `participants` DISABLE KEYS */;
INSERT INTO `participants` VALUES (1,1,1),(2,1,4),(3,2,3),(4,2,2),(5,3,1),(6,3,3),(7,4,1),(8,4,2),(9,4,3),(10,5,3),(11,5,7),(18,8,1),(19,8,2),(20,8,3),(21,9,1),(22,9,2),(23,9,8);
/*!40000 ALTER TABLE `participants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `participant_id` int(11) NOT NULL,
  `report_type` varchar(45) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id_reports_users_idx` (`user_id`),
  KEY `paticipant_id_report_paticipants_idx` (`participant_id`),
  CONSTRAINT `paticipant_id_report_paticipants` FOREIGN KEY (`participant_id`) REFERENCES `participants` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id_reports_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token` varchar(1000) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT 'icon_default_avatar.png',
  `img_cover` varchar(255) DEFAULT 'bg_over.png',
  `birthday` datetime DEFAULT NULL,
  `gender` enum('underfined','male','female','other gender') DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `deleted_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Nga Gerrard','$2a$10$PldJuUx4g353KI8brTYoieLy1n0Hln16GbgGqmaLBW60PdFG0CHfy','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6Ik5nYSBHZXJyYXJkIiwic2NvcGUiOiJ1c2VyIiwianRpIjoiNmFjMzQxNWMtYTZhNC00N2Y3LWE1ZjUtZmU0NTZkZGI4MDdlIiwiaWF0IjoxNTI2MDU5NTY1LCJleHAiOjE1Mjk2NTk1NjV9.J0JjNC_YSKyUHSW5INsD0aY8XPjCZDm84P-t4dLHips','01639518700','anh1.png','HEAB1489.JPG','1995-03-13 00:00:00','female','2018-03-31 22:32:45',NULL),(2,'VuThiNga','$2a$10$GepOnpVsBX/LcL1mA.Cgfe7lqL/o1a9K85E9leOj1KjawkyhjOkKi','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IlZ1VGhpTmdhIiwic2NvcGUiOiJ1c2VyIiwianRpIjoiMTRhYWIzZWEtOWUxNC00YzRjLWJlZTUtOWJhMjI4ZjE0NjU1IiwiaWF0IjoxNTIyOTQzNjYyLCJleHAiOjE1MjY1NDM2NjJ9.50qT8XfHwy16GcsXTtawpIIJsFJuqN5kv01puvE18Bc','0984058146','bingoleft.png','yoichi_hiruma_eyeshield_21_by_hansih2-d5wha05.png','1995-02-22 00:00:00','female','2018-04-05 22:54:24',NULL),(3,'Steven Gerrard','$2a$10$DxsfOk7VSxnmhLZp.TgGBekN89f1auctF6AN06zu1NOn8OjhT174S','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IlN0ZXZlbiBHZXJyYXJkIiwic2NvcGUiOiJ1c2VyIiwianRpIjoiYTdhNTM0ZjEtMzIzZC00MGNjLTg0MzEtOGRkMTZmYzc3MjBkIiwiaWF0IjoxNTIzMDMwNjM1LCJleHAiOjE1MjY2MzA2MzV9.GEGYwGgYk6EJnc7shrxx1Z27h7lW9tAP4dV-6oHm-lI',NULL,'21fe701357449e7d416697f919915aeabdb926ef_hq.gif','bg_over.png','1980-05-30 00:00:00','male','2018-04-06 23:03:58',NULL),(4,'Quynh Nguyen','$2a$10$tpO6H7nOOfimdCwFAidbFuGVi9.YPM1SRLG7jJSmRELVb9eZgFOgi','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IlF1eW5oIE5ndXllbiIsInNjb3BlIjoidXNlciIsImp0aSI6IjQ3NmY4NWNlLWRhZWUtNDc4MC05OGNmLWY5MzVkMjkzYjUyNSIsImlhdCI6MTUyMzYwNDIxMSwiZXhwIjoxNTI3MjA0MjExfQ.DG5WzQoGOF1lFN0AAU0miVtcybAuctFmIt7jGjXfRkY',NULL,'toc-ngan-dep-an-tuong-6.jpg','15741308_732277136925643_8843417271534870565_n.jpg','1996-12-12 00:00:00','female','2018-04-13 14:23:31',NULL),(5,'Tung Nguyen','$2a$10$8x8Z8GJ0SvFwOk3tBCCpc.wX/XoXWghQ5iNc/uh1cYXhfbJtt/FfG','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IlR1bmcgTmd1eWVuIiwic2NvcGUiOiJ1c2VyIiwianRpIjoiNjljOThjMDEtMjk4Zi00MDIwLTk1MGQtNDNmMzNkYjRkOTBkIiwiaWF0IjoxNTIzNjA1MDg1LCJleHAiOjE1MjcyMDUwODV9.cw_oB_yprSkUKRJtGSK8efCZw61UslyPlveVipx52Go',NULL,'toc-ngan-dep-an-tuong-6.jpg','bg_over.png','2000-05-10 00:00:00','male','2018-04-13 14:36:33',NULL),(6,'Su Gi','$2a$10$G1jv7eg5XhlznIqmjo51z.Po8tWmfSi8LSPDGiE81Bv.2Oyj0r5L6','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IlN1IEdpIiwic2NvcGUiOiJ1c2VyIiwianRpIjoiZmY4YjY0MTEtNTJkOS00ODYxLWE3ZjMtYTU0ZmU0Y2JjOWY2IiwiaWF0IjoxNTI0NzQyOTY2LCJleHAiOjE1MjgzNDI5NjZ9.pImtD8waA2k29D_5uHl4oAWGhX3RKar7I4baJiLh7bY','0984058146','icon_default_avatar.png','bg_over.png','2006-04-26 00:00:00','male','2018-04-26 17:32:27',NULL),(7,'Agger','$2a$10$GEWQb2jJsQyScutv6c3AjuS3bZIlt98B0KCeki1SNQn8h/RhRyiPG','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IkFnZ2VyIiwic2NvcGUiOiJ1c2VyIiwianRpIjoiN2ZiNjk4YzEtNDZiZi00ZjBlLTkzNDgtNWVmNTA2MDM5YjA1IiwiaWF0IjoxNTI0NzM4ODMyLCJleHAiOjE1MjgzMzg4MzJ9._LCAYbBEg9d6erpBctH0z5eq8tnnHKuHKRtzSTd7qnk',NULL,'icon_default_avatar.png','bg_over.png',NULL,NULL,'2018-04-26 17:33:52',NULL),(8,'Sallad','$2a$10$PJZXUVc4Ck4aptqERJjxzORR.pNjTnQi86i1PkZ4En43VqlmBZIAS','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IlNhbGxhZCIsInNjb3BlIjoidXNlciIsImp0aSI6ImZkNjI2MGZiLTgwMWYtNDVlYi04MDlmLTRhN2MzNWY2NWM5YyIsImlhdCI6MTUyNDc0NDQzNSwiZXhwIjoxNTI4MzQ0NDM1fQ.KpZBtbJWQSjJm3fLjCNM-UpEWXuwTofYzzhDzdo9BOU',NULL,'icon_default_avatar.png','bg_over.png',NULL,NULL,'2018-04-26 19:07:17',NULL),(9,'Vu Thi Ngan','$2a$10$lTckmaazMSkTprvFH2efXOC3sXL3y50QBBw/ZH68ZIaEel24ECkZ2','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IlZ1IFRoaSBOZ2FuIiwic2NvcGUiOiJ1c2VyIiwianRpIjoiMjVhZGJmZjQtZTkwZi00YWNhLTgzZDYtYzc0YzM2NWJlMzcyIiwiaWF0IjoxNTI0NzQ0NDUwLCJleHAiOjE1MjgzNDQ0NTB9.tKD7onHRVVSulVMwBzf9lrvjkjLb1xSsHVOIK8nYLv4',NULL,'icon_default_avatar.png','bg_over.png',NULL,NULL,'2018-04-26 19:07:30',NULL),(10,'Vu Thi Nguyet','$2a$10$nB8UDiA1R4U0os27Zr4P1e7c7/Oh.XK/NFkANp4KTdkjAbJnGcX.O','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IlZ1IFRoaSBOZ3V5ZXQiLCJzY29wZSI6InVzZXIiLCJqdGkiOiI2ZWRmNTY1MC1iZjFiLTRkMTktYTVjYy0xYjAyMmIyNTlmZTEiLCJpYXQiOjE1MjQ3NDQ0NjIsImV4cCI6MTUyODM0NDQ2Mn0.JevmsxQ6uYw-VzIqm5NWC_9l3Z_BJsdoM232UcJHDtc',NULL,'icon_default_avatar.png','bg_over.png',NULL,NULL,'2018-04-26 19:07:42',NULL),(11,'Henry Tang','$2a$10$zEiddNbYFotg1ThbitLlSeuwAljNd/6yKljcwWkXS3qSsJGAabHwq','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IkhlbnJ5IFRhbmciLCJzY29wZSI6InVzZXIiLCJqdGkiOiJjMWQ2ZTZjMC1kODhiLTRjZTItOGRiOS0yZmE2ZTFmOGQ2MDUiLCJpYXQiOjE1MjQ3NDQ0NzUsImV4cCI6MTUyODM0NDQ3NX0.pvKo-D66POnaHe-yc4-yNfzIIpvgIPWBIJss_C0Iinc',NULL,'icon_default_avatar.png','bg_over.png',NULL,NULL,'2018-04-26 19:07:55',NULL),(12,'Viet Nam','$2a$10$WPYH0LtxJYgxZ.RUUm4uyuqo7H1ECgnY0zRygbn.2/v.Gr9Ceo/fq','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IlZpZXQgTmFtIiwic2NvcGUiOiJ1c2VyIiwianRpIjoiNTkwZmM5OTItNTQ3My00ZTRkLWI1NjEtZDc5MGJjZGU5MDM0IiwiaWF0IjoxNTI0NzQ0NDg0LCJleHAiOjE1MjgzNDQ0ODR9.qm9hFXCMs2zlarFKGMXbuwEcnd87tETW1YZ6oiq_k8c',NULL,'icon_default_avatar.png','bg_over.png',NULL,NULL,'2018-04-26 19:08:05',NULL),(13,'Duy Nam','$2a$10$l/3x718d240k3b9JdA8PP.ZcfWPUqm38x/SrXshC4jtm.xkhqBWBm','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IkR1eSBOYW0iLCJzY29wZSI6InVzZXIiLCJqdGkiOiI5OWEzYTQ5NS1mMGI3LTRjMzMtYTNhYS00Y2EzOTcxNDg5YmMiLCJpYXQiOjE1MjQ3NDQ0OTQsImV4cCI6MTUyODM0NDQ5NH0.NlR-QhkKzGZR_0DQeGIqn7G3b-v4IHnM-Edn_-uxFOQ',NULL,'icon_default_avatar.png','bg_over.png',NULL,NULL,'2018-04-26 19:08:14',NULL),(14,'FC Thai Binh','$2a$10$eA.aggrZJDNAAWiUkmJUSOrS81YCbRK2HLT8ClUFXrX6z3RLT65UK','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IkZDIFRoYWkgQmluaCIsInNjb3BlIjoidXNlciIsImp0aSI6IjY4YzZhYmQ3LTdjYzEtNDMyNS05ZWRlLTA2ZjJmNDZlNjZjMyIsImlhdCI6MTUyNDc0NDUwNCwiZXhwIjoxNTI4MzQ0NTA0fQ.w0Nnu1tTPVfg5aPe1IJQ-x2Q4vC4lxa-Bg1YhK6I3y4',NULL,'icon_default_avatar.png','bg_over.png',NULL,NULL,'2018-04-26 19:08:24',NULL),(15,'FC Nam Dinh','$2a$10$PBcui8lbH2SXlEm3saNLvOw9Ib7pAe6drJTFg4csFGAl3GppriPh6','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IkZDIE5hbSBEaW5oIiwic2NvcGUiOiJ1c2VyIiwianRpIjoiZjliZDliZWQtNWIzZi00NDBiLWEzMzktMTMzY2I0N2VjYjIxIiwiaWF0IjoxNTI0NzQ0NTIzLCJleHAiOjE1MjgzNDQ1MjN9.DEhefczCgMXwsMIMuTHOBExWiu1K8XjZJdDMH9apiV8',NULL,'icon_default_avatar.png','bg_over.png',NULL,NULL,'2018-04-26 19:08:44',NULL),(16,'Liverpool Viet Nam','$2a$10$qbRllEs/liXxdLs9BiYxA.NXUcDHS7qIoinRfKHYfunsvnNVrHXNC','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IkxpdmVycG9vbCBWaWV0IE5hbSIsInNjb3BlIjoidXNlciIsImp0aSI6ImY1N2UyOWRhLWQ0MGQtNDVkOC1hMDM3LThiZWRhNDEzMzM2MiIsImlhdCI6MTUyNDc0NDUzMywiZXhwIjoxNTI4MzQ0NTMzfQ.MsEG7TFgLV8GDLG2QhUNNkai-5qsLUFpfiKC4oEnDjE',NULL,'icon_default_avatar.png','bg_over.png',NULL,NULL,'2018-04-26 19:08:54',NULL),(17,'Fan Viet Nam','$2a$10$Lyy9PzPuC1i4wXWddLJdluji.dQnBfCqQqaAeZcoSNxcgBKemYMOi','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IkZhbiBWaWV0IE5hbSIsInNjb3BlIjoidXNlciIsImp0aSI6Ijk5NGJkOTEyLWQyZmMtNDQyNC05N2EyLTY0ZjczYjdjMGNjZSIsImlhdCI6MTUyNDc0NDU0NCwiZXhwIjoxNTI4MzQ0NTQ0fQ.lPLQ-bQuYFXrC-G1luHXjS-WTtkYWeLiEIAq96I1CVo',NULL,'icon_default_avatar.png','bg_over.png',NULL,NULL,'2018-04-26 19:09:05',NULL),(18,'Fan Thai Lan','$2a$10$TYM0glGcc92jK0OR4hp9VuVJQvfls3NkqIMmarkPlbw0TMjSpfXFa','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6IkZhbiBUaGFpIExhbiIsInNjb3BlIjoidXNlciIsImp0aSI6IjRkMWUyMzE0LTBkMmMtNGFjNS1iN2Y3LTUzODI1NzkwMGVhMSIsImlhdCI6MTUyNDc0NDU1MywiZXhwIjoxNTI4MzQ0NTUzfQ.DLIMOs5f04aUFQ6fykapLkBaKK4Rn2O5-NcX2ALYe2Y',NULL,'icon_default_avatar.png','bg_over.png',NULL,NULL,'2018-04-26 19:09:13',NULL),(19,'Meyseyside','$2a$10$dmi4mPaycnFVrxlt73hJFepzdB4LuTijY/iYLrZAQgAvd.PVU.jIa','eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdG9ybXBhdGgiLCJzdWIiOiJtc2lsdmVybWFuIiwibmFtZSI6Ik1leXNleXNpZGUiLCJzY29wZSI6InVzZXIiLCJqdGkiOiIyNDFiZWNmYi1kOGViLTQwNGMtYWFiNi0zZWZjN2I2YzUwOTIiLCJpYXQiOjE1MjQ3NDQ1NjEsImV4cCI6MTUyODM0NDU2MX0.HlcD-bC7OVDcmcJY_yW93SM-zv63ZafrM7w1Xt7uLh4',NULL,'icon_default_avatar.png','bg_over.png',NULL,NULL,'2018-04-26 19:09:21',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-13 13:24:10
