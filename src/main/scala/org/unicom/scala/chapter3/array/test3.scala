package org.unicom.scala.chapter3.array

import scala.util.Random
import scala.collection.mutable.ArrayBuffer
import scala.collection.JavaConversions.asScalaBuffer
/**
 * 第三章练习
 */
object test3 {
  def main(args: Array[String]): Unit = {
    val b = Array(1, 5);
    val a = b.reverse
    a.foreach(println)

  }
  def deleteUnFirstF(arr : Array[Int]) = {
    val indexes = (for (i <- 0 until arr.length if arr(i) < 0) yield i)
    val rights  = indexes.reverse.dropRight(1)
    val tmp = arr.toBuffer
    for (index <- rights) tmp.remove(index)
    tmp 
  }

  
//1. 编写一段代码，将a设置为一个n个随机整数的数组，要求随机数介于0和n之间。
  def mkArray(n:Int)={
    val arr = new Array[Int](10)
    val myRandom = new scala.util.Random()
    //方式一：常规方式给数组中每个元素赋值
//    for(i <- 1 to n ){
//      arr(i-1) = myRandom.nextInt(n)
//    }
    
    //方式二：一次性生成一个新的数组，需要用一个新的变量接收
    val my = for(i <- arr) yield myRandom.nextInt(n)
    
//    for(i <- 0 to my.length-1) println(my(0))//遍历循环输出方式1
//    for(i <- my) print(i + "\t")//遍历循环输出方式2
    my.foreach(print)//遍历循环输出方式3
  }

//2. 编写一个循环，将整数数组中相邻的元素置换
   def revert(arr : Array[Int]) = {
    for (i <- 0 until (arr.length - 1, 2)) {
      val t = arr(i);
      arr(i) = arr(i + 1);
      arr(i + 1) = t;
    } 
  } 

//3. 重复前一个练习，不过这次生成一个新的值交换过的数组。用for/yield。
   def revert1(arr : Array[Int]) = 
    for (i <- 0 until arr.length if (i < (arr.length - 1) && i % 2 == 0) ) yield  {
      {
        val t = arr(i);
        arr(i) = arr(i + 1);
        arr(i + 1) = t;
      } 
      arr(i);
    } 
  

//4. 给定一个整数数组，产出一个新的数组，包含元数组中的所有正值，以原有顺序排列，之后的元素是所有零或负值，以原有顺序排列。

//5. 如何计算Array[Double]的平均值？
 
//6. 如何重新组织Array[Int]的元素将它们反序排列？对于ArrayBuffer[Int]你又会怎么做呢？
   
//7. 编写一段代码，产出数组中的所有值，去掉重复项。
   
//8. 重新编写3.4节结尾的示例。收集负值元素的下标，反序，去掉最后一个下标，然后对每一个下标调用a.remove(i)。比较这样做的效率和3.4节中另外两种方法的效率。

//9. 创建一个由java.util.TimeZone.getAvailableIDs返回的时区集合，判断条件是它们在美洲，去掉”America/“前缀并排序。
     
//10. 引入java.awt.datatransfer._并构建一个类型为SystemFlavorMap类型的对象，然后以DataFlavor.imageFlavor为参数调用getNativesForFlavor方法，以Scala缓冲保存返回值。
  
}