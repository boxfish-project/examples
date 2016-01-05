CREATE TABLE TB_USER(
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
USERNAME VARCHAR(100) NOT NULL UNIQUE,
PASSWORD VARCHAR(100) NOT NULL
);

CREATE TABLE TB_ROLE(
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE TB_AUTHORITY(
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE TB_UVR(
USER_ID BIGINT NOT NULL,
ROLE_ID BIGINT NOT NULL
);

CREATE TABLE TB_RVA(
ROLE_ID BIGINT NOT NULL,
AUTH_ID BIGINT NOT NULL
);

CREATE TABLE TB_MESSAGE(
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
UID BIGINT NOT NULL,
CONTENT VARCHAR(500) NOT NULL,
CREATED DATETIME NOT NULL,
UPDATED DATETIME NOT NULL
);