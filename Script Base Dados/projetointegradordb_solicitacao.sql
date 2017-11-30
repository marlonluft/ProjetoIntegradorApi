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
-- Table structure for table `solicitacao`
--

DROP TABLE IF EXISTS `solicitacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solicitacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idusuario` int(11) NOT NULL,
  `cidade_origem` varchar(255) NOT NULL,
  `uf_origem` varchar(2) NOT NULL,
  `cidade_destino` varchar(255) NOT NULL,
  `uf_destino` varchar(2) NOT NULL,
  `data_ida` datetime NOT NULL,
  `data_volta` datetime NOT NULL,
  `motivo` varchar(255) NOT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `justificativa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `solicitacao_ibfk_1` (`idusuario`),
  CONSTRAINT `solicitacao_ibfk_1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitacao`
--

LOCK TABLES `solicitacao` WRITE;
/*!40000 ALTER TABLE `solicitacao` DISABLE KEYS */;
INSERT INTO `solicitacao` VALUES (45,32,'AC','AC','Alagoas','AL','2017-11-04 00:00:00','2017-11-04 00:00:00','Evento','Marcel é chato','5','pô cara, tá demais'),(47,32,'AM','AM','BA','BA','2017-11-04 00:00:00','2017-11-05 00:00:00','Workshop','Data 04/11/2017 e 05/11/2017 teste bug custos','5','Falhou decimal'),(48,32,'AC','AC','AL','AL','2017-11-04 00:00:00','2017-11-04 00:00:00','Reunião','','5','java'),(49,32,'aa','AL','bb','AL','2017-11-04 00:00:00','2017-11-04 00:00:00','Evento','','6',''),(55,32,'solicitação do 77777777777','PE','77777777777','PI','2017-11-06 00:00:00','2017-11-06 00:00:00','Workshop','asdasdas','5','aaa'),(56,32,'Blumenau','AC','Indaial','AL','2017-11-09 00:00:00','2017-11-23 00:00:00','Apresentação','','5','eu quero assim obvio'),(57,13,'Blumenau','SC','São Paulo','SP','2017-12-04 00:00:00','2017-12-08 00:00:00','Outro','Homologação com cliente','0',''),(58,13,'Blumenau','SC','São Paulo','SP','2017-12-04 00:00:00','2017-12-08 00:00:00','Reunião','Homologação Cliente','0',''),(59,13,'Blumenau','SC','São PAulo','SP','2017-12-04 00:00:00','2017-12-08 00:00:00','Reunião','homologaão','0',''),(61,37,'Colaborador do Dézio','PE','Colaborador do Dézio','SC','2017-11-29 00:00:00','2017-11-30 00:00:00','Reunião','Colaborador do Dézio','0',''),(62,37,'Colaborador do Dézio 2','PA','Colaborador do Dézio 2','PE','2017-11-29 00:00:00','2018-11-01 00:00:00','Apresentação','Colaborador do Dézio 2','6',''),(64,38,'Colabortador do Zéc 1','PE','Colabortador do Zéc 1','TO','2017-11-29 00:00:00','2017-11-29 00:00:00','Workshop','Colabortador do Zéc 1','5','ah para para'),(65,37,'asdsadsa','MT','dasdsad','PE','2017-11-29 00:00:00','2017-11-29 00:00:00','Workshop','dasdsadsa','2','Eu quero assim');
/*!40000 ALTER TABLE `solicitacao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-30 18:09:36
