package org.unicom.bigdata.mapreduce.join.mapsidejoin.mapper;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * @description map端Jion程序的Mapper
 * @author: Mmy 2018-6-8 08:41:57
 * @create: 2018-06-11 09:04:40
 **/
public class JoinAtMapSideMapper extends Mapper<LongWritable,Text,Text,Text> {
    //封装所有部门信息的Map
    HashMap<Integer,String> allDepartment = new HashMap<>();
    /**
     * 从HDFS上获取字典文件，并将字典文件的内容转换成一个HashMap
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //字典文件的HDFS路径
        String dictionariesFilePath = "hdfs://tcbdcluster/mr/join/cache/dept.txt";
        Configuration hdfsConf = new Configuration();
        FileSystem tcbdclusterHDFS = null;
        try {
            tcbdclusterHDFS = FileSystem.get(new URI(dictionariesFilePath), hdfsConf);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        FSDataInputStream fin = tcbdclusterHDFS.open(new Path(dictionariesFilePath));
        BufferedReader br = new BufferedReader(new InputStreamReader(fin));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] lineSplit = line.split(",");
            allDepartment.put(new Integer(lineSplit[0]),lineSplit[1]+","+lineSplit[2]);
        }
        fin.close();
    }

    /**
     * map方法，每执行一次，处理一行数据
     * @param lineNumber 当前处理的行的行号
     * @param lineContext 当前处理的行的内容
     * @param context mr程序的上下文对象
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable lineNumber, Text lineContext, Context context) throws IOException, InterruptedException {
        String[] lineContextSplit = lineContext.toString().split(",");
        String departmentInfo = allDepartment.get(new Integer(lineContextSplit[2]));
        String currntEmpsAllInfo = lineContext.toString() + "," + departmentInfo;
        context.write(new Text(),new Text(currntEmpsAllInfo));
    }
}
