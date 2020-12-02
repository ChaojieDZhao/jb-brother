---
title: java泛型
date: 2019-06-02 09:07:21
tags:
- java
---

## 字典
- `type parameter`，类型形参。如`Collection<Integer>`，该声明的类型形参是`Integer`。
- `parameterized type`，参数化类型。如`new Array<Integer>`，该调用的参数化类型是`Integer`。
- `type variable`，类型变量。如类声明`public class Canvas<T>` ，`T`就是类型变量。


## 什么是泛型
> 泛型是从J2SE 5.0引入的一个增强特性，允许类型和方法操作各种类型的对象同时提供编译时类型的安全性检查。另外也增加了集合编译时类型的安全性检查，消除了之前需强制转换的繁重工作。

```java_holder_method_tree
List myIntList = new LinkedList(); // 1
myIntList.add(new Integer(0)); // 2
Integer x = (Integer) myIntList.iterator().next(); // 3        
```

如上述代码，第3行是很烦人的，通常情况下，开发者知道何种类型的数据会被添加到集合，但是强制转换还是必须的，因为编译器只会保证`iterator()`方法返回的是一个对象。    
然而为了保证对`Integer x`的赋值是正确的，还是需要进行一次强制类型转换。        
这样除了带来无关逻辑的混乱，还有可能导致运行时异常，因为开发者是会出错的。

使用泛型优化后的代码：

```java_holder_method_tree
List<Integer> myIntList = new LinkedList<Integer>(); // 1'
myIntList.add(new Integer(0)); // 2'
Integer x = myIntList.iterator().next(); // 3'
```

我们为泛型集合接口`List`传入了一个类型形参（`Type Parameter`）`Integer`，不仅去除了强制转换，也告诉开发人员和编译器该`List`存储的类型，也使得编译器可以在编译时进行类型的安全检查。

## 泛类和子类
下述代码会出现问题吗？相信很多不了解泛型的人员都会回答没有问题。
```java_holder_method_tree
List<String> ls = new ArrayList<String>(); // 1
List<Object> lo = ls; // 2 
```

这个问题的争议点就是：`List<String>`是不是`List<Object>`？     

如果有人回答是的话，请看下述的代码。
```java_holder_method_tree
lo.add(new Object()); // 3
String s = ls.get(0); // 4: 尝试将一个对象赋值给一个字符引用
```

答案是：会出问题，如果编译时不出问题的话，运行时也会出现问题。**事实上，编译器在编译期间就会提示编译错误**。

所以`List<String>`不是`List<Object>`的子类型，也就不是`List<Object>`。如此，泛型是来解决遗留问题的，不是而产生问题的，理解泛型就要有这个认知。

## 通配符
对比如下两段代码，它们需要打印了一个集合的所有元素，哪一种更有效？
```java_holder_method_tree
void printCollection(Collection c) {
    Iterator i = c.iterator();
    for (k = 0; k < c.size(); k++) {
        System.out.println(i.next());
    }
}
```

```java_holder_method_tree
void printCollection(Collection<Object> c) {
    for (Object e : c) {
        System.out.println(e);
    }
}
```

答案是：第一种更有效。尽管第二种引入了泛型，然而第一种可以接受任何类型的集合进行迭代，第二种则只会接受`Collection<Object>`。
如上一节所讲，`Collection<Object>`不是所有集合的超类，它不能接受`Collection<String>`或`Collection<Integer>`。

那么，如何使用泛型代表所有集合的超类，答案就是 `Collection<?>`，读作未知类集合，即可以存储任何元素类型的集合，该方式叫做通配符类型。

现在我们就可以使用通配符类型来匹配各种类型的集合， 并且享受编译时类型安全检查的好处。
```java_holder_method_tree
Collection<?> c = new ArrayList<String>(); //line 1
// Collection<?> c = new ArrayList<>(); //line 2
c.add(new Object()); // 编译异常
c.add(null); 
```

现在我们不知道`c`的元素类型代表什么，所以我们不能把`Object`添加进去。而`add`方法只接受`E`类型的元素，即`String`元素。
如果把`line 1`替换为`line 2`，则add接受的则必须是`?`（unknown）的类或子类，但是我们不知道unknown的类型，所以我们也不能添加任何类型的元素，除了`null`，因为`null`是所有类型的成员。

### 边界通配符
看下面一段代码：
```java_holder_method_tree
public abstract class Shape {
    public abstract void draw(Canvas c);
}

public class Circle extends Shape {
    private int x, y, radius;
    public void draw(Canvas c) {
        ...
    }
}

public class Rectangle extends Shape {
    private int x, y, width, height;
    public void draw(Canvas c) {
        ...
    }
}

public class Canvas {
    public void draw(Shape s) {
        s.draw(this);
   }
}

public void drawAll(List<Shape> shapes) {
    for (Shape s: shapes) {
        s.draw(this);
   }
}
```

由于前几节所讲，`drawAll`可以写入`List<Shape>`，但不可以写入`List<Rectangle>`或者`List<Circle>`，那么如果实现可以写入后两者呢？

答案就是使用边界统配符。
```java_holder_method_tree
public void drawAll(List<? extends Shape> shapes) {
    ...
}
```

`List<? extends Shape>`就是边界通配符的一个例子，`?`代表的还是unknown，即unknown必须是`Shape`或子类。我们称之为`Shape`是通配符的上边界。
当然`List<Rectangle>`或`List<Circle>`是符合的，因为`Rectangle`和`Circle`是`Shape`的子类。

当然这是有代价的，比如下面的`addRectangle`方法会出现编译错误，因为我们不知道`?`的类型，所以我们也不知道`?`是不是`Rectangle`的父类，所以该操作就不允许。
```java_holder_method_tree
public void addRectangle(List<? extends Shape> shapes) {
    // Compile-time error!
    shapes.add(0, new Rectangle());
}
```

## 泛型方法
现在书写一个程序将一个对象数组放置到一个集合中。
```java_holder_method_tree
static void fromArrayToCollection(Object[] a, Collection<?> c) {
    for (Object o : a) { 
        c.add(o); // 编译时异常
    }
}
```

上述程序还是犯了一个初学泛型的错误。不能将一个对象添加到`?`(unknown)的集合中。

解决该问题的方式就是使用泛型方法，就像类型声明一样，方法也有泛型声明，如下：
```java_holder_method_tree
static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
    for (T o : a) {
        c.add(o); // Correct
    }
}
```

现在我们就可以调用该方法，只要数组的元素类型是集合类型的`T`或子类即可。

```java_holder_method_tree
Object[] oa = new Object[100];
Collection<Object> co = new ArrayList<Object>();

// T 被推断为 Object
fromArrayToCollection(oa, co); 

String[] sa = new String[100];
Collection<String> cs = new ArrayList<String>();

// T 被推断为 String
fromArrayToCollection(sa, cs);

// T 被推断为 Object
fromArrayToCollection(sa, co);

Integer[] ia = new Integer[100];
Float[] fa = new Float[100];
Number[] na = new Number[100];
Collection<Number> cn = new ArrayList<Number>();

// T 被推断为 Number
fromArrayToCollection(ia, cn);

// T 被推断为 Number
fromArrayToCollection(fa, cn);

// T 被推断为 Number
fromArrayToCollection(na, cn);

// T 被推断为 Object
fromArrayToCollection(na, co);

// 编译异常
fromArrayToCollection(na, cs);
```

编译器会自动推断较为具体的类型以保证调用的类型正确。

那么我们该如何在泛型方法和通配符类型中选择呢？我们看一下`API`中的`Collection`的方法。

```java_holder_method_tree
interface Collection<E> {
    public boolean containsAll(Collection<?> c);
    public boolean addAll(Collection<? extends E> c);
}
```

为什么官方不使用如下的泛型方法取代呢？

```java_holder_method_tree
interface Collection<E> {
    public <T> boolean containsAll(Collection<T> c);
    public <T extends E> boolean addAll(Collection<T> c);
}
```

在`containsAll`,`addAll`方法中，类型形参`T`只被使用了一次，并且返回值与`T`没有任何的依赖关系。这种场景单纯就是告诉我们类型参数只是用来进行多态的使用，
它唯一的作用就是让实参类型更加的多样化，这种情况使用`?`通配符则是最好的方式。

**通配符设计就是为了更加灵活的子类型使用**

泛型方法允许使用类型形参来表示方法的形参和返回值类型的关系。如果没有类似的依赖关系，则不应该使用泛型方法。

当然它们也可以一起同时使用，比如` Collections.copy()`
```java_holder_method_tree
class Collections {
    public static <T> void copy(List<T> dest, List<? extends T> src) {
    ...
}
```

要注意的是两个形参的依赖关系，`src`的元素类型必须是可分配给`dest`的元素类型`T`的，所以`src`的元素类型必须是`T`或者子类。
`copy`方法的签名就表明了这种依赖关系。

当然我们可以换一种写法，完全不使用通配符。

```java_holder_method_tree
class Collections {
    public static <T, S extends T> void copy(List<T> dest, List<S> src) {
    ...
}
```

这样也是可以的，但是当`T`在`dest`和`src`中都使用了，`S`则只使用了一次，它并没有依赖关系。这就是可以把`S`替换为`?`的信号。
这就是该如何选择通配符和泛型方法两者使用的依据。

## 要点
### 泛型类被所有的调用共享
```java_holder_method_tree
List <String> l1 = new ArrayList<String>();
List<Integer> l2 = new ArrayList<Integer>();
System.out.println(l1.getClass() == l2.getClass()); //line 1
```
`line 1`的输出是`true`，因为所有的泛型实例都有相同的运行期类型，不管它们的真实类型形参是什么。

这也定义了什么是泛型：即它会对所有可能的类型形参都具有相同的行为。**可以理解为同一个类可以有不同的类型型参**。
我们知道类的静态变量和静态方法在所有的实例之中共享，这也是无论在静态方法或初始值设定项中，静态变量声明或初始值设定项中引用类型声明的类型形参是非法的。
因为没有实例化之前，不知道静态代码中引用的类型形参`T`是什么类型。

```java_holder_method_tree
public class Canvas<T>
{
	static T t; //编译报错
	T t1;
  
	static {
		T t3; //编译报错
	}
}
```

### 类型转换和实例判断
如上所述，还有一个隐喻的事实就是，泛型类被所有的实例共享，也就是说判断一个实例是不是泛型类的某种调用就是毫无意义的。如下：

```java_holder_method_tree
Collection cs = new ArrayList<String>();
// 不合法.
if (cs instanceof Collection<String>) { ... }

类似，还有下面叙述的转换

// Unchecked warning,
Collection<String> cstr = (Collection<String>) cs;
```

类似的，还有类型变量。如下：
```java_holder_method_tree
// Unchecked warning. 
<T> T badCast(T t, Object o) {
    return (T) o;
}
```

实际上，**类型变量在运行期是不存在的**，这意味着它们在时间和空间上都不需要性能开销，这很好。不幸的是，这也意味着类型转换中不能可靠地使用它们。


## 类字面量作为运行时类型标记 
`Class`类也变成了泛型， 这是除了容器以外泛型的一个重要应用。
`Class<T>`中的`T`代表的什么，`T`代表的是`class`所要表示的对象。
如`String.class`的类型是` Class<String>`。`Serializable.class` 的类型时`Class<Serializable>`。
`String.class`类字面量，作为类型`Class<String>`的标记。

很自然的我们可以使用类字面量作为工厂对象被反射使用，如下述例子：

```java_holder_method_tree
Collection emps = sqlUtility.select(EmpInfo.class, "select * from emps");
...
public static Collection select(Class c, String sqlStatement) { 
    Collection result = new ArrayList();
    /* Run sql query using jdbc. */
    for (/* Iterate over jdbc results. */ ) { 
        Object item = c.newInstance(); 
        /* Use reflection and set all of item's
         * fields from sql results. 
         */  
        result.add(item); 
    } 
    return result; 
}
```

但是这种方式不能完全返回我们想要的精确类型的集合，但现在`Class`引入了泛型。我们可以使用如下的方式代替：
```java_holder_method_tree
Collection<EmpInfo> 
    emps = sqlUtility.select(EmpInfo.class, "select * from emps");
...
public static <T> Collection<T> select(Class<T> c, String sqlStatement) { 
    Collection<T> result = new ArrayList<T>();
    /* Run sql query using jdbc. */
    for (/* Iterate over jdbc results. */ ) { 
        T item = c.newInstance(); 
        /* Use reflection and set all of item's
         * fields from sql results. 
         */  
        result.add(item);
    } 
    return result; 
} 
```

## 下边界通配符

看下面一个例子：
```java_holder_method_tree
interface Sink<T> {
    flush(T t);
}
```

```java_holder_method_tree
public static <T> T writeAll(Collection<T> coll, Sink<T> snk) {
    T last;
    for (T t : coll) {
        last = t;
        snk.flush(last);
    }
    return last;
}

Sink<Object> s;
Collection<String> cs;
String str = writeAll(cs, s); // 错误调用.  
```

上述的`writeAll`调用是不合法的，她的形参类型都包含类型变量`T`。这种情况下，`T`不会被推断为合适的类型。并且`String`和`Object`都不满足条件，因为`T`要为同一类型。
如何改写`writeAll`方法呢？如下：

```java_holder_method_tree
public static <T> T writeAll(Collection<T> coll, Sink<? super T> snk) {
    ...
}
```

`? super T`表示未知类型必须是`T`类型的父类，这样将可以满足条件。 


### 通配符的匹配（wildcard capture）
```java_holder_method_tree
Set<?> unknownSet = new HashSet<String>();
class Collections {
    ...
    <T> public static Set<T> unmodifiableSet(Set<T> set) {
        ...
    }
}
...
Set<?> s = Collections.unmodifiableSet(unknownSet); 
```
上述方式介绍了一种新的特殊规则，同时符合类型安全的策略，即通配符的匹配。即编译器可以把`?`当做泛型方法的类型参数。








