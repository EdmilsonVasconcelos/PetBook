CREATE TABLE pet(
    id bigint not null auto_increment,
    name varchar(255) not null,
    date_of_age DATETIME(6),
    gender varchar(6) not null,
    created DATETIME(6),
    updated DATETIME(6),
    PRIMARY KEY (id)
)engine=InnoDB default charset=utf8;