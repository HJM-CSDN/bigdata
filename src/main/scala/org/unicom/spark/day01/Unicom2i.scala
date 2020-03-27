package org.unicom.spark.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object Unicom2i {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf()
    conf.setAppName("SparkWC")
      conf.setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)

    //获取HDFS数据
   // val lines: RDD[String] = sc.textFile("hdfs://s01:8020/data/gp/user_all")
   // val lines: RDD[String] = sc.textFile("hdfs://c01:8020/data/gp/user_all")
    val lines: RDD[String] = sc.textFile("D:\\测试数据\\user_20190709.txt")


    //将获取的数据进行切分,生成一个个单词
    val words = lines.map(_.split(","))

     //将单词后面跟一个1,生成一个个对偶元组
    val tup = words.map(x=>{
      val serial_number = x(0)            //服务号码
      val user_id = x(1)                  //用户编码
      val province_code = x(2)            //省份编码
      val province_name = x(3)            //省份名称
      val eparchy_code = x(4)             //地州编码
      val eparchy_name = x(5)             //地州名称
      val product_id = x(6)               //产品编码
      val product_name = x(7)             //产品名称
      val product_start_time = x(8)       //产品开始时间
      val product_id_next_month = x(9)    //下月产品编码
      val product_name_next_month = x(10)  //下月产品名称
      val state_code = x(11)               //状态编码
      val service_change_time = x(12)      //服务变更时间
      val credit_fee = x(13)               //信用额度(单位：分)
      val credit_fee_time = x(14)          //信用额度更新时间
      val leave_real_fee = x(15)           //实时余额(单位：分)
      val leave_fee_time = x(16)           //实时余额更新时间
      val open_account_time = x(17)        //开户时间
      val city_code = x(18)                //城市编码
      val cust_name = x(19)                //用户名称
      val pspt_type_code = x(20)           //证件类型
      val pspt_id = x(21)                  //证件编码
      val contact_phone = x(22)            //联系电话
      val old_tag = x(23)                  //老用户标识
      val pay_first_tag = x(24)            //首充标识
      val pay_first_fee = x(25)            //首充费用(单位：分)
      val pay_first_time = x(26)           //首充时间
      val update_time = x(27)              //更新时间
      val customer_id = x(28)              //用户编码
      val channel = x(29)                  //发展渠道编码
      val is_online = x(30)                //线上发展标识：1为线上渠道,0为线下渠道
      val rn = x(31)                       //剃重使用序号，无意义

      ("用户: "+cust_name,"手机号: "+serial_number,"省份: "+province_name,"地市: "+eparchy_name,"id:"+pspt_id)
    })

    val tup2 = tup.filter(_._1.contains("韩")).filter(_._3.contains("山东")).filter(_._4.contains("东营")).filter(_._5.contains("1996"))

    //println(sorted.collect.toBuffer)
     tup2.foreach(println)
    //tup2.saveAsTextFile("D:\\测试数据\\福建\\厦门.txt")

    //释放资源
    sc.stop()
  }
}
