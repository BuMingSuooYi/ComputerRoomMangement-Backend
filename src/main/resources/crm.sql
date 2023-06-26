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
INSERT INTO `account` VALUES (1673280682400501761,'20206106','e10adc3949ba59abbe56e057f20f883e',2,0,0,'2023-06-26 18:42:57','2023-06-26 18:42:57'),(1673280683356803074,'20206107','e10adc3949ba59abbe56e057f20f883e',2,0,0,'2023-06-26 18:42:58','2023-06-26 18:42:58'),(1673280683356803076,'20206108','e10adc3949ba59abbe56e057f20f883e',2,0,0,'2023-06-26 18:42:58','2023-06-26 18:42:58'),(1673280683423911939,'20201101','e10adc3949ba59abbe56e057f20f883e',2,0,0,'2023-06-26 18:42:58','2023-06-26 18:42:58'),(1673281056133959681,'admain','e10adc3949ba59abbe56e057f20f883e',0,0,0,'2023-06-26 18:44:27','2023-06-26 18:44:27'),(1673281056268177410,'admain','e10adc3949ba59abbe56e057f20f883e',1,0,0,'2023-06-26 18:44:27','2023-06-26 18:44:27');
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
INSERT INTO `clazz_period` VALUES (1673316782527561729,'202061班',60),(1673316828694265858,'202011班',50);
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
INSERT INTO `computer` VALUES (1673309290317860866,'小新air7-001',1673308046308265985,1673284466090561537,1,0),(1673309290317860867,'小新air7-002',1673308046308265985,1673284466090561537,2,0),(1673309290384969729,'小新air7-003',1673308046308265985,1673284466090561537,3,0),(1673309290384969730,'小新air7-004',1673308046308265985,1673284466090561537,4,0),(1673309290384969731,'小新air7-005',1673308046308265985,1673284466090561537,5,0),(1673309290384969732,'小新air7-006',1673308046308265985,1673284466090561537,6,0),(1673309290447884289,'小新air7-007',1673308046308265985,1673284466090561537,7,0),(1673309290447884290,'小新air7-008',1673308046308265985,1673284466090561537,8,0),(1673309290447884291,'小新air7-009',1673308046308265985,1673284466090561537,9,0),(1673309290447884292,'小新air7-010',1673308046308265985,1673284466090561537,10,0),(1673309290510798850,'小新air7-011',1673308046308265985,1673284466090561537,11,0),(1673309290510798851,'小新air7-012',1673308046308265985,1673284466090561537,12,0),(1673309290510798852,'小新air7-013',1673308046308265985,1673284466090561537,13,0),(1673309290510798853,'小新air7-014',1673308046308265985,1673284466090561537,14,0),(1673309290510798854,'小新air7-015',1673308046308265985,1673284466090561537,15,0),(1673309290577907713,'小新air7-016',1673308046308265985,1673284466090561537,16,0),(1673309290577907714,'小新air7-017',1673308046308265985,1673284466090561537,17,0),(1673309290577907715,'小新air7-018',1673308046308265985,1673284466090561537,18,0),(1673309290577907716,'小新air7-019',1673308046308265985,1673284466090561537,19,0),(1673309290577907717,'小新air7-020',1673308046308265985,1673284466090561537,20,0),(1673309290645016578,'小新air7-021',1673308046308265985,1673284466090561537,21,0),(1673309290645016579,'小新air7-022',1673308046308265985,1673284466090561537,22,0),(1673309290645016580,'小新air7-023',1673308046308265985,1673284466090561537,23,0),(1673309290645016581,'小新air7-024',1673308046308265985,1673284466090561537,24,0),(1673309290645016582,'小新air7-025',1673308046308265985,1673284466090561537,25,0),(1673309290712125441,'拯救者-001',1673308373921157122,1673284609669976066,1,0),(1673309290712125442,'拯救者-002',1673308373921157122,1673284609669976066,2,0),(1673309290712125443,'拯救者-003',1673308373921157122,1673284609669976066,3,0),(1673309290712125444,'拯救者-004',1673308373921157122,1673284609669976066,4,0),(1673309290712125445,'拯救者-005',1673308373921157122,1673284609669976066,5,0),(1673309290775040001,'拯救者-006',1673308373921157122,1673284609669976066,6,0),(1673309290775040002,'拯救者-007',1673308373921157122,1673284609669976066,7,0),(1673309290775040003,'拯救者-008',1673308373921157122,1673284609669976066,8,0),(1673309290775040004,'拯救者-009',1673308373921157122,1673284609669976066,9,0),(1673309290837954561,'拯救者-010',1673308373921157122,1673284609669976066,10,0),(1673309290837954562,'拯救者-011',1673308373921157122,1673284896497455105,1,0),(1673309290837954563,'拯救者-012',1673308373921157122,1673284896497455105,2,0),(1673309290837954564,'拯救者-013',1673308373921157122,1673284896497455105,3,0),(1673309290837954565,'拯救者-014',1673308373921157122,1673284896497455105,4,0),(1673309290900869122,'拯救者-015',1673308373921157122,1673284896497455105,5,0),(1673309290900869123,'拯救者-016',1673308373921157122,1673284896497455105,6,0),(1673309290900869124,'拯救者-017',1673308373921157122,1673284896497455105,7,0),(1673309290900869125,'拯救者-018',1673308373921157122,1673284896497455105,8,0),(1673309290967977985,'拯救者-019',1673308373921157122,1673284896497455105,9,0),(1673309290967977986,'拯救者-020',1673308373921157122,1673284896497455105,10,0);
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
INSERT INTO `computer_configuration` VALUES (1673308046308265985,'小新','这是小新air7的硬件','这是小新air7的软件'),(1673308373921157122,'拯救者','这是拯救者的硬件','这是拯救者的软件');
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
INSERT INTO `computer_record` VALUES (1673309764043526146,1673280683356803075,1673309290317860866,'2023-06-26 20:38:31','2023-06-26 20:40:03','2023-06-26 20:38:31','2023-06-26 20:40:03'),(1673309857475842050,1673280683356803073,1673309290317860867,'2023-06-26 20:38:53','2023-06-26 20:40:05','2023-06-26 20:38:53','2023-06-26 20:40:05'),(1673309907501305858,1673280683423911938,1673309290384969729,'2023-06-26 20:39:05','2023-06-26 20:40:07','2023-06-26 20:39:05','2023-06-26 20:40:07'),(1673310019854127106,1673280683423911940,1673309290384969730,'2023-06-26 20:39:32','2023-06-26 20:40:09','2023-06-26 20:39:32','2023-06-26 20:40:09'),(1673310019854127107,1673280683423911940,1673309290384969730,'2023-06-25 14:00:32','2023-06-25 15:00:32','2023-06-25 14:00:32','2023-06-25 15:00:32'),(1673311109416865793,1673280683356803075,1673309290712125441,'2023-06-26 20:43:52','2023-06-26 21:23:55','2023-06-26 20:43:52','2023-06-26 20:43:55');
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
INSERT INTO `login_log` VALUES (1673283149817303041,'admain','127.0.0.1','2023-06-26 18:52:46',1,'登录成功','2023-06-26 18:52:46','2023-06-26 18:52:46'),(1673285381996531714,'admain','127.0.0.1','2023-06-26 19:01:38',1,'登录成功','2023-06-26 19:01:38','2023-06-26 19:01:38'),(1673286778582323202,'admain','127.0.0.1','2023-06-26 19:07:11',1,'登录成功','2023-06-26 19:07:11','2023-06-26 19:07:11'),(1673286850825015297,'20206107','127.0.0.1','2023-06-26 19:07:28',1,'登录成功','2023-06-26 19:07:28','2023-06-26 19:07:28'),(1673309439534419970,'admain','127.0.0.1','2023-06-26 20:37:14',1,'登录成功','2023-06-26 20:37:14','2023-06-26 20:37:14'),(1673316135950434306,'admian','127.0.0.1','2023-06-26 21:03:50',0,'账户不存在','2023-06-26 21:03:50','2023-06-26 21:03:50'),(1673316166795345922,'admain','127.0.0.1','2023-06-26 21:03:58',0,'密码错误','2023-06-26 21:03:58','2023-06-26 21:03:58'),(1673316190996480002,'admain','127.0.0.1','2023-06-26 21:04:03',1,'登录成功','2023-06-26 21:04:03','2023-06-26 21:04:03');
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
INSERT INTO `machine_room` VALUES (1673284466090561537,'D301','5*5','张一白',1),(1673284609669976066,'D302','4*3','义实嚄',1),(1673284896497455105,'D303','4*7','张一白',1),(1673285002063892481,'智能机房','3*3','刘善本',0);
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
INSERT INTO `maintenance_record` VALUES (1673314453296332801,'1673309290317860866','2023-06-24 20:17:09','2023-06-24 20:47:09','测试电脑','2023-06-26 20:57:09','2023-06-26 20:57:09');
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
INSERT INTO `section` VALUES (1673283471272955906,'1','08:00:00','09:40:00'),(1673283657760100354,'2','10:00:00','11:40:00'),(1673283826937352193,'3','14:00:00','15:40:00'),(1673283972290957314,'4','16:00:00','17:40:00'),(1673284102901583873,'5','19:00:00','20:40:00'),(1673284227757625346,'6','21:00:00','22:40:00');
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
INSERT INTO `student` VALUES (1673280683356803073,1673280682400501761,'20206106','韩健','202061班',1,'13820206106'),(1673280683356803075,1673280683356803074,'20206107','赖心豪','202061班',1,'18120206107'),(1673280683423911938,1673280683356803076,'20206108','刘稻裕','202061班',1,'13420206108'),(1673280683423911940,1673280683423911939,'20201101','张黎','202011班',0,'12345678912');
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

-- Dump completed on 2023-06-26 21:08:48
