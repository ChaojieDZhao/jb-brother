---
title: boot中shiro应用
date: 2017-06-22 15:59:27
tags:
- shiro
- spring boot
---

## Shiro简介
`Apache Shiro`是Java的一个强大和易于使用的java安全框架进行认证、授权、加密、和会话管理。Shiro的通俗易懂的API，你可以轻松快速安全的任何应用程序–从最小到最大的Web和移动应用程序的企业应用程序。目前，使用`Apache Shiro`的人越来越多，对比`Spring Security`，可能没有`Spring Security`做的功能强大，但是在实际工作时可能并不需要那么复杂的东西，所以使用小而简单的`Shiro`就足够了，并且`Spring Security`也比`Shiro`更具有侵入性。对于它俩到底哪个好，这个不必纠结，能更简单的解决项目问题就好了。

## Shiro的运行机制
Authentication：身份认证/登录，验证用户是不是拥有相应的身份；
Authorization：权限验证，验证某个已认证的用户是否拥有某个权限；即判断用户是否能做事情，常见的如：验证某个用户是否拥有某个角色。或者细粒度的验证某个用户对某个资源是否具有某个权限；
Cryptography：加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储；
Session Manager：会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中；会话可以是普通JavaSE环境的，也可以是如Web环境的；
Web Integration：Web支持，可以非常容易的集成到Web环境，简化了基于简单的URL模式匹配和过滤器链定义来确保Web应用程序的安全性。
Caching：缓存，比如用户登录后，其用户信息、拥有的角色/权限不必每次去查，这样可以提高效率；


一个不错的Demo链接：[spring-boot-sample-shiro](https://gitee.com/Zalldios/spring-bucket-demo/tree/master/spring-bucket/spring-boot)
