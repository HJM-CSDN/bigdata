package org.unicom.java.小程序;

/**
 * @description 进度条工具
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2020/3/5 09:27
 */
public class JinDuTiao {
    public static void main(String[] args){
        System.out.print("\033[0;1;5;31m");
        System.out.println("----------\n正在下载\n----------");
        System.out.println("\033[0m");
        for(int i=0;i<=100;i++){
            System.out.print("\r");
            StringBuilder sb=new StringBuilder();
            for(int j=0;j<100;j++)
                sb.append(j<i?"=":(i==j?">":" "));
            System.out.print(sb.toString()+"\t"+i+"%");
            try{
                Thread.sleep(100);
            }catch(Exception e){
            }
        }
        System.out.println("\nAll done!");
    }
}
