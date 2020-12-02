## i++和++i的区别(i++代表先进行计算，++i是代表后进行计算)

int i = 0; arrays[i++];代表获取的arrays[0]这个元素；   
int i = 0; arrays[++i];代表获取的arrays[1]这个元素；  
[例子](../../../../jb-basic/src/main/java/basic/other/TestExpressions.java)   

## java中==和equals和hashcode的区别？

==运算符，用于比较两个变量是否相等，比较的值。   
对象equals方法内部调用使用的==，基本类型的==比较的是值，但是我们可以重写。引用类型的比较的是对象，也就是内存地址（但是一般情况下不想只比较内存地址，所以可以重写equals方法）。   
不重写equals的情况下，自定义对象的equals和==是相同的。
   
[例子](../../../../jb-basic/src/main/java/basic/other/TestFeatures.java)

> NOTE:这种是不相等的，因为他们都是对象（Object），单单一个int,double等基本类型是不能使用equals方法的。

## 什么是窄化（下转型）

byte -> short,char -> int -> long            
int -> float    
long -> double -> float    
一、基本数据类型的特点，位数，最大值和最小值。
一个byte（字节）是8bit，
基本类型：short(2个字节) 二进制位数：16
基本类型：int(4个字节) 二进制位数：32
基本类型：long(8个字节) 二进制位数：64
基本类型：float(4个字节) 二进制位数：32(有效数字8位)
最小值：Float.MIN_VALUE=1.4E-45 （2的-149次方）最大值：Float.MAX_VALUE=3.4028235E38 （2的128次方-1）
基本类型：double(8个字节) 二进制位数：64(有效数字16位)
最小值：Double.MIN_VALUE=4.9E-324 （2的-1074次方）最大值：Double.MAX_VALUE=1.7976931348623157E308 （2的1024次方-1）

## Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150; System.out.println(f1 == f2); System.out.println(f3 == f4);

整型字面量的值在-128到127之间，那么不会new新的Integer对象，而是直接引用常量池中的Integer对象，所以上面的面试题中f1==f2的结果是true，而f3==f4的结果是false。

## Java 到底是值传递还是引用传递？

int num = 10; String str = "hello";  num存储的直接是一个value（值变量），str存储的时一个地址0Xxx指向了value（hello）。传递的都是值（或者是值变量，或者是引用变量的地址）。
如果参数是基本类型，传递的是基本类型的字面量值的拷贝。
如果参数是引用类型，传递的是该参量所引用的对象在堆中地址值的拷贝。

[例子](../../../../jb-basic/src/main/java/basic/reference)

## transient关键字

向该对象被序列化的时候，关键字修饰的变量将不被序列化。

## 哪一种线程创建的监视更加的合理化？

技术上来说，实现Runnable接口的更加的合理，因为你如果继承一个线程，那么你就无法继承其他的类

> NOTE:因为继承只能继承一个类。

## java中的内部类和特性？

嵌套类  嵌套类的访问规则和outer一样，嵌套类就是内部一个类使用了static修饰。      
成员内部类  需要先实例化outer，然后使用outer的instance对象实例化inner。    
局部内部类 可以使用在方法里面或者特定作用域。    
匿名内部类  必须使用new，所以该类必须要先定义，另外当坐在方法的形参需要被内部类使用到的时候，必须为final修饰。为什么呢？    

> NOTE:因为内部类访问外部参数是通过传入自己的构造方法使用的，相当于拷贝了一份，所以修改值不会影响原值任何的改动。使用匿名
内部类的时候让人觉得内部类其实已经对值做出了改变，但是其实没有。为了杜绝这个混淆，所以添加了final验证，既是这个值已经不能更改了。

[例子](../../../../jb-basic/src/main/java/basic/clazz)

## 声明变量和定义变量的区别？

`String s1`为声明变量 
`String s2 = "hehe"`为定义变量

## 对象引用作为实例化变量的默认值是null，除非我们明确指出。

## java中类可以使用private 和 protect修饰吗？

不可以，not allow。

## 抢占调度和时间切片的区别

在抢占调度中，高优先级的任务进入等待状态，或者死亡状态，不然会一直执行下去，直到更高优先级的任务出现。
在时间切片下，执行一个预先定义的时间切片任务，然后重新进入准备好的任务池。然后，调度程序根据优先级和其他因素决定下一个应该执行哪个任务。

## main方法中String[] args的第一个元素是什么？

null。

## 什么是受检异常和未受检异常？

未受检异常就是运行时异常。
受检异常为编译器时就抛出的异常。
```
未受检异常
Java.lang.ArithmeticException
Java.lang.ArrayStoreExcetpion
Java.lang.ClassCastException
Java.lang.EnumConstantNotPresentException
Java.lang.IllegalArgumentException
Java.lang.IllegalThreadStateException
Java.lang.NumberFormatException
Java.lang.IllegalMonitorStateException
Java.lang.IllegalStateException
Java.lang.IndexOutOfBoundsException
Java.lang.ArrayIndexOutOfBoundsException
Java.lang.StringIndexOutOfBoundsException
Java.lang.NegativeArraySizeException’
Java.lang.NullPointerException
Java.lang.SecurityException
Java.lang.TypeNotPresentException
Java.lang.UnsupprotedOperationException
受检异常
Java.lang.ClassNotFoundException
Java.lang.CloneNotSupportedException
Java.lang.IllegalAccessException
Java.lang.InterruptedException
Java.lang.NoSuchFieldException
Java.lang.NoSuchMethodException
```

## 如何自定义和控制序列化程序。

实现Externalizable接口的两个方法。ng array本身就是empty（非null）的，和c++不同，第一个元素是程序本身。

##  序列化不序列三种情况？

- 静态属性。
- 没有实现Serializable
- transient修饰

## 堆和栈的理解?

栈是运行时的单位，而堆是存储的单位。
在Java中一个线程就会相应有一个线程栈与之对应，这点很容易理解，因为不同的线程执行逻辑有所不同，因此需要一个独立的线程栈。
而堆则是所有线程共享的。栈因为是运行单位，因此里面存储的信息都是跟当前线程（或程序）相关信息的。包括局部变量、程序运行状态、方法返回值等等；而堆只负责存储对象信息。

## 为什么要把堆和栈区分出来呢？栈中不是也可以存储数据吗？

- 第一，从软件设计的角度看，栈代表了处理逻辑，而堆代表了数据。这样分开，使得处理逻辑更为清晰。分而治之的思想。这种隔离、模块化的思想在软件设计的方方面面都有体现。
- 第二，堆与栈的分离，使得堆中的内容可以被多个栈共享（也可以理解为多个线程访问同一个对象）。这种共享的收益是很多的。一方面这种共享提供了一种有效的数据交互方式(如：共享内存)，
另一方面，堆中的共享常量和缓存可以被所有栈访问，节省了空间。
- 第三，栈因为运行时的需要，比如保存系统运行的上下文，需要进行地址段的划分。由于栈只能向上增长，因此就会限制住栈存储内容的能力。
而堆不同，堆中的对象是可以根据需要动态增长的，因此栈和堆的拆分，使得动态增长成为可能，相应栈中只需记录堆中的一个地址即可。
- 第四，面向对象就是堆和栈的完美结合。其实，面向对象方式的程序与以前结构化的程序在执行上没有任何区别。
但是，面向对象的引入，使得对待问题的思考方式发生了改变，而更接近于自然方式的思考。
当我们把对象拆开，你会发现，对象的属性其实就是数据，存放在堆中；而对象的行为（方法），就是运行逻辑，放在栈中。
我们在编写对象的时候，其实即编写了数据结构，也编写的处理数据的逻辑。不得不承认，面向对象的设计，确实很美。

##  堆中存什么？栈中存什么？

堆中存的是对象。栈中存的是基本数据类型和堆中对象的引用。一个对象的大小是不可估计的，或者说是可以动态变化的，但是在栈中，一个对象只对应了一个4btye的引用（堆栈分离的好处）。
为什么不把基本类型放堆中呢？因为其占用的空间一般是1~8个字节——需要空间比较少，而且因为是基本类型，所以不会出现动态增长的情况——长度固定，因此栈中存储就够了，如果把他存在堆中是没有什么意义的。
可以这么说，基本类型和对象的引用都是存放在栈中，而且都是几个字节的一个数，因此在程序运行时，他们的处理方式是统一的。

## 为什么存在这两种类型呢(基本类型和包装类型)？

我们都知道在Java语言中，`NEW`一个对象存储在堆里，我们通过栈中的引用来使用这些对象；但是对于经常用到的一系列类型如int，如果我们用new将其存储在堆里就不是很高效。
特别是简单的小的变量。所以就出现了基本类型，同C++一样，Java采用了相似的做法，对于这些类型不是用new关键字来创建，而是直接将变量的值存储在栈中，因此更加高效。

## 为什么我们需要包装类？

我们知道Java是一个面相对象的编程语言，基本类型并不具有对象的性质，为了让基本类型也具有对象的特征，就出现了包装类型（如我们在使用集合类型Collection时就一定要使用包装类型而非基本类型），
它相当于将基本类型“包装起来”，使得它具有了对象的性质，并且为其添加了属性和方法，丰富了基本类型的操作。另外，当需要往ArrayList，HashMap中放东西时，像int，double这种基本类型是放不进去的，
因为容器都是装object的，这是就需要这些基本类型的包装器类了。

## 不可达对象能不能转化为可达对象（unreachable object become reachable again）?

可以的，This can happen when the object's finalize() method is invoked and the object performs an operation which causes it to become accessible to reachable objects.

## java中的对象引用

- 强引用：
只要引用存在，垃圾回收器永远不会回收
```java_holder_method_tree
Object obj = new Object();
```
而这样 obj对象对后面new Object的一个强引用，只有当obj这个引用被释放之后，对象才会被释放掉，这也是我们经常所用到的编码形式。
强引用是使用最普遍的引用。如果一个对象具有强引用，那垃圾回收器绝不会回收它。当内存空间不足，Java虚拟机宁愿抛出OutOfMemoryError错误，使程序异常终止，也不会靠随意回收具有强引用的对象来解决内存不足的问题。

- 软引用：
非必须引用，内存溢出之前进行回收，可以通过以下代码实现
```java_holder_method_tree
Object obj = new Object();
SoftReference<Object> sf = new SoftReference<Object>(obj);
obj = null;
sf.get();  //有时候会返回null
```
这时候sf是对obj的一个软引用，通过sf.get()方法可以取到这个对象，当然，当这个对象被标记为需要回收的对象时，则返回null；
软引用主要用户实现类似缓存的功能，在内存足够的情况下直接通过软引用取值，无需从繁忙的真实来源查询数据，提升速度；当内存不足时，自动删除这部分缓存数据，从真正的来源查询这些数据。
如果一个对象只具有软引用，则内存空间足够，垃圾回收器就不会回收它；如果内存空间不足了，就会回收这些对象的内存。只要垃圾回收器没有回收它，该对象就可以被程序使用。
软引用可用来实现内存敏感的高速缓存（下文给出示例）。
软引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被垃圾回收器回收，Java虚拟机就会把这个软引用加入到与之关联的引用队列中。

- 弱引用：
第二次垃圾回收时回收，可以通过如下代码实现
```java_holder_method_tree
Object obj = new Object();
WeakReference<Object> wf = new WeakReference<Object>(obj);
obj = null;
wf.get();  //有时候会返回null
wf.isEnQueued();  //返回是否被垃圾回收器标记为即将回收的垃圾
```
弱引用是在第二次垃圾回收时回收，短时间内通过弱引用取对应的数据，可以取到，当执行过第二次垃圾回收时，将返回null。
弱引用主要用于监控对象是否已经被垃圾回收器标记为即将回收的垃圾，可以通过弱引用的isEnQueued方法返回对象是否被垃圾回收器标记。
弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命2PC周期。在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。
弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。

- 虚引用：
垃圾回收时回收，无法通过引用取到对象值，可以通过如下代码实现
```java_holder_method_tree
Object obj = new Object();
PhantomReference<Object> pf = new PhantomReference<Object>(obj);
obj=null;
pf.get();//永远返回null
pf.isEnQueued();//返回是否从内存中已经删除
```
虚引用是每次垃圾回收的时候都会被回收，通过虚引用的get方法永远获取到的数据为null，因此也被成为幽灵引用。
虚引用主要用于检测对象是否已经从内存中删除。
“虚引用”顾名思义，就是形同虚设，与其他几种引用都不同，虚引用并不会决定对象的生命周期。如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收。
虚引用主要用来跟踪对象被垃圾回收器回收的活动。

>NOTE: 虚引用与软引用和弱引用的一个区别在于：虚引用必须和引用队列 （ReferenceQueue）联合使用。
当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之关联的引用队列中。
>
>NOTE:DirectByteBuffer是通过虚引用(Phantom Reference)来实现堆外内存的释放的。
PhantomReference 是所有“弱引用”中最弱的引用类型。不同于软引用和弱引用，虚引用无法通过 get() 方法来取得目标对象的强引用从而使用目标对象，观察源码可以发现 get() 被重写为永远返回 null。
那虚引用到底有什么作用？其实虚引用主要被用来 跟踪对象被垃圾回收的状态，通过查看引用队列中是否包含对象所对应的虚引用来判断它是否 即将被垃圾回收，从而采取行动。它并不被期待用来取得目标对象的引用，而目标对象被回收前，它的引用会被放入一个 ReferenceQueue 对象中，从而达到跟踪对象垃圾回收的作用。


## java线程可以启动两次吗

不能，因为处于runnable的线程已经开始运行，如果再次调用运行方法，会抛出`IllegalThreadStateException`。

## 什么是Java上下文切换？

线程是由CPU进行调度的，CPU的一个时间片内只执行一个线程上下文内的线程，当CPU由执行线程A切换到执行线程B的过程中会发生一些列的操作，
这些操作主要有”保存线程A的执行现场“然后”载入线程B的执行现场”，这个过程称之为“上下文切换（context switch）”,这个上下文切换过程并不廉价，如果没有必要，应该尽量减少上下文切换的发生。

## 引起上下文切换的原因

- 时间片用完，CPU正常调度下一个任务   
- 被其他优先级更高的任务抢占   
- 执行任务碰到IO阻塞，调度器挂起当前任务，切换执行下一个任务   
- 用户代码主动挂起当前任务让出CPU时间   
- 多任务抢占资源，由于没有抢到被挂起   
- 硬件中断   

## Java里面所有的不变的属性需要设置为final吗？

没有必要，你可以实现相同的功能通过以下操作：设为非final的private 变量，且只有在构造函数中才能修改。不设set方法，如果是一个可变对象，不要泄露任何指向这个对象的引用。
设置一个引用变量为final 只能确保这个变量不会被赋予一个不同的引用，但是你仍然可以改变引用变量的属性值，(效果如下一个问题)。

## 为什么Java final关键字修饰的Map List可以更改

final 类型的map和arrayList中，put或添加数据并不是修改原有变量的内存指向地址。只是给这个地址中加了更多的值而已。
但是final String s ="123"   s = " 456"; 这等于指向了两个内存地址。 final的意思是不可变最终的  是指他内存中所指向的内存地址是不可变是最终的

## 为什么java泛型不支持数组

在Java中，Object[]数组可以是任何数组的父类，或者说，任何一个数组都可以向上转型成它在定义时指定元素类型的父类的数组，
这个时候如果我们往里面放不同于原始数据类型 但是满足后来使用的父类类型的话，编译不会有问题，但是在运行时会检查加入数组的对象的类型，于是会抛ArrayStoreException：
``` java_holder_method_tree
String[] strArray = new String[20];
Object[] objArray = strArray;
objArray[0] = new Integer(1);   // throws ArrayStoreException at runtime
```

## 要区分数组(协变性)和泛型容器(无关性)的功能，这里先要理解三个概念：协变性（covariance）、逆变性（contravariance）和无关性（invariant）。

Java’s generic type parameters are invariant（不变的）       
若类A是类B的子类，则记作A ≦ B。设有变换f()，若：   
- 当A ≦ B时，有f(A)≦ f(B)，则称变换f()具有协变性；    
- 当A ≦ B时，有f(B)≦ f(A)，则称变换f()具有逆变性；     
- 如果以上两者皆不成立，则称变换f()具有无关性。     

引入泛型的目的之一就是为了提高程序的安全性，减少错误发生，并且`泛型检测只存在于编译时期`，编译后的代码会编译为基类型，也就是Object。    
（泛型的第一要素就是如果一段代码在编译时没有提出“未经检查的转换”警告，则程序在运行时不会引发ClassCastException异常，也就是程序一定是类型安全的。）。    
比如下面的类型代码

```java_holder_method_tree
# 基本测试类型
public class Pair<T> {
	public void info( )
	{
		System.out.println("I am Pair");
	}
}
# 下面这一段代码是没有问题的
public static void main(String[] args) {
    ArrayList list = new ArrayList();
    list.add(new Pair());
    list.add(5);
    Pair p = (Pair) list.get(0);
    p.info();
    Integer number = (Integer)list.get(1);
    System.out.println(number);
}
# 下面这段代码在运行时候可是有问题了，但是编译器却没有提示错误。
public static void main(String[] args) {
    ArrayList list=new ArrayList();
    list.add(new Pair());
    list.add(5);
    Pair p=(Pair)list.get(1);
    p.info();
}
# 下面这段代码连编译都过不去，更别说与运行时异常了。
public static void main(String[] args) {
    ArrayList<Pair> list=new ArrayList();
    list.add(new Pair());
    //这一行编译报错
    list.add(5);
    Pair p= list.get(1); //并且该行不需要强制转换
    p.info();
}
# 而类型的父子转换是有限制的
public static void main(String[] args) {
    ArrayList<Pair> list=new ArrayList();
    list.add(new Pair());
    //强转报错，不支持改写法。
    ArrayList<Object> list2 = (ArrayList<Object>)list;
}
```

如果要是支持的话

```java_holder_method_tree
ArrayList<Integer>[] intArr = new ArrayList<Integer>[10];
Object[] obj = intArr;

// Unsound, but passes run time store check
ArrayList<String> listStr = new ArrayList<String>();
obj[0] = listStr;                    //是不会报错的
```

因为Java的泛型会在编译后将类型信息抹掉，
这样做不但编译器不能发现类型错误，就连运行时的数组存储检查对它也无能为力，
运行时检查它能看到的是我们往里面放Map的对象，我们定义的<Integer, String>在这个时候已经被抹掉了，
于是而对它而言，只要是Map，都是合法的。想想看，我们本来定义的是装Map<Integer, String>的数组，结果我们却可以往里面放任何Map，接下来如果有代码试图按原有的定义去取值，后果是什么不言自明。
其实最核心的原因就是泛型是使用抹除类型的方法去实现的。

## 什么是具象化类型？

完全在运行时可用的类型被称为具象化类型（refiable type），会做这种区分是因为有些类型会在编译过程中被擦除，并不是所有的类型都在运行时可用。
- 1、非泛型类声明，接口类型声明；
- 2、所有泛型参数类型为无界通配符（仅用‘?’修饰）的泛型参数类；
- 3、原始类型；
- 4、基本数据类型；
- 5、其元素类型为具象化类型的数组；
- 6、嵌套类（内部类，匿名内部类等，比如java.util.HashMap.Entry），并且嵌套过程中的每一个类都是具象化的。

## 什么是泛型擦除？
在学习泛型擦除之前，明确一个概念：Java的泛型不存在于运行时。这也是为什么有人说Java没有真正的泛型。
泛型擦除（类型擦除），它是指在编译器处理带泛型定义的类\接口\方法时，会在字节码指令集里抹去全部泛型类型信息，被擦除后泛型，在字节码里只保留泛型的原始类型(raw type)。
原始类型，是指抹去泛型信息后的类型，在Java中，它必须是一个引用类型（非基本数据类型），一般而言，它对应的是泛型的定义上界。
举例：<T>中的T对应的原始泛型是Object，<T extends String>对应的原始类型就是String。

## JAVA的复杂运算符？

- &(按位与)    
0 & 0 = 0, 0 & 1 = 0, 1 & 0 = 0, 1 & 1 = 1

- |(按位或)    
0 | 0 = 0, 0 | 1 = 1, 1 | 0 = 1, 1 | 1 = 1

- ^(按位异或)    
0 ^ 0 = 0, 0 ^ 1 = 1, 1 ^ 0 = 1, 1 ^ 1 = 0

- ~(按位取反)    
~0=1

- 左移运算符(<<)
左移时，空出的右端用0补充，左端移出的位的信息就被丢弃。   
在二进制数运算中，在信息没有因移动而丢失的情况下，每左移1位相当于乘2。如4 << 2，结果为16。  

[例子](../../../../jb-basic/src/main/java/basic/other/TestSpecialOperator.java)   

## assiic码值？

- 65-90 A-Z
- 97-122 a-z

## 为什么使用char[]数组存储敏感信息（账户，密码）比string好？

因为string是final修饰的不可变数据，他会存储在string常量池，直到在方法区中被回收，在此期间，anyone having access to a memory dump（内存转储） can potentially extract the sensitive data and exploit it。
而使用char的话可以直接设置为null,这样的话，很快就会被回收。
主要原理就是方法区的回收间隔较长。

## 怎么样捕获另一个线程抛出的异常？

Thread.UncaughtExceptionHandler
``` java_holder_method_tree
// create our uncaught exception handler
Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
    public void uncaughtException(Thread th, Throwable ex) {
        System.out.println("Uncaught exception: " + ex);
    }
};

// create another thread
Thread otherThread = new Thread() {
    public void run() {
        System.out.println("Sleeping ...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted.");
        }
        System.out.println("Throwing exception ...");
        throw new RuntimeException();
    }
};

// set our uncaught exception handler as the one to be used when the new thread
// throws an uncaught exception
otherThread.setUncaughtExceptionHandler(handler);

// start the other thread - our uncaught exception handler will be invoked when
// the other thread throws an uncaught exception
otherThread.start();
```

## When designing an abstract class, why should you avoid calling abstract methods inside its constructor?

因为子类的构造方法是在父类的构造方法之后执行的。
如果父类的构造方法调用了本身的抽象方法，那么就会执行子类的实现方法。
子类的实现方法没有初始化值的话，则完蛋。

[例子](../../../../jb-basic/src/main/java/basic/clazz/abstractClass)

## What does it mean for a collection to be “backed by” another? Give an example of when this property is useful.

If a collection backs another, it means that changes in one are reflected in the other and vice-versa(反之亦然).
比如map.keySet()遍历的时候移除一个key,则原始的map里面的key value，都会被移除。

## 为什么总有一个“main方法”？        

main方法是程序的入口，并且是静态方法。static关键字意味着这个方法是类的一部分，而不是实例对象的一部分。为什么会这样呢?           
为什么我们不用一个非静态的方法作为程序的入口呢？          
如果一个方法不是静态的，那么对象需要先被创建好以后才能使用这个方法。因为这个方法必须要在一个对象上调用。            
对于一个入口来说，这是不现实的。因此，程序的入口方法是静态的。       
参数 “String[] args”表明可以将一个字符串数组传递给程序来帮助程序初始化。       

## 什么是补码?

补码只是一种相对合理的编码方案。这个方案在负数的机器表示中解决了3个问题：
**数的表示**
在数的表示上通过人为的定义来消除编码映射的不唯一性，对转换后的10000000强制认定为-128。当然对原码和反码也可以做这种强制认定，那为什么原码和反码没有流行起来？
原码和反码没有流行起来，是因为在数的运算上对符号位的处理无法用当时已有的机器物理设计来实现。由于原码和反码在编码时采用了硬性的人工设计，
这种设计在数理上无法自动的通过模来实现对符号位的自动处理，符号位必须人工处理，必须对机器加入新的物理部件来专门处理符号位，这加大了机器设计难度，加大的机器成本，不到万不得已，不走这条路。
**数的运算**
设计补码时，有意识的引用了模运算在数理上对符号位的自动处理，利用模的自动丢弃实现了符号位的自然处理，仅仅通过编码的改变就可以在不更改机器物理架构的基础上完成的预期的要求，所以补码沿用至今。
**自身逻辑意义的完整性**
补码这个编码方案要解决的是如何在机器中表示负数，其本质意义为用一个正数来表示这个正数对应的负数。所谓-20的补码是指：如何在机器中用补码形式表示-20。
具体过程是这样的：将20的二进制形式直接写出00010100，然后所有位取反变成11101011，再加1变成了11101100。
最简单的补码转换方式，不必去理会转换过程中的符号位，只关注转换前和最终转换后的符号位就行了。
那么对11101100求出其补码又具有什么现实含义呢？对一个数求补，逻辑过程是对这个数的所有的二进制位按位取反再加1。
现实含义是求出这个数对应的负数形式。
对11101100求补就是求出这个数对应的负数的形式，直接操作下11101100，先所有位取反00010011，再减去1就成了00010100。
对11101100求出其补码的含义：11101100按照现行补码码制表示的有符号数是-20，对于-20求补就是求出其对应的负数-(-20)，现实中-(-20)是+20，那么求补运算的结果符合现实情况吗，00010100转换成有符号数正是+20，这就说明了补码自身逻辑意义是完整的，是不会自相矛盾的。
最后，补码的总前提是机器数，不要忘了机器数的符号位含义，最高位为0表示正数，最高位为1表示负数,而最高位是指机器字长的最左边一位。字节数100B，最高位为00000100中的最左边的0。

## JAVA中二进制、八进制、十六进制表示法

[例子](../../../../jb-basic/src/main/java/basic/other/TestBinary.java)

## 关于面向接口编程和面向对象编程的区别？

接口不是对象 不能实例化  但是能够表达继承该接口对象的行为 就是他能干什么 
那好了 当我们需要完成一个任务的时候 我们需要分解步骤  那么谁能够干什么就是我们需要关心的
我们不需要关心这个对象有什么 如果有参数的化传过去  因此我们在编写程序步骤的时候 只需要了解什么对象能干
那好了 我们使用接口表明了有一群能干这份工作的人   
怎么干？   交给你自己   你可以自己指定任意我能声明的对象
好了  都交给你了  那就可以制定了吧   能够根据自己的需求需要的结果  任意的调用 不要钱   这也是策略模式的基本体现
如果对象做这件事的化  也就是说  解决这种任务的就只有他一个   
如果我们这满足不了你的需求 好说 我再次重新写一个实现类  去按照你的需求覆盖这个方法  不用修改任何代码 直接解耦合 这是java中的依赖倒转原则 满足了开闭原则的基础 
因此现在流行一种说法  就是面向接口编程   程度浅的同学还以为接口可以实现方法了呢  两者绝不冲突  因为接口声明的还是对象   

## 全局位移有序ID?

> snowfalke（64位）
1位，不用。二进制中最高位为1的都是负数，但是我们生成的id一般都使用整数，所以这个最高位固定是0
41位，用来记录时间戳（毫秒）。
41位可以表示241−1个数字，
如果只用来表示正整数（计算机中正数包含0），可以表示的数值范围是：0 至 241−1，减1是因为可表示的数值范围是从0开始算的，而不是1。
也就是说41位可以表示241−1个毫秒的值，转化成单位年则是(241−1)/(1000∗60∗60∗24∗365)=69年
10位，用来记录工作机器id。
可以部署在210=1024个节点，包括5位datacenterId和5位workerId
5位（bit）可以表示的最大正整数是25−1=31，即可以用0、1、2、3、....31这32个数字，来表示不同的datecenterId或workerId
12位，序列号，用来记录同毫秒内产生的不同id。
12位（bit）可以表示的最大正整数是212−1=4096，即可以用0、1、2、3、....4095这4096个数字，来表示同一机器同一时间截（毫秒)内产生的4096个ID序号

## 栈溢出攻击?

>  向缓冲器填入过多的数据，超出边界，导致数据外溢。 同时利用缓冲器溢出改写数据、改变程序执行流程。
由于字符串处理函数（gets，strcpy等等）没有对数组越界加以监视和限制，我们利用字符数组写越界，覆盖堆栈中的老元素的值，就可以修改返回地址。
栈： 由高地址向低地址增长     堆： 由低地址向高地址增长
    
## Java对象的大小?

基本数据的类型的大小是固定的，这里就不多说了。对于非基本类型的Java对象，其大小就值得商榷。
在Java中，一个空Object对象的大小是8byte，这个大小只是保存堆中一个没有任何属性的对象的大小。看下面语句：
Object ob = new Object();
这样在程序中完成了一个Java对象的生命，但是它所占的空间为：4byte+8byte。4byte是上面部分所说的Java栈中保存引用的所需要的空间。而那8byte则是Java堆中对象的信息。因为所有的Java非基本类型的对象都需要默认继承Object对象，因此不论什么样的Java对象，其大小都必须是大于8byte。
有了Object对象的大小，我们就可以计算其他对象的大小了。
```` java
Class NewObject {
    int count;
    boolean flag;
    Object ob;
}
````
其大小为：空对象大小(8byte)+int大小(4byte)+Boolean大小(1byte)+空Object引用的大小(4byte)=17byte。但是因为Java在对对象内存分配时都是以8的整数倍来分，因此大于17byte的最接近8的整数倍的是24，因此此对象的大小为24byte。
这里需要注意一下基本类型的包装类型的大小。因为这种包装类型已经成为对象了，因此需要把他们作为对象来看待。包装类型的大小至少是12byte（声明一个空Object至少需要的空间），而且12byte没有包含任何有效信息，同时，因为Java对象大小是8的整数倍，因此一个基本类型包装类的大小至少是16byte。这个内存占用是很恐怖的，它是使用基本类型的N倍（N>2），有些类型的内存占用更是夸张（随便想下就知道了）。因此，可能的话应尽量少使用包装类。在JDK5.0以后，因为加入了自动类型装换，因此，Java虚拟机会在存储方面进行相应的优化。

## java程序怎么样正常打印到控制台，错误日志输出到一个文件？

``` java_holder_method_tree
Stream st = new Stream(new FileOutputStream("output.txt")); 
System.setErr(st); 
System.setOut(st);
```

## Java语言的类型？

JAVA是一种静态语言，也就是说每一个变量和每一个表达式，在编译器的时候就会知道它的类型。
JAVA也是一种强类型语言，因为类型限制了变量和表达式可以产生的值的类型

## 什么是文件句柄？

1.只有windows中才有句柄，windows中的句柄是指针的指针，因为windows中对象的经常会在内存中移动（如进行垃圾回收后），所以地址值经常会变，所以就对外提供一个指针的指针即句柄给用户，句柄的地址是不会变的。
2.linux中是没有文件句柄的，只有文件描述符，只是大家习惯把它说成句柄。
3，linux中， 每当进程打开一个文件时，系统就为其分配一个唯一对应的整型文件描述符（从0开始），用来标识这个文件。linux 操作系统通常对每个进程能打开的文件数量有一个限制。默认是1024。

## 值传递、指针传递、引用传递的区别？

https://www.cnblogs.com/poissonnotes/p/4371352.html




    
















   