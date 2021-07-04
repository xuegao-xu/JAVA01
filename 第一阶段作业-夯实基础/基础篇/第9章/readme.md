# 第九章学习笔记

### 1	Java 异常分类

**异常**

- 指程序不正常的行为或者状态 ，比如：
  - int a = 5/0;
  - 数组越界访问
  - 读取文件，结果该文件不存在

**异常处理（目的）**

- 让程序返回到安全状态
- 使程序允许用户保存结果，并以适当方式关闭程序



![](https://image-1306027094.cos.ap-nanjing.myqcloud.com/md/l111.jpg)

**异常分类**

- Throwable: 所有错误的祖先
- Error: 系统内部错误或者资源耗尽。程序员不管  (指程序相关)
- Exception: 程序有关的异常，需要重点关注   (系统相关)
  - RuntimeException: 程序自身的错误(也叫Unchecked Exception)
    - 例：5/0 ，空指针，数组越界
  - 非 RuntimeException ：外界相关的错误（也叫Checked Exception)编译器会辅助检查 checked 异常
  - IOException是非 RuntimeException最主要的一个代表）
    - 打开一个不存在文件
    - 加载一个不存在的类

**RuntimeException的子类(也叫Unchecked Exception)** 

程序员必须处理，以预防为主。编译器不关心此类异常，也不会辅助检查，需要程序员自己管的异常，包括 Error子类和RuntimeException 子类。Error的子类，可以不用处理。

**非 RuntimeException 的 Exception 的子类(也叫Checked Exception )**

程序员必须处理，以发生后处理为主，编译器会辅助检查的异常

**注意：**  编译器会检查程序是否为 checked exception 配置了处理。如果没有处理，会报错。





### 2	Java 异常处理

**异常处理(目的)**

- 允许用户及时保存结果
- 抓住异常，分析异常内容
- 控制程序返回到安全状态

**try catch finally: 一种保护代码正常运行的机制。**

**异常结构**

- try…catch(catch 可以有多个，下同)
- try…catch…finally
- try…finally

**注意：**try 必须有， catch 和 finally 至少要有一个



**try:**

正常业务逻辑代码。

**catch:** 

当 try 发生异常，将执行 catch 代码。若无异常，绕之。

**finally:** 

当 try 或 catch 执行结束后，必须要执行 finally 



**注意：**

- catch 块可以有多个，每个有不同的入口形参。

- 当已发生的异常和某一个 catch 块中的形参类型一致，那么将
  执行该catch 块 中的代码 

- 如果没有一个匹配， catch 也不会被触发。最后都进入 finally 块

- 进入 catch 块后，并不会返回到 try 发生异常的 位置 ，也不会执行后续的 catch 块，一个异常只能进入一个catch块 

- catch 块的异常匹配是从上而下进行匹配的。

- 所以一般是将小的异常写在前面，而一些大（宽泛）的异常则写在末尾。

- try 结构中，如果有 finally 块， finally 肯定会被执行 。

- try catch finally 每个模块里面也会发生异常，所以也可以在内部继续写一个完整的 try 结构 。（不允许嵌套try-catch-finally 结构，如一个try里写多个catch）

  ```
  例：try {
  	try-catch-finally 结构
  }
  catch（）{
  	try-catch-finally 结构
  }
  finally {
  	try-catch-finally 结构
  }
  ```

- 方法里可能异常的语句，如果不处理，那么可以使用throws 来声明异常。

- 调用带有 throws 异常（ checked exception ）的方法时，要么处理这些异常，或者再次向外 throws ，直到 main 函数为止。

- 如果父类的方法抛出多个异常，那么重写的子类方法必须抛出那些相同的异常或者异常的子集，不能抛出新的异常，也不能超出父类方法声明的异常范围。



### 3	自定义异常

- 自定义异常，需要继承 Exception 类或其子类。
  - 继承自 Exception ，就变成 Checked Exception
  - 继承自 RuntimeException, 就变成 Unchecked Exception

- 自定义重点在构造函数
  - 调用父类 Exception 的 message 构造函数
  - 可以自定义自己的成员变量

- 在程序中采用 throw 主动抛出异常