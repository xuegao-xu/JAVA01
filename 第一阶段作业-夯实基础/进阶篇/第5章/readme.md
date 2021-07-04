# 第五章学习笔记

## 1	多进程和多线程简介

**多进程概念**

- 当前的操作系统都是多任务OS
- 每个独立执行的子任务就是一个进程
- OS将时间划分为多个时间片（时间很短）
- 每个时间片内将CPU分配给某一个任务，时间片结束，CPU将自动回收，再分配给另外任务。从外部看，所有任务是同时在执行。但是在CPU上，任务是按照串行依次运行（单核CPU）。如果是多核，多个进程任务可以并行。但是单个核上，多进程只能串行执行。

**多进程的优点**

- 可以同时运行多个任务
- 程序因IO堵塞时，可以释放CPU，让CPU为其他程序服务
- 当系统有多个CPU时，可以为多个程序同时服务
- 我们的CPU不再提高频率，而是提高核数
- 2005年Herb Sutter的文章The free lunch is over，指明多核和并行程序才是提高程序性能的唯一办法



**多进程的缺点**

- 太笨重，不好管理
- 太笨重，不好切换



**多线程概念**

- 一个程序可以包括多个子任务，可串/并行
- 每个子任务可以称为一个线程
- 如果一个子任务阻塞，程序可以将CPU调度另外一个子任务进行工作。这样CPU还是保留在本程序中，而不是被调度到别的程序(进程)去。这样，提高本程序所获得CPU时间和利用率。



**多进程vs 多线程**

- 线程共享数据
- 线程通讯更高效
- 线程更轻量级，更容易切换
-  多个线程更容易管理

**多进程执行：启动两个java.exe，多线程执行：只启动一个java.exe**



## 2	Java多线程实现

```
Java 多线程创建
• java.lang.Thread
    – 线程继承Thread类，实现run方法
public class Thread1 extends Thread{
        public void rum(){
            System.out.println("hello");
        }
}
• java.lang.Runnable接口
    – 线程实现Runnable接口，实现run方法
public class Thread2 implements Runnable{
        public void rum(){
            System.out.println("hello");
        }
}
```

```
Java多线程启动
– start方法，会自动以新进程调用run方法
– 直接调用run方法，将变成串行执行
– 同一个线程，多次start会报错，只执行第一次start方法
– 多个线程启动，其启动的先后顺序是随机的
– 线程无需关闭，只要其run方法执行结束后，自动关闭
– main函数(线程)可能早于新线程结束，整个程序并不终止
– 整个程序终止是等所有的线程都终止(包括main函数线程)
```

```
Thread vs Runnable(Java多线程实现对比)
– Thread占据了父类的名额，不如Runnable方便
– Thread 类实现Runnable
– Runnable启动时需要Thread类的支持
– Runnable 更容易实现多线程中资源共享
结论：建议实现Runnable接口来完成多线程
```



## 3	Java多线程信息共享

```
多线程信息共享
• 线程类
    – 通过继承Thread或实现Runnable
    – 通过start方法，调用run方法， run方法工作
    – 线程run结束后，线程退出
• 粗粒度：子线程与子线程之间、和main线程之间缺乏交流
• 细粒度：线程之间有信息交流通讯
    – 通过共享变量达到信息共享
    – JDK原生库暂不支持发送消息(类似MPI并行库直接发送消息)
• 通过共享变量在多个线程中共享消息
    – static变量
    – 同一个Runnable类的成员变量
• 多线程信息共享问题
    – 工作缓存副本
    – 关键步骤缺乏加锁限制
• i++，并非原子性操作
    – 读取主存i (正本)到工作缓存(副本)中
    – 每个CPU执行(副本)i+1操作
    – CPU将结果写入到缓存(副本)中
    – 数据从工作缓存(副本)刷到主存(正本)中
• 变量副本问题的解决方法
    – 采用volatile 关键字修饰变量
    – 保证不同线程对共享变量操作时的可见性
• 关键步骤加锁限制
    – 互斥：某一个线程运行一个代码段(关键区)，其他线程不能同时运行这个代码段
    – 同步：多个线程的运行，必须按照某一种规定的先后顺序来运行
    – 互斥是同步的一种特例
• 互斥的关键字是synchronized
    – synchronized代码块/函数，只能一个线程进入
    – synchronized加大性能负担，但是使用简便
```



## 4	Java多线程管理

```
多线程管理
• 线程类
    – 通过继承Thread或实现Runnable
    – 通过start方法，调用run方法，run方法工作
    – 线程run结束后，线程退出
• 粗粒度：子线程与子线程之间、和main线程之间缺乏同步
• 细粒度：线程之间有同步协作
    – 等待
    – 通知/唤醒
    – 终止
• 线程状态
    – NEW 刚创建(new)
    – RUNNABLE 就绪态(start)
    – RUNNING 运行中(run)
    – BLOCK 阻塞(sleep)
    – TERMINATED 结束
• Thread的部分API已经废弃
    – 暂停和恢复suspend/resume
    – 消亡stop/destroy
• 线程阻塞/和唤醒
    – sleep，时间一到，自己会醒来
    – wait/notify/notifyAll，等待，需要别人来唤醒
    – join，等待另外一个线程结束
    – interrupt,向另外一个线程发送中断信号,该线程收到信号,会触发InterruptedException(可解除阻塞)，并进行下一步处理
• 线程被动地暂停和终止
    – 依靠别的线程来拯救自己
    – 没有及时释放资源
• 线程主动暂停和终止
    – 定期监测共享变量
    – 如果需要暂停或者终止，先释放资源，再主动动作  
    – 暂停：Thread.sleep()，休眠
    – 终止：run方法结束，线程终止
• 多线程死锁
    – 每个线程互相持有别人需要的锁(哲学家吃面问题)
    – 预防死锁，对资源进行等级排序
• 守护(后台)线程
    – 普通线程的结束，是run方法运行结束
    – 守护线程的结束，是run方法运行结束，或main函数结束
    – 守护线程永远不要访问资源，如文件或数据库等
• 线程查看工具jvisualvm
```



## 5	Java并发框架Executor

```
并行计算
• 业务：任务多，数据量大
• 串行vs 并行
    – 串行编程简单，并行编程困难
    – 单个计算核频率下降，计算核数增多，整体性能变高
• 并行困难(任务分配和执行过程高度耦合)
    – 如何控制粒度，切割任务
    – 如何分配任务给线程，监督线程执行过程
• 并行模式
    – 主从模式(Master-Slave)
    – Worker模式(Worker-Worker)
• Java并发编程
    – Thread/Runnable/Thread组管理
    – Executor(重点)
    – Fork-Join框架    
```

```
线程组管理
• 线程组ThreadGroup
    – 线程的集合
    – 树形结构，大线程组可以包括小线程组
    – 可以通过enumerate方法遍历组内的线程，执行操作
    – 能够有效管理多个线程，但是管理效率低
    – 任务分配和执行过程高度耦合
    – 重复创建线程、关闭线程操作，无法重用线程
```

```
Executor
• 从JDK 5开始提供Executor FrameWork (java.util.concurrent.*)
    – 分离任务的创建和执行者的创建
    – 线程重复利用(new线程代价很大)
• 理解共享线程池的概念
    – 预设好的多个Thread，可弹性增加
    – 多次执行很多很小的任务
    – 任务创建和执行过程解耦
    – 程序员无需关心线程池执行任务过程
• 主要类：ExecutorService, ThreadPoolExecutor，Future
    – Executors.newCachedThreadPool/newFixedThreadPool 创建线程池
    – ExecutorService 线程池服务
    – Callable 具体的逻辑对象(线程类)
    – Future 返回结果    
```



## 6	Java并发框架Fork-Join

```
并行计算
• 并行模式
    – 主从模式(Master-Slave)
    – Worker模式(Worker-Worker)
• Java并发编程
    – Thread/Runnable/Thread组管理
    – Executor
    – Fork-Join框架(重点)
```

```
Fork-Join
• Java 7 提供另一种并行框架：分解、治理、合并(分治编程)
• 适合用于整体任务量不好确定的场合(最小任务可确定)
• 关键类
    – ForkJoinPool 任务池
    – RecursiveAction
    – RecursiveTask
```



## 7	Java并发数据结构

```
并发数据结构
• 常用的数据结构是线程不安全的
    – ArrayList, HashMap, HashSet 非同步的
    – 多个线程同时读写，可能会抛出异常或数据错误
• 传统Vector，Hashtable等同步集合性能过差
• 并发数据结构：数据添加和删除
    – 阻塞式集合：当集合为空或者满时，等待
    – 非阻塞式集合：当集合为空或者满时，不等待，返回null或异常
• List
    – Vector 同步安全，写多读少
    – ArrayList 不安全
    – Collections.synchronizedList(List list) 基于synchronized，效率差
    – CopyOnWriteArrayList 读多写少，基于复制机制，非阻塞
• Set
    – HashSet 不安全
    – Collections.synchronizedSet(Set set) 基于synchronized，效率差
    – CopyOnWriteArraySet (基于CopyOnWriteArrayList实现) 读多写少，非阻塞 
• Map
    – Hashtable 同步安全，写多读少
    – HashMap 不安全
    – Collections.synchronizedMap(Map map) 基于synchronized，效率差
    – ConcurrentHashMap 读多写少，非阻塞
• Queue & Deque (队列，JDK 1.5 提出)
    – ConcurrentLinkedQueue 非阻塞
    – ArrayBlockingQueue/LinkedBlockingQueue 阻塞    
```



## 8	Java并发协作控制

```
线程协作
• Thread/Executor/Fork-Join
    – 线程启动，运行，结束
    – 线程之间缺少协作
• synchronized 同步
    – 限定只有一个线程才能进入关键区
    – 简单粗暴，性能损失有点大
```

```
Lock
• Lock也可以实现同步的效果
    – 实现更复杂的临界区结构
    – tryLock方法可以预判锁是否空闲
    – 允许分离读写的操作，多个读，一个写
    – 性能更好
• ReentrantLock类，可重入的互斥锁
• ReentrantReadWriteLock类，可重入的读写锁
• lock和unlock函数
```

```
Semaphore
• 信号量，由1965年Dijkstra提出的
• 信号量：本质上是一个计数器
• 计数器大于0，可以使用，等于0不能使用
• 可以设置多个并发量，例如限制10个访问
• Semaphore
    – acquire获取
    – release释放
• 比Lock更进一步，可以控制多个同时访问关键区
```

```
Latch
• 等待锁，是一个同步辅助类
• 用来同步执行任务的一个或者多个线程
• 不是用来保护临界区或者共享资源
• CountDownLatch
    – countDown() 计数减1
    – await() 等待latch变成0
```

```
Barrier
• 集合点，也是一个同步辅助类
• 允许多个线程在某一个点上进行同步
• CyclicBarrier
    – 构造函数是需要同步的线程数量
    – await等待其他线程，到达数量后，就放行
```

```
Phaser
• 允许执行并发多阶段任务，同步辅助类
• 在每一个阶段结束的位置对线程进行同步，当所有的线程都到达这步，再进行下一步
• Phaser
    – arrive()
    – arriveAndAwaitAdvance()
```

```
Exchanger
• 允许在并发线程中互相交换消息
• 允许在2个线程中定义同步点，当两个线程都到达同步点，它们交换数据结构
• Exchanger
    – exchange(), 线程双方互相交互数据
    – 交换数据是双向的
```



9	Java定时任务执行

```
定时任务
• Thread/Executor/Fork-Join 多线程
    – 立刻执行
    – 框架调度
• 定时执行
    – 固定某一个时间点运行
    – 以某一个周期
• 简单定时器机制
    – 设置计划任务，也就是在指定的时间开始执行某一个任务。
    – TimerTask 封装任务
    – Timer类定时器    
• Executor +定时器机制
• ScheduledExecutorService
    – 定时任务
    – 周期任务   
• Quartz
– Quartz是一个较为完善的任务调度框架
– 解决程序中Timer零散管理的问题
– 功能更加强大
    • Timer执行周期任务，如果中间某一次有异常，整个任务终止执行
    • Quartz执行周期任务，如果中间某一次有异常，不影响下次任务执行    
```

