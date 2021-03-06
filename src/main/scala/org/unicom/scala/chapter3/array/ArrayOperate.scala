package org.unicom.scala.chapter3.array

import scala.collection.mutable.ArrayBuffer

/**
 * 数组操作
 * 1.遍历
 * 2.求和、极值
 * 3.排序
 * 4.自定义排序
 * 5.自定义数组显示样式
 * 6.其他用法
 */
object ArrayOperate {
  //定义main函数
  def main(args: Array[String]): Unit = {

    /*-------------遍历数组和循环------------------*/
    /*
     * 在java中，数组和集合在操作上语法有些不同，scala则更加统一一些。
     * 示例如下：
     */
    println("定义数组: val a = Array(1,5,9)")
    //定义数组
    val a = Array(1, 5, 9)
    print("i从0开始取数组中a的所有值:")
    println //println可不加()
    //i从0开始取数组a中的所有值
    for (i <- 0 until a.length)
      print(i + ": " + a(i) + "  ")
    println

    /*
     * until 是RichInt上的方法，返回所有小于（但不包括）上限的数字。
     * 例如： 0 until 5 //生成Range(0,1,2,3,4)
     * 实际上0 until 5是0.until(5) 的scala简写形式
     */

    //总结来说，示例中的for循环的最终形式如下：
    //    for(i<- Range)//即遍历一个Range中的所有元素

    println("将数组a中的元素以两个一跳的形式遍历循环:val range2 = 0 until (a.length, 2)")
    //将数组a中的元素以两个一跳的形式遍历循环
    val range2 = 0 until (a.length, 2)
    for (i <- range2)
      print(i + ": " + a(i) + "  ")
    println

    println("将数组a中的元素倒序遍历:val t1: Range = 0 until a.length    val t2: Range = t1.reverse")
    //将数组a中的元素倒序遍历
    val t1: Range = 0 until a.length
    val t2: Range = t1.reverse
    for (i <- t2)
      print(i + ": " + a(i) + "  ")
    println

    println("不使用数组下标遍历数组(类似于java中的高级for循环)")
    //不使用数组下标遍历数组(类似于java中的高级for循环)
    for (i <- a)
      print(i + "  ")
    println

    /*----------------------数组转换---------------------------*/
    /*
     * 数组转换的本质是对一个原始数组进行遍历操作的同时进行一些其他操作，
     * 操作完成后形成新的结果数组，原始数组不受影响
     */
    println("使用for推导式遍历一个数组并对每一个数据进行相同的操作")
    println("var a1 = Array(1,2,5)  var a2 = for(i <- a1) yield 2 * i")
    //使用for推导式遍历一个数组并对每一个数据进行相同操作
    val a1 = Array(1, 2, 5)
    val a2 = for (i <- a1) yield 2 * i
    for (i <- a2)
      print(i + "  ")
    println

    println("使用带条件判断(scala中称之为守卫)的for推导式对一个数组进行操作")
    println("var a3 = for (i <- a1 if i % 2 == 0) yield 2 * i")
    //使用带条件判断（scala中称之为守卫）的for推导式对一个数组进行操作
    val a3 = for (i <- a1 if i % 2 == 0) yield 2 * i
    for (i <- a3)
      print(i + "  ")
    println

    println("另一种写法:val a4 = a1.filter(_%2==0).map(2*_),甚至都用{}")
    //另一种写法是(注意小括号里的内容为_% 2 ==0)
    val a4 = a1.filter(_ % 2 == 0).map(2 * _)
    for (i <- a4)
      print(i)
    println()
    //甚至(注意大括号里的内容为_% 2 ==0)
    val a5 = a1.filter { _ % 2 == 0 } map { 2 * _ }

    /*
    * 总结：上述使用filter和map结合的形式相对于使用守卫和yield的形式来说
    * 仅仅是形式上的区别，本质上都是for循环加条件判断，底层没有任何区别
    */

    /*-------------------------数组求和、极值---------------------*/
    println("对Array和ArrayBuffer进行求和: val sumArray = Array(1,2,3).sum  对数组使用sum,数据类型必须是数值类型")
    //对Array和ArrayBuffer进行求和
    val sumArray = Array(1, 2, 3).sum //注意：对数组使用sum方法时，数据类型必须是数值类型 
    println("sumArray: " + sumArray)

    println("最大值:")
    //最大值和最小值
    val maxArray = Array(1, 2, 3).max //注意：对数组使用max/min方法时，数据类型必须是数值类型 
    println("maxArray: " + maxArray)

    /*--------------------------数组排序------------------------*/
    val oldArray = Array(1, 6, 3, 8, 9)
    //对数组或数组缓冲下行代码皆适用
    val newArray = oldArray.sorted //注意：oldArray并没有被改变，newArray是一个新的数组
    //对数组而言下行代码才适用
    val newArray1 = scala.util.Sorting.quickSort(oldArray) //注意：oldArray被改变 返回值是Unit类型

    //循环输出排序后的数组
    println("newArray")
    for (i <- newArray)
      print(i + " ")
    println
    println("newArray1")
    println(newArray1)
    println("oldArray")
    for (i <- oldArray)
      print(i + " ")
    println

    println("    /*-------------------------自定义排序---------------------*/\n    //自定义排序即需要我们自定义一个比较函数（后续讲解），然后使用sortWith方法\n    val arr = Array(1, 8, 5, 2, 2, 9)")
    /*-------------------------自定义排序---------------------*/
    //自定义排序即需要我们自定义一个比较函数（后续讲解），然后使用sortWith方法
    val arr = Array(1, 8, 5, 2, 2, 9)

    println("    //方式一：使用    <  这个函数来进行排序（即：正序排序）\n    val arr1 = arr.sortWith(_ < _)\n    //循环输出排序后的数组\n    println(\"正序排序结果：\")\n    for (i <- arr1)\n      print(i + \" \")\n    println")
    //方式一：使用    <  这个函数来进行排序（即：正序排序）
    val arr1 = arr.sortWith(_ < _)
    //循环输出排序后的数组
    println("正序排序结果：")
    for (i <- arr1)
      print(i + " ")
    println

    println("    //方式二：使用 > 这个函数来进行排序（即：倒序排序）\n    val arr2 = arr.sortWith(_ > _)\n    //循环输出排序后的数组\n    println(\"倒序排序结果：\")\n    for (i <- arr2)\n      print(i + \" \")\n    println")
    //方式二：使用 > 这个函数来进行排序（即：倒序排序）
    val arr2 = arr.sortWith(_ > _)
    //循环输出排序后的数组
    println("倒序排序结果：")
    for (i <- arr2)
      print(i + " ")
    println

//手写一个例子:
    val arr123 = Array(1,2,3,6,2,4,7,9,4)
    val l = arr123.sortWith(_>_)
    for (elem <- l) {
      print(elem + "  ")
    }
    println
    scala.util.Sorting.quickSort(arr123) //默认正排序
    for (elem <- arr123) {
      print(elem + "  ")
    }
    println

    /*--------------------------自定义数组显示样式--------------------*/
    val arrDemo = Array(1, 2, 3, 4)
    //直接输出arrDemo，默认底层调用的是java的toString方法，除了这个数组底层的hash地址，并没有太多的含义
    println("直接调用java的toString方法输出："+arrDemo)

    //自定义数组的显示样式1
    println(" println(\"自定义样式一输出：\"+arrDemo.mkString(\" and \"))")
    println("自定义样式一输出："+arrDemo.mkString(" and "))
    println("println(\"自定义样式二输出：\"+arrDemo.mkString(\"<\", \",\", \">\"))")
    //自定义数组的显示样式2
    println("自定义样式二输出："+arrDemo.mkString("<", ",", ">"))
    //参数顺序可以自定义,但是三个参数都要写上,并且用"="
    println(" println(\"自定义样式二输出：\"+arrDemo.mkString(end=\"<\",sep=\",\",start=\">\"))")
    println("自定义样式二输出："+arrDemo.mkString(end="<",sep=",",start=">"))
    
    /*--------------------------数组的其他常见用法--------------*/
    //定义一个示例数组
    val myArr = Array(1,2,3)
    //统计数组中有多个大于0的元素
    val myArrCount = myArr.count{_>0}  //不支持等号
    println("示例数组中满足条件的元素的个数：" + myArrCount)

    //剩余用法查询scala的api
    
  }
}