package org.unicom.scala

object  一天搞定scala {

}
/**
  * 《chang哥教你一天搞定Scala》
  * scala是一门多范式编程语言，集成了面向对象编程和函数式编程等多种特性。
  * scala运行在虚拟机上，并兼容现有的Java程序。
  * Scala源代码被编译成java字节码，所以运行在JVM上，并可以调用现有的Java类库。
  */

/**
  * 第一个Scala程序
  * Scala和Java最大的区别是：Scala语句末尾的分号（；）是可选的！
  * 编译运行：
  * 先编译：scalac HelloScala.scala   将会生成两个文件：HelloScala$.class和HelloScala.class
  * 在运行：scala HelloScala
  * 输出结果：hello scala!!!
  *
  *   object HelloScala{
         def main(args: Array[String]): Unit = {
           println("hello scala!!!")
         }
       }
  */

/**
  * Scala基本语法:
  * 区分大小写
  * 类名首字母大写（MyFirstScalaClass）
  * 方法名称第一个字母小写（myMethodName()）
  * 程序文件名应该与对象名称完全匹配
  * def main(args:Array[String]):scala程序从main方法开始处理，程序的入口。
  *
  * Scala注释：分为多行/**/和单行//
  *
  * 换行符：Scala是面向行的语言，语句可以用分号（；）结束或换行符（println()）
  *
  * 定义包有两种方法：
  *   1、package com.ahu
  *      class HelloScala
  *   2、package com.ahu{
  *       class HelloScala
  *     }
  *
  * 引用：import java.awt.Color
  * 如果想要引入包中的几个成员，可以用selector（选取器）:
  *   import java.awt.{Color,Font}
  *   // 重命名成员
  *   import java.util.{HashMap => JavaHashMap}
  *   // 隐藏成员 默认情况下，Scala 总会引入 java.lang._ 、 scala._ 和 Predef._，所以在使用时都是省去scala.的
  *   import java.util.{HashMap => _, _} //引入了util包所有成员，但HashMap被隐藏了
  */

/**
  * Scala数据类型：
  * Scala与Java有着相同的数据类型，下面列出一些Scala有的数据类型。
  * Unit：表示无值，和其他语言的void一样。
  * Null：null或空引用。
  * Nothing：是Scala的类层级的最低端，是任何其他类型的子类型。
  * Any：是所有其他类的超类。
  * AnyRef：是Scala所有引用类的基类。
  *
  * 多行字符串的表示方法：
       val foo ="""第一行
           第二行
           第三行"""
  */

/**
  * Scala变量：
  * 在Scala中，使用关键字“var”声明变量，使用关键字“val”声明常量。
  *   var myVar1 : String = "foo"
  *   var myVar2 : Int
  *   val myVal = "Hello,Scala！"
  * Scala多个变量声明：
  *   val xmax, ymax = 100  // xmax,ymax都声明为100
  */

/**
  * Scala访问修饰符：
  * Scala访问修饰符和Java基本一样，分别有private、protected、public。
  * 默认情况下，Scala对象的访问级别是public。
  *
  * 私有成员：用private关键字修饰的成员仅在包含了成员定义的类或对象内部可见。
  *     class Outer{
  *       class Inner{
  *         private def f(){println("f")}
  *         class InnerMost{
  *           f() // 正确
  *         }
  *         (new Inner).f() // 错误
  *       }
  *     }
  *
  * 保护成员：Scala比Java中更严格。只允许保护成员在定义了该成员的类的子类中被访问。
  *     package p{
  *     class Super{
  *       protected def f() {println("f")}
  *     }
  *         class Sub extends Super{
  *           f()
  *         }
  *         class Other{
  *           (new Super).f()  // 错误
  *         }
  *     }
  *
  * 公共成员：默认public，这样的成员在任何地方都可以被访问。
  *   class Outer{
  *     class Inner{
  *       def f(){println("f")}
  *       class InnerMost{
  *         f() // 正确
  *       }
  *     }
  *     (new Inner).f() // 正确
  *   }
  *
  * 作用域保护：Scala中，访问修饰符可以通过使用限定词强调。
  *   private[x] 或者 protected[x]
  *   private[x]：这个成员除了对[...]中的类或[...]中的包中的类及他们的伴生对象可见外，对其他的类都是private。
  */

/**
  * Scala运算符：和Java一样，这里就不再浪费时间一一介绍了。
  * 算术运算符、关系运算符、逻辑运算符、位运算符、赋值运算符。
  */

/**
  * Scala if...else语句：和Java一样，简单列举一下四种情况。
  *   if(...){
  *
  *   }
  *
  *   if(...){
  *
  *   }else{
  *
  *   }
  *
  *   if(...){
  *
  *   }else if(...){
  *
  *   }else{
  *
  *   }
  *
  *   if(...){
  *     if(...){
  *
  *     }
  *   }
  */

/**
  * Scala循环：和Java一样，这里不赘述，只介绍三种循环类型。
  * while循环、do...while循环、for循环
  */

/**
  * Scala函数：用一个例子来说明函数的定义和函数调用。
  * object Test{
  *   def main(args: Array[String]){
  *     println(addInt(1,3)); // 函数调用
  *   }
  *   def addInt(a:Int, b:Int) : Int = {  // 函数定义
  *     var sum:Int = 0
  *     sum = a + b
  *     return sum
  *   }
  * }
  */

/**
  * Scala闭包：
  * 闭包是一个函数，返回值依赖于声明在函数外部的一个或多个变量。
  * 例子：
  *  object Test{
  *   def main(args: Array[String]){
  *     println("muliplier(1) value = " + muliplier(1))
  *     println("muliplier(2) value = " + muliplier(2))
  *   }
  *   var factor = 3  // 定义在函数外的自由变量
  *   val muliplier = (i:Int) => i * factor  // muliplier函数变量就是一个闭包
  *  }
  *  输出结果：
  *  muliplier(1) value = 3
  *  muliplier(2) value = 6
  */

/**
  * Scala字符串：
  *
  * Scala中可以创建两中字符串：一种是不可修改的，一种是可以修改的。
  * // 创建不可修改的字符串
  * val greeting:String = "Hello World!";
  * // 创建可以修改的字符串
  * object Test{
  *   def main(args: Array[String]){
  *     val buf = new StringBuilder;
  *     buf += 'a'  // 添加一个字符
  *     buf ++= "bcdef" // 添加一个字符串
  *     println(buf.toString);  // 输出：abcdef
  *   }
  * }
  *
  * 字符串长度：xxx.length()
  *
  * 字符串连接：可以用concat()方法或者用加号
  * object Test {
       def main(args: Array[String]) {
         var str1 = "字符串1：";
         var str2 =  "字符串2";
         var str3 =  "字符串3：";
         var str4 =  "字符串4";
         println( str1 + str2 ); //  字符串1：字符串2
         println( str3.concat(str4) ); // 字符串3：字符串4
       }
     }
  *
  * 创建格式化字符串：
  * String类中可以使用printf()方法来格式化字符串并输出。
  * object Test{
  *   def main(args:Array[String]){
  *     var floatVar = 12.456
  *     var intVar = 2000
  *     var stringVar = "字符串变量"
  *     var fs = printf("浮点型变量为 " +
  *                     "%f，整形变量为 %d, 字符串为 " +
  *                     "%s", floatVar, intVar, stringVar)
  *     println(fs) // 浮点型变量为 12.456000, 整型变量为 2000, 字符串为 字符串变量
  *   }
  * }
  */

/**
  * Scala数组：
  * 1、声明数组
  *   var z:Array[String] = new Array[String](3)  或者  var z = new Array[String]()
  *   z(0) = "value1"; z(1) = "value2"; z(2) = "value3"
  *
  *   var z = Array("value1", "value2", "value3")
  *
  * 2、处理数组
  *   object Test{
  *     def main(args: Array[String]){
  *       var myList = Array(1.1, 2.2, 3.3, 4.4)
  *
  *       // 输出所有数组元素
  *       for(x <- myList){
  *         println(x)
  *       }
  *
  *       // 计算数组所有元素的总和
  *       var total = 0.0
  *       for(i <- 0 to (myList.length - 1)){
  *         total += myList(i)
  *       }
  *       println("总和：" + total)
  *
  *       // 查找数组中的最大元素
  *       var max = myList(0)
  *       for(i <- 1 to (myList.length - 1)){
  *         if(myList(i) > max)
  *           max = myList(i)
  *       }
  *       println("最大值：" + max)
  *     }
  *   }
  *
  * 3、多维数组
  * import Array._
  * object Test{
  *   def main(args: Array[String]){
  *     // 定义数组
  *     var myMatrix = ofDim[Int](3,3)
  *     // 创建矩阵
  *     for(i <- 0 to 2){
  *       for(j <- 0 to 2){
  *         myMatrix(i)(j) = j;
  *       }
  *     }
  *     // 打印矩阵
  *     for(i <- 0 to 2){
  *       for(j <- 0 to 2){
  *         print(" " + myMatrix(i)(j));
  *       }
  *       println();
  *     }
  *   }
  * }
  *
  * 4、合并数组
  * import Array._
  * object Test{
  *   def main(args: Array[String]){
  *     var myList1 = Array(1.1, 2.2, 3.3, 4.4)
  *     var myList2 = Array(5.5, 6.6, 7.7, 8.8)
  *     // 使用concat()合并
  *     var myList3 = concat(myList1, myList2)
  *     // 输出所有数组元素
  *     for(x <- myList3){
  *       println(x)
  *     }
  *   }
  * }
  *
  * 5、创建区间数组:使用range(x,y,z)创建区间数组,数值范围大于等于x,小于y。z表示步长，默认为1。
  * object Test{
  *   def main(args: Array[String]){
  *     var myList1 = range(10, 20, 2)
  *     var myList2 = range(10, 20)
  *     for(x <- myList1){
  *       print(" " + x)  //输出：10 12 14 16 18
  *     }
  *     println()
  *     for(x <- myList2){
  *       print(" " + x)  // 输出：10  12 13 14 15 16 17 18 19
  *     }
  *   }
  * }
  */

/**
  * Scala集合：分为可变集合和不可变集合。
  * 可变集合：可以在适当的地方被更新或扩展，也就是可以修改、添加、移除一个集合的元素。
  * 不可变集合：永远不会改变。但可以模拟添加、移除、更新操作，但是这些操作将在每一种情况下都返回一个新的集合，
  *            同时使原来的集合不发生改变。
  * // 定义整形List
  * val x = List(1,2,3,4)
  * // 定义Set
  * var x = Set(1,3,5,7)
  * // 定义Map
  * val x = Map("one" -> 1, "two" -> 2, "three" -> 3)
  * // 创建两个不同类型的元组
  * val x = (10, "Runoob")
  * // 定义Option
  * val x:Option[Int] = Some(5)
  */

/**
  * Scala迭代器：
  * 迭代器不是一个集合，而是一个用于访问集合的方法。
  *
  */
/*object Test{
  def main(args: Array[String]): Unit = {
    val it = Iterator("one", "two", "three", "four")
    while(it.hasNext){  // 检测集合中是否还有元素
      println(it.next())  // 返回迭代器的下一个元素，并更新迭代器的状态
    }

    val ita = Iterator(1, 2, 3, 4, 5)
    val itb = Iterator(, 22, 33, 44, 55)
    //println(ita.max)  // 查找最大元素
    //println(itb.min)  // 查找最小元素

    println(ita.size) // 获取迭代器的长度
    println(itb.length) // 获取迭代器的长度
  }
}*/

/**
  * Scala类和对象：
  * 类是对象的抽象，对象是类的具体实例。
  * 类是抽象的，不占用内存；对象是类的具体实例，占用存储空间。
  *
  */
/*import java.io._
class Point(xc: Int, yc: Int){
  var x: Int = xc
  var y: Int = yc
  def move(dx: Int, dy: Int): Unit ={
    x = x + dx
    y = y + dy
    println("x点的坐标是：" + x)
    println("y点的坐标是：" + y)
  }
}
object Test{
  def main(args: Array[String]): Unit = {
    val pt = new Point(10, 20)
    // 移到一个新的位置
    pt.move(10, 10)
  }
}*/
/**
  * Scala继承：跟Java差不多。
  * 1、重写一个非抽象方法必须使用override修饰符
  * 2、只有主构造函数才可以往基类的构造函数里写参数
  * 3、在子类中重写超类的抽象方法时，不需要使用override
  */

/*class Point(val xc: Int, val yc: Int){
  var x: Int = xc
  var y: Int = yc
  def move(dx: Int, dy: Int): Unit ={
    x = x + dx
    y = y + dy
    println("x点的坐标是：" + x)
    println("y点的坐标是：" + y)
  }
  //-------------------------------------
  var name = ""
  override def toString = getClass.getName + "[name=" + name + "]"
}
class Location(override val xc: Int, override val yc: Int,
               val zc: Int) extends Point(xc, yc){  // 继承   重写了父类的字段
  var z: Int = zc
  def move(dx: Int, dy: Int, dz: Int){
    x = x + dx
    y = y + dy
    z = z + dz
    println("x点的坐标是：" + x)
    println("y点的坐标是：" + y)
    println("z点的坐标是：" + z)
  }
  //---------------------------------------
  var salary = 0.0
  override def toString = super.toString + "[salary=" + salary + "]"
}
object Test{
  def main(args: Array[String]): Unit = {
    val loc = new Location(10, 20, 30)
    loc.move(10, 10 ,5)
    //------------------------------------
    loc.name = "lc"
    loc.salary = 35000.0
    println(loc)
  }
}*/

/**
  * Scala单例对象：
  * Scala中没有static，要使用object关键字实现单例模式。
  * Scala中使用单例模式时，除了定义类，还要定义一个同名的object对象，它和类的区别是，object对象不能带参数。
  * 当单例对象与某个类共享一个名称时，他被称作这个类的伴生对象。
  * 必须在同一个源文件里定义类和它的伴生对象。
  * 类和它的伴生对象可以互相访问其私有成员。
  */
/*// 私有构造方法
class Marker private(val color:String) {
  println("创建" + this)
  override def toString(): String = "颜色标记："+ color  //4：颜色标记：red
}

// 伴生对象，与类共享名字，可以访问类的私有属性和方法
object Marker{
  private val markers: Map[String, Marker] = Map(
    "red" -> new Marker("red"), //1：创建颜色标记：red
    "blue" -> new Marker("blue"), //2：创建颜色标记：blue
    "green" -> new Marker("green")  //3：创建颜色标记：green
  )

  def apply(color:String) = {
    if(markers.contains(color)) markers(color) else null
  }

  def getMarker(color:String) = {
    if(markers.contains(color)) markers(color) else null  //5：颜色标记：blue
  }

  def main(args: Array[String]) {
    println(Marker("red"))
    // 单例函数调用，省略了.(点)符号
    println(Marker getMarker "blue")
  }
}*/

/**
  * Scala Trait（特征）：
  * 相当于Java的接口，但比接口功能强大，它还可以定义属性和方法的实现。
  * 一般情况下Scala的类只能单继承，但特征可以实现多重继承。
  */
/*// 定义特征
trait Equal{
  def isEqual(x: Any): Boolean  // 未实现的方法
  def isNotEqual(x: Any): Boolean = !isEqual(x) // 实现了的方法
}

class Point(xc: Int, yc: Int) extends Equal{
  var x: Int = xc
  var y: Int = yc

  override def isEqual(obj: Any): Boolean =
    obj.isInstanceOf[Point] &&
    obj.asInstanceOf[Point].x == x
}

object Test{
  def main(args: Array[String]): Unit = {
    val p1 = new Point(2, 3)
    val p2 = new Point(2, 4)
    val p3 = new Point(3, 3)
    println(p1.isNotEqual(p2))
    println(p1.isNotEqual(p3))
    println(p1.isNotEqual(2))
  }
}*/

/**
  * 特征构造顺序：
  * 构造器的执行顺序：
  * 1、调用超类的构造器
  * 2、特征构造器在超类构造器之后、类构造器之前执行
  * 3、特征由左到右被构造
  * 4、每个特征当中，父特征先被构造
  * 5、如果多个特征共有一个父特征，父特征不会被重复构造
  * 6、所有特征被构造完毕，子类被构造
  */

/**
  * Scala模式匹配：
  * 选择器 match {备选项}
  */
/*object Test{
  def main(args: Array[String]): Unit = {
    println(matchTest("two"))
    println(matchTest("test"))
    println(matchTest(1))
    println(matchTest(6))
  }
  def matchTest(x: Any): Any = x match {
    case 1 => "one"
    case "two" => 2
    case y: Int => "scala.Int"  // 对应类型匹配
    case _ => "many"  // 默认全匹配选项
  }
}*/
/**
  * 使用样例类：
  * 使用case关键字的类定义就是样例类，样例类是种特殊的类，经过优化以用于模式匹配。
  */
/*object Test{
  def main(args: Array[String]): Unit = {
    val alice = new Person("Alice", 25)
    val bob = new Person("Bob", 32)
    val charlie = new Person("Charlie", 27)
    for(person <- List(alice, bob, charlie)){
      person match{
        case Person("Alice", 25) => println("Hi Alice!")
        case Person("Bob", 32) => println("Hi Bob!")
        case Person(name, age) => println("Age: " + age + " year,name: " + name +"?")
      }
    }
  }
  // 样例类
  case class Person(name: String, age: Int)
}*/

/**
  * Scala正则表达式：
  * 和Java差不多，在用的时候查一下就行了。
  */

/**
  * Scala异常处理：
  * 和Java类似。在Scala中借用了模式匹配的方法来在catch语句块中来进行异常匹配。
  */
/*import java.io.{FileNotFoundException, FileReader, IOException}
object Test{
  def main(args: Array[String]): Unit = {
    try {
      val f = new FileReader("input.txt")
    }catch {
      case ex: FileNotFoundException => {
        println("Missing file exception")
      }
      case ex: IOException => {
        println("IO Exception")
      }
    }finally {
      println("Exiting finally...")
    }
  }
}*/

/**
  * Scala提取器（Extractor）:
  * apply方法：无需new操作就可创建对象。
  * unapply方法：是apply方法的反向操作，接受一个对象，然后从对象中提取值，提取的值通常是用来构造对象的值。
  */
object Test4 {
  def main(args: Array[String]) {

    println ("Apply 方法 : " + apply("Zara", "gmail.com")); // 也可直接Test("Zara", "gmail.com")来创建Zara@gmail.com
    println ("Unapply 方法 : " + unapply("Zara@gmail.com"));
    println ("Unapply 方法 : " + unapply("Zara Ali"));

  }
  // 注入方法 (可选)
  def apply(user: String, domain: String) = {
    user +"@"+ domain
  }

  // 提取方法（必选）
  def unapply(str: String): Option[(String, String)] = {
    val parts = str split "@"
    if (parts.length == 2){
      Some(parts(0), parts(1))
    }else{
      None
    }
  }
}
/**
  * 提取器使用模式匹配：
  * 在我们实例化一个类的时，可以带上0个或者多个的参数，编译器在实例化的时会调用 apply 方法。
  */
/*object Test {
  def main(args: Array[String]) {

    val x = Test(5)
    println(x)

    x match
    {
      case Test(num) => println(x + " 是 " + num + " 的两倍！")  //2：10是5的两倍！
      //unapply 被调用
      case _ => println("无法计算")
    }

  }
  def apply(x: Int) = x*2 //1：10
  def unapply(z: Int): Option[Int] = if (z%2==0) Some(z/2) else None
}*/

/**
  * Scala文件I/O:
  *
  */
// 文件写操作
import java.io._
object Test3 {
  def main(args: Array[String]) {
    val writer = new PrintWriter(new File("D:\\test.txt" ),"utf-8")
    for(i <- 1 to 10)
    writer.write(i)
    writer.write("你好啊")
    writer.close()
  }
}
// 从屏幕上读取用户输入
//object Test2 {
//  def main(args: Array[String]) {
//    print("请输入 : " )
//    val line = Console.readLine // 在控制台手动输入
//    println("你输入的是: " + line)
//  }
//}
// 从文件上读取内容
import scala.io.Source
object Test {
  def main(args: Array[String]) {
    println("文件内容为:" )
    //文件中如果出现中文会有问题
    Source.fromFile("C:\\Users\\hjm13\\Desktop\\数据清洗\\项目\\1.txt" ,"gbk").foreach{
      print
    }
  }
}