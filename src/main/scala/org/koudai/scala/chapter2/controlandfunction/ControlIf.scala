package org.koudai.scala.chapter2.controlandfunction

/*
 * scala的逻辑控制结构之if语句
 */
object ControlIf {
  //定义main函数
  def main(args: Array[String]): Unit = {
    /*--------------------------条件表达式-------------------------------*/
    /*
     * scala中，if/else语法结构和java一样，但是scala中if/else表达式可以返回一个值。
     * 就这点来说，scala中的if/esle更相当于java中的三目运算符（boolean?a:b）和java 中的if/else方式的结合体
     *
     * 如下所示，两种方式都是等效的
     */
    //首先定义一个常量用于if判断
    val x = 0

    //方式1
    val resultVal = if (x>0) 1 else -1 //将整个表达式的值赋给常量resultVal
    println(resultVal)

    //方式2
    var resultVar = 0
    if (x>0) resultVar = 1 else resultVar = -1 //根据情况不同,给变量resultVar赋不同的值
    //上述两种方式的区别在于方式一可以直接将结果赋值给一个常量，
    //方式二中却只能使用变量来接受条件判断的结果，所以方式一更好一些

    /*
     * any类型
     * 见示例:
     */
    if(x>0) "hello world" else 25
    //上述实例中返回值类型有两种,字符类型和数字类型,我们无法使用单一的类型来接受这个if表达式的结果值
    //在scala中,设计了一种特殊的值类型,称之其他类型的超类型,叫作Any,类似于java中的Object.
    var any = if(x>0) "hello world" else 25

    /*
     * Unit类型
     * 如果if语句的else部分缺失了,见下面的示例:
     */
    if(x>0) 1
    //如果x是0或者负数,if语句就会没有输出值了,但是在scala的设计中每个表达式都应该有某种类型的输出值.
    //上述案例中的问题的解决方案是引入一个Unit类,写作().不带else的这个语句等同于:
    if(x>0) 1 else ()

    //这里的()表示"无有用值"的占位符.Unit类似于java中的void,但又不完全相同
    //Unit刚像是关系型数据库的null,虽然表示空的概念,但是null还是要占用底层存储的.void则表示一个空的概念

    /*------------scala不支持switch，但是有一个强大得多的模式匹配机制-----------------*/
  }
}
