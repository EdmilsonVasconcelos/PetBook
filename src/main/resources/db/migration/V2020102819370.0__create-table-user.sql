CREATE TABLE user(
    id bigint not null auto_increment,
    email varchar(255) not null,
    password TEXT not null,
    admin boolean not null,
    created DATETIME(6),
    updated DATETIME(6),
  
    primary key (id)
)engine=InnoDB default charset=utf8;