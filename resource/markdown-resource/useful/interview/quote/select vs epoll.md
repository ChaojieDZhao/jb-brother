## select()

确定一个或多个套接口的状态，本函数用于确定一个或多个套接口的状态，对每一个套接口，
调用者可查询它的可读性、可写性及错误状态信息，用fd_set结构来表示一组等待检查的套接口，在调用返回时，
这个结构存有满足一定条件的套接口组的子集，并且select()返回满足条件的套接口的数目。

nfds：是一个整数值，是指集合中所有文件描述符的范围，即所有文件描述符的最大值加1，不能错！在Windows中这个参数的值无所谓，可以设置不正确。
readfds：（可选）指针，指向一组等待可读性检查的套接口。
writefds：（可选）指针，指向一组等待可写性检查的套接口。
exceptfds：（可选）指针，指向一组等待错误检查的套接口。
timeout：select()最多等待时间，对阻塞操作则为NULL。

select()调用返回处于就绪状态并且已经包含在fd_set结构中的描述字总数；如果超时则返回0；否则的话，返回SOCKET_ERROR错误，应用程序可通过WSAGetLastError获取相应错误代码。
当返回为-1时，所有描述符集清0。
当返回为0时，超时不修改任何描述符集。
当返回为非0时，在3个描述符集里，依旧是1的位就是**准备好的描述符**。

fd_set：
select()机制中提供一fd_set的数据结构，实际上是一long类型的数组，每一个数组元素都能与一打开的文件句柄（不管是socket句柄，还是其他文件或命名管道或设备句柄）建立联系，
建立联系的工作由程序员完成，当调用select()时，由内核根据IO状态修改fd_set的内容，由此来通知执行了select()的进程哪一socket或文件发生了可读或可写事件。

fd_set结构 ， 就是一个long int数据，long int类型为32位，使用位域的方式表示描述符的就绪，数组中所有元素按照二进制位排列，每一位都对应一个文件描述符（通过索引关联）。
___FD_SETSIZE / __NFDBITS 前者为1024，后者与系统有关，一般数组大小为32。所以可以监听32*32个文件描述符。

```c++
typedef struct{
    long int fds_bits[__FD_SETSIZE / __NFDBITS];   //
} fd_set;
```

常见用法：
FD_ZERO(&set); /*FD_ZERO清除文件描述符集fdset中的所有位(既把所有位都设置为0)*/
FD_SET(fd, &set); /*设置文件描述符集fdset中对应于文件描述符fd的位(设置为1)*/
FD_CLR(fd, &set); /*清除文件描述符集fdset中对应于文件描述符fd的位（设置为0）*/
FD_ISSET(fd, &set); /*检测文件描述符集fdset中对应于文件描述符fd的位是否被设置*/

## epoll

epoll 全称 eventpoll，是 linux 内核实现IO多路复用（IO multiplexing）的一个实现。IO多路复用的意思是在一个操作里同时监听多个输入输出源，在其中一个或多个输入输出源可用的时候返回，然后对其的进行读写操作。

在 linux 中，和 epoll 类似的有 select 和 poll。网上很多文章着重关注 epoll 与 select 的区别，或者关注 epoll 中 level-triggered 和 edge-triggered 的行为。

从性能上看，如果 fd 集合很大，用户态和内核态之间数据复制的花销是很大的，所以 select 一般限制 fd 集合最大1024。

从使用上看，epoll 返回的是可用的 fd 子集，select 返回的是全部，哪些可用需要用户遍历判断。


**首先**，linux 的 file 有个 pollable 的概念，只有 pollable 的 file 才可以加入到 epoll 和 select 中。一个 file 是 pollable 的当且仅当其定义了 file->f_op->poll。file->f_op->poll 的形式如下

__poll_t poll(struct file *fp, poll_table *wait)
不同类型的 file 实现不同，但做的事情都差不多：

通过 fp 拿到其对应的 waitqueue
通过 wait 拿到外部设置的 callback[[1]]
执行 callback(fp, waitqueue, wait)，在 callback 中会将另外一个 callback2[[2]] 注册到 waitqueue[[3]]中，此后 fp 有触发事件就会调用 callback2
waitqueue 是事件驱动的，与驱动程序密切相关，简单来说 poll 函数在 file 的触发队列中注册了个 callback， 有事件发生时就调用callback。感兴趣可以根据文后 [[4]] 的提示看看 socket 的 poll 实现

然后是重要的三个函数：

调用epoll_create时，内核除了帮我们在epoll文件系统里建了个file结点，在内核cache里建了个 红黑树 用于存储以后epoll_ctl传来的socket外，还会再建立一个list链表，用于存储准备就绪的事件.


当我们执行epoll_ctl时，除了把socket放到epoll文件系统里file对象对应的红黑树上之外，还会给内核中断处理程序注册一个回调函数，告诉内核，如果这个句柄的中断到了，
就把它放到准备就绪list链表里。所以，当一个socket上有数据到了，内核在把网卡上的数据copy到内核中后就来把socket插入到准备就绪链表里了。

epoll_ctl 支持添加移除 fd，我们只看添加的情况。epoll_ctl 的主要操作在 ep_insert, 它做了以下事情：

初始化一个 epitem，里面包含 fd，监听的事件，就绪链表，关联的 epoll_fd 等信息
调用 ep_item_poll(epitem, ep_ptable_queue_proc[[1]])。ep_item_poll 会调用 vfs_poll， vfs_poll 会调用上面说的 file->f_op->poll 将 ep_poll_callback[[2]] 注册到 waitqueue
调用 ep_rbtree_insert(eventpoll, epitem) 将 epitem 插入 evenpoll 对象的红黑树，方便后续查找
ep_poll_callback

在了解 epoll_wait 之前我们还需要知道 ep_poll_callback 做了哪些操作

ep_poll_callback 被调用，说明 epoll 中某个 file 有了新事件
eventpoll 对象有一个 rdllist 字段，用链表存着当前就绪的所有 epitem
ep_poll_callback 被调用的时候将 file 对应的 epitem 加到 rdllist 里（不重复）
如果当前用户正在 epoll_wait 阻塞状态 ep_poll_callback 还会通过 wake_up_locked 将 epoll_wait 唤醒


epoll相比于select并不是在所有情况下都要高效，例如在如果有少于1024个文件描述符监听，且大多数socket都是出于活跃繁忙的状态，这种情况下，select要比epoll更为高效，**因为epoll会有更多次的系统调用，用户态和内核态会有更加频繁的切换**。

epoll_wait 主要做了以下操作：

**循环检测rdlist，如果设置了timeout，可能会进入到睡眠状态，但是过程中可能会被唤醒**

1、检查 rdllist，如果不为空则去到 7，如果为空则去到 2
2、设置 timeout
3、开始无限循环
4、设置线程状态为 TASK_INTERRUPTABLE 
5、检查 rdllist 如果不为空去到 7， 否则去到 6
6、调用 schedule_hrtimeout_range 睡到 timeout，中途有可能被 ep_poll_callback 唤醒回到 4，如果真的 timeout 则 break 去到 7
7、设置线程状态为 TASK_RUNNING，rdllist如果不为空时退出循环，否则继续循环
8、调用 ep_send_events 将 rdllist 返回给用户态


epoll高效的本质在于：

减少了用户态和内核态的文件句柄拷贝
减少了对可读可写文件句柄的遍历
mmap 加速了内核与用户空间的信息传递，epoll是通过内核与用户mmap同一块内存，避免了无谓的内存拷贝
IO性能不会随着监听的文件描述的数量增长而下降
使用红黑树存储fd，以及对应的回调函数，其插入，查找，删除的性能不错，相比于hash，不必预先分配很多的空间

