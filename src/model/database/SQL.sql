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
`tipo` varchar(20) NOT NULL DEFAULT 'utente',
`livello` int(10) NOT NULL DEFAULT '0',
`puntiExp` int(10) NOT NULL DEFAULT '0'
);



/*GIOCO*/

DROP TABLE IF EXISTS `gioco`;
CREATE TABLE `gioco`(
`id` int(5) PRIMARY KEY NOT NULL AUTO_INCREMENT,
`nome` varchar(30) NOT NULL UNIQUE,
`exp` int(2) NOT NULL
);


/*RECENSIONE*/

DROP TABLE IF EXISTS `recensione`;
CREATE TABLE `recensione`(
`id` int(5) PRIMARY KEY NOT NULL AUTO_INCREMENT,
`approvazione` tinyint(1) NOT NULL DEFAULT '0',
`testo` varchar(255) NOT NULL,
`gioco` int(5) FOREIGN KEY REFERENCES gioco(`id`),
`utente` int(5) FOREIGN KEY REFERENCES utente(`id`),
UNIQUE (`gioco`, `utente`)
);

/*TIMELINE*/

DROP TABLE IF EXISTS `timeline`;
CREATE TABLE `timeline`(
`data` date NOT NULL,
`livello` int(2) NOT NULL DEFAULT '0',
`utente` int(5) FOREIGN KEY REFERENCES utente (`id`),
PRIMARY KEY (`utente`,`livello`)
);


/*VOTO*/

DROP TABLE IF EXISTS `voto`;
CREATE TABLE `voto`(
`votazione` int(1) NOT NULL DEFAULT '0',
`utente` int(5) FOREIGN KEY REFERENCES utente (`id`),
`gioco` int(5) FOREIGN KEY REFERENCES gioco (`id`),
PRIMARY KEY (`utente`, `gioco`)
);

/* TRIGGER on level update */

DROP TRIGGER IF EXISTS `updatetimeline`;
DELIMITER $$
CREATE TRIGGER `updatetimeline`
AFTER UPDATE ON `utente`
FOR EACH ROW BEGIN
IF(OLD.livello != NEW.livello)
THEN INSERT INTO timeline(utente, data, livello) VALUES (OLD.id, DATE(NOW()), NEW.livello);
END IF;
END $$
DELIMITER ;


/* TRIGGER on new user registration */

DROP TRIGGER IF EXISTS `nuovoutente`;
CREATE TRIGGER `nuovoutente`
AFTER INSERT ON `utente`
FOR EACH ROW
INSERT INTO timeline(utente, data, livello) VALUES (NEW.id, DATE(NOW()), NEW.livello);

/* TRIGGER on new user deletion */

DROP TRIGGER IF EXISTS `utentecancellato`;
CREATE TRIGGER `utentecancellato`
AFTER DELETE ON `utente`
FOR EACH ROW 
DELETE FROM timeline WHERE utente = OLD.id;


/* DUMP UTENTE */

INSERT INTO `utente`(`id`,`nome`,`cognome`,`username`,`email`,`password`,`tipo`,`livello`,`puntiExp`) VALUES
(1, 'Peter', 'Parker', 'Spiderman', 'uomoragno@avengers.com', 'ragnetto', 'utente', 0, 25),
(2, 'Tony', 'Stark', 'IronMan', 'starkcorporation@avengers.com', 'avengers', 'amministratore', 3, 100),
(3, 'Bruce', 'Banner', 'Hulk', 'incredibile@avengers.com', 'uomoverde', 'utente', 2, 30),
(4, 'Steve', 'Rogers', 'CapitanAmerica', 'capitano@avengers.com', 'scudo', 'moderatore', 2, 50);



/* DUMP GIOCO */

INSERT INTO `gioco`(`id`,`nome`,`exp`) VALUES
(1, 'Poker', 30),
(2, 'Briscola', 5),
(3, 'Asso piglia tutto', 10),
(4, 'AcchiappaLaTalpa', 20),
(5, 'Clash Royale', 3);

/* DUMP RECENSIONI */

INSERT INTO `recensione`(`id`,`approvazione`,`testo`,`gioco`,`utente`) VALUES
(1, 0, 'Recensione del Capitano', 1, 4),
(2, 0, 'Recensione di HULKK', 3, 3),
(3, 0, 'Recensione di Spiderman', 5, 1),
(4, 0, 'Recensione del signor Tony', 2, 2),
(5, 1, 'Fenomenale', 1, 2),
(6, 1, 'Bel Gioco!!', 4 , 1),
(7, 1, 'Un po bruttino', 3, 4);




/* DUMP TIMELINE */

INSERT INTO `timeline`(`data`,`livello`,`utente`) VALUES
('1999/03/02', 0, 2),
('2000/12/12', 0, 3),
('2002/03/05', 0, 4),
('2003/06/06', 1, 2),
('2016/02/03', 2, 2),
('2017/03/28', 1, 3),
('2016/12/15', 1, 4);


/* DUMP VOTO */

INSERT INTO `voto`(`votazione`,`utente`,`gioco`) VALUES
(4, 2, 2),
(3, 3, 5),
(1, 2, 4),
(5, 4, 1),
(3, 1, 1),
(5, 4, 3);
