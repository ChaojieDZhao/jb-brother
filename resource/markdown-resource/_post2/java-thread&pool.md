---
title: java线程
date: 2017-01-22 09:07:21
tags:
- java
---


## 线程的六种状态:
### NEW 
线程刚创建, 尚未启动

### RUNNABLE
线程正在虚拟机正常运行中, 当然可能会有某种耗时计算/IO等待的操作/CPU时间片切换等, 这个状态下发生的等待一般是其他系统资源, 而不是锁, Sleep等

### BLOCKED
这个状态下, 是在多个线程有同步操作的场景, 比如正在等待另一个线程的 synchronized 块的执行释放, 或者可重入的 synchronized 块里别人调用wait() 方法, 也就是这里是线程在等待进入临界区

### WAITING
这个状态下是指线程拥有了某个锁之后, 调用了他的wait方法, 等待其他线程/锁拥有者调用 notify / notifyAll 一遍该线程可以继续下一步操作,
 这里要区分 BLOCKED 和 WATING 的区别, **一个是在临界点外面等待进入, 一个是在临界点里面wait等待别人notify**， 线程调用了另外一个线程（THREAD-OTHER）的join方法, 也会进入WAITING状态, 等待（THREAD-OTHER）的线程执行结束

### TIMED_WAITING
这个状态就是有限的(时间限制)的WAITING, 一般出现在调用wait(long), join(long)等情况下, 另外情况就是一个线程sleep后, 也会进入TIMED_WAITING状态

### TERMINATED
这个状态下表示 该线程的run方法已经执行完毕了, 基本上就等于死亡了(当时如果线程被持久持有, 可能不会被回收)

### 守护进程
deamon是一种运行在后台的一种特殊的进程，它独立于控制终端并且周期性的执行某种任务或等待处理某些发生的事件。由于在Linux中，**每个系统与用户进行交流的界面成为终端**，每一个从此终端开始运行的进程都会依附于这个终端，这个终端被称为这些进程的控制终端，当控制终端被关闭的时候，相应的进程都会自动关闭。
但是守护进程却能突破这种限制，它脱离于终端并且在后台运行，并且它脱离终端的目的是为了避免进程在运行的过程中的信息在任何终端中显示并且进程也不会被任何终端所产生的终端信息所打断。它从被执行的时候开始运转，直到整个系统关闭才退出(当然可以人为的杀死相应的守护进程)。如果想让某个进程不因为用户或中断或其他变化而影响，那么就必须把这个进程变成一个守护进程。

### 守护线程和非守护线程
Daemon的作用是为其他线程的运行提供服务，比如说GC线程。User Thread线程和Daemon Thread守护线程本质上来说去没啥区别，唯一的区别之处就在虚拟机的离开：如果User Thread全部撤离，那么Daemon Thread也就没啥线程好服务的了，所以虚拟机也就退出了。
- 1）、thread.setDaemon(true)必须在thread.start()之前设置，否则会跑出一个IllegalThreadStateException异常。你不能把正在运行的常规线程设置为守护线程。  （备注：这点与守护进程有着明显的区别，守护进程是创建后，让进程摆脱原会话的控制+让进程摆脱原进程组的控制+让进程摆脱原控制终端的控制；所以说寄托于虚拟机的语言机制跟系统级语言有着本质上面的区别）
- 2）、在Daemon线程中产生的新线程也是Daemon的。  （这一点又是有着本质的区别了：守护进程fork()出来的子进程不再是守护进程，尽管它把父进程的进程相关信息复制过去了，但是子进程的进程的父进程不是init进程，所谓的守护进程本质上说就是“父进程挂掉，init收养，然后文件0,1,2都是/dev/null，当前目录到/”）

## Java通过Executors提供四种线程池，分别为：
### 1、newCachedThreadPool
创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。（线程最大并发数不可控制）
``` java
public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
}
```
### 2、newFixedThreadPool
创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
``` java
public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
}
```
### 3、newScheduledThreadPool
创建一个定长线程池，支持定时及周期性任务执行。
``` java
public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize);
}

public ScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
              new DelayedWorkQueue())
}

public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
             Executors.defaultThreadFactory(), defaultHandler);
}
```
### 4、newSingleThreadExecutor
创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
``` java
public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
}
```
### ThreadPoolExecutor的构造方法参数
``` java
public ThreadPoolExecutor(int corePoolSize,    
                              int maximumPoolSize,    
                              long keepAliveTime,    
                              TimeUnit unit,    
                              BlockingQueue<Runnable> workQueue,    
                              ThreadFactory threadFactory,    
                              RejectedExecutionHandler handler)     
```

### 构造方法参数解释
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


### 处理流程
当提交一个任务到线程池时它的执行流程是怎样的呢？

![](https://ws1.sinaimg.cn/large/006tNbRwgy1fnbzmai8yrj30dw08574s.jpg)
> 首先第一步会判断核心线程数有没有达到上限，如果没有则创建线程(会获取全局锁)，满了则会将任务丢进阻塞队列。     
如果队列也满了则需要判断最大线程数是否达到上限，如果没有则创建线程(获取全局锁)，如果最大线程数也满了则会根据饱和策略处理。
常用的饱和策略有:  
- 直接丢弃任务。     
- 调用者线程处理。     
- 丢弃队列中的最近任务，执行当前任务。  
所以当线程池完成预热之后都是将任务放入队列，接着由工作线程一个个从队列里取出执行。     

## 合理配置线程池
> 线程池并不是配置越大越好，而是要根据任务的熟悉来进行划分：      
如果是 `CPU` 密集型任务应当分配较少的线程，比如 `CPU` 个数相当的大小。      
如果是 IO 密集型任务，IO操作不占用CPU，线程并不是一直在运行，所以可以尽可能的多配置线程，比如 `CPU 个数 * 2` 。         
当是一个混合型任务，可以将其拆分为 `CPU` 密集型任务以及 `IO` 密集型任务，这样来分别配置。             
在《linux多线程服务器端编程》中有一个思路，CPU计算和IO的阻抗匹配原则。       
如果线程池中的线程在执行任务时，密集计算所占的时间比重为P(0<P<=1)，而系统一共有C个CPU，为了让CPU跑满而又不过载，线程池的大小经验公式 T = C / P。        
在此，T只是一个参考，考虑到P的估计并不是很准确，T的最佳估值可以上下浮动50%。        
这个经验公式的原理很简单，T个线程，每个线程占用P的CPU时间，如果刚好占满C个CPU,那么必有 T * P = C。        
下面验证一下边界条件的正确性：        
假设C = 8，P = 1.0，线程池的任务完全是密集计算，那么T = 8。只要8个活动线程就能让8个CPU饱和，再多也没用了，因为CPU资源已经耗光了。       
假设C = 8，P = 0.5，线程池的任务有一半是计算，有一半在等IO上，那么T = 16.考虑操作系统能灵活，合理调度sleeping/writing/running线程，那么大概16个“50%繁忙的线程”能让8个CPU忙个不停。    
启动更多的线程并不能提高吞吐量，反而因为增加上下文切换的开销而降低性能。    
如果P < 0.2，这个公式就不适用了，T可以取一个固定值，比如 5*C。另外公式里的C不一定是CPU总数，可以是“分配给这项任务的CPU数目”，比如在8核机器上分出4个核来做一项任务，那么C=4    
文章如何合理设置线程池大小里面提到了一个公式：    
最佳线程数目 = （（线程等待时间+线程CPU时间）/线程CPU时间 ）* CPU数目    
比如平均每个线程CPU运行时间为0.5s，而线程等待时间（非CPU运行时间，比如IO）为1.5s，CPU核心数为8，那么根据上面这个公式估算得到：((0.5+1.5)/0.5)*8=32。这个公式进一步转化为：    
最佳线程数目 = （线程等待时间与线程CPU时间之比 + 1）* CPU数目    
可以得出一个结论：    
线程等待时间所占比例越高，需要越多线程。线程CPU时间所占比例越高，需要越少线程。    
以上公式与之前的CPU和IO密集型任务设置线程数基本吻合    

## 另外的线程池类型
### ScheduledExecutorService（调度线程池）
> 该线程池致力于定时执行的任务，比如心跳类型的任务。
``` java
ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
    Runnable task = new Runnable() {
        @Override
        public void run() {
            System.out.println("...........HeartBeat...........");
        }
    };
executor.scheduleAtFixedRate(task, 5, 3, TimeUnit.SECONDS);
```



