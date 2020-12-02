### 显示Spring Boot RunDashboard
> 打开.idea目录下的workspace.xml  找到\<component name="RunDashboard"\>后添加
``` xml
<option name="configurationTypes">  
      <set>  
        <option value="SpringBootApplicationConfigurationType" />  
      </set>  
 </option>  
```

### live templates方法params多行显示
``` script
@params
groovyScript("def result=''; def params=\"${_1}\".replaceAll('[\\\\[|\\\\]|\\\\s]', '').split(',').toList(); for(i = 0; i < params.size(); i++) {result+=' * @param ' + params[i] + ((i < params.size() - 1) ? '\\n' : '')}; return result", methodParameters())

template Live
*
 * @describe 
 * @author zhaocj  
 * @date $time$
$params$
 * @return $returns$  
 */  
```

### spring boot项目启动指定不同的启动文件
> 在启动配置中，Program arguments中添加--spring.profiles.active=dev

### idea properties文件转 yaml
> [https://plugins.jetbrains.com/plugin/8000-properties-to-yaml-converter](yaml转换下载)
- At first select properties file. 
- Convert action is also available from the context menu after right-clicking properties file. You can find it also in the menu:
Code -> Convert Properties to YAML.

### idea控制台抛出没有行数显示
> idea.cycle.buffer.size=disabled


### 优化idea gc配置
```text
-Xms1024m
-Xmx1024m
-Xmn372m
-XX:MetaspaceSize=256m
-XX:MaxMetaspaceSize=512m
-XX:ReservedCodeCacheSize=240m
-XX:+UseConcMarkSweepGC
-XX:+UseParNewGC
-XX:+CMSParallelRemarkEnabled
-XX:+CMSScavengeBeforeRemark
-XX:CMSInitiatingOccupancyFraction=75
-XX:+UseCMSInitiatingOccupancyOnly
-XX:+UseCMSCompactAtFullCollection
-XX:CMSFullGCsBeforeCompaction=3
-XX:SoftRefLRUPolicyMSPerMB=50
```
-XX:+UseParNewGC：显示声明Young区垃圾回收算法，也可以不显示声明（显示声明为了让JVM参数更易懂），因为Old区申明为CMS GC的话，Young区默认就是ParNew；
-XX:+CMSParallelRemarkEnabled：CMS的remark阶段多线程并行；该参数默认就是true，所以这里只是显示声明；
-XX:+CMSScavengeBeforeRemark：CMS GC前执行一次Minor GC，即YGC；
-XX:CMSInitiatingOccupancyFraction=75：如果Old区声明为CMS GC的话，该参数的默认值为92，比较大，建议调小到75，减少Promotion failed的概率；
-XX:+UseCMSInitiatingOccupancyOnly：只有当Old区达到75%时才触发CMS GC，如果不声明的话，还有很多种其他条件触发CMS GC；
-XX:+UseCMSCompactAtFullCollection：CMS是标记清理算法，每次回收后有内存碎片问题，该参数会整理内存碎片，但是会影响暂停时间；
-XX:CMSFullGCsBeforeCompaction=3：表示经过多少次foreground CMS GC后对Old区做一次压缩。由于UseCMSCompactAtFullCollection这个参数为true整理内存碎片时会影响性能，但是碎片问题也需要解决或者缓解。所以，设置适当调整该参数的值，在执行多次foreground CMS GC后才对Old区进行压缩；




