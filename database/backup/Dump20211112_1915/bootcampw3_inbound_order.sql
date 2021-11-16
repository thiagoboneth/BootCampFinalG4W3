-- MySQL dump 10.13  Distrib 8.0.26, for macos11 (x86_64)
--
-- Host: 127.0.0.1    Database: bootcampw3
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `inbound_order`
--

DROP TABLE IF EXISTS `inbound_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inbound_order` (
  `id_inbound_order` bigint NOT NULL AUTO_INCREMENT,
  `order_date` date NOT NULL,
  `idbatch_number` bigint NOT NULL,
  `section_code` bigint NOT NULL,
  PRIMARY KEY (`id_inbound_order`),
  KEY `FKktn0o5nksuki2vk2op5da2k60` (`idbatch_number`),
  KEY `FKf3ycfu0pg4auw1tu7mee2rkbe` (`section_code`),
  CONSTRAINT `FKf3ycfu0pg4auw1tu7mee2rkbe` FOREIGN KEY (`section_code`) REFERENCES `section` (`section_code`),
  CONSTRAINT `FKktn0o5nksuki2vk2op5da2k60` FOREIGN KEY (`idbatch_number`) REFERENCES `batch_stock` (`idbatch_number`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inbound_order`
--

LOCK TABLES `inbound_order` WRITE;
/*!40000 ALTER TABLE `inbound_order` DISABLE KEYS */;
INSERT INTO `inbound_order` VALUES (1,'2021-11-12',1,3),(2,'2021-11-12',2,1),(3,'2021-11-12',3,1),(4,'2021-11-12',4,2),(5,'2021-11-12',5,2),(6,'2021-11-12',6,3),(7,'2021-11-12',7,2),(8,'2021-11-12',8,3),(9,'2021-11-12',9,14),(10,'2021-11-12',10,19);
/*!40000 ALTER TABLE `inbound_order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-12 19:16:11
