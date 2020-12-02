## JAVA存储模型
[源码查看](http://hg.openjdk.java.net/jdk8u/jdk8u/hotspot/file/fba8dbd018a6/src/share/vm/oops)

### 对象（class instance）在内存中存储的布局可以分为3块区域：对象头（Header）、实例数据（Instance Data）和对齐填充（Padding）。

### `klass-oop`模型
HotSpot中采用了OOP-Klass模型，它是描述Java对象实例的模型，它分为两部分：

#### `klass`
类被加载到内存时，就被封装成了`klass`，`klass`包含类的元数据信息，像类的方法、常量池这些信息都是存在`klass`里的，你可以认为它是java里面的`java.lang.Class`对象，记录了类的全部信息；

#### `OOP`
**`Klass`是在class文件在加载过程中创建的，OOP则是在Java程序运行过程中new对象时创建的。**

`OOP`（Ordinary Object Pointer）指的是普通对象指针，它包含`MarkWord` 和元数据指针，`MarkWord`用来存储当前指针指向的对象运行时的一些状态数据；
元数据指针则指向`klass`,用来告诉你当前指针指向的对象是什么类型，也就是使用哪个类来创建出来的；
那么为何要设计这样一个一分为二的对象模型呢？
这是因为`HotSopt JVM`的设计者不想让每个对象中都含有一个`vtable`（虚函数表），所以就把对象模型拆成`klass`和`oop`，其中`oop`中不含有任何虚函数，而`klass`就含有虚函数表，可以进行method dispatch。

一个OOP对象包含以下几个部分：
- 对象头 （第一部分）
Mark Word，如哈希码（HashCode）、GC分代年龄、锁状态标志、线程持有的锁、偏向线程ID、偏向时间戳等，用于存储对象自身的运行时数据，
这部分数据的长度在32位和64位的虚拟机（未开启压缩指针）中分别为32bit和64bit，官方称它为“Mark Word”。

- 实例数据（第二部分）。
存储的是真正有效数据，如各种字段内容，各字段的分配策略为longs/doubles、ints、shorts/chars、bytes/boolean、oops(ordinary object pointers)，相同宽度的字段总是被分配到一起，便于之后取数据。 
父类定义的变量会出现在子类定义的变量的前面。实例数据部分是对象真正存储的有效信息，也是在程序代码中所定义的各种类型的字段内容。无论是从父类继承下来的，还是在子类中定义的，都需要记录起来。
对齐填充。仅仅起到占位符的作用，并非必须

- 数组长度（第三部分，只有数组对象有） 
如果对象是一个数组, 那在对象头中还必须有一块数据用于记录数组长度.

- 对齐填充（第三部分）
并不是必然存在的，也没有特别的含义，它仅仅起着占位符的作用。由于HotSpot VM的自动内存管理系统要求对象起始地址必须是8字节的整数倍，换句话说，就是对象的大小必须是8字节的整数倍。
而对象头部分正好是8字节的倍数（1倍或者2倍），因此，当对象`实例数据`部分没有对齐时，就需要通过对齐填充来补全。

OOP压缩内容:
• 每个Class的属性指针（静态成员变量）
• 每个对象的属性指针
• 普通对象数组的每个元素指针

#### 对象大小计算
- 在32位系统下，存放Class指针的空间大小是4字节，MarkWord是4字节，对象头为8字节。
- 在64位系统下，存放Class指针的空间大小是8字节，MarkWord是8字节，对象头为16字节。
- 64位开启指针压缩的情况下，存放Class指针的空间大小是4字节，MarkWord是8字节，对象头为12字节。
数组长度4字节+数组对象头8字节(对象引用4字节（未开启指针压缩的64位为8字节）+数组markword为4字节（64位未开启指针压缩的为8字节）)+对齐4=16字节。
- 静态属性不算在对象大小内。因为它的引用是在方法区。






## JVM的类型
### client和server类型
> 两个JVM是使用的不同编译器。Client JVM适合需要快速启动和较小内存空间的应用，它适合交互性的应用，比如GUI；
而Server JVM则是看重执行效率的应用的最佳选择。不同之处包括：编译策略、默认堆大小、内嵌策略。      
Client VM的编译器没有像Server VM一样执行许多复杂的优化算法，因此，它在分析和编译代码片段的时候更快。
而Server VM则包含了一个高级的编译器，该编译器支持许多和在C++编译器上执行的一样的优化，同时还包括许多传统的编译器无法实现的优化。

## jvm的工作模式
> 如果想要使用某个程序测试每一个工作模式的效率，启动参数添加如下
```
-server -showversion -Xint    #server类型的编译器使用 解释模式。
-server -showversion -Xcomp    #server类型的编译器使用 编译模式，启动十分缓慢。
-server -showversion -Xmixed    #server类型的编译器使用 混合模式。
-XX:+TieredCompilation -showversion -Xmixed    #编译的代码使用分层编译。
```

### -Xint代表解释模式(interpreted mode)
> -Xint标记会强制JVM以解释方式执行所有的字节码，当然这会降低运行速度，通常低10倍或更多。

### -Xcomp代表编译模式(compiled mode)
> 与它（-Xint）正好相反，JVM在第一次使用时会把所有的字节码编译成本地代码，从而带来最大程度的优化。这听起来不错，因为这完全绕开了缓慢的解释器。然而，很多应用在使用-Xcomp也会有一些性能损失，但是这比使用-Xint损失的少，原因是-Xcomp没有让JVM启用JIT编译器的全部功能，我们并没有看到-Xcomp比-Xmixed快多少。

#### （c1和c2的结合，client编译器和server编译器的结合）分层编译（-XX:+TieredCompilation)
> 除了纯编译和默认的mixed之外，jvm 从jdk6u25 之后，引入了分层编译。HotSpot 内置两种编译器，分别是client启动时的c1编译器和server启动时的c2编译器。
c2在将代码编译成机器代码的时候需要搜集大量的统计信息以便在编译的时候进行优化，因此编译出来的代码执行效率比较高，代价是程序启动时间比较长，而且需要执行比较长的时间，才能达到最高性能；
与之相反， c1的目标是使程序尽快进入编译执行的阶段，所以在编译前需要搜集的信息比c2要少，编译速度因此提高很多，但是付出的代价是编译之后的代码执行效率比较低，
但尽管如此，c1编译出来的代码在性能上比解释执行的性能已经有很大的提升，所以所谓的分层编译，就是一种折中方式，
在系统执行初期，执行频率比较高的代码先被c1编译器编译，以便尽快进入编译执行，然后随着时间的推移，执行频率较高的代码再被c2编译器编译，以达到最高的性能。

### -Xmixed代表混合模式(mixed mode)
> 混合模式是JVM的默认工作模式。它会同时使用编译模式和解释模式。对于字节码中多次被调用的部分，JVM会将其编译成本地代码以提高执行效率；而被调用很少（甚至只有一次）的方法在解释模式下会继续执行，从而减少编译和优化成本。JIT编译器在运行时创建方法使用文件，然后一步一步的优化每一个方法，有时候会主动的优化应用的行为。这些优化技术，比如积极的分支预测（optimistic branch prediction），如果不先分析应用就不能有效的使用。这样将频繁调用的部分提取出来，编译成本地代码，也就是在应用中构建某种热点（即HotSpot，这也是HotSpot JVM名字的由来）。使用混合模式可以获得最好的执行效率。

## JVM打印参数
> java -Xmx1024m -Xms1024m -XX:+UseConcMarkSweepGC -XX:+PrintFlagsFinal -version
> java -XX:+PrintFlagsInitial
> java -XX:+UnlockExperimentalVMOptions -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsFinal -version |findstr GC #解锁隐藏参数
> java -XX:+PrintCommandLineFlags $application_path_name #打印命令行参数
> jinfo -flags $pid #打印启动参数
:=代表重新赋值的。

## JVM有意义参数
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:/JAVA_TEST_DUMP.DUMP
> 如果我们没法为-Xmx（最大堆内存）设置一个合适的大小，那么就有可能面临内存溢出（OutOfMemoryError）的风险，这可能是我们使用JVM时面临的最可怕的猛兽之一。就同另外一篇关于这个主题的博文说的一样，导致内存溢出的根本原因需要仔细的定位。通常来说，分析堆内存快照（Heap Dump）是一个很好的定位手段，如果发生内存溢出时没有生成内存快照那就实在是太糟了，特别是对于那种JVM已经崩溃或者错误只出现在顺利运行了数小时甚至数天的生产系统上的情况。
幸运的是，我们可以通过设置-XX:+HeapDumpOnOutOfMemoryError 让JVM在发生内存溢出时自动的生成堆内存快照。有了这个参数，当我们不得不面对内存溢出异常的时候会节约大量的时间。默认情况下，堆内存快照会保存在JVM的启动目录下名为java_pid<pid>.hprof 的文件里（在这里<pid>就是JVM进程的进程号）。也可以通过设置-XX:HeapDumpPath=<path>来改变默认的堆内存快照生成路径，<path>可以是相对或者绝对路径。
虽然这一切听起来很不错，但有一点我们需要牢记。堆内存快照文件有可能很庞大，特别是当内存溢出错误发生的时候。因此，我们推荐将堆内存快照生成路径指定到一个拥有足够磁盘空间的地方      

-XX:OnOutOfMemoryError
> 当内存溢发生时，我们甚至可以可以执行一些指令，比如发个E-mail通知管理员或者执行一些清理工作。通过-XX:OnOutOfMemoryError 这个参数我们可以做到这一点，这个参数可以接受一串指令和它们的参数。在这里，我们将不会深入它的细节，但我们提供了它的一个例子。在下面的例子中，当内存溢出错误发生的时候，我们会将堆内存快照写到/tmp/heapdump.hprof 文件并且在JVM的运行目录执行脚本cleanup.sh
java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/heapdump.hprof -XX:OnOutOfMemoryError ="sh ~/cleanup.sh" MyApp

-XX:InitialCodeCacheSize and -XX:ReservedCodeCacheSize
> JVM一个有趣的，但往往被忽视的内存区域是“代码缓存”，它是用来存储已编译方法生成的本地代码。代码缓存确实很少引起性能问题，但是一旦发生其影响可能是毁灭性的。如果代码缓存被占满，JVM会打印出一条警告消息，并切换到interpreted-only 模式：JIT编译器被停用，字节码将不再会被编译成机器码。因此，应用程序将继续运行，但运行速度会降低一个数量级，直到有人注意到这个问题。就像其他内存区域一样，我们可以自定义代码缓存的大小。相关的参数是-XX:InitialCodeCacheSize 和-XX:ReservedCodeCacheSize，它们的参数和上面介绍的参数一样，都是字节值。

-XX:+UseCodeCacheFlushing
> 如果代码缓存不断增长，例如，因为热部署引起的内存泄漏，那么提高代码的缓存大小只会延缓其发生溢出。为了避免这种情况的发生，我们可以尝试一个有趣的新参数：当代码缓存被填满时让JVM放弃一些编译代码。通过使用-XX:+UseCodeCacheFlushing 这个参数，我们至少可以避免当代码缓存被填满的时候JVM切换到interpreted-only 模式。不过，我仍建议尽快解决代码缓存问题发生的根本原因，如找出内存泄漏并修复它。

## 垃圾回收机制的意义
> C++ 开发中管理内存是一个很麻烦的问题，而 Java 引入了垃圾回收机制，开发者不需要手动去管理内存的分配和回收问题，一切都交给 JVM 通过垃圾回收机制处理，同时有效的防止了内存泄漏的问题。
Java 语言规范中并没有明确的指定 JVM 使用哪种回收算法，但通常回收算法主要做 2 件事情：
- 发现无用的对象
- 回收被无用对象占用的内存空间

## 垃圾回收为什么要分代
> 分代的垃圾回收策略，是基于这样一个事实：不同的对象的生命周期是不一样的。因此，不同生命周期的对象可以采取不同的收集方式，以便提高回收效率。
在Java程序运行的过程中，会产生大量的对象，其中有些对象是与业务信息相关，比如Http请求中的Session对象、线程、Socket连接，这类对象跟业务直接挂钩，因此生命周期比较长。但是还有一些对象，主要是程序运行过程中生成的临时变量，这些对象生命周期会比较短，比如：String对象，由于其不变类的特性，系统会产生大量的这些对象，有些对象甚至只用一次即可回收。
试想，在不进行对象存活时间区分的情况下，每次垃圾回收都是对整个堆空间进行回收，花费时间相对会长，同时，因为每次回收都需要遍历所有存活对象，但实际上，对于生命周期长的对象而言，这种遍历是没有效果的，因为可能进行了很多次遍历，但是他们依旧存在。因此，分代垃圾回收采用分治的思想，进行代的划分，把不同生命周期的对象放在不同代上，不同代上采用最适合它的垃圾回收方式进行回收。

## 垃圾回收机制的区分
### 按照分区的方式
> 增量收集（Incremental Collecting）:实时垃圾回收算法，即：在应用进行的同时进行垃圾回收。   
分代收集（Generational Collecting）:基于对对象生命周期分析后得出的垃圾回收算法。把对象分为年青代、年老代、持久代，对不同生命周期的对象使用不同的算法（上述方式中的一个）进行回收。现在的垃圾回收器（从J2SE1.2开始）都是使用此算法的。
不过`java8`之后把持久代给消除了，`JDK8 HotSpot JVM`现在使用了本地内存来存储类元数据，被称为Metaspace。

### 按系统线程区分
- 串行收集:串行收集使用单线程处理所有垃圾回收工作，因为无需多线程交互，实现容易，而且效率比较高。但是，其局限性也比较明显，即无法使用多处理器的优势，所以此收集适合单处理器机器。当然，此收集器也可以用在小数据量（100M左右）情况下的多处理器机器上。
- 并行收集:并行收集使用多线程处理垃圾回收工作，因而速度快，效率高。而且理论上CPU数目越多，越能体现出并行收集器的优势。
- 并发收集:相对于串行收集和并行收集而言，前面两个在进行垃圾回收工作时，需要暂停整个运行环境，而只有垃圾回收程序在运行，因此，系统在垃圾回收时会有明显的暂停，而且暂停时间会因为堆越大而越长。

## 实现垃圾回收的机制
### 引用计数法
> 这个算法的实现是，给对象中添加一个引用计数器，每当一个地方引用这个对象时，计数器值+1；当引用失效时，计数器值-1。任何时刻计数值为0的对象就是不可能再被使用的。这种算法使用场景很多，但是，Java中却没有使用这种算法，因为这种算法很难解决对象之间相互引用的情况。

### 可达性分析法
> 这个算法的基本思想是通过一系列称为“GC Roots”的对象作为起始点，从这些节点向下搜索，搜索所走过的路径称为引用链，当一个对象到GC Roots没有任何引用链（即GC Roots到对象不可达）时，则证明此对象是不可用的。   
在Java语言中，可以作为GCRoots的对象包括下面几种：    
- 虚拟机栈（栈帧中的局部变量区，也叫做局部变量表）中引用的对象。
- 方法区中的类静态属性引用的对象。
- 方法区中常量引用的对象。
- 本地方法栈中JNI(Native方法)引用的对象。

## 永久代（又叫做方法区，JDK8之后移除了，更换为metaspace）的垃圾回收
> 方法区的垃圾回收主要回收两部分内容：1. 废弃常量。2. 无用的类。既然进行垃圾回收，就需要判断哪些是废弃常量，哪些是无用的类。      
如何判断废弃常量呢？以字面量回收为例，如果一个字符串“abc”已经进入常量池，但是当前系统没有任何一个String对象引用了叫做“abc”的字面量，那么，如果发生垃圾回收并且有必要时，“abc”就会被系统移出常量池。常量池中的其他类（接口）、方法、字段的符号引用也与此类似。   
如何判断无用的类呢？需要满足以下三个条件      
- 该类的所有实例都已经被回收，即Java堆中不存在该类的任何实例。   
- 加载该类的ClassLoader已经被回收。   
- 该类对应的java.lang.Class对象没有在任何地方被引用，无法在任何地方通过反射访问该类的方法。   
满足以上三个条件的类可以进行垃圾回收，但是并不是无用就被回收，虚拟机提供了一些参数供我们配置。

## 实现垃圾回收的算法
### 标记-清除（Mark-Sweep）算法  
> 这是最基础的算法，标记-清除算法就如同它的名字样，分为“标记”和“清除”两个阶段：首先标记出所有需要回收的对象，标记完成后统一回收所有被标记的对象。这种算法的不足主要体现在效率和空间，从效率的角度讲，标记和清除两个过程的效率都不高；从空间的角度讲，标记清除后会产生大量不连续的内存碎片， 内存碎片太多可能会导致以后程序运行过程中在需要分配较大对象时，无法找到足够的连续内存而不得不提前触发一次垃圾收集动作。 
  
![gc-mark-sweep](http://alldios-image.test.upcdn.net/note/gc-mark-sweep.png)

### 复制（Copying）算法
> 复制算法是为了解决效率问题而出现的，它将可用的内存分为两块，每次只用其中一块，当这一块内存用完了，就将还存活着的对象复制到另外一块上面，然后再把已经使用过的内存空间一次性清理掉。这样每次只需要对整个半区进行内存回收，内存分配时也不需要考虑内存碎片等复杂情况，只需要移动指针，按照顺序分配即可。
缺点：内存缩小为了原来的一半，这样代价太高了。现在的商用虚拟机都采用这种算法来回收新生代，不过研究表明1:1的比例非常不科学，因此新生代的内存被划分为一块较大的Eden空间和两块较小的Survivor空间，每次使用Eden和其中一块Survivor。每次回收时，将Eden和Survivor中还存活着的对象一次性复制到另外一块Survivor空间上，最后清理掉Eden和刚才用过的Survivor空间。HotSpot虚拟机默认Eden区和Survivor区的比例为8:1，意思是每次新生代中可用内存空间为整个新生代容量的90%。当然，我们没有办法保证每次回收都只有不多于10%的对象存活，当Survivor空间不够用时，需要依赖老年代进行分配担保（Handle Promotion）。

![gc-mark-sweep](http://alldios-image.test.upcdn.net/note/gc-copying.png)

### 标记-整理（Mark-Compact）算法
> 复制算法在对象存活率较高的场景下要进行大量的复制操作，效率很低。万一对象100%存活，那么需要有额外的空间进行分配担保。老年代都是不易被回收的对象，对象存活率高，因此一般不能直接选用复制算法。根据老年代的特点，有人提出了另外一种标记-整理算法，过程与标记-清除算法一样，不过不是直接对可回收对象进行清理，而是让所有存活对象都向一端移动，然后直接清理掉边界以外的内存。标记-整理算法的工作过程如图：

![gc-mark-sweep](http://alldios-image.test.upcdn.net/note/gc-mark-sweep.png)


## jVM的分代
![gc-mark-sweep](http://alldios-image.test.upcdn.net/note/vm-generation.png)
> 虚拟机中的共划分为三个代：年轻代（Young Generation）、年老点（Old Generation）和持久代（Permanent Generation）。其中持久代主要存放的是Java类的类信息，与垃圾收集要收集的Java对象关系不大。年轻代和年老代的划分是对垃圾收集影响比较大的。

### 年轻代
> 所有新生成的对象首先都是放在年轻代的。年轻代的目标就是尽可能快速的收集掉那些生命周期短的对象。年轻代分三个区。一个Eden区，两个Survivor区(一般而言)。大部分对象在Eden区中生成。当Eden区满时，还存活的对象将被复制到Survivor区（两个中的一个），当这个Survivor区满时，此区的存活对象将被复制到另外一个Survivor区，当这个Survivor去也满了的时候，从第一个Survivor区复制过来的并且此时还存活的对象，将被复制“年老区(Tenured)”。需要注意，Survivor的两个区是对称的，没先后关系，所以同一个区中可能同时存在从Eden复制过来 对象，和从前一个Survivor复制过来的对象，而复制到年老区的只有从第一个Survivor去过来的对象。而且，Survivor区总有一个是空的。同时，根据程序需要，Survivor区是可以配置为多个的（多于两个），这样可以增加对象在年轻代中的存在时间，减少被放到年老代的可能。

### 年老代:
> 在年轻代中经历了N次垃圾回收后仍然存活的对象，就会被放到年老代中。因此，可以认为年老代中存放的都是一些生命周期较长的对象。

### 永久代:
> 存放静态文件，如今Java类、方法等。持久代对垃圾回收没有显著影响，但是有些应用可能动态生成或者调用一些class，例如Hibernate等，在这种时候需要设置一个比较大的持久代空间来存放这些运行过程中新增的类。持久代大小通过-XX:MaxPermSize=<N>进行设置。

## jvm什么时候触发垃圾回收？
> 由于对象进行了分代处理，因此垃圾回收区域、时间也不一样。GC有两种类型：Scavenge GC和Full GC。

### Scavenge GC
> 一般情况下，当新对象生成，并且在Eden申请空间失败时，就会触发Scavenge GC，对Eden区域进行GC，清除非存活对象，并且把尚且存活的对象移动到Survivor区。然后整理Survivor的两个区。这种方式的GC是对年轻代的Eden区进行，不会影响到年老代。因为大部分对象都是从Eden区开始的，同时Eden区不会分配的很大，所以Eden区的GC会频繁进行。因而，一般在这里需要使用速度快、效率高的算法，使Eden去能尽快空闲出来。

### Full GC
> 对整个堆进行整理，包括Young、Tenured和Perm。Full GC因为需要对整个对进行回收，所以比Scavenge GC要慢，因此应该尽可能减少Full GC的次数。在对JVM调优的过程中，很大一部分工作就是对于FullGC的调节。有如下原因可能导致Full GC：
- 年老代（Tenured）被写满
- 持久代（Perm）被写满
- System.gc()被显示调用

## 如何选择合适的垃圾回收算法
### 串行收集器
> 用单线程处理所有垃圾回收工作，因为无需多线程交互，所以效率比较高。但是，也无法使用多处理器的优势，所以此收集器适合单处理器机器。当然，此收集器也可以用在小数据量（100M左右）情况下的多处理器机器上。可以使用`-XX:+UseSerialGC`打开。

– 适用情况：数据量比较小（100M左右）；单处理器下并且对响应时间无要求的应用。   
– 缺点：只能用于小型应用

### 并行收集器
> 对年轻代进行并行垃圾回收，因此可以减少垃圾回收时间。一般在多线程多处理器机器上使用。使用`-XX:+UseParallelGC`打开。并行收集器在J2SE5.0第六6更新上引入，在Java SE6.0中进行了增强–可以对年老代进行并行收集。如果年老代不使用并发收集的话，默认是使用单线程进行垃圾回收，因此会制约扩展能力。使用`-XX:+UseParallelOldGC`打开。
使用`-XX:ParallelGCThreads=<N>`设置并行垃圾回收的线程数。此值可以设置与机器处理器数量相等。
此收集器可以进行如下配置：
最大垃圾回收暂停:指定垃圾回收时的最长暂停时间，通过`-XX:MaxGCPauseMillis=<N>`指定。<N>为毫秒.如果指定了此值的话，堆大小和垃圾回收相关参数会进行调整以达到指定值。设定此值可能会减少应用的吞吐量。
吞吐量:吞吐量为垃圾回收时间与非垃圾回收时间的比值，通过`-XX:GCTimeRatio=<N>`来设定，公式为1/（1+N）。例如，`-XX:GCTimeRatio=19`时，表示5%的时间用于垃圾回收。默认情况为99，即1%的时间用于垃圾回收。 

– 适用情况：“对吞吐量有高要求”，多CPU、对应用响应时间无要求的中、大型应用。举例：后台处理、科学计算。   
– 缺点：垃圾收集过程中应用响应时间可能加长

### 并发收集器
> 可以保证大部分工作都并发进行（应用不停止），垃圾回收只暂停很少的时间，此收集器适合对响应时间要求比较高的中、大规模应用。使用`-XX:+UseConcMarkSweepGC`打开。
并发收集器主要减少年老代的暂停时间，他在应用不停止的情况下使用独立的垃圾回收线程，跟踪可达对象。在每个年老代垃圾回收周期中，在收集初期并发收集器 会对整个应用进行简短的暂停，在收集中还会再暂停一次。第二次暂停会比第一次稍长，在此过程中多个线程同时进行垃圾回收工作。
并发收集器使用处理器换来短暂的停顿时间。在一个N个处理器的系统上，并发收集部分使用K/N个可用处理器进行回收，一般情况下1<=K<=N/4。   
在只有一个处理器的主机上使用并发收集器，设置为incremental mode模式也可获得较短的停顿时间。  
 
#### 浮动垃圾：
> 由于在应用运行的同时进行垃圾回收，所以有些垃圾可能在垃圾回收进行完成时产生，这样就造成了“Floating Garbage”，这些垃圾需要在下次垃圾回收周期时才能回收掉。所以，并发收集器一般需要20%的预留空间用于这些浮动垃圾。

#### Concurrent Mode Failure
> 并发收集器在应用运行时进行收集，所以需要保证堆在垃圾回收的这段时间有足够的空间供程序使用，否则，垃圾回收还未完成，堆空间先满了。这种情况下将会发生“并发模式失败”，此时整个应用将会暂停，进行垃圾回收。

#### 启动并发收集器
> 因为并发收集在应用运行时进行收集，所以必须保证收集完成之前有足够的内存空间供程序使用，否则会出现“Concurrent Mode Failure”。通过设置`-XX:CMSInitiatingOccupancyFraction=<N>`指定还有多少剩余堆时开始执行并发收集

– 适用情况：“对响应时间有高要求”，多CPU、对应用响应时间有较高要求的中、大型应用。举例：Web服务器/应用服务器、电信交换、集成开发环境。

## jvm参数设置
### 堆大小设置
> 典型设置：java -Xmx3550m -Xms3550m -Xss128k -XX:NewRatio=4 -XX:SurvivorRatio=4 -XX:MaxPermSize=16m -XX:MaxTenuringThreshold=0
- `-Xmx3550m`：设置JVM最大可用内存为3550M。
- `-Xms3550m`：设置JVM促使内存为3550m。此值可以设置与-Xmx相同，以避免每次垃圾回收完成后JVM重新分配内存。
- `Xmn2g`：设置年轻代大小为2G。整个堆大小=年轻代大小 + 年老代大小 + 持久代大小。持久代一般固定大小为64m，所以增大年轻代后，将会减小年老代大小。此值对系统性能影响较大，Sun官方推荐配置为整个堆的3/8。
- `Xss128k`：设置每个线程的堆栈大小。JDK5.0以后每个线程堆栈大小为1M，以前每个线程堆栈大小为256K。更具应用的线程所需内存大小进行调整。在相同物理内存下，减小这个值能生成更多的线程。但是操作系统对一个进程内的线程数还是有限制的，不能无限生成，经验值在3000~5000左右。
- `-XX:NewRatio=4`:设置年轻代（包括Eden和两个Survivor区）与年老代的比值（除去持久代）。设置为4，则年轻代与年老代所占比值为1：4，年轻代占整个堆栈的1/5
- `-XX:SurvivorRatio=4`：设置年轻代中Eden区与Survivor区的大小比值。设置为4，则两个Survivor区与一个Eden区的比值为2:4，一个Survivor区占整个年轻代的1/6
- `-XX:MaxPermSize=16m`:设置持久代大小为16m。
- `-XX:MaxTenuringThreshold=0`：设置垃圾最大年龄。如果设置为0的话，则年轻代对象不经过Survivor区，直接进入年老代。对于年老代比较多的应用，可以提高效率。如果将此值设置为一个较大值，则年轻代对象会在Survivor区进行多次复制，这样可以增加对象再年轻代的存活时间，增加在年轻代即被回收的概论。 

### 回收收集器选择
> 典型配置，适用于吞吐量优先的并行收集器：java -Xmx3800m -Xms3800m -Xmn2g -Xss128k -XX:+UseParallelGC -XX:ParallelGCThreads=20
- -XX:+UseParallelGC：选择垃圾收集器为并行收集器。此配置仅对年轻代有效。即上述配置下，年轻代使用并发收集，而年老代仍旧使用串行收集。
- -XX:ParallelGCThreads=20：配置并行收集器的线程数，即：同时多少个线程一起进行垃圾回收。此值最好配置与处理器数目相等。
> 典型配置，适用于吞吐量优先的并行收集器：java -Xmx3550m -Xms3550m -Xmn2g -Xss128k -XX:+UseParallelGC -XX:ParallelGCThreads=20 -XX:+UseParallelOldGC
- -XX:+UseParallelOldGC：配置年老代垃圾收集方式为并行收集。JDK6.0支持对年老代并行收集。
> 典型配置，适用于吞吐量优先的并行收集器：java -Xmx3550m -Xms3550m -Xmn2g -Xss128k -XX:+UseParallelGC  -XX:MaxGCPauseMillis=100
- `-XX:MaxGCPauseMillis=100`:设置每次年轻代垃圾回收的最长时间，如果无法满足此时间，JVM会自动调整年轻代大小，以满足此值
> 典型配置，适用于吞吐量优先的并行收集器：java -Xmx3550m -Xms3550m -Xmn2g -Xss128k -XX:+UseParallelGC  -XX:MaxGCPauseMillis=100 -XX:+UseAdaptiveSizePolicy
- `-XX:+UseAdaptiveSizePolicy`：设置此选项后，并行收集器会自动选择年轻代区大小和相应的Survivor区比例，以达到目标系统规定的最低相应时间或者收集频率等，此值建议使用并行收集器时，一直打开。

> 典型配置，适用于响应时间优先的并发收集器：java -Xmx3550m -Xms3550m -Xmn2g -Xss128k -XX:ParallelGCThreads=20 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC
-XX:+UseConcMarkSweepGC：设置年老代为并发收集。测试中配置这个以后，-XX:NewRatio=4的配置失效了，原因不明。所以，此时年轻代大小最好用-Xmn设置。
-XX:+UseParNewGC: 设置年轻代为并行收集。可与CMS收集同时使用。JDK5.0以上，JVM会根据系统配置自行设置，所以无需再设置此值。
> 典型配置，适用于响应时间优先的并发收集器：java -Xmx3550m -Xms3550m -Xmn2g -Xss128k -XX:+UseConcMarkSweepGC -XX:CMSFullGCsBeforeCompaction=5 -XX:+UseCMSCompactAtFullCollection
-XX:CMSFullGCsBeforeCompaction：由于并发收集器不对内存空间进行压缩、整理，所以运行一段时间以后会产生“碎片”，使得运行效率降低。此值设置运行多少次GC以后对内存空间进行压缩、整理。
-XX:+UseCMSCompactAtFullCollection：打开对年老代的压缩。可能会影响性能，但是可以消除碎片 

### 堆和回收器配置汇总
#### 堆设置
```` json
-Xms:初始堆大小
-Xmx:最大堆大小
-XX:NewSize=n:设置年轻代大小
-XX:NewRatio=n:设置年轻代和年老代的比值。如:为3，表示年轻代与年老代比值为1：3，年轻代占整个年轻代年老代和的1/4
-XX:SurvivorRatio=n:年轻代中Eden区与两个Survivor区的比值。注意Survivor区有两个。如：3，表示Eden：Survivor=3：2，一个Survivor区占整个年轻代的1/5
-XX:MaxPermSize=n:设置持久代大小
````

#### 收集器设置
``` json
-XX:+UseSerialGC:设置串行收集器
-XX:+UseParallelGC:设置并行收集器
-XX:+UseParalledlOldGC:设置并行年老代收集器
-XX:+UseConcMarkSweepGC:设置并发收集器
```

#### 垃圾回收统计信息
```` json
-XX:+PrintGCDetails  
-XX:+PrintHeapAtGC  
-XX:+PrintGCDateStamps  
-XX:+PrintTenuringDistribution  
-verbose:gc  
-Xloggc:filename

WINDOWS:set JAVA_OPTS=-XX:+PrintGCDetails -XX:+PrintHeapAtGC -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution -verbose:gc -Xloggc:%CURRENT_DIR%/gc.log   
````

#### 并行收集器设置
```` json
-XX:ParallelGCThreads=n:设置并行收集器收集时使用的CPU数。并行收集线程数。
-XX:MaxGCPauseMillis=n:设置并行收集最大暂停时间
-XX:GCTimeRatio=n:设置垃圾回收时间占程序运行时间的百分比。公式为1/(1+n)
````

#### 并发收集器设置
```` json
-XX:+CMSIncrementalMode:设置为增量模式。适用于单CPU情况。
-XX:ParallelGCThreads=n:设置并发收集器年轻代收集方式为并行收集时，使用的CPU数。并行收集线程数。
````

### 调优总结
#### 年轻代大小选择
> 响应时间优先的应用：尽可能设大，直到接近系统的最低响应时间限制（根据实际情况选择）。在此种情况下，年轻代收集发生的频率也是最小的。同时，减少到达年老代的对象。   
吞吐量优先的应用：尽可能的设置大，可能到达Gbit的程度。因为对响应时间没有要求，垃圾收集可以并行进行，一般适合8CPU以上的应用。

#### 年老代大小选择
> 响应时间优先的应用：年老代使用并发收集器，所以其大小需要小心设置，一般要考虑并发会话率和会话持续时间等一些参数。如果堆设置小了，可以会造成内存碎片、高回收频率以及应用暂停而使用传统的标记清除方式；如果堆大了，则需要较长的收集时间。最优化的方案，一般需要参考以下数据获得：
- 并发垃圾收集信息
- 持久代并发收集次数
- 传统GC信息
- 花在年轻代和年老代回收上的时间比例
> 减少年轻代和年老代花费的时间，一般会提高应用的效率  
吞吐量优先的应用：
一般吞吐量优先的应用都有一个很大的年轻代和一个较小的年老代。原因是，这样可以尽可能回收掉大部分短期对象，减少中期的对象，而年老代尽存放长期存活对象。
较小堆引起的碎片问题
因为年老代的并发收集器使用标记、清除算法，所以不会对堆进行压缩。当收集器回收时，他会把相邻的空间进行合并，这样可以分配给较大的对象。但是，当堆空间较小时，运行一段时间以后，就会出现“碎片”，如果并发收集器找不到足够的空间，那么并发收集器将会停止，然后使用传统的标记、清除方式进行回收。如果出现“碎片”，可能需要进行如下配置：
``` json
1. -XX:+UseCMSCompactAtFullCollection：使用并发收集器时，开启对年老代的压缩。
2. -XX:CMSFullGCsBeforeCompaction=0：上面配置开启的情况下，这里设置多少次Full GC后，对年老代进行压缩
```

# 性能篇参数
## 建议的性能参数
### 取消偏向锁 -XX:-UseBiasedLocking
> JDK1.6开始默认打开的偏向锁，会尝试把锁赋给第一个访问它的线程，取消同步块上的synchronized原语。如果始终只有一条线程在访问它，就成功略过同步操作以获得性能提升。
但一旦有第二条线程访问这把锁，JVM就要撤销偏向锁恢复到未锁定线程的状态，如果打开安全点日志，可以看到不少RevokeBiasd的纪录，像GC一样Stop The World的干活，虽然只是很短的停顿，但对于多线程并发的应用，取消掉它反而有性能的提升，所以Cassandra就取消了它。

### 加大Integer Cache -XX:AutoBoxCacheMax=20000
> Integer i=3;这语句有着 int自动装箱成Integer的过程，JDK默认只缓存 -128 ~ +127的Integer 和 Long，超出范围的数字就要即时构建新的Integer对象。设为20000后，我们应用的QPS有足足4%的影响。为什么是2万呢，因为-XX:+AggressiveOpts里也是这个值。
     
### 启动时访问并置零内存页面 -XX:+AlwaysPreTouch
> 启动时就把参数里说好了的内存全部舔一遍，可能令得启动时慢上一点，但后面访问时会更流畅，比如页面会连续分配，比如不会在晋升新生代到老生代时才去访问页面使得GC停顿时间加长。ElasticSearch和Cassandra都打开了它。
     
### SecureRandom生成加速 -Djava.security.egd=file:/dev/./urandom
> 此江湖偏方原因为Tomcat的SecureRandom显式使用SHA1PRNG算法时，初始因子默认从/dev/random读取会存在堵塞。额外效果是SecureRandom的默认算法也变成合适的SHA1了。

## 可选的性能参数
### -XX:+PerfDisableSharedMem
> Cassandra家的一个参数，一直没留意，直到发生高IO时的JVM停顿。原来JVM经常会默默的在/tmp/hperf 目录写上一点statistics数据，如果刚好遇到PageCache刷盘，把文件阻塞了，就不能结束这个Stop the World的安全点了。
禁止JVM写statistics数据的代价，是jps和jstat 用不了，只能用JMX，而JMX取新老生代的使用百分比还真没jstat方便，VJTools VJTools里的vjmxcli弥补了这一点。详见The Four Month Bug: JVM statistics cause garbage collection pauses

### -XX:-UseCounterDecay
> 禁止JIT调用计数器衰减。默认情况下，每次GC时会对调用计数器进行砍半的操作，导致有些方法一直温热，永远都达不到触发C2编译的1万次的阀值。

### -XX:-TieredCompilation
> 多层编译是JDK8后默认打开的比较骄傲的功能，先以C1静态编译，采样足够后C2编译。
但我们实测，性能最终略降2%，可能是因为有些方法C1编译后C2不再编译了。应用启动时的偶发服务超时也多了，可能是忙于编译。所以我们将它禁止了，但记得打开前面的-XX:-UseCounterDecay，避免有些温热的方法永远都要解释执行。

## 不建议的性能参数
### -XX:+AggressiveOpts
> 一些还没默认打开的优化参数集合, -XX:AutoBoxCacheMax是其中的一项。但如前所述，关键系统里不建议打开。
虽然通过-XX:+AggressiveOpts 与 -XX:-AggressiveOpts 的对比，目前才改变了三个参数，但为免以后某个版本的JDK里默默改变更多激进的配置，还是不要打开了。

### JIT Compile相关的参数，函数调用多少次之后开始编译的阀值，内联函数大小的阀值等等，不要乱改。

### -server，在64位多核的linux中，你想设成-client都不行的，所以写了也是白写。

# 内存与GC篇
## GC策略
>为了稳健，还是8G以下的堆还是CMS好了，G1现在虽然是默认了，但其实在小堆里的表现也没有比CMS好，还是JDK11的ZGC引人期待。
### CMS基本写法
-XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly
因为我们的监控系统会通过JMX监控内存达到90%的状况，所以设置让它75%就开始跑了，早点开始也能减少Full GC等意外情况(概念重申，这种主动的CMS GC，和JVM的老生代、永久代、堆外内存完全不能分配内存了而强制Full GC是不同的概念)。
为了让这个设置生效，还要设置-XX:+UseCMSInitiatingOccupancyOnly，否则75％只被用来做开始的参考值，后面还是JVM自己算。
 
### -XX:MaxTenuringThreshold=2
> 这是改动效果最明显的一个参数了。对象在Survivor区最多熬过多少次Young GC后晋升到年老代，JDK8里CMS 默认是6，其他如G1是15。
Young GC是最大的应用停顿来源，而新生代里GC后存活对象的多少又直接影响停顿的时间，所以如果清楚Young GC的执行频率和应用里大部分临时对象的最长生命周期，可以把它设的更短一点，让其实不是临时对象的新生代对象赶紧晋升到年老代，别呆着。
用-XX:+PrintTenuringDistribution观察下，如果后面几代的大小总是差不多，证明过了某个年龄后的对象总能晋升到老生代，就可以把晋升阈值设小，比如JMeter里2就足够了。
 
### -XX:+ExplicitGCInvokesConcurrent 但不要-XX:+DisableExplicitGC
> full gc时，使用CMS算法，不是全程停顿，必选。
但像R大说的，System GC是保护机制（如堆外内存满时清理它的堆内引用对象），禁了system.gc() 未必是好事，只要没用什么特别烂的类库，真有人调了总有调的原因，所以不应该加这个烂大街的参数。

### ParallelRefProcEnabled 和 CMSParallelInitialMarkEnabled
> 并行的处理Reference对象，如WeakReference，默认为false，除非在GC log里出现Reference处理时间较长的日志，否则效果不会很明显，但我们总是要JVM尽量的并行，所以设了也就设了。
同理还有-XX:+CMSParallelInitialMarkEnabled，JDK8已默认开启，但小版本比较低的JDK7甚至不支持。
 
### ParGCCardsPerStrideChunk
> Linkined的黑科技， 上一个版本的文章不建议打开，后来发现有些场景的确能减少YGC时间，详见难道他们说的都是真的，简单说就是影响YGC时扫描老生代的时间，默认值256太小了，但32K也未必对，需要自己试验。
-XX:+UnlockDiagnosticVMOptions -XX: ParGCCardsPerStrideChunk=1024
 
## 可选的GC参数
### 并发收集线程数
> ParallelGCThreads＝8+( Processor - 8 ) ( 5/8 )；
ConcGCThreads = (ParallelGCThreads + 3)/4
比如双CPU，六核，超线程就是24个处理器，小于8个处理器时ParallelGCThreads按处理器数量，大于时按上述公式YGC线程数＝18， CMS GC线程数＝5。

> CMS GC线程数的公式太怪，也有人提议简单改为YGC线程数的1/2。
一些不在乎停顿时间的后台辅助程序，比如日志收集的logstash，建议把它减少到2，避免在GC时突然占用太多CPU核，影响主应用。
而另一些并不独占服务器的应用，比如旁边跑着一堆sidecar的，也建议减少YGC线程数。
一个真实的案例，24核的服务器，默认18条YGC线程，但因为旁边有个繁忙的Service Mesh Proxy在跑着，这18条线程并不能100%的抢到CPU，出现了不合理的慢GC。把线程数降低到12条之后，YGC反而快了很多。 所以那些贪心的把YGC线程数＝CPU 核数的，通常弄巧成拙。
 
### -XX:－CMSClassUnloadingEnabled
> 在CMS中清理永久代中的过期的Class而不等到Full GC，JDK7默认关闭而JDK8打开。看自己情况，比如有没有运行动态语言脚本如Groovy产生大量的临时类。它有时会大大增加CMS的暂停时间。所以如果新类加载并不频繁，这个参数还是显式关闭的好。
 
### -XX:+CMSScavengeBeforeRemark
> 默认为关闭，在CMS remark前，先执行一次minor GC将新生代清掉，这样从老生代的对象引用到的新生代对象的个数就少了，停止全世界的CMS remark阶段就短一些。如果打开了，会让一次YGC紧接着一次CMS GC，使得停顿的总时间加长了。
又一个真实案例，CMS GC的时间和当时新生代的大小成比例，新生代很小时很快完成，新生代80％时CMS GC停顿时间超过一秒，这时候就还是打开了划算。

## 不建议的GC参数
### -XX:+UseParNewGC
> 用了CMS，新生代收集默认就是，不用自己设。
 
### -XX:CMSFullGCsBeforeCompaction
> 默认为0，即每次full gc都对老生代进行碎片整理压缩。Full GC 不同于 老生代75%时触发的CMS GC，只在老生代达到100%，老生代碎片过大无法分配空间给新晋升的大对象，堆外内存满，这些特殊情况里发生，所以设为每次都进行碎片整理是合适的，详见此贴里R大的解释。
 
### -XX:+GCLockerInvokesConcurrent
> 我们犯过的错，不是所有Concurrent字样的参数都是好参数，加上之后，原本遇上JNI GCLocker只需要补偿YGC就够的，变成要执行YGC ＋ CMS GC了。

##  内存大小的设置
>其实JVM除了显式设置的-Xmx堆内存，还有一堆其他占内存的地方(堆外内存，线程栈，永久代，二进制代码cache)，在容量规划的时候要留意。
关键业务系统的服务器上内存一般都是够的，所以尽管设得宽松点。

### -Xmx, -Xms,
> 堆内存大小，2～4G均可。
 
### -Xmn or -XX:NewSize or -XX:NewRatio
> JDK默认新生代占堆大小的1/3， 个人喜欢把对半分， 因为增大新生代能减少GC的频率，如果老生代里没多少长期对象的话，占2/3通常太多了。可以用-Xmn 直接赋值(等于-XX:NewSize and -XX:MaxNewSize同值的缩写)，或把NewRatio设为1来对半分。
 
### -XX: PermSize=128m -XX:MaxPermSize=512m （JDK7）
> -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m（JDK8）
现在的应用有Hibernate/Spring这些闹腾的家伙AOP之后类都比较多，可以一开始就把初始值从64M设到128M（否则第一次自动扩张会造成大约3秒的JVM停顿），并设一个更大的Max值以求保险。
JDK8的永生代几乎可用完机器的所有内存，同样设一个128M的初始值，512M的最大值保护一下。

### -Xss
> 在堆之外，线程占用栈内存，默认每条线程为1M（以前是256K）。存放方法调用出参入参的栈，局部变量，标量替换后掉局部变量等，有人喜欢把它设回256k，节约内存并开更多线程，有人则会在遇到错误后把它再设大点，特别是有很深的JSON解析之类的递归调用时。

### -XX:SurvivorRatio
> 新生代中每个存活区的大小，默认为8，即1/10的新生代 1/(SurvivorRatio+2)，有人喜欢设小点省点给新生代如Cassandra，但要避免太小使得存活区放不下临时对象而被迫晋升到老生代，还是从GC日志里看实际情况了。

### -XX:MaxDirectMemorySize
> 堆外内存的最大值，默认为Heap区总内存减去一个Survivor区的大小，详见Netty之堆外内存扫盲篇，如果肯定用不了这么多，也可以把它主动设小，来获得一个比较清晰内存占用预估值，特别是在容器里。

### -XX:ReservedCodeCacheSize
> JIT编译后二进制代码的存放区，满了之后就不再编译，对性能影响很大。 JDK7默认不开多层编译48M，开了96M，而JDK8默认开多层编译240M。可以在JMX里看看CodeCache的占用情况，也可以用VJTools里的vjtop来看，JDK7下默认的48M可以设大点，不抠这么点。

# 监控篇
> JVM输出的各种日志，如果未指定路径，通常会生成到运行应用的相同目录，为了避免有时候在不同的地方执行启动脚本，一般将日志路径集中设到一个固定的地方。

## 监控建议配置
### -XX:+PrintCommandLineFlags
> 运维有时会对启动参数做一些临时的更改，将每次启动的参数输出到stdout，将来有据可查。
打印出来的是命令行里设置了的参数以及因为这些参数隐式影响的参数，比如开了CMS后，-XX:+UseParNewGC也被自动打开。

### -XX:-OmitStackTraceInFastThrow
> 为异常设置StackTrace是个昂贵的操作，所以当应用在相同地方抛出相同的异常N次(两万?)之后，JVM会对某些特定异常如NPE，数组越界等进行优化，不再带上异常栈。此时，你可能会看到日志里一条条Nul Point Exception，而之前输出完整栈的日志早被滚动到不知哪里去了，也就完全不知道这NPE发生在什么地方，欲哭无泪。 所以，将它禁止吧，ElasticSearch也这样干。
 
### Crash文件
#### -XX:ErrorFile
> JVM crash时，hotspot 会生成一个error文件，提供JVM状态信息的细节。如前所述，将其输出到固定目录，避免到时会到处找这文件。文件名中的%p会被自动替换为应用的PID
-XX:ErrorFile=${MYLOGDIR}/hs_err_%p.log

#### coredump
> 当然，更好的做法是生成coredump，从CoreDump能够转出Heap Dump 和 Thread Dump 还有crash的地方，非常实用。
在启动脚本里加上 ulimit -c unlimited或其他的设置方式，如果有root权限，设一下输出目录更好
echo "/{MYLOGDIR}/coredump.%p" > /proc/sys/kernel/core_pattern
什么？你不知道coredump有什么用？看来你是没遇过JVM Segment Fault的幸福人。
 
#### -XX:+HeapDumpOnOutOfMemoryError(可选)
> 在Out Of Memory，JVM快死掉的时候，输出Heap Dump到指定文件。不然开发很多时候还真不知道怎么重现错误。
路径只指向目录，JVM会保持文件名的唯一性，叫java_pid${pid}.hprof。因为如果指向文件，而文件已存在，反而不能写入。
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${LOGDIR}/
但在容器环境下，输出4G的HeapDump，在普通硬盘上会造成20秒以上的硬盘IO跑满，也是个十足的恶邻，影响了同一宿主机上所有其他的容器。

### GC日志
> JDK9完全不一样了，这里还是写JDK7/8的配置。

#### 基本配置
#####  -Xloggc:/dev/shm/gc-myapp.log -XX:+PrintGCDateStamps -XX:+PrintGCDetails
> 有人担心写GC日志会影响性能，但测试下来实在没什么影响，GC问题是Java里最常见的问题，没日志怎么行。
后来又发现如果遇上高IO的情况，GC时操作系统正在flush pageCache 到磁盘，也可能导致GC log文件被锁住，从而让GC结束不了。所以把它指向了/dev/shm 这种内存中文件系统，避免这种停顿，详见Eliminating Large JVM GC Pauses Caused by Background IO Traffic
用PrintGCDateStamps而不是PrintGCTimeStamps，打印可读的日期而不是时间戳。
 
##### -XX:+PrintGCApplicationStoppedTime
> 这是个非常非常重要的参数，但它的名字没起好，其实除了打印清晰的完整的GC停顿时间外，还可以打印其他的JVM停顿时间，比如取消偏向锁，class 被agent redefine，code deoptimization等等，有助于发现一些原来没想到的问题。如果真的发现了一些不知是什么的停顿，需要打印安全点日志找原因（见后）。

##### -XX:+PrintGCCause
> 打印产生GC的原因，比如AllocationFailure什么的，在JDK8已默认打开，JDK7要显式打开一下。
 
##### -XX:+PrintPromotionFailure
> 打开了就知道是多大的新生代对象晋升到老生代失败从而引发Full GC时的。
 
##### GC日志滚动与备份
> GC日志默认会在重启后清空，有人担心长期运行的应用会把文件弄得很大，所以"-XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=1M"的参数可以让日志滚动起来。
但真正用起来重启后的文件名太混乱太让人头痛，GC日志再大也达不到哪里去，所以我们没有加滚动，而且自行在启动脚本里对旧日志做备份。

##### 安全点日志
> 如果GC日志里有非GC的JVM停顿时间，你得打出安全点日志来知道详情。
-XX:+PrintSafepointStatistics -XX: PrintSafepointStatisticsCount=1 -XX:+UnlockDiagnosticVMOptions -XX:- DisplayVMOutput -XX:+LogVMOutput -XX:LogFile=/dev/shm/vm-myapp.log

##### JMX
> -Dcom.sun.management.jmxremote.port=7001 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=127.0.0.1
以上设置，只让本地的Zabbix之类监控软件通过JMX监控JVM，不允许远程访问。

如果应用忘记了加上述参数，又不想改参数重启服务，可以用VJTools的vjmxcli来救急，它能通过PID直接连入目标JVM打开JMX。

G1收集器：http://www.importnew.com/19269.html

##  javaDirectBuffer的内存回收

触发堆外内存回收的时机是通过Cleaner对象的clean方法进行回收。在每次新建一个DirectBuffer对象的时候，会同时创建一个Cleaner对象，
同一个进程创建的所有的DirectBuffer对象跟Cleaner对象的个数是一样的，并且所有的Cleaner对象会组成一个链表，前后相连。
Cleaner对象的clean方法执行时机是JVM在判断该Cleaner对象关联的DirectBuffer已经不被任何对象引用了(也就是经过可达性分析判定为不可达的时候)。
此时Cleaner对象会被JVM挂到PendingList上。然后有一个固定的线程扫描这个List，如果遇到Cleaner对象，那么就执行clean方法。
DirectBuffer在一些高性能的中间件上使用还是相当广泛的。正确的使用可以提升程序的性能，降低GC的频率。

## 迭代，循环，遍历，递归的区别？
loop、iterate、traversal和recursion这几个词是计算机技术书中经常会出现的几个词汇。
- 1，循环（loop），指的是在满足条件的情况下，重复执行同一段代码。比如，while语句。循环则技能对应集合，列表，数组等，也能对执行代码进行操作。
- 2，迭代（iterate），指的是按照某种顺序逐个访问列表中的每一项。比如，for语句。迭代只能对应集合，列表，数组等。不能对执行代码进行迭代。
- 3，遍历（traversal），指的是按照一定的规则访问树形结构中的每个节点，而且每个节点都只访问一次。   遍历同迭代一样，也不能对执行代码进行遍历。
- 4，递归（recursion），指的是一个函数不断调用自身的行为。
(1)，通俗的解释：递归就像往存钱罐里存钱，先往里边塞钱，2块，5块，10块这样的塞，叫入栈。取钱的时候，后塞进去的先取出来，这叫出栈。具体多少钱，要全部出栈才知道。  
(2)，递归分类：线性递归和尾递归。
































