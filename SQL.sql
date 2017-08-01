CREATE DATABASE IF NOT EXISTS `Gaming`;

USE `Gaming`;

/*UTENTE*/
DROP TABLE IF EXISTS `utente`;
CREATE TABLE `utente`(
`id` int(5) PRIMARY KEY NOT NULL AUTO_INCREMENT,
`nome` varchar(20) NOT NULL,
`cognome` varchar(20) NOT NULL,
`username` varchar(10) NOT NULL UNIQUE,
`email` varchar(20) NOT NULL UNIQUE,
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
`nome` varchar(20) NOT NULL UNIQUE,
`exp` int(2) NOT NULL
)ENGINE=innoDB;


/*TIMELINE*/
DROP TABLE IF EXISTS `timeline`;
CREATE TABLE `timeline`(
`id` int(5) PRIMARY KEY NOT NULL AUTO_INCREMENT,
`data` date NOT NULL,
`livello` int(12) NOT NULL DEFAULT '0',
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

/*TRIGGER*/
DROP TRIGGER IF EXISTS 'updatetimeline'
DELIMITER $$
CREATE TRIGGER 'updatetimeline'
AFTER UPDATE 'utente'
FOR EACH ROW
DECLARE vecchio_livello INT;
DECLARE nuovo_livello INT;
SET vecchio_livello=OLD.livello;
set nuovo_livello=NEW.livello;
IF(vecchio_livello!=nuovo_livello) THEN
	INSERT INTO timeline(livello,data,utente) VALUES(nuovo_livello,DATE(NOW-----------------))
END IF;
END
$$
DELIMITER;