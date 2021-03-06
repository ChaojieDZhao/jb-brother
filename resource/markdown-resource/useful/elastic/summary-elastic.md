## 核心概念
- cluster(集群)    

同一个集群名的一个或者多个节点组成，共同存储着你的数据。每个集群拥有一个主节点，它由集群自行选举出来，在当前主节点挂了，能被其他节点取代。
集群有唯一的名称标识，默认名称为`ElasticSearch`。此名称很重要，因为如果将节点设置为通过其名称加入集群，节点则只能通过设置该名称加入集群。        

- node(节点)    

节点组成了集群，每一个节点的名称必须不一样，一个唯一的节点名称，用来管理节点。
每一个当前节点分为了下面当中的一个或者多个角色。    
   
Master-eligible node：该节点具有选取资格。             
Data node：数据节点保存数据并执行数据相关操作，eg: CRUD，搜索和聚合。       
Ingest node：预处理节点，ingest pipeline，应用于文档，以便在索引之前变换和丰富文档。      
Coordinating node：通过协调其他数据节点，用来接受和返回请求。  

- shard(分片)

shard（分片）是一个Lucene实例。它是由elasticsearch管理的低层次的工作单元。
一个index可以分为多个切片(number_of_shards)，切片分为主分片（the primary，负责写和查操作）和副本分片(the replis，只负责查询操作)。
主分片需要验证写入/删除请求和合理性和正确性，本地执行之后，然后转发给切片复制名单里面的所有需要进行该操作的其他切片。    
因此会出现执行成功的切片和执行失败的切片，在结果返回。
```
{
    "_shards" : {
        "total" : 2,
        "failed" : 0,
        "successful" : 2
    }
}
```
可以在写操作之前加上`wait_for_active_shards=N`参数确保`the primary`执行操作之前需要有N的个活动的切片，降低了最少成功数(_shards.successful)< N 的风险。
如果某个节点的主切片死掉了，则主节点将会重新在切片复制名单里面选出一个主切片。    
切片的状态包括 `STARTED,UNASSIGNED,INITIALIZING,RELOCATING`

> NOTE:默认索引分为5个切片和1个切片副本。

> NOTE:查询操作时，结果可能不完全包含，因为跨切片查询中的某一个副本组全部不可用，es为了快速响应，依旧会将部分结果返回，而不是选择去等待这个副本组可用。

- routing(路由)

当你索引一个文档时，它会被存储在一个replication group（副本组）。通过对routing值进行哈希计算来决定具体是哪一个副本组。    
默认情况下，routing值是来自于文档ID，如果文档指定了一个父文档，则通过其父文档ID(保证父子文档存储在同一个分片上)。    
如果你不想使用默认的文档ID来作为routing值,你可以在索引时直接指定一个routing值，或者在mapping中指定一个字段的值来作为routing值。   
索引和查询的 `routing`需要一致，不然可能检索不到期望的文档值。

- index(索引)

index(索引)类似于关系型数据库中的表。它有一个mapping(映射)来定义索引中的fields(属性)，这些属性被分组成多种type(类型)。
索引是一个逻辑命名空间，它对应一到多个primary shards(主分片)和零到多个replica shards(副本分片)。    

- mapping(映射)

mapping(映射)类似于关系型数据库中的元数据定义。每一个index(索引)对应一个mapping(映射)，它定义了index(索引)中的每一个type(类型)，另外还有一些索引级别的设置。     
mapping(映射)可以显式定义，或者当一个文档进行索引时自动生成(dynamic mapping type，动态映射)，这样它会去意图猜测每个域的类型。

- type(类型)

type(类型)代表文档的类型，如一封邮件，一个用户，一条推文。  
搜索API可以通过文档类型来过滤。index(索引)可以包涵多个类型，每一个type(类型)有一系列的fields(属性)。   
同一个index(索引)中不同type(类型)的同名fields(属性)必须使用相同的mapping(映射)(定义文档的属性如何索引以及是文档能被搜索)。    

- source field(meta field，源属性)

在默认情况下，你索引的json document(文档)会存储在_source field(属性)中，get和search请求会返回该field(属性)。
这样可以直接在搜索结果中获取原始文档对象，不需要通过ID再检索一次文档对象。

- term (词条)

term(词条)是elasticsearch中被索引的确切值。foo, Foo, FOO 这些term(词条)不相等。term(词条)可以通过词条搜索来检索。

- text(文本)

text(文本)(或者说全文)是普通的非结构化文本，如一个段落。默认情况下，text(文本)会被analyzed(分析)成term(词条)，term(词条)是实际存储在索引中的内容。
文本的field(属性)必须在索引时完成analyzed(分词)来支持`全文检索`的功能，全文检索使用的关键词也必须在搜索时`analyzed`(分析)成索引时产生的相同term(词条)。

- recall(召回率)&precision(精确率)

recall，返回所有的相关文档。    
precision，不返回无关文档。  

- slop(近似度匹配)

搜索文本中的几个词项，要经过几次移动才能与一个文档匹配，这个移动的次数，就是slop。

- Inverted Index
一个倒排索引由文档中所有不重复词的列表构成，对于其中每个词，有一个包含它的文档列表。     
为了创建倒排索引，我们首先将每个文档的 content 域拆分成单独的 词（我们称它为 词条 或 tokens ），创建一个包含所有不重复词条的排序列表，然后列出每个词条出现在哪个文档。

| Token       | DocIDs |
|-------------|--------|
| open_source | 2      |
| search      | 1,2    |

- translog(事务日志)

Lucene的修改只有在提交的时候保存在磁盘上，这是个相当耗时的操作，因此不能在每次索引或删除操作之后执行。
两次提交之前如果发生了进程退出或硬件故障的情况发生，这之间的修改会丢失。
为了防止数据丢失，每个分片都有transaction log（事务日志）或与之相关的日志。任何索引或删除操作在Lucene索引内部执行之后会被写入事务日志。
如果发生了崩溃事件，当分片恢复的时候，最近的更新操作可以根据translog进行恢复。

- near realtime(NRT 近实时)

Elasticsearch 是一个近实时的搜索平台。这意味着从您索引一个文档开始直到它可以被查询时会有轻微的延迟时间(通常为一秒)。
由于倒排索引的不可变性，文档在删除或者更新的时候，倒排索引不会及时的刷新（操作不可见），需要等到`in buffer memory`refresh到一个未被提交的segment(文件缓存，已经可以被搜索)。
当`tranlog`的值过大或者执行flush操作的时候，会进行`fsync`操作。未提交的segment会提交。commit point(它维持了当前拥有段的状态)被刷新。

![](./es-basic/assert/elas_1107.png)

- realtime

get API 是实时的，而且它不受 index 刷新频率的影响（当数据对search操作可见）。    
如果 document 已经修改完但没还有刷新，get API将会执行 in-place刷新操作使得 document 可见。可配置。

- fuzziness ( 模糊性 )
当查询 text ( 文本 ) 或者 keyword fields ( 关键字字段 )时，模糊性被解释为 `Levenshtein Edit Distance`，是指两个字串之间，由一个转成另一个所需的最少编辑操作次数。

- doc value&fielddata
doc value，基于磁盘存储，在索引的时候会为文档创建，面向列存储，用于排序或聚合或脚本访问。脚本访问如doc[name]。
fielddata，即时内存存储，当进行聚合等操作时，会转换每个segment存储的反向索引。然后存储到缓存中。

## index
相同特征文档的集合，index的名称必须为小写，index的创建不需要事先声明。索引可以被关闭，和开启。
底层的Lucene是不理解内部对象的，Lucene文档是由一组键值对列表组成的。为了能让Elasticsearch有效地索引内部类，它把我们的文档转化成这样：
{
    "tweet":            [elasticsearch, flexible, very],
    "user.id":          [@johnsmith],
    "user.name.first":  [john],
    "user.name.last":   [smith]
}
一个Elasticsearch分片就是一个Lucene索引，Lucene索引被分解成段。分段是索引中存储索引数据的内部存储元素，并且是不可变的。
较小的段被定期地合并成较大的段，以保持索引的大小和清除被标记为删除的文档。

### exact value(精确值查找)
当进行精确值查找时，可以使用过滤器（filters），这样elastic search会识别成filter context(在筛选上下文中，查询子句回答问题`此文档是否与此查询子句匹配`，答案是简单的是或否-不计算分数。  
过滤上下文主要用于过滤结构化数据。)。过滤器很重要，因为它们执行速度非常快，不会计算相关度（直接跳过了整个评分阶段）而且很容易被缓存。    
如果没有为index显式设置mapping，则可能会导致一些查询不符合预期，因为某些field可能会被分析存储，不能通过精确查询匹配到。   

#### 内部操作步骤
- 1 查找匹配文档.    
term 查询在倒排索引中查找 query text，然后获取包含该 term 的所有文档。

- 2 创建 bitset.
过滤器会创建一个 bitset （一个包含 0 和 1 的数组），它描述了哪个文档会包含该 term 。
匹配文档的标志位是 1 。如[1,0,0,0] 。在内部，它表示成一个 "roaring bitmap"，可以同时对稀疏或密集的集合进行高效编码。

- 3 迭代 bitset(s)
一旦为每个查询生成了 bitsets ，Elasticsearch 就会循环迭代 bitsets 从而找到满足所有过滤条件的匹配文档的集合。    
执行顺序是启发式的，但一般来说先迭代稀疏的 bitset （因为它可以排除掉大量的文档）。

- 4 增量使用计数(进行缓存).
Elasticsearch 能够缓存非评分查询从而获取更快的访问，但是它也会不太聪明地缓存一些使用极少的东西。      
非评分计算因为倒排索引已经足够快了，所以我们只想缓存那些我们 知道 在将来会被再次使用的查询，以避免资源的浪费。

### full-text value(全文查找)
查询上下文中使用的查询子句回答问题`此文档与此查询子句的匹配程度如何`？除了决定文档是否匹配之外，query子句还计算一个分数，表示文档相对于其他文档的匹配程度。     
一个评分查询计算每一个文档与此查询的 _相关程度_， 同时将这个相关程度分配给表示相关性的字段 `_score`，并且按照相关性对匹配到的文档进行排序。    
这种相关性的概念是非常适合全文搜索的情况，因为全文搜索几乎没有完全 「正确」 的答案。  

#### 多匹配查询
**best_fields（default）**

当搜索词语具体概念的时候，比如 “brown fox” ，词组比各自独立的单词更有意义。像 title 和 body 这样的字段，尽管它们之间是相关的，但同时又彼此相互竞争。    
文档在 相同字段 中包含的词越多越好，评分也来自于 最匹配字段 。     

**most_fields**

为了对相关度进行微调，常用的一个技术就是将相同的数据索引到不同的字段，它们各自具有独立的分析链。   
主字段可能包括它们的词源、同义词以及 变音词 或口音词，被用来匹配尽可能多的文档。   
相同的文本被索引到其他字段，以提供更精确的匹配。一个字段可以包括未经词干提取过的原词，另一个字段包括其他词源、口音，还有一个字段可以提供 词语相似性 信息的瓦片词（shingles）。   
其他字段是作为匹配每个文档时提高相关度评分的 信号 ， 匹配字段越多 则越好。  

**cross_fields **

对于某些实体，我们需要在多个字段中确定其信息，单个字段都只能作为整体的一部分：    
在这种情况下，我们希望在 任何 这些列出的字段中找到尽可能多的词，这有如在一个大字段中进行搜索，这个大字段包括了所有列出的字段。

### query-then-fetch&dfs-query-then-fetch（Distributed Frequency Search）
**query-then-fetch**    
- 1 请求节点发送查询到每个shard
- 2 找到所有匹配的文档，并使用本地的Term/Document Frequency信息进行打分
- 3 对结果构建一个优先队列（排序，标页等）
- 4 返回关于结果的元数据到请求节点。注意，实际文档还没有发送，只是分数相关信息
- 5 来自所有shard的分数合并起来，并在请求节点上进行排序，文档被按照查询要求进行选择
- 6 最终，实际文档从他们各自所在的独立的shard上检索出来
- 7 结果被返回给用户

**dfs-query-then-fetch**
- 1 预查询每个shard，询问Term和Document frequency
- 2 请求节点发送查询到每个shard
- 3 shard找到所有匹配的文档，并使用全局的Term/Document Frequency信息进行打分
- 4 对结果构建一个优先队列（排序，标页等）
- 5 返回关于结果的元数据到请求节点。注意，实际文档还没有发送，只是分数
- 6 来自所有shard的分数合并起来，并在请求节点上进行排序，文档被按照查询要求进行选择
- 7 最终，实际文档从他们各自所在的独立的shard上检索出来
- 8 结果被返回给用户

### TF/IDF （term frequency–inverse document frequency）
- 检索词频率    
检索词在该字段出现的频率？出现频率越高，相关性也越高。 字段中出现过 5 次要比只出现过 1 次的相关性高。

- 反向文档频率
每个检索词在索引中出现的频率？频率越高，相关性越低。检索词出现在多数文档中会比出现在少数文档中的权重更低。    

- 字段长度准则
字段的长度是多少？长度越长，相关性越低。 检索词出现在一个短的 title 要比同样的词出现在一个长的 content 字段权重更大。    

查询后添加一个explain可以理解匹配过程
```
GET /_search?explain 
{
   "query"   : { "match" : { "tweet" : "honeymoon" }}
}
```

#### Anatomy of an analyzer(三部分组成)
- character filter(可有可无)：在一段文本进行分词之前，先进行预处理，转换一些特殊符号，还有比较常见的就是，过滤html标签(<span>hello<span> --> hello)，& --> and(I&you --> I and you)
- tokenizer(分词器必须有一个)。例如：hello you and me --> hello, you, and, me，(标识器还负责记录每个词的顺序或位置，以及该词所代表的原始词的起始和结束字符偏移量。)
- token filter(可有可无)：大小写的转换，停用词，同义词的转换等。例如：dogs --> dog，liked --> like，Tom --> tom。 常见词如a/the/an这些词没有什么意义，就干掉.








## 1、Elasticsearch的功能

（1）分布式的搜索引擎和数据分析引擎
搜索：百度，网站的站内搜索，IT系统的检索
数据分析：电商网站，最近7天牙膏这种商品销量排名前十的商家有哪些；新闻网站，最近一个月访问量排名前3的新闻板块是哪些

（2）全文检索，结构化检索，数据分析
全文检索：我想搜索商品名称包含牙膏的商品，select * from products where product_name like "%牙膏%"
结构化检索：我想搜索商品分类为日化用品的商品有哪些，select * from products where category_id="日化用品"
数据分析：我们分析每一个商品分类下有多少个商品，select category_id,count(*) from products group by category_id

（3）对海量数据进行近实时的处理
分布式：ES自动可以将海量数据分散到多台服务器上去存储和检索
海量数据的处理：分布式以后，就可以采用大量的服务器去存储和检索数据，自然而然就可以实现海量数据的处理了
近实时：检索个数据要花费1个小时（这就不叫做近实时，叫做离线批处理，batch-processing）;在秒级别对数据进行搜索和分析才叫做近实时

## 2、Elasticsearch的适用场景

国外：
（1）维基百科 全文检索、高亮、搜索推荐
（2）The Guardian(国外新闻网站) 用户行为日志（点击，浏览，收藏，评论）+社交网络数据（对某某新闻的相关看法），数据分析，给到每篇新闻文章的作者，让他们知道他的文章的公众反馈（好、坏、热门。。。）
（3）Stack Overflow(国外程序异常讨论论坛)，全文检索，搜索到相关问题和答案，如果程序报错了，就会将报错信息粘贴到里面去，搜索有没有对应的答案
（4）github，搜索上千亿行的代码
（5）电商网站，检索商品
（6）日志数据的分析 elk技术
（7）商品价格监控网站，用户设定某商品的价格阈值，当低于该阈值的时候，发送通知消息给用户
（8）BI系统，商业智能Business Intelligence。比如有个大型商场集团，BI，分析一下某某地区最近3年的用户消费金额的趋势以及用户群体的组成构成，产出相关的数张报表。

国内
站内搜索（电商、招聘、门户等等）
IT系统搜索（OA、CRM、ERP等等）
数据分析

## 3、Elasticsearch的特点

（1）可以作为大型分布式集群（数百台服务器）技术，处理PB级的数据，服务大公司；也可以运行在单机上服务于小公司
（2）Elasticsearch不是什么新技术，主要是将全文检索、数据分析以及分布式技术，合并在了一起，才形成了独一无二的ES：lucene(全文检索)，商用的数据分析软件，分布式数据库
（3）对用户而言，是开箱即用的，非常简单，作为中小型应用，直接3分钟部署一下ES，就可以作为生产环境的系统来使用了，此时的场景是数据量不大，操作不是太复杂
（4）数据库的功能面对很多领域是不够用的（事务，还有各种联机事务型的操作）；特殊的功能，比如全文检索，同义词处理，相关度排名，复杂数据分析，海量数据的近实时处理，Elasticsearch作为传统数据库的一个补充，提供了数据库所不能提供的很多功能


























