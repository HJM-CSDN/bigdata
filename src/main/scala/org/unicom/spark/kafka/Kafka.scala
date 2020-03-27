package org.unicom.spark.kafka

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer



object Kafka {

  private lazy val application: Config = ConfigFactory.load()

  /**
    * topics  订阅一项中，对于topic的定义必须是可迭代的或者是集合，需要将数组转化为list  array和seq都不行
    */
  val topics = application.getString("unicom.kafka.topics")

  val topic_list: ListBuffer[String] = mutable.ListBuffer[String]()

  topic_list +=topics

  val p1: TopicPartition = new TopicPartition(topics, 1)
  val p2: TopicPartition = new TopicPartition(topics, 2)
  val p3: TopicPartition = new TopicPartition(topics, 3)
  val p4: TopicPartition = new TopicPartition(topics, 4)
  val p5: TopicPartition = new TopicPartition(topics, 5)
  val p6: TopicPartition = new TopicPartition(topics, 6)
  val p7: TopicPartition = new TopicPartition(topics, 7)
  val p8: TopicPartition = new TopicPartition(topics, 8)
  val p9: TopicPartition = new TopicPartition(topics, 9)
  val p10: TopicPartition = new TopicPartition(topics, 10)
  val p11: TopicPartition = new TopicPartition(topics, 11)

  val list: ListBuffer[TopicPartition] = mutable.ListBuffer[TopicPartition]()
//  list.+=(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11)
  list.+=(p1)
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[2]")
      .setAppName("test")


    val ssc = new StreamingContext(conf,Seconds(2))
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> application.getString("unicom.kafka.brokers"),  //kafka broker
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> application.getString("unicom.kafka.groupId"),
      "auto.offset.reset" -> "earliest"
      //"auto.offset.reset" -> "latest"
    )
    val dstream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream(ssc,
      LocationStrategies.PreferConsistent,
     // ConsumerStrategies.Subscribe(topics.map(_.toString).toSet, kafkaParams)) //不行，java scala类型不匹配
      ConsumerStrategies.Subscribe(topic_list, kafkaParams))
     // ConsumerStrategies.Assign(list, kafkaParams)) //针对多个topic的情况

    dstream.foreachRDD(rdd=>{
      rdd.foreach(println)
    })

    ssc.start()
    ssc.awaitTermination()
  }


}
