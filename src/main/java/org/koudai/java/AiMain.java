package org.koudai.java;

import java.util.Scanner;

/**
 * @description AI核心代码
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2018/12/28 19:46
 */
public class AiMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;
        while (true){
            str = sc.next();
            str = str.replace("吗","");
            str = str.replace("?","!");
            System.out.println(str);
        }
    }
}
