## xin社区

## 资料
[Spring 文档](https://spring.io/guides)  
[Spring web 文档](https://spring.io/guides/gs/serving-web-content/)  
[Spring boot 官方文档](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/)  
[Github 登录文档](https://developer.github.com/apps/building-github-apps/creating-a-github-app/)  
[Bootstrap 文档](https://v3.bootcss.com/components/)  
[okHttp 文档](https://square.github.io/okhttp/)  
[mybatis 文档](http://mybatis.org/spring/)  

## 脚本

```sql
create table USER
(
	ID INTEGER primary key auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	constraint USER_PK
		primary key (ID)
);

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
```