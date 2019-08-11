package org.koudai.scala.chapter4.mapping

import scala.collection.mutable

/**
  * 映射
  * 注意：这里scala Object的名字不能叫Map，会造成代码报错
  */
object MyMap {
  def main(args: Array[String]): Unit = {
    /*----------------定义和构造映射----------------------*/
    //定义 值不可变映射Map[String,Int]
    val scoreVal = Map("Alice" -> 10,"Bob" -> 3,"Cindy" -> 8)
     //"上述示例中的 -> 符号用来给key赋值,实际上也使代码更加易读,可以理解为一个key指向这个key的值"也可写为如下形式:
    val s = Map(("Alice",10),("Bob",3),("Cindy",8))
    println(scoreVal)
    println(s)
    //定义值可变映射(注意：这里的Map跟上述的不可变Map不相同，二者没有在同一个包中，只是名字相同）
    val scoreVar = scala.collection.mutable.Map("Alice" -> 10,"Bob" -> 3,"Cindy" -> 8)
    println(scoreVar)
    //定义空映射
    val scoreEmpty = new mutable.HashMap[String,Int]
    println(scoreEmpty)
    //下述形式的调用将会返回一个Option对象（后续讲解），要么是Some（键对应的值），要么是None
    //    map.get(key)
    println(scoreVal.get("Alice"))
    println(scoreEmpty.get(""))

    /*------------------更新映射中的值----------------------*/
    /*
     * 注意：只能对可变类型的映射中的值进行更改
     * 示例如下：
     */
    scoreVar("Bob") = 10 //将Bob的成绩修改为10（假定scoreVar是可变的）
    scoreVar("Hjm") = 100 //将Hjm的成绩添加到scoreVar（假定scoreVar是可变的）
    println(scoreVar)

    //一次更新多个值
    scoreVar += ("HJM1" -> 10,"HJM2" -> 11)
    println(scoreVar)

    //移除HJM2
    scoreVar -= "HJM2"
    println(scoreVar)

    //以一个不可变的映射为基础，对其副本做更改操作后得到一个新的映射（原映射没有被改变）
    val scoreVal2 =  scoreVal +("Bob" -> 10,"HJM" -> 10)
    println(scoreVal2)

    //同上，我们也可以对一个不可变的映射进行删除操作，得到一个新的映射（原映射同样没变）
    val scoreVal3 =  scoreVal - "Bob"
    println(scoreVal3)
     /*
      * 虽然我们对不可变的映射操作后会额外生成新的映射，
      * 但是老的映射和新的映射共享大部分结构，所以这种
      * 操作的效率并不是很低。
      */

    /*----------------------迭代映射----------------------*/
    //迭代map
    for ((k,v) <- scoreVar)
      print(k+":"+v+"  ")
    println

    for (elem <- scoreVar) {
      println(elem._1+":"+ elem._2)
    }

    //迭代key
    for(k <- scoreVar.keySet)
      print(k+"\t")
    println

    //迭代value
    for(v <- scoreVar.values)
      print(v+"\t")
    println

    //翻转一个map中的key和values
    val newMap = for((k,v)<- scoreVar) yield (v,k)
    //迭代翻转后的map  key相同会覆盖
    for ((k,v) <- newMap)
      print(k+":"+v+"\t")
    println
    /*
     * 注意：
     * 翻转后的map中的键值对的数量可能给翻转之前不一样，
     * 这是由于翻转前多个key的值相同造成的，新map中的key也不可以相同
     */
   //TODO
    /*---------------------已排序映射----------------------------*/
    /*
     * scala中的map默认实现是哈希map。
     * 如果业务中使用的map没有很好的哈希函数
     * 或者需要顺序的使用所有的键就需要一个树map。
     */
    //得到一个不可变的树形映射（键有序）
    val scoresTreeMapImmutable = scala.collection.immutable.SortedMap("Alice" -> 10,"Cindy" -> 8,"Bob" -> 3)
    for ((k,v) <- scoresTreeMapImmutable) {
      print(k+":"+v+"\t")
      println()
    }
    //得到一个可变的树形映射（键有序）
    // Scala目前基于平衡树的映射只提供了不可变版本。
   /* val scoresTreeMapMutable = scala.collection.mutable.SortedMap("Alice" -> 10,"Cindy" -> 8,"Bob" -> 3)
    for((k,v) <- scoresTreeMapMutable)
      print(k+":"+v+"\t")
    println
*/
    //但是如果需要按照插入的时候的顺序访问所有键，可以使用LinkedHaseMap。（其实这要求数据输入时要有序）
    val months = scala.collection.mutable.LinkedHashMap("Jan" -> 1,"Feb" -> 2,"Mar" -> 3,"Apr" -> 4)
    for((k,v) <- months)
      print(k+":"+v+"\t")
    println

    //
    /*---------------------与java互操作----------------------------*/
    /*
     * 将java中的映射转换成scala中的映射
     */
    import scala.collection.JavaConversions.mapAsScalaMap
    //将java中的TreeMap转换成scala中的映射
    val scores:scala.collection.mutable.Map[String,Int] =
      new java.util.TreeMap[String,Int]

    import scala.collection.JavaConversions.propertiesAsScalaMap
    //从java.util.Properties到scala中的Map[String,String]的转换
    val props:scala.collection.Map[String,String] = System.getProperties()

    /*
     * 将Scala映射传递给预期java映射
     */
    import scala.collection.JavaConversions.mapAsJavaMap
    import java.awt.font.TextAttribute._
    val attrs = Map(FAMILY -> "Serif",SIZE -> 12)//scala映射
    val font = new java.awt.Font(attrs) //该方法预期一个Java映射
  }
}
