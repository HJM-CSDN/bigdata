package org.koudai.java.Date;

import java.util.Scanner;

/**
 * @description 倒计时
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/7/2 17:18
 */
public class CountDown {
    public static void main(String[] args) {
        int hour = 0;
        int min = 0;
        int sec = 60;
        Scanner a = new Scanner(System.in);
        System.out.println("请输入剩余小时：");
        hour = a.nextInt();
        Scanner b = new Scanner(System.in);
        System.out.println("请输入剩余分钟：");
        min = b.nextInt();
        time1(hour*60*60+min*60);

    }

    private static void time1(int time){
        while (time > 0) {
            time--;
                      try {
                Thread.sleep(1000);
                int hh = time / 60 / 60 % 60;
                int mm = time / 60 % 60;
                int ss = time % 60;
                System.out.println("还剩" + hh + "小时" + mm + "分钟" + ss + "秒"+"下班");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                    }
           }
}
