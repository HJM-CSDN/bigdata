package org.koudai.scala.chapter1.base

/**
  * scala 操作符和方法
  */
object  MyOperator {

  /**
    * 定义main函数
    */
  def main(args: Array[String]): Unit = {
    /*
     * 1.在scala中，运算符的规则和java中没有什么区别，但是“+”是一个方法
     */
    val result = 5 * 8 - 9
    println(result)

    /*
      * 在scala中，运算符可以当做函数名，这跟java大不相同
      * 在java中常见的形式是 int1 + int2，实际上在scala中可以写成像下面这种形式：
      * int1.+(int2)即int1调用一个"+"这么一个方法，int2是这个方法的参数
      *
      * 在java中，方法调用一般写成这种方式a.方法(b)
      * 在scala中，方法调用还可以简写成这种方式： a 方法  b
      * 注意：上述scala所说的方法指任意方法
      */
    //java常用写法
   // 1 + 2
    println(1 + 2)
    //scala支持写法
  //  1.+(2)
    println(1.+(2))
    //scala支持写法
  //  1 toString()
    println(1 toString())
  //  1 toString
    println(1 toString)

    /*-----------------------具体使用哪种风格，看自己喜好，更推荐scala方式------------------------------*/

    /*
     * scala中没有++和--符号
     * 因为scala中规定运算符也可以当做方法名，但是我们没有办法简单的定义一个++函数，因为Int类是不可变的。
     * 需要注意的是：可变的是var定义的变量，而不是Int类型的对象。所以 这样一个方法不能改变某个整数类型的值。
     * scala的设计者不值得为少一个键额外增加一个特例。
     * 所以，在scala中使用+=1 和-=1来代替++和--
     */
    var int1 = 10
    int1 += 1
    print(int1)

    /*
     * 对于BigDecimal对象，在scala中可以以一种比较方便(也更符合生活常规)的方式使用数学操作符
     * 在这里，乘号也是一个方法
     */
    val y: BigDecimal = 1234567890
    println()
    print(y * y * y)

    //在java中，因为BigDecimal是对象，所以需要写成如下方式
    //x.multiply(x).multiply(x)
  }
}
