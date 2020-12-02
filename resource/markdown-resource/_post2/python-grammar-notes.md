---
title: python特殊语法笔记
date: 2017-11-25 10:08:20
tags:
- python
---

Beautiful is better than ugly.   
Explicit is better than implicit.   
Simple is better than complex.   
Complex is better than complicated.   
Flat is better than nested.   
Sparse is better than dense.   
Readability counts.   
Special cases aren't special enough to break the rules.   
Although practicality beats purity.   
Errors should never pass silently.   
Unless explicitly silenced.   
In the face of ambiguity, refuse the temptation to guess.   
There should be one-- and preferably only one --obvious way to do it.   
Although that way may not be obvious at first unless you're Dutch.   
Now is better than never.   
Although never is often better than *right* now.   
If the implementation is hard to explain, it's a bad idea.   
If the implementation is easy to explain, it may be a good idea.   
Namespaces are one honking great idea -- let's do more of those!   


优美胜于丑陋（Python 以编写优美的代码为目标）   
明了胜于晦涩（优美的代码应当是明了的，命名规范，风格相似）   
简洁胜于复杂（优美的代码应当是简洁的，不要有复杂的内部实现）   
复杂胜于凌乱（如果复杂不可避免，那代码间也不能有难懂的关系，要保持接口简洁）   
扁平胜于嵌套（优美的代码应当是扁平的，不能有太多的嵌套）   
间隔胜于紧凑（优美的代码有适当的间隔，不要奢望一行代码解决问题）   
可读性很重要（优美的代码是可读的）   
即便假借特例的实用性之名，也不可违背这些规则（这些规则至高无上）   
不要包容所有错误，除非你确定需要这样做（精准地捕获异常，不写 except:pass 风格的代码）     
当存在多种可能，不要尝试去猜测    
而是尽量找一种，最好是唯一一种明显的解决方案（如果不确定，就用穷举法）     
虽然这并不容易，因为你不是 Python 之父（这里的 Dutch 是指 Guido ）     
做也许好过不做，但不假思索就动手还不如不做（动手之前要细思量）    
如果你无法向人描述你的方案，那肯定不是一个好方案；反之亦然（方案测评标准）    
命名空间是一种绝妙的理念，我们应当多加利用（倡导与号召）   

## 数据类型和关键字
### 字符串
#### 字符串变量的索引
```
word = 'Python'
word[0]  #位置0的字符
'P'
word[5]  #位置5的字符
'n'
word[-1]  #最后一个字符
'n'
word[:2] + word[2:]
'Python'
word[:4] + word[4:]
'Python'
word[:2]   # 从0到字符串2的字符串
'Py'
word[4:]   # 从4到len(word)
'on'
word[-2:]  # 从前往后数第二个到最后end
'on'

```

#### 原始字符串(不需反斜杠转义)
``` python
#使用一个r单字符写在变量值之前
str = r'D:\soooooooft\python_install'
print(str)
'D:\soooooooft\python_install'
```

#### `_`的应用
>  在交互模式中，最后一个有关打印的表达式的值会赋值给变量`_`

```
>>> tax = 12.5 / 100
>>> price = 100.50
>>> price * tax
12.5625
>>> price + _
113.0625
>>> round(_, 2)
113.06
```


#### 字符串的多行赋值
```
print("""\    #可测试没有改换行符的情景
Usage: thingy [OPTIONS]
     -h                        Display this usage message
     -H hostname               Hostname to connect to
""")
```


### 数字类型
#### 数字处理
``` python
number_a = 5.99
number_b = int(a)
print(number_b)   #输出5
print(17 // 3)  # 输出5，丢弃小数部分。
print(5 ** 2)   #5的2次方,25
```

### 集合类型
集合类型十分的强大，最常见的就是list类型，和str有很多的共性。
```
a = ['a', 'b', 'c']
n = [1, 2, 3]
x = [a, n]
x
[['a', 'b', 'c'], [1, 2, 3]]
x[0]
['a', 'b', 'c']
x[0][1]
'b'
```

### 关键字
#### end的使用 避免打印新行
``` python
>>> a, b = 0, 1
>>> while b < 1000:
...     print(b, end=',')
...     a, b = b, a+b
...
1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,
```

## 控制流程
### if语句（使用elif代替其他编程语言的else if，减少冗余的缩进）
``` python
>>> x = int(input("Please enter an integer: "))
Please enter an integer: 42
>>> if x < 0:
...     x = 0
...     print('Negative changed to zero')
... elif x == 0:
...     print('Zero')
... elif x == 1:
...     print('Single')
... else:
...     print('More')
...
```

### for语句（只能在子语句中终结循环）
在遍历子语句中操作这个循环对象，建议使用切片符号进行显示的对象拷贝。比如下面两种情况：
``` python
>>> for w in words[:]:  # Loop over a slice copy of the entire list.
...     if len(w) > 6:
...         words.insert(0, w)
...
>>> words
['defenestrate', 'cat', 'window', 'defenestrate']

#如果是下面这种，插入操作将会一直不停的执行。永远不会结束。
>>> for w in words:
...     if len(w) > 6:
...         words.insert(0, w)
...
>>> words
['defenestrate', 'cat', 'window', 'defenestrate']
```

### range函数
遍历一个数字类型的序列使用range函数将会很方便，支持等差数的遍历。
``` python
range(5, 10)
   5 through 9

range(0, 10, 3)
   0, 3, 6, 9

range(-10, -100, -30)
  -10, -40, -70
  
>>> a = ['Mary', 'had', 'a', 'little', 'lamb']
>>> for i in range(len(a)):
...     print(i, a[i])
...
0 Mary
1 had
2 a
3 little
4 lamb
```

循环块中可以有else语句，当for循环结束后，或者while条件为false后执行，但是当break跳出循环的时候是不会执行else的。比如一个求质数的例子。
``` python
>>> for n in range(2, 10):
...     for x in range(2, n):
...         if n % x == 0:
...             print(n, 'equals', x, '*', n//x)
...             break
...     else:
...         # for遍历完成并且没有找到一个因数
...         print(n, 'is a prime number')
...
2 is a prime number
3 is a prime number
4 equals 2 * 2
5 is a prime number
6 equals 2 * 3
7 is a prime number
8 equals 2 * 4
9 equals 3 * 3
```

## 函数
### 函数的定义
def关键字定义一个函数，后面跟形参，方法内容从下一行开始，必须缩进。
每一个函数都会返回一个值，即使他没有返回（return）语句，没有返回语句的会返回None。

### 函数的可变长度形参传递和参数默认值设置
> 定义一个函数
``` python
def ask_ok(prompt, retries=4, reminder='Please try again!'):
    while True:
        ok = input(prompt)
        if ok in ('y', 'ye', 'yes'):
            return True
        if ok in ('n', 'no', 'nop', 'nope'):
            return False
        retries = retries - 1
        if retries < 0:
            raise ValueError('invalid user response')
        print(reminder)
```
> 调用函数时，我们可以认为拥有默认值的参数是可以不参与参数传递的。
``` python
ask_ok('Do you really want to quit?')
ask_ok('OK to overwrite the file?', 2)
ask_ok('OK to overwrite the file?', 2, 'Come on, only yes or no!')
```

在方法定义层面，上述方法的调用有一个参数是必须要传递的（required argument），有三个是可选（optional arguments）的 (三个可选参数：state,action,type)
在调用方法层面，又可以分为 键值参数（keyword arguments）和位置参数（positional arguments）。如下所示：
``` python
def parrot(voltage, state='a stiff', action='voom', type='Norwegian Blue'):
    print("-- This parrot wouldn't", action, end=' ')
    print("if you put", voltage, "volts through it.")
    print("-- Lovely plumage, the", type)
    print("-- It's", state, "!")
	

方法调用时，值得注意的是，位置参数必须在键值参数之前：
parrot(1000)                                          # 1 positional argument
parrot(voltage=1000)                                  # 1 keyword argument
parrot(voltage=1000000, action='VOOOOOM')             # 2 keyword arguments
parrot(action='VOOOOOM', voltage=1000000)             # 2 keyword arguments
parrot('a million', 'bereft of life', 'jump')         # 3 positional arguments
parrot('a thousand', state='pushing up the daisies')  # 1 positional, 1 keyword	
```


### 可变长位置参数和可变长键值参数传递
**name代表任意长度的键值参数传递，*name代表任意长度的位置参数传递，*name必须在**name之前，如下：
``` python
def cheeseshop(kind, *arguments, **keywords):
    print("-- Do you have any", kind, "?")
    print("-- I'm sorry, we're all out of", kind)
    for arg in arguments:
        print(arg)
    print("-" * 40)
    for kw in keywords:
        print(kw, ":", keywords[kw])

		
调用方法：
cheeseshop("Limburger", "It's very runny, sir.",
           "It's really very, VERY runny, sir.",
           shopkeeper="Michael Palin",
           client="John Cleese",
           sketch="Cheese Shop Sketch")
		   
		   
打印结果：
-- Do you have any Limburger ?
-- I'm sorry, we're all out of Limburger
It's very runny, sir.
It's really very, VERY runny, sir.
----------------------------------------
shopkeeper : Michael Palin
client : John Cleese
sketch : Cheese Shop Sketch		   
```

> 另外，初始值只会在定义域初始化方法的时候计算一次，如果是可变对象如list和class实例，字典等的话会出现如下类似场景。

``` python
def f(a, L=[]):
    L.append(a)
    return L

print(f(1))
print(f(2))
print(f(3))

打印结果对应如下：
[1]
[1, 2]
[1, 2, 3]
```


## python中的数据结构
### 使用list作为stack
``` python
>>> stack = [3, 4, 5]
>>> stack.append(6)
>>> stack.append(7)
>>> stack
[3, 4, 5, 6, 7]
>>> stack.pop()
7
>>> stack
[3, 4, 5, 6]
>>> stack.pop()
6
>>> stack.pop()
5
>>> stack
[3, 4]
```

### 使用list作为queue
在队列中，list结尾的添加和弹出是很快的，但是list开头的插入和弹出是很慢的。
python为了提升队列的效率，python设计出了collections.deque，使用如下：
``` python
>>> from collections import deque
>>> queue = deque(["Eric", "John", "Michael"])
>>> queue.append("Terry")           # Terry arrives
>>> queue.append("Graham")          # Graham arrives
>>> queue.popleft()                 # The first to arrive now leaves
'Eric'
>>> queue.popleft()                 # The second to arrive now leaves
'John'
>>> queue                           # Remaining queue in order of arrival
deque(['Michael', 'Terry', 'Graham'])
```

### 递推式构造列表（List Comprehensions）

## 高级定义
### if \__name\__ == '\__main\__' 的含义
一个 Python 源码文件除了可以被直接运行外，还可以作为模块（也就是库）被导入。不管是导入还是直接运行，最顶层的代码都会被运行（Python 用缩进来区分代码层次）。而实际上在导入的时候，有一部分代码我们是不希望被运行的。
例如如下代码我们只想在当做独立脚本的时候运行main()方法,其他模块import该模块的时候不想运行main()方法。
``` python
PI = 3.14LEGB
def main():
    print "PI:", PI
main()
```
if \__name\__ == '\__main\__'则可以很顺畅的解决该问题。
\__name\__是一个导入相关的模块内置属性。该属性不可刻意使用，它是每一个模块的全限定名称，在导入系统（THE IMPORT SYSTEM）中只能并且可以标识一个模块。
如果一个模块被直接运行（直接或者间接称成为顶级代码[top-level code]运行时），则其没有包结构，其 \__name\__ 值为 \__main\__。

### CLASS描述
名字空间(Namespaces)
> 一些语言中比如c,c++,java 变量名是内存地址别名, 而Python的名字就是一个字符串，它与所指向的目标对象关联构成名字空间里面的一个键值对{name: object}，因此可以这么说，python的名字空间就是一个字典。
python里面有很多名字空间，每个地方都有自己的名字空间，互不干扰，不同空间中的两个相同名字的变量之间没有任何联系一般有4种: LEGB四种

    locals:函数内部的名字空间，一般包括函数的局部变量以及形式参数
    enclosing function:在嵌套函数中外部函数的名字空间.
    globals:当前的模块空间，模块就是一些py文件。也就是说，globals()类似全局变量。
    __builtins__: 内置模块空间，也就是内置变量或者内置函数的名字空间。
	
1.当程序引用某个变量的名字时，就会从当前名字空间开始搜索。搜索顺序规则便是: LEGB。2.使用locals()访问局部命名空间，使用globals()访问全局命名空间。




