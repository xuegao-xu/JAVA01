# 第四章学习笔记

### 1	XML简介

```
XML基本概念
• XML(eXtensible Markup Language)
• 可扩展标记语言：意义+数据(适用于传输数据)
• 标签可自行定义，具有自我描述性
• 纯文本表示，跨系统/平台/语言
```

```
XML常规语法
– 任何的起始标签都必须有一个结束标签。
– 简化写法，例如，<name></name>可以写为<name/>。
– 大小写敏感，如<name>和<Name>不一样。
– 每个文件都要有一个根元素。
– 标签必须按合适的顺序进行嵌套，不可错位。
– 所有的特性都必须有值，且在值的周围加上引号。
– 需要转义字符，如“<”需要用&lt;代替。
– 注释：<!-- 注释内容-->
```

```
XML扩展(1)
• DTD(Document Type Definition)
– 定义XML 文档的结构
– 使用一系列合法的元素来定义文档结构
– 可嵌套在xml文档中，或者在xml中引用
XML扩展(2)
• XML Schema(XSD，XML Schema Definition)
– 定义XML 文档的结构, DTD的继任者
– 支持数据类型，可扩展，功能更完善、强大
– 采用xml编写
XML扩展(3)
• XSL
– 扩展样式表语言(eXtensible Stylesheet Language)
– XSL作用于XML，等同于CSS作用于HTML
– 内容
• XSLT: 转换XML 文档
• XPath: 在XML 文档中导航
• XSL-FO: 格式化XML文档
```



### 2	XML解析

```
XML解析方法
– 树结构
• DOM: Document Object Model 文档对象模型，擅长(小规模)读/写
– 流结构
• SAX: Simple API for XML 流机制解释器(推模式)，擅长读
• Stax: The Streaming API for XML 流机制解释器(拉模式)，擅长读，
```



**DOM**

- DOM 是W3C 处理XML 的标准API
  – 直观易用。
  – 其处理方式是将XML 整个作为类似树结构的方式读入内存中以便操作及解析，方便修改。
  – 解析大数据量的XML 文件，会遇到内存泄露及程序崩溃的风险。

```
DOM类
• DocumentBuilder 解析类，parse方法
• Node 节点主接口，getChildNodes返回一个NodeList
• NodeList 节点列表，每个元素是一个Node
• Document 文档根节点
• Element 标签节点元素(每一个标签都是标签节点)
• Text节点(包含在XML元素内的，都算Text节点)
• Attr节点(每个属性节点)
```

```
SAX
• Simple API for XML
– 采用事件/流模型来解析XML 文档，更快速、更轻量。
– 有选择的解析和访问，不像DOM 加载整个文档，内存要求较低。
– SAX 对XML 文档的解析为一次性读取，不创建/不存储文档对
象，很难同时访问文档中的多处数据。
– 推模型。当它每发现一个节点就引发一个事件，而我们需要编写这些事件的处理程序。关键类：
```

```
Stax
• Streaming API for XML
– 流模型中的拉模型
– 在遍历文档时，会把感兴趣的部分从读取器中拉出，不需要引发
事件，允许我们选择性地处理节点。这大大提高了灵活性，以及
整体效率。
– 两套处理API
• 基于指针的API， XMLStreamReader
• 基于迭代器的API，XMLEventReader
```

**其他的第三方库**

- DOM/SAX/Stax是JDK自带的解析功能。
  - DOM：读(小规模)XML，写XML
  - SAX/Stax：适合读(大规模)XML (一般大于1M，是并发量)
- 第三方库
  -  JDOM: www.jdom.org
  -  DOM4J: dom4j.github.io
- 第三方库一般都包含DOM,SAX等多种方式解析，是对Java解析进行封装。



### 3	JSON简介及解析

```
JSON概念
– JavaScript Object Notation, JS 对象表示法
– 是一种独立于编程语言的、轻量的、数据交换格式
– 类似XML，更小、更快、更易解析
– 最早用于Javascript中，容易解析，最后推广到全语言
– 尽管使用Javascript语法，但是独立于编程语言
```

```
JSONObject和JSONArray
• 名称/值对。如"firstName":"John"
    – JSON对象：{“name":"Jo","email":"a@b.com"}
    – 数据在键值对中
    – 数据由逗号分隔
    – 花括号保存对象
• JSON数组
    – 方括号保存数组
    [{“name":"Jo","email":"a@b.com"},{“name":"Jo","email":"a@b.com"}]
```

```
Java的JSON处理
• org.json：JSON官方推荐的解析类
    – 简单易用，通用性强
    – 复杂功能欠缺
• GSON：Google出品
    – 基于反射，可以实现JSON对象、JSON字符串和Java对象互转
• Jackson：号称最快的JSON处理器
    – 简单易用，社区更新和发布速度比较快
```

```
JSON 主要用途
• JSON生成
• JSON解析
• JSON校验
• 和Java Bean对象进行互解析
    – 具有一个无参的构造函数
    – 可以包括多个属性，所有属性都是private
    – 每个属性都有相应的Getter/Setter方法
    – Java Bean用于封装数据，又可称为POJO(Plain Old Java Object)
```

**JSON和XML比较**

- 都是数据交换格式，可读性强，可扩展性高
- 大部分的情况下，JSON更具优势（编码简单，转换方便），而且JSON字符长度一般小于XML，传输效率更高
- XML更加注重标签和顺序
- JSON会丢失信息



### 4	图形图像简介及解析

```
图形图像基础概念
• 图形：Graph
    – 矢量图，根据几何特性来画的，比如点、直线、弧线等
• 图像：Image
    – 由像素点组成
    – 格式：jpg, png, bmp, svg, wmf, gif, tiff 等
    – 颜色：RGB(Red, Green, Blue)
```

```
Java图形图像关键类
• 图形：Graph
    – java.awt 包
    – Java 2D库: Graphics2D, Line2D, Rectangle2D, Ellipse2D,Arc2D
    – Color, Stroke
• 图像：Image
    – javax.imageio包
    – ImageIO, BufferedImage, ImageReader, ImageWriter
```

```
Java图像关键类描述
• Java原生支持jpg, png, bmp, wbmp, gif
• javax.imageio.ImageIO
    – 自动封装多种ImageReader和ImageWriter，读写图像文件
    – read 读取图片write 写图片
• java.awt.image.BufferedImage,图像在内存中的表示类
    – getHeight 获取高度
    – getWidth 获取宽度
• 图像文件读写/截取/合并
```

```
验证码的生成
• 验证码，一个图片文件
– 外框
– 底色
– 干扰线
    • 随机产生一些直线
– 字母
    • 字母选择，不要0，o，1，I，L
    • 字母颜色(RGB)
    • 字母位置
```

```
统计图的生成
– 柱状图/饼图/折线图
– Java原生的Graphics 2D可以画，比较繁琐
– 基于jFreeChart(www.jfree.org/jfreechart)可以快速实现统计图生成
    • 设定数据集
    • 调用ChartFactory生成图形
```



### 5	条形码和二维码简介及解析

```
条形码(barcode)
– 将宽度不等的多个黑条和空白，按照一定的编码规则排列，用以表达一组信息的图形标识符
– 上个世纪40年代发明的
– 通常代表一串数字/字母，每一位有特殊含义
– 一般数据容量30个数字/字母
– 专门机构管理：中国物品编码中心
```

```
二维码，二维条形码
– 用某种特定的几何图形按一定规律在平面（二维方向上）分布的黑白相间的图形记录数据符号信息
– 比一维条形码能存更多信息，表示更多数据类型
– 能够存储数字/字母/汉字/图片等信息
– 字符集128个字符
– 可存储几百到几十KB字符
– 抗损坏
```

```
Zxing(Zebra Crossing)
– Google 出品
– 支持1D和2D的Barcode
– 主要类
    • BitMatrix 位图矩阵
    • MultiFormatWriter 位图编写器
    • MatrixToImageWriter 写入图片
    
Barcode4J
– http://barcode4j.sourceforge.net/
– 纯Java实现的条形码生成
– 只负责生成，不负责解析
– 主要类
    • BarcodeUtil
    • BarcodeGenerator
    • DefaultConfiguration
```



### 6	DOCX简介及解析

```
Docx 简介
• 以Microsoft Office的doc/docx为主要处理对象。
• Word2003（包括）之前都是doc，文档格式不公开。
• Word2007（包括）之后都是docx，遵循XML路线，文档格式公开。
• docx为主要研究对象
    – 文字样式
    – 表格
    – 图片
    – 公式


Docx 功能和处理
• 常见功能
    – docx解析
    – docx生成（完全生成，模板加部分生成：套打）
• 处理的第三方库
    – Jacob, COM4J (Windows 平台)
    – POI, docx4j, OpenOffice/Libre Office SDK (免费)
    – Aspose (收费)
    – 一些开源的OpenXML的包
```

```
Apache POI
– Apache 出品，必属精品， poi.apache.org
– 可处理docx, xlsx, pptx, visio等office套件
– 纯Java工具包，无需第三方依赖
– 主要类
    • XWPFDocument 整个文档对象
    • XWPFParagraph 段落
    • XWPFRun 一个片段(字体样式相同的一段)
    • XWPFPicture 图片
    • XWPFTable 表格
```

```
Doc/Docx处理总结
• 不同的Office工具，产生出来的docx文件格式不兼容
• 不同的第三方包，能够解析和生成的内容也不同，使用的类也不同
• doc/docx功能非常非常多，第三方包不是万能的，也存在无法解析的情况
```



### 7	表格文件简介及解析

```
表格文件
• xls/xlsx 文件(Microsoft Excel)
• CSV文件(Comma-Seperated Values文件)
```

```
xlsx(Excel)
• 与word类似，也分成xls和xlsx。
• xlsx以XML为标准，为主要研究对象
• 数据
	– sheet
        • 行
        • 列
          –单元格

xlsx(Excel)功能和第三方包
• 常见功能
– 解析
– 生成
• 第三方的包
– POI，JXL (免费)
– COM4J (Windows平台)
– Aspose等(收费)
```

```
Apache POI
– Apache 出品，必属精品， poi.apache.org
– 可处理docx, xlsx, pptx, visio等office套件
– 纯Java工具包，无需第三方依赖
– 主要类
    • XSSFWorkbook 整个文档对象
    • XSSFSheet 单个sheet对象
    • XSSFRow 一行对象
    • XSSFCell 一个单元格对象
```

```
CSV文件
• 全称： Comma-Seperated Values文件(逗号分隔)
• 广义CSV文件，可以由空格/Tab键/分号/…/完成字段分隔
• 第三方包：Apache Commons CSV
    – CSVFormat 文档格式
    – CSVParser 解析文档
    – CSVRecord 一行记录
    – CSVPrinter 写入文档
```



### 8	PDF简介及解析

```
PDF
– Portable Document Format的简称，意为“便携式文档格式”。
– Adobe公司发明的。
– PostScript，用以生成和输出图形，在任何打印机上都可保证精确的颜色和准确的打印效果。
– 字型嵌入系统，可使字型随文件一起传输。
– 结构化的存储系统，绑定元素和任何相关内容到单个文件，带有适当的数据压缩系统

PDF 处理和第三方包
• 产生pdf和修改pdf，建议先生成docx，再进行转化
• 常见功能处理
    – 解析PDF
    – 生成PDF(转化)
• 第三方包
    – Apache PDFBox (免费)
    – iText (收费)
    – XDocReport (将docx转化为pdf)
```

```
Apache PDFBox
– 纯Java类库
– 主要功能：创建，提取文本，分割/合并/删除，…
– 主要类
    • PDDocument pdf文档对象
    • PDFTextStripper pdf文本对象
    • PDFMergerUtility 合并工具
```

```
XDocReport
– 将docx文档合并输出为其他数据格式(pdf/html/…)
– PdfConverter
– 基于poi和iText完成
```

