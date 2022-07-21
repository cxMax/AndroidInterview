# Android高性能日志方案
> 通过源码分析以及调研，对比xlog、logan、log4a的日志方案

|   | 版本	| 最后更新日期 | 文件写入方案 | 压缩 | 加密 | 日志导出 | 多进程支持 | 单进程多文件 | 多线程 | so大小 | 缺点 | 优点 | 结论 |
|  ----  | ----  | ----  | ----  | ----  | ----  | ----  | ----  | ----  | ----  | ----  | ----  | ----  | ----  |
| xlog | 1.2.6 | 2022/5/17 | mmap | 必须 | 非必须 | 需解密 | 不支持（分进程单独存储文件路径）（按业务储存不同文件路径）| 支持 | c++实现加锁(log2file的时候会锁mutex_log_file_满足触发flush的条件时候，会去锁mutex_buffer_async_ | libmarsxlog.so（108kb）libc++_shared.so（657kb）|1. 即使不加密，仍需要进行decode_mars_nocrypt_log_file.py解密，无法直接明文看日志，直接打开会有乱码，<br>1.1 出现乱码的原因，主要是header进行了压缩，解压缩就正常了， 修改成本大。https://github.com/Tencent/mars/issues/391<br>2. libc++_shared.so与mmkv冲突，<br>2.1 解决方案：https://github.com/Tencent/MMKV/issues/362<br>3. logcat日志打印格式修改，成本低。<br>4. log2mmap是在主线程，有可能会造成主线程卡顿，<br>5. 丢日志问题,<br>5.1 log2mmap，mmap2file，锁竞争情况下，极端场景，mmap溢出导致，日志丢失，（概率应该低）https://github.com/Tencent/mars/issues/631<br>6. 卡顿情况,<br>6.1 极端场景，内存不足情况下，日志压缩以及write会造成卡顿，但不是xlog导致的，https://github.com/Tencent/mars/issues/550<br>7. 多进程的处理, <br>7.1 单个进程操作单个文件https://github.com/Tencent/mars/issues/200<br>8. 崩溃情况,<br>8.1 4.x系统，so找不到，已解决，https://github.com/Tencent/mars/issues/334<br>8.2 mmap缓存路径不能设置到sdcard，否则偶现崩溃，已解决，https://github.com/Tencent/mars/issues/204 | 1. android native 函数 __android_log_write，避免了原生android.util.Log.println<br>2. 1.3.0以上版本提供了多实例，可以根据业务进行区分（还是按文件区分，不能写在同一个文件中）<br>3. 支持最大文件size，<br>4. 按照天分片段记录，不设置，默认是存在一个文件中<br>5. 最小1天，最大10天过期时间<br>6. 初始化调用appender_open的时候，2分钟后则回去清理过日志期文件<br>7. 单条日志的最大单次写入长度是16kb（log2mmap），log2file的buffer大小是4kb<br>8. log2file线程，在appender_open的时候就开始创建，并且进入休眠等待状态，等待cond_buffer_async_将其唤醒<br>9. mmap内存固定大小为150kb<br>9.1 mmap2file写入时机，<br>9.2 mmap缓存达到1/3或者<br>9.3 手动flush，<br>9.4 15min超时会async_flush，<br>9.5 每次初始化后open的时候。https://github.com/Tencent/mars/issues/310，https://github.com/Tencent/mars/issues/819<br>10. 初始化时机不一样，xlog会在调用appenderOpen的时候初始化（以及去写mmap若还有日志，就会写入文件）|	
| logan | 1.2.4 | 2021/8/9 | mmap | 必须 | 必须 | 需解密 | 不支持（分进程单独存储文件路径）| 不支持 | Java层实现加锁<br>对任务进行加锁，对native_wirte没有加锁，上层多线程调用native_wirte会出问题 | 267kb | 1. 日志文件按照日期来的，每天的日志都写在一个文件里面，如果超出设置的文件默认大小10M（可手动设置），他就不会写了。<br> 1.1 解决方案：（需要监听文件大小，重新初始化一次）。<br>2. 需要上层自己封装logcat打印，（并且是原生的api），logan只支持本地写，并不提供logcat打印，https://github.com/Meituan-Dianping/Logan/issues/250<br>3. 固定LoganThread线程，消息队列、本地写、发送，都在于此。<br>3.1 最大队列500条，用于存储写、发送任务，超过500条，如果出现了单条任务比如说发送未执行完毕，就会持有锁，等着执行完毕，如果后续大量任务进来，超过500条阀值，后面任务就直接丢失，对应就是日志丢失。<br>4. 单实例问题，无法根据业务进行区分，全部都是写在一个文件里面。<br>5. 性能问题，解决cpu和耗时，主要问题是，每次写都会去判断文件是否存在，<br>5.1 已有pr，https://github.com/Meituan-Dianping/Logan/pull/359<br>5.2 fwrite，cpu问题，https://github.com/Meituan-Dianping/Logan/issues/226<br>6. 超出MaxFile之后，日志不会再写入了。<br> 6.1 需要监听日志文件大小，重新初始化。需要上层业务自己做https://github.com/Meituan-Dianping/Logan/issues/353<br>6.2 目前的策略是丢弃新写入的日志，也可以自己改，丢弃老的日志<br>7. 解密，需要自己提供python脚本，需参考<br>7.1 解密方案：https://github.com/lixiao123/DeLogan，https://github.com/Meituan-Dianping/Logan/issues/351<br>7.2 文件解析可能存在异常，https://github.com/Meituan-Dianping/Logan/issues/301<br>8. 不支持多进程，多进程可能会存在日志丢失https://github.com/Meituan-Dianping/Logan/issues/324<br>8.1 多进程的处理方式是，mmap_cache_ptah和file_path都跟进程名关联，https://github.com/Meituan-Dianping/Logan/issues/252<br>9. crash丢日志的问题，<br>9.1 解决方案有违官方初衷，该方案是启动后就先flush写入一次。https://github.com/Meituan-Dianping/Logan/issues/293，https://github.com/Meituan-Dianping/Logan/issues/269<br>9.2 APP重大事件，如进入后台。手动flush。https://github.com/Meituan-Dianping/Logan/issues/190<br>9.3 丢日志问题的根本原因，500这个队列是有关系的。https://github.com/Meituan-Dianping/Logan/issues/106<br>9.4 mmap缓存丢日志的问题，不足16字节，https://github.com/Meituan-Dianping/Logan/issues/81<br>10. 多线程，是由上层java，LoganThread控制的，java层通过对任务进行加锁，c层没有进行加锁处理<br>11. 未解决的问题<br>11.1 崩溃 https://github.com/Meituan-Dianping/Logan/issues/282<br>11.2 正在写入的任务，无法终止，https://github.com/Meituan-Dianping/Logan/issues/15 | 1. 超过一天，每次写文件前，都会清除过期日志。默认7天，<br>2. 单个文件大小默认10M，20KB分片写文件<br>3. mmap内存固定大小为150kb<br>4. 写入时机，<br>4.1 空文件第一条日志，<br>4.2 mmap缓存超过1/3，<br>4.3 使用内存buffer并且压缩完毕，<br>4.4 手动调用flush，就写入文件<br>5. 写入文件顺序，是先压缩，再加密，再写入<br>6. 所有写日志的操作(log2mmap，mmap2file)都是在LoganThread，不会造成主线程卡顿。<br>7. 调用w()或者s()的时候，才触发初始化 |
| log4a | 	1.5.0 | 2019/9/10 | mmap | 可选 | 无 | 明文 | 不支持（分进程单独存储文件路径）| 不支持 | c++（log2mmap,mmap2file两个buffer的拷贝是持有一把锁，flush2file是线程）| 318kb | 1. 没有过期日志清理功能，日志过期时间等，需要自己定义逻辑实现<br>1.1 因此，缓存文件是一直停留在用户设备中，并且随着使用不断增大，😂，这个肯定不行。<br>2. 我理解压缩应该是必须的，因为日志会很大，这样存不了多少，就占据大量的用户设备存储空间<br>3. 每天单文件记录，按日期来的<br>4. 并且日志文件大小没有限制（当日志文件写的无限大的时候，不知道有没问题）<br>5. 单实例问题，无法根据业务进行区分，全部都是写在一个文件里面<br>6. 没有做分片写入，就会导致，如果某一时间点大量写日记，就会造成cpu峰值<br>7. 日志丢失情况<br>7.1 log2mmap,mmap2file锁竞争，理论上，会有丢日志可能<br>8. 第三方app集成案例较少，线上表现还有待观察，目前还是根据源码进行判断 | 1. 初始化的时候，mmap若有数据->写入文件，并清空<br>2. mmap分配的内存大小默认为4kb，可以自行在初始化设置，demo设置的400kb。<br>3. 文件写入时机<br>3.1 changeLogPath<br>3.2 超过mmap缓存大小，就异步写入文件<br>3.3 每次初始化的时候，会将mmap已有的数据进行写入<br>3.4 手动调用flush<br>3.5 提出调用release | 
| DataTransHub | 1.2.8 | mmap	 |
| logback | | Java IO |								

## Xlog 和 Logan 对比				
> 会更倾向选择XLog

| 对比项 | Xlog | Logan | 
|  ----  | ----  | ----  | 
| cpu | 更优↑ |  | 
| 内存 | 更优↑ |  | 	
| 耗时 |  | 更优↑<br>解释一下，<br>1. Logan，log2mmap,mmap2file都是跑在LoganThread线程里面。<br>2. XLog，log2mmap这步是在主线程做的，mmap2file是在线程做的。<br>所以xlog耗时要大于Logan。 | 
| 压缩 | ✅| ✅| 
| 压缩方式 | 流式压缩 | 流式压缩 | 
| 必须压缩 | 必须 | 必须 | 
| 加密 | ✅ | ✅ | 
| 加密方式 | RSA | AES | 
| 明文日志 | ✅ | ❌ | 
| 必须解密 | 非必需 | 必须 | 
| 日志解密 | 更稳定↑ | 这里参见Logan缺点第7点 | 
| 多进程 | ❌ | ❌ | 
| 单进程多文件隔离<br>（按业务收集日志） | ✅ | ❌ | 
| 多线程	 | ✅更优↑<br>这里参见Xlog缺点第5点 | ✅<br>这里参见Logan优点第3点 | 
| 锁实现	 | c++ | java | 
| file写入时机<br>（尽可能的保障少丢日志） | 更优↑<br>这里参见Xlog优点第7点 | 参见Logan优点第4点 | 
| 分片写文件<br>（cpu表现避免出现峰值） | ❌ | ✅ | 
| 日志丢失表现 | 更优↑<b>这里参见Xlog缺点第5点 | 这里参见Logan缺点第1、3、6、9点 | 
| 自定义日志格式 | 修改成本一样—  | 修r改成本一样—  | 
| logcat日志打印 | ✅<br>甚至还进行了优化，参见Xlog优点第1点 | ❌<br>这里参见Logan缺点第2点 | 
| 稳定性 | 更优↑<br>这里参见Xlog优点第8点 | 这里参见Logan缺点第11点 | 
| 第三方app使用情况 | 更优↑ |  | 
| wiki及生态 | 更优↑<br>博客配合源码 | Logan主要是靠看源码进行理解 | 
| 源码阅读 | Xlog看上去有点乱，不过看多了还好 | 更优↑<br>Logan的代码更清晰，注释更多 | 
| 实现语言 | c++/c | c | 
| 其他优点 | 这里参考Xlog优点第1、3点 | 
| 其他缺点 | 这里参见Xlog缺点1、2点 | 

## 引用
* 微信终端跨平台组件 mars 系列（一） - 高性能日志模块xlog
* Xlog Benchmark
* Android高性能日志写入方案-mmap
* 微信跨平台组件mars-xlog架构分析及迁移思路
* android日志搜集原理及方案比较 - NeilZhang - 博客园
* android日志方案,Android 架构之高性能移动端日志系统_weixin_39990660的博客-CSDN博客
* Android日志系统搭建 - 掘金
* 开源系列 | 基于微信XLog的日志框架&& 对于XLog的分析 - 掘金
* 移动端日志收集方案对比
* https://github.com/wustMeiming/XlogDecoder
* 【Dev Club 分享】微信mars 的高性能日志模块 xlog
* 微信高性能线上日志系统xlog剖析
* 微信终端跨平台组件 mars 系列（一） - 高性能日志模块xlog



