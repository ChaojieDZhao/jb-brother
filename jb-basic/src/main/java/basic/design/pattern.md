类和类之间的关系，耦合度从高到低：
is 。继承、实现
has 。组合、聚合、关联
use 。依赖。

## 依赖关系(Dependency)

**单向**，表示一个类依赖于另一个类的定义，其中一个类的变化将影响另外一个类，是一种“use a”关系!如果A依赖于B，则B表现为A的局部变量，方法参数，静态方法调用等

```java
public class Person {  
    public void doSomething(){  
        Card card = new Card();//局部变量  
        ....  
    } 

    public void doSomething(Card card){}   //方法参数

    public void doSomething2(){  
            int id = Card.getId(); //静态方法调用  
            ...  
    }  
}  
```

## 关联关系(Association)

单向或双向（通常我们需要避免使用双向关联关系），是一种"has a"关系，如果A单向关联B，则可以说A has a B，通常表现为全局变量。

> 特点是：不是在构造方法中设置的

```java
public class Person {  
    public Address address;  
      
    public void setAddress (Address address){        
        this.address= address;  
    }  
      
    public Address getAddress (){          
        return address;  
    }  
}  
```

**双关联**

```java
public class customer {  
    public Product product[]    ;  
}  

public class Product {  
    public customer customer[]    ;  
}  
```

**自关联**

```java
public class Node {  
    public Node current    ;  
}  
```

## 聚合关系(Aggregation)

> 聚合的特点是：有这个属性，且要在构造方法中使用，但不是在构造方法中创建的。
> 注意和组合的差别：两者的创建时机不同，组合是在Person创建的时候，被创建的。聚合是可以在别的创建的。

单向，关联关系的一种，与关联关系之间的区别是**语义上**的，关联的两个对象通常是平等的，聚合则一般不平等，有一种整体和局部的感觉，
关联的两个对象之间一般是平等的，例如你是我的朋友，聚合则一般不是平等的，例如一个公司包含了很多员工，其实现上是差不多的。实现上区别不大!

```java
public class Team {  
    public Person person;  
      
    public Team(Person person){  
        this.person = person;  
    }  
} 
```

## 组合关系(Composition)     

组合也是关联关系的一种特例，它体现的是一种contains-a的关系，这种关系比聚合更强，也称为强聚合。
它同样体现整体与部分间的关系，但此时整体与部分是不可分的，整体的生命周期结束也就意味着部分的生命周期结束，比如人和人的大脑。
表现在代码层面，和关联关系是一致的，只能从语义级别来区分。在UML类图设计中，组合关系以实心菱形加实线箭头表示。 

```java
public class Person {
    // Person和Heart之间是组合
    private Heart h;
    public Person(){
        h=new Heart();
    }
}
```

> 特征是：要在构造方法中创建对象

## 继承关系(Inheritance)
## 实现（Realization）：类实现接口属于这种关系
## 泛化（Generalization）：即"is a"关系，类继承抽象类，类继承父类都属于这种关系










## 结构型(Structural patterns)

外观模式/门面模式（Facade门面模式）

- 适配器模式（Adapter）

[](https://blog.csdn.net/wwwdc1012/article/details/82780560)

角色：
Target（目标抽象类）：目标抽象类定义客户所需接口，可以是一个抽象类或接口，也可以是具体类。
Adapter（适配器类）：适配器可以调用另一个接口，作为一个转换器，对Adaptee和Target进行适配，适配器类是适配器模式的核心，在对象适配器中，
它通过继承Target并关联一个Adaptee对象使二者产生联系。
Adaptee（适配者类）：适配者即被适配的角色，它定义了一个已经存在的接口，这个接口需要适配，适配者类一般是一个具体类，
包含了客户希望使用的业务方法，在某些情况下可能没有适配者类的源代码。

核心：使用继承和关联来完成。

适用场景：
系统需要使用一些现有的类，而这些类的接口（如方法名）不符合系统的需要，甚至没有这些类的源代码。
想创建一个可以重复使用的类，用于与一些彼此之间没有太大关联的一些类，包括一些可能在将来引进的类一起工作。

