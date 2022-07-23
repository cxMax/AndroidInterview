# android_Java_面经

## 软引用、弱引用
* 软引用 ： 内存不足的时候，才会被回收
* 弱引用 ：每次执行gc的时候，才会被回收
* 虚引用 ：直接就是为null
* 强引用 ：一个对象具有强引用，垃圾回收绝对不会回收它

## 内存泄漏问题
* 查找内存泄露方式：
	* Java ：
		1. 手动通过dump hprof文件通过mat来分析
		2. 通过LeakCanary工具
		3. dumpsys meminfo命令可以查看进程。这里涉及到几个概念
			* RSS - 实际使用物理内存（包含共享内存）
			* VSS - 虚拟耗用内存（包含共享内存）
			* PSS - 实际使用物理内存
			* USS - 物理内存（进程独占内存）
	* C++ ：
		1. Google提供的malloc debug
		2.GitHub开源库LeakTracker

## 垃圾回收机制
* 引用计数法
* 可达性分析法 - gc root引用链（JVM用的这个）


## GC Root对象有哪些？
* 类对象
* 线程
* Java方法和Local变量和参数
* JNI方法的变量和参数
* 全局JNI引用

## 垃圾搜集算法
* 标记-清除 ：单线程
* 标记-整理 ：老年代大多数用这个
* 复制算法 ：新生代大多数用这个
* 分代收集算法

## 垃圾收集器
* Serial/serial Old : 单线程版本，一个运行在新生代，一个运行在老年代，会暂停用户线程，直到搜集完毕
* ParallelNew ：Serial收集器多线程版本
* Parallel Scavenge / Parallel Old : 多线程收集器，一个用于新生代，一个用于老年代
* Concurrent Mark Sweep : 并发使用标记 - 清除算法的垃圾收集器，大部分都用这个
* G1，Java 1.9以后会替换Concurrent Mark Sweep

## JVM内存结构
### 所有线程共享的数据区：
* 方法区（Method Area） - 已加载的类信息、常量、静态变量、及时编译器编译后的代码等数据
* 堆内存（Heap） - 所有对象实例和数组都要分配到堆内存上
	* 新生代 - eden/survivor（from、to），比例：8：1：1
	* 老年代
	* 永久代 - 1.8以后是metaspace

### 线程隔离的数据区 ：
* 虚拟栈 ：
	* 生命周期与线程的生命周期相同
	* 每个方法在执行的时候，会创建一个栈帧，用于存储局部变量
	* 这里会抛出StackOverFlowError、OutOfMemoryError.
	* StackOverFlowError - 无限递归和循环。线程请求的栈深度大于虚拟机所允许的深度
	* OutOfMemoryError - 内存泄漏，导致拓展时无法申请到足够的内存
* 程序计数器 ：
	* 当前线程所执行字节码的信号指示器
	* 每一个线程都需要一个独立的程序计数器，各条线程的计数器互相不影响，独立存储
	* 唯一没有任何OutOfMemoryError的区域
* 本地方法栈 ：
	* 虚拟机使用到native方法服务的
	* 也会抛出StackOverFlowError、OutOfMemoryError

## bitmap内存缓存为什么用LruCache，不用SoftReference
* SoftReference源码有写：Google推荐内存缓存管理，使用LruCache数据结构，而非软引用
* Glide三级缓存 ：
	1. WeakReference的内存缓存
	2. LruCache的内存缓存
	3. DiskLruCache二进制文件，硬盘缓存

### LruCache数据结构原理
* 底层使用的LinkHashMap
* LinkedHashMap和HashMap的区别：
	* Entry，LinkedHashMap是双链表，HashMap是单链表
* HashMap的数据结构 ：
	1. 可以用数组 - 线性探测法（出现hash冲突）
	2. 可以用node - 拉链法（一个一个单链表，到后面转化为红黑树）

## Bitmap原理
> 分为Bitmap的创建和Bitmap的释放

### Bitmap创建
* Native也会创建skbitmap，指向的是Heap对象。所以，实际上像素的内存只有一份，被不同对象持有，Java层的Bitmap和native层的bitmap都指向它

### Bitmap的回收
* Java层调用bitmap.recycle()最终还是调用的native层的bitmap::freePixels的方法，
* native层移除Global对象的引用，这个对象是Heap上一堆像素空间，会在gc时释放掉。
* 至于java层，此时bitmap对象还在，只是bitmap内部mBuffer是无效地址，没有像素Heap的Bitmap就几乎不消耗内存了。也是在gc时候，会释放掉这个java内存

### bitmap大小计算
* 大小 = 高 * 宽 * 一个像素所占的内存
* ARGB_8888 4个字节；RGB_565 2个字节；ARGB_4444 2个字节；

### Bitmap内存优化
1. 缓存的使用 - LruCache
2. 图片压缩
	* 压缩格式 ：jepg、webp取代png
	* 压缩质量 ：0 - 100

## 排序算法
### 比较排序 ：
* 冒泡排序、快速排序、插入排序、希尔排序、选择排序、归并排序

* 快速排序 - Arrays.sort()其实用的就是快排
* 排序算法中最快的是快速排序算法，搜索算法中最快的是二分搜索算法。
* 时间复杂度O(nlogn)

### 非比较排序 ：
* 计数排序、桶排序、基数排序

* 桶排序
	1. 桶排序是稳定的
	2. 桶排序是常见排序里最快的一种,比快排还要快…大多数情况下
	3. 桶排序非常快,但是同时也非常耗空间,基本上是最耗空间的一种排序算法
	* 时间复杂度O(n)

## final关键字
1. final修饰类不能被继承
2. final修饰方法，不能被overide
3. final修饰变量，初始化后不能被改变值或者指向一个新的对象
4. final域的读、写不能重排序
5. 多线程下，任意线程能看到final域初始化后的值


## JMM内存模型
* 内存屏障
* happens - before
* 重排序
* 顺序一致性
* volatile关键字

## 多线程、线程池、加锁的考察

### Java线程的五种状态
* 新建（new）- Thread thread = new Thread（）；
* 就绪（runnable）- thread.start（）；表示随时可以被cpu调度执行
* 运行（running）- 线程获取cpu调度开始执行
* 阻塞（blocked）- 等待阻塞wait（）；同步阻塞synchronized；其他阻塞sleep（）
* 死亡（dead） - 执行完毕，退出run方法


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


### AsyncTask
* 内部实现 ：
	* 使用Callable + Future 用于获取结果
* 缺点 ：
	1. 默认的拒绝策略, 和128长度的线程池 , 如果处理大量的任务, 会导致进程直接崩溃
	2. 内部是默认串行机制, 如果并行的, 因为doInbackground()访问了他公共资源, 未作同步, 所以gg
	3. 必须在主线程初始化, 因为默认的IntentHandler是Looper是主线程的
	4. 内存泄漏, 内部会持有外部引用
	5. cancel不一定能doInBackground会执行到结束, 只是不执行onPostExecute
	6. Activity生命周期结束，AsyncTask会一直执行doInBackground()方法直到方法执行结束
	7. 如果cancel(boolean)调用了,doInBackground()仍然会执行到方法结束，只是不会去调用onPostExecute()方法
	8. 在AsyncTask全部执行完毕之后，进程中还是会常驻corePoolSize个线程
	9. AsyncTask串行和并行的问题，默认是串行，提供了并行函数。如果需要并行， 需要处理好doInBackground()访问公共资源同步的问题
	10. 在AsyncTask全部执行完毕之后，进程中还是会常驻corePoolSize个线程。因为线程池没有取消。
在android 11 上已经废弃了，官方建议用java并发类和kotlin协程来解决

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
1.  如果当前运行的线程少于corePoolSize，则创建新线程来执行任务
2.  如果运行的线程等于或多于corePoolSize，则将任务加入BlockingQueue
3.  如果无法将任务加入BlockingQueue（队列已满），则创建新的线程来处理任务
4.  创建新线程将使当前运行的线程超出maximumPoolSize，任务将被拒绝，并调用RejectedExecutionHandler.rejectedExecution()方法
5.  corePoolSize线程里面的任务执行完毕，会从BlockingQueue中按照队列的顺序去取任务。
6.  在没有任务执行时，当线程的空闲时间超过keepAliveTime，会自动释放线程资源

### 线程池的几种状态
* Running 
	* (01) 状态说明：线程池处在RUNNING状态时，能够接收新任务，以及对已添加的任务进行处理。
	* (02) 状态切换：线程池的初始化状态是RUNNING。换句话说，线程池被一旦被创建，就处于RUNNING状态！
* shutdown 
	* (01) 状态说明：线程池处在SHUTDOWN状态时，不接收新任务，但能处理已添加的任务。
	* (02) 状态切换：调用线程池的shutdown()接口时，线程池由RUNNING -> SHUTDOWN。
* Stop
	* (01) 状态说明：线程池处在STOP状态时，不接收新任务，不处理已添加的任务，并且会中断正在处理的任务。
 	* (02) 状态切换：调用线程池的shutdownNow()接口时，线程池由(RUNNING or SHUTDOWN ) -> STOP。
* Tidying
	* (01) 状态说明：当所有的任务已终止，ctl记录的"任务数量"为0，线程池会变为TIDYING状态。当线程池变为TIDYING状态时，会执行钩子函数terminated()。terminated()在ThreadPoolExecutor类中是空的，若用户想在线程池变为TIDYING时，进行相应的处理；可以通过重载terminated()函数来实现。
	* (02) 状态切换：当线程池在SHUTDOWN状态下，阻塞队列为空并且线程池中执行的任务也为空时，就会由 SHUTDOWN -> TIDYING。
当线程池在STOP状态下，线程池中执行的任务为空时，就会由STOP -> TIDYING。
* TERMINATED
	* (01) 状态说明：线程池彻底终止，就变成TERMINATED状态。
	* (02) 状态切换：线程池处在TIDYING状态时，执行完terminated()之后，就会由 TIDYING -> TERMINATED。

## 线程同步
### volatile关键字
1. 内存可见性 ： 每个线程获取volatile的变量都是最新的
2. 禁止内存重排序 ： volatile关键字修饰的变量，会在前后，各插入一个内存屏障，禁止重排序 （这个答漏掉了， 主要是单例模式double check， 为什么要这么写的原因）
3. 仅仅对Java基础类型的单次操作具有原子性


### Volatile关键字如何保证可见性
* 普通变量 ： 读取到工作内存，是从主内存拷贝过来了，写只是修改工作内存，其他线程从主内存读取到的还是之前的
* volatile修饰的变量 ： 读写都是对主内存进行操作，工作内存中的值会设为无效


### synchronized关键字
* 锁方法、锁代码块（锁类、锁对象）


* synchronized本质 ： 
	* 保证方法内部或代码块内部资源（数据）的互斥访问。即同一时间、由同一个monitor监视的代码，最多只能有一个线程在访问
	* 保证线程之间对监视资源的数据同步。即，任何线程在获取到monitor后的第一时间，会先将共享内存中的数据复制到自己的缓存中；任何线程在释放monitor的第一时间，会先将缓存中的数据复制到共享内存中。


### synchronized和ReentrantLock比较
* 可重入性 ：
	* 两者都是可重入的锁，锁的计数器都自增1，所以要等到锁的计数器下降为0时才能释放锁

* 加锁原理
	* Synchronized是依赖于JVM实现的，jvm会帮我们自动释放锁资源
	* ReenTrantLock是JDK实现的，需要手动调用try{}finally{}unlock去释放锁，基于AQS实现的，do-while自旋，多次尝试和比较寄存器和内存的值是否一致

* 性能区别
	* Google源码推荐使用Synchronized，两者性能差不多
	* Synchronized - 低并发
	* ReenTrantLock - 高并发更优

* 公平锁、非公平锁
	* Synchronized - 只有非公平锁
	* ReenTrantLock - 既有公平锁、非公平锁

* 非公平锁实现原理：
	1. 通过构造还是来指定，默认是非公平锁
	2. 每个线程在获取锁时会先查看此锁维护的等待队列，如果为空，或者当前线程线程是等待队列的第一个，就占有锁，否则就会加入到等待队列中，以后会按照FIFO的规则从队列中获取

* 颗粒度
	* ReenTrantLock提供Condition（条件），使用更灵活
	* ReenTrantLock提供了一种能够中断等待锁的线程的机制

* 其他点
	* Synchronized阻塞-唤醒，带来的系统开销更大一些
	* Synchronized不能中途释放锁，必须锁对象的线程执行完毕或者出现异常会释放锁，不能中断一个正在试图获得锁的线程
	* Synchronized竞争关系，不知道具体是哪个线程竞争到了，不够灵活


### ReentrantReadWriteLock
* ReadLock - 共享锁
* WriteLock - 独占锁
* 读读共享，读写互斥，写写互斥


### 乐观锁、悲观锁
> Android出现比较少，主要是出现后端。跟锁没关系，主要是两种数据库思想。


* 乐观锁：
	* 乐观锁在操作数据时非常乐观，认为别人不会同时修改数据。因此乐观锁不会上锁，只是在执行更新的时候判断一下在此期间别人是否修改了数据：如果别人修改了数据则放弃操作，否则执行操作。
* 悲观锁：
	* 悲观锁在操作数据时比较悲观，认为别人会同时修改数据。因此操作数据时直接把数据锁住，直到操作完成后才会释放锁；上锁期间其他人不能修改数据。


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

## Java数据结构考察

### 数组
1. 内存中连续，因为是静态分配内存。
2. 查询时间复杂度O（1），插入/删除 O（n）

* 缺点
	* 当数组长度发生变化时，原有的元素需要经过copy到新的数组中，这样性能有较大的损耗。

* 那么二维数组在内存的空间地址是连续的么？
	* C++中二维数组在地址空间上是连续的，Java是没有指针的，同时也不对程序员暴漏其元素的地址，寻址操作完全交给虚拟机

### 链表
> 单链表、双链表、循环链表


1. 内存中不连续，他是动态分配内存
2. 查询时间复杂度O（n），插入/删除 O（1）


### 哈希表
* 实现方式：
	* 数组、链表数组（Entry[]）

* Hash碰撞是什么？
	* 对象的个数大于hash表的个数，此时计算hash函数计算得再均匀，也避免不了几个对象同时映射到一个索引下

* Hash冲突解决方式？
	* 拉链法 - 发生冲突的元素，被存储在链表中
	* 线性探测法 - 一定要保证tableSize大于dataSize。 我们需要依靠哈希表中的空位来解决碰撞问题，寻找冲突的下一个空位。


### List集合

#### Vector
1. 底层还是数组来实现的
2. 线程安全
* 因为add, remove等操作加了synchronized关键字进行加锁保证线程安全

#### ArrayList
1. 底层还是数组来实现的

#### Stack
1. 是基于Vector来实现的，是线程安全的

* 但是，缺点 ：
	1. 当数组默认的容量发生改变时,原有的元素需要经过copy到新的数组中,因此pop、push的性能会有较大降低
	2. 继承的是vector这个类，有add等方法，破坏了stack的api

#### LinkedList
1. 单链表实现的
2. 实现了Deque接口，双端队列，官方推荐使用 Deque(双端队列) 代替 Stack， 有链表和数组分别实现的

#### CopyOnWriteArrayList
1. 底层数组实现的
2. 通过了synchronized关键字进行加锁保证线程安全

#### Collections.synchronizedList - 包装类，实现线程安全


### Set集合

#### HashSet
1. 它通过HashMap实现的；HashSet不是线程安全的，只适用于单线程

#### TreeSet
1. 它是通过TreeMap实现的

#### CopyOnWriteArraySet
1. 通过CopyOnWriteArrayList来实现的

### Map集合
#### HashMap
1. 扩容问题
	* 扩容因子0.75，是否扩容有threshold决定，threshold是由初始容量和 loadFactor 
	* Resize = 容量容积 * 扩容因子， 初始化16的话， 那就是12的时候，就要resize了
2. 有序怎么做？
	* 单链表实现改成双链表，类似LinkedHashMap
3. Hash冲突的解决
	* 拉链法 - Entry数组
4. 红黑树
	* 链表元素达到8个后，会调用treeifyBin转化为红黑树
5. 为什么要转红黑树？
	* 我理解就是查找效率，黑树的查找效率为o(logN)，要优于链表的o(N)
	* 保证每次插入最多只需要三次旋转就能达到平衡

#### WeakHashMap
* HashMap的“键”是强引用类型，而WeakHashMap的“键”是弱引用类型

#### HashTable
1. 线程安全，所有操作都是sychronized关键字包裹，读写加锁
2. put不能放null，会抛异常
3. hash算法和hashmap不一样，直接用的key.hashcode

#### TreeMap
1. 通过红黑树（R-B tree）实现
2. 排序怎么做到的呢？内部也是通过Comparator接口排序的

#### LinkedHashMap
1. 有序的hashmap
2. 双向链表 - 保证的有序
3. LruCache内部实现就是通过LinkedHashMap来做的

#### ConcurrentHashMap
1. 加锁机制
	* 1.6以前segment分段锁机制（继承ReentrantLock）
	* 1.8以后抛弃了segment分段锁机制，利用CAS+sychronized来保证并发安全
2. size方法
	* 1.7以前，不准确，最多三次计算做比较。
	* 1.8以后。volatile关键字计算
3. 同步机制
	* 因为分段锁的机制，对同一片段访问是互斥的，对于不同片段访问，是可以同步进行的。相比于HashTable高并发场景更适合


### Queue集合


#### ArrayBlockingQueue
1. 数组，FIFO（先入先出）
2. ReentrantLock来实现，一个锁

#### LinkedBlockingQueue
1. 单向链表，插入锁、取出锁，可以同时读写

#### LinkedBlockingDeque
1. 双向链表
2. FIFO、FILO

#### ConcurrentLinkedQueue
* 单向链表实现的队列， FIFO
* volatile + CAS实现线程安全，性能最高

#### ConcurrentLinkedDeque
* 双向节点链表，FIFO/FILO
* volatile + CAS实现线程安全，性能最高


### Android特有数据结构


#### ArrayMap
1. 使用数组来实现的
2. 内存占用的优化
	* 在内存中物理位置是连续的


#### ArraySet
1. 也是数组实现的
2. 包含单一不重复元素
3. 内存使用上更节省

#### SpareseArray
* 避免自动装箱、拆箱的损耗，节约内存

## 性能优化相关
### 冷启动优化
#### app层面：
* 三个阶段 ：
	* 创建进程（系统去做的，唯一相关的就是包大小，去load apk）、
	* 启动应用（启动主线程，启动application，启动mainactivity）
		* Application::attachBaseContext() 和 application::OnCreate()
	* 绘制界面（加载布局和首帧绘制）
		* Activity::OnWindowFocusChanged xml渲染成view tree，用户可交互，		 
		* Activity::OnFirstDraw 第一帧开始渲染，宽高已经测量好了
		* Activity预加载 -> 数据预加载
* 工具 ：
	* systrace
	* 函数耗时打印
		* Debug.startMethodTracing(“tracefilename");
		* Trace.beginSection()
	* AOP技术 - spectJ代码插桩
* 方法 ：	
	1. application异步并发初始化，有依赖关系的，可以用Android异步启动框架Alpha
	
	2. 首页xml的优化，这里也会影响加载速度。过度绘制， 这里就引申出懒加载、延迟加载、异步加载
	3. 启动过程中发生gc，也会导致100～200ms左右的并发回收时间。这里主要是避免大量创建对象和draw过程中创建对象，主要是数据结构的合理使用、循环递归代码避免创建对象
	4. 线程调度的时间。一般异步线程到主线程回调，一般是大概60ms～100ms
	5. 冷启动过程中关闭一些动画的播放，避免占用主线程资源
	6. sp使用优化，大文件异步读取，有遇到过全部配置sp在主线程读取，很耗时间
	7. bindapplication这里有一个去加载apk资源，所以这里包体大小也会对冷启动时间有所影响，大概是400ms
	8. 还可以启动首页和基础模块的class文件，打入到主dex中，从而也可以提升启动速度

* framework层面：
	1. 打印framework全日志
	2. SystemServer（应用的启动流程调度、进程的创建和管理、窗口的创建和管理） 和 AppProcess（的进程初始化、组件初始化(Activity、Service、ContentProvider、Broadcast)、主界面的构建、内容填充等）
	3. 启动过程中减少系统调用，避免与 AMS、WMS 竞争锁
	4. 启动过程中不要启动子进程
	5. 启动过程中除了 Activity 之外的组件启动要谨慎

* 检测冷启动的方式：
	1. am start
	2. 通过log，reportFullyDrawn
	3. 可以通过录屏的方式

### UI卡顿
#### UI卡顿检测方式 ：
1. 通过设置strickmode会打印耗时代码堆栈
2. 使用systrace，也只有手动进行调试，对于一些隐藏的找不到。解决红标block的地方
3. 使用主线程Looper::setMessageLogging，自定义printer，使用handlerthread，超过1s的消息，打印堆栈
4. 系统提示“The application may be doing too much work on its main thread”，去实现Choreographer::FrameCallback接口，掉帧的地方，打印主线程代码堆栈信息


### 内存泄漏
1. MAT, 需要配合自动化脚本，再测试阶段去dump hprof文件，主要还是通过activity的实例去找持有对象。
2. 使用leakcarnary在开发调试阶段，自检内存泄漏代码

### LeakCanary原理
1. 默认会exclude掉很多原生泄漏，还有厂商rom搞出来的
2. 监控activity、fragment， 也可以自定义
3. Activity的话，通过Application::ActivityLifecycleCallbacks在ondestroy生命周期回调中， 去调用refwatacher.watch函数 
4. fragment的话，通过FragmentMananger.FragmentLifecycleCallback在ondestory去watch的.  Android 26以上（即8.0以上）。

* 具体怎么去做，看对象有没有被回收？
	1. 有个知识点 - 在主线程做的， 并非子线程
	2. 第二个，用到了IdleHandler -> 主线程空闲的时候才去做 
	3. 先移除RefrenceQueue里面的弱饮用、软引用。（创建的时候，会添加进去，软引用第二个默认参数）
	4. 还会执行一次gc， 因为gc后不会立刻回收，有可能要等一会
		* 这里有点意思的，他gc的代码，是从finalizationTester拷贝过来的。
		* 因为Sytem.gc不会每次都执行
	5. 所以这里还要移除一次
	6. 还是通过dump， hprof文件
	7. 然后呢，分析的话，是在子进程中，一个线程里面做的
	8. 最新的contentProvider初始化的
		* contentProvider初始化快于Application，看源码


### 内存泄漏场景
1. 资源释放。cursor，bitmap.recycle，io close。
2. 单例持有activity
3. 匿名类做耗时操作，并持有activity。常见的handler.postDelay
4. 主线程/子线程handler非静态对象
5. Android 5.0、6.0时期原生api

### 稳定性如何做的？
1. 开发过程中review代码
2. 项目架构给稳定性带来的影响（lifecycle、livedata、navigation）
3. 静态扫描（findbugs、checkstyle）-> precommit
3. crash日志（Java、C++）addres2line

### 代码review怎么做的？
1. 代码规范（命名、函数是否过长、有没有copy+paste）
2. 简单的异常NPE、越界
3. 是否引用三方库（版本兼容性、是否有第三方依赖造成冲突、是否有单独构造线程池等）
4. res资源文件（资源压缩、字符串资源标点符号问题、布局文件是否合理）
5. 代码质量（设计模式->多个成员变量标记位 、 if、else深入嵌套）
6. 数据结构使用合理

### 包体大小优化
1. abi打包
2. 资源文件压缩（vector->webp->jepg->png、图片本地压缩tinypng）
3. 代码层面。
	* 混淆
	* 不必要的代码引用，主要来源三方库
4. apk bundle方案
5. Lottie动画优化-》腾讯有个库
7. 一套资源文件


### 线程池优化
1. 杜绝new thread使用
2. AsycTask正确使用
3. 按业务类型划分线程池
4. 三方库又构造新的线程池，造成内存资源浪费，使用api用项目中的线程池

## ANR
### ANR类型：
* Service Timeout:比如前台服务在20s内未执行完成；后台服务则是200s
* BroadcastQueue Timeout：比如前台广播在10s内未执行完成
* ContentProvider Timeout：内容提供者,在publish过超时10s;
* InputDispatching Timeout: 输入事件分发超时5s，最主要是这个.


### ANR的保存路径 :
* data/anr/traces.txt

### ANR文件分析 ：
* 先看trace, 排查内存是否足够,Total和Max; 是否存在线程死锁, 阻塞了主线程
* 第二看system_server这个进程, 是否是因为其他进程原因影响了app进程的anr
* 看log上下文, 主要看anr发生前（AMS打开了哪些界面、有没有系统服务挂掉的问题、是否是主线程日志打印占用cpu资源导致的）




