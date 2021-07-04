# 第八章学习笔记

### 1	Java调用Java程序(RMI)

```
Java 混合编程
• 众多编程语言，各有各的特点和应用范围
    – https://www.tiobe.com/tiobe-index/
• 现实世界存在很多应用程序
    – 由不同编程语言实现
    – 分布式部署的
• Java混合编程
    – Java程序和其他应用程序进行通讯和数据交互
    – Java和Java/C/JS/Python/Web Service/命令行的混合编程
```

```
RMI(1)
• 学习Java程序过程
    – 在main函数里面完成所有功能
    – 基于函数/方法将功能拆开，采用函数相互调用
    – 类对象/继承/多态
    – A a = new A(); a.f1(); //完成某一个功能

RMI(2)
• 单虚拟机JVM上的程序运行
    – 启动一个main程序，然后重复以下的2个步骤
        • new 出一个对象
        • 调用对象的某一个方法
• 多虚拟机JVM的程序运行
    – 启动多个main程序，这些程序可以部署在多个机器/虚拟机上
    – 多个进程可通过网络互相传递消息进行协作
    – 进程通过RMI可调用另一个机器的Java的函数
    
RMI(3)
• RMI：Remote Method Invocation 远程方法调用
    – 两个位于不同JVM虚拟机的Java程序互相请求访问
```

![](https://image-1306027094.cos.ap-nanjing.myqcloud.com/md/1.jpg)

![](https://image-1306027094.cos.ap-nanjing.myqcloud.com/md/2.jpg)

**RMI(4)**

![](https://image-1306027094.cos.ap-nanjing.myqcloud.com/md/3.jpg)

![](https://image-1306027094.cos.ap-nanjing.myqcloud.com/md/4.jpg)

![](https://image-1306027094.cos.ap-nanjing.myqcloud.com/md/5.jpg)

```
RMI(5)
• RMI的参数和返回值
    – (自动化)传递远程对象(实现Remote接口)
        • 当一个对远程对象的引用从一个JVM传递到另一个JVM，该远程对象的发送者和接收者将持有同一个实体对象的引用。这个引用并非是一个内存位置，而是由网络地址和该远程对象的唯一标识符构成的。###两个JVM拥有同一个对象###
    – (自动化)传递可序列化对象(实现Serializable接口)
        • JVM中的一个对象经过序列化后的字节，通过网络，其副本传递到另一个JVM中，并重新还原为一个Java对象。###每个JVM拥有自己的对象###
        
RMI(6)
• RMI优点
    – 跨平台分布式对象调用
    – 完全对象支持
    – 安全策略
• RMI缺点
    – 双方必须是Java语言实现
    – 不如消息传递协作方便        
```

### 2	Java调用C程序

```
Java和C互操作(通过JNI)
    – JNI，Java Native Interface
    – Java和本地C代码进行互操作
        • Java调用C程序完成一些需要快速计算的功能(重点)
        • C调用Java程序(基于反射的方法)               
```

**调用步骤**

```
• 在Java类中声明一个本地方法
• 调用javac.exe编译，得到HelloNative.class
• 调用javah.exe得到包含该方法(Java_HelloNative_greeting)的头文件HelloNative.h
• 实现.c文件(对应HelloNative.h)
• 将.c和.h文件，整合为共享库(DLL)文件
• 在Java类中，加载相应的共享库文件
```

### 3	Java调用JavaScript程序

```
JavaScript
• JavaScript语言，又称JS语言
    – 脚本语言(解释型语言)
        • 便于快速变更，可以修改运行时的程序行为
        • 支持程序用户的定制化
    – 可用于Web前端和后端开发(全栈)        
```

```
Java调用JS
    • 脚本引擎，ScriptEngine
        – Nashorn，JDK 8自带的JS解释器(JDK6/7是Rhino解释器)
        • ScriptEngine engine = new	ScriptEngineManager().getEngineByName(“nashorn”)
    – 主要方法(通过Nashorn的脚本)
        • eval，执行一段js脚本. eval(Stringstr),eval(Readerreader)
        • put，设置一个变量
        • get，获取一个变量
        • createBindings, 创建一个Bindings
        • setBindings, 设置脚本变量使用的范围
```

### 4	Java调用Python程序

```
• Python语言
    – 解释型脚本语言
    – 可用于科学计算软件/Web/桌面软件开发
```

```
Jython(曾用名JPython)
    – Jython是Python语言在Java平台的实现
    – Jython是在JVM上实现的Python，由Java编写
    – Jython将Python源码编译成JVM字节码，由JVM执行对应的字节码，因此能很好的与JVM集成。Jython并不是Java和Python的连接器。
```

```
Jython
• 关键类
    – PythonInterpreter
        • exec 执行语句
        • set 设置变量值
        • get 获取变量值
        • execfile执行一个python文件
    – PyObject
    – PyFunction
```

### 5	Java调用Web Service

```
Java 调用Web Service
    • Java提供wsimport 工具
        – %JAVA_HOME%\bin目录下
        – 根据wsdl文档，自动产生客户端中间代码
        – wsimport -keep -verbose        http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?WSDL
    
    • Java 调用Web Service
        – 调用wsimport所产生客户端中间代码
        – 提供相应参数
        – 获取返回结果
        
    • Java 调用Web Service 其他办法
        – Axis/Axis2 (axis.apache.org)
        – 采用URLConnection访问Web Service
        – 采用HttpClient访问Web Service           
```

|   参数   | 说明                                  |    参数    |               说明               |
| :------: | :------------------------------------ | :--------: | :------------------------------: |
|    -p    | 定义生成包名称                        |     -s     | 指定客户端执行类的源文件存放目录 |
|    -d    | 指定客户端执行类的class文件的存放目录 |   -keep    |   表示生成客户端执行类的源代码   |
| -verbose | 输出编译器正在执行操作的信息          | -extension |      使用扩展来支持SOAP1.2       |

### 6	Java调用命令行

**命令行**
很多程序没有源码，但是可以执行命令行，也可以带输入参数，可以有输出结果

```
Runtime
• Java提供Runtime类
    – exec 以一个独立进程执行命令command, 并返回Process句柄
    – 当独立进程启动后，需要处理该进程的输出流/错误流
        • 调用Process.getInputStream 可以获取进程的输出流
        • 调用Process.getErrorStream可以获取进程的错误输出流
    – 调用Process.waitFor 等待目标进程的终止(当前进程阻塞)	
```

