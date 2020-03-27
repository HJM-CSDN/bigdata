package org.unicom.scala.chapter9.fileandregex

import java.net.URL
import java.io.File
import java.io.FileInputStream
import java.io.PrintWriter

/**
 * scala文件操作
 */
object file {
  /**
   * 定义main函数
   */
  def main(args: Array[String]): Unit = {
    /*--------------------读取文件中所有的行--------------------*/
    import scala.io.Source//导入io包中的Object Source

    //读取桌面上的1.txt文件   这里的参数：“文件地址” 也可以替换成java.io.file对象
//    val source = Source.fromFile("C:/Users/mmy/Desktop/1.txt","UTF-8")

//    val mkStringResult = source.mkString;
//    println(mkStringResult)

//    //获取source上的行文本迭代器（在使用迭代器之前不能进行mkString操作，此操作会将迭代器中的内容清空）
//    val lineIterator:Iterator[String] = source.getLines
//    //循环迭代器取出所有行
//    for(i <- lineIterator) println(i)
//    println("-------------------------------------------------------------------------")
//
//    //直接获取source上的所有文本（迭代器执行完毕之后source中的内容会被清空，所以mkString结果为空，此时输出的内容为空）
//    val sourceStr = source.mkString
//    println(sourceStr)//这个输出没有值，只能输出空行
//
//    //关闭source
//    source.close()

    /*--------------------读取文件中所有的字符--------------------*/
    /*
     * 直接遍历Source对象对象即可
     * 示例如下:
     */
    //读取桌面上的1.txt文件   这里的参数：“文件地址” 也可以替换成java.io.file对象
//    val source1 = Source.fromFile("C:/Users/mmy/Desktop/1.txt","UTF-8")

//    for(i <- source1) println(i)//按照换行形式打印每个字符
//    for(i <- source1) print(i)//按照非换行形式打印每个字符


    /*--------------------只查看而不处理字符--------------------*/
    /*
     * 只查看不处理字符可以使用head方法
     * 示例如下：
     */
//    val source2 = Source.fromFile("C:/Users/mmy/Desktop/1.txt","UTF-8")
//
//    //调用buffered方法为调用head方法做准备
//    val iter:BufferedIterator[Char] = source2.buffered
//    while(iter.hasNext){
//      val strHead = iter.head.toString //head方法的返回值类型是char，需要toString
//      if(strHead == "~"){//如果碰到的字符是波浪线就不输出了
//        iter.drop(1)
//      }else{
//        print(iter.next())
//      }
//    }
//    println//输出一个空行
//    println(iter)//循环遍历完成之后迭代器内容已被清空，表名next()方法的本质是返回结果并删除

    /*--------------------读取词法单元和数字--------------------*/
    /*
     * demo文件中存放的字符都是浮点数据
     */
//    //将demo文件中的内容以空格分隔组成数组
//    val arr = Source.fromFile("C:/Users/mmy/Desktop/demo").mkString.split(" ")
//    //将字符串数组转换成数字数组
//    val arrNums = for(i <- arr) yield i.toDouble
//    arrNums.foreach(println)
//    println()
//    //如下代码效果与上行代码效果相同
//    val arrNums1 = arr.map (_.toDouble)
//    arrNums.foreach(print)
//    println()

    /*--------------------读取二进制文件--------------------*/
    /**
     * scala中没有直接读取二进制文件的方法，还需要使用java的类库
     * 以下是代码实现（没有二进制文件，只是假代码）：
     */
//    val file = new File("filepath")
//    val in = new FileInputStream(file)
//    val bytes = new Array[Byte](file.length().toInt)
//    in.read(bytes)
//    in.close()

    /*--------------------写入文本文件--------------------*/
    /**
     * 同样的，使用scala写入文本文件需要使用java.io.PrintWriter
     * 示例如下：
     */
//    val printer = new PrintWriter("C:/Users/mmy/Desktop/3.txt")
//    println("开始写入流程~~")
//    val start = System.currentTimeMillis()//开始写入时间
//    for(i <- 1 to 20000000) if(i%100 == 0) printer.print(i+System.lineSeparator()) else printer.print(i+" ")
//    val end = System.currentTimeMillis()
//    val times = end-start
//    println("文件内容写入成功,总耗时： "+ times +"毫秒")
//    printer.close()

    /*--------------------访问目录--------------------*/
    /**
     * scala并没有正式的访问目录的类，我们可以使用java来实现
     */
    //遍历某目录下的所有子目录
    import java.io.File
    def subdirs(dir:File): Iterator[File] = {
      val children = dir.listFiles.filter (_.isDirectory())
      children.toIterator ++ children.toIterator.flatMap(subdirs)
    }

    //利用上述函数可以像如下代码访问所有的子目录
//    for (d <- subdirs(dir)) 处理 d
//
    /*--------------------序列化--------------------*/
    /**
     * 序列化的作用是将对象或者其他数据结构转换成二进制流，便于传输，后续再使用反序列化将其还原。
     * 因为二进制流是最便于网络传输的数据格式。
     *
     * scala中序列化的语法如下：
     * 注意：@SerialVersionUID不是强制的
     */
    @SerialVersionUID(1L) class Persion extends Serializable

    /*--------------------进程控制--------------------*/
    /**
     * scala的实际目标之一就是能在简单的脚本化任务和大型程序之间保持良好的伸缩性。
     * 所以scala.sys.process包提供了用于与shell程序交互的工具。
     * 示例如下：
     */
    //执行ls -al 命令
//    import sys.process._
//    "ls al" ! //！操作符用来执行命令，执行成功返回0，否则就显示错误的非0值
//    val result = "ls al .." !! //使用!!操作符将命令的返回结果以字符串的形式返回
  }
}