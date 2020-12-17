码问社区

在线演示地址

www.mawen.co



功能列表

开源论坛、问答系统，现有功能提问、回复、通知、最新、最热、消除零回复功能。功能持续更新中…… 

技术栈

  技术               	链接                                      
  Spring Boot      	http://projects.spring.io/spring-boot/#quick-start
  MyBatis          	https://mybatis.org/mybatis-3/zh/index.html
  MyBatis Generator	http://mybatis.org/generator/           
  H2               	http://www.h2database.com/html/main.html
  Flyway           	https://flywaydb.org/getstarted/firststeps/maven
  Lombok           	https://www.projectlombok.org           
  Bootstrap        	https://v3.bootcss.com/getting-started/ 
  Github OAuth     	https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/
  UFile            	https://github.com/ucloud/ufile-sdk-java
  Bootstrap        	https://v3.bootcss.com/getting-started/ 

在线视频

  标题                             	链接                                      
  【Spring Boot 实战】论坛项目【第一季】      	https://www.bilibili.com/video/BV1r4411r7au
  【Spring Boot 实战】热门话题【第二季】      	https://www.bilibili.com/video/BV1Z4411f7RK
  【Spring Boot 实战】接入广告流量变现【第三季】  	https://www.bilibili.com/video/BV1L4411y7J9
  【Spring Boot 实战】Vue 零基础入门【第四季】 	https://www.bilibili.com/video/BV1gE411R7YA
  【Spring Boot 实战】快速搭建免费 HTTPS 服务	https://www.bilibili.com/video/BV1oJ411K7VT

本地运行手册

1. 安装必备工具  
   JDK，Maven
2. 克隆代码到本地

    git clone https://github.com/codedrinker/community.git
    ````
    3. 运行打包命令
    ```sh
    mvn package

1. 运行项目  

    java -jar target/community-0.0.1-SNAPSHOT.jar

1. 访问项目

    http://localhost:8887

资源文件

未使用 Flyway 之前的数据库脚本

    CREATE TABLE USER
    (
        ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
        ACCOUNT_ID VARCHAR(100),
        NAME VARCHAR(50),
        TOKEN VARCHAR(36),
        GMT_CREATE BIGINT,
        GMT_MODIFIED BIGINT
    );

运行 migrate 和 generator 的命令

    mvn flyway:migrate
    mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
    java -jar -Dspring.profiles.active=production target/community-0.0.1-SNAPSHOT.jar
    sudo java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005  -jar -Dspring.profiles.active=production target/community-0.0.1-SNAPSHOT.jar(远程debug)
    ~/.m2/setting.xml(需要搜maven配置的  吧pom.xml 里面的profile配置包裹起来 maven.apache.org/settings.html)
    mvn clean compile flyway:migrate -Production(-P 配置数据里${db.user}等信息)
扩展资料

Spring 文档    

Spring Web   

es    

Github deploy key    

Bootstrap    

Github OAuth    

Spring    

菜鸟教程    

Thymeleaf    

Spring Dev Tool  

Spring MVC  

Markdown 插件   

UFfile SDK  

Count(*) VS Count(1)  

Git   

Visual Paradigm    

Flyway  

Lombok    

ctotree   

Table of content sidebar    

One Tab    

Live Reload  

Postman

更新日志

- 2019-7-30 修复 session 过期时间很短问题   
- 2019-8-2 修复因为*和+号产生的搜索异常问题  
- 2019-8-18 添加首页按照最新、最热、零回复排序  
- 2019-8-18 修复搜索输入 ? 号出现异常问题
- 2019-8-22 修复图片大小限制和提问内容为空问题
- 2019-9-1 添加动态导航栏
