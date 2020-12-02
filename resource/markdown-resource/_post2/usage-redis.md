---
title: redis使用
date: 2017-08-17 20:01:36
tags:
- redis
---

##  集群搭建
### 第一步：创建一个文件夹redis-cluster，然后在其下面分别创建6个文件夹如下：   
- mkdir -p /usr/local/redis-cluster    
- mkdir 7001、mkdir 7002、mkdir 7003、mkdir 7004、mkdir 7005、mkdir 7006   

### 第二步：把之前的redis.conf配置文件分别copy到700*修改各个文件内容，也就是对700*下的每一个copy的redis.conf文件进行修改！如下：
- daemonize yes      
- port 700*（分别对每个机器的端口号进行设置）         
- bind 192.168.131.171（必须要绑定当前机器的ip，不然会无限悲剧下去哇..深坑勿入！！！）   
- dir /usr/local/redis-cluster/700*/（指定数据文件存放位置，必须要指定不同的目录位置，不然会丢失数据，深坑勿入！！！）   
- cluster-enabled yes（启动集群模式，开始玩耍）   
- cluster-config-file nodes700*.conf（这里700x最好和port对应上）   
- cluster-node-timeout 5000   
- appendonly yes   

### 第三步：注意每个文件要修改端口号，bind的ip，数据存放的dir，并且nodes文件都需要进行修改！

### 第四步：由于redis集群需要使用ruby命令，所以我们需要安装ruby
- yum -y install gcc openssl-devel libyaml-devel libffi-devel readline-devel zlib-devel gdbm-devel ncurses-devel gcc-c++ automake autoconf  
- yum -y install ruby rubygems 
- gem install redis （安装redis和ruby的接口）

### 第五步：分别启动6个redis实例，然后检查是否启动成功
- usr/local/redis/bin/redis-server /usr/local/redis-cluster/700*/redis.conf 
- ps -el | grep redis 查看是否启动成功

### 第六步：首先到redis3.0的安装目录下，然后执行redis-trib.rb命令。
- cd /usr/local/redis3.0/src
- \./redis-trib.rb  create --replicas 1 192.168.131.171:7001 192.168.131.171:7002 192.168.131.171:7003 192.168.131.171:7004 192.168.131.171:7005 192.168.131.171:7006

### 第七步：到此为止我们集群搭建成功！进行验证：
- 连接任意一个客户端即可：./redis-cli -c -h -p （-c表示集群模式，指定ip地址和端口号）如：/usr/local/redis/bin/redis-cli -c -h 192.168.131.171 -p 700*
- 进行验证：cluster info（查看集群信息）、cluster nodes（查看节点列表）
- 进行数据操作验证
- 关闭集群则需要逐个进行关闭，使用命令：usr/local/redis/bin/redis-cli -c -h 192.168.131.171 -p 700* shutdown

### 第八步：（补充）
友情提示：当出现集群无法启动时，删除临时的数据文件，再次重新启动每一个redis服务，然后重新构造集群环境。

### 第九步：（集群操作文章）
redis-trib.rb官方群操作命令： [链接](http://redis.io/topics/cluster-tutorial)

推荐博客：[链接](http://blog.51yip.com/nosql/1726.html/comment-page-1)

### 搭建可能遇到的问题
#### ruby版本低下

升级方法：[链接](http://www.iyu.co/web/centos-install-ruby/)


## redis的特性
### 快照(rdb)
(原理:每隔n分钟或N次写入后从内存dump数据形成rdb文件压缩，放至到目录上。完成rdb快照文件的生成之后，就替换之前的旧的快照文件)
save 900 1    //900内,有1条写入,则产生快照 
save 300 1000    //如果300秒内有1000次写入,则产生快照
save 60 10000    //如果60秒内有10000次写入,则产生快照
> NOTE:这3个选项都屏蔽,则rdb禁用
> NOTE:新版本由新的进程(后台备份进程)导出.

stop-writes-on-bgsave-error yes    //后台备份进程出错时,主进程停不停止写入? (rdb的导入进程出错了，redis停止写入)
rdbcompression yes    //导出的rdb文件是否压缩
Rdbchecksum yes    //导入rbd恢复时数据时,要不要检验rdb的完整性:
dbfilename dump.rdb    //导出来的rdb文件名
dir ./    //rdb的放置路径

> NOTE: 保存条件：1分钟，5分钟，15分钟才保存，所以不在里面做的修改，断电等,故障都会丢失。
> NOTE: rdb的缺点，时间间隔内容易造成数据丢失。

### 日志(aof)
(aof原理，每执行一个命令，aof进程都会将命令记录到txt文档中，写txt会严重拖慢redis。)
appendonly no    //是否打开 aof日志功能(yes:打开)
appendfsync always    //每1个命令,都立即同步到aof. 安全,速度慢
appendfsync everysec    //折衷方案,每秒写1次
appendfsync no    //写入工作交给操作系统,由操作系统判断缓冲区大小,统一写入到aof. 同步频率低,速度快,
no-appendfsync-on-rewrite yes:    //正在导出rdb快照的过程中,要不要停止同步aof
auto-aof-rewrite-percentage 100    //aof文件大小比起上次重写时的大小,增长率100%时,重写
auto-aof-rewrite-min-size 64mb    //aof文件,至少超过64M时,重写

### aof的fsync策略
- 每次写入一条数据就执行一次fsync
> 每次写入一条数据，立即将这个数据对应的写日志fsync到磁盘上去，性能非常非常差，吞吐量很低; 确保说redis里的数据一条都不丢，那就只能这样了。

- 每隔一秒执行一次fsync
> 每秒将os cache中的数据fsync到磁盘，这个最常用的，生产环境一般都这么配置，性能很高，QPS还是可以上万的。

- 不主动执行fsync
> 仅仅redis负责将数据写入os cache就撒手不管了，然后后面os自己会时不时有自己的策略将数据刷入磁盘，不可控了。

### 哨兵（sentinel，sentinel主要是监控主从redis是否运行正常，不正常切换主从.）
sentinel监控主从原理：监控主redis,如果不回应出现问题，就会将slave设为master，其他slave指向它。
Sentinel不断与master通信,获取master的slave信息，监听master与slave的状态。
如果某slave失效,直接通知master去除该slave.
如果master失效,,是按照slave优先级(可配置), 选取1个slave做 new master。

### 主从复制流程（这个过程是异步的）
- 1、slave server启动连接到master server之后，salve server主动发送SYNC命令给master server。
- 2、master server接受SYNC命令之后，判断，是否有正在进行内存快照的子进程，如果有，则等待其结束，否则，fork一个子进程，子进程把内存数据保存为文件，并发送给slave server。
- 3、master server子进程进程做数据快照时，父进程可以继续接收client端请求写数据，此时，父进程把新写入的数据放到待发送缓存队列(backlog,默认大小是1M)中。
- 4、slave server 接收内存快照文件之后，清空内存数据，根据接收的快照文件，重建内存表数据结构。
- 5、master server把快照文件发送完毕之后，发送缓存队列中保存的子进程快照期间改变的数据给slave server，slave server做相同处理，保存数据一致性。
- 6、master server 后续接收的数据，都会通过步骤1建立的连接，把数据发送到slave server。

NOTE:
（1）redis采用异步方式复制数据到slave节点，不过redis 2.8开始，slave node会周期性地确认自己每次复制的数据量    
（2）一个master node是可以配置多个slave node的    
（3）slave node也可以连接其他的slave node    
（4）slave node做复制的时候，是不会block master node的正常工作的      
（5）slave node在做复制的时候，也不会block对自己的查询操作，它会用旧的数据集来提供服务; 但是复制完成的时候，需要删除旧数据集，加载新数据集，这个时候就会暂停对外服务了    
（6）slave node主要用来进行横向扩容，做读写分离，扩容的slave node可以提高读的吞吐量    

### 异步复制导致的数据丢失（redis数据复制问题）
> 因为master -> slave的复制是异步的，所以可能有部分数据还没复制到slave，master就宕机了，此时这些部分数据就丢失了

### 脑裂（redis数据复制问题）
> 也就是说，某个master所在机器突然脱离了正常的网络，跟其他slave机器不能连接，但是实际上master还运行着
此时哨兵可能就会认为master宕机了，然后开启选举，将其他slave切换成了master
这个时候，集群里就会有两个master，也就是所谓的脑裂
此时虽然某个slave被切换成了master，但是可能client还没来得及切换到新的master，还继续写向旧master的数据可能也丢失了
因此旧master再次恢复的时候，会被作为一个slave挂到新的master上去，自己的数据会清空，重新从新的master复制数据

## 常见问题
Q:在dump rdb过程中,aof如果停止同步,会不会丢失？
> A:不会,所有的操作缓存在内存的队列里, dump完成后,统一操作.

Q:aof重写什么意思？
> A:aof重写是指把内存中的数据,逆化成命令,写入到.aof日志里.
以解决 aof日志过大的问题.（因为对同一key value操作的步骤比较多，日志比较多，但内存中就有一个最终的状态，所以重写成简单一次性命令
比如最终set key value.降低文件的大小。

Q:如果rdb文件,和aof文件都存在,优先用谁来恢复数据？
> A:aof文件的完整度较高。

Q:恢复时rdb和aof哪个恢复的快？
> A:rdb快,因为其是数据的内存映射,直接载入到内存,而aof是命令,需要逐条执行。

Q:sentinel与master通信,如果某次因为master IO操作频繁,导致超时,此时,认为master失效,很武断，怎么办？
> A:sentnel允许多个实例看守1个master, 当N台(N可设置)sentinel都认为master失效,才正式失效。

Q:在redis中保存几条数据，立即停掉redis进程，然后重启redis，数据还在吗？为什么？
> A:带出来一个知识点，通过redis-cli SHUTDOWN这种方式去停掉redis，其实是一种安全退出的模式，redis在退出的时候会将内存中的数据立即生成一份完整的rdb快照
  
redis缓存架构[链接](https://www.jianshu.com/u/baad5ee99ca9)










