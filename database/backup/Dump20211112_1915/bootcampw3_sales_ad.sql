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
-- Table structure for table `sales_ad`
--

DROP TABLE IF EXISTS `sales_ad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_ad` (
  `idsales_ad` bigint NOT NULL AUTO_INCREMENT,
  `maximum_temperature` float NOT NULL,
  `minimum_temperature` float NOT NULL,
  `price` double DEFAULT NULL,
  `volume` float NOT NULL,
  `idproduct` bigint NOT NULL,
  `idseller` bigint NOT NULL,
  PRIMARY KEY (`idsales_ad`),
  KEY `FKkxogjikp5mhrhejv4o4ebbgus` (`idproduct`),
  KEY `FKapue32sbbgm0eabxbe17lxui8` (`idseller`),
  CONSTRAINT `FKapue32sbbgm0eabxbe17lxui8` FOREIGN KEY (`idseller`) REFERENCES `seller` (`idseller`),
  CONSTRAINT `FKkxogjikp5mhrhejv4o4ebbgus` FOREIGN KEY (`idproduct`) REFERENCES `products` (`idproduct`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_ad`
--

LOCK TABLES `sales_ad` WRITE;
/*!40000 ALTER TABLE `sales_ad` DISABLE KEYS */;
INSERT INTO `sales_ad` VALUES (1,0,-5,10,1,1,1),(2,0,-5,8,1,2,2),(3,0,-5,8,1,3,3),(4,-1,-10,100,1,4,4),(5,-1,-10,15,1,5,1),(6,0,-5,5,1,6,2),(7,0,-5,50,1,7,3),(8,20,0,7,1,8,4),(9,35,20,35,1,9,1),(10,20,0,8,1,10,2);
/*!40000 ALTER TABLE `sales_ad` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-12 19:16:10
