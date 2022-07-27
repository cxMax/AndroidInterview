## Android IPC机制
> 也就是跨进程通信方案

### 总结
* 基于Unix系统的IPC
	* 匿名管道，有名管道FIFO
	* 信号
* 基于SystemV和Posix系统的IPC
	* 消息队列
	* 信号量
	* 共享内存
* 基于Socket的IPC
* Linux的内存映射函数mmap()
* Linux 2.6.22版本后才有的eventfd
* Android系统独有的
	* Binder
	* 匿名共享内存Ashmen

### 基于Unix系统的IPC

#### 管道
> [Android进程间通信机制-管道](https://www.jianshu.com/p/115cf0e519c2)

* 两种类型：
	* 匿名管道 pipe （只支持父子进程和兄弟进程），子进程fork父进程的文件描述符
	* 有名管道 FIFO（有序，先进先出）在不相关进程，也能通信，因为会创建文件

* 单向半双工（单端读、单端写，不能同时进行读写）

* 本质 : 
	* 内核创建虚拟文件，并且调用文件读写函数来进行数据通信


* 两次数据拷贝
	* 读 : 用户空间拷贝 -> 内核
	* 写 : 内核 -> 用户空间

* 匿名通道特点 :
	* 数据传输量小，只支持4k
	* 单向通信，不存在并发问题

* 有名通道特点 : 
	* 因为可以同时读写，所以存在并发问题	

* Android使用场景 :
	* android消息机制，Looper#wake的唤醒，其实就是往管道mWakeWritePipeFd里写入一个字母“W”，mWakeReadPipeFd接收到数据后，就会唤醒Looper。
 	
#### 信号 signal

* 单向，只管发送信号，不能带参数，知道进程pid就可以发信号，也可以一次给一群进程发信号

* 唯一的异步通信机制，有地方注册了就处理，没注册就忽略

* 使用场景 :
	* 硬件来源（cltr + c），软件来源（kill命令）

* Android使用场景 :
	* 杀进程，系统调用Process#killProcess的时候，其实就是发送的SIGNAL_KILL这个信号

	
### 于SystemV和Posix系统的IPC

#### 消息队列
> 保存在内核中的消息链表

* 特点: 
	* 链表结构
	* 两次数据拷贝，用户空间 -> 内核
	* 具有异步能力
	* 队列有最大长度，单个消息也有最大数据量16kb限制

* Android使用场景 :
	* 无

#### 信号量
> 信号量其实是一个整型的计数器，主要用于实现进程间的互斥与同步，而不是用于缓存进程间通信的数据

* 特点 :
	* 主要就三个操作，初始化，p、v；

* 应用场景 :
	* 两个进程访问同一个共享内存，信号量会进行pv操作，恢复至0，则进行唤醒


#### 共享内存
> 每个进程都有自己独立的虚拟内存空间，不同进程的虚拟内存映射到不同的物理内存中
> /dev/shm下创建虚拟文件
 
* 0次数据拷贝


### Socket


* 双全工（A->B可以同时发送），可以同时进行读写

* 两次数据拷贝
	* 发送数据 : <br>从用户空间拷贝 -> 内核缓存区
	* 接受数据 : <br> 从内核缓存区 -> 拷贝至用户空间

* Android使用场景 :
	* zygote入口函数，会开启一个socket，用于创建进程 AMS与socket进行通信，
		* AMS调用Process的start()，去链接sockect，
		* Zygote的registerServerSocket和runSelectLoop心，循环的等待socket链接

* 缺点 :
> [SOCKET通信中TCP、UDP数据包大小的确定](https://love.junzimu.com/archives/553)
> [socket--接受大数据 ](https://www.cnblogs.com/bigberg/p/7747419.html)
	* 传输的数据不能太大	
		* UDP协议单个包大小不能超过64k
		* TCP没有大小限制，如果包数据过大，会分段发送；官方建议，单个包大小不超过8k；

	* 不支持直接RPC
		* 什么是RPC? [既然有HTTP协议，为什么还要有RPC](https://juejin.cn/post/6844904072022065165)
 

### mmap
> 实现共享内存，但不仅仅只用于共享内存，mmap的设计，主要是用来做文件映射的

* 特点 :
	* 不需要拷贝

* android应用场景 : 
	* 安装应用，加载dex文件，进行odex优化，通过mmap去映射dex文件


### evenfd
> 替代pipe

* 特点 : 
	* 只需要创建一个虚拟文件，而pipe需要创建两个
	* 也是读、写两个函数

* Android使用场景 :
	* 　消息机制looper唤醒wake函数，其实就是往eventfd写入1

### Android系统独有

#### 匿名共享内存
> mmap来映射/dev/ashmem创建一个虚拟文件


#### bindler

* 一次数据拷贝， 怎么实现的呢？
	* 写数据的时候，用户空间 -> 内核空间缓冲区， 1次内存拷贝；
	* 读数据的时候， 因为用户空间映射了内核缓存区和数据接受缓存区，所以在发送到内核的时候，也发送到了用户空间，所以0次内存拷贝

* c/s架构
	* server、client、ServiceMananger跑在用户空间
	* Binder驱动运行于内核空间

* 三层设计
	* Java层 
		* client : BinderProxy
		* server : Binder
		* 通过addService和getService来调用native层
	* native层
		* client - BpBinder
		* server - BBinder
		* Android系统中的各个服务，都是添加到ServiceMananger进行管理的，并提供注册和查询服务
	* kernel层 :
		* Client和Server都是通过ioctl()函数和binder交互的，为什么不用read？和write呢？ 因为一次调用实现先写后读以满足同步交互，而不必分别调用write()和read() 

* 一次完成的binder数据发送接受流程
	*  Proxy ->BinderProxy -> BpBinder ->transact -> 驱动 -> ontransact处理 -> BBinder -> Binder 



* 从aidl原理看binder
	* 第1步，首先定义aidl接口
	* 第2步，系统会编译生成， 
		* Stub内部类，继承binder，说明是一个本地对象；实现了IInterface接口，是一个抽象类，具体的相关实现需要我们手动完成。
		* BinderProxy类是Binder类的一个内部类，表示远端binder在本地的代理	* 第3步，跨进程数据流经驱动的时候，驱动会识别IBinder类型的数据，从而自动完成不同进程Binder本地对象以及Binder代理对象的转换

	* 第4步，通过service#bindService后，会跟我们返回一个IBinder对象，我们Stub.asInerface去判断返回远端代理对象，还是本地对象。如果是client和server都在同一进程，直接返回stub对象。如果不在同一进程，则返回代理对象，这个是驱动去判断的。

	* 第5步，发送数据，transact -> android_util_Binder.cpp talkWithDriver 通过ioctl调用，进入内核态度，client线程挂起，等待返回，驱动完成后，唤醒server进程，onTransact_方法，然后就会调用本地方法，这个方法将结果返回给驱动，驱动唤醒挂起的Client进程里面的线程并将结果返回。于是一次跨进程调用就完成了。

* Binder传递大数据
> frameworks/native/libs/binder/processState.cpp，binder传输的数据大小不能超过1M

	* 会导致TransactionTooLargeException

* bundle通过parcel传递数据的时候 > 200k 就会抛出异常
	* 解决方案 : 使用bundle.putBinder通过aidl传递数据
	* 为什么parcel传递大数据时候会抛出异常？
		* 因为setAllowFds = false











## 引用
* [Android知识体系之IPC](https://www.zybuluo.com/TryLoveCatch/note/1800510)
* [张三同学没答好「进程间通信」，被面试官挂了....](https://zhuanlan.zhihu.com/p/165224175)
* [深入理解Android进程间通信机制](https://blog.csdn.net/tyuiof/article/details/108290327)
* [跨进程传递大图，你能想到哪些方案呢？](https://juejin.cn/post/6844904182126739470)
