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
INSERT INTO `channel` VALUES (1,'Sky Sports Football','<script type=\'text/javascript\'>id=\'skyfb\'; id_width=640; id_height=490; id_p=2;</script><script type=\'text/javascript\' src=\'http://cdn.crichd.im/channel.js\'></script>\n');
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
) ENGINE=InnoDB AUTO_INCREMENT=15667 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clubs`
--

LOCK TABLES `clubs` WRITE;
/*!40000 ALTER TABLE `clubs` DISABLE KEYS */;
INSERT INTO `clubs` VALUES (1,782,'Borussia Dortmund'),(2,782,'Atletico Madrid'),(3,782,'Monaco'),(4,782,'Club Brugge'),(5,783,'Barcelona'),(6,783,'Inter'),(7,783,'Tottenham Hotspur'),(8,783,'PSV Eindhoven'),(9,784,'Liverpool'),(10,784,'Napoli'),(11,784,'Paris Saint Germain'),(12,784,'FK Crvena Zvezda'),(13,785,'FC Porto'),(14,785,'Schalke 04'),(15,785,'Galatasaray'),(16,785,'Lokomotiv Moscow'),(17,786,'Ajax'),(18,786,'FC Bayern MÃ¼nchen'),(19,786,'Benfica'),(20,786,'AEK Athens'),(21,787,'Manchester City'),(22,787,'Lyon'),(23,787,'Hoffenheim'),(24,787,'Shakhtar Donetsk'),(25,788,'Real Madrid'),(26,788,'Roma'),(27,788,'CSKA Moscow'),(28,788,'Viktoria Plzen'),(29,789,'Juventus'),(30,789,'Manchester United'),(31,789,'Valencia'),(32,789,'Young Boys'),(33,791,'FC Zuerich'),(34,791,'Bayer Leverkusen'),(35,791,'Ludogorets Razgrad'),(36,791,'AEK Larnaca'),(37,792,'Salzburg'),(38,792,'RasenBallsport Leipzig'),(39,792,'Celtic'),(40,792,'Rosenborg'),(41,793,'Zenit St. Petersburg'),(42,793,'Slavia Prague'),(43,793,'FC Koebenhavn'),(44,793,'Bordeaux'),(45,794,'Dinamo Zagreb'),(46,794,'Fenerbahce'),(47,794,'Spartak Trnava'),(48,794,'Anderlecht'),(49,795,'Arsenal'),(50,795,'Sporting CP'),(51,795,'Vorskla'),(52,795,'Qarabag FK'),(53,796,'Real Betis'),(54,796,'Milan'),(55,796,'Olympiacos'),(56,796,'F91 Dudelange'),(57,797,'Rangers'),(58,797,'Villarreal'),(59,797,'Rapid Wien'),(60,797,'Spartak Moscow'),(61,798,'Eintracht Frankfurt'),(62,798,'Lazio'),(63,798,'Marseille'),(64,798,'Apollon Limassol'),(65,799,'Genk'),(66,799,'Malmoe FF'),(67,799,'Sarpsborg 08'),(68,799,'Besiktas'),(69,800,'Sevilla'),(70,800,'FC Krasnodar'),(71,800,'Standard Liege'),(72,800,'Akhisarspor'),(73,801,'FC Astana'),(74,801,'Dynamo Kyiv'),(75,801,'Rennes'),(76,801,'Jablonec'),(77,802,'Chelsea'),(78,802,'PAOK Thessaloniki FC'),(79,802,'Vidi FC'),(80,802,'BATE Borisov'),(81,62,'Liverpool'),(82,62,'Chelsea'),(83,62,'Manchester City'),(84,62,'Arsenal'),(85,62,'Tottenham Hotspur'),(86,62,'AFC Bournemouth'),(87,62,'Watford'),(88,62,'Manchester United'),(89,62,'Everton'),(90,62,'Wolverhampton Wanderers'),(91,62,'Brighton &amp; Hove Albion'),(92,62,'Leicester City'),(93,62,'West Ham United'),(94,62,'Crystal Palace'),(95,62,'Burnley'),(96,62,'Southampton'),(97,62,'Cardiff City'),(98,62,'Fulham'),(99,62,'Newcastle United'),(100,62,'Huddersfield Town'),(101,63,'Sheffield United'),(102,63,'Leeds United'),(103,63,'Middlesbrough'),(104,63,'Norwich City'),(105,63,'West Bromwich Albion'),(106,63,'Derby County'),(107,63,'Nottingham Forest'),(108,63,'Swansea City'),(109,63,'Birmingham City'),(110,63,'Queens Park Rangers'),(111,63,'Bristol City'),(112,63,'Blackburn Rovers'),(113,63,'Stoke City'),(114,63,'Wigan Athletic'),(115,63,'Sheffield Wednesday'),(116,63,'Brentford'),(117,63,'Aston Villa'),(118,63,'Millwall'),(119,63,'Bolton Wanderers'),(120,63,'Preston North End'),(121,63,'Rotherham United'),(122,63,'Reading'),(123,63,'Hull City'),(124,63,'Ipswich Town'),(125,79,'Juventus'),(126,79,'Napoli'),(127,79,'Inter'),(128,79,'Lazio'),(129,79,'Fiorentina'),(130,79,'Sampdoria'),(131,79,'Milan'),(132,79,'Roma'),(133,79,'Sassuolo'),(134,79,'Torino'),(135,79,'Genoa'),(136,79,'Parma'),(137,79,'Cagliari'),(138,79,'SPAL'),(139,79,'Atalanta'),(140,79,'Bologna'),(141,79,'Udinese'),(142,79,'Empoli'),(143,79,'Frosinone'),(144,79,'Chievo'),(145,109,'Barcelona'),(146,109,'Deportivo Alaves'),(147,109,'Sevilla'),(148,109,'Atletico Madrid'),(149,109,'RCD Espanyol'),(150,109,'Real Valladolid'),(151,109,'Levante'),(152,109,'Getafe'),(153,109,'Real Madrid'),(154,109,'Celta Vigo'),(155,109,'Girona'),(156,109,'Real Sociedad'),(157,109,'Real Betis'),(158,109,'Valencia CF'),(159,109,'Eibar'),(160,109,'Athletic Bilbao'),(161,109,'Villarreal'),(162,109,'Leganes'),(163,109,'Rayo Vallecano'),(164,109,'SD Huesca'),(165,117,'Borussia Dortmund'),(166,117,'FC Bayern MÃ¼nchen'),(167,117,'Borussia MÃ¶nchengladbach'),(168,117,'Werder Bremen'),(169,117,'RasenBallsport Leipzig'),(170,117,'Hertha Berlin'),(171,117,'Eintracht Frankfurt'),(172,117,'Hoffenheim'),(173,117,'Augsburg'),(174,117,'Wolfsburg'),(175,117,'Freiburg'),(176,117,'Bayer Leverkusen'),(177,117,'Mainz 05'),(178,117,'1. FC NÃ¼rnberg'),(179,117,'Schalke 04'),(180,117,'Hannover 96'),(181,117,'Fortuna DÃ¼sseldorf'),(182,117,'VfB Stuttgart'),(183,127,'Paris Saint Germain'),(184,127,'Lille'),(185,127,'Montpellier'),(186,127,'Lyon'),(187,127,'Marseille'),(188,127,'Saint-Etienne'),(189,127,'Strasbourg'),(190,127,'Bordeaux'),(191,127,'Reims'),(192,127,'Nice'),(193,127,'Toulouse FC'),(194,127,'Angers'),(195,127,'Nantes'),(196,127,'Rennes'),(197,127,'Caen'),(198,127,'Nimes'),(199,127,'Dijon'),(200,127,'Amiens'),(201,127,'Monaco'),(202,127,'Guingamp'),(203,128,'FC Metz'),(204,128,'Lens'),(205,128,'Brest'),(206,128,'FC Lorient'),(207,128,'Orleans'),(208,128,'Niort'),(209,128,'Paris FC'),(210,128,'Grenoble'),(211,128,'Le Havre'),(212,128,'Troyes'),(213,128,'Clermont Foot'),(214,128,'AS Beziers'),(215,128,'AC Ajaccio'),(216,128,'Sochaux'),(217,128,'Valenciennes FC'),(218,128,'Chateauroux'),(219,128,'Gazelec Ajaccio'),(220,128,'AJ Auxerre'),(221,128,'Red Star'),(222,128,'Nancy'),(223,137,'PSV Eindhoven'),(224,137,'Ajax'),(225,137,'Feyenoord'),(226,137,'Heracles'),(227,137,'Vitesse'),(228,137,'VVV-Venlo'),(229,137,'FC Utrecht'),(230,137,'SC Heerenveen'),(231,137,'AZ Alkmaar'),(232,137,'Excelsior'),(233,137,'ADO Den Haag'),(234,137,'PEC Zwolle'),(235,137,'Willem II'),(236,137,'Fortuna Sittard'),(237,137,'De Graafschap'),(238,137,'FC Emmen'),(239,137,'NAC Breda'),(240,137,'FC Groningen'),(241,144,'KRC Genk'),(242,144,'Club Brugge'),(243,144,'Royal Antwerp'),(244,144,'RSC Anderlecht'),(245,144,'Gent'),(246,144,'Standard Liege'),(247,144,'St.Truiden'),(248,144,'Kortrijk'),(249,144,'Oostende'),(250,144,'Sporting Charleroi'),(251,144,'Eupen'),(252,144,'Cercle Brugge'),(253,144,'Waasland-Beveren'),(254,144,'Royal Excel Mouscron'),(255,144,'Lokeren'),(256,144,'Zulte-Waregem'),(257,150,'FC Porto'),(258,150,'Braga'),(259,150,'Benfica'),(260,150,'Rio Ave'),(261,150,'Sporting CP'),(262,150,'Santa Clara'),(263,150,'Vitoria de Setubal'),(264,150,'Vitoria de Guimaraes'),(265,150,'Belenenses'),(266,150,'Portimonense'),(267,150,'Maritimo'),(268,150,'Moreirense'),(269,150,'Feirense'),(270,150,'Boavista'),(271,150,'Chaves'),(272,150,'Tondela'),(273,150,'Nacional'),(274,150,'Aves'),(275,176,'Hearts'),(276,176,'Kilmarnock'),(277,176,'Celtic'),(278,176,'Livingston'),(279,176,'Rangers'),(280,176,'Hibernian'),(281,176,'St.Johnstone'),(282,176,'Aberdeen'),(283,176,'Hamilton Academical'),(284,176,'Motherwell'),(285,176,'St. Mirren'),(286,176,'Dundee FC'),(287,274,'Salzburg'),(288,274,'LASK'),(289,274,'SKN St. Poelten'),(290,274,'Wolfsberger AC'),(291,274,'Austria Wien'),(292,274,'Hartberg'),(293,274,'Rapid Wien'),(294,274,'SK Sturm Graz'),(295,274,'Mattersburg'),(296,274,'FC Wacker Innsbruck'),(297,274,'Altach'),(298,274,'Admira Wacker'),(299,280,'AEL Limassol'),(300,280,'Apollon Limassol'),(301,280,'APOEL Nicosia'),(302,280,'Nea Salamis'),(303,280,'AEK Larnaca'),(304,280,'Doxa Katokopia'),(305,280,'Anorthosis'),(306,280,'Omonia Nicosia'),(307,280,'Alki Oroklini'),(308,280,'Enosis Paralimni'),(309,280,'Pafos FC'),(310,280,'Ermis Aradippou'),(311,284,'FC Koebenhavn'),(312,284,'FC Midtjylland'),(313,284,'Esbjerg FB'),(314,284,'AC Horsens'),(315,284,'AaB'),(316,284,'Broendby If'),(317,284,'AGF'),(318,284,'SonderjyskE'),(319,284,'FC Nordsjaelland'),(320,284,'OB'),(321,284,'Randers FC'),(322,284,'Vendsyssel FF'),(323,284,'Vejle Boldklub'),(324,284,'Hobro'),(325,293,'HJK'),(326,293,'RoPS'),(327,293,'KuPS'),(328,293,'Honka'),(329,293,'Ilves'),(330,293,'VPS'),(331,293,'FC Lahti'),(332,293,'FC Inter'),(333,293,'SJK'),(334,293,'IFK Mariehamn'),(335,293,'TPS'),(336,293,'PS Kemi'),(337,306,'Atromitos'),(338,306,'PAOK Thessaloniki FC'),(339,306,'AEK Athens'),(340,306,'Panathinaikos'),(341,306,'Olympiacos'),(342,306,'Xanthi FC'),(343,306,'Panetolikos'),(344,306,'Aris Thessaloniki'),(345,306,'Lamia'),(346,306,'Panionios'),(347,306,'Larisa Ael'),(348,306,'OFI Crete'),(349,306,'PAS Giannina'),(350,306,'Asteras Tripolis'),(351,306,'Levadiakos'),(352,306,'Apollon Smyrnis'),(353,325,'Valur'),(354,325,'Breidablik'),(355,325,'Stjarnan'),(356,325,'KR Reykjavik'),(357,325,'FH Hafnarfjordur'),(358,325,'IBV Vestmannaeyjar'),(359,325,'KA Akureyri'),(360,325,'Fylkir'),(361,325,'Vikingur Reykjavik'),(362,325,'Grindavik'),(363,325,'Fjoelnir'),(364,325,'Keflavik'),(365,329,'Dundalk'),(366,329,'Cork City'),(367,329,'Shamrock Rovers'),(368,329,'Waterford FC'),(369,329,'St. Patrick\'s Athletic'),(370,329,'Bohemian FC'),(371,329,'Sligo Rovers'),(372,329,'Derry City'),(373,329,'Limerick'),(374,329,'Bray Wanderers'),(375,334,'AS Jeunesse Esch'),(376,334,'Racing FC Union Luxembourg'),(377,334,'FC Differdange 03'),(378,334,'F91 Dudelange'),(379,334,'Union Titus Petange'),(380,334,'CS Fola Esch'),(381,334,'FC Progres Niedercorn'),(382,334,'FC Victoria Rosport'),(383,334,'FC Etzella Ettelbruck'),(384,334,'US Mondorf les Bains'),(385,334,'US Hostert'),(386,334,'RM Hamm Benfica'),(387,334,'Una Strassen'),(388,334,'US Rumelange'),(389,337,'Rosenborg'),(390,337,'Brann'),(391,337,'Molde'),(392,337,'FK Haugesund'),(393,337,'Ranheim'),(394,337,'Odds Ballklubb'),(395,337,'Kristiansund BK'),(396,337,'Vaalerenga'),(397,337,'Sarpsborg 08'),(398,337,'Tromsoe'),(399,337,'Stroemsgodset'),(400,337,'Bodoe/Glimt'),(401,337,'Start'),(402,337,'Lillestroem'),(403,337,'Stabaek'),(404,337,'Sandefjord'),(405,345,'Glenavon'),(406,345,'Linfield'),(407,345,'Crusaders'),(408,345,'Ballymena United'),(409,345,'Coleraine'),(410,345,'Cliftonville'),(411,345,'Glentoran'),(412,345,'Institute'),(413,345,'Warrenpoint Town'),(414,345,'Ards'),(415,345,'Newry City AFC'),(416,345,'Dungannon Swifts'),(417,350,'AIK'),(418,350,'IFK Norrkoeping'),(419,350,'Hammarby'),(420,350,'Haecken'),(421,350,'Malmoe FF'),(422,350,'Oestersunds FK'),(423,350,'GIF Sundsvall'),(424,350,'Djurgaarden'),(425,350,'Oerebro'),(426,350,'Kalmar FF'),(427,350,'Elfsborg'),(428,350,'Sirius'),(429,350,'IFK Gothenburg'),(430,350,'Dalkurd FF'),(431,350,'Brommapojkarna'),(432,350,'Trelleborgs FF'),(433,368,'Young Boys'),(434,368,'FC Basel 1893'),(435,368,'Thun'),(436,368,'FC Zuerich'),(437,368,'FC St. Gallen 1879'),(438,368,'Lugano'),(439,368,'Luzern'),(440,368,'Sion'),(441,368,'Grasshoppers'),(442,368,'Xamax'),(443,376,'Istanbul Basaksehir'),(444,376,'Kasimpasa'),(445,376,'Galatasaray'),(446,376,'Antalyaspor'),(447,376,'Ankaragucu'),(448,376,'Trabzonspor'),(449,376,'Besiktas'),(450,376,'Yeni Malatyaspor'),(451,376,'Goztepe'),(452,376,'Konyaspor'),(453,376,'Bursaspor'),(454,376,'Kayserispor'),(455,376,'Alanyaspor'),(456,376,'Sivasspor'),(457,376,'Fenerbahce'),(458,376,'Rizespor'),(459,376,'Erzurum BB'),(460,376,'Akhisar Bld Spor'),(461,395,'TNS'),(462,395,'Connah\'s Quay'),(463,395,'Barry Town United FC'),(464,395,'Newtown'),(465,395,'Bala Town FC'),(466,395,'Cardiff Met University'),(467,395,'Aberystwyth'),(468,395,'Caernarfon Town FC'),(469,395,'Cefn Druids'),(470,395,'Llandudno FC'),(471,395,'Carmarthen'),(472,395,'Llanelli AFC'),(473,400,'BATE Borisov'),(474,400,'FK Vitebsk'),(475,400,'Shakhtyor Soligorsk'),(476,400,'Dinamo Minsk'),(477,400,'Torpedo Zhodino'),(478,400,'Dinamo Brest'),(479,400,'Neman Grodno'),(480,400,'FK Slutsk'),(481,400,'FC Minsk'),(482,400,'Isloch'),(483,400,'Gomel'),(484,400,'FK Gorodeya'),(485,400,'Smolevichy'),(486,400,'FC Luch Minsk'),(487,400,'FC Torpedo Minsk'),(488,400,'Dnepr Mogilev'),(489,403,'FK Zeljeznicar'),(490,403,'FK Sarajevo'),(491,403,'Hsk Zrinjski Mostar'),(492,403,'FK Mladost Doboj Kakanj'),(493,403,'Siroki Brijeg'),(494,403,'FK Sloboda Tuzla'),(495,403,'FK Zvijezda 09'),(496,403,'FK Radnik Bijeljina'),(497,403,'FK Tuzla City'),(498,403,'NK Gosk Gabela'),(499,403,'FK Krupa'),(500,403,'Celik Zenica'),(501,407,'Ludogorets 1945 Razgrad'),(502,407,'Levski Sofia'),(503,407,'PFC CSKA-Sofia'),(504,407,'Beroe'),(505,407,'Botev Plovdiv'),(506,407,'Lokomotiv Plovdiv'),(507,407,'Cherno More Varna'),(508,407,'Etar'),(509,407,'Slavia Sofia'),(510,407,'Vitosha Bistritsa'),(511,407,'OFC Botev Vratsa'),(512,407,'Dunav Ruse'),(513,407,'FK Septemvri Sofia'),(514,407,'FC Vereya'),(515,411,'Dinamo Zagreb'),(516,411,'NK Lokomotiva'),(517,411,'NK Osijek'),(518,411,'HNK Gorica'),(519,411,'Rijeka'),(520,411,'Slaven'),(521,411,'Hajduk Split'),(522,411,'Inter Zapresic'),(523,411,'NK Istra 1961'),(524,411,'Rudes'),(525,415,'FC Viktoria Plzen'),(526,415,'Slavia Prague'),(527,415,'FC Banik Ostrava'),(528,415,'AC Sparta Prague'),(529,415,'Zlin'),(530,415,'Jablonec'),(531,415,'FC Slovan Liberec'),(532,415,'FK Mlada Boleslav'),(533,415,'Bohemians Prague 1905'),(534,415,'Pribram'),(535,415,'Opava'),(536,415,'SK Sigma Olomouc'),(537,415,'Teplice'),(538,415,'Karvina'),(539,415,'FK Dukla Prague'),(540,415,'1 FC Slovacko'),(541,419,'Flora Tallinn'),(542,419,'Nomme JK Kalju'),(543,419,'FCI Levadia'),(544,419,'Trans Narva'),(545,419,'Paide Linnameeskond'),(546,419,'Tammeka'),(547,419,'Tulevik Viljandi'),(548,419,'Talinna Kalev'),(549,419,'FC Kuressaare'),(550,419,'Paernu Linnameeskond'),(551,424,'Ferencvaros'),(552,424,'Budapest Honved'),(553,424,'MTK Budapest'),(554,424,'Debrecen'),(555,424,'Videoton FC'),(556,424,'Paksi FC'),(557,424,'Mezokovesd SE'),(558,424,'Ujpest'),(559,424,'Puskas FC Academy'),(560,424,'Kisvarda FC'),(561,424,'Diosgyori VTK'),(562,424,'Haladas'),(563,437,'Maccabi Tel Aviv FC'),(564,437,'Hapoel Hadera'),(565,437,'Bnei Yehuda Tel Aviv FC'),(566,437,'Hapoel Ironi Kiryat Shmona'),(567,437,'Hapoel Beer Sheva FC'),(568,437,'Maccabi Petach Tikva'),(569,437,'Maccabi Netanya FC'),(570,437,'FC Ashdod'),(571,437,'Hapoel Raanana FC'),(572,437,'Hapoel Tel Aviv FC'),(573,437,'Bnei Sakhnin'),(574,437,'Hapoel Haifa FC'),(575,437,'Maccabi Haifa FC'),(576,437,'Beitar Jerusalem FC'),(577,994,'Hapoel Beer Sheva'),(578,994,'Maccabi Tel Aviv'),(579,994,'Beitar Trump Jerusalem'),(580,994,'Hapoel Haifa'),(581,994,'Maccabi Netanya'),(582,994,'Bnei Yehuda Tel Aviv'),(583,995,'Hapoel Ironi Kiryat Shmona'),(584,995,'Maccabi Petach Tikva'),(585,995,'Hapoel Raanana'),(586,995,'Maccabi Haifa'),(587,995,'Bnei Sakhnin'),(588,995,'FC Ashdod'),(589,995,'Hapoel Ashkelon'),(590,995,'Hapoel Ironi Akko'),(591,441,'Riga FC'),(592,441,'RFS'),(593,441,'Ventspils'),(594,441,'FK Liepaja'),(595,441,'FK Spartaks'),(596,441,'FS Metta/LU'),(597,441,'FK Jelgava'),(598,441,'Valmieras FK'),(599,444,'Suduva'),(600,444,'Zalgiris Vilnius'),(601,444,'Trakai FK'),(602,444,'Stumbras Kaunas'),(603,444,'FK Kauno Zalgiris'),(604,444,'Atlantas Klaipeda'),(605,444,'FK Palanga'),(606,444,'Lietava Jonava'),(607,447,'FC Sheriff'),(608,447,'FC Milsami Orhei'),(609,447,'CS Petrocub'),(610,447,'Speranta'),(611,447,'FC Sfintul Gheorghe'),(612,447,'Zimbru'),(613,447,'Dinamo-Auto'),(614,447,'FC Zaria'),(615,450,'Buducnost Podgorica'),(616,450,'OFK Titograd'),(617,450,'Zeta'),(618,450,'Sutjeska'),(619,450,'Petrovac'),(620,450,'FK Iskra'),(621,450,'Rudar Pljevlja'),(622,450,'Grbalj Radanovici'),(623,450,'FK Lovcen'),(624,450,'Mornar'),(625,453,'KS Lechia Gdansk'),(626,453,'Jagiellonia Bialystok'),(627,453,'Legia Warsaw'),(628,453,'Piast Gliwice'),(629,453,'Wisla Krakow'),(630,453,'Lech Poznan'),(631,453,'Korona Kielce SA'),(632,453,'Arka Gdynia 1929'),(633,453,'KGHM Zaglebie Lubin'),(634,453,'Pogon Szczecin'),(635,453,'WKS Slask Wroclaw'),(636,453,'Cracovia'),(637,453,'Wisla Plock SA'),(638,453,'Gornik Zabrze'),(639,453,'ASPN Miedz Legnica'),(640,453,'Zaglebie Sosnowiec SA'),(641,459,'CFR Cluj'),(642,459,'FC FCSB'),(643,459,'CS Universitatea Craiova'),(644,459,'FC Viitorul Constanta'),(645,459,'Gaz Metan Medias'),(646,459,'Astra Giurgiu'),(647,459,'Sepsi OSK'),(648,459,'FC Dunarea Calarasi'),(649,459,'FC Botosani'),(650,459,'CSM Politehnica Iasi'),(651,459,'Concordia Chiajna'),(652,459,'Dinamo Bucuresti'),(653,459,'AFC Hermannstadt'),(654,459,'FC Voluntari'),(655,804,'CFR Cluj'),(656,804,'FC FCSB'),(657,804,'CS Universitatea Craiova'),(658,804,'FC Viitorul Constanta'),(659,804,'Astra Giurgiu'),(660,804,'CSMS Iasi'),(661,805,'Dinamo Bucuresti'),(662,805,'Botosani'),(663,805,'Sepsi OSK'),(664,805,'Gaz Metan Medias'),(665,805,'Concordia Chiajna'),(666,805,'FC Voluntari'),(667,805,'ACS Poli Timisoara'),(668,805,'Juventus Bucuresti'),(669,469,'Zenit St. Petersburg'),(670,469,'FC Krasnodar'),(671,469,'FC Lokomotiv Moscow'),(672,469,'FC Rostov'),(673,469,'CSKA Moscow'),(674,469,'FC Spartak Moscow'),(675,469,'FC Rubin Kazan'),(676,469,'FC Orenburg'),(677,469,'Republican FC Akhmat Grozny'),(678,469,'FC Arsenal Tula'),(679,469,'FC Dinamo Moscow'),(680,469,'Ural'),(681,469,'Krylya Sovetov Samara'),(682,469,'FC Ufa'),(683,469,'FC Anzhi Makhachkala'),(684,469,'FK Yenisey Krasnoyarsk'),(685,478,'FK Red Star Belgrade'),(686,478,'Radnicki Nis'),(687,478,'FK Partizan'),(688,478,'Cukaricki'),(689,478,'Mladost Lucani'),(690,478,'Proleter'),(691,478,'FK Napredak Krusevac'),(692,478,'Vojvodina'),(693,478,'Rad Beograd'),(694,478,'Backa Backa Palanka'),(695,478,'Macva Sabac'),(696,478,'FK Zemun'),(697,478,'Vozdovac'),(698,478,'FK Radnik Surdulica'),(699,478,'FK Spartak Subotica'),(700,478,'FK Dinamo Vranje'),(701,481,'Slovan Bratislava'),(702,481,'DAC 1904 Dunajska Streda'),(703,481,'Zilina'),(704,481,'Ruzomberok'),(705,481,'Trencin'),(706,481,'SKF Sered'),(707,481,'Zeleziarne Podbrezova'),(708,481,'Spartak Trnava'),(709,481,'Nitra'),(710,481,'Zemplin Michalovce'),(711,481,'Zlate Moravce'),(712,481,'FK Senica'),(713,486,'Maribor'),(714,486,'NK Olimpija Ljubljana'),(715,486,'Gorica'),(716,486,'NK Celje'),(717,486,'Aluminij'),(718,486,'Domzale'),(719,486,'NS Mura'),(720,486,'Rudar Velenje'),(721,486,'NK Krsko'),(722,486,'NK Triglav Kranj'),(723,490,'FC Shakhtar Donetsk'),(724,490,'FC Dynamo Kiev'),(725,490,'Oleksandriya'),(726,490,'Vorskla'),(727,490,'FC Lviv'),(728,490,'FC Mariupol'),(729,490,'Desna'),(730,490,'Zorya Luhansk'),(731,490,'FC Olimpik Donetsk'),(732,490,'Karpaty Lviv'),(733,490,'FC Chernomorets Odessa'),(734,490,'FC Arsenal Kiev'),(735,1022,'Shakhtar Donetsk'),(736,1022,'Dynamo Kyiv'),(737,1022,'Vorskla'),(738,1022,'Zorya'),(739,1022,'Mariupol'),(740,1022,'FC Lviv'),(741,1023,'FC Olexandria'),(742,1023,'Karpaty'),(743,1023,'Olimpik Donetsk'),(744,1023,'Zirka'),(745,1023,'Chornomorets Odesa'),(746,1023,'Feniks Bucha');
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
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=latin1;
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

-- Dump completed on 2018-10-30 22:03:51
