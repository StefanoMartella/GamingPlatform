DROP DATABASE IF EXISTS `Gaming`;
CREATE DATABASE `Gaming`;

USE `Gaming`;


/* UTENTE */

CREATE TABLE `utente`(
`id` int(5) PRIMARY KEY AUTO_INCREMENT,
`nome` varchar(50) NOT NULL,
`cognome` varchar(50) NOT NULL,
`username` varchar(50) NOT NULL UNIQUE,
`email` varchar(50) NOT NULL UNIQUE,
`password` varchar(50) NOT NULL,
`tipo` varchar(20) NOT NULL DEFAULT 'utente',
`livello` int(10) NOT NULL DEFAULT '0',
`puntiExp` int(10) NOT NULL DEFAULT '0'
);


/* GIOCO */

CREATE TABLE `gioco`(
`id` int(5) PRIMARY KEY AUTO_INCREMENT,
`nome` varchar(30) NOT NULL UNIQUE,
`exp` int(2) NOT NULL
);


/* RECENSIONE */

CREATE TABLE `recensione`(
`id` int(5) PRIMARY KEY AUTO_INCREMENT,
`approvazione` tinyint(1) NOT NULL DEFAULT '0',
`testo` varchar(255) NOT NULL,
`gioco` int(5) ,
`utente` int(5) ,
FOREIGN KEY (gioco) REFERENCES gioco(`id`) ON DELETE CASCADE,
FOREIGN KEY (utente) REFERENCES utente(`id`) ON DELETE CASCADE,
UNIQUE (`gioco`, `utente`)
);


/* TIMELINE */

CREATE TABLE `timeline`(
`data` date NOT NULL,
`livello` int(2) NOT NULL DEFAULT '0',
`utente` int(5) ,
PRIMARY KEY (`utente`,`livello`),
FOREIGN KEY (utente) REFERENCES utente (`id`) ON DELETE CASCADE
);


/* VOTO */

CREATE TABLE `voto`(
`votazione` int(1) NOT NULL DEFAULT '0',
`utente` int(5) ,
`gioco` int(5) ,
PRIMARY KEY (`utente`, `gioco`),
FOREIGN KEY (utente) REFERENCES utente (`id`) ON DELETE CASCADE,
FOREIGN KEY (gioco) REFERENCES gioco (`id`) ON DELETE CASCADE
);


/* TRIGGER on level update */

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

CREATE TRIGGER `nuovoutente`
AFTER INSERT ON `utente`
FOR EACH ROW
INSERT INTO timeline(utente, data, livello) VALUES (NEW.id, DATE(NOW()), NEW.livello);


/* TRIGGER on new user deletion */

CREATE TRIGGER `utentecancellato`
AFTER DELETE ON `utente`
FOR EACH ROW 
DELETE FROM timeline WHERE utente = OLD.id;


/* DUMP UTENTE */

INSERT INTO `utente`(`id`,`nome`,`cognome`,`username`,`email`,`password`,`tipo`,`livello`,`puntiExp`) VALUES
(1, 'Peter', 'Parker', 'Spiderman', 'spiderman@avengers.com', 'ragnetto', 'moderatore', 0, 25),
(2, 'Tony', 'Stark', 'IronMan', 'ironman@avengers.com', 'avengers', 'amministratore', 3, 340),
(3, 'Bruce', 'Banner', 'Hulk', 'hulk@avengers.com', 'uomoverde', 'utente', 2, 230),
(4, 'Steve', 'Rogers', 'CapitanAmerica', 'capitanamerica@avengers.com', 'scudo', 'utente', 2, 250),
(5, 'Clark','Kent','Superman','superman@dc.com','krypton','utente', 3, 340),
(6, 'Alan','Scott','Lanterna verde','lanternaverde@dc.com','anelloverde','utente', 1, 75),
(7, 'Bruce','Wayne','Batman','batman@dc.com','pipistrello','moderatore', 4, 430),
(8, 'Jay','Garrick','Flash','flash@dc.com','fulmine','utente', 2, 220);


/* DUMP GIOCO */

INSERT INTO `gioco`(`id`,`nome`,`exp`) VALUES
(1, 'Poker', 30),
(2, 'Briscola', 5),
(3, 'Asso piglia tutto', 10),
(4, 'AcchiappaLaTalpa', 20),
(5, 'Clash Royale', 5),
(6, 'Risiko', 40),
(7, 'Monopoli', 35),
(8, 'Fifa', 20),
(9, 'Lol', 40),
(10,'Super Mario', 40);


/* DUMP RECENSIONI */

INSERT INTO `recensione`(`id`,`approvazione`,`testo`,`gioco`,`utente`) VALUES
(1, 0, 'Recensione del Capitano', 1, 4),
(2, 0, 'Recensione di HULKK', 3, 3),
(3, 0, 'Recensione di Flash', 5, 8),
(4, 0, 'Recensione del signor Tony', 2, 2),
(5, 1, 'Fenomenale', 1, 2),
(6, 1, 'Bel Gioco!!', 4, 6),
(7, 1, 'Un po bruttino', 3, 4);


/* DUMP TIMELINE */

INSERT INTO `timeline`(`data`,`livello`,`utente`) VALUES
('2017/08/15', 0, 2),
('2017/08/15', 0, 3),
('2017/08/15', 0, 4),
('2017/08/15', 0, 5),
('2017/08/15', 0, 6),
('2017/08/15', 0, 7),
('2017/08/15', 0, 8),
('2017/08/15', 1, 2),
('2017/08/15', 1, 5),
('2017/08/15', 2, 5),
('2017/08/15', 1, 7),
('2017/08/15', 2, 7),
('2017/08/15', 3, 7),
('2017/08/15', 1, 8),
('2017/08/15', 2, 2),
('2017/08/15', 1, 3),
('2017/08/15', 1, 4);


/* DUMP VOTO */

INSERT INTO `voto`(`votazione`,`utente`,`gioco`) VALUES
(4, 2, 2),
(3, 3, 5),
(1, 2, 4),
(5, 4, 1),
(3, 1, 1),
(5, 4, 3);
