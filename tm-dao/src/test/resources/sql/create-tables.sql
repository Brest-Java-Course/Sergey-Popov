SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `person`;
SET foreign_key_checks = 1;
CREATE TABLE `person` (
`person_id` int(11) NOT NULL AUTO_INCREMENT,
`person_fname` varchar(45) NOT NULL,
`person_lname` varchar(45) NOT NULL,
PRIMARY KEY (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(45) NOT NULL,
  `task_startdate` datetime(3) NOT NULL,
  `task_enddate` datetime(3) NULL,
  `task_elapsedtime` int(11) NULL DEFAULT 0,
  `person_id` int(11) NOT NULL,
  `task_state` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`task_id`),
  KEY `fk_pers_idx` (`person_id`),
  CONSTRAINT `fk_pers` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;