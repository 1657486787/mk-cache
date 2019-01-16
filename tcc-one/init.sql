--数据库trans1
CREATE DATABASE trans1;

-- 航班表tcc_fly_order
DROP TABLE IF EXISTS trans1.`tcc_fly_order`;
CREATE TABLE trans1.`tcc_fly_order` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `bus_id` VARCHAR(50) DEFAULT NULL,
  `idcard` VARCHAR(20) DEFAULT NULL,
  `status` INT(10) UNSIGNED DEFAULT '0',
  `money` INT(10) UNSIGNED DEFAULT NULL,
  `frozen` INT(10) UNSIGNED DEFAULT '0',
  `remark` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
INSERT INTO trans1.tcc_fly_order (`bus_id`,`idcard`,`STATUS`,`money`,`frozen`,`remark`) VALUES('init','init',0,500,0,'初始化'),('init','init',0,500,0,'初始化'),('init','init',0,500,0,'初始化'),('init','init',0,500,0,'初始化');

-- tcc 表，需要自己建
CREATE TABLE trans1.`TCC_TRANSACTION_ORDER` (
  `TRANSACTION_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DOMAIN` VARCHAR(100) DEFAULT NULL,
  `GLOBAL_TX_ID` VARBINARY(32) NOT NULL,
  `BRANCH_QUALIFIER` VARBINARY(32) NOT NULL,
  `CONTENT` VARBINARY(8000) DEFAULT NULL,
  `STATUS` INT(11) DEFAULT NULL,
  `TRANSACTION_TYPE` INT(11) DEFAULT NULL,
  `RETRIED_COUNT` INT(11) DEFAULT NULL,
  `CREATE_TIME` DATETIME DEFAULT NULL,
  `LAST_UPDATE_TIME` DATETIME DEFAULT NULL,
  `VERSION` INT(11) DEFAULT NULL,
  `IS_DELETE` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`TRANSACTION_ID`),
  UNIQUE KEY `UX_TX_BQ` (`GLOBAL_TX_ID`,`BRANCH_QUALIFIER`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;



--数据库trans2
CREATE DATABASE trans2;

-- 航班表tcc_fly_order
DROP TABLE IF EXISTS trans2.`tcc_fly_order`;
CREATE TABLE trans2.`tcc_fly_order` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `bus_id` VARCHAR(50) DEFAULT NULL,
  `idcard` VARCHAR(20) DEFAULT NULL,
  `status` INT(10) UNSIGNED DEFAULT '0',
  `money` INT(10) UNSIGNED DEFAULT NULL,
  `frozen` INT(10) UNSIGNED DEFAULT '0',
  `remark` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

INSERT INTO trans2.tcc_fly_order (`bus_id`,`idcard`,`STATUS`,`money`,`frozen`,`remark`) VALUES('init','init',0,500,0,'初始化'),('init','init',0,500,0,'初始化'),('init','init',0,500,0,'初始化'),('init','init',0,500,0,'初始化'),('init','init',0,500,0,'初始化');

-- tcc 表，需要自己建
CREATE TABLE trans2.`TCC_TRANSACTION_ORDER` (
  `TRANSACTION_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DOMAIN` VARCHAR(100) DEFAULT NULL,
  `GLOBAL_TX_ID` VARBINARY(32) NOT NULL,
  `BRANCH_QUALIFIER` VARBINARY(32) NOT NULL,
  `CONTENT` VARBINARY(8000) DEFAULT NULL,
  `STATUS` INT(11) DEFAULT NULL,
  `TRANSACTION_TYPE` INT(11) DEFAULT NULL,
  `RETRIED_COUNT` INT(11) DEFAULT NULL,
  `CREATE_TIME` DATETIME DEFAULT NULL,
  `LAST_UPDATE_TIME` DATETIME DEFAULT NULL,
  `VERSION` INT(11) DEFAULT NULL,
  `IS_DELETE` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`TRANSACTION_ID`),
  UNIQUE KEY `UX_TX_BQ` (`GLOBAL_TX_ID`,`BRANCH_QUALIFIER`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;