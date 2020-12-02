---
title: webSocket与http
date: 2017-03-16 10:40:33
tags:
- websocket
---

> 推送的实现方式：
- 客户端不断的查询服务器，检索新内容，也就是所谓的pull 或者轮询方式。
- 客户端和服务器之间维持一个TCP/IP长连接，服务器向客户端push。
- 服务器有新内容时，发送一条类似短信的信令给客户端，客户端收到后从服务器中下载新内容，也就是SMS的推送方式。


## HTTP协议
HTTP协议是用在应用层的协议，他是基于TCP协议的，HTTP协议建立链接也必须要有三次握手才能发送信息。   
HTTP链接分为短链接，长链接，短链接是每次请求都要三次握手才能发送自己的信息。即每一个request对应一个response。长链接是在一定的期限内保持链接。保持TCP连接不断开。客户端与服务器通信，必须要有客户端发起然后服务器返回结果。客户端是主动的，服务器是被动的。   
## WebSocket 
WebSocket他是为了解决客户端发起多个HTTP请求到服务器资源浏览器必须要经过长时间的轮训问题而生的，他实现了多路复用，他是全双工通信。在WebSocket协议下客服端和浏览器可以同时发送信息。   
建立了WebSocket之后服务器不必在浏览器发送request请求之后才能发送信息到浏览器。这时的服务器已有主动权想什么时候发就可以发送信息到服务器。而且信息当中不必在带有head的部分信息了与HTTP的长链接通信来说，这种方式，不仅能降低服务器的压力。而且信息当中也减少了部分多余的信息。   

在spring-boot中通过使用注解，可以很方便地创建一个WebSocket应用，主要有下面五个注解：   
<i>@ServerEndpoint</i>：定义WebSocket的地址  
<i>@OnOpen</i>：服务端和客户端建立连接时调用  
<i>@OnMessage</i>：发送数据时调用  
<i>@OnClose</i>：关闭连接时调用  
<i>@OnError</i>：出错时调用  

一个不错的demo地址：  [spring-boot-sample-websocket](https://gitee.com/Zalldios/spring-bucket-demo/tree/master/spring-bucket/spring-boot)
