CREATE TABLE pet(
    id bigint not null auto_increment,
    name varchar(255) not null,
    date_of_age DATETIME,
    gender varchar(6) not null,
    created DATETIME,
    updated DATETIME,
    PRIMARY KEY (id)
)engine=InnoDB default charset=utf8;