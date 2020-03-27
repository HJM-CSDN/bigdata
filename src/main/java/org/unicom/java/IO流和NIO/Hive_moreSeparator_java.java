package org.unicom.java.IO流和NIO;

import java.io.*;


/**
 * @author 闫羽航
 * @title: Hive_moreSeparator_java
 * @projectName Project
 * @description: 替换文本分隔符
 * @date 2020/2/28 12:26
 */
public class Hive_moreSeparator_java {

    public static void main(String[] args) throws IOException {

        String inputPath = args[0];
        String outputPath = args[1];

        File inputfile = new File(inputPath);
        File outputfile = new File(outputPath);
        BufferedReader br = new BufferedReader(new FileReader(inputfile));
        FileOutputStream fileOutputStream = new FileOutputStream(outputfile);
        String line = null;
        while((line = br.readLine()) != null) {
            String writeStr = line.replace("@@@","|")+"\r\n";
            fileOutputStream.write(writeStr.getBytes());

        }

        fileOutputStream.close();
        br.close();

    }
}
