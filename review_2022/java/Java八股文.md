# Java八股文

## Java基础

### JVM、JRE、JDK
* JVM - 虚拟机（这里还可以拓展，虚拟机类型 - hotspot）
* JRE - 是运行环境，包含JVM
* JDK - 编译器和各种工具， 写代码就需要JDK

### public、protected、defalt、private的区别
* private - 私有的，修饰类和方法，在类中可以访问
* defalt - 修饰类和方法，在同一package下可见
* protected - 修饰方法，同一package下可见
* public - 修饰类和方法，所有可见

### final、finally、finalize
* final - 可以修饰类、方法、变量，必须初始化，对所有线程可见（后面JMM内存模型的时候，再专门讲）
* finally - try/catch固定用法，无论抛出异常，都会执行
* finnalize - Object对象的方法，表示回收对象，不推荐使用；调用了不一定被回收，对象被回收的时候才会回收

### static
* 修饰类、方法、变量
* transient关键字无法修饰static静态变量，静态变量不能被序列化
* todo 更多的看后面会拓展不

### 面向对象、面向过程

#### 面向对象
>继承、封装、多态

* 封装 : 封装成抽象类
* 继承 : 使用现有类的所有功能，而且还可以在现有功能的基础上做拓展
* 多态 : 多态是基于继承，使得同一个属性在父类及其子类中具有不同的含义。（重载和重写）
	* 重写 : 参数列表、返回类型都不能修改，常用override关键字，属于运行时多态
	* 重载 : 参数列表、返回类型可以修改，属于编译时多态

#### 面向过程（面向接口）
* 动态代理，进行代码插桩

### 泛型
* 减少了强制类型转换的次数
* 泛型只在编译时期起作用，运行阶段 JVM 看不⻅泛型类型(JVM 只能看⻅对应的原始类型，因为进行了类型擦除)
	* Java编译器会在编译时尽可能的发现可能出错的地方，但是仍然无法避免在运行时刻出现类型转换异常的情况

### equlas 和 ==

#### ==
* 基础变量类型，值是否相等；对象变量类型，两个对象的地址是否相等（也就是是否指向同一个对象）；
* 或者为了区分，这个理解为地址；

#### equals
* 如果没有重写equals方法，和“==”是一样的，
* 重写了equals和hashcode方法，比较值是否一致

### String
> String s1 = "ab"
> String s2 = "ab" + "c"

### 过程解析
* JVM - 方法区 - 字符串常量 : 有三个对象，ab、c、abc

### 如果改成
> new String("ab"); new String("ab")

* 三个对象；2个对象在堆内存中，一个对象在“ab"方法区 - 字符串常量里面， 两个堆内存中指向该字符串常量

### String、StringBuilder、StringBuffer

* String : final 修饰，不可变
* StringBuilder : 线程不安全
* StringBuffer : 线程安全，synchronized加锁的方式

* StirngBuilder和StringBuffer扩容问题，都是使用的System.arraycopy， 进行浅拷贝

### 浅拷贝和深拷贝

* 浅拷贝
	* 数据引用进行传递，不创建新的对象

* 深拷贝
	* 创建新的对象，复制其内容
	* android实现深拷贝的几种方式 :
		* 最暴力的方式，new 对象，逐一set属性值
		* 重写Object#clone
		* 通过序列化Serializable和Parcelable
		* 通过三方库api（例如apache，spring）

### Java 中 IO 流分为几种?BIO,NIO,AIO 有什么区别?
> io流有两种，字节流，字符流

#### 字节流

* 可用于任何类型的对象，包括二进制对象
* 可以处理任何类型的IO操作的功能，不能直接处理Unicode字符

#### 字符流
* 字符流只能处理字符或者字符串
* 可以处理Unicode字符

* BIO（普通socket用法）
	* 同步阻塞式 IO
	* 一对一，没有并发能力

* NIO（Chanel）
	* 同步非阻塞 IO
	* 支持一对多

* AIO
	* 异步非堵塞 IO (没用过，只是理解) 

### 快排
// todo

## 多线程

### Java线程的五种状态
* 新建（new）
	*  Thread thread = new Thread（）；
* 就绪（runnable）
	* thread.start（）；表示随时可以被cpu调度执行
* 运行（running）
	* 线程获取cpu调度开始执行
* 阻塞（blocked）- 
	* Blocked 等待获取锁
	* 同步阻塞；object.wait、Tread.join
	* 其他阻塞；sleep
* 死亡（dead）
	* 执行完毕，退出run方法

### Object的wait、notify、notifyAll和Thread的yield、sleep、join、interrupt
* Object的方法释放锁
* Thread的方法不会释放锁

* yiled - 线程让步
* Join - 主线程等待子线程运行完再继续运行

### 线程优先级
1. 线程创建具有继承优先级
2. j ava有个setpriority设置优先级，1～10，值越高，获取cpu时间片的概率越大
3. Android也有个setpriority设置优先级，-20～19，优先级priority越低获取概率越大


### new Thread 存在的问题 
* 每个线程的创建与销毁（特别是创建）的资源开销是非常大的
* 大量的子线程会分享主线程的系统资源，从而会使主线程因资源受限而导致应用性能降低。
* 线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控

### 线程池类型
1.  NewFixedThreadPool ：corePoolSize = maximumPoolSize , 核心线程数，等于最大线程数量，当线程池没有可执行任务时，也不会释放线程。
创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
Core = max = num
2.  NewCachedThreadPool ：在没有任务执行时，当线程的空闲时间超过keepAliveTime，会自动释放线程资源，当提交新任务时，如果没有空闲线程，则会重新创建线程执行任务，会导致一定的系统开销；使用该线程池时，一定要注意控制并发的任务数，否则 创建大量的线程可能导致严重的性能问题
创建一个可缓存的线程池,可灵活回收空闲线程，若无可回收，则新建线程。
Core = 0 ; max = Integer.MAX_VALUE
3.  newSingleThreadPool ：线程池中只有一个线程，如果该线程异常结束，会重新创建一个新的线程继续执行任务，唯一的线程可以保证所提交任务的顺序执行。
Core = max = 1
4.  newScheduledThreadPool ：在指定时间内周期性的执行所提交的任务，在实际的业务场景中可以使用该线程池定期的同步数据
创建一个定长线程池，支持定时及周期性任务执行
Core = num; max = Integer.MAX_VALUE

### RxJava线程池
* Schedulers.newThread ：总是启用新线程，并在新线程执行操作。
* Schedulers.io ：I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io中，可以避免创建不必要的线程。
Core = 0 ; max = Integer.MAX_VALUE
* Schedulers.computation ： 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被I/O 等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
Core = max = num

### 根据业务类型划分线程池
* CPU密集型任务 - CPU密集型任务应配置尽可能小的线程，如配置Ncpu+1个线程的线程池
Core = max = num

* IO密集型任务 - 读写比较频繁，考虑阻塞、非阻塞、同步、异步等Io操作，CPU消耗很少，任务的大部分时间都在等待IO操作完成（因为IO的速度远远低于CPU和内存的速度），增加线程数量可以充分利用cpu空闲时间片段
Core = 0 ; max = Integer.MAX_VALUE

* 并发线程池 - 获取一个用于文件并发下载的线程池,修改核心线程数和最大线程数

* 单线程池 - 获取一个单线程池，所有任务将会被按照加入的顺序执行，免除了同步开销的问题

### 线程池参数
1.  RejectedExecutionHandler (饱和策阅) ：线程池提供了4种策略： CallerRunsPolicy AbortPolicy DiscardPolicy DiscardOldestPolicy
2.  workQueue (饱和策阅) ： workQueue必须是BlockingQueue阻塞队列。当线程池中的线程数超过它的corePoolSize的时候，线程会进入阻塞队列进行阻塞等待。通过workQueue，线程池实现了阻塞功能
3.  corePoolSize 、maximumPoolSize、keepAliveTime、TimeUnit、ThreadFactory

### 线程池工作原理
1.  如果当前运行的线程少于corePoolSize，则创建核心线程来执行任务
2.  如果运行的线程等于或多于corePoolSize，则将任务加入BlockingQueue
3.  如果无法将任务加入BlockingQueue（队列已满），则创建新的线程来处理任务
4.  创建新线程将使当前运行的线程超出maximumPoolSize，任务将被拒绝，并调用RejectedExecutionHandler.rejectedExecution()方法
5.  corePoolSize线程里面的任务执行完毕，会从BlockingQueue中按照队列的顺序去取任务。
6.  在没有任务执行时，当线程的空闲时间超过keepAliveTime，会自动释放线程资源

### 线程池的几种状态
* Running
	* 线程池，一旦创建，就是running状态
* shutdown（不接收新任务，但能处理已添加的任务）
	* 调用shutdown()，线程池由RUNNING -> SHUTDOWN
* Stop（不接收新任务，不处理已添加的任务，并且会中断正在处理的任务）
	* 调用shutdownNow()，线程池由(RUNNING or SHUTDOWN ) -> STOP
* Tidying（所有的任务已终止）
	* 终止前的一个状态，会执行terminated
* TERMINATED（彻底终止）
	* 执行完terminated

## 锁

* 显示锁 : 实现了Lock接口和AQS框架。 例如 : ReentrantLock
* 隐式锁 : synchronized关键字。

### 锁的分类
> 宏观，加锁策略，乐观锁和悲观锁

#### 乐观锁
* 多线程共享数据，数据冲突，不加锁

#### 悲观锁
* 读写加锁

### synchronized关键字

#### 基本原理
* 通过内置锁实现同步
	* 实现同步，对共享 变量操作的原子性、保证了其他线程对共享变量的可⻅性、有序性
	* 使用的是monitorEnter和monitorExit两个字节码指令， 依赖于操作系统中的mutex lock实现
* 可重入锁
	* 避免了同一个线程重复请求自身已经获取的锁时出现死锁问题
* 基于JVM实现的，JVM会自动帮我们实现锁

#### 基础用法
* 锁类（修饰static方法）
* 锁对象（锁当前普通方法）
* 锁代码块 （锁修饰类）

#### synchronized实现原理
* synchronized关键字的实现，依赖于Java的对象头
* Java 对象头里的 Mark Word 里默认存储对象的 HashCode、分代年龄、是否偏向锁以及锁标 记位
* jdk优化，又分无锁、偏向锁、轻量级锁、重量级锁

### synchronized和ReentrantLock比较

#### 可重入性 ：
* 两者都是可重入的锁，锁的计数器都自增1，所以要等到锁的计数器下降为0时才能释放锁

#### 加锁原理
* Synchronized是依赖于JVM实现的，jvm会帮我们自动释放锁资源
* ReenTrantLock是JDK实现的，需要手动调用try{}finally{}unlock去释放锁，基于AQS实现的，do-while自旋，多次尝试和比较寄存器和内存的值是否一致，volatile + unsafe.cas做的

#### 性能区别
* Google源码推荐使用Synchronized，两者性能差不多
* Synchronized - 低并发
* ReenTrantLock - 高并发更优

#### 公平锁、非公平锁
* Synchronized - 只有非公平锁
* ReenTrantLock - 既有公平锁、非公平锁

#### 非公平锁实现原理：
* 通过构造还是来指定，默认是非公平锁
* 每个线程在获取锁时会先查看此锁维护的等待队列，如果为空，或者当前线程线程是等待队列的第一个，就占有锁，否则就会加入到等待队列中，以后会按照FIFO的规则从队列中获取

#### 颗粒度
* ReenTrantLock提供Condition（条件），使用更灵活
* ReenTrantLock提供了一种能够中断等待锁的线程的机制

#### 其他点
* Synchronized阻塞-唤醒，带来的系统开销更大一些
* Synchronized不能中途释放锁，必须锁对象的线程执行完毕或者出现异常会释放锁，不能中断一个正在试图获得锁的线程
* Synchronized竞争关系，不知道具体是哪个线程竞争到了，不够灵活

### ReentrantReadWriteLock
* ReadLock - 共享锁
* WriteLock - 独占锁
* 读读共享，读写互斥，写写互斥

### 死锁
> 出现多个锁，并且相互持有对方的锁、且互相都在等待对方释放，即死锁。
例如 : 


`
synchronized(1) {
	synchronized(2) {
	}
}
synchronized(2) {
	synchronized(1) {
	}
}
`


* 死锁的必要条件 ： 
	* 互斥 ：一个资源每次只能被一个线程使用
	* 占用且等待 ：一个线程因请求资源而被阻塞时，对已获得的资源保持不放
	* 不可抢占 ： 线程已获得的资源，不可被剥离
	* 循环等待 ： 若干线程直接，形成一种头尾相连的循环等待资源的关系
* 如何解决死锁问题 ：
	* 避免逻辑中，出现多个线程相互持有对方独占锁的情况
	* 在竞争激烈的环境下， 考虑使用ReetrantLock

### JUC
#### Condition
* Condition的作用是对锁进行更精确的控制。await(), signal(),signalAll()
* Object中的wait(),notify(),notifyAll()方法是和"同步锁"(synchronized关键字)捆绑使用的；
* 而Condition是需要与"互斥锁"/"共享锁"捆绑使用的。(ReentrantLock)

* 能够更加精细的控制多线程的休眠与唤醒。对于同一个锁，我们可以创建多个Condition，在不同的情况下使用不同的Condition.
* Condition能够更加精细的控制多线程的休眠与唤醒, Object的notify()或notifyAll()不能明确的指定唤醒,只能唤醒synchronized锁住的对象的线程,通过Condition，就能明确的指定唤醒读线程

#### LockSupport

* LockSupport中的park() 和 unpark() 的作用分别是阻塞线程和解除阻塞线程; 区别于wait() 和 notify() , 是因为wait()在线程阻塞前, 必须通过synchronized获取同步锁. 而LockSupport则不用synchronized锁,

#### CountDownLatch
> 对多线程的控制

* 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待

#### CyclicBarrier
> 对多线程的控制
* CyclicBarrier是一个同步辅助类，允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier
* CyclicBarrier是通过ReentrantLock(独占锁)和Condition来实现的

####  CountDownLatch和CyclicBarrier区别：
* CountDownLatch的作用是允许1或N个线程等待其他线程完成执行；而CyclicBarrier则是允许N个线程相互等待。
* CountDownLatch的计数器无法被重置；CyclicBarrier的计数器可以被重置后使用，因此它被称为是循环的barrier。

#### Semaphore
> 对多线程的控制

* Semaphore是一个计数信号量, 信号量维护了一个信号量许可集。线程可以通过调用acquire()来获取信号量的许可；当信号量中有可用的许可时，线程能获取该许可；否则线程必须等待，直到有可用的许可为止。 线程可以通过release()来释放它所持有的信号量许可.

### 生产消费模型
> 一般就是出一道题，让线程依次执行

## JMM内存模型

### volatile
* 可见性
	* 任意线程对这个 volatile 变量的读，都是最后的写入的值

* 原子性
	* 对Java基础类型的单次操作具有原子性，++无效，64位

* 禁止内存重排序 
	* volatile关键字修饰的变量，会在前后，各插入一个内存屏障

### final
1. final修饰类不能被继承
2. final修饰方法，不能被overide
3. final修饰变量，初始化后不能被改变值或者指向一个新的对象
4. final域的读、写不能重排序
5. 多线程下，任意线程能看到final域初始化后的值

* hanpens-before内存语义
	* final的读写在初始化,到变量引用之前, 均不能进行重排序.  
	* final可以修饰方法,表示不能被子类重写
	* 一旦修饰的变量初始化完成，其他线程无需同步，可以正确看见final字段的值。 一旦初始化完成，final变量立刻写回到主内存


## JVM

### 类加载

#### 双亲委派机制
* 子类使用“组合”关系来复用父类加载器的代码，而不 是继承
* Bootstarp Class Loader <- Extension Class Loader <- Application Class Loader <- 自定义类加载器

#### ClassLoader : 
* BootstrapLoader - 负责加载系统类 (这个是C++来实现的, Java里面调不了) 
	* 路径 - 加载$JAVA_HOME/jre/lib下载的rt.jar中的文件，其中是Java的核心类.Bootstrap classloader是JVM中的实现
* ExtClassLoader - 负责加载扩展类
      * 路径 - 加载lib/ext文件夹下的jar包
* AppClassLoader - 负责加载应用类 
      * 路径 - 加载ClassPath目录下的jar包和Class文件

#### Java类加载过程
* 装载 : 查找和导入class文件
* 链接 : 其中解析步骤是可以选择
    * (a)检查 : 检查载入的class文件数据的正确性
    * (b)准备 : 给类的静态变量分配存储空间
    * (c)解析 : 将符号引用转成直接引用
* 初始化 : 对静态变量 , 静态代码块执行初始化工作

#### Java对象创建过程
* 类加载检查
	* 先判断类是否已加载，
	* 如果未加载，需要重新走一遍类加载过程（装载、链接、初始化）
* 分配内存
	* 在类加载完成后，对象所需要的内存大小完全确定，可以对新生对象分配内存。
* 初始化内存空间
* 进行对象头设置
	* 主要是mark word，包括哈希码， GC 分代年龄，锁状态标志，线程持有 的锁，偏向线程ID,偏向时间戳等

* 最后才是执行构造函数

#### ART机制和Dalvik机制

* 在 Dalvik 下，应用每次运行的时候，字节码都需要通过即时编译器(just in time ，JIT)转
换为机器码，这会拖慢应用的运行效率。

* 而在 ART 环境中，应用在第一次安装的时候，字节码就会预先编译成机器码，极大的提高 了程序的运行效率，同时减少了手机的耗电量，使其成为真正的本地应用。这个过程叫做预 编译(AOT,Ahead-Of-Time)。这样的话，应用的启动(首次)和执行都会变得更加快速。

* 缺点 - 包体积更大、安装时间变长

### JVM内存结构
* 线程共享区域 ：
	* 方法区 ：加载的类信息、常量、静态变量
		* 抛出OutOfMemory异常
	* 堆 ： 主要存放对象实例和数组。
		* 新生代 - eden/survivor（from、to），比例：8：1：1
		* 老年代
		* 永久代 - 1.8以后是metaspace
		* 抛出OutOfMemoryError
* 线程私有区域：
	* 虚拟机栈 - 每个方法在执行时，都会创建一个栈帧用用于存储局部变量表，生命周期，是方法执行结束，一个栈帧从虚拟机栈中入栈出栈的过程。这个是位java方法（字节码）提供的服务
		* 会抛出 StackOverflowError 和 OutOfMemoryError
	* 本地方法栈 - 虚拟机使用到的native方法服务的。
		* 会抛出 StackOverflowError 和 OutOfMemoryError
	* 程序计数器 - 通过改变计数器的值来选取下一个要执行指令的字节码。
		* 不会有OutOfMemoryError

### 垃圾回收机制
* 引用计数算法
	* 很难解决对象之间相互循环引用的问题

* 可达性分析算法
	* GC Roots作为起点, 查看引用链

### gc root对象 
1. 方法区中，静态属性引用的对象
2. 方法区中，常量的引用对象
3. 虚拟机栈中引用的对象
4. 本地方法栈中jni引用的对象

### 软引用、弱引用
* 软引用 ： 内存不足的时候，才会被回收
* 弱引用 ：每次执行gc的时候，才会被回收
* 虚引用 ：直接就是为null
* 强引用 ：一个对象具有强引用，垃圾回收绝对不会回收它

### 垃圾回收算法
* 标记-清除 ：单线程
* 标记-整理 ：老年代大多数用这个
* 复制算法 ：新生代大多数用这个
* 分代收集算法

### 垃圾收集器
* Serial/serial Old : 单线程版本，一个运行在新生代，一个运行在老年代，会暂停用户线程，直到搜集完毕
* ParallelNew ：Serial收集器多线程版本
* Parallel Scavenge / Parallel Old : 多线程收集器，一个用于新生代，一个用于老年代
* Concurrent Mark Sweep : 并发使用标记 - 清除算法的垃圾收集器，大部分都用这个
* G1，Java 1.9以后会替换Concurrent Mark Sweep

### Java编译过程
> 有可能是错的

* Javac 编译成class
* 程序运行在jvm环境中
* 依次执行，类加载，对初始化过程
* JIT编译器是存在于jvm中， 将字节码编译为机器指令，再由cpu执行



# 引用
* 八股文
* [HashMap的扩容机制](https://zhuanlan.zhihu.com/p/114363420) 
* [Java代码编译和执行的整个过程-简述(一)](https://zhuanlan.zhihu.com/p/32453148)