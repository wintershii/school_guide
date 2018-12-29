

-- 用户表
CREATE TABLE school_user (
  id int NOT NULL AUTO_INCREMENT,
  username varchar(20) NOT NULL,
  password varchar(20) NOT NULL,
  role int NOT NULL COMMENT '用户角色  0--普通用户  1-- 管理员',
  create_time datetime DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  PRIMARY KEY (id),
  
)



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