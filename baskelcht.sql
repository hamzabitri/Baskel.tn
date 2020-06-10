-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 10 juin 2020 à 15:27
-- Version du serveur :  5.7.24
-- Version de PHP :  7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `baskelcht`
--

-- --------------------------------------------------------

--
-- Structure de la table `fos_user`
--

DROP TABLE IF EXISTS `fos_user`;
CREATE TABLE IF NOT EXISTS `fos_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_957A6479C05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `fos_user`
--

INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`) VALUES
(1, 'chtourou', 'chtourou', 'mariem.chtourou@esprit.tn', 'mariem.chtourou@esprit.tn', 1, NULL, '$2y$13$CesRlb5AISg4xeFnEUpCXOpP9sXrVn6mgRfLcpDakMZdYeKXjrpqG', '2020-06-10 12:41:04', NULL, NULL, 'a:1:{i:0;s:16:\"ROLE_SUPER_ADMIN\";}'),
(2, 'abdou', 'abdou', 'abdou@gmail.com', 'abdou@gmail.com', 1, NULL, '$2y$13$rNlaHOZmlpmlE5WAbUGDGuBDFhYbF7c5uxPSQVnXjNzwmajxDhEvW', '2020-06-04 23:25:20', NULL, NULL, 'a:0:{}');

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE IF NOT EXISTS `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iduser` int(11) DEFAULT NULL,
  `idstation` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_5E9E89CB5E5C27E9` (`iduser`),
  KEY `IDX_5E9E89CB403DAADF` (`idstation`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `location`
--

INSERT INTO `location` (`id`, `iduser`, `idstation`) VALUES
(10, 1, 30),
(11, 1, 33);

-- --------------------------------------------------------

--
-- Structure de la table `station`
--

DROP TABLE IF EXISTS `station`;
CREATE TABLE IF NOT EXISTS `station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_station` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `longitude` decimal(10,5) NOT NULL,
  `latitude` decimal(10,5) NOT NULL,
  `nbrVelo` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `station`
--

INSERT INTO `station` (`id`, `nom_station`, `longitude`, `latitude`, `nbrVelo`) VALUES
(30, 'Hedi Nouira', '10.15760', '36.85602', 4),
(31, 'Le Chat Noir', '10.15960', '36.84670', 8),
(32, 'Collège Menzah 6', '10.16931', '36.84357', 1),
(33, 'Le Zink', '10.17745', '36.83709', 9),
(34, 'Kiosque Menzah 1', '10.18164', '36.83687', 4),
(35, 'Place Pasteur', '10.17805', '36.82154', 14),
(38, 'Abdou est mort', '34.84800', '29.31089', 0);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `FK_5E9E89CB403DAADF` FOREIGN KEY (`idstation`) REFERENCES `station` (`id`),
  ADD CONSTRAINT `FK_5E9E89CB5E5C27E9` FOREIGN KEY (`iduser`) REFERENCES `fos_user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
