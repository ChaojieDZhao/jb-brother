---
title: firewall-cmd命令记录
date: 2016-09-23 19:33:19
tags:
- linux
---

## 操作端口
``` shell
firewall-cmd --zone=public --add-port=80/tcp --permanent    #开放80 端口
firewall-cmd --zone=public --remove-port=80/tcp --permanent    #移除80 端口   
命令含义：  
--zone    #作用域    没有此参数则为添加所有区域的端口
--add-port=80/tcp    #添加端口，格式为：端口/通讯协议  
--permanent    #永久生效，没有此参数重启后失效
```

## 操作服务
``` shell
firewall-cmd --zone=public --add-service=http --permanent    #开放http服务
firewall-cmd --zone=public --remove-service=http --permanent    #移除http服务   
命令含义：  
--zone    #作用域    没有此参数则为添加所有区域的服务
--add-service=http    #添加服务  
--permanent    #永久生效，没有此参数重启后失效
```

## 查看状态
``` shell
1,查看 firewall  状态 :  
$ firewall-cmd --state    # eg :running  
2,查看 firewall 版本 :   
$ firewall-cmd --version #eg : 0.4.3.2  
```

## 其他内容
``` shell
firewall-cmd --list-all-zones    #查看所有的zone信息
firewall-cmd --get-default-zone    #查看默认zone是哪一个
firewall-cmd --get-active-zones    #查看已经被激活的zone
firewall-cmd --zone=internal --change-zone=p3p1    #临时修改接口p3p1所属的zone为internal
firewall-cmd --add-service=http    #暂时开放http
firewall-cmd --permanent --add-service=http    #永久开放http
firewall-cmd --zone=public --add-port=80/tcp --permanent    #在public中永久开放80端口
firewall-cmd --permanent --zone=public --remove-service=ssh    #从public zone中移除服务
firewall-cmd --reload    #重新加载配置
systemctl start firewalld.service  systemctl stop firewalld.service    #启动和停止服务
```