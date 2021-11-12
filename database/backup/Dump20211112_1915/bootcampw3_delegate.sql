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
-- Table structure for table `delegate`
--

DROP TABLE IF EXISTS `delegate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delegate` (
  `id_delegate` bigint NOT NULL AUTO_INCREMENT,
  `lastname` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `section_code` bigint NOT NULL,
  PRIMARY KEY (`id_delegate`),
  KEY `FKf5gyc1vxuu8khce76eduss2nu` (`section_code`),
  CONSTRAINT `FKf5gyc1vxuu8khce76eduss2nu` FOREIGN KEY (`section_code`) REFERENCES `section` (`section_code`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delegate`
--

LOCK TABLES `delegate` WRITE;
/*!40000 ALTER TABLE `delegate` DISABLE KEYS */;
INSERT INTO `delegate` VALUES (1,'Romanov','Katharina',1),(2,'Motta','Roberta',2),(3,'Luiz','Geraldo',3),(4,'Franca','Valéria',4),(5,'Hailander','Whasingthon',5),(6,'Santos','Silvia',6),(7,'Gaucho','Ronaldinho',7),(8,'Pereira','Carlota',8),(9,'Senna','Ayrton',9),(10,'Marina','Maria',10),(11,'Arcael','Miguel',11),(12,'Silva','Marina',12),(13,'Supply','Supla',13),(14,'Whatson','Emma',14),(15,'Atacante','Zico',15),(16,'Monteiro','Eduarda',16),(17,'Messias','Jair',17),(18,'Almeida','Barbara',18),(19,'Pirlo','Andrea',19),(20,'Bekenbouer','Karollina',20);
/*!40000 ALTER TABLE `delegate` ENABLE KEYS */;
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
