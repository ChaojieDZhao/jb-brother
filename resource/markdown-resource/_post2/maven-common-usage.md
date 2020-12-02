---
title: maven常用插件技能
date: 2016-07-20 09:49:50
tags:
- maven
---

## 配置文件优先级
> pom.xml> user settings > global settings

## maven-surefire-plugin
在我们的maven项目中，一定有一些测试用例报异常的情况，但是这会引起项目build失败，怎么样解决这个问题，`maven-surefire-plugin`无疑是最佳的选择。
下面看一个用例配置：
``` xml
<project>
  [...]
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.20.1</version>
        <configuration>
          <excludes>
            <exclude>**/TestCircle.java</exclude>
            <exclude>**/TestSquare.java</exclude>
          </excludes>
        </configuration>
      </plugin>
    </plugins>
  </build>
  [...]
</project>
```
我们只需要在项目中的配置文件中配置更改exclude属性即可，支持regex表达式
相关链接：[常用配置](http://maven.apache.org/surefire/maven-surefire-plugin/usage.html)|[实例](http://maven.apache.org/surefire/maven-surefire-plugin/examples)

## maven-compiler-plugin
不同的java版本编译器汇会产生不同的效果，例如代码中要是使用上了jdk1.8的新特性，但是maven在编译的时候使用的是jdk1.7的版本，那这一段代码是完全不可能编译成.class文件的。`maven-compiler-plugin`就是为了处理这一种情况的出现。
下面看一个用例配置：
``` xml
<project>
  [...]
  <build>
    [...]
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.7.0</version>
        <configuration>
          <verbose>true</verbose>
          <fork>true</fork>
          <executable><!-- path-to-javac --></executable>
          <compilerVersion>1.8</compilerVersion>
        </configuration>
      </plugin>
    </plugins>
    [...]
  </build>
  [...]
</project>
```
相关链接：[常用配置](http://maven.apache.org/surefire/maven-compiler-plugin/usage.html)|[实例](http://maven.apache.org/surefire/maven-compiler-plugin/examples)

