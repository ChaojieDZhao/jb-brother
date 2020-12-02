---
title: docker使用记录
date: 2016-09-24 19:33:19
tags:
- linux
---

##  docker很常规的分为企业版和社区版本。作为开发来讲，社区版本就可以满足开发需求，

docker社区版本的安装[链接地址](https://docs.docker.com/engine/installation/linux/docker-ce/centos/#set-up-the-repository)

###  常用命令

```  shell
systemctl enable docker.service
Created symlink from /etc/systemd/system/multi-user.target.wants/docker.service to /usr/lib/systemd/system/docker.service.
systemctl start docker.service
systemctl stop docker.service
docker help查看帮助
```

### docker的三个概念

**仓库（repository）**
仓库是集中存放镜像文件的场所，仓库注册服务器（Registry）上往往存放着多个仓库，每个仓库中又包含了多个镜像，每个镜像有不同的标签（tag）。
目前，最大的公开仓库是 Docker Hub，存放了数量庞大的镜像供用户下载。

**镜像（image）**
一个镜像是可执行的包，包括了运行应用的一切需求，比如代码，运行环境，依赖包，和环境变量和配置文件。

**容器（ Container）**
容器是从镜像创建的运行实例，变成了一个有状态的镜像或者用户进程。


### docker的集群

> A swarm is a group of machines that are running Docker and joined into a cluster. After that has happened, you continue to run the Docker commands you’re used to, but now they are executed on a cluster by a swarm manager. The machines in a swarm can be physical or virtual. After joining a swarm, they are referred to as nodes.


###  container命令
``` shell
docker container ls -a    #展示所有应用
docker run -d -p 8761:8761 --name discover_eureka my/eureka:0.0.1    #后台（-d  代表detached mode）运行该应用
docker run -d -p 8761:8761 -v /home/docker/cloud/logs/:/logs --name discover_eureka my/eureka:0.0.1
docker run -d -p 5000:5000 --restart=always --name registry registry:2
docker logs -f -t --tail 100 docker_zookeeper
docker container stop <Container NAME or ID>          # 优雅的退出该应用
docker container kill <Container NAME or ID>          # 强制终止该应用
docker container rm $(docker container ls -a -q)         # 删除所有应用
docker image ls -a                             # 展示所有镜像
docker image rm $(docker image ls -a -q)   # 删除所有镜像
docker login    # Log in this CLI session using your Docker credentials
docker logout    #登出
docker tag <image> username/repository:tag    # 为这个镜像命名方便推到远程服务器，远程仓库username/repository需要与本地镜像的仓库名一直才可以推
docker push username/repository:tag            # Upload tagged image to registry
docker run username/repository:tag                   # Run image from a registry
 docker save -o with-font.tar openjdk/8-openjdk-aipine:with-font    #保存镜像到文件
 docker load -i docker-archive/with-font.tar     #加载文件到镜像
```

### swarm 命令

```  shell
curl -L https://github.com/docker/machine/releases/download/v0.13.0/docker-machine-`uname -s`-`uname -m` >/tmp/docker-machine && chmod +x /tmp/docker-machine && sudo cp /tmp/docker-machine /usr/local/bin/docker-machine    #安装docker-machine
docker-machine version 
rm $(which docker-machine)
docker-machine rm -f $(docker-machine ls -q)
docker stack ls                                            # List stacks or apps
docker stack deploy -c <composefile> <appname>  # Run the specified Compose file
docker service ls                 # List running services associated with an app
docker service ps <service>                  # List tasks associated with an app
docker inspect <task or container>                   # Inspect task or container
docker container ls -q                                      # List container IDs
docker stack rm <appname>                             # Tear down an application
docker swarm leave --force    # Take down a single node swarm from the manager
```

### 一些配置文件

/etc/docker/daemon.json
```shell
{
  "registry-mirrors":["https://registry.docker-cn.com"],
  "insecure-registries":["192.168.134.128:5000"]
}
```

```shell 
alias di='docker image'
alias dis='docker images'
alias dirm='docker image rm -f'

alias dc='docker container'
alias dcls='docker container ls'
alias dcrm='docker container rm -f'
alias dcsa='docker stop $(docker container ls -a -q)'
alias dcrr='docker run -d -p 5000:5000 --restart=always --name registry registry:2'

alias didf='docker system df'  #查看镜像体积

```

更过命令解释详见[地址链接](https://docs.docker.com/reference/)


如果想要将Docker应用于具体的业务实现，是存在困难的——`编排、管理和调度`等各个方面，都不容易。于是，人们迫切需要一套管理系统，对Docker及容器进行更高级更灵活的管理。
K8S，就是基于容器的集群管理平台，它的全称，是kubernetes。