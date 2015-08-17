-- MySQL dump 10.13  Distrib 5.6.19, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: restaurantdb
-- ------------------------------------------------------
-- Server version	5.6.19-0ubuntu0.14.04.1

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
-- Table structure for table `confirmations`
--

DROP TABLE IF EXISTS `confirmations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `confirmations` (
  `primarykey` int(11) NOT NULL,
  `reservationfk` int(11) NOT NULL,
  `seatingfk` int(11) NOT NULL,
  `timeslotfk` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`primarykey`),
  UNIQUE KEY `primarykey_UNIQUE` (`primarykey`),
  KEY `reservationforeingkey_idx` (`reservationfk`),
  KEY `seatingforeignkey_idx` (`seatingfk`),
  KEY `timeslotforeignkey_idx` (`timeslotfk`),
  CONSTRAINT `reservationforeingkey` FOREIGN KEY (`reservationfk`) REFERENCES `request_reservation` (`primaykey`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `seatingforeignkey` FOREIGN KEY (`seatingfk`) REFERENCES `seating` (`primarykey`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `timeslotforeignkey` FOREIGN KEY (`timeslotfk`) REFERENCES `time_slot` (`primarykey`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `confirmations`
--

LOCK TABLES `confirmations` WRITE;
/*!40000 ALTER TABLE `confirmations` DISABLE KEYS */;
/*!40000 ALTER TABLE `confirmations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ownerprofile`
--

DROP TABLE IF EXISTS `ownerprofile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ownerprofile` (
  `primarykey` int(11) NOT NULL,
  `restaurantname` varchar(45) NOT NULL,
  `address` varchar(255) NOT NULL,
  `openingtime` varchar(45) NOT NULL,
  `closingtime` varchar(45) NOT NULL,
  PRIMARY KEY (`primarykey`),
  UNIQUE KEY `primarykey_UNIQUE` (`primarykey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ownerprofile`
--

LOCK TABLES `ownerprofile` WRITE;
/*!40000 ALTER TABLE `ownerprofile` DISABLE KEYS */;
INSERT INTO `ownerprofile` VALUES (343143,'Taj Indian Cuisine','999 wst walnut street Indianapolis, Indiana 46202','11 am','9 pm');
/*!40000 ALTER TABLE `ownerprofile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_reservation`
--

DROP TABLE IF EXISTS `request_reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request_reservation` (
  `primaykey` int(11) NOT NULL,
  `guestname` varchar(45) DEFAULT NULL,
  `partysize` int(11) NOT NULL,
  `phonenumber` varchar(45) NOT NULL,
  `emailid` varchar(45) NOT NULL,
  `timeslot` int(11) NOT NULL,
  `date` date NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`primaykey`),
  UNIQUE KEY `primaykey_UNIQUE` (`primaykey`),
  KEY `timeslotfk_idx` (`timeslot`),
  CONSTRAINT `timeslotfk` FOREIGN KEY (`timeslot`) REFERENCES `time_slot` (`primarykey`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_reservation`
--

LOCK TABLES `request_reservation` WRITE;
/*!40000 ALTER TABLE `request_reservation` DISABLE KEYS */;
INSERT INTO `request_reservation` VALUES (174300,'alice',4,'9999808907','alice@gmail.com',9,'2015-10-09','w'),(293652,'rim',10,'980687964','kjnk@gmail.com',4,'2015-08-16','w'),(433397,'jeni',5,'9999999999','j@gmail.com',9,'2015-08-20','w'),(435729,'don',6,'7897898999','don@gmail.com',4,'2015-09-15','w'),(687659,'dino',7,'9809809999','abc@gmail.com',2,'2015-11-11','w'),(775187,'shweta',4,'9802137321','ss@gg.com',2,'2015-08-10','w'),(886225,'derek',6,'2222222222','d@gmail.com',5,'2015-10-11','w'),(985151,'alex',7,'9802634307','abcd@gmail.com',1,'2015-08-09','w'),(997651,'shweta',8,'980687964','kjnk@gmail.com',4,'2015-08-08','w'),(1176887,'saurabh',4,'9802137321','ss@gg.com',2,'2015-08-10','w'),(1399436,'john',2,'9009009999','john@gmail.com',5,'2015-08-14','w'),(1570205,'dino',7,'9809809999','abc@gmail.com',2,'2015-11-11','w'),(1717450,'mcmcmc',8,'980687964','kjnk@gmail.com',2,'2015-09-15','w'),(1799442,'deepesh',2,'9899897789','deepesh@gmail.com',3,'2015-10-15','w'),(1863228,'jennifer',6,'9099098888','jeniffer@gmail.com',5,'2015-10-11','w'),(1981502,'martini',2,'9099099999','mat@gmail.com',8,'2015-08-14','w');
/*!40000 ALTER TABLE `request_reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seating`
--

DROP TABLE IF EXISTS `seating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seating` (
  `primarykey` int(11) NOT NULL,
  `capacity` int(11) NOT NULL,
  PRIMARY KEY (`primarykey`),
  UNIQUE KEY `primarykey_UNIQUE` (`primarykey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seating`
--

LOCK TABLES `seating` WRITE;
/*!40000 ALTER TABLE `seating` DISABLE KEYS */;
INSERT INTO `seating` VALUES (1,4),(2,4),(3,2),(4,10),(5,2);
/*!40000 ALTER TABLE `seating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_slot`
--

DROP TABLE IF EXISTS `time_slot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time_slot` (
  `primarykey` int(11) NOT NULL,
  `timestring` varchar(45) NOT NULL,
  PRIMARY KEY (`primarykey`),
  UNIQUE KEY `primarykey_UNIQUE` (`primarykey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_slot`
--

LOCK TABLES `time_slot` WRITE;
/*!40000 ALTER TABLE `time_slot` DISABLE KEYS */;
INSERT INTO `time_slot` VALUES (1,'11 am -12 pm'),(2,'12 pm - 1pm'),(3,'1 pm - 2pm'),(4,'2 pm - 3 pm'),(5,'3 pm - 4 pm'),(6,'5 pm - 6 pm'),(7,'6 pm - 7 pm'),(8,'7 pm - 8 pm'),(9,'8 pm - 9 pm');
/*!40000 ALTER TABLE `time_slot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `primarykey` int(11) NOT NULL,
  PRIMARY KEY (`primarykey`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `primarykey_UNIQUE` (`primarykey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('shweta','sunshine',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-16 23:54:52
