# Android View

## View的理解

### activity、window、ViewRootImpl的关系
* 一个activity对应一个window（PhoneWindow）

* 层级关系 :
	* activity -> window（PhoneWindow） -> DecorView -> ContentView（ViewGroup）


### ViewRootImpl是干嘛的呢？
1. activity#onCreate#setContentView，会创建DecorView，此时DecorView还没有和Window（PhoneWindow）关联
2. onResume之后，WindowManagerImpl添加DecorView到PhoneWindow中，并且初始化ViewRootImpl
3. ViewRootImpl会关联DecorView，然后执行ViewRootImpl的performTravelsals开始绘制

### View的事件分发 ?
1. activity#dispatchTouchEvent
2. PhoneWIndow#superDispatchTouchEvent
3. DecorView#superDispatchTouchEvent
4. ContextView（ViewGroup）#dispatchTouchEvent

### View的绘制流程 ？
1. 还是在ActivityThread::handleResumeActivity中处理的
2. WindowManagerGlobal::addView
3. 实例化ViewRootImpl，并且调用ViewRootImpl::setView，decorview和ViewRootImpl相互绑定
4. checkThread，这里会抛主线程更新UI的exception
5. 注册Choreographer监听，也就是16ms刷新一次的监听，并且每次监听都会执行performTraversals.
6. performTraversals是干嘛的呢？主要是开始进行view的三大流程performMeasure、performLayout、performDraw

## WIndow机制

* WindowManagerService的binder接口是IWindowSession
* WindowManager是一个interface，继承于ViewMananger，具体实现是WindowManangerImpl，但真正的实现是单例类WindowManagerGlobal
* ViewRootImpl的创建在WindowManagerGlobal这里
* ViewRootImpl与WMS同新的binder接口是IWindowSession，创建则也在WindowManagerGlobal这里

### PhoneWindow是干嘛的？

* WindowManagerImpl管理着PhoneWIndow，
* PhoneWindow内部持有DecorView，而DecorView持有的ViewRootImpl
* ViewRootImpl通过IWindowSession和WMS进行交互

### PopupWindow和Dialog从Window的角度看区别?
> 结论就是，先添加dialog，后添加PopupWindow，但PopupWindow仍显示在dialog之下
* PopupWindow创建DecorView通过WindowManager#addView进行添加
* Dialog则会同activity一样，在创建出PhoneWindow，


### ViewRootImpl是干嘛的？

* 通过IWindowSession将window添加到WindowManagerService
* 页面View的根节点，关联window和view，也是DecorView的父节点
* Choreographer接收vSync信号，触发view的三大流程（performTraversals - performMeasure、performLayout、performDraw）
* WindowInputEventReceiver接收屏幕输入事件，分发手势， 手势分发的发源地
* 接收Vsync同步信号，触发View的动画重绘



## View事件分发

### View事件分发流程

* 首先事件是由InputManangerService发出的，有几个类型的事件(屏幕点击事件、键盘点击事件)等等
	* 系统里面通过一个责任链模式来进行分发
* InputManagerService 通过WMS找到要分发的Window，进行分发
* 因为ViewRootImpl有IWindowSesstion可以与WMS进行通信
* 在ViewRootImpl内部回去注册一个叫InputEventReceiver，用于事件的分发
* 这里就到了，事件的分发流程了 :
	* ViewRootImpl收到事件 
	* -> 顶层ViewGroup(一般是FrameLayout) 
	* -> DecorView:: dispatchTouchEvent 
	* -> Activity 
	* -> PhoneWindow（内部持有DecorView对象） 
	* -> 又交给DecorView 
	* -> ContentView（ViewGroup）
	* -> 子View一层一层往下传递

### View事件拦截传递调用链
> 注意接口setonTouchListener的触发时机在onTouchEvent之前

* dispatchTouchEvent -> onInterceptTouchEvent -> onTouchListener  -> onTouchEvent


## View绘制

### OnMeasure、OnLayout、OnDraw
* onmeasure、ondraw、onlayout在ViewRootImpl里面都是发送的异步消息，优先执行
* ViewRootImpl里面通过performMeasure、performLayout、performDraw等函数，view至顶向下开始测量

### onCreate、onResume中为什么获取不到View的宽高
* 还没初始化ViewRootImpl, 还没进行view的三大流程，所以获取不到

### onCreate中使用view.post为什么可以获取到宽高
* View.post内部调用的AttachInfo::mHandler.post
* 因为Attachinfo的初始化是在ViewRootImpl里面初始化，并且双向绑定view和ViewRootImpl的， 因此就表明ViewRootImpl已经执行了measure等工作， 所以能获取到宽高
* 如果没初始化完，他会加到一个队列里面，初始化完毕后，会遍历队列，执行runnable

### 子线程更新UI真的不行吗？
* ViewRootImpl::requestLayout（measure和layout工作） 这个函数会去检查是否在主线程，否者会抛出异常

* 可以更新，只要没涉及到requestLayout()和invalidate()这两个函数，就没有问题。 最主要其实就是可以在oncreate的时候，子线程去setui

* requestLayout这里还有个知识点，handler发送的消息屏障类型， 主要是为了尽早的进入measure、layout、draw 这三大流程


### Android UI刷新机制
* choreographer::mDisplayReceiver这个类注册native vsync信号回调的类
* vsync消息队列callbackqueue模型 - 是个数组
* 有几种类型：callback_input、callback_animation、callback_traversal、callback_commit

* 原理：
	1. 通过注册mDisplayReceiver vsync回调
	2. 接收到native发过来的vsync信号，doframe方法
	3. doframe里面就开始do几种类型的callbackqueue，最先是input，后面才是animation，travsersal


### Android UI刷新机制


* 丢帧原因一般是什么引起的？
	* 主线程耗时操作

* 16ms调用onDraw一次？
	* vsync信号16ms， 不一定每次都会绘制，需要应用端发起重绘

* onDraw之后屏幕会马上刷新吗？
	* 下次vsync信号来了才刷新，绘制完毕不会立刻刷新。如果信号来了，还没绘制完毕，则再次信号来了才刷新

* 如果界面没有重绘，还会每隔16ms刷新屏幕吗？
	* 用的是缓存数据， 不会

* 如果屏幕快要刷新的时候才去onDraw绘制会丢帧吗？
	* 这个都没关系的，发起重绘，只是添加到消息队列里面， vsync信号来了， 才去处理这个重绘消息

### Android UI线程是怎么启动的？

* 什么是UI线程？
	1. UI线程就是刷新UI所在的线程
	2. 是单线程刷新、并非多线程

* UI线程是主线程吗？
	1. Activity.runOnUiThread - 主线程创建UIthread和handler，结论就是对于activity来说，UI线程就是主线程
	2. View.post - ViewRootImpl创造适合所在线程的handler去处理的，
		* 这里有个细节点，就是onresume 和 oncreate， ViewRootImpl是在onresume之后初始化的，这里有个区别
	* ViewRootImpl是在主线程创建的

* UI线程就是ViewRootImpl创建的时候所在的线程
	* 因为Decorview、ViewRootImpl恰好都在主线程创建的 -> ActivityThread::handleResume
	* widnowManger.addView的时候初始化ViewRootImpl。

* 所以刷新UI，也可以在子线程里面做。
	* 前提是ViewRootImpl初始化 和 刷新UI要保持一致， 主要是requeslayout有个checkthread

### UI线程的启动流程？消息循环事怎么创建的
1. zygote fork进程（ZygoteProcess#fork）
2. 启动binder线程 
3. 执行activitythread::main 入口函数 looper::prepareMainLooper , looper::loop









