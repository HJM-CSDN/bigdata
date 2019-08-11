package org.koudai.java.数组去重;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 数组去重
 * @Auther: 韩金铭 1329674322@qq.com
 * @Date: 2019/3/14 22:13
 */
public class List去重 {
    public static void main(String[] args) {
        String[] str = {"Java","C","Php","Php","C#"};

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < str.length; i++) {
            if (!list.contains(str[i])){
                list.add(str[i]);
            }
        }
        String[] newStr = list.toArray(new String[1]);//返回一个包含所有对象的指定类型的数组
        for (int i = 0; i < newStr.length; i++) {
            System.out.println(newStr[i]);
        }
    }
}
