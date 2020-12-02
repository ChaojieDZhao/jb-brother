---
title: linux常用命令
date: 2016-09-17 19:27:29
tags:
- linux
---

## Linux常用的查询命令

## find  path expression  命令

```shell
1.按照文件名查找
find / -name httpd.conf　　#在根目录下查找文件httpd.conf，表示在整个硬盘查找
find /etc -name '*srm*'　　#使用通配符*(0或者任意多个)。表示在/etc目录下查找文件名中含有字符串‘srm’的文件
find . -name 'srm*' 　　#表示当前目录下查找文件名开头是字符串‘srm’的文件  

2.按照文件特征查找 　　　　
find / -amin -10 　　# 查找在系统中最后10分钟访问的文件(access time)  
find / -atime -2　　 # 查找在系统中最后48小时访问的文件  
find / -empty 　　# 查找在系统中为空的文件或者文件夹  
find / -group cat 　　# 查找在系统中属于 group为cat的文件  
find / -mmin -5 　　# 查找在系统中最后5分钟里修改过的文件(modify time)
find / -mtime -1 　　#查找在系统中最后24小时里修改过的文件
find / -user fred 　　#查找在系统中属于fred这个用户的文件
find / -size +10000c　　#查找出大于10000000字节的文件(c:字节，w:双字，k:KB，M:MB，G:GB)
find / -size -1000k 　　#查找出小于1000KB的文件

3.使用混合查找方式查找文件，参数有： ！，-and(-a)，-or(-o)。
find /tmp -size +10000c -and -mtime +2 　　#在/tmp目录下查找大于10000字节并在最后2分钟之前修改的文件
find / -user fred -or -user george 　　#在/目录下查找用户是fred或者george的文件文件
find /tmp ! -user panda　　#在/tmp目录中查找所有不属于panda用户的文件
find -type f -name '*.conf'|xargs grep 'web_performance_filename-based_cache_busting'     #查找问题
```

## grep options pattern  命令

```shell
grep 'test' d*　　#显示所有以d开头的文件中包含 test的行  
grep 'test' aa bb cc 　　 #显示在aa，bb，cc文件中包含test的行
grep '[a-z]\{5\}' aa 　　#显示所有包含每行字符串至少有5个连续小写字符的字符串的行
grep magic /usr/src　　#显示/usr/src目录下的文件(不含子目录)包含magic的行
grep -r magic /usr/src　　#显示/usr/src目录下的文件(包含子目录)包含magic的行
grep -E 'aa|bb'    #显示有aa的行，或者bb的行
grep -i -n '2019-03-21 11:07:55.380' warn.log    #显示行号 
```

## netstat options expression  命令

```shell
netstat -nat |awk '{print $6}'|sort|uniq -c|sort -rn    #统计套接字状态和数据信息
netstat -n | awk '/^tcp/ {++S[$NF]};END {for(a in S) print a, S[a]}'     #统计TCP套接字链接数
netstat -anlp|grep 80|grep tcp|awk '{print $5}'|awk -F: '{print $1}'|sort|uniq -c|sort -nr|head -n 20    #查找套接字前20个IP排名
```

## linux环境变量指令

### 概述

```
env    #查看全局环境变量
set    #查看所有环境变量   
echo $PATH or export $PATH    #查看单个环境变量    
unset $PATH    #只针对当前会话   

/etc/profile    #全局配置文件
~/.bash_profile    #局部配置文件
```

### 修改环境变量

```
1、用命令直接修改但只能在当前会话生效
export JAVA_HOME=/usr/local/java    #添加新变量名
export PATH=$PATH:/usr/local/java/bin #修改已有变量名
       
2、修改全局配置文件 
``` shell   
vim /etc/profile
export PATH=$PATH:/usr/local/java/bin    #添加至文档最后
source /etc/profile

3、修改个人环境变量配置文件
vim ~/.bash_profile 
export PATH=$PATH:/usr/local/java/bin    #添加至文档最后
source ~/.bash_profile
```


