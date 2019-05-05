-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.5.57-MariaDB

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
-- Table structure for table `address`
--

DROP SCHEMA IF EXISTS mydb;
CREATE SCHEMA mydb;
USE mydb;  

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `addr_id` int(10) unsigned NOT NULL,
  `user_name` varchar(25) NOT NULL,
  `country` varchar(25) NOT NULL,
  `city` varchar(25) NOT NULL,
  `street_name` varchar(25) NOT NULL,
  `street_num` int(10) unsigned NOT NULL,
  `zip` int(10) unsigned NOT NULL,
  PRIMARY KEY (`addr_id`),
  KEY `fk_Address_User1_idx` (`user_name`),
  CONSTRAINT `fk_Address_User1` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Solonopoulos','Greece','Thessaloniki','Aeschinou',7,54642),(2,'Konstantinopoulos','Greece','Thessaloniki ','Aemonos',28,54351),(3,'Georgopoulos','Greece','Filyro','Ippokratous',6,57010),(4,'Beethoven','Greece','Larisa','Kyprou',21,41234),(5,'Maxwell','Greece','Trikala','Asklipiou',43,54326),(6,'Gauss','Greece','Volos','Ermou',60,64578),(7,'Alice','Greece','Athens','Papandreou',123,87291),(8,'Bob','Greece','Athens','Parou',12,87258),(9,'Yoyo','Greece','Peiraias','Gazi',1,79423),(10,'Neo','Greece','Volos','Tzavela',22,64589);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `mydb`.`Address_BEFORE_INSERT` BEFORE INSERT ON `Address` FOR EACH ROW
BEGIN
	IF (NEW.zip <= 9999 && NEW.zip > 99999) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'invalid data';
	END IF;
    IF (NEW.street_num < 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'invalid data';
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `mydb`.`Address_BEFORE_UPDATE` BEFORE UPDATE ON `Address` FOR EACH ROW
BEGIN
	IF (NEW.zip <= 9999 && NEW.zip > 99999) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'invalid data';
	END IF;
    IF (NEW.street_num < 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'invalid data';
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `characteristics`
--

DROP TABLE IF EXISTS `characteristics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `characteristics` (
  `gender` enum('MALE','FEMALE') NOT NULL,
  `registry_id` int(10) unsigned NOT NULL,
  `size` enum('SMALL','MEDIUM','LARGE') NOT NULL,
  `weight` float unsigned NOT NULL,
  `wing_span` float unsigned NOT NULL,
  PRIMARY KEY (`gender`,`registry_id`),
  KEY `fk_Characteristics_Species1_idx` (`registry_id`),
  CONSTRAINT `fk_Characteristics_Species1` FOREIGN KEY (`registry_id`) REFERENCES `species` (`registry_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characteristics`
--

LOCK TABLES `characteristics` WRITE;
/*!40000 ALTER TABLE `characteristics` DISABLE KEYS */;
INSERT INTO `characteristics` VALUES ('MALE',1,'SMALL',5,35),('MALE',3,'LARGE',10,97),('MALE',5,'SMALL',0.7,10),('MALE',6,'MEDIUM',5,27),('MALE',9,'MEDIUM',3.5,28),('MALE',10,'MEDIUM',3.1,30),('FEMALE',2,'MEDIUM',6.5,22.5),('FEMALE',4,'LARGE',23,150),('FEMALE',7,'LARGE',15,90),('FEMALE',8,'SMALL',2,25);
/*!40000 ALTER TABLE `characteristics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `edit_history`
--

DROP TABLE IF EXISTS `edit_history`;
/*!50001 DROP VIEW IF EXISTS `edit_history`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `edit_history` AS SELECT 
 1 AS `user_name`,
 1 AS `species_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment` (
  `id` int(10) unsigned NOT NULL,
  `user_name` varchar(25) NOT NULL,
  `countryside_gear` varchar(25) NOT NULL,
  `observation_gear` varchar(25) NOT NULL,
  `transportation` varchar(25) NOT NULL,
  PRIMARY KEY (`id`,`user_name`),
  KEY `fk_Equipment_User1_idx` (`user_name`),
  CONSTRAINT `fk_Equipment_User1` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (1,'Solonopoulos','Sleeping_bag','Camera','Electric_bike'),(2,'Konstantinopoulos','Sleeping_bag','Binoculars','Motorcycle '),(3,'Georgopoulos','RV','Camera','Jeep'),(4,'Beethoven','Trailer','Telescope','Car'),(5,'Maxwell','Tent','Rope','Bike'),(6,'Gauss','Small_Tent','Climbing_equipment','Scooter'),(7,'Alice','RV','Telescope','Jeep'),(8,'Bob','Umbrella','Camera','Scooter'),(9,'Yoyo','RV','Climbing_equipment','Car'),(10,'Neo','Small_Tent','Binoculars','Bike');
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `equipment_history`
--

DROP TABLE IF EXISTS `equipment_history`;
/*!50001 DROP VIEW IF EXISTS `equipment_history`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `equipment_history` AS SELECT 
 1 AS `user_name`,
 1 AS `id`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `lives_in`
--

DROP TABLE IF EXISTS `lives_in`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lives_in` (
  `registry_id` int(10) unsigned NOT NULL,
  `region_id` varchar(25) NOT NULL,
  `from` char(3) NOT NULL,
  `to` char(3) NOT NULL,
  PRIMARY KEY (`registry_id`,`region_id`),
  KEY `fk_Species_has_Location_Location1_idx` (`region_id`),
  KEY `fk_Species_has_Location_Species1_idx` (`registry_id`),
  CONSTRAINT `fk_Species_has_Location_Species1` FOREIGN KEY (`registry_id`) REFERENCES `species` (`registry_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Species_has_Location_Location1` FOREIGN KEY (`region_id`) REFERENCES `location` (`region_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lives_in`
--

LOCK TABLES `lives_in` WRITE;
/*!40000 ALTER TABLE `lives_in` DISABLE KEYS */;
INSERT INTO `lives_in` VALUES (1,'1','MAY','AUG'),(2,'2','FEB','OCT'),(3,'3','APR','JUL'),(4,'4','JUN','AUG'),(5,'5','SEP','DEC'),(6,'6','FEB','DEC'),(7,'7','JUN','AUG'),(8,'8','MAY','SEP'),(9,'9','FEB','OCT'),(10,'10','FEB','JUL');
/*!40000 ALTER TABLE `lives_in` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `region_id` varchar(25) NOT NULL,
  `habitat` varchar(25) NOT NULL,
  `observatory` varchar(30) NOT NULL,
  `area_characteristics` varchar(25) NOT NULL,
  `energy_footprint` float unsigned NOT NULL,
  PRIMARY KEY (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES ('1','Prespes_SouthWest','PSW3','High_humidity',300),('10','Vistonida_East','V1','Sunny',150),('2','Prespes_South','PS2','High_humidity',500),('3','Vistonida_East','V1','Sunny',100),('4','Koroneia_SouthEast','KSE1','Dry',250),('5','Koroneia_South','KS1','Dry',200),('6','Koroneia_North','KN1','Dry',200),('7','Vistonida_West','VW1','Humidity',100),('8','Vistonida_East','V1','Dry',100),('9','Vistonida_East','V1','Sunny',400);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `population`
--

DROP TABLE IF EXISTS `population`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `population` (
  `pop_id` int(10) unsigned NOT NULL,
  `registry_id` int(10) unsigned NOT NULL,
  `pop_habitat` int(10) unsigned NOT NULL,
  `growth_rate_habitat` float NOT NULL,
  `from` int(10) unsigned NOT NULL,
  `to` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pop_id`),
  KEY `fk_Population_Species1_idx` (`registry_id`),
  CONSTRAINT `registry_id` FOREIGN KEY (`registry_id`) REFERENCES `species` (`registry_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `population`
--

LOCK TABLES `population` WRITE;
/*!40000 ALTER TABLE `population` DISABLE KEYS */;
INSERT INTO `population` VALUES (1,1,0,5000,2014,2016),(2,2,0,354,2012,2015),(3,3,0,3201,2010,2011),(4,4,0,234,2013,2015),(5,5,0,455,2016,2017),(6,6,1,796,2016,2017),(7,7,0,1237,2017,2018),(8,8,0,2678,2017,2018),(9,9,0,569,2014,2015),(10,10,0,1570,2017,2018);
/*!40000 ALTER TABLE `population` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program` (
  `description_code` int(10) unsigned NOT NULL,
  `day` int(10) unsigned NOT NULL,
  `month` int(10) unsigned NOT NULL,
  `year` int(10) unsigned NOT NULL,
  `area` varchar(25) NOT NULL,
  PRIMARY KEY (`description_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program`
--

LOCK TABLES `program` WRITE;
/*!40000 ALTER TABLE `program` DISABLE KEYS */;
INSERT INTO `program` VALUES (1130010,23,8,2019,'Vistonida'),(1220010,16,12,2020,'Axios'),(1230001,7,12,2019,'Koroneia'),(1230002,8,11,2025,'Koroneia'),(1230003,8,9,2019,'Koroneia'),(1230005,7,10,2021,'Koroneia'),(1340001,10,5,2020,'Prespes'),(1340002,5,6,2020,'Prespes'),(1340003,9,7,2020,'Axios'),(1340004,7,6,2023,'Axios');
/*!40000 ALTER TABLE `program` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `mydb`.`Program_BEFORE_INSERT` 
BEFORE INSERT ON `Program` FOR EACH ROW
BEGIN
	IF (NEW.day <= 0 & NEW.day > 31) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'invalid data';
	END IF;
    IF (NEW.month <= 0 & NEW.month > 12) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'invalid data';
	END IF;
    IF (NEW.year <= 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'invalid data';
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `mydb`.`Program_BEFORE_UPDATE`
BEFORE UPDATE ON `Program` FOR EACH ROW
BEGIN
	IF (NEW.day <= 0 & NEW.day > 31) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'invalid data';
	END IF;
    IF (NEW.month <= 0 & NEW.month > 12) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'invalid data';
	END IF;
    IF (NEW.year <= 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'invalid data';
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `species`
--

DROP TABLE IF EXISTS `species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `species` (
  `registry_id` int(10) unsigned NOT NULL,
  `species_name` varchar(25) NOT NULL,
  `genus` varchar(25) NOT NULL,
  `family` varchar(25) NOT NULL,
  `migration` tinyint(1) NOT NULL,
  `endangered` tinyint(1) NOT NULL,
  `eating_habits` enum('carnivorous','herbivorous','omnivorous') NOT NULL,
  `reproduction_period_start` char(3) NOT NULL,
  `reproduction_period_end` char(3) NOT NULL,
  `edit_status` tinyint(1) NOT NULL,
  PRIMARY KEY (`registry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `species`
--

LOCK TABLES `species` WRITE;
/*!40000 ALTER TABLE `species` DISABLE KEYS */;
INSERT INTO `species` VALUES (1,'A.Nipalensis','Aceros','Bucerotidae',0,1,'herbivorous','MAR','MAY',1),(2,'Z.Stresemanni','Zavattariornis','Corvidae',0,1,'herbivorous','MAR','MAY',0),(3,'M.Thunderbird','Mozilla','Emailae',1,0,'omnivorous','MAY','JUN',1),(4,'M.Inquieta','Myiagra','Monarchidae',1,0,'carnivorous','MAR','JUL',1),(5,'Ceyx','Alcedininae','Alcedinidae',1,0,'herbivorous','JAN','JUN',1),(6,'Chaetoecercus','Trochilinae','Trochilidae',0,0,'omnivorous','OCT','DEC',0),(7,'Woody Woodpecker','Dendropicos','Picidae',1,0,'herbivorous','MAY','JUN',1),(8,'P.Carteri','Poodytes','Locustellidae',1,0,'omnivorous','MAY','JUN',0),(9,'Gyrfalcon','Falco','Falconidae',1,0,'omnivorous','JUN','AUG',1),(10,'Tweety','Dove','WarnerBros',1,0,'herbivorous','JUN','AUG',1);
/*!40000 ALTER TABLE `species` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_name` varchar(25) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(15) NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Alice','alice@ornithologiki.org','003069 34567815'),('Beethoven','beethoven@ornithologiki.org','003069 34567813'),('Bob','bob@ornithologiki.org','003069 34567816'),('Gauss','gauss@ornithologiki.org','003069 34567814'),('Georgopoulos','georgios@ornithologiki.org','003069 34567812'),('Konstantinopoulos','konstantinos@ornithologiki.org','003069 23456781'),('Maxwell','maxwell@ornithologiki.org','003069 34567817'),('Neo','neo@ornithologiki.org','003069 34567845'),('Solonopoulos','solon@ornithologiki.org','003069 12345678'),('Yoyo','yoyo@ornithologiki.org','003069 34567819');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_program`
--

DROP TABLE IF EXISTS `user_program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_program` (
  `description_code` int(10) unsigned NOT NULL,
  `user_name` varchar(25) NOT NULL,
  PRIMARY KEY (`description_code`,`user_name`),
  KEY `fk_Program_has_User_User1_idx` (`user_name`),
  KEY `fk_Program_has_User_Program1_idx` (`description_code`),
  CONSTRAINT `fk_Program_has_User_Program1` FOREIGN KEY (`description_code`) REFERENCES `program` (`description_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Program_has_User_User1` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_program`
--

LOCK TABLES `user_program` WRITE;
/*!40000 ALTER TABLE `user_program` DISABLE KEYS */;
INSERT INTO `user_program` VALUES (1130010,'Georgopoulos'),(1220010,'Konstantinopoulos'),(1230001,'Beethoven'),(1230002,'Maxwell'),(1230003,'Neo'),(1230005,'Yoyo'),(1340001,'Solonopoulos'),(1340002,'Gauss'),(1340003,'Alice'),(1340004,'Bob');
/*!40000 ALTER TABLE `user_program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `user_programs`
--

DROP TABLE IF EXISTS `user_programs`;
/*!50001 DROP VIEW IF EXISTS `user_programs`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `user_programs` AS SELECT 
 1 AS `user_name`,
 1 AS `description_code`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `user_species`
--

DROP TABLE IF EXISTS `user_species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_species` (
  `registry_id` int(10) unsigned NOT NULL,
  `user_name` varchar(25) NOT NULL,
  PRIMARY KEY (`registry_id`,`user_name`),
  KEY `fk_Species_has_User_User1_idx` (`user_name`),
  KEY `fk_Species_has_User_Species1_idx` (`registry_id`),
  CONSTRAINT `fk_Species_has_User_Species1` FOREIGN KEY (`registry_id`) REFERENCES `species` (`registry_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Species_has_User_User1` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_species`
--

LOCK TABLES `user_species` WRITE;
/*!40000 ALTER TABLE `user_species` DISABLE KEYS */;
INSERT INTO `user_species` VALUES (1,'Solonopoulos'),(2,'Konstantinopoulos'),(3,'Georgopoulos'),(4,'Beethoven'),(5,'Maxwell'),(6,'Gauss'),(7,'Alice'),(8,'Bob'),(9,'Yoyo'),(10,'Neo');
/*!40000 ALTER TABLE `user_species` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `edit_history`
--

/*!50001 DROP VIEW IF EXISTS `edit_history`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `edit_history` AS select `user_species`.`user_name` AS `user_name`,`species`.`species_name` AS `species_name` from (`species` join `user_species` on((`species`.`registry_id` = `user_species`.`registry_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `equipment_history`
--

/*!50001 DROP VIEW IF EXISTS `equipment_history`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `equipment_history` AS select `user`.`user_name` AS `user_name`,`equipment`.`id` AS `id` from (`user` join `equipment` on((`user`.`user_name` = `equipment`.`user_name`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `user_programs`
--

/*!50001 DROP VIEW IF EXISTS `user_programs`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `user_programs` AS select `user`.`user_name` AS `user_name`,`user_program`.`description_code` AS `description_code` from (`user` join `user_program` on((`user`.`user_name` = `user_program`.`user_name`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-22 18:53:08
