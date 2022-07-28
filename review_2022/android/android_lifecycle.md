# android 生命周期

## activity生命周期相关

### activity生命周期详解
* onCreate
* onAttachFragment
* onContentChanged
* onStart (onRestart)
* onActiityResult
* onRestoreInstanceState
* (onPostCreate)
* onResume
* onAttachToWindow
* onCreateOptionMenu
* onPrepareOptionMenu
* onUserInteration
* onUserLeaveHint
* onPause
* onSaveInstanceState
* onStop
* onDestroy

### onNewIntent生命周期调用时机
> onNewIntent生命周期跟是否在栈顶，会有区别

#### SingleTop、SingleTask位于栈顶
* activity若位于栈顶，则直接复用
	* 从onPasue恢复，onPause -> onNewIntent -> onResume

#### SingleTaskwe位于栈中
* activity若位于栈中，
	* 从onStop恢复，onStop -> onNewIntent -> onRestart -> onStart -> onResume

### activity生命周期流转
> 这道题，满分回答， 还要加上launch_mode去区分，以下只是standard模式下的activity，launch_mode如上道题

#### A启动B
* A : 
	* onResume-> onPause
* B :
	* onCreate -> onStart -> onResume
* A : 
	* onStop -> onDestroy

#### B点了back，返回A
* B :
	* onResume -> onPause
* A :
	* onRestart -> onStart -> onResume
* B :
	* onStop -> onDestroy 

#### 按Home键，（其实也就是切换至后台）

* onPause -> onStop

#### 在切换至前台
* onRestart -> onStart -> onResume

#### finish销毁
* onPause -> onStop -> onDestroy


### onSaveInstanceState和onRestoreInstanceState调用时机
> 主要还是activity发生重建，比如说 : 屏幕旋转，多语言切换

* 屏幕旋转，生命周期的流转 :
	* onPause -> onSaveInstanceState -> onStop -> onDestroy -> onCreate -> onStart -> onRestoreInstanceState -> onResume

	* onSaveInstanceState
		* 在onPause和onStop之间调用
	 * onRestoreInstanceState
		* 在onStart和onResume之间调用


## Fragment生命周期相关

### Fragment生命周期详解
* onInflate
* onAttach
* onCreate
* onCreateView
* onViewCreated
* onActivityCreated
* onViewStateRestored
* onStart
* onResume
* onCreateOptionMenu
* onPrepareOptionMenu
* onPause
* onSaveInstanceState
* onStop
* onDestroyView
* onDestroy
* onDetach

## View生命周期相关



## 引用
[Android activity onNewIntent 调用时机](http://baurine.github.io/2015/12/26/android_onnewintent.html)
[Activity状态图、生命周期图（超详细）](https://www.cnblogs.com/sjjg/p/5874644.html)
[浅析onRestoreInstanceState调用时机](https://www.jianshu.com/p/f50b1ee509e6)