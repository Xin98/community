## xin社区

## 资料
[Spring 文档](https://spring.io/guides)  
[Spring web 文档](https://spring.io/guides/gs/serving-web-content/)  
[Spring boot 官方文档](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/)  
[Github 登录文档](https://developer.github.com/apps/building-github-apps/creating-a-github-app/)  
[Bootstrap 文档](https://v3.bootcss.com/components/)  
[okHttp 文档](https://square.github.io/okhttp/)  
[mybatis 文档](http://mybatis.org/spring/)  
[lombok 官网](https://projectlombok.org/)  
[thymeleaf 官方文档](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)  
[spring framework > web servlet 官方文档](https://docs.spring.io/spring/docs/5.2.4.RELEASE/spring-framework-reference/web.html#spring-web)

## 脚本

```sql
create database community;
use community;
create table user
(
	id int primary key auto_increment,
	account_id varchar(100),
	name varchar(50),
	token char(36),
	gmt_create bigint,
	gmt_modified bigint
);
alter table user
	add avatar_url varchar(100);

create table question(
    id BIGINT primary key auto_increment,
    creator_id int,
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