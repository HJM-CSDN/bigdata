package org.koudai.scala.chapter10.mytrait

import org.koudai.scala.chapter8.extend.test
import java.io.PrintWriter

/**
 * scala特质（即java中的接口）
 */
object mytrait {
  /*---------------为什么没有多继承-----------------*/
  /*
   * 子类中容易造成变量的定义混乱
   */
  /*---------------当做接口使用的特质-----------------*/
  //定义一个普通特质
  trait logger{
    def log(msg: String) //接口中定义的普通抽象方法
  }
  //定义一个带有方法实现的特质（java1.8中接口也可以带有默认实现）
  trait logger1{
    //接口中定义带实现的方法
    def log1(msg: String) {}
  }
  
  //实现一个特质
  class test extends logger{ //子类实现一个特质的时候使用extends关键字，与java不同
    //实现特质中的抽象方法时不需要override关键字，但是需要像普通方法定义一样使用def
    def log(msg: String){
      //方法实现 略
    }
  }
  
  //定义类继承多个特质时使用with关键字
  class test1 extends logger with logger1{
     def log(msg: String){
      //方法实现 略
      }
     //直接调用logger1中的已经有默认实现的方法
     log1("你好")
  }
  
  /*---------------带有特质的对象-----------------*/
  /**
   * 简单来说，带有特质的对象就是在对象构造时指定其所需要的特质
    *
   * 示例如下：
   */
  //定义一个高级logger接口并实现里面的方法
  trait BestLogger extends logger{
    override def log(msg:String){
      println("hello   "+msg)
    }
  } 
  //创建Test类的对象（同时指定对象所需要的特质）
  val t1 = new test with BestLogger
  
  /*---------------叠加在一起的特质-----------------*/
  /**
   * 使用with关键字串联起来的有先后顺序的特质上实现的同一个抽象方法被调用的前后顺序不同。
   * 具体来说先从最后一个特质执行，依次往前。
   * 示例如下：
   */
  //定义教务特质，内定义一个进班的抽象方法
  trait jiaowu {
    def goToClassRoom(rule:String){
      println(rule)
    }
  }
  
  //班级们口有俩班主任，需要满足满班主任提出的要求才能进班
  //多个特质实现同一个特质中的抽象方法时需要加override才能使同时实现这两个特质的类不报错
  
  //定义班主任1
  trait teacher1 extends jiaowu{
    override def goToClassRoom(rule:String){
      super.goToClassRoom{"先脱鞋才能进班"}
    }
  }
  
  //定义班主任2
  trait teacher2 extends jiaowu{
    override def goToClassRoom(rule:String){
      super.goToClassRoom{rule}
    }
  }
  
  //定义学生1类，这类学生都先碰见班主任1后碰见班主任2
  class stu extends jiaowu{
    goToClassRoom("我是学生，没法自己定规矩")
  }
  
  
  
  /*---------------在特质中重写抽象方法-----------------*/
  trait traitParent{
    def method (str:String)
  }
  
//  trait traitSun extends traitParent{
//    abstract override def method (){
////      super.method(str:String)
//    }
//  }
  /*---------------在特质中重写抽象方法-----------------*/
  //定义构造器带输出的特质
  trait traitOut{    
    println("xxxxxxxxxxxxxxxx")//这条输出语句也是特质的构造器的一部分
  }
  class testTraitOut extends traitOut{ 
    println("y"*20)
  }
  def main(args: Array[String]): Unit = {
//		  val testT = new testTraitOut		  
//		  val rest = 1 + 2 toString()
//		  println(rest)
    
    println(scala.util.Random.nextInt(3)+1)
  
  }
  
}

