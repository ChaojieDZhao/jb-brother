nginx有一个master进程和多个worker进程，主进程负责读取和评估配置和维护工作进程。
工作进程真正的处理请求，
nginx使用事件驱动模型和系统依赖机制有效的进行请求的分发。
工作进程的数量可以固定，也可以根据cpu的数量动态进行调整。

如果符合多个路径，则会跳转到最长的那个路径。

```cmd
yum -y install make zlib zlib-devel gcc-c++ libtool  openssl openssl-devel
rm -rf /var/spool/mail/nginx    #可以先删除
groupadd -g 1006 nginx
useradd -u 1006 -g 1006 -d /usr/share/nginx -s /sbin/nologin nginx    #不允许登录
passwd nginx

sudo chmod -R 755 /usr/share/nginx

./configure --prefix=/usr/local/nginx \
--pid-path=/usr/local/nginx/run/nginx.pid \
--with-http_ssl_module \
--user=nginx \
--group=nginx \
--with-pcre \
--without-ma    il_pop3_module \
--without-mail_imap_module \
--without-mail_smtp_module

make & make install
```

## 常用命令

```cmd
sudo /usr/local/nginx/sbin/nginx    #以root方式运行

cd /usr/local/nginx/
sbin/nginx –c conf/nginx.conf
sbin/nginx –s  stop — fast shutdown
sbin/nginx –s  reload — reloading the configuration file
sbin/nginx –s  reopen — reopening the log files
sbin/nginx –s  quit — graceful shutdown

kill -s QUIT 1628    #直接通过kill发送信号给工作进程优雅停止
```

## graceful shutdown

to stop nginx processes with waiting for the worker processes to finish serving current requests, 
