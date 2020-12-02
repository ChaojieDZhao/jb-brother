### Redis 内存淘汰机制了解么？

> 相关问题：MySQL 里有 2000w 数据，Redis 中只存 20w 的数据，如何保证 Redis 中的数据都是热点数据?

Redis 提供 6 种数据淘汰策略：

1. **volatile-lru（least recently used）**：从已设置过期时间的数据集（server.db[i].expires）中挑选最近最少使用的数据淘汰
2. **volatile-ttl**：从已设置过期时间的数据集（server.db[i].expires）中挑选将要过期的数据淘汰
3. **volatile-random**：从已设置过期时间的数据集（server.db[i].expires）中任意选择数据淘汰
4. **allkeys-lru（least recently used）**：当内存不足以容纳新写入数据时，在键空间中，移除最近最少使用的 key（这个是最常用的）
5. **allkeys-random**：从数据集（server.db[i].dict）中任意选择数据淘汰
6. **no-eviction**：禁止驱逐数据，也就是说当内存不足以容纳新写入数据时，新写入操作会报错。这个应该没人使用吧！

4.0 版本后增加以下两种：

7. **volatile-lfu（least frequently used）**：从已设置过期时间的数据集(server.db[i].expires)中挑选最不经常使用的数据淘汰
8. **allkeys-lfu（least frequently used）**：当内存不足以容纳新写入数据时，在键空间中，移除最不经常使用的 key


###  redis的主从复制机制
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
