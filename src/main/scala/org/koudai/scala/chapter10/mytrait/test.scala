package org.koudai.scala.chapter10.mytrait

object test {
  def main(args: Array[String]): Unit = {
    val a = Array(0, 1, 2, 3, 4, 5)
    println(a.toString()) //WrappedArray(0, 1, 2, 3, 4, 5)
    val b = a.stringPrefix
    println(b) //WrappedArray

  }
}