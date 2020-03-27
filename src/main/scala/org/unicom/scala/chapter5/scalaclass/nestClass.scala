package org.unicom.scala.chapter5.scalaclass

/**
 * scala嵌套类
 */
class nestClass {
  /* 
   * nest 音[nest] 嵌套
   * 在scala中，你几乎可以在任何语法结构中内嵌任何语法结构
   * 例如：你可以在函数中定义函数，在类中定义类。
   */
  
  //内部类innerA
  class innerA{
  }
  
  //定义两个本类的对象
  val nestClass1 = new nestClass
  val nestClass2 = new nestClass
  
  /*
   * 在scala中，每个本类（nestClass）的对象都有一个自己的内部类innerA，
   * nestClass1.innerA 和  nestClass2.innerA是不同的两个类。
   * 这与java不同，java中内部类从属于外部类（scala中从属于外部类的对象）。
   * 
   * scala创建内部类的对象的语法：
   * new 外部类的对象.内部类类名
   * 
   * java创建内部类的对象的语法：
   * 外部类的对象.new 内部类类名()
   * 
   * 在内嵌类中对外部的类的访问（这点上scala与java相同）
   * 外部类.this
   */
}

