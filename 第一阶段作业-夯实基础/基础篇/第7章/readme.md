# 第七章学习笔记

### 1	package

- Java放置在同一个目录下面的类，类之间的相互调用无需显式声明调用。
- 同一个目录下，两个类的名字不能相同
- 类全称(例:cn.edu.ecnu.PackageExample)短名称(PackageExample)
- 引用类的时候，必须采用全称引用；程序正文可以用短名称
- 包名package name尽量唯一
- 因为域名是唯一的，因此常用域名做包名
- 域名逆序：cn.edu.ecnu，范围通常从大到小
- 类的完整名字：包名+类名，cn.edu.ecnu.PackageExample
- 包名:和目录层次一样(cn.com.test.Man.java 必须放在cn\com\test目录下)
- Java通过包(package)来分开类，package必须和目录层次一样(例：cn\edu\ecnu)



### 2	import

**import规则**

- import必须全部放在package之后，类定义之前，多个import的顺序无关。
- 可以用来引入一个目录下的所有类，比如import java.lang.*;

```
此意思是引入java.lang下面所有的类文件，当不包括java.lang下面所有的子目录文件，即并不包括java.lang.reflect.*,换句话说，不能递归包含各个目录下的文件。
```

- import 尽量精确，不推荐用，以免新增的同名新程序会使得老程序报错
- Java通过引用(import)来导入类，import类尽量准确



### 3	jar 文件导出和导入

**jar文件**

一种扩展名为jar的文件，是Java所特有的一种文件格式，用于可执行程序文件的传播。jar文件实际上是一组class文件的压缩包

**jar文件优势**

- jar文件可以包括多个class，比多层目录更加简洁实用
- jar文件经过压缩，只有一个文件，在网络下载和传播方面，更具有优势
- jar文件只包括class，而没有包含java文件，在保护源文件知识版权方面，能够可以起到更好的作用
- 将多个class文件压缩成jar文件（只有一个文件），可以规定给一个版本号，更容易进行版本控制

**导出**

在Eclipse中选中项目，点击顶部菜单File->Export，选中Java>Jar File, 然后点击Next，然后勾选要导出的文件，选择导出地址，最后点击finish。



**导入**

新建项目，然后选中项目，右键选Properties。左侧菜单栏选中Java Build Path, 在右侧标签栏选中Libraries。点击Add External JARs, 选中要导入的jar包，点击Apply and Close按钮，即完成添加过程。

### 4	Java访问权限

**Java访问权限有四种**

- private: 私有的，只能本类访问
- default(通常忽略不写)：同一个包内访问
- protected：同一个包，子类均可以访问
- public：公开的，所有类都可以访问



**使用范围**

- 四种都可以用来修饰成员变量、成员方法、构造函数

- default和public可以修饰类

  ​	

|           | 同一个类 | 同一个包 | 不同包的子类 | 不同包的非子类 |
| --------- | -------- | -------- | ------------ | -------------- |
| private   | ✓        |          |              |                |
| default   | ✓        | ✓        |              |                |
| protected | ✓        | ✓        | ✓            |                |
| public    | ✓        | ✓        | ✓            | ✓              |

**推荐成员变量都是private，成员方法都是public**

