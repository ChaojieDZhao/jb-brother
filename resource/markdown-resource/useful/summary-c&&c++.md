C是面向过程的语言，而C++是面向对象的语言。所以C++支持继承和多态。C++拥有非常强大的STL模版库。
C++有非常强大的设计模式，比如单例，工厂，观察者模式等等，这些在C语言当中都是不支持的。
C和C++一个典型的区别就在动态内存管理上了，C语言通过malloc和free来进行堆内存的分配和释放，而C++是通过new和delete来管理堆内存的。
另外强制类型转换上也不一样，C的强制类型转换使用()小括号里面加类型进行类型强转的，而C++有四种自己的类型强转方式，分别是const_cast，static_cast，reinterpret_cast和dynamic_cast。
C++还支持带有默认值的函数，函数的重载，inline内联函数，这些C语言都不支持，当然还有const这个关键字，C和C++也是有区别的。
C++不仅支持指针，还支持更安全的引用，不过在汇编代码上，指针和引用的操作是一样的。
在C++中，struct关键字不仅可以用来定义结构体，它也可以用来定义类。

## 什么是头文件
C/C++：头文件里放的是源代码
#include <*.h>
其实是将.h中的所有代码直接放在当前位置，即替换到#include <*.h>，相当于复制粘贴的操作。
头文件中可以放函数，放常量，放类。
JAVA：import里面是被编译过的*.class文件
import的包由多个类组成，有且只有类，以类为单位。