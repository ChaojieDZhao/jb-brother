# Spiring oauth2
> ```开发者A```注册某IT论坛后，发现可以在信息栏中填写自己的 Github 个人信息和仓库项目，但是他又觉得手工填写十分麻烦，直接提供 Github 账户和密码给论坛管理员帮忙处理更是十分智障。
 不过该论坛似乎和 Github 有不可告人的秘密，开发者A可以点击“导入”按钮，授权该论坛访问自己的 Github 账户并限制其只具备读权限。这样一来，
 Github 中的所有仓库和相关信息就可以很方便地被导入到信息栏中，账户隐私信息也不会泄露。
 这背后，便是 OAuth 2 在大显神威。
 
## 1. OAuth2 角色
###  资源所有者（Resource Owner）
> 资源所有者是 OAuth 2 四大基本角色之一，在 OAuth 2 标准中，资源所有者即代表授权客户端访问本身资源信息的用户（User），也就是应用场景中的```开发者A```。
客户端访问用户帐户的权限仅限于用户授权的“范围”（aka. scope，例如读取或写入权限）。

### 资源/授权服务器（Resource/Authorization Server）
> 资源服务器托管了受保护的用户账号信息，而授权服务器验证用户身份然后为客户端派发资源访问令牌。
在上述应用场景中，Github 既是授权服务器也是资源服务器，个人信息和仓库信息即为资源（Resource）。而在实际工程中，不同的服务器应用往往独立部署，协同保护用户账户信息资源。

### 客户端（Client）
> 在 OAuth 2 中，客户端即代表意图访问受限资源的第三方应用。在访问实现之前，它必须先经过用户者授权，并且获得的授权凭证将进一步由授权服务器进行验证。

## 2. OAuth2 授权流程
  ![](https://images2018.cnblogs.com/blog/647983/201805/647983-20180504165446261-1334969394.png)
  
 - s1 Authrization Request
 >客户端向用户请求对资源服务器的authorization grant。
 - s2 Authorization Grant（Get）
 >如果用户授权该次请求，客户端将收到一个authorization grant。
 - s3 Authorization Grant（Post）
 >客户端向授权服务器发送它自己的客户端身份标识和上一步中的authorization grant，请求访问令牌。
 - s4 Access Token（Get）
 >如果客户端身份被认证，并且authorization grant也被验证通过，授权服务器将为客户端派发access token。授权阶段至此全部结束。
 - s5 Access Token（Post && Validate）
 >客户端向资源服务器发送access token用于验证并请求资源信息。
 - s6 Protected Resource（Get）
 > 如果access token验证通过，资源服务器将向客户端返回资源信息。

## 3. 客户端应用注册
> 在应用 OAuth 2 之前，你必须在授权方服务中注册你的应用。如 Google Identity Platform 或者 Github OAuth Setting，诸如此类 OAuth 实现平台中一般都要求开发者提供如下所示的授权设置项。
应用名称，   应用网站，   重定向URI或回调URL
重定向URI是授权方服务在用户授权（或拒绝）应用程序之后重定向供用户访问的地址，因此也是用于处理授权码或访问令牌的应用程序的一部分。

### Client ID 和 Client Secret
> 一旦你的应用注册成功，授权方服务将以client id和client secret的形式为应用发布client credentials（客户端凭证）。
client id是公开透明的字符串，授权方服务使用该字符串来标识应用程序，并且还用于构建呈现给用户的授权 url 。
当应用请求访问用户的帐户时，client secret用于验证应用身份，并且必须在客户端和服务之间保持私有性。

## 授权许可
###  Authorization Code 结合普通服务器端应用使用。





## Spring Oauth2
### 1 授权服务 Authorization Service.
#### 1.1 AuthorizationEndpoint：用来作为请求者获得授权的服务，默认的URL是/oauth/authorize.
> 他也是获取grant的第一步 

#### 1.2 TokenEndpoint：用来作为请求者获得令牌（Token）的服务，默认的URL是/oauth/token.
> 他是获取grant之后获取access_token的第二步

### 2 资源服务 Resource Service.
#### 2.1 OAuth2AuthenticationProcessingFilter：用来作为认证令牌（Token）的一个处理流程过滤器。只有当过滤器通过之后，请求者才能获得受保护的资源。
> 他是带着token请求资源的第三步







app_id， app_key， app_secret ， 对于平台来说， 需要给你的 你的开发者账号分配对应的权限:
1 app_id 是用来标记你的开发者账号的， 是你的用户id， 这个id 在数据库添加检索， 方便快速查找
2 app_key 和 app_secret 是一对出现的账号， 同一个 app_id 可以对应多个 app_key+app_secret， 这样平台就可以分配你不一样的权限。
比如 app_key1 + app_secret1 只有只读权限 但是 app_key2+app_secret2 有读写权限。。 这样你就可以把对应的权限 放给不同的开发者。 其中权限的配置都是直接跟app_key 做关联的， app_key 也需要添加数据库检索， 方便快速查找。
3 至于为什么要有app_key + app_secret 这种成对出现的机制呢， 因为要加密， 通常在首次验证(类似登录场景) ，
你需要用 app_key(标记要申请的权限有哪些) + app_secret(密码， 表示你真的拥有这个权限) 来申请一个token， 就是我们经常用到的access_token， 之后的数据请求， 就直接提供access_token就可以验证权限了。

简化的场景:
1 省去app_id，他默认每一个用户有且仅有一套权限配置， 所以直接将 app_id = app_key ， 然后外加一个app_secret就够了。
2 省去app_id 和 app_key， 相当于 app_id = app_key = app_secret， 通常用于开放性接口的地方， 特别是很多地图类api 都采用这种模式， 这种模式下， 带上app_id 的目的仅仅是统计某一个用户调用接口的次数而已了。

AppID：应用的唯一标识 AppKey：公匙（相当于账号）AppSecret：私匙（相当于密码）

token：令牌（过期失效）

使用方法

1. 向第三方服务器请求授权时，带上AppKey和AppSecret（需存在服务器端）

2. 第三方服务器验证AppKey和AppSecret在DB中有无记录

3. 如果有，生成一串唯一的字符串（token令牌），返回给服务器，服务器再返回给客户端

4. 客户端下次请求敏感数据时带上令牌




最近在做API开发，主要是接口交互，开发文档涉及到授权的access_token与refresh_token

有2个疑问：

1，access_token过期可以通过refresh_token获取，而为什么不是再获取一下access_token就好呢

2，refresh_token过期呢？接口怎么获取？

问题:access_token与refresh_token之为什么要用refresh_token刷新不重新获取access_token?

原因是access_token只保存2个小时,而refresh_token保存30天;

当access_token在登录2个小时后过期了,难道就要用户在重新登录吗?

当然不可能,这个时候refresh_token就有用武之地了;

那又有同学问了,那我直接重新访问

https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code

不就能获取access_token了吗?为什么要用refresh_token刷新?

那么重点就来了,上面的访问路径需要参数code,而code是一次性的参数,就是用一次之后就没用了的,需要重新获取,而重新获取就需要用户再一次扫码或者账号登录才能获取code,所以到这里应该就能明白为什么要用refresh_token刷新而不是重新用code来获取access_token了;

上面回答了第一个问题，第二个问题是通过登陆来解决，但接口交互是不存在登陆的。找到下面的解释：

refresh token需要过期时间么？
客户端需要保存token和refresh token，以便下一次访问能继续。如果客户端是浏览器，那么两个token都需要设置过期时间；但是可以设置得长一点，可以以天为单位（例如7天、15天）；
如果客户端是一个服务器，那么refresh token可以永久有效，直到下一次登录，refresh token本身被更新为止。

可以理解成把refresh token设置为长期有效，除非有客户端登陆更新，问题暂时解决。
