package org.unicom.scala

import scala.util.Random

/**
  * description: 随机回答
  * author: HJM
  * create: 2016-10-30 15:38
  **/
object RandomAnswer {

  def main(args: Array[String]): Unit = {
    //1.12
    //2.11
    //3.14
    //4.15
    //5.10
    //6.12
    val groupID = Random.nextInt(6)+1;
    println("回答问题的组的编号是：" + groupID)
    groupID match {
      case 1 => println("回答问题的组员的编号是：" + (Random.nextInt(12)+1))
      case 2 => println("回答问题的组员的编号是：" + (Random.nextInt(11)+1))
      case 3 => println("回答问题的组员的编号是：" + (Random.nextInt(14)+1))
      case 4 => println("回答问题的组员的编号是：" + (Random.nextInt(15)+1))
      case 5 => println("回答问题的组员的编号是：" + (Random.nextInt(10)+1))
      case 6 => println("回答问题的组员的编号是：" + (Random.nextInt(12)+1))
    }
  }
}
