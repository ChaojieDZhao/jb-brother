---
title: phantomjs使用
date: 2019-2-1 22:02:43
tags:
- phantomjs
---

> phantomjs是一个无头浏览器，也就是没有gui，是一个基于webkit的JavaScript API。      
它使用QtWebKit作为它核心浏览器的功能，使用webkit来编译解释执行JavaScript代码。
任何你可以在基于webkit浏览器做的事情，它都能做到。

# phantomjs常用功能
- 页面自动化
```javascript
var page = require('webpage').create();
page.open('http://www.sample.com', function() {
  page.includeJs("http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js", function() {
    page.evaluate(function() {
      $("button").click();
    });
    phantom.exit()
  });
});
```
上述操作加载了一个页面并且自动执行的$("button").click()事件

- 屏幕抓取
```javascript
var page = require('webpage').create();
page.open('http://github.com/', function() {
  page.render('github.png');
  phantom.exit();
});
```
上述操作加载了一个页面并且自动生成和保存为github.png图片，图片名称和页面路径都可以通过参数形式指定，另外还可以生成其他图片或pdf格式

- 功能性测试

- 网络监控

# phantomjs注意事项
phantomjs常被用来进行html转图片操作，也就是程序开发中的`生成分享海报`。    
值得注意的是，如果一个html声明页面字体phantomjs则会去系统上找寻对应的字体。    
如果没有找寻到则会使用默认字体，也就是说。    
如果声明了字体，则系统上必须有该字体，如果没有，**生成一个图片的时长消耗也会加大，影响效率**。
