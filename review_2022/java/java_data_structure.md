# 数据结构

## 数组
* 数组是存放在连续内存空间上的相同类型数据的集合。
	* 数组下标都是从0开始的。
	* 数组内存空间的地址是连续的
* 因为数组的在内存空间的地址是连续的，所以我们在删除或者增添元素的时候，就难免要移动其他元素的地址。
数组的元素是不能删的，只能覆盖。

* 那么二维数组在内存的空间地址是连续的么？
	* 所以可以看出在C++中二维数组在地址空间上是连续的
	* Java是没有指针的，同时也不对程序员暴漏其元素的地址，寻址操作完全交给虚拟机


* 数组
	1. 内存中连续，因为是静态分配内存。
	2. 数组的缺点是，当数组长度发生变化时，原有的元素需要经过copy到新的数组中，这样性能有较大的损耗

## 链表
* 链表几种类型 ：单链表、双链表、循环链表
* 链表的存储方式：数组是在内存中是连续分布的，但是链表在内存中可不是连续分布的。

* 链表
	1. 内存中不连续，他是动态分配内存。

## Java Stack缺陷
1. 当数组默认的容量发生改变时,原有的元素需要经过copy到新的数组中,因此pop、push的性能会有较大降低
2. 继承的是vector这个类，有add等方法，破坏了stack的api
	* 解决方案: <br>官方推荐使用 Deque(双端队列) 代替 Stack， 有链表和数组分别实现的。
		* 推荐使用LinkedList，单链表实现的栈，内存会占用更少

## 哈希表
> 哈希表一般用来快速判断一个元素是否出现在集合里


### 出现hash碰撞的情况 ？
* 对象的个数大于hash表的个数，此时计算hash函数计算得再均匀，也避免不了几个对象同时映射到一个索引下


### hashtable出现hash碰撞， 怎么来解决呢？
* 拉链法 - <br>发生冲突的元素，被存储在链表中；这个也是HashMap的做法


* 线性探测法 - <br>一定要保证tableSize大于dataSize。 我们需要依靠哈希表中的空位来解决碰撞问题；一般是+1，而且数据结构是数组的形式

### 常见的三种哈希结构
* 数组 - <br>可以拿数组当哈希表来用，但哈希值不要太大。使用数组来做哈希的题目，是因为题目都限制了数值的大小。而且如果哈希值比较少、特别分散、跨度非常大，使用数组就造成空间的极大浪费。


* set - <br>直接使用set 不仅占用空间比数组大，而且速度要比数组慢，set把数值映射到key上都要做hash计算的 , 输出结果中的每个元素一定是唯一的，也就是说输出的结果的去重的， 同时可以不考虑输出结果的顺序

当我们遇到了要快速判断一个元素是否出现集合里的时候，就要考虑哈希法了

* map - <br>判断是否出现、重复，就用hash表来解决， 这个map 主要是适应无序的场景， 有序肯定就要考虑数组了
使用map的空间消耗要比数组大一些的，因为map要维护红黑树或者哈希表，而且还要做哈希函数，是费时的！数据量大的话就能体现出来差别了。 所以数组更加简单直接有效！

## 二叉树

### 满二叉树
* 顾名思义，就是每一个层的结点数都达到最大值，则这个二叉树就是满二叉树。2^n -1个节点

### 完全二叉树 

* 在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置

* 之前我们刚刚讲过优先级队列其实是一个堆，堆就是一棵完全二叉树，同时保证父子节点的顺序关系。

### 二叉搜索树 
1. 有序树
2. 左节点<根节点<右节点
* 那么二叉搜索树采用中序遍历，其实就是一个有序数组。下二叉搜索树可是有序的

### 平衡二叉搜索树
* 左右节点高度差不能超过1


## 堆
> 堆是一颗完全二叉树，树中每个节点的值都不小于（或不大于）其左右孩子的值。

### 大顶堆 
* 堆头是最大元素

### 小顶堆 
* 堆头是最小元素

## List

### ArrayList

#### 基本原理
* object[] 数组，内存中是连续的
* 默认容量为10，初次add的时候，初始化
* 扩容的时候是1.5倍，System.arraycopy的方式进行扩容
* 数组会申请内存空间，即使你没用到，也会申请一个内存地址，会有内存地址浪费的问题
* object[] 数组使用transient关键字修饰，重写了wirteObject方法，避免内存空间浪费，只对数组内有数据的对象进行序列化
* 遍历效率 : get > foreach > iterator
* Arrays.sort原理是用的快排

#### 优缺点
* 查找效率更高
* 遍历效率低
* 添加删除元素，需要移动元素，效率低（因为数组中元素不能删除，需要进行覆盖）
* 扩容时，使用的数组拷贝，性能开销大
* Collections.synchronizedList 实现线程安全，通过代理模式，内部持有真正的list，synchronized进行所有操作锁对象的方式实现，推荐使用CopyOnWriteArrayList

### LinkedList

#### 基本原理
* 双向链表，动态分配内存，内存地址不连续
* 也实现了queue的接口，双端队列，可以用作stack

#### 优缺点
* 添加删除元素，效率高
* 查询效率低，每次查找都要从头节点开始往后查找
* 遍历效率，removeFist()或removeLast()的效率最高, 但读取数值的话 还是用foreach遍历

### LinkedList和ArrayList在实际开发中内存占用
* 一般情况下，LinkedList的占用空间更大，因为每个节点要维护指向前后地址的两个节点，但也不是绝对，如果刚好数据量超过ArrayList默认的临时值时，ArrayList占用的空间也是不小的，因为扩容的原因会浪费将近原来数组一半的容量，不过，因为ArrayList的数组变量是用transient关键字修饰的，如果集合本身需要做序列化操作的话，ArrayList这部分多余的空间不会被序列化

### Vector
> 线程安全，不推荐使用，推荐使用CopyOnWriteArrayList

#### 基本原理
* object[] 数组，但未使用transient关键字，也就意味着，序列化的时候，有更大的内存开销
* 初始容量为10，其他都跟ArrayList一样
* 所有操作都是synchronized关键字
* 遍历 : 多了一个Enumeration遍历

#### Enumeration和Iterator区别
* Iterator
	* 遍历拥有fast-fail机制，多线程操作，会抛异常，ConcurrentModificationException
* Enumeration
	* 不会抛出异常，ConcurrentModificationException

### CopyOnWriteArrayList
> 实现方式和ArrayList基本一致，concurrent包下面的

#### 基本原理
* 修改元素的时候，加锁方式不一样，Collections.synchronizedList和Vector，读、写都锁对象；CopyOnWriteArrayList修改元素加锁，读不加锁。

## Map
### WeakHashMap : 
* 数组 + 单链表，不会转红黑树；Entry是弱音用
* HashMap都是通过”拉链法"实现的散列表

### HashSet/HashMap

#### 基本原理
* 数组 + 单链表 + 红黑树
* 初始容量为16，因子为0.75（达到容量的0.75倍开始扩容），扩容大小为2的次幂
* 单个链表元素超过8，会将单链表转变为红黑树；红黑树节点小于6，会将红黑树转新变为单链表。为了提升检索效率。
* 单个元素查询效率o(1)，红黑树o(logn)
* 重写了hashcode和equals，查询效率最快，key通过hash算法，直接得到数组下标；在通过equals对比key，拿到value
* hash冲突就是指，不同key通过hash算法（模运算）指向同一个下标
* 拉链法是指，指向链表最后一个位置
* 死循环情况
	* HashMap在并发使用场景发生死循环 或 数据丢失。主要发生场景实在扩容的时候， 产生循环链表，出发死循环 
* key，value支持null

#### 红黑树是什么？
* 相对接近平衡的二叉树
* 每个节点要么红色，要么是黑色
* 根节点一定是黑色
* 每个空叶子节点必须是黑色
* 如果一个节点是红色的，那么它的子节点必须是黑色
* 从一个节点到该节点的子孙节点的所有路径包含相同个数的黑色节点

#### HashMap是先扩容？还是先转红黑树？
> 答案：先扩容，再转红黑树

* 扩容：resize
* 转红黑树：treeifyBin

#### HashMap扩容算法
* 新的地址 = 原长度 + 原位置

#### HashSet如何保证数据不重复？
* ashSet的add()函数调用了HashMap的put, 先比较hashCode, 在比较equals函数, 因此一个Object需要重写hashCode和equals这两个函数

### LinkedHashMap
> LruCache的实现

#### 基本原理
* entry和map都继承与HashMap
* 不一样的是，双链表，增加两个node，分别指向前、后
* 因此保证了有序，输入和输出顺序相同

### ConcurrentHashMap

#### 基本原理
* 数组 + 单链表 + 转红黑树
* CAS + Volatile关键字来做的

### TreeSet/TreeMap

#### 基本原理
* 红黑树实现
* 实现排序的两种方式
	* 通过实现构造函数compator接口，实现排序
	* 通过对象实现comparable接口，实现排序

#### 线程安全的实现两种方式
* ConcurrentSkipListSet,这个东西是通过ConcurrentSkipListMap这个来实现的, 其内部实现是跳表, 非红黑树(ConcurrentHashMap)
* Collections.synchronizedSortedSet, 所有这个类似api的实现方式都是底层SynchronizedCollection这个来实现的

### HashTable

#### 基本原理
* 数组 + 单链表，不会转红黑树
* synchronized锁对象，读、写都加锁
* hash算法就仅仅是hashcode
* put不支持null


## Android特有的数据结构

### ArrayMap
> 总体来讲，内存使用更优（相比于HashMap），数据小，使用场景比较高频
> 如果key值，是int类型，推荐使用SparseArray，内存开销缩小1/3

#### 原理
* 数据结构使用的数组
	* mHashes是一个记录所有key的hashcode值组成的数组，是从小到大的排序方式； [key1.hash, key2.hash]
	* mArray是一个记录着key-value键值对所组成的数组，是mHashes大小的2倍； [key1, value1, key2, value2]
* 缓存机制（二级缓存，内部缓存2个ArrayMap对象）
	* 容量大小4和8，为了减少频繁地创建和回
* 扩容机制
	* 按照4、8、1.5倍容量，来扩容的
	* remove和clear时，也会有收紧策略，对应也是4、8、1.5倍容量
	* 扩容的处理方式，还是数组拷贝
* 查找采用的二分查找
* 就直接使用的hashcode，出现hash冲突的时候，采用线性探测法，往前往后查找

### ArraySet
> 和ArrayMap几乎一致
 
* 不一致的地方 : 两个数组，一个存value，另一个存key

### SparseArray
* 数据结构使用的也是数组
	* 一个保存key，一个保存value。 没有保存hash值，因此缩小了1/3

## ArrayMap、HashMap、SparseArray比较

* 数据结构
	* 数组 : ArrayMap，SparseArray
	* 数组 + 单链表 + 红黑树 : HashMap
* 内存 :
	* SparseArray > ArrayMap > HashMap
	* 容量的利用率比ArrayMap低，整体更消耗内存
* 性能方面 :
	* HashMap > SparseArray > ArrayMap
	* ArrayMap添加删除涉及到数组移动

* 缓存机制 :
	* ArrayMap 4、8，最大容量10，频繁创建对象分配内存和gc
	* HashMap没有缓存
	* SparseArray有延迟回收机制，提供删除效率，同时减少数组成员来回拷贝的次数
* 扩容 :
	* ArrayMap 4、8、1.5倍
	* 0.75 * capacity时，触发扩容，2的幂次方
* 并发 :
	*  都不支持多线程


## 引用
[当面试官问我ArrayList和LinkedList哪个更占空间时，我这么答让他眼前一亮](https://zhuanlan.zhihu.com/p/166686856)
[深度解读ArrayMap优势与缺陷](http://gityuan.com/2019/01/13/arraymap/)
[ArrayMap的hash碰撞和内存优化的原理](http://gaozhipeng.me/posts/arraymap/)
