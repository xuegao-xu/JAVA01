# 第六章学习笔记

### 1	网络基础知识

```
网络基础知识
• 网络是当前信息技术的第一推动力
• 每个计算设备上都有若干个网卡
• 每个网卡上有(全球唯一)单独的硬件地址，MAC地址
• IP地址:每个网卡/机器都有一个或多个IP地址
    – IPV4:192.168.0.100，每段从0到255
    – IPV6: 128bit长，分成8段，每段4个16进制数
• FE80:0000:0000:0000:AAAA:0000:00C2:0002
• https://baike.baidu.com/item/IPv6/172297?fr=aladdin
    – 查询：Windows平台ipconfig, Linux/Mac平台ifconfig
• port：端口,0-65535
    – 0~1023, OS已经占用了，80是Web，23是telnet
    – 1024~65535，一般程序可使用(谨防冲突)
• 两台机器通讯就是在IP+Port上进行的
• 在Windows/Linux/Mac上都可以通过netstat -an来查询
• 保留ip：127.0.0.1 本机
• 公网(万维网/互联网)和内网(局域网)
    – 网络是分层的
    – 最外层是公网/互联网
    – 底下的每层都是内网
    – ip地址可以在每个层次的网重用
    – tracert 看当前机器和目标机器的访问中继
• 通讯协议：TCP和UDP
• TCP(Transmission Control Protocol)
    – 传输控制协议，面向连接的协议
    – 两台机器的可靠无差错的数据传输
    – 双向字节流传递
• UDP(User Datagram Protocol)
    – 用户数据报协议，面向无连接协议
    – 不保证可靠的数据传输
    – 速度快，也可以在较差网络下使用
```

### 2	Java UDP编程

```
网络通讯协议
• 通讯协议：TCP和UDP
• TCP：Transmission Control Protocol
    – 传输控制协议，面向连接的协议
    – 两台机器的可靠无差错的数据传输
    – 双向字节流传递
• UDP: User Datagram Protocol
    – 用户数据报协议，面向无连接协议
    – 不保证可靠的数据传输
    – 速度快，也可以在较差网络下使用
```

```
UDP
• 计算机通讯：数据从一个IP的port出发（发送方），运输到另外一个IP的port（接收方）
• UDP：无连接无状态的通讯协议，
    – 发送方发送消息，如果接收方刚好在目的地，则可以接受。如果不在，那这个消息就丢失了
    – 发送方也无法得知是否发送成功
    – UDP的好处就是简单，节省，经济
• DatagramSocket：通讯的数据管道
    – send 和receive方法
    – (可选，多网卡)绑定一个IP和Port
• DatagramPacket
    – 集装箱：封装数据
    – 地址标签：目的地IP+Port
• 实例
    – 无主次之分
    – 接收方必须早于发起方执行    
```

### 3	Java TCP 编程

```
TCP
• TCP协议：有链接、保证可靠的无误差通讯
    – ①服务器：创建一个ServerSocket，等待连接
    – ②客户机：创建一个Socket，连接到服务器
    – ③服务器：ServerSocket接收到连接，创建一个Socket和客户的Socket建立专线连接，后续服务器和客户机的对话(这一对Socket)会在一个单独的线程（服务器端）上运行
    – ④服务器的ServerSocket继续等待连接，返回①
• ServerSocket: 服务器码头
    – 需要绑定port
    – 如果有多块网卡，需要绑定一个IP地址
• Socket: 运输通道
    – 客户端需要绑定服务器的地址和Port
    – 客户端往Socket输入流写入数据，送到服务端
    – 客户端从Socket输出流取服务器端过来的数据
    – 服务端反之亦然
• 服务端等待响应时，处于阻塞状态
• 服务端可以同时响应多个客户端
• 服务端每接受一个客户端，就启动一个独立的线程与之对
应
• 客户端或者服务端都可以选择关闭这对Socket的通道
• 实例
    – 服务端先启动，且一直保留
    – 客户端后启动，可以先退出
```

### 4	Java HTTP 编程

```
网页访问
• 网页是特殊的网络服务(HTTP, Hypertext Transfer Protocol)
    – 在浏览器输入URL地址
    – 浏览器将连接到远程服务器上(IP+80Port)
    – 请求下载一个HTML文件下来，放到本地临时文件夹中
    – 在浏览器显示出来
```

```
HTTP
– 超文本传输协议(HyperText Transfer Protocol)
– 用于从WWW（World Wide Web）服务器传输超文本到本地浏览器的传输协议
– 1989年蒂姆•伯纳斯•李（Tim Berners Lee）提出了一种能让远隔两地的研究者们共享知识的设想
– 借助多文档之间相互关联形成的超文本（HyperText），连成可相互参阅的WWW
– 1990年问世，1997年发布版本1.1，2015年发布版本2.0
– 资源文件采用HTML编写，以URL形式对外提供
```

```
HTML
– 超文本标记语言(HyperText Markup Language)
– 标准语法http://www.w3school.com.cn/html/index.asp
– 表单form
```

```
HTTP访问方式
• 访问方式
    – GET：从服务器获取资源到客户端
    – POST：从客户端向服务器发送数据
    – PUT：上传文件
    – DELETE：删除文件
    – HEAD：报文头部
    – OPTIONS：询问支持的方法
    – TRACE：追踪路径
    – CONNECT：用隧道协议连接代理
```

```
Java HTTP 编程
Java HTTP编程(java.net包)
    – 支持模拟成浏览器的方式去访问网页
    – URL , Uniform Resource Locator，代表一个资源
    – URLConnection
        • 获取资源的连接器
        • 根据URL的openConnection()方法获得URLConnection
        • connect方法，建立和资源的联系通道
        • getInputStream方法，获取资源的内容
```

### 5	Java HTTP 编程

```
JDK HttpClient
• JDK 9 新增，JDK10更新，JDK11正式发布
• java.net.http包
• 取代URLConnection
• 支持HTTP/1.1和HTTP/2
• 实现大部分HTTP方法
• 主要类
    – HttpClient
    – HttpRequest
    – HttpResponse
```

```
HttpComponents
• hc.apache.org, Apache出品
• 从HttpClient进化而来
• 是一个集成的Java HTTP工具包
    – 实现所有HTTP方法：get/post/put/delete
    – 支持自动转向
    – 支持https协议
    – 支持代理服务器等
```

### 6	Java NIO 编程

```
NIO
• Non-Blocking I/O
• 提供非阻塞通讯等方式
• 避免同步I/O通讯效率过低
• 一个线程可以管理多个连接
• 减少线程多的压力
• Non-Blocking I/O, 非阻塞I/O, (又名New I/O)
• JDK 1.4引入，1.7升级NIO 2.0 (包括了AIO)
• 主要在java.nio包中
• 主要类
    – Buffer 缓存区
    – Channel 通道
    – Selector多路选择器
    
• Buffer 缓冲区，一个可以读写的内存区域
    – ByteBuffer, CharBuffer, DoubleBuffer, IntBuffer, LongBuffer,ShortBuffer (StringBuffer 不是Buffer缓冲区)
• 四个主要属性
    – capacity 容量， position 读写位置
    – limit 界限， mark 标记，用于重复一个读/写操作

Channel 通道
– 全双工的，支持读/写(而Stream流是单向的)
– 支持异步读写
– 和Buffer配合，提高效率
– ServerSocketChannel 服务器TCP Socket 接入通道，接收客户端
– SocketChannel TCP Socket通道，可支持阻塞/非阻塞通讯
– DatagramChannel UDP 通道
– FileChannel 文件通道

Selector多路选择器
– 每隔一段时间，不断轮询注册在其上的Channel
– 如果有一个Channel有接入、读、写操作，就会被轮询出来
– 根据SelectionKey可以获取相应的Channel，进行后续IO操作
– 避免过多的线程
– SelectionKey四种类型
• OP_CONNECT
• OP_ACCEPT
• OP_READ
• OP_WRITE
```

### 7	Java AIO 编程

```
Non-Blocking I/O(NIO)
– 提供非阻塞等方式
– 避免I/O阻塞通讯效率过低
– 一个线程可以管理多个连接
– 减少线程多的压力
– 不是真异步
```

```
AIO
• Asynchronous I/O, 异步I/O
• JDK 1.7引入，主要在java.nio包中
• 异步I/O，采用回调方法进行处理读写操作
• 主要类
    – AsynchronousServerSocketChannel 服务器接受请求通道
  	  	 • bind 绑定在某一个端口accept 接受客户端请求
    – AsynchronousSocketChannel Socket通讯通道
   		 • read 读数据write 写数据
    – CompletionHandler 异步处理类
    	 • completed 操作完成后异步调用方法failed 操作失败后异步调用方法
```

|     三种I/O的区别     | BIO  |  NIO   |  AIO   |
| :-------------------: | :--: | :----: | :----: |
|       阻塞方式        | 阻塞 | 非阻塞 | 非阻塞 |
|       同步方式        | 同步 |  同步  |  异步  |
|       编程难度        | 简单 |  较难  |  困难  |
| 客户机/服务器线程对比 | 1:1  |  N:1   |  N:1   |
|         性能          |  低  |   高   |   高   |

### 8	Netty 编程

```
Netty
• Netty, http://netty.io
• 最早由韩国Trustin Lee 设计开发的
• 后来由JBoss接手开发，现在是独立的Netty Project
• 一个非阻塞的客户端-服务端网络通讯框架
• 基于异步事件驱动模型
• 简化Java的TCP和UDP编程
• 支持HTTP/2， SSL等多种协议
• 支持多种数据格式，如JSON等

• 关键技术
    – 通道Channel
        • ServerSocketChannel/NioServerSocketChannel/…
        • SocketChannel/NioSocketChannel
– 事件EventLoop
    • 为每个通道定义一个EventLoop，处理所有的I/O事件
    • EventLoop注册事件
    • EventLoop将事件派发给ChannelHandler
    • EventLoop安排进一步操作
    
关键技术
– 事件
    • 事件按照数据流向进行分类
    • 入站事件：连接激活/数据读取/……
    • 出站事件：打开到远程连接/写数据/……
– 事件处理ChannelHandler
    • Channel通道发生数据或状态改变
    • EventLoop会将事件分类，并调用ChannelHandler的回调函数
    • 程序员需要实现ChannelHandler内的回调函数
    • ChannelInboundHandler/ChannelOutboundHandler    
    
• 关键技术
– ChannelHandler工作模式：责任链
    • 责任链模式
        – 将请求的接收者连成一条链
        – 在链上传递请求，直到有一个接收者处理该请求
        – 避免请求者和接收者的耦合
    • ChannelHandler可以有多个，依次进行调用
    • ChannelPipeline作为容器，承载多个ChannelHandler
– ByteBuf
    • 强大的字节容器，提供丰富API进行操作    
    
Mina
– Apache Mina, http://mina.apache.org/
– NIO 框架库
– 事件驱动的异步网络通讯
– 和Netty的区别https://stackoverflow.com/questions/1637752/netty-vs-apachemina    
```

### 9	邮件基础知识

```
邮件基本知识
• 邮件：一封信，包括发件人/收件人/文本/图片/附件等
• 邮件客户端
• 邮件服务端
    – 发送邮件服务器
    – 接受邮件服务器
    
• 邮件客户端
    – Foxmail
    – OutLook(Express, Microsoft Outlook)
    – Thunderbird (linux平台)
• 邮件服务端
    – Microsoft Exchange Server
    – IBM Lotus Notes
    – SendMail, Qmail, James    
    
主要协议(发送端口25, 接收端口110)
– 发送, SMTP, Simple Mail Transfer Protocol
– 接收, Pop3, Post Office Protocol 3, (POP)
– 接收, IMAP, Internet Message Access Protocol, IMAP4
    • 摘要浏览
    • 选择下载附件
    • 多文件夹
    • 网络硬盘    
    
邮件格式
    – RFC822邮件格式：邮件头，文字邮件正文
    – MIME(MultiPurpose Internet Mail Extension) 图片/音频/视频等等
• 邮件编码
    – 纯英文邮件, ASCII编码，7Bit
    – 8Bit编码
    – BASE64，6个bit, A-Za-z0-9+/
    – Quoted-printable,对每个非ASCII字符用“=“加上这个字符的十六进制编码.
    
• 邮件中继：通过别人的邮件服务器(中继服务器)将你的邮件系统的邮件送到目标地址
• 垃圾邮件(Spam)    
```

### 10	Java Mail 编程

```
Java Mail 服务器配置
• 邮件服务器支持
    – 需要在邮件服务内设置，可以查看相关邮件帮助
    – 需要知道pop服务器和smtp服务器信息
```

```
Java Mail工具包
• javax.mail 包和javax.mail.internet 包
    – https://javaee.github.io/javamail，目前1.6.2
    – mvn dependency
```

```
Java Mail核心API
关键类
    – Session: 邮件会话和HttpSession不同
    – Store: 邮件存储空间
    – Folder: 邮件文件夹
    – Message: 电子邮件
    – Address: 邮件地址
    – Transport: 发送协议类
```

