-- MySQL dump 10.13  Distrib 5.7.20, for macos10.12 (x86_64)
--
-- Host: localhost    Database: CleanStreams
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `account_id` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `permissions` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'123@123.com','123','1');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `away_lineup`
--

DROP TABLE IF EXISTS `away_lineup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `away_lineup` (
  `lineup_id` int(11) NOT NULL,
  `1` varchar(100) DEFAULT NULL,
  `2` varchar(100) DEFAULT NULL,
  `3` varchar(100) DEFAULT NULL,
  `4` varchar(100) DEFAULT NULL,
  `5` varchar(100) DEFAULT NULL,
  `6` varchar(100) DEFAULT NULL,
  `7` varchar(100) DEFAULT NULL,
  `8` varchar(100) DEFAULT NULL,
  `9` varchar(100) DEFAULT NULL,
  `10` varchar(100) DEFAULT NULL,
  `11` varchar(100) DEFAULT NULL,
  `12` varchar(100) DEFAULT NULL,
  `13` varchar(100) DEFAULT NULL,
  `14` varchar(100) DEFAULT NULL,
  `15` varchar(100) DEFAULT NULL,
  `16` varchar(100) DEFAULT NULL,
  `17` varchar(100) DEFAULT NULL,
  `18` varchar(100) DEFAULT NULL,
  `19` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`lineup_id`),
  CONSTRAINT `lineup_id_away_fk` FOREIGN KEY (`lineup_id`) REFERENCES `matches` (`match_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `away_lineup`
--

LOCK TABLES `away_lineup` WRITE;
/*!40000 ALTER TABLE `away_lineup` DISABLE KEYS */;
/*!40000 ALTER TABLE `away_lineup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel`
--

DROP TABLE IF EXISTS `channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel` (
  `channel_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `code` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channel`
--

LOCK TABLES `channel` WRITE;
/*!40000 ALTER TABLE `channel` DISABLE KEYS */;
INSERT INTO `channel` VALUES (1,'Sky Sports Football','    <iframe FRAMEBORDER=\"0\" MARGINWIDTH=\"0\" MARGINHEIGHT=\"0\" SCROLLING=\"NO\" WIDTH=\"620\" HEIGHT=\"460\" SRC=\"http://skyembed.com/skyspfootball.php\" ALLOWFULLSCREEN=\"allowfullscreen\"></iframe>\n'),(2,'BT Sport 1','    <iframe FRAMEBORDER=\"0\" MARGINWIDTH=\"0\" MARGINHEIGHT=\"0\" SCROLLING=\"NO\" WIDTH=\"620\" HEIGHT=\"460\" SRC=\"http://skyembed.com/btsport1.php\" ALLOWFULLSCREEN=\"allowfullscreen\"></iframe> ');
/*!40000 ALTER TABLE `channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clubs`
--

DROP TABLE IF EXISTS `clubs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clubs` (
  `club_id` int(11) NOT NULL AUTO_INCREMENT,
  `league_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`league_id`,`name`),
  UNIQUE KEY `club_id_UNIQUE` (`club_id`),
  KEY `league_id_club_fk_idx` (`league_id`),
  CONSTRAINT `league_id_club_fk` FOREIGN KEY (`league_id`) REFERENCES `leagues` (`league_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20143 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clubs`
--

LOCK TABLES `clubs` WRITE;
/*!40000 ALTER TABLE `clubs` DISABLE KEYS */;
/*!40000 ALTER TABLE `clubs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `countries` (
  `country_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `in_use` int(11) DEFAULT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (163,'Champions League',NULL),(164,'Europa League',NULL),(169,'England',NULL),(170,'Italy',NULL),(171,'Spain',NULL),(172,'Germany',NULL),(173,'France',NULL),(174,'Netherlands',NULL),(175,'Belgium',NULL),(176,'Portugal',NULL),(177,'Scotland',NULL),(185,'Austria',NULL),(186,'Cyprus',NULL),(187,'Denmark',NULL),(188,'Finland',NULL),(189,'Greece',NULL),(190,'Iceland',NULL),(191,'Ireland',NULL),(192,'Luxembourg',NULL),(193,'Norway',NULL),(194,'Northern Ireland',NULL),(195,'Sweden',NULL),(196,'Switzerland',NULL),(197,'Turkey',NULL),(198,'Wales',NULL),(199,'Belarus',NULL),(200,'Bosnia & Herz.',NULL),(201,'Bulgaria',NULL),(202,'Croatia',NULL),(203,'Czech Republic',NULL),(204,'Estonia',NULL),(205,'Hungary',NULL),(206,'Israel',NULL),(207,'Latvia',NULL),(208,'Lithuania',NULL),(210,'Moldova',NULL),(211,'Montenegro',NULL),(212,'Poland',NULL),(213,'Romania',NULL),(214,'Russia',NULL),(215,'Serbia',NULL),(216,'Slovakia',NULL),(217,'Slovenia',NULL),(218,'Ukraine',NULL);
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `home_lineup`
--

DROP TABLE IF EXISTS `home_lineup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `home_lineup` (
  `lineup_id` int(11) NOT NULL,
  `1` varchar(100) DEFAULT NULL,
  `2` varchar(100) DEFAULT NULL,
  `3` varchar(100) DEFAULT NULL,
  `4` varchar(100) DEFAULT NULL,
  `5` varchar(100) DEFAULT NULL,
  `6` varchar(100) DEFAULT NULL,
  `7` varchar(100) DEFAULT NULL,
  `8` varchar(100) DEFAULT NULL,
  `9` varchar(100) DEFAULT NULL,
  `10` varchar(100) DEFAULT NULL,
  `11` varchar(100) DEFAULT NULL,
  `12` varchar(100) DEFAULT NULL,
  `13` varchar(100) DEFAULT NULL,
  `14` varchar(100) DEFAULT NULL,
  `15` varchar(100) DEFAULT NULL,
  `16` varchar(100) DEFAULT NULL,
  `17` varchar(100) DEFAULT NULL,
  `18` varchar(100) DEFAULT NULL,
  `19` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`lineup_id`),
  CONSTRAINT `lineup_id_home_fk` FOREIGN KEY (`lineup_id`) REFERENCES `matches` (`match_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `home_lineup`
--

LOCK TABLES `home_lineup` WRITE;
/*!40000 ALTER TABLE `home_lineup` DISABLE KEYS */;
/*!40000 ALTER TABLE `home_lineup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leagues`
--

DROP TABLE IF EXISTS `leagues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leagues` (
  `league_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `league_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`league_id`),
  KEY `country_id_fk_idx` (`country_id`),
  CONSTRAINT `country_id_fk` FOREIGN KEY (`country_id`) REFERENCES `countries` (`country_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leagues`
--

LOCK TABLES `leagues` WRITE;
/*!40000 ALTER TABLE `leagues` DISABLE KEYS */;
INSERT INTO `leagues` VALUES (62,'Premier League',169,1),(63,'Championship',169,2),(79,'Serie A',170,1),(109,'Liga BBVA',171,1),(117,'Bundesliga',172,1),(127,'Ligue 1',173,1),(128,'Ligue 2',173,2),(137,'Eredivisie',174,1),(144,'Pro League',175,2),(150,'Primeira Liga',176,2),(176,'Premiership',177,2),(274,'Bundesliga',185,1),(280,'1st League',186,2),(284,'Superliga',187,2),(293,'Veikkausliiga',188,2),(306,'Super League',189,2),(325,'Urvalsdeild',190,2),(329,'Airtricity League',191,2),(334,'National Division',192,2),(337,'Tippeligaen',193,2),(345,'Premiership',194,2),(350,'Allsvenskan',195,2),(368,'Super League',196,2),(376,'Super Lig',197,2),(395,'Premier League',198,2),(400,'Premier',199,2),(403,'Premijer Liga',200,2),(407,'A PFG',201,2),(411,'1st League',202,2),(415,'Synot league',203,2),(419,'Premium liiga',204,2),(424,'OTP BANK Liga',205,2),(437,'Ligat HaAl',206,2),(441,'Virsliga',207,2),(444,'A Lyga',208,2),(447,'National Division',210,2),(450,'Telekom 1',211,2),(453,'Ekstraklasa',212,2),(459,'Liga 1',213,2),(469,'Premier League',214,1),(478,'Super Liga',215,2),(481,'Fortuna League',216,2),(486,'1st SNL',217,2),(490,'Premier League',218,2),(782,'Group A',163,3),(783,'Group B',163,3),(784,'Group C',163,3),(785,'Group D',163,3),(786,'Group E',163,3),(787,'Group F',163,3),(788,'Group G',163,3),(789,'Group H',163,3),(790,'Qualification',163,3),(791,'Group A',164,3),(792,'Group B',164,3),(793,'Group C',164,3),(794,'Group D',164,3),(795,'Group E',164,3),(796,'Group F',164,3),(797,'Group G',164,3),(798,'Group H',164,3),(799,'Group I',164,3),(800,'Group J',164,3),(801,'Group K',164,3),(802,'Group L',164,3),(803,'Qualification',164,NULL),(804,'Liga I:: championship',213,NULL),(805,'Liga I:: relegation',213,NULL),(924,'Eight Finals',163,3),(925,'1 Round',164,3),(978,'Eight Finals',164,3),(994,'Ligat HaAl:: championship',206,NULL),(995,'Ligat HaAl:: relegation',206,NULL),(996,'Quarter Finals',163,3),(997,'Quarter Finals',164,3),(1022,'Premier League Championship',218,NULL),(1023,'Premier League Relegation',218,NULL),(1068,'Final',163,3),(1069,'Semi Finals',163,3),(1085,'Final',164,3),(1086,'Semi Finals',164,3);
/*!40000 ALTER TABLE `leagues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matches` (
  `match_id` int(11) NOT NULL,
  `country_id` int(11) DEFAULT NULL,
  `league_id` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `home_team_name` varchar(100) DEFAULT NULL,
  `away_team_name` varchar(100) DEFAULT NULL,
  `home_team_score` varchar(15) DEFAULT NULL,
  `away_team_score` varchar(15) DEFAULT NULL,
  `home_team_half_score` varchar(15) DEFAULT NULL,
  `away_team_half_score` varchar(15) DEFAULT NULL,
  `home_team_extra_score` varchar(15) DEFAULT NULL,
  `away_team_extra_score` varchar(15) DEFAULT NULL,
  `home_team_pen_score` varchar(15) DEFAULT NULL,
  `away_team_pen_score` varchar(15) DEFAULT NULL,
  `match_live` varchar(10) DEFAULT NULL,
  `streams_status` int(11) DEFAULT NULL,
  `tweet_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`match_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matches`
--

LOCK TABLES `matches` WRITE;
/*!40000 ALTER TABLE `matches` DISABLE KEYS */;
/*!40000 ALTER TABLE `matches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msg_b`
--

DROP TABLE IF EXISTS `msg_b`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msg_b` (
  `msg_id` int(11) NOT NULL,
  `msg` varchar(200) DEFAULT NULL,
  `cat` int(11) DEFAULT NULL,
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msg_b`
--

LOCK TABLES `msg_b` WRITE;
/*!40000 ALTER TABLE `msg_b` DISABLE KEYS */;
INSERT INTO `msg_b` VALUES (1,'Need a stream for - ',1),(2,'Looking for a stream - ',1),(3,'Want to watch - ',1),(4,'Looking for a way to watch - ',1),(5,'Trying to find a stream -',1),(6,'Almost time to watch -',1),(7,'Need to find a stream for -',1),(8,'Come find a stream for - ',1),(9,'Half time for -',2),(10,'45 minutes gone in - ',2),(11,'45 minutes left to go in - ',2),(12,'half way gone in -',2);
/*!40000 ALTER TABLE `msg_b` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msg_e`
--

DROP TABLE IF EXISTS `msg_e`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msg_e` (
  `msg_id` int(11) NOT NULL,
  `msg` varchar(200) DEFAULT NULL,
  `cat` int(11) DEFAULT NULL,
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msg_e`
--

LOCK TABLES `msg_e` WRITE;
/*!40000 ALTER TABLE `msg_e` DISABLE KEYS */;
INSERT INTO `msg_e` VALUES (1,' - Look on my twitter account for a link',1),(2,'- Click on my profile to find a link ',1),(3,'- Look on CleanStreams for a link (Link on my profile)',1),(4,'- Find a free stream on CleanStreams ',1),(5,'- Click the link on my profile ',1),(6,'- Use CleanStreams to find a free stream ',1),(7,'- Grab a free stream on CleanStreams',1),(8,'- Browse for a free stream on CleanStreams',1),(9,'- CleanStreams may have a free one for you ',1),(10,'- There may be a free stream on clean streams',1);
/*!40000 ALTER TABLE `msg_e` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `standings`
--

DROP TABLE IF EXISTS `standings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `standings` (
  `league_id` int(11) NOT NULL,
  `club_id` int(11) NOT NULL,
  `country_name` varchar(100) DEFAULT NULL,
  `league_name` varchar(100) DEFAULT NULL,
  `team_name` varchar(100) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `played` varchar(5) DEFAULT NULL,
  `win` varchar(5) DEFAULT NULL,
  `draw` varchar(5) DEFAULT NULL,
  `lose` varchar(5) DEFAULT NULL,
  `points` varchar(5) DEFAULT NULL,
  `goals_for` varchar(5) DEFAULT NULL,
  `goals_against` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`league_id`,`club_id`),
  KEY `club_id_standings_fk_idx` (`club_id`),
  CONSTRAINT `club_id_standings_fk` FOREIGN KEY (`club_id`) REFERENCES `clubs` (`club_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `league_id_standings_fk` FOREIGN KEY (`league_id`) REFERENCES `leagues` (`league_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `standings`
--

LOCK TABLES `standings` WRITE;
/*!40000 ALTER TABLE `standings` DISABLE KEYS */;
/*!40000 ALTER TABLE `standings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `streams`
--

DROP TABLE IF EXISTS `streams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `streams` (
  `stream_id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL,
  `score` varchar(45) DEFAULT NULL,
  `name` varchar(500) DEFAULT NULL,
  `match_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`stream_id`),
  KEY `match_id_streams_fk_idx` (`match_id`),
  CONSTRAINT `match_id_streams_fk` FOREIGN KEY (`match_id`) REFERENCES `matches` (`match_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=283 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `streams`
--

LOCK TABLES `streams` WRITE;
/*!40000 ALTER TABLE `streams` DISABLE KEYS */;
/*!40000 ALTER TABLE `streams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `substitutions`
--

DROP TABLE IF EXISTS `substitutions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `substitutions` (
  `substitution_id` int(11) NOT NULL,
  `match_id` int(11) DEFAULT NULL,
  `team_id` varchar(45) DEFAULT NULL,
  `player_swap` varchar(100) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`substitution_id`),
  KEY `match_id_substitutions_fk_idx` (`match_id`),
  CONSTRAINT `match_id_substitutions_fk` FOREIGN KEY (`match_id`) REFERENCES `matches` (`match_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `substitutions`
--

LOCK TABLES `substitutions` WRITE;
/*!40000 ALTER TABLE `substitutions` DISABLE KEYS */;
/*!40000 ALTER TABLE `substitutions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-31 20:03:21
