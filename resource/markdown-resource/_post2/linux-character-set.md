---
title: linux中的字符集
date: 2016-08-30 09:30:16
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


### linux shell通配符(wildcard)
>通配符是由shell处理的（我们在shell各个命令帮助中也没有发现有这些通配符介绍）, 它只会出现在 命令的“参数”里（它不用在命令名称里， 也不用在操作符上）。当shell在“参数”中遇到了通配符时，shell会将其当作路径或文件名去在磁盘上搜寻可能的匹配：若符合要求的匹配存在，则进行代换(路径扩展)；否则就将该通配符作为一个普通字符传递给“命令”，然后再由命令进行处理。总之，通配符 实际上就是一种shell实现的路径扩展功能。在 通配符被处理后, shell会先完成该命令的重组，然后再继续处理重组后的命令，直至执行该命令。

>例如，ls *.txt 实际shell搜索文件,找到了符合条件的文件，命令会变成：ls a.txt b.txt ,实际在执行ls 时候传给它的是a.txt b.txt。

|字符|	含义|	实例|
|---|---|---|
|*|	匹配 0 或多个字符	a*b|  a与b之间可以有任意长度的任意字符, 也可以一个也没有, 如aabcb, axyzb, a012b, ab。|
|?|匹配任意一个字符	a?b|  a与b之间必须也只能有一个字符, 可以是任意字符, 如aab, abb, acb, a0b。
|[list]| 匹配 list 中的任意单一字符	a[xyz]b |  a与b之间必须也只能有一个字符, 但只能是 x 或 y 或 z, 如: axb, ayb, azb。
|[!list]| 匹配 除list 中的任意单一字符|	a[!0-9]b  a与b之间必须也只能有一个字符, 但不能是阿拉伯数字, 如axb, aab, a-b。
|[c1-c2]|匹配 c1-c2 中的任意单一字符 如：[0-9] [a-z]|	a[0-9]b  0与9之间必须也只能有一个字符 如a0b, a1b... a9b。
|{string1,string2,...}|	匹配 sring1 或 string2 (或更多)其一字符串|	a{abc,xyz,123}b，a与b之间只能是abc或xyz或123这三个字符串之一。|

**需要说明的是：通配符看起来有点象正则表达式语句，但是它与正则表达式不同的，不能相互混淆。把通配符理解为shell 特殊代号字符就可。而且涉及的只有，*,? [] ,{} 这几种。**
有时候，我们想让 通配符，或者元字符 变成普通字符，不需要使用它。那么这里我们就需要用到转义符了。 shell提供转义符有三种。

### Linux元字符
**元字符是shell编程最重要的组成部分，并且内容也比较多，开篇记录。**

### Linux转义字符
有时候，我们想让 通配符，或者元字符 变成普通字符，不需要使用它。那么这里我们就需要用到转义符了。 shell提供转义符有三种。


|字符|	含义|	实例|
|---|---|---|
|‘’(单引号)|又叫硬转义，其内部所有的shell 元字符、通配符都会被关掉。注意，硬转义中不允许出现’(单引号)。|ls '*txt' 匹配不了testtxt文件或者 echo '$PATH'|
|“”(双引号)|又叫软转义，其内部只允许出现特定的shell 元字符：$用于参数代换 `用于命令代替|echo "$PATH"|
|\\ (反斜杠)|	 又叫转义，去除其后紧跟的元字符或通配符的特殊意义。|ls  \\*.txt 无法匹配|

