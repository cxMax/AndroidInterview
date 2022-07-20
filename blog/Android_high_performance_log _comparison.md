# Android高性能日志方案性能测试数据对比
> 主要对以下日志库进行性能测试和对比，xlog、logan、log4a、Java-Log-IO方案


## 测试方法
* CPU及内存 <br>
`top -m 10 -H | grep 19733`
* 内存  <br>
`dumpsys meminfo com.example.baselib`
* 耗时  <br>
`python systrace.py --time=10 -o systrace_LogUtil_IO.html sched gfx view wm -a com.example.baselib`

## 测试case
### 1w条/s，单条数据 10  byte，测试10次取平均
|   | CPU	| CPU 增长 | Java内存	 | 内存 增长 | 平均耗时 | 备注 | 
|  ----  | ----  | ----  | ----  | ----  | ----  | ----  |
|  基础状态 | 0%	| 0% | / | / |  | | 
|  LogUtils | 120% | 120% | / | / | 877.107ms | [systrace_logutils.html] | 
|  Log4a | 7.3% | 7.3% | / | / | 36.689ms | [systrace_log4a.html] | 
|  XLog | 21% | 21% | / | / | 403.687ms | [systrace_xlog.html] | 
|  Logan | 15% | 15% | / | / | 403.687ms | [systrace_logan.html] | 
* cpu使用率<br>
Log4a（15倍⬆️） > Logan（7倍⬆️） > XLog（4.7倍⬆️） > LogUtils
* 耗时<br>
Log4a（22倍⬆️）> Logan（6.1倍⬆️）> XLog（1.17倍⬆️） > LogUtils
* 内存（下同）<br>
LogUtils是java io的方案，所以会存在频繁的gc，造成内存抖动。<br>
其余三个方案都是在native做的，java内存变化几乎都无差异，肯定是mmap方案，内存表现更佳，因此就不单独记录了。<br>

### 10w条/s，单条数据 10  byte，测试10次取平均
|   | CPU	| CPU 增长 | Java内存	 | 内存 增长 | 平均耗时 | 备注 | 
|  ----  | ----  | ----  | ----  | ----  | ----  | ----  |
|  基础状态 | 0%	| 0% | / | / |  | | 
|  LogUtils | 200% | 200% | / | / | 64648ms |直接OOM| 
|  Log4a | 15% | 15% | / | / | 240.398ms | [systrace_log4a.html] | 
|  XLog | 100% | 100% | / | / | 3,934.656ms | [systrace_xlog.html] | 
|  Logan | 78% | 78% | / | / | 1,061.961ms | [systrace_logan.html] | 
* cpu使用率<br>
Log4a（12.3倍⬆️） > Logan（1.6倍⬆️） > XLog（1倍⬆️） > LogUtils
* 耗时<br>
Log4a（268倍⬆️）> Logan（60倍⬆️）> XLog（15.4倍⬆️） > LogUtils

### 10条/s，单条日志10MB，测试10次
|   | CPU	| CPU 增长 | Java内存	 | 内存 增长 | 平均耗时 | 备注 | 
|  ----  | ----  | ----  | ----  | ----  | ----  | ----  |
|  基础状态 | 0%	| 0% | / | / |  | | 
|  LogUtils | 86% | 86% | / | / | 46ms | [systrace_logutils.html] | 
|  Log4a | 10% | 10% | / | / | 44.297ms | [systrace_log4a.html] | 
|  XLog | 10% | 10% | / | / | 51.525ms | [systrace_xlog.html] | 
|  Logan | 12% | 12% | / | / | 36.218ms | [systrace_logan.html] | 

### 50条/s，单条数据400kb，测试10次取平均

|   | CPU	| CPU 增长 | Java内存	 | 内存 增长 | 平均耗时 | 备注 | 
|  ----  | ----  | ----  | ----  | ----  | ----  | ----  |
|  基础状态 | 0%	| 0% | / | / |  | | 
|  LogUtils | 30% | 30% | / | / | 135.083ms | [systrace_logutils.html] | 
|  Log4a | 17% | 17% | / | / | 180.948ms | [systrace_log4a.html] | 
|  XLog | 13% | 13% | / | / | 175.549ms | [systrace_xlog.html] | 
|  Logan | 21% | 21% | / | / | 128.801ms | [systrace_logan.html] | 

### 100条/s，单条数据400kb，测试10次取平均

|   | CPU	| CPU 增长 | Java内存	 | 内存 增长 | 平均耗时 | 备注 | 
|  ----  | ----  | ----  | ----  | ----  | ----  | ----  |
|  基础状态 | 0%	| 0% | / | / |  | | 
|  LogUtils | 50% | 50% | / | / | 248.755ms | [systrace_logutils.html] | 
|  Log4a | 24% | 24% | / | / | 328.498ms | [systrace_log4a.html] | 
|  XLog | 18% | 18% | / | / | 321.137ms | [systrace_xlog.html] | 
|  Logan | 35% | 35% | / | / | 235.518ms | [systrace_logan.html] | 

### 1s打印50条数据，单条数据1kb

|   | CPU	| CPU 增长 | Java Heap | Java Heap增长 | native Heap | Native Heap增长 | 耗时 | 备注 |
|  ----  | ----  | ----  | ----  | ----  | ----  | ----  | ----  | ----  |
|  基础状态 | 2.3% | 0% | 16572kb |  | 8620kb |  |  | |
|  LogUtils | 34.85% | 1415%↑ | 17719kb | 7%↑	| 11772kb | 36.6%↑| 5.658 ms|  |
| log4a | 4.4697% | 94.3%↑| 16746.963kb | 0% | 9166.8148 | 6%↑| 8.138 ms |  |
| xlog | 5.1556% | 124.2% | 16721.9429 | 0% | 9426.8571 | 9%↑| 15.347 ms |  |
| logan | 4.8417% | 110.5%	 | 16741.0811	| 0% | 9731.9 | 12.9%↑| 7.069 ms|  |
* logutils: <br>
logutils_top_output.log <br>
logutils_meminfo_1_output.log <br>
logutils_meminfo_2_output.log <br>
logutils_meminfo_output.log <br>
systrace_logutils.html <br>
* log4a: <br>
logutils_top_output.log <br>
systrace_log4a.html <br>
log4a_meminfo_output.log <br>
* Xlog: <br>
logutils_top_output.log <br>
log_meminfo_output.log <br>
systrace_xlog.html <br>
* Logan <br>
logutils_top_output.log <br>
log_meminfo_output.log <br>
systrace_logan.html <br>





