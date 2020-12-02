## MySQL 索引原理
现在互联网应用中对数据库的使用多数都是读较多，比例可以达到 `10:1`。并且数据库在做查询时 `IO` 消耗较大，
所以如果能把一次查询的 `IO` 次数控制在常量级那对数据库的性能提升将是非常明显的，因此基于 `B+ Tree` 的索引结构出现了。

### B+ Tree 的数据结构
![](https://ws2.sinaimg.cn/large/006tKfTcgy1fn10d6j9sij30hc08cab3.jpg)
如图所示是 `B+ Tree` 的数据结构。是由一个一个的磁盘块组成的树形结构，每个磁盘块由数据项和指针组成。

> NOTE:所有的数据都是存放在叶子节点，非叶子节点不存放数据。

### 查找过程
s1: 以磁盘块1为例，指针 P1 表示小于17的磁盘块，P2 表示在 `17~35` 之间的磁盘块，P3 则表示大于35的磁盘块。

s2: 比如要查找数据项99，首先将磁盘块1 load 到内存中，发生 1 次 `IO`。接着通过二分查找发现 99 大于 35，所以找到了 P3 指针。通过P3 指针发生第二次 IO 将磁盘块4加载到内存。再通过二分查找发现大于87，通过 P3 指针发生了第三次 IO 将磁盘块11 加载到内存。最后再通过一次二分查找找到了数据项99。

s3: 由此可见，如果一个几百万的数据查询只需要进行三次 IO 即可找到数据，那么整个效率将是非常高的。

s4: 观察树的结构，发现查询需要经历几次 IO 是由树的高度来决定的，而树的高度又由磁盘块，数据项的大小决定的。

s5: 磁盘块越大，数据项越小那么数的高度就越低。这也就是为什么索引字段要尽可能小的原因。

## mysql参数查询
``` sql
show processlist;    #查询所有的链接
show variables like '%max_connections%';    #查询设置的最大链接数  
show variables like '%storage_engine%';  
set session query_cache_type = off    #关闭缓存
show variables like '%cache%';
show variables like '%character%';    #查询字符集
show variables like '%log_bin%';   #查询binlog    
have_query_cache: 为YES表示缓存开启
query_cache_type: on表示默认使用缓存
query_cache_limit: 能缓存的单条查询结果容量最大值, 超过此值则不会缓存
query_cache_size: 查询缓存总共占内存空间大小, 一般设置为256M为一个不错的大小

show index from test_order;    #查看建立在某个表上的索引：  
DROP INDEX CREATE_USER_INDEX ON test_order;    #删除某个表上的索引
```

## 索引使用和优化
### 负向查询不能使用索引

```sql
select name from user where id not in (1,3,4);
```
应该修改为:

```sql
select name from user where id in (2,5,6);
```

### 前导模糊查询不能使用索引
如:

```sql
select name from user where name like '%zhangsan'
```

非前导则可以:
```sql
select name from user where name like 'zhangsan%'
```
建议可以考虑使用 `Lucene` 等全文索引工具来代替频繁的模糊查询。

### 数据区分不明显的不建议创建索引
如 user 表中的性别字段，可以明显区分的才建议创建索引，如身份证等字段。

### 字段的默认值不要为 null
这样会带来和预期不一致的查询结果。

### 在字段上进行计算不能命中索引
```sql
select name from user where FROM_UNIXTIME(create_time) < CURDATE();
```

应该修改为:

```sql
select name from user where create_time < FROM_UNIXTIME(CURDATE());
```

### 最左前缀问题

如果给 user 表中的 username pwd 字段创建了复合索引那么使用以下SQL 都是可以命中索引:

```sql
select username from user where username='zhangsan' and pwd ='axsedf1sd'

select username from user where pwd ='axsedf1sd' and username='zhangsan'

select username from user where username='zhangsan'
```

但是使用

```sql
select username from user where pwd ='axsedf1sd'
```
是不能命中索引的。

### 如果明确知道只有一条记录返回
```sql
select name from user where username='zhangsan' limit 1
```
可以提高效率，可以让数据库停止游标移动。

### 不要让数据库帮我们做强制类型转换
```sql
select name from user where telno=18722222222
```
这样虽然可以查出数据，但是会导致全表扫描。

需要修改为
```
select name from user where telno='18722222222'
```

### 如果需要进行 join 的字段两表的字段类型要相同
不然也不会命中索引。

## 数据库拆分
当数据库量非常大的时候，DB 已经成为系统瓶颈时就可以考虑进行水平垂直拆分了。

### 水平拆分
一般水平拆分是根据表中的某一字段(通常是主键 ID )取模处理，将一张表的数据拆分到多个表中。这样每张表的表结构是相同的但是数据不同。

不但可以通过 ID 取模分表还可以通过时间分表，比如每月生成一张表。
按照范围分表也是可行的:一张表只存储 `0~1000W`的数据，超过只就进行分表，这样分表的优点是扩展灵活，但是存在热点数据。

按照取模分表拆分之后我们的查询、修改、删除也都是取模。比如新增一条数据的时候往往需要一张临时表来生成 ID,然后根据生成的 ID 取模计算出需要写入的是哪张表(也可以使用[分布式 ID 生成器](https://github.com/crossoverJie/Java-Interview/blob/master/MD/ID-generator.md)来生成 ID)。

分表之后不能避免的就是查询要比以前复杂，通常不建议 `join` ，一般的做法是做两次查询。

### 垂直拆分
当一张表的字段过多时则可以考虑垂直拆分。
通常是将一张表的字段才分为主表以及扩展表，使用频次较高的字段在一张表，其余的在一张表。

这里的多表查询也不建议使用 `join` ，依然建议使用两次查询。

### 拆分之后带来的问题
拆分之后由一张表变为了多张表，一个库变为了多个库。最突出的一个问题就是事务如何保证。

### 两段提交

### 最终一致性
如果业务对强一致性要求不是那么高那么最终一致性则是一种比较好的方案。

通常的做法就是补偿，比如 一个业务是 A 调用 B，两个执行成功才算最终成功，当 A 成功之后，B 执行失败如何来通知 A 呢。

比较常见的做法是 失败时 B 通过 MQ 将消息告诉 A，A 再来进行回滚。这种的前提是 A 的回滚操作得是幂等的，不然 B 重复发消息就会出现问题。


## 常见执行问题
### 命令行强制执行sql
mysql -f -uroot -p123456 -Dparkos_v1<D:/parkos_mq.sql

### 赋予所有的IP都能链接
GRANT ALL PRIVILEGES ON \*.\* TO 'root'@'%' IDENTIFIED BY '123456' WITH GRANT OPTION;

### LIKE操作符只能匹配两种字符并且不区分大小写
- % matches any number of characters, even zero characters.
- _ matches exactly one character.




# sql练习
```sql
CREATE TABLE shop (
    article INT(4) UNSIGNED ZEROFILL DEFAULT '0000' NOT NULL,
    dealer  CHAR(20)                 DEFAULT ''     NOT NULL,
    price   DOUBLE(16,2)             DEFAULT '0.00' NOT NULL,
    PRIMARY KEY(article, dealer));
INSERT INTO shop VALUES
    (1,'A',3.45),(1,'B',3.99),(2,'A',10.99),(3,'B',1.45),
    (3,'C',1.69),(3,'D',1.25),(4,'D',19.95);
```


[相关链接](https://www.cnblogs.com/siqi/p/3480946.html)


