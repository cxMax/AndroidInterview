# android架构领悟

## mvc
* Model - 数据获取
* View - UI，xml或者view对象，activity/fragment
* Controller - 全部耦合在activity/fragment

* 缺点 ：
	*  全部耦合到activity/fragment，非常臃肿

## mvp
* Model - 数据获取
* View - UI，xml或者view对象，activity/fragment
* precenter - 分别持有View层和Model层，使得View和Model相互隔离。处理View于Model间的交互和业务逻辑

* 缺点 : 
	* 用户产生的界面交互 - 单向趋动数据刷新
	* P层会越来越臃肿， 多模块， 公共业务难解耦

## mvvm
* Model - 数据获取
* View - UI，xml或者view对象，activity/fragment
* ViewModel - 双向绑定
* 好处 : 
	* 由单向用户交互 驱动界面刷新
	* 变为双向绑定， 即用户可以通过交互刷新界面，并且界面可以监听数据状态，刷新界面

### 三个阶段

#### 第一阶段
> 2016年左右， viewmodel， databinding的使用， rxjava + retrofit + okhttp + databinding

* 最主要的还是用到了databinding
* viewmodel 使用的 databinding#BaseObservable
* 整体架构还是偏mvp，不是独立的mvvm架构

#### 第二阶段
>2018年开始,，配合Jetpack组件相对完善，开始出现， livedata + lifecycle + viewmodel + rxjava + retrofit + okhttp 


* 开始使用到了jetpack组件，解决实际开发中的问题
	* livedata 替换databinding的Observable，实现数据监听
	* 官方lifecycle组件，配合rxjava / lievedata 解决生命周期释放的问题
* 比较规范的mvvm架构，
	* viewmodel，
	* model -> repository -> source

* 缺点 :
	* 会去封装databinding、viewmodel到base，

#### 第三阶段
>2021年左右，配合最新的jetpack组件， flow + Coroutines + lifecycle + hilt2

* 优点 :
	* 主要是简化使用，通过拓展函数，减少封装，主要是ViewModel，databinding
	* 充分利用ViewModel特性，配合ViewModelScope + Coroutines解决生命周期问题，

# Jetpack一些组件的原理

## LiveData
>. 好处，监听了生命周期LifecycleOwner，避免内存泄漏


### 原理
* 观察、订阅模型，
* 持有LifecycleOwner（即activity、fragment），当生命周期再次可见时，会notify

### 数据倒灌

* 产生时机 :
	* activity 界面旋转/多语音切换，导致界面重绘的时候

* 触发原因 : 
	* 监听了界面事件，生命周期方法发生变化，会触发notify，然后又去new了一个wrapper对象，内部在刷新的时候，有个判断，lastVersion和mVersion进行比较， 因为是重新new的一个wrapper对象，因此导致lastversion重置咯， 所以会触发刷新

* 解决方案 :
	* 通过反射的形式，去修改重建后mLastVersion的值，

## ViewModel

### 好处 ：
1. 不会因为屏幕旋转而销毁
2 .在作用域内单一实例特性，多个fragment之间可以方便通信，并且维护同一个数据状态。


### ViewModel的创建

1. 是通过ViewModelProvider传入ViewModelStoreOwner接口的实现（activity、fragment），最终通过反射的形式去初始化对象的。 并且两个工厂类去创建这个对象， 一个是带application的，另一个是不带application的。
2. 是通过一个hashmap在ViewModelStore中存储

### ViewModel的销毁
* activity - 会跟activity lifecycle组件绑定，在ondestory的时候，销毁。
* fragment - 在activity::ondestory的时候，会通过FragmentManager::DispatchDestory传递这个事件，并销毁。


### ViewModel在activity重建的时候，不会被销毁
原因就是 ： onRetainNonConfigurationInstance中会存储ViewModelStore实例，activity重建的时候回去获取getLastNonConfigurationInstance

### 生命周期问题

* 也是通过lifecycle生命组件进行绑定的

## Navigation

### 原理
* 大概源码看了下，没看那么细节：
	1. NavHostFragment作为导航载体
	2. 导航的控制类还是NavController
	3. NavController将导航任务委托给Navigator，他的实现类是FragmentNavigator和ActivityNavigator。
	4. FragmentNavigator和ActivityNavigator这两个类里面具体有个内部类Destination，activity的Destination还是构造的intent，通过startActivity实现跳转。 fragment的Destination构造的是classname，通过FragmentManager进行管理和操作。



## LifeCycle

### 原理
1. 订阅-观察模型
2. 构造了ReprotFragment进行生命周期监听， 跟glide原理是类似的
3. activity和fragment实现了LifecycleOwner接口
4. 通过LifecycleRegistry进行事件分发
5. 订阅者通过实现LifecycleObserver接口实现获取到对应的生命周期事件

# 引用
[Android消息总线的演进之路：用LiveDataBus替代RxBus、EventBus](https://tech.meituan.com/2018/07/26/android-livedatabus.html)











