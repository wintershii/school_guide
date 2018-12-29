
-- 地点表
CREATE TABLE school_place (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  intro varchar(200) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY place_name_index (name) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;