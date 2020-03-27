package org.unicom.scala.chapter2.controlandfunction

/**
  * scala函数
  */
object Function {
  /*-----------------------------scala函数-----------------------------*/
  /*
   * 在scala中，除了支持方法（还没讲）外，还支持函数。
   * 方法对对象进行操作，函数则不是。想在java中实现函数的功能，只能使用静态方法来模拟。
   * 函数定义如下：
   */
  def abs(x:Double) = if(x>=0) x else -x
  /*
 * scala的函数定义中，我们必须给出所有的参数类型。不过，只要函数不是递归的，我们就不需要指定返回类型。
 * scala编译器可以通过=符号右侧的表达式的类型推断出返回类型。
 * 		如果函数体需要多个表达式完成，可以使用代码块。块中的最后一个表达式的值就是函数的返回值。
 * 如下示例中：返回的值即为for循环之后的r的值
 */
  def fac(n: Int) = {
    var r = 1
    for (i <- 1 to n) r = r * i
    r
  }
  //在上述示例中，我们也可以使用return关键字来控制函数的结束，但是在scala中这并不常见。
  //后期我们会逐渐明白为什么这么做，我们一般仅在需要跳出循环时使用return。

  //对于递归函数，我们必须指定返回类型。示例如下：
  def fac2(n: Int): Int = if (n <= 0) 1 else n * fac2(n - 1)
  /*
     * 某些编程语言（如ML和Haskell）能够推断出递归函数的类型，用的是Hindley-Milner算法。
     * 不过，在面向对象的语言中这样做并不总是行得通。如何扩展Hindley-Milner算法让它能够
     * 处理子类型仍然是个科研命题。
     */

  /*----------------------------默认参数和带名参数------------------------------------*/
  /*
   * 我们在调用某些函数时并不显示的给出所有参数值，对于这些函数我们可以使用默认参数。
   * 示例如下：
   */
  //定义decorate（英文意思：修饰 ['dekəreɪt]）函数
  def decorate(str: String, left: String = "[", right: String = "]") = left + str + right

/*  def main(args: Array[String]): Unit = {
    print(decorate("hjm"))
  }*/
  /*-------------------------------变长参数------------------------------------------*/
  /*
   * scala支持函数的参数可以有不确定的个数，类似于java中的可变参数
   * 示例如下：
   */
  //统计任意多个数字的和
  def sum(args:Int*)={
    var result = 0
    for (i <- args) result += i
    result
  }
/*   def main(args: Array[String]): Unit = {
    print(sum(1,2,3,4))
  }*/
  /*
     * 注意：如果我们需要调用变长参数且参数类型为Object的java方法时，例如PrintStream.printf
     * 或MessageFormat.format时，你需要手工对基本类型进行转换。（暂时了解即可）
     * 示例如下：
     */
  //使用MessageFormat时，会自动在类头部引入java.text.MessageFormat
  //val str = MessageFormat.format("The answer to {0} is {1}","everything",42.asInstanceOf(AnyRef));

  /*----------------------------------过程------------------------------------*/
  /*
   * scala中对于不返回值的函数有特殊的表示法。如果函数体包含在花括号当中但没有前面的“=”号，
   * 那么这个时候返回类型就是Unit。这样的函数被称为过程（procedure [prə'siːdʒə]）。
   * 示例如下：
   */
  //定义一个函数把一个字符串打印的更好看
  def box(s:String): Unit ={
    var border = "-"*s.length + "--\n"
    println(border + "|" + s + "|\n" + border)
  }
  //上述函数还可以写成如下形式
  def box1(s:String): Unit ={
  }

/*  def main(args: Array[String]): Unit = {
    box("hjm")
  }*/

  /*--------------------------------main函数--------------------------------------*/
  /*
	 * 定义main函数
	 *
	 * 注意：main就是一个scala中最常见的函数
	 */
    def main(args: Array[String]): Unit = {
      	  //使用自定义的abs函数
      		println(abs(5))
      		println(abs(-5))

  //使用自定义的fac函数
    println(fac(2))
    println(fac(3))
    println(fac(2^32-1))
    println(fac(2^32))  //超过范围返回0

  //		//使用自定义的fac2函数
  		println()
  //
  //		/*-----------------使用自定义的decorate--------------------开始-----------*/
  		println(decorate("Hello"))//只写一个参数
  		println(decorate("Hello","<"))//写两个参数
  		println(decorate("Hello","<",">"))//写三个参数
  //
  //		//通过使用可以明显看到，如果某些参数我们不填写，scala会直接使用函数定义时候的默认值。
  //		//调用函数时指定参数名，参数顺序可以跟函数定义时的顺序不同。
  //		//调用函数时指定参数名可以让函数更加可读，这种方式对于有很多默认参数的函数来说也很有用。
  		val result = decorate(left = "《", str = "Hello",  right="》")
  		println(result)
  //		/*-----------------使用自定义的decorate--------------------结束-----------*/
  //
  //		//使用自定义的sum函数
  		val sumResult1 = sum(1,2,3)
  		println(sumResult1)

  //		/*
  //		 * 我们自定义的sum方法所需要的参数类型是Seq(后续讲解)，Seq与Range不同，
  //		 * 所以无法直接将1 to 100产生的Range作为sum方法的参数，我们可以使用下列方式
  //		 * 对Range进行转换：1 to 100:_*
  //		 * 示例如下：
  //		 */
  		val sumResult2 = sum(1 to 100:_*)
  		println(sumResult2)

  //使用自定义的box函数
      box("Hello world")
      println("*" * 100)
    }

}
