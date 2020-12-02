### "2>&1"含义

```
 ls -l  > /dev/null 2>&1 &  
```
> 对于&1 更准确的说应该是文件描述符1,而1标识标准输出，stdout。   
> 对于2，表示标准错误，stderr。   
> 2>&1 的意思就是将标准错误重定向到标准输出。这里标准输出已经重定向到了 /dev/null。那么标准错误也会输出到/dev/null。
> 可以把/dev/null 可以看作"黑洞". 它等价于一个只写文件. 所有写入它的内容都会永远丢失. 而尝试从它那儿读取内容则什么也读不到。   
> ***偶尔也可以把 & 在命令的最后加上，表示让程序后台执行。***   


## less的使用

跳到文本的最后一行：按`G`,即`shift+g`       
跳到文本的开头：即`gg`          
跳转查看：`ctrl+ E/Y/F/B`  

`?` 反向查询
`/` 正向查询

`N` 反向查找
`n` 正向查找

`-N` 使用行号

`20z`设置每一个窗口20行

`shift+r` 重新油漆

`F`一直刷新，就行`tail -f`

### wget下载java
```shelll
wget --no-cookies --no-check-   certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "https://download.oracle.com/otn-pub/java/jdk/8u201-b09/42970487e3af4f5aa5bca3f542482c60/jdk-8u201-linux-x64.rpm"
```

### 查看服务启动文件
```shell 
systemctl show --property=FragmentPath docker 
```

### 运维常用命令
``` cmd
cat nohup.out | nl | less    #查看上下翻页且显示行号 
file nohup.out    #检查文件类型
cat /root/.bash_history    #查看历史登录用户
echo 2 > /proc/sys/vm/drop_caches    #清除缓存
tar -jxvf  /phantomjs-2.1.1-linux-x86_64.tar.bz2    #解压bz2结尾的文件
tar -zxvf  /phantomjs-2.1.1-linux-x86_64.tar.gz    #解压gz结尾的文件
tar -czvf ***.tar.gz  folder    #压缩gz结尾的文件
tar -cjvf ***.tar.bz2  folder    #压缩bz2结尾的文件
getconf LONG_BIT    #查看系统位数
lsb_release -a    #查看系统信息
cat /etc/redhat-release
wc -l warn.log    #统计一个文件有多少行
less -n warn.log    #显示行号展示
ls -lt |head -100    #按照时间排序查看前一百行
more +3 test.log    #从第三行开始展示
ifconfig | grep -Eo 'inet (addr:)?([0-9]*\.){3}[0-9]*' | grep -Eo '([0-9]*\.){3}[0-9]*' | grep -v '127.0.0.1' | head -n 1    #快速查询IP
sudo du -sh local/kafka/logs/*
kill -9 `ps -aux | grep apt-get | awk '{print $2}'`    #杀死一大堆进程
rpm -qa | grep 'container-selinux'
```

## 替换源

```cmd 
# wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
# sed -i  's/$releasever/7/g' /etc/yum.repos.d/CentOS-Base.repo
# yum clean all
# yum list
```

## 查看服务器版本命令

```cmd
cat /etc/issue  #查看所有发布信息
getconf LONG_BIT    #查看系统位数
lsb_release -a    #查看系统信息
cat /etc/redhat-release    #查看redhat系统版本
uname -a    #查看内核/操作系统/CPU信息
cat /proc/cpuinfo    #查看CPU信息
cat /proc/meminfo |grep MemTotal    #查看内存信息 
uname -m    #显示机器的处理器架构(2) 
uname -r    #显示正在使用的内核版本 
dmidecode -q    #显示硬件系统部件 - (SMBIOS / DMI) 
cat /proc/cpuinfo    #显示CPU info的信息 
cat /proc/interrupts    #显示中断 
cat /proc/meminfo    #校验内存使用 
cat /proc/swaps    #显示哪些swap被使用 
cat /proc/version    #显示内核的版本 
cat /proc/net/dev    #显示网络适配器及统计 
cat /proc/mounts    #显示已加载的文件系统 
lspci -tv    #罗列 PCI 设备 
lsusb -tv    #显示 USB 设备 
date    #显示系统日期 
```