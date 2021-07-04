# 第一章学习笔记

### 1	构建工具

**想要使用第三方库的函数API**

1.通过添加jar包

2.通过创建Maven工程

**构建工具功能**

- 自动帮程序员甄别和下载第三方库(jar)
- 完成整个项目编译(调用javac.exe)
- 完成整个项目单元测试流程(调用JUnit工具)
- 完成项目打包(jar/war等格式，调用jar.exe)

**当前主要的Java构建工具**
Maven, Gradle, Ivy, Buildr , Ant 等

```
Maven的构建过程
1.创建Maven Project
2.在mvn中央仓库(mvnrepository.com)中搜索Commons Math
3.将Apache Commons Math依赖文本加到项目pom.xml中
4.使用Apache Commons Math类进行编码
5.Maven编译和运行：右键项目->Run as ->Maven Build，在Maven build的Goals里输入clean package(第一次配置，后续不用配置)
6.Maven编译和运行：右键项目->Run as ->Maven Build，编译成功，控制台输出Build Success就表示成功了
7.程序运行：右键GcdTest，选择Run as -> Java Application
```



### 2	Maven基础概念和实战

**检查是否正确安装Maven**

```
–File菜单->New->Other，在弹出窗口中查找是否有Maven项目
–Window菜单->Preferences, 在弹出窗口左侧是否有Maven子菜单
–Help菜单->Install New Software, 在Work with窗口中输入Maven，
下面显示出All items are installed
```

**Maven开发流程**

```
–新建Maven项目
–在中央仓库查找第三方jar的依赖文本
–拷贝依赖文本至项目的pom.xml
–执行maven build，编译/构建整个项目
```

- Maven是一个软件，http://maven.apache.org/ 下载
- Maven也有一个中心仓库，https://mvnrepository.com/(包含很多第三方软件,也有很多第三方的Maven仓库
- Maven是一个构建工具，自动下载中心仓库的jar文件，存在本地用户的.m2文件夹进行管理，编译、测试、运行、和打包发布Java项目。

**XML格式**

```
• 包含了项目信息、依赖信息、构建信息
• 构件信息(artifact)
–groupId：组织
–artifactId：产品名称
–version：版本
```

**基本目录结构**

```
–src
	• main
		–java/ 存放java文件
		–resources/ 存放程序资源文件
	• test/
		–java/ 存放测试程序
		–resources/ 存放测试程序资源文件
–pom.xml
```

**生命周期**

- 清理
- 编译
- 测试
- 打包
- 安装
- 部署
