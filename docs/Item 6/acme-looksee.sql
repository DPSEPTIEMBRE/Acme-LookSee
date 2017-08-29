start transaction;

drop database if exists `Acme-LookSee`;
create database `Acme-LookSee`;

use `Acme-LookSee`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete on `Acme-LookSee`.* to 'acme-user'@'%';
grant select, insert, update, delete, create, drop, references, index, alter, create temporary tables, lock tables, create view, create routine, alter routine, execute, trigger, show view on `Acme-LookSee`.* to 'acme-manager'@'%';

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-LookSee
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `activityreport`
--

DROP TABLE IF EXISTS `activityreport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activityreport` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `writtenMoment` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activityreport`
--

LOCK TABLES `activityreport` WRITE;
/*!40000 ALTER TABLE `activityreport` DISABLE KEYS */;
INSERT INTO `activityreport` VALUES (311,0,'search a job','search a job','2017-11-07 00:00:00'),(312,0,'written a reading','written a reading','2018-01-07 00:00:00'),(313,0,'written a new education record','Modify curricula 1','2018-06-07 00:00:00');
/*!40000 ALTER TABLE `activityreport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activityreport_attachments`
--

DROP TABLE IF EXISTS `activityreport_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activityreport_attachments` (
  `ActivityReport_id` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  KEY `FK_bil12max48hswory8gmgucgyy` (`ActivityReport_id`),
  CONSTRAINT `FK_bil12max48hswory8gmgucgyy` FOREIGN KEY (`ActivityReport_id`) REFERENCES `activityreport` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activityreport_attachments`
--

LOCK TABLES `activityreport_attachments` WRITE;
/*!40000 ALTER TABLE `activityreport_attachments` DISABLE KEYS */;
INSERT INTO `activityreport_attachments` VALUES (311,'https://www.activity.com/job'),(312,'https://www.activity.com/reading'),(313,'https://www.activity.com/education');
/*!40000 ALTER TABLE `activityreport_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actorName` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_cgls5lrufx91ufsyh467spwa3` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_activityreport`
--

DROP TABLE IF EXISTS `actor_activityreport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_activityreport` (
  `Actor_id` int(11) NOT NULL,
  `activities_id` int(11) NOT NULL,
  UNIQUE KEY `UK_2yw8xvgxn9celsyyxnyholg39` (`activities_id`),
  CONSTRAINT `FK_2yw8xvgxn9celsyyxnyholg39` FOREIGN KEY (`activities_id`) REFERENCES `activityreport` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_activityreport`
--

LOCK TABLES `actor_activityreport` WRITE;
/*!40000 ALTER TABLE `actor_activityreport` DISABLE KEYS */;
INSERT INTO `actor_activityreport` VALUES (343,311),(343,312);
/*!40000 ALTER TABLE `actor_activityreport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_folder`
--

DROP TABLE IF EXISTS `actor_folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_folder` (
  `Actor_id` int(11) NOT NULL,
  `folders_id` int(11) NOT NULL,
  UNIQUE KEY `UK_dp573h40udupcm5m1kgn2bc2r` (`folders_id`),
  CONSTRAINT `FK_dp573h40udupcm5m1kgn2bc2r` FOREIGN KEY (`folders_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_folder`
--

LOCK TABLES `actor_folder` WRITE;
/*!40000 ALTER TABLE `actor_folder` DISABLE KEYS */;
INSERT INTO `actor_folder` VALUES (272,315),(272,316),(272,317),(272,318),(314,319),(314,320),(314,321),(314,322),(343,323),(343,324),(343,325),(343,326),(350,327),(350,328),(350,329),(350,330),(351,331),(351,332),(351,333),(351,334),(361,335),(361,336),(361,337),(361,338),(362,339),(362,340),(362,341),(362,342);
/*!40000 ALTER TABLE `actor_folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actorName` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (272,0,'Pablo','41001','pablo@hotmail.com','+5 (10) 9132','Pablito',264);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administratorkey`
--

DROP TABLE IF EXISTS `administratorkey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administratorkey` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `keyName` varchar(255) DEFAULT NULL,
  `keyValue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administratorkey`
--

LOCK TABLES `administratorkey` WRITE;
/*!40000 ALTER TABLE `administratorkey` DISABLE KEYS */;
INSERT INTO `administratorkey` VALUES (363,0,'IVA','21'),(364,0,'LASTPAID','10/8/2017'),(365,0,'PRICE','1'),(366,0,'CACHE','10'),(367,0,'MAXRESULTS','10');
/*!40000 ALTER TABLE `administratorkey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `createMoment` datetime DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `curricula_id` int(11) DEFAULT NULL,
  `offer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fbe3c252r9i843hdydofp6kra` (`curricula_id`),
  KEY `FK_3rramw205jtvglx4ffqwelo8l` (`offer_id`),
  CONSTRAINT `FK_3rramw205jtvglx4ffqwelo8l` FOREIGN KEY (`offer_id`) REFERENCES `offer` (`id`),
  CONSTRAINT `FK_fbe3c252r9i843hdydofp6kra` FOREIGN KEY (`curricula_id`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (355,0,'2017-12-07 17:29:00','PENDING',306,352),(356,0,'2017-11-07 12:30:00','ACCEPTED',308,353),(357,0,'2017-10-07 12:30:00','REJECTED',310,353);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cache`
--

DROP TABLE IF EXISTS `cache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cache` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cacheType` int(11) DEFAULT NULL,
  `sessionId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2140i3m38krnuhdibrc945uob` (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cache`
--

LOCK TABLES `cache` WRITE;
/*!40000 ALTER TABLE `cache` DISABLE KEYS */;
/*!40000 ALTER TABLE `cache` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cache_resultids`
--

DROP TABLE IF EXISTS `cache_resultids`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cache_resultids` (
  `Cache_id` int(11) NOT NULL,
  `resultIds` int(11) DEFAULT NULL,
  KEY `FK_9ggc2cm529cc5few4ho2uvo2r` (`Cache_id`),
  CONSTRAINT `FK_9ggc2cm529cc5few4ho2uvo2r` FOREIGN KEY (`Cache_id`) REFERENCES `cache` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cache_resultids`
--

LOCK TABLES `cache_resultids` WRITE;
/*!40000 ALTER TABLE `cache_resultids` DISABLE KEYS */;
/*!40000 ALTER TABLE `cache_resultids` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidate`
--

DROP TABLE IF EXISTS `candidate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `candidate` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actorName` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cimbhv6q7i6y8f4xc7hl8g1n1` (`userAccount_id`),
  CONSTRAINT `FK_cimbhv6q7i6y8f4xc7hl8g1n1` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate`
--

LOCK TABLES `candidate` WRITE;
/*!40000 ALTER TABLE `candidate` DISABLE KEYS */;
INSERT INTO `candidate` VALUES (314,0,'Juan Jose','41192','juanjo@hotmail.com','+5 (10) 9888','Valle Zarza',266),(343,0,'Juan Carlos','41100','juanca@hotmail.com','+5 (10) 8888','Lopez Muñoz',267);
/*!40000 ALTER TABLE `candidate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidate_application`
--

DROP TABLE IF EXISTS `candidate_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `candidate_application` (
  `Candidate_id` int(11) NOT NULL,
  `applications_id` int(11) NOT NULL,
  UNIQUE KEY `UK_3o5m3pl82lsvm1ai5i3phu8er` (`applications_id`),
  KEY `FK_o7c0qtljneoervmujg02tdy10` (`Candidate_id`),
  CONSTRAINT `FK_o7c0qtljneoervmujg02tdy10` FOREIGN KEY (`Candidate_id`) REFERENCES `candidate` (`id`),
  CONSTRAINT `FK_3o5m3pl82lsvm1ai5i3phu8er` FOREIGN KEY (`applications_id`) REFERENCES `application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate_application`
--

LOCK TABLES `candidate_application` WRITE;
/*!40000 ALTER TABLE `candidate_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `candidate_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidate_curricula`
--

DROP TABLE IF EXISTS `candidate_curricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `candidate_curricula` (
  `Candidate_id` int(11) NOT NULL,
  `curriculas_id` int(11) NOT NULL,
  UNIQUE KEY `UK_a7lck30rfw12ak51860cvvinb` (`curriculas_id`),
  KEY `FK_38jmblotn7rhepukas760lgai` (`Candidate_id`),
  CONSTRAINT `FK_38jmblotn7rhepukas760lgai` FOREIGN KEY (`Candidate_id`) REFERENCES `candidate` (`id`),
  CONSTRAINT `FK_a7lck30rfw12ak51860cvvinb` FOREIGN KEY (`curriculas_id`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate_curricula`
--

LOCK TABLES `candidate_curricula` WRITE;
/*!40000 ALTER TABLE `candidate_curricula` DISABLE KEYS */;
INSERT INTO `candidate_curricula` VALUES (314,305),(343,307);
/*!40000 ALTER TABLE `candidate_curricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actorName` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  `VAT` varchar(255) DEFAULT NULL,
  `bloked` bit(1) DEFAULT NULL,
  `companyName` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ppk06rd1hchvqnqh2kkjm5il9` (`creditCard_id`),
  KEY `FK_ryofib0fqdaxa17n2qeqqf6am` (`userAccount_id`),
  CONSTRAINT `FK_ryofib0fqdaxa17n2qeqqf6am` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_ppk06rd1hchvqnqh2kkjm5il9` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (350,0,'Jesus','41036','jaime@hotmail.com','+5 (10) 9777','Jaime',268,'ES12345678R','\0','Company name 1',344),(351,0,'Paco','41436','pp@hotmail.com','+5 (10) 9678','Pepe',269,'ES87654321R','\0','Company name 2',345);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_payment`
--

DROP TABLE IF EXISTS `company_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_payment` (
  `Company_id` int(11) NOT NULL,
  `payments_id` int(11) NOT NULL,
  UNIQUE KEY `UK_lk9xx3qfj2mu3r97oq6so21n4` (`payments_id`),
  KEY `FK_hjcfkx4yascw75oh5mgctv779` (`Company_id`),
  CONSTRAINT `FK_hjcfkx4yascw75oh5mgctv779` FOREIGN KEY (`Company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FK_lk9xx3qfj2mu3r97oq6so21n4` FOREIGN KEY (`payments_id`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_payment`
--

LOCK TABLES `company_payment` WRITE;
/*!40000 ALTER TABLE `company_payment` DISABLE KEYS */;
INSERT INTO `company_payment` VALUES (350,347),(351,348),(351,349);
/*!40000 ALTER TABLE `company_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcard`
--

DROP TABLE IF EXISTS `creditcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditcard` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `CVV` int(11) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `expirationMonth` int(11) DEFAULT NULL,
  `expirationYear` int(11) DEFAULT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcard`
--

LOCK TABLES `creditcard` WRITE;
/*!40000 ALTER TABLE `creditcard` DISABLE KEYS */;
INSERT INTO `creditcard` VALUES (344,0,123,'AMEX',12,2020,'holder name 1',7521532412355264.00),(345,0,236,'MASTERCARD',10,2019,'holder name 2',1234567891234567.00),(346,0,238,'MASTERCARD',10,2019,'holder name 3',1234567891234567.00);
/*!40000 ALTER TABLE `creditcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula`
--

DROP TABLE IF EXISTS `curricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `copy` bit(1) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `personalRecord_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sb69tfhsf51vub0mq9n3kyeuy` (`personalRecord_id`),
  CONSTRAINT `FK_sb69tfhsf51vub0mq9n3kyeuy` FOREIGN KEY (`personalRecord_id`) REFERENCES `personalrecord` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula`
--

LOCK TABLES `curricula` WRITE;
/*!40000 ALTER TABLE `curricula` DISABLE KEYS */;
INSERT INTO `curricula` VALUES (305,0,'\0','2017-07-11-asdfg',296),(306,0,'','2017-07-11-asdfg',297),(307,0,'\0','2017-07-11-avdfg',296),(308,0,'','2017-07-11-avdfg',297),(309,0,'\0','2017-07-18-avdfh',296),(310,0,'','2017-07-18-avdfh',297);
/*!40000 ALTER TABLE `curricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula_educationrecord`
--

DROP TABLE IF EXISTS `curricula_educationrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula_educationrecord` (
  `Curricula_id` int(11) NOT NULL,
  `educationRecords_id` int(11) NOT NULL,
  UNIQUE KEY `UK_7svxs7dgerojiaytqcpwbgaq0` (`educationRecords_id`),
  KEY `FK_t1wke0qhch7ranmaupik2aldb` (`Curricula_id`),
  CONSTRAINT `FK_t1wke0qhch7ranmaupik2aldb` FOREIGN KEY (`Curricula_id`) REFERENCES `curricula` (`id`),
  CONSTRAINT `FK_7svxs7dgerojiaytqcpwbgaq0` FOREIGN KEY (`educationRecords_id`) REFERENCES `educationrecord` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula_educationrecord`
--

LOCK TABLES `curricula_educationrecord` WRITE;
/*!40000 ALTER TABLE `curricula_educationrecord` DISABLE KEYS */;
INSERT INTO `curricula_educationrecord` VALUES (305,281),(306,282),(307,277),(307,278),(308,279),(308,280);
/*!40000 ALTER TABLE `curricula_educationrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula_endorserrecord`
--

DROP TABLE IF EXISTS `curricula_endorserrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula_endorserrecord` (
  `Curricula_id` int(11) NOT NULL,
  `endorserRecords_id` int(11) NOT NULL,
  UNIQUE KEY `UK_exe5kb3j7mnedts3ea6jvcsp7` (`endorserRecords_id`),
  KEY `FK_9t3mmgy847vqc8upl8mv706mt` (`Curricula_id`),
  CONSTRAINT `FK_9t3mmgy847vqc8upl8mv706mt` FOREIGN KEY (`Curricula_id`) REFERENCES `curricula` (`id`),
  CONSTRAINT `FK_exe5kb3j7mnedts3ea6jvcsp7` FOREIGN KEY (`endorserRecords_id`) REFERENCES `endorserrecord` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula_endorserrecord`
--

LOCK TABLES `curricula_endorserrecord` WRITE;
/*!40000 ALTER TABLE `curricula_endorserrecord` DISABLE KEYS */;
INSERT INTO `curricula_endorserrecord` VALUES (305,287),(306,288),(307,283),(307,284),(308,285),(308,286);
/*!40000 ALTER TABLE `curricula_endorserrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula_miscellaneousrecord`
--

DROP TABLE IF EXISTS `curricula_miscellaneousrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula_miscellaneousrecord` (
  `Curricula_id` int(11) NOT NULL,
  `miscellaneousRecords_id` int(11) NOT NULL,
  UNIQUE KEY `UK_ae8caphsf8j19u8cf6q0l5axy` (`miscellaneousRecords_id`),
  KEY `FK_ivld1qhgtgfa4ij8v1a5xh4jf` (`Curricula_id`),
  CONSTRAINT `FK_ivld1qhgtgfa4ij8v1a5xh4jf` FOREIGN KEY (`Curricula_id`) REFERENCES `curricula` (`id`),
  CONSTRAINT `FK_ae8caphsf8j19u8cf6q0l5axy` FOREIGN KEY (`miscellaneousRecords_id`) REFERENCES `miscellaneousrecord` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula_miscellaneousrecord`
--

LOCK TABLES `curricula_miscellaneousrecord` WRITE;
/*!40000 ALTER TABLE `curricula_miscellaneousrecord` DISABLE KEYS */;
INSERT INTO `curricula_miscellaneousrecord` VALUES (307,299),(307,300),(307,301),(308,302),(308,303),(308,304);
/*!40000 ALTER TABLE `curricula_miscellaneousrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula_note`
--

DROP TABLE IF EXISTS `curricula_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula_note` (
  `Curricula_id` int(11) NOT NULL,
  `notes_id` int(11) NOT NULL,
  KEY `FK_e41ln4bp3bd0uy1vnkjaqqoub` (`notes_id`),
  KEY `FK_e9kre60qkjjohsahq1wk5rhbw` (`Curricula_id`),
  CONSTRAINT `FK_e9kre60qkjjohsahq1wk5rhbw` FOREIGN KEY (`Curricula_id`) REFERENCES `curricula` (`id`),
  CONSTRAINT `FK_e41ln4bp3bd0uy1vnkjaqqoub` FOREIGN KEY (`notes_id`) REFERENCES `note` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula_note`
--

LOCK TABLES `curricula_note` WRITE;
/*!40000 ALTER TABLE `curricula_note` DISABLE KEYS */;
/*!40000 ALTER TABLE `curricula_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula_professionalrecord`
--

DROP TABLE IF EXISTS `curricula_professionalrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula_professionalrecord` (
  `Curricula_id` int(11) NOT NULL,
  `professionalRecords_id` int(11) NOT NULL,
  UNIQUE KEY `UK_ec8xu2gjxln8ooeqm0ucekv3g` (`professionalRecords_id`),
  KEY `FK_8i0ncl6lxqwuk1bwkbtwakvja` (`Curricula_id`),
  CONSTRAINT `FK_8i0ncl6lxqwuk1bwkbtwakvja` FOREIGN KEY (`Curricula_id`) REFERENCES `curricula` (`id`),
  CONSTRAINT `FK_ec8xu2gjxln8ooeqm0ucekv3g` FOREIGN KEY (`professionalRecords_id`) REFERENCES `professionalrecord` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula_professionalrecord`
--

LOCK TABLES `curricula_professionalrecord` WRITE;
/*!40000 ALTER TABLE `curricula_professionalrecord` DISABLE KEYS */;
INSERT INTO `curricula_professionalrecord` VALUES (305,289),(305,293),(306,290),(306,294),(307,291),(308,292);
/*!40000 ALTER TABLE `curricula_professionalrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `educationrecord`
--

DROP TABLE IF EXISTS `educationrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `educationrecord` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `copy` bit(1) DEFAULT NULL,
  `diplomaTitle` varchar(255) DEFAULT NULL,
  `finalStudying` date DEFAULT NULL,
  `initialStudying` date DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educationrecord`
--

LOCK TABLES `educationrecord` WRITE;
/*!40000 ALTER TABLE `educationrecord` DISABLE KEYS */;
INSERT INTO `educationrecord` VALUES (277,0,'http://tusimagenesde.com/wp-content/uploads/2016/07/diplomas-1.jpg','\0','Enginer informatic of software','2017-09-07','2014-08-09','US'),(278,0,'https://image.slidesharecdn.com/diplomas-150722061346-lva1-app6892/95/diplomas-1-638.jpg?cb=1437545653','\0','Master Enginer informatic of software','2019-09-07','2013-08-09','US'),(279,0,'http://tusimagenesde.com/wp-content/uploads/2016/07/diplomas-1.jpg','','Enginer informatic of software','2017-09-07','2014-08-09','US'),(280,0,'https://image.slidesharecdn.com/diplomas-150722061346-lva1-app6892/95/diplomas-1-638.jpg?cb=1437545653','','Master Enginer informatic of software','2019-09-07','2013-08-09','US'),(281,0,'http://www.cblingua.com/wp-content/uploads/2016/10/traduccion_diploma.jpg','\0','Master Enginer informatic of software','2018-09-07','2011-10-09','US'),(282,0,'http://www.cblingua.com/wp-content/uploads/2016/10/traduccion_diploma.jpg','','Master Enginer informatic of software','2018-09-07','2011-10-09','US');
/*!40000 ALTER TABLE `educationrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `educationrecord_comments`
--

DROP TABLE IF EXISTS `educationrecord_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `educationrecord_comments` (
  `EducationRecord_id` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  KEY `FK_8upnn1ut4e4lbxwiapc4rvi51` (`EducationRecord_id`),
  CONSTRAINT `FK_8upnn1ut4e4lbxwiapc4rvi51` FOREIGN KEY (`EducationRecord_id`) REFERENCES `educationrecord` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educationrecord_comments`
--

LOCK TABLES `educationrecord_comments` WRITE;
/*!40000 ALTER TABLE `educationrecord_comments` DISABLE KEYS */;
INSERT INTO `educationrecord_comments` VALUES (277,' Good education record'),(278,' A+ in all subjects'),(279,' Good education record'),(280,' A+ in all subjects'),(281,' A+ in all subjects'),(281,' Speak 3 idioms'),(282,' A+ in all subjects'),(282,' Speak 3 idioms');
/*!40000 ALTER TABLE `educationrecord_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endorserrecord`
--

DROP TABLE IF EXISTS `endorserrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endorserrecord` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `copy` bit(1) DEFAULT NULL,
  `endorserEmail` varchar(255) DEFAULT NULL,
  `endorserName` varchar(255) DEFAULT NULL,
  `endorserPhone` varchar(255) DEFAULT NULL,
  `linkedIn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endorserrecord`
--

LOCK TABLES `endorserrecord` WRITE;
/*!40000 ALTER TABLE `endorserrecord` DISABLE KEYS */;
INSERT INTO `endorserrecord` VALUES (283,0,'\0','lope@gmail.com','Juan Jose Lopez Aguado','+5 (10) 4488','https://www.linkedin.com/in/JuanjoseLop/'),(284,0,'\0','karliokre@hotmail.com','Juan Carlos Lopez Muñoz','+5 (10) 4488','https://www.linkedin.com/in/juan-carlos-l%C3%B3pez-mu%C3%B1oz-34881b145/'),(285,0,'','lope@gmail.com','Juan Jose Lopez Aguado','+5 (10) 4488','https://www.linkedin.com/in/JuanjoseLop/'),(286,0,'','karliokre@hotmail.com','Juan Carlos Lopez Muñoz','+5 (10) 4488','https://www.linkedin.com/in/juan-carlos-l%C3%B3pez-mu%C3%B1oz-34881b145/'),(287,0,'\0','laurapadialglez@gmail.com','Laura Padial González','+34 663384774','https://www.linkedin.com/in/laura-padial-gonz%C3%A1lez-4b871460/'),(288,0,'','laurapadialglez@gmail.com','Laura Padial González','+34 663384774','https://www.linkedin.com/in/laura-padial-gonz%C3%A1lez-4b871460/');
/*!40000 ALTER TABLE `endorserrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endorserrecord_comments`
--

DROP TABLE IF EXISTS `endorserrecord_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endorserrecord_comments` (
  `EndorserRecord_id` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  KEY `FK_b0fje2axm5kdjeio92yd935mi` (`EndorserRecord_id`),
  CONSTRAINT `FK_b0fje2axm5kdjeio92yd935mi` FOREIGN KEY (`EndorserRecord_id`) REFERENCES `endorserrecord` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endorserrecord_comments`
--

LOCK TABLES `endorserrecord_comments` WRITE;
/*!40000 ALTER TABLE `endorserrecord_comments` DISABLE KEYS */;
INSERT INTO `endorserrecord_comments` VALUES (283,' He is a good alumn'),(284,' He is a good alumn'),(285,' He is a good alumn'),(286,' He is a good alumn'),(287,' She is a good alumn'),(288,' She is a good alumn');
/*!40000 ALTER TABLE `endorserrecord_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `folderName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES (315,0,'inbox'),(316,0,'outbox'),(317,0,'trashbox'),(318,0,'spambox'),(319,0,'inbox'),(320,0,'outbox'),(321,0,'trashbox'),(322,0,'spambox'),(323,0,'inbox'),(324,0,'trashbox'),(325,0,'outbox'),(326,0,'spambox'),(327,0,'inbox'),(328,0,'trashbox'),(329,0,'outbox'),(330,0,'spambox'),(331,0,'inbox'),(332,0,'trashbox'),(333,0,'outbox'),(334,0,'spambox'),(335,0,'inbox'),(336,0,'trashbox'),(337,0,'outbox'),(338,0,'spambox'),(339,0,'inbox'),(340,0,'trashbox'),(341,0,'outbox'),(342,0,'spambox');
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder_mailmessage`
--

DROP TABLE IF EXISTS `folder_mailmessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder_mailmessage` (
  `Folder_id` int(11) NOT NULL,
  `messages_id` int(11) NOT NULL,
  UNIQUE KEY `UK_t0swyuobr7225xnposfuu8ixm` (`messages_id`),
  KEY `FK_rys3vi5853dyhptvldu6nn09l` (`Folder_id`),
  CONSTRAINT `FK_rys3vi5853dyhptvldu6nn09l` FOREIGN KEY (`Folder_id`) REFERENCES `folder` (`id`),
  CONSTRAINT `FK_t0swyuobr7225xnposfuu8ixm` FOREIGN KEY (`messages_id`) REFERENCES `mailmessage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder_mailmessage`
--

LOCK TABLES `folder_mailmessage` WRITE;
/*!40000 ALTER TABLE `folder_mailmessage` DISABLE KEYS */;
INSERT INTO `folder_mailmessage` VALUES (322,369),(325,368);
/*!40000 ALTER TABLE `folder_mailmessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mailmessage`
--

DROP TABLE IF EXISTS `mailmessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mailmessage` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `sent` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `recipient_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mailmessage`
--

LOCK TABLES `mailmessage` WRITE;
/*!40000 ALTER TABLE `mailmessage` DISABLE KEYS */;
INSERT INTO `mailmessage` VALUES (368,0,'it is a mesage important','HIGH','2017-11-07 00:00:00','urgent',343,314),(369,0,'it is a sex important','NEUTRAL','2017-11-07 00:00:00','hello',343,314),(370,0,'it is a mesage important','LOW','2017-11-07 00:00:00','hello world',314,343);
/*!40000 ALTER TABLE `mailmessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `miscellaneousrecord`
--

DROP TABLE IF EXISTS `miscellaneousrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `miscellaneousrecord` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `copy` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `miscellaneousrecord`
--

LOCK TABLES `miscellaneousrecord` WRITE;
/*!40000 ALTER TABLE `miscellaneousrecord` DISABLE KEYS */;
INSERT INTO `miscellaneousrecord` VALUES (299,0,'https://www.acme-pad-thai.com','\0','acme pad-thai'),(300,0,'https://www.acme-love.com','\0','acme love'),(301,0,'https://www.acme-gym.com','\0','acme gym'),(302,0,'https://www.acme-pad-thai.com','','acme pad-thai'),(303,0,'https://www.acme-love.com','','acme love'),(304,0,'https://www.acme-gym.com','','acme gym');
/*!40000 ALTER TABLE `miscellaneousrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `miscellaneousrecord_comments`
--

DROP TABLE IF EXISTS `miscellaneousrecord_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `miscellaneousrecord_comments` (
  `MiscellaneousRecord_id` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  KEY `FK_ajnajfg26mdd0ujsxrq56vyvf` (`MiscellaneousRecord_id`),
  CONSTRAINT `FK_ajnajfg26mdd0ujsxrq56vyvf` FOREIGN KEY (`MiscellaneousRecord_id`) REFERENCES `miscellaneousrecord` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `miscellaneousrecord_comments`
--

LOCK TABLES `miscellaneousrecord_comments` WRITE;
/*!40000 ALTER TABLE `miscellaneousrecord_comments` DISABLE KEYS */;
INSERT INTO `miscellaneousrecord_comments` VALUES (299,' It is a social network for food thailand '),(300,' It is a social network for love'),(301,' It is a social network for gym'),(302,' It is a social network for food thailand '),(303,' It is a social network for love'),(304,' It is a social network for gym');
/*!40000 ALTER TABLE `miscellaneousrecord_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `createdMoment` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `reply` varchar(255) DEFAULT NULL,
  `replyMoment` datetime DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `curricula_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gjmijqjaorxjsgqyc3trdq62l` (`curricula_id`),
  CONSTRAINT `FK_gjmijqjaorxjsgqyc3trdq62l` FOREIGN KEY (`curricula_id`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` VALUES (358,0,'2017-12-07 18:07:02','remark1','reply1','2017-12-07 18:09:00','CORRECTED',305),(359,0,'2017-12-07 18:25:02','remark2','reply2','2017-12-07 18:39:00','PENDING',307),(360,0,'2018-05-07 18:25:02','remark3','reply3','2018-05-07 18:39:00','PENDING',309);
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer`
--

DROP TABLE IF EXISTS `offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `deadlineApply` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `maxRange` double DEFAULT NULL,
  `minRange` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `company_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_vbihdost6d5dkmf0fu1954ye` (`company_id`),
  CONSTRAINT `FK_vbihdost6d5dkmf0fu1954ye` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer`
--

LOCK TABLES `offer` WRITE;
/*!40000 ALTER TABLE `offer` DISABLE KEYS */;
INSERT INTO `offer` VALUES (352,0,'euros','2019-08-10 12:23:00','description offer 1',1400,1200,'title offer 1',350),(353,0,'euros','2019-08-06 20:23:00','description offer 2',1400,1300,'title offer 2',350),(354,0,'dolares','2018-01-11 12:23:00','description offer 3',2000,1800,'title offer 3',351);
/*!40000 ALTER TABLE `offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer_application`
--

DROP TABLE IF EXISTS `offer_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offer_application` (
  `Offer_id` int(11) NOT NULL,
  `applications_id` int(11) NOT NULL,
  UNIQUE KEY `UK_76hvkv9t5axbk3c1i1rga9pki` (`applications_id`),
  KEY `FK_fuwfsf1a1xj3qjljarvsq0axc` (`Offer_id`),
  CONSTRAINT `FK_fuwfsf1a1xj3qjljarvsq0axc` FOREIGN KEY (`Offer_id`) REFERENCES `offer` (`id`),
  CONSTRAINT `FK_76hvkv9t5axbk3c1i1rga9pki` FOREIGN KEY (`applications_id`) REFERENCES `application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer_application`
--

LOCK TABLES `offer_application` WRITE;
/*!40000 ALTER TABLE `offer_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `offer_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `createMoment` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `paid` bit(1) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `tax` double DEFAULT NULL,
  `creditCard_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_snfx4b738qbms19i7w16v75x1` (`creditCard_id`),
  CONSTRAINT `FK_snfx4b738qbms19i7w16v75x1` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (347,0,'2017-10-06 20:00:30','description payment 1','\0',1,21,344),(348,0,'2017-10-07 15:00:00','description payment 2','',1,31,345),(349,0,'2018-06-07 15:02:00','description payment 2','',1,20,345);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalrecord`
--

DROP TABLE IF EXISTS `personalrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personalrecord` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `copy` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `linkedIn` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalrecord`
--

LOCK TABLES `personalrecord` WRITE;
/*!40000 ALTER TABLE `personalrecord` DISABLE KEYS */;
INSERT INTO `personalrecord` VALUES (295,0,'\0','juanjo@gmail.com','Juan Jose Valle Zarza','https://www.linkedin.com/in/Juanjose/','+5 (10) 9888','https://cdn.shopify.com/s/files/1/0792/9923/t/5/assets/logo.png?3399403889687444689'),(296,0,'\0','juanca@gmail.com','Juan Carlos Lopez Muñoz','https://www.linkedin.com/in/Juancarlos/','+5 (10) 8888','https://cdn.shopify.com/s/files/1/0792/9923/t/5/assets/logo.png?3399403889687444689'),(297,0,'','juanca@gmail.com','Juan Carlos Lopez Muñoz','https://www.linkedin.com/in/Juancarlos/','+5 (10) 8888','https://cdn.shopify.com/s/files/1/0792/9923/t/5/assets/logo.png?3399403889687444689'),(298,0,'\0','laura94sev@hotmail.com','Laura Padial González','https://www.linkedin.com/in/laura-padial-gonz%C3%A1lez-4b871460/','+5 (10) 1234567','https://cdn.shopify.com/s/files/1/0792/9923/t/5/assets/logo.png?3399403889687444689');
/*!40000 ALTER TABLE `personalrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professionalrecord`
--

DROP TABLE IF EXISTS `professionalrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professionalrecord` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `companyName` varchar(255) DEFAULT NULL,
  `copy` bit(1) DEFAULT NULL,
  `finalWork` date DEFAULT NULL,
  `initialWork` date DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professionalrecord`
--

LOCK TABLES `professionalrecord` WRITE;
/*!40000 ALTER TABLE `professionalrecord` DISABLE KEYS */;
INSERT INTO `professionalrecord` VALUES (289,0,'http://intt2.com.mx/wp-content/uploads/2016/01/m348_oracle.png','Oracle','\0','2016-11-07','2015-02-07','Developer'),(290,0,'http://intt2.com.mx/wp-content/uploads/2016/01/m348_oracle.png','Oracle','','2016-11-07','2015-02-07','Developer'),(291,0,'http://satelec.etsit.upm.es/wp-content/uploads/2017/02/microsoft-gray-300x111.png','Microsoft','\0','2017-09-12','2015-11-07','Tester'),(292,0,'http://satelec.etsit.upm.es/wp-content/uploads/2017/02/microsoft-gray-300x111.png','Microsoft','','2017-09-12','2015-11-07','Tester'),(293,0,'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Facebook_New_Logo_%282015%29.svg/245px-Facebook_New_Logo_%282015%29.svg.png','Facebook','\0','2018-09-06','2016-11-01','Project Manager'),(294,0,'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Facebook_New_Logo_%282015%29.svg/245px-Facebook_New_Logo_%282015%29.svg.png','Facebook','','2018-09-06','2016-11-01','Project Manager');
/*!40000 ALTER TABLE `professionalrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professionalrecord_comments`
--

DROP TABLE IF EXISTS `professionalrecord_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professionalrecord_comments` (
  `ProfessionalRecord_id` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  KEY `FK_p6ml7gtt96exn742i2gl3b8dj` (`ProfessionalRecord_id`),
  CONSTRAINT `FK_p6ml7gtt96exn742i2gl3b8dj` FOREIGN KEY (`ProfessionalRecord_id`) REFERENCES `professionalrecord` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professionalrecord_comments`
--

LOCK TABLES `professionalrecord_comments` WRITE;
/*!40000 ALTER TABLE `professionalrecord_comments` DISABLE KEYS */;
INSERT INTO `professionalrecord_comments` VALUES (289,' It is a very important company'),(290,' It is a very important company'),(291,' It is a very important company so'),(292,' It is a very important company so'),(293,' It is a very important company so'),(293,' I love this work'),(294,' It is a very important company so'),(294,' I love this work');
/*!40000 ALTER TABLE `professionalrecord_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spamword`
--

DROP TABLE IF EXISTS `spamword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spamword` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spamword`
--

LOCK TABLES `spamword` WRITE;
/*!40000 ALTER TABLE `spamword` DISABLE KEYS */;
INSERT INTO `spamword` VALUES (273,0,'viagra'),(274,0,'cialis'),(275,0,'sex'),(276,0,'love');
/*!40000 ALTER TABLE `spamword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (264,0,'200ceb26807d6bf99fd6f4f0d1ca54d4','administrator'),(265,0,'1b3231655cebb7a1f783eddf27d254ca','super'),(266,0,'e7a02779bc8c3b75ffd0039ced68c8f8','candidate1'),(267,0,'f61b2fa651fbf329a43d2e100c1f4425','candidate2'),(268,0,'df655f976f7c9d3263815bd981225cd9','company1'),(269,0,'d196a28097115067fefd73d25b0c0be8','company2'),(270,0,'f7c9ed573deaccaa627fed102428ca4d','verifier1'),(271,0,'c6191e06b6b6a7b79199e36d7cfb246e','verifier2');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (264,'ADMINISTRATOR'),(265,'CANDIDATE'),(265,'COMPANY'),(265,'ADMINISTRATOR'),(265,'VERIFIER'),(266,'CANDIDATE'),(267,'CANDIDATE'),(268,'COMPANY'),(269,'COMPANY'),(270,'VERIFIER'),(271,'VERIFIER');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verifier`
--

DROP TABLE IF EXISTS `verifier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `verifier` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actorName` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cjq8misoadmt0cq6ajcvrx0gm` (`userAccount_id`),
  CONSTRAINT `FK_cjq8misoadmt0cq6ajcvrx0gm` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verifier`
--

LOCK TABLES `verifier` WRITE;
/*!40000 ALTER TABLE `verifier` DISABLE KEYS */;
INSERT INTO `verifier` VALUES (361,0,'Lucas','41010','lucas@hotmail.com','+5 (10) 9654','Vazquez',270),(362,0,'Maria','41013','mari@hotmail.com','+5 (10) 9112','Vazquez',271);
/*!40000 ALTER TABLE `verifier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verifier_note`
--

DROP TABLE IF EXISTS `verifier_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `verifier_note` (
  `Verifier_id` int(11) NOT NULL,
  `notes_id` int(11) NOT NULL,
  UNIQUE KEY `UK_ihuj9t3rncqo6yhare2j2cgb` (`notes_id`),
  KEY `FK_8ks40k588ji91jjtys57kb2yd` (`Verifier_id`),
  CONSTRAINT `FK_8ks40k588ji91jjtys57kb2yd` FOREIGN KEY (`Verifier_id`) REFERENCES `verifier` (`id`),
  CONSTRAINT `FK_ihuj9t3rncqo6yhare2j2cgb` FOREIGN KEY (`notes_id`) REFERENCES `note` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verifier_note`
--

LOCK TABLES `verifier_note` WRITE;
/*!40000 ALTER TABLE `verifier_note` DISABLE KEYS */;
INSERT INTO `verifier_note` VALUES (361,358),(361,359),(362,360);
/*!40000 ALTER TABLE `verifier_note` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-29 23:18:33

commit;