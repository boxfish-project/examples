CREATE TABLE IF NOT EXISTS TB_USER(
	ID BIGINT PRIMARY KEY AUTO_INCREMENT,
	USERNAME VARCHAR(20) NOT NULL,
	PASSWORD VARCHAR(20) NOT NULL,
	CREATED BIGINT NOT NULL
);