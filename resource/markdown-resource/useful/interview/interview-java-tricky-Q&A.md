## 为什么hashCode使用的是31这个素数？

我们为编写的类重写hashCode方法时，可能会看到如下所示的代码，其实我们不太理解为什么要使用这样的乘法运算来产生哈希码（散列码），
而且为什么这个数是个素数，为什么通常选择31这个数？前两个问题的答案你可以自己百度一下，选择31是因为可以用移位和减法运算来代替乘法，从而得到更好的性能。
说到这里你可能已经想到了：31 * num 等价于(num << 5) – num，左移5位相当于乘以2的5次方再减去自身就相当于乘以31，现在的VM都能自动完成这个优化。  

> NOTE:用最优效率的方法计算2*8     解决：2 << 3

## 为什么hashMap的容量扩容时一定是2的幂次?

HashMap通过哈希算法得出哈希值之后，将键值对放入哪个索引的方法：h & (length-1)；
1、HashMap的容量为16转化成二进制为10000，length-1得出的二进制为01111，因为2的幂-1都是11111结尾的，所以碰撞几率小。     
2、空间不浪费；如果length不是2的次幂，比如length为15，则length-1为14，对应的二进制为1110，hashcode与操作的最后一位都为0，
而0001，0011，0101，1001，1011，0111，1101这几个位置永远都不能存放元素了，空间浪费相当大，更糟的是这种情况中，数组可以使用的位置比数组长度小了很多，这意味着进一步增加了碰撞的几率，减慢了查询的效率！这样就会造成空间的浪费。

## java注解

元注解 注解的注解
@Target：注解的作用目标
@Retention：注解的生命周期
@Documented：注解是否应当被包含在 JavaDoc 文档中
@Inherited：是否允许子类继承该注解

@Target(value = {ElementType.FIELD})
ElementType.TYPE：允许被修饰的注解作用在类、接口和枚举上
ElementType.FIELD：允许作用在属性字段上
ElementType.METHOD：允许作用在方法上
ElementType.PARAMETER：允许作用在方法参数上
ElementType.CONSTRUCTOR：允许作用在构造器上
ElementType.LOCAL_VARIABLE：允许作用在本地局部变量上
ElementType.ANNOTATION_TYPE：允许作用在注解上
ElementType.PACKAGE：允许作用在包上

@Retention(value = RetentionPolicy.RUNTIME)
RetentionPolicy.SOURCE：当前注解编译期可见，不会写入 class 文件
RetentionPolicy.CLASS：类加载阶段丢弃，会写入 class 文件
RetentionPolicy.RUNTIME：永久保存，可以反射获取

## nio bio
**系统执行是需要空间切换的，比如recv，它用于从套接字上接收一个消息，因为是一个系统调用，所以调用时会从用户进程空间切换到内核空间运行一段时间再切换回来。
因为这里需要使用两个系统调用(select和recvfrom)，而blocking IO只调用了一个系统调用(recvfrom)。但是，用select的优势在于它可以同时处理多个connection。
（多说一句：所以，如果处理的连接数不是很高的话，使用select/epoll的web server不一定比使用multi-threading + blocking IO的web server性能更好，可能延迟还更大。
select/epoll的优势并不是对于单个连接能处理得更快，而是在于能处理更多的连接。**


阻塞就是一个线程对应一个套接字，也就是说如果服务器接听到连接请求，就会使用一个线程去负责后续工作。
那么如果没有数据读和写的话，那么一个处理线程也都是阻塞状态什么都不能做。
所以它适合的场景就是说连接固定或者较少，数据传输频繁的情况。 因为其实他是使用了一个（注意是一个）系统recvFrom指令，阻塞了内核准备准据和复制用户内存的两个过程。

缺点就是验证依赖线程：

线程的创建和销毁成本很高；
线程本身占用较大内存；
线程切换成本很高；
容易造成锯齿状的系统负载；

代码层面就是ServerSocket监听到一个连接就分派给线程池的一个线程。

非阻塞就是一个简称监听多个套接字，他需要两个指令select 和recvFrom
即客户端发送的连接请求都会注册到多路复用器上，多路复用器**轮询到连接有I/O请求时**才启动一个线程进行处理。
socket主要的读、写、注册和接受函数，在等待就绪阶段都是非阻塞的，真正的IO操作是同步阻塞的。
将每个连接(IO通道)都注册到Selector多路复用器上，告诉复用器我已经连接了，如果有IO事件发生，你就通知我。
Selector就不断地调用select函数，去访问每一个通道看那个通道有IO事件发生，如果发生了就通知，内核开启一个对应事件的线程去工作。

代码层面就是，创建server之后直接可以accept的，每次接受一个链接就使用线程处理。 
* [bio代码](https://gitee.com/Zalldios/jb-brother/tree/master/jb-basic/src/main/java/basic/io/bio)
* [nio代码](https://gitee.com/Zalldios/jb-brother/blob/master/jb-basic/src/main/java/basic/io/nio/demo02/server/ServerHandler.java)

第一步：
通过read系统调用想内核发起读请求
内核想硬件发送读指令，并等待读就绪
第二步：
3. 内核把将要读取的数据复制到描述符所指向的内核缓冲区中
4. 将数据从内核缓冲区拷贝到用户进程空间中


BIO 和 NIO 作为 Server 端，当建立了 10 个连接时，分别产生多少个线程？

答案： 因为传统的 IO 也就是 BIO 是同步线程堵塞的，所以每个连接都要分配一个专用线程来处理请求，这样 10 个连接就会创建 10 个线程去处理。
而 NIO 是一种同步非阻塞的 I/O 模型，它的核心技术是多路复用，可以使用一个链接上的不同通道来处理不同的请求，所以即使有 10 个连接，对于 NIO 来说，开启 1 个线程就够了。