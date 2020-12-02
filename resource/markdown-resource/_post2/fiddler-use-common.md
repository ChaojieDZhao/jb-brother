---
title: fidder使用总结
date: 2017-03-12 22:02:43
tags:
- fidder
---

## 使用Fiddler抓取夜神模拟器
> 1.点击Tools-Fiddler Options进入Fiddler Options页面。
2.点击Connections，将Fiddler listens on port设为8800，勾选Allow remote computers to connect。
3.点击OK，代理设置完成，重启Fiddler配置生效。
4.夜神模拟器点击设置，进入到wifi连接选项。
5.点击wifi进入wifi选项，长按热点，出现修改网络的弹窗。
6.点击修改网络，勾选高级选项,将代理设为手动，代理服务器主机名填写电脑的ip，端口号填写8800。
7.点击保存。（和我们手机客户端原理一样）搜索OnBeforeRequest关键字。

## 修改请求头Host
> 现在微服务提的比较广泛，业务会拆分为很多的服务模块，如果想测试一个服务但是需要其他服务的支持（比如一般情况下需要登录才可以查看对应活动，假设对活动模块进行了拆分），是不是需要你本地把所有的服务都启动起来？
这显然是不现实的，下面fiddler中的一种方法可以进行模块的分离测试，主要原理就是针对远程服务的IP替换为本机的IP，这样对应服务就会从本地访问来获取数据。
工具栏查找 Rules > Customize Rules 
找到OnBeforeRequest方法在方法开头添加以下代码。
``` javascript
 if (oSession.host.toLowerCase()== 'alldios.com')
    {
       oSession.host='192.168.0.110:8882';
       oSession["ui-color"] = "orange";
    }
```