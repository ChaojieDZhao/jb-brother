##### @ConfigurationProperties
> 自动从配置文件中封装配置类

#### @Qualifier("objRedisTemplate")
> 如果配置bean返回的是相同类，可以加上Qualifier指明注入那个类属性

#### @Configuration
> 配置config文件，常见druid连接池，或者定时器配置

#### @Bean(name = "objRedisTemplate")
> 配置类注入

#### @PostMapping

#### @RestController
> Controller和ResponseBody和合体，只要使用了，就不能返回页面

#### @PathVariable
> restful风格的参数前的注解，里面最少赋值参数名

#### @RequestBody
> HTTP BODY接收参数

#### @RequestParam
> HTTP URl 请求头接收参数、

#### @FeignClient(name = "jgnabs-ms", path = "", fallback = FJgnabsServiceHystrix.class)
> 代表一个Fegin Client，FJgnabsServiceHystrix代表代用出错返回的值

#### @Value
> 加载配置文件里面的值

#### @EnableFeignClients
> 自动搜索配置FeignClient的接口

#### @EnableEurekaClient

#### @EnableTransactionManagement

#### @FrameworkEndpoint
> oauth2 端点注解，主要用来撤回token

#### @EnableResourceServer 
> oauth2 用来开启资源服务器

#### @EnableAuthorizationServer 
> oauth2 用来开启验证服务器

#### @AutoConfigureMockMvc 
> 自动配置MockMVC

#### @PreAuthorize("hasAuthority('query-demo')")



























