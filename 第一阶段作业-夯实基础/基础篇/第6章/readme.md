# 第六章学习笔记

### 1	static

- static 静态的， Java 中特殊的关键字
- Java 中的 static 关键字 可作用在
  - 变量
  - 方法
  - 类
  - 匿名方法块

**静态变量**

- 静态变量是类的共有成员

- static 变量只依赖于类存在（通过类即可访问），不依赖于对象实例存在。即可以通过(类名.静态变量名)即可访问。
- 所有的对象实例的值都共享存储在一个共同的空间（栈）。

**static 方法**

- 静态方法也无需通过对象来引用，而通过类名可以直接引用。
- 在静态方法中，只能使用静态变量，不能使用非静态变量。
- 静态方法禁止引用非静态方法，非静态方法既可以引用静态方法，也可以使用非静态方法。

**static 块**

- 只在类第一次被加载时调用 。
- 换句话说，在程序运行期间，这段代码只运行一次 。
- 执行顺序： static 块 > 匿名块 > 构造函数。

**小结：**

- static 变量：不管有多少个对象，内存中只有一份
- static 方法：可以用类名直接引用，无需 new 对象来引用
- static 块： static 块只执行一次，并且 static 块 匿名块 构造函数



### 2	单例模式

- 单例模式(Singleton)，又名单态模式
- 保证一个类在整个程序运行过程中,类的内存空间中有且只有一个对象
- 采用 static 来共享对象实例
- 采用 private 构造函数，防止外界 new 操作。
- 单例模式是 GoF 的 23 种设计模式中经典的一种，属于创建型模式类型。
- 设计模式：在软件开发过程中，经过验证的，用于解决在特定环境下的、重复出现的、特定问题的解决方案。



### 3	final

- Java 的 final 关键字同样可以用来修饰
  - 类
  - 方法
  - 变量（成员变量，局部变量）
- final 的 类，不能被继承
- 父类中如果有 final 的方法，子类中不能改写此方法
- final 的变量，不能再次赋值。
  - 如果是基本型别的变量，不能修改其值；
  - 如果是对象实例，那么不能修改其指针 但是可以修改对象内部的值 ; 



### 4	常量设计

**常量：一种不会修改的变量**

- Java 没有 constant 关键字
- 不能修改， final
- 不会修改 只读 只要一份， static
- 方便访问(public)

**Java 中的常量**

- 格式：public static final 常量类型 常量名
- 建议变量名字全大写，以连字符相连，如 NUMBER

**特殊的常量：接口内定义的变量默认是常量**

### 5	常量池

- Java为很多基本类型的包装类/字符串都建立常量池
- 常量池：相同的值只存储一份，节省内存，共享访问
- 基本类型的包装类
  - Boolean，Byte，Short，Integer，Long，Character，Float，Double
  - Boolean： true, false
  - Byte, Character : \u0000~\u007f (0—127)
  - Short, Int, Long：-128~127
  - Float，Double：没有缓存(常量池)

**基本类型的包装类和字符串有两种创建方式**

- 常量式(字面量)赋值创建，放在栈内存(将被常量化)
  - Integer a = 10;
  - String b = “abc”;

- new对象进行创建，放在堆内存(不会常量化)
  - Integer c = new Integer(10);
  - String d = new String(“abc”);   

（这两种创建方式将会导致创建的对象存放的位置不同）

- 基本类型和包装类比较，将对包装类自动拆箱
- 对象之间比较，比较地址
- 加法+会自动拆箱常量赋值(堆内存)和new创建(栈内存)不是同一个对象
- 编译器只会优化确定的字符串，并缓存
- Java 为 Boolean, Byte, Character, Short, Int, Long, String 的常量
  赋值建立常量池，没有包括 Float 和 Double



### 6	不可变对象

**不可变对象 (Immutable Object)**

- 一旦创建，这个对象（状态 值）不能被更改
- 其内在的成员变量的值就不能修改了。

**典型的不可变对象**

- 八个基本型别的包装类的对象
- String BigInteger 和 BigDecimal 等的对象

**可变对象 (Mutable Object)**

- 普通对象

**不可变对象，也是传指针(引用）**

**由于不可变，临时变量指向新内存，外部实参的指针不改动**



**如何创建不可变对象**

- immutable 对象是不可改变，有改变，请 clone/new 一个对象进行修改
- 所有的属性都是 final 和 private 的
- 不提供 setter 方法
- 类是 final 的，或者所有的方法都是 final
- 类中包含 mutable 对象，那么返回拷贝需要深度 clone



**不可变对象 (Immutable Object）优点**

- 只读，线程安全
- 并发读，提高性能
- 可以重复使用

**缺点**

- 制造垃圾，浪费空间

### 7	字符串

- 字符串是 Java 使用最多的类，是一种典型的不可变对象
- String 定义有 2 种
  - String a = “abc”; // 常量赋值，栈分配内
  - String b = new String(“abc”); //new 对象，堆分配内存
- 字符串内容比较： equals 方法
- 是否指向同一个对象：指针比较（==）



**Java 常量池 (Constant Pool）**

- 保存在编译期间就已经确定的数据
- 是一块特殊的内存
- 相同的常量字符串只存储一份，节省内存，共享访问



**字符串的加法**

- String a=“abc”;
- a = a+“def”; // 由于 String 不可修改，效率差
- 使用 StringBuffer/StringBuilder 类的 append 方法进行修改
- StringBuffer/StringBuilder 的对象都是可变对象
- StringBuffer( 同步，线程安全，修改快速）
- StringBuilder( 不同步，线程不安全，修改更快）

**不可变对象提高读效率**

**不可变对象设计的方法**

**字符串 append 操作速度：StringBuilder>StringBuffer>String**