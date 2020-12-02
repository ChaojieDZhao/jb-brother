---
title: jquery选择器记录
date: 2016-07-30 18:41:00
tags:
- jquery
---


## jQuery 元素选择器
jQuery 使用 CSS 选择器来选取HTML元素。
$("p") 选取p元素。
$("p.intro") 选取所有class="intro"的p元素。
$("p#demo") 选取所有id="demo"的p元素。

## jQuery 属性选择器
jQuery 使用 XPath 表达式来选择带有给定属性的元素。
$("[href]") 选取所有带有 href 属性的元素。
$("[href='#']") 选取所有带有 href 值等于 "#" 的元素。
$("[href!='#']") 选取所有带有 href 值不等于 "#" 的元素。
$("[href$='.jpg']") 选取所有 href 值以 ".jpg" 结尾的元素。

## jQuery CSS 选择器
jQuery CSS 选择器可用于改变 HTML 元素的 CSS 属性。
下面的例子把所有 p 元素的背景颜色更改为红色：
$("p").css("background-color","red");

## type选择器
$(":file") 选择所有type等于file的元素

## 经典问题
$('div', $('#pick')) 表示的是获取id为pick的dom里面的所有的div
$("p").filter(".intro")  带有类名 "intro" 的所有p元素
$("p").eq(1)   选取第二个p元素

如何获取兄弟元素？
$('#id').siblings()   当前元素所有的兄弟节点
$('#id').prev()       当前元素前一个兄弟节点
$('#id').prevAll()   当前元素之前所有的兄弟节点
$('#id').next()       当前元素之后第一个兄弟节点
$('#id').nextAll()    当前元素之后所有的兄弟节点

如何插入元素?
$('#id').append()     在被选元素的结尾插入内容
$('#id').prepend()     在被选元素的开头插入内容
$('#id').after()        在被选元素之后插入内容
$("<span>Hello world!</span>").insertAfter("p");   在p元素后面插入span内容
$('#id').before()      在被选元素之前插入内容







