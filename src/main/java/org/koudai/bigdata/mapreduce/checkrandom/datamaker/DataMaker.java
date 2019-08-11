package org.koudai.bigdata.mapreduce.checkrandom.datamaker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @description 数据制造器
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2018/9/25 11:43
 */
public class DataMaker {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        //在linux下执行
        //  File file  = new File("/data/randomdata");
        //在windows本地
          File file  = new File("d:/data");

        //防止文件不存在
        if (!file.exists())
        {
            try {
                file.createNewFile();
                System.out.println(file.getAbsoluteFile()+"创建成功");
            }catch (IOException e){
                System.err.print("文件创建失败,具体原因如下:");
                e.printStackTrace();
            }
        }else {
            System.out.println("给定的文件: "+file.getAbsolutePath()+"已存在");
        }

        //构造文件输出流对象
        FileOutputStream outputStream = new FileOutputStream(file);
        //使用StringBuffer高效拼接字符串
        StringBuffer sb = new StringBuffer();
        //持续往文件中输入内容
        for (long i = 1L; i <= 10000000; i++) {//写入的数字总行数
            //控制单行输入
            for (int j = 0; j < 10; j++) {//每行写入的数字的数量为10个
                if(j != 9){//每一行的非最后一个数字后面都拼上逗号
                    sb.append(random.nextInt(100)+1+",");
                }else{//每一行的最后一个数字后面都不拼逗号
                    sb.append(random.nextInt(100)+1+"");
                }
            }
            //在每一行的行尾拼接上换行符
            sb.append(System.lineSeparator());
            //每构造一万行文本,往java outputstream对象中写入一次
            if (i%10000==0){
                outputStream.write(sb.toString().getBytes());
                outputStream.flush();
                System.out.println("已经写入了"+i+"行");
                sb.setLength(0);
            }
        }
        //最后flush一次,保证所有内容都写入到文件中
        outputStream.flush();
        //关闭输出流
        outputStream.close();
        System.out.println("整体写入程序完成!");
    }
}
