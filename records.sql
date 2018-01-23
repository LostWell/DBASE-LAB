CREATE DATABASE  IF NOT EXISTS `records` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `records`;
-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: records
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
  `classcode` varchar(45) NOT NULL,
  `time` varchar(45) NOT NULL,
  `day` varchar(45) NOT NULL,
  `subjid` varchar(45) NOT NULL,
  PRIMARY KEY (`classcode`),
  KEY `subjid_idx` (`subjid`),
  CONSTRAINT `subjid` FOREIGN KEY (`subjid`) REFERENCES `subject` (`subjid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES ('9329','11:00 AM-12:30 AM','WF','CS 311A'),('9330A','03:00 PM-04:00 PM','WS','ICS 311'),('9330B','02:30 PM-04:00 PM','TF','ICS 311L'),('9331A','01:00 PM-02:00 PM','TF','ICS 312'),('9331B','04:00 PM-05:30 PM','WS','ICS 312L'),('9332','08:00 AM-09:00 AM','TTHS','ENGL 7'),('9333','11:00 AM-12:00 PM','TF','IT 311'),('9334A','09:00 AM-10:00 AM','WS','ICS 321'),('9334B','09:30 AM-11:00 AM','TTHS','ICS 321L'),('9341','11:00 AM-12:30 PM','WS','CS 311A'),('9358','03:00 PM-04:00 PM','WF','ENGL 7'),('9360A','01:00 PM-02:00 PM','TF','ICS 312'),('9360B','04:00 PM-05:30 PM','WF','ICS 312L'),('9362','02:30 PM-04:00 PM','TTHS','IT 311'),('9381','02:00 PM-03:00 PM','WF','PHYS 2B');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enroll`
--

DROP TABLE IF EXISTS `enroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enroll` (
  `classcode` varchar(45) NOT NULL,
  `idno` int(11) NOT NULL,
  `datesubmitted` varchar(45) NOT NULL,
  KEY `idno_idx` (`idno`),
  KEY `classcode_idx` (`classcode`),
  CONSTRAINT `classcode` FOREIGN KEY (`classcode`) REFERENCES `class` (`classcode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idno` FOREIGN KEY (`idno`) REFERENCES `students` (`idno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enroll`
--

LOCK TABLES `enroll` WRITE;
/*!40000 ALTER TABLE `enroll` DISABLE KEYS */;
INSERT INTO `enroll` VALUES ('9381',2151234,'08/03/2017'),('9341',2154782,'08/04/2017'),('9362',2161332,'08/05/2017'),('9362',2161548,'08/05/2017'),('9358',2164587,'08/06/2017'),('9332',2166253,'01/08/2018'),('9331A',2166253,'01/09/2018');
/*!40000 ALTER TABLE `enroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `idno` int(11) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `midInitial` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `contactno` varchar(16) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (2151234,'Tadeja','Alessandra','K','F','+63 935 870 1354','2151234@slu.edu.ph'),(2154782,'Gonzales','Jheyanne','O','F','+63 915 487 2650','2154782@slu.edu.ph'),(2161332,'Bautista','Aian','J','M','+63 977 802 6814','2161332@slu.edu.ph'),(2161548,'Cruz','Shyra','G','F','+63 905 916 7644','2161548@slu.edu.ph'),(2164587,'Aquino','Eyvind','M','M','+63 905 631 2487','2164587@slu.edu.ph'),(2165457,'Ramos','Jairuss','B','M','+63 995 872 1430','2165457@slu.edu.ph'),(2166087,'Abiera','Gerichelle','R','F','+63 999 215 4803','2166087@slu.edu.ph'),(2166253,'Lagasca','Louel','D','M','+63 915 205 7397','2166253@slu.edu.ph'),(2167845,'Reyes','Justine','H','F','+63 907 262 8541','2167845@slu.edu.ph');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `subjid` varchar(45) NOT NULL,
  `title` longtext NOT NULL,
  `units` int(11) NOT NULL,
  PRIMARY KEY (`subjid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES ('CS 311A','Object Oriented Programming',3),('ENGL 7','Technical Writing',3),('ICS 311','Operating Systems',2),('ICS 311L','Operating Systems',1),('ICS 312','Programming Applications',2),('ICS 312L','Programming Applications',1),('ICS 321','Database Management Systems',2),('ICS 321L','Database Management Systems',1),('IT 311','Systems Analysis and Design',3),('PHYS 2B','Electromagnetism and Electricity',3);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-23 21:50:22
