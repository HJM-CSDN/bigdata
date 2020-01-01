package org.koudai.java.练习;

/**
 * @description
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/12/27 21:38
 */
public class Test5 {
    public static void main(String[] args) {
        Student student = new Student();
        student.showInfo(1);
        student.showInfo("han");
    }
}

class Person{
    void showInfo(String string){
        System.out.println("My name is "+ string);
    }
}

class Student extends Person{

    @Override
    void showInfo(String string) {
        super.showInfo(string);
    }

    void showInfo(int age) {
        System.out.println("My age is "+ age);
    }
}