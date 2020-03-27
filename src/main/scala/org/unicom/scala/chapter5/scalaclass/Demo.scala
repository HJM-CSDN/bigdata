package org.unicom.scala.chapter5.scalaclass

/**
 * 示例，用来创建类的对象并使用类上的方法 
 * 注意：下述代码以object开头，scala中的类也可以定义main方法，但是不可直接执行
 */
object Demo {
  //定义main函数
  def main(args: Array[String]): Unit = {
    /*----------------------构造器示例--------------------------*/
    val test1_1 = new Test1("张三",10)
    val test1_2 = new Test1("张三")
    val test1_3 = new Test1(age=10,name="张三")

    val test2 = new Test2("张三",10)
    
    /*----------------------其它示例--------------------------*/
    //创建ScalaClass类的对象
    val sc = new ScalaClass //使用默认构造函数， 小括号可带可不带

    /*
     * 在scala中，修改值的函数如果没有参数一般默认带上小括号
     * 取值的函数如果没有参数一般默认不带小括号
     * 示例如下：
     */
    //使用取值函数，默认不带小括号
    val old = sc.current
    println(old)
    //使用改值函数，默认带小括号
    sc.increment()
    println(sc.current)

    /*--------------------setter和getter属性-------------------*/
    //age属性的getter方法默认就叫做age
    val ageOld = sc.age //等同于sc.getAge()
    println(ageOld)




    //age属相的setter方法默认叫作age_=，但是使用起来略有不同
    sc.age = 20 //等同于调用sc.setAge(20)
    println(sc.age)

  }
}