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
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `perfil` int(11) NOT NULL,
  `cod_setor` int(11) DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Setor_idx` (`cod_setor`),
  CONSTRAINT `FK_Setor` FOREIGN KEY (`cod_setor`) REFERENCES `setor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (13,'José Adm','teste2@admin.com','123456',2,9,'123.456.789-12'),(27,'Marcel Ges','asdasd@asasda.com','123456',3,NULL,'564.646.464-46'),(29,'Carlos Ges','novaschin@brahma.com','123456',3,NULL,'999.999.999-99'),(31,'Maria Colab','cola@roaksda.com.br','123456',1,NULL,'888.888.888-88'),(32,'Pelé Colab','cola@bnor.aocm.br','123456',2,13,'777.777.777-77'),(33,'Administrador 2','adm2@blumeterra.com.br','123456',1,NULL,'075.616.539-30'),(35,'Dézio','teste@terra.com.br','123456',3,NULL,'111.111.111-11'),(36,'Zéc','ze@terra.com.br','123456',3,NULL,'222.222.222-22'),(37,'Colaborador do Dézio','cola@terara.com.br','123456',2,16,'333.333.333-33'),(38,'Colaborador do Zéc','rsaeras@terepok.com.br','123456',2,15,'444.444.444-44'),(39,'dasdas','dsada@telrke.com.br','123456',2,15,'999.999.999-98');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-30 18:09:40
