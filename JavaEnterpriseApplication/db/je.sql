-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versi server:                 10.1.13-MariaDB - mariadb.org binary distribution
-- OS Server:                    Win32
-- HeidiSQL Versi:               9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for je
DROP DATABASE IF EXISTS `je`;
CREATE DATABASE IF NOT EXISTS `je` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `je`;


-- Dumping structure for table je.division
DROP TABLE IF EXISTS `division`;
CREATE TABLE IF NOT EXISTS `division` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sub` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Dumping data for table je.division: 4 rows
/*!40000 ALTER TABLE `division` DISABLE KEYS */;
REPLACE INTO `division` (`Id`, `name`, `sub`) VALUES
	(1, 'IT', 'PROGRAMMER'),
	(2, 'IT', 'INFRASTRUCTURE'),
	(3, 'IT', 'ANALYST'),
	(4, 'IT', 'OPERATION');
/*!40000 ALTER TABLE `division` ENABLE KEYS */;


-- Dumping structure for table je.employee
DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `total_siblings` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- Dumping data for table je.employee: 3 rows
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
REPLACE INTO `employee` (`Id`, `name`, `address`, `gender`, `total_siblings`) VALUES
	(9, 'Mepa Kurniasih', 'Serpong Utara - Tangerang Selatan', 'Wanita', 1),
	(10, 'Labib Aflah', 'Jakarta Selatan - Tangerang Selatan', 'Pria', 2),
	(11, 'Achmad Ardiansyah', 'Kebayoran Lama, Jakarta Selatan', 'Pria', 6);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;


-- Dumping structure for table je.tr_division_employee
DROP TABLE IF EXISTS `tr_division_employee`;
CREATE TABLE IF NOT EXISTS `tr_division_employee` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_ID` int(11) NOT NULL,
  `DIVISION_ID` int(11) NOT NULL,
  `NOTES` varchar(50) DEFAULT NULL,
  `STATUS` varchar(50) NOT NULL,
  `JOIN_DATE` date NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `DIVISI_ID` (`DIVISION_ID`),
  KEY `EMPLOYEE_ID` (`EMPLOYEE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;

-- Dumping data for table je.tr_division_employee: 3 rows
/*!40000 ALTER TABLE `tr_division_employee` DISABLE KEYS */;
REPLACE INTO `tr_division_employee` (`ID`, `EMPLOYEE_ID`, `DIVISION_ID`, `NOTES`, `STATUS`, `JOIN_DATE`) VALUES
	(45, 9, 3, 'Karyawan Magang', 'Active', '2018-11-29'),
	(44, 10, 1, 'Karyawan Tetap', 'Active', '2018-11-22'),
	(46, 11, 2, 'Karyawan Magang', 'Active', '2018-08-10');
/*!40000 ALTER TABLE `tr_division_employee` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
