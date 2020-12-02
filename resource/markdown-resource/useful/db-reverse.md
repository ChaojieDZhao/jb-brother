---
title: database在PD中的逆向
date: 2016-08-22 10:44:20
tags:
- database
---

### 为什么要数据库建模？
> 建模可以直接生成创建数据库的sql代码，或者让建模工具和数据库建立连接，这样就可以随时通过更改实体及他们之间的关系来直接更改数据库结构了。而传统的使用word的方式，你必需在建立数据库时，把字段名称和类型重新再敲上一遍，而且为了保证这个过程建立的数据库和原来你用word设计的数据库结构的一致性，你必需付出额外的劳动。比如从sqlserver换成了oracle，恐怕花费的精力就更多了。而数据库建模工具就没有这个缺点，应为它是和数据库平台无关的，所以可以简单的移植到不同的数据库平台。 

### oracle数据库逆向（本地没有安装oracle数据库）
> 需要安装oracleODBC驱动程序，比你安装客户端要小很多。
32位客户端[链接地址](http://www.oracle.com/technetwork/topics/winsoft-085727.html)同PowerDesigner配对
64位客户端[链接地址](http://www.oracle.com/technetwork/topics/winx64soft-089540.html)同PowerDesigner配对
1 同目录解e压Instant Client Basic所有文件运行的基础
                                2 同目录解压Instant Client ODBC程序
                                3 点击install.exe程序，就已经成功安装了ODBC程序。
                                4 C:\Windows\System32\odbcad32.xe添加驱动程序源

> PowerDesinger的file > reverse engineer > database >配置就可以完成

**oracle转mysql的方案**
使用PowerDesigner固然可以，不过算是比较麻烦的。
Navicat组织退出了一款可以转换数据库结构的可以去看看。


