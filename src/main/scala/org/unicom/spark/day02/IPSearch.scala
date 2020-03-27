package org.unicom.spark.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object IPSearch {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("ipsearch").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val ipInfo: RDD[(String, String, String)] = sc.textFile("F://~").map(line => {
      val fields = line.split("\\|")
      val startIP = fields(2)
      val endIP = fields(3)
      val province = fields(6)

      (startIP, endIP, province)
    })
    val inInfoArr: Array[(String, String, String)] = ipInfo.collect()
    //TODO


  }
}
