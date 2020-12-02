---
title: linux权限管理
date: 2016-09-20 10:44:20
tags:
- linux
---

## 用户和用户组
### groupadd -g 122  alldios
添加一个组ID为502标识的组

### useradd -u 122 -g alldios -d /home/alldios -s /bin/bash alldios

useradd -u 122 -g alldios -d /home/alldios    #允许登录
-s /sbin/nologin alldios    #如果加上只有用户，不允许登录

此命令创建了一个用户alldios，
其中-d和-m选项用来为登录名alldios产生一个主目录/home/alldios（/home为默认的用户主目录所在的父目录）。
-u指定了用户id，-g指定用户组。

```shell
passwd alldios
mkdir /home/alldios
chown -R alldios:alldios /home/alldios
```

## 用户管理文件讲解
/etc/passwd(可以查看所有用户) 行讲解
```  shell
alldios:x:1000:1000::/home/alldios:/bin/bash
第一字段：用户名（也被称为登录名）
第二字段：口令；在例子中我们看到的是一个x，其实密码已被映射到/etc/shadow 文件中
第三字段：UID ；用户code
第四字段：GID；用户组code
第五字段：用户名全称，这是可选的，可以不设置
第六字段：用户的家目录所在位置；alldios 这个用户是/home/alldios
第七字段：用户所用SHELL 的类型，alldios用的是 bash ；所以设置为/bin/bash 
```

/etc/shadow(可以查看所有用户) 行讲解
```  shell
alldios:$6$qEb05whs$VrIE0d4WZk5UpM7DJ7x7mt8jHrDHJX8RetqUM9K5SQARgKCREhIp/eT.4FeNFcF6ClYlLdG9KGoMx3XKIfVG80:17449:0:99999:7:::
第一字段：用户名（也被称为登录名），在/etc/shadow中，用户名和/etc/passwd 是相同的，这样就把passwd 和shadow中用的用户记录联系在一起；这个字段是非空的
第二字段：密码（已被加密），如果是有些用户在这段是x，表示这个用户不能登录到系统；这个字段是非空的
第三字段：上次修改口令的时间；这个时间是从1970年01月01日算起到最近一次修改口令的时间间隔（天数），您可以通过passwd 来修改用户的密码，然后查看/etc/shadow中此字段的变化
第四字段：两次修改口令间隔最少的天数；如果设置为0,则禁用此功能；也就是说用户必须经过多少天才能修改其口令；此项功能用处不是太大；默认值是通过/etc/login.defs文件定义中获取，PASS_MIN_DAYS 中有定义
第五字段：两次修改口令间隔最多的天数；这个能增强管理员管理用户口令的时效性，应该说在增强了系统的安全性；如果是系统默认值，是在添加用户时由/etc/login.defs文件定义中获取，在PASS_MAX_DAYS 中定义
第六字段：提前多少天警告用户口令将过期；当用户登录系统后，系统登录程序提醒用户口令将要作废；如果是系统默认值，是在添加用户时由/etc/login.defs文件定义中获取，在PASS_WARN_AGE 中定义
第七字段：在口令过期之后多少天禁用此用户；此字段表示用户口令作废多少天后，系统会禁用此用户，也就是说系统会不能再让此用户登录，也不会提示用户过期，是完全禁用
第八字段：用户过期日期；此字段指定了用户作废的天数（从1970年的1月1日开始的天数），如果这个字段的值为空，帐号永久可用
第九字段：保留字段，目前为空，以备将来Linux发展之用；
```

/etc/group(可以查看所有用户组) 行讲解
```  shell
/etc/group 的内容包括用户组（Group）、用户组口令、GID及该用户组所包含的用户（User），每个用户组一条记录；格式如下：
group_name:passwd:GID:user_list
 在/etc/group 中的每条记录分四个字段：
第一字段：用户组名称；
第二字段：用户组密码；
第三字段：GID
第四字段：用户列表，每个用户之间用,号分割；本字段可以为空；如果字段为空表示用户组为GID的用户名；
```

/etc/gshadow 行讲解
```  shell
/etc/gshadow 的内容包括用户组（Group）、用户组口令、GID及该用户组所包含的用户（User），每个用户组一条记录；格式如下：
alldios:$6$qEb05whs$VrIE0d4WZk5UpM7DJ7x7mt8jHrDHJX8RetqUM9K5SQARgKCREhIp/eT.4FeNFcF6ClYlLdG9KGoMx3XKIfVG80:17449:0:99999:7:::
 在/etc/gshadow 中的每条记录分四个字段：
第一字段：用户组
第二字段：用户组密码，这个段可以是空的或!，如果是空的或有!，表示没有密码；
第三字段：用户组管理者，这个字段也可为空，如果有多个用户组管理者，用,号分割；
第四字段：组成员，如果有多个成员，用,号分割；
```
    
## 文件权限
在linux中的每个用户必须属于一个组，不能独立于组外。在linux中每个文件有所有者、所在组、其它组的概念
- 所有者
- 所在组
- 其它组
- 改变用户所在的组
其它组
> 除开文件的所有者和所在组的用户外，系统的其它用户都是文件的其它组
```  shell
ls -l 中显示的内容如下：
rwxrw-r‐-1 root root 1213 Feb 2 09:39 abc
- 10个字符确定不同用户能对文件干什么
- 第一个字符代表文件（-）、目录（d），链接（l）
- 其余字符每3个一组（rwx），读（r）、写（w）、执行（x）
- 第一组rwx：文件所有者的权限是读、写和执行
- 第二组rw-：与文件所有者同一组的用户的权限是读、写但不能执行
- 第三组r--：不与文件所有者同组的其他用户的权限是读不能写和执行
- 也可用数字表示为：r=4，w=2，x=1  因此rwx=4+2+1=7
- 1 表示连接的文件数
- root 表示用户
- root表示用户所在的组
- 1213 表示文件大小（字节）
- Feb 2 09:39 表示最后修改日期
- abc 表示文件名
 ```
 
### 改变权限的命令
>  chmod 改变文件或目录的权限
chmod 755 abc：赋予abc权限rwxr-xr-x
chmod u=rwx，g=rx，o=rx abc：同上u=用户权限，g=组权限，o=不同组其他用户权限
chmod u-x，g+w abc：给abc去除用户执行的权限，增加组写的权限
chmod a+r abc：给所有用户添加读的权限

### 改变所有者（chown）和用户组（chgrp）命令
> chown xiaoming abc：改变abc目录的所有者为xiaoming
chgrp root abc：改变abc目录所属的组为root
chown root ./abc：改变abc这个目录的所有者是root
chown ‐R root ./abc：改变abc这个目录及其下面所有的文件和目录的所有者是root
sudo usermod -a -G root nginx：添加用户组权限
groups nginx：查看用户组

### 改变用户所在组
> 在添加用户时，可以指定将该用户添加到哪个组中，同样用root的管理权限可以改变某个用户所在的组
usermod ‐g 组名 用户名
你可以用
usermod ‐d 目录名 用户名，改变该用户登录的初始目录