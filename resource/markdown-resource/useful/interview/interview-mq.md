## 延迟队列

可以指定队列中的消息在哪个时间点被消费。场景有下单成功后，在30分钟内没有支付，自动取消订单。
外卖平台发送订餐通知，下单成功后60s给用户推送短信。

**使用redis zset实现**   

通过zadd命令向队列delay queue中添加元素，并设置score值表示元素过期的时间。
消费端轮询队列delay queue，将元素排序后取最小时间与当前时间比对，如小于当前时间代表已经过期移除key。

**redis key值过期回调**    

1、修改redis.conf文件开启notify-keyspace-events Ex。     
2、注册过期监听时间即可。

**rabbitmq ttl和Dead Letter Exchanges实现**

单消息A0001发送到延迟队列order.delay.queue，并设置x-message-tt消息存活时间为30分钟，当到达30分钟后订单消息A0001成为了Dead Letter（死信），延迟队列检测到有死信，
通过配置x-dead-letter-exchange，将死信重新转发到能正常消费的关单队列，直接监听关单队列处理关单逻辑即可。


```java_holder_method_tree
/**
 * 发送消息
 */
public void send(String delayTimes) {
    amqpTemplate.convertAndSend("order.pay.exchange", "order.pay.queue","大家好我是延迟数据", message -> {
        // 设置延迟毫秒值
        message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
        return message;
    });
} 

/**
 * 延时队列
 */
@Bean(name = "order.delay.queue")
public Queue getMessageQueue() {
    return QueueBuilder
            .durable(RabbitConstant.DEAD_LETTER_QUEUE)
            // 配置到期后转发的交换
            .withArgument("x-dead-letter-exchange", "order.close.exchange")
            // 配置到期后转发的路由键
            .withArgument("x-dead-letter-routing-key", "order.close.queue")
            .build();
} 
```

## 优先级队列

创建优先级队列需要使用arguments声明（x-max-priority），创建优先级消息需要basic properties设置。   
1.优先级队列必须和优先级消息一起使用，才能发挥出效果，但是会消耗性能    
2.优先级队列必须在消费者繁忙的时候，才能对消息按照优先级排序    
3.非优先级队列发送优先级消息是不会排序的，所以向非优先级队列发送优先级是没有任何作用的     

## 顺序消息

为了解决消息顺序执行的问题，可以一个队列只有一个消费者，这样就不会出现消息的顺序判断。
或者如果出现性能问题，那就发送消息时候使用hash均衡一下。反正就是一个队列只有一个消费者。

## 消息模型

基本消息模型：RabbitMQ是一个消息代理：它接受和转发消息。 你可以把它想象成一个邮局：当你把邮件放在邮箱里时，你可以确定邮差先生最终会把邮件发送给你的收件人。 在这个比喻中，RabbitMQ是邮政信箱，邮局和邮递员。   

work消息模型：工作队列或者竞争消费者模式，就是多个消费者消费一个队列。

订阅模型：分为fanout(广播)，direct，topic。 

NOTE: 消费者确认消息消费发送ack分为自动和手动。生产者发送也需要消息回执确认。如果生产者发送到exchange的消息没有找到对应的队列，则可以使用return listener监听进行处理。

NOTE: 为了解决消费能力的差异问题，可以使用我们可以使用channel的basicQos方法和prefetchCount = 1设置。 
这告诉RabbitMQ一次不要向工作人员发送多于一条消息。 或者换句话说，不要向工作人员发送新消息，直到它处理并确认了前一个消息。 相反，它会将其分派给不是仍然忙碌的下一个工作人员。