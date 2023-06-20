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
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除 0:未删除,1:已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1670785313762553858,'lai','123456',2,0,0,NULL,NULL),(1670785627169337346,'admain','13456.0',0,0,0,NULL,NULL),(1670785627236446210,'admain','13456.0',1,0,0,NULL,NULL),(1670785627236446211,'admain','13456.0',0,0,0,NULL,NULL),(1670785627303555074,'admain','13456.0',1,0,0,NULL,NULL),(1670785627303555075,'admain','13456.0',0,0,0,NULL,NULL),(1670788336635097090,'admain','13456.0',0,0,0,NULL,NULL),(1670788337218105346,'admain','13456.0',1,0,0,NULL,NULL),(1670788337285214210,'admain','13456.0',0,0,0,NULL,NULL),(1670788337285214211,'admain','13456.0',1,0,0,NULL,NULL),(1670788337285214212,'admain','13456.0',0,0,0,NULL,NULL);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='班级学时表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clazz_period`
--

LOCK TABLES `clazz_period` WRITE;
/*!40000 ALTER TABLE `clazz_period` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='电脑表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer`
--

LOCK TABLES `computer` WRITE;
/*!40000 ALTER TABLE `computer` DISABLE KEYS */;
INSERT INTO `computer` VALUES (1671072351581659138,'小新air7-001',1671069968042647554,1671069907401400322,1,0),(1671072352223387650,'小新air7-002',1671069968042647554,1671069907401400322,2,0),(1671072352223387651,'小新air7-003',1671069968042647554,1671069907401400322,3,0),(1671072352223387652,'小新air7-004',1671069968042647554,1671069907401400322,4,0),(1671072352223387653,'小新air7-005',1671069968042647554,1671069907401400322,5,0),(1671072352286302209,'小新air7-006',1671069968042647554,1671069907401400322,6,0),(1671072352286302210,'小新air7-007',1671069968042647554,1671069907401400322,7,0),(1671072352286302211,'小新air7-008',1671069968042647554,1671069907401400322,8,0),(1671072352286302212,'小新air7-009',1671069968042647554,1671069907401400322,9,0),(1671072352286302213,'小新air7-010',1671069968042647554,1671069907401400322,10,0),(1671072352349216769,'小新air7-011',1671069968042647554,1671069907401400322,11,0),(1671072352349216770,'小新air7-012',1671069968042647554,1671069907401400322,12,0);
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
  `hardware` varchar(255) DEFAULT NULL COMMENT '硬件配置',
  `software` varchar(255) DEFAULT NULL COMMENT '软件配置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='电脑配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer_configuration`
--

LOCK TABLES `computer_configuration` WRITE;
/*!40000 ALTER TABLE `computer_configuration` DISABLE KEYS */;
INSERT INTO `computer_configuration` VALUES (1671069968042647554,'这是小新air7的硬件','这是小新air7的软件');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='上机记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer_record`
--

LOCK TABLES `computer_record` WRITE;
/*!40000 ALTER TABLE `computer_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `computer_record` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机房表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machine_room`
--

LOCK TABLES `machine_room` WRITE;
/*!40000 ALTER TABLE `machine_room` DISABLE KEYS */;
INSERT INTO `machine_room` VALUES (1671069859099795457,'D301','3*4','机房阿姨1',1),(1671069907401400322,'D302','3*5','机房阿姨2',1);
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
  `computer` bigint NOT NULL COMMENT '电脑表主键',
  `start_time` datetime DEFAULT NULL COMMENT '起始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='电脑维修记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maintenance_record`
--

LOCK TABLES `maintenance_record` WRITE;
/*!40000 ALTER TABLE `maintenance_record` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机房不可用表';
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
  `start_time` datetime DEFAULT NULL COMMENT '起始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='节次表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
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
  `sex` int DEFAULT NULL COMMENT '性别',
  `telephone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-20 17:39:13
