package org.koudai.scala.chapter3.array

/**
 * 多维数组、scala和java互操作
 */
object Arraysarray {
  //定义main函数
  def main(args: Array[String]): Unit = {
    /*---------------------多维数组----------------------------*/
    /*
     * 与java类似，多维数组本质上就是数组中嵌套数组
     */
    val a1 = new Array[Array[Int]](3)

    for(i <- 0 until a1.length){
        a1(i) = new Array[Int](4)
        for (j <- 0 until a1(i).length){
          a1(i)(j) = j
        }
    }
    for (i <- a1) {
      for (elem <- i)
         print(elem)
      println()
    }

    println
    //定义一个多维数组(输出拼成三角形的形状) 即：不规则多维数组
    val arrs = new Array[Array[Int]](5)
    println(arrs.length)
    
    //给多维数组赋值
    for(i <- 0 until arrs.length){
    	arrs(i) = new Array[Int](i+1)      
 			for(j<- 0 until arrs(i).length){
 			  arrs(i)(j) = j
 			}
    }
    
    //输出多维数组中的值
    for(i <- arrs){
      for(j<-i){
        print(j)
      }
      println
    }
    
    /*--------------scala与java的互操作--------------*/
    /* 
     * scala数组是通过java数组实现的。
     * 通过引入如下包，可实现scala与java的互操作，这种情况下，
     * 调用java方法时，这些对象会被自动包装成java列表
     */
    import scala.collection.JavaConversions.bufferAsJavaList
    import scala.collection.mutable.ArrayBuffer
    
    val command = ArrayBuffer("ls","-al","/home")
    val pb = new ProcessBuilder(command)//scala到java的转换
    println(pb.toString)

    import scala.collection.JavaConversions.asScalaBuffer
    import scala.collection.mutable.Buffer
    val cmd:Buffer[String] = pb.command()  //java到scala的转换
    println(cmd)
  }
}