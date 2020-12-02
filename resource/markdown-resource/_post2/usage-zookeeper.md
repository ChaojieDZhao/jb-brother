---
title: zookeeper使用
date: 2018-12-22 20:01:36
tags:
- zookeeper
---

## zk伪集群安装
- 1 百度云下载zkcluster.tar.gz（安装之后打包的文件，保存以供后期快速使用）
- 2 上传linux服务器解压
- 3 确认根据目录图片确认当前目录是否准确
- 4 修改conf配置 zoo.cfg的ip配置
- 5 使用命令启动，查看状态

## zk常用命令
``` shell
zk_4507/bin/zkServer.sh start zk_4507/conf/zoo.cfg    #启动
zk_4506/bin/zkServer.sh start zk_4506/conf/zoo.cfg    #启动  
zk_4505/bin/zkServer.sh start zk_4505/conf/zoo.cfg    #启动

zk_4505/bin/zkCli.sh -server 127.0.0.1:4505    #链接
zk_4506/bin/zkServer.sh status    #查看  zookeeper配置和角色信息  
```
> NOTE:具体路径操作命令：`-h` 查询帮助

## zk配额机制
-n  设置子节点的数量，包括其自身。   
-b  节点中数据的长度(byte)。
```java_holder_method_tree
setquota -n 10 /test_quota    #设置配额
listquota  /test_quota    #查看配额
delquota -n /test_quota    #删除配额
```

## zk三种数据存储机制
-s: sequence 模式, 创建后会生成原节点+序列号的节点，再次执行相同命令会产生序列号加1的节点。
-e: 临时节点与数据, 关闭连接后数据就会被删除。

## 什么是命名服务？
是指通过指定的名字来获取资源或者服务的地址，提供者的信息。    
利用Zookeeper很容易创建一个全局的路径，而这个路径就可以作为一个名字，它可以指向集群中的集群，提供的服务的地址，远程对象等。    
简单来说使用Zookeeper做命名服务就是用路径作为名字，路径上的数据就是其名字指向的实体。     
服务提供者在启动的时候，向zk上的指定节点/dubbo/${serviceName}/providers目录下写入自己的URL地址，这个操作就完成了服务的发布。    
服务消费者启动的时候，订阅/dubbo/{serviceName}/providers目录下的提供者URL地址， 并向/dubbo/{serviceName} /consumers目录下写入自己的URL地址。    
注意，所有向zk上注册的地址都是临时节点，这样就能够保证服务提供者和消费者能够自动感应资源的变化。   
另外，Dubbo还有针对服务粒度的监控，方法是订阅/dubbo/{serviceName}目录下所有提供者和消费者的信息。   

## 什么是znode?
znode维持一个stat结构，它包含数据变化的版本号、ACL变化和时间戳，以允许cache校验和协调化的更新。   
每当znode的数据变化时，版本号将增加。一个客户端收到数据时，它也会收到数据的版本号。    
保存在每个znode中的数据都是自动读写的。读操作获取znode的所有数据，写操作替换掉znode的所有数据。每个节点有一个 访问控制表（ACL）来限制谁能做哪些操作。    
Regular ZNode: 常规型ZNode, 用户需要显式的创建、删除。
Ephemeral ZNode: 临时型ZNode, 用户创建它之后，可以显式的删除，也可以在创建它的Session结束后，由ZooKeeper Server自动删除。    
ZNode还有一个Sequential的特性，如果创建的时候指定的话，该ZNode的名字后面会自动Append一个不断增加的SequenceNo。
    
## znode中stat的解释
- czxid. 节点创建时的zxid.
- mzxid. 节点最新一次更新发生时的zxid.
- ctime. 节点创建时的时间戳.
- mtime. 节点最新一次更新发生时的时间戳.
- dataVersion. 节点数据的更新次数.
- cversion. 其子节点的更新次数.
- aclVersion. 节点ACL(授权信息)的更新次数.
- ephemeralOwner. 如果该节点为ephemeral节点, ephemeralOwner值表示与该节点绑定的session id. 如果该节点不是ephemeral节点, ephemeralOwner值为0. 
- dataLength. 节点数据的字节数.
- numChildren. 子节点个数.

## znode中acl(access control lists)
- 针对节点可以设置相关读写等权限，目的是为了保障数据安全性
- 权限permissions可以指定不同的权限范围以及角色

### acl的构成
zk的acl通过 [scheme:id:permissions] 来构成权限列表：
- scheme：代表采用的某种权限机制    

world：**world**(默认的都为world)下只有一个id，即只有一个用户，也就是anyone，那么组合的写法就是 world:anyone:[permissions]    
ip：当设置为ip指定的ip地址，此时限制ip进行访问，比如ip:192.168.77.130:[permissions]    
auth：代表认证登录，需要注册用户获取权限后才可以登录访问，形式为 auth:user:password:[permissions]    
digest：需要对密码加密才能访问，组合形式为：digest:username:BASE64(SHA1(password)):[permissions]    
super：代表超级管理员，拥有所有的权限。  
> auth与digest的区别就是，前者使用明文密码进行登录，后者使用密文密码进行登录。setAcl /path auth:lee:lee:cdrwa 
与 setAcl /path digest:lee:BASE64(SHA1(password)):cdrwa是等价的，在通过 addauth digest lee:lee 后都能操作指定节点的权限。在实际情况中，digest要更为常用一些。

- id：代表允许访问的用户
- permissions：权限组合字符串

权限字符串缩写 crdwa ：
CREATE：创建子节点权限
READ：访问节点/子节点权限
WRITE：设置节点数据权限
DELETE：删除子节点权限
ADMIN：管理员权限

### acl命令
getAcl 获取某个节点的acl权限信息    
setAcl 设置某个节点的acl权限信息    
addauth 输入认证授权信息，注册时输入明文密码（登录），但是在zk的系统里，密码是以加密后的形式存在的   
```java_holder_method_tree
addauth digest user1:123456 #添加一个认证信息
```

## 什么是session（会话）？
Client与ZooKeeper之间的通信，需要创建一个Session，这个Session会有一个超时时间。因为ZooKeeper集群会把Client的Session信息持久化，所以在Session没超时之前，
Client与ZooKeeper Server的连接可以在各个ZooKeeper Server之间透明地移动。
在实际的应用中，如果Client与Server之间的通信足够频繁，Session的维护就不需要其它额外的消息了。
否则，ZooKeeper Client会每t/3 ms发一次心跳给Server，如果Client 2t/3 ms没收到来自Server的心跳回应，就会换到一个新的ZooKeeper Server上。
这里t是用户配置的Session的超时时间。

## 什么是watcher？
ZooKeeper支持一种Watch操作，Client可以在某个ZNode上设置一个Watcher，来Watch该ZNode上的变化。如果该ZNode上有相应的变化，就会触发这个Watcher，
把相应的事件通知给设置Watcher的Client。需要注意的是，ZooKeeper中的Watcher是一次性的，即触发一次就会被取消，如果想继续Watch的话，需要客户端重新设置Watcher。

## zk的角色扮演
**leader和follower**  
ZooKeeper需要在所有的服务（可以理解为服务器）中选举出一个Leader，然后让这个Leader来负责管理集群。此时，集群中的其它服务器则成为此Leader的Follower。并且，当Leader故障的时候，需要ZooKeeper能够快速地在Follower中选举出下一个Leader。这就是ZooKeeper的Leader机制，下面我们将简单介绍在ZooKeeper中，Leader选举（Leader Election）是如何实现的。
此操作实现的核心思想是：首先创建一个EPHEMERAL目录节点，例如“/election”。然后。每一个ZooKeeper服务器在此目录下创建一个SEQUENCE|EPHEMERAL类型的节点，例如“/election/n_”。在SEQUENCE标志下，ZooKeeper将自动地为每一个ZooKeeper服务器分配一个比前一个分配的序号要大的序号。此时创建节点的ZooKeeper服务器中拥有最小序号编号的服务器将成为Leader。
在实际的操作中，还需要保障：当Leader服务器发生故障的时候，系统能够快速地选出下一个ZooKeeper服务器作为Leader。一个简单的解决方案是，让所有的follower监视leader所对应的节点。当Leader发生故障时，Leader所对应的临时节点将会自动地被删除，此操作将会触发所有监视Leader的服务器的watch。这样这些服务器将会收到Leader故障的消息，并进而进行下一次的Leader选举操作。但是，这种操作将会导致“从众效应”的发生，尤其当集群中服务器众多并且带宽延迟比较大的时候，此种情况更为明显。
在Zookeeper中，为了避免从众效应的发生，它是这样来实现的：每一个follower对follower集群中对应的比自己节点序号小一号的节点（也就是所有序号比自己小的节点中的序号最大的节点）设置一个watch。只有当follower所设置的watch被触发的时候，它才进行Leader选举操作，一般情况下它将成为集群中的下一个Leader。很明显，此Leader选举操作的速度是很快的。因为，每一次Leader选举几乎只涉及单个follower的操作。

**observer**
observer的行为在大多数情况下与follower完全一致, 但是他们不参加选举和投票, 而仅仅接受(observing)选举和投票的结果.

## zk特性 
### 读、写(更新)模式
在ZooKeeper集群中，读可以从任意一个ZooKeeper Server读，这一点是保证ZooKeeper比较好的读性能的关键；
写的请求会先Forwarder到Leader，然后由Leader来通过ZooKeeper中的原子广播协议，将请求广播给所有的Follower，Leader收到一半以上的写成功的Ack后，
就认为该写成功了，就会将该写进行持久化，并告诉客户端写成功了。

### WAL和Snapshot
和大多数分布式系统一样，ZooKeeper也有WAL(Write-Ahead-Log)，对于每一个更新操作，ZooKeeper都会先写WAL,
然后再对内存中的数据做更新，然后向Client通知更新结果。另外，ZooKeeper还会定期将内存中的目录树进行Snapshot，
落地到磁盘上，这个跟HDFS中的FSImage是比较类似的。这么做的主要目的，一当然是数据的持久化，二是加快重启之后的恢复速度，
如果全部通过Replay WAL的形式恢复的话，会比较慢。

### FIFO
对于每一个ZooKeeper客户端而言，所有的操作都是遵循FIFO顺序的，
这一特性是由下面两个基本特性来保证的：一是ZooKeeper Client与Server之间的网络通信是基于TCP，TCP保证了Client/Server之间传输包的顺序；二是ZooKeeper Server执行客户端请求也是严格按照FIFO顺序的。

### Linearizability
在ZooKeeper中，所有的更新操作都有严格的偏序关系，更新操作都是串行执行的，这一点是保证ZooKeeper功能正确性的关键。

## zab介绍
ZooKeeper服务的内部通信，是基于Zab协议，即ZooKeeper Atomic Broadcast协议。原子广播(AB)是分布式计算普遍使用的原语。本质上说，ZooKeeper服务是基于复制分发的。
它需要半数以上的服务器能正常工作。崩溃的服务器能恢复并且重新加入集群。ZooKeeper采用主备方式来维护被复制状态的一致性。
在ZooKeeper中，leader接受所有客户的请求并加以执行，然后将增量的状态变化，以事务的方式通过Zab复制到所有的followers。
一旦leader崩溃，在进行常规操作之前，必须执行一套恢复协议，来a). 协调各followers达到一致状态，b). 选举出新的leader。
为了选举出leader，新的leader必须得到多数支持(quorum)。由于zookeeper server随时都可能崩溃或恢复，随时间流逝，同一个进程可能好几次成为leader。
为了能区分随时间变化的不同的leader实例，ZooKeeper为每一个leader都分配一个实例值。一个leader实例值最多能映射到一个进程。
对Zab设计而言很关键的一点是，对于每个状态变化的观察，都是基于上一个状态的增量变化。这也就意味着对于状态变化，必须依赖于其更新的次序。状态改变过程是不能乱序的。
只要增量的发送是有序的，状态的更新便是幂等的，即使某个增量变化被反复执行多次。
因此，对于消息系统而言，保证at-least once语义已经足够，以此来简化整个Zab的实现。
Zab是Zookeeper核心的重要组件，因此必须高效运行。ZooKeeper被设计成能支持高吞吐量、低时延的系统。应用能广泛地在集群环境下使用Zookeeper，能接受来自不同数据中心大量的客户端连接。
在设计zookeeper过程中，我们发现很难在隔离的环境下解释清楚原子广播，必定要提及必须满足的应用的需求和目标。在解释原子广播必须涉及应用，这导致了不同的协议元素，甚至是一些有意思的优化。
Zookeeper所使用的zad协议也是类似paxos协议的。所有分布式自协商一致性算法都是paxos算法的简化或者变种。

## zk典型应用场景
1. 名字服务(Name Service) 
分布式应用中，通常需要一套完备的命令机制，既能产生唯一的标识，又方便人识别和记忆。 我们知道，每个ZNode都可以由其路径唯一标识，路径本身也比较简洁直观，另外ZNode上还可以存储少量数据，这些都是实现统一的NameService的基础。

2. 配置管理(Configuration Management) 
在分布式系统中，常会遇到这样的场景: 某个Job的很多个实例在运行，它们在运行时大多数配置项是相同的，如果想要统一改某个配置，一个个实例去改，是比较低效，也是比较容易出错的方式。通过ZooKeeper可以很好的解决这样的问题，下面的基本的步骤：

3. 组员管理(Group Membership) 
在典型的Master-Slave结构的分布式系统中，Master需要作为“总管”来管理所有的Slave, 当有Slave加入，或者有Slave宕机，Master都需要感知到这个事情，然后作出对应的调整，以便不影响整个集群对外提供服务。

4. 简单互斥锁(Simple Lock) 
我们知识，在传统的应用程序中，线程、进程的同步，都可以通过操作系统提供的机制来完成。但是在分布式系统中，多个进程之间的同步，操作系统层面就无能为力了。
这时候就需要像ZooKeeper这样的分布式的协调(Coordination)服务来协助完成同步，下面是用ZooKeeper实现简单的互斥锁的步骤，这个可以和线程间同步的mutex做类比来理解：
多个进程尝试去在指定的目录下去创建一个临时性(Ephemeral)结点 /locks/my_lock
ZooKeeper能保证，只会有一个进程成功创建该结点，创建结点成功的进程就是抢到锁的进程，假设该进程为A
其它进程都对/locks/my_lock进行Watch
当A进程不再需要锁，可以显式删除/locks/my_lock释放锁；或者是A进程宕机后Session超时，ZooKeeper系统自动删除/locks/my_lock结点释放锁。此时，其它进程就会收到ZooKeeper的通知，并尝试去创建/locks/my_lock抢锁，如此循环反复

5. 互斥锁(Simple Lock without Herd Effect) 
上一节的例子中有一个问题，每次抢锁都会有大量的进程去竞争，会造成羊群效应(Herd Effect)，为了解决这个问题，我们可以通过下面的步骤来改进上述过程：
每个进程都在ZooKeeper上创建一个临时的顺序结点(Ephemeral Sequential) /locks/lock_${seq}
${seq}最小的为当前的持锁者(${seq}是ZooKeeper生成的Sequenctial Number)
其它进程都对只watch比它次小的进程对应的结点，比如2 watch 1, 3 watch 2, 以此类推
当前持锁者释放锁后，比它次大的进程就会收到ZooKeeper的通知，它成为新的持锁者，如此循环反复
这里需要补充一点，通常在分布式系统中用ZooKeeper来做Leader Election(选主)就是通过上面的机制来实现的，这里的持锁者就是当前的“主”。

## acl四字命令
> http://zookeeper.apache.org/doc/r3.4.11/zookeeperAdmin.html#sc_zkCommands




