# Android日志Java_IO方案性能测试
> Java-Log-IO日志本地储存的方案，开源库: log4j， logback等

## 测试命令
[top_shell.sh]
## 测试代码
 `/**  * 打印10次，每次打印1w条数据  * @param log LOG  */ private fun doPerformanceTest(log: LOG) {     Trace.beginSection("LogUtil_IO")     val currentTimeMillis = System.currentTimeMillis()     for (i in 0..19) {         when (log) {             LOG.LogUtils -> LogUtil.e(TAG, "log-$$str_1k")             LOG.Log4a -> Log4a.e(TAG, "log-$$str_1k")             LOG.Logan -> Logan.w("${TAG}-log-$$str_1k", 1)             LOG.Xlog -> com.tencent.mars.xlog.Log.e(TAG, "log-$$str_1k")         }     }     val ret = String.format(         Locale.getDefault(),         "* %s spend: %d ms\n",         "LogUtil",         System.currentTimeMillis() - currentTimeMillis     )     resultTv.append(ret)     Trace.endSection() }  testBtn.setOnClickListener {     mainViewModel.viewModelScope.launch {         doPerformanceTest(LOG.LogUtils)     } }`


## 结论
> 单条数据1kb

|  日志条数/秒   | 主进程 CPU（ 单位：百分比） |  IO 主线程 CPU（ 单位：百分比）  | Logcat CPU(单位：百分比)  |  内存(单位：MB)  | 
|  ----  | ----  | ----  | ----  | ----  |
| 10 | 28.8593% | 5.765% | 5.2982% | 12 |
| 50 | 49.4533% | 19.16% | 9.0819% | 10 |
| 100 | 63.3941% | 25.7059% | 7.5483% | 12 |
| 200 | 68.8824% | 30.8944% | 9.1407% | 10 |
| 1000 | 93.1286% | 60.6846% | 9.1407% | 10 |



### 测试数据
#### 1s打印10条数据，单条数据1kb

|    | CPU |  CPU 增长  | 内存 | 内存 增长 | 备注 | 
|  ----  | ----  | ----  | ----  | ----  |----  |
| 基础状态| 	0	| 0	| 146M	| 0	| 
| LogUtils| 	23%| 	23%| 	150M| 	4M	| 仅仅打印到Logcat，不异步文件io（附件1）| 
| LogUtils| 	28.8593% | 	28.8593%| 	158M| 	12M	| 异步文件io，并且同时打印到Logcat（附件2）| 
| LogUtils| 	23.5611% | 	23.5611%| 	158M| 	12M	|  仅仅异步文件io，不打印到Logcat（附件3）| 
#### 结论:
|  日志条数/秒   | 主进程 CPU（ 单位：百分比） |  IO 主线程 CPU（ 单位：百分比）  | Logcat CPU(单位：百分比)  |  内存(单位：MB)  | 
|  ----  | ----  | ----  | ----  | ----  |
| 10 | 28.8593% | 5.765% | 5.2982% | 12 |

附件1（仅仅打印到Logcat，日志如下）<br>
[logutils_top_output.log] <br>
附件2（同时打印到logcat，并且io到本地文件，日志如下） <br>
[logutils_top_output.log] <br>
附件3（不打印到logcat，并且io到本地文件，日志如下） <br>
[logutils_top_output.log] <br>

#### 1s打印50条数据，单条数据1kb
|    | CPU |  CPU 增长  | 内存 | 内存 增长 | 备注 | 
|  ----  | ----  | ----  | ----  | ----  |----  |
| 基础状态| 	0	| 0	| 146M	| 0	| 
| LogUtils| 	27.5417%| 	27.5417%| 	150M| 	4M	| 仅仅打印到Logcat，不异步文件io（附件1）| 
| LogUtils| 	49.4533% | 	49.4533%| 	158M| 	12M	| 异步文件io，并且同时打印到Logcat（附件2）| 
| LogUtils| 	40.3714% | 	40.3714%| 	158M| 	12M	|  仅仅异步文件io，不打印到Logcat（附件3）| 
#### 结论:
|  日志条数/秒   | 主进程 CPU（ 单位：百分比） |  IO 主线程 CPU（ 单位：百分比）  | Logcat CPU(单位：百分比)  |  内存(单位：MB)  | 
|  ----  | ----  | ----  | ----  | ----  |
| 50 | 49.4533% | 19.16% | 9.0819% | 10 |

附件1（仅仅打印到Logcat，日志如下）<br>
[logutils_top_output.log] <br>
附件2（同时打印到logcat，并且io到本地文件，日志如下） <br>
[logutils_top_output.log] <br>
附件3（不打印到logcat，并且io到本地文件，日志如下） <br>
[logutils_top_output.log] <br>

#### 1s打印100条数据，单条数据1kb

|    | CPU |  CPU 增长  | 内存 | 内存 增长 | 备注 | 
|  ----  | ----  | ----  | ----  | ----  |----  |
| 基础状态| 	0	| 0	| 145M	| 0	| 
| LogUtils| 	28.6%| 	28.6%| 	154M| 	9M	| 仅仅打印到Logcat，不异步文件io（附件1）| 
| LogUtils| 	63.3941% | 	63.3941%| 	158M| 	12M	| 异步文件io，并且同时打印到Logcat（附件2）| 
| LogUtils| 	55.8458% | 	55.8458%| 	158M| 	12M	|  仅仅异步文件io，不打印到Logcat（附件3）| 
#### 结论:
|  日志条数/秒   | 主进程 CPU（ 单位：百分比） |  IO 主线程 CPU（ 单位：百分比）  | Logcat CPU(单位：百分比)  |  内存(单位：MB)  | 
|  ----  | ----  | ----  | ----  | ----  |
| 100 | 63.3941% | 25.7059% | 7.5483% | 12 |

附件1（仅仅打印到Logcat，日志如下）<br>
[logutils_top_output.log] <br>
附件2（同时打印到logcat，并且io到本地文件，日志如下） <br>
[logutils_top_output.log] <br>
附件3（不打印到logcat，并且io到本地文件，日志如下） <br>
[logutils_top_output.log] <br>

#### 1s打印200条数据，单条数据1kb

|    | CPU |  CPU 增长  | 内存 | 内存 增长 | 备注 | 
|  ----  | ----  | ----  | ----  | ----  |----  |
| 基础状态| 	0	| 0	| 145M	| 0	| 
| LogUtils| 	26.6667%| 	26.6667%| 	154M| 	9M	| 仅仅打印到Logcat，不异步文件io（附件1）| 
| LogUtils| 	68.8824% | 	68.8824%| 	158M| 	12M	| 异步文件io，并且同时打印到Logcat（附件2）| 
| LogUtils| 	59.7417% | 	59.7417%| 	158M| 	12M	|  仅仅异步文件io，不打印到Logcat（附件3）| 
#### 结论:
|  日志条数/秒   | 主进程 CPU（ 单位：百分比） |  IO 主线程 CPU（ 单位：百分比）  | Logcat CPU(单位：百分比)  |  内存(单位：MB)  | 
|  ----  | ----  | ----  | ----  | ----  |
| 200 | 68.8824% | 30.8944% | 9.1407% | 10 |

附件1（仅仅打印到Logcat，日志如下）<br>
[logutils_top_output.log] <br>
附件2（同时打印到logcat，并且io到本地文件，日志如下） <br>
[logutils_top_output.log] <br>
附件3（不打印到logcat，并且io到本地文件，日志如下） <br>
[logutils_top_output.log] <br>

#### 1s打印1000条数据，单条数据1kb

|    | CPU |  CPU 增长  | 内存 | 内存 增长 | 备注 | 
|  ----  | ----  | ----  | ----  | ----  |----  |
| 基础状态| 	0	| 0	| 146M	| 0	| 
| LogUtils| 	93.128% | 	93.128%| 	158M| 	12M	| 异步文件io，并且同时打印到Logcat（附件2）| 
| LogUtils| 	90.230% | 	90.230%| 	158M| 	12M	|  仅仅异步文件io，不打印到Logcat（附件3）| 
#### 结论:
|  日志条数/秒   | 主进程 CPU（ 单位：百分比） |  IO 主线程 CPU（ 单位：百分比）  | Logcat CPU(单位：百分比)  |  内存(单位：MB)  | 
|  ----  | ----  | ----  | ----  | ----  |
| 1000 | 93.1286% | 60.6846% | 9.1407% | 10 |

附件1（仅仅打印到Logcat，日志如下）<br>
[logutils_top_output.log] <br>
附件2（同时打印到logcat，并且io到本地文件，日志如下） <br>
[logutils_top_output.log] <br>
附件3（不打印到logcat，并且io到本地文件，日志如下） <br>
[logutils_top_output.log] <br>