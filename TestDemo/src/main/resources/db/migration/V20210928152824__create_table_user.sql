CREATE TABLE if not exists user_info
(
    user_id       varchar(50) not null comment 'ID' primary key,
    user_name     varchar(50) not null comment '用户名称',
    user_password varchar(50) null comment '用户密码',
    user_email    varchar(50) null comment '用户邮箱'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE utf8mb4_bin
    comment '用户表';