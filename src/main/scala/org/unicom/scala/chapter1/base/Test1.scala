package org.unicom.scala.chapter1.base

/**
  * scala第一章的练习
  */
object Test1 {
  def main(args: Array[String]): Unit = {
    var a = "abcdef"*5
    println(a)
    println(a.take(3))
    println(a.drop(3))
    println(a.takeRight(3))
    println(a.dropRight(3))
   // 查询API即可 take是从字符串首开始获取字符串,drop是从字符串首开始去除字符串。
    // takeRight和dropRight是从字符串尾开始操作。 这四个方法都是单方向的。
    // 如果我想要字符串中间的子字符串，那么需要同时调用drop和dropRight，或者使用substring 
  }
  //1.在Scala REPL中键入3,然后按Tab键。有哪些方法可以被应用?


  //2.在Scala REPL中，计算3的平方根,然后再对该值求平方。现在，这个结果与3相差多少？(提示:res变量是你的朋友)


  //3.如何检测Scala中的的res变量是val还是var?


  //4.Scala允许你用数字去乘字符串—去REPL中试一下"crazy"*3。这个操作做什么？在Scaladoc中如何找到这个操作?


  //5.10 max 2的含义是什么？max方法定义在哪个类中？


  //6.用BigInt计算2的1024次方


  //7.为了在使用probablePrime(100,Random)获取随机素数时不在probablePrime和Radom之前使用任何限定符，你需要引入什么？


  //8.创建随机文件的方式之一是生成一个随机的BigInt，然后将它转换成三十六进制，输出类似"qsnvbevtomcj38o06kul"这样的字符串。查阅Scaladoc，找到在Scala中实现该逻辑的办法。


  //9.在Scala中如何获取字符串的首字符和尾字符？

  //10.take,drop,takeRight和dropRight这些字符串函数是做什么用的？和substring相比，他们的优点和缺点都是哪些？

}
