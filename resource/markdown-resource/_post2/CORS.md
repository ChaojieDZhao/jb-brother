---
title: 跨源请求
date: 2019-08-16 14:17:53
tags:
- http
---

> `Cross-Origin Resource Sharing`（跨源共享）允许web客户端向不同源的服务器发出`http`请求，跨源是一种web技术，因为它同时包含客户端和服务端的组件。
服务端组件控制那些类型的跨源请求被允许，而客户端组件控制如何发出跨源请求。

## 什么是`origin`（源）
`origin` 定义了客户端你的来源。比如`http://localhost:1111/client.html`的`origin`就是`http://localhost:1111`。换句话说除了最后路径部分， 就叫做`origin`。
可以看出`origin`有几部分组成，协议+主机+端口，**null除外**。如下所示的几种`url`和它对应的`origin`：

| URL                                    | ORIGIN                 |
|----------------------------------------|------------------------|
| http://localhost:1111                  | http://localhost:1111  |
| https://localhost:1111/client.html     | https://localhost:1111 |
| file:///Users/hossain/ch02/client.html | null                   |

代表`origin`的`header`值不需要我们手动设置，浏览器会自动识别请求是否是一个跨域请求，并且帮助设置`origin`。出于安全考虑，人工重写`origin`的值是无效的。
值得注意的是，同源请求有时也会有`origin`的值，如`Chrome`和`Safari`发送非`get`请求的时候也会发送`origin`的值。在这种情况下，`origin`的值和请求服务器的`origin`是一致的。
所以判断一个请求是不是是不是跨源请求的依据不仅仅是有没有`origin`，还要确认`origin`的值和请求服务器的`origin`是否一致。

## 服务端如何允许跨域请求
服务器使用响应头`Access-Control-Allow-Origin`用来允许一个客户端的跨域请求，如果返回`header`没有该值，则视为请求失败。
`Access-Control-Allow-Origin`的值可以是一个通配符，亦可以是一个`origin`。如下所示：

Access-Control-Allow-Origin: *
Access-Control-Allow-Origin: http://localhost:1111

`Access-Control-Allow-Origin: *`代表的是任何客户端可以访问我的资源。
`Access-Control-Allow-Origin: http://localhost:1111`代表只有`origin`为`http://localhost:1111`的客户端可以访问我的资源。

该响应头的信息设置只支持`*`或者一个标准的`origin`。      
**所以，如果想要拒绝一个跨域请求，返回一个不用的`origin`，或者不返回该值即可**

> 但是很多时候单纯设置`Access-Control-Allow-Origin`是不够的，比如`DELETE`或者`PUT`在发送真正的请求之时需要先一步的询问服务端的权限。该步骤就是`preflight request`

## 预检请求（preflight request）
预检请求就是在客户端发送真实请求之前会先发送一个预检请求先询问服务端是否有权限。换句话说，先询问一下服务端浏览器是否可以发送该请求。
预检请求使用`options`方法。如果预检响应失败，则不会发送真实的请求。
 
除了预检响应可以返回允许的`origin` 之外，允许返回支持跨源请求的方法。如：
`Access-Control-Allow-Methods: HEAD, GET, POST, PUT, DELETE`

除了预检响应可以返回支持的方法，也可以返回允许的`header`。如：
```http
Preflight request: 
OPTIONS /api/posts HTTP/1.1
User-Agent: Chrome
Host: 127.0.0.1:9999
Accept: */*
Origin: http://localhost:1111
Access-Control-Request-Method: GET
Access-Control-Request-Headers: Timezone-Offset, Sample-Source

允许发送请求头后 发送真实的请求: 
GET /api/posts HTTP/1.1
User-Agent: Chrome
Host: 127.0.0.1:9999
Accept: */*
Origin: http://localhost:1111
Timezone-Offset: 300
Sample-Source: alldios
```

## 代理服务器
不同客户端访问同一个代理服务器，可能会造成一些缓存上的问题。如`http://tablet.espn.com`的客户端访问代理服务器的`API`返回`http://tablet.tencent.com`的`origin`，
而`http://mobile.tencent.com`访问相同的`API`可能会根据缓存返回相同的`origin`，如还是`http://tablet.tencent.com`。这会导致后者的请求失败。
如要需要解决这种问题，可以设置请求头`Vary: Origin`。如果设置该请求头，服务器将会视`Origin: http://mobile.tencent.com`和`Origin:http://tablet.tencent.com`为不同的请求。   























预检请求出发需要一定的条件，触发机制[参考](https://developer.mozilla.org/zh/docs/Web/HTTP/Access_control_CORS)