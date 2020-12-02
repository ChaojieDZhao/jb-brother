# 管理后台联合登录
- s1 先登录管理后台，然后生成调用微信的state
- s2 微信未绑定的话绑定接口，注意state是由已经登录的用户组成，扫描之后微信提示是否授权（是否颁发grant）。
> https://open.weixin.qq.com/connect/qrconnect?appid=wxd9ae924f240d7565&redirect_uri=http%3A%2F%2Fwww.baijindai.com%2Fsys%2Fwxlogin&response_type=code&scope=snsapi_login&state=${state!}#wechat_redirect
- s3 如果用户接受登录请求，则回调给redirect url，state和code（code就是grant）。
- s4 根据code（grant）调用微信接口获取openID
> https://api.weixin.qq.com/sns/oauth2/access_token?appid="+Configs.getProperty("sys.wxapp")+"&secret="+Configs.getProperty("sys.wxsec")+"&code="+code+"&grant_type=authorization_code
- s4 根据state获取userID，然后进行userID和openID的绑定

如果已经绑定 则直接
> https://open.weixin.qq.com/connect/qrconnect?appid=wxd9ae924f240d7565&redirect_uri=http%3A%2F%2Fwww.baijindai.com%2Fsys%2Fwxlogin&response_type=code&scope=snsapi_login&state=nostate#wechat_redirect

