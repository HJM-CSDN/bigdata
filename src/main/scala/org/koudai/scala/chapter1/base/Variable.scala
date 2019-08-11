package org.koudai.scala.chapter1.base

/**
 * Scala base基础语法（变量和常量）
 */
object ScalaBase {
  
  /*------------------------------------------注意-----------------------------------------------*/
  /*-----scala编译器中，右键点击xxx.scala Refactor重名名时，xxx.scala内部名字不变，需要手动修改-----*/
  /*------------------------------------------注意-----------------------------------------------*/
  
  /**
   * 定义main函数
   */
  def main(args: Array[String]): Unit = {
    /*------------------------变量和常量赋值-----------------------------*/
    /*
     * 1.在scala中给变量赋值的时候一般不需要指定类型,这一点跟js有些类似，（如果需要也可以指定类型） 
     * 2.在scala中，推荐使用val即声明常量，除非你真的需要改变它的值
     * 3.在scala中仅当同一行代码中存在多条语句时才需要分号隔开
     */
    
    //定义变量
    var r = 1
    //变量可以被重新赋值
    r = 0    
    
    //定义常量
    val c = 2
    //常量不允许重新进行赋值
//    c = 3
    
    //定义指定类型的变量
    var rType: String = "1"
    
    //同时创建多个变量并赋值
    val a,b = 1
    
    /*---------------------变量和常量都需要赋初始值-------------------------*/
    //如下语法都是错误的
//    var myvar //老版本：此处打开注释后编译不显示错误（实际上是编译器认为你还没有写完，所以没有出错误提示），
              //但是运行时会告知我们这里有错误
              //新版本：编译时直接报错
//    val myval
    

    
    /*------------------------变量类型-----------------------------*/
    /*
     * 1.跟java相同，也有7种数值类型和一个Boolean类型 byte short int long float double char + boolean
     * 2.跟java不同的是，这些所有的类型都是类。scala并不刻意区分基本类型和引用类型
     * 3.scala中不刻意区分基本类型和引用类型（基本类型的封装由scala内部来做）,
     * 因为scala中做了封装，所以如下示例语法（看起来是数值类型调用方法）也是正确的
     */
    val inta: Int= 1
    inta.toString()

    //实际上int类型的1首先被scala内部转换成了RichInt,所以1才可以具有调用方法的能力。
    //与其类似的是：
    //Double被转换成了RichDouble；Char被转换成了RichChar等
    //String 被转换成了StringOps类，scala在java中的String类的基础上又给其添加了许多方法，
    //所以scala中的String比java中的String上的方法更多，例如intersect函数：
    print("hello".intersect("world")) //输出两个值中的相同的字符lo
    println()
    
    //scala中的Range（ [reɪn(d)ʒ]英文意思是：范围， 类似于java中的一个连续的数组） 与Range同意的词是Hbase中的region
    var myRange:Range  = 1.to(10)
    println(myRange)

    /*-----------------------思考---------------------------*/
    //99.44.toInt的结果
    print(99.44.toInt+"\n")
    //99.toChar的结果
    println(99.toChar)
    
    //scala的7种数值类型和boolean类型
    val byteType: Byte = 1
    val charType: Char = 'a'
    val shortType: Short = 1 
    val intType: Int = 1
    val longType: Long = 1l
    val floatType: Float = 1.1f//这里注意一下，不写f默认1.1为Double类型
    val doubleType: Double = 1.1
    val booleanType: Boolean = true

  }
}