package org.koudai.scala.chapter11.operator
//import scala.math.sqrt
/**
 * 操作符
 */
object Operator {
  def main(args: Array[String]): Unit = {
    /*
     * 变量、函数、类等的名称统称为标识（zhi 四声）符
     * 标识符的规则如下：
     * 1.java的标识符命名规则如下：字母和数字字符的序列，以字母或下划线开头。java中的规则scala也都遵守。
     * 2.scala和java一样，可以使用Unicode字符。示例如下：
     */
    val èÅ = "a" //更多Unicode字符详见：http://unicode-table.com/cn/#control-character

    /*
     *3.除字母、数字、下划线、括号()[]{}或者分隔符.,;'"之外的ASCII字符。即如下面这些：
     * !#%&*+-/:<=>?@\^|~
     * 注意：上述符号中没有乘号，因为其被用来当作注释
     *
     * 4.Unicode的数学符号，或Unicode的Sm和So类别中的其他符号。示例如下：
     * ** 是一个合法的标识符，√也是
     */
   // val √ = sqrt _
    val √ = scala.math.sqrt _
    println(√(2))
    val ** = 1
    println(**)
  }
}