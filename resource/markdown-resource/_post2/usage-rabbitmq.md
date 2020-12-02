---
title: rabbitmq使用
date: 2017-08-27 20:01:36
tags:
- rabbitmq
---

## Rabbitmq的基本概念
### Queue（队列）
> RabbitMQ的内部对象，用于存储消息。生产者最终的消息投递到Queue中，然后消费者从Queue中获取消息并且消费。多个消费者可以订阅同一个Queue，这样的话消息会被平均分摊给消费者并处理。

### Message acknowledgment（消息回执）
> 消费者收到Queue中的消息但是没有处理完成的话就Down机了，这种情况下可能导致消息丢失，因此需要消费者在消费完消息后发送一个回执给RabbitMQ，
RabbitMQ收到消息回执（Message acknowledgment）后才将消息从Queue中移除，如果RabbitMQ没有收到回执并检测到消费者的RabbitMQ链接断开，则RabbitMQ会将该消息发送给其他消费者（如果存在多个消费者进行处理的话）。
这里不存在timeout概念，一个消费者处理消息时间再长也不会导致该消息被发送到其他消费者，除非他的RabbitMQ链接断开。***所以处理完之后一定要有回执（Message acknowledgment）***。

### Message durability
> 如果希望RabbitMQ服务器重启的情况下，也不想丢失数据，我们可以设置Queue和Messgae都设置为可持久化的（duration），这样可以保证绝大部分情况我们的RabbitMQ的消息不会丢失。但是依然解决不了小概率丢失时间的发生（比如RabbitMQ服务器已经接受了生产者的消息，但还是没有来得及持久化该消息的时候RabbitMQ断电了），如果需要对这种小概率时间也要管理起来，那么我们要用到事物。

### Prefetch count
> 我们提到多个消费者同时订阅同一个Queue中的消息，Queue中的消息就会被平摊给多个消费者。这是如果每个消息的处理时间不同，就有可能会导致某些消费者一直在忙，而有些消费者很快就处理完了手头工作并且一直处于空闲的状态。我们可以通过设置PrefetchCount来限制Queue每次发送给每个消费者的消息数，比如我们设置为PrefetchCount=1，则Queue每次给每个消费者发送一条消息，消费者处理完这条消息后Queue会再给该消费者发送一条消息。

### Exchange
> 我们在之前提到过生产者将消息投递到Queue中，实际上这在RabbitMQ中这种情况是永远不会发生的。实际的情况是，生产者将消息发送到Exchange（交换器），由Exchange将消费路由到一个或者多个Queue中。
```java_holder_method_tree
channel.exchangeDeclare(EXCHANGE_NAME, "fanout");    //声明一个fanout类型的交换机
```

### Exchange Type
> RabbitMQ常用的Exchange Type有fanout,direct,topic,header这四种（AMQP规范里面还提到了两种类型分别是system和自定义）。

#### fanout
> fanout类型的Exchange路由规则非常简单，就是把所有发送到该Exchange的消息路由到所有与他绑定的Queue中。

#### direct
> direct类型的Exchange路由规则也很简单，他会把消息路由到那些Binding Key与Routing Key完全匹配的Queue中。

#### topic
> 前面讲到direct类型的Exchange路由规则是完全匹配binding key与routing key，但这种严格的匹配方式在很多情况下不能满足实际业务需求。topic类型的Exchange在匹配规则上进行了扩展，它与direct类型的Exchage相似，也是将消息路由到binding key与routing key相匹配的Queue中，但这里的匹配规则有些不同，它约定：
- routing key为一个句点号“. ”分隔的字符串（我们将被句点号“. ”分隔开的每一段独立的字符串称为一个单词），如“stock.usd.nyse”、“nyse.vmw”、“quick.orange.rabbit”
- binding key与routing key一样也是句点号“. ”分隔的字符串
- binding key中可以存在两种特殊字符“*”与“#”，用于做模糊匹配，其中“*”用于匹配一个单词，“#”用于匹配多个单词（可以是零个）

#### headers
> headers类型的Exchange不依赖于routing key与binding key的匹配规则来路由消息，而是根据发送的消息内容中的headers属性进行匹配。
在绑定Queue与Exchange时指定一组键值对；当消息发送到Exchange时，RabbitMQ会取到该消息的headers（也是一个键值对的形式），对比其中的键值对是否完全匹配Queue与Exchange绑定时指定的键值对；如果完全匹配则消息会路由到该Queue，否则不会路由到该Queue。   
该类型的Exchange没有用到过（不过也应该很有用武之地），所以不做介绍。

### Binding
> RabbitMQ中通过Binding将Exchange与Queue关联起来，这样RabbitMQ就知道如何正确的将消息路由到指定的Queue了。

### Binding Key
> 在绑定（Binding)Exchange与Queue的同时，一般会指定一个Binding Key（parkos.41010502111111）; 
> 在绑定多个Queue到同一个Exchange的时候，这些Binding允许使用想用的Binding Key。   
> Binding Key并不是在所有情况下都生效，他依赖于Exchange Type，比如fanout类型的Exchange就会无视Binding Key，而是将消息路由到所有绑定到该Exchange的Queue。
```java_holder_method_tree
channel.queueBind(queueName, EXCHANGE_NAME, "parkos.41010502111111");   
```

### Routing Key（生产者发送信息时指定，通常情况下，direct，topic类型的交换机，如果Routing Key和Binding Key匹配，则该队列就会获取该消息）
> 生产者将消息发送给Exchange的时候，一般会指定一个Routing Key（parkos.41010502111111），来指定这个消息的路由规则，而这个Routing Key需要与Exchange Type和Binding Key联合使用才能最终生效。在Exchange Type与Binding Key固定的情况下（在正常使用时一般这些内容都是固定配置好的），我们的生产者就可以在发送消息给Exchange时，通过指定Routing Key来决定消息流向哪里。RabbitMQ为Routing Key设定的长度限制为255 bytes。
```java_holder_method_tree
this.rabbitTemplate.convertAndSend(exchange, "parkos.41010502111111", message, new MessagePostProcessor() {
        @Override
        public Message postProcessMessage(Message msg) throws AmqpException {
            msg.getMessageProperties().setPriority(message.getPri());
            return msg;
        }
    }, correlationData);
```

#### RPC
> MQ本身是基于异步的消息处理，前面的示例中所有的生产者（P）将消息发送到RabbitMQ后不会知道消费者（C）处理成功或者失败（甚至连有没有消费者来处理这条消息都不知道）。    
但实际的应用场景中，我们很可能需要一些同步处理，需要同步等待服务端将我的消息处理完成后再进行下一步处理。这相当于RPC（Remote Procedure Call，远程过程调用）。在RabbitMQ中也支持RPC。    
RabbitMQ基础概念详细介绍    
RabbitMQ中实现RPC的机制是：    
> 客户端发送请求（消息）时，在消息的属性（MessageProperties，在AMQP协议中定义了14中properties，这些属性会随着消息一起发送）中设置两个值replyTo（一个Queue名称，用于告诉服务器处理完成后将通知我的消息发送到这个Queue中）和correlationId（此次请求的标识号，服务器处理完成后需要将此属性返还，客户端将根据这个id了解哪条请求被成功执行了或执行失败）
服务器端收到消息并处理       
服务器端处理完消息后，将生成一条应答消息到replyTo指定的Queue，同时带上correlationId属性    
客户端之前已订阅replyTo指定的Queue，从中收到服务器的应答消息后，根据其中的correlationId属性分析哪条请求被执行了，根据执行结果进行后续业务处理       

#### Virtual hosts
> 每个virtual host本质上都是一个RabbitMQ Server，拥有它自己的queue，exchagne，和bings rule等等。这保证了你可以在多个不同的application中使用RabbitMQ。


## 安装RabbitMQ
### 源码安装Erlang
``` shell
yum -y install wget
wget -c http://www.erlang.org/download/otp_src_19.2.tar.gz   (该链接务必从官网拿到,这文件不是一般的大~~)
tar -xvf otp_src_19.2.tar.gz 
yum install gcc gcc-c++glibc-devel xz make ncurses-devel openssl-devel xmlto
yum -y install perl ncurses-devel

cd otp_src_19.2
./configure --prefix=/opt/erlang --with-ssl -enable-threads -enable-smmp-support -enable-kernel-poll --enable-hipe --without-javac
make && make install
vi /root/.bashrc
添加
export ERLANG_HOME=/opt/erlang
export PATH=$ERLANG_HOME/bin:$PATH

erl --version(查看是够安装成功,到此完成了)
```

### rpm安装Erlang
> 去https://github.com/rabbitmq/erlang-rpm/releases下载erlang-rpm
```
yum -ivh erlang-rpm
```

### 安装和使用rabbitmq
``` shell
wget -c https://dl.bintray.com/rabbitmq/rabbitmq-server-rpm/rabbitmq-server-3.6.12-1.el7.noarch.rpm
yum -y install socat    #安装依赖的插件
yum -y install rabbitmq-server-3.6.12-1.el7.noarch.rpm    #至此安装成功
systemctl enable rabbitmq-server    #开启服务
systemctl start rabbitmq-server    #启动服务
rabbitmqctl add_user jiege jiege    #添加用户
rabbitmqctl set_user_tags jiege administrator    #设置角色  
rabbitmqctl set_permissions -p "/" jiege ".*" ".*" ".*"     #赋予权限
rabbitmq-plugins enable rabbitmq_management    #开启远程服务
```

### 消息回调
> 如果消息没有到exchange,则confirm回调,ack=false
如果消息到达exchange,则confirm回调,ack=true
exchange到queue成功,则不回调return
exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)




