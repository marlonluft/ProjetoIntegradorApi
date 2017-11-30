CREATE DATABASE  IF NOT EXISTS `projetointegradordb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `projetointegradordb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: projetointegradorbd.mysql.database.azure.com    Database: projetointegradordb
-- ------------------------------------------------------
-- Server version	5.6.26.0

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
-- Table structure for table `custos`
--

DROP TABLE IF EXISTS `custos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `custos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idsolicitacao` int(11) NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `valor_solic` float NOT NULL,
  `valor_prest` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `custos_ibfk_1` (`idsolicitacao`),
  CONSTRAINT `custos_ibfk_1` FOREIGN KEY (`idsolicitacao`) REFERENCES `solicitacao` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custos`
--

LOCK TABLES `custos` WRITE;
/*!40000 ALTER TABLE `custos` DISABLE KEYS */;
INSERT INTO `custos` VALUES (92,45,'0',1,55,55),(93,45,'3',2,0,13),(95,47,'0',1,500.99,500),(96,48,'0',10,1.99,500),(97,48,'0',1,0,1000),(98,49,'0',1,500.98,500.99),(99,49,'0',1,0,1000.99),(110,55,'1',1,111.11,0),(114,56,'1',1,50.99,20),(115,56,'0',2,299.5,0),(116,56,'2',2,0,25.5),(117,59,'0',2,1500,NULL),(119,61,'1',1,122.33,NULL),(120,61,'1',1,331.25,NULL),(121,61,'1',1,1234.56,NULL),(122,62,'1',9,1111.11,100),(123,64,'1',4,21312.3,213.12),(124,65,'2',3,11.11,0),(125,62,'1',2,0,2),(126,62,'1',2,0,2);
/*!40000 ALTER TABLE `custos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-30 18:09:23
