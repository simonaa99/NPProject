/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 10.4.14-MariaDB : Database - ravnicadrustveneigre
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ravnicadrustveneigre` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `ravnicadrustveneigre`;

/*Table structure for table `clan` */

DROP TABLE IF EXISTS `clan`;

CREATE TABLE `clan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(200) DEFAULT NULL,
  `prezime` varchar(200) DEFAULT NULL,
  `brojTelefona` varchar(20) DEFAULT NULL,
  `adresa` varchar(100) DEFAULT NULL,
  `kategorijaId` bigint(20) DEFAULT NULL,
  `clanskaKartaId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `brojTelefona` (`brojTelefona`),
  KEY `kategorijaID` (`kategorijaId`),
  KEY `clanskaKartaID` (`clanskaKartaId`),
  CONSTRAINT `clan_ibfk_1` FOREIGN KEY (`kategorijaId`) REFERENCES `kategorijaclanova` (`id`),
  CONSTRAINT `clan_ibfk_2` FOREIGN KEY (`clanskaKartaId`) REFERENCES `clanskakarta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

/*Data for the table `clan` */

insert  into `clan`(`id`,`ime`,`prezime`,`brojTelefona`,`adresa`,`kategorijaId`,`clanskaKartaId`) values 
(1,'Aleksa','Glišović','0658794258','Jurija Gagarina 67, Beograd',2,2),
(2,'MIna','Kostic','0659487231','Vojvode Supljikca, Beograd',3,3),
(3,'Stefan','Kostic','063218945','Bulever Despota Stefana 4, Beograd',4,4),
(4,'Marina','Milic','0695487319','Bulevar Zorana Djidjica 50, Beograd',2,5),
(5,'Aleksandar','Marojevic','0654892379','Vojvode Stepe 10, Beograd',4,6);

/*Table structure for table `clanskakarta` */

DROP TABLE IF EXISTS `clanskakarta`;

CREATE TABLE `clanskakarta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brojClanskeKarte` varchar(15) DEFAULT NULL,
  `datumIzdavanja` date DEFAULT NULL,
  `datumIsteka` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Data for the table `clanskakarta` */

insert  into `clanskakarta`(`id`,`brojClanskeKarte`,`datumIzdavanja`,`datumIsteka`) values 
(2,'484800353715556','2022-07-09','2023-07-09'),
(3,'678613165253150','2022-07-12','2023-07-12'),
(4,'387731874863436','2022-07-12','2023-07-12'),
(5,'876843625422084','2022-07-14','2023-07-14'),
(6,'365326636430827','2022-07-12','2023-07-12');

/*Table structure for table `igra` */

DROP TABLE IF EXISTS `igra`;

CREATE TABLE `igra` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(200) DEFAULT NULL,
  `brojIgraca` varchar(11) DEFAULT NULL,
  `kategorijaId` bigint(20) DEFAULT NULL,
  `izdavacId` bigint(20) DEFAULT NULL,
  `kolicina` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `kategorijaId` (`kategorijaId`),
  KEY `izdavacId` (`izdavacId`),
  CONSTRAINT `igra_ibfk_1` FOREIGN KEY (`kategorijaId`) REFERENCES `kategorijaigara` (`id`),
  CONSTRAINT `igra_ibfk_2` FOREIGN KEY (`izdavacId`) REFERENCES `izdavac` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

/*Data for the table `igra` */

insert  into `igra`(`id`,`naziv`,`brojIgraca`,`kategorijaId`,`izdavacId`,`kolicina`) values 
(4,'Katan','4',1,4,4),
(5,'Underwater Cities','4',1,5,3),
(6,'7 WONDERS ARCHITECTS','7',1,6,5),
(7,'Catan Vitezovi i Gradovi','4',1,4,1),
(8,'Dixit Refresh - srpski jezik','7',1,7,10),
(11,'TAKO CICA KOZA SIR PICA','8',3,9,5),
(12,'SPLENDOR','4',2,8,6),
(13,'PATCHWORK NA SRPSKOM','2',5,10,7),
(14,'7 Wonders Duel','2',5,6,1),
(15,'Ticket to Ride: Europe','5',3,11,2),
(16,'Omerta','5',4,12,12),
(17,'Pictures','5',4,13,5),
(18,'Sushi Go!','5',4,14,5),
(19,'Bang!','7',6,15,12);

/*Table structure for table `izdavac` */

DROP TABLE IF EXISTS `izdavac`;

CREATE TABLE `izdavac` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `imePrezime` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

/*Data for the table `izdavac` */

insert  into `izdavac`(`id`,`imePrezime`) values 
(4,'Kosmos'),
(5,'Delicious Games'),
(6,'Repos Production'),
(7,'Libellud'),
(8,'Marc Andre'),
(9,'Blue Orange Games x Mipl'),
(10,'Lookout Games'),
(11,'Days of Wonder'),
(12,'Helvetiq'),
(13,'PD Verlag'),
(14,'Gamewright'),
(15,'dV GIOCHI');

/*Table structure for table `iznajmljivanje` */

DROP TABLE IF EXISTS `iznajmljivanje`;

CREATE TABLE `iznajmljivanje` (
  `clanId` bigint(20) NOT NULL,
  `igraId` bigint(20) NOT NULL,
  `datumIznajmljivanja` date DEFAULT NULL,
  `datumVracanja` date DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `clanId` (`clanId`),
  KEY `igraId` (`igraId`),
  CONSTRAINT `iznajmljivanje_ibfk_1` FOREIGN KEY (`clanId`) REFERENCES `clan` (`id`),
  CONSTRAINT `iznajmljivanje_ibfk_2` FOREIGN KEY (`igraId`) REFERENCES `igra` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `iznajmljivanje` */

insert  into `iznajmljivanje`(`clanId`,`igraId`,`datumIznajmljivanja`,`datumVracanja`,`id`) values 
(1,4,'2022-07-11','2022-07-11',1),
(4,12,'2022-07-12',NULL,2),
(5,4,'2022-07-12',NULL,3),
(4,16,'2022-07-13','2022-07-14',4),
(1,4,'2022-07-14',NULL,5);

/*Table structure for table `kategorijaclanova` */

DROP TABLE IF EXISTS `kategorijaclanova`;

CREATE TABLE `kategorijaclanova` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(40) DEFAULT NULL,
  `popustNaClanarinu` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `kategorijaclanova` */

insert  into `kategorijaclanova`(`id`,`naziv`,`popustNaClanarinu`) values 
(1,'Gost',0),
(2,'Rednovan',10),
(3,'Takmmičar',20),
(4,'Šampion meseca',30);

/*Table structure for table `kategorijaigara` */

DROP TABLE IF EXISTS `kategorijaigara`;

CREATE TABLE `kategorijaigara` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `kategorijaigara` */

insert  into `kategorijaigara`(`id`,`naziv`) values 
(1,'Porodične_igre'),
(2,'Kartične_igre'),
(3,'Igre_na_srpskom'),
(4,'Party_games'),
(5,'Igre_za_dvoje'),
(6,'Igre_za_decu');

/*Table structure for table `radnik` */

DROP TABLE IF EXISTS `radnik`;

CREATE TABLE `radnik` (
  `radnikID` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `ime` varchar(40) DEFAULT NULL,
  `prezime` varchar(40) DEFAULT NULL,
  `prijavljen` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`radnikID`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `radnik` */

insert  into `radnik`(`radnikID`,`username`,`password`,`ime`,`prezime`,`prijavljen`) values 
(1,'simi','simi123456','Simona','Baosic',NULL),
(2,'ludidzoni','ludidzoni01','Nikola','Milanov',NULL),
(4,'dora','dora12345','Teodora','Milic',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
