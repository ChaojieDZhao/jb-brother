---
title: 服务器隐忧排查
date: 2018-01-16 14:17:53
tags:
- java
---

# linux
## 查询耗费进程的pid
top -H

## 查询该进程的线程占用情况
top -H -p $pid
或者top -p $pid,shift + h 线程按照CPU排序。

## 查询线程状态信息
jstack $pid |grep $tid(需要转换为十六进制) -A 30

## jstack命令记录
jstack -l $pid
jstack -l -m $pid #to print both java and native frames (mixed mode)，打印java和本地帧。如果调用动态库的话 一定要带上该参数。

### ThreadDump文件
jstack -l $pid | tee -a $file-path

## 直接转换十六进制
echo 'obase=16; $pid' | bc 或者  printf “%x\n” $pid

## 显示内存模型信息
jmap -histo:live $pid | head -n 100

## 统计打印出来的Thread_Info
grep java.lang.Thread.State abnormalThreadInfo.txt | awk '{print $2$3$4$5}' | sort | uniq -c

# windows
## 查看当前Java的进程信息
jps -l

## 查看当前存活的对象
jmap -histo:live $pid

## 打印当前的堆信息
jmap –heap $pid

# jstat命令指南
## 类加载统计 
jstat -class $pid   
> Loaded:加载class的数量   
Bytes：所占用空间大小   
Unloaded：未加载数量   
Bytes:未加载占用空间   
Time：时间 

## 类编译统计 
jstat -compiler 6988  
> Compiled：编译数量。
Failed：失败数量
Invalid：不可用数量
Time：时间
FailedType：失败类型
FailedMethod：失败的方法 

## 总结统计
jstat -gcutil $pid
[jvm-各分区定义](https://www.cnblogs.com/yjd_hycf_space/p/7755633.html)

# jinfo命令指南
## 查看某进程的参数信息
jinfo -flags $pid

## 筛选某进程的某一参数信息
jinfo -flag MaxHeapSize $pid    #此参数已经在JAVA8中过期了，转换为了metaspace。

> jinfo还可以直接更改参数的值，使用在生产情况。



