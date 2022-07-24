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

### Android framework源码相关

#### Binder机制

* C/S架构：
	* 三层 ： java 、c++ 、kennel驱动层

* Client : proxy -> BinderProxy -> BpBinder
* Server : Stub -> Binder -> BBinder

* 通信方式 ：
	* c++层面，BpBinder 通过transcat发送请求
	* c++层面，BBinder，通过onTranscat处理请求

* 通过parcel传递对象。

* 一次数据拷贝，物理内存-》内核-〉用户空间

* 服务都是注册到ServiceManager里面，有个 Java缓存sCache


* TranscationTooLargeException
	* Binder传递大数据，会导致TranscationTooLargeException

* 出现场景：
	* bundle传递bitmapbitmap是parcel

* bundle setAllowFds(false)主要是这个，
* 打开了的话， 直接拷贝内存地址，就不会要不错了。

* 适用的方案：
	* contentprovider、memory， 用的是共享内存。 
	* 不是binder传输， binder传输的话， 要打开setAllowFds描述符

#### IPC方式
* 管道（半双工，单向）
	* 单读、单写
	* 用于父子进程
* 场景:
1. looper::looper 6.0以后用的eventfd
	* 缺点：传输数据量不能太大，至少两次内存拷贝
	* 2次数据拷贝，应用层-》内核，内核-〉应用层

* Socket（全双工，即可读写）
	* 两个进程通信
* 场景：
	1. zygote入口函数，用于进程创建
	* 缺点：传输数据量不能太大，至少两次内存拷贝

* 共享内存（很快，不需要多次）
	* 两个进程也是没关系
* 场景：
	1. MemoryFile -> native_mmap映射到内存空间
	* 优点：
		* 进程大数据传输

* 信号（单向，发出去之后怎么处理他不知道）
	* 只能带信号，不能带出参数
	* 知道进程pid就能发信号，也可以一次给一群进程发信号
	* 场景：
		* 杀进程的时候会发信号

* Binder
	* 内存拷贝1次

### 手机开机流程
* zygote进程启动
	* （通过c++调用的main函数）
* zygoteInit.java main函数
	1. 资源预加载，系统class文件、系统资源文件、系统动态库
	2. 启动systemserver进程（也是systemserver的main函数）
	3. 创建zygotesocket服务，是用来接受AMS应用创建的请求
	4. 进入阻塞状态，等待请求，等待ams申请创建应用进程的请求
* Systemserver main函数
	1. 启动引导服务(ams、pms等，一共17个)
	2. 启动核心服务(电池管理服务、usagestate服务等等，一共9个)
	3. 启动其他一般服务（蓝牙服务、电话服务等等，一共90个）
* Ams::systemReady() 去启动launcher服务

### SystemServer启动流程
* SystemServer进程是android中一个很重要的进程由Zygote进程启动；
* SystemServer进程主要用于启动系统中的服务；
* SystemServer进程启动服务的启动函数为main函数；
* SystemServer在执行过程中首先会初始化一些系统变量，加载类库，创建Context对象，创建SystemServiceManager对象等之后才开始启动系统服务；
* SystemServer进程将系统服务分为三类：boot服务，core服务和other服务，并逐步启动
* SertemServer进程在尝试启动服务之前会首先尝试与Zygote建立socket通讯，只有通讯成功之后才会开始尝试启动服务； 其他服务都是通过binder进行通信的
* 创建的系统服务过程中主要通过SystemServiceManager对象来管理，通过调用服务对象的构造方法和onStart方法初始化服务的相关变量；
* 服务对象都有自己的异步消息对象，并运行在单独的线程中；


### launcher启动流程
* Ams::systemReady()  启动launcher入口
* activitytaskmanagerservice.localservice::startHomeOnAllDisplay
	1. android 10引进的RootAcvtityContainer::StartHomeOnDisplay
	1. 调用PMS查询符合Launcher应用条件的activity intent（其实主要就是配置intent为home和default标记）
	* PackageManagerService，该服务也是android系统中一个比较重要的服务，包括多apk文件的安装，解析，删除，卸载等等操作。


* ActivityStarter::startActivityUnchecked()
	1. 清单文件注册校验，启动权限检查
	2. 根据启动模式和intent.flag计算出该activity所属的任务栈并加入，单此刻并不会显示。


* ActivityStack::resumeTopActivityInnerLock()
	1. 启动activity之前会把当前可见的activity暂停。计算待启动的activity所属进程是否存在


* ActivityStackSuperVisor::startSpecificActivityLocked
	1. 进程如果存在就启动activity。
	2. 如果进程不存在就创建进程


* Ams::startProcess
	1. 委派给ProcessList来负责进程的创建


* ProcessList::startProcessLocked
	1. 建立连接zygote进程的socket
	2. 把进程创建所需要的信息通过socket发送出去
	3. 通过ActivityThread来创建进程

* 总结：

* Intent的显式启动和隐式启动
	* 隐式 - IntentFilter配置的action等参数去启动
	* 显式 - 通过类名去启动

* Launcher的启动流程

	* Zygote进程 --> SystemServer进程 --> startOtherService方法 --> ActivityManagerService的systemReady方法 --> startHomeActivityLocked方法 --> ActivityStackSupervisor的startHomeActivity方法 --> 执行Activity的启动逻辑，执行scheduleResumeTopActivities()方法。。。。

	* 因为是隐式的启动Activity，所以启动的Activity就是在AndroidManifest.xml中配置catogery的值为：
	* public static final String CATEGORY_HOME = "android.intent.category.HOME";
	* 可以发现android M中在androidManifest.xml中配置了这个catogory的activity是LauncherActivity，所以我们就可以将这个Launcher启动起来了

	* LauncherActivity中是以ListView来显示我们的应用图标列表的，并且为每个Item保存了应用的包名和启动Activity类名，这样点击某一项应用图标的时候就可以根据应用包名和启动Activity名称启动我们的App了。


### 应用进程启动流程
1. 我们可以通过启动四大组件的方式启动应用进程，在应用进程没有启动的时候，如果我们通过启动这些组件，这时候系统会判断当前这些组件所需要的应用进程是否已经启动，若没有的话，则会启动应用进程

2. Instrumentation - 当我们在执行对Activity的具体操作时，比如回调生命周期的各个方法都是借助于Instrumentation类来实现的

3. ActivityThread - 进程的启动方法就是ActivityThread的main方法


### 系统启动并解析Manifest的流程
1. android系统启动过程中解析Manifest的流程是通过PackageManagerService服务来实现
2. SystemServer启动boot服务，初始化PackageManagerService的时候，解析了系统中几个apk的安装目录（“data”、“app”）
3. 创建PackagerParser去扫描Manifest每一个节点
4. 保存到Package内存里面


### 系统apk安装流程
1. 代码中执行intent.setDataAndType(Uri.parse(“file://" + path),"application/vnd.android.package-archive");可以调起PackageInstallerActivity；
2. PackageInstallerActivity主要用于执行解析apk文件，解析manifest，解析签名等操作；
3. InstallAppProcess主要用于执行安装apk逻辑，用于初始化安装界面，用于初始化用户UI。并调用PackageInstaller执行安装逻辑；
4. InstallAppProcess内注册有广播，当安装完成之后接收广播，更新UI。显示apk安装完成界面；


### Activity启动流程

* A启动B : 
	* onPause(a) -> onCreate(b) --> onStart(b) --> onResume(b) --> onStop(a)
* 然后杀掉B : 
	onPause(b)) -> onRestart(a)) -> onStart(a)) -> onResume(a)) -> onStop(b)) -> onDestory(b)


#### Activity启动流程中，一些类的关系 ：

* ActivityManagerNative.getDefault() -> AMS本地代理对象，一个单例模式

* IApplicationThread也是一个Binder对象，它是ActivityThread中ApplicationThread的Binder client端

* 每个应用进程对应着一个Instrumentation和一个ActivityThread，Instrumentation就是具体操作Activity回调其生命周期方法的

#### 启动流程

1. Activity的启动流程涉及到多个进程之间的通讯这里主要是ActivityThread与ActivityManagerService之间的通讯
2. ActivityThread向ActivityManagerService传递进程间消息通过ActivityManagerNative，ActivityManagerService向ActivityThread进程间传递消息通过IApplicationThread
3. ActivityManagerService接收到应用进程创建Activity的请求之后会执行初始化操作，解析启动模式，保存请求信息等一系列操作。
4. ActivityManagerService保存完请求信息之后会将当前系统栈顶的Activity执行onPause操作，并且IApplication进程间通讯告诉应用程序继承执行当前栈顶的Activity的onPause方法；
5. ActivityThread接收到SystemServer的消息之后会统一交个自身定义的Handler对象处理分发；
6. ActivityThread执行完栈顶的Activity的onPause方法之后会通过ActivityManagerNative执行进程间通讯告诉ActivityManagerService，栈顶Actiity已经执行完成onPause方法，继续执行后续操作；
7. ActivityManagerService会继续执行启动Activity的逻辑，这时候会判断需要启动的Activity所属的应用进程是否已经启动，若没有启动则首先会启动这个Activity的应用程序进程；
8. ActivityManagerService会通过socket与Zygote继承通讯，并告知Zygote进程fork出一个新的应用程序进程，然后执行ActivityThread的main方法；
9. 在ActivityThead.main方法中执行初始化操作，初始化主线程异步消息，然后通知ActivityManagerService执行进程初始化操作；
10. ActivityManagerService会在执行初始化操作的同时检测当前进程是否有需要创建的Activity对象，若有的话，则执行创建操作；
11. ActivityManagerService将执行创建Activity的通知告知ActivityThread，然后通过反射机制创建出Activity对象，并执行Activity的onCreate方法，onStart方法，onResume方法；
12. ActivityThread执行完成onResume方法之后告知ActivityManagerService onResume执行完成，开始执行栈顶Activity的onStop方法；
13. ActivityManagerService开始执行栈顶的onStop方法并告知ActivityThread；
14. ActivityThread执行真正的onStop方法；

### Activity调用finish的销毁流程
* Activity A 启动 Activity B : 
	* onPause(a) --> onCreate(b) --> onStart(b) --> onResume(b) --> onStop(a) 
* Activity B 调用 finish : 
	* onPause(b)) -> onRestart(a)) -> onStart(a)) -> onResume(a)) -> onStop(b)) -> onDestory(b)


### Context创建流程
1. 应用进程启动 --> 创建Instrumentation --> 创建Application对象 --> 创建Application相关的ContextImpl对象；
2. ActivityThread.main方法—> ActivityManagerService.bindApplication方法 --> ActivityThread.handleBindApplication --> 创建Instrumentation，创建Application；
3. 每个应用进程对应一个Instrumentation，对应一个Application；
4. Instrumentation与Application都是通过java反射机制创建；
5. Application创建过程中会同时创建一个ContextImpl对象，并建立关联；


### Activity布局加载流程

#### Activity生命周期中不调用supper会有什么问题吗？
* 会爆SuperNotCalledException，主要是一个mCalled成员变量

* 一个Activity对应着一个PhoneWindow对象

* Activity的展示界面的特性是通过Window对象来控制的；

* 每个Activity对象都对应这个一个Window对象，并且Window对象的初始化在启动Activity的时候完成，在执行Activity的onCreate方法之前；

* 每个Window对象内部都存在一个FrameLayout类型的mDector对象，它是Acitivty界面的root view；

* Activity中的window对象的实例是PhoneWindow对象，PhoneWindow对象中的几个成员变量mDector，mContentRoot，mContentParent都是View组件，它们的关系是：mDector --> mContentRoot --> mContentParent --> 自定义layoutView

* LayoutInflater.inflate主要用于将布局文件加载到内存View组件中，也可以设定加载到某一个父组件中；

* 典型的Activity的onCreate方法中需要调用super.onCreate方法和setContentView方法，若不调用super.onCreate方法，执行启动该Activity的逻辑会报错，若不执行setContentView的方法，该Activity只会显示一个空页面。


### Activity布局加载流程
* Activity执行onResume之后再ActivityThread中执行Activity的makeVisible方法。

* View的绘制流程包含了测量大小，测量位置，绘制三个流程；

* Activty的界面绘制是从mDector即根View开始的，也就是从mDector的测量大小，测量位置，绘制三个流程；

* View体系的绘制流程是从ViewRootImpl的performTraversals方法开始的；

* View的测量大小流程:performMeasure --> measure --> onMeasure等方法;

* View的测量位置流程：performLayout --> layout --> onLayout等方法；

* View的绘制流程：onDraw等方法；

* View组件的绘制流程会在onMeasure,onLayout以及onDraw方法中执行分发逻辑，也就是在onMeasure同时执行子View的测量大小逻辑，在onLayout中同时执行子View的测量位置逻辑，在onDraw中同时执行子View的绘制逻辑；

* Activity中都对应这个一个Window对象，而每一个Window对象都对应着一个新的WindowManager对象（WindowManagerImpl实例）；

### MeasureSpec
1. EXACTLY
2. AT_MOST
3. UNSPECIFIED

* 首先绘制ViewGroup，再去绘制子View
* 通过遍历的方式去绘制的子view。并且measureSpec是向下传递的

### onSaveInstanceState的执行时机

* onSaveInstanceState方法是Activity的生命周期方法，主要用于在Activity销毁时保存一些信息。

* 当Activity只执行onPause方法时（Activity a打开一个透明Activity b）这时候如果App设置的targetVersion大于android3.0则不会执行onSaveInstanceState方法。

* 当Activity执行onStop方法时，通过分析源码我们知道调用onSaveInstanceState的方法直接传值为true，所以都会执行onSaveInstanceState方法。

### onLowMemory的执行流程
* 系统在JNI层会时时检测内存变量，当内存过低时会通过kiilbackground的方法清理后台进程。

* 经过层层的调用过程最终会执行Activity、Service、ContentProvider、Application的onLowMemory方法。

* 可以在组件的onLowMemory方法中执行一些清理资源的操作，释放内存防止进程被杀死。


## Android Java一些基础
### 线程通信
> 共享内存(JMMjava内存模型, volatile关键字) 和 发送消息(handler)


* 共享内存 : 线程之间通过读写内存中的公共状态来隐式通信
* 消息传递 : 在消息传递的并发模型里, 线程通过明确的发送消息来显示进行通信


### Http相关的面试题

* 请求方法 ：
	* get - 获取资源，对服务器数据不修改，不发送body
	* post - 增加/修改资源，发送内容会放在body里面
	* put - 修改资源，
	* delete - 删除资源
	* head - 获取资源， 返回值没有body

* header里面的参数
	* content-type ： 三种类型：本文、文件、表单
	* content-length：长度
	* user-agent：浏览器/手机
	* range/accept-range：断点续传
	* cookie：
	* authorization：授权
	* accept-charset/encoding：客户端接收字符集
	* content-encoding：客户端接受编码格式
	* cache相关：
	* last- modify

### Tcp/Ip七层协议模型
* 应用层 - http/ftp/dns
* 表示层 - 收到数据翻译成机器语言发送给下一层
* 会话层 - 
* 传输层 - tcp三次握手/四次挥手，udp协议
* 网络层 - ip地址
* 数据链路层 - 以太网、Wi-Fi
* 物理层 - 网络硬件进行传输


### 三次握手、四次挥手过程理解

* 三次握手
	* 第一次握手 - client发送syn报文，等待服务器确认
	* 第二次握手 - server发送syn+ack包，进入recev状态
	* 第三次握手 - client收到syn+ack包，向server发送ack包确认。
	* 就此，双方进入建立连接状态

* 四次挥手
	* 第一次挥手 - client发送fin释放报文，
	* 第二次挥手 - server收到fin报文，发送ack报文，并进入关闭close-wait等待状态
	* 第三次挥手 - client收到ack报文，进入fin-wait终止等待，等待服务器发送fin释放报文
	* 第四次挥手 - server发送fin释放报文，client收到并，发出ack报文/进入time-wait，经过2*msl（最长报文段寿命）释放链接。 server只要收到ack报文，就立刻clsoe链接了

* 为什么是三次握手、四次挥手？
	* 因为server收到fin不会立刻关闭，先回复ack报文，等待所有报文都发送完了，才会发送fin报文

### TCP和UDP的区别
* TCP ： 面向连接、面向字节流、保证数据顺序、正确性，连接稳定，网页浏览、接口访问都是tcp协议传输
* UDP ：面向无连接、基于数据报、可能丢包、数据传输很快，适用于直播、游戏

### TCP与HTTP

#### 一个tcp连接是否会在http请求完成后断开 ?
* 默认不会断开，只有在http请求写明connection:close才会断开

#### 一个tcp连接可以对应多少个http请求？
* 1个tcp对应多个http请求

#### 一个tcp连接中的http请求可以一起发送吗？
* http1.1协议，不能，一个tcp连接顺序进行http请求处理
* http2.0协议，可以，multiplexing

#### http1.1如果提升加载效率？
1. 和多个服务器建立tcp连接
2. 在同一连接上顺序处理多个请求

#### 同一个host的tcp连接有没有数量限制？
1. Chrome最多6个连接，取决于浏览器
2. 如果是https在同一个域名下，会写上用http2的协议，multiplexing

### HashMap相关面试题


#### 1.HashMap简介
* HashMap - 单链表数组，key值可以为null，非有序（LinkedHashMap）、非同步（ConcurrentHashMap）

#### 2.HashMap什么时候扩容
* resize的条件是 = 初始容量 * 扩容因子

#### 3.HashMap的put原理
* A）先判断是否需要resize
* B）计算出存储位置table[i]
* C）判断hash值是否一致，如果一致做replace操作
* D）如果不一致，则出现了hash冲突，插入单链表到链表最后位置，单链表长度超过8就转化为红黑树

#### 4.HashMap的get原理
* a）计算出hash值
* B）计算出table[I]的值，判断存储位置是否有元素存在
* C）比较table[i]头节点的hash值是否一致，否则就按照红黑树/链表的方式进行遍历

#### 5.Hash冲突时什么鬼？怎么解决的？
* Hash值取模的时候，会出现冲突，比如说，元 素 A 的 h a s h 值 为 9 ，元 素 B 的 h a s h 值 为 1 7 。哈 希 表 N o d e < K , V > [ ] table 的长度为 8， 取模后都是1

* 解决方式：拉链法， 就是单链表插入最后一位。

#### 6.HashMap的容量为什么一定是2的n次方？
* 在我们自己初始化设定HashMap大小的时候，无论设置为多少，hashmap会将容量大小设置成最接近2的n次方的数

* 为什么这么做呢？
	* 取模的运算消耗很大，位运算效率高，并且hash冲突会比较小。

#### 7.负载因子为什么时0.75

* 负载因子越大，表示装载程度越高，能容量更多的元素，因此出现hash碰撞的几率会增大，链表会越拉越长，查询效率就会降低

* 负载因子越小，表示越稀疏，会造成空间的浪费，但是此时查询效率最高。


#### 8.HashMap和HashTable的区别
* A）HashMap允许null值，HashTable则不允许null，会报空指针异常
* b）hashmap初始化16，hashmap取2倍，容量一定是2的n次方；hashtable初始化11，扩容2倍+1
* C）计算存储方式也就是table【i】那个i的取值不一样；hashmap是hash值与数组长度取模；hastable不是模运算，取余数
* d）线程安全，但是hashtable不如concurrenthashmap

#### 9.为什么hashmap多线程不安全
* A）多线程同时put、remove操作，都会导致modcount改变，当modcount != exceptedmodcount 的时候，就会抛出异常
* B）多线程，resize扩容的时候，也可以发生死循环

#### 10.hashmap的key值用不变的数据类型，string int等

### JMM内存模型
* 乱序 优化可以保证在单线程下该执行结果与顺序执行的结果是一致的，但不保证程 序中各个语句计算的先后顺序与输入代码中的顺序一致

* 所有变量都存储在主内存 ， 每条线程都有自己的工作内存（保存了该线程使用到的变量的主内存中的共享变量的副本拷贝）

* 主内存拷贝到工作内存8个基本操作（lock、unlock、read、load、use、assign、store、write）

* Java内存交互基本操作的三种特性
	1. 原子性 - 多线程一起执行，一个操作一旦执行、就不会被其他线程干扰
	2. 可见性 - 多个线程访问同一个变量，一个线程修改了变量值，其他线程能够立刻看到修改后的值
	3. 有序性 - 
		* 线程内 - as if serial 串行执行代码
		* 线程间 - 同步代码块和volatile字段操作维持有序

* happens-before
	* 单线程 -  靠前的字节码结果对靠后的字节码可见， 但执行顺序不一定一致
	* 多线程 - 做了同步， a线程的结果对b线程结果可见， a线程执行先于b线程

* 内存屏障
	* 常见有4种内存屏障
	* Load 和 Store的组合 
	* 禁止处理器发生重排序保证有序性
	* 常见的有volatile、synchronized、unsafe来使用内存屏障

### volatile关键字
1. 保证可见性
	* 写 - 1.改变工作内存；2.把工作内存刷新到主内存
	* 读 - 1.主内存刷新到工作内存；2.在改变工作内存
2. 禁止进行指令重排序
	* 保证执行顺序， 指令优化的时候，不能重排序

* 原理 ：
	* 写 : StoreStore（任何读写，在写之前，优先别提交） -> 写 -> storeLoad（写对其他线程可见）
	* 读 : LoadLoad（读取最新值） -> 读 -> LoadStore（写的更新对读可见）

* 64位基础数据结构 double long

### final关键字
* 可见性是指 - 一旦修饰的变量初始化完成，其他线程无需同步，可以正确看见final字段的值。 一旦初始化完成，final变量立刻写回到主内存

### synchronized包裹的代码区域
* 读数据 - 从主内存读取。保证读诗最新的
* 写数据 - 离开代码块，就会把当前线程的数据刷新到主内存中去

### JVM相关

#### 1.ART机制和Dalvik机制

* 在 Dalvik 下，应用每次运行的时候，字节码都需要通过即时编译器(just in time ，JIT)转
换为机器码，这会拖慢应用的运行效率。

* 而在 ART 环境中，应用在第一次安装的时候，字节码就会预先编译成机器码，极大的提高 了程序的运行效率，同时减少了手机的耗电量，使其成为真正的本地应用。这个过程叫做预 编译(AOT,Ahead-Of-Time)。这样的话，应用的启动(首次)和执行都会变得更加快速。

* 缺点 - 包体积更大、安装时间变长

### 2.JVM内存结构
* 线程共享区域 ：
	* 方法区 ：加载的类信息、常量、静态变量
	* 堆 ： 主要存放对象实例和数组。
* 线程私有区域：
	* 虚拟机栈 - 每个方法在执行时，都会创建一个栈帧用用于存储局部变量表，生命周期，是方法执行结束，一个栈帧从虚拟机栈中入栈出栈的过程。这个是位java方法（字节码）提供的服务
	* 本地方法栈 - 虚拟机使用到的native方法服务的。
	* 程序计数器 - 通过改变计数器的值来选取下一个要执行指令的字节码。 不会有OutOfMemoryError

### 对象的创建
#### 垃圾回收算法
* 引用计数法 - 解决不了相互引用的问题
* 可达性分析法 
	* gc root对象 
		1. 方法区中，静态属性引用的对象
		2. 方法区中，常量的引用对象
		3. 虚拟机栈中引用的对象
		4. 本地方法栈中jni引用的对象

### sychronized 和 lock
#### Sychronized 
* 存在层次 - jvm
* 锁的释放 - 执行完同步代码块，释放；线程发生异常，释放；
* 锁的获取 - 一个线程获取到锁，其他线程一直等待；
* 锁的状态 - 无法判断
* 锁类型 - 可重入、不可中断、非公平
* 性能 - 少量同步；

* 原理 
	* 编译成字节码（class文件），sychronized对应的代码块其实就是添加了monitorenter和monitorexit（有两个，一个是正常执行完毕，一个是异常）。 当程序执行到monitorenter和monitorexit的时候，锁计数器会+1，-1。没有获取到锁，就等待。

#### Lock
* 存在层次 - jdk
* 锁的释放 - try - finally
* 锁的获取 - 不用等待，可以尝试获取
* 锁的 状态 - 可以判断
* 锁类型 - 可冲入、可判断，公平、非公平
* 性能 - 大量同步

* 原理 
	* volatile和cas操作实现的

#### Sychronized 1.8以后的优化点 ：
1. 线程自旋和适应性自旋 
	* 设置循环，循环10次后，在挂起
2. 锁消除
	* 不存在线程安全问题的时候，就会把这个同步锁消除
3. 锁粗化
4. 轻量级锁
5. 偏向锁

### Activity启动模式
* Standard : 标准模式，都会创建实例
* SingleTop : 在栈顶就复用
* SingleTask : 栈之上的实例都会被移除掉
* SingleInstance : 单独的栈


### onSavedInstanceState的调用时机

* 未经许可的销毁 - 比如屏幕旋转、语言切换
* onSavedInstanceState(Bundle bundle)通常和 onRestoreInstanceState(Bundle bundle)不会成对出现
* onRestoreInstanceState 这玩意不太好触发，给大家提个好办法，横竖屏切换 的时候100%会触发

### 断点续传
* Java有提供一个类，RandomAccessFile，主要是seek api，记录下载的二进制文件的点，第二次从这里开始io操作

### Standard、SingleTask、SingleTop、SingleInstance
* Standard - 每次都创建新的实例进入task顶部
* SingleTop - 如果再栈顶，就调用onNewIntent（）
* SingleTask - 栈中，在它之上的都会被弹出；
* SingleInstance - 这个栈中只存在这一个实例，如果已存在这个实例，就调用onNewIntent（）的方法

* 当一个在一个栈中启动（Standard、SingleTop、SingleTask）再去启动 SingleInstance 这么一个堆栈，在启动一个activity。 此时返回？

	* 返回的还是按照之前那个栈的顺序，先入后出去返回。最后才是SingleInstance这个堆栈。

* A启动B的问题 
	* A pause - B create - B Start - B Resume - B Stop

* B finish的问题
	* B Pause - A ReStart - A Start - A Resume - B Stop - B Destroy


### OKhttp

* 线程池队列
	* 用的双端队列ArrayDeque，而没有用LinkedList。
	* ArrayDeque 底层是 数组；
	* LinkedList 底层是 单链表；
	* 在垃圾回收时，使用数组结构的效率要优于链表

* 线程池用的io线程池
	* newCachedThreadPool - 0，max，队列用的是SynchronousQueue（每当有任务添加进来时会立即触发消费，即每次插入操作一定伴随一个移除操作）

* 只会当里面没有任务的时候，才能往里面放任务，当放完之后，只能等它的任务被取走才能放

* 责任链模式

* 拦截器顺序
	* 应用拦截器
	* RetryAndFollowUpInterceptor - 重试和重定向的处理
	* BridgeInterceptor - 主要是http请求头，header参数的填充
	* CacheInterceptor - 负责cache缓存的处理，有缓存就直接返回缓存
	* ConnectInterceptor - 建立tcp连接
	* 网络拦截器
	* CallServerInterceptor - 负责真实的网络io读取response

* 应用拦截器 和 网络拦截器 
	* 应用拦截器 - 处理原始请求， 可以添加自定义header
	* 应用拦截器只会触发一次，网络拦截器，就表示真正进行了一次网络请求。


* 重定向的处理？
	* 主要是实现RedirectInterceptor的拦截器，处理对应300几重定向的逻辑

* 在 Dispatcher 中，默认支持的最大并发连接数是64，每个 host 最多可以有5个并发请求





