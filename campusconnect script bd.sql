CREATE DATABASE  IF NOT EXISTS `campusconnectbd` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `campusconnectbd`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: campusconnectbd
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `infoadicional`
--

DROP TABLE IF EXISTS `infoadicional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `infoadicional` (
  `info_adicional_id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `tipo` enum('GUSTO','HOBBIE','INTERES') NOT NULL,
  PRIMARY KEY (`info_adicional_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `fecha_like` date NOT NULL,
  `like_id` bigint NOT NULL AUTO_INCREMENT,
  `perfil_destino_id` bigint NOT NULL,
  `perfil_origen_id` bigint NOT NULL,
  `estado_like` enum('ACEPTADO','PENDIENTE','RECHAZADO') NOT NULL,
  PRIMARY KEY (`like_id`),
  KEY `FKn0x6chbblg2sivbt24pyv5ku5` (`perfil_destino_id`),
  KEY `FKcqtejuyf3xb2ymauw0ew1qyfy` (`perfil_origen_id`),
  CONSTRAINT `FKcqtejuyf3xb2ymauw0ew1qyfy` FOREIGN KEY (`perfil_origen_id`) REFERENCES `perfil` (`perfil_id`),
  CONSTRAINT `FKn0x6chbblg2sivbt24pyv5ku5` FOREIGN KEY (`perfil_destino_id`) REFERENCES `perfil` (`perfil_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matches` (
  `fecha_match` date NOT NULL,
  `like_a` bigint NOT NULL,
  `like_b` bigint NOT NULL,
  `match_id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`match_id`),
  UNIQUE KEY `UKr2qya0f4ykldi84oni242jk8c` (`like_a`),
  UNIQUE KEY `UK3fm30q2b2t5umvp64vmvqphwh` (`like_b`),
  CONSTRAINT `FK22wmqp935b95ewod9hbbtelkl` FOREIGN KEY (`like_b`) REFERENCES `likes` (`like_id`),
  CONSTRAINT `FKnl71vmpkfbjmncm0ja0s6891o` FOREIGN KEY (`like_a`) REFERENCES `likes` (`like_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mensaje`
--

DROP TABLE IF EXISTS `mensaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensaje` (
  `fecha_hora_envio` datetime(6) NOT NULL,
  `match_id` bigint NOT NULL,
  `mensaje_id` bigint NOT NULL AUTO_INCREMENT,
  `perfil_emisor_id` bigint NOT NULL,
  PRIMARY KEY (`mensaje_id`),
  KEY `FKm796oui24jg3av0n7mf4mxkwx` (`match_id`),
  KEY `FKfhhlr08ebpnkx9nam9qkcw2p9` (`perfil_emisor_id`),
  CONSTRAINT `FKfhhlr08ebpnkx9nam9qkcw2p9` FOREIGN KEY (`perfil_emisor_id`) REFERENCES `perfil` (`perfil_id`),
  CONSTRAINT `FKm796oui24jg3av0n7mf4mxkwx` FOREIGN KEY (`match_id`) REFERENCES `matches` (`match_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil` (
  `fecha_nacimiento` date NOT NULL,
  `perfil_id` bigint NOT NULL AUTO_INCREMENT,
  `contrasena` varchar(255) NOT NULL,
  `correo_institucional` varchar(255) NOT NULL,
  `foto_perfil` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `carrera` enum('ADMINISTRACION','ADMINISTRACION_EMPRESAS_TURISTICAS','ARQUITECTURA','CIENCIAS_EDUCACION','CIENCIAS_EJERCICIO_FISICO','CONTADURIA_PUBLICA','DIRECCION_CULTURA_FISICA_DEPORTE','DISENO_GRAFICO','ECONOMIA_FINANZAS','EDUCACION_INFANTIL','EDUCACION_INICIAL_GESTION','EMPRENDIMIENTO_INNOVACION','GASTRONOMIA','GESTION_DESARROLLO_ARTES','INGENIERIA_BIOSISTEMAS','INGENIERIA_BIOTECNOLOGIA','INGENIERIA_CIENCIAS_AMBIENTALES','INGENIERIA_CIVIL','INGENIERIA_ELECTROMECANICA','INGENIERIA_ELECTRONICA','INGENIERIA_INDUSTRIAL_SISTEMAS','INGENIERIA_MANUFACTURA','INGENIERIA_MECATRONICA','INGENIERIA_QUIMICA','INGENIERIA_SOFTWARE','MEDICO_VETERINARIO_ZOOTECNISTA','MERCADOTECNIA','PSICOLOGIA','TECNOLOGIA_ALIMENTOS') NOT NULL,
  PRIMARY KEY (`perfil_id`),
  UNIQUE KEY `UKka8ckmt978rspdgq9vatpmyf` (`correo_institucional`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `perfil_info_adicional`
--

DROP TABLE IF EXISTS `perfil_info_adicional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil_info_adicional` (
  `info_adicional_id` bigint NOT NULL,
  `perfil_id` bigint NOT NULL,
  KEY `FKq9i45eewe8ove0h90i61l3uab` (`info_adicional_id`),
  KEY `FKibce9hltmm6k0as7sd7dunriy` (`perfil_id`),
  CONSTRAINT `FKibce9hltmm6k0as7sd7dunriy` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`perfil_id`),
  CONSTRAINT `FKq9i45eewe8ove0h90i61l3uab` FOREIGN KEY (`info_adicional_id`) REFERENCES `infoadicional` (`info_adicional_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-24 13:00:34
