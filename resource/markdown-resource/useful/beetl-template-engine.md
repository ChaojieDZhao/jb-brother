---
title: beetl模板引擎应用
date: 2017-03-09 09:29:27
tags:
- template engine
---
jsp全名是`Java Server Page`,中文名叫Java服务器页面，根本是一个简化的servlet。Jsp是在传统的网页html文件中插入Java程序段和Jsp标记，从而形成*.jsp文件。Jsp实现了java语言在html中的扩展。

`Beetl`, 是`Beetl Template Language`的缩写，它是新一代的模板引擎，功能强大，性能良好，易学易用。

首先Jsp和beetl是两种不同的东西，jsp是服务器页面，Beetl是模板引擎。他们两个使用场景是不同的，JSP是一种动态网页技术标准，等于html+java+jsp标记，在服务器端执行，返回给客户的是html文本，主要用来展示的。而Beetl主要是用来分离页面和数据的。但是它们都会生成html, 都会跟后台进行数据交互。

值得一提的是`Beetl`的性能，通过与主流模板引擎`Freemarker`，`Vecloity`以及JSP对比，Beetl6倍于`Freemarker`，2倍于JSP。这是因为宏观上，通过了优化的渲染引擎，IO的二进制输出，字节码属性访问增强，微观上，通过一维数组保存上下文Context,静态文本合并处理，通过重复使用字节数组来防止java频繁的创建和销毁数组，还使用模板缓存，运行时优化等方法。

### 引擎核心步骤

> 利用正则表达式分解出普通字符串和模板标识符，<%=%>的正则表达式为/<%=\s*([^%>]+)\s*%>/g.
将模板标识符转换成普通的语言表达式
生成待执行语句
将数据填入执行，生成最终的字符串

beetl引擎官网[链接](http://ibeetl.com/)
一个不错的demo地址[spring-boot-sample-beetl](https://gitee.com/Zalldios/spring-bucket-demo/tree/master/spring-bucket/spring-boot)

值得一提的是spring-boot中已经内集成了`thymeleaf`模板引擎，使用和配置也十分的简单。
个人而言模板引擎不止这两种，性能选择是最重要的选择方向。
目前`beetl`性能暂时高出`thymeleaf`不少。
`beetl`是国内一个大神写的，[地址](https://my.oschina.net/xiandafu/)。