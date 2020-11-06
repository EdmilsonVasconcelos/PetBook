CREATE TABLE post(
    id bigint not null auto_increment,
    post varchar(255) not null,
    created DATETIME,
    updated DATETIME,
    PRIMARY KEY (id)
)engine=InnoDB default charset=utf8;