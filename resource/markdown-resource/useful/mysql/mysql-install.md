# 安装 & 权限

下载 MySQL Yum Repository
wget http://dev.mysql.com/get/mysql57-community-release-el7-8.noarch.rpm
添加 MySQL Yum Repository
yum localinstall -y mysql57-community-release-el7-8.noarch.rpm
 通过 Yum 来安装 MySQL
yum install -y mysql-community-server
重置 5.7 的密码很简单,方法有两种：
1、修改 /etc/my.cnf，在 [mysqld] 小节下添加一行：skip-grant-tables=1
这一行配置让 mysqld 启动时不对密码进行验证
2、重启 mysqld 服务：systemctl restart mysqld
3、使用 root 用户登录到 mysql：mysql -u root 
4、切换到mysql数据库(use mysql;)，更新 user 表：
update user set authentication_string = password('alldios123456'), password_expired = 'N', password_last_changed = now() where user = 'root';
在之前的版本中，密码字段的字段名是 password，5.7版本改为了 authentication_string
5、退出 mysql，编辑 /etc/my.cnf 文件，删除 skip-grant-tables=1 的内容
6、重启 mysqld 服务，再用新密码登录即可

=============================至此数据库安装完毕==================================

=================================================================================

启动mysql服务
systemctl start mysqld
开机启动
shell> systemctl enable mysqld
shell> systemctl daemon-reload
开启远程登录 远程访问设置

创建一个管理员用户 admin 账号 ，密码是 Alldios@123456
CREATE USER 'alldios'@'%' IDENTIFIED BY 'Alldios@123456';
给这个用户授予所有的远程访问的权限。这个用户主要用于管理整个数据库、备份、还原等操作。
GRANT  ALL PRIVILEGES  ON *.* TO 'alldios'@'%';

创建一个普通用户 sa ，密码是 some_pass
CREATE USER 'sa'@'%' IDENTIFIED BY 'some_pass';
给这个用户授予 SELECT,INSERT,UPDATE,DELETE 的远程访问的权限，这个账号一般用于提供给实施的系统访问
GRANT SELECT,INSERT,UPDATE,DELETE  ON *.* TO 'sa'@'%';

flush privileges;

修改my.cnf 添加 bind-address = 0.0.0.0  

==================================配置===========================================================

utf8编码  修改/etc/my.cnf配置文件，在[mysqld]下添加编码配置，如下所示：
[mysqld]
character_set_server=utf8
init_connect='SET NAMES utf8'
重新启动mysql服务，查看数据库默认编码如下所示：
+--------------------------+----------------------------+
| Variable_name            | Value                      |
+--------------------------+----------------------------+
| character_set_client     | utf8                       |
| character_set_connection | utf8                       |
| character_set_database   | utf8                       |
| character_set_filesystem | binary                     |
| character_set_results    | utf8                       |
| character_set_server     | utf8                       |
| character_set_system     | utf8                       |
| character_sets_dir       | /usr/share/mysql/charsets/ |
+--------------------------+----------------------------+

## 权限管理模板
CREATE USER 'passerby'@'%' IDENTIFIED BY 'passerby#$!23';
GRANT  ALL PRIVILEGES  ON elastic.* TO 'passerby'@'%';
SHOW GRANTS FOR passerby;
REVOKE ALL PRIVILEGES ON *.* FROM 'passerby'@'%'; 
DROP USER passerby

CREATE USER 'xu'@'%' IDENTIFIED BY 'Xu@123456';
GRANT  ALL PRIVILEGES  ON xiaoyan.* TO 'xu'@'%';
GRANT  ALL PRIVILEGES  ON xy_cti_music.* TO 'xu'@'%';

GRANT SELECT,INSERT,UPDATE,DELETE  ON xiaoyan.* TO 'xu'@'%';
GRANT SELECT,INSERT,UPDATE,DELETE  ON xy_cti_music.* TO 'xu'@'%';
SHOW GRANTS FOR xu;
DROP USER xu;
SELECT User, Host FROM mysql.user;
delete from user where user = "root" and host = "%";     #禁止root远程登录
update user set password=password('123') where user='root' and host='localhost';    #修改root@localhost用户密码 
set password for 用户名@localhost = password('新密码');    #修改密码
flush privileges; 


## mysql配置样例
```
[mysqld]
skip-grant-tables=1
lower_case_table_names=1
sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION
symbolic-links=0
bind-address = 0.0.0.0
log-error=/var/log/mysqld.log
pid-file=/var/run/mysqld/mysqld.pid
character_set_server=utf8
init_connect='SET NAMES utf8'
```