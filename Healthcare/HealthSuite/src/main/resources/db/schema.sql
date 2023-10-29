-- HealthSuite db

-- users
create table if not exists `users` (
	username varchar(50) not null primary key,
	password varchar(50) not null,
	enabled boolean not null
);


-- authorities
create table if not exists `authorities` (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint FK_AUTHRITIES_USERS
	foreign key (username) references users(username)
);

create unique index IDX_USERS_AUTHORITIES on authorities (username, authority);


insert into `users` (`username`, `password`, `enabled`)
values 
	('rlakra', 'password', true),
	('rslakra', 'secret', true),	
	('lakra', 'password', true)	
;

insert into `authorities` (`username`, `authority`)
values 
	('rlakra', 'ROLE_USER'),
	('rslakra', 'ROLE_USER'),	
	('rslakra', 'ROLE_ADMIN'),	
	('lakra', 'ROLE_USER')	
;
