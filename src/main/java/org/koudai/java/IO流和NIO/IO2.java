package org.koudai.java.IO流和NIO;

import java.io.FileWriter;
import java.io.IOException;

import static jodd.util.ThreadUtil.sleep;

/**
 * @description 通过IO 写flume配置
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/7/21 23:45
 */
public class IO2 {
    public static void main(String[] args) throws IOException {
    //    FileWriter fileWriter = new FileWriter("D://flume.txt",true);
        FileWriter fileWriter = new FileWriter("C:\\Users\\hjm13\\Desktop\\p19---flume\\flume.txt");

//        for (int i = 1; i <= 384; i++) {
//              fileWriter.write("k"+i+" ");
//        }
//        fileWriter.write("\n");
//
//        for (int i = 1; i <= 192; i++) {
//            System.out.println("sink "+i+"写入成功");
//            fileWriter.write("#define sink "+i+"\n");
//            fileWriter.write("f1.sinks.k"+i+".type = avro"+"\n");
//            fileWriter.write("f1.sinks.k"+i+".hostname = 10.169.8.24"+"\n");
//            fileWriter.write("f1.sinks.k"+i+".port =44444"+"\n\n");
//            fileWriter.write("f1.sinks.k"+i+".channel = c1"+"\n");
//            fileWriter.flush();
//        }
//
        for (int i = 193; i <= 256; i++) {
            System.out.println("sink "+i+"写入成功");
            fileWriter.write("#define sink "+i+"\n");
            fileWriter.write("f1.sinks.k"+i+".type = avro"+"\n");
            fileWriter.write("f1.sinks.k"+i+".hostname = 10.169.8.25"+"\n");
            fileWriter.write("f1.sinks.k"+i+".port =44447"+"\n\n");
            fileWriter.flush();
        }
        for (int i = 257; i <= 320; i++) {
            System.out.println("sink "+i+"写入成功");
            fileWriter.write("#define sink "+i+"\n");
            fileWriter.write("f1.sinks.k"+i+".type = avro"+"\n");
            fileWriter.write("f1.sinks.k"+i+".hostname = 10.169.8.25"+"\n");
            fileWriter.write("f1.sinks.k"+i+".port =44448"+"\n\n");
            fileWriter.flush();
        }
        for (int i = 321; i <= 384; i++) {
            System.out.println("sink "+i+"写入成功");
            fileWriter.write("#define sink "+i+"\n");
            fileWriter.write("f1.sinks.k"+i+".type = avro"+"\n");
            fileWriter.write("f1.sinks.k"+i+".hostname = 10.169.8.25"+"\n");
            fileWriter.write("f1.sinks.k"+i+".port =44449"+"\n\n");
            fileWriter.flush();
        }
//        for (int i = 385; i <= 576; i++) {
//            System.out.println("sink "+i+"写入成功");
//            fileWriter.write("#define sink "+i+"\n");
//            fileWriter.write("f1.sinks.k"+i+".type = avro"+"\n");
//            fileWriter.write("f1.sinks.k"+i+".hostname = 10.169.8.24"+"\n");
//            fileWriter.write("f1.sinks.k"+i+".port =44446"+"\n\n");
//            fileWriter.flush();
//        }
//
//        for (int i = 1; i <= 192; i++) {
//            fileWriter.write("f1.sinks.k"+i+".channel = c1"+"\n");
//            fileWriter.flush();
//        }
//        for (int i = 193; i <= 384; i++) {
//            fileWriter.write("f1.sinks.k"+i+".channel = c2"+"\n");
//            fileWriter.flush();
//        }

//        for (int i = 193; i <= 256; i++) {
//            fileWriter.write("f1.sinks.k"+i+".channel = c4"+"\n");
//            fileWriter.flush();
//        }
//
//        for (int i = 257; i <= 320; i++) {
//            fileWriter.write("f1.sinks.k"+i+".channel = c5"+"\n");
//            fileWriter.flush();
//        }
//
//        for (int i = 321; i <= 384; i++) {
//            fileWriter.write("f1.sinks.k"+i+".channel = c6"+"\n");
//            fileWriter.flush();
//        }
//        for (int i = 385; i <= 576; i++) {
//            fileWriter.write("f1.sinks.k"+i+".channel = c3"+"\n");
//            fileWriter.flush();
//        }

/*        for (int i = 129; i <= 192; i++) {
            System.out.println("sink "+i+"写入成功");
            fileWriter.write("#define sink "+i+"\n");
            fileWriter.write("f1.sinks.k"+i+".type = avro"+"\n");
            fileWriter.write("f1.sinks.k"+i+".hostname = 10.169.8.24"+"\n");
            fileWriter.write("f1.sinks.k"+i+".port =44446"+"\n\n");
            fileWriter.flush();
        }*/
        fileWriter.close();
    }
}
