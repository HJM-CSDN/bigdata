package org.koudai.scala.chapter3.array

/**
 * scala 固定长度的数组
 */
object FixedLengthArray {
  //定义main函数
  def main(args: Array[String]): Unit = {
    /*
     * 为了跟变长数组进行区分，
     * scala中定长数组一般称之为数组，变长数组称之为数组缓存
     * 
     */
    //如果我们需要一个长度不变的数据，可以使用Scala中的Array。示例如下
        
	  //定义一个10个整数的数组，所有元素初始化为0
    println("定义一个10个整数的数组,所有元素初始化为0:\n val nums = new Array[Int](10)")
    val nums = new Array[Int](10)
    
 		//定义一个10个元素的字符串数组，所有元素都初始化为null
    println("定义一个10个元素的字符串数组,所有元素都初始化为null:\n val strs = new Array[String](10)")
    val strs = new Array[String](10)

    //定义一个长度为2的Array[String]--这里所说的类型是scala自动推断出来的
    println("定义一个长度为2的Array[String]:\nval s = Array(\"Hello\",\"World\")")
    val s = Array("Hello","World") //注意：如果已提供初始值就不需要new
    //此处val代表s指针指向的地址不能更改,但是s指向地址的内容可以更改
    println("注意：如果已提供初始值就不需要new\n此处val代表s指针指向的地址不能更改,但是s指向地址的内容可以更改")
    //将数组s更改为Array("GoodBye","World")
    println("将数组s更改为Array(\"GoodBye\",\"World\"):\n s(0) = \"GoodBye\"println(s(0)):")
    s(0) = "GoodBye" //注意：在scala中使用()而不是[]来访问元素
    println(s(0))
    println("结果为:")
    for (elem <- s) {
      print(elem)
    }
    /*
     * 说明：
     * 在JVM中，Scala的Array以java的数组的方式实现。
     * scala中的String类型的数组即为java中的java.lang.String[]类型
     * 其他的Int、Double等类型与java中的基本类型对应的数组是相同的。
     * 例如：scala中Array(1,2,5)在JVM中就是一个int[]
     */
  }
}