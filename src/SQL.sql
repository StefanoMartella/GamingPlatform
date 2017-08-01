CREATE DATABASE IF NOT EXISTS `Gaming`;

USE `Gaming`;

/*UTENTE*/

DROP TABLE IF EXISTS `utente`;
CREATE TABLE `utente`(
`id` int(5) PRIMARY KEY NOT NULL AUTO_INCREMENT,
`nome` varchar(20) NOT NULL,
`cognome` varchar(20) NOT NULL,
`username` varchar(30) NOT NULL UNIQUE,
`email` varchar(50) NOT NULL UNIQUE,
`password` varchar(30) NOT NULL,
`tipo` ENUM('U','M') NOT NULL DEFAULT 'U',
`livello` int(33) NOT NULL DEFAULT '0'
)ENGINE=innoDB;


/*RECENSIONE*/

DROP TABLE IF EXISTS `recensione`;
CREATE TABLE `recensione`(
`id` int(5) PRIMARY KEY NOT NULL AUTO_INCREMENT,
`approvazione` tinyint(1) NOT NULL DEFAULT '0',
`testo` varchar(255) NOT NULL,
`gioco` int(5) NOT NULL REFERENCES gioco (`id`),
`utente` int(5) NOT NULL REFERENCES utente (`id`),
UNIQUE (`gioco`, `utente`)
)ENGINE=innoDB;



/*GIOCO*/

DROP TABLE IF EXISTS `gioco`;
CREATE TABLE `gioco`(
`id` int(5) PRIMARY KEY NOT NULL AUTO_INCREMENT,
`nome` varchar(30) NOT NULL UNIQUE,
`exp` int(2) NOT NULL
)ENGINE=innoDB;


/*TIMELINE*/

DROP TABLE IF EXISTS `timeline`;
CREATE TABLE `timeline`(
`id` int(5) PRIMARY KEY NOT NULL AUTO_INCREMENT,
`data` date NOT NULL,
`livello` int(2) NOT NULL DEFAULT '0',
`utente` int(5) NOT NULL REFERENCES utente (`id`),
UNIQUE (`utente`, `livello`)
)ENGINE=innoDB;


/*VOTO*/

DROP TABLE IF EXISTS `voto`;
CREATE TABLE `voto`(
`votazione` int(1) NOT NULL DEFAULT '0',
`utente` int(5) NOT NULL REFERENCES utente (`id`),
`gioco` int(5) NOT NULL REFERENCES gioco (`id`),
PRIMARY KEY (`utente`, `gioco`)
)ENGINE=innoDB;

/*TRIGGER on level update.*/

DROP TRIGGER IF EXISTS `updatetimeline`;
DELIMITER $$
CREATE TRIGGER `updatetimeline`
AFTER UPDATE ON `utente`
FOR EACH ROW BEGIN
IF(OLD.livello != NEW.livello)
THEN INSERT INTO timeline(utente, data, livello) VALUES (OLD.id, DATE(NOW()), NEW.livello);
END IF;
END
$$
DELIMITER ;


/* TRIGGER on new user registration */

DROP TRIGGER IF EXISTS `nuovoutente`;
CREATE TRIGGER `nuovoutente`
AFTER INSERT ON `utente`
FOR EACH ROW
INSERT INTO timeline(utente, data, livello) VALUES (NEW.id, DATE(NOW()), NEW.livello);

