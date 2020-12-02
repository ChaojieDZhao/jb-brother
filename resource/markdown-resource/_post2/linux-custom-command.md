---
title: linux自定义命令
date: 2016-09-20 10:44:20
tags:
- linux
---
在linux服务器管理开发中，我们经常会要书写很长的命令，有的命令没有任何的参数但是非常的长，记忆上和书写上都需要很大的困难。我们怎么样能够快速根据一个需求来缩写执行一个相关命令？Linux中的自定义命令功能完全可以实现这种需求，无疑为开发团队提升了很大的效率。

一般来说，Shell的初始化脚本分为2种：一种是全局默认的，一种是用户自己的。
全局默认的路径为/etc/profile在profile中，它默认会再加载/etc/bash.bashrc。
另一种用户自己的就是$HOME目录下的.profile它默认会载加载.bashrc文件。
有点文件也会加载一些其他的脚本，这个具体视脚本而定。

#### 第一种  使用alias进行命令再次起名
如：快速清理Shell的命令历史记录，自己的别名叫clean：
alias clean="history -c ; history -w"
unalias clean #取消别名
**或者修改shell脚本文件**
编辑所用的 shell 配置文件，如 bash 的 .bashrc，zsh 的 .zshrc，如加入：
alias ws="cd ~/workspace"
重载该配置文件使更改生效，如source .bashrc。在任意位置输入ws，即可 cd 到 workspace 目录。
如果有很多 alias 命令，可单独定义一个文件存放。

#### 第二种 将可执行文件目录加入PATH
1、创建存放自定义命令的目录，如my_cmd
2、将该目录加入path中
> 编辑所用的 shell 配置文，如加入
> PATH=$PATH:~/my_cmd
> 也可以将该命令添加到方法一中单独创建的自定义命令存放文件中


3、重载该配置文件使更改生效，如 source .bashrc
4、将自定义的可执行程序放入 my_cmd 中，在 命令行中就可以直接执行了

