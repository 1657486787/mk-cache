CREATE DATABASE trans1;

CREATE TABLE trans1.`tcc_fly_order` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `bus_id` VARCHAR(100) DEFAULT NULL,
  `idcard` VARCHAR(100) DEFAULT NULL,
  `STATUS` TINYINT(2) DEFAULT NULL,
  `remark` VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

INSERT INTO trans1.tcc_fly_order (`bus_id`,`idcard`,`STATUS`,`remark`) VALUES('init','init',0,'初始化');
INSERT INTO trans1.tcc_fly_order (`bus_id`,`idcard`,`STATUS`,`remark`) VALUES('init','init',0,'初始化');
INSERT INTO trans1.tcc_fly_order (`bus_id`,`idcard`,`STATUS`,`remark`) VALUES('init','init',0,'初始化');
INSERT INTO trans1.tcc_fly_order (`bus_id`,`idcard`,`STATUS`,`remark`) VALUES('init','init',0,'初始化');



CREATE DATABASE trans2;

CREATE TABLE trans2.`tcc_fly_order` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `bus_id` VARCHAR(100) DEFAULT NULL,
  `idcard` VARCHAR(100) DEFAULT NULL,
  `STATUS` TINYINT(2) DEFAULT NULL,
  `remark` VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

INSERT INTO trans2.tcc_fly_order (`bus_id`,`idcard`,`STATUS`,`remark`) VALUES('init','init',0,'初始化');
INSERT INTO trans2.tcc_fly_order (`bus_id`,`idcard`,`STATUS`,`remark`) VALUES('init','init',0,'初始化');
INSERT INTO trans2.tcc_fly_order (`bus_id`,`idcard`,`STATUS`,`remark`) VALUES('init','init',0,'初始化');
INSERT INTO trans2.tcc_fly_order (`bus_id`,`idcard`,`STATUS`,`remark`) VALUES('init','init',0,'初始化');
INSERT INTO trans2.tcc_fly_order (`bus_id`,`idcard`,`STATUS`,`remark`) VALUES('init','init',0,'初始化');