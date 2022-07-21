# Android 之 Activity Launch Mode

## 基础

* Standard : <br>标准模式，无论栈中是否存在，统一new一个新的实例于栈中
* SingleTop : <br>如果栈顶是该activity，则直接复用，并回调该实例onNewIntent()方法， 否则，在栈中new一个全新的activity，走新建流程。
* SingleTask : <br>如果栈中有该activity，则移动该activity至栈顶，并且移除其置于其上的所有activity实例，回调onNewIntent()方法， 否则，在栈中new一个全新的activity，走新建流程。
* SingleInstance : <br>这种模式启动的Activity独自占用一个Task任务栈，同一时刻系统中只会存在一个实例，已存在的实例被再次启动时，只会唤起原实例，并回调onNewIntent()方法。一般用于聊天界面

## 进阶
* SingleTask : <br>
	* A应用启动B应用的SingleTask，
	* 先判断B的task里面是否已有SingleTask的实例，
	* 如果有，移除其栈顶的所有实例，并回调其中onNewIntent()方法
	* 如果没有，则创建SingleTask的实例
	* 然后再把B整个Task置于A的栈顶；

	* 这个时候用户点back，则先回回退整个B的Task，B Task如果没有activity了，到 A Task的切换，明显是栈的切换，而明显是task之间的切换，而非activity之间的切换

	
	* 类似于上述场景，两个task的叠加，只限于前台task

	* 那前台task和后台task的触发呢？
		*  按home键，叠加的task，就会进行拆开，变成最近一个的task
		*  按最近任务键，查看任务；也会进行拆开，变成最近一个的task

	* 在当前app 的task 全局就只有这个一个对象，
	* 一般会配合taskAffinity一起使用；

* SingleInstance : <br> 
	* 和SingleTask基本一致，
	* 但是它只能要求task里只允许有他一个实例；不允许有其他activity
	* 不需要配置taskAffinity，默认会新建一个task，但是taskAffinity和当前task冲突，多任务查看task的时候，会被隐藏起来。
	* 如果复用的话，还是会走onNewIntent

* SingleTop : <br> 和standard几乎一样，启动时，不考虑taskAffinity；然后就是onNewIntent

### 总结：
* standard和singleTop多用于App内部
* singleInstance : 多用于开发给外部App来共享使用
* singleTask : 内部交互和外部交互都用得上

## activity其他一些属性
* taskAffinity : <br>activity任务栈的名字，默认是包名，<br>当多个Task有相同的taskAffinity时，系统只会在最近任务显示一个Task，并且是最新打开的那个
	* 最近任务列表，主要是按照taskAffinity进行区分和现实的
	* 第一个activity会默认使用app的taskAffinity，但是后面启动的都会默认进入当前task，除了SingleTask，其他设置了taskAffinity都会被忽略掉（主要是standard和singleTop）
	* 直接进入当前的	
	
* allowTaskReparenting属性 : <br> A应用，启动了B应用具有该属性的activity，当task切换，由A task 切换至 B task，那么, 之前启动该属性的activity会回到B task中，并至于栈顶，并且从A task中消失。<br>android 9, 10 失效；android 11 有效；

* clearTaskOnLaunch : <br>这个属性用来清除回退栈中除了根Activity的所有Activity，只对根Activity起作用。当设置为true时，每次重新进入app，只会看到根Activity。

* finishOnTaskLaunch : <br>
这个属性与clearTaskOnLaunch相反，它是将本Activity移除出去，而不影响其他的Activity。也是launch启动的时候

* alwaysRetainTaskState : <br>
这个属性的作用是保存返回栈的状态，只对根Activity起作用。正常情况下，系统清理一个返回栈，会将根Activity之上的所有Activity都清除掉。设置该属性后，系统会保存当前的状态。

## flag
> flag的使用场景，还是有点复杂，说实话

* FLAG_ACTIVITY_NEW_TASK
	* service启动activity需要设置
	* 要根据taskAffinity来看，以下几种情况
		* taskAffinity和当前栈顶的不一致，并且task为null，则创建task，并创建activity实例至于栈顶
		* taskAffinity和当前栈顶的不一致，并且task不为null，则先把该task移动至栈顶，然后再去创建activity实例至于栈顶
		* taskAffinity和当前栈顶的一致，并且task不为null，还要看根activity是不是一样，如果不是一样，直接创建
		*  taskAffinity和当前栈顶的一致，并且task不为null，还要看根activity是不是一样，如果是一样，直接显示，不做任何处理，onNewIntent都不会回调；

* FLAG_ACTIVITY_CLEAR_TASK
	* 和 FLAG_ACTIVITY_NEW_TASK 一起使用， 也就是清理

* FLAG_ACTIVITY_CLEAR_TOP
	* 都会新建activity实例 

* FLAG_ACTIVITY_SINGLE_TOP
	* 这个不会，和singleTop一样，直接onNewIntent了

## 引用
[Android TaskAffinity和allowTaskReparenting属性](https://juejin.cn/post/6844903942866878472)<br>
[浅谈 Android launchMode和taskAffinity](https://juejin.cn/post/7026669198537392165)<br>
[Android 面试黑洞——当我按下 Home 键再切回来，会发生什么？](https://juejin.cn/post/6883741254614515720#heading-2)<br>
[喜闻乐见之Activity的launchMode](https://juejin.cn/post/6844903591145127943)<br>

[Android面试官装逼失败之：Activity的启动模式](https://juejin.cn/post/6844903494470598669#heading-6)<br>




