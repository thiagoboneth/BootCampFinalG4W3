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
-- Table structure for table `batch_stock`
--

DROP TABLE IF EXISTS `batch_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_stock` (
  `idbatch_number` bigint NOT NULL AUTO_INCREMENT,
  `current_quantity` bigint NOT NULL,
  `current_temperature` float NOT NULL,
  `due_date` date NOT NULL,
  `initial_quantity` bigint NOT NULL,
  `manufacturing_date` date NOT NULL,
  `manufacturing_time` datetime NOT NULL,
  `minimum_temperature` float NOT NULL,
  `idsales_ad` bigint DEFAULT NULL,
  PRIMARY KEY (`idbatch_number`),
  KEY `FKikfod2789p0hk74p9avsvlqe` (`idsales_ad`),
  CONSTRAINT `FKikfod2789p0hk74p9avsvlqe` FOREIGN KEY (`idsales_ad`) REFERENCES `sales_ad` (`idsales_ad`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch_stock`
--

LOCK TABLES `batch_stock` WRITE;
/*!40000 ALTER TABLE `batch_stock` DISABLE KEYS */;
INSERT INTO `batch_stock` VALUES (1,50,-1,'2021-11-12',100,'2021-11-12','2021-11-12 18:22:07',-10,1),(2,290,-1,'2021-11-12',300,'2021-11-12','2021-11-12 18:22:53',-10,2),(3,120,-1,'2021-11-12',200,'2021-11-12','2021-11-12 18:24:30',-10,3),(4,660,-5,'2021-11-12',700,'2021-11-12','2021-11-12 18:26:18',-1,4),(5,1920,-5,'2021-11-12',2000,'2021-11-12','2021-11-12 18:26:59',-1,5),(6,9970,-1,'2021-11-12',10000,'2021-11-12','2021-11-12 18:28:24',-10,6),(7,360,-1,'2021-11-12',400,'2021-11-12','2021-11-12 18:30:46',-5,7),(8,3930,0,'2021-11-12',4000,'2021-11-12','2021-11-12 18:31:51',-5,8),(9,390,28,'2021-11-12',450,'2021-11-12','2021-11-12 18:32:52',20,9),(10,2900,0,'2021-11-12',3000,'2021-11-12','2021-11-12 18:33:26',-5,10);
/*!40000 ALTER TABLE `batch_stock` ENABLE KEYS */;
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
