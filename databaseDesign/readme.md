# 数据库部署

使用 `deploy.sql` 将部署这个数据库在运行mysql的服务器上。
 
你应该需要修改
`MySQLutil`
中的部分内容如
* 服务器ip
* 数据库名称
* 用户名和登录密码
```java
static final String DB_URL = "jdbc:mysql:/123.56.18.36:3306/andorid-2020-springuseSSL=false&serverTimezone=Asia/ShanghaicharacterEncoding=utf-8";
static final String USER = "";
static final String PASS = "";
```