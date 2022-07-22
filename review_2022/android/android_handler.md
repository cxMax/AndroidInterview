# android handler


## 基本实现

一个线程对应一个Looper,<br> 
一个Handler对应一个Looper和一个MessageQueue, <br> 
一个Looper对应一个MessageQueue, <br> 
一个MessageQueue对应一个Message<br> 
一个Message里面有一个用于处理消息的Handler<br> 

### Handler 
> 消息辅助类, 主要是发送消息和处理消息 

主要注意两点，一个是发送消息的方式；另一个则是处理消息

#### 发送消息
* post(@NonNull Runnable r)
* sendMessage(@NonNull Message msg) <br> 这里补充说明一下，这两种方式只是发送同步消息的一种，handler还可以发送异步消息，异步消息一般和消息屏障一块使用，这里后面在MessageQueue中讲。<br>消息怎么添加到队列呢？这个也是在MessageQueue中enqueueMessage，也是在下面讲到



#### 分发消息
* dispatchMessage : <br> 具体流程，还是在Looper.loop()消息循环中，MesessageQueue.next() poll出要处理的消息， 交由message.target也就是handler的dispatchMessage进行分发处理

#### 处理消息
对应上面发送消息的两种方式处理方式也会有不同

* runnable.run(): <br> 如果是post的方式发送，那么message.callback就不为null，在上述分发消息过程中，dispatchMessage就直接判断，callback.run() 进行处理了

* handleMessage(@NonNull Message msg)：  <br> 这个主要是通过构造函数实现Handler#CallBack接口也就是handlerMessage进行处理的<br> 这里要注意，就是static handler handlerMessage内存泄漏的问题

### Meesage
> 单纯的一个消息， 是以单链表的形式存储在messagequeque中的

这里主要说一下，Message中一些重要的点和属性

* 数据结构
	* 单链表形式，next指向下一个消息
	* when很重要，消息分发的时间戳，用于比较消息发送的时间，和当前系统时间进行比对，如果是小于当前时间， 就会被poll出来，进行dispatch进行分发处理；注意这个时间戳，是 SystemClock.uptimeMillis()，如果系统休眠状态，这个是不会计算在内的

* obtain() : <br> 消息复用
	* 内部缓存复用策略，缓存池，默认50个大小，静态变量，sPool是表头，最新回收的消息放在表头。<br>发送消息时，如果消息池中有消息，就进行复用，把sPool指向下一个消息。<br>如果是回收消息，即Message#recycleUnchecked()，表头指向最新回收的消息

### MessageQueue 
> 消息队列，主要是消息入队，和弹出消息

* enqueueMessage(Message msg, long when)： <br> 他会跟关键的带用一个时间戳when，其主要作用是便利消息队列（主要是单链表的查找），遇到小于when的messgae，就弹出，并把它插入到他的后面<br> 
	1. 如果新消息的被执行时间when比队列中任何一个消息都早，则插入对头。唤醒Looper
	2. 如果当前对头是同步屏障消息，新消息是异步消息，则唤醒Looper
	3. MessageQueue是个阻塞状态，当message满足被触发的when这个参数，才会执行Looper.loop
	4. 什么时候会触发looper::loop的唤醒，对头消息是同步屏障消息，和新插入的消息是异步消息，就会唤醒循环
	5. 唤醒loop用的是nativeWake

* next(): <br> nextPollOnce,他会带一个时间戳，如果不等于0就会阻塞,

* postSyncBarrier的屏障消息
> 异步消息插队并优先执行，api是 messageQueue::postSyncBarrier , messageQueue::removeSyncBarrier， 需要反射调用
	1. 一般屏障消息和异步消息会同时使用
	2. 屏障消息的作用是，如果对头是屏障消息，会忽略同步消息，优先让异步消息先执行，这个主要是在messageQueue::next()去检索下一条执行消息，里面的逻辑
	3. 异步消息被执行的时候，屏障消息也会被移除掉

* postSyncBarrier的使用场景，明白一般是用于优先处理，所以会有以下场景 : 
	1. ViewRootImpl接受VSync信号驱动UI绘制
	2. ActivityThread接收AMS的事件驱动生命周期
	3. InputMethodManager分发软键盘输入事件
	4. PhoneWindowManager分发电话页面各种事件


### Looper
> 核心函数loop , 消息循环

loop()死循环 -》messageQueue.next，这个函数也是个死循环 -〉会去调用native的nextPollOnce函数（检查消息队列有没有消息，如果有消息就返回，没有消息就阻塞到这里）-》会去调用native的 looper::pollOnce函数 -> 关键最后还是会去调用native looper::pollInner::epoll_wait（也就是等待消息）

### handler机制原理
1. Handler机制原理是使用pipe管道（c++）
2. 主现场没有消息处理时，阻塞在管道的读端（nativePollOnce）
3. 添加消息的时候，就会往管道写一个字节，这样就能唤醒主线程从管道读端返回。

### handler机制其他应用场景
#### 应用场景 
比如UI卡顿

####检测机制
framework的WatchDog，BlockCanary（一个开源库）

##### WatchDog 
检查是否死锁，

#### WatchDog原理 
系统各个服务会去注册MonitiorCheck，检查死锁的方式，都是会去开一个线程去获取这个锁，能获取到就表示正常，不能获取到就表示异常了

#### 应用层 
主要通过handler的api。去set一个MessageLogging。打印一个消息处理的时间差

### IdleHandler
#### IdleHandler含义
* 当一个线程开始阻塞等待更多消息的时候，会触发idlehandler的回调。回调return true或者false的时候表示，一直可用还是只用一次就不用了。<br>
* 当前消息队列为null的时候不会用到，当前消息队列有消息，但还没到触发时间

#### IdleHandler使用场景
* 用来延迟执行，解决handler.postDelay()懒加载，那个时间不准确的问题
* 批量任务、只关注最终结果。可以封装成message处理，messages处理完了。工作线程idler的时候再处理


#### IdleHandler原理 
* 他的触发原理，主要是在MessageQueue中，消息循环的时候，MessageQueue::next() ,如果没有消息分发的时候，就会触发IdlerHandler。

#### Android源码使用
* GcIdler。 主线程第一次gc的时候，回调用一次idlerhandler执行一次gc
* UIAutomator源码。 waitforIdle() wait 和 notify去唤醒。

