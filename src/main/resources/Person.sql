CREATE TABLE `Person` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `country` varchar(20) DEFAULT NULL,
  `email` varchar(50) NOT NULL DEFAULT '',
  `birthDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `un_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;