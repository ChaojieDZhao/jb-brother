---
title: java远程调试
date: 2018-06-15 09:29:27
tags:
- java
---

> 做了一个线下的产品，在生产端总是会出现各种问题，解决的时候不能一直都需要搭建一个相同的环境去模拟。记录一下之前用过的远程调试。

## 本地idea添加一个Remote单元，配置。
![idea-remote-java-debug](http://alldios-image.test.upcdn.net/note/idea-remote-java-debug.png)
参数含义如下
- transport :调试程序和VM之间的通信方式， dt_socket 表示用套接字传输 
- server=y : 表示是监听其他debug client端的请求 
- suspend : 表示是否在调试客户端建立连接之后启动 VM。 
> + 如果为y，那么当前的VM就是suspend直到有debug client连接进来才开始执行程序 
> + 如果为n，那么当前的VM就会直接执行，不会等待debug client连接进来 
- address=8778 : 表示端口是8778

## 远程服务添加代理
catalina.sh添加
``` shell
JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,address=8778,suspend=n,server=y"
```
如图所示：   
![remote-server-java-config](http://alldios-image.test.upcdn.net/note/remote-server-java-config.png)

> 保持本地代码和远端代码一致，在本地代码端打断点，远端执行到指定代码时，便会跳到断点处。
