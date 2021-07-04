# 第十章学习笔记

### 1	数组

**数组：是一个存放多个数据的容器**

- 数据是同一种类型
- 所有的数据是线性规则排列
- 可通过位置索引来快速定位访问数据
- 需明确容器的长度

**Java数组定义和初始化**

```
数组定义：
1.int a[];(没有进行new操作，实际上是null，也不知道内存位置)
2.int[] b;(没有进行new操作，实际上是null，也不知道内存位置)

初始化；
1.int a[] = new int[]{1,2,3};
2.int b[] = {1,2,3};
注意：声明变量的时候没有分配内存，不需要指定大小
例：int a[5];
```



**数组索引**

- 数组的length属性标识数组的长度
- 从0开始，到length - 1
- int[] a = new int[5]; //从a[0]~a[4], 不是到a[5]; 但是a.length是5
- 数组不能越界访问，否则会报异常(ArrayIndexOutOfBoundsException)



**数组遍历（两种方法）**

```
第一种方法（原始for语句，需要自己控制索引位置）
for(int i=0;i<d.length;i++){
	System.out.println(d[i]);
}
```

```
第二种方法（新型for语句，不需要自己控制索引位置）
for(int e : d){
	System.out.println(e);
}
```

**多维数组**

- 数组的数组
- 存储是按照行存储原则

**多维数组（分为两种）**

```
第一种（规则数组）
int a[][] = new int[2][3];
第二种（不规则数组）
int b[][];
b = new int[3][];
b[0] = new int[3][];
b[0] = new int[3];
b[1] = new int[4];
b[2] = new int[5];
```

**多维数组遍历（两种方法）**

```
第一种（自己控制索引位置）
int k = 0;
for(int i=0;i<a.length;i++){
	for(int j = 0;j<a[i].length;j++){
	a[i][j] = ++k;
	}
}
第二种（不需要控制索引位置）
for(int[] items : a){
	for(int item : items){
		System.out.print(item + ", ");
	}
	System.out.println();(起换行作用)
}
```

### 2	JCF

**JCF 集合框架特点**

- 功能更强大
- 易于学习
- 接口和实现分离，多种设计模式设计更灵活
- 泛型设计

**JCF 主要的数据结构(实现类)**

- 列表 (List, ArrayList,LinkedList)
- 集合 (Set, HashSet, TreeSet,LinkedHashSet)
- 映射 (Map, HashMap, TreeMap,LinkedHashMap)

**JCF 主要的算法类**

- Arrays: 对数组进行查找和排序等操作
- Collections ：对 Collection 及其子类进行排序和查找操作



### 3	列表 List

**List：列表**

- 有序的Collection
- 允许重复元素
- {1，2，4，{5，2}，1，3}

**List 主要实现**

- ArrayList(非同步的)
- LinkedList(非同步)
- Vector(同步)



**ArrayList**

- 以数组实现的列表，不支持同步
  - List list = Collections.synchronizedList(new ArrayList(...));
- 利用索引位置可以快速定位访问
- 不适合指定位置的插入、删除操作
- 适合变动不大，主要用于查询的数据
- 和 Java 数组相比，其容量是可动态调整的
- ArrayList 在元素填满容器时会自动扩充容器大小的 50%

**LinkedList**

- 以双向链表实现的列表，不支持同步
  - List list = Collections.synchronizedList (new
- 可被当作堆栈、队列和双端队列进行操作
- 顺序访问高效，随机访问较差，中间插入和删除高效
- 适用于经常变化的数据



**Vector(同步)**

- 和ArrayList类似，可变数组实现的列表
- Vector同步，适合在多线程下使用
- 原先不属于JCF框架，属于Java最早的数据结构，性能较差
- 从JDK1.2开始，Vector被重写，并纳入到JCF,官方文档建议在非同步情况下，优先采用ArrayList

**同步采用Vector,非同步情况下，根据数据操作特点选取ArrayList/LinkedList**



### 4	集合 Set

**集合 Set**

- 确定性：对任意对象都能判定其是否属于某一个集合
- 互异性：集合内每个元素都是无差异的， 注意是内容差异
- 无序性：集合内的顺序无关



**Java 中的集合接口 Set**

- HashSet：基于散列函数的集合，无序，不支持同步
- TreeSet：基于树结构的集合，可排序的，不支持同步
- LinkedHashSet：基于散列函数和双向链表的集合，可排序的，不
  支持同步



**HashSet**

- 基于 HashMap 实现的，可以容纳 null 元素 , 不支持同步

  - ```
    Set s = Collections.synchronizedSet(new HashSet(...));
    ```

- add 添加一个元素

- clear 清除整个 HashSet

- contains 判定是否包含一个元素

- remove 删除一个元素 size 大小

- retainAll 计算两个集合交集



**LinkedHashSet**

- 继承 HashSet ，也是基于 HashMap 实现的，可以容纳 null 元素

- 不支持同步

  - ```
    Set s = Collections.synchronizedSet(new LinkedHashSet(...));
    ```

  

- 方法和 HashSet 基本一致

  - ```
    add 添加一个元素
    clear 清除整个 HashSet
    contains 判定是否包含一个元素
    remove 删除一个元素 size 大小
    ```

    

- 通过一个双向链表维护插入顺序



**TreeSet**

- 基于 TreeMap 实现的， 不可以容纳 null 元素， 不支持同步

  - ```
    SortedSet s = Collections.synchronizedSortedSet(new TreeSet(...));
    ```

    

- add 添加一个元素

- clear 清除整个 TreeSet

- contains 判定是否包含一个元素

- remove 删除一个元素 size 大小

- 可以根据 compareTo 方法或指定 Comparator 排序



**HashSet, LinkedHashSet, TreeSet 的元素都只能是对象**

**HashSet 和 LinkedHashSet 判定元素重复的原则**

- 判定两个元素的 hashCode 返回值是否相同，若不同，返回 false
- 若两者 hashCode 相同，判定 equals 方法，若不同，返回 false ；否则
  返回 true 
- hashCode 和 equals 方法是所有类都有的，因为 Object 类有

**TreeSet 判定元素重复的原则**

- 需要元素继承自 Comparable 接口
- 比较两个元素的 compareTo 方法



### 5	映射 Map

**Map映射**

- 数学定义：两个集合之间的元素对应关系。
- 一个输入对应到一个输出
  {1，张三}，{2，李四}，{Key，Value}，也叫键值对，K-V对

 **Java中Map**

- Hashtable（同步，慢，数据量小）
- HashMap（不支持同步，快，数据量大）
- Properties (同步，文件形式，数据量小)



**Hashtable**

- K-V 对， K 和 V 都不允许为 null
- 同步，多线程安全
- 无序的
- 适合小数据量

**主要方法：** 

```
clear			清空数据contains		等同于containsValuecontainsValue	是否包含某一个值containsKey		是否包含某一个keyget				根据key获取相应的值put				增加新的K-V对remove			删除某一个K-V对size			返回数据大小
```



**HashMap(最常用的映射结构)**

- K V 对， K 和 V 都允许为 null

- 不同步，多线程不安全

  ```
  Map m = Collections.synchronizedMap(new HashMap(...));此语句可将它转化为同步
  ```

- 无序的

**主要方法：**

```
clear			清空数据contains		等同于containsValuecontainsValue	是否包含某一个值containsKey		是否包含某一个keyget				根据key获取相应的值put				增加新的K-V对remove			删除某一个K-V对size			返回数据大小
```



**LinkedHashMap**

- 基于双向链表的维持插入顺序的HashMap（它的遍历将会按照插入的顺序进行遍历的）

**TreeMap**

- 基于红黑树的Map，可以根据key的自然排序或者compareTo方法进行排序输出

**如需要排序，考虑 LinkedHashMap 和 TreeMap**



**Properties**

- 继承于Hashtable
- 如需要将K-V对保存在文件中，可采用Properties
- 适用于数据量少的配置文件
- 继承自Hashtable的方法：clear, contains/containsValue, containsKey,get, put,remove, size
- 从文件加载的load方法， 写入到文件中的store方法
- 获取属性getProperty ，设置属性setProperty



### 6	工具类

**什么是JCF中的工具类**

工具类不存储数据，它只是在数据容器上，实现排序，搜索等高效操作

**Arrays：处理对象是数组**

```
排序：对数组排序, sort/parallelSort。查找：从数组中查找一个元素, binarySearch。批量拷贝：从源数组批量复制元素到目标数组, copyOf。批量赋值：对数组进行批量赋值, fill。等价性比较：判定两个数组内容是否相同, equals。
```

**Collections: 处理对象是Collection及其子类**

```
排序：对List进行排序，sort。搜索：从List中搜索元素，binarySearch批量赋值：对List批量赋值，fill。最大、最小：查找集合中最大/小值，max，min反序：将List 反序排列，reverse
```

**对象实现Comparable接口（需要修改对象类时）**

```
compareTo方法	(> 返回1，返回0，<返回-1)Arrays和Collections在进行对象sort时，自动调用该方法新建Comparator（适用于对象类不可更改的情况）compare方法(> 返回1，返回0，<返回-1)Comparator比较器将作为参数提交给工具类的sort方法
```

