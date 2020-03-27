package org.unicom.scala.chapter5.scalaclass

/**
 * scala类的构造器
 */
object ScalaClassConstructor {
  
}
/*
 * java中有默认构造器，scala中称之为主构造器（primary constructor），
 * 在scala中，除了主构造器之外，其他的构造器都称之为辅助构造器（auxiliary constructor）
 */

/*
 * 主构造器名字与类名交织在一起
 * 注意：下述示例皆是单独的类
 * 示例如下：
 */

//示例1：scala中类名后带参形式（这些参数还可以有默认值，会自动赋值给对象）
class Test1(val name:String,val age: Int = 0){
  //java中不允许类名后带参形式,需要写类的成员变量
}

/*
 * 注意：
 * 参数可以有任意权限，如果参数不带var或者val
 * 其权限等同于private[this]（本章后续内容讲解）
 */

//主构造器也可以变成私有的，其形式是在类名后面，类参数的前面 写private
//这样就可以使用辅助构造器来实现单例了。

//示例2：scala的类中直接写执行语句（java中执行语句必须写在方法内部）
//       这时，可执行语句也是主构造器的一部分
class Test2(val name: String="某某某", val age: Int) {
  println("My name is: " + name, "and my age is:" + age)
}


//辅助构造器示例
class Test3 {
  /*
   * 辅助构造器与java中的非默认构造器的区别如下：
   * 1.辅助构造器的名称为this（java中所有的构造器名称都与类名相同--会造成修改类名不方便）
   * 2.每一个辅助构造器都必须以一个  对先前已定义的辅助构造器或者主构造器 的调用开始
   * 示例如下：
   */
  var stuName = ""
  var stuAge = 0
  //辅助构造器1  相当于java中带一个参数的构造函数
  def this(stuName: String) {
    this() //首先调用主构造器
    this.stuName = stuName
  }
  //辅助构造器2   相当于java中带有两个参数的构造函数
  def this(stuName: String, stuAge: Int) {
    this(stuName) //首先调用前一个辅助构造器    
    this.stuAge = stuAge
  }
}
 