CREATE TABLE IF NOT EXISTS comics (
  comic_id int(5) NOT NULL AUTO_INCREMENT,
  title varchar(200) NOT NULL,
  category varchar(100) NOT NULL,
  PRIMARY KEY (comic_id)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=UTF8;
