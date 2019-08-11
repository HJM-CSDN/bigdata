package org.koudai.bigdata.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.net.URI;
import java.io.IOException;

/**
 * Api的作用:比如数据采集,数据分析.
 * 海量日志存取到HDFS需要进行写流程,分析海量数据需要进行读流程,结果保存到HDFS又要进行写流程
 */

//为了方便操作,先在HDFS上将/目录及其所有多级子项的权限修改为777
//即hadoop fs -chmod -R 777 /
public class HadoopFSOperations {
    static String nameNodeURI = "";
    static Configuration hdfsConf = null;
    static FileSystem hdfs = null;
    static FileSystem local = null;
    //普通代码块,优先于构造函数执行,一般用来初始化应用程序:全局变量
    static {
        nameNodeURI = "hdfs://min1:8020";//哪一个active用哪一个  相当于web界面的50070
        hdfsConf = new Configuration();
        try{
            //HDFS文件系统
            hdfs = FileSystem.get(new URI(nameNodeURI),hdfsConf);
            //本地文件系统
            local = FileSystem.getLocal(new Configuration());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //应用程序入口
    public static void main(String[] args) throws Exception{
        //在特定文件系统上创建目录
//        makedir(hdfs,"/a/b/c/d");
 //       System.out.println("在HDFS上创建多级目录成功");
/*        makedir(local,"d:/testdir");
        System.out.println("在本地上创建文件夹成功");*/

        //在特定文件系统上列出目录详情
         System.out.println("HDFS上的根目录/mr详情如下:");
         listDir(hdfs,"/mr");
//        System.out.println("HDFS上的根目录详情如下:");
//         listDir(local,"c:/");
//
//        //在特定文件系统上删除目录
//        rmr(hdfs,"/a/b");
//        System.out.println("在HDFS上删除多级目录成功");
//        rmr(local,"d:/testdir");
//        System.out.println("在windows上删除目录成功:");
//
//        //往HDFS上上传指定内容的文件
//        createNewHDFSFile("/c12/javaio","好难理解的javaIO");
//        System.out.println("往HDFS上上传指定内容的文件成功！");
//
//     //从本地文件系统拷贝文件到HDFS上
//        uploadLocalFile2HDFS("D:\\IDEAProjects\\bigdata\\src\\main\\java\\org\\qianfeng\\bigdata\\hadoop\\hdfs\\readme","/mr/checkrandom2/input");
//        System.out.println("上传本地文件到HDFS成功！");
//
//
//        从HDFS上的文件上读取内容
         System.out.println("HDFS上的文件中的内容如下：");
    //     readHDFSFile("/mr/checkrandom2/input/readme");
         readHDFSFile("/mr/multiple/output1/part-r-00000");
         readHDFSFile("/mr/wc/input/hjm.txt");


//        //删除HDFS上的文件
//        rmr(hdfs,"/c12/javaio");
//        System.out.println("删除HDFS上的文件成功！");
//        //关闭与HDFS之间的连接
//        rename(hdfs,"/a","/aa");
//        System.out.println("重命名HDFS上的文件夹成功!");
        hdfs.close();
    }

    /**
     * 在指定的文件系统中创建目录
     * @param fs  指定的文件系统
     * @param dir 需要创建的目录
     * @throws IOException
     */
    public static void makedir(FileSystem fs,String dir) throws IOException {
        fs.mkdirs(new Path(dir));
    }

    /**
     * 列出指定文件系统中给定的目录的详情
     * @param fs    指定的文件系统
     * @param dir   需要列出详情的目录
     * @throws IOException
     */
    public static void listDir(FileSystem fs,String dir) throws IOException {
        FileStatus[] statuses = fs.listStatus(new Path(dir));
        for (int i = 0; i <statuses.length; i++) {
            System.out.println(statuses[i].getPath().toString());
        }
    }

    /**
     *删除指定文件系统中的给定的目录
     * @param fs    指定的文件系统
     * @param dir   需要删除的目录
     * @throws IOException
     */
    public static  void  rmr(FileSystem fs,String dir) throws IOException {
        fs.delete(new Path(dir),true);
    }

    /**
     * 重命名指定文件系统中给定的目录或文件
     * @param fs            指定的文件系统
     * @param oldAbsPath    旧的文件名称
     * @param newAbsPath    新的文件名称
     * @throws IOException
     */
    public  static void  rename(FileSystem fs,String oldAbsPath,String newAbsPath) throws IOException {
        fs.rename(new Path(oldAbsPath),new Path(newAbsPath));
    }

    /**
     * 通过javaAPI在HDFS上新建文件并直接给定文件的内容
     * @param filePath          文件路径
     * @param filrContent       文件内容
     * @throws IOException
     */
    public static void createNewHDFSFile(String filePath,String filrContent) throws IOException {
        FSDataOutputStream out = hdfs.create(new Path(filePath));
        out.write(filrContent.getBytes());
        out.close();
    }
    /**
     * 上传本地文件到HDFS
     * 注意：如果在Windows操作系统上传文件，本地文件系统指的是Windows的文件系统
     * @param LocalPath     本地文件路径
     * @param HDFSPath      HDFS文件路径
     * @throws IOException
     */
    public static void uploadLocalFile2HDFS(String LocalPath,String HDFSPath)throws IOException{
        hdfs.copyFromLocalFile(new Path(LocalPath),new Path(HDFSPath));
    }

    public static void readHDFSFile(String filePath) throws Exception {
        Path path = new Path(filePath);
        FSDataInputStream in = hdfs.open(path);
        FileStatus status = hdfs.getFileStatus(path);
        byte[] buffer = new byte[Integer.parseInt(String.valueOf(status.getLen()))];
        in.readFully(0,buffer);
        System.out.println("~~~~~~~~~~~~读取到的文件的内容如下：~~~~~~~~~~~~");
        System.out.println(new String(buffer));//将读取字节数组转换成字符串
        in.close();
        in.close();
    }

}
