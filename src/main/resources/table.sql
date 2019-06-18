create database test default character set utf8;
user test;
create table user(
	id int not null primary key auto_increment,
	name varchar(64),
	age int
);

create table custinfo(
	id int not null primary key auto_increment,
	phone varchar(64)
);