package org.unicom.scala.chapter0.helloworld

object HelloWorld {
  /*
 * 1.scala程序最终需要转换成class文件以用于执行，所以scala中的注释与java中一致
 */
  /**
    * 与java类似，定义main方法
    */
  def main(args: Array[String]): Unit = {
    println("hello world")
    println("hello".reverse(0))//打印字符串最后一个字母

  }
}
