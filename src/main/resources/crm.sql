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
  `id` int NOT NULL COMMENT '主键',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `type` int DEFAULT NULL COMMENT '人员类型 0:系统管理员,1:教师,2:学生',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clazz_period`
--

DROP TABLE IF EXISTS `clazz_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clazz_period` (
  `id` int NOT NULL COMMENT '主键',
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
  `id` int NOT NULL COMMENT '主键',
  `number` varchar(255) DEFAULT NULL COMMENT '编号',
  `configuration` int DEFAULT NULL COMMENT '电脑配置表主键',
  `machine_room` int DEFAULT NULL COMMENT '隶属机房',
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
/*!40000 ALTER TABLE `computer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computer_configuration`
--

DROP TABLE IF EXISTS `computer_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `computer_configuration` (
  `id` int NOT NULL COMMENT '主键',
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
/*!40000 ALTER TABLE `computer_configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computer_record`
--

DROP TABLE IF EXISTS `computer_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `computer_record` (
  `id` int NOT NULL COMMENT '主键',
  `student` int DEFAULT NULL COMMENT '学生表主键',
  `computer` int DEFAULT NULL COMMENT '电脑表主键',
  `start_time` datetime DEFAULT NULL COMMENT '起始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
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
  `id` int NOT NULL COMMENT '主键',
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
/*!40000 ALTER TABLE `machine_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maintenance_record`
--

DROP TABLE IF EXISTS `maintenance_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maintenance_record` (
  `id` int NOT NULL COMMENT '主键',
  `computer` varchar(255) DEFAULT NULL COMMENT '电脑表主键',
  `start_time` datetime DEFAULT NULL COMMENT '起始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
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
  `id` int NOT NULL COMMENT '主键',
  `machine_room` int DEFAULT NULL COMMENT '机房表主键',
  `time` datetime DEFAULT NULL COMMENT '开始时间',
  `section` varchar(255) DEFAULT NULL COMMENT '节次表主键',
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
  `id` int NOT NULL COMMENT '主键',
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
  `id` int NOT NULL COMMENT '主键',
  `account` int DEFAULT NULL COMMENT '账户主键',
  `student_no` varchar(255) DEFAULT NULL COMMENT '学号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `clazz` varchar(255) DEFAULT NULL COMMENT '班级编号，例202061',
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

-- Dump completed on 2023-06-13 14:19:19
