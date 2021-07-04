# 第十一章学习笔记

### 1	文件系统及Java文件基本操作

- 文件系统是由OS(操作系统)管理的
- 文件系统和Java进程是平行的，并列的两套系统
- 文件系统是由文件夹和文件递归组合而成
- 文件目录分隔符
  - Linux/Unix 用/隔开
  - Windows用\隔开，涉及到转义，在程序中需用/或双反斜杠代替
- 文件包括文件里面的内容和文件基本属性
- 文件基本属性：名称、大小、扩展名、修改时间等



```
java.io.File是文件和目录的重要类(JDK6及以前是唯一的)
目录一般也使用File类进行表示
• File类与OS无关，但会受到OS的权限限制
• 常用方法
createNewFile,delete,exists, getAbsolutePath, getName,
getParent,getPath, isDirectory, isFile, length, listFiles, mkdir, mkdirs
注意：File不涉及到具体的文件内容，只涉及属性

Java 7提出的NIO包，提出新的文件系统类
Path, Files, DirectoryStream, FileVisitor,FileSystem等方法是java.io.File的有益补充
• 文件复制和移动
• 文件相对路径
• 递归遍历目录
• 递归删除目录
```



### 2	Java io 包概述

- Java文件处理类都在java.io包中

- Java读写文件，只能以(数据)流的形式进行读写
- java.io包中
  - 节点类：直接对文件进行读写
  - 包装类
    - 转化类：字节/字符/数据类型的转化类
    - 装饰类：装饰节点类

```
字节：byte, 8bit, 最基础的存储单位

字符：a, 10000, 我，の

数据类型: 3， 5.25，abcdef

文件是以字节保存，因此程序将变量保存到文件需要转化
```

```
转换类：字符到字节之间的转化
–InputStreamReader：文件读取时字节，转化为Java能理解的字符
–OutputStreamWriter：Java将字符转化为字节输入到文件中

装饰类：装饰节点类
–DataInputStream,DataOutputStream: 封装数据流
–BufferedInputStream,BufferOutputStream：缓存字节流
–BufferedReader, BufferedWriter：缓存字符流
```



### 3	文本文件读写

**文件类型**

- 一般文本文件(若干行字符构成的文件)，如txt等
- 一般二进制文件，如数据文件dat
- 带特殊格式的文本文件，如xml等
- 带特殊格式二进制文件，如doc,ppt等

**文件是数据的一个容器(口袋)**

**文件可以存放大量的数据**

**文件很大，注定Java只能以流形式依次处理**



**从Java角度理解(读写)**

- 输出：数据从Java到文件中，写操作
- 输入：数据从文件到Java中，读操作

**文本文件读写**

- 输出文本字符到文件中
- 从文件中读取文本字符串



**写文件**

```
–先创建文件，写入数据，关闭文件
–FileOutputStream, OutputStreamWriter, BufferedWriter
–BufferWriter
    • write
    • newLine
–使用try-resource 语句，自动关闭资源
–关闭最外层的数据流，将会把其上所有的数据流关闭
```

**读文件**

```
–先打开文件，逐行读入数据，关闭文件
–FileInputStream, InputStreamWriter, BufferedReader
–BufferReader
    • readLine
–使用try-resource 语句，自动关闭资源
–关闭最外层的数据流，将会把其上所有的数据流关闭
```



### 4	二进制文件读写

**二进制文件**

- 狭义上说，采用字节编码，非字符编码的文件
- 广义上说，一切文件都是二进制文件
- 用记事本等无法打开/阅读

**二进制文件读写**

- 输出数据到文件中
- 从文件中读取数据



**写文件**

```
–先创建文件，写入数据，关闭文件
–FileOutputStream, BufferedOutputStream，DataOutputStream
–DataOutputStream
    • flush
	•write/writeBoolean/writeByte/writeChars/writeDouble/writeInt/WriteUTF/…
–使用try-resource 语句，自动关闭资源
–关闭最外层的数据流，将会把其上所有的数据流关闭
```

**读文件**

```
–先打开文件，读入数据，关闭文件
–FileInputStream, BufferedInputStream，DataInputStream
–DataInputStream
	• read/readBoolean/readChar/readDouble/readFloat/readInt/readUTF/…
–使用try-resource 语句，自动关闭资源
–关闭最外层的数据流，将会把其上所有的数据流关闭
```



### 5	Zip文件读写

```
压缩包：zip, rar, gz, ……
• Java zip 包支持Zip和Gzip包的压缩和解压
• zip文件操作类: java.util.zip包中
    –java.io.InputStream, java.io.OutputStream的子类
    –ZipInputStream, ZipOutputSream 压缩文件输入/输出流
    –ZipEntry 压缩项
```



**压缩单个/多个zip文件**

```
–打开输出zip文件–添加一个ZipEntry–打开一个输入文件，读数据，向ZipEntry写数据，关闭输入文件–重复以上两个步骤，写入多个文件到zip文件中–关闭zip文件
```



**解压单个/多个zip文件**

```
–打开输入的zip文件–获取下一个ZipEntry–新建一个目标文件，从ZipEntry读取数据，向目标文件写入数据，-关闭目标文件–重复以上两个步骤，从zip包中读取数据到多个目标文件–关闭zip文件
```

