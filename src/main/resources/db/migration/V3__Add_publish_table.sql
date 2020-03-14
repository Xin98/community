create table publish(
    id BIGINT primary key auto_increment,
    creater_id varchar(100),
    title varchar(50),
    detail text,
    tag varchar(256),
    view_count int default 0,
    read_count int default 0,
    comment_count int default 0,
    like_count int default 0,
    dislike_count int default 0,
    stars int default 0,
    gmt_create bigint,
    gmt_modified bigint
);