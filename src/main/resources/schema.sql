DROP TABLE IF EXISTS PLAYER;

CREATE TABLE PLAYER (
id INT AUTO_INCREMENT PRIMARY KEY,
firstname varchar(200) not null,
lastname varchar(200) not null,
number Int default null,
squad varchar(200) default null
);