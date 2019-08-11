package org.koudai.scala.chapter12.HigherOrderFunctions

/**
 * 高阶函数
 */
object HigherOrderFunctions {
  def main(args: Array[String]): Unit = {
  //柯里化curring
   def mulOneAtTime(x:Int)(y:Int)=x * y
   println(mulOneAtTime(6)(7))

    val a = Array("hello","world")
    val b = Array("HELLO","WORLD")
    a.corresponds(b)(_.equalsIgnoreCase(_))
    println(a.corresponds(b)(_.equalsIgnoreCase(_)))

  }
}