package org.unicom.java.IO流和NIO.吐槽数据;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;

/**
 * @description 数据标注
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2020/3/23 15:30
 */
public class TuCaoIO {
    public static void main(String[] args) throws IOException, AWTException {


        FileReader input = new FileReader("src\\main\\java\\org\\koudai\\java\\IO流和NIO\\吐槽数据\\5190_韩金铭.csv");


        LineNumberReader numberReader = new LineNumberReader(input);

        FileWriter output = new FileWriter("src\\main\\java\\org\\koudai\\java\\IO流和NIO\\吐槽数据\\output.csv",true);

        String s = "";

        for (int i = 1; i <= 5190; i++) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("欢迎使用吐槽标注程序!");
            System.out.println("0.剔除描述不详细的、无意义的吐槽");
            System.out.println("1.提取网速、基站、客服方面的吐槽点");
            System.out.println("2.筛选用户体验方面的吐槽点（页面相关：找不到在哪儿，页面乱）");
            System.out.println("3.识别出疑问类的吐槽点");
            System.out.println("4.识别出功能、故障类的吐槽数据");
            System.out.println("多标签的情况（两个或三个）");
            System.out.println("-------------------------------------------------------------------");

            s = numberReader.readLine();
            System.out.println("第"+i+"行数据：\n");
          //  System.out.println(s);

            String content[] = s.split("\\|\\|\\|");
            System.out.println("一级二级标签：");
            System.out.println("\t"+content[0]+"   "+content[1]);
            System.out.println();
            System.out.println("吐槽内容：\n");
            System.out.println("\t"+content[2]);
            Scanner a = new Scanner(System.in);
            System.out.println();
            System.out.println("请输入你的标注类型（0-4/多种）：\n\n\n\n\n\n\n\n\n\n");
            int num = a.nextInt();
            output.write(s+num+"\n");
            output.flush();
         //   clearConsole();
        }


        numberReader.close();
        input.close();
        output.close();
    }

    public static void clearConsole() throws AWTException
    {
        Robot r = new Robot();
     //   r.mousePress(InputEvent.BUTTON3_MASK);       // 按下鼠标右键
      //  r.mouseRelease(InputEvent.BUTTON3_MASK);    // 释放鼠标右键
      //  r.keyPress(KeyEvent.VK_CONTROL);             // 按下Ctrl键
        r.keyPress(KeyEvent.VK_R);                    // 按下R键
        r.keyRelease(KeyEvent.VK_R);                  // 释放R键
     //   r.keyRelease(KeyEvent.VK_CONTROL);            // 释放Ctrl键
       // r.delay(10);

    }
}
