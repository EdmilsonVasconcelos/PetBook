CREATE TABLE post(
    id bigint not null auto_increment,
    post varchar(255) not null,
    created DATETIME(6),
    updated DATETIME(6),
    PRIMARY KEY (id)
)engine=InnoDB default charset=utf8;