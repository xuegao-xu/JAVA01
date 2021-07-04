# 第七章学习笔记

## 1	数据库和SQL

```
DB
• DB： Database = Data + Base
• 数据库：数据+库，存放数据的库(场所)
• 数据：规范、半规范、不规范数据
• 库
    – 一个空间，一个场所
    – 停车场、钱包、教室
    – 文件
    
DB：保存数据的地方
    – 数据安全
    – 存取效率
    – 性价比高
    
DB分类
• DB（文件集合，类似.doc,.docx文件）
• DBMS: Database Management System（类似Office/WPS）
    – 操纵和管理数据库的软件，可建立、使用和维护数据库
• DB种类
    – 文本文件/二进制文件
    – Xls文件
    – Access
    – Mysql 
	– SQL Server
	– Oracle/DB2
	– SQLite (免费,手机上使用)
```

```
表
• 表：table, 实体
    – 列：列、属性、字段
    – 行：记录、元组tuple，数据
• 数据值域：数据的取值范围
• 字段类型
    – int :整数-2147483648~2147483647，4个字节
    – double：小数，8个字节
    – datetime ：时间，7个字节
    – varchar：字符串，可变字节
```

```
SQL
• 结构化查询语言(Structured Query Language)，简称SQL
    – 是一种特殊目的的编程语言，是一种数据库查询和程序设计语言，用于存取数据以及查询、更新和管理关系数据库系统；同时也是数据库脚本文件的扩展名。
• SQL标准
    – SQL-86/SQL-89/SQL-92
    – SQL:1999/ SQL:2003/ SQL:2008/ SQL:2011/ SQL:2016
    – 基础的部分，所有标准都一样
    – 标准仅仅是标准，每个厂商的数据库实现可能有一些不一致
    
SQL语句
	-create table t1(a int, b varchar(20));
    -insert into t1(a,b) values(1,’abc’);
    -select a from t1;
    -select a,b from t1 where a > 1;
    -delete from t1 where a = 10 and b=‘ab’;
    -update t1 set a=2, b = ‘cd’ where a=1 and b=‘ab’;
    -drop table t1;
```

## 2	JDBC基本操作

```
JDBC
• Java和数据库是平行的两套系统
• Java和数据库的连接方法
    – Native API (不能跨平台)
    – ODBC/JDBC-ODBC (效率很差，也无法跨平台)
    – JDBC （主流）: Java Database Connectivity
        • JDBC 1, JDK 1.1
        • JDBC 2 JDK 1.3~JDK1.4
        • JDBC 3 JDK 5
        • JDBC 4 JDK 6, (JDK 7, JDBC4.1; JDK8, JDBC4.2)
        
Java SQL 操作类库
• java.sql.*, javax.sql.*; 这2个包只是接口类
• 根据数据库版本和JDBC版本合理选择
• 一般数据库发行包都会提供jar包，同时也要注意区分32位和64位（数据库分32/64位， JDK也分32/64位）。
• 连接字符串（样例）
    – jdbc:oracle:thin:@127.0.0.1:1521:dbname
    – jdbc:mysql://localhost:3306/mydb
    – jdbc:sqlserver://localhost:1433; DatabaseName=dbname       
```

```
Java连接数据库操作步骤
• 构建连接（搭桥）
    – 注册驱动，寻找材质, class.forName(“…”);
    – 确定对岸目标, 建桥Connection
• 执行操作（派个人过桥, 提着篮子，去拿数据）
    – Statement (执行者)
    – ResultSet(结果集)
• 释放连接（拆桥） connection.close();
```

```
Statement
• Statement 执行者类
    – 使用executeQuery()执行select语句，返回结果放在ResultSet
    – 使用executeUpdate()执行insert/update/delete，返回修改的行数
    – 一个Statement对象一次只能执行一个命令
• ResultSet 结果对象
    – next() 判断是否还有下一条记录
    – getInt/getString/getDouble
• 可以按索引位置，可以按照列名
```

**注意**

- ResultSet不能多个做笛卡尔积连接
- ResultSet最好不要超过百条，否则极其影响性能
- ResultSet也不是一口气加载所有的select结果数据
- Connection 很昂贵，需要及时close
- Connection所用的jar包和数据库要匹配



3	JDBC高级操作

```
事务
• 数据库事务，Database Transaction。
• 作为单个逻辑工作单元执行的一系列操作，要么完全地执行，要么完全地不执行。
• 事务，必须满足所谓的ACID（原子性、一致性、隔离性和持久性）属性。
• 事务是数据库运行中的逻辑工作单位，由DBMS中的事务管理子系统负责事务的处理。
```

```
JDBC事务
• 关闭自动提交，实现多语句同一事务：
• connection.setAutoCommit(false);
• connection.commit(); 提交事务
• connection.rollback(); 回滚事务
• 保存点机制
    – connection.setSavepoint()
    – connection.rollback(Savepoint)
```

```
PreparedStatement
• 拼接SQL字符串很危险
• Java提供PreparedStatement，更为安全执行SQL
• 和Statement区别是使用“?” 代替字符串拼接
• 使用setXXX(int,Object)的函数来实现对于？的替换
    – 注：不需要考虑字符串的两侧单引号
    – 参数赋值，清晰明了，拒绝拼接错误
• 提供addBatch批量更新功能
• Select语句一样用ResultSet接收结果
• 使用PreparedStatement的好处：
    – 防止注入攻击
    – 防止繁琐的字符串拼接和错误
    – 直接设置对象而不需要转换为字符串
– PreparedStatement使用预编译速度相对Statement快很多
```

```
ResultSetMetaData
• ResultSet可以用来承载所有的select语句返回的结果集
• ResultSetMetaData来获取ResultSet返回的属性（如，每一行的名字类型等）
    – getColumnCount()，返回结果的列数
    – getColumnClassName(i)，返回第i列的数据的Java类名
    – getColumnTypeName(i)，返回第i列的数据库类型名称
    – getColumnType(i)，返回第i列的SQL类型
• 使用ResultSetMetaData解析ResultSet
```

4	数据库连接池

```
享元模式
• Connection是Java和数据库两个平行系统的桥梁
• 桥梁构建不易，成本很高，单次使用成本昂贵
• 运用共享技术来实现数据库连接池(享元模式)
    – 降低系统中数据库连接Connection对象的数量
    – 降低数据库服务器的连接响应消耗
    – 提高Connection获取的响应速度
• 享元模式, Flyweight Pattern
    – 经典23个设计模式的一种，属于结构型模式。
    – 一个系统中存在大量的相同的对象，
       -由于这类对象的大量使用，会造成系统内存的耗费，
       -可以使用享元模式来减少系统中对象的数量。    
```

```
数据库连接池
• 理解池Pool的概念
    – 初始数、最大数、增量、超时时间等参数。
• 常用的数据库连接池
    – DBCP (Apache, http://commons.apache.org/，性能较差)
    – C3P0 (https://www.mchange.com/projects/c3p0/)
    – Druid (Alibaba, https://github.com/alibaba/druid)
```

```
C3P0连接池
    <default-config> <!-- 默认配置-->
    <property
    name="driverClass">com.mysql.jdbc.Driver</property>
    <property
 name="jdbcUrl">jdbc:mysql://localhost:3306/test
 	</property>
    <property name="user">root</property>
    <property name="password">123456</property>
    <property name="initialPoolSize">5</property>
    <property name="maxPoolSize">20</property>
    </default-config>
    
• driverClass 驱动class，这里为mysql的驱动
• jdbcUrl jdbc链接
• user password数据库用户名密码
• initialPoolSize 初始数量：一开始创建多少条链接
• maxPoolSize 最大数：最多有多少条链接
• acquireIncrement 增量：用完每次增加多少个
• maxIdleTime最大空闲时间：超出的链接会被抛弃    
```

