-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: crm
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint NOT NULL COMMENT '主键',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `type` int NOT NULL COMMENT '人员类型 0:系统管理员,1:教师,2:学生',
  `is_disabled` int NOT NULL DEFAULT '0' COMMENT '账户是否禁用 0:未禁用,1:禁用',
  `is_deleted` int DEFAULT '0' COMMENT '是否删除 0:未删除,1:已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='账户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1671753091944677377,'20206101','Ocwr0GUduQ',2,0,0,'2023-06-22 13:32:51','2023-06-22 13:32:51'),(1671753399089364994,'20206102','3sNIc8V06g',2,0,0,'2023-06-22 13:34:05','2023-06-22 13:34:05'),(1671753497517096961,'20206103','3tdfzXaC6S',2,0,0,'2023-06-22 13:34:28','2023-06-22 13:34:28'),(1671753597861625858,'admin','123456',0,0,0,'2023-06-22 13:34:52','2023-06-22 13:34:52'),(1672128636976685058,'20206107','35qcEAwtYj',2,0,1,'2023-06-23 14:25:08','2023-06-23 14:25:08');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clazz_period`
--

DROP TABLE IF EXISTS `clazz_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clazz_period` (
  `id` bigint NOT NULL COMMENT '主键',
  `clazz` varchar(255) DEFAULT NULL COMMENT '班级，例如:202061',
  `time` int DEFAULT NULL COMMENT '学时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='班级学时表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clazz_period`
--

LOCK TABLES `clazz_period` WRITE;
/*!40000 ALTER TABLE `clazz_period` DISABLE KEYS */;
INSERT INTO `clazz_period` VALUES (1671116994923421698,'202061',30),(1671117086845796354,'202062',30),(1671117140058951682,'202063',28),(1671117370951098370,'202064',40),(1671119784856911874,'202065',18),(1671119854016790530,'202066',10),(1671119887067906050,'202067',20);
/*!40000 ALTER TABLE `clazz_period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computer`
--

DROP TABLE IF EXISTS `computer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `computer` (
  `id` bigint NOT NULL COMMENT '主键',
  `number` varchar(255) DEFAULT NULL COMMENT '编号',
  `configuration` bigint DEFAULT NULL COMMENT '电脑配置表主键',
  `machine_room` bigint DEFAULT NULL COMMENT '隶属机房',
  `camera_stand` int DEFAULT NULL COMMENT '机位',
  `state` int DEFAULT NULL COMMENT '状态，0:空闲,1:使用中,2:维修中',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='电脑表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer`
--

LOCK TABLES `computer` WRITE;
/*!40000 ALTER TABLE `computer` DISABLE KEYS */;
INSERT INTO `computer` VALUES (1671403979563069442,'小新air17-001',1671148923576041473,1671125365932089345,1,1),(1671405542528421890,'小新air17-002',1671149026403553282,1671125365932089345,2,1),(1671749653882077185,'小新air17-003',1671148923576041473,1671125365932089345,3,2),(1671762632149172225,'小新air17-004',1671148923576041473,1671125365932089345,4,1),(1671848734356914178,'小新air17-005',1671148923576041473,1671125365932089345,5,1),(1671849005082460161,'小新air17-006',1671148923576041473,1671125365932089345,6,1);
/*!40000 ALTER TABLE `computer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computer_configuration`
--

DROP TABLE IF EXISTS `computer_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `computer_configuration` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '配置名称',
  `hardware` varchar(255) DEFAULT NULL COMMENT '硬件配置',
  `software` varchar(255) DEFAULT NULL COMMENT '软件配置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='电脑配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer_configuration`
--

LOCK TABLES `computer_configuration` WRITE;
/*!40000 ALTER TABLE `computer_configuration` DISABLE KEYS */;
INSERT INTO `computer_configuration` VALUES (1671148923576041473,'配置一','CPU','IDEA'),(1671149026403553282,'配置二','AMD','WebStrome'),(1671152222278672386,'配置三','IDEA','AMD'),(1671152276741709825,'配置四','IDEA','AMD'),(1671152387655884801,'配置五','IDEA','AMD'),(1671152504429502466,'配置六','sofaware','IDEA'),(1671152568606547970,'配置七','软件','硬件');
/*!40000 ALTER TABLE `computer_configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computer_record`
--

DROP TABLE IF EXISTS `computer_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `computer_record` (
  `id` bigint NOT NULL COMMENT '主键',
  `student` bigint NOT NULL COMMENT '学生表主键',
  `computer` bigint NOT NULL COMMENT '电脑表主键',
  `start_time` datetime DEFAULT NULL COMMENT '起始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='上机记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer_record`
--

LOCK TABLES `computer_record` WRITE;
/*!40000 ALTER TABLE `computer_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `computer_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_log`
--

DROP TABLE IF EXISTS `login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_log` (
  `id` bigint NOT NULL COMMENT '主键',
  `username` varchar(255) DEFAULT NULL COMMENT '电脑主键',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '起始时间',
  `login_time` datetime DEFAULT NULL COMMENT '结束时间',
  `status` int DEFAULT NULL COMMENT '备注，0登录失败1登录成功',
  `info` varchar(255) DEFAULT NULL COMMENT '其它说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_log`
--

LOCK TABLES `login_log` WRITE;
/*!40000 ALTER TABLE `login_log` DISABLE KEYS */;
INSERT INTO `login_log` VALUES (1671370239356952577,'张三','10.1.88.4','2023-06-22 13:32:51',1,'登陆成功！','2023-06-21 12:11:32','2023-06-21 12:11:32'),(1671370416406900737,'李四','10.1.88.4','2023-06-22 13:32:52',1,'登陆成功！','2023-06-21 12:12:14','2023-06-21 12:12:14');
/*!40000 ALTER TABLE `login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `machine_room`
--

DROP TABLE IF EXISTS `machine_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `machine_room` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '机房名 例：D301',
  `pattern` varchar(255) DEFAULT NULL COMMENT '排列模式 例如2*3，代表2行3列',
  `principal` varchar(255) DEFAULT NULL COMMENT '负责人',
  `state` int DEFAULT NULL COMMENT '状态 0:未开放,1:开放',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='机房表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machine_room`
--

LOCK TABLES `machine_room` WRITE;
/*!40000 ALTER TABLE `machine_room` DISABLE KEYS */;
INSERT INTO `machine_room` VALUES (1671125365932089345,'D301','3*4','张凯',1),(1671125451219111938,'D302','6*4','李凯',1),(1671125517409329153,'D303','5*4','王五',1),(1671125570169470977,'D304','5*5','李欣',1),(1671125667045388290,'D305','5*6','王立新',1),(1671125774939705345,'D306','5*4','张保',1),(1671127214512918530,'D307','3*4','张凯鲁',1),(1671165545846038529,'D308','4*3','张三',1),(1671165643028062209,'D309','4*4','李四',1),(1671399895237468162,'E201','2*3','赖心豪',1),(1671400293109145601,'D302','3*5','机房阿姨2',1);
/*!40000 ALTER TABLE `machine_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maintenance_record`
--

DROP TABLE IF EXISTS `maintenance_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maintenance_record` (
  `id` bigint NOT NULL COMMENT '主键',
  `computer` varchar(255) NOT NULL COMMENT '电脑表主键',
  `start_time` datetime DEFAULT NULL COMMENT '起始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='电脑维修记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maintenance_record`
--

LOCK TABLES `maintenance_record` WRITE;
/*!40000 ALTER TABLE `maintenance_record` DISABLE KEYS */;
INSERT INTO `maintenance_record` VALUES (1671412022988148737,'1671403979563069442','2023-06-14 00:00:00','2023-06-22 00:00:00','','2023-06-21 14:57:34','2023-06-21 14:57:34'),(1671413269069729793,'1671405542528421890','2023-06-01 00:00:00','2023-06-08 00:00:00','无','2023-06-21 15:02:31','2023-06-22 14:16:46');
/*!40000 ALTER TABLE `maintenance_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reject_record`
--

DROP TABLE IF EXISTS `reject_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reject_record` (
  `id` bigint NOT NULL COMMENT '主键',
  `machine_room` bigint DEFAULT NULL COMMENT '机房表主键',
  `section` bigint DEFAULT NULL COMMENT '节次表主键',
  `time` datetime DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='机房不可用表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reject_record`
--

LOCK TABLES `reject_record` WRITE;
/*!40000 ALTER TABLE `reject_record` DISABLE KEYS */;
INSERT INTO `reject_record` VALUES (1671754887614300162,1671125365932089345,1671192939400392706,'2023-06-22 00:00:00'),(1671754938231160834,1671125365932089345,1671385016191836161,'2023-06-22 00:00:00'),(1671754978811052033,1671125365932089345,1671385223918936065,'2023-06-22 00:00:00'),(1671755011807641601,1671125365932089345,1671385411668566017,'2023-06-22 00:00:00'),(1671755048453275650,1671125451219111938,1671192939400392706,'2023-06-22 00:00:00'),(1671755891839729666,1671125451219111938,1671385016191836161,'2023-06-22 00:00:00'),(1671755930402160641,1671125451219111938,1671385223918936065,'2023-06-22 00:00:00'),(1671755965147774977,1671125451219111938,1671385411668566017,'2023-06-22 00:00:00'),(1671756056201920514,1671125451219111938,1671385223918936065,'2023-06-22 00:00:00');
/*!40000 ALTER TABLE `reject_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `section` (
  `id` bigint NOT NULL COMMENT '主键',
  `number` varchar(255) DEFAULT NULL COMMENT '节次，例如:1',
  `start_time` time DEFAULT NULL COMMENT '起始时间',
  `end_time` time DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='节次表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (1671192939400392706,'一二节次','08:00:00','09:40:00'),(1671385016191836161,'三四节次','10:00:00','11:40:00'),(1671385223918936065,'五六节次','14:00:00','15:40:00'),(1671385411668566017,'七八节次','16:00:00','17:40:00'),(1671726904933482497,'九十节次','19:00:00','20:40:00');
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` bigint NOT NULL COMMENT '主键',
  `account` bigint NOT NULL COMMENT '账户主键',
  `student_no` varchar(255) NOT NULL COMMENT '学号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `clazz` varchar(255) DEFAULT NULL COMMENT '班级编号，例202061',
  `sex` int DEFAULT NULL COMMENT '性别，0:女,1:男',
  `telephone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='学生表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `before_delete_student` BEFORE DELETE ON `student` FOR EACH ROW BEGIN
    -- 删除相关上机记录
    DELETE FROM computer_record WHERE student = OLD.id;
END */;;
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

-- Dump completed on 2023-06-23 14:29:54
