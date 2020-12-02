---
title: boot中netty使用
date: 2017-03-30 09:03:24
tags:
- spring boot
- netty
---

#### 简述
> 1）本质：JBoss做的一个Jar包
2）目的：快速开发高性能、高可靠性的网络服务器和客户端程序
3）优点：提供异步的、事件驱动的网络应用程序框架和工具
通俗的说：一个好使的处理Socket的东东

#### 核心
> Netty的核心就是对Java底层实现NIO的API的封装，引入了处理链的概念。Netty的Pipeline和TCP/IP协议栈有很多的相似之处，数据的每一层传输都会根据特定的规则对数据进行包头的封装，然后在接收端反方向一层一层的解析。这也是栈的概念（FILO）.

#### Netty传输对象
> 我们可以使用Netty的处理链机制反序列化对象获取对象的信息进行相应的操作，可以使用Netty自定义的处理数据包的黏包的问题，可以在链中加入加密解密数据包的机制。处理链更像是可以自定义一种数据的传输协议。

一个不错的Demo链接：[spring-boot-sample-netty](https://gitee.com/Zalldios/spring-bucket-demo/tree/master/spring-bucket/spring-boot)

