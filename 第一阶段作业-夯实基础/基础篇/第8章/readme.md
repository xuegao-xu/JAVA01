# 第八章学习笔记

#### 1	Java类库概述

**Java类库**

- 包名以java 开始的包是Java 核心包(Java Core Package) 
- 包名以javax 开始的包是Java 扩展包(Java Extension Package) 



#### 2	数字相关类

**Java 数字类**

- 整数 Short, Int, Long
- 浮点数 Float, Double
- 大数类 BigInteger (大整数 ),BigDecimal (大浮点数)
  - BigInteger (大整数 )支持无限大的整数运算
  - BigDecimal (大浮点数)支持无限大的小数运算
- 随机数类 Random
  - nextInt() 返回一个随机 int
  - nextInt(int a) 返回一个 [0, 之间的随机 int
  - nextDouble() 返回一个 [ 之间 double
  - ints 方法批量返回随机数数组
- 工具类 Math

**java.lang.Math**

- 绝对值函数 abs
- 对数函数 log
- 比较函数 max 、 min
- 幂函数 pow
- 四舍五入函数 round 等
- 向下取整 floor
- 向上取整 ceil
- Math.random() 返回一个[0.0,1.0]之间 double



#### 3	字符串相关类

**String**

- Java 中使用频率最高的类
- 是一个不可变对象，加减操作性能较差
- 以下方法需要牢记： charAt , concat , contains, endsWith,equals ,
  equalsIgnoreCase , hashCode , indexOf length, matches, replace,
  replaceAll , split, startsWith , subString , valueOf



**可变字符串**

- StringBuffer （字符串加减，同步，性能好）
- StringBuilder （字符串加减，不同步，性能更好）
- StringBuffer/StringBuilder 方法一样，区别在同步，方法：append/insert/delete/replace/substring
- length 字符串实际大小， capacity 字符串占用空间大小
- trimToSize(): 去除空隙,将字符串存储压缩到实际大小,如有大量 append ,事先预估大小，再调用相应构造函数



#### 4	时间相关类

**java.util.Date( 基本废弃 Deprecated)**

- getTime() 返回自 1970.1.1以来的毫秒数

**Calendar 是目前程序中最常用的,但是是抽象类**

- Calendar gc=Calendar getInstance()
- Calendar gc= new GregorianCalendar();

**Calendar类**

- get(Field) 来获取时间中每个属性的值 注意 月份 0 11
- getTime() 返回相应的 Date 对象
- getTimeInMillis(), 返回自 1970 1 1 以来的毫秒数
- set(Field) 设置时间字段
- add(field, amount) 根据指定字段增加 减少时间
- roll(field, amount) 根据指定字段增加 减少时间 但不影响上一级
  的时间段

**java time 包主要类**

- LocalDate ：日期类
- LocalTime ：时间类(时分秒-纳秒)
- LocalDateTime：LocalDate+LocalTime
- Instant 时间戳



#### 5	格式化(Format)相关类

**三种格式化**

- 数字格式化	NumberFormat ：数字格式化 抽象类
- 字符串格式化  MessageFormat ：字符串格式化
- 时间格式化   DateFormat ：日期/时间格式化 抽象类



**NumberFormat ：数字格式化 抽象类**

- 例如：将 1234567 格式化输出为 1,234,567

**MessageFormat ：字符串格式化**

- 支持多个参数-值对位复制文本
- 支持变量的自定义格式
- 例如将 ”Hello 1 根据变量值 格式化为 Hello World

**DateFormat ：时间格式化,抽象类**

- parse ：将字符串格式化为时间对象
- format ：将时间对象格式化为字符串
- 如将当前时间转为化 YYYY MM DD HH 24 MI SS 输出