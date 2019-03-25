-- MySQL dump 10.17  Distrib 10.3.13-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: Migi_DB
-- ------------------------------------------------------
-- Server version	10.3.13-MariaDB-1:10.3.13+maria~bionic-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `historique`
--

DROP TABLE IF EXISTS `historique`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historique` (
  `id_user` int(11) NOT NULL,
  `id_note` int(11) NOT NULL,
  `num_revision` int(11) NOT NULL,
  `coef_revision` double(10,2) NOT NULL DEFAULT 2.50,
  KEY `id_user` (`id_user`),
  KEY `id_note` (`id_note`),
  CONSTRAINT `historique_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `historique_ibfk_2` FOREIGN KEY (`id_note`) REFERENCES `note` (`id_note`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historique`
--

LOCK TABLES `historique` WRITE;
/*!40000 ALTER TABLE `historique` DISABLE KEYS */;
/*!40000 ALTER TABLE `historique` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note` (
  `id_user` int(11) NOT NULL,
  `id_note` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `have_revision` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id_note`),
  KEY `fk_id_user` (`id_user`),
  CONSTRAINT `fk_id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note_revision`
--

DROP TABLE IF EXISTS `note_revision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note_revision` (
  `id_user` int(11) NOT NULL,
  `id_note` int(11) NOT NULL,
  `date_revision` date NOT NULL,
  KEY `id_user` (`id_user`),
  KEY `id_note` (`id_note`),
  CONSTRAINT `note_revision_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `note_revision_ibfk_2` FOREIGN KEY (`id_note`) REFERENCES `note` (`id_note`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note_revision`
--

LOCK TABLES `note_revision` WRITE;
/*!40000 ALTER TABLE `note_revision` DISABLE KEYS */;
/*!40000 ALTER TABLE `note_revision` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note_tag`
--

DROP TABLE IF EXISTS `note_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note_tag` (
  `id_user` int(11) DEFAULT NULL,
  `id_note` int(11) DEFAULT NULL,
  `id_tag` int(11) DEFAULT NULL,
  KEY `fk_id_user_tab_note_tag` (`id_user`),
  KEY `fk_id_note_tab_note_tag` (`id_note`),
  KEY `fk_id_tag_tab_note_tag` (`id_tag`),
  CONSTRAINT `fk_id_note_tab_note_tag` FOREIGN KEY (`id_note`) REFERENCES `note` (`id_note`),
  CONSTRAINT `fk_id_tag_tab_note_tag` FOREIGN KEY (`id_tag`) REFERENCES `tag` (`id_tag`),
  CONSTRAINT `fk_id_user_tab_note_tag` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note_tag`
--

LOCK TABLES `note_tag` WRITE;
/*!40000 ALTER TABLE `note_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `note_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id_user` int(11) DEFAULT NULL,
  `id_tag` int(11) NOT NULL AUTO_INCREMENT,
  `text_tag` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `color_tag` varchar(8) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '9a67ea',
  PRIMARY KEY (`id_tag`),
  UNIQUE KEY `text_tag` (`text_tag`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `fk_id_user_tab_Tag` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `tag_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nickname` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `u_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'Migi_DB'
--
/*!50003 DROP PROCEDURE IF EXISTS `agregar_revision` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`migi`@`localhost` PROCEDURE `agregar_revision`(IN `id_u` INT, IN `id_n` INT, IN `q` INT)
BEGIN 
        DECLARE existeNota INT;
            DECLARE coef DOUBLE;
            DECLARE ef DOUBLE;
            DECLARE proxRevision INT DEFAULT 0;
            DECLARE anteriorRevision INT DEFAULT 0;
                        

            SELECT COUNT(*) INTO existeNota FROM historique WHERE id_user = id_u and id_note = id_n;
           
            IF existeNota>1 THEN
            SELECT prox_revision INTO anteriorRevision FROM historique WHERE id_user = id_u and id_note = id_n and num_revision=existeNota;
            END IF;
            
            IF existeNota = 0 THEN
            
            
            UPDATE note SET have_revision = true where id_user = id_u AND id_note = id_n;
            
            SET ef = 2.5;
                SET coef = (ef-0.8+(0.28*q)-(0.02*q*q));
                IF coef < 1.3 THEN 
                 SET coef = 1.3 ;
                END IF;
            INSERT INTO historique VALUES(id_u, id_n, 1,coef,CURDATE(), 1);
                INSERT INTO note_revision VALUES(id_u, id_n, CURDATE() +1 );
                
                
           
            ELSEIF existeNota > 1 AND (q < 3 OR anteriorRevision = 1) THEN
            
                SELECT prox_revision INTO proxRevision FROM historique WHERE id_user = id_u and id_note = id_n and num_revision=existeNota;
                SELECT coef_revision INTO ef FROM historique WHERE id_user = id_u and id_note = id_n and num_revision = existeNota;
 IF q < 3 THEN
                
                     SET coef = (ef-0.8+(0.28*q)-(0.02*q*q));
                    IF coef < 1.3 THEN 
                        SET coef = 1.3 ;
                    END IF;
                    INSERT INTO historique VALUES(id_u, id_n, existeNota +1 ,coef,CURDATE(), 1);
                    INSERT INTO note_revision VALUES(id_u, id_n, CURDATE() +  INTERVAL 1 DAY);
                ELSE 
                  
                    INSERT INTO historique VALUES(id_u, id_n, existeNota +1 ,ef,CURDATE(), 6);
                    INSERT INTO note_revision VALUES(id_u, id_n, CURDATE() +  INTERVAL 6 DAY);
                
                
                END IF;
            
            

            ELSEIF existeNota = 1 THEN 
 SELECT coef_revision INTO ef FROM historique WHERE id_user = id_u and id_note = id_n and num_revision = 1;
                
                SET coef = (ef-0.8+(0.28*q)-(0.02*q*q));
                IF coef < 1.3 THEN 
                 SET coef = 1.3 ;
                END IF;
            INSERT INTO historique VALUES(id_u, id_n, existeNota +1 ,coef,CURDATE(), 6);
                INSERT INTO note_revision VALUES(id_u, id_n, CURDATE() + 6);
 
            ELSE 
            
            SELECT coef_revision INTO ef FROM historique WHERE id_user = id_u and id_note = id_n and num_revision = existeNota ;
                
           SELECT prox_revision INTO proxRevision FROM historique WHERE id_user = id_u and id_note = id_n and num_revision=existeNota;
                
                SET coef = (ef-0.8+(0.28*q)-(0.02*q*q));
                IF coef < 1.3 THEN 
                 SET coef = 1.3 ;
                END IF;
            INSERT INTO historique VALUES(id_u, id_n,existeNota +1 ,coef,CURDATE(), ef*proxRevision);
          SELECT prox_revision INTO proxRevision FROM historique WHERE id_user = id_u and id_note = id_n and num_revision=existeNota+1;

                INSERT INTO note_revision VALUES(id_u, id_n, CURDATE() + INTERVAL proxRevision DAY);

            END IF;

            
            
        END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-24  1:04:35
