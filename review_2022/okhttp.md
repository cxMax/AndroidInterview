# okhttp

## 原理
1. 首先需要构造OkhttpClient对象，可以构造各类网络相关的参数。cookieJar、证书相关、协议、拦截器、超时、重试等参数
2. 他会把请求包装成一个request，通过enqueue或excute进行异步、同步调用
3. 最大请求数64个，对 每个域名 我们最多同时只能有5个请求，针对每个域名，okhttp最多可以发起5条tcp连接
4.  dispatcher就是一个 线程调度器

## 从http协议来看拦截器

### addInterceptor
* 在整个责任链最顶层开始往下调用。
* 一般在应用里，添加日志打印、设置账号信息到header等

### RetryAndFollowUpInterceptor
* 初始化相关的工作，主要是重试和重定向相关的


### BridgeInterceptor
* 添加header相关参数
	* gzip 压缩
	* connection，keepalive等

### CacheInterceptor
* 负责cache的处理
* 如果有用到cache这里就直接返回了

### ConnectInterceptor

* 负责建立链接
	* tcp和tsl连接，都在这里连接


### addNetworkInterceptor 
* 网络拦截器，拿到最原始的byte数据

### CallServerInterceptor
* 负责实质的请求和响应io的操作
* 一般用作网络调试

### 其他
* okhttp根据域名配置默认信任链，可以防止抓包软件，抓到该域名下的包

# retrofit
## 原理
* 使用动态代理，通过注解配置， 使用invocationHandler去构造请求对象，使用ServiceMethod.adapt进行网络请求
* 其中里面用到了大量的设计模式，
	* builder模式，Retrofit.Builder
	* 工厂模式，CallAdapter
	* 适配器模式，转化为不同的call
	* 动态代理模式，主要是invocationhandler
	* 外观模式，retrofit
	* 策略模式等


## Android网络优化
>分两块 ： 短链接优化 和 长链接优化

### 短链接优化 ：

1. 域名合并
	* 各业务团队出于独立性和稳定性的考虑，纷纷申请了自己的三级域名
		* 1.Http请求需要跟不同服务器建立连接，增加了网络的并发连接数量损耗
		* 2.每条域名都需要经过dns服务来解析服务器IP 

	* 解决方案：
		* 1.域名收编 -> 网络请求发出前，在客户端的网络底层将URL中的域名做替换
		* 2.域名还原 -> 改造的请求被送到网络后端，在SLB中，拥有与客户端网络相反的一套域名反收编逻辑
2. ip直连
	* 1.程序启动的时候拉去域名对应的所有IP列表
	* 2.对所有IP进行跑马测试，找到速度最快的IP。





# 引用
[从http协议的角度来理解Okhttp ](https://juejin.cn/post/6926780058057539592)