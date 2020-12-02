### 用Runnable还是Thread
> 如果你知道Java不支持类的多重继承，但允许你调用多个接口。所以如果你要继承其他类，当然是调用Runnable接口好了。

### Thread 类中的start(:和 run(:方法有什么区别？
> start()方法被用来启动新创建的线程，而且start()内部调用了run()方法，这和直接调用run()方法的效果不一样。当你调用run()方法的时候，只会是在原来的线程中调用，没有新的线程启动，start()方法才会启动新线程。
[start和run的例子](other01/SecondThreadTest.java)
  
### Java中Runnable和Callable有什么不同？
> Runnable和Callable都代表那些要在不同的线程中执行的任务。Runnable从JDK1.0开始就有了，Callable是在JDK1.5增加的。它们的主要区别是Callable的 call(:方法可以返回值和抛出异常，而Runnable的run()方法没有这些功能。Callable可以返回装载有计算结果的Future对象。

### Java中CyclicBarrier 和 CountDownLatch有什么不同？
> CyclicBarrier 和 CountDownLatch 都可以用来让一组线程等待其它线程。与 CyclicBarrier 不同的是，CountdownLatch 不能重新使用。

### 绿色线程和本地线程的区别
> 绿色线程不是由操作系统调度的，这意味着它们的调度在用户空间中发生，而不是内核处理的。 这意味着绿色线程通常不能使用所有CPU内核，对于任何主流平台，现在运行 Java，你将使用真正的线程。

### Java中什么是竞态条件？
> 竞态条件会导致程序在并发情况下出现一些bugs。多线程对一些资源的竞争的时候就会产生竞态条件，如果首先要执行的程序竞争失败排到后面执行了，那么整个程序就会出现一些不确定的bugs。这种bugs很难发现而且会重复出现，因为线程间的随机竞争。一个例子就是无序处理。

### Java中如何停止一个线程？
> Java提供了很丰富的API但没有为停止线程提供API。JDK 1.0本来有一些像stop(), suspend(:和 resume()的控制方法但是由于潜在的死锁威胁因此在后续的JDK版本中他们被弃用了，之后Java API的设计者就没有提供一个兼容且线程安全的方法来停止一个线程。当run(:或者 call(:方法执行完的时候线程会自动结束,如果要手动结束一个线程，你可以用volatile 布尔变量来退出run()方法的循环或者是取消任务来中断线程。
[Volatile例子](example/Volatile/VolatileTest.java)

### 一个线程运行时发生异常会怎样？
> 这是我在一次面试中遇到的一个很刁钻的Java面试题, 简单的说，如果异常没有被捕获该线程将会停止执行。Thread.UncaughtExceptionHandler是用于处理未捕获异常造成线程突然中断情况的一个内嵌接口。当一个未捕获异常将造成线程中断的时候JVM会使用Thread.getUncaughtExceptionHandler()来查询线程的UncaughtExceptionHandler并将线程和异常作为参数传递给handler的uncaughtException()方法进行处理。

### 如果保证几个线程的顺序执行
- 可以使用join方法，当调用一个线程的join方法的时候，则会等待这个线程运行终结之后才会继续运行。
[join例子](method/JoinTest.java)
- 使用condition的await和signalAll方法。
[await_signalAll例子](example/thread/sequencethread/Application.java)

https://blog.csdn.net/zqz_zqz/article/details/70233767