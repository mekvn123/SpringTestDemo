CREATE TABLE if not exists post
(
    post_id varchar(50) not null comment 'ID' primary key,
    comment varchar(50) not null comment '留言',
    user_id varchar(50) not null comment '用户ID',
    CONSTRAINT FK_POST_USER_INFO FOREIGN KEY (user_id)
    REFERENCES user_info (user_id)
    )
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE utf8mb4_bin
    comment '回复信息表';