

-- 用户表
CREATE TABLE school_user (
  id int NOT NULL AUTO_INCREMENT,
  username varchar(20) NOT NULL,
  password varchar(200) NOT NULL,
  role int NOT NULL COMMENT '用户角色  0--普通用户  1-- 管理员',
  create_time datetime DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY user_name_unique (​username) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- 地点表
CREATE TABLE school_place (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  intro varchar(200) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  PRIMARY KEY (id),
  KEY place_name_index (name) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;


-- 路线表
CREATE TABLE school_route (
  id int NOT NULL AUTO_INCREMENT,
  start_id int NOT NULL,
  start_name varchar(50) NOT NULL,
  arrive_id int NOT NULL,
  arrive_name varchar(50) NOT NULL,
  distant int NOT NULL,
  create_time datetime DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  PRIMARY KEY (id),
  KEY start_arrive_id_index (start_id, arrive_id) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;