package org.koudai.scala.chapter4.mapping

object Tuple {
  def main(args: Array[String]): Unit = {
    /*
     * 元组;不同类型的值的聚集
     *示例：
     */
    //定义元组
    val tuple = (1,2,3,"qq")

    //获取元组中的固定下标的数据
    println(tuple._2)

    /*---------使用模式匹配来获取元组的元素（类似于java中使用正则在父字符串中截取子字符串）------*/
    //匹配整行
    val (1,2,3,"qq") = tuple
    println(tuple._3)

    //匹配部分   //TODO
    val (1,_,_,_) = tuple
    println(tuple)

    /*
 * 元组可用去函数需要返回不知一个值得情况，StringOps的partition方法：
 * 输出一个字符串中满足条件和不满足条件的部分
 */
    println("New York".partition(_ isUpper))
    //输出对偶（“NY”,"ew ork")
    //_.isUpper 的 . 可以省略，但是空格不能省略

    /*-------------------------------------拉链操作-----------------------------------*/
    //symbol ['sɪmb(ə)l] 意思：符号、标志
    val symbols = Array("<","-",">")
    val counts = Array(2,10,2)
    //对符号数组使用zip方法 （以counts数组为参）形成一个多维数组
    val pairs = symbols.zip(counts)
    //输出zip后的多维数组的值
    for (elem <- pairs)
      print(elem+"\t")
    println
    //对多维数组使用统一的处理方式
    for((s,n) <- pairs)
      Console.print(s*n)
    println
    //使用toMap方法可以将对偶的集合转换成映射,语法如下
    //keys.zip(values).toMap
    println(symbols.zip(counts).toMap)

    /*-------------------完整示例--------------------*/
    val sales = Array(2,10,2) //商品销量数据
    val price = Array(1,10,1) //商品单价数据
    //对符号数组使用zip方法（以counts数组为参）形成一个多维数组
    val pairsNew = counts.zip(price)
    //声明一个变量记录总价
    var zongjia = 0
    for((s,n) <- pairsNew)
      zongjia += s*n
    println(zongjia)

    //解拉链操作
    val unzip = pairsNew.unzip

    for (elem <- unzip._1)
      print(elem + "\t")
    println
    for (elem <- unzip._2)
      print(elem  + "\t")
    println
  }

}
