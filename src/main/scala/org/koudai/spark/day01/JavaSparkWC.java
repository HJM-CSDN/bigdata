package org.koudai.spark.day01;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @description
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/10/31 20:38
 */
public class JavaSparkWC {
    public static void main(String[] args) {
        //模板代码
        SparkConf conf = new SparkConf()
                .setAppName("JavaSparkWC")
                .setMaster("local");
        JavaSparkContext jsc = new JavaSparkContext(conf);

        //获取数据
        JavaRDD<String> lines = jsc.textFile("hdfs://min2:8020/mr/wc/input/hjm.txt");

        //切分
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String line) throws Exception {
                return Arrays.asList(line.split(" ")).iterator();
            }
        });

        //生成元组
        JavaPairRDD<String,Integer> tup = words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String word) throws Exception {
                return new Tuple2<String, Integer>(word,1);
            }
        });

        // 聚合
        JavaPairRDD<String, Integer> sumed = tup.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        // 把元组里的元素进行交换
        JavaPairRDD<Integer, String> swaped = sumed.mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {
            @Override
            public Tuple2<Integer, String> call(Tuple2<String, Integer> tup) throws Exception {
//                return new Tuple2<Integer, String>(tup._2, tup._1);
                return tup.swap();
            }
        });

        // 降序排序
        JavaPairRDD<Integer, String> sorted = swaped.sortByKey(false);

        // 再次交换
        JavaPairRDD<String, Integer> res = sorted.mapToPair(new PairFunction<Tuple2<Integer, String>, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(Tuple2<Integer, String> tup) throws Exception {
                return tup.swap();
            }
        });

        System.out.println(res.collect());

        jsc.stop();
    }
}
