- JAVASCRIPT对大小写敏感
- 在一个语句中声明多个变量
- 没有值的变量返回值 undefined
- 重新声明变量不会破坏值

```
var carName = "porsche";
var carName;
console.log(carName)    //porsche
``` 

- 字符串和数字之间相加

```
8" + 3 + 5;    //835
3 + 5 + "8"    //88
``` 

- 获取变量的类型

```
type of 0     //number
type of name    //undifined
```

- 访问对象属性

```
person.property
person["property"]
```

- 方法访问对象属性

```
var person = {
    firstName: "Bill",
    lastName : "Gates",
    id       : 12345,
    fullName : function() {
       return this.firstName + " " + this.lastName;
    }
};
```

- 属性访问对象属性

```
var person = {
    firstName: "Bill",
    lastName : "Gates",
    id       : 12345,
    fullName : function() {
       return this.firstName + " " + this.lastName;
    }
};
```

- 调用函数必须添加()，不然会直接渲染函数声明
- 用反斜杠对文本字符串折行
- 可以使用指数计数法编写极大或极小的数，数字会被精确到 15 位
- 在乘，除，减重java会将文字转化为数字，反之加法不会。
- NaN 的类型是数字（没错！typeof NaN 返回 number）
- 如果计算出最大可能数字之外的数字，则返回无穷大（Infinity）
- Infinity 也是数（typeof Infinity 返回 number）
- 前缀为 0x 的常量会被解释为十六进制 
- 绝对不要把数字设置为对象

```
var x = new Number(500);  // x 是对象
var y = new Number(500);  // y 是对象
x == y    //false
```

- 使用内置对象

```
var x1 = new Object();   // 新的 Object 对象
var x2 = new String();   // 新的 String 对象
var x3 = new Number();   // 新的 Number 对象
var x4 = new Boolean();  // 新的 Boolean 对象
var x5 = new Array();  // 新的 Array 对象
var x6 = new RegExp();   // 新的 RegExp 对象
var x7 = new Function(); // 新的 Function 对象
var x8 = new Date();   // 新的 Date 对象
```

- 对对象副本的任何更改也将更改原始对象

```

var person = {firstName:"Bill", lastName:"Gates", age:62, eyeColor:"blue"}
var x = person;
x.age = 10;
person.firstName + " is " + person.age + " years old."    //Bill is 10 years old.
```

- `for in`访问对象的所有属性

```
var txt = "";
var person = {fname:"Bill", lname:"Gates", age:62}; 
var x;
for (x in person) {
  txt += person[x] + " ";
}
txt    //Bill Gates 62
```

- 删除对象的属性

```  
var person = {
  firstname:"Bill",
  lastname:"Gates",
  age:62,
  eyecolor:"blue"
};

delete person.age;
```

- 访问对象的属性可以使用点号表示法，和括号表示法
- 删除数组的某一项，数组长度不变，索引不会变，内部的值变成undefined

``` 
var myObj, i, x = "";
myObj = {
  "name":"Bill Gates",
  "age":62,
  "cars": ["Porsche","BMW","Volvo"]
}   
delete myObj.cars[2];
```