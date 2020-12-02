---
title: java关键字记录
date: 2017-02-20 13:30:36
tags:
- java
---

### synchronized
> 修饰一个代码块，被修饰的代码块称为同步语句块，其作用的范围是大括号{}括起来的代码，作用的对象是调用这个代码块的对象； 
修饰一个方法，被修饰的方法称为同步方法，其作用的范围是整个方法，作用的对象是调用这个方法的对象； 
修改一个静态的方法，其作用的范围是整个静态方法，作用的对象是这个类的所有对象； 
修改一个类，其作用的范围是synchronized后面括号括起来的部分，作用的对象是这个类的所有对象。

### transient
> transient修饰的变量不参与序列化，比如在一个实现Serializable的类中有一个transient修饰的变量，使用ObjectStream序列化的时候会把该变量的值设为null，无论该变量是否有值。

###  this
> Java关键字this只能用于方法体内。当一个对象创建后，Java虚拟机（JVM）就会给这个对象分配一个引用自身的指针，这个指针的名字就是 this。this只能在类中的非静态方法中使用，静态方法和静态的代码块中绝对不能出现this，并且this只和特定的对象关联，而不和类关联，同一个类的不同对象有不同的this。
this 是指向当前对象的引用（一个变量），需要对象实例化以后才能赋值。而静态成员都是类所属的，不需要对实例化就可以使用，所以在静态上下文中引用this时可能其还未赋值，所以应不能这样使用。
我们可以在任何实例方法中使用this，因为虚拟机默认为每个方法添加this参数，在第零个位置。