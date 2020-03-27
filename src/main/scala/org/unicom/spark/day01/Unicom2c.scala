package org.unicom.spark.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object Unicom2c {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf()
    conf.setAppName("SparkWC")
      conf.setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)

    //获取HDFS数据
    val lines: RDD[String] = sc.textFile("D:\\测试数据\\非2i")


    //将获取的数据进行切分,生成一个个单词
    //val words = lines.map(_.split(","))

    val tup = lines.filter(_.contains("370522")).filter(_.contains("1996"))
    //println(sorted.collect.toBuffer)
    tup.foreach(println)

    //释放资源
    sc.stop()
  }
}
