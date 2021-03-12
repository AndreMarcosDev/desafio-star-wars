create table planet (
	id bigint not null auto_increment,
	name varchar(255) not null,
	climate varchar(255) not null,
	terrain varchar(255) not null,
	films int,
	
	primary key (id)
) engine=InnoDB default charset=utf8;
