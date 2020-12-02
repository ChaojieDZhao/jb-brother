# 说下arraylist的扩容机制
初始化的时候数组默认是0，添加的时候会先去确认当前数组长度+1的空间是否够用，然后进行数组数据的添加。
如何确认数组长度？

先计算当前长度，如果要是elementData就是空数组的话，则直接变为默认大小的10.

和元素数据的大小去比对，如果大于0 的话就去扩容。

使用Arrays.copyOf(elementData, newCapacity);
arraylsit的扩容方式是Arrays.copyOf()方法把原数组进行了拷贝，然后返回一个新长度的数组

# 讲讲treemap 他是怎么实现自然排序的
内置一个排序器，会调用元素的compareTo(Object o)方法来比较元素之间的大小关系， 我们在要排序的对象中实现 Comparable 然后重写compareTo就可以。

# 为什么hashMap的容量扩容时一定是2的幂次?

HashMap通过哈希算法得出哈希值之后，将键值对放入哪个索引的方法：h & (length-1)；
1、HashMap的容量为16转化成二进制为10000，length-1得出的二进制为01111，因为2的幂-1都是11111结尾的，所以碰撞几率小。     
2、空间不浪费；如果length不是2的次幂，比如length为15，则length-1为14，对应的二进制为1110，hashcode与操作的最后一位都为0，
而0001，0011，0101，1001，1011，0111，1101这几个位置永远都不能存放元素了，空间浪费相当大，更糟的是这种情况中，数组可以使用的位置比数组长度小了很多，这意味着进一步增加了碰撞的几率，减慢了查询的效率！这样就会造成空间的浪费。

5、hashmap为什么链表长度8的时候会转树？

为什么要转变？因为红黑树查询效率高。

为什么不直接使用map？
因为树节点所占空间是普通节点的两倍，所以只有当节点足够多的时候，才会使用树节点。也就是说，节点少的时候，尽管时间复杂度上，红黑树比链表好一点，但是红黑树所占空间比较大
，综合考虑，认为只能在节点太多的时候，红黑树占空间大这一劣势不太明显的时候，才会舍弃链表，使用红黑树。

为什么是8:
因为通常情况下，链表长度很难达到8，但是特殊情况下链表长度为8，哈希表容量又很大，造成链表性能很差的时候，只能采用红黑树提高性能，这是一种应对策略。

6、hashmap remove方法在增强for循环中执行有问题吗？
有问题 他是fail-fast  快速失败的
modCount == expectedModCount

7 那你知道哪些拒绝策略呢
答：大概知道有抛异常，丢弃最新的任务，或者丢弃年龄最大的任务等

- 1 corePoolSize 维持的最小的活动线程的数量      
当提交一个任务到线程池时，如果总线程数量小于corePoolSize，线程会继续创建新线程来执行任务。除非您设置了allowCoreThreadTimeOut属性（默认为false）。
- 2 maximumPoolSize 线程池的最大数量  
所需线程数大于corePoolSize`&&`阻塞队列已满 会继续创建线程直到maximumPoolSize 
- 3 keepAliveTime 空闲线程存活时间  
如果当前线程数量大于corePoolSize，超出的`&&`空闲时间大于keepAliveTime的 则会终止。
- 4 unit 时间单位  
- 5 workQueue 任务队列  
用于保存等待执行的任务的阻塞队列。一个阻塞队列，用来存储等待执行的任务
- 6 threadFactory 用于设置创建线程的工厂  
可以通过线程工厂给每个创建出来的线程设置更有意义的名字
- 7 handler 饱和策略（四种ThreadPoolExcutor的饱和策略）  
ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务

8 处理流程
当提交一个任务到线程池时它的执行流程是怎样的呢？

![](https://ws1.sinaimg.cn/large/006tNbRwgy1fnbzmai8yrj30dw08574s.jpg)
> 首先第一步会判断核心线程数有没有达到上限，如果没有则创建线程(会获取全局锁)，满了则会将任务丢进阻塞队列。     
如果队列也满了则需要判断最大线程数是否达到上限，如果没有则创建线程(获取全局锁)，如果最大线程数也满了则会根据饱和策略处理。

## 那我们现在这么多线程数都开了，以后没任务了是不是有点浪费资源？

<p>allowCoreThreadTimeOut为true<br>该值为true，则线程池数量最后销毁到0个。</p>
当没任务的时候，线程池的getTask方法会进行判断，while死循环会结束，知道线程数降到核心线程数


## 线程池怎么关闭的呢

调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，
但是如果调用的是 shutdownNow() 方法，则相当于调用每个线程的 interrupt() 方法。
最后，一定要记得，shutdownNow和shuwdown调用完，线程池并不是立马就关闭了，要想等待线程池关闭，还需调用awaitTermination方法来阻塞等待。


## spring 三级缓存

https://www.jianshu.com/p/adc8a0ecf596
https://www.cnblogs.com/shoshana-kong/p/10890136.html
https://www.jianshu.com/p/6c359768b1dc