CREATE TABLE friendship(
    id bigint not null auto_increment,
    id_requested bigint not null,
    id_receiver bigint not null,
    status_friendship varchar(20) not null,
    created DATETIME,
    updated DATETIME,
    PRIMARY KEY (id)
)engine=InnoDB default charset=utf8;