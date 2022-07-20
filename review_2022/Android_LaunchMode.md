# Android 之 Activity Launch Mode

## 基础

* Standard : 标准模式，无论栈中是否存在，统一new一个新的实例于栈中
* SingleTop : 如果栈顶是该activity，则直接复用，并回调该实例onNewIntent()方法， 否则，在栈中new一个全新的activity，走新建流程。
* SingleTask : 如果栈中有该activity，则移动该activity至栈顶，并且移除其置于其上的所有activity实例，回调onNewIntent()方法
* SingleInstance :
  这种模式启动的Activity独自占用一个Task任务栈，同一时刻系统中只会存在一个实例，已存在的实例被再次启动时，只会唤起原实例，并回调onNewIntent()方法。一般用于聊天界面

## 进阶