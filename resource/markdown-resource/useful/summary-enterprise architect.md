## 架构类型的特性

- 集中式架构：单体无分散
- 分布式架构：分散压力
- 微服务架构：分散能力

## 一个企业的IT建设非常重要的三大基础环境：团队协作环境、服务基础环境、IT基础设施。
   
- 团队协作环境：主要是DevOps领域的范畴，负责从需求到计划任务，团队协作，再到质量管理、持续集成和发布。
- 服务基础环境：指的是微服务应用平台，其目标主要就是要支撑微服务应用的设计开发测试，运行期的业务数据处理和应用的管理监控。
- IT基础设施：主要是各种运行环境支撑如IaaS (VM虚拟化)和CaaS (容器虚拟化)等实现方式。



start > browse > navigator acid

design > ToolBox

红色的A代表linked document

## 一些图
- BPMN
- wireframing
- web modeling 
- strategic modeling 决策模型
- Roadmap 路线图

SWOT ANALYSIS


泛化(Generalization)：是一种继承关系，表示一般与特殊的关系，它指定了子类如何特化父类的所有特征和行为。

实现(Realization)：是一种类与接口的关系，表示类是接口所有特征和行为的实现。

关联(Association)：是一种拥有的关系，它使一个类知道另一个类的属性和方法。

聚合(Aggregation)：是整体与部分的关系，且部分可以离开整体而单独存在。

组合(Composition)：是整体与部分的关系，但部分不能离开整体而单独存在。

依赖(Dependency)：是一种使用的关系，即一个类的实现需要另一个类的协助。


- ArchiMate
是一种整合多种架构的一种可视化业务分析模型语言，属于架构描述语言（ADL）它从业务、应用和技术三个层次（Layer），物件、
行为和主体三个方面（Aspect）和产品、组织、流程、资讯、资料、应用、技术领域（Domain）来进行描述。

- mindmap 
思维导图

- Strategic Modeling
战略建模

- CMMN 案例模型
Case Management Model and Notation diagram 

- UX
User experience

- IFML
The Interaction Flow Modeling Language

- BPMN 业务流程图与标注
Business Process Modeling Notation

- uaf
统一架构框架(Unified Architecture Framework,UAF)



# UML建模
统一建模语言（英语：Unified Modeling Language，缩写 UML）是非专利的第三代建模和规约语言。UML是一种开放的方法，用于说明、可视化、构建和编写一个正在开发的、面向对象的、软件密集系统的制品的开放方法。
UML展现了一系列最佳工程实践，这些最佳实践在对大规模，复杂系统进行建模方面，特别是在软件架构层次已经被验证有效。

## UML结构视图（structural perspective）

### UML 包图（package diagram）
描述包的结构图，一般情况下使用`带有方向箭头的虚线`表示依赖关系，

> ToolBox :Package Relationships>IMPORT

### UML 类图（class diagram）

### UML 对象图（object diagram）

### UML 复用结构图（composite structure diagram）
复合结构图反映类、   接口和组件的内部合作 （和它们的属性） 来描述它的功能。

### UML 构件图（component diagram）
组件图说明了软件块、   嵌入的控制器等构成了一个系统，其组织和依赖关系。

## UML行为视图（behavioral perspective）

### UML 用例图（user case diagram）
用例图捕获用例以及系统和使用户之间的关系。他们描述了系统的功能要求，使用者作用于系统边界的方法以及系统的反应。

### UML 序列图（sequence diagram）
随着时间的推移，序列图作为一系列的有序步骤是行为结构化表示形式。他们用来描绘工作流程、消息传递和元素一般合作随着时间达到的结果。

### UML 时态图（timing diagram）
时态图中定义不同的对象的行为时间尺度内提供更改状态，以及随着时间的推移进行交互的对象的可视化表示形式。

### UML 通信图（Communication Diagram）
通信图显示了在运行时，元素之间的互动关系，并可视化对象间关系。

### UML 互动概览图（interaction overview diagram）
互动概述图用可视化方式说明其他交互图   （时间、 序列、 沟通和互动概述图） 之间的合作，展示控制流的作用。

### UML 活动图（activity diagram）
活动图建模一个系统的行为。系统的整体流中这些行为是如何相关的。

### UML 状态机（state Machine diagram）
状态机图说明了如何元素可以在状态之间移动、   根据状态转移触发器和制约条件来分类其行为。