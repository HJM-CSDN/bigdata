package org.koudai.scala.chapter1.base

/**
  * 调用函数和方法
  */
object Function{
  /**
    * 定义main函数
    */
  def main(args: Array[String]): Unit = {
    /*
     * 除了方法（方法需要依赖类而存在）之外，Scala中还提供很多系统函数。
     * 函数的使用方式相当简便，如下所示（如下所示的函数都在scala的math包中，需要先引入scala中的包）：
     * 跟java相比，函数类似于静态方法，但是却不需要依赖某个具体的类（java中就算是静态方法也需要类型打点的形式进行调用）。
     */

    //给数字2开平方
    val sqrt2 = math.sqrt(2)
    println(sqrt2)

    /*
     * scala中，使用以scala开头的包时，我们可以省去scala前缀。
     * 例如：
     * 1.import math._等同于 import scala._
     * 2.math.sqrt(2)等同于scala.math.sqrt(2)
     * 以下所有的写法都是正确的
     */
    math.sqrt(2)
    math sqrt(2)
    math sqrt 2
    scala.math sqrt 2


    /*
     * Scala中没有静态方法，不过它有个类似的特性，叫作单例对象（Singleton object），后续课程详细讲解。
     * 通常，一个类有一个伴生对象（companion object），其上的方法就跟java中的静态方法一样。
     *
     * 例如：
     * BigInt类的BigInt伴生对象有一个生成指定位数的随机素数的方法probablePrime，用法如下：
     *
     */
    val result = BigInt.probablePrime(100, scala.util.Random)
    println(result)


    /*
     * scala中，不带参数的方法通常不需要圆括号
     * 例如：
     * 	StringOps类的API就提供了一个distinct方法，顾名思义，是返回字符串中不重复的部分，
     *  它无需参数，使用示例如下
     */
    val distinctStr = "hanjinming".distinct
    println(distinctStr)

    /*--------------------apply方法-------------------------------*/

    /*
     * 在scala中我们通常都会使用类似函数调用的语法。
     * 例如：
     * 如果s是一个字符串，那么s(i)就是该字符串的第i个字符。i从零开始计数
     * 在java中需要这样写s.charAt(i),即java中采用的还是方法调用的思路
     */
    val applyResult = "hello" (0)
    println(applyResult)

    /*
     * 我们可以将这种用法当做是“()”操作符的重载形式，它背后的实现原理是一个名为apply的方法。
     * 也就是说，上述的"hello"(4)是下述方式的简写：
     */
    val a = "hello".apply(4)
    println(a)
    /*
     * apply方法在scala语言内部的其他常见例子
     * 1.将字符串或者数字转换为BigInt
     * 		即：BigInt("1234567890")是BigInt.apply("1234567890")的简写
     * 2.Array(1,2)返回数组也是使用的Array伴生对象上的apply方法
     */
  }
}
