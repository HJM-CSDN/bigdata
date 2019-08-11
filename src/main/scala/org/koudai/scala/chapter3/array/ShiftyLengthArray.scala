package org.koudai.scala.chapter3.array

/**
 * scala中变化长度的数据
 */
object ShiftyLengthArray {
  //定义main函数
  def main(args: Array[String]): Unit = {
    /*
     * 对于长度需要变化的数组，java有ArrayList。
     * scala中的等效的数据结构为ArrayBuffer。
     * 在scala中，变长数组又称之为数组缓冲
     */
    //与java中使用ArrayList类似，需要导入ArrayBuffer包（在这里导入只为讲课使用）
    import scala.collection.mutable.ArrayBuffer
    
    //准备一个空的变长数组，准备存放数据
    val b = ArrayBuffer[Int]() 
    //上述代码也可以写成如下方式，注意：有new的时候就不需要小括号了 
    val b1 = new ArrayBuffer[Int]
    
    //使用+=符号在数组尾部添加一个元素
    println("使用+=符号在数组尾部添加一个元素:b += 1;")
    b += 1;    println(b)
    
    //使用+=符号在数组尾部添加多个元素
    println("使用+=符号在数组尾部添加多个元素:b += (1,2,3);")
    b += (1,2,3);    println(b)
    
    //使用append方法往数组缓冲尾部添加任意多个元素
    println("使用append方法往数组缓冲尾部添加任意多个元素:b.append(5,5,5);")
    b.append(5,5,5); println(b)
      
    //以++=操作符追加任何集合
    println("以++=操作符追加任何集合:b ++= Array(5,8);")
    b ++= Array(5,8);  println(b)
    
    //往数组缓冲b中追加一个数组或者数组缓冲（即：任何带有TraversableOnce特质的集合）
    println("往数组缓冲b中追加一个数组或者数组缓冲（即：任何带有TraversableOnce特质的集合）:b.appendAll(b);")
    b.appendAll(b); println(b)
    
    //移除最后3个元素
    println("移除最后3个元素:b.trimEnd(3);")
    b.trimEnd(3); println(b)
    println("移除开头3个元素:b.trimStart(3);")
    b.trimStart(3); println(b)
    /*
     * 1.在数组缓冲的尾端添加或移除元素是一个高效的操作
     * 2.在其他任意位置插入或移除元素，这样的操作并不高效--所有在这个位置之后的元素都必须被平移
     *  （原因：底层是java的数组结构，数组本身的数据结构导致这样的效果，与java中的ArrayList与LinkedList类似）
     *  示例如下：
     */
    
    //在b的下标为1的地方插入6这个值
    println("在b的下标为1的地方插入6这个值:b.insert(1,6);")
    b.insert(1, 6); println(b)
    
    //在b的下标为2的地方开始，插入9、8、7这三个值
    println("在b的下标为2的地方开始插入9.8.7这三个值:b.insert(2,9,8,7):")
    b.insert(2, 9,8,7); println(b)
    
    //移除任意位置上的数据(本例中移除下标为2的那个数据)
    println("移除任意位置上的数据:b.remove(2):")
    b.remove(2); println(b)
    
    //移除从下标2开始，往后的2个数据
    println("移除从下标2开始,再往后的两个数据:b.remove(2,2)")
    b.remove(2,2); println(b)
    
    //如果我们需要一个定长数组，但是不知道最终需要装多少元素的。
    //可以先定义一个数组缓冲，然后调用如下形式的方法即可:
    b.toArray;
    
    //反过来假如我们需要将一个数组a转换为数组缓冲，则执行如下形式的代码。
    //a.toBuffer
    println(b.toBuffer)
    println()
  }  
}