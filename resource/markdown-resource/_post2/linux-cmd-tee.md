
---
title: tee命令使用
date: 2016-09-28 17:22:24
tags:
- linux
---

tee命令用于将数据重定向到文件，另一方面还可以提供一份重定向数据的副本作为后续命令的stdin。简单的说就是把数据重定向到给定文件和屏幕上。

列如 `ls -l |tee file.txt|cat`

tee和echo类似，可以把打印内容输入到一个文件，常和`<<`使用   
`<< `字符表示从命令行读取输入，直到一个与<< 所跟内容相同的字符结束

```  shell
[root@alldios~]#tee /etc/yum.repos.d/docker.repo <<-'EOF'  #创建一个文件，内容就是下面命令行EOF之前的输入。
[dockerrepo]
name=Docker Repository
baseurl=https://yum.dockerproject.org/repo/main/centos/7/
enabled=1
gpgcheck=1
gpgkey=https://yum.dockerproject.org/gpg
EOF
```

