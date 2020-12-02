---
title: sed命令使用
date: 2016-09-26 17:22:24
tags:
- linux
---

## sed命令格式
> `sed` 命令行格式为： `sed` [选项] `command` 输入文本
> `sed` 命令并不会改变源文件的内容。`sed` 把每一行都存在临时缓存区中，对这个副本进行编辑，所以不会修改或破坏源文件。

测试文件`testsed`内容 
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

### 命令详情
#### option
-n 使用安静(silent)模式，只列出经过sed特殊处理的那一行(或者动作)内容；   
-e 直接在指令列模式上进行 sed 的动作编辑；   
-f 直接将 sed 的动作写在一个文件内， -f filename 则可以执行filename内的sed命令；   
-r 让sed命令支持扩展的正则表达式(默认是基础正则表达式)；   
-i 直接修改读取的文件内容，而不是由屏幕输出；   

#### 命令   
a \： 即append追加字符串，可将其后的字符加在所选择内容的后面   
c \： 取代/替换字符串，可将其后内容替换至所选内容   
d  ： 即delete删除，该命令会将当前选中的行删除   
i \： 即insert插入字符串，可将其后内容插入至所选内容前   
p  ： print即打印，该命令会打印当前选择的行到屏幕上   
s  ： 替换，通常s命令的用法是这样的：1，2s/old/new/g，将old字符串替换成new字符串   

``` shell
sed '/north/p' testsed    #打印源文件内容，并打印匹配的内容
sed -n '/north/p' testsed    #只打印匹配north的行
sed -n '3d' testsed    #不打印第三行
sed -n '3,$d' testsed    #不打印第三行开始到结束行
sed -n '3,+5p' testsed    #从第三行打印到第八行
sed -n '/north/d' testsed    #不打印包含north的行
sed -n 's/west/north/g' testsed    #将west替换为north后打印
sed -n 's/^west/north/p' testsed    #将行首的west并替换成 north打印
sed -i 's/SELINUX=enforcing/SELINUX=disabled/g' /etc/selinux/config    #直接替换
sed -r 's/^.//g' nohup.out    #删除每行的第一个字符
sed 's/\(.*\)is\(.*\)null\(.*\)/\1\2/g' nohup.out    #匹配null左右两边
sed -n '/%%/!'p  rabbitmq.config |tr -s '\n'   #去除注释行和空行。
sed -n '/#/!'p  redis.conf |tr -s '\n'   #去除注释行和空行。
sed -n '/alldios/,+4p' nohup.out    #匹配含有alldios的行，并且向下扩展4行。 
nl /etc/passwd | sed '2a drink tea'     #第二行添加drink tea字符串
nl /etc/passwd | sed '2, 5c No 2-5 number'    #第2到5行替换为No 2-5 number
nl /etc/passwd | sed -e '2,5d'   #删除2到5行
```

### sed命令和awk命令合用的话效果拔群，awk常用命令如下
|字符|含义|
|---|---|
|$0|当前记录（作为单个变量)|
|$1~$n|当前记录的第n个字段，字段间由FS分隔|
|FS|输入字段分隔符默认是空格|
|NF|当前记录中的字段个数，就是有多少列|
|NR|已经读出的记录数，就是行号，从1开始|
|RS|输入的记录他隔符默认为换行符|
|OFS|输出字段分隔符默认也是空格|
|ORS|输出的记录分隔符，默认为换行符|
|ARGC|命令行参数个数|
|ARGV|命令行参数数组|
|FILENAME|当前输入文件的名字|
|IGNORECASE|如果为真，则进行忽略大小写的匹配|
|ARGIND|当前被处理文件的ARGV标志符|
|CONVFMT|数字转换格式%.6g|
|ENVIRON|UNIX环境变量|
|ERRNO|UNIX系统错误消息|
|FIELDWIDTHS|输入字段宽度的空白分隔字符串|
|FNR|当前记录数|
|OFMT|数字的输出格式%.6g|
|RSTART|被匹配函数匹配的字符串首|
|RLENGTH|被匹配函数匹配的字符串长度|
|SUBSEP|\034|

``` shell
awk -F ':' '{print $1}' nohup.out    #使用\:分隔行并且打印第一列内容。
awk -F '[()]' '{print $1, $2, $3}' nohup.out    #在-F参数中使用一对方括号来指定多个分隔符，awk 处理 nohup.out 文件时就会使用 "(" 和 ")" 来对文件的每一行进行分割。
awk 'BEGIN{FS=":"}/^root/{print $1,$NF}' /etc/passwd    #FS为字段分隔符，可以自己设置，默认是空格，因为passwd里面是”:”分隔，所以需要修改默认分隔符。
awk 'BEGIN{FS=":";OFS="^^"}/^root/{print FNR,$1,$NF}' /etc/passwd    #OFS设置默认字段分隔符
awk '/^http-nio-80-/{print $0}' nohup.out    #匹配有http-nio-80-开头的行。
awk -F ' ' '{print $1,$2,$3}' nohup.out | sed -n '1,+15p'    #匹配字符串
netstat -anlp|grep 80|grep tcp|awk '{print $5}'|awk -F: '{print $1}'|sort|uniq -c|sort -nr|head -n 20    #查看链接最多的IP
```

更多操作见[链接](https://www.cnblogs.com/ginvip/p/6376049.html)




