create table profile(
id int primary key auto_increment,
username varchar(20) not null unique
);

insert into profile(username)values('lenicliu');
insert into profile(username)values('richard');
insert into profile(username)values('allen');
insert into profile(username)values('limbor');