package org.koudai.java.练习;

/**
 * @description
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/12/27 21:38
 */
public class Test {

    public static String output="";
    public static void Fun(int i){
        try {
            if(i == 2){
                output += "A";
                throw new Exception();
            }
        }catch (Exception e){
            output += "B";
        }finally {
            output += "C";
        }
        output += "D";
    }




    public static void main(String[] args) {
        Fun(0);
        Fun(2);
        System.out.println(output);
    }
}
