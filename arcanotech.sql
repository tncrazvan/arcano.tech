-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versione server:              10.3.16-MariaDB - mariadb.org binary distribution
-- S.O. server:                  Win64
-- HeidiSQL Versione:            10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dump della struttura del database arcanotech
CREATE DATABASE IF NOT EXISTS `arcanotech` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `arcanotech`;

-- Dump della struttura di tabella arcanotech.method
CREATE TABLE IF NOT EXISTS `method` (
  `namespace_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `structure_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `method_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `method_type` varchar(255) COLLATE utf8_bin NOT NULL,
  `returns_array` tinyint(1) unsigned NOT NULL,
  PRIMARY KEY (`namespace_id`,`structure_name`,`method_id`),
  CONSTRAINT `FK_method_structure` FOREIGN KEY (`namespace_id`, `structure_name`) REFERENCES `structure` (`namespace_id`, `structure_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dump dei dati della tabella arcanotech.method: ~0 rows (circa)
/*!40000 ALTER TABLE `method` DISABLE KEYS */;
/*!40000 ALTER TABLE `method` ENABLE KEYS */;

-- Dump della struttura di tabella arcanotech.namespace
CREATE TABLE IF NOT EXISTS `namespace` (
  `namespace_id` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`namespace_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dump dei dati della tabella arcanotech.namespace: ~0 rows (circa)
/*!40000 ALTER TABLE `namespace` DISABLE KEYS */;
INSERT INTO `namespace` (`namespace_id`) VALUES
	('com.github.tncrazvan.arcano');
/*!40000 ALTER TABLE `namespace` ENABLE KEYS */;

-- Dump della struttura di tabella arcanotech.parameter
CREATE TABLE IF NOT EXISTS `parameter` (
  `namespace_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `structure_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `method_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `parameter_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `parameter_type` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`namespace_id`,`structure_name`,`method_id`,`parameter_id`),
  CONSTRAINT `FK_parameter_method` FOREIGN KEY (`namespace_id`, `structure_name`, `method_id`) REFERENCES `method` (`namespace_id`, `structure_name`, `method_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dump dei dati della tabella arcanotech.parameter: ~0 rows (circa)
/*!40000 ALTER TABLE `parameter` DISABLE KEYS */;
/*!40000 ALTER TABLE `parameter` ENABLE KEYS */;

-- Dump della struttura di tabella arcanotech.property
CREATE TABLE IF NOT EXISTS `property` (
  `namespace_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `structure_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `property_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `value` varchar(255) COLLATE utf8_bin NOT NULL,
  `visibility_id` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `property_type` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `is_final` tinyint(1) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`namespace_id`,`structure_name`,`property_id`),
  KEY `FK_property_visibility` (`visibility_id`),
  CONSTRAINT `FK_property_structure` FOREIGN KEY (`namespace_id`, `structure_name`) REFERENCES `structure` (`namespace_id`, `structure_name`),
  CONSTRAINT `FK_property_visibility` FOREIGN KEY (`visibility_id`) REFERENCES `visibility` (`visibility_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dump dei dati della tabella arcanotech.property: ~0 rows (circa)
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
/*!40000 ALTER TABLE `property` ENABLE KEYS */;

-- Dump della struttura di tabella arcanotech.structure
CREATE TABLE IF NOT EXISTS `structure` (
  `namespace_id` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `structure_name` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `visibility_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `is_abstract` tinyint(1) unsigned NOT NULL DEFAULT 0,
  `structure_type_id` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`namespace_id`,`structure_name`),
  KEY `FK_structure_structure_type` (`structure_type_id`),
  KEY `FK_structure_visibility` (`visibility_id`),
  CONSTRAINT `FK_structure_namespace` FOREIGN KEY (`namespace_id`) REFERENCES `namespace` (`namespace_id`),
  CONSTRAINT `FK_structure_structure_type` FOREIGN KEY (`structure_type_id`) REFERENCES `structure_type` (`structure_type_id`),
  CONSTRAINT `FK_structure_visibility` FOREIGN KEY (`visibility_id`) REFERENCES `visibility` (`visibility_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dump dei dati della tabella arcanotech.structure: ~0 rows (circa)
/*!40000 ALTER TABLE `structure` DISABLE KEYS */;
/*!40000 ALTER TABLE `structure` ENABLE KEYS */;

-- Dump della struttura di tabella arcanotech.structure_type
CREATE TABLE IF NOT EXISTS `structure_type` (
  `structure_type_id` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`structure_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dump dei dati della tabella arcanotech.structure_type: ~2 rows (circa)
/*!40000 ALTER TABLE `structure_type` DISABLE KEYS */;
INSERT INTO `structure_type` (`structure_type_id`) VALUES
	('class'),
	('interface');
/*!40000 ALTER TABLE `structure_type` ENABLE KEYS */;

-- Dump della struttura di tabella arcanotech.visibility
CREATE TABLE IF NOT EXISTS `visibility` (
  `visibility_id` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`visibility_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dump dei dati della tabella arcanotech.visibility: ~4 rows (circa)
/*!40000 ALTER TABLE `visibility` DISABLE KEYS */;
INSERT INTO `visibility` (`visibility_id`) VALUES
	(''),
	('private'),
	('protected'),
	('public');
/*!40000 ALTER TABLE `visibility` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
