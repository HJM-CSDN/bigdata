package org.koudai.java.IO流和NIO;


import java.io.FileWriter;
import java.io.IOException;

/**
 * @description javaIO流
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/2/23 20:00
 * Description:
 *    流（IO）流：input-outStream,实现两个设备之间的数据传输
 *    分类：
 *         根据操作方式：输入流和输出流
 *         根据输入的类型：字节流和字符流
 *    以内存为参考：
 *         字节流：1.字节输入流：InputStream   2.字节输出流：OutputStream
 *         字符流的两个父类：1.字符读入流（将数据读入内存）：Reader
 *                          2.字符写出流（将数据从内存取出）：Writer
 *    操作文件，磁盘存储更适合用字符流。
 */
public class IO {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("temp1.txt");
        //当第二个参数为true，则不会覆盖原文件，而是续写
        FileWriter fileWriter2 = new FileWriter("temp2.txt",true);

        fileWriter.write("我是从JavaIO流写出的文件1");

        fileWriter2.write("我是从JavaIO流写出的文件2，可追加\n");


        fileWriter.flush();
        fileWriter2.flush();
        fileWriter.close();
        fileWriter2.close();

        //fileWriter.write("close后不可在写入");
    }
}
