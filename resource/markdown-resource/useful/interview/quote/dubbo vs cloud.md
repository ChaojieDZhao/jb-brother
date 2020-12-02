## glossary 活跃度负载均衡

每个调用方服务维护一个活跃数计数器。当A机器开始处理请求，该计数器加1，此时A还未处理完成。
若处理完毕则计数器减1。而B机器接受到请求后很快处理完毕。那么A,B的活跃数分别是1，0。当又产生了一个新的请求，则选择B机器去执行(B活跃数最小)，这样使慢的机器A收到少的请求。

# Dubbo Vs SpringCloud(远程调用模块)

- Dubbo 支持更多功能、更灵活、支持高并发的RPC框架。

- SpringCloud全家桶里面（Feign、Ribbon、Hystrix），特点是非常方便。两者有很多共性（依赖注册中心、负载均衡），下面主要从区别上罗列分析。

1. Dubbo支持多传输协议(Dubbo、Rmi、http、redis等等)，可以根据业务场景选择最佳的方式。非常灵活。

默认的Dubbo协议：利用Netty，TCP传输，单一、异步、长连接,适合数据量小、高并发和服务提供者远远少于消费者的场景。

而SpringCloud远程调用的Feign组件是基于Http传输协议，短连接，不适合高并发的访问。

2. Dubbo和Ribbon（Feign默认集成Ribbon）都支持负载均衡策略，但是Dubbo支持的更灵活

- Dubbo支持4种算法（随机、轮询、活跃度、Hash一致性），而且算法里面引入权重的概念，Ribbon只支持N种策略：轮询、随机、ResponseTime加权

- Dubbo配置的形式不仅支持代码配置，还支持Dubbo控制台灵活动态配置

- Dubbo负载均衡的算法可以精准到某个服务接口的某个方法，而Ribbon的算法是Client级别的

```

  <dubbo:reference interface="com.mor.server.dubbo.service.DemoServe">

      <dubbo:method name="sayHello" loadbalance="roundrobin"/>

  </dubbo:reference>

  spring-cloud-psm-user.ribbon.NFLoadBalancerRuleClassName=com.netflix.loadbalancer.RandomRule

```

3. 容错策略不同

- Dubbo支持多种容错策略：failover、failfast、brodecast、forking等，也引入了retry次数,timeout等配置参数

- Feign是利用熔断机制来实现容错的，处理的方式不一样

4. Dubbo支持更多更灵活的并发控制

- 客户端配置actives参数，配置单个Cunsumer最大并发请求数，超出则线程阻塞等待，超时报错

- Provider可以配置executes参数来限制最大的并发线程数，超出报错

- Provider可以配置accepts参数来限制最大长连接数来限制最大的连接数

- Provider的通过配置任务线程池的类型和最大线程数来控制并发量，超负载直接丢弃

5. Dubbo支持更完善的监控和管理界面，SC也有Actuator等工具进行监控，但是并不是针对远程调用这一块的

6. Dubbo支持客户端设置调用结果缓存，支持配置3种策略的结果缓存(LRU、LFU、FIO)，但是要自己实现超时管理

```

<dubbo:reference id="demoService"  cache="lru" />
