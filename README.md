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
	ID INTEGER default NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_819E9CB8_C3FE_4A25_BDCE_A887060C24C7" auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	constraint USER_PK
		primary key (ID)
);


```