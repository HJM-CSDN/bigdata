package org.koudai.spark.day01

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object MergeFile {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf()
    conf.setAppName("SparkWC")
    conf.setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)

    //正则匹配获取多个文件夹下数据
    val lines: RDD[String] = sc.textFile("D:\\测试数据\\东营\\part-00[0-9][0-9][0-9]")


    lines.coalesce(1).saveAsTextFile("D:\\测试数据\\东营\\数据")

    sc.stop()
  }
}
