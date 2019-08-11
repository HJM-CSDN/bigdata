package org.koudai.scala.chapter2.controlandfunction

/**
  * scala控制结构之循环
  * Circulation  [sɜːkjʊ'leɪʃ(ə)n] 循环
  */
object ControlCirculation {
  def main(args: Array[String]): Unit = {
    /*-----------------------while循环----------------------------*/
    /*
     * scala中的while循环和do循环与java中一致
     * 示例如下：
     */
    //while循环
    while(1>2){
      print("1>2")
    }
    //do while 循环
    do{
      print("1>2")
    }while(1>2)
    println
    /*-------------------------for循环--------------------------------*/
    /*
     *java中的for循环结构如下：
     * for(初始化变量；检查变量是否满足某条件；更新变量)
     * scala中没有跟上述java中的for循环一致的for循环，若想使用，可使用while循环或者如下形式的for循环
     */
    //1.Range形式的循环
    //i从1开始循环到10(包括10)
    //实际上这个循环的本质是遍历1 to 10后产生的Range，这个循环会依次取得Range中的每个值
    for(i <- 1 to 10)
      println("i is " + i)
    //上述循环也可以写成如下
    for (i <- 1 to 10 ) println("i is " + i);

    //2.对字符串的循环
    //常规方式遍历循环字符串
    val str = "hello"
    for (i <- 0 until str.length())
      print(str(i) + "\t")
    //输出一个空行
    println()

    //scala方式遍历循环字符串（不需要下标）
    for (tempStr <- "Hello")
      print(tempStr + "\t")
    //输出一个空行
    println

    //注意：在scala中，循环的使用并不如其他语言那么频繁。
    /*------------------------------scala跳出循环的方式------------------------------------*/
    /*
     * 在scala中，并没有提供break和continue语法。但是支持return语法。
     * 如果我们想实现类似java中的break跳出方式时，有以下几个方式：
     */
    //1.使用一个Boolean类型的控制变量，在合适的时候return。
    //2.使用嵌套函数--可以从函数当中return。
    //3.使用Breaks对象中的break方法，形式如下：
    //导入Breaks包（注意：在java中，不能在类内导包。这里也只是为了演示才在这个位置导包，以后仍旧需要在类外部导包）
    import scala.util.control.Breaks._
    //定义breakeable函数
    breakable{
      println(1)
      println(2)
      println(3)
      break//跳出breakable函数
      println(4)
    }

    /*-------------------------高级for循环--------------------------*/
    /*
     * 在scala中，for循环比在java中要更加高级。
     * 示例如下：
     */

    //类似于java中的双层for循环（在scala中称i和j为两个生成器）
    for (i <- 1 to 3;j <- 1 to 3) print ((10*i + j) + " ")
    println

    //类似于java中的双层for循环且带条件（每个生成器都可以带守卫--即以if开头的Boolean表达式）
    for(i <- 1 to 3; j <- 1 to 3 if i!=j ) print ((10*i + j)+ "  ")
    println

    //带有多种条件的for循环表达式之一
    for(i <- 1 to 3; from = 4-i; j <- from to 3 ) print ((10*i + j)+ "  ")
    println

    //带有多种条件的for循环表达式之二
    for(i <- 1 to 3; from = 4-i; j <- from to 3 if i!=j ) print ((10*i + j)+ "  ")
    println

    /*----------------------for循环的推导方式--------------------------*/
    /*
     * 如果for循环的循环体以yield（[jiːld]英文意思：出产）开始，则该循环会构造出一个集合，
     * 在scala中表示集合的单词为：Vector ['vektə] 有向量、矢量的意思。以后会详细讲解
     * 每次迭代生成集合中的一个值，示例如下：
     */
    val myVector = for(i <- 1 to 10) yield i % 3
    println(myVector)
    //上述类型的循环叫做for推导式。
    //for推导式生成的集合与它的第一个生成器是类型兼容的。示例如下：

    //方式一:将生成一个字符串(因为第一个生成器是字符串)
    val c1 = for(c <- "Hello"; i<- 0 to 1) yield (c+i).toChar
    println(c1)

    //方式二：将生成一个字符集合（因为第一个生成器是Range）
    val c2 =for(i <- 0 to 1;c <- "Hello")yield (c+i).toChar
    println(c2)

    /*----------------------for循环语法问题--------------------------------*/
    /*
     * 上述的所有for循环中，循环条件都写在小括号中并用分号分割开，
     * 因为scala的语法支持不写分号，所以for循环还可以写成如下形式（使用大括号加换行以避免分号）：
     */
    for{
      i<- 1 to 3
      from = 4-i
      j<- from to 3
    }
      print ((10*i + j)+ "  ")
  }
}
