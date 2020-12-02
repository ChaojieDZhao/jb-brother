---
title: mybatisGenerator的使用
date: 2016-06-15 20:48:16
tags:
- java
---

mybatis generator是很好用的mybatis技术相关的自动代码生成工具。每次建立一张表然后手动写入一个实体类和mapper接口文件还有xml配置文件感觉会很麻烦，使用mybatis generator只需要简单的配置就能完成我们的工作。

#### 展示一个oracle数据库中用到的分页xml
```  xml
<sql id="OracleDialectPrefix" >
<if test="page != null" >
  select * from ( select row_.*, rownum rownum_ from ( 
</if>
</sql>
<sql id="OracleDialectSuffix" >
<if test="page != null" >
  <![CDATA[ ) row_ ) where rownum_ > #{page.begin} and rownum_ <= #{page.end} ]]>
</if>
</sql>
```

一个不错的demo地址：  [mybatis-3-generator](https://gitee.com/Zalldios/mybatis-3-generator)