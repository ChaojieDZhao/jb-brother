---
title: java8中实用特性
date: 2019-01-11 15:20:59
tags:
- java
---

## 接口定义中的默认方法
JAVA8允许接口通过default关键字定义非抽象方法，可以叫做默认方法。实现类可以只实现定义的抽象方法即可。
```java_holder_method_tree
interface Formula
{
    static int positive(int a)
    {
        return a > 0 ? a : 0;
    }
    double calculate(int a);
    default double sqrt(int a)
    {
        return Math.sqrt(positive(a));
    }
}
```

``` java_holder_method_tree
Formula formula1 = new Formula()
{
    @Override
    public double calculate(int a)
    {
        return sqrt(a * 100);
    }
};

formula1.calculate(100);     // 100.0
formula1.sqrt(-23);          // 0.0
Formula.positive(-4);        // 0.0
```

## Lambda 表达式
lambda表达式在java系统中怎么实现的？

### Functional Interfaces   
> 可以有一个默认方法，但是必须只能有一个抽象方法。

```java_holder_method_tree
@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

Converter<String, Integer> converter = (from) -> Integer.valueOf(from);   //(from)代表参数， Integer.valueOf(from); 代表Converter抽象方法的实现。
Integer converted = converter.convert("123");
System.out.println(converted);    // 123

```
每一个Lambda表达式对应一个接口类型，可以叫做功能性接口。就是使用@FunctionalInterface修饰的接口并且只有一个抽象方法。
如果使用了@FunctionalInterface注解，声明第二个抽象方法编译器也会报错。
> NOTE:当然如果只有一个抽象方法，使用lambda特性该注解也可以被忽略不写。

### 方法和构造引用与`::`关键字
上面的 _(from) -> Integer.valueOf(from);_ 可以使用 _Integer::valueOf;_ 来代替。除了引用静态方法也可以引用对象方法。
```java_holder_method_tree
class Something {
    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
}

Something something = new Something();
Converter<String, String> converter = something::startsWith;
String converted = converter.convert("Java");
System.out.println(converted);    // "J"
```

也可以使用于构造函数

```java_holder_method_tree
class Person {
    String firstName;
    String lastName;

    Person() {}

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}


PersonFactory<Person> personFactory = Person::new;
Person person = personFactory.create("Peter", "Parker");
```

## 内置的功能性接口
### Predicates
