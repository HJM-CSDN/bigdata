package org.koudai.scala.chapter9.fileandregex

import scala.util.matching.Regex

/**
 * scala正则表达式
 */
object regex {
  def main(args: Array[String]): Unit = {
    /**
     * scala中使用scala.util.matching.Regex来实现正则。
     * 要构造一个Regex对象，调用String类的r方法即可。
     * 示例如下：
     */
		val numPattern:Regex = "[0-9]+".r//首先定义数值类型的正则

    //遍历所有匹配项的迭代器,使用findAllInt方法
    for(matchString <- numPattern.findAllIn("898ds,87 sdf"))
      print(matchString + "\t")
    println()
    
    //找到字符串中的首个匹配项，使用findFirstIn
    val m1:Option[String] = numPattern.findFirstIn("99d5dd65") //这里的返回值类型是Option[String]，后续讲解
    println(m1)
    
    //检查字符串中的开始部分是否能匹配，使用findPrefixOf
    val b1: Option[String] = numPattern.findPrefixOf("axxxx000")
    val b2: Option[String] = numPattern.findPrefixOf("95axxxx000")
    println("b1: " + b1)
    println("b2: " + b2)
    
    //替换首个匹配项或全部匹配项
    val res1 = numPattern.replaceFirstIn("99h55", "xx")
    println("res1: " + res1)
    
    val res2 = numPattern.replaceAllIn("99h5", "xx")
    println("res2: " + res2)
    
    /*------------------正则表达式组----------------------*/
    /**
     * 正则表达式组可以提取多组值，每个值都用小括号圈住
     */
    //匹配字母或数字  ，如果正则表达式含有反斜杠或者引号，可以使用""""这种双 双引号的形式
    val regex:Regex = "([0-9]+) ([a-z]+)".r
    val content = "12 xiaowang"  
    val regex(age,name) = content  //将num设为123，str设为xiaowang
    //其实上述语句本质是Regex类中定义了一个unapply方法，上述调用等同于下述调用
    //val regex(age,name) = Regext.unapply(content)

    println(name +":" + age) //单个匹配，都得匹配上  
    //多个匹配
    for(regex(age,name) <- regex.findAllIn("99 xiaoli,jsdhfksdh 12 huge"))
      println("name: " + name + "\tage: " + age)
  }
}