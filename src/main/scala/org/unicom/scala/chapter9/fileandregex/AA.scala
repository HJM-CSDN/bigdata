package org.unicom.scala.chapter9.fileandregex

object AA {
  def ceil(double:Double) = { double.round }
  def main(args: Array[String]): Unit = {
    val num = 3.14
    val fun = ceil _
    println(fun(num))

    //定义一个名叫hanshu1的函数
    def hanshu1 = (x:Double) => 3*x
    //定义一个匿名函数并将其赋值给hanshu这个val
    val hanshu = (x:Int) => 3*x
//    
//    //方式一：非中置表示法
//    Array(1,2,3).map((x:Int) => 3*x).foreach { x => print(x+"\t") }
//    
//    //方法二：中置表示法
//    val result = Array(1,2,3) map{ (x:Int) => 3*x }
//    result.foreach{x => println(x)}
//    
//    //首先定义一个参数是函数的函数
//    def ss(f:(Double) => Double) = f(0.25)
//    
//    //对上述定义的函数进行调用
//    println(ss((x:Double) => 3*x))
//    ss((x) => 3*x)   
//    ss(x => 3*x)
//    ss(3*_)
//    
//    val s = (1 to 9).map(_*0.1)
//    for(i <- s ) print(i + "\t")
//    println()
//    
//    def mulBy(f:Double) = (x:Double) => f * x
//    val triple1 = mulBy(3)
//    val triple = (x:Double) => 3 * x
//    val half = mulBy(0.5)
//    println(triple(14) + " " + half(14) )
//    
//    val r1 = 3 + 4 -> 5
//    println(r1)
//    val r2 = 3 -> 4 + "5"
//    println(r2)
//    
//    val nn = 1 to -10
//    println(nn)
//    nn.foreach { x => println(x) }
    val a = Set(1,7,2,9)
    val b = Set(2,3,5,7)
    println(a--b)
    println(b--a)
    
    println("Scala".zipWithIndex)
    println("Scala".zipWithIndex.max._2)
    println('S'.toInt)
  }
}
