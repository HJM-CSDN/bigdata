 package org.unicom.scala.chapter10.mytrait

import java.io.PrintWriter

object mytrait1 {
  
  def main(args: Array[String]): Unit = {
    val a=Array(1,2,3,4,5)
     val b = a.reverseMap( {x:Int => x*10} )
    println(b.mkString(","))

    val c = List("I", "have", "a", "beautiful", "house")
    for (elem <- c) {
      println(elem)
    }
  }
  
}

