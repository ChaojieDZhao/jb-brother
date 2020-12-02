---
title: 同步之中的信号量机制
date: 2016-12-19 13:58:23
tags:
- sync
---
###  前言
> 为实现进程互斥进入自己临界区，所有同步机制应该遵循下述四条原则
空闲让进：当无进程进入临界区，表明临界资源正处于空闲状态，应允许一个请求进入临界区的进程立即进入临界区。  
忙则等待：当已有进程进入了临界区，表明临界区资源正在被访问，因而其他试图进入临界区的进程必须等待。
有限等待：对要求访问临界区资源的进程，应保证在有限时间内进入自己的临界区，以免进入`死等`状态。
让权等待：当进程不能使用自己的临界区时，应立即释放处理机，以免进程进入`忙等`的状态。

### 整型信号量
定义为一个表示资源数目的整形量S，与一般整形量不同，他只能够通过两个标准的原子操作wait(S)，和signal(S)进行访问。
``` c++
wait(S):   while S<=0 do no-operation #循环判断如果资源已经被耗尽，则什么都不做。
           S:=S-1 #如果还有资源，则进行信号量 减一 操作。
signal(S): S:=S+1 #如果使用完毕，则进行信号量 加一 操作。
```

### 记录型信号量
我们可以观察到在整型信号量中，出现了一直循环的现象，并没有遵循让权等待的原则。记录型信号量，使用了一个记录型的数据结构。
``` c++
type semaphore=record
    value:integer #资源的数量值
    L:list of process #进程的等待队列
    end
procedure wait(S) #请求操作
	var S:semaphore;                          
	begin
	  S.value:=S.value-1; #进行 减一 操作
	  if S.value<0 then block(S.L); #如果资源已经小于零，则阻塞，并且添加到等待队列。则是S的绝对值代表了阻塞队列的长度。
	end
procedure signal(S) #释放操作
	var S:semaphore;
	begin
	  S.value:=S.value+1; #进行 加一 操作。
	  if S.value<=0 then wakeup(S.L); #如果等待队列中还有等待进程，则唤醒一个等待队列的线程继续执行。
    end
```

### AND型信号量
如果一个进程需要一次性同时访问多个资源的话，上述的两种信号量都不可以解决。AND信号量的核心就是要么一次性全部分配，要么一个都不分配。那么如下（simultaneous）：
``` c++
Swait(S1,S2,S3...Sn)
   if(S1>=1&S2>=1&S3>=1&S4>=1) then
     for i:=1 to n do
     Si=Si-1;
     endfor
   else
   如果第一个Si资源不足，将进程放置在Si相关联的等待队列，继续执行Swait操作。
   endif
Ssingal(S1,S2,S3...Sn)
  for i:=1 to n do
  Si:=Si+1;
  移交所有与Si相关的等待队列的进程到就绪队列。
  endfor;
```

### 信号量集
上述所有的信号量机制都是对信号量进行加一或者减一的操作，意味着每次只能获得和释放一个单位的临界资源。如果需要N个某类的临界资源时候，需要进行N此的wait(S)操作。这是比较低效的，并且当资源的某一数量小于某值的情况后便不予以分配。因为在每次分配之前都需要测试数量是否足够。
那么我们假设 S为信号量，d为需求值，t为下限值。
``` c++
Swait(S1,t1,d1,...Sn,tn,dn)
   if(S1>=t1 & Sn>=tn) then
     for i:=1 to n do
     Si=Si-di;
     endfor
   else
   如果第一个Si资源不足，将进程放置在Si相关联的等待队列，继续执行Swait操作。
   endif
Ssingal(S1,t1,d1,...Sn,tn,dn)
  for i:=1 to n do
  Si:=Si+di;
  移交所有与Si相关的等待队列的进程到就绪队列。
  endfor;
```
