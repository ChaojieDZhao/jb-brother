docker start `docker container name`  #启动容器
docker stop `docker container name`   #停止容器

echo "Asia/Shanghai" > /etc/timezone  #修改容器时间
cd /etc/ && cp  /usr/share/zoneinfo/Asia/Shanghai localtime


docker cp /usr/share/zoneinfo/Asia/Shanghai docker_kafka:/etc/
dc exec -ti docker_kafka sh -c 'mv /etc/Shanghai /etc/localtime'  #修改容器

## memcache使用

```
docker run -d  -p 11211:11211 --name docker_memcache memcached

telnet 127.0.0.1 11211 #链接memcached

add test_1 0 0 2 #测试memcached命令
12

get test_1

delete test_1 
quit #退出
```

## mongodb使用
```
docker run -d -p 27017:27017 -v /home/docker/mongodb:/data/db --name docker_mongodb mongo

docker exec -it docker_mongodb mongo admin #在容器中执行mongo命令，使用admin库添加用户
use admin
db.createUser({user:"miguTek",pwd:"miguTekPass",roles:["root"]})
db.auth("miguTek", "miguTekPass")
exit #退出

docker exec -it docker_mongodb bash #进入容器交互命令
cd /bin
mongo

show dbs;
show collections; 
show users;  
use db;
db.help();
```


## kafka使用

```
docker pull wurstmeister/zookeeper
docker pull wurstmeister/kafka

docker run -d --name docker_zookeeper -p 2181:2181 wurstmeister/zookeeper

docker run  -d --name docker_kafka \
-p 9092:9092 \
-e KAFKA_BROKER_ID=0 \
-e KAFKA_ZOOKEEPER_CONNECT=200.200.6.17:2182 \
-e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://200.200.6.17:9092 \
-e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 wurstmeister/kafka 

docker exec -ti docker_kafka /bin/bash #进入docker_kafka容器交互命令
cd /opt/kafka_
bin/kafka-topics.sh --list --zookeeper 192.168.134.122:2181
bin/kafka-topics.sh --describe --zookeeper 192.168.134.122:2181 --topic springCloudBus
bin/kafka-console-producer.sh --broker-list 192.168.134.122:9092 --topic springCloudBus
bin/kafka-console-consumer.sh --bootstrap-server 192.168.134.122:9092 --topic springCloudBus --from-beginning
bin/kafka-consumer-groups.sh --bootstrap-server 192.168.134.122:9092 --list
```

## kibana使用

```
docker run -d --name docker_kibana -e ELASTICSEARCH_URL=http://192.168.134.122:9200 -p 5601:5601 kibana:5.4.3
curl http://localhost:5601/
```

## MYSQL使用

```
 sudo chown -R 122:122 /home/docker/mysql/logs

docker run -d -p 3306:3306 --name docker_mysql -v $PWD/mysql/conf:/etc/mysql/conf.d -v $PWD/mysql/logs:/var/log/mysql \
 -v $PWD/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456  mysql:5.7
 
 docker exec -ti docker_mysql /bin/bash
docker exec -ti docker_mysql sh -c "mysql -u root -p"
```

## redis

```cmd
docker pull redis:3.2
docker run -d -p 6379:6379 -v $PWD/data:/data --name docker_redis redis:3.2 redis-server --appendonly yes
```
 

## rabbitmq

```
docker pull rabbitmq:3.7.7-management
docker run -d --name docker_rabbitmq -p 5672:5672 -p 15672:15672 -v `pwd`/rabbitmq/data:/var/lib/rabbitmq -e RABBITMQ_DEFAULT_VHOST=my_vhost  -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin rabbitmq:3.7.7-management 
```


## FTP

```
docker run -d -v /var/local/ftp:/home/vsftpd \
-p 20:20 -p 21:21 -p  21100-21110:21100-21110 \
-e FTP_USER=userftp -e FTP_PASS=123456 \
-e PASV_ADDRESS=200.200.6.18 \
-e PASV_MIN_PORT=21100 -e PASV_MAX_PORT=21110 \
--name docker_vsftpd --restart=always fauria/vsftpd
	
docker exec -i -t docker_vsftpd bash
```

## 常用的命令

```cmd
docker image rm -f  $(docker image ls | grep 'report-web*' |  awk -F ' ' '{print $3}')  

docker image rm -f  $(docker image ls | grep 'spring-cloud-*' |  awk -F ' ' '{print $3}')    #批量删除镜像
docker container rm -f  $(docker container ls | grep 'spring-cloud-*' |  awk -F ' ' '{print $1}')     #批量删除容器
docker rmi $(docker images -f "dangling=true" -q)    #删除无名称的镜像

docker exec -ti discover_eureka sh    #进入airpline（默认只有sh）容器
```
    
##
```
#!/bin/bash

CONTAINER_NAME=report-web
IMAGE_NAME=$CONTAINER_NAME

echo  "rebuild image and container：$IMAGE_NAME $CONTAINER_NAME"

docker container rm -f $CONTAINER_NAME

docker image rm -f  $(docker image ls | grep $CONTAINER_NAME* |  awk -F ' ' '{print $3}')

docker build -t report/$CONTAINER_NAME:1.0 .

docker run -p 8090:8090 --name $CONTAINER_NAME --link redis:redis -v /etc/localtime:/etc/localtime -v /mydata/app/report/:/home/report -d report/report-web:1.0

docker logs -f -t --tail 100 $CONTAINER_NAME
```