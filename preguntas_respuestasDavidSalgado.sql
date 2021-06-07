-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: preguntas_respuestas
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `preguntas`
--

DROP TABLE IF EXISTS `preguntas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preguntas` (
  `id_preg` int NOT NULL,
  `pregunta` varchar(255) DEFAULT NULL,
  `cod_resp` int NOT NULL,
  PRIMARY KEY (`id_preg`),
  KEY `cod_resp` (`cod_resp`),
  CONSTRAINT `preguntas_ibfk_1` FOREIGN KEY (`cod_resp`) REFERENCES `respuestas` (`id_resp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preguntas`
--

LOCK TABLES `preguntas` WRITE;
/*!40000 ALTER TABLE `preguntas` DISABLE KEYS */;
INSERT INTO `preguntas` VALUES (1,'¿Quien pinto la capilla Sixtina?',1),(2,'¿Quién realizo la cupula de Santa Maria del Fiore?',2),(3,'¿Quien pinto “El nacimiento de Venus”?',3),(4,'¿Quien esculpio la “Puerta del Paraiso” del baptisterio de Florencia?',4),(5,'¿Quien esculpio el “David” de bronce?',5),(6,'¿Quien escribio la “Divina comedia”?',6),(7,'¿Quien pinto la “Virgen de las rocas”?',7),(8,'¿Quien pinto “La escuela de Atenas”?',8),(9,'¿Quien realizo la fachada de Santa María Novella en Florencia?',9),(10,'¿Quien realizo el Palacio Medici?',10);
/*!40000 ALTER TABLE `preguntas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respuestas`
--

DROP TABLE IF EXISTS `respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `respuestas` (
  `id_resp` int NOT NULL,
  `respuesta` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_resp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuestas`
--

LOCK TABLES `respuestas` WRITE;
/*!40000 ALTER TABLE `respuestas` DISABLE KEYS */;
INSERT INTO `respuestas` VALUES (1,'Miguel Angel'),(2,'Brunelleschi'),(3,'Botticelli'),(4,'Ghiberti'),(5,'Donatello'),(6,'Dante Alighieri'),(7,'Leonardo Da Vinci'),(8,'Rafael Sanzio'),(9,'Alberti'),(10,'Michelozzo'),(11,'Otra Tortuga Ninja'),(12,'Bernini'),(13,'Borromini'),(14,'El Greco'),(15,'Velazquez');
/*!40000 ALTER TABLE `respuestas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-26 20:20:35
