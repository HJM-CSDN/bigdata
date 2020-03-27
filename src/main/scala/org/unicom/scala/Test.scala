package org.unicom.scala

import scala.collection.mutable.ArrayBuffer

object Test0 {
  def main(args: Array[String]): Unit = {
    ArrayBuffer
    //Scala中获取字符串"Hello"的首字符和尾字符
    print("Hello".take(1),"Hello".reverse(0))
    print("Hello".take(1),"Hello".takeRight(1))
    println
    val a = List("Leo","Jen","Peter","Jack")
    println(a)

    //给定常量val m = Map(("Tom",20),("Bob",25)),要求对常量m中所有的value
    //翻倍并保存结果
    val m = Map[String,Int](("Tom",20),("Bob",25))
    m.map(x=>(x._1,x._2*2)).foreach(println)
    println(for (elem <- m) yield {(elem._1,elem._2*2)})
    val n = m.map(x=>(x._1,x._2*2))//保存结果

    //计算1+2+3+...+100的结果并输出(输出要求带换行)
    var sum = 0
    for(i <- 1 to 100)
      sum += i
    println(sum)

    val dd = Array(1.2,1.2,1.2)
    println(average(dd))
    def average(a:Array[Double]): Double ={
      var sum = 0.0
      for (i<- a){
        sum += i
      }
      sum/a.length
    }

    val tuple = (1,"s")
    println(tuple._1)

    //定义一个匿名的一参函数,作用是将给定的int类型的参数乘以3并输出
    val abv = (x:Int)=>x*3
    println(abv(3))

    val arr = Array("1dd","2")
    println(arr(0))
  }
}
