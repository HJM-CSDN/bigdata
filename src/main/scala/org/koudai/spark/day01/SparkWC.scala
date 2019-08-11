package org.koudai.spark.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object SparkWC {
  def main(args: Array[String]): Unit = {
    //模板代码
    //创建文件配置对象
    val conf: SparkConf = new SparkConf()
    //指定应用程序名称
    conf.setAppName("SparkWC")
    //指定本地测试模式(local模式)中用几个线程来模拟集群
    //local[2]:调用两个线程模拟集群运行
    //local:调用1个线程来模拟集群运行
    //local[*]:调用系统所有的空闲线程来模拟集群运行
      conf.setMaster("local[2]")
    //创建上下文对象(也叫提交集群的入口类)
    val sc: SparkContext = new SparkContext(conf)

    //获取HDFS数据
    val lines: RDD[String] = sc.textFile("hdfs://min1:8020/mr/wc/input")

    //将获取的数据进行切分,生成一个个单词
    val words: RDD[String] = lines.flatMap(_.split(" "))

     //将单词后面跟一个1,生成一个个对偶元组
    val tup: RDD[(String,Int)] = words.map((_,1))

    //进行聚合操作
   val sumed: RDD[(String, Int)] = tup.reduceByKey(_+_)
    //val sumed2: RDD[(String,Int)] = tup.reduceByKey((x,y)=>x+y)

    //降序排序
    val sorted: RDD[(String,Int)] = sumed.sortBy(x=>x._2,false)

    println(sorted.collect.toBuffer)
    //sorted.foreach(println)

    //释放资源
    sc.stop()
  }
}
