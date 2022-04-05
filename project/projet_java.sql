-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 05 avr. 2022 à 16:20
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projet_java`
--

-- --------------------------------------------------------

--
-- Structure de la table `chambre`
--

DROP TABLE IF EXISTS `chambre`;
CREATE TABLE IF NOT EXISTS `chambre` (
  `numporte` int(11) NOT NULL,
  `nom` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `traitement` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`numporte`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `gestionnaire`
--

DROP TABLE IF EXISTS `gestionnaire`;
CREATE TABLE IF NOT EXISTS `gestionnaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_chambre` int(11) NOT NULL,
  `id_medicament` int(11) NOT NULL,
  `heure` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `CHAMBRE` (`id_chambre`),
  KEY `MEDICAMENT` (`id_medicament`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `heure`
--

DROP TABLE IF EXISTS `heure`;
CREATE TABLE IF NOT EXISTS `heure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `heure`
--

INSERT INTO `heure` (`id`, `libelle`) VALUES
(1, '8h'),
(2, '9h'),
(3, '10h'),
(4, '11h'),
(5, '12h'),
(6, '14h'),
(7, '15h'),
(8, '16h'),
(9, '17h');

-- --------------------------------------------------------

--
-- Structure de la table `jour`
--

DROP TABLE IF EXISTS `jour`;
CREATE TABLE IF NOT EXISTS `jour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `jour`
--

INSERT INTO `jour` (`id`, `libelle`) VALUES
(1, 'Lundi'),
(2, 'Mardi'),
(3, 'Mercredi'),
(4, 'Jeudi'),
(5, 'Vendredi');

-- --------------------------------------------------------

--
-- Structure de la table `medicaments`
--

DROP TABLE IF EXISTS `medicaments`;
CREATE TABLE IF NOT EXISTS `medicaments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(30) COLLATE utf8_bin NOT NULL,
  `niveau_toxicite` varchar(10) COLLATE utf8_bin NOT NULL,
  `stock` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `medicaments`
--

INSERT INTO `medicaments` (`id`, `libelle`, `niveau_toxicite`, `stock`) VALUES
(1, 'ALPHA-BLOQUANTS', '3', 92),
(5, 'azaza', '4', 0),
(6, 'paracetamol', '2', 34);

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(40) COLLATE utf8_bin NOT NULL,
  `prenom` varchar(40) COLLATE utf8_bin NOT NULL,
  `email` varchar(40) COLLATE utf8_bin NOT NULL,
  `adresse_postale` varchar(30) COLLATE utf8_bin NOT NULL,
  `numero_secu` varchar(60) COLLATE utf8_bin NOT NULL,
  `mutuelle` varchar(60) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id`, `nom`, `prenom`, `email`, `adresse_postale`, `numero_secu`, `mutuelle`) VALUES
(7, 'LIGNANI', 'Quentin', 'qlignani@gmail.com', '18 rue de Paris', '12345676', '23456543');

-- --------------------------------------------------------

--
-- Structure de la table `planning`
--

DROP TABLE IF EXISTS `planning`;
CREATE TABLE IF NOT EXISTS `planning` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_jour` int(11) NOT NULL,
  `id_heure` int(11) NOT NULL,
  `id_type` int(11) NOT NULL,
  `id_utilisateurs` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_heure` (`id_heure`),
  KEY `id_jour` (`id_jour`),
  KEY `id_utilisateurs` (`id_utilisateurs`),
  KEY `id_type` (`id_type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `planning`
--

INSERT INTO `planning` (`id`, `id_jour`, `id_heure`, `id_type`, `id_utilisateurs`) VALUES
(1, 1, 1, 2, 2),
(2, 1, 8, 2, 2),
(3, 3, 5, 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `rdv`
--

DROP TABLE IF EXISTS `rdv`;
CREATE TABLE IF NOT EXISTS `rdv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_utilisateurs` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `date` varchar(20) COLLATE utf8_bin NOT NULL,
  `heure` varchar(20) COLLATE utf8_bin NOT NULL,
  `type` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_patient` (`id_patient`),
  KEY `id_medecin` (`id_utilisateurs`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `rdv`
--

INSERT INTO `rdv` (`id`, `id_utilisateurs`, `id_patient`, `date`, `heure`, `type`) VALUES
(1, 1, 2, '2021-02-22', '12:00', '1');

-- --------------------------------------------------------

--
-- Structure de la table `type_rdv`
--

DROP TABLE IF EXISTS `type_rdv`;
CREATE TABLE IF NOT EXISTS `type_rdv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(20) COLLATE utf8_bin NOT NULL,
  `undeletable` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `type_rdv`
--

INSERT INTO `type_rdv` (`id`, `libelle`, `undeletable`) VALUES
(1, 'Generaliste', 0),
(2, 'Dermatologue', 0);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

DROP TABLE IF EXISTS `utilisateurs`;
CREATE TABLE IF NOT EXISTS `utilisateurs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) COLLATE utf8_bin NOT NULL,
  `prenom` varchar(30) COLLATE utf8_bin NOT NULL,
  `email` varchar(40) COLLATE utf8_bin NOT NULL,
  `mdp` varchar(20) COLLATE utf8_bin NOT NULL,
  `role` varchar(10) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `nom`, `prenom`, `email`, `mdp`, `role`) VALUES
(1, 'monsieur', 'Administrateur', 'admin@admin.fr', '1234', 'ADMIN'),
(2, 'LIGNANI', 'QUENTIN', 'med@med.fr', '1234', 'MED'),
(4, 'monsieur', 'gestionnaire', 'gest@gest.fr', '1234', 'GEST'),
(5, 'Administratif', 'Damon', 'administratif@administratif.fr', '1234', 'TRATIF'),
(6, 'madame', 'infermiere', 'infermiere@infermiere.fr', '1234', 'INF');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `planning`
--
ALTER TABLE `planning`
  ADD CONSTRAINT `id_heure` FOREIGN KEY (`id_heure`) REFERENCES `heure` (`id`),
  ADD CONSTRAINT `id_jour` FOREIGN KEY (`id_jour`) REFERENCES `jour` (`id`),
  ADD CONSTRAINT `id_type` FOREIGN KEY (`id_type`) REFERENCES `type_rdv` (`id`),
  ADD CONSTRAINT `id_utilisateurs` FOREIGN KEY (`id_utilisateurs`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `rdv`
--
ALTER TABLE `rdv`
  ADD CONSTRAINT `id_medecin` FOREIGN KEY (`id_utilisateurs`) REFERENCES `utilisateurs` (`id`),
  ADD CONSTRAINT `id_patient` FOREIGN KEY (`id_patient`) REFERENCES `utilisateurs` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
