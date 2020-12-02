# mybatis对session的处理。
有状态就是有数据存储功能。有状态对象(Stateful Bean)，就是有实例变量的对象  ，可以保存数据，是非线程安全的。在不同方法调用间不保留任何状态。    
无状态就是一次操作，不能保存数据。无状态对象(Stateless Bean)，就是没有实例变量的对象  .不能保存数据，是不变类，是线程安全的。    

## mybatis sqlsession对象
因为mybatis的sqlSesssion 执有Exector对象。所以他是一个有状态的对象。官方给出的解释也是说这个对象是非线程安全的。
但是mybatis对这个对象做了一个封装SqlSessionTemplate对象。SqlSessionTemplate对象对sqlsession实现线程安全是实现方式。就是用java的动态代理。
sqlsessionTemplate内部执有一个对sqlsession的一个代理对象。这个代理对象的实现就是生成一个新的sqlsession 这个sqlsession对象是从sqlsessionUtil.getsqlsession获取到的。
这个类是在org.mybatis.spring包下面的是集成了spring的 。   它获取sqlsession的时候会先从spring中去获取。如果开启了spring的事务管理。
那么你本次调用的sqlsession就会是你这次会话一个共用的sqlsession, 如果你没有开启事务的话，每次更新就会是一个新的sqlsession。

## mybatis一级缓存
MyBatis 默认开启了一级缓存，一级缓存是在SqlSession 层面进行缓存的。即，同一个SqlSession ，多次调用同一个Mapper和同一个方法的同一个参数，只会进行一次数据库查询。    
然后把数据缓存到缓冲中，以后直接先从缓存中取出数据，不会直接去查数据库。        
但是不同的SqlSession对象，因为不用的SqlSession都是相互隔离的，所以相同的Mapper、参数和方法，他还是会再次发送到SQL到数据库去执行。    

## mybatis二级缓存
需要缓存在SqlSessionFactory层面给各个SqlSession 对象共享。默认二级缓存是不开启的，需要手动进行配置。
```text
<cache eviction="LRU" flushInterval="100000" size="1024" readOnly="true"/>

eviction：缓存回收策略
- LRU：最少使用原则，移除最长时间不使用的对象
- FIFO：先进先出原则，按照对象进入缓存顺序进行回收
- SOFT：软引用，移除基于垃圾回收器状态和软引用规则的对象
- WEAK：弱引用，更积极的移除移除基于垃圾回收器状态和弱引用规则的对象
flushInterval：刷新时间间隔，单位为毫秒，这里配置的100毫秒。如果不配置，那么只有在进行数据库修改操作才会被动刷新缓存区
size：引用额数目，代表缓存最多可以存储的对象个数
readOnly：是否只读，如果为true，则所有相同的sql语句返回的是同一个对象（有助于提高性能，但并发操作同一条数据时，可能不安全），如果设置为false，则相同的sql，后面访问的是cache的clone副本。
可以在Mapper的具体方法下设置对二级缓存的访问意愿：

​ 如果一条语句每次都需要最新的数据，就意味着每次都需要从数据库中查询数据，可以把这个属性设置为false，如：

<select id="selectAll" resultMap="BaseResultMap" useCache="false">
刷新缓存（就是清空缓存），二级缓存默认会在insert、update、delete操作后刷新缓存，可以手动配置不更新缓存，如下：
<update id="updateById" parameterType="User" flushCache="false" />
```

## mybatis自定义缓存
自定义缓存对象，该对象必须实现 org.apache.ibatis.cache.Cache 接口。
