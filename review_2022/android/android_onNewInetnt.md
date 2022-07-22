## android onNewIntent相关

### 生命周期
#### SingleTop
从onPasue恢复，onPause -> onNewIntent -> onResum

### SingleTask和SingleInstance
从onStop恢复，onStop -> onNewIntent -> onRestart -> onStart -> onResume


