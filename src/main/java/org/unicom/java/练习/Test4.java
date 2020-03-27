package org.unicom.java.练习;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/12/27 21:38
 */
public class Test4 {
    public static void main(String[] args) throws IOException {
        String str;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        while (!(str=bf.readLine()).equals("exit")){
            char c = str.charAt(0);
            if (c>='A' && c <= 'Z'){
                c += 32;
                System.out.println(c);
            }else if (c>='a' && c <= 'z'){
                c -= 32;
                System.out.println(c);
            }else {
                System.out.println("输入格式有误，请重新输入！");
            }
        }
    }

}
