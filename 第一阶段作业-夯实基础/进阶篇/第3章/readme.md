# 第三章学习笔记

### 1	Java字符编码

```
字符编码
– 字符：0，a，我，①，の，……
– 计算机只用0和1, 1 bit(0 或者1)
– ASCII码
• (American Standard Code for Information Interchange)
• 美国信息交换标准代码，奠定计算机编码基础
• 用一个字节(1 Byte=8 bits) 来存储a-z,A-Z,0-9和一些常用符号
• 用于显示英语及西欧语言
• 回车键(13, 00001101), 0(48, 00110000), A(65,01000001), a(97, 01100001)
```



```
字符编码
– ASCII编码采用1 Byte，8 bits，最多256个字符
– ASCII无法适应其他地方，如汉字数量有十几万
– 扩展编码(加字节)
• ISO8859(1-15) 西欧语言
• GB2132， GBK，GB18030 ASCII+中文
• Big5 ASCII + 繁体中文
• Shift_JIS ASCII+日文
– Unicode 编码
```



```
中文编码
– GB2312，1980年发布，7445个字符(6763个简体字)，包括拉丁字
母、希腊字母、日文平假名及片假名字母、俄语西里尔字母等682个符号
– GBK，1995年发布，21886 个汉字和符号，包括GB2312和Big 5
– GB18030(2000, 2005两个版本)，70244个汉字和符号，包括GBK和GB2312
– Big 5，繁体中文
– GB18030 > GBK > GB2312
```

```
Unicode(字符集)
– 目标：不断扩充，存储全世界所有的字符
• 编码方案
– UTF-8，兼容ASCII，变长(1-4个字节存储字符)，经济，方便传输
– UTF-16，用变长(2-4个字节)来存储所有字符
– UTF-32，用32bits(4个字节)存储所有字符
```



```
ANSI编码
– Windows上非Unicode的默认编码(Windows code pages)
– 在简体中文Windows操作系统中，ANSI 编码代表GBK 编码
– 在繁体中文Windows操作系统中，ANSI编码代表Big5
– 记事本默认是采用ANSI保存
– ANSI编码文件不能在兼容使用
```



```
源文件编码：采用UTF-8编码
– Eclipse，右键java文件，属性，resource，选择UTF-8
– Eclipse，右键项目，属性，resource，选择UTF-8
• 程序内部采用UTF-16编码存储所有字符(不是程序员控制)
• 和外界(文本文件)的输入输出尽量采用UTF-8编码
– 不能使用一种编码写入，换另外一种编码读取
```



### 2	Java国际化编程

```
国际化编程
• Internationalization, 缩写为i18n.
• 多语言版本的软件
就是一套软件，多个语言包
可以根据语言设定，可以切换显示文本
```

```
Java是第一个设计成支持国际化的编程语言
– java.util.ResourceBundle 用于加载一个语言_国家语言包
– java.util.Locale 定义一个语言_国家
– java.text.MessageFormat 用于格式化带占位符的字符串
– java.text.NumberFormat 用于格式化数字/金额
– java.text.DateFormat用于格式化日期时间
– java.time.format.DateTimeFormatter用于格式化日期时间
```

```
Locale类
• Locale(zh_CN, en_US,…)
– 语言，zh，en等
– 国家/地区，CN，US等
– 其他变量(variant)(几乎不用)
• Locale方法
– getAvailableLocales()返回所有的可用Locale
– getDefault()返回默认的Locale
```

```
语言文件
– 一个Properties文件(参见《Java核心技术》第十章)
– 包含K-V对，每行一个K-V，例如：age=20
– 存储文件必须是ASCII码文件
– 如果是ASCII以外的文字，必须用Unicode的表示\uxxxx
– 可以采用native2ascii.exe (%JAVA_HOME%\bin目录下)进行转码
– 命名规则
• 包名+语言+国家地区.properties, (语言和国家地区可选)
• message.properties
• message_zh.properties
• message_zh_CN.properties
```

```
ResourceBundle
– 根据Locale要求，加载语言文件(Properties文件)
– 存储语言集合中所有的K-V对
– getString(String key) 返回所对应的value

ResourceBundle 根据key找value的查找路径
– 包名_当前Locale语言_当前Locale国家地区_当前Locale变量(variant)
– 包名_当前Locale语言_当前Locale国家地区
– 包名_当前Locale语言
– 包名_默认Locale语言_默认Locale国家地区_默认Locale变量(variant)
– 包名_默认Locale语言_默认Locale国家地区
– 包名_默认Locale语言
```

```
日期/时间国际化
– DateTimeFormatter和Locale的结合
数字/金额国际化
– NumberFormat和Locale结合
```



### 3	Java高级字符串处理

```
正则表达式(Regular Expression)
–规则表达式，计算机科学的一个基础概念
–用事先定义好的一些特定字符、及这些特定字符的组合，组成一个“规则字符串”
–^[A-Za-z]+$， 代表着一个字符串，只能由26英文字母组成
–作用
  • 测试字符串内的模式
  • 识别/替换文本
  • 提取文本
```

**正则表达式独立于特定语言（Java, Perl, Python, PHP…）**

**正则表达式的匹配模板**

- 定界符
- 原子
- 特殊功能字符(元字符)
- 模式修正符

```
Java的正则表达式
• java.util.regex包
–Pattern 正则表达式的编译表示
    • compile 编译一个正则表达式为Pattern对象
    • matcher 用Pattern对象匹配一个字符串，返回匹配结果
–Matcher
    • Index Methods(位置方法) // start(), start(int group), end(), end(int group)
    • Study Methods(查找方法) // lookingAt(), find(), find(int start), matches()
    • Replacement Methods(替换方法) //replaceAll(String replacement)
```

**其他字符串操作**

- 字符串和集合互转
  - [1,2,3], “1,2,3”
- 字符串转义
  - 对关键字符转义
- 变量名字格式化
  - 名字驼峰命名
- 字符串输入流
  - 将字符串转为一个输入流
  - 输入流可以定义为Scanner，这是Online Judge的实现原理