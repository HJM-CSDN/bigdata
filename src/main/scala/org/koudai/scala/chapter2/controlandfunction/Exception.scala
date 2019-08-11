package org.koudai.scala.chapter2.controlandfunction

import scala.math._
import java.net.URL
import java.io.IOException
import java.net.MalformedURLException
/**
  * scala异常
  */
object Exception {
  /**
    * 定义main函数（过程）
    */
  def main(args: Array[String]): Unit = {
    /*
    * scala的异常工作机制和java一样。
    * 当程序抛出异常时当前的运算被终止，示例如下：
    */
    //    throw new Exception("异常信息")

    /*
     * 和java一样，抛出的对象必须是java.lang.Throwable的子类。不过与java不同的是，
     * Scala没有“受检”异常--即你    不需要    在方法或函数定义时就声明可能会抛出的某种异常。
     *
     * 在java中，“受检”异常在编译期被检查。如果你的方法可能会抛出IOException，你必须做出声明
     * （在方法内部try/catch 或者 在方法上throws出去）。许多java程序员反感这个特性，最终造成的
     * 结果是过早的捕获这些异常，或者使用超通用的异常类（例如我们总是喜欢 throws Exception）。
     * scala的设计者们决定不支持“受检”异常，因为他们意识到彻底的编译期检查并不总是好的。
     */

    /*----------------------------if/else的类型---------------------------*/
    /*
     * 如下类型的if/else语句，
     * 第一个分支的结果类型是Double，第二个分支因为抛出了异常所以类型是Nothing。
     * 所以if/else的表达式的类型是Double
     */
    val x = 0.0
    if (x >= 0){
      sqrt(x)
    }else{
      throw new IllegalArgumentException("x should not by negative")
    }
    /*----------------------------scala的异常捕获方式---------------------------*/
    //scala捕获异常时使用的是模式匹配的方法（后续讲解）示例如下：
    try{
      var url = new URL("http://www.baidu.com");
      //伪代码,处理给定的URL
      // process(url)
    }catch {
      case _:MalformedURLException => println("Bad URL")
      case ex:IOException => ex.printStackTrace()
    }

    /*----------------------------scala异常 综合示例---------------------------*/
    //示例如下
    var in = new URL("http://www.baidu.com").openStream()
    try{
      //伪代码,处理给定的URL
      // process(in)
      var temp = in.read();
      while (temp>0){
        print(temp.toChar)
        temp = in.read()
      }
    }finally {
      in.close()
    }
    //上述代码中，与java类似，finally中的代码总是会被执行
    //注意: try/catch和try/finally是互补的，try/finally通常用在异常没有被处理时执行某种动作
    //（通常是清理资源动作）
  }
}
