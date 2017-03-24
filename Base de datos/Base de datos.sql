-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.7.15-log - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para bd_aerolinea_amr
DROP DATABASE IF EXISTS `bd_aerolinea_amr`;
CREATE DATABASE IF NOT EXISTS `bd_aerolinea_amr` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bd_aerolinea_amr`;

-- Volcando estructura para tabla bd_aerolinea_amr.aeropuerto
DROP TABLE IF EXISTS `aeropuerto`;
CREATE TABLE IF NOT EXISTS `aeropuerto` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IATA` char(3) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Localidad` varchar(15) NOT NULL,
  `Capacidad` int(10) NOT NULL,
  UNIQUE KEY `IATA` (`IATA`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.aeropuerto: ~4 rows (aproximadamente)
DELETE FROM `aeropuerto`;
/*!40000 ALTER TABLE `aeropuerto` DISABLE KEYS */;
INSERT INTO `aeropuerto` (`ID`, `IATA`, `Nombre`, `Localidad`, `Capacidad`) VALUES
	(1, 'ALC', 'Aeropuerto de Alicante-Elche', 'Alicante', 3000000),
	(2, 'BVA', 'Aeropuerto de Beauvais-Tillé', 'París', 3500000),
	(3, 'FCO', 'Aeropuerto Internacional Leonardo da Vinci', 'Roma', 4500000),
	(4, 'LCY', 'Aeropuerto de la Ciudad de Londres', 'Londres', 5000000),
	(5, 'RAK', 'Aeropuerto de Marrakech-Menara', 'Marrakech', 1500000);
/*!40000 ALTER TABLE `aeropuerto` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.avion
DROP TABLE IF EXISTS `avion`;
CREATE TABLE IF NOT EXISTS `avion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COD_Avion` varchar(50) NOT NULL,
  `Tipo` varchar(50) NOT NULL,
  `Plazas` int(11) NOT NULL,
  `Filas` int(11) NOT NULL,
  `Columnas` int(11) NOT NULL,
  UNIQUE KEY `COD_Avion` (`COD_Avion`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.avion: ~2 rows (aproximadamente)
DELETE FROM `avion`;
/*!40000 ALTER TABLE `avion` DISABLE KEYS */;
INSERT INTO `avion` (`ID`, `COD_Avion`, `Tipo`, `Plazas`, `Filas`, `Columnas`) VALUES
	(1, 'AV01', 'Boeing 737', 8, 2, 4),
	(2, 'AV02', 'Boeing 747', 8, 2, 4);
/*!40000 ALTER TABLE `avion` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.backupaeropuerto
DROP TABLE IF EXISTS `backupaeropuerto`;
CREATE TABLE IF NOT EXISTS `backupaeropuerto` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IATA` char(3) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Localidad` varchar(15) NOT NULL,
  `Capacidad` int(10) NOT NULL,
  UNIQUE KEY `IATA` (`IATA`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.backupaeropuerto: ~5 rows (aproximadamente)
DELETE FROM `backupaeropuerto`;
/*!40000 ALTER TABLE `backupaeropuerto` DISABLE KEYS */;
INSERT INTO `backupaeropuerto` (`ID`, `IATA`, `Nombre`, `Localidad`, `Capacidad`) VALUES
	(1, 'ALC', 'Aeropuerto de Alicante-Elche', 'Alicante', 3000000),
	(2, 'BVA', 'Aeropuerto de Beauvais-Tillé', 'París', 3500000),
	(3, 'FCO', 'Aeropuerto Internacional Leonardo da Vinci', 'Roma', 4500000),
	(4, 'LCY', 'Aeropuerto de la Ciudad de Londres', 'Londres', 5000000),
	(5, 'RAK', 'Aeropuerto de Marrakech-Menara', 'Marrakech', 1500000);
/*!40000 ALTER TABLE `backupaeropuerto` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.backupavion
DROP TABLE IF EXISTS `backupavion`;
CREATE TABLE IF NOT EXISTS `backupavion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COD_Avion` varchar(50) NOT NULL,
  `Tipo` varchar(50) NOT NULL,
  `Plazas` int(11) NOT NULL,
  `Filas` int(11) NOT NULL,
  `Columnas` int(11) NOT NULL,
  UNIQUE KEY `COD_Avion` (`COD_Avion`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.backupavion: ~2 rows (aproximadamente)
DELETE FROM `backupavion`;
/*!40000 ALTER TABLE `backupavion` DISABLE KEYS */;
INSERT INTO `backupavion` (`ID`, `COD_Avion`, `Tipo`, `Plazas`, `Filas`, `Columnas`) VALUES
	(1, 'AV01', 'Boeing 737', 8, 2, 4),
	(2, 'AV02', 'Boeing 747', 8, 2, 4);
/*!40000 ALTER TABLE `backupavion` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.backupconexion
DROP TABLE IF EXISTS `backupconexion`;
CREATE TABLE IF NOT EXISTS `backupconexion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Origen` int(11) NOT NULL,
  `ID_Destino` int(11) NOT NULL,
  UNIQUE KEY `ID_Origen_ID_Destino` (`ID_Origen`,`ID_Destino`),
  KEY `ID` (`ID`),
  KEY `FK_backupconexion_backupaeropuerto_2` (`ID_Destino`),
  CONSTRAINT `FK_backupconexion_backupaeropuerto` FOREIGN KEY (`ID_Origen`) REFERENCES `backupaeropuerto` (`ID`),
  CONSTRAINT `FK_backupconexion_backupaeropuerto_2` FOREIGN KEY (`ID_Destino`) REFERENCES `backupaeropuerto` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.backupconexion: ~4 rows (aproximadamente)
DELETE FROM `backupconexion`;
/*!40000 ALTER TABLE `backupconexion` DISABLE KEYS */;
INSERT INTO `backupconexion` (`ID`, `ID_Origen`, `ID_Destino`) VALUES
	(1, 1, 2),
	(2, 2, 1),
	(7, 1, 3),
	(8, 3, 1);
/*!40000 ALTER TABLE `backupconexion` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.backupocupacion
DROP TABLE IF EXISTS `backupocupacion`;
CREATE TABLE IF NOT EXISTS `backupocupacion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IDPasajero` int(11) DEFAULT NULL,
  `IDVuelo` int(11) NOT NULL,
  `Fila` int(11) NOT NULL,
  `Columna` char(2) NOT NULL,
  UNIQUE KEY `IDPasajero_IDVuelo_Fila_Coumna` (`IDPasajero`,`IDVuelo`,`Fila`,`Columna`),
  KEY `ID` (`ID`),
  KEY `FK_ocupacion_vuelo` (`IDVuelo`),
  CONSTRAINT `FK_backupocupacion_backuppasajero` FOREIGN KEY (`IDPasajero`) REFERENCES `backuppasajero` (`ID`),
  CONSTRAINT `FK_backupocupacion_backupvuelo` FOREIGN KEY (`IDVuelo`) REFERENCES `backupvuelo` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.backupocupacion: ~0 rows (aproximadamente)
DELETE FROM `backupocupacion`;
/*!40000 ALTER TABLE `backupocupacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `backupocupacion` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.backuppasajero
DROP TABLE IF EXISTS `backuppasajero`;
CREATE TABLE IF NOT EXISTS `backuppasajero` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Tratamiento` varchar(5) DEFAULT NULL,
  `Nombre` varchar(25) NOT NULL,
  `Apellido` varchar(25) NOT NULL,
  `TipoIden` set('DNI','PASAPORTE') NOT NULL,
  `Identificador` varchar(25) NOT NULL,
  `FechaCad` date NOT NULL,
  `Fecha_Nac` date NOT NULL,
  `Nacionalidad` varchar(50) NOT NULL,
  `Tutor` set('si') DEFAULT NULL,
  `ID_Tutor` int(11) DEFAULT NULL,
  `ID_Reserva` int(11) NOT NULL,
  UNIQUE KEY `Identificador_ID_Reserva` (`Identificador`,`ID_Reserva`),
  KEY `ID` (`ID`),
  KEY `FK_backuppasajero_backuppasajero` (`ID_Tutor`),
  KEY `FK_backuppasajero_backupreserva` (`ID_Reserva`),
  CONSTRAINT `FK_backuppasajero_backuppasajero` FOREIGN KEY (`ID_Tutor`) REFERENCES `backuppasajero` (`ID`),
  CONSTRAINT `FK_backuppasajero_backupreserva` FOREIGN KEY (`ID_Reserva`) REFERENCES `backupreserva` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.backuppasajero: ~0 rows (aproximadamente)
DELETE FROM `backuppasajero`;
/*!40000 ALTER TABLE `backuppasajero` DISABLE KEYS */;
/*!40000 ALTER TABLE `backuppasajero` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.backuppasajeroservicio
DROP TABLE IF EXISTS `backuppasajeroservicio`;
CREATE TABLE IF NOT EXISTS `backuppasajeroservicio` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Pasajero` int(11) NOT NULL,
  `ID_Servicio` int(11) NOT NULL,
  `ID_Vuelo` int(11) NOT NULL,
  UNIQUE KEY `ID_Pasajero_ID_Servicio_ID_Vuelo` (`ID_Pasajero`,`ID_Servicio`,`ID_Vuelo`),
  KEY `ID` (`ID`),
  KEY `FK_backuppasajeroservicio_backupservicio` (`ID_Servicio`),
  KEY `FK_backuppasajeroservicio_backupvuelo` (`ID_Vuelo`),
  CONSTRAINT `FK_backuppasajeroservicio_backuppasajero` FOREIGN KEY (`ID_Pasajero`) REFERENCES `backuppasajero` (`ID`),
  CONSTRAINT `FK_backuppasajeroservicio_backupservicio` FOREIGN KEY (`ID_Servicio`) REFERENCES `backupservicio` (`ID`),
  CONSTRAINT `FK_backuppasajeroservicio_backupvuelo` FOREIGN KEY (`ID_Vuelo`) REFERENCES `backupvuelo` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.backuppasajeroservicio: ~0 rows (aproximadamente)
DELETE FROM `backuppasajeroservicio`;
/*!40000 ALTER TABLE `backuppasajeroservicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `backuppasajeroservicio` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.backupreserva
DROP TABLE IF EXISTS `backupreserva`;
CREATE TABLE IF NOT EXISTS `backupreserva` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COD_Reserva` varchar(25) NOT NULL,
  `ID_Cliente` int(11) NOT NULL,
  `ID_Vuelo_Ida` int(11) DEFAULT NULL,
  `ID_Vuelo_Vuelta` int(11) DEFAULT NULL,
  `Fecha` date NOT NULL,
  `ID_Tarjeta` int(11) NOT NULL,
  `Coste` double NOT NULL,
  `FacturarI` set('si') DEFAULT NULL,
  `FacturarV` set('si') DEFAULT NULL,
  UNIQUE KEY `COD_Reserva` (`COD_Reserva`),
  KEY `ID` (`ID`),
  KEY `FK_backupreserva_cliente` (`ID_Cliente`),
  KEY `FK_backupreserva_backupvuelo` (`ID_Vuelo_Ida`),
  KEY `FK_backupreserva_backupvuelo_2` (`ID_Vuelo_Vuelta`),
  KEY `FK_backupreserva_backuptarjeta` (`ID_Tarjeta`),
  CONSTRAINT `FK_backupreserva_backuptarjeta` FOREIGN KEY (`ID_Tarjeta`) REFERENCES `backuptarjeta` (`ID`),
  CONSTRAINT `FK_backupreserva_backupvuelo` FOREIGN KEY (`ID_Vuelo_Ida`) REFERENCES `backupvuelo` (`ID`),
  CONSTRAINT `FK_backupreserva_backupvuelo_2` FOREIGN KEY (`ID_Vuelo_Vuelta`) REFERENCES `backupvuelo` (`ID`),
  CONSTRAINT `FK_backupreserva_cliente` FOREIGN KEY (`ID_Cliente`) REFERENCES `cliente` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.backupreserva: ~0 rows (aproximadamente)
DELETE FROM `backupreserva`;
/*!40000 ALTER TABLE `backupreserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `backupreserva` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.backupservicio
DROP TABLE IF EXISTS `backupservicio`;
CREATE TABLE IF NOT EXISTS `backupservicio` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Servicio` varchar(25) NOT NULL,
  `Precio` varchar(25) NOT NULL,
  UNIQUE KEY `Servicio_Precio` (`Servicio`,`Precio`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.backupservicio: ~0 rows (aproximadamente)
DELETE FROM `backupservicio`;
/*!40000 ALTER TABLE `backupservicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `backupservicio` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.backuptarjeta
DROP TABLE IF EXISTS `backuptarjeta`;
CREATE TABLE IF NOT EXISTS `backuptarjeta` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Cliente` int(11) NOT NULL,
  `Numero` blob NOT NULL,
  `Tipo` varchar(50) NOT NULL,
  `CSV` varchar(3) NOT NULL,
  `Mes_Caducidad` int(2) NOT NULL,
  `Ano_Caducidad` varchar(4) NOT NULL,
  UNIQUE KEY `Numero_Tipo_CSV` (`Tipo`,`CSV`),
  KEY `ID` (`ID`),
  KEY `FK_backuptarjeta_cliente` (`ID_Cliente`),
  CONSTRAINT `FK_backuptarjeta_cliente` FOREIGN KEY (`ID_Cliente`) REFERENCES `cliente` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.backuptarjeta: ~0 rows (aproximadamente)
DELETE FROM `backuptarjeta`;
/*!40000 ALTER TABLE `backuptarjeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `backuptarjeta` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.backupvuelo
DROP TABLE IF EXISTS `backupvuelo`;
CREATE TABLE IF NOT EXISTS `backupvuelo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Conexion` int(11) NOT NULL,
  `COD_Vuelo` varchar(10) NOT NULL,
  `Fecha` date NOT NULL,
  `Hora_Salida` time NOT NULL,
  `Hora_Entrada` time NOT NULL,
  `Precio` double NOT NULL,
  `Duracion` time NOT NULL,
  `Plazas_Libres` int(2) NOT NULL,
  `Avion` int(11) NOT NULL,
  UNIQUE KEY `COD_Vuelo` (`COD_Vuelo`),
  KEY `ID` (`ID`),
  KEY `FK_backupvuelo_backupconexion` (`ID_Conexion`),
  KEY `FK_backupvuelo_backupavion` (`Avion`),
  CONSTRAINT `FK_backupvuelo_backupavion` FOREIGN KEY (`Avion`) REFERENCES `backupavion` (`ID`),
  CONSTRAINT `FK_backupvuelo_backupconexion` FOREIGN KEY (`ID_Conexion`) REFERENCES `backupconexion` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.backupvuelo: ~0 rows (aproximadamente)
DELETE FROM `backupvuelo`;
/*!40000 ALTER TABLE `backupvuelo` DISABLE KEYS */;
/*!40000 ALTER TABLE `backupvuelo` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.cliente
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Tratamiento` varchar(5) NOT NULL,
  `Nombre` varchar(25) NOT NULL,
  `Apellido` varchar(25) NOT NULL,
  `TipoIden` set('DNI','PASAPORTE') NOT NULL,
  `DNI` varchar(25) NOT NULL,
  `Direccion` varchar(150) NOT NULL,
  `Correo` varchar(25) NOT NULL,
  `Usuario` varchar(25) NOT NULL,
  `Contrasena` blob NOT NULL,
  `Telf` varchar(25) NOT NULL,
  UNIQUE KEY `DNI_Correo_Usuario` (`DNI`,`Correo`,`Usuario`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.cliente: ~0 rows (aproximadamente)
DELETE FROM `cliente`;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.conexion
DROP TABLE IF EXISTS `conexion`;
CREATE TABLE IF NOT EXISTS `conexion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Origen` int(11) NOT NULL,
  `ID_Destino` int(11) NOT NULL,
  UNIQUE KEY `ID_Origen_ID_Destino` (`ID_Origen`,`ID_Destino`),
  KEY `ID` (`ID`),
  KEY `FK_conexion_aeropuerto_2` (`ID_Destino`),
  CONSTRAINT `FK_conexion_aeropuerto` FOREIGN KEY (`ID_Origen`) REFERENCES `aeropuerto` (`ID`),
  CONSTRAINT `FK_conexion_aeropuerto_2` FOREIGN KEY (`ID_Destino`) REFERENCES `aeropuerto` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.conexion: ~4 rows (aproximadamente)
DELETE FROM `conexion`;
/*!40000 ALTER TABLE `conexion` DISABLE KEYS */;
INSERT INTO `conexion` (`ID`, `ID_Origen`, `ID_Destino`) VALUES
	(1, 1, 2),
	(2, 2, 1),
	(7, 1, 3),
	(8, 3, 1);
/*!40000 ALTER TABLE `conexion` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.ocupacion
DROP TABLE IF EXISTS `ocupacion`;
CREATE TABLE IF NOT EXISTS `ocupacion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IDPasajero` int(11) DEFAULT NULL,
  `IDVuelo` int(11) NOT NULL,
  `Fila` int(11) NOT NULL,
  `Columna` char(2) NOT NULL,
  UNIQUE KEY `IDPasajero_IDVuelo_Fila_Coumna` (`IDPasajero`,`IDVuelo`,`Fila`,`Columna`),
  KEY `ID` (`ID`),
  KEY `FK_ocupacion_vuelo` (`IDVuelo`),
  CONSTRAINT `FK_ocupacion_vuelo` FOREIGN KEY (`IDVuelo`) REFERENCES `vuelo` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.ocupacion: ~0 rows (aproximadamente)
DELETE FROM `ocupacion`;
/*!40000 ALTER TABLE `ocupacion` DISABLE KEYS */;
INSERT INTO `ocupacion` (`ID`, `IDPasajero`, `IDVuelo`, `Fila`, `Columna`) VALUES
	(11, NULL, 2, 1, 'A'),
	(12, NULL, 2, 1, 'B'),
	(13, NULL, 2, 1, 'C'),
	(14, NULL, 2, 1, 'D'),
	(15, NULL, 2, 2, 'A'),
	(16, NULL, 2, 2, 'B'),
	(17, NULL, 2, 2, 'C'),
	(18, NULL, 2, 2, 'D'),
	(19, NULL, 3, 1, 'A'),
	(20, NULL, 3, 1, 'B'),
	(21, NULL, 3, 1, 'C'),
	(22, NULL, 3, 1, 'D'),
	(23, NULL, 3, 2, 'A'),
	(24, NULL, 3, 2, 'B'),
	(25, NULL, 3, 2, 'C'),
	(26, NULL, 3, 2, 'D'),
	(27, NULL, 4, 1, 'A'),
	(28, NULL, 4, 1, 'B'),
	(29, NULL, 4, 1, 'C'),
	(30, NULL, 4, 1, 'D'),
	(31, NULL, 4, 2, 'A'),
	(32, NULL, 4, 2, 'B'),
	(33, NULL, 4, 2, 'C'),
	(34, NULL, 4, 2, 'D'),
	(35, NULL, 5, 1, 'A'),
	(36, NULL, 5, 1, 'B'),
	(37, NULL, 5, 1, 'C'),
	(38, NULL, 5, 1, 'D'),
	(39, NULL, 5, 2, 'A'),
	(40, NULL, 5, 2, 'B'),
	(42, NULL, 5, 2, 'C'),
	(43, NULL, 5, 2, 'D'),
	(44, NULL, 6, 1, 'A'),
	(45, NULL, 6, 1, 'B'),
	(46, NULL, 6, 1, 'C'),
	(47, NULL, 6, 1, 'D'),
	(48, NULL, 6, 2, 'A'),
	(49, NULL, 6, 2, 'B'),
	(50, NULL, 6, 2, 'C'),
	(51, NULL, 6, 2, 'D'),
	(52, NULL, 7, 1, 'A'),
	(53, NULL, 7, 1, 'B'),
	(54, NULL, 7, 1, 'C'),
	(55, NULL, 7, 1, 'D'),
	(56, NULL, 7, 2, 'A'),
	(57, NULL, 7, 2, 'B'),
	(58, NULL, 7, 2, 'C'),
	(59, NULL, 7, 2, 'D'),
	(60, NULL, 8, 1, 'A'),
	(61, NULL, 8, 1, 'B'),
	(62, NULL, 8, 1, 'C'),
	(63, NULL, 8, 1, 'D'),
	(64, NULL, 8, 2, 'A'),
	(65, NULL, 8, 2, 'B'),
	(66, NULL, 8, 2, 'C'),
	(67, NULL, 8, 2, 'D'),
	(76, NULL, 10, 1, 'A'),
	(77, NULL, 10, 1, 'B'),
	(78, NULL, 10, 1, 'C'),
	(79, NULL, 10, 1, 'D'),
	(80, NULL, 10, 2, 'A'),
	(81, NULL, 10, 2, 'B'),
	(82, NULL, 10, 2, 'C'),
	(83, NULL, 10, 2, 'D'),
	(84, NULL, 11, 1, 'A'),
	(85, NULL, 11, 1, 'B'),
	(86, NULL, 11, 1, 'C'),
	(87, NULL, 11, 1, 'D'),
	(88, NULL, 11, 2, 'A'),
	(89, NULL, 11, 2, 'B'),
	(90, NULL, 11, 2, 'C'),
	(91, NULL, 11, 2, 'D'),
	(92, NULL, 12, 1, 'A'),
	(93, NULL, 12, 1, 'B'),
	(94, NULL, 12, 1, 'C'),
	(95, NULL, 12, 1, 'D'),
	(96, NULL, 12, 2, 'A'),
	(97, NULL, 12, 2, 'B'),
	(98, NULL, 12, 2, 'C'),
	(99, NULL, 12, 2, 'D'),
	(100, NULL, 13, 1, 'A'),
	(101, NULL, 13, 1, 'B'),
	(102, NULL, 13, 1, 'C'),
	(103, NULL, 13, 1, 'D'),
	(104, NULL, 13, 2, 'A'),
	(105, NULL, 13, 2, 'B'),
	(106, NULL, 13, 2, 'C'),
	(107, NULL, 13, 2, 'D'),
	(108, NULL, 14, 1, 'A'),
	(109, NULL, 14, 1, 'B'),
	(110, NULL, 14, 1, 'C'),
	(111, NULL, 14, 1, 'D'),
	(112, NULL, 14, 2, 'A'),
	(113, NULL, 14, 2, 'C'),
	(114, NULL, 14, 2, 'B'),
	(115, NULL, 14, 2, 'D'),
	(116, NULL, 15, 1, 'A'),
	(117, NULL, 15, 1, 'C'),
	(118, NULL, 15, 1, 'B'),
	(119, NULL, 15, 1, 'D'),
	(120, NULL, 15, 2, 'B'),
	(121, NULL, 15, 2, 'A'),
	(122, NULL, 15, 2, 'C'),
	(123, NULL, 15, 2, 'D'),
	(124, NULL, 16, 1, 'A'),
	(125, NULL, 16, 1, 'B'),
	(126, NULL, 16, 1, 'C'),
	(127, NULL, 16, 1, 'D'),
	(128, NULL, 16, 2, 'A'),
	(129, NULL, 16, 2, 'B'),
	(130, NULL, 16, 2, 'C'),
	(131, NULL, 16, 2, 'D'),
	(1, NULL, 1, 1, 'A'),
	(2, NULL, 1, 1, 'B'),
	(3, NULL, 1, 1, 'C'),
	(4, NULL, 1, 1, 'D'),
	(5, NULL, 1, 2, 'A'),
	(6, NULL, 1, 2, 'B'),
	(7, NULL, 1, 2, 'C'),
	(8, NULL, 1, 2, 'D'),
	(68, NULL, 9, 1, 'A'),
	(69, NULL, 9, 1, 'B'),
	(70, NULL, 9, 1, 'C'),
	(71, NULL, 9, 1, 'D'),
	(72, NULL, 9, 2, 'A'),
	(73, NULL, 9, 2, 'B'),
	(74, NULL, 9, 2, 'C'),
	(75, NULL, 9, 2, 'D');
/*!40000 ALTER TABLE `ocupacion` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.pasajero
DROP TABLE IF EXISTS `pasajero`;
CREATE TABLE IF NOT EXISTS `pasajero` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Tratamiento` varchar(5) DEFAULT NULL,
  `Nombre` varchar(25) NOT NULL,
  `Apellido` varchar(25) NOT NULL,
  `TipoIden` set('DNI','PASAPORTE') NOT NULL,
  `Identificador` varchar(25) NOT NULL,
  `FechaCad` date NOT NULL,
  `Fecha_Nac` date NOT NULL,
  `Nacionalidad` varchar(50) NOT NULL,
  `Tutor` set('si') DEFAULT NULL,
  `ID_Tutor` int(11) DEFAULT NULL,
  `ID_Reserva` int(11) NOT NULL,
  UNIQUE KEY `Identificador_ID_Reserva` (`Identificador`,`ID_Reserva`),
  KEY `ID` (`ID`),
  KEY `FK_pasajero_pasajero` (`ID_Tutor`),
  KEY `FK_pasajero_reserva` (`ID_Reserva`),
  CONSTRAINT `FK_pasajero_pasajero` FOREIGN KEY (`ID_Tutor`) REFERENCES `pasajero` (`ID`),
  CONSTRAINT `FK_pasajero_reserva` FOREIGN KEY (`ID_Reserva`) REFERENCES `reserva` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.pasajero: ~0 rows (aproximadamente)
DELETE FROM `pasajero`;
/*!40000 ALTER TABLE `pasajero` DISABLE KEYS */;
/*!40000 ALTER TABLE `pasajero` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.pasajeroservicio
DROP TABLE IF EXISTS `pasajeroservicio`;
CREATE TABLE IF NOT EXISTS `pasajeroservicio` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Pasajero` int(11) NOT NULL,
  `ID_Servicio` int(11) NOT NULL,
  `ID_Vuelo` int(11) NOT NULL,
  UNIQUE KEY `ID_Pasajero_ID_Servicio_ID_Vuelo` (`ID_Pasajero`,`ID_Servicio`,`ID_Vuelo`),
  KEY `ID` (`ID`),
  KEY `FK_PasajeroServicio_servicio` (`ID_Servicio`),
  KEY `FK_pasajeroservicio_vuelo` (`ID_Vuelo`),
  CONSTRAINT `FK_PasajeroServicio_pasajero` FOREIGN KEY (`ID_Pasajero`) REFERENCES `pasajero` (`ID`),
  CONSTRAINT `FK_PasajeroServicio_servicio` FOREIGN KEY (`ID_Servicio`) REFERENCES `servicio` (`ID`),
  CONSTRAINT `FK_pasajeroservicio_vuelo` FOREIGN KEY (`ID_Vuelo`) REFERENCES `vuelo` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.pasajeroservicio: ~0 rows (aproximadamente)
DELETE FROM `pasajeroservicio`;
/*!40000 ALTER TABLE `pasajeroservicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `pasajeroservicio` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.reserva
DROP TABLE IF EXISTS `reserva`;
CREATE TABLE IF NOT EXISTS `reserva` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COD_Reserva` varchar(25) NOT NULL,
  `ID_Cliente` int(11) NOT NULL,
  `ID_Vuelo_Ida` int(11) DEFAULT NULL,
  `ID_Vuelo_Vuelta` int(11) DEFAULT NULL,
  `Fecha` date NOT NULL,
  `ID_Tarjeta` int(11) NOT NULL,
  `Coste` double NOT NULL,
  `FacturarI` set('si') DEFAULT NULL,
  `FacturarV` set('si') DEFAULT NULL,
  UNIQUE KEY `COD_Reserva` (`COD_Reserva`),
  KEY `ID` (`ID`),
  KEY `FK_reserva_cliente` (`ID_Cliente`),
  KEY `FK_reserva_tarjeta` (`ID_Tarjeta`),
  KEY `FK_reserva_vuelo` (`ID_Vuelo_Ida`),
  KEY `FK_reserva_vuelo_2` (`ID_Vuelo_Vuelta`),
  CONSTRAINT `FK_reserva_cliente` FOREIGN KEY (`ID_Cliente`) REFERENCES `cliente` (`ID`),
  CONSTRAINT `FK_reserva_tarjeta` FOREIGN KEY (`ID_Tarjeta`) REFERENCES `tarjeta` (`ID`),
  CONSTRAINT `FK_reserva_vuelo` FOREIGN KEY (`ID_Vuelo_Ida`) REFERENCES `vuelo` (`ID`),
  CONSTRAINT `FK_reserva_vuelo_2` FOREIGN KEY (`ID_Vuelo_Vuelta`) REFERENCES `vuelo` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.reserva: ~0 rows (aproximadamente)
DELETE FROM `reserva`;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.servicio
DROP TABLE IF EXISTS `servicio`;
CREATE TABLE IF NOT EXISTS `servicio` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Servicio` varchar(25) NOT NULL,
  `Precio` varchar(25) NOT NULL,
  UNIQUE KEY `Servicio_Precio` (`Servicio`,`Precio`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.servicio: ~0 rows (aproximadamente)
DELETE FROM `servicio`;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
INSERT INTO `servicio` (`ID`, `Servicio`, `Precio`) VALUES
	(1, 'Bebe', '24.99'),
	(2, 'Maleta 10kg', '14.99'),
	(3, 'Maleta 20kg', '24.99'),
	(4, 'Reservar asiento', '9.99'),
	(5, 'Prioridad de embarque', '9.99'),
	(6, 'Seguro de viaje', '29.99');
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.tarjeta
DROP TABLE IF EXISTS `tarjeta`;
CREATE TABLE IF NOT EXISTS `tarjeta` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Cliente` int(11) NOT NULL,
  `Numero` blob NOT NULL,
  `Tipo` varchar(50) NOT NULL,
  `CSV` varchar(3) NOT NULL,
  `Mes_Caducidad` int(2) NOT NULL,
  `Ano_Caducidad` varchar(4) NOT NULL,
  UNIQUE KEY `Tipo_CSV_Mes_Caducidad_Ano_Caducidad` (`Tipo`,`CSV`,`Mes_Caducidad`,`Ano_Caducidad`),
  KEY `ID` (`ID`),
  KEY `FK_tarjeta_cliente` (`ID_Cliente`),
  CONSTRAINT `FK_tarjeta_cliente` FOREIGN KEY (`ID_Cliente`) REFERENCES `cliente` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.tarjeta: ~0 rows (aproximadamente)
DELETE FROM `tarjeta`;
/*!40000 ALTER TABLE `tarjeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarjeta` ENABLE KEYS */;

-- Volcando estructura para tabla bd_aerolinea_amr.vuelo
DROP TABLE IF EXISTS `vuelo`;
CREATE TABLE IF NOT EXISTS `vuelo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Conexion` int(11) NOT NULL,
  `COD_Vuelo` varchar(10) NOT NULL,
  `Fecha` date NOT NULL,
  `Hora_Salida` time NOT NULL,
  `Hora_Entrada` time NOT NULL,
  `Precio` double NOT NULL,
  `Duracion` time NOT NULL,
  `Plazas_Libres` int(2) NOT NULL,
  `Avion` int(11) NOT NULL,
  UNIQUE KEY `COD_Vuelo` (`COD_Vuelo`),
  KEY `ID` (`ID`),
  KEY `FK_vuelo_conexion` (`ID_Conexion`),
  KEY `FK_vuelo_avion` (`Avion`),
  CONSTRAINT `FK_vuelo_avion` FOREIGN KEY (`Avion`) REFERENCES `avion` (`ID`),
  CONSTRAINT `FK_vuelo_conexion` FOREIGN KEY (`ID_Conexion`) REFERENCES `conexion` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bd_aerolinea_amr.vuelo: ~0 rows (aproximadamente)
DELETE FROM `vuelo`;
/*!40000 ALTER TABLE `vuelo` DISABLE KEYS */;
INSERT INTO `vuelo` (`ID`, `ID_Conexion`, `COD_Vuelo`, `Fecha`, `Hora_Salida`, `Hora_Entrada`, `Precio`, `Duracion`, `Plazas_Libres`, `Avion`) VALUES
	(9, 2, 'PAL2000', '2017-04-08', '01:00:00', '02:30:00', 39.99, '01:30:00', 8, 1),
	(1, 1, 'PAL2001', '2017-04-08', '01:00:00', '02:30:00', 39.99, '01:30:00', 8, 1),
	(10, 2, 'PAL2010', '2017-04-08', '18:00:00', '19:30:00', 39.99, '01:30:00', 8, 1),
	(2, 1, 'PAL2011', '2017-04-08', '18:00:00', '19:30:00', 39.99, '01:30:00', 8, 1),
	(11, 2, 'PAL2020', '2017-04-12', '01:00:00', '02:30:00', 44.99, '01:30:00', 8, 1),
	(3, 1, 'PAL2021', '2017-04-12', '01:00:00', '02:30:00', 44.99, '01:30:00', 8, 1),
	(12, 2, 'PAL2030', '2017-04-12', '18:00:00', '19:30:00', 44.99, '01:30:00', 8, 1),
	(4, 1, 'PAL2031', '2017-04-12', '18:00:00', '19:30:00', 44.99, '01:30:00', 8, 1),
	(13, 2, 'PAL2040', '2017-04-15', '01:00:00', '02:30:00', 59.99, '01:30:00', 8, 2),
	(5, 1, 'PAL2041', '2017-04-15', '01:00:00', '02:30:00', 59.99, '01:30:00', 8, 2),
	(14, 2, 'PAL2050', '2017-04-15', '18:00:00', '19:30:00', 69.99, '01:30:00', 8, 2),
	(6, 1, 'PAL2051', '2017-04-15', '18:00:00', '19:30:00', 69.99, '01:30:00', 8, 2),
	(15, 2, 'PAL2060', '2017-04-19', '01:00:00', '02:30:00', 39.99, '01:30:00', 8, 1),
	(7, 1, 'PAL2061', '2017-04-19', '01:00:00', '02:30:00', 39.99, '01:30:00', 8, 1),
	(16, 2, 'PAL2070', '2017-04-19', '18:00:00', '19:30:00', 39.99, '01:30:00', 8, 1),
	(8, 1, 'PAL2071', '2017-04-19', '18:00:00', '19:30:00', 44.99, '01:30:00', 8, 1),
	(17, 7, 'PAL2101', '2017-04-08', '01:00:00', '02:45:00', 44.99, '01:45:00', 8, 1),
	(18, 7, 'PAL2111', '2017-04-08', '18:00:00', '19:45:00', 44.99, '01:45:00', 8, 1),
	(19, 7, 'PAL2121', '2017-04-12', '01:00:00', '02:45:00', 54.99, '01:45:00', 8, 2),
	(20, 7, 'PAL2131', '2017-04-12', '18:00:00', '19:45:00', 54.99, '01:45:00', 8, 2),
	(21, 7, 'PAL2141', '2017-04-15', '01:00:00', '02:45:00', 69.99, '01:45:00', 8, 2),
	(22, 7, 'PAL2151', '2017-04-15', '19:00:00', '20:45:00', 74.99, '01:45:00', 8, 2),
	(23, 7, 'PAL2161', '2017-04-19', '01:00:00', '02:45:00', 44.99, '01:45:00', 8, 1),
	(24, 7, 'PAL2171', '2017-04-19', '18:00:00', '19:45:00', 44.99, '01:45:00', 8, 1),
	(25, 8, 'PAL2200', '2017-04-08', '01:00:00', '02:45:00', 44.99, '01:45:00', 8, 1),
	(26, 8, 'PAL2210', '2017-04-08', '18:00:00', '19:45:00', 44.99, '01:45:00', 8, 1),
	(27, 8, 'PAL2220', '2017-04-12', '01:00:00', '02:45:00', 54.99, '01:45:00', 8, 2),
	(28, 8, 'PAL2230', '2017-04-12', '18:00:00', '19:45:00', 54.99, '01:45:00', 8, 2),
	(29, 8, 'PAL2240', '2017-04-15', '01:00:00', '02:45:00', 69.99, '01:45:00', 8, 2),
	(30, 8, 'PAL2250', '2017-04-15', '18:00:00', '19:45:00', 74.99, '01:45:00', 8, 2),
	(31, 8, 'PAL2260', '2017-04-19', '01:00:00', '02:45:00', 39.99, '01:45:00', 8, 1),
	(32, 8, 'PAL2270', '2017-04-19', '18:00:00', '19:45:00', 39.99, '01:45:00', 8, 1);
/*!40000 ALTER TABLE `vuelo` ENABLE KEYS */;

-- Volcando estructura para disparador bd_aerolinea_amr.vuelo_before_delete
DROP TRIGGER IF EXISTS `vuelo_before_delete`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `vuelo_before_delete` BEFORE DELETE ON `vuelo` FOR EACH ROW begin
	  DECLARE antes INT DEFAULT 0;
	  DECLARE despues INT DEFAULT 0;
	  DECLARE antes1 INT DEFAULT 0;
	  DECLARE despues1 INT DEFAULT 0;
	  
	  /*La ocupacion siempre se borrará*/
	  select count(*) into antes from ocupacion;
     delete from ocupacion where IDVuelo = OLD.ID;
	  select count(*) into despues from ocupacion;
     
     /*No compruebo si se borran porque habra casos en los que no se borren*/
	  delete from pasajeroservicio where ID_Vuelo = OLD.ID;
	  
	  /*Eliminamos a los pasajeros menores, para que no haya problema con las claves foraneas*/
	  delete from pasajero where ID_Reserva in (select ID from reserva where ID_Vuelo_Ida is null and ID_Vuelo_Vuelta is null) and ID_Tutor is not null;
	  
	  /*Eliminamos a los pasajeros adultos como el Barça al PSG*/
	  delete from pasajero where ID_Reserva in (select ID from reserva where ID_Vuelo_Ida is null and ID_Vuelo_Vuelta is null) and ID_Tutor is null;
     
	  /*Eliminamos las reservas que tengan sus dos IDs de vuelo a null*/
     delete from reserva where ID_Vuelo_Ida is null and ID_Vuelo_Vuelta is null;
     
     if (antes = despues) then
	  	SIGNAL SQLSTATE '75001' set MYSQL_ERRNO = '5020', MESSAGE_TEXT = 'NO facturaste.';
	  end if;
end//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
