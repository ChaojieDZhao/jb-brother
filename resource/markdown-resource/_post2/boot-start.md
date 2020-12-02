---
title: 服务端的boot程序
date: 2017-07-08 19:05:19
tags:
- spring boot
---

本项目举例[链接](https://gitee.com/Zalldios/spring-boot-linux.git)

## spring-boot windows服务端的启动
- 1 使用maven生成jar包
- 2 java -jar linuxx.jar 可跟profile文件
- 3 打包成可执行的war包[操作链接](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-create-a-deployable-war-file)

## spring-boot linux服务端的启动
### 关闭应用的脚本：`stop.sh`
``` shell
#!/bin/bash
PID=$(ps -ef | grep linuxx.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
    echo Application is already stopped
else
    echo kill $PID
    kill $PID
fi
```

### 启动应用的脚本：`start.sh`
``` shell
#!/bin/bash
nohup java -jar linuxx.jar --server.port=8888 &
```

### 整合了关闭和启动的脚本：`run.sh`
由于会先执行关闭应用，然后再启动应用，这样不会引起端口冲突等问题，适合在持续集成系统中进行反复调用。
``` shell
#!/bin/bash
echo stop application
source stop.sh
echo start application
source start.sh
```

## 第二种方式
```shell
#!/bin/bash
APP_NAME=linuxx.jar
usage() {
	echo "Usage: sh run.sh [start|stop|restart|status]"    
	exit 1
} 
is_exist(){  
	pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}' `  
	if [ -z "${pid}" ]; then   
		return 1  
	else    
		return 0  
	fi
} 
start(){  
	is_exist  
	if [ $? -eq "0" ]; then    
		echo "${APP_NAME} is already running. pid=${pid} ."  
	else    
		nohup java -jar $APP_NAME > /dev/null 2>&1 &  
	fi
} 
stop(){  
	is_exist  
	if [ $? -eq "0" ]; then    
		kill -9 $pid  
	else    
		echo "${APP_NAME} is not running"  
	fi  
} 
status(){  
	is_exist  
	if [ $? -eq "0" ]; then    
		echo "${APP_NAME} is running. Pid is ${pid}"  
	else    
		echo "${APP_NAME} is NOT running."  
	fi
} 
restart(){  
	stop  
	start
} 
case "$1" in  
	"start")    
	start    
	;;  
	"stop")    
	stop    
	;;  
	"status")    
	status    
	;;  
	"restart")    
	restart    
	;;  
	*)    
	usage    
	;;
esac
```

## 开启debug
```shell
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar demo.jar
```
