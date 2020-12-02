---
title: shell编程的元字符
date: 2016-08-32 09:30:16
tags:
- linux
---

测试文件`testtxt`内容 
```  shell   
northwest       NW      Charles Main    3.0     .98     3       34  
northwest       NW      Charles Main    3.0     .98     3       34  
western         WE      Sharon Gray     5.3     .97     5       23  
southwest       SW      Lewis Dalsass   2.7     .8      2       18  
southern        SO      Suan Chin       5.1     .95     4       15  
southeast       SE      Patricia Hemenway       4.0     .7      4       17  
eastern         EA      TB Savage       4.4     .84     5       20  
northeast       NE      AM Main Jr.     5.1     .94     3       13    
northeast       NE      AM Main Jr.     5.1     .94     3       13  
north           NO      Margot Weber    4.5     .89     5       9  
north           NO      Margot Weber    4.5     .89     5       9  
central         CT      Ann Stephens    5.7     .94     5       13 
```

### Linux元字符
返单引号有两种，一种是常位于左上角第二排的反单引号，也叫重音符，一种是常见的单引号。暂且把第一种叫做`反单引号`，第二种叫做`单引号`。
 
|字符|	含义|	实例|
|---|---|---|
|""|软引||
|''|硬引|---|
|<|输入重定向|---|
|>|输出重定向|---|
|>>|追加|---|
|$|参数使用|$# 是传给脚本的参数个数|
|$|参数使用|$2 是传递给该shell脚本的第二个参数|
|$|参数使用|$@ 是脚本的所有参数的列表|
|$|参数使用|$$ 是脚本运行的当前进程ID号|
|$|参数使用|$? 是显示最后命令的退出状态，0表示没有错误，其他表示有错误|
|;|在前一个命令结束时，而忽略其返回值，继续执行下一个命令|---|
|&&|在前一个命令结束时，若返回值为 true，继续执行下一个命令|---|
|&#124;&#124;|在前一个命令结束时，若返回值为 false，继续执行下一个命令|---|
|&|重导向 file descriptor ，或将命令置于背景执行|---|
