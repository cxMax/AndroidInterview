# Android framework相关面试题
> 我就简单记一些每个问题的回答关键点，然后具体回答在草稿纸上演练


1. 手机开机启动流程 ✅
	* 1) c++ -> Zygote#main 
		* a)  资源加载 （资源文件，Java，so）
		* b) SystemServer#main
			* 三类服务的启动
				* 引导服务（ams，pms）
				* 必须服务（电池，usagestate）
				* 其他服务 （蓝牙）
		* c) ZygoteSocket创建，并等待请求链接（创建进程）
	* 2) AMS#启动lancher

2. 系统launcher启动流程 ✅
> 跟普通应用进程启动流程大致一致，多了一步去检测launcher入口

	* 1) Manifest检索 -> PMS检索Intent配置为Home、Default的标记的HomeActivity（这个就是launcher_activity）
	* 2) ActivityStack栈的启动 -> ActiivtyStarter根据launch_mode和flag计算出activity，并加入到ActivityStack
	* 3) AMS会pause栈顶的Activity -> 对应是ActivityStack去resumeTopActivityInnerLock
	* 4）若进程已启动
		* ActivityStackSuperVisor就直接启动Activity
	* 5）若进程没有启动
		* AMS#stackProcess -> Process#start -> ZygoteProcess#start 通过socket将要创建的进程信息发送出去
		* zygote fork出进程后，最终会调用ActivityThread#main函数


3.  ActivityThread是干嘛的？
> 进程入口类

	* 内部参数
		* ArrayMap保存的四大组件（Activity、Service、ContentProvider）
		* BroadcastReceiver创建
		* mH 主线程Handler
		* 初始化主线程Looper
		* IApplicationThread，本地stub实现，就是在ActivityThread完成的
		* Instrumentation - ActivityThread的工具类，创建application、activity，

4. Instrumentation是干嘛的？
> ActivityThread的工具类，创建application、activity，启动或者调用activity生命周期事件


5. LoadedApk是干嘛的？
> 保存apk相关信息（路径、ClassLoaded），ClassLoaded真正去反射创建application

6. binder线程

	* binder主线程 : spawnPooledThread(boolean) 通过传递true -> binder主线程，命名binder_1	* binder子线程 : spawnPooledThread(boolean) 通过传递false -> binder子线程，命名biinder_x
	* binder其他线程 : 现有线程，直接加入线程池 IPC.joinThreadPool 

7. Context几种类型
	* Activity : <- ContextThemeWrapper <- ContextWrapper <- Context
	* Service : <- ContextWrapper <- Context
	* Application : <- ContextWrapper <- Context

8. Application初始化
> 跟应用启动逻辑有关，只是其中一个步骤，正好回忆下

*  AMS#起四大组件 -> Process#start -> ZygoteProcess#start -> ActivityThread#main -> AMS#attach -> IApplicationThread#bindApplication -> mH#handleBindApplication -> Instrumentation#newApplication -> LoadedApk#通过classloader反射进行创建 -> Application对象 -> application#attachBaseContext -> application#onCreate

7. Activity初始化
> 晚点看完activity启动流程再补充


* instrumentation#newActivity反射区创建对象 -> 因为之前已经创建了activity了 -> 创建一个ContextImpl -> 调用activity#attach进行绑定 -> 开始走activity的生命周期

8. Service初始化 
> 晚点看完service初始化流程再补充
> 和activity初始化方式比较类似

* 反射创建service -> 创建ContextImp -> 绑定application和context

9. BroadcastReceiver的context

* 动态注册 : Activity、service宿主的context
* 静态注册 : Application

10. getApplication和getApplicationContext有什么区别？
> 虽然赋值给了不同的成员变量， 但都是同一个Application，指向LoadedApk通过反射创建并缓存的application对象

* api的区别:
	* getApplicationContext是context的抽象方法
	* getApplication在Activity和Service中有

11. 


## 引用
* systemserver启动.pages
*[ 进程的Binder线程池工作过程](http://gityuan.com/2016/10/29/binder-thread-pool/)