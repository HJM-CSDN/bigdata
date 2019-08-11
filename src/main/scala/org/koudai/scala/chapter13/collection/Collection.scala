package org.koudai.scala.chapter13.collection

import scala.collection.mutable

/**
 * 集合
  * 所有集合都扩展自Iterable特质
  * 集合有三大类,分别为序列. 集 和映射
 */
object Collection {
  def main(args: Array[String]): Unit = {
    val a = IndexedSeq(1,2,3,4,5,5,4,3,2,"1",1 to 3)
    //每个scala集合特质或类都有一个带有apply方法的伴生对象,这个apply方法可以用来构建集合中的对象
    //(一般.apply都省略的)  这样的设计叫做统一创建原则
    //val b = mutable.SortedSet.apply(1,4,3,2,6,4,32,2)
    val b = mutable.SortedSet(1,4,3,2,6,4,32,2)
    println(a)
    println(b)

    //可变集合和不可变集合
    /**
      * Scala优先采用不可变集合,
      */


  }
}