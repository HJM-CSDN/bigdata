package org.unicom.java.PatternMatcher;

import java.util.regex.Pattern;

/**
 * @description
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/8/14 15:05
 */
public class PatternMatcher {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("\\w+");
        System.out.println(p.pattern());

        Boolean a = Pattern.matches("\\d+\\w+","2222");
        System.out.println(a);
    }
    //todo 自定义
    public static boolean isMatch (String parameter,String pattern){
        return true;
    }

}
